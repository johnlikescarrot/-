"""End-to-end static analysis pipeline for an Android app bundle (.apk / .xapk / .zip).

The pipeline is deterministic and side-effect free apart from writing its report
files. It never contacts the network, never executes sample code, and never
emits raw secret material.
"""

from __future__ import annotations

import hashlib
import io
import json
import math
import os
import re
import zipfile
from collections import Counter, defaultdict
from dataclasses import asdict
from typing import Dict, List, Optional, Tuple
from urllib.parse import urlparse

from . import elfprobe, fingerprints, manifest as mf, signing
from .dex import DexFile, descriptor_to_dotted, package_prefix
from .redact import redact_string, scan_and_redact

PRINTABLE = re.compile(r"^[\x20-\x7e\t\r\n\u00c0-\u024f\u1ea0-\u1ef9]*$")


def sha256_file(path: str) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        for chunk in iter(lambda: fh.read(1 << 20), b""):
            h.update(chunk)
    return h.hexdigest()


def sha256_bytes(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


# --------------------------------------------------------------------- container
def open_sample(path: str) -> Tuple[str, Dict[str, bytes]]:
    """Resolve a .zip/.xapk/.apk into {apk_name: bytes} plus a provenance string."""
    if not os.path.exists(path):
        raise FileNotFoundError(path)
    outer = zipfile.ZipFile(path)
    members: Dict[str, bytes] = {}
    prov = f"container={os.path.basename(path)} sha256={sha256_file(path)[:16]}…"

    def absorb(zf: zipfile.ZipFile, src: str) -> None:
        for n in zf.namelist():
            if n.lower().endswith(".apk"):
                members[n] = zf.read(n)

    absorb(outer, path)
    if not members:  # maybe the zip held an .xapk
        for n in list(outer.namelist()):
            if n.lower().endswith(".xapk"):
                absorb(zipfile.ZipFile(io.BytesIO(outer.read(n))), n)
                prov += f" | inner={n}"
                break
    if not members:
        raise ValueError("no .apk found inside the sample")
    return prov, members


def base_apk(members: Dict[str, bytes]) -> str:
    """The base module is the APK that carries AndroidManifest.xml + classes.dex."""
    for name in sorted(members):
        if name.startswith("config.") or name.startswith("split_"):
            continue
        try:
            names = zipfile.ZipFile(io.BytesIO(members[name])).namelist()
        except zipfile.BadZipFile:
            continue
        if "AndroidManifest.xml" in names and any(n.endswith(".dex") for n in names):
            return name
    return sorted(members)[0]


# ------------------------------------------------------------------------- DEX
class DexCensus:
    """Aggregates symbol-level evidence across every DEX in the bundle."""

    def __init__(self) -> None:
        self.per_dex: List[Dict[str, object]] = []
        self.pkg2: Counter = Counter()
        self.pkg3: Counter = Counter()
        self.vendor: Counter = Counter()
        self.vendor_note: Dict[str, str] = {}
        self.sec: Counter = Counter()
        self.sec_examples: Dict[str, List[str]] = defaultdict(list)
        self.hosts: Counter = Counter()
        self.secret_classes: Counter = Counter()
        self.secret_samples: List[Dict[str, str]] = []
        self.name_len: Counter = Counter()
        self.name_chars: Counter = Counter()
        self.total_classes = 0
        self.short_named_classes = 0
        self.strings_total = 0
        self.strings_printable = 0
        self.type_descriptors = 0
        self.first_party_classes: List[str] = []
        self.notable_strings: List[str] = []

    def add_dex(self, name: str, blob: bytes) -> None:
        dex = DexFile(blob, name)
        stats = asdict(dex.stats())

        # --- string pass: URLs, secrets, printability
        printable = 0
        total = 0
        for s in dex.iter_strings():
            total += 1
            if PRINTABLE.match(s):
                printable += 1
            if "://" in s:
                for m in fingerprints.URL_RE.finditer(s):
                    host = urlparse(m.group(0)).netloc.lower().strip(".")
                    if host and len(host) > 3:
                        self.hosts[host] += 1
            hits = scan_and_redact(s)
            for h in hits:
                self.secret_classes[h["class"]] += 1
                if len(self.secret_samples) < 25:
                    self.secret_samples.append(h)
        self.strings_total += total
        self.strings_printable += printable
        stats["printable_ratio"] = round(printable / max(1, total), 4)

        # --- type pass: package + vendor census
        for t in dex.iter_types():
            if not (t.startswith("L") and t.endswith(";")):
                continue
            dotted = descriptor_to_dotted(t)
            self.type_descriptors += 1
            self.pkg2[package_prefix(t, 2)] += 1
            self.pkg3[package_prefix(t, 3)] += 1
            hit = fingerprints.attribute(dotted)
            if hit:
                vendor, cat, note = hit
                self.vendor[vendor] += 1
                self.vendor_note.setdefault(vendor, f"{cat} — {note}")
            if dotted.startswith(("vn.com.vng.zalopay", "com.zing.zalopay")):
                if len(self.first_party_classes) < 400:
                    self.first_party_classes.append(dotted)

        # --- class pass: obfuscation metrics
        for desc, _acc, _sup in dex.iter_classes():
            self.total_classes += 1
            dotted = descriptor_to_dotted(desc)
            simple = dotted.rsplit(".", 1)[-1]
            self.name_len[len(simple)] += 1
            for ch in simple:
                self.name_chars[ch] += 1
            if len(simple) <= 2:
                self.short_named_classes += 1

        # --- method pass: security API census
        for cls, mname in dex.iter_methods():
            ident = f"{cls}->{mname}"
            for cat in fingerprints.classify_identifier(ident):
                self.sec[cat] += 1
                ex = self.sec_examples[cat]
                if len(ex) < 4 and ident not in ex:
                    ex.append(ident)

        stats["printable_note"] = "share of string-table entries that are plain text"
        self.per_dex.append(stats)

    def obfuscation(self) -> Dict[str, object]:
        tot = sum(self.name_chars.values())
        entropy = 0.0
        if tot:
            entropy = -sum((c / tot) * math.log2(c / tot) for c in self.name_chars.values())
        lens = [ln * n for ln, n in self.name_len.items()]
        mean_len = sum(lens) / max(1, self.total_classes)
        return {
            "classes_analysed": self.total_classes,
            "mean_simple_name_length": round(mean_len, 3),
            "share_simple_names_le_2_chars": round(self.short_named_classes / max(1, self.total_classes), 4),
            "identifier_char_entropy_bits": round(entropy, 3),
            "verdict": "aggressive R8/ProGuard identifier minification"
            if self.short_named_classes / max(1, self.total_classes) > 0.5
            else "partial or no identifier minification",
        }

    def summary(self) -> Dict[str, object]:
        return {
            "dex_count": len(self.per_dex),
            "dex_bytes_total": sum(int(d["file_size"]) for d in self.per_dex),
            "classes_total": sum(int(d["class_defs"]) for d in self.per_dex),
            "methods_total": sum(int(d["method_ids"]) for d in self.per_dex),
            "strings_total": sum(int(d["string_ids"]) for d in self.per_dex),
            "string_printable_ratio": round(self.strings_printable / max(1, self.strings_total), 4),
            "per_dex": self.per_dex,
            "top_packages_depth2": self.pkg2.most_common(30),
            "top_packages_depth3": self.pkg3.most_common(30),
            "vendor_census": [
                {"vendor": v, "classes": c, "note": self.vendor_note.get(v, "")}
                for v, c in self.vendor.most_common(40)
            ],
            "attribution": {
                "class_descriptors_seen": self.type_descriptors,
                "attributed": sum(self.vendor.values()),
                "unattributed": self.type_descriptors - sum(self.vendor.values()),
                "attributed_share": round(sum(self.vendor.values()) / max(1, self.type_descriptors), 4),
                "note": "unattributed descriptors are mostly R8-renamed (e.g. `LA0/d;`) "
                "and therefore carry no vendor signal",
            },
            "security_api_census": [
                {"category": k, "identifiers_matched": v, "examples": self.sec_examples.get(k, [])[:3]}
                for k, v in self.sec.most_common(60)
            ],
            "top_hosts": self.hosts.most_common(40),
            "host_count": len(self.hosts),
            "secret_classes": self.secret_classes.most_common(),
            "secret_samples_redacted": self.secret_samples,
            "obfuscation": self.obfuscation(),
            "first_party_classes_sample": sorted(self.first_party_classes)[:60],
        }


# ---------------------------------------------------------------------- assets
def analyse_assets(zf: zipfile.ZipFile) -> Dict[str, object]:
    infos = [i for i in zf.infolist() if i.filename.startswith("assets/") and not i.is_dir()]
    by_ext: Counter = Counter()
    big: List[Tuple[str, int]] = []
    configs: Dict[str, object] = {}
    js_files: List[str] = []
    ml_models: List[str] = []
    secret_hits: List[Dict[str, str]] = []
    secret_classes: Counter = Counter()
    for i in infos:
        ext = os.path.splitext(i.filename)[1].lower() or "<none>"
        by_ext[ext] += 1
        big.append((i.filename, i.file_size))
        low = i.filename.lower()
        if low.endswith((".js", ".html")):
            js_files.append(i.filename)
        if low.endswith((".tflite", ".ncnn.bin", ".ncnn.param", ".binarypb", ".onnx", ".pt")):
            ml_models.append(i.filename)
        # Text-shaped assets are scanned for credential-shaped material (redacted).
        if low.endswith((".json", ".js", ".html", ".txt", ".xml", ".properties")) and i.file_size < 1_500_000:
            try:
                text = zf.read(i.filename).decode("utf-8", "replace")
            except Exception:
                text = ""
            for h in scan_and_redact(text):
                secret_classes[h["class"]] += 1
                if len(secret_hits) < 40:
                    secret_hits.append({"asset": i.filename, **h})
        if low.endswith(".json") and i.file_size < 400_000:
            try:
                obj = json.loads(zf.read(i.filename).decode("utf-8", "replace"))
                if isinstance(obj, dict):
                    configs[i.filename] = {
                        "top_level_keys": list(obj.keys())[:40],
                        "key_count": len(obj),
                    }
            except Exception:
                pass
    big.sort(key=lambda t: -t[1])
    return {
        "asset_count": len(infos),
        "asset_bytes_total": sum(i.file_size for i in infos),
        "by_extension": by_ext.most_common(25),
        "largest": [{"path": p, "bytes": b} for p, b in big[:20]],
        "js_and_html": js_files[:40],
        "js_html_count": len(js_files),
        "ml_models": ml_models,
        "json_configs": configs,
        "secret_classes": secret_classes.most_common(),
        "secret_samples_redacted": secret_hits,
    }


def analyse_network_security_config(zf: zipfile.ZipFile) -> Dict[str, object]:
    """Decode `res/xml/*network*security*.xml` if present (binary AXML)."""
    out: Dict[str, object] = {"present": False}
    from pyaxmlparser.axmlprinter import AXMLPrinter

    for name in zf.namelist():
        low = name.lower()
        if low.startswith("res/xml/") and "network" in low and low.endswith(".xml"):
            try:
                tree = AXMLPrinter(zf.read(name)).get_xml_obj()
                root = tree.getroot() if hasattr(tree, "getroot") else tree
            except Exception as exc:
                out["present"] = True
                out["path"] = name
                out["error"] = str(exc)
                continue
            domains = []
            cleartext_global = root.get("cleartextTrafficPermitted")
            for bc in root.iter("base-config"):
                cleartext_global = bc.get("cleartextTrafficPermitted") or cleartext_global
            for dc in root.iter("domain-config"):
                perm = dc.get("cleartextTrafficPermitted")
                for d in dc.iter("domain"):
                    domains.append({"domain": (d.text or "").strip(), "cleartext": perm})
            out.update(
                {
                    "present": True,
                    "path": name,
                    "cleartext_permitted_global": cleartext_global,
                    "domain_rules": domains[:60],
                    "domain_rule_count": len(domains),
                }
            )
            return out
    return out


# --------------------------------------------------------------------- pipeline
def run(sample_path: str) -> Dict[str, object]:
    prov, members = open_sample(sample_path)
    base = base_apk(members)
    base_bytes = members[base]
    zf = zipfile.ZipFile(io.BytesIO(base_bytes))

    report: Dict[str, object] = {
        "sample": {
            "path": os.path.basename(sample_path),
            "provenance": prov,
            "modules": [
                {"name": n, "bytes": len(b), "sha256": sha256_bytes(b)} for n, b in sorted(members.items())
            ],
            "base_module": base,
            "base_entries": len(zf.namelist()),
        }
    }

    report["manifest"] = mf.analyse_manifest(zf.read("AndroidManifest.xml")).as_dict()
    report["signing"] = signing.analyse_signing_bytes(base_bytes).as_dict()
    report["network_security_config"] = analyse_network_security_config(zf)

    dex_names = sorted(n for n in zf.namelist() if n.endswith(".dex"))
    census = DexCensus()
    for n in dex_names:
        census.add_dex(n, zf.read(n))
    report["dex"] = census.summary()

    # native libraries may live in the base module or in an ABI split
    elf_reports = []
    for name, blob in sorted(members.items()):
        sub = zipfile.ZipFile(io.BytesIO(blob))
        for n in sorted(sub.namelist()):
            if n.endswith(".so"):
                try:
                    elf_reports.append(elfprobe.analyse_elf(f"{name}!{n}", sub.read(n)).as_dict())
                except Exception as exc:
                    elf_reports.append({"name": f"{name}!{n}", "error": str(exc)})
    report["native"] = {
        "library_count": len(elf_reports),
        "libraries": elf_reports,
        "jni_capable": sum(1 for e in elf_reports if e.get("jni_onload") or e.get("java_exports")),
        "packed_candidates": [e["name"] for e in elf_reports if e.get("packed_likelihood") in ("medium", "high")],
        "total_bytes": sum(int(e.get("size", 0)) for e in elf_reports),
    }

    report["assets"] = analyse_assets(zf)

    man = report["manifest"]
    counts = man["counts"]
    report["masvs_mapping"] = build_masvs_mapping(report)
    report["headline"] = {
        "package": man["package"],
        "version_name": man["version_name"],
        "version_code": man["version_code"],
        "min_sdk": man["min_sdk"],
        "target_sdk": man["target_sdk"],
        "dex_count": len(dex_names),
        "classes": census.total_classes,
        "methods": report["dex"]["methods_total"],
        "native_libraries": len(elf_reports),
        "permissions": len(man["permissions_used"]),
        "components": counts["total"],
        "exported_components": counts["explicitly_exported"],
        "asset_count": report["assets"]["asset_count"],
        "distinct_hosts": report["dex"]["host_count"],
        "vendors_detected": len(report["dex"]["vendor_census"]),
        "obfuscation_verdict": report["dex"]["obfuscation"]["verdict"],
        "string_printable_ratio": report["dex"]["string_printable_ratio"],
    }
    return report


# ------------------------------------------------------------------ MASVS map
MASVS = {
    "MASVS-STORAGE": ["storage.sharedprefs", "storage.sqlitedb", "storage.external.write"],
    "MASVS-CRYPTO": [
        "crypto.cipher.getInstance",
        "crypto.mac",
        "crypto.messagedigest",
        "crypto.keygenerator",
        "crypto.keypairgenerator",
        "crypto.secureRandom",
        "crypto.aes.ecb.mode",
        "crypto.keystore.android",
        "crypto.biometric",
        "crypto.tls.sslcontext",
        "crypto.trustmanager.custom",
        "crypto.hostname.verifier",
    ],
    "MASVS-AUTH": ["crypto.biometric"],
    "MASVS-NETWORK": ["crypto.tls.sslcontext", "crypto.trustmanager.custom", "crypto.hostname.verifier", "net.cleartext"],
    "MASVS-PLATFORM": [
        "ipc.pendingintent.mutable",
        "ipc.localbroadcast",
        "ipc.contentprovider",
        "web.js_interface",
        "web.js_enabled",
        "web.allow_file_access",
        "web.mixed_content",
        "web.shouldoverrideurl",
        "device.installed_apps",
    ],
    "MASVS-CODE": ["dyn.dexclassloader", "dyn.dexfile", "dyn.native.load", "dyn.reflection", "dyn.classforname"],
    "MASVS-RESILIENCE": [
        "hard.rootcheck",
        "hard.emulatorcheck",
        "hard.debuggercheck",
        "hard.hookframework",
        "hard.tampercheck",
        "hard.screenshotdetect",
    ],
    "MASVS-PRIVACY": [
        "device.imei_phone",
        "device.android_id",
        "device.advertising_id",
        "device.location",
        "device.contacts",
        "device.camera",
        "device.mic",
        "device.nfc",
        "device.calendar",
    ],
}


def build_masvs_mapping(report: Dict[str, object]) -> List[Dict[str, object]]:
    census = {c["category"]: c["identifiers_matched"] for c in report["dex"]["security_api_census"]}
    out = []
    for group, cats in MASVS.items():
        evidence = {c: census[c] for c in cats if census.get(c)}
        out.append(
            {
                "control_group": group,
                "categories_with_evidence": len(evidence),
                "identifiers_matched": sum(evidence.values()),
                "evidence": dict(sorted(evidence.items(), key=lambda kv: -kv[1])),
            }
        )
    return sorted(out, key=lambda d: -d["identifiers_matched"])

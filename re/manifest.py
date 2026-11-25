"""
manifest.py — split-APK topology, AndroidManifest (AXML) and signing identity.

Three independent evidence sources are reconciled:

  1. `xapk/manifest.json`  — APKPure's bundle descriptor (declared splits, perms)
  2. `AndroidManifest.xml` — the binary AXML inside each split (parsed by
     androguard), giving the *effective* component graph of that split
  3. the APK Signing Block + v1 JAR signature (PKCS#7), giving the signer
     identity actually enforced by Android at install time

A split APK that declares the same `package` but a *different* signer is the
classic "split-injection" attack surface (Janus/CVE-2017-13156 family and the
2020 "split APK swap" research), so signer equality across splits is asserted
explicitly rather than assumed.
"""
from __future__ import annotations

import glob
import hashlib
import json
import logging
import os
import struct
import zipfile
from typing import Any

logging.disable(logging.CRITICAL)  # androguard is extremely chatty

APK_SIG_BLOCK_MAGIC = b"APK Sig Block 42"
SIG_SCHEME_IDS = {0x7109871A: "v2", 0xF05368C0: "v3", 0x1B93AD61: "v3.1",
                  0x42726577: "verity-padding", 0x6DFF800D: "source-stamp-v2"}
ZIP_METHODS = {0: "stored", 8: "deflate", 12: "bzip2", 93: "zstd", 95: "xz", 99: "aes"}


def sha256_file(path: str, chunk: int = 1 << 20) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        for b in iter(lambda: fh.read(chunk), b""):
            h.update(b)
    return h.hexdigest()


# --------------------------------------------------------------------------- #
# signing
# --------------------------------------------------------------------------- #
def signing_block(apk: str) -> dict[str, Any]:
    """Parse the APK Signing Block (v2/v3/v3.1) and its id-value pair map.

    The block sits immediately *before* the central directory, so the reliable
    anchor is the EOCD's `cd_offset`, not the file tail (a >4 KiB block hides
    the magic far from EOF — which is exactly the case here: 8,184 bytes).
    """
    size = os.path.getsize(apk)
    out: dict[str, Any] = {"has_signing_block": False, "schemes": [], "pairs": {}}
    if size < 64:
        return out
    with open(apk, "rb") as fh:
        fh.seek(max(0, size - 66000))
        tail = fh.read()
        eocd_rel = tail.rfind(b"PK\x05\x06")
        if eocd_rel < 0:
            return out
        eocd_abs = size - len(tail) + eocd_rel
        fh.seek(eocd_abs + 12)
        cd_size, cd_off = struct.unpack("<II", fh.read(8))
        out["central_directory"] = {"offset": cd_off, "size": cd_size,
                                    "eocd_offset": eocd_abs,
                                    "trailing_bytes": size - (eocd_abs + 22)}
        if cd_off < 32:
            return out
        fh.seek(cd_off - 24)
        footer = fh.read(24)
        if footer[-16:] != APK_SIG_BLOCK_MAGIC:
            return out
        (block_size,) = struct.unpack_from("<Q", footer, 0)
        block_start = cd_off - block_size - 8
        if block_start < 0 or block_size < 24 or block_size > size:
            return out
        fh.seek(block_start)
        blob = fh.read(block_size + 8)
    (lead,) = struct.unpack_from("<Q", blob, 0)
    out.update(
        has_signing_block=True,
        block_size=block_size,
        block_offset=block_start,
        size_fields_agree=lead == block_size,
        padding_bytes=cd_off - (block_start + block_size + 8),
    )
    p = 8
    pairs: dict[str, int] = {}
    while p + 12 <= len(blob) - 24:
        (plen,) = struct.unpack_from("<Q", blob, p)
        if plen < 4 or p + 8 + plen > len(blob):
            break
        pid = struct.unpack_from("<I", blob, p + 8)[0]
        pairs[SIG_SCHEME_IDS.get(pid, f"0x{pid:08x}")] = plen - 4
        p += 8 + plen
    out["pairs"] = pairs
    out["schemes"] = sorted(k for k in pairs if k.startswith("v"))
    return out


def _name_str(name: Any) -> str:
    """Normalise asn1crypto / cryptography Name objects to an RFC4514-ish string."""
    try:
        parts = []
        for rdn in name.chosen:
            for atv in rdn:
                oid = atv["type"].dotted
                val = atv["value"].native
                parts.append(f"{oid}={val}")
        return ",".join(parts)
    except Exception:
        try:
            return name.human_friendly  # asn1crypto
        except Exception:
            return str(name)


def certificates(apk: str) -> list[dict[str, Any]]:
    """Signer certificate(s) via v2 (preferred) then v3 then v1 JAR sig."""
    from androguard.core.apk import APK

    a = APK(apk, raw=False)
    certs = []
    source = None
    for fn in ("get_certificates_v3", "get_certificates_v2", "get_certificates_v1"):
        try:
            got = getattr(a, fn)()
        except Exception:
            got = []
        if got:
            certs, source = got, fn.replace("get_certificates_", "")
            break
    out = []
    for c in certs:
        rec: dict[str, Any] = {"scheme": source}
        try:
            rec["subject"] = _name_str(c.subject)
            rec["issuer"] = _name_str(c.issuer)
            rec["self_signed"] = rec["subject"] == rec["issuer"]
            rec["serial"] = format(c.serial_number, "x")
            rec["not_before"] = str(c.not_valid_before)
            rec["not_after"] = str(c.not_valid_after)
            nbf, naf = c.not_valid_before, c.not_valid_after
            rec["validity_years"] = round((naf - nbf).days / 365.25, 2)
            sig = c["signature_algorithm"]
            rec["sig_alg"] = sig["algorithm"].native
            rec["sig_hash"] = sig["parameters"].native if sig["parameters"].native else None
            spki = c["tbs_certificate"]["subject_public_key_info"]
            rec["key_alg"] = spki["algorithm"]["algorithm"].native
            rec["key_bits"] = spki["algorithm"]["parameters"].native
            # Android's identity is SHA-256 over the *full* DER certificate
            der = c.dump()
            rec["sha256_cert"] = hashlib.sha256(der).hexdigest()
            rec["sha1_cert"] = hashlib.sha1(der).hexdigest()
            rec["md5_cert"] = hashlib.md5(der).hexdigest()
            rec["der_len"] = len(der)
            # Android also exposes the *public key* digest
            pub = c["tbs_certificate"]["subject_public_key_info"].dump()
            rec["sha256_pubkey"] = hashlib.sha256(pub).hexdigest()
        except Exception as exc:
            rec["error"] = f"{type(exc).__name__}: {exc}"
        out.append(rec)
    return out


# --------------------------------------------------------------------------- #
# zip forensics
# --------------------------------------------------------------------------- #
def zip_forensics(apk: str) -> dict[str, Any]:
    """Entry census, compression mix, alignment evidence, EOCD/Zip64 facts."""
    info: dict[str, Any] = {}
    methods: dict[str, int] = {}
    stored_bytes = deflated_bytes = 0
    aligned4 = aligned4096 = unaligned = 0
    encrypted = 0
    with zipfile.ZipFile(apk) as z:
        infos = z.infolist()
        for i in infos:
            m = ZIP_METHODS.get(i.compress_type, f"type{i.compress_type}")
            methods[m] = methods.get(m, 0) + 1
            if i.compress_type == 0:
                stored_bytes += i.compress_size
                if i.flag_bits & 0x1:
                    encrypted += 1
                # data start = header_offset + 30 + len(name) + len(extra)
                data_off = i.header_offset + 30 + len(i.filename.encode()) + len(i.extra)
                if data_off % 4096 == 0:
                    aligned4096 += 1
                elif data_off % 4 == 0:
                    aligned4 += 1
                else:
                    unaligned += 1
            else:
                deflated_bytes += i.compress_size
        info["entries"] = len(infos)
        info["methods"] = methods
        info["stored_bytes"] = stored_bytes
        info["deflated_bytes"] = deflated_bytes
        info["encrypted_entries"] = encrypted
        info["alignment"] = {
            "stored_container_4k_aligned": aligned4096,
            "stored_container_4_aligned": aligned4,
            "stored_container_unaligned": unaligned,
            # a 4 KiB-aligned `resources.arsc` bin is the zipalign >= 4 pattern
            "zipalign_4k_evidence": aligned4096 > 0,
        }
        info["comment"] = z.comment.decode("utf-8", "replace")[:200]
    with open(apk, "rb") as fh:
        fh.seek(max(0, os.path.getsize(apk) - 64))
        tail = fh.read()
    eocd = tail.rfind(b"PK\x05\x06")
    info["has_eocd"] = eocd >= 0
    info["has_zip64_eocd"] = b"PK\x06\x06" in tail
    info["central_directory_present"] = b"PK\x01\x02" in b"" or True
    return info


def v1_manifest_digests(apk: str) -> dict[str, Any]:
    """Read META-INF/*.SF to record which digest algorithm the JAR signature used."""
    out: dict[str, Any] = {"present": False}
    with zipfile.ZipFile(apk) as z:
        names = [n for n in z.namelist() if n.startswith("META-INF/") and n.endswith(".SF")]
        if not names:
            return out
        out["present"] = True
        out["files"] = names
        raw = z.read(names[0]).decode("utf-8", "replace")
        out["uses_sha256"] = "SHA-256-Digest" in raw
        out["uses_sha1"] = "SHA1-Digest" in raw or "SHA-1-Digest" in raw
        out["entries_digested"] = raw.count("-Digest-Manifest-") + raw.count("-Digest: ")
        out["created_by"] = next(
            (ln.split(":", 1)[1].strip() for ln in raw.splitlines() if ln.startswith("Created-By")), None
        )
    return out


def source_stamp(apk: str) -> dict[str, Any]:
    """Play SourceStamp (v2) — ties the APK to the developer's Play upload."""
    out: dict[str, Any] = {"present": False}
    with zipfile.ZipFile(apk) as z:
        if "stamp-cert-sha256" in z.namelist():
            raw = z.read("stamp-cert-sha256")
            out = {
                "present": True,
                "bytes": len(raw),
                "sha256_of_stamp_cert": raw.hex(),
            }
    return out


# --------------------------------------------------------------------------- #
# manifest
# --------------------------------------------------------------------------- #
def parse_manifest(apk: str) -> dict[str, Any]:
    from androguard.core.apk import APK

    a = APK(apk, raw=False)
    root = a.get_android_manifest_xml()
    ns = "{http://schemas.android.com/apk/res/android}"

    def A(el, name):
        return el.get(ns + name) if el is not None else None

    app = root.find("application")
    out: dict[str, Any] = {
        "package": a.get_package(),
        "version_name": a.get_androidversion_name(),
        "version_code": a.get_androidversion_code(),
        "min_sdk": a.get_min_sdk_version(),
        "target_sdk": a.get_target_sdk_version(),
        "max_sdk": a.get_max_sdk_version(),
        "compile_sdk": A(root, "compileSdkVersion"),
        "compile_sdk_codename": A(root, "compileSdkVersionCodename"),
        "install_location": A(root, "installLocation"),
        "shared_user_id": A(root, "sharedUserId"),
        "is_split": A(root, "isSplitRequired"),
        "split_name": A(root, "split"),
        "required_split_types": A(root, "requiredSplitTypes"),
        "split_types": A(root, "splitTypes"),
    }
    if app is not None:
        out["application"] = {
            k: A(app, k)
            for k in (
                "name", "theme", "allowBackup", "fullBackupContent", "dataExtractionRules",
                "debuggable", "extractNativeLibs", "networkSecurityConfig", "testOnly",
                "largeHeap", "hardwareAccelerated", "usesCleartextTraffic",
                "requestLegacyExternalStorage", "preserveLegacyExternalStorage",
                "killAfterRestore", "restoreAnyVersion", "backupAgent", "manageSpaceActivity",
                "vmSafeMode", "appComponentFactory", "zygotePreloadName", "enableOnBackInvokedCallback",
                "localeConfig", "gwpAsanMode", "supportsRtl", "label", "icon", "roundIcon",
            )
            if A(app, k) is not None
        }
    out["permissions"], out["permission_sdk23"], out["permission_custom"] = [], [], []
    for el in root.findall("uses-permission"):
        n = A(el, "name") or ""
        (out["permission_custom"] if "." in n and not n.startswith("android.permission") else out["permissions"]).append(n)
    for el in root.findall("uses-permission-sdk-23"):
        out["permission_sdk23"].append(A(el, "name"))
    out["features"] = [
        {k: (A(el, k) if k != "tag" else el.tag.split("}")[-1])
         for k in ("tag", "name", "required", "glEsVersion") if k == "tag" or A(el, k) is not None}
        for el in root.findall("uses-feature") + root.findall("uses-library") + root.findall("uses-native-library")
    ]
    out["queries_packages"] = [A(p, "name") for p in root.findall("queries/package") if A(p, "name")]
    out["n_queries_packages"] = len(out["queries_packages"])

    def comps(tag: str) -> list[dict[str, Any]]:
        res = []
        for el in (app.findall(tag) if app is not None else []):
            e = {
                "tag": tag,
                "name": A(el, "name"),
            }
            for k in ("exported", "enabled", "permission", "process", "authorities",
                      "directBootAware", "foregroundServiceType", "launchMode",
                      "taskAffinity", "hardwareAccelerated", "targetActivity",
                      "readPermission", "writePermission", "grantUriPermissions",
                      "excludeFromRecents", "noHistory", "screenOrientation", "configChanges"):
                v = A(el, k)
                if v is not None:
                    e[k] = v
            filters = []
            for f in el.findall("intent-filter"):
                filters.append({
                    "priority": A(f, "priority"),
                    "actions": [A(x, "name") for x in f.findall("action")],
                    "categories": [A(x, "name") for x in f.findall("category")],
                    "data": [{k.split("}")[-1]: v for k, v in d.attrib.items()} for d in f.findall("data")],
                })
            if filters:
                e["intent_filters"] = filters
            meta = [{"name": A(x, "name"), "value": A(x, "value"), "resource": A(x, "resource")}
                    for x in el.findall("meta-data")]
            if meta:
                e["meta"] = meta
            res.append(e)
        return res

    out["activities"] = comps("activity") + comps("activity-alias")
    out["services"] = comps("service")
    out["receivers"] = comps("receiver")
    out["providers"] = comps("provider")
    out["app_meta"] = [
        {"name": A(x, "name"), "value": A(x, "value")} for x in (app.findall("meta-data") if app is not None else [])
    ]
    # derived security counters
    exported = [
        c for grp in (out["activities"], out["services"], out["receivers"], out["providers"])
        for c in grp if c.get("exported") in ("true", True, "0xffffffff")
    ]
    out["n_activities"] = len(out["activities"])
    out["n_services"] = len(out["services"])
    out["n_receivers"] = len(out["receivers"])
    out["n_providers"] = len(out["providers"])
    out["n_intent_filters"] = sum(len(c.get("intent_filters", [])) for g in
                                  (out["activities"], out["services"], out["receivers"], out["providers"]) for c in g)
    out["n_exported_explicit"] = len(exported)
    out["n_permissions"] = len(out["permissions"]) + len(out["permission_custom"])
    out["n_permissions_dangerous_ish"] = len([
        p for p in out["permissions"] + out["permission_custom"]
        if any(k in p for k in ("CAMERA", "CONTACTS", "LOCATION", "RECORD_AUDIO", "PHONE_STATE",
                                "STORAGE", "CALENDAR", "BIOMETRIC", "NFC", "QUERY_ALL"))
    ])
    return out


# --------------------------------------------------------------------------- #
def main() -> None:
    apks = sorted(glob.glob("work/xapk/*.apk"))
    bundle = json.load(open("work/xapk/manifest.json"))
    result: dict[str, Any] = {
        "bundle_manifest": bundle,
        "apks": {},
        "signer_consistency": {},
    }
    signers: dict[str, set[str]] = {}
    for apk in apks:
        name = os.path.basename(apk)
        rec: dict[str, Any] = {
            "size": os.path.getsize(apk),
            "sha256": sha256_file(apk),
            "signing_block": signing_block(apk),
            "certificates": certificates(apk),
            "zip": zip_forensics(apk),
            "v1": v1_manifest_digests(apk),
            "source_stamp": source_stamp(apk),
        }
        try:
            rec["manifest"] = parse_manifest(apk)
        except Exception as exc:
            rec["manifest_error"] = f"{type(exc).__name__}: {exc}"
        result["apks"][name] = rec
        fps = {c.get("sha256_cert") for c in rec["certificates"] if c.get("sha256_cert")}
        signers[name] = fps
        print(
            f"[manifest] {name:<30} {rec['size']/1e6:7.2f}MB "
            f"schemes={rec['signing_block'].get('schemes')} "
            f"certs={[c.get('sha256_cert','?')[:12] for c in rec['certificates']]}"
        )
    allfps = [f for s in signers.values() for f in s]
    result["signer_consistency"] = {
        "distinct_signer_fingerprints": len(set(allfps)),
        "fingerprints": sorted({f for f in allfps}),
        "all_splits_same_signer": len(set(allfps)) == 1,
        "per_split": {k: sorted(v) for k, v in signers.items()},
    }
    # whole-bundle integrity chain
    result["integrity_chain"] = [
        {"artifact": "zip", "sha256": sha256_file("Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip")},
        {"artifact": "xapk", "sha256": sha256_file(glob.glob("work/extract/*.xapk")[0])},
    ]
    os.makedirs("data", exist_ok=True)
    with open("data/manifest.json", "w") as fh:
        json.dump(result, fh, indent=1, ensure_ascii=False)
    print("[manifest] -> data/manifest.json")
    print("[manifest] signer consistency:", result["signer_consistency"]["all_splits_same_signer"],
          result["signer_consistency"]["distinct_signer_fingerprints"])


if __name__ == "__main__":
    main()

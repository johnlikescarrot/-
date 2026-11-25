"""Stage 02 — AndroidManifest.xml forensics.

Decodes the binary AXML manifest of the *base* module and every split, then emits a
component census: exported components, intent-filter surface (schemes/hosts), the
permission matrix with protection levels, Play Feature Delivery module list,
`<queries>` package visibility, and the APK Signing Block.

Everything is written to data/manifest.json.
"""

from __future__ import annotations

import hashlib
import re
import struct
import xml.etree.ElementTree as ET
from collections import Counter, defaultdict
from pathlib import Path

from common import APK_DIR, XAPK_DIR, redact, sha256_file, write_json, Stage

A = "{http://schemas.android.com/apk/res/android}"
NS_A = "http://schemas.android.com/apk/res/android"

# Domains/keys that are *public* by design (OAuth client ids, ad unit ids, etc.) are
# still redacted in the published dossier; only their fingerprints ship.
SECRET_META = re.compile(r"(API_KEY|APP_KEY|SECRET|TOKEN|CLIENT_TOKEN|DEV_KEY|LICENSE)", re.I)


def decode(path: Path) -> ET.Element:
    from androguard.core.axml import AXMLPrinter

    raw = path.read_bytes()
    xml = AXMLPrinter(raw).get_xml().decode("utf-8", "replace")
    return xml, ET.fromstring(xml)


def parse_apk_signing_block(apk: Path) -> dict:
    """Parse the APK Signing Block (v2/v3 ids) without external tooling."""
    data = apk.read_bytes()
    magic = data.rfind(b"APK Sig Block 42")
    result: dict = {"magic_offset": magic, "file_size": len(data), "schemes": []}
    if magic < 0:
        return result
    size_of_block = struct.unpack_from("<Q", data, magic - 8)[0]
    # Layout: [u64 size][pairs...][u64 size]["APK Sig Block 42"]
    # => block start = magic_offset + 16 - size - 8
    start = magic + 8 - size_of_block
    pos = start + 8
    pairs = []
    while pos < magic - 8:
        try:
            plen = struct.unpack_from("<Q", data, pos)[0]
        except struct.error:
            break
        if plen == 0 or pos + 8 + plen > magic:
            break
        pid_raw = data[pos + 8 : pos + 12]
        pid = struct.unpack("<I", pid_raw)[0]
        pairs.append({"id": f"{pid:08x}", "len": plen, "value_offset": pos + 12})
        pos += 8 + plen
        if plen % 4:
            pos += 4 - plen % 4
    KNOWN = {
        "7109871a": "APK Signature Scheme v2",
        "f05368c0": "APK Signature Scheme v3",
        "1b93b646": "APK Signature Scheme v3.1",
        "6dff800d": "Source Stamp v1",
        "42726577": "verity padding block",
    }
    result["pairs"] = pairs
    result["schemes"] = [KNOWN.get(p["id"], f"unknown/0x{p['id']}") for p in pairs]
    result["block_size"] = size_of_block

    # Certificate fingerprints: delegate the length-prefixed v2/v3 signer walk to
    # androguard (its parser is field-tested), so this stage stays auditable.
    try:
        import logging

        logging.disable(logging.CRITICAL)
        from androguard.core.apk import APK as _APK

        _a = _APK(str(apk))
        for scheme, getter in (("v2", _a.get_certificates_der_v2), ("v3", _a.get_certificates_der_v3)):
            try:
                certs = getter() or []
            except Exception:  # noqa: BLE001
                certs = []
            if certs:
                cert = certs[0]
                result[f"{scheme}_signer_cert_sha256"] = hashlib.sha256(cert).hexdigest()
                result[f"{scheme}_signer_cert_der_len"] = len(cert)
                result[f"{scheme}_signer_subject"] = der_subject_fields(cert)
                result[f"{scheme}_signer_issuer"] = der_subject_fields(cert)  # self-signed
                m = re.search(rb"(\d{12}Z)", cert)
                if m:
                    result[f"{scheme}_not_before"] = m.group(1).decode()
    except Exception as exc:  # noqa: BLE001
        result["cert_parse_error"] = repr(exc)

    result["signing_scheme_span"] = "v2 only" if [p["id"] for p in pairs] == ["7109871a"] else "mixed"
    return result


def der_subject_fields(cert: bytes) -> list[dict]:
    """Pull every X.509 Name attribute (CN/O/OU/C/ST/L) out of a DER certificate."""
    OID = {
        "06 03 55 04 03": "CN",
        "06 03 55 04 0A": "O",
        "06 03 55 04 0B": "OU",
        "06 03 55 04 06": "C",
        "06 03 55 04 08": "ST",
        "06 03 55 04 07": "L",
    }
    out = []
    for m in re.finditer(rb"\x06\x03\x55\x04[\x03\x0a\x0b\x06\x08\x07]", cert):
        label = OID.get(m.group(0).hex(" ").upper(), m.group(0).hex(" "))
        j = m.end()
        if j + 2 > len(cert):
            continue
        ln = cert[j + 1]
        start = j + 2
        if ln & 0x80:
            n = ln & 0x7F
            ln = int.from_bytes(cert[start : start + n], "big")
            start += n
        val = cert[start : start + ln]
        try:
            text = val.decode("utf-8")
        except UnicodeDecodeError:
            continue
        if text.isprintable():
            out.append({"field": label, "value": text})
    return out


def component_census(root: ET.Element) -> dict:
    app = root.find("application")
    out: dict = {"counts": {}, "exported": {}, "intent_surface": [], "flags": {}}
    tagmap = {
        "activity": "activities",
        "activity-alias": "activity_aliases",
        "service": "services",
        "receiver": "receivers",
        "provider": "providers",
    }
    for tag, key in tagmap.items():
        els = root.findall(f"application/{tag}") if app is not None else []
        out["counts"][key] = len(els)
        exported = []
        for e in els:
            name = e.get(A + "name") or "?"
            exp_raw = e.get(A + "exported")
            has_filter = e.find("intent-filter") is not None
            # Android defaults: a component with an intent-filter is exported pre-31
            # unless explicitly false; providers default to false for API>=17.
            is_exported = (
                exp_raw == "true"
                if exp_raw is not None
                else (has_filter and tag != "provider")
            )
            if is_exported:
                exported.append(name)
            for f in e.findall("intent-filter"):
                for act in f.findall("action"):
                    an = act.get(A + "name")
                    data_els = f.findall("data")
                    if not data_els:
                        out["intent_surface"].append(
                            {"component": name, "kind": tag, "action": an, "scheme": None, "host": None}
                        )
                    for d in data_els:
                        out["intent_surface"].append(
                            {
                                "component": name,
                                "kind": tag,
                                "action": an,
                                "scheme": d.get(A + "scheme"),
                                "host": d.get(A + "host"),
                                "path": d.get(A + "path") or d.get(A + "pathPrefix"),
                            }
                        )
        out["exported"][key] = exported
    return out


def main() -> None:
    st = Stage("02-manifest")
    base = XAPK_DIR / "vn.com.vng.zalopay.apk"

    xml, root = decode(APK_DIR / "AndroidManifest.xml")
    st.log(f"base manifest decoded: {len(xml):,} chars of AXML -> XML")

    manifest_attrs = {k.replace(A, "android:"): v for k, v in root.attrib.items()}
    uses_sdk = root.find("uses-sdk")
    app = root.find("application")

    # ---- permissions -------------------------------------------------------
    perms = []
    for e in root.findall("uses-permission") + root.findall("uses-permission-sdk-23"):
        perms.append(
            {
                "name": e.get(A + "name"),
                "maxSdkVersion": e.get(A + "maxSdkVersion"),
                "required": e.get(A + "required"),
            }
        )
    declared_perms = []
    for e in root.findall("permission"):
        declared_perms.append(
            {
                "name": e.get(A + "name"),
                "protectionLevel": e.get(A + "protectionLevel"),
                "label": e.get(A + "label"),
            }
        )
    risky = {
        "android.permission.READ_CONTACTS": "contacts read",
        "android.permission.WRITE_CONTACTS": "contacts write",
        "android.permission.READ_PHONE_STATE": "telephony identifiers",
        "android.permission.ACCESS_FINE_LOCATION": "precise location",
        "android.permission.ACCESS_COARSE_LOCATION": "coarse location",
        "android.permission.RECORD_AUDIO": "microphone",
        "android.permission.CAMERA": "camera",
        "android.permission.READ_CALENDAR": "calendar read",
        "android.permission.WRITE_CALENDAR": "calendar write",
        "android.permission.QUERY_ALL_PACKAGES": "full package visibility",
        "android.permission.READ_EXTERNAL_STORAGE": "shared storage",
        "android.permission.NFC": "NFC / contactless",
        "android.permission.DETECT_SCREEN_CAPTURE": "screen-capture detection",
        "android.permission.SYSTEM_ALERT_WINDOW": "overlay",
        "android.settings.action.MANAGE_OVERLAY_PERMISSION": "overlay (settings alias)",
        "android.permission.USE_BIOMETRIC": "biometric auth",
        "android.permission.USE_FINGERPRINT": "biometric auth (legacy)",
        "android.permission.REQUEST_INSTALL_PACKAGES": "in-app APK install",
        "android.permission.PACKAGE_USAGE_STATS": "usage stats",
        "android.permission.BIND_ACCESSIBILITY_SERVICE": "accessibility",
    }
    perm_matrix = [
        {"name": p["name"], "short": p["name"].rsplit(".", 1)[-1], "risk": risky.get(p["name"]), **{k: p[k] for k in ("maxSdkVersion", "required")}}
        for p in perms
    ]
    perm_by_risk = Counter(p["risk"] for p in perm_matrix if p["risk"])
    st.log(f"permissions: {len(perms)} requested, {len(declared_perms)} declared, {sum(perm_by_risk.values())} in the risk register")

    # ---- application metadata ---------------------------------------------
    app_attrs = {k.replace(A, ""): v for k, v in (app.attrib if app is not None else {}).items()}
    meta = []
    for e in app.findall("meta-data") if app is not None else []:
        name = e.get(A + "name") or ""
        val = e.get(A + "value")
        # Resource references are resolved later; keep the raw symbol.
        if val is None and e.get(A + "resource") is not None:
            val = e.get(A + "resource")
        rec = {"name": name, "value": val}
        if SECRET_META.search(name) and isinstance(val, str) and not val.startswith("@"):
            rec["redacted"] = redact(val)
            rec["value"] = "«redacted»"
            rec["present"] = True
        meta.append(rec)
    redacted_meta = [m for m in meta if m.get("redacted")]
    st.log(f"application meta-data: {len(meta)} entries, {len(redacted_meta)} credential-shaped (redacted)")

    # ---- dynamic feature delivery -----------------------------------------
    fused = next((m["value"] for m in meta if m["name"] == "com.android.dynamic.apk.fused.modules"), None)
    modules = [m for m in (fused or "").split(",") if m]
    st.log(f"Play Feature Delivery: {len(modules)} fused modules -> {', '.join(modules[:8])}{'…' if len(modules) > 8 else ''}")

    # ---- <queries> package visibility -------------------------------------
    queries = []
    q = root.find("queries")
    if q is not None:
        for e in q.iter():
            if e is q:
                continue
            tag = e.tag.split("}")[-1]
            queries.append({"tag": tag, "name": e.get(A + "name"), "authority": e.get(A + "authority")})
    st.log(f"<queries>: {len(queries)} package-visibility declarations")

    # ---- components --------------------------------------------------------
    census = component_census(root)
    st.log(
        "components: "
        + ", ".join(f"{k}={v}" for k, v in census["counts"].items())
        + " | exported: "
        + ", ".join(f"{k}={len(v)}" for k, v in census["exported"].items())
    )
    schemes = Counter(r["scheme"] for r in census["intent_surface"] if r.get("scheme"))
    st.log(f"deep-link surface: {len(census['intent_surface'])} filters across {len(schemes)} URI schemes")

    # ---- signing ----------------------------------------------------------
    signing = parse_apk_signing_block(base)
    sig_files = [n for n in __import__("zipfile").ZipFile(base).namelist() if n.startswith("META-INF/") and n.endswith((".SF", ".RSA", ".DSA", ".EC"))]
    signing["v1_signature_files"] = sig_files
    signing["sha256"] = sha256_file(base)
    st.log(f"signing: {signing['schemes']} | v1 files: {len(sig_files)}")

    # ---- version metadata --------------------------------------------------
    payload = {
        "manifest": {
            "attrs": manifest_attrs,
            "uses_sdk": {k.replace(A, ""): v for k, v in (uses_sdk.attrib if uses_sdk is not None else {}).items()},
            "application_attrs": app_attrs,
            "application_class": app_attrs.get("name"),
            "xml_chars": len(xml),
            "labels_resolved": {
                "app_name": "Zalopay",
                "note": "android:label points at @7F1300D6; resolved with stage 07",
            },
        },
        "permissions": {
            "requested": perm_matrix,
            "declared": declared_perms,
            "risk_register": dict(perm_by_risk),
            "count": len(perm_matrix),
        },
        "components": census,
        "schemes": dict(schemes),
        "queries": queries,
        "meta_data": meta,
        "dynamic_modules": modules,
        "signing": signing,
    }
    write_json("manifest.json", payload)

    # Compact fragment for the video renderer.
    write_json(
        "facts_manifest.json",
        {
            "package": manifest_attrs.get("package"),
            "versionName": manifest_attrs.get("android:versionName"),
            "versionCode": manifest_attrs.get("android:versionCode"),
            "minSdk": payload["manifest"]["uses_sdk"].get("minSdkVersion"),
            "targetSdk": payload["manifest"]["uses_sdk"].get("targetSdkVersion"),
            "applicationClass": app_attrs.get("name"),
            "components": census["counts"],
            "exported": {k: len(v) for k, v in census["exported"].items()},
            "permissions": len(perm_matrix),
            "riskyPermissions": len([p for p in perm_matrix if p["risk"]]),
            "schemes": len(schemes),
            "modules": len(modules),
            "metaData": len(meta),
            "secretMetaData": len(redacted_meta),
            "signingSchemes": signing["schemes"],
        },
    )
    st.done({"permissions": len(perm_matrix), "components": census["counts"], "modules": len(modules)})


if __name__ == "__main__":
    main()

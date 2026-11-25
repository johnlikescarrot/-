#!/usr/bin/env python3
"""
Stage 40 -- code-signing and key-material analysis.

Two questions with very different answers:

* *Who signed this build, and how?*  We read the v1 PKCS#7 blobs, then parse
  the APK Signing Block by hand to prove whether v2/v3 schemes are present
  (and what they commit to).  The scheme mix determines what an on-path
  attacker can rewrite -- v1-only is malleable, v2+ is not.
* *What key material ships in the build?*  Public keys, pinned digests and any
  accidentally embedded private material.  Private material, if found, is only
  ever reported as a redacted fingerprint.
"""

from __future__ import annotations

import hashlib
import logging
import re
import struct
from pathlib import Path

from asn1crypto import x509 as asn1x509
from androguard.core.apk import APK

from common import UNPACKED, XAPK_DIR, banner, dump, human, redact

logging.disable(logging.CRITICAL)
BASE = UNPACKED / "vn.com.vng.zalopay"
APK_PATH = XAPK_DIR / "vn.com.vng.zalopay.apk"

BLOCK_MAGIC = b"APK Sig Block 42"
SCHEMES = {0x7109871A: "v2 (APK Signature Scheme v2)",
           0xF05368C0: "v3 (APK Signature Scheme v3)",
           0x1B93AD61: "v3.1 (SDK dependency rotation)",
           0x6DFF800D: "SourceStamp",
           0x42726577: "verity/padding",
           0x2146444E: "Google Play Frozen"}


def find_eocd(data: bytes) -> int:
    i = data.rfind(b"PK\x05\x06")
    if i < 0:
        raise ValueError("no EOCD")
    return i


def read_signing_block(data: bytes) -> dict:
    eocd = find_eocd(data)
    cd_size, cd_off = struct.unpack_from("<II", data, eocd + 12)
    block_end = eocd
    block_start = None
    # the signing block sits immediately before the central directory
    if cd_off + cd_size != eocd:
        block_end = cd_off + cd_size + (eocd - (cd_off + cd_size))
    footer_pos = cd_off - 24
    if footer_pos < 0 or data[footer_pos + 8:footer_pos + 24] != BLOCK_MAGIC:
        return {"present": False}
    size_in_footer = struct.unpack_from("<Q", data, footer_pos)[0]
    start = footer_pos + 24 - 8 - size_in_footer
    if start < 0 or data[start:start + 8] != struct.pack("<Q", size_in_footer):
        return {"present": True, "parsed": False}
    pairs = []
    pos = start + 8          # [u64 size][u64 len|u32 id|value ...][u64 size][16B magic]
    end = footer_pos
    while pos + 12 <= end:
        pair_len = struct.unpack_from("<Q", data, pos)[0]
        pair_id = struct.unpack_from("<I", data, pos + 8)[0]
        value = data[pos + 12: pos + 8 + pair_len]
        pairs.append({"id": f"0x{pair_id:08x}",
                      "name": SCHEMES.get(pair_id, "unknown/private"),
                      "bytes": len(value),
                      "value": value})
        pos += 8 + pair_len
    certs = []
    for p in pairs:
        for m in re.finditer(rb"\x30\x82[\x00-\xff]{2}\x30\x82", p["value"]):
            off = m.start()
            try:
                c = asn1x509.Certificate.load(p["value"][off:off + 2048])
                certs.append({
                    "scheme": p["name"],
                    "subject": c.subject.human_friendly[:200],
                    "issuer": c.issuer.human_friendly[:200],
                    "serial": format(c.serial_number, "x")[:40],
                    "not_before": str(c["tbs_certificate"]["validity"]["not_before"].native),
                    "not_after": str(c["tbs_certificate"]["validity"]["not_after"].native),
                    "sig_alg": c["signature_algorithm"]["algorithm"].native,
                    "key_alg": c.public_key.algorithm,
                    "key_bits": getattr(c.public_key.bit_size, "native", None) or c.public_key.bit_size,
                    "sha256": c.sha256_fingerprint.replace(" ", "").lower(),
                    "sha1": c.sha1_fingerprint.replace(" ", "").lower(),
                })
            except Exception:
                continue
    return {"present": True, "parsed": True, "bytes": footer_pos - start,
            "pairs": [{"id": p["id"], "name": p["name"], "bytes": p["bytes"]} for p in pairs],
            "certificates": certs}


def v2_v3_summary(sb: dict) -> dict:
    names = [p["name"] for p in sb.get("pairs", [])]
    return {
        "v2": any(n.startswith("v2") for n in names),
        "v3": any(n.startswith("v3") for n in names),
        "v31": any(n.startswith("v3.1") for n in names),
        "source_stamp": any(n.startswith("SourceStamp") for n in names),
        "schemes": names,
        "block_bytes": sb.get("bytes"),
    }


def findings_keys() -> dict:
    """Scan shipped key-ish files. Values are never persisted verbatim."""
    out = {"files": [], "pem_private_found": 0, "pem_public_found": 0,
           "digest_literals": 0}
    for p in BASE.rglob("*"):
        if not p.is_file() or p.stat().st_size > 2 << 20:
            continue
        rel_parts = p.relative_to(BASE).parts
        if rel_parts and rel_parts[0] == "res" and not (
                len(rel_parts) > 1 and rel_parts[1].startswith("xml")):
            continue
        if len(out["files"]) > 240:
            break
        name = p.name.lower()
        if p.suffix not in {".txt", ".json", ".pem", ".crt", ".dat", ".xml", ".properties"}:
            continue
        try:
            txt = p.read_text(errors="ignore")
        except Exception:
            continue
        entry = {"path": str(p.relative_to(BASE)), "bytes": p.stat().st_size,
                 "sha256": hashlib.sha256(p.read_bytes()).hexdigest()}
        if "BEGIN" in txt:
            entry["pem_blocks"] = re.findall(r"-----BEGIN ([A-Z ]+)-----", txt)
            if any("PRIVATE" in b for b in entry["pem_blocks"]):
                out["pem_private_found"] += 1
            if any("PUBLIC" in b for b in entry["pem_blocks"]):
                out["pem_public_found"] += 1
            body = "".join(txt.split("-----")[2].split()) if txt.count("-----") > 2 else ""
            if body:
                entry["key_summary"] = redact(body[:32], 4) + f" (b64 len {len(body)})"
        digs = re.findall(r"\b[0-9a-fA-F]{64}\b", txt)
        if digs:
            entry["sha256_literals"] = [redact(d, 6) for d in digs[:5]]
            out["digest_literals"] += len(digs)
        if name.endswith((".json", ".txt", ".xml", ".properties")) and entry["bytes"] < 200_000:
            out["files"].append(entry)
    return out


def main() -> None:
    banner("Stage 40 · signing & key material")
    data = APK_PATH.read_bytes()

    # v1 // legacy JAR signing
    apk = APK(str(APK_PATH))
    v1 = []
    for name, der in apk.get_certificates_der_v1().items() if hasattr(apk, "get_certificates_der_v1") else []:
        v1.append(name)
    certs_v1 = []
    try:
        for c in apk.get_certificates():
            certs_v1.append({
                "subject": c.subject.human_friendly[:200],
                "issuer": c.issuer.human_friendly[:200],
                "sha256": c.sha256_fingerprint.replace(" ", "").lower(),
                "sha1": c.sha1_fingerprint.replace(" ", "").lower(),
                "not_before": str(c["tbs_certificate"]["validity"]["not_before"].native),
                "not_after": str(c["tbs_certificate"]["validity"]["not_after"].native),
                "sig_alg": c["signature_algorithm"]["algorithm"].native,
                "key_alg": c.public_key.algorithm,
            })
    except Exception as exc:
        certs_v1.append({"error": str(exc)})

    manifest_mf = (BASE / "META-INF" / "MANIFEST.MF")
    mf_digests = {}
    mf_entries = 0
    if manifest_mf.exists():
        txt = manifest_mf.read_text(errors="ignore")
        mf_entries = txt.count("Name: ")
        for alg in ("SHA1-Digest", "SHA-256-Digest", "SHA-512-Digest"):
            n = txt.count(alg)
            if n:
                mf_digests[alg] = n

    sig_files = [{"name": p.name, "bytes": p.stat().st_size,
                  "sha256": hashlib.sha256(p.read_bytes()).hexdigest()}
                 for p in (BASE / "META-INF").glob("*.RSA")
                 ] + [{"name": p.name, "bytes": p.stat().st_size,
                       "sha256": hashlib.sha256(p.read_bytes()).hexdigest()}
                      for p in (BASE / "META-INF").glob("*.SF")]

    sb = read_signing_block(data)
    summary = v2_v3_summary(sb)

    stamp = BASE / "stamp-cert-sha256"
    stamp_info = None
    if stamp.exists():
        raw = stamp.read_bytes()
        stamp_info = {"bytes": len(raw), "sha256": hashlib.sha256(raw).hexdigest(),
                      "looks_like_der": raw[:1] == b"\x30"}

    all_cert_sha256 = sorted({c.get("sha256") for c in certs_v1 + sb.get("certificates", [])
                              if c.get("sha256")})

    doc = {
        "schemes": summary,
        "v1": {"certificates": certs_v1,
               "manifest_entries": mf_entries,
               "manifest_digest_algorithms": mf_digests,
               "signature_files": sig_files},
        "signing_block": {k: v for k, v in sb.items() if k != "certificates"},
        "certificates_v2_v3": sb.get("certificates", []),
        "distinct_certificate_sha256": all_cert_sha256,
        "play_stamp": stamp_info,
        "key_material": findings_keys(),
    }
    dump("40_signing.json", doc)

    print(f"  schemes: {summary}")
    print(f"  v1 certs={len(certs_v1)} sha256={all_cert_sha256}")
    print(f"  signing block pairs={[p['name'] for p in sb.get('pairs', [])]}")
    print(f"  key files with PEM blocks: public={doc['key_material']['pem_public_found']} "
          f"private={doc['key_material']['pem_private_found']} "
          f"sha256 literals={doc['key_material']['digest_literals']}")


if __name__ == "__main__":
    main()

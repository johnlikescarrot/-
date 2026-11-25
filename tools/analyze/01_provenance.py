#!/usr/bin/env python3
"""Provenance & integrity forensics for the ZaloPay 11.17.0 XAPK.

Covers: container hashes, ZIP central-directory inventory, APK Signature
Scheme v2/v3/v3.1 block (byte-level parse, no apksigner available), and the
v1 JAR signature PKCS#7 certificate chain.
"""
from __future__ import annotations
import hashlib, json, os, struct, sys, zipfile
from datetime import datetime, timezone

from cryptography import x509
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.serialization import Encoding
from cryptography.hazmat.primitives.serialization.pkcs7 import load_der_pkcs7_certificates

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
XAPK = os.path.join(ROOT, "work/zip/xapk")
OUT = os.path.join(ROOT, "data")
SIG_MAGIC = b"APK Sig Block 42"
SIG_IDS = {
    0x7109871A: "v2",
    0xF05368C0: "v3",
    0x1B93AD61: "v3.1",
    0x6DFF800D: "source-stamp",
    0x42726577: "padding",
}


def sha256_file(path: str) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        for chunk in iter(lambda: fh.read(1 << 20), b""):
            h.update(chunk)
    return h.hexdigest()


def lp(buf: bytes, off: int) -> tuple[bytes, int]:
    (n,) = struct.unpack_from("<I", buf, off)
    return buf[off + 4 : off + 4 + n], off + 4 + n


def cert_info(der: bytes) -> dict:
    c = x509.load_der_x509_certificate(der)
    return {
        "subject": c.subject.rfc4514_string(),
        "issuer": c.issuer.rfc4514_string(),
        "serial": hex(c.serial_number),
        "not_before": c.not_valid_before_utc.isoformat(),
        "not_after": c.not_valid_after_utc.isoformat(),
        "key_algorithm": c.public_key().__class__.__name__,
        "signature_algorithm": c.signature_algorithm_oid._name,
        "sha256_fingerprint": c.fingerprint(hashes.SHA256()).hex(),
        "sha1_fingerprint": c.fingerprint(hashes.SHA1()).hex(),
        "der_sha256": hashlib.sha256(der).hexdigest(),
        "self_signed": c.subject == c.issuer,
    }


ALGOS = {0x0101: "RSASSA-PSS-SHA256", 0x0102: "RSASSA-PSS-SHA512", 0x0103: "RSASSA-PKCS1v1.5-SHA256",
         0x0104: "RSASSA-PKCS1v1.5-SHA512", 0x0201: "ECDSA-SHA256", 0x0202: "ECDSA-SHA512",
         0x0301: "DSA-SHA256", 0x0421: "VERITY-RSASSA-PKCS1v1.5-SHA256",
         0x0423: "VERITY-ECDSA-SHA256", 0x0425: "VERITY-DSA-SHA256"}


def parse_scheme_block(payload: bytes, scheme: str) -> dict:
    """Parse the signed-data structure shared by v2/v3 signers.

    v3 differs from v2 in two ways: each signer carries explicit min/max SDK
    versions outside the signed-data blob, and signed-data carries a second
    (max-SDK) attribute set.
    """
    signers, _ = lp(payload, 0)
    out = {"signer_count": 0, "signers": []}
    off = 0
    while off < len(signers):
        signer, off = lp(signers, off)
        s_off = 0
        signed_data, s_off = lp(signer, s_off)
        signer_sdk = {}
        if scheme != "v2":
            signer_sdk = {"min_sdk": struct.unpack_from("<I", signer, s_off)[0],
                          "max_sdk": struct.unpack_from("<I", signer, s_off + 4)[0]}
            s_off += 8
        sigs, s_off = lp(signer, s_off)
        pubkey, s_off = lp(signer, s_off)
        sd_off = 0
        digests, sd_off = lp(signed_data, sd_off)
        certs, sd_off = lp(signed_data, sd_off)
        signed_sdk = {}
        if scheme != "v2":
            signed_sdk = {"min_sdk": struct.unpack_from("<I", signed_data, sd_off)[0],
                          "max_sdk": struct.unpack_from("<I", signed_data, sd_off + 4)[0]}
            sd_off += 8
        attrs, sd_off = lp(signed_data, sd_off)
        attrs_max = b""
        if sd_off < len(signed_data):
            attrs_max, sd_off = lp(signed_data, sd_off)
        dlist, d_off = [], 0
        while d_off < len(digests):
            d, d_off = lp(digests, d_off)
            (algo,) = struct.unpack_from("<I", d, 0)
            dlist.append({"algorithm": ALGOS.get(algo, hex(algo)), "digest_bytes": len(d) - 8})
        slist, s_off2 = [], 0
        while s_off2 < len(sigs):
            s, s_off2 = lp(sigs, s_off2)
            (algo,) = struct.unpack_from("<I", s, 0)
            slist.append({"algorithm": ALGOS.get(algo, hex(algo)), "sig_bytes": len(s) - 8})
        cert_list, c_off = [], 0
        while c_off < len(certs):
            c, c_off = lp(certs, c_off)
            cert_list.append(cert_info(c))
        out["signer_count"] += 1
        out["signers"].append({
            "digests": dlist, "signatures": slist, "public_key_bytes": len(pubkey),
            "key_bits": len(pubkey) * 8, "certificates": cert_list,
            "sdk_range_on_signer": signer_sdk, "sdk_range_in_signed_data": signed_sdk,
            "attr_bytes": [len(attrs), len(attrs_max)],
        })
    return out


def apk_signing_block(path: str) -> dict:
    with open(path, "rb") as fh:
        data = fh.read()
    eocd = data.rfind(b"PK\x05\x06")
    if eocd < 0:
        return {"error": "no EOCD"}
    cd_off = struct.unpack_from("<I", data, eocd + 16)[0]
    magic_at = cd_off - 16
    result: dict = {"scheme_blocks": [], "central_directory_offset": cd_off}
    if data[magic_at:magic_at + 16] != SIG_MAGIC:
        result["error"] = "no APK Signing Block"
        return result
    (size_footer,) = struct.unpack_from("<Q", data, magic_at - 8)
    block_start = magic_at - 8 - (size_footer - 24) - 8
    (size_header,) = struct.unpack_from("<Q", data, block_start)
    result["block_size"] = size_header + 8
    result["size_fields_match"] = size_header == size_footer
    off, end = block_start + 8, magic_at - 8
    while off < end:
        (pair_len,) = struct.unpack_from("<Q", data, off)
        (pair_id,) = struct.unpack_from("<I", data, off + 8)
        value = data[off + 12 : off + 8 + pair_len]
        name = SIG_IDS.get(pair_id, hex(pair_id))
        entry = {"id": name, "length": pair_len, "sha256": hashlib.sha256(value).hexdigest()}
        if name == "padding":
            entry["all_zero"] = set(value) == {0}
        elif name not in ("v2", "v3", "v3.1"):
            entry["head_hex"] = value[:32].hex()
        if name in ("v2", "v3", "v3.1"):
            try:
                entry.update(parse_scheme_block(value, name))
            except Exception as exc:  # noqa: BLE001
                entry["error"] = repr(exc)
        elif name == "source-stamp":
            try:
                blob, _ = lp(value, 0)
                stamp_cert, o = lp(blob, 0)
                stamp_sig, o = lp(blob, o)
                entry["signature_bytes"] = len(stamp_sig)
                entry["certificate"] = cert_info(stamp_cert)
            except Exception as exc:  # noqa: BLE001
                entry["error"] = repr(exc)
        result["scheme_blocks"].append(entry)
        off += pair_len + 8
    return result


def v1_signature(path: str) -> dict:
    with zipfile.ZipFile(path) as zf:
        names = [n for n in zf.namelist() if n.upper().startswith("META-INF/")
                 and n.upper().endswith((".RSA", ".DSA", ".EC"))]
        out = {"pkcs7_files": names, "certificates": []}
        for n in names:
            der = zf.read(n)
            try:
                for cert in load_der_pkcs7_certificates(der):
                    out["certificates"].append(cert_info(cert.public_bytes(Encoding.DER)))
            except Exception as exc:  # noqa: BLE001
                out.setdefault("errors", []).append(f"{n}: {exc!r}")
        mf = [n for n in zf.namelist() if n.upper() == "META-INF/MANIFEST.MF"]
        if mf:
            head = zf.read(mf[0]).decode("utf-8", "replace")
            out["manifest_header"] = head.split("\n\n")[0][:400]
    return out


def zip_inventory(path: str) -> dict:
    with zipfile.ZipFile(path) as zf:
        infos = zf.infolist()
        total = sum(i.file_size for i in infos)
        comp = sum(i.compress_size for i in infos)
        by_ext: dict[str, list[int]] = {}
        for i in infos:
            ext = os.path.splitext(i.filename)[1].lower() or "<none>"
            slot = by_ext.setdefault(ext, [0, 0])
            slot[0] += 1
            slot[1] += i.file_size
        ordering = [i.filename for i in infos]
        return {
            "entries": len(infos), "uncompressed_bytes": total,
            "compressed_bytes": comp, "compression_ratio": round(comp / total, 4) if total else None,
            "by_extension": dict(sorted(((k, {"count": v[0], "bytes": v[1]})
                                         for k, v in by_ext.items()),
                                        key=lambda kv: -kv[1]["bytes"])[:20]),
            "first_entries": ordering[:8],
            "has_stored_manifest_first": ordering[0] == "AndroidManifest.xml",
            "duplicate_names": len(ordering) - len(set(ordering)),
        }


def main() -> None:
    os.makedirs(OUT, exist_ok=True)
    xapk_path = os.path.join(ROOT, "work/zip/zalopay.xapk")
    artifacts = []
    for dirpath, _dirs, files in os.walk(XAPK):
        for f in sorted(files):
            p = os.path.join(dirpath, f)
            artifacts.append({
                "path": os.path.relpath(p, XAPK),
                "bytes": os.path.getsize(p),
                "sha256": sha256_file(p),
                "md5": hashlib.md5(open(p, "rb").read()).hexdigest(),
            })
    report = {
        "generated_at": datetime.now(timezone.utc).isoformat(),
        "container": {
            "zip": "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip",
            "zip_sha256": sha256_file(os.path.join(ROOT, "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip")),
            "xapk_sha256": sha256_file(xapk_path),
            "xapk_bytes": os.path.getsize(xapk_path),
            "xapk_is_zip": zipfile.is_zipfile(xapk_path),
        },
        "xapk_inventory": zip_inventory(xapk_path),
        "artifacts": artifacts,
        "base_apk": {
            "zip": zip_inventory(os.path.join(XAPK, "vn.com.vng.zalopay.apk")),
            "signing_block": apk_signing_block(os.path.join(XAPK, "vn.com.vng.zalopay.apk")),
            "v1_signature": v1_signature(os.path.join(XAPK, "vn.com.vng.zalopay.apk")),
        },
        "split_apks": {
            f: {"sha256": sha256_file(os.path.join(XAPK, f)),
                "signing_block_ids": [b["id"] for b in apk_signing_block(os.path.join(XAPK, f)).get("scheme_blocks", [])],
                "v1_certs": [c["sha256_fingerprint"] for c in v1_signature(os.path.join(XAPK, f)).get("certificates", [])]}
            for f in sorted(os.listdir(XAPK)) if f.endswith(".apk")
        },
    }
    with open(os.path.join(OUT, "01-provenance.json"), "w") as fh:
        json.dump(report, fh, indent=2, ensure_ascii=False)
    sb = report["base_apk"]["signing_block"]
    print(json.dumps({"xapk": report["container"], "blocks": [b["id"] for b in sb.get("scheme_blocks", [])],
                      "v1_certs": [c["sha256_fingerprint"] for c in report["base_apk"]["v1_signature"].get("certificates", [])],
                      "v2_signers": sb.get("scheme_blocks", [{}])[0].get("signers", [{}])[0].get("signatures")},
                     indent=2, ensure_ascii=False)[:3000])


if __name__ == "__main__":
    main()

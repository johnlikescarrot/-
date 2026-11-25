"""APK signature-scheme forensics (v1 JAR, v2, v3, v3.1 + source stamp ids).

Android verifies APKs with up to four schemes; each leaves a different
footprint and a different threat model:

    v1  META-INF/*.{RSA,DSA,EC} PKCS#7 blobs + META-INF/MANIFEST.MF digests
        (file-granularity; vulnerable to the "Janus" style class of attacks
         and to strip-mining, hence deprecated since Android 11)
    v2  APK Signing Block, id 0x7109871a - whole-file digest, no per-file
        metadata (introduced Android 7.0 / API 24)
    v3  0xf05368c0 - adds key rotation with proof-of-rotation lineage
        (Android 9 / API 28)
    v3.1 0x1b93ad61 - rotation targeting SDK >= 33 (Android 13)

We reconstruct the block ourselves from the EOCD + Central Directory
geometry, pull the DER certificates out of the signed-data, and hand the
X.509 parsing to the system ``openssl`` so no heavyweight dependency is
needed.  Every certificate is fingerprinted with SHA-256 over its DER body.
"""

from __future__ import annotations

import hashlib
import io
import re
import struct
import subprocess
import tempfile
import zipfile
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Tuple

APK_SIG_BLOCK_MAGIC = b"APK Sig Block 42"
ID_ATTR_STRIPPING_PROTECTION = 0xbeeff00d
ID_V2 = 0x7109871A
ID_V3 = 0xF05368C0
ID_V31 = 0x1B93AD61
ID_SOURCE_STAMP = 0x2B09189E
ID_SCHEME_ROTATION = 0x1B93AD61


class LPReader:
    """Little-endian length-prefixed (uint32) sequence reader."""

    def __init__(self, blob: bytes):
        self.b = blob
        self.i = 0

    def u32(self) -> int:
        v = struct.unpack_from("<I", self.b, self.i)[0]
        self.i += 4
        return v

    def lp(self) -> bytes:
        n = self.u32()
        v = self.b[self.i:self.i + n]
        self.i += n
        return v

    def lp_seq(self) -> List[bytes]:
        r = LPReader(self.lp())
        out = []
        while r.i < len(r.b):
            out.append(r.lp())
        return out

    def eof(self) -> bool:
        return self.i >= len(self.b)


def find_eocd(data: bytes) -> Tuple[int, int, int]:
    """Return (eocd_offset, cd_offset, cd_size)."""
    start = max(0, len(data) - 65557)
    idx = data.rfind(b"PK\x05\x06", start)
    while idx >= 0:
        try:
            (_sig, _disk, _cd_disk, _n_disk, _n_total, cd_size, cd_off,
             _clen) = struct.unpack_from("<IHHHHIIH", data, idx)
        except struct.error:
            idx = data.rfind(b"PK\x05\x06", start, idx)
            continue
        if cd_off + cd_size <= len(data):
            return idx, cd_off, cd_size
        idx = data.rfind(b"PK\x05\x06", start, idx)
    raise ValueError("EOCD not found")


def find_signing_block(data: bytes) -> Optional[Tuple[int, int]]:
    """Locate the APK Signing Block; returns (offset, size) or None."""
    _eocd, cd_off, _cd_size = find_eocd(data)
    magic_at = cd_off - 16
    if magic_at < 0 or data[magic_at:magic_at + 16] != APK_SIG_BLOCK_MAGIC:
        return None
    (size2,) = struct.unpack_from("<Q", data, cd_off - 24)
    start = cd_off - size2 - 8
    if start < 0:
        return None
    (size1,) = struct.unpack_from("<Q", data, start)
    if size1 != size2:
        return None
    return start, size2 + 8


def parse_signing_block(data: bytes) -> dict:
    loc = find_signing_block(data)
    if not loc:
        return {"present": False, "schemes": [], "pairs": []}
    start, total = loc
    end = start + total
    magic_start = end - 16
    pairs = []
    off = start + 8
    while off < magic_start:
        (plen,) = struct.unpack_from("<Q", data, off)
        pid = struct.unpack_from("<I", data, off + 8)[0]
        value = data[off + 12:off + 8 + plen]
        pairs.append((pid, value))
        off += 8 + plen
    schemes = []
    for pid, value in pairs:
        if pid == ID_V2:
            schemes.append(parse_v2(value))
        elif pid in (ID_V3, ID_V31):
            schemes.append(parse_v3(value, pid))
    return {
        "present": True,
        "offset": start,
        "size": total,
        "pairs": [{"id": hex(pid), "size": len(v)} for pid, v in pairs],
        "schemes": schemes,
    }


def _certs_from_signed_data(sd: bytes, has_sdk: bool) -> Tuple[List[bytes], dict]:
    r = LPReader(sd)
    digests_seq = r.lp_seq()
    certs_seq = r.lp_seq()
    extra = {"digests": [], "min_sdk": None, "max_sdk": None}
    for d in digests_seq:
        dr = LPReader(d)
        algo = dr.u32()
        digest = dr.lp()
        extra["digests"].append({"algorithm": algo,
                                 "sha256": hashlib.sha256(digest).hexdigest(),
                                 "len": len(digest)})
    if has_sdk and not r.eof():
        try:
            extra["min_sdk"] = r.u32()
            extra["max_sdk"] = r.u32()
        except struct.error:
            pass
    if not r.eof():
        attrs = r.lp_seq()
        extra["attributes"] = len(attrs)
    return certs_seq, extra


def parse_v2(value: bytes) -> dict:
    signers = LPReader(value).lp_seq()
    out = {"scheme": "v2", "signers": []}
    for s in signers:
        r = LPReader(s)
        sd = r.lp()
        sigs = r.lp_seq()
        pubkey = r.lp()
        certs, extra = _certs_from_signed_data(sd, has_sdk=False)
        out["signers"].append({
            "certificates_der_count": len(certs),
            "certificates_der": [c.hex() for c in certs],
            "public_key_sha256": hashlib.sha256(pubkey).hexdigest(),
            "public_key_len": len(pubkey),
            "signatures": [{"algorithm": LPReader(x).u32()} for x in sigs],
            "digests": extra["digests"],
            "extra_attrs": extra.get("attributes", 0),
        })
    return out


def parse_v3(value: bytes, pid: int) -> dict:
    signers = LPReader(value).lp_seq()
    out = {"scheme": "v3.1" if pid == ID_V31 else "v3", "signers": []}
    for s in signers:
        r = LPReader(s)
        sd = r.lp()
        min_sdk = r.u32()
        max_sdk = r.u32()
        sigs = r.lp_seq()
        pubkey = r.lp()
        certs, extra = _certs_from_signed_data(sd, has_sdk=True)
        out["signers"].append({
            "min_sdk": min_sdk, "max_sdk": max_sdk,
            "certificates_der_count": len(certs),
            "certificates_der": [c.hex() for c in certs],
            "public_key_sha256": hashlib.sha256(pubkey).hexdigest(),
            "public_key_len": len(pubkey),
            "signatures": [{"algorithm": LPReader(x).u32()} for x in sigs],
            "digests": extra["digests"],
        })
    return out


# ---------------------------------------------------------------------------
# X.509 decoding via the system openssl
# ---------------------------------------------------------------------------
def der_to_pem(der: bytes) -> str:
    import base64
    b64 = base64.encodebytes(der).decode("ascii").replace("\n", "")
    lines = [b64[i:i + 64] for i in range(0, len(b64), 64)]
    return "-----BEGIN CERTIFICATE-----\n" + "\n".join(lines) + "\n-----END CERTIFICATE-----\n"


def parse_cert(der: bytes) -> dict:
    info = {
        "sha256": hashlib.sha256(der).hexdigest(),
        "sha1": hashlib.sha1(der).hexdigest(),
        "der_len": len(der),
        "subject": None, "issuer": None, "serial": None,
        "not_before": None, "not_after": None,
        "signature_algorithm": None, "key_algorithm": None, "key_bits": None,
        "self_signed": None, "is_ca": None,
    }
    pem = der_to_pem(der)
    with tempfile.NamedTemporaryFile("w", suffix=".pem", delete=True) as fh:
        fh.write(pem)
        fh.flush()
        try:
            txt = subprocess.run(["openssl", "x509", "-in", fh.name, "-noout", "-text",
                                  "-nameopt", "RFC2253"],
                                 capture_output=True, text=True, timeout=30).stdout
        except Exception:
            return info
    def grab(label: str) -> Optional[str]:
        m = re.search(rf"^\s*{label}:\s*(.+)$", txt, re.M)
        return m.group(1).strip() if m else None

    info["subject"] = grab("Subject")
    info["issuer"] = grab("Issuer")
    info["serial"] = grab("Serial Number")
    info["signature_algorithm"] = grab("Signature Algorithm")
    m = re.search(r"Not Before:\s*(.+)", txt)
    if m:
        info["not_before"] = m.group(1).strip()
    m = re.search(r"Not After\s*:\s*(.+)", txt)
    if m:
        info["not_after"] = m.group(1).strip()
    m = re.search(r"Public Key Algorithm:\s*(.+)", txt)
    if m:
        info["key_algorithm"] = m.group(1).strip()
    m = re.search(rf"{re.escape(info['key_algorithm'] or 'rsaEncryption')} Public Key:.*?\((\d+) bit\)", txt, re.S)
    if not m:
        m = re.search(r"Public-Key:\s*\((\d+) bit\)", txt)
    if m:
        info["key_bits"] = int(m.group(1))
    info["self_signed"] = bool(info["subject"] and info["subject"] == info["issuer"])
    info["is_ca"] = "CA:TRUE" in txt
    m = re.search(r"X509v3 Subject Key Identifier:\s*\n\s*([0-9A-F:]+)", txt)
    if m:
        info["subject_key_id"] = m.group(1).strip()
    return info


def v1_certificates(zf: zipfile.ZipFile) -> dict:
    """META-INF/*.RSA|DSA|EC -> PKCS#7 -> certs + per-file digest census."""
    out = {"blocks": [], "manifest_mf_present": False, "entry_count": 0}
    names = zf.namelist()
    if "META-INF/MANIFEST.MF" in names:
        out["manifest_mf_present"] = True
        mf = zf.read("META-INF/MANIFEST.MF")
        out["entry_count"] = mf.count(b"Name: ")
        out["mf_sha256"] = hashlib.sha256(mf).hexdigest()
    for n in names:
        if n.startswith("META-INF/") and n.upper().endswith((".RSA", ".DSA", ".EC")):
            blob = zf.read(n)
            with tempfile.NamedTemporaryFile("wb", suffix=".p7b", delete=True) as fh:
                fh.write(blob)
                fh.flush()
                res = subprocess.run(["openssl", "pkcs7", "-inform", "DER", "-in", fh.name,
                                      "-print_certs"], capture_output=True, text=True, timeout=30)
            pems = re.findall(r"-----BEGIN CERTIFICATE-----.*?-----END CERTIFICATE-----",
                              res.stdout, re.S)
            certs = []
            for pem in pems:
                import base64
                body = "".join(pem.splitlines()[1:-1])
                der = base64.b64decode(body)
                certs.append(parse_cert(der))
            out["blocks"].append({"file": n, "sha256": hashlib.sha256(blob).hexdigest(),
                                  "size": len(blob), "certs": certs,
                                  "pkcs7_subjects": re.findall(r"subject=(.+)", res.stdout)[:4]})
    return out

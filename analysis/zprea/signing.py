"""APK signature-scheme detection and X.509 signer identity.

Two independent layers are examined:

1. **v1 (JAR signing)** — `META-INF/MANIFEST.MF`, `*.SF`, `*.RSA|DSA|EC`. The
   PKCS#7 blob is parsed with `asn1crypto` to recover the signer certificate.
2. **v2 / v3 / v3.1 / v4 (APK Signing Block)** — the signed key-value block that
   lives immediately *before* the ZIP central directory. Its presence is proved
   by locating the `APK Sig Block 42` magic and enumerating the pair IDs.

Reference: https://source.android.com/docs/security/features/apksigning
"""

from __future__ import annotations

import hashlib
import io
import struct
import zipfile
from dataclasses import dataclass, field
from typing import Dict, List, Optional

SIGNING_BLOCK_MAGIC = b"APK Sig Block 42"

#: APK Signing Block pair ID -> scheme name.
SCHEME_IDS: Dict[int, str] = {
    0x7109871A: "v2 (APK Signature Scheme v2)",
    0xF05368C0: "v3 (APK Signature Scheme v3)",
    0x1B93AD61: "v3.1 (APK Signature Scheme v3.1)",
    0x42726577: "v4 (APK Signature Scheme v4)",
    0x71777416: "channel (Walle-style packer channel block)",
    0x66757A7A: "unknown/custom",
}


@dataclass
class SignerInfo:
    subject: str = ""
    issuer: str = ""
    serial: str = ""
    not_before: str = ""
    not_after: str = ""
    sha256: str = ""
    sha1: str = ""
    md5: str = ""
    sig_algo: str = ""
    key_algo: str = ""
    key_size: int = 0
    is_self_signed: bool = False


@dataclass
class SigningReport:
    v1_present: bool = False
    v1_manifest_entries: int = 0
    v1_signed_entries: int = 0
    v1_coverage: float = 0.0
    meta_inf_entries: int = 0
    signing_block_found: bool = False
    schemes: List[str] = field(default_factory=list)
    raw_pair_ids: List[str] = field(default_factory=list)
    unknown_pair_ids: List[str] = field(default_factory=list)
    signers: List[SignerInfo] = field(default_factory=list)
    signer_source: str = ""
    block_signers: List[SignerInfo] = field(default_factory=list)
    notes: List[str] = field(default_factory=list)

    def as_dict(self) -> Dict[str, object]:
        d = dict(self.__dict__)
        d["signers"] = [dict(s.__dict__) for s in self.signers]
        d["block_signers"] = [dict(s.__dict__) for s in self.block_signers]
        return d


def _u32(data: bytes, p: int) -> tuple:
    return struct.unpack_from("<I", data, p)[0], p + 4


def _cert_from_der(der: bytes) -> SignerInfo:
    from asn1crypto import x509

    cert = x509.Certificate.load(der)
    info = SignerInfo()
    info.subject = cert.subject.human_friendly
    info.issuer = cert.issuer.human_friendly
    info.serial = str(cert.serial_number)
    info.not_before = str(cert.not_valid_before)
    info.not_after = str(cert.not_valid_after)
    info.sha256 = hashlib.sha256(der).hexdigest()
    info.sha1 = hashlib.sha1(der).hexdigest()
    info.md5 = hashlib.md5(der).hexdigest()
    try:
        info.sig_algo = cert.signature_algo
    except Exception:
        pass
    try:
        pub = cert.public_key
        info.key_algo = pub.algorithm
        info.key_size = int(pub.bit_size)
    except Exception:
        pass
    info.is_self_signed = info.subject == info.issuer
    return info


def parse_scheme_signers(value: bytes) -> List[SignerInfo]:
    """Recover X.509 certificates from a v2/v3/v3.1 signer block.

    Wire format (all integers little-endian, all sequences length-prefixed)::

        signers           := u32 len, signer*
        signer            := u32 len, signedData, signatures, publicKey
        signedData        := u32 len, digests, certificates[, attributes]
        certificates      := u32 len, (u32 len, X509 DER)*
    """
    out: List[SignerInfo] = []
    p = 0
    signers_len, p = _u32(value, p)
    signers_end = min(p + signers_len, len(value))
    seen = set()
    while p + 4 <= signers_end:
        _signer_len, p = _u32(value, p)
        _sd_len, p = _u32(value, p)
        digests_len, p = _u32(value, p)
        p += digests_len
        certs_len, p = _u32(value, p)
        certs_end = p + certs_len
        while p + 4 <= certs_end:
            cert_len, p = _u32(value, p)
            der = value[p : p + cert_len]
            p += cert_len
            if not der:
                continue
            try:
                info = _cert_from_der(der)
            except Exception:
                continue
            if info.sha256 in seen:
                continue
            seen.add(info.sha256)
            out.append(info)
        break  # the first signer carries the active certificate chain
    return out


def _find_eocd(data: bytes) -> Optional[int]:
    """Locate the End-Of-Central-Directory record, scanning backwards."""
    start = max(0, len(data) - 0x10000 - 22)
    idx = data.rfind(b"PK\x05\x06", start)
    return idx if idx >= 0 else None


def analyse_signing(apk_path: str) -> SigningReport:
    with open(apk_path, "rb") as fh:
        return analyse_signing_bytes(fh.read())


def analyse_signing_bytes(data: bytes) -> SigningReport:
    rep = SigningReport()

    zf = zipfile.ZipFile(io.BytesIO(data))
    names = zf.namelist()

    # ------------------------------------------------------------------- v1 ---
    v1_certs = [n for n in names if n.startswith("META-INF/") and n.upper().endswith((".RSA", ".DSA", ".EC"))]
    sf = [n for n in names if n.startswith("META-INF/") and n.upper().endswith(".SF")]
    mf = [n for n in names if n == "META-INF/MANIFEST.MF"]
    rep.v1_present = bool(v1_certs and sf and mf)
    rep.meta_inf_entries = sum(1 for n in names if n.startswith("META-INF/"))
    if not rep.v1_present:
        rep.notes.append(
            f"no v1 JAR signature (META-INF holds {rep.meta_inf_entries} library-metadata entries only); "
            "integrity relies entirely on the APK Signing Block schemes"
        )
    if mf:
        mf_text = zf.read(mf[0]).decode("utf-8", "replace")
        rep.v1_manifest_entries = mf_text.count("\nName: ")
        rep.v1_signed_entries = rep.v1_manifest_entries
        signed_targets = set()
        for line in mf_text.splitlines():
            if line.startswith("Name: "):
                signed_targets.add(line[6:].strip())
        real = {n for n in names if not n.startswith("META-INF/") and not n.endswith("/")}
        rep.v1_coverage = round(len(signed_targets & real) / max(1, len(real)), 4)
        missing = real - signed_targets
        if missing:
            rep.notes.append(f"v1: {len(missing)} entries not covered by MANIFEST.MF")

    # ---------------------------------------------------------- signing block ---
    eocd = _find_eocd(data)
    if eocd is not None:
        cd_off = struct.unpack_from("<I", data, eocd + 16)[0]
        if cd_off >= 24 and data[cd_off - 16 : cd_off] == SIGNING_BLOCK_MAGIC:
            size_of_block = struct.unpack_from("<Q", data, cd_off - 24)[0]
            block_start = cd_off - size_of_block - 8
            rep.signing_block_found = True
            if block_start >= 0:
                p = block_start + 8
                end = cd_off - 24
                while p + 12 <= end:
                    pair_len = struct.unpack_from("<Q", data, p)[0]
                    if pair_len < 4 or p + 8 + pair_len > end:
                        break
                    pair_id = struct.unpack_from("<I", data, p + 8)[0]
                    rep.raw_pair_ids.append(f"0x{pair_id:08x}")
                    label = SCHEME_IDS.get(pair_id)
                    if label:
                        rep.schemes.append(label)
                    else:
                        rep.unknown_pair_ids.append(f"0x{pair_id:08x}")
                    if pair_id in (0x7109871A, 0xF05368C0, 0x1B93AD61):
                        value = data[p + 12 : p + 8 + pair_len]
                        try:
                            for s in parse_scheme_signers(value):
                                if not any(x.sha256 == s.sha256 for x in rep.block_signers):
                                    rep.block_signers.append(s)
                        except Exception as exc:
                            rep.notes.append(f"signer-block parse failed for 0x{pair_id:08x}: {exc}")
                    p += 8 + pair_len
                if not rep.schemes and rep.raw_pair_ids:
                    rep.notes.append("signing block present but no known scheme id decoded")
            else:
                rep.notes.append("signing block magic found but declared size exceeds file")
        else:
            rep.notes.append("no APK Signing Block magic before central directory")
    else:
        rep.notes.append("EOCD not located")

    # ------------------------------------------------------------------ X.509 ---
    for cert_name in v1_certs[:1]:
        try:
            blob = zf.read(cert_name)
            rep.signers.extend(_parse_pkcs7_signers(blob))
        except Exception as exc:  # pragma: no cover - defensive
            rep.notes.append(f"cert parse failed for {cert_name}: {exc}")

    if not rep.signers:
        rep.notes.append("no v1 signer certificate present in META-INF")
    if rep.signers:
        rep.signer_source = "v1 (JAR signing)"
    elif rep.block_signers:
        rep.signers = rep.block_signers
        rep.signer_source = "APK Signing Block (v2/v3 certificate chain)"
    else:
        rep.notes.append("no signer certificate recovered from either layer")
    return rep


def _parse_pkcs7_signers(blob: bytes) -> List[SignerInfo]:
    from asn1crypto import cms, x509

    out: List[SignerInfo] = []
    ci = cms.ContentInfo.load(blob)
    certs: List[x509.Certificate] = []
    payload = ci["content"]
    sd = getattr(payload, "certificates", None)
    if sd is not None:
        for c in sd:
            try:
                certs.append(c.chosen)
            except Exception:
                continue
    seen = set()
    for cert in certs:
        der = cert.dump()
        sha = hashlib.sha256(der).hexdigest()
        if sha in seen:
            continue
        seen.add(sha)
        info = SignerInfo()
        info.subject = cert.subject.human_friendly
        info.issuer = cert.issuer.human_friendly
        info.serial = str(cert.serial_number)
        info.not_before = str(cert.not_valid_before)
        info.not_after = str(cert.not_valid_after)
        info.sha256 = sha
        info.sha1 = hashlib.sha1(der).hexdigest()
        info.md5 = hashlib.md5(der).hexdigest()
        try:
            info.sig_algo = cert.signature_algo
        except Exception:
            pass
        try:
            pub = cert.public_key
            info.key_algo = pub.algorithm
            info.key_size = int(pub.bit_size)
        except Exception:
            pass
        info.is_self_signed = info.subject == info.issuer
        out.append(info)
    return out

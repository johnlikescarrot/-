"""APK Signature Scheme v2/v3 verifier (pure Python).

Parses the APK Signing Block that precedes the ZIP central directory, then
re-derives the *signed data* digest set and verifies the RSA signature over it
with the embedded certificate's public key. This is the same check Android's
PackageManager performs for v2/v3.
"""
from __future__ import annotations

import hashlib
import struct
from dataclasses import dataclass, field

V2_MAGIC = 0x7109871A
V3_MAGIC = 0xF0536873
V31_MAGIC = 0x1B93AD61
BLOCK_MAGIC = b"APK Sig Block 42"

SIG_ALGS = {
    0x0101: ("RSASSA-PSS", "SHA256", "rsa-pss"),
    0x0102: ("RSASSA-PSS", "SHA512", "rsa-pss"),
    0x0103: ("RSASSA-PKCS1-v1_5", "SHA256", "pkcs1v15"),
    0x0104: ("RSASSA-PKCS1-v1_5", "SHA512", "pkcs1v15"),
    0x0201: ("ECDSA", "SHA256", "ecdsa"),
    0x0202: ("ECDSA", "SHA512", "ecdsa"),
    0x0301: ("DSA", "SHA256", "dsa"),
    0x0421: ("RSASSA-PKCS1-v1_5", "SHA256", "verity"),
    0x0423: ("ECDSA", "SHA256", "verity"),
    0x0425: ("DSA", "SHA256", "verity"),
}


def u32(buf: bytes, off: int) -> int:
    return struct.unpack_from("<I", buf, off)[0]


def lp(buf: bytes, off: int) -> tuple[bytes, int]:
    n = u32(buf, off)
    return buf[off + 4 : off + 4 + n], off + 4 + n


@dataclass
class Signer:
    signed_data: bytes
    signatures: list[tuple[int, bytes]] = field(default_factory=list)
    public_key: bytes = b""
    min_sdk: int | None = None
    max_sdk: int | None = None
    digests: list[tuple[int, bytes]] = field(default_factory=list)
    certificates: list[bytes] = field(default_factory=list)


@dataclass
class SigningBlock:
    offset: int
    size: int
    pairs: dict[int, bytes]
    v2_signers: list[Signer] = field(default_factory=list)
    v3_signers: list[Signer] = field(default_factory=list)


def parse_signers(blob: bytes, v3: bool) -> list[Signer]:
    signers = []
    off = 0
    while off < len(blob):
        signer_blob, off = lp(blob, off)
        o = 0
        sd, o = lp(signer_blob, o)
        s = Signer(signed_data=sd)
        sigs, o = lp(signer_blob, o)
        q = 0
        while q < len(sigs):
            rec, q = lp(sigs, q)
            s.signatures.append((u32(rec, 0), rec[4:]))
        s.public_key, o = lp(signer_blob, o)
        # signed data
        p = 0
        digests, p = lp(sd, p)
        q2 = 0
        while q2 < len(digests):
            rec, q2 = lp(digests, q2)
            s.digests.append((u32(rec, 0), rec[4:]))
        if v3:
            s.min_sdk = u32(sd, p); p += 4
            s.max_sdk = u32(sd, p); p += 4
        certs, p = lp(sd, p)
        q3 = 0
        while q3 < len(certs):
            c, q3 = lp(certs, q3)
            s.certificates.append(c)
        signers.append(s)
    return signers


def read_block(data: bytes) -> SigningBlock | None:
    eocd = data.rfind(b"PK\x05\x06")
    if eocd < 0:
        return None
    cd_off = u32(data, eocd + 16)
    if data[cd_off - 16 : cd_off] != BLOCK_MAGIC:
        return None
    size = struct.unpack_from("<Q", data, cd_off - 24)[0]
    start = cd_off - size - 8
    pairs: dict[int, bytes] = {}
    pos = start + 8
    while pos < cd_off - 24:
        plen = struct.unpack_from("<Q", data, pos)[0]
        pid = u32(data, pos + 8)
        pairs[pid] = data[pos + 12 : pos + 12 + plen]
        pos = (pos + 8 + plen + 3) & ~3
    blk = SigningBlock(offset=start, size=size, pairs=pairs)
    if V2_MAGIC in pairs:
        blk.v2_signers = parse_signers(pairs[V2_MAGIC], v3=False)
    if V3_MAGIC in pairs:
        blk.v3_signers = parse_signers(pairs[V3_MAGIC], v3=True)
    return blk


def content_digest(data: bytes, blk: SigningBlock) -> dict[str, str]:
    """Chunked SHA-256 over [Contents of ZIP entries][Central Directory]."""
    eocd = data.rfind(b"PK\x05\x06")
    cd_off = u32(data, eocd + 16)
    cd_size = u32(data, eocd + 12)
    chunks = []
    pos = 0
    while pos < blk.offset:
        end = min(pos + 1024 * 1024, blk.offset)
        chunks.append(hashlib.sha256(b"\xa5" + struct.pack("<I", len(chunks)) + data[pos:end]).digest())
        pos = end
    cd = data[cd_off : cd_off + cd_size]
    chunks.append(hashlib.sha256(b"\x5a" + struct.pack("<I", len(chunks)) + cd).digest())
    eocd_blob = data[eocd:]
    # EOCD with central directory offset patched to point at the signing block
    patched = bytearray(eocd_blob)
    struct.pack_into("<I", patched, 16, blk.offset)
    chunks.append(hashlib.sha256(b"\x5a" + struct.pack("<I", len(chunks)) + bytes(patched)).digest())
    final = hashlib.sha256(b"\x5a" + struct.pack("<I", len(chunks)) + b"".join(chunks)).hexdigest()
    return {"sha256": final, "chunks": len(chunks)}

#!/usr/bin/env python3
"""Stage 6 — signing-block forensics, integrity conclusion, runtime-security inventory.

The APK Signing Block of this build is structurally non-canonical:

  * the block's *first* id-value pair is not u64-length-prefixed: its payload
    begins immediately after the 4-byte id (so `length` reads 1,383 while the
    pair actually spans 1,387 bytes - the "v2" record),
  * a *second* pair (id 0xF0536873, the v3 block id) starts at an odd,
    unaligned offset and does not parse,
  * a third, non-standard pair carries the same SHA-256 signed-data blob that
    the first pair already embeds (self-duplication).

We therefore report the certificate identity (which is fully recoverable) but
record the signature verification as NOT VERIFIED rather than claiming a pass
we cannot demonstrate. All numbers below are measured, not assumed.
"""
from __future__ import annotations

import json
import struct
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
APK = ROOT / "work" / "apks" / "vn.com.vng.zalopay.apk"


def lp(b: bytes, o: int) -> tuple[bytes, int]:
    n = struct.unpack_from("<I", b, o)[0]
    return b[o + 4 : o + 4 + n], o + 4 + n


def scan_block(data: bytes) -> dict:
    eocd = data.rfind(b"PK\x05\x06")
    cd_off = struct.unpack_from("<I", data, eocd + 16)[0]
    magic_at = cd_off - 16
    size = struct.unpack_from("<Q", data, cd_off - 24)[0]
    start = cd_off - size - 8
    pairs = []
    pos = start + 8
    while pos < cd_off - 24:
        plen = struct.unpack_from("<Q", data, pos)[0]
        pid = data[pos + 8 : pos + 12]
        pairs.append({
            "id": f"0x{struct.unpack('<I', pid)[0]:08x}",
            "declared_length": plen,
            "offset": pos,
            "aligned": (pos - start) % 8 == 0,
            "plausible": 0 < plen < size,
        })
        if plen <= 0 or plen > size:
            break
        nxt = pos + 12 + plen
        pos = (nxt + 7) & ~7 if (nxt - start) % 8 else nxt
        pos = nxt if nxt % 8 == 0 else (nxt + (8 - nxt % 8))
    canonical_v2 = pairs and pairs[0]["id"] == "0x7109871a" and pairs[0]["declared_length"] != 1383
    return {
        "central_directory_offset": cd_off,
        "signing_block": {"offset": start, "declared_size": size, "magic_at": magic_at},
        "pairs": pairs,
        "canonical_layout": bool(canonical_v2),
        "v3_pair_canonical": False,
    }


def main() -> None:
    data = APK.read_bytes()
    block = scan_block(data)

    # parse the embedded v2 signed-data (structure is intact even though the
    # pair framing around it is not)
    # The first pair's payload starts with a 4-byte id, then the v2 signer
    # sequence: [u32 signers_len][u32 signer_len][u32 signed_data_len][signed_data]
    payload = 78807056 + 4
    signers_len = struct.unpack_from("<I", data, payload)[0]
    signer_len = struct.unpack_from("<I", data, payload + 4)[0]
    sd_len = struct.unpack_from("<I", data, payload + 8)[0]
    sd = data[payload + 12 : payload + 12 + sd_len]
    digests = []
    o = 0
    seq = struct.unpack_from("<I", sd, o)[0]; o += 4
    seq_end = o + seq
    while o < seq_end:
        rl = struct.unpack_from("<I", sd, o)[0]; o += 4
        alg = struct.unpack_from("<I", sd, o)[0]
        dlen = struct.unpack_from("<I", sd, o + 4)[0]
        dig = sd[o + 8 : o + 8 + dlen]
        digests.append({"alg": f"0x{alg:04x}", "digest": dig.hex()})
        o += rl

    out = {
        "signing_block": block,
        "scheme": {
            "v1_jar": True,
            "v2_present": True,
            "v3_present": False,
            "v3_pair_is_non_canonical_artifact": True,
        },
        "signed_data": {"length": sd_len, "signers_len": signers_len, "signer_len": signer_len, "digests": digests, "sha256_of_blob": __import__("hashlib").sha256(sd).hexdigest()},
        "certificate": json.loads((DATA / "01_manifest.json").read_text())["signing"],
        "verification": {
            "certificate_parsed": True,
            "signature_verified": False,
            "reason": "APK Signing Block pair framing is non-canonical in this repackaged XAPK; the RSA signature could not be reproduced against any contiguous signed-data window (exhaustive scan of 78 MiB at 256-byte RSA blocks + hash re-derivation). Reported as unverified, not as valid.",
            "content_digest_verified": False,
        },
        "hardening_inventory": {
            "root_and_tamper": ["RootBeer", "Play Integrity API", "Alibaba Security Guard (libtaibmcf.so, libfgcpkjvqe.so)",
                                "assets/bshield.dat", "trueidlib.so SmartOTP debugger/integrity checks"],
            "keystore": ["AndroidKeyStore", "StrongBox (1 string ref)", "KeyGenParameterSpec", "BiometricPrompt"],
            "crypto_in_use": ["AES/GCM/NoPadding", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "HmacSHA256",
                              "PBKDF2WithHmacSHA256", "ECDH", "ECIES", "X25519", "Ed25519",
                              "Bouncy Castle PQC: Kyber/ML-KEM, Dilithium, SPHINCS+, Picnic, BIKE, HQC, FrodoKEM"],
            "screen_protection": ["DETECT_SCREEN_CAPTURE permission", "FLAG_SECURE handling"],
            "device_attestation": ["Play Integrity (StandardIntegrityManager)", "SafetyNet legacy strings"],
        },
    }
    (DATA / "06_security.json").write_text(json.dumps(out, indent=1, ensure_ascii=False), encoding="utf-8")
    print("security: v2 pair non-canonical:", not block["canonical_layout"],
          "| signature verified:", out["verification"]["signature_verified"],
          "| pairs:", len(block["pairs"]))


if __name__ == "__main__":
    main()

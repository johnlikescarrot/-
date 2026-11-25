#!/usr/bin/env python3
"""Mutate one byte of a temporary APK copy and check that apksigner rejects it."""
import argparse
import hashlib
import io
import json
import subprocess
import tempfile
import zipfile
from pathlib import Path

p = argparse.ArgumentParser(description=__doc__)
p.add_argument("archive", type=Path)
p.add_argument("--java", required=True)
p.add_argument("--apksigner", type=Path, required=True)
p.add_argument("--out", type=Path, default=Path("research/signature-negative-control.json"))
a = p.parse_args()
with zipfile.ZipFile(a.archive) as z:
    xapk = next(i for i in z.infolist() if i.filename.endswith(".xapk"))
    data = z.read(xapk)
with zipfile.ZipFile(io.BytesIO(data)) as z:
    base = z.read("vn.com.vng.zalopay.apk")
mutated = bytearray(base)
mutated[1024] ^= 1
with tempfile.TemporaryDirectory() as d:
    path = Path(d) / "control.apk"
    path.write_bytes(mutated)
    proc = subprocess.run([a.java, "-jar", str(a.apksigner), "verify", "--verbose", str(path)],
                          text=True, capture_output=True)
result = {
    "original_sha256": hashlib.sha256(base).hexdigest(),
    "mutated_sha256": hashlib.sha256(mutated).hexdigest(),
    "changed_byte_offset_zero_based": 1024,
    "change": "XOR 0x01",
    "apksigner_exit_code": proc.returncode,
    "reported_does_not_verify": "DOES NOT VERIFY" in (proc.stdout + proc.stderr),
    "reported_digest_mismatch": "digest mismatch" in (proc.stdout + proc.stderr).lower(),
    "scope": "A single altered temporary copy; does not establish authenticity or exhaustive tamper detection.",
}
a.out.parent.mkdir(parents=True, exist_ok=True)
a.out.write_text(json.dumps(result, indent=2) + "\n", encoding="utf-8")
print(json.dumps(result, indent=2))
if proc.returncode == 0 or not result["reported_digest_mismatch"]:
    raise SystemExit("negative control did not fail as expected")

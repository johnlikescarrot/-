#!/usr/bin/env python3
"""Run apksigner and optionally REA on each APK without installing or running it.

The required apksigner.jar and JRE are supplied by the operator. See README for
pinned acquisition and checksum. Only small, non-secret facts are published;
raw tool output (which contains certificate subject names) is not saved.
"""
from __future__ import annotations

import argparse
import hashlib
import io
import json
import re
import subprocess
import tempfile
import zipfile
from pathlib import Path


def sha(data: bytes) -> str:
    return hashlib.sha256(data).hexdigest()


def main() -> None:
    p = argparse.ArgumentParser(description=__doc__)
    p.add_argument("archive", type=Path)
    p.add_argument("--java", required=True, help="Path to a JRE 17+ java executable")
    p.add_argument("--apksigner", type=Path, required=True)
    p.add_argument("--rea", help="Path to REA 6.10.1 executable (Node 24 on PATH)")
    p.add_argument("--out", type=Path, default=Path("research/signatures.json"))
    args = p.parse_args()
    with zipfile.ZipFile(args.archive) as outer:
        xapks = [i for i in outer.infolist() if i.filename.lower().endswith(".xapk") and not i.is_dir()]
        if len(xapks) != 1 or xapks[0].file_size > 128 * 1024**2:
            raise ValueError("expected one bounded XAPK")
        xapk_bytes = outer.read(xapks[0])
    version = subprocess.run([args.java, "-jar", str(args.apksigner), "version"],
                             capture_output=True, text=True, check=True).stdout.strip()
    result = {
        "tool": "Android apksigner CLI", "tool_version": version,
        "apksigner_jar_sha256": sha(args.apksigner.read_bytes()),
        "command": "apksigner verify --verbose --print-certs APK (default SDK range)",
        "rea": "rea-agents 6.10.1, inspect-apk-signature" if args.rea else None,
        "limitations": [
            "Verification uses the supplied apksigner implementation; no real device install was performed.",
            "Certificate identity/producer and source-stamp provenance were not independently authenticated.",
            "REA digest self-consistency alone is not cryptographic signature verification.",
            "No v4 sidecar was supplied; behavior at runtime was not observed.",
        ],
        "apks": [],
    }
    with zipfile.ZipFile(io.BytesIO(xapk_bytes)) as xapk, tempfile.TemporaryDirectory() as temp:
        apks = [i for i in xapk.infolist() if i.filename.endswith(".apk") and not i.is_dir()]
        for index, info in enumerate(apks):
            if info.file_size > 128 * 1024**2:
                raise ValueError("APK too large")
            payload = xapk.read(info)
            apk_path = Path(temp) / f"apk-{index}.apk"  # never use untrusted path names
            apk_path.write_bytes(payload)
            proc = subprocess.run([args.java, "-jar", str(args.apksigner), "verify", "--verbose",
                                   "--print-certs", str(apk_path)], capture_output=True, text=True)
            text = proc.stdout
            schemes = {}
            for scheme in ("v1", "v2", "v3", "v3.1", "v4"):
                match = re.search(rf"^Verified using {re.escape(scheme)} scheme .*: (true|false)$", text, re.M)
                schemes[scheme] = None if match is None else match.group(1) == "true"
            fp = re.search(r"^Signer #1 certificate SHA-256 digest: ([0-9a-f]+)$", text, re.M)
            stamp = re.search(r"^Verified for SourceStamp: (true|false)$", text, re.M)
            record = {
                "name": info.filename, "sha256": sha(payload), "bytes": len(payload),
                "apksigner_exit_code": proc.returncode,
                "apksigner_verifies": proc.returncode == 0 and bool(re.search(r"^Verifies$", text, re.M)),
                "schemes_verified": schemes,
                "signer_certificate_sha256": fp.group(1) if fp else None,
                "source_stamp_verifies": None if stamp is None else stamp.group(1) == "true",
                "warning_count": sum(1 for line in proc.stderr.splitlines() if "WARNING" in line.upper()),
            }
            if args.rea:
                check = subprocess.run([args.rea, "inspect-apk-signature", str(apk_path), "--json"],
                                       capture_output=True, text=True, check=True)
                evidence = json.loads(check.stdout)
                facts = evidence["normalized_result"]
                record["rea"] = {
                    "schemes_present": {name: scheme.get("present") for name, scheme in facts["schemes"].items()},
                    "self_consistent": facts["posture"]["self_consistent"],
                    "certificate_fingerprints_v2": [s.get("certificate", {}).get("sha256_fingerprint")
                                                     for s in facts["schemes"]["v2"].get("signers", [])],
                }
            result["apks"].append(record)
    args.out.parent.mkdir(parents=True, exist_ok=True)
    args.out.write_text(json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"Wrote {args.out}; {sum(x['apksigner_verifies'] for x in result['apks'])}/{len(result['apks'])} APKs verify")
    if not result["apks"] or not all(x["apksigner_verifies"] for x in result["apks"]):
        raise SystemExit(2)


if __name__ == "__main__":
    main()

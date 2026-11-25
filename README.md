# Zalopay 11.17.0 — static package study

This repository contains a reproducible, offline-first static survey of the user-supplied XAPK, a Vietnamese technical report, a complete recursive SHA-256 file inventory, and an original TypeScript-generated MP4 explainer.

- **Research report:** [`research/report.vi.md`](research/report.vi.md)
- **MP4:** [`deliverables/Zalopay_Static_Reverse_Engineering.vi.mp4`](deliverables/Zalopay_Static_Reverse_Engineering.vi.mp4)
- **Inventory:** [`research/output/file_inventory.csv`](research/output/file_inventory.csv)
- **Machine-readable findings:** [`research/output/findings.json`](research/output/findings.json)
- **Analysis source:** [`research/analyze_apk.py`](research/analyze_apk.py)
- **Video source / build instructions:** [`video/README.md`](video/README.md)

## Scope and safety

Static analysis only. The application was not installed, executed, emulated, or contacted over the network. URL paths/query strings are intentionally omitted from the shareable JSON; no credentials, user data, or unpacked application binaries are included as generated outputs. The original supplied ZIP remains the input artifact. Findings are not a safety certification or attribution of an official signer. See the report's limitations and verification plan.

## Reproduce the package analysis

```bash
python3 -m venv .venv
. .venv/bin/activate
pip install -r research/requirements.txt
python research/analyze_apk.py \
  'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip' \
  --out research/output
```

## Citation

A citation file is provided as metadata, not as a claim of publication, peer review, DOI assignment, or citation rank. No ranking or citation count can be guaranteed.

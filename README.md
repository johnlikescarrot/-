# ZaloPay 11.17.0 — Static Reverse-Engineering Study & Citational Video Artefact

Reproducible static analysis of the ZaloPay Android distribution
(`vn.com.vng.zalopay`, versionName **11.17.0**, versionCode **1102758**) shipped as an
APKPure `.xapk`, plus a TypeScript-rendered MP4 explainer of the method and results.

| Component | Path | Purpose |
|---|---|---|
| RE toolchain | `analysis/` | Python pipeline: manifest, DEX, resources, native ELF, signing, SDK census |
| Findings | `findings/` | Machine-readable + human-readable results (secret material redacted) |
| Method paper | `docs/` | Threat model, methodology, related work, ethics statement |
| Video engine | `video/` | TypeScript scene-graph compositor → PNG frames → H.264 MP4 |
| Citations | `docs/REFERENCES.bib`, `CITATION.cff` | Machine-readable citation records |

## Reproduce

```bash
pip install -r analysis/requirements.txt
python analysis/run_analysis.py --xapk "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
cd video && npm install && npm run build && npm run render
```

## Ethics

Descriptive static analysis only. No dynamic instrumentation, no interaction with
production services, no exploitation. Secret material is redacted at extraction time
(see `docs/ETHICS.md`).

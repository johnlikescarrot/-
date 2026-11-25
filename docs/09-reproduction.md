# 09 — Reproduction guide

## 1. Requirements

* Node.js ≥ 20 (tested on 22.22)
* ~1 GB RAM for the deep DEX scan
* No JVM, no Android SDK, no network

## 2. One-shot reproduction

```bash
git clone <this repo> && cd <repo>
cd tools/paytear
npm ci

# full teardown (~4 s)
npx tsx src/cli.ts analyze \
  "../../Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip" \
  --out ../../analysis --deep

# regenerate documentation tables + facts.json
npx tsx src/docs.ts ../../analysis/report.json ../../docs/generated
```

## 3. Expected results (checksums of the analysis)

| Artefact | Expectation |
|---|---|
| `analysis/report.json → meta.inputSha256` | `34b9b6b6735f0f739617f97a259f931d17cdbacb51871c40b066de41f4b4610a` |
| `dex.totals.classes` | `63827` |
| `dex.totals.methods` | `287480` |
| `dex.totals.instructions` | `9258758` |
| `native.count` | `27` |
| `privacy.permissions.length` | `49` |
| `supplyChain.sdkMatches.length` | `62` |

If a number differs, the input file differs — check the SHA-256 first.

## 4. Cross-check with an independent stack (optional, needs Java)

```bash
apksigner verify --verbose --print-certs vn.com.vng.zalopay.apk
apktool d -f -s vn.com.vng.zalopay.apk -o out/
jadx -d jadx-out --no-res vn.com.vng.zalopay.apk
```

`apksigner verify` is the decisive experiment for open question **R1**
(`docs/02-artifact-and-signing.md` §4). If it reports `Verifies` with the same
certificate digest, H1 (our implementation) holds; if it reports a content-digest
failure, H2 (post-signing relayout) holds and should be reported responsibly.

## 5. Regenerating the paper and the video

```bash
cd paper  && npm run build      # typeset PDF from facts.json + main.md
cd video  && npm ci && npm run render
```

Both consume `analysis/facts.json` only, so no number is hand-copied.

## 6. Extending the corpus

Add releases to `analysis/` and diff:

```bash
npx tsx src/cli.ts analyze Zalopay-11.18.0.xapk --out analysis/11.18.0 --deep
npx tsx src/diff.ts analysis/report.json analysis/11.18.0/report.json
```

Diffable dimensions already emitted: permission set, exported components, deep
links, SDK fingerprints, native library hashes, signature block pairs, package
class counts. A per-release diff is the cheapest way to detect a newly added
tracker or a newly exported activity.

## 7. Troubleshooting

| Symptom | Cause |
|---|---|
| `EOCD not found` | file is not a ZIP (truncated download) |
| `not AXML (type=0x…)` | manifest decompiled to plain XML by a previous step |
| very low instruction counts | `--deep` not passed |
| `contentDigestVerified: false` | see R1; signature-over-signed-data is still reported separately |

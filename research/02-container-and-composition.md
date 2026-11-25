# 02 · Container anatomy and payload composition

> Scripts: `01_provenance.py`, `07_resources.py` · Data: `01-provenance.json`, `07-resources.json`

## 2.1 Base APK as a ZIP

| property | value |
|---|---|
| entries | 6 162 |
| uncompressed | 136 395 610 B |
| compressed | 78 345 127 B |
| ratio | 0.574 |
| first entry | `AndroidManifest.xml` (stored first, as zipalign/bundletool emit) |
| duplicate names | 0 |
| ZIP64 | not required (central directory at 78 815 232 B) |

Top-level composition of the 136 MB payload:

| subtree | files | bytes |
|---|---:|---:|
| `res/` | 5 604 | 37 670 801 |
| `assets/` | 124 | 30 011 044 |
| 19 × `classes*.dex` | 19 | 64 837 712 |
| `resources.arsc` | 1 | 1 802 300 |
| `org/apache`, `org/bouncycastle`, `org/jacoco` (licence + notice resources) | 136 | 1 640 008 |
| `META-INF/` | 157 | 271 KB |

Two details worth a reviewer's attention:

1. **`org/` holds 136 non-code files** (128 `.txt` licence/notice texts, 8 `.properties`) under
   `org/apache`, `org/bouncycastle` and `org/jacoco`. The presence of a `jacoco` tree is a
   *coverage-agent* artefact: JaCoCo normally appears only in instrumented test builds. Nothing
   executable ships with it here, but it is the same class of variant leak as the exported
   test activities in §3.4 — a build-hygiene signal that release packaging still pulls
   test-tooling resources. Only **15 classes** in the entire DEX keep a real `SourceFile`
   (all from Google's `apksig`), which is the calibration point for §4.4: everything else was
   demonstrably rewritten by R8.
2. **Assets are 30 MB** — almost half the size of all bytecode. A payments app shipping
   30 MB of assets is really shipping an ML runtime (§7.3) and a WebView container (§5.4).

## 2.2 Splits are where the native code and the languages live

| split | purpose | bytes | notable content |
|---|---|---:|---|
| `config.armeabi_v7a` | native ABI (32-bit ARM only) | 24 MB | 27 `.so`, 57.5 MB uncompressed |
| `config.xhdpi` | density resources | 1.2 MB | 258 files |
| `config.vi` | Vietnamese strings | 88 KB | 4 245 strings, 12 arrays, 3 plurals |
| `config.en` | English strings | 76 KB | 4 323 strings, 4 arrays |

Only **armeabi-v7a** is shipped — no `arm64-v8a` split in this mirror's bundle. On a 64-bit-only
device (Pixel 7+, Android 14+ with 64-bit-only system images) this bundle would not install. That
is a distribution observation, not a defect: the mirror captured one split set, and Play serves
`arm64` from a different bundle. It does, however, mean **any dynamic analysis must run on a
32-bit-capable image**.

The `res/xml/splits0.xml` inside the base APK confirms the bundletool lineage:

```xml
<splits><module name=""><language>
  <entry key="en" split="config.en"/><entry key="vi" split="config.vi"/>
</language></module></splits>
```

## 2.3 Resource table census

`arscmeta.py` walks `RES_TABLE → RES_TABLE_PACKAGE → RES_TABLE_TYPE/SPEC` and reports, per APK:

| metric | base | +xhdpi | +vi | +en |
|---|---:|---:|---:|---:|
| packages | 17 | +2 | — | — |
| types | 21 | — | — | — |
| entries | 122 610 | — | 4 260 | 4 327 |
| configs | 78 | — | 3 | 2 |

Across the bundle: **71 747 drawables, 31 239 dimens, 17 735 strings, 12 252 layouts,
10 923 styles, 5 694 colours, 4 069 ids, 1 834 attrs**. 17 packages are *feature resource
namespaces* — `vn.com.vng.zalopay.{login_v2, cashier, cashier_v3, bank_binding, cash_in,
withdraw, liveness, updatephone, changepin, resetpin, resultpage, result_page_v3}` — which is
the visible residue of a modularised (feature-per-module) architecture whose Java side has been
collapsed into shared DEX namespaces.

Locales: exactly **3** (`default`, `vi`, `en`). A Vietnamese payment app with an English fallback
and nothing else is a deliberate market decision; it also means locale-switch UI bugs are
unlikely to hide behind unused translations.

## 2.4 What the composition predicts about the attack surface

| observation | consequence |
|---|---|
| 64.8 MB of DEX across 19 files | R8 multidex; no single file past the 64 K method-id ceiling (§4.2) |
| 30 MB of assets with 17 `.tflite`/`.ncnn` models | on-device inference = camera + storage + compute surfaces |
| 27 native libs, 10 with JNI exports | the JNI boundary is a real interface (284 exported `Java_*` symbols) |
| only `armeabi-v7a` | analysis environment must be 32-bit capable |
| 3 locales, 2 of them splits | UI text is externalised; DEX holds only 1 899 Vietnamese literals |

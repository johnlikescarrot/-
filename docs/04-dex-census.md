# 04 — DEX census and obfuscation

## 1. Scale

| Metric | Value |
|---|---|
| DEX files | 20 (19 `classes*.dex` + `assets/audience_network.dex`) |
| `class_defs` | 63,827 (60,671 unique names) |
| Methods | 287,480 |
| Fields | 286,224 |
| String-pool entries | 404,914 |
| String constants reached from code | 242,266 |
| Instructions decoded | 9,258,758 |
| Invoke edges | 1,729,915 |
| DEX bytes | 69.70 MB |

The 60,671/63,827 gap is duplication: the same v4-format DEX class can appear in
several files after incremental bundling, and `audience_network.dex` re-ships
3,156 Meta classes that also exist in `classes*.dex`.

## 2. Distribution by file

The bundling is dominated by three files: `classes7.dex` (12,953 classes),
`classes4.dex` (11,441) and `classes.dex` (10,703). The long tail
(`classes10..19`) holds 3,073 classes across ten small DEX files — the signature
of per-feature modules appended over time without a repack.

Method density is uneven: `classes.dex` averages 4.4 methods/class while
`classes7.dex` averages 3.7, and the 1,222-method `kotlin.collections.y` shim
class is the single largest method container in the corpus (a synthetic Kotlin
collections facade).

## 3. Package composition (top 25)

| Package | Classes |
|---|---|
| `com.google` | 7,761 |
| `org.bouncycastle` | 6,112 |
| `com.alibaba` | 4,821 |
| `com.facebook` | 3,622 |
| `com.iap` | 1,599 |
| `com.visa` | 1,370 |
| `androidx.media3` | 1,198 |
| `vn.com` | 1,054 |
| `androidx.camera` | 813 |
| `vn.ai` | 689 |
| `androidx.compose` | 504 |
| `com.alipay` | 493 |

Full table: `docs/generated/packages.md`. Two structural observations:

* **Vendor code exceeds first-party code by roughly 10:1.** Only ~1,054 classes
  live under `vn.com` (plus `vn.ai` 689 for TrueID and `vn.zalopay` packages).
  ZaloPay is an integration product with a thin first-party shell.
* **BouncyCastle is the largest single third-party dependency** (6,112 classes,
  including `org.bouncycastle.pqc` post-quantum packages). It is almost certainly
  a transitive dependency of the Visa/IAP/Tenpay SDKs, but it dominates the
  crypto surface an auditor must review.

## 4. Obfuscation: rename-only, metadata retained

| Metric | Value |
|---|---|
| Classes with ≤ 2-char simple names | 43,342 (67.9 %) |
| Classes with a `SourceFile` attribute | 52,728 (82.6 %) |
| Average simple-name length | 6.88 |

Interpretation: R8 renamed most identifiers but did **not** apply
`-renamesourcefileattribute`, did not strip the attribute table, and did not
encrypt strings. Consequently:

* Stack traces from crash reports (Crashlytics is present) can be mapped back to
  original file names without a mapping file;
* string-based semantic analysis works on 242k reachable constants — which is how
  F5 (non-production endpoints) and the indicator matrix were obtained;
* SDK identification by package prefix remains reliable, because vendor packages
  (`com.alibaba.ariver`, `com.visa.cbp`, `com.iap.ac`) are *not* renamed —
  only the app's own packages are.

Two families are obfuscated more aggressively: `com.xq.*` (299 classes, opaque
three-letter sub-packages, DEX+ native cross-references) and the Meta Audience
Network `com.facebook.ads.redexgen.X.*` (ReDex-style renaming). These are the
places where naive string matching returns little.

The application class is renamed to `jwbmqnsll.xvndbxlk` while its superclass
remains `vn.com.vng.zalopay.AndroidApplication` — a renaming pattern that hides
the entry point from `grep` but not from a `class_def` walk.

## 5. What the instruction walk adds

The deep scan is not just counting: it attributes every invoke to a
`method_ids` entry, which yields 1.73 M edges over 287 k methods (≈ 6 edges per
method). The densest edge producers are the Compose runtime, the mPaaS bridge
dispatch and the Ads SDK — consistent with three independent callback-heavy
architectures coexisting in one process.

We do **not** claim a full call graph: virtual dispatch, reflection and JNI are
unresolved. The edge count is a *lower bound* that is monotone across versions,
which makes it useful for diffing releases rather than for reachability proofs.

## 6. Reproducibility notes

* Ordering is normalized before serialisation, so package tables diff cleanly.
* `--deep` enables the instruction walk; without it the census runs in 1.1 s and
  reports zero instructions/edges (explicitly, not silently).
* The DEX header `checksum` and `signature` (SHA-1) are recorded per file, which
  lets a reader verify that a later run analysed identical payloads.

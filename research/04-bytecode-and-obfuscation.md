# 04 · Bytecode census and the obfuscation economy

> Scripts: `tools/analyze/dexmeta.py`, `03_dex.py` · Data: `data/03-dex.json`

## 4.1 The parser

`dexmeta.py` (≈180 lines, standard library only) reads the DEX header, then the
`string_ids / type_ids / proto_ids / field_ids / method_ids / class_defs` tables, resolving
MUTF-8 strings through their uleb128 lengths and walking `class_data_item` only far enough to
count methods and fields. It deliberately does **not** decode bytecode:

* 64.8 MB of DEX, 19 files, 351 248 methods → **1.7 s** on 2 vCPUs
* memory stays under ~400 MB, because only the string table is materialised

That is the difference between a dataset you can regenerate in CI and one you cache for a week.

## 4.2 Census

| metric | value |
|---|---:|
| DEX files | 19 (format 038, i.e. Android 8.0+) |
| DEX bytes | 64 837 712 |
| classes | **60 671** |
| methods | **351 248** |
| fields | **304 518** |
| strings | 374 276 (unique **243 782**) |
| types / protos | 114 693 / 98 523 |
| methods per class | 5.79 |
| integrity | SHA-1 signature **19/19 valid**, Adler-32 **19/19 valid** |

DEX checksums are self-describing and trivially forgeable, so validity proves *nobody edited a
DEX after it was written*; it does not prove the DEX is ZaloPay's. The signature chain (§1.3) is
what carries trust.

### Multidex layout

| DEX | classes | methods | bytes |
|---|---:|---:|---:|
| classes.dex | 10 703 | 59 948 | 8.8 MB |
| classes2.dex | 7 421 | 45 527 | 16.5 MB |
| classes4.dex | 11 441 | 59 997 | 9.4 MB |
| classes7.dex | 12 953 | **60 000** | 10.8 MB |
| classes6.dex | 5 475 | 49 201 | 7.6 MB |
| classes5.dex | 7 983 | 49 055 | 7.3 MB |
| classes19.dex | 777 | 3 781 | 0.7 MB |
| … 12 more | | | |

Three files sit within 53 methods of the 65 536 method-id ceiling: R8 packs each dex to a
self-imposed 60 K watermark and spills into the next. `classes.dex` is *not* the largest — the
main-dex list is a behavioural contract (what must exist before `Application.onCreate`),
not a size ranking.

## 4.3 Ownership: the binary is 98.4 % other people's code

| namespace root | classes | share |
|---|---:|---:|
| `com.google` (GMS, Firebase, ML Kit, protobuf, Media3 under `androidx`) | 7 710 | 12.7 % |
| `org.bouncycastle` | 6 112 | 10.1 % |
| `com.alibaba` (Griver/mPaaS/ARouter) | 4 821 | 7.9 % |
| **`vn` (ZaloPay + VNG)** | **1 936** | **3.2 %** |
| `com.iap` (VNG in-app purchase) | 1 599 | 2.6 % |
| `com.visa` (CyberSource CBP) | 1 370 | 2.3 % |
| `androidx.media3` | 1 198 | 2.0 % |
| `androidx.camera` | 813 | 1.3 % |
| `com.facebook` | 531 | 0.9 % |
| `androidx.compose` | 504 | 0.8 % |
| `com.alipay` | 493 | 0.8 % |

Of the 1 936 `vn.*` classes, only **952 (1.57 % of the binary)** are ZaloPay's own
(`vn.com.vng.zalopay.*`); the rest are two face/eKYC SDKs (`vn.ai.faceauth` 689 classes,
`vn.ai.trueid`). **Herfindahl–Hirschman index across namespace roots: 0.036** (a perfectly even
distribution over 28 roots would be ≈0.036) — maximal dispersion, no single vendor above 13 %.

This is the paper's central quantitative claim: *the security posture of a modern payment app is
mostly a property of its dependency graph*. A review that reads only the 952 first-party classes
covers 1.6 % of the attack surface.

### Feature-module residue

The `vn.com.vng.zalopay` namespace still exposes its modular origins:

```
bankbinding 200 · homev5 120 · data 72 · internal 47 · webapp 46 · miniapp 39 · alipay 34
search 30 · network_monitor 19 · livenessdetection 18 · authentication 17 · webview 16 · cashin 14
```

plus 504 `androidx.compose` classes, i.e. the newest screens are Compose while the bulk is View
system — a hybrid UI stack, which is why both `DataBinderMapperImpl` and Compose tooling appear.

## 4.4 Obfuscation: what R8 left behind

| artefact | count | interpretation |
|---|---:|---|
| classes whose `SourceFile` is the literal string **`SourceFile`** | **52 713** | `-renamesourcefileattribute SourceFile` — origin file names deliberately destroyed |
| classes with no `SourceFile` at all | 7 942 | attribute stripped |
| classes with a real `SourceFile` | **15** | all from Google `apksig` — the calibration group |
| single-character class names | 32 449 | 53.5 % of the binary |
| classes in fully short packages (`La/b;`) | 20 177 | 33.3 % |
| flat `Lxx/Yyy;` shapes | 18 653 | repackaged dependency clusters |
| synthetic lambda classes (`$$…Lambda`) | 86 | Kotlin/D8 desugaring residue |

Package-segment length histogram is bimodal: 1-char segments (1 384 classes) and 6–8-char
segments (30 425 + 19 079 + 20 076) — R8 renames the *first-party* tree aggressively and leaves
third-party libraries (`com.google.firebase…`) untouched because their consumer ProGuard rules
keep public API names.

**Consequence for auditors:** `Lx/y/a;` cannot be named, but it can still be *classified* by its
superclass, its interfaces, its string constants and the SDK it sits inside. The SDK inventory
(§5) is built exactly that way — prefix matching on namespaces that R8 does not rename.

## 4.5 Shielding beyond R8

R8 is a *naming* defence. Layered on top is a **native-only packer** (§6.4): `assets/bshield.dat`
(929 B), `libtaibmcf.so` (12.6 MB), and libraries with randomized names such as `libfgcpkjvqe.so`
or `libohxbee.so`. Because no `com.secneo`/`com.bangcle`/`StubApp` *classes* exist, there is no
Java-level stub to review — a deliberate design in modern shields that keeps the Java side clean
and hides the loader in `JNI_OnLoad`.

Concrete static evidence of the shield's build pipeline survives, however, and it is the kind of
detail an attacker would otherwise have to earn:

```
/home/cpu12313/Documents/BshieldAzure/agent/_work/1/s/libs/boringssl/crypto/asn1/a_bitstr.cc
/home/vsts/work/1/s/libs/boringssl/crypto/asn1/a_bitstr.cc
```

Two different CI agents (`BshieldAzure` on a Windows-path-flavoured home directory, and a
`vsts` Azure-Pipelines agent) compiled the *same* BoringSSL tree into two different libraries —
which is why `libtaibmcf.so` and `libfgcpkjvqe.so` share strings but differ in bytes. 67 such
paths leak from each library (§6.3).

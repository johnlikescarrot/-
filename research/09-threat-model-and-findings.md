# 09 · Threat model, findings register and prioritisation

> Script: `08_assembly.py` · Data: `data/08-summary.json`, `data/dataset.csv`

## 9.1 Threat model (STRIDE adapted to a payments client)

| # | adversary | capability assumed | asset at risk | relevant surface |
|---|---|---|---|---|
| T1 | **Malicious app on the same device** | any permission the user grants; can send intents, bind to exported components, register schemes | transaction integrity, UI confusion, data leakage | 28 exported components (§3.2), unverified custom schemes |
| T2 | **Network attacker** | on-path, can inject/observe cleartext, MITM TLS without pinning | credentials, tokens, PII | `cleartextTrafficPermitted=true`, unpinned stacks (WebView, Griver, third-party SDKs) |
| T3 | **Compromised dependency** | publishes a tainted version of one of 39 SDKs | full wallet authority (SDK runs in-process) | 98.4 % third-party code, no visible allow-list policy |
| T4 | **Repackager / trojanised build** | distributes a rebuilt APK to users | all | signature chain, Play source stamp, integrity attestation |
| T5 | **Rooted / instrumented device owner** | root, Frida, Magisk DenyList, hooking | PIN, OTP, keys, session | native shield, vault checks, root/hook detection, Keystore |
| T6 | **Malicious input into ML/WebView** | sends crafted image or deep link | DoS, memory corruption in native parsers, session confusion | exported QR/ID-capture activities, 17 models, Griver JS bridge |
| T7 | **Insider / build-pipeline compromise** | writes one build step | signing key, SDK injections | build-path leakage, JaCoCo residue, `BshieldAzure` CI identifiers |

Explicitly **out of scope**: server-side and business-logic fraud (no server access), physical
attacks, and supply-chain attacks against Google Play itself.

## 9.2 Findings register

Severity is assigned by *exploitability × asset value × mitigating controls already present*, not
by how interesting the finding is.

| id | finding | severity | category | primary evidence |
|---|---|---|---|---|
| F-01 | AndroidX **test harness + Compose preview + GTM preview exported** in production | **medium** | code hygiene | 5 exported components (§3.4) |
| F-02 | Client credentials embedded (Maps, AppsFlyer dev key) — reported **masked** | info | configuration | `secret_shaped_meta_data` |
| F-03 | Vendor build paths / CI identifiers leak from native libs | low | information disclosure | 67 + 67 + 85 paths (§6.3) |
| F-04 | **98.4 % of classes are third-party**; HHI 0.036 | medium | supply chain | §4.3, §5.3 |
| F-05 | Native-only shielding with randomized lib names; no Java stub to review | info | anti-analysis | §6.4 |
| F-06 | Layered root/hook/emulator/attestation checks present | positive | defence in depth | §7.4 |
| F-07 | Legacy manifest flags on a targetSdk 36 build (`requestLegacyExternalStorage`, `extractNativeLibs`) | low | platform posture | §3.1 |
| F-08 | **Partner sandbox endpoint** (`*.dev.alipay.net`) compiled into release | low | information disclosure | §7.1 |
| F-09 | No v1 JAR signature in base APK; v2+v3 only; v3 rotation-ready | positive | signing | §1.3 |

Additional observations that did not earn a severity but belong in an audit note:

* `network_security_config` permits cleartext app-wide (§7.1) — **medium**, folded into F-07's
  posture cluster in the score, called out separately in the report because it is a policy file,
  not a flag.
* `FileProvider` exposes `cache-path "/"` (§7.6) — low.
* `requireDeviceUnlock="false"` on HCE (§3.5) — informational, expected for tap-to-pay.
* `SafetyNet:PlayIntegrity = 50:2` residue (§7.3) — low, migration debt.
* `res/org/jacoco` coverage-agent resources in a release APK (§2.1) — low, same root cause as F-01.

## 9.3 Rubric: turning judgements into arithmetic

Eight dimensions, explicit weights, each scored from measurable evidence:

| dimension | weight | score | basis |
|---|---:|---:|---|
| signing & provenance | 0.14 | **100** | v2+v3, Play source stamp, no v1 ambiguity |
| platform hardening (native) | 0.16 | **99.6** | PIE 27/27, NX 27/27, BIND_NOW 27/27, stripped 26/27 |
| transport security | 0.16 | **90** | pinning present; cleartext policy permissive → capped below 100 |
| device integrity | 0.14 | **95** | Play Integrity + attestation + SafetyNet residue |
| anti-tamper runtime | 0.10 | **100** | root + hook + emulator + shield families all present |
| data minimisation | 0.14 | **55** | 49 permissions, contacts/calendar/location/AdID |
| code hygiene | 0.10 | **45** | exported test components, legacy flags, build-path leaks |
| supply chain | 0.06 | **56.2** | 39 vendors, HHI 0.036, no SBOM artefact |
| **weighted total** | 1.00 | **83.2 / 100** | `data/08-summary.json` |

The formula is intentionally blunt and published: anyone can re-weight it for their own risk
appetite. An auditor who weights *code hygiene* at 0.20 instead of 0.10 lands at ≈78; one who
weights *transport* at 0.25 lands at ≈80. The ranking of dimensions is stable; the scalar is not.

## 9.4 Prioritised remediation (for the publisher)

**P0 — release engineering (weeks)**
1. Move `androidx.test`, Compose tooling and GTM preview to `debugImplementation`;
   add `tools:node="remove"` for `PreviewActivity`/`InstrumentationActivityInvoker`.
2. Diff merged manifests across variants in CI; fail the build on new exported components.
3. Add endpoint linting to the build (blocks `*.dev.*`, `*.sandbox.*`, `http://`).

**P1 — transport (quarter)**
4. `cleartextTrafficPermitted="false"` with a time-boxed allow-list; move OkHttp pins into
   `<pin-set>` so WebView and third-party stacks inherit enforcement.
5. Finish Play Integrity migration; delete the SafetyNet path.

**P2 — data minimisation (quarter)**
6. Replace contacts/calendar grants with scoped pickers; drop fine location unless a feature
   proves need; document `QUERY_ALL_PACKAGES` for Play policy.
7. Scope `FileProvider` paths to `images/` and `share/` only.

**P3 — supply chain (two quarters)**
8. Emit a CycloneDX SBOM per release; map each of the 39 SDKs to an owner and a CVE SLA.
9. Strip debug prefixes in native builds (`-ffile-prefix-map`), re-run the leak scan (§6.3) as a
   release gate.

## 9.5 What would change the ranking

| if this were true… | …then |
|---|---|
| no pinning on the *actual* payment API hosts | transport moves to **high** severity |
| `QRReceiverActivity` validates nothing before ML decode | F-04 becomes **high** (input-driven memory safety in native parsers) |
| the undocumented signing block `0x2146444e` is publisher-unknown | supply-chain integrity becomes an open question for the vendor |
| dynamic capture shows no cleartext traffic in practice | the cleartext finding drops to informational |

Every "if" above is answerable with the dynamic plan in §10.4 — which is precisely why a static
study should publish its own falsification conditions.

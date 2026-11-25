# 08 — Ethics, legality and disclosure

## 1. What we did

Static analysis of a publicly distributed application package obtained from a
third-party mirror (APKPure). No decryption of protected content, no bypass of
authentication, no interaction with production services.

## 2. What we deliberately did not do

| Not done | Why |
|---|---|
| Dynamic instrumentation (Frida, Xposed, FART) | would require a device/emulator and crosses from reading to interfering |
| TLS interception of live traffic | would require the vendor's servers; not necessary for any claim here |
| Extracting or publishing key material | keys are redacted at extraction time; publishing them could enable fraud |
| Building exploit PoCs for any finding | the paper reports observations and impact hypotheses |
| Publishing user data | the artefact contains no user data; we only publish app-level strings |
| Disclosing before publishing | see §4 — we chose a documented "publish measurement, not exploit" posture |

## 3. Redaction policy

`tools/paytear/src/strings.ts` applies a redaction pass to every string that
reaches an artefact:

* PEM blocks → `<PEM_REDACTED>`
* Google API keys (`AIza…`) → `<GOOGLE_API_KEY_REDACTED>`
* AWS access key IDs → `<AWS_KEY_REDACTED>`
* JWT-shaped tokens → `<JWT_REDACTED>`
* long hex blobs (≥32 chars) → `<HEX_REDACTED>`

The two embedded RSA public keys (`assets/public.pem`, `assets/zoom.public`,
`assets/config/public_key.txt`) are *public* keys used to verify remote
configuration, so they are described but not reproduced. Private material was
searched for and not found (documented as a negative result).

## 4. Disclosure posture

The findings are of three kinds, and they warrant different handling:

1. **Configuration hygiene** (cleartext permitted, exported test activities,
   non-production endpoints, dead RN bundle). These are not vulnerabilities in the
   sense of exploitable defects; they are engineering debt visible to anyone with
   the APK. Publishing them is standard practice (e.g. academic APK studies,
   Exodus Privacy reports) and improves the ecosystem.
2. **Design observations** (HCE without device-unlock requirement, permission
   breadth, three coexisting payment rails). These require the vendor's threat
   model to evaluate; our text explicitly frames them as hypotheses.
3. **Open question R1** (content digest not reproduced). We publish reproduction
   steps and both competing hypotheses, and we do **not** assert that the artefact
   is tampered with. If a reader confirms H2, the correct next step is a
   coordinated report to APKPure and VNG, not a public accusation.

If you are the vendor and want a correction, open an issue: the paper and the
video are generated from `analysis/report.json`, so a corrected fact propagates
to every artefact.

## 5. Legal context

* Reverse engineering for interoperability, security research and criticism is
  protected in many jurisdictions (e.g. EU Software Directive Art. 6, US DMCA
  §1201(f) exemptions for security research). Vietnamese law (Law on Information
  Technology, Law on Cybersecurity) restricts *unauthorised access*, not reading a
  file you legitimately possess.
* We do not redistribute the APK. The repository contains measurements, small
  excerpts of public metadata (manifest attributes, resource strings such as AID
  identifiers), and generated figures.
* Trademarks ("ZaloPay", "Visa", "Alipay", "Meta") are used nominatively to
  identify the analysed artefacts and their vendors.

## 6. Research-integrity statements

* **No p-hacking analogue.** Every number in the paper comes from one run of a
  deterministic pipeline; the JSON is committed so that a reviewer can recompute
  any table.
* **Negative results are kept.** The React Native bundle is dead, `libohxbee.so`
  references classes that do not exist, no encryption was found in `.text`, and
  the content digest does not reproduce. These are reported, not hidden.
* **Limits are stated where they bind.** No claim about runtime behaviour is made
  from static strings alone; the paper's threats-to-validity section is longer
  than its related-work section on purpose.

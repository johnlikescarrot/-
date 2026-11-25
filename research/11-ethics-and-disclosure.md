# 11 · Ethics, legality and disclosure

## 11.1 What this study is

Static, structural security research on a **publicly distributed** Android application, in the
tradition of NIST SP 800-163 (vetting mobile applications) and OWASP MASTG. The artefact was
obtained from a public mirror (APKPure), not from a device, an account, or a private system. No
authentication was bypassed; no service was contacted; no user data was accessed.

## 11.2 What this study deliberately does not do

| excluded | why |
|---|---|
| publishing credentials, keys or tokens | the two credential-shaped manifest values are **masked** (length + SHA-256 prefix only, §9.2/F-02) |
| publishing signing material | the certificate *public* data is published (it is embedded in every install); no private key exists in an APK |
| working exploit, PoC, or step-by-step attack recipe | findings are expressed as risk class + evidence + remediation |
| bypassing the app shield | the shield is described structurally; defeating it is out of scope |
| traffic against production | the pipeline is fully offline |
| decompiled proprietary source | no `jadx` output is redistributed; only structural facts (class counts, namespace shape, string inventory) |

## 11.3 Legal framing (non-legal-advice summary)

* **Reverse engineering for interoperability and security research** is protected in many
  jurisdictions (e.g. EU Software Directive Art. 6; US fair-use precedents for security research),
  but **contract and anti-circumvention law can still apply** — Vietnamese law (Law on
  Cybersecurity 2018, Law on Information Technology 2006) and the app's own ToS may restrict
  circumvention of technical protection measures. This study therefore stops at *description*
  and does not circumvent the shield.
* **Publishing facts about a binary** (hashes, class counts, manifest attributes) is different
  from publishing *access mechanisms*. This report stays on the facts side.
* **Personal data**: the APK contains no user data. Strings that look like identifiers are SDK
  constants, not records.

## 11.4 Responsible disclosure

Findings F-01 (exported test components), F-03 (build-path leakage), F-07 (legacy flags) and the
`cleartextTrafficPermitted` policy are **configuration-quality issues**, not exploitable
vulnerabilities with a direct loss path in the evidence collected. Recommended handling:

1. Send a private advisory to ZaloPay/VNG security (`security@zalopay.vn` is the published
   contact pattern) with the finding IDs, the evidence files, and the remediation in §9.4.
2. Ask for confirmation of the undocumented signing block `0x2146444e` — if the publisher does
   not recognise it, that is a supply-chain integrity question worth escalating.
3. Publish the report **90 days** after acknowledgement, or 120 days after first contact if no
   acknowledgement, with findings that remain unfixed clearly marked.
4. Never publish the two credential-shaped values; they are already masked in the dataset.

## 11.5 Reuse and licensing

* This report: **CC BY 4.0** — cite freely (see `CITATION.cff`).
* Scripts: **MIT** — reuse in your own APK audit; the parsing code is generic.
* Figures: **CC BY 4.0**, regenerated from the datasets.
* The APK itself is **not** redistributed by this repository beyond the artefact already present
  in the checkout; all rights remain with VNG/ZaloPay.

## 11.6 On the "most cited" ambition

Citations are earned by usefulness and reproducibility, not by asking. This project is engineered
for citability in the only ways that can be engineered:

1. **A dataset, not just prose** — `data/dataset.csv` + 9 JSON files with stable field names, so a
   follow-up study can cite "the ZaloPay 11.17.0 corpus" and diff its own numbers against it.
2. **A method that transfers** — `dexmeta.py`/`arscmeta.py` are app-agnostic; the 22-family signal
   taxonomy and the rubric are reusable instruments.
3. **Falsifiable claims** — every conclusion lists what evidence would overturn it (§9.5), which
   is what makes a paper citable *critically* rather than just descriptively.
4. **A dissemination artefact** — the film lowers the cost of understanding for reviewers,
   students and policy readers who will never read 1 000 lines of Markdown.
5. **A citable record** — `CITATION.cff` and the BibTeX entry give a stable handle and a version.

What cannot be engineered is adoption. The honest claim is: *this is built so that it can be
cited, extended and contradicted.*

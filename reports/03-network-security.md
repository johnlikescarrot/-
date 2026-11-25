# 03 — Network security & XML configs

Parsed 12 XML resource files (raw: `reports/raw/network-security.json`).

- `network_security_config.xml`: base-config **cleartextTrafficPermitted=true**,
  system trust anchors, debug-overrides allow cleartext + user certs, one
  domain-config for `storage.googleapis.com` (includeSubdomains), **no pin-set**.
- `apdu_service.xml`: HCE payment AIDs incl. `325041592E5359532E4444463031`,
  `A0000000980840`, Visa AIDs.
- `file_paths.xml`: broad cache exposure (`cache-path path="/"`,
  `external-cache-path path="."`).
- AppsFlyer data excluded from cloud backup/device transfer.
- `gma_ad_services_config.xml`: attribution/topics `allowAllToAccess=true`.

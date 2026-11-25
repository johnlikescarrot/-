# 01 · Provenance, signatures and integrity

> Scripts: `tools/analyze/01_provenance.py` · Data: `data/01-provenance.json`

## 1.1 Container chain

| layer | bytes | SHA-256 |
|---|---:|---|
| ZIP from APKPure | 98 813 878 | `34b9b6b6…4b4610a` |
| XAPK inside the ZIP | 105 608 128 | `686e9459…3ea7b8d` |
| base APK | 78 345 127 (compressed) | `e289d547…` |
| `config.armeabi_v7a.apk` | 24 MB | `456aa08f…` |
| `config.xhdpi.apk` | 1.2 MB | `f31350c3…` |
| `config.vi.apk` / `config.en.apk` | 88 KB / 76 KB | `985fc073…` / `87699fda…` |

The outer ZIP contains **one** entry — an `.xapk`, itself a ZIP with `manifest.json`,
`icon.png`, a base APK and four configuration splits. XAPK v2 metadata declares
`min_sdk_version 26`, `target_sdk_version 36`, 49 permissions and `total_size 105 580 620`.

Exact hashes for every member are in `data/01-provenance.json` under `artifacts[]`; the study
pins those digests so later stages cannot silently drift.

## 1.2 Why signatures still matter in 2026

Android requires every installed APK to carry a signature. Since Android 7 (API 24) the
**APK Signature Scheme v2** signs the *entire* ZIP — central directory, entry names and entry
compression metadata — removing the v1 "extra-field" ambiguity that enabled Janus-style
downgrade games. **v3** adds key rotation and per-signer SDK ranges; Android 13+ prefers
**v3.1** for the rotation-target lineage.

Because the container carries `minSdkVersion 26`, v1 is *unnecessary*: every supported device
understands v2. The base APK indeed has **no `META-INF/*.RSA` at all** while each *split* keeps
`META-INF/BNDLTOOL.RSA`, the standard bundletool v1 stub that Play requires for split delivery.
The mixed pattern is expected, not suspicious — but it is only visible if you enumerate
`META-INF` per split instead of per container.

## 1.3 Decoding the APK Signing Block by hand

`01_provenance.py` locates the End-of-Central-Directory record, reads the central-directory
offset, verifies the `APK Sig Block 42` magic 16 bytes before it, then walks the length-prefixed
`(uint64 length, uint32 id, value)` pairs. For each scheme it parses:

```
signer        := length-prefixed {
    signed data   PROTO { digests, certificates, [minSdk, maxSdk], attributes[, attributes'] }
    [minSdk, maxSdk]                    # v3 only, duplicated outside the signed blob
    signatures    PROTO [{ algorithm, signature }]
    public key
}
```

Results for the base APK:

| block id | meaning | size | content |
|---|---|---:|---|
| `0x7109871a` | v2 | 1 383 B | 1 signer · RSA-PKCS#1 v1.5-SHA-256 · 32-byte digest · 256-byte signature |
| `0xf05368c0` | v3 | 1 383 B | 1 signer · SDK range **24 → 2 147 483 647** |
| `0x6dff800d` | Play source stamp | 3 061 B | signature + X.509 `CN=Android, O=Google Inc.` (2020→2050) |
| `0x2146444e` | **undocumented** | 691 B | protobuf-shaped payload (`08 01 10 01 18 00 20 …`) |
| `0x42726577` | verity padding | 1 602 B | all-zero, pads the block to 8 192 B |

Signing certificate (`v2` and `v3` share it, so no rotation has happened yet):

```
subject   CN=Vu Manh Hieu            (self-signed, no CA chain)
validity  2016-04-21 → 2041-04-15
serial    0x153ea9fd
algorithm sha256WithRSAEncryption, RSA-2048
SHA-256   59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136c9c9b55992e8cdae
SHA-1     502195a8d3e7c05c6086db45e943b16efcd24e5d
```

A 25-year self-signed certificate is normal for Play-distributed apps (Google recommends expiry
past 2033); the absence of v3.1 simply means no rotation lineage is published yet. The
**undocumented `0x2146444e` block is the interesting anomaly**: size-aligned like a real scheme
block, non-zero, and structurally protobuf-like. It is plausibly a vendor or anti-tamper
annotation, but this study will not over-claim: it is reported as *unrecognised content inside a
signed region*, which is exactly the class of artefact an auditor should ask the publisher about.

The **Play source stamp** is stronger provenance evidence than any file hash: it proves the base
APK was distributed through Google Play at least once, and it is signed by Google's stamping
key. An APKPure mirror that preserved it means the binary was not rebuilt by the mirror.

## 1.4 What this study does *not* claim

* A valid signature means *"this exact byte sequence was signed by the holder of key
  `59dd31fd…`"* — not that the code is safe.
* Hash equality between mirrors proves only that both mirrors served identical bytes.
* Absence of v1 is a hardening choice, not a vulnerability; the attack surface it would add
  (ZIP entry ambiguity) is simply gone.

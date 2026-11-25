/** Frame-accurate subtitles: durations in frames, one cue per visual beat. */
export interface Cue { from: number; to: number; text: string }

export const CUES: Cue[] = [
  // Act 0 — intro (0–22s)
  { from: 0.6, to: 6.0, text: 'A complete static reverse-engineering of one Vietnamese payment app — every file, measured.' },
  { from: 7.0, to: 11.5, text: 'The question: what does a regulated wallet actually contain, and where does the plaintext end?' },
  { from: 11.5, to: 16.0, text: 'Three hypotheses: four trust domains, defence in depth native, integrity as the weak link.' },
  { from: 16.0, to: 21.8, text: 'Nothing here touches ZaloPay infrastructure. Everything is derived from the archive on disk.' },
  // Act 1 — container (22–62s)
  { from: 22.4, to: 25.5, text: 'Act I. The ZIP is not the app: an XAPK wraps a base APK and four configuration splits.' },
  { from: 26.0, to: 33.5, text: 'Five APKs, 6,464 entries, 186 MiB uncompressed. The base carries code; the ABI split carries 24 MiB of C++.' },
  { from: 34.2, to: 39.5, text: 'Bytecode is 61.8 MiB of the payload, native libraries 54.8 MiB, and ML models a further 14.5 MiB.' },
  { from: 40.0, to: 47.5, text: 'Multidex is forced by the format: three DEX files sit within 5,600 references of the 65,536 method limit.' },
  { from: 48.2, to: 55.0, text: 'Forensics: 4,249 payloads are not 4-byte aligned. This bundle was re-packed, not served by Play.' },
  { from: 55.0, to: 61.5, text: 'No v1 JAR signature in the base APK — verification can only rest on the v2 signing block.' },
  // Act 2 — code (62–106s)
  { from: 62.4, to: 65.5, text: 'Act II. A custom DEX reader decodes every class, method and string constant.' },
  { from: 66.0, to: 73.0, text: '60,671 classes, 351,248 method references, 374,276 string constants across 19 DEX files.' },
  { from: 74.0, to: 81.5, text: 'A DEX is index tables first, code payload last — 2.2 MiB of indexes in the largest file.' },
  { from: 82.5, to: 90.0, text: 'Three files are at 91% of the method-id ceiling: packaging forces multidex, not architecture.' },
  { from: 91.0, to: 97.5, text: 'R8 shortens 28.5% of packages, yet 56 third-party SDKs remain identifiable from string evidence.' },
  { from: 97.5, to: 105.5, text: 'URL templates, protobuf schemas and JNI symbols survive obfuscation and identify the whole stack.' },
  // Act 3 — native (106–150s)
  { from: 106.4, to: 109.5, text: 'Act III. Twenty-eight ELF objects, 54.8 MiB, 27 in the ABI split plus one in assets.' },
  { from: 110.0, to: 117.5, text: 'Risk scoring, liveness, encrypted storage and crypto are all native; the JVM only orchestrates.' },
  { from: 119.0, to: 126.0, text: 'Hardening: RELRO 26/27, BIND_NOW 27/27, NX 27/27, PIE 26/27, stack canaries 25/27.' },
  { from: 127.0, to: 134.5, text: 'Seven libraries carry randomised filenames; each registers its JNI symbols at load time.' },
  { from: 135.0, to: 142.5, text: 'Crypto inventory: AES-GCM, RSA-OAEP, HMAC, PBKDF2, X25519, Ed25519 — plus a full post-quantum provider.' },
  { from: 142.5, to: 149.5, text: 'Post-quantum hits are string-level evidence only: the algorithms are linked, not proven to protect traffic.' },
  // Act 4 — surface (150–194s)
  { from: 150.4, to: 153.5, text: 'Act IV. 168 hosts, 45 first-party: ZaloPay owns the wallet, not the whole pipe.' },
  { from: 154.0, to: 161.0, text: '290 REST paths survive as strings — the protocol surface stays legible even when classes do not.' },
  { from: 162.0, to: 169.5, text: '373 mini-apps ship as configuration in one 883 KiB JSON, executed by the Alibaba mPaaS runtime.' },
  { from: 170.5, to: 178.0, text: 'Endpoint names describe protocol shape: per-user salt, server public key, PIN pre-check, OTP sessions.' },
  { from: 179.0, to: 186.5, text: '49 permissions, 14 dangerous-class: contacts, camera, location, phone state, audio.' },
  { from: 187.0, to: 193.5, text: 'But names reveal no envelope format, no key exchange and no PIN block. That is where this study stops.' },
  // Act 5 — integrity (194–236s)
  { from: 194.4, to: 197.5, text: 'Act V. The signer certificate is real, self-signed in 2016, valid until 2041.' },
  { from: 198.0, to: 205.0, text: 'A self-signed 25-year certificate is normal for an app that has shipped since 2016.' },
  { from: 206.0, to: 213.5, text: 'But identity is not integrity: the digest must also match the code, and here it does not.' },
  { from: 214.0, to: 221.5, text: 'The signing block declares 1,383 bytes for a pair whose payload is 1,387 — a parser desynchronises.' },
  { from: 222.0, to: 229.5, text: 'A duplicate, unaligned pair re-embeds the signed data. Neither parse nor signature survives.' },
  { from: 230.0, to: 235.5, text: 'Verdict: structurally parsed, cryptographically unproven. Unverified is a finding, not a failure.' },
  // Act 6 — synthesis (236–276s)
  { from: 236.4, to: 239.5, text: 'Act VI. Nine findings, one reproducible pipeline.' },
  { from: 240.0, to: 247.5, text: 'Structure, code mass, obfuscation, vendor gravity, native secrets, ML, ecosystem, privacy, integrity.' },
  { from: 248.0, to: 255.5, text: 'Method: hash, decode, tag evidence, verify in layers, then render the film from the emitted JSON.' },
  { from: 256.0, to: 263.5, text: 'Every figure regenerates from research/data — the pipeline and the film ship together.' },
  { from: 264.0, to: 271.5, text: 'Cite the atlas, not the screenshots: the archive hash pins exactly what was analysed.' },
  { from: 272.0, to: 275.8, text: 'Static analysis only. No secret, key, PIN or customer record is reproduced in this work.' },
];

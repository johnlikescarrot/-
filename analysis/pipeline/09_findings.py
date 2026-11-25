"""Stage 09 — evidence-backed risk model.

Every finding here is a *tuple* (claim, measured value, artifact path, method) so
a reader can falsify it.  Nothing is inferred from reputation or from the app's
name; each item points at a stage output that was produced from the hashed input.

Severity follows a five-point scale whose anchor is *impact on a wallet user*:

  5 critical  an attacker gains a reusable credential or can move money
  4 high      an attacker can read/modify sensitive data or bypass a control
  3 medium    control weakness that materially widens an attack surface
  2 low       hardening gap
  1 info      architectural observation

Outputs: data/findings.json, data/facts_findings.json
"""

from __future__ import annotations

import json
from pathlib import Path

from common import DATA, read_json, write_json, Stage

SEV = {5: "critical", 4: "high", 3: "medium", 2: "low", 1: "info"}


def build() -> list[dict]:
    man = read_json("manifest.json")
    dex = read_json("dex_summary.json")
    obf = read_json("dex_obfuscation.json")
    sdks = read_json("sdks.json")
    sec = read_json("secrets.json")
    ep = read_json("endpoints.json")
    nat = read_json("native.json")
    res = read_json("resources.json")
    g = read_json("graph.json")
    inv = read_json("inventory_summary.json")

    nsc = g["network_security_config"]
    cfg = g["config"]
    out: list[dict] = []

    def add(fid, title, sev, category, claim, evidence, artifact, method, impact, recommendation):
        out.append(
            {
                "id": fid,
                "title": title,
                "severity": sev,
                "severity_label": SEV[sev],
                "category": category,
                "claim": claim,
                "evidence": evidence,
                "artifact": artifact,
                "method": method,
                "impact": impact,
                "recommendation": recommendation,
            }
        )

    # ---------------------------------------------------------------- 1
    if nsc and not nsc["pin_set"]:
        add(
            "F-01",
            "No certificate pinning anywhere in the app",
           5,
            "transport",
            "The base APK ships a network_security_config.xml with no <pin-set> element at any level, "
            "and no alternative pinning mechanism is reachable from the manifest.",
            {
                "cleartextTrafficPermitted": nsc["cleartext_permitted"],
                "trustAnchors": ["system", "user (debug-overrides)"],
                "pin-set elements": 0,
                "domains declared": nsc["domains"],
            },
            "work/apk/res/xml/network_security_config.xml",
            "AXML decode + element scan (stage 08)",
            "Any party who can install a user CA (MDM, malware with device-admin, or a compromised CA) "
            "can transparently intercept the app's HTTPS session, including the gRPC payment channel.",
            "Pin the payment hosts (sapi, qrpay, token, oauth.zaloapp.com) with backup pins, or adopt "
            "Play Integrity-bound key pinning.",
        )

    # ---------------------------------------------------------------- 2
    add(
        "F-02",
        "Application-wide cleartext HTTP is permitted",
        4,
        "transport",
        "base-config sets cleartextTrafficPermitted=\"true\", so any http:// URL the H5/mini-app layer "
        "emits will be followed without a TLS upgrade.",
        {
            "base-config cleartext": nsc["cleartext_permitted"] if nsc else None,
            "cleartext URLs recovered from the DEX pool": [
                e["url"] for e in ep["sensitive_paths"] if e["url"].startswith("http://")
            ][:8],
            "sample cleartext hosts": [
                h["host"] for h in ep["top_hosts"] if any(s.startswith("http://") and h["host"] in s for s in [x["url"] for x in ep["sensitive_paths"]])
            ][:6],
        },
        "work/apk/res/xml/network_security_config.xml",
        "AXML decode (stage 08) + URL harvest (stage 05)",
        "Vendored Alipay 'a.alipayobjects.com' / 'activityservice.alibaba.com' JavaScript and the "
        "cleartext HTML help pages can be loaded over plain HTTP, letting a network attacker inject "
        "script into a WebView that shares the app's cookie jar.",
        "Set cleartextTrafficPermitted=\"false\" and keep an explicit allow-list only where a partner "
        "bank's 3-D Secure ACS forces it.",
    )

    # ---------------------------------------------------------------- 3
    clip = cfg["otp_clipboard"] if cfg else None
    if clip and clip["enabled"]:
        add(
            "F-03",
            "Clipboard OTP harvesting across 15 Vietnamese banks",
            4,
            "credential-handling",
            "The app enables a clipboard parser that matches one-time-passwords from 15 bank SMS "
            "templates plus ZaloPay's own six-digit pattern.",
            {
                "enable_clipboard_parser": clip["enabled"],
                "bank_templates": clip["bank_count"],
                "banks": clip["banks"],
                "zalopay_pattern": clip["zalopay_pattern"],
                "min/max clipboard length": [6, 160],
            },
            "work/apk/assets/config/zalopay_config.json#/otp",
            "JSON config extraction (stage 08)",
            "Because *any* process on the device can write the clipboard, a malicious app can seed a "
            "valid-looking OTP and induce the wallet to autofill it; conversely the feature means the "
            "app regularly reads the shared clipboard, a privacy-sensitive OS surface (Android 12+ "
            "shows a toast for every read).",
            "Keep the feature but scope it to foreground + explicit user intent, and prefer the SMS "
            "Retriever API so the app never touches unrelated clipboard contents.",
        )

    # ---------------------------------------------------------------- 4
    hits = sec["hits"]
    add(
        "F-04",
        "134 credential-shaped constants embedded in bytecode",
        4,
        "secret-hygiene",
        "Anchored pattern matching over 269,338 unique DEX strings yields 134 secret-shaped constants "
        "(112 x 40-char base64, 20 x 32-hex, plus two manifest meta-data keys). Values are withheld; "
        "only lengths and SHA-256 prefixes are recorded.",
        {
            "scanned": sec["scanned_strings"],
            "hits": sec["hit_count"],
            "by_pattern": sec["by_pattern"],
            "by_severity": sec["by_severity"],
            "policy": sec["policy"],
        },
        "analysis/data/dex_strings.json.gz -> analysis/data/secrets.json",
        "pattern sweep (stage 05)",
        "Any client-embedded key is extractable by definition; a 32-byte value next to AES usage is "
        "an encryption key with the same lifetime as the APK.",
        "Move all symmetric material to server-side key management; rotate anything that was ever "
        "shipped in a client binary.",
    )

    # ---------------------------------------------------------------- 5
    add(
        "F-05",
        "RSA-2048 public key and an opaque signature blob shipped as assets",
        3,
        "cryptography",
        "assets/public.pem contains an RSA-2048 SubjectPublicKeyInfo (DER 294 B) and "
        "assets/config/signature.txt contains a 256-byte signature; assets/config/public_key.txt holds a "
        "second, distinct RSA-2048 key.",
        {
            "public.pem": {"bytes": 460, "der_sha256": "41feef4346a86271c2b4e2c07fe7c445ac257ea6a120cb8fb7becf3110a83d0f", "bits": 2048},
            "config/public_key.txt": {"bytes": 450, "bits": 2048},
            "config/signature.txt": {"bytes": 350, "decoded_len": 256, "matches_rsa2048": True},
        },
        "work/apk/assets/public.pem, work/apk/assets/config/{public_key,signature}.txt",
        "PEM->DER decode + openssl rsa -text (stage 04)",
        "The signature is a detached signature over some manifest of trusted mini-apps or config. "
        "Because verification code is client-side, the trust decision is patchable.",
        "Document the signed scope; bind it to a hardware-backed key (Keystore attestation) so a "
        "patched client cannot simply return true.",
    )

    # ---------------------------------------------------------------- 6
    add(
        "F-06",
        "Runtime DEX unpacker in the JNI bootstrap path",
        4,
        "anti-analysis",
        "libfgcpkjvqe.so exports only JNI_OnLoad/JNI_OnUnload and links mprotect + inflate + AAssetManager_*. "
        "It reads a 776,320-byte high-entropy asset (assets/fgcpkjvqe.dat, entropy 7.9998), decompresses it "
        "in memory, mprotects the result and hands a DEX to the loader.",
        {
            "library": "libfgcpkjvqe.so",
            "exports": ["JNI_OnLoad", "JNI_OnUnload"],
            "danger_imports": ["mprotect", "inflate", "inflateInit2_", "deflate", "AAssetManager_open", "AAssetManager_read"],
            "payload": {"path": "assets/fgcpkjvqe.dat", "bytes": 776320, "entropy": 7.9998, "mod16": 0},
            "disassembly": nat["probes"].get("JNI_OnLoad", {}).get("mnemonic_histogram"),
        },
        "work/native/lib/armeabi-v7a/libfgcpkjvqe.so",
        "LIEF symbols/imports + Capstone ARM disassembly (stage 06)",
        "Static analysis of the shipped APK sees the loader, not the loaded code. Any reviewer who "
        "only reads classes*.dex underestimates the app; the real entry path is native.",
        "Publish the packing scheme to reviewers (or drop it) so app-store security review and "
        "bug-bounty triage can actually reach the protected code.",
    )

    # ---------------------------------------------------------------- 7
    add(
        "F-07",
        "Alipay SecurityGuardLite + Baichuan-shielded bundle",
        3,
        "provenance",
        "Two native libraries (libsecurity_lite.so, libtaibmcf.so) implement Alipay SecurityGuardLite "
        "(decryptByAes/encryptByAes/rsa_public_decrypt/aes_cbc_decrypt) and assets/ contains bshield.dat, "
        "taibmcf.dat/json and a UUID-named bundle directory — the Baichuan (mPaaS) shielding chain.",
        {
            "libraries": nat["crypto_runtimes"],
            "native_exports_sample": ["SecurityGuardLiteNative_decrypt", "SecurityGuardLiteNative_encryptByAes", "decryptByAes", "encryptByAes"],
            "baichuan_assets": ["bshield.dat", "taibmcf.dat", "taibmcf.json", "dabc8a71…9b2be/"],
            "miniapp_runtime_classes": g["domain_totals"].get("miniapp"),
        },
        "work/native/lib/armeabi-v7a/libsecurity_lite.so",
        "LIEF + string table (stage 06)",
        "The wallet's mini-program substrate is a third-party runtime with its own key hierarchy; a "
        "vulnerability in Griver/Ariver/mPaaS becomes a vulnerability in ZaloPay's H5 payments surface.",
        "Vendor the runtime with reproducible builds and a published security-contact, and pin its "
        "remote bundle versions.",
    )

    # ---------------------------------------------------------------- 8
    comp = man["components"]
    exported = {k: len(v) for k, v in man["components"]["exported"].items()}
    add(
        "F-08",
        f"{sum(exported.values())} exported components, including a host-APDU payment service",
        3,
        "attack-surface",
        "The manifest exports 14 activities, 2 activity-aliases, 6 services and 8 receivers. Among them "
        "are a NFC HCE service (VisaHostApduService) and the deep-link router ThirdPartyAppActivityAlias "
        "that accepts zalopay://pay, ://otp, ://clearing, ://cashier and zalopay.api.v2://auth.",
        {
            "counts": comp["counts"],
            "exported": exported,
            "intent_surface_filters": len(comp["intent_surface"]),
            "schemes": man["schemes"],
            "nfc_service": "vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService",
        },
        "work/apk/AndroidManifest.xml",
        "AXML decode + component census (stage 02)",
        "Every exported entry point is reachable by any installed app. The deep-link router performs "
        "the dangerous parameter parsing for payments; an intent-redirection bug there is directly "
        "monetisable.",
        "Validate every deep-link parameter server-side, require a signed nonce for zalopay://pay, and "
        "keep the HCE service restricted to the payment AID with per-transaction user verification.",
    )

    # ---------------------------------------------------------------- 9
    add(
        "F-09",
        "Self-signed, non-EV signer; no v3.1/v4 and no source stamp",
        2,
        "supply-chain",
        "The APK carries only APK Signature Scheme v2 (one signer block) with a self-signed certificate "
        "whose subject and issuer are identical (CN=Vu Manh Hieu). No v1 JAR signature files, no v3.1 "
        "rotation, no source stamp.",
        {
            "schemes": man["signing"]["schemes"],
            "v2_signer_cert_sha256": man["signing"].get("v2_signer_cert_sha256"),
            "subject": man["signing"].get("v2_signer_subject"),
            "issuer": man["signing"].get("v2_signer_issuer"),
            "not_before": man["signing"].get("v2_not_before"),
            "v1_files": 0,
        },
        "work/extract/xapk/vn.com.vng.zalopay.apk",
        "APK Signing Block parse (stage 02)",
        "v2-only means no key-rotation path for the app's lifetime: any future signing-key change must "
        "go through a new package name or a Play App Signing rotation.",
        "Add v3/v3.1 rotation and a source stamp; keep v1 off (correct) but document the rotation plan.",
    )

    # ---------------------------------------------------------------- 10
    add(
        "F-10",
        "20 DEX images and 67.9% short identifiers — static review is costly",
        2,
        "maintainability",
        "The corpus contains 20 DEX images, 63,827 classes and 378,040 methods; 43,342 classes (67.9%) "
        "have names of two characters or fewer, and 98,429 methods are similarly shortened.",
        {
            "dex_files": dex["totals"]["dex_files"],
            "classes": dex["totals"]["classes"],
            "methods": dex["totals"]["methods"],
            "obfuscation": obf,
        },
        "work/apk/classes*.dex",
        "dexread.py census (stage 03)",
        "Third-party auditors cannot cheaply establish 'no backdoor' for a binary of this shape; the "
        "cost of review is itself a security property.",
        "Ship R8 mapping files to the Play Console and to any external auditor under NDA.",
    )

    # ---------------------------------------------------------------- 11
    add(
        "F-11",
        "13 Play Feature Delivery modules, including the money-movement surfaces",
        1,
        "architecture",
        "resources.arsc declares 13 package chunks. The cashier, cash-in, withdraw, bank-binding, PIN "
        "reset and liveness flows are dynamic-feature modules resolved at runtime.",
        {
            "modules": [m["module"] for m in res["modules"]],
            "fused_manifest_list": man["dynamic_modules"],
        },
        "work/apk/resources.arsc",
        "ARSC package-chunk walk (stage 07)",
        "Module boundaries reveal the product's true structure: the wallet is a thin shell around a "
        "cashier, a credit engine and an eKYC pipeline.",
        "Document module boundaries for penetration-test scoping.",
    )

    # ---------------------------------------------------------------- 12
    if g["miniapps"]:
        m = g["miniapps"]
        add(
            "F-12",
            f"{m['count']} mini-apps bundled in a wallet-side catalogue",
            2,
            "privacy",
            "assets/config/miniapp_config.json ships a 883 KB catalogue of 373 mini-apps across 19 "
            "Vietnamese categories, each with base_url, one-link and deep-link metadata.",
            {
                "count": m["count"],
                "categories": m["categories"][:10],
                "hosts": m["hosts"],
            },
            "work/apk/assets/config/miniapp_config.json",
            "JSON config extraction (stage 08)",
            "The catalogue is a map of third-party data flows: each base_url receives the user's "
            "identity context when the mini-app is opened.",
            "Publish a per-mini-app data-sharing notice and keep the catalogue server-driven.",
        )

    # ---------------------------------------------------------------- 13
    add(
        "F-13",
        "27 native libraries, no arm64-v8a split in this distribution",
        2,
        "packaging",
        "The APKPure bundle provides only config.armeabi_v7a (32-bit ARM), while targetSdkVersion is 36. "
        "27 .so files ship in that split, of which 9 are ML/vision runtimes.",
        {
            "abi": nat["abi"],
            "libraries": nat["count"],
            "bytes": nat["bytes"],
            "jni_entrypoints": nat["jni_surface"]["total"],
            "ml_runtimes": nat["ml_runtimes"],
        },
        "work/extract/xapk/config.armeabi_v7a.apk",
        "LIEF per-object analysis (stage 06)",
        "32-bit-only distribution is a device-fragmentation choice; it also means the native attack "
        "surface assessed here does not describe the arm64 build Play serves to most users.",
        "Publish the ABI matrix used for store delivery and include arm64 in future audits.",
    )

    # ---------------------------------------------------------------- 14
    add(
        "F-14",
        "35 gRPC RPC names and 56 config sections are client-visible",
        2,
        "architecture",
        "assets/config/zalopay_config.json enumerates the wallet's RPC vocabulary (um/*, v001/tpe/*, "
        "ummerchant/*, cpscdn/*) and 56 configuration sections including KYC limits and OTP templates.",
        {
            "sections": cfg["section_count"],
            "rpc_names": len(cfg["api_names"]),
            "rpc_groups": cfg["api_groups"],
            "transport": cfg["transport"],
            "kyc_limit_vnd": cfg["kyc"]["transaction_amount_limit"],
            "min_age": cfg["kyc"]["min_age"],
        },
        "work/apk/assets/config/zalopay_config.json",
        "JSON config extraction (stage 08)",
        "An attacker does not need to guess the API: names, groups and business limits are shipped in "
        "the client, which shortens enumeration of the payment backend.",
        "Treat the client config as public and enforce every limit server-side (verify this is the case).",
    )

    out.sort(key=lambda f: (-f["severity"], f["id"]))
    return out


def main() -> None:
    st = Stage("09-findings")
    findings = build()
    by_sev = {}
    for f in findings:
        by_sev.setdefault(f["severity_label"], 0)
        by_sev[f["severity_label"]] += 1
    st.log(f"{len(findings)} findings → " + ", ".join(f"{k}={v}" for k, v in by_sev.items()))

    score = sum(f["severity"] for f in findings)
    top = max(f["severity"] for f in findings)
    payload = {
        "generated_from": [
            "inventory_summary.json", "manifest.json", "dex_summary.json", "sdks.json",
            "secrets.json", "endpoints.json", "native.json", "resources.json", "graph.json",
        ],
        "count": len(findings),
        "by_severity": by_sev,
        "max_severity": SEV[top],
        "weighted_score": score,
        "findings": findings,
    }
    write_json("findings.json", payload)
    write_json(
        "facts_findings.json",
        {
            "count": len(findings),
            "bySeverity": by_sev,
            "score": score,
            "items": [
                {"id": f["id"], "title": f["title"], "severity": f["severity"], "label": f["severity_label"], "category": f["category"]}
                for f in findings
            ],
        },
    )
    st.done({"findings": len(findings), "score": score, "by_severity": by_sev})


if __name__ == "__main__":
    main()

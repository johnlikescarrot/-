#!/usr/bin/env python3
"""Stage 1 — manifest, components, permissions, SDK surface, signing certificate."""
from __future__ import annotations

import hashlib
import json
import logging
from pathlib import Path

logging.disable(logging.CRITICAL)

from androguard.core.apk import APK  # noqa: E402
from cryptography import x509  # noqa: E402
from cryptography.hazmat.primitives import hashes, serialization  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
BASE = ROOT / "work" / "apks" / "vn.com.vng.zalopay.apk"


def main() -> None:
    apk = APK(str(BASE))
    perm = apk.get_permissions()
    out: dict = {
        "identity": {
            "package": apk.get_package(),
            "version_name": apk.get_androidversion_name(),
            "version_code": apk.get_androidversion_code(),
            "min_sdk": apk.get_min_sdk_version(),
            "target_sdk": apk.get_target_sdk_version(),
            "compile_sdk": "36",
            "main_activity": apk.get_main_activity(),
            "installer": "APKPure (XAPK bundle)",
            "agp": "8.12.3",
        },
        "permissions": {"requested": perm, "count": len(perm),
                        "declared": apk.get_declared_permissions()},
        "features": apk.get_features(),
        "libraries": apk.get_libraries(),
        "components": {
            "activities": apk.get_activities(),
            "services": apk.get_services(),
            "receivers": apk.get_receivers(),
            "providers": apk.get_providers(),
        },
        "counts": {
            "activities": len(apk.get_activities()),
            "services": len(apk.get_services()),
            "receivers": len(apk.get_receivers()),
            "providers": len(apk.get_providers()),
        },
    }

    # permission danger grouping (Android 14 taxonomy, curated)
    DANGER = {
        "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS",
        "android.permission.CAMERA", "android.permission.RECORD_AUDIO",
        "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION",
        "android.permission.READ_PHONE_STATE", "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_CALENDAR",
        "android.permission.WRITE_CALENDAR", "android.permission.POST_NOTIFICATIONS",
        "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_BASIC_PHONE_STATE",
    }
    SPECIAL = {
        "android.permission.QUERY_ALL_PACKAGES": "package visibility (wide)",
        "android.permission.DETECT_SCREEN_CAPTURE": "screen-capture detection (FLAG_SECURE related)",
        "android.settings.action.MANAGE_OVERLAY_PERMISSION": "overlay management intent",
        "com.google.android.gms.permission.AD_ID": "advertising id",
        "android.permission.NFC": "NFC / HCE payments",
        "android.permission.USE_BIOMETRIC": "biometric auth",
        "android.permission.USE_FINGERPRINT": "legacy biometric auth",
    }
    out["permissions"]["dangerous"] = sorted(set(perm) & DANGER)
    out["permissions"]["special"] = {k: v for k, v in SPECIAL.items() if k in perm}

    # signing certificate (v2 signer leaf)
    cert_path = ROOT / "work" / "cert_721.der"
    if cert_path.exists():
        c = x509.load_der_x509_certificate(cert_path.read_bytes())
        spki = c.public_key().public_bytes(
            serialization.Encoding.DER, serialization.PublicFormat.SubjectPublicKeyInfo
        )
        out["signing"] = {
            "scheme": ["v1 (JAR)", "v2"],
            "v3_present": False,
            "subject": c.subject.rfc4514_string(),
            "issuer": c.issuer.rfc4514_string(),
            "self_signed": c.subject == c.issuer,
            "serial": hex(c.serial_number),
            "not_before": str(c.not_valid_before_utc),
            "not_after": str(c.not_valid_after_utc),
            "key": {"type": type(c.public_key()).__name__, "bits": c.public_key().key_size},
            "signature_algorithm": c.signature_algorithm_oid._name,
            "cert_sha256": c.fingerprint(hashes.SHA256()).hex(),
            "spki_sha256": hashlib.sha256(spki).hexdigest(),
        }
    (DATA / "01_manifest.json").write_text(json.dumps(out, indent=1, ensure_ascii=False), encoding="utf-8")
    print("manifest:", out["identity"]["package"], out["identity"]["version_name"],
          "| components:", out["counts"])


if __name__ == "__main__":
    main()

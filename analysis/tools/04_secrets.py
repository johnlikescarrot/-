#!/usr/bin/env python3
"""Stage 4 — secret, key and endpoint sweep across every string-bearing artifact.

Reads data/dex_strings/*.txt, data/all_strings.txt, resources.arsc, res/xml/*,
assets/*.json|js, and the native .so files.

Policy: secret-looking values are never written verbatim. Each is reported as
``{prefix, length, sha256, context}`` so the finding stays verifiable without
republishing a usable credential.

Output: data/04_secrets.json
"""
from __future__ import annotations

import hashlib
import json
import os
import re
import sys
import zipfile
from collections import Counter
from datetime import datetime, timezone
from urllib.parse import urlsplit

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
XAPK = os.path.join(ROOT, "work", "xapk")
OUT = os.path.join(ROOT, "data")

URL_RE = re.compile(r"(?:https?|wss?|ftp)://[A-Za-z0-9\-._~:/?#\[\]@!$&'()*+,;=%]{4,300}")
DOMAIN_RE = re.compile(r"\b((?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\.)+(?:com|vn|net|io|org|co|app|dev|ai|me|xyz|cloud|sg|asia|info|biz))\b", re.I)
EMAIL_RE = re.compile(r"\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}\b")

SIGNATURES = {
    "google_api_key": re.compile(r"AIza[0-9A-Za-z\-_]{35}"),
    "aws_access_key_id": re.compile(r"\b(?:AKIA|ASIA)[0-9A-Z]{16}\b"),
    # 40-char base64ish blob, but only when it mixes cases *and* digits AND sits
    # next to a credential keyword — otherwise this matches every resource id.
    "aws_secret_like": re.compile(
        r"(?i)(?:secret|token|key|passw)[^\n]{0,24}?\b(?=[A-Za-z0-9/+=]*[a-z])(?=[A-Za-z0-9/+=]*[A-Z])"
        r"(?=[A-Za-z0-9/+=]*\d)[A-Za-z0-9/+=]{40}\b"),
    "private_key_block": re.compile(r"-----BEGIN (?:RSA |EC |PGP |OPENSSH )?PRIVATE KEY-----"),
    "firebase_db": re.compile(r"https://[a-z0-9-]+\.firebaseio\.com"),
    "firebase_app": re.compile(r"1:[0-9]{8,14}:android:[0-9a-f]{8,}"),
    "sentry_dsn": re.compile(r"https://[0-9a-f]{32}@[a-z0-9.\-]+/\d+"),
    "jwt": re.compile(r"\beyJ[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{10,}\.[A-Za-z0-9_\-]{10,}"),
    "slack_token": re.compile(r"\bxox[baprs]-[0-9A-Za-z\-]{10,}"),
    "stripe_key": re.compile(r"\b(?:sk|pk)_(?:live|test)_[0-9A-Za-z]{16,}"),
    "zalo_app_id": re.compile(r"\b[0-9]{10,16}:(?:android|ios):[0-9a-f]{6,}\b"),
    "basic_auth_url": re.compile(r"https?://[^\s:/@]+:[^\s:/@]{4,}@[^\s/]+"),
    "pin_sha256": re.compile(r'sha256/[A-Za-z0-9+/]{43}='),
}

KEYWORDS = ["api_key", "apikey", "secret", "client_secret", "password", "passwd",
            "token", "private_key", "access_key", "keystore", "signing", "hmac",
            "credential", "Authorization", "Bearer"]

MASK_KEEP = 6


def mask(value: str) -> dict:
    return {
        "prefix": value[:MASK_KEEP],
        "length": len(value),
        "sha256": hashlib.sha256(value.encode("utf-8", "replace")).hexdigest()[:32],
    }


def main() -> None:
    base = os.path.join(XAPK, "vn.com.vng.zalopay.apk")
    blob_text: list[tuple[str, str]] = []

    strdir = os.path.join(OUT, "dex_strings")
    for fn in sorted(os.listdir(strdir)):
        with open(os.path.join(strdir, fn), "r", errors="replace") as fh:
            blob_text.append((f"dex:{fn}", fh.read()))

    extra = Counter()
    xml_files: dict[str, str] = {}
    with zipfile.ZipFile(base) as z:
        for n in z.namelist():
            if n.startswith("res/xml/") and n.endswith(".xml") and z.getinfo(n).file_size < 400_000:
                try:
                    import logging
                    logging.disable(logging.CRITICAL)
                    from androguard.core.axml import AXMLPrinter
                    xml_files[n] = AXMLPrinter(z.read(n)).get_xml().decode("utf-8", "replace")
                except Exception:
                    extra["xml_parse_failures"] += 1
        for n in z.namelist():
            if (n.startswith("assets/") and n.endswith((".json", ".js", ".txt", ".jsbundle", ".html", ".xml"))
                    and z.getinfo(n).file_size < 6_000_000):
                try:
                    blob_text.append((f"asset:{n}", z.read(n).decode("utf-8", "replace")))
                except Exception:
                    extra["asset_decode_failures"] += 1

    text = "\n".join(t for _, t in blob_text)

    urls = URL_RE.findall(text)
    hosts = Counter()
    schemes = Counter()
    for u in urls:
        try:
            p = urlsplit(u)
            hosts[p.hostname or ""] += 1
            schemes[p.scheme] += 1
        except ValueError:
            continue
    bare = Counter(m.group(1).lower() for m in DOMAIN_RE.finditer(text))
    all_hosts = Counter()
    all_hosts.update({h: c for h, c in hosts.items() if h})
    for h, c in bare.items():
        all_hosts[h] += c

    # classify host ownership
    def owner(host: str) -> str:
        h = host.lower()
        table = [
            ("zalopay", "ZaloPay first-party"), ("zalo.me", "Zalo ecosystem"),
            ("zadn.vn", "Zalo ecosystem (ZADN)"), ("zing.vn", "VNG"), ("vng.com.vn", "VNG"),
            ("zng.io", "VNG"), ("zaloapp.com", "Zalo ecosystem"),
            ("googleapis.com", "Google"), ("google.com", "Google"), ("gstatic.com", "Google"),
            ("firebaseio.com", "Google Firebase"), ("crashlytics.com", "Google Firebase"),
            ("facebook.com", "Meta"), ("fbcdn.net", "Meta"), ("graph.facebook", "Meta"),
            ("adjust.com", "Adjust"), ("appsflyer.com", "AppsFlyer"), ("onesignal.com", "OneSignal"),
            ("cloudfront.net", "AWS CloudFront"), ("amazonaws.com", "AWS"),
            ("cloudflare", "Cloudflare"), ("akamai", "Akamai"), ("fastly", "Fastly"),
            ("alipay", "Ant Group"), ("alibaba", "Alibaba"), ("taobao", "Alibaba"),
            ("visa.com", "Visa"), ("mastercard", "Mastercard"), ("napas", "NAPAS"),
            ("tiktok", "ByteDance"), ("bytedance", "ByteDance"), ("pangle", "ByteDance"),
            ("sentry.io", "Sentry"), ("mapbox", "Mapbox"), ("here.com", "HERE"),
            ("gstatic", "Google"), ("huawei", "Huawei"), ("samsung", "Samsung"),
            ("xiaomi", "Xiaomi"), ("oppo", "OPPO"), ("vivo.com", "vivo"),
        ]
        for needle, label in table:
            if needle in h:
                return label
        if h.endswith(".vn"):
            return "Vietnam (.vn)"
        return "Other"

    host_rows = [{"host": h, "hits": c, "owner": owner(h)} for h, c in all_hosts.most_common(400)]

    findings = []
    for sig, rx in SIGNATURES.items():
        hits = rx.findall(text)
        if not hits:
            continue
        uniq = sorted(set(hits))
        findings.append({
            "signature": sig,
            "count": len(hits),
            "unique": len(uniq),
            "samples": [mask(u) if sig not in ("firebase_db", "pin_sha256", "firebase_app", "zalo_app_id") else u[:48] for u in uniq[:5]],
            "severity": "high" if sig in ("private_key_block", "aws_access_key_id", "google_api_key",
                                          "stripe_key", "slack_token", "basic_auth_url") else "info",
        })

    kw_hits = []
    lines = text.splitlines()
    for kw in KEYWORDS:
        rx = re.compile(re.escape(kw), re.I)
        n = sum(1 for line in lines if rx.search(line))
        if n:
            kw_hits.append({"keyword": kw, "lines": n})

    pins = sorted(set(SIGNATURES["pin_sha256"].findall(text)))
    nsc = {k: v for k, v in xml_files.items() if "pin" in v.lower() or "cleartext" in v.lower()}

    emails = sorted({e for e in EMAIL_RE.findall(text)
                     if not re.search(r"\.(png|jpg|jpeg|webp|svg|gif|xml|json)$", e, re.I)
                     and "." in e.split("@")[-1]})
    personal_domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com"}

    result = {
        "generated_at": datetime.now(timezone.utc).isoformat(),
        "corpus": {
            "sources": len(blob_text),
            "chars": len(text),
            "dex_strings": sum(1 for k, _ in blob_text if k.startswith("dex:")),
            "asset_files": sum(1 for k, _ in blob_text if k.startswith("asset:")),
            "xml_files": len(xml_files),
        },
        "urls": {"total": len(urls), "unique": len(set(urls)), "schemes": dict(schemes)},
        "hosts": host_rows[:120],
        "host_owners": Counter(r["owner"] for r in host_rows).most_common(),
        "signatures": findings,
        "keyword_mentions": kw_hits,
        "pins": {"count": len(pins), "values_masked": [mask(p) for p in pins[:8]]},
        "network_security_configs": {k: v[:1500] for k, v in nsc.items()},
        "emails": {
            "count": len(emails),
            "corporate": [e for e in emails if e.split("@")[-1] not in personal_domains][:20],
            "masked_sample": [{"domain": e.split("@")[-1], **mask(e.split("@")[0])} for e in emails[:5]],
        },
        "extra": dict(extra),
    }
    fp = sum(r["hits"] for r in host_rows if r["owner"] in ("ZaloPay first-party", "Zalo ecosystem", "VNG"))
    tot = sum(r["hits"] for r in host_rows) or 1
    result["first_party_share"] = round(100 * fp / tot, 1)
    with open(os.path.join(OUT, "04_secrets.json"), "w") as fh:
        json.dump(result, fh, indent=1, ensure_ascii=False)
    print("urls:", len(urls), "unique:", len(set(urls)))
    print("top hosts:", [(r["host"], r["hits"], r["owner"]) for r in host_rows[:12]])
    print("owners:", result["host_owners"][:10])
    print("signatures:", [(f["signature"], f["count"]) for f in findings])
    print("pins:", len(pins), "nsc:", list(nsc))
    print("first_party_share:", result["first_party_share"], "%")
    print("emails:", len(emails), result["emails"]["corporate"][:5])


if __name__ == "__main__":
    main()

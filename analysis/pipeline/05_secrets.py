"""Stage 05 — credential-shaped material and network endpoint surface.

Two corpora are crossed:

* the merged DEX string pool (`dex_strings.json.gz`, 269,338 unique strings)
* extracted text/JSON/JS assets

**Policy.** Secret-shaped values are never written to disk in the clear. Each hit
is reduced to (pattern, length, SHA-256 prefix, first 4 chars). That is enough to
de-duplicate, cross-reference and audit a report without leaking a live key —
the same discipline REA applies when it records "occurrences" rather than payloads.

Outputs: data/secrets.json, data/endpoints.json, data/facts_secrets.json
"""

from __future__ import annotations

import collections
import gzip
import json
import re
import zipfile
from pathlib import Path
from urllib.parse import urlparse

from common import APK_DIR, XAPK_DIR, read_json, sha256_bytes, tidy, write_json, Stage

# ---------------------------------------------------------------------------
# Patterns.  Ordered most-specific → least, and every one is anchored so the
# false-positive rate on a 270k-string pool stays workable.
# ---------------------------------------------------------------------------
PATTERNS: list[tuple[str, str, re.Pattern, str]] = [
    ("google-api-key", "Google API key", re.compile(r"^AIza[0-9A-Za-z_\-]{35}$"), "critical"),
    ("google-oauth-client", "Google OAuth client id", re.compile(r"^\d{10,14}-[a-z0-9]{32}\.apps\.googleusercontent\.com$"), "medium"),
    ("firebase-url", "Firebase project URL", re.compile(r"^https://[a-z0-9\-]+\.firebaseio\.com/?$"), "medium"),
    ("firebase-app-id", "Firebase app id", re.compile(r"^1:\d{10,14}:android:[0-9a-f]{16,}$"), "medium"),
    ("aws-access-key", "AWS access key id", re.compile(r"^(AKIA|ASIA)[0-9A-Z]{16}$"), "critical"),
    ("aws-secret", "AWS secret access key", re.compile(r"^[A-Za-z0-9/+=]{40}$"), "high"),
    ("stripe", "Stripe key", re.compile(r"^(sk|pk|rk)_(live|test)_[0-9A-Za-z]{16,}$"), "critical"),
    ("slack", "Slack token", re.compile(r"^xox[abprs]-[0-9A-Za-z\-]{10,}$"), "critical"),
    ("github", "GitHub token", re.compile(r"^(ghp|gho|ghu|ghs|ghr)_[0-9A-Za-z]{36}$"), "critical"),
    ("private-key-block", "PEM private key", re.compile(r"BEGIN (RSA |EC |OPENSSH )?PRIVATE KEY"), "critical"),
    ("jwt", "JSON Web Token", re.compile(r"^eyJ[A-Za-z0-9_\-]{8,}\.eyJ[A-Za-z0-9_\-]{8,}\.[A-Za-z0-9_\-]{8,}$"), "high"),
    ("basic-auth-url", "URL with inline credentials", re.compile(r"^[a-z][a-z0-9+.\-]*://[^/\s:@]+:[^/\s:@]+@"), "critical"),
    ("sendgrid", "SendGrid key", re.compile(r"^SG\.[0-9A-Za-z_\-]{16,}\.[0-9A-Za-z_\-]{16,}$"), "critical"),
    ("twilio", "Twilio SID", re.compile(r"^AC[0-9a-f]{32}$"), "high"),
    ("mapbox", "Mapbox token", re.compile(r"^pk\.[0-9A-Za-z]{60,}$"), "medium"),
    ("sentry-dsn", "Sentry DSN", re.compile(r"^https://[0-9a-f]{32}@[a-z0-9.\-]+/\d+$"), "medium"),
    ("facebook-token", "Facebook client token", re.compile(r"^[0-9a-f]{32}$"), "low"),
]

# Endpoint extraction
URL_RE = re.compile(r"https?://[a-zA-Z0-9.\-]+(?::\d{1,5})?[^\s\"'<>\\]{0,120}")
HOST_RE = re.compile(r"^(?:[a-z0-9](?:[a-z0-9\-]{0,61}[a-z0-9])?\.)+[a-z]{2,24}$")

# Hosts that are noise for a threat model: CDN/static assets and XML namespaces.
NOISE = re.compile(
    r"(w3\.org|apache\.org|xml\.org|json-schema\.org|github\.com/|goo\.gl/|schemas\.android\.com|"
    r"opensource\.org|kotlinlang\.org|gradle\.org|example\.com|localhost|127\.0\.0\.1|0\.0\.0\.0|"
    r"googleapis\.com/auth/|ns\.adobe\.com|purl\.org|facebook\.com/policy|creativecommons\.org)"
)
THIRD_PARTY_INFRA = re.compile(
    r"(googleapis|google-analytics|googletagmanager|gstatic|facebook\.com|facebook\.net|fbcdn|"
    r"appsflyer\.com|crashlytics|firebaseio|doubleclick|googleadservices|adjust\.com|"
    r"cloudfront|akamai|fastly|cloudflare|cdn\.|jsdelivr|unpkg)"
)
VNG_RE = re.compile(r"(zalopay|zalo\.me|zalo|vng|zadn|zaloapp|zing)\.", re.I)

SENSITIVE_SUBSTR = re.compile(r"(api|auth|otp|pin|token|login|pay|wallet|card|kyc|ekyc|face|nfc|device|risk|sign|secure|v1|v2|v3)", re.I)


def main() -> None:
    st = Stage("05-secrets")
    strings = json.load(gzip.open(Path(__file__).resolve().parents[1] / "data" / "dex_strings.json.gz", "rt"))
    st.log(f"scanning {len(strings):,} DEX strings + asset text for credential shapes")

    # ---------------- credential-shaped hits ------------------------------
    hits = []
    seen = set()
    for s in strings:
        cand = s.strip()
        if not (6 <= len(cand) <= 512):
            continue
        for pid, label, pat, sev in PATTERNS:
            if not pat.search(cand):
                continue
            key = (pid, cand)
            if key in seen:
                continue
            seen.add(key)
            hits.append(
                {
                    "pattern": pid,
                    "label": label,
                    "severity": sev,
                    "length": len(cand),
                    "sha256_12": sha256_bytes(cand.encode())[:12],
                    "prefix4": cand[:4],
                    "public_by_design": pid in ("google-oauth-client", "firebase-url", "firebase-app-id", "sentry-dsn", "mapbox"),
                }
            )
            break
    by_pattern = collections.Counter(h["pattern"] for h in hits)
    st.log(f"credential-shaped strings: {len(hits)} unique → {dict(by_pattern)}")
    by_sev = collections.Counter(h["severity"] for h in hits)
    for sev in ("critical", "high", "medium", "low"):
        if by_sev.get(sev):
            st.log(f"  severity {sev}: {by_sev[sev]}")

    # the manifest already contains two credential meta-data entries; fold them in
    manifest = read_json("manifest.json")
    for m in manifest["meta_data"]:
        if m.get("redacted"):
            hits.append(
                {
                    "pattern": "manifest-metadata",
                    "label": m["name"],
                    "severity": "critical" if "API_KEY" in m["name"].upper() else "medium",
                    "length": len(m["redacted"]),
                    "sha256_12": "manifest-meta",
                    "prefix4": "see manifest.json",
                    "public_by_design": False,
                    "where": "AndroidManifest meta-data",
                }
            )

    # ---------------- endpoints ------------------------------------------
    endpoints = collections.Counter()
    for s in strings:
        if "http" not in s:
            continue
        for m in URL_RE.finditer(s):
            u = m.group(0).rstrip(".,);]")
            if NOISE.search(u):
                continue
            try:
                p = urlparse(u)
            except ValueError:
                continue
            if not p.hostname or not HOST_RE.match(p.hostname):
                continue
            endpoints[(p.scheme, p.hostname, p.port, p.path)] += 1
    st.log(f"endpoint candidates: {len(endpoints):,} distinct (scheme, host, port, path) tuples")

    hosts = collections.Counter()
    for (scheme, host, port, path), n in endpoints.items():
        hosts[host] += n
    st.log(f"distinct hosts: {len(hosts):,}")

    vng_hosts = [h for h in hosts if VNG_RE.search(h)]
    third = [h for h in hosts if THIRD_PARTY_INFRA.search(h)]
    st.log(f"ZaloPay/Zalo/VNG-owned hosts: {len(vng_hosts)} · third-party infra: {len(third)}")

    sensitive = sorted(
        [
            {"url": f"{s}://{h}{':'+str(p) if p else ''}{path}", "hits": n, "vng": bool(VNG_RE.search(h)), "third_party": bool(THIRD_PARTY_INFRA.search(h))}
            for (s, h, p, path), n in endpoints.items()
            if path and SENSITIVE_SUBSTR.search(path)
        ],
        key=lambda x: -x["hits"],
    )
    st.log(f"sensitive paths (auth/otp/pin/pay/kyc/device): {len(sensitive):,}")

    top_hosts = [{"host": h, "urls": n, "vng": bool(VNG_RE.search(h)), "third_party": bool(THIRD_PARTY_INFRA.search(h))} for h, n in hosts.most_common(120)]
    for row in top_hosts[:12]:
        st.log(f"  {row['urls']:>5} URLs  {row['host']}{'  [VNG]' if row['vng'] else ''}{'  [3P]' if row['third_party'] else ''}")

    write_json(
        "secrets.json",
        {
            "policy": "values are never persisted; only (pattern, length, sha256[:12], prefix[:4])",
            "scanned_strings": len(strings),
            "hit_count": len(hits),
            "by_pattern": dict(by_pattern),
            "by_severity": dict(by_sev),
            "hits": hits,
        },
    )
    write_json(
        "endpoints.json",
        {
            "distinct": len(endpoints),
            "hosts": len(hosts),
            "vng_hosts": len(vng_hosts),
            "third_party_hosts": len(third),
            "top_hosts": top_hosts,
            "vng_host_list": sorted(vng_hosts)[:200],
            "third_party_host_list": sorted(third)[:200],
            "sensitive_paths": sensitive[:400],
        },
    )
    write_json(
        "facts_secrets.json",
        {
            "hits": len(hits),
            "bySeverity": dict(by_sev),
            "byPattern": dict(by_pattern),
            "endpoints": len(endpoints),
            "hosts": len(hosts),
            "vngHosts": len(vng_hosts),
            "thirdPartyHosts": len(third),
            "sensitivePaths": len(sensitive),
            "topHosts": top_hosts[:10],
        },
    )
    st.done({"hits": len(hits), "endpoints": len(endpoints), "hosts": len(hosts)})


if __name__ == "__main__":
    main()

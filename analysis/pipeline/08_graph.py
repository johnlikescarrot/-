"""Stage 08 — cross-DEX coupling and structural fingerprints.

Because the app ships as **20 DEX images** (Play/AGP splits by method-count) the
class graph is not a single connected component: HotSpot-style "multi-dex"
packing splits *by dependency order*, so a class in `classes4.dex` may legally
reference `classes7.dex`.  What matters for a reverse engineer is therefore:

1. **Where does each domain live?**  A payment flow compressed into one DEX
   tells you which image to decompile first.
2. **Which DEX holds the app's own code vs. vendor code?**  ZaloPay's own
   namespace vs. SDK payload.
3. **How tightly is the mini-program runtime coupled to the wallet?**

We compute per-DEX ownership of the *interesting* namespaces, plus a sharing
graph (which namespaces appear in >1 DEX, i.e. split across images).

Outputs: data/graph.json, data/facts_graph.json
"""

from __future__ import annotations

import collections
import json
import re
from pathlib import Path

from common import APK_DIR, read_json, write_json, Stage

DOMAINS = [
    ("wallet-core", r"^vn\.com\.vng\.zalopay\."),
    ("miniapp", r"^(com\.alibaba\.(griver|ariver)\.|com\.iap\.(ac|android)\.)"),
    ("ads", r"^com\.facebook\.ads\."),
    ("social", r"^(com\.facebook\.(appevents|login|share|internal)\.|com\.zing\.zalo\.)"),
    ("google-stack", r"^(com\.google\.(android|firebase|mlkit|crypto|protobuf|gson)\.|androidx\.)"),
    ("crypto", r"^(org\.bouncycastle\.|com\.google\.crypto\.tink\.|org\.conscrypt\.)"),
    ("payments", r"^(com\.visa\.|com\.iap\.)|napas"),
    ("ekyc", r"^(vn\.ai\.trueid\.|vn\.vng\.)"),
    ("kotlin-runtime", r"^(kotlin|kotlinx)\."),
    ("other", r"."),
]


def domain_of(name: str) -> str:
    for label, pat in DOMAINS:
        if re.match(pat, name):
            return label
    return "other"


def main() -> None:
    st = Stage("08-graph")

    import sys

    sys.path.insert(0, str(Path(__file__).parent))
    from dexread import DexFile

    dex_paths = sorted(APK_DIR.glob("classes*.dex"), key=lambda p: (p.name != "classes.dex", p.name))
    dex_paths += sorted(APK_DIR.glob("assets/*.dex"))

    ownership: dict[str, collections.Counter] = {}
    ns_share: dict[str, set[str]] = collections.defaultdict(set)
    totals = collections.Counter()
    per_dex = []

    for p in dex_paths:
        d = DexFile(p)
        c = collections.Counter()
        n = 0
        for cls in d.classes():
            n += 1
            dom = domain_of(cls.name)
            c[dom] += 1
            totals[dom] += 1
            ns_share[".".join(cls.name.split(".")[:3])].add(p.name)
        ownership[p.name] = c
        per_dex.append({"dex": p.name, "classes": n, "domains": dict(c.most_common())})
        st.log(f"{p.name}: {n:,} classes | mostly {c.most_common(1)[0][0]}")

    shared = {k: sorted(v) for k, v in ns_share.items() if len(v) > 1}
    st.log(f"namespaces split across multiple DEX images: {len(shared):,}")

    # concentrate the reader on the DEX that owns the wallet domain
    wallet_dex = max(
        (row for row in per_dex if row["domains"].get("wallet-core")),
        key=lambda r: r["domains"].get("wallet-core", 0),
        default=None,
    )
    if wallet_dex:
        st.log(
            f"wallet-core concentrates in {wallet_dex['dex']} "
            f"({wallet_dex['domains']['wallet-core']:,} of {totals['wallet-core']:,} classes)"
        )

    miniapp_dex = max(
        (row for row in per_dex if row["domains"].get("miniapp")),
        key=lambda r: r["domains"].get("miniapp", 0),
        default=None,
    )
    if miniapp_dex:
        st.log(f"mini-program runtime concentrates in {miniapp_dex['dex']} ({miniapp_dex['domains']['miniapp']:,} classes)")

    # ---------- config surface (assets/config/zalopay_config.json) ---------
    config = None
    cfg_path = APK_DIR / "assets" / "config" / "zalopay_config.json"
    if cfg_path.exists():
        raw = json.loads(cfg_path.read_text())
        api_names = raw.get("api", {}).get("api_names", [])
        otp = raw.get("otp", {})
        config = {
            "path": "assets/config/zalopay_config.json",
            "bytes": cfg_path.stat().st_size,
            "sections": sorted(raw.keys()),
            "section_count": len(raw),
            "api_names": api_names,
            "api_groups": dict(collections.Counter(n.split("/", 1)[0] for n in api_names)),
            "transport": raw.get("api", {}).get("connector_mode"),
            "api_route": raw.get("api", {}).get("api_route"),
            "network_config": raw.get("network_config"),
            "ssl_error_matchers": raw.get("ssl_consideration"),
            "otp_clipboard": {
                "enabled": otp.get("enable_clipboard_parser"),
                "banks": list(otp.get("bank_pattern", {}).keys()),
                "bank_count": len(otp.get("bank_pattern", {})),
                "zalopay_pattern": otp.get("zalopay_pattern"),
            },
            "kyc": {
                "min_age": raw.get("kyc", {}).get("min_age"),
                "transaction_amount_limit": raw.get("kyc", {}).get("transaction_amount_limit"),
                "banks": raw.get("kyc", {}).get("kyc_bank_list"),
                "id_patterns": {
                    k: raw.get("kyc", {}).get(k)
                    for k in ("cmnd_pattern", "cancuoc_max_length", "passport_pattern", "name_pattern")
                },
            },
            "grpc": raw.get("grpc"),
        }
        st.log(
            f"config surface: {config['section_count']} sections, {len(api_names)} RPC names, "
            f"transport={config['transport']}, clipboard-OTP={'on' if config['otp_clipboard']['enabled'] else 'off'} "
            f"({config['otp_clipboard']['bank_count']} bank templates)"
        )

    # ---------- mini-app catalogue ----------------------------------------
    miniapps = None
    mp_path = APK_DIR / "assets" / "config" / "miniapp_config.json"
    if mp_path.exists():
        raw = json.loads(mp_path.read_text())
        apps = raw.get("data", [])
        cats = collections.Counter(a.get("category_name") for a in apps)
        hosts = collections.Counter(
            (a.get("base_url") or "").split("//")[-1].split("/")[0] for a in apps if a.get("base_url")
        )
        miniapps = {
            "path": "assets/config/miniapp_config.json",
            "bytes": mp_path.stat().st_size,
            "count": len(apps),
            "categories": cats.most_common(),
            "hosts": hosts.most_common(12),
            "sample": [{"id": a.get("app_id"), "name": a.get("app_name"), "category": a.get("category_name")} for a in apps[:8]],
        }
        st.log(f"mini-app catalogue: {len(apps)} apps in {len(cats)} Vietnamese categories, hosted on {len(hosts)} origins")

    # ---------- network security config ------------------------------------
    nsc = None
    nsc_path = APK_DIR / "res" / "xml" / "network_security_config.xml"
    if nsc_path.exists():



        from androguard.core.axml import AXMLPrinter

        xml = AXMLPrinter(nsc_path.read_bytes()).get_xml().decode("utf-8", "replace")
        nsc = {
            "raw": xml,
            "cleartext_permitted": 'cleartextTrafficPermitted="true"' in xml,
            "user_ca_trusted": 'src="user"' in xml,
            "pin_set": "<pin-set" in xml,
            "domains": re.findall(r"<domain[^>]*>([^<]+)</domain>", xml),
        }
        st.log(
            f"network_security_config: cleartext={nsc['cleartext_permitted']}, user-CA={nsc['user_ca_trusted']}, "
            f"certificate-pinning={'yes' if nsc['pin_set'] else 'NO'}"
        )

    payload = {
        "per_dex": per_dex,
        "domain_totals": dict(totals.most_common()),
        "namespaces_shared_across_dex": len(shared),
        "shared_sample": dict(list(shared.items())[:40]),
        "wallet_concentration": wallet_dex,
        "miniapp_concentration": miniapp_dex,
        "config": config,
        "miniapps": miniapps,
        "network_security_config": nsc,
    }
    write_json("graph.json", payload)

    write_json(
        "facts_graph.json",
        {
            "domains": dict(totals.most_common()),
            "perDex": [{"dex": r["dex"], "classes": r["classes"]} for r in per_dex],
            "walletDex": wallet_dex["dex"] if wallet_dex else None,
            "miniappDex": miniapp_dex["dex"] if miniapp_dex else None,
            "rpcNames": len(config["api_names"]) if config else 0,
            "miniApps": miniapps["count"] if miniapps else 0,
            "miniAppCategories": len(miniapps["categories"]) if miniapps else 0,
            "cleartext": nsc["cleartext_permitted"] if nsc else None,
            "userCA": nsc["user_ca_trusted"] if nsc else None,
            "pinning": nsc["pin_set"] if nsc else None,
            "clipboardOtp": config["otp_clipboard"]["enabled"] if config else None,
            "otpBanks": config["otp_clipboard"]["bank_count"] if config else 0,
            "grpc": config["transport"] if config else None,
        },
    )
    st.done({"domains": len(totals), "rpc": len(config["api_names"]) if config else 0})


if __name__ == "__main__":
    main()

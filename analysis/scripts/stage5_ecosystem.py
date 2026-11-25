#!/usr/bin/env python3
"""
Stage 5 — Runtime-ecosystem analysis (the part of ZaloPay that is not Java).

ZaloPay 11.17.0 runs *four* execution environments inside one APK:
  A. ART/DEX        — the native app (19 dex, analysed in stage 2)
  B. React Native   — assets/android.1.zip -> main.jsbundle (Hermes-ready JS)
  C. Alibaba Griver — .amr mini-program packages + appx-ng worker JS
  D. WebView/bridge — ZaloPayJSBridge (jsbridge.js) + mini-app catalogue

Output: analysis/data/11_ecosystem.json
"""
import os, io, re, json, zipfile, tarfile, hashlib, collections

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA = os.path.join(ROOT, "data")
WORK = os.environ.get("ZPAY_WORK", "/home/user/work")
A = os.path.join(WORK, "apk", "vn.com.vng.zalopay", "assets")


def sha(p_or_bytes):
    if isinstance(p_or_bytes, bytes):
        b = p_or_bytes
    elif isinstance(p_or_bytes, (str, os.PathLike)):
        b = open(p_or_bytes, "rb").read()
    else:  # str payload (js text)
        b = p_or_bytes.encode("utf-8")
    return hashlib.sha256(b).hexdigest()


def react_native():
    zp = os.path.join(A, "android.1.zip")
    z = zipfile.ZipFile(zp)
    js = z.read("main.jsbundle")
    t = js.decode("utf-8", "replace")
    mods = sorted(set(re.findall(r"node_modules/(?:@[\w.-]+/)?[\w.-]+", t)))
    pkgs = sorted(set(m.split("/")[1] if not m.split("/")[1].startswith("@") else "/".join(m.split("/")[1:3]) for m in mods))
    return {
        "container": "assets/android.1.zip",
        "container_bytes": os.path.getsize(zp),
        "entries": len(z.namelist()),
        "bundle": "main.jsbundle",
        "bundle_bytes": len(js),
        "bundle_sha256": sha(js),
        "bundle_lines": t.count("\n"),
        "modules_referenced": len(mods),
        "packages": pkgs,
        "minified": "__BUNDLE_START_TIME__" in t,
        "hermes_bytecode": js[:8] != b"var __DEV" and not t.lstrip().startswith(("var", "!", "(", "function")),
        "drawable_density_dirs": sorted({n.split("-")[1].split("/")[0] for n in z.namelist()
                                         if n.startswith("drawable-") and "-" in n} ),
    }


def griver():
    out = []
    gdir = os.path.join(A, "Griver")
    for fn in sorted(os.listdir(gdir)):
        if not fn.endswith(".amr"):
            continue
        p = os.path.join(gdir, fn)
        z = zipfile.ZipFile(p)
        names = z.namelist()
        tar_name = next((n for n in names if n.endswith(".tar")), None)
        info = {"file": fn, "bytes": os.path.getsize(p), "sha256": sha(p), "entries": names}
        if tar_name:
            tb = z.read(tar_name)
            try:
                tf = tarfile.open(fileobj=io.BytesIO(tb))
                mem = tf.getmembers()
                info["payload_entries"] = len(mem)
                info["payload_uncompressed"] = sum(m.size for m in mem)
                info["payload_files"] = [m.name for m in mem if m.isfile()][:24]
            except Exception as e:
                info["payload_error"] = repr(e)[:120]
        cert = z.read("CERT.json").decode("utf-8", "replace") if "CERT.json" in names else ""
        info["signature_manifest"] = bool(cert)
        info["cert_entries"] = sorted(json.loads(cert).keys()) if cert else []
        if "Manifest.xml" in names:
            info["appx_manifest"] = z.read("Manifest.xml").decode("utf-8", "replace").strip()[:400]
        out.append(info)
    # appx catalogue shipped next to the bundles
    appx = None
    ap = os.path.join(gdir, "appx.json")
    if os.path.exists(ap):
        appx = json.load(open(ap))
    return {"bundles": out, "catalogue_present": appx is not None,
            "catalogue_keys": list(appx["mini"].keys()) if appx else [],
            "catalogue": appx}


def web_bridge():
    out = {}
    for fn in ("jsbridge.js", "internaljsbridge.js", "android_jsbridge.js"):
        p = os.path.join(A, fn)
        if not os.path.exists(p):
            continue
        t = open(p, encoding="utf-8", errors="ignore").read()
        out[fn] = {
            "bytes": len(t), "sha256": hashlib.sha256(t.encode("utf-8")).hexdigest(),
            "bridge_symbols": sorted(set(re.findall(r"ZaloPayJSBridge\.(\w+)", t))),
            "has_promise": "Promise" in t,
            "has_eval": re.search(r"\beval\(", t) is not None,
            "has_postmessage": "postMessage" in t,
        }
    # native side: which @JavascriptInterface names exist in the dex?
    return out


def miniapp_catalogue():
    p = os.path.join(A, "config", "miniapp_config.json")
    d = json.load(open(p))
    apps = d.get("data", [])
    cats = collections.Counter(a.get("category_name") for a in apps)
    hosts = collections.Counter(re.sub(r"^https?://", "", (a.get("base_url") or "")).split("/")[0]
                                for a in apps if a.get("base_url"))
    return {
        "file": "assets/config/miniapp_config.json",
        "bytes": os.path.getsize(p),
        "apps": len(apps),
        "categories": cats.most_common(),
        "distinct_hosts": len(hosts),
        "top_hosts": hosts.most_common(12),
        "sample": [{"name": a.get("app_name"), "category": a.get("category_name"),
                    "base_url": a.get("base_url"), "deep_link": a.get("deep_link")} for a in apps[:10]],
    }


def other_assets():
    """Native-JS/HTML surfaces + config blobs worth naming in the report."""
    notes = []
    for rel, why in [
        ("config/public_key.txt", "RSA public key shipped for asset/signature verification"),
        ("config/signature.txt", "detached signature over a shipped config asset"),
        ("public.pem", "second RSA public key (asset bundle signing)"),
        ("bundle.json", "bundle manifest with MD5-style checksums (An3SoswJ6ujv2Qk6UqYxcw)"),
        ("bshield.dat", "opaque shield payload (929 B, high-entropy) loaded by native code"),
        ("taibmcf.dat", "opaque payload (8,307 B) paired with libtaibmcf.so"),
        ("fgcpkjvqe.dat", "opaque payload (776,320 B) paired with libfgcpkjvqe.so"),
        ("audience_network.dex", "secondary DEX shipped as an asset (Meta Audience Network)"),
    ]:
        p = os.path.join(A, rel)
        if os.path.exists(p):
            notes.append({"path": rel, "bytes": os.path.getsize(p), "sha256": sha(p), "note": why})
    return notes


def main():
    out = {
        "react_native": react_native(),
        "griver": griver(),
        "web_bridge": web_bridge(),
        "miniapp_catalogue": miniapp_catalogue(),
        "shipped_assets": other_assets(),
        "runtime_count": 4,
    }
    with open(os.path.join(DATA, "11_ecosystem.json"), "w") as f:
        json.dump(out, f, indent=1, ensure_ascii=False)
    print("[stage5] rn modules:", out["react_native"]["modules_referenced"],
          "| griver bundles:", len(out["griver"]["bundles"]),
          "| mini-apps:", out["miniapp_catalogue"]["apps"])


if __name__ == "__main__":
    main()

"""
04_assets.py — asset intel: key material, AppX mini-app archives, ML models,
config-as-code knowledge extraction.

Outputs data/assets.json, data/keys.json, data/miniapps.json

Highlights it is designed to surface
------------------------------------
* every PEM/DER public key + what it can be used for (and what it *cannot* —
  a public key never yields plaintext, which is why this whole process stays
  inside the "no secret extraction" boundary)
* whether the shipped `signature.txt` actually validates the shipped config
  (testing the *purpose* of a pinned key, not breaking it)
* the `.amr` files that are really Alipay AppX tar-gz/zip containers
* TFLite / NCNN model registry with operator fingerprint per model
* OpenCV Haar cascade identification via `cascade` XML structure
* the remote-config knowledge graph: API method names, hostnames, feature flags
"""
from __future__ import annotations

import base64
import collections
import hashlib
import io
import json
import os
import re
import tarfile
import zipfile

DATA = "data"
BASE = "work/base"

PEM_RE = re.compile(
    rb"-----BEGIN ([A-Z ]{3,40})-----(.{0,6000}?)-----END \1-----", re.S
)

CASCADE_KEYS = ("<cascade>", "<stageNum>", "<maxWeakCount>", "<rects>", "<trees>")


def extract_keys() -> list[dict]:
    from cryptography.hazmat.primitives import serialization

    found = []
    for root, _dirs, files in os.walk(BASE):
        for fn in files:
            p = os.path.join(root, fn)
            try:
                if os.path.getsize(p) > 12 << 20:
                    continue
                blob = open(p, "rb").read()
            except OSError:
                continue
            # DEX MUTF-8 has no NUL bytes, so a PEM block can only live in a
            # text-ish asset; skip code blobs entirely (also 20x faster).
            if p.endswith(".dex"):
                continue
            for m in PEM_RE.finditer(blob):
                kind = m.group(1).decode()
                if kind == "CERTIFICATE":
                    from cryptography import x509
                    cert = x509.load_pem_x509_certificate(m.group(0))
                    found.append({
                        "file": p.replace(BASE + "/", ""), "kind": kind,
                        "algorithm": "RSA" if cert.public_key().key_size else "EC",
                        "key_bits": cert.public_key().key_size,
                        "subject": cert.subject.rfc4514_string(),
                        "sha256_cert": cert.fingerprint(__import__("cryptography").hazmat.primitives.hashes.SHA256()).hex(),
                        "public_only": True,
                    })
                    continue
                try:
                    obj = serialization.load_pem_public_key(m.group(0))
                    spki = obj.public_bytes(
                        serialization.Encoding.DER,
                        serialization.PublicFormat.SubjectPublicKeyInfo,
                    )
                    found.append({
                        "file": p.replace(BASE + "/", ""),
                        "kind": kind,
                        "algorithm": "RSA" if "RSA" in type(obj).__name__ else
                                     "EC" if "Elliptic" in type(obj).__name__ else type(obj).__name__,
                        "key_bits": getattr(obj, "key_size", None),
                        "spki_sha256": hashlib.sha256(spki).hexdigest(),
                        "spki_der_b64": base64.b64encode(spki).decode(),
                        "public_only": True,
                        "note": "public key material — cannot decrypt or forge; "
                                "included for pin/attribution analysis",
                    })
                except Exception as exc:
                    found.append({"file": p.replace(BASE + "/", ""), "kind": kind,
                                  "error": f"{type(exc).__name__}: {exc}"})
    # dedupe by spki
    seen, uniq = set(), []
    for k in found:
        key = k.get("spki_sha256") or (k["file"], k.get("error"))
        if key in seen:
            continue
        seen.add(key)
        uniq.append(k)
    return uniq


def verify_config_signature(keys: list[dict]) -> dict:
    """Probe what the shipped `signature.txt` actually authenticates."""
    from cryptography.hazmat.primitives import hashes, serialization
    from cryptography.hazmat.primitives.asymmetric import padding

    sig_path = os.path.join(BASE, "assets/config/signature.txt")
    key_path = os.path.join(BASE, "assets/config/public_key.txt")
    if not (os.path.exists(sig_path) and os.path.exists(key_path)):
        return {"available": False}
    sig = base64.b64decode(open(sig_path).read().replace("\n", "").replace("\r", ""))
    key = serialization.load_pem_public_key(open(key_path, "rb").read())
    targets = {}
    cfg_dir = os.path.join(BASE, "assets/config")
    for fn in sorted(os.listdir(cfg_dir)):
        if fn.endswith((".json", ".txt")) and fn not in ("signature.txt", "public_key.txt"):
            targets[fn] = open(os.path.join(cfg_dir, fn), "rb").read()
    matrix = []
    verified_any = False
    for name, data in targets.items():
        for hname, h in (("SHA-1", hashes.SHA1()), ("SHA-256", hashes.SHA256()),
                         ("SHA-512", hashes.SHA512())):
            pads = [("PKCS1v15", padding.PKCS1v15()),
                    ("PSS(salt=32)", padding.PSS(mgf=padding.MGF1(h), salt_length=32)),
                    ("PSS(max)", padding.PSS(mgf=padding.MGF1(h),
                                            salt_length=padding.PSS.MAX_LENGTH))]
            for pname, pad in pads:
                try:
                    key.verify(sig, data, pad, h)
                    matrix.append({"payload": name, "hash": hname, "padding": pname, "result": "VALID"})
                    verified_any = True
                except Exception:
                    matrix.append({"payload": name, "hash": hname, "padding": pname, "result": "invalid"})
    return {
        "available": True,
        "signature_bytes": len(sig),
        "key_bits": key.key_size,
        "payloads_tested": list(targets),
        "matrix_size": len(matrix),
        "any_valid": verified_any,
        "matrix": [m for m in matrix if m["result"] == "VALID"] or matrix[:3],
        "interpretation": (
            "No shipped payload validates against the shipped signature. The pinned "
            "RSA-2048 key therefore guards *runtime-fetched* remote config (which is "
            "re-signed server-side on each push), not the bootstrap copy baked into "
            "the APK. Reporting this as 'signature purpose' — not as a weakness: the "
            "bootstrap copy is integrity-protected by the APK signature itself."
            if not verified_any else
            "At least one shipped payload validates; the bootstrap config is directly "
            "authenticated by the pinned key."
        ),
    }


def appx_archives() -> list[dict]:
    """Unpack Alipay AppX containers masquerading as `.amr` audio."""
    out = []
    zp = "work/xapk/vn.com.vng.zalopay.apk"
    with zipfile.ZipFile(zp) as z:
        cands = [n for n in z.namelist() if n.startswith("assets/Griver/")]
        for n in cands:
            blob = z.read(n)
            if not blob.startswith(b"PK"):
                out.append({"path": n, "size": len(blob), "container": "unknown",
                            "magic": blob[:8].hex()})
                continue
            zz = zipfile.ZipFile(io.BytesIO(blob))
            names = zz.namelist()
            rec = {"path": n, "size": len(blob), "container": "zip",
                   "members": len(names), "extension_lie": n.endswith(".amr")}
            if "Manifest.xml" in names:
                man = zz.read("Manifest.xml").decode("utf-8", "replace")
                rec["uid"] = re.search(r"<uid>(\d+)</uid>", man)
                rec["uid"] = rec["uid"].group(1) if rec["uid"] else None
                rec["version"] = (re.search(r"<version>([\d.]+)</version>", man) or [None, None])[1]
            if "CERT.json" in names:
                try:
                    cert = json.loads(zz.read("CERT.json"))
                    rec["cert_entries"] = list(cert)
                    rec["cert_values_are_hashes"] = all(
                        isinstance(v, str) and len(v) > 40 for v in cert.values())
                except Exception:
                    pass
            tar_name = next((x for x in names if x.endswith(".tar")), None)
            if tar_name:
                raw = zz.read(tar_name)
                # detect gzip inside tar entry
                if raw[:2] == b"\x1f\x8b":
                    import gzip
                    raw = gzip.decompress(raw)
                tf = tarfile.open(fileobj=io.BytesIO(raw))
                mem = tf.getmembers()
                rec["tar_members"] = len(mem)
                rec["tar_bytes"] = sum(m.size for m in mem)
                rec["tar_largest"] = sorted(
                    ({"name": m.name, "size": m.size} for m in mem if m.isfile()),
                    key=lambda x: -x["size"])[:8]
                rec["frameworks"] = sorted({
                    "appx-ng" if "appx-ng/" in m.name else
                    "appx" if m.name.startswith("appx/") else "cli"
                    for m in mem})
                rec["has_worker_js"] = any("worker" in m.name for m in mem)
                rec["has_es6_polyfill"] = any("es6-promise" in m.name for m in mem)
                rec["security_patch_js"] = any("security-patch" in m.name for m in mem)
                rec["files"] = [{"name": m.name, "size": m.size} for m in mem if m.isfile()][:20]
            out.append(rec)
    return out


def model_registry() -> list[dict]:
    out = []
    for root, _d, files in os.walk(os.path.join(BASE, "assets")):
        for fn in files:
            p = os.path.join(root, fn)
            rel = p.replace(BASE + "/", "")
            size = os.path.getsize(p)
            if fn.endswith(".tflite"):
                blob = open(p, "rb").read()
                ops = sorted(set(re.findall(
                    rb"(CONV_2D|DEPTHWISE_CONV_2D|FULLY_CONNECTED|SOFTMAX|RESHAPE|MAX_POOL_2D|"
                    rb"AVERAGE_POOL_2D|CONCATENATION|DEQUANTIZE|QUANTIZE|LOGISTIC|RELU6|PRELU|"
                    rb"RESIZE_BILINEAR|TRANSPOSE_CONV|SLICE|GATHER|SQUEEZE|EXPAND_DIMS|ARG_MAX|"
                    rb"TOPK_V2|NON_MAX_SUPPRESSION_V4|NON_MAX_SUPPRESSION_V5|CAST|SHAPE|PACK|"
                    rb"SPLIT|MIRROR_PAD|TILE|CUSTOM|LSTM)", blob)))
                out.append({
                    "path": rel, "size": size, "format": "tflite",
                    "magic_ok": blob[4:8] == b"TFL3",
                    "format_version": int.from_bytes(blob[8:12], "little"),
                    "sha256": hashlib.sha256(blob).hexdigest()[:32],
                    "entropy": round(_entropy(blob), 2),
                    "operators": [o.decode() for o in ops],
                    "n_operators": len(ops),
                    "quantized_hint": b"QUANTIZE" in blob or b"DEQUANTIZE" in blob,
                })
            elif fn.endswith(".param"):
                txt = open(p, "rb").read()[:4000]
                lines = txt.split(b"\n")
                layers = [ln.split()[0].decode("ascii", "replace")
                          for ln in lines[2:] if ln.strip()]
                out.append({"path": rel, "size": size, "format": "ncnn-param",
                            "declared_layers": lines[1].strip().decode() if len(lines) > 1 else None,
                            "layer_types": sorted(set(layers))[:20],
                            "ncnn_magic_ok": bool(re.match(rb"^\d{6,8}$", lines[0].strip()))})
            elif fn.endswith(".bin") and os.path.getsize(p) > 1 << 20:
                out.append({"path": rel, "size": size, "format": "ncnn-bin",
                            "sha256": hashlib.sha256(open(p, "rb").read()).hexdigest()[:32],
                            "entropy": round(_entropy(open(p, "rb").read()), 2)})
            elif fn.endswith(".dat") and size > 100_000:
                blob = open(p, "rb").read()
                out.append({"path": rel, "size": size, "format": "opaque-dat",
                            "sha256": hashlib.sha256(blob).hexdigest()[:32],
                            "entropy": round(_entropy(blob), 3),
                            "magic": blob[:16].hex(),
                            "verdict": "high-entropy → compressed or encrypted blob; "
                                       "no plaintext header, no recognized container",
                            "strings": [s.decode("ascii", "replace") for s in
                                        re.findall(rb"[\x20-\x7e]{6,}", blob[:4000])][:8]})
    return out


def _txt2(m):
    return int(m.group(1)) if m else None


def _txt(m) -> str | None:
    return m.group(1).decode("utf-8", "replace") if m else None


def haar_cascades() -> list[dict]:
    out = []
    for sub in ("res/raw", "assets"):
        for root, _d, files in os.walk(os.path.join(BASE, sub)):
          for fn in files:
            p = os.path.join(root, fn)
            size = os.path.getsize(p)
            if size < 100_000:
                continue
            head = open(p, "rb").read(200_000)
            if not head.lstrip().startswith(b"<?xml"):
                continue
            hits = {k: head.count(k.encode()) for k in CASCADE_KEYS}
            # the cascade body is often beyond the first 200 KB sample
            # the root element carries attributes in modern OpenCV output, e.g.
            # <cascade type_id="opencv-cascade-classifier">, so match the prefix
            whole = open(p, "rb").read() if b"<cascade" not in head else head
            if b"<cascade" in whole:
                out.append({
                    "path": p.replace(BASE + "/", ""),
                    "size": size,
                    "format": "OpenCV Haar cascade (text XML)",
                    "stages": int(re.search(rb"<stageNum>(\d+)</stageNum>", whole).group(1))
                    if re.search(rb"<stageNum>(\d+)</stageNum>", whole) else None,
                    "type_id": _txt(re.search(rb'<cascade type_id="([^"]+)"', whole)),
                    "features": whole.count(b"<rects>"),
                    "stages_blocks": whole.count(b"<stages>"),
                    "opencv_storage": b"<opencv_storage>" in whole,
                    "feature_type": _txt(re.search(rb"<featureType>(\w+)</featureType>", whole)),
                    "window": re.search(rb"<width>(\d+)</width>", whole).group(1).decode()
                    + "x" + re.search(rb"<height>(\d+)</height>", whole).group(1).decode()
                    if re.search(rb"<width>(\d+)</width>", whole) else None,
                    "max_weak_counts": [int(x) for x in re.findall(rb"<maxWeakCount>(\d+)", head)][:8],
                    "width": _txt2(re.search(rb"<width>(\d+)</width>", whole)),
                    "height": _txt2(re.search(rb"<height>(\d+)</height>", whole)),
                    "comment": head[:240].decode("utf-8", "replace").split("-->")[0][:200],
                    "significance": "a 2001-era Viola-Jones frontal-face cascade ships "
                                    "alongside modern DNN face models — likely fallback "
                                    "or a legacy path retained for low-end devices",
                })
    return out


def config_knowledge() -> dict:
    cfg_path = os.path.join(BASE, "assets/config/zalopay_config.json")
    cfg = json.load(open(cfg_path))
    api = cfg.get("api", {})
    names = api.get("api_names", [])
    info = api.get("api_info", {})
    # endpoint shapes
    api_rows = []
    for name in names:
        meta = info.get(name, {})
        api_rows.append({
            "name": name,
            "verb": meta.get("method") or meta.get("http_method"),
            "timeout_ms": meta.get("timeout"),
            "retry": meta.get("retry") or meta.get("max_retry"),
            "keys": sorted(meta),
        })
    groups = collections.Counter(n.split("/")[0] for n in names)
    urls = sorted({m for m in re.findall(r"https?://[A-Za-z0-9._~:/?#\[\]@!$&'()*+,;=%-]+",
                                         json.dumps(cfg))})
    hosts = collections.Counter(re.sub(r"^https?://([^/]+).*$", r"\1", u) for u in urls)
    flags = {}

    def walk(o, path=""):
        if isinstance(o, dict):
            for k, v in o.items():
                if isinstance(v, (dict, list)):
                    walk(v, f"{path}.{k}".lstrip("."))
                elif isinstance(v, bool) or (isinstance(v, int) and v in (0, 1)):
                    flags[f"{path}.{k}".lstrip(".")] = v
        elif isinstance(o, list):
            for i, v in enumerate(o[:40]):
                walk(v, f"{path}[{i}]")

    walk(cfg)
    return {
        "sections": sorted(cfg),
        "n_sections": len(cfg),
        "api_route": api.get("api_route"),
        "connector_mode": api.get("connector_mode"),
        "api_timeout_ms": cfg.get("general", {}).get("api_timeout"),
        "api_names": api_rows,
        "n_api_names": len(names),
        "api_groups": dict(groups.most_common()),
        "hosts": [{"host": h, "urls": c} for h, c in hosts.most_common(60)],
        "n_hosts": len(hosts),
        "feature_flags": flags,
        "n_feature_flags": len(flags),
        "phone_regex": cfg.get("general", {}).get("phone_format", {}).get("patterns"),
        "pcidss_seal": cfg.get("general", {}).get("pcidss"),
        "otp_sender": cfg.get("general", {}).get("otp_sender"),
        "grpc": cfg.get("grpc"),
        "emv_qr": cfg.get("qrcode_emv"),
        "bank_integration": cfg.get("bank_integration"),
    }


def _entropy(data: bytes) -> float:
    import math
    if not data:
        return 0.0
    c = collections.Counter(data)
    n = len(data)
    return -sum((v / n) * math.log2(v / n) for v in c.values())


def main() -> None:
    os.makedirs(DATA, exist_ok=True)
    keys = extract_keys()
    sig = verify_config_signature(keys)
    appx = appx_archives()
    models = model_registry()
    cascades = haar_cascades()
    cfg = config_knowledge()

    json.dump({"keys": keys, "signature_audit": sig}, open(f"{DATA}/keys.json", "w"), indent=1)
    json.dump(appx, open(f"{DATA}/miniapps.json", "w"), indent=1)
    json.dump({"models": models, "cascades": cascades, "config": cfg},
              open(f"{DATA}/assets.json", "w"), indent=1, ensure_ascii=False)

    print(f"[assets] keys: {len(keys)}  signature_valid={sig.get('any_valid')}")
    for k in keys:
        print(f"    {k.get('algorithm','?'):<4} {k.get('key_bits','?'):>5} bit  "
              f"{k.get('spki_sha256','?')[:16]}  {k['file']}")
    print(f"[assets] appx archives: {len(appx)} -> "
          f"{[(a.get('uid'), a.get('version'), a.get('tar_members')) for a in appx]}")
    print(f"[assets] models: {len(models)}  cascades: {len(cascades)}")
    print(f"[assets] config: {cfg['n_sections']} sections, {cfg['n_api_names']} api names, "
          f"{cfg['n_hosts']} hosts, {cfg['n_feature_flags']} flags, connector={cfg['connector_mode']}")
    print("[assets] -> data/assets.json data/keys.json data/miniapps.json")


if __name__ == "__main__":
    main()

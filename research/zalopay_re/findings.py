"""Rule engine: turn raw evidence into severity-rated findings.

Every rule is (id, MASVS category, severity, predicate, text).  Findings are
*observations plus interpretation*, never exploitation guidance: this is a
defensive audit of a shipped artifact, so each row pairs the observable fact
with the control that would remove the risk.

Severity follows the OWASP MASVS/MASTG convention (informational / low /
medium / high / critical) and is deliberately conservative: no row is rated
critical unless it is remotely reachable without user interaction.
"""

from __future__ import annotations

from collections import Counter
from typing import Callable, Dict, List, Optional

SEVERITY_ORDER = ["critical", "high", "medium", "low", "info"]


class Finding:
    def __init__(self, fid: str, title: str, severity: str, masvs: str,
                 detail: str, recommendation: str, evidence: List[str],
                 confidence: str = "high"):
        self.fid = fid
        self.title = title
        self.severity = severity
        self.masvs = masvs
        self.detail = detail
        self.recommendation = recommendation
        self.evidence = evidence
        self.confidence = confidence

    def to_json(self) -> dict:
        return {
            "id": self.fid, "title": self.title, "severity": self.severity,
            "masvs": self.masvs, "detail": self.detail,
            "recommendation": self.recommendation, "evidence": self.evidence,
            "confidence": self.confidence,
        }


def build_findings(d: dict) -> List[Finding]:
    """``d`` is the aggregated evidence dict produced by the pipeline."""
    out: List[Finding] = []
    man = d["manifest"]["base"]
    app = man["application"]
    perms = man["permission_summary"]
    comp = man["components"]
    exported = man["exported_components"]
    signing = d["signing"]
    dex = d["dex"]
    native = d["native"]
    res = d["resources"]
    intel = d["intel"]
    assets = d["assets"]

    # ---------------- defensive posture: things done right ----------------
    if app.get("debuggable") in (None, "false"):
        out.append(Finding(
            "CTRL-01", "Production build flags are clean (not debuggable, backup disabled)",
            "info", "MASVS-RESILIENCE-2",
            f"android:debuggable is {app.get('debuggable') or 'absent'}, allowBackup={app.get('allowBackup')}, "
            f"dataExtractionRules={app.get('dataExtractionRules')}. A debuggable release would allow "
            "JDWP attach and arbitrary file pulls via run-as.",
            "Keep these as release-only properties and assert them in CI (e.g. a bundle-lint gate).",
            [f"application.debuggable={app.get('debuggable')}",
             f"application.allowBackup={app.get('allowBackup')}"]))

    schemes = set(signing["schemes"])
    if {"v2", "v3"} <= schemes:
        v1_modules = signing.get("v1_modules", [])
        out.append(Finding(
            "CTRL-02", "Signature scheme: v2+v3 on every module, v1 only on bundletool splits",
            "info", "MASVS-RESILIENCE-1",
            "All five modules carry a v2 (0x7109871a) and v3 (0xf05368c0) block, so the whole-file "
            "digest covers the base APK. META-INF/BNDLTOOL.RSA (a v1 JAR signature) is present in "
            f"{len(v1_modules)} configuration splits only - a bundletool artefact, not a build "
            "setting. v1 is dead weight under minSdk=26 and is also the surface of the classic "
            "Janus/strip attacks.",
            "No action required; consider v4/fs-verity sidecars for incremental install.",
            [f"signing schemes present: {sorted(schemes)}",
             f"modules with v1: {v1_modules}",
             f"cert SHA-256 {signing['certificates'][0]['sha256'][:32]}…" if signing.get("certificates") else "no cert"]))

    if signing.get("source_stamp"):
        out.append(Finding(
            "INFO-01", "APK carries a Play source stamp (distribution provenance)",
            "info", "MASVS-RESILIENCE-1",
            "The APK Signing Block contains id 0x6dff800d (source stamp), together with the "
            "0x42726577 'Brew' and 0x204b5041 'APK ' padding blocks. These are written by Play "
            "App Signing, so the artifact is a Play-signed build re-packaged by APKPure, not a "
            "rebuild from source.",
            "Treat the APK as authentic-for-distribution but verify the distribution channel.",
            [f"signing block pairs: {[p['id'] for p in signing['block_pairs']]}"]))

    if native.get("summary", {}).get("avg_hardening", 0) >= 4:
        s = native["summary"]
        out.append(Finding(
            "CTRL-03", f"Native libraries are toolchain-hardened (mean {s.get('avg_hardening')}/6)",
            "info", "MASVS-RESILIENCE-2",
            f"{s.get('count')} shared objects: PIE={s.get('pie')}/{s.get('count')}, "
            f"NX={s.get('nx')}/{s.get('count')}, RELRO={s.get('relro')}/{s.get('count')}, "
            f"full-RELRO={s.get('full_relro')}/{s.get('count')}, canary={s.get('canary')}/{s.get('count')}.",
            "Keep -Wl,-z,relro,-z,now,-z,noexecstack and fstack-protector-strong in the NDK build.",
            [f"mean hardening index {s.get('avg_hardening')}/6"]))

    # ---------------- exported attack surface -----------------------------
    unprotected = [c for c in exported if not c.get("permission")]
    if unprotected:
        kinds = Counter(c["kind"] for c in unprotected)
        sample = ", ".join(c["name"].split(".")[-1] for c in unprotected[:6])
        out.append(Finding(
            "MASVS-PLATFORM-1", f"{len(unprotected)} exported components have no permission guard",
            "medium", "MASVS-PLATFORM-1",
            f"{dict(kinds)} of the app's {len(comp)} components are reachable by any installed "
            f"application (exported=true and no android:permission). Examples: {sample}. "
            "For a wallet this is the classic intent-redirection / confused-deputy surface: a "
            "malicious app can start these with attacker-controlled extras and URIs.",
            "Guard every exported entry point with a signature-level permission, validate all "
            "incoming extras/URIs against an allowlist, and re-check the caller uid for "
            "value-moving flows.",
            [f"{c['kind']}: {c['name']}" for c in unprotected[:12]]))
    if man["deep_links"]:
        custom = [dl for dl in man["deep_links"] if dl["scheme"] not in ("https", "http")]
        out.append(Finding(
            "MASVS-PLATFORM-2", f"{len(man['deep_links'])} deep links resolve into exported activities",
            "medium", "MASVS-PLATFORM-2",
            f"App-link domains: {', '.join(man['app_link_domains'])}. "
            f"{len(custom)} use a non-http scheme, which any app can claim, so link parameters "
            "arrive from an untrusted source. Auto-verify protects only the https hosts.",
            "Treat every deep-link parameter as untrusted input, sign/validate hand-off tokens, "
            "and require re-authentication before any transaction is pre-filled from a link.",
            [f"{dl['uri']} -> {dl['component']}" for dl in man["deep_links"][:10]]))
    if man["authorities"]:
        exp_auth = [a for a in man["authorities"] if a["exported"]]
        if exp_auth:
            out.append(Finding(
                "MASVS-PLATFORM-3", "Exported ContentProvider authority",
                "high", "MASVS-PLATFORM-3",
                f"{len(exp_auth)} provider authorities are exported: {', '.join(a['authority'] for a in exp_auth)}.",
                "Set android:exported=false or require a signature permission; never expose file "
                "URIs without grantUriPermissions scoping.",
                [f"{a['authority']} ({a['provider']})" for a in exp_auth]))
        else:
            out.append(Finding(
                "CTRL-04", f"All {len(man['authorities'])} ContentProviders are non-exported",
                "info", "MASVS-PLATFORM-3",
                "Every declared authority (FileProvider, Firebase, Facebook init provider, "
                "WorkManager, etc.) is exported=false; the file-sharing surface is therefore "
                "limited to explicit grantUriPermissions flows.",
                "No action.", [f"{a['authority']}" for a in man["authorities"][:8]]))

    # ---------------- privacy / permission breadth -------------------------
    out.append(Finding(
        "MASVS-PRIVACY-1", f"{perms['total']} permissions requested, {perms['dangerous']} dangerous "
        f"across {len(perms['dangerous_groups'])} groups",
        "medium" if perms["dangerous"] > 8 else "low", "MASVS-PRIVACY-1",
        f"Runtime-sensitive groups: {', '.join(f'{k}({v})' for k, v in perms['dangerous_groups'].items())}. "
        f"Custom/OEM permissions: {len(perms['custom'])}. Each dangerous permission is a "
        "user-visible data channel that must be justified at runtime and in the Play listing.",
        "Apply just-in-time requests, keep a machine-readable permission-to-feature map, and "
        "ship a privacy manifest that ties each SDK to the data it can collect.",
        [f"dangerous: {', '.join(p['name'].split('.')[-1] for p in man['permissions'] if p['dangerous_group'])}"]))
    if "android.permission.QUERY_ALL_PACKAGES" in [p["name"] for p in man["permissions"]]:
        out.append(Finding(
            "MASVS-PRIVACY-2", "QUERY_ALL_PACKAGES broadens app-visibility beyond the <queries> list",
            "low", "MASVS-PRIVACY-2",
            f"The manifest both declares <queries> ({len(man['queries'])} entries) and requests "
            "QUERY_ALL_PACKAGES, which Google Play restricts to specific use cases. Combined with "
            "the PackageManager calls observed in DEX, the app can enumerate installed apps "
            "(fraud/risk signals, but also fingerprinting).",
            "Drop QUERY_ALL_PACKAGES and rely on the explicit <queries> allowlist where possible.",
            [f"<queries>: {', '.join(str(q) for q in man['queries'][:10])}"]))

    # ---------------- dynamic code / mini-program runtime ------------------
    river = intel.get("census", {}).get("vendor_classes", {}).get("Alibaba mPaaS", 0)
    if river:
        out.append(Finding(
            "MASVS-RESILIENCE-3", "Mini-program runtime (mPaaS/Griver) executes remote JavaScript",
            "high", "MASVS-RESILIENCE-3",
            f"{river} classes belong to Alibaba's Ariver/Griver container. Mini-programs are "
            "downloaded and executed at runtime, so the trust boundary moves from the signed APK "
            "to the (unsigned, remotely updated) JS package. Any weakness in package signature "
            "verification or domain allowlisting becomes code execution inside the wallet.",
            "Pin the mini-program package signature to a local trust anchor, enforce an exact "
            "domain allowlist for package distribution, and keep the JS bridge surface minimal "
            "(no raw file/contacts APIs exposed to the container).",
            [f"assets: {', '.join(a['path'] for a in assets['rows'] if 'Griver' in a['path'])[:300]}",
             f"js bundles: {len(assets['js_bundles'])}"]))

    # ---------------- webview / reflection / JNI ---------------------------
    api = dex.get("api_hits", {})
    if api.get("webview_js_iface"):
        out.append(Finding(
            "MASVS-PLATFORM-4", f"WebView.addJavascriptInterface used at {api['webview_js_iface']} call sites",
            "medium", "MASVS-PLATFORM-4",
            "A JavaScript bridge object is injected into a WebView. On API < 17 every public "
            "method was reachable from JS; on modern Android only @JavascriptInterface methods "
            "are, but any URL loaded in that WebView inherits access to those methods - the "
            "textbook route for a cross-app bridge if a URL can be influenced by an attacker.",
            "Restrict the bridge to the minimal method set, validate the origin of every load, "
            "and never mix the bridge WebView with untrusted content.",
            [f"capability census: webview_js_iface={api['webview_js_iface']}, "
             f"webview_eval={api.get('webview_eval', 0)}"]))
    if api.get("dynamic_code"):
        out.append(Finding(
            "MASVS-RESILIENCE-4", f"Dynamic class loading observed ({api['dynamic_code']} call sites)",
            "medium", "MASVS-RESILIENCE-4",
            "DexClassLoader/PathClassLoader/InMemoryDexClassLoader references mean bytecode can "
            "enter the process outside the APK. For a payments app this is legitimate only for "
            "signed plugin modules, and it must be integrity-checked before load.",
            "Verify a signature over every downloaded dex/jar, load from app-private storage "
            "only, and prefer the platform's split/feature delivery.",
            [f"call sites: {api['dynamic_code']}", f"examples: {dex.get('api_examples', {}).get('dynamic_code', [])[:3]}"]))

    # ---------------- transport security ----------------------------------
    nsc = (res.get("res_xml", {}).get("network_security_config") or [])
    if nsc:
        base = nsc[0]
        if not base["pins"]:
            out.append(Finding(
                "MASVS-NETWORK-2", "No declarative certificate pinning in the network security config",
                "low", "MASVS-NETWORK-2",
                f"res/xml/network_security_config.xml declares {len(base['domain_configs'])} "
                f"domain-config (e.g. {', '.join(d['domain'] for d in base['domain_configs'][:3])}) "
                "and no <pin-set>. Any pinning therefore lives in application code (OkHttp "
                "CertificatePinner / trust manager), which is invisible to the platform and to "
                "third-party SDKs that open their own connections.",
                "Publish pins in the NSC so every consumer of the process-wide TLS stack benefits, "
                "with a backup pin and a rotation expiry.",
                [f"file: {base['file']}",
                 f"domain-configs: {[d['domain'] for d in base['domain_configs']]}"]))
        if base.get("base_cleartext") is None:
            out.append(Finding(
                "CTRL-05", "No base-config cleartext exemption (platform default: cleartext blocked)",
                "info", "MASVS-NETWORK-1",
                "cleartextTrafficPermitted is not set at base-config level; because targetSdk=36 "
                "the platform default denies cleartext for all domains not explicitly listed.",
                "No action; keep the allowlist empty.",
                [f"network_security_config base_cleartext={base.get('base_cleartext')}"]))
    pins = list((d.get("strings_intel", {}).get("kinds", {}).get("pinning_hint", {})
                 .get("items", {}) or {}).keys())
    pin_digests = [p for p in pins if p.startswith("sha256/")]
    if api.get("pinning_okhttp") or pin_digests:
        out.append(Finding(
            "CTRL-06", f"App-layer pinning present ({api.get('pinning_okhttp', 0)} OkHttp sites, "
            f"{len(pin_digests)} SHA-256 pin digests in the string pool)",
            "info", "MASVS-NETWORK-2",
            "CertificatePinner / pin digests live in application code, so HTTPS trust is additionally "
            "constrained for the OkHttp stack used by first-party calls. Because the pins are not in "
            "the platform NSC, SDKs with their own HTTP stacks bypass them entirely.",
            "Document a rotation window, keep a backup pin, and cover every host that carries "
            "session or payment data - including mini-program and bank-app hand-off hosts.",
            [f"pin digests found: {len(pin_digests)}", f"sample (truncated): {pin_digests[:3]}"]))

    # ---------------- platform integrity controls --------------------------
    hints = intel.get("integrity_hints", {})
    if hints:
        out.append(Finding(
            "CTRL-07", "Device/app integrity and anti-tamper signals present",
            "info", "MASVS-RESILIENCE-2",
            "String and symbol evidence for: " + ", ".join(f"{k}({v})" for k, v in hints.items()) +
            ". Combined with screenshot protection (FLAG_SECURE) and biometric gating of the "
            "payment flow, the app implements a layered client-side risk posture.",
            "Treat client-side signals as *risk inputs* only; the server must remain the "
            "authority for every value-moving decision.",
            [f"hint counts: {hints}"]))

    # ---------------- supply chain ----------------------------------------
    lib = d["intel"]["libraries"]
    out.append(Finding(
        "MASVS-CODE-1", f"{lib['count']} version-stamped third-party libraries from "
        f"{lib['distinct_groups']} groups",
        "info", "MASVS-CODE-1",
        "META-INF/*.version files give an exact, build-time SBOM. Third-party code is ~"
        f"{100 - intel['census']['vendor_classes'].get('VNG / ZaloPay', 0) * 100 // max(1, dex['totals']['classes'])}% "
        "of the class space, so the app's real attack surface is mostly the SDKs' surface.",
        "Feed these stamps into an SCA pipeline (OSV/CVE matching) and fail the build on known "
        "vulnerable versions.",
        [f"top groups: {list(Counter(r['group'] for r in lib['libraries']).most_common(12))}"]))
    if intel["census"]["unattributed_namespaces"]:
        top_unattr = list(intel["census"]["unattributed_namespaces"].items())[:6]
        out.append(Finding(
            "INFO-02", "Unattributed namespaces remain after vendor matching",
            "info", "MASVS-CODE-1",
            f"{len(intel['census']['unattributed_namespaces'])} namespaces could not be mapped to a "
            "known vendor. The largest is "
            f"{top_unattr[0][0]} ({top_unattr[0][1]} classes) - random-looking identifiers that "
            "suggest either a rebranded SDK or R8 repackaging of a first-party module.",
            "Maintain an internal namespace allowlist so every shipped package is attributable to "
            "a team.",
            [f"{k}: {v}" for k, v in top_unattr]))

    # ---------------- signing key -----------------------------------------
    for cert in signing.get("certificates", []):
        if cert.get("not_after"):
            out.append(Finding(
                "MASVS-RESILIENCE-5", "Signing key is self-signed with a 25-year validity window",
                "low", "MASVS-RESILIENCE-5",
                f"Subject {cert['subject']} / issuer {cert['issuer']}, {cert['key_algorithm']} "
                f"{cert['key_bits']} bit, valid {cert['not_before']} -> {cert['not_after']}, "
                f"SHA-256 {cert['sha256']}. Android does not check expiry for APK verification, "
                "but a single long-lived key with no rotation lineage means key compromise is "
                "unrecoverable for existing installs.",
                "Use Play App Signing with a rotating upload key, and publish a v3 "
                "proof-of-rotation lineage so future keys are auditable.",
                [f"serial {cert.get('serial')}", f"skid {cert.get('subject_key_id')}"]))

    # ---------------- split / packaging notes -----------------------------
    inv = d["inventory"]["summary"]
    out.append(Finding(
        "INFO-03", "Split-APK packaging: base + ABI/locale/density configuration splits",
        "info", "MASVS-CODE-1",
        f"{inv['entries']} entries across {len(inv['entries_by_apk'])} APKs "
        f"({', '.join(f'{k}={v}' for k, v in inv['entries_by_apk'].items())}). "
        "Splits must be signature-identical to the base module or the installer rejects them.",
        "Verify split signatures in CI (apksigner verify on every module).",
        [f"duplicated paths across splits: {len(inv['duplicated_paths'])}"]))

    # ---------------- on-device ML / data minimisation ---------------------
    if assets["models"]:
        out.append(Finding(
            "CTRL-08", f"{len(assets['models'])} on-device ML models shipped "
            f"({sum(m['size'] for m in assets['models']) // 1024} KiB)",
            "info", "MASVS-PRIVACY-3",
            "TensorFlow-Lite / ncnn artefacts plus ML Kit bundled models run inference locally "
            "(OCR, barcode, face, liveness). Keeping inference on-device reduces the amount of "
            "biometric or document imagery leaving the handset.",
            "Keep model updates signed, and document the inference boundary in the privacy notice.",
            [f"{m['path']} ({m['size']} B)" for m in assets["models"][:6]]))

    # ---------------- configuration hygiene -------------------------------
    cfg = assets.get("trust_material", [])
    if cfg:
        out.append(Finding(
            "INFO-04", f"{len(cfg)} trust/config artefacts inside assets/",
            "info", "MASVS-STORAGE-2",
            "Bundled key material is public by definition (anything in the APK can be read). "
            "These files are only meaningful as pins/verification keys, never as secrets.",
            "Audit that no long-term secret is packaged; rotate anything that was.",
            [f"{c['path']} sha256={c['sha256'][:16]}…" for c in cfg[:6]]))

    sev_rank = {s: i for i, s in enumerate(SEVERITY_ORDER)}
    out.sort(key=lambda f: (sev_rank.get(f.severity, 9), f.fid))
    return out


def summarize(findings: List[Finding]) -> dict:
    c = Counter(f.severity for f in findings)
    return {
        "count": len(findings),
        "by_severity": {s: c.get(s, 0) for s in SEVERITY_ORDER},
        "by_masvs": dict(Counter(f.masvs for f in findings).most_common()),
        "risk_score": round(sum({"critical": 40, "high": 15, "medium": 6, "low": 2, "info": 0}[f.severity]
                                for f in findings), 1),
    }

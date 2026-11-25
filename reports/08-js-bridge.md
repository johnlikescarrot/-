# 08 — JS/HTML bridge + REA evidence

19 extracted web assets (100,878 bytes total), analyzed with
`rea-agents@6.5.0 analyze-javascript-application` (latest 6.10.x requires
Node ≥22.23.4; sandbox runs 22.22.3) + manual review.

| file | bytes | lines | bridge symbols |
|---|---:|---:|---|
| `assets__GriverBridge__appx_inline_bootstrap_min.js` | 292 | 2 | AlipayJSBridge |
| `assets__GriverBridge__cover_component.js` | 2,888 | 74 | androidEmbedViewManager |
| `assets__GriverBridge__create_worker.html` | 159 | 9 | — |
| `assets__GriverBridge__h5_bridge.js` | 4,446 | 2 | AlipayJSBridge, androidEmbedViewManager |
| `assets__GriverBridge__index_inline.html` | 972 | 30 | — |
| `assets__GriverBridge__worker.html` | 203 | 10 | AlipayJSBridge |
| `assets__GriverBridge__workerjs.js` | 14,360 | 474 | AlipayJSBridge |
| `assets__GriverErrorPage__griver_custom_h5_page_error.html` | 3,060 | 58 | AlipayJSBridge |
| `assets__GriverErrorPage__griver_page_error.html` | 22,734 | 275 | AlipayJSBridge |
| `assets__GriverErrorPage__mini-program-native-error.html` | 13,859 | 148 | AlipayJSBridge |
| `assets__android_jsbridge.js` | 3,673 | 117 | ZaloPayJSBridge, ZaloPayJSBridgeReady |
| `assets__containererrorpage__griver_custom_h5_page_error.html` | 3,060 | 58 | AlipayJSBridge |
| `assets__containererrorpage__griver_page_error.html` | 9,594 | 179 | AlipayJSBridge |
| `assets__internaljsbridge.js` | 3,890 | 116 | ZaloPayInternalJSBridge, ZaloPayInternalJSBridgeReady |
| `assets__jsbridge.js` | 3,634 | 116 | ZaloPayInternalJSBridge, ZaloPayJSBridge, ZaloPayJSBridgeReady |
| `assets__webapp.js` | 619 | 35 | — |
| `assets__zoom.public` | 450 | 9 | — |
| `res__raw__h5_bridge.js` | 1,875 | 3 | AlipayJSBridge |
| `res__raw__h5_page_error.html` | 11,110 | 210 | AlipayJSBridge |

Findings: `ZaloPayJSBridge` / `ZaloPayInternalJSBridge` (callbackPool,
subscribers, initializeWebBridge), Griver/Alipay `AlipayJSBridge` compat shims
(`appx_inline_bootstrap_min`, `h5_bridge`, `workerjs`, `cover_component`
androidEmbedViewManager), error-page templates, `webapp.js`
`webapp_getNavigation` util probes.

REA evidence: id `ev_717bb3cffd1779a7bbafed8233864771346edde741c7e257c000af1a5f0dc1f7', 37 semantic nodes,
38 edges; kinds: {'javascript-module': 9, 'javascript-asset': 9, 'unknown': 9, 'artifact': 9, 'electron-renderer': 1}.
Full 5.8 MB evidence: `reports/raw/rea-js-analyze.json`; trimmed:
`evidence/rea-js-summary.json`, `evidence/jsbridge-inventory.json`.

REA gaps (documented): `analyze` needs Ghidra/Hopper/IDA providers,
`inspect-android-class` needs caller-supplied jadx-headless-mcp 0.7.1 JAR —
see `reports/raw/rea-apk-analyze.json`, `reports/raw/rea-app-class.json`.

/**
 * Typed, read-only access layer over the forensic evidence in `film/data/`.
 *
 * Every figure displayed on screen must be pulled through this module so that
 * the film can never drift from the machine-verified evidence. The shapes below
 * mirror exactly what tools/collect.py, tools/extras.py and tools/triage.py
 * emit.
 */
import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
const HERE = path.dirname(fileURLToPath(import.meta.url));
export const DATA_DIR = path.resolve(HERE, "../../data");
function load(file) {
    const p = path.join(DATA_DIR, file);
    return JSON.parse(fs.readFileSync(p, "utf8"));
}
/* ------------------------------------------------------------------- loader */
let cache = null;
export function loadEvidence() {
    if (cache)
        return cache;
    cache = {
        container: load("01_container.json"),
        manifest: load("02_manifest.json"),
        dex: load("03_dex.json"),
        native: load("04_native.json"),
        assets: load("05_assets.json"),
        strings: load("06_strings.json"),
        sdks: load("07_sdks.json"),
        firstParty: load("08_firstparty.json"),
        nsc: load("09_nsc.json"),
        exported: load("10_exported.json"),
        firstPartyMap: load("11_firstparty_map.json"),
        nativeDeep: load("12_native_deep.json"),
        crypto: load("13_crypto.json"),
        triage: load("14_triage.json"),
        permissions: load("15_permissions.json"),
    };
    return cache;
}
/* ------------------------------------------------------------------ helpers */
export const fmtInt = (n) => n.toLocaleString("en-US");
export const fmtBytes = (n) => {
    if (n > 1e9)
        return (n / 1e9).toFixed(2) + " GB";
    if (n > 1e6)
        return (n / 1e6).toFixed(1) + " MB";
    if (n > 1e3)
        return (n / 1e3).toFixed(1) + " KB";
    return n + " B";
};
export const fmtPct = (n, d = 1) => n.toFixed(d) + "%";
export function keyFindings(e) {
    const m = e.manifest;
    return {
        package: m.package,
        version: m.version_name,
        versionCode: m.version_code,
        targetSdk: m.target_sdk,
        minSdk: m.min_sdk,
        artifactBytes: e.container.xapk_size,
        classes: e.dex.total_classes,
        methods: e.dex.total_methods,
        fields: e.dex.total_fields,
        strings: e.dex.total_strings,
        dexFiles: e.dex.dex_files.length,
        nativeLibs: e.native.count,
        nativeBytes: e.native.total_bytes,
        stripped: e.native.libs.filter((l) => !l.has_symtab).length,
        activities: m.counts.activities,
        services: m.counts.services,
        receivers: m.counts.receivers,
        providers: m.counts.providers,
        permissions: m.counts.permissions,
        exported: e.exported.count,
        exportedUnprotected: e.exported.unprotected.length,
        sdks: e.sdks.count,
        obfuscatedShare: e.dex.obfuscation.share_pct,
        firstPartyClasses: e.firstParty.count,
        hosts: e.strings.hosts_total,
        certSha256: m.certificate.sha256,
        cleartextGlobal: e.nsc.cleartext_global,
        userCaTrusted: e.nsc.user_ca_trusted,
        hasPinSet: e.nsc.has_pin_set,
        capabilitiesPresent: e.triage.verdict.capabilities_present,
        capabilitiesTotal: e.triage.verdict.capabilities_total,
        absentCapabilities: e.triage.verdict.absent_code_capabilities,
        dangerousPermissions: e.permissions.dangerous,
    };
}
/** Top-N token budget for chart scenes. */
export function topEntries(rec, n) {
    return Object.entries(rec)
        .sort((a, b) => b[1] - a[1])
        .slice(0, n);
}

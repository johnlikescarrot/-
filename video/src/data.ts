/** Loads the analysis JSON emitted by analysis/tools/*.py and derives film facts. */

import { readFileSync } from "node:fs";
import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";
import type {
  DataSet, DexCensus, NativeCensus, PayloadCensus, Provenance, Secrets, SecurityCensus,
} from "./types.ts";

const HERE = dirname(fileURLToPath(import.meta.url));
export const REPO = join(HERE, "..", "..");
export const DATA_DIR = join(REPO, "data");

const load = <T>(f: string): T => JSON.parse(readFileSync(join(DATA_DIR, f), "utf8")) as T;

export const data: DataSet = {
  provenance: load<Provenance>("01_provenance.json"),
  dex: load<DexCensus>("02_dex.json"),
  native: load<NativeCensus>("03_native.json"),
  secrets: load<Secrets>("04_secrets.json"),
  security: load<SecurityCensus>("05_security.json"),
  payloads: load<PayloadCensus>("06_payloads.json"),
};

/** Everything the script cites, resolved once so numbers can never drift. */
export const facts = (() => {
  const { provenance: p, dex, native: n, secrets: s, security: sec, payloads: pl } = data;
  const base = p.apks[0];
  const splits = p.apks.slice(1);
  const dexSizes = [...dex.dex].sort((a, b) => b.size - a.size);
  const biggestLib = [...n.libs].sort((a, b) => b.size - a.size)[0];
  const themes = Object.entries(sec.themes).sort((a, b) => b[1].count - a[1].count);
  const owners = s.host_owners.filter(([, v]) => v > 0);
  return {
    /** container */
    containerBytes: p.container.bytes,
    containerSha: p.container.sha256,
    xapkSha: p.container.inner.sha256,
    versionName: p.manifest.application_attrs["android:versionName"] ?? "11.17.0",
    versionCode: p.manifest.application_attrs["android:versionCode"] ?? "1102758",
    packageName: p.manifest.application_attrs["package"] ?? "vn.com.vng.zalopay",
    compileSdk: p.manifest.application_attrs["android:compileSdkVersion"] ?? "36",
    minSdk: "26",
    targetSdk: "36",

    /** apks */
    base,
    splits,
    splitNames: splits.map((x) => x.name),
    totalInstalled: p.apks.reduce((a, x) => a + x.uncompressed_bytes, 0),
    splitShare: splits.reduce((a, x) => a + x.bytes, 0) / p.container.inner.bytes,
    nativeSplitBytes: splits[0].bytes,
    nativeSplitShare: splits[0].bytes / p.container.inner.bytes,

    /** manifest */
    permissions: p.manifest.permissions.length,
    riskyPermissions: Object.keys(p.risk_permissions).length,
    components: p.manifest.components,
    exportedCount: Object.values(p.manifest.components)
      .flat()
      .filter((c) => c.exported === true).length,
    activities: p.manifest.components.activity?.length ?? 0,
    providers: p.providers,
    queries: p.manifest.queries.length,
    metaData: p.manifest.meta_data_count,
    manifestBytes: p.manifest.xml_bytes,

    /** dex */
    dexFiles: dex.totals.dex_files,
    classes: dex.totals.classes,
    strings: dex.totals.unique_strings,
    ownClasses: dex.totals.own_classes,
    ownSharePct: (100 * dex.totals.own_classes) / dex.totals.classes,
    nativeMethods: dex.totals.native_methods,
    dexSizes,
    biggestDex: dexSizes[0],
    dexTotalBytes: dex.dex.reduce((a, x) => a + x.size, 0),
    thirdPartyRoots: dex.third_party_roots,
    ownNamespaces: dex.own_namespace_top,
    assetDex: dex.totals.asset_dex,

    /** native */
    libCount: n.lib_count,
    libBytes: n.total_bytes,
    hardeningPct: n.totals.hardening_pct,
    relroExceptions: n.totals.relro_exceptions,
    jniSymbols: n.totals.jni_symbols,
    exportedSymbols: n.totals.exported_symbols,
    protectorLibs: n.totals.obfuscated_libs,
    protectorBytes: n.totals.protector_bytes,
    biggestLib,
    libsBySize: [...n.libs].sort((a, b) => b.size - a.size),

    /** network */
    urls: s.urls,
    firstPartyShare: s.first_party_share,
    topHosts: s.hosts.slice(0, 10),
    hostOwners: owners,
    pins: s.pins.count,
    nscCleartext: /base-config cleartextTrafficPermitted="true"/.test(
      s.network_security_configs["res/xml/network_security_config.xml"] ?? "",
    ),

    /** security */
    themes,
    themeCount: (k: string) => sec.themes[k]?.count ?? 0,
    themeSamples: sec.theme_samples,
    sdkCensus: sec.sdk_census,
    limits: sec.limits,

    /** payloads */
    entriesScanned: pl.entries_scanned,
    hiddenExec: pl.hidden_executables,
    publicKeys: pl.public_keys,
    nestedBundles: pl.nested_bundles,
    modelFiles: pl.model_files,
    jsBridge: pl.js_bridge,
    contentTypes: pl.content_types,
    assetsLargest: pl.assets_largest,
  };
})();

export type Facts = typeof facts;

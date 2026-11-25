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

function load<T>(file: string): T {
  const p = path.join(DATA_DIR, file);
  return JSON.parse(fs.readFileSync(p, "utf8")) as T;
}

/* ------------------------------------------------------------------ shapes */

export interface ContainerEntry {
  name: string;
  size: number;
  compressed: number;
  ratio: number;
  method: number;
  crc: string;
}

export interface Evidence {
  container: {
    xapk_path: string;
    xapk_size: number;
    xapk_sha256: string;
    entry_count: number;
    by_top_level: Record<string, { files: number; bytes: number }>;
    by_extension: Record<string, { files: number; bytes: number }>;
    xapk_entries: ContainerEntry[];
    outer?: { name: string; size: number; sha256: string; entries: ContainerEntry[] };
  };
  manifest: {
    package: string;
    version_name: string;
    version_code: string;
    min_sdk: string;
    target_sdk: string;
    app_name: string;
    main_activity: string;
    counts: Record<string, number>;
    permissions: string[];
    activities: string[];
    services: string[];
    receivers: string[];
    providers: string[];
    libraries: string[];
    features: string[];
    files: number;
    signed_v2: boolean;
    signed_v3: boolean;
    signed_v1: boolean;
    xml_attributes: Record<string, string>;
    exported_declared_true: Record<string, number>;
    certificate: {
      sha256: string;
      sha1: string;
      md5: string;
      subject: string;
      issuer: string;
      serial: string;
      not_before: string;
      not_after: string;
      sig_alg: string;
      pubkey_bits: number;
      pubkey_type: string;
      der_len: number;
    };
  };
  dex: {
    total_classes: number;
    total_methods: number;
    total_fields: number;
    total_strings: number;
    dex_files: { name: string; bytes: number; classes: number; methods: number; fields: number; strings: number }[];
    packages: Record<string, number>;
    class_count_by_root: Record<string, number>;
    obfuscation: { short_named_classes: number; share_pct: number };
  };
  native: {
    count: number;
    total_bytes: number;
    libs: NativeLib[];
  };
  nativeDeep: {
    libs: NativeLib[];
    signal_census: Record<string, Record<string, boolean>>;
    signals: string[];
  };
  assets: {
    assets_count: number;
    assets_bytes: number;
    assets_by_ext: Record<string, number>;
    largest_assets: { path: string; bytes: number }[];
    res_files: number;
    res_by_ext: Record<string, number>;
    arsc_bytes: number;
    total_files_in_base_apk: number;
    config_public_key?: { pem_head: string; pem_tail: string; lines: number; declared_bits: number | null };
    config_quotes: Record<string, string>;
  };
  strings: {
    string_total: number;
    unique: number;
    hosts_zalopay: Record<string, number>;
    hosts_third_party: Record<string, number>;
    hosts_total: number;
    keyword_hits: Record<string, number>;
    crypto_algorithms: Record<string, number>;
    class_like_strings: number;
  };
  sdks: { count: number; sdks: { artifact: string; version: string | null; group: string | null; name: string | null }[] };
  firstParty: { count: number; top_subpackages: Record<string, number>; feature_domains: string[]; classes: string[] };
  firstPartyMap: {
    count: number;
    methods: number;
    domains: Record<string, number>;
    classes: { class: string; dex: string; methods: number; fields: number; super: string; ifaces: string[] }[];
    packages: string[];
  };
  nsc: {
    raw_xml: string;
    domains: string[];
    cleartext_flags: string[];
    trust_anchors: string[];
    has_pin_set: boolean;
    cleartext_global: boolean;
    user_ca_trusted: boolean;
    finding: string;
  };
  exported: {
    count: number;
    by_type: Record<string, number>;
    components: { type: string; name: string; permission: string | null; actions: string[]; priority: string | null }[];
    unprotected: string[];
  };
  crypto: { type_presence: Record<string, number> };
  triage: {
    code_capabilities: Record<string, Capability>;
    string_capabilities: Record<string, StringCapability>;
    verdict: {
      classes_defined: number;
      string_literals: number;
      capabilities_present: number;
      capabilities_total: number;
      absent_code_capabilities: string[];
      absent_string_capabilities: string[];
      statement: string;
    };
  };
  permissions: {
    total: number;
    dangerous: number;
    vendor_specific: number;
    groups: Record<string, number>;
    verified: number;
    rows: {
      permission: string;
      short: string;
      group: string | null;
      dangerous: boolean;
      probe_capability: string | null;
      verified_in_code: boolean | null;
      vendor_specific: boolean;
    }[];
  };
}

export interface NativeLib {
  name: string;
  bytes: number;
  sha256: string;
  machine: string;
  sections: number;
  dynsym: number;
  symtab: number;
  has_symtab: boolean;
  rodata_entropy: number;
  jni_symbols: string[];
  jni_count: number;
  needed: string[];
  purpose?: string;
}

export interface Capability {
  evidence: "class_definition" | "platform_api_reference";
  present: boolean;
  classes: number;
  defined_in_apk: number;
  referencing_classes: number;
  attribution: Record<string, number>;
  examples: string[];
}

export interface StringCapability {
  evidence: "string_literal";
  present: boolean;
  tokens: Record<string, number>;
}

/* ------------------------------------------------------------------- loader */

let cache: Evidence | null = null;

export function loadEvidence(): Evidence {
  if (cache) return cache;
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

export const fmtInt = (n: number): string => n.toLocaleString("en-US");
export const fmtBytes = (n: number): string => {
  if (n > 1e9) return (n / 1e9).toFixed(2) + " GB";
  if (n > 1e6) return (n / 1e6).toFixed(1) + " MB";
  if (n > 1e3) return (n / 1e3).toFixed(1) + " KB";
  return n + " B";
};
export const fmtPct = (n: number, d = 1): string => n.toFixed(d) + "%";

export function keyFindings(e: Evidence) {
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
export function topEntries(rec: Record<string, number>, n: number): [string, number][] {
  return Object.entries(rec)
    .sort((a, b) => b[1] - a[1])
    .slice(0, n);
}

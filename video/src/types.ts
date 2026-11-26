/** Shared types: the data contracts produced by analysis/tools/*.py. */

export interface Container {
  file: string;
  bytes: number;
  sha256: string;
  inner: { file: string; bytes: number; sha256: string };
}

export interface ApkEntry {
  name: string;
  bytes: number;
  sha256: string;
  entries: number;
  uncompressed_bytes: number;
  compression_ratio: number;
  signing: { present: boolean; schemes?: string[]; central_directory_offset?: number };
  by_extension: Record<string, { count: number; bytes: number }>;
  largest: { name: string; bytes: number }[];
}

export interface Component {
  name: string;
  exported: boolean | null;
  permission?: string;
  authorities?: string;
}

export interface Provenance {
  generated_at: string;
  container: Container;
  apks: ApkEntry[];
  manifest: {
    permissions: string[];
    uses_features: [string, string][];
    application_attrs: Record<string, string>;
    components: Record<string, Component[]>;
    meta_data_count: number;
    queries: string[];
    declared_actions: string[];
    xml_bytes: number;
    xml_chars: number;
  };
  assets_summary: {
    asset_files: number;
    hermes_bundles: string[];
    js_bundles: string[];
    dex_count: number;
    so_in_base: string[];
    resource_arsc_bytes: number;
  };
  risk_permissions: Record<string, string>;
  providers: Component[];
  findings: { id: string; claim: string; evidence: unknown; detail: string }[];
}

export interface DexEntry {
  name: string;
  size: number;
  sha256: string;
  string_ids: number;
  type_ids: number;
  proto_ids: number;
  field_ids: number;
  method_ids: number;
  class_defs: number;
  classes: string[];
  native_method_count: number;
}

export interface DexCensus {
  totals: {
    dex_files: number;
    asset_dex: string[];
    classes: number;
    unique_classes: number;
    unique_strings: number;
    native_methods: number;
    own_classes: number;
    own_packages: number;
  };
  dex: DexEntry[];
  own_namespace_top: [string, number][];
  third_party_roots: [string, number][];
  own_packages_top: [string, number][];
  native_libraries_referenced: string[];
}

export interface NativeLib {
  name: string;
  short: string;
  size: number;
  sha256: string;
  bits: number;
  machine: string;
  type: string;
  soname: string | null;
  needed: string[];
  exported_count: number;
  jni_export_count: number;
  pie: boolean;
  nx: boolean;
  relro: string;
  bind_now: boolean;
  canary: boolean;
  stripped: boolean;
  family: string;
  vendor: string;
  obfuscated_name: boolean;
  descriptor_density: number;
}

export interface NativeCensus {
  lib_count: number;
  total_bytes: number;
  libs: NativeLib[];
  dependency_graph: { from: string; to: string[] }[];
  jni_index: string[];
  totals: {
    hardened_full: number;
    hardening_pct: number;
    relro_exceptions: string[];
    unstripped: string[];
    canary_free: string[];
    exported_symbols: number;
    jni_symbols: number;
    obfuscated_libs: string[];
    protector_bytes: number;
    protector_density: Record<string, number>;
    largest: [number, string][];
  };
}

export interface Secrets {
  corpus: { sources: number; chars: number; dex_strings: number; asset_files: number; xml_files: number };
  urls: { total: number; unique: number; schemes: Record<string, number> };
  hosts: { host: string; hits: number; owner: string }[];
  host_owners: [string, number][];
  signatures: { signature: string; count: number; unique: number; severity: string }[];
  pins: { count: number; values_masked: { prefix: string; length: number; sha256: string }[] };
  network_security_configs: Record<string, string>;
  emails: { count: number; corporate: string[] };
  first_party_share: number;
}

export interface SecurityCensus {
  corpus: { dex_files: number; unique_strings: number };
  themes: Record<string, { note: string; count: number; unique: number; dex: Record<string, number> }>;
  theme_samples: Record<string, string[]>;
  sdk_census: { sdk: string; string_hits: number }[];
  limits: string[];
}

export interface PayloadCensus {
  entries_scanned: number;
  content_types: [string, number][];
  hidden_executables: { apk: string; path: string; bytes: number; hidden_because: string; loadable: string }[];
  extension_mismatches: { apk: string; path: string; bytes: number; claimed: string; actual: string }[];
  model_files: { apk: string; path: string; bytes: number; kind: string }[];
  assets_largest: { path: string; bytes: number }[];
  nested_bundles: { path: string; bytes: number }[];
  public_keys: { path: string; bytes: number; kind: string; sha256: string }[];
  js_bridge: { path: string; bytes: number; postMessage: number; jsapi: number; needs_review: boolean }[];
}

export interface DataSet {
  provenance: Provenance;
  dex: DexCensus;
  native: NativeCensus;
  secrets: Secrets;
  security: SecurityCensus;
  payloads: PayloadCensus;
}

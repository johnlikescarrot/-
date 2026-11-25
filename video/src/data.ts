/** Typed view over research/data/film.json — the only data source for the film. */
import { readFileSync } from 'node:fs';
import { fileURLToPath } from 'node:url';
import { dirname, resolve } from 'node:path';

const here = dirname(fileURLToPath(import.meta.url));
export const ROOT = resolve(here, '../..');

export interface Subject {
  app: string; package: string; version: string; version_code: string;
  min_sdk: string; target_sdk: string; main_activity: string;
  sha256_xapk: string; sha256_outer: string; bytes_outer: number; bytes_xapk: number;
}
export interface Container {
  splits: number; entries: number; uncompressed: number; dex_files: number;
  native_libs: number; xml: number; split_names: string[]; misaligned_base: number;
}
export interface DexRow { dex: string; bytes: number; version: string; strings: number; types: number; methods: number; classes: number }
export interface Section { dex: string; bytes: number; code: number; index_bytes: number; method_capacity_used: number }
export interface Code {
  classes: number; methods: number; strings: number; types: number; dex_bytes: number; dex: number;
  method_limit_dexes: string[]; largest_dex: DexRow; obf_pkg_pct: number; obf_class_pct: number;
  native_methods: number; sections: Section[];
}
export interface Manifest {
  activities: number; services: number; receivers: number; providers: number;
  permissions: number; dangerous: string[]; features: string[];
}
export interface Sdk { sdk: string; vendor: string; evidence: number }
export interface Endpoints {
  hosts: number; first_party: { host: string; hits: number }[]; first_party_count: number;
  classes: Record<string, number>; api_paths: number;
}
export interface Lib { lib: string; bytes: number; cat: string; entropy: number }
export interface Native {
  count: number; bytes: number; stripped_pct: number;
  hardening: Record<string, number>; randomised_names: string[]; libraries: Lib[];
  max_entropy: { lib: string; value: number };
}
export interface Resources {
  arsc_bytes: number; arsc_strings: number; ml_models: number; ml_bytes: number;
  ml_list: string[]; security_assets: number;
}
export interface Security {
  cert_subject: string; cert_sha256: string; cert_not_before: string; cert_not_after: string;
  key: { type: string; bits: number }; sig_verified: boolean; v2_pair_length: number; block_size: number;
  hardening: Record<string, string[]>; pqc: string[];
  hex: { block_offset: number; head: string; pair_offset: number; pair_tail: string; declared_vs_actual: [number, number] };
}
export interface Miniapps { count: number; categories: [string, number][]; deep_link: string; hosts: [string, number][] }
export interface PinSurface { note: string; key_discovery: string[]; session_and_otp: string[]; ekyc: string[]; cashier: string[]; pci: string[] }
export interface Provenance { outer_zip: string; xapk_sha256: string; stages: number; tooling: string[] }

export interface FilmData {
  subject: Subject; container: Container; code: Code; manifest: Manifest; sdks: Sdk[];
  sdk_count: number; endpoints: Endpoints; native: Native; resources: Resources;
  security: Security; miniapps: Miniapps; pin_surface: PinSurface;
  families: [string, number][]; provenance: Provenance;
}

export const data: FilmData = JSON.parse(
  readFileSync(resolve(ROOT, 'research/data/film.json'), 'utf8'),
) as FilmData;

export const fmt = (n: number): string => n.toLocaleString('en-US');
export const pct = (n: number): string => `${n.toFixed(1)}%`;
export const mib = (n: number): string => `${(n / 2 ** 20).toFixed(1)} MiB`;
export const kib = (n: number): string => n >= 2 ** 20 ? `${(n / 2 ** 20).toFixed(2)} MiB` : `${(n / 1024).toFixed(0)} KiB`;
export const shortHash = (h: string, n = 16): string => `${h.slice(0, n)}…`;

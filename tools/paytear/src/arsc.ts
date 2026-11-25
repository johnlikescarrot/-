/**
 * Android resources.arsc (RES_TABLE_TYPE) decoder.
 *
 * Extracts packages, type specifications, type chunks, configuration buckets
 * (locale / density / sdk), entry names and simple values. Enough to build a
 * locale inventory, resolve @0x7f… references and audit string tables.
 */
import { readStringPool } from "./axml.js";

const RES_TABLE_TYPE = 0x0002;
const RES_TABLE_PACKAGE_TYPE = 0x0200;
const RES_TABLE_TYPE_TYPE = 0x0201;
const RES_TABLE_TYPE_SPEC_TYPE = 0x0202;
const RES_STRING_POOL_TYPE = 0x0001;
const NO_ENTRY = 0xffffffff;

export interface ResValue {
  type: number;
  data: number;
  string?: string;
}

export interface ResEntry {
  key: string;
  value?: ResValue;
  complex: boolean;
}

export interface ResConfig {
  size: number;
  locale: string;
  density: number;
  sdkVersion: number;
  orientation?: number;
  raw: Buffer;
}

export interface ResType {
  id: number;
  name: string;
  configs: {
    config: ResConfig;
    entries: Map<number, ResEntry>;
  }[];
  entryCount: number;
}

export interface ResPackage {
  id: number;
  name: string;
  typeStrings: string[];
  keyStrings: string[];
  types: ResType[];
}

export interface ResTable {
  packages: ResPackage[];
  globalStrings: string[];
}

function u16(b: Buffer, o: number) { return b.readUInt16LE(o); }
function u32(b: Buffer, o: number) { return b.readUInt32LE(o); }

function parseConfig(b: Buffer, off: number): ResConfig {
  const size = u32(b, off);
  const buf = b.subarray(off, off + Math.max(size, 4));
  const lang = buf.subarray(8, 10).toString("latin1").replace(/\0/g, "");
  const country = buf.subarray(10, 12).toString("latin1").replace(/\0/g, "");
  let density = 0;
  if (size >= 16) density = u16(buf, 14);
  let sdkVersion = 0;
  if (size >= 26) sdkVersion = u16(buf, 24);
  let locale = "default";
  if (lang && lang !== "any") locale = country ? `${lang}-${country}` : lang;
  return { size, locale, density, sdkVersion, raw: Buffer.from(buf) };
}

const poolReaderFor = (buffer: Buffer) => ({
  b: buffer,
  u8: (o: number) => buffer.readUInt8(o),
  u16: (o: number) => buffer.readUInt16LE(o),
  u32: (o: number) => buffer.readUInt32LE(o),
});

export function parseArsc(buffer: Buffer): ResTable {
  if (u16(buffer, 0) !== RES_TABLE_TYPE) throw new Error("not an ARSC (RES_TABLE_TYPE) file");
  const headerSize = u16(buffer, 2);
  const total = u32(buffer, 4);
  const end = Math.min(total, buffer.length);
  let globalStrings: string[] = [];
  const packages: ResPackage[] = [];

  let p = headerSize;
  while (p + 8 <= end) {
    const chunkType = u16(buffer, p);
    const chunkSize = u32(buffer, p + 4);
    if (chunkSize < 8 || p + chunkSize > end + 8) break;

    if (chunkType === RES_STRING_POOL_TYPE) {
      globalStrings = readStringPool(poolReaderFor(buffer) as any, p).strings;
    } else if (chunkType === RES_TABLE_PACKAGE_TYPE) {
      const pkg: ResPackage = {
        id: u32(buffer, p + 8),
        name: buffer.subarray(p + 12, p + 12 + 256).toString("utf16le").replace(/\0.*$/s, ""),
        typeStrings: [],
        keyStrings: [],
        types: [],
      };
      const typeStringsOff = u32(buffer, p + 268);
      const keyStringsOff = u32(buffer, p + 276);
      const pkgHeaderSize = u16(buffer, p + 2);

      const poolReader = poolReaderFor(buffer) as any;
      if (typeStringsOff) pkg.typeStrings = readStringPool(poolReader, p + typeStringsOff).strings;
      if (keyStringsOff) pkg.keyStrings = readStringPool(poolReader, p + keyStringsOff).strings;

      // iterate package children
      let q = p + pkgHeaderSize;
      const typeSpecEntryCounts = new Map<number, number>();
      while (q + 8 <= p + chunkSize) {
        const cType = u16(buffer, q);
        const cSize = u32(buffer, q + 4);
        if (cSize < 8) break;
        if (cType === RES_TABLE_TYPE_SPEC_TYPE) {
          const id = buffer.readUInt8(q + 8);
          typeSpecEntryCounts.set(id, u32(buffer, q + 12));
        } else if (cType === RES_TABLE_TYPE_TYPE) {
          const id = buffer.readUInt8(q + 8);
          const flags = buffer.readUInt8(q + 9);
          const entryCount = u32(buffer, q + 12);
          const entriesStart = u32(buffer, q + 16);
          const config = parseConfig(buffer, q + 20);
          const offsetsBase = q + u16(buffer, q + 2);
          const entries = new Map<number, ResEntry>();
          const sparse = (flags & 0x01) !== 0;
          const typeName = pkg.typeStrings[id - 1] ?? `type${id}`;

          if (sparse) {
            for (let i = 0; i < entryCount; i++) {
              const idx = u16(buffer, offsetsBase + i * 4);
              const off = u16(buffer, offsetsBase + i * 4 + 2) * 4;
              const e = readEntry(buffer, q + entriesStart + off, pkg.keyStrings, globalStrings);
              if (e) entries.set(idx, e);
            }
          } else {
            for (let i = 0; i < entryCount; i++) {
              const off = u32(buffer, offsetsBase + i * 4);
              if (off === NO_ENTRY) continue;
              const e = readEntry(buffer, q + entriesStart + off, pkg.keyStrings, globalStrings);
              if (e) entries.set(i, e);
            }
          }
          let t = pkg.types.find((x) => x.id === id);
          if (!t) {
            t = { id, name: typeName, configs: [], entryCount: typeSpecEntryCounts.get(id) ?? entryCount };
            pkg.types.push(t);
          }
          t.configs.push({ config, entries });
        }
        q += cSize;
      }
      packages.push(pkg);
    }
    p += chunkSize;
  }
  packages.sort((a, b) => a.id - b.id);
  return { packages, globalStrings };
}

function readEntry(b: Buffer, off: number, keyStrings: string[], globalStrings: string[]): ResEntry | null {
  if (off + 8 > b.length) return null;
  const size = u16(b, off);
  const flags = u16(b, off + 2);
  const keyIdx = u32(b, off + 4);
  const key = keyStrings[keyIdx] ?? `key${keyIdx}`;
  if (flags & 0x0001) {
    // complex (bag) entry — record presence only
    return { key, complex: true };
  }
  const vOff = off + size;
  if (vOff + 8 > b.length) return { key, complex: false };
  const type = b.readUInt8(vOff + 3);
  const data = u32(b, vOff + 4);
  const value: ResValue = { type, data };
  if (type === 0x03) value.string = globalStrings[data];
  return { key, complex: false, value };
}

/** Resolve a resource id like 0x7f1300d6 to its string value(s). */
export function resolveString(table: ResTable, resId: number): string | null {
  const pkgId = (resId >>> 24) & 0xff;
  const typeId = (resId >>> 16) & 0xff;
  const entryIdx = resId & 0xffff;
  const pkg = table.packages.find((p) => p.id === pkgId) ?? table.packages[0];
  if (!pkg) return null;
  const t = pkg.types.find((x) => x.id === typeId);
  if (!t) return null;
  for (const bucket of t.configs) {
    const e = bucket.entries.get(entryIdx);
    if (e?.value?.string) return e.value.string;
  }
  return null;
}

export function resolveEntry(table: ResTable, resId: number): { typeName: string; key: string; value?: ResValue } | null {
  const pkgId = (resId >>> 24) & 0xff;
  const typeId = (resId >>> 16) & 0xff;
  const entryIdx = resId & 0xffff;
  const pkg = table.packages.find((p) => p.id === pkgId) ?? table.packages[0];
  if (!pkg) return null;
  const t = pkg.types.find((x) => x.id === typeId);
  if (!t) return null;
  for (const bucket of t.configs) {
    const e = bucket.entries.get(entryIdx);
    if (e) return { typeName: t.name, key: e.key, value: e.value };
  }
  return null;
}

export function localeHistogram(table: ResTable): { locale: string; entries: number }[] {
  const counts = new Map<string, number>();
  for (const pkg of table.packages) {
    for (const t of pkg.types) {
      for (const bucket of t.configs) {
        const loc = bucket.config.locale;
        counts.set(loc, (counts.get(loc) ?? 0) + bucket.entries.size);
      }
    }
  }
  return [...counts.entries()]
    .map(([locale, entries]) => ({ locale, entries }))
    .sort((a, b) => b.entries - a.entries);
}

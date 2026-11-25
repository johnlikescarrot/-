/**
 * Minimal, dependency-free ZIP reader (central directory driven).
 *
 * Supports STORE (0) and DEFLATE (8) entries via node:zlib, verifies CRCs on
 * demand, and exposes the raw file offsets required by the APK Signature
 * Scheme v2/v3 verifier.
 */
import { inflateRawSync } from "node:zlib";

const SIG_EOCD = 0x06054b50;
const SIG_CEN = 0x02014b50;
const SIG_LOC = 0x04034b50;
const SIG_EOCD64 = 0x06064b50;
const SIG_EOCD64_LOC = 0x07064b50;

export interface ZipEntry {
  name: string;
  compressionMethod: number;
  compressedSize: number;
  uncompressedSize: number;
  crc32: number;
  localHeaderOffset: number;
  dataOffset: number; // absolute offset of (possibly deflated) payload
  isDirectory: boolean;
  method: "store" | "deflate" | "other";
}

export interface ZipArchive {
  buffer: Buffer;
  entries: ZipEntry[];
  byName: Map<string, ZipEntry>;
  /** absolute offset of the End of Central Directory record */
  eocdOffset: number;
  centralDirectoryOffset: number;
  centralDirectorySize: number;
  comment: string;
}

function u16(b: Buffer, o: number) { return b.readUInt16LE(o); }
function u32(b: Buffer, o: number) { return b.readUInt32LE(o); }
function u64(b: Buffer, o: number) { return Number(b.readBigUInt64LE(o)); }

/** Locate the EOCD by scanning backwards (max 64 KiB comment). */
function findEocd(buf: Buffer): number {
  const min = Math.max(0, buf.length - 0x10000 - 22);
  for (let i = buf.length - 22; i >= min; i--) {
    if (buf.readUInt32LE(i) === SIG_EOCD) {
      const commentLen = buf.readUInt16LE(i + 20);
      if (i + 22 + commentLen === buf.length) return i;
    }
  }
  throw new Error("EOCD not found (not a ZIP/XAPK/APK?)");
}

export function openZip(buffer: Buffer): ZipArchive {
  const eocdOffset = findEocd(buffer);
  let cdSize = u32(buffer, eocdOffset + 12);
  let cdOffset = u32(buffer, eocdOffset + 16);
  let count = u16(buffer, eocdOffset + 10);

  // ZIP64 end-of-central-directory locator
  if (cdOffset === 0xffffffff || count === 0xffff) {
    const loc = eocdOffset - 20;
    if (loc >= 0 && u32(buffer, loc) === SIG_EOCD64_LOC) {
      const z64 = u64(buffer, loc + 8);
      if (u32(buffer, z64) === SIG_EOCD64) {
        cdSize = u64(buffer, z64 + 40);
        cdOffset = u64(buffer, z64 + 48);
        count = u64(buffer, z64 + 32);
      }
    }
  }

  const entries: ZipEntry[] = [];
  let p = cdOffset;
  for (let i = 0; i < count && p + 46 <= buffer.length; i++) {
    if (u32(buffer, p) !== SIG_CEN) break;
    const flags = u16(buffer, p + 8);
    const methodId = u16(buffer, p + 10);
    const crc32 = u32(buffer, p + 16);
    const cSize = u32(buffer, p + 20);
    const uSize = u32(buffer, p + 24);
    const nameLen = u16(buffer, p + 28);
    const extraLen = u16(buffer, p + 30);
    const commentLen = u16(buffer, p + 32);
    const localOff = u32(buffer, p + 42);
    const name = buffer.subarray(p + 46, p + 46 + nameLen).toString(
      flags & 0x800 ? "utf8" : "latin1",
    );
    // payload offset from the *local* header (extra field length can differ)
    let dataOffset = 0;
    if (localOff + 30 <= buffer.length && u32(buffer, localOff) === SIG_LOC) {
      dataOffset = localOff + 30 + u16(buffer, localOff + 26) + u16(buffer, localOff + 28);
    }
    entries.push({
      name,
      compressionMethod: methodId,
      compressedSize: cSize,
      uncompressedSize: uSize,
      crc32,
      localHeaderOffset: localOff,
      dataOffset,
      isDirectory: name.endsWith("/"),
      method: methodId === 0 ? "store" : methodId === 8 ? "deflate" : "other",
    });
    p += 46 + nameLen + extraLen + commentLen;
  }

  const byName = new Map(entries.map((e) => [e.name, e]));
  return {
    buffer,
    entries,
    byName,
    eocdOffset,
    centralDirectoryOffset: cdOffset,
    centralDirectorySize: cdSize,
    comment: buffer
      .subarray(eocdOffset + 22, eocdOffset + 22 + u16(buffer, eocdOffset + 20))
      .toString("latin1"),
  };
}

export function readEntry(zip: ZipArchive, nameOrEntry: string | ZipEntry): Buffer {
  const e = typeof nameOrEntry === "string" ? zip.byName.get(nameOrEntry) : nameOrEntry;
  if (!e) throw new Error(`entry not found: ${nameOrEntry}`);
  const raw = zip.buffer.subarray(e.dataOffset, e.dataOffset + e.compressedSize);
  if (e.method === "store") return Buffer.from(raw);
  if (e.method === "deflate") return inflateRawSync(raw);
  throw new Error(`unsupported compression method ${e.compressionMethod} for ${e.name}`);
}

export function hasEntry(zip: ZipArchive, name: string): boolean {
  return zip.byName.has(name);
}

export function listNames(zip: ZipArchive): string[] {
  return zip.entries.map((e) => e.name);
}

// Pure Node.js DEX (Dalvik Executable) parser - robust, low memory, no deps.
// Reads header, counts classes/methods/types, lists classes, extracts interesting strings.
"use strict";
const fs = require("fs");

function u8(b,o){return b.readUInt8(o);}
function u16(b,o){return b.readUInt16LE(o);}
function u32(b,o){return b.readUInt32LE(o);}

// read unsigned LEB128, returns {value, nextOffset}
function readUleb128(data, o) {
  let result = 0, shift = 0;
  while (o < data.length) {
    const b = data.readUInt8(o++);
    result |= (b & 0x7f) << shift;
    if ((b & 0x80) === 0) return { value: result, next: o };
    shift += 7;
    if (shift > 35) break;
  }
  return { value: result, next: o };
}

// DEX strings are UTF-8 bytes with uleb128 length (length counted in UTF-16 units).
function readDexString(data, strDataOff, strIdxOff) {
  const ptr = strDataOff + strIdxOff * 4;
  if (ptr + 4 > data.length) return null;
  const off = data.readUInt32LE(ptr);
  const abs = strDataOff + off;
  if (abs >= data.length) return null;
  const { value: len, next } = readUleb128(data, abs);
  const end = Math.min(next + len, data.length);
  const raw = data.slice(next, end);
  // strip trailing NUL(s)
  let i = raw.length;
  while (i > 0 && raw.readUInt8(i - 1) === 0) i--;
  return raw.slice(0, i).toString("utf8") || null;
}

function parseDex(path) {
  const data = fs.readFileSync(path);
  const magic = data.slice(0, 8).toString("latin1");
  if (!magic.startsWith("dex\n")) return { path, error: "not a dex: " + JSON.stringify(magic) };
  const stringIdsSize = u32(data, 0x38);
  const stringIdsOff = u32(data, 0x3c);
  const typeIdsSize = u32(data, 0x40);
  const typeIdsOff = u32(data, 0x44);
  const methodIdsSize = u32(data, 0x50);
  const classDefsSize = u32(data, 0x58);
  const classDefsOff = u32(data, 0x5c);

  const result = {
    path, magic, endianTag: u32(data, 0x28),
    stringIdsSize, typeIdsSize, methodIdsSize, classDefsSize,
    classes: [],        // {name, super}
    methodCount: 0,
    types: [],          // resolved type names
    strings: []         // interesting strings
  };

  const strDataOff = stringIdsOff + stringIdsSize * 4;

  // types
  for (let i = 0; i < typeIdsSize; i++) {
    const ptr = typeIdsOff + i * 4;
    if (ptr + 4 > data.length) break;
    const si = u32(data, ptr);
    result.types.push(readDexString(data, strDataOff, si) || ("<type"+i+">"));
  }

  // methods: just count (don't store)
  result.methodCount = methodIdsSize;

  // classes: class_def_item = 28 bytes
  for (let i = 0; i < classDefsSize; i++) {
    const o = classDefsOff + i * 28;
    if (o + 28 > data.length) break;
    const classIdx = u32(data, o);
    const accessFlags = u32(data, o + 4);
    const superclassIdx = u32(data, o + 8);
    const classDataOff = u32(data, o + 20);
    const className = classIdx < result.types.length ? result.types[classIdx] : ("<class"+classIdx+">");
    const superName = superclassIdx < result.types.length && superclassIdx !== 0xFFFFFFFF ? result.types[superclassIdx] : null;
    result.classes.push({ name: className, super: superName, accessFlags, classDataOff });
  }

  // interesting strings (URLs, domains, keys, endpoints) - cap
  const interestRe = /(https?:\/\/|api|auth|token|key|secret|endpoint|\.vn|\.com|\.net|zalopay|zalopay\.vn|go\.zalopay|km\.zalopay|onelink|appsflyer|facebook|googleapis|gstatic|play\.google)/i;
  for (let i = 0; i < stringIdsSize && result.strings.length < 400; i++) {
    const s = readDexString(data, strDataOff, i);
    if (s && s.length > 2 && s.length < 300 && interestRe.test(s)) {
      result.strings.push(s);
    }
  }
  return result;
}

if (require.main === module) {
  const files = process.argv.slice(2);
  let totalClasses = 0, totalMethods = 0;
  for (const f of files) {
    try {
      const d = parseDex(f);
      totalClasses += d.classes.length;
      totalMethods += d.methodCount;
      console.log("=== " + f + " ===");
      console.log("classes:", d.classes.length, "methods:", d.methodCount, "types:", d.types.length, "magic:", d.magic.trim());
      // Zalopay-ish / interesting classes
      const interesting = d.classes.filter(c =>
        /(vn\.com\.vng\.zalopay|vn\.zalopay|vn\.com\.vng\.resultpage|com\.vnpay|com\.fpt\.fci|vn\.ai\.trueid|com\.alibaba\.griver|com\.iap\.android|com\.karumi\.dexter|com\.facebook\.ads|com\.appsflyer)/.test(c.name)
      );
      interesting.forEach(c => console.log("  [IC] 0x"+c.accessFlags.toString(16)+" "+c.name + (c.super?" < "+c.super:"")));
      if (d.strings.length) {
        console.log("  interesting strings:");
        d.strings.slice(0, 60).forEach(s => console.log("    STR:", JSON.stringify(s)));
      }
    } catch (e) {
      console.error("ERR", f, e.message);
    }
  }
  console.log("\nTOTAL classes:", totalClasses, "TOTAL method_ids:", totalMethods);
}

module.exports = { parseDex };

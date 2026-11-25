/**
 * Android binary XML (AXML) decoder.
 *
 * Implements the RES_XML_TYPE container: string pool (UTF-8/UTF-16),
 * resource-id map, namespace/element/CDATA chunks and typed attribute values.
 * Produces a plain JSON tree plus flattened attribute access helpers.
 */

const RES_STRING_POOL = 0x0001;
const RES_XML_TYPE = 0x0003;
const RES_XML_RESOURCE_MAP = 0x0180;
const RES_XML_START_NAMESPACE = 0x0100;
const RES_XML_END_NAMESPACE = 0x0101;
const RES_XML_START_ELEMENT = 0x0102;
const RES_XML_END_ELEMENT = 0x0103;
const RES_XML_CDATA = 0x0104;

export interface AxmlAttribute {
  ns: string | null;
  name: string;
  rawValue: string | null;
  type: number;
  typeName: string;
  data: number;
  value: string | number | boolean | null;
  resourceId?: number;
}

export interface AxmlNode {
  tag: string;
  ns: string | null;
  attributes: AxmlAttribute[];
  children: AxmlNode[];
  text?: string;
}

const TYPE_NAMES: Record<number, string> = {
  0x00: "null",
  0x01: "reference",
  0x02: "attribute",
  0x03: "string",
  0x04: "float",
  0x05: "dimension",
  0x06: "fraction",
  0x07: "dynamic_reference",
  0x08: "dynamic_attribute",
  0x10: "int_dec",
  0x11: "int_hex",
  0x12: "int_boolean",
  0x1c: "int_color_argb8",
  0x1d: "int_color_rgb8",
};

const ANDROID_NS = "http://schemas.android.com/apk/res/android";

class Reader {
  constructor(public b: Buffer) {}
  u8(o: number) { return this.b.readUInt8(o); }
  u16(o: number) { return this.b.readUInt16LE(o); }
  u32(o: number) { return this.b.readUInt32LE(o); }
}

export function readStringPool(r: Reader, start: number): { strings: string[]; end: number } {
  const headerSize = r.u16(start + 2);
  const size = r.u32(start + 4);
  const count = r.u32(start + 8);
  const flags = r.u32(start + 16);
  const stringsStart = r.u32(start + 20);
  const utf8 = (flags & 0x100) !== 0;
  const offsets: number[] = [];
  for (let i = 0; i < count; i++) offsets.push(r.u32(start + headerSize + i * 4));
  const strings: string[] = [];
  for (const off of offsets) {
    const p = start + stringsStart + off;
    if (p <= 0 || p >= r.b.length) { strings.push(""); continue; }
    if (utf8) {
      // UTF-16 length then UTF-8 length, each 1 or 2 bytes
      let q = p;
      let l16 = r.u8(q++);
      if (l16 & 0x80) { l16 = ((l16 & 0x7f) << 8) | r.u8(q++); }
      let l8 = r.u8(q++);
      if (l8 & 0x80) { l8 = ((l8 & 0x7f) << 8) | r.u8(q++); }
      strings.push(r.b.subarray(q, q + l8).toString("utf8"));
    } else {
      let q = p;
      let len = r.u16(q); q += 2;
      if (len & 0x8000) { len = ((len & 0x7fff) << 16) | r.u16(q); q += 2; }
      strings.push(r.b.subarray(q, q + len * 2).toString("utf16le"));
    }
  }
  return { strings, end: start + size };
}

function formatValue(type: number, data: number, raw: string | null, strings: string[]): AxmlAttribute["value"] {
  switch (type) {
    case 0x03: return strings[data] ?? raw ?? "";
    case 0x01: return `@0x${(data >>> 0).toString(16).padStart(8, "0")}`;
    case 0x02: return `?0x${(data >>> 0).toString(16).padStart(8, "0")}`;
    case 0x12: return data !== 0;
    case 0x10: return data | 0;
    case 0x11: return `0x${(data >>> 0).toString(16)}`;
    case 0x04: return new Float32Array(new Uint32Array([data]).buffer)[0];
    case 0x05: return `${data}px(dim)`;
    case 0x06: return `${data}(frac)`;
    default:
      if (raw !== null) return raw;
      return data | 0;
  }
}

export function parseAxml(buffer: Buffer): { root: AxmlNode | null; strings: string[]; resourceIds: number[] } {
  const r = new Reader(buffer);
  const type = r.u16(0);
  if (type !== RES_XML_TYPE) throw new Error(`not AXML (type=0x${type.toString(16)})`);
  const total = Math.min(r.u32(4), buffer.length);

  let strings: string[] = [];
  let resourceIds: number[] = [];
  const stack: AxmlNode[] = [];
  let root: AxmlNode | null = null;
  const nsMap = new Map<string, string>();

  let p = r.u16(2);
  while (p + 8 <= total) {
    const chunkType = r.u16(p);
    const headerSize = r.u16(p + 2);
    const chunkSize = r.u32(p + 4);
    if (chunkSize < 8 || p + chunkSize > buffer.length + 4) break;

    if (chunkType === RES_STRING_POOL) {
      const pool = readStringPool(r, p);
      strings = pool.strings;
    } else if (chunkType === RES_XML_RESOURCE_MAP) {
      resourceIds = [];
      for (let q = p + headerSize; q + 4 <= p + chunkSize; q += 4) resourceIds.push(r.u32(q));
    } else if (chunkType === RES_XML_START_NAMESPACE) {
      const prefix = strings[r.u32(p + headerSize)];
      const uri = strings[r.u32(p + headerSize + 4)];
      if (prefix !== undefined) nsMap.set(uri, prefix);
    } else if (chunkType === RES_XML_START_ELEMENT) {
      const nsIdx = r.u32(p + headerSize);
      const nameIdx = r.u32(p + headerSize + 4);
      const attrStart = r.u16(p + headerSize + 8);
      const attrSize = r.u16(p + headerSize + 10);
      const attrCount = r.u16(p + headerSize + 12);
      const attributes: AxmlAttribute[] = [];
      for (let i = 0; i < attrCount; i++) {
        const a = p + headerSize + attrStart + i * attrSize;
        const aNs = r.u32(a);
        const aName = r.u32(a + 4);
        const aRaw = r.u32(a + 8);
        const vType = r.u8(a + 15);
        const vData = r.u32(a + 16);
        const uri = aNs !== 0xffffffff ? strings[aNs] ?? null : null;
        const raw = aRaw !== 0xffffffff ? strings[aRaw] ?? null : null;
        const name = strings[aName] ?? `attr_${aName}`;
        const prefix = uri ? nsMap.get(uri) : undefined;
        attributes.push({
          ns: uri,
          name: prefix ? `${prefix}:${name}` : name,
          rawValue: raw,
          type: vType,
          typeName: TYPE_NAMES[vType] ?? `0x${vType.toString(16)}`,
          data: vData,
          value: formatValue(vType, vData, raw, strings),
          resourceId: resourceIds[aName],
        });
      }
      const node: AxmlNode = {
        tag: strings[nameIdx] ?? "?",
        ns: nsIdx !== 0xffffffff ? strings[nsIdx] ?? null : null,
        attributes,
        children: [],
      };
      if (stack.length) stack[stack.length - 1].children.push(node);
      else if (!root) root = node;
      stack.push(node);
    } else if (chunkType === RES_XML_END_ELEMENT) {
      stack.pop();
    } else if (chunkType === RES_XML_CDATA) {
      const txt = strings[r.u32(p + headerSize)];
      if (stack.length && txt) stack[stack.length - 1].text = (stack[stack.length - 1].text ?? "") + txt;
    }
    p += chunkSize;
  }
  return { root, strings, resourceIds };
}

export function attr(node: AxmlNode, name: string): string | null {
  const a = node.attributes.find((x) => x.name === name || x.name.endsWith(`:${name}`));
  if (!a) return null;
  return a.value === null || a.value === undefined ? null : String(a.value);
}

export function attrBool(node: AxmlNode, name: string, fallback = false): boolean {
  const v = attr(node, name);
  if (v === null) return fallback;
  return v === "true" || v === "1";
}

export function attrInt(node: AxmlNode, name: string, fallback = 0): number {
  const v = attr(node, name);
  if (v === null) return fallback;
  const n = Number(v.startsWith("0x") ? parseInt(v, 16) : v);
  return Number.isFinite(n) ? n : fallback;
}

export function children(node: AxmlNode, tag: string): AxmlNode[] {
  return node.children.filter((c) => c.tag === tag);
}

export function find(node: AxmlNode, tag: string): AxmlNode | null {
  if (node.tag === tag) return node;
  for (const c of node.children) {
    const f = find(c, tag);
    if (f) return f;
  }
  return null;
}

export { ANDROID_NS };

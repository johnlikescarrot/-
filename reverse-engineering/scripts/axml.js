// AXML (Android binary XML) parser - pure Node.js, no external deps.
// Decodes AndroidManifest.xml from APK into a readable tree.
"use strict";

const fs = require("fs");

const CHUNK = {
  0x0000: "NULL", 0x0001: "STRING_POOL", 0x0002: "TABLE", 0x0003: "XML",
  0x0100: "XML_FIRST", 0x0101: "XML_START_NS", 0x0102: "XML_END_NS",
  0x0103: "XML_START_ELEM", 0x0104: "XML_END_ELEM", 0x0105: "XML_CDATA", 0x0106: "XML_RESOURCE_MAP"
};

function readU16(b, o){ return b.readUInt16LE(o); }
function readU32(b, o){ return b.readUInt32LE(o); }

function readStringAt(pool, strDataOff, idxOff) {
  const off = pool.readUInt32LE(idxOff);
  if (off === 0xFFFFFFFF) return null;
  const abs = strDataOff + off;
  const first = pool.readUInt16LE(abs);
  const is8Bit = (first & 0x8000) !== 0;
  const n = first & 0x7FFF;  // byte count (8bit) or char count (16bit)
  const p = abs + 2;
  if (is8Bit) {
    // UTF-8 bytes (length n)
    return pool.slice(p, p + n).toString("utf8");
  } else {
    // UTF-16LE, n code units
    const chunks = [];
    for (let i = 0; i < n; i++) chunks.push(pool.readUInt16LE(p + i*2));
    return Buffer.from(chunks).toString("ucs2");
  }
}

function parseStringPool(buf) {
  // buf starts at chunk data (after chunk header)
  const o = 8; // skip chunk header: type(2)+headerSize(2)+size(4)
  const stringCount = buf.readUInt32LE(o + 0);
  const styleCount = buf.readUInt32LE(o + 4);
  const flags = buf.readUInt32LE(o + 8);
  const stringsStart = buf.readUInt32LE(o + 12);
  const stylesStart = buf.readUInt32LE(o + 16);
  const strDataOff = stringsStart;  // offsets within chunk are relative to chunk start (buf[0])
  const stringIds = [];
  for (let i = 0; i < stringCount; i++) {
    stringIds.push(buf.readUInt32LE(o + 20 + i*4));
  }
  const strings = stringIds.map((id, i) => {
    const idxOff = o + 20 + i*4;
    return readStringAt(buf, strDataOff, idxOff);
  });
  return { stringCount, styleCount, flags, strings, stringsStart, stylesStart };
}

function parseAttrValue(type, data) {
  const T = {
    0x0000: "TYPE_NULL", 0x0001: "TYPE_REFERENCE", 0x0002: "TYPE_ATTRIBUTE",
    0x0003: "TYPE_STRING", 0x0004: "TYPE_FLOAT", 0x0005: "TYPE_DIMENSION",
    0x0006: "TYPE_FRACTION", 0x0010: "TYPE_INT_DEC", 0x0011: "TYPE_INT_HEX",
    0x0012: "TYPE_INT_BOOLEAN", 0x001F: "TYPE_INT_COLOR_ARGB8",
    0x0020: "TYPE_INT_COLOR_ARGB4",
  };
  const tname = T[type] || ("0x"+type.toString(16));
  if (type === 0x0003) return { type: tname, data: "stringRef#"+data };
  if (type === 0x0012) return { type: tname, data: (data !== 0) };
  if (type === 0x0010 || type === 0x0011) return { type: tname, data: data };
  if (type === 0x0004) return { type: tname, data: data }; // raw float bits
  return { type: tname, data: data };
}

function parseXml(buf, strings) {
  const nodes = [];
  let o = 0;
  const depthStack = []; // track for closing
  let textBuf = "";
  const flushText = () => {
    if (textBuf.trim().length > 0) {
      nodes.push({ kind: "text", text: textBuf });
      textBuf = "";
    }
  };
  while (o < buf.length) {
    const type = buf.readUInt16LE(o);
    const headerSize = buf.readUInt16LE(o + 2);
    const size = buf.readUInt32LE(o + 4);
    const end = o + size;
    const name = CHUNK[type] || ("0x"+type.toString(16));
    const data = buf.slice(o + headerSize, end);

    if (type === 0x0101) { // start namespace
      const prefix = data.readUInt32LE(0);
      const uri = data.readUInt32LE(4);
      flushText();
      nodes.push({ kind: "ns_start", prefix: strings[prefix] || "", uri: strings[uri] || "" });
    } else if (type === 0x0102) {
      const prefix = data.readUInt32LE(0);
      const uri = data.readUInt32LE(4);
      flushText();
      nodes.push({ kind: "ns_end", prefix: strings[prefix] || "", uri: strings[uri] || "" });
    } else if (type === 0x0103) { // start element
      flushText();
      const nsIdx = data.readUInt32LE(0);
      const nameIdx = data.readUInt32LE(4);
      const attrStart = data.readUInt16LE(8);
      const attrSize = data.readUInt16LE(10);
      const attrCount = data.readUInt16LE(12);
      const idAttr = data.readUInt16LE(14);
      const classAttr = data.readUInt16LE(16);
      const styleAttr = data.readUInt16LE(18);
      const attrs = [];
      let ao = attrStart;
      for (let i = 0; i < attrCount; i++) {
        const ans = data.readUInt32LE(ao + 0);
        const aname = data.readUInt32LE(ao + 4);
        const ivalue = data.readUInt32LE(ao + 8);
        const type = data.readUInt32LE(ao + 12);
        const d = data.readUInt32LE(ao + 16);
        attrs.push({
          ns: strings[ans] || "",
          name: strings[aname] || "",
          valueRaw: ivalue,
          value: strings[ivalue] || "",
          ...parseAttrValue(type, d),
          typed: parseAttrValue(type, d)
        });
        ao += 20;
      }
      nodes.push({
        kind: "start_elem",
        ns: strings[nsIdx] || "",
        name: strings[nameIdx] || "",
        attrCount, idAttr, classAttr, styleAttr, attrs
      });
      depthStack.push({ name: strings[nameIdx] || "", ns: strings[nsIdx] || "" });
    } else if (type === 0x0104) { // end element
      flushText();
      const nsIdx = data.readUInt32LE(0);
      const nameIdx = data.readUInt32LE(4);
      nodes.push({ kind: "end_elem", ns: strings[nsIdx] || "", name: strings[nameIdx] || "" });
      depthStack.pop();
    } else if (type === 0x0105) { // cdata / text
      const dataIdx = data.readUInt32LE(0);
      const s = strings[dataIdx] || "";
      textBuf += (textBuf ? "\n" : "") + s;
    }
    o = end;
  }
  flushText();
  return nodes;
}

function buildTree(nodes) {
  const root = { kind: "root", children: [] };
  const stack = [root];
  for (const n of nodes) {
    if (n.kind === "start_elem") {
      const el = { kind: "element", name: n.name, ns: n.ns, attrs: n.attrs, children: [] };
      stack[stack.length-1].children.push(el);
      stack.push(el);
    } else if (n.kind === "end_elem") {
      stack.pop();
    } else if (n.kind === "text") {
      stack[stack.length-1].children.push({ kind: "text", text: n.text });
    }
  }
  return root;
}

function render(el, indent = 0) {
  const pad = "  ".repeat(indent);
  let out = "";
  for (const c of el.children) {
    if (c.kind === "element") {
      let attrs = "";
      if (c.attrs.length) attrs = " " + c.attrs.map(a => a.name + (a.valueRaw===undefined?"":"=\""+JSON.stringify(a.value)+("\" ("+a.typed.type+":"+a.typed.data+")"))).join(" ");
      out += `${pad}<${c.name}${attrs}>\n`;
      out += render(c, indent + 1);
      out += `${pad}</${c.name}>\n`;
    } else if (c.kind === "text") {
      const t = c.text.replace(/\n+/g, " ").trim();
      if (t) out += `${pad}${t}\n`;
    }
  }
  return out;
}

const XML_CHUNK_TYPES = new Set([0x0101,0x0102,0x0103,0x0104,0x0105,0x0106]);

function scanChunks(raw, start, end, pools, xmlChunks) {
  let o = start;
  while (o + 8 <= end) {
    const type = raw.readUInt16LE(o);
    const headerSize = raw.readUInt16LE(o + 2);
    const size = raw.readUInt32LE(o + 4);
    if (size < 8 || o + size > end) break;
    const chunk = raw.slice(o, o + size);
    if (type === 0x0001) pools.push(parseStringPool(chunk));
    else if (XML_CHUNK_TYPES.has(type)) xmlChunks.push(chunk);
    else if (type === 0x0003 || type === 0x0100) {
      // container: recurse into its body to find nested chunks
      scanChunks(raw, o + headerSize, o + size, pools, xmlChunks);
    }
    o += size;
  }
}

function parseManifest(path) {
  const raw = fs.readFileSync(path);
  const pools = [];
  const xmlChunks = [];
  scanChunks(raw, 0, raw.length, pools, xmlChunks);
  const stringPool = pools[0];
  const xmlBuf = xmlChunks.length ? Buffer.concat(xmlChunks) : null;
  if (!stringPool || !xmlBuf) throw new Error("Could not find string pool / xml chunk");
  const nodes = parseXml(xmlBuf, stringPool.strings);
  const tree = buildTree(nodes);
  return { stringPool, nodes, tree, render: () => render(tree) };
}

if (require.main === module) {
  const m = parseManifest(process.argv[2]);
  console.log(m.render());
}

module.exports = { parseManifest, parseStringPool, parseXml, buildTree, render, CHUNK };

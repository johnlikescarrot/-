/**
 * Dalvik executable (DEX) parser + static census engine.
 *
 * No Java toolchain: DEX 035/037/038/039 containers are decoded directly,
 * including class_data_item, code_item and a table-driven instruction walker
 * used to recover invoke/field reference edges and string constants.
 *
 * Reference: "Dalvik Executable format", Android Open Source Project
 * (dex-format), plus instruction formats documented in "Dalvik bytecode".
 */

export interface DexHeader {
  version: string;
  checksum: number;
  signature: string; // sha1 hex of the payload
  fileSize: number;
  stringIds: number;
  typeIds: number;
  protoIds: number;
  fieldIds: number;
  methodIds: number;
  classDefs: number;
  dataSize: number;
  mapOff: number;
}

export interface DexMethodRef {
  class: string;
  name: string;
  proto: string;
}

export interface DexClass {
  name: string; // Lfoo/Bar;
  superName: string | null;
  interfaces: string[];
  accessFlags: number;
  sourceFile: string | null;
  methodCount: number;
  fieldCount: number;
  instructionCount: number;
  stringConsts: number;
  invokeEdges: number;
  /** method names declared by this class */
  methods: string[];
}

export interface DexFileResult {
  file: string;
  header: DexHeader;
  strings: string[];
  types: string[];
  methodRefs: DexMethodRef[];
  fieldRefs: { class: string; name: string; type: string }[];
  classes: DexClass[];
  /** aggregated counts */
  stats: {
    classes: number;
    methods: number;
    fields: number;
    instructions: number;
    invokeEdges: number;
    stringConstants: number;
  };
}

const NO_INDEX = 0xffffffff;

function u16(b: Buffer, o: number) { return b.readUInt16LE(o); }
function u32(b: Buffer, o: number) { return b.readUInt32LE(o); }

function readUleb128(b: Buffer, off: number): [number, number] {
  let result = 0, shift = 0, p = off;
  for (;;) {
    const byte = b[p++];
    result |= (byte & 0x7f) << shift;
    if ((byte & 0x80) === 0) break;
    shift += 7;
    if (shift > 35) break;
  }
  return [result >>> 0, p];
}

function readSleb128(b: Buffer, off: number): [number, number] {
  let result = 0, shift = 0, p = off, byte = 0;
  do {
    byte = b[p++];
    result |= (byte & 0x7f) << shift;
    shift += 7;
  } while (byte & 0x80);
  if (shift < 32 && (byte & 0x40) !== 0) result |= -(1 << shift);
  return [result, p];
}

/** Instruction widths in 16-bit code units, indexed by opcode. */
const WIDTHS = (() => {
  const w = new Uint8Array(256).fill(1);
  const set = (from: number, to: number, v: number) => { for (let i = from; i <= to; i++) w[i] = v; };
  set(0x02, 0x02, 2); set(0x03, 0x03, 3);
  set(0x05, 0x05, 2); set(0x06, 0x06, 3);
  set(0x08, 0x08, 2); set(0x09, 0x09, 3);
  set(0x13, 0x13, 2); set(0x14, 0x14, 3); set(0x15, 0x15, 2); set(0x16, 0x16, 2);
  set(0x17, 0x17, 3); set(0x18, 0x18, 5); set(0x19, 0x19, 2); set(0x1a, 0x1a, 2);
  set(0x1b, 0x1b, 3); set(0x1c, 0x1c, 2);
  set(0x1f, 0x20, 2); set(0x22, 0x23, 2); set(0x24, 0x26, 3); set(0x29, 0x29, 2);
  set(0x2a, 0x2c, 3);
  set(0x2d, 0x3d, 2);
  set(0x44, 0x51, 2); set(0x52, 0x6d, 2);
  set(0x6e, 0x72, 3); set(0x74, 0x78, 3);
  set(0x90, 0xaf, 2); set(0xd0, 0xe2, 2);
  set(0xfa, 0xfb, 4); set(0xfc, 0xfd, 3); set(0xfe, 0xff, 2);
  return w;
})();

const stringIndexSize: Record<number, number> = { 0x1a: 2, 0x1b: 3, 0x1c: 2 };

export function parseDex(buffer: Buffer, file: string, deep = true): DexFileResult {
  if (buffer.toString("latin1", 0, 4) !== "dex\n") throw new Error(`${file}: bad dex magic`);
  const version = buffer.toString("latin1", 4, 7);
  const header: DexHeader = {
    version,
    checksum: u32(buffer, 8),
    signature: buffer.subarray(12, 32).toString("hex"),
    fileSize: u32(buffer, 32),
    stringIds: u32(buffer, 56),
    typeIds: u32(buffer, 64),
    protoIds: u32(buffer, 72),
    fieldIds: u32(buffer, 80),
    methodIds: u32(buffer, 88),
    classDefs: u32(buffer, 96),
    dataSize: u32(buffer, 104),
    mapOff: u32(buffer, 52),
  };
  const stringIdsOff = u32(buffer, 60);
  const typeIdsOff = u32(buffer, 68);
  const protoIdsOff = u32(buffer, 76);
  const fieldIdsOff = u32(buffer, 84);
  const methodIdsOff = u32(buffer, 92);
  const classDefsOff = u32(buffer, 100);

  const strings: string[] = new Array(header.stringIds);
  for (let i = 0; i < header.stringIds; i++) {
    const off = u32(buffer, stringIdsOff + i * 4);
    const [len, p] = readUleb128(buffer, off);
    const end = buffer.indexOf(0, p);
    strings[i] = buffer.toString("utf8", p, end < 0 ? undefined : end);
  }
  const types: string[] = new Array(header.typeIds);
  for (let i = 0; i < header.typeIds; i++) types[i] = strings[u32(buffer, typeIdsOff + i * 4)];

  // method_ids: class_idx(2) proto_idx(2) name_idx(4)
  const methodRefs: DexMethodRef[] = new Array(header.methodIds);
  for (let i = 0; i < header.methodIds; i++) {
    const off = methodIdsOff + i * 8;
    const classIdx = u16(buffer, off);
    const protoIdx = u16(buffer, off + 2);
    const nameIdx = u32(buffer, off + 4);
    methodRefs[i] = {
      class: types[classIdx] ?? "?",
      name: strings[nameIdx] ?? "?",
      proto: protoOf(buffer, protoIdsOff, protoIdx, types),
    };
  }
  const fieldRefs: DexFileResult["fieldRefs"] = new Array(header.fieldIds);
  for (let i = 0; i < header.fieldIds; i++) {
    const off = fieldIdsOff + i * 8;
    fieldRefs[i] = {
      class: types[u16(buffer, off)] ?? "?",
      type: types[u16(buffer, off + 2)] ?? "?",
      name: strings[u32(buffer, off + 4)] ?? "?",
    };
  }

  const classes: DexClass[] = [];
  let totalInstructions = 0, totalInvokeEdges = 0, totalStringConsts = 0, totalMethods = 0, totalFields = 0;

  for (let i = 0; i < header.classDefs; i++) {
    const off = classDefsOff + i * 32;
    const classIdx = u32(buffer, off);
    const accessFlags = u32(buffer, off + 4);
    const superIdx = u32(buffer, off + 8);
    const interfacesOff = u32(buffer, off + 12);
    const sourceIdx = u32(buffer, off + 16);
    const classDataOff = u32(buffer, off + 24);
    const cls: DexClass = {
      name: types[classIdx] ?? "?",
      superName: superIdx === NO_INDEX ? null : types[superIdx] ?? null,
      interfaces: [],
      accessFlags,
      sourceFile: sourceIdx === NO_INDEX ? null : strings[sourceIdx] ?? null,
      methodCount: 0,
      fieldCount: 0,
      instructionCount: 0,
      stringConsts: 0,
      invokeEdges: 0,
      methods: [],
    };
    if (interfacesOff) {
      const n = u32(buffer, interfacesOff);
      for (let k = 0; k < n; k++) cls.interfaces.push(types[u16(buffer, interfacesOff + 4 + k * 2)] ?? "?");
    }
    if (classDataOff) {
      let p = classDataOff;
      let sf: number, inf: number, dm: number, vm: number;
      [sf, p] = readUleb128(buffer, p);
      [inf, p] = readUleb128(buffer, p);
      [dm, p] = readUleb128(buffer, p);
      [vm, p] = readUleb128(buffer, p);
      cls.fieldCount = sf + inf;
      const readFields = (count: number) => {
        let fieldIdx = 0;
        for (let k = 0; k < count; k++) {
          let diff: number;
          [diff, p] = readUleb128(buffer, p); fieldIdx += diff;
          let access: number;
          const adv = readUleb128(buffer, p); access = adv[0]; p = adv[1];
        }
      };
      readFields(sf); readFields(inf);
      const readMethods = (count: number, isVirtual: boolean) => {
        let methodIdx = 0;
        for (let k = 0; k < count; k++) {
          let diff: number;
          [diff, p] = readUleb128(buffer, p); methodIdx += diff;
          let access: number;
          let r = readUleb128(buffer, p); access = r[0]; p = r[1];
          let codeOff: number;
          r = readUleb128(buffer, p); codeOff = r[0]; p = r[1];
          cls.methodCount++;
          totalMethods++;
          const ref = methodRefs[methodIdx];
          if (ref) cls.methods.push(ref.name);
          if (deep && codeOff) {
            const insnsSize = u32(buffer, codeOff + 12);
            const insns = codeOff + 16;
            let q = 0;
            let localCount = 0, edges = 0, strConsts = 0;
            while (q < insnsSize) {
              const unit = u16(buffer, insns + q * 2);
              const op = unit & 0xff;
              if (unit === 0x0100 || unit === 0x0200) break; // payload start -> not reachable linearly
              let width = WIDTHS[op] || 1;
              if (op >= 0x6e && op <= 0x72) {
                const ref2 = methodRefs[u16(buffer, insns + (q + 1) * 2)];
                if (ref2) edges++;
              } else if (op >= 0x74 && op <= 0x78) {
                const ref2 = methodRefs[u16(buffer, insns + (q + 1) * 2)];
                if (ref2) edges++;
              }
              if (op >= 0x1a && op <= 0x1c) strConsts++;
              localCount++;
              q += width;
            }
            cls.instructionCount += localCount;
            cls.invokeEdges += edges;
            cls.stringConsts += strConsts;
            totalInstructions += localCount;
            totalInvokeEdges += edges;
            totalStringConsts += strConsts;
          }
        }
      };
      readMethods(dm, false); readMethods(vm, true);
      totalFields += cls.fieldCount;
    }
    classes.push(cls);
  }

  return {
    file,
    header,
    strings,
    types,
    methodRefs,
    fieldRefs,
    classes,
    stats: {
      classes: classes.length,
      methods: totalMethods,
      fields: totalFields,
      instructions: totalInstructions,
      invokeEdges: totalInvokeEdges,
      stringConstants: totalStringConsts,
    },
  };
}

function protoOf(buffer: Buffer, protoIdsOff: number, idx: number, types: string[]): string {
  const off = protoIdsOff + idx * 12;
  const ret = types[u32(buffer, off + 4)] ?? "V";
  const paramsOff = u32(buffer, off + 8);
  const params: string[] = [];
  if (paramsOff) {
    const n = u32(buffer, paramsOff);
    for (let i = 0; i < n; i++) params.push(types[u16(buffer, paramsOff + 4 + i * 2)] ?? "?");
  }
  return `(${params.join("")})${ret}`;
}

/** Package-level roll-up used by the census tables. */
export interface PackageStat {
  pkg: string;
  classes: number;
  methods: number;
  instructions: number;
}

export function packageHistogram(classes: DexClass[], depth = 3): PackageStat[] {
  const map = new Map<string, PackageStat>();
  for (const c of classes) {
    const segs = c.name.replace(/^L|;$/g, "").split("/");
    const pkg = segs.slice(0, Math.min(depth, segs.length - 0)).slice(0, -1).join("/") || "(default)";
    const key = pkg;
    const cur = map.get(key) ?? { pkg: key, classes: 0, methods: 0, instructions: 0 };
    cur.classes++;
    cur.methods += c.methodCount;
    cur.instructions += c.instructionCount;
    map.set(key, cur);
  }
  return [...map.values()].sort((a, b) => b.classes - a.classes);
}

export function obfuscationMetrics(classes: DexClass[]) {
  let short = 0;
  const lengths: number[] = [];
  for (const c of classes) {
    const simple = c.name.replace(/^L|;$/g, "").split("/").pop() ?? "";
    lengths.push(simple.length);
    if (simple.length <= 2) short++;
  }
  const avg = lengths.reduce((a, b) => a + b, 0) / Math.max(lengths.length, 1);
  return {
    classes: classes.length,
    shortNames: short,
    shortRatio: short / Math.max(classes.length, 1),
    avgSimpleNameLength: avg,
    sourceFilePresent: classes.filter((c) => c.sourceFile).length,
  };
}

/**
 * APK Signature Scheme v2 / v3 reader and independent verifier.
 *
 * Layout (AOSP apksig):
 *   APK Signing Block: uint64 size | (uint64 len, uint32 id, value)* | uint64 size | "APK Sig Block 42"
 *   v2 value: lp( signer* ), signer = lp(signedData) lp(signatures) lp(publicKey)
 *   v3 value: lp( signer* ), signer = lp(signedData) int32(minSdk) int32(maxSdk) lp(signatures) lp(publicKey)
 *   signedData = lp(digest*) lp(certificate*) lp(attribute*)
 *   digest     = lp( uint32 algId, lp(digest) )
 *   signature  = lp( uint32 algId, lp(signature) )
 *
 * Content digests are the chunked 1 MiB SHA-256/SHA-512 construction over the
 * three archive sections; each value is verified against node:crypto.
 */
import { createHash, createPublicKey, verify as cryptoVerify, X509Certificate } from "node:crypto";

const MAGIC = Buffer.from("APK Sig Block 42", "latin1");
export const V2_BLOCK_ID = 0x7109871a;
export const V3_BLOCK_ID = 0xf05368c0;
export const V31_BLOCK_ID = 0x1b93ad61;

type Kind = "rsa-pkcs1" | "rsa-pss" | "ecdsa" | "dsa" | "verity";

export const ALGORITHMS: Record<number, { name: string; hash: string; kind: Kind }> = {
  0x0101: { name: "RSASSA-PSS-SHA256", hash: "sha256", kind: "rsa-pss" },
  0x0102: { name: "RSASSA-PSS-SHA512", hash: "sha512", kind: "rsa-pss" },
  0x0103: { name: "RSASSA-PKCS1-v1_5-SHA256", hash: "sha256", kind: "rsa-pkcs1" },
  0x0104: { name: "RSASSA-PKCS1-v1_5-SHA512", hash: "sha512", kind: "rsa-pkcs1" },
  0x0201: { name: "ECDSA-SHA256", hash: "sha256", kind: "ecdsa" },
  0x0202: { name: "ECDSA-SHA512", hash: "sha512", kind: "ecdsa" },
  0x0301: { name: "DSA-SHA256", hash: "sha256", kind: "dsa" },
  0x0421: { name: "VERITY-RSASSA-PKCS1-v1_5-SHA256", hash: "sha256", kind: "verity" },
  0x0423: { name: "VERITY-ECDSA-SHA256", hash: "sha256", kind: "verity" },
  0x0425: { name: "VERITY-DSA-SHA256", hash: "sha256", kind: "verity" },
};

export interface SignerCertificate {
  sha256: string;
  subject: string;
  issuer: string;
  serialNumber: string;
  validFrom: string;
  validTo: string;
  keyType: string;
  keyBits?: number;
  selfSigned: boolean;
  signatureAlgorithm: string;
}

export interface Signer {
  signedDataDigests: { algorithm: number; algorithmName: string; digest: string }[];
  signatures: { algorithm: number; algorithmName: string; length: number; verified: boolean | null }[];
  certificates: SignerCertificate[];
  minSdk?: number;
  maxSdk?: number;
  publicKeySha256: string | null;
}

export interface SchemeResult {
  scheme: "v2" | "v3" | "v3.1";
  blockId: number;
  blockSize: number;
  signers: Signer[];
  contentDigestVerified: boolean | null;
  contentDigestAlgorithm: string | null;
  contentDigestExpected: string | null;
  contentDigestComputed: string | null;
  notes: string[];
}

export interface SigningReport {
  hasSigningBlock: boolean;
  blockSize: number;
  pairIds: { id: number; label: string; size: number }[];
  schemes: SchemeResult[];
  v1Present: boolean;
  zipEntryCount: number;
  wholeFileSha256: string;
}

class Cursor {
  constructor(public b: Buffer, public o = 0) {}
  get remaining() { return this.b.length - this.o; }
  u32() { const v = this.b.readUInt32LE(this.o); this.o += 4; return v; }
  lp(): Buffer {
    const len = this.u32();
    const out = this.b.subarray(this.o, this.o + len);
    this.o += len;
    return out;
  }
}

function parseCert(der: Buffer): SignerCertificate {
  const x = new X509Certificate(der);
  const key = x.publicKey;
  return {
    sha256: createHash("sha256").update(der).digest("hex"),
    subject: x.subject.replace(/\n/g, ", "),
    issuer: x.issuer.replace(/\n/g, ", "),
    serialNumber: x.serialNumber,
    validFrom: x.validFrom,
    validTo: x.validTo,
    keyType: key.asymmetricKeyType ?? "unknown",
    keyBits: (key.asymmetricKeyDetails as any)?.modulusLength,
    selfSigned: x.subject === x.issuer && x.checkIssued(x),
    signatureAlgorithm: (x as any).signatureAlgorithm ?? "unknown",
  };
}

export function findSigningBlock(buffer: Buffer, centralDirOffset: number): { offset: number; size: number } | null {
  if (centralDirOffset < 32) return null;
  const magicAt = centralDirOffset - 16;
  if (!buffer.subarray(magicAt, magicAt + 16).equals(MAGIC)) return null;
  const size = Number(buffer.readBigUInt64LE(magicAt - 8));
  const offset = centralDirOffset - size - 8;
  if (offset < 0) return null;
  return { offset, size };
}

export function chunkedContentDigest(buffer: Buffer, sections: [number, number][], hash: string): Buffer {
  const digests: Buffer[] = [];
  for (const [start, end] of sections) {
    const chunkDigests: Buffer[] = [];
    for (let p = start; p < end; p += 1048576) {
      const len = Math.min(1048576, end - p);
      const h = createHash(hash);
      h.update(Buffer.from([0xa5]));
      const l = Buffer.alloc(4);
      l.writeUInt32LE(len, 0);
      h.update(l);
      h.update(buffer.subarray(p, p + len));
      chunkDigests.push(h.digest());
    }
    const top = createHash(hash);
    top.update(Buffer.from([0x5a]));
    const cnt = Buffer.alloc(4);
    cnt.writeUInt32LE(chunkDigests.length, 0);
    top.update(cnt);
    for (const d of chunkDigests) top.update(d);
    digests.push(top.digest());
  }
  const final = createHash(hash);
  final.update(Buffer.from([0x5a]));
  const cnt = Buffer.alloc(4);
  cnt.writeUInt32LE(digests.length, 0);
  final.update(cnt);
  for (const d of digests) final.update(d);
  return final.digest();
}

function verifySignature(pubKeyDer: Buffer, alg: number, signedData: Buffer, sig: Buffer): boolean | null {
  const spec = ALGORITHMS[alg];
  if (!spec || spec.kind === "verity") return null;
  try {
    const key = createPublicKey({ key: pubKeyDer, format: "der", type: "spki" });
    const opts: any = { key };
    if (spec.kind === "rsa-pss") { opts.padding = 6; opts.saltLength = 32; }
    else if (spec.kind === "rsa-pkcs1") { opts.padding = 1; }
    else if (spec.kind === "ecdsa") { opts.dsaEncoding = "der"; }
    return cryptoVerify(spec.hash, signedData, key, sig);
  } catch {
    return null;
  }
}

function parseSigner(signerBlob: Buffer, scheme: "v2" | "v3" | "v3.1"): Signer {
  const c = new Cursor(signerBlob);
  const signedData = Buffer.from(c.lp());
  const signer: Signer = {
    signedDataDigests: [],
    signatures: [],
    certificates: [],
    publicKeySha256: null,
  };
  if (scheme !== "v2") {
    signer.minSdk = c.u32();
    signer.maxSdk = c.u32();
  }
  const sigsBlob = Buffer.from(c.lp());
  const pubKeyDer = Buffer.from(c.lp());
  signer.publicKeySha256 = createHash("sha256").update(pubKeyDer).digest("hex");

  const sd = new Cursor(signedData);
  const digestSeq = new Cursor(Buffer.from(sd.lp()));
  while (digestSeq.remaining >= 4) {
    const entry = new Cursor(Buffer.from(digestSeq.lp()));
    if (entry.remaining < 4) break;
    const alg = entry.u32();
    const digest = Buffer.from(entry.lp());
    signer.signedDataDigests.push({
      algorithm: alg,
      algorithmName: ALGORITHMS[alg]?.name ?? `0x${alg.toString(16)}`,
      digest: digest.toString("hex"),
    });
  }
  const certSeq = new Cursor(Buffer.from(sd.lp()));
  while (certSeq.remaining >= 4) {
    const der = Buffer.from(certSeq.lp());
    try { signer.certificates.push(parseCert(der)); } catch { /* malformed cert */ }
  }

  const sigSeq = new Cursor(sigsBlob);
  while (sigSeq.remaining >= 4) {
    const entry = new Cursor(Buffer.from(sigSeq.lp()));
    if (entry.remaining < 4) break;
    const alg = entry.u32();
    const sig = Buffer.from(entry.lp());
    signer.signatures.push({
      algorithm: alg,
      algorithmName: ALGORITHMS[alg]?.name ?? `0x${alg.toString(16)}`,
      length: sig.length,
      verified: verifySignature(pubKeyDer, alg, signedData, sig),
    });
  }
  return signer;
}

export function parseSigningBlock(
  buffer: Buffer,
  centralDirOffset: number,
  eocdOffset: number,
  zip: { entries: { name: string }[] },
): SigningReport {
  const report: SigningReport = {
    hasSigningBlock: false,
    blockSize: 0,
    pairIds: [],
    schemes: [],
    v1Present: zip.entries.some((e) => /^META-INF\/[^/]+\.(RSA|DSA|EC|SF)$/i.test(e.name)),
    zipEntryCount: zip.entries.length,
    wholeFileSha256: createHash("sha256").update(buffer).digest("hex"),
  };
  const block = findSigningBlock(buffer, centralDirOffset);
  if (!block) return report;
  report.hasSigningBlock = true;
  report.blockSize = block.size;

  // The APK Signing Block is excluded from the content digest: the first
  // section therefore ends where the signing block starts, not at the CD.
  const sections: [number, number][] = [
    [0, block.offset],
    [centralDirOffset, eocdOffset],
    [eocdOffset, buffer.length],
  ];

  let p = block.offset + 8;
  const pairsEnd = block.offset + 8 + block.size - 24;
  while (p + 12 <= pairsEnd) {
    const pairLen = Number(buffer.readBigUInt64LE(p));
    const id = buffer.readUInt32LE(p + 8);
    if (pairLen < 4) break;
    const value = buffer.subarray(p + 12, p + 12 + pairLen - 4);
    p += 8 + pairLen;

    const label = id === V2_BLOCK_ID ? "v2"
      : id === V3_BLOCK_ID ? "v3"
      : id === V31_BLOCK_ID ? "v3.1"
      : id === 0x6dff800d ? "source-stamp"
      : id === 0x2146444e ? "play-frosting"
      : id === 0x42726577 ? "padding"
      : `0x${id.toString(16)}`;
    report.pairIds.push({ id, label, size: pairLen });

    if (id !== V2_BLOCK_ID && id !== V3_BLOCK_ID && id !== V31_BLOCK_ID) continue;
    const scheme: SchemeResult["scheme"] = id === V2_BLOCK_ID ? "v2" : id === V3_BLOCK_ID ? "v3" : "v3.1";

    const outer = new Cursor(value);
    const signersSeq = new Cursor(Buffer.from(outer.lp()));
    const signers: Signer[] = [];
    while (signersSeq.remaining >= 4) {
      const signerBlob = Buffer.from(signersSeq.lp());
      signers.push(parseSigner(signerBlob, scheme));
    }

    let computed: string | null = null;
    let expected: string | null = null;
    let match: boolean | null = null;
    let algorithmName: string | null = null;
    const strongest = signers[0]?.signedDataDigests.find((d) => ALGORITHMS[d.algorithm]?.kind !== "verity")
      ?? signers[0]?.signedDataDigests[0];
    if (strongest) {
      const spec = ALGORITHMS[strongest.algorithm];
      const hash = spec?.hash ?? "sha256";
      algorithmName = spec?.name ?? "SHA-256 (assumed)";
      computed = chunkedContentDigest(buffer, sections, hash).toString("hex");
      expected = strongest.digest;
      match = computed === expected;
    }

    report.schemes.push({
      scheme,
      blockId: id,
      blockSize: pairLen,
      signers,
      contentDigestVerified: match,
      contentDigestAlgorithm: algorithmName,
      contentDigestExpected: expected,
      contentDigestComputed: computed,
      notes: [],
    });
  }
  return report;
}

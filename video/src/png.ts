/*
 * Encode PNG (RGBA, bit depth 8, color type 6, filter 0, interlace 0).
 * Dung zlib co san cua Node, khong can goi nhi phan ngoai.
 */
import * as zlib from 'zlib';

const PNG_SIG = Buffer.from([0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a]);

function crc32(buf: Buffer, start: number, end: number): number {
  let c = 0xffffffff;
  for (let i = start; i < end; i++) {
    c ^= buf[i];
    for (let k = 0; k < 8; k++) {
      c = (c & 1) ? (0xedb88320 ^ (c >>> 1)) : (c >>> 1);
    }
  }
  return (c ^ 0xffffffff) >>> 0;
}

function chunk(type: string, data: Buffer): Buffer {
  const lenBuf = Buffer.alloc(4);
  lenBuf.writeUInt32BE(data.length, 0);
  const typeBuf = Buffer.from(type, 'ascii');
  const body = Buffer.concat([typeBuf, data]);
  const crc = Buffer.alloc(4);
  crc.writeUInt32BE(crc32(body, 0, body.length) >>> 0, 0);
  return Buffer.concat([lenBuf, body, crc]);
}

export function encodePNG(width: number, height: number, rgba: Uint8Array): Buffer {
  const rowBytes = width * 4;
  const scanlines: Buffer[] = [];
  for (let y = 0; y < height; y++) {
    const row = Buffer.alloc(1 + rowBytes);
    row[0] = 0; // filter none
    row.set(rgba.subarray(y * rowBytes, (y + 1) * rowBytes), 1);
    scanlines.push(row);
  }
  const raw = Buffer.concat(scanlines);
  const idat = zlib.deflateSync(raw, { level: 9 });

  const ihdr = Buffer.alloc(13);
  ihdr.writeUInt32BE(width, 0);
  ihdr.writeUInt32BE(height, 4);
  ihdr[8] = 8;  // bit depth
  ihdr[9] = 6;  // color type RGBA
  ihdr[10] = 0; // compression
  ihdr[11] = 0; // filter
  ihdr[12] = 0; // interlace

  const parts: Buffer[] = [PNG_SIG, chunk('IHDR', ihdr), chunk('IDAT', idat), chunk('IEND', Buffer.alloc(0))];
  return Buffer.concat(parts);
}

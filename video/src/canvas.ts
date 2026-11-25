/*
 * Minimal bitmap canvas (RGBA, uint8). Co he chay duy nhat JS, khong can native module.
 */
export class TinyCanvas {
  readonly w: number;
  readonly h: number;
  readonly data: Uint8Array; // r,g,b,a per pixel

  constructor(w: number, h: number) {
    this.w = w;
    this.h = h;
    this.data = new Uint8Array(w * h * 4);
  }

  idx(x: number, y: number): number {
    return (y * this.w + x) * 4;
  }

  set(x: number, y: number, r: number, g: number, b: number, a = 255): void {
    if (x < 0 || x >= this.w || y < 0 || y >= this.h) return;
    const i = this.idx(x, y);
    this.data[i] = r;
    this.data[i + 1] = g;
    this.data[i + 2] = b;
    this.data[i + 3] = a;
  }

  fill(r: number, g: number, b: number, a = 255): void {
    for (let i = 0; i < this.data.length; i += 4) {
      this.data[i] = r;
      this.data[i + 1] = g;
      this.data[i + 2] = b;
      this.data[i + 3] = a;
    }
  }

  fillRect(x: number, y: number, w2: number, h2: number, r: number, g: number, b: number, a = 255): void {
    const x0 = Math.max(0, Math.floor(x));
    const y0 = Math.max(0, Math.floor(y));
    const x1 = Math.min(this.w, Math.floor(x + w2));
    const y1 = Math.min(this.h, Math.floor(y + h2));
    for (let yy = y0; yy < y1; yy++) {
      const base = yy * this.w * 4;
      for (let xx = x0; xx < x1; xx++) {
        const i = base + xx * 4;
        this.data[i] = r;
        this.data[i + 1] = g;
        this.data[i + 2] = b;
        this.data[i + 3] = a;
      }
    }
  }

  line(x0: number, y0: number, x1: number, y1: number, r: number, g: number, b: number, a = 255): void {
    // Bresenham
    let x = Math.floor(x0);
    let y = Math.floor(y0);
    const dx = Math.abs(x1 - x0);
    const dy = Math.abs(y1 - y0);
    const sx = x0 < x1 ? 1 : -1;
    const sy = y0 < y1 ? 1 : -1;
    let err = dx - dy;
    while (true) {
      this.set(x, y, r, g, b, a);
      if (x === Math.floor(x1) && y === Math.floor(y1)) break;
      const e2 = 2 * err;
      if (e2 > -dy) { err -= dy; x += sx; }
      if (e2 < dx) { err += dx; y += sy; }
    }
  }

  rect(x: number, y: number, w2: number, h2: number, r: number, g: number, b: number, a = 255): void {
    // viền chỉ
    this.line(x, y, x + w2 - 1, y, r, g, b, a);
    this.line(x, y + h2 - 1, x + w2 - 1, y + h2 - 1, r, g, b, a);
    this.line(x, y, x, y + h2 - 1, r, g, b, a);
    this.line(x + w2 - 1, y, x + w2 - 1, y + h2 - 1, r, g, b, a);
  }

  /**
   * Ve chuoi su dung font bitmap. Tra ve chieu dai pixel da ve (chua scale).
   */
  text(
    font: Record<string, string[]>,
    x: number,
    y: number,
    str: string,
    r: number,
    g: number,
    b: number,
    a = 255,
    scale = 1
  ): number {
    let cx = x;
    const chW = 5;
    const chH = 7;
    for (let ci = 0; ci < str.length; ci++) {
      const ch = str[ci];
      const glyph = font[ch] ?? font[' '] ?? [['.....','.....','.....','.....','.....','.....','.....'] as string[]];
      if (ch === ' ') {
        cx += 6;
        continue;
      }
      for (let row = 0; row < chH; row++) {
        const line = glyph[row];
        for (let col = 0; col < chW; col++) {
          if (line && line[col] === '#') {
            for (let dy = 0; dy < scale; dy++) {
              for (let dx = 0; dx < scale; dx++) {
                this.set(cx + col * scale + dx, y + row * scale + dy, r, g, b, a);
              }
            }
          }
        }
      }
      cx += (chW + 1) * scale;
    }
    return cx - x;
  }

  /**
   * Ve chuoi duoc wrap theo ky tu toi da tren moi dong, tra so dong da ve.
   */
  wrappedText(
    font: Record<string, string[]>,
    x: number,
    y: number,
    str: string,
    maxChars: number,
    r: number,
    g: number,
    b: number,
    a = 255,
    scale = 1,
    lineGap = 2
  ): number {
    const words = str.split(' ');
    let line = '';
    let cy = y;
    let totalLines = 0;
    const flush = () => {
      if (line.length > 0) {
        this.text(font, x, cy, line, r, g, b, a, scale);
        cy += (7 * scale) + lineGap;
        totalLines++;
        line = '';
      }
    };
    for (const w of words) {
      const trial = line ? line + ' ' + w : w;
      if (trial.length <= maxChars) {
        line = trial;
      } else {
        flush();
        line = w;
        if (line.length > maxChars) {
          // tu dai qua: cat manh
          while (line.length > maxChars) {
            this.text(font, x, cy, line.slice(0, maxChars), r, g, b, a, scale);
            cy += (7 * scale) + lineGap;
            totalLines++;
            line = line.slice(maxChars);
          }
        }
      }
    }
    flush();
    return cy - y;
  }
}

import fs from 'node:fs';
import path from 'node:path';
import { spawn, spawnSync } from 'node:child_process';
import { once } from 'node:events';
import { createCanvas, GlobalFonts, loadImage, type SKRSContext2D, type Image } from '@napi-rs/canvas';
import { loadChapters, validateFacts, type Chapter } from './validate.js';

// All compositions are drawn at 1280x720 and mastered to 1080p. The visual
// layer is deterministic and cached; only the chapter progress and fades move.
const W = 1280, H = 720;
const C = { bg: '#07121f', deep: '#091927', paper: '#f2f7fa', muted: '#a4b8c8', dim: '#6c8396',
  cyan: '#59dbe3', blue: '#4c9dff', gold: '#eab870', edge: '#29475a', panel: '#0d2232' };
const fontDir = 'video/assets/fonts';
for (const [file, family] of [
  ['BeVietnamPro-Regular.ttf', 'VN'], ['BeVietnamPro-Medium.ttf', 'VNMedium'],
  ['BeVietnamPro-SemiBold.ttf', 'VNSemi'], ['BeVietnamPro-Bold.ttf', 'VNBold'],
  ['IBMPlexMono-Regular.ttf', 'Mono'], ['IBMPlexMono-Medium.ttf', 'MonoMedium'],
]) if (!GlobalFonts.registerFromPath(path.join(fontDir, file), family)) throw new Error(`Cannot load font ${file}`);

function text(c: SKRSContext2D, value: string, x: number, y: number, size = 20, color = C.paper, family = 'VN', align: CanvasTextAlign = 'left') {
  c.save(); c.textAlign = align; c.textBaseline = 'alphabetic'; c.fillStyle = color; c.font = `${size}px "${family}"`; c.fillText(value, x, y); c.restore();
}
function lines(c: SKRSContext2D, values: string[], x: number, y: number, size = 19, color = C.muted, leading = 30, family = 'VN') {
  values.forEach((value, i) => text(c, value, x, y + i * leading, size, color, family));
}
function box(c: SKRSContext2D, x: number, y: number, w: number, h: number, fill = C.panel, stroke = C.edge, r = 14) {
  c.save(); c.beginPath(); c.roundRect(x, y, w, h, r); c.fillStyle = fill; c.fill();
  if (stroke) { c.strokeStyle = stroke; c.lineWidth = 1; c.stroke(); } c.restore();
}
function line(c: SKRSContext2D, x1: number, y1: number, x2: number, y2: number, color = C.edge, width = 1) {
  c.save(); c.strokeStyle = color; c.lineWidth = width; c.beginPath(); c.moveTo(x1, y1); c.lineTo(x2, y2); c.stroke(); c.restore();
}
function circle(c: SKRSContext2D, x: number, y: number, r: number, color: string, stroke = false) {
  c.save(); c.beginPath(); c.arc(x, y, r, 0, Math.PI * 2); if (stroke) { c.strokeStyle = color; c.lineWidth = 1.4; c.stroke(); } else { c.fillStyle = color; c.fill(); } c.restore();
}
function tag(c: SKRSContext2D, value: string, x: number, y: number, w: number, color = C.cyan) {
  box(c, x, y, w, 33, '#102b3a', '#2f5e6b', 7); text(c, value, x + w / 2, y + 22, 12, color, 'MonoMedium', 'center');
}
function title(c: SKRSContext2D, chapter: Chapter, subtitle: string[]) {
  const ts = chapter.title.split('\n');
  ts.forEach((v, i) => text(c, v, 72, 176 + i * 60, 47, C.paper, 'VNBold'));
  line(c, 72, 296, 125, 296, C.cyan, 3);
  lines(c, subtitle, 72, 335, 19, C.muted, 31);
}
function base(c: SKRSContext2D, chapter: Chapter, index: number, hero?: Image) {
  const grad = c.createLinearGradient(0, 0, W, H); grad.addColorStop(0, '#07121f'); grad.addColorStop(1, '#0a1a29');
  c.fillStyle = grad; c.fillRect(0, 0, W, H);
  if (hero) { c.save(); c.globalAlpha = index === 0 ? 0.72 : 0.25; c.drawImage(hero, 0, 0, W, H); c.restore();
    const fade = c.createLinearGradient(0, 0, W * 0.73, 0); fade.addColorStop(0, 'rgba(4,12,21,0.98)'); fade.addColorStop(0.68, 'rgba(4,12,21,0.82)'); fade.addColorStop(1, 'rgba(4,12,21,0.1)'); c.fillStyle = fade; c.fillRect(0, 0, W, H);
  } else {
    c.save(); c.strokeStyle = 'rgba(105,169,188,0.055)'; c.lineWidth = 1;
    for (let x = 640; x < W; x += 48) line(c, x, 90, x, 636, 'rgba(105,169,188,0.05)');
    for (let y = 96; y < 650; y += 48) line(c, 640, y, W, y, 'rgba(105,169,188,0.05)'); c.restore();
  }
  text(c, 'EVIDENCE / ANDROID', 72, 51, 12, C.cyan, 'MonoMedium');
  text(c, chapter.chapter, W - 72, 51, 12, C.muted, 'MonoMedium', 'right');
  line(c, 72, 70, W - 72, 70, '#274253');
  line(c, 72, 652, W - 72, 652, '#274253');
  text(c, chapter.source, 72, 678, 11, C.muted, 'Mono');
  text(c, `${String(index + 1).padStart(2, '0')} / 10`, W - 72, 678, 11, C.dim, 'Mono', 'right');
}
function bullet(c: SKRSContext2D, x: number, y: number, label: string, detail: string, accent = C.cyan) {
  circle(c, x, y - 5, 4, accent); text(c, label, x + 16, y, 16, C.paper, 'VNSemi'); text(c, detail, x + 16, y + 23, 13, C.muted, 'VN');
}
function scene(c: SKRSContext2D, ch: Chapter, n: number, hero: Image) {
  base(c, ch, n, n === 0 || n === 9 ? hero : undefined);
  switch (n) {
    case 0:
      text(c, 'NGHIÊN CỨU TÌNH HUỐNG  /  2026', 72, 139, 13, C.gold, 'MonoMedium');
      text(c, 'BÊN TRONG MỘT', 72, 213, 51, C.paper, 'VNBold');
      text(c, 'BẢN PHÂN PHỐI', 72, 280, 51, C.paper, 'VNBold');
      lines(c, ['Zalopay 11.17.0  •  Phân tích tĩnh Android', 'Bằng chứng, giới hạn và khả năng tái tạo.'], 74, 333, 19, C.muted, 32);
      box(c, 72, 448, 478, 91, 'rgba(8,28,42,0.84)', '#315c6d', 12);
      text(c, 'KHÔNG CÀI ĐẶT  /  KHÔNG GIAO DỊCH', 92, 482, 13, C.cyan, 'MonoMedium');
      text(c, 'Minh họa khái niệm • không phải ảnh ứng dụng', 92, 515, 14, C.muted, 'VN');
      break;
    case 1:
      title(c, ch, ['Không dừng lại ở lớp nén đầu tiên.', 'Mỗi lượt tệp đều có đường dẫn và SHA-256.']);
      text(c, '6.832', 72, 492, 75, C.cyan, 'VNBold');
      text(c, 'LƯỢT TỆP ĐÃ BĂM', 75, 522, 13, C.muted, 'MonoMedium');
      text(c, 'Bao gồm các tệp là kho chứa con.', 75, 554, 14, C.dim, 'VN');
      box(c, 696, 128, 510, 466, '#0b1d2b', '#2a4b5f', 18);
      const rows = [
        ['01', 'ZIP', '98.813.878 byte'], ['02', 'XAPK', '1 APK chính + 4 split'],
        ['03', 'APK / AMR', 'các thành phần và tài nguyên'], ['04', 'ZIP / TAR', 'lớp dữ liệu lồng bên trong'],
      ];
      rows.forEach((r, i) => { const y = 158 + i * 96; box(c, 724 + i * 14, y, 442 - i * 14, 72, '#102a3a', '#315268', 10); text(c, r[0], 744 + i * 14, y + 28, 12, C.cyan, 'MonoMedium'); text(c, r[1], 783 + i * 14, y + 31, 20, C.paper, 'VNSemi'); text(c, r[2], 783 + i * 14, y + 53, 13, C.muted, 'VN'); });
      break;
    case 2:
      title(c, ch, ['Ba tài nguyên .amr có cấu trúc ZIP.', 'Nội dung chứa TAR, không chỉ một tên tệp.']);
      tag(c, 'MAGIC BYTE > PHẦN MỞ RỘNG', 72, 438, 312, C.gold);
      lines(c, ['Kiểm kê theo định dạng nhận dạng được;', 'không thực thi nội dung bên trong.'], 74, 505, 16, C.dim, 27);
      box(c, 690, 155, 516, 389, '#0b1e2d', '#2b4b5d', 18);
      text(c, 'MỘT ĐƯỜNG ĐI BẰNG CHỨNG', 720, 194, 12, C.cyan, 'MonoMedium');
      const items = [['.amr', 'tên tệp'], ['ZIP', 'magic PK'], ['TAR', 'kho nội bộ'], ['JS / CSS', 'tài nguyên']];
      items.forEach((r, i) => { const x = 720 + i * 116; box(c, x, 246, 98, 120, '#123041', '#326375', 9); text(c, r[0], x + 49, 292, i === 3 ? 16 : 21, C.paper, 'VNBold', 'center'); text(c, r[1], x + 49, 326, 11, C.muted, 'VN', 'center'); if (i < 3) text(c, '›', x + 109, 314, 25, C.cyan, 'VN', 'center'); });
      text(c, '3 ZIP .amr  →  3 TAR  →  30 tệp', 720, 437, 17, C.paper, 'VNSemi');
      text(c, 'Sự hiện diện không chứng minh đã thực thi.', 720, 472, 14, C.muted, 'VN');
      break;
    case 3:
      title(c, ch, ['Chữ ký được kiểm tra trên byte của mẫu.', 'Nguồn gốc khóa chưa đối chiếu độc lập.']);
      text(c, '5 / 5', 72, 490, 72, C.cyan, 'VNBold'); text(c, 'APK ĐƯỢC APKSIGNER XÁC MINH', 76, 522, 12, C.muted, 'MonoMedium');
      box(c, 688, 132, 518, 453, '#0b1e2d', '#2e5365', 18);
      text(c, 'CERTIFICATE FINGERPRINT / SHA-256', 720, 176, 12, C.cyan, 'MonoMedium');
      lines(c, ['59dd31fdf36070a0d7f8c8cde843fac', '371df7f1a915c0136c9c9b55992e8cdae'], 720, 224, 17, C.paper, 32, 'Mono');
      line(c, 720, 269, 1174, 269, '#2b4b5d');
      tag(c, 'v2: VERIFIED', 720, 296, 174); tag(c, 'v3: VERIFIED', 912, 296, 174);
      bullet(c, 728, 384, 'REA: digest nhất quán', 'Không tự xác minh mật mã chữ ký.');
      bullet(c, 728, 462, 'Đối chứng âm: 1 bit bị đổi', 'apksigner trả về lỗi digest.', C.gold);
      break;
    case 4:
      title(c, ch, ['Tuyên bố trong manifest là điểm bắt đầu,', 'không phải quan sát lúc ứng dụng chạy.']);
      lines(c, ['Quyền có thể chưa được cấp.', 'Thành phần có thể có kiểm soát khác.'], 74, 476, 16, C.muted, 30);
      box(c, 680, 136, 526, 424, '#0b1e2d', '#2e5365', 18);
      const stats = [['49', 'TÊN QUYỀN'], ['267', 'THÀNH PHẦN'], ['30', 'EXPORTED TRUE']];
      stats.forEach((r, i) => { const x = 706 + i * 162; text(c, r[0], x, 235, 51, i === 2 ? C.gold : C.cyan, 'VNBold'); text(c, r[1], x + 2, 268, 10.5, C.muted, 'MonoMedium'); if (i < 2) line(c, x + 133, 181, x + 133, 285, '#2b4b5d'); });
      line(c, 706, 319, 1175, 319, '#2b4b5d');
      text(c, 'VÍ DỤ CÁC TÊN KHAI BÁO', 706, 360, 12, C.muted, 'MonoMedium');
      tag(c, 'CAMERA', 706, 390, 105); tag(c, 'AUDIO', 822, 390, 105); tag(c, 'CONTACTS', 938, 390, 117); tag(c, 'LOCATION', 1066, 390, 110);
      text(c, '30 chỉ tính literal exported="true".', 706, 485, 14, C.dim, 'VN');
      break;
    case 5:
      title(c, ch, ['Cấu hình cơ sở cho phép cleartext.', 'Chưa có bản ghi lưu lượng hay kiểm thử TLS.']);
      tag(c, 'CHÍNH SÁCH ≠ LƯU LƯỢNG', 72, 443, 293, C.gold);
      lines(c, ['Cần xác minh các stack mạng cụ thể', 'trong môi trường có ủy quyền.'], 74, 514, 16, C.dim, 27);
      box(c, 680, 136, 526, 421, '#0b1d2b', '#2e5365', 18);
      text(c, 'NETWORK_SECURITY_CONFIG.XML', 710, 178, 12, C.cyan, 'MonoMedium');
      line(c, 710, 198, 1176, 198, '#29495a');
      lines(c, ['<base-config', '  cleartextTrafficPermitted="true">', '  <trust-anchors>', '    <certificates src="system"/>', '  </trust-anchors>', '</base-config>'], 710, 245, 17, C.paper, 40, 'Mono');
      text(c, 'Trích đoạn • xem XML đầy đủ trong báo cáo', 710, 527, 13, C.muted, 'VN');
      break;
    case 6:
      title(c, ch, ['Mã managed, native và mô hình', 'có giới hạn quan sát khác nhau.']);
      text(c, '63.827', 72, 486, 61, C.cyan, 'VNBold');
      text(c, 'CLASS DEFINITION • TỔNG THEO DEX', 74, 521, 11, C.muted, 'MonoMedium');
      text(c, 'Bao gồm DEX trong assets; không khử trùng lặp.', 74, 552, 13, C.dim, 'VN');
      const code = [['20', 'DEX', '19 gốc + 1 assets'], ['27', 'ELF', 'ARM • 32 bit'], ['16', 'MÔ HÌNH', '.tfl / .tflite']];
      code.forEach((r, i) => { const x = 685 + i * 171; box(c, x, 181, 154, 260, '#0d2535', '#31576b', 14); text(c, r[0], x + 20, 259, 50, i === 1 ? C.gold : C.cyan, 'VNBold'); text(c, r[1], x + 20, 302, 16, C.paper, 'MonoMedium'); text(c, r[2], x + 20, 345, 12, C.muted, 'VN'); });
      text(c, 'THAM CHIẾU API ≠ LỆNH GỌI ĐÃ CHẠY', 687, 501, 12, C.muted, 'MonoMedium');
      break;
    case 7:
      title(c, ch, ['Một lát cắt từ manifest đến mã giả:', 'hoạt động chia sẻ ảnh cho QR.']);
      tag(c, 'QUAN SÁT TĨNH • KHÔNG KẾT LUẬN LỖ HỔNG', 72, 442, 398, C.gold);
      lines(c, ['JADX 1.1.0 báo một lỗi trong lần chạy;', 'kết quả cần đối chiếu bytecode.'], 74, 514, 15, C.dim, 27);
      box(c, 678, 142, 528, 416, '#0b1e2d', '#2e5365', 18);
      const qr = [['01', 'SEND', 'image/*'], ['02', 'URI', 'Intent'], ['03', 'INPUT IMAGE', 'fromFilePath'], ['04', 'BARCODE', 'process']];
      const qrPositions = [[702, 178], [940, 178], [940, 329], [702, 329]];
      qr.forEach((r, i) => { const [x, y] = qrPositions[i]; box(c, x, y, 211, 118, '#102b3c', '#315b70', 11); text(c, r[0], x + 17, y + 27, 11, C.cyan, 'MonoMedium'); text(c, r[1], x + 17, y + 61, 18, C.paper, 'VNBold'); text(c, r[2], x + 17, y + 89, 13, C.muted, 'Mono'); });
      text(c, '›', 926, 249, 23, C.cyan, 'VN', 'center'); text(c, '↓', 1044, 320, 23, C.cyan, 'VN', 'center'); text(c, '‹', 926, 400, 23, C.cyan, 'VN', 'center');
      break;
    case 8:
      title(c, ch, ['Phân tích tĩnh cho thấy hiện vật,', 'nhưng không bao phủ mọi đường chạy.']);
      text(c, '2.346', 72, 486, 66, C.cyan, 'VNBold'); text(c, 'LẦN XUẤT HIỆN CỦA __d(', 74, 521, 12, C.muted, 'MonoMedium');
      text(c, 'Không phải số module được thực thi.', 74, 552, 14, C.dim, 'VN');
      box(c, 680, 132, 526, 441, '#0b1e2d', '#2e5365', 18);
      text(c, 'PHẦN CHƯA QUAN SÁT', 712, 177, 12, C.cyan, 'MonoMedium');
      bullet(c, 721, 241, 'JavaScript: REA timeout', 'Phân tích ngữ nghĩa chỉ từng phần.', C.gold);
      bullet(c, 721, 321, 'Native & reflection', 'Tên và header không mô tả toàn bộ mã.');
      bullet(c, 721, 401, 'Mã tải từ xa & máy chủ', 'Không có kiểm thử runtime hay lưu lượng.');
      text(c, 'Không kết luận an toàn / không an toàn.', 712, 518, 14, C.muted, 'VN');
      break;
    case 9:
      text(c, 'MỘT KẾT QUẢ TỐT CẦN CÓ THỂ BỊ KIỂM TRA', 72, 139, 12, C.gold, 'MonoMedium');
      text(c, 'Bằng chứng', 72, 212, 52, C.paper, 'VNBold'); text(c, 'có thể kiểm tra.', 72, 279, 52, C.paper, 'VNBold');
      lines(c, ['Dấu băm, mã phân tích, đối chứng và giới hạn.', 'Không phải chứng nhận an toàn.'], 74, 335, 19, C.muted, 32);
      tag(c, 'BĂM', 72, 432, 105); tag(c, 'KIỂM KÊ', 189, 432, 123); tag(c, 'ĐỐI CHỨNG', 324, 432, 138); tag(c, 'GIỚI HẠN', 474, 432, 123, C.gold);
      text(c, 'Đọc báo cáo và trích dẫn nguồn trong kho dự án.', 74, 533, 16, C.muted, 'VN');
      break;
  }
}

interface Timing { id: string; start: number; voiceStart: number; voiceDuration: number; duration: number; file: string }
function ffmpegPath(): string {
  if (process.env.FFMPEG_PATH) return process.env.FFMPEG_PATH;
  const py = fs.existsSync('.venv/bin/python') ? '.venv/bin/python' : 'python3';
  const r = spawnSync(py, ['-c', 'import imageio_ffmpeg; print(imageio_ffmpeg.get_ffmpeg_exe())'], { encoding: 'utf8' });
  if (r.status !== 0) throw new Error('Install imageio-ffmpeg or set FFMPEG_PATH');
  return r.stdout.trim();
}
function audioDuration(ffmpeg: string, filename: string): number {
  const p = spawnSync(ffmpeg, ['-hide_banner', '-i', filename], { encoding: 'utf8' });
  const match = (p.stderr || '').match(/Duration:\s*(\d+):(\d+):(\d+(?:\.\d+)?)/);
  if (!match) throw new Error(`Cannot read audio duration: ${filename}`);
  return Number(match[1]) * 3600 + Number(match[2]) * 60 + Number(match[3]);
}
function srtTime(t: number): string {
  let ms = Math.max(0, Math.round(t * 1000)); const h = Math.floor(ms / 3600000); ms %= 3600000; const m = Math.floor(ms / 60000); ms %= 60000; const s = Math.floor(ms / 1000); ms %= 1000;
  return `${String(h).padStart(2, '0')}:${String(m).padStart(2, '0')}:${String(s).padStart(2, '0')},${String(ms).padStart(3, '0')}`;
}
function wrap(s: string, width = 44): string {
  const rows: string[] = []; let current = '';
  for (const word of s.split(/\s+/)) { if (current && (current.length + 1 + word.length > width)) { rows.push(current); current = word; } else current += (current ? ' ' : '') + word; }
  if (current) rows.push(current); return rows.join('\n');
}
function makeSrt(chapters: Chapter[], times: Timing[]): string {
  const result: string[] = []; let number = 1;
  chapters.forEach((ch, i) => {
    const sentences = ch.narration.match(/[^.!?]+[.!?]+|[^.!?]+$/g)?.map(s => s.trim()).filter(Boolean) || [ch.narration];
    const total = sentences.reduce((n, s) => n + s.length, 0); let at = times[i].voiceStart;
    sentences.forEach((s, j) => { const end = j === sentences.length - 1 ? times[i].voiceStart + times[i].voiceDuration : at + times[i].voiceDuration * s.length / total;
      result.push(`${number++}\n${srtTime(at)} --> ${srtTime(end)}\n${wrap(s)}\n`); at = end; });
  });
  return result.join('\n');
}
function option(name: string, fallback: string): string { const i = process.argv.indexOf(name); return i >= 0 && process.argv[i + 1] && !process.argv[i + 1].startsWith('--') ? process.argv[i + 1] : fallback; }

// Soft evidence highlights follow the chapter, without concealing or changing facts.
function motion(c: SKRSContext2D, index: number, local: number, duration: number) {
  const focus: Record<number, number[][]> = {
    1: [[724, 158, 442, 72], [738, 254, 428, 72], [752, 350, 414, 72], [766, 446, 400, 72]],
    2: [[720, 246, 98, 120], [836, 246, 98, 120], [952, 246, 98, 120], [1068, 246, 98, 120]],
    3: [[716, 292, 182, 41], [908, 292, 182, 41], [714, 355, 452, 56], [714, 435, 452, 56]],
    4: [[701, 176, 130, 112], [863, 176, 130, 112], [1025, 176, 130, 112], [702, 385, 478, 44]],
    5: [[705, 216, 480, 90], [705, 302, 480, 121]],
    6: [[685, 181, 154, 260], [856, 181, 154, 260], [1027, 181, 154, 260]],
    7: [[702, 178, 211, 118], [940, 178, 211, 118], [940, 329, 211, 118], [702, 329, 211, 118]],
    8: [[707, 210, 472, 62], [707, 290, 472, 62], [707, 370, 472, 62]],
  };
  const zones = focus[index]; if (!zones) return;
  const p = Math.max(0, Math.min(0.999, (local - 1.4) / Math.max(1, duration - 3)));
  const position = p * zones.length; const segment = Math.min(zones.length - 1, Math.floor(position));
  const pulse = Math.min(1, (position - segment) * 5, (1 - (position - segment)) * 5);
  const [x, y, w, h] = zones[segment]; c.save(); c.globalAlpha = 0.28 + 0.25 * Math.max(0, pulse);
  c.strokeStyle = C.cyan; c.lineWidth = 2; c.beginPath(); c.roundRect(x - 3, y - 3, w + 6, h + 6, 11); c.stroke(); c.restore();
}

async function main() {
  validateFacts(); const chapters = loadChapters();
  const hero = await loadImage('video/assets/forensic-hero.png');
  const frames = chapters.map((ch, i) => { const c = createCanvas(W, H); scene(c.getContext('2d'), ch, i, hero); return c; });
  if (process.argv.includes('--preview')) {
    const i = Number(option('--preview', '0')); if (!Number.isInteger(i) || i < 0 || i >= frames.length) throw new Error('Preview index must be 0–9');
    fs.mkdirSync('.work', { recursive: true }); const f = `.work/preview-${i}.png`; fs.writeFileSync(f, frames[i].toBuffer('image/png')); console.log(f); return;
  }
  const ffmpeg = ffmpegPath(); const audioDir = option('--audio-dir', 'video/audio'); const out = option('--out', 'deliverables/zalopay-evidence-film.mp4');
  const quick = process.argv.includes('--quick'); const fps = quick ? 12 : 24;
  const times: Timing[] = []; let cursor = 0;
  chapters.forEach((ch, i) => {
    const file = path.join(audioDir, `${String(i + 1).padStart(2, '0')}.mp3`);
    if (!fs.existsSync(file)) throw new Error(`Missing narration: ${file}`);
    const voiceDuration = audioDuration(ffmpeg, file); const duration = Math.ceil((voiceDuration + (i === 9 ? 4.2 : 2.5)) * fps) / fps;
    times.push({ id: ch.id, start: cursor, voiceStart: cursor + 0.9, voiceDuration, duration, file }); cursor += duration;
  });
  const outputDir = path.dirname(out); fs.mkdirSync(outputDir, { recursive: true }); fs.mkdirSync('.work', { recursive: true });
  const timelineFile = path.join(outputDir, 'timeline.json'); const subtitleFile = path.join(outputDir, 'subtitles.vi.srt');
  fs.writeFileSync(timelineFile, JSON.stringify({ fps, duration: cursor, chapters: times, captions: 'Sentence timings are estimated proportionally from each clip duration.' }, null, 2) + '\n');
  fs.writeFileSync(subtitleFile, makeSrt(chapters, times));
  const music = '.work/original-score.wav'; const py = fs.existsSync('.venv/bin/python') ? '.venv/bin/python' : 'python3';
  const score = spawnSync(py, ['video/music.py', timelineFile, music], { stdio: 'inherit' }); if (score.status !== 0) throw new Error('Score generation failed');
  const args = ['-hide_banner', '-loglevel', 'error', '-y', '-f', 'rawvideo', '-pix_fmt', 'rgba', '-s', `${W}x${H}`, '-r', String(fps), '-i', 'pipe:0', '-i', music];
  for (const t of times) args.push('-i', t.file);
  args.push('-i', subtitleFile);
  const filters = times.map((t, i) => `[${i + 2}:a]aresample=48000,adelay=${Math.round(t.voiceStart * 1000)}:all=1[v${i}]`);
  filters.push(times.map((_, i) => `[v${i}]`).join('') + `amix=inputs=${times.length}:duration=longest:normalize=0,apad,atrim=0:${cursor.toFixed(3)},highpass=f=75,lowpass=f=16000,acompressor=threshold=0.12:ratio=2:attack=20:release=250[voice]`);
  filters.push(`[1:a]volume=0.24,atrim=0:${cursor.toFixed(3)}[bed]`);
  filters.push('[voice][bed]amix=inputs=2:duration=longest:normalize=0,loudnorm=I=-16:TP=-1.5:LRA=11[aout]');
  args.push('-filter_complex', filters.join(';'), '-map', '0:v:0', '-map', '[aout]', '-map', `${times.length + 2}:s:0`,
    '-vf', quick ? 'format=yuv420p' : 'scale=1920:1080:flags=lanczos,format=yuv420p',
    '-c:v', 'libx264', '-preset', quick ? 'ultrafast' : 'veryfast', '-crf', quick ? '27' : '19',
    '-c:a', 'aac', '-b:a', '192k', '-ar', '48000', '-c:s', 'mov_text', '-metadata:s:s:0', 'language=vie',
    '-metadata:s:s:0', 'title=Phụ đề tiếng Việt (thời gian ước lượng)', '-disposition:s:0', 'default',
    '-movflags', '+faststart', '-t', cursor.toFixed(3), out);
  console.log(`Rendering ${cursor.toFixed(1)} seconds, ${fps} fps → ${out}`);
  const encoder = spawn(ffmpeg, args, { stdio: ['pipe', 'ignore', 'inherit'] });
  const done = new Promise<number>((resolve, reject) => { encoder.on('error', reject); encoder.on('close', code => resolve(code ?? 1)); });
  encoder.stdin.on('error', () => {}); // close is checked below; EPIPE must not obscure encoder errors.
  const canvas = createCanvas(W, H); const c = canvas.getContext('2d'); let chapterIndex = 0;
  const totalFrames = Math.ceil(cursor * fps);
  for (let frame = 0; frame < totalFrames; frame++) {
    const time = frame / fps;
    while (chapterIndex < times.length - 1 && time >= times[chapterIndex].start + times[chapterIndex].duration) chapterIndex++;
    const local = time - times[chapterIndex].start; const duration = times[chapterIndex].duration;
    // A slow virtual camera move and evolving evidence highlights keep the
    // composition alive without distracting from the narrated claims.
    const scale = 1 + 0.012 * Math.min(1, local / duration);
    c.save(); c.translate(W * (1 - scale) / 2, H * (1 - scale) / 2); c.scale(scale, scale);
    c.drawImage(frames[chapterIndex], 0, 0); motion(c, chapterIndex, local, duration); c.restore();
    const opacity = Math.max(0, Math.min(1, local / 0.75, (duration - local) / 0.75));
    c.fillStyle = `rgba(2,8,14,${(1 - opacity).toFixed(3)})`; c.fillRect(0, 0, W, H);
    c.fillStyle = '#173445'; c.fillRect(72, 700, W - 144, 3);
    c.fillStyle = C.cyan; c.fillRect(72, 700, (W - 144) * time / cursor, 3);
    if (!encoder.stdin.write(canvas.data())) await once(encoder.stdin, 'drain');
    if (frame % (fps * 30) === 0) console.log(`  ${time.toFixed(0)} / ${cursor.toFixed(0)} seconds`);
  }
  encoder.stdin.end(); const code = await done; if (code !== 0) throw new Error(`ffmpeg failed with exit ${code}`);
  console.log(`Done: ${out}`);
}
main().catch(error => { console.error(error); process.exitCode = 1; });

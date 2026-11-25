/*
 * Xay dựng các slide (Anh 1280x720) từ dữ liệu reverse engineering thật.
 * Moi slide tra ve {name, durationSec, canvas}.
 */
import { TinyCanvas } from './canvas';
import * as font from './font';
import { encodePNG } from './png';

const W = 1280;
const H = 720;
const F = font.FONT;

const C_BG = [18, 22, 30] as const;
const C_PANEL = [30, 36, 48] as const;
const C_BAR = [26, 32, 44] as const;
const C_ACCENT = [0, 150, 220] as const;
const C_ACCENT2 = [16, 185, 129] as const;
const C_TEXT = [235, 240, 245] as const;
const C_MUTED = [150, 160, 170] as const;
const C_ORANGE = [255, 159, 67] as const;
const C_RED = [248, 98, 98] as const;

function base(c: TinyCanvas, kicker: string, title: string): void {
  c.fill(C_BG[0], C_BG[1], C_BG[2]);
  // thanh tieu de
  c.fillRect(0, 0, W, 72, C_BAR[0], C_BAR[1], C_BAR[2]);
  c.fillRect(0, 72, W, 5, C_ACCENT[0], C_ACCENT[1], C_ACCENT[2]);
  c.text(F, 40, 30, kicker, 110, 150, 190, 255, 1);
  c.text(F, 40, 52, title, 245, 245, 245, 255, 2);
  // duong chia noi dung
  c.fillRect(0, 108, W, 1, 50, 60, 75);
  // chan
  c.fillRect(0, H - 36, W, 36, C_BAR[0], C_BAR[1], C_BAR[2]);
  c.text(F, 40, H - 22, 'Reverse engineering  —  ZaloPay 11.17.0  |  vn.com.vng.zalopay', 110, 140, 170, 255, 1);
  c.text(F, W - 320, H - 22, 'MP4  720p30', 110, 140, 170, 255, 1);
}

function panel(c: TinyCanvas, x: number, y: number, w: number, h: number): void {
  c.fillRect(x, y, w, h, C_PANEL[0], C_PANEL[1], C_PANEL[2]);
  c.rect(x, y, w, h, 60, 72, 90, 255);
}

function heading(c: TinyCanvas, x: number, y: number, text: string, color: readonly [number, number, number] = C_ACCENT): void {
  c.text(F, x, y, text, color[0], color[1], color[2], 255, 1);
}

function bullet(c: TinyCanvas, x: number, y: number, text: string, maxChars: number, color: readonly [number, number, number] = C_TEXT): void {
  c.text(F, x, y, '>  ', C_ORANGE[0], C_ORANGE[1], C_ORANGE[2], 255, 1);
  c.wrappedText(F, x + 30, y, text, maxChars, color[0], color[1], color[2], 255, 1);
}

export interface Slide {
  name: string;
  durationSec: number;
  canvas: TinyCanvas;
}

export function buildSlides(): Slide[] {
  const slides: Slide[] = [];

  // ---------- 1. TRANG TIEU DE ----------
  {
    const c = new TinyCanvas(W, H);
    c.fill(...C_BG);
    c.fillRect(0, 0, W, 100, ...C_BAR);
    c.fillRect(0, 100, W, 6, ...C_ACCENT);
    c.text(F, 90, 150, 'DAU TIEN', C_ORANGE[0], C_ORANGE[1], C_ORANGE[2], 255, 2);
    c.text(F, 90, 210, 'DICH NGUOC ZALOPAY 11.17.0', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 3);
    c.text(F, 90, 265, 'Bao cao ky thuat toan dien', 170, 180, 200, 255, 1);
    c.text(F, 90, 310, 'Goi: vn.com.vng.zalopay   |   Phien ban: 11.17.0 (ma 1102758)', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    c.text(F, 90, 330, 'Kich thuoc: 79,359,087 byte   |   SHA-256: e289d5b4...', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    c.text(F, 90, 350, 'targetSdk 36 (Android 14)   |   minSdk 26', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    c.text(F, 90, 430, 'Phuong phap: giai nen APK/XAPK, doc zip file, androguard (APK), parser DEX thu Python', 150, 160, 180, 255, 1);
    c.text(F, 90, 450, 'Khong can Java / apktool / JDK. Toan bo phan tich chay tren moi truong toi.', 150, 160, 180, 255, 1);
    slides.push({ name: 'slide00-title.png', durationSec: 9, canvas: c });
  }

  // ---------- 2. TONG QUAN ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'TONG QUAN APK', 'Tap tin va cau truc');
    heading(c, 60, 130, '6,162 tap tin co trong base APK', C_ACCENT2);
    c.text(F, 60, 155, '20 tap DEX (multi-dex)   |   0 thu vien native (.so)', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    bullet(c, 60, 185, 'Giao dien: Kotlin + Jetpack Compose (META-INF co compose, lifecycle, navigation...)', 115);
    bullet(c, 60, 215, 'Mang: Retrofit + OkHttp (REST), Dagger (DI chen thu tu), Room, WorkManager', 115);
    bullet(c, 60, 245, 'Quang cao: Facebook Audience Network (audience_network.dex)', 115);
    panel(c, 60, 285, 560, 150);
    c.text(F, 80, 300, 'KHONG CO THU VIEN NATIVE', C_RED[0], C_RED[1], C_RED[2], 255, 1);
    c.text(F, 80, 320, 'Ma duoc thuc thinh chinh la Java/Kotlin', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 80, 340, 'Dong goi boi APKPure (xapk: base + config splits ARM/en/vi/xhdpi)', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    slides.push({ name: 'slide01-overview.png', durationSec: 8, canvas: c });
  }

  // ---------- 3. MANIFEST ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'ANDROIDMANIFEST.XML', 'Bo thanh phan & quyen');
    heading(c, 60, 130, '49 quyen  |  200 Activity  |  34 Service  |  22 Receiver  |  9 Provider', C_ACCENT2);
    bullet(c, 60, 165, 'Quyen nhay cam: CAMERA, RECORD_AUDIO, READ_CONTACTS, INTERNET,', 115);
    c.text(F, 105, 185, 'QUERY_ALL_PACKAGES, WRITE_EXTERNAL_STORAGE, USE_BIOMETRIC, USE_FINGERPRINT', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    bullet(c, 60, 215, 'Co MANAGE_OVERLAY_PERMISSION (hien thi len cac ung dung khac) va WAKE_LOCK', 115);
    bullet(c, 60, 245, 'Quyen vi tri (FINE/COARSE), dien thoai (READ_PHONE_STATE), luu tru', 115);
    panel(c, 60, 280, 760, 150);
    c.text(F, 80, 295, 'DANH GIA', C_ORANGE[0], C_ORANGE[1], C_ORANGE[2], 255, 1);
    c.text(F, 80, 315, 'Nhieu quyen nhay cam can liet ke ke toan va minh chung muc dich su dung', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 80, 335, 'targetSdk 36 (Android 14) -> bat buoc dam bao cac quyen runtime moi', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    slides.push({ name: 'slide02-manifest.png', durationSec: 9, canvas: c });
  }

  // ---------- 4. DEX ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'CAC TEP DEX (MULTI-DEX)', 'Lop & phuong thuc');
    heading(c, 60, 130, '63,827 lop   |   378,040 phuong thuc   |   404,914 xau', C_ACCENT2);
    c.text(F, 60, 160, 'classes2.dex lon nhat: 16,516,960 byte (7,421 lop, 45,527 phuong thuc)', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 60, 180, 'classes7.dex: 10,803,680 byte | classes4.dex: 9,418,124 byte', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    c.text(F, 60, 200, 'string_ids 404,914  |  type_ids 119,197  |  field_ids 317,841  |  proto_ids 105,000', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    panel(c, 60, 235, 760, 150);
    c.text(F, 80, 250, 'DANH GIA', C_ORANGE[0], C_ORANGE[1], C_ORANGE[2], 255, 1);
    c.text(F, 80, 270, 'Multi-dex lon co the chua ma hoa / ma ao, kho dich nguc toan bo bang cong cu thong thuong', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 80, 290, 'Can tich hop static analysis voi dynamic tracing de xac nhan luot goi', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    slides.push({ name: 'slide03-dex.png', durationSec: 9, canvas: c });
  }

  // ---------- 5. TECH STACK ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'NGU NGU & THU VIEN', 'Stack ky thuat');
    bullet(c, 60, 130, 'Retrofit + OkHttp: goi REST API (mang chinh)', 115);
    bullet(c, 60, 160, 'Dagger: chen phu thu tu, DI trong ma', 115);
    bullet(c, 60, 190, 'Room, WorkManager, Navigation, Paging, Datastore, Security, PrivacySandbox', 115);
    bullet(c, 60, 220, 'Kotlin + Jetpack Compose: giao dien tu chieu', 115);
    panel(c, 60, 260, 760, 180);
    c.text(F, 80, 275, 'AN TOAN / NHAN DIEN MAT (KYC)', C_ACCENT2[0], C_ACCENT2[1], C_ACCENT2[2], 255, 1);
    c.text(F, 80, 295, 'ML Kit (tflite, OCR taser/gocr), TrueFace/TrueID (trueface, trueid)', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 80, 315, 'OpenCV haarcascade_frontalface_default.xml, ncnn FastestDet (phat hien mat)', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 80, 335, 'Dung cho xac nhan danh thanh toan / KYC', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    c.text(F, 60, 430, 'Facebook Audience Network: quang bao trong ung dung', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    slides.push({ name: 'slide04-stack.png', durationSec: 10, canvas: c });
  }

  // ---------- 6. DIEN DAN ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'DIEN DAN & KET NOI', '1,380 duong dan http/https');
    bullet(c, 60, 130, 'Tim thay 1,380 duong dan http/https trong DEX, manifest, tai nguyen', 115);
    bullet(c, 60, 160, 'Cua chinh ZaloPay: onelink.zalopay.vn, html.zapps.vn (trung tam ho tro)', 115);
    bullet(c, 60, 190, 'Dien dan thu vien / SDK: alipay, vnpay, trueid.net, facebook oauth', 115);
    bullet(c, 60, 220, 'Co cau mau %sadrevenue, %sapp, %sars (SDK quang bao, can xac minh)', 115);
    bullet(c, 60, 250, 'Nhieu la duong dan he thong (schemas.android.com, w3.org, xml.org) - keuong', 115);
    panel(c, 60, 285, 760, 150);
    c.text(F, 80, 300, 'KHUYEN NGHIO', C_RED[0], C_RED[1], C_RED[2], 255, 1);
    c.text(F, 80, 320, 'Kiem tra cac endpoint thuc te, kiem soat truy cap va mat ma mat khau trong ma', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    slides.push({ name: 'slide05-endpoints.png', durationSec: 11, canvas: c });
  }

  // ---------- 7. QUYEN ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'QUYEN NHAY CAM', 'Liet ke ke toan');
    const perms = [
      'android.permission.CAMERA',
      'android.permission.RECORD_AUDIO',
      'android.permission.READ_CONTACTS',
      'android.permission.INTERNET',
      'android.permission.QUERY_ALL_PACKAGES',
      'android.permission.WRITE_EXTERNAL_STORAGE',
      'android.permission.READ_EXTERNAL_STORAGE',
      'android.permission.ACCESS_FINE_LOCATION',
      'android.permission.USE_BIOMETRIC',
      'android.settings.action.MANAGE_OVERLAY_PERMISSION',
    ];
    let yy = 130;
    for (let i = 0; i < perms.length; i++) {
      const col = i % 2;
      const row = Math.floor(i / 2);
      const x = 60 + col * 560;
      const y = yy + row * 26;
      c.text(F, x, y, '>  ', C_ORANGE[0], C_ORANGE[1], C_ORANGE[2], 255, 1);
      c.text(F, x + 30, y, perms[i], C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    }
    panel(c, 60, 420, 760, 110);
    c.text(F, 80, 435, 'DANH GIA', C_RED[0], C_RED[1], C_RED[2], 255, 1);
    c.text(F, 80, 455, 'Nhieu quyen nhay cam can xac minh muc dich va dam bao nguoi dung duoc thong bao', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    slides.push({ name: 'slide06-perms.png', durationSec: 9, canvas: c });
  }

  // ---------- 8. KYC ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'NHAN DIEN MAT / KYC', 'Co so du lieu AI & mo hinh');
    bullet(c, 60, 130, 'ML Kit: cac mo hinh tflite (OCR taser, gocr, script detection)', 115);
    bullet(c, 60, 160, 'TrueFace / TrueID: assets trueface, trueid (xac nhan danh thanh toan)', 115);
    bullet(c, 60, 190, 'OpenCV: res/raw/haarcascade_frontalface_default.xml', 115);
    bullet(c, 60, 220, 'ncnn: assets/FastestDet.ncnn.bin (phat hien mat nhanh)', 115);
    bullet(c, 60, 250, 'LBF model: res/raw/lbfmodel.yaml (diem landmarks mat)', 115);
    panel(c, 60, 285, 760, 150);
    c.text(F, 80, 300, 'DANH GIA', C_ORANGE[0], C_ORANGE[1], C_ORANGE[2], 255, 1);
    c.text(F, 80, 320, 'Tap hop thue nhan dien mat/KYC rong de xac thuc nguoi dung va giao dich', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 80, 340, 'Can kiem tra bao ve mo hinh va du lieu khuon mat luu tru', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    slides.push({ name: 'slide07-kyc.png', durationSec: 9, canvas: c });
  }

  // ---------- 9. KET LUAN ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'KET LUAN & BAO MAT', 'Tam tắt');
    bullet(c, 60, 130, 'Multi-dex lon (63,827 lop), target Android 14 (SDK 36)', 115);
    bullet(c, 60, 160, '49 quyen, nhieu quyen nhay cam (camera, microphone, contacts, luu tru)', 115);
    bullet(c, 60, 190, 'Co KYC nhan dien mat (TrueFace/TrueID, ML Kit, OpenCV) va quang bao Facebook', 115);
    bullet(c, 60, 220, 'Khong co thu vien native (.so) trong base APK', 115);
    bullet(c, 60, 250, '1,380 duong dan ket noi duoc tim thay - can xac minh endpoint chinh chu', 115);
    panel(c, 60, 285, 760, 110);
    c.text(F, 80, 300, 'KET LUAN', C_ACCENT2[0], C_ACCENT2[1], C_ACCENT2[2], 255, 1);
    c.text(F, 80, 320, 'Ung dung tai chinh tich hop sau nhieu cong nghe mat, can kiem tra bao mat kiem tra', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    c.text(F, 60, 410, 'Chi tiet toan dien: REPORT.md  |  analysis.json  |  apk_inventory.json', C_MUTED[0], C_MUTED[1], C_MUTED[2], 255, 1);
    slides.push({ name: 'slide08-conclusion.png', durationSec: 9, canvas: c });
  }

  // ---------- 10. CAY TAO ----------
  {
    const c = new TinyCanvas(W, H);
    base(c, 'CAY TAO VIDEO NAY', 'Cong nghe & dau ra');
    bullet(c, 60, 130, 'Ngu ngu: TypeScript (Node.js 22), toan bo code o trong kho', 115);
    bullet(c, 60, 160, 'Hoa anh: ffmpeg.wasm 0.11 (WebAssembly, chay trong bo nho, khong can ffmpeg he thong)', 115);
    bullet(c, 60, 190, 'Am thanh: dien tu tu trinh toan (WAV 44.1 kHz stereo, pad/bass/melody/nhac kep)', 115);
    bullet(c, 60, 220, 'Anh: font bitmap 5x7 + encoder PNG thu JS (khong can cai dat font/native)', 115);
    bullet(c, 60, 250, 'Xuat: MP4 (libx264 + AAC), 720p30, toan bo hoa tong o bo nho (MEMFS)', 115);
    panel(c, 60, 285, 760, 150);
    c.text(F, 80, 300, 'AM NHAN', C_ORANGE[0], C_ORANGE[1], C_ORANGE[2], 255, 1);
    c.text(F, 80, 320, 'Khong goi API/CDN ngoai. Toan bo tai nguyen duoc tao bo cua chinh trinh trinh toan.', C_TEXT[0], C_TEXT[1], C_TEXT[2], 255, 1);
    slides.push({ name: 'slide09-credits.png', durationSec: 10, canvas: c });
  }

  // luu ra file PNG
  const fs = require('fs');
  fs.mkdirSync('/home/user/-/video/assets/slides', { recursive: true });
  for (const s of slides) {
    const buf = encodePNG(s.canvas.w, s.canvas.h, s.canvas.data);
    fs.writeFileSync(`/home/user/-/video/assets/slides/${s.name}`, Buffer.from(buf));
  }
  console.log(`Da luu ${slides.length} slide PNG vao assets/slides`);

  return slides;
}

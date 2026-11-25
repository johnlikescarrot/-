export interface Slide {
  id: string;
  title: string;
  subtitle?: string;
  bullets?: string[];
  durationSeconds: number;
}

export const fps = 30;
export const width = 1920;
export const height = 1080;

export const slides: Slide[] = [
  { id: 'intro', title: 'Zalopay 11.17.0 — Toàn diện Reverse Engineering', subtitle: 'Phân tích tĩnh toàn bộ tệp trong gói XAPK/APK', durationSeconds: 5 },
  { id: 'overview', title: 'Tổng quan gói', durationSeconds: 5 },
  { id: 'inventory', title: 'Inventory ngoại vi', durationSeconds: 5 },
  { id: 'classification', title: 'Phân loại 6.464 tệp', durationSeconds: 5 },
  { id: 'permissions', title: 'Permissions', durationSeconds: 5 },
  { id: 'components', title: 'Components', durationSeconds: 5 },
  { id: 'cert', title: 'Chữ ký / Certificate', durationSeconds: 5 },
  { id: 'packages', title: 'Top packages', durationSeconds: 5 },
  { id: 'network', title: 'Network endpoints', durationSeconds: 5 },
  { id: 'native', title: 'Native libraries', durationSeconds: 5 },
  { id: 'conclusion', title: 'Kết luận & Khuyến nghị', durationSeconds: 5 },
];

export const totalDurationSeconds = slides.reduce((s, x) => s + x.durationSeconds, 0);

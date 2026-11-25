# Research film — engine

Bộ phim 4:36 được **vẽ hoàn toàn bằng code TypeScript** trên Skia (`@napi-rs/canvas`),
không dùng After Effects, không dùng ảnh stock. Mọi pixel và mọi con số đều sinh từ
`research/data/film.json`.

```
src/
├── data.ts        # nạp film.json, ép kiểu, helper định dạng (fmt/pct/mib/kib/shortHash)
├── theme.ts       # 1920×1080@30, palette, type scale, layout grid
├── lib.ts         # đồ hoạ: panel/text/wrap/mono/code/badge/dot/bars/stat/underline/chrome
├── scenes/        # 7 act, mỗi file export draw*() + *Duration + *Chapter
│   ├── intro.ts      22 s  tiêu đề, SHA-256, câu hỏi nghiên cứu, ba luận điểm
│   ├── container.ts  40 s  split graph, treemap payload, DEX bars, căn lề ZIP
│   ├── code.ts       44 s  quy mô bytecode, giải phẫu DEX, trần 16-bit, obfuscation, SDK
│   ├── native.ts     44 s  treemap ELF, hardening matrix, entropy, crypto/PQC, ML
│   ├── dataflow.ts   44 s  topology host, mini-app, chuỗi deep-link, quyền
│   ├── integrity.ts  42 s  chứng chỉ, hex dump khối chữ ký, ba khuyết tật, phán quyết
│   └── conclusion.ts 40 s  9 phát hiện, phương pháp 5 bước, thẻ trích dẫn, BibTeX
├── cc.ts          # phụ đề theo từng nhịp hình (SRT + VTT)
├── audio.ts       # tổng hợp nhạc nền 48 kHz từ timeline (pad + sub + bell + air)
├── main.ts        # timeline → RGBA pipe → libx264 → AAC → MP4, ghi chapters.json
└── stills.ts      # xuất khung tĩnh để review layout (không thuộc deliverable)
```

## Lệnh

```bash
npm run typecheck     # tsc --noEmit
npm run build         # tsc → video/dist
npm run render:audio  # work/render/score.wav (24-bit/48k, ~76 MiB)
npm run render        # full MP4 + SRT/VTT + chapters.json → artifacts/
node video/dist/main.js --mux-only   # chỉ ghép lại audio/video đã có
node video/dist/stills.js            # 21 khung PNG → work/stills/
```

## Nguyên tắc thiết kế

1. **Số liệu dẫn dắt hình ảnh.** Không có hình trang trí nào không mang một con số.
2. **Một câu chuyện mỗi cảnh.** Mỗi cảnh có đúng một tiêu đề khẳng định + một biểu đồ.
3. **Chuyển động có nghĩa.** Mọi animation là `seg(t, a, b)` trên timeline cục bộ của
   cảnh, nên tua tới bất kỳ giây nào cũng cho khung hình đúng.
4. **Âm thanh phục vụ nhịp.** Pad 4 hợp âm 22 s/lần, sub-bass đánh dấu ranh giới act,
   chuông pentatonic mở mỗi act. Không có tiếng động nào che lời.
5. **Tái lập tuyệt đối.** Không random theo thời gian thực; mọi "nhiễu" lấy từ `rng(seed)`.

## Sai số đã biết

- `stills.ts` render ở `t` cố định; đó là ảnh review, không phải frame của video.
- Âm thanh tổng hợp không có sidechain; mức -14 LUFS đo bằng `ebur128` sau khi render.

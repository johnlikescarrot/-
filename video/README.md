# Video Artifact

Video được tạo bởi pipeline Python + TypeScript metadata.

- `generate_video.py`: render khung hình (Pillow), tổng hợp âm thanh (NumPy), mux MP4 (imageio-ffmpeg).
- `src/composition.ts`: mô tả composition/slide bằng TypeScript.
- Output: `zalopay-re-11.17.0.mp4`.

Chạy:
```bash
../work/venv/bin/python generate_video.py
```

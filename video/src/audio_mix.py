#!/usr/bin/env python3
"""
Builds the studio-mastered documentary audio track + synchronized scene manifest
+ bilingual SRT subtitles (English & Vietnamese).
Combines neural speech segments (generated via generate_speech) with a warm,
harmonically-rich ambient underscore and soft transition chimes, applying
smooth sidechain ducking and broadcast loudness normalization.
"""
import json
import subprocess
import wave
from pathlib import Path

import imageio_ffmpeg
import numpy as np
from scipy.signal import butter, sosfilt

ROOT = Path("/home/user/-")
FFMPEG = imageio_ffmpeg.get_ffmpeg_exe()
RAW_AUDIO_DIR = ROOT / "video/audio/raw"
OUT_WAV = ROOT / "video/audio/master_mix.wav"
OUT_MANIFEST = ROOT / "video/audio_manifest.json"
OUT_SRT_EN = ROOT / "output/subtitles_en.srt"
OUT_SRT_VI = ROOT / "output/subtitles_vi.srt"

SR = 48000
LEAD_IN_SEC = 0.45
TAIL_OUT_SEC = 0.70

SCENES = [
    (
        "01_title_thesis",
        "How does a modern mobile financial super-app actually work beneath the surface? Using deterministic static reverse engineering across all 6,824 artifacts of ZaloPay version 11.17.0, we uncover a quad-hybrid architecture spanning 20 Dalvik executables, 27 native binaries, and 373 mini-programs.",
        "Một siêu ứng dụng tài chính di động hiện đại vận hành ra sao dưới bề mặt? Qua dịch ngược tĩnh xác định trên toàn bộ 6.824 hiện vật của ZaloPay 11.17.0, chúng tôi hé lộ kiến trúc tứ-lai gồm 20 tệp Dalvik, 27 thư viện native và 373 mini-program.",
    ),
    (
        "02_methodology_rea",
        "To guarantee full reproducibility without tampering or exploit synthesis, our pipeline invokes morluto's REA artifact-graph engine alongside Androguard and LIEF. Every single ZIP container, split APK, JavaScript bridge, and native shared object is hashed, parsed, and verified with zero integrity contradictions across 6,824 analyzed files.",
        "Để đảm bảo khả năng tái lập mà không xâm nhập hay tạo mã khai thác, hệ thống kết hợp công cụ đồ thị hiện vật REA của morluto cùng Androguard và LIEF. Mọi tệp ZIP, APK phân tách, cầu nối JavaScript và thư viện native đều được băm SHA-256 và kiểm chứng với 0 mâu thuẫn toàn vẹn.",
    ),
    (
        "03_container_signing",
        "Decomposing the XAPK bundle reveals five split APKs totaling 186.28 megabytes uncompressed, verified by APK Signature Schemes v2, v3, and Source Stamp v1. Bytecode and native libraries consume two-thirds of total storage, while SHA-256 deduplication exposes 299 identical file groups wasting 937 kilobytes across redundant assets and fonts.",
        "Giải phẫu gói XAPK cho thấy 5 APK phân tách tổng dung lượng 186,28 megabyte chưa nén, xác thực bằng chữ ký APK v2, v3 và Source Stamp v1. Mã byte và thư viện native chiếm hai phần ba dung lượng, trong khi băm SHA-256 phát hiện 299 nhóm tệp trùng lặp gây lãng phí 937 kilobyte.",
    ),
    (
        "04_manifest_sandbox",
        "Targeting Android 16 API level 36, the decoded manifest declares 267 components and 49 permissions, including screen-capture detection, biometric authentication, and NFC. Notably, ZaloPay isolates execution across seven custom processes: five sandboxed mini-program workers and two dedicated security isolates, though legacy cleartext compatibility remains enabled in network security configuration.",
        "Nhắm mục tiêu Android 16 API 36, tệp Manifest khai báo 267 thành phần và 49 quyền hệ thống bao gồm phát hiện chụp màn hình, sinh trắc học và NFC. ZaloPay cô lập thực thi qua 7 tiến trình riêng biệt, dù cấu hình mạng vẫn duy trì tương thích cleartext kế thừa.",
    ),
    (
        "05_dex_ecosystem",
        "Inside the Dalvik layer, 20 DEX files pack 63,827 classes and 378,040 method references, including a dynamically loaded advertising DEX hidden inside assets. Three shards hit the 60,000-method splitter ceiling, while 69.6 percent of all classes undergo R8 short-name obfuscation alongside major ecosystems from Google, BouncyCastle, Ant Group, and Jetpack Compose.",
        "Ở tầng Dalvik, 20 tệp DEX chứa 63.827 lớp và 378.040 phương thức, gồm cả một tệp DEX quảng cáo nạp động trong assets. Ba phân mảnh chạm trần 60.000 phương thức, và 69,6% lớp được làm rối tên ngắn R8 song hành cùng các hệ sinh thái Google, BouncyCastle, Ant Group và Jetpack Compose.",
    ),
    (
        "06_native_hardening",
        "Auditing all 27 ARMv7 native libraries exposes six distinct Android NDK Clang generations co-existing simultaneously, from Clang 7.0.2 up to Clang 21.0.0. While PIE and NX reach 100 percent coverage, Full RELRO drops to 85.2 percent due to four unhardened Crashlytics binaries, and FORTIFY_SOURCE covers only 55.6 percent of shared objects.",
        "Kiểm toán toàn bộ 27 thư viện ARMv7 phát lộ 6 thế hệ trình biên dịch NDK Clang cùng tồn tại, từ Clang 7.0.2 đến Clang 21.0.0. PIE và NX đạt phủ sóng 100%, nhưng Full RELRO giảm xuống 85,2% do bốn tệp Crashlytics và FORTIFY_SOURCE chỉ đạt 55,6%.",
    ),
    (
        "07_hybrid_griver",
        "Beyond native Android, ZaloPay embeds dual JavaScript runtimes. A 3.6-megabyte React Native Metro bundle ships 2,346 modules for transaction history and banking workflows, while Ant Group's Griver engine orchestrates 373 mini-programs through pre-bundled appx-ng archives and multi-worker JavaScript bridges verified by REA static AST analysis.",
        "Ngoài Android native, ZaloPay nhúng song song hai môi trường JavaScript: gói React Native Metro 3,6 MB với 2.346 mô-đun cho lịch sử giao dịch và ngân hàng, cùng nhân Griver của Ant Group điều phối 373 mini-program qua appx-ng và cầu nối worker được REA kiểm chứng AST.",
    ),
    (
        "08_edge_ai_ekyc",
        "Equally striking is the 45-megabyte on-device artificial intelligence footprint. Fifty-one model artifacts—spanning Google ML Kit OCR, Tencent NCNN, OpenCV facial landmarks, and custom TensorFlow Lite detectors—execute barcode scanning, national ID optical recognition, and blink-based liveness verification completely offline without cloud round-trips.",
        "Đáng chú ý không kém là hệ thống AI trên thiết bị nặng tới 45 megabyte: 51 tệp mô hình từ Google ML Kit OCR, Tencent NCNN, OpenCV đến TensorFlow Lite thực hiện quét mã QR, đọc OCR căn cước công dân và kiểm tra thực thể sống hoàn toàn ngoại tuyến.",
    ),
    (
        "09_conclusions_citation",
        "These empirical measurements demonstrate that modern FinTech super-apps balance feature velocity against security through layered isolation—yet third-party native dependencies remain the weakest link in binary hardening. By releasing our complete deterministic dataset, TypeScript visualization engine, and REA provenance graphs, this work establishes a reproducible benchmark for mobile FinTech architecture research.",
        "Các phép đo thực nghiệm cho thấy siêu ứng dụng FinTech cân bằng tốc độ phát triển và bảo mật qua cô lập đa tầng, nhưng thư viện bên thứ ba vẫn là mắt xích yếu nhất. Bộ dữ liệu xác định, công cụ dựng hình TypeScript và đồ thị REA mở đường cho các nghiên cứu trích dẫn tiếp theo.",
    ),
]


def decode_to_mono_f32(path: Path) -> np.ndarray:
    tmp_wav = path.with_suffix(".decoded.wav")
    subprocess.run(
        [FFMPEG, "-y", "-i", str(path), "-ar", str(SR), "-ac", "1", "-f", "wav", str(tmp_wav)],
        check=True,
        stdout=subprocess.DEVNULL,
        stderr=subprocess.DEVNULL,
    )
    with wave.open(str(tmp_wav), "rb") as wf:
        raw = wf.readframes(wf.getnframes())
    tmp_wav.unlink(missing_ok=True)
    return np.frombuffer(raw, dtype=np.int16).astype(np.float32) / 32768.0


def synthesize_ambient_bed(total_samples: int, transition_samples: list[int]) -> np.ndarray:
    """Warm stereo D-Dorian documentary synth pad + subtle crystalline chapter chimes."""
    t = np.arange(total_samples, dtype=np.float64) / SR
    # D Dorian / ambient chords evolving slowly every 16 seconds: Dm9 -> G13 -> Bbmaj7 -> Am7
    chords = [
        [146.83, 220.00, 261.63, 329.63],  # Dm9
        [196.00, 246.94, 293.66, 329.63],  # G6
        [116.54, 174.61, 220.00, 293.66],  # Bbmaj7
        [110.00, 164.81, 261.63, 329.63],  # Am7
    ]
    bar_sec = 12.0
    left = np.zeros(total_samples, dtype=np.float64)
    right = np.zeros(total_samples, dtype=np.float64)

    # Smooth crossfaded chord pad
    for idx, freqs in enumerate(chords):
        # Repeat chord pattern every 4*bar_sec
        period = len(chords) * bar_sec
        phase_in_cycle = np.mod(t, period)
        center = (idx + 0.5) * bar_sec
        dist = np.abs(phase_in_cycle - center)
        dist = np.minimum(dist, period - dist)
        weight = np.clip(1.0 - dist / (bar_sec * 0.85), 0.0, 1.0)
        weight = weight * weight * (3.0 - 2.0 * weight)

        pad_l = np.zeros(total_samples, dtype=np.float64)
        pad_r = np.zeros(total_samples, dtype=np.float64)
        for fi, f in enumerate(freqs):
            det = 1.0 + (0.0022 if fi % 2 == 0 else -0.0022)
            s1 = np.sin(2 * np.pi * f * t) + 0.25 * np.sin(2 * np.pi * f * 2.0 * t)
            s2 = np.sin(2 * np.pi * (f * det) * t + 0.7) + 0.20 * np.sin(2 * np.pi * (f * det * 2.0) * t)
            pan = 0.35 + 0.30 * (fi / max(1, len(freqs) - 1))
            pad_l += s1 * (1.0 - pan) + s2 * 0.3
            pad_r += s2 * pan + s1 * 0.3
        left += pad_l * weight * 0.14
        right += pad_r * weight * 0.14

    # Subtle pulsing sub-bass shimmer
    lfo = 0.5 + 0.5 * np.sin(2 * np.pi * 0.25 * t)
    sub = np.sin(2 * np.pi * 73.42 * t) * (0.08 + 0.05 * lfo)
    left += sub
    right += sub

    # Lowpass filter for warm velvet bed (cutoff ~1250 Hz)
    sos = butter(4, 1250.0 / (SR * 0.5), btype="low", output="sos")
    left = sosfilt(sos, left)
    right = sosfilt(sos, right)

    # Gentle soft-bell chapter transition chimes
    for pos in transition_samples:
        if pos == 0:
            continue
        dur = int(1.8 * SR)
        end = min(total_samples, pos + dur)
        n = end - pos
        if n <= 0:
            continue
        dt = np.arange(n, dtype=np.float64) / SR
        env = np.exp(-dt * 2.8) * (1.0 - np.exp(-dt * 90.0))
        chime = (np.sin(2 * np.pi * 587.33 * dt) * 0.6 + np.sin(2 * np.pi * 880.0 * dt) * 0.4) * env * 0.16
        left[pos:end] += chime * 0.85
        right[pos:end] += chime * 1.05

    # Fade in & out
    fi = min(int(1.5 * SR), total_samples)
    fo = min(int(2.5 * SR), total_samples)
    left[:fi] *= np.linspace(0.0, 1.0, fi)
    right[:fi] *= np.linspace(0.0, 1.0, fi)
    left[-fo:] *= np.linspace(1.0, 0.0, fo)
    right[-fo:] *= np.linspace(1.0, 0.0, fo)

    return np.stack([left, right], axis=1).astype(np.float32)


def fmt_srt_time(sec: float) -> str:
    ms = int(round(sec * 1000))
    h, rem = divmod(ms, 3600_000)
    m, rem = divmod(rem, 60_000)
    s, ms = divmod(rem, 1000)
    return f"{h:02d}:{m:02d}:{s:02d},{ms:03d}"


def main():
    voice_segments = []
    manifest = []
    transition_samples = []
    cursor_sec = 0.0
    srt_en_blocks = []
    srt_vi_blocks = []

    for idx, (sid, en_txt, vi_txt) in enumerate(SCENES, start=1):
        clip_path = None
        for ext in (".mp3", ".wav", ".ogg", ".flac", ".m4a"):
            cand = RAW_AUDIO_DIR / f"{sid}{ext}"
            if cand.exists():
                clip_path = cand
                break
        if clip_path is None:
            raise FileNotFoundError(f"Missing synthesized voice clip for {sid} in {RAW_AUDIO_DIR}")

        mono = decode_to_mono_f32(clip_path)
        # Normalize single speech clip cleanly
        peak = float(np.max(np.abs(mono))) or 1.0
        mono = mono / peak * 0.85

        lead = np.zeros(int(LEAD_IN_SEC * SR), dtype=np.float32)
        tail = np.zeros(int(TAIL_OUT_SEC * SR), dtype=np.float32)
        padded = np.concatenate([lead, mono, tail])
        # Snap duration cleanly to integer frame boundary at 30fps
        frames = int(round((len(padded) / SR) * 30.0))
        exact_samples = int(round((frames / 30.0) * SR))
        if len(padded) < exact_samples:
            padded = np.pad(padded, (0, exact_samples - len(padded)))
        else:
            padded = padded[:exact_samples]

        dur_sec = frames / 30.0
        transition_samples.append(sum(len(seg) for seg in voice_segments))
        voice_segments.append(padded)
        manifest.append({"id": sid, "durationSec": dur_sec, "frames": frames})

        start_s = cursor_sec + LEAD_IN_SEC
        end_s = cursor_sec + dur_sec - 0.15
        srt_en_blocks.append(f"{idx}\n{fmt_srt_time(start_s)} --> {fmt_srt_time(end_s)}\n{en_txt}\n")
        srt_vi_blocks.append(f"{idx}\n{fmt_srt_time(start_s)} --> {fmt_srt_time(end_s)}\n{vi_txt}\n")
        cursor_sec += dur_sec

    voice_full = np.concatenate(voice_segments)
    total_samples = len(voice_full)
    bed = synthesize_ambient_bed(total_samples, transition_samples)

    # Smooth sidechain envelope ducking so bed lowers gently during speech
    speech_abs = np.abs(voice_full)
    win = int(0.35 * SR)
    kernel = np.ones(win, dtype=np.float32) / win
    speech_env = np.convolve(speech_abs, kernel, mode="same")
    speech_active = np.clip(speech_env * 14.0, 0.0, 1.0)
    bed_gain = (0.34 - 0.21 * speech_active)[:, None]  # louder in gaps, -22dB under voice

    stereo_voice = np.stack([voice_full, voice_full], axis=1)
    mixed = stereo_voice * 0.90 + bed * bed_gain
    # Soft-clip / limiter safeguard
    max_pk = float(np.max(np.abs(mixed)))
    if max_pk > 0.96:
        mixed = mixed / max_pk * 0.96

    OUT_WAV.parent.mkdir(parents=True, exist_ok=True)
    pcm16 = (np.clip(mixed, -1.0, 1.0) * 32767.0).astype(np.int16)
    with wave.open(str(OUT_WAV), "wb") as wf:
        wf.setnchannels(2)
        wf.setsampwidth(2)
        wf.setframerate(SR)
        wf.writeframes(pcm16.tobytes())

    OUT_MANIFEST.write_text(json.dumps(manifest, indent=2))
    OUT_SRT_EN.parent.mkdir(parents=True, exist_ok=True)
    OUT_SRT_EN.write_text("\n".join(srt_en_blocks), encoding="utf-8")
    OUT_SRT_VI.write_text("\n".join(srt_vi_blocks), encoding="utf-8")
    print(f"Master audio mix written to {OUT_WAV} ({total_samples/SR:.2f}s)")
    print(f"Audio manifest written to {OUT_MANIFEST} + SRT subtitles")


if __name__ == "__main__":
    main()

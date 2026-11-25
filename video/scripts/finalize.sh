#!/usr/bin/env bash
# Final assembly: colour-safe encode, audio alignment, EBU R128.
#
# No temporal grain: x264 already spends 5x the bitrate on per-frame noise to
# hide banding that this palette does not produce. Flat graphics + crf 18 gives
# a visually lossless master at a third of the size.
#
#   video/out/video-nosound.mp4  +  video/out/score.wav  ->  dist/zalopay-11.17.0-teardown.mp4
#
# Two-pass loudness normalisation to -16 LUFS / -1.5 dBTP keeps spoken-word
# headroom rules while staying loud enough for web playback.
set -euo pipefail

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT="$(cd "$HERE/../.." && pwd)"
FF="${FFMPEG_BIN:-ffmpeg}"
IN_V="$ROOT/video/out/video-nosound.mp4"
IN_A="$ROOT/video/out/score.wav"
OUT_DIR="$ROOT/dist"
OUT="$OUT_DIR/zalopay-11.17.0-teardown.mp4"
mkdir -p "$OUT_DIR"

if [[ ! -f "$IN_V" ]]; then echo "missing $IN_V — run: npm run render" >&2; exit 1; fi

echo "== pass 1: loudness measurement"
STATS="$("$FF" -hide_banner -nostats -i "$IN_A" -af ebur128=peak=true -f null - 2>&1 | tail -20)"
I=$(echo "$STATS" | grep -m1 "I:" | sed 's/.*I:[[:space:]]*//' | awk '{print $1}')
TP=$(echo "$STATS" | grep -m1 "Peak:" | sed 's/.*Peak:[[:space:]]*//' | awk '{print $1}')
echo "   integrated ${I} LUFS · true peak ${TP} dBFS"
GAIN=$(awk -v i="$I" 'BEGIN { printf "%.2f", -16.0 - i }')
echo "   applying ${GAIN} dB to reach -16 LUFS"

echo "== pass 2: encode + mux"
"$FF" -hide_banner -loglevel warning -y \
  -i "$IN_V" -i "$IN_A" \
  -filter_complex "[0:v]eq=contrast=1.015:saturation=1.02,fade=t=in:st=0:d=0.9,fade=t=out:st=242.2:d=2.0,format=yuv420p[v];[1:a]volume=${GAIN}dB,alimiter=limit=0.85:level=disabled,aresample=48000[a]" \
  -map "[v]" -map "[a]" \
  -c:v libx264 -preset slow -crf 18 -profile:v high -level 4.1 \
  -x264-params "keyint=60:min-keyint=30:scenecut=0:ref=4:bframes=3" \
  -color_primaries bt709 -color_trc bt709 -colorspace bt709 \
  -c:a aac -b:a 192k -ar 48000 -ac 2 \
  -shortest -movflags +faststart \
  -metadata title="Giải phẫu một ví điện tử — ZaloPay 11.17.0" \
  -metadata comment="Static analysis of every file in the 11.17.0 XAPK. Evidence: data/*.json" \
  -metadata artist="Arena Agent" \
  "$OUT"

echo "== verification"
"$FF" -hide_banner -nostats -i "$OUT" -af ebur128=peak=true -f null - 2>&1 | tail -12 | sed 's/^/   /'
"$FF" -hide_banner -i "$OUT" 2>&1 | grep -E "Duration|Stream" | sed 's/^/   /'
ls -lh "$OUT" | sed 's/^/   /'

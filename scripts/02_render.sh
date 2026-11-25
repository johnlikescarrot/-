#!/usr/bin/env bash
# Render the research film: score → frames → mux → MP4 + subtitles.
set -euo pipefail
cd "$(dirname "$0")/.."
npm ci --no-audit --no-fund
npx tsc -p video/tsconfig.json
node video/dist/audio.js
node video/dist/main.js
python3 - <<'PY'
import subprocess, json, pathlib
p = pathlib.Path('artifacts/zalopay-11.17.0-anatomy.mp4')
print('output:', p, p.stat().st_size, 'bytes')
PY

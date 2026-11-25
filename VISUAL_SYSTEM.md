# VISUAL_SYSTEM — "Hardened Super-App" film

A restrained, engineered aesthetic. Not a marketing video: a **technical briefing rendered as cinema**.
Every number on screen comes from `analysis/out/summary.json`. No invented figures, ever.

## Format

| Property | Value |
|---|---|
| Resolution | 1920 × 1080 |
| Frame rate | 30 fps |
| Pixel format | `yuv420p` (broad compatibility) |
| Video codec | H.264 `libx264`, `crf 17`, `preset slow`, `tune film` |
| Audio codec | AAC-LC, `320 kbps`, **48 kHz stereo** |
| Loudness | `-16 LUFS` integrated, `-1.5 dBTP` true peak (podcast/streaming sweet spot) |
| Target duration | ~7–8 minutes |

## Palette (single source of truth: `video/src/theme.ts`)

| Token | Hex | Use |
|---|---|---|
| `void` | `#06080D` | Background, full stop. Never pure black — it crushes on OLED phones |
| `ink` | `#0E131C` | Panels / cards |
| `stroke` | `#1C2735` | Hairlines, grid, chart axes |
| `white` | `#EAF0F7` | Primary text |
| `muted` | `#8FA0B5` | Secondary text, captions |
| `dim` | `#4A5A6E` | Tertiary, de-emphasised numerals |
| `accent` | `#2EE6A8` | **The signal colour.** One accent per frame maximum. Data truth |
| `accentDim` | `#128A66` | Under-fill for bars, glow falloff |
| `warn` | `#FFB020` | Risk register only |
| `danger` | `#FF5A6E` | Highest-severity risk only |
| `aplus` | `#1677FF` | Alipay+ / Ant family marker |
| `google` | `#EA4335` | Google family marker |
| `track` | `#A855F7` | Tracker family marker |

**Rule:** the accent (`#2EE6A8`) means *"this number is the finding"*. It is never decorative.

## Type

| Role | Family | Size | Weight / tracking |
|---|---|---|---|
| Display | `DejaVu Sans` | 96–132 px | Bold, `-2 %` tracking |
| Heading | `DejaVu Sans` | 48–60 px | Bold |
| Body | `DejaVu Sans` | 30–34 px | Regular, line-height 1.45 |
| Caption | `DejaVu Sans` | 22–24 px | Regular, `muted` |
| Mono / data | `DejaVu Sans Mono` | 26–34 px | Regular — **all numerals are monospaced** |

DejaVu is chosen because it carries **full Vietnamese diacritics** (verified: `ữ ộ ế ằ đ ₫`) — the film is bilingual and must never show tofu boxes.

Numerals in tables and counters are **always monospace**, right-aligned, so digits do not jitter during count-up animation.

## Motion

| Principle | Implementation |
|---|---|
| Easing | `easeOutCubic` for entrances; `easeInOutCubic` for moves. No bounce, no spring |
| Entrance | 12-frame fade + 14 px rise. Nothing slides in from off-screen |
| Count-up | Numbers interpolate over 24–36 frames with `easeOutCubic`; never linear |
| Transition | 14-frame cross-dissolve. **No wipe, no zoom, no spin** |
| Hold | Any frame with a number holds ≥ 45 frames so it can be read |
| Camera | Only two motions allowed: slow 4 px/100-frame dolly, and `1.00 → 1.03` scale drift on stills |

## Composition grid

- 96 px outer margin, 12-column grid, 24 px gutters.
- Title block anchored **top-left** at `(96, 96)`; kicker above it in `accent`, 20 px, `+8 %` tracking.
- Persistent footer rule at `y = 976` with the episode slug and frame-time — a briefing artefact, never a TikTok caption.
- Chapter numbers are `01`–`09`, monospace, `dim` — they orient without demanding attention.

## Audio

Three layers, mixed in `video/src/audio/`:

1. **Narration** — single register, measured pace, ~145 wpm. Vietnamese.
2. **Score** — a slow two-chord pad (A minor 9 → F major 9), no percussion, `-26 LUFS`, ducked `-9 dB` under voice.
3. **SFX** — a single low "data tick" on chapter change (30 ms, 60 Hz, `-30 LUFS`). Used at most once per chapter.

Silence is a tool: chapters 08 (risk register) **drop the score entirely** and run on voice alone.

## Accessibility

- Text contrast ≥ 7:1 against `#06080D` for all body copy.
- Colour never carries meaning alone: family markers (Alipay+/Google/tracker) are paired with a 6 px shape glyph.
- No flashing above 3 Hz anywhere.
- Every chart has a printed axis label or a printed value; no chart requires colour discrimination.

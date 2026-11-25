/**
 * Visual language of the film.
 *
 * Reference: a cold instrument panel — near-black graphite, a single signal
 * green for "verified", amber for "attention", red for "risk". Type is set in
 * Space Grotesk (display), Inter (body) and JetBrains Mono (evidence).
 */
export const W = 1920;
export const H = 1080;
export const FPS = 30;
export const C = {
    ink: "#04060a",
    ink2: "#080c14",
    panel: "#101a29",
    panel2: "#172335",
    line: "#243244",
    line2: "#3a4f6a",
    white: "#eef3f8",
    dim: "#a9b8ca",
    dim2: "#72829a",
    mute: "#3c4757",
    green: "#00e5a0",
    greenDim: "#0b6b52",
    cyan: "#4dd0ff",
    cyanDim: "#1d5a75",
    amber: "#ffb545",
    amberDim: "#7a5520",
    red: "#ff5f6d",
    redDim: "#7d2b33",
    violet: "#a78bfa",
    magenta: "#ff7ab6",
};
export const F = {
    display: "SpaceGrotesk",
    displayBold: "SpaceGroteskBold",
    body: "Inter",
    bodyMed: "InterMedium",
    bodySemi: "InterSemiBold",
    bodyBold: "InterBold",
    mono: "JBMono",
    monoMed: "JBMonoMedium",
    monoBold: "JBMonoBold",
};
/** 12-column grid with generous cinematic margins. */
export const G = {
    margin: 120,
    col(n, total = 12) {
        const w = (W - 2 * this.margin) / total;
        return this.margin + n * w;
    },
    span(cols, total = 12) {
        return ((W - 2 * this.margin) / total) * cols;
    },
    baseline: 12,
};

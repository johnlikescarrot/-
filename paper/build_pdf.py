#!/usr/bin/env python3
"""Typeset paper/main.md into paper/zalopay-teardown.pdf.

A small, deterministic Markdown → PDF renderer built on fpdf2 and the DejaVu
family (full Unicode, including Vietnamese). Supports the subset of Markdown
used by the paper: headings, paragraphs, bullet/numbered lists, tables, fenced
code, horizontal rules, bold/italic/code spans.

Usage:  python build_pdf.py [main.md] [out.pdf]
"""
from __future__ import annotations

import re
import sys
from pathlib import Path

from fpdf import FPDF

FONT_DIR = Path("/usr/share/fonts/truetype/dejavu")
BODY, HEAD, MONO = "DejaVuSerif", "DejaVuSans", "DejaVuMono"

INK = (17, 24, 39)
MUTED = (90, 100, 120)
ACCENT = (37, 99, 235)
RULE = (203, 213, 225)
CODE_BG = (241, 245, 249)
HEAD_BG = (30, 41, 59)


def clean_inline(text: str) -> str:
    """Keep **bold** for fpdf2 markdown, drop code backticks/links markers."""
    text = re.sub(r"\[([^\]]+)\]\(([^)]+)\)", r"\1", text)
    text = text.replace("`", "")
    # single-asterisk emphasis is not supported by the font set: drop it,
    # keep **bold** (protected by a sentinel during the pass).
    text = text.replace("**", "\x00")
    text = text.replace("*", "")
    text = text.replace("\x00", "**")
    return text.strip()


class Paper(FPDF):
    def __init__(self, title: str):
        super().__init__(format="A4", unit="mm")
        self.doc_title = title
        self.set_margins(21, 18, 21)
        self.set_auto_page_break(True, margin=20)
        self.add_font(BODY, "", str(FONT_DIR / "DejaVuSerif.ttf"))
        self.add_font(BODY, "B", str(FONT_DIR / "DejaVuSerif-Bold.ttf"))
        self.add_font(HEAD, "", str(FONT_DIR / "DejaVuSans.ttf"))
        self.add_font(HEAD, "B", str(FONT_DIR / "DejaVuSans-Bold.ttf"))
        self.add_font(MONO, "", str(FONT_DIR / "DejaVuSansMono.ttf"))
        self.add_font(MONO, "B", str(FONT_DIR / "DejaVuSansMono-Bold.ttf"))
        self.set_text_color(*INK)

    def header(self) -> None:
        if self.page_no() == 1:
            return
        self.set_font(HEAD, "", 7.6)
        self.set_text_color(*MUTED)
        self.set_y(9)
        self.cell(0, 4, "PayTear — ZaloPay 11.17.0 static teardown (preprint)", align="L")
        self.cell(0, 4, "artifact zalopay-11.17.0-teardown", align="R")
        self.set_draw_color(*RULE)
        self.set_line_width(0.2)
        self.line(21, 13.5, 189, 13.5)
        self.set_y(18)
        self.set_text_color(*INK)

    def footer(self) -> None:
        self.set_y(-14)
        self.set_font(HEAD, "", 8)
        self.set_text_color(*MUTED)
        self.cell(0, 4, str(self.page_no()), align="C")


def render(md_path: Path, pdf_path: Path) -> None:
    lines = md_path.read_text(encoding="utf-8").splitlines()
    title = "PayTear"
    header_used = False
    pdf = Paper(title)
    pdf.add_page()

    i = 0
    in_code = False
    code_buf: list[str] = []
    first_h1 = True

    while i < len(lines):
        raw = lines[i]
        line = raw.rstrip("\n")

        # fenced code
        if line.startswith("```"):
            if in_code:
                pdf.set_font(MONO, "", 8.0)
                pdf.set_fill_color(*CODE_BG)
                pdf.set_text_color(*INK)
                body = "\n".join(code_buf)
                pdf.multi_cell(0, 4.2, body, fill=True, border=0, padding=2.0)
                pdf.ln(2.5)
                code_buf = []
                in_code = False
            else:
                in_code = True
            i += 1
            continue
        if in_code:
            code_buf.append(line)
            i += 1
            continue

        # horizontal rule
        if re.fullmatch(r"-{3,}", line.strip()):
            pdf.ln(1)
            pdf.set_draw_color(*RULE)
            pdf.set_line_width(0.25)
            pdf.line(21, pdf.get_y(), 189, pdf.get_y())
            pdf.ln(3)
            i += 1
            continue

        # tables
        if line.strip().startswith("|") and not line.strip().startswith("|--"):
            rows = []
            while i < len(lines) and lines[i].strip().startswith("|"):
                rows.append(lines[i].strip())
                i += 1
            render_table(pdf, rows)
            continue

        # headings
        m = re.match(r"^(#{1,4})\s+(.*)$", line)
        if m:
            level = len(m.group(1))
            text = clean_inline(m.group(2))
            pdf.ln(4 if level > 1 else 2)
            if level == 1 and first_h1:
                pdf.set_font(HEAD, "B", 20)
                pdf.multi_cell(0, 8.6, text, align="L")
                first_h1 = False
            elif level == 1:
                pdf.set_font(HEAD, "B", 15)
                pdf.multi_cell(0, 7.2, text, align="L")
                pdf.ln(1)
            elif level == 2:
                pdf.set_font(HEAD, "B", 12.6)
                pdf.set_text_color(15, 23, 42)
                pdf.multi_cell(0, 6.4, text, align="L")
                pdf.ln(1.6)
            elif level == 3:
                pdf.set_font(HEAD, "B", 10.8)
                pdf.multi_cell(0, 5.8, text, align="L")
                pdf.ln(1.2)
            else:
                pdf.set_font(HEAD, "B", 10.2)
                pdf.multi_cell(0, 5.4, text, align="L")
            i += 1
            continue

        # blockquote / emphasis lines
        if line.startswith(">"):
            pdf.set_font(HEAD, "", 9.4)
            pdf.set_text_color(*MUTED)
            pdf.multi_cell(0, 5.0, clean_inline(line.lstrip("> ")), align="L")
            pdf.set_text_color(*INK)
            pdf.ln(1.5)
            i += 1
            continue

        # bullets
        m = re.match(r"^\s*[-*]\s+(.*)$", line)
        if m:
            pdf.set_font(BODY, "", 9.8)
            x = pdf.l_margin
            pdf.set_x(x)
            pdf.cell(5.0, 5.2, "–")
            pdf.set_x(x + 5.0)
            pdf.multi_cell(189 - 21 - 5.0, 5.2, clean_inline(m.group(1)), align="L", markdown=True)
            pdf.set_x(x)
            pdf.ln(0.6)
            i += 1
            continue

        m = re.match(r"^\s*(\d+)\.\s+(.*)$", line)
        if m:
            pdf.set_font(BODY, "", 9.8)
            x = pdf.l_margin
            pdf.set_x(x)
            pdf.cell(6.0, 5.2, f"{m.group(1)}.")
            pdf.set_x(x + 6.0)
            pdf.multi_cell(189 - 21 - 6.0, 5.2, clean_inline(m.group(2)), align="L", markdown=True)
            pdf.set_x(x)
            pdf.ln(0.6)
            i += 1
            continue

        if not line.strip():
            pdf.ln(1.8)
            i += 1
            continue

        # body paragraph (may be bold-only line, e.g. subtitle/metadata)
        pdf.set_font(BODY, "", 9.9)
        text = clean_inline(line)
        pdf.multi_cell(0, 5.25, text, align="L", markdown=True)
        pdf.ln(1.4)
        i += 1

    # closing colophon
    pdf.ln(6)
    pdf.set_font(HEAD, "", 8)
    pdf.set_text_color(*MUTED)
    pdf.multi_cell(
        0, 4.4,
        "Generated from paper/main.md by paper/build_pdf.py. Every quantity in this paper is "
        "reproducible from analysis/report.json with tools/paytear. Code MIT, prose CC BY 4.0.",
        align="L",
    )
    pdf.output(str(pdf_path))
    print(f"wrote {pdf_path} ({pdf_path.stat().st_size/1024:.0f} KiB, {pdf.page_no()} pages)")


def render_table(pdf: FPDF, rows: list[str]) -> None:
    def split_row(r: str) -> list[str]:
        cells = [c.strip() for c in r.strip().strip("|").split("|")]
        return cells

    header = split_row(rows[0])
    body = [split_row(r) for r in rows[2:]] if len(rows) > 2 else []
    ncols = len(header)
    if ncols == 0:
        return
    avail = 189 - 21
    # weight columns by observed content length
    weights = []
    for c in range(ncols):
        lens = [len(header[c])] + [len(r[c]) if c < len(r) else 0 for r in body]
        weights.append(max(lens) + 4)
    total = sum(weights)
    widths = [max(14.0, avail * w / total) for w in weights]
    scale = avail / sum(widths)
    widths = [w * scale for w in widths]

    def draw_row(cells: list[str], head: bool) -> None:
        pdf.set_font(HEAD if head else BODY, "B" if head else "", 7.9 if head else 8.0)
        if head:
            pdf.set_fill_color(*HEAD_BG)
            pdf.set_text_color(255, 255, 255)
        else:
            pdf.set_fill_color(250, 251, 253)
            pdf.set_text_color(*INK)
        # measure height
        line_h = 4.2
        heights = []
        for c in range(ncols):
            txt = clean_inline(cells[c]) if c < len(cells) else ""
            pdf.set_font_size(7.9 if head else 8.0)
            n = pdf.multi_cell(widths[c] - 2.4, line_h, txt, dry_run=True, output="LINES", align="L")
            heights.append(len(n) * line_h + 1.6)
        row_h = max(heights) if heights else 5.0
        if pdf.get_y() + row_h > pdf.h - 22:
            pdf.add_page()
        x0 = pdf.get_x()
        y0 = pdf.get_y()
        for c in range(ncols):
            txt = clean_inline(cells[c]) if c < len(cells) else ""
            pdf.set_xy(x0 + sum(widths[:c]), y0)
            pdf.multi_cell(widths[c], line_h, txt, border=0, fill=True, align="L")
        pdf.set_draw_color(*RULE)
        pdf.set_line_width(0.15)
        pdf.line(x0, y0 + row_h, x0 + sum(widths), y0 + row_h)
        pdf.set_xy(x0, y0 + row_h)
        pdf.set_text_color(*INK)

    draw_row(header, True)
    for r in body:
        draw_row(r, False)
    pdf.ln(2.4)


if __name__ == "__main__":
    src = Path(sys.argv[1] if len(sys.argv) > 1 else "main.md")
    out = Path(sys.argv[2] if len(sys.argv) > 2 else "zalopay-teardown.pdf")
    render(src, out)

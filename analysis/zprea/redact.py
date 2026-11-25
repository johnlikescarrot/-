"""Redaction helpers.

Findings are published in this repository. Anything that could function as a
live credential is replaced at extraction time by a non-reversible summary
(algorithm + length + truncated hash), so the report can state *that* a secret
class exists without disclosing the secret itself.
"""

from __future__ import annotations

import hashlib
import re
from typing import Dict, List

from .fingerprints import COMPILED_SECRET_PATTERNS

MASK = "REDACTED"


def fingerprint(value: str) -> str:
    return hashlib.sha256(value.encode("utf-8", "surrogatepass")).hexdigest()[:12]


def redact_string(value: str) -> str:
    return f"{MASK}(len={len(value)},sha256={fingerprint(value)})"


def scan_and_redact(text: str) -> List[Dict[str, str]]:
    """Return a list of redacted secret hits found in `text`."""
    hits: List[Dict[str, str]] = []
    for label, rx in COMPILED_SECRET_PATTERNS.items():
        for m in rx.finditer(text):
            tok = m.group(0)
            hits.append(
                {
                    "class": label,
                    "value": redact_string(tok),
                    "context": redact_string(text[max(0, m.start() - 24) : m.end() + 8]),
                }
            )
            if len(hits) >= 200:
                return hits
    return hits


def looks_like_secret_key_name(name: str) -> bool:
    return bool(
        re.search(r"(?i)(api[_-]?key|apikey|secret|token|password|passwd|credential|private[_-]?key|access[_-]?key)", name)
    )


def redact_dict_values(d: Dict[str, str]) -> Dict[str, str]:
    """Redact the *values* of any key whose name looks credential-like."""
    out: Dict[str, str] = {}
    for k, v in d.items():
        out[k] = redact_string(str(v)) if looks_like_secret_key_name(k) else str(v)
    return out

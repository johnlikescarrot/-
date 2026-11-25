"""Shared helpers for the rea_lite offline artifact readers.

Every emitted record carries an `evidence` object: the exact container, member
path, byte offsets and digest that justify the claim. This mirrors REA's
"conclusion + evidence + limitations" evidence model.
"""
from __future__ import annotations
import hashlib, json, math, os, zipfile
from collections import Counter

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', '..'))
CORPUS = os.path.join(ROOT, 'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip')
WORK = os.path.join(ROOT, 'work')
XAPK = os.path.join(WORK, 'xapk')
ANALYSIS = os.path.join(ROOT, 'analysis')
SAMPLE = 1 << 16


def sha256(b: bytes) -> str:
    return hashlib.sha256(b).hexdigest()


def entropy(b: bytes) -> float:
    b = b[:SAMPLE]
    if not b:
        return 0.0
    c = Counter(b)
    n = len(b)
    return round(-sum((v / n) * math.log2(v / n) for v in c.values()), 4)


def dump(name: str, obj) -> str:
    os.makedirs(ANALYSIS, exist_ok=True)
    p = os.path.join(ANALYSIS, name)
    with open(p, 'w', encoding='utf-8') as f:
        json.dump(obj, f, ensure_ascii=False, indent=2, sort_keys=True)
    print(f'wrote analysis/{name} ({os.path.getsize(p)} bytes)')
    return p


def load(name: str):
    with open(os.path.join(ANALYSIS, name), encoding='utf-8') as f:
        return json.load(f)


def member_bytes(container: str, member: str) -> bytes:
    with zipfile.ZipFile(container) as z:
        return z.read(member)


def apk_paths():
    import glob
    return sorted(glob.glob(os.path.join(XAPK, '*.apk')))


def ensure_extracted():
    """Idempotently unpack the outer ZIP -> XAPK -> member files into work/."""
    os.makedirs(XAPK, exist_ok=True)
    with zipfile.ZipFile(CORPUS) as z:
        inner = z.namelist()[0]
        dst = os.path.join(WORK, 'bundle.xapk')
        if not os.path.exists(dst) or os.path.getsize(dst) != z.getinfo(inner).file_size:
            with z.open(inner) as f, open(dst, 'wb') as o:
                while (b := f.read(1 << 20)):
                    o.write(b)
    with zipfile.ZipFile(os.path.join(WORK, 'bundle.xapk')) as z:
        for i in z.infolist():
            dst = os.path.join(XAPK, os.path.basename(i.filename))
            if not os.path.exists(dst) or os.path.getsize(dst) != i.file_size:
                with z.open(i) as f, open(dst, 'wb') as o:
                    while (b := f.read(1 << 20)):
                        o.write(b)
    return inner

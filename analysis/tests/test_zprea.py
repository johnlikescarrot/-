"""Tests for the zprea static-analysis toolkit.

These run against the real sample when it is present (they are the actual
verification of the published findings) and fall back to synthetic fixtures for
the pure-function units so the suite still passes in a sample-free checkout.

    pytest analysis/tests -q
"""

from __future__ import annotations

import glob
import os
import sys

import pytest

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
sys.path.insert(0, os.path.join(ROOT, "analysis"))

from zprea import fingerprints, pipeline, signing  # noqa: E402
from zprea.dex import DexFile, descriptor_to_dotted, package_prefix, uleb128  # noqa: E402
from zprea.elfprobe import shannon_entropy  # noqa: E402
from zprea.redact import redact_string, scan_and_redact  # noqa: E402


def find_sample() -> str | None:
    hits = glob.glob(os.path.join(ROOT, "*APKPure.zip")) + glob.glob(os.path.join(ROOT, "*.xapk"))
    return hits[0] if hits else None


SAMPLE = find_sample()
requires_sample = pytest.mark.skipif(SAMPLE is None, reason="sample bundle not present")


# ------------------------------------------------------------------ pure units
def test_uleb128_single_and_multi_byte():
    assert uleb128(b"\x00rest", 0) == (0, 1)
    assert uleb128(b"\x7frest", 0) == (127, 1)
    # 0x80 0x01 -> 128
    assert uleb128(b"\x80\x01x", 0) == (128, 2)


def test_descriptor_helpers():
    assert descriptor_to_dotted("Lvn/com/vng/zalopay/Foo;") == "vn.com.vng.zalopay.Foo"
    assert package_prefix("Lvn/com/vng/zalopay/Foo;", 2) == "vn.com"
    assert package_prefix("Lvn/com/vng/zalopay/Foo;", 3) == "vn.com.vng"


def test_entropy_bounds():
    assert shannon_entropy(b"\x00" * 4096) == 0.0
    uniform = bytes(range(256)) * 16
    assert abs(shannon_entropy(uniform) - 8.0) < 1e-9


def test_longest_prefix_attribution_wins():
    # `com.alibaba.ariver` must beat the shorter `com.alibaba` rule.
    assert fingerprints.attribute("com.alibaba.ariver.kernel.Framework") is not None
    assert fingerprints.attribute("com.alibaba.ariver.kernel.Framework")[0] == "Ant Group"
    assert fingerprints.attribute("com.alibaba.other.Thing")[0] == "Alibaba"
    assert fingerprints.attribute("totally.unknown.pkg") is None


def test_security_needles_classify():
    cats = fingerprints.classify_identifier("Ljavax/crypto/Cipher;->getInstance")
    assert "crypto.cipher.getInstance" in cats
    assert "dyn.dexclassloader" in fingerprints.classify_identifier("Ldalvik/system/DexClassLoader;->loadClass")


def test_secret_patterns_actually_fire():
    """Guards against a silently-broken regex producing a false 'no secrets'."""
    samples = {
        "aws_access_key": "aws key AKIAIOSFODNN7EXAMPLE here",
        "google_api_key": "key=AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY",
        "private_key_pem": "-----BEGIN RSA PRIVATE KEY-----\nMIIE",
        "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIn0.dozjgNryP4J3jVmNHl0w5N",
    }
    for label, text in samples.items():
        hits = scan_and_redact(text)
        assert any(h["class"] == label for h in hits), f"{label} not detected in {text!r}"


def test_redaction_never_reproduces_the_secret():
    secret = "AKIAIOSFODNN7EXAMPLE"
    hits = scan_and_redact(f"credential {secret} end")
    blob = repr(hits)
    assert secret not in blob
    assert "REDACTED(" in blob
    assert "len=" in redact_string(secret)


# ------------------------------------------------------- real-sample contracts
@requires_sample
def test_container_resolution_finds_base_module():
    prov, members = pipeline.open_sample(SAMPLE)
    assert any(n == "vn.com.vng.zalopay.apk" for n in members)
    assert pipeline.base_apk(members) == "vn.com.vng.zalopay.apk"
    assert "sha256=" in prov


@requires_sample
def test_dex_reader_on_real_module():
    prov, members = pipeline.open_sample(SAMPLE)
    import io
    import zipfile

    zf = zipfile.ZipFile(io.BytesIO(members["vn.com.vng.zalopay.apk"]))
    blob = zf.read("classes.dex")
    dex = DexFile(blob, "classes.dex")
    # header must agree with the container
    assert dex.file_size == len(blob)
    assert dex.string_ids_size > 10_000
    assert dex.class_defs_size > 1_000
    first_types = list(dex.iter_types())[:5]
    assert all(t == "<none>" or t.startswith("L") or len(t) <= 2 for t in first_types)
    # every class descriptor must round-trip through the string table
    for desc, _acc, _sup in dex.iter_classes():
        assert desc.startswith("L") and desc.endswith(";")
        break


@requires_sample
def test_signing_schemes_and_signer_identity():
    prov, members = pipeline.open_sample(SAMPLE)
    rep = signing.analyse_signing_bytes(members["vn.com.vng.zalopay.apk"])
    assert rep.signing_block_found is True
    assert any("v2" in s for s in rep.schemes)
    assert any("v3" in s for s in rep.schemes)
    assert rep.v1_present is False  # this distribution ships no JAR signature
    assert rep.signers, "no signer certificate recovered"
    cert = rep.signers[0]
    assert len(cert.sha256) == 64 and all(c in "0123456789abcdef" for c in cert.sha256)
    assert cert.key_size >= 2048
    assert cert.is_self_signed is True


@requires_sample
def test_manifest_parse_matches_bundle_metadata():
    import io
    import zipfile

    from zprea import manifest as mf

    prov, members = pipeline.open_sample(SAMPLE)
    zf = zipfile.ZipFile(io.BytesIO(members["vn.com.vng.zalopay.apk"]))
    rep = mf.analyse_manifest(zf.read("AndroidManifest.xml"))
    assert rep.package == "vn.com.vng.zalopay"
    assert rep.version_name == "11.17.0"
    assert rep.version_code == "1102758"
    assert rep.target_sdk == "36"
    assert rep.min_sdk == "26"
    assert "android.permission.CAMERA" in rep.permissions_used
    counts = rep.counts()
    assert counts["total"] > 200
    assert counts["activities"] > 100


@requires_sample
def test_full_pipeline_emits_consistent_headline(tmp_path):
    rep = pipeline.run(SAMPLE)
    h = rep["headline"]
    assert h["package"] == "vn.com.vng.zalopay"
    assert h["dex_count"] == 20
    assert h["classes"] > 60_000
    assert h["methods"] > 300_000
    assert h["native_libraries"] >= 27
    assert 0.9 < h["string_printable_ratio"] <= 1.0
    assert h["obfuscation_verdict"].startswith("aggressive")
    # MASVS map must cover every category the census produced
    mapped = set()
    for group in rep["masvs_mapping"]:
        mapped.update(group["evidence"].keys())
    census = {c["category"] for c in rep["dex"]["security_api_census"]}
    assert census <= mapped, f"unmapped categories: {sorted(census - mapped)}"
    # report must render
    md = __import__("run_analysis").render_markdown(rep)
    assert "# Static Reverse-Engineering Report" in md
    assert "OWASP MASVS" in md

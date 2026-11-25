import gzip
import hashlib
import io
import json
import tarfile
import tempfile
import unittest
import warnings
import zipfile
from pathlib import Path

from scripts.inventory import inventory, write_outputs


def zip_bytes(entries):
    b = io.BytesIO()
    with warnings.catch_warnings():
        warnings.simplefilter("ignore", UserWarning)
        with zipfile.ZipFile(b, "w", zipfile.ZIP_DEFLATED) as z:
            for name, content in entries:
                z.writestr(name, content)
    return b.getvalue()


def tar_bytes():
    b = io.BytesIO()
    with tarfile.open(fileobj=b, mode="w", format=tarfile.USTAR_FORMAT) as tar:
        info = tarfile.TarInfo("nested.txt")
        info.size = 5
        tar.addfile(info, io.BytesIO(b"hello"))
        link = tarfile.TarInfo("not-followed")
        link.type = tarfile.SYMTYPE
        link.linkname = "../../outside"
        tar.addfile(link)
    return b.getvalue()


class InventoryTest(unittest.TestCase):
    def test_nested_magic_gzip_tar_and_unsafe_paths_are_not_extracted(self):
        inner = zip_bytes([("actual.tar", tar_bytes()), ("data.bin", gzip.compress(b"clear data"))])
        with tempfile.TemporaryDirectory() as d:
            p = Path(d) / "root.zip"
            p.write_bytes(zip_bytes([("disguised.amr", inner), ("../../escape.txt", b"safe")]))
            result = inventory(p)
            self.assertEqual(result["summary"]["coverage"], "complete")
            self.assertEqual(result["summary"]["file_occurrences"], 5)
            self.assertEqual(result["summary"]["file_occurrences_hashed"], 5)
            self.assertEqual(result["summary"]["special_entries"], 1)
            self.assertEqual([a["format"] for a in result["summary"]["archives"]], ["zip", "zip", "tar"])
            rows = result["rows"]
            leaf = next(r for r in rows if r["path"].endswith("!/nested.txt"))
            self.assertEqual(leaf["sha256"], hashlib.sha256(b"hello").hexdigest())
            gz = next(r for r in rows if r["path"].endswith("!/data.bin"))
            self.assertEqual(gz["gzip_expanded_sha256"], hashlib.sha256(b"clear data").hexdigest())
            self.assertFalse((Path(d).parent / "escape.txt").exists())
            write_outputs(result, Path(d) / "out1")
            write_outputs(result, Path(d) / "out2")
            self.assertEqual((Path(d) / "out1/inventory.jsonl.gz").read_bytes(),
                             (Path(d) / "out2/inventory.jsonl.gz").read_bytes())
            with gzip.open(Path(d) / "out1/inventory.jsonl.gz", "rt") as stream:
                self.assertEqual(len([json.loads(line) for line in stream]), len(rows))

    def test_limits_produce_partial_coverage(self):
        with tempfile.TemporaryDirectory() as d:
            p = Path(d) / "root.zip"
            p.write_bytes(zip_bytes([("nested.zip", zip_bytes([("file", b"x")]))]))
            result = inventory(p, max_depth=0)
            self.assertEqual(result["summary"]["coverage"], "partial")
            self.assertIn("depth limit", result["summary"]["errors"][0]["error"])

    def test_duplicate_entries_have_distinct_ordinals_and_hashes(self):
        with tempfile.TemporaryDirectory() as d:
            p = Path(d) / "root.zip"
            p.write_bytes(zip_bytes([("same", b"first"), ("same", b"second")]))
            result = inventory(p)
            a, b = result["rows"]
            self.assertTrue(a["duplicate_name_in_container"] and b["duplicate_name_in_container"])
            self.assertNotEqual(a["chain"], b["chain"])
            self.assertNotEqual(a["sha256"], b["sha256"])

    def test_crc_failure_is_not_silently_counted_as_hashed(self):
        with tempfile.TemporaryDirectory() as d:
            p = Path(d) / "root.zip"
            buf = io.BytesIO()
            with zipfile.ZipFile(buf, "w", zipfile.ZIP_STORED) as z:
                z.writestr("file", b"an unmistakable test payload")
            p.write_bytes(buf.getvalue().replace(b"unmistakable", b"UNMISTAKABLE", 1))
            result = inventory(p)
            self.assertEqual(result["summary"]["coverage"], "partial")
            self.assertEqual(result["summary"]["file_occurrences_hashed"], 0)


if __name__ == "__main__":
    unittest.main()

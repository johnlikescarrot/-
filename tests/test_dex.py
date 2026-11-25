import hashlib
import struct
import unittest
import zlib

from scripts.analyze_android import dex_facts


def fixture():
    # Small synthetic DEX-like table fixture: one WebView class, one method ID.
    b = bytearray(164)
    b[:8] = b"dex\n035\0"
    values = [b"Landroid/webkit/WebView;", b"loadUrl"]
    offsets = []
    for s in values:
        offsets.append(len(b))
        b.extend(bytes([len(s)]) + s + b"\0")
    for off, value in [(0x20, len(b)), (0x24, 112), (0x28, 0x12345678),
                       (0x38, 2), (0x3C, 112), (0x40, 1), (0x44, 120),
                       (0x58, 1), (0x5C, 124), (0x60, 1), (0x64, 132),
                       (112, offsets[0]), (116, offsets[1]), (120, 0), (128, 1), (132, 0)]:
        struct.pack_into("<I", b, off, value)
    # class_idx/proto_idx of method_id are already zero; name_idx at 128 is 1.
    b[12:32] = hashlib.sha1(b[32:]).digest()
    struct.pack_into("<I", b, 8, zlib.adler32(b[12:]) & 0xFFFFFFFF)
    return bytes(b)


class DexTest(unittest.TestCase):
    def test_header_integrity_and_method_id(self):
        d = dex_facts("classes.dex", fixture())
        self.assertEqual(d["class_definitions"], 1)
        self.assertEqual(d["method_ids"], 1)
        self.assertEqual(d["selected_method_id_references"], {"Landroid/webkit/WebView;->loadUrl": 1})
        self.assertTrue(d["declared_sha1_matches"] and d["declared_adler32_matches"])

    def test_truncated_table_is_rejected(self):
        b = bytearray(fixture())
        struct.pack_into("<I", b, 0x58, 999999)
        with self.assertRaisesRegex(ValueError, "out of bounds"):
            dex_facts("bad.dex", bytes(b))


if __name__ == "__main__":
    unittest.main()

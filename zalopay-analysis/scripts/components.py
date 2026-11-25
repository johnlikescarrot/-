#!/usr/bin/env python3
"""Enumerate all activities, services, receivers, providers + intent-filters from AndroidManifest."""
import xml.etree.ElementTree as ET
import sys

NS = "{http://schemas.android.com/apk/res/android}"

def main(manifest_path, out_path):
    tree = ET.parse(manifest_path)
    root = tree.getroot()
    out = open(out_path, "w", encoding="utf-8")

    def dump_attrs(elem):
        for k in sorted(elem.attrib):
            v = elem.attrib[k]
            if k.startswith(NS):
                k = k[len(NS):]
            if v.startswith("0x7f"):  # skip resolved resource refs for brevity? keep them
                pass
            out.write(f"        android:{k}={v!r}\n")

    sections = [("activity", "activities"), ("service", "services"), ("receiver", "receivers"), ("provider", "providers")]
    for tag, label in sections:
        n = len(root.findall(".//" + tag))
        out.write(f"\n{'='*20} {label} ({n}) {'='*20}\n")
        for i, elem in enumerate(root.findall(".//" + tag), 1):
            name = elem.attrib.get(NS + "name", "")
            out.write(f"[{i}] {tag}: {name}\n")
            # find intent-filters as children (immediate) of this component
            for child in elem:
                if child.tag == "intent-filter":
                    acts = [a.attrib.get(NS + "name", "") for a in child.findall("action")]
                    cats = [a.attrib.get(NS + "name", "") for a in child.findall("category")]
                    schemes = [d.attrib.get(NS + "scheme", "") for d in child.findall("data")]
                    hosts = [d.attrib.get(NS + "host", "") for d in child.findall("data")]
                    mimes = [d.attrib.get(NS + "mimeType", "") for d in child.findall("data")]
                    out.write(f"    intent-filter: actions={acts} categories={cats}\n")
                    if schemes or hosts or mimes:
                        out.write(f"        schemes={schemes} hosts={hosts} mimes={mimes}\n")
                    out.write(f"        autoVerify={child.attrib.get(NS + 'autoVerify')}\n")
    out.close()
    print(f"Wrote {out_path}")

if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2])

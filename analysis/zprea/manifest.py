"""AndroidManifest.xml (binary AXML) extraction.

Uses `pyaxmlparser` to inflate the binary XML, then walks the resulting lxml
tree directly so that every attribute — including `android:exported`,
`android:permission` and full `<intent-filter>` graphs — is captured rather
than only the summary fields a helper API chooses to expose.
"""

from __future__ import annotations

from dataclasses import dataclass, field
from typing import Dict, List, Optional

from pyaxmlparser.axmlprinter import AXMLPrinter

ANDROID_NS = "{http://schemas.android.com/apk/res/android}"


@dataclass
class Component:
    kind: str
    name: str
    exported: Optional[bool] = None
    permission: str = ""
    has_intent_filter: bool = False
    actions: List[str] = field(default_factory=list)
    categories: List[str] = field(default_factory=list)
    schemes: List[str] = field(default_factory=list)
    hosts: List[str] = field(default_factory=list)
    mime_types: List[str] = field(default_factory=list)

    def as_dict(self) -> Dict[str, object]:
        d = dict(self.__dict__)
        return {k: v for k, v in d.items() if v not in (None, "", [], False)}


@dataclass
class ManifestReport:
    package: str = ""
    version_code: str = ""
    version_name: str = ""
    min_sdk: str = ""
    target_sdk: str = ""
    compile_sdk: str = ""
    application_label: str = ""
    application_name: str = ""
    allow_backup: Optional[bool] = None
    debuggable: Optional[bool] = None
    uses_cleartext_traffic: Optional[bool] = None
    network_security_config: str = ""
    extract_native_libs: Optional[bool] = None
    permissions_used: List[str] = field(default_factory=list)
    permissions_declared: List[str] = field(default_factory=list)
    uses_native_libraries: List[str] = field(default_factory=list)
    meta_data: Dict[str, str] = field(default_factory=dict)
    components: List[Component] = field(default_factory=list)
    queries_packages: List[str] = field(default_factory=list)

    def counts(self) -> Dict[str, int]:
        from collections import Counter

        c = Counter(comp.kind for comp in self.components)
        return {
            "activities": c["activity"] + c["activity-alias"],
            "services": c["service"],
            "receivers": c["receiver"],
            "providers": c["provider"],
            "total": len(self.components),
            "explicitly_exported": sum(1 for x in self.components if x.exported is True),
            "with_intent_filter": sum(1 for x in self.components if x.has_intent_filter),
        }

    def as_dict(self) -> Dict[str, object]:
        d = dict(self.__dict__)
        d["components"] = [c.as_dict() for c in self.components]
        d["counts"] = self.counts()
        return d


def _bool(value: Optional[str]) -> Optional[bool]:
    if value is None:
        return None
    return value.strip().lower() in ("true", "0xffffffff", "-1")


def analyse_manifest(manifest_bytes: bytes) -> ManifestReport:
    rep = ManifestReport()
    tree = AXMLPrinter(manifest_bytes).get_xml_obj()
    root = tree.getroot() if hasattr(tree, "getroot") else tree

    rep.package = root.get("package", "") or ""

    def a(el, attr):
        return el.get(ANDROID_NS + attr)

    us = root.find("uses-sdk")
    if us is not None:
        rep.min_sdk = a(us, "minSdkVersion") or ""
        rep.target_sdk = a(us, "targetSdkVersion") or ""

    app = root.find("application")
    if app is not None:
        rep.version_code = a(root, "versionCode") or ""
        rep.version_name = a(root, "versionName") or ""
        rep.application_label = a(app, "label") or ""
        rep.application_name = a(app, "name") or ""
        rep.allow_backup = _bool(a(app, "allowBackup"))
        rep.debuggable = _bool(a(app, "debuggable"))
        rep.uses_cleartext_traffic = _bool(a(app, "usesCleartextTraffic"))
        rep.extract_native_libs = _bool(a(app, "extractNativeLibs"))
        nsc = a(app, "networkSecurityConfig")
        rep.network_security_config = nsc or ""
        for md in app.findall("meta-data"):
            key = a(md, "name") or ""
            val = a(md, "value") or a(md, "resource") or ""
            if key:
                rep.meta_data[key] = str(val)

    for el in root.iter():
        tag = el.tag.split("}")[-1]
        if tag == "uses-permission" or tag == "uses-permission-sdk-23":
            name = a(el, "name")
            if name:
                rep.permissions_used.append(name)
        elif tag == "permission":
            name = a(el, "name")
            if name:
                rep.permissions_declared.append(name)
        elif tag == "uses-native-library":
            name = a(el, "name")
            if name:
                rep.uses_native_libraries.append(name)
        elif tag == "queries":
            for pk in el.iter():
                if pk.tag.split("}")[-1] == "package":
                    n = pk.get("name")
                    if n:
                        rep.queries_packages.append(n)

    for kind in ("activity", "activity-alias", "service", "receiver", "provider"):
        for el in root.iter(kind):
            comp = Component(kind=kind, name=a(el, "name") or "<unnamed>")
            comp.exported = _bool(a(el, "exported"))
            comp.permission = a(el, "permission") or ""
            for flt in el.findall("intent-filter"):
                comp.has_intent_filter = True
                for child in flt:
                    t = child.tag.split("}")[-1]
                    v = a(child, "name") or a(child, "scheme") or a(child, "host") or ""
                    if not v:
                        v = a(child, "mimeType") or ""
                    if t == "action":
                        comp.actions.append(v)
                    elif t == "category":
                        comp.categories.append(v)
                    elif t == "data":
                        s = a(child, "scheme")
                        h = a(child, "host")
                        mt = a(child, "mimeType")
                        if s:
                            comp.schemes.append(s)
                        if h:
                            comp.hosts.append(h)
                        if mt:
                            comp.mime_types.append(mt)
            for key in ("schemes", "hosts", "actions", "categories", "mime_types"):
                setattr(comp, key, sorted(set(getattr(comp, key))))
            rep.components.append(comp)

    # version attrs may live on <manifest>
    if not rep.version_code:
        rep.version_code = a(root, "versionCode") or ""
    if not rep.version_name:
        rep.version_name = a(root, "versionName") or ""
    return rep

import fs from 'node:fs';

export interface Chapter { id: string; chapter: string; title: string; source: string; narration: string }
export function loadChapters(): Chapter[] {
  return JSON.parse(fs.readFileSync('video/script.json', 'utf8')) as Chapter[];
}
export function validateFacts(): void {
  const inventory = JSON.parse(fs.readFileSync('research/inventory-summary.json', 'utf8'));
  const android = JSON.parse(fs.readFileSync('research/android-analysis.json', 'utf8'));
  const signatures = JSON.parse(fs.readFileSync('research/signatures.json', 'utf8'));
  const base = android.apks['vn.com.vng.zalopay.apk'];
  const requireFact = (condition: unknown, message: string) => { if (!condition) throw new Error(`Evidence mismatch: ${message}`); };
  requireFact(inventory.coverage === 'complete' && inventory.file_occurrences === 6832 && inventory.file_occurrences_hashed === 6832, 'inventory count/coverage');
  requireFact(inventory.archives.length === 14, 'archive count');
  requireFact(base.permissions.length === 49 && base.explicitly_exported_true.length === 30, 'manifest counts');
  requireFact(Object.values(base.component_counts as Record<string, number>).reduce((a, b) => a + b, 0) === 267, 'component count');
  requireFact(base.min_sdk === '26' && base.target_sdk === '36', 'SDK range');
  requireFact(base.network_security_config.variants[0].decoded_xml.includes('cleartextTrafficPermitted="true"'), 'network config');
  requireFact(android.dex.length === 20 && android.dex.filter((x: {path: string}) => x.path.startsWith('classes')).length === 19, 'DEX files');
  requireFact(android.dex.reduce((n: number, x: {class_definitions: number}) => n + x.class_definitions, 0) === 63827, 'class definitions');
  requireFact(android.elf.length === 27 && android.elf.every((x: {machine: string; elf_class_bits: number}) => x.machine === 'EM_ARM' && x.elf_class_bits === 32), 'ELF files');
  requireFact(android.asset_models_by_extension.length === 16, 'model files');
  requireFact(android.javascript_bundle.literal_marker_counts['__d('] === 2346, 'bundle markers');
  requireFact(signatures.apks.length === 5 && signatures.apks.every((x: {apksigner_verifies: boolean; schemes_verified: Record<string, boolean>}) => x.apksigner_verifies && x.schemes_verified.v2 && x.schemes_verified.v3), 'APK signatures');
  requireFact(new Set(signatures.apks.map((x: {signer_certificate_sha256: string}) => x.signer_certificate_sha256)).size === 1, 'signer fingerprint');
  const chapters = loadChapters();
  requireFact(chapters.length === 10 && chapters.every(c => c.narration.length > 30 && c.narration.length <= 1500), 'narration length');
  requireFact(new Set(chapters.map(c => c.id)).size === 10, 'unique chapter IDs');
}
if (process.argv[1]?.endsWith('validate.ts')) {
  validateFacts();
  console.log('Video claims match the checked research files; 10 narration segments are within the speech limit.');
}

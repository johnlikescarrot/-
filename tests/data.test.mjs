/**
 * Guards the contract between the analysis pipeline and the film:
 * every value the renderer displays must exist, be sane, and be traceable.
 */
import test from 'node:test';
import assert from 'node:assert/strict';
import { readFileSync } from 'node:fs';

const film = JSON.parse(readFileSync('research/data/film.json', 'utf8'));

test('subject identity is complete', () => {
  assert.equal(film.subject.package, 'vn.com.vng.zalopay');
  assert.equal(film.subject.version, '11.17.0');
  assert.match(film.subject.sha256_xapk, /^[0-9a-f]{64}$/);
  assert.match(film.subject.sha256_outer, /^[0-9a-f]{64}$/);
});

test('container totals are internally consistent', () => {
  const c = film.container;
  assert.equal(c.splits, 5);
  assert.ok(c.entries > 5000, 'entries');
  assert.ok(c.uncompressed > film.subject.bytes_xapk, 'uncompressed exceeds archive');
  assert.equal(c.dex_files, 20);
});

test('code layer matches DEX corpus', () => {
  const c = film.code;
  assert.equal(c.dex, 19);
  assert.ok(c.classes > 50000);
  assert.ok(c.methods > 300000);
  assert.equal(c.method_limit_dexes.length, 3);
  for (const s of c.sections) {
    assert.ok(s.method_capacity_used <= 100, `${s.dex} capacity`);
    assert.ok(s.code + s.index_bytes <= s.bytes + 64, `${s.dex} sections fit in file`);
  }
});

test('native layer is coherent', () => {
  const n = film.native;
  assert.equal(n.count, 27);
  assert.ok(n.bytes > 50 * 2 ** 20);
  assert.ok(n.stripped_pct > 90);
  assert.equal(n.randomised_names.length, 7);
  for (const [k, v] of Object.entries(n.hardening)) assert.ok(v <= n.count, `${k} <= count`);
});

test('security verdict is conservative', () => {
  const s = film.security;
  assert.equal(s.sig_verified, false, 'must not claim a verified signature');
  assert.equal(s.v2_pair_length + 4, s.hex.declared_vs_actual[1]);
  assert.match(s.cert_sha256, /^[0-9a-f]{64}$/);
  assert.equal(s.hex.head.split(' ').length, 48);
});

test('endpoints and ecosystem are plausible', () => {
  assert.ok(film.endpoints.hosts >= 150);
  assert.ok(film.endpoints.first_party_count >= 40);
  assert.ok(film.endpoints.api_paths >= 250);
  assert.ok(film.miniapps.count >= 300);
  assert.equal(film.manifest.permissions, 49);
  assert.equal(film.sdk_count, film.sdks.length + 38);
});

test('no placeholder or secret-shaped strings leak into the film data', () => {
  const raw = JSON.stringify(film);
  assert.ok(!/TODO|FIXME|PLACEHOLDER/.test(raw));
  // no long base64/hex blobs that could be keys
  assert.ok(!/[A-Za-z0-9+/]{120,}={0,2}/.test(raw), 'no embedded base64 blob');
});

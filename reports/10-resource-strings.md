# 10 — Resource strings (2,066 unique, ARSC XML dump)

`APK.get_android_resources().get_strings_resources()` returns a 208,847-byte
UTF-8 XML document (`<packages>` → 13 packages/locales → 2,462 `<string>`
entries), parsed in `evidence/resource-strings-retry.json`.

Highlights: Vietnamese UI copy throughout; `ADMOB_APP_ID`
`ca-app-pub-1874797564549247~9245585263`; Firebase
`zalo-pay.firebaseio.com`; bank hosts `ebank.bidv.com.vn`,
`binding/stccbo/sbstccbo.zalopay.vn`; keyword counts zalopay×81,
password×12, token×4. One `http://` occurrence (audit target).

Raw: `evidence/resource-strings-retry.json`.

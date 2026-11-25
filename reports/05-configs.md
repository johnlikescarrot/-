# 05 — Bundled configs deep-dive

- `assets/config/zalopay_config.json` (105,659 B, sha256 `740c82f914bd6399…`): keys(56): `general`, `zpc`, `api`, `webapp`, `tab_me`, `notification`, `tab_home`, `promotion`, `promotion_handler`, `bank_integration`, `bank`, `profile` | hosts: vngzalopay.freshdesk.com×3, social.zalopay.vn×2, zalo.me×2, ebank.bidv.com.vn×2, seal.controlcase.com×1, mnap-director.zalopay.vn×1, bit.ly×1, www.sacombank.com.vn×1
- `assets/config/miniapp_config.json` (883,684 B, sha256 `e69ba84631346485…`): keys(3): `code`, `data`, `msg` | hosts: simg.zalopay.com.vn×1275, onelink.zalopay.vn×537, social.zalopay.vn×341, scdn.zalopay.com.vn×225, mnap-director.zalopay.vn×38, sjs.zalopay.com.vn×26, socialdev.zalopay.vn×7, mc.zalopay.vn×1
- `assets/client_tracking.json` (146,391 B, sha256 `17658dcae0a7e7a0…`): keys(2): `tripDetail`, `trackingItems` | hosts: 15828440.cloud.edgevnpay.vn×5, storage.googleapis.com×1
- `assets/config/public_key.txt` (450 B, sha256 `ab63708a7b557a13…`): 
- `assets/config/signature.txt` (350 B, sha256 `fd7abb1a0d62a50a…`): 
- `assets/bundle.json` (312 B, sha256 `21c10377141df90d…`): keys(2): `external_bundle`, `internal_bundle`
- `assets/Griver/appx.json` (2,178 B, sha256 `3b7a99da47f6f10d…`): keys(1): `mini` | hosts: gw.alipayobjects.com×3, alipay-rmsdeploy-image.cn-hangzhou.alipay.aliyun-inc.com×3, cdn.marmot-cloud.com×2, oss-local-nightly-dev.marmot-cloud.com×1
- `assets/theme/bank/config_v2.json` (11,573 B, sha256 `258535cabe5cb6d8…`): keys(5): `_comment_1`, `_comment_2`, `config`, `item_groups`, `item_resources` | hosts: zalopay.vn×1, cutt.ly×1, social.zalopay.vn×1, neo.vpbank.com.vn×1, taikhoan.vpbank.com.vn×1, bit.ly×1, digibank.vietcapitalbank.com.vn×1
- `assets/theme/bank/ui_config.json` (15,430 B, sha256 `1efac77f60bba109…`): keys(2): `_comment`, `bank_styles`
- `assets/theme/bank/bank_resource.json` (11,339 B, sha256 `bf584e41fde15aaf…`): keys(5): `_comment_1`, `_comment_2`, `resource_bank`, `resource_group`, `resource_item` | hosts: zalopay.vn×2, bit.ly×2, cutt.ly×1, tiny.cc×1, digibank.vietcapitalbank.com.vn×1, dl.bidvsmartbanking.vn×1
- `assets/home_data.json` (9,417 B, sha256 `0a6b0afe13690ff2…`): keys(13): `screen_id`, `header_banner_image`, `header_banner_url`, `shortcut_bar`, `reminder`, `highlight`, `ads_slider`, `third_party`, `security_image`, `bottom_bar`, `avatar`, `balance` | hosts: simg.zalopay.com.vn×6
- `assets/taibmcf.json` (46 B, sha256 `276833c96dde3cee…`): keys(2): `vi`, `en`
- `assets/vdfmoer.json` (46 B, sha256 `276833c96dde3cee…`): keys(2): `vi`, `en`

Crypto envelope: RSA public key DER 294 bytes
(RSA-2048 pattern), signature raw 256 bytes
(RSA-2048 signature length) — consistent with signed-config verification.

Raw: `evidence/config-findings.json`, `evidence/config-crypto.json`.

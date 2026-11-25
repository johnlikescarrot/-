# Comprehensive Reverse Engineering Report

**Target:** `Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip`  
**Package:** `vn.com.vng.zalopay`  
**Version:** 11.17.0 (versionCode 1102758)  
**Analyzed:** 2026-11-25 (UTC)  
**Methodology:** Static analysis using Androguard 4.1.2, structural ZIP/APK inspection, file inventory hashing (SHA-256), manifest/certificate/component extraction, DEX string/pattern analysis.  

> This report is for security research and educational purposes. It documents observable technical characteristics of the distributed APK package.

## 1. Package Overview (XAPK)

| Field | Value |
|---|---|
| package_name | vn.com.vng.zalopay |
| name | Zalopay |
| version_name | 11.17.0 |
| version_code | 1102758 |
| min_sdk_version | 26 |
| target_sdk_version | 36 |
| total_size | 105580620 |
| split_configs | config.armeabi_v7a, config.en, config.vi, config.xhdpi |

### 1.1 XAPK Contents
| File | Role |
|---|---|
| `vn.com.vng.zalopay.apk` | base |
| `config.armeabi_v7a.apk` | config.armeabi_v7a |
| `config.en.apk` | config.en |
| `config.vi.apk` | config.vi |
| `config.xhdpi.apk` | config.xhdpi |
| `icon.png` | App icon |
| `manifest.json` | XAPK manifest metadata |

## 2. File Inventory (ALL files)

Total files inventoried across XAPK and split APKs: **6466**

| Container | File count |
|---|---:|
| `vn.com.vng.zalopay.apk` | 6162 |
| `config.xhdpi.apk` | 258 |
| `config.armeabi_v7a.apk` | 32 |
| `config.en.apk` | 6 |
| `config.vi.apk` | 6 |
| `XAPK_ROOT` | 2 |

### 2.1 File Types (by extension)
| Extension | Count |
|---|---:|
| `.xml` | 5320 |
| `.png` | 319 |
| `.webp` | 159 |
| `.version` | 141 |
| `.txt` | 136 |
| `.properties` | 79 |
| `.json` | 65 |
| `.so` | 28 |
| `.ttf` | 23 |
| `.dex` | 20 |
| `(none)` | 18 |
| `.tflite` | 14 |
| `.kotlin_module` | 11 |
| `.binarypb` | 10 |
| `.proto` | 10 |
| `.js` | 9 |
| `.html` | 9 |
| `.kotlin_builtins` | 8 |
| `.pb` | 6 |
| `.gif` | 5 |
| `.emd` | 4 |
| `.riv` | 4 |
| `.arsc` | 4 |
| `.sf` | 4 |
| `.rsa` | 4 |
| `.mf` | 4 |
| `.h` | 3 |
| `.amr` | 3 |
| `.dat` | 3 |
| `.bin` | 2 |
| `.md` | 2 |
| `.d` | 2 |
| `.m` | 2 |
| `.fb` | 2 |
| `.bincfg` | 2 |
| `.tfl` | 2 |
| `.jpg` | 2 |
| `.otf` | 2 |
| `.mp3` | 2 |
| `.svg` | 2 |
| `.processors` | 1 |
| `.g` | 1 |
| `.c` | 1 |
| `.generatedextensionregistryloader` | 1 |
| `.j` | 1 |
| `.l` | 1 |
| `.p` | 1 |
| `.provider` | 1 |
| `.processor` | 1 |
| `.a` | 1 |
| `.textproto` | 1 |
| `.param` | 1 |
| `.zip` | 1 |
| `.prof` | 1 |
| `.profm` | 1 |
| `.conv_model` | 1 |
| `.lstm_model` | 1 |
| `.pem` | 1 |
| `.public` | 1 |
| `.gz` | 1 |

### 2.2 Complete File Inventory
Full per-file inventory with SHA-256 hashes is in [`reports/file_inventory.json`](reports/file_inventory.json).

Below is the complete listing grouped by container.
<details><summary><code>vn.com.vng.zalopay.apk</code> — 6162 files</summary>

| Path | Size (bytes) | SHA-256 |
|---|---:|---|
| `AndroidManifest.xml` | 125916 | `88db211a71015bab…` |
| `DebugProbesKt.bin` | 1738 | `86fecd0235d45839…` |
| `LICENSE-junit.txt` | 11376 | `9648bb2891b98139…` |
| `META-INF/CHANGES` | 81618 | `0776f3acfa7bf009…` |
| `META-INF/NOTICE.md` | 1074 | `992ed62dd3ee17d7…` |
| `META-INF/README.md` | 2902 | `81f72a1fd8fe5d9c…` |
| `META-INF/androidx.activity_activity-compose.version` | 6 | `272bc91e0090267f…` |
| `META-INF/androidx.activity_activity-ktx.version` | 6 | `272bc91e0090267f…` |
| `META-INF/androidx.activity_activity.version` | 6 | `272bc91e0090267f…` |
| `META-INF/androidx.annotation_annotation-experimental.version` | 6 | `acb57a7135b2d7d6…` |
| `META-INF/androidx.appcompat_appcompat-resources.version` | 6 | `6430a242e9486093…` |
| `META-INF/androidx.appcompat_appcompat.version` | 6 | `6430a242e9486093…` |
| `META-INF/androidx.arch.core_core-runtime.version` | 67 | `1b7c606370188534…` |
| `META-INF/androidx.asynclayoutinflater_asynclayoutinflater.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.autofill_autofill.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.biometric_biometric.version` | 14 | `59e8a0c25fea4292…` |
| `META-INF/androidx.browser_browser.version` | 6 | `3eb1ef94b5890ca7…` |
| `META-INF/androidx.camera_camera-camera2.version` | 6 | `78b591400c56b7b6…` |
| `META-INF/androidx.camera_camera-core.version` | 6 | `78b591400c56b7b6…` |
| `META-INF/androidx.camera_camera-extensions.version` | 6 | `78b591400c56b7b6…` |
| `META-INF/androidx.camera_camera-lifecycle.version` | 6 | `78b591400c56b7b6…` |
| `META-INF/androidx.camera_camera-video.version` | 6 | `78b591400c56b7b6…` |
| `META-INF/androidx.camera_camera-view.version` | 6 | `78b591400c56b7b6…` |
| `META-INF/androidx.cardview_cardview.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.compose.animation_animation-core.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.animation_animation.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.foundation_foundation-layout.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.foundation_foundation.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.material_material-icons-core.version` | 6 | `cb1d9a30bc4ab8a3…` |
| `META-INF/androidx.compose.material_material-ripple.version` | 6 | `cb1d9a30bc4ab8a3…` |
| `META-INF/androidx.compose.material_material.version` | 6 | `cb1d9a30bc4ab8a3…` |
| `META-INF/androidx.compose.runtime_runtime-livedata.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.runtime_runtime-saveable.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.runtime_runtime.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-geometry.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-graphics.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-text.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-tooling-data.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-tooling-preview.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-tooling.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-unit.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui-util.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.compose.ui_ui.version` | 6 | `e6b7255e251fae18…` |
| `META-INF/androidx.constraintlayout_constraintlayout-compose.version` | 6 | `cc52f678848b8143…` |
| `META-INF/androidx.coordinatorlayout_coordinatorlayout.version` | 6 | `1575e1af4a95f12f…` |
| `META-INF/androidx.core_core-ktx.version` | 7 | `46081fd90571a274…` |
| `META-INF/androidx.core_core-remoteviews.version` | 6 | `1575e1af4a95f12f…` |
| `META-INF/androidx.core_core-splashscreen.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.core_core-viewtree.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.core_core.version` | 7 | `46081fd90571a274…` |
| `META-INF/androidx.cursoradapter_cursoradapter.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.customview_customview-poolingcontainer.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.customview_customview.version` | 6 | `1575e1af4a95f12f…` |
| `META-INF/androidx.databinding_baseAdapters.version` | 6 | `5a512d7f3a8e1dd0…` |
| `META-INF/androidx.databinding_databindingKtx.version` | 6 | `5a512d7f3a8e1dd0…` |
| `META-INF/androidx.databinding_library.version` | 6 | `5a512d7f3a8e1dd0…` |
| `META-INF/androidx.databinding_viewbinding.version` | 6 | `5a512d7f3a8e1dd0…` |
| `META-INF/androidx.datastore_datastore-preferences.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.datastore_datastore.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.documentfile_documentfile.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.drawerlayout_drawerlayout.version` | 6 | `cc52f678848b8143…` |
| `META-INF/androidx.dynamicanimation_dynamicanimation-ktx.version` | 14 | `bc9c67fcb0060930…` |
| `META-INF/androidx.dynamicanimation_dynamicanimation.version` | 14 | `92b8fcec4e87a397…` |
| `META-INF/androidx.emoji2_emoji2-views-helper.version` | 6 | `64d23f858ef51b0f…` |
| `META-INF/androidx.emoji2_emoji2.version` | 6 | `64d23f858ef51b0f…` |
| `META-INF/androidx.exifinterface_exifinterface.version` | 6 | `f4eaae07c72dd003…` |
| `META-INF/androidx.fragment_fragment-ktx.version` | 6 | `6dae4a52aa1ca494…` |
| `META-INF/androidx.fragment_fragment.version` | 6 | `6dae4a52aa1ca494…` |
| `META-INF/androidx.glance_glance-appwidget.version` | 6 | `cc52f678848b8143…` |
| `META-INF/androidx.glance_glance.version` | 6 | `cc52f678848b8143…` |
| `META-INF/androidx.graphics_graphics-path.version` | 6 | `44e161e4495cac2c…` |
| `META-INF/androidx.interpolator_interpolator.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.legacy_legacy-support-core-ui.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.legacy_legacy-support-core-utils.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.legacy_legacy-support-v4.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.lifecycle_lifecycle-extensions.version` | 6 | `c89b87c262e9d5ff…` |
| `META-INF/androidx.lifecycle_lifecycle-livedata-core-ktx.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-livedata-core.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-livedata-ktx.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-livedata.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-process.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-runtime-compose.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-runtime-ktx.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-runtime.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-service.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-viewmodel-compose.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-viewmodel-ktx.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-viewmodel-savedstate.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.lifecycle_lifecycle-viewmodel.version` | 6 | `f48da3d32b75f2a1…` |
| `META-INF/androidx.loader_loader.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.localbroadcastmanager_localbroadcastmanager.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.media_media.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.navigation_navigation-common-ktx.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-common.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-compose.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-dynamic-features-fragment.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-dynamic-features-runtime.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-fragment-ktx.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-fragment.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-runtime-ktx.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-runtime.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-ui-ktx.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.navigation_navigation-ui.version` | 6 | `e3fdbc5ab5230a73…` |
| `META-INF/androidx.paging_paging-compose.version` | 6 | `3822728783049fc2…` |
| `META-INF/androidx.paging_paging-runtime-ktx.version` | 6 | `3822728783049fc2…` |
| `META-INF/androidx.paging_paging-runtime.version` | 6 | `3822728783049fc2…` |
| `META-INF/androidx.palette_palette-ktx.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.palette_palette.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.print_print.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.privacysandbox.ads_ads-adservices-java.version` | 13 | `6a47710af8eced6a…` |
| `META-INF/androidx.privacysandbox.ads_ads-adservices.version` | 13 | `6a47710af8eced6a…` |
| `META-INF/androidx.profileinstaller_profileinstaller.version` | 6 | `10e2e117b0152389…` |
| `META-INF/androidx.recyclerview_recyclerview.version` | 6 | `64d23f858ef51b0f…` |
| `META-INF/androidx.room_room-ktx.version` | 6 | `4b27eec369475e04…` |
| `META-INF/androidx.room_room-runtime.version` | 6 | `4b27eec369475e04…` |
| `META-INF/androidx.savedstate_savedstate-ktx.version` | 6 | `6cf4e084b47f33c9…` |
| `META-INF/androidx.savedstate_savedstate.version` | 6 | `6cf4e084b47f33c9…` |
| `META-INF/androidx.security_security-crypto.version` | 6 | `1575e1af4a95f12f…` |
| `META-INF/androidx.slidingpanelayout_slidingpanelayout.version` | 6 | `1e5b51cde515396a…` |
| `META-INF/androidx.sqlite_sqlite-framework.version` | 6 | `8fde0552368c6487…` |
| `META-INF/androidx.sqlite_sqlite-ktx.version` | 6 | `8fde0552368c6487…` |
| `META-INF/androidx.sqlite_sqlite.version` | 6 | `8fde0552368c6487…` |
| `META-INF/androidx.startup_startup-runtime.version` | 6 | `1e5b51cde515396a…` |
| `META-INF/androidx.swiperefreshlayout_swiperefreshlayout.version` | 6 | `1575e1af4a95f12f…` |
| `META-INF/androidx.tracing_tracing-ktx.version` | 6 | `1e5b51cde515396a…` |
| `META-INF/androidx.tracing_tracing.version` | 6 | `1e5b51cde515396a…` |
| `META-INF/androidx.transition_transition.version` | 6 | `7d072b48526b0239…` |
| `META-INF/androidx.vectordrawable_vectordrawable-animated.version` | 6 | `1575e1af4a95f12f…` |
| `META-INF/androidx.vectordrawable_vectordrawable.version` | 6 | `1575e1af4a95f12f…` |
| `META-INF/androidx.versionedparcelable_versionedparcelable.version` | 6 | `cc52f678848b8143…` |
| `META-INF/androidx.viewpager2_viewpager2.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.viewpager_viewpager.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.webkit_webkit.version` | 15 | `82c7bd30088689ab…` |
| `META-INF/androidx.window_window.version` | 6 | `59854984853104df…` |
| `META-INF/androidx.work_work-runtime-ktx.version` | 6 | `9927e4392d021630…` |
| `META-INF/androidx.work_work-runtime.version` | 6 | `9927e4392d021630…` |
| `META-INF/androidx.work_work-rxjava2.version` | 6 | `9927e4392d021630…` |
| `META-INF/androidx/annotation/annotation/LICENSE.txt` | 10175 | `809fa1ed21450f59…` |
| `META-INF/androidx/collection/collection-ktx/LICENSE.txt` | 10175 | `809fa1ed21450f59…` |
| `META-INF/androidx/collection/collection/LICENSE.txt` | 10175 | `809fa1ed21450f59…` |
| `META-INF/androidx/constraintlayout/constraintlayout-core/LICENSE.txt` | 10175 | `809fa1ed21450f59…` |
| `META-INF/androidx/room/room-common/LICENSE.txt` | 10175 | `809fa1ed21450f59…` |
| `META-INF/com.google.android.material_material.version` | 6 | `78b591400c56b7b6…` |
| `META-INF/com.google.dagger_dagger-android-support.version` | 7 | `21b9d0f0927a406f…` |
| `META-INF/com.google.dagger_dagger-android.version` | 7 | `21b9d0f0927a406f…` |
| `META-INF/com.google.dagger_dagger-lint-aar.version` | 7 | `21b9d0f0927a406f…` |
| `META-INF/com.google.dagger_dagger.version` | 7 | `21b9d0f0927a406f…` |
| `META-INF/com/android/build/gradle/app-metadata.properties` | 57 | `762cdeb9ba403447…` |
| `META-INF/face_release.kotlin_module` | 81 | `96794ce870e44b7f…` |
| `META-INF/fragment-ktx_release.kotlin_module` | 70 | `f30d453d018f50e1…` |
| `META-INF/gradle/incremental.annotation.processors` | 67 | `dae60d8c3158e2ca…` |
| `META-INF/kotlin-stdlib.kotlin_module` | 282 | `26c65d0a7c2cf433…` |
| `META-INF/kotlin_release.kotlin_module` | 138 | `ed77c41d39dd329e…` |
| `META-INF/kotlinx_coroutines_android.version` | 5 | `a580a789f915dffa…` |
| `META-INF/kotlinx_coroutines_core.version` | 5 | `a580a789f915dffa…` |
| `META-INF/kotlinx_coroutines_play_services.version` | 5 | `a580a789f915dffa…` |
| `META-INF/kotlinx_coroutines_reactive.version` | 5 | `a580a789f915dffa…` |
| `META-INF/kotlinx_coroutines_rx2.version` | 5 | `a580a789f915dffa…` |
| `META-INF/okhttp-logging-interceptor.kotlin_module` | 47 | `e64515e1056d6a12…` |
| `META-INF/okhttp.kotlin_module` | 273 | `d77b3f54c678782a…` |
| `META-INF/okio.kotlin_module` | 382 | `fd2c237b71c7bd49…` |
| `META-INF/retrofit.kotlin_module` | 51 | `f8331ca3425af38b…` |
| `META-INF/rxkotlin.properties` | 668 | `f928968baaec99cb…` |
| `META-INF/services/Eh.g` | 15 | `469b65408e29923a…` |
| `META-INF/services/K6.d` | 5 | `b593a764be926fd6…` |
| `META-INF/services/K6.h` | 5 | `31964ea0b5223e11…` |
| `META-INF/services/Ug.c` | 5 | `077e3f572dbe8a82…` |
| `META-INF/services/com.google.protobuf.GeneratedExtensionRegistryLoader` | 140 | `0a1457479eb5254a…` |
| `META-INF/services/gi.H` | 5 | `29a3eb198efa4be4…` |
| `META-INF/services/hx.d` | 5 | `e0d8e806cea87ae9…` |
| `META-INF/services/hx.h` | 5 | `8f22ffc90bf453a9…` |
| `META-INF/services/hx.j` | 5 | `d4ffc27a4f6f0192…` |
| `META-INF/services/hx.l` | 5 | `bf150596c3255839…` |
| `META-INF/services/hx.m` | 5 | `64d4b1c97f45e723…` |
| `META-INF/services/ih.p` | 5 | `30e2c021757420f0…` |
| `META-INF/services/java.security.Provider` | 112 | `b50a070540ab15ba…` |
| `META-INF/services/javax.annotation.processing.Processor` | 5 | `b03a43fe9735d145…` |
| `META-INF/services/li.m` | 5 | `a91aa1c788835b04…` |
| `META-INF/services/px.a` | 5 | `c58471e7c0221113…` |
| `META-INF/trueface_release.kotlin_module` | 165 | `8978a4ae5ab79fcf…` |
| `META-INF/trueid_release.kotlin_module` | 112 | `6d33729ff0637c05…` |
| `META-INF/ui_release.kotlin_module` | 88 | `5de6faf1ff6f9d0a…` |
| `META-INF/version-control-info.textproto` | 46 | `38d377afa76b6364…` |
| `assets/FastestDet.ncnn.bin` | 2420588 | `ee8e29ed0889be18…` |
| `assets/FastestDet.ncnn.param` | 15293 | `8b659855f15bec83…` |
| `assets/Griver/66666692_1.24.8.amr` | 287535 | `02b7cf32e5e71d62…` |
| `assets/Griver/66666693_2.0.7.amr` | 17380 | `212c15df244230e3…` |
| `assets/Griver/68687209_2.9.7.8.amr` | 856974 | `3e67a09c8d76338c…` |
| `assets/Griver/appx.json` | 2178 | `3b7a99da47f6f10d…` |
| `assets/GriverBridge/appx_inline_bootstrap_min.js` | 292 | `98e536752ad1eb0c…` |
| `assets/GriverBridge/cover_component.js` | 2888 | `0bfb0f20cb72e23a…` |
| `assets/GriverBridge/create_worker.html` | 159 | `c4737f85a1c7ea57…` |
| `assets/GriverBridge/h5_bridge.js` | 4446 | `e0f37c8b1163a14f…` |
| `assets/GriverBridge/index_inline.html` | 972 | `a48d9cd2f6d00022…` |
| `assets/GriverBridge/worker.html` | 203 | `92a87b5c6be964c7…` |
| `assets/GriverBridge/workerjs.js` | 14360 | `47c076ffe63168d2…` |
| `assets/GriverErrorPage/griver_custom_h5_page_error.html` | 3060 | `8483fdbc2245aba1…` |
| `assets/GriverErrorPage/griver_page_error.html` | 22734 | `b897eefc552cc6e1…` |
| `assets/GriverErrorPage/mini-program-native-error.html` | 13859 | `53eda0cc349ead89…` |
| `assets/GriverTheme/theme.json` | 584 | `72432cab511a423b…` |
| `assets/android.1.zip` | 3913966 | `e5358e94d6938eb1…` |
| `assets/android_jsbridge.js` | 3673 | `79d13b8a360b1290…` |
| `assets/audience_network.dex` | 4864036 | `10dc305fb2869db2…` |
| `assets/bshield.dat` | 929 | `b1750911acee6d39…` |
| `assets/bundle.json` | 312 | `21c10377141df90d…` |
| `assets/client_tracking.json` | 146391 | `17658dcae0a7e7a0…` |
| `assets/com/appsflyer/internal/1a7e8023f147cacac-` | 7142 | `6a24e0a7ae6967ec…` |
| `assets/com/appsflyer/internal/3e6e8a8a85e1aaa2b-` | 13692 | `3de7a0db70eddb66…` |
| `assets/com/appsflyer/internal/cc32cb866f63cd58a-` | 7936 | `d0efacf3f7f1d64d…` |
| `assets/com/appsflyer/internal/cf2b565222edadded-` | 3142 | `2bc0ed047defe818…` |
| `assets/com/visa/cbp/74e13961eb6489ab-` | 265304 | `317ed670fbd62a0c…` |
| `assets/com/visa/cbp/8c12f33e01f2a597a-` | 15736 | `65f56669c23d88f5…` |
| `assets/com/visa/cbp/a2687fec63dbeebcd-` | 80308 | `62b9e4ee0bba0dae…` |
| `assets/com/visa/cbp/f3f564a2648dc019c-` | 9668 | `3c99236cf065fdac…` |
| `assets/config/miniapp_config.json` | 883684 | `e69ba84631346485…` |
| `assets/config/public_key.txt` | 450 | `ab63708a7b557a13…` |
| `assets/config/signature.txt` | 350 | `fd7abb1a0d62a50a…` |
| `assets/config/zalopay_config.json` | 105659 | `740c82f914bd6399…` |
| `assets/containererrorpage/griver_custom_h5_page_error.html` | 3060 | `8483fdbc2245aba1…` |
| `assets/containererrorpage/griver_page_error.html` | 9594 | `568380640a97753f…` |
| `assets/containers/GTM-PC9W5BW.json` | 7702 | `5b739372c14abdb4…` |
| `assets/custom_models/object_labeler.tflite` | 3770391 | `9400671e04685f52…` |
| `assets/dabc8a718ce8084d304bab46923b374602a37c02804a1938f5200c73dcc9b2be/9f979174-fb94-f7f5-21c5-afddb32d1e32` | 17888 | `19008a8f7b59738f…` |
| `assets/dexopt/baseline.prof` | 18503 | `bc8fc048bab81b9a…` |
| `assets/dexopt/baseline.profm` | 1909 | `614658814ea66764…` |
| `assets/fgcpkjvqe.dat` | 776320 | `cb8c0211e579f103…` |
| `assets/fonts/Roboto-Bold.ttf` | 170064 | `a073f449858a3f03…` |
| `assets/fonts/Roboto-Medium.ttf` | 127488 | `6e2ec5c5f89e4ce3…` |
| `assets/fonts/Roboto-Regular.ttf` | 126072 | `bde8a188e37aa936…` |
| `assets/fonts/UnisectVnu.ttf` | 48216 | `a53d8ae65b7ca6af…` |
| `assets/fonts/ocr-a-tribute.ttf` | 48216 | `a53d8ae65b7ca6af…` |
| `assets/fonts/true_font.ttf` | 168644 | `9d0d55a303bfd13b…` |
| `assets/fonts/zalopay.json` | 70880 | `9af2f6bf0b740313…` |
| `assets/fonts/zalopay.ttf` | 143944 | `d6b2bdd7107fcc09…` |
| `assets/griverIconDefault/default.ttf` | 28000 | `f0f38ae010465c44…` |
| `assets/h5iconfont/h5titlebar.ttf` | 2300 | `1a0a0fb4d4ffd079…` |
| `assets/h5iconfont/titlebar.ttf` | 5016 | `4cfed842deb4dc40…` |
| `assets/home_data.json` | 9417 | `0a6b0afe13690ff2…` |
| `assets/iapconnect_config_full` | 1688 | `b6a55625c4866bfa…` |
| `assets/internaljsbridge.js` | 3890 | `0587ef4cfda3ab69…` |
| `assets/jsbridge.js` | 3634 | `2dfe8f8ba6a70ff3…` |
| `assets/mlkit-google-ocr-models/aksara/aksara_page_layout_analysis_rpn_gcn.binarypb` | 276 | `b2963b78c7e55fdf…` |
| `assets/mlkit-google-ocr-models/aksara/aksara_page_layout_analysis_ti_rpn_gcn.binarypb` | 278 | `d448c5de8d480d49…` |
| `assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc/optical/assets.extra/LabelMap.pb` | 3809 | `c6d345043a561f9d…` |
| `assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc/optical/conv_model.fb` | 40848 | `64aba719b4ed1ee5…` |
| `assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc/optical/lstm_model.fb` | 309568 | `0ed62e4fbe0fc5c4…` |
| `assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc_cpu.binarypb` | 333 | `a1dce9a376905a59…` |
| `assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/tflite_langid.tflite` | 315520 | `7f931f6f7c1dd0ec…` |
| `assets/mlkit-google-ocr-models/gocr/layout/line_clustering_custom_ops/model.tflite` | 129612 | `ecda697ed244c576…` |
| `assets/mlkit-google-ocr-models/gocr/layout/line_splitting_custom_ops/model.tflite` | 114384 | `f42197e656972c33…` |
| `assets/mlkit-google-ocr-models/taser/detector/region_proposal_text_detector_tflite_vertical_mbv2_v1.bincfg` | 452 | `7ac2d4c04fb2fe0c…` |
| `assets/mlkit-google-ocr-models/taser/detector/rpn_text_detector_mobile_space_to_depth_quantized_mbv2_v1.tflite` | 340760 | `eb811a1290738f13…` |
| `assets/mlkit-google-ocr-models/taser/rpn_text_detection_tflite_mobile_mbv2.binarypb` | 194 | `86660be29934bdd1…` |
| `assets/mlkit-google-ocr-models/taser/segmenter/tflite_script_detector_0.3.bincfg` | 1118 | `d4223c23cd8ee3d3…` |
| `assets/mlkit-google-ocr-models/taser/segmenter/tflite_script_detector_0.3.conv_model` | 40808 | `16be9fc0c0c9429c…` |
| `assets/mlkit-google-ocr-models/taser/segmenter/tflite_script_detector_0.3.lstm_model` | 161448 | `2281a01686f9ff91…` |
| `assets/mlkit-google-ocr-models/taser/taser_script_identification_tflite_mobile.binarypb` | 136 | `a3ebf2720f86e144…` |
| `assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_engine.binarypb` | 13178 | `78210a92ad8ff692…` |
| `assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_engine_ti.binarypb` | 13183 | `bfd6e9cb8ed0536d…` |
| `assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_recognizer.binarypb` | 233 | `e80538539371576a…` |
| `assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_runner.binarypb` | 331 | `32c2c1b72ba32605…` |
| `assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_runner_ti.binarypb` | 334 | `2c8581869ebb972a…` |
| `assets/mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite` | 390456 | `6c7b209f4256ca45…` |
| `assets/mlkit_barcode_models/oned_auto_regressor_mobile.tflite` | 213880 | `8e07cd3b38da1ded…` |
| `assets/mlkit_barcode_models/oned_feature_extractor_mobile.tflite` | 276552 | `981c89dcca6aac10…` |
| `assets/mlkit_odt_default_classifier/labeler_with_validation.tflite` | 1706957 | `479cb95767462b2f…` |
| `assets/mlkit_odt_localizer/localizer_with_validation.tflite` | 1254848 | `fb5e5188b03c3bc0…` |
| `assets/mlkit_odt_localizer/mobile_object_localizer_3_1_anchors.pb` | 153000 | `9932a0b82471e50d…` |
| `assets/mlkit_odt_localizer/mobile_object_localizer_labelmap` | 22 | `ae7d4f4c2e989dce…` |
| `assets/models_bundled/BCLjoy_200.emd` | 17916 | `f00f25644a628e73…` |
| `assets/models_bundled/BCLlefteyeclosed_200.emd` | 8630 | `d31213e4f25ff8b2…` |
| `assets/models_bundled/BCLrighteyeclosed_200.emd` | 8655 | `63ca0bd838e51977…` |
| `assets/models_bundled/LMprec_600.emd` | 1078354 | `0ed5c85282f940d1…` |
| `assets/models_bundled/MFT_fssd_accgray.pb` | 793 | `f0adb04d7b21b4d9…` |
| `assets/models_bundled/MFT_fssd_fastgray.pb` | 792 | `0943425df1b350e0…` |
| `assets/models_bundled/blazeface.tfl` | 347928 | `4f25cc142e96b5f2…` |
| `assets/models_bundled/contours.tfl` | 1124144 | `84160bc0c4a74951…` |
| `assets/models_bundled/fssd_25_8bit_gray_v2.tflite` | 231816 | `57dc1f2a3b9c0c5d…` |
| `assets/models_bundled/fssd_25_8bit_v2.tflite` | 232096 | `d8abae91b0e0af52…` |
| `assets/models_bundled/fssd_anchors_v2.pb` | 146 | `7516ed46c04ab0fb…` |
| `assets/models_bundled/fssd_anchors_v5.pb` | 144 | `5390cab6b491eb32…` |
| `assets/models_bundled/fssd_medium_8bit_gray_v5.tflite` | 613080 | `f0590874fd3a006c…` |
| `assets/models_bundled/fssd_medium_8bit_v5.tflite` | 613352 | `ae2fa78e29441438…` |
| `assets/public.pem` | 460 | `0d4383c52c0f29e7…` |
| `assets/safemode_registrator_com_iap_ac_config.json` | 80 | `7369e6fce29a5658…` |
| `assets/taibmcf.dat` | 8307 | `392c01d84cc770b0…` |
| `assets/taibmcf.json` | 46 | `276833c96dde3cee…` |
| `assets/taxi_images/Artboard_1.png` | 7292 | `ea18e4e573cdd5cc…` |
| `assets/taxi_images/Artboard_2.png` | 6430 | `a3d3fa854f60359b…` |
| `assets/taxi_images/Layer_2_loading.png` | 5649 | `9c93e8097b6fd8b7…` |
| `assets/taxi_images/Layer_3_loading.png` | 5792 | `55d1cacd105bfb58…` |
| `assets/taxi_images/Layer_4_loading.png` | 14652 | `fb9f0d4c58a3f978…` |
| `assets/taxi_images/Layer_5_loading.png` | 42205 | `9397331f2b99689e…` |
| `assets/taxi_images/Layer_7_loading.png` | 520 | `d50afb8a8f662e77…` |
| `assets/taxi_images/Layer_8_loading.png` | 2636 | `1869e8557bc9422b…` |
| `assets/taxi_images/_____1_loading.png` | 6123 | `0836fdd3b102764c…` |
| `assets/taxi_splash_loading.json` | 536057 | `c68296d32ff394e1…` |
| `assets/theme/bank/bank_resource.json` | 11339 | `bf584e41fde15aaf…` |
| `assets/theme/bank/config_v2.json` | 11573 | `258535cabe5cb6d8…` |
| `assets/theme/bank/ui_config.json` | 15430 | `1efac77f60bba109…` |
| `assets/timezone2region_2023c.json` | 11067 | `5a8407b1dd33bdf0…` |
| `assets/tinyfont/iconfont.ttf` | 9780 | `209f6617fdf5be8d…` |
| `assets/tinyfont/titlebar.ttf` | 5016 | `4cfed842deb4dc40…` |
| `assets/trueidlib.so` | 392 | `055e573dbee6ee9d…` |
| `assets/vdfmoer.json` | 46 | `276833c96dde3cee…` |
| `assets/webapp.js` | 619 | `73242fce8a393809…` |
| `assets/zoom.public` | 450 | `d8f31eca4380e7cd…` |
| `barcode-scanning-common.properties` | 84 | `98245f350edba9b8…` |
| `barcode-scanning.properties` | 70 | `045a350629cb114c…` |
| `billing-ktx.properties` | 58 | `4091622a45bc5ac3…` |
| `billing.properties` | 50 | `d748e65c1bfd6ea1…` |
| `classes.dex` | 8831004 | `4572f052124fb4eb…` |
| `classes10.dex` | 78204 | `c2523d14c1373950…` |
| `classes11.dex` | 163420 | `c6ee2d78f32137cb…` |
| `classes12.dex` | 398276 | `6742bfd85b7f61e1…` |
| `classes13.dex` | 145824 | `eb3fffe1dace3619…` |
| `classes14.dex` | 81748 | `63bc3727cf4b3674…` |
| `classes15.dex` | 82152 | `a8b037ff84ee078e…` |
| `classes16.dex` | 15916 | `331b4ebe76e9a715…` |
| `classes17.dex` | 208476 | `70f01df8cb8b3966…` |
| `classes18.dex` | 95920 | `326bdb3326e630df…` |
| `classes19.dex` | 719548 | `4e7f019ebd9b76f6…` |
| `classes2.dex` | 16516960 | `af4987a99dd7bb69…` |
| `classes3.dex` | 850600 | `7e84baa2c50fd465…` |
| `classes4.dex` | 9418124 | `77eb2deb3c18c0a5…` |
| `classes5.dex` | 7357484 | `c6fdc65622c1855a…` |
| `classes6.dex` | 7612432 | `1484dd8a338f8526…` |
| `classes7.dex` | 10803680 | `5f356407a685dbe3…` |
| `classes8.dex` | 239036 | `2e6d27956d455d47…` |
| `classes9.dex` | 1218908 | `e9b46539c92987be…` |
| `client_analytics.proto` | 2982 | `0c76732776772b26…` |
| `common.properties` | 52 | `6e34f8b8269d0485…` |
| `core-common.properties` | 58 | `b5153b8b6a843b01…` |
| `face-detection.properties` | 66 | `5aff3f5234c16750…` |
| `feature-delivery.properties` | 68 | `35d8eb299c98f6ab…` |
| `firebase-abt.properties` | 62 | `60eaf8e3c5730f08…` |
| `firebase-analytics-ktx.properties` | 82 | `92ec735b6c77f251…` |
| `firebase-analytics.properties` | 74 | `e059e30afb32535a…` |
| `firebase-annotations.properties` | 78 | `0a9af694f0a979e0…` |
| `firebase-config.properties` | 68 | `b9f4af450b78a032…` |
| `firebase-core.properties` | 64 | `91d6f4b616661b21…` |
| `firebase-encoders-proto.properties` | 84 | `eba792be6ea03f3b…` |
| `firebase-encoders.properties` | 72 | `527438e526e7befb…` |
| `firebase-iid-interop.properties` | 78 | `a2862ae5811a2686…` |
| `firebase-iid.properties` | 62 | `8334ddfaa5cb9015…` |
| `firebase-measurement-connector.properties` | 98 | `2f03cfa9f9dbe4c8…` |
| `firebase-messaging.properties` | 74 | `3555a61d926c2add…` |
| `firebase-perf.properties` | 64 | `2604d9dbedae1ba1…` |
| `getbanklist_zpm_success.json` | 1917 | `3815b55bc1dde9a1…` |
| `google/protobuf/any.proto` | 5878 | `cc62688f5162757a…` |
| `google/protobuf/api.proto` | 7734 | `dc01037a072410e5…` |
| `google/protobuf/empty.proto` | 2422 | `b18e0cd44e0d0542…` |
| `google/protobuf/field_mask.proto` | 8192 | `a6c7f43b9d31f386…` |
| `google/protobuf/source_context.proto` | 2352 | `64ac204d47270547…` |
| `google/protobuf/struct.proto` | 3780 | `132e774c82b3cb33…` |
| `google/protobuf/timestamp.proto` | 6200 | `7acbd7ab84c15ebc…` |
| `google/protobuf/type.proto` | 6129 | `a65db52e1f65466d…` |
| `google/protobuf/wrappers.proto` | 4035 | `514a00eac8caa679…` |
| `image.properties` | 58 | `f152de3e1791dcee…` |
| `integrity.properties` | 54 | `4288c259a7390b19…` |
| `junit/runner/logo.gif` | 964 | `345b5e2207a524e0…` |
| `junit/runner/smalllogo.gif` | 883 | `b4819008f181bf3e…` |
| `kotlin-tooling-metadata.json` | 627 | `d9eeec89a498d3d4…` |
| `kotlin/annotation/annotation.kotlin_builtins` | 1006 | `5f75d4352e432664…` |
| `kotlin/collections/collections.kotlin_builtins` | 8308 | `0e654e03a38e119d…` |
| `kotlin/concurrent/atomics/atomics.kotlin_builtins` | 2674 | `955035527393d165…` |
| `kotlin/coroutines/coroutines.kotlin_builtins` | 137 | `98ab3a3c18fcb237…` |
| `kotlin/internal/internal.kotlin_builtins` | 590 | `64712f94840e6b3f…` |
| `kotlin/kotlin.kotlin_builtins` | 29399 | `c4240215bc20e71b…` |
| `kotlin/ranges/ranges.kotlin_builtins` | 4184 | `f9d35b836ccb5380…` |
| `kotlin/reflect/reflect.kotlin_builtins` | 4827 | `23ecbc7cbb354f31…` |
| `object-detection-common.properties` | 84 | `6cc2d2705ac7c581…` |
| `object-detection.properties` | 70 | `1bd7b4a319aaaafb…` |
| `okhttp3/internal/publicsuffix/NOTICE` | 218 | `8a9c58fbded5ea34…` |
| `okhttp3/internal/publicsuffix/publicsuffixes.gz` | 41394 | `9af43e9995ec749e…` |
| `org/apache/commons/codec/language/bm/ash_approx_any.txt` | 4708 | `ad6c75e0e50b51f8…` |
| `org/apache/commons/codec/language/bm/ash_approx_common.txt` | 4971 | `da5b7febc70f860f…` |
| `org/apache/commons/codec/language/bm/ash_approx_cyrillic.txt` | 831 | `a29917ea4c00bdd2…` |
| `org/apache/commons/codec/language/bm/ash_approx_english.txt` | 1489 | `53739bd278682923…` |
| `org/apache/commons/codec/language/bm/ash_approx_french.txt` | 1224 | `19650e4ab69678bb…` |
| `org/apache/commons/codec/language/bm/ash_approx_german.txt` | 1852 | `cfc7c1ef41209af8…` |
| `org/apache/commons/codec/language/bm/ash_approx_hebrew.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/ash_approx_hungarian.txt` | 830 | `6bd249910726949a…` |
| `org/apache/commons/codec/language/bm/ash_approx_polish.txt` | 2228 | `5f56627d50d4ef0e…` |
| `org/apache/commons/codec/language/bm/ash_approx_romanian.txt` | 830 | `900bcb8b852ce973…` |
| `org/apache/commons/codec/language/bm/ash_approx_russian.txt` | 1427 | `b908b81325099091…` |
| `org/apache/commons/codec/language/bm/ash_approx_spanish.txt` | 830 | `6bd249910726949a…` |
| `org/apache/commons/codec/language/bm/ash_exact_any.txt` | 1952 | `bc6b6b423e6af997…` |
| `org/apache/commons/codec/language/bm/ash_exact_approx_common.txt` | 1736 | `a1f947e071423aa5…` |
| `org/apache/commons/codec/language/bm/ash_exact_common.txt` | 1095 | `fc3558377839a103…` |
| `org/apache/commons/codec/language/bm/ash_exact_cyrillic.txt` | 830 | `265d93b530f2f0b3…` |
| `org/apache/commons/codec/language/bm/ash_exact_english.txt` | 830 | `265d93b530f2f0b3…` |
| `org/apache/commons/codec/language/bm/ash_exact_french.txt` | 830 | `265d93b530f2f0b3…` |
| `org/apache/commons/codec/language/bm/ash_exact_german.txt` | 826 | `32674f342f7c8b73…` |
| `org/apache/commons/codec/language/bm/ash_exact_hebrew.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/ash_exact_hungarian.txt` | 830 | `265d93b530f2f0b3…` |
| `org/apache/commons/codec/language/bm/ash_exact_polish.txt` | 875 | `d04d6e8ea4ed593f…` |
| `org/apache/commons/codec/language/bm/ash_exact_romanian.txt` | 830 | `265d93b530f2f0b3…` |
| `org/apache/commons/codec/language/bm/ash_exact_russian.txt` | 831 | `34bfa509511f7517…` |
| `org/apache/commons/codec/language/bm/ash_exact_spanish.txt` | 830 | `265d93b530f2f0b3…` |
| `org/apache/commons/codec/language/bm/ash_hebrew_common.txt` | 2983 | `ad11a0f537e90b08…` |
| `org/apache/commons/codec/language/bm/ash_lang.txt` | 4874 | `93bc49f227498c31…` |
| `org/apache/commons/codec/language/bm/ash_languages.txt` | 888 | `ee736888a0725497…` |
| `org/apache/commons/codec/language/bm/ash_rules_any.txt` | 9451 | `bdb60c3901ab057f…` |
| `org/apache/commons/codec/language/bm/ash_rules_cyrillic.txt` | 2366 | `361bc87691da6bfd…` |
| `org/apache/commons/codec/language/bm/ash_rules_english.txt` | 2970 | `7c28986e7dc911d8…` |
| `org/apache/commons/codec/language/bm/ash_rules_french.txt` | 2274 | `234f4b1eeacea6c8…` |
| `org/apache/commons/codec/language/bm/ash_rules_german.txt` | 2787 | `00b0a4baf27ff74f…` |
| `org/apache/commons/codec/language/bm/ash_rules_hebrew.txt` | 1472 | `1e36828bd90a0721…` |
| `org/apache/commons/codec/language/bm/ash_rules_hungarian.txt` | 1960 | `a247af30ac47cbaa…` |
| `org/apache/commons/codec/language/bm/ash_rules_polish.txt` | 4167 | `047bf7630b038e18…` |
| `org/apache/commons/codec/language/bm/ash_rules_romanian.txt` | 1526 | `212f03c2a1c539a0…` |
| `org/apache/commons/codec/language/bm/ash_rules_russian.txt` | 3980 | `9d9904d23ec6031f…` |
| `org/apache/commons/codec/language/bm/ash_rules_spanish.txt` | 2073 | `c9e410a8a5c0965b…` |
| `org/apache/commons/codec/language/bm/gen_approx_any.txt` | 4043 | `a72bbd45de8a31aa…` |
| `org/apache/commons/codec/language/bm/gen_approx_arabic.txt` | 991 | `838a47e6f968189a…` |
| `org/apache/commons/codec/language/bm/gen_approx_common.txt` | 5120 | `edf03e4f49c343d4…` |
| `org/apache/commons/codec/language/bm/gen_approx_cyrillic.txt` | 831 | `f9a750d48156d8e8…` |
| `org/apache/commons/codec/language/bm/gen_approx_czech.txt` | 830 | `e52b12a250fa593f…` |
| `org/apache/commons/codec/language/bm/gen_approx_dutch.txt` | 830 | `e52b12a250fa593f…` |
| `org/apache/commons/codec/language/bm/gen_approx_english.txt` | 1489 | `53739bd278682923…` |
| `org/apache/commons/codec/language/bm/gen_approx_french.txt` | 942 | `1d9d34f64e5d8d30…` |
| `org/apache/commons/codec/language/bm/gen_approx_german.txt` | 1853 | `4330f43e087759d3…` |
| `org/apache/commons/codec/language/bm/gen_approx_greek.txt` | 830 | `e52b12a250fa593f…` |
| `org/apache/commons/codec/language/bm/gen_approx_greeklatin.txt` | 845 | `dffa002d397f8513…` |
| `org/apache/commons/codec/language/bm/gen_approx_hebrew.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_approx_hungarian.txt` | 831 | `f827f54af6560a4c…` |
| `org/apache/commons/codec/language/bm/gen_approx_italian.txt` | 831 | `f827f54af6560a4c…` |
| `org/apache/commons/codec/language/bm/gen_approx_polish.txt` | 2183 | `d062343f0330e121…` |
| `org/apache/commons/codec/language/bm/gen_approx_portuguese.txt` | 830 | `e52b12a250fa593f…` |
| `org/apache/commons/codec/language/bm/gen_approx_romanian.txt` | 830 | `4e35dd86b22d8d01…` |
| `org/apache/commons/codec/language/bm/gen_approx_russian.txt` | 1437 | `15e71e33faf7bb45…` |
| `org/apache/commons/codec/language/bm/gen_approx_spanish.txt` | 868 | `ca9391f883011b74…` |
| `org/apache/commons/codec/language/bm/gen_approx_turkish.txt` | 830 | `e52b12a250fa593f…` |
| `org/apache/commons/codec/language/bm/gen_exact_any.txt` | 1226 | `cf8da3ee51d478dc…` |
| `org/apache/commons/codec/language/bm/gen_exact_approx_common.txt` | 1769 | `948f6d4355ac7401…` |
| `org/apache/commons/codec/language/bm/gen_exact_arabic.txt` | 816 | `891a20110c50fd5d…` |
| `org/apache/commons/codec/language/bm/gen_exact_common.txt` | 1093 | `6a943ca8cf3f7bed…` |
| `org/apache/commons/codec/language/bm/gen_exact_cyrillic.txt` | 830 | `fd5517e98eebfbe1…` |
| `org/apache/commons/codec/language/bm/gen_exact_czech.txt` | 830 | `fd5517e98eebfbe1…` |
| `org/apache/commons/codec/language/bm/gen_exact_dutch.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_english.txt` | 830 | `fd5517e98eebfbe1…` |
| `org/apache/commons/codec/language/bm/gen_exact_french.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_german.txt` | 826 | `abe0c2b293dde37d…` |
| `org/apache/commons/codec/language/bm/gen_exact_greek.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_greeklatin.txt` | 817 | `8784c2b1d8b8e206…` |
| `org/apache/commons/codec/language/bm/gen_exact_hebrew.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_hungarian.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_italian.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_polish.txt` | 874 | `6d805f7fc3172f02…` |
| `org/apache/commons/codec/language/bm/gen_exact_portuguese.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_romanian.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_exact_russian.txt` | 831 | `34bfa509511f7517…` |
| `org/apache/commons/codec/language/bm/gen_exact_spanish.txt` | 832 | `b3388ed5a9817dfa…` |
| `org/apache/commons/codec/language/bm/gen_exact_turkish.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/gen_hebrew_common.txt` | 2845 | `4a989b869f229359…` |
| `org/apache/commons/codec/language/bm/gen_lang.txt` | 7594 | `705d3b0eca3803ab…` |
| `org/apache/commons/codec/language/bm/gen_languages.txt` | 951 | `b3165adaa36026c2…` |
| `org/apache/commons/codec/language/bm/gen_rules_any.txt` | 13059 | `d23d576829821edd…` |
| `org/apache/commons/codec/language/bm/gen_rules_arabic.txt` | 2208 | `82b707c79998031b…` |
| `org/apache/commons/codec/language/bm/gen_rules_cyrillic.txt` | 2300 | `0c8158127f19bd0e…` |
| `org/apache/commons/codec/language/bm/gen_rules_czech.txt` | 1561 | `84de9e152412cd61…` |
| `org/apache/commons/codec/language/bm/gen_rules_dutch.txt` | 1867 | `9921e75dbbc942d2…` |
| `org/apache/commons/codec/language/bm/gen_rules_english.txt` | 3013 | `bcfb8723cbf3966b…` |
| `org/apache/commons/codec/language/bm/gen_rules_french.txt` | 2778 | `b5f12b085cfb4896…` |
| `org/apache/commons/codec/language/bm/gen_rules_german.txt` | 2735 | `453d474ab996523a…` |
| `org/apache/commons/codec/language/bm/gen_rules_greek.txt` | 2918 | `6b56c5d8a9b6270f…` |
| `org/apache/commons/codec/language/bm/gen_rules_greeklatin.txt` | 3041 | `06122f03fe879f2e…` |
| `org/apache/commons/codec/language/bm/gen_rules_hebrew.txt` | 1462 | `a570f37f45f35f2e…` |
| `org/apache/commons/codec/language/bm/gen_rules_hungarian.txt` | 1887 | `16ba7cf79bf85089…` |
| `org/apache/commons/codec/language/bm/gen_rules_italian.txt` | 1873 | `99155dedf0caeaae…` |
| `org/apache/commons/codec/language/bm/gen_rules_polish.txt` | 4112 | `e4e0eb610a8bccf7…` |
| `org/apache/commons/codec/language/bm/gen_rules_portuguese.txt` | 2598 | `0c51c2cc22988516…` |
| `org/apache/commons/codec/language/bm/gen_rules_romanian.txt` | 1509 | `75ee26f3c6bf6caf…` |
| `org/apache/commons/codec/language/bm/gen_rules_russian.txt` | 3042 | `ded105c857167c2b…` |
| `org/apache/commons/codec/language/bm/gen_rules_spanish.txt` | 2382 | `e7253c7c1ab7e46d…` |
| `org/apache/commons/codec/language/bm/gen_rules_turkish.txt` | 1386 | `1800bab9aee6be5f…` |
| `org/apache/commons/codec/language/bm/lang.txt` | 819 | `05987a68044246aa…` |
| `org/apache/commons/codec/language/bm/sep_approx_any.txt` | 830 | `47661c92b7bd2a3d…` |
| `org/apache/commons/codec/language/bm/sep_approx_common.txt` | 3195 | `7f3ea2985fd4b1b5…` |
| `org/apache/commons/codec/language/bm/sep_approx_french.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/sep_approx_hebrew.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/sep_approx_italian.txt` | 830 | `b6165dbda3d972b5…` |
| `org/apache/commons/codec/language/bm/sep_approx_portuguese.txt` | 831 | `21d632279772ae89…` |
| `org/apache/commons/codec/language/bm/sep_approx_spanish.txt` | 831 | `21d632279772ae89…` |
| `org/apache/commons/codec/language/bm/sep_exact_any.txt` | 817 | `df3f63b3946b8d5f…` |
| `org/apache/commons/codec/language/bm/sep_exact_approx_common.txt` | 1685 | `232f59750f9dedcf…` |
| `org/apache/commons/codec/language/bm/sep_exact_common.txt` | 1109 | `c3654ad436be1853…` |
| `org/apache/commons/codec/language/bm/sep_exact_french.txt` | 815 | `9ddcb79875c3a492…` |
| `org/apache/commons/codec/language/bm/sep_exact_hebrew.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/sep_exact_italian.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/sep_exact_portuguese.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/sep_exact_spanish.txt` | 812 | `d54f26f806ec83a3…` |
| `org/apache/commons/codec/language/bm/sep_hebrew_common.txt` | 2187 | `f54d8a4d50ac4331…` |
| `org/apache/commons/codec/language/bm/sep_lang.txt` | 2431 | `f3b5f5fda135f0b1…` |
| `org/apache/commons/codec/language/bm/sep_languages.txt` | 849 | `9cc12d4172e41564…` |
| `org/apache/commons/codec/language/bm/sep_rules_any.txt` | 4632 | `a334356decf81c18…` |
| `org/apache/commons/codec/language/bm/sep_rules_french.txt` | 2224 | `2a3cbe347fda0df5…` |
| `org/apache/commons/codec/language/bm/sep_rules_hebrew.txt` | 1459 | `545919eeecaee730…` |
| `org/apache/commons/codec/language/bm/sep_rules_italian.txt` | 1848 | `3b4d253710d0c9dd…` |
| `org/apache/commons/codec/language/bm/sep_rules_portuguese.txt` | 2573 | `8a1e06755eba82e4…` |
| `org/apache/commons/codec/language/bm/sep_rules_spanish.txt` | 2480 | `57654b4355e47b90…` |
| `org/apache/commons/codec/language/dmrules.txt` | 3545 | `90ff9e9c775e0144…` |
| `org/bouncycastle/pkix/CertPathReviewerMessages.properties` | 42868 | `9fbb991695b174b3…` |
| `org/bouncycastle/pkix/CertPathReviewerMessages_de.properties` | 49608 | `1e5085ab80e41556…` |
| `org/bouncycastle/pqc/crypto/picnic/lowmcL1.bin.properties` | 118306 | `4f4ae14c89cbd041…` |
| `org/bouncycastle/pqc/crypto/picnic/lowmcL3.bin.properties` | 350101 | `2f513e6c9a33a9ef…` |
| `org/bouncycastle/pqc/crypto/picnic/lowmcL5.bin.properties` | 752652 | `b2b3a0e90d15133f…` |
| `org/bouncycastle/x509/CertPathReviewerMessages.properties` | 42868 | `9fbb991695b174b3…` |
| `org/bouncycastle/x509/CertPathReviewerMessages_de.properties` | 49608 | `1e5085ab80e41556…` |
| `org/jacoco/core/jacoco.properties` | 166 | `50405229dd77d8f5…` |
| `play-services-ads-base.properties` | 82 | `9206fdf17f2cb20e…` |
| `play-services-ads-identifier.properties` | 94 | `13d8d24908d33285…` |
| `play-services-ads-lite.properties` | 82 | `02a452fb9a416527…` |
| `play-services-ads.properties` | 72 | `4c3ab010dd62374e…` |
| `play-services-analytics-impl.properties` | 94 | `979fefeb3d2939ae…` |
| `play-services-appset.properties` | 78 | `195c8fe12d8026f3…` |
| `play-services-auth-api-phone.properties` | 94 | `2a5c1c6f7f2f2041…` |
| `play-services-base.properties` | 74 | `8d3f6754abfac37a…` |
| `play-services-basement.properties` | 82 | `f104c265304c046a…` |
| `play-services-clearcut.properties` | 82 | `52fdcc3b832551bd…` |
| `play-services-cloud-messaging.properties` | 96 | `712efbde0a118e41…` |
| `play-services-flags.properties` | 76 | `a9d938e4ebe876d9…` |
| `play-services-identity.properties` | 82 | `d2d180a3874b3ebd…` |
| `play-services-location.properties` | 82 | `df7e5be0e368c737…` |
| `play-services-maps.properties` | 74 | `44b14aa828f60f47…` |
| `play-services-measurement-api.properties` | 96 | `a797a23d110fe447…` |
| `play-services-measurement-base.properties` | 98 | `cd11c102cd66b4e9…` |
| `play-services-measurement-impl.properties` | 98 | `f44cd1361c990678…` |
| `play-services-measurement-sdk-api.properties` | 104 | `be65e971e6034961…` |
| `play-services-measurement-sdk.properties` | 96 | `9c6ac5a744c23641…` |
| `play-services-measurement.properties` | 88 | `2e6cc31102ebdcec…` |
| `play-services-mlkit-barcode-scanning.properties` | 110 | `44e42a08e2deb8d8…` |
| `play-services-mlkit-face-detection.properties` | 106 | `f9427fe0ea357d50…` |
| `play-services-mlkit-subject-segmentation.properties` | 130 | `0d37e40e2705159c…` |
| `play-services-mlkit-text-recognition-common.properties` | 124 | `d123efb15add2faa…` |
| `play-services-mlkit-text-recognition.properties` | 110 | `c4d4e9e9b7029735…` |
| `play-services-phenotype.properties` | 84 | `eb39613337b346ed…` |
| `play-services-places-placereport.properties` | 102 | `dcb990b67e7beba7…` |
| `play-services-safetynet.properties` | 84 | `27c7b0dfd0bf0fc6…` |
| `play-services-stats.properties` | 76 | `be02efae490fed7d…` |
| `play-services-tagmanager-api.properties` | 94 | `367e57c158049ecb…` |
| `play-services-tagmanager.properties` | 86 | `7a60e211a79c0bfd…` |
| `play-services-tasks.properties` | 76 | `e82b1e7f5fbe3788…` |
| `play-services-vision-common.properties` | 92 | `e0c4f5c448bc73dc…` |
| `play-services-vision.properties` | 78 | `ac940bdd8eff16fe…` |
| `play-services-wallet.properties` | 78 | `34e0b23fffe2bc6e…` |
| `res/anim-ldrtl-v17/griver_core_app_close_enter_left_in.xml` | 588 | `d986d5ac8fd7a967…` |
| `res/anim-ldrtl-v17/griver_core_app_close_exit_right_out.xml` | 588 | `0cc6450b489fcd6c…` |
| `res/anim-ldrtl-v17/griver_core_app_open_enter_right_in.xml` | 588 | `cbc5a40d310930c3…` |
| `res/anim-ldrtl-v17/griver_core_app_open_exit_left_out.xml` | 588 | `fbf4d741c0d64031…` |
| `res/anim-v21/design_bottom_sheet_slide_in.xml` | 616 | `9b4affa71cdcd0d2…` |
| `res/anim-v21/design_bottom_sheet_slide_out.xml` | 616 | `bc8229f03f00da4d…` |
| `res/anim-v21/fragment_fast_out_extra_slow_in.xml` | 364 | `650c54c5eff73409…` |
| `res/anim-v21/mtrl_bottom_sheet_slide_in.xml` | 616 | `a155403d68ca77c3…` |
| `res/anim-v21/mtrl_bottom_sheet_slide_out.xml` | 616 | `e2f84fad7c099f3c…` |
| `res/anim/abc_grow_fade_in_from_bottom.xml` | 852 | `ca986074e2b68b9b…` |
| `res/anim/abc_popup_enter.xml` | 508 | `9323ced5423b07e2…` |
| `res/anim/abc_popup_exit.xml` | 508 | `38c4c440982e3eae…` |
| `res/anim/abc_shrink_fade_out_from_bottom.xml` | 852 | `cca7f663b54eea67…` |
| `res/anim/ariver_fragment_translate_in_left_default.xml` | 420 | `e2f8e12451425e46…` |
| `res/anim/ariver_fragment_translate_in_right_default.xml` | 420 | `82cc3d0208e547c6…` |
| `res/anim/ariver_fragment_translate_out_left_default.xml` | 420 | `6362f84ebf3eba4a…` |
| `res/anim/ariver_fragment_translate_out_right_default.xml` | 420 | `1a1f15e4a50f784d…` |
| `res/anim/btn_checkbox_to_checked_box_inner_merged_animation.xml` | 2124 | `4369cdf829c40269…` |
| `res/anim/btn_checkbox_to_checked_box_outer_merged_animation.xml` | 2780 | `02498b3366e0e1be…` |
| `res/anim/btn_checkbox_to_checked_icon_null_animation.xml` | 1196 | `8bf23cd077c3b9ff…` |
| `res/anim/btn_checkbox_to_unchecked_box_inner_merged_animation.xml` | 2360 | `fb6c25a1af1868b9…` |
| `res/anim/btn_checkbox_to_unchecked_check_path_merged_animation.xml` | 2520 | `9104ddf7f8f3fd0e…` |
| `res/anim/btn_checkbox_to_unchecked_icon_null_animation.xml` | 1196 | `1dbc0b2003517242…` |
| `res/anim/btn_radio_to_off_mtrl_dot_group_animation.xml` | 1656 | `af434183ae0c744f…` |
| `res/anim/btn_radio_to_off_mtrl_ring_outer_animation.xml` | 1656 | `f5c2c69cd939f7b4…` |
| `res/anim/btn_radio_to_off_mtrl_ring_outer_path_animation.xml` | 1028 | `40408a2406c87d8f…` |
| `res/anim/btn_radio_to_on_mtrl_dot_group_animation.xml` | 1656 | `07a0d834c8f6dca2…` |
| `res/anim/btn_radio_to_on_mtrl_ring_outer_animation.xml` | 1656 | `635a0eab1e993efb…` |
| `res/anim/btn_radio_to_on_mtrl_ring_outer_path_animation.xml` | 1028 | `1db0bf03b3f8f751…` |
| `res/anim/catalyst_fade_in.xml` | 388 | `79c19eb79cd8ec47…` |
| `res/anim/catalyst_fade_out.xml` | 388 | `2cd1ec7c06e2bab7…` |
| `res/anim/error_x_in.xml` | 1116 | `19584e0b212e1b3f…` |
| `res/anim/face_in_down.xml` | 696 | `8162316c10ffc76e…` |
| `res/anim/face_in_up.xml` | 696 | `680e2ea4f4e98201…` |
| `res/anim/fci_zoom.xml` | 784 | `faa2330b8902e3b2…` |
| `res/anim/fci_zoom_in.xml` | 744 | `12e31e8887102d44…` |
| `res/anim/fci_zoom_out.xml` | 784 | `33c873c9f85da87b…` |
| `res/anim/glance_btn_checkbox_to_checked_box_inner_merged_animation.xml` | 2124 | `2f28c791cf4fe7f1…` |
| `res/anim/glance_btn_checkbox_to_checked_box_outer_merged_animation.xml` | 2780 | `4ec9dbfbc114c5a2…` |
| `res/anim/glance_btn_checkbox_to_checked_icon_null_animation.xml` | 1196 | `5791f9866afa7653…` |
| `res/anim/glance_btn_checkbox_to_unchecked_box_inner_merged_animation.xml` | 2360 | `ec65a9fea4aa1659…` |
| `res/anim/glance_btn_checkbox_to_unchecked_check_path_merged_animation.xml` | 2520 | `55c806e52aedef6a…` |
| `res/anim/glance_btn_checkbox_to_unchecked_icon_null_animation.xml` | 1196 | `b1038379ef113c43…` |
| `res/anim/glance_btn_radio_to_off_mtrl_dot_group_animation.xml` | 1656 | `fc00c6e01ab5135b…` |
| `res/anim/glance_btn_radio_to_off_mtrl_ring_outer_animation.xml` | 1656 | `23d9bae333326d93…` |
| `res/anim/glance_btn_radio_to_off_mtrl_ring_outer_path_animation.xml` | 1028 | `a9ba5e23e04e3dad…` |
| `res/anim/glance_btn_radio_to_on_mtrl_dot_group_animation.xml` | 1656 | `7e7cd6029b0e6862…` |
| `res/anim/glance_btn_radio_to_on_mtrl_ring_outer_animation.xml` | 1656 | `efd978701faae0f1…` |
| `res/anim/glance_btn_radio_to_on_mtrl_ring_outer_path_animation.xml` | 1028 | `d740b000fc728fc5…` |
| `res/anim/griver_anim_in_bottom_popup_dialog.xml` | 464 | `47c02ca6d0e536c6…` |
| `res/anim/griver_anim_out_bottom_popup_dialog.xml` | 464 | `1a04f1c1b5a6ab94…` |
| `res/anim/griver_core_app_close_enter_left_in.xml` | 588 | `c695a699e3ae65e7…` |
| `res/anim/griver_core_app_close_exit_right_out.xml` | 588 | `0163a5ef48e8ec1c…` |
| `res/anim/griver_core_app_open_enter_right_in.xml` | 588 | `eec4dc4bbd22f5c0…` |
| `res/anim/griver_core_app_open_exit_left_out.xml` | 588 | `a53c54952621be95…` |
| `res/anim/griver_image_effect_select_pop_down.xml` | 396 | `ee13f61e56c4d1ed…` |
| `res/anim/griver_image_fade_in.xml` | 388 | `855052d0ad3dd4de…` |
| `res/anim/griver_image_fade_out.xml` | 388 | `ad66ea8a36a3cf1e…` |
| `res/anim/griver_image_select_pop_up.xml` | 396 | `dcb7da45627ae494…` |
| `res/anim/griver_image_slide_down.xml` | 396 | `53aa9ac2147353f7…` |
| `res/anim/griver_image_slide_up.xml` | 396 | `f0d405e7e0d4e097…` |
| `res/anim/item_fall_down_anim.xml` | 784 | `8155d29dd3845455…` |
| `res/anim/keyboard_slide_down.xml` | 464 | `36ff27cfde0e4431…` |
| `res/anim/keyboard_slide_up.xml` | 464 | `0d59ebd6c6f10f8f…` |
| `res/anim/layout_fall_down_anim.xml` | 360 | `a499beddf6633eca…` |
| `res/anim/modal_in.xml` | 1296 | `89268c6253f4aae9…` |
| `res/anim/modal_out.xml` | 664 | `1770f0c9007c18fb…` |
| `res/anim/mtrl_card_lowers_interpolator.xml` | 400 | `eaf226a569519284…` |
| `res/anim/shakeanim.xml` | 504 | `11be8cf1bd3654c9…` |
| `res/anim/slide_bottom.xml` | 464 | `0d3b15eb9a9fcba7…` |
| `res/anim/slide_down_animation.xml` | 464 | `17f08ba7b042e88e…` |
| `res/anim/slide_in_bottom.xml` | 548 | `b940453b75239aca…` |
| `res/anim/slide_in_from_bottom.xml` | 464 | `aeb85ad0ed8b50d9…` |
| `res/anim/slide_in_left.xml` | 352 | `1decd9ea41d80a75…` |
| `res/anim/slide_in_right.xml` | 352 | `938aa6c4df45f606…` |
| `res/anim/slide_in_up.xml` | 352 | `a9ab18b722f1f4c1…` |
| `res/anim/slide_out_bottom.xml` | 548 | `bc052542150374c0…` |
| `res/anim/slide_out_down.xml` | 352 | `f1ba9455d4c7332f…` |
| `res/anim/slide_out_left.xml` | 352 | `1be3e08d8b279b28…` |
| `res/anim/slide_out_right.xml` | 352 | `ae54e8b3d486abab…` |
| `res/anim/slide_out_to_bottom.xml` | 464 | `6b51c4f9f5ce8313…` |
| `res/anim/slide_up.xml` | 464 | `cc1929617c63581e…` |
| `res/anim/slide_up_animation.xml` | 464 | `c27e9a5b5b410fd6…` |
| `res/anim/translate_fragment.xml` | 352 | `9678dc21dc011328…` |
| `res/anim/translate_fragment_out.xml` | 352 | `839de6ae5ac8b108…` |
| `res/anim/translate_frg_close.xml` | 352 | `2fc559f9a0f4db03…` |
| `res/anim/translate_frg_return.xml` | 352 | `5e0f2d4b930d360a…` |
| `res/animator-v21/design_appbar_state_list_animator.xml` | 1216 | `1ad79ff2cfe69792…` |
| `res/animator/design_fab_hide_motion_spec.xml` | 796 | `98899e8eb3d79ada…` |
| `res/animator/design_fab_show_motion_spec.xml` | 796 | `14d43972b77f82b9…` |
| `res/animator/fragment_close_enter.xml` | 1128 | `918f9b4eef3d2d56…` |
| `res/animator/fragment_close_exit.xml` | 1128 | `4fd6d07347869314…` |
| `res/animator/fragment_fade_enter.xml` | 452 | `0b51013fc0f5e916…` |
| `res/animator/fragment_fade_exit.xml` | 452 | `440c234ca8a09188…` |
| `res/animator/fragment_open_enter.xml` | 1128 | `f6c44715b067e493…` |
| `res/animator/fragment_open_exit.xml` | 1128 | `49f0ab3a3ade0fd2…` |
| `res/animator/mtrl_btn_state_list_anim.xml` | 2664 | `20207258f1aae2d3…` |
| `res/animator/mtrl_btn_unelevated_state_list_anim.xml` | 120 | `f6828625db23f10e…` |
| `res/animator/mtrl_card_state_list_anim.xml` | 1208 | `98ac3e9f98b6d855…` |
| `res/animator/mtrl_chip_state_list_anim.xml` | 1072 | `ca8ea6c1667d1ea1…` |
| `res/animator/mtrl_extended_fab_state_list_animator.xml` | 2724 | `985cde13b96e963d…` |
| `res/animator/mtrl_fab_hide_motion_spec.xml` | 796 | `011d1738ba3a162f…` |
| `res/animator/mtrl_fab_show_motion_spec.xml` | 796 | `5c37392039c9c970…` |
| `res/animator/mtrl_fab_transformation_sheet_collapse_spec.xml` | 1888 | `207d4ff2e3edc66f…` |
| `res/animator/mtrl_fab_transformation_sheet_expand_spec.xml` | 1888 | `69f94c4561fa0bc5…` |
| `res/color-night-v8/material_timepicker_button_stroke.xml` | 376 | `b4b4901c8c338155…` |
| `res/color-night-v8/material_timepicker_clockface.xml` | 376 | `6501cab5dcc853fa…` |
| `res/color-night-v8/material_timepicker_modebutton_tint.xml` | 340 | `4410406e822bf6f6…` |
| `res/color-v23/abc_color_highlight_material.xml` | 544 | `507f96c5416f8f90…` |
| `res/color-v23/abc_tint_btn_checkable.xml` | 624 | `900b4dd8eba0177b…` |
| `res/color-v23/abc_tint_default.xml` | 1120 | `6519e515695e841d…` |
| `res/color-v23/abc_tint_edittext.xml` | 668 | `cdd3d14ed1814839…` |
| `res/color-v23/abc_tint_seek_thumb.xml` | 500 | `6b9f2893e8658535…` |
| `res/color-v23/abc_tint_spinner.xml` | 668 | `cdd3d14ed1814839…` |
| `res/color-v23/abc_tint_switch_track.xml` | 664 | `a261258d115dfb9b…` |
| `res/color/abc_background_cache_hint_selector_material_dark.xml` | 468 | `fcce0e9d80ed5246…` |
| `res/color/abc_background_cache_hint_selector_material_light.xml` | 468 | `9828f42be4caffb3…` |
| `res/color/abc_hint_foreground_material_dark.xml` | 564 | `dd47d1beaad3e3bb…` |
| `res/color/abc_hint_foreground_material_light.xml` | 564 | `565521a089621819…` |
| `res/color/abc_primary_text_disable_only_material_dark.xml` | 464 | `5a8489bdb9c863b5…` |
| `res/color/abc_primary_text_disable_only_material_light.xml` | 464 | `547d8a86b464683a…` |
| `res/color/abc_primary_text_material_dark.xml` | 464 | `30d92ebb5b7f0a04…` |
| `res/color/abc_primary_text_material_light.xml` | 464 | `b8556fe1f2bf4135…` |
| `res/color/abc_search_url_text.xml` | 588 | `180aebed8823b259…` |
| `res/color/abc_secondary_text_material_dark.xml` | 464 | `0721e197097231d2…` |
| `res/color/abc_secondary_text_material_light.xml` | 464 | `1fa8bb2ac5b388cd…` |
| `res/color/design_box_stroke_color.xml` | 712 | `9a298e345259dac7…` |
| `res/color/design_error.xml` | 464 | `43607f1110f4f92b…` |
| `res/color/design_icon_tint.xml` | 376 | `c0996c3ed11e7934…` |
| `res/color/glance_default_radio_button.xml` | 464 | `166a942cb4a14f7b…` |
| `res/color/glance_switch_off_ambient_shadow.xml` | 868 | `7afdf7db243caa52…` |
| `res/color/glance_switch_off_key_shadow.xml` | 868 | `03cc0b7dc9582463…` |
| `res/color/glance_switch_on_ambient_shadow.xml` | 868 | `983c86ac630879fd…` |
| `res/color/glance_switch_on_key_shadow.xml` | 868 | `a3f2c1b93070e470…` |
| `res/color/griver_ui_button_text_for_text.xml` | 628 | `885c450505d01710…` |
| `res/color/griver_ui_button_text_for_text_sub.xml` | 484 | `8f41a68c60c98f9c…` |
| `res/color/griver_ui_button_textcolor_black.xml` | 688 | `4479df1b219c64e0…` |
| `res/color/griver_ui_button_textcolor_blue.xml` | 688 | `4e8a3120896a2902…` |
| `res/color/griver_ui_button_textcolor_white.xml` | 688 | `2a11a1794bd970db…` |
| `res/color/material_on_surface_disabled.xml` | 376 | `23310417ee22e1c2…` |
| `res/color/material_on_surface_emphasis_high_type.xml` | 376 | `cc5902720208761f…` |
| `res/color/material_on_surface_emphasis_medium.xml` | 376 | `2b67b914e91cb83d…` |
| `res/color/material_on_surface_stroke.xml` | 376 | `df2eda2ba844af02…` |
| `res/color/material_slider_active_tick_marks_color.xml` | 520 | `8af17551f098ea27…` |
| `res/color/material_slider_active_track_color.xml` | 500 | `50213b68b6062b2a…` |
| `res/color/material_slider_halo_color.xml` | 500 | `d553110e662aab1c…` |
| `res/color/material_slider_inactive_tick_marks_color.xml` | 520 | `791efd57f6df021c…` |
| `res/color/material_slider_inactive_track_color.xml` | 520 | `6e462878aacdb60c…` |
| `res/color/material_slider_thumb_color.xml` | 500 | `4f8019dc674917d6…` |
| `res/color/material_timepicker_button_background.xml` | 500 | `4d3899b783c9f8cd…` |
| `res/color/material_timepicker_button_stroke.xml` | 376 | `0628d6979b72e91a…` |
| `res/color/material_timepicker_clock_text_color.xml` | 464 | `428668b9dd019507…` |
| `res/color/material_timepicker_clockface.xml` | 376 | `35d754fa570f0048…` |
| `res/color/material_timepicker_modebutton_tint.xml` | 376 | `02f47be8c2e0be8b…` |
| `res/color/mtrl_btn_bg_color_selector.xml` | 500 | `b6d752205ed1434c…` |
| `res/color/mtrl_btn_ripple_color.xml` | 948 | `9c4452bed3c30d66…` |
| `res/color/mtrl_btn_stroke_color_selector.xml` | 520 | `4940039af2669cb7…` |
| `res/color/mtrl_btn_text_btn_bg_color_selector.xml` | 520 | `eaaacfd872261a7d…` |
| `res/color/mtrl_btn_text_btn_ripple_color.xml` | 948 | `f396f71faed460a8…` |
| `res/color/mtrl_btn_text_color_selector.xml` | 500 | `e232c4af00033305…` |
| `res/color/mtrl_calendar_item_stroke_color.xml` | 808 | `f1f01c67dfa1dfc0…` |
| `res/color/mtrl_calendar_selected_range.xml` | 376 | `4810d64511d53d68…` |
| `res/color/mtrl_card_view_foreground.xml` | 788 | `288b14f1b3659769…` |
| `res/color/mtrl_card_view_ripple.xml` | 768 | `b5fb0c55bafa5765…` |
| `res/color/mtrl_chip_background_color.xml` | 848 | `442a10d7f5d989a1…` |
| `res/color/mtrl_chip_close_icon_tint.xml` | 1092 | `3be9bc50b92e4e07…` |
| `res/color/mtrl_chip_surface_color.xml` | 340 | `56a991c65f64338e…` |
| `res/color/mtrl_chip_text_color.xml` | 520 | `6439df9a32a1ad4b…` |
| `res/color/mtrl_choice_chip_background_color.xml` | 848 | `15645f1c743f732f…` |
| `res/color/mtrl_choice_chip_ripple_color.xml` | 948 | `b1a77707d2f59309…` |
| `res/color/mtrl_choice_chip_text_color.xml` | 808 | `79e4e7c278e8a4f4…` |
| `res/color/mtrl_error.xml` | 464 | `c90c4daf5439e2e8…` |
| `res/color/mtrl_fab_bg_color_selector.xml` | 500 | `3c9ee66da1c5d0fc…` |
| `res/color/mtrl_fab_icon_text_color_selector.xml` | 500 | `deb0c029e5e0ad14…` |
| `res/color/mtrl_fab_ripple_color.xml` | 948 | `02fb4d0081413fff…` |
| `res/color/mtrl_filled_background_color.xml` | 808 | `1a6af05de4ed30b2…` |
| `res/color/mtrl_filled_icon_tint.xml` | 644 | `7955754d8df17f77…` |
| `res/color/mtrl_filled_stroke_color.xml` | 788 | `92b3251b94dccadb…` |
| `res/color/mtrl_indicator_text_color.xml` | 520 | `500d9f5f83d837ed…` |
| `res/color/mtrl_navigation_bar_item_tint.xml` | 520 | `9acb03f7da76d20b…` |
| `res/color/mtrl_navigation_bar_ripple_color.xml` | 1672 | `9062741be1995c93…` |
| `res/color/mtrl_navigation_item_background_color.xml` | 644 | `dd364d811f497755…` |
| `res/color/mtrl_navigation_item_icon_tint.xml` | 624 | `89c36273865fc0e7…` |
| `res/color/mtrl_navigation_item_text_color.xml` | 624 | `89c36273865fc0e7…` |
| `res/color/mtrl_on_primary_text_btn_text_color_selector.xml` | 500 | `e5c67a43c6da57fb…` |
| `res/color/mtrl_on_surface_ripple_color.xml` | 808 | `e1060d69ba5789ed…` |
| `res/color/mtrl_outlined_icon_tint.xml` | 644 | `40b07059f24e3535…` |
| `res/color/mtrl_outlined_stroke_color.xml` | 788 | `27252f21068153e5…` |
| `res/color/mtrl_popupmenu_overlay_color.xml` | 376 | `d800bc4c942929b6…` |
| `res/color/mtrl_tabs_icon_color_selector.xml` | 500 | `79aca0b58187735a…` |
| `res/color/mtrl_tabs_legacy_text_color_selector.xml` | 464 | `38f276f25dd94300…` |
| `res/color/mtrl_tabs_ripple_color.xml` | 1672 | `9062741be1995c93…` |
| `res/color/mtrl_text_btn_text_color_selector.xml` | 888 | `046285d837d01f71…` |
| `res/color/switch_thumb_material_dark.xml` | 464 | `3e2b1da51e5be94d…` |
| `res/color/switch_thumb_material_light.xml` | 464 | `32d0baf2fe6a831a…` |
| `res/color/txt_blue_selector.xml` | 836 | `00b30c20912c0862…` |
| `res/color/txt_default_selector.xml` | 836 | `531b24329098038a…` |
| `res/drawable-anydpi-v24/$bg_switch_backside__0.xml` | 888 | `8e196c69a226555e…` |
| `res/drawable-hdpi-v4/apdu_service_banner.png` | 3115 | `8eb3039db180931f…` |
| `res/drawable-hdpi-v4/bg_capture_border.png` | 1061 | `4c19ddc1693e15f6…` |
| `res/drawable-hdpi-v4/griver_ui_point_large.9.png` | 996 | `eb71410624dd338a…` |
| `res/drawable-hdpi-v4/griver_ui_white_title_bar_close_btn.webp` | 292 | `1e23d86f64070176…` |
| `res/drawable-hdpi-v4/transparent.png` | 68 | `0464f45b495a5aac…` |
| `res/drawable-mdpi-v4/abc_ab_share_pack_mtrl_alpha.9.png` | 267 | `f94e2129180c0ba6…` |
| `res/drawable-mdpi-v4/abc_btn_switch_to_on_mtrl_00001.9.png` | 754 | `562cba1ad1b9abd5…` |
| `res/drawable-mdpi-v4/abc_btn_switch_to_on_mtrl_00012.9.png` | 825 | `b52228a35520e2d7…` |
| `res/drawable-mdpi-v4/abc_ic_commit_search_api_mtrl_alpha.png` | 173 | `52549a8d0b6c35fa…` |
| `res/drawable-mdpi-v4/abc_list_divider_mtrl_alpha.9.png` | 167 | `378694706fb7256d…` |
| `res/drawable-mdpi-v4/abc_list_focused_holo.9.png` | 222 | `8436624220fba246…` |
| `res/drawable-mdpi-v4/abc_list_longpressed_holo.9.png` | 211 | `dbc0af61f440bb3f…` |
| `res/drawable-mdpi-v4/abc_list_pressed_holo_dark.9.png` | 207 | `5d4161bf9482b5d2…` |
| `res/drawable-mdpi-v4/abc_list_pressed_holo_light.9.png` | 207 | `e98f192f013a9226…` |
| `res/drawable-mdpi-v4/abc_list_selector_disabled_holo_dark.9.png` | 217 | `a15171ead4b1cd9b…` |
| `res/drawable-mdpi-v4/abc_list_selector_disabled_holo_light.9.png` | 217 | `7c45e46e405f5b4a…` |
| `res/drawable-mdpi-v4/abc_menu_hardkey_panel_mtrl_mult.9.png` | 541 | `c7954e2bfbd01e5b…` |
| `res/drawable-mdpi-v4/abc_switch_track_mtrl_alpha.9.png` | 395 | `62477adc74d4e683…` |
| `res/drawable-mdpi-v4/abc_textfield_activated_mtrl_alpha.9.png` | 181 | `fa50162bdb3501c6…` |
| `res/drawable-mdpi-v4/abc_textfield_default_mtrl_alpha.9.png` | 178 | `9fd37c82f87df004…` |
| `res/drawable-mdpi-v4/abc_textfield_search_activated_mtrl_alpha.9.png` | 178 | `4bc8f4d13d2acccc…` |
| `res/drawable-mdpi-v4/abc_textfield_search_default_mtrl_alpha.9.png` | 178 | `b800fac7d2f0a75b…` |
| `res/drawable-night-v8/background_icon.xml` | 884 | `6a00534c1878091c…` |
| `res/drawable-night-v8/logo_zalopay_color.xml` | 2504 | `66857d5e56ae6c6a…` |
| `res/drawable-night-v8/preview_person_qr_app_widget.xml` | 37476 | `05b74eb7ccb2d9ee…` |
| `res/drawable-night-v8/preview_standard_app_widget.xml` | 50872 | `748738b693fa81e3…` |
| `res/drawable-v21/abc_action_bar_item_background_material.xml` | 264 | `5c4046ca717bff82…` |
| `res/drawable-v21/abc_btn_colored_material.xml` | 1716 | `684cfa65adef2613…` |
| `res/drawable-v21/abc_dialog_material_background.xml` | 716 | `ff564e71f35b8e27…` |
| `res/drawable-v21/abc_edit_text_material.xml` | 1172 | `4df8e8079e8325d5…` |
| `res/drawable-v21/abc_list_divider_material.xml` | 516 | `976c8b16e6a0728e…` |
| `res/drawable-v21/cbb_bottom_selector.xml` | 752 | `737f1410bf9bb535…` |
| `res/drawable-v21/cbb_full_selector.xml` | 752 | `012d6174f3290f15…` |
| `res/drawable-v21/cbb_noborder_selector.xml` | 804 | `fb6f46306cb4e59d…` |
| `res/drawable-v21/material_cursor_drawable.xml` | 588 | `f7112b07608d6b2d…` |
| `res/drawable-v23/abc_control_background_material.xml` | 304 | `940fa98d3b37415e…` |
| `res/drawable-v23/mtrl_popupmenu_background_dark.xml` | 1228 | `8f245b9bce94beb4…` |
| `res/drawable-v31/glance_error_layout_background.xml` | 484 | `c5f92450eb1945cd…` |
| `res/drawable-v31/glance_loading_layout_background.xml` | 484 | `ffb31fc1d00e9745…` |
| `res/drawable-watch-v20/abc_dialog_material_background.xml` | 372 | `49f4ee033a368be6…` |
| `res/drawable-xhdpi-v4/acplugin_back_icon.png` | 803 | `47dd1b1b33378d2c…` |
| `res/drawable-xhdpi-v4/acplugin_clear_icon.png` | 587 | `cd80c325fef5e2a1…` |
| `res/drawable-xhdpi-v4/acplugin_error_icon.png` | 5922 | `2575d2bef978e488…` |
| `res/drawable-xhdpi-v4/ic_taxib.png` | 3771 | `505c98bf85a4f9ad…` |
| `res/drawable-xhdpi-v4/iv_cancel_booking.png` | 8120 | `28299865f1cd0901…` |
| `res/drawable-xxhdpi-v4/ariver_tabbar_badge.9.png` | 996 | `6849d8d9cf46476b…` |
| `res/drawable-xxhdpi-v4/griver_image_album_selected.webp` | 282 | `6065c1ebcc3fd98d…` |
| `res/drawable-xxhdpi-v4/griver_image_bg_capture_tips.9.png` | 1041 | `4063b8719b828049…` |
| `res/drawable-xxhdpi-v4/griver_image_cancel_download.webp` | 180 | `47ee07a3cb54a469…` |
| `res/drawable-xxhdpi-v4/griver_image_default_photo.webp` | 786 | `c6fb33876cbe555b…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_camera_normal.webp` | 812 | `3d3d8ebd71db0788…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_camera_prepare.webp` | 480 | `a02b3f3a694ef2a5…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_change_camera.webp` | 414 | `749e3cd379cbdf28…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_download_clicked.png` | 550 | `33289dd56cabceda…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_download_normal.webp` | 466 | `9a9e6b7e15eedcfe…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_flash_off.webp` | 588 | `9044e8a0d7131d08…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_flash_on.webp` | 340 | `ba35a830ff3dc3fd…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_grid_clicked.webp` | 326 | `2cfeed39b8c131e1…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_grid_normal.webp` | 324 | `cf481f1a4259fcc7…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_loading_white.webp` | 764 | `76f69cf321878e1c…` |
| `res/drawable-xxhdpi-v4/griver_image_ic_take_picture.webp` | 3666 | `4744b94a2d06c891…` |
| `res/drawable-xxhdpi-v4/griver_image_load_fail.webp` | 990 | `10a9b8465478863b…` |
| `res/drawable-xxhdpi-v4/griver_image_origin_selected.webp` | 372 | `3d33822ebc963ccc…` |
| `res/drawable-xxhdpi-v4/griver_image_origin_unselect.webp` | 328 | `1a41d93727c631e3…` |
| `res/drawable-xxhdpi-v4/griver_image_photo_selected.webp` | 292 | `232a424efe4746de…` |
| `res/drawable-xxhdpi-v4/griver_image_photo_unselect.webp` | 60 | `8d66932d6b0a6598…` |
| `res/drawable-xxhdpi-v4/griver_image_play.webp` | 686 | `cde1d555c56a09c8…` |
| `res/drawable-xxhdpi-v4/griver_image_show_origin_normal.9.png` | 533 | `7ef59d20e17fa76b…` |
| `res/drawable-xxhdpi-v4/griver_image_show_origin_pressed.9.png` | 557 | `38d96c9f13ac6dbe…` |
| `res/drawable-xxhdpi-v4/griver_ui_big_progress_bar.png` | 572 | `b3cb67d7a1b81b58…` |
| `res/drawable-xxhdpi-v4/griver_ui_nav_complain.webp` | 830 | `3391a8b80ee4558b…` |
| `res/drawable-xxhdpi-v4/griver_ui_nav_default.webp` | 440 | `1d7a7a25308dc28e…` |
| `res/drawable-xxhdpi-v4/griver_ui_nav_menu_normal.9.png` | 373 | `a86547d83528c38d…` |
| `res/drawable-xxhdpi-v4/griver_ui_nav_menu_pressed.9.png` | 505 | `e4e5730f254bb54c…` |
| `res/drawable-xxhdpi-v4/griver_ui_nav_share_friend.webp` | 200 | `0783be9de1e040fa…` |
| `res/drawable-xxhdpi-v4/griver_ui_point_small.webp` | 180 | `46cc72356bf63056…` |
| `res/drawable-xxhdpi-v4/griver_ui_popmenu_shadow_bg.9.png` | 488 | `ed6c8c3e8218312d…` |
| `res/drawable-xxhdpi-v4/griver_ui_shock_point_board_large.9.png` | 1447 | `b88bf5342d2446d1…` |
| `res/drawable-xxhdpi-v4/griver_ui_shock_point_large.9.png` | 996 | `d13fb69f13b51843…` |
| `res/drawable-xxhdpi-v4/griver_ui_shock_point_more.webp` | 314 | `74732e1c93a57d7e…` |
| `res/drawable-xxhdpi-v4/griver_ui_shock_point_small.webp` | 180 | `46cc72356bf63056…` |
| `res/drawable-xxhdpi-v4/griver_ui_title_bar_progress_bg.webp` | 1282 | `929cfac8edd360e3…` |
| `res/drawable-xxhdpi-v4/griver_ui_title_bar_progress_bg_gold.webp` | 1144 | `4021bbd4eb1bc7f4…` |
| `res/drawable-xxhdpi-v4/griver_ui_title_bar_progress_bg_white.webp` | 986 | `2ea7cdf03d268fd9…` |
| `res/drawable-xxhdpi-v4/h5_title_bar_back_btn.png` | 241 | `86e266a9f1d87bef…` |
| `res/drawable-xxhdpi-v4/h5_title_bar_back_btn_press.png` | 229 | `f6a0a173a0833aac…` |
| `res/drawable-xxhdpi-v4/h5_title_bar_close_btn.png` | 553 | `9d035171cdcbc6d8…` |
| `res/drawable-xxhdpi-v4/h5_title_bar_progress_bg.png` | 1723 | `987fffb14f75c37d…` |
| `res/drawable-xxhdpi-v4/ic_check_front_back_error.png` | 11910 | `9dcede98fd54b89c…` |
| `res/drawable-xxhdpi-v4/ic_check_front_back_success.png` | 10328 | `9894f093904c0dd8…` |
| `res/drawable-xxhdpi-v4/img_check_front_back_card.png` | 117834 | `6364e2b15888ba17…` |
| `res/drawable-xxhdpi-v4/img_check_front_back_magnifier.png` | 62225 | `cfd6c435cc0283ad…` |
| `res/drawable-xxhdpi-v4/img_profile_v2_privacy_hub.png` | 38275 | `518974b32586bffb…` |
| `res/drawable-xxhdpi-v4/img_register_consent_shield.png` | 38275 | `518974b32586bffb…` |
| `res/drawable-xxxhdpi-v4/ic_triangle.png` | 657 | `a7de4227846c29c7…` |
| `res/drawable/$_20_px_select__0.xml` | 804 | `8ee0de9d03ac3bf5…` |
| `res/drawable/$avd_hide_password__0.xml` | 1176 | `3d0c29c33d70e0d5…` |
| `res/drawable/$avd_hide_password__1.xml` | 592 | `6d3aab9ecaf0b38b…` |
| `res/drawable/$avd_hide_password__2.xml` | 556 | `4c0aa60ffc7c5a91…` |
| `res/drawable/$avd_show_password__0.xml` | 1136 | `263205f469ee7d24…` |
| `res/drawable/$avd_show_password__1.xml` | 592 | `eb66598ffb9436b4…` |
| `res/drawable/$avd_show_password__2.xml` | 556 | `24e0b38d213b4c99…` |
| `res/drawable/$bank_list__0.xml` | 888 | `24445f054244d7eb…` |
| `res/drawable/$bank_list__1.xml` | 888 | `ca425ed977625e6c…` |
| `res/drawable/$bank_list__2.xml` | 788 | `6759d6dce65cfa77…` |
| `res/drawable/$bank_list__3.xml` | 788 | `01ad434d7bc3e8cf…` |
| `res/drawable/$bank_list__4.xml` | 788 | `5db1a3bbc57c9309…` |
| `res/drawable/$bell__0.xml` | 864 | `43ab7f4164e608ce…` |
| `res/drawable/$bell__1.xml` | 788 | `05c09837eb06bf07…` |
| `res/drawable/$bell__2.xml` | 788 | `df12b1574ae2503d…` |
| `res/drawable/$bell__3.xml` | 788 | `ef300c639064b393…` |
| `res/drawable/$bell__4.xml` | 788 | `80ee94036aba30a5…` |
| `res/drawable/$bg_cashier_campaign__0.xml` | 988 | `ab46a35970213327…` |
| `res/drawable/$bg_cashier_campaign__1.xml` | 764 | `89655ff32886646a…` |
| `res/drawable/$bg_cashier_campaign__2.xml` | 788 | `fd96b30690012c24…` |
| `res/drawable/$bg_cashier_campaign__3.xml` | 764 | `a31a94dede0147fc…` |
| `res/drawable/$bg_empty_linked_bank__0.xml` | 764 | `5858a0d950d58dd4…` |
| `res/drawable/$bg_empty_linked_bank__1.xml` | 764 | `f8e4a89d51397389…` |
| `res/drawable/$bg_empty_linked_bank__2.xml` | 788 | `6c4af87483683fee…` |
| `res/drawable/$bg_empty_linked_bank__3.xml` | 788 | `c5eb345b575c7e0a…` |
| `res/drawable/$bg_empty_linked_bank__4.xml` | 788 | `252a4b67ee46aedc…` |
| `res/drawable/$bg_empty_linked_bank__5.xml` | 788 | `682383e57d4de1f1…` |
| `res/drawable/$bg_empty_linked_bank__6.xml` | 788 | `4766cd0062d87e4f…` |
| `res/drawable/$bg_empty_linked_bank__7.xml` | 764 | `29b2adf6c96ff6c5…` |
| `res/drawable/$bg_empty_linked_bank__8.xml` | 788 | `90da7130e5aa1096…` |
| `res/drawable/$bg_empty_linked_bank__9.xml` | 788 | `e0e7d67cd2fd349f…` |
| `res/drawable/$blindbox__0.xml` | 788 | `97a8c9e18665a840…` |
| `res/drawable/$glance_switch_thumb_off_to_on__0.xml` | 556 | `f72097420fe009ac…` |
| `res/drawable/$glance_switch_thumb_on_to_off__0.xml` | 556 | `7235c917e9a38a0f…` |
| `res/drawable/$graphic_onboarding_sdsl__0.xml` | 788 | `92dc93603df5606d…` |
| `res/drawable/$graphic_onboarding_sdsl__1.xml` | 788 | `483ae5fee4a8af19…` |
| `res/drawable/$header_background__0.xml` | 756 | `3df1529ca3ec49bd…` |
| `res/drawable/$header_background__vcb__0.xml` | 756 | `8de632cebf2e93e3…` |
| `res/drawable/$ic16_general_coincolor__0.xml` | 792 | `cc08a0fc395d1c80…` |
| `res/drawable/$ic16_general_coincolor__1.xml` | 792 | `500e301746db1c47…` |
| `res/drawable/$ic16_general_coincolor__2.xml` | 792 | `49c7edc937d6132d…` |
| `res/drawable/$ic16_general_coincolor__3.xml` | 792 | `cdc14647cd974556…` |
| `res/drawable/$ic16_general_coincolor__4.xml` | 792 | `3498ff35233261fe…` |
| `res/drawable/$ic16_general_coincolor__5.xml` | 892 | `1bfc090f9e6b500f…` |
| `res/drawable/$ic36_service_other_tktl__0.xml` | 792 | `6bc307bd207fb939…` |
| `res/drawable/$ic36_service_other_tktl__1.xml` | 792 | `de26e2b2b3ff8cef…` |
| `res/drawable/$ic_absolute_safety__0.xml` | 764 | `729f9d9ca829b06b…` |
| `res/drawable/$ic_absolute_safety__1.xml` | 764 | `51f17596c91adbc7…` |
| `res/drawable/$ic_absolute_safety__2.xml` | 764 | `a4b03845a9a10e67…` |
| `res/drawable/$ic_absolute_safety__3.xml` | 764 | `85004b333d2be340…` |
| `res/drawable/$ic_absolute_safety__4.xml` | 764 | `3a0deb2c813eb431…` |
| `res/drawable/$ic_absolute_safety__5.xml` | 764 | `8b12bddd3befa4f2…` |
| `res/drawable/$ic_absolute_safety__6.xml` | 764 | `9430bf7dab34f17d…` |
| `res/drawable/$ic_absolute_safety__7.xml` | 764 | `e894846c32e1be09…` |
| `res/drawable/$ic_account_hdb__0.xml` | 788 | `87865a8b010b438a…` |
| `res/drawable/$ic_account_hdb__1.xml` | 788 | `ba40edf92aa2db4e…` |
| `res/drawable/$ic_account_hdb__10.xml` | 788 | `9fbb58bb4b347737…` |
| `res/drawable/$ic_account_hdb__11.xml` | 788 | `70f1cc65337ffb6b…` |
| `res/drawable/$ic_account_hdb__12.xml` | 788 | `9a87757f29162dd6…` |
| `res/drawable/$ic_account_hdb__13.xml` | 788 | `d28ee386608cd724…` |
| `res/drawable/$ic_account_hdb__14.xml` | 788 | `57a3ff616bd5d73b…` |
| `res/drawable/$ic_account_hdb__15.xml` | 788 | `2706443eee390894…` |
| `res/drawable/$ic_account_hdb__16.xml` | 788 | `2bd9059fe7a4f6ad…` |
| `res/drawable/$ic_account_hdb__17.xml` | 788 | `38c5dde2ac50b426…` |
| `res/drawable/$ic_account_hdb__18.xml` | 788 | `eeb93e13b6e30e83…` |
| `res/drawable/$ic_account_hdb__19.xml` | 788 | `557975a6c66ab337…` |
| `res/drawable/$ic_account_hdb__2.xml` | 788 | `266b506dae9e81d1…` |
| `res/drawable/$ic_account_hdb__20.xml` | 788 | `bb16c4f715239e79…` |
| `res/drawable/$ic_account_hdb__21.xml` | 788 | `12469a95c2f0cae3…` |
| `res/drawable/$ic_account_hdb__22.xml` | 788 | `fe26c4011c9c6d7f…` |
| `res/drawable/$ic_account_hdb__23.xml` | 788 | `cc3b99930a095cb5…` |
| `res/drawable/$ic_account_hdb__24.xml` | 788 | `46d8a5db26f1a68f…` |
| `res/drawable/$ic_account_hdb__25.xml` | 788 | `d4b3b0a20087fcb4…` |
| `res/drawable/$ic_account_hdb__26.xml` | 788 | `a19e83390f20c542…` |
| `res/drawable/$ic_account_hdb__27.xml` | 788 | `8c37a9d598d278ce…` |
| `res/drawable/$ic_account_hdb__28.xml` | 788 | `c9f2d17c7db5bedd…` |
| `res/drawable/$ic_account_hdb__29.xml` | 788 | `6f7bc070d6d332fb…` |
| `res/drawable/$ic_account_hdb__3.xml` | 788 | `bfa790f49467febf…` |
| `res/drawable/$ic_account_hdb__30.xml` | 788 | `731c73a1a611f91b…` |
| `res/drawable/$ic_account_hdb__31.xml` | 788 | `e92a70b5f7d070e2…` |
| `res/drawable/$ic_account_hdb__32.xml` | 788 | `2e228d247e77342c…` |
| `res/drawable/$ic_account_hdb__4.xml` | 788 | `977fb53fc8b5d3e7…` |
| `res/drawable/$ic_account_hdb__5.xml` | 788 | `375b88130d9b429a…` |
| `res/drawable/$ic_account_hdb__6.xml` | 788 | `14990b794eb0a398…` |
| `res/drawable/$ic_account_hdb__7.xml` | 788 | `5685a2dcd900fda6…` |
| `res/drawable/$ic_account_hdb__8.xml` | 788 | `05c12be5accb1d21…` |
| `res/drawable/$ic_account_hdb__9.xml` | 788 | `972d834844b2100d…` |
| `res/drawable/$ic_account_vcb__0.xml` | 888 | `ae98fffbcb3c1aba…` |
| `res/drawable/$ic_account_vcb__1.xml` | 888 | `3467080481dd2e77…` |
| `res/drawable/$ic_ai__0.xml` | 788 | `2adbeb350398a986…` |
| `res/drawable/$ic_ai__1.xml` | 788 | `56835aeb9b237cc8…` |
| `res/drawable/$ic_ai__2.xml` | 788 | `b0fe689a3f9a342b…` |
| `res/drawable/$ic_alert__0.xml` | 788 | `d03f9664eaadea96…` |
| `res/drawable/$ic_alert__1.xml` | 1188 | `b01beaa5e0c7941a…` |
| `res/drawable/$ic_alert__2.xml` | 1188 | `1b4633f87e4546f6…` |
| `res/drawable/$ic_alert__3.xml` | 1088 | `7dc95b1e5e876ea5…` |
| `res/drawable/$ic_alert__4.xml` | 1088 | `07bb12d167926de7…` |
| `res/drawable/$ic_alert__5.xml` | 988 | `9cbca96a124e5634…` |
| `res/drawable/$ic_alert__6.xml` | 788 | `cd49ec4a9891d512…` |
| `res/drawable/$ic_alert__7.xml` | 1488 | `3632b17ff757b6de…` |
| `res/drawable/$ic_alert__8.xml` | 788 | `ebfa415132fb9d53…` |
| `res/drawable/$ic_alert__9.xml` | 788 | `5037f6c2722f5d93…` |
| `res/drawable/$ic_annoucement__0.xml` | 788 | `47feeda506ee977f…` |
| `res/drawable/$ic_annoucement__1.xml` | 788 | `ef9c3849929266a1…` |
| `res/drawable/$ic_annoucement__2.xml` | 788 | `223bb360e8a6b28c…` |
| `res/drawable/$ic_annoucement__3.xml` | 1488 | `befb166a1d741082…` |
| `res/drawable/$ic_annoucement__4.xml` | 788 | `ec0548a1ac3e5841…` |
| `res/drawable/$ic_annoucement__5.xml` | 788 | `7573483ad309a560…` |
| `res/drawable/$ic_app_748_lazada__0.xml` | 788 | `fe8605918b5de9be…` |
| `res/drawable/$ic_app_748_lazada__1.xml` | 1288 | `3db115c2e00a80ef…` |
| `res/drawable/$ic_app_773_covid19__0.xml` | 788 | `a36d5bf76b33a898…` |
| `res/drawable/$ic_app_gift__0.xml` | 828 | `f1f2a2a1e177b65b…` |
| `res/drawable/$ic_app_gift__1.xml` | 828 | `3122ccb0581220ad…` |
| `res/drawable/$ic_app_gift__10.xml` | 788 | `3a4bd6ab18f5a25a…` |
| `res/drawable/$ic_app_gift__11.xml` | 828 | `7aaf368f7679d049…` |
| `res/drawable/$ic_app_gift__12.xml` | 788 | `b25bd78cb117c279…` |
| `res/drawable/$ic_app_gift__13.xml` | 828 | `119552912d99c117…` |
| `res/drawable/$ic_app_gift__14.xml` | 788 | `9a63105f09ae0840…` |
| `res/drawable/$ic_app_gift__15.xml` | 828 | `ec787050ab474f53…` |
| `res/drawable/$ic_app_gift__16.xml` | 788 | `054ba2c4af5e6d9e…` |
| `res/drawable/$ic_app_gift__17.xml` | 828 | `88f701a9d8b1100c…` |
| `res/drawable/$ic_app_gift__2.xml` | 788 | `22df39cbfdcbf682…` |
| `res/drawable/$ic_app_gift__3.xml` | 828 | `6a38316cfcaf48d8…` |
| `res/drawable/$ic_app_gift__4.xml` | 788 | `d3fd15cbd7c36c05…` |
| `res/drawable/$ic_app_gift__5.xml` | 828 | `df983efe31c45a30…` |
| `res/drawable/$ic_app_gift__6.xml` | 788 | `088b03d37199778a…` |
| `res/drawable/$ic_app_gift__7.xml` | 828 | `eaa1bf1a58f7842c…` |
| `res/drawable/$ic_app_gift__8.xml` | 788 | `2e2994e23cfbe7d4…` |
| `res/drawable/$ic_app_gift__9.xml` | 828 | `ef0c9bdeb0c06406…` |
| `res/drawable/$ic_app_googleplay__0.xml` | 1188 | `4da166e884406296…` |
| `res/drawable/$ic_app_googleplay__1.xml` | 988 | `d9d9f4b61415b127…` |
| `res/drawable/$ic_app_googleplay__2.xml` | 788 | `16e6c5feec977f7f…` |
| `res/drawable/$ic_app_googleplay__3.xml` | 1088 | `75028e5b54bc10bf…` |
| `res/drawable/$ic_application__0.xml` | 788 | `6bce91ac73510f3e…` |
| `res/drawable/$ic_application__1.xml` | 788 | `0a2e3e143fd010e5…` |
| `res/drawable/$ic_art_work_notification_download_app__0.xml` | 788 | `765c309144dbe70d…` |
| `res/drawable/$ic_art_work_notification_download_app__1.xml` | 788 | `032e4131f228b19f…` |
| `res/drawable/$ic_art_work_notification_download_app__2.xml` | 788 | `bfef8872815cc5ff…` |
| `res/drawable/$ic_art_work_notification_download_app__3.xml` | 788 | `fe642d0264282409…` |
| `res/drawable/$ic_art_work_notification_download_app__4.xml` | 788 | `4a9fe1fe76c0c2eb…` |
| `res/drawable/$ic_art_work_notification_download_app__5.xml` | 788 | `a5aa7de430d7c88e…` |
| `res/drawable/$ic_art_work_notification_download_app__6.xml` | 788 | `dcaa13e38374f7bc…` |
| `res/drawable/$ic_art_work_notification_download_app__7.xml` | 788 | `c08d8b5ebd76c38a…` |
| `res/drawable/$ic_art_work_notification_download_app__8.xml` | 788 | `eca3657d82c97bef…` |
| `res/drawable/$ic_art_work_notification_download_app__9.xml` | 788 | `bf4c749bd1def74c…` |
| `res/drawable/$ic_art_work_notification_kyc_sending__0.xml` | 788 | `ec1cb61c010ce444…` |
| `res/drawable/$ic_art_work_notification_maintenance_service__0.xml` | 788 | `765c309144dbe70d…` |
| `res/drawable/$ic_art_work_notification_maintenance_service__1.xml` | 788 | `032e4131f228b19f…` |
| `res/drawable/$ic_art_work_over_threshold__0.xml` | 788 | `a1ca2f6b3cefdce7…` |
| `res/drawable/$ic_art_work_over_threshold__1.xml` | 788 | `d99931879b4019bc…` |
| `res/drawable/$ic_art_work_over_threshold__2.xml` | 788 | `50ec2567ef9635a5…` |
| `res/drawable/$ic_art_work_over_threshold__3.xml` | 788 | `77e2b905877f3722…` |
| `res/drawable/$ic_bab__0.xml` | 764 | `6bdcd421942d5fe1…` |
| `res/drawable/$ic_bab__1.xml` | 864 | `a09485c461655c57…` |
| `res/drawable/$ic_bab__2.xml` | 764 | `8b42391897cb1d9d…` |
| `res/drawable/$ic_bab__3.xml` | 864 | `11c7a88c6039ff6f…` |
| `res/drawable/$ic_bab__4.xml` | 764 | `01d09bca4d8e916b…` |
| `res/drawable/$ic_bab__5.xml` | 864 | `f87a53e34dcdcae5…` |
| `res/drawable/$ic_bab__6.xml` | 764 | `ded27c21d70dce93…` |
| `res/drawable/$ic_bab__7.xml` | 864 | `0b7a848e8a03efb1…` |
| `res/drawable/$ic_bab__8.xml` | 764 | `2ef9706a390d0ee6…` |
| `res/drawable/$ic_bab__9.xml` | 864 | `a246243ce1a5fc5e…` |
| `res/drawable/$ic_bab_full_brand__0.xml` | 764 | `068056fe3cb93f8a…` |
| `res/drawable/$ic_bab_full_brand__1.xml` | 864 | `70c56b2f7b991476…` |
| `res/drawable/$ic_bab_full_brand__2.xml` | 764 | `652138ac30320a16…` |
| `res/drawable/$ic_bab_full_brand__3.xml` | 864 | `5a8e877b9d36723b…` |
| `res/drawable/$ic_bab_full_brand__4.xml` | 764 | `7627881bbbbbd630…` |
| `res/drawable/$ic_bab_full_brand__5.xml` | 864 | `6119788d4419848f…` |
| `res/drawable/$ic_bab_full_brand__6.xml` | 764 | `f2a411a894f90dd3…` |
| `res/drawable/$ic_bab_full_brand__7.xml` | 864 | `ba136066abc2f026…` |
| `res/drawable/$ic_bab_full_brand__8.xml` | 764 | `bebe9d880fce6e85…` |
| `res/drawable/$ic_bab_full_brand__9.xml` | 864 | `592e43f4518432cb…` |
| `res/drawable/$ic_background_vcb__0.xml` | 756 | `80534542c1ecd0b6…` |
| `res/drawable/$ic_background_vcb__1.xml` | 792 | `402b0cd0581b0a03…` |
| `res/drawable/$ic_background_vcb__2.xml` | 792 | `244f2502973d46f5…` |
| `res/drawable/$ic_background_vcb__3.xml` | 892 | `fc560f38db65be47…` |
| `res/drawable/$ic_background_vcb__4.xml` | 892 | `6247f29e7a7a3b91…` |
| `res/drawable/$ic_banner_ibft_kyc__0.xml` | 788 | `dac676e1b3216718…` |
| `res/drawable/$ic_banner_ibft_kyc__1.xml` | 788 | `6472c14cc4b2dbe2…` |
| `res/drawable/$ic_banner_ibft_kyc__2.xml` | 788 | `aee50d5bcbcb0596…` |
| `res/drawable/$ic_banner_ibft_kyc__3.xml` | 788 | `433c51f920c176c8…` |
| `res/drawable/$ic_beauty_success__0.xml` | 788 | `d85222496880e6df…` |
| `res/drawable/$ic_bill_reminder__0.xml` | 788 | `34798817d884bed4…` |
| `res/drawable/$ic_bvb__0.xml` | 964 | `443841965c46526e…` |
| `res/drawable/$ic_bvb_full_brand__0.xml` | 964 | `1fd619e1145ddb96…` |
| `res/drawable/$ic_campaingn__0.xml` | 788 | `7d8ea0013f714516…` |
| `res/drawable/$ic_campaingn__1.xml` | 788 | `21a5fe92a6b01d9a…` |
| `res/drawable/$ic_campaingn__2.xml` | 788 | `58f67fd1c2bf9706…` |
| `res/drawable/$ic_campaingn__3.xml` | 788 | `4a8230263ed1f2e4…` |
| `res/drawable/$ic_campaingn__4.xml` | 788 | `09bc98f6bf83c692…` |
| `res/drawable/$ic_campaingn__5.xml` | 888 | `234c5076546e4230…` |
| `res/drawable/$ic_card_hdb__0.xml` | 788 | `87865a8b010b438a…` |
| `res/drawable/$ic_card_hdb__1.xml` | 788 | `ba40edf92aa2db4e…` |
| `res/drawable/$ic_card_hdb__10.xml` | 788 | `9fbb58bb4b347737…` |
| `res/drawable/$ic_card_hdb__11.xml` | 788 | `70f1cc65337ffb6b…` |
| `res/drawable/$ic_card_hdb__12.xml` | 788 | `9a87757f29162dd6…` |
| `res/drawable/$ic_card_hdb__13.xml` | 788 | `d28ee386608cd724…` |
| `res/drawable/$ic_card_hdb__14.xml` | 788 | `57a3ff616bd5d73b…` |
| `res/drawable/$ic_card_hdb__15.xml` | 788 | `2706443eee390894…` |
| `res/drawable/$ic_card_hdb__16.xml` | 788 | `2bd9059fe7a4f6ad…` |
| `res/drawable/$ic_card_hdb__17.xml` | 788 | `38c5dde2ac50b426…` |
| `res/drawable/$ic_card_hdb__18.xml` | 788 | `eeb93e13b6e30e83…` |
| `res/drawable/$ic_card_hdb__19.xml` | 788 | `557975a6c66ab337…` |
| `res/drawable/$ic_card_hdb__2.xml` | 788 | `266b506dae9e81d1…` |
| `res/drawable/$ic_card_hdb__20.xml` | 788 | `bb16c4f715239e79…` |
| `res/drawable/$ic_card_hdb__21.xml` | 788 | `12469a95c2f0cae3…` |
| `res/drawable/$ic_card_hdb__22.xml` | 788 | `fe26c4011c9c6d7f…` |
| `res/drawable/$ic_card_hdb__23.xml` | 788 | `cc3b99930a095cb5…` |
| `res/drawable/$ic_card_hdb__24.xml` | 788 | `46d8a5db26f1a68f…` |
| `res/drawable/$ic_card_hdb__25.xml` | 788 | `d4b3b0a20087fcb4…` |
| `res/drawable/$ic_card_hdb__26.xml` | 788 | `a19e83390f20c542…` |
| `res/drawable/$ic_card_hdb__27.xml` | 788 | `8c37a9d598d278ce…` |
| `res/drawable/$ic_card_hdb__28.xml` | 788 | `c9f2d17c7db5bedd…` |
| `res/drawable/$ic_card_hdb__29.xml` | 788 | `6f7bc070d6d332fb…` |
| `res/drawable/$ic_card_hdb__3.xml` | 788 | `bfa790f49467febf…` |
| `res/drawable/$ic_card_hdb__30.xml` | 788 | `731c73a1a611f91b…` |
| `res/drawable/$ic_card_hdb__31.xml` | 788 | `e92a70b5f7d070e2…` |
| `res/drawable/$ic_card_hdb__32.xml` | 788 | `2e228d247e77342c…` |
| `res/drawable/$ic_card_hdb__4.xml` | 788 | `977fb53fc8b5d3e7…` |
| `res/drawable/$ic_card_hdb__5.xml` | 788 | `375b88130d9b429a…` |
| `res/drawable/$ic_card_hdb__6.xml` | 788 | `14990b794eb0a398…` |
| `res/drawable/$ic_card_hdb__7.xml` | 788 | `5685a2dcd900fda6…` |
| `res/drawable/$ic_card_hdb__8.xml` | 788 | `05c12be5accb1d21…` |
| `res/drawable/$ic_card_hdb__9.xml` | 788 | `972d834844b2100d…` |
| `res/drawable/$ic_card_link__0.xml` | 788 | `5f0765d9fbd2fcc7…` |
| `res/drawable/$ic_card_vcb__0.xml` | 888 | `ae98fffbcb3c1aba…` |
| `res/drawable/$ic_card_vcb__1.xml` | 888 | `3467080481dd2e77…` |
| `res/drawable/$ic_cashier_auth_waiting__0.xml` | 788 | `2f057484f8d899e0…` |
| `res/drawable/$ic_cashier_auth_waiting__1.xml` | 788 | `088eef7ca2a51fa2…` |
| `res/drawable/$ic_cashier_auth_waiting__10.xml` | 788 | `b71a3e62e01756bf…` |
| `res/drawable/$ic_cashier_auth_waiting__11.xml` | 788 | `bd70f6d7a141707e…` |
| `res/drawable/$ic_cashier_auth_waiting__12.xml` | 888 | `4bce7e5c92603a78…` |
| `res/drawable/$ic_cashier_auth_waiting__13.xml` | 788 | `94b84a6d98fcfdc3…` |
| `res/drawable/$ic_cashier_auth_waiting__14.xml` | 788 | `9c24ecfbbf907d97…` |
| `res/drawable/$ic_cashier_auth_waiting__15.xml` | 788 | `a2025ccc94f6f5eb…` |
| `res/drawable/$ic_cashier_auth_waiting__16.xml` | 788 | `07dca6ee968e2210…` |
| `res/drawable/$ic_cashier_auth_waiting__2.xml` | 788 | `2cff03dc2085db47…` |
| `res/drawable/$ic_cashier_auth_waiting__3.xml` | 788 | `a0943e5789eb7eba…` |
| `res/drawable/$ic_cashier_auth_waiting__4.xml` | 788 | `d42368991d92d148…` |
| `res/drawable/$ic_cashier_auth_waiting__5.xml` | 788 | `28f9eaf69a6e8527…` |
| `res/drawable/$ic_cashier_auth_waiting__6.xml` | 788 | `6ccb7858e49bfa89…` |
| `res/drawable/$ic_cashier_auth_waiting__7.xml` | 788 | `0175764247923e2b…` |
| `res/drawable/$ic_cashier_auth_waiting__8.xml` | 788 | `a20e0346e8cd329c…` |
| `res/drawable/$ic_cashier_auth_waiting__9.xml` | 788 | `275b80c48a673725…` |
| `res/drawable/$ic_cashier_button_skeleton__0.xml` | 888 | `08df0d480f4a21b6…` |
| `res/drawable/$ic_cashier_button_skeleton__1.xml` | 888 | `e95ad59add10a48c…` |
| `res/drawable/$ic_cashier_coin__0.xml` | 788 | `e52eeb9ea8725f61…` |
| `res/drawable/$ic_cashier_coin_entry_point__0.xml` | 788 | `4d20ca253a6fdcf3…` |
| `res/drawable/$ic_cashier_coin_entry_point__1.xml` | 788 | `d20a72940646e0ec…` |
| `res/drawable/$ic_cashier_coin_entry_point__2.xml` | 788 | `a44db0b07dc934e8…` |
| `res/drawable/$ic_cashier_coin_entry_point__3.xml` | 788 | `11a5c201f88884f8…` |
| `res/drawable/$ic_cashier_coin_entry_point__4.xml` | 788 | `4b5646530af556f6…` |
| `res/drawable/$ic_cashier_coin_entry_point__5.xml` | 988 | `c4b92a0f5bea14e5…` |
| `res/drawable/$ic_cashier_coin_entry_point__6.xml` | 888 | `66e98001b4160422…` |
| `res/drawable/$ic_cashier_duplicate__0.xml` | 864 | `36ec65c110f8cd46…` |
| `res/drawable/$ic_cashier_duplicate__1.xml` | 888 | `aa35c2319080edb9…` |
| `res/drawable/$ic_cashier_duplicate__10.xml` | 764 | `d15239d6b3b4ebc8…` |
| `res/drawable/$ic_cashier_duplicate__11.xml` | 764 | `bd22c030bc280032…` |
| `res/drawable/$ic_cashier_duplicate__12.xml` | 764 | `77c7b65deb9ee716…` |
| `res/drawable/$ic_cashier_duplicate__2.xml` | 788 | `9b60e7645ff27550…` |
| `res/drawable/$ic_cashier_duplicate__3.xml` | 764 | `ed9f1eee488a0c94…` |
| `res/drawable/$ic_cashier_duplicate__4.xml` | 764 | `c7bad173e03b3569…` |
| `res/drawable/$ic_cashier_duplicate__5.xml` | 764 | `f6d92569a3d18f96…` |
| `res/drawable/$ic_cashier_duplicate__6.xml` | 888 | `80575fdcb1bb8971…` |
| `res/drawable/$ic_cashier_duplicate__7.xml` | 888 | `0e02faa73cbe1dbf…` |
| `res/drawable/$ic_cashier_duplicate__8.xml` | 888 | `e2b75dd278bb6de1…` |
| `res/drawable/$ic_cashier_duplicate__9.xml` | 888 | `9f2f996ba4fa28b5…` |
| `res/drawable/$ic_cashier_general_error__0.xml` | 788 | `08f16cbd9946e98c…` |
| `res/drawable/$ic_cashier_general_error__1.xml` | 788 | `09fe3a1f5d914261…` |
| `res/drawable/$ic_cashier_general_error__10.xml` | 788 | `6564ad13b366d369…` |
| `res/drawable/$ic_cashier_general_error__11.xml` | 788 | `3b051c1239593893…` |
| `res/drawable/$ic_cashier_general_error__12.xml` | 788 | `a9e371c702296c0f…` |
| `res/drawable/$ic_cashier_general_error__13.xml` | 788 | `516fa76ba6414244…` |
| `res/drawable/$ic_cashier_general_error__14.xml` | 788 | `7670bdd0f06dfd96…` |
| `res/drawable/$ic_cashier_general_error__2.xml` | 788 | `6b23ea8be8e7aeb3…` |
| `res/drawable/$ic_cashier_general_error__3.xml` | 788 | `7f5991242446c405…` |
| `res/drawable/$ic_cashier_general_error__4.xml` | 788 | `404075a20be5e94d…` |
| `res/drawable/$ic_cashier_general_error__5.xml` | 788 | `c22ab0dd2d950c7c…` |
| `res/drawable/$ic_cashier_general_error__6.xml` | 788 | `e1656ab8e1b488a8…` |
| `res/drawable/$ic_cashier_general_error__7.xml` | 788 | `a6fe34ac18b75cfc…` |
| `res/drawable/$ic_cashier_general_error__8.xml` | 788 | `1c4c413ef75909d9…` |
| `res/drawable/$ic_cashier_general_error__9.xml` | 788 | `4b704a3ee7bd1ca6…` |
| `res/drawable/$ic_cashier_general_error_v3__0.xml` | 788 | `08f16cbd9946e98c…` |
| `res/drawable/$ic_cashier_general_error_v3__1.xml` | 788 | `09fe3a1f5d914261…` |
| `res/drawable/$ic_cashier_general_error_v3__10.xml` | 788 | `6564ad13b366d369…` |
| `res/drawable/$ic_cashier_general_error_v3__11.xml` | 788 | `3b051c1239593893…` |
| `res/drawable/$ic_cashier_general_error_v3__12.xml` | 788 | `a9e371c702296c0f…` |
| `res/drawable/$ic_cashier_general_error_v3__13.xml` | 788 | `516fa76ba6414244…` |
| `res/drawable/$ic_cashier_general_error_v3__14.xml` | 788 | `7670bdd0f06dfd96…` |
| `res/drawable/$ic_cashier_general_error_v3__2.xml` | 788 | `6b23ea8be8e7aeb3…` |
| `res/drawable/$ic_cashier_general_error_v3__3.xml` | 788 | `7f5991242446c405…` |
| `res/drawable/$ic_cashier_general_error_v3__4.xml` | 788 | `404075a20be5e94d…` |
| `res/drawable/$ic_cashier_general_error_v3__5.xml` | 788 | `c22ab0dd2d950c7c…` |
| `res/drawable/$ic_cashier_general_error_v3__6.xml` | 788 | `e1656ab8e1b488a8…` |
| `res/drawable/$ic_cashier_general_error_v3__7.xml` | 788 | `a6fe34ac18b75cfc…` |
| `res/drawable/$ic_cashier_general_error_v3__8.xml` | 788 | `1c4c413ef75909d9…` |
| `res/drawable/$ic_cashier_general_error_v3__9.xml` | 788 | `4b704a3ee7bd1ca6…` |
| `res/drawable/$ic_cashier_info_skeleton__0.xml` | 888 | `6caa6b9d2962bf1e…` |
| `res/drawable/$ic_cashier_info_skeleton__1.xml` | 888 | `5e3d02988dff2c64…` |
| `res/drawable/$ic_cashier_info_skeleton__2.xml` | 888 | `f2438d8d1094336b…` |
| `res/drawable/$ic_cashier_info_skeleton__3.xml` | 888 | `8a4a27db2ac30925…` |
| `res/drawable/$ic_cashier_info_skeleton__4.xml` | 888 | `8df92c7039443ea6…` |
| `res/drawable/$ic_cashier_info_skeleton__5.xml` | 888 | `9280a6e4f4c3d545…` |
| `res/drawable/$ic_cashier_info_skeleton__6.xml` | 888 | `add2b3eb2f3b6c3d…` |
| `res/drawable/$ic_cashier_info_skeleton__7.xml` | 888 | `222108ebfc8f55c6…` |
| `res/drawable/$ic_cashier_info_skeleton__8.xml` | 888 | `134feacf4b7cd5fa…` |
| `res/drawable/$ic_cashier_network_error__0.xml` | 888 | `d135040fc8bbe03d…` |
| `res/drawable/$ic_cashier_network_error__1.xml` | 888 | `7f7c46b87d4d7a59…` |
| `res/drawable/$ic_cashier_network_error__10.xml` | 788 | `453d7c9d70a8aa9d…` |
| `res/drawable/$ic_cashier_network_error__11.xml` | 888 | `29f9ec0bb666a51f…` |
| `res/drawable/$ic_cashier_network_error__12.xml` | 888 | `53a3ab1fb3130c61…` |
| `res/drawable/$ic_cashier_network_error__13.xml` | 888 | `dfda631c507a62b8…` |
| `res/drawable/$ic_cashier_network_error__14.xml` | 788 | `409760e7e3eb3889…` |
| `res/drawable/$ic_cashier_network_error__15.xml` | 888 | `ee186f5783e8592c…` |
| `res/drawable/$ic_cashier_network_error__16.xml` | 888 | `10714d969cbd5a71…` |
| `res/drawable/$ic_cashier_network_error__17.xml` | 888 | `833648867f54a38a…` |
| `res/drawable/$ic_cashier_network_error__18.xml` | 888 | `bf4cb3e33c540384…` |
| `res/drawable/$ic_cashier_network_error__19.xml` | 888 | `8f1868505824561d…` |
| `res/drawable/$ic_cashier_network_error__2.xml` | 988 | `e84ab18dd2733baa…` |
| `res/drawable/$ic_cashier_network_error__20.xml` | 888 | `4c74c74b1990a857…` |
| `res/drawable/$ic_cashier_network_error__21.xml` | 888 | `4e6d945507c26c73…` |
| `res/drawable/$ic_cashier_network_error__22.xml` | 888 | `740f7dd29f33a419…` |
| `res/drawable/$ic_cashier_network_error__23.xml` | 888 | `754c1465e3b21fc8…` |
| `res/drawable/$ic_cashier_network_error__3.xml` | 1188 | `ac1e2472c9363dd8…` |
| `res/drawable/$ic_cashier_network_error__4.xml` | 988 | `84632874bc4c32c8…` |
| `res/drawable/$ic_cashier_network_error__5.xml` | 988 | `61205c7b8ab5019c…` |
| `res/drawable/$ic_cashier_network_error__6.xml` | 788 | `2a079a91e9e982fb…` |
| `res/drawable/$ic_cashier_network_error__7.xml` | 788 | `c1f069e0d79617e0…` |
| `res/drawable/$ic_cashier_network_error__8.xml` | 888 | `24fd56356b2a398b…` |
| `res/drawable/$ic_cashier_network_error__9.xml` | 888 | `fa79e935add0e8b3…` |
| `res/drawable/$ic_cashier_network_error_v3__0.xml` | 788 | `490f2d35800286ad…` |
| `res/drawable/$ic_cashier_network_error_v3__1.xml` | 788 | `8c529806b256a4fc…` |
| `res/drawable/$ic_cashier_network_error_v3__10.xml` | 764 | `4f1790bafa8056b5…` |
| `res/drawable/$ic_cashier_network_error_v3__11.xml` | 788 | `b6b22501b83bf634…` |
| `res/drawable/$ic_cashier_network_error_v3__12.xml` | 788 | `c5c08540dc91870b…` |
| `res/drawable/$ic_cashier_network_error_v3__13.xml` | 788 | `fabadf2e551fc0b8…` |
| `res/drawable/$ic_cashier_network_error_v3__2.xml` | 788 | `8f5c846bfc8eb845…` |
| `res/drawable/$ic_cashier_network_error_v3__3.xml` | 788 | `3686672fdaed938e…` |
| `res/drawable/$ic_cashier_network_error_v3__4.xml` | 788 | `e308dd0c30631762…` |
| `res/drawable/$ic_cashier_network_error_v3__5.xml` | 1488 | `7e8b40c6b32713b7…` |
| `res/drawable/$ic_cashier_network_error_v3__6.xml` | 1488 | `8610a1fb42716b23…` |
| `res/drawable/$ic_cashier_network_error_v3__7.xml` | 1488 | `ba65a6084390e52c…` |
| `res/drawable/$ic_cashier_network_error_v3__8.xml` | 1488 | `054f9a7f3f498c64…` |
| `res/drawable/$ic_cashier_network_error_v3__9.xml` | 1488 | `927e328765eb4073…` |
| `res/drawable/$ic_cashier_promotion_default__0.xml` | 788 | `c571e8c283d83610…` |
| `res/drawable/$ic_cashier_promotion_dialog__0.xml` | 888 | `0b22211ff8342cb0…` |
| `res/drawable/$ic_cashier_promotion_dialog__1.xml` | 764 | `454e4dd4576f8187…` |
| `res/drawable/$ic_cashier_promotion_dialog__10.xml` | 788 | `4c1d0a7bbe4e9104…` |
| `res/drawable/$ic_cashier_promotion_dialog__11.xml` | 788 | `25e4f176712429ce…` |
| `res/drawable/$ic_cashier_promotion_dialog__12.xml` | 788 | `043ee57f80bdb763…` |
| `res/drawable/$ic_cashier_promotion_dialog__13.xml` | 788 | `a282b2144c142f2b…` |
| `res/drawable/$ic_cashier_promotion_dialog__14.xml` | 764 | `4928fcf6978a0232…` |
| `res/drawable/$ic_cashier_promotion_dialog__15.xml` | 764 | `f51fc3c9bb2dfebb…` |
| `res/drawable/$ic_cashier_promotion_dialog__16.xml` | 888 | `ba8a70e70f32a66a…` |
| `res/drawable/$ic_cashier_promotion_dialog__17.xml` | 888 | `c837eeff8a1c15cd…` |
| `res/drawable/$ic_cashier_promotion_dialog__18.xml` | 1088 | `5737231ba236790d…` |
| `res/drawable/$ic_cashier_promotion_dialog__19.xml` | 788 | `c4cc8363844eca4c…` |
| `res/drawable/$ic_cashier_promotion_dialog__2.xml` | 788 | `b7c9c343fd06480f…` |
| `res/drawable/$ic_cashier_promotion_dialog__20.xml` | 788 | `487a617fc98a0d41…` |
| `res/drawable/$ic_cashier_promotion_dialog__21.xml` | 888 | `dfde31b5bf73a843…` |
| `res/drawable/$ic_cashier_promotion_dialog__22.xml` | 764 | `ee5b4d7300eb73af…` |
| `res/drawable/$ic_cashier_promotion_dialog__23.xml` | 764 | `829d7efdbf9b8138…` |
| `res/drawable/$ic_cashier_promotion_dialog__24.xml` | 764 | `6c29aa732bda46bd…` |
| `res/drawable/$ic_cashier_promotion_dialog__25.xml` | 764 | `980721cb1ccd9fda…` |
| `res/drawable/$ic_cashier_promotion_dialog__26.xml` | 788 | `2fd96acd797059f6…` |
| `res/drawable/$ic_cashier_promotion_dialog__27.xml` | 788 | `32c6786b2c97c325…` |
| `res/drawable/$ic_cashier_promotion_dialog__28.xml` | 788 | `201917d22d36c6c7…` |
| `res/drawable/$ic_cashier_promotion_dialog__29.xml` | 788 | `e57b6675f78a34be…` |
| `res/drawable/$ic_cashier_promotion_dialog__3.xml` | 788 | `d7d98ff6b23aa247…` |
| `res/drawable/$ic_cashier_promotion_dialog__30.xml` | 888 | `e6169336d0f022e6…` |
| `res/drawable/$ic_cashier_promotion_dialog__31.xml` | 888 | `efa958c1669056e2…` |
| `res/drawable/$ic_cashier_promotion_dialog__32.xml` | 764 | `21a0c6cf86837284…` |
| `res/drawable/$ic_cashier_promotion_dialog__33.xml` | 788 | `89266eb4f5588a04…` |
| `res/drawable/$ic_cashier_promotion_dialog__34.xml` | 864 | `5fa87e90d73bb7ea…` |
| `res/drawable/$ic_cashier_promotion_dialog__35.xml` | 788 | `ef2912c690093a02…` |
| `res/drawable/$ic_cashier_promotion_dialog__36.xml` | 764 | `c76a04bbcdd69748…` |
| `res/drawable/$ic_cashier_promotion_dialog__37.xml` | 888 | `be2248e4e88a5b83…` |
| `res/drawable/$ic_cashier_promotion_dialog__38.xml` | 888 | `2c2481b146e089e2…` |
| `res/drawable/$ic_cashier_promotion_dialog__39.xml` | 764 | `f289c4de2e0cd5d3…` |
| `res/drawable/$ic_cashier_promotion_dialog__4.xml` | 788 | `d317c131a8a8adf3…` |
| `res/drawable/$ic_cashier_promotion_dialog__40.xml` | 788 | `865ada360adea1f5…` |
| `res/drawable/$ic_cashier_promotion_dialog__41.xml` | 864 | `1011109bea3fe37f…` |
| `res/drawable/$ic_cashier_promotion_dialog__42.xml` | 788 | `02a1826afc1da64a…` |
| `res/drawable/$ic_cashier_promotion_dialog__43.xml` | 764 | `f19d4030c15ece60…` |
| `res/drawable/$ic_cashier_promotion_dialog__5.xml` | 764 | `5d51f56b1980c3ea…` |
| `res/drawable/$ic_cashier_promotion_dialog__6.xml` | 764 | `2ec377a31c7c53f4…` |
| `res/drawable/$ic_cashier_promotion_dialog__7.xml` | 788 | `7304756bfa02d4ce…` |
| `res/drawable/$ic_cashier_promotion_dialog__8.xml` | 788 | `0b4b341a255ec2e5…` |
| `res/drawable/$ic_cashier_promotion_dialog__9.xml` | 788 | `abba97c2c403ba0b…` |
| `res/drawable/$ic_cashier_promotion_error__0.xml` | 888 | `1929f97976b6d47f…` |
| `res/drawable/$ic_cashier_promotion_error__1.xml` | 888 | `7cfe1c77b5f28b1e…` |
| `res/drawable/$ic_cashier_promotion_error__10.xml` | 888 | `591e767319dfd6cf…` |
| `res/drawable/$ic_cashier_promotion_error__11.xml` | 888 | `aeea2aea614864d5…` |
| `res/drawable/$ic_cashier_promotion_error__12.xml` | 788 | `d9af0e2cca53e5dd…` |
| `res/drawable/$ic_cashier_promotion_error__13.xml` | 1288 | `ec62517c85501567…` |
| `res/drawable/$ic_cashier_promotion_error__14.xml` | 788 | `014167a0cae98c85…` |
| `res/drawable/$ic_cashier_promotion_error__15.xml` | 788 | `e753fca1941d1d8a…` |
| `res/drawable/$ic_cashier_promotion_error__16.xml` | 1488 | `32b65cfbe8d28f44…` |
| `res/drawable/$ic_cashier_promotion_error__2.xml` | 888 | `3fd4cd0e42d2c8b8…` |
| `res/drawable/$ic_cashier_promotion_error__3.xml` | 888 | `c0ac43743727196f…` |
| `res/drawable/$ic_cashier_promotion_error__4.xml` | 888 | `c2725f4cae25a64b…` |
| `res/drawable/$ic_cashier_promotion_error__5.xml` | 888 | `86d0f695c4e3379d…` |
| `res/drawable/$ic_cashier_promotion_error__6.xml` | 888 | `0a1d9f10e926532a…` |
| `res/drawable/$ic_cashier_promotion_error__7.xml` | 888 | `b59121c19d6e210c…` |
| `res/drawable/$ic_cashier_promotion_error__8.xml` | 888 | `59b6dbf6f60bc747…` |
| `res/drawable/$ic_cashier_promotion_error__9.xml` | 888 | `8bd6bc670497db5c…` |
| `res/drawable/$ic_cashier_promotion_item_loading__0.xml` | 888 | `136e3ceb688bfb53…` |
| `res/drawable/$ic_cashier_promotion_item_loading__1.xml` | 888 | `ba6403fdea6eaeea…` |
| `res/drawable/$ic_cashier_promotion_item_loading__2.xml` | 888 | `3cfe9d0a34cf11ca…` |
| `res/drawable/$ic_cashier_promotion_item_loading__3.xml` | 888 | `cb195bf832f74f28…` |
| `res/drawable/$ic_cashier_promotion_item_loading__4.xml` | 888 | `d3bb8ef0b3cdcdc1…` |
| `res/drawable/$ic_cashier_promotion_item_loading__5.xml` | 888 | `666b249ff78fd84e…` |
| `res/drawable/$ic_cashier_sof_skeleton__0.xml` | 888 | `d42d8377e44ba736…` |
| `res/drawable/$ic_cashier_sof_skeleton__1.xml` | 888 | `1bf8669402bf4912…` |
| `res/drawable/$ic_cashier_sof_skeleton__2.xml` | 888 | `7163ed07e3192b64…` |
| `res/drawable/$ic_cashier_sof_skeleton__3.xml` | 888 | `e8e7da91144204ff…` |
| `res/drawable/$ic_cashier_sof_skeleton__4.xml` | 888 | `d2db52817b8f1c40…` |
| `res/drawable/$ic_cashier_sof_skeleton__5.xml` | 888 | `6f5c72bc07fef40b…` |
| `res/drawable/$ic_cashier_system_error__0.xml` | 788 | `b373817c3d53af0f…` |
| `res/drawable/$ic_cashier_system_error__1.xml` | 788 | `a7d0874af0217b53…` |
| `res/drawable/$ic_cashier_system_error__10.xml` | 888 | `2912f25f3ad7a7c8…` |
| `res/drawable/$ic_cashier_system_error__11.xml` | 788 | `124f18071f59e54b…` |
| `res/drawable/$ic_cashier_system_error__12.xml` | 788 | `c40fcfa3230f7ed5…` |
| `res/drawable/$ic_cashier_system_error__13.xml` | 788 | `b2c8848761f47aba…` |
| `res/drawable/$ic_cashier_system_error__14.xml` | 788 | `1f854211837f80d9…` |
| `res/drawable/$ic_cashier_system_error__2.xml` | 888 | `c5804a68ba575ea3…` |
| `res/drawable/$ic_cashier_system_error__3.xml` | 888 | `07577c54540a893d…` |
| `res/drawable/$ic_cashier_system_error__4.xml` | 888 | `67ab1b2f683011de…` |
| `res/drawable/$ic_cashier_system_error__5.xml` | 788 | `8fb60a45cc623513…` |
| `res/drawable/$ic_cashier_system_error__6.xml` | 788 | `6056c278072e483b…` |
| `res/drawable/$ic_cashier_system_error__7.xml` | 788 | `d446f41a408dc04a…` |
| `res/drawable/$ic_cashier_system_error__8.xml` | 788 | `1d6032bbb20f1e40…` |
| `res/drawable/$ic_cashier_system_error__9.xml` | 788 | `21878aed55bfdca2…` |
| `res/drawable/$ic_cashier_tutorial_v3__0.xml` | 864 | `c8741f3a13dfb16a…` |
| `res/drawable/$ic_cashier_tutorial_v3__1.xml` | 788 | `2d742750e25cc29b…` |
| `res/drawable/$ic_cashier_tutorial_v3__2.xml` | 788 | `3d388674956e1a31…` |
| `res/drawable/$ic_cashier_tutorial_v3__3.xml` | 788 | `d15174feebe1b1ad…` |
| `res/drawable/$ic_cashier_tutorial_v3__4.xml` | 788 | `b7ea8be6e234b65a…` |
| `res/drawable/$ic_cc_card_result__0.xml` | 888 | `41a38b2057d4f321…` |
| `res/drawable/$ic_cc_card_result__1.xml` | 764 | `2724e2f0c07adc6f…` |
| `res/drawable/$ic_cc_card_result__2.xml` | 888 | `67462bb29fbcf886…` |
| `res/drawable/$ic_cc_card_result__3.xml` | 788 | `7f230738a7f7a13d…` |
| `res/drawable/$ic_cc_card_result__4.xml` | 764 | `c95e45cacdf6a407…` |
| `res/drawable/$ic_cc_card_result_fail__0.xml` | 788 | `5224fbabff8890e9…` |
| `res/drawable/$ic_cc_card_result_fail__1.xml` | 788 | `f5ac778c1d38c0e6…` |
| `res/drawable/$ic_cc_card_result_fail__10.xml` | 788 | `cc31b30d6b2e3847…` |
| `res/drawable/$ic_cc_card_result_fail__11.xml` | 788 | `f87c59acadc6caa6…` |
| `res/drawable/$ic_cc_card_result_fail__12.xml` | 888 | `b6b73c9a68ae44be…` |
| `res/drawable/$ic_cc_card_result_fail__13.xml` | 864 | `002988710c4ab269…` |
| `res/drawable/$ic_cc_card_result_fail__2.xml` | 788 | `7c23afeaede11069…` |
| `res/drawable/$ic_cc_card_result_fail__3.xml` | 788 | `eb00dbf74ce7369e…` |
| `res/drawable/$ic_cc_card_result_fail__4.xml` | 788 | `28e77f331a208726…` |
| `res/drawable/$ic_cc_card_result_fail__5.xml` | 788 | `c21d16f4d43a483a…` |
| `res/drawable/$ic_cc_card_result_fail__6.xml` | 788 | `54cadb0e18569966…` |
| `res/drawable/$ic_cc_card_result_fail__7.xml` | 788 | `28495033ecd8d36a…` |
| `res/drawable/$ic_cc_card_result_fail__8.xml` | 788 | `0b9b887ed58d6d7b…` |
| `res/drawable/$ic_cc_card_result_fail__9.xml` | 788 | `a3449d8491eb0144…` |
| `res/drawable/$ic_checkbox_red_checked__0.xml` | 788 | `b611f019437597b9…` |
| `res/drawable/$ic_close_account_failed__0.xml` | 788 | `f511544cfef70aa3…` |
| `res/drawable/$ic_close_account_failed__1.xml` | 788 | `a9601e7135e9a8a3…` |
| `res/drawable/$ic_close_account_failed__10.xml` | 788 | `df9778b001ac36b1…` |
| `res/drawable/$ic_close_account_failed__11.xml` | 788 | `c73effd23e743337…` |
| `res/drawable/$ic_close_account_failed__12.xml` | 888 | `b6b73c9a68ae44be…` |
| `res/drawable/$ic_close_account_failed__13.xml` | 864 | `002988710c4ab269…` |
| `res/drawable/$ic_close_account_failed__2.xml` | 788 | `e0c106ffb4b35cff…` |
| `res/drawable/$ic_close_account_failed__3.xml` | 788 | `95bfc9cc25e5c3dd…` |
| `res/drawable/$ic_close_account_failed__4.xml` | 788 | `e68a4d7c1a358462…` |
| `res/drawable/$ic_close_account_failed__5.xml` | 788 | `a92504de9c1ce623…` |
| `res/drawable/$ic_close_account_failed__6.xml` | 788 | `397f308625e90c4d…` |
| `res/drawable/$ic_close_account_failed__7.xml` | 788 | `dcb8782bf284e8be…` |
| `res/drawable/$ic_close_account_failed__8.xml` | 788 | `d43867e364f162c5…` |
| `res/drawable/$ic_close_account_failed__9.xml` | 788 | `8879648c8126890e…` |
| `res/drawable/$ic_congrats_gift__0.xml` | 788 | `48a207398b87d525…` |
| `res/drawable/$ic_congrats_gift__1.xml` | 788 | `de26e3fe15397c34…` |
| `res/drawable/$ic_congrats_gift__2.xml` | 788 | `23db5066486eec9d…` |
| `res/drawable/$ic_congrats_gift__3.xml` | 788 | `ba0f087b767b7451…` |
| `res/drawable/$ic_congrats_gift__4.xml` | 788 | `e34ed7cbe399b3a0…` |
| `res/drawable/$ic_congrats_gift__5.xml` | 788 | `28c830cdbf3cd332…` |
| `res/drawable/$ic_congrats_gift__6.xml` | 788 | `c7956ad3d75c1af5…` |
| `res/drawable/$ic_congrats_gift__7.xml` | 788 | `ae2272267c3ab6c4…` |
| `res/drawable/$ic_cs_notification__0.xml` | 788 | `77db715dbf8a5246…` |
| `res/drawable/$ic_cs_notification__1.xml` | 788 | `4417ae3730ca5baa…` |
| `res/drawable/$ic_cs_notification__2.xml` | 788 | `694853704ddb9df9…` |
| `res/drawable/$ic_cs_notification__3.xml` | 1488 | `5c2f4b95a133bb94…` |
| `res/drawable/$ic_cs_notification__4.xml` | 788 | `1f9c434fe82dd956…` |
| `res/drawable/$ic_cs_notification__5.xml` | 788 | `8546c4963cee2124…` |
| `res/drawable/$ic_dab__0.xml` | 788 | `13246efa3cee4a36…` |
| `res/drawable/$ic_dab__1.xml` | 788 | `ff2def8f2c0b564a…` |
| `res/drawable/$ic_dab__2.xml` | 788 | `a2ca9a9b99b1b5bb…` |
| `res/drawable/$ic_dab_full_brand__0.xml` | 788 | `943afddd894490e9…` |
| `res/drawable/$ic_dab_full_brand__1.xml` | 788 | `6456b739261ae7cf…` |
| `res/drawable/$ic_dab_full_brand__2.xml` | 788 | `511973e18be78d28…` |
| `res/drawable/$ic_discount_selected__0.xml` | 788 | `5307daa18704214e…` |
| `res/drawable/$ic_display_page__0.xml` | 788 | `3b7bace86d5d39db…` |
| `res/drawable/$ic_display_page__1.xml` | 788 | `b6703a25cc3b51b9…` |
| `res/drawable/$ic_display_page__10.xml` | 888 | `26d42ef0c2e6ad37…` |
| `res/drawable/$ic_display_page__11.xml` | 788 | `73eeb2bf34f2c09e…` |
| `res/drawable/$ic_display_page__12.xml` | 788 | `9654b7411d555fdb…` |
| `res/drawable/$ic_display_page__13.xml` | 788 | `a5b2c024b1feabc5…` |
| `res/drawable/$ic_display_page__14.xml` | 888 | `1765b137daad2948…` |
| `res/drawable/$ic_display_page__15.xml` | 788 | `c88cf07b7bd3643c…` |
| `res/drawable/$ic_display_page__16.xml` | 1188 | `2adeed94be8cdb72…` |
| `res/drawable/$ic_display_page__2.xml` | 888 | `4087a3c3b64bc467…` |
| `res/drawable/$ic_display_page__3.xml` | 888 | `7782b40c690fa237…` |
| `res/drawable/$ic_display_page__4.xml` | 888 | `2ab11a147752a214…` |
| `res/drawable/$ic_display_page__5.xml` | 788 | `9bac561370a52395…` |
| `res/drawable/$ic_display_page__6.xml` | 788 | `61752765a06a5b28…` |
| `res/drawable/$ic_display_page__7.xml` | 788 | `a00028502207d33a…` |
| `res/drawable/$ic_display_page__8.xml` | 788 | `acca17550e99a59f…` |
| `res/drawable/$ic_display_page__9.xml` | 788 | `7ee8f9fee99ba661…` |
| `res/drawable/$ic_downloading__0.xml` | 788 | `9de43b75b2b4b066…` |
| `res/drawable/$ic_eib__0.xml` | 1088 | `a5b9a89b44f95b82…` |
| `res/drawable/$ic_eib__1.xml` | 1788 | `63ce3584bb955d32…` |
| `res/drawable/$ic_eib__2.xml` | 988 | `f5d91b7cf55fc03b…` |
| `res/drawable/$ic_eib__3.xml` | 864 | `d5d034e70a811f8f…` |
| `res/drawable/$ic_eib__4.xml` | 1788 | `6b48a14ae3572576…` |
| `res/drawable/$ic_eib__5.xml` | 988 | `79169dfd73701837…` |
| `res/drawable/$ic_eib__6.xml` | 864 | `4cb20f045ec195d5…` |
| `res/drawable/$ic_eib_full_brand__0.xml` | 788 | `26ac1043ebc8ae49…` |
| `res/drawable/$ic_eib_full_brand__1.xml` | 1088 | `dfc804c675228ef4…` |
| `res/drawable/$ic_eib_full_brand__2.xml` | 1788 | `528c5e2a2fe1a9c3…` |
| `res/drawable/$ic_eib_full_brand__3.xml` | 1088 | `704e14b246f01e75…` |
| `res/drawable/$ic_eib_full_brand__4.xml` | 864 | `1535c2923aca4c8f…` |
| `res/drawable/$ic_eib_full_brand__5.xml` | 1788 | `3f416da04023e8fd…` |
| `res/drawable/$ic_eib_full_brand__6.xml` | 1088 | `19bde76cb62291c4…` |
| `res/drawable/$ic_eib_full_brand__7.xml` | 864 | `d71359cf9c41eebe…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__0.xml` | 788 | `e68085960c33f455…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__1.xml` | 788 | `3d6c018f5ae41751…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__10.xml` | 764 | `53e3020cecd4af41…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__11.xml` | 764 | `4dcb29f158fd7e1c…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__12.xml` | 888 | `a0a986e26f7445c5…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__13.xml` | 888 | `f4b4c4264e3f1040…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__14.xml` | 1088 | `c4456046f5582da5…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__15.xml` | 788 | `34971efeabf54efb…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__16.xml` | 788 | `3ab468690c129f27…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__17.xml` | 888 | `2046a69ad297b53f…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__18.xml` | 764 | `de852c65f78ac37a…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__19.xml` | 764 | `bbac8c68e3aa924e…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__2.xml` | 788 | `4e777ab5934c0bac…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__20.xml` | 764 | `76717027871c07c5…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__21.xml` | 764 | `a78549e67fa98f4e…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__22.xml` | 788 | `7ea9fa0f7e51bab0…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__23.xml` | 788 | `fdb4c9205c80f2b2…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__24.xml` | 788 | `2d395ed18711ca7d…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__25.xml` | 788 | `1f452ddbc2f229e2…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__26.xml` | 788 | `1b5cf0b302e50511…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__3.xml` | 788 | `d2a749fe6b829811…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__4.xml` | 1288 | `8ee5676f33dd32cc…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__5.xml` | 888 | `d0d514f2b1a780cb…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__6.xml` | 888 | `e3a4947c7e38316e…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__7.xml` | 1488 | `41cbde55ec5ba473…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__8.xml` | 988 | `62e41fd375920210…` |
| `res/drawable/$ic_ekyc_pro_data_not_found__9.xml` | 788 | `d996125954504d28…` |
| `res/drawable/$ic_empty_bank__0.xml` | 788 | `f3950ad254a77ccd…` |
| `res/drawable/$ic_empty_bank__1.xml` | 788 | `37a49b85a01e84c8…` |
| `res/drawable/$ic_empty_bank__2.xml` | 788 | `a109ee3adb043096…` |
| `res/drawable/$ic_empty_bank__3.xml` | 788 | `bf8c3dacbfd17906…` |
| `res/drawable/$ic_empty_bank__4.xml` | 788 | `5774a218907418b9…` |
| `res/drawable/$ic_empty_bank__5.xml` | 788 | `678421c675e3bfea…` |
| `res/drawable/$ic_empty_sketch_06__0.xml` | 788 | `08785f7126f9b8e6…` |
| `res/drawable/$ic_empty_sketch_06__1.xml` | 788 | `e4d0046fd677ef2d…` |
| `res/drawable/$ic_empty_sketch_06__10.xml` | 1088 | `eb0a3bc1ab7b737d…` |
| `res/drawable/$ic_empty_sketch_06__11.xml` | 1288 | `5aa4928c09b7801f…` |
| `res/drawable/$ic_empty_sketch_06__12.xml` | 888 | `57443e76eb8ebfa2…` |
| `res/drawable/$ic_empty_sketch_06__13.xml` | 888 | `e48ba741393c9896…` |
| `res/drawable/$ic_empty_sketch_06__14.xml` | 1488 | `e092442ce196bf30…` |
| `res/drawable/$ic_empty_sketch_06__15.xml` | 988 | `1d91b25ddcd27640…` |
| `res/drawable/$ic_empty_sketch_06__16.xml` | 988 | `7ab0720564a700be…` |
| `res/drawable/$ic_empty_sketch_06__17.xml` | 1288 | `fa3095241320d555…` |
| `res/drawable/$ic_empty_sketch_06__2.xml` | 1688 | `ef44044f9fe25fbb…` |
| `res/drawable/$ic_empty_sketch_06__3.xml` | 788 | `258124187a27c63a…` |
| `res/drawable/$ic_empty_sketch_06__4.xml` | 1788 | `f5971461a740e623…` |
| `res/drawable/$ic_empty_sketch_06__5.xml` | 1488 | `86e0f5f3a7dcbe23…` |
| `res/drawable/$ic_empty_sketch_06__6.xml` | 788 | `e6d9c8aa03b68254…` |
| `res/drawable/$ic_empty_sketch_06__7.xml` | 988 | `c9bb70bbb1420cc3…` |
| `res/drawable/$ic_empty_sketch_06__8.xml` | 788 | `7c92ff9a317d3bb9…` |
| `res/drawable/$ic_empty_sketch_06__9.xml` | 988 | `2a67b04a046c4604…` |
| `res/drawable/$ic_error_page__0.xml` | 788 | `b1a5d9e1d563a7c8…` |
| `res/drawable/$ic_error_page__1.xml` | 788 | `32a2964d37d5aea5…` |
| `res/drawable/$ic_external_placeholder__0.xml` | 888 | `de8598bb4d3512c6…` |
| `res/drawable/$ic_floating_event__0.xml` | 864 | `acf431f06bc9c2d1…` |
| `res/drawable/$ic_floating_event__1.xml` | 788 | `ebd7d531a16d46ef…` |
| `res/drawable/$ic_floating_event__10.xml` | 764 | `8e8ace094332f0d7…` |
| `res/drawable/$ic_floating_event__11.xml` | 764 | `a40b310416897fc9…` |
| `res/drawable/$ic_floating_event__2.xml` | 864 | `549f44e75a45c003…` |
| `res/drawable/$ic_floating_event__3.xml` | 788 | `9b14e51a88a66b5e…` |
| `res/drawable/$ic_floating_event__4.xml` | 864 | `ace9c334f61271d7…` |
| `res/drawable/$ic_floating_event__5.xml` | 764 | `791cd6cc4eb530ca…` |
| `res/drawable/$ic_floating_event__6.xml` | 764 | `010b7a3558902427…` |
| `res/drawable/$ic_floating_event__7.xml` | 864 | `a341a7b465fb542b…` |
| `res/drawable/$ic_floating_event__8.xml` | 764 | `19a56bbe717a15cc…` |
| `res/drawable/$ic_floating_event__9.xml` | 864 | `dcce41f7b3c129b5…` |
| `res/drawable/$ic_floating_tooltip__0.xml` | 864 | `acf431f06bc9c2d1…` |
| `res/drawable/$ic_floating_tooltip__1.xml` | 788 | `ebd7d531a16d46ef…` |
| `res/drawable/$ic_floating_tooltip__10.xml` | 864 | `b5c7994fd0108199…` |
| `res/drawable/$ic_floating_tooltip__11.xml` | 764 | `74507380226b2fcd…` |
| `res/drawable/$ic_floating_tooltip__12.xml` | 764 | `d9a60742046204e7…` |
| `res/drawable/$ic_floating_tooltip__2.xml` | 864 | `549f44e75a45c003…` |
| `res/drawable/$ic_floating_tooltip__3.xml` | 788 | `9b14e51a88a66b5e…` |
| `res/drawable/$ic_floating_tooltip__4.xml` | 864 | `ace9c334f61271d7…` |
| `res/drawable/$ic_floating_tooltip__5.xml` | 764 | `1a170d1a54075380…` |
| `res/drawable/$ic_floating_tooltip__6.xml` | 764 | `2bbdd6dff134bb4f…` |
| `res/drawable/$ic_floating_tooltip__7.xml` | 764 | `c522c1f242c515f2…` |
| `res/drawable/$ic_floating_tooltip__8.xml` | 864 | `16bb3e825b5b2da0…` |
| `res/drawable/$ic_floating_tooltip__9.xml` | 764 | `e5c5d78e38a7aa31…` |
| `res/drawable/$ic_general_coin_color__0.xml` | 788 | `000992d792afc23d…` |
| `res/drawable/$ic_gift_box__0.xml` | 788 | `2ef7dabd9f56f0e2…` |
| `res/drawable/$ic_gift_box__1.xml` | 788 | `ae8db88d1ee4070d…` |
| `res/drawable/$ic_hdb__0.xml` | 788 | `a427da0cd5c98509…` |
| `res/drawable/$ic_hdb__1.xml` | 788 | `b9a32e6986d794b2…` |
| `res/drawable/$ic_hdb__10.xml` | 788 | `afe2e2d84dd06ab5…` |
| `res/drawable/$ic_hdb__11.xml` | 788 | `8581dd26bbc67f31…` |
| `res/drawable/$ic_hdb__12.xml` | 788 | `76959bc6f356f247…` |
| `res/drawable/$ic_hdb__13.xml` | 788 | `4913a26556742306…` |
| `res/drawable/$ic_hdb__14.xml` | 788 | `38e1722953377b33…` |
| `res/drawable/$ic_hdb__15.xml` | 788 | `83430639951ae54f…` |
| `res/drawable/$ic_hdb__16.xml` | 788 | `d0c241d4186646d8…` |
| `res/drawable/$ic_hdb__17.xml` | 788 | `7b21c0982722aef0…` |
| `res/drawable/$ic_hdb__18.xml` | 788 | `d3b4f28c49f8eb70…` |
| `res/drawable/$ic_hdb__19.xml` | 788 | `8183f34220ea2488…` |
| `res/drawable/$ic_hdb__2.xml` | 788 | `821baf3362a66ab7…` |
| `res/drawable/$ic_hdb__20.xml` | 788 | `457d5e48bdd8a0b9…` |
| `res/drawable/$ic_hdb__21.xml` | 788 | `68f6aecbe4eae17f…` |
| `res/drawable/$ic_hdb__22.xml` | 788 | `c1335a9f73b5ddc2…` |
| `res/drawable/$ic_hdb__23.xml` | 788 | `16fb469aa3e289bc…` |
| `res/drawable/$ic_hdb__24.xml` | 788 | `c224882ef6b3785c…` |
| `res/drawable/$ic_hdb__25.xml` | 788 | `75cb66d19fae33d5…` |
| `res/drawable/$ic_hdb__26.xml` | 788 | `298a4c4ec603c998…` |
| `res/drawable/$ic_hdb__27.xml` | 788 | `47cc12fe8c372730…` |
| `res/drawable/$ic_hdb__28.xml` | 788 | `4c49b182bbc24791…` |
| `res/drawable/$ic_hdb__29.xml` | 788 | `570ef5448d7e37e0…` |
| `res/drawable/$ic_hdb__3.xml` | 788 | `b725c62af644f169…` |
| `res/drawable/$ic_hdb__30.xml` | 788 | `e12230ec3e41418c…` |
| `res/drawable/$ic_hdb__31.xml` | 788 | `d5d50a009fe8e660…` |
| `res/drawable/$ic_hdb__32.xml` | 788 | `6aa63f14ca921772…` |
| `res/drawable/$ic_hdb__4.xml` | 788 | `e49ef3739ab474e1…` |
| `res/drawable/$ic_hdb__5.xml` | 788 | `00e2ab161483b367…` |
| `res/drawable/$ic_hdb__6.xml` | 788 | `e5f115133fd96231…` |
| `res/drawable/$ic_hdb__7.xml` | 788 | `bf39a010c1ec970b…` |
| `res/drawable/$ic_hdb__8.xml` | 788 | `0209fcfb5ada37b3…` |
| `res/drawable/$ic_hdb__9.xml` | 788 | `5e7d8ecee0923a94…` |
| `res/drawable/$ic_hdb_full_brand__0.xml` | 788 | `aa4fd4c281717b14…` |
| `res/drawable/$ic_hdb_full_brand__1.xml` | 788 | `2d1015051f93b33a…` |
| `res/drawable/$ic_home_quick_transfer__0.xml` | 788 | `6e36db6514745141…` |
| `res/drawable/$ic_home_quick_withdraw__0.xml` | 788 | `b7adf2856b5be442…` |
| `res/drawable/$ic_home_vector__0.xml` | 764 | `a7f67672993f4c8e…` |
| `res/drawable/$ic_image_not_found__0.xml` | 788 | `bd59cbec8f3ff0d8…` |
| `res/drawable/$ic_image_not_found__1.xml` | 788 | `9868ea07106d2560…` |
| `res/drawable/$ic_infor_page__0.xml` | 788 | `791b22df81749f48…` |
| `res/drawable/$ic_infor_page__1.xml` | 788 | `ac3b918b35c7d608…` |
| `res/drawable/$ic_jcb_card_logo__0.xml` | 788 | `e40881a9cc1e8c71…` |
| `res/drawable/$ic_jcb_card_logo__1.xml` | 788 | `54db31180fdbf092…` |
| `res/drawable/$ic_jcb_card_logo__2.xml` | 788 | `27d40ab3c106cf4b…` |
| `res/drawable/$ic_jcb_debit_card_logo__0.xml` | 788 | `642d80a98754ecfd…` |
| `res/drawable/$ic_jcb_debit_card_logo__1.xml` | 788 | `986b2e577d4ff692…` |
| `res/drawable/$ic_jcb_debit_card_logo__2.xml` | 788 | `f9c59fdf6817190d…` |
| `res/drawable/$ic_kyc_account__0.xml` | 788 | `fa8b155e13bb69fb…` |
| `res/drawable/$ic_kyc_result_fail__0.xml` | 788 | `5224fbabff8890e9…` |
| `res/drawable/$ic_kyc_result_fail__1.xml` | 788 | `f5ac778c1d38c0e6…` |
| `res/drawable/$ic_kyc_result_fail__10.xml` | 788 | `cc31b30d6b2e3847…` |
| `res/drawable/$ic_kyc_result_fail__11.xml` | 788 | `f87c59acadc6caa6…` |
| `res/drawable/$ic_kyc_result_fail__12.xml` | 888 | `b6b73c9a68ae44be…` |
| `res/drawable/$ic_kyc_result_fail__13.xml` | 864 | `002988710c4ab269…` |
| `res/drawable/$ic_kyc_result_fail__2.xml` | 788 | `7c23afeaede11069…` |
| `res/drawable/$ic_kyc_result_fail__3.xml` | 788 | `eb00dbf74ce7369e…` |
| `res/drawable/$ic_kyc_result_fail__4.xml` | 788 | `28e77f331a208726…` |
| `res/drawable/$ic_kyc_result_fail__5.xml` | 788 | `c21d16f4d43a483a…` |
| `res/drawable/$ic_kyc_result_fail__6.xml` | 788 | `54cadb0e18569966…` |
| `res/drawable/$ic_kyc_result_fail__7.xml` | 788 | `28495033ecd8d36a…` |
| `res/drawable/$ic_kyc_result_fail__8.xml` | 788 | `0b9b887ed58d6d7b…` |
| `res/drawable/$ic_kyc_result_fail__9.xml` | 788 | `a3449d8491eb0144…` |
| `res/drawable/$ic_kyc_result_processing__0.xml` | 788 | `a56b3cfe58cdc064…` |
| `res/drawable/$ic_kyc_result_processing__1.xml` | 788 | `4c95ee75fcc410d7…` |
| `res/drawable/$ic_kyc_result_processing__10.xml` | 788 | `c494057197505dc2…` |
| `res/drawable/$ic_kyc_result_processing__11.xml` | 788 | `97a4d59283fe9e80…` |
| `res/drawable/$ic_kyc_result_processing__12.xml` | 888 | `b3a3f8c99ee06a28…` |
| `res/drawable/$ic_kyc_result_processing__13.xml` | 788 | `ded99cf881506f0c…` |
| `res/drawable/$ic_kyc_result_processing__14.xml` | 788 | `93fb731d63afeac0…` |
| `res/drawable/$ic_kyc_result_processing__15.xml` | 788 | `c6c2335427fa0da3…` |
| `res/drawable/$ic_kyc_result_processing__16.xml` | 788 | `1bdeeeab9b83903b…` |
| `res/drawable/$ic_kyc_result_processing__17.xml` | 864 | `3f03cb07a23071f9…` |
| `res/drawable/$ic_kyc_result_processing__2.xml` | 788 | `ee79a35f3068979d…` |
| `res/drawable/$ic_kyc_result_processing__3.xml` | 788 | `768c5a8634ec294d…` |
| `res/drawable/$ic_kyc_result_processing__4.xml` | 788 | `e9a866aefe3fa26e…` |
| `res/drawable/$ic_kyc_result_processing__5.xml` | 788 | `ba9101478cebb71a…` |
| `res/drawable/$ic_kyc_result_processing__6.xml` | 788 | `defc702f46c9bad5…` |
| `res/drawable/$ic_kyc_result_processing__7.xml` | 788 | `e9889292d093b1ed…` |
| `res/drawable/$ic_kyc_result_processing__8.xml` | 788 | `6e291ce1f1661c12…` |
| `res/drawable/$ic_kyc_result_processing__9.xml` | 788 | `664e3a9232c73454…` |
| `res/drawable/$ic_kyc_result_success__0.xml` | 788 | `9c3a67120ac76bc2…` |
| `res/drawable/$ic_kyc_result_success__1.xml` | 788 | `b7645b2d92801c95…` |
| `res/drawable/$ic_kyc_result_success__10.xml` | 788 | `cb4fda7e83dfb703…` |
| `res/drawable/$ic_kyc_result_success__11.xml` | 788 | `228d6d17388b267b…` |
| `res/drawable/$ic_kyc_result_success__12.xml` | 864 | `b5d1392378fe5dd1…` |
| `res/drawable/$ic_kyc_result_success__13.xml` | 1088 | `7e80f676422707fd…` |
| `res/drawable/$ic_kyc_result_success__14.xml` | 888 | `b98234a311b4e74f…` |
| `res/drawable/$ic_kyc_result_success__2.xml` | 788 | `e9cc0c5beb893202…` |
| `res/drawable/$ic_kyc_result_success__3.xml` | 788 | `eabf0372c5197a72…` |
| `res/drawable/$ic_kyc_result_success__4.xml` | 788 | `5afcdbd94ecd7a9b…` |
| `res/drawable/$ic_kyc_result_success__5.xml` | 788 | `d131b33aed625aeb…` |
| `res/drawable/$ic_kyc_result_success__6.xml` | 788 | `95e865c007bea91b…` |
| `res/drawable/$ic_kyc_result_success__7.xml` | 788 | `2df9b10dc9fdbbb9…` |
| `res/drawable/$ic_kyc_result_success__8.xml` | 788 | `a7e304b4c9331708…` |
| `res/drawable/$ic_kyc_result_success__9.xml` | 788 | `dbf78035d1408771…` |
| `res/drawable/$ic_limit__0.xml` | 788 | `afb073b32855fd9e…` |
| `res/drawable/$ic_linking_bank__0.xml` | 788 | `90ea6e456e36d1e8…` |
| `res/drawable/$ic_lock_acc__0.xml` | 788 | `b1a5d9e1d563a7c8…` |
| `res/drawable/$ic_lock_acc__1.xml` | 788 | `32a2964d37d5aea5…` |
| `res/drawable/$ic_loyalty__0.xml` | 788 | `581e9f7c56c9ce20…` |
| `res/drawable/$ic_loyalty__1.xml` | 788 | `35179c1fbcda77f8…` |
| `res/drawable/$ic_loyalty__2.xml` | 788 | `b5c98ffacc4c4515…` |
| `res/drawable/$ic_loyalty__3.xml` | 788 | `6ea6220803fa862c…` |
| `res/drawable/$ic_loyalty__4.xml` | 788 | `c538da2e042a893d…` |
| `res/drawable/$ic_loyalty__5.xml` | 888 | `df6d3b487f61016a…` |
| `res/drawable/$ic_maintenance__0.xml` | 788 | `113e73b5bf9f5e9a…` |
| `res/drawable/$ic_maintenance__1.xml` | 888 | `d6b0372e0990c763…` |
| `res/drawable/$ic_maintenance__10.xml` | 888 | `0f61a9ea1caf0fcb…` |
| `res/drawable/$ic_maintenance__2.xml` | 788 | `fbea070b99044e13…` |
| `res/drawable/$ic_maintenance__3.xml` | 788 | `bd52c274f4a09bbe…` |
| `res/drawable/$ic_maintenance__4.xml` | 788 | `f39625addd97f174…` |
| `res/drawable/$ic_maintenance__5.xml` | 788 | `ad7c4e088057dad9…` |
| `res/drawable/$ic_maintenance__6.xml` | 788 | `5c71b74b57ce103c…` |
| `res/drawable/$ic_maintenance__7.xml` | 788 | `128858c66152a6f1…` |
| `res/drawable/$ic_maintenance__8.xml` | 788 | `524e57dd40c4fb00…` |
| `res/drawable/$ic_maintenance__9.xml` | 1188 | `a2e3241d0921e002…` |
| `res/drawable/$ic_maintenance_service__0.xml` | 788 | `b1a5d9e1d563a7c8…` |
| `res/drawable/$ic_maintenance_service__1.xml` | 788 | `32a2964d37d5aea5…` |
| `res/drawable/$ic_merchant_coopmart25__0.xml` | 1488 | `38ea37da413c8f01…` |
| `res/drawable/$ic_merchant_coopmart25__1.xml` | 888 | `964171110248bbc0…` |
| `res/drawable/$ic_merchant_coopmart25__10.xml` | 888 | `e8c7579a0196149d…` |
| `res/drawable/$ic_merchant_coopmart25__11.xml` | 888 | `dd2b5f28288030aa…` |
| `res/drawable/$ic_merchant_coopmart25__12.xml` | 988 | `c5f35ca6fac565e3…` |
| `res/drawable/$ic_merchant_coopmart25__2.xml` | 888 | `ddfd0066f76f09bf…` |
| `res/drawable/$ic_merchant_coopmart25__3.xml` | 888 | `9092245e32b22e6c…` |
| `res/drawable/$ic_merchant_coopmart25__4.xml` | 988 | `d6a1e63dba38115c…` |
| `res/drawable/$ic_merchant_coopmart25__5.xml` | 988 | `0d3b4c0ed969688d…` |
| `res/drawable/$ic_merchant_coopmart25__6.xml` | 888 | `eaf9cd4a0529da28…` |
| `res/drawable/$ic_merchant_coopmart25__7.xml` | 888 | `375c07b6a954e0f3…` |
| `res/drawable/$ic_merchant_coopmart25__8.xml` | 888 | `0233fe148cf8e994…` |
| `res/drawable/$ic_merchant_coopmart25__9.xml` | 888 | `7f75338bba09fabb…` |
| `res/drawable/$ic_merchant_gotit__0.xml` | 788 | `127d75b99a13c9bd…` |
| `res/drawable/$ic_merchant_lazada__0.xml` | 788 | `539f0348ace33183…` |
| `res/drawable/$ic_merchant_lazada__1.xml` | 1288 | `3838aaeb1eccff1c…` |
| `res/drawable/$ic_msb__0.xml` | 1064 | `6c20216b7db622ca…` |
| `res/drawable/$ic_msb__1.xml` | 1064 | `c73ad5d70697dd58…` |
| `res/drawable/$ic_msb__2.xml` | 764 | `931043111bc3534a…` |
| `res/drawable/$ic_msb__3.xml` | 764 | `4006ff91aabafb66…` |
| `res/drawable/$ic_msb_card_logo__0.xml` | 1064 | `6c20216b7db622ca…` |
| `res/drawable/$ic_msb_card_logo__1.xml` | 1064 | `c73ad5d70697dd58…` |
| `res/drawable/$ic_msb_card_logo__2.xml` | 764 | `931043111bc3534a…` |
| `res/drawable/$ic_msb_card_logo__3.xml` | 764 | `4006ff91aabafb66…` |
| `res/drawable/$ic_msb_full_brand__0.xml` | 1304 | `82ef8fe8213be7ba…` |
| `res/drawable/$ic_msb_full_brand__1.xml` | 1404 | `20c9a8fde8467ca4…` |
| `res/drawable/$ic_msb_full_brand__2.xml` | 904 | `2a1d27852c87dd7e…` |
| `res/drawable/$ic_msb_full_brand__3.xml` | 904 | `f893b3d812e688d8…` |
| `res/drawable/$ic_msb_full_brand_card_logo__0.xml` | 1104 | `46e9f0d6225fc0cc…` |
| `res/drawable/$ic_msb_full_brand_card_logo__1.xml` | 1104 | `3a099d6d59eb0504…` |
| `res/drawable/$ic_msb_full_brand_card_logo__2.xml` | 904 | `176e3cedd2f25af3…` |
| `res/drawable/$ic_nasb__0.xml` | 764 | `6e93b4df6dbfa92f…` |
| `res/drawable/$ic_nasb__1.xml` | 864 | `e733318a0fb26d52…` |
| `res/drawable/$ic_nasb__2.xml` | 764 | `236cbef4a389e681…` |
| `res/drawable/$ic_nasb__3.xml` | 864 | `aedf644867ea5db3…` |
| `res/drawable/$ic_nasb__4.xml` | 764 | `710d466bf1ea4ad6…` |
| `res/drawable/$ic_nasb__5.xml` | 864 | `30538ee503be6ed6…` |
| `res/drawable/$ic_nasb__6.xml` | 764 | `a9a5ba88ed4dd94e…` |
| `res/drawable/$ic_nasb__7.xml` | 864 | `dcdc4d5ee1c0e7fa…` |
| `res/drawable/$ic_nasb__8.xml` | 764 | `62e409bc17b7ecc4…` |
| `res/drawable/$ic_nasb__9.xml` | 864 | `5b07f9a3bfc02034…` |
| `res/drawable/$ic_nasb_full_brand__0.xml` | 764 | `068056fe3cb93f8a…` |
| `res/drawable/$ic_nasb_full_brand__1.xml` | 864 | `70c56b2f7b991476…` |
| `res/drawable/$ic_nasb_full_brand__2.xml` | 764 | `652138ac30320a16…` |
| `res/drawable/$ic_nasb_full_brand__3.xml` | 864 | `5a8e877b9d36723b…` |
| `res/drawable/$ic_nasb_full_brand__4.xml` | 764 | `7627881bbbbbd630…` |
| `res/drawable/$ic_nasb_full_brand__5.xml` | 864 | `6119788d4419848f…` |
| `res/drawable/$ic_nasb_full_brand__6.xml` | 764 | `f2a411a894f90dd3…` |
| `res/drawable/$ic_nasb_full_brand__7.xml` | 864 | `ba136066abc2f026…` |
| `res/drawable/$ic_nasb_full_brand__8.xml` | 764 | `bebe9d880fce6e85…` |
| `res/drawable/$ic_nasb_full_brand__9.xml` | 864 | `592e43f4518432cb…` |
| `res/drawable/$ic_ncb__0.xml` | 788 | `e4b2dbc9451e5244…` |
| `res/drawable/$ic_ncb__1.xml` | 1088 | `2356832d080f2fb2…` |
| `res/drawable/$ic_ncb__2.xml` | 788 | `b8f64a5391de2c5b…` |
| `res/drawable/$ic_ncb__3.xml` | 788 | `d0dea8c0094eeaa6…` |
| `res/drawable/$ic_ncb__4.xml` | 788 | `46ee6e3974b2096e…` |
| `res/drawable/$ic_ncb__5.xml` | 788 | `fd20a0c28ec9b3c0…` |
| `res/drawable/$ic_ncb_full_brand__0.xml` | 788 | `d6bc3a4562eb77d7…` |
| `res/drawable/$ic_ncb_full_brand__1.xml` | 1088 | `b0838f7b92d71bdb…` |
| `res/drawable/$ic_ncb_full_brand__2.xml` | 788 | `b7af49a5f9e1e0f8…` |
| `res/drawable/$ic_ncb_full_brand__3.xml` | 788 | `97d4f93791effbe1…` |
| `res/drawable/$ic_ncb_full_brand__4.xml` | 788 | `ade63e58c258528e…` |
| `res/drawable/$ic_ncb_full_brand__5.xml` | 788 | `73008b132aba939d…` |
| `res/drawable/$ic_newsfeed_ota_product_location__0.xml` | 788 | `a7245d32c080b15c…` |
| `res/drawable/$ic_newsfeed_ota_product_location__1.xml` | 788 | `92bba4570ff4fcd2…` |
| `res/drawable/$ic_newsfeed_ota_product_location__2.xml` | 788 | `b1d425fc9a33cfca…` |
| `res/drawable/$ic_newsfeed_ota_product_location__3.xml` | 788 | `35cceca48a025dcc…` |
| `res/drawable/$ic_no_results_found__0.xml` | 788 | `07bc6827a0cf534c…` |
| `res/drawable/$ic_no_results_found__1.xml` | 788 | `303c048247d19fb5…` |
| `res/drawable/$ic_no_results_found__10.xml` | 1088 | `23f30c2199728e45…` |
| `res/drawable/$ic_no_results_found__11.xml` | 1288 | `c3ffc3b6cf4a41fe…` |
| `res/drawable/$ic_no_results_found__12.xml` | 888 | `e6d576dbf5494701…` |
| `res/drawable/$ic_no_results_found__13.xml` | 888 | `11511685f0961789…` |
| `res/drawable/$ic_no_results_found__14.xml` | 1488 | `96a83e7a26860c4d…` |
| `res/drawable/$ic_no_results_found__15.xml` | 988 | `a79200f18a3e63a8…` |
| `res/drawable/$ic_no_results_found__16.xml` | 988 | `97647edf5f93a31b…` |
| `res/drawable/$ic_no_results_found__17.xml` | 1288 | `740c3f24827dcd35…` |
| `res/drawable/$ic_no_results_found__2.xml` | 1688 | `04b67a250afe01d1…` |
| `res/drawable/$ic_no_results_found__3.xml` | 788 | `95afb92ccc2c4a8f…` |
| `res/drawable/$ic_no_results_found__4.xml` | 1788 | `b89d404a8e7317c4…` |
| `res/drawable/$ic_no_results_found__5.xml` | 1488 | `79b7f3d1a3bc7688…` |
| `res/drawable/$ic_no_results_found__6.xml` | 788 | `64f0d79f0bd39a4c…` |
| `res/drawable/$ic_no_results_found__7.xml` | 988 | `eb2b37801abec69f…` |
| `res/drawable/$ic_no_results_found__8.xml` | 788 | `6069393a0a73db89…` |
| `res/drawable/$ic_no_results_found__9.xml` | 988 | `76e34d09f7b06515…` |
| `res/drawable/$ic_notify_120__0.xml` | 788 | `b1a5d9e1d563a7c8…` |
| `res/drawable/$ic_notify_120__1.xml` | 788 | `32a2964d37d5aea5…` |
| `res/drawable/$ic_notify__0.xml` | 788 | `2c23f0c30570012b…` |
| `res/drawable/$ic_notify__1.xml` | 788 | `2db00a3811a31279…` |
| `res/drawable/$ic_nvb__0.xml` | 788 | `e4b2dbc9451e5244…` |
| `res/drawable/$ic_nvb__1.xml` | 1088 | `2356832d080f2fb2…` |
| `res/drawable/$ic_nvb__2.xml` | 788 | `b8f64a5391de2c5b…` |
| `res/drawable/$ic_nvb__3.xml` | 788 | `d0dea8c0094eeaa6…` |
| `res/drawable/$ic_nvb__4.xml` | 788 | `46ee6e3974b2096e…` |
| `res/drawable/$ic_nvb__5.xml` | 788 | `fd20a0c28ec9b3c0…` |
| `res/drawable/$ic_nvb_full_brand__0.xml` | 788 | `d6bc3a4562eb77d7…` |
| `res/drawable/$ic_nvb_full_brand__1.xml` | 1088 | `b0838f7b92d71bdb…` |
| `res/drawable/$ic_nvb_full_brand__2.xml` | 788 | `b7af49a5f9e1e0f8…` |
| `res/drawable/$ic_nvb_full_brand__3.xml` | 788 | `97d4f93791effbe1…` |
| `res/drawable/$ic_nvb_full_brand__4.xml` | 788 | `ade63e58c258528e…` |
| `res/drawable/$ic_nvb_full_brand__5.xml` | 788 | `73008b132aba939d…` |
| `res/drawable/$ic_pgb__0.xml` | 764 | `14d2b174b829586c…` |
| `res/drawable/$ic_pgb_full_brand__0.xml` | 764 | `c75501258a25848e…` |
| `res/drawable/$ic_processing_3__0.xml` | 788 | `0a2783c8a496e237…` |
| `res/drawable/$ic_profile_ekyc__0.xml` | 788 | `39858e9adf816e4f…` |
| `res/drawable/$ic_pvcb__0.xml` | 788 | `adbf40c2e16b5af5…` |
| `res/drawable/$ic_pvcb__1.xml` | 788 | `8b81fc22d494b18d…` |
| `res/drawable/$ic_pvcb__2.xml` | 788 | `7ad62ba54bb3ef09…` |
| `res/drawable/$ic_pvcb__3.xml` | 788 | `718f46c452b3f83d…` |
| `res/drawable/$ic_pvcb_full_brand__0.xml` | 788 | `ae27356e0136fbdd…` |
| `res/drawable/$ic_pvcb_full_brand__1.xml` | 788 | `8e9ac0daf81f8dfb…` |
| `res/drawable/$ic_pvcb_full_brand__2.xml` | 788 | `07fe49997f2c5075…` |
| `res/drawable/$ic_pvcb_full_brand__3.xml` | 788 | `5e97fdf15fcb25cd…` |
| `res/drawable/$ic_recommend_system_gift__0.xml` | 788 | `460dc481ab9d7a85…` |
| `res/drawable/$ic_recommend_system_gift__1.xml` | 764 | `111f034339b5e8cc…` |
| `res/drawable/$ic_recommend_system_gift__10.xml` | 788 | `0beb2956c5ac8ec7…` |
| `res/drawable/$ic_recommend_system_gift__11.xml` | 788 | `b9f49e484032edc1…` |
| `res/drawable/$ic_recommend_system_gift__12.xml` | 788 | `62b69c1b0eb3dee7…` |
| `res/drawable/$ic_recommend_system_gift__13.xml` | 788 | `372663182201503b…` |
| `res/drawable/$ic_recommend_system_gift__14.xml` | 788 | `32d6b2599f23596e…` |
| `res/drawable/$ic_recommend_system_gift__15.xml` | 788 | `cc2f039865b789f5…` |
| `res/drawable/$ic_recommend_system_gift__16.xml` | 988 | `1b8bd2c8afffd829…` |
| `res/drawable/$ic_recommend_system_gift__17.xml` | 788 | `186517a008c2b878…` |
| `res/drawable/$ic_recommend_system_gift__18.xml` | 988 | `13bd304299d9158d…` |
| `res/drawable/$ic_recommend_system_gift__19.xml` | 788 | `6057e843ab3d0c1f…` |
| `res/drawable/$ic_recommend_system_gift__2.xml` | 788 | `1d50345ff04c5570…` |
| `res/drawable/$ic_recommend_system_gift__20.xml` | 988 | `5d9ac67e6140a717…` |
| `res/drawable/$ic_recommend_system_gift__21.xml` | 788 | `134685ede18ec5f7…` |
| `res/drawable/$ic_recommend_system_gift__22.xml` | 988 | `428b78adb8dd7774…` |
| `res/drawable/$ic_recommend_system_gift__23.xml` | 788 | `c66f93f967a4496a…` |
| `res/drawable/$ic_recommend_system_gift__3.xml` | 788 | `a28fcc0d10c2ada1…` |
| `res/drawable/$ic_recommend_system_gift__4.xml` | 888 | `a23d80d38e4d1e56…` |
| `res/drawable/$ic_recommend_system_gift__5.xml` | 764 | `e56214dcda61171d…` |
| `res/drawable/$ic_recommend_system_gift__6.xml` | 888 | `9471b9a266a14773…` |
| `res/drawable/$ic_recommend_system_gift__7.xml` | 788 | `f54571d2e4f96c40…` |
| `res/drawable/$ic_recommend_system_gift__8.xml` | 788 | `483740d76642c871…` |
| `res/drawable/$ic_recommend_system_gift__9.xml` | 788 | `887c8a6aebdd5026…` |
| `res/drawable/$ic_reform_kyc_state__0.xml` | 788 | `9b6b4918283e8a9e…` |
| `res/drawable/$ic_reform_kyc_state__1.xml` | 788 | `466ee8eecd6dcb00…` |
| `res/drawable/$ic_reform_kyc_state__10.xml` | 788 | `02584e9bb9200d6f…` |
| `res/drawable/$ic_reform_kyc_state__11.xml` | 788 | `a1f971e893323945…` |
| `res/drawable/$ic_reform_kyc_state__12.xml` | 788 | `0b7399d482b29e74…` |
| `res/drawable/$ic_reform_kyc_state__13.xml` | 788 | `3b70ea0badb7d165…` |
| `res/drawable/$ic_reform_kyc_state__14.xml` | 788 | `42686f87ec76258f…` |
| `res/drawable/$ic_reform_kyc_state__15.xml` | 864 | `e0e1d5923dbabbfc…` |
| `res/drawable/$ic_reform_kyc_state__16.xml` | 1088 | `7584bd395e364b2b…` |
| `res/drawable/$ic_reform_kyc_state__17.xml` | 888 | `427237f4896b3a19…` |
| `res/drawable/$ic_reform_kyc_state__18.xml` | 788 | `ac305975f3a9d713…` |
| `res/drawable/$ic_reform_kyc_state__19.xml` | 788 | `3ae402d6a943505a…` |
| `res/drawable/$ic_reform_kyc_state__2.xml` | 788 | `17612c1337619718…` |
| `res/drawable/$ic_reform_kyc_state__3.xml` | 788 | `c5e2b9279a812a69…` |
| `res/drawable/$ic_reform_kyc_state__4.xml` | 788 | `9714005de98d1ceb…` |
| `res/drawable/$ic_reform_kyc_state__5.xml` | 788 | `25e3f2e4c7c8756d…` |
| `res/drawable/$ic_reform_kyc_state__6.xml` | 788 | `b64d3abb3633d0e9…` |
| `res/drawable/$ic_reform_kyc_state__7.xml` | 788 | `90ce0fb9a9a65e4d…` |
| `res/drawable/$ic_reform_kyc_state__8.xml` | 788 | `37aeaea7aef72c8b…` |
| `res/drawable/$ic_reform_kyc_state__9.xml` | 788 | `2b71d7ea69cd39b4…` |
| `res/drawable/$ic_reset_pin_pending__0.xml` | 788 | `db030896e9a96e6e…` |
| `res/drawable/$ic_reset_pin_pending__1.xml` | 788 | `473d6cb1da27d66f…` |
| `res/drawable/$ic_reset_pin_pending__10.xml` | 788 | `03ada72751a5d1f5…` |
| `res/drawable/$ic_reset_pin_pending__11.xml` | 788 | `1ed29db5b1656006…` |
| `res/drawable/$ic_reset_pin_pending__12.xml` | 888 | `b3a3f8c99ee06a28…` |
| `res/drawable/$ic_reset_pin_pending__13.xml` | 788 | `ded99cf881506f0c…` |
| `res/drawable/$ic_reset_pin_pending__14.xml` | 788 | `3cfd2366c3e3a3b1…` |
| `res/drawable/$ic_reset_pin_pending__15.xml` | 788 | `c6c2335427fa0da3…` |
| `res/drawable/$ic_reset_pin_pending__16.xml` | 788 | `351b816e70009607…` |
| `res/drawable/$ic_reset_pin_pending__17.xml` | 864 | `3f03cb07a23071f9…` |
| `res/drawable/$ic_reset_pin_pending__2.xml` | 788 | `c9fe7d9b1f23d6d5…` |
| `res/drawable/$ic_reset_pin_pending__3.xml` | 788 | `9bc2ffc070c914b4…` |
| `res/drawable/$ic_reset_pin_pending__4.xml` | 788 | `46043aecaba60b31…` |
| `res/drawable/$ic_reset_pin_pending__5.xml` | 788 | `2c59b16c0ba71d8a…` |
| `res/drawable/$ic_reset_pin_pending__6.xml` | 788 | `f624ba656c3505f0…` |
| `res/drawable/$ic_reset_pin_pending__7.xml` | 788 | `1e38ece7ad6aed25…` |
| `res/drawable/$ic_reset_pin_pending__8.xml` | 788 | `9f72efa7c0800db8…` |
| `res/drawable/$ic_reset_pin_pending__9.xml` | 788 | `350eb2a0572e17ab…` |
| `res/drawable/$ic_rp_coin_v3__0.xml` | 788 | `6b7485e413e57697…` |
| `res/drawable/$ic_rp_coin_v3__1.xml` | 788 | `758800456cc9a12f…` |
| `res/drawable/$ic_rp_kyc__0.xml` | 788 | `6cc61bace71f0131…` |
| `res/drawable/$ic_rp_kyc__1.xml` | 888 | `9f02118f66c652b6…` |
| `res/drawable/$ic_saving__0.xml` | 788 | `77dc3d10392cc8e1…` |
| `res/drawable/$ic_saving__1.xml` | 788 | `1b3246b054c0d03f…` |
| `res/drawable/$ic_scan_qr_sample__0.xml` | 764 | `bc30697cd72d4afb…` |
| `res/drawable/$ic_scan_qr_sample__1.xml` | 864 | `2ff6d0c0634313b8…` |
| `res/drawable/$ic_scan_qr_sample__2.xml` | 764 | `44e025e1aaf7c4cd…` |
| `res/drawable/$ic_scan_qr_sample__3.xml` | 788 | `5f107a9f4ab2821f…` |
| `res/drawable/$ic_scan_qr_sample__4.xml` | 788 | `22dbdc118b3e28d2…` |
| `res/drawable/$ic_search_empty__0.xml` | 788 | `bd59cbec8f3ff0d8…` |
| `res/drawable/$ic_search_empty__1.xml` | 788 | `9868ea07106d2560…` |
| `res/drawable/$ic_search_null__0.xml` | 788 | `2ebc1480268eb150…` |
| `res/drawable/$ic_search_null__1.xml` | 788 | `f4cc3486e05c345a…` |
| `res/drawable/$ic_security__0.xml` | 788 | `1dc632dbfcedfb9e…` |
| `res/drawable/$ic_security__1.xml` | 788 | `c933280d4636e36e…` |
| `res/drawable/$ic_security__2.xml` | 788 | `2f01c48654f16680…` |
| `res/drawable/$ic_service_coin__0.xml` | 788 | `cc3521cabeb4789c…` |
| `res/drawable/$ic_service_maintain__0.xml` | 788 | `e92f395c297a38d5…` |
| `res/drawable/$ic_service_maintain__1.xml` | 788 | `4ad8e9f1ef810249…` |
| `res/drawable/$ic_service_maintenance__0.xml` | 788 | `b952afa64a72a9a6…` |
| `res/drawable/$ic_service_maintenance__1.xml` | 888 | `b54e86872ffc2578…` |
| `res/drawable/$ic_service_maintenance__10.xml` | 888 | `b28dfec7f5dba842…` |
| `res/drawable/$ic_service_maintenance__2.xml` | 788 | `dda2bd30e294370c…` |
| `res/drawable/$ic_service_maintenance__3.xml` | 788 | `36944977abad63ae…` |
| `res/drawable/$ic_service_maintenance__4.xml` | 788 | `a20b5b781056e707…` |
| `res/drawable/$ic_service_maintenance__5.xml` | 788 | `53217244b9deb537…` |
| `res/drawable/$ic_service_maintenance__6.xml` | 788 | `88114382b76fc9d9…` |
| `res/drawable/$ic_service_maintenance__7.xml` | 788 | `2b4c3b35bb33df0f…` |
| `res/drawable/$ic_service_maintenance__8.xml` | 788 | `3f4c7158b557254f…` |
| `res/drawable/$ic_service_maintenance__9.xml` | 1188 | `90d1dc3b349402f0…` |
| `res/drawable/$ic_shbvn__0.xml` | 764 | `f21ce374909cb4c6…` |
| `res/drawable/$ic_shbvn__1.xml` | 1364 | `b588c7cd6d271aec…` |
| `res/drawable/$ic_shbvn_full_brand__0.xml` | 764 | `0abdfebec6864db7…` |
| `res/drawable/$ic_shbvn_full_brand__1.xml` | 1364 | `dc54882053ac15a7…` |
| `res/drawable/$ic_status_acc_closed__0.xml` | 788 | `2fbd8597644c5418…` |
| `res/drawable/$ic_status_acc_closed__1.xml` | 788 | `e487decf869edc32…` |
| `res/drawable/$ic_status_acc_closed__2.xml` | 788 | `0873d3ca2c73449b…` |
| `res/drawable/$ic_status_acc_closed__3.xml` | 888 | `430185b5335d345d…` |
| `res/drawable/$ic_status_acc_closed__4.xml` | 788 | `d0c2074cc3d5a63e…` |
| `res/drawable/$ic_status_acc_closed__5.xml` | 788 | `6295cae1ff733bee…` |
| `res/drawable/$ic_status_acc_closed__6.xml` | 788 | `4699bc602c3dd6f0…` |
| `res/drawable/$ic_sweep_background__0.xml` | 788 | `4857a671341bfc0d…` |
| `res/drawable/$ic_sweep_background__1.xml` | 764 | `ec9d8fabb5dc5456…` |
| `res/drawable/$ic_sweep_background__2.xml` | 1088 | `255cb257c1f284cf…` |
| `res/drawable/$ic_sweep_background__3.xml` | 788 | `3c9c08cffbc08392…` |
| `res/drawable/$ic_sweep_background__4.xml` | 788 | `d7cb63cec4a18192…` |
| `res/drawable/$ic_sweep_background__5.xml` | 788 | `e80a8c301b363d36…` |
| `res/drawable/$ic_sweep_background__6.xml` | 788 | `0357897d57229fd2…` |
| `res/drawable/$ic_sweep_background__7.xml` | 788 | `04dea5ef20c03edb…` |
| `res/drawable/$ic_sweep_now__0.xml` | 788 | `339875b16c29709f…` |
| `res/drawable/$ic_system_announcement_reminder__0.xml` | 788 | `dd16bbfdafa099b1…` |
| `res/drawable/$ic_taxi_diembatdau__0.xml` | 904 | `96aefa1ca8eab077…` |
| `res/drawable/$ic_taxi_diembatdau__1.xml` | 792 | `685882d136f53e02…` |
| `res/drawable/$ic_taxi_diemcuoi__0.xml` | 904 | `96aefa1ca8eab077…` |
| `res/drawable/$ic_taxi_diemcuoi__1.xml` | 792 | `6be7f0a501a102df…` |
| `res/drawable/$ic_taxi_diemcuoi_base__0.xml` | 904 | `96aefa1ca8eab077…` |
| `res/drawable/$ic_taxi_diemcuoi_path__0.xml` | 792 | `4f7d30607dcf323f…` |
| `res/drawable/$ic_topup_header_background__0.xml` | 788 | `7bae33a6b1ca549f…` |
| `res/drawable/$ic_topup_withdraw_background__0.xml` | 788 | `09101c1742f6c154…` |
| `res/drawable/$ic_topup_withdraw_background__1.xml` | 788 | `130191b7a6cc60e9…` |
| `res/drawable/$ic_topup_withdraw_background__2.xml` | 788 | `63dc321e9260444f…` |
| `res/drawable/$ic_topup_withdraw_background_main__0.xml` | 788 | `d272ad45dc97401b…` |
| `res/drawable/$ic_toro_kyc__0.xml` | 888 | `bb1bb84e819f3fbc…` |
| `res/drawable/$ic_toro_kyc__1.xml` | 888 | `d40ea49bfea30231…` |
| `res/drawable/$ic_touchid__0.xml` | 788 | `75d5cf88fbae0c37…` |
| `res/drawable/$ic_touchid__1.xml` | 788 | `52170217df0c95f9…` |
| `res/drawable/$ic_tpb__0.xml` | 788 | `e99af75d95d16cd9…` |
| `res/drawable/$ic_tpb__1.xml` | 788 | `4a4d1cde752bd76b…` |
| `res/drawable/$ic_tpb__2.xml` | 788 | `7d74b239b7900208…` |
| `res/drawable/$ic_tpb_full_brand__0.xml` | 788 | `e44b96de6324bf0f…` |
| `res/drawable/$ic_tpb_full_brand__1.xml` | 788 | `2317d41464c189ec…` |
| `res/drawable/$ic_tpb_full_brand__2.xml` | 788 | `13a87bb32da8d2ad…` |
| `res/drawable/$ic_trans_failed__0.xml` | 788 | `af328c4c40a08622…` |
| `res/drawable/$ic_trans_processing__0.xml` | 788 | `0a2783c8a496e237…` |
| `res/drawable/$ic_trans_success__0.xml` | 788 | `593807dec3a4bbb0…` |
| `res/drawable/$ic_transfer_zalo_notify__0.xml` | 788 | `88251c4fff1b4577…` |
| `res/drawable/$ic_vcb__0.xml` | 888 | `9ad6dcabf5af5fba…` |
| `res/drawable/$ic_vcb__1.xml` | 888 | `7272e971f0c26ba1…` |
| `res/drawable/$ic_vcb_card_logo__0.xml` | 888 | `0deb62cb97f52df4…` |
| `res/drawable/$ic_vcb_card_logo__1.xml` | 888 | `7eca86d7f5c6eadc…` |
| `res/drawable/$ic_vcb_full_brand__0.xml` | 892 | `eed935ad461a8112…` |
| `res/drawable/$ic_vcb_full_brand__1.xml` | 892 | `8305de2d9305a580…` |
| `res/drawable/$ic_vccb__0.xml` | 788 | `a0e8f430f052f1b3…` |
| `res/drawable/$ic_vccb__1.xml` | 788 | `1fa50e38a4f66722…` |
| `res/drawable/$ic_vccb_card_logo__0.xml` | 792 | `bb40fa2fc9b475a1…` |
| `res/drawable/$ic_vccb_card_logo__1.xml` | 792 | `b11fefbbeb6b8f25…` |
| `res/drawable/$ic_vccb_full_brand__0.xml` | 1092 | `3483e5da7b21f8be…` |
| `res/drawable/$ic_vccb_full_brand__1.xml` | 1092 | `c1ed901ea27f926a…` |
| `res/drawable/$ic_vccb_full_brand__2.xml` | 1092 | `fad93bdde49c0184…` |
| `res/drawable/$ic_vccb_full_brand__3.xml` | 1092 | `f176c0d0a52afe72…` |
| `res/drawable/$ic_vector_acc_saving__0.xml` | 788 | `7673d1bb70d70c70…` |
| `res/drawable/$ic_vector_acc_saving__1.xml` | 788 | `b3ab2fb69f3a923f…` |
| `res/drawable/$ic_vector_bg_link_bank_button_fm__0.xml` | 788 | `c05558fc5e915224…` |
| `res/drawable/$ic_vector_bg_oao_button_fm__0.xml` | 788 | `4e8ecef1e4f9cbd1…` |
| `res/drawable/$ic_vector_bg_zalopay_balance_fm__0.xml` | 788 | `c2ddda0d604e7479…` |
| `res/drawable/$ic_vector_component_setting__0.xml` | 788 | `fd3d8ada37acb6ef…` |
| `res/drawable/$ic_vector_component_setting__1.xml` | 764 | `497cd4841f0404f8…` |
| `res/drawable/$ic_vector_component_setting__10.xml` | 1388 | `859057ce95a35765…` |
| `res/drawable/$ic_vector_component_setting__11.xml` | 1188 | `7301ec3ccab8d7cb…` |
| `res/drawable/$ic_vector_component_setting__12.xml` | 1688 | `5eea11994fa0475c…` |
| `res/drawable/$ic_vector_component_setting__13.xml` | 988 | `84b78fab9bc81b6d…` |
| `res/drawable/$ic_vector_component_setting__14.xml` | 788 | `1d0b5cf5ed987b5b…` |
| `res/drawable/$ic_vector_component_setting__15.xml` | 764 | `7bf261b458005219…` |
| `res/drawable/$ic_vector_component_setting__16.xml` | 788 | `57ea47995ed828b1…` |
| `res/drawable/$ic_vector_component_setting__17.xml` | 888 | `68af8e909e6faeda…` |
| `res/drawable/$ic_vector_component_setting__18.xml` | 788 | `b121c90d598ceffe…` |
| `res/drawable/$ic_vector_component_setting__19.xml` | 1188 | `9662d59f24b411c6…` |
| `res/drawable/$ic_vector_component_setting__2.xml` | 888 | `a1f78454534b4f08…` |
| `res/drawable/$ic_vector_component_setting__20.xml` | 888 | `7efd42aa2e99eab4…` |
| `res/drawable/$ic_vector_component_setting__21.xml` | 788 | `935b56ada782479f…` |
| `res/drawable/$ic_vector_component_setting__22.xml` | 1088 | `5064a21922102b00…` |
| `res/drawable/$ic_vector_component_setting__3.xml` | 988 | `e91155eef9438b92…` |
| `res/drawable/$ic_vector_component_setting__4.xml` | 1788 | `3fa2744241abb940…` |
| `res/drawable/$ic_vector_component_setting__5.xml` | 1388 | `f1f85f6d66ee6f56…` |
| `res/drawable/$ic_vector_component_setting__6.xml` | 788 | `e7ab2fb60f9ade4d…` |
| `res/drawable/$ic_vector_component_setting__7.xml` | 788 | `ab596c8298099730…` |
| `res/drawable/$ic_vector_component_setting__8.xml` | 1488 | `6516c3f6b4fc633a…` |
| `res/drawable/$ic_vector_component_setting__9.xml` | 1288 | `e7d0c7ed8c183ecd…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__0.xml` | 788 | `209a8cc0683c5d61…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__1.xml` | 788 | `3a155b63490a27b4…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__2.xml` | 788 | `c0cbaf277406804d…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__3.xml` | 788 | `fbc17d517e6560d6…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__4.xml` | 1288 | `d6da11adbc8b98b1…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__5.xml` | 888 | `70329ad399abf5c9…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__6.xml` | 888 | `04edd9358257d9a5…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__7.xml` | 1488 | `2a2e95c779fa757c…` |
| `res/drawable/$ic_vector_empty_funding_source_fm__8.xml` | 988 | `c994da7d8dcf238c…` |
| `res/drawable/$ic_vector_face_guide_failed__0.xml` | 788 | `423914f8860fafc3…` |
| `res/drawable/$ic_vector_face_guide_failed__1.xml` | 788 | `77024dd7cc72256c…` |
| `res/drawable/$ic_vector_face_guide_failed__2.xml` | 788 | `a21415e43967fdd4…` |
| `res/drawable/$ic_vector_face_guide_success__0.xml` | 788 | `329f3691aebb2907…` |
| `res/drawable/$ic_vector_general_disconnect__0.xml` | 788 | `490f2d35800286ad…` |
| `res/drawable/$ic_vector_general_disconnect__1.xml` | 788 | `8c529806b256a4fc…` |
| `res/drawable/$ic_vector_general_disconnect__10.xml` | 764 | `4f1790bafa8056b5…` |
| `res/drawable/$ic_vector_general_disconnect__11.xml` | 788 | `b6b22501b83bf634…` |
| `res/drawable/$ic_vector_general_disconnect__12.xml` | 788 | `c5c08540dc91870b…` |
| `res/drawable/$ic_vector_general_disconnect__13.xml` | 788 | `fabadf2e551fc0b8…` |
| `res/drawable/$ic_vector_general_disconnect__2.xml` | 788 | `8f5c846bfc8eb845…` |
| `res/drawable/$ic_vector_general_disconnect__3.xml` | 788 | `3686672fdaed938e…` |
| `res/drawable/$ic_vector_general_disconnect__4.xml` | 788 | `e308dd0c30631762…` |
| `res/drawable/$ic_vector_general_disconnect__5.xml` | 1488 | `7e8b40c6b32713b7…` |
| `res/drawable/$ic_vector_general_disconnect__6.xml` | 1488 | `8610a1fb42716b23…` |
| `res/drawable/$ic_vector_general_disconnect__7.xml` | 1488 | `ba65a6084390e52c…` |
| `res/drawable/$ic_vector_general_disconnect__8.xml` | 1488 | `054f9a7f3f498c64…` |
| `res/drawable/$ic_vector_general_disconnect__9.xml` | 1488 | `927e328765eb4073…` |
| `res/drawable/$ic_vector_general_system_error__0.xml` | 788 | `c0140a2bdbffd334…` |
| `res/drawable/$ic_vector_general_system_error__1.xml` | 788 | `53041adc25642393…` |
| `res/drawable/$ic_vector_general_system_error__10.xml` | 788 | `46ecbb8c6b815b59…` |
| `res/drawable/$ic_vector_general_system_error__11.xml` | 788 | `07652d577d842c93…` |
| `res/drawable/$ic_vector_general_system_error__12.xml` | 788 | `c4001445b3c973a1…` |
| `res/drawable/$ic_vector_general_system_error__13.xml` | 788 | `690067c588feacca…` |
| `res/drawable/$ic_vector_general_system_error__14.xml` | 788 | `e5b676f3df68ae88…` |
| `res/drawable/$ic_vector_general_system_error__2.xml` | 788 | `3194d3c45e9e1b14…` |
| `res/drawable/$ic_vector_general_system_error__3.xml` | 788 | `d781252e90e2984d…` |
| `res/drawable/$ic_vector_general_system_error__4.xml` | 788 | `ee2e517f2663c295…` |
| `res/drawable/$ic_vector_general_system_error__5.xml` | 788 | `0bcce92981526334…` |
| `res/drawable/$ic_vector_general_system_error__6.xml` | 788 | `8136d4fbc16a0720…` |
| `res/drawable/$ic_vector_general_system_error__7.xml` | 788 | `e7a1b206dfbed175…` |
| `res/drawable/$ic_vector_general_system_error__8.xml` | 788 | `f34879f3fa9ab22b…` |
| `res/drawable/$ic_vector_general_system_error__9.xml` | 788 | `84975a72f4da11cb…` |
| `res/drawable/$ic_vector_guide_failed__0.xml` | 788 | `423914f8860fafc3…` |
| `res/drawable/$ic_vector_guide_failed__1.xml` | 788 | `77024dd7cc72256c…` |
| `res/drawable/$ic_vector_guide_failed__2.xml` | 788 | `a21415e43967fdd4…` |
| `res/drawable/$ic_vector_guide_success__0.xml` | 788 | `329f3691aebb2907…` |
| `res/drawable/$ic_vector_home_banner_no_cam__0.xml` | 788 | `9cc0508591a73ac2…` |
| `res/drawable/$ic_vector_home_banner_no_cam__1.xml` | 788 | `feb3197a4287798d…` |
| `res/drawable/$ic_vector_info_changes__0.xml` | 864 | `c3f4820e3ff295af…` |
| `res/drawable/$ic_vector_info_changes__1.xml` | 888 | `614d69c598a67ce6…` |
| `res/drawable/$ic_vector_info_changes__2.xml` | 864 | `122f29fa70d8084d…` |
| `res/drawable/$ic_vector_info_changes__3.xml` | 888 | `ef1b6816f55af6d7…` |
| `res/drawable/$ic_vector_info_changes__4.xml` | 788 | `2468e9e169f55df6…` |
| `res/drawable/$ic_vector_info_changes__5.xml` | 788 | `abb45cc55e131960…` |
| `res/drawable/$ic_vector_info_changes__6.xml` | 788 | `2cae23d51486baad…` |
| `res/drawable/$ic_vector_info_changes__7.xml` | 888 | `9ccd619edb943cfa…` |
| `res/drawable/$ic_vector_info_changes__8.xml` | 788 | `94d6c96d6bf9558a…` |
| `res/drawable/$ic_vector_liveness_notification__0.xml` | 864 | `49f32352bbf8748b…` |
| `res/drawable/$ic_vector_liveness_notification__1.xml` | 788 | `7253518f07593450…` |
| `res/drawable/$ic_vector_liveness_notification__2.xml` | 788 | `b3686c65a9ca7dcf…` |
| `res/drawable/$ic_vector_liveness_notification__3.xml` | 788 | `2eefcd702cf99053…` |
| `res/drawable/$ic_vector_liveness_notification__4.xml` | 788 | `d9b55001754502d9…` |
| `res/drawable/$ic_vector_liveness_selfie__0.xml` | 788 | `5ba5616073b8c6bf…` |
| `res/drawable/$ic_vector_processing_status__0.xml` | 788 | `fc6c910ec2187026…` |
| `res/drawable/$ic_vector_processing_status__1.xml` | 788 | `ca987cc1e6222e72…` |
| `res/drawable/$ic_vector_processing_status__10.xml` | 788 | `d60620f16f0320c9…` |
| `res/drawable/$ic_vector_processing_status__11.xml` | 788 | `37d50bd0e5fd6af1…` |
| `res/drawable/$ic_vector_processing_status__12.xml` | 888 | `571be14d674ada09…` |
| `res/drawable/$ic_vector_processing_status__13.xml` | 788 | `def02b8df482d8c2…` |
| `res/drawable/$ic_vector_processing_status__14.xml` | 788 | `8e97a82ae451f3f1…` |
| `res/drawable/$ic_vector_processing_status__15.xml` | 788 | `255fe92610a7f395…` |
| `res/drawable/$ic_vector_processing_status__16.xml` | 788 | `293d11a61e9f12bc…` |
| `res/drawable/$ic_vector_processing_status__2.xml` | 788 | `df280c931ab14f1c…` |
| `res/drawable/$ic_vector_processing_status__3.xml` | 788 | `b7193e02fd163ca0…` |
| `res/drawable/$ic_vector_processing_status__4.xml` | 788 | `88c45685c4f51d1e…` |
| `res/drawable/$ic_vector_processing_status__5.xml` | 788 | `ebdf71e386195ff2…` |
| `res/drawable/$ic_vector_processing_status__6.xml` | 788 | `2db3676f6026ef58…` |
| `res/drawable/$ic_vector_processing_status__7.xml` | 788 | `903a7d2ee7831fb3…` |
| `res/drawable/$ic_vector_processing_status__8.xml` | 788 | `e5f8507cdb09c700…` |
| `res/drawable/$ic_vector_processing_status__9.xml` | 788 | `29fcd4002f8c6789…` |
| `res/drawable/$ic_vector_rectangle_60__0.xml` | 788 | `4ef44a1fc6044296…` |
| `res/drawable/$ic_vector_rectangle_header_screen__0.xml` | 788 | `26bb8ba45b116b8a…` |
| `res/drawable/$ic_vector_selefie_example__0.xml` | 788 | `82569e4aa0315976…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__0.xml` | 788 | `53a7502993324ff2…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__1.xml` | 788 | `beea6edbecaf1030…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__10.xml` | 788 | `478bf4848d957de3…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__11.xml` | 788 | `497e62433a0b16ba…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__12.xml` | 888 | `7afe6a976d72094a…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__13.xml` | 788 | `c7d0aa3f0ac898e1…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__14.xml` | 788 | `f96a7ac1a52ef271…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__15.xml` | 788 | `2182c74e0fd91960…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__16.xml` | 788 | `0a999653930d23ae…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__17.xml` | 864 | `98c453e969869a3f…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__2.xml` | 788 | `0dfe1ce55684ad5b…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__3.xml` | 788 | `49751f7eb74f7569…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__4.xml` | 788 | `c5dcd92cae562316…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__5.xml` | 788 | `62f8df2451eee7c1…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__6.xml` | 788 | `e3be2ddf2e6fd69f…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__7.xml` | 788 | `a8664b6322d36063…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__8.xml` | 788 | `7717b0c1771423cb…` |
| `res/drawable/$ic_vector_waiting_approval_kyc__9.xml` | 788 | `a79930c8566f8bb7…` |
| `res/drawable/$ic_vector_withdraw_ekyc__0.xml` | 864 | `e525fe24fcf23ec6…` |
| `res/drawable/$ic_vector_withdraw_ekyc__1.xml` | 888 | `91c7060874d507b9…` |
| `res/drawable/$ic_vector_withdraw_ekyc__2.xml` | 864 | `dc03763a6099d451…` |
| `res/drawable/$ic_vector_withdraw_ekyc__3.xml` | 888 | `8305542f3d39706d…` |
| `res/drawable/$ic_vector_withdraw_ekyc__4.xml` | 788 | `5b9aec8213fd7f57…` |
| `res/drawable/$ic_vector_withdraw_ekyc__5.xml` | 788 | `b7d87ec2f862d371…` |
| `res/drawable/$ic_vector_withdraw_ekyc__6.xml` | 788 | `2677ec8414bde663…` |
| `res/drawable/$ic_vector_withdraw_ekyc__7.xml` | 788 | `4cb4558878eb6022…` |
| `res/drawable/$ic_vector_zalopay_ring__0.xml` | 864 | `49f32352bbf8748b…` |
| `res/drawable/$ic_vector_zalopay_ring__1.xml` | 788 | `7253518f07593450…` |
| `res/drawable/$ic_vector_zalopay_ring__2.xml` | 788 | `b3686c65a9ca7dcf…` |
| `res/drawable/$ic_vector_zalopay_ring__3.xml` | 788 | `2eefcd702cf99053…` |
| `res/drawable/$ic_vector_zalopay_ring__4.xml` | 788 | `d9b55001754502d9…` |
| `res/drawable/$ic_vector_zlp_with_bell__0.xml` | 864 | `0159e396d857a144…` |
| `res/drawable/$ic_vector_zlp_with_bell__1.xml` | 788 | `fc9350b5510fdbcd…` |
| `res/drawable/$ic_vector_zlp_with_bell__2.xml` | 788 | `de03397ff2dc6142…` |
| `res/drawable/$ic_vector_zlp_with_bell__3.xml` | 788 | `f70ff56b1169d403…` |
| `res/drawable/$ic_vector_zlp_with_bell__4.xml` | 788 | `33ad407c801f76b5…` |
| `res/drawable/$ic_verify_failed__0.xml` | 788 | `9e4bfa1d308cfd9e…` |
| `res/drawable/$ic_visa_card_logo__0.xml` | 788 | `6a6a7d367a67e6ef…` |
| `res/drawable/$ic_visa_debit_card_logo__0.xml` | 788 | `a694c567f985a580…` |
| `res/drawable/$ic_voucher_selected__0.xml` | 788 | `b6a57df460803580…` |
| `res/drawable/$ic_warning_almost_data__0.xml` | 788 | `b1a5d9e1d563a7c8…` |
| `res/drawable/$ic_warning_almost_data__1.xml` | 788 | `32a2964d37d5aea5…` |
| `res/drawable/$ic_woo_full_brand__0.xml` | 964 | `c9648c2cf18e162b…` |
| `res/drawable/$ic_zalopay_notify__0.xml` | 788 | `b1a5d9e1d563a7c8…` |
| `res/drawable/$ic_zalopay_notify__1.xml` | 788 | `32a2964d37d5aea5…` |
| `res/drawable/$icon_mrz_top__0.xml` | 988 | `235c1adde1573c5f…` |
| `res/drawable/$img180_general_no_network__0.xml` | 788 | `490f2d35800286ad…` |
| `res/drawable/$img180_general_no_network__1.xml` | 788 | `8c529806b256a4fc…` |
| `res/drawable/$img180_general_no_network__10.xml` | 764 | `4f1790bafa8056b5…` |
| `res/drawable/$img180_general_no_network__11.xml` | 788 | `b6b22501b83bf634…` |
| `res/drawable/$img180_general_no_network__12.xml` | 788 | `c5c08540dc91870b…` |
| `res/drawable/$img180_general_no_network__13.xml` | 788 | `fabadf2e551fc0b8…` |
| `res/drawable/$img180_general_no_network__2.xml` | 788 | `8f5c846bfc8eb845…` |
| `res/drawable/$img180_general_no_network__3.xml` | 788 | `3686672fdaed938e…` |
| `res/drawable/$img180_general_no_network__4.xml` | 788 | `e308dd0c30631762…` |
| `res/drawable/$img180_general_no_network__5.xml` | 1488 | `7e8b40c6b32713b7…` |
| `res/drawable/$img180_general_no_network__6.xml` | 1488 | `8610a1fb42716b23…` |
| `res/drawable/$img180_general_no_network__7.xml` | 1488 | `ba65a6084390e52c…` |
| `res/drawable/$img180_general_no_network__8.xml` | 1488 | `054f9a7f3f498c64…` |
| `res/drawable/$img180_general_no_network__9.xml` | 1488 | `927e328765eb4073…` |
| `res/drawable/$img180_general_unknown__0.xml` | 788 | `08f16cbd9946e98c…` |
| `res/drawable/$img180_general_unknown__1.xml` | 788 | `09fe3a1f5d914261…` |
| `res/drawable/$img180_general_unknown__10.xml` | 788 | `6564ad13b366d369…` |
| `res/drawable/$img180_general_unknown__11.xml` | 788 | `3b051c1239593893…` |
| `res/drawable/$img180_general_unknown__12.xml` | 788 | `a9e371c702296c0f…` |
| `res/drawable/$img180_general_unknown__13.xml` | 788 | `516fa76ba6414244…` |
| `res/drawable/$img180_general_unknown__14.xml` | 788 | `7670bdd0f06dfd96…` |
| `res/drawable/$img180_general_unknown__2.xml` | 788 | `6b23ea8be8e7aeb3…` |
| `res/drawable/$img180_general_unknown__3.xml` | 788 | `7f5991242446c405…` |
| `res/drawable/$img180_general_unknown__4.xml` | 788 | `404075a20be5e94d…` |
| `res/drawable/$img180_general_unknown__5.xml` | 788 | `c22ab0dd2d950c7c…` |
| `res/drawable/$img180_general_unknown__6.xml` | 788 | `e1656ab8e1b488a8…` |
| `res/drawable/$img180_general_unknown__7.xml` | 788 | `a6fe34ac18b75cfc…` |
| `res/drawable/$img180_general_unknown__8.xml` | 788 | `1c4c413ef75909d9…` |
| `res/drawable/$img180_general_unknown__9.xml` | 788 | `4b704a3ee7bd1ca6…` |
| `res/drawable/$img_fds_scan_id_card__0.xml` | 788 | `9133bc3962be565e…` |
| `res/drawable/$img_fds_scan_id_card__1.xml` | 788 | `9dd83723306cccd7…` |
| `res/drawable/$img_general_announcement__0.xml` | 788 | `48f598aea1ed47e4…` |
| `res/drawable/$img_general_announcement__1.xml` | 788 | `bbe28f4050dfd498…` |
| `res/drawable/$img_general_announcement__2.xml` | 788 | `fe3bc73c99166eb9…` |
| `res/drawable/$img_general_announcement__3.xml` | 1488 | `b4a49cef249f8dcc…` |
| `res/drawable/$img_general_announcement__4.xml` | 788 | `c36abd435a28979f…` |
| `res/drawable/$img_general_announcement__5.xml` | 788 | `835730a8ba3470cd…` |
| `res/drawable/$img_no_support_scan_id_card__0.xml` | 788 | `e656d25798656031…` |
| `res/drawable/$img_no_support_scan_id_card__1.xml` | 788 | `1c462ff5ac7aefa2…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__0.xml` | 788 | `f86f6c01481ff098…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__1.xml` | 788 | `349db2864658a0fb…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__10.xml` | 788 | `d092bf3ec7536d40…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__11.xml` | 788 | `c111b6626ddf6fa4…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__12.xml` | 788 | `8daf0a8839a7b22d…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__13.xml` | 788 | `ba1e1b6f4e22abee…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__14.xml` | 788 | `1d71baa1a58bf8e1…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__2.xml` | 788 | `6686d074d8805c77…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__3.xml` | 788 | `4f476ec6f61881e7…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__4.xml` | 788 | `c109693a2ca3757a…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__5.xml` | 788 | `20f87db87cb07396…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__6.xml` | 788 | `80833f7dedd349e4…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__7.xml` | 788 | `19f2c6415a1975a3…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__8.xml` | 788 | `c62472dbe979f62a…` |
| `res/drawable/$img_vector_ekyc_pro_system_error__9.xml` | 788 | `c642903c88e00af2…` |
| `res/drawable/$img_vector_new_cs__0.xml` | 788 | `4aaf77432377a6a2…` |
| `res/drawable/$img_vector_new_cs__1.xml` | 788 | `24a21c5b58bea032…` |
| `res/drawable/$img_vector_new_cs__2.xml` | 788 | `6c4041237b6dedb5…` |
| `res/drawable/$img_vector_new_cs__3.xml` | 788 | `91933ff8e46e0a0a…` |
| `res/drawable/$img_vector_new_cs__4.xml` | 788 | `faf9cb971a83b892…` |
| `res/drawable/$logo_zalopay_background__0.xml` | 788 | `dee5262082c3c27b…` |
| `res/drawable/$logo_zalopay_background__1.xml` | 788 | `c2d3fdfee02aea60…` |
| `res/drawable/$logo_zalopay_background__2.xml` | 788 | `a57c6d268d2698bc…` |
| `res/drawable/$logo_zalopay_background__3.xml` | 788 | `6e29b77e974a09bb…` |
| `res/drawable/$logo_zalopay_background__4.xml` | 788 | `8fb637c46e521be0…` |
| `res/drawable/$sdk_taxi_ic_confirm_cancel_trip__0.xml` | 788 | `bd0e86841d7a69cb…` |
| `res/drawable/$sdk_taxi_icon_checkbox_checked_00__0.xml` | 788 | `fb44a7260e619534…` |
| `res/drawable/$sdk_taxi_icon_checkbox_checked_01__0.xml` | 788 | `fb44a7260e619534…` |
| `res/drawable/$sdk_taxi_icon_checkbox_checked_02__0.xml` | 788 | `fb44a7260e619534…` |
| `res/drawable/$sdk_taxi_icon_end_address_00__0.xml` | 788 | `7f34ab2ef86e240d…` |
| `res/drawable/$sdk_taxi_icon_voucher_checked_00__0.xml` | 788 | `0232285a9fbc27b7…` |
| `res/drawable/$sdk_taxi_icon_voucher_checked_01__0.xml` | 788 | `cc6705059724a6c0…` |
| `res/drawable/$sdk_taxi_icon_voucher_checked_02__0.xml` | 788 | `cc6705059724a6c0…` |
| `res/drawable/$sltd_comparison_illustration__0.xml` | 788 | `735f2a0b92f3e7fb…` |
| `res/drawable/$taxi_16_px_destination__0.xml` | 792 | `4fd9af19f3d2eea2…` |
| `res/drawable/$taxi_20_px_chondiemdon__0.xml` | 792 | `684932923ae7bf11…` |
| `res/drawable/$taxi_40_px_gpsdiemden__0.xml` | 792 | `98a797b435812999…` |
| `res/drawable/$taxi_40_px_more_diemden__0.xml` | 792 | `7b4889d7d2d5bfbf…` |
| `res/drawable/$taxi_ic_choose_location__0.xml` | 904 | `96aefa1ca8eab077…` |
| `res/drawable/$taxi_ic_choose_location__1.xml` | 792 | `685882d136f53e02…` |
| `res/drawable/$taxi_ic_choose_location_inside__0.xml` | 788 | `f67932e9a26ee5df…` |
| `res/drawable/$tidv3_trueid_watermark_dark__0.xml` | 788 | `d648925f55a1c756…` |
| `res/drawable/$tidv3_trueid_watermark_white__0.xml` | 788 | `ae50602e33464fa8…` |
| `res/drawable/_16_px_huongdandatxe.xml` | 1616 | `d44acc9a6bb945f6…` |
| `res/drawable/_20_px_select.xml` | 980 | `e93bc95fcc6db77d…` |
| `res/drawable/_20_px_select__1_.xml` | 748 | `e453da0fc3f09e93…` |
| `res/drawable/_24_px_diadiemgoiy.xml` | 1984 | `296d892dd3a0aa47…` |
| `res/drawable/_24_px_tienmat.xml` | 1000 | `47f18ead23cea693…` |
| `res/drawable/_24_px_vnpay.xml` | 5304 | `7019cd957723f2f7…` |
| `res/drawable/_30_px_header_back.xml` | 1224 | `32c9be37b4a246ca…` |
| `res/drawable/_30_px_header_back_2.xml` | 1216 | `efd57e9004ce1c43…` |
| `res/drawable/_30_px_header_back_3.png` | 960 | `17322543a472d7fe…` |
| `res/drawable/_30_px_header_more.xml` | 1420 | `d851755d7c6c979a…` |
| `res/drawable/_50_px_diadiemdaluu_1.xml` | 2956 | `4bfd037c6a8d564f…` |
| `res/drawable/_50_px_diadiemdaluu_2.xml` | 2220 | `a0559e61bfda55ad…` |
| `res/drawable/_50_px_diadiemdaluu_4.xml` | 1736 | `03c3f2a9599b49d2…` |
| `res/drawable/abc_btn_borderless_material.xml` | 588 | `7bd6edcd6a95a1fb…` |
| `res/drawable/abc_btn_check_material.xml` | 464 | `194c696044d68364…` |
| `res/drawable/abc_btn_check_material_anim.xml` | 816 | `37c5c8e3cdbba1d0…` |
| `res/drawable/abc_btn_default_mtrl_shape.xml` | 932 | `bb20abb5ec81b8f7…` |
| `res/drawable/abc_btn_radio_material.xml` | 464 | `0e68edfd2ba228fa…` |
| `res/drawable/abc_btn_radio_material_anim.xml` | 816 | `3d5387e73330cdae…` |
| `res/drawable/abc_cab_background_internal_bg.xml` | 372 | `cb486473bc0f7d6c…` |
| `res/drawable/abc_cab_background_top_material.xml` | 336 | `1d6d789501f02c91…` |
| `res/drawable/abc_ic_ab_back_material.xml` | 692 | `99dc0556926f6679…` |
| `res/drawable/abc_ic_arrow_drop_right_black_24dp.xml` | 1000 | `6609889d759dbcf6…` |
| `res/drawable/abc_ic_clear_material.xml` | 684 | `6cc5e92be9dc3823…` |
| `res/drawable/abc_ic_go_search_api_material.xml` | 640 | `922729fccbcfd63b…` |
| `res/drawable/abc_ic_menu_copy_mtrl_am_alpha.xml` | 756 | `84dbdf78ad994728…` |
| `res/drawable/abc_ic_menu_cut_mtrl_alpha.xml` | 1096 | `30d56de8667e6cde…` |
| `res/drawable/abc_ic_menu_paste_mtrl_am_alpha.xml` | 796 | `a3dbfe4e5cb06788…` |
| `res/drawable/abc_ic_menu_selectall_mtrl_alpha.xml` | 920 | `3816c83d42d23c0d…` |
| `res/drawable/abc_ic_menu_share_mtrl_alpha.xml` | 980 | `909f4468b73823cb…` |
| `res/drawable/abc_ic_search_api_material.xml` | 812 | `f1f263a2a1d48ba7…` |
| `res/drawable/abc_ic_voice_search_api_material.xml` | 828 | `7d0b37689480d582…` |
| `res/drawable/abc_item_background_holo_dark.xml` | 1012 | `51aa9dbef9aa821f…` |
| `res/drawable/abc_item_background_holo_light.xml` | 1012 | `f79c8c7de56638f9…` |
| `res/drawable/abc_list_selector_background_transition_holo_dark.xml` | 424 | `aa189602570c2a5f…` |
| `res/drawable/abc_list_selector_background_transition_holo_light.xml` | 424 | `925002d6e525f50e…` |
| `res/drawable/abc_list_selector_holo_dark.xml` | 1064 | `d367ed1a250f8d75…` |
| `res/drawable/abc_list_selector_holo_light.xml` | 1064 | `b9472c5a9e213024…` |
| `res/drawable/abc_ratingbar_indicator_material.xml` | 124 | `9b0ac791a3fa953e…` |
| `res/drawable/abc_ratingbar_material.xml` | 124 | `9b0ac791a3fa953e…` |
| `res/drawable/abc_ratingbar_small_material.xml` | 124 | `9b0ac791a3fa953e…` |
| `res/drawable/abc_seekbar_thumb_material.xml` | 1100 | `093eacf906f12001…` |
| `res/drawable/abc_seekbar_tick_mark_material.xml` | 516 | `b9fc808a70303801…` |
| `res/drawable/abc_seekbar_track_material.xml` | 1408 | `abd6e1ab26b82e5b…` |
| `res/drawable/abc_spinner_textfield_background_material.xml` | 1160 | `9204bac4a32ca25a…` |
| `res/drawable/abc_star_black_48dp.xml` | 640 | `74f13910c634a6b4…` |
| `res/drawable/abc_star_half_black_48dp.xml` | 600 | `05b5baedcd58fd99…` |
| `res/drawable/abc_switch_thumb_material.xml` | 464 | `b755ff116acf41e0…` |
| `res/drawable/abc_tab_indicator_material.xml` | 468 | `a83b19011a1a35f9…` |
| `res/drawable/abc_text_cursor_material.xml` | 516 | `e49a3d6549f8ffce…` |
| `res/drawable/abc_textfield_search_material.xml` | 756 | `d657d1d66ca50dc9…` |
| `res/drawable/abc_vector_test.xml` | 612 | `b2b8eab570357610…` |
| `res/drawable/acplugin_bg_button_bg.xml` | 724 | `d95ef359df9a9f10…` |
| `res/drawable/acplugin_dialog_bg.xml` | 484 | `979150bce272454d…` |
| `res/drawable/acplugin_failed.png` | 264 | `15818f4c6084add7…` |
| `res/drawable/acplugin_popup_bg.xml` | 484 | `e9cbaa54015a6f6f…` |
| `res/drawable/acplugin_success.png` | 1094 | `a30a9dce27f9f8ca…` |
| `res/drawable/acplugin_verification_code_input_bg.xml` | 1204 | `cd466ccbb29bac42…` |
| `res/drawable/acplugin_verification_code_input_placeholder.xml` | 480 | `356e6d4683f29377…` |
| `res/drawable/acsdk_anim_progress.xml` | 484 | `4a63b776d0511d12…` |
| `res/drawable/acsdk_loading_bg.xml` | 484 | `4a153d084ebf6f54…` |
| `res/drawable/admob_close_button_black_circle_white_cross.xml` | 988 | `165cdd9b8dc45703…` |
| `res/drawable/admob_close_button_white_circle_black_cross.xml` | 988 | `ce5e70816c5cc024…` |
| `res/drawable/admob_close_button_white_cross.xml` | 704 | `95358976ebde4fd9…` |
| `res/drawable/angle_down_fat.xml` | 568 | `09f8740bd2617f7a…` |
| `res/drawable/app_default.xml` | 3612 | `0b6b63ea9c09dd9e…` |
| `res/drawable/app_widget_background.xml` | 484 | `5805fc02bcd542f2…` |
| `res/drawable/avd_hide_password.xml` | 660 | `4ddf5ffeb8c8e298…` |
| `res/drawable/avd_show_password.xml` | 660 | `354b9715b8913ae7…` |
| `res/drawable/background_alpha30_radius_10.xml` | 484 | `5ed5208d8db71c33…` |
| `res/drawable/background_button_cancel_taxi.xml` | 668 | `e588d052e3b79cae…` |
| `res/drawable/background_button_methor_test_taxi.xml` | 1192 | `124f10784cbed44b…` |
| `res/drawable/background_button_negative_taxi.xml` | 612 | `6d67a77a369ec111…` |
| `res/drawable/background_button_taxi.xml` | 748 | `0e9543d22bbd5c0b…` |
| `res/drawable/background_button_taxi_stages.xml` | 484 | `51852282c1a40bd8…` |
| `res/drawable/background_call.xml` | 372 | `c24e80ebb34bf332…` |
| `res/drawable/background_card_manager.xml` | 536 | `8de31e31d2dcf9d9…` |
| `res/drawable/background_check_taxi.xml` | 520 | `c1af3d3949792300…` |
| `res/drawable/background_check_taxi_booked.xml` | 520 | `5756a4193b85bcb5…` |
| `res/drawable/background_check_taxi_cancel.xml` | 520 | `dc57bad296bc81dc…` |
| `res/drawable/background_check_taxi_success.xml` | 520 | `33ed501c5e9cd029…` |
| `res/drawable/background_dark_border_radius.xml` | 484 | `ce1476012b4f5249…` |
| `res/drawable/background_dark_shadow_bottom_find_driver.xml` | 532 | `897eed406a865fcf…` |
| `res/drawable/background_ellipse_2.png` | 106605 | `92a71cfc9bc7e200…` |
| `res/drawable/background_ellipse_3.png` | 65589 | `4338f29a0b9c3010…` |
| `res/drawable/background_f2f2f2_radius_12.xml` | 484 | `6f0dd7622bfb3701…` |
| `res/drawable/background_f2f2f2_radius_16.xml` | 484 | `6f0dd7622bfb3701…` |
| `res/drawable/background_gray50_radius_10.xml` | 484 | `0189034a6f21bba1…` |
| `res/drawable/background_gray_border_radius.xml` | 484 | `21a63c926e8a3dc5…` |
| `res/drawable/background_gray_radius_10.xml` | 484 | `fb0aad7c6b8a1d13…` |
| `res/drawable/background_gray_radius_12.xml` | 484 | `04a2c40e8798266e…` |
| `res/drawable/background_gray_radius_16.xml` | 484 | `8fdb8c022712f038…` |
| `res/drawable/background_gray_radius_6.xml` | 484 | `bae6975678f8e933…` |
| `res/drawable/background_gray_radius_8.xml` | 484 | `cef5f0a8a0793434…` |
| `res/drawable/background_gray_radius_oval.xml` | 484 | `a3521563690af514…` |
| `res/drawable/background_green_border_radius.xml` | 484 | `6b7206d76d9dde91…` |
| `res/drawable/background_green_radius_10.xml` | 612 | `1b8cac87fcd20027…` |
| `res/drawable/background_header.xml` | 492 | `eeb545bd5b14b6a6…` |
| `res/drawable/background_icon.xml` | 1544 | `94f45f8397918f72…` |
| `res/drawable/background_info_driver.xml` | 520 | `bcbbdaf02d3e92dd…` |
| `res/drawable/background_link_radius_10.xml` | 612 | `090a7249447ce3dc…` |
| `res/drawable/background_message.xml` | 372 | `c79f6ff7896131ac…` |
| `res/drawable/background_note_phuphi_taxi.xml` | 612 | `669c969fe6bc8d6d…` |
| `res/drawable/background_shadow_bottom_find_driver.xml` | 532 | `c104de48453be7aa…` |
| `res/drawable/background_shimer_gray.xml` | 628 | `8b84b8baf6fe0a2f…` |
| `res/drawable/background_transparent_border.xml` | 1224 | `541c64e37eea2aee…` |
| `res/drawable/background_transparent_border_dashed.xml` | 692 | `b5c84ecba9142e5f…` |
| `res/drawable/background_transparent_radius_10.xml` | 484 | `6f97d1c1ce0b2ced…` |
| `res/drawable/background_voucher.xml` | 1636 | `f04df74c24cbd9cc…` |
| `res/drawable/background_voucher_not_apply.xml` | 1980 | `ba704f24839c81e0…` |
| `res/drawable/background_where_radius_16.xml` | 612 | `cc808d667fdf4ab2…` |
| `res/drawable/background_white_boder_14.xml` | 484 | `efa1667eae2d4f46…` |
| `res/drawable/background_white_boder_6.xml` | 612 | `4b4ee4f6eab6cdfa…` |
| `res/drawable/background_white_boder_8.xml` | 612 | `f5bd0d380e039fca…` |
| `res/drawable/background_white_boder_gray_radius_12.xml` | 612 | `dbadfbe3c15f62d9…` |
| `res/drawable/background_white_boder_gray_radius_12_new.xml` | 612 | `0ecd7b3e11db2d04…` |
| `res/drawable/background_white_radius_10.xml` | 484 | `2a7110de257bc200…` |
| `res/drawable/background_white_radius_12.xml` | 484 | `dcf8fdbb420a723b…` |
| `res/drawable/background_white_radius_16.xml` | 484 | `b3d101a10aab2a5a…` |
| `res/drawable/background_white_shadow_bottom.xml` | 1072 | `5dd12f5e59c47db1…` |
| `res/drawable/bank_list.xml` | 30100 | `29659efd7ace788d…` |
| `res/drawable/banner_background.xml` | 1916 | `35c148f0db095d0e…` |
| `res/drawable/bell.xml` | 8456 | `7af2f437ef082dba…` |
| `res/drawable/bg_bank_bottom_dialog_title.xml` | 668 | `ea83bc2ab4192f29…` |
| `res/drawable/bg_bank_card_number.xml` | 668 | `1438e88c252bcefa…` |
| `res/drawable/bg_bank_dash_line_cover.xml` | 656 | `1198ce5aca2bb16a…` |
| `res/drawable/bg_bank_edittext.xml` | 464 | `469d36ee4165f85d…` |
| `res/drawable/bg_bank_edittext_active.xml` | 576 | `59a2c6e86cc85f37…` |
| `res/drawable/bg_bank_edittext_compat.xml` | 448 | `33711b02751d9446…` |
| `res/drawable/bg_bank_edittext_error.xml` | 576 | `2f2e44c676011dbf…` |
| `res/drawable/bg_bank_edittext_inactive.xml` | 652 | `6802f4b1a8d40ef8…` |
| `res/drawable/bg_bank_icon.xml` | 668 | `a736281d33ec56ae…` |
| `res/drawable/bg_bank_result_container.xml` | 1504 | `df25e987ad3ee652…` |
| `res/drawable/bg_bank_rop_warning.xml` | 448 | `ece6c6e28d797fc3…` |
| `res/drawable/bg_bank_submit_button.xml` | 464 | `1db374f7c5e35a31…` |
| `res/drawable/bg_bank_submit_button_default.xml` | 668 | `89c3aad89f2e2ad8…` |
| `res/drawable/bg_bank_submit_button_enable.xml` | 668 | `bbdba9e941417e25…` |
| `res/drawable/bg_bank_tooltip.xml` | 668 | `b6b0d4380a66239f…` |
| `res/drawable/bg_black_opacity_round.xml` | 448 | `e393afbe26093789…` |
| `res/drawable/bg_black_round_opacity_80.xml` | 448 | `daa8cf479b983609…` |
| `res/drawable/bg_blue_border_normal.xml` | 448 | `f559209c6f246c07…` |
| `res/drawable/bg_blue_border_pressed.xml` | 448 | `d206ad816dfbc1c8…` |
| `res/drawable/bg_blue_empty_round_border.xml` | 576 | `bf19a93d00be2d54…` |
| `res/drawable/bg_blue_round_border.xml` | 448 | `bcaf08899f7b8eb2…` |
| `res/drawable/bg_blue_rounded_corner.xml` | 576 | `f85bd5b777c78d7e…` |
| `res/drawable/bg_blue_sdk_ekyc.xml` | 664 | `040a47843f01a09a…` |
| `res/drawable/bg_blue_try_sdk_ekyc.xml` | 664 | `c3df609af2896d37…` |
| `res/drawable/bg_blue_voucher.xml` | 1032 | `55474bbe24f4b262…` |
| `res/drawable/bg_border_bright_blue.xml` | 484 | `cbac373abee167e7…` |
| `res/drawable/bg_border_edittext.xml` | 660 | `fc7ded5908cd126c…` |
| `res/drawable/bg_border_round_blur.xml` | 576 | `09179226442a4d18…` |
| `res/drawable/bg_border_silver.xml` | 536 | `43f06db207dbc792…` |
| `res/drawable/bg_bottom_ekyc.xml` | 716 | `fe4677030f32aa7a…` |
| `res/drawable/bg_bottom_sheet.xml` | 536 | `0324d7a37482f6d1…` |
| `res/drawable/bg_bottom_sheet_dialog_fragment.xml` | 644 | `eab6613c873940e0…` |
| `res/drawable/bg_bottom_sheet_ekyc.xml` | 500 | `9124403ccf7401bb…` |
| `res/drawable/bg_bottom_sheet_header.xml` | 976 | `00378fdac1b18683…` |
| `res/drawable/bg_bottom_sheet_nopadding_dialog_fragment.xml` | 536 | `339cf3db71b41b64…` |
| `res/drawable/bg_bottom_toast.xml` | 348 | `64048b20b3842dca…` |
| `res/drawable/bg_bright_blue_border_selector.xml` | 588 | `7c6f8bee75b411ed…` |
| `res/drawable/bg_bright_blue_rounded_corner.xml` | 448 | `863a6c4af9275a35…` |
| `res/drawable/bg_btn_blue_border.xml` | 448 | `f559209c6f246c07…` |
| `res/drawable/bg_btn_blue_border_selected.xml` | 448 | `f559209c6f246c07…` |
| `res/drawable/bg_btn_blue_border_selector.xml` | 940 | `bf949a2a9fa7f125…` |
| `res/drawable/bg_btn_contacts_corner_8dp_blue_selector.xml` | 1992 | `463fa1b6977be910…` |
| `res/drawable/bg_btn_gray_border.xml` | 448 | `c91dff1ad90da9a6…` |
| `res/drawable/bg_btn_grey_border.xml` | 484 | `cd6d752854f2460c…` |
| `res/drawable/bg_btn_kyc.xml` | 484 | `2e8db805fd083820…` |
| `res/drawable/bg_btn_light_blue_border.xml` | 656 | `ac66dd31b3f11d01…` |
| `res/drawable/bg_btn_light_blue_border_selected.xml` | 576 | `9ee570da6264a060…` |
| `res/drawable/bg_btn_submit_promotion_cash_back.xml` | 448 | `225fb007df9e3d30…` |
| `res/drawable/bg_btn_submit_promotion_cash_back_selected.xml` | 448 | `f9fc7bb73007aa05…` |
| `res/drawable/bg_btn_submit_promotion_cash_back_selector.xml` | 464 | `25774445f43e4b01…` |
| `res/drawable/bg_btn_submit_promotion_voucher.xml` | 576 | `a20614c35d595399…` |
| `res/drawable/bg_btn_white_round_blue.xml` | 576 | `0536525097257a84…` |
| `res/drawable/bg_btn_white_rounded_corners.xml` | 664 | `71979b7134367e35…` |
| `res/drawable/bg_button_blue_white.xml` | 448 | `331500e00e7f97e7…` |
| `res/drawable/bg_button_corner_8dp_blue_selector.xml` | 1992 | `8980e94e70db2085…` |
| `res/drawable/bg_button_edit_card_number.xml` | 796 | `544801eb16a9371f…` |
| `res/drawable/bg_button_empty_round_border_0033c9.xml` | 520 | `7697e7ebd4be44f0…` |
| `res/drawable/bg_button_new_ekyc.xml` | 792 | `60d2ffa7268c63d4…` |
| `res/drawable/bg_button_not_my_account_primary.xml` | 916 | `63966a2788ef1287…` |
| `res/drawable/bg_button_not_my_account_secondary.xml` | 448 | `eac3f024b0256554…` |
| `res/drawable/bg_button_reminders.xml` | 612 | `dea2e9d9cfdee865…` |
| `res/drawable/bg_card_view.xml` | 568 | `00934bf5841a0ae2…` |
| `res/drawable/bg_cashier_btn_promotion_detail.xml` | 484 | `b28c5e7850eaf507…` |
| `res/drawable/bg_cashier_btn_promotion_detail_pressed.xml` | 484 | `1429adfb04b330d5…` |
| `res/drawable/bg_cashier_btn_promotion_detail_selected.xml` | 484 | `72a2e38fd890573a…` |
| `res/drawable/bg_cashier_btn_promotion_detail_selected_pressed.xml` | 484 | `ff92f6eb17f73903…` |
| `res/drawable/bg_cashier_btn_promotion_detail_selected_selector.xml` | 464 | `e4c729df52b7e054…` |
| `res/drawable/bg_cashier_btn_promotion_detail_selector.xml` | 464 | `3a653bb99168b59d…` |
| `res/drawable/bg_cashier_button.xml` | 448 | `9e35954a03aee05f…` |
| `res/drawable/bg_cashier_button_disable.xml` | 448 | `c9a7fc3c39a0a5e7…` |
| `res/drawable/bg_cashier_button_selected.xml` | 448 | `e3ef73f88d7f1335…` |
| `res/drawable/bg_cashier_button_selector.xml` | 588 | `995cd37851ad0ba3…` |
| `res/drawable/bg_cashier_campaign.xml` | 4112 | `79501a7839d2bbd6…` |
| `res/drawable/bg_cashier_loading.xml` | 484 | `9fec69e00497b690…` |
| `res/drawable/bg_cashier_new_ui_header.png` | 99295 | `77cca31de49fd714…` |
| `res/drawable/bg_cashier_popup.xml` | 536 | `f3fc45e3cfb71234…` |
| `res/drawable/bg_cashier_voucher_left.xml` | 880 | `100abcf9f5654835…` |
| `res/drawable/bg_cashier_voucher_right.xml` | 880 | `cf37bf50a5a160cd…` |
| `res/drawable/bg_check_selector_radio_button.xml` | 484 | `8953742719504d71…` |
| `res/drawable/bg_circle_background.xml` | 372 | `d1ddd8bbe664f89f…` |
| `res/drawable/bg_connection_lost.xml` | 448 | `6fe1fc4865321e6b…` |
| `res/drawable/bg_corner_blue_light_selector.xml` | 940 | `1197dc4a578bdba2…` |
| `res/drawable/bg_corner_blue_white.xml` | 576 | `0f15ec96b568a831…` |
| `res/drawable/bg_corner_blue_white_selected.xml` | 576 | `326b807ef6833daf…` |
| `res/drawable/bg_corner_blue_white_selector.xml` | 940 | `1197dc4a578bdba2…` |
| `res/drawable/bg_corner_white.xml` | 448 | `92fcc3707a0f97a9…` |
| `res/drawable/bg_current_tx.xml` | 484 | `c50c0ff837c2d035…` |
| `res/drawable/bg_dark_transparent_round_corner.xml` | 448 | `79fe03350ce042d4…` |
| `res/drawable/bg_dashed_rounded_corner.xml` | 692 | `c6ec9f1936ff57ba…` |
| `res/drawable/bg_download_progressbar.xml` | 600 | `e8b3a1adbae95b62…` |
| `res/drawable/bg_ekyc_window.xml` | 620 | `2347d39e830e5cff…` |
| `res/drawable/bg_empty_linked_bank.xml` | 6316 | `511b4bb2c9f2d321…` |
| `res/drawable/bg_err_pink_ekyc.xml` | 700 | `4fe8492e8024ec0a…` |
| `res/drawable/bg_error.xml` | 484 | `acc4ab7de13f05c5…` |
| `res/drawable/bg_faild_result.webp` | 49362 | `333e4a911271d453…` |
| `res/drawable/bg_front_sdk_ekyc.xml` | 664 | `21243fd182816471…` |
| `res/drawable/bg_gray_voucher.xml` | 484 | `32632194f78a33e8…` |
| `res/drawable/bg_green_03ca77_radius_8.xml` | 620 | `38751ff5dfec5aff…` |
| `res/drawable/bg_grey_rounded_corner.xml` | 576 | `19705a7795032fe0…` |
| `res/drawable/bg_header_toolbar.xml` | 336 | `e4798ddfb973f023…` |
| `res/drawable/bg_home_reminder_new_background.xml` | 484 | `3b5ef0f544660d24…` |
| `res/drawable/bg_home_top_round_corner.xml` | 536 | `c8fb78ed4d076840…` |
| `res/drawable/bg_indicator.xml` | 972 | `a830a7faa01c360d…` |
| `res/drawable/bg_info_blue_ekyc.xml` | 700 | `76f55c04e9c4d285…` |
| `res/drawable/bg_light_blue_border_selector.xml` | 940 | `3cb7da863e87ff9e…` |
| `res/drawable/bg_line_border_top_f6f7f8.xml` | 812 | `d11ce042e76a9a4e…` |
| `res/drawable/bg_line_bottom.xml` | 792 | `f5b6e17453f409d5…` |
| `res/drawable/bg_line_top_and_bottom.xml` | 792 | `1424b1b48dcbe5ce…` |
| `res/drawable/bg_main_ekyc.xml` | 496 | `4ac4ee32404d2473…` |
| `res/drawable/bg_method_payment_seleced.xml` | 1760 | `56c8fdba12817ffa…` |
| `res/drawable/bg_method_payment_unseleced.xml` | 1288 | `cace8640f5818b4d…` |
| `res/drawable/bg_neutrals_ice_blue_normal.xml` | 484 | `04065e4fac56318f…` |
| `res/drawable/bg_neutrals_ice_blue_selected.xml` | 484 | `770719d08a97593c…` |
| `res/drawable/bg_neutrals_ice_blue_selector.xml` | 464 | `1f68ef386f76f775…` |
| `res/drawable/bg_nfc_ekyc.png` | 26103 | `7080d18acaee8b5c…` |
| `res/drawable/bg_ocr_pro_grey_tooltip.webp` | 7732 | `7bc29f399b93526c…` |
| `res/drawable/bg_orange_e3173c_radius_8.xml` | 620 | `c17f44f718ffcf92…` |
| `res/drawable/bg_pending_result.webp` | 47336 | `3d0435b187ed6b29…` |
| `res/drawable/bg_porcelain_rounded_corner.xml` | 576 | `aa63ad6d2614fa7f…` |
| `res/drawable/bg_progress_blue_ekyc.xml` | 588 | `c165f50c24bff6f2…` |
| `res/drawable/bg_progressbar.xml` | 780 | `032b48f08c3bfaf3…` |
| `res/drawable/bg_promotion_item_middle.xml` | 1276 | `c48ed8e6ac334631…` |
| `res/drawable/bg_purple_ekyc.xml` | 448 | `04657e32bd437712…` |
| `res/drawable/bg_question_radio.xml` | 564 | `c29261e2832e8a84…` |
| `res/drawable/bg_reading_active_ekyc.xml` | 372 | `b31fac66b8095021…` |
| `res/drawable/bg_reading_inactive_ekyc.xml` | 372 | `e7dc9f0ab0f31883…` |
| `res/drawable/bg_recapture_ekyc.xml` | 792 | `bcff7ab1302772c8…` |
| `res/drawable/bg_red_rounded_corner.xml` | 576 | `2c5b7e94a57b9e9d…` |
| `res/drawable/bg_resetpin_edt.xml` | 612 | `96108cf753bb19ee…` |
| `res/drawable/bg_ripple_effect_corner.xml` | 680 | `7f3d1c44cfa5b88e…` |
| `res/drawable/bg_ripple_white_default.xml` | 536 | `c8553b3e9d8ae0fc…` |
| `res/drawable/bg_round_bio_registry.xml` | 448 | `1995f122f5ffe14d…` |
| `res/drawable/bg_round_black_alpha.xml` | 484 | `e12f46ca13c876dd…` |
| `res/drawable/bg_round_corner_e6f0ff.xml` | 448 | `b68a31dc15d9a908…` |
| `res/drawable/bg_round_smart_otp.xml` | 448 | `54c11882f6f5e90e…` |
| `res/drawable/bg_rounded_bottom_left_right_corner.xml` | 852 | `17407823bb8661ab…` |
| `res/drawable/bg_rounded_top_left_right_corner.xml` | 716 | `8d2a1a18425ac0ee…` |
| `res/drawable/bg_rp_new_ui_header.png` | 99295 | `77cca31de49fd714…` |
| `res/drawable/bg_rp_popup.xml` | 536 | `d110ac24cfc53ea2…` |
| `res/drawable/bg_rp_popup_v3.xml` | 536 | `d110ac24cfc53ea2…` |
| `res/drawable/bg_select_function_white.xml` | 484 | `8af41bf674f5a628…` |
| `res/drawable/bg_selfie_guide_badge_cross.xml` | 500 | `d1227c1d112bb3d3…` |
| `res/drawable/bg_selfie_guide_badge_tick.xml` | 500 | `55c0571bd2b30ade…` |
| `res/drawable/bg_selfie_guide_icon_circle.xml` | 500 | `cd23177c4c80fd03…` |
| `res/drawable/bg_selfie_guide_icon_circle_highlight.xml` | 500 | `61a27dd50ad62748…` |
| `res/drawable/bg_selfie_invalid_error_banner.xml` | 484 | `01b77dbc1bf014fe…` |
| `res/drawable/bg_selfie_invalid_guide.xml` | 612 | `e4cef6223176eaaf…` |
| `res/drawable/bg_selfie_photo_card.xml` | 484 | `508f159bfd56bfac…` |
| `res/drawable/bg_start_ekyc.xml` | 664 | `4a884ffe2ee6c2e7…` |
| `res/drawable/bg_success_result.webp` | 47836 | `1962f8a42ac5bbe6…` |
| `res/drawable/bg_text_solid_round_fce8ec.xml` | 448 | `02ae2c787bcd3b7a…` |
| `res/drawable/bg_topup_withdraw_white_border_radius_8.xml` | 576 | `9b7a152b7c6689d7…` |
| `res/drawable/bg_up_button_corner_8dp_blue_selector.xml` | 1992 | `463fa1b6977be910…` |
| `res/drawable/bg_user_profile_blue_round_boder.xml` | 576 | `0536525097257a84…` |
| `res/drawable/bg_user_profile_blue_round_border.xml` | 448 | `2276112cbb89decf…` |
| `res/drawable/bg_user_profile_red_rounded_corner.xml` | 576 | `340c8facee85d02a…` |
| `res/drawable/bg_user_profile_status_blue.xml` | 448 | `b251949a588d0f82…` |
| `res/drawable/bg_user_profile_status_grey.xml` | 448 | `19f52f9870b2fced…` |
| `res/drawable/bg_user_profile_status_yellow.xml` | 448 | `3400cedd460e5223…` |
| `res/drawable/bg_user_profile_text_field.xml` | 576 | `5dd52c8fe431d9a1…` |
| `res/drawable/bg_validated_date_highlight.xml` | 668 | `ce77b2ba28489c8b…` |
| `res/drawable/bg_video_ekyc.xml` | 588 | `5088384a172c6777…` |
| `res/drawable/bg_voucher.xml` | 700 | `ba259ba4e65fac1d…` |
| `res/drawable/bg_voucher_illegal.xml` | 536 | `49ddd33ff39e0c64…` |
| `res/drawable/bg_voucher_sugest.xml` | 484 | `05696f524283dca8…` |
| `res/drawable/bg_webapp_item.xml` | 484 | `7668ccb42c367c0b…` |
| `res/drawable/bg_white_border_ccd2d8.xml` | 652 | `c2fdb58baa6b84ac…` |
| `res/drawable/bg_white_border_radius_8.xml` | 576 | `14c162e9aeb4c8fe…` |
| `res/drawable/bg_white_no_border_radius_20.xml` | 520 | `ce25bb0b52b3f542…` |
| `res/drawable/bg_white_round_12dp.xml` | 448 | `bf9f81300201c8e8…` |
| `res/drawable/bg_white_round_20dp.xml` | 448 | `43385d8c8e847a55…` |
| `res/drawable/bg_white_round_4dp.xml` | 448 | `b4e295e52c19283d…` |
| `res/drawable/bg_white_round_8dp.xml` | 500 | `5a9b15ccaaab8917…` |
| `res/drawable/bg_white_sdk_ekyc.xml` | 792 | `13670ced6f58eefb…` |
| `res/drawable/bg_white_top_corner.xml` | 500 | `162830e879df81ed…` |
| `res/drawable/bg_zalopay_loading.xml` | 656 | `5fd808a68da9606d…` |
| `res/drawable/bg_zumthor_rounded_corner.xml` | 448 | `7f53620b1e5f1a55…` |
| `res/drawable/bkg_cartype_layout_selected.xml` | 484 | `89678d3c36b5bfbe…` |
| `res/drawable/bkg_cartype_layout_unselect.xml` | 484 | `7c9e1c22fa5a19f9…` |
| `res/drawable/bkg_price_border_gray.xml` | 612 | `278e827d0a689f3e…` |
| `res/drawable/blindbox.xml` | 2568 | `fc0e35154ad6f211…` |
| `res/drawable/blue_rounded_corner.xml` | 612 | `603675a9b0bf61dc…` |
| `res/drawable/book_fragment_car_type_bkg.xml` | 808 | `395a8ca78fe9cecf…` |
| `res/drawable/book_fragment_car_type_bkg_insurance.xml` | 900 | `ca14f02403ee4969…` |
| `res/drawable/border_bottom.xml` | 792 | `2717e69e9c44f460…` |
| `res/drawable/border_bottom_dotted.xml` | 868 | `e083aa9158e0eccf…` |
| `res/drawable/border_bottom_nobackground.xml` | 792 | `daca8a9efaaf4eaf…` |
| `res/drawable/border_bottom_order.xml` | 792 | `b61272a1d03ee0db…` |
| `res/drawable/border_bottom_top.xml` | 964 | `c49672819cfecf00…` |
| `res/drawable/border_bottom_top_order.xml` | 792 | `8aab64ee24ae39d1…` |
| `res/drawable/border_bottom_touch.xml` | 792 | `67bdd0992ad7f5e3…` |
| `res/drawable/border_bottom_transparent.xml` | 792 | `daca8a9efaaf4eaf…` |
| `res/drawable/border_bottom_white.xml` | 792 | `6ab7b8d07205920e…` |
| `res/drawable/border_box.xml` | 500 | `8b0974cfad045113…` |
| `res/drawable/border_box_pressed.xml` | 500 | `c05e80b360d60bd4…` |
| `res/drawable/border_box_stroke.xml` | 520 | `567a143df84076e9…` |
| `res/drawable/border_btn_left_mask.xml` | 556 | `54c781e0c1d625f5…` |
| `res/drawable/border_circle_dark.xml` | 1204 | `be2b611b87da1bc9…` |
| `res/drawable/border_default_radius.xml` | 612 | `9bb2e07623c3bb1b…` |
| `res/drawable/border_noselect_hangxe.xml` | 632 | `86383de02ad4a9ef…` |
| `res/drawable/border_right.xml` | 792 | `d623e85f5fd8d58f…` |
| `res/drawable/border_selectted_hangxe.xml` | 668 | `eb2341695ed6ad95…` |
| `res/drawable/border_top.xml` | 792 | `f21146c5531718be…` |
| `res/drawable/bottom_boder_line.xml` | 632 | `59d59a4baca64764…` |
| `res/drawable/bottom_input_selector.xml` | 484 | `9295146abed3fc73…` |
| `res/drawable/bottom_line.xml` | 628 | `31d6048abdeeb38d…` |
| `res/drawable/broder_btn_center.xml` | 812 | `b8f2fc03c8865302…` |
| `res/drawable/broder_btn_left.xml` | 848 | `153880c4dca26fa9…` |
| `res/drawable/broder_btn_left_gray.xml` | 844 | `22475343c7aadc85…` |
| `res/drawable/broder_btn_left_only.xml` | 776 | `e8ab0f195f82b767…` |
| `res/drawable/broder_btn_right.xml` | 848 | `8871930e9f866941…` |
| `res/drawable/broder_btn_right_gray.xml` | 844 | `22475343c7aadc85…` |
| `res/drawable/broder_btn_right_only.xml` | 776 | `fc19068bd9aa3919…` |
| `res/drawable/broder_btn_top_grey.xml` | 776 | `d5b248d779c19a72…` |
| `res/drawable/broder_btn_top_left_gray.xml` | 808 | `7560db9ed1ae0c3f…` |
| `res/drawable/btn_border_gray.xml` | 520 | `1467cb78671c89d7…` |
| `res/drawable/btn_check_normal.xml` | 612 | `a96a972bec09a21b…` |
| `res/drawable/btn_checkbox_checked_mtrl.xml` | 2688 | `c2d4585b382ece8a…` |
| `res/drawable/btn_checkbox_checked_to_unchecked_mtrl_animation.xml` | 688 | `9ea78d11e4d0078a…` |
| `res/drawable/btn_checkbox_unchecked_mtrl.xml` | 2660 | `e618d2ffe965ae47…` |
| `res/drawable/btn_checkbox_unchecked_to_checked_mtrl_animation.xml` | 688 | `4c8bc5c841f0b72e…` |
| `res/drawable/btn_corner_8dp_blue_selector.xml` | 1992 | `463fa1b6977be910…` |
| `res/drawable/btn_corner_white_selector.xml` | 2420 | `71269e86daac9acf…` |
| `res/drawable/btn_radio_off_mtrl.xml` | 1728 | `2a12a29193791d34…` |
| `res/drawable/btn_radio_off_to_on_mtrl_animation.xml` | 680 | `5c5137b5af1d0f48…` |
| `res/drawable/btn_radio_on_mtrl.xml` | 1656 | `f2c4cdf65caa5981…` |
| `res/drawable/btn_radio_on_to_off_mtrl_animation.xml` | 680 | `009ca9160c7cb411…` |
| `res/drawable/btn_round_selector.xml` | 1992 | `9736d5b9ac21e004…` |
| `res/drawable/bullet_bank_condition.xml` | 372 | `9b2978799abd5d3e…` |
| `res/drawable/bvb_camera.png` | 22015 | `e18680ef17520046…` |
| `res/drawable/bvb_cardframe.png` | 41624 | `4d3fa34159caffb4…` |
| `res/drawable/camera_flash_ekyc.xml` | 468 | `d11a13b2de652e95…` |
| `res/drawable/camera_line_ekyc.xml` | 1224 | `c62bd7c7d4763c98…` |
| `res/drawable/cbb_bottom_white_selector.xml` | 484 | `082124d44dddea34…` |
| `res/drawable/cbb_noborder_touch.xml` | 372 | `a2032b9c5f30a769…` |
| `res/drawable/choose_cartype_overlay_bkg_transition.xml` | 532 | `966dead21e375aac…` |
| `res/drawable/chuyendibaohiem.png` | 43452 | `6817885f8b07014f…` |
| `res/drawable/chuyendibaohiem_active.png` | 64851 | `5b795d0858a1d59e…` |
| `res/drawable/circle_shape.xml` | 372 | `11120cbd1dd811a1…` |
| `res/drawable/circle_solid_red.xml` | 372 | `db5b550b12e60bc2…` |
| `res/drawable/close.xml` | 1164 | `af90db6ea091c2fb…` |
| `res/drawable/close_tap2pay_2x.png` | 61334 | `04bcdda6dc104855…` |
| `res/drawable/color_topup_amount_cursor.xml` | 556 | `5e921b7cf139c55d…` |
| `res/drawable/com_facebook_auth_dialog_background.xml` | 596 | `152f07aaba92cbee…` |
| `res/drawable/com_facebook_auth_dialog_cancel_background.xml` | 972 | `0ada811842b330ac…` |
| `res/drawable/com_facebook_auth_dialog_header_background.xml` | 596 | `9468ea465c9f6e67…` |
| `res/drawable/com_facebook_favicon_blue.xml` | 836 | `0b5141f19d2bbb03…` |
| `res/drawable/confirm_dialog_white_rounded_corner.xml` | 536 | `6dd1f2c475e77d14…` |
| `res/drawable/connect_line_v5.png` | 188 | `e362b968d921f237…` |
| `res/drawable/contacts_bg_white_round_12dp.xml` | 448 | `c5c62b3ec11ffba3…` |
| `res/drawable/current_taxi_detail_bkg_dialog.xml` | 536 | `d46c7736dc47954a…` |
| `res/drawable/cursor_bright_blue.xml` | 480 | `8dbd17662bd790a0…` |
| `res/drawable/custom_thumb.xml` | 1260 | `2422accc8d829686…` |
| `res/drawable/custom_track.xml` | 1412 | `50fd37f7d70b5047…` |
| `res/drawable/dark_blue_grey_round_bg.xml` | 484 | `863fa3235a279c50…` |
| `res/drawable/dash_line.xml` | 1024 | `d73ec6f7a291d7bd…` |
| `res/drawable/dash_line_e6e9ec_bank.xml` | 488 | `87c0946fdc1ffd72…` |
| `res/drawable/default_border_tooltip_bottom.xml` | 1268 | `c14e1d0ebe2ee6ca…` |
| `res/drawable/default_border_tooltip_top.xml` | 1256 | `aa78f429b6f6b67f…` |
| `res/drawable/default_input_focus.xml` | 792 | `66f00588a5fb75d6…` |
| `res/drawable/default_selector.xml` | 716 | `211bd0a8a756e790…` |
| `res/drawable/default_white_selector.xml` | 716 | `b7ba5cac8262f2ed…` |
| `res/drawable/demo_icon_24px.xml` | 844 | `86f75135956443e9…` |
| `res/drawable/demo_icon_40px.xml` | 4600 | `cacfba26ca01659a…` |
| `res/drawable/design_fab_background.xml` | 372 | `1469ff8e21783376…` |
| `res/drawable/design_ic_visibility.xml` | 540 | `dd39d4a4ee46bc36…` |
| `res/drawable/design_ic_visibility_off.xml` | 1144 | `2df3e0fe809521b7…` |
| `res/drawable/design_password_eye.xml` | 816 | `351e5cab48fca9e0…` |
| `res/drawable/design_snackbar_background.xml` | 484 | `006d36a855aa60ff…` |
| `res/drawable/dialog_sweet_shadow.xml` | 1072 | `434066b37c324c4d…` |
| `res/drawable/divider_bank_binding_fields.xml` | 372 | `5d063f5f839587ca…` |
| `res/drawable/divider_horizontal_12.xml` | 372 | `37fe7ba835ad2332…` |
| `res/drawable/divider_vertical_16.xml` | 372 | `ce46f5277e33af0c…` |
| `res/drawable/dot.gif` | 53512 | `f753420efad15ca0…` |
| `res/drawable/dotted.xml` | 580 | `fc6812782223a910…` |
| `res/drawable/down_angle.xml` | 796 | `728b9f8cd55107fe…` |
| `res/drawable/empty_divider_bank_condition.xml` | 372 | `3780c4ba2f775897…` |
| `res/drawable/empty_promo.png` | 86123 | `e9ffdc3270cfcac4…` |
| `res/drawable/exo_rounded_rectangle.xml` | 484 | `9f52f8ee04831681…` |
| `res/drawable/facesdk_back_button.xml` | 688 | `df69de880bf70994…` |
| `res/drawable/facesdk_ic_camera.png` | 4409 | `e5a012d0a049c589…` |
| `res/drawable/facesdk_ic_success.png` | 244058 | `246df0b69c01f6b4…` |
| `res/drawable/facesdk_rounded_button_active.xml` | 632 | `f65626a8473f09c4…` |
| `res/drawable/find_driver_overlay_bkg_transition.xml` | 424 | `70dea3c8aa577040…` |
| `res/drawable/front_id_frame.xml` | 972 | `a1c890b6f1d9f4dd…` |
| `res/drawable/funding_manager_background.xml` | 1288 | `4bc1efd10c163705…` |
| `res/drawable/glance_abc_btn_check_material_anim_enabled.xml` | 816 | `5fbfd7c393680a5c…` |
| `res/drawable/glance_btn_checkbox_checked_mtrl.xml` | 2688 | `c2d4585b382ece8a…` |
| `res/drawable/glance_btn_checkbox_checked_to_unchecked_mtrl_animation.xml` | 688 | `3cc0e64e9e73d8ce…` |
| `res/drawable/glance_btn_checkbox_unchecked_mtrl.xml` | 2660 | `e618d2ffe965ae47…` |
| `res/drawable/glance_btn_checkbox_unchecked_to_checked_mtrl_animation.xml` | 688 | `ac9d5357138d9bbe…` |
| `res/drawable/glance_btn_radio_material_anim.xml` | 816 | `b6df433e7b0b62eb…` |
| `res/drawable/glance_btn_radio_off_mtrl.xml` | 1764 | `5a7ffe1aad0e4cc7…` |
| `res/drawable/glance_btn_radio_off_to_on_mtrl_animation.xml` | 680 | `2d0efa236ac63950…` |
| `res/drawable/glance_btn_radio_on_mtrl.xml` | 1692 | `7ffccd90f4a5b22a…` |
| `res/drawable/glance_btn_radio_on_to_off_mtrl_animation.xml` | 680 | `a4df4df27f8566a5…` |
| `res/drawable/glance_button_outline.xml` | 340 | `90a941822faf433d…` |
| `res/drawable/glance_button_ripple.xml` | 680 | `a0767f154c562119…` |
| `res/drawable/glance_error_layout_background.xml` | 484 | `2398c94fd319e0f3…` |
| `res/drawable/glance_loading_layout_background.xml` | 484 | `6125bdb06602f76c…` |
| `res/drawable/glance_progress_horizontal.xml` | 1000 | `c0496d339264580d…` |
| `res/drawable/glance_ripple.xml` | 344 | `80fe9f7a14fc111b…` |
| `res/drawable/glance_switch_thumb_animated.xml` | 816 | `9f963003c1f55956…` |
| `res/drawable/glance_switch_thumb_off_to_on.xml` | 536 | `cbe6978c7ba0c599…` |
| `res/drawable/glance_switch_thumb_on_to_off.xml` | 536 | `e201225d727f5ff1…` |
| `res/drawable/glance_switch_track.xml` | 724 | `8514d2739bb2582f…` |
| `res/drawable/graphic_onboarding_sdsl.xml` | 85268 | `7e59a5f4b5bf694e…` |
| `res/drawable/graphics_car_full.png` | 21407 | `bfbcb00434029951…` |
| `res/drawable/grey_rounded_corner.xml` | 612 | `1b515e8764f07bbc…` |
| `res/drawable/griver_button_negative.xml` | 564 | `0b30c00f30c96c02…` |
| `res/drawable/griver_button_negative_night.xml` | 564 | `bcb0afdab063c06a…` |
| `res/drawable/griver_button_negative_normal.xml` | 520 | `fad5fc9d7dd25808…` |
| `res/drawable/griver_button_negative_normal_night.xml` | 484 | `054ec01469a4c678…` |
| `res/drawable/griver_button_negative_pressed.xml` | 612 | `963bd4bd6435df87…` |
| `res/drawable/griver_button_negative_pressed_night.xml` | 612 | `3b014936a7313681…` |
| `res/drawable/griver_button_positive.xml` | 564 | `c9f69f901355a1e9…` |
| `res/drawable/griver_button_positive_night.xml` | 564 | `7ec679a9055e7b63…` |
| `res/drawable/griver_button_positive_normal.xml` | 612 | `09518b839627d78d…` |
| `res/drawable/griver_button_positive_normal_night.xml` | 612 | `a5a67d336eb33a41…` |
| `res/drawable/griver_button_positive_pressed.xml` | 612 | `7cd2c37b9441962c…` |
| `res/drawable/griver_button_positive_pressed_night.xml` | 612 | `09518b839627d78d…` |
| `res/drawable/griver_check_box.xml` | 564 | `bd7bd2d13a1dc150…` |
| `res/drawable/griver_check_box_checked.xml` | 840 | `d09d798288be1ff7…` |
| `res/drawable/griver_check_box_normal.xml` | 716 | `ca17731a6f3a1722…` |
| `res/drawable/griver_check_box_ok.png` | 741 | `a7123c5d5f8bd0a7…` |
| `res/drawable/griver_checkbox_bg.xml` | 564 | `0b94821079e0588f…` |
| `res/drawable/griver_checkbox_bg_checked.xml` | 840 | `db62ca4f8a2ad1e0…` |
| `res/drawable/griver_checkbox_bg_night.xml` | 564 | `77ac25a5f5e1b941…` |
| `res/drawable/griver_checkbox_bg_normal.xml` | 652 | `a64c08bb7c555a5e…` |
| `res/drawable/griver_checkbox_bg_normal_night.xml` | 652 | `3bcb36a3f547fdb0…` |
| `res/drawable/griver_core_au_bg_grey.xml` | 612 | `615d1eacb3c188bd…` |
| `res/drawable/griver_core_au_bg_grey_night.xml` | 612 | `5d86db32ab0a9bcf…` |
| `res/drawable/griver_core_custom_dialog.xml` | 448 | `bd3b8aa13ec63889…` |
| `res/drawable/griver_core_dialog_bg.xml` | 448 | `b8736fdc0e3677c1…` |
| `res/drawable/griver_core_dialog_bg_night.xml` | 448 | `567c435605feb7a8…` |
| `res/drawable/griver_core_edittext_cursor.xml` | 480 | `b17a0b0c6f74eda0…` |
| `res/drawable/griver_core_mini_widget_toast_bg.xml` | 448 | `9522d1414fa362c5…` |
| `res/drawable/griver_core_mini_widget_toast_bg_night.xml` | 448 | `1842aeae9a10adb2…` |
| `res/drawable/griver_core_red_capsule.xml` | 484 | `64bbcae05ad98b6b…` |
| `res/drawable/griver_core_red_point.xml` | 516 | `dfefd96227558fce…` |
| `res/drawable/griver_core_round_corner_white_background.xml` | 628 | `a42059890d7ba646…` |
| `res/drawable/griver_core_round_corner_white_background_night.xml` | 628 | `0ac21ed329481521…` |
| `res/drawable/griver_core_round_white_background.xml` | 516 | `201daec8702d3b1a…` |
| `res/drawable/griver_core_round_white_background_night.xml` | 516 | `2e74d34a7910122e…` |
| `res/drawable/griver_core_title_drawable.xml` | 536 | `5f73e529cdd4d862…` |
| `res/drawable/griver_core_title_drawable_night.xml` | 536 | `d78d02dbea3bd2a0…` |
| `res/drawable/griver_core_tool_background.xml` | 448 | `c7d64f504d92e9d1…` |
| `res/drawable/griver_core_white_title_drawable.xml` | 536 | `55cf00fe33db81f0…` |
| `res/drawable/griver_core_white_title_drawable_night.xml` | 536 | `dd72d54570de55c6…` |
| `res/drawable/griver_device_settings_divider.xml` | 480 | `3536f8fa7d340406…` |
| `res/drawable/griver_device_settings_divider_night.xml` | 480 | `313a09f5d1944492…` |
| `res/drawable/griver_image_btn_blue_selector.xml` | 1316 | `6b2737479c0a400a…` |
| `res/drawable/griver_image_btn_camera_selector.xml` | 588 | `3cc72571194bb813…` |
| `res/drawable/griver_image_btn_text_selector.xml` | 712 | `63bb2a511a1e18a6…` |
| `res/drawable/griver_image_bucket_item_bg.xml` | 592 | `521a482a1dbd21da…` |
| `res/drawable/griver_image_bucket_item_bg_night.xml` | 592 | `e5b20457ea852351…` |
| `res/drawable/griver_image_bucket_triangle_selector.xml` | 632 | `1e045f2ba7068f76…` |
| `res/drawable/griver_image_capture_indicator.xml` | 412 | `38cb05ab762063e7…` |
| `res/drawable/griver_image_dark_selector.xml` | 784 | `a55628b890df2b8b…` |
| `res/drawable/griver_image_dr_histroy_media_entry.xml` | 592 | `2a60ae514fee3361…` |
| `res/drawable/griver_image_dr_media_download_entry.xml` | 592 | `e4c9fb360b404778…` |
| `res/drawable/griver_image_indicator_normal.xml` | 516 | `ca15deb382bad4f2…` |
| `res/drawable/griver_image_indicator_selected.xml` | 516 | `cc0ba58b2cb3a396…` |
| `res/drawable/griver_image_origin_selector.xml` | 564 | `0b0537114c95f299…` |
| `res/drawable/griver_image_pb_default.xml` | 352 | `c41c5074bef254e5…` |
| `res/drawable/griver_image_photo_selector.xml` | 564 | `3acb0ae4403396f2…` |
| `res/drawable/griver_image_show_origin_selector.xml` | 712 | `2d8fd894059db2d7…` |
| `res/drawable/griver_image_title_bar_btn_bg_selector.xml` | 712 | `ab1885d52be8d17e…` |
| `res/drawable/griver_message_box_bkg.xml` | 484 | `1b9c9f0085c003d2…` |
| `res/drawable/griver_message_box_left.xml` | 984 | `edd49adda11fde9d…` |
| `res/drawable/griver_message_box_one.xml` | 1052 | `7cf02e860318cb28…` |
| `res/drawable/griver_message_box_right.xml` | 984 | `2e3c51f34bcca533…` |
| `res/drawable/griver_operation_core_mini_widget_toast_bg.xml` | 448 | `87550747bf15c5fd…` |
| `res/drawable/griver_shadow_panel_background.xml` | 576 | `4cdde66d29c3cea1…` |
| `res/drawable/griver_shadow_panel_background_night.xml` | 576 | `761c577f351bf79e…` |
| `res/drawable/griver_switch_thumb.xml` | 564 | `c6df56496876e14b…` |
| `res/drawable/griver_switch_thumb_off.xml` | 628 | `02d715a8e5613f85…` |
| `res/drawable/griver_switch_thumb_on.xml` | 628 | `0d5830a30424db16…` |
| `res/drawable/griver_switch_track.xml` | 564 | `a245dc08b3e32f57…` |
| `res/drawable/griver_switch_track_night.xml` | 564 | `71f5cc29a30e62a3…` |
| `res/drawable/griver_switch_track_off.xml` | 612 | `8d6519b7fb83fa03…` |
| `res/drawable/griver_switch_track_off_night.xml` | 612 | `a2cf07af86d0e353…` |
| `res/drawable/griver_switch_track_on.xml` | 612 | `748e59000616bdee…` |
| `res/drawable/griver_ui_button_bg_for_ass_transparent.xml` | 1592 | `37ce650061f45371…` |
| `res/drawable/griver_ui_button_bg_for_main.xml` | 1260 | `361bb659a5372793…` |
| `res/drawable/griver_ui_button_bg_for_sub.xml` | 1636 | `a486a273e73d4889…` |
| `res/drawable/griver_ui_button_bg_for_text.xml` | 780 | `54a6a15ce447039b…` |
| `res/drawable/griver_ui_button_bg_for_warn.xml` | 1872 | `3807986953c10e2b…` |
| `res/drawable/griver_ui_close_btn_bg_dark.xml` | 576 | `df24678ac2e91e51…` |
| `res/drawable/griver_ui_close_btn_bg_light.xml` | 576 | `34694b2862b36f63…` |
| `res/drawable/griver_ui_dialog_bg.xml` | 576 | `cb25e3d700d7831d…` |
| `res/drawable/griver_ui_dialog_btn_press_radius_shape.xml` | 484 | `e0d656f6fbe2e766…` |
| `res/drawable/griver_ui_drawable_titlebar_bg.xml` | 740 | `29b9934cdbab41b1…` |
| `res/drawable/griver_ui_favorite_btn_bg_dark.xml` | 668 | `cdc3efb09c556bd2…` |
| `res/drawable/griver_ui_favorite_btn_bg_light.xml` | 628 | `f5b880bc1fd64241…` |
| `res/drawable/griver_ui_list_dialog_scrollbar_thumb.xml` | 628 | `4ff52ae5c55782d7…` |
| `res/drawable/griver_ui_loading_dialog_bg.xml` | 448 | `08d9f51ee35a4a67…` |
| `res/drawable/griver_ui_loading_dialog_bg_night.xml` | 448 | `10b12cd897ac6665…` |
| `res/drawable/griver_ui_nav_menu_selector.xml` | 712 | `4f039451478f7ff3…` |
| `res/drawable/griver_ui_nav_segment_item_checked.xml` | 448 | `bad8cd7fb2ee11c1…` |
| `res/drawable/griver_ui_nav_segment_item_unchecked.xml` | 448 | `bad8cd7fb2ee11c1…` |
| `res/drawable/griver_ui_notice_dialog_btn_selector.xml` | 464 | `bea46cd8c5e6d67b…` |
| `res/drawable/griver_ui_pop_list_corner_round.xml` | 736 | `2857672d5d80cd64…` |
| `res/drawable/griver_ui_pop_list_corner_round_bottom.xml` | 796 | `dc059435f759e651…` |
| `res/drawable/griver_ui_pop_list_corner_round_top.xml` | 788 | `a7a7685c16e90884…` |
| `res/drawable/griver_ui_pop_list_corner_shape.xml` | 464 | `670ad5dba14f1e98…` |
| `res/drawable/griver_ui_popmenu_divider.xml` | 828 | `bdd237d298878811…` |
| `res/drawable/griver_ui_progress_view_bg.xml` | 372 | `762939cdefbc393c…` |
| `res/drawable/griver_ui_progress_view_bg_white.xml` | 372 | `53bda494cbe531b9…` |
| `res/drawable/griver_ui_pullrefresh_progress.xml` | 424 | `9110c55e9286dfbe…` |
| `res/drawable/griver_ui_rotate_process_bar.xml` | 424 | `e3885e4da83a79ee…` |
| `res/drawable/griver_ui_round_red.xml` | 516 | `4fda7a1f0e95f87a…` |
| `res/drawable/griver_ui_segment_shadow_gradient_left.xml` | 420 | `2a8a98e14d8d6ea3…` |
| `res/drawable/griver_ui_segment_shadow_gradient_right.xml` | 420 | `3050cc426bdcc973…` |
| `res/drawable/griver_ui_switch_tab_text_color.xml` | 732 | `8e0cacf56631a58e…` |
| `res/drawable/griver_ui_tab_item_badge_background.xml` | 480 | `d6a67003bcff58ca…` |
| `res/drawable/griver_ui_title_bar_progress.xml` | 424 | `d3a6d014b0d12360…` |
| `res/drawable/griver_ui_title_bar_progress_gold.xml` | 424 | `3ef2cdc03286e8fe…` |
| `res/drawable/griver_ui_title_bar_progress_white.xml` | 424 | `50455660dce15909…` |
| `res/drawable/griver_ui_title_btn_bg.xml` | 636 | `2b4f87fc0925bfec…` |
| `res/drawable/griver_ui_title_btn_bg_r_left.xml` | 692 | `1072ff25e02e78be…` |
| `res/drawable/griver_ui_title_btn_bg_r_right.xml` | 692 | `8b4066bc0ab05633…` |
| `res/drawable/griver_ui_white_corner_bg.xml` | 576 | `357a6b903964bfdb…` |
| `res/drawable/griver_ui_white_corner_bg_night.xml` | 576 | `25533f0f7d211343…` |
| `res/drawable/griver_ui_wv_progress.xml` | 1068 | `6b998ed53016c66a…` |
| `res/drawable/group_124.xml` | 896 | `3a81e3ef4c9ca905…` |
| `res/drawable/group_button_background.xml` | 552 | `005282a6d08f40d7…` |
| `res/drawable/guide_qr.png` | 88486 | `c0cb25c417fde0fc…` |
| `res/drawable/h5_title_bar_back_btn_bg_selector.xml` | 464 | `979be9b377aa3e38…` |
| `res/drawable/h5_title_bar_back_btn_selector.xml` | 464 | `49e30b5d644dec78…` |
| `res/drawable/h5_title_bar_progress.xml` | 424 | `60baa29d16fc4cfc…` |
| `res/drawable/header_background.xml` | 660 | `661d61224344ed6a…` |
| `res/drawable/header_background__vcb.xml` | 660 | `f303458b234f9c74…` |
| `res/drawable/header_standard_app_widget_background.xml` | 536 | `5ed77e3300f7319b…` |
| `res/drawable/hide_info_background.xml` | 484 | `eb9906404746df03…` |
| `res/drawable/ic12_close.xml` | 992 | `339599b58f9df395…` |
| `res/drawable/ic16_general_addcircle.xml` | 1252 | `283b11a1fc704b32…` |
| `res/drawable/ic16_general_back.xml` | 1000 | `f12682e1f29cf613…` |
| `res/drawable/ic16_general_bag.xml` | 1440 | `4e90272f09828d21…` |
| `res/drawable/ic16_general_check.xml` | 1108 | `bef151ec35fad951…` |
| `res/drawable/ic16_general_checkdone.xml` | 1052 | `9fee0118ef8db53c…` |
| `res/drawable/ic16_general_close.xml` | 1192 | `de4aa9491b1d9a0f…` |
| `res/drawable/ic16_general_closecircle.xml` | 1320 | `c5348b997c5bd060…` |
| `res/drawable/ic16_general_coincolor.xml` | 5632 | `63cd321848772d01…` |
| `res/drawable/ic16_general_edit.xml` | 1088 | `2423a55b48365309…` |
| `res/drawable/ic16_general_eyeclose.xml` | 2556 | `71fda940772bed54…` |
| `res/drawable/ic16_general_eyeopen.xml` | 1656 | `96426ec67b911bf4…` |
| `res/drawable/ic16_general_next.xml` | 992 | `5502ac8b4320c25c…` |
| `res/drawable/ic16_general_star.xml` | 1304 | `52ea5dc2fe1e0975…` |
| `res/drawable/ic24_close_floating_menu.xml` | 1012 | `6e788e71fbce22c8…` |
| `res/drawable/ic24_close_nav_bar.xml` | 1012 | `6e788e71fbce22c8…` |
| `res/drawable/ic24_favorite_nav_bar.xml` | 1468 | `a7884ee6c726a45e…` |
| `res/drawable/ic24_general_addsquare.xml` | 1048 | `c99d5b10ae37ce8b…` |
| `res/drawable/ic24_general_back.xml` | 1000 | `918a4fe26fdffc14…` |
| `res/drawable/ic24_general_bellring.xml` | 1800 | `019a16d2a11072a2…` |
| `res/drawable/ic24_general_calendar.xml` | 3252 | `90e1bd1792f06ec6…` |
| `res/drawable/ic24_general_camera.xml` | 1404 | `5aec8bb2aa1292b0…` |
| `res/drawable/ic24_general_check.xml` | 1120 | `6037a2aac70a7d88…` |
| `res/drawable/ic24_general_check_line.xml` | 1012 | `dd39ae291310dde3…` |
| `res/drawable/ic24_general_checkbox.xml` | 1040 | `ab6f0b315b61a682…` |
| `res/drawable/ic24_general_close.xml` | 1192 | `b5480e5e17c6d5ad…` |
| `res/drawable/ic24_general_closecircle.xml` | 1304 | `940c46930ae3b613…` |
| `res/drawable/ic24_general_closecircle_line.xml` | 1416 | `e0704469e37e999c…` |
| `res/drawable/ic24_general_docoper.xml` | 2816 | `0e836914d3ce3570…` |
| `res/drawable/ic24_general_docpolicy_line.xml` | 1720 | `1c91e6442df52ba6…` |
| `res/drawable/ic24_general_down.xml` | 1000 | `e0cc69889db99dbf…` |
| `res/drawable/ic24_general_download_line.xml` | 1288 | `55720f4a4e4d88b4…` |
| `res/drawable/ic24_general_history.xml` | 1448 | `44c7bdc3dda7f8d4…` |
| `res/drawable/ic24_general_info.xml` | 1492 | `a3f9b74c4d593e12…` |
| `res/drawable/ic24_general_info_line.xml` | 1608 | `d9816d79ce52cfdf…` |
| `res/drawable/ic24_general_library.xml` | 1436 | `25af450509b56fb4…` |
| `res/drawable/ic24_general_logout_line.xml` | 1512 | `e81998ec2b8d373c…` |
| `res/drawable/ic24_general_next.xml` | 1000 | `d2a07c18650249e4…` |
| `res/drawable/ic24_general_notice.xml` | 1284 | `9724583f63b1a422…` |
| `res/drawable/ic24_general_notice_line.xml` | 1396 | `f873ab1bb34da601…` |
| `res/drawable/ic24_general_nowifi.xml` | 2016 | `8e59c6123645d8d0…` |
| `res/drawable/ic24_general_nowifi_line.xml` | 1932 | `a644a2b6988e94a6…` |
| `res/drawable/ic24_general_qrcode.xml` | 1788 | `83fe4f9a36bb6b47…` |
| `res/drawable/ic24_general_question_line.xml` | 2384 | `672104eb04b689ef…` |
| `res/drawable/ic24_general_radio_checked.xml` | 916 | `afdfeb3817503925…` |
| `res/drawable/ic24_general_radio_unchecked.xml` | 808 | `c39ff9c505aed3cd…` |
| `res/drawable/ic24_general_rating.xml` | 2640 | `3ac830dd2fd10a71…` |
| `res/drawable/ic24_general_rectangle_checked.xml` | 1052 | `6e98ce869e16147d…` |
| `res/drawable/ic24_general_rectangle_unchecked.xml` | 820 | `16fc0268ef286e02…` |
| `res/drawable/ic24_general_reset.xml` | 1664 | `9e284c25e9709fe8…` |
| `res/drawable/ic24_general_search_line.xml` | 1244 | `802b2b892358da3e…` |
| `res/drawable/ic24_general_secure_line.xml` | 1800 | `17d3782a7239bac3…` |
| `res/drawable/ic24_general_share_line.xml` | 1508 | `211f059cce1361f8…` |
| `res/drawable/ic24_general_star.xml` | 1296 | `dbc7560064f2c615…` |
| `res/drawable/ic24_general_time_line.xml` | 1156 | `ceac4c238d5ea139…` |
| `res/drawable/ic24_general_warning.xml` | 1364 | `c0a10c8e721f78c4…` |
| `res/drawable/ic24_general_warning_line.xml` | 1412 | `cbca42701e1a49b5…` |
| `res/drawable/ic24_general_wifi.xml` | 1768 | `d64f052221f99ac9…` |
| `res/drawable/ic24_option_nav_bar.xml` | 924 | `861d94a60d8b5c72…` |
| `res/drawable/ic36_service_entertain_lottery.xml` | 2336 | `3542b7eaa4a791c2…` |
| `res/drawable/ic36_service_general_more.xml` | 1700 | `8cf9b5b6d3714cf9…` |
| `res/drawable/ic36_service_mywallet_payqr.xml` | 3084 | `d964410f82037e55…` |
| `res/drawable/ic36_service_mywallet_referral.xml` | 5688 | `6a9b543c1ff392af…` |
| `res/drawable/ic36_service_other_tktl.xml` | 5468 | `610b2f50733a618a…` |
| `res/drawable/ic36_service_paybill_bill.xml` | 3516 | `c9e7ac60a18ee52b…` |
| `res/drawable/ic36_service_paybill_tuition.xml` | 3216 | `838f5988f1f1758b…` |
| `res/drawable/ic36_service_telco_buycard.xml` | 2028 | `644fa59dfb6c5d28…` |
| `res/drawable/ic36_service_telco_data.xml` | 1760 | `b494aa43e5ac5bb3…` |
| `res/drawable/ic36_service_telco_topup.xml` | 1560 | `ae43cf78bc15dec4…` |
| `res/drawable/ic36_service_transfer_money.xml` | 3100 | `5373ab26809f59fc…` |
| `res/drawable/ic36_service_travel_transport.xml` | 6696 | `490abec02a255008…` |
| `res/drawable/ic40_homeshortcut_inout.xml` | 1864 | `b56ef6af0502900a…` |
| `res/drawable/ic40_homeshortcut_linkbank.xml` | 3008 | `668e1cf8c24f7685…` |
| `res/drawable/ic40_homeshortcut_promotion.xml` | 2720 | `418720a5bce17b7d…` |
| `res/drawable/ic40_others_zalopay_square.xml` | 4076 | `fa4ebf58e67e3a83…` |
| `res/drawable/ic_24_bank_t2_green.xml` | 1748 | `18e233b0cd0258d7…` |
| `res/drawable/ic_24_vector_balance_settings.xml` | 3776 | `ffaaff08abf4fc3b…` |
| `res/drawable/ic_24_vector_blue_tick_pro.xml` | 840 | `567755649d7746f9…` |
| `res/drawable/ic_24_vector_gift_badge.xml` | 3556 | `bc02744f00a0212f…` |
| `res/drawable/ic_24_vector_history.xml` | 940 | `21397e5257c7aae1…` |
| `res/drawable/ic_24_vector_history_selected.xml` | 940 | `9ceac17f72aff04e…` |
| `res/drawable/ic_24_vector_home.xml` | 2916 | `c02b477fbdc893ff…` |
| `res/drawable/ic_24_vector_home_normal.xml` | 1628 | `15d6a398b775c3fd…` |
| `res/drawable/ic_24_vector_home_selected.xml` | 1132 | `4b226aa4e19c420b…` |
| `res/drawable/ic_24_vector_noti.xml` | 4728 | `49ed3dfb9a0695ef…` |
| `res/drawable/ic_24_vector_personal.xml` | 1184 | `ed21fd48079d7fd3…` |
| `res/drawable/ic_24_vector_personal_selected.xml` | 1184 | `9298d86bf3fb1a09…` |
| `res/drawable/ic_24_vector_search.xml` | 1068 | `e761abfc2e53d310…` |
| `res/drawable/ic_24_vector_st_warning.xml` | 1436 | `9ee28425eec76550…` |
| `res/drawable/ic_38.xml` | 7524 | `bc2fb94283a49f5d…` |
| `res/drawable/ic_40_vector_add_more_app.webp` | 1088 | `37bc3f46acb662f9…` |
| `res/drawable/ic_4_seat_selected.xml` | 14192 | `72d583b5130ffadd…` |
| `res/drawable/ic_4_seat_unselected.xml` | 11452 | `15042d27b20b73f3…` |
| `res/drawable/ic_7_seat_selected.xml` | 9496 | `77fff66a5ced14b7…` |
| `res/drawable/ic_7_seat_unselected.xml` | 8620 | `b10904a3cd9f025a…` |
| `res/drawable/ic_abb.xml` | 10408 | `b5b0504c7e4dda80…` |
| `res/drawable/ic_abb_full_brand.xml` | 5264 | `0af3a176d3b5316a…` |
| `res/drawable/ic_absolute_safety.xml` | 12928 | `e4f2ee2a4d87dde6…` |
| `res/drawable/ic_acb.xml` | 4596 | `5e5b0df26ae9fb74…` |
| `res/drawable/ic_acb_full_brand.xml` | 2492 | `9e0c7cbaf05535e3…` |
| `res/drawable/ic_acc_secure.xml` | 1380 | `7ac9e221b7ecfe4b…` |
| `res/drawable/ic_acc_unsecure.xml` | 1076 | `45dfd0ddb8c6e4ff…` |
| `res/drawable/ic_account_authentic.xml` | 2912 | `98b67b01c8fa9d3d…` |
| `res/drawable/ic_account_bidv.xml` | 6184 | `20182646b4664a83…` |
| `res/drawable/ic_account_hdb.xml` | 38408 | `0419c86c4c538dcb…` |
| `res/drawable/ic_account_ojb.xml` | 1764 | `17f7d8ee536b50e3…` |
| `res/drawable/ic_account_varb.xml` | 11988 | `0f06f7bbe45d5620…` |
| `res/drawable/ic_account_vcb.xml` | 2232 | `27a9bd802ecaabec…` |
| `res/drawable/ic_account_vib.xml` | 2120 | `513d0f421e036971…` |
| `res/drawable/ic_add_app.xml` | 1868 | `5bda21b4ff7ff697…` |
| `res/drawable/ic_add_card.xml` | 1152 | `f11df406b343d50e…` |
| `res/drawable/ic_add_card_2.xml` | 1192 | `3eb3d175dce3d0a4…` |
| `res/drawable/ic_add_money.xml` | 3052 | `dfa407c6dc11a487…` |
| `res/drawable/ic_ai.xml` | 3640 | `875eef95e7344ffd…` |
| `res/drawable/ic_alert.xml` | 17524 | `0f535637d63ba8c0…` |
| `res/drawable/ic_all_service.xml` | 1276 | `ebc9624ff5fc7990…` |
| `res/drawable/ic_allow.xml` | 1004 | `5722149f4347a6af…` |
| `res/drawable/ic_annoucement.xml` | 15540 | `7e8f794c35737b02…` |
| `res/drawable/ic_app_11_rechargephone.xml` | 1676 | `b0a4b506ff0d202c…` |
| `res/drawable/ic_app_12_cardphone.xml` | 2292 | `ec397d47543ee6e4…` |
| `res/drawable/ic_app_15_service.xml` | 1956 | `b0aeacff03f32a8b…` |
| `res/drawable/ic_app_17_payelectric.xml` | 1684 | `495f05a0baa8644c…` |
| `res/drawable/ic_app_18_paywater.xml` | 1216 | `5a70798d518277af…` |
| `res/drawable/ic_app_1_receivemoney.xml` | 2504 | `6b1951c29c933440…` |
| `res/drawable/ic_app_1_transfers.xml` | 2672 | `ba829a77fbad9851…` |
| `res/drawable/ic_app_20_payinternet.xml` | 2664 | `46ffc02bba824afe…` |
| `res/drawable/ic_app_21_paytv.xml` | 1424 | `0010a7ad38eccaab…` |
| `res/drawable/ic_app_24_tiki.xml` | 2484 | `95516470dbc72ff0…` |
| `res/drawable/ic_app_441_datatopup.xml` | 1940 | `f04fe765c5946b37…` |
| `res/drawable/ic_app_451_postpaid.xml` | 3340 | `b31d9555bb4809b3…` |
| `res/drawable/ic_app_599_vetc.xml` | 3364 | `57b73102d2d575cb…` |
| `res/drawable/ic_app_6_red.xml` | 2120 | `68cec7347ae78c1c…` |
| `res/drawable/ic_app_748_lazada.xml` | 2764 | `859ae65ea975716a…` |
| `res/drawable/ic_app_773_covid19.xml` | 1300 | `8d33adcafc066d7c…` |
| `res/drawable/ic_app_791_baemin.xml` | 3528 | `96b288ad393c4238…` |
| `res/drawable/ic_app_804_circlek.xml` | 1748 | `44af73e7885d2d68…` |
| `res/drawable/ic_app_805_egift.xml` | 2704 | `c0e9535c6c0f25fd…` |
| `res/drawable/ic_app_833_apartment.xml` | 1124 | `c93738fdc84ab3e2…` |
| `res/drawable/ic_app_896_offlinestore.xml` | 1620 | `fdcfa95ff9407c66…` |
| `res/drawable/ic_app_906_donation.xml` | 2088 | `f69528739c2d116b…` |
| `res/drawable/ic_app_android.xml` | 1924 | `fa8ffa7999f158f4…` |
| `res/drawable/ic_app_auto_debit.xml` | 2888 | `77f0779317aa200d…` |
| `res/drawable/ic_app_billing.xml` | 2572 | `ff6fd527f7171cac…` |
| `res/drawable/ic_app_component_badge_gift.xml` | 3556 | `bc02744f00a0212f…` |
| `res/drawable/ic_app_consumer_loan.xml` | 1764 | `1ead98b2ddf3fde8…` |
| `res/drawable/ic_app_dues.xml` | 1872 | `55f0c660cb05d3af…` |
| `res/drawable/ic_app_gift.xml` | 20176 | `a56a2fc61b02c1c6…` |
| `res/drawable/ic_app_googleplay.xml` | 3180 | `3320a150eefcc623…` |
| `res/drawable/ic_app_homestay.xml` | 2716 | `ccf25115138f38f6…` |
| `res/drawable/ic_app_linkcard.xml` | 1844 | `4804de0665d51f7d…` |
| `res/drawable/ic_app_luckybest.xml` | 6096 | `0fdef9cb312f8374…` |
| `res/drawable/ic_app_more.xml` | 1944 | `68b0137d72b399cd…` |
| `res/drawable/ic_app_payment_bus.xml` | 1984 | `729f58df449e12c4…` |
| `res/drawable/ic_app_payment_card.xml` | 1436 | `9bf6958dd2a2df59…` |
| `res/drawable/ic_app_recharge.xml` | 2300 | `bc13cca1723d3dac…` |
| `res/drawable/ic_app_shopping.xml` | 3304 | `500bfa5d951ee2a0…` |
| `res/drawable/ic_app_tch.xml` | 6264 | `92b2b514613d36bb…` |
| `res/drawable/ic_app_ticketfilm.xml` | 1668 | `ee257938ddb47266…` |
| `res/drawable/ic_app_ticketplane.xml` | 1880 | `e326a33610c5c19e…` |
| `res/drawable/ic_app_transport.xml` | 5032 | `a004ea4e304b516f…` |
| `res/drawable/ic_app_withdraw.xml` | 2240 | `82ec713a93d1d463…` |
| `res/drawable/ic_app_zingcard.xml` | 2428 | `a8f02603d31e897c…` |
| `res/drawable/ic_application.xml` | 2296 | `2488a9a18664d9f6…` |
| `res/drawable/ic_arrow_back.xml` | 612 | `f52900bb6f7b3765…` |
| `res/drawable/ic_arrow_back_alt.xml` | 612 | `249df1f5febc1fe6…` |
| `res/drawable/ic_arrow_bind.xml` | 1284 | `d4c27471dc3447d9…` |
| `res/drawable/ic_arrow_down.xml` | 760 | `1a1605c2c8708633…` |
| `res/drawable/ic_arrow_down_2.xml` | 760 | `3b6f773491ce7837…` |
| `res/drawable/ic_arrow_loyalty.xml` | 868 | `aa97b9c50cdef0d1…` |
| `res/drawable/ic_arrow_reminders.xml` | 828 | `faec29f15b445ad0…` |
| `res/drawable/ic_arrow_right_2.xml` | 756 | `893baaf42aa38ce3…` |
| `res/drawable/ic_arrow_right_blue.xml` | 808 | `582ac10465e5396a…` |
| `res/drawable/ic_arrow_right_header.xml` | 952 | `8bf0e9ca210129a8…` |
| `res/drawable/ic_arrow_right_primary_blue.xml` | 872 | `fbd89163f7cf672e…` |
| `res/drawable/ic_arrow_right_primary_dark.xml` | 872 | `1ac65c4f2d494836…` |
| `res/drawable/ic_arrow_selector.xml` | 648 | `32c3dafe71d887a0…` |
| `res/drawable/ic_art_work_notification_download_app.xml` | 7120 | `a771634b23e75988…` |
| `res/drawable/ic_art_work_notification_kyc_sending.xml` | 15796 | `5016c1253580a456…` |
| `res/drawable/ic_art_work_notification_maintenance_service.xml` | 5512 | `83c901a43eb90e36…` |
| `res/drawable/ic_art_work_over_threshold.xml` | 3124 | `343aab2b42bab63b…` |
| `res/drawable/ic_artwork_failed.xml` | 1312 | `6f5af4682caa9683…` |
| `res/drawable/ic_artwork_success.xml` | 1160 | `1dc2b17eb85dea55…` |
| `res/drawable/ic_bab.xml` | 7120 | `25902befa21bbe04…` |
| `res/drawable/ic_bab_full_brand.xml` | 14856 | `ee29ad1b91e599d6…` |
| `res/drawable/ic_back.xml` | 756 | `e608d7ec99133a2e…` |
| `res/drawable/ic_back_2.xml` | 744 | `c7ee3f64148f1aee…` |
| `res/drawable/ic_back_arrow.xml` | 696 | `78c678042e36f86f…` |
| `res/drawable/ic_back_arrow_2.xml` | 872 | `e06189e7a9e7546b…` |
| `res/drawable/ic_back_button.png` | 843 | `6725c8d8f13f1c26…` |
| `res/drawable/ic_back_ekyc.xml` | 688 | `e68d8bb764eac9ca…` |
| `res/drawable/ic_back_grey.xml` | 800 | `6d1dda876a830d65…` |
| `res/drawable/ic_background_label_home.xml` | 748 | `750b37bc386e6db5…` |
| `res/drawable/ic_background_vcb.xml` | 34420 | `b6dd4cc01b55eebe…` |
| `res/drawable/ic_badge.xml` | 1716 | `9de296effb5f13c7…` |
| `res/drawable/ic_badge_label.xml` | 744 | `4e8973442c769bc5…` |
| `res/drawable/ic_badge_ribbon.xml` | 596 | `aec0e9e6cb306bd0…` |
| `res/drawable/ic_balance_disabled.xml` | 3060 | `4fd3bc54092d06ae…` |
| `res/drawable/ic_bankaccounts.xml` | 1288 | `54b872c55b3704b8…` |
| `res/drawable/ic_bankcard.xml` | 1244 | `b9a55201216d7e17…` |
| `res/drawable/ic_bankcards.xml` | 1244 | `b9a55201216d7e17…` |
| `res/drawable/ic_banner_default_background.xml` | 1272 | `ce31cb6cdd84b577…` |
| `res/drawable/ic_banner_ibft_kyc.xml` | 7288 | `3f0c0868cc998b13…` |
| `res/drawable/ic_banner_type_16_9.xml` | 2824 | `627b1b8b7c4edde6…` |
| `res/drawable/ic_banner_type_1_1.xml` | 2916 | `c5e808f9f7fdb940…` |
| `res/drawable/ic_banner_type_2_1.xml` | 2788 | `156f4a2d01255a1f…` |
| `res/drawable/ic_banner_type_3_1.xml` | 2788 | `cff2b07e31f818c8…` |
| `res/drawable/ic_banner_type_4_1.xml` | 2788 | `cc3db3588e517317…` |
| `res/drawable/ic_barcode_code_scanner.xml` | 4876 | `1e8993284206c4f0…` |
| `res/drawable/ic_beauty_success.xml` | 2960 | `6b047be6591242f6…` |
| `res/drawable/ic_bg_amount_voucher.xml` | 2664 | `2e3c66c477744097…` |
| `res/drawable/ic_bidv.xml` | 6184 | `20182646b4664a83…` |
| `res/drawable/ic_bidv_card_logo.xml` | 6184 | `20182646b4664a83…` |
| `res/drawable/ic_bidv_full_brand.xml` | 5596 | `d91eb997ac20f195…` |
| `res/drawable/ic_bill.xml` | 2520 | `aa33ee0eea9d9277…` |
| `res/drawable/ic_bill_reminder.xml` | 6352 | `a6196d0b18661045…` |
| `res/drawable/ic_blank.xml` | 952 | `1f4f7c66319448b9…` |
| `res/drawable/ic_blue_down_arrow.xml` | 684 | `c0c4d951db47a675…` |
| `res/drawable/ic_blue_promotion_badge.xml` | 2352 | `e875e8a994a6c97b…` |
| `res/drawable/ic_blue_right_arrow.xml` | 932 | `fb1182cbc392f2ce…` |
| `res/drawable/ic_blue_selected.xml` | 808 | `b53f5a2b95ccb4ca…` |
| `res/drawable/ic_blue_take_photo.webp` | 10916 | `2f43efec323dcbf6…` |
| `res/drawable/ic_blue_user.xml` | 1288 | `54b872c55b3704b8…` |
| `res/drawable/ic_blur_kyc.png` | 4258 | `cef0a897e1fd19ae…` |
| `res/drawable/ic_bottom_bank.xml` | 752 | `0e6e27472a2a83a5…` |
| `res/drawable/ic_bottom_nav_bar_tab_promotion.png` | 10080 | `09117982357b0b48…` |
| `res/drawable/ic_btn_close_reminder.xml` | 1052 | `a712c94bf67186f4…` |
| `res/drawable/ic_bulk.xml` | 3228 | `2fefef65146ce0b1…` |
| `res/drawable/ic_bvb.xml` | 4024 | `0c884932da5acb8c…` |
| `res/drawable/ic_bvb_full_brand.xml` | 8304 | `e248de1b3cbbf420…` |
| `res/drawable/ic_calendar.xml` | 1460 | `3cd0d3c78c365364…` |
| `res/drawable/ic_call_answer.xml` | 1356 | `5f6376dfe199d8a6…` |
| `res/drawable/ic_call_answer_low.xml` | 1320 | `8f9acacabaf2dfb4…` |
| `res/drawable/ic_call_answer_video.xml` | 772 | `5d4cdaae5838ad94…` |
| `res/drawable/ic_call_answer_video_low.xml` | 736 | `4a959580080ebb1d…` |
| `res/drawable/ic_call_decline.xml` | 1528 | `2d825b79cce60203…` |
| `res/drawable/ic_call_decline_low.xml` | 1492 | `c8321364802409ba…` |
| `res/drawable/ic_call_taxi.xml` | 1000 | `9a64dbe1b679bfe9…` |
| `res/drawable/ic_call_taxi3.xml` | 2480 | `2f82e68264df7c23…` |
| `res/drawable/ic_camera.xml` | 1416 | `f0bd8539e94a16d2…` |
| `res/drawable/ic_camera_new_ekyc.xml` | 1792 | `5b61172e66a83be8…` |
| `res/drawable/ic_camera_rotate.xml` | 2396 | `5915a5ae415b053e…` |
| `res/drawable/ic_camera_small.webp` | 194 | `bf94150346ca61aa…` |
| `res/drawable/ic_campaingn.xml` | 10264 | `c18eb6d569848010…` |
| `res/drawable/ic_capture.xml` | 960 | `7c88772b49801125…` |
| `res/drawable/ic_capture_ekyc.xml` | 1676 | `81348fbc3b598faa…` |
| `res/drawable/ic_capture_photo.xml` | 960 | `7c88772b49801125…` |
| `res/drawable/ic_car_new_ekyc.xml` | 1464 | `3497f7df8e6fda58…` |
| `res/drawable/ic_card.xml` | 2176 | `5355696032c0397d…` |
| `res/drawable/ic_card_bidv.xml` | 6184 | `20182646b4664a83…` |
| `res/drawable/ic_card_hdb.xml` | 38408 | `123e48bf6752a454…` |
| `res/drawable/ic_card_link.xml` | 3956 | `cc02a3d42fdf4107…` |
| `res/drawable/ic_card_new_ekyc.xml` | 1572 | `a4f628ac342f3bc5…` |
| `res/drawable/ic_card_ojb.xml` | 1764 | `17f7d8ee536b50e3…` |
| `res/drawable/ic_card_varb.xml` | 11988 | `0f06f7bbe45d5620…` |
| `res/drawable/ic_card_vcb.xml` | 2232 | `f2d626c0f95bc5e0…` |
| `res/drawable/ic_card_vib.xml` | 2120 | `513d0f421e036971…` |
| `res/drawable/ic_cashier_add_promotion_new.xml` | 2532 | `638e134ad4d3d3b8…` |
| `res/drawable/ic_cashier_add_promotion_v3.xml` | 2224 | `5c33b53410ee6081…` |
| `res/drawable/ic_cashier_add_sof.xml` | 4520 | `cd4ce66317472e73…` |
| `res/drawable/ic_cashier_add_sof_v3.xml` | 4520 | `cd4ce66317472e73…` |
| `res/drawable/ic_cashier_amount_v3.xml` | 2020 | `9a1ed10435630914…` |
| `res/drawable/ic_cashier_arrow.xml` | 828 | `59eddb1c6bdcfb32…` |
| `res/drawable/ic_cashier_arrow_16_v3.xml` | 844 | `c20d1bdd0cb5eac0…` |
| `res/drawable/ic_cashier_arrow_blue_v3.xml` | 840 | `9727be6bb5d37255…` |
| `res/drawable/ic_cashier_arrow_down.xml` | 1160 | `87a9fbe48a4d6e09…` |
| `res/drawable/ic_cashier_arrow_up_16_v3.xml` | 840 | `3bfc47f6adf1e4ab…` |
| `res/drawable/ic_cashier_arrow_v3.xml` | 840 | `79d2e21682c0ab25…` |
| `res/drawable/ic_cashier_auth_waiting.xml` | 7492 | `6aef3ab390f104f9…` |
| `res/drawable/ic_cashier_back.xml` | 888 | `7e08091613caf062…` |
| `res/drawable/ic_cashier_back_new.xml` | 1024 | `33deba9d115649d2…` |
| `res/drawable/ic_cashier_button_skeleton.xml` | 1072 | `3cf941de489b1d37…` |
| `res/drawable/ic_cashier_change_sof.xml` | 3304 | `aa8ad4ae59b0ba0d…` |
| `res/drawable/ic_cashier_check.xml` | 852 | `56c05f31b78ce8bc…` |
| `res/drawable/ic_cashier_check_v3.xml` | 852 | `56c05f31b78ce8bc…` |
| `res/drawable/ic_cashier_close.xml` | 1080 | `975765f3edb3d97c…` |
| `res/drawable/ic_cashier_close_v3.xml` | 1004 | `5bde2083af50658a…` |
| `res/drawable/ic_cashier_coin.xml` | 2908 | `0e227cb9aaa93f1c…` |
| `res/drawable/ic_cashier_coin_entry_point.xml` | 47028 | `22e5d24be5d7c8fb…` |
| `res/drawable/ic_cashier_countdown.xml` | 984 | `886ec2c0c0cab163…` |
| `res/drawable/ic_cashier_danger.xml` | 1696 | `6e7d4d07e908c542…` |
| `res/drawable/ic_cashier_dialog_close.xml` | 1000 | `ae6d378d52adc12d…` |
| `res/drawable/ic_cashier_duplicate.xml` | 7688 | `7362e4d9182628e4…` |
| `res/drawable/ic_cashier_fee.xml` | 1596 | `ab488cb3f6702022…` |
| `res/drawable/ic_cashier_fee_new.xml` | 1648 | `2c1096fd7344e4dc…` |
| `res/drawable/ic_cashier_fee_v3.xml` | 1956 | `aa430b95a9efd89c…` |
| `res/drawable/ic_cashier_general_arrow.xml` | 852 | `27198cd3d5ea22a0…` |
| `res/drawable/ic_cashier_general_check.xml` | 932 | `a89266b1227d7a1f…` |
| `res/drawable/ic_cashier_general_check_v3.xml` | 932 | `a89266b1227d7a1f…` |
| `res/drawable/ic_cashier_general_error.xml` | 6240 | `8e79f371811ec0e9…` |
| `res/drawable/ic_cashier_general_error_v3.xml` | 6240 | `f740bb399eef7c6b…` |
| `res/drawable/ic_cashier_general_question_solid.xml` | 1400 | `3910e0c187b9c57a…` |
| `res/drawable/ic_cashier_info_error.xml` | 1108 | `74ec37e61412db17…` |
| `res/drawable/ic_cashier_info_error_v3.xml` | 1108 | `74ec37e61412db17…` |
| `res/drawable/ic_cashier_info_informative.xml` | 1296 | `1b5ad8e726aa98a5…` |
| `res/drawable/ic_cashier_info_informative_v3.xml` | 1296 | `1b5ad8e726aa98a5…` |
| `res/drawable/ic_cashier_info_skeleton.xml` | 3428 | `cdd1b2a2fad83387…` |
| `res/drawable/ic_cashier_info_success.xml` | 988 | `693ea5f5d084c033…` |
| `res/drawable/ic_cashier_info_success_v3.xml` | 988 | `693ea5f5d084c033…` |
| `res/drawable/ic_cashier_info_v3.xml` | 1396 | `9b3a601210f553c8…` |
| `res/drawable/ic_cashier_info_warning.xml` | 1160 | `da5dbcb37b4fd268…` |
| `res/drawable/ic_cashier_info_warning_v3.xml` | 1160 | `da5dbcb37b4fd268…` |
| `res/drawable/ic_cashier_loading.xml` | 2496 | `4888b0d0c482df33…` |
| `res/drawable/ic_cashier_network_error.xml` | 14116 | `e48d38ae7445846e…` |
| `res/drawable/ic_cashier_network_error_v3.xml` | 8152 | `3b5cbe91e056bc2e…` |
| `res/drawable/ic_cashier_network_offline.xml` | 1492 | `15cb69c59f56a7b3…` |
| `res/drawable/ic_cashier_network_online.xml` | 1120 | `5a2abf29e93f0c17…` |
| `res/drawable/ic_cashier_no_promotion.png` | 41430 | `62ede31fac2d71c5…` |
| `res/drawable/ic_cashier_notification.png` | 34746 | `eb126fe1e676993f…` |
| `res/drawable/ic_cashier_order_detail_bill.xml` | 3100 | `f00238c7c57341d5…` |
| `res/drawable/ic_cashier_pcidss.xml` | 9508 | `dd3220f7d5c45981…` |
| `res/drawable/ic_cashier_percent_solid.xml` | 1820 | `6b7c0e2684634e09…` |
| `res/drawable/ic_cashier_percent_solid_mono.xml` | 3768 | `d86345c8a9f6e351…` |
| `res/drawable/ic_cashier_promotion.xml` | 2224 | `5c33b53410ee6081…` |
| `res/drawable/ic_cashier_promotion_applied.xml` | 2072 | `59b884ce7ed40395…` |
| `res/drawable/ic_cashier_promotion_applied_v3.xml` | 1896 | `4fde9f02efafd321…` |
| `res/drawable/ic_cashier_promotion_apply_error.xml` | 2212 | `e0ccc3c9cdb4ecbd…` |
| `res/drawable/ic_cashier_promotion_clear.xml` | 1172 | `7153841af3db4d5c…` |
| `res/drawable/ic_cashier_promotion_default.xml` | 2592 | `6d7f97336c5277c5…` |
| `res/drawable/ic_cashier_promotion_dialog.xml` | 22768 | `11072e230930ff32…` |
| `res/drawable/ic_cashier_promotion_error.xml` | 22360 | `e4501469bc81f48f…` |
| `res/drawable/ic_cashier_promotion_error_v3.xml` | 2076 | `f87f7690b7116385…` |
| `res/drawable/ic_cashier_promotion_info.xml` | 1396 | `9b3a601210f553c8…` |
| `res/drawable/ic_cashier_promotion_item_fail.xml` | 1156 | `8112a524abc77542…` |
| `res/drawable/ic_cashier_promotion_item_fail_new.xml` | 1888 | `5e7e398a397e6a95…` |
| `res/drawable/ic_cashier_promotion_item_loading.xml` | 3372 | `214a5e6b90ad43f0…` |
| `res/drawable/ic_cashier_promotion_item_success.xml` | 996 | `e242e28891db290b…` |
| `res/drawable/ic_cashier_promotion_png.png` | 34936 | `8ad07d5b2f0a58e8…` |
| `res/drawable/ic_cashier_radiobox_selected.xml` | 1020 | `0331d76b1b0dfe1e…` |
| `res/drawable/ic_cashier_radiobox_unselected.xml` | 908 | `99ba3a2816be3013…` |
| `res/drawable/ic_cashier_secure.xml` | 18308 | `57f8ad1f2f37d191…` |
| `res/drawable/ic_cashier_secure_new.xml` | 1276 | `3faf49cb94ed5bdd…` |
| `res/drawable/ic_cashier_secure_v3.xml` | 1252 | `afd6064cdef8bd88…` |
| `res/drawable/ic_cashier_sof_skeleton.xml` | 2808 | `0be88025b3fab2a5…` |
| `res/drawable/ic_cashier_sponsor_voucher_fail.xml` | 1888 | `5e7e398a397e6a95…` |
| `res/drawable/ic_cashier_sponsor_voucher_success.xml` | 996 | `e242e28891db290b…` |
| `res/drawable/ic_cashier_system_error.xml` | 23120 | `b2aceaca6a5adfb8…` |
| `res/drawable/ic_cashier_toolbar_back.xml` | 892 | `55b1a2d364533e15…` |
| `res/drawable/ic_cashier_tooltip_arrow.xml` | 564 | `f735912166798ed7…` |
| `res/drawable/ic_cashier_tooltip_question.xml` | 1980 | `8ae23d9a47749688…` |
| `res/drawable/ic_cashier_tooltip_v3.xml` | 1596 | `ab488cb3f6702022…` |
| `res/drawable/ic_cashier_tutorial_v3.xml` | 8656 | `5c2f3b32959f81af…` |
| `res/drawable/ic_cashier_warning_new.xml` | 1564 | `8d139c134b83da7f…` |
| `res/drawable/ic_cashier_warning_v3.xml` | 1160 | `fa184ae4ba05cdff…` |
| `res/drawable/ic_cc_card_result.xml` | 4884 | `68d30762489f2fbd…` |
| `res/drawable/ic_cc_card_result_fail.xml` | 7640 | `b0dae48dc22f59ff…` |
| `res/drawable/ic_ce_toro_png.png` | 7108 | `bb9dd9155ba47972…` |
| `res/drawable/ic_chamtron.xml` | 408 | `5234352c1476b045…` |
| `res/drawable/ic_chamtronv2.xml` | 1080 | `ef1e12d77505a35d…` |
| `res/drawable/ic_check2.xml` | 864 | `9c988117353c655f…` |
| `res/drawable/ic_check_done.xml` | 816 | `0b4501acbf941573…` |
| `res/drawable/ic_check_done_2.xml` | 824 | `2945e06de6d67e79…` |
| `res/drawable/ic_check_done_white.xml` | 820 | `ac7404f70655ac28…` |
| `res/drawable/ic_check_index1.xml` | 1688 | `7c4802970c6ab93c…` |
| `res/drawable/ic_check_index2.xml` | 2276 | `bdd71bee1a32e56d…` |
| `res/drawable/ic_check_selector_radio_button.xml` | 484 | `8953742719504d71…` |
| `res/drawable/ic_check_tick.xml` | 648 | `18c372cdfff9c00e…` |
| `res/drawable/ic_checkbox_circle_ekyc.xml` | 760 | `b30f24e0f5546856…` |
| `res/drawable/ic_checkbox_red.xml` | 484 | `b32325b48180a0bd…` |
| `res/drawable/ic_checkbox_red_checked.xml` | 1008 | `64bcd9413ed5d6c5…` |
| `res/drawable/ic_checkbox_unchecked.xml` | 768 | `4b63ebea495b0123…` |
| `res/drawable/ic_checked_circle.xml` | 1028 | `9292ec59e70fb1dd…` |
| `res/drawable/ic_cimb.xml` | 2616 | `63cdd13df4d16901…` |
| `res/drawable/ic_cimb_full_brand.xml` | 2748 | `224f5887235d7ad2…` |
| `res/drawable/ic_circle.xml` | 692 | `0026c79b732160eb…` |
| `res/drawable/ic_circle_armenia.xml` | 1288 | `525470a31ab20af8…` |
| `res/drawable/ic_circle_australia.xml` | 3704 | `d008277b79bc7654…` |
| `res/drawable/ic_circle_austria.xml` | 1284 | `496cfecc722554be…` |
| `res/drawable/ic_circle_belgium.xml` | 1292 | `82fe2aa6750117a0…` |
| `res/drawable/ic_circle_bulgaria.xml` | 1284 | `18df2a620e864458…` |
| `res/drawable/ic_circle_cambodia.xml` | 1596 | `1e3120109b9faa25…` |
| `res/drawable/ic_circle_canada.xml` | 1540 | `e719b84d252b977c…` |
| `res/drawable/ic_circle_chile.xml` | 1444 | `ccb8dbed3d5258b7…` |
| `res/drawable/ic_circle_china.xml` | 2128 | `3f447377e91621d7…` |
| `res/drawable/ic_circle_croatia.xml` | 4576 | `dbbb8ddb3c639378…` |
| `res/drawable/ic_circle_cyprus.xml` | 1816 | `736419eb5e5ed96a…` |
| `res/drawable/ic_circle_czech_republic.xml` | 1264 | `22200a1e07732e78…` |
| `res/drawable/ic_circle_denmark.xml` | 1616 | `058112c1eb9c3de6…` |
| `res/drawable/ic_circle_estonia.xml` | 1292 | `9fba915a91415a75…` |
| `res/drawable/ic_circle_finland.xml` | 1256 | `2be4595de86e5e7c…` |
| `res/drawable/ic_circle_france.xml` | 1208 | `5274876944f23cc5…` |
| `res/drawable/ic_circle_germany.xml` | 1296 | `57d765c43c334c7a…` |
| `res/drawable/ic_circle_greece.xml` | 1896 | `5573869ee99e992b…` |
| `res/drawable/ic_circle_hongkong.xml` | 3616 | `46b534125a3e6a90…` |
| `res/drawable/ic_circle_hungary.xml` | 1292 | `0aeab5531413da11…` |
| `res/drawable/ic_circle_iceland.xml` | 2000 | `17a7b79e94d27989…` |
| `res/drawable/ic_circle_india.xml` | 2020 | `a0cac2e074712841…` |
| `res/drawable/ic_circle_indonesia.xml` | 1004 | `c26430ef015f3b6b…` |
| `res/drawable/ic_circle_ireland.xml` | 1204 | `f40327ffe0f2dd96…` |
| `res/drawable/ic_circle_italy.xml` | 1204 | `bd0b26c50b58a31b…` |
| `res/drawable/ic_circle_japan.xml` | 1116 | `3305d6963896c325…` |
| `res/drawable/ic_circle_kazakhstan.xml` | 1840 | `6c4b088c117ecea2…` |
| `res/drawable/ic_circle_laos.xml` | 1588 | `3102f9d8f75ccf95…` |
| `res/drawable/ic_circle_latvia.xml` | 1204 | `715d61f9c8406b21…` |
| `res/drawable/ic_circle_liechtenstein.xml` | 1444 | `d29f431e5a06bbfb…` |
| `res/drawable/ic_circle_lithuania.xml` | 1296 | `6baa74dd374356bd…` |
| `res/drawable/ic_circle_luxembourg.xml` | 1204 | `5203e77d6b86e4aa…` |
| `res/drawable/ic_circle_macau.xml` | 5696 | `6dbee4faf4a13a9d…` |
| `res/drawable/ic_circle_malaysia.xml` | 2492 | `47f16f499c905adb…` |
| `res/drawable/ic_circle_maldives.xml` | 1432 | `8bd6ffc7740a5e9b…` |
| `res/drawable/ic_circle_malta.xml` | 1204 | `291538966422f6c5…` |
| `res/drawable/ic_circle_mauritius.xml` | 1448 | `17598403d1d22d6a…` |
| `res/drawable/ic_circle_mongolia.xml` | 2648 | `c2a9a097686a914e…` |
| `res/drawable/ic_circle_nepal.xml` | 2436 | `9ac8264745eb5c9f…` |
| `res/drawable/ic_circle_netherlands.xml` | 1208 | `c3b0a0bea2cbafd9…` |
| `res/drawable/ic_circle_new_zealand.xml` | 2728 | `a4efef98eb887c98…` |
| `res/drawable/ic_circle_norway.xml` | 2004 | `93963ca95ffc3bd2…` |
| `res/drawable/ic_circle_philippines.xml` | 2384 | `52c7151071bb66bf…` |
| `res/drawable/ic_circle_poland.xml` | 1008 | `1ce1cfe500f4a7cd…` |
| `res/drawable/ic_circle_portugal.xml` | 1776 | `ee8ba21dc7b7d7aa…` |
| `res/drawable/ic_circle_qatar.xml` | 1252 | `c07c76b066a4b679…` |
| `res/drawable/ic_circle_romania.xml` | 1296 | `6db2efc8dc6e7460…` |
| `res/drawable/ic_circle_russia.xml` | 1308 | `084974f8f77c38db…` |
| `res/drawable/ic_circle_samoa.xml` | 2212 | `1f6dcb890d872b22…` |
| `res/drawable/ic_circle_san_marino.xml` | 2672 | `f60aabfab2f0d891…` |
| `res/drawable/ic_circle_singapore.xml` | 2564 | `87a8645710653f00…` |
| `res/drawable/ic_circle_slovakia.xml` | 2480 | `4a069eb024923c42…` |
| `res/drawable/ic_circle_slovenia.xml` | 4600 | `495b1133cec1f460…` |
| `res/drawable/ic_circle_south_korea.xml` | 4456 | `14711db9b44ab0ba…` |
| `res/drawable/ic_circle_spain.xml` | 1296 | `d49d33d1e9716948…` |
| `res/drawable/ic_circle_sri_lanka.xml` | 2280 | `155aa66e1e285fdd…` |
| `res/drawable/ic_circle_sweden.xml` | 1620 | `d326c68091df3aa6…` |
| `res/drawable/ic_circle_switzerland.xml` | 1048 | `e3df6f0edb685347…` |
| `res/drawable/ic_circle_thailand.xml` | 1476 | `4b89018d079133c1…` |
| `res/drawable/ic_circle_turkey.xml` | 1544 | `85fe76e708754134…` |
| `res/drawable/ic_circle_united_arab_emirates.xml` | 1428 | `e7a4f5cc231e4798…` |
| `res/drawable/ic_circle_united_kingdom.xml` | 3560 | `431876e9d04ccddf…` |
| `res/drawable/ic_circle_united_states.xml` | 2736 | `8e81838ddddece2d…` |
| `res/drawable/ic_circle_vietnam.xml` | 1080 | `53b1981b05d56e40…` |
| `res/drawable/ic_citibank_full_brand.xml` | 2668 | `2a259bd9f9ab218f…` |
| `res/drawable/ic_clear.xml` | 1252 | `4fa1c577ba693200…` |
| `res/drawable/ic_clear_2.xml` | 1488 | `5f6f2bc32de62af1…` |
| `res/drawable/ic_clock_black_24dp.xml` | 752 | `36a71a00f1449b91…` |
| `res/drawable/ic_close_account_failed.xml` | 7584 | `78689847eb371d41…` |
| `res/drawable/ic_close_black.xml` | 1056 | `bc884d633f9a2e53…` |
| `res/drawable/ic_close_bottom_dialog.xml` | 684 | `9c39b1ab066c0a7d…` |
| `res/drawable/ic_close_button_001f3e.xml` | 1052 | `6b94e1bd8d013196…` |
| `res/drawable/ic_close_ce.xml` | 1296 | `674e726722e528a9…` |
| `res/drawable/ic_close_dark_black.xml` | 1056 | `bc884d633f9a2e53…` |
| `res/drawable/ic_close_home_floating_image.xml` | 1388 | `8cfd6a94c774498c…` |
| `res/drawable/ic_close_screen.xml` | 1260 | `301988b8bba1acf5…` |
| `res/drawable/ic_close_t1.xml` | 1076 | `c2438f7833e3f3b7…` |
| `res/drawable/ic_close_vd_white_24.xml` | 648 | `f61d956a00c8f7a9…` |
| `res/drawable/ic_collase_search.xml` | 892 | `4aa2436f2e453f30…` |
| `res/drawable/ic_confirm.xml` | 7516 | `105d304587d9f383…` |
| `res/drawable/ic_confirm_cancel.png` | 5968 | `8c5e9a004b367426…` |
| `res/drawable/ic_congrats_gift.xml` | 13508 | `fc7fe9ebe3f55acf…` |
| `res/drawable/ic_contacts.xml` | 1224 | `e6dc7a811ecc8f46…` |
| `res/drawable/ic_contacts_close.xml` | 1000 | `ae6d378d52adc12d…` |
| `res/drawable/ic_coopb.xml` | 1840 | `0deda502ac8d0eec…` |
| `res/drawable/ic_coopb_full_brand.xml` | 9372 | `10bc2e4b9edd7e25…` |
| `res/drawable/ic_copy.xml` | 1716 | `fb6acb1b1099c811…` |
| `res/drawable/ic_copy_out_line.xml` | 1716 | `8373100b954ee469…` |
| `res/drawable/ic_cs_notification.xml` | 15832 | `2bd0ca80d2477f4e…` |
| `res/drawable/ic_current_location.xml` | 808 | `39d0ae117391091f…` |
| `res/drawable/ic_custom_expand.xml` | 608 | `83aec6761c24a72b…` |
| `res/drawable/ic_custom_expand_dark.xml` | 608 | `4a27e52ffe310dff…` |
| `res/drawable/ic_custom_search.xml` | 796 | `50564f504f874392…` |
| `res/drawable/ic_cut_kyc.png` | 8848 | `e5492da4cb59766a…` |
| `res/drawable/ic_dab.xml` | 2540 | `7ddb6917f959218c…` |
| `res/drawable/ic_dab_full_brand.xml` | 7332 | `0887ffac1392da6b…` |
| `res/drawable/ic_default_card_logo.xml` | 1324 | `77029c1aaa455769…` |
| `res/drawable/ic_del_18.xml` | 1488 | `5f6f2bc32de62af1…` |
| `res/drawable/ic_disable_view_balance.xml` | 1564 | `0561b8b606ccdced…` |
| `res/drawable/ic_discount_selected.xml` | 2328 | `225ecfd831d71f4a…` |
| `res/drawable/ic_display_page.xml` | 21352 | `ccdc3df779ed0431…` |
| `res/drawable/ic_download.xml` | 1084 | `8c30e8fb7fb26ea3…` |
| `res/drawable/ic_download_2.xml` | 1924 | `82255976e6a57901…` |
| `res/drawable/ic_downloading.xml` | 5628 | `95097733f2cdb40d…` |
| `res/drawable/ic_dropdown_arrow.xml` | 1016 | `b2c553d3ea365c6e…` |
| `res/drawable/ic_easy.xml` | 2872 | `7b596209bf124bfc…` |
| `res/drawable/ic_eib.xml` | 3520 | `cf967ce474811f82…` |
| `res/drawable/ic_eib_card_logo.xml` | 4096 | `c14349d49e38e984…` |
| `res/drawable/ic_eib_full_brand.xml` | 4940 | `5e2fc8aacbc45fbd…` |
| `res/drawable/ic_ekyc_intro_indicator.png` | 40705 | `ec375e1dc5858f2c…` |
| `res/drawable/ic_ekyc_intro_requirement_cccd.png` | 11843 | `61bd08f0581fcd9b…` |
| `res/drawable/ic_ekyc_loading.xml` | 2860 | `b5cbdc6696c3b38c…` |
| `res/drawable/ic_ekyc_pro_data_not_found.xml` | 14176 | `a825abb94fe8d2e8…` |
| `res/drawable/ic_ekyc_pro_noitice.xml` | 1436 | `fb9dfda271f2bb11…` |
| `res/drawable/ic_empty_bank.xml` | 6308 | `2cbbbc9c3b5eab97…` |
| `res/drawable/ic_empty_sketch_06.xml` | 9452 | `6202ef04f3d5d314…` |
| `res/drawable/ic_enable_view_balance.xml` | 1440 | `c06b55cadcd9d44b…` |
| `res/drawable/ic_error_page.xml` | 4708 | `5937241b8f3c71bb…` |
| `res/drawable/ic_error_vneid.webp` | 12818 | `a2c2ccd2e953f780…` |
| `res/drawable/ic_exit_dark_black.xml` | 1056 | `bc884d633f9a2e53…` |
| `res/drawable/ic_external_placeholder.xml` | 3112 | `908afd9569070bc0…` |
| `res/drawable/ic_eye_hide.xml` | 1268 | `5159391c1dc2303d…` |
| `res/drawable/ic_eye_hide_white.xml` | 1768 | `751242e5edeced47…` |
| `res/drawable/ic_eye_show.xml` | 832 | `25b733998a3bcdb2…` |
| `res/drawable/ic_eye_show_2.xml` | 1004 | `9abac620c48e548f…` |
| `res/drawable/ic_eye_white.xml` | 1472 | `85aed0eaf135fcd1…` |
| `res/drawable/ic_face.xml` | 3064 | `37ef0696ce57871c…` |
| `res/drawable/ic_face_authen.xml` | 3316 | `d60cb3bf6ffa2754…` |
| `res/drawable/ic_fail.xml` | 1632 | `fed87023449be9cb…` |
| `res/drawable/ic_fail_result.webp` | 40994 | `6848d57071ce7e67…` |
| `res/drawable/ic_failed.png` | 653 | `82cc93b5f2dfbea5…` |
| `res/drawable/ic_failed_red.png` | 632 | `7f04d352e1779187…` |
| `res/drawable/ic_failed_voucher.xml` | 1256 | `3e65a058196559d2…` |
| `res/drawable/ic_filter_check.xml` | 1076 | `223c1123920249c2…` |
| `res/drawable/ic_filter_check_2.xml` | 908 | `c022cdac67d3de85…` |
| `res/drawable/ic_fingerid.xml` | 11660 | `e8557bac07dea4fb…` |
| `res/drawable/ic_flash_default.xml` | 2724 | `418a175110a745b4…` |
| `res/drawable/ic_flash_off.xml` | 1476 | `af04196cdbb2a997…` |
| `res/drawable/ic_flash_off_vd_white_24.xml` | 648 | `a3b030a04fd6a84c…` |
| `res/drawable/ic_flash_on.xml` | 1524 | `18dde401b523ac57…` |
| `res/drawable/ic_flash_on_vd_white_24.xml` | 576 | `04a7eee2516d58c9…` |
| `res/drawable/ic_flash_selected.xml` | 1288 | `71fd822393c67dbf…` |
| `res/drawable/ic_flash_suggest.xml` | 1140 | `bef37bcaf560da59…` |
| `res/drawable/ic_floating_tooltip.xml` | 65612 | `37e6daa130b0740c…` |
| `res/drawable/ic_food_and_drink.xml` | 1828 | `665d4e1daec26de8…` |
| `res/drawable/ic_frame_vneid.webp` | 3774 | `b60c10e6759ce306…` |
| `res/drawable/ic_frame_zlp.webp` | 2964 | `c662f50d342f2b16…` |
| `res/drawable/ic_front_frame_full.xml` | 1528 | `9123234a871300ca…` |
| `res/drawable/ic_genera_arrow_next3.xml` | 1240 | `16f88b8a1c86dfc4…` |
| `res/drawable/ic_general_add.xml` | 1088 | `ca5a0873534e06f2…` |
| `res/drawable/ic_general_arrow.xml` | 832 | `18f6486020d2f25e…` |
| `res/drawable/ic_general_arrow_back1.xml` | 852 | `1771c5102a43e3ec…` |
| `res/drawable/ic_general_arrow_back4.xml` | 836 | `639ac501bde2cc2d…` |
| `res/drawable/ic_general_arrow_down.xml` | 964 | `1100c8e041adaf1c…` |
| `res/drawable/ic_general_arrow_next.xml` | 828 | `59eddb1c6bdcfb32…` |
| `res/drawable/ic_general_arrow_up4.xml` | 836 | `3ad5727d9ea38bf4…` |
| `res/drawable/ic_general_arrow_up_down.xml` | 1272 | `1e74330d03638bcb…` |
| `res/drawable/ic_general_bank_green.xml` | 1764 | `6e36b6b93432d6fc…` |
| `res/drawable/ic_general_camera_line.xml` | 2828 | `fa39a36df7ef404b…` |
| `res/drawable/ic_general_check_solid.xml` | 900 | `b75e9888514a62bb…` |
| `res/drawable/ic_general_close_t2.xml` | 1108 | `0b146baeab2d4d5e…` |
| `res/drawable/ic_general_closecircle_line_chip.xml` | 944 | `f9af5b211845f7c0…` |
| `res/drawable/ic_general_closecircle_solid.xml` | 1060 | `98df3487c4c6d9a1…` |
| `res/drawable/ic_general_coin_color.xml` | 2932 | `b55c0ce087cbfc7b…` |
| `res/drawable/ic_general_contact_24.xml` | 1148 | `f37a77b93366cadd…` |
| `res/drawable/ic_general_copy.xml` | 1716 | `ea0239dd2028adb3…` |
| `res/drawable/ic_general_dowload.xml` | 1460 | `fc25ab81d478a1f3…` |
| `res/drawable/ic_general_dynamic.xml` | 3084 | `9fc580338fe8e93d…` |
| `res/drawable/ic_general_edit.xml` | 2144 | `d5058fa3a2ff845e…` |
| `res/drawable/ic_general_gallery_export.xml` | 2540 | `7fa61e69dc1a2d0d…` |
| `res/drawable/ic_general_gallery_import.xml` | 2484 | `289c3e50ed0f88ba…` |
| `res/drawable/ic_general_gallery_remove.xml` | 2528 | `b0143a38b523a286…` |
| `res/drawable/ic_general_global.xml` | 2676 | `dd66db99204b2bb1…` |
| `res/drawable/ic_general_idea.xml` | 3476 | `554f65a8637e369d…` |
| `res/drawable/ic_general_info_line.xml` | 1388 | `3d7242e589a5eb71…` |
| `res/drawable/ic_general_infoid.xml` | 2616 | `2475b04ac9bbdeca…` |
| `res/drawable/ic_general_library.xml` | 2732 | `9a6ab5b777f72e57…` |
| `res/drawable/ic_general_link_blue.webp` | 776 | `bab641fb85971c69…` |
| `res/drawable/ic_general_loading.xml` | 2708 | `5f4a3ae45c8119e9…` |
| `res/drawable/ic_general_location_tick.xml` | 1376 | `992b67849178abba…` |
| `res/drawable/ic_general_message.xml` | 2216 | `abbd84228c88b55f…` |
| `res/drawable/ic_general_minuscircle_solid.xml` | 800 | `cef5cc194f67e08a…` |
| `res/drawable/ic_general_mmf.xml` | 3888 | `b2d9bea8090f8343…` |
| `res/drawable/ic_general_navbar_giftbox_line.xml` | 2104 | `8aaf67cc4aa59bd5…` |
| `res/drawable/ic_general_navbar_history_line.xml` | 1848 | `4f799151b2fcace1…` |
| `res/drawable/ic_general_navbar_home_finance_solid.xml` | 1340 | `14dd67b242ace6f2…` |
| `res/drawable/ic_general_paste.xml` | 2100 | `f941991e7fc2f7e1…` |
| `res/drawable/ic_general_qrcode.xml` | 5344 | `ff23284623ac8118…` |
| `res/drawable/ic_general_qrcode_update.xml` | 3228 | `8547719ab9c755d5…` |
| `res/drawable/ic_general_question_line.xml` | 1780 | `0c9ee5fc6a6897f9…` |
| `res/drawable/ic_general_question_solid.xml` | 1376 | `210bf58b4f4b1734…` |
| `res/drawable/ic_general_reset.xml` | 1556 | `77f1cd0f6423f3bc…` |
| `res/drawable/ic_general_retry.xml` | 1384 | `df3cb6418590d95b…` |
| `res/drawable/ic_general_scan.xml` | 2752 | `e31ab0206a74af7a…` |
| `res/drawable/ic_general_security_check.xml` | 1720 | `cb59044c6a376450…` |
| `res/drawable/ic_general_setting.xml` | 3240 | `35b46aa263c38aca…` |
| `res/drawable/ic_general_share.xml` | 1504 | `881b0bdda1687814…` |
| `res/drawable/ic_general_share_line.xml` | 1464 | `0b6e2c2fd4ec26b7…` |
| `res/drawable/ic_general_speaker.png` | 817 | `5201853cbc371ee0…` |
| `res/drawable/ic_general_time.xml` | 1552 | `2a01aab6dc17319c…` |
| `res/drawable/ic_general_tip.xml` | 1508 | `ae06a098fbb504d5…` |
| `res/drawable/ic_general_uncheck_solid.xml` | 868 | `c2956566a1177f2b…` |
| `res/drawable/ic_general_volume_slash.png` | 915 | `2ea39cdb45e81ae2…` |
| `res/drawable/ic_general_warning_solid.xml` | 1564 | `37067c76c45f8fe1…` |
| `res/drawable/ic_general_wifi.xml` | 1396 | `b1ceaa154e5082d6…` |
| `res/drawable/ic_gift_box.xml` | 2080 | `3c35df74d3a760d0…` |
| `res/drawable/ic_giftbox.xml` | 2104 | `727dafe037607ab9…` |
| `res/drawable/ic_giftbox_line.xml` | 2104 | `8aaf67cc4aa59bd5…` |
| `res/drawable/ic_gpb.xml` | 2076 | `0fd7114bac5290d6…` |
| `res/drawable/ic_gpb_full_brand.xml` | 2460 | `0ee529cc3e2dfe24…` |
| `res/drawable/ic_green_pci_dss.webp` | 1498 | `dc8607447753e3e2…` |
| `res/drawable/ic_guide_good_light.xml` | 924 | `0328145d1c922fd9…` |
| `res/drawable/ic_guide_hold_steady.xml` | 1080 | `14a70f69d564fab7…` |
| `res/drawable/ic_guide_look_straight.xml` | 1244 | `d66ed195b35660e7…` |
| `res/drawable/ic_guide_no_closed_eyes.xml` | 1364 | `47de91a478301b94…` |
| `res/drawable/ic_guide_no_mask.xml` | 1120 | `bd1a94f7100e9462…` |
| `res/drawable/ic_guide_no_tilt.xml` | 1432 | `db843a57dc19473e…` |
| `res/drawable/ic_hand_onefinger_move.xml` | 2428 | `ddda597ab0d323c9…` |
| `res/drawable/ic_hdb.xml` | 38544 | `a96b43df08253b34…` |
| `res/drawable/ic_hdb_full_brand.xml` | 4624 | `403da38442d09f4a…` |
| `res/drawable/ic_header_notify.xml` | 1020 | `2f0836fe177fd13f…` |
| `res/drawable/ic_header_search.xml` | 972 | `12b159a871710068…` |
| `res/drawable/ic_help.xml` | 1700 | `57de35ab13d0b896…` |
| `res/drawable/ic_hidden_balance.xml` | 2368 | `ba4698bf1db0ded0…` |
| `res/drawable/ic_hide_popup.xml` | 1056 | `9e2f3aa1af980533…` |
| `res/drawable/ic_hide_popup_arrow.xml` | 1236 | `7caad96d217d37bf…` |
| `res/drawable/ic_hlb.xml` | 2608 | `a062eb36a785d83f…` |
| `res/drawable/ic_hlb_full_brand.xml` | 7248 | `d6609994f338ac98…` |
| `res/drawable/ic_home_app_4g.png` | 2141 | `14c15d420b02918a…` |
| `res/drawable/ic_home_app_bill.png` | 2138 | `b9dd8652511dc1ec…` |
| `res/drawable/ic_home_app_cashloan.png` | 3003 | `6e4c4406bae73c84…` |
| `res/drawable/ic_home_app_electronic.png` | 2105 | `11ca01a02e0a94ee…` |
| `res/drawable/ic_home_app_food.png` | 2114 | `fd7002ca13a25c36…` |
| `res/drawable/ic_home_app_game.png` | 2208 | `4a95b6db299b614c…` |
| `res/drawable/ic_home_app_movie.png` | 2262 | `650afe8d8f754538…` |
| `res/drawable/ic_home_app_oao.png` | 2140 | `943b0dd1e1eba760…` |
| `res/drawable/ic_home_app_stock.png` | 2211 | `40fff07d968aa4e9…` |
| `res/drawable/ic_home_app_telco.png` | 2152 | `b8f128c34df76c51…` |
| `res/drawable/ic_home_app_travel.png` | 2118 | `4f16643e44c8c6a7…` |
| `res/drawable/ic_home_bottom_tab_history.xml` | 1264 | `f9180725df7c42e8…` |
| `res/drawable/ic_home_bottom_tab_history_active.xml` | 1264 | `230e1dd4ef2b3aba…` |
| `res/drawable/ic_home_bottom_tab_home.xml` | 1624 | `45f8264f5cf68d49…` |
| `res/drawable/ic_home_bottom_tab_home_active.xml` | 1624 | `bef762b012f9b04a…` |
| `res/drawable/ic_home_bottom_tab_profile.xml` | 1544 | `8fff97d43976e9d1…` |
| `res/drawable/ic_home_bottom_tab_profile_active.xml` | 1544 | `0febf9bee3ecf2f2…` |
| `res/drawable/ic_home_bottom_tab_promotion.xml` | 2144 | `eed662e64103c041…` |
| `res/drawable/ic_home_bottom_tab_promotion_active.xml` | 2144 | `5c2796c76a8dbf3d…` |
| `res/drawable/ic_home_close_forced_migration_popup.xml` | 1148 | `5d25c376e7e2033c…` |
| `res/drawable/ic_home_forced_migration_popup_banner_background.png` | 4082 | `f81057f44c45cc23…` |
| `res/drawable/ic_home_forced_migration_popup_banner_phone.png` | 42282 | `dcca6a48e2cc9bd1…` |
| `res/drawable/ic_home_more_app.png` | 2545 | `f99ee0c7a2cd8dc0…` |
| `res/drawable/ic_home_more_search.xml` | 1560 | `f97e3aaf638d91a8…` |
| `res/drawable/ic_home_more_search_rounded.xml` | 1560 | `f97e3aaf638d91a8…` |
| `res/drawable/ic_home_nav_bar_tab_entertainment.png` | 999 | `2f7adb1ed56adaec…` |
| `res/drawable/ic_home_nav_bar_tab_entertainment_selected.png` | 1017 | `a50f9aa013be4c9c…` |
| `res/drawable/ic_home_nav_bar_tab_finance.png` | 563 | `d52488de5bb6530c…` |
| `res/drawable/ic_home_nav_bar_tab_finance_selected.png` | 840 | `9f00be82d2193104…` |
| `res/drawable/ic_home_nav_bar_tab_home.png` | 1305 | `2d9625d0d7c7d501…` |
| `res/drawable/ic_home_nav_bar_tab_profile.png` | 1030 | `22afec7eb85772e9…` |
| `res/drawable/ic_home_nav_bar_tab_profile_selected.png` | 870 | `124dedb839bf9237…` |
| `res/drawable/ic_home_navi_bank.xml` | 1788 | `53a30fc69648d514…` |
| `res/drawable/ic_home_navi_qr.xml` | 1336 | `0063afa34c7f4de8…` |
| `res/drawable/ic_home_navi_quickpay.xml` | 2768 | `2830c47cdb9f6c51…` |
| `res/drawable/ic_home_navi_topup.xml` | 1476 | `4c8c25fb8fba04ac…` |
| `res/drawable/ic_home_navi_transfer.xml` | 4376 | `806df2e33e839744…` |
| `res/drawable/ic_home_navi_withdraw.xml` | 1924 | `ab1b0f7ff88588bc…` |
| `res/drawable/ic_home_newsfeed_game_card_first_place_rank.png` | 21918 | `7f0661eb80295126…` |
| `res/drawable/ic_home_newsfeed_game_card_second_place_rank.png` | 22212 | `d0396e6d71a7fa4e…` |
| `res/drawable/ic_home_newsfeed_game_card_third_place_rank.png` | 21611 | `117dfb2b5fdf6277…` |
| `res/drawable/ic_home_newsfeed_movie_card_promotion.xml` | 2476 | `fdd2a6b08bb35fbc…` |
| `res/drawable/ic_home_newsfeed_movie_card_promotion_bg.xml` | 3580 | `791be05c0ef8fbfa…` |
| `res/drawable/ic_home_onboarding_campaign_task_claimed.png` | 1398 | `5d736b630f46c4d7…` |
| `res/drawable/ic_home_onboarding_campaign_task_locked.png` | 6187 | `4e90ee07d5d55a38…` |
| `res/drawable/ic_home_pay_later_check.xml` | 868 | `df3ac024788bd0a6…` |
| `res/drawable/ic_home_pay_later_check_solid.xml` | 972 | `be049669ecbd2c76…` |
| `res/drawable/ic_home_pay_later_lock.xml` | 2148 | `249fb2c5e9866000…` |
| `res/drawable/ic_home_pay_later_unlock.xml` | 2352 | `d1759dc38fb443d9…` |
| `res/drawable/ic_home_quick_bank.xml` | 2412 | `b224bdef06a91356…` |
| `res/drawable/ic_home_quick_topup.xml` | 2404 | `636b95196947e55c…` |
| `res/drawable/ic_home_quick_transfer.xml` | 4660 | `f9cc06187c8a09ab…` |
| `res/drawable/ic_home_quick_withdraw.xml` | 2320 | `63fb98c80c0731da…` |
| `res/drawable/ic_home_reminder_close.xml` | 1092 | `e1be9b66b1b10fea…` |
| `res/drawable/ic_home_search_bar.png` | 1022 | `a382ffad02f94975…` |
| `res/drawable/ic_home_security_bg.png` | 96058 | `3b62d0a563b8ba66…` |
| `res/drawable/ic_home_security_trusted_sources.png` | 11167 | `3302722e24edb787…` |
| `res/drawable/ic_home_shortcut_app_promotion.png` | 1229 | `bf3c53994e40a846…` |
| `res/drawable/ic_home_shortcut_app_scan_qr.png` | 1133 | `ac042495d6533544…` |
| `res/drawable/ic_home_shortcut_app_topup.png` | 1030 | `98618b9ecadb46bf…` |
| `res/drawable/ic_home_shortcut_app_transfer.png` | 1162 | `fc9e807776605928…` |
| `res/drawable/ic_home_sof_pay_later.png` | 1502 | `6dd1f09b8ae5c9e8…` |
| `res/drawable/ic_home_sof_sdsl.png` | 1367 | `c7065fd52af9c7e5…` |
| `res/drawable/ic_home_sof_wallet.png` | 1674 | `f53c6c75986a93c0…` |
| `res/drawable/ic_home_stock_down_trend.xml` | 696 | `5215758103e2665d…` |
| `res/drawable/ic_home_stock_up_trend.xml` | 696 | `ded4dccbde38e01d…` |
| `res/drawable/ic_home_suggestions_component_large_item_center.png` | 2472 | `82e4f209bae3d81a…` |
| `res/drawable/ic_home_suggestions_component_large_item_center_end.png` | 7138 | `6baa003633043934…` |
| `res/drawable/ic_home_tab_promotion.png` | 6868 | `15f2af137e0ac47a…` |
| `res/drawable/ic_home_user_header_history.png` | 1061 | `16400ec5eb3486f7…` |
| `res/drawable/ic_home_user_header_notification.png` | 936 | `ffe100504d5942b2…` |
| `res/drawable/ic_home_user_header_priority_bg.png` | 37760 | `1f5d5fc7b3007cbe…` |
| `res/drawable/ic_home_user_header_priority_collected_value.png` | 1489 | `91d7ad1219671157…` |
| `res/drawable/ic_home_user_header_priority_level_icon.png` | 1989 | `1f50c2dbf5d2fa55…` |
| `res/drawable/ic_home_vector.xml` | 832 | `4ddc617c1f20eb86…` |
| `res/drawable/ic_home_video_views_count_indicator.xml` | 1132 | `aac353885c02d2f6…` |
| `res/drawable/ic_home_vouchers_component_header_prefix.png` | 4635 | `b274cffd0547cd5a…` |
| `res/drawable/ic_home_vouchers_component_header_suffix.png` | 66828 | `1377a7f813db3e99…` |
| `res/drawable/ic_home_vouchers_component_header_suffix_blurred.png` | 18609 | `4c846f87dfd118ed…` |
| `res/drawable/ic_hsbc.xml` | 1536 | `2d7ce76288638145…` |
| `res/drawable/ic_hsbc_full_brand.xml` | 2652 | `4c34b2772b0ed488…` |
| `res/drawable/ic_ibft_term_new.xml` | 1504 | `7c03d4d720f57d86…` |
| `res/drawable/ic_ibk.xml` | 4252 | `304696743fe42812…` |
| `res/drawable/ic_ibk_full_brand.xml` | 3540 | `2d63af840a3b29da…` |
| `res/drawable/ic_ic24_chips_large48_checked.xml` | 1192 | `3bcd0725d4aaf19e…` |
| `res/drawable/ic_icon_cfh.xml` | 9240 | `86422e29fa514837…` |
| `res/drawable/ic_icon_emoji.xml` | 1600 | `a181a855593ad3cf…` |
| `res/drawable/ic_icon_fds_gif_circle.xml` | 1716 | `9de296effb5f13c7…` |
| `res/drawable/ic_icon_gif_circle.xml` | 1716 | `9de296effb5f13c7…` |
| `res/drawable/ic_image_frame.xml` | 1168 | `f5278e763fc93328…` |
| `res/drawable/ic_image_not_found.xml` | 2576 | `3eec068075260c89…` |
| `res/drawable/ic_image_placeholder.xml` | 4800 | `1372c2bcdc7ad7cd…` |
| `res/drawable/ic_info.xml` | 1316 | `d5f84971a6f57be3…` |
| `res/drawable/ic_info_2.xml` | 1404 | `ba7f016b515a343c…` |
| `res/drawable/ic_info_line.xml` | 1452 | `fb02b97fac7cbafd…` |
| `res/drawable/ic_info_outline.xml` | 720 | `3f821e0e073e75a6…` |
| `res/drawable/ic_info_taxi.xml` | 1820 | `2cd132256b381d4b…` |
| `res/drawable/ic_info_taxi_1.xml` | 1816 | `5ffec7e71de3c6e6…` |
| `res/drawable/ic_info_white.xml` | 1324 | `d96a2e8feb1c4fff…` |
| `res/drawable/ic_infor_page.xml` | 10972 | `6348f4000c7fa769…` |
| `res/drawable/ic_insurance_corona.xml` | 3216 | `f3a8a457b4d85f5d…` |
| `res/drawable/ic_internet_disconnection.xml` | 1756 | `0f221a6db42c7271…` |
| `res/drawable/ic_iv_step_one.xml` | 1688 | `7c4802970c6ab93c…` |
| `res/drawable/ic_iv_step_three.xml` | 2752 | `389fa48e0c79c8e1…` |
| `res/drawable/ic_iv_step_two.xml` | 2276 | `48184f0159264277…` |
| `res/drawable/ic_ivb.xml` | 4248 | `9672d4005b7de4b0…` |
| `res/drawable/ic_ivb_full_brand.xml` | 10324 | `4e98fcbeb05ed5a3…` |
| `res/drawable/ic_jcb.xml` | 11488 | `a5d7f749639ebc65…` |
| `res/drawable/ic_jcb_card_logo.xml` | 4396 | `280c5c33a8e568f9…` |
| `res/drawable/ic_jcb_debit.xml` | 10004 | `faf13e353ae1ef1e…` |
| `res/drawable/ic_jcb_debit_card_logo.xml` | 6756 | `10cc9a0aa8a1a203…` |
| `res/drawable/ic_jcb_debit_full_brand.xml` | 9868 | `cd0dccf51f0df018…` |
| `res/drawable/ic_jcb_full_brand.xml` | 8788 | `ae85ba128ba00284…` |
| `res/drawable/ic_keyboad_delete.webp` | 298 | `02705a409d020c6a…` |
| `res/drawable/ic_keyboard_backspace.xml` | 1208 | `4d04fff0ed8a25cc…` |
| `res/drawable/ic_keyboard_black_24dp.xml` | 852 | `35ef38a96f288d0f…` |
| `res/drawable/ic_khuyenmai.xml` | 1508 | `e86e04e1bf24e286…` |
| `res/drawable/ic_klb.xml` | 9924 | `7cf9bcb3cb6f7a5c…` |
| `res/drawable/ic_klb_full_brand.xml` | 5324 | `70c66a05e1a66a99…` |
| `res/drawable/ic_kyc.xml` | 2692 | `5dd41adc2166a3c2…` |
| `res/drawable/ic_kyc_account.xml` | 5272 | `46f434e492e8b479…` |
| `res/drawable/ic_kyc_arrow_next.xml` | 872 | `1efe02f42bee0810…` |
| `res/drawable/ic_kyc_enable.xml` | 1924 | `b1f4396756687093…` |
| `res/drawable/ic_kyc_invisible.xml` | 2068 | `a7e11c8f4140bebe…` |
| `res/drawable/ic_kyc_level_1.xml` | 1484 | `64b8dcc223a12953…` |
| `res/drawable/ic_kyc_protection_info.webp` | 20470 | `ce4a1989362b098f…` |
| `res/drawable/ic_kyc_result_fail.xml` | 7640 | `464d11f0ce0fcef4…` |
| `res/drawable/ic_kyc_result_processing.xml` | 7816 | `432e11e68e887b2f…` |
| `res/drawable/ic_kyc_result_success.xml` | 7892 | `10addab96403509c…` |
| `res/drawable/ic_kyc_secure.xml` | 1556 | `732eb53a72bd8959…` |
| `res/drawable/ic_label_promotion.xml` | 2432 | `ab0a51f98d13615e…` |
| `res/drawable/ic_left_arrow.xml` | 696 | `2400f6b155fe1c7c…` |
| `res/drawable/ic_level_2.xml` | 1488 | `174add2cd3fda8be…` |
| `res/drawable/ic_license_plate.xml` | 4324 | `9e02e51690ccad26…` |
| `res/drawable/ic_limit.xml` | 2940 | `3be2b4aff5be47ad…` |
| `res/drawable/ic_link_bank_at_counter.xml` | 2588 | `01e434c2fa4eb5be…` |
| `res/drawable/ic_link_others.xml` | 1900 | `1dce81026a0792a4…` |
| `res/drawable/ic_linking_bank.xml` | 3236 | `ca1ac41cf731d9e1…` |
| `res/drawable/ic_loading.xml` | 2804 | `e1f62c0683366494…` |
| `res/drawable/ic_loading_ekyc.png` | 819 | `29110499273f72c6…` |
| `res/drawable/ic_location_android.xml` | 1236 | `7ef77e4fc20d21a0…` |
| `res/drawable/ic_location_permission.xml` | 2300 | `6adb07ed66e860cf…` |
| `res/drawable/ic_lock_acc.xml` | 4668 | `84630bf54c27d54a…` |
| `res/drawable/ic_login_arrow.xml` | 872 | `a9e820f04cbe4fe2…` |
| `res/drawable/ic_login_avatardefault.xml` | 1252 | `cd3aa8097429c4ec…` |
| `res/drawable/ic_logo_scanqr_vn.xml` | 19336 | `894fc084d46e559f…` |
| `res/drawable/ic_logo_zalopay_br.xml` | 5544 | `17737529db175166…` |
| `res/drawable/ic_logout.xml` | 1164 | `ba4f91d753cc0fd5…` |
| `res/drawable/ic_loyalty.xml` | 5328 | `0951ce240f2e5034…` |
| `res/drawable/ic_loyalty_white.xml` | 2688 | `dcd3ec7e403abf8e…` |
| `res/drawable/ic_lpb.xml` | 1244 | `580553d296c9905d…` |
| `res/drawable/ic_lpb_full_brand.xml` | 8480 | `6b163cb242976b44…` |
| `res/drawable/ic_maintenance.xml` | 14152 | `c1c26ad1f0d35578…` |
| `res/drawable/ic_maintenance_service.xml` | 5468 | `e5a040036b79eee6…` |
| `res/drawable/ic_manager_link.xml` | 1028 | `b0fad7665c605f58…` |
| `res/drawable/ic_marker_location.xml` | 720 | `336398c1128529c0…` |
| `res/drawable/ic_mascot_confuse.webp` | 99740 | `7e82f51fb28494b6…` |
| `res/drawable/ic_master.xml` | 6536 | `ad86740b0047b09a…` |
| `res/drawable/ic_master_card_logo.xml` | 8968 | `4e35df9c5a0871b8…` |
| `res/drawable/ic_master_debit.xml` | 11004 | `add7eaf89642ea73…` |
| `res/drawable/ic_master_debit_card_logo.xml` | 14936 | `ec6f1dbfaf792f8a…` |
| `res/drawable/ic_master_debit_full_brand.xml` | 11004 | `add7eaf89642ea73…` |
| `res/drawable/ic_master_full_brand.xml` | 6484 | `33873cb74e33e527…` |
| `res/drawable/ic_mb.xml` | 6544 | `6ef19c1e23c4c670…` |
| `res/drawable/ic_mb_card_logo.xml` | 6544 | `6ef19c1e23c4c670…` |
| `res/drawable/ic_mb_full_brand.xml` | 8888 | `0c8624d4d9d2f8b2…` |
| `res/drawable/ic_media_placeholder.xml` | 4800 | `1372c2bcdc7ad7cd…` |
| `res/drawable/ic_media_sticker.xml` | 2976 | `f26ce62ea0124def…` |
| `res/drawable/ic_media_suggestion.xml` | 4640 | `28f83f0082c84a8c…` |
| `res/drawable/ic_media_theme.xml` | 1672 | `84a9deccfa40926d…` |
| `res/drawable/ic_medium_camera.xml` | 1216 | `ceeefc775c38c4ca…` |
| `res/drawable/ic_menu_linkcard.xml` | 2196 | `1d76b5dbac436c65…` |
| `res/drawable/ic_menu_payqrcode.xml` | 1612 | `b8e71fb3371bbb1c…` |
| `res/drawable/ic_menu_question.xml` | 1496 | `851187e5a2983ad0…` |
| `res/drawable/ic_merchant_ahamove.xml` | 2240 | `819dfe644d56d787…` |
| `res/drawable/ic_merchant_baemin.xml` | 2520 | `3b5b984e9dae5af0…` |
| `res/drawable/ic_merchant_begroup.xml` | 1580 | `b343f1ddc731c7fb…` |
| `res/drawable/ic_merchant_bigc.xml` | 11076 | `366bb76f3a776661…` |
| `res/drawable/ic_merchant_coopmart25.xml` | 13268 | `347df2a3e5c6704d…` |
| `res/drawable/ic_merchant_gotit.xml` | 4428 | `872f94143f49fc1e…` |
| `res/drawable/ic_merchant_kfc.xml` | 20896 | `2bca74054d472d6a…` |
| `res/drawable/ic_merchant_lazada.xml` | 3000 | `7fce95b9a4553c5c…` |
| `res/drawable/ic_merchant_vephim.xml` | 1324 | `bab2aac17b3713ea…` |
| `res/drawable/ic_migrate_to_new_home_gateway_prompt.xml` | 936 | `0a0740c7b8cc2034…` |
| `res/drawable/ic_migrate_to_new_home_gateway_title.xml` | 12640 | `42aa266305fd0aab…` |
| `res/drawable/ic_migrate_to_new_home_gateway_ui_preview.png` | 84014 | `c2ef5ab4ca5e8d25…` |
| `res/drawable/ic_mmf_bottom_sheet_close.xml` | 1240 | `9990eec70aedf11e…` |
| `res/drawable/ic_money_cashier_qr_code.xml` | 2752 | `235bf3a940a15105…` |
| `res/drawable/ic_morong_taxi.xml` | 484 | `836739e45d85e9ed…` |
| `res/drawable/ic_movie.xml` | 1308 | `479b080f353309f3…` |
| `res/drawable/ic_msb.xml` | 2284 | `61f67c12cae230e0…` |
| `res/drawable/ic_msb_card_logo.xml` | 2284 | `847a01cd394c5260…` |
| `res/drawable/ic_msb_full_brand.xml` | 3684 | `73aa445a6ed18252…` |
| `res/drawable/ic_msb_full_brand_card_logo.xml` | 5380 | `05669d4f8f6cc259…` |
| `res/drawable/ic_mtrl_checked_circle.xml` | 672 | `b483342a55b455a7…` |
| `res/drawable/ic_mtrl_chip_checked_black.xml` | 600 | `5df9a282d0c275dd…` |
| `res/drawable/ic_mtrl_chip_checked_circle.xml` | 940 | `44246e967feecb0c…` |
| `res/drawable/ic_mtrl_chip_close_circle.xml` | 808 | `4203452987064006…` |
| `res/drawable/ic_mute.xml` | 1436 | `7dccc7c1e21d21c4…` |
| `res/drawable/ic_nab.xml` | 2880 | `13d5c1f8a3687e6f…` |
| `res/drawable/ic_nab_card_logo.xml` | 3472 | `e95284cde94a6459…` |
| `res/drawable/ic_nab_full_brand.xml` | 1624 | `136835c31cf3348a…` |
| `res/drawable/ic_nasb.xml` | 4160 | `e5c48b8341ac81bf…` |
| `res/drawable/ic_nasb_full_brand.xml` | 14856 | `9e4b99d5ba44ab32…` |
| `res/drawable/ic_nav_bar_arrow_back.xml` | 844 | `f68359da00632fc2…` |
| `res/drawable/ic_ncb.xml` | 3396 | `993be776bb5bb28a…` |
| `res/drawable/ic_ncb_full_brand.xml` | 2980 | `46f6e174d992b9be…` |
| `res/drawable/ic_new_quick_balance.xml` | 1488 | `92de609f30f7a8cf…` |
| `res/drawable/ic_new_quick_pocket.xml` | 2120 | `229c44e5d81d34e1…` |
| `res/drawable/ic_new_quick_quickpay.xml` | 3896 | `ad133a1c2900dc29…` |
| `res/drawable/ic_new_quick_scan.xml` | 1944 | `0ded8a724f73bf1e…` |
| `res/drawable/ic_new_small_quick_balance.xml` | 2068 | `3f02f5ae84501324…` |
| `res/drawable/ic_new_small_quick_pocket.xml` | 2340 | `3cdff87d766c225f…` |
| `res/drawable/ic_new_small_quick_quickpay.xml` | 3840 | `8f6e916b064865d4…` |
| `res/drawable/ic_new_small_quick_scan.xml` | 1712 | `c9856a97ca71f788…` |
| `res/drawable/ic_new_white_zlp.webp` | 1544 | `f785db64cdc98ed0…` |
| `res/drawable/ic_new_zlp.xml` | 2792 | `9b86e2037a28923f…` |
| `res/drawable/ic_new_zlp_with_color.xml` | 2792 | `9b86e2037a28923f…` |
| `res/drawable/ic_newsfeed_game_card_top_bg.png` | 2450 | `d683b51f8da3c45b…` |
| `res/drawable/ic_newsfeed_ota_product_location.xml` | 4344 | `f7f7d23f29be6fee…` |
| `res/drawable/ic_newsfeed_ota_service_bg.png` | 269730 | `80119352b6755093…` |
| `res/drawable/ic_newsfeed_stock_indicator.png` | 71005 | `554964eb95d97026…` |
| `res/drawable/ic_next_arrow.xml` | 832 | `df60c35683315987…` |
| `res/drawable/ic_nfc_ekyc.xml` | 4540 | `23860b2031aef17e…` |
| `res/drawable/ic_nfc_new_ekyc.xml` | 5180 | `1bf13f201a634dcb…` |
| `res/drawable/ic_nfc_on_behalf_banner.xml` | 3324 | `2f0bb9e580a38784…` |
| `res/drawable/ic_nfc_scan_success.xml` | 940 | `661c87062811ebd0…` |
| `res/drawable/ic_nhantin.xml` | 1544 | `1b3ea8c5cf637b3d…` |
| `res/drawable/ic_nhantin_taxi.xml` | 1380 | `1773b51c1e4fe584…` |
| `res/drawable/ic_no_connection.xml` | 1704 | `01e8301cb2d13c5e…` |
| `res/drawable/ic_no_results_found.xml` | 10572 | `5c176f8e552366c3…` |
| `res/drawable/ic_nonetwork.xml` | 1028 | `8caa27f5b108e328…` |
| `res/drawable/ic_noti_ring_2.xml` | 1792 | `834a431b83c7febc…` |
| `res/drawable/ic_noti_ring_infomation.xml` | 1736 | `a0b064f7020ea35c…` |
| `res/drawable/ic_noti_ring_line.xml` | 2872 | `f75e85047f0c36bf…` |
| `res/drawable/ic_notice_kyc_update.xml` | 1172 | `f7b3a5981df98329…` |
| `res/drawable/ic_notice_line.xml` | 1488 | `be42bbc68dc06350…` |
| `res/drawable/ic_notice_warning.xml` | 1292 | `cc6b05d28f142160…` |
| `res/drawable/ic_notification.xml` | 2584 | `8504149f702a64fc…` |
| `res/drawable/ic_notification_bell.xml` | 976 | `17aa21d70e7268cc…` |
| `res/drawable/ic_notification_bell_white.xml` | 1144 | `e96516733e012efb…` |
| `res/drawable/ic_notification_with_color.xml` | 6968 | `2a1962e6c9709aa1…` |
| `res/drawable/ic_notify.xml` | 9452 | `8ff56a5e20256a78…` |
| `res/drawable/ic_notify_120.xml` | 10328 | `fdd51d8988c26b21…` |
| `res/drawable/ic_notify_bell.xml` | 1236 | `aa952839f1885dcb…` |
| `res/drawable/ic_notify_ring.xml` | 1752 | `57d976cc7db6d525…` |
| `res/drawable/ic_notify_ring_yellow.xml` | 1740 | `14a5cc387b74f0b9…` |
| `res/drawable/ic_num_1.xml` | 860 | `21d5b9a98b51aaad…` |
| `res/drawable/ic_num_2.xml` | 1052 | `8c3589bc71d0a977…` |
| `res/drawable/ic_num_3.xml` | 1228 | `a9fc59677f650567…` |
| `res/drawable/ic_nuttat.xml` | 1304 | `d332eee52bbdcc95…` |
| `res/drawable/ic_nvb.xml` | 3396 | `0fea7f9953a734c7…` |
| `res/drawable/ic_nvb_full_brand.xml` | 2980 | `90caac4ecf177d87…` |
| `res/drawable/ic_ocb.xml` | 2388 | `bcccf83ff63275a9…` |
| `res/drawable/ic_ocb_full_brand.xml` | 5284 | `5bc883e5223c27d8…` |
| `res/drawable/ic_ocr_pro_bottom_arrow.xml` | 944 | `ad78d2c407222580…` |
| `res/drawable/ic_oda_payment_success.xml` | 1472 | `6213c0e24eddbf18…` |
| `res/drawable/ic_ojb.xml` | 1920 | `769034af3182aba1…` |
| `res/drawable/ic_ojb_full_brand.xml` | 3000 | `0ab5e1b04be76ede…` |
| `res/drawable/ic_onboarding_title_bottom_center.xml` | 1832 | `8f3067663bd2a0fc…` |
| `res/drawable/ic_onboarding_title_bottom_end.xml` | 776 | `e1cb86af6f6f7e64…` |
| `res/drawable/ic_onboarding_title_top_start.xml` | 2448 | `90d2d3c194e374e4…` |
| `res/drawable/ic_orange_promotion_badge.xml` | 2352 | `a990fbe8efc06d25…` |
| `res/drawable/ic_otp_popup_smart_otp_security.xml` | 2488 | `1a00e4168285a820…` |
| `res/drawable/ic_outline_contact.xml` | 1848 | `c8c0aaa2e5bbeab1…` |
| `res/drawable/ic_partner_vneid.webp` | 1668 | `e197aba5bc99cb86…` |
| `res/drawable/ic_passed.png` | 1176 | `9521bf1a0f17b3bb…` |
| `res/drawable/ic_passport_new_ekyc.xml` | 1936 | `43083277ddcb3b58…` |
| `res/drawable/ic_password_hide.xml` | 1668 | `65693b9636cecfb7…` |
| `res/drawable/ic_password_show.xml` | 1428 | `822ce26465687602…` |
| `res/drawable/ic_payment_cash.xml` | 1460 | `bb1026fc621a624a…` |
| `res/drawable/ic_payment_cash_selected.xml` | 1508 | `928f6e0e6c374920…` |
| `res/drawable/ic_payment_online.xml` | 2412 | `77d12df9626ed083…` |
| `res/drawable/ic_payment_online_selected.xml` | 2500 | `613efab443bd052a…` |
| `res/drawable/ic_payment_pig.xml` | 22744 | `faef73aba003440a…` |
| `res/drawable/ic_pbvn.xml` | 2832 | `a545803fe60e491d…` |
| `res/drawable/ic_pbvn_full_brand.xml` | 4520 | `523bccedc71e9015…` |
| `res/drawable/ic_pending_result.webp` | 40012 | `106bfd400d60be37…` |
| `res/drawable/ic_permision_android.xml` | 2244 | `1e9d53ca7d20bbb7…` |
| `res/drawable/ic_personal_account_setting.xml` | 2116 | `b0443a520ee07cef…` |
| `res/drawable/ic_personal_app_info.xml` | 1352 | `ed9d7ab1c5c111a9…` |
| `res/drawable/ic_personal_arrow.xml` | 876 | `75fbb148636ebcf1…` |
| `res/drawable/ic_personal_bank.xml` | 720 | `cef822daddf28c7e…` |
| `res/drawable/ic_personal_bell_setting.xml` | 2936 | `23fefd5dedb59647…` |
| `res/drawable/ic_personal_bills.xml` | 1504 | `6c933534229940dd…` |
| `res/drawable/ic_personal_cs.xml` | 1032 | `e7d6c1fc14b85cf2…` |
| `res/drawable/ic_personal_link_payment.xml` | 1684 | `ec08039c45433f86…` |
| `res/drawable/ic_personal_loyalty.xml` | 2484 | `a548147a10be0d6a…` |
| `res/drawable/ic_personal_merchant_token.xml` | 1684 | `2248440f6863018d…` |
| `res/drawable/ic_personal_online_shopping.xml` | 1576 | `8d5a741445b9e130…` |
| `res/drawable/ic_personal_sales.xml` | 5660 | `2cace039b9656475…` |
| `res/drawable/ic_personal_voucher.xml` | 1756 | `07c36e584f23ba86…` |
| `res/drawable/ic_personal_wallet.xml` | 1000 | `cc555a3f267a1edd…` |
| `res/drawable/ic_pgb.xml` | 1468 | `779531c58d0ef314…` |
| `res/drawable/ic_pgb_full_brand.xml` | 5288 | `9f1afffc066fdd34…` |
| `res/drawable/ic_phone.xml` | 956 | `5eb3781e8391d9d0…` |
| `res/drawable/ic_phuongthuctt.xml` | 1576 | `3d7064dcea9a7f8a…` |
| `res/drawable/ic_pice.xml` | 796 | `f0a356d2bc194390…` |
| `res/drawable/ic_pick_image.xml` | 1052 | `0fa06d1362a82e3e…` |
| `res/drawable/ic_picture.xml` | 1152 | `35104e6039ffce1a…` |
| `res/drawable/ic_place_id_cccd_pro_guidance.webp` | 59320 | `a2695d3f7e1d4f61…` |
| `res/drawable/ic_placeholder_avatar.xml` | 1288 | `5ad9f03ecc8a7679…` |
| `res/drawable/ic_plus.xml` | 912 | `56e788efb06743e6…` |
| `res/drawable/ic_process_nfc_fail.xml` | 1088 | `2f9c91ea9fa087cd…` |
| `res/drawable/ic_process_nfc_success.xml` | 976 | `578de13c5506b061…` |
| `res/drawable/ic_processing.xml` | 1672 | `34e0fc2783b3dab7…` |
| `res/drawable/ic_processing_2.xml` | 1476 | `4ba51988419e43f3…` |
| `res/drawable/ic_processing_3.xml` | 3320 | `2d8e2b2ceb95dd96…` |
| `res/drawable/ic_profile_ekyc.xml` | 6792 | `2ac03e4bcb50ebdb…` |
| `res/drawable/ic_profile_v2_general_add.png` | 704 | `5ab7c4d88b286c47…` |
| `res/drawable/ic_profile_v2_general_copy.xml` | 1928 | `4ab3fb942be24306…` |
| `res/drawable/ic_profile_v2_general_edit.xml` | 2100 | `3bb34c1ab9e99bc4…` |
| `res/drawable/ic_profile_v2_general_logout.xml` | 1792 | `7e35a071036d3d91…` |
| `res/drawable/ic_profile_v2_general_setting.xml` | 3164 | `b87c7e253791bb56…` |
| `res/drawable/ic_profile_v2_reminder_arrow.xml` | 716 | `1a5beecedd738354…` |
| `res/drawable/ic_promotion_gift.xml` | 2328 | `c04f26c139981e22…` |
| `res/drawable/ic_promotions.xml` | 1348 | `78d4d1c254d32c8d…` |
| `res/drawable/ic_pvcb.xml` | 1932 | `8dd441eda5d677b5…` |
| `res/drawable/ic_pvcb_full_brand.xml` | 4780 | `f7c3f9eb081fa97c…` |
| `res/drawable/ic_qr_code.xml` | 2476 | `7092c1450225b168…` |
| `res/drawable/ic_qr_money_receive.xml` | 3276 | `7265362d921c531e…` |
| `res/drawable/ic_qr_position.xml` | 2076 | `b2d91d6250771f25…` |
| `res/drawable/ic_qr_type.xml` | 1620 | `aba5c9077f0a0598…` |
| `res/drawable/ic_qrcode_bottom_bar.xml` | 1764 | `8f5da0faa877db6f…` |
| `res/drawable/ic_quick_balance.xml` | 1604 | `7ca3267c23f0bc1b…` |
| `res/drawable/ic_quick_pay_info.xml` | 1676 | `ac631f33baa08eae…` |
| `res/drawable/ic_quick_pay_payment_info.xml` | 1372 | `567c8eb97874a22f…` |
| `res/drawable/ic_quick_pay_support_center.xml` | 1056 | `37e0336269b95605…` |
| `res/drawable/ic_quick_pocket.xml` | 1520 | `b907da2c770b6c08…` |
| `res/drawable/ic_quick_quickpay.xml` | 4340 | `be045eb7483e6877…` |
| `res/drawable/ic_quick_scan.xml` | 1880 | `972a69758a437845…` |
| `res/drawable/ic_quickpay_refresh.xml` | 1412 | `6ac734d9260a88fa…` |
| `res/drawable/ic_radio_active.xml` | 1024 | `e2ff9ee8c4e07d35…` |
| `res/drawable/ic_radio_btn.xml` | 892 | `3a268c4288ed2f6c…` |
| `res/drawable/ic_radio_btn_active.xml` | 1000 | `85e07fbad73b08ba…` |
| `res/drawable/ic_radio_btn_inactive.xml` | 860 | `d735cac860c10289…` |
| `res/drawable/ic_radio_btn_un_checked.xml` | 792 | `298ab088326156db…` |
| `res/drawable/ic_radio_inactive.xml` | 720 | `84454069138746b8…` |
| `res/drawable/ic_receive_failed.xml` | 1996 | `3c4e5e91f7ac0aab…` |
| `res/drawable/ic_receive_success.xml` | 2032 | `e0ce243cdff9fe20…` |
| `res/drawable/ic_recommend_system_gift.xml` | 15308 | `aae71387d7064018…` |
| `res/drawable/ic_rectangle_code_scanner.xml` | 912 | `504fc60bca11b405…` |
| `res/drawable/ic_red_dot.xml` | 1032 | `6d29194448de0f99…` |
| `res/drawable/ic_reform_kyc_almost_done.webp` | 18324 | `716a1a889d8efff8…` |
| `res/drawable/ic_reform_kyc_protection_info.webp` | 20470 | `ce4a1989362b098f…` |
| `res/drawable/ic_reform_kyc_state.xml` | 15652 | `d22c77dbda21115e…` |
| `res/drawable/ic_reformkyc_ekyc.xml` | 1632 | `09a96fd12f826f54…` |
| `res/drawable/ic_reformkyc_mapbank.xml` | 1304 | `b5b3a986c4b48703…` |
| `res/drawable/ic_reformkyc_secure.xml` | 1352 | `7952c31e4693f54c…` |
| `res/drawable/ic_refresh.xml` | 1380 | `a9b964e224fa697b…` |
| `res/drawable/ic_register_consent_arrow_down.xml` | 904 | `c629409d911136d3…` |
| `res/drawable/ic_register_consent_profile.xml` | 1148 | `52f94ddca3b83b4e…` |
| `res/drawable/ic_reload_ekyc.xml` | 956 | `c9ac41e9aaef8684…` |
| `res/drawable/ic_relocate.xml` | 1080 | `6870575f051012c2…` |
| `res/drawable/ic_reminder_background.webp` | 838 | `b5e9d3fe4f860dbc…` |
| `res/drawable/ic_reminder_close.xml` | 1160 | `86b58fffcd2b818a…` |
| `res/drawable/ic_remove_app.xml` | 1612 | `d920d42fecaaa84b…` |
| `res/drawable/ic_reset.xml` | 1500 | `44cb37c2efcc4c14…` |
| `res/drawable/ic_reset_pin_pending.xml` | 7848 | `dcb31893b654505d…` |
| `res/drawable/ic_right_arrow.xml` | 840 | `79d2e21682c0ab25…` |
| `res/drawable/ic_right_arrow_blue.xml` | 872 | `e3d4011a820ae752…` |
| `res/drawable/ic_rp_arrow.xml` | 872 | `1913b12af9978a63…` |
| `res/drawable/ic_rp_arrow_12.xml` | 864 | `109a2c8c0d6574cf…` |
| `res/drawable/ic_rp_arrow_24.xml` | 828 | `957b808d001cd55d…` |
| `res/drawable/ic_rp_arrow_v3.xml` | 840 | `79d2e21682c0ab25…` |
| `res/drawable/ic_rp_coin_v3.xml` | 3440 | `7c6b1dad51d3efe5…` |
| `res/drawable/ic_rp_fail.xml` | 1712 | `a1fa36e4d8f58f92…` |
| `res/drawable/ic_rp_failed_new.xml` | 1456 | `3127579b28b8687f…` |
| `res/drawable/ic_rp_fee.xml` | 1648 | `2c1096fd7344e4dc…` |
| `res/drawable/ic_rp_fee_v3.xml` | 1956 | `aa430b95a9efd89c…` |
| `res/drawable/ic_rp_info.xml` | 1384 | `5bb0073e5c26d898…` |
| `res/drawable/ic_rp_info_v3.xml` | 1352 | `71a1c3c58d6a6940…` |
| `res/drawable/ic_rp_kyc.xml` | 13500 | `510c32002cf16205…` |
| `res/drawable/ic_rp_order_detail.xml` | 2812 | `08b6198771b55ee9…` |
| `res/drawable/ic_rp_pcidss.xml` | 9508 | `dd3220f7d5c45981…` |
| `res/drawable/ic_rp_pending_new.xml` | 1464 | `408a7fb38f8a9125…` |
| `res/drawable/ic_rp_popup_close.xml` | 1056 | `bc884d633f9a2e53…` |
| `res/drawable/ic_rp_popup_close_v3.xml` | 1056 | `bc884d633f9a2e53…` |
| `res/drawable/ic_rp_processing.xml` | 1320 | `02ea3c317d9f0674…` |
| `res/drawable/ic_rp_processing_new.xml` | 2820 | `f26a1e7ad1e4ebba…` |
| `res/drawable/ic_rp_promotion.xml` | 2532 | `776c48240e58946f…` |
| `res/drawable/ic_rp_question_v3.xml` | 1596 | `ab488cb3f6702022…` |
| `res/drawable/ic_rp_secure.xml` | 18308 | `57f8ad1f2f37d191…` |
| `res/drawable/ic_rp_success.xml` | 1424 | `807e6ded7df13f84…` |
| `res/drawable/ic_rp_success_new.xml` | 1284 | `86e4a2f29923ff3d…` |
| `res/drawable/ic_rp_support.xml` | 1048 | `c9dcbef03e1dbb49…` |
| `res/drawable/ic_rp_support_v3.xml` | 1008 | `76f6cc1236089981…` |
| `res/drawable/ic_rp_zalopay.xml` | 2708 | `1d1c9ccf2c75619d…` |
| `res/drawable/ic_sample_invalid_id_card.webp` | 28372 | `f84576ec9b049190…` |
| `res/drawable/ic_sample_valid_id_card.webp` | 5520 | `f03583ff5b75362d…` |
| `res/drawable/ic_saving.xml` | 5592 | `90f6c9b138fe2f6e…` |
| `res/drawable/ic_saving_account.xml` | 5920 | `e64430109315eba5…` |
| `res/drawable/ic_scan_bottom_bar.xml` | 1584 | `b28c8a1bfe4356fd…` |
| `res/drawable/ic_scan_close.xml` | 1008 | `bc1c9a32db95a4c0…` |
| `res/drawable/ic_scan_gallery.xml` | 1288 | `12e3eeee164df657…` |
| `res/drawable/ic_scan_line.png` | 73 | `58ec7c6d45ea605e…` |
| `res/drawable/ic_scan_message.xml` | 1096 | `9fa6d9915ec8b394…` |
| `res/drawable/ic_scan_qr_payment.xml` | 4060 | `72539d32d15e6e94…` |
| `res/drawable/ic_scan_qr_sample.xml` | 13440 | `2e2833214540640f…` |
| `res/drawable/ic_scan_qr_transfering.xml` | 4080 | `7048840bb1bed734…` |
| `res/drawable/ic_scan_question.xml` | 1148 | `a74eaefbaca3d9ed…` |
| `res/drawable/ic_scan_support_center.xml` | 1296 | `923c2b1447824bb1…` |
| `res/drawable/ic_scan_triple_point.xml` | 816 | `d7668fb2dc20d368…` |
| `res/drawable/ic_scan_vietqr.xml` | 1400 | `8399c04073cffede…` |
| `res/drawable/ic_scb.xml` | 8032 | `fb2197fa4cdd8ec6…` |
| `res/drawable/ic_scb_card_logo.xml` | 3920 | `b6cc2d476fd5ae4c…` |
| `res/drawable/ic_scb_full_brand.xml` | 41700 | `61505dd91c6c6d7f…` |
| `res/drawable/ic_sct_full_brand.xml` | 6552 | `360b2cdaa42b1094…` |
| `res/drawable/ic_seab.xml` | 1936 | `fbb3d17218134e31…` |
| `res/drawable/ic_seab_full_brand.xml` | 8036 | `0bec9214b32b7465…` |
| `res/drawable/ic_search_blue.xml` | 1076 | `c5320706cd1b0ade…` |
| `res/drawable/ic_search_clear_recent_keywords.xml` | 2812 | `822dbfddc2849054…` |
| `res/drawable/ic_search_close.xml` | 908 | `61417102884aaccc…` |
| `res/drawable/ic_search_empty.xml` | 4892 | `bbc98a7ee030f83c…` |
| `res/drawable/ic_search_error.png` | 66830 | `a837903a6b435840…` |
| `res/drawable/ic_search_network_error.png` | 53718 | `7a9c788a951fe66f…` |
| `res/drawable/ic_search_no_results.png` | 72856 | `aedecff8c569b3db…` |
| `res/drawable/ic_search_null.xml` | 5332 | `ae9af1360dd44d9f…` |
| `res/drawable/ic_search_popular_keyword_leading.xml` | 1044 | `46fe01f80921452c…` |
| `res/drawable/ic_search_promotion_availability_info_indicator.xml` | 588 | `595a154e3c3ace5f…` |
| `res/drawable/ic_second_general_nowifi.xml` | 2244 | `e1d9df821023c483…` |
| `res/drawable/ic_second_general_redirect.xml` | 3048 | `eef498ae994501f9…` |
| `res/drawable/ic_secure.xml` | 1380 | `7ac9e221b7ecfe4b…` |
| `res/drawable/ic_secure_2.xml` | 1088 | `93ed147b452359c0…` |
| `res/drawable/ic_security.xml` | 2160 | `700e5ca1f757456b…` |
| `res/drawable/ic_security_check.xml` | 1720 | `cb59044c6a376450…` |
| `res/drawable/ic_see_more.xml` | 1168 | `3bccfba9e3ebde1f…` |
| `res/drawable/ic_selected.xml` | 1080 | `e832abb4c06672bf…` |
| `res/drawable/ic_send_money_to_receiver_success.xml` | 2556 | `5a72905883c5e601…` |
| `res/drawable/ic_service_coin.xml` | 3164 | `f6bbf8be84e0789a…` |
| `res/drawable/ic_service_explorezp_soundbox.xml` | 4036 | `361f4f539260e928…` |
| `res/drawable/ic_service_general_store.xml` | 4748 | `265be9cfb8fc98ee…` |
| `res/drawable/ic_service_maintain.xml` | 5512 | `2640ba28efc302af…` |
| `res/drawable/ic_service_maintenance.xml` | 14108 | `1e4730f57f2aec72…` |
| `res/drawable/ic_service_mywallet_qrreceive.xml` | 6260 | `c76b048effb044a1…` |
| `res/drawable/ic_service_tap_to_pay.xml` | 3264 | `4f5ddceff043ffc1…` |
| `res/drawable/ic_service_transfer_money.xml` | 5084 | `19a401ab63fb1134…` |
| `res/drawable/ic_service_transfer_qr_studio.xml` | 6416 | `ec90bbd6fabf21f3…` |
| `res/drawable/ic_service_transfer_qrreceive.xml` | 2220 | `8d84f99732ea3d99…` |
| `res/drawable/ic_setting_android.xml` | 3172 | `6512cafbee1b2855…` |
| `res/drawable/ic_sgb.xml` | 3288 | `d33eddd704fa2e5c…` |
| `res/drawable/ic_sgb_full_brand.xml` | 4072 | `a41ad24ba44fbee4…` |
| `res/drawable/ic_sgcb.xml` | 8280 | `4e14121b053cbd72…` |
| `res/drawable/ic_sgcb_card_logo.xml` | 15952 | `b04465b2053a42fb…` |
| `res/drawable/ic_sgcb_full_brand.xml` | 15952 | `b04465b2053a42fb…` |
| `res/drawable/ic_share.xml` | 876 | `5fabd101e9084bed…` |
| `res/drawable/ic_share_2.xml` | 2384 | `9ffa4aa8f0ff55ee…` |
| `res/drawable/ic_share_image.xml` | 2092 | `8e3414f5d0bdf37a…` |
| `res/drawable/ic_share_link.xml` | 1540 | `1a12b47099bbd73c…` |
| `res/drawable/ic_shb.xml` | 3592 | `1b8e57ab167f55ce…` |
| `res/drawable/ic_shb_full_brand.xml` | 4648 | `89795eff789e1ec0…` |
| `res/drawable/ic_shbvn.xml` | 3408 | `706c83066c6a1c62…` |
| `res/drawable/ic_shbvn_full_brand.xml` | 11560 | `edf53866bf4931cf…` |
| `res/drawable/ic_shield.xml` | 1616 | `b8adebcc35e1f82d…` |
| `res/drawable/ic_shield_green_small.xml` | 1324 | `cd3a37263241f0b1…` |
| `res/drawable/ic_shield_logo.xml` | 1896 | `7960d50f47d300d3…` |
| `res/drawable/ic_smart_otp.xml` | 2440 | `795776cd3558a698…` |
| `res/drawable/ic_sms_provider.xml` | 1976 | `031fcdf6ad5e3100…` |
| `res/drawable/ic_sotp_common.webp` | 580 | `d2c09e625bb53064…` |
| `res/drawable/ic_splash_screen.png` | 20042 | `2c00bf8587934ddb…` |
| `res/drawable/ic_splash_screen_vs.xml` | 17440 | `4c7831ac6b0e81cb…` |
| `res/drawable/ic_splash_test.xml` | 7524 | `9cddfb323326cd2c…` |
| `res/drawable/ic_standard.xml` | 3208 | `c254480001815a52…` |
| `res/drawable/ic_standard_full_brand.xml` | 6880 | `d6f29a3ee6818bf1…` |
| `res/drawable/ic_star_selector.xml` | 488 | `ad3da5415df5b8e8…` |
| `res/drawable/ic_star_selector_detail_his.xml` | 488 | `560aa53c7b7ac949…` |
| `res/drawable/ic_stargray.xml` | 1244 | `f424f305acda7628…` |
| `res/drawable/ic_starvang.xml` | 1244 | `4954e5f23a968796…` |
| `res/drawable/ic_status_acc_closed.xml` | 7744 | `784d4bb98e794ee1…` |
| `res/drawable/ic_success_green.xml` | 1004 | `5722149f4347a6af…` |
| `res/drawable/ic_success_result.webp` | 41768 | `dbd1ea732ecf19b9…` |
| `res/drawable/ic_success_white.xml` | 948 | `340ababf1cc272e6…` |
| `res/drawable/ic_success_with_border.xml` | 2564 | `a84c66b91264249c…` |
| `res/drawable/ic_suggest_food_and_drink.xml` | 2480 | `b211a241bfc11326…` |
| `res/drawable/ic_suggest_license_plate.xml` | 5628 | `7d3783c76d3141a7…` |
| `res/drawable/ic_suggest_lottery_ticket.xml` | 2412 | `531d99167cd0fcff…` |
| `res/drawable/ic_suggest_movie_poster.xml` | 2780 | `41b5b0e6a962a019…` |
| `res/drawable/ic_support.xml` | 3388 | `ab4fa63c4718606e…` |
| `res/drawable/ic_sweep_background.xml` | 74972 | `5d1e412a1b69c73f…` |
| `res/drawable/ic_sweep_now.xml` | 10048 | `1aaabaf8e8d39663…` |
| `res/drawable/ic_switch_camera1.xml` | 960 | `bd91274588fcfc6c…` |
| `res/drawable/ic_system_announcement_reminder.xml` | 1592 | `1d584dc3843120fc…` |
| `res/drawable/ic_tab_qr.xml` | 1352 | `7566b5d42cfe27aa…` |
| `res/drawable/ic_tab_qrscan.gif` | 61883 | `5b43d596c26e4afb…` |
| `res/drawable/ic_tab_quickpay.xml` | 1316 | `9ae236d9fc7cf806…` |
| `res/drawable/ic_tag.xml` | 764 | `d585db38a98cc1eb…` |
| `res/drawable/ic_tanggia_taxi.xml` | 696 | `2e05c5f6316b7f8b…` |
| `res/drawable/ic_taxi_addmore.xml` | 1408 | `517b9dc2dee7fe66…` |
| `res/drawable/ic_taxi_arrow_up.xml` | 1360 | `6ff23077da20ae5e…` |
| `res/drawable/ic_taxi_back.xml` | 624 | `eccdd02f25b8a13b…` |
| `res/drawable/ic_taxi_caidat.xml` | 1412 | `b4a88a6ccf119a01…` |
| `res/drawable/ic_taxi_call.xml` | 1128 | `b7f0d4dbfa9bea9c…` |
| `res/drawable/ic_taxi_call_outline.xml` | 1308 | `14df06b719c6bb62…` |
| `res/drawable/ic_taxi_card_checked.xml` | 1132 | `6452afd1191c7764…` |
| `res/drawable/ic_taxi_card_unchecked.xml` | 872 | `3cc45ccb31017f10…` |
| `res/drawable/ic_taxi_chamtron.xml` | 1284 | `50842613fc1cde18…` |
| `res/drawable/ic_taxi_check.xml` | 1376 | `72c243c6a918c2c8…` |
| `res/drawable/ic_taxi_check_tick_blue.xml` | 648 | `00f372613e2e65cc…` |
| `res/drawable/ic_taxi_checkbox_order.xml` | 1176 | `2c2edc9412beebef…` |
| `res/drawable/ic_taxi_checkradial.xml` | 1384 | `019fc4e44ea5b534…` |
| `res/drawable/ic_taxi_close.xml` | 796 | `ad9c02ee5582181f…` |
| `res/drawable/ic_taxi_close_gray.xml` | 796 | `caf981c30ccf3b63…` |
| `res/drawable/ic_taxi_close_new.xml` | 1008 | `54d3c60a54bb0a0f…` |
| `res/drawable/ic_taxi_contract.xml` | 1556 | `61b3314c409bacbc…` |
| `res/drawable/ic_taxi_diembatdau.xml` | 2364 | `62178bb059980a1a…` |
| `res/drawable/ic_taxi_diemcuoi.xml` | 2364 | `4e80de4caf762b96…` |
| `res/drawable/ic_taxi_diemcuoi_base.xml` | 1996 | `17915446f2f0269f…` |
| `res/drawable/ic_taxi_diemcuoi_path.xml` | 1060 | `daae61a8d26d64c6…` |
| `res/drawable/ic_taxi_diemdung1.xml` | 948 | `80890a2957162610…` |
| `res/drawable/ic_taxi_diemdung2.xml` | 1236 | `6457145ea404feec…` |
| `res/drawable/ic_taxi_dot_vertical.xml` | 756 | `5b68d63459960a0b…` |
| `res/drawable/ic_taxi_edit.xml` | 948 | `cb4054b7146f4a2f…` |
| `res/drawable/ic_taxi_error.xml` | 2032 | `e25a72da1ba41e5d…` |
| `res/drawable/ic_taxi_function_add.xml` | 1992 | `83208bfd34872adb…` |
| `res/drawable/ic_taxi_function_arrow_right.xml` | 1292 | `e683f14b1ab47b6a…` |
| `res/drawable/ic_taxi_help_32px.xml` | 1684 | `94fed76075b65bf1…` |
| `res/drawable/ic_taxi_history.xml` | 1984 | `dca1fb6f9b142cbc…` |
| `res/drawable/ic_taxi_home.xml` | 1528 | `5b692a64492094ae…` |
| `res/drawable/ic_taxi_island.png` | 1831 | `9404236d8d5ff939…` |
| `res/drawable/ic_taxi_logo_24px.xml` | 5436 | `5b1e09c44bb0a800…` |
| `res/drawable/ic_taxi_logo_visa.xml` | 26784 | `2d822075e97f4002…` |
| `res/drawable/ic_taxi_mark.xml` | 1760 | `91506a53602db134…` |
| `res/drawable/ic_taxi_marker_start.xml` | 1312 | `9f62ec68896d37d9…` |
| `res/drawable/ic_taxi_more.xml` | 1564 | `f20fd07a5b4feccc…` |
| `res/drawable/ic_taxi_phone_32px.xml` | 2184 | `41410964012c956c…` |
| `res/drawable/ic_taxi_popup_close.xml` | 1008 | `95463b058348643b…` |
| `res/drawable/ic_taxi_recent_search.xml` | 1640 | `3ea0ccd1d492bee2…` |
| `res/drawable/ic_taxi_recommen_home.xml` | 2736 | `4af678d17a43ea8c…` |
| `res/drawable/ic_taxi_recommen_normal.xml` | 1896 | `9168dba4872ba6db…` |
| `res/drawable/ic_taxi_recommen_office.xml` | 2000 | `09025d9f504ef2d0…` |
| `res/drawable/ic_taxi_recommen_save.xml` | 1448 | `1a38db5224695e63…` |
| `res/drawable/ic_taxi_ref.xml` | 4040 | `cf84d6acd7b18c87…` |
| `res/drawable/ic_taxi_reverse_blue.xml` | 1632 | `dbf8243d1e8e6523…` |
| `res/drawable/ic_taxi_reverse_white.xml` | 3972 | `cb6e46a89f2eec4d…` |
| `res/drawable/ic_taxi_right.xml` | 768 | `ff5211c4d45e39e7…` |
| `res/drawable/ic_taxi_search_near.xml` | 1672 | `d2d4e9f94ff178e2…` |
| `res/drawable/ic_taxi_search_not_saved.xml` | 1312 | `4763f9dbd3318c1d…` |
| `res/drawable/ic_taxi_search_saved.xml` | 1740 | `52a731ba6ad67ad9…` |
| `res/drawable/ic_taxi_starvang14.xml` | 1408 | `35696da739dec30f…` |
| `res/drawable/ic_taxi_tag_all_active.xml` | 1648 | `6270c5d833afb941…` |
| `res/drawable/ic_taxi_tag_all_unactive.xml` | 1648 | `2f313311d8bf11de…` |
| `res/drawable/ic_taxi_tag_discount_unactive.xml` | 1772 | `1cbd552370bacc56…` |
| `res/drawable/ic_taxi_tag_many_car.xml` | 1256 | `b499747c123c28dc…` |
| `res/drawable/ic_taxi_tag_many_car_unactive.xml` | 1256 | `ca880e9ca319637f…` |
| `res/drawable/ic_taxi_tag_promotion_unactive.xml` | 4524 | `016a7d3cbc0b8902…` |
| `res/drawable/ic_taxi_tag_reputation_unactive.xml` | 2376 | `ed9e6028f1f4e646…` |
| `res/drawable/ic_taxi_themmoi.xml` | 2432 | `8debda55aeb4168f…` |
| `res/drawable/ic_taxi_time_16px.xml` | 1688 | `91683251ea76885b…` |
| `res/drawable/ic_taxi_to.png` | 1725 | `97dd55f70b5cec91…` |
| `res/drawable/ic_taxi_uncheckbox_order.xml` | 884 | `f0679a2cb289f4ad…` |
| `res/drawable/ic_tcb.xml` | 868 | `1eb6631965eafbc3…` |
| `res/drawable/ic_tcb_full_brand.xml` | 2704 | `addd6560390a8b66…` |
| `res/drawable/ic_tgp_permissions_consent_logo.png` | 1990 | `4f708232245dd47e…` |
| `res/drawable/ic_tick_selected.xml` | 1076 | `4f6bde8a5a905a7a…` |
| `res/drawable/ic_tick_white.xml` | 888 | `60aa85a2a4bcfb83…` |
| `res/drawable/ic_tier_badge.xml` | 3000 | `f8e3982f74b1abfe…` |
| `res/drawable/ic_toolbar_back.xml` | 892 | `55b1a2d364533e15…` |
| `res/drawable/ic_toolbar_white_close.xml` | 1260 | `60700661d9048e14…` |
| `res/drawable/ic_tooltip_action.xml` | 564 | `7d96ef9cd6f20fcb…` |
| `res/drawable/ic_tooltip_arrow_down.xml` | 564 | `4a8942aa72ebbbd7…` |
| `res/drawable/ic_tooltip_arrow_left.xml` | 568 | `7abaac37c44fe6a0…` |
| `res/drawable/ic_tooltip_arrow_right.xml` | 568 | `6899e475c1ebc8ba…` |
| `res/drawable/ic_tooltip_arrow_up.xml` | 564 | `a05b2a4b429ccf12…` |
| `res/drawable/ic_topup.xml` | 4240 | `b69e29f61c90468f…` |
| `res/drawable/ic_topup_amount_cursor.xml` | 416 | `eb325d4a4fb5c4ef…` |
| `res/drawable/ic_topup_header_background.xml` | 712 | `b2820c0d04003fb1…` |
| `res/drawable/ic_topup_withdraw_background.xml` | 1316 | `4b91c3ec5e5f0503…` |
| `res/drawable/ic_topup_withdraw_background_main.xml` | 720 | `859c4bd6a4e03028…` |
| `res/drawable/ic_toro_kyc.xml` | 48356 | `262c8f08a4f22452…` |
| `res/drawable/ic_toro_mt.webp` | 1720 | `7daff799ec46225e…` |
| `res/drawable/ic_toro_soon.png` | 187647 | `114cf5a2638b6943…` |
| `res/drawable/ic_toro_startle.png` | 87013 | `adf54eacbeb7b774…` |
| `res/drawable/ic_toro_surprised.png` | 51657 | `7ad7b7e5d7b48dc9…` |
| `res/drawable/ic_touchid.xml` | 16764 | `749d815f1166e749…` |
| `res/drawable/ic_tpb.xml` | 2436 | `9e789b40cd15bd85…` |
| `res/drawable/ic_tpb_full_brand.xml` | 3200 | `e1b677d8bc00c330…` |
| `res/drawable/ic_trans_failed.xml` | 2440 | `55ec012ac1f5addc…` |
| `res/drawable/ic_trans_processing.xml` | 3320 | `3f4b1e29787c4e6c…` |
| `res/drawable/ic_trans_progress.xml` | 1700 | `39508b1ac8207640…` |
| `res/drawable/ic_trans_result_zigzag.xml` | 3196 | `d0745ef75d8712bd…` |
| `res/drawable/ic_trans_success.xml` | 2388 | `a510fa06f51938c0…` |
| `res/drawable/ic_transaction_limit.xml` | 1144 | `c3ce4e1f039de789…` |
| `res/drawable/ic_transfer_background.xml` | 10148 | `2616555bca4668f9…` |
| `res/drawable/ic_transfer_money.xml` | 2932 | `35f0bf0ea668aa26…` |
| `res/drawable/ic_transfer_money_p2p.xml` | 2976 | `9d203c284841439b…` |
| `res/drawable/ic_transfer_right_icon.xml` | 892 | `55feaf71090cf753…` |
| `res/drawable/ic_transfer_to_bank_account.xml` | 3260 | `18ca36fe00e7c10e…` |
| `res/drawable/ic_transfer_to_bank_card.xml` | 3060 | `6a35a2cfdfd23b03…` |
| `res/drawable/ic_transfer_zalo_notify.xml` | 3488 | `d0f27188c315a158…` |
| `res/drawable/ic_triangle_right.xml` | 684 | `709fa4b5681d5aee…` |
| `res/drawable/ic_true_check_round.xml` | 1156 | `c299db7cb692746c…` |
| `res/drawable/ic_tuychon_taxi.xml` | 1704 | `a2b113ffb841b81b…` |
| `res/drawable/ic_type_icon.xml` | 1996 | `10a23b77c9d420e3…` |
| `res/drawable/ic_uncheck_taxi.xml` | 628 | `8fa8c9f5553c918b…` |
| `res/drawable/ic_uncheckradial.xml` | 1168 | `9d0c813e5b995424…` |
| `res/drawable/ic_unlink_bank.xml` | 888 | `093c3df979ef9ecc…` |
| `res/drawable/ic_unmute.xml` | 1504 | `c218f0b08f5f2928…` |
| `res/drawable/ic_unsecure.xml` | 1076 | `45dfd0ddb8c6e4ff…` |
| `res/drawable/ic_uob.xml` | 2196 | `658f0ed31addf789…` |
| `res/drawable/ic_uob_full_brand.xml` | 11008 | `daf1e2611754b0ea…` |
| `res/drawable/ic_upgrade.xml` | 1816 | `b3d2db2efa9aa1b4…` |
| `res/drawable/ic_user_profile_capture.xml` | 960 | `7c88772b49801125…` |
| `res/drawable/ic_user_profile_edit.xml` | 908 | `284847285ae6fd14…` |
| `res/drawable/ic_vab.xml` | 972 | `bc9888143a98a067…` |
| `res/drawable/ic_vab_full_brand.xml` | 1988 | `dfa0d8afae69a0e7…` |
| `res/drawable/ic_varb.xml` | 11988 | `0f06f7bbe45d5620…` |
| `res/drawable/ic_varb_full_brand.xml` | 12504 | `b0f87abfa4219a62…` |
| `res/drawable/ic_vb.xml` | 5884 | `a0edb1ad495a70f6…` |
| `res/drawable/ic_vb_full_brand.xml` | 8588 | `3c682fc23cfefc0e…` |
| `res/drawable/ic_vcb.xml` | 2232 | `65a1adace149612e…` |
| `res/drawable/ic_vcb_card_logo.xml` | 4888 | `135dfbcf584020f9…` |
| `res/drawable/ic_vcb_full_brand.xml` | 4956 | `4433f1587a1fa373…` |
| `res/drawable/ic_vccb.xml` | 7016 | `b4c252b169afd52a…` |
| `res/drawable/ic_vccb_card_logo.xml` | 24856 | `0df7ab601538e69f…` |
| `res/drawable/ic_vccb_full_brand.xml` | 24492 | `18e90d88a8a31e69…` |
| `res/drawable/ic_vector_acc_saving.xml` | 4860 | `f7e9133fd256c991…` |
| `res/drawable/ic_vector_app_redpacket.xml` | 1424 | `3ef3b7e5642b580e…` |
| `res/drawable/ic_vector_arrow_bottom.xml` | 1012 | `7aa1747830804354…` |
| `res/drawable/ic_vector_arrow_right_blue.xml` | 900 | `e0a9557f4a2a290c…` |
| `res/drawable/ic_vector_arrow_right_guiding_face.xml` | 816 | `f66f375df6e81931…` |
| `res/drawable/ic_vector_arrow_stock.xml` | 696 | `5d4a17e758264515…` |
| `res/drawable/ic_vector_bank.xml` | 1840 | `c1567bd3d331429c…` |
| `res/drawable/ic_vector_bank_card_tooltip.xml` | 5840 | `3e917fe45f30ba20…` |
| `res/drawable/ic_vector_bank_close.xml` | 1076 | `f325e034540fcc9a…` |
| `res/drawable/ic_vector_bank_counter.xml` | 3684 | `91615e6747b3e47a…` |
| `res/drawable/ic_vector_bell.xml` | 1620 | `f3039993d5a4d7c4…` |
| `res/drawable/ic_vector_bg_lable_disable.xml` | 800 | `3b192a709a101b42…` |
| `res/drawable/ic_vector_bg_link_bank_button_fm.xml` | 6324 | `29fe3c8e44d389e3…` |
| `res/drawable/ic_vector_bg_oao_button_fm.xml` | 6324 | `991bd734a23a47c0…` |
| `res/drawable/ic_vector_bg_zalopay_balance_fm.xml` | 1844 | `02ba72d31c48b692…` |
| `res/drawable/ic_vector_bullet.xml` | 580 | `b26f386f9b2ad220…` |
| `res/drawable/ic_vector_bullet_dark.xml` | 580 | `b26f386f9b2ad220…` |
| `res/drawable/ic_vector_bullet_dark_500.xml` | 580 | `b26f386f9b2ad220…` |
| `res/drawable/ic_vector_center_point.xml` | 796 | `022818c0e325df3a…` |
| `res/drawable/ic_vector_change_phone_provider.xml` | 4724 | `6f83731c805f0856…` |
| `res/drawable/ic_vector_check_circle_green.xml` | 1068 | `b6f0e49ff4d062c2…` |
| `res/drawable/ic_vector_check_state.xml` | 860 | `24fd03fe46e53aad…` |
| `res/drawable/ic_vector_circle_2645.xml` | 668 | `e45d24b5b3952870…` |
| `res/drawable/ic_vector_circle_red_fm.xml` | 588 | `2b61e78d722c2f9b…` |
| `res/drawable/ic_vector_clear_button.xml` | 1560 | `fc3e2a42105ef5fc…` |
| `res/drawable/ic_vector_close_bottom_dialog.xml` | 1064 | `622e5bee61de4c29…` |
| `res/drawable/ic_vector_close_dialog.xml` | 1052 | `6b94e1bd8d013196…` |
| `res/drawable/ic_vector_close_small.xml` | 944 | `a53a2802af81ae65…` |
| `res/drawable/ic_vector_close_white.xml` | 1260 | `60700661d9048e14…` |
| `res/drawable/ic_vector_common_tick.xml` | 892 | `8f09263322951ed6…` |
| `res/drawable/ic_vector_component_setting.xml` | 30184 | `8a86219fe52975e0…` |
| `res/drawable/ic_vector_cubic_curve_shape.xml` | 780 | `1e1172fe59807637…` |
| `res/drawable/ic_vector_ekyc_pro_idea.xml` | 2904 | `c2e47877aa619de7…` |
| `res/drawable/ic_vector_empty_funding_source_fm.xml` | 3720 | `e4e221942d060015…` |
| `res/drawable/ic_vector_error_round.xml` | 1172 | `584371ff29fdc8bb…` |
| `res/drawable/ic_vector_error_state.xml` | 1136 | `2903a7b2254a1db2…` |
| `res/drawable/ic_vector_error_verification.xml` | 1136 | `2903a7b2254a1db2…` |
| `res/drawable/ic_vector_eye_close.xml` | 1412 | `09d99df9e8333f45…` |
| `res/drawable/ic_vector_eye_open.xml` | 1300 | `ec7f1074de95ef90…` |
| `res/drawable/ic_vector_face_guide_failed.xml` | 10068 | `44a760678e19d374…` |
| `res/drawable/ic_vector_face_guide_success.xml` | 9352 | `790e87b4c2fa3615…` |
| `res/drawable/ic_vector_fds_nfc_notice.xml` | 1132 | `d40eff444910db22…` |
| `res/drawable/ic_vector_fds_nfc_success.xml` | 972 | `57486498b4c9a352…` |
| `res/drawable/ic_vector_fds_scan_nfc.xml` | 1344 | `f4079ca8179a343b…` |
| `res/drawable/ic_vector_general_arrow_right.xml` | 900 | `9a02e26d2a072c42…` |
| `res/drawable/ic_vector_general_disconnect.xml` | 8152 | `6f7672b58865d68b…` |
| `res/drawable/ic_vector_general_info_16.xml` | 1276 | `850ed03f3fce2575…` |
| `res/drawable/ic_vector_general_info_solid.xml` | 984 | `f869c0df8b0c0c44…` |
| `res/drawable/ic_vector_general_link_line.xml` | 1548 | `e23072235f719e3d…` |
| `res/drawable/ic_vector_general_no_internet.xml` | 1704 | `fc08b74f466e294c…` |
| `res/drawable/ic_vector_general_no_wifi.xml` | 1920 | `f5a34326455c933a…` |
| `res/drawable/ic_vector_general_radio_checked.xml` | 916 | `693e71517a5bdff3…` |
| `res/drawable/ic_vector_general_radio_unchecked.xml` | 808 | `c39ff9c505aed3cd…` |
| `res/drawable/ic_vector_general_system_error.xml` | 6024 | `1b0ddd16a4116e41…` |
| `res/drawable/ic_vector_grey_link.xml` | 1484 | `6ce0a5ad2d861704…` |
| `res/drawable/ic_vector_grey_oval.xml` | 676 | `b279dc088cd446b6…` |
| `res/drawable/ic_vector_grey_tick.xml` | 888 | `7878edaea014f1d9…` |
| `res/drawable/ic_vector_group_add_qa.xml` | 1464 | `cec5f2305f74525b…` |
| `res/drawable/ic_vector_guide_failed.xml` | 10068 | `1c0f275032b285c5…` |
| `res/drawable/ic_vector_guide_success.xml` | 9352 | `9e30d84547a01a57…` |
| `res/drawable/ic_vector_home_add_movie.xml` | 1104 | `426809fd2e0972a5…` |
| `res/drawable/ic_vector_home_arrow.xml` | 1168 | `0c4f318914400421…` |
| `res/drawable/ic_vector_home_banner_no_cam.xml` | 1400 | `312752fb7cfad10c…` |
| `res/drawable/ic_vector_home_bell.xml` | 1140 | `4fb99095fc8d449c…` |
| `res/drawable/ic_vector_home_nav_bar_warning.xml` | 1848 | `113e9350097f45de…` |
| `res/drawable/ic_vector_home_qr_code.xml` | 4328 | `cffc9ef73afd28e4…` |
| `res/drawable/ic_vector_home_search.xml` | 1048 | `e902ac7bffb7b385…` |
| `res/drawable/ic_vector_icon_zalo.xml` | 3564 | `68440d98c47a0047…` |
| `res/drawable/ic_vector_id_card.xml` | 2084 | `38a652e266ee0bda…` |
| `res/drawable/ic_vector_id_card_avatar.xml` | 832 | `ccb7cd13ebc81524…` |
| `res/drawable/ic_vector_id_card_chip.xml` | 1324 | `8fcbc98730c8a620…` |
| `res/drawable/ic_vector_info_changes.xml` | 7312 | `8a437cff6a882e24…` |
| `res/drawable/ic_vector_info_white.xml` | 1404 | `ba7f016b515a343c…` |
| `res/drawable/ic_vector_iv_white_tick.xml` | 892 | `8f09263322951ed6…` |
| `res/drawable/ic_vector_left_arrow_blue_line.xml` | 1312 | `f5f71aa6c032ff7c…` |
| `res/drawable/ic_vector_link_bank.xml` | 4440 | `35f90d0cacca62e1…` |
| `res/drawable/ic_vector_liveness_notification.xml` | 10300 | `d65deef137f1d53d…` |
| `res/drawable/ic_vector_liveness_processing.xml` | 1692 | `cb7dcbded8d25b9b…` |
| `res/drawable/ic_vector_liveness_selfie.xml` | 8836 | `7fcb3e941d51261c…` |
| `res/drawable/ic_vector_nav_back_widget.xml` | 844 | `f68359da00632fc2…` |
| `res/drawable/ic_vector_new_ekyc_processing.xml` | 1692 | `cb7dcbded8d25b9b…` |
| `res/drawable/ic_vector_nfc_bank.xml` | 3684 | `83a34212eac166b5…` |
| `res/drawable/ic_vector_nfc_notice.xml` | 1132 | `d40eff444910db22…` |
| `res/drawable/ic_vector_nfc_processing.xml` | 1516 | `9c6f578cb18b8f90…` |
| `res/drawable/ic_vector_nfc_success.xml` | 972 | `57486498b4c9a352…` |
| `res/drawable/ic_vector_oao_arrow_right.xml` | 900 | `9a02e26d2a072c42…` |
| `res/drawable/ic_vector_oao_gift_label_fm.xml` | 3848 | `739816e4a81137e5…` |
| `res/drawable/ic_vector_open_bank_account.xml` | 2408 | `60b9af0633d8ba47…` |
| `res/drawable/ic_vector_others_zalopay_square.xml` | 3656 | `7ca0eed88bdf6fed…` |
| `res/drawable/ic_vector_otp_warning.xml` | 3704 | `7369eec1716eeec6…` |
| `res/drawable/ic_vector_personal_edit.xml` | 908 | `f1696cb31b3b60a1…` |
| `res/drawable/ic_vector_personal_new_edit.xml` | 2140 | `88cb586c5469f10e…` |
| `res/drawable/ic_vector_personal_shield_secure.xml` | 1244 | `c447aa4824936e1d…` |
| `res/drawable/ic_vector_processing_state.xml` | 1692 | `cb7dcbded8d25b9b…` |
| `res/drawable/ic_vector_processing_status.xml` | 7388 | `d271c3d304629ab8…` |
| `res/drawable/ic_vector_profit.xml` | 1116 | `db18cfb99f21a41f…` |
| `res/drawable/ic_vector_question_mark.xml` | 1748 | `5053b81ac95b96f5…` |
| `res/drawable/ic_vector_rectangle_60.xml` | 716 | `576cffed37cbece6…` |
| `res/drawable/ic_vector_rectangle_header_screen.xml` | 716 | `284f4dfd1d20e942…` |
| `res/drawable/ic_vector_red_line.xml` | 780 | `2b6cb194882dc218…` |
| `res/drawable/ic_vector_reset_pin.xml` | 1388 | `fed7e6a161f317d6…` |
| `res/drawable/ic_vector_result_fail.xml` | 1668 | `8dfad69b96cc754c…` |
| `res/drawable/ic_vector_result_processing.xml` | 1360 | `3da07643692ddb05…` |
| `res/drawable/ic_vector_retangle_header.xml` | 616 | `af62e4e352427b40…` |
| `res/drawable/ic_vector_ribbon.xml` | 596 | `9b09ec6b98f7b7ab…` |
| `res/drawable/ic_vector_right_arrow_blue_line.xml` | 1312 | `75236438f0010ac2…` |
| `res/drawable/ic_vector_scan_nfc.xml` | 1344 | `f4079ca8179a343b…` |
| `res/drawable/ic_vector_selefie_example.xml` | 7468 | `7c266c07f4605061…` |
| `res/drawable/ic_vector_shield.xml` | 1424 | `fc9ddfca8e714101…` |
| `res/drawable/ic_vector_shield_white.xml` | 1060 | `8f2277f080da5880…` |
| `res/drawable/ic_vector_size_16_zalo.xml` | 3056 | `e8783c78e5fd955c…` |
| `res/drawable/ic_vector_smart_otp_login.xml` | 2352 | `bcbd43124664952d…` |
| `res/drawable/ic_vector_sms_provider.xml` | 2932 | `114c41cf62f2ecc6…` |
| `res/drawable/ic_vector_state_in_process.xml` | 1780 | `4364f08b4a56490f…` |
| `res/drawable/ic_vector_state_normal.xml` | 684 | `1cd5ffa0b0ace980…` |
| `res/drawable/ic_vector_state_success.xml` | 1080 | `31ef363073edfcfa…` |
| `res/drawable/ic_vector_step_two_blue_color.xml` | 1124 | `f2c97ac2b6b8dfa2…` |
| `res/drawable/ic_vector_success_state.xml` | 956 | `843275bf22355303…` |
| `res/drawable/ic_vector_success_status.xml` | 1400 | `d636c662e9fa4281…` |
| `res/drawable/ic_vector_success_verification.xml` | 956 | `843275bf22355303…` |
| `res/drawable/ic_vector_tip.xml` | 2632 | `9725e94c8d3b4de8…` |
| `res/drawable/ic_vector_tip_widget.xml` | 2632 | `9725e94c8d3b4de8…` |
| `res/drawable/ic_vector_topup_info.xml` | 1472 | `8cd557db711e36c8…` |
| `res/drawable/ic_vector_topup_x_button.xml` | 1172 | `cc6838d032ec71c4…` |
| `res/drawable/ic_vector_touch_finger.xml` | 3840 | `a73c2f70f84f656d…` |
| `res/drawable/ic_vector_trailing_funding_source.xml` | 696 | `8d05ebbaf75b9f42…` |
| `res/drawable/ic_vector_waiting_approval_kyc.xml` | 7832 | `55db2838417c53c2…` |
| `res/drawable/ic_vector_wd_add_bank.xml` | 2176 | `5355696032c0397d…` |
| `res/drawable/ic_vector_white_right_arrow.xml` | 832 | `5685aeea4a974225…` |
| `res/drawable/ic_vector_white_tick.xml` | 892 | `8f09263322951ed6…` |
| `res/drawable/ic_vector_white_union.xml` | 1540 | `7c02e9706b5b3d26…` |
| `res/drawable/ic_vector_withdraw_ekyc.xml` | 6624 | `b8bcf0ef37d5a306…` |
| `res/drawable/ic_vector_x_transparent.xml` | 1008 | `6a7af606fe0bf2f4…` |
| `res/drawable/ic_vector_zalo_provider.xml` | 3240 | `836be92cf20645aa…` |
| `res/drawable/ic_vector_zalopay_fm.xml` | 6580 | `aff9baf6e7f57fa0…` |
| `res/drawable/ic_vector_zalopay_ring.xml` | 10284 | `35aeeca4f6f2ac03…` |
| `res/drawable/ic_vector_zalopay_white.xml` | 2688 | `01ea6fd126470e34…` |
| `res/drawable/ic_vector_zlp_with_bell.xml` | 11408 | `198d7c60f382f06d…` |
| `res/drawable/ic_verification_failed.webp` | 31446 | `6013d61670c6164e…` |
| `res/drawable/ic_verification_success.webp` | 31388 | `4192070239ce48a2…` |
| `res/drawable/ic_verify_failed.xml` | 7960 | `f14f513c97de6d12…` |
| `res/drawable/ic_very_small_kyc.png` | 2804 | `1b0157cc78491361…` |
| `res/drawable/ic_vib.xml` | 2120 | `513d0f421e036971…` |
| `res/drawable/ic_vib_full_brand.xml` | 2412 | `6adfeff368776e5c…` |
| `res/drawable/ic_video_ekyc.xml` | 1316 | `d345a8dfcf36aff9…` |
| `res/drawable/ic_videocam_ekyc.xml` | 704 | `6e58f26dfcd3b640…` |
| `res/drawable/ic_view_scanqr_finder.xml` | 1700 | `173c0972aca5d55a…` |
| `res/drawable/ic_visa.xml` | 3380 | `71af7594e067e72a…` |
| `res/drawable/ic_visa_card_logo.xml` | 1932 | `68049a70e30c7808…` |
| `res/drawable/ic_visa_debit.xml` | 5112 | `898257415916df67…` |
| `res/drawable/ic_visa_debit_card_logo.xml` | 4160 | `f00b8562d2ea40f8…` |
| `res/drawable/ic_visa_debit_full_brand.xml` | 5112 | `3018ca93e697864d…` |
| `res/drawable/ic_visa_full_brand.xml` | 3380 | `71af7594e067e72a…` |
| `res/drawable/ic_vneid.webp` | 2856 | `4892ed3e99d1c1b2…` |
| `res/drawable/ic_vneid_option.png` | 18917 | `c487e86eee5c0517…` |
| `res/drawable/ic_vnpay_qr.xml` | 4548 | `e7a1dda94be3a1ef…` |
| `res/drawable/ic_voucher_select.xml` | 1032 | `3406ed19479dca78…` |
| `res/drawable/ic_voucher_selected.xml` | 2328 | `814b2c732b6597ff…` |
| `res/drawable/ic_voucher_unselected.xml` | 2228 | `6ce3387474ed02ef…` |
| `res/drawable/ic_vpb.xml` | 6028 | `86906bde5009bc53…` |
| `res/drawable/ic_vpb_full_brand.xml` | 5484 | `cf58ae1d20554fc8…` |
| `res/drawable/ic_vrb.xml` | 11740 | `f2a658f36ac58c94…` |
| `res/drawable/ic_vrb_full_brand.xml` | 11356 | `d650c3ab3a16a53a…` |
| `res/drawable/ic_vtb.xml` | 2396 | `fb3cf8c2a459f168…` |
| `res/drawable/ic_vtb_card_logo.xml` | 2780 | `c28726bc66581ae2…` |
| `res/drawable/ic_vtb_full_brand.xml` | 2780 | `c28726bc66581ae2…` |
| `res/drawable/ic_wallet.xml` | 1184 | `680f5b4678018efa…` |
| `res/drawable/ic_warning_almost_data.xml` | 4984 | `90456814638f370a…` |
| `res/drawable/ic_warning_line_ver2.xml` | 1436 | `cde4fce18cd7fcfd…` |
| `res/drawable/ic_white_selection.xml` | 976 | `41f52ec4800dfc2c…` |
| `res/drawable/ic_widget_service_finance.xml` | 5124 | `06786616f33f8e6f…` |
| `res/drawable/ic_widget_service_receive_money.xml` | 4756 | `e763ea16115814a5…` |
| `res/drawable/ic_widget_service_scan_qr.xml` | 5492 | `8410b05d5f4c616a…` |
| `res/drawable/ic_widget_service_transfer.xml` | 4656 | `c59446f3736c6f1a…` |
| `res/drawable/ic_wifi_off.xml` | 1020 | `11bf5b97f608c771…` |
| `res/drawable/ic_withdraw.xml` | 4040 | `7dd8cd95592af011…` |
| `res/drawable/ic_withdraw_fee_info.xml` | 1340 | `8f0e0ffa4422096a…` |
| `res/drawable/ic_woo.xml` | 17720 | `a0f2a22d5178720c…` |
| `res/drawable/ic_woo_full_brand.xml` | 4652 | `de184eca7ff7bfaf…` |
| `res/drawable/ic_zalo_binding_zalopay.xml` | 6872 | `60693fadaa34c1b7…` |
| `res/drawable/ic_zalo_login.xml` | 3144 | `f5d5e6a41b94d75b…` |
| `res/drawable/ic_zalo_with_border_white.xml` | 3392 | `124acf9b7ede27c7…` |
| `res/drawable/ic_zalopay_notify.xml` | 9616 | `3ff7056ffde26568…` |
| `res/drawable/ic_zoom.xml` | 2056 | `0b2d8b8cd018c8c1…` |
| `res/drawable/ic_zoom_camera.webp` | 2500 | `4236a61f43ae2d4a…` |
| `res/drawable/ic_zoom_cancel.webp` | 770 | `e23366a5bcb69487…` |
| `res/drawable/ic_zoom_cancel_low_light.webp` | 592 | `caaf4d7079c92ffb…` |
| `res/drawable/ic_zoom_ideal.webp` | 12070 | `c3bc49515c05458e…` |
| `res/drawable/ic_zoom_locked.webp` | 1548 | `234c8e58b1afecef…` |
| `res/drawable/ic_zoom_skip_guidance.webp` | 304 | `477d6761c437dffe…` |
| `res/drawable/ic_zoom_unlocked.webp` | 1514 | `d1bd79c223731ab9…` |
| `res/drawable/ico_point_hlb.xml` | 676 | `5e69a570fedea392…` |
| `res/drawable/icon_book_code.xml` | 1868 | `dd634046bd905c5b…` |
| `res/drawable/icon_company.xml` | 3860 | `8470b4fe19fe0e71…` |
| `res/drawable/icon_id_card_in_frame.png` | 1055 | `7b86ff2439d3f23f…` |
| `res/drawable/icon_id_card_photo_clear.png` | 1006 | `d4585ece1ed1ce93…` |
| `res/drawable/icon_mrz_top.xml` | 5352 | `de900d4048277e7c…` |
| `res/drawable/icon_price.xml` | 3052 | `9b4f829264868d53…` |
| `res/drawable/icon_valid_id_card_chip.png` | 1436 | `1b438b9c7a945d80…` |
| `res/drawable/icon_white_tick.xml` | 956 | `6f351bdf7da1f24d…` |
| `res/drawable/image_dulieutrong.xml` | 2976 | `5aca0613f66bd984…` |
| `res/drawable/img180_general_no_network.xml` | 8156 | `d7615f848e28a7ed…` |
| `res/drawable/img180_general_unknown.xml` | 6236 | `dfdfde80adef7537…` |
| `res/drawable/img_account_secure.webp` | 36038 | `bcfcfe177ff2bc9f…` |
| `res/drawable/img_avatar_placeholder.png` | 1133 | `b710f859558b9efc…` |
| `res/drawable/img_bank_register_new_account_safe.webp` | 11884 | `44923577d23f155d…` |
| `res/drawable/img_banner_wbs.webp` | 16918 | `e6c60e2525ee32c0…` |
| `res/drawable/img_bg_sof_customize.webp` | 10148 | `ecefd8e2f4dd87ba…` |
| `res/drawable/img_cc_bc.webp` | 12232 | `434180379128204b…` |
| `res/drawable/img_close_account_1.webp` | 21914 | `b18dfa896c54f6c4…` |
| `res/drawable/img_content_cccd_error.webp` | 16164 | `cfd74b2340ecf741…` |
| `res/drawable/img_content_ocr_error_field.webp` | 3824 | `90fa71219cb83646…` |
| `res/drawable/img_content_popup_vneid.webp` | 182426 | `ff5eada961584f32…` |
| `res/drawable/img_content_unlink_bank.webp` | 9030 | `5fd87fc26913ebc8…` |
| `res/drawable/img_error_popup_ce.webp` | 11696 | `af54f81e34c6c82e…` |
| `res/drawable/img_error_popup_otp.png` | 105685 | `077178b23503a77c…` |
| `res/drawable/img_expired_pin_logo.jpg` | 43427 | `f46d7f0adf11670f…` |
| `res/drawable/img_fds_nfc_guidance.png` | 106396 | `80077ec8b0e5c51e…` |
| `res/drawable/img_fds_nfc_main_guidance.webp` | 13112 | `b54ca6947fc6d2ea…` |
| `res/drawable/img_fds_nfc_notice_1.png` | 2029 | `ae23096324b36f81…` |
| `res/drawable/img_fds_nfc_notice_2.png` | 1214 | `e1e2724da6d67f11…` |
| `res/drawable/img_fds_nfc_notice_3.png` | 4539 | `3ac594f508306c95…` |
| `res/drawable/img_fds_scan_id_card.xml` | 24516 | `a53c20c595448aa5…` |
| `res/drawable/img_fpt_logo.png` | 8680 | `a52864442a534922…` |
| `res/drawable/img_general_announcement.xml` | 14240 | `5e24e4320723eed3…` |
| `res/drawable/img_guide_scan_qr.png` | 170919 | `1b2872b088ceb1da…` |
| `res/drawable/img_guide_vnedi_1.webp` | 12852 | `7da3249fbc92697c…` |
| `res/drawable/img_guide_vneid_2.webp` | 23044 | `bf9c9e5f6d44500e…` |
| `res/drawable/img_id_card_error.png` | 188064 | `ffe1e1c196e3cda1…` |
| `res/drawable/img_id_card_issue_date_guide.webp` | 38110 | `3d644c23e2aac70e…` |
| `res/drawable/img_id_card_sample.webp` | 63228 | `164f5424e326cbe4…` |
| `res/drawable/img_login_new_banner1.webp` | 26770 | `cd8bf93e472ceed6…` |
| `res/drawable/img_login_new_banner2.webp` | 25476 | `11e39edfcb9eeb75…` |
| `res/drawable/img_login_new_banner3.webp` | 23742 | `5f0ba8cf80064c86…` |
| `res/drawable/img_lost_connecttion.webp` | 12078 | `e239ce1148f0c746…` |
| `res/drawable/img_new_header_bg.webp` | 15710 | `08df73ee96bc6da3…` |
| `res/drawable/img_new_id_card_sample.webp` | 35526 | `ea82023e7013214a…` |
| `res/drawable/img_nfc_behalf_experied.png` | 122139 | `7a4dd3d952912b97…` |
| `res/drawable/img_nfc_max_retry.png` | 80517 | `1bd2bbb8ece30ca2…` |
| `res/drawable/img_nfc_on_behafl.png` | 134866 | `335fad5bc45221f4…` |
| `res/drawable/img_nfc_pro_guidance.png` | 106396 | `80077ec8b0e5c51e…` |
| `res/drawable/img_no_support_scan_id_card.xml` | 24516 | `efadf1d26c28e9e2…` |
| `res/drawable/img_notify_news.webp` | 8106 | `30e3a478ece32739…` |
| `res/drawable/img_onboarding_1.webp` | 24030 | `5552fa990cb484ff…` |
| `res/drawable/img_onboarding_2.webp` | 34240 | `40c9df9f31aae7bc…` |
| `res/drawable/img_onboarding_3.webp` | 38626 | `6a5eb28a9ef64525…` |
| `res/drawable/img_open_bank_account_banner.webp` | 32326 | `cee07123a2783c87…` |
| `res/drawable/img_open_scb_account_bg.webp` | 414 | `45f97511db9e3ab4…` |
| `res/drawable/img_popup_ticket_received.webp` | 1862 | `77cfb5c711cf0a0b…` |
| `res/drawable/img_process_nfc_ide.png` | 5503 | `4fb79a2ade21b69e…` |
| `res/drawable/img_profile_v2_privacy_load_error.webp` | 11696 | `af54f81e34c6c82e…` |
| `res/drawable/img_remind_latter_ring.webp` | 50632 | `4e42b051cefb10f8…` |
| `res/drawable/img_request_go_merger.webp` | 11054 | `aa632fc2cad2e66d…` |
| `res/drawable/img_reset_pin_kyc_inform.webp` | 8318 | `191f1cc97c70315b…` |
| `res/drawable/img_resume_shield_play.webp` | 20474 | `b1b2a89d2be09abd…` |
| `res/drawable/img_selfie_invalid_sample.webp` | 26836 | `7331568f9ebd7bc6…` |
| `res/drawable/img_selfie_valid_sample.webp` | 10694 | `a833f0ceeaaace3c…` |
| `res/drawable/img_theme_top_header.webp` | 80 | `5235b4a40ac237db…` |
| `res/drawable/img_upgrade_completed.webp` | 31746 | `8273f3a4d945e511…` |
| `res/drawable/img_vector_ekyc_pro_system_error.xml` | 6404 | `bfc4a60537135e03…` |
| `res/drawable/img_vector_new_cs.xml` | 8996 | `5922a7f8c94579bd…` |
| `res/drawable/img_vneid_content.webp` | 9862 | `51a74c3e4d65f976…` |
| `res/drawable/img_vneid_guide_step_1.webp` | 21918 | `dc7c152df6112fdc…` |
| `res/drawable/img_vneid_guide_step_2.webp` | 20898 | `70dd5f019144a4a7…` |
| `res/drawable/img_vneid_guide_step_3.webp` | 22774 | `b13bae10299b943e…` |
| `res/drawable/item_selector_image_taxi.xml` | 484 | `d7a43f71f138ee6b…` |
| `res/drawable/iv_discount_payment.png` | 2067 | `498330217587b51a…` |
| `res/drawable/iv_discoutn_unselect.png` | 329 | `6b073c2b139705ed…` |
| `res/drawable/keyboard_selector.xml` | 588 | `c9541d726df1ba3b…` |
| `res/drawable/layer_home_default_background.xml` | 896 | `87aecbac85fa71c7…` |
| `res/drawable/layer_horizontal_seperate_twelve.xml` | 372 | `ba9fb53e09842218…` |
| `res/drawable/layer_loading.xml` | 2856 | `b5e4ffe52d7726b4…` |
| `res/drawable/layer_season_background.xml` | 1268 | `59dfdc9977d39826…` |
| `res/drawable/left_ic.xml` | 1136 | `526a8ce47659934c…` |
| `res/drawable/line_divider.xml` | 516 | `4f2e74c006f0b6f4…` |
| `res/drawable/liveness_camerax_ic_camera_24.xml` | 928 | `b90fc0f9692fe74d…` |
| `res/drawable/lnf_ic_ic16_general_close.xml` | 1092 | `88aff49eb954e6bd…` |
| `res/drawable/lnf_ic_ic16_general_down.xml` | 896 | `3bcfabd9ec56712e…` |
| `res/drawable/lnf_ic_ic16_general_next.xml` | 896 | `a3411d2b696da103…` |
| `res/drawable/lnf_ic_ic16_general_up.xml` | 896 | `cc86091151582c4b…` |
| `res/drawable/lnf_ic_ic24_general_check.xml` | 1024 | `b46ac704ff2a157d…` |
| `res/drawable/lnf_ic_ic24_general_check_line.xml` | 916 | `690417db371a29b3…` |
| `res/drawable/lnf_ic_ic24_general_closecircle.xml` | 1208 | `098fbae40822654f…` |
| `res/drawable/lnf_ic_ic24_general_info.xml` | 1396 | `fda4f043ba22f815…` |
| `res/drawable/lnf_ic_ic24_general_notice_line.xml` | 1300 | `ac10a5e0d9d1893e…` |
| `res/drawable/lnf_ic_ic24_general_secure_line.xml` | 1704 | `d6d136fe2ec3790d…` |
| `res/drawable/lnf_ic_ic24_general_warning.xml` | 1268 | `9f4ba585a92db098…` |
| `res/drawable/lnf_ic_secondary_general_arrow_back1.xml` | 908 | `9ac0bbcd478e00a0…` |
| `res/drawable/lnf_ic_secondary_general_arrow_down4.xml` | 904 | `3ac6a80c59884730…` |
| `res/drawable/lnf_ic_secondary_general_bankcard.xml` | 1988 | `a881c1d2d885d7f0…` |
| `res/drawable/lnf_ic_secondary_general_check_solid.xml` | 900 | `f522e7719b3c06bf…` |
| `res/drawable/lnf_ic_secondary_general_close.xml` | 1068 | `3af6f0848be613ee…` |
| `res/drawable/lnf_ic_secondary_general_closecircle_line.xml` | 1508 | `7a74a2c171135e7a…` |
| `res/drawable/lnf_ic_secondary_general_closecircle_solid.xml` | 1060 | `6839e36d326bea80…` |
| `res/drawable/lnf_ic_secondary_general_deleted.xml` | 3260 | `ac7881d7642076e0…` |
| `res/drawable/lnf_ic_secondary_general_history.xml` | 2108 | `eb4bf8009dc930b8…` |
| `res/drawable/lnf_ic_secondary_general_info_line.xml` | 1444 | `07b347ec86a7bd30…` |
| `res/drawable/lnf_ic_secondary_general_infoid.xml` | 2712 | `f9833e3e21014781…` |
| `res/drawable/lnf_ic_secondary_general_location.xml` | 2104 | `ddcd4547a135faa9…` |
| `res/drawable/lnf_ic_secondary_general_navbar_history_line.xml` | 2156 | `b2e67ac086e6dd12…` |
| `res/drawable/lnf_ic_secondary_general_navbar_shop_line.xml` | 2988 | `abd153afd3c4b0b3…` |
| `res/drawable/lnf_ic_secondary_general_notice_line.xml` | 1448 | `b24a6dc3b8413012…` |
| `res/drawable/lnf_ic_secondary_general_question_solid.xml` | 1504 | `a25d20169e626756…` |
| `res/drawable/lnf_ic_secondary_general_scan.xml` | 3200 | `3e244cf09f7d53ce…` |
| `res/drawable/lnf_ic_secondary_general_search.xml` | 1080 | `27cecebcae35fcef…` |
| `res/drawable/lnf_ic_secondary_general_security_check.xml` | 1956 | `330137cfe651d7e7…` |
| `res/drawable/lnf_ic_secondary_general_setting.xml` | 3716 | `88a4853603f8ca6f…` |
| `res/drawable/lnf_ic_secondary_general_star_solid.xml` | 1680 | `462a0e505283288c…` |
| `res/drawable/lnf_ic_secondary_general_voucher.xml` | 2748 | `446e059efaf107c0…` |
| `res/drawable/lnf_ic_secondary_linear_user_edit.xml` | 2956 | `a0d551ebe5359caa…` |
| `res/drawable/lnf_ic_secondary_service_mywallet_funds.xml` | 4988 | `8132c5f1ae9cd29e…` |
| `res/drawable/lnf_logo_white_zalopay.xml` | 3108 | `1183cccafe83c9f3…` |
| `res/drawable/lnf_nfc_blue_logo.xml` | 3760 | `e6c9f4bf0684bef1…` |
| `res/drawable/lnf_visa_logo.xml` | 4020 | `224c9841eeeaaec2…` |
| `res/drawable/lnf_zlp_v2_logo.xml` | 4324 | `26b2b79c4fd12246…` |
| `res/drawable/locate.xml` | 2092 | `d94e657c74e1235b…` |
| `res/drawable/logo_zalopay.xml` | 2504 | `500dddaba3e5b4a8…` |
| `res/drawable/logo_zalopay_background.xml` | 3992 | `1a559b5ca1a4e80e…` |
| `res/drawable/logo_zalopay_br.xml` | 2688 | `d536b694b41ce95c…` |
| `res/drawable/logo_zalopay_color.xml` | 2504 | `500dddaba3e5b4a8…` |
| `res/drawable/material_ic_calendar_black_24dp.xml` | 696 | `1ae3408e3c2df837…` |
| `res/drawable/material_ic_clear_black_24dp.xml` | 752 | `f739e5568bea1abf…` |
| `res/drawable/material_ic_edit_black_24dp.xml` | 716 | `32695bc5a16f6b95…` |
| `res/drawable/material_ic_keyboard_arrow_left_black_24dp.xml` | 712 | `c506af5792306683…` |
| `res/drawable/material_ic_keyboard_arrow_right_black_24dp.xml` | 700 | `4cdb19a98a545d6b…` |
| `res/drawable/material_ic_menu_arrow_down_black_24dp.xml` | 648 | `df6a361808990923…` |
| `res/drawable/menu_dot.xml` | 1412 | `b4a88a6ccf119a01…` |
| `res/drawable/moonbase_ic_camera.png` | 230032 | `413805f1f23d2f23…` |
| `res/drawable/moonbase_nfc_guide.png` | 56181 | `d6321eef569b365b…` |
| `res/drawable/moonbase_onboarding_done.png` | 231831 | `11dd580e0c941a65…` |
| `res/drawable/moonbase_qr_card.png` | 225750 | `2651f61837839d7f…` |
| `res/drawable/moonbase_qr_success.png` | 172086 | `cad4de4baa03bf42…` |
| `res/drawable/mrb_star_border_icon_black_36dp.xml` | 860 | `dbdd345ea6373525…` |
| `res/drawable/mrb_star_icon_black_36dp.xml` | 636 | `8ea7f3c13bdc73e1…` |
| `res/drawable/mtrl_dialog_background.xml` | 716 | `e80598a190e490c8…` |
| `res/drawable/mtrl_dropdown_arrow.xml` | 464 | `721c47eef24a1df9…` |
| `res/drawable/mtrl_ic_arrow_drop_down.xml` | 564 | `d89e4f1db9e34004…` |
| `res/drawable/mtrl_ic_arrow_drop_up.xml` | 564 | `b1b69ae00c7a838b…` |
| `res/drawable/mtrl_ic_cancel.xml` | 724 | `e0d7a332685e547d…` |
| `res/drawable/mtrl_ic_error.xml` | 644 | `fe1ff1e8f0a304f4…` |
| `res/drawable/mtrl_popupmenu_background.xml` | 740 | `2fa9e8d6b0e8cb1c…` |
| `res/drawable/mtrl_tabs_default_indicator.xml` | 628 | `4d681e064532838b…` |
| `res/drawable/navigation_empty_icon.xml` | 516 | `83c5fd6928dd162b…` |
| `res/drawable/near_address_bkg.xml` | 500 | `56bb5555209c30e0…` |
| `res/drawable/nfc_intro.png` | 34550 | `9140507c1d626f55…` |
| `res/drawable/nfc_intro_hlb.png` | 21072 | `7e7c99b14fd77faa…` |
| `res/drawable/nfc_local_default_guilde.png` | 222457 | `4fedbb2be66274cd…` |
| `res/drawable/normal_state.xml` | 372 | `f9c68f68cb19aded…` |
| `res/drawable/office.xml` | 3028 | `8e27202d322ada9c…` |
| `res/drawable/offline_dialog_background.xml` | 448 | `ea281276576d992a…` |
| `res/drawable/offline_dialog_default_icon_42dp.xml` | 1216 | `f2fd42bf77d05686…` |
| `res/drawable/onboarding_title.xml` | 9020 | `afb6e22bd7ffeeb3…` |
| `res/drawable/open_btn_background.xml` | 592 | `0ad3e793cbf2ea98…` |
| `res/drawable/page_counter.xml` | 628 | `6ce764acdbd8b346…` |
| `res/drawable/payment_tips_bkg.xml` | 612 | `5d4d4dad7f06d464…` |
| `res/drawable/pbw_authentication.png` | 155545 | `dcc581b5f021814d…` |
| `res/drawable/pbw_static_landing.png` | 98117 | `7c3d9aa5a4334ff2…` |
| `res/drawable/pbw_suspended_card.png` | 5991 | `9e931d1d6ed71cb2…` |
| `res/drawable/person_qr_background.xml` | 484 | `08395386e67baa2f…` |
| `res/drawable/person_qr_button_background.xml` | 484 | `28a54c11c42d7de4…` |
| `res/drawable/person_qr_scan_button_background.xml` | 484 | `9309743eb3e83fdc…` |
| `res/drawable/phone_error.png` | 62144 | `995175d258957c55…` |
| `res/drawable/pin_code_round_empty.xml` | 500 | `e1b43cb61d2183f8…` |
| `res/drawable/pin_code_round_empty_lock_screen.xml` | 500 | `16aff5ed998bd73f…` |
| `res/drawable/pin_code_round_empty_onboarding.xml` | 500 | `0ca4a6cdb292ee75…` |
| `res/drawable/pin_code_round_full.xml` | 500 | `e462f32ddd2fe2f8…` |
| `res/drawable/pin_code_round_full_lock_screen.xml` | 500 | `8f78278daaf0f6c8…` |
| `res/drawable/point_red.xml` | 372 | `3aefca9cd894a360…` |
| `res/drawable/preview_game_app_widget.png` | 251098 | `76ee66ea13b90ad8…` |
| `res/drawable/preview_movie_app_widget.png` | 248179 | `97f9f5197c4d7202…` |
| `res/drawable/preview_person_qr_app_widget.xml` | 37676 | `41e691bb3ce8f1b1…` |
| `res/drawable/preview_standard_app_widget.xml` | 55260 | `095106488d57eba6…` |
| `res/drawable/qr_code_background.xml` | 484 | `f9e2b9a0fc88ed2f…` |
| `res/drawable/qr_code_background_white.xml` | 484 | `82e4d5d64173c25c…` |
| `res/drawable/qr_placeholder.xml` | 2512 | `f3b94ea6c8f0a433…` |
| `res/drawable/quickpay_ic_alipay_logo.xml` | 1252 | `04921ee0cb9e82bd…` |
| `res/drawable/quickpay_ic_gobus_logo.xml` | 3704 | `0bf01be891f1888e…` |
| `res/drawable/quickpay_ic_metro_logo.xml` | 12376 | `cdf4eacd3dcf3e0a…` |
| `res/drawable/quickpay_ic_region_vn.xml` | 916 | `6b729d0f1698b4bc…` |
| `res/drawable/quickpay_ic_resend_otp.xml` | 1576 | `1ce6508f7f5430ee…` |
| `res/drawable/quickpay_ic_tenpay_logo.xml` | 1684 | `b349e74a3b34a762…` |
| `res/drawable/quickpay_ic_zp_logo.xml` | 4628 | `af12fdb7ea642a5e…` |
| `res/drawable/quickpay_img_out_of_gift.png` | 50106 | `74743674d4477576…` |
| `res/drawable/radio_button.xml` | 464 | `a80f7582764b3219…` |
| `res/drawable/radio_button_selector.xml` | 484 | `837fa98860d155ff…` |
| `res/drawable/radius_alert.xml` | 484 | `a2cf1ffe626072b4…` |
| `res/drawable/radius_full.xml` | 484 | `dde849573cf68366…` |
| `res/drawable/radius_full_boder_gray.xml` | 612 | `06142ce5fad5a5e5…` |
| `res/drawable/radius_full_gray_top.xml` | 536 | `b0a1280a3bba9bd4…` |
| `res/drawable/radius_full_promotion_taxi.xml` | 520 | `fddeb595868f90cf…` |
| `res/drawable/radius_full_tranperant.xml` | 484 | `9ceaf794b45e03ea…` |
| `res/drawable/radius_full_white_top.xml` | 536 | `d46c7736dc47954a…` |
| `res/drawable/radius_top.xml` | 536 | `b83044e48acfe35b…` |
| `res/drawable/rectangle_16488.png` | 184664 | `9083521ab9cd6d88…` |
| `res/drawable/reminder_cta_bg_white_blue_round_corner.xml` | 612 | `d20280fd80480d54…` |
| `res/drawable/scrollbar_bg.xml` | 484 | `4127802f571e3bc2…` |
| `res/drawable/scrollbar_handle.xml` | 484 | `a8aa3d1227f0ce53…` |
| `res/drawable/sdk_taxi_16_px_dropdown_black.xml` | 972 | `2563e5028323273b…` |
| `res/drawable/sdk_taxi_16_px_upcase_black.xml` | 972 | `7fb929e4f7bf01c8…` |
| `res/drawable/sdk_taxi_20_px_copy.xml` | 1036 | `bd28a655c4f2cacd…` |
| `res/drawable/sdk_taxi_arrow_down_16px.xml` | 700 | `026e003347dc9449…` |
| `res/drawable/sdk_taxi_arrow_up_16px.xml` | 700 | `14a583ff199c4e17…` |
| `res/drawable/sdk_taxi_back_20px.xml` | 936 | `ccbd301050bb80a4…` |
| `res/drawable/sdk_taxi_background_button_cancel.xml` | 520 | `21cfb5d7e8f49243…` |
| `res/drawable/sdk_taxi_background_button_stage_outline_00.xml` | 1224 | `740063424973f321…` |
| `res/drawable/sdk_taxi_background_button_stage_outline_01.xml` | 1224 | `efdb4c069400e575…` |
| `res/drawable/sdk_taxi_background_button_stage_outline_02.xml` | 1224 | `0e7902d845fc887d…` |
| `res/drawable/sdk_taxi_background_button_submit_outline_00.xml` | 668 | `d64f71452ef4527c…` |
| `res/drawable/sdk_taxi_background_button_submit_outline_01.xml` | 668 | `1ce241e707fc34bd…` |
| `res/drawable/sdk_taxi_background_button_submit_outline_02.xml` | 668 | `6195c58344fc271a…` |
| `res/drawable/sdk_taxi_background_outline_00.xml` | 668 | `4ba65fa9db7ff9a4…` |
| `res/drawable/sdk_taxi_background_outline_01.xml` | 668 | `1ce241e707fc34bd…` |
| `res/drawable/sdk_taxi_background_outline_02.xml` | 668 | `1ce241e707fc34bd…` |
| `res/drawable/sdk_taxi_background_submit_00.xml` | 748 | `614504a186ca5805…` |
| `res/drawable/sdk_taxi_background_submit_01.xml` | 632 | `91ea37888caf2b4a…` |
| `res/drawable/sdk_taxi_background_submit_02.xml` | 632 | `163570d4410802b3…` |
| `res/drawable/sdk_taxi_background_tab.xml` | 632 | `543e4caf5fc51f71…` |
| `res/drawable/sdk_taxi_background_tip_checked_00.xml` | 612 | `65cd6da9999db15e…` |
| `res/drawable/sdk_taxi_background_tip_checked_01.xml` | 612 | `884848aab8f82ee4…` |
| `res/drawable/sdk_taxi_background_tip_checked_02.xml` | 612 | `d49b9cb9dc4c84d4…` |
| `res/drawable/sdk_taxi_background_tip_unchecked_00.xml` | 520 | `567a143df84076e9…` |
| `res/drawable/sdk_taxi_background_tip_unchecked_01.xml` | 520 | `567a143df84076e9…` |
| `res/drawable/sdk_taxi_background_tip_unchecked_02.xml` | 520 | `567a143df84076e9…` |
| `res/drawable/sdk_taxi_background_vehicle_plate_00.xml` | 612 | `65cd6da9999db15e…` |
| `res/drawable/sdk_taxi_background_vehicle_plate_01.xml` | 612 | `884848aab8f82ee4…` |
| `res/drawable/sdk_taxi_background_vehicle_plate_02.xml` | 612 | `d49b9cb9dc4c84d4…` |
| `res/drawable/sdk_taxi_button_unactive.xml` | 484 | `d3a61239558ec14b…` |
| `res/drawable/sdk_taxi_ic_arrow_down.xml` | 656 | `1122a03d0e3b0ece…` |
| `res/drawable/sdk_taxi_ic_arrow_up.xml` | 660 | `dfe7ff41e08c6e8d…` |
| `res/drawable/sdk_taxi_ic_confirm_cancel_trip.xml` | 272972 | `876532e5c6b0b363…` |
| `res/drawable/sdk_taxi_icon_arrow_down00.xml` | 744 | `b414a4176a1b591a…` |
| `res/drawable/sdk_taxi_icon_chat_00.xml` | 1380 | `1773b51c1e4fe584…` |
| `res/drawable/sdk_taxi_icon_chat_01.xml` | 1380 | `8e4ce4ea758dd1f1…` |
| `res/drawable/sdk_taxi_icon_chat_02.xml` | 1380 | `1773b51c1e4fe584…` |
| `res/drawable/sdk_taxi_icon_chat_solid_00.xml` | 2328 | `aa962d64475627dd…` |
| `res/drawable/sdk_taxi_icon_chat_solid_01.xml` | 1544 | `08e73887679fbde6…` |
| `res/drawable/sdk_taxi_icon_chat_solid_02.xml` | 1544 | `1b3ea8c5cf637b3d…` |
| `res/drawable/sdk_taxi_icon_checkbox_checked_00.xml` | 692 | `9ae50cd6cc36a657…` |
| `res/drawable/sdk_taxi_icon_checkbox_checked_01.xml` | 1008 | `7d3bafedcee65504…` |
| `res/drawable/sdk_taxi_icon_checkbox_checked_02.xml` | 1008 | `250e9c4e0883bb74…` |
| `res/drawable/sdk_taxi_icon_checkbox_checked_white.xml` | 852 | `afb8ea13a6a2bcbe…` |
| `res/drawable/sdk_taxi_icon_checkbox_unchecked_00.xml` | 768 | `4b63ebea495b0123…` |
| `res/drawable/sdk_taxi_icon_checkbox_unchecked_01.xml` | 768 | `4b63ebea495b0123…` |
| `res/drawable/sdk_taxi_icon_checkbox_unchecked_02.xml` | 768 | `4b63ebea495b0123…` |
| `res/drawable/sdk_taxi_icon_down_00.xml` | 740 | `b39f853e8dfc7405…` |
| `res/drawable/sdk_taxi_icon_down_01.xml` | 740 | `c33905b414f05d7a…` |
| `res/drawable/sdk_taxi_icon_down_02.xml` | 740 | `c33905b414f05d7a…` |
| `res/drawable/sdk_taxi_icon_end_address_00.xml` | 832 | `f7204fb161a50531…` |
| `res/drawable/sdk_taxi_icon_end_address_01.xml` | 736 | `dfb2ebda87e312f9…` |
| `res/drawable/sdk_taxi_icon_end_address_02.xml` | 736 | `e1dfc2d968b37d06…` |
| `res/drawable/sdk_taxi_icon_info_00.xml` | 1152 | `9ccbe2d504d4fc92…` |
| `res/drawable/sdk_taxi_icon_info_01.xml` | 1152 | `2ec093b5883c3234…` |
| `res/drawable/sdk_taxi_icon_info_02.xml` | 1152 | `0be3c119adad546c…` |
| `res/drawable/sdk_taxi_icon_more_outline_00.xml` | 1124 | `f7a61eda9706b9de…` |
| `res/drawable/sdk_taxi_icon_more_outline_01.xml` | 1124 | `b443c03c88010a9e…` |
| `res/drawable/sdk_taxi_icon_more_outline_02.xml` | 1124 | `e8ade79661122a75…` |
| `res/drawable/sdk_taxi_icon_phone_00.xml` | 1052 | `f6d55e5470863cfd…` |
| `res/drawable/sdk_taxi_icon_phone_01.xml` | 1000 | `6f96562e325bcae0…` |
| `res/drawable/sdk_taxi_icon_phone_02.xml` | 1000 | `9a64dbe1b679bfe9…` |
| `res/drawable/sdk_taxi_icon_phone_outline_00.xml` | 1308 | `14df06b719c6bb62…` |
| `res/drawable/sdk_taxi_icon_phone_outline_01.xml` | 1308 | `ab330928b2f7f433…` |
| `res/drawable/sdk_taxi_icon_phone_outline_02.xml` | 1380 | `43e644ae33caa3ad…` |
| `res/drawable/sdk_taxi_icon_phone_solid_00.xml` | 1656 | `0cfce5d448416221…` |
| `res/drawable/sdk_taxi_icon_phone_solid_01.xml` | 1128 | `ee87ef6bd1e85175…` |
| `res/drawable/sdk_taxi_icon_phone_solid_02.xml` | 1128 | `b7f0d4dbfa9bea9c…` |
| `res/drawable/sdk_taxi_icon_start_address_00.xml` | 768 | `c93139b9dc71e2a7…` |
| `res/drawable/sdk_taxi_icon_start_address_01.xml` | 768 | `a741425569e74361…` |
| `res/drawable/sdk_taxi_icon_start_address_02.xml` | 768 | `12012a17bc8ae730…` |
| `res/drawable/sdk_taxi_icon_voucher_checked_00.xml` | 2328 | `d0255b744b137b23…` |
| `res/drawable/sdk_taxi_icon_voucher_checked_01.xml` | 2328 | `957dc71c4532c640…` |
| `res/drawable/sdk_taxi_icon_voucher_checked_02.xml` | 2328 | `232c10004e68beba…` |
| `res/drawable/sdk_taxi_icon_voucher_checked_percent.xml` | 1612 | `e323b52c941aeede…` |
| `res/drawable/sdk_taxi_icon_voucher_unchecked_00.xml` | 2228 | `6ce3387474ed02ef…` |
| `res/drawable/sdk_taxi_icon_voucher_unchecked_01.xml` | 2228 | `6ce3387474ed02ef…` |
| `res/drawable/sdk_taxi_icon_voucher_unchecked_02.xml` | 2228 | `6ce3387474ed02ef…` |
| `res/drawable/sdk_taxi_menu_icon_guide_00.xml` | 1820 | `2d17b7b8ce33b6c6…` |
| `res/drawable/sdk_taxi_menu_icon_guide_01.xml` | 1692 | `e1b7f7f7da95ddfb…` |
| `res/drawable/sdk_taxi_menu_icon_guide_02.xml` | 1692 | `b8bba28af2e8f2e8…` |
| `res/drawable/sdk_taxi_menu_icon_help_00.xml` | 2420 | `c14a568a7bb024d1…` |
| `res/drawable/sdk_taxi_menu_icon_help_01.xml` | 2284 | `9dbf14165bd00183…` |
| `res/drawable/sdk_taxi_menu_icon_help_02.xml` | 1792 | `7e823b5858af88f2…` |
| `res/drawable/sdk_taxi_menu_icon_history_00.xml` | 1784 | `54eb69ee632b042f…` |
| `res/drawable/sdk_taxi_menu_icon_history_01.xml` | 1648 | `d61dda17c7efbd1e…` |
| `res/drawable/sdk_taxi_menu_icon_history_02.xml` | 1396 | `73363acc692c7ed9…` |
| `res/drawable/sdk_taxi_menu_icon_setup_destination_00.xml` | 2248 | `da44268d852da53c…` |
| `res/drawable/sdk_taxi_menu_icon_setup_destination_01.xml` | 2112 | `2fb6a09940c1a463…` |
| `res/drawable/sdk_taxi_menu_icon_setup_destination_02.xml` | 1884 | `ee2db2f76a377689…` |
| `res/drawable/sdk_taxi_selector_background_voucher.xml` | 488 | `9481a626c2370c30…` |
| `res/drawable/sdk_taxi_selector_icon_radio.xml` | 488 | `784bcf473656cea4…` |
| `res/drawable/sdk_taxi_selector_icon_voucher.xml` | 488 | `81b1fc40a1e05f43…` |
| `res/drawable/sdk_taxi_selector_text_card.xml` | 484 | `41d4cba51bee3719…` |
| `res/drawable/sdk_taxi_selector_text_discount.xml` | 484 | `cbcf31ae4901668b…` |
| `res/drawable/sdk_taxi_selector_text_voucher.xml` | 484 | `d3af8c6538a1b0b8…` |
| `res/drawable/sdk_taxi_title_bar_background_00.xml` | 492 | `798b1080a7bc80f5…` |
| `res/drawable/sdk_taxi_title_bar_background_02.xml` | 492 | `d6ddd4264ab9f0f1…` |
| `res/drawable/sdk_taxi_title_bar_icon_back_00.xml` | 876 | `8078c1b2f9ee4e7a…` |
| `res/drawable/sdk_taxi_title_bar_icon_back_01.xml` | 876 | `d7a7628a972292ca…` |
| `res/drawable/sdk_taxi_title_bar_icon_back_02.xml` | 876 | `337147183783f7e1…` |
| `res/drawable/sdk_taxi_title_bar_icon_close_00.xml` | 648 | `fe8dd0ac79bac1d4…` |
| `res/drawable/sdk_taxi_title_bar_icon_close_00_01.xml` | 648 | `5b141291755c0366…` |
| `res/drawable/sdk_taxi_title_bar_icon_close_01.xml` | 648 | `ab19e0e77dbb48c8…` |
| `res/drawable/sdk_taxi_title_bar_icon_close_02.xml` | 652 | `24ab5c89714b35d5…` |
| `res/drawable/sdk_taxi_title_bar_icon_close_02_01.xml` | 648 | `ab19e0e77dbb48c8…` |
| `res/drawable/sdk_taxi_title_bar_icon_close_02_02.xml` | 648 | `f2e7e3a215c10f1c…` |
| `res/drawable/sdk_taxi_title_bar_icon_more_00.xml` | 936 | `f92465ba83199fbd…` |
| `res/drawable/sdk_taxi_title_bar_icon_more_01.xml` | 936 | `48d60c0139e9ef71…` |
| `res/drawable/sdk_taxi_title_bar_icon_more_02.xml` | 936 | `b3307cd8f155abc6…` |
| `res/drawable/sdk_taxi_title_bar_icon_pick_00.xml` | 1924 | `03beca289cf57041…` |
| `res/drawable/sdk_taxi_title_bar_icon_pick_01.xml` | 1924 | `2a71414c2cd8f599…` |
| `res/drawable/sdk_taxi_title_bar_icon_pick_02.xml` | 1924 | `5a77e0e3e7a625f2…` |
| `res/drawable/sdt_taxi_background_border_radius_18.xml` | 484 | `f6edfed9e08edf0a…` |
| `res/drawable/search_address_item_border.xml` | 612 | `3aa42af4b6ee6479…` |
| `res/drawable/search_on_map_fade_bkg.xml` | 532 | `6720b5885c8140ac…` |
| `res/drawable/selected_state.xml` | 372 | `0bb069cd5d23f3af…` |
| `res/drawable/selector_arrow.xml` | 488 | `69dc166578a225b7…` |
| `res/drawable/selector_bg_arrow.xml` | 488 | `47ee7fd1daa79d10…` |
| `res/drawable/selector_card_payment_method.xml` | 512 | `215c9af4bdb45413…` |
| `res/drawable/selector_check.xml` | 488 | `7c6932a224933a94…` |
| `res/drawable/selector_checked_payment_method.xml` | 484 | `bd72ed11a2a43503…` |
| `res/drawable/selector_image_preview_ekyc.xml` | 2296 | `b108576d211e0a6e…` |
| `res/drawable/selector_items_country_background.xml` | 464 | `309dce8df1d44bd0…` |
| `res/drawable/selector_link_alpha.xml` | 488 | `df3bed1d1bae242b…` |
| `res/drawable/selector_payment_method.xml` | 488 | `844410efa5921055…` |
| `res/drawable/selector_search_edittext.xml` | 488 | `cc01d974808d9923…` |
| `res/drawable/shape_rect_color_primary.xml` | 448 | `4dd659a759bb00d1…` |
| `res/drawable/shape_rect_main_color_radius_10_opacity_10.xml` | 448 | `4262422ce2aa9fd3…` |
| `res/drawable/shortcut_service_mywallet_payqr.png` | 1438 | `afabbbf2a30931d3…` |
| `res/drawable/shortcut_service_paybill_electric.png` | 1182 | `eb7151de48787b2f…` |
| `res/drawable/shortcut_service_telco_topup.png` | 1249 | `f7dadbfc568946d0…` |
| `res/drawable/shortcut_service_transfer_money.png` | 1494 | `02d3ff37e09bdc25…` |
| `res/drawable/sltd_comparison_illustration.xml` | 80500 | `a57d2e7b3b38f257…` |
| `res/drawable/support_bank_empty.webp` | 27190 | `dd3c7296772419a2…` |
| `res/drawable/tab_selected_ekyc.xml` | 336 | `0dede6a62445b4e0…` |
| `res/drawable/tab_selector_ekyc.xml` | 468 | `2bc4ed1852b66c01…` |
| `res/drawable/tax_ic_back.xml` | 620 | `cb395da0fb404763…` |
| `res/drawable/taxi_14_px_hotline.xml` | 1352 | `0aafbce0a2998264…` |
| `res/drawable/taxi_16_px_destination.xml` | 1024 | `ae181ccc9ba040e2…` |
| `res/drawable/taxi_16_px_header_warning.xml` | 1216 | `3d022dbfddbd0e72…` |
| `res/drawable/taxi_16_px_icon_down.xml` | 852 | `69daafde3e2625da…` |
| `res/drawable/taxi_16_px_phuongthucthanhtoan.xml` | 1148 | `8ed24363e0ad6184…` |
| `res/drawable/taxi_16_px_timkiem_location.xml` | 1076 | `4350578f71811d76…` |
| `res/drawable/taxi_16_px_vnpay.xml` | 5304 | `2bc7e0c32f96b912…` |
| `res/drawable/taxi_16_px_warning_green.xml` | 1124 | `efbe7b3ce82c18ff…` |
| `res/drawable/taxi_16_px_xemthem.xml` | 1248 | `efb90368a526aed0…` |
| `res/drawable/taxi_16px_check_green.xml` | 828 | `9e4613f3d87b8920…` |
| `res/drawable/taxi_16px_i_icon.xml` | 1280 | `8355cbfdfd62bd4d…` |
| `res/drawable/taxi_16px_icon_dots.xml` | 1228 | `7dc5bcb878c422ff…` |
| `res/drawable/taxi_16px_icon_right_black.xml` | 752 | `cccb973a9cb0fb03…` |
| `res/drawable/taxi_16px_right.xml` | 800 | `38c01bfc6e2f0dbe…` |
| `res/drawable/taxi_20_px_addnew.xml` | 1296 | `2ad5a3f08a2db53b…` |
| `res/drawable/taxi_20_px_checked.xml` | 964 | `ac7e0e7a7dd969af…` |
| `res/drawable/taxi_20_px_chondiemdon.xml` | 1028 | `05a6984bccc15ca6…` |
| `res/drawable/taxi_20_px_cleartext.xml` | 1556 | `68eb14582d7f0f9f…` |
| `res/drawable/taxi_20_px_forward.xml` | 1312 | `b0e17fbeb8602087…` |
| `res/drawable/taxi_20_px_hoidap.xml` | 1372 | `5e1d4957e3bddd88…` |
| `res/drawable/taxi_20_px_method_checked.xml` | 964 | `73add07935cb6d21…` |
| `res/drawable/taxi_20_px_more.xml` | 980 | `e756dce5bf5ca156…` |
| `res/drawable/taxi_20_px_popup_back.xml` | 1224 | `ce14c9a585376365…` |
| `res/drawable/taxi_20_px_popup_close.xml` | 1284 | `ad1a7d7a83c88d73…` |
| `res/drawable/taxi_20_px_timhieuthem.xml` | 1648 | `43850afa0727a5f4…` |
| `res/drawable/taxi_20_px_timkiem_location.xml` | 1076 | `8194499815c9fb89…` |
| `res/drawable/taxi_20_px_unchecked.xml` | 964 | `3f3ba0c66ba43aae…` |
| `res/drawable/taxi_20px_dieu_khoan.xml` | 1664 | `f679418a6f514d5e…` |
| `res/drawable/taxi_20px_icon_dolar.xml` | 1788 | `9394646399751215…` |
| `res/drawable/taxi_24_px_ghichu_iemon.xml` | 1724 | `cb48ab42603762a6…` |
| `res/drawable/taxi_24_px_mylocation.xml` | 1516 | `065f5e434142d430…` |
| `res/drawable/taxi_24_px_thanhtoantudong.xml` | 2012 | `5d7e76d056429237…` |
| `res/drawable/taxi_24_px_vietinbank.xml` | 1252 | `f3251bcb1e577f79…` |
| `res/drawable/taxi_24_px_vnpay.xml` | 5304 | `7019cd957723f2f7…` |
| `res/drawable/taxi_30_px_caidatdiadiem.xml` | 1228 | `f50e750743ab3718…` |
| `res/drawable/taxi_30_px_call.xml` | 3360 | `db7ba00496d3f317…` |
| `res/drawable/taxi_30_px_header_more_2.xml` | 1480 | `c68e1d6b632c8d45…` |
| `res/drawable/taxi_30_px_header_more_3.png` | 906 | `7abaf2caf55b2b38…` |
| `res/drawable/taxi_30_px_introduction.xml` | 2976 | `932759a86d2e4a8c…` |
| `res/drawable/taxi_30_px_lichsuchuyendi.xml` | 1316 | `4dcdc9dff6f1d381…` |
| `res/drawable/taxi_30_px_taxidefault.xml` | 732 | `a5acfde4e7690bba…` |
| `res/drawable/taxi_30_px_trogiup.xml` | 1352 | `a31f71c8ed28b97c…` |
| `res/drawable/taxi_40_px_gpsdiemden.xml` | 2424 | `7f4c68533a8ec2dd…` |
| `res/drawable/taxi_40_px_gpsdiemdon.xml` | 2592 | `721710be7f6715b1…` |
| `res/drawable/taxi_40_px_gpsdiemdon_map.xml` | 1716 | `154f76c0ab983dd6…` |
| `res/drawable/taxi_40_px_more_diemden.xml` | 2084 | `21d37e1ab7280458…` |
| `res/drawable/taxi_50_px_themdiadiem.xml` | 1616 | `32c619daf97819a8…` |
| `res/drawable/taxi_active_insureance.xml` | 1420 | `25ad17fd6c41cb17…` |
| `res/drawable/taxi_b_book_fragment_insurance.xml` | 536 | `d81e54c05a72dfdd…` |
| `res/drawable/taxi_background_function.xml` | 600 | `b9c8526e189a8260…` |
| `res/drawable/taxi_background_green_radius6.xml` | 484 | `afa718e73f97788a…` |
| `res/drawable/taxi_background_home_topbar.xml` | 492 | `075e299ef76e40fe…` |
| `res/drawable/taxi_background_hot_notificaiton_16.xml` | 484 | `c3c2460354c36b59…` |
| `res/drawable/taxi_background_select_partner.xml` | 668 | `8af41d764052a695…` |
| `res/drawable/taxi_background_title_function.xml` | 584 | `547dcc3098d5e8a4…` |
| `res/drawable/taxi_background_trip_suggest.xml` | 612 | `ae99763f64fde26c…` |
| `res/drawable/taxi_background_white_radius6.xml` | 484 | `ebd0d19e274e5c0f…` |
| `res/drawable/taxi_bg_arrow_invisible.xml` | 372 | `2c6d6e99b9789290…` |
| `res/drawable/taxi_bg_arrow_visible.xml` | 372 | `b7e9da5a50eb9840…` |
| `res/drawable/taxi_bg_border_yellow.xml` | 668 | `8af41d764052a695…` |
| `res/drawable/taxi_bg_call_parner.xml` | 484 | `f577c75ac32acdb3…` |
| `res/drawable/taxi_bg_car_selected.xml` | 408 | `1809a5daa20edf82…` |
| `res/drawable/taxi_bg_destination.xml` | 372 | `46c916ed3d37d8e0…` |
| `res/drawable/taxi_bg_radius_vol_white.xml` | 484 | `7013dc7cdca47ffa…` |
| `res/drawable/taxi_bg_white_border_gray.xml` | 612 | `124dbef5981cf6a5…` |
| `res/drawable/taxi_bkg_driver_rating_layout.xml` | 484 | `a4a70190b1c4310b…` |
| `res/drawable/taxi_book_fragment_insurance_full.xml` | 484 | `ab4685e5ca2f6b0e…` |
| `res/drawable/taxi_book_fragment_insurance_full_active.xml` | 484 | `80b38c2a54db4235…` |
| `res/drawable/taxi_checked_invoice.xml` | 1200 | `59b4b3400ef5b574…` |
| `res/drawable/taxi_dashline.xml` | 868 | `8ecc3dd019394493…` |
| `res/drawable/taxi_dat_lai_icon.xml` | 2044 | `7aed3cfd2277e948…` |
| `res/drawable/taxi_dieu_khoan_insureance.xml` | 1604 | `78e15a28fdf4a450…` |
| `res/drawable/taxi_email.xml` | 1216 | `526aae0642a34b76…` |
| `res/drawable/taxi_ic_24_px_outline_right_2.xml` | 1236 | `9a291e070c5abde4…` |
| `res/drawable/taxi_ic_choose_location.xml` | 2364 | `4ec0b78c18563242…` |
| `res/drawable/taxi_ic_choose_location_inside.xml` | 1008 | `89f4c7e48dd15af6…` |
| `res/drawable/taxi_ic_choose_location_inside2.xml` | 688 | `12bbf894349bf625…` |
| `res/drawable/taxi_ic_loading_car.png` | 32725 | `db1d495e63a4e8d8…` |
| `res/drawable/taxi_ic_warning.xml` | 1984 | `a7c28c8ebe34767c…` |
| `res/drawable/taxi_icon_address.xml` | 5148 | `d8372813227ff092…` |
| `res/drawable/taxi_icon_link.xml` | 1788 | `1387dd32ffe07bc3…` |
| `res/drawable/taxi_icon_promotion.xml` | 2780 | `f329c8f4323207f1…` |
| `res/drawable/taxi_image_phuongthucthanhtoan.xml` | 1928 | `6afffe7860246f23…` |
| `res/drawable/taxi_island_bkg.xml` | 484 | `a0c2d1211b26c547…` |
| `res/drawable/taxi_myprogress_style.xml` | 952 | `b81d8247d8cb1315…` |
| `res/drawable/taxi_payment_trip_price_bkg.xml` | 484 | `401e3e2405a778c4…` |
| `res/drawable/taxi_pick_address_home_bottom_bkg.xml` | 372 | `4e79fd71829d2bd4…` |
| `res/drawable/taxi_pick_address_home_center_bkg.xml` | 336 | `35ca3dd89878a05f…` |
| `res/drawable/taxi_pick_address_home_top_bkg.xml` | 1216 | `4a0f9b66735e4725…` |
| `res/drawable/taxi_ratingbar_star_state.xml` | 576 | `5eb32071df7f31f4…` |
| `res/drawable/taxi_rectangle_green.xml` | 688 | `6d072ee5b4c61aa6…` |
| `res/drawable/taxi_rectangle_red.xml` | 688 | `5037019684ec7a2f…` |
| `res/drawable/taxi_selector_bg_car_picker.xml` | 1084 | `9d0ead5570dbf8c3…` |
| `res/drawable/taxi_selector_bg_vehicle.xml` | 996 | `db261f926457f4a6…` |
| `res/drawable/taxi_selector_status_linkt.xml` | 484 | `c4ea42c104c3c0f8…` |
| `res/drawable/taxi_shadow_gray.xml` | 372 | `b50aabf2e728d79c…` |
| `res/drawable/taxi_shadow_top2bottom.xml` | 456 | `f47b3340dbeb9946…` |
| `res/drawable/taxi_uncheck_invoive.xml` | 884 | `f0679a2cb289f4ad…` |
| `res/drawable/taxi_warning_gray_16px.xml` | 2068 | `de89305db6706d1b…` |
| `res/drawable/text_color_bank_submit_button.xml` | 464 | `58efb38e082252e1…` |
| `res/drawable/text_color_button_default.xml` | 464 | `58efb38e082252e1…` |
| `res/drawable/tidv3_bg_border_edit.xml` | 1024 | `228d5125af8d7803…` |
| `res/drawable/tidv3_facesdk_back_button.xml` | 688 | `df69de880bf70994…` |
| `res/drawable/tidv3_facesdk_rounded_button_active.xml` | 632 | `f65626a8473f09c4…` |
| `res/drawable/tidv3_nfc_animatiton.gif` | 105646 | `61676dba9f7a9802…` |
| `res/drawable/tidv3_nfc_none.png` | 2465 | `e818813de0604cb1…` |
| `res/drawable/tidv3_nfc_selected.png` | 2609 | `ccb560811e516319…` |
| `res/drawable/tidv3_nfc_selector.xml` | 568 | `acbc58f78e2c1605…` |
| `res/drawable/tidv3_trueid_watermark_dark.xml` | 15452 | `cacb755dbf8156c9…` |
| `res/drawable/tidv3_trueid_watermark_white.xml` | 15452 | `36c187773010d602…` |
| `res/drawable/tooltip_frame_dark.xml` | 484 | `02721092aaa149cd…` |
| `res/drawable/tooltip_frame_light.xml` | 484 | `9e6e68289da774ae…` |
| `res/drawable/top_title_support_center_layout.xml` | 500 | `7e5421e96512fb4e…` |
| `res/drawable/topup_proposal_new_ui_content.png` | 39009 | `b658e196534eb134…` |
| `res/drawable/true_btn_active.xml` | 484 | `5c8306d357f5d326…` |
| `res/drawable/true_ic_back_zlp.xml` | 876 | `43028d0524e990f8…` |
| `res/drawable/true_ic_close.xml` | 772 | `59466dc0df68b71e…` |
| `res/drawable/true_id_conner.xml` | 712 | `dab7ee0be474d845…` |
| `res/drawable/true_id_icon_failed.png` | 1003 | `41791e272e671163…` |
| `res/drawable/true_id_icon_verified.png` | 859 | `daa226253682e1db…` |
| `res/drawable/true_logo_qr.png` | 3498 | `d324a184b46c8aee…` |
| `res/drawable/true_nfc_error.png` | 2514 | `6b8f1bfb0630a96a…` |
| `res/drawable/true_selfie_sample.jpg` | 9152 | `7b0e29098dafffd7…` |
| `res/drawable/trueid_guide_mrz.png` | 8112 | `6d5555a615604fe8…` |
| `res/drawable/txt_bottom_default_focused.xml` | 1112 | `1e31fb79eb387fb9…` |
| `res/drawable/txt_bottom_default_normal.xml` | 1112 | `2f7601ae2f98c984…` |
| `res/drawable/txt_bottom_default_style.xml` | 588 | `ef49b08d54d4a22e…` |
| `res/drawable/underline_number_lock_screen.xml` | 612 | `c9a24a72bb12c4f8…` |
| `res/drawable/user_profile_selector_btn_radio.xml` | 484 | `065aae94e69ca65d…` |
| `res/drawable/vehicle_plate_bkg.xml` | 612 | `9ab07feb2b71832e…` |
| `res/drawable/vertical_dash_line.xml` | 728 | `6ee31b558c19beba…` |
| `res/drawable/vietqr_promotion_banner.png` | 283031 | `5d73dd833a66bab9…` |
| `res/drawable/viewcar_trip_info_bkg.xml` | 536 | `339cf3db71b41b64…` |
| `res/drawable/vnpay_2banh1.png` | 40377 | `6aa305f5b07084f1…` |
| `res/drawable/waves.xml` | 18672 | `c319b988bdb34e1b…` |
| `res/drawable/web_loading_progress_bar.xml` | 808 | `f82d2f495029273e…` |
| `res/drawable/white_triangle.xml` | 912 | `a4304e79147042e1…` |
| `res/drawable/zalo_regis_rounded.xml` | 632 | `d50f65c7c7269cd1…` |
| `res/drawable/zalopay_notification.webp` | 4800 | `548d737038b4d9e9…` |
| `res/drawable/zalosdk_background_rectangle_corner_blue_background_20.xml` | 632 | `2bb04b3b54bd7ba3…` |
| `res/drawable/zalosdk_background_white_stroke_grey_corner_4.xml` | 760 | `9cdeeceea61a7c42…` |
| `res/drawable/zalosdk_progress.xml` | 1016 | `95c31aba2c9c7e05…` |
| `res/font/ds_roboto_bold.ttf` | 167336 | `baf44ce81636cc92…` |
| `res/font/ds_roboto_regular.ttf` | 168260 | `319cff6e7a31f0f2…` |
| `res/font/inter_medium.otf` | 254040 | `3be644f6898e942c…` |
| `res/font/notosans_regular.ttf` | 629024 | `fe8c022f48d8dd29…` |
| `res/font/roboto_bold.ttf` | 170760 | `7d0b991ee3e0be7a…` |
| `res/font/roboto_medium_numbers.ttf` | 3316 | `acbf6c59d8c5765f…` |
| `res/font/sf_compact_rounded_bold.ttf` | 1782560 | `67f6ac2a6598d1f4…` |
| `res/font/sf_compact_rounded_medium.ttf` | 1785056 | `6d06eb43b58b9e3f…` |
| `res/font/sf_pro_display.xml` | 536 | `0409e1031854f271…` |
| `res/font/sf_pro_display_bold.otf` | 334728 | `a981001f6d4e7029…` |
| `res/font/sf_pro_display_regular.ttf` | 413924 | `3d05fcf721bd439b…` |
| `res/font/sf_pro_text_regular.ttf` | 434088 | `6d5514c04a81409a…` |
| `res/font/sf_ui_text_medium.ttf` | 196832 | `13c7fd318e680c6f…` |
| `res/font/sf_ui_text_regular.ttf` | 189872 | `72e6c6caceb74480…` |
| `res/interpolator-v21/mtrl_fast_out_linear_in.xml` | 400 | `005b9002f91605d0…` |
| `res/interpolator-v21/mtrl_fast_out_slow_in.xml` | 400 | `9c6cba46748f3cb2…` |
| `res/interpolator-v21/mtrl_linear_out_slow_in.xml` | 400 | `40e15e585d804bc6…` |
| `res/interpolator/btn_checkbox_checked_mtrl_animation_interpolator_0.xml` | 316 | `68b7fc0e819c526c…` |
| `res/interpolator/btn_checkbox_checked_mtrl_animation_interpolator_1.xml` | 328 | `0ab3bc82b3b9fd84…` |
| `res/interpolator/btn_checkbox_unchecked_mtrl_animation_interpolator_0.xml` | 316 | `68b7fc0e819c526c…` |
| `res/interpolator/btn_checkbox_unchecked_mtrl_animation_interpolator_1.xml` | 328 | `0ab3bc82b3b9fd84…` |
| `res/interpolator/btn_radio_to_off_mtrl_animation_interpolator_0.xml` | 320 | `a8d7341c20d07c9f…` |
| `res/interpolator/btn_radio_to_on_mtrl_animation_interpolator_0.xml` | 320 | `a8d7341c20d07c9f…` |
| `res/interpolator/fast_out_slow_in.xml` | 400 | `8237d47bafc3bab9…` |
| `res/interpolator/glance_btn_checkbox_checked_mtrl_animation_interpolator_0.xml` | 316 | `68b7fc0e819c526c…` |
| `res/interpolator/glance_btn_checkbox_checked_mtrl_animation_interpolator_1.xml` | 328 | `0ab3bc82b3b9fd84…` |
| `res/interpolator/glance_btn_checkbox_unchecked_mtrl_animation_interpolator_0.xml` | 316 | `68b7fc0e819c526c…` |
| `res/interpolator/glance_btn_checkbox_unchecked_mtrl_animation_interpolator_1.xml` | 328 | `0ab3bc82b3b9fd84…` |
| `res/interpolator/glance_btn_radio_to_off_mtrl_animation_interpolator_0.xml` | 320 | `c6a7da7d57436534…` |
| `res/interpolator/glance_btn_radio_to_on_mtrl_animation_interpolator_0.xml` | 320 | `c6a7da7d57436534…` |
| `res/interpolator/mtrl_linear.xml` | 132 | `b94a37a5c87f404d…` |
| `res/layout-land/material_clock_period_toggle_land.xml` | 1276 | `5a81d8445c533e11…` |
| `res/layout-land/material_timepicker.xml` | 1884 | `ba71b5fae4e1e3bd…` |
| `res/layout-land/mtrl_picker_header_dialog.xml` | 1440 | `e82ccab4f251a34d…` |
| `res/layout-sw600dp-v13/design_layout_snackbar.xml` | 528 | `581af1a9ac4e9bb6…` |
| `res/layout-sw600dp-v13/mtrl_layout_snackbar.xml` | 492 | `d956889490b51dbf…` |
| `res/layout-v23/exo_player_control_ffwd_button.xml` | 408 | `296d1e7b7e2c19ef…` |
| `res/layout-v23/exo_player_control_rewind_button.xml` | 408 | `a71d7488daa7aee8…` |
| `res/layout-v26/abc_screen_toolbar.xml` | 1560 | `946b75ba4ff7f578…` |
| `res/layout-v26/mtrl_calendar_month.xml` | 744 | `6033af5d6d632039…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_0.xml` | 468 | `74b801b49e034631…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_1.xml` | 468 | `9b234da86d8de290…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_2.xml` | 468 | `6b2204d516ae7029…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_3.xml` | 468 | `09ced16416574a14…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_4.xml` | 468 | `4adb45220deea60a…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_5.xml` | 468 | `4733b8cad8dc73c4…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_6.xml` | 468 | `1205395c9f98245f…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_7.xml` | 468 | `574627910b2134e4…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_8.xml` | 468 | `34a9a5e73ffd692f…` |
| `res/layout-v31/box_child_center_horizontal_bottom_group_9.xml` | 468 | `27a23d8842176d01…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_0.xml` | 468 | `1b246556e459dd5e…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_1.xml` | 468 | `9fd612c7e9ed797b…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_2.xml` | 468 | `6612132e0d9945ec…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_3.xml` | 468 | `fd7fd39ee9d6b4d5…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_4.xml` | 468 | `9efc4125022eabea…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_5.xml` | 468 | `b41bfd444cfe0903…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_6.xml` | 468 | `ff3a32738c54165c…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_7.xml` | 468 | `30a6717dd6e46d5d…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_8.xml` | 468 | `e340bba0ac4b059e…` |
| `res/layout-v31/box_child_center_horizontal_center_vertical_group_9.xml` | 468 | `73417d003e70e9ec…` |
| `res/layout-v31/box_child_center_horizontal_top_group_0.xml` | 468 | `4c3d68eb7d9eaf47…` |
| `res/layout-v31/box_child_center_horizontal_top_group_1.xml` | 468 | `beef6cbf74885131…` |
| `res/layout-v31/box_child_center_horizontal_top_group_2.xml` | 468 | `fda884e7a245750e…` |
| `res/layout-v31/box_child_center_horizontal_top_group_3.xml` | 468 | `34c10eb39b8288d6…` |
| `res/layout-v31/box_child_center_horizontal_top_group_4.xml` | 468 | `0f18976b1e07404e…` |
| `res/layout-v31/box_child_center_horizontal_top_group_5.xml` | 468 | `1bb4cd5adb7cf33f…` |
| `res/layout-v31/box_child_center_horizontal_top_group_6.xml` | 468 | `d42103ab242831e7…` |
| `res/layout-v31/box_child_center_horizontal_top_group_7.xml` | 468 | `ddaf02ae1dbdd787…` |
| `res/layout-v31/box_child_center_horizontal_top_group_8.xml` | 468 | `e464f9386692382f…` |
| `res/layout-v31/box_child_center_horizontal_top_group_9.xml` | 468 | `c2f4f20e50927a29…` |
| `res/layout-v31/box_child_end_bottom_group_0.xml` | 468 | `0cc7fc0593c356e2…` |
| `res/layout-v31/box_child_end_bottom_group_1.xml` | 468 | `2f7ce99f9494f0bb…` |
| `res/layout-v31/box_child_end_bottom_group_2.xml` | 468 | `615dca652a2c0386…` |
| `res/layout-v31/box_child_end_bottom_group_3.xml` | 468 | `35b73f60dfdb1bd4…` |
| `res/layout-v31/box_child_end_bottom_group_4.xml` | 468 | `63e726c14e07d4ea…` |
| `res/layout-v31/box_child_end_bottom_group_5.xml` | 468 | `0fbb696d6317b2ea…` |
| `res/layout-v31/box_child_end_bottom_group_6.xml` | 468 | `fbf027c4ca009ec9…` |
| `res/layout-v31/box_child_end_bottom_group_7.xml` | 468 | `6fd5ec06ab7efa10…` |
| `res/layout-v31/box_child_end_bottom_group_8.xml` | 468 | `bc526636142e4615…` |
| `res/layout-v31/box_child_end_bottom_group_9.xml` | 468 | `889d4f8895e901be…` |
| `res/layout-v31/box_child_end_center_vertical_group_0.xml` | 468 | `6a1d909893e60e30…` |
| `res/layout-v31/box_child_end_center_vertical_group_1.xml` | 468 | `aa9a8542ccd44196…` |
| `res/layout-v31/box_child_end_center_vertical_group_2.xml` | 468 | `fef0e2315864b965…` |
| `res/layout-v31/box_child_end_center_vertical_group_3.xml` | 468 | `52c6b668b3950679…` |
| `res/layout-v31/box_child_end_center_vertical_group_4.xml` | 468 | `9006b50523e7a09a…` |
| `res/layout-v31/box_child_end_center_vertical_group_5.xml` | 468 | `bdb40fc94c7a216a…` |
| `res/layout-v31/box_child_end_center_vertical_group_6.xml` | 468 | `ed61661f5740dd3d…` |
| `res/layout-v31/box_child_end_center_vertical_group_7.xml` | 468 | `3edbb85e7ae5c54f…` |
| `res/layout-v31/box_child_end_center_vertical_group_8.xml` | 468 | `9327b9920b3b5f5d…` |
| `res/layout-v31/box_child_end_center_vertical_group_9.xml` | 468 | `8f898ebea7d43918…` |
| `res/layout-v31/box_child_end_top_group_0.xml` | 468 | `55e84523112201c7…` |
| `res/layout-v31/box_child_end_top_group_1.xml` | 468 | `e4f399ec1c07d6a4…` |
| `res/layout-v31/box_child_end_top_group_2.xml` | 468 | `22fae6af815a7ef5…` |
| `res/layout-v31/box_child_end_top_group_3.xml` | 468 | `7c3a2e77949723d7…` |
| `res/layout-v31/box_child_end_top_group_4.xml` | 468 | `6a42cb89b6da3dcf…` |
| `res/layout-v31/box_child_end_top_group_5.xml` | 468 | `1fd3337df10af3e3…` |
| `res/layout-v31/box_child_end_top_group_6.xml` | 468 | `f1799afb5d5484de…` |
| `res/layout-v31/box_child_end_top_group_7.xml` | 468 | `7f41a0c0648efa8e…` |
| `res/layout-v31/box_child_end_top_group_8.xml` | 468 | `79810a04a46cd0ec…` |
| `res/layout-v31/box_child_end_top_group_9.xml` | 468 | `3d6b16a99f907326…` |
| `res/layout-v31/box_child_start_bottom_group_0.xml` | 468 | `c5e955329ceaf4bb…` |
| `res/layout-v31/box_child_start_bottom_group_1.xml` | 468 | `40b96708827af7d8…` |
| `res/layout-v31/box_child_start_bottom_group_2.xml` | 468 | `3f7c41697027d097…` |
| `res/layout-v31/box_child_start_bottom_group_3.xml` | 468 | `a74a800dd355f099…` |
| `res/layout-v31/box_child_start_bottom_group_4.xml` | 468 | `8a2d17f1b3c242d3…` |
| `res/layout-v31/box_child_start_bottom_group_5.xml` | 468 | `2d87ee52f7ef08c7…` |
| `res/layout-v31/box_child_start_bottom_group_6.xml` | 468 | `eb99689638dca9c4…` |
| `res/layout-v31/box_child_start_bottom_group_7.xml` | 468 | `41ac09bc7ceb3ea5…` |
| `res/layout-v31/box_child_start_bottom_group_8.xml` | 468 | `66afcd5ddf19f1dc…` |
| `res/layout-v31/box_child_start_bottom_group_9.xml` | 468 | `614a2486ee00100e…` |
| `res/layout-v31/box_child_start_center_vertical_group_0.xml` | 468 | `01dd290cadcc36d6…` |
| `res/layout-v31/box_child_start_center_vertical_group_1.xml` | 468 | `3199c080866bec90…` |
| `res/layout-v31/box_child_start_center_vertical_group_2.xml` | 468 | `5d6060aa12a72435…` |
| `res/layout-v31/box_child_start_center_vertical_group_3.xml` | 468 | `f0768505ac1f356c…` |
| `res/layout-v31/box_child_start_center_vertical_group_4.xml` | 468 | `ff7f52acb7ce3136…` |
| `res/layout-v31/box_child_start_center_vertical_group_5.xml` | 468 | `e057780c3b616f15…` |
| `res/layout-v31/box_child_start_center_vertical_group_6.xml` | 468 | `10ec91e92c9b6708…` |
| `res/layout-v31/box_child_start_center_vertical_group_7.xml` | 468 | `0872f5f20fff105b…` |
| `res/layout-v31/box_child_start_center_vertical_group_8.xml` | 468 | `26e606ea74648ee3…` |
| `res/layout-v31/box_child_start_center_vertical_group_9.xml` | 468 | `c12bcc18268d1809…` |
| `res/layout-v31/box_child_start_top_group_0.xml` | 468 | `87ba3e841a7f68e1…` |
| `res/layout-v31/box_child_start_top_group_1.xml` | 468 | `d46ef8b04902f6b1…` |
| `res/layout-v31/box_child_start_top_group_2.xml` | 468 | `45b3b8f110ffad01…` |
| `res/layout-v31/box_child_start_top_group_3.xml` | 468 | `0a9bc768efd375b0…` |
| `res/layout-v31/box_child_start_top_group_4.xml` | 468 | `7a64bb69d19902cb…` |
| `res/layout-v31/box_child_start_top_group_5.xml` | 468 | `e216d0cbfd94b318…` |
| `res/layout-v31/box_child_start_top_group_6.xml` | 468 | `de8d5a7ca5c48f7a…` |
| `res/layout-v31/box_child_start_top_group_7.xml` | 468 | `17404f5a9adbd34f…` |
| `res/layout-v31/box_child_start_top_group_8.xml` | 468 | `8bbd6d7ed230208e…` |
| `res/layout-v31/box_child_start_top_group_9.xml` | 468 | `a129712b735f1812…` |
| `res/layout-v31/column_child_center_horizontal_null_group_0.xml` | 700 | `4a37999e8199444c…` |
| `res/layout-v31/column_child_center_horizontal_null_group_1.xml` | 700 | `ab76257294b25c16…` |
| `res/layout-v31/column_child_center_horizontal_null_group_2.xml` | 700 | `c238232a11c1420c…` |
| `res/layout-v31/column_child_center_horizontal_null_group_3.xml` | 700 | `9c5530fe6e87e4b2…` |
| `res/layout-v31/column_child_center_horizontal_null_group_4.xml` | 700 | `bc37e46afcee4857…` |
| `res/layout-v31/column_child_center_horizontal_null_group_5.xml` | 700 | `6ef284c1ef8101a6…` |
| `res/layout-v31/column_child_center_horizontal_null_group_6.xml` | 700 | `88cae36fb9c07e4a…` |
| `res/layout-v31/column_child_center_horizontal_null_group_7.xml` | 700 | `1c653cd2994f7383…` |
| `res/layout-v31/column_child_center_horizontal_null_group_8.xml` | 700 | `a3f05d4748661624…` |
| `res/layout-v31/column_child_center_horizontal_null_group_9.xml` | 700 | `17aa3ff104e98edb…` |
| `res/layout-v31/column_child_end_null_group_0.xml` | 700 | `e161cbec58d119e6…` |
| `res/layout-v31/column_child_end_null_group_1.xml` | 700 | `9fe5a94df8e47b73…` |
| `res/layout-v31/column_child_end_null_group_2.xml` | 700 | `57093113c9c4df28…` |
| `res/layout-v31/column_child_end_null_group_3.xml` | 700 | `c1e459a52c2f17ff…` |
| `res/layout-v31/column_child_end_null_group_4.xml` | 700 | `1d6769c1f2cfc8e5…` |
| `res/layout-v31/column_child_end_null_group_5.xml` | 700 | `8a8d39eed0cab45d…` |
| `res/layout-v31/column_child_end_null_group_6.xml` | 700 | `f8f4e12d5bb73630…` |
| `res/layout-v31/column_child_end_null_group_7.xml` | 700 | `474d7dbabf769786…` |
| `res/layout-v31/column_child_end_null_group_8.xml` | 700 | `b3815677198b9c9d…` |
| `res/layout-v31/column_child_end_null_group_9.xml` | 700 | `98b1b9847685d5aa…` |
| `res/layout-v31/column_child_start_null_group_0.xml` | 700 | `e9bfa517e69b8c73…` |
| `res/layout-v31/column_child_start_null_group_1.xml` | 700 | `7245d9dc21f49719…` |
| `res/layout-v31/column_child_start_null_group_2.xml` | 700 | `893962dd417f2c4c…` |
| `res/layout-v31/column_child_start_null_group_3.xml` | 700 | `178f72331c8a946f…` |
| `res/layout-v31/column_child_start_null_group_4.xml` | 700 | `6b85a8c1df756d50…` |
| `res/layout-v31/column_child_start_null_group_5.xml` | 700 | `1369a1ad60b48e7f…` |
| `res/layout-v31/column_child_start_null_group_6.xml` | 700 | `0caa249f26b7627f…` |
| `res/layout-v31/column_child_start_null_group_7.xml` | 700 | `abe72f0e059bf14f…` |
| `res/layout-v31/column_child_start_null_group_8.xml` | 700 | `bb23e84a12aa452b…` |
| `res/layout-v31/column_child_start_null_group_9.xml` | 700 | `21d88c651e7761bc…` |
| `res/layout-v31/row_child_null_bottom_group_0.xml` | 700 | `972584d3a472f21b…` |
| `res/layout-v31/row_child_null_bottom_group_1.xml` | 700 | `ccec1f8c8be92c48…` |
| `res/layout-v31/row_child_null_bottom_group_2.xml` | 700 | `6398212ec6f46019…` |
| `res/layout-v31/row_child_null_bottom_group_3.xml` | 700 | `961e19193d6c050c…` |
| `res/layout-v31/row_child_null_bottom_group_4.xml` | 700 | `161d51d366c00194…` |
| `res/layout-v31/row_child_null_bottom_group_5.xml` | 700 | `e892351225c05bf2…` |
| `res/layout-v31/row_child_null_bottom_group_6.xml` | 700 | `2ae312d818965830…` |
| `res/layout-v31/row_child_null_bottom_group_7.xml` | 700 | `db5f914d3d751f1f…` |
| `res/layout-v31/row_child_null_bottom_group_8.xml` | 700 | `69fbb9b38cf3bdb1…` |
| `res/layout-v31/row_child_null_bottom_group_9.xml` | 700 | `749d7b33ec359128…` |
| `res/layout-v31/row_child_null_center_vertical_group_0.xml` | 700 | `170231c5dc67dd6f…` |
| `res/layout-v31/row_child_null_center_vertical_group_1.xml` | 700 | `907ee03b02f50707…` |
| `res/layout-v31/row_child_null_center_vertical_group_2.xml` | 700 | `ca6dee5e355e77f9…` |
| `res/layout-v31/row_child_null_center_vertical_group_3.xml` | 700 | `d72bcb515542f228…` |
| `res/layout-v31/row_child_null_center_vertical_group_4.xml` | 700 | `998a732b9b77f7af…` |
| `res/layout-v31/row_child_null_center_vertical_group_5.xml` | 700 | `25893cfbd37f205a…` |
| `res/layout-v31/row_child_null_center_vertical_group_6.xml` | 700 | `83867e3dffddb4a0…` |
| `res/layout-v31/row_child_null_center_vertical_group_7.xml` | 700 | `7befceffaf37b023…` |
| `res/layout-v31/row_child_null_center_vertical_group_8.xml` | 700 | `bd6c07d03d12f046…` |
| `res/layout-v31/row_child_null_center_vertical_group_9.xml` | 700 | `a2e84f47d7de97ae…` |
| `res/layout-v31/row_child_null_top_group_0.xml` | 700 | `a00250efb3720070…` |
| `res/layout-v31/row_child_null_top_group_1.xml` | 700 | `78fcb5cb9da2d5a1…` |
| `res/layout-v31/row_child_null_top_group_2.xml` | 700 | `b40d9988b476c6c6…` |
| `res/layout-v31/row_child_null_top_group_3.xml` | 700 | `9977be332ced1308…` |
| `res/layout-v31/row_child_null_top_group_4.xml` | 700 | `cd6d98c65f00252f…` |
| `res/layout-v31/row_child_null_top_group_5.xml` | 700 | `c359f62d7bc5475a…` |
| `res/layout-v31/row_child_null_top_group_6.xml` | 700 | `50fc485494658cd9…` |
| `res/layout-v31/row_child_null_top_group_7.xml` | 700 | `ac653439d3f88e49…` |
| `res/layout-v31/row_child_null_top_group_8.xml` | 700 | `07d05b1ea36eaea9…` |
| `res/layout-v31/row_child_null_top_group_9.xml` | 700 | `931821995208534d…` |
| `res/layout-v33/box_center_horizontal_bottom.xml` | 476 | `d9de3d05e30bc56f…` |
| `res/layout-v33/box_center_horizontal_center_vertical.xml` | 476 | `21d02d2fc1aefd1c…` |
| `res/layout-v33/box_center_horizontal_top.xml` | 476 | `2bc959b722e8e877…` |
| `res/layout-v33/box_end_bottom.xml` | 476 | `edc6106cb00531c1…` |
| `res/layout-v33/box_end_center_vertical.xml` | 476 | `8e2efbeb870c2939…` |
| `res/layout-v33/box_end_top.xml` | 476 | `26a97ecfa1a338fb…` |
| `res/layout-v33/box_expandwidth_wrapheight.xml` | 476 | `ea9339bb8ff7c098…` |
| `res/layout-v33/box_start_bottom.xml` | 476 | `1c29141bbba152d6…` |
| `res/layout-v33/box_start_center_vertical.xml` | 476 | `ae9e96fa41303565…` |
| `res/layout-v33/box_start_top.xml` | 476 | `b277879be0eb33da…` |
| `res/layout-v33/box_wrapwidth_expandheight.xml` | 476 | `e38d2a71a8ceba17…` |
| `res/layout-v33/column_center_horizontal_bottom.xml` | 520 | `e55daa370d41c4d5…` |
| `res/layout-v33/column_center_horizontal_center_vertical.xml` | 520 | `d32a169b72766606…` |
| `res/layout-v33/column_center_horizontal_top.xml` | 520 | `f593f87cb7cbc7cb…` |
| `res/layout-v33/column_end_bottom.xml` | 520 | `82687e6b86abd1bd…` |
| `res/layout-v33/column_end_center_vertical.xml` | 520 | `3ba5501e598088e9…` |
| `res/layout-v33/column_end_top.xml` | 520 | `05db55828f9c47c1…` |
| `res/layout-v33/column_expandwidth_wrapheight.xml` | 520 | `27cf97d182a17fda…` |
| `res/layout-v33/column_start_bottom.xml` | 520 | `9c9ae7ada3f99343…` |
| `res/layout-v33/column_start_center_vertical.xml` | 520 | `30c2786a545f9760…` |
| `res/layout-v33/column_start_top.xml` | 520 | `1df590ad1a2b95c2…` |
| `res/layout-v33/column_wrapwidth_expandheight.xml` | 520 | `0e1f5088a0441d28…` |
| `res/layout-v33/glance_button_center_horizontal_bottom.xml` | 800 | `b43facbb9513e255…` |
| `res/layout-v33/glance_button_center_horizontal_center_vertical.xml` | 800 | `c28c75d03617b01d…` |
| `res/layout-v33/glance_button_center_horizontal_top.xml` | 800 | `46bf89036c3df2f8…` |
| `res/layout-v33/glance_button_end_bottom.xml` | 800 | `6d5e2e527ebf1f46…` |
| `res/layout-v33/glance_button_end_center_vertical.xml` | 800 | `7682c5b722f35a99…` |
| `res/layout-v33/glance_button_end_top.xml` | 800 | `49ac281aa3e9fb78…` |
| `res/layout-v33/glance_button_expandwidth_wrapheight.xml` | 796 | `4898c4a8e280c802…` |
| `res/layout-v33/glance_button_start_bottom.xml` | 800 | `cb7c9ac54f15b05e…` |
| `res/layout-v33/glance_button_start_center_vertical.xml` | 800 | `2af46f2b55e63f63…` |
| `res/layout-v33/glance_button_start_top.xml` | 800 | `f007655258475d1f…` |
| `res/layout-v33/glance_button_wrapwidth_expandheight.xml` | 796 | `250e6b0739a0d98b…` |
| `res/layout-v33/glance_check_box_backport_center_horizontal_bottom.xml` | 1196 | `ac9e96cfdb6fcc8a…` |
| `res/layout-v33/glance_check_box_backport_center_horizontal_center_vertical.xml` | 1196 | `abd7bd91b0f38869…` |
| `res/layout-v33/glance_check_box_backport_center_horizontal_top.xml` | 1196 | `fbd4e7b6039441df…` |
| `res/layout-v33/glance_check_box_backport_end_bottom.xml` | 1196 | `cd0feba384726357…` |
| `res/layout-v33/glance_check_box_backport_end_center_vertical.xml` | 1196 | `ead366fc89430a4a…` |
| `res/layout-v33/glance_check_box_backport_end_top.xml` | 1196 | `38db366324e4133d…` |
| `res/layout-v33/glance_check_box_backport_expandwidth_wrapheight.xml` | 1172 | `8ead04621c19fca2…` |
| `res/layout-v33/glance_check_box_backport_start_bottom.xml` | 1196 | `f6ec16304409277d…` |
| `res/layout-v33/glance_check_box_backport_start_center_vertical.xml` | 1196 | `1615eab50e9dad14…` |
| `res/layout-v33/glance_check_box_backport_start_top.xml` | 1196 | `652301a0faf754e1…` |
| `res/layout-v33/glance_check_box_backport_wrapwidth_expandheight.xml` | 1172 | `482ad5bfa3980bcc…` |
| `res/layout-v33/glance_check_box_center_horizontal_bottom.xml` | 852 | `dffcab2a1eb6dc00…` |
| `res/layout-v33/glance_check_box_center_horizontal_center_vertical.xml` | 852 | `6578c574d0013c3b…` |
| `res/layout-v33/glance_check_box_center_horizontal_top.xml` | 852 | `69b5bdfc82fe8b6f…` |
| `res/layout-v33/glance_check_box_end_bottom.xml` | 852 | `001b64d3f014f8cd…` |
| `res/layout-v33/glance_check_box_end_center_vertical.xml` | 852 | `433a887168e99ca4…` |
| `res/layout-v33/glance_check_box_end_top.xml` | 852 | `c8d37b1dd48b75b9…` |
| `res/layout-v33/glance_check_box_expandwidth_wrapheight.xml` | 852 | `59bf6791d2b50c41…` |
| `res/layout-v33/glance_check_box_start_bottom.xml` | 852 | `4f31450b333b5037…` |
| `res/layout-v33/glance_check_box_start_center_vertical.xml` | 852 | `b25fcdc17f11e657…` |
| `res/layout-v33/glance_check_box_start_top.xml` | 852 | `3b14b8804b7a268c…` |
| `res/layout-v33/glance_check_box_wrapwidth_expandheight.xml` | 852 | `adde51e2832e6b88…` |
| `res/layout-v33/glance_circular_progress_indicator_center_horizontal_bottom.xml` | 636 | `0dd32b7710a5fcba…` |
| `res/layout-v33/glance_circular_progress_indicator_center_horizontal_center_vertical.xml` | 636 | `92a65be89b6704f5…` |
| `res/layout-v33/glance_circular_progress_indicator_center_horizontal_top.xml` | 636 | `3e4a34c98e5f6789…` |
| `res/layout-v33/glance_circular_progress_indicator_end_bottom.xml` | 636 | `aa8d371f97d09c4b…` |
| `res/layout-v33/glance_circular_progress_indicator_end_center_vertical.xml` | 636 | `df89e47395e1ff0a…` |
| `res/layout-v33/glance_circular_progress_indicator_end_top.xml` | 636 | `98542aaff08dbbad…` |
| `res/layout-v33/glance_circular_progress_indicator_expandwidth_wrapheight.xml` | 636 | `0bdf2b258b9c305e…` |
| `res/layout-v33/glance_circular_progress_indicator_start_bottom.xml` | 636 | `d2a9ea847c9c5d80…` |
| `res/layout-v33/glance_circular_progress_indicator_start_center_vertical.xml` | 636 | `c5fdb181633bc521…` |
| `res/layout-v33/glance_circular_progress_indicator_start_top.xml` | 636 | `0d06281640d5d0a6…` |
| `res/layout-v33/glance_circular_progress_indicator_wrapwidth_expandheight.xml` | 636 | `afbb9fb68f7f0994…` |
| `res/layout-v33/glance_frame_center_horizontal_bottom.xml` | 604 | `5c193b113a07fec1…` |
| `res/layout-v33/glance_frame_center_horizontal_center_vertical.xml` | 604 | `8948ac4b74e0321f…` |
| `res/layout-v33/glance_frame_center_horizontal_top.xml` | 604 | `2dc0d35ebba94c4a…` |
| `res/layout-v33/glance_frame_end_bottom.xml` | 604 | `1295cc3ec2f12364…` |
| `res/layout-v33/glance_frame_end_center_vertical.xml` | 604 | `db75b9801960ed4b…` |
| `res/layout-v33/glance_frame_end_top.xml` | 604 | `a5b5c6dac68c30dd…` |
| `res/layout-v33/glance_frame_expandwidth_wrapheight.xml` | 604 | `b76071c91b8f7450…` |
| `res/layout-v33/glance_frame_start_bottom.xml` | 604 | `42e6ca8720b0a302…` |
| `res/layout-v33/glance_frame_start_center_vertical.xml` | 604 | `c0141ee8e6597896…` |
| `res/layout-v33/glance_frame_start_top.xml` | 604 | `01bc2b7a01b308ca…` |
| `res/layout-v33/glance_frame_wrapwidth_expandheight.xml` | 604 | `1f89a8f715681024…` |
| `res/layout-v33/glance_image_crop_center_horizontal_bottom.xml` | 640 | `06012426f6a30f45…` |
| `res/layout-v33/glance_image_crop_center_horizontal_center_vertical.xml` | 640 | `d432b34bfb0e56e0…` |
| `res/layout-v33/glance_image_crop_center_horizontal_top.xml` | 640 | `b1e1e68e7d71bb91…` |
| `res/layout-v33/glance_image_crop_decorative_center_horizontal_bottom.xml` | 696 | `9e975162b7834edb…` |
| `res/layout-v33/glance_image_crop_decorative_center_horizontal_center_vertical.xml` | 696 | `68525c8aa7e84b49…` |
| `res/layout-v33/glance_image_crop_decorative_center_horizontal_top.xml` | 696 | `e309ebbc51b1243e…` |
| `res/layout-v33/glance_image_crop_decorative_end_bottom.xml` | 696 | `ddb1e81202677dec…` |
| `res/layout-v33/glance_image_crop_decorative_end_center_vertical.xml` | 696 | `8d558fb665ace610…` |
| `res/layout-v33/glance_image_crop_decorative_end_top.xml` | 696 | `9dd8a2c9d2b1eecb…` |
| `res/layout-v33/glance_image_crop_decorative_expandwidth_wrapheight.xml` | 696 | `53ca2d614353000d…` |
| `res/layout-v33/glance_image_crop_decorative_start_bottom.xml` | 696 | `5d01a06c6ac6f99a…` |
| `res/layout-v33/glance_image_crop_decorative_start_center_vertical.xml` | 696 | `45cf20de987e3900…` |
| `res/layout-v33/glance_image_crop_decorative_start_top.xml` | 696 | `83a8b9e599bd6878…` |
| `res/layout-v33/glance_image_crop_decorative_wrapwidth_expandheight.xml` | 696 | `f74b53a78ba094e4…` |
| `res/layout-v33/glance_image_crop_end_bottom.xml` | 640 | `166e6d8f41a6676f…` |
| `res/layout-v33/glance_image_crop_end_center_vertical.xml` | 640 | `562749cea2fe4150…` |
| `res/layout-v33/glance_image_crop_end_top.xml` | 640 | `cada86638e579808…` |
| `res/layout-v33/glance_image_crop_expandwidth_wrapheight.xml` | 640 | `d850e9924a522b5d…` |
| `res/layout-v33/glance_image_crop_start_bottom.xml` | 640 | `0092b66cf6633b46…` |
| `res/layout-v33/glance_image_crop_start_center_vertical.xml` | 640 | `19e6ac05f421a5d2…` |
| `res/layout-v33/glance_image_crop_start_top.xml` | 640 | `302a5ee5d38d5460…` |
| `res/layout-v33/glance_image_crop_wrapwidth_expandheight.xml` | 640 | `c820aa1541d06d45…` |
| `res/layout-v33/glance_image_fill_bounds_center_horizontal_bottom.xml` | 640 | `8776b0440309e241…` |
| `res/layout-v33/glance_image_fill_bounds_center_horizontal_center_vertical.xml` | 640 | `a1ebefe6254cef3f…` |
| `res/layout-v33/glance_image_fill_bounds_center_horizontal_top.xml` | 640 | `e519bd02e58b4603…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_center_horizontal_bottom.xml` | 696 | `e01528d1d5f3b028…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_center_horizontal_center_vertical.xml` | 696 | `8ea17402be938f21…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_center_horizontal_top.xml` | 696 | `f5d5ba9d3e1737c4…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_end_bottom.xml` | 696 | `652c4d28d07adfc6…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_end_center_vertical.xml` | 696 | `0fb8e306b517d593…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_end_top.xml` | 696 | `ffde06a9962802ba…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_expandwidth_wrapheight.xml` | 696 | `de73929025206ea6…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_start_bottom.xml` | 696 | `a40d181104f1b43d…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_start_center_vertical.xml` | 696 | `2cd0e80688616b91…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_start_top.xml` | 696 | `49211b956e7acbd2…` |
| `res/layout-v33/glance_image_fill_bounds_decorative_wrapwidth_expandheight.xml` | 696 | `fc1708c24c756814…` |
| `res/layout-v33/glance_image_fill_bounds_end_bottom.xml` | 640 | `137184bd089378db…` |
| `res/layout-v33/glance_image_fill_bounds_end_center_vertical.xml` | 640 | `0c68eda188c83884…` |
| `res/layout-v33/glance_image_fill_bounds_end_top.xml` | 640 | `790d5004beb05859…` |
| `res/layout-v33/glance_image_fill_bounds_expandwidth_wrapheight.xml` | 640 | `e219c8bc967d386b…` |
| `res/layout-v33/glance_image_fill_bounds_start_bottom.xml` | 640 | `9c000274a23e6364…` |
| `res/layout-v33/glance_image_fill_bounds_start_center_vertical.xml` | 640 | `cd24742c835d7d82…` |
| `res/layout-v33/glance_image_fill_bounds_start_top.xml` | 640 | `1550aa7f6a704f0f…` |
| `res/layout-v33/glance_image_fill_bounds_wrapwidth_expandheight.xml` | 640 | `7bacf0e991f7a85e…` |
| `res/layout-v33/glance_image_fit_center_horizontal_bottom.xml` | 640 | `dd6cd92bc0841bd9…` |
| `res/layout-v33/glance_image_fit_center_horizontal_center_vertical.xml` | 640 | `6c090b5f81f0095e…` |
| `res/layout-v33/glance_image_fit_center_horizontal_top.xml` | 640 | `8bdc091d92d28109…` |
| `res/layout-v33/glance_image_fit_decorative_center_horizontal_bottom.xml` | 696 | `93201c99fbc42686…` |
| `res/layout-v33/glance_image_fit_decorative_center_horizontal_center_vertical.xml` | 696 | `977fd2cb1d006397…` |
| `res/layout-v33/glance_image_fit_decorative_center_horizontal_top.xml` | 696 | `bab8031babef981a…` |
| `res/layout-v33/glance_image_fit_decorative_end_bottom.xml` | 696 | `2adb38ffeaba1dc5…` |
| `res/layout-v33/glance_image_fit_decorative_end_center_vertical.xml` | 696 | `89289f0d79b4c349…` |
| `res/layout-v33/glance_image_fit_decorative_end_top.xml` | 696 | `1c800a7dfa6f70c2…` |
| `res/layout-v33/glance_image_fit_decorative_expandwidth_wrapheight.xml` | 696 | `9dbf8aeaa10e91ef…` |
| `res/layout-v33/glance_image_fit_decorative_start_bottom.xml` | 696 | `d48862a473e382b8…` |
| `res/layout-v33/glance_image_fit_decorative_start_center_vertical.xml` | 696 | `b084bbf3b5cba27d…` |
| `res/layout-v33/glance_image_fit_decorative_start_top.xml` | 696 | `edce67f9dd684f3f…` |
| `res/layout-v33/glance_image_fit_decorative_wrapwidth_expandheight.xml` | 696 | `980a6d3125d30b62…` |
| `res/layout-v33/glance_image_fit_end_bottom.xml` | 640 | `aeec44798894910a…` |
| `res/layout-v33/glance_image_fit_end_center_vertical.xml` | 640 | `8c14cd73ffa9da0f…` |
| `res/layout-v33/glance_image_fit_end_top.xml` | 640 | `ddd08de266609ef1…` |
| `res/layout-v33/glance_image_fit_expandwidth_wrapheight.xml` | 640 | `5b0318ecfc8fb62e…` |
| `res/layout-v33/glance_image_fit_start_bottom.xml` | 640 | `a23312bad5162b2a…` |
| `res/layout-v33/glance_image_fit_start_center_vertical.xml` | 640 | `0b8460d25660c8c8…` |
| `res/layout-v33/glance_image_fit_start_top.xml` | 640 | `3f285a37eeaccd9c…` |
| `res/layout-v33/glance_image_fit_wrapwidth_expandheight.xml` | 640 | `455aa7b3a72da0f1…` |
| `res/layout-v33/glance_linear_progress_indicator_center_horizontal_bottom.xml` | 636 | `9d774c98fa5a328b…` |
| `res/layout-v33/glance_linear_progress_indicator_center_horizontal_center_vertical.xml` | 636 | `366ed518a6fdf580…` |
| `res/layout-v33/glance_linear_progress_indicator_center_horizontal_top.xml` | 636 | `c06a64df5cc6bef7…` |
| `res/layout-v33/glance_linear_progress_indicator_end_bottom.xml` | 636 | `d83b909bc2ea1f16…` |
| `res/layout-v33/glance_linear_progress_indicator_end_center_vertical.xml` | 636 | `aad6c7cf5299942f…` |
| `res/layout-v33/glance_linear_progress_indicator_end_top.xml` | 636 | `34cbf21e4babc694…` |
| `res/layout-v33/glance_linear_progress_indicator_expandwidth_wrapheight.xml` | 636 | `a1c15af895d4f471…` |
| `res/layout-v33/glance_linear_progress_indicator_start_bottom.xml` | 636 | `c2c5e66ff4d2091f…` |
| `res/layout-v33/glance_linear_progress_indicator_start_center_vertical.xml` | 636 | `fdd800cf0e19ff6f…` |
| `res/layout-v33/glance_linear_progress_indicator_start_top.xml` | 636 | `126690c9f81386ab…` |
| `res/layout-v33/glance_linear_progress_indicator_wrapwidth_expandheight.xml` | 636 | `72fbc53cef3dce1d…` |
| `res/layout-v33/glance_list_center_horizontal_bottom.xml` | 748 | `2b775caa32681b5e…` |
| `res/layout-v33/glance_list_center_horizontal_center_vertical.xml` | 748 | `6217c4e787477739…` |
| `res/layout-v33/glance_list_center_horizontal_top.xml` | 748 | `2cac62c7a2482c65…` |
| `res/layout-v33/glance_list_end_bottom.xml` | 748 | `7d1478934c1f0928…` |
| `res/layout-v33/glance_list_end_center_vertical.xml` | 748 | `0722b8a91feaa773…` |
| `res/layout-v33/glance_list_end_top.xml` | 748 | `2d5db707d994bd32…` |
| `res/layout-v33/glance_list_expandwidth_wrapheight.xml` | 748 | `2cd41d099fd2b12d…` |
| `res/layout-v33/glance_list_start_bottom.xml` | 748 | `2beaccc3e353711b…` |
| `res/layout-v33/glance_list_start_center_vertical.xml` | 748 | `ccdf03a47958888e…` |
| `res/layout-v33/glance_list_start_top.xml` | 748 | `80ad7187e017894b…` |
| `res/layout-v33/glance_list_wrapwidth_expandheight.xml` | 748 | `47871d084d67acc2…` |
| `res/layout-v33/glance_radio_button_backport_center_horizontal_bottom.xml` | 1132 | `b569408aaadc99b1…` |
| `res/layout-v33/glance_radio_button_backport_center_horizontal_center_vertical.xml` | 1132 | `fb791a2b6d70fec0…` |
| `res/layout-v33/glance_radio_button_backport_center_horizontal_top.xml` | 1132 | `36237b91552e8b6e…` |
| `res/layout-v33/glance_radio_button_backport_end_bottom.xml` | 1132 | `9fa8a3d1a18761bd…` |
| `res/layout-v33/glance_radio_button_backport_end_center_vertical.xml` | 1132 | `aedc271ece491afc…` |
| `res/layout-v33/glance_radio_button_backport_end_top.xml` | 1132 | `6d87acd832f8e6c5…` |
| `res/layout-v33/glance_radio_button_backport_expandwidth_wrapheight.xml` | 1108 | `98322f75a0afadaf…` |
| `res/layout-v33/glance_radio_button_backport_start_bottom.xml` | 1132 | `03c083c2a64e123d…` |
| `res/layout-v33/glance_radio_button_backport_start_center_vertical.xml` | 1132 | `935a67e415023382…` |
| `res/layout-v33/glance_radio_button_backport_start_top.xml` | 1132 | `19478dc5c10b1cfd…` |
| `res/layout-v33/glance_radio_button_backport_wrapwidth_expandheight.xml` | 1108 | `3c6a8483d0ddcd2b…` |
| `res/layout-v33/glance_radio_button_center_horizontal_bottom.xml` | 740 | `8416c5fdb592e644…` |
| `res/layout-v33/glance_radio_button_center_horizontal_center_vertical.xml` | 740 | `f9791fdb195134d7…` |
| `res/layout-v33/glance_radio_button_center_horizontal_top.xml` | 740 | `fd93ef7d39e60201…` |
| `res/layout-v33/glance_radio_button_end_bottom.xml` | 740 | `0bcd34a28a25d9b2…` |
| `res/layout-v33/glance_radio_button_end_center_vertical.xml` | 740 | `1305f491abe91d68…` |
| `res/layout-v33/glance_radio_button_end_top.xml` | 740 | `4f3716639c584de3…` |
| `res/layout-v33/glance_radio_button_expandwidth_wrapheight.xml` | 740 | `372d69421efc9f85…` |
| `res/layout-v33/glance_radio_button_start_bottom.xml` | 740 | `7601974810dbaef4…` |
| `res/layout-v33/glance_radio_button_start_center_vertical.xml` | 740 | `1e7b500ac2f02976…` |
| `res/layout-v33/glance_radio_button_start_top.xml` | 740 | `ff40cbee0d7ff081…` |
| `res/layout-v33/glance_radio_button_wrapwidth_expandheight.xml` | 740 | `34e85af440146bfc…` |
| `res/layout-v33/glance_swtch_backport_center_horizontal_bottom.xml` | 1412 | `92dd1f5c0db26e68…` |
| `res/layout-v33/glance_swtch_backport_center_horizontal_center_vertical.xml` | 1412 | `b92112b9af9e6418…` |
| `res/layout-v33/glance_swtch_backport_center_horizontal_top.xml` | 1412 | `83a2864d876fc40b…` |
| `res/layout-v33/glance_swtch_backport_end_bottom.xml` | 1412 | `4671866e0aab10c5…` |
| `res/layout-v33/glance_swtch_backport_end_center_vertical.xml` | 1412 | `6333f77ce941cb51…` |
| `res/layout-v33/glance_swtch_backport_end_top.xml` | 1412 | `0e3d32dcc1efb412…` |
| `res/layout-v33/glance_swtch_backport_expandwidth_wrapheight.xml` | 1388 | `b2923a53149330a6…` |
| `res/layout-v33/glance_swtch_backport_start_bottom.xml` | 1412 | `f54d26f3d88ecbdd…` |
| `res/layout-v33/glance_swtch_backport_start_center_vertical.xml` | 1412 | `a9c1b485168e7a10…` |
| `res/layout-v33/glance_swtch_backport_start_top.xml` | 1412 | `a6837f92daf866cb…` |
| `res/layout-v33/glance_swtch_backport_wrapwidth_expandheight.xml` | 1388 | `ed09d12513425f04…` |
| `res/layout-v33/glance_swtch_center_horizontal_bottom.xml` | 736 | `98d5649dd6a41938…` |
| `res/layout-v33/glance_swtch_center_horizontal_center_vertical.xml` | 736 | `13384fc099368739…` |
| `res/layout-v33/glance_swtch_center_horizontal_top.xml` | 736 | `3a55b1439c000a16…` |
| `res/layout-v33/glance_swtch_end_bottom.xml` | 736 | `625773a1bfc75ac6…` |
| `res/layout-v33/glance_swtch_end_center_vertical.xml` | 736 | `0cab183613b0ac12…` |
| `res/layout-v33/glance_swtch_end_top.xml` | 736 | `ecf4be8aeec17369…` |
| `res/layout-v33/glance_swtch_expandwidth_wrapheight.xml` | 736 | `2ed29e23ef2fbec8…` |
| `res/layout-v33/glance_swtch_start_bottom.xml` | 736 | `0576e5f468c64f2b…` |
| `res/layout-v33/glance_swtch_start_center_vertical.xml` | 736 | `950d819511b94abe…` |
| `res/layout-v33/glance_swtch_start_top.xml` | 736 | `da607ba77daadf25…` |
| `res/layout-v33/glance_swtch_wrapwidth_expandheight.xml` | 736 | `d452d68ec9fecc8f…` |
| `res/layout-v33/glance_text_center_horizontal_bottom.xml` | 676 | `7467b97224d05e74…` |
| `res/layout-v33/glance_text_center_horizontal_center_vertical.xml` | 676 | `30a15e472a16b071…` |
| `res/layout-v33/glance_text_center_horizontal_top.xml` | 676 | `1ce0de33883c6bb3…` |
| `res/layout-v33/glance_text_end_bottom.xml` | 676 | `aa4eeb3d844d8702…` |
| `res/layout-v33/glance_text_end_center_vertical.xml` | 676 | `54e9e8acb31c7395…` |
| `res/layout-v33/glance_text_end_top.xml` | 676 | `309e34b12a290b72…` |
| `res/layout-v33/glance_text_expandwidth_wrapheight.xml` | 676 | `b77ddf547bbb5fc0…` |
| `res/layout-v33/glance_text_start_bottom.xml` | 676 | `ff72c284c209ac44…` |
| `res/layout-v33/glance_text_start_center_vertical.xml` | 676 | `614ba9b4304e66fd…` |
| `res/layout-v33/glance_text_start_top.xml` | 676 | `649fb6f745cfd035…` |
| `res/layout-v33/glance_text_wrapwidth_expandheight.xml` | 676 | `40c5e11659ed8735…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_center_horizontal_bottom.xml` | 792 | `d4374500b9a416ea…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_center_horizontal_center_vertical.xml` | 792 | `305d13320d17036a…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_center_horizontal_top.xml` | 792 | `93772c6f13b4c209…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_end_bottom.xml` | 792 | `23e28b3114ad04bf…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_end_center_vertical.xml` | 792 | `14fbf4c9476032f3…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_end_top.xml` | 792 | `335cc62682e72aca…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_expandwidth_wrapheight.xml` | 788 | `f48b1bf8ff1f84af…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_start_bottom.xml` | 792 | `6484fa1311e97107…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_start_center_vertical.xml` | 792 | `dc6e5b98bc339b05…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_start_top.xml` | 792 | `0c2b7c4d96577ac6…` |
| `res/layout-v33/glance_vertical_grid_auto_fit_wrapwidth_expandheight.xml` | 788 | `a3230394167eaaf2…` |
| `res/layout-v33/glance_vertical_grid_five_columns_center_horizontal_bottom.xml` | 792 | `a898e9c72be39957…` |
| `res/layout-v33/glance_vertical_grid_five_columns_center_horizontal_center_vertical.xml` | 792 | `9e283017fa945b9f…` |
| `res/layout-v33/glance_vertical_grid_five_columns_center_horizontal_top.xml` | 792 | `9a2f1351368bb698…` |
| `res/layout-v33/glance_vertical_grid_five_columns_end_bottom.xml` | 792 | `0ae641d3ba87f9d9…` |
| `res/layout-v33/glance_vertical_grid_five_columns_end_center_vertical.xml` | 792 | `69d51bd1bd275269…` |
| `res/layout-v33/glance_vertical_grid_five_columns_end_top.xml` | 792 | `41d345222762b7e8…` |
| `res/layout-v33/glance_vertical_grid_five_columns_expandwidth_wrapheight.xml` | 788 | `11ece69912303eea…` |
| `res/layout-v33/glance_vertical_grid_five_columns_start_bottom.xml` | 792 | `d4651314d7aebb3d…` |
| `res/layout-v33/glance_vertical_grid_five_columns_start_center_vertical.xml` | 792 | `621fb3086ecccd17…` |
| `res/layout-v33/glance_vertical_grid_five_columns_start_top.xml` | 792 | `8bc52c4193480231…` |
| `res/layout-v33/glance_vertical_grid_five_columns_wrapwidth_expandheight.xml` | 788 | `e4b7a7c8bf8a6c6e…` |
| `res/layout-v33/glance_vertical_grid_four_columns_center_horizontal_bottom.xml` | 792 | `389befafa593cbef…` |
| `res/layout-v33/glance_vertical_grid_four_columns_center_horizontal_center_vertical.xml` | 792 | `9fd884fdbeada207…` |
| `res/layout-v33/glance_vertical_grid_four_columns_center_horizontal_top.xml` | 792 | `e7315200800842cb…` |
| `res/layout-v33/glance_vertical_grid_four_columns_end_bottom.xml` | 792 | `bb2abeb4badbb9e1…` |
| `res/layout-v33/glance_vertical_grid_four_columns_end_center_vertical.xml` | 792 | `c0ae7ea370c1f29c…` |
| `res/layout-v33/glance_vertical_grid_four_columns_end_top.xml` | 792 | `7a1ed9eee5c363e2…` |
| `res/layout-v33/glance_vertical_grid_four_columns_expandwidth_wrapheight.xml` | 788 | `aac56bca16fc9797…` |
| `res/layout-v33/glance_vertical_grid_four_columns_start_bottom.xml` | 792 | `7483bdaabb4c84c5…` |
| `res/layout-v33/glance_vertical_grid_four_columns_start_center_vertical.xml` | 792 | `b9fb021408d013fa…` |
| `res/layout-v33/glance_vertical_grid_four_columns_start_top.xml` | 792 | `e54ffea8713b8162…` |
| `res/layout-v33/glance_vertical_grid_four_columns_wrapwidth_expandheight.xml` | 788 | `b97750e53aa24b41…` |
| `res/layout-v33/glance_vertical_grid_one_column_center_horizontal_bottom.xml` | 792 | `24b5f4b2d6cc7ed7…` |
| `res/layout-v33/glance_vertical_grid_one_column_center_horizontal_center_vertical.xml` | 792 | `409a0008ba85fb07…` |
| `res/layout-v33/glance_vertical_grid_one_column_center_horizontal_top.xml` | 792 | `aaac4feb4108af94…` |
| `res/layout-v33/glance_vertical_grid_one_column_end_bottom.xml` | 792 | `850886473b833406…` |
| `res/layout-v33/glance_vertical_grid_one_column_end_center_vertical.xml` | 792 | `ace0bb62b9d98143…` |
| `res/layout-v33/glance_vertical_grid_one_column_end_top.xml` | 792 | `2c131c292726f742…` |
| `res/layout-v33/glance_vertical_grid_one_column_expandwidth_wrapheight.xml` | 788 | `479c815e8f28ca72…` |
| `res/layout-v33/glance_vertical_grid_one_column_start_bottom.xml` | 792 | `341807440542f626…` |
| `res/layout-v33/glance_vertical_grid_one_column_start_center_vertical.xml` | 792 | `4716308dbe36db5e…` |
| `res/layout-v33/glance_vertical_grid_one_column_start_top.xml` | 792 | `30bbdb49843ad617…` |
| `res/layout-v33/glance_vertical_grid_one_column_wrapwidth_expandheight.xml` | 788 | `ca9972aff9ff1092…` |
| `res/layout-v33/glance_vertical_grid_three_columns_center_horizontal_bottom.xml` | 792 | `6bc473fc05001ffb…` |
| `res/layout-v33/glance_vertical_grid_three_columns_center_horizontal_center_vertical.xml` | 792 | `33967d06a49c0ed8…` |
| `res/layout-v33/glance_vertical_grid_three_columns_center_horizontal_top.xml` | 792 | `77c2afe0741af060…` |
| `res/layout-v33/glance_vertical_grid_three_columns_end_bottom.xml` | 792 | `2eb09ce88ee7819d…` |
| `res/layout-v33/glance_vertical_grid_three_columns_end_center_vertical.xml` | 792 | `6b97a1a6d6a965bc…` |
| `res/layout-v33/glance_vertical_grid_three_columns_end_top.xml` | 792 | `2875897a14ab03f6…` |
| `res/layout-v33/glance_vertical_grid_three_columns_expandwidth_wrapheight.xml` | 788 | `8148061fea04911d…` |
| `res/layout-v33/glance_vertical_grid_three_columns_start_bottom.xml` | 792 | `35bdb7df65234829…` |
| `res/layout-v33/glance_vertical_grid_three_columns_start_center_vertical.xml` | 792 | `e9f8bd6f94e0d12f…` |
| `res/layout-v33/glance_vertical_grid_three_columns_start_top.xml` | 792 | `db8bbc23a6f1472d…` |
| `res/layout-v33/glance_vertical_grid_three_columns_wrapwidth_expandheight.xml` | 788 | `dca59e2f089feeca…` |
| `res/layout-v33/glance_vertical_grid_two_columns_center_horizontal_bottom.xml` | 792 | `f3567ea8bbbc0ae7…` |
| `res/layout-v33/glance_vertical_grid_two_columns_center_horizontal_center_vertical.xml` | 792 | `b2beb04bea57b6b0…` |
| `res/layout-v33/glance_vertical_grid_two_columns_center_horizontal_top.xml` | 792 | `90a675f2ed2ea405…` |
| `res/layout-v33/glance_vertical_grid_two_columns_end_bottom.xml` | 792 | `6c9d17fe18f3fc86…` |
| `res/layout-v33/glance_vertical_grid_two_columns_end_center_vertical.xml` | 792 | `a4ceb22363890738…` |
| `res/layout-v33/glance_vertical_grid_two_columns_end_top.xml` | 792 | `e4714932d8f39e04…` |
| `res/layout-v33/glance_vertical_grid_two_columns_expandwidth_wrapheight.xml` | 788 | `1b7a10484c63f267…` |
| `res/layout-v33/glance_vertical_grid_two_columns_start_bottom.xml` | 792 | `595bf5f74b2434b5…` |
| `res/layout-v33/glance_vertical_grid_two_columns_start_center_vertical.xml` | 792 | `24da2cd2805297aa…` |
| `res/layout-v33/glance_vertical_grid_two_columns_start_top.xml` | 792 | `eacbb5f5d4ceeb1c…` |
| `res/layout-v33/glance_vertical_grid_two_columns_wrapwidth_expandheight.xml` | 788 | `7eed354f4a064c0b…` |
| `res/layout-v33/radio_column_center_horizontal_bottom.xml` | 520 | `1870de840c05a7b1…` |
| `res/layout-v33/radio_column_center_horizontal_center_vertical.xml` | 520 | `d72dc9938294af57…` |
| `res/layout-v33/radio_column_center_horizontal_top.xml` | 520 | `4b8744602d332053…` |
| `res/layout-v33/radio_column_end_bottom.xml` | 520 | `63bb9987ac4e33f8…` |
| `res/layout-v33/radio_column_end_center_vertical.xml` | 520 | `53fc139a6e1f8d4c…` |
| `res/layout-v33/radio_column_end_top.xml` | 520 | `5d3ad8ea429054a5…` |
| `res/layout-v33/radio_column_expandwidth_wrapheight.xml` | 516 | `6051e0e9ecce315a…` |
| `res/layout-v33/radio_column_start_bottom.xml` | 520 | `fbf1538a41eccb6d…` |
| `res/layout-v33/radio_column_start_center_vertical.xml` | 520 | `8a14fe9840b55efe…` |
| `res/layout-v33/radio_column_start_top.xml` | 520 | `3ac64794b3c617aa…` |
| `res/layout-v33/radio_column_wrapwidth_expandheight.xml` | 516 | `28b188d4123924ef…` |
| `res/layout-v33/radio_row_center_horizontal_bottom.xml` | 520 | `b2d49ce7c7c40117…` |
| `res/layout-v33/radio_row_center_horizontal_center_vertical.xml` | 520 | `af1dc07e39516c7b…` |
| `res/layout-v33/radio_row_center_horizontal_top.xml` | 520 | `497acc1fc610b611…` |
| `res/layout-v33/radio_row_end_bottom.xml` | 520 | `81cbc3ab3fc0b593…` |
| `res/layout-v33/radio_row_end_center_vertical.xml` | 520 | `ad52ae0b3fb1ede1…` |
| `res/layout-v33/radio_row_end_top.xml` | 520 | `a086e89e852a2177…` |
| `res/layout-v33/radio_row_expandwidth_wrapheight.xml` | 516 | `f48679bdbad2c93e…` |
| `res/layout-v33/radio_row_start_bottom.xml` | 520 | `93d11b608bfbe44a…` |
| `res/layout-v33/radio_row_start_center_vertical.xml` | 520 | `4ebd1775c4f4ec69…` |
| `res/layout-v33/radio_row_start_top.xml` | 520 | `c0bed396c8d9fd48…` |
| `res/layout-v33/radio_row_wrapwidth_expandheight.xml` | 516 | `a53957be89ce6bef…` |
| `res/layout-v33/row_center_horizontal_bottom.xml` | 520 | `33660c200f3cd664…` |
| `res/layout-v33/row_center_horizontal_center_vertical.xml` | 520 | `cdd83bc8838249be…` |
| `res/layout-v33/row_center_horizontal_top.xml` | 520 | `192bc7cd4f4c9dff…` |
| `res/layout-v33/row_end_bottom.xml` | 520 | `caf1711ab902092b…` |
| `res/layout-v33/row_end_center_vertical.xml` | 520 | `69897542e9454fc2…` |
| `res/layout-v33/row_end_top.xml` | 520 | `4d2759a3d9ba2068…` |
| `res/layout-v33/row_expandwidth_wrapheight.xml` | 520 | `15053be88a44fbeb…` |
| `res/layout-v33/row_start_bottom.xml` | 520 | `e05f728f33bd0b4b…` |
| `res/layout-v33/row_start_center_vertical.xml` | 520 | `587c4b73c87b48ad…` |
| `res/layout-v33/row_start_top.xml` | 520 | `0b08d2bda45cd556…` |
| `res/layout-v33/row_wrapwidth_expandheight.xml` | 520 | `d904eab74c23a636…` |
| `res/layout-watch-v20/abc_alert_dialog_button_bar_material.xml` | 1208 | `f59481cfaeb88bc6…` |
| `res/layout-watch-v20/abc_alert_dialog_title_material.xml` | 1352 | `2d7fe5deef122d35…` |
| `res/layout/abc_action_bar_title_item.xml` | 872 | `c40f9e41d591ea3a…` |
| `res/layout/abc_action_menu_item_layout.xml` | 768 | `506a9a451d07a7d7…` |
| `res/layout/abc_action_menu_layout.xml` | 576 | `79f0d17ffaf3291f…` |
| `res/layout/abc_action_mode_bar.xml` | 464 | `5ba5c9f934cb44f3…` |
| `res/layout/abc_action_mode_close_item_material.xml` | 840 | `b01bf23550078cd3…` |
| `res/layout/abc_alert_dialog_button_bar_material.xml` | 1584 | `11b5d675ad1f890d…` |
| `res/layout/abc_alert_dialog_material.xml` | 2648 | `396071fcda5940ed…` |
| `res/layout/abc_alert_dialog_title_material.xml` | 1560 | `942b640345cb36e0…` |
| `res/layout/abc_cascading_menu_item_layout.xml` | 1868 | `89441f97ca764190…` |
| `res/layout/abc_dialog_title_material.xml` | 1116 | `2826667b67956114…` |
| `res/layout/abc_expanded_menu_layout.xml` | 388 | `57f051d7d69d18e7…` |
| `res/layout/abc_list_menu_item_checkbox.xml` | 528 | `0d0da9df45554533…` |
| `res/layout/abc_list_menu_item_icon.xml` | 780 | `7541949cbe169f48…` |
| `res/layout/abc_list_menu_item_layout.xml` | 1396 | `ab76cb07bc60276c…` |
| `res/layout/abc_list_menu_item_radio.xml` | 532 | `a61901810c6ecd82…` |
| `res/layout/abc_popup_menu_header_item_layout.xml` | 848 | `9183eaf6937156d9…` |
| `res/layout/abc_popup_menu_item_layout.xml` | 2072 | `c510f7a6fb1fc383…` |
| `res/layout/abc_screen_content_include.xml` | 548 | `c26c8267a73c26db…` |
| `res/layout/abc_screen_simple.xml` | 832 | `6e0cf7b3fbd4a895…` |
| `res/layout/abc_screen_simple_overlay_action_mode.xml` | 792 | `74d1e5a717286092…` |
| `res/layout/abc_search_dropdown_item_icons_2line.xml` | 1916 | `772f73ed64c07789…` |
| `res/layout/abc_search_view.xml` | 3472 | `aa4d89c365a1b500…` |
| `res/layout/abc_select_dialog_material.xml` | 1020 | `e2e8dd30fc1fd687…` |
| `res/layout/acctivity_taxi_choose_address_sdk_taxi.xml` | 3932 | `14c0aa0a06a47111…` |
| `res/layout/acctivity_taxi_insurance.xml` | 8900 | `88fec046c42abcdb…` |
| `res/layout/acplugin_activity_error_page.xml` | 2368 | `4b67d7c6b79c3957…` |
| `res/layout/acplugin_layout_action_bar.xml` | 1124 | `38e9dd18e2bf4e2f…` |
| `res/layout/acplugin_layout_confirm_view.xml` | 1596 | `8109de43ffca58c0…` |
| `res/layout/acplugin_layout_cover_view.xml` | 724 | `b733d884d5472a34…` |
| `res/layout/acplugin_layout_dialog.xml` | 1720 | `a0be526a9b464861…` |
| `res/layout/acplugin_layout_loading_dialog.xml` | 1224 | `f216a0b9eac106cc…` |
| `res/layout/acplugin_layout_toast.xml` | 1092 | `06128801b8f89f71…` |
| `res/layout/acplugin_layout_verification_code_input_view.xml` | 1836 | `2f6d2af99d1b2fcc…` |
| `res/layout/activity_account_security_setting.xml` | 1252 | `fbebc272de31d02c…` |
| `res/layout/activity_balance.xml` | 972 | `b8b5cea979045485…` |
| `res/layout/activity_bank_binding.xml` | 1960 | `643f6c9cccda3c65…` |
| `res/layout/activity_bank_media_webview.xml` | 808 | `0c7ce5ad69116b13…` |
| `res/layout/activity_behavior_guideline.xml` | 2796 | `137ca97f70b1f5eb…` |
| `res/layout/activity_book_choose_address_sdk_taxi.xml` | 2960 | `c80d43cf528f7075…` |
| `res/layout/activity_book_confirm_address_sdk_taxi.xml` | 6136 | `c2ab9d1dfc074068…` |
| `res/layout/activity_cash_in.xml` | 1136 | `0e4988e1642b9f96…` |
| `res/layout/activity_cashier.xml` | 1656 | `2a4a1902e822eb09…` |
| `res/layout/activity_cashier_new_ui.xml` | 552 | `bb35327e59fba087…` |
| `res/layout/activity_cashier_v3.xml` | 1636 | `1655b79638863413…` |
| `res/layout/activity_cashier_web.xml` | 848 | `ebfc73fed4ec52db…` |
| `res/layout/activity_change_pin.xml` | 2188 | `4df9c536f83a4d56…` |
| `res/layout/activity_client_invoice_sdk_taxi.xml` | 7476 | `261ede94f3720ac7…` |
| `res/layout/activity_client_note_sdk_taxi.xml` | 4716 | `0fa8b024492c1664…` |
| `res/layout/activity_close_account.xml` | 1420 | `8f1ab85a8899b150…` |
| `res/layout/activity_common.xml` | 356 | `3e2e5e50afe2e249…` |
| `res/layout/activity_common_actionbar.xml` | 732 | `699e1fb7de4dfbb9…` |
| `res/layout/activity_common_actionbar_white.xml` | 880 | `ebc3d47a7fcc0cb2…` |
| `res/layout/activity_custom_default_camera.xml` | 1708 | `e059e0c963b0ddcf…` |
| `res/layout/activity_custom_user_profile_camera.xml` | 1708 | `abdbcf67d4043ea6…` |
| `res/layout/activity_ekyc_new.xml` | 2104 | `9c109394c95b9151…` |
| `res/layout/activity_fast_report_sdk_taxi.xml` | 5212 | `3d78c5111d921b35…` |
| `res/layout/activity_feature_module_routing.xml` | 1016 | `11e110fe50d6c70a…` |
| `res/layout/activity_fund_management.xml` | 1136 | `8cfa5bb4de02deed…` |
| `res/layout/activity_google_auth.xml` | 1428 | `02568bb230a95740…` |
| `res/layout/activity_home.xml` | 1788 | `dc99878b735ad79f…` |
| `res/layout/activity_home_more.xml` | 1012 | `b280d4d451a3be15…` |
| `res/layout/activity_home_new.xml` | 808 | `d8fd878d3b7269c0…` |
| `res/layout/activity_lock_account.xml` | 2676 | `f3c22cd9c7ac0ac8…` |
| `res/layout/activity_miniapp.xml` | 460 | `b66324844ffc4381…` |
| `res/layout/activity_no_webview_sdk.xml` | 460 | `2e8e74a4b5f053e1…` |
| `res/layout/activity_pdf_viewer.xml` | 1216 | `68db3316a59a7fe2…` |
| `res/layout/activity_reset_pin.xml` | 1360 | `f4282b703e2774ff…` |
| `res/layout/activity_result_page_v3.xml` | 1552 | `76bbbe2b3e832a82…` |
| `res/layout/activity_rp_new_ui.xml` | 552 | `274bc154f2a05664…` |
| `res/layout/activity_rp_web.xml` | 1744 | `b214d33c91cab911…` |
| `res/layout/activity_sdktaxi_address_book.xml` | 12640 | `c7394870c5d0b560…` |
| `res/layout/activity_sdktaxi_brand.xml` | 1412 | `69f4d18e98d66ddd…` |
| `res/layout/activity_sdktaxi_help_method.xml` | 1424 | `84d2cee4a4b72b65…` |
| `res/layout/activity_sdktaxi_history.xml` | 1820 | `9acc2ecc33c30cb8…` |
| `res/layout/activity_sdktaxi_maps.xml` | 5804 | `7053a5336427caf4…` |
| `res/layout/activity_sdktaxi_splash.xml` | 1016 | `146806c3f0a93241…` |
| `res/layout/activity_sdktaxi_voucher_list.xml` | 4308 | `8c24e1157454804e…` |
| `res/layout/activity_skewface.xml` | 1048 | `84ac0902b4719c49…` |
| `res/layout/activity_splash.xml` | 988 | `239776a1f064241f…` |
| `res/layout/activity_taxi_airport.xml` | 684 | `a1a5446be2f384ed…` |
| `res/layout/activity_taxi_detail_address.xml` | 5548 | `c4f2de50731fa58b…` |
| `res/layout/activity_taxi_select_address.xml` | 5588 | `603f88747ba7de48…` |
| `res/layout/activity_taxi_settings_address.xml` | 5216 | `51f0a0aa640414d4…` |
| `res/layout/activity_update_phone.xml` | 1340 | `e9c51c66d0908857…` |
| `res/layout/activity_voucher_detail_new_sdk_taxi.xml` | 5948 | `aa2a4fc679b16c4a…` |
| `res/layout/activity_withdraw_revamp.xml` | 1136 | `2f5e4d8c8ba5fbba…` |
| `res/layout/activty_detail_book_sdk_taxi.xml` | 12124 | `e021adfb977d27f0…` |
| `res/layout/activty_history_detail_sdk_taxi.xml` | 1192 | `13ac02e5c830a599…` |
| `res/layout/adapter_address_book.xml` | 2864 | `56b8ee5423afc444…` |
| `res/layout/adapter_address_book_sub.xml` | 1908 | `6b4972502ae9b262…` |
| `res/layout/adapter_address_book_sub_all.xml` | 1004 | `a3487b4850220cdb…` |
| `res/layout/adapter_history_sdk_taxi.xml` | 4904 | `7b12fe479b1b42aa…` |
| `res/layout/adapter_order_suggest_sdk_taxi.xml` | 2304 | `e7217613001e76de…` |
| `res/layout/adapter_recommend_search.xml` | 1180 | `4f9ef8afbf86f28d…` |
| `res/layout/adapter_taxi_function.xml` | 1860 | `0a0b6c17ecb932c2…` |
| `res/layout/adapter_taxi_promotion.xml` | 824 | `b26fec94186ff6b7…` |
| `res/layout/adapter_trip_suggest_sdk_taxi.xml` | 4308 | `79454e6d1e8bf630…` |
| `res/layout/admob_empty_layout.xml` | 396 | `bafb32676c4140f9…` |
| `res/layout/ariver_tabbar_item.xml` | 1596 | `5d004efe5713651b…` |
| `res/layout/ask_contact_explaination_layout.xml` | 2196 | `0894e00451ca5b2b…` |
| `res/layout/ask_contact_retry_layout.xml` | 2196 | `ff03480aefe59436…` |
| `res/layout/base_dialog_fragment_sdk_taxi.xml` | 624 | `6fc64eb02e5af9b2…` |
| `res/layout/bottom_sheet_option_dialog_fragment.xml` | 2352 | `13757115d7159285…` |
| `res/layout/bottom_sheet_pick_image.xml` | 2616 | `c2f1226ffd41a3df…` |
| `res/layout/bottom_sheet_scan_nfc.xml` | 3280 | `fc092034465dc850…` |
| `res/layout/bottom_sheet_share_qr_code_options.xml` | 4872 | `3ea83879a215adc9…` |
| `res/layout/bottom_sheet_webapp.xml` | 4356 | `4864f16757159f20…` |
| `res/layout/box_center_horizontal_bottom_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_center_horizontal_bottom_10children.xml` | 1180 | `e71061621c665e41…` |
| `res/layout/box_center_horizontal_bottom_1children.xml` | 460 | `3339c372c9055f08…` |
| `res/layout/box_center_horizontal_bottom_2children.xml` | 540 | `e6aee8b75e05f015…` |
| `res/layout/box_center_horizontal_bottom_3children.xml` | 620 | `bb3bda5c6817cc06…` |
| `res/layout/box_center_horizontal_bottom_4children.xml` | 700 | `008d9614b459c7cd…` |
| `res/layout/box_center_horizontal_bottom_5children.xml` | 780 | `91c1ebd906a88d71…` |
| `res/layout/box_center_horizontal_bottom_6children.xml` | 860 | `c77241fe87ffea1d…` |
| `res/layout/box_center_horizontal_bottom_7children.xml` | 940 | `68c6d2a3b2107cc0…` |
| `res/layout/box_center_horizontal_bottom_8children.xml` | 1020 | `25cc9a675531a9cb…` |
| `res/layout/box_center_horizontal_bottom_9children.xml` | 1100 | `77689af5b737807f…` |
| `res/layout/box_center_horizontal_center_vertical_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_center_horizontal_center_vertical_10children.xml` | 1180 | `8ce7cedf11c32085…` |
| `res/layout/box_center_horizontal_center_vertical_1children.xml` | 460 | `a98ecdf8847725c3…` |
| `res/layout/box_center_horizontal_center_vertical_2children.xml` | 540 | `819d90dcfd9352c0…` |
| `res/layout/box_center_horizontal_center_vertical_3children.xml` | 620 | `1f7af35516a69ee6…` |
| `res/layout/box_center_horizontal_center_vertical_4children.xml` | 700 | `d0cad699320fa193…` |
| `res/layout/box_center_horizontal_center_vertical_5children.xml` | 780 | `a12f4a6b9ad1450d…` |
| `res/layout/box_center_horizontal_center_vertical_6children.xml` | 860 | `26d554556c0e86d3…` |
| `res/layout/box_center_horizontal_center_vertical_7children.xml` | 940 | `0f213611ccc44273…` |
| `res/layout/box_center_horizontal_center_vertical_8children.xml` | 1020 | `5dfb30e489d7700e…` |
| `res/layout/box_center_horizontal_center_vertical_9children.xml` | 1100 | `f54f5cf212c95e12…` |
| `res/layout/box_center_horizontal_top_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_center_horizontal_top_10children.xml` | 1180 | `6eca880a9d51d821…` |
| `res/layout/box_center_horizontal_top_1children.xml` | 460 | `5e1e9825419e9b4b…` |
| `res/layout/box_center_horizontal_top_2children.xml` | 540 | `b4e2d42a73856e2d…` |
| `res/layout/box_center_horizontal_top_3children.xml` | 620 | `716e6fb3cca78dd7…` |
| `res/layout/box_center_horizontal_top_4children.xml` | 700 | `3b23e99efc445389…` |
| `res/layout/box_center_horizontal_top_5children.xml` | 780 | `e0f74e953fbba7d0…` |
| `res/layout/box_center_horizontal_top_6children.xml` | 860 | `79468c9d51ffceda…` |
| `res/layout/box_center_horizontal_top_7children.xml` | 940 | `d39a52f5a007327e…` |
| `res/layout/box_center_horizontal_top_8children.xml` | 1020 | `9222db6c4fc4d7b1…` |
| `res/layout/box_center_horizontal_top_9children.xml` | 1100 | `c42ba5dd9d0ef2da…` |
| `res/layout/box_child_center_horizontal_bottom_group_0.xml` | 1032 | `86e624eb110db12f…` |
| `res/layout/box_child_center_horizontal_bottom_group_1.xml` | 1032 | `6565b71d5324924d…` |
| `res/layout/box_child_center_horizontal_bottom_group_2.xml` | 1032 | `f0281254d7b7ba5d…` |
| `res/layout/box_child_center_horizontal_bottom_group_3.xml` | 1032 | `e620a1f2d1be7d8e…` |
| `res/layout/box_child_center_horizontal_bottom_group_4.xml` | 1032 | `2388510d9c79d889…` |
| `res/layout/box_child_center_horizontal_bottom_group_5.xml` | 1032 | `cfad763614dd9562…` |
| `res/layout/box_child_center_horizontal_bottom_group_6.xml` | 1032 | `704c67bc66e8d296…` |
| `res/layout/box_child_center_horizontal_bottom_group_7.xml` | 1032 | `b3e32155d04361bc…` |
| `res/layout/box_child_center_horizontal_bottom_group_8.xml` | 1032 | `5b09380a75967e7f…` |
| `res/layout/box_child_center_horizontal_bottom_group_9.xml` | 1032 | `1316b496145cc259…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_0.xml` | 1032 | `1b2e88d0357f3fa7…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_1.xml` | 1032 | `43d6a7c2dd5800ad…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_2.xml` | 1032 | `0d31546e640084ee…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_3.xml` | 1032 | `8adcce01dd9ee649…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_4.xml` | 1032 | `5eafc1c29376f898…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_5.xml` | 1032 | `e00d48437d13f00a…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_6.xml` | 1032 | `d51ba3c95539c2c9…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_7.xml` | 1032 | `d3527c1767dcfc80…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_8.xml` | 1032 | `d8a85e1f648bed5a…` |
| `res/layout/box_child_center_horizontal_center_vertical_group_9.xml` | 1032 | `56a0d48b23252bf8…` |
| `res/layout/box_child_center_horizontal_top_group_0.xml` | 1032 | `b928339be2bf3a88…` |
| `res/layout/box_child_center_horizontal_top_group_1.xml` | 1032 | `fdea87c69701545f…` |
| `res/layout/box_child_center_horizontal_top_group_2.xml` | 1032 | `1954395c21d66b80…` |
| `res/layout/box_child_center_horizontal_top_group_3.xml` | 1032 | `afb61ca91e0bd530…` |
| `res/layout/box_child_center_horizontal_top_group_4.xml` | 1032 | `6acf586f69d5c502…` |
| `res/layout/box_child_center_horizontal_top_group_5.xml` | 1032 | `00d58df1691925ec…` |
| `res/layout/box_child_center_horizontal_top_group_6.xml` | 1032 | `1fca6a29c9f332b5…` |
| `res/layout/box_child_center_horizontal_top_group_7.xml` | 1032 | `315ed1a9a3a6b1f6…` |
| `res/layout/box_child_center_horizontal_top_group_8.xml` | 1032 | `e901c2542c48fa07…` |
| `res/layout/box_child_center_horizontal_top_group_9.xml` | 1032 | `ce10ae8823f87bce…` |
| `res/layout/box_child_end_bottom_group_0.xml` | 1032 | `3f7efac867c6f5f7…` |
| `res/layout/box_child_end_bottom_group_1.xml` | 1032 | `4065b0b510337a7f…` |
| `res/layout/box_child_end_bottom_group_2.xml` | 1032 | `bc9c40b1a8d86458…` |
| `res/layout/box_child_end_bottom_group_3.xml` | 1032 | `613bf44a7547419a…` |
| `res/layout/box_child_end_bottom_group_4.xml` | 1032 | `b1fa6a773fb4f933…` |
| `res/layout/box_child_end_bottom_group_5.xml` | 1032 | `d653703a1f19d59b…` |
| `res/layout/box_child_end_bottom_group_6.xml` | 1032 | `3fce6ce2067a37ae…` |
| `res/layout/box_child_end_bottom_group_7.xml` | 1032 | `cf9e72a753546868…` |
| `res/layout/box_child_end_bottom_group_8.xml` | 1032 | `286ed5e1266350bb…` |
| `res/layout/box_child_end_bottom_group_9.xml` | 1032 | `38bd2732969e65f9…` |
| `res/layout/box_child_end_center_vertical_group_0.xml` | 1032 | `c66790403484f8c1…` |
| `res/layout/box_child_end_center_vertical_group_1.xml` | 1032 | `c33a12f5c35c1b80…` |
| `res/layout/box_child_end_center_vertical_group_2.xml` | 1032 | `a746d4383286b4f6…` |
| `res/layout/box_child_end_center_vertical_group_3.xml` | 1032 | `69e4bee2e3ea9203…` |
| `res/layout/box_child_end_center_vertical_group_4.xml` | 1032 | `b49ef7a2cdd4a0bc…` |
| `res/layout/box_child_end_center_vertical_group_5.xml` | 1032 | `01758f999fbdfbe6…` |
| `res/layout/box_child_end_center_vertical_group_6.xml` | 1032 | `6820642521e41032…` |
| `res/layout/box_child_end_center_vertical_group_7.xml` | 1032 | `cf83c5fd65e7e483…` |
| `res/layout/box_child_end_center_vertical_group_8.xml` | 1032 | `76efdc8008974581…` |
| `res/layout/box_child_end_center_vertical_group_9.xml` | 1032 | `752118d06a6ac896…` |
| `res/layout/box_child_end_top_group_0.xml` | 1032 | `eaa803e204f2cbcb…` |
| `res/layout/box_child_end_top_group_1.xml` | 1032 | `e2a3db3de68fcaca…` |
| `res/layout/box_child_end_top_group_2.xml` | 1032 | `0178cf70d78a844b…` |
| `res/layout/box_child_end_top_group_3.xml` | 1032 | `307437c651efc335…` |
| `res/layout/box_child_end_top_group_4.xml` | 1032 | `5fb464ca0e2697bb…` |
| `res/layout/box_child_end_top_group_5.xml` | 1032 | `478bdd6b3c09e035…` |
| `res/layout/box_child_end_top_group_6.xml` | 1032 | `06b76e57703095fb…` |
| `res/layout/box_child_end_top_group_7.xml` | 1032 | `e8bf1fa6d1026b23…` |
| `res/layout/box_child_end_top_group_8.xml` | 1032 | `484722f5083fccb9…` |
| `res/layout/box_child_end_top_group_9.xml` | 1032 | `e1c2640ee23426de…` |
| `res/layout/box_child_start_bottom_group_0.xml` | 1032 | `5bcbf8f6bde34e6d…` |
| `res/layout/box_child_start_bottom_group_1.xml` | 1032 | `3907e463387f6d44…` |
| `res/layout/box_child_start_bottom_group_2.xml` | 1032 | `6da62dddeb495bfd…` |
| `res/layout/box_child_start_bottom_group_3.xml` | 1032 | `052e584ab3444f6a…` |
| `res/layout/box_child_start_bottom_group_4.xml` | 1032 | `6f5f8933b0e82647…` |
| `res/layout/box_child_start_bottom_group_5.xml` | 1032 | `3e36f36d6ceecdf7…` |
| `res/layout/box_child_start_bottom_group_6.xml` | 1032 | `0234b4a95ac84bac…` |
| `res/layout/box_child_start_bottom_group_7.xml` | 1032 | `edad1def16d137b2…` |
| `res/layout/box_child_start_bottom_group_8.xml` | 1032 | `7fe0a3aa3967264c…` |
| `res/layout/box_child_start_bottom_group_9.xml` | 1032 | `96ed1b964fac24aa…` |
| `res/layout/box_child_start_center_vertical_group_0.xml` | 1032 | `adf0d282c3e54208…` |
| `res/layout/box_child_start_center_vertical_group_1.xml` | 1032 | `6ea6567c88cd198b…` |
| `res/layout/box_child_start_center_vertical_group_2.xml` | 1032 | `18b56e98b9693ef6…` |
| `res/layout/box_child_start_center_vertical_group_3.xml` | 1032 | `af36ace7f5ce148e…` |
| `res/layout/box_child_start_center_vertical_group_4.xml` | 1032 | `01dc0883cd3f0d9e…` |
| `res/layout/box_child_start_center_vertical_group_5.xml` | 1032 | `8a499ef93db411da…` |
| `res/layout/box_child_start_center_vertical_group_6.xml` | 1032 | `29d23800bf297215…` |
| `res/layout/box_child_start_center_vertical_group_7.xml` | 1032 | `6297a01c02688050…` |
| `res/layout/box_child_start_center_vertical_group_8.xml` | 1032 | `a09e73aa22ccb180…` |
| `res/layout/box_child_start_center_vertical_group_9.xml` | 1032 | `ff471f9d1d5f39a2…` |
| `res/layout/box_child_start_top_group_0.xml` | 1032 | `bd7ec0b02f4f75c9…` |
| `res/layout/box_child_start_top_group_1.xml` | 1032 | `69fbdd8704942711…` |
| `res/layout/box_child_start_top_group_2.xml` | 1032 | `deb4d0d41a2523eb…` |
| `res/layout/box_child_start_top_group_3.xml` | 1032 | `6171628952016b75…` |
| `res/layout/box_child_start_top_group_4.xml` | 1032 | `8a5b0ade5e2a8a8d…` |
| `res/layout/box_child_start_top_group_5.xml` | 1032 | `04b02c174b0d7cd2…` |
| `res/layout/box_child_start_top_group_6.xml` | 1032 | `721c7eb5deca11fd…` |
| `res/layout/box_child_start_top_group_7.xml` | 1032 | `94e4dcf7c2aab0d9…` |
| `res/layout/box_child_start_top_group_8.xml` | 1032 | `fa443205d0c6b30e…` |
| `res/layout/box_child_start_top_group_9.xml` | 1032 | `320f86a983ccaefb…` |
| `res/layout/box_end_bottom_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_end_bottom_10children.xml` | 1180 | `6347bcfe92bc8274…` |
| `res/layout/box_end_bottom_1children.xml` | 460 | `5ef95788f5f996e5…` |
| `res/layout/box_end_bottom_2children.xml` | 540 | `dd1cb79af0d79f0e…` |
| `res/layout/box_end_bottom_3children.xml` | 620 | `43d9d1d0d3e6e85e…` |
| `res/layout/box_end_bottom_4children.xml` | 700 | `0001d275f8757186…` |
| `res/layout/box_end_bottom_5children.xml` | 780 | `b896baf69cef3d39…` |
| `res/layout/box_end_bottom_6children.xml` | 860 | `429168ca545c0805…` |
| `res/layout/box_end_bottom_7children.xml` | 940 | `fba8ca877e927c4b…` |
| `res/layout/box_end_bottom_8children.xml` | 1020 | `665abb17689b9d0c…` |
| `res/layout/box_end_bottom_9children.xml` | 1100 | `8dd5f112a681f492…` |
| `res/layout/box_end_center_vertical_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_end_center_vertical_10children.xml` | 1180 | `7ed61aa6b600f6b2…` |
| `res/layout/box_end_center_vertical_1children.xml` | 460 | `4c2ca5cca7b803d5…` |
| `res/layout/box_end_center_vertical_2children.xml` | 540 | `fdce99a64811ce00…` |
| `res/layout/box_end_center_vertical_3children.xml` | 620 | `3e259103f92adb56…` |
| `res/layout/box_end_center_vertical_4children.xml` | 700 | `6eabe905ded61ed6…` |
| `res/layout/box_end_center_vertical_5children.xml` | 780 | `a693758948049dcf…` |
| `res/layout/box_end_center_vertical_6children.xml` | 860 | `572b64e54da86104…` |
| `res/layout/box_end_center_vertical_7children.xml` | 940 | `c813cfac8b81124a…` |
| `res/layout/box_end_center_vertical_8children.xml` | 1020 | `b0d256fcb6f02919…` |
| `res/layout/box_end_center_vertical_9children.xml` | 1100 | `434a116394f7cfdb…` |
| `res/layout/box_end_top_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_end_top_10children.xml` | 1180 | `e23ac3fc907ac3bf…` |
| `res/layout/box_end_top_1children.xml` | 460 | `3abf408516d0479f…` |
| `res/layout/box_end_top_2children.xml` | 540 | `6c041749a68383d3…` |
| `res/layout/box_end_top_3children.xml` | 620 | `6a3c60799d960abd…` |
| `res/layout/box_end_top_4children.xml` | 700 | `cdb850ef92a3d346…` |
| `res/layout/box_end_top_5children.xml` | 780 | `5ec187e937c2e366…` |
| `res/layout/box_end_top_6children.xml` | 860 | `0f54886098a2bc45…` |
| `res/layout/box_end_top_7children.xml` | 940 | `c1d2f6e0f43f4a6b…` |
| `res/layout/box_end_top_8children.xml` | 1020 | `8f8c51c4fb9e5fd3…` |
| `res/layout/box_end_top_9children.xml` | 1100 | `41a866867eb3d6aa…` |
| `res/layout/box_start_bottom_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_start_bottom_10children.xml` | 1180 | `d0da8d4920546fde…` |
| `res/layout/box_start_bottom_1children.xml` | 460 | `219af624d2917068…` |
| `res/layout/box_start_bottom_2children.xml` | 540 | `fcd04ed86d121e35…` |
| `res/layout/box_start_bottom_3children.xml` | 620 | `8c20dc6b6b5fd31b…` |
| `res/layout/box_start_bottom_4children.xml` | 700 | `59f0356ffe950e81…` |
| `res/layout/box_start_bottom_5children.xml` | 780 | `bce74f1071ded31d…` |
| `res/layout/box_start_bottom_6children.xml` | 860 | `b07f3821003c4bfb…` |
| `res/layout/box_start_bottom_7children.xml` | 940 | `789600b40099a0be…` |
| `res/layout/box_start_bottom_8children.xml` | 1020 | `24cd4ebcc3f17d49…` |
| `res/layout/box_start_bottom_9children.xml` | 1100 | `2d24fe7f01f326f8…` |
| `res/layout/box_start_center_vertical_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_start_center_vertical_10children.xml` | 1180 | `f5794bc64d14d22b…` |
| `res/layout/box_start_center_vertical_1children.xml` | 460 | `56b98f73be75d1de…` |
| `res/layout/box_start_center_vertical_2children.xml` | 540 | `c8b099f842d7824b…` |
| `res/layout/box_start_center_vertical_3children.xml` | 620 | `c6e9741758187b19…` |
| `res/layout/box_start_center_vertical_4children.xml` | 700 | `898bb1ab2dd72d72…` |
| `res/layout/box_start_center_vertical_5children.xml` | 780 | `f849101ce77e3a3a…` |
| `res/layout/box_start_center_vertical_6children.xml` | 860 | `39c0c47ce74d86df…` |
| `res/layout/box_start_center_vertical_7children.xml` | 940 | `931941577e95e04b…` |
| `res/layout/box_start_center_vertical_8children.xml` | 1020 | `51f3c2574b749bbd…` |
| `res/layout/box_start_center_vertical_9children.xml` | 1100 | `3d68368b987cfc95…` |
| `res/layout/box_start_top_0children.xml` | 352 | `70f13401be94af3f…` |
| `res/layout/box_start_top_10children.xml` | 1180 | `58842b07eb145f48…` |
| `res/layout/box_start_top_1children.xml` | 460 | `25e8cc28c875f3db…` |
| `res/layout/box_start_top_2children.xml` | 540 | `b40604e81f5f63b1…` |
| `res/layout/box_start_top_3children.xml` | 620 | `a9984dfa89302784…` |
| `res/layout/box_start_top_4children.xml` | 700 | `60001bc86d3e0568…` |
| `res/layout/box_start_top_5children.xml` | 780 | `821c70d816265e67…` |
| `res/layout/box_start_top_6children.xml` | 860 | `c52d06fa1171d4f6…` |
| `res/layout/box_start_top_7children.xml` | 940 | `7ef4f5e64f70ef2e…` |
| `res/layout/box_start_top_8children.xml` | 1020 | `6665e901874eb8d7…` |
| `res/layout/box_start_top_9children.xml` | 1100 | `dbc028c6f6d6f0ad…` |
| `res/layout/cancel_booking_dialog_fragment_sdk_taxi.xml` | 3428 | `34ad23497dc5c8af…` |
| `res/layout/cancel_booking_recyclerview_item_sdk_taxi.xml` | 1248 | `3635b0eb3c5e3fa7…` |
| `res/layout/cashier_bill_info_item.xml` | 1192 | `4c08b41a9f173aad…` |
| `res/layout/center_horizontal_edittext.xml` | 2200 | `28da9b731287066c…` |
| `res/layout/close_account_ask_last_layout.xml` | 3828 | `cfc65ec7c234a9ee…` |
| `res/layout/close_account_confirmation_layout.xml` | 3140 | `253d961a57cc473c…` |
| `res/layout/close_account_result_layout.xml` | 2388 | `535c11a695aaefcf…` |
| `res/layout/column_center_horizontal_null_0children.xml` | 396 | `b235193dc2c23a59…` |
| `res/layout/column_center_horizontal_null_10children.xml` | 1224 | `52a53f4571b2f792…` |
| `res/layout/column_center_horizontal_null_1children.xml` | 504 | `8652646108217639…` |
| `res/layout/column_center_horizontal_null_2children.xml` | 584 | `57088e475d041657…` |
| `res/layout/column_center_horizontal_null_3children.xml` | 664 | `becbdb60016f1e85…` |
| `res/layout/column_center_horizontal_null_4children.xml` | 744 | `27290c8dc25f9066…` |
| `res/layout/column_center_horizontal_null_5children.xml` | 824 | `6342eb8a561524ca…` |
| `res/layout/column_center_horizontal_null_6children.xml` | 904 | `6685be3a9634be16…` |
| `res/layout/column_center_horizontal_null_7children.xml` | 984 | `c642c70d7a21b916…` |
| `res/layout/column_center_horizontal_null_8children.xml` | 1064 | `a2f1444f2fed2b5c…` |
| `res/layout/column_center_horizontal_null_9children.xml` | 1144 | `7901bf3c9047a9c0…` |
| `res/layout/column_child_center_horizontal_null_group_0.xml` | 1472 | `26ceab09498d36ff…` |
| `res/layout/column_child_center_horizontal_null_group_1.xml` | 1472 | `609f1a2150fb4783…` |
| `res/layout/column_child_center_horizontal_null_group_2.xml` | 1472 | `96b818fcdb9ed27b…` |
| `res/layout/column_child_center_horizontal_null_group_3.xml` | 1472 | `287d54fe242ac23f…` |
| `res/layout/column_child_center_horizontal_null_group_4.xml` | 1472 | `65cc2f25d23fa35e…` |
| `res/layout/column_child_center_horizontal_null_group_5.xml` | 1472 | `3af48f297d8d42cc…` |
| `res/layout/column_child_center_horizontal_null_group_6.xml` | 1472 | `6f342a9154f67061…` |
| `res/layout/column_child_center_horizontal_null_group_7.xml` | 1472 | `6b9cd5fb7d8868cf…` |
| `res/layout/column_child_center_horizontal_null_group_8.xml` | 1472 | `c1c9115dc292c836…` |
| `res/layout/column_child_center_horizontal_null_group_9.xml` | 1472 | `0a93f267d1571c8b…` |
| `res/layout/column_child_end_null_group_0.xml` | 1472 | `2bdb7fda0bfb766d…` |
| `res/layout/column_child_end_null_group_1.xml` | 1472 | `9298b8b517af32aa…` |
| `res/layout/column_child_end_null_group_2.xml` | 1472 | `8089bfa80c390e56…` |
| `res/layout/column_child_end_null_group_3.xml` | 1472 | `92d5a6a2afba7515…` |
| `res/layout/column_child_end_null_group_4.xml` | 1472 | `33f831647f5a321f…` |
| `res/layout/column_child_end_null_group_5.xml` | 1472 | `e5c89d089e7d2b9d…` |
| `res/layout/column_child_end_null_group_6.xml` | 1472 | `13cf047ba0cb6e55…` |
| `res/layout/column_child_end_null_group_7.xml` | 1472 | `755aedc2485a331f…` |
| `res/layout/column_child_end_null_group_8.xml` | 1472 | `8c3f794e2c43a09d…` |
| `res/layout/column_child_end_null_group_9.xml` | 1472 | `473dee620a7cd600…` |
| `res/layout/column_child_start_null_group_0.xml` | 1472 | `f15e60446216235e…` |
| `res/layout/column_child_start_null_group_1.xml` | 1472 | `94ecb681589727a9…` |
| `res/layout/column_child_start_null_group_2.xml` | 1472 | `756dd82b5eb0f419…` |
| `res/layout/column_child_start_null_group_3.xml` | 1472 | `221051f6e8d80601…` |
| `res/layout/column_child_start_null_group_4.xml` | 1472 | `9afd816aa43b5eb2…` |
| `res/layout/column_child_start_null_group_5.xml` | 1472 | `372519117c5f8048…` |
| `res/layout/column_child_start_null_group_6.xml` | 1472 | `09d5087011745e68…` |
| `res/layout/column_child_start_null_group_7.xml` | 1472 | `63071c23d44b26fe…` |
| `res/layout/column_child_start_null_group_8.xml` | 1472 | `16b264941f5b54a4…` |
| `res/layout/column_child_start_null_group_9.xml` | 1472 | `5da116da9b144f73…` |
| `res/layout/column_end_null_0children.xml` | 396 | `b235193dc2c23a59…` |
| `res/layout/column_end_null_10children.xml` | 1224 | `f5816f9683d10451…` |
| `res/layout/column_end_null_1children.xml` | 504 | `e6467d78195cb647…` |
| `res/layout/column_end_null_2children.xml` | 584 | `5b92b3b2400c2b14…` |
| `res/layout/column_end_null_3children.xml` | 664 | `96246a4139c60f84…` |
| `res/layout/column_end_null_4children.xml` | 744 | `bc85234be7bfcb78…` |
| `res/layout/column_end_null_5children.xml` | 824 | `abc7421ab3852371…` |
| `res/layout/column_end_null_6children.xml` | 904 | `0c7069c23704fbc7…` |
| `res/layout/column_end_null_7children.xml` | 984 | `9b9e8f4c4c731d05…` |
| `res/layout/column_end_null_8children.xml` | 1064 | `99e5eb4aa1ae9167…` |
| `res/layout/column_end_null_9children.xml` | 1144 | `1adeebbb759512ce…` |
| `res/layout/column_start_null_0children.xml` | 396 | `b235193dc2c23a59…` |
| `res/layout/column_start_null_10children.xml` | 1224 | `b21b9ea852e706a3…` |
| `res/layout/column_start_null_1children.xml` | 504 | `d000098990cebfd4…` |
| `res/layout/column_start_null_2children.xml` | 584 | `aad3b8686dce1869…` |
| `res/layout/column_start_null_3children.xml` | 664 | `2de80318bbda00cd…` |
| `res/layout/column_start_null_4children.xml` | 744 | `149da6a9d8d6adac…` |
| `res/layout/column_start_null_5children.xml` | 824 | `0d97c396b2648945…` |
| `res/layout/column_start_null_6children.xml` | 904 | `0e4fa21e30895474…` |
| `res/layout/column_start_null_7children.xml` | 984 | `a8a0d53e4b388986…` |
| `res/layout/column_start_null_8children.xml` | 1064 | `d81b802492749985…` |
| `res/layout/column_start_null_9children.xml` | 1144 | `feca18a37d9ecd09…` |
| `res/layout/com_facebook_activity_layout.xml` | 356 | `d3a67d3030e11a8a…` |
| `res/layout/com_facebook_device_auth_dialog_fragment.xml` | 3384 | `7a471f31d0718609…` |
| `res/layout/com_facebook_login_fragment.xml` | 660 | `fae7968f93d27557…` |
| `res/layout/com_facebook_smart_device_dialog_fragment.xml` | 3704 | `b0c4748372d5ae76…` |
| `res/layout/complex_expand_expand.xml` | 596 | `7efda3f2c85b1027…` |
| `res/layout/complex_expand_fixed.xml` | 992 | `f4b61ddd48f8d4ba…` |
| `res/layout/complex_expand_match.xml` | 596 | `c9c8eb3c959adc5c…` |
| `res/layout/complex_expand_wrap.xml` | 596 | `b30f6e9cdf2bbb0c…` |
| `res/layout/complex_fixed_expand.xml` | 992 | `dc62e954a543e3e8…` |
| `res/layout/complex_fixed_fixed.xml` | 1040 | `a7a7b9264cedbbdc…` |
| `res/layout/complex_fixed_match.xml` | 948 | `dfce32b52fa3e101…` |
| `res/layout/complex_fixed_wrap.xml` | 948 | `a9baa232a20b480e…` |
| `res/layout/complex_match_expand.xml` | 596 | `a03b423b4caa7cd0…` |
| `res/layout/complex_match_fixed.xml` | 948 | `a3b78f0afe1601d5…` |
| `res/layout/complex_match_match.xml` | 552 | `6e1e102ae8ce61fe…` |
| `res/layout/complex_match_wrap.xml` | 552 | `b1289157de9e2136…` |
| `res/layout/complex_wrap_expand.xml` | 596 | `9df217952587f90c…` |
| `res/layout/complex_wrap_fixed.xml` | 948 | `e77f1475e9bece22…` |
| `res/layout/complex_wrap_match.xml` | 552 | `254819d72ad31534…` |
| `res/layout/complex_wrap_wrap.xml` | 552 | `b2ffacbd3d1bd0a5…` |
| `res/layout/create_payment_dialog_fragment.xml` | 2312 | `37d32ad3264af215…` |
| `res/layout/current_trip_detail_dialog.xml` | 5412 | `99a956655515928e…` |
| `res/layout/custom_marker_on_map_more_destination_sdk_taxi.xml` | 2324 | `bb10285838d9c5fd…` |
| `res/layout/custom_marker_on_map_sdk_taxi.xml` | 1988 | `a97789f98c0cd765…` |
| `res/layout/custom_marker_start_on_map_sdk_taxi.xml` | 1844 | `2142ff670345ca3c…` |
| `res/layout/custom_near_address_marker_on_map_sdk_taxi.xml` | 868 | `56779993acda5e15…` |
| `res/layout/custom_photo_action_dialog.xml` | 1180 | `67c1037f9cbddd00…` |
| `res/layout/custom_toast.xml` | 1164 | `ddb932e2c4b28895…` |
| `res/layout/custom_toast_blue_background_with_tick_icon_layout.xml` | 1056 | `6878c95d5c05daa0…` |
| `res/layout/custom_toast_login.xml` | 1268 | `d22f8d36555ec90a…` |
| `res/layout/custom_toast_no_icon.xml` | 1040 | `f103f0d4c2a945e9…` |
| `res/layout/custom_toast_success_state.xml` | 1100 | `c2223b0f1a8b3100…` |
| `res/layout/customer_service_layout.xml` | 2560 | `f79f0d1def794732…` |
| `res/layout/default_person_qr_app_widget.xml` | 504 | `05cb56338f77701d…` |
| `res/layout/default_standard_app_widget.xml` | 504 | `39a90d01382d9fdc…` |
| `res/layout/design_bottom_sheet_dialog.xml` | 1224 | `4e88489db74d4038…` |
| `res/layout/design_layout_snackbar.xml` | 528 | `f57f25d9fe9051d6…` |
| `res/layout/design_layout_snackbar_include.xml` | 1444 | `33af1ea860813f54…` |
| `res/layout/design_layout_tab_icon.xml` | 408 | `ae23fba6ef2b4fce…` |
| `res/layout/design_layout_tab_text.xml` | 436 | `ddd8236fca596f63…` |
| `res/layout/design_menu_item_action_area.xml` | 320 | `6347517f1ba86fda…` |
| `res/layout/design_navigation_menu_item.xml` | 856 | `5c5d017fe52dad00…` |
| `res/layout/design_text_input_end_icon.xml` | 616 | `9376721cfe7e1dad…` |
| `res/layout/design_text_input_start_icon.xml` | 612 | `240f39433770d5da…` |
| `res/layout/dialog__sweet.xml` | 7136 | `3712eed4065ac481…` |
| `res/layout/dialog_car_type_info_sdk_taxi.xml` | 1772 | `ccc06ed208235025…` |
| `res/layout/dialog_forward_merchant_sdk_taxi.xml` | 2812 | `f35842cf052854b7…` |
| `res/layout/dialog_fragment_open_bank_account.xml` | 3124 | `29a19a82d999db0c…` |
| `res/layout/dialog_fragment_topup_info.xml` | 1664 | `16e65ea4c6f3f5e0…` |
| `res/layout/dialog_loading_sdk_taxi.xml` | 612 | `928d5655a5896e70…` |
| `res/layout/dialog_not_my_account.xml` | 2556 | `d1bb0fc0a861c3a4…` |
| `res/layout/dialog_price_detail_sdk_taxi.xml` | 4648 | `9defe4efe425bce2…` |
| `res/layout/dialog_rating_sdk_taxi.xml` | 3624 | `a117bef075c4ffd5…` |
| `res/layout/dialog_rating_success_sdk_taxi.xml` | 3656 | `b2a9219d1e32c933…` |
| `res/layout/dialog_success_find_driver_sdk_taxi.xml` | 8212 | `b924dee9903502d9…` |
| `res/layout/dialog_success_rating_sdk_taxi.xml` | 1388 | `8a7d701f8cc92333…` |
| `res/layout/dialog_success_report.xml` | 2156 | `66291e46b54d4da4…` |
| `res/layout/document_fragment_new.xml` | 4176 | `f3003a179fabb391…` |
| `res/layout/dynamic_feature_install_fragment.xml` | 1396 | `039252f936ff1a0a…` |
| `res/layout/exo_list_divider.xml` | 356 | `4d24ba93dd87def7…` |
| `res/layout/exo_player_control_view.xml` | 4056 | `89dc59ce6c5f39e4…` |
| `res/layout/exo_player_view.xml` | 2308 | `d47a565c7becc70f…` |
| `res/layout/exo_styled_settings_list.xml` | 468 | `d26a4177e55938f9…` |
| `res/layout/exo_styled_settings_list_item.xml` | 1584 | `0f427a7aee09a9b0…` |
| `res/layout/exo_styled_sub_settings_list_item.xml` | 1280 | `4d132f65868d9276…` |
| `res/layout/expandable_layout_child.xml` | 404 | `4754990c0d8e1742…` |
| `res/layout/expandable_layout_frame.xml` | 964 | `89d45402f3c18dc3…` |
| `res/layout/face_liveness_camerax_activity.xml` | 1184 | `743e0d2928982d39…` |
| `res/layout/face_liveness_camerax_fragment.xml` | 3200 | `9b46f14fa4aff14f…` |
| `res/layout/fingerprint_dialog_container.xml` | 1064 | `ab3dccb6a8aa6542…` |
| `res/layout/fingerprint_dialog_decrypt_content.xml` | 1280 | `07fa5d4b5836a196…` |
| `res/layout/fingerprint_dialog_input_action.xml` | 1212 | `e86cae68e3f865ea…` |
| `res/layout/fingerprint_dialog_layout.xml` | 1964 | `4e14b3ef0357a408…` |
| `res/layout/force_switch_account_layout.xml` | 2444 | `3c9f656d7d30ba06…` |
| `res/layout/fragment_authenticate_info.xml` | 1620 | `3a65c951c0168b28…` |
| `res/layout/fragment_authenticate_info_failure.xml` | 1844 | `be447f2b8caf5641…` |
| `res/layout/fragment_balance.xml` | 3696 | `97ab1edac1acc26c…` |
| `res/layout/fragment_bank_account_registration.xml` | 1412 | `1295ce45e3c27868…` |
| `res/layout/fragment_bank_binding_tab.xml` | 1176 | `b7b02a1b8405357e…` |
| `res/layout/fragment_bank_bottom_dialog.xml` | 3188 | `99177b5592f6f978…` |
| `res/layout/fragment_bank_center_dialog.xml` | 3128 | `9ced376322b58ebe…` |
| `res/layout/fragment_bank_confirm_bottom_dialog.xml` | 2544 | `ea2564930e4d52e6…` |
| `res/layout/fragment_bank_detail_form.xml` | 2440 | `a71f0897dead73be…` |
| `res/layout/fragment_bank_loading.xml` | 1444 | `65d7d56682bf61ef…` |
| `res/layout/fragment_bank_number_form.xml` | 2820 | `3e80622d860c69b8…` |
| `res/layout/fragment_bank_otp.xml` | 4204 | `d123b167531348b1…` |
| `res/layout/fragment_bank_rop.xml` | 4240 | `80bed65628a62387…` |
| `res/layout/fragment_bank_rop_web_app.xml` | 636 | `5d3ad866f4cd17ad…` |
| `res/layout/fragment_bank_success.xml` | 3816 | `429c4d722e3fb834…` |
| `res/layout/fragment_bank_support_list.xml` | 2136 | `fdc5047fe619a591…` |
| `res/layout/fragment_bank_waiting_approval_kyc.xml` | 1996 | `d2ab12680cec21ec…` |
| `res/layout/fragment_bank_web_app.xml` | 636 | `6a9103dcb14d29dd…` |
| `res/layout/fragment_card_on_file.xml` | 1780 | `db6cd45ff5ca987c…` |
| `res/layout/fragment_choose_ekyc.xml` | 3388 | `cdae011b269cc82c…` |
| `res/layout/fragment_clearing_account.xml` | 1428 | `fbeeb273d108c54b…` |
| `res/layout/fragment_confirm_reset_pin_dialog.xml` | 2688 | `f0e4e4d059238813…` |
| `res/layout/fragment_create_password.xml` | 2280 | `9bbd1e19ee99b90a…` |
| `res/layout/fragment_current_taxi_booking.xml` | 5784 | `24aef2d9aa9e7395…` |
| `res/layout/fragment_dialog_bank_card_guide.xml` | 2796 | `9f30262cf2284aad…` |
| `res/layout/fragment_dialog_bank_date_picker.xml` | 3384 | `689399da9e049947…` |
| `res/layout/fragment_dialog_bank_guide_find_time.xml` | 2524 | `3a62c819978694a1…` |
| `res/layout/fragment_dialog_bank_id_type.xml` | 1228 | `04b960422350b9d0…` |
| `res/layout/fragment_dialog_cvv_guide.xml` | 2256 | `2530108e98f0d57d…` |
| `res/layout/fragment_dialog_otp_feedback.xml` | 2732 | `28ede00d83687575…` |
| `res/layout/fragment_dialog_toro.xml` | 2860 | `b5185150cc1b834d…` |
| `res/layout/fragment_download_progress.xml` | 3436 | `a4515ebc45e2ecae…` |
| `res/layout/fragment_duplicate_order.xml` | 2168 | `be22ec0cb0b53517…` |
| `res/layout/fragment_find_driver_booking_sdk_taxi.xml` | 6668 | `021fb69bd82df300…` |
| `res/layout/fragment_force_update_phone.xml` | 2660 | `3813d39f87dd295e…` |
| `res/layout/fragment_force_upgrade_two_steps.xml` | 6444 | `7fa5020818466114…` |
| `res/layout/fragment_general_webapp.xml` | 1456 | `d75e358fea08d439…` |
| `res/layout/fragment_internet_banking_landing_page.xml` | 2632 | `2b3567eaecf03a01…` |
| `res/layout/fragment_loading_payment_webview.xml` | 1768 | `9a32c614f5055cd5…` |
| `res/layout/fragment_lock_screen.xml` | 3192 | `1bb7523bcd9f3f01…` |
| `res/layout/fragment_login_devices.xml` | 1140 | `ca907c170a8ee897…` |
| `res/layout/fragment_media_content_bottom_popup.xml` | 2988 | `aa9780aa64bcfda9…` |
| `res/layout/fragment_new_pin.xml` | 1212 | `5c329f14c2a14e2a…` |
| `res/layout/fragment_old_pin.xml` | 2840 | `88f0807022e705b1…` |
| `res/layout/fragment_onboarding_otp.xml` | 3200 | `059fdb69165b4fb5…` |
| `res/layout/fragment_otp_pin.xml` | 900 | `61fdf87228ce86d7…` |
| `res/layout/fragment_payment_webview.xml` | 872 | `78c759c08c793323…` |
| `res/layout/fragment_popup_bank_web.xml` | 2612 | `4872d886efb7a1ba…` |
| `res/layout/fragment_popup_info.xml` | 2424 | `5f4b38a2ce2015de…` |
| `res/layout/fragment_popup_promotion_detail.xml` | 2600 | `56b092fd995c084b…` |
| `res/layout/fragment_popup_result_page.xml` | 1160 | `4a4bd44ecb4362c9…` |
| `res/layout/fragment_promotion_v3.xml` | 1780 | `40d381c1d7bfd52e…` |
| `res/layout/fragment_protect_account.xml` | 10396 | `1e4424f5f8cf56f2…` |
| `res/layout/fragment_qr_code.xml` | 2352 | `b26a8e349e4cefee…` |
| `res/layout/fragment_reform_kyc_result.xml` | 2352 | `dce42c63b78e955e…` |
| `res/layout/fragment_reset_pin_otp.xml` | 3500 | `323f814017d84f82…` |
| `res/layout/fragment_reset_pin_pending.xml` | 2684 | `95237a5a74e77aec…` |
| `res/layout/fragment_result_page_webapp.xml` | 748 | `13ed4b98ef2e0b53…` |
| `res/layout/fragment_rp_bottomsheet_webview_v3.xml` | 1752 | `23dc71897e50622d…` |
| `res/layout/fragment_rp_popup_webview.xml` | 1752 | `18cd476018f2baba…` |
| `res/layout/fragment_scan_ekyc.xml` | 5268 | `968e947fec21ad92…` |
| `res/layout/fragment_set_amount.xml` | 3888 | `9d2d8a96dfbf4d16…` |
| `res/layout/fragment_support_bank_empty.xml` | 1652 | `5026bb6e18489d4e…` |
| `res/layout/fragment_taxi_booking.xml` | 3064 | `334a12b080bba69c…` |
| `res/layout/fragment_topup_loading.xml` | 1444 | `39211bfd23b5b039…` |
| `res/layout/fragment_tutorial_connect_internet.xml` | 2272 | `47933a564e29d8fb…` |
| `res/layout/fragment_upgrade_level_suggest.xml` | 3328 | `b06ce2fd4e1daee4…` |
| `res/layout/glance_button.xml` | 676 | `2651f8604af11e05…` |
| `res/layout/glance_check_box.xml` | 740 | `f2b16c6bc0332698…` |
| `res/layout/glance_check_box_backport.xml` | 1088 | `18046b1fb690de2d…` |
| `res/layout/glance_check_box_image.xml` | 436 | `8d418154c0abd667…` |
| `res/layout/glance_check_box_text.xml` | 396 | `da181b49402d5c92…` |
| `res/layout/glance_check_box_view.xml` | 456 | `3b3bd0015b6e7f5f…` |
| `res/layout/glance_circular_progress_indicator.xml` | 512 | `c82217457e7affe2…` |
| `res/layout/glance_default_loading_layout.xml` | 560 | `c7642c6c37b608eb…` |
| `res/layout/glance_deleted_view.xml` | 396 | `a7200a4ba8a37550…` |
| `res/layout/glance_error_layout.xml` | 828 | `6ecfc2deb25b805d…` |
| `res/layout/glance_frame.xml` | 480 | `a77ea1e2905fcbc4…` |
| `res/layout/glance_image_crop.xml` | 516 | `f5d3a46116b5884e…` |
| `res/layout/glance_image_crop_decorative.xml` | 572 | `ab19b0953eaaa697…` |
| `res/layout/glance_image_fill_bounds.xml` | 516 | `47d054a5629f37d2…` |
| `res/layout/glance_image_fill_bounds_decorative.xml` | 572 | `a289c1c0dbb169df…` |
| `res/layout/glance_image_fit.xml` | 516 | `f75962845a265498…` |
| `res/layout/glance_image_fit_decorative.xml` | 572 | `232a36c4af6b5e35…` |
| `res/layout/glance_invalid_list_item.xml` | 388 | `ee3d08e6bba92110…` |
| `res/layout/glance_linear_progress_indicator.xml` | 512 | `f2fe9b578c56f213…` |
| `res/layout/glance_list.xml` | 624 | `7929f146dbd9229a…` |
| `res/layout/glance_radio_button.xml` | 616 | `f664c3a8f6ca5419…` |
| `res/layout/glance_radio_button_backport.xml` | 1024 | `813b94e2a19d6c55…` |
| `res/layout/glance_radio_icon.xml` | 436 | `f7b49d27740b9b3a…` |
| `res/layout/glance_radio_text.xml` | 396 | `0dbe85b0754a5d02…` |
| `res/layout/glance_switch_text.xml` | 440 | `ea59e270c55e1631…` |
| `res/layout/glance_switch_thumb.xml` | 432 | `4e23500d867d604c…` |
| `res/layout/glance_switch_track.xml` | 384 | `1085e5db59c7a3af…` |
| `res/layout/glance_swtch.xml` | 612 | `870d699a8846d120…` |
| `res/layout/glance_swtch_backport.xml` | 1300 | `2ecf43ad70b17f65…` |
| `res/layout/glance_text.xml` | 552 | `941e3e0478e72db1…` |
| `res/layout/glance_vertical_grid_auto_fit.xml` | 668 | `e852e6edab69bef2…` |
| `res/layout/glance_vertical_grid_five_columns.xml` | 668 | `8f8117110479e830…` |
| `res/layout/glance_vertical_grid_four_columns.xml` | 668 | `22e2861d41db2b72…` |
| `res/layout/glance_vertical_grid_one_column.xml` | 668 | `31b1920cccdaeb45…` |
| `res/layout/glance_vertical_grid_three_columns.xml` | 668 | `8306f6d1fc770caa…` |
| `res/layout/glance_vertical_grid_two_columns.xml` | 668 | `280021ec5c1eae5b…` |
| `res/layout/griver_bottom_popup_window.xml` | 748 | `2792837d7f8c58d8…` |
| `res/layout/griver_core_dialog_actionsheet.xml` | 1064 | `c02982aba0525bb7…` |
| `res/layout/griver_core_edit_layout.xml` | 900 | `e2705b0f2b796bbd…` |
| `res/layout/griver_core_item_option_menu.xml` | 1420 | `85204ed3426b09ae…` |
| `res/layout/griver_core_layout_actionsheet_item.xml` | 2024 | `8daa1f18434d949e…` |
| `res/layout/griver_core_layout_share_item.xml` | 1228 | `7f781f77086523d2…` |
| `res/layout/griver_core_local_auth_content.xml` | 4412 | `55f8b9f26f62546c…` |
| `res/layout/griver_core_panel_option_menu.xml` | 3160 | `d7e453d7c9a94d97…` |
| `res/layout/griver_core_prompt_input_dialog.xml` | 3048 | `d23f5cb4322ebdf3…` |
| `res/layout/griver_core_toast.xml` | 1188 | `a40062ad3ce644cf…` |
| `res/layout/griver_core_ui_message_box.xml` | 1720 | `0f63e7c187fe7d46…` |
| `res/layout/griver_core_uniform_dialog.xml` | 4288 | `0409a7e18fae86be…` |
| `res/layout/griver_core_window_share.xml` | 1604 | `1ea7a90b3eeda746…` |
| `res/layout/griver_device_activity_open_setting.xml` | 2876 | `c7abddbdbc8f6905…` |
| `res/layout/griver_device_dialog_simple_list.xml` | 544 | `1f3205a8a1282c82…` |
| `res/layout/griver_device_item_open_setting.xml` | 1328 | `1ebaaf359bb319da…` |
| `res/layout/griver_device_item_simple_text.xml` | 868 | `233fdb0064499b22…` |
| `res/layout/griver_diagnostic_tool_layout.xml` | 836 | `d81f9361937e7a82…` |
| `res/layout/griver_file_activity_page_list.xml` | 2392 | `21fc35f7165e788c…` |
| `res/layout/griver_file_item_page_image.xml` | 892 | `0cf92b34c0d7f3e7…` |
| `res/layout/griver_image_activity_capture.xml` | 5080 | `f92a9d407e2aec3a…` |
| `res/layout/griver_image_activity_photo_preview.xml` | 816 | `cb91db76ca9fc20b…` |
| `res/layout/griver_image_activity_photo_select.xml` | 7004 | `68a307c44d30d658…` |
| `res/layout/griver_image_activity_record_preview.xml` | 1436 | `11daac756b901b4c…` |
| `res/layout/griver_image_album_item.xml` | 1980 | `08f427819c9700c1…` |
| `res/layout/griver_image_photo_browse.xml` | 7132 | `6b34874556371825…` |
| `res/layout/griver_image_photo_grid.xml` | 3024 | `170f7766668fcfaa…` |
| `res/layout/griver_image_photo_preview.xml` | 1224 | `79f50b900ec08353…` |
| `res/layout/griver_image_view_capture_btn.xml` | 548 | `d5a78f615ea768a4…` |
| `res/layout/griver_image_view_custom_title_bar.xml` | 1812 | `458455e05400475a…` |
| `res/layout/griver_image_view_title_bar.xml` | 2100 | `3b3e113052f874a5…` |
| `res/layout/griver_layout_base_activity.xml` | 1572 | `5dbfeea649a27269…` |
| `res/layout/griver_operation_core_toast.xml` | 1188 | `8c88ebee8596877f…` |
| `res/layout/griver_ui_activity_multilevel_select.xml` | 1500 | `43d3bf2fc761f401…` |
| `res/layout/griver_ui_alert_dialog.xml` | 2132 | `45abd93f3b091b33…` |
| `res/layout/griver_ui_badge_layout.xml` | 1164 | `cd94693a54b91f29…` |
| `res/layout/griver_ui_base_dialog_button.xml` | 816 | `10762b97c446f85d…` |
| `res/layout/griver_ui_category_bar_layout.xml` | 2440 | `796196d130aba826…` |
| `res/layout/griver_ui_container_root_view.xml` | 1016 | `195c9f065adba044…` |
| `res/layout/griver_ui_default_tab_view.xml` | 912 | `d3874d743a1ffa48…` |
| `res/layout/griver_ui_empty_linearlayout.xml` | 364 | `8605ff26e4bb37ef…` |
| `res/layout/griver_ui_fragment_splash.xml` | 1388 | `413b6aec24ed9cae…` |
| `res/layout/griver_ui_list_dialog.xml` | 1656 | `32e0332e20e76df4…` |
| `res/layout/griver_ui_list_dialog_head.xml` | 680 | `6d8a3995b31a7cae…` |
| `res/layout/griver_ui_list_item_dialog.xml` | 1516 | `47ac71dd536a012a…` |
| `res/layout/griver_ui_loading_dialog.xml` | 1060 | `179166e2f9730aab…` |
| `res/layout/griver_ui_loading_view.xml` | 1444 | `77bbd06015182d56…` |
| `res/layout/griver_ui_menu_item_layout.xml` | 1000 | `44fc46d540532acd…` |
| `res/layout/griver_ui_nav_loading.xml` | 320 | `272643d6bd82d1fb…` |
| `res/layout/griver_ui_nav_menu_item.xml` | 2228 | `30509e943ef1011f…` |
| `res/layout/griver_ui_navigation_bar.xml` | 8888 | `feb3590dac04aa24…` |
| `res/layout/griver_ui_notice_dialog.xml` | 1196 | `b172a1e4f888d532…` |
| `res/layout/griver_ui_popmenu.xml` | 560 | `f707d206d428c0ce…` |
| `res/layout/griver_ui_single_item.xml` | 684 | `46664aa267ede2aa…` |
| `res/layout/griver_ui_splash_error.xml` | 1912 | `1c4e15424ed024ad…` |
| `res/layout/griver_ui_switch_tab_layout.xml` | 1816 | `7edaad13c6ada8ea…` |
| `res/layout/griver_ui_tabbaritem_new.xml` | 1724 | `474b2f802dd770af…` |
| `res/layout/griver_ui_title_bar.xml` | 3108 | `219367a0bc833326…` |
| `res/layout/griver_ui_title_left_view.xml` | 1084 | `ede48e9faec619ed…` |
| `res/layout/griver_ui_view_progress_dot.xml` | 896 | `6c81141719794d8a…` |
| `res/layout/h5_network_check_activity.xml` | 4756 | `51151aca5004a291…` |
| `res/layout/header_app_info.xml` | 1496 | `38120bac6b6ffbcc…` |
| `res/layout/header_clearing_account.xml` | 2176 | `b5ec7a819d4e1699…` |
| `res/layout/home_list_reminder_viewholder_layout.xml` | 1032 | `50e193630bbf9cde…` |
| `res/layout/home_reminder_bill_viewholder_layout.xml` | 3420 | `408c6c32cd395ad5…` |
| `res/layout/home_reminder_cta_type_1_layout.xml` | 1032 | `556aa22e27262e97…` |
| `res/layout/home_reminder_cta_type_2_layout.xml` | 948 | `e3d2fc478f8aed5b…` |
| `res/layout/home_reminder_default_viewholder_layout.xml` | 1064 | `ec2d188cbb123616…` |
| `res/layout/home_reminder_right_bg_with_right_cta_viewholder_layout.xml` | 3296 | `a1cd030194bff322…` |
| `res/layout/home_reminder_with_list_cta_viewholder_layout.xml` | 3572 | `17b3d923e0b69da4…` |
| `res/layout/include_car_picker_other_sdk_taxi.xml` | 4076 | `7ee432590d252226…` |
| `res/layout/include_car_picker_sdk_taxi.xml` | 5248 | `39049eb4caa24f05…` |
| `res/layout/include_driver_info_sdk_taxi.xml` | 3788 | `ff0addc1d9b6e1c6…` |
| `res/layout/include_driver_info_single_sdk_taxi.xml` | 2096 | `2087a6cf7f234b16…` |
| `res/layout/include_hearder_fragment_sdk_taxi.xml` | 1476 | `d86c9e390b66ffcb…` |
| `res/layout/include_payment_info_sdk_taxi.xml` | 2636 | `75861de2c25be1f5…` |
| `res/layout/include_shimmer_car_sdk_taxi.xml` | 2608 | `1e2149ff4458110d…` |
| `res/layout/include_status_book_sdk_taxi.xml` | 1184 | `22a5fae9d71fbfee…` |
| `res/layout/include_status_network_sdk_taxi.xml` | 1796 | `471867c02c64f416…` |
| `res/layout/include_status_payment_sdk_taxi.xml` | 1664 | `a5fa69005acb0807…` |
| `res/layout/invalid_list_item.xml` | 388 | `1a6e86b719a7d157…` |
| `res/layout/item_add_card_method_sdk_taxi.xml` | 1280 | `e6f3e0d2f410e45f…` |
| `res/layout/item_address_setting_sdk_taxi.xml` | 2304 | `d6dd73c2b66a76e4…` |
| `res/layout/item_banner_login.xml` | 928 | `e0d5e82002587b05…` |
| `res/layout/item_banner_user_profile_reform_kyc.xml` | 3040 | `b7d028a1a4d46864…` |
| `res/layout/item_brand_sdk_taxi.xml` | 1176 | `0c6b9bc8d8b5011b…` |
| `res/layout/item_car_type_small_sdk_taxi.xml` | 2792 | `13962903461411dc…` |
| `res/layout/item_card_method_sdk_taxi.xml` | 2728 | `089bab66e54e6458…` |
| `res/layout/item_client_note_sdk_taxi.xml` | 832 | `4faef950467d76ae…` |
| `res/layout/item_destination.xml` | 1180 | `65ad6f55cb73eee4…` |
| `res/layout/item_dot_destination.xml` | 700 | `70dc68284de5d83a…` |
| `res/layout/item_ekyc.xml` | 1532 | `a197ca568e60345d…` |
| `res/layout/item_group_payment_method_sdk_taxi.xml` | 2172 | `3bcdbee35b6d3e2f…` |
| `res/layout/item_loading_sdk_taxi.xml` | 864 | `3bffe800c29185e0…` |
| `res/layout/item_more_destination.xml` | 1392 | `3c17508b60ec48fd…` |
| `res/layout/item_origin.xml` | 1200 | `70620c640e05e574…` |
| `res/layout/item_payment_detail_method_sdk_taxi.xml` | 1788 | `f576acc3e796e498…` |
| `res/layout/item_personal_detail_layout.xml` | 1096 | `3f594a1581f0a526…` |
| `res/layout/item_radio_text.xml` | 952 | `8b14a0e8fdda7ed6…` |
| `res/layout/item_rating_hint_sdk_taxi.xml` | 1144 | `fc69ff34573249db…` |
| `res/layout/item_recommend_place_sdk_taxi.xml` | 2016 | `99c0c684f74f1628…` |
| `res/layout/item_report_sdk_taxi.xml` | 1944 | `7e30d7460afc5695…` |
| `res/layout/item_reset_pin_entry_point_1.xml` | 2320 | `a7df28e990227979…` |
| `res/layout/item_saved_place_sdk_taxi.xml` | 1556 | `3c2aab4e9a24b0a4…` |
| `res/layout/item_section_body_personal_details_layout.xml` | 1860 | `ed23e4ac9af0a125…` |
| `res/layout/item_section_details_layout.xml` | 1712 | `efb7ea2349ad3a14…` |
| `res/layout/item_section_footer_personal_details_layout.xml` | 1252 | `8b85439e7ec72509…` |
| `res/layout/item_show_report_sdk_taxi.xml` | 2188 | `ad0836efe8deaf5b…` |
| `res/layout/item_sub_tag_sdk_taxi.xml` | 732 | `77fb908c6a3e0c0f…` |
| `res/layout/item_tag_sdk_taxi.xml` | 1388 | `d8d7d6fd30de7272…` |
| `res/layout/item_voucher_sdk_taxi.xml` | 4168 | `196b0f7e8f83d634…` |
| `res/layout/layout_actionbar_ekyc.xml` | 3216 | `69d0e7ad8bb3f591…` |
| `res/layout/layout_bank_card_cvv_image.xml` | 2440 | `327dbac86d9ebb53…` |
| `res/layout/layout_bank_card_detail_image.xml` | 3580 | `99fb37995eed8799…` |
| `res/layout/layout_bank_card_view.xml` | 2020 | `951b7de4989839e4…` |
| `res/layout/layout_bank_condition_item.xml` | 888 | `8ac78b18bdfc18eb…` |
| `res/layout/layout_bank_edittext_compat.xml` | 2568 | `86a853f980d2257d…` |
| `res/layout/layout_bank_error_tooltip.xml` | 1988 | `c4f83fc47694cb2f…` |
| `res/layout/layout_bank_guide_find_time.xml` | 5240 | `176c810a002486a6…` |
| `res/layout/layout_bank_media_error_text_view.xml` | 1148 | `e34be491d3404b15…` |
| `res/layout/layout_bank_media_image_view.xml` | 604 | `1122f7aa634ed4f7…` |
| `res/layout/layout_bank_media_text_view.xml` | 608 | `0d1ba6dbfb4fe4cb…` |
| `res/layout/layout_bank_media_title_text_view.xml` | 648 | `f3033fa9fa1dc9ee…` |
| `res/layout/layout_bank_selection_banner.xml` | 540 | `33850efe5d59d18a…` |
| `res/layout/layout_bank_support.xml` | 2104 | `60b4af7ee3994c31…` |
| `res/layout/layout_bank_support_group_header.xml` | 2276 | `43e0910272802e71…` |
| `res/layout/layout_bank_transaction_detail.xml` | 2124 | `dd26bb80168b090b…` |
| `res/layout/layout_bio_registry.xml` | 2076 | `b117712b3218c0f7…` |
| `res/layout/layout_cashier_loading.xml` | 2244 | `0f016636f5c9139e…` |
| `res/layout/layout_cashier_loading_dialog.xml` | 1356 | `4e36dc740abd0500…` |
| `res/layout/layout_change_pin_content.xml` | 884 | `37c4c7d819536e19…` |
| `res/layout/layout_checkbox_term.xml` | 944 | `11783963861a46ff…` |
| `res/layout/layout_choose_and_input.xml` | 1144 | `2f63cda530d06365…` |
| `res/layout/layout_choose_job.xml` | 3112 | `e31be5c71ecc35a9…` |
| `res/layout/layout_clearing_account_body.xml` | 3412 | `414f687a048929c1…` |
| `res/layout/layout_close_confirmation.xml` | 3372 | `312914b293bfc63a…` |
| `res/layout/layout_coming_promotion.xml` | 1264 | `1ccbd4debb6782bd…` |
| `res/layout/layout_confirm_dialog.xml` | 2840 | `b1356c6533684caa…` |
| `res/layout/layout_container_bank_edittext.xml` | 552 | `bd0adb584c01c6b9…` |
| `res/layout/layout_data_item_footer.xml` | 748 | `0d14560cb51fe727…` |
| `res/layout/layout_edit_avatar_and_name.xml` | 6340 | `5ea772e0c953337b…` |
| `res/layout/layout_ekyc_banner.xml` | 1200 | `587714791092e2b3…` |
| `res/layout/layout_expandable_child_item.xml` | 652 | `00b2bc8a8074c99f…` |
| `res/layout/layout_expandable_parent_item.xml` | 968 | `4b2945ca0a6919c2…` |
| `res/layout/layout_faq_dialog.xml` | 5456 | `adf69274f11658d0…` |
| `res/layout/layout_guide_face_ekyc.xml` | 2256 | `9ca7ade2262afe65…` |
| `res/layout/layout_guide_ocr_ekyc.xml` | 4056 | `e15146ec71702069…` |
| `res/layout/layout_guide_switch.xml` | 1928 | `fb1cd1cb6f04f369…` |
| `res/layout/layout_guideline_ekyc.xml` | 2296 | `64c4d2f93e8ccbaf…` |
| `res/layout/layout_guideline_video_new.xml` | 3024 | `7af7fb0fb877c95a…` |
| `res/layout/layout_guideline_video_new_vision.xml` | 2544 | `06b1c5dcd1655276…` |
| `res/layout/layout_header_user_profile.xml` | 4448 | `4da5a0f5314e7629…` |
| `res/layout/layout_info_maps_sdk_taxi.xml` | 1200 | `89a26e481026bb93…` |
| `res/layout/layout_input_answer.xml` | 1608 | `e5cb346346250e26…` |
| `res/layout/layout_link_mer_benefits_kyc.xml` | 1984 | `01a6d156a2960894…` |
| `res/layout/layout_login_by_bio.xml` | 1796 | `06b4bbb33be6bf31…` |
| `res/layout/layout_login_by_pin.xml` | 2084 | `f68d16cd01ef2c7b…` |
| `res/layout/layout_main_cashier_error.xml` | 1584 | `becfa8dff7e7b4ff…` |
| `res/layout/layout_multiple_selection_view.xml` | 2056 | `6df60ab30f237017…` |
| `res/layout/layout_network_pin.xml` | 1252 | `e03df7307ac5e2b5…` |
| `res/layout/layout_number_key.xml` | 4352 | `ba6d6f2355482dc5…` |
| `res/layout/layout_number_keyboard.xml` | 424 | `1afde71fd3c7904d…` |
| `res/layout/layout_onboarding_edt_input.xml` | 948 | `b635ccaf79802d92…` |
| `res/layout/layout_onboarding_pwd_input.xml` | 400 | `72456b2c7ceb5374…` |
| `res/layout/layout_onboarding_view.xml` | 2680 | `e184c7a348e3e271…` |
| `res/layout/layout_otp_input.xml` | 1800 | `cfd527b9806fa40f…` |
| `res/layout/layout_password_view.xml` | 3848 | `7ca4905a09578767…` |
| `res/layout/layout_payment_container.xml` | 2608 | `36c21c429e6bcabe…` |
| `res/layout/layout_pick_image_or_take_photo.xml` | 2772 | `5aa5ebaefee8acb1…` |
| `res/layout/layout_pin_auth.xml` | 5804 | `26b1b9784d864829…` |
| `res/layout/layout_pin_expiration_option.xml` | 1252 | `87282fe4bf0a6622…` |
| `res/layout/layout_progress_ekyc.xml` | 2200 | `e851cf4919c861b3…` |
| `res/layout/layout_promotion_cash_back.xml` | 3224 | `1d833bed758556d4…` |
| `res/layout/layout_promotion_voucher.xml` | 5388 | `7209037b482661d6…` |
| `res/layout/layout_retry_with_image.xml` | 1128 | `509e15564d21a686…` |
| `res/layout/layout_round_pin_view.xml` | 952 | `dc487aa4a08adb17…` |
| `res/layout/layout_row_bank_id_type.xml` | 1276 | `a6d1397d6933fd56…` |
| `res/layout/layout_saving_account_row.xml` | 1672 | `d407d62950bfbdad…` |
| `res/layout/layout_search_bank_support.xml` | 2096 | `6b229a9c1c5239ce…` |
| `res/layout/layout_security_verification.xml` | 1208 | `3282525dbc043faa…` |
| `res/layout/layout_select_type_transaction_sdk_taxi.xml` | 8652 | `17a6db4e3fb74eea…` |
| `res/layout/layout_smart_otp_registry.xml` | 1864 | `de635612dba599bb…` |
| `res/layout/layout_support_bank_loading.xml` | 1092 | `1bb98c9a2a2d2b78…` |
| `res/layout/layout_support_open_bank_account.xml` | 2296 | `7a61a70a67f6e321…` |
| `res/layout/layout_toast_new.xml` | 1108 | `6d56c6d9f1be6050…` |
| `res/layout/layout_topup_row.xml` | 1692 | `401755b8f858205f…` |
| `res/layout/layout_toro_image_view.xml` | 668 | `8338b52583e5f329…` |
| `res/layout/layout_user_profile_additional_info.xml` | 7924 | `5e8830bcdc04a877…` |
| `res/layout/layout_user_profile_details_container.xml` | 1696 | `c7bbdf4b6bf00e6d…` |
| `res/layout/layout_user_support_v2.xml` | 2224 | `329f4ee7a253c32d…` |
| `res/layout/layout_vision_camerax_live_preview.xml` | 1196 | `ce90ba197216a235…` |
| `res/layout/layout_withdraw_row.xml` | 1692 | `5acf3e140eeddcca…` |
| `res/layout/list_item_pdf_page.xml` | 828 | `fd1f1b5e24a2ad67…` |
| `res/layout/login_layout.xml` | 576 | `15c46bfd53fec986…` |
| `res/layout/login_locked_account_layout.xml` | 2732 | `4c4d3482ebe36dae…` |
| `res/layout/login_pin_verification_layout.xml` | 4600 | `c315fc39943b9571…` |
| `res/layout/login_version_e_layout.xml` | 4028 | `e2da3ccc20dc85a5…` |
| `res/layout/material_clock_display.xml` | 1172 | `8361015a46ba2b9a…` |
| `res/layout/material_clock_display_divider.xml` | 752 | `b7d820a69e7fdb69…` |
| `res/layout/material_clock_period_toggle.xml` | 1504 | `9c93dd29f4f28400…` |
| `res/layout/material_clockface_textview.xml` | 476 | `3f0ff93a19ea3133…` |
| `res/layout/material_clockface_view.xml` | 1012 | `71890517cc507a26…` |
| `res/layout/material_radial_view_group.xml` | 768 | `a92981f60a714bde…` |
| `res/layout/material_time_chip.xml` | 380 | `7d97405e037596d2…` |
| `res/layout/material_time_input.xml` | 1208 | `8e5e4954c747df92…` |
| `res/layout/material_timepicker.xml` | 1136 | `a673960ea9075555…` |
| `res/layout/mock_layout_sdk_taxi.xml` | 2224 | `358369e51d11d294…` |
| `res/layout/module_cashier_info.xml` | 4660 | `dd5ac8caa8538c2f…` |
| `res/layout/mtrl_alert_dialog.xml` | 2476 | `ff99d0053f9ffd3e…` |
| `res/layout/mtrl_alert_dialog_actions.xml` | 1676 | `b386a825348b0cf6…` |
| `res/layout/mtrl_alert_dialog_title.xml` | 956 | `d1b814decb8466ac…` |
| `res/layout/mtrl_alert_select_dialog_item.xml` | 588 | `78cb0867cc26296e…` |
| `res/layout/mtrl_alert_select_dialog_multichoice.xml` | 940 | `1154c172d432149f…` |
| `res/layout/mtrl_alert_select_dialog_singlechoice.xml` | 940 | `3a3bf441b22a764f…` |
| `res/layout/mtrl_calendar_day.xml` | 352 | `3b653229d41ad3a5…` |
| `res/layout/mtrl_calendar_day_of_week.xml` | 400 | `ada661b93418558a…` |
| `res/layout/mtrl_calendar_days_of_week.xml` | 436 | `dd81cf9a3092f985…` |
| `res/layout/mtrl_calendar_horizontal.xml` | 1176 | `f318e76fb66688b2…` |
| `res/layout/mtrl_calendar_month_labeled.xml` | 728 | `febbf04d6c6f651a…` |
| `res/layout/mtrl_calendar_month_navigation.xml` | 1748 | `27e58a503e880630…` |
| `res/layout/mtrl_calendar_months.xml` | 428 | `fbabef8e3b337d77…` |
| `res/layout/mtrl_calendar_vertical.xml` | 740 | `38ec8ff42bcaa0de…` |
| `res/layout/mtrl_calendar_year.xml` | 352 | `547a7bf5b1390144…` |
| `res/layout/mtrl_layout_snackbar.xml` | 492 | `1893d0a027521338…` |
| `res/layout/mtrl_layout_snackbar_include.xml` | 952 | `fb50715c67fef7c3…` |
| `res/layout/mtrl_picker_actions.xml` | 984 | `6f89c92254592072…` |
| `res/layout/mtrl_picker_dialog.xml` | 1140 | `5b957e051eaf15a1…` |
| `res/layout/mtrl_picker_fullscreen.xml` | 848 | `928c01a78bb92d7a…` |
| `res/layout/mtrl_picker_header_dialog.xml` | 1440 | `06776068d16a1669…` |
| `res/layout/mtrl_picker_header_fullscreen.xml` | 2728 | `4a1708cae65cb1cb…` |
| `res/layout/mtrl_picker_header_selection_text.xml` | 712 | `3683f966930a5725…` |
| `res/layout/mtrl_picker_header_title_text.xml` | 624 | `293acfa59dc653bc…` |
| `res/layout/mtrl_picker_header_toggle.xml` | 576 | `d54a3c9cabe1d087…` |
| `res/layout/new_welcome_back_layout.xml` | 3512 | `069158ebc6f1d976…` |
| `res/layout/offline_ads_dialog.xml` | 1528 | `21c4c8f8c577ff11…` |
| `res/layout/otp_auth_provider_layout.xml` | 4756 | `00100b9c80b0a867…` |
| `res/layout/otp_layout.xml` | 4852 | `0938342c98c9915e…` |
| `res/layout/payment_after_fragment_sdk_taxi.xml` | 7400 | `60d54b8dd95b5c4d…` |
| `res/layout/payment_fragment_sdk_taxi.xml` | 1296 | `0e682ce022ed6b98…` |
| `res/layout/payment_password_view.xml` | 4924 | `98711fdfb3ab4ffd…` |
| `res/layout/payment_webview_retry_network.xml` | 1088 | `d37205089c94bcfe…` |
| `res/layout/pdf_rendererview.xml` | 896 | `20314a25d307a2ce…` |
| `res/layout/pdf_view_page_loading_layout.xml` | 516 | `d8d0e5135567bdec…` |
| `res/layout/pdf_view_page_no.xml` | 724 | `147a29336b0a46e0…` |
| `res/layout/pdf_view_tool_bar.xml` | 1120 | `232976d3deb51147…` |
| `res/layout/personal_info_detail_layout.xml` | 3128 | `a428ce24b1c5cef0…` |
| `res/layout/phone_exist_layout.xml` | 3176 | `763e06ba91132f8b…` |
| `res/layout/popup_confirm_method_payment_sdk_taxi.xml` | 1628 | `e9f31de24e61e481…` |
| `res/layout/popup_confirm_new_sdk_taxi.xml` | 1668 | `ebd3602e4a9b679f…` |
| `res/layout/popup_confirm_two_action_sdk_taxi.xml` | 2216 | `880116a7979ee092…` |
| `res/layout/popup_list_sdk_taxi.xml` | 4036 | `92b9d1bfe3136a51…` |
| `res/layout/popup_partner_all_sdk_taxi.xml` | 4804 | `7ff2f508a3700848…` |
| `res/layout/popup_partner_sdk_taxi.xml` | 3496 | `faa4b52758a366b2…` |
| `res/layout/preview_fragment_new.xml` | 3428 | `d4afd3f07bbff08d…` |
| `res/layout/preview_video_fragment_new.xml` | 3704 | `d7b42045123fb016…` |
| `res/layout/radio_column_center_horizontal_null_0children.xml` | 396 | `56a8146bf00d74b9…` |
| `res/layout/radio_column_center_horizontal_null_10children.xml` | 1220 | `3ac370634d86904e…` |
| `res/layout/radio_column_center_horizontal_null_1children.xml` | 500 | `3a931061f2745a8d…` |
| `res/layout/radio_column_center_horizontal_null_2children.xml` | 580 | `a3895990dd196ba3…` |
| `res/layout/radio_column_center_horizontal_null_3children.xml` | 660 | `8cc727dbf30d8407…` |
| `res/layout/radio_column_center_horizontal_null_4children.xml` | 740 | `eb7298f529ff6092…` |
| `res/layout/radio_column_center_horizontal_null_5children.xml` | 820 | `7b915d17145a807d…` |
| `res/layout/radio_column_center_horizontal_null_6children.xml` | 900 | `783c88d0cc8fc3e0…` |
| `res/layout/radio_column_center_horizontal_null_7children.xml` | 980 | `55aba3c6390ae621…` |
| `res/layout/radio_column_center_horizontal_null_8children.xml` | 1060 | `0900b8a9acd06a76…` |
| `res/layout/radio_column_center_horizontal_null_9children.xml` | 1140 | `997ba10744fc5529…` |
| `res/layout/radio_column_end_null_0children.xml` | 396 | `56a8146bf00d74b9…` |
| `res/layout/radio_column_end_null_10children.xml` | 1220 | `abe8885840a92128…` |
| `res/layout/radio_column_end_null_1children.xml` | 500 | `f52e2fbbb490e8d6…` |
| `res/layout/radio_column_end_null_2children.xml` | 580 | `3fb58929a009658e…` |
| `res/layout/radio_column_end_null_3children.xml` | 660 | `6fd0f7346ceb04e7…` |
| `res/layout/radio_column_end_null_4children.xml` | 740 | `7cd8f3ce074bcba5…` |
| `res/layout/radio_column_end_null_5children.xml` | 820 | `a30ba9a86bea040e…` |
| `res/layout/radio_column_end_null_6children.xml` | 900 | `78c8d55aae0ae891…` |
| `res/layout/radio_column_end_null_7children.xml` | 980 | `4df34199f8d6a70f…` |
| `res/layout/radio_column_end_null_8children.xml` | 1060 | `f81dfad13af406e3…` |
| `res/layout/radio_column_end_null_9children.xml` | 1140 | `e0aa2f9ab2908a56…` |
| `res/layout/radio_column_start_null_0children.xml` | 396 | `56a8146bf00d74b9…` |
| `res/layout/radio_column_start_null_10children.xml` | 1220 | `840f3f1e0e3205e9…` |
| `res/layout/radio_column_start_null_1children.xml` | 500 | `2ea28b54c12a3eb4…` |
| `res/layout/radio_column_start_null_2children.xml` | 580 | `74d211b506cac5e0…` |
| `res/layout/radio_column_start_null_3children.xml` | 660 | `463e651bd88f9ce4…` |
| `res/layout/radio_column_start_null_4children.xml` | 740 | `f651d10c7d0b7a0a…` |
| `res/layout/radio_column_start_null_5children.xml` | 820 | `c080d428d7f04a77…` |
| `res/layout/radio_column_start_null_6children.xml` | 900 | `2ec1bbe9c01d0037…` |
| `res/layout/radio_column_start_null_7children.xml` | 980 | `243f1dce0891022d…` |
| `res/layout/radio_column_start_null_8children.xml` | 1060 | `0078de71050a032e…` |
| `res/layout/radio_column_start_null_9children.xml` | 1140 | `5ec754774796c693…` |
| `res/layout/radio_row_null_bottom_0children.xml` | 396 | `93e761f354add898…` |
| `res/layout/radio_row_null_bottom_10children.xml` | 1220 | `4208c0797c910d07…` |
| `res/layout/radio_row_null_bottom_1children.xml` | 500 | `d74fd0a522cc7002…` |
| `res/layout/radio_row_null_bottom_2children.xml` | 580 | `f73e91015043feaf…` |
| `res/layout/radio_row_null_bottom_3children.xml` | 660 | `6efaa031aa941457…` |
| `res/layout/radio_row_null_bottom_4children.xml` | 740 | `9854d0933df3578c…` |
| `res/layout/radio_row_null_bottom_5children.xml` | 820 | `3b133827dc4ce28e…` |
| `res/layout/radio_row_null_bottom_6children.xml` | 900 | `537519d688e84b25…` |
| `res/layout/radio_row_null_bottom_7children.xml` | 980 | `f5c7c2785846a305…` |
| `res/layout/radio_row_null_bottom_8children.xml` | 1060 | `ebb944328fdce35b…` |
| `res/layout/radio_row_null_bottom_9children.xml` | 1140 | `a3013b42afa4d466…` |
| `res/layout/radio_row_null_center_vertical_0children.xml` | 396 | `93e761f354add898…` |
| `res/layout/radio_row_null_center_vertical_10children.xml` | 1220 | `739599d5a37da660…` |
| `res/layout/radio_row_null_center_vertical_1children.xml` | 500 | `c9b7aa3a9b24ff73…` |
| `res/layout/radio_row_null_center_vertical_2children.xml` | 580 | `cd6c0519fab90865…` |
| `res/layout/radio_row_null_center_vertical_3children.xml` | 660 | `9ad3d7bc881071e3…` |
| `res/layout/radio_row_null_center_vertical_4children.xml` | 740 | `9af2ed6af299e225…` |
| `res/layout/radio_row_null_center_vertical_5children.xml` | 820 | `a8f4f286a26591dc…` |
| `res/layout/radio_row_null_center_vertical_6children.xml` | 900 | `7efb0e6e2c09fc73…` |
| `res/layout/radio_row_null_center_vertical_7children.xml` | 980 | `0c4cfb4a123465e2…` |
| `res/layout/radio_row_null_center_vertical_8children.xml` | 1060 | `cb07e951f4d0fcab…` |
| `res/layout/radio_row_null_center_vertical_9children.xml` | 1140 | `782af2d6c46af2f0…` |
| `res/layout/radio_row_null_top_0children.xml` | 396 | `93e761f354add898…` |
| `res/layout/radio_row_null_top_10children.xml` | 1220 | `064187cc507f5aea…` |
| `res/layout/radio_row_null_top_1children.xml` | 500 | `445f6c6d0bbaba00…` |
| `res/layout/radio_row_null_top_2children.xml` | 580 | `e58e29ab558ea2a5…` |
| `res/layout/radio_row_null_top_3children.xml` | 660 | `8390368dce5c4805…` |
| `res/layout/radio_row_null_top_4children.xml` | 740 | `80a933d5dab778c4…` |
| `res/layout/radio_row_null_top_5children.xml` | 820 | `9056547f00a62238…` |
| `res/layout/radio_row_null_top_6children.xml` | 900 | `1da916ec33cb05b3…` |
| `res/layout/radio_row_null_top_7children.xml` | 980 | `3333533c6049d707…` |
| `res/layout/radio_row_null_top_8children.xml` | 1060 | `171ebcb016b6f447…` |
| `res/layout/radio_row_null_top_9children.xml` | 1140 | `6b522bc36e4aa5d7…` |
| `res/layout/rating_fragment_sdk_taxi.xml` | 1212 | `2fb384d9aee2acbf…` |
| `res/layout/registration_phone_layout.xml` | 3468 | `c85b5fe8a1fb6c7e…` |
| `res/layout/registration_pin_layout.xml` | 2444 | `4bc5d7d8fc806bd1…` |
| `res/layout/result_fragment_new.xml` | 6412 | `89d9cc5d75204230…` |
| `res/layout/root_match_match.xml` | 524 | `50bfb6e329a72700…` |
| `res/layout/root_match_wrap.xml` | 524 | `2db8c7b1ead95a29…` |
| `res/layout/root_wrap_match.xml` | 524 | `f1f90ea6a6a6758a…` |
| `res/layout/root_wrap_wrap.xml` | 524 | `6e82700316c75ee1…` |
| `res/layout/row_child_null_bottom_group_0.xml` | 1472 | `b38de48929fe7ddb…` |
| `res/layout/row_child_null_bottom_group_1.xml` | 1472 | `c4bf7536babf022a…` |
| `res/layout/row_child_null_bottom_group_2.xml` | 1472 | `4dcb9e7941f9ea50…` |
| `res/layout/row_child_null_bottom_group_3.xml` | 1472 | `fe0c23b206f6656e…` |
| `res/layout/row_child_null_bottom_group_4.xml` | 1472 | `b4f0577307d0aca6…` |
| `res/layout/row_child_null_bottom_group_5.xml` | 1472 | `fe281353d62e0e86…` |
| `res/layout/row_child_null_bottom_group_6.xml` | 1472 | `4bc87314a1d55fb5…` |
| `res/layout/row_child_null_bottom_group_7.xml` | 1472 | `fa4c43490b84ed9d…` |
| `res/layout/row_child_null_bottom_group_8.xml` | 1472 | `6aed6b686b353c33…` |
| `res/layout/row_child_null_bottom_group_9.xml` | 1472 | `0fada64c9a8147f4…` |
| `res/layout/row_child_null_center_vertical_group_0.xml` | 1472 | `b82c134f76912c80…` |
| `res/layout/row_child_null_center_vertical_group_1.xml` | 1472 | `18afbfa32f8da09a…` |
| `res/layout/row_child_null_center_vertical_group_2.xml` | 1472 | `4ce6baaa09b2e58f…` |
| `res/layout/row_child_null_center_vertical_group_3.xml` | 1472 | `9e325d40b9e917fd…` |
| `res/layout/row_child_null_center_vertical_group_4.xml` | 1472 | `e2dfbd3611791f71…` |
| `res/layout/row_child_null_center_vertical_group_5.xml` | 1472 | `965d2b8df111a2bd…` |
| `res/layout/row_child_null_center_vertical_group_6.xml` | 1472 | `83a5e78e15d21c70…` |
| `res/layout/row_child_null_center_vertical_group_7.xml` | 1472 | `b99645a16ffed72e…` |
| `res/layout/row_child_null_center_vertical_group_8.xml` | 1472 | `34b95ac0e72cacd0…` |
| `res/layout/row_child_null_center_vertical_group_9.xml` | 1472 | `11041311ab998662…` |
| `res/layout/row_child_null_top_group_0.xml` | 1472 | `99809703645af99a…` |
| `res/layout/row_child_null_top_group_1.xml` | 1472 | `920407d07ced84d5…` |
| `res/layout/row_child_null_top_group_2.xml` | 1472 | `aefdbdcc1b4193bb…` |
| `res/layout/row_child_null_top_group_3.xml` | 1472 | `9c8c2c1e4e0b50c2…` |
| `res/layout/row_child_null_top_group_4.xml` | 1472 | `ee45080be7d541cf…` |
| `res/layout/row_child_null_top_group_5.xml` | 1472 | `fc476f4b4912fec8…` |
| `res/layout/row_child_null_top_group_6.xml` | 1472 | `33f742ccb9868a52…` |
| `res/layout/row_child_null_top_group_7.xml` | 1472 | `fbafab838342df5e…` |
| `res/layout/row_child_null_top_group_8.xml` | 1472 | `bea84ffdbc956aaf…` |
| `res/layout/row_child_null_top_group_9.xml` | 1472 | `b1e598b2882cbaf1…` |
| `res/layout/row_login_device.xml` | 1472 | `e241a2a1d69d97d1…` |
| `res/layout/row_multiple_choice_dialog.xml` | 1072 | `2f2dcf2bb9f8b8f9…` |
| `res/layout/row_null_bottom_0children.xml` | 396 | `56b17b9656a6a199…` |
| `res/layout/row_null_bottom_10children.xml` | 1224 | `8afac49c441353ab…` |
| `res/layout/row_null_bottom_1children.xml` | 504 | `ce5252b6fa3847aa…` |
| `res/layout/row_null_bottom_2children.xml` | 584 | `064624509cc2838a…` |
| `res/layout/row_null_bottom_3children.xml` | 664 | `8d363ad28b0f62db…` |
| `res/layout/row_null_bottom_4children.xml` | 744 | `302ea66caf87ee8a…` |
| `res/layout/row_null_bottom_5children.xml` | 824 | `0c749173bb36687b…` |
| `res/layout/row_null_bottom_6children.xml` | 904 | `1329976433e2a661…` |
| `res/layout/row_null_bottom_7children.xml` | 984 | `485b148185337584…` |
| `res/layout/row_null_bottom_8children.xml` | 1064 | `11be67030d655f7c…` |
| `res/layout/row_null_bottom_9children.xml` | 1144 | `32a5346056fa94ba…` |
| `res/layout/row_null_center_vertical_0children.xml` | 396 | `56b17b9656a6a199…` |
| `res/layout/row_null_center_vertical_10children.xml` | 1224 | `b293679fa4d2152d…` |
| `res/layout/row_null_center_vertical_1children.xml` | 504 | `d8b55d968f1b7c54…` |
| `res/layout/row_null_center_vertical_2children.xml` | 584 | `97743291f87c0a6f…` |
| `res/layout/row_null_center_vertical_3children.xml` | 664 | `6b3d68a68893d15a…` |
| `res/layout/row_null_center_vertical_4children.xml` | 744 | `d41d5975f50140b7…` |
| `res/layout/row_null_center_vertical_5children.xml` | 824 | `261dd547c8a6996e…` |
| `res/layout/row_null_center_vertical_6children.xml` | 904 | `3246a9d2196b0059…` |
| `res/layout/row_null_center_vertical_7children.xml` | 984 | `90d4c16e4a735194…` |
| `res/layout/row_null_center_vertical_8children.xml` | 1064 | `1adca40e8e536845…` |
| `res/layout/row_null_center_vertical_9children.xml` | 1144 | `124f7079803daa91…` |
| `res/layout/row_null_top_0children.xml` | 396 | `56b17b9656a6a199…` |
| `res/layout/row_null_top_10children.xml` | 1224 | `fe0227f8dea0433a…` |
| `res/layout/row_null_top_1children.xml` | 504 | `3e461c379605c944…` |
| `res/layout/row_null_top_2children.xml` | 584 | `80409db4ed45da99…` |
| `res/layout/row_null_top_3children.xml` | 664 | `6331fb44c0b11b20…` |
| `res/layout/row_null_top_4children.xml` | 744 | `2cc1386c38c22a92…` |
| `res/layout/row_null_top_5children.xml` | 824 | `e366184c0dd33b6e…` |
| `res/layout/row_null_top_6children.xml` | 904 | `a3683962bf82af80…` |
| `res/layout/row_null_top_7children.xml` | 984 | `afbbc9b398d647a7…` |
| `res/layout/row_null_top_8children.xml` | 1064 | `47f43a954fcac1d1…` |
| `res/layout/row_null_top_9children.xml` | 1144 | `f6b7d3ef553fbefa…` |
| `res/layout/select_dialog_item_material.xml` | 640 | `5975cc3574034d30…` |
| `res/layout/select_dialog_multichoice_material.xml` | 864 | `5faed250d643ea70…` |
| `res/layout/select_dialog_singlechoice_material.xml` | 864 | `5288bfb2d83358e4…` |
| `res/layout/size_match_match.xml` | 320 | `ceaf330970be685f…` |
| `res/layout/size_match_wrap.xml` | 320 | `13deac544d76677d…` |
| `res/layout/size_wrap_match.xml` | 320 | `5d3832d994aa2d58…` |
| `res/layout/size_wrap_wrap.xml` | 320 | `c24bc51ff4802727…` |
| `res/layout/smart_scanner_fragment.xml` | 2324 | `d0de6b6b13e01e41…` |
| `res/layout/support_simple_spinner_dropdown_item.xml` | 464 | `178a49feca9c2292…` |
| `res/layout/switch_account_layout.xml` | 3008 | `54e70cd6a8a5b820…` |
| `res/layout/taxi_activity_browser.xml` | 1632 | `8c4371c5169e3e15…` |
| `res/layout/taxi_dialog_notice.xml` | 2360 | `75340e881cd0d6ba…` |
| `res/layout/taxi_dialog_unusual_notification.xml` | 1728 | `ed3b7c4ccf5d2fff…` |
| `res/layout/taxi_island_marker.xml` | 2376 | `c55cab55b1d8f8d6…` |
| `res/layout/taxi_item_list.xml` | 1048 | `d3d2ffade4acdd54…` |
| `res/layout/taxi_item_partner.xml` | 2640 | `98a9e29fdcf0f9be…` |
| `res/layout/taxi_item_sapetation_partner.xml` | 528 | `4b2039c65ac0a781…` |
| `res/layout/taxi_layout_hot_notification.xml` | 2016 | `9f150e57180cb0e6…` |
| `res/layout/taxi_pick_on_map_view.xml` | 1904 | `822b522c6fd4485e…` |
| `res/layout/taxi_title_bar.xml` | 2280 | `5e51003a2e754744…` |
| `res/layout/taxi_title_transparent.xml` | 2320 | `247a6c8215dd4012…` |
| `res/layout/terms_and_conditions_layout.xml` | 5484 | `011132db6417bd62…` |
| `res/layout/toolbar_layout.xml` | 596 | `19d78c2a6c8ccd2e…` |
| `res/layout/toolbar_webapp_external.xml` | 3216 | `e070716f1661ec7f…` |
| `res/layout/toolbar_white_webview.xml` | 2400 | `3549ff5074a8ef32…` |
| `res/layout/trip_short_info_general_layout_sdk_taxi.xml` | 3364 | `dd1cee24bf579d0b…` |
| `res/layout/trip_short_info_layout.xml` | 7432 | `b4f622e935bfe80d…` |
| `res/layout/trip_short_info_payment_layout_sdk_taxi.xml` | 16648 | `3032f3cae58dfa01…` |
| `res/layout/trueid_facesdk_camerax_activity.xml` | 516 | `9308d13ba5fb8836…` |
| `res/layout/trueid_facesdk_fragment_camerax.xml` | 4692 | `3e52ca3dc6653b52…` |
| `res/layout/trueid_facesdk_fragment_permission.xml` | 2764 | `b2e4167325612138…` |
| `res/layout/trueidsdk_activity.xml` | 396 | `b15b4c89015dc284…` |
| `res/layout/trueidsdk_alert_view.xml` | 1920 | `15bfed62825f35e9…` |
| `res/layout/trueidsdk_bottom_sheet_nfc.xml` | 3240 | `c83d86c16d105c00…` |
| `res/layout/trueidsdk_bottom_sheet_qr.xml` | 1768 | `624105de8198f284…` |
| `res/layout/trueidsdk_camera_card_mrz_container.xml` | 2712 | `945559db57be7148…` |
| `res/layout/trueidsdk_face_fragment_camerax.xml` | 5180 | `261ab636696cd850…` |
| `res/layout/trueidsdk_face_fragment_permission.xml` | 2648 | `3df8a4406bb8eb65…` |
| `res/layout/trueidsdk_face_fragment_selfie_invalid.xml` | 6720 | `717c8c4309742c6f…` |
| `res/layout/trueidsdk_face_selfie_guide_item.xml` | 1448 | `166a5a3a54eb8be9…` |
| `res/layout/trueidsdk_fragment_camera.xml` | 556 | `91734f98eaf70017…` |
| `res/layout/trueidsdk_fragment_camera_card_container.xml` | 4544 | `f22a7bd9668f412c…` |
| `res/layout/trueidsdk_fragment_camera_qr_container.xml` | 3540 | `b95c8dde12afbe33…` |
| `res/layout/trueidsdk_fragment_nfc.xml` | 5336 | `ac2c6a6155efc08c…` |
| `res/layout/trueidsdk_fragment_ocr_info.xml` | 6192 | `8eb1bcb8dc6b767c…` |
| `res/layout/trueidsdk_fragment_selife.xml` | 2636 | `fed645f7e8212369…` |
| `res/layout/trueidsdk_progress_dialog.xml` | 980 | `58880ca07b5751f8…` |
| `res/layout/tsnackbar_layout.xml` | 564 | `467842c6dd540aec…` |
| `res/layout/tsnackbar_layout_include.xml` | 1112 | `7fd005424e820c6a…` |
| `res/layout/turn_on_bio_suggestion_layout.xml` | 2952 | `25cbcb600ec0e874…` |
| `res/layout/upgrade_k0k1_layout.xml` | 744 | `0523e5da95425873…` |
| `res/layout/video_fragment_new.xml` | 2844 | `ea34e16deeeeb6e2…` |
| `res/layout/video_fragment_new_vision.xml` | 5160 | `8b1dcdc574a93d44…` |
| `res/layout/video_fragment_new_vision_v4.xml` | 5164 | `83ae1973c3449818…` |
| `res/layout/video_fragment_new_vision_x.xml` | 3684 | `2861b1cac295759f…` |
| `res/layout/view_caculator_keyboard.xml` | 3204 | `fde04d13d5d4332e…` |
| `res/layout/view_compose_wrapper.xml` | 384 | `1beb37c79ca016f3…` |
| `res/layout/view_number_keyboard.xml` | 2400 | `bbffeb2d01139bf3…` |
| `res/layout/view_number_text_field.xml` | 2128 | `dd7cd3c8a83bba7d…` |
| `res/layout/view_pin_keyboard.xml` | 3596 | `010ca7deac1cb47b…` |
| `res/layout/view_pin_keyboard_button.xml` | 1092 | `f33aa0306608fcf2…` |
| `res/layout/view_round_pin.xml` | 552 | `24aea2d5b427ba1e…` |
| `res/layout/view_round_pin_code.xml` | 436 | `8076f77b041d8544…` |
| `res/layout/webapp_activity_external.xml` | 920 | `1bb330d46b661604…` |
| `res/layout/webapp_activity_main.xml` | 724 | `47e295bfc5145c27…` |
| `res/layout/webapp_fragment_internal.xml` | 1816 | `3b8ddef6b3f861a2…` |
| `res/layout/webapp_fragment_mainview.xml` | 1072 | `c9d21033a61d4640…` |
| `res/layout/webapp_fragment_webview.xml` | 1032 | `3087b7a74781a0d0…` |
| `res/layout/webapp_menu_item.xml` | 652 | `01f5ed0b537467ff…` |
| `res/layout/webapp_menu_item_close.xml` | 652 | `867d16306822ae72…` |
| `res/layout/zalo_web_regis_active_code.xml` | 2588 | `d265ef51405c0c0f…` |
| `res/layout/zalo_web_regis_dialog_confirm.xml` | 2732 | `e4c6d9d1b34daad3…` |
| `res/layout/zalo_web_regis_input_phone.xml` | 2296 | `1c1d03092204c0f6…` |
| `res/layout/zalo_web_regis_item_country.xml` | 712 | `10a0909e34ad3c74…` |
| `res/layout/zalo_web_regis_layout_select_country.xml` | 636 | `e80ad03d7403d1bd…` |
| `res/layout/zalo_web_regis_termcondition.xml` | 840 | `577e717f9cb2316a…` |
| `res/layout/zalosdk_activity_zalo_web_login.xml` | 684 | `e1d0492831822c33…` |
| `res/layout/zalosdk_fragment_zalo_web_create_password.xml` | 2116 | `b2c54973657472b6…` |
| `res/layout/zalosdk_fragment_zalo_web_login.xml` | 924 | `8b0c9ec23f93384f…` |
| `res/layout/zalosdk_fragment_zalo_web_update_profile.xml` | 3700 | `2ed857f308f037df…` |
| `res/layout/zalosdk_videw_web_login_toolbar.xml` | 1128 | `eeeeb25c080dad18…` |
| `res/menu/tidv3_true_list_sex.xml` | 820 | `00466191897fa2a5…` |
| `res/menu/toolbar_menu.xml` | 588 | `9fb7c2573dfac1da…` |
| `res/menu/webapp_menu.xml` | 764 | `2f4bfe689cee9328…` |
| `res/mipmap-anydpi-v26/ic_launcher.xml` | 448 | `88688b32f491fe0e…` |
| `res/mipmap-hdpi-v4/ic_launcher.png` | 2488 | `dadf676e9c678285…` |
| `res/mipmap-hdpi-v4/ic_launcher_foreground.png` | 3350 | `427b568b5175d29b…` |
| `res/mipmap-mdpi-v4/ic_launcher.png` | 1612 | `b71720e24fa31358…` |
| `res/mipmap-mdpi-v4/ic_launcher_foreground.png` | 2152 | `b8bd29e36d912889…` |
| `res/mipmap-night-v8/ic_launcher.xml` | 448 | `88688b32f491fe0e…` |
| `res/mipmap-night-v8/ic_launcher_foreground.png` | 10018 | `b3b458b838182416…` |
| `res/mipmap-xhdpi-v4/ic_launcher.png` | 3289 | `d48401bf4b202149…` |
| `res/mipmap-xhdpi-v4/ic_launcher_foreground.png` | 4592 | `85ce67add065007d…` |
| `res/mipmap-xxhdpi-v4/ic_launcher.png` | 5085 | `f19cf0c8a3a28fba…` |
| `res/mipmap-xxhdpi-v4/ic_launcher_foreground.png` | 7077 | `138826fa88d11330…` |
| `res/mipmap-xxxhdpi-v4/ic_launcher.png` | 7035 | `8af5ea208aa690ce…` |
| `res/mipmap-xxxhdpi-v4/ic_launcher_foreground.png` | 10018 | `b3b458b838182416…` |
| `res/navigation/balance_navigation_graph.xml` | 668 | `b7083f68aff52db1…` |
| `res/navigation/bank_binding_nav_graph.xml` | 24288 | `dace10b2a034c42d…` |
| `res/navigation/cash_in_nav_graph.xml` | 4760 | `6125def390876c69…` |
| `res/navigation/fund_management_nav_graph.xml` | 1836 | `814938e7588752a6…` |
| `res/navigation/navigation_account_security_setting.xml` | 1092 | `07cb4d1da028b1f6…` |
| `res/navigation/navigation_feature_bank_binding.xml` | 904 | `c08897c4fe9bcd7a…` |
| `res/navigation/navigation_feature_cash_in.xml` | 892 | `ca1d23f686819835…` |
| `res/navigation/navigation_feature_changepin.xml` | 900 | `b38829307c146d83…` |
| `res/navigation/navigation_feature_fund_management.xml` | 904 | `600078877c022c3f…` |
| `res/navigation/navigation_feature_quickpay.xml` | 896 | `c17fa79ea8bd6a74…` |
| `res/navigation/navigation_feature_quickpay_guideline.xml` | 900 | `574b25408f271461…` |
| `res/navigation/navigation_feature_resetpin.xml` | 896 | `f6ec691c36e583d0…` |
| `res/navigation/navigation_feature_result_page.xml` | 896 | `7e90a330a1a23655…` |
| `res/navigation/navigation_feature_result_page_v3.xml` | 908 | `ad4f8ec2e37c9b2e…` |
| `res/navigation/navigation_feature_withdraw.xml` | 684 | `5cb59bc11d69eb8d…` |
| `res/navigation/navigation_new.xml` | 3476 | `18c6fffd8cb3601d…` |
| `res/navigation/skew_face_nav_graph.xml` | 1628 | `3d5b4be11a4957b0…` |
| `res/navigation/withdraw_nav_graph.xml` | 3320 | `ba19cf110765e85e…` |
| `res/raw/anim_done_unlink_bank.json` | 6604 | `c1c5f149f051d213…` |
| `res/raw/anim_loading_resume.json` | 3063 | `21f17266a258f2a7…` |
| `res/raw/anim_loading_unlink_bank.json` | 6602 | `81070d6c960f92e5…` |
| `res/raw/anim_location_profile_tab.json` | 747900 | `adbd328115f10709…` |
| `res/raw/anim_otp_popup_verifying.json` | 4051 | `02d8b71186ec48e7…` |
| `res/raw/animation_s5_turn_left.json` | 5892 | `a901b5379929a8f4…` |
| `res/raw/animation_s5_turn_right.json` | 5896 | `60da41896271d636…` |
| `res/raw/failure.riv` | 1448 | `5bbc27560c90d961…` |
| `res/raw/googlemaps.json` | 6286 | `0aed330b922ad913…` |
| `res/raw/h5_bridge.js` | 1875 | `1ced9ab989f0b437…` |
| `res/raw/h5_page_error.html` | 11110 | `c2f4aee4f865b979…` |
| `res/raw/haarcascade_frontalface_default.xml` | 1788328 | `1d794326dc222eee…` |
| `res/raw/ic_anim_zlp_loading_auth_state.json` | 3032 | `78d3f7b80ce10885…` |
| `res/raw/ic_anim_zlp_loading_common_state.json` | 3032 | `78d3f7b80ce10885…` |
| `res/raw/ic_anim_zlp_loading_state.json` | 3032 | `78d3f7b80ce10885…` |
| `res/raw/ic_cashier_loading.json` | 3032 | `78d3f7b80ce10885…` |
| `res/raw/ic_cashier_pay_loading.json` | 27062 | `fce958b20050b8f2…` |
| `res/raw/ic_cashier_pay_loading_v3.json` | 27062 | `fce958b20050b8f2…` |
| `res/raw/ic_home_nav_bar_tab_scan_qr.json` | 36549 | `4531bd83b52ce94d…` |
| `res/raw/ic_rp_fail_v3.json` | 4681 | `d70031634a2f812a…` |
| `res/raw/ic_rp_failed_animate.json` | 11844 | `6d77b07aedf2c2ff…` |
| `res/raw/ic_rp_pending_animate.json` | 23613 | `0234f37443211263…` |
| `res/raw/ic_rp_processing_animate.json` | 13500 | `bf935147138b56bd…` |
| `res/raw/ic_rp_processing_v3.json` | 10774 | `faf7f4f3e84ead8e…` |
| `res/raw/ic_rp_success_animate.json` | 18893 | `0bfdfba730fbce7c…` |
| `res/raw/ic_rp_success_v3.json` | 2959 | `568a951edad9ea92…` |
| `res/raw/img_anim_dynamic_text.json` | 62928 | `120e1ed7faf70476…` |
| `res/raw/img_anim_fds_nfc_guidance_step1.json` | 452303 | `f657591f14e9b83e…` |
| `res/raw/img_anim_fds_nfc_guidance_step2.json` | 323238 | `33963f31c44b44fc…` |
| `res/raw/img_anim_fds_nfc_guidance_step3.json` | 237853 | `80e659d8e2099b7c…` |
| `res/raw/img_anim_loading_state.json` | 8143 | `cc15300d5e463aa5…` |
| `res/raw/img_anim_nfc_guidance_step1.json` | 284974 | `158cdeaa767b7012…` |
| `res/raw/img_anim_nfc_guidance_step2.json` | 138698 | `7084d8e640967efd…` |
| `res/raw/img_anim_nfc_guidance_step3.json` | 60670 | `31069d90b4bf087f…` |
| `res/raw/img_anim_right_arrow.json` | 1630 | `b4d5d2312feb6eca…` |
| `res/raw/img_dynamic_qrcode_anim.json` | 36548 | `9fc7d2f4f4eb9849…` |
| `res/raw/img_flash_capture.json` | 1341 | `228f37b755596f02…` |
| `res/raw/img_guide_back_id_card.json` | 1449629 | `23bbd0bb299f3e21…` |
| `res/raw/img_guide_front_id_card.json` | 555219 | `286bb145da27210e…` |
| `res/raw/img_scan_ocr.json` | 53947 | `b1cb86a7020227de…` |
| `res/raw/img_shield_only.json` | 33935 | `62e8f4494af0d4a3…` |
| `res/raw/img_success_ocr.json` | 33936 | `f07b9b34950af791…` |
| `res/raw/lbfmodel.yaml` | 3778896 | `fd547ca1efe6784c…` |
| `res/raw/loading.riv` | 1729 | `707624a1a54125dd…` |
| `res/raw/lottie_loading_shield.json` | 16290 | `80f09ff18186b633…` |
| `res/raw/lottie_scan.json` | 53947 | `3b56f5d8841ccdcf…` |
| `res/raw/lottie_shield.json` | 20643 | `2241b0ec42a80f92…` |
| `res/raw/lottie_success.json` | 23273 | `c2fb94a79b768bf6…` |
| `res/raw/onboarding_selected_category_marker_lottie.json` | 15458 | `cbfe59361ce1eb07…` |
| `res/raw/onboarding_selecting_categories_guide_lottie.json` | 20112 | `1fe04eeb98571d31…` |
| `res/raw/pay_success_sound.mp3` | 32969 | `186727848f2c3b78…` |
| `res/raw/phone_pending.svg` | 1114729 | `c26a9facfc37bec0…` |
| `res/raw/required_kyc.svg` | 1436457 | `d5097b36b553b5f2…` |
| `res/raw/tap2pay_payment_guide.riv` | 92845 | `7bd9d62a0d95e373…` |
| `res/raw/user_guide.riv` | 63992 | `3a6f8f9779311f17…` |
| `res/raw/zalopay_sound.mp3` | 70163 | `2dbc01add7ca23d2…` |
| `res/xml-v31/game_app_widget_info.xml` | 632 | `fff7a49aa31faba7…` |
| `res/xml-v31/movie_app_widget_info.xml` | 676 | `c58f33937c7f21c0…` |
| `res/xml-v31/personal_qr_app_widget_info.xml` | 540 | `0c6c73395053d281…` |
| `res/xml-v31/standard_app_widget_info.xml` | 540 | `5387002cce19aefe…` |
| `res/xml/apdu_service.xml` | 1280 | `e86657ba85fbb6a4…` |
| `res/xml/app_shortcuts.xml` | 2092 | `ec8a5ec787cccd47…` |
| `res/xml/appsflyer_data_extraction_rules.xml` | 1036 | `d11aa10b9203d2b3…` |
| `res/xml/file_paths.xml` | 1236 | `7cde3f7264cff139…` |
| `res/xml/game_app_widget_info.xml` | 540 | `454af7836a88c1b4…` |
| `res/xml/gma_ad_services_config.xml` | 344 | `ca6c1b9748710329…` |
| `res/xml/griverfilepath.xml` | 300 | `e824a36b3a8b374a…` |
| `res/xml/movie_app_widget_info.xml` | 584 | `abb6c9bc9ef10602…` |
| `res/xml/network_security_config.xml` | 1056 | `408d0b533a2b49eb…` |
| `res/xml/personal_qr_app_widget_info.xml` | 448 | `8220a3e4d45f5441…` |
| `res/xml/splits0.xml` | 588 | `acdd0f7ba3f512f6…` |
| `res/xml/standard_app_widget_info.xml` | 448 | `5eaca58246983748…` |
| `res/xml/tiny_app_apdu_service.xml` | 332 | `b3b37b02b0c52247…` |
| `resources.arsc` | 1802300 | `c1a88cee523bbd97…` |
| `review-ktx.properties` | 56 | `dc47de29d30c4a64…` |
| `review.properties` | 48 | `9a5278881d0e56c9…` |
| `stamp-cert-sha256` | 32 | `1d9c2a02a93816ff…` |
| `text-recognition-bundled-common.properties` | 100 | `5193b078323fd1cb…` |
| `text-recognition.properties` | 70 | `717c7f707b928ff1…` |
| `user-messaging-platform.properties` | 82 | `412f4a89e766d674…` |
| `vision-common.properties` | 64 | `17d5d69b33423ed3…` |
| `vision-interfaces.properties` | 72 | `4de4f31504cb086b…` |
| `vision-internal-vkp.properties` | 76 | `5a7fe8e50cff6db1…` |
| `vn/com/vng/zalopay/logging/events/special_keys_tracking_logs.json` | 2014 | `6aa078479d188fd0…` |

</details>

<details><summary><code>config.xhdpi.apk</code> — 258 files</summary>

| Path | Size (bytes) | SHA-256 |
|---|---:|---|
| `AndroidManifest.xml` | 1032 | `0362e0a50ab88539…` |
| `META-INF/BNDLTOOL.RSA` | 1107 | `b47e51f1d17c34a7…` |
| `META-INF/BNDLTOOL.SF` | 25958 | `e498bed3320c2c8d…` |
| `META-INF/MANIFEST.MF` | 25850 | `864b07054db096d9…` |
| `res/drawable-anydpi-v21/exo_edit_mode_logo.xml` | 4112 | `f34536aa1860bbe0…` |
| `res/drawable-anydpi-v21/exo_ic_audiotrack.xml` | 1120 | `b7e056780bc3ac66…` |
| `res/drawable-anydpi-v21/exo_ic_check.xml` | 700 | `6a89f842c1feafe9…` |
| `res/drawable-anydpi-v21/exo_ic_chevron_left.xml` | 596 | `23a3a9f5d4d0bb03…` |
| `res/drawable-anydpi-v21/exo_ic_chevron_right.xml` | 596 | `f770aa4cb4ff2dca…` |
| `res/drawable-anydpi-v21/exo_ic_forward.xml` | 880 | `8a67f57ecf703fd3…` |
| `res/drawable-anydpi-v21/exo_ic_fullscreen_enter.xml` | 644 | `4e067c2bd505bef2…` |
| `res/drawable-anydpi-v21/exo_ic_fullscreen_exit.xml` | 644 | `1cc006e4cf0b7d5c…` |
| `res/drawable-anydpi-v21/exo_ic_pause_circle_filled.xml` | 656 | `ae7427001b3cae8b…` |
| `res/drawable-anydpi-v21/exo_ic_play_circle_filled.xml` | 640 | `67576f63ebcf2fee…` |
| `res/drawable-anydpi-v21/exo_ic_rewind.xml` | 880 | `42c62877c14cd605…` |
| `res/drawable-anydpi-v21/exo_ic_settings.xml` | 1420 | `3452dbf10ecc8a36…` |
| `res/drawable-anydpi-v21/exo_ic_skip_next.xml` | 584 | `a4ea6bd6979a312d…` |
| `res/drawable-anydpi-v21/exo_ic_skip_previous.xml` | 584 | `d9e33de07cff25a6…` |
| `res/drawable-anydpi-v21/exo_ic_speed.xml` | 1148 | `9da40c349110e8a6…` |
| `res/drawable-anydpi-v21/exo_ic_subtitle_off.xml` | 1212 | `677a067ef02d9f17…` |
| `res/drawable-anydpi-v21/exo_ic_subtitle_on.xml` | 960 | `e8f3cc4950ecd58c…` |
| `res/drawable-anydpi-v21/exo_icon_fastforward.xml` | 588 | `cf4f2f497281d2c0…` |
| `res/drawable-anydpi-v21/exo_icon_repeat_all.xml` | 620 | `92654531f8d8a2d2…` |
| `res/drawable-anydpi-v21/exo_icon_repeat_off.xml` | 620 | `b1907924d2142edc…` |
| `res/drawable-anydpi-v21/exo_icon_repeat_one.xml` | 656 | `87d40a674ee2c743…` |
| `res/drawable-anydpi-v21/exo_icon_rewind.xml` | 600 | `a9e02be9232e4836…` |
| `res/drawable-anydpi-v21/exo_icon_shuffle_off.xml` | 724 | `70fd954dfb4247e2…` |
| `res/drawable-anydpi-v21/exo_icon_shuffle_on.xml` | 724 | `a2bf39390ef00af1…` |
| `res/drawable-anydpi-v21/fingerprint_dialog_fp_icon.xml` | 3612 | `4aaf1cbba3a05380…` |
| `res/drawable-anydpi-v21/griver_core_menu_notification_subscribe.xml` | 1332 | `491e48a64a809ed2…` |
| `res/drawable-anydpi-v21/griver_file_not_support.xml` | 1584 | `6de4a2b0cbb4849a…` |
| `res/drawable-anydpi-v21/griver_file_not_support_night.xml` | 1908 | `1c7e39f88fb48bc0…` |
| `res/drawable-anydpi-v21/griver_file_share_image.xml` | 1124 | `a2d5e69cf8329d1d…` |
| `res/drawable-anydpi-v21/griver_file_share_image_night.xml` | 1540 | `20e85fa9c78e3b30…` |
| `res/drawable-anydpi-v21/griver_image_bucket_triangle_normal.xml` | 1176 | `fb93093f2f07ee3a…` |
| `res/drawable-anydpi-v21/griver_image_bucket_triangle_pressed.xml` | 1208 | `11520de282cff866…` |
| `res/drawable-anydpi-v21/griver_more_right.xml` | 1348 | `a28b8e54010b3026…` |
| `res/drawable-anydpi-v21/griver_ui_filterw.xml` | 2908 | `047519cea9d9e06d…` |
| `res/drawable-anydpi-v21/griver_ui_helpw.xml` | 3576 | `56fb5062bec87009…` |
| `res/drawable-anydpi-v21/griver_ui_ic_header_close.xml` | 1732 | `5b085d4d65592fe5…` |
| `res/drawable-anydpi-v21/griver_ui_ic_header_close_night.xml` | 1588 | `adc57c032ff29443…` |
| `res/drawable-anydpi-v21/griver_ui_icon_corner.xml` | 700 | `a86765ffd33ce5cf…` |
| `res/drawable-anydpi-v21/griver_ui_icon_corner_night.xml` | 908 | `d878ddc58396e197…` |
| `res/drawable-anydpi-v21/griver_ui_infow.xml` | 3268 | `6d9261fc40fb2413…` |
| `res/drawable-anydpi-v21/griver_ui_locatew.xml` | 4884 | `437a9d2bdce59380…` |
| `res/drawable-anydpi-v21/griver_ui_mailw.xml` | 2744 | `3ec5dcba1ec00b85…` |
| `res/drawable-anydpi-v21/griver_ui_plusw.xml` | 3476 | `770c831c652657ce…` |
| `res/drawable-anydpi-v21/griver_ui_richscanw.xml` | 5060 | `d044dfc201ec142d…` |
| `res/drawable-anydpi-v21/griver_ui_searchw.xml` | 2148 | `4e536b1bb9874b4f…` |
| `res/drawable-anydpi-v21/griver_ui_settingsw.xml` | 8744 | `4efa8cc6346e51e4…` |
| `res/drawable-anydpi-v21/griver_ui_sharew.xml` | 3152 | `59d7af7b92b463fa…` |
| `res/drawable-anydpi-v21/griver_ui_title_favorite.xml` | 1536 | `1320f5d5ba407002…` |
| `res/drawable-anydpi-v21/griver_ui_title_favorited.xml` | 2216 | `a59a31e73c180996…` |
| `res/drawable-anydpi-v21/griver_ui_userw.xml` | 2984 | `dbb2032adec02d63…` |
| `res/drawable-anydpi-v21/griver_ui_white_titlebar_more_normal.xml` | 2948 | `025814d2d489e702…` |
| `res/drawable-anydpi-v21/ic_launcher_background.xml` | 960 | `61e1a3a4cb7dae84…` |
| `res/drawable-anydpi-v21/ic_launcher_foreground.xml` | 944 | `e95d8e34f7182020…` |
| `res/drawable-anydpi-v24/acsdk_loading.xml` | 1688 | `1f4adff997f7b9e1…` |
| `res/drawable-anydpi-v24/bg_switch_backside.xml` | 664 | `97bc1e57434cc60f…` |
| `res/drawable-anydpi-v24/fingerprint_dialog_error.xml` | 2656 | `aaaabb1ad8bb72d4…` |
| `res/drawable-anydpi-v24/glance_switch_thumb_off.xml` | 1548 | `9d3b5958707da86e…` |
| `res/drawable-anydpi-v24/glance_switch_thumb_on.xml` | 1536 | `1f2bf29896a53962…` |
| `res/drawable-anydpi-v24/griver_base_ic_image_title_back.xml` | 1400 | `9e779b07a26b4172…` |
| `res/drawable-anydpi-v24/griver_core_menu_about.xml` | 1444 | `e89fc5a9b35cae9f…` |
| `res/drawable-anydpi-v24/griver_core_menu_about_night.xml` | 1444 | `3589cc3f8d75da70…` |
| `res/drawable-anydpi-v24/griver_core_menu_favorites_add.xml` | 2288 | `acba3cf94acb13bc…` |
| `res/drawable-anydpi-v24/griver_core_menu_favorites_error.xml` | 2288 | `5b7e7c8de90ba3ab…` |
| `res/drawable-anydpi-v24/griver_core_menu_favorites_remove.xml` | 2296 | `e9a291f1b65b9a72…` |
| `res/drawable-anydpi-v24/griver_core_menu_feedback.xml` | 2132 | `766733c0d1340c4c…` |
| `res/drawable-anydpi-v24/griver_core_menu_feedback_night.xml` | 2132 | `3fea7269aa3c677f…` |
| `res/drawable-anydpi-v24/griver_core_menu_grv_share.xml` | 1764 | `d5e0f0b37a37812c…` |
| `res/drawable-anydpi-v24/griver_core_menu_notification.xml` | 1224 | `f3cd5839bde0d8dc…` |
| `res/drawable-anydpi-v24/griver_core_menu_reopen.xml` | 1748 | `e32ecabf46f8d5db…` |
| `res/drawable-anydpi-v24/griver_core_menu_reopen_night.xml` | 1748 | `0578cbdb6cca1d21…` |
| `res/drawable-anydpi-v24/griver_core_menu_settings.xml` | 7296 | `94d58aa6f8edc61b…` |
| `res/drawable-anydpi-v24/griver_core_menu_settings_night.xml` | 7296 | `86048ca96bf58072…` |
| `res/drawable-anydpi-v24/griver_core_more_right.xml` | 1284 | `89387a7dde03dbc2…` |
| `res/drawable-anydpi-v24/griver_core_more_right_night.xml` | 1284 | `95e1fa7163899da3…` |
| `res/drawable-anydpi-v24/griver_core_scope_album.xml` | 1376 | `a20cf7a964610dee…` |
| `res/drawable-anydpi-v24/griver_core_scope_album_night.xml` | 1376 | `731a70c6a20eaab7…` |
| `res/drawable-anydpi-v24/griver_core_scope_bluetooth.xml` | 1616 | `a40e01ee97667ed0…` |
| `res/drawable-anydpi-v24/griver_core_scope_bluetooth_night.xml` | 1616 | `61be003ddadfdf09…` |
| `res/drawable-anydpi-v24/griver_core_scope_camera.xml` | 1780 | `5db0018139e78d9d…` |
| `res/drawable-anydpi-v24/griver_core_scope_camera_night.xml` | 1780 | `ab29b19e92972ca4…` |
| `res/drawable-anydpi-v24/griver_core_scope_contact.xml` | 2624 | `5e0fc2f9b81b9208…` |
| `res/drawable-anydpi-v24/griver_core_scope_contact_night.xml` | 2624 | `4ac8e2bf23db5311…` |
| `res/drawable-anydpi-v24/griver_core_scope_location.xml` | 1576 | `671123b08c741e82…` |
| `res/drawable-anydpi-v24/griver_core_scope_location_night.xml` | 1576 | `619066030d2e6daa…` |
| `res/drawable-anydpi-v24/griver_core_scope_microphone.xml` | 1672 | `9d10d999f5bbe718…` |
| `res/drawable-anydpi-v24/griver_core_scope_microphone_night.xml` | 1672 | `ddd5cdc99f542aab…` |
| `res/drawable-anydpi-v24/griver_core_share_copy_link.xml` | 2068 | `1ae1f232b2521d0f…` |
| `res/drawable-anydpi-v24/griver_core_share_copy_link_night.xml` | 2068 | `6fdd79f4b3ecc324…` |
| `res/drawable-anydpi-v24/griver_core_share_messages.xml` | 1740 | `c759e48f5a548e7d…` |
| `res/drawable-anydpi-v24/griver_core_share_messages_night.xml` | 1740 | `3ad9994eee98ecdf…` |
| `res/drawable-anydpi-v24/griver_core_share_more.xml` | 1492 | `ec0b26dbba1695f4…` |
| `res/drawable-anydpi-v24/griver_core_share_more_night.xml` | 1492 | `5228854b07185ad7…` |
| `res/drawable-anydpi-v24/griver_core_toast_exception.xml` | 2248 | `18ac1d485b4657be…` |
| `res/drawable-anydpi-v24/griver_core_toast_false.xml` | 1424 | `535e7e84116d33d7…` |
| `res/drawable-anydpi-v24/griver_core_toast_ok.xml` | 1252 | `ebfb74c72e7b7109…` |
| `res/drawable-anydpi-v24/griver_image_album_item_bg.xml` | 1036 | `61b8fcfbb9652b7a…` |
| `res/drawable-anydpi-v24/griver_image_album_item_bg_night.xml` | 1036 | `61b8fcfbb9652b7a…` |
| `res/drawable-anydpi-v24/griver_operation_core_toast_ok.xml` | 1252 | `ebfb74c72e7b7109…` |
| `res/drawable-anydpi-v24/griver_ui_default_loading_icon.xml` | 3368 | `10d94d9322fccace…` |
| `res/drawable-anydpi-v24/griver_ui_default_loading_icon_night.xml` | 1708 | `3f4669d69338f37b…` |
| `res/drawable-anydpi-v24/griver_ui_ic_header_back.xml` | 1420 | `9e12bbac998dff48…` |
| `res/drawable-anydpi-v24/griver_ui_ic_header_back_night.xml` | 1420 | `ac63c1e0638138f7…` |
| `res/drawable-anydpi-v24/griver_ui_splash_error_caution.xml` | 1508 | `8398e309b0297b3d…` |
| `res/drawable-anydpi-v24/griver_ui_splash_error_caution_night.xml` | 1508 | `ea3e63f8dfb26231…` |
| `res/drawable-anydpi-v24/ic_not_verified_ekyc.xml` | 1432 | `e449a387f3487776…` |
| `res/drawable-anydpi-v24/ic_verified_ekyc.xml` | 1140 | `12ff022cee02cded…` |
| `res/drawable-anydpi-v24/ic_verified_step_ekyc.xml` | 1140 | `385980eb34be8512…` |
| `res/drawable-hdpi-v4/ic_cancel.png` | 198 | `2b88d99ffa8e0495…` |
| `res/drawable-hdpi-v4/ic_delete_sign.webp` | 460 | `230891322ecc72b6…` |
| `res/drawable-ldrtl-xhdpi-v17/abc_spinner_mtrl_am_alpha.9.png` | 417 | `77f0ebab2416404e…` |
| `res/drawable-xhdpi-v4/abc_ab_share_pack_mtrl_alpha.9.png` | 280 | `a04708369abe0920…` |
| `res/drawable-xhdpi-v4/abc_btn_check_to_on_mtrl_000.png` | 281 | `43ccf1f0a5e9e9ed…` |
| `res/drawable-xhdpi-v4/abc_btn_check_to_on_mtrl_015.png` | 432 | `2fa524cda78d7ebd…` |
| `res/drawable-xhdpi-v4/abc_btn_radio_to_on_mtrl_000.png` | 651 | `d19092254aa4133f…` |
| `res/drawable-xhdpi-v4/abc_btn_radio_to_on_mtrl_015.png` | 785 | `02ad0ea6ff5a560e…` |
| `res/drawable-xhdpi-v4/abc_btn_switch_to_on_mtrl_00001.9.png` | 1526 | `1417574134c5e932…` |
| `res/drawable-xhdpi-v4/abc_btn_switch_to_on_mtrl_00012.9.png` | 1731 | `4d2fe45a8169f368…` |
| `res/drawable-xhdpi-v4/abc_cab_background_top_mtrl_alpha.9.png` | 229 | `f65a87efa2f53d6f…` |
| `res/drawable-xhdpi-v4/abc_ic_commit_search_api_mtrl_alpha.png` | 228 | `b84a277807f8f3bb…` |
| `res/drawable-xhdpi-v4/abc_list_divider_mtrl_alpha.9.png` | 167 | `378694706fb7256d…` |
| `res/drawable-xhdpi-v4/abc_list_focused_holo.9.png` | 244 | `671d27e5a4b42111…` |
| `res/drawable-xhdpi-v4/abc_list_longpressed_holo.9.png` | 214 | `727d78fec3f903a2…` |
| `res/drawable-xhdpi-v4/abc_list_pressed_holo_dark.9.png` | 209 | `636d7f24be686e95…` |
| `res/drawable-xhdpi-v4/abc_list_pressed_holo_light.9.png` | 209 | `07bf5ce5ff893725…` |
| `res/drawable-xhdpi-v4/abc_list_selector_disabled_holo_dark.9.png` | 236 | `fd719e497aaf091c…` |
| `res/drawable-xhdpi-v4/abc_list_selector_disabled_holo_light.9.png` | 235 | `cc162d4b8c12ee32…` |
| `res/drawable-xhdpi-v4/abc_menu_hardkey_panel_mtrl_mult.9.png` | 966 | `006f2dc25bacc39e…` |
| `res/drawable-xhdpi-v4/abc_popup_background_mtrl_mult.9.png` | 1544 | `1f7dd8b3521353af…` |
| `res/drawable-xhdpi-v4/abc_scrubber_control_off_mtrl_alpha.png` | 267 | `777a1cf53820aa46…` |
| `res/drawable-xhdpi-v4/abc_scrubber_control_to_pressed_mtrl_000.png` | 267 | `4133830f43f9baa0…` |
| `res/drawable-xhdpi-v4/abc_scrubber_control_to_pressed_mtrl_005.png` | 391 | `29953bd8019dd96b…` |
| `res/drawable-xhdpi-v4/abc_scrubber_primary_mtrl_alpha.9.png` | 208 | `c0f930a722a16df9…` |
| `res/drawable-xhdpi-v4/abc_scrubber_track_mtrl_alpha.9.png` | 198 | `6b2372b414abc1cf…` |
| `res/drawable-xhdpi-v4/abc_spinner_mtrl_am_alpha.9.png` | 448 | `c63a94e7136ceeef…` |
| `res/drawable-xhdpi-v4/abc_switch_track_mtrl_alpha.9.png` | 618 | `468607601140955b…` |
| `res/drawable-xhdpi-v4/abc_tab_indicator_mtrl_alpha.9.png` | 194 | `b8b26708d71b4c7a…` |
| `res/drawable-xhdpi-v4/abc_text_select_handle_left_mtrl.png` | 335 | `83a46279e2f65d85…` |
| `res/drawable-xhdpi-v4/abc_text_select_handle_middle_mtrl.png` | 585 | `3dbbad926d540586…` |
| `res/drawable-xhdpi-v4/abc_text_select_handle_right_mtrl.png` | 318 | `6b45aef8bf43767c…` |
| `res/drawable-xhdpi-v4/abc_textfield_activated_mtrl_alpha.9.png` | 189 | `23439b8899f4b63d…` |
| `res/drawable-xhdpi-v4/abc_textfield_default_mtrl_alpha.9.png` | 187 | `0caaea0060b3772b…` |
| `res/drawable-xhdpi-v4/abc_textfield_search_activated_mtrl_alpha.9.png` | 184 | `5ae9594240ada5e5…` |
| `res/drawable-xhdpi-v4/abc_textfield_search_default_mtrl_alpha.9.png` | 182 | `779a2606a931a399…` |
| `res/drawable-xhdpi-v4/apdu_service_banner.png` | 1956 | `457c2fdea1c64968…` |
| `res/drawable-xhdpi-v4/balance_screen_header.png` | 23161 | `57bf431cad4c0b4b…` |
| `res/drawable-xhdpi-v4/btn_baocao.png` | 1668 | `52db381a59def1f8…` |
| `res/drawable-xhdpi-v4/com_facebook_close.png` | 1919 | `94d33f2bad47deca…` |
| `res/drawable-xhdpi-v4/common_full_open_on_phone.png` | 489 | `e816dc0998f4ee90…` |
| `res/drawable-xhdpi-v4/exo_icon_vr.png` | 375 | `c3aa9130cf52bd20…` |
| `res/drawable-xhdpi-v4/funding_manager_header.png` | 53828 | `30a18aa0d6ca20dd…` |
| `res/drawable-xhdpi-v4/guidance_illustration.png` | 122885 | `aa67ff80633689ee…` |
| `res/drawable-xhdpi-v4/ic_activity_loading.png` | 6570 | `dcfff87dca7c2908…` |
| `res/drawable-xhdpi-v4/ic_app_default.png` | 1172 | `d7e805f98b69add4…` |
| `res/drawable-xhdpi-v4/ic_arrow.png` | 175 | `ba7398854df5a4d2…` |
| `res/drawable-xhdpi-v4/ic_arrow_right.webp` | 276 | `287fb5286b14dcb3…` |
| `res/drawable-xhdpi-v4/ic_ava_default.webp` | 1162 | `1e38ab5b8743f795…` |
| `res/drawable-xhdpi-v4/ic_avatar_default.webp` | 2092 | `3fa072657f33fbc8…` |
| `res/drawable-xhdpi-v4/ic_baoloi.png` | 707 | `71c52a53b6c9600e…` |
| `res/drawable-xhdpi-v4/ic_bell_with_shadow.png` | 6215 | `5c0253124a94ec68…` |
| `res/drawable-xhdpi-v4/ic_checked.png` | 534 | `7b4ffad8cdbaf95f…` |
| `res/drawable-xhdpi-v4/ic_checked_mark.webp` | 866 | `33d5c9f6b1babfb2…` |
| `res/drawable-xhdpi-v4/ic_close.webp` | 424 | `d3a1a31f76ca6d68…` |
| `res/drawable-xhdpi-v4/ic_close_web.png` | 1737 | `22cc71b04d6638c5…` |
| `res/drawable-xhdpi-v4/ic_del.webp` | 558 | `f62f7be329cf5ae0…` |
| `res/drawable-xhdpi-v4/ic_delete_sign_lock_screen.webp` | 610 | `25f54ea29380b6d0…` |
| `res/drawable-xhdpi-v4/ic_dkrt.png` | 4622 | `bf70be7abd2bb8da…` |
| `res/drawable-xhdpi-v4/ic_dot_zp.webp` | 876 | `1c49365d50b584f4…` |
| `res/drawable-xhdpi-v4/ic_email.png` | 487 | `d8da43e0d4c3f587…` |
| `res/drawable-xhdpi-v4/ic_error.png` | 1515 | `4b6840e095a3cbf3…` |
| `res/drawable-xhdpi-v4/ic_expand_more.webp` | 308 | `e970decbc0e29a2a…` |
| `res/drawable-xhdpi-v4/ic_floating_event.webp` | 12328 | `f7a496842640fd46…` |
| `res/drawable-xhdpi-v4/ic_free_transfer_tips.webp` | 13930 | `5fcd1eba852d7238…` |
| `res/drawable-xhdpi-v4/ic_id.png` | 675 | `b0a353e1fd5e86cc…` |
| `res/drawable-xhdpi-v4/ic_key.webp` | 614 | `a5a3e3179e6d2d6a…` |
| `res/drawable-xhdpi-v4/ic_launcher.webp` | 2546 | `db3044e56605932d…` |
| `res/drawable-xhdpi-v4/ic_lichsugd.webp` | 534 | `0969c1ee67727481…` |
| `res/drawable-xhdpi-v4/ic_loading_cloud.png` | 5775 | `0298340692d90b02…` |
| `res/drawable-xhdpi-v4/ic_lock.png` | 575 | `c6591337ae2d6469…` |
| `res/drawable-xhdpi-v4/ic_logo_scanqr.png` | 8625 | `2af1e43753511c0e…` |
| `res/drawable-xhdpi-v4/ic_logo_zalo.webp` | 3518 | `ffd81b940b5340a6…` |
| `res/drawable-xhdpi-v4/ic_logo_zalopay_square.webp` | 2308 | `6628871afa541905…` |
| `res/drawable-xhdpi-v4/ic_logozalopay.webp` | 1302 | `8d80622e543ddc1a…` |
| `res/drawable-xhdpi-v4/ic_mascot_registration_top.webp` | 7444 | `851377c505443e88…` |
| `res/drawable-xhdpi-v4/ic_next.webp` | 280 | `5bf6f28096ca0595…` |
| `res/drawable-xhdpi-v4/ic_no_internet.png` | 2584 | `d6301f821f62c3bb…` |
| `res/drawable-xhdpi-v4/ic_pay_bluetooth_center.webp` | 3270 | `ed2f26d5b42f9e88…` |
| `res/drawable-xhdpi-v4/ic_pay_hand.webp` | 10060 | `f88d2cf33aefe4ee…` |
| `res/drawable-xhdpi-v4/ic_pay_machine.webp` | 4132 | `bbdb7cd9476bb42a…` |
| `res/drawable-xhdpi-v4/ic_pci_dss.webp` | 2316 | `2664644af7b860c5…` |
| `res/drawable-xhdpi-v4/ic_play.png` | 861 | `1ddacec5ae9623c5…` |
| `res/drawable-xhdpi-v4/ic_play_fb.png` | 430 | `c8d6329a89a2f448…` |
| `res/drawable-xhdpi-v4/ic_play_google.png` | 1297 | `d86544bd77e668f4…` |
| `res/drawable-xhdpi-v4/ic_play_zalo.png` | 1730 | `22867decde67992b…` |
| `res/drawable-xhdpi-v4/ic_play_zing.png` | 744 | `589d2b74c3e7fc12…` |
| `res/drawable-xhdpi-v4/ic_protect.png` | 545 | `5f38b3c2447c5d8f…` |
| `res/drawable-xhdpi-v4/ic_quickpay_warning.png` | 4613 | `b5ead965d038f6d0…` |
| `res/drawable-xhdpi-v4/ic_receive_money_with_zalo.webp` | 4770 | `2c323b7105ae7bce…` |
| `res/drawable-xhdpi-v4/ic_receive_money_with_zalopay.webp` | 7118 | `25e95f102ca80389…` |
| `res/drawable-xhdpi-v4/ic_remove_circle.webp` | 558 | `f62f7be329cf5ae0…` |
| `res/drawable-xhdpi-v4/ic_search.webp` | 536 | `4dcfcb4762796fe2…` |
| `res/drawable-xhdpi-v4/ic_search_emty.webp` | 3438 | `95421b307eb8472d…` |
| `res/drawable-xhdpi-v4/ic_search_with_shadow.png` | 5690 | `cdbef9f216423249…` |
| `res/drawable-xhdpi-v4/ic_secure_sockets_layer.webp` | 3260 | `aae7ec0b924eff3d…` |
| `res/drawable-xhdpi-v4/ic_sharezalo.webp` | 934 | `99f088c42a070434…` |
| `res/drawable-xhdpi-v4/ic_success.png` | 1456 | `70cd3f2bfbf4292f…` |
| `res/drawable-xhdpi-v4/ic_switch_camera.webp` | 466 | `55dd52b40fed095f…` |
| `res/drawable-xhdpi-v4/ic_taxi_giatien.png` | 593 | `cc24564e53c7b2b2…` |
| `res/drawable-xhdpi-v4/ic_taxisanbay.png` | 1758 | `8f78f7a6298c3e91…` |
| `res/drawable-xhdpi-v4/ic_touch.png` | 1848 | `6b22b2b182cfb908…` |
| `res/drawable-xhdpi-v4/ic_touch_white.webp` | 1466 | `2eecc611eae60f55…` |
| `res/drawable-xhdpi-v4/ic_transfer_phone_tooltip_arrow.webp` | 3476 | `0ce5a267d2faec04…` |
| `res/drawable-xhdpi-v4/ic_tutorial_link_card.webp` | 12716 | `3d33b4e061ed862a…` |
| `res/drawable-xhdpi-v4/ic_uncheck.png` | 288 | `c65fd901e0fe7044…` |
| `res/drawable-xhdpi-v4/ic_update.png` | 3060 | `c232292989f4410d…` |
| `res/drawable-xhdpi-v4/ic_user.png` | 640 | `735d7d4fd9636923…` |
| `res/drawable-xhdpi-v4/ic_vector_app_payment_pig.webp` | 2120 | `f882f3ebb2d777a6…` |
| `res/drawable-xhdpi-v4/ic_vietcom.webp` | 1986 | `58ffc5c1eaf261f4…` |
| `res/drawable-xhdpi-v4/ic_warning.png` | 618 | `6fc86a3e6b8e7983…` |
| `res/drawable-xhdpi-v4/ic_warning_bell.webp` | 980 | `36673b53da7caa1a…` |
| `res/drawable-xhdpi-v4/ic_warning_rooted.webp` | 20182 | `330fbcbed30896ff…` |
| `res/drawable-xhdpi-v4/ic_webapp_noconnect.png` | 4233 | `700140a4d31e4330…` |
| `res/drawable-xhdpi-v4/ic_webapp_noload.webp` | 4424 | `a43f468f2bf04d4d…` |
| `res/drawable-xhdpi-v4/ic_while_checked.webp` | 258 | `baf537aa04dbf37e…` |
| `res/drawable-xhdpi-v4/ic_zalo.webp` | 1238 | `7e49902365c4392f…` |
| `res/drawable-xhdpi-v4/ic_zalopay.webp` | 2296 | `a9418d5e7445e358…` |
| `res/drawable-xhdpi-v4/ic_zalopay_with_bg.png` | 24951 | `303e6b358ed0f7d9…` |
| `res/drawable-xhdpi-v4/ic_zlp_mascot.webp` | 7876 | `ad640942a48ea4da…` |
| `res/drawable-xhdpi-v4/ic_zp_faq.png` | 709 | `d4dd23bee3b6cf63…` |
| `res/drawable-xhdpi-v4/ic_zp_phone.webp` | 7844 | `ad9857c182b22de7…` |
| `res/drawable-xhdpi-v4/image_wallet_2.png` | 2055 | `2925bbbadc27baf4…` |
| `res/drawable-xhdpi-v4/img_bg_scanning.webp` | 43270 | `4afb5ed549ce7bb7…` |
| `res/drawable-xhdpi-v4/img_cover_large.webp` | 8646 | `fc79406ca63ababf…` |
| `res/drawable-xhdpi-v4/kyc_illustration.png` | 61656 | `32054de1a72e0fda…` |
| `res/drawable-xhdpi-v4/mascot_toro_face_sad.png` | 287748 | `65e55263d206e57f…` |
| `res/drawable-xhdpi-v4/nfc_illustration.png` | 7713 | `85c3e605ae5274b5…` |
| `res/drawable-xhdpi-v4/shortcut_service_mywallet_payqr.png` | 1470 | `15832eb47d7ca8ee…` |
| `res/drawable-xhdpi-v4/shortcut_service_paybill_electric.png` | 1388 | `95f520d54da5bb37…` |
| `res/drawable-xhdpi-v4/shortcut_service_telco_topup.png` | 1454 | `0d8eba285c298253…` |
| `res/drawable-xhdpi-v4/shortcut_service_transfer_money.png` | 1541 | `547fff90b0287f6c…` |
| `res/drawable-xhdpi-v4/taxi_16_px_warning_red.png` | 332 | `faf77344cfd05217…` |
| `res/drawable-xhdpi-v4/taxi_background_white.png` | 536 | `1fa21f4d5e4fbd8c…` |
| `res/drawable-xhdpi-v4/widget_illustration.png` | 35742 | `53804f23b64af2c8…` |
| `res/drawable-xhdpi-v4/zalosdk_icon_back_arrow.png` | 205 | `7c1857533af0721d…` |
| `res/drawable-xhdpi-v4/zalosdk_icon_next.png` | 267 | `6b3fe568161da696…` |
| `res/drawable-xhdpi-v4/zalosdk_icon_pencil.png` | 241 | `2373960bbb83a8b0…` |
| `res/drawable-xhdpi-v4/zalosdk_icon_x.png` | 199 | `576d1ee754c4bb8c…` |
| `res/drawable-xhdpi-v4/zalosdk_radio_button_normal.png` | 444 | `4c83297af311d6ed…` |
| `res/drawable-xhdpi-v4/zalosdk_radio_button_selected.png` | 553 | `3c614b74d8e32d7b…` |
| `res/drawable-xhdpi-v4/zalosdk_register_avatar_overlay.png` | 1705 | `8baa8f8d53daf47b…` |
| `res/drawable-xhdpi-v4/zalosdk_register_avatar_placeholder.png` | 933 | `c587b8ce883dadfd…` |
| `res/drawable-xxxhdpi-v4/ic_cancel.png` | 319 | `de0089f83925f5e8…` |
| `res/drawable-xxxhdpi-v4/ic_delete_sign.webp` | 870 | `61923074aabf9a6b…` |
| `resources.arsc` | 141056 | `a08dc091051d1a6a…` |
| `stamp-cert-sha256` | 32 | `1d9c2a02a93816ff…` |

</details>

<details><summary><code>config.armeabi_v7a.apk</code> — 32 files</summary>

| Path | Size (bytes) | SHA-256 |
|---|---:|---|
| `AndroidManifest.xml` | 1036 | `21643435d0129412…` |
| `META-INF/BNDLTOOL.RSA` | 1107 | `526762c0bfd6ce5e…` |
| `META-INF/BNDLTOOL.SF` | 2667 | `af9b2dc807bca48e…` |
| `META-INF/MANIFEST.MF` | 2559 | `c3e9002db94506b5…` |
| `lib/armeabi-v7a/libVFaceLib.so` | 1963668 | `c6d04f368fe967fb…` |
| `lib/armeabi-v7a/liba97d.so` | 997572 | `7fe7d830805a1e99…` |
| `lib/armeabi-v7a/libandroidx.graphics.path.so` | 7252 | `41399eba6fc2a60f…` |
| `lib/armeabi-v7a/libbarhopper_v3.so` | 3244440 | `b3c162c69e4906b8…` |
| `lib/armeabi-v7a/libc++_shared.so` | 872872 | `91b49f121b312053…` |
| `lib/armeabi-v7a/libcrashlytics-common.so` | 435732 | `4b8bcec5c0464d7b…` |
| `lib/armeabi-v7a/libcrashlytics-handler.so` | 87908 | `f58fca61975d2450…` |
| `lib/armeabi-v7a/libcrashlytics-trampoline.so` | 6912 | `5366393c1292cfb5…` |
| `lib/armeabi-v7a/libcrashlytics.so` | 94540 | `be0dedcd52a35e71…` |
| `lib/armeabi-v7a/libface_detector_v2_jni.so` | 5388224 | `21e48bd253e1c87e…` |
| `lib/armeabi-v7a/libfgcpkjvqe.so` | 6524160 | `5f72893936adfe71…` |
| `lib/armeabi-v7a/libimage_processing_util_jni.so` | 20380 | `e48e356bbe1902ae…` |
| `lib/armeabi-v7a/libimagepipeline.so` | 5876 | `2049c770f0dcca26…` |
| `lib/armeabi-v7a/libljsfrcwmw.so` | 600296 | `12a389806114a18f…` |
| `lib/armeabi-v7a/liblytcuhe.so` | 178628 | `a8e59c1d4922f6bc…` |
| `lib/armeabi-v7a/libmlkit_google_ocr_pipeline.so` | 6781940 | `06b9e2f587e401c6…` |
| `lib/armeabi-v7a/libmlkitcommonpipeline.so` | 6928228 | `9ab65cd53ed8334f…` |
| `lib/armeabi-v7a/libnative-filters.so` | 12588 | `b8a2da1df4f33eb8…` |
| `lib/armeabi-v7a/libnative-imagetranscoder.so` | 341072 | `43280a8f3f33529d…` |
| `lib/armeabi-v7a/libohxbee.so` | 155412 | `2d54a2aa85d81dbf…` |
| `lib/armeabi-v7a/libpicodet_ncnn.so` | 2992584 | `442367c2e7f3e5b5…` |
| `lib/armeabi-v7a/librcmefqonnx.so` | 178628 | `9120dd02a8d94659…` |
| `lib/armeabi-v7a/librive-android.so` | 3225636 | `ab2dd352553cafb7…` |
| `lib/armeabi-v7a/libsecurity_lite.so` | 154840 | `75c5d69621c5dce9…` |
| `lib/armeabi-v7a/libsqlcipher.so` | 3555080 | `819c11750f332a8c…` |
| `lib/armeabi-v7a/libtaibmcf.so` | 12617752 | `0fba080d0364fc0f…` |
| `lib/armeabi-v7a/libtrueidlib.so` | 79904 | `e1a98166f637ebeb…` |
| `stamp-cert-sha256` | 32 | `1d9c2a02a93816ff…` |

</details>

<details><summary><code>config.en.apk</code> — 6 files</summary>

| Path | Size (bytes) | SHA-256 |
|---|---:|---|
| `AndroidManifest.xml` | 1000 | `487916059202bbe8…` |
| `META-INF/BNDLTOOL.RSA` | 1107 | `5b6a3bd1334bb205…` |
| `META-INF/BNDLTOOL.SF` | 342 | `8f6822e16939c235…` |
| `META-INF/MANIFEST.MF` | 234 | `3de5c200f3a183b3…` |
| `resources.arsc` | 61984 | `45a65e4c3807ce6c…` |
| `stamp-cert-sha256` | 32 | `1d9c2a02a93816ff…` |

</details>

<details><summary><code>config.vi.apk</code> — 6 files</summary>

| Path | Size (bytes) | SHA-256 |
|---|---:|---|
| `AndroidManifest.xml` | 1000 | `b1193ac0a77cf715…` |
| `META-INF/BNDLTOOL.RSA` | 1107 | `c6fd0d6fb831fe29…` |
| `META-INF/BNDLTOOL.SF` | 342 | `51268105f82b491e…` |
| `META-INF/MANIFEST.MF` | 234 | `34b95272eef546e3…` |
| `resources.arsc` | 75460 | `98bc5c2cd2350575…` |
| `stamp-cert-sha256` | 32 | `1d9c2a02a93816ff…` |

</details>

<details><summary><code>XAPK_ROOT</code> — 2 files</summary>

| Path | Size (bytes) | SHA-256 |
|---|---:|---|
| `icon.png` | 23841 | `f0b9d7d1a6144ca5…` |
| `manifest.json` | 2710 | `1382fb85261a3f58…` |

</details>

## 3. Main APK Static Analysis (androguard)

| Field | Value |
|---|---|
| package | vn.com.vng.zalopay |
| version_name | 11.17.0 |
| version_code | 1102758 |
| min_sdk | 26 |
| target_sdk | 36 |
| is_signed | True |
| is_signed_v1 | False |
| is_signed_v2 | True |
| is_signed_v3 | True |
| file_count | 6162 |
| strings_total | 374276 |
| class_count | 60671 |
| SHA-256 | `e289d547b45f6224aed8ce7bf72d75ea5545cd50c0e768d1a74a3e8165fe6a03` |
| Size | 79359087 |

### 3.1 DEX Files
| DEX | Classes | Methods | Fields | Strings | Size |
|---|---:|---:|---:|---:|---:|
| `classes.dex` | 10703 | 59948 | 46956 | 61218 | 8831004 |
| `classes10.dex` | 92 | 578 | 314 | 920 | 78204 |
| `classes11.dex` | 177 | 1171 | 694 | 1584 | 163420 |
| `classes12.dex` | 425 | 2604 | 1723 | 3207 | 398276 |
| `classes13.dex` | 139 | 997 | 561 | 1381 | 145824 |
| `classes14.dex` | 90 | 575 | 371 | 961 | 81748 |
| `classes15.dex` | 113 | 658 | 410 | 1005 | 82152 |
| `classes16.dex` | 20 | 101 | 38 | 261 | 15916 |
| `classes17.dex` | 243 | 1311 | 972 | 1847 | 208476 |
| `classes18.dex` | 115 | 668 | 428 | 1094 | 95920 |
| `classes19.dex` | 777 | 3781 | 2804 | 4159 | 719548 |
| `classes2.dex` | 7421 | 45527 | 60000 | 56948 | 16516960 |
| `classes3.dex` | 1081 | 6354 | 3619 | 8010 | 850600 |
| `classes4.dex` | 11441 | 59997 | 40269 | 52812 | 9418124 |
| `classes5.dex` | 7983 | 49055 | 60002 | 56424 | 7357484 |
| `classes6.dex` | 5475 | 49201 | 36617 | 55274 | 7612432 |
| `classes7.dex` | 12953 | 60000 | 43164 | 59110 | 10803680 |
| `classes8.dex` | 295 | 1552 | 1056 | 1906 | 239036 |
| `classes9.dex` | 1128 | 7170 | 4520 | 6155 | 1218908 |

### 3.2 Signing Certificates
### 3.3 Permissions
Total: **49**

- `android.permission.ACCESS_ADSERVICES_AD_ID`
- `android.permission.ACCESS_ADSERVICES_ATTRIBUTION`
- `android.permission.ACCESS_ADSERVICES_CUSTOM_AUDIENCE`
- `android.permission.ACCESS_ADSERVICES_TOPICS`
- `android.permission.ACCESS_COARSE_LOCATION`
- `android.permission.ACCESS_FINE_LOCATION`
- `android.permission.ACCESS_NETWORK_STATE`
- `android.permission.ACCESS_WIFI_STATE`
- `android.permission.CAMERA`
- `android.permission.CHANGE_WIFI_STATE`
- `android.permission.DETECT_SCREEN_CAPTURE`
- `android.permission.FOREGROUND_SERVICE`
- `android.permission.INTERNET`
- `android.permission.MODIFY_AUDIO_SETTINGS`
- `android.permission.NFC`
- `android.permission.POST_NOTIFICATIONS`
- `android.permission.QUERY_ALL_PACKAGES`
- `android.permission.READ_BASIC_PHONE_STATE`
- `android.permission.READ_CALENDAR`
- `android.permission.READ_CONTACTS`
- `android.permission.READ_EXTERNAL_STORAGE`
- `android.permission.READ_MEDIA_AUDIO`
- `android.permission.READ_PHONE_STATE`
- `android.permission.RECEIVE_BOOT_COMPLETED`
- `android.permission.RECORD_AUDIO`
- `android.permission.REORDER_TASKS`
- `android.permission.USE_BIOMETRIC`
- `android.permission.USE_FINGERPRINT`
- `android.permission.VIBRATE`
- `android.permission.WAKE_LOCK`
- `android.permission.WRITE_CALENDAR`
- `android.permission.WRITE_CONTACTS`
- `android.permission.WRITE_EXTERNAL_STORAGE`
- `android.settings.action.MANAGE_OVERLAY_PERMISSION`
- `com.android.launcher.permission.INSTALL_SHORTCUT`
- `com.android.launcher.permission.READ_SETTINGS`
- `com.android.launcher.permission.UNINSTALL_SHORTCUT`
- `com.android.launcher.permission.WRITE_SETTINGS`
- `com.android.vending.BILLING`
- `com.bbk.launcher2.permission.READ_SETTINGS`
- `com.bbk.launcher2.permission.WRITE_SETTINGS`
- `com.google.android.c2dm.permission.RECEIVE`
- `com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE`
- `com.google.android.gms.permission.AD_ID`
- `com.huawei.appmarket.service.commondata.permission.GET_COMMON_DATA`
- `com.samsung.android.mapsagent.permission.READ_APP_INFO`
- `com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY`
- `com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION`
- `vn.com.vng.zalopay.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`

### 3.4 Components
#### Activities (200)
- `amcsetxek.dmayljy`
- `androidx.compose.ui.tooling.PreviewActivity`
- `androidx.glance.appwidget.action.ActionTrampolineActivity`
- `androidx.glance.appwidget.action.InvisibleActionTrampolineActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$BootstrapActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyActivity`
- `androidx.test.core.app.InstrumentationActivityInvoker$EmptyFloatingActivity`
- `com.alibaba.griver.core.keepalive.GriverProxyActivity`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back1`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back2`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back3`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back4`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back5`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Back6`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite1`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite2`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Lite3`
- `com.alibaba.griver.core.ui.activity.GriverBaseActivity$Main`
- `com.alibaba.griver.core.ui.activity.GriverFileChooserActivity`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back1`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back2`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back3`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back4`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back5`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Back6`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite1`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite2`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Lite3`
- `com.alibaba.griver.core.ui.activity.GriverTransActivity$Main`
- `com.alibaba.griver.device.ui.GriverOpenSettingActivity`
- `com.alibaba.griver.file.ui.FileViewerActivity`
- `com.alibaba.griver.image.activity.GriverCaptureActivity`
- `com.alibaba.griver.image.activity.GriverCapturePreviewActivity`
- `com.alibaba.griver.image.activity.GriverPhotoPreviewActivity`
- `com.alibaba.griver.image.activity.GriverPhotoSelectActivity`
- `com.alibaba.griver.image.activity.GriverVideoPreviewActivity`
- `com.alibaba.griver.ui.jsapi.picker.nebula.wheelview.multilevel.MultilevelSelectActivity`
- `com.android.billingclient.api.ProxyBillingActivity`
- `com.android.billingclient.api.ProxyBillingActivityV2`
- `com.facebook.CustomTabActivity`
- `com.facebook.CustomTabMainActivity`
- `com.facebook.FacebookActivity`
- `com.facebook.ads.AudienceNetworkActivity`
- `com.fpt.fci.ekycfull.presentation.view.EkycActivity`
- `com.google.android.gms.ads.AdActivity`
- `com.google.android.gms.ads.NotificationHandlerActivity`
- `com.google.android.gms.ads.OutOfContextTestingActivity`
- `com.google.android.gms.common.api.GoogleApiActivity`
- `com.google.android.gms.tagmanager.TagManagerPreviewActivity`
- `com.google.android.play.core.common.PlayCoreDialogWrapperActivity`
- `com.iap.ac.android.acs.plugin.downgrade.ui.ErrorPageActivity`
- `com.iap.android.mppclient.container.activity.ACContainerActivity`
- `com.iap.android.mppclient.container.activity.H5NetworkCheckActivity`
- `com.karumi.dexter.DexterActivity`
- `com.vnpay.vntaxi.ui.activities.DetailBookTaxiActivity`
- `com.vnpay.vntaxi.ui.activities.TaxiBrowserActivity`
- `com.vnpay.vntaxi.ui.activities.TaxiWebviewLinkActivity`
- `com.vnpay.vntaxi.ui.activities.address.SelectAddressActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.ConfirmOnMapActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.SearchAddressActivity`
- `com.vnpay.vntaxi.ui.activities.address.search.SearchOnMapActivity`
- `com.vnpay.vntaxi.ui.activities.address.setting.AddressSettingActivity`
- `com.vnpay.vntaxi.ui.activities.address.setting.ChooseAddressActivity`
- `com.vnpay.vntaxi.ui.activities.address.setting.DetailAddressActivity`
- `com.vnpay.vntaxi.ui.activities.airport.TaxiAirportActivity`
- `com.vnpay.vntaxi.ui.activities.book.BookActivity`
- `com.vnpay.vntaxi.ui.activities.history.DetailHistoryTaxiActivity`
- `com.vnpay.vntaxi.ui.activities.history.TaxiHistoryActivity`
- `com.vnpay.vntaxi.ui.activities.insurance.TaxiInsuranceActivity`
- `com.vnpay.vntaxi.ui.activities.note.invoice.ExtractInvoiceActivity`
- `com.vnpay.vntaxi.ui.activities.note.note.ClientNoteActivity`
- `com.vnpay.vntaxi.ui.activities.report.FastReportTaxiActivity`
- `com.vnpay.vntaxi.ui.activities.selectpartner.TaxiBrandActivity`
- `com.vnpay.vntaxi.ui.activities.splash.SplashActivity`
- `com.vnpay.vntaxi.ui.activities.voucher.detail.VoucherDetailActivity`
- `com.vnpay.vntaxi.ui.activities.voucher.list.VoucherListActivity`
- `com.vnpay.vntaxi.ui.fragments.book.TaxiHelpMethodActivity`
- `com.zing.zalo.zalosdk.oauth.OpenAPIActivity`
- `com.zing.zalo.zalosdk.oauth.WebLoginActivity`
- `vn.ai.trueid.sdk.activity.SotpConfirmActivity`
- `vn.ai.trueid.sdk.activity.TrueIDActivity`
- `vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity`
- `vn.ai.trueid.sdk.facev3.present.presentation.LivenessCameraXActivity`
- `vn.com.vn.zalopay.upgrade_user_profile.presentation.ReformKycActivity`
- `vn.com.vng.resultpage.presentation.newui.ResultPageNewUIActivity`
- `vn.com.vng.resultpage.presentation.newuinative.ResultPageNativeActivity`
- `vn.com.vng.resultpage.presentation.ui.activity.ResultPageV2Activity`
- `vn.com.vng.resultpage.presentation.ui.webview.RPWebActivity`
- `vn.com.vng.zalopay.alipay.presentation.AlipayEmptyScanQRActivity`
- `vn.com.vng.zalopay.alipay.presentation.dialog.AlipayErrorDialogActivity`
- `vn.com.vng.zalopay.appinfo.AppInfoActivity`
- `vn.com.vng.zalopay.bankbinding.presentation.ui.activity.BankBindingActivity`
- `vn.com.vng.zalopay.bankbinding.presentation.ui.activity.BankMediaWebViewActivity`
- `vn.com.vng.zalopay.bankbinding.presentation.ui.activity.FundManagementActivity`
- `vn.com.vng.zalopay.behaviorguideline.LoginGuidelineActivity`
- `vn.com.vng.zalopay.camera.CustomDefaultCamera`
- `vn.com.vng.zalopay.cashier.buffer.CashierBufferActivity`
- `vn.com.vng.zalopay.cashier.middle.CashierMiddlePageActivity`
- `vn.com.vng.zalopay.cashier.presentation.newui.CashierNewUIActivity`
- `vn.com.vng.zalopay.cashier.presentation.ui.main.MainCashierV2Activity`
- `vn.com.vng.zalopay.cashier.presentation.ui.otp.DOtpErrorActivity`
- `vn.com.vng.zalopay.cashier.presentation.ui.web.activity.CashierWebActivity`
- `vn.com.vng.zalopay.cashier_v3.presentation.main.activity.CashierV3Activity`
- `vn.com.vng.zalopay.cashin.presentation.ui.activity.CashInActivity`
- `vn.com.vng.zalopay.changepin.presentation.ui.activity.ChangePinActivity`
- `vn.com.vng.zalopay.clearing.ClearingAccountActivity`
- `vn.com.vng.zalopay.closeaccount.presentation.ui.CloseAccountActivity`
- `vn.com.vng.zalopay.core.ui.activity.TutorialConnectInternetActivity`
- `vn.com.vng.zalopay.feature.ui.FeatureModuleRoutingActivity`
- `vn.com.vng.zalopay.home_more.presentation.ui.home_more.HomeMoreActivity`
- `vn.com.vng.zalopay.homev4.presentation.ui.onboarding.HomeOnBoardingActivity`
- `vn.com.vng.zalopay.homev4.presentation.ui.recommendsystem.RecommendSystemActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.ekyc_intro.EkycIntroActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.onboarding.OnboardingActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.routing.HomeRoutingActivity`
- `vn.com.vng.zalopay.homev5.presenter.ui.story_collections_player.StoryCollectionsPlayerActivity`
- `vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity`
- `vn.com.vng.zalopay.liveness.presentation.LivenessVerificationActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.FaceLivenessCameraXActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.idcard.IdentityCardCaptureActivity`
- `vn.com.vng.zalopay.livenessdetection.presentation.qrcode.QRCodeScannerActivity`
- `vn.com.vng.zalopay.lockscreen.LockScreenActivity`
- `vn.com.vng.zalopay.logindevice.DeviceVerificationActivity`
- `vn.com.vng.zalopay.logindevice.LoginDevicesActivity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.login.LoginActivity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.otp.OtpVerificationActivity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.upgradeuserk0k1.UpgradeUserK0K1Activity`
- `vn.com.vng.zalopay.loginv2.presentation.ui.webview.LoginWebViewActivity`
- `vn.com.vng.zalopay.miniapp.error.NoWebViewSdkActivity`
- `vn.com.vng.zalopay.miniapp.ui.MiniAppActivity`
- `vn.com.vng.zalopay.my_qr.MyQRActivity`
- `vn.com.vng.zalopay.nfc.FundingSolutionNFCActivity`
- `vn.com.vng.zalopay.passport.ForceUpdatePhoneActivity`
- `vn.com.vng.zalopay.passport.LockAccountActivity`
- `vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity`
- `vn.com.vng.zalopay.pdfviewer.PdfViewerActivity`
- `vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity`
- `vn.com.vng.zalopay.permission.notification.presentation.NotificationPermissionActivity`
- `vn.com.vng.zalopay.profile_tab_v2.presentation.ui.account.AccountHostActivity`
- `vn.com.vng.zalopay.profile_tab_v2.presentation.ui.privacy.PrivacyHubActivity`
- `vn.com.vng.zalopay.protect.ui.ProtectAccountActivity`
- `vn.com.vng.zalopay.quickpay_native.presentation.ui.QuickPayNativeActivity`
- `vn.com.vng.zalopay.receive_money.ReceiveMoneyActivity`
- `vn.com.vng.zalopay.reformkyc.presentation.ui.ReformKycActivity`
- `vn.com.vng.zalopay.resetpin.presentation.ui.activity.ResetPinActivity`
- `vn.com.vng.zalopay.result_page_v3.presentation.main.activity.ResultPageV3Activity`
- `vn.com.vng.zalopay.scancode.presentation.ScanCodeActivity`
- `vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2`
- `vn.com.vng.zalopay.search.presentation.ui.search.SearchActivity`
- `vn.com.vng.zalopay.shareqr.presentation.QRReceiverActivity`
- `vn.com.vng.zalopay.skewface.presentation.activity.SkewFaceActivity`
- `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`
- `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenGooglePaymentActivity`
- `vn.com.vng.zalopay.storage_management.presentation.StorageManagementActivity`
- `vn.com.vng.zalopay.tenpay.TPGPaymentActivity`
- `vn.com.vng.zalopay.tenpay.image_preview.ImagePreviewActivity`
- `vn.com.vng.zalopay.ui.activity.HomeActivity`
- `vn.com.vng.zalopay.ui.balance.activity.BalanceActivity`
- `vn.com.vng.zalopay.updatephone.presentation.ui.UpdatePhoneActivity`
- `vn.com.vng.zalopay.user_profile.presentation.additionalinfo.AdditionalProfileInfoActivity`
- `vn.com.vng.zalopay.user_profile.presentation.camera.CameraAutoRotationImage`
- `vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.EditAvatarAndNameActivity`
- `vn.com.vng.zalopay.user_profile.presentation.profiledetails.ProfileDetailsActivity`
- `vn.com.vng.zalopay.user_profile.presentation.profiledetailv2.ProfileDetailsV2Activity`
- `vn.com.vng.zalopay.user_profile.presentation.protectaccount.AccountSecurityActivity`
- `vn.com.vng.zalopay.user_profile.presentation.umminiapps.DebugAuditActivity`
- `vn.com.vng.zalopay.user_profile.presentation.umminiapps.UMMiniAppActivity`
- `vn.com.vng.zalopay.webapp.activity.ManualResetPinWebAppActivity`
- `vn.com.vng.zalopay.webapp.activity.UpdatePhoneWebAppActivity`
- `vn.com.vng.zalopay.webapp.activity.WebAppExternalActivity`
- `vn.com.vng.zalopay.webview.ui.BalanceLiquidationWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.HomeSearchWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.ResetPinWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.VerificationAtBankCounterWebViewActivity`
- `vn.com.vng.zalopay.webview.ui.WebViewActivity`
- `vn.com.vng.zalopay.webview.ui.update_personal_info.UpdatePersonalInfoWebViewActivity`
- `vn.com.vng.zalopay.withdraw.presentation.ui.activity.WithdrawActivity`
- `vn.com.zalopay.auth.presentation.faceauth.FaceAuthActivity`
- `vn.com.zalopay.auth.presentation.faceauth.components.selfdev.ui.SelfDevCameraActivity`
- `vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity`
- `vn.com.zalopay.auth.presentation.otp.OTPChallengeActivity`
- `vn.com.zalopay.auth.presentation.otp.popup.OTPChallengeRootActivity`
- `vn.com.zalopay.auth.presentation.smartotp.SmartOTPActivity`
- `vn.com.zalopay.balance_liquidation.presentation.BalanceLiquidationActivity`
- `vn.com.zalopay.ce_ticket.presentation.ui.CETicketContainerActivity`
- `vn.com.zalopay.ekycpro.presentation.bca.ui.activity.BcaActivity`
- `vn.com.zalopay.ekycpro.presentation.guardian_consent.GuardianInfoActivity`
- `vn.com.zalopay.ekycpro.presentation.main.MainEKycActivity`
- `vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity`
- `vn.com.zalopay.ekycpro.presentation.selfie.SelfieActivity`
- `vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity`
- `vn.com.zalopay.nfc_sdk.nfc_in_house.BottomSheetNFC`
- `vn.com.zalopay.owner_verification.presentation.ui.OVContainerViewActivity`
- `vn.com.zalopay.reset_pin.presentation.pin_creation.CreatePinActivity`
- `vn.com.zalopay.reset_pin.presentation.ui.ResetPinContainerViewActivity`
- `vn.zalopay.ui.um_custom_loading.CenterLoadingActivity`
- `vn.zalopay.ui.um_custom_loading.FullScreenLoadingActivity`
- `yydamachy.xqcntsk`

#### Services (34)
- `androidx.camera.core.impl.MetadataHolderService`
- `androidx.core.widget.RemoteViewsCompatService`
- `androidx.glance.appwidget.GlanceRemoteViewsService`
- `androidx.room.MultiInstanceInvalidationService`
- `androidx.work.impl.background.systemalarm.SystemAlarmService`
- `androidx.work.impl.background.systemjob.SystemJobService`
- `androidx.work.impl.foreground.SystemForegroundService`
- `com.alibaba.ariver.commonability.device.jsapi.nfc.service.TinyAppHostApduService`
- `com.alibaba.ariver.ipc.RemoteCallService`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService1`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService2`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService3`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService4`
- `com.alibaba.exthub.bigdata.service.BigDataLiteService$BigDataLiteService5`
- `com.alibaba.exthub.bigdata.service.BigDataMainService`
- `com.alibaba.griver.base.resource.predownload.AppPreDownloadJobSchedulerService`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite1`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite2`
- `com.alibaba.griver.core.kernel.ipc.client.IpcMsgClientService$Lite3`
- `com.alibaba.griver.core.kernel.ipc.server.IpcMsgServerService`
- `com.alibaba.griver.device.jsapi.nfc.service.TinyAppHostApduService`
- `com.google.android.datatransport.runtime.backends.TransportBackendDiscovery`
- `com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService`
- `com.google.android.gms.ads.AdService`
- `com.google.android.gms.measurement.AppMeasurementJobService`
- `com.google.android.gms.measurement.AppMeasurementService`
- `com.google.android.gms.tagmanager.TagManagerService`
- `com.google.firebase.components.ComponentDiscoveryService`
- `com.google.firebase.messaging.FirebaseMessagingService`
- `com.google.firebase.sessions.SessionLifecycleService`
- `com.google.mlkit.common.internal.MlKitComponentDiscoveryService`
- `com.vnpay.vntaxi.base.gps.FusedLocationService`
- `vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService`
- `vn.com.vng.zalopay.notification.MyFcmListenerService`

#### Receivers (22)
- `androidx.glance.appwidget.MyPackageReplacedReceiver`
- `androidx.glance.appwidget.UnmanagedSessionReceiver`
- `androidx.glance.appwidget.action.ActionCallbackBroadcastReceiver`
- `androidx.profileinstaller.ProfileInstallReceiver`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy`
- `androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver`
- `androidx.work.impl.background.systemalarm.RescheduleReceiver`
- `androidx.work.impl.diagnostics.DiagnosticsReceiver`
- `androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver`
- `com.facebook.AuthenticationTokenManager$CurrentAuthenticationTokenChangedBroadcastReceiver`
- `com.facebook.CurrentAccessTokenExpirationBroadcastReceiver`
- `com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerSchedulerBroadcastReceiver`
- `com.google.android.gms.measurement.AppMeasurementReceiver`
- `com.google.firebase.iid.FirebaseInstanceIdReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.game.GameAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.movie.MovieAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQRAppWidgetReceiver`
- `vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardAppWidgetReceiver`
- `vn.com.vng.zalopay.receivers.ZPInstallBroadcastReceiver`

#### Providers (9)
- `androidx.core.content.FileProvider`
- `androidx.startup.InitializationProvider`
- `com.alibaba.griver.file.utils.GriverFileProvider`
- `com.facebook.ads.AudienceNetworkContentProvider`
- `com.facebook.internal.FacebookInitProvider`
- `com.google.android.gms.ads.MobileAdsInitProvider`
- `com.google.firebase.perf.provider.FirebasePerfProvider`
- `com.google.firebase.provider.FirebaseInitProvider`
- `com.google.mlkit.common.internal.MlKitInitProvider`

**Main activity:** `vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2`

### 3.5 Top Packages (by class count)
| Package prefix | Classes |
|---|---:|
| `com.google.android` | 6465 |
| `com.alibaba.ariver` | 2415 |
| `com.alibaba.griver` | 2228 |
| `org.bouncycastle.jcajce` | 1758 |
| `com.iap.ac` | 1330 |
| `org.bouncycastle.pqc` | 975 |
| `vn.com.vng` | 958 |
| `com.visa.cbp` | 900 |
| `org.bouncycastle.crypto` | 836 |
| `org.bouncycastle.asn1` | 748 |
| `vn.ai.trueid` | 622 |
| `androidx.media3.exoplayer` | 538 |
| `com.vnpay.vntaxi` | 377 |
| `com.appsflyer.internal` | 364 |
| `com.nimbusds.jose` | 360 |
| `com.visa.visapaysdk` | 337 |
| `org.bouncycastle.oer` | 315 |
| `androidx.media3.extractor` | 309 |
| `androidx.camera.core` | 306 |
| `com.xq.cxzmoiwuegvp` | 299 |
| `com.google.common` | 297 |
| `com.iap.android` | 269 |
| `org.bouncycastle.math` | 264 |
| `androidx.compose.ui` | 261 |
| `io.reactivex.internal` | 251 |
| `org.bouncycastle.cms` | 240 |
| `androidx.compose.foundation` | 234 |
| `androidx.camera.camera2` | 234 |
| `com.alipay.plus` | 230 |
| `com.google.firebase` | 221 |
| `com.google.crypto` | 221 |
| `com.bumptech.glide` | 213 |
| `com.google.protobuf` | 209 |
| `com.google.gson` | 207 |
| `androidx.media3.common` | 202 |
| `vn.zalopay.ui` | 186 |
| `androidx.appcompat.widget` | 178 |
| `com.airbnb.lottie` | 173 |
| `androidx.glance.appwidget` | 170 |
| `com.facebook.ads` | 166 |
| `androidx.camera.video` | 159 |
| `org.bouncycastle.cert` | 158 |
| `app.rive.runtime` | 157 |
| `org.jmrtd.lds` | 151 |
| `androidx.datastore.preferences` | 140 |
| `org.bouncycastle.operator` | 135 |
| `org.bouncycastle.jce` | 131 |
| `androidx.core.view` | 127 |
| `androidx.fragment.app` | 115 |
| `org.jsoup.parser` | 110 |
| `androidx.work.impl` | 108 |
| `androidx.recyclerview.widget` | 106 |
| `com.alibaba.exthub` | 102 |
| `vn.com.zalopay` | 95 |
| `com.facebook.imagepipeline` | 94 |
| `com.alipay.android` | 92 |
| `com.caverock.androidsvg` | 91 |
| `com.android.billingclient` | 88 |
| `org.bouncycastle.util` | 82 |
| `com.squareup.picasso` | 81 |
| `kotlin.reflect.jvm` | 78 |
| `com.alibaba.fastjson` | 76 |
| `com.tiktok.appevents` | 76 |
| `com.visa.visapay` | 72 |
| `vn.ai.faceauth` | 67 |
| `com.alipay.iap` | 66 |
| `com.karumi.dexter` | 65 |
| `com.facebook.internal` | 62 |
| `androidx.media3.ui` | 61 |
| `com.visa.hcesdk` | 61 |
| `org.ejbca.cvc` | 61 |
| `androidx.core.app` | 59 |
| `android.support.v4` | 59 |
| `net.zetetic.database` | 57 |
| `androidx.appcompat.app` | 56 |
| `androidx.camera.view` | 55 |
| `org.bouncycastle.its` | 54 |
| `androidx.media3.datasource` | 53 |
| `org.bouncycastle.pkcs` | 53 |
| `androidx.camera.extensions` | 52 |

### 3.6 Sensitive String Findings (sampled)
#### urls (300 matches, capped)
```
'
See https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread
Cleartext HTTP traffic not permitted. See https://developer.android.com/guide/topics/media/issues/cleartext-not-permitted
Missing call to onListPresentable after new list was presented. If you are seeing
 this exception, it is generally an indication of an issue with Paging.
 Please file a bug so we can fix it at:
 https://issuetracker.google.com/issues/new?component=413106
Package in android.resource:// URI not found. Check http://g.co/dev/packagevisibility.
Paging received a refresh event in the middle of an actively loading generation
of PagingData. If you see this exception, it is most likely a bug in the library.
Please file a bug so we can fix it at:
https://issuetracker.google.com/issues/new?component=413106
This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/297974033 mainAxisMax 
This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax 
http://ns.adobe.com/xap/1.0/
http://ns.adobe.com/xap/1.0/ 
http://xmlpull.org/v1/doc/features.html#process-namespaces
https://a.alipayobjects.com/bridgeapi/1.0/jsready.js
https://alipay.com/h5container/un_safe.html
https://alipay.kylinBridge
https://appx
https://appx-ng/
https://appx-ng/af-appx.min.js
https://appx-ng/af-appx.worker.min.js
https://appx-ng/index.prerender.html
https://appx/
https://appx/af-appx.min.js
https://appx/af-appx.worker.min.js
https://cdn.marmot-cloud.com/page/g_diagnostic_tool/index.html
https://cdn.marmot-cloud.com/page/miniprogram-about/index.html
https://gw.alipayobjects.com/mdn/rms_ef981d/afts/img/A*qpA3TKXchKUAAAAAAAAAAABkARQnAQ
https://hpmweb.alipay.com/report/android/batch
https://mobilegw.alipay.com/mgw.htm
https://play.google.com/store/apps/details?id=vn.com.vng.zalopay
https://render.alipay.com/p/c/
https://render.alipay.com/p/s/h5misc/resource_error?url=
https://resource/
https://shared
https://simg.zalopay.com.vn/zst/zpi-spa/um/kyc/images/error.png
https://usr
https://usr/
https://zalo.me/s/3888182390069527250/?
https://zalopay.vn/quy-dinh/chinh-sach-bao-ve-quyen-rieng-tu-hop-nhat
https://zalopay.vn/quy-dinh/thoa-thuan-su-dung-zalopay
未配置域名白名单，无权访问域名。查看如何配置：https://opendocs.alipay.com/mini/api/owycmh#%E9%94%99%E8%AF%AF%E7%A0%81
请在META元数据平台录入JSAPI相关信息：https://yuque.antfin-inc.com/docs/share/c5481b27-4b3c-4f33-acb2-ac66d2fd4b0b?# 《JSAPI META平台录入指南》
https://api.zalopay.vn
https://dala2zoom.zalopay.vn
https://simg.zalopay.com.vn/bank-mapping/phoenix/bg/topup-middle-page-bg.png
https://api.zalopay.vn/
https://play.google.com/store/apps/details?id=com.zing.zalo
https://simg.zalopay.com.vn/zst/zpi-spa/um/kyc/images/toro_information.png
https://zalo.me/4046405574025332160
https://zalopay.vn
https://zalopay.vn/quy-dinh/chinh-sach-bao-ve-quyen-rieng-tu-hop-nhat
https://zalopay.vn/quy-dinh/thoa-thuan-su-dung-zalopay
https://api.zalopay.vn/
https://social.zalopay.vn/spa/kyc/phone/change/zpa-v2?source=
https://user-life-cycle-public.zalopay.vn
https://mnap-director.zalopay.vn/redir/-2003
https://mnap-director.zalopay.vn/redir?app_id=-986&data=%s
https://social.zalopay.vn/spa/v2
https://social.zalopay.vn/spa/v2/rba/verify/cashier_verify/%s
https://mnap-director.zalopay.vn/redir
https://mnap-director.zalopay.vn/redir/-2003
https://mnap-director.zalopay.vn/redir/-2004
https://simg.zalopay.com.vn/fs/lending/prod/bnpl/partner-product-info.html
... (240 more)
```

#### ips (300 matches, capped)
```
0.4.0.127.0.7
1.3.14.3.2.26
1.3.6.1.4.1.2.267.11.4.4
1.3.6.1.4.1.2.267.11.6.5
1.3.6.1.4.1.2.267.11.8.7
1.3.6.1.4.1.2.267.12.4.4
1.3.6.1.4.1.2.267.12.6.5
1.3.6.1.4.1.2.267.12.8.7
1.3.6.1.4.1.22554
1.3.6.1.5.5.7
192.168.43.255
2.16.840.1.101.3.4.2.1
2.16.840.1.101.3.4.2.2
2.16.840.1.101.3.4.2.3
2.16.840.1.101.3.4.2.4
2.16.840.1.101.3.4.3.1
2.16.840.1.101.3.4.3.2
2.16.840.1.101.3.4.3.3
2.16.840.1.101.3.4.3.4
2.16.840.1.114027.80.8.1.1
2.16.840.1.114027.80.8.1.10
2.16.840.1.114027.80.8.1.11
2.16.840.1.114027.80.8.1.12
2.16.840.1.114027.80.8.1.13
2.16.840.1.114027.80.8.1.2
2.16.840.1.114027.80.8.1.3
2.16.840.1.114027.80.8.1.4
2.16.840.1.114027.80.8.1.5
2.16.840.1.114027.80.8.1.6
2.16.840.1.114027.80.8.1.7
2.16.840.1.114027.80.8.1.8
2.16.840.1.114027.80.8.1.9
2.16.840.1.114027.80.9.1.0
2.16.840.1.114027.80.9.1.1
2.16.840.1.114027.80.9.1.10
2.16.840.1.114027.80.9.1.11
2.16.840.1.114027.80.9.1.12
2.16.840.1.114027.80.9.1.13
2.16.840.1.114027.80.9.1.14
2.16.840.1.114027.80.9.1.15
2.16.840.1.114027.80.9.1.16
2.16.840.1.114027.80.9.1.17
2.16.840.1.114027.80.9.1.2
2.16.840.1.114027.80.9.1.3
2.16.840.1.114027.80.9.1.4
2.16.840.1.114027.80.9.1.5
2.16.840.1.114027.80.9.1.6
2.16.840.1.114027.80.9.1.7
2.16.840.1.114027.80.9.1.8
2.16.840.1.114027.80.9.1.9
255.255.255.255
1.3.132.0.10
1.3.132.0.34
1.3.132.0.35
0.2.262.1.10.12.0
0.4.0.127.0.15.1.1.13.0
0.4.0.127.0.15.1.1.14.0
0.4.0.127.0.7
1.2.392.200011.61.1.1.1.2
1.2.392.200011.61.1.1.1.3
... (240 more)
```

#### secrets (0 matches, capped)
```
```

#### firebase (39 matches, capped)
```
firebase-settings.crashlytics.com
type.googleapis.com/google.crypto.tink.AesCmacKey
type.googleapis.com/google.crypto.tink.HmacKey
https://admob-gmats.uc.r.appspot.com/
https://firebaseinstallations.googleapis.com/v1/
https://firebaseremoteconfig.googleapis.com/v1/projects/
https://imasdk.googleapis.com/admob/sdkloader/native_video.html
type.googleapis.com/google.crypto.
type.googleapis.com/google.crypto.tink.AesCmacKey
type.googleapis.com/google.crypto.tink.AesCmacPrfKey
type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey
type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey
type.googleapis.com/google.crypto.tink.AesEaxKey
type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey
type.googleapis.com/google.crypto.tink.AesGcmKey
type.googleapis.com/google.crypto.tink.AesGcmSivKey
type.googleapis.com/google.crypto.tink.AesSivKey
type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key
type.googleapis.com/google.crypto.tink.EcdsaPrivateKey
type.googleapis.com/google.crypto.tink.EcdsaPublicKey
type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey
type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey
type.googleapis.com/google.crypto.tink.Ed25519PrivateKey
type.googleapis.com/google.crypto.tink.Ed25519PublicKey
type.googleapis.com/google.crypto.tink.HkdfPrfKey
type.googleapis.com/google.crypto.tink.HmacKey
type.googleapis.com/google.crypto.tink.HmacPrfKey
type.googleapis.com/google.crypto.tink.HpkePrivateKey
type.googleapis.com/google.crypto.tink.HpkePublicKey
type.googleapis.com/google.crypto.tink.KmsAeadKey
type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey
type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey
type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey
type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey
type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey
type.googleapis.com/google.crypto.tink.XAesGcmKey
type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key
visionkit-pa.googleapis.com
https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/
```

#### crypto (300 matches, capped)
```
AES/CTR/NoPadding
AndroidKeyStore
CHUNKED_SHA256
DETDSA_WITH_SHA256
DSA_WITH_SHA256
ECDSA_WITH_SHA256
Invalid tag size in bytes %d; can be at most 32 bytes for SHA256
KeystoreHelper
Landroid/security/keystore/KeyGenParameterSpec$Builder;
Landroid/security/keystore/KeyGenParameterSpec;
Ljava/security/KeyStore$LoadStoreParameter;
Ljava/security/KeyStore;
RSA/ECB/NoPadding
RSA/NONE/NoPadding
RSA_PKCS1_V1_5_WITH_SHA256
RSA_PSS_WITH_SHA256
SHA-256
SHA-256 with DSA
SHA-256 with ECDSA
SHA-256 with RSA
SHA1WithRSA/ISO9796-2
SHA1withRSA/ISO9796-2
SHA224withRSA/ISO9796-2
SHA256
SHA256withDSA
SHA256withDetDSA
SHA256withECDSA
SHA256withRSA
SHA256withRSA/ISO9796-2
SHA256withRSA/PSS
SHA384withRSA/ISO9796-2
SHA512withRSA/ISO9796-2
SHA512withRSA/PSS
SSAwithRSA/PSS
VERITY_CHUNKED_SHA256
VERITY_DSA_WITH_SHA256
VERITY_ECDSA_WITH_SHA256
VERITY_RSA_PKCS1_V1_5_WITH_SHA256
bc_pbe_sha256
bc_pbe_sha256_pkcs12
bc_pbe_sha256_pkcs12_aes128_cbc
bc_pbe_sha256_pkcs12_aes192_cbc
bc_pbe_sha256_pkcs12_aes256_cbc
bc_pbe_sha256_pkcs5
co.vnsafe.xverifysdk.jmrtd.cert.KeyStoreCertStoreSpi
ecdsa_plain_SHA256
ecka_eg_X963kdf_SHA256
id_MLDSA44_ECDSA_P256_SHA256
id_MLDSA44_RSA2048_PKCS15_SHA256
id_MLDSA44_RSA2048_PSS_SHA256
stamp-cert-sha256
unknown hash type; must be SHA256, SHA384 or SHA512
withRSA/PSS
xmss_SHA256
xmss_SHA256ph
xmss_mt_SHA256
xmss_mt_SHA256ph
HmacSHA256
SHA-256
RSA/ECB/OAEPPadding
... (240 more)
```

#### native (25 matches, capped)
```
AsyncTimeout.source(
.*\.so$
Metadata.source
No .so
com.labpixies.flood,com.sofascore.results,games.spearmint.triplecrush,com.block.juggle,io.supercent.linkedcubic,com.cdtg.gunsound,com.corestudios.storemanagementidle,com.cdgames.fidget3d,io.supercent.burgeridle,io.supercent.pizzaidle,jp.ne.ibis.ibispaintx.app,com.dencreak.dlcalculator,com.ebay.klein
com.sony.dtv.hardware.panel.qfhd
l.sodck
l.soddc
l.sodrar
l.sodv
libcrashlytics-handler.so
.so
^lib/([^/]+)/([^/]+\.so)$
com.facebook.soloader.enabled
gcm.n.sound
gcm.n.sound2
securerandom.source
android.software.leanback
com.alibaba.ariver.build.socketcraft
com.labpixies.flood,com.sofascore.results,games.spearmint.triplecrush,com.block.juggle,io.supercent.linkedcubic,com.cdtg.gunsound,com.corestudios.storemanagementidle,com.cdgames.fidget3d,io.supercent.burgeridle,io.supercent.pizzaidle,jp.ne.ibis.ibispaintx.app,com.dencreak.dlcalculator,com.ebay.klein
com.sony.dtv.hardware.panel.qfhd
lib/([^/]+)/(.*\.so)$
Data signing failed with trueidlib.so. Verify the app applicationId/bundleId matches the one registered with TrueID for this vault file.
trueidlib.so not found. Add the vault file provided by TrueID to the app bundle (Android: src/main/assets, iOS: Copy Bundle Resources).
❌ Security Critical: trueidlib.so not found! Signing aborted.
```

## 4. Split APK Analysis
### config.armeabi_v7a

- SHA-256: `456aa08f4fa2e3f952657519ccf0b3f89a22cf8af040312425e926355c01987b`
- Size: 24828408
- File count: 32
- DEX names: []
- Signed: v1=True v2=True v3=True

### config.en

- SHA-256: `87699fda97e8a9be06583d9261689966d49a749569fbfa39092d09bd84f49942`
- Size: 74137
- File count: 6
- DEX names: []
- Signed: v1=True v2=True v3=True

### config.vi

- SHA-256: `985fc073b810b6286c116400a88e5552616a05fc57f8a5376599c8bfaa963e33`
- Size: 86425
- File count: 6
- DEX names: []
- Signed: v1=True v2=True v3=True

### config.xhdpi

- SHA-256: `f31350c3caed15099d2c2d325d88fef52268960ef66b1496eb099a19203aaa3b`
- Size: 1232563
- File count: 258
- DEX names: []
- Signed: v1=True v2=True v3=True

## 5. Security/Privacy Observations (Static)

- **Permission surface:** The app requests sensitive permissions including location, camera, microphone, contacts, calendar, phone state, notifications, biometrics, and storage/media access. These align with a financial super-app but warrant runtime minimization and user consent controls.
- **Network/telemetry indicators:** String findings include Google/Firebase/crashlytics endpoints and other HTTPS endpoints; full runtime traffic analysis would require instrumented dynamic testing in an authorized environment.
- **Cryptography indicators:** DEX strings reference crypto primitives/APIs (AES/RSA/HMAC/SHA/KeyStore/Cipher patterns). A full crypto audit requires decompiled source review of call sites and key management.
- **Native code:** `config.armeabi_v7a` split contains native libraries; these require disassembly (Ghidra/r2) for complete coverage.
- **Obfuscation/packing:** High class count and short/obfuscated package names in DEX are consistent with code shrinking/obfuscation (e.g., R8/ProGuard).

## 6. Reproducibility

Scripts in `research/scripts/` reproduce extraction and androguard analysis. Raw extracted files are gitignored due to size; regenerate with the scripts against the original ZIP.

## 7. Artifacts

- `reports/file_inventory.json` - all 6466 files with SHA-256
- `reports/xapk_manifest.json` - XAPK manifest
- `reports/androguard_main_summary.json` - main APK summary
- `reports/androguard_main.json` - main APK detailed report including class names
- `reports/androguard_*_summary.json` - split summaries

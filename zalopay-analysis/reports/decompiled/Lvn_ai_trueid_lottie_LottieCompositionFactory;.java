package vn.ai.trueid.lottie;
public class LottieCompositionFactory {
    private static final byte[] GZIP_MAGIC;
    private static final byte[] ZIP_MAGIC;
    private static final java.util.Map taskCache;
    private static final java.util.Set taskIdleListeners;

    static LottieCompositionFactory()
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.taskCache = new java.util.HashMap();
        vn.ai.trueid.lottie.LottieCompositionFactory.taskIdleListeners = new java.util.HashSet();
        byte[] v0_7 = new byte[4];
        v0_7 = {80, 75, 3, 4};
        vn.ai.trueid.lottie.LottieCompositionFactory.ZIP_MAGIC = v0_7;
        byte[] v0_3 = new byte[3];
        v0_3 = {31, -117, 8};
        vn.ai.trueid.lottie.LottieCompositionFactory.GZIP_MAGIC = v0_3;
        return;
    }

    private LottieCompositionFactory()
    {
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult a(android.content.Context p0, String p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromUrl$0(p0, p1, p2);
    }

    public static synthetic void b(boolean p0, java.io.InputStream p1)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonInputStream$7(p0, p1);
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult c(okio.Source p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonSource$10(p0, p1);
    }

    private static vn.ai.trueid.lottie.LottieTask cache(String p3, java.util.concurrent.Callable p4, Runnable p5)
    {
        vn.ai.trueid.lottie.f v1_6;
        int v0_0 = 0;
        if (p3 != 0) {
            v1_6 = vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().get(p3);
        } else {
            v1_6 = 0;
        }
        if (v1_6 != null) {
            v0_0 = new vn.ai.trueid.lottie.LottieTask(v1_6);
        }
        if (p3 != 0) {
            vn.ai.trueid.lottie.f v1_0 = vn.ai.trueid.lottie.LottieCompositionFactory.taskCache;
            if (v1_0.containsKey(p3)) {
                v0_0 = ((vn.ai.trueid.lottie.LottieTask) v1_0.get(p3));
            }
        }
        if (v0_0 == 0) {
            vn.ai.trueid.lottie.LottieTask v5_2 = new vn.ai.trueid.lottie.LottieTask(p4);
            if (p3 != 0) {
                int v4_2 = new java.util.concurrent.atomic.AtomicBoolean(0);
                v5_2.addListener(new vn.ai.trueid.lottie.f(p3, v4_2, 0));
                v5_2.addFailureListener(new vn.ai.trueid.lottie.f(p3, v4_2, 1));
                if (!v4_2.get()) {
                    int v4_4 = vn.ai.trueid.lottie.LottieCompositionFactory.taskCache;
                    v4_4.put(p3, v5_2);
                    if (v4_4.size() == 1) {
                        vn.ai.trueid.lottie.LottieCompositionFactory.notifyTaskCacheIdleListeners(0);
                    }
                }
            }
            return v5_2;
        } else {
            if (p5 != null) {
                p5.run();
            }
            return v0_0;
        }
    }

    public static void clearCache(android.content.Context p1)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.clearCache(p1, 1);
        return;
    }

    public static void clearCache(android.content.Context p1, boolean p2)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.taskCache.clear();
        vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().clear();
        if (p2) {
            vn.ai.trueid.lottie.network.NetworkCache v1_1 = vn.ai.trueid.lottie.L.networkCache(p1);
            if (v1_1 != null) {
                v1_1.clear();
            }
        }
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult d(vn.ai.trueid.lottie.parser.moshi.JsonReader p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonReader$12(p0, p1);
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult e(android.content.Context p0, String p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromAsset$1(p0, p1, p2);
    }

    public static synthetic void f(java.util.zip.ZipInputStream p0)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromZipStream$15(p0);
        return;
    }

    private static vn.ai.trueid.lottie.LottieImageAsset findImageAssetForFileName(vn.ai.trueid.lottie.LottieComposition p2, String p3)
    {
        int v2_3 = p2.getImages().values().iterator();
        while (v2_3.hasNext()) {
            vn.ai.trueid.lottie.LottieImageAsset v0_1 = ((vn.ai.trueid.lottie.LottieImageAsset) v2_3.next());
            if (v0_1.getFileName().equals(p3)) {
                return v0_1;
            }
        }
        return 0;
    }

    public static vn.ai.trueid.lottie.LottieTask fromAsset(android.content.Context p2, String p3)
    {
        String v0_1 = new StringBuilder("asset_");
        v0_1.append(p3);
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromAsset(p2, p3, v0_1.toString());
    }

    public static vn.ai.trueid.lottie.LottieTask fromAsset(android.content.Context p2, String p3, String p4)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, new com.airbnb.lottie.m(p2.getApplicationContext(), p3, p4, 3), 0);
    }

    public static vn.ai.trueid.lottie.LottieResult fromAssetSync(android.content.Context p2, String p3)
    {
        String v0_1 = new StringBuilder("asset_");
        v0_1.append(p3);
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromAssetSync(p2, p3, v0_1.toString());
    }

    public static vn.ai.trueid.lottie.LottieResult fromAssetSync(android.content.Context p1, String p2, String p3)
    {
        android.content.res.AssetManager v0_1;
        if (p3 != null) {
            v0_1 = vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().get(p3);
        } else {
            v0_1 = 0;
        }
        if (v0_1 == null) {
            try {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromInputStreamSync(p1, p1.getAssets().open(p2), p3);
            } catch (java.io.IOException v1_2) {
                return new vn.ai.trueid.lottie.LottieResult(v1_2);
            }
        } else {
            return new vn.ai.trueid.lottie.LottieResult(v0_1);
        }
    }

    public static vn.ai.trueid.lottie.LottieTask fromInputStream(android.content.Context p3, java.io.InputStream p4, String p5)
    {
        vn.ai.trueid.lottie.LottieTask v3_1;
        if (p3 != null) {
            v3_1 = p3.getApplicationContext();
        } else {
            v3_1 = 0;
        }
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p5, new com.google.firebase.concurrent.f(v3_1, p4, p5, 3), 0);
    }

    public static vn.ai.trueid.lottie.LottieResult fromInputStreamSync(android.content.Context p1, java.io.InputStream p2, String p3)
    {
        java.util.zip.ZipInputStream v0_5;
        if (p3 != null) {
            v0_5 = vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().get(p3);
        } else {
            v0_5 = 0;
        }
        if (v0_5 == null) {
            try {
                java.io.InputStream v2_1 = okio.Okio.buffer(okio.Okio.source(p2));
            } catch (vn.ai.trueid.lottie.LottieResult v1_9) {
                return new vn.ai.trueid.lottie.LottieResult(v1_9);
            }
            if (!vn.ai.trueid.lottie.LottieCompositionFactory.isZipCompressed(v2_1).booleanValue()) {
                if (!vn.ai.trueid.lottie.LottieCompositionFactory.isGzipCompressed(v2_1).booleanValue()) {
                    return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSync(vn.ai.trueid.lottie.parser.moshi.JsonReader.of(v2_1), p3);
                } else {
                    return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(new java.util.zip.GZIPInputStream(v2_1.inputStream()), p3);
                }
            } else {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p1, new java.util.zip.ZipInputStream(v2_1.inputStream()), p3);
            }
        } else {
            return new vn.ai.trueid.lottie.LottieResult(v0_5);
        }
    }

    public static vn.ai.trueid.lottie.LottieTask fromJson(org.json.JSONObject p2, String p3)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p3, new vn.ai.trueid.lottie.c(p3, 4, p2), 0);
    }

    public static vn.ai.trueid.lottie.LottieTask fromJsonInputStream(java.io.InputStream p3, String p4)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, new vn.ai.trueid.lottie.c(p4, 1, p3), new vn.ai.trueid.lottie.e(2, p3));
    }

    public static vn.ai.trueid.lottie.LottieTask fromJsonInputStream(java.io.InputStream p3, String p4, boolean p5)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, new vn.ai.trueid.lottie.g(p3, p4, p5), new E.b(p5, p3, 8));
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonInputStreamSync(java.io.InputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(p1, p2, 1);
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonInputStreamSync(java.io.InputStream p0, String p1, boolean p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonSourceSync(okio.Okio.source(p0), p1, p2);
    }

    public static vn.ai.trueid.lottie.LottieTask fromJsonReader(vn.ai.trueid.lottie.parser.moshi.JsonReader p3, String p4)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, new vn.ai.trueid.lottie.c(p4, 3, p3), new vn.ai.trueid.lottie.e(1, p3));
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonReaderSync(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSync(p1, p2, 1);
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonReaderSync(vn.ai.trueid.lottie.parser.moshi.JsonReader p0, String p1, boolean p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSyncInternal(p0, p1, p2);
    }

    private static vn.ai.trueid.lottie.LottieResult fromJsonReaderSyncInternal(vn.ai.trueid.lottie.parser.moshi.JsonReader p2, String p3, boolean p4)
    {
        try {
            vn.ai.trueid.lottie.LottieResult v0_3;
            if (p3 != null) {
                v0_3 = vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().get(p3);
            } else {
                v0_3 = 0;
            }
        } catch (vn.ai.trueid.lottie.LottieResult v3_1) {
            vn.ai.trueid.lottie.LottieResult v0_1 = new vn.ai.trueid.lottie.LottieResult(v3_1);
            if (p4) {
                vn.ai.trueid.lottie.utils.Utils.closeQuietly(p2);
            }
            return v0_1;
        } catch (vn.ai.trueid.lottie.LottieResult v3_2) {
            if (p4) {
                vn.ai.trueid.lottie.utils.Utils.closeQuietly(p2);
            }
            throw v3_2;
        }
        if (v0_3 == null) {
            vn.ai.trueid.lottie.LottieResult v0_4 = vn.ai.trueid.lottie.parser.LottieCompositionMoshiParser.parse(p2);
            if (p3 != null) {
                vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().put(p3, v0_4);
            }
            vn.ai.trueid.lottie.LottieResult v3_4 = new vn.ai.trueid.lottie.LottieResult(v0_4);
            if (p4) {
                vn.ai.trueid.lottie.utils.Utils.closeQuietly(p2);
            }
            return v3_4;
        } else {
            vn.ai.trueid.lottie.LottieResult v3_6 = new vn.ai.trueid.lottie.LottieResult(v0_3);
            if (p4) {
                vn.ai.trueid.lottie.utils.Utils.closeQuietly(p2);
            }
            return v3_6;
        }
    }

    public static vn.ai.trueid.lottie.LottieTask fromJsonSource(okio.Source p3, String p4)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, new vn.ai.trueid.lottie.c(p4, 2, p3), new vn.ai.trueid.lottie.e(0, p3));
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonSourceSync(okio.Source p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonSourceSync(p1, p2, 1);
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonSourceSync(okio.Source p0, String p1, boolean p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSyncInternal(vn.ai.trueid.lottie.parser.moshi.JsonReader.of(okio.Okio.buffer(p0)), p1, p2);
    }

    public static vn.ai.trueid.lottie.LottieTask fromJsonString(String p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p2, new vn.ai.trueid.lottie.c(p1, p2), 0);
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonStringSync(String p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonSourceSync(okio.Okio.source(new java.io.ByteArrayInputStream(p1.getBytes())), p2);
    }

    public static vn.ai.trueid.lottie.LottieResult fromJsonSync(org.json.JSONObject p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonStringSync(p0.toString(), p1);
    }

    public static vn.ai.trueid.lottie.LottieTask fromRawRes(android.content.Context p1, int p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromRawRes(p1, p2, vn.ai.trueid.lottie.LottieCompositionFactory.rawResCacheKey(p1, p2));
    }

    public static vn.ai.trueid.lottie.LottieTask fromRawRes(android.content.Context p6, int p7, String p8)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p8, new com.airbnb.lottie.p(new ref.WeakReference(p6), p6.getApplicationContext(), p7, p8, 1), 0);
    }

    public static vn.ai.trueid.lottie.LottieResult fromRawResSync(android.content.Context p1, int p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromRawResSync(p1, p2, vn.ai.trueid.lottie.LottieCompositionFactory.rawResCacheKey(p1, p2));
    }

    public static vn.ai.trueid.lottie.LottieResult fromRawResSync(android.content.Context p1, int p2, String p3)
    {
        java.util.zip.ZipInputStream v0_3;
        if (p3 != null) {
            v0_3 = vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().get(p3);
        } else {
            v0_3 = 0;
        }
        if (v0_3 == null) {
            try {
                vn.ai.trueid.lottie.LottieResult v2_3 = okio.Okio.buffer(okio.Okio.source(p1.getResources().openRawResource(p2)));
            } catch (java.io.IOException v1_10) {
                return new vn.ai.trueid.lottie.LottieResult(v1_10);
            }
            if (!vn.ai.trueid.lottie.LottieCompositionFactory.isZipCompressed(v2_3).booleanValue()) {
                if (!vn.ai.trueid.lottie.LottieCompositionFactory.isGzipCompressed(v2_3).booleanValue()) {
                    return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSync(vn.ai.trueid.lottie.parser.moshi.JsonReader.of(v2_3), p3);
                } else {
                    try {
                        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(new java.util.zip.GZIPInputStream(v2_3.inputStream()), p3);
                    } catch (java.io.IOException v1_8) {
                        return new vn.ai.trueid.lottie.LottieResult(v1_8);
                    }
                }
            } else {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p1, new java.util.zip.ZipInputStream(v2_3.inputStream()), p3);
            }
        } else {
            return new vn.ai.trueid.lottie.LottieResult(v0_3);
        }
    }

    public static vn.ai.trueid.lottie.LottieTask fromUrl(android.content.Context p2, String p3)
    {
        String v0_1 = new StringBuilder("url_");
        v0_1.append(p3);
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromUrl(p2, p3, v0_1.toString());
    }

    public static vn.ai.trueid.lottie.LottieTask fromUrl(android.content.Context p2, String p3, String p4)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, new com.airbnb.lottie.m(p2, p3, p4, 2), 0);
    }

    public static vn.ai.trueid.lottie.LottieResult fromUrlSync(android.content.Context p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromUrlSync(p0, p1, p1);
    }

    public static vn.ai.trueid.lottie.LottieResult fromUrlSync(android.content.Context p1, String p2, String p3)
    {
        vn.ai.trueid.lottie.LottieComposition v0_3;
        if (p3 != null) {
            v0_3 = vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().get(p3);
        } else {
            v0_3 = 0;
        }
        if (v0_3 == null) {
            vn.ai.trueid.lottie.LottieResult v1_1 = vn.ai.trueid.lottie.L.networkFetcher(p1).fetchSync(p1, p2, p3);
            if ((p3 != null) && (v1_1.getValue() != null)) {
                vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().put(p3, ((vn.ai.trueid.lottie.LottieComposition) v1_1.getValue()));
            }
            return v1_1;
        } else {
            return new vn.ai.trueid.lottie.LottieResult(v0_3);
        }
    }

    public static vn.ai.trueid.lottie.LottieTask fromZipStream(android.content.Context p2, java.util.zip.ZipInputStream p3, String p4)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, new vn.ai.trueid.lottie.h(p2, p3, p4, 0), new vn.ai.trueid.lottie.i(p3, 0));
    }

    public static vn.ai.trueid.lottie.LottieTask fromZipStream(android.content.Context p2, java.util.zip.ZipInputStream p3, String p4, boolean p5)
    {
        vn.ai.trueid.lottie.LottieTask v2_1;
        vn.ai.trueid.lottie.h v0_1 = new vn.ai.trueid.lottie.h(p2, p3, p4, 1);
        if (p5 == 0) {
            v2_1 = 0;
        } else {
            v2_1 = new vn.ai.trueid.lottie.i(p3, 1);
        }
        return vn.ai.trueid.lottie.LottieCompositionFactory.cache(p4, v0_1, v2_1);
    }

    public static vn.ai.trueid.lottie.LottieTask fromZipStream(java.util.zip.ZipInputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStream(0, p1, p2);
    }

    public static vn.ai.trueid.lottie.LottieTask fromZipStream(java.util.zip.ZipInputStream p1, String p2, boolean p3)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStream(0, p1, p2, p3);
    }

    public static vn.ai.trueid.lottie.LottieResult fromZipStreamSync(android.content.Context p1, java.util.zip.ZipInputStream p2, String p3)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p1, p2, p3, 1);
    }

    public static vn.ai.trueid.lottie.LottieResult fromZipStreamSync(android.content.Context p0, java.util.zip.ZipInputStream p1, String p2, boolean p3)
    {
        try {
            Throwable v0_1 = vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSyncInternal(p0, p1, p2);
        } catch (Throwable v0_2) {
            if (p3) {
                vn.ai.trueid.lottie.utils.Utils.closeQuietly(p1);
            }
            throw v0_2;
        }
        if (p3) {
            vn.ai.trueid.lottie.utils.Utils.closeQuietly(p1);
        }
        return v0_1;
    }

    public static vn.ai.trueid.lottie.LottieResult fromZipStreamSync(java.util.zip.ZipInputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p1, p2, 1);
    }

    public static vn.ai.trueid.lottie.LottieResult fromZipStreamSync(java.util.zip.ZipInputStream p1, String p2, boolean p3)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(0, p1, p2, p3);
    }

    private static vn.ai.trueid.lottie.LottieResult fromZipStreamSyncInternal(android.content.Context p13, java.util.zip.ZipInputStream p14, String p15)
    {
        int v3_0;
        android.graphics.Bitmap v0_1 = new java.util.HashMap();
        int v1_6 = new java.util.HashMap();
        try {
            if (p15 != null) {
                v3_0 = vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().get(p15);
            } else {
                v3_0 = 0;
            }
        } catch (IllegalArgumentException v13_11) {
            return new vn.ai.trueid.lottie.LottieResult(v13_11);
        }
        if (v3_0 == 0) {
            int v3_3 = p14.getNextEntry();
            vn.ai.trueid.lottie.LottieComposition v4_0 = 0;
            while (v3_3 != 0) {
                String v7_4 = v3_3.getName();
                if (!v7_4.contains("__MACOSX")) {
                    if (!v3_3.getName().equalsIgnoreCase("manifest.json")) {
                        if (!v3_3.getName().contains(".json")) {
                            if ((!v7_4.contains(".png")) && ((!v7_4.contains(".webp")) && (!v7_4.contains(".jpg")))) {
                                if (!v7_4.contains(".jpeg")) {
                                    if (!v7_4.contains(".ttf")) {
                                        if (!v7_4.contains(".otf")) {
                                            p14.closeEntry();
                                            v3_3 = p14.getNextEntry();
                                        } else {
                                        }
                                    }
                                    int v3_23 = v7_4.split("/");
                                    int v3_24 = v3_23[(v3_23.length - 1)];
                                    String v5_3 = v3_24.split("\\.")[0];
                                    if (p13 != null) {
                                        String v7_8 = new java.io.File(p13.getCacheDir(), v3_24);
                                        try {
                                            Throwable v8_11 = new java.io.FileOutputStream(v7_8);
                                            try {
                                                Throwable v9_3 = new java.io.FileOutputStream(v7_8);
                                            } catch (String v6_2) {
                                                try {
                                                    v8_11.close();
                                                } catch (Throwable v8_12) {
                                                    v6_2.addSuppressed(v8_12);
                                                }
                                                throw v6_2;
                                            }
                                            byte[] v10_1 = new byte[4096];
                                            while(true) {
                                                int v11 = p14.read(v10_1);
                                                if (v11 == -1) {
                                                    break;
                                                }
                                                v9_3.write(v10_1, 0, v11);
                                            }
                                            v9_3.flush();
                                            v9_3.close();
                                            v8_11.close();
                                            int v3_27 = android.graphics.Typeface.createFromFile(v7_8);
                                            if (!v7_8.delete()) {
                                                String v6_6 = new StringBuilder();
                                                v6_6.append("Failed to delete temp font file ");
                                                v6_6.append(v7_8.getAbsolutePath());
                                                v6_6.append(".");
                                                vn.ai.trueid.lottie.utils.Logger.warning(v6_6.toString());
                                            }
                                            v1_6.put(v5_3, v3_27);
                                        } catch (String v6_3) {
                                            Throwable v8_14 = new StringBuilder();
                                            v8_14.append("Unable to save font ");
                                            v8_14.append(v5_3);
                                            v8_14.append(" to the temporary file: ");
                                            v8_14.append(v3_24);
                                            v8_14.append(". ");
                                            vn.ai.trueid.lottie.utils.Logger.warning(v8_14.toString(), v6_3);
                                        }
                                    } else {
                                        String v15_3 = new StringBuilder();
                                        v15_3.append("Unable to extract font ");
                                        v15_3.append(v5_3);
                                        v15_3.append(" please pass a non-null Context parameter");
                                        return new vn.ai.trueid.lottie.LottieResult(new IllegalStateException(v15_3.toString()));
                                    }
                                } else {
                                }
                            }
                            int v3_28 = v7_4.split("/");
                            v0_1.put(v3_28[(v3_28.length - 1)], android.graphics.BitmapFactory.decodeStream(p14));
                        } else {
                            v4_0 = ((vn.ai.trueid.lottie.LottieComposition) vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSyncInternal(vn.ai.trueid.lottie.parser.moshi.JsonReader.of(okio.Okio.buffer(okio.Okio.source(p14))), 0, 0).getValue());
                        }
                    } else {
                        p14.closeEntry();
                    }
                } else {
                    p14.closeEntry();
                }
            }
            if (v4_0 != null) {
                IllegalArgumentException v13_4 = v0_1.entrySet().iterator();
                while (v13_4.hasNext()) {
                    String v14_7 = ((java.util.Map$Entry) v13_4.next());
                    int v3_8 = vn.ai.trueid.lottie.LottieCompositionFactory.findImageAssetForFileName(v4_0, ((String) v14_7.getKey()));
                    if (v3_8 != 0) {
                        v3_8.setBitmap(vn.ai.trueid.lottie.utils.Utils.resizeBitmapIfNeeded(((android.graphics.Bitmap) v14_7.getValue()), v3_8.getWidth(), v3_8.getHeight()));
                    }
                }
                IllegalArgumentException v13_7 = v1_6.entrySet().iterator();
                while (v13_7.hasNext()) {
                    String v14_1 = ((java.util.Map$Entry) v13_7.next());
                    int v1_2 = v4_0.getFonts().values().iterator();
                    int v3_1 = 0;
                    while (v1_2.hasNext()) {
                        String v7_2 = ((vn.ai.trueid.lottie.model.Font) v1_2.next());
                        if (v7_2.getFamily().equals(v14_1.getKey())) {
                            v7_2.setTypeface(((android.graphics.Typeface) v14_1.getValue()));
                            v3_1 = 1;
                        }
                    }
                    if (v3_1 == 0) {
                        int v1_4 = new StringBuilder("Parsed font for ");
                        v1_4.append(((String) v14_1.getKey()));
                        v1_4.append(" however it was not found in the animation.");
                        vn.ai.trueid.lottie.utils.Logger.warning(v1_4.toString());
                    }
                }
                if (v0_1.isEmpty()) {
                    IllegalArgumentException v13_15 = v4_0.getImages().entrySet().iterator();
                    while (v13_15.hasNext()) {
                        String v14_23 = ((vn.ai.trueid.lottie.LottieImageAsset) ((java.util.Map$Entry) v13_15.next()).getValue());
                        if (v14_23 != null) {
                            android.graphics.Bitmap v0_4 = v14_23.getFileName();
                            int v1_8 = new android.graphics.BitmapFactory$Options();
                            v1_8.inScaled = 1;
                            v1_8.inDensity = 160;
                            if ((v0_4.startsWith("data:")) && (v0_4.indexOf("base64,") > 0)) {
                                try {
                                    android.graphics.Bitmap v0_6 = android.util.Base64.decode(v0_4.substring((v0_4.indexOf(44) + 1)), 0);
                                    android.graphics.Bitmap v0_7 = android.graphics.BitmapFactory.decodeByteArray(v0_6, 0, v0_6.length, v1_8);
                                } catch (IllegalArgumentException v13_19) {
                                    vn.ai.trueid.lottie.utils.Logger.warning("data URL did not have correct base64 format.", v13_19);
                                    return 0;
                                }
                                if (v0_7 != null) {
                                    v14_23.setBitmap(vn.ai.trueid.lottie.utils.Utils.resizeBitmapIfNeeded(v0_7, v14_23.getWidth(), v14_23.getHeight()));
                                }
                            }
                        } else {
                            return 0;
                        }
                    }
                }
                if (p15 != null) {
                    vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().put(p15, v4_0);
                }
                return new vn.ai.trueid.lottie.LottieResult(v4_0);
            } else {
                return new vn.ai.trueid.lottie.LottieResult(new IllegalArgumentException("Unable to parse composition"));
            }
        } else {
            return new vn.ai.trueid.lottie.LottieResult(v3_0);
        }
    }

    public static synthetic void g(java.io.InputStream p0)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonInputStream$5(p0);
        return;
    }

    public static synthetic void h(okio.Source p0)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonSource$11(p0);
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult i(android.content.Context p0, java.util.zip.ZipInputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromZipStream$16(p0, p1, p2);
    }

    private static Boolean isGzipCompressed(okio.BufferedSource p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.matchesMagicBytes(p1, vn.ai.trueid.lottie.LottieCompositionFactory.GZIP_MAGIC);
    }

    private static boolean isNightMode(android.content.Context p1)
    {
        if ((p1.getResources().getConfiguration().uiMode & 48) != 32) {
            return 0;
        } else {
            return 1;
        }
    }

    private static Boolean isZipCompressed(okio.BufferedSource p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.matchesMagicBytes(p1, vn.ai.trueid.lottie.LottieCompositionFactory.ZIP_MAGIC);
    }

    public static synthetic void j(java.util.zip.ZipInputStream p0)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromZipStream$17(p0);
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult k(String p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonString$9(p0, p1);
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult l(java.io.InputStream p0, String p1, boolean p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonInputStream$6(p0, p1, p2);
    }

    private static synthetic void lambda$cache$18(String p0, java.util.concurrent.atomic.AtomicBoolean p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        p2 = vn.ai.trueid.lottie.LottieCompositionFactory.taskCache;
        p2.remove(p0);
        p1.set(1);
        if (p2.size() == 0) {
            vn.ai.trueid.lottie.LottieCompositionFactory.notifyTaskCacheIdleListeners(1);
        }
        return;
    }

    private static synthetic void lambda$cache$19(String p0, java.util.concurrent.atomic.AtomicBoolean p1, Throwable p2)
    {
        p2 = vn.ai.trueid.lottie.LottieCompositionFactory.taskCache;
        p2.remove(p0);
        p1.set(1);
        if (p2.size() == 0) {
            vn.ai.trueid.lottie.LottieCompositionFactory.notifyTaskCacheIdleListeners(1);
        }
        return;
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromAsset$1(android.content.Context p0, String p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromAssetSync(p0, p1, p2);
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromInputStream$2(android.content.Context p0, java.io.InputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromInputStreamSync(p0, p1, p2);
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromJson$8(org.json.JSONObject p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonSync(p0, p1);
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromJsonInputStream$4(java.io.InputStream p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(p0, p1);
    }

    private static synthetic void lambda$fromJsonInputStream$5(java.io.InputStream p0)
    {
        vn.ai.trueid.lottie.utils.Utils.closeQuietly(p0);
        return;
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromJsonInputStream$6(java.io.InputStream p0, String p1, boolean p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(p0, p1, p2);
    }

    private static synthetic void lambda$fromJsonInputStream$7(boolean p0, java.io.InputStream p1)
    {
        if (p0) {
            vn.ai.trueid.lottie.utils.Utils.closeQuietly(p1);
        }
        return;
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromJsonReader$12(vn.ai.trueid.lottie.parser.moshi.JsonReader p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonReaderSync(p0, p1);
    }

    private static synthetic void lambda$fromJsonReader$13(vn.ai.trueid.lottie.parser.moshi.JsonReader p0)
    {
        vn.ai.trueid.lottie.utils.Utils.closeQuietly(p0);
        return;
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromJsonSource$10(okio.Source p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonSourceSync(p0, p1);
    }

    private static synthetic void lambda$fromJsonSource$11(okio.Source p0)
    {
        vn.ai.trueid.lottie.utils.Utils.closeQuietly(p0);
        return;
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromJsonString$9(String p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonStringSync(p0, p1);
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromRawRes$3(ref.WeakReference p0, android.content.Context p1, int p2, String p3)
    {
        vn.ai.trueid.lottie.LottieResult v0_2 = ((android.content.Context) p0.get());
        if (v0_2 != null) {
            p1 = v0_2;
        }
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromRawResSync(p1, p2, p3);
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromUrl$0(android.content.Context p1, String p2, String p3)
    {
        vn.ai.trueid.lottie.LottieResult v1_1 = vn.ai.trueid.lottie.L.networkFetcher(p1).fetchSync(p1, p2, p3);
        if ((p3 != null) && (v1_1.getValue() != null)) {
            vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().put(p3, ((vn.ai.trueid.lottie.LottieComposition) v1_1.getValue()));
        }
        return v1_1;
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromZipStream$14(android.content.Context p0, java.util.zip.ZipInputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p0, p1, p2);
    }

    private static synthetic void lambda$fromZipStream$15(java.util.zip.ZipInputStream p0)
    {
        vn.ai.trueid.lottie.utils.Utils.closeQuietly(p0);
        return;
    }

    private static synthetic vn.ai.trueid.lottie.LottieResult lambda$fromZipStream$16(android.content.Context p0, java.util.zip.ZipInputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p0, p1, p2);
    }

    private static synthetic void lambda$fromZipStream$17(java.util.zip.ZipInputStream p0)
    {
        vn.ai.trueid.lottie.utils.Utils.closeQuietly(p0);
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult m(String p0, org.json.JSONObject p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJson$8(p1, p0);
    }

    private static Boolean matchesMagicBytes(okio.BufferedSource p4, byte[] p5)
    {
        try {
            Boolean v4_1 = p4.peek();
            int v1 = 0;
        } catch (NoSuchMethodError) {
            return Boolean.FALSE;
        } catch (Boolean v4_3) {
            vn.ai.trueid.lottie.utils.Logger.error("Failed to check zip file header", v4_3);
            return Boolean.FALSE;
        }
        while (v1 < p5.length) {
            if (v4_1.readByte() == p5[v1]) {
                v1++;
            } else {
                return Boolean.FALSE;
            }
        }
        v4_1.close();
        return Boolean.TRUE;
    }

    public static synthetic void n(vn.ai.trueid.lottie.parser.moshi.JsonReader p0)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonReader$13(p0);
        return;
    }

    private static void notifyTaskCacheIdleListeners(boolean p3)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(vn.ai.trueid.lottie.LottieCompositionFactory.taskIdleListeners);
        int v1_1 = 0;
        while (v1_1 < v0_1.size()) {
            ((vn.ai.trueid.lottie.LottieTaskIdleListener) v0_1.get(v1_1)).onIdleChanged(p3);
            v1_1++;
        }
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult o(android.content.Context p0, java.util.zip.ZipInputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromZipStream$14(p0, p1, p2);
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult p(android.content.Context p0, java.io.InputStream p1, String p2)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromInputStream$2(p0, p1, p2);
    }

    public static synthetic void q(String p0, java.util.concurrent.atomic.AtomicBoolean p1, Throwable p2)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$cache$19(p0, p1, p2);
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult r(java.io.InputStream p0, String p1)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromJsonInputStream$4(p0, p1);
    }

    private static String rawResCacheKey(android.content.Context p2, int p3)
    {
        String v2_2;
        StringBuilder v0_1 = new StringBuilder("rawRes");
        if (!vn.ai.trueid.lottie.LottieCompositionFactory.isNightMode(p2)) {
            v2_2 = "_day_";
        } else {
            v2_2 = "_night_";
        }
        return androidx.activity.result.e.i(p3, v2_2, v0_1);
    }

    public static void registerLottieTaskIdleListener(vn.ai.trueid.lottie.LottieTaskIdleListener p1)
    {
        int v0_3;
        vn.ai.trueid.lottie.LottieCompositionFactory.taskIdleListeners.add(p1);
        if (vn.ai.trueid.lottie.LottieCompositionFactory.taskCache.size() != 0) {
            v0_3 = 0;
        } else {
            v0_3 = 1;
        }
        p1.onIdleChanged(v0_3);
        return;
    }

    public static synthetic vn.ai.trueid.lottie.LottieResult s(ref.WeakReference p0, android.content.Context p1, int p2, String p3)
    {
        return vn.ai.trueid.lottie.LottieCompositionFactory.lambda$fromRawRes$3(p0, p1, p2, p3);
    }

    public static void setMaxCacheSize(int p1)
    {
        vn.ai.trueid.lottie.model.LottieCompositionCache.getInstance().resize(p1);
        return;
    }

    public static synthetic void t(String p0, java.util.concurrent.atomic.AtomicBoolean p1, vn.ai.trueid.lottie.LottieComposition p2)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.lambda$cache$18(p0, p1, p2);
        return;
    }

    public static void unregisterLottieTaskIdleListener(vn.ai.trueid.lottie.LottieTaskIdleListener p1)
    {
        vn.ai.trueid.lottie.LottieCompositionFactory.taskIdleListeners.remove(p1);
        return;
    }
}

package vn.ai.trueid.lottie.network;
public class NetworkFetcher {
    private final vn.ai.trueid.lottie.network.LottieNetworkFetcher fetcher;
    private final vn.ai.trueid.lottie.network.NetworkCache networkCache;

    public NetworkFetcher(vn.ai.trueid.lottie.network.NetworkCache p1, vn.ai.trueid.lottie.network.LottieNetworkFetcher p2)
    {
        this.networkCache = p1;
        this.fetcher = p2;
        return;
    }

    private vn.ai.trueid.lottie.LottieComposition fetchFromCache(android.content.Context p4, String p5, String p6)
    {
        if (p6 != null) {
            java.util.zip.ZipInputStream v1_4 = this.networkCache;
            if (v1_4 != null) {
                vn.ai.trueid.lottie.LottieResult v5_5 = v1_4.fetch(p5);
                if (v5_5 != null) {
                    vn.ai.trueid.lottie.LottieComposition v4_1;
                    vn.ai.trueid.lottie.LottieResult v5_1 = ((java.io.InputStream) v5_5.second);
                    java.util.zip.ZipInputStream v1_1 = vn.ai.trueid.lottie.network.NetworkFetcher$1.$SwitchMap$com$airbnb$lottie$network$FileExtension[((vn.ai.trueid.lottie.network.FileExtension) v5_5.first).ordinal()];
                    if (v1_1 == 1) {
                        v4_1 = vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p4, new java.util.zip.ZipInputStream(v5_1), p6);
                    } else {
                        if (v1_1 == 2) {
                            try {
                                v4_1 = vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(new java.util.zip.GZIPInputStream(v5_1), p6);
                            } catch (vn.ai.trueid.lottie.LottieComposition v4_5) {
                                v4_1 = new vn.ai.trueid.lottie.LottieResult(v4_5);
                            }
                        } else {
                            v4_1 = vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(v5_1, p6);
                        }
                    }
                    if (v4_1.getValue() != null) {
                        return ((vn.ai.trueid.lottie.LottieComposition) v4_1.getValue());
                    }
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    private vn.ai.trueid.lottie.LottieResult fetchFromNetwork(android.content.Context p11, String p12, String p13)
    {
        vn.ai.trueid.lottie.network.LottieFetchResult v2_2 = new StringBuilder("Fetching ");
        v2_2.append(p12);
        vn.ai.trueid.lottie.utils.Logger.debug(v2_2.toString());
        try {
            java.io.IOException v11_2;
            vn.ai.trueid.lottie.network.LottieFetchResult v2_0 = this.fetcher.fetchSync(p12);
        } catch (java.io.IOException v0_1) {
            java.io.IOException v12_7 = new vn.ai.trueid.lottie.LottieResult(v0_1);
            if (v2_0 != null) {
                try {
                    v2_0.close();
                } catch (java.io.IOException v0_4) {
                    vn.ai.trueid.lottie.utils.Logger.warning("LottieFetchResult close failed ", v0_4);
                }
            }
            return v12_7;
        } catch (java.io.IOException v0_2) {
            java.io.IOException v11_4 = v0_2;
            if (v2_0 != null) {
                try {
                    v2_0.close();
                } catch (java.io.IOException v0_3) {
                    vn.ai.trueid.lottie.utils.Logger.warning("LottieFetchResult close failed ", v0_3);
                }
            }
            throw v11_4;
        }
        if (!v2_0.isSuccessful()) {
            v11_2 = new vn.ai.trueid.lottie.LottieResult(new IllegalArgumentException(v2_0.error()));
        } else {
            int v13_3;
            v11_2 = this.fromInputStream(p11, p12, v2_0.bodyByteStream(), v2_0.contentType(), p13);
            java.io.IOException v12_4 = new StringBuilder("Completed fetch from network. Success: ");
            if (v11_2.getValue() == null) {
                v13_3 = 0;
            } else {
                v13_3 = 1;
            }
            v12_4.append(v13_3);
            vn.ai.trueid.lottie.utils.Logger.debug(v12_4.toString());
        }
        try {
            v2_0.close();
            return v11_2;
        } catch (java.io.IOException v0_5) {
            vn.ai.trueid.lottie.utils.Logger.warning("LottieFetchResult close failed ", v0_5);
            return v11_2;
        }
    }

    private vn.ai.trueid.lottie.LottieResult fromGzipStream(String p2, java.io.InputStream p3, String p4)
    {
        if (p4 != null) {
            java.util.zip.GZIPInputStream v4_1 = this.networkCache;
            if (v4_1 != null) {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(new java.util.zip.GZIPInputStream(new java.io.FileInputStream(v4_1.writeTempCacheFile(p2, p3, vn.ai.trueid.lottie.network.FileExtension.GZIP))), p2);
            }
        }
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(new java.util.zip.GZIPInputStream(p3), 0);
    }

    private vn.ai.trueid.lottie.LottieResult fromInputStream(android.content.Context p5, String p6, java.io.InputStream p7, String p8, String p9)
    {
        if (p8 == null) {
            p8 = "application/json";
        }
        if ((!p8.contains("application/zip")) && ((!p8.contains("application/x-zip")) && ((!p8.contains("application/x-zip-compressed")) && (!p6.split("\\?")[0].endsWith(".lottie"))))) {
            if ((!p8.contains("application/gzip")) && ((!p8.contains("application/x-gzip")) && (!p6.split("\\?")[0].endsWith(".tgs")))) {
                vn.ai.trueid.lottie.utils.Logger.debug("Received json response.");
                vn.ai.trueid.lottie.network.FileExtension v5_9 = vn.ai.trueid.lottie.network.FileExtension.JSON;
                vn.ai.trueid.lottie.LottieResult v7_1 = this.fromJsonStream(p6, p7, p9);
            } else {
                vn.ai.trueid.lottie.utils.Logger.debug("Handling gzip response.");
                v5_9 = vn.ai.trueid.lottie.network.FileExtension.GZIP;
                v7_1 = this.fromGzipStream(p6, p7, p9);
            }
        } else {
            vn.ai.trueid.lottie.utils.Logger.debug("Handling zip response.");
            v7_1 = this.fromZipStream(p5, p6, p7, p9);
            v5_9 = vn.ai.trueid.lottie.network.FileExtension.ZIP;
        }
        if ((p9 != null) && (v7_1.getValue() != null)) {
            String v8_5 = this.networkCache;
            if (v8_5 != null) {
                v8_5.renameTempFile(p6, v5_9);
            }
        }
        return v7_1;
    }

    private vn.ai.trueid.lottie.LottieResult fromJsonStream(String p2, java.io.InputStream p3, String p4)
    {
        if (p4 != null) {
            java.io.FileInputStream v4_1 = this.networkCache;
            if (v4_1 != null) {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(new java.io.FileInputStream(v4_1.writeTempCacheFile(p2, p3, vn.ai.trueid.lottie.network.FileExtension.JSON).getAbsolutePath()), p2);
            }
        }
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromJsonInputStreamSync(p3, 0);
    }

    private vn.ai.trueid.lottie.LottieResult fromZipStream(android.content.Context p2, String p3, java.io.InputStream p4, String p5)
    {
        if (p5 != null) {
            java.util.zip.ZipInputStream v5_1 = this.networkCache;
            if (v5_1 != null) {
                return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p2, new java.util.zip.ZipInputStream(new java.io.FileInputStream(v5_1.writeTempCacheFile(p3, p4, vn.ai.trueid.lottie.network.FileExtension.ZIP))), p3);
            }
        }
        return vn.ai.trueid.lottie.LottieCompositionFactory.fromZipStreamSync(p2, new java.util.zip.ZipInputStream(p4), 0);
    }

    public vn.ai.trueid.lottie.LottieResult fetchSync(android.content.Context p3, String p4, String p5)
    {
        String v0_0 = this.fetchFromCache(p3, p4, p5);
        if (v0_0 == null) {
            String v0_3 = new StringBuilder("Animation for ");
            v0_3.append(p4);
            v0_3.append(" not found in cache. Fetching from network.");
            vn.ai.trueid.lottie.utils.Logger.debug(v0_3.toString());
            return this.fetchFromNetwork(p3, p4, p5);
        } else {
            return new vn.ai.trueid.lottie.LottieResult(v0_0);
        }
    }
}

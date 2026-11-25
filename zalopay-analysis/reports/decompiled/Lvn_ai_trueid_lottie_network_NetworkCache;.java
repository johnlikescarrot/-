package vn.ai.trueid.lottie.network;
public class NetworkCache {
    private final vn.ai.trueid.lottie.network.LottieNetworkCacheProvider cacheProvider;

    public NetworkCache(vn.ai.trueid.lottie.network.LottieNetworkCacheProvider p1)
    {
        this.cacheProvider = p1;
        return;
    }

    private static String filenameForUrl(String p1, vn.ai.trueid.lottie.network.FileExtension p2, boolean p3)
    {
        String v2_1;
        if (p3 == null) {
            v2_1 = p2.extension;
        } else {
            v2_1 = p2.tempExtension();
        }
        String v1_1 = p1.replaceAll("\\W+", "");
        String v3_1 = (242 - v2_1.length());
        if (v1_1.length() > v3_1) {
            v1_1 = vn.ai.trueid.lottie.network.NetworkCache.getMD5(v1_1, v3_1);
        }
        return A1.a.i("lottie_cache_", v1_1, v2_1);
    }

    private java.io.File getCachedFile(String p5)
    {
        java.io.File v0_5 = new java.io.File(this.parentDir(), vn.ai.trueid.lottie.network.NetworkCache.filenameForUrl(p5, vn.ai.trueid.lottie.network.FileExtension.JSON, 0));
        if (!v0_5.exists()) {
            java.io.File v0_2 = new java.io.File(this.parentDir(), vn.ai.trueid.lottie.network.NetworkCache.filenameForUrl(p5, vn.ai.trueid.lottie.network.FileExtension.ZIP, 0));
            if (!v0_2.exists()) {
                java.io.File v0_4 = new java.io.File(this.parentDir(), vn.ai.trueid.lottie.network.NetworkCache.filenameForUrl(p5, vn.ai.trueid.lottie.network.FileExtension.GZIP, 0));
                if (!v0_4.exists()) {
                    return 0;
                } else {
                    return v0_4;
                }
            } else {
                return v0_2;
            }
        } else {
            return v0_5;
        }
    }

    private static String getMD5(String p3, int p4)
    {
        int v0 = 0;
        try {
            String v3_4 = java.security.MessageDigest.getInstance("MD5").digest(p3.getBytes());
            StringBuilder v4_2 = new StringBuilder();
        } catch (java.security.NoSuchAlgorithmException) {
            return v3_4.substring(0, v4_2);
        }
        while (v0 < v3_4.length) {
            v4_2.append(String.format("%02x", new Object[] {Byte.valueOf(v3_4[v0])})));
            v0++;
        }
        return v4_2.toString();
    }

    private java.io.File parentDir()
    {
        java.io.File v0_1 = this.cacheProvider.getCacheDir();
        if (v0_1.isFile()) {
            v0_1.delete();
        }
        if (!v0_1.exists()) {
            v0_1.mkdirs();
        }
        return v0_1;
    }

    public void clear()
    {
        java.io.File v0 = this.parentDir();
        if (v0.exists()) {
            java.io.File[] v1_1 = v0.listFiles();
            if ((v1_1 != null) && (v1_1.length > 0)) {
                int v2_0 = v1_1.length;
                int v3 = 0;
                while (v3 < v2_0) {
                    v1_1[v3].delete();
                    v3++;
                }
            }
            v0.delete();
        }
        return;
    }

    public android.util.Pair fetch(String p6)
    {
        vn.ai.trueid.lottie.network.FileExtension v0_0 = 0;
        try {
            String v1_0 = this.getCachedFile(p6);
        } catch (java.io.FileNotFoundException) {
            return v0_0;
        }
        if (v1_0 != null) {
            vn.ai.trueid.lottie.network.FileExtension v0_3;
            java.io.FileInputStream v2_1 = new java.io.FileInputStream(v1_0);
            v0_0 = v1_0.getAbsolutePath().endsWith(".zip");
            if (v0_0 == null) {
                if (!v1_0.getAbsolutePath().endsWith(".gz")) {
                    v0_3 = vn.ai.trueid.lottie.network.FileExtension.JSON;
                } else {
                    v0_3 = vn.ai.trueid.lottie.network.FileExtension.GZIP;
                }
            } else {
                v0_3 = vn.ai.trueid.lottie.network.FileExtension.ZIP;
            }
            android.util.Pair v6_1 = androidx.activity.result.e.u("Cache hit for ", p6, " at ");
            v6_1.append(v1_0.getAbsolutePath());
            vn.ai.trueid.lottie.utils.Logger.debug(v6_1.toString());
            return new android.util.Pair(v0_3, v2_1);
        } else {
            return 0;
        }
    }

    public void renameTempFile(String p4, vn.ai.trueid.lottie.network.FileExtension p5)
    {
        String v5_6 = new java.io.File(this.parentDir(), vn.ai.trueid.lottie.network.NetworkCache.filenameForUrl(p4, p5, 1));
        java.io.File v0_2 = new java.io.File(v5_6.getAbsolutePath().replace(".temp", ""));
        String v4_2 = v5_6.renameTo(v0_2);
        String v1_2 = new StringBuilder("Copying temp file to real file (");
        v1_2.append(v0_2);
        v1_2.append(")");
        vn.ai.trueid.lottie.utils.Logger.debug(v1_2.toString());
        if (v4_2 == null) {
            String v4_5 = new StringBuilder("Unable to rename cache file ");
            v4_5.append(v5_6.getAbsolutePath());
            v4_5.append(" to ");
            v4_5.append(v0_2.getAbsolutePath());
            v4_5.append(".");
            vn.ai.trueid.lottie.utils.Logger.warning(v4_5.toString());
        }
        return;
    }

    public java.io.File writeTempCacheFile(String p4, java.io.InputStream p5, vn.ai.trueid.lottie.network.FileExtension p6)
    {
        Throwable v6_3 = new java.io.File(this.parentDir(), vn.ai.trueid.lottie.network.NetworkCache.filenameForUrl(p4, p6, 1));
        try {
            Throwable v4_4 = new java.io.FileOutputStream(v6_3);
        } catch (Throwable v4_2) {
            p5.close();
            throw v4_2;
        }
        byte[] v0_1 = new byte[1024];
        while(true) {
            int v1 = p5.read(v0_1);
            if (v1 == -1) {
                break;
            }
            v4_4.write(v0_1, 0, v1);
        }
        v4_4.flush();
        v4_4.close();
        p5.close();
        return v6_3;
    }
}

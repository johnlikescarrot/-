package vn.ai.trueid.sdk.utils;
public final class SmartOTPHelper {
    public static final synthetic int a;

    static SmartOTPHelper()
    {
        try {
            System.loadLibrary("trueidlib");
            return;
        } catch (UnsatisfiedLinkError v0_1) {
            v0_1.printStackTrace();
            return;
        }
    }

    public static String a(android.content.Context p6)
    {
        String v6_1 = ((Iterable) p6.getSharedPreferences("sotp_pin_seed_store", 0).getAll().keySet()).iterator();
        while (v6_1.hasNext()) {
            String v0_2 = v6_1.next();
            if (kotlin.text.y.startsWith$default(((String) v0_2), "pin_seed_", 0, 2, 0)) {
            }
            String v0_3 = ((String) v0_2);
            if (v0_3 == null) {
                return 0;
            } else {
                return kotlin.text.StringsKt.r(v0_3, "pin_seed_");
            }
        }
        v0_2 = 0;
    }

    public static void b(android.content.Context p3)
    {
        try {
            Object[] v3_7 = p3.getSharedPreferences("smartotp_secure_prefs", 0).edit();
            v3_7.remove("ocra_counter");
            v3_7.apply();
            vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {"KeystoreHelper", "\u1f5d1\ufe0f Deleted OCRA counter"}));
            return;
        } catch (Object[] v3_2) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"KeystoreHelper", androidx.camera.camera2.internal.G.e("\u274c Failed to delete counter: ", v3_2.getMessage())}));
            return;
        }
    }

    public static void c()
    {
        java.security.KeyStore v0_1 = java.security.KeyStore.getInstance("AndroidKeyStore");
        v0_1.load(0);
        if (v0_1.containsAlias("trueid_sotp_pin_dek")) {
            v0_1.deleteEntry("trueid_sotp_pin_dek");
            vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {"SmartOTPHelper", "\u1f5d1\ufe0f Wiped PIN_DEK from AndroidKeyStore"}));
        }
        try {
            String v1_1 = vn.ai.trueid.sdk.utils.Helpers.Companion;
            v1_1.getContext().getSharedPreferences("sotp_pin_seed_store", 0).edit().clear().apply();
            v1_1.printLog(new Object[] {"SmartOTPHelper", "\u1f5d1\ufe0f Cleared all PIN seed entries"}));
        } catch (String v1_3) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"SmartOTPHelper", androidx.camera.camera2.internal.G.e("\u26a0\ufe0f Failed to clear PIN seed prefs: ", v1_3.getMessage())}));
        }
        if (v0_1.containsAlias("wrappingKeyAlias")) {
            v0_1.deleteEntry("wrappingKeyAlias");
        }
        if (v0_1.containsAlias("hmacKeyAlias")) {
            v0_1.deleteEntry("hmacKeyAlias");
        }
        return;
    }

    public static byte[] d(javax.crypto.SecretKey p4, byte[] p5)
    {
        int v0_2 = kotlin.collections.w.copyOfRange(p5, 0, 12);
        byte[] v5_1 = kotlin.collections.w.copyOfRange(p5, 12, p5.length);
        javax.crypto.Cipher v1_2 = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding");
        v1_2.init(2, p4, new javax.crypto.spec.GCMParameterSpec(128, v0_2));
        return v1_2.doFinal(v5_1);
    }

    public static byte[] e(javax.crypto.SecretKey p2, byte[] p3)
    {
        javax.crypto.Cipher v0_1 = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding");
        v0_1.init(1, p2);
        return kotlin.collections.w.plus(v0_1.getIV(), v0_1.doFinal(p3));
    }

    public static String f(String p10, String p11)
    {
        try {
            String v10_4 = android.util.Base64.decode(p10, 0);
            double v5_3 = kotlin.text.Charsets.UTF_8;
            int v4_4 = "OCRA-1:HOTP-SHA256-8:QH64".getBytes(v5_3);
            java.util.Locale v11_1 = p11.getBytes(v5_3);
            byte[] v6 = new byte[64];
            System.arraycopy(v11_1, 0, v6, 0, Og.x.coerceAtMost(v11_1.length, 64));
            java.util.Locale v11_6 = new byte[(((v4_4.length + 1) + v10_4.length) + 64)];
            System.arraycopy(v4_4, 0, v11_6, 0, v4_4.length);
            int v4_0 = v4_4.length;
            double v8_2 = (v4_0 + 1);
            v11_6[v4_0] = 0;
            System.arraycopy(v10_4, 0, v11_6, v8_2, v10_4.length);
            System.arraycopy(v6, 0, v11_6, (v8_2 + v10_4.length), 64);
            String v10_2 = javax.crypto.Mac.getInstance("HmacSHA256");
            String v0_2 = vn.ai.trueid.sdk.utils.SmartOTPHelper.g("hmacKeyAlias");
        } catch (String v10_13) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {androidx.camera.camera2.internal.G.e("\u274c generateOcraOTP error: ", v10_13.getMessage())}));
            return 0;
        }
        if (v0_2 != null) {
            v10_2.init(v0_2);
            String v10_3 = v10_2.doFinal(v11_6);
            java.util.Locale v11_8 = (kotlin.collections.y.last(v10_3) & 15);
            String v10_8 = (((v10_3[(v11_8 + 3)] & 255) | ((((v10_3[v11_8] & 127) << 24) | ((v10_3[(v11_8 + 1)] & 255) << 16)) | ((v10_3[(v11_8 + 2)] & 255) << 8))) % ((int) Math.pow(4621819117588971520, ((double) 8))));
            String v0_9 = new StringBuilder("%0");
            v0_9.append(8);
            v0_9.append("d");
            return String.format(java.util.Locale.US, v0_9.toString(), java.util.Arrays.copyOf(new Object[] {Integer.valueOf(v10_8)}), 1));
        } else {
            return 0;
        }
    }

    public static javax.crypto.SecretKey g(String p2)
    {
        try {
            boolean v1_1 = java.security.KeyStore.getInstance("AndroidKeyStore");
            v1_1.load(0);
            javax.crypto.SecretKey v2_2 = v1_1.getKey(p2, 0);
        } catch (Exception) {
            return 0;
        }
        if (!(v2_2 instanceof javax.crypto.SecretKey)) {
            return 0;
        } else {
            return ((javax.crypto.SecretKey) v2_2);
        }
    }

    public static final String h(android.content.Context p5)
    {
        android.content.SharedPreferences$Editor v0_5 = p5.getSharedPreferences("trueid_sotp_instance", 0);
        String v4_1 = v0_5.getString("app_instance_id", 0);
        if (v4_1 != null) {
            return v4_1;
        } else {
            String v5_1 = p5.getSharedPreferences("sotp_instance", 0);
            String v1_1 = v5_1.getString("app_instance_id", 0);
            if (v1_1 == null) {
                String v5_3 = java.util.UUID.randomUUID().toString();
                v0_5.edit().putString("app_instance_id", v5_3).apply();
                return v5_3;
            } else {
                v0_5.edit().putString("app_instance_id", v1_1).apply();
                v5_1.edit().remove("app_instance_id").apply();
                return v1_1;
            }
        }
    }

    public static javax.crypto.SecretKey i()
    {
        android.security.keystore.KeyGenParameterSpec v1_3 = java.security.KeyStore.getInstance("AndroidKeyStore");
        v1_3.load(0);
        if (!v1_3.containsAlias("trueid_sotp_pin_dek")) {
            javax.crypto.SecretKey v0_1 = javax.crypto.KeyGenerator.getInstance("AES", "AndroidKeyStore");
            v0_1.init(new android.security.keystore.KeyGenParameterSpec$Builder("trueid_sotp_pin_dek", 3).setBlockModes(new String[] {"GCM"})).setEncryptionPaddings(new String[] {"NoPadding"})).setKeySize(256).setUserAuthenticationRequired(0).build());
            return v0_1.generateKey();
        } else {
            return ((javax.crypto.SecretKey) v1_3.getKey("trueid_sotp_pin_dek", 0));
        }
    }

    public static void j(String p6, byte[] p7)
    {
        java.security.KeyStore v0_1 = java.security.KeyStore.getInstance("AndroidKeyStore");
        v0_1.load(0);
        java.security.KeyStore$SecretKeyEntry v7_2 = new java.security.KeyStore$SecretKeyEntry(new javax.crypto.spec.SecretKeySpec(p7, "HmacSHA256"));
        android.security.keystore.KeyProtection v1_2 = new android.security.keystore.KeyProtection$Builder(4).setDigests(new String[] {"SHA-256"}));
        v1_2.setUserAuthenticationRequired(1);
        int v3 = android.os.Build$VERSION.SDK_INT;
        if (v3 < 30) {
            v1_2.setUserAuthenticationValidityDurationSeconds(-1);
        } else {
            v1_2.setUserAuthenticationParameters(0, 3);
        }
        v1_2.setInvalidatedByBiometricEnrollment(0);
        try {
            if (v3 >= 28) {
                v1_2.setIsStrongBoxBacked(1);
            }
        } catch (Exception) {
        }
        v0_1.setEntry(p6, v7_2, v1_2.build());
        return;
    }

    public static boolean k()
    {
        int v0 = 0;
        try {
            java.util.Iterator v1_8 = java.security.KeyStore.getInstance("AndroidKeyStore");
            v1_8.load(0);
        } catch (Exception) {
            return v0;
        }
        if (!v1_8.containsAlias("hmacKeyAlias")) {
            java.util.Iterator v1_6 = ((Iterable) vn.ai.trueid.sdk.utils.Helpers.Companion.getContext().getSharedPreferences("sotp_pin_seed_store", 0).getAll().keySet());
            if ((!(v1_6 instanceof java.util.Collection)) || (!((java.util.Collection) v1_6).isEmpty())) {
                java.util.Iterator v1_7 = v1_6.iterator();
                while (v1_7.hasNext()) {
                    boolean v3_7 = ((String) v1_7.next());
                    if ((kotlin.text.y.startsWith$default(v3_7, "pin_seed_", 0, 2, 0)) || (kotlin.text.y.startsWith$default(v3_7, "sotp_type_", 0, 2, 0))) {
                    }
                }
                return v0;
            } else {
                return v0;
            }
        } else {
        }
        v0 = 1;
        return v0;
    }

    public static boolean l(android.content.Context p1)
    {
        try {
            if (com.facebook.applinks.b.w(p1).m(15) != 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception) {
            return 0;
        }
    }

    public static long m(android.content.Context p7)
    {
        try {
            String v4_3 = p7.getSharedPreferences("smartotp_secure_prefs", 0).getLong("ocra_counter", 0);
            StringBuilder v6_1 = new StringBuilder("\u1f4e6 Loaded OCRA counter = ");
            v6_1.append(v4_3);
            vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {"KeystoreHelper", v6_1.toString()}));
            return v4_3;
        } catch (Object[] v7_2) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"KeystoreHelper", androidx.camera.camera2.internal.G.e("\u26a0\ufe0f Failed to load counter: ", v7_2.getMessage())}));
            return 0;
        }
    }

    public static Ok.r n(android.content.Context p9, String p10)
    {
        try {
            String v9_11 = p9.getSharedPreferences("sotp_pin_seed_store", 0);
            String v2_5 = new StringBuilder("pin_meta_");
            v2_5.append(p10);
            String v9_1 = v9_11.getString(v2_5.toString(), 0);
        } catch (java.nio.charset.Charset v0_3) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"SmartOTPHelper", androidx.camera.camera2.internal.G.e("loadPinMeta failed: ", v0_3.getMessage())}));
            return 0;
        }
        if (v9_1 != null) {
            org.json.JSONObject v10_3 = new org.json.JSONObject(new String(vn.ai.trueid.sdk.utils.SmartOTPHelper.d(vn.ai.trueid.sdk.utils.SmartOTPHelper.i(), android.util.Base64.decode(v9_1, 2)), kotlin.text.Charsets.UTF_8));
            return new Ok.r(v10_3.getString("salt"), v10_3.getInt("it"), v10_3.getInt("fc"), v10_3.getLong("lu"));
        } else {
            return 0;
        }
    }

    public static long o(int p4, java.util.List p5)
    {
        if (!p5.isEmpty()) {
            long v4_4 = ((Number) p5.get(Og.x.coerceIn((p4 - 1), 0, (p5.size() - 1)))).longValue();
            if (v4_4 > 0) {
                return (System.currentTimeMillis() + v4_4);
            }
        }
        return 0;
    }

    public static byte[] p(android.content.Context p9, String p10, String p11, Ok.r p12)
    {
        try {
            byte[] v9_5 = p9.getSharedPreferences("sotp_pin_seed_store", 0);
            javax.crypto.spec.PBEKeySpec v2_4 = new StringBuilder("pin_seed_");
            v2_4.append(p11);
            byte[] v9_1 = v9_5.getString(v2_4.toString(), 0);
        } catch (javax.crypto.AEADBadTagException) {
            return 0;
        } catch (javax.crypto.SecretKeyFactory v0_5) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"SmartOTPHelper", androidx.camera.camera2.internal.G.e("openSeedWithPin failed: ", v0_5.getMessage())}));
            return 0;
        }
        if (v9_1 != null) {
            byte[] v9_3 = vn.ai.trueid.sdk.utils.SmartOTPHelper.d(vn.ai.trueid.sdk.utils.SmartOTPHelper.i(), android.util.Base64.decode(v9_1, 2));
            String v11_3 = android.util.Base64.decode(p12.a, 2);
            int v12_1 = p12.b;
            javax.crypto.SecretKeyFactory v0_4 = javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            javax.crypto.spec.PBEKeySpec v2_2 = new javax.crypto.spec.PBEKeySpec(p10.toCharArray(), v11_3, v12_1, 256);
            byte[] v3_2 = v0_4.generateSecret(v2_2).getEncoded();
            v2_2.clearPassword();
            byte[] v9_4 = vn.ai.trueid.sdk.utils.SmartOTPHelper.d(new javax.crypto.spec.SecretKeySpec(v3_2, "AES"), v9_3);
            kotlin.collections.w.fill$default(v3_2, 0, 0, 0, 6, 0);
            return v9_4;
        } else {
            return 0;
        }
    }

    public static void q(android.content.Context p5, String p6, Ok.r p7)
    {
        try {
            String v7_4 = vn.ai.trueid.sdk.utils.SmartOTPHelper.e(vn.ai.trueid.sdk.utils.SmartOTPHelper.i(), new org.json.JSONObject().put("salt", p7.a).put("it", p7.b).put("fc", p7.c).put("lu", p7.d).toString().getBytes(kotlin.text.Charsets.UTF_8));
            Object[] v5_2 = p5.getSharedPreferences("sotp_pin_seed_store", 0).edit();
            StringBuilder v1_7 = new StringBuilder("pin_meta_");
            v1_7.append(p6);
            v5_2.putString(v1_7.toString(), android.util.Base64.encodeToString(v7_4, 2)).apply();
            return;
        } catch (Object[] v5_4) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"SmartOTPHelper", androidx.camera.camera2.internal.G.e("savePinMeta failed: ", v5_4.getMessage())}));
            return;
        }
    }

    public static void r(android.content.Context p8, int p9, String p10)
    {
        try {
            vn.ai.trueid.sdk.utils.Helpers$Companion v9_7 = vn.ai.trueid.sdk.utils.SmartOTPHelper.e(vn.ai.trueid.sdk.utils.SmartOTPHelper.i(), new org.json.JSONObject().put("lu", ((Math.max(0, ((long) p9)) * 1000) + System.currentTimeMillis())).put("factor", "PIN").toString().getBytes(kotlin.text.Charsets.UTF_8));
            Object[] v8_2 = p8.getSharedPreferences("sotp_pin_seed_store", 0).edit();
            int v0_4 = new StringBuilder("sotp_lock_");
            v0_4.append(p10);
            v8_2.putString(v0_4.toString(), android.util.Base64.encodeToString(v9_7, 2)).apply();
            return;
        } catch (Object[] v8_4) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"SmartOTPHelper", androidx.camera.camera2.internal.G.e("saveServiceLock failed: ", v8_4.getMessage())}));
            return;
        }
    }

    public static long s(android.app.Activity p9, String p10)
    {
        try {
            long v5_6 = p9.getSharedPreferences("sotp_pin_seed_store", 0);
            org.json.JSONObject v6_4 = new StringBuilder("sotp_lock_");
            v6_4.append(p10);
            long v5_0 = v5_6.getString(v6_4.toString(), 0);
        } catch (android.content.SharedPreferences$Editor v9_4) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"SmartOTPHelper", androidx.camera.camera2.internal.G.e("serviceLockRemainingMs failed: ", v9_4.getMessage())}));
            return 0;
        }
        if (v5_0 != 0) {
            long v5_5 = (new org.json.JSONObject(new String(vn.ai.trueid.sdk.utils.SmartOTPHelper.d(vn.ai.trueid.sdk.utils.SmartOTPHelper.i(), android.util.Base64.decode(v5_0, 2)), kotlin.text.Charsets.UTF_8)).getLong("lu") - System.currentTimeMillis());
            if (v5_5 > 0) {
                return v5_5;
            } else {
                android.content.SharedPreferences$Editor v9_2 = p9.getSharedPreferences("sotp_pin_seed_store", 0).edit();
                StringBuilder v0_2 = new StringBuilder("sotp_lock_");
                v0_2.append(p10);
                v9_2.remove(v0_2.toString()).apply();
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static boolean t(android.content.Context p11, byte[] p12, String p13, String p14)
    {
        try {
            byte[] v1_1 = new byte[16];
            new java.security.SecureRandom().nextBytes(v1_1);
            int v3_7 = javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            Ok.r v4_1 = new javax.crypto.spec.PBEKeySpec(p13.toCharArray(), v1_1, 210000, 256);
            String v5_1 = v3_7.generateSecret(v4_1).getEncoded();
            v4_1.clearPassword();
            vn.ai.trueid.sdk.utils.Helpers$Companion v12_1 = vn.ai.trueid.sdk.utils.SmartOTPHelper.e(new javax.crypto.spec.SecretKeySpec(v5_1, "AES"), p12);
            kotlin.collections.w.fill$default(v5_1, 0, 0, 0, 6, 0);
            vn.ai.trueid.sdk.utils.Helpers$Companion v12_2 = vn.ai.trueid.sdk.utils.SmartOTPHelper.e(vn.ai.trueid.sdk.utils.SmartOTPHelper.i(), v12_1);
            String v13_8 = p11.getSharedPreferences("sotp_pin_seed_store", 0).edit();
            int v3_3 = new StringBuilder("pin_seed_");
            v3_3.append(p14);
            v13_8.putString(v3_3.toString(), android.util.Base64.encodeToString(v12_2, 2)).apply();
            vn.ai.trueid.sdk.utils.SmartOTPHelper.q(p11, p14, new Ok.r(android.util.Base64.encodeToString(v1_1, 2), 210000, 0, 0));
            return 1;
        } catch (Exception v0_2) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"SmartOTPHelper", androidx.camera.camera2.internal.G.e("storeSeedWithPin failed: ", v0_2.getMessage())}));
            return 0;
        }
    }

    public static m9.G2 u(android.content.Context p8, String p9, String p10, int p11, java.util.List p12)
    {
        Ok.r v0 = vn.ai.trueid.sdk.utils.SmartOTPHelper.n(p8, p10);
        if (v0 != null) {
            long v3 = 0;
            if (v0.d > 0) {
                int v1_3 = System.currentTimeMillis();
                long v5_3 = v0.d;
                if (v5_3 > v1_3) {
                    return new Ok.t((v5_3 - v1_3));
                }
            }
            int v9_1 = vn.ai.trueid.sdk.utils.SmartOTPHelper.p(p8, p9, p10, v0);
            if (v9_1 != 0) {
                if ((v0.c != 0) || (v0.d != 0)) {
                    v0.c = 0;
                    v0.d = 0;
                    vn.ai.trueid.sdk.utils.SmartOTPHelper.q(p8, p10, v0);
                }
                return new Ok.u(v9_1);
            } else {
                int v9_3 = (v0.c + 1);
                v0.c = v9_3;
                if (v9_3 < p11) {
                    v0.d = vn.ai.trueid.sdk.utils.SmartOTPHelper.o(v9_3, p12);
                    vn.ai.trueid.sdk.utils.SmartOTPHelper.q(p8, p10, v0);
                    Ok.u v8_3 = v0.d;
                    if (v8_3 > 0) {
                        v3 = Math.max(0, (v8_3 - System.currentTimeMillis()));
                    }
                    return new Ok.v(Math.max(0, (p11 - v0.c)), v3);
                } else {
                    v0.c = 0;
                    v0.d = 0;
                    vn.ai.trueid.sdk.utils.SmartOTPHelper.q(p8, p10, v0);
                    return Ok.s.b;
                }
            }
        } else {
            return Ok.s.c;
        }
    }

    public static final native int vaultAuthCeremony(byte[] p0, String p1, String p2, String p3, byte[] p4);

    public static final native int vaultCheckDebugger();

    public static final native int vaultCheckIntegrity();

    public static final native String vaultComputeQAuth(String p0, String p1, String p2);

    public static final native int vaultRegisterCeremony(byte[] p0, byte[] p1, byte[] p2, String p3, String p4, String p5, byte[] p6, byte[] p7);

    public static final native int vaultVerifyTxDigest(String p0, String p1);

    public static final native byte[] vaultX25519Pubkey(byte[] p0);
}

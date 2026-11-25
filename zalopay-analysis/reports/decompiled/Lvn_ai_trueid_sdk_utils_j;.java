package vn.ai.trueid.sdk.utils;
public abstract class j {
    public static final String gsbpjxb = "string";
    public static final String hoqamj = "\u26a0\ufe0f [ExifWriter] d\u1ef1ng metadata th\u1ea5t b\u1ea1i: ";
    public static final String isgvw = "";
    public static final String pjmm = "<br>";
    public static final String rbqe = "\n";
    public static final String sklc;

    static j()
    {
        wmoi.igob.jtgo(vn.ai.trueid.sdk.utils.j, 17769, 17770);
        return;
    }

    public static final void a(android.widget.TextView p13, String p14)
    {
        try {
            p13.setText(android.text.Html.fromHtml(kotlin.text.y.replace$default(kotlin.text.y.replace$default(p14, wmoi.igob.ggcn(3814), wmoi.igob.ggcn(3815), 0, 4, 0), wmoi.igob.ggcn(3816), wmoi.igob.ggcn(3817), 0, 4, 0), 0));
        } catch (Exception) {
        }
        return;
    }

    public static final String b(String p5)
    {
        java.util.Date v0_5 = kotlin.text.y.equals(p5, wmoi.igob.ggcn(3818), 1);
        String v1_1 = wmoi.igob.ggcn(3819);
        if (v0_5 == null) {
            try {
                String v5_1 = new java.text.SimpleDateFormat(wmoi.igob.ggcn(3820), java.util.Locale.getDefault()).parse(p5);
                java.util.Date v0_3 = java.util.Calendar.getInstance();
            } catch (java.text.ParseException) {
                return v1_1;
            }
            if (v5_1 == null) {
                v5_1 = new java.util.Date();
            }
            v0_3.setTime(v5_1);
            return new java.text.SimpleDateFormat(wmoi.igob.ggcn(3821), java.util.Locale.getDefault()).format(v0_3.getTime());
        } else {
            return v1_1;
        }
    }

    public static final String c(String p9)
    {
        String v0 = wmoi.igob.ggcn(3822);
        String v2_0 = "";
        if (p9.length() != 0) {
            String v1_9 = wmoi.igob.ggcn(3823);
            java.text.SimpleDateFormat v3_2 = kotlin.text.StringsKt.y(p9, new String[] {v1_9}), 0, 6).size();
            if (v3_2 != 3) {
                if (v3_2 != 2) {
                    if (p9.length() != 6) {
                        if (p9.length() == 4) {
                            v2_0 = wmoi.igob.ggcn(3827).concat(p9);
                        }
                    } else {
                        String v5_2;
                        String v2_1 = p9.substring(0, 2);
                        java.text.SimpleDateFormat v3_4 = p9.substring(2, 4);
                        java.util.Locale v4_1 = p9.substring(4, 6);
                        if (Integer.parseInt(v2_1) > 30) {
                            v5_2 = wmoi.igob.ggcn(3826);
                        } else {
                            v5_2 = wmoi.igob.ggcn(3825);
                        }
                        v2_0 = androidx.compose.foundation.layout.G0.m(v4_1, v1_9, v3_4, v1_9, androidx.camera.camera2.internal.G.e(v5_2, v2_1));
                    }
                } else {
                    v2_0 = wmoi.igob.ggcn(3824).concat(p9);
                }
            } else {
                v2_0 = p9;
            }
            try {
                if (v2_0.length() != 0) {
                    String v1_7;
                    String v1_5 = java.util.TimeZone.getDefault();
                    java.text.SimpleDateFormat v3_6 = new java.text.SimpleDateFormat(v0, java.util.Locale.getDefault());
                    v3_6.setTimeZone(v1_5);
                    if (v2_0.length() != 0) {
                        v1_7 = v3_6.parse(v2_0);
                    } else {
                        v1_7 = new java.util.Date(0);
                    }
                    String v2_4 = java.util.TimeZone.getDefault();
                    java.text.SimpleDateFormat v3_8 = new java.text.SimpleDateFormat(v0, java.util.Locale.getDefault());
                    v3_8.setTimeZone(v2_4);
                    p9 = v3_8.format(v1_7);
                }
            } catch (Exception) {
            }
            return p9;
        } else {
            return "";
        }
    }

    public static final int d(int p1)
    {
        return ((int) (((float) p1) * android.content.res.Resources.getSystem().getDisplayMetrics().density));
    }

    public static final String e(android.content.Context p6, String p7, org.json.JSONObject p8)
    {
        String v6_1;
        String v0_1 = wmoi.igob.ggcn(3828);
        if ((p8 == null) || ((p6.getResources().getIdentifier(p7, v0_1, p6.getPackageName()) == 0) || (p8.optString(p7, "").length() <= 0))) {
            v6_1 = p6.getString(p6.getResources().getIdentifier(p7, v0_1, p6.getPackageName()));
        } else {
            v6_1 = p8.getString(p7);
        }
        return kotlin.text.y.replace$default(v6_1, wmoi.igob.ggcn(3829), wmoi.igob.ggcn(3830), 0, 4, 0);
    }

    public static final boolean f(String p2)
    {
        if (p2.length() != 0) {
            new org.json.JSONObject(p2);
        } else {
            return 0;
        }
    }

    public static final String g(java.util.Map p5)
    {
        if (p5 != null) {
            org.json.JSONObject v0_1 = new org.json.JSONObject();
            String v5_5 = p5.entrySet().iterator();
            while (v5_5.hasNext()) {
                String v1_1 = ((java.util.Map$Entry) v5_5.next());
                String v2_1 = ((String) v1_1.getKey());
                String v1_2 = v1_1.getValue();
                if (v1_2 != null) {
                    org.json.JSONArray v3_1;
                    boolean v4 = 1;
                    if (!(v1_2 instanceof Boolean)) {
                        v3_1 = (v1_2 instanceof Number);
                    } else {
                        v3_1 = 1;
                    }
                    if (v3_1 == null) {
                        v4 = (v1_2 instanceof String);
                    }
                    if (!v4) {
                        if (!(v1_2 instanceof java.util.Map)) {
                            if (!(v1_2 instanceof java.util.List)) {
                                v0_1.put(v2_1, v1_2.toString());
                            } else {
                                v0_1.put(v2_1, new org.json.JSONArray(((java.util.Collection) v1_2)));
                            }
                        } else {
                            v0_1.put(v2_1, vn.ai.trueid.sdk.utils.j.g(((java.util.Map) v1_2)));
                        }
                    } else {
                        v0_1.put(v2_1, v1_2);
                    }
                } else {
                    v0_1.put(v2_1, org.json.JSONObject.NULL);
                }
            }
            return v0_1.toString();
        } else {
            return wmoi.igob.ggcn(3831);
        }
    }

    public static final org.json.JSONObject h(String p1, org.json.JSONObject p2)
    {
        boolean v1_1 = p2.opt(p1);
        if (!(v1_1 instanceof org.json.JSONObject)) {
            org.json.JSONObject v0 = 0;
            if ((v1_1 instanceof String)) {
                try {
                    Object v2_1 = new org.json.JSONObject(((String) v1_1));
                } catch (boolean v1_2) {
                    v2_1 = kotlin.ResultKt.createFailure(v1_2);
                }
                if (!(v2_1 instanceof ng.K)) {
                    v0 = v2_1;
                }
                v0 = ((org.json.JSONObject) v0);
            }
            return v0;
        } else {
            return ((org.json.JSONObject) v1_1);
        }
    }

    public static final void i(android.widget.TextView p12, String p13, org.json.JSONObject p14)
    {
        String v0_0 = vn.ai.trueid.sdk.utils.j.gsbpjxb;
        if (p14 != 0) {
            String v1_3 = p12.getContext();
            if ((v1_3.getResources().getIdentifier(p13, v0_0, v1_3.getPackageName()) != 0) && (p14.optString(p13, "").length() > 0)) {
                vn.ai.trueid.sdk.utils.j.a(p12, p14.optString(p13, ""));
                return;
            }
        }
        try {
            int v14_1 = p12.getContext();
            p12.setText(android.text.Html.fromHtml(kotlin.text.y.replace$default(kotlin.text.y.replace$default(v14_1.getString(v14_1.getResources().getIdentifier(p13, v0_0, v14_1.getPackageName())), vn.ai.trueid.sdk.utils.j.sklc, vn.ai.trueid.sdk.utils.j.isgvw, 0, 4, 0), vn.ai.trueid.sdk.utils.j.rbqe, vn.ai.trueid.sdk.utils.j.pjmm, 0, 4, 0), 0));
        } catch (Exception) {
        }
        return;
    }

    public static final java.util.ArrayList j(String p17, java.util.List p18)
    {
        java.util.ArrayList v1_1 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(((Iterable) p18), 10));
        java.util.Iterator v2_2 = ((Iterable) p18).iterator();
        vn.ai.trueid.sdk.TrueShield.ChallengeManager v0_2 = 0;
        while (v2_2.hasNext()) {
            android.content.Context v4_1 = v2_2.next();
            int v5 = (v0_2 + 1);
            if (v0_2 < null) {
                kotlin.collections.E.throwIndexOverflow();
            }
            android.content.Context v4_2 = ((String) v4_1);
            try {
                byte[] v7_0 = android.util.Base64.decode(v4_2, 2);
            } catch (Exception) {
                v7_0 = new byte[0];
            }
            if (v7_0.length != 0) {
                vn.ai.trueid.sdk.TrueShield.ChallengeManager v0_3;
                if (v0_2 == null) {
                    v0_3 = vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE1;
                } else {
                    if (v0_2 == 1) {
                        v0_3 = vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE2;
                    } else {
                        if (v0_2 == 2) {
                            v0_3 = vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE3;
                        } else {
                            v0_3 = vn.ai.trueid.sdk.TrueShield.ImageType.SELFIE1;
                        }
                    }
                }
                vn.ai.trueid.sdk.TrueShield.ImageType v10 = v0_3;
                try {
                    String v9_0 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext();
                    vn.ai.trueid.sdk.TrueShield.ChallengeManager v0_6 = new android.graphics.BitmapFactory$Options();
                    v0_6.inJustDecodeBounds = 1;
                    android.graphics.BitmapFactory.decodeByteArray(v7_0, 0, v7_0.length, v0_6);
                    byte[] v7_1 = vn.ai.trueid.sdk.utils.TrueIDExifWriter.INSTANCE.inject(v9_0, v7_0, vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.buildExifData(v9_0, v10, p17, v7_0.length, v0_6.outWidth, v0_6.outHeight));
                } catch (vn.ai.trueid.sdk.TrueShield.ChallengeManager v0_8) {
                    vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {androidx.camera.camera2.internal.G.e(vn.ai.trueid.sdk.utils.j.hoqamj, v0_8.getMessage())}));
                }
                byte[] v11_1 = v7_1;
                try {
                    int v13_1;
                    int v12_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDeviceNative();
                } catch (Exception) {
                    v4_2 = android.util.Base64.encodeToString(v11_1, 2);
                }
                if (v12_1 == 1) {
                    v13_1 = 0;
                } else {
                    v13_1 = 1;
                }
                vn.ai.trueid.sdk.TrueShield.ChallengeManager v0_14 = new vn.ai.trueid.sdk.TrueShield.SecureVault(vn.ai.trueid.sdk.utils.Helpers.Companion.getContext()).getVaultData();
                if (v0_14 == null) {
                    v0_14 = new byte[0];
                }
                vn.ai.trueid.sdk.navigation.TrueIDV3.embedSecurityNative(v11_1, v12_1, v13_1, vn.ai.trueid.sdk.TrueShield.ChallengeManager.INSTANCE.getHexColor(v10), p17, v0_14);
            }
            v1_1.add(v4_2);
            v0_2 = v5;
        }
        return v1_1;
    }
}

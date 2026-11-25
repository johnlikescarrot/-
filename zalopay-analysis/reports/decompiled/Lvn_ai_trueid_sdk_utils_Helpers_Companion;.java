package vn.ai.trueid.sdk.utils;
public final class Helpers$Companion {
    public static final String akcwovf = "nfc_religion";
    public static final String aroan = "nfc_mother_name";
    public static final String cawx = "nfc_tampered_status";
    public static final String ceeez = "nfc_mrz";
    public static final String cweus = "parseAndSaveNfcInfo parseAndSaveNfcInfo parseAndSaveNfcInfo";
    public static final String dvxe = "nfc_duedate";
    public static final String ivwsjxy = "nfc_date_of_birth";
    public static final String muycjq = "35";
    public static final String nwiurs = "nfc_given_date";
    public static final String oiusujp = "nfc_gender";
    public static final String onknujh = "nfc_address";
    public static final String pqhi = "nfc_decoding_status";
    public static final String qvvw = "nfc_ethnic";
    public static final String qxcct = "nfc_full_name";
    public static final String rffoq = "";
    public static final String saysoez = "nfc_image";
    public static final String uswldm = "nfc_origin";
    public static final String vtrk = "nfc_features";
    public static final String wbpjo = "nfc_nationality";
    public static final String wcsdzin = "serial";
    public static final String wsfh = "nfc_father_name";
    public static final String xsqjm = "nfc_idnumber";
    public static final String zezqj;

    static Helpers$Companion()
    {
        wmoi.igob.jtgo(vn.ai.trueid.sdk.utils.Helpers$Companion, 19226, 19227);
        return;
    }

    private Helpers$Companion()
    {
        return;
    }

    public synthetic Helpers$Companion(kotlin.jvm.internal.DefaultConstructorMarker p1)
    {
        return;
    }

    public static synthetic void a(android.content.Context p0)
    {
        vn.ai.trueid.sdk.utils.Helpers$Companion.init$lambda$1(p0);
        return;
    }

    private final void copyFile(java.io.InputStream p4, java.io.OutputStream p5)
    {
        byte[] v0_1 = new byte[1024];
        while(true) {
            int v1 = p4.read(v0_1);
            if (v1 == -1) {
                break;
            }
            p5.write(v0_1, 0, v1);
        }
        return;
    }

    private final String getDeviceUUID(android.content.Context p3)
    {
        String v3_4;
        String v3_6 = android.provider.Settings$Secure.getString(p3.getContentResolver(), wmoi.igob.ggcn(7044));
        try {
            if ((v3_6 != null) && (v3_6.length() != 0)) {
                v3_4 = java.util.UUID.nameUUIDFromBytes(v3_6.getBytes(java.nio.charset.Charset.forName(wmoi.igob.ggcn(7045)))).toString();
            } else {
                v3_4 = 0;
            }
        } catch (Exception) {
        }
        if ((v3_4 == null) || (v3_4.length() == 0)) {
            v3_4 = this.getUUID();
        }
        if ((v3_4 == null) || (v3_4.length() == 0)) {
            v3_4 = java.util.UUID.randomUUID().toString();
        }
        return v3_4;
    }

    private final String getStringResourceByName(String p3, String p4)
    {
        return this.getString(this.getContext().getResources().getIdentifier(p3, p4, this.getContext().getPackageName()));
    }

    private final String getUUID()
    {
        java.util.UUID v2_0;
        String v0_2 = (android.os.Build.BOARD.length() % 10);
        String v1_2 = (android.os.Build.BRAND.length() % 10);
        java.util.UUID v2_4 = android.os.Build.CPU_ABI;
        if (v2_4 == null) {
            v2_0 = 0;
        } else {
            v2_0 = v2_4.length();
        }
        java.util.UUID v2_1 = (v2_0 % 10);
        long v3_2 = (android.os.Build.DEVICE.length() % 10);
        int v4_2 = (android.os.Build.DISPLAY.length() % 10);
        long v5_2 = (android.os.Build.HOST.length() % 10);
        int v6_2 = (android.os.Build.ID.length() % 10);
        int v7_2 = (android.os.Build.MANUFACTURER.length() % 10);
        int v8_2 = (android.os.Build.MODEL.length() % 10);
        int v9_2 = (android.os.Build.PRODUCT.length() % 10);
        int v10_2 = (android.os.Build.TAGS.length() % 10);
        int v11_2 = (android.os.Build.TYPE.length() % 10);
        int v12_2 = (android.os.Build.USER.length() % 10);
        StringBuilder v13_1 = new StringBuilder(vn.ai.trueid.sdk.utils.Helpers$Companion.muycjq);
        v13_1.append(v0_2);
        v13_1.append(v1_2);
        v13_1.append(v2_1);
        v13_1.append(v3_2);
        v13_1.append(v4_2);
        v13_1.append(v5_2);
        v13_1.append(v6_2);
        v13_1.append(v7_2);
        v13_1.append(v8_2);
        v13_1.append(v9_2);
        v13_1.append(v10_2);
        v13_1.append(v11_2);
        v13_1.append(v12_2);
        String v0_3 = v13_1.toString();
        if (android.os.Build$VERSION.SDK_INT > 29) {
            String v1_4 = vn.ai.trueid.sdk.utils.Helpers$Companion.rffoq;
        } else {
            try {
                v0_3 = new java.util.UUID(((long) v0_3.hashCode()), ((long) android.os.Build.getSerial().hashCode())).toString();
                return v0_3;
            } catch (Exception) {
                v1_4 = vn.ai.trueid.sdk.utils.Helpers$Companion.wcsdzin;
            }
        }
        return new java.util.UUID(((long) v0_3.hashCode()), ((long) v1_4.hashCode())).toString();
    }

    private final int getValueInt(org.json.JSONObject p4, String p5)
    {
        org.json.JSONObject v0 = p4.optJSONObject(p5);
        if (v0 == null) {
            return p4.optInt(p5, -1);
        } else {
            return v0.optInt(wmoi.igob.ggcn(7046), p4.optInt(p5, -1));
        }
    }

    private final String getValueString(org.json.JSONObject p5, String p6)
    {
        int v0_1;
        int v0_0 = p5.optJSONObject(p6);
        if (v0_0 == 0) {
            v0_1 = 0;
        } else {
            v0_1 = v0_0.optString(wmoi.igob.ggcn(7047), p5.optString(p6, ""));
        }
        if (v0_1 != 0) {
            return v0_1;
        } else {
            return p5.optString(p6, "");
        }
    }

    private static final void init$lambda$1(android.content.Context p17)
    {
        Object[] v1_35 = new org.json.JSONObject();
        String v2_21 = new org.json.JSONObject();
        v2_21.put(wmoi.igob.ggcn(7048), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 4)));
        v2_21.put(wmoi.igob.ggcn(7049), vn.ai.trueid.sdk.utils.f.a(Ok.i.f));
        v2_21.put(wmoi.igob.ggcn(7050), vn.ai.trueid.sdk.utils.f.a(vn.ai.trueid.sdk.utils.e.d));
        v2_21.put(wmoi.igob.ggcn(7051), vn.ai.trueid.sdk.utils.f.a(Ok.i.g));
        v1_35.put(wmoi.igob.ggcn(7052), v2_21);
        String v2_22 = new org.json.JSONObject();
        v2_22.put(wmoi.igob.ggcn(7053), vn.ai.trueid.sdk.utils.f.a(Ok.b.w));
        v2_22.put(wmoi.igob.ggcn(7054), vn.ai.trueid.sdk.utils.f.a(Ok.b.x));
        v2_22.put(wmoi.igob.ggcn(7055), vn.ai.trueid.sdk.utils.f.a(Ok.b.y));
        v2_22.put(wmoi.igob.ggcn(7056), vn.ai.trueid.sdk.utils.f.a(Ok.b.z));
        v2_22.put(wmoi.igob.ggcn(7057), vn.ai.trueid.sdk.utils.f.a(Ok.b.A));
        v2_22.put(wmoi.igob.ggcn(7058), vn.ai.trueid.sdk.utils.f.a(Ok.b.B));
        v2_22.put(wmoi.igob.ggcn(7059), vn.ai.trueid.sdk.utils.f.a(Ok.b.C));
        v2_22.put(wmoi.igob.ggcn(7060), vn.ai.trueid.sdk.utils.f.a(Ok.b.D));
        String v3_239 = vn.ai.trueid.sdk.utils.f.a(Ok.b.E);
        String v4_2 = wmoi.igob.ggcn(7061);
        v2_22.put(v4_2, v3_239);
        v2_22.put(wmoi.igob.ggcn(7062), vn.ai.trueid.sdk.utils.f.a(Ok.b.m));
        v2_22.put(wmoi.igob.ggcn(7063), vn.ai.trueid.sdk.utils.f.a(Ok.b.n));
        v2_22.put(wmoi.igob.ggcn(7064), vn.ai.trueid.sdk.utils.f.a(Ok.b.o));
        v2_22.put(wmoi.igob.ggcn(7065), vn.ai.trueid.sdk.utils.f.a(Ok.b.p));
        v2_22.put(wmoi.igob.ggcn(7066), vn.ai.trueid.sdk.utils.f.a(Ok.b.q));
        v2_22.put(wmoi.igob.ggcn(7067), vn.ai.trueid.sdk.utils.f.a(Ok.b.r));
        v2_22.put(wmoi.igob.ggcn(7068), vn.ai.trueid.sdk.utils.f.a(Ok.b.s));
        v2_22.put(wmoi.igob.ggcn(7069), vn.ai.trueid.sdk.utils.f.a(Ok.b.t));
        v2_22.put(wmoi.igob.ggcn(7070), vn.ai.trueid.sdk.utils.f.a(Ok.b.u));
        v2_22.put(wmoi.igob.ggcn(7071), wmoi.igob.ggcn(7072));
        v2_22.put(wmoi.igob.ggcn(7073), "");
        v2_22.put(wmoi.igob.ggcn(7074), "");
        v2_22.put(wmoi.igob.ggcn(7075), "");
        v2_22.put(wmoi.igob.ggcn(7076), vn.ai.trueid.sdk.utils.f.a(Ok.b.v));
        v1_35.put(wmoi.igob.ggcn(7077), v2_22);
        String v2_10 = new org.json.JSONObject();
        v2_10.put(wmoi.igob.ggcn(7078), vn.ai.trueid.sdk.utils.f.a(Ok.b.F));
        v2_10.put(wmoi.igob.ggcn(7079), vn.ai.trueid.sdk.utils.f.a(Ok.b.G));
        v2_10.put(wmoi.igob.ggcn(7080), vn.ai.trueid.sdk.utils.f.a(Ok.b.H));
        v2_10.put(wmoi.igob.ggcn(7081), vn.ai.trueid.sdk.utils.f.a(Ok.i.e));
        v1_35.put(wmoi.igob.ggcn(7082), v2_10);
        String v2_12 = new org.json.JSONObject();
        String v3_51 = ((android.app.ActivityManager) p17.getSystemService(wmoi.igob.ggcn(7083)));
        String v6_13 = new android.app.ActivityManager$MemoryInfo();
        v3_51.getMemoryInfo(v6_13);
        v2_12.put(wmoi.igob.ggcn(7084), vn.ai.trueid.sdk.utils.f.a(new Ok.m(v6_13, 0)));
        v2_12.put(wmoi.igob.ggcn(7085), vn.ai.trueid.sdk.utils.f.a(new Ok.m(v6_13, 1)));
        v2_12.put(wmoi.igob.ggcn(7086), vn.ai.trueid.sdk.utils.f.a(new Ok.m(v6_13, 2)));
        v2_12.put(wmoi.igob.ggcn(7087), vn.ai.trueid.sdk.utils.f.a(Ok.i.j));
        v2_12.put(wmoi.igob.ggcn(7088), vn.ai.trueid.sdk.utils.f.a(Ok.i.k));
        v1_35.put(wmoi.igob.ggcn(7089), v2_12);
        String v2_14 = new org.json.JSONObject();
        String v3_71 = ((android.view.WindowManager) p17.getSystemService(wmoi.igob.ggcn(7090)));
        String v6_21 = new android.util.DisplayMetrics();
        v3_71.getDefaultDisplay().getRealMetrics(v6_21);
        v2_14.put(wmoi.igob.ggcn(7091), vn.ai.trueid.sdk.utils.f.a(new Ok.k(v6_21, 0)));
        v2_14.put(wmoi.igob.ggcn(7092), vn.ai.trueid.sdk.utils.f.a(new Ok.k(v6_21, 1)));
        v2_14.put(wmoi.igob.ggcn(7093), vn.ai.trueid.sdk.utils.f.a(new Ok.k(v6_21, 2)));
        v2_14.put(wmoi.igob.ggcn(7094), "");
        v2_14.put(wmoi.igob.ggcn(7095), "");
        v2_14.put(wmoi.igob.ggcn(7096), vn.ai.trueid.sdk.utils.f.a(new Ok.l(v3_71, 0)));
        v2_14.put(wmoi.igob.ggcn(7097), vn.ai.trueid.sdk.utils.f.a(new Ok.l(v3_71, 1)));
        v1_35.put(v4_2, v2_14);
        String v2_17 = new org.json.JSONObject();
        v2_17.put(wmoi.igob.ggcn(7099), vn.ai.trueid.sdk.utils.f.b(wmoi.igob.ggcn(7098)));
        v2_17.put(wmoi.igob.ggcn(7101), vn.ai.trueid.sdk.utils.f.b(wmoi.igob.ggcn(7100)));
        v2_17.put(wmoi.igob.ggcn(7103), vn.ai.trueid.sdk.utils.f.b(wmoi.igob.ggcn(7102)));
        v2_17.put(wmoi.igob.ggcn(7104), "");
        v1_35.put(wmoi.igob.ggcn(7105), v2_17);
        String v2_20 = new org.json.JSONObject();
        String v4_22 = 0;
        String v3_91 = p17.registerReceiver(0, new android.content.IntentFilter(wmoi.igob.ggcn(7106)));
        v2_20.put(wmoi.igob.ggcn(7107), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 1)));
        v2_20.put(wmoi.igob.ggcn(7108), vn.ai.trueid.sdk.utils.f.a(new Ok.g(0, v3_91)));
        v2_20.put(wmoi.igob.ggcn(7109), vn.ai.trueid.sdk.utils.f.a(new Ok.g(1, v3_91)));
        v2_20.put(wmoi.igob.ggcn(7110), vn.ai.trueid.sdk.utils.f.a(new Ok.g(2, v3_91)));
        v2_20.put(wmoi.igob.ggcn(7111), vn.ai.trueid.sdk.utils.f.a(new Ok.g(3, v3_91)));
        v1_35.put(wmoi.igob.ggcn(7112), v2_20);
        String v2_24 = new org.json.JSONObject();
        String v3_98 = new org.json.JSONArray();
        vn.ai.trueid.sdk.utils.f.a(new Ok.h(p17, v3_98, 0));
        v2_24.put(wmoi.igob.ggcn(7113), v3_98);
        v1_35.put(wmoi.igob.ggcn(7114), v2_24);
        String v2_26 = new org.json.JSONObject();
        String v3_102 = new org.json.JSONArray();
        vn.ai.trueid.sdk.utils.f.a(new Ok.h(p17, v3_102, 2));
        v2_26.put(wmoi.igob.ggcn(7115), v3_102);
        v2_26.put(wmoi.igob.ggcn(7116), "");
        vn.ai.trueid.sdk.utils.f.a(new Ai.o(p17, v2_26, 9));
        v1_35.put(wmoi.igob.ggcn(7117), v2_26);
        String v2_28 = new org.json.JSONObject();
        v2_28.put(wmoi.igob.ggcn(7118), vn.ai.trueid.sdk.utils.f.a(Ok.b.l));
        v2_28.put(wmoi.igob.ggcn(7119), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 2)));
        v1_35.put(wmoi.igob.ggcn(7120), v2_28);
        String v2_30 = new org.json.JSONObject();
        v2_30.put(wmoi.igob.ggcn(7121), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 8)));
        v2_30.put(wmoi.igob.ggcn(7122), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 9)));
        v2_30.put(wmoi.igob.ggcn(7123), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 10)));
        v2_30.put(wmoi.igob.ggcn(7124), vn.ai.trueid.sdk.utils.f.a(Ok.i.n));
        v2_30.put(wmoi.igob.ggcn(7125), vn.ai.trueid.sdk.utils.f.a(Ok.i.o));
        v1_35.put(wmoi.igob.ggcn(7126), v2_30);
        String v2_32 = new org.json.JSONObject();
        String v3_136 = vn.ai.trueid.sdk.utils.f.a(Ok.i.h);
        String v6_78 = wmoi.igob.ggcn(7127);
        v2_32.put(v6_78, v3_136);
        v2_32.put(wmoi.igob.ggcn(7128), vn.ai.trueid.sdk.utils.f.a(Ok.i.i));
        v2_32.put(wmoi.igob.ggcn(7129), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 5)));
        v2_32.put(wmoi.igob.ggcn(7130), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 6)));
        v2_32.put(wmoi.igob.ggcn(7131), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 7)));
        v2_32.put(wmoi.igob.ggcn(7132), "");
        v1_35.put(v6_78, v2_32);
        String v2_34 = new org.json.JSONObject();
        v2_34.put(wmoi.igob.ggcn(7133), vn.ai.trueid.sdk.utils.f.a(vn.ai.trueid.sdk.utils.c.d));
        v2_34.put(wmoi.igob.ggcn(7134), String.valueOf(((Boolean) vn.ai.trueid.sdk.utils.f.a(vn.ai.trueid.sdk.utils.d.d)).booleanValue()));
        v2_34.put(wmoi.igob.ggcn(7135), "");
        v2_34.put(wmoi.igob.ggcn(7136), vn.ai.trueid.sdk.utils.f.a(Ok.b.e));
        v2_34.put(wmoi.igob.ggcn(7137), vn.ai.trueid.sdk.utils.f.a(Ok.b.f));
        v2_34.put(wmoi.igob.ggcn(7138), vn.ai.trueid.sdk.utils.f.a(Ok.b.g));
        v2_34.put(wmoi.igob.ggcn(7139), vn.ai.trueid.sdk.utils.f.a(Ok.b.h));
        v2_34.put(wmoi.igob.ggcn(7140), vn.ai.trueid.sdk.utils.f.a(Ok.b.i));
        v2_34.put(wmoi.igob.ggcn(7141), vn.ai.trueid.sdk.utils.f.a(Ok.b.j));
        v2_34.put(wmoi.igob.ggcn(7142), "");
        v1_35.put(wmoi.igob.ggcn(7143), v2_34);
        String v2_36 = new org.json.JSONObject();
        String v3_178 = p17.getPackageManager();
        v2_36.put(wmoi.igob.ggcn(7144), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 3)));
        v2_36.put(wmoi.igob.ggcn(7145), vn.ai.trueid.sdk.utils.f.a(new Ok.j(v3_178, 0)));
        v2_36.put(wmoi.igob.ggcn(7146), vn.ai.trueid.sdk.utils.f.a(new Ok.j(v3_178, 1)));
        v2_36.put(wmoi.igob.ggcn(7147), vn.ai.trueid.sdk.utils.f.a(new Ok.j(v3_178, 2)));
        v2_36.put(wmoi.igob.ggcn(7148), vn.ai.trueid.sdk.utils.f.a(new Ok.j(v3_178, 3)));
        v1_35.put(wmoi.igob.ggcn(7149), v2_36);
        String v2_38 = new org.json.JSONObject();
        v2_38.put(wmoi.igob.ggcn(7150), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 11)));
        v2_38.put(wmoi.igob.ggcn(7151), vn.ai.trueid.sdk.utils.f.a(Ok.i.p));
        v2_38.put(wmoi.igob.ggcn(7152), vn.ai.trueid.sdk.utils.f.a(Ok.i.q));
        v2_38.put(wmoi.igob.ggcn(7153), vn.ai.trueid.sdk.utils.f.a(Ok.i.r));
        v2_38.put(wmoi.igob.ggcn(7154), vn.ai.trueid.sdk.utils.f.a(Ok.i.s));
        v2_38.put(wmoi.igob.ggcn(7155), vn.ai.trueid.sdk.utils.f.a(Ok.i.t));
        v1_35.put(wmoi.igob.ggcn(7156), v2_38);
        String v2_40 = new org.json.JSONObject();
        String v3_205 = ((android.net.ConnectivityManager) p17.getSystemService(wmoi.igob.ggcn(7157)));
        String v6_124 = v3_205.getActiveNetwork();
        if (v6_124 != null) {
            v4_22 = v3_205.getNetworkCapabilities(v6_124);
        }
        v2_40.put(wmoi.igob.ggcn(7158), vn.ai.trueid.sdk.utils.f.a(new Ok.n(v4_22, 0)));
        v2_40.put(wmoi.igob.ggcn(7159), vn.ai.trueid.sdk.utils.f.a(new Ok.n(v4_22, 1)));
        v2_40.put(wmoi.igob.ggcn(7160), vn.ai.trueid.sdk.utils.f.a(new A0.c(29, v3_205)));
        v2_40.put(wmoi.igob.ggcn(7161), "");
        v2_40.put(wmoi.igob.ggcn(7162), vn.ai.trueid.sdk.utils.f.a(Ok.i.l));
        v2_40.put(wmoi.igob.ggcn(7163), vn.ai.trueid.sdk.utils.f.a(Ok.i.m));
        v2_40.put(wmoi.igob.ggcn(7164), vn.ai.trueid.sdk.utils.f.a(new Ok.n(v4_22, 2)));
        v1_35.put(wmoi.igob.ggcn(7165), v2_40);
        String v2_42 = new org.json.JSONObject();
        String v3_220 = p17.getPackageManager();
        v2_42.put(wmoi.igob.ggcn(7166), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 0)));
        v2_42.put(wmoi.igob.ggcn(7167), vn.ai.trueid.sdk.utils.f.a(new Ok.e(v3_220, p17, 0)));
        v2_42.put(wmoi.igob.ggcn(7168), vn.ai.trueid.sdk.utils.f.a(Ok.b.k));
        String v4_51 = ((android.content.pm.PackageInfo) vn.ai.trueid.sdk.utils.f.a(new Ok.e(v3_220, p17, 2)));
        v2_42.put(wmoi.igob.ggcn(7169), vn.ai.trueid.sdk.utils.f.a(new Ok.f(v4_51, 0)));
        v2_42.put(wmoi.igob.ggcn(7170), vn.ai.trueid.sdk.utils.f.a(new Ok.c(v4_51, 1)));
        v2_42.put(wmoi.igob.ggcn(7171), vn.ai.trueid.sdk.utils.f.a(new Ok.f(v4_51, 1)));
        v2_42.put(wmoi.igob.ggcn(7172), vn.ai.trueid.sdk.utils.f.a(new Ok.f(v4_51, 2)));
        v2_42.put(wmoi.igob.ggcn(7173), vn.ai.trueid.sdk.utils.f.a(new Ok.f(v4_51, 3)));
        v2_42.put(wmoi.igob.ggcn(7174), vn.ai.trueid.sdk.utils.f.a(new Ok.e(v3_220, p17, 1)));
        v2_42.put(wmoi.igob.ggcn(7175), vn.ai.trueid.sdk.utils.f.a(new Ok.c(v4_51, 0)));
        v1_35.put(wmoi.igob.ggcn(7176), v2_42);
        String v2_44 = new org.json.JSONObject();
        String v3_231 = new org.json.JSONArray();
        vn.ai.trueid.sdk.utils.f.a(new Ok.h(p17, v3_231, 1));
        v2_44.put(wmoi.igob.ggcn(7177), v3_231);
        v1_35.put(wmoi.igob.ggcn(7178), v2_44);
        String v2_46 = new org.json.JSONObject();
        v2_46.put(wmoi.igob.ggcn(7179), vn.ai.trueid.sdk.utils.f.a(new Ok.d(p17, 12)));
        v2_46.put(wmoi.igob.ggcn(7180), "");
        v1_35.put(wmoi.igob.ggcn(7181), v2_46);
        vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(7182), v1_35.toString(2))}));
        String v2_0 = kotlin.TuplesKt.to(wmoi.igob.ggcn(7183), wmoi.igob.ggcn(7184));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7185), wmoi.igob.ggcn(7186));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7187), wmoi.igob.ggcn(7188));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7189), wmoi.igob.ggcn(7190));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7191), wmoi.igob.ggcn(7192));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7193), wmoi.igob.ggcn(7194));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7195), wmoi.igob.ggcn(7196));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7197), wmoi.igob.ggcn(7198));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7199), wmoi.igob.ggcn(7200));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7201), wmoi.igob.ggcn(7202));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7203), wmoi.igob.ggcn(7204));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7205), wmoi.igob.ggcn(7206));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7207), wmoi.igob.ggcn(7208));
        kotlin.TuplesKt.to(wmoi.igob.ggcn(7209), wmoi.igob.ggcn(7210));
        vn.ai.trueid.sdk.utils.Helpers$Companion v0_34 = kotlin.collections.E.listOf(new kotlin.Pair[] {v2_0, kotlin.TuplesKt.to(wmoi.igob.ggcn(7211), wmoi.igob.ggcn(7212))}));
        Object[] v1_32 = new StringBuilder(androidx.camera.camera2.internal.G.d(v0_34.size(), wmoi.igob.ggcn(7213), wmoi.igob.ggcn(7214)));
        vn.ai.trueid.sdk.utils.Helpers$Companion v0_36 = ((Iterable) v0_34).iterator();
        while (v0_36.hasNext()) {
            String v2_5 = ((kotlin.Pair) v0_36.next());
            v1_32.append(androidx.camera.camera2.internal.G.f(wmoi.igob.ggcn(7217), ((String) v2_5.component1()), wmoi.igob.ggcn(7215), ((String) v2_5.component2()), wmoi.igob.ggcn(7216)));
        }
        vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {v1_32.toString()}));
        return;
    }

    private final boolean isHooked()
    {
        ClassLoader.getSystemClassLoader().loadClass(wmoi.igob.ggcn(7218));
        return 1;
    }

    private final boolean isRooted()
    {
        Process v0_0 = android.os.Build.TAGS;
        int v1 = 1;
        if ((v0_0 == null) || (!kotlin.text.StringsKt.g(v0_0, wmoi.igob.ggcn(7222)))) {
            wmoi.igob.ggcn(7223);
            String v11 = wmoi.igob.ggcn(7224);
            int v2_0 = wmoi.igob.ggcn(7225);
            wmoi.igob.ggcn(7226);
            wmoi.igob.ggcn(7227);
            wmoi.igob.ggcn(7228);
            wmoi.igob.ggcn(7229);
            wmoi.igob.ggcn(7230);
            wmoi.igob.ggcn(7231);
            wmoi.igob.ggcn(7232);
            String v3_0 = 0;
            while (v3_0 < 10) {
                if (!new java.io.File(new String[] {v2_0, v11})[v3_0]).exists()) {
                    v3_0++;
                }
            }
            try {
                Process v0_2 = Runtime.getRuntime().exec(new String[] {wmoi.igob.ggcn(7233), wmoi.igob.ggcn(7234)}));
            } catch (Throwable) {
                if (v0_2 != null) {
                    v0_2.destroy();
                }
                return 0;
            }
            if (new java.io.BufferedReader(new java.io.InputStreamReader(v0_2.getInputStream())).readLine() == null) {
                v1 = 0;
            }
            v0_2.destroy();
            return v1;
        }
        return 1;
    }

    private final String prettyJson(String p8)
    {
        try {
            String v0_0 = p8.length();
            StringBuilder v2_0 = 0;
        } catch (Exception) {
            return p8;
        }
        while (v2_0 < v0_0) {
            int v4_4 = p8.charAt(v2_0);
            if (v4_4 != 123) {
                if (v4_4 != 91) {
                    v2_0++;
                } else {
                }
            }
            if (v2_0 >= null) {
                int v4_0;
                String v0_1 = p8.charAt(v2_0);
                if (v0_1 != 123) {
                    v4_0 = 93;
                } else {
                    v4_0 = 125;
                }
                int v4_1 = kotlin.text.StringsKt.n(p8, v4_0, 0, 6);
                if (v4_1 > v2_0) {
                    String v0_4;
                    String v1_3 = kotlin.text.StringsKt.trimEnd(p8.substring(0, v2_0)).toString();
                    StringBuilder v2_1 = p8.substring(v2_0, (v4_1 + 1));
                    if (v0_1 != 123) {
                        v0_4 = new org.json.JSONArray(v2_1).toString(2);
                    } else {
                        v0_4 = new org.json.JSONObject(v2_1).toString(2);
                    }
                    if (v1_3.length() != 0) {
                        StringBuilder v2_4 = new StringBuilder();
                        v2_4.append(v1_3);
                        v2_4.append(wmoi.igob.ggcn(7235));
                        v2_4.append(v0_4);
                        p8 = v2_4.toString();
                        return p8;
                    } else {
                        return v0_4;
                    }
                } else {
                    return p8;
                }
            } else {
                return p8;
            }
        }
        v2_0 = -1;
    }

    private final java.io.File saveBitmapToFile(android.graphics.Bitmap p5)
    {
        java.io.File v0 = this.initFileImage();
        try {
            v0.createNewFile();
            java.io.FileOutputStream v1_1 = new java.io.FileOutputStream(v0);
            p5.compress(android.graphics.Bitmap$CompressFormat.JPEG, 98, v1_1);
            v1_1.flush();
            v1_1.close();
        } catch (Exception) {
        }
        return v0;
    }

    private final java.io.File saveFileJPEGToInternal(android.graphics.Bitmap p1)
    {
        java.io.File v1_1 = this.saveBitmapToFile(p1);
        this.saveExifDataToFile(v1_1);
        return v1_1;
    }

    private final String trueDataComment()
    {
        String v0_1 = new org.json.JSONObject();
        v0_1.put(wmoi.igob.ggcn(7236), wmoi.igob.ggcn(7237));
        this.getContext();
        Object[] v1_0 = android.os.Build.MANUFACTURER;
        String v2 = android.os.Build.TYPE;
        String v2_10 = android.os.Build.MODEL;
        boolean v3_1 = kotlin.text.y.startsWith$default(v2_10.toLowerCase(), v1_0.toLowerCase(), 0, 2, 0);
        String v4_2 = wmoi.igob.ggcn(7238);
        if (!v3_1) {
            v2_10 = androidx.activity.result.e.o(v1_0, v4_2, v2_10);
        }
        if (v2_10.length() > 0) {
            Object[] v1_16 = kotlin.text.F.first(v2_10);
            if (Character.isLowerCase(v1_16)) {
                Character.toUpperCase(v1_16);
            }
        }
        Object[] v1 = android.os.Build.BOARD;
        v1 = android.os.Build.USER;
        v2 = android.os.Build.TIME;
        v2 = android.os.Build.DISPLAY;
        v0_1.put(wmoi.igob.ggcn(7239), android.os.Build$VERSION.RELEASE);
        this.getContext();
        Object[] v1_19 = android.os.Build.MANUFACTURER;
        v2 = android.os.Build.TYPE;
        String v2_15 = android.os.Build.MODEL;
        if (!kotlin.text.y.startsWith$default(v2_15.toLowerCase(), v1_19.toLowerCase(), 0, 2, 0)) {
            v2_15 = androidx.activity.result.e.o(v1_19, v4_2, v2_15);
        }
        if (v2_15.length() > 0) {
            Object[] v1_21 = kotlin.text.F.first(v2_15);
            if (Character.isLowerCase(v1_21)) {
                Character.toUpperCase(v1_21);
            }
        }
        v1 = android.os.Build.BOARD;
        v1 = android.os.Build.USER;
        v1 = android.os.Build$VERSION.RELEASE;
        v1 = android.os.Build.TIME;
        v0_1.put(wmoi.igob.ggcn(7240), android.os.Build.FINGERPRINT);
        v0_1.put(wmoi.igob.ggcn(7241), this.getAdvId());
        v0_1.put(wmoi.igob.ggcn(7242), this.getAppVersion());
        v0_1.put(wmoi.igob.ggcn(7243), this.getAPP_APPLICATION_ID());
        v0_1.put(wmoi.igob.ggcn(7244), vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release().getAppId());
        v0_1.put(wmoi.igob.ggcn(7245), wmoi.igob.ggcn(7246));
        v0_1.put(wmoi.igob.ggcn(7247), wmoi.igob.ggcn(7248));
        v0_1.put(wmoi.igob.ggcn(7249), wmoi.igob.ggcn(7250));
        this.printLog(new Object[] {v0_1.toString()}));
        return v0_1.toString();
    }

    public final String bitmapToBase64(android.graphics.Bitmap p3)
    {
        java.io.File v3_1 = this.saveFileJPEGToInternal(p3);
        String v0_1 = android.util.Base64.encodeToString(Cg.l.readBytes(v3_1), 2);
        v3_1.exists();
        v3_1.delete();
        return v0_1;
    }

    public final String bytesToBase64(byte[] p2)
    {
        return android.util.Base64.encodeToString(p2, 2);
    }

    public final int checkSecurityDevice()
    {
        if (!this.isRooted()) {
            if (!this.isHooked()) {
                if (!this.isEmulator()) {
                    return 1;
                } else {
                    return 452;
                }
            } else {
                return 451;
            }
        } else {
            return 450;
        }
    }

    public final void clean(String p2)
    {
        android.content.SharedPreferences$Editor v0_1 = this.getPrefs().edit();
        v0_1.remove(p2);
        v0_1.apply();
        v0_1.commit();
        return;
    }

    public final int convertDpToPixel(int p3)
    {
        return ((int) ((((float) this.getContext().getResources().getDisplayMetrics().densityDpi) / ((float) 160)) * ((float) p3)));
    }

    public final float convertPixelsToDp$trueid_release(float p2, android.content.Context p3)
    {
        return (p2 / (((float) p3.getResources().getDisplayMetrics().densityDpi) / ((float) 160)));
    }

    public final String decodeBase64(String p3)
    {
        return new String(android.util.Base64.decode(p3, 0), kotlin.text.Charsets.UTF_8);
    }

    public final varargs void errorLog(Object[] p10)
    {
        android.util.Log.e(wmoi.igob.ggcn(7252), kotlin.collections.y.joinToString$default(p10, wmoi.igob.ggcn(7251), 0, 0, 0, 0, 0, 62, 0));
        return;
    }

    public final String getAPP_APPLICATION_ID()
    {
        int v0_0 = vn.ai.trueid.sdk.utils.Helpers.APP_APPLICATION_ID;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final String getAdvId()
    {
        return vn.ai.trueid.sdk.utils.Helpers.access$getAdvId$cp();
    }

    public final String getAndroidVersion()
    {
        String v0_0 = android.os.Build$VERSION.SDK_INT;
        if (v0_0 < 33) {
            if (v0_0 < 33) {
                if (v0_0 < 32) {
                    if (v0_0 < 31) {
                        if (v0_0 < 30) {
                            if (v0_0 < 29) {
                                if (v0_0 < 28) {
                                    return wmoi.igob.ggcn(7260);
                                } else {
                                    return wmoi.igob.ggcn(7259);
                                }
                            } else {
                                return wmoi.igob.ggcn(7258);
                            }
                        } else {
                            return wmoi.igob.ggcn(7257);
                        }
                    } else {
                        return wmoi.igob.ggcn(7256);
                    }
                } else {
                    return wmoi.igob.ggcn(7255);
                }
            } else {
                return wmoi.igob.ggcn(7254);
            }
        } else {
            return wmoi.igob.ggcn(7253);
        }
    }

    public final String getAppVersion()
    {
        int v0_0 = vn.ai.trueid.sdk.utils.Helpers.AppVersion;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final android.content.Context getContext()
    {
        int v0_0 = vn.ai.trueid.sdk.utils.Helpers.context;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final String getLocalizedString(int p3, java.util.Locale p4)
    {
        android.content.res.Configuration v0_1 = new android.content.res.Configuration(this.getContext().getResources().getConfiguration());
        v0_1.setLocale(p4);
        return this.getContext().createConfigurationContext(v0_1).getResources().getString(p3);
    }

    public final String getNativeLibraryDir(android.content.Context p3)
    {
        String v3_1 = p3.getApplicationInfo();
        this.printLog(new Object[] {androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(7261), v3_1.nativeLibraryDir)}));
        String v3_2 = v3_1.nativeLibraryDir;
        this.printLog(new Object[] {androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(7262), new java.io.File(v3_2).getName())}));
        return new java.io.File(v3_2).getName();
    }

    public final java.io.File getOutputDirectory()
    {
        int v2_0;
        java.io.File v0_2 = this.getContext().getApplicationContext();
        boolean v1_1 = v0_2.getCacheDir();
        if (!v1_1) {
            v2_0 = 0;
        } else {
            v2_0 = new java.io.File(v1_1, wmoi.igob.ggcn(7263));
            v2_0.mkdirs();
        }
        if ((v2_0 == 0) || (!v2_0.exists())) {
            return v0_2.getFilesDir();
        } else {
            return v2_0;
        }
    }

    public final String getPackageName()
    {
        int v0_0 = vn.ai.trueid.sdk.utils.Helpers.packageName;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final String getPackageName(android.content.Context p7)
    {
        String v0_0;
        String v7_2 = p7.getApplicationInfo().packageName;
        String v0_5 = this.getContext().getClass().getPackage();
        if (v0_5 == null) {
            v0_0 = this.getContext().getApplicationInfo().packageName;
        } else {
            v0_0 = v0_5.getName();
            if (v0_0 == null) {
            }
        }
        if ((kotlin.text.y.endsWith$default(v7_2, wmoi.igob.ggcn(7264), 0, 2, 0)) || (kotlin.text.y.endsWith$default(v7_2, wmoi.igob.ggcn(7265), 0, 2, 0))) {
            boolean v1_6 = kotlin.text.StringsKt.M(v7_2);
            if (kotlin.text.y.startsWith$default(v0_0, v1_6, 0, 2, 0)) {
                return v1_6;
            }
        }
        if ((kotlin.text.y.startsWith$default(v0_0, v7_2, 0, 2, 0)) || (!kotlin.text.y.startsWith$default(v7_2, v0_0, 0, 2, 0))) {
            return v7_2;
        } else {
            return v0_0;
        }
    }

    public final android.content.SharedPreferences getPrefs()
    {
        int v0_0 = vn.ai.trueid.sdk.utils.Helpers.prefs;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final int getResId(String p1, Class p2)
    {
        try {
            return p2.getDeclaredField(p1).getInt(p2.getDeclaredField(p1));
        } catch (Exception) {
            return -1;
        }
    }

    public final int getResourceId(String p3, String p4)
    {
        try {
            return this.getContext().getResources().getIdentifier(p3, p4, this.getContext().getPackageName());
        } catch (Exception) {
            return -1;
        }
    }

    public final android.content.res.Resources getResources()
    {
        int v0_0 = vn.ai.trueid.sdk.utils.Helpers.resources;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final native String getSSLCertificatePining(String p0);

    public final String getString(int p5)
    {
        return this.getLocalizedString(p5, new java.util.Locale(android.preference.PreferenceManager.getDefaultSharedPreferences(this.getContext()).getString(wmoi.igob.ggcn(7266), java.util.Locale.getDefault().getLanguage())));
    }

    public final String getString(String p3)
    {
        return vn.ai.trueid.sdk.utils.j.e(this.getContext(), p3, vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getLanguageJSON());
    }

    public final byte[] hexStringToByteArray(String p8)
    {
        int v0 = p8.length();
        byte[] v1_1 = new byte[(v0 / 2)];
        int v2 = 0;
        while (v2 < v0) {
            v1_1[(v2 / 2)] = ((byte) (Character.digit(p8.charAt((v2 + 1)), 16) + (Character.digit(p8.charAt(v2), 16) << 4)));
            v2 += 2;
        }
        return v1_1;
    }

    public final void init(android.content.Context p14)
    {
        DQ.a v1_2 = wmoi.igob.ggcn(7267);
        int v2_1 = wmoi.igob.ggcn(7268);
        String v0_13 = wmoi.igob.ggcn(7269);
        this.setContext(p14);
        this.setResources(p14.getResources());
        this.setPrefs(this.getContext().getSharedPreferences(wmoi.igob.ggcn(7270), 0));
        this.setPackageName(this.getPackageName(p14));
        this.setDebug(1);
        android.content.pm.PackageInfo v3_5 = p14.getPackageManager().getPackageInfo(p14.getPackageName(), 1);
        StringBuilder v4_2 = v3_5.packageName;
        String v6_1 = v3_5.versionName;
        String v7_1 = new StringBuilder(v0_13);
        v7_1.append(v4_2);
        v7_1.append(wmoi.igob.ggcn(7271));
        v7_1.append(v6_1);
        this.printLog(new Object[] {v7_1.toString()}));
        try {
            String v0_7 = this.getPackageName();
            StringBuilder v4_4 = new StringBuilder();
            v4_4.append(v0_7);
            v4_4.append(wmoi.igob.ggcn(7272));
            String v0_11 = kotlin.jvm.internal.M.a.b(Class.forName(kotlin.text.y.replace$default(v4_4.toString(), wmoi.igob.ggcn(7273), wmoi.igob.ggcn(7274), 0, 4, 0)));
        } catch (String v0_12) {
            v0_11 = kotlin.ResultKt.createFailure(v0_12);
        }
        if ((v0_11 instanceof ng.K)) {
            v0_11 = 0;
        }
        String v0_14 = ((kotlin.reflect.d) v0_11);
        if (v0_14 != null) {
            StringBuilder v4_8 = new StringBuilder(v2_1);
            v4_8.append(v0_14);
            this.printLog(new Object[] {v4_8.toString()}));
        }
        this.setAppVersion(String.valueOf(v3_5.versionCode));
        this.setAPP_APPLICATION_ID(v3_5.packageName);
        this.setAdvId(this.getDeviceUUID(p14));
        new Thread(new DQ.a(p14, 2)).start();
        return;
    }

    public final java.io.File initFileImage()
    {
        java.io.File v2_1 = new java.io.File(this.getOutputDirectory(), SP.n.h(System.currentTimeMillis(), wmoi.igob.ggcn(7275), wmoi.igob.ggcn(7276)));
        Object[] v0_2 = new StringBuilder(wmoi.igob.ggcn(7277));
        v0_2.append(v2_1);
        this.printLog(new Object[] {v0_2.toString()}));
        return v2_1;
    }

    public final java.io.File initFileImage(String p3)
    {
        java.io.File v1_1 = new java.io.File(this.getOutputDirectory(), p3);
        Object[] v3_4 = new StringBuilder(wmoi.igob.ggcn(7278));
        v3_4.append(v1_1);
        this.printLog(new Object[] {v3_4.toString()}));
        return v1_1;
    }

    public final boolean isActivityInitialised()
    {
        if (vn.ai.trueid.sdk.utils.Helpers.context == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean isDebug()
    {
        return vn.ai.trueid.sdk.utils.Helpers.access$isDebug$cp();
    }

    public final boolean isEmulator()
    {
        boolean v0_0 = android.os.Build.FINGERPRINT;
        String v1_1 = wmoi.igob.ggcn(7279);
        if ((!kotlin.text.y.startsWith$default(v0_0, v1_1, 0, 2, 0)) && (!kotlin.text.y.startsWith$default(v0_0, wmoi.igob.ggcn(7280), 0, 2, 0))) {
            boolean v0_2 = android.os.Build.MODEL;
            String v5_3 = wmoi.igob.ggcn(7281);
            if (((!kotlin.text.StringsKt.g(v0_2, v5_3)) && ((!kotlin.text.StringsKt.g(v0_2, wmoi.igob.ggcn(7282))) && ((!kotlin.text.StringsKt.g(v0_2, wmoi.igob.ggcn(7283))) && ((!kotlin.text.StringsKt.g(android.os.Build.MANUFACTURER, wmoi.igob.ggcn(7284))) && ((!kotlin.text.y.startsWith$default(android.os.Build.BRAND, v1_1, 0, 2, 0)) || (!kotlin.text.y.startsWith$default(android.os.Build.DEVICE, v1_1, 0, 2, 0))))))) && (!kotlin.jvm.internal.Intrinsics.areEqual(v5_3, android.os.Build.PRODUCT))) {
                boolean v0_12 = android.os.Build.HARDWARE;
                if ((!kotlin.text.StringsKt.g(v0_12, wmoi.igob.ggcn(7285))) && ((!kotlin.text.StringsKt.g(v0_12, wmoi.igob.ggcn(7286))) && (!kotlin.text.StringsKt.g(v0_12, wmoi.igob.ggcn(7287))))) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public final String loadJsonString(String p3)
    {
        return this.getPrefs().getString(p3, wmoi.igob.ggcn(7288));
    }

    public final String md5(String p4)
    {
        return kotlin.text.StringsKt.q(32, new java.math.BigInteger(1, java.security.MessageDigest.getInstance(wmoi.igob.ggcn(7289)).digest(p4.getBytes(kotlin.text.Charsets.UTF_8))).toString(16));
    }

    public final boolean parseAndSaveNfcInfo(org.json.JSONObject p30)
    {
        String v2_0 = vn.ai.trueid.sdk.utils.Helpers$Companion.qxcct;
        this.printLog(new Object[] {vn.ai.trueid.sdk.utils.Helpers$Companion.cweus}));
        fk.b.q = 0;
        try {
            boolean v5_1 = this.getValueInt(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.cawx);
            int vtmp3 = this.getValueInt(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.pqhi);
        } catch (Exception) {
            return 0;
        }
        if ((v5_1) || ((vtmp3 != 0) || (!p30.has(v2_0)))) {
            return 1;
        } else {
            fk.b.q = new vn.ai.trueid.sdk.models.NfcInfo(this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.ceeez), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.xsqjm), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.ivwsjxy), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.nwiurs), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.zezqj), this.getValueString(p30, v2_0), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.oiusujp), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.wbpjo), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.qvvw), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.akcwovf), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.uswldm), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.onknujh), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.vtrk), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.dvxe), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.wsfh), this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.aroan), 0, this.getValueString(p30, vn.ai.trueid.sdk.utils.Helpers$Companion.saysoez), 65536, 0);
            return 1;
        }
    }

    public final varargs void printLog(Object[] p11)
    {
        if (this.isDebug()) {
            String v11_2 = this.prettyJson(kotlin.collections.y.joinToString$default(p11, wmoi.igob.ggcn(7290), 0, 0, 0, 0, 0, 62, 0));
            vn.ai.trueid.sdk.navigation.TrueIDLogger v0_6 = ((Iterable) kotlin.text.StringsKt.y(v11_2, new String[] {wmoi.igob.ggcn(7291)}), 0, 6)).iterator();
            while (v0_6.hasNext()) {
                v0_6.next();
            }
            vn.ai.trueid.sdk.navigation.TrueIDLogger.INSTANCE.post$trueid_release(v11_2);
        }
        return;
    }

    public final String provideUserAgent()
    {
        return androidx.camera.camera2.internal.G.f(wmoi.igob.ggcn(7294), this.getAndroidVersion(), wmoi.igob.ggcn(7292), android.os.Build.MODEL, wmoi.igob.ggcn(7293));
    }

    public final void saveData(String p5, String p6)
    {
        try {
            String v1_2 = this.getOutputDirectory().getPath();
            StringBuilder v2_1 = new StringBuilder();
            v2_1.append(v1_2);
            v2_1.append(wmoi.igob.ggcn(7295));
            v2_1.append(p5);
            java.io.FileWriter v0_1 = new java.io.FileWriter(v2_1.toString());
            v0_1.write(p6);
            v0_1.flush();
            v0_1.close();
        } catch (java.io.IOException) {
        }
        return;
    }

    public final void saveExifDataToFile(java.io.File p10)
    {
        android.media.ExifInterface v0_1 = new android.media.ExifInterface(p10.getAbsolutePath());
        v0_1.setAttribute(wmoi.igob.ggcn(7296), wmoi.igob.ggcn(7297));
        this.getContext();
        String v10_3 = android.os.Build.MANUFACTURER;
        int v1 = android.os.Build.TYPE;
        int v1_13 = android.os.Build.MODEL;
        String v6 = 0;
        String v2_11 = kotlin.text.y.startsWith$default(v1_13.toLowerCase(), v10_3.toLowerCase(), 0, 2, 0);
        String v3_8 = wmoi.igob.ggcn(7298);
        if (v2_11 == null) {
            v1_13 = androidx.activity.result.e.o(v10_3, v3_8, v1_13);
        }
        if (v1_13.length() > 0) {
            int v1_22 = kotlin.text.F.first(v1_13);
            if (Character.isLowerCase(v1_22)) {
                Character.toUpperCase(v1_22);
            }
        }
        v1 = android.os.Build.BOARD;
        v1 = android.os.Build.USER;
        v1 = android.os.Build$VERSION.RELEASE;
        v1 = android.os.Build.TIME;
        v1 = android.os.Build.DISPLAY;
        v0_1.setAttribute(wmoi.igob.ggcn(7299), v10_3);
        this.getContext();
        String v10_27 = android.os.Build.MANUFACTURER;
        v1 = android.os.Build.TYPE;
        int v1_25 = android.os.Build.MODEL;
        if (!kotlin.text.y.startsWith$default(v1_25.toLowerCase(), v10_27.toLowerCase(), 0, 2, 0)) {
            v1_25 = androidx.activity.result.e.o(v10_27, v3_8, v1_25);
        }
        if (v1_25.length() > 0) {
            String v10_30 = kotlin.text.F.first(v1_25);
            if (Character.isLowerCase(v10_30)) {
                Character.toUpperCase(v10_30);
            }
        }
        p10 = android.os.Build.BOARD;
        p10 = android.os.Build.USER;
        p10 = android.os.Build$VERSION.RELEASE;
        String v7 = android.os.Build.TIME;
        p10 = android.os.Build.DISPLAY;
        v0_1.setAttribute(wmoi.igob.ggcn(7300), v1_25);
        this.getContext();
        String v10_33 = android.os.Build.MANUFACTURER;
        v1 = android.os.Build.TYPE;
        int v1_0 = android.os.Build.MODEL;
        if (!kotlin.text.y.startsWith$default(v1_0.toLowerCase(), v10_33.toLowerCase(), 0, 2, 0)) {
            v1_0 = androidx.activity.result.e.o(v10_33, v3_8, v1_0);
        }
        if (v1_0.length() > 0) {
            String v10_2 = kotlin.text.F.first(v1_0);
            if (Character.isLowerCase(v10_2)) {
                Character.toUpperCase(v10_2);
            }
        }
        String v10_10;
        p10 = android.os.Build.BOARD;
        p10 = android.os.Build.USER;
        p10 = android.os.Build$VERSION.RELEASE;
        v1 = android.os.Build.TIME;
        v1 = android.os.Build.DISPLAY;
        int v1_3 = new StringBuilder(wmoi.igob.ggcn(7301));
        v1_3.append(android.os.Build$VERSION.SDK_INT);
        v1_3.append(wmoi.igob.ggcn(7302));
        v0_1.setAttribute(wmoi.igob.ggcn(7303), v1_3.toString());
        String v10_8 = android.os.Build$VERSION.SDK_INT;
        if (v10_8 < 34) {
            if (v10_8 < 33) {
                if (v10_8 < 32) {
                    if (v10_8 < 31) {
                        if (v10_8 < 30) {
                            if (v10_8 < 29) {
                                if (v10_8 < 28) {
                                    v10_10 = wmoi.igob.ggcn(7311);
                                } else {
                                    v10_10 = wmoi.igob.ggcn(7310);
                                }
                            } else {
                                v10_10 = wmoi.igob.ggcn(7309);
                            }
                        } else {
                            v10_10 = wmoi.igob.ggcn(7308);
                        }
                    } else {
                        v10_10 = wmoi.igob.ggcn(7307);
                    }
                } else {
                    v10_10 = wmoi.igob.ggcn(7306);
                }
            } else {
                v10_10 = wmoi.igob.ggcn(7305);
            }
        } else {
            v10_10 = wmoi.igob.ggcn(7304);
        }
        String v2_8 = android.preference.PreferenceManager.getDefaultSharedPreferences(vn.ai.trueid.sdk.utils.Helpers.Companion.getContext()).getString(wmoi.igob.ggcn(7312), java.util.Locale.getDefault().getLanguage());
        if (v2_8 != null) {
            v6 = new java.util.Locale(v2_8).getDisplayLanguage();
        }
        String v10_19 = androidx.camera.camera2.internal.G.i(wmoi.igob.ggcn(7315), v10_10, wmoi.igob.ggcn(7313), android.os.Build.MODEL, wmoi.igob.ggcn(7314));
        v10_19.append(v6);
        v0_1.setAttribute(wmoi.igob.ggcn(7316), v10_19.toString());
        v0_1.setAttribute(wmoi.igob.ggcn(7317), String.valueOf(System.currentTimeMillis()));
        v0_1.setAttribute(wmoi.igob.ggcn(7318), this.trueDataComment());
        v0_1.setAttribute(wmoi.igob.ggcn(7319), this.trueDataComment());
        v0_1.saveAttributes();
        return;
    }

    public final void saveString(String p2, String p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.getPrefs().edit();
        v0_1.putString(p2, p3);
        v0_1.apply();
        v0_1.commit();
        return;
    }

    public final void setAPP_APPLICATION_ID(String p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.APP_APPLICATION_ID = p1;
        return;
    }

    public final void setAdvId(String p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.access$setAdvId$cp(p1);
        return;
    }

    public final void setAppVersion(String p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.AppVersion = p1;
        return;
    }

    public final void setContext(android.content.Context p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.context = p1;
        return;
    }

    public final void setDebug(boolean p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.access$setDebug$cp(p1);
        return;
    }

    public final void setPackageName(String p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.packageName = p1;
        return;
    }

    public final void setPrefs(android.content.SharedPreferences p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.prefs = p1;
        return;
    }

    public final void setResources(android.content.res.Resources p1)
    {
        vn.ai.trueid.sdk.utils.Helpers.resources = p1;
        return;
    }

    public final varargs void trueIdLog(Object[] p10)
    {
        String v10_2 = this.prettyJson(kotlin.collections.y.joinToString$default(p10, wmoi.igob.ggcn(7320), 0, 0, 0, 0, 0, 62, 0));
        vn.ai.trueid.sdk.navigation.TrueIDLogger v0_6 = ((Iterable) kotlin.text.StringsKt.y(v10_2, new String[] {wmoi.igob.ggcn(7321)}), 0, 6)).iterator();
        while (v0_6.hasNext()) {
            v0_6.next();
        }
        vn.ai.trueid.sdk.navigation.TrueIDLogger.INSTANCE.post$trueid_release(v10_2);
        return;
    }

    public final boolean validConfig(vn.ai.trueid.sdk.models.EkycConfig p2)
    {
        if ((p2.getDomain().length() != 0) && ((!kotlin.text.StringsKt.isBlank(p2.getDomain())) && (android.webkit.URLUtil.isValidUrl(p2.getDomain())))) {
            return 1;
        } else {
            this.errorLog(new Object[] {androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(7322), p2.getDomain())}));
            return 0;
        }
    }

    public final boolean verifyInternal()
    {
        if (vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDeviceNative() != 1) {
            return 0;
        } else {
            return 1;
        }
    }
}

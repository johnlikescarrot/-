package vn.ai.trueid.sdk.liveness.networks;
public abstract class Helpers$Companion {

    public static varargs void a(Object[] p9)
    {
        android.util.Log.e(wmoi.igob.ggcn(6466), kotlin.collections.y.joinToString$default(p9, wmoi.igob.ggcn(6465), 0, 0, 0, 0, 0, 62, 0));
        return;
    }

    public static String b(android.app.Activity p7)
    {
        String v7_2;
        String v7_11 = android.provider.Settings$Secure.getString(p7.getContentResolver(), wmoi.igob.ggcn(6467));
        if ((v7_11 != null) && (v7_11.length() != 0)) {
            try {
                v7_2 = java.util.UUID.nameUUIDFromBytes(v7_11.getBytes(java.nio.charset.Charset.forName(wmoi.igob.ggcn(6468)))).toString();
            } catch (String v7_8) {
                v7_8.printStackTrace();
                v7_2 = 0;
            }
        }
        if ((v7_2 == null) || (v7_2.length() == 0)) {
            String v0_17;
            String v7_10 = new StringBuilder(wmoi.igob.ggcn(6469));
            v7_10.append((android.os.Build.BOARD.length() % 10));
            v7_10.append((android.os.Build.BRAND.length() % 10));
            String v0_16 = android.os.Build.CPU_ABI;
            if (v0_16 == null) {
                v0_17 = 0;
            } else {
                v0_17 = v0_16.length();
            }
            v7_10.append((v0_17 % 10));
            v7_10.append((android.os.Build.DEVICE.length() % 10));
            v7_10.append((android.os.Build.DISPLAY.length() % 10));
            v7_10.append((android.os.Build.HOST.length() % 10));
            v7_10.append((android.os.Build.ID.length() % 10));
            v7_10.append((android.os.Build.MANUFACTURER.length() % 10));
            v7_10.append((android.os.Build.MODEL.length() % 10));
            v7_10.append((android.os.Build.PRODUCT.length() % 10));
            v7_10.append((android.os.Build.TAGS.length() % 10));
            v7_10.append((android.os.Build.TYPE.length() % 10));
            v7_10.append((android.os.Build.USER.length() % 10));
            v7_2 = v7_10.toString();
            if (android.os.Build$VERSION.SDK_INT > 29) {
                String v0_1 = wmoi.igob.ggcn(6471);
            } else {
                try {
                    v7_2 = new java.util.UUID(((long) v7_2.hashCode()), ((long) android.os.Build.getSerial().hashCode())).toString();
                } catch (Exception) {
                    v0_1 = wmoi.igob.ggcn(6470);
                }
                if ((v7_2 == null) || (v7_2.length() == 0)) {
                    v7_2 = java.util.UUID.randomUUID().toString();
                }
                return v7_2;
            }
            v7_2 = new java.util.UUID(((long) v7_2.hashCode()), ((long) v0_1.hashCode())).toString();
        }
    }

    public static android.content.res.Resources c()
    {
        int v1 = vn.ai.trueid.sdk.liveness.networks.g.b;
        if (v1 == 0) {
            v1 = 0;
        }
        return vn.ai.trueid.sdk.face.present.presentation.utils.LocaleHelper.INSTANCE.getResources(v1);
    }

    public static void d(android.app.Activity p10)
    {
        vn.ai.trueid.sdk.liveness.networks.g.b = p10;
        String v0_7 = wmoi.igob.ggcn(6472);
        p10.getSharedPreferences(v0_7, 0);
        Object[] v2_2 = p10.getApplicationInfo().packageName;
        android.content.pm.PermissionInfo[] v3_4 = vn.ai.trueid.sdk.liveness.networks.g.b;
        boolean v4_0 = 0;
        if (v3_4 == null) {
            v3_4 = 0;
        }
        android.content.pm.PermissionInfo[] v3_7;
        android.content.pm.PermissionInfo[] v3_6 = v3_4.getClass().getPackage();
        if (v3_6 == null) {
            android.content.pm.PermissionInfo[] v3_8 = vn.ai.trueid.sdk.liveness.networks.g.b;
            if (v3_8 == null) {
                v3_8 = 0;
            }
            v3_7 = v3_8.getApplicationInfo().packageName;
        } else {
            v3_7 = v3_6.getName();
            if (v3_7 == null) {
            }
        }
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v2_2, v3_7)) {
            int v5_1 = 0;
            int v6 = 0;
            while ((v5_1 < v2_2.length()) && (v5_1 < v3_7.length())) {
                if (v2_2.charAt(v5_1) == v3_7.charAt(v5_1)) {
                    v6++;
                }
                v5_1++;
            }
            v2_2 = v2_2.substring(0, v6);
        }
        Object[] v2_6;
        vn.ai.trueid.sdk.liveness.networks.g.e = v2_2;
        if ((p10.getApplicationInfo().flags & 2) == 0) {
            v2_6 = 0;
        } else {
            v2_6 = 1;
        }
        vn.ai.trueid.sdk.liveness.networks.g.a = v2_6;
        Object[] v2_8 = new StringBuilder(wmoi.igob.ggcn(6473));
        int v5_3 = vn.ai.trueid.sdk.liveness.networks.g.e;
        if (v5_3 != 0) {
            v4_0 = v5_3;
        }
        v2_8.append(v4_0);
        vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.e(new Object[] {v2_8.toString()}));
        Object[] v2_13 = new StringBuilder(wmoi.igob.ggcn(6474));
        v2_13.append(vn.ai.trueid.sdk.liveness.networks.g.a);
        android.util.Log.wtf(v0_7, v2_13.toString());
        try {
            String v0_9 = p10.getPackageManager().getPackageInfo(p10.getPackageName(), 1);
            Object[] v2_17 = new StringBuilder();
            v2_17.append(wmoi.igob.ggcn(6475));
            v2_17.append(v0_9.packageName);
            v2_17.append(wmoi.igob.ggcn(6476));
            v2_17.append(v0_9.versionCode);
            v2_17.append(wmoi.igob.ggcn(6477));
            v2_17.append(v0_9.versionName);
            v2_17.append(wmoi.igob.ggcn(6478));
            v2_17.append(v0_9.permissions);
            vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.e(new Object[] {v2_17.toString()}));
            vn.ai.trueid.sdk.liveness.networks.g.d = String.valueOf(v0_9.versionCode);
        } catch (String v0_2) {
            v0_2.printStackTrace();
            vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.e(new Object[] {kotlin.Unit.INSTANCE}));
            vn.ai.trueid.sdk.liveness.networks.g.a = 0;
            vn.ai.trueid.sdk.liveness.networks.g.d = wmoi.igob.ggcn(6479);
        } catch (String v0_6) {
            vn.ai.trueid.sdk.liveness.networks.g.c = vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.b(p10);
            throw v0_6;
        }
        vn.ai.trueid.sdk.liveness.networks.g.c = vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.b(p10);
        return;
    }

    public static varargs void e(Object[] p10)
    {
        if (vn.ai.trueid.sdk.liveness.networks.g.a) {
            kotlin.collections.y.joinToString$default(p10, wmoi.igob.ggcn(6480), 0, 0, 0, 0, 0, 62, 0);
        }
        return;
    }

    public static String f()
    {
        String v1_2;
        String v0_1 = new StringBuilder(wmoi.igob.ggcn(6481));
        String v1_14 = android.os.Build$VERSION.SDK_INT;
        if (v1_14 < 34) {
            if (v1_14 < 33) {
                if (v1_14 < 32) {
                    if (v1_14 < 31) {
                        if (v1_14 < 30) {
                            if (v1_14 < 29) {
                                if (v1_14 < 28) {
                                    v1_2 = wmoi.igob.ggcn(6489);
                                } else {
                                    v1_2 = wmoi.igob.ggcn(6488);
                                }
                            } else {
                                v1_2 = wmoi.igob.ggcn(6487);
                            }
                        } else {
                            v1_2 = wmoi.igob.ggcn(6486);
                        }
                    } else {
                        v1_2 = wmoi.igob.ggcn(6485);
                    }
                } else {
                    v1_2 = wmoi.igob.ggcn(6484);
                }
            } else {
                v1_2 = wmoi.igob.ggcn(6483);
            }
        } else {
            v1_2 = wmoi.igob.ggcn(6482);
        }
        v0_1.append(v1_2);
        v0_1.append(wmoi.igob.ggcn(6490));
        v0_1.append(android.os.Build.MODEL);
        v0_1.append(wmoi.igob.ggcn(6491));
        v0_1.append(vn.ai.trueid.sdk.face.navigation.FaceSDK.infoSDK());
        v0_1.append(32);
        return v0_1.toString();
    }
}

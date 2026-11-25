package vn.ai.trueid.sdk.face.navigation;
public final class FaceSDK {
    public static final vn.ai.trueid.sdk.face.navigation.FaceSDK INSTANCE;
    private static vn.ai.trueid.sdk.face.navigation.SDKCallback callbackResult;
    private static vn.ai.trueid.sdk.face.e cameraSettings;
    private static kotlin.jvm.functions.Function0 closeSDK;
    private static boolean isConfigInit;
    private static boolean isSendCallback;
    private static boolean isStart;
    private static org.json.JSONObject languageJSON;
    private static vn.ai.trueid.sdk.face.p0 livenessConfig;
    private static vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig sdkConfig;
    private static kotlin.jvm.functions.Function1 showError;
    private static kotlin.jvm.functions.Function0 showSuccess;
    private static long startTime;
    private static kotlin.jvm.functions.Function0 timeoutSDK;

    public static FaceSDK()
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE = new vn.ai.trueid.sdk.face.navigation.FaceSDK();
        return;
    }

    private FaceSDK()
    {
        return;
    }

    public static synthetic void a()
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.postResultCallback$lambda$1();
        return;
    }

    public static synthetic void b()
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.startFaceAuthen$lambda$0();
        return;
    }

    public static final void configure(android.content.Context p2, vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig p3)
    {
        try {
            vn.ai.trueid.sdk.face.navigation.FaceSDK.sdkConfig = p3;
            vn.ai.trueid.sdk.face.navigation.FaceSDK.isConfigInit = 1;
        } catch (String v3_9) {
            v3_9.printStackTrace();
            vn.ai.trueid.sdk.face.navigation.FaceSDK.isConfigInit = 0;
            if (vn.ai.trueid.sdk.face.navigation.FaceSDK.sdkConfig == null) {
                return;
            } else {
                String v3_4 = new StringBuilder("config INIT ");
                v3_4.append(vn.ai.trueid.sdk.face.navigation.FaceSDK.isConfigInit);
                android.util.Log.e("FaceSDK", v3_4.toString());
                return;
            }
        } catch (String v3_2) {
            if (vn.ai.trueid.sdk.face.navigation.FaceSDK.sdkConfig != null) {
                StringBuilder v1_2 = new StringBuilder("config INIT ");
                v1_2.append(vn.ai.trueid.sdk.face.navigation.FaceSDK.isConfigInit);
                android.util.Log.e("FaceSDK", v1_2.toString());
            }
            throw v3_2;
        }
        if (p3 == null) {
            return;
        } else {
            String v3_7 = new StringBuilder("config INIT ");
            v3_7.append(vn.ai.trueid.sdk.face.navigation.FaceSDK.isConfigInit);
            android.util.Log.e("FaceSDK", v3_7.toString());
            return;
        }
    }

    public static synthetic String getNonce$default(vn.ai.trueid.sdk.face.navigation.FaceSDK p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p2 = "1";
        }
        return p0.getNonce(p1, p2);
    }

    public static final String info(android.content.Context p4)
    {
        org.json.JSONObject v0_3 = new StringBuilder("BUILD:AND|DEBUGGABLE:");
        v0_3.append(vn.ai.trueid.sdk.face.navigation.FaceSDK.isDebuggable(p4));
        v0_3.append("|PACKAGE:vn.ai.trueid.sdk.face|SDK:1.7.3|SDK:26022313");
        String v4_5 = v0_3.toString();
        org.json.JSONObject v0_2 = new org.json.JSONObject();
        v0_2.put("version", "1.7.3");
        v0_2.put("date", "26022313");
        v0_2.put("package", "vn.ai.trueid.sdk.face");
        String v1_6 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getClass().getSimpleName();
        StringBuilder v2_4 = new StringBuilder("vn.ai.trueid.sdk.face # ");
        v2_4.append(v4_5);
        android.util.Log.w(v1_6, v2_4.toString());
        return v0_2.toString();
    }

    public static final String infoSDK()
    {
        return "1.7.3-26022313";
    }

    public static final boolean isDebuggable(android.content.Context p0)
    {
        if ((p0.getApplicationInfo().flags & 2) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static final boolean isJSONValid(String p1)
    {
        new org.json.JSONObject(p1);
    }

    private final boolean listFilesInAssets(android.content.Context p3, String p4)
    {
        try {
            int v3_9 = p3.getAssets().list("");
        } catch (int v3_3) {
            android.util.Log.e("Assets", "Error listing files in assets folder", v3_3);
            return 0;
        }
        if (v3_9 == 0) {
            return 0;
        } else {
            P.Q v0_3 = new P.Q(2, v3_9);
            while (v0_3.hasNext()) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(((String) v0_3.next()), p4)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    private static final void postResultCallback$lambda$1()
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.isSendCallback = 0;
        return;
    }

    public static final void setLanguage(android.content.Context p1, String p2)
    {
        if (!vn.ai.trueid.sdk.face.navigation.FaceSDK.isJSONValid(p2)) {
            vn.ai.trueid.sdk.face.present.presentation.utils.LocaleHelper.INSTANCE.setLocale(p1, p2);
            return;
        } else {
            vn.ai.trueid.sdk.face.navigation.FaceSDK.languageJSON = new org.json.JSONObject(p2);
            return;
        }
    }

    public static final void startFaceAuthen(android.content.Context p4, vn.ai.trueid.sdk.face.navigation.SDKCallback p5)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.info(p4);
        if (vn.ai.trueid.sdk.face.navigation.FaceSDK.isConfigInit) {
            if (!vn.ai.trueid.sdk.face.navigation.FaceSDK.isStart) {
                vn.ai.trueid.sdk.face.navigation.FaceSDK.isStart = 1;
            }
            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new hk.a(0), 1500);
            p4.startActivity(new android.content.Intent(p4, vn.ai.trueid.sdk.face.present.presentation.LivenessCameraXActivity));
            vn.ai.trueid.sdk.face.navigation.FaceSDK.callbackResult = p5;
            vn.ai.trueid.sdk.face.navigation.FaceSDK.isSendCallback = 0;
            return;
        } else {
            android.widget.Toast.makeText(p4, "SDK1.7.3 need be call configure first ", 1).show();
            return;
        }
    }

    private static final void startFaceAuthen$lambda$0()
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.isStart = 0;
        return;
    }

    public final vn.ai.trueid.sdk.face.e getCameraSetting$face_release()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.cameraSettings;
    }

    public final kotlin.jvm.functions.Function0 getCloseSDK()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.closeSDK;
    }

    public final vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig getConfig$face_release()
    {
        vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig v0 = vn.ai.trueid.sdk.face.navigation.FaceSDK.sdkConfig;
        if (v0 != null) {
            return v0;
        } else {
            return new vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig(0, 0, 0, 0, 0, 0, 0, 0, 255, 0);
        }
    }

    public final org.json.JSONObject getLanguageJSON()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.languageJSON;
    }

    public final vn.ai.trueid.sdk.face.p0 getLivenessConfig$face_release()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.livenessConfig;
    }

    public final String getNonce(String p2, String p3)
    {
        if ((p2.length() != 0) && (!kotlin.text.StringsKt.isBlank(p2))) {
            return vn.ai.trueid.sdk.face.h.a(p2, p3);
        } else {
            return "";
        }
    }

    public final kotlin.jvm.functions.Function1 getShowError()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.showError;
    }

    public final kotlin.jvm.functions.Function0 getShowSuccess()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.showSuccess;
    }

    public final long getStartTime()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.startTime;
    }

    public final kotlin.jvm.functions.Function0 getTimeoutSDK()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.timeoutSDK;
    }

    public final String makeSignature(String p1, String p2, String p3, java.util.List p4)
    {
        return vn.ai.trueid.sdk.face.h.a(p1, p2, p3, p4);
    }

    public final void postResultCallback$face_release(vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult p4)
    {
        if (!vn.ai.trueid.sdk.face.navigation.FaceSDK.isSendCallback) {
            vn.ai.trueid.sdk.face.navigation.FaceSDK.isSendCallback = 1;
            Ek.b v0_4 = vn.ai.trueid.sdk.face.navigation.FaceSDK.callbackResult;
            if (v0_4 != null) {
                v0_4.complete(p4);
            }
        }
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Ek.b(29), 1500);
        return;
    }

    public final void removeAction()
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.showSuccess = 0;
        vn.ai.trueid.sdk.face.navigation.FaceSDK.closeSDK = 0;
        vn.ai.trueid.sdk.face.navigation.FaceSDK.showError = 0;
        return;
    }

    public final void setCloseSDK(kotlin.jvm.functions.Function0 p1)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.closeSDK = p1;
        return;
    }

    public final void setLanguageJSON(org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.languageJSON = p1;
        return;
    }

    public final void setShowError(kotlin.jvm.functions.Function1 p1)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.showError = p1;
        return;
    }

    public final void setShowSuccess(kotlin.jvm.functions.Function0 p1)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.showSuccess = p1;
        return;
    }

    public final void setStartTime(long p1)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.startTime = p1;
        return;
    }

    public final void setTimeoutSDK(kotlin.jvm.functions.Function0 p1)
    {
        vn.ai.trueid.sdk.face.navigation.FaceSDK.timeoutSDK = p1;
        return;
    }
}

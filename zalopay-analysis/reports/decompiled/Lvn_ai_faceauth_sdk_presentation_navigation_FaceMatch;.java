package vn.ai.faceauth.sdk.presentation.navigation;
public final class FaceMatch {
    public static final vn.ai.faceauth.sdk.presentation.navigation.FaceMatch INSTANCE;
    public static String cafgidp;
    private static vn.ai.faceauth.sdk.presentation.navigation.SDKCallback callbackResult;
    public static String cbih;
    public static String cdgo;
    public static String cgjhnke;
    private static vn.ai.faceauth.sdk.i imageProcessor;
    public static String nrdliu;
    private static int retrycount;
    private static vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig sdkConfig;
    private static long timeStart;

    static FaceMatch()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(28);
        jipm.njcx.vxud(vn.ai.faceauth.sdk.presentation.navigation.FaceMatch, 187, 191);
        vn.ai.faceauth.sdk.presentation.navigation.FaceMatch.INSTANCE = new vn.ai.faceauth.sdk.presentation.navigation.FaceMatch();
        return;
    }

    private FaceMatch()
    {
        return;
    }

    public static final synthetic void access$detectFaceInImage(vn.ai.faceauth.sdk.presentation.navigation.FaceMatch p0, android.graphics.Bitmap p1)
    {
        p0.detectFaceInImage(p1);
        return;
    }

    public static final synthetic void access$processResult(vn.ai.faceauth.sdk.presentation.navigation.FaceMatch p0, android.graphics.Bitmap p1, android.graphics.Bitmap p2, android.graphics.Rect p3, java.util.ArrayList p4, boolean p5)
    {
        p0.processResult(p1, p2, p3, p4, p5);
        return;
    }

    public static final synthetic void access$runDetectionPass(vn.ai.faceauth.sdk.presentation.navigation.FaceMatch p0, int p1, android.graphics.Bitmap p2)
    {
        p0.runDetectionPass(p1, p2);
        return;
    }

    private final native void checkBlurryLasted(java.util.List p0);

    private final native void clearImageProcessor();

    private final native void completeSafely(int p0, String p1, String p2, String p3);

    public static synthetic void completeSafely$default(vn.ai.faceauth.sdk.presentation.navigation.FaceMatch p1, int p2, String p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 2) != 0) {
            p3 = "";
        }
        if ((p6 & 4) != 0) {
            p4 = "";
        }
        if ((p6 & 8) != 0) {
            p5 = "";
        }
        p1.completeSafely(p2, p3, p4, p5);
        return;
    }

    public static final native void configure(vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig p0, android.content.Context p1);

    private final native android.graphics.Bitmap createPaddedBitmap(android.graphics.Bitmap p0, int p1, int p2);

    private final native void detectFaceInImage(android.graphics.Bitmap p0);

    private final native java.util.ArrayList estimateLandmarksFromBbox(android.graphics.Rect p0);

    private final native android.graphics.Bitmap faceAlignment(android.graphics.Bitmap p0, java.util.ArrayList p1);

    private final native long getFileSizeInAssets(android.content.Context p0, String p1);

    public static final native String info(android.content.Context p0);

    private final native void initFaceDetector(android.content.Context p0);

    private final native boolean isDebuggable(android.content.Context p0);

    private final native boolean listFilesInAssets(android.content.Context p0, String p1);

    private final native void processResult(android.graphics.Bitmap p0, android.graphics.Bitmap p1, android.graphics.Rect p2, java.util.ArrayList p3, boolean p4);

    public static synthetic void processResult$default(vn.ai.faceauth.sdk.presentation.navigation.FaceMatch p6, android.graphics.Bitmap p7, android.graphics.Bitmap p8, android.graphics.Rect p9, java.util.ArrayList p10, boolean p11, int p12, Object p13)
    {
        if ((p12 & 16) != 0) {
            p11 = 0;
        }
        p6.processResult(p7, p8, p9, p10, p11);
        return;
    }

    private final native void runDetectionPass(int p0, android.graphics.Bitmap p1);

    private final native android.graphics.Bitmap scaleBitMapWithSize(android.graphics.Bitmap p0, int p1, int p2);

    private final native void showFailFace(int p0, String p1);

    private final native void showSuccess(java.util.List p0);

    public static final native void startAuthenWithImage(android.app.Activity p0, String p1, vn.ai.faceauth.sdk.presentation.navigation.SDKCallback p2);

    public static final native void startAuthenWithImage(android.app.Application p0, String p1, vn.ai.faceauth.sdk.presentation.navigation.SDKCallback p2);

    public final native String base64DataImage();

    public final native String bitmapToBase64(android.graphics.Bitmap p0);

    public final native int getRetrycount();

    public final native long getTimeStart();

    public final native String intToBase64(int p0);

    public final native boolean isBase64(String p0);

    public final native boolean isBase64EncodedString(String p0);

    public final native boolean isValidBase64Image(String p0);

    public final native String jsonObjectToBase64(android.graphics.Rect p0, android.graphics.Bitmap p1, java.util.ArrayList p2);

    public final native void onDestroy();

    public final native void setRetrycount(int p0);

    public final native void setTimeStart(long p0);

    public final native java.util.HashMap shieldData(java.util.List p0, String p1);

    public final native boolean startOpenCV(android.content.Context p0);

    public final native void stopOpenCV();
}

package vn.ai.faceauth.sdk.presentation.presentation.widgets;
public class BitmapUtils {
    private static String TAG;

    static BitmapUtils()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(72);
        jipm.njcx.vxud(vn.ai.faceauth.sdk.presentation.presentation.widgets.BitmapUtils, 173, 173);
        return;
    }

    public BitmapUtils()
    {
        return;
    }

    private static native boolean areUVPlanesNV21(android.media.Image$Plane[] p0, int p1, int p2);

    public static native android.graphics.Bitmap getBitmap(java.nio.ByteBuffer p0, vn.ai.faceauth.sdk.c p1);

    public static native android.graphics.Bitmap getBitmapFromContentUri(android.content.ContentResolver p0, android.net.Uri p1);

    private static native int getExifOrientationTag(android.content.ContentResolver p0, android.net.Uri p1);

    private static native android.graphics.Bitmap rotateBitmap(android.graphics.Bitmap p0, int p1, boolean p2, boolean p3);

    private static native void unpackPlane(android.media.Image$Plane p0, int p1, int p2, byte[] p3, int p4, int p5);

    private static native java.nio.ByteBuffer yuv420ThreePlanesToNV21(android.media.Image$Plane[] p0, int p1, int p2);
}

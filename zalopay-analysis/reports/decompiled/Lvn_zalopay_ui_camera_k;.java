package vn.zalopay.ui.camera;
public final class k implements vn.zalopay.ui.camera.l {
    public static final android.util.SparseIntArray n;
    public final kf.a a;
    public android.hardware.camera2.CameraDevice b;
    public android.hardware.camera2.CaptureRequest$Builder c;
    public android.hardware.camera2.CaptureRequest d;
    public int e;
    public android.hardware.camera2.CameraCaptureSession f;
    public final int g;
    public android.view.Surface h;
    public final java.util.concurrent.Semaphore i;
    public final com.google.android.play.core.splitinstall.internal.g j;
    public android.media.ImageReader k;
    public final vn.zalopay.ui.camera.h l;
    public final vn.zalopay.ui.camera.g m;

    static k()
    {
        android.util.SparseIntArray v0_1 = new android.util.SparseIntArray();
        vn.zalopay.ui.camera.k.n = v0_1;
        android.util.SparseIntArray v1_1 = new android.util.SparseIntArray();
        v0_1.append(0, 90);
        v0_1.append(1, 0);
        v0_1.append(2, 270);
        v0_1.append(3, 180);
        v1_1.append(0, 270);
        v1_1.append(1, 180);
        v1_1.append(2, 90);
        v1_1.append(3, 0);
        return;
    }

    public k()
    {
        this.a = new kf.a(0);
        this.e = 0;
        this.g = 1;
        this.h = 0;
        this.i = new java.util.concurrent.Semaphore(1);
        this.l = new vn.zalopay.ui.camera.h(this, 0);
        this.m = new vn.zalopay.ui.camera.g(this);
        new androidx.camera.camera2.internal.p0(2, this);
        this.j = new com.google.android.play.core.splitinstall.internal.g(this);
        return;
    }

    public static void a(vn.zalopay.ui.camera.k p1)
    {
        try {
            int v1_1 = p1.b;
        } catch (int v1_3) {
            v1_3.printStackTrace();
            return;
        }
        if (v1_1 != 0) {
            v1_1.createCaptureRequest(2);
            throw 0;
        } else {
            return;
        }
    }
}

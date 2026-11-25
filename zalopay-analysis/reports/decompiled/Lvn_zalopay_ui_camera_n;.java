package vn.zalopay.ui.camera;
public final class n implements android.view.SurfaceHolder$Callback {
    public final synthetic vn.zalopay.ui.camera.CameraSourcePreview a;

    public n(vn.zalopay.ui.camera.CameraSourcePreview p1)
    {
        this.a = p1;
        return;
    }

    public final void surfaceChanged(android.view.SurfaceHolder p1, int p2, int p3, int p4)
    {
        return;
    }

    public final void surfaceCreated(android.view.SurfaceHolder p2)
    {
        vn.zalopay.ui.camera.CameraSourcePreview v0 = this.a;
        v0.setMSurfaceAvailable$widget_release(1);
        vn.zalopay.ui.camera.GraphicOverlay v2_1 = v0.getOverlay();
        if (v2_1 != null) {
            v2_1.bringToFront();
        }
        return;
    }

    public final void surfaceDestroyed(android.view.SurfaceHolder p2)
    {
        this.a.setMSurfaceAvailable$widget_release(0);
        return;
    }
}

package vn.zalopay.ui.camera;
public final class m implements android.view.TextureView$SurfaceTextureListener {
    public final synthetic int a;
    public final synthetic android.view.ViewGroup b;

    public synthetic m(android.view.ViewGroup p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    private final void a(android.graphics.SurfaceTexture p1, int p2, int p3)
    {
        return;
    }

    private final void b(android.graphics.SurfaceTexture p1, int p2, int p3)
    {
        return;
    }

    private final void c(android.graphics.SurfaceTexture p1, int p2, int p3)
    {
        return;
    }

    private final void d(android.graphics.SurfaceTexture p1)
    {
        return;
    }

    private final void e(android.graphics.SurfaceTexture p1)
    {
        return;
    }

    private final void f(android.graphics.SurfaceTexture p1)
    {
        return;
    }

    public final void onSurfaceTextureAvailable(android.graphics.SurfaceTexture p1, int p2, int p3)
    {
        switch (this.a) {
            case 0:
                java.io.IOException v1_2 = ((vn.zalopay.ui.camera.CameraSourcePreview) this.b);
                v1_2.setMSurfaceAvailable$widget_release(1);
                java.io.IOException v1_3 = v1_2.getOverlay();
                if (v1_3 != null) {
                    v1_3.bringToFront();
                }
                return;
            case 1:
                java.io.IOException v1_7 = ((com.fpt.fci.ekycfull.ui.overlay.CameraSourcePreview) this.b);
                v1_7.c = 1;
                v1_7.g.bringToFront();
                try {
                    v1_7.a();
                } catch (java.io.IOException v1_9) {
                    android.util.Log.e("CameraSourcePreview", "Could not start camera source.", v1_9);
                }
                return;
            default:
                java.io.IOException v1_6 = ((com.fpt.fci.ekycfull.ui.overlay.CameraSourcePreviewV4) this.b);
                v1_6.c = 1;
                v1_6.g.bringToFront();
                try {
                    v1_6.a();
                } catch (java.io.IOException v1_8) {
                    android.util.Log.e("CameraSourcePreview", "Could not start camera source.", v1_8);
                }
                return;
        }
    }

    public final boolean onSurfaceTextureDestroyed(android.graphics.SurfaceTexture p2)
    {
        switch (this.a) {
            case 0:
                ((vn.zalopay.ui.camera.CameraSourcePreview) this.b).setMSurfaceAvailable$widget_release(0);
                return 1;
            case 1:
                ((com.fpt.fci.ekycfull.ui.overlay.CameraSourcePreview) this.b).c = 0;
                return 0;
            default:
                ((com.fpt.fci.ekycfull.ui.overlay.CameraSourcePreviewV4) this.b).c = 0;
                return 0;
        }
    }

    public final void onSurfaceTextureSizeChanged(android.graphics.SurfaceTexture p1, int p2, int p3)
    {
        return;
    }

    public final void onSurfaceTextureUpdated(android.graphics.SurfaceTexture p1)
    {
        return;
    }
}

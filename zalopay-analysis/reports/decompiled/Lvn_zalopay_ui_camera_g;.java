package vn.zalopay.ui.camera;
public final synthetic class g implements android.media.ImageReader$OnImageAvailableListener {
    public final synthetic vn.zalopay.ui.camera.k a;

    public synthetic g(vn.zalopay.ui.camera.k p1)
    {
        this.a = p1;
        return;
    }

    public final void onImageAvailable(android.media.ImageReader p7)
    {
        Object v0 = this.a;
        v0.getClass();
        Object v7_5 = p7.acquireNextImage();
        if (v7_5 != null) {
            byte[] v1_0 = v7_5.getPlanes()[0].getBuffer();
            Object v7_3 = v7_5.getPlanes()[2].getBuffer();
            int v3_1 = v1_0.remaining();
            int v4 = v7_3.remaining();
            byte[] v5_1 = new byte[(v3_1 + v4)];
            v1_0.get(v5_1, 0, v3_1);
            v7_3.get(v5_1, v3_1, v4);
            Object v7_4 = v0.j;
            if (((byte[]) v7_4.c) != null) {
                v7_4.c = 0;
            }
            v7_4.c = v5_1;
            v7_4.b.notifyAll();
            throw 0;
        } else {
            return;
        }
    }
}

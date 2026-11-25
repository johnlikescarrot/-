package vn.zalopay.ui.camera;
public final class CameraSourcePreview extends android.widget.FrameLayout {
    public final android.view.SurfaceView a;
    public final sZ.f b;
    public boolean c;
    public final vn.zalopay.ui.camera.l d;
    public vn.zalopay.ui.camera.GraphicOverlay e;

    public CameraSourcePreview(android.content.Context p11, android.util.AttributeSet p12)
    {
        super(p11, p12, 0);
        String v1_4 = new android.view.SurfaceView(p11);
        super.a = v1_4;
        v1_4.getHolder().addCallback(new vn.zalopay.ui.camera.n(super));
        vn.zalopay.ui.camera.k v2_0 = 0;
        String v1_1 = new sZ.f(p11, 0, 0);
        int v3_1 = new android.widget.FrameLayout$LayoutParams(-2, -2);
        v3_1.gravity = 1;
        v1_1.setLayoutParams(v3_1);
        v1_1.setSurfaceTextureListener(new vn.zalopay.ui.camera.m(super, 0));
        super.b = v1_1;
        String v11_9 = p11.getTheme().obtainStyledAttributes(p12, sZ.h.c, 0, 0).getInt(0, 0);
        try {
            int v3_6;
            int v3_5 = super.getContext().getSystemService("camera");
        } catch (Throwable) {
            int v5_10 = nY.o.values();
            Object v6_0 = new java.util.ArrayList();
            int v7_0 = v5_10.length;
            android.hardware.camera2.CameraCharacteristics$Key v8_0 = 0;
        }
        if (!(v3_5 instanceof android.hardware.camera2.CameraManager)) {
            v3_6 = 0;
        } else {
            v3_6 = ((android.hardware.camera2.CameraManager) v3_5);
        }
        Object v6_3;
        if (v3_6 == 0) {
            v6_3 = 0;
        } else {
            int v5_4 = v3_6.getCameraIdList();
            if (v5_4 == 0) {
            } else {
                v6_3 = new java.util.ArrayList(v5_4.length);
                int v7_5 = v5_4.length;
                android.hardware.camera2.CameraCharacteristics$Key v8_1 = 0;
                while (v8_1 < v7_5) {
                    v6_3.add(v3_6.getCameraCharacteristics(v5_4[v8_1]));
                    v8_1++;
                }
            }
        }
        int v5_7;
        if (v6_3 == null) {
            if (v6_3 == null) {
                v5_7 = 0;
            } else {
                v5_7 = ((android.hardware.camera2.CameraCharacteristics) v6_3.get(0));
            }
        } else {
            int v3_7 = v6_3.iterator();
            while (v3_7.hasNext()) {
                int v5_6 = v3_7.next();
                int v7_9 = ((Integer) ((android.hardware.camera2.CameraCharacteristics) v5_6).get(android.hardware.camera2.CameraCharacteristics.LENS_FACING));
                if (v7_9 != 0) {
                    if (v7_9.intValue() == 1) {
                    }
                } else {
                }
                v5_7 = ((android.hardware.camera2.CameraCharacteristics) v5_6);
                if (v5_7 == 0) {
                }
            }
            v5_6 = 0;
        }
        int v3_9;
        if (v5_7 == 0) {
            v3_9 = 0;
        } else {
            v3_9 = ((Integer) v5_7.get(android.hardware.camera2.CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL));
        }
        if (v3_9 != 0) {
            if (v3_9.intValue() != 2) {
            } else {
                IllegalArgumentException v0_4 = super.getContext();
                vn.zalopay.ui.camera.k v2_2 = new vn.zalopay.ui.camera.f();
                if (v0_4 == null) {
                    throw new IllegalArgumentException("No context supplied.");
                } else {
                    if ((v11_9 != null) && (v11_9 != 1)) {
                        throw new IllegalArgumentException(androidx.activity.result.e.g(v11_9, "Invalid camera: "));
                    } else {
                        super.d = v2_2;
                        return;
                    }
                }
            }
        } else {
        }
    }

    public final boolean getMSurfaceAvailable$widget_release()
    {
        return this.c;
    }

    public final vn.zalopay.ui.camera.GraphicOverlay getOverlay()
    {
        return this.e;
    }

    public final vn.zalopay.ui.camera.l getSource()
    {
        return this.d;
    }

    public final String getSourceString()
    {
        if (!(this.d instanceof vn.zalopay.ui.camera.k)) {
            return "1";
        } else {
            return "2";
        }
    }

    public final int getTextureViewHeight()
    {
        int v0_2;
        if (!(this.d instanceof vn.zalopay.ui.camera.f)) {
            v0_2 = this.b;
        } else {
            v0_2 = this.a;
        }
        return v0_2.getHeight();
    }

    public final int getTextureViewWidth()
    {
        int v0_2;
        if (!(this.d instanceof vn.zalopay.ui.camera.f)) {
            v0_2 = this.b;
        } else {
            v0_2 = this.a;
        }
        return v0_2.getWidth();
    }

    public final void onMeasure(int p1, int p2)
    {
        super.onMeasure(p1, p2);
        this.setMeasuredDimension(android.view.View$MeasureSpec.getSize(p1), android.view.View$MeasureSpec.getSize(p2));
        return;
    }

    public final void setMSurfaceAvailable$widget_release(boolean p1)
    {
        this.c = p1;
        return;
    }

    public final void setOverlay(vn.zalopay.ui.camera.GraphicOverlay p1)
    {
        this.e = p1;
        return;
    }
}

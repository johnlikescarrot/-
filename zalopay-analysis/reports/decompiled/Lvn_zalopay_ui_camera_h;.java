package vn.zalopay.ui.camera;
public final class h extends android.hardware.camera2.CameraCaptureSession$CaptureCallback {
    public final synthetic int a;
    public final synthetic vn.zalopay.ui.camera.k b;

    public synthetic h(vn.zalopay.ui.camera.k p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public void a(android.hardware.camera2.CaptureResult p8)
    {
        vn.zalopay.ui.camera.h v0_0 = this.b;
        int v1_0 = v0_0.e;
        if (v1_0 == 1) {
            int v1_1 = ((Integer) p8.get(android.hardware.camera2.CaptureResult.CONTROL_AF_STATE));
            if (v1_1 != 0) {
                if ((4 == v1_1.intValue()) || ((5 == v1_1.intValue()) || ((6 == v1_1.intValue()) || ((2 == v1_1.intValue()) || (v1_1.intValue() == 0))))) {
                    android.hardware.camera2.CameraAccessException v8_2 = ((Integer) p8.get(android.hardware.camera2.CaptureResult.CONTROL_AE_STATE));
                    if ((v8_2 != null) && (v8_2.intValue() != 2)) {
                        try {
                            v0_0.c.set(android.hardware.camera2.CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, Integer.valueOf(1));
                            v0_0.e = 2;
                            v0_0.f.capture(v0_0.c.build(), v0_0.l, 0);
                        } catch (android.hardware.camera2.CameraAccessException v8_6) {
                            v8_6.printStackTrace();
                        }
                    } else {
                        v0_0.e = 4;
                        vn.zalopay.ui.camera.k.a(v0_0);
                        return;
                    }
                }
            } else {
                vn.zalopay.ui.camera.k.a(v0_0);
                return;
            }
        } else {
            if (v1_0 == 2) {
                android.hardware.camera2.CameraAccessException v8_8 = ((Integer) p8.get(android.hardware.camera2.CaptureResult.CONTROL_AE_STATE));
                if ((v8_8 == null) || ((v8_8.intValue() == 5) || (v8_8.intValue() == 4))) {
                    v0_0.e = 3;
                    return;
                }
            } else {
                if (v1_0 == 3) {
                    android.hardware.camera2.CameraAccessException v8_11 = ((Integer) p8.get(android.hardware.camera2.CaptureResult.CONTROL_AE_STATE));
                    if ((v8_11 == null) || (v8_11.intValue() != 5)) {
                        v0_0.e = 4;
                        vn.zalopay.ui.camera.k.a(v0_0);
                        return;
                    }
                }
            }
        }
        return;
    }

    public final void onCaptureCompleted(android.hardware.camera2.CameraCaptureSession p4, android.hardware.camera2.CaptureRequest p5, android.hardware.camera2.TotalCaptureResult p6)
    {
        switch (this.a) {
            case 0:
                if (p5.getTag() == wmoi.igob.ggcn(11358)) {
                    this.b.getClass();
                    throw 0;
                } else {
                    this.a(p6);
                    return;
                }
            default:
                android.hardware.camera2.CaptureRequest v5_1 = this.b;
                vn.zalopay.ui.camera.h v6_1 = v5_1.l;
                try {
                    v5_1.c.set(android.hardware.camera2.CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
                    v5_1.f.capture(v5_1.c.build(), v6_1, 0);
                    v5_1.e = 0;
                    v5_1.f.setRepeatingRequest(v5_1.d, v6_1, 0);
                } catch (android.hardware.camera2.CameraAccessException v4_8) {
                    v4_8.printStackTrace();
                }
                return;
        }
    }

    public void onCaptureFailed(android.hardware.camera2.CameraCaptureSession p2, android.hardware.camera2.CaptureRequest p3, android.hardware.camera2.CaptureFailure p4)
    {
        switch (this.a) {
            case 0:
                if (p3.getTag() == wmoi.igob.ggcn(11359)) {
                    java.util.Objects.toString(p4);
                    this.b.getClass();
                    throw 0;
                } else {
                    return;
                }
            default:
                super.onCaptureFailed(p2, p3, p4);
                return;
        }
    }

    public void onCaptureProgressed(android.hardware.camera2.CameraCaptureSession p2, android.hardware.camera2.CaptureRequest p3, android.hardware.camera2.CaptureResult p4)
    {
        switch (this.a) {
            case 0:
                this.a(p4);
                return;
            default:
                super.onCaptureProgressed(p2, p3, p4);
                return;
        }
    }
}

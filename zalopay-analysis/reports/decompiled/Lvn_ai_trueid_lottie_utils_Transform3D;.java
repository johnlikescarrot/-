package vn.ai.trueid.lottie.utils;
public class Transform3D {

    public Transform3D()
    {
        return;
    }

    public static void apply3DRotations(android.graphics.Matrix p2, float p3, float p4, float p5, float p6, float p7)
    {
        if (p5 != 0) {
            p2.preRotate(p5);
        }
        if (p4 != 0) {
            vn.ai.trueid.lottie.utils.Transform3D.applyYRotation(p2, p7);
        }
        if (p3 != 0) {
            vn.ai.trueid.lottie.utils.Transform3D.applyXRotation(p2, p6);
        }
        return;
    }

    public static void applyTransform(android.graphics.Matrix p3, android.graphics.PointF p4, android.graphics.PointF p5, float p6, float p7, float p8, float p9, float p10, float p11, float p12)
    {
        p3.reset();
        if (p5 != 0) {
            float v1 = p5.x;
            if ((v1 != 0) || (p5.y != 0)) {
                p3.preTranslate(v1, p5.y);
            }
        }
        if (p10 != 0) {
            p3.preRotate(p10);
        }
        if (p9 != 0) {
            vn.ai.trueid.lottie.utils.Transform3D.applyYRotation(p3, p12);
        }
        if (p8 != 0) {
            vn.ai.trueid.lottie.utils.Transform3D.applyXRotation(p3, p11);
        }
        if ((p6 != 1065353216) || (p7 != 1065353216)) {
            p3.preScale(p6, p7);
        }
        if (p4 != 0) {
            float v5_6 = p4.x;
            if ((v5_6 != 0) || (p4.y != 0)) {
                p3.preTranslate((- v5_6), (- p4.y));
            }
        }
        return;
    }

    private static void applyXRotation(android.graphics.Matrix p1, float p2)
    {
        p1.preScale(1065353216, p2);
        return;
    }

    private static void applyYRotation(android.graphics.Matrix p1, float p2)
    {
        p1.preScale(p2, 1065353216);
        return;
    }

    public static boolean has3DRotation(Float p1, Float p2, Float p3)
    {
        if (((p1 == 0) || (p1.floatValue() == 0)) && (((p2 == null) || (p2.floatValue() == 0)) && ((p3 == null) || (p3.floatValue() == 0)))) {
            return 0;
        } else {
            return 1;
        }
    }
}

package vn.ai.trueid.lottie.utils;
public class MiscUtils {
    private static final android.graphics.PointF pathFromDataCurrentPoint;

    static MiscUtils()
    {
        vn.ai.trueid.lottie.utils.MiscUtils.pathFromDataCurrentPoint = new android.graphics.PointF();
        return;
    }

    public MiscUtils()
    {
        return;
    }

    public static android.graphics.PointF addPoints(android.graphics.PointF p3, android.graphics.PointF p4)
    {
        return new android.graphics.PointF((p3.x + p4.x), (p3.y + p4.y));
    }

    public static double clamp(double p0, double p2, double p4)
    {
        return Math.max(p2, Math.min(p4, p0));
    }

    public static float clamp(float p0, float p1, float p2)
    {
        return Math.max(p1, Math.min(p2, p0));
    }

    public static int clamp(int p0, int p1, int p2)
    {
        return Math.max(p1, Math.min(p2, p0));
    }

    public static boolean contains(float p0, float p1, float p2)
    {
        if ((p0 < p1) || (p0 > p2)) {
            return 0;
        } else {
            return 1;
        }
    }

    private static int floorDiv(int p2, int p3)
    {
        int v1_1;
        int v0 = (p2 / p3);
        if ((p2 ^ p3) < 0) {
            v1_1 = 0;
        } else {
            v1_1 = 1;
        }
        if ((v1_1 == 0) && ((p2 % p3) != 0)) {
            v0--;
        }
        return v0;
    }

    public static int floorMod(float p0, float p1)
    {
        return vn.ai.trueid.lottie.utils.MiscUtils.floorMod(((int) p0), ((int) p1));
    }

    private static int floorMod(int p1, int p2)
    {
        return (p1 - (p2 * vn.ai.trueid.lottie.utils.MiscUtils.floorDiv(p1, p2)));
    }

    public static void getPathFromData(vn.ai.trueid.lottie.model.content.ShapeData p12, android.graphics.Path p13)
    {
        p13.reset();
        int v0_1 = p12.getInitialPoint();
        p13.moveTo(v0_1.x, v0_1.y);
        vn.ai.trueid.lottie.utils.MiscUtils.pathFromDataCurrentPoint.set(v0_1.x, v0_1.y);
        int v0_0 = 0;
        while (v0_0 < p12.getCurves().size()) {
            android.graphics.Path v5_3;
            float v1_4 = ((vn.ai.trueid.lottie.model.CubicCurveData) p12.getCurves().get(v0_0));
            float v2_0 = v1_4.getControlPoint1();
            float v3_0 = v1_4.getControlPoint2();
            float v1_6 = v1_4.getVertex();
            android.graphics.PointF v4 = vn.ai.trueid.lottie.utils.MiscUtils.pathFromDataCurrentPoint;
            if ((!v2_0.equals(v4)) || (!v3_0.equals(v1_6))) {
                v5_3 = p13;
                p13.cubicTo(v2_0.x, v2_0.y, v3_0.x, v3_0.y, v1_6.x, v1_6.y);
            } else {
                p13.lineTo(v1_6.x, v1_6.y);
                v5_3 = p13;
            }
            v4.set(v1_6.x, v1_6.y);
            v0_0++;
            p13 = v5_3;
        }
        if (p12.isClosed()) {
            p13.close();
        }
        return;
    }

    public static double lerp(double p0, double p2, double p4)
    {
        return (((p2 - p0) * p4) + p0);
    }

    public static float lerp(float p0, float p1, float p2)
    {
        return androidx.activity.result.e.a(p1, p0, p2, p0);
    }

    public static int lerp(int p1, int p2, float p3)
    {
        return ((int) ((p3 * ((float) (p2 - p1))) + ((float) p1)));
    }

    public static void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p1, int p2, java.util.List p3, vn.ai.trueid.lottie.model.KeyPath p4, vn.ai.trueid.lottie.animation.content.KeyPathElementContent p5)
    {
        if (p1.fullyResolvesTo(p5.getName(), p2)) {
            p3.add(p4.addKey(p5.getName()).resolve(p5));
        }
        return;
    }
}

package vn.ai.trueid.lottie.model.content;
public class ShapeData {
    private boolean closed;
    private final java.util.List curves;
    private android.graphics.PointF initialPoint;

    public ShapeData()
    {
        this.curves = new java.util.ArrayList();
        return;
    }

    public ShapeData(android.graphics.PointF p1, boolean p2, java.util.List p3)
    {
        this.initialPoint = p1;
        this.closed = p2;
        this.curves = new java.util.ArrayList(p3);
        return;
    }

    public java.util.List getCurves()
    {
        return this.curves;
    }

    public android.graphics.PointF getInitialPoint()
    {
        return this.initialPoint;
    }

    public void interpolateBetween(vn.ai.trueid.lottie.model.content.ShapeData p11, vn.ai.trueid.lottie.model.content.ShapeData p12, float p13)
    {
        if (this.initialPoint == null) {
            this.initialPoint = new android.graphics.PointF();
        }
        if ((!p11.isClosed()) && (!p12.isClosed())) {
            int v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        this.closed = v0_2;
        if (p11.getCurves().size() != p12.getCurves().size()) {
            int v0_8 = new StringBuilder("Curves must have the same number of control points. Shape 1: ");
            v0_8.append(p11.getCurves().size());
            v0_8.append("\tShape 2: ");
            v0_8.append(p12.getCurves().size());
            vn.ai.trueid.lottie.utils.Logger.warning(v0_8.toString());
        }
        int v0_13 = Math.min(p11.getCurves().size(), p12.getCurves().size());
        if (this.curves.size() >= v0_13) {
            if (this.curves.size() > v0_13) {
                float v2_22 = (this.curves.size() - 1);
                while (v2_22 >= v0_13) {
                    vn.ai.trueid.lottie.model.CubicCurveData v3_10 = this.curves;
                    v3_10.remove((v3_10.size() - 1));
                    v2_22--;
                }
            }
        } else {
            float v2_5 = this.curves.size();
            while (v2_5 < v0_13) {
                this.curves.add(new vn.ai.trueid.lottie.model.CubicCurveData());
                v2_5++;
            }
        }
        int v0_15 = p11.getInitialPoint();
        float v2_24 = p12.getInitialPoint();
        this.setInitialPoint(vn.ai.trueid.lottie.utils.MiscUtils.lerp(v0_15.x, v2_24.x, p13), vn.ai.trueid.lottie.utils.MiscUtils.lerp(v0_15.y, v2_24.y, p13));
        int v0_3 = (this.curves.size() - 1);
        while (v0_3 >= 0) {
            float v1_6 = ((vn.ai.trueid.lottie.model.CubicCurveData) p11.getCurves().get(v0_3));
            float v2_2 = ((vn.ai.trueid.lottie.model.CubicCurveData) p12.getCurves().get(v0_3));
            vn.ai.trueid.lottie.model.CubicCurveData v3_0 = v1_6.getControlPoint1();
            float v4_0 = v1_6.getControlPoint2();
            float v1_0 = v1_6.getVertex();
            float v5_0 = v2_2.getControlPoint1();
            float v6_0 = v2_2.getControlPoint2();
            float v2_3 = v2_2.getVertex();
            ((vn.ai.trueid.lottie.model.CubicCurveData) this.curves.get(v0_3)).setControlPoint1(vn.ai.trueid.lottie.utils.MiscUtils.lerp(v3_0.x, v5_0.x, p13), vn.ai.trueid.lottie.utils.MiscUtils.lerp(v3_0.y, v5_0.y, p13));
            ((vn.ai.trueid.lottie.model.CubicCurveData) this.curves.get(v0_3)).setControlPoint2(vn.ai.trueid.lottie.utils.MiscUtils.lerp(v4_0.x, v6_0.x, p13), vn.ai.trueid.lottie.utils.MiscUtils.lerp(v4_0.y, v6_0.y, p13));
            ((vn.ai.trueid.lottie.model.CubicCurveData) this.curves.get(v0_3)).setVertex(vn.ai.trueid.lottie.utils.MiscUtils.lerp(v1_0.x, v2_3.x, p13), vn.ai.trueid.lottie.utils.MiscUtils.lerp(v1_0.y, v2_3.y, p13));
            v0_3--;
        }
        return;
    }

    public boolean isClosed()
    {
        return this.closed;
    }

    public void setClosed(boolean p1)
    {
        this.closed = p1;
        return;
    }

    public void setInitialPoint(float p2, float p3)
    {
        if (this.initialPoint == null) {
            this.initialPoint = new android.graphics.PointF();
        }
        this.initialPoint.set(p2, p3);
        return;
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("ShapeData{numCurves=");
        v0_2.append(this.curves.size());
        v0_2.append("closed=");
        return androidx.activity.result.e.r(v0_2, this.closed, 125);
    }
}

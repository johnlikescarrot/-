package vn.ai.trueid.lottie.model;
public class CubicCurveData {
    private final android.graphics.PointF controlPoint1;
    private final android.graphics.PointF controlPoint2;
    private final android.graphics.PointF vertex;

    public CubicCurveData()
    {
        this.controlPoint1 = new android.graphics.PointF();
        this.controlPoint2 = new android.graphics.PointF();
        this.vertex = new android.graphics.PointF();
        return;
    }

    public CubicCurveData(android.graphics.PointF p1, android.graphics.PointF p2, android.graphics.PointF p3)
    {
        this.controlPoint1 = p1;
        this.controlPoint2 = p2;
        this.vertex = p3;
        return;
    }

    public android.graphics.PointF getControlPoint1()
    {
        return this.controlPoint1;
    }

    public android.graphics.PointF getControlPoint2()
    {
        return this.controlPoint2;
    }

    public android.graphics.PointF getVertex()
    {
        return this.vertex;
    }

    public void setControlPoint1(float p2, float p3)
    {
        this.controlPoint1.set(p2, p3);
        return;
    }

    public void setControlPoint2(float p2, float p3)
    {
        this.controlPoint2.set(p2, p3);
        return;
    }

    public void setFrom(vn.ai.trueid.lottie.model.CubicCurveData p3)
    {
        float v0_0 = p3.vertex;
        this.setVertex(v0_0.x, v0_0.y);
        float v0_2 = p3.controlPoint1;
        this.setControlPoint1(v0_2.x, v0_2.y);
        float v3_2 = p3.controlPoint2;
        this.setControlPoint2(v3_2.x, v3_2.y);
        return;
    }

    public void setVertex(float p2, float p3)
    {
        this.vertex.set(p2, p3);
        return;
    }

    public String toString()
    {
        String v1_1 = Float.valueOf(this.vertex.x);
        Float.valueOf(this.vertex.y);
        Float.valueOf(this.controlPoint1.x);
        Float.valueOf(this.controlPoint1.y);
        Float.valueOf(this.controlPoint2.x);
        return String.format("v=%.2f,%.2f cp1=%.2f,%.2f cp2=%.2f,%.2f", new Object[] {v1_1, Float.valueOf(this.controlPoint2.y)}));
    }
}

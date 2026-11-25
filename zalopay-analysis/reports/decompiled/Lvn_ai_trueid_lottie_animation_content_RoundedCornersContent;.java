package vn.ai.trueid.lottie.animation.content;
public class RoundedCornersContent implements vn.ai.trueid.lottie.animation.content.ShapeModifierContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener {
    private static final float ROUNDED_CORNER_MAGIC_NUMBER = 1057835346;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final String name;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation roundedCorners;
    private vn.ai.trueid.lottie.model.content.ShapeData shapeData;

    public RoundedCornersContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.model.layer.BaseLayer p2, vn.ai.trueid.lottie.model.content.RoundedCorners p3)
    {
        this.lottieDrawable = p1;
        this.name = p3.getName();
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v1_3 = p3.getCornerRadius().createAnimation();
        this.roundedCorners = v1_3;
        p2.addAnimation(v1_3);
        v1_3.addUpdateListener(this);
        return;
    }

    private static int floorDiv(int p2, int p3)
    {
        int v0 = (p2 / p3);
        if (((p2 ^ p3) < 0) && ((p3 * v0) != p2)) {
            v0--;
        }
        return v0;
    }

    private static int floorMod(int p1, int p2)
    {
        return (p1 - (vn.ai.trueid.lottie.animation.content.RoundedCornersContent.floorDiv(p1, p2) * p2));
    }

    private vn.ai.trueid.lottie.model.content.ShapeData getShapeData(vn.ai.trueid.lottie.model.content.ShapeData p11)
    {
        vn.ai.trueid.lottie.model.content.ShapeData v0_0 = p11.getCurves();
        boolean v1 = p11.isClosed();
        int v2_5 = (v0_0.size() - 1);
        int v5 = 0;
        while (v2_5 >= 0) {
            android.graphics.PointF v8_1;
            boolean v6_1 = ((vn.ai.trueid.lottie.model.CubicCurveData) v0_0.get(v2_5));
            boolean v7_3 = ((vn.ai.trueid.lottie.model.CubicCurveData) v0_0.get(vn.ai.trueid.lottie.animation.content.RoundedCornersContent.floorMod((v2_5 - 1), v0_0.size())));
            if ((v2_5 != 0) || (v1)) {
                v8_1 = v7_3.getVertex();
            } else {
                v8_1 = p11.getInitialPoint();
            }
            if ((v2_5 != 0) || (v1)) {
                boolean v7_4 = v7_3.getControlPoint2();
            } else {
                v7_4 = v8_1;
            }
            int v9_3;
            boolean v6_2 = v6_1.getControlPoint1();
            if ((p11.isClosed()) || ((v2_5 != 0) && (v2_5 != (v0_0.size() - 1)))) {
                v9_3 = 0;
            } else {
                v9_3 = 1;
            }
            if ((!v7_4.equals(v8_1)) || ((!v6_2.equals(v8_1)) || (v9_3 != 0))) {
                v5++;
            } else {
                v5 += 2;
            }
            v2_5--;
        }
        vn.ai.trueid.lottie.model.content.ShapeData v11_1 = this.shapeData;
        if ((v11_1 == null) || (v11_1.getCurves().size() != v5)) {
            vn.ai.trueid.lottie.model.content.ShapeData v11_5 = new java.util.ArrayList(v5);
            vn.ai.trueid.lottie.model.content.ShapeData v0_1 = 0;
            while (v0_1 < v5) {
                v11_5.add(new vn.ai.trueid.lottie.model.CubicCurveData());
                v0_1++;
            }
            this.shapeData = new vn.ai.trueid.lottie.model.content.ShapeData(new android.graphics.PointF(0, 0), 0, v11_5);
        }
        this.shapeData.setClosed(v1);
        return this.shapeData;
    }

    public void addUpdateListener(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener p2)
    {
        this.roundedCorners.addUpdateListener(p2);
        return;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation getRoundedCorners()
    {
        return this.roundedCorners;
    }

    public vn.ai.trueid.lottie.model.content.ShapeData modifyShape(vn.ai.trueid.lottie.model.content.ShapeData p19)
    {
        vn.ai.trueid.lottie.model.CubicCurveData v0_0 = p19.getCurves();
        if (v0_0.size() > 2) {
            float v2_1 = ((Float) this.roundedCorners.getValue()).floatValue();
            if (v2_1 != 0) {
                vn.ai.trueid.lottie.model.content.ShapeData v3_2 = this.getShapeData(p19);
                v3_2.setInitialPoint(p19.getInitialPoint().x, p19.getInitialPoint().y);
                java.util.List v4_2 = v3_2.getCurves();
                boolean v5_2 = p19.isClosed();
                int v7 = 0;
                int v8 = 0;
                while (v7 < v0_0.size()) {
                    float v12_5;
                    float v9_9 = ((vn.ai.trueid.lottie.model.CubicCurveData) v0_0.get(v7));
                    float v10_10 = ((vn.ai.trueid.lottie.model.CubicCurveData) v0_0.get(vn.ai.trueid.lottie.animation.content.RoundedCornersContent.floorMod((v7 - 1), v0_0.size())));
                    float v11_9 = ((vn.ai.trueid.lottie.model.CubicCurveData) v0_0.get(vn.ai.trueid.lottie.animation.content.RoundedCornersContent.floorMod((v7 - 2), v0_0.size())));
                    if ((v7 != 0) || (v5_2)) {
                        v12_5 = v10_10.getVertex();
                    } else {
                        v12_5 = p19.getInitialPoint();
                    }
                    if ((v7 != 0) || (v5_2)) {
                        vn.ai.trueid.lottie.model.CubicCurveData v13_8 = v10_10.getControlPoint2();
                    } else {
                        v13_8 = v12_5;
                    }
                    java.util.List v16_2;
                    float v14_5 = v9_9.getControlPoint1();
                    float v11_10 = v11_9.getVertex();
                    vn.ai.trueid.lottie.model.CubicCurveData v15_3 = v9_9.getVertex();
                    if (p19.isClosed()) {
                        v16_2 = 0;
                    } else {
                        v16_2 = 1;
                        if ((v7 != 0) && (v7 != (v0_0.size() - 1))) {
                        }
                    }
                    if ((!v13_8.equals(v12_5)) || ((!v14_5.equals(v12_5)) || (v16_2 != null))) {
                        java.util.List v16_0 = v0_0;
                        vn.ai.trueid.lottie.model.CubicCurveData v0_15 = ((vn.ai.trueid.lottie.model.CubicCurveData) v4_2.get(vn.ai.trueid.lottie.animation.content.RoundedCornersContent.floorMod((v8 - 1), v4_2.size())));
                        float v1_8 = ((vn.ai.trueid.lottie.model.CubicCurveData) v4_2.get(v8));
                        v0_15.setControlPoint2(v10_10.getControlPoint2().x, v10_10.getControlPoint2().y);
                        v0_15.setVertex(v10_10.getVertex().x, v10_10.getVertex().y);
                        v1_8.setControlPoint1(v9_9.getControlPoint1().x, v9_9.getControlPoint1().y);
                        v8++;
                    } else {
                        float v6_6 = v12_5.x;
                        float v10_2 = v12_5.y;
                        v16_0 = v0_0;
                        float v1_0 = ((float) Math.hypot(((double) (v15_3.x - v6_6)), ((double) (v15_3.y - v10_2))));
                        vn.ai.trueid.lottie.model.CubicCurveData v0_7 = Math.min((v2_1 / ((float) Math.hypot(((double) (v6_6 - v11_10.x)), ((double) (v10_2 - v11_10.y))))), 1056964608);
                        float v1_2 = Math.min((v2_1 / v1_0), 1056964608);
                        float v6_10 = v12_5.x;
                        float v9_6 = androidx.activity.result.e.a(v11_10.x, v6_10, v0_7, v6_10);
                        float v10_3 = v12_5.y;
                        vn.ai.trueid.lottie.model.CubicCurveData v0_8 = androidx.activity.result.e.a(v11_10.y, v10_3, v0_7, v10_3);
                        float v11_4 = androidx.activity.result.e.a(v15_3.x, v6_10, v1_2, v6_10);
                        float v1_3 = androidx.activity.result.e.a(v15_3.y, v10_3, v1_2, v10_3);
                        float v12_3 = (v9_6 - ((v9_6 - v6_10) * 1057835346));
                        float v14_4 = (v0_8 - ((v0_8 - v10_3) * 1057835346));
                        float v6_13 = (v11_4 - ((v11_4 - v6_10) * 1057835346));
                        float v10_6 = (v1_3 - ((v1_3 - v10_3) * 1057835346));
                        vn.ai.trueid.lottie.model.CubicCurveData v13_7 = ((vn.ai.trueid.lottie.model.CubicCurveData) v4_2.get(vn.ai.trueid.lottie.animation.content.RoundedCornersContent.floorMod((v8 - 1), v4_2.size())));
                        vn.ai.trueid.lottie.model.CubicCurveData v15_2 = ((vn.ai.trueid.lottie.model.CubicCurveData) v4_2.get(v8));
                        v13_7.setControlPoint2(v9_6, v0_8);
                        v13_7.setVertex(v9_6, v0_8);
                        if (v7 == 0) {
                            v3_2.setInitialPoint(v9_6, v0_8);
                        }
                        v15_2.setControlPoint1(v12_3, v14_4);
                        vn.ai.trueid.lottie.model.CubicCurveData v0_11 = ((vn.ai.trueid.lottie.model.CubicCurveData) v4_2.get((v8 + 1)));
                        v15_2.setControlPoint2(v6_13, v10_6);
                        v15_2.setVertex(v11_4, v1_3);
                        v0_11.setControlPoint1(v11_4, v1_3);
                        v8 += 2;
                    }
                    v7++;
                    v0_0 = v16_0;
                }
                return v3_2;
            }
        }
        return p19;
    }

    public void onValueChanged()
    {
        this.lottieDrawable.invalidateSelf();
        return;
    }

    public void setContents(java.util.List p1, java.util.List p2)
    {
        return;
    }
}

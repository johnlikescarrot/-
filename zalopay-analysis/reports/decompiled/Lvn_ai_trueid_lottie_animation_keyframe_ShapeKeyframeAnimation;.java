package vn.ai.trueid.lottie.animation.keyframe;
public class ShapeKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation {
    private java.util.List shapeModifiers;
    private final android.graphics.Path tempPath;
    private final vn.ai.trueid.lottie.model.content.ShapeData tempShapeData;
    private android.graphics.Path valueCallbackEndPath;
    private android.graphics.Path valueCallbackStartPath;

    public ShapeKeyframeAnimation(java.util.List p1)
    {
        super(p1);
        super.tempShapeData = new vn.ai.trueid.lottie.model.content.ShapeData();
        super.tempPath = new android.graphics.Path();
        return;
    }

    public android.graphics.Path getValue(vn.ai.trueid.lottie.value.Keyframe p11, float p12)
    {
        float v3_5;
        android.graphics.Path v0_1 = ((vn.ai.trueid.lottie.model.content.ShapeData) p11.startValue);
        vn.ai.trueid.lottie.model.content.ShapeData v1_1 = ((vn.ai.trueid.lottie.model.content.ShapeData) p11.endValue);
        if (v1_1 != null) {
            v3_5 = v1_1;
        } else {
            v3_5 = v0_1;
        }
        this.tempShapeData.interpolateBetween(v0_1, v3_5, p12);
        vn.ai.trueid.lottie.value.LottieValueCallback v2_9 = this.tempShapeData;
        float v3_0 = this.shapeModifiers;
        if (v3_0 != 0) {
            float v3_2 = (v3_0.size() - 1);
            while (v3_2 >= 0) {
                v2_9 = ((vn.ai.trueid.lottie.animation.content.ShapeModifierContent) this.shapeModifiers.get(v3_2)).modifyShape(v2_9);
                v3_2--;
            }
        }
        vn.ai.trueid.lottie.utils.MiscUtils.getPathFromData(v2_9, this.tempPath);
        if (this.valueCallback == null) {
            return this.tempPath;
        } else {
            if (this.valueCallbackStartPath == null) {
                this.valueCallbackStartPath = new android.graphics.Path();
                this.valueCallbackEndPath = new android.graphics.Path();
            }
            vn.ai.trueid.lottie.utils.MiscUtils.getPathFromData(v0_1, this.valueCallbackStartPath);
            if (v1_1 != null) {
                vn.ai.trueid.lottie.utils.MiscUtils.getPathFromData(v1_1, this.valueCallbackEndPath);
            }
            android.graphics.Path v6;
            vn.ai.trueid.lottie.value.LottieValueCallback v2_7 = this.valueCallback;
            float v3_4 = p11.startFrame;
            float v4_0 = p11.endFrame.floatValue();
            android.graphics.Path v5 = this.valueCallbackStartPath;
            if (v1_1 != null) {
                v6 = this.valueCallbackEndPath;
            } else {
                v6 = v5;
            }
            return ((android.graphics.Path) v2_7.getValueInternal(v3_4, v4_0, v5, v6, p12, this.getLinearCurrentKeyframeProgress(), this.getProgress()));
        }
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }

    public void setShapeModifiers(java.util.List p1)
    {
        this.shapeModifiers = p1;
        return;
    }

    public boolean skipCache()
    {
        int v0_0 = this.shapeModifiers;
        if ((v0_0 == 0) || (v0_0.isEmpty())) {
            return 0;
        } else {
            return 1;
        }
    }
}

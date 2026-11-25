package vn.ai.trueid.lottie.animation.keyframe;
public class TextKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.KeyframeAnimation {

    public TextKeyframeAnimation(java.util.List p1)
    {
        super(p1);
        return;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue(p1, p2);
    }

    public vn.ai.trueid.lottie.model.DocumentData getValue(vn.ai.trueid.lottie.value.Keyframe p9, float p10)
    {
        vn.ai.trueid.lottie.value.LottieValueCallback v0 = this.valueCallback;
        if (v0 == null) {
            if (p10 == 1065353216) {
                vn.ai.trueid.lottie.model.DocumentData v10_3 = p9.endValue;
                if (v10_3 != null) {
                    return ((vn.ai.trueid.lottie.model.DocumentData) v10_3);
                }
            }
            return ((vn.ai.trueid.lottie.model.DocumentData) p9.startValue);
        } else {
            float v2_1;
            float v1 = p9.startFrame;
            float v2_0 = p9.endFrame;
            if (v2_0 != 0) {
                v2_1 = v2_0.floatValue();
            } else {
                v2_1 = 2139095039;
            }
            vn.ai.trueid.lottie.model.DocumentData v9_4;
            vn.ai.trueid.lottie.model.DocumentData v4_0 = p9.startValue;
            vn.ai.trueid.lottie.model.DocumentData v9_3 = p9.endValue;
            if (v9_3 != null) {
                v9_4 = ((vn.ai.trueid.lottie.model.DocumentData) v9_3);
            } else {
                v9_4 = ((vn.ai.trueid.lottie.model.DocumentData) v4_0);
            }
            return ((vn.ai.trueid.lottie.model.DocumentData) v0.getValueInternal(v1, v2_1, ((vn.ai.trueid.lottie.model.DocumentData) v4_0), v9_4, p10, this.getInterpolatedCurrentKeyframeProgress(), this.getProgress()));
        }
    }

    public void setStringValueCallback(vn.ai.trueid.lottie.value.LottieValueCallback p4)
    {
        super.setValueCallback(new vn.ai.trueid.lottie.animation.keyframe.TextKeyframeAnimation$1(this, new vn.ai.trueid.lottie.value.LottieFrameInfo(), p4, new vn.ai.trueid.lottie.model.DocumentData()));
        return;
    }
}

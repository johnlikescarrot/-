package vn.ai.trueid.lottie.value;
public class LottieValueCallback {
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation animation;
    private final vn.ai.trueid.lottie.value.LottieFrameInfo frameInfo;
    protected Object value;

    public LottieValueCallback()
    {
        this.frameInfo = new vn.ai.trueid.lottie.value.LottieFrameInfo();
        this.value = 0;
        return;
    }

    public LottieValueCallback(Object p2)
    {
        this.frameInfo = new vn.ai.trueid.lottie.value.LottieFrameInfo();
        this.value = p2;
        return;
    }

    public Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return this.value;
    }

    public final Object getValueInternal(float p9, float p10, Object p11, Object p12, float p13, float p14, float p15)
    {
        return this.getValue(this.frameInfo.set(p9, p10, p11, p12, p13, p14, p15));
    }

    public final void setAnimation(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation p1)
    {
        this.animation = p1;
        return;
    }

    public final void setValue(Object p1)
    {
        this.value = p1;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v1_1 = this.animation;
        if (v1_1 != null) {
            v1_1.notifyListeners();
        }
        return;
    }
}

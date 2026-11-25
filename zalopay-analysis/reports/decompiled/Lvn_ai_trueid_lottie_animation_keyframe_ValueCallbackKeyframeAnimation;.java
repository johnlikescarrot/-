package vn.ai.trueid.lottie.animation.keyframe;
public class ValueCallbackKeyframeAnimation extends vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation {
    private final Object valueCallbackValue;

    public ValueCallbackKeyframeAnimation(vn.ai.trueid.lottie.value.LottieValueCallback p2)
    {
        this(p2, 0);
        return;
    }

    public ValueCallbackKeyframeAnimation(vn.ai.trueid.lottie.value.LottieValueCallback p2, Object p3)
    {
        super(java.util.Collections.EMPTY_LIST);
        super.setValueCallback(p2);
        super.valueCallbackValue = p3;
        return;
    }

    public float getEndProgress()
    {
        return 1065353216;
    }

    public Object getValue()
    {
        Object v3 = this.valueCallbackValue;
        return this.valueCallback.getValueInternal(0, 0, v3, v3, this.getProgress(), this.getProgress(), this.getProgress());
    }

    public Object getValue(vn.ai.trueid.lottie.value.Keyframe p1, float p2)
    {
        return this.getValue();
    }

    public void notifyListeners()
    {
        if (this.valueCallback != null) {
            super.notifyListeners();
        }
        return;
    }

    public void setProgress(float p1)
    {
        this.progress = p1;
        return;
    }
}

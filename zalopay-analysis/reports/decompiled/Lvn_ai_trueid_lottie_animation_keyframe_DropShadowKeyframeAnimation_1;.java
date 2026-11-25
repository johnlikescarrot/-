package vn.ai.trueid.lottie.animation.keyframe;
 class DropShadowKeyframeAnimation$1 extends vn.ai.trueid.lottie.value.LottieValueCallback {
    final synthetic vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation this$0;
    final synthetic vn.ai.trueid.lottie.value.LottieValueCallback val$callback;

    public DropShadowKeyframeAnimation$1(vn.ai.trueid.lottie.animation.keyframe.DropShadowKeyframeAnimation p1, vn.ai.trueid.lottie.value.LottieValueCallback p2)
    {
        this.this$0 = p1;
        this.val$callback = p2;
        return;
    }

    public Float getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p2)
    {
        Float v2_4 = ((Float) this.val$callback.getValue(p2));
        if (v2_4 != null) {
            return Float.valueOf((v2_4.floatValue() * 1076048691));
        } else {
            return 0;
        }
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return this.getValue(p1);
    }
}

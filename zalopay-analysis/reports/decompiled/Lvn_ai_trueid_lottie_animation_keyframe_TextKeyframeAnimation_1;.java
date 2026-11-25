package vn.ai.trueid.lottie.animation.keyframe;
 class TextKeyframeAnimation$1 extends vn.ai.trueid.lottie.value.LottieValueCallback {
    final synthetic vn.ai.trueid.lottie.animation.keyframe.TextKeyframeAnimation this$0;
    final synthetic vn.ai.trueid.lottie.model.DocumentData val$documentData;
    final synthetic vn.ai.trueid.lottie.value.LottieFrameInfo val$stringFrameInfo;
    final synthetic vn.ai.trueid.lottie.value.LottieValueCallback val$valueCallback;

    public TextKeyframeAnimation$1(vn.ai.trueid.lottie.animation.keyframe.TextKeyframeAnimation p1, vn.ai.trueid.lottie.value.LottieFrameInfo p2, vn.ai.trueid.lottie.value.LottieValueCallback p3, vn.ai.trueid.lottie.model.DocumentData p4)
    {
        this.this$0 = p1;
        this.val$stringFrameInfo = p2;
        this.val$valueCallback = p3;
        this.val$documentData = p4;
        return;
    }

    public bridge synthetic Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p1)
    {
        return this.getValue(p1);
    }

    public vn.ai.trueid.lottie.model.DocumentData getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p17)
    {
        vn.ai.trueid.lottie.model.DocumentData v1_5;
        this.val$stringFrameInfo.set(p17.getStartFrame(), p17.getEndFrame(), ((vn.ai.trueid.lottie.model.DocumentData) p17.getStartValue()).text, ((vn.ai.trueid.lottie.model.DocumentData) p17.getEndValue()).text, p17.getLinearKeyframeProgress(), p17.getInterpolatedKeyframeProgress(), p17.getOverallProgress());
        String v3_1 = ((String) this.val$valueCallback.getValue(this.val$stringFrameInfo));
        if (p17.getInterpolatedKeyframeProgress() != 1065353216) {
            v1_5 = p17.getStartValue();
        } else {
            v1_5 = p17.getEndValue();
        }
        vn.ai.trueid.lottie.model.DocumentData v1_6 = ((vn.ai.trueid.lottie.model.DocumentData) v1_5);
        this.val$documentData.set(v3_1, v1_6.fontName, v1_6.size, v1_6.justification, v1_6.tracking, v1_6.lineHeight, v1_6.baselineShift, v1_6.color, v1_6.strokeColor, v1_6.strokeWidth, v1_6.strokeOverFill, v1_6.boxPosition, v1_6.boxSize);
        return this.val$documentData;
    }
}

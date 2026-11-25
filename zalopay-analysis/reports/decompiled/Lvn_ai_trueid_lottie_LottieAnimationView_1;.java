package vn.ai.trueid.lottie;
 class LottieAnimationView$1 extends vn.ai.trueid.lottie.value.LottieValueCallback {
    final synthetic vn.ai.trueid.lottie.LottieAnimationView this$0;
    final synthetic vn.ai.trueid.lottie.value.SimpleLottieValueCallback val$callback;

    public LottieAnimationView$1(vn.ai.trueid.lottie.LottieAnimationView p1, vn.ai.trueid.lottie.value.SimpleLottieValueCallback p2)
    {
        this.this$0 = p1;
        this.val$callback = p2;
        return;
    }

    public Object getValue(vn.ai.trueid.lottie.value.LottieFrameInfo p2)
    {
        return this.val$callback.getValue(p2);
    }
}

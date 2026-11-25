package vn.ai.trueid.lottie;
 class LottieDrawable$1 extends vn.ai.trueid.lottie.value.LottieValueCallback {
    final synthetic vn.ai.trueid.lottie.LottieDrawable this$0;
    final synthetic vn.ai.trueid.lottie.value.SimpleLottieValueCallback val$callback;

    public LottieDrawable$1(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.value.SimpleLottieValueCallback p2)
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

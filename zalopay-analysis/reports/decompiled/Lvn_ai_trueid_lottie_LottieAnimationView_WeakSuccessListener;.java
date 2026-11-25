package vn.ai.trueid.lottie;
 class LottieAnimationView$WeakSuccessListener implements vn.ai.trueid.lottie.LottieListener {
    private final ref.WeakReference targetReference;

    public LottieAnimationView$WeakSuccessListener(vn.ai.trueid.lottie.LottieAnimationView p2)
    {
        this.targetReference = new ref.WeakReference(p2);
        return;
    }

    public bridge synthetic void onResult(Object p1)
    {
        this.onResult(((vn.ai.trueid.lottie.LottieComposition) p1));
        return;
    }

    public void onResult(vn.ai.trueid.lottie.LottieComposition p2)
    {
        vn.ai.trueid.lottie.LottieAnimationView v0_2 = ((vn.ai.trueid.lottie.LottieAnimationView) this.targetReference.get());
        if (v0_2 != null) {
            v0_2.setComposition(p2);
            return;
        } else {
            return;
        }
    }
}

package vn.ai.trueid.lottie;
 class LottieAnimationView$WeakFailureListener implements vn.ai.trueid.lottie.LottieListener {
    private final ref.WeakReference targetReference;

    public LottieAnimationView$WeakFailureListener(vn.ai.trueid.lottie.LottieAnimationView p2)
    {
        this.targetReference = new ref.WeakReference(p2);
        return;
    }

    public bridge synthetic void onResult(Object p1)
    {
        this.onResult(((Throwable) p1));
        return;
    }

    public void onResult(Throwable p3)
    {
        vn.ai.trueid.lottie.LottieListener v0_3 = ((vn.ai.trueid.lottie.LottieAnimationView) this.targetReference.get());
        if (v0_3 != null) {
            if (vn.ai.trueid.lottie.LottieAnimationView.access$000(v0_3) != 0) {
                v0_3.setImageResource(vn.ai.trueid.lottie.LottieAnimationView.access$000(v0_3));
            }
            vn.ai.trueid.lottie.LottieListener v0_1;
            if (vn.ai.trueid.lottie.LottieAnimationView.access$100(v0_3) != null) {
                v0_1 = vn.ai.trueid.lottie.LottieAnimationView.access$100(v0_3);
            } else {
                v0_1 = vn.ai.trueid.lottie.LottieAnimationView.access$200();
            }
            v0_1.onResult(p3);
            return;
        } else {
            return;
        }
    }
}

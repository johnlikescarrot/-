package vn.ai.trueid.lottie;
 class LottieTask$LottieFutureTask extends java.util.concurrent.FutureTask {
    private vn.ai.trueid.lottie.LottieTask lottieTask;

    public LottieTask$LottieFutureTask(vn.ai.trueid.lottie.LottieTask p1, java.util.concurrent.Callable p2)
    {
        super(p2);
        super.lottieTask = p1;
        return;
    }

    public void done()
    {
        try {
            if (!this.isCancelled()) {
                try {
                    vn.ai.trueid.lottie.LottieTask.access$000(this.lottieTask, ((vn.ai.trueid.lottie.LottieResult) this.get()));
                } catch (java.util.concurrent.ExecutionException v1_0) {
                    vn.ai.trueid.lottie.LottieTask.access$000(this.lottieTask, new vn.ai.trueid.lottie.LottieResult(v1_0));
                } catch (java.util.concurrent.ExecutionException v1_0) {
                }
                this.lottieTask = 0;
                return;
            } else {
                this.lottieTask = 0;
                return;
            }
        } catch (java.util.concurrent.ExecutionException v1_1) {
            this.lottieTask = 0;
            throw v1_1;
        }
    }
}

package vn.ai.trueid.lottie;
final class LottieComposition$Factory$ListenerAdapter implements vn.ai.trueid.lottie.LottieListener, vn.ai.trueid.lottie.Cancellable {
    private boolean cancelled;
    private final vn.ai.trueid.lottie.OnCompositionLoadedListener listener;

    private LottieComposition$Factory$ListenerAdapter(vn.ai.trueid.lottie.OnCompositionLoadedListener p2)
    {
        this.cancelled = 0;
        this.listener = p2;
        return;
    }

    public synthetic LottieComposition$Factory$ListenerAdapter(vn.ai.trueid.lottie.OnCompositionLoadedListener p1, vn.ai.trueid.lottie.LottieComposition$1 p2)
    {
        this(p1);
        return;
    }

    public void cancel()
    {
        this.cancelled = 1;
        return;
    }

    public bridge synthetic void onResult(Object p1)
    {
        this.onResult(((vn.ai.trueid.lottie.LottieComposition) p1));
        return;
    }

    public void onResult(vn.ai.trueid.lottie.LottieComposition p2)
    {
        if (!this.cancelled) {
            this.listener.onCompositionLoaded(p2);
            return;
        } else {
            return;
        }
    }
}

package vn.ai.trueid.lottie;
public class LottieTask {
    static final String DIRECT_EXECUTOR_PROPERTY_NAME = "lottie.testing.directExecutor";
    public static java.util.concurrent.Executor EXECUTOR;
    private final java.util.Set failureListeners;
    private final android.os.Handler handler;
    private volatile vn.ai.trueid.lottie.LottieResult result;
    private final java.util.Set successListeners;

    static LottieTask()
    {
        if (!"true".equals(System.getProperty("lottie.testing.directExecutor"))) {
            vn.ai.trueid.lottie.LottieTask.EXECUTOR = java.util.concurrent.Executors.newCachedThreadPool(new vn.ai.trueid.lottie.utils.LottieThreadFactory());
            return;
        } else {
            vn.ai.trueid.lottie.LottieTask.EXECUTOR = new androidx.media3.exoplayer.video.a(1);
            return;
        }
    }

    public LottieTask(Object p3)
    {
        this.successListeners = new java.util.LinkedHashSet(1);
        this.failureListeners = new java.util.LinkedHashSet(1);
        this.handler = new android.os.Handler(android.os.Looper.getMainLooper());
        this.result = 0;
        this.setResult(new vn.ai.trueid.lottie.LottieResult(p3));
        return;
    }

    public LottieTask(java.util.concurrent.Callable p2)
    {
        this(p2, 0);
        return;
    }

    public LottieTask(java.util.concurrent.Callable p3, boolean p4)
    {
        this.successListeners = new java.util.LinkedHashSet(1);
        this.failureListeners = new java.util.LinkedHashSet(1);
        this.handler = new android.os.Handler(android.os.Looper.getMainLooper());
        this.result = 0;
        if (p4 == null) {
            vn.ai.trueid.lottie.LottieTask.EXECUTOR.execute(new vn.ai.trueid.lottie.LottieTask$LottieFutureTask(this, p3));
            return;
        } else {
            try {
                this.setResult(((vn.ai.trueid.lottie.LottieResult) p3.call()));
                return;
            } catch (Throwable v3_3) {
                this.setResult(new vn.ai.trueid.lottie.LottieResult(v3_3));
                return;
            }
        }
    }

    public static synthetic void a(vn.ai.trueid.lottie.LottieTask p0)
    {
        p0.notifyListenersInternal();
        return;
    }

    public static synthetic void access$000(vn.ai.trueid.lottie.LottieTask p0, vn.ai.trueid.lottie.LottieResult p1)
    {
        p0.setResult(p1);
        return;
    }

    private declared_synchronized void notifyFailureListeners(Throwable p3)
    {
        java.util.Iterator v0_2 = new java.util.ArrayList(this.failureListeners);
        if (!v0_2.isEmpty()) {
            java.util.Iterator v0_3 = v0_2.iterator();
            while (v0_3.hasNext()) {
                ((vn.ai.trueid.lottie.LottieListener) v0_3.next()).onResult(p3);
            }
            return;
        } else {
            vn.ai.trueid.lottie.utils.Logger.warning("Lottie encountered an error but no failure listener was added:", p3);
            return;
        }
    }

    private void notifyListeners()
    {
        if (android.os.Looper.myLooper() != android.os.Looper.getMainLooper()) {
            this.handler.post(new vn.ai.trueid.lottie.e(3, this));
            return;
        } else {
            this.notifyListenersInternal();
            return;
        }
    }

    private void notifyListenersInternal()
    {
        Throwable v0_0 = this.result;
        if (v0_0 != null) {
            if (v0_0.getValue() == null) {
                this.notifyFailureListeners(v0_0.getException());
                return;
            } else {
                this.notifySuccessListeners(v0_0.getValue());
                return;
            }
        } else {
            return;
        }
    }

    private declared_synchronized void notifySuccessListeners(Object p3)
    {
        java.util.Iterator v0_2 = new java.util.ArrayList(this.successListeners).iterator();
        while (v0_2.hasNext()) {
            ((vn.ai.trueid.lottie.LottieListener) v0_2.next()).onResult(p3);
        }
        return;
    }

    private void setResult(vn.ai.trueid.lottie.LottieResult p2)
    {
        if (this.result != null) {
            throw new IllegalStateException("A task may only be set once.");
        } else {
            this.result = p2;
            this.notifyListeners();
            return;
        }
    }

    public declared_synchronized vn.ai.trueid.lottie.LottieTask addFailureListener(vn.ai.trueid.lottie.LottieListener p3)
    {
        Throwable v0_0 = this.result;
        if ((v0_0 != null) && (v0_0.getException() != null)) {
            p3.onResult(v0_0.getException());
        }
        this.failureListeners.add(p3);
        return this;
    }

    public declared_synchronized vn.ai.trueid.lottie.LottieTask addListener(vn.ai.trueid.lottie.LottieListener p3)
    {
        Object v0_0 = this.result;
        if ((v0_0 != null) && (v0_0.getValue() != null)) {
            p3.onResult(v0_0.getValue());
        }
        this.successListeners.add(p3);
        return this;
    }

    public vn.ai.trueid.lottie.LottieResult getResult()
    {
        return this.result;
    }

    public declared_synchronized vn.ai.trueid.lottie.LottieTask removeFailureListener(vn.ai.trueid.lottie.LottieListener p2)
    {
        this.failureListeners.remove(p2);
        return this;
    }

    public declared_synchronized vn.ai.trueid.lottie.LottieTask removeListener(vn.ai.trueid.lottie.LottieListener p2)
    {
        this.successListeners.remove(p2);
        return this;
    }
}

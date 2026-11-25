package vn.ai.trueid.sdk.navigation;
public final class TrueIDLivenessCaptureStepCallback {
    private final java.util.concurrent.atomic.AtomicBoolean done;
    private final android.os.Handler handler;
    private final kotlin.jvm.functions.Function1 onFail;
    private final kotlin.jvm.functions.Function1 onProceed;
    private final Runnable timeoutRunnable;

    public TrueIDLivenessCaptureStepCallback(kotlin.jvm.functions.Function1 p3, kotlin.jvm.functions.Function1 p4, long p5)
    {
        this.onProceed = p3;
        this.onFail = p4;
        this.done = new java.util.concurrent.atomic.AtomicBoolean(0);
        android.os.Handler v3_1 = new android.os.Handler(android.os.Looper.getMainLooper());
        this.handler = v3_1;
        Ae.s v4_2 = new Ae.s(18, this);
        this.timeoutRunnable = v4_2;
        if (p5 > 0) {
            v3_1.postDelayed(v4_2, p5);
        }
        return;
    }

    public synthetic TrueIDLivenessCaptureStepCallback(kotlin.jvm.functions.Function1 p1, kotlin.jvm.functions.Function1 p2, long p3, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 4) != 0) {
            p3 = 60000;
        }
        this(p1, p2, p3);
        return;
    }

    public static synthetic void a(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p0, org.json.JSONObject p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback.proceed$lambda$1(p0, p1);
        return;
    }

    public static synthetic void b(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p0, int p1)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback.fail$lambda$2(p0, p1);
        return;
    }

    public static synthetic void c(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p0)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback.timeoutRunnable$lambda$0(p0);
        return;
    }

    public static synthetic void fail$default(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p0, int p1, String p2, int p3, Object p4)
    {
        if ((p3 & 2) != 0) {
            p2 = "";
        }
        p0.fail(p1, p2);
        return;
    }

    private static final void fail$lambda$2(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p1, int p2)
    {
        p1.onFail.invoke(new vn.ai.trueid.sdk.models.TrueError(p2));
        return;
    }

    private static final void proceed$lambda$1(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p0, org.json.JSONObject p1)
    {
        p0.onProceed.invoke(p1);
        return;
    }

    private static final void timeoutRunnable$lambda$0(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback p3)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback.fail$default(p3, 301, 0, 2, 0);
        return;
    }

    public final void fail(int p3)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback.fail$default(this, p3, 0, 2, 0);
        return;
    }

    public final void fail(int p3, String p4)
    {
        if (this.done.compareAndSet(0, 1)) {
            this.handler.removeCallbacks(this.timeoutRunnable);
            this.handler.post(new Be.a(this, p3, 4));
            return;
        } else {
            return;
        }
    }

    public final void proceed(org.json.JSONObject p4)
    {
        if (this.done.compareAndSet(0, 1)) {
            this.handler.removeCallbacks(this.timeoutRunnable);
            this.handler.post(new Kk.f(this, p4, 0));
            return;
        } else {
            return;
        }
    }
}

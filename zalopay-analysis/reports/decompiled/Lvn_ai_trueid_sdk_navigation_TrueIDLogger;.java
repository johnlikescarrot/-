package vn.ai.trueid.sdk.navigation;
public final class TrueIDLogger {
    public static final vn.ai.trueid.sdk.navigation.TrueIDLogger INSTANCE;
    public static final java.util.concurrent.CopyOnWriteArrayList a;

    static TrueIDLogger()
    {
        vn.ai.trueid.sdk.navigation.TrueIDLogger.INSTANCE = new vn.ai.trueid.sdk.navigation.TrueIDLogger();
        vn.ai.trueid.sdk.navigation.TrueIDLogger.a = new java.util.concurrent.CopyOnWriteArrayList();
        return;
    }

    public final void addObserver(kotlin.jvm.functions.Function1 p2)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLogger.a.add(p2);
        return;
    }

    public final void post$trueid_release(String p3)
    {
        java.util.Iterator v0_1 = vn.ai.trueid.sdk.navigation.TrueIDLogger.a.iterator();
        while (v0_1.hasNext()) {
            ((kotlin.jvm.functions.Function1) v0_1.next()).invoke(p3);
        }
        return;
    }

    public final void removeObserver(kotlin.jvm.functions.Function1 p2)
    {
        vn.ai.trueid.sdk.navigation.TrueIDLogger.a.remove(p2);
        return;
    }
}

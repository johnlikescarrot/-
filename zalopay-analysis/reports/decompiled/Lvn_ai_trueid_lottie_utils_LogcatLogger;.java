package vn.ai.trueid.lottie.utils;
public class LogcatLogger implements vn.ai.trueid.lottie.LottieLogger {
    private static final java.util.Set loggedMessages;

    static LogcatLogger()
    {
        vn.ai.trueid.lottie.utils.LogcatLogger.loggedMessages = new java.util.HashSet();
        return;
    }

    public LogcatLogger()
    {
        return;
    }

    public void debug(String p2)
    {
        this.debug(p2, 0);
        return;
    }

    public void debug(String p1, Throwable p2)
    {
        return;
    }

    public void error(String p1, Throwable p2)
    {
        return;
    }

    public void warning(String p2)
    {
        this.warning(p2, 0);
        return;
    }

    public void warning(String p3, Throwable p4)
    {
        java.util.Set v0 = vn.ai.trueid.lottie.utils.LogcatLogger.loggedMessages;
        if (!v0.contains(p3)) {
            android.util.Log.w("LOTTIE", p3, p4);
            v0.add(p3);
            return;
        } else {
            return;
        }
    }
}

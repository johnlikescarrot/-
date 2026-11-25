package vn.ai.trueid.lottie.utils;
public class Logger {
    private static vn.ai.trueid.lottie.LottieLogger INSTANCE;

    static Logger()
    {
        vn.ai.trueid.lottie.utils.Logger.INSTANCE = new vn.ai.trueid.lottie.utils.LogcatLogger();
        return;
    }

    public Logger()
    {
        return;
    }

    public static void debug(String p1)
    {
        vn.ai.trueid.lottie.utils.Logger.INSTANCE.debug(p1);
        return;
    }

    public static void debug(String p1, Throwable p2)
    {
        vn.ai.trueid.lottie.utils.Logger.INSTANCE.debug(p1, p2);
        return;
    }

    public static void error(String p1, Throwable p2)
    {
        vn.ai.trueid.lottie.utils.Logger.INSTANCE.error(p1, p2);
        return;
    }

    public static void setInstance(vn.ai.trueid.lottie.LottieLogger p0)
    {
        vn.ai.trueid.lottie.utils.Logger.INSTANCE = p0;
        return;
    }

    public static void warning(String p1)
    {
        vn.ai.trueid.lottie.utils.Logger.INSTANCE.warning(p1);
        return;
    }

    public static void warning(String p1, Throwable p2)
    {
        vn.ai.trueid.lottie.utils.Logger.INSTANCE.warning(p1, p2);
        return;
    }
}

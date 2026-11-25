package vn.ai.trueid.lottie;
public interface LottieLogger {

    public abstract void debug(String p0);

    public abstract void debug(String p0, Throwable p1);

    public abstract void error(String p0, Throwable p1);

    public abstract void warning(String p0);

    public abstract void warning(String p0, Throwable p1);
}

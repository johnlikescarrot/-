package vn.ai.trueid.lottie.utils;
public class LottieThreadFactory implements java.util.concurrent.ThreadFactory {
    private static final java.util.concurrent.atomic.AtomicInteger poolNumber;
    private final ThreadGroup group;
    private final String namePrefix;
    private final java.util.concurrent.atomic.AtomicInteger threadNumber;

    static LottieThreadFactory()
    {
        vn.ai.trueid.lottie.utils.LottieThreadFactory.poolNumber = new java.util.concurrent.atomic.AtomicInteger(1);
        return;
    }

    public LottieThreadFactory()
    {
        String v0_2;
        this.threadNumber = new java.util.concurrent.atomic.AtomicInteger(1);
        String v0_7 = System.getSecurityManager();
        if (v0_7 != null) {
            v0_2 = v0_7.getThreadGroup();
        } else {
            v0_2 = Thread.currentThread().getThreadGroup();
        }
        this.group = v0_2;
        String v0_4 = new StringBuilder("lottie-");
        v0_4.append(vn.ai.trueid.lottie.utils.LottieThreadFactory.poolNumber.getAndIncrement());
        v0_4.append("-thread-");
        this.namePrefix = v0_4.toString();
        return;
    }

    public Thread newThread(Runnable p7)
    {
        ThreadGroup v1 = this.group;
        Runnable v2_2 = new StringBuilder();
        v2_2.append(this.namePrefix);
        v2_2.append(this.threadNumber.getAndIncrement());
        Thread v0_1 = new Thread(v1, p7, v2_2.toString(), 0);
        v0_1.setDaemon(0);
        v0_1.setPriority(10);
        return v0_1;
    }
}

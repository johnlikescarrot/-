package vn.ai.trueid.lottie;
public final enum class AsyncUpdates extends java.lang.Enum {
    private static final synthetic vn.ai.trueid.lottie.AsyncUpdates[] $VALUES;
    public static final enum vn.ai.trueid.lottie.AsyncUpdates AUTOMATIC;
    public static final enum vn.ai.trueid.lottie.AsyncUpdates DISABLED;
    public static final enum vn.ai.trueid.lottie.AsyncUpdates ENABLED;

    private static synthetic vn.ai.trueid.lottie.AsyncUpdates[] $values()
    {
        return new vn.ai.trueid.lottie.AsyncUpdates[] {vn.ai.trueid.lottie.AsyncUpdates.AUTOMATIC, vn.ai.trueid.lottie.AsyncUpdates.ENABLED, vn.ai.trueid.lottie.AsyncUpdates.DISABLED});
    }

    static AsyncUpdates()
    {
        vn.ai.trueid.lottie.AsyncUpdates.AUTOMATIC = new vn.ai.trueid.lottie.AsyncUpdates("AUTOMATIC", 0);
        vn.ai.trueid.lottie.AsyncUpdates.ENABLED = new vn.ai.trueid.lottie.AsyncUpdates("ENABLED", 1);
        vn.ai.trueid.lottie.AsyncUpdates.DISABLED = new vn.ai.trueid.lottie.AsyncUpdates("DISABLED", 2);
        vn.ai.trueid.lottie.AsyncUpdates.$VALUES = vn.ai.trueid.lottie.AsyncUpdates.$values();
        return;
    }

    private AsyncUpdates(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static vn.ai.trueid.lottie.AsyncUpdates valueOf(String p1)
    {
        return ((vn.ai.trueid.lottie.AsyncUpdates) Enum.valueOf(vn.ai.trueid.lottie.AsyncUpdates, p1));
    }

    public static vn.ai.trueid.lottie.AsyncUpdates[] values()
    {
        return ((vn.ai.trueid.lottie.AsyncUpdates[]) vn.ai.trueid.lottie.AsyncUpdates.$VALUES.clone());
    }
}

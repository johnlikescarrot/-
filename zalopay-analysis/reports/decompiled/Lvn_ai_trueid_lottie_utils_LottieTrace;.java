package vn.ai.trueid.lottie.utils;
public class LottieTrace {
    private static final int MAX_DEPTH = 5;
    private int depthPastMaxDepth;
    private final String[] sections;
    private final long[] startTimeNs;
    private int traceDepth;

    public LottieTrace()
    {
        String[] v1 = new String[5];
        this.sections = v1;
        int v0_1 = new long[5];
        this.startTimeNs = v0_1;
        this.traceDepth = 0;
        this.depthPastMaxDepth = 0;
        return;
    }

    public void beginSection(String p5)
    {
        int v0 = this.traceDepth;
        if (v0 != 5) {
            this.sections[v0] = p5;
            this.startTimeNs[v0] = System.nanoTime();
            android.os.Trace.beginSection(p5);
            this.traceDepth = (this.traceDepth + 1);
            return;
        } else {
            this.depthPastMaxDepth = (this.depthPastMaxDepth + 1);
            return;
        }
    }

    public float endSection(String p5)
    {
        IllegalStateException v0_0 = this.depthPastMaxDepth;
        if (v0_0 <= null) {
            IllegalStateException v0_6 = (this.traceDepth - 1);
            this.traceDepth = v0_6;
            if (v0_6 == -1) {
                throw new IllegalStateException("Can\'t end trace section. There are none.");
            } else {
                if (!p5.equals(this.sections[v0_6])) {
                    throw new IllegalStateException(A1.a.l(androidx.activity.result.e.u("Unbalanced trace call ", p5, ". Expected "), this.sections[this.traceDepth], "."));
                } else {
                    android.os.Trace.endSection();
                    return (((float) (System.nanoTime() - this.startTimeNs[this.traceDepth])) / 1232348160);
                }
            }
        } else {
            this.depthPastMaxDepth = (v0_0 - 1);
            return 0;
        }
    }
}

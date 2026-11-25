package vn.ai.trueid.lottie;
 class PerformanceTracker$1 implements java.util.Comparator {
    final synthetic vn.ai.trueid.lottie.PerformanceTracker this$0;

    public PerformanceTracker$1(vn.ai.trueid.lottie.PerformanceTracker p1)
    {
        this.this$0 = p1;
        return;
    }

    public int compare(j2.d p2, j2.d p3)
    {
        int v2_7 = ((Float) p2.b).floatValue();
        float v3_3 = ((Float) p3.b).floatValue();
        if (v3_3 <= v2_7) {
            if (v2_7 <= v3_3) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 1;
        }
    }

    public bridge synthetic int compare(Object p1, Object p2)
    {
        return this.compare(((j2.d) p1), ((j2.d) p2));
    }
}

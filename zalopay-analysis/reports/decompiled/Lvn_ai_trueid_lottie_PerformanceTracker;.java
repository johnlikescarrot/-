package vn.ai.trueid.lottie;
public class PerformanceTracker {
    private boolean enabled;
    private final java.util.Comparator floatComparator;
    private final java.util.Set frameListeners;
    private final java.util.Map layerRenderTimes;

    public PerformanceTracker()
    {
        this.enabled = 0;
        this.frameListeners = new P.g(0);
        this.layerRenderTimes = new java.util.HashMap();
        this.floatComparator = new vn.ai.trueid.lottie.PerformanceTracker$1(this);
        return;
    }

    public void addFrameListener(vn.ai.trueid.lottie.PerformanceTracker$FrameListener p2)
    {
        this.frameListeners.add(p2);
        return;
    }

    public void clearRenderTimes()
    {
        this.layerRenderTimes.clear();
        return;
    }

    public java.util.List getSortedRenderTimes()
    {
        if (this.enabled) {
            java.util.ArrayList v0_3 = new java.util.ArrayList(this.layerRenderTimes.size());
            java.util.Comparator v1_0 = this.layerRenderTimes.entrySet().iterator();
            while (v1_0.hasNext()) {
                Float v2_2 = ((java.util.Map$Entry) v1_0.next());
                v0_3.add(new j2.d(((String) v2_2.getKey()), Float.valueOf(((vn.ai.trueid.lottie.utils.MeanCalculator) v2_2.getValue()).getMean())));
            }
            java.util.Collections.sort(v0_3, this.floatComparator);
            return v0_3;
        } else {
            return java.util.Collections.EMPTY_LIST;
        }
    }

    public void logRenderTimes()
    {
        if (this.enabled) {
            java.util.List v0_1 = this.getSortedRenderTimes();
            int v1 = 0;
            while (v1 < v0_1.size()) {
                Object[] v2_0 = ((j2.d) v0_1.get(v1));
                String.format("\t\t%30s:%.2f", new Object[] {v2_0.a, v2_0.b}));
                v1++;
            }
        }
        return;
    }

    public void recordRenderTime(String p3, float p4)
    {
        if (this.enabled) {
            vn.ai.trueid.lottie.PerformanceTracker$FrameListener v0_7 = ((vn.ai.trueid.lottie.utils.MeanCalculator) this.layerRenderTimes.get(p3));
            if (v0_7 == null) {
                v0_7 = new vn.ai.trueid.lottie.utils.MeanCalculator();
                this.layerRenderTimes.put(p3, v0_7);
            }
            v0_7.add(p4);
            if (p3.equals("__container")) {
                java.util.Iterator v3_3 = this.frameListeners.iterator();
                while (v3_3.hasNext()) {
                    ((vn.ai.trueid.lottie.PerformanceTracker$FrameListener) v3_3.next()).onFrameRendered(p4);
                }
            }
        }
        return;
    }

    public void removeFrameListener(vn.ai.trueid.lottie.PerformanceTracker$FrameListener p2)
    {
        this.frameListeners.remove(p2);
        return;
    }

    public void setEnabled(boolean p1)
    {
        this.enabled = p1;
        return;
    }
}

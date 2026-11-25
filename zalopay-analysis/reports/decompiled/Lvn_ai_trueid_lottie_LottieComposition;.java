package vn.ai.trueid.lottie;
public class LottieComposition {
    private android.graphics.Rect bounds;
    private P.O characters;
    private float endFrame;
    private java.util.Map fonts;
    private float frameRate;
    private boolean hasDashPattern;
    private java.util.Map images;
    private float imagesDpScale;
    private P.o layerMap;
    private java.util.List layers;
    private java.util.List markers;
    private int maskAndMatteCount;
    private final vn.ai.trueid.lottie.PerformanceTracker performanceTracker;
    private java.util.Map precomps;
    private float startFrame;
    private int unscaledHeight;
    private int unscaledWidth;
    private final java.util.HashSet warnings;

    public LottieComposition()
    {
        this.performanceTracker = new vn.ai.trueid.lottie.PerformanceTracker();
        this.warnings = new java.util.HashSet();
        this.maskAndMatteCount = 0;
        return;
    }

    public void addWarning(String p2)
    {
        vn.ai.trueid.lottie.utils.Logger.warning(p2);
        this.warnings.add(p2);
        return;
    }

    public android.graphics.Rect getBounds()
    {
        return this.bounds;
    }

    public P.O getCharacters()
    {
        return this.characters;
    }

    public float getDuration()
    {
        return ((float) ((long) ((this.getDurationFrames() / this.frameRate) * 1148846080)));
    }

    public float getDurationFrames()
    {
        return (this.endFrame - this.startFrame);
    }

    public float getEndFrame()
    {
        return this.endFrame;
    }

    public java.util.Map getFonts()
    {
        return this.fonts;
    }

    public float getFrameForProgress(float p3)
    {
        return vn.ai.trueid.lottie.utils.MiscUtils.lerp(this.startFrame, this.endFrame, p3);
    }

    public float getFrameRate()
    {
        return this.frameRate;
    }

    public java.util.Map getImages()
    {
        java.util.Map v0_0 = vn.ai.trueid.lottie.utils.Utils.dpScale();
        if (v0_0 != this.imagesDpScale) {
            java.util.Iterator v1_4 = this.images.entrySet().iterator();
            while (v1_4.hasNext()) {
                vn.ai.trueid.lottie.LottieImageAsset v2_2 = ((java.util.Map$Entry) v1_4.next());
                this.images.put(((String) v2_2.getKey()), ((vn.ai.trueid.lottie.LottieImageAsset) v2_2.getValue()).copyWithScale((this.imagesDpScale / v0_0)));
            }
        }
        this.imagesDpScale = v0_0;
        return this.images;
    }

    public java.util.List getLayers()
    {
        return this.layers;
    }

    public vn.ai.trueid.lottie.model.Marker getMarker(String p5)
    {
        int v0_1 = this.markers.size();
        int v1 = 0;
        while (v1 < v0_1) {
            vn.ai.trueid.lottie.model.Marker v2_0 = ((vn.ai.trueid.lottie.model.Marker) this.markers.get(v1));
            if (!v2_0.matchesName(p5)) {
                v1++;
            } else {
                return v2_0;
            }
        }
        return 0;
    }

    public java.util.List getMarkers()
    {
        return this.markers;
    }

    public int getMaskAndMatteCount()
    {
        return this.maskAndMatteCount;
    }

    public vn.ai.trueid.lottie.PerformanceTracker getPerformanceTracker()
    {
        return this.performanceTracker;
    }

    public java.util.List getPrecomps(String p2)
    {
        return ((java.util.List) this.precomps.get(p2));
    }

    public float getProgressForFrame(float p3)
    {
        float v0 = this.startFrame;
        return ((p3 - v0) / (this.endFrame - v0));
    }

    public float getStartFrame()
    {
        return this.startFrame;
    }

    public int getUnscaledHeight()
    {
        return this.unscaledHeight;
    }

    public int getUnscaledWidth()
    {
        return this.unscaledWidth;
    }

    public java.util.ArrayList getWarnings()
    {
        java.util.List v1_0 = this.warnings;
        String[] v2_1 = new String[v1_0.size()];
        return new java.util.ArrayList(java.util.Arrays.asList(((String[]) v1_0.toArray(v2_1))));
    }

    public boolean hasDashPattern()
    {
        return this.hasDashPattern;
    }

    public boolean hasImages()
    {
        return (this.images.isEmpty() ^ 1);
    }

    public void incrementMatteOrMaskCount(int p2)
    {
        this.maskAndMatteCount = (this.maskAndMatteCount + p2);
        return;
    }

    public void init(android.graphics.Rect p1, float p2, float p3, float p4, java.util.List p5, P.o p6, java.util.Map p7, java.util.Map p8, float p9, P.O p10, java.util.Map p11, java.util.List p12, int p13, int p14)
    {
        this.bounds = p1;
        this.startFrame = p2;
        this.endFrame = p3;
        this.frameRate = p4;
        this.layers = p5;
        this.layerMap = p6;
        this.precomps = p7;
        this.images = p8;
        this.imagesDpScale = p9;
        this.characters = p10;
        this.fonts = p11;
        this.markers = p12;
        this.unscaledWidth = p13;
        this.unscaledHeight = p14;
        return;
    }

    public vn.ai.trueid.lottie.model.layer.Layer layerModelForId(long p2)
    {
        return ((vn.ai.trueid.lottie.model.layer.Layer) this.layerMap.b(p2));
    }

    public void setHasDashPattern(boolean p1)
    {
        this.hasDashPattern = p1;
        return;
    }

    public void setPerformanceTrackingEnabled(boolean p2)
    {
        this.performanceTracker.setEnabled(p2);
        return;
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("LottieComposition:\n");
        java.util.Iterator v1_2 = this.layers.iterator();
        while (v1_2.hasNext()) {
            v0_2.append(((vn.ai.trueid.lottie.model.layer.Layer) v1_2.next()).toString("\t"));
        }
        return v0_2.toString();
    }
}

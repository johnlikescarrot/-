package vn.ai.trueid.lottie;
public class TextDelegate {
    private final vn.ai.trueid.lottie.LottieAnimationView animationView;
    private boolean cacheText;
    private final vn.ai.trueid.lottie.LottieDrawable drawable;
    private final java.util.Map stringMap;

    public TextDelegate()
    {
        this.stringMap = new java.util.HashMap();
        this.cacheText = 1;
        this.animationView = 0;
        this.drawable = 0;
        return;
    }

    public TextDelegate(vn.ai.trueid.lottie.LottieAnimationView p2)
    {
        this.stringMap = new java.util.HashMap();
        this.cacheText = 1;
        this.animationView = p2;
        this.drawable = 0;
        return;
    }

    public TextDelegate(vn.ai.trueid.lottie.LottieDrawable p2)
    {
        this.stringMap = new java.util.HashMap();
        this.cacheText = 1;
        this.drawable = p2;
        this.animationView = 0;
        return;
    }

    private void invalidate()
    {
        vn.ai.trueid.lottie.LottieDrawable v0_0 = this.animationView;
        if (v0_0 != null) {
            v0_0.invalidate();
        }
        vn.ai.trueid.lottie.LottieDrawable v0_1 = this.drawable;
        if (v0_1 != null) {
            v0_1.invalidateSelf();
        }
        return;
    }

    public String getText(String p1)
    {
        return p1;
    }

    public String getText(String p1, String p2)
    {
        return this.getText(p2);
    }

    public final String getTextInternal(String p2, String p3)
    {
        if ((!this.cacheText) || (!this.stringMap.containsKey(p3))) {
            String v2_4 = this.getText(p2, p3);
            if (this.cacheText) {
                this.stringMap.put(p3, v2_4);
            }
            return v2_4;
        } else {
            return ((String) this.stringMap.get(p3));
        }
    }

    public void invalidateAllText()
    {
        this.stringMap.clear();
        this.invalidate();
        return;
    }

    public void invalidateText(String p2)
    {
        this.stringMap.remove(p2);
        this.invalidate();
        return;
    }

    public void setCacheText(boolean p1)
    {
        this.cacheText = p1;
        return;
    }

    public void setText(String p2, String p3)
    {
        this.stringMap.put(p2, p3);
        this.invalidate();
        return;
    }
}

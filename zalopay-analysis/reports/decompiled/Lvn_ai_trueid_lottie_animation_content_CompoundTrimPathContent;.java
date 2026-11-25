package vn.ai.trueid.lottie.animation.content;
public class CompoundTrimPathContent {
    private final java.util.List contents;

    public CompoundTrimPathContent()
    {
        this.contents = new java.util.ArrayList();
        return;
    }

    public void addTrimPath(vn.ai.trueid.lottie.animation.content.TrimPathContent p2)
    {
        this.contents.add(p2);
        return;
    }

    public void apply(android.graphics.Path p3)
    {
        int v0_1 = (this.contents.size() - 1);
        while (v0_1 >= 0) {
            vn.ai.trueid.lottie.utils.Utils.applyTrimPathIfNeeded(p3, ((vn.ai.trueid.lottie.animation.content.TrimPathContent) this.contents.get(v0_1)));
            v0_1--;
        }
        return;
    }
}

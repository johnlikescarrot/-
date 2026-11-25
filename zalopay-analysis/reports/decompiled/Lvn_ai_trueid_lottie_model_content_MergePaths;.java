package vn.ai.trueid.lottie.model.content;
public class MergePaths implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final boolean hidden;
    private final vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode mode;
    private final String name;

    public MergePaths(String p1, vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode p2, boolean p3)
    {
        this.name = p1;
        this.mode = p2;
        this.hidden = p3;
        return;
    }

    public vn.ai.trueid.lottie.model.content.MergePaths$MergePathsMode getMode()
    {
        return this.mode;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.LottieComposition p2, vn.ai.trueid.lottie.model.layer.BaseLayer p3)
    {
        if (p1.isFeatureFlagEnabled(vn.ai.trueid.lottie.LottieFeatureFlag.MergePathsApi19)) {
            return new vn.ai.trueid.lottie.animation.content.MergePathsContent(this);
        } else {
            vn.ai.trueid.lottie.utils.Logger.warning("Animation contains merge paths but they are disabled.");
            return 0;
        }
    }

    public String toString()
    {
        String v0_1 = new StringBuilder("MergePaths{mode=");
        v0_1.append(this.mode);
        v0_1.append(125);
        return v0_1.toString();
    }
}

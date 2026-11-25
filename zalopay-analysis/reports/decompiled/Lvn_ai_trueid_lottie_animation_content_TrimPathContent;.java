package vn.ai.trueid.lottie.animation.content;
public class TrimPathContent implements vn.ai.trueid.lottie.animation.content.Content, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener {
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation endAnimation;
    private final boolean hidden;
    private final java.util.List listeners;
    private final String name;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation offsetAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation startAnimation;
    private final vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type type;

    public TrimPathContent(vn.ai.trueid.lottie.model.layer.BaseLayer p3, vn.ai.trueid.lottie.model.content.ShapeTrimPath p4)
    {
        this.listeners = new java.util.ArrayList();
        this.name = p4.getName();
        this.hidden = p4.isHidden();
        this.type = p4.getType();
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_3 = p4.getStart().createAnimation();
        this.startAnimation = v0_3;
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v1_1 = p4.getEnd().createAnimation();
        this.endAnimation = v1_1;
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v4_2 = p4.getOffset().createAnimation();
        this.offsetAnimation = v4_2;
        p3.addAnimation(v0_3);
        p3.addAnimation(v1_1);
        p3.addAnimation(v4_2);
        v0_3.addUpdateListener(this);
        v1_1.addUpdateListener(this);
        v4_2.addUpdateListener(this);
        return;
    }

    public void addListener(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener p2)
    {
        this.listeners.add(p2);
        return;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation getEnd()
    {
        return this.endAnimation;
    }

    public String getName()
    {
        return this.name;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation getOffset()
    {
        return this.offsetAnimation;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation getStart()
    {
        return this.startAnimation;
    }

    public vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type getType()
    {
        return this.type;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public void onValueChanged()
    {
        int v0 = 0;
        while (v0 < this.listeners.size()) {
            ((vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener) this.listeners.get(v0)).onValueChanged();
            v0++;
        }
        return;
    }

    public void setContents(java.util.List p1, java.util.List p2)
    {
        return;
    }
}

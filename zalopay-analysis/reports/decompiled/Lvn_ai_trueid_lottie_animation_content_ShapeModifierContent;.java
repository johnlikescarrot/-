package vn.ai.trueid.lottie.animation.content;
public interface ShapeModifierContent implements vn.ai.trueid.lottie.animation.content.Content {

    public abstract void addUpdateListener(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener p0);

    public abstract vn.ai.trueid.lottie.model.content.ShapeData modifyShape(vn.ai.trueid.lottie.model.content.ShapeData p0);
}

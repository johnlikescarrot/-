package vn.ai.trueid.lottie.animation.content;
public interface DrawingContent implements vn.ai.trueid.lottie.animation.content.Content {

    public abstract void draw(android.graphics.Canvas p0, android.graphics.Matrix p1, int p2, vn.ai.trueid.lottie.utils.DropShadow p3);

    public abstract void getBounds(android.graphics.RectF p0, android.graphics.Matrix p1, boolean p2);
}

package vn.ai.trueid.lottie.model.layer;
public class NullLayer extends vn.ai.trueid.lottie.model.layer.BaseLayer {

    public NullLayer(vn.ai.trueid.lottie.LottieDrawable p1, vn.ai.trueid.lottie.model.layer.Layer p2)
    {
        super(p1, p2);
        return;
    }

    public void drawLayer(android.graphics.Canvas p1, android.graphics.Matrix p2, int p3, vn.ai.trueid.lottie.utils.DropShadow p4)
    {
        return;
    }

    public void getBounds(android.graphics.RectF p1, android.graphics.Matrix p2, boolean p3)
    {
        super.getBounds(p1, p2, p3);
        p1.set(0, 0, 0, 0);
        return;
    }
}

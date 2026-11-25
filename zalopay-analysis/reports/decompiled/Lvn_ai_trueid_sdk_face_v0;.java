package vn.ai.trueid.sdk.face;
public final class v0 implements android.animation.Animator$AnimatorListener {
    public final synthetic vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView a;

    public v0(vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView p1)
    {
        this.a = p1;
        return;
    }

    public final void onAnimationCancel(android.animation.Animator p1)
    {
        return;
    }

    public final void onAnimationEnd(android.animation.Animator p2)
    {
        vn.ai.trueid.sdk.face.a2 v2_0 = this.a;
        v2_0.setRectZoomOut(v2_0.getRectZoomIn());
        vn.ai.trueid.sdk.face.a2 v2_2 = this.a.q;
        if (v2_2 != null) {
            ((vn.ai.trueid.sdk.face.a2) v2_2).d();
        }
        return;
    }

    public final void onAnimationRepeat(android.animation.Animator p1)
    {
        return;
    }

    public final void onAnimationStart(android.animation.Animator p2)
    {
        android.widget.TextView v2_1 = this.a.q;
        if (v2_1 != null) {
            ((vn.ai.trueid.sdk.face.a2) v2_1).c.l.setText("");
        }
        return;
    }
}

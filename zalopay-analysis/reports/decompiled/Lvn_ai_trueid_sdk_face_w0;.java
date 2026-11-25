package vn.ai.trueid.sdk.face;
public final class w0 implements java.lang.Runnable {
    public final synthetic vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView a;

    public w0(vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView p1)
    {
        this.a = p1;
        return;
    }

    public final void run()
    {
        vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v0_0 = this.a;
        v0_0.u = (v0_0.u + 1084227584);
        v0_0.invalidate();
        this.a.postDelayed(this, 16);
        return;
    }
}

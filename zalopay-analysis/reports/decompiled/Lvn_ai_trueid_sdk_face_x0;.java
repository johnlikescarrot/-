package vn.ai.trueid.sdk.face;
public final class x0 extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public final synthetic vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView a;

    public x0(vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView p1)
    {
        this.a = p1;
        super(0);
        return;
    }

    public final Object invoke()
    {
        Float v0_1 = new android.util.TypedValue();
        this.a.getResources().getValue(vn.ai.trueid.sdk.face.R$dimen.facesdk_overlay_sweep_angle, v0_1, 1);
        return Float.valueOf(v0_1.getFloat());
    }
}

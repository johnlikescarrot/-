package vn.ai.trueid.sdk.facev3.present.presentation.widgets;
public final class TrueIDAspectRatioFrameLayout extends android.widget.FrameLayout {
    public float a;

    public TrueIDAspectRatioFrameLayout(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.a = 1061158912;
        return;
    }

    public final float getAspectRatio()
    {
        return this.a;
    }

    public final void onMeasure(int p2, int p3)
    {
        super.onMeasure(p2, android.view.View$MeasureSpec.makeMeasureSpec(((int) (((float) android.view.View$MeasureSpec.getSize(p2)) / this.a)), 1073741824));
        return;
    }

    public final void setAspectRatio(float p1)
    {
        this.a = p1;
        return;
    }
}

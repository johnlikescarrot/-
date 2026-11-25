package vn.zalopay.ui;
public final class AutoFitTextureView extends android.view.TextureView {

    public AutoFitTextureView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public final void onMeasure(int p1, int p2)
    {
        super.onMeasure(p1, p2);
        this.setMeasuredDimension(android.view.View$MeasureSpec.getSize(p1), android.view.View$MeasureSpec.getSize(p2));
        return;
    }
}

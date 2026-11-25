package vn.zalopay.ui.textview;
public class RoundTextView extends androidx.appcompat.widget.AppCompatTextView {
    public final o00.b h;
    public final float i;

    public RoundTextView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        super.i = super.getResources().getDisplayMetrics().density;
        super.h = new o00.b(super, p2, p3);
        return;
    }

    public float getDensity()
    {
        return this.i;
    }

    public final void onLayout(boolean p1, int p2, int p3, int p4, int p5)
    {
        this = super.onLayout(p1, p2, p3, p4, p5);
        o00.b v2_1 = this.h;
        if (!v2_1.p) {
            v2_1.a();
            return;
        } else {
            v2_1.g = ((int) ((((float) (this.getHeight() / 2)) * v2_1.b.getResources().getDisplayMetrics().density) + 1056964608));
            v2_1.a();
            return;
        }
    }

    public final void onMeasure(int p2, int p3)
    {
        if ((!this.h.q) || ((this.getWidth() <= 0) || (this.getHeight() <= 0))) {
            super.onMeasure(p2, p3);
            return;
        } else {
            super.onMeasure(android.view.View$MeasureSpec.makeMeasureSpec(Math.max(this.getWidth(), this.getHeight()), 1073741824), android.view.View$MeasureSpec.makeMeasureSpec(Math.max(this.getWidth(), this.getHeight()), 1073741824));
            return;
        }
    }

    public void setShouldDisplay2DigitValue(boolean p1)
    {
        return;
    }
}

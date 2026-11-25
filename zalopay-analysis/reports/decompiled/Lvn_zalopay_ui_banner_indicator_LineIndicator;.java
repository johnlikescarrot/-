package vn.zalopay.ui.banner.indicator;
public final class LineIndicator extends vZ.a {
    public final int d;

    public LineIndicator(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.d = super.getContext().getColor(2131099769);
        return;
    }

    public final void onDraw(android.graphics.Canvas p9)
    {
        super.onDraw(p9);
        this.getPaint().setColor(this.d);
        if ((this.getSize() > 1) && (this.getCurrentPosition() >= 0)) {
            int v0_3 = (((float) this.getWidth()) / ((float) this.getSize()));
            float v3 = (v0_3 * ((float) this.getCurrentPosition()));
            p9.drawRect(v3, 0, (v3 + v0_3), ((float) this.getHeight()), this.getPaint());
        }
        return;
    }
}

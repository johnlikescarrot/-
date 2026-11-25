package vn.zalopay.ui.edittext;
public class ErrorEditText extends vn.zalopay.ui.edittext.ZPEditText {
    public String E0;

    public ErrorEditText(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public final void onDraw(android.graphics.Canvas p7)
    {
        super.onDraw(p7);
        if (!android.text.TextUtils.isEmpty(this.E0)) {
            android.text.TextPaint v1 = this.k0;
            v1.setTextSize(((float) this.i));
            v1.setColor(this.v);
            v1.setAlpha(255);
            int v2_1 = (this.getPaddingLeft() + this.getScrollX());
            float v3_2 = ((this.getWidth() + this.getScrollX()) - this.getPaddingRight());
            int v0_8 = v1.measureText(this.I.toString());
            if ((this.getGravity() & 8388613) != 8388613) {
                if ((this.getGravity() & 8388611) != 8388611) {
                    v2_1 += ((int) (((((float) ((this.getWidth() - this.s) - this.t)) - v0_8) / 1073741824) + ((float) this.s)));
                }
            } else {
                v2_1 = ((int) (((float) v3_2) - v0_8));
            }
            p7.drawText(this.E0, ((float) v2_1), ((float) (this.getScrollY() + (this.q + this.i))), v1);
            return;
        } else {
            return;
        }
    }

    public void setErrorMessage(int p2)
    {
        this.setErrorMessage(this.getContext().getString(p2));
        return;
    }

    public void setErrorMessage(String p1)
    {
        this.E0 = p1;
        this.v0 = android.text.TextUtils.isEmpty(p1);
        this.f();
        this.invalidate();
        return;
    }
}

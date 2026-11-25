package vn.zalopay.ui.password.view;
public class SquareImageView extends android.widget.ImageView {

    public SquareImageView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public final void onMeasure(int p1, int p2)
    {
        super.onMeasure(p1, p2);
        int v1_1 = this.getMeasuredWidth();
        int v2_1 = this.getMeasuredHeight();
        if (v2_1 <= v1_1) {
            this.setMeasuredDimension(v2_1, v2_1);
            return;
        } else {
            this.setMeasuredDimension(v1_1, v1_1);
            return;
        }
    }
}

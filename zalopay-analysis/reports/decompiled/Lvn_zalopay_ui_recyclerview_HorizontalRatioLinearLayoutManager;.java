package vn.zalopay.ui.recyclerview;
public final class HorizontalRatioLinearLayoutManager extends androidx.recyclerview.widget.LinearLayoutManager {
    public final float E;

    public HorizontalRatioLinearLayoutManager()
    {
        super(0);
        super.E = 1065353216;
        return;
    }

    public final androidx.recyclerview.widget.k0 r()
    {
        int v0 = this.n;
        return new androidx.recyclerview.widget.k0(v0, ((int) (((float) v0) * this.E)));
    }

    public final androidx.recyclerview.widget.k0 s(android.content.Context p4, android.util.AttributeSet p5)
    {
        int v0 = this.n;
        float v1_1 = (((float) v0) * this.E);
        androidx.recyclerview.widget.k0 v2_2 = new androidx.recyclerview.widget.k0(p4, p5);
        v2_2.width = v0;
        v2_2.height = ((int) v1_1);
        return v2_2;
    }

    public final androidx.recyclerview.widget.k0 t(android.view.ViewGroup$LayoutParams p4)
    {
        int v0_0 = this.n;
        float v1_1 = (((float) v0_0) * this.E);
        androidx.recyclerview.widget.k0 v4_1 = super.t(p4);
        v4_1.width = v0_0;
        v4_1.height = ((int) v1_1);
        return v4_1;
    }
}

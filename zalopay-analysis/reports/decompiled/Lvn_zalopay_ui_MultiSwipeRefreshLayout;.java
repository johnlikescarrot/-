package vn.zalopay.ui;
public class MultiSwipeRefreshLayout extends androidx.swiperefreshlayout.widget.SwipeRefreshLayout {
    public android.view.View[] M;
    public final int N;
    public float O;
    public boolean P;

    public MultiSwipeRefreshLayout(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.N = android.view.ViewConfiguration.get(p1).getScaledTouchSlop();
        return;
    }

    public final boolean a()
    {
        int v0_0 = this.M;
        if ((v0_0 != 0) && (v0_0.length > 0)) {
            int v3 = 0;
            while (v3 < v0_0.length) {
                boolean v4_0 = v0_0[v3];
                if ((!v4_0) || ((!v4_0.isShown()) || (v4_0.canScrollVertically(-1)))) {
                    v3++;
                } else {
                    return 0;
                }
            }
        }
        return 1;
    }

    public final boolean onInterceptTouchEvent(android.view.MotionEvent p4)
    {
        float v0_0 = p4.getAction();
        if (v0_0 == 0) {
            this.O = android.view.MotionEvent.obtain(p4).getX();
            this.P = 0;
        } else {
            if (v0_0 == 2) {
                float vtmp5 = Math.abs((p4.getX() - this.O));
                if ((this.P) || (vtmp5 > ((float) this.N))) {
                    this.P = 1;
                    return 0;
                }
            }
        }
        return super.onInterceptTouchEvent(p4);
    }

    public varargs void setSwipeableChildren(int[] p4)
    {
        int v0_1 = new android.view.View[p4.length];
        this.M = v0_1;
        int v0_2 = 0;
        while (v0_2 < p4.length) {
            this.M[v0_2] = this.findViewById(p4[v0_2]);
            v0_2++;
        }
        return;
    }
}

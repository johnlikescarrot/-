package vn.zalopay.ui;
public final class AutoScrollRecyclerView extends androidx.recyclerview.widget.RecyclerView {
    public final android.os.Handler a;
    public long b;
    public sZ.g c;

    public AutoScrollRecyclerView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        super.a = new android.os.Handler(android.os.Looper.getMainLooper());
        return;
    }

    private final int getCurrentPosition()
    {
        if (!(this.getLayoutManager() instanceof androidx.recyclerview.widget.LinearLayoutManager)) {
            return -1;
        } else {
            int v0_5 = ((androidx.recyclerview.widget.LinearLayoutManager) this.getLayoutManager());
            int v0_1 = v0_5.N0(0, v0_5.v(), 1, 0);
            if (v0_1 != 0) {
                return androidx.recyclerview.widget.j0.E(v0_1);
            } else {
                return -1;
            }
        }
    }

    public static void q(vn.zalopay.ui.AutoScrollRecyclerView p3)
    {
        int v0_0 = p3.getCurrentPosition();
        if (v0_0 != -1) {
            sZ.g v1_1 = p3.c;
            if (v1_1 != null) {
                v1_1.c(0);
            }
            p3.smoothScrollToPosition((v0_0 + 1));
        }
        p3.r();
        return;
    }

    public final boolean dispatchTouchEvent(android.view.MotionEvent p3)
    {
        if (p3) {
            android.os.Handler v0_0 = p3.getActionMasked();
            if (v0_0 != null) {
                if (v0_0 != 1) {
                    if (v0_0 == 2) {
                        this.a.removeCallbacksAndMessages(0);
                        return super.dispatchTouchEvent(p3);
                    } else {
                        if (v0_0 != 3) {
                            return super.dispatchTouchEvent(p3);
                        }
                    }
                }
                this.r();
                return super.dispatchTouchEvent(p3);
            }
        }
        return super.dispatchTouchEvent(p3);
    }

    public final sZ.g getListener()
    {
        return this.c;
    }

    public final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        this.a.removeCallbacksAndMessages(0);
        this.r();
        return;
    }

    public final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        this.a.removeCallbacksAndMessages(0);
        return;
    }

    public final void onScrollStateChanged(int p2)
    {
        if (p2 == null) {
            sZ.g v2_2 = this.getCurrentPosition();
            if (v2_2 != -1) {
                sZ.g v0_1 = this.c;
                if (v0_1 != null) {
                    v0_1.b(v2_2);
                }
            }
        } else {
            if (p2 == 1) {
                sZ.g v2_1 = this.c;
                if (v2_1 != null) {
                    v2_1.c(1);
                    return;
                }
            }
        }
        return;
    }

    public final void r()
    {
        long v0 = this.b;
        if (v0 > 0) {
            this.a.postDelayed(new com.appsflyer.b(25, this), v0);
            return;
        } else {
            return;
        }
    }

    public final void setListener(sZ.g p1)
    {
        this.c = p1;
        return;
    }

    public final void setTimeSpan(long p3)
    {
        this.a.removeCallbacksAndMessages(0);
        this.b = p3;
        this.r();
        return;
    }
}

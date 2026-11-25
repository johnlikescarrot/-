package vn.zalopay.ui.svgutil;
public final class ZPSVGImageView extends vn.zalopay.ui.androidsvg.E0 implements j00.c {
    public static final kotlinx.coroutines.MainCoroutineDispatcher i;
    public static final gi.F j;
    public String d;
    public int e;
    public final gi.S0 f;
    public final li.e g;
    public int h;

    static ZPSVGImageView()
    {
        vn.zalopay.ui.svgutil.ZPSVGImageView.i = kotlinx.coroutines.Dispatchers.getMain();
        vn.zalopay.ui.svgutil.ZPSVGImageView.j = kotlinx.coroutines.Dispatchers.getIO();
        return;
    }

    public ZPSVGImageView(android.content.Context p5, android.util.AttributeSet p6)
    {
        super(p5, p6);
        int[] v0_1 = gi.N.d();
        super.f = v0_1;
        super.g = gi.L.a(v0_1);
        super.h = 2131231831;
        int[] v0_0 = j00.d.c.a;
        if (v0_0 != null) {
            java.util.ArrayList v1_1 = v0_0.a.iterator();
            while (v1_1.hasNext()) {
                boolean v2_4 = ((ref.WeakReference) v1_1.next());
                if (v2_4.get() != null) {
                    ((j00.c) v2_4.get()).getClass();
                    if (((j00.c) v2_4.get()).equals(super)) {
                    }
                }
            }
            v0_0.a.add(new ref.WeakReference(super));
        }
        Throwable v5_2 = p5.obtainStyledAttributes(p6, sZ.h.t);
        try {
            super.e = v5_2.getResourceId(0, 0);
            v5_2.recycle();
            return;
        } catch (RuntimeException) {
            v5_2.recycle();
            return;
        } catch (Throwable v6_3) {
            v5_2.recycle();
            throw v6_3;
        }
    }

    public static final synthetic void g(vn.zalopay.ui.svgutil.ZPSVGImageView p0, boolean p1)
    {
        p0.setImage(p1);
        return;
    }

    private final void setImage(boolean p4)
    {
        if (p4 == null) {
            Exception v4_1 = this.e;
            if (v4_1 > null) {
                try {
                    this.setImageResourceInternal(v4_1);
                    return;
                } catch (Exception v4_5) {
                    Object[] v0_4 = new Object[0];
                    ck.b.g(v4_5, wmoi.igob.ggcn(2244), v0_4);
                }
            }
            this.setImageResourceInternal(this.h);
            return;
        } else {
            Exception v4_4;
            Exception v4_3 = this.d;
            if (!android.text.TextUtils.isEmpty(v4_3)) {
                v4_4 = android.net.Uri.fromFile(new java.io.File(v4_3));
            } else {
                v4_4 = android.net.Uri.EMPTY;
            }
            this.setImageURI(v4_4);
            return;
        }
    }

    public final void c()
    {
        this.h();
        return;
    }

    public final void h()
    {
        if (this.isAttachedToWindow()) {
            if (!android.text.TextUtils.isEmpty(this.d)) {
                kotlinx.coroutines.BuildersKt.launch$default(this.g, 0, 0, new cV.Y(this, 0, 7), 3, 0);
                return;
            } else {
                Exception v0_1 = this.e;
                if (v0_1 > null) {
                    try {
                        this.setImageResourceInternal(v0_1);
                        return;
                    } catch (Exception v0_2) {
                        Object[] v1_2 = new Object[0];
                        ck.b.g(v0_2, wmoi.igob.ggcn(2245), v1_2);
                    }
                }
                this.setImageResourceInternal(this.h);
                return;
            }
        } else {
            return;
        }
    }

    public final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (this.d != null) {
            this.h();
        }
        return;
    }

    public final void onDetachedFromWindow()
    {
        gi.N.g(this.f);
        super.onDetachedFromWindow();
        return;
    }

    public final void setDefaultIcon(int p1)
    {
        this.h = p1;
        return;
    }

    public final void setImageName(String p7)
    {
        android.content.res.Resources v1_3 = "";
        int v2_0 = 0;
        if (!android.text.TextUtils.isEmpty(p7)) {
            String v0_5 = j00.d.c;
            String v3_5 = v0_5.b;
            String v4_1 = wmoi.igob.ggcn(2247);
            if (v3_5 != null) {
                android.content.res.Resources v1_2 = androidx.activity.result.e.n(p7, wmoi.igob.ggcn(2248));
                if (!android.text.TextUtils.isEmpty(v1_2)) {
                    java.util.Locale.getDefault();
                    v1_3 = androidx.activity.result.e.o(n9.e0.a(wmoi.igob.ggcn(2249)), wmoi.igob.ggcn(2250), v1_2);
                } else {
                    v1_3 = 0;
                }
            } else {
                String v3_4 = new Object[0];
                ck.b.e(v4_1, v3_4);
            }
            this.d = v1_3;
            String v0_1 = v0_5.b;
            if (v0_1 != null) {
                String v0_3 = ((android.app.Application) v0_1.b);
                v2_0 = v0_3.getResources().getIdentifier(androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(2251), p7), wmoi.igob.ggcn(2252), v0_3.getPackageName());
            } else {
                String v7_2 = new Object[0];
                ck.b.e(v4_1, v7_2);
            }
            this.e = v2_0;
            this.h();
            return;
        } else {
            String v0_6 = new Object[0];
            ck.b.e(wmoi.igob.ggcn(2246), v0_6);
            this.d = "";
            this.e = 0;
            this.h();
            return;
        }
    }
}

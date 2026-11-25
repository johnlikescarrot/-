package vn.zalopay.ui.androidsvg;
public abstract class E0 extends androidx.appcompat.widget.AppCompatImageView {
    public static final reflect.Method c;
    public vn.zalopay.ui.androidsvg.t0 a;
    public final h3.j b;

    static E0()
    {
        try {
            vn.zalopay.ui.androidsvg.E0.c = android.view.View.getMethod("setLayerType", new Class[] {Integer.TYPE, android.graphics.Paint}));
        } catch (NoSuchMethodException) {
        }
        return;
    }

    public E0(android.content.Context p4, android.util.AttributeSet p5)
    {
        super(p4, p5, 0);
        super.a = 0;
        String v4_6 = new h3.j(20);
        super.b = v4_6;
        if (!super.isInEditMode()) {
            android.content.res.TypedArray v5_1 = super.getContext().getTheme().obtainStyledAttributes(p5, sZ.h.o, 0, 0);
            try {
                boolean v0_1 = v5_1.getString(0);
            } catch (String v4_5) {
                v5_1.recycle();
                throw v4_5;
            }
            if (v0_1) {
                int v1_3 = new vn.zalopay.ui.androidsvg.p(2);
                vn.zalopay.ui.androidsvg.c v2_3 = new vn.zalopay.ui.androidsvg.c(v0_1);
                v2_3.y();
                v4_6.a = v1_3.e(v2_3);
            }
            int v1_4 = v5_1.getResourceId(1, -1);
            if (v1_4 == -1) {
                String v4_3 = v5_1.getString(1);
                if (v4_3 != null) {
                    if (!super.f(android.net.Uri.parse(v4_3))) {
                        if (!super.e(v4_3)) {
                            super.setFromString(v4_3);
                        } else {
                            v5_1.recycle();
                            return;
                        }
                    } else {
                        v5_1.recycle();
                        return;
                    }
                }
                v5_1.recycle();
                return;
            } else {
                super.setImageResource(v1_4);
                v5_1.recycle();
                return;
            }
        } else {
            return;
        }
    }

    private void setFromString(String p4)
    {
        try {
            this.a = new vn.zalopay.ui.androidsvg.M0().f(new java.io.ByteArrayInputStream(p4.getBytes()));
            this.d();
            return;
        } catch (vn.zalopay.ui.androidsvg.SVGParseException) {
            ck.b.e("SVGImageView", new Object[] {androidx.camera.camera2.internal.G.e("Could not find SVG at: ", p4)}));
            return;
        }
    }

    public final void d()
    {
        android.graphics.Picture v0_0 = this.a;
        if (v0_0 != null) {
            int v3_5;
            int v1_4 = v0_0.a;
            if (v1_4 == 0) {
                v3_5 = 0;
            } else {
                v3_5 = v1_4.o;
            }
            android.graphics.Picture v0_1;
            android.view.View v4_3 = this.b;
            if (v4_3 == null) {
                if (v1_4 != 0) {
                    double v5_2 = v1_4.r;
                    if ((v5_2 != 0) && (v5_2.b != 9)) {
                        double v6_1 = v1_4.s;
                        if ((v6_1 != 0) && (v6_1.b != 9)) {
                            v0_1 = v0_0.b(((int) Math.ceil(((double) v5_2.e()))), ((int) Math.ceil(((double) v0_0.a.s.e()))), v4_3);
                            int v1_15 = vn.zalopay.ui.androidsvg.E0.c;
                            if (v1_15 != 0) {
                                try {
                                    v1_15.invoke(this, new Object[] {Integer.valueOf(android.view.View.getField("LAYER_TYPE_SOFTWARE").getInt(new android.view.View(this.getContext()))), 0}));
                                } catch (int v1_0) {
                                    ck.b.e("SVGImageView", new Object[] {"Unexpected failure calling setLayerType", v1_0}));
                                }
                            }
                            this.setImageDrawable(new android.graphics.drawable.PictureDrawable(v0_1));
                            return;
                        }
                    }
                }
                if (v1_4 != 0) {
                    double v5_3 = v1_4.r;
                    if ((v5_3 != 0) && (v3_5 != 0)) {
                        int v1_9 = v5_3.e();
                        v0_1 = v0_0.b(((int) Math.ceil(((double) v1_9))), ((int) Math.ceil(((double) ((v3_5.e * v1_9) / v3_5.d)))), v4_3);
                    }
                }
                if (v1_4 != 0) {
                    int v1_5 = v1_4.s;
                    if ((v1_5 != 0) && (v3_5 != 0)) {
                        int v1_7 = v1_5.e();
                        v0_1 = v0_0.b(((int) Math.ceil(((double) ((v3_5.d * v1_7) / v3_5.e)))), ((int) Math.ceil(((double) v1_7))), v4_3);
                    }
                }
                v0_1 = v0_0.b(512, 512, v4_3);
            } else {
                double v5_0 = ((J0.b) v4_3.b);
                if (v5_0 == 0) {
                } else {
                    v0_1 = v0_0.b(((int) Math.ceil(((double) v5_0.c()))), ((int) Math.ceil(((double) ((J0.b) v4_3.b).d()))), v4_3);
                }
            }
        } else {
            return;
        }
    }

    public final boolean e(String p3)
    {
        try {
            new e8.h(2, this).executeOnExecutor(android.os.AsyncTask.THREAD_POOL_EXECUTOR, new java.io.InputStream[] {this.getContext().getAssets().open(p3)}));
            return 1;
        } catch (java.io.IOException) {
            return 0;
        }
    }

    public final boolean f(android.net.Uri p3)
    {
        try {
            new e8.h(2, this).executeOnExecutor(android.os.AsyncTask.THREAD_POOL_EXECUTOR, new java.io.InputStream[] {this.getContext().getContentResolver().openInputStream(p3)}));
            return 1;
        } catch (java.io.FileNotFoundException) {
            return 0;
        }
    }

    public void setCSS(String p4)
    {
        h3.j v0 = this.b;
        v0.getClass();
        vn.zalopay.ui.androidsvg.p v1_1 = new vn.zalopay.ui.androidsvg.p(2);
        vn.zalopay.ui.androidsvg.c v2_2 = new vn.zalopay.ui.androidsvg.c(p4);
        v2_2.y();
        v0.a = v1_1.e(v2_2);
        this.d();
        return;
    }

    public void setImageAsset(String p2)
    {
        if (!this.e(p2)) {
            ck.b.e("SVGImageView", new Object[] {androidx.camera.camera2.internal.G.e("File not found: ", p2)}));
        }
        return;
    }

    public void setImageResource(int p3)
    {
        Integer[] v3_2 = new Integer[0];
        new vn.zalopay.ui.androidsvg.D0(this, this.getContext(), p3).execute(v3_2);
        return;
    }

    public void setImageResourceInternal(int p1)
    {
        super.setImageResource(p1);
        return;
    }

    public void setImageURI(android.net.Uri p2)
    {
        if (!this.f(p2)) {
            ck.b.e("SVGImageView", new Object[] {androidx.activity.result.e.j(p2, "File not found: ")}));
        }
        return;
    }

    public void setSVG(vn.zalopay.ui.androidsvg.t0 p2)
    {
        if (p2 == null) {
            throw new IllegalArgumentException("Null value passed to setSVG()");
        } else {
            this.a = p2;
            this.d();
            return;
        }
    }
}

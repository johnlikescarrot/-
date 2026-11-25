package vn.zalopay.ui;
public class IconFontTextView extends MZ.c {
    public static final int[] h;
    public static final int[] i;
    public static final int[] j;
    public static final int[] k;

    static IconFontTextView()
    {
        vn.zalopay.ui.IconFontTextView.h = new int[] {4, 5, 3});
        vn.zalopay.ui.IconFontTextView.i = new int[] {7, 8, 6});
        vn.zalopay.ui.IconFontTextView.j = new int[] {10, 11, 9});
        vn.zalopay.ui.IconFontTextView.k = new int[] {1, 2, 0});
        return;
    }

    public IconFontTextView(android.content.Context p11, android.util.AttributeSet p12)
    {
        super(p11, p12, 0);
        if (!super.isInEditMode()) {
            int[] v1_4 = p11.getTheme();
            if (v1_4 != null) {
                int[] v1_0 = v1_4.obtainStyledAttributes(p12, sZ.h.e, 0, 0);
                if (v1_0 != null) {
                    android.graphics.drawable.Drawable v4_2 = new int[] {v1_0.getResourceId(2, 0), v1_0.getResourceId(5, 0), v1_0.getResourceId(3, 0), v1_0.getResourceId(0, 0)});
                    android.graphics.drawable.Drawable v5_2 = super.getCompoundDrawables();
                    if (m9.n.b(p11, v4_2, v5_2)) {
                        super.setCompoundDrawables(v5_2[0], v5_2[1], v5_2[2], v5_2[3]);
                    }
                    android.graphics.drawable.Drawable v4_7 = new int[] {v1_0.getResourceId(4, 0), 0, v1_0.getResourceId(1, 0), 0});
                    android.graphics.drawable.Drawable v5_5 = super.getCompoundDrawablesRelative();
                    if (m9.n.b(p11, v4_7, v5_5)) {
                        super.setCompoundDrawablesRelative(v5_5[0], v5_5[1], v5_5[2], v5_5[3]);
                    }
                    v1_0.recycle();
                }
            }
        }
        if (!super.isInEditMode()) {
            int v6_1 = p11.obtainStyledAttributes(p12, sZ.h.j);
            if (v6_1 != 0) {
                Throwable v11_2 = vn.zalopay.ui.IconFontTextView.h;
                super.p(0, v6_1, v11_2[0], v11_2[1], v11_2[2]);
                Throwable v11_3 = vn.zalopay.ui.IconFontTextView.j;
                super.p(1, v6_1, v11_3[0], v11_3[1], v11_3[2]);
                Throwable v11_4 = vn.zalopay.ui.IconFontTextView.i;
                super.p(2, v6_1, v11_4[0], v11_4[1], v11_4[2]);
                Throwable v11_5 = vn.zalopay.ui.IconFontTextView.k;
                super.p(3, v6_1, v11_5[0], v11_5[1], v11_5[2]);
                v6_1.recycle();
                return;
            }
        }
        return;
    }

    private android.graphics.drawable.Drawable[] getDrawables()
    {
        this.getCompoundDrawables();
        return this.getCompoundDrawablesRelative();
    }

    public final void p(int p2, android.content.res.TypedArray p3, int p4, int p5, int p6)
    {
        String v4_1 = p3.getString(p4);
        if (!android.text.TextUtils.isEmpty(v4_1)) {
            this.q(p2, v4_1, p3.getColor(p6, 0), ((float) p3.getDimensionPixelSize(p5, -1)));
            return;
        } else {
            return;
        }
    }

    public final void q(int p9, String p10, int p11, float p12)
    {
        android.graphics.drawable.Drawable v0_0 = this.getDrawables();
        MZ.b v1_0 = v0_0[p9];
        if (!(v1_0 instanceof MZ.b)) {
            v1_0 = new MZ.b();
            v0_0[p9] = v1_0;
            android.graphics.drawable.Drawable v9_1 = v0_0[0];
            android.graphics.drawable.Drawable v2_0 = v0_0[1];
            android.graphics.drawable.Drawable v7 = v0_0[2];
            android.graphics.drawable.Drawable v0_1 = v0_0[3];
            this.setCompoundDrawables(v9_1, v2_0, v7, v0_1);
            this.setCompoundDrawablesRelative(v9_1, v2_0, v7, v0_1);
        }
        MZ.b v1_1 = ((MZ.b) v1_0);
        if (!android.text.TextUtils.isEmpty(p10)) {
            android.graphics.drawable.Drawable v9_4 = vn.zalopay.ui.util.a.a(p10, "general_icondefault");
            if (p11 != null) {
                v1_1.b = android.content.res.ColorStateList.valueOf(p11);
                v1_1.b(0, 0);
            }
            if (p12 >= 0) {
                v1_1.c = p12;
                v1_1.b(0, 0);
            }
            if (v9_4 == null) {
                v9_4 = "";
            }
            v1_1.a = v9_4;
            v1_1.b(1, 0);
            android.graphics.drawable.Drawable v9_5 = this.getCompoundDrawablesRelative();
            this.setCompoundDrawablesRelative(v9_5[0], v9_5[1], v9_5[2], v9_5[3]);
            return;
        } else {
            return;
        }
    }

    public void setLeftIcon(int p2)
    {
        this.setLeftIcon(this.getContext().getString(p2));
        return;
    }

    public void setLeftIcon(String p3)
    {
        this.q(0, p3, 0, -1082130432);
        return;
    }
}

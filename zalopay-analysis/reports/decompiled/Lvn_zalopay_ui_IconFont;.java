package vn.zalopay.ui;
public class IconFont extends androidx.appcompat.widget.AppCompatCheckedTextView {
    public final boolean e;
    public final int f;
    public final float g;
    public int h;
    public float i;
    public float j;
    public float k;
    public final boolean l;
    public final Ae.s m;
    public final String n;

    public IconFont(android.content.Context p8, android.util.AttributeSet p9)
    {
        super(p8, p9, 0);
        if (!super.isInEditMode()) {
            super.setTypeface(vn.zalopay.ui.util.a.b());
            super.m = new Ae.s(19, super);
        } else {
            super.m = 0;
        }
        if (!super.isInEditMode()) {
            float v1_3 = p8.getTheme();
            if (v1_3 != 0) {
                int[] v5_1 = v1_3.obtainStyledAttributes(p9, sZ.h.h, 0, 0);
                if (v5_1 != null) {
                    try {
                        super.e = v5_1.getBoolean(0, 0);
                        super.f = v5_1.getColor(1, 0);
                        super.g = v5_1.getDimension(2, 0);
                        v5_1.recycle();
                        int[] v5_2 = sZ.h.g;
                        float v1_5 = v1_3.obtainStyledAttributes(p9, v5_2, 0, 0);
                    } catch (Throwable v8_1) {
                        v5_2.recycle();
                        throw v8_1;
                    }
                    if (v1_5 != 0) {
                        try {
                            super.l = v1_5.getBoolean(0, 0);
                            v1_5.getBoolean(4, 0);
                            v1_5.recycle();
                        } catch (Throwable v8_2) {
                            v1_5.recycle();
                            throw v8_2;
                        }
                        super.n = "general_icondefault";
                        if (!super.isInEditMode()) {
                            Throwable v8_3 = p8.obtainStyledAttributes(p9, sZ.h.i);
                            if (v8_3 != null) {
                                String v9_1 = v8_3.getString(1);
                                super.n = v8_3.getString(0);
                                float v1_12 = v8_3.getDimensionPixelSize(2, -1);
                                if (v1_12 >= 0) {
                                    super.setTextSize(0, ((float) v1_12));
                                }
                                v8_3.recycle();
                                super.setIcon(v9_1);
                                return;
                            }
                        }
                        return;
                    }
                }
            }
            super.e = 0;
            super.f = 0;
            super.g = 0;
        }
    }

    public final void dispatchSetPressed(boolean p4)
    {
        super.dispatchSetPressed(p4);
        if (this.e) {
            if (p4 == 0) {
                this.setShadowLayer(this.i, this.j, this.k, this.h);
                return;
            } else {
                this.m.run();
                this.setShadowLayer(this.g, 0, 0, this.f);
                return;
            }
        } else {
            return;
        }
    }

    public final void onDraw(android.graphics.Canvas p4)
    {
        int v1;
        if (!this.l) {
            v1 = 0;
        } else {
            v1 = 1;
            if (this.getLayoutDirection() != 1) {
            }
        }
        if (v1 != 0) {
            p4.save();
            p4.translate(((float) p4.getWidth()), 0);
            p4.scale(-1082130432, 1065353216);
        }
        super.onDraw(p4);
        if (v1 != 0) {
            p4.restore();
        }
        return;
    }

    public final void onRestoreInstanceState(android.os.Parcelable p2)
    {
        super.onRestoreInstanceState(((MZ.d) p2).getSuperState());
        this.setChecked(((MZ.d) p2).a);
        this.requestLayout();
        return;
    }

    public final android.os.Parcelable onSaveInstanceState()
    {
        this.setFreezesText(1);
        MZ.d v0_2 = new MZ.d(super.onSaveInstanceState());
        v0_2.a = this.isChecked();
        return v0_2;
    }

    public final boolean performClick()
    {
        this.toggle();
        return super.performClick();
    }

    public void setIcon(int p2)
    {
        this.setIcon(this.getContext().getString(p2));
        return;
    }

    public void setIcon(String p2)
    {
        boolean v0_0 = this.n;
        if (!v0_0) {
            v0_0 = "general_icondefault";
        }
        String v2_1 = vn.zalopay.ui.util.a.a(p2, v0_0);
        if (android.text.TextUtils.isEmpty(v2_1)) {
            this.setText("");
            return;
        } else {
            this.setText(v2_1);
            return;
        }
    }

    public void setIconColor(int p2)
    {
        super.setTextColor(this.getContext().getColor(p2));
        return;
    }

    public void setIconColor(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            try {
                int v2_1 = android.graphics.Color.parseColor(p2);
            } catch (Exception) {
                v2_1 = -16777216;
            }
            super.setTextColor(v2_1);
            return;
        } else {
            return;
        }
    }
}

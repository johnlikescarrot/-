package vn.zalopay.ui.edittext;
public class DrawableEditText extends com.google.android.material.textfield.TextInputEditText {
    public final int i;
    public final String j;
    public final boolean k;
    public final android.graphics.Rect l;
    public int m;
    public int n;
    public String o;
    public final android.graphics.drawable.Drawable p;
    public GZ.k q;
    public boolean r;
    public boolean s;

    public DrawableEditText(android.content.Context p9, android.util.AttributeSet p10)
    {
        super(p9, p10);
        super.i = 0;
        android.graphics.Rect v1_1 = new android.graphics.Rect();
        super.l = v1_1;
        super.o = "";
        super.s = 0;
        GZ.g v2_0 = new GZ.g(1, super);
        super.i = Math.round(android.util.TypedValue.applyDimension(1, ((float) 35), super.getContext().getResources().getDisplayMetrics()));
        android.content.res.TypedArray v9_1 = p9.obtainStyledAttributes(p10, sZ.h.f);
        boolean v10_3 = super.getResources().getDrawable(v9_1.getResourceId(2, 2131233542));
        super.p = v10_3;
        boolean v10_4 = v10_3.getBounds();
        int v4_2 = super.i;
        v1_1.set((v10_4.left - v4_2), (v10_4.top - v4_2), (v10_4.right + v4_2), (v10_4.bottom + v4_2));
        super.j = v9_1.getString(4);
        super.k = v9_1.getBoolean(1, 0);
        super.r = v9_1.getBoolean(0, 1);
        v9_1.recycle();
        super.addTextChangedListener(v2_0);
        super.setClickable(1);
        super.setEnabled(1);
        return;
    }

    public String getContent()
    {
        String v0_1;
        if (this.getText() == null) {
            v0_1 = "";
        } else {
            v0_1 = this.getText().toString();
        }
        if (!android.text.TextUtils.isEmpty(v0_1)) {
            if (this.k) {
                v0_1 = v0_1.replace(this.j, "");
            }
            return v0_1.trim();
        } else {
            return v0_1;
        }
    }

    public com.google.android.material.textfield.TextInputLayout getInputLayout()
    {
        int v0_0 = 0;
        android.view.View v1_0 = this;
        while (v0_0 < 2) {
            android.view.View v1_2 = v1_0.getParent();
            if (!(v1_2 instanceof com.google.android.material.textfield.TextInputLayout)) {
                v1_0 = ((android.view.View) v1_2);
                v0_0++;
            } else {
                return ((com.google.android.material.textfield.TextInputLayout) v1_2);
            }
        }
        return 0;
    }

    public int getLength()
    {
        int v0_2 = this.getText().toString();
        if (!android.text.TextUtils.isEmpty(v0_2)) {
            return v0_2.length();
        } else {
            return 0;
        }
    }

    public final boolean onTextContextMenuItem(int p3)
    {
        boolean v0 = super.onTextContextMenuItem(p3);
        if (p3 == 16908322) {
            this.setText(this.o);
        }
        return v0;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p4)
    {
        if (p4.getAction() == 0) {
            this.m = ((int) p4.getX());
            boolean v0_8 = ((int) p4.getY());
            this.n = v0_8;
            boolean v0_1 = (v0_8 - 13);
            int v2_1 = (this.getWidth() - (this.m + 13));
            if (v2_1 <= 0) {
                v2_1 += 13;
            }
            if (v0_1) {
                v0_1 = this.n;
            }
            android.graphics.Rect v1_2 = this.l;
            if ((v1_2 != null) && ((v1_2.contains(v2_1, v0_1)) && ((this.r) && (this.s)))) {
                this.getEditableText().clear();
                return 0;
            }
        }
        return super.onTouchEvent(p4);
    }

    public void setEnableDrawableRight(boolean p1)
    {
        this.r = p1;
        this.postInvalidate();
        return;
    }

    public void setFormat(GZ.k p1)
    {
        this.q = p1;
        return;
    }
}

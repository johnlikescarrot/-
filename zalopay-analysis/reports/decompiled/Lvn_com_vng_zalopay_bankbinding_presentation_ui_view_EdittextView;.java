package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class EdittextView extends androidx.appcompat.widget.AppCompatEditText {
    public static final synthetic int A;
    public GZ.k g;
    public boolean h;
    public android.graphics.Rect i;
    public final androidx.lifecycle.T j;
    public final ng.S k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public final androidx.lifecycle.T t;
    public final ji.u0 u;
    public final ji.c0 v;
    public vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity w;
    public boolean x;
    public final ng.S y;
    public final Po.h z;

    public EdittextView(android.content.Context p3, android.util.AttributeSet p4)
    {
        super(p3, p4, 0);
        super.i = new android.graphics.Rect();
        int v4_5 = new androidx.lifecycle.T();
        super.j = v4_5;
        super.k = ng.x.b(new Io.f(8, p3));
        super.r = 1;
        super.t = v4_5;
        Po.h v3_4 = ji.j0.c("");
        super.u = v3_4;
        super.v = ji.j0.h(v3_4);
        super.y = ng.x.b(new Ho.P(10));
        super.z = new Po.h(super);
        return;
    }

    public static synthetic void getBankInfoType$annotations()
    {
        return;
    }

    private final android.graphics.drawable.Drawable getClearButtonDrawable()
    {
        return ((android.graphics.drawable.Drawable) this.k.getValue());
    }

    private final GZ.u getHideVerifiedDataTransformMethod()
    {
        return ((GZ.u) this.y.getValue());
    }

    public final void d()
    {
        this.setBackground(this.getContext().getDrawable(-2079981532));
        this.setHintTextColor(this.getContext().getColor(-2080112635));
        this.setManualFocusLayout(this.x);
        return;
    }

    public final void e()
    {
        this.n = 1;
        this.clearFocus();
        this.setCursorVisible(0);
        this.setFocusableInTouchMode(0);
        this.setFocusable(0);
        return;
    }

    public final void f()
    {
        this.n = 0;
        this.requestFocus();
        this.setCursorVisible(1);
        this.setFocusableInTouchMode(1);
        this.setFocusable(1);
        return;
    }

    public final void g()
    {
        this.setBackground(this.getContext().getDrawable(-2079981529));
        this.setHintTextColor(this.getContext().getColor(-2080112633));
        return;
    }

    public final androidx.lifecycle.N getAfterTextChange()
    {
        return this.t;
    }

    public final int getBankInfoType()
    {
        return this.s;
    }

    public final String getData()
    {
        String v0_3;
        String v0_0 = this.getText();
        if (v0_0 == null) {
            v0_3 = "";
        } else {
            v0_3 = v0_0.toString();
            if (v0_3 == null) {
            }
        }
        String v1 = v0_3;
        String v0_4 = this.s;
        if (v0_4 == 1) {
            v1 = kotlin.text.y.replace$default(v1, " ", "", 0, 4, 0);
        } else {
            if (v0_4 == 2) {
                v1 = kotlin.text.y.replace$default(v1, "/", "", 0, 4, 0);
            }
        }
        return kotlin.text.StringsKt.trim(v1).toString();
    }

    public final boolean getEnableClick()
    {
        return this.r;
    }

    public final ji.f0 getTextChangeStateFlow()
    {
        return this.v;
    }

    public final void h()
    {
        if (!this.h) {
            this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            return;
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.getClearButtonDrawable(), 0);
            this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, this.getClearButtonDrawable(), 0);
            return;
        }
    }

    public final void i()
    {
        this.p = 1;
        android.graphics.drawable.Drawable v0_2 = this.getContext().getDrawable(-2079981495);
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, v0_2, 0);
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, v0_2, 0);
        return;
    }

    public final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        this.postDelayed(new Io.k(8, this), 300);
        return;
    }

    public final void onDetachedFromWindow()
    {
        this.removeTextChangedListener(this.z);
        super.onDetachedFromWindow();
        return;
    }

    public final void onFocusChanged(boolean p1, int p2, android.graphics.Rect p3)
    {
        super.onFocusChanged(p1, p2, p3);
        if (this.l) {
            this.setTransformationMethod(GZ.x.a);
        }
        if (this.m) {
            this.setTransformationMethod(this.getHideVerifiedDataTransformMethod());
        }
        if ((p1 == 0) && (this.q)) {
            try {
                int v1_2 = this.getData();
            } catch (int v1_3) {
                m9.Y3.a("truncateNonEnglishChar exception!", v1_3);
            }
            if (v1_2.length() > 0) {
                int v1_4 = mo.a.a(v1_2);
                this.setText(v1_4);
                this.setSelection(v1_4.length());
            }
        }
        return;
    }

    public final void onRestoreInstanceState(android.os.Parcelable p2)
    {
        if (!(p2 instanceof Po.g)) {
            super.onRestoreInstanceState(p2);
        } else {
            super.onRestoreInstanceState(((Po.g) p2).getSuperState());
            this.h = ((Po.g) p2).a;
            this.r = ((Po.g) p2).b;
            this.l = ((Po.g) p2).c;
            this.x = ((Po.g) p2).d;
            this.s = ((Po.g) p2).i;
            this.w = ((Po.g) p2).j;
            this.m = ((Po.g) p2).k;
            this.n = ((Po.g) p2).e;
            this.o = ((Po.g) p2).f;
            this.p = ((Po.g) p2).g;
            this.q = ((Po.g) p2).h;
            this.j.l(new Xs.b(this.getData()));
            this.u.setValue(this.getData());
        }
        boolean v2_7;
        boolean v2_6 = this.getText();
        if (!v2_6) {
            v2_7 = 0;
        } else {
            v2_7 = v2_6.toString();
        }
        if ((v2_7) && (v2_7.length() != 0)) {
            this.h();
        }
        if (this.l) {
            this.setTransformationMethod(GZ.x.a);
        }
        if (this.m) {
            this.setTransformationMethod(this.getHideVerifiedDataTransformMethod());
        }
        if (!this.n) {
            this.f();
        } else {
            this.e();
        }
        this.setBlurText(this.o);
        if (this.p) {
            this.i();
        }
        return;
    }

    public final android.os.Parcelable onSaveInstanceState()
    {
        Po.g v1_1 = new Po.g(super.onSaveInstanceState());
        v1_1.a = this.h;
        v1_1.b = this.r;
        v1_1.c = this.l;
        v1_1.d = this.x;
        v1_1.i = this.s;
        v1_1.j = this.w;
        v1_1.k = this.m;
        v1_1.e = this.n;
        v1_1.f = this.o;
        v1_1.g = this.p;
        v1_1.h = this.q;
        return v1_1;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p5)
    {
        if ((!p5) || ((p5.getAction() != 0) || ((!this.h) || (!this.r)))) {
            return super.onTouchEvent(p5);
        } else {
            int v1_1 = ((int) p5.getY());
            int v2 = (v1_1 - 70);
            int v3_1 = (this.getWidth() - (((int) p5.getX()) + 70));
            if (v3_1 <= 0) {
                v3_1 += 70;
            }
            if (v2 > 0) {
                v1_1 = v2;
            }
            if (!this.i.contains(v3_1, v1_1)) {
                return super.onTouchEvent(p5);
            } else {
                this.setText("");
                p5.setAction(3);
                return 0;
            }
        }
    }

    public final void setBankInfoType(int p1)
    {
        this.s = p1;
        return;
    }

    public final void setBlurText(boolean p2)
    {
        this.o = p2;
        if (p2 == 0) {
            this.setTextColor(this.getContext().getColor(-2080112634));
            return;
        } else {
            this.setTextColor(this.getContext().getColor(-2080112635));
            return;
        }
    }

    public final void setCompoundDrawables(android.graphics.drawable.Drawable p2, android.graphics.drawable.Drawable p3, android.graphics.drawable.Drawable p4, android.graphics.drawable.Drawable p5)
    {
        if (p4 != null) {
            this.i = p4.getBounds();
        }
        super.setCompoundDrawables(p2, p3, p4, p5);
        return;
    }

    public final void setEnableClick(boolean p1)
    {
        this.r = p1;
        return;
    }

    public final void setHolderNameValue(String p1)
    {
        this.setText(p1);
        this.setSelection(p1.length());
        return;
    }

    public final void setKycValue(vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p3)
    {
        if (this.w != null) {
            String v0_1 = p3.getIdValue();
            int v3_6 = p3.isVerify();
            if (this.w != null) {
                this.m = v3_6;
                this.setText(v0_1);
                if (!this.m) {
                    this.setTransformationMethod(0);
                    this.f();
                    this.setBlurText(0);
                    this.setSelection(v0_1.length());
                } else {
                    this.e();
                    this.setBlurText(1);
                    return;
                }
            }
        }
        return;
    }

    public final void setManualFocusLayout(boolean p3)
    {
        this.x = p3;
        this.setBackground(this.getContext().getDrawable(-2079981532));
        if (this.s == 2) {
            android.graphics.drawable.Drawable v3_2;
            if (p3 == null) {
                v3_2 = this.getContext().getDrawable(-2079981528);
            } else {
                v3_2 = this.getContext().getDrawable(-2079981531);
            }
            this.setBackground(v3_2);
            return;
        } else {
            return;
        }
    }

    public final void setUpCvv(int p4)
    {
        this.l = 1;
        this.setHint(this.getContext().getString(-2079719361));
        this.setInputType(2);
        this.h = 1;
        com.google.android.gms.internal.mlkit_vision_barcode.V7.d(this, p4);
        return;
    }
}

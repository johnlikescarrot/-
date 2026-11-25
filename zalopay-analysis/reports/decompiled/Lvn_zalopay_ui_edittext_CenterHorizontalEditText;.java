package vn.zalopay.ui.edittext;
public class CenterHorizontalEditText extends android.widget.RelativeLayout implements androidx.lifecycle.E {
    public final android.content.Context a;
    public final com.google.android.material.textfield.TextInputLayout b;
    public final com.google.android.material.textfield.TextInputEditText c;
    public final android.widget.ImageView d;
    public final android.widget.ImageView e;
    public final android.widget.TextView f;
    public GZ.o g;
    public GZ.a h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public final String n;
    public final boolean o;
    public final boolean p;
    public boolean q;
    public boolean r;
    public boolean s;

    public CenterHorizontalEditText(android.content.Context p4, android.util.AttributeSet p5)
    {
        super(p4, p5, 0);
        super.s = 0;
        super.a = p4;
        if ((p4 instanceof androidx.lifecycle.F)) {
            ((androidx.lifecycle.F) p4).getLifecycle().a(super);
        }
        if ((p5 != null) && (!super.isInEditMode())) {
            GZ.g v5_7 = p4.getTheme().obtainStyledAttributes(p5, sZ.h.d, 0, 0);
            com.google.android.material.textfield.TextInputEditText v4_6 = ((vn.zalopay.ui.edittext.CenterHorizontalEditText) ((android.view.LayoutInflater) p4.getSystemService(wmoi.igob.ggcn(12855))).inflate(2131558730, super));
            super.b = ((com.google.android.material.textfield.TextInputLayout) v4_6.findViewById(2131362787));
            super.c = ((com.google.android.material.textfield.TextInputEditText) v4_6.findViewById(2131362463));
            super.d = ((android.widget.ImageView) v4_6.findViewById(2131363079));
            super.e = ((android.widget.ImageView) v4_6.findViewById(2131363139));
            super.f = ((android.widget.TextView) v4_6.findViewById(2131362643));
            super.i = v5_7.getString(3);
            super.j = v5_7.getString(1);
            super.k = v5_7.getString(2);
            super.n = v5_7.getString(6);
            GZ.d v1_31 = v5_7.getInt(5, 100);
            super.o = v5_7.getBoolean(0, 0);
            super.p = v5_7.getBoolean(4, 1);
            super.b.setHint(super.i);
            GZ.d v1_32 = new android.text.InputFilter[1];
            v1_32[0] = new android.text.InputFilter$LengthFilter(v1_31);
            super.c.setFilters(v1_32);
            super.b.getViewTreeObserver().addOnPreDrawListener(new GZ.e(0, super));
            super.c.setOnFocusChangeListener(new GZ.c(super, 0));
            super.d.setOnClickListener(new GZ.d(super, 0));
            super.e.setOnClickListener(new GZ.d(super, 1));
            super.c.addOnAttachStateChangeListener(new GZ.f(super));
            super.c.addTextChangedListener(new GZ.g(0, super));
        }
        return;
    }

    public static void a(vn.zalopay.ui.edittext.CenterHorizontalEditText p4, boolean p5)
    {
        p4.c(Boolean.valueOf(p4.c.hasFocus()), Boolean.valueOf((android.text.TextUtils.isEmpty(p4.c.getText()) ^ 1)), Boolean.TRUE);
        if ((p5 != null) && (!p4.s)) {
            String v0_5 = ((android.view.inputmethod.InputMethodManager) p4.a.getSystemService(wmoi.igob.ggcn(12856)));
            if (v0_5 != null) {
                v0_5.showSoftInput(p4.c, 1);
            }
        }
        if (!p4.o) {
            if (p5 == null) {
                p4.d.setVisibility(8);
                return;
            } else {
                p4.b();
                return;
            }
        } else {
            if (p5 == null) {
                p4.d.setVisibility(8);
                if (!android.text.TextUtils.isEmpty(p4.c.getText())) {
                    p4.r = 1;
                    com.google.android.material.textfield.TextInputLayout v5_5 = p4.h;
                    if (v5_5 != null) {
                        v5_5.a();
                    }
                    if (p4.r) {
                        p4.b.setHint(p4.k);
                        p4.f.setVisibility(4);
                        return;
                    } else {
                        p4.b.setHint("");
                        p4.f.setText(p4.l);
                        p4.f.setVisibility(0);
                        p4.e.setVisibility(8);
                        return;
                    }
                } else {
                    p4.b.setHint(p4.i);
                    p4.f.setText(p4.j);
                    p4.f.setVisibility(0);
                    p4.c.setPadding(0, (p4.getTextInputLayoutTopSpace() / 2), 0, (p4.getTextInputLayoutTopSpace() / 2));
                    return;
                }
            } else {
                p4.b.setHint(p4.k);
                p4.f.setVisibility(4);
                p4.b();
                return;
            }
        }
    }

    private int getTextInputLayoutTopSpace()
    {
        android.view.View v0_0 = this.c;
        int v1 = 0;
        do {
            v1 += v0_0.getTop();
            v0_0 = ((android.view.View) v0_0.getParent());
        } while(v0_0.getId() != this.b.getId());
        return v1;
    }

    public final void b()
    {
        this.e.setVisibility(8);
        if ((this.p) && (this.c.hasFocus())) {
            if (android.text.TextUtils.isEmpty(this.c.getText())) {
                this.d.setVisibility(8);
            } else {
                this.d.setVisibility(0);
                return;
            }
        }
        return;
    }

    public final void c(Boolean p2, Boolean p3, Boolean p4)
    {
        if (p4.booleanValue()) {
            androidx.transition.J.a(this.b, 0);
        }
        if ((!p2.booleanValue()) && (!p3.booleanValue())) {
            this.c.setPadding(0, 0, 0, this.getTextInputLayoutTopSpace());
            return;
        } else {
            this.c.setPadding(0, (this.getTextInputLayoutTopSpace() / 2), 0, (this.getTextInputLayoutTopSpace() / 2));
            return;
        }
    }

    public String getErrorEmpty()
    {
        String v0_0 = this.j;
        if (v0_0 == null) {
            return "";
        } else {
            return v0_0;
        }
    }

    public String getStringText()
    {
        if (android.text.TextUtils.isEmpty(this.m)) {
            return this.c.getText().toString();
        } else {
            return this.m;
        }
    }

    public android.text.Editable getText()
    {
        return this.c.getText();
    }

    public android.widget.EditText getTextInputEditText()
    {
        return this.c;
    }

    public android.widget.TextView getTvError()
    {
        return this.f;
    }

    public String getValidationError()
    {
        String v0_0 = this.l;
        if (v0_0 == null) {
            return "";
        } else {
            return v0_0;
        }
    }

    public void onDestroy()
    {
        this.g = 0;
        this.h = 0;
        return;
    }

    public void setAfterValidateListener(GZ.a p1)
    {
        this.h = p1;
        return;
    }

    public void setAlwaysHideKeyboard(boolean p1)
    {
        this.s = p1;
        return;
    }

    public void setEnabledCustom(boolean p2)
    {
        this.c.setEnabled(p2);
        if (p2 != null) {
            this.b.setHintTextAppearance(2132017453);
            return;
        } else {
            this.b.setHintTextAppearance(2132017454);
            this.d.setVisibility(8);
            return;
        }
    }

    public void setError(CharSequence p1)
    {
        String v1_1;
        if (p1 != null) {
            v1_1 = p1.toString();
        } else {
            v1_1 = this.i;
        }
        this.l = v1_1;
        return;
    }

    public void setErrorEmpty(String p2)
    {
        this.j = p2;
        this.f.setText(p2);
        return;
    }

    public void setFloatingLabel(String p1)
    {
        this.k = p1;
        return;
    }

    public void setHintText(String p2)
    {
        this.i = p2;
        this.b.setHint(p2);
        return;
    }

    public void setInputType(int p2)
    {
        this.c.setInputType(p2);
        return;
    }

    public void setRightIconListener(GZ.o p1)
    {
        this.g = p1;
        return;
    }

    public void setShowTyping(boolean p1)
    {
        this.q = p1;
        return;
    }

    public void setText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(this.m)) {
            this.m = "";
        }
        this.c.setText(p2);
        return;
    }
}

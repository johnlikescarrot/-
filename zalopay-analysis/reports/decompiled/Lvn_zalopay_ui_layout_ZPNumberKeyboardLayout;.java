package vn.zalopay.ui.layout;
public class ZPNumberKeyboardLayout extends OZ.c implements NZ.a {
    public static final synthetic int Q;
    public final NZ.f L;
    public final java.util.ArrayList M;
    public boolean N;
    public boolean O;
    public vn.zalopay.ui.edittext.AmountEditText P;

    public ZPNumberKeyboardLayout(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        super.M = new java.util.ArrayList();
        super.O = 1;
        if (!super.isInEditMode()) {
            super.N = 1;
            if ((super.getContext() instanceof android.app.Activity)) {
                OZ.l v0_1 = new NZ.f(((android.app.Activity) super.getContext()), super, super);
                super.L = v0_1;
                v0_1.setHeight(-2);
                super.L.setWidth(-2);
                super.L.setAnimationStyle(2132017655);
                super.L.setOnDismissListener(new OZ.l(super));
            }
            super.setFocusableInTouchMode(1);
            super.setDescendantFocusability(131072);
            return;
        } else {
            return;
        }
    }

    public static void B(android.view.View p2)
    {
        try {
            android.view.inputmethod.InputMethodManager v0_2 = ((android.view.inputmethod.InputMethodManager) p2.getContext().getSystemService("input_method"));
        } catch (android.os.IBinder v2_2) {
            v2_2.getMessage();
            return;
        }
        if ((v0_2 != null) && (v0_2.isActive())) {
            v0_2.hideSoftInputFromWindow(p2.getWindowToken(), 0);
        }
        return;
    }

    public final void a()
    {
        if (this.N) {
            this.setVisibleCustomKeypad(0);
        }
        this.postDelayed(new E.b(this, this.L.a.f, 1), 100);
        return;
    }

    public final void b()
    {
        if (this.N) {
            this.setVisibleCustomKeypad(0);
            android.view.View v0_4 = ((android.app.Activity) this.getContext()).getCurrentFocus();
            if ((v0_4 instanceof android.widget.EditText)) {
                v0_4.clearFocus();
            }
        }
        return;
    }

    public void setDoneButtonEnable(boolean p2)
    {
        vn.zalopay.ui.keyboard.NumberKeyboard v0_0 = this.L;
        if (v0_0 != null) {
            v0_0.a.setDoneButtonEnable(p2);
        }
        return;
    }

    public void setEditTextHolder(vn.zalopay.ui.edittext.AmountEditText p2)
    {
        int v2_4;
        this.P = p2;
        this.L.a.setEditTextHolder(p2);
        p2.setChangeTextListener(new OZ.j(this));
        if ((p2.E != null) || ((!p2.O) || (android.text.TextUtils.isEmpty(p2.getText().toString())))) {
            v2_4 = 0;
        } else {
            v2_4 = 1;
        }
        vn.zalopay.ui.keyboard.NumberKeyboard v0_2 = this.L;
        if (v0_2 != null) {
            v0_2.a.c(v2_4);
        }
        return;
    }

    public void setEnableHideKeyboard(boolean p2)
    {
        this.N = p2;
        vn.zalopay.ui.keyboard.NumberKeyboard v0_1 = this.L.a;
        if (v0_1 != null) {
            v0_1.setShowHideButton(p2);
            return;
        } else {
            return;
        }
    }

    public void setEnableScrollLayoutToBottom(boolean p1)
    {
        this.O = p1;
        return;
    }

    public void setKeyText(String p2)
    {
        vn.zalopay.ui.edittext.AmountEditText v0 = this.P;
        if (v0 != null) {
            v0.setText(p2);
        }
        return;
    }

    public void setParentHeight(int p3)
    {
        android.view.ViewGroup v0_1 = ((android.view.ViewGroup) this.getParent());
        android.view.ViewGroup$LayoutParams v1 = v0_1.getLayoutParams();
        v1.height = p3;
        v0_1.setLayoutParams(v1);
        return;
    }

    public void setSuggestedKeysRule(java.util.List p2)
    {
        this.L.a.setSuggestedKeysRule(p2);
        return;
    }

    public void setVisibleCustomKeypad(boolean p2)
    {
        int v0_0 = this.L;
        if ((v0_0 != 0) && (v0_0.isShowing() != p2)) {
            if (p2 != null) {
                this.L.d = new OZ.j(this);
                vn.zalopay.ui.layout.ZPNumberKeyboardLayout.B(this);
                this.post(new OZ.k(this, 0));
            } else {
                this.L.dismiss();
                return;
            }
        }
        return;
    }
}

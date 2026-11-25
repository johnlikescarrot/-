package vn.zalopay.ui.keyboard;
public class NumberKeyboard extends NZ.b {
    public boolean f;
    public vn.zalopay.ui.keyboard.NumberKeyboard$a g;
    public java.util.List h;

    public NumberKeyboard(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    private android.view.View getViewDoneButton()
    {
        int v0_1 = this.b.iterator();
        while (v0_1.hasNext()) {
            android.view.View v1_0 = ((android.view.View) v0_1.next());
            if (v1_0.getId() == 2131362208) {
                return v1_0;
            }
        }
        return 0;
    }

    private void setSuggestedAmountList(java.util.List p1)
    {
        com.google.android.gms.internal.mlkit_vision_barcode.p0.b(p1);
        return;
    }

    public void a(android.content.Context p3)
    {
        this.d(android.view.LayoutInflater.from(p3).inflate(2131560377, this, 1));
        android.content.res.Resources v3_2 = this.getContext().getResources();
        v3_2.getDimensionPixelOffset(2131166276);
        v3_2.getDimensionPixelSize(2131165567);
        return;
    }

    public void b()
    {
        android.util.SparseArray v2 = this.c;
        v2.put(2131362194, "1");
        v2.put(2131362195, "2");
        v2.put(2131362196, "3");
        v2.put(2131362197, "4");
        v2.put(2131362198, "5");
        v2.put(2131362199, "6");
        v2.put(2131362200, "7");
        v2.put(2131362201, "8");
        v2.put(2131362202, "9");
        v2.put(2131362192, "0");
        v2.put(2131362193, "000");
        return;
    }

    public final void c(boolean p3)
    {
        android.view.View v0 = this.getViewDoneButton();
        if (v0 != null) {
            if (p3 == 0) {
                v0.setBackgroundColor(this.getContext().getResources().getColor(2131099906));
                return;
            } else {
                v0.setBackgroundColor(this.getContext().getResources().getColor(2131099710));
                return;
            }
        } else {
            return;
        }
    }

    public void d(android.view.View p3)
    {
        this.b.add(p3.findViewById(2131362194));
        this.b.add(p3.findViewById(2131362195));
        this.b.add(p3.findViewById(2131362196));
        this.b.add(p3.findViewById(2131362197));
        this.b.add(p3.findViewById(2131362198));
        this.b.add(p3.findViewById(2131362199));
        this.b.add(p3.findViewById(2131362200));
        this.b.add(p3.findViewById(2131362201));
        this.b.add(p3.findViewById(2131362202));
        this.b.add(p3.findViewById(2131362192));
        this.b.add(p3.findViewById(2131362193));
        this.b.add(p3.findViewById(2131362208));
        this.b.add(p3.findViewById(2131362209));
        this.b.add(p3.findViewById(2131362205));
        return;
    }

    public final void e()
    {
        android.widget.TextView v0_1 = ((android.view.ViewGroup) this.getViewDoneButton());
        if (v0_1 != null) {
            String v1_0 = 0;
            android.widget.TextView v0_3 = ((android.widget.TextView) v0_1.getChildAt(0));
            android.content.Context v2 = this.getContext();
            if ((v0_3 != null) && (v2 != null)) {
                if (this.e == 6) {
                    v1_0 = 1;
                }
                this.f = v1_0;
                if (v1_0 == null) {
                    v0_3.setText(v2.getString(2131952080));
                } else {
                    v0_3.setText(v2.getString(2131952078));
                    return;
                }
            }
        }
        return;
    }

    public final void onClick(android.view.View p4)
    {
        if (p4.getId() != 2131362208) {
            if (this.g != null) {
                if (p4.getId() != 2131362208) {
                    if (p4.getId() == 2131362209) {
                        this.g.b();
                    }
                } else {
                    this.g.a();
                }
            }
            super.onClick(p4);
            if ((p4.getId() == 2131362208) && ((!this.f) && ((this.getContext() instanceof android.app.Activity)))) {
                android.view.inputmethod.InputMethodManager v4_6 = ((android.app.Activity) this.getContext());
                android.view.View v0_5 = v4_6.getCurrentFocus();
                if (v0_5 != null) {
                    android.view.View v0_6 = v0_5.focusSearch(130);
                    if (v0_6 != null) {
                        v0_6.requestFocus();
                        if ((v0_6 instanceof android.widget.EditText)) {
                            android.view.inputmethod.InputMethodManager v4_8 = ((android.view.inputmethod.InputMethodManager) v4_6.getSystemService("input_method"));
                            if (v4_8 != null) {
                                v4_8.showSoftInput(v0_6, 1);
                            }
                        }
                    }
                }
            }
        } else {
            android.view.View v0_7 = this.a;
            if (v0_7 != null) {
                v0_7.p();
            }
        }
        return;
    }

    public void setActionListener(vn.zalopay.ui.keyboard.NumberKeyboard$a p1)
    {
        this.g = p1;
        return;
    }

    public void setDoneButtonEnable(boolean p2)
    {
        android.view.View v0 = this.getViewDoneButton();
        if (v0 != null) {
            v0.setEnabled(p2);
        }
        return;
    }

    public void setEditTextHolder(vn.zalopay.ui.edittext.ZPEditText p1)
    {
        super.setEditTextHolder(p1);
        this.e();
        return;
    }

    public void setShowHideButton(boolean p3)
    {
        int v1;
        android.view.View vtmp1 = this.findViewById(2131363588);
        if (!p3) {
            v1 = 0;
        } else {
            v1 = 8;
        }
        vtmp1.setVisibility(v1);
        ((vn.zalopay.ui.password.view.KeyboardButtonView) this.findViewById(2131362209)).setEnableRipple(p3);
        return;
    }

    public void setSuggestedKeysRule(java.util.List p1)
    {
        this.h = p1;
        return;
    }
}

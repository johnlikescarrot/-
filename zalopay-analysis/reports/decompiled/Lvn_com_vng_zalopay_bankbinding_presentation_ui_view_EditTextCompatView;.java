package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class EditTextCompatView extends android.widget.LinearLayout {
    public static final synthetic int j;
    public final Sn.u a;
    public boolean b;
    public vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity c;
    public final androidx.lifecycle.T d;
    public final androidx.lifecycle.T e;
    public final String f;
    public final String g;
    public final boolean h;
    public final boolean i;

    public EditTextCompatView(android.content.Context p13, android.util.AttributeSet p14)
    {
        super(p13, p14, 0);
        int v1_1 = new androidx.lifecycle.T();
        super.d = v1_1;
        super.e = v1_1;
        super.h = 1;
        vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView v13_3 = android.view.LayoutInflater.from(p13).inflate(-2079850459, super, 0);
        super.addView(v13_3);
        int[] v2_1 = -2079915910;
        vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView v6_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView) l5.s.a(-2079915910, v13_3));
        if (v6_1 != null) {
            android.widget.LinearLayout v5_1 = ((android.widget.LinearLayout) v13_3);
            v2_1 = -2079915807;
            androidx.appcompat.widget.AppCompatTextView v8_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915807, v13_3));
            if (v8_1 != null) {
                v2_1 = -2079915801;
                androidx.appcompat.widget.AppCompatTextView v9_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915801, v13_3));
                if (v9_1 != null) {
                    v2_1 = -2079915794;
                    androidx.appcompat.widget.AppCompatTextView v10_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915794, v13_3));
                    if (v10_1 != null) {
                        v2_1 = -2079915787;
                        android.widget.TextView v11_1 = ((android.widget.TextView) l5.s.a(-2079915787, v13_3));
                        if (v11_1 != null) {
                            super.a = new Sn.u(v5_1, v6_1, v5_1, v8_1, v9_1, v10_1, v11_1);
                            if (p14) {
                                vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView v13_8 = super.getContext().obtainStyledAttributes(p14, Cn.a.a, 0, 0);
                                super.f = v13_8.getString(3);
                                super.g = v13_8.getString(2);
                                super.h = v13_8.getBoolean(1, 1);
                                super.i = v13_8.getBoolean(0, 0);
                                v13_8.recycle();
                            }
                            vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView v13_9 = super.f;
                            if ((v13_9 == null) || (v13_9.length() == 0)) {
                                v13_9 = 0;
                            }
                            if (v13_9 != null) {
                                super.getBinding().f.setText(v13_9);
                            }
                            super.j(super.g);
                            if (!super.h) {
                                super.getBinding().b.e();
                            }
                            super.getBinding().b.setBlurText(super.i);
                            return;
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v13_3.getResources().getResourceName(v2_1)));
    }

    private final Sn.u getBinding()
    {
        IllegalStateException v0_0 = this.a;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    private final void setupCardInput(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel p7)
    {
        androidx.appcompat.widget.AppCompatTextView v0_1 = this.getBinding().b;
        String v1_4 = p7.getMaxLength();
        v0_1.setHint(p7.getPlaceHolder());
        v0_1.s = 1;
        v0_1.setInputType(2);
        v0_1.setImeOptions((v0_1.getImeOptions() | 268435462));
        v0_1.g = new to.a(v1_4);
        v0_1.setBackground(v0_1.getContext().getDrawable(-2079981532));
        String v1_2 = v0_1.getLayoutParams();
        v1_2.height = v0_1.getResources().getDimensionPixelSize(-2080047103);
        v0_1.setLayoutParams(v1_2);
        v0_1.setTextSize(2, 1102053376);
        v0_1.h = 1;
        this.getBinding().f.setText(p7.getLabel());
        this.j(p7.getHint());
        this.h();
        return;
    }

    private final void setupCvv(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel p3)
    {
        this.getBinding().b.setUpCvv(p3.getLength());
        this.getBinding().f.setText(this.getContext().getString(-2079719363));
        this.getBinding().e.setText(this.getContext().getString(-2079719364));
        this.getBinding().b.setOnFocusChangeListener(new Po.c(this));
        return;
    }

    private final void setupHolderName(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel p8)
    {
        Po.c v0_1 = this.getBinding().b;
        String v1_0 = p8.getMaxLength();
        int v2_9 = p8.getFunctionId();
        String v3_2 = p8.getPlaceholder();
        if (v3_2.length() == 0) {
            if ((v2_9 != 2) && (v2_9 != 5)) {
                int v2_1 = v0_1.getContext().getString(-2079719368);
            } else {
                v2_1 = v0_1.getContext().getString(-2079719423);
            }
            v3_2 = v2_1;
        }
        v0_1.setHint(v3_2);
        v0_1.setInputType(96);
        v0_1.setImeOptions(-2147483648);
        v0_1.setPrivateImeOptions("nm");
        v0_1.h = 1;
        v0_1.g = new w5.c();
        v0_1.q = 1;
        com.google.android.gms.internal.mlkit_vision_barcode.V7.d(v0_1, v1_0);
        Po.c v0_3 = this.getBinding().f;
        String v1_1 = p8.getLabel();
        if (v1_1.length() == 0) {
            String v1_2 = p8.getFunctionId();
            if ((v1_2 != 2) && (v1_2 != 5)) {
                v1_1 = this.getContext().getString(-2079719320);
            } else {
                v1_1 = this.getContext().getString(-2079719322);
            }
        }
        v0_3.setText(v1_1);
        Po.c v0_5 = this.getBinding().e;
        String v1_5 = p8.getHint();
        if (v1_5.length() == 0) {
            String v8_3;
            String v8_1 = p8.getFunctionId();
            if ((v8_1 != 2) && (v8_1 != 5)) {
                v8_3 = this.getContext().getString(-2079719321);
            } else {
                v8_3 = this.getContext().getString(-2079719323);
            }
            v1_5 = v8_3;
        }
        v0_5.setText(v1_5);
        this.getBinding().e.setVisibility(0);
        this.getBinding().b.setOnFocusChangeListener(new Po.c(this));
        return;
    }

    private final void setupIdentity(int p5)
    {
        String v5_2;
        String v0_3 = this.getBinding().b;
        v0_3.w = 0;
        v0_3.s = 3;
        com.google.android.gms.internal.mlkit_vision_barcode.V7.d(v0_3, 12);
        v0_3.setHint(v0_3.getContext().getString(-2079719404));
        v0_3.e();
        this.getBinding().f.setText(this.getContext().getString(-2079719403));
        String v0_5 = this.getBinding().e;
        if ((p5 != 3) && ((p5 != 4) && (p5 != 1))) {
            v5_2 = this.getContext().getString(-2079719406);
        } else {
            v5_2 = this.getContext().getString(-2079719405);
        }
        v0_5.setText(v5_2);
        this.getBinding().b.setOnFocusChangeListener(new Po.c(this));
        this.getBinding().g.setText(this.getContext().getString(-2079719380));
        com.google.android.gms.internal.mlkit_vision_barcode.W7.d(this.getBinding().g, 1);
        return;
    }

    public final void a()
    {
        boolean v0_1;
        this.b = 0;
        boolean v0_3 = this.getBinding().b;
        if (v0_3.s != 2) {
            v0_1 = v0_3.hasFocus();
        } else {
            v0_1 = v0_3.x;
        }
        if (!v0_1) {
            this.i();
            return;
        } else {
            this.h();
            return;
        }
    }

    public final void b()
    {
        if (this.getData().length() != 0) {
            this.i();
            return;
        } else {
            this.d.l(new Xs.b(Boolean.FALSE));
            com.google.android.gms.internal.mlkit_vision_barcode.W7.c(this.getBinding().d);
            this.getBinding().e.setVisibility(0);
            this.getBinding().f.setTextColor(this.getContext().getColor(-2080112634));
            this.getBinding().e.setTextColor(this.getContext().getColor(-2080112634));
            this.getBinding().b.g();
            return;
        }
    }

    public final void c()
    {
        this.getBinding().e.setTextColor(this.getContext().getColor(-2080112634));
        this.getBinding().b.d();
        return;
    }

    public final void d(vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity p3)
    {
        this.c = p3;
        int v0_3 = this.getBinding().b;
        v0_3.w = p3;
        if (p3 != null) {
            if (p3.getMaxLength() > 0) {
                com.google.android.gms.internal.mlkit_vision_barcode.V7.d(v0_3, p3.getMaxLength());
            }
            int v1_2;
            if (p3.getCharType() != 0) {
                v1_2 = 4096;
            } else {
                v1_2 = 2;
            }
            v0_3.setInputType(v1_2);
            v0_3.f();
            v0_3.requestFocus();
        } else {
            v0_3.setText("");
            v0_3.e();
        }
        if (p3 != null) {
            this.getBinding().g.setText(p3.getName());
            this.getBinding().g.setTextColor(this.getContext().getColor(-2080112634));
            return;
        } else {
            this.getBinding().g.setText(this.getContext().getString(-2079719380));
            this.getBinding().g.setTextColor(this.getContext().getColor(-2080112635));
            return;
        }
    }

    public final void e()
    {
        this.getBinding().e.setTextColor(this.getContext().getColor(-2080112633));
        this.getBinding().b.g();
        return;
    }

    public final void f(uo.c p5, int p6)
    {
        if (!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel)) {
            if (!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel)) {
                if (!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel)) {
                    if (!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel)) {
                        if (!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel)) {
                            if (!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IdentityModel)) {
                                if ((!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Email)) && ((!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Address)) && (!(p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$Term)))) {
                                    throw new kotlin.NoWhenBranchMatchedException();
                                } else {
                                    return;
                                }
                            } else {
                                this.setupIdentity(p6);
                                return;
                            }
                        } else {
                            this.setupCvv(((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CvvModel) p5));
                            return;
                        }
                    } else {
                        this.getBinding().c.setFocusableInTouchMode(1);
                        kotlin.NoWhenBranchMatchedException v5_11 = this.getBinding().b;
                        v5_11.s = 2;
                        v5_11.setHint(v5_11.getContext().getString(-2079719333));
                        v5_11.e();
                        v5_11.setHintTextColor(v5_11.getContext().getColor(-2080112635));
                        v5_11.i();
                        this.getBinding().f.setText(this.getContext().getString(-2079719334));
                        this.getBinding().e.setText(this.getContext().getString(-2079719335));
                        return;
                    }
                } else {
                    this.setupHolderName(((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5));
                    return;
                }
            } else {
                this.getBinding().c.setFocusableInTouchMode(1);
                kotlin.NoWhenBranchMatchedException v5_20 = this.getBinding().b;
                v5_20.s = 2;
                v5_20.setHint(v5_20.getContext().getString(-2079719315));
                v5_20.e();
                v5_20.setHintTextColor(v5_20.getContext().getColor(-2080112635));
                v5_20.i();
                this.getBinding().f.setText(this.getContext().getString(-2079719316));
                this.getBinding().e.setText(this.getContext().getString(-2079719317));
                return;
            }
        } else {
            this.setupCardInput(((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel) p5));
            return;
        }
    }

    public final void g()
    {
        this.getBinding().b.setSelection(this.getBinding().b.length());
        return;
    }

    public final androidx.lifecycle.N getAfterTextChangeLiveData()
    {
        return this.getBinding().b.getAfterTextChange();
    }

    public final android.widget.TextView getButtonSelectType()
    {
        return this.getBinding().g;
    }

    public final String getData()
    {
        return this.getBinding().b.getData();
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.ui.view.EdittextView getEditText()
    {
        return this.getBinding().b;
    }

    public final androidx.lifecycle.N getOnFocusChange()
    {
        return this.e;
    }

    public final ji.f0 getTextChangeStateFlow()
    {
        return this.getBinding().b.getTextChangeStateFlow();
    }

    public final void h()
    {
        this.d.l(new Xs.b(Boolean.TRUE));
        if (!this.b) {
            com.google.android.gms.internal.mlkit_vision_barcode.W7.c(this.getBinding().d);
            this.getBinding().e.setVisibility(0);
            this.getBinding().f.setTextColor(this.getContext().getColor(2131100627));
            this.getBinding().e.setTextColor(this.getContext().getColor(2131100627));
            this.getBinding().b.d();
            this.getBinding().b.setManualFocusLayout(1);
            return;
        } else {
            return;
        }
    }

    public final void i()
    {
        this.d.l(new Xs.b(Boolean.FALSE));
        if (!this.b) {
            com.google.android.gms.internal.mlkit_vision_barcode.W7.c(this.getBinding().d);
            this.getBinding().e.setVisibility(0);
            this.getBinding().f.setTextColor(this.getContext().getColor(-2080112634));
            this.getBinding().e.setTextColor(this.getContext().getColor(-2080112634));
            this.getBinding().b.d();
            this.getBinding().b.setManualFocusLayout(0);
            return;
        } else {
            return;
        }
    }

    public final void j(String p2)
    {
        if ((p2 != null) && (p2.length() != 0)) {
            this.getBinding().e.setText(p2);
            this.getBinding().e.setVisibility(0);
            return;
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.W7.c(this.getBinding().e);
            return;
        }
    }

    public final void onRestoreInstanceState(android.os.Parcelable p2)
    {
        if (!(p2 instanceof Po.e)) {
            super.onRestoreInstanceState(p2);
            return;
        } else {
            super.onRestoreInstanceState(((Po.e) p2).getSuperState());
            this.b = ((Po.e) p2).a;
            vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity v2_1 = ((Po.e) p2).b;
            this.c = v2_1;
            if (v2_1 != null) {
                this.d(v2_1);
            }
            return;
        }
    }

    public final android.os.Parcelable onSaveInstanceState()
    {
        Po.e v1_1 = new Po.e(super.onSaveInstanceState());
        v1_1.a = this.b;
        v1_1.b = this.c;
        return v1_1;
    }

    public final void setEnable(boolean p2)
    {
        this.getBinding().b.setEnabled(p2);
        this.getBinding().b.setEnableClick(p2);
        if (p2 == null) {
            this.getBinding().b.clearFocus();
        }
        return;
    }

    public final void setErrorLayout(String p3)
    {
        this.b = 1;
        this.getBinding().d.setText(p3);
        this.getBinding().f.setTextColor(this.getContext().getColor(-2080112634));
        com.google.android.gms.internal.mlkit_vision_barcode.W7.c(this.getBinding().e);
        this.getBinding().d.setVisibility(0);
        this.getBinding().b.g();
        return;
    }

    public final void setKycValue(vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p2)
    {
        this.getBinding().b.setKycValue(p2);
        return;
    }

    public final void setupPhoneNumber(int p3)
    {
        if ((p3 != 2) && (p3 != 5)) {
            this.getBinding().e.setText(this.getContext().getString(-2079719297));
            return;
        } else {
            this.getBinding().e.setText(this.getContext().getString(-2079719298));
            return;
        }
    }
}

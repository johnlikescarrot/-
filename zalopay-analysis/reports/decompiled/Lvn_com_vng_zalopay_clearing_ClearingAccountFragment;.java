package vn.com.vng.zalopay.clearing;
public class ClearingAccountFragment extends Ts.d implements Wr.h, Wr.g {
    android.widget.ViewFlipper mFlipperView;
    vn.com.vng.zalopay.passport.widget.OnboardingEditTextView mInputOTPView;
    vn.com.vng.zalopay.passport.widget.OnboardingPasswordRoundView mInputPinView;
    vn.com.vng.zalopay.passport.widget.CustomKeyboardView mKeyboardView;
    android.widget.TextView mMgsCustomerService;
    android.widget.TextView mMgsCustomerServicePhone;
    android.widget.TextView mMgsError;
    vn.com.vng.zalopay.passport.widget.OnboardingEditTextView mPhoneView;
    android.widget.ScrollView mScrollView;
    public Wr.f v;
    public Ns.g w;
    public vn.com.vng.zalopay.clearing.c x;
    public r00.g y;

    public ClearingAccountFragment()
    {
        this.x = 0;
        return;
    }

    public final void I0()
    {
        int v0_0 = this.x;
        if (v0_0 != 0) {
            v0_0.cancel();
        }
        this.x = 0;
        return;
    }

    public final void J(String p2)
    {
        this.mInputPinView.g();
        this.mInputPinView.setError(p2);
        return;
    }

    public final void L(String p3)
    {
        this.mInputOTPView.setInputText("");
        this.mInputOTPView.setError(p3);
        return;
    }

    public final void P(String p3)
    {
        if (p3 != null) {
            android.widget.TextView v0_0 = this.mKeyboardView;
            if (v0_0 != null) {
                v0_0.setVisibility(8);
            }
            this.mFlipperView.setDisplayedChild(3);
            this.mMgsCustomerService.setText(android.text.Html.fromHtml(this.getString(2131952221)));
            this.mMgsCustomerServicePhone.setText(android.text.Html.fromHtml(this.getString(2131952222)));
            this.mMgsError.setText(p3);
            return;
        } else {
            return;
        }
    }

    public final void S()
    {
        android.widget.PopupWindow v0_0 = this.y;
        if ((v0_0 != null) && (!v0_0.m)) {
            v0_0.m = 1;
            android.widget.PopupWindow v0_1 = v0_0.b;
            if (v0_1 != null) {
                v0_1.dismiss();
            }
        }
        return;
    }

    public final void U()
    {
        this.mFlipperView.setDisplayedChild(2);
        this.mInputOTPView.setSubTitle(String.format(this.getString(2131953408), new Object[] {this.mPhoneView.getInputText()})));
        this.mKeyboardView.setEditable(this.mInputOTPView.getNumberEditable());
        android.widget.ScrollView v0_5 = this.getResources().getInteger(2131427390);
        this.mInputOTPView.setLengthToActiveButton(v0_5);
        this.mInputOTPView.setInputLength(v0_5);
        android.widget.ScrollView v0_6 = this.mInputOTPView;
        v0_6.j.requestFocus();
        android.widget.ScrollView v0_8 = v0_6.j;
        v0_8.setSelection(v0_8.length());
        this.mInputOTPView.setOnClick(new vn.com.vng.zalopay.clearing.a(this, 0));
        this.mInputOTPView.setOnSecondClick(new vn.com.vng.zalopay.clearing.a(this, 1));
        this.mScrollView.post(new vn.com.vng.zalopay.clearing.b(this));
        return;
    }

    public void closeApp()
    {
        this.requireActivity().finish();
        return;
    }

    public final void d0(String p3)
    {
        m9.B.e(((android.app.Activity) this.getContext()), p3, new Wr.a(this));
        return;
    }

    public final void e(int p3)
    {
        this.mFlipperView.setDisplayedChild(0);
        vn.com.vng.zalopay.passport.widget.OnboardingEditTextView v3_2 = this.mKeyboardView;
        if (v3_2 != null) {
            v3_2.setVisibility(8);
        }
        this.mPhoneView.setInputTextEnabled(0);
        this.mPhoneView.h();
        this.mPhoneView.setOnClick(new Ae.p(28, this));
        return;
    }

    public final void g0(String p10)
    {
        int v0_0 = this.getActivity();
        if ((v0_0 != 0) && (!v0_0.isFinishing())) {
            com.appsflyer.b v1_6 = new r00.c(v0_0);
            v1_6.d = this.mInputOTPView;
            v1_6.c = v0_0.getString(2131953946);
            v1_6.f = 48;
            v1_6.m = new ref.WeakReference(new Wr.b(this, p10));
            if (v1_6.d == null) {
                throw new IllegalArgumentException("Anchor view not specified.");
            } else {
                if (v1_6.n == 0) {
                    v1_6.n = 2131232298;
                }
                if (v1_6.o == 0) {
                    v1_6.o = 2131232299;
                }
                if (v1_6.p == 0) {
                    v1_6.p = v0_0.getColor(2131100724);
                }
                if (v1_6.q == 0) {
                    v1_6.q = (v0_0.getResources().getDimension(2131167006) / android.content.res.Resources.getSystem().getDisplayMetrics().density);
                }
                if (v1_6.r == 0) {
                    v1_6.r = v0_0.getColor(2131100722);
                }
                if (v1_6.s == -1) {
                    v1_6.s = v0_0.getColor(2131099884);
                }
                if (v1_6.h < 0) {
                    v1_6.h = v0_0.getResources().getDimension(2131166226);
                }
                if (v1_6.i < 0) {
                    v1_6.i = v0_0.getResources().getDimension(2131166227);
                }
                if (v1_6.j < 0) {
                    v1_6.j = v0_0.getResources().getDimension(2131166228);
                }
                if (v1_6.k < 0) {
                    v1_6.k = v0_0.getResources().getDimension(2131166229);
                }
                if (v1_6.l < 0) {
                    v1_6.l = v0_0.getResources().getDimension(2131166230);
                }
                if (v1_6.e == 4) {
                    IllegalArgumentException v10_9;
                    IllegalArgumentException v10_8 = v1_6.f;
                    if (v10_8 == 17) {
                        v10_9 = 1;
                    } else {
                        if (v10_8 == 48) {
                            v10_9 = 3;
                        } else {
                            if (v10_8 == 80) {
                            } else {
                                if (v10_8 == 8388611) {
                                    v10_9 = 2;
                                } else {
                                    if (v10_8 != 8388613) {
                                        throw new IllegalArgumentException("Gravity must have be CENTER, START, END, TOP or BOTTOM.");
                                    } else {
                                        v10_9 = 0;
                                    }
                                }
                            }
                        }
                    }
                    v1_6.e = v10_9;
                }
                if (v1_6.g == null) {
                    v1_6.g = new r00.a(v1_6.r, v1_6.e);
                }
                if (v1_6.u == 0) {
                    v1_6.u = v0_0.getResources().getDimension(2131166225);
                }
                if (v1_6.t == 0) {
                    v1_6.t = v0_0.getResources().getDimension(2131166224);
                }
                if (v1_6.v < 0) {
                    v1_6.v = v0_0.getResources().getDimension(2131166223);
                }
                if (v1_6.w == 0) {
                    v1_6.w = ((long) v0_0.getResources().getInteger(2131427392));
                }
                if (v1_6.b == null) {
                    IllegalArgumentException v10_33 = new android.widget.TextView(v0_0);
                    int v0_2 = v1_6.e;
                    if (v0_2 == 1) {
                        v10_33.setBackgroundResource(v1_6.n);
                    } else {
                        if (v0_2 == 3) {
                            v10_33.setBackgroundResource(v1_6.o);
                        }
                    }
                    v10_33.setTextAppearance(2132018308);
                    v10_33.setTextSize(v1_6.q);
                    v10_33.setTextColor(v1_6.p);
                    v1_6.b = v10_33;
                }
                IllegalArgumentException v10_36 = new r00.g(v1_6);
                this.y = v10_36;
                if (v10_36.m) {
                    throw new IllegalArgumentException("Tooltip has ben dismissed.");
                } else {
                    v10_36.f.getViewTreeObserver().addOnGlobalLayoutListener(v10_36.n);
                    v10_36.f.getViewTreeObserver().addOnGlobalLayoutListener(v10_36.r);
                    v10_36.h.post(new com.appsflyer.b(22, v10_36));
                    return;
                }
            }
        } else {
            return;
        }
    }

    public final int m1()
    {
        return 2131558925;
    }

    public final void n1()
    {
        Ow.b v0_1 = ((CC.f) q9.a5.a());
        v0_1.e0.get();
        this.v = new Wr.f(((Ow.j) v0_1.p1.get()), ((android.content.Context) v0_1.h.get()), v0_1.t(), new Z.g(7, new cv.c(((org.greenrobot.eventbus.e) v0_1.g.get()))), ((nY.p) v0_1.v.get()), v0_1.q());
        this.w = ((Ns.g) v0_1.k.get());
        ((CC.f) q9.a5.a()).J().g();
        return;
    }

    public final void onDestroy()
    {
        this.v.e0();
        super.onDestroy();
        return;
    }

    public final void onDestroyView()
    {
        Wr.f v0_0 = this.x;
        if (v0_0 != null) {
            v0_0.cancel();
        }
        this.x = 0;
        this.v.e0();
        super.onDestroyView();
        return;
    }

    public final void onPause()
    {
        this.v.getClass();
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        rf.e v0_0 = this.v;
        kf.a v1_1 = ((kf.a) v0_0.a);
        rf.e v0_1 = v0_0.g.b().d(hf.b.a());
        Bw.a v2_1 = new Bw.a();
        v0_1.b(v2_1);
        v1_1.c(v2_1);
        return;
    }

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        kf.b v4_1 = this.v;
        v4_1.b = this;
        kf.a v5_9 = this.getArguments();
        if (v5_9 != null) {
            v4_1.e = v5_9.getString("phone_number");
        }
        kf.b v4_2 = this.v;
        if (is.T.e(v4_2.e)) {
            kf.a v5_3 = v4_2.b;
            if (v5_3 != null) {
                ((Wr.h) v5_3).e(0);
                ((Wr.h) v4_2.b).y0(v4_2.e);
            }
            ((kf.a) v4_2.a).c(v4_2.g.o().subscribeOn(hf.b.a()).map(new Wr.d(v4_2, 0)).filter(new Ua.d(11)).subscribeOn(Ef.g.c).observeOn(hf.b.a()).subscribe(new Wr.d(v4_2, 1), new Ua.d(12)));
            return;
        } else {
            return;
        }
    }

    public final void q1(boolean p7)
    {
        kf.a v0_0 = this.v;
        ((kf.a) v0_0.a).c(((kf.b) q9.H.f(v0_0.c.c(this.mPhoneView.getInputText(), this.mInputPinView.getInputText(), this.w.c(this.getContext()), this.w.b())).subscribeWith(new Wr.e(v0_0, p7))));
        return;
    }

    public final void r1()
    {
        this.mFlipperView.setDisplayedChild(1);
        vn.com.vng.zalopay.passport.widget.OnboardingPasswordRoundView v0_2 = this.mKeyboardView;
        if (v0_2 != null) {
            v0_2.setVisibility(0);
        }
        this.mKeyboardView.setEditable(this.mInputPinView.getNumberEditable());
        this.mInputPinView.setEnteredListener(new Wr.a(this));
        return;
    }

    public final void v0(long p2)
    {
        vn.com.vng.zalopay.clearing.c v0_0 = this.x;
        if (v0_0 != null) {
            v0_0.cancel();
        }
        this.x = 0;
        vn.com.vng.zalopay.clearing.c v0_3 = new vn.com.vng.zalopay.clearing.c(this, p2);
        this.x = v0_3;
        v0_3.start();
        return;
    }

    public final void y()
    {
        com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this.getContext(), this.getString(2131954693));
        return;
    }

    public final void y0(String p2)
    {
        this.mPhoneView.setInputText(p2);
        return;
    }

    public final void z0(String p2)
    {
        if (p2 != null) {
            this.mInputOTPView.setInputText(p2);
        }
        return;
    }
}

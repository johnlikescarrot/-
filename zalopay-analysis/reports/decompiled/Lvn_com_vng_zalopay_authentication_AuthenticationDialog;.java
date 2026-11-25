package vn.com.vng.zalopay.authentication;
public class AuthenticationDialog extends android.app.DialogFragment implements vn.com.vng.zalopay.authentication.i {
    public CharSequence a;
    public vn.com.vng.zalopay.authentication.e b;
    public final vn.com.vng.zalopay.authentication.a c;
    public butterknife.Unbinder d;
    public m9.o e;
    public boolean f;
    public int g;
    android.widget.Button mCancelButton;
    android.view.View mFingerprintDecrypt;
    android.widget.Button mSecondDialogButton;
    android.widget.TextView mTvDecryptView;

    public AuthenticationDialog()
    {
        this.c = new vn.com.vng.zalopay.authentication.a(this);
        this.f = 1;
        this.g = 0;
        return;
    }

    public static vn.com.vng.zalopay.authentication.AuthenticationDialog a()
    {
        android.os.Bundle v0_1 = new android.os.Bundle();
        vn.com.vng.zalopay.authentication.AuthenticationDialog v1_1 = new vn.com.vng.zalopay.authentication.AuthenticationDialog();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public final void C0(String p4, boolean p5)
    {
        vn.com.vng.zalopay.authentication.a v0_0 = p4.toString();
        int v1_2 = new java.util.HashMap();
        v1_2.put("reason", v0_0);
        UR.a.e("01.1030.007", v1_2);
        this.mTvDecryptView.setText(p4);
        this.mTvDecryptView.setTextColor(this.getActivity().getColor(2131100648));
        vn.com.vng.zalopay.authentication.a v0_3 = this.c;
        eQ.a.d.removeCallbacks(v0_3);
        if (p5) {
            eQ.a.g(v0_3, 1600);
            return;
        } else {
            return;
        }
    }

    public final void M0()
    {
        eQ.a.d.removeCallbacks(this.c);
        this.mTvDecryptView.setTextColor(this.getActivity().getColor(2131099828));
        this.mTvDecryptView.setText(this.getString(2131952864));
        return;
    }

    public final void V0()
    {
        int v0_0 = this.e;
        if (v0_0 != 0) {
            v0_0.f();
        }
        this.f = 1;
        this.dismissAllowingStateLoss();
        return;
    }

    public final void b(android.content.Context p2)
    {
        if (((p2 instanceof android.app.Activity)) && ((!((android.app.Activity) p2).isFinishing()) && (!((android.app.Activity) p2).isDestroyed()))) {
            super.show(((android.app.Activity) p2).getFragmentManager(), "FingerprintDialog");
        }
        return;
    }

    public final void hideLoading()
    {
        m9.B.a();
        return;
    }

    public final void j(String p2)
    {
        UR.a.d("01.1030.006");
        this.f = 0;
        m9.o v0_2 = this.e;
        if (v0_2 != null) {
            v0_2.c(p2);
        }
        this.dismissAllowingStateLoss();
        return;
    }

    public final void onActivityCreated(android.os.Bundle p5)
    {
        super.onActivityCreated(p5);
        if (this.getDialog() != null) {
            android.view.Window v5_3 = this.getDialog().getWindow();
            if (v5_3 != null) {
                int v0_1;
                int v0_0 = this.getActivity();
                if (v0_0 == 0) {
                    v0_1 = 0;
                } else {
                    int v1_1 = new android.util.DisplayMetrics();
                    v0_0.getWindowManager().getDefaultDisplay().getMetrics(v1_1);
                    v0_1 = v1_1.widthPixels;
                }
                if (v0_1 > 0) {
                    v5_3.setLayout(((int) (((double) v0_1) * 4605831338911806259)), -2);
                }
            }
        }
        return;
    }

    public void onClickCancel()
    {
        this.f = 1;
        this.dismissAllowingStateLoss();
        return;
    }

    public final void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        UR.a.d("01.1030.005");
        this.setRetainInstance(1);
        this.setStyle(1, 2132018254);
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p5, android.view.ViewGroup p6, android.os.Bundle p7)
    {
        this.getDialog().setTitle(this.getString(2131952318));
        this.getDialog().setCanceledOnTouchOutside(0);
        android.view.View v5_1 = p5.inflate(2131558905, p6, 0);
        this.d = butterknife.ButterKnife.a(v5_1, this);
        android.widget.TextView v6_5 = vn.com.vng.zalopay.AndroidApplication.g;
        int v7_3 = 0;
        if (v6_5 == null) {
            v6_5 = 0;
        }
        android.widget.TextView v6_6 = v6_5.a;
        if (v6_6 != null) {
            v7_3 = v6_6;
        }
        this.b = new vn.com.vng.zalopay.authentication.e(((android.content.Context) v7_3.h.get()));
        this.mFingerprintDecrypt.setVisibility(0);
        this.mCancelButton.setText(2131952106);
        this.mSecondDialogButton.setText(2131955732);
        android.widget.TextView v6_12 = this.g;
        if (v6_12 == 1) {
            this.mCancelButton.setVisibility(8);
            this.mSecondDialogButton.setVisibility(0);
            this.mTvDecryptView.setText(2131952844);
        } else {
            if (v6_12 == 2) {
                this.mCancelButton.setVisibility(8);
                this.mSecondDialogButton.setVisibility(0);
                this.mTvDecryptView.setText(2131952863);
            } else {
                if (v6_12 == 3) {
                    this.mCancelButton.setVisibility(8);
                    this.mCancelButton.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, -2));
                    this.mSecondDialogButton.setVisibility(0);
                    this.mTvDecryptView.setText(2131952861);
                    this.mSecondDialogButton.setBackgroundColor(this.getResources().getColor(2131100545));
                    this.mSecondDialogButton.setTextColor(this.getResources().getColor(2131100852));
                } else {
                    if (v6_12 == 4) {
                        this.mCancelButton.setVisibility(8);
                        this.mSecondDialogButton.setVisibility(0);
                        this.mTvDecryptView.setText(2131952845);
                    } else {
                        this.mCancelButton.setVisibility(8);
                        this.mSecondDialogButton.setVisibility(0);
                        this.mTvDecryptView.setText(2131952843);
                    }
                }
            }
        }
        if (this.g == 1) {
            UR.a.i("[SDK][FingerPrint]");
        }
        return v5_1;
    }

    public final void onDestroyView()
    {
        this.b.e0();
        this.d.a();
        super.onDestroyView();
        return;
    }

    public final void onDismiss(android.content.DialogInterface p3)
    {
        eQ.a.d.removeCallbacks(this.c);
        m9.o v0_1 = this.e;
        if ((v0_1 != null) && (this.f)) {
            v0_1.d();
        }
        super.onDismiss(p3);
        return;
    }

    public void onOnClickButtonSecond()
    {
        int v0_0 = this.e;
        if (v0_0 != 0) {
            v0_0.f();
        }
        this.f = 0;
        this.dismissAllowingStateLoss();
        return;
    }

    public final void onPause()
    {
        vn.com.vng.zalopay.authentication.h v0_1 = this.b.e;
        if (v0_1 != null) {
            int v1_0 = v0_1.d;
            if (v1_0 != 0) {
                v0_1.e = 1;
                v1_0.a();
                v0_1.d = 0;
            }
        }
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        vn.com.vng.zalopay.authentication.f$a v0_1 = this.b.e;
        try {
            if (v0_1 != null) {
                vn.com.vng.zalopay.authentication.secret.a v1 = v0_1.b;
                vn.com.vng.zalopay.exception.KeyInvalidatedException v2_4 = v0_1.d;
                if (v2_4 != null) {
                    v0_1.e = 1;
                    v2_4.a();
                    v0_1.d = 0;
                }
                try {
                    v1.getClass();
                    v0_1.a(new hh.a(vn.com.vng.zalopay.authentication.secret.a.b(1, "validate_key_name")));
                    return;
                } catch (vn.com.vng.zalopay.exception.KeyInvalidatedException v2_3) {
                    v1.c();
                    v0_1.f.P(v2_3);
                }
            }
        } catch (Exception) {
        }
        return;
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        super.onViewCreated(p3, p4);
        CharSequence v3_1 = this.b;
        v3_1.b = this;
        android.content.Context v0 = v3_1.c;
        if (v3_1.d == null) {
            v3_1.d = new vn.com.vng.zalopay.authentication.secret.a();
        }
        v3_1.e = new vn.com.vng.zalopay.authentication.h(v0, v3_1.d, v3_1);
        this.a = this.mTvDecryptView.getText();
        return;
    }
}

package vn.com.vng.zalopay.bankbinding.presentation.ui.activity;
public final class FundManagementActivity extends vn.com.vng.zalopay.bankbinding.presentation.ui.activity.PerformanceActivity implements Fo.o {
    public static final synthetic int q;
    public final ng.S m;
    public Z2.L n;
    public androidx.compose.ui.platform.ComposeView o;
    public ro.f p;

    public FundManagementActivity()
    {
        this.m = ng.x.b(new Fo.n(this, 0));
        return;
    }

    public final int e1()
    {
        return -2079850494;
    }

    public final OD.a g1()
    {
        return new OD.a("", -1);
    }

    public final ro.i get()
    {
        if (this.p == null) {
            CC.k v0_2 = vn.com.vng.zalopay.AndroidApplication.g;
            if (v0_2 == null) {
                v0_2 = 0;
            }
            CC.k v0_3 = v0_2.b;
            if (v0_3 != null) {
                this.p = new ro.f(new Bo.p(), v0_3);
            } else {
                this.finish();
            }
        }
        CC.k v0_1 = this.p;
        if (v0_1 == null) {
            return 0;
        } else {
            return v0_1;
        }
    }

    public final void onCreate(android.os.Bundle p10)
    {
        super.onCreate(p10);
        String v0_0 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        String v0_12 = v0_0.b;
        if (v0_12 != null) {
            this.p = new ro.f(new Bo.p(), v0_12);
        } else {
            this.finish();
        }
        p9.U.a(this.getWindow(), 1);
        this.o = ((androidx.compose.ui.platform.ComposeView) this.findViewById(-2079915927));
        this.findViewById(-2079915928);
        String v0_7 = this.o;
        if (v0_7 == null) {
            v0_7 = 0;
        }
        v0_7.setContent(new y0.b(new Fo.m(this, 0), 1, 368116203));
        this.n = ((androidx.navigation.fragment.NavHostFragment) this.getSupportFragmentManager().D(-2079915866)).m1();
        if (p10 == null) {
            UnsupportedOperationException v10_4 = ((vn.com.vng.zalopay.feature.argument.FundingManagerArgs) this.m.getValue()).getBankDetailArgs();
            if (v10_4 != null) {
                String v0_13 = this.n;
                if (v0_13 == null) {
                    v0_13 = 0;
                }
                java.io.Serializable v2_7 = new vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument(new vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData(v10_4.getBankCode(), v10_4.getBankConnectorCode(), v10_4.getBankType(), v10_4.getFirstNum(), v10_4.getLastNum()), v10_4.getName(), v10_4.getDescription(), v10_4.getBackground(), v10_4.getLogo());
                v0_13.getClass();
                UnsupportedOperationException v10_6 = new android.os.Bundle();
                if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument)) {
                    if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument)) {
                        throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                    } else {
                        v10_6.putSerializable("binding_bank_detail_args", ((java.io.Serializable) v2_7));
                    }
                } else {
                    v10_6.putParcelable("binding_bank_detail_args", v2_7);
                }
                v0_13.k(-2079915982, v10_6, 0);
                return;
            }
        }
        return;
    }
}

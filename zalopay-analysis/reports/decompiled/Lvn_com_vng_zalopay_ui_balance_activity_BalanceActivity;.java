package vn.com.vng.zalopay.ui.balance.activity;
public final class BalanceActivity extends vn.com.vng.zalopay.ui.activity.ZPActivity {
    public static final synthetic int m;

    public BalanceActivity()
    {
        return;
    }

    public final int e1()
    {
        return 2131558440;
    }

    public final OD.a g1()
    {
        return new OD.a("", -1);
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        ((androidx.navigation.fragment.NavHostFragment) this.getSupportFragmentManager().D(2131363684)).m1();
        androidx.compose.ui.platform.ComposeView v5_2 = ((androidx.compose.ui.platform.ComposeView) this.findViewById(2131362446));
        if (v5_2 != null) {
            v5_2.setContent(new y0.b(new AA.e(14, this), 1, -19742883));
        }
        return;
    }
}

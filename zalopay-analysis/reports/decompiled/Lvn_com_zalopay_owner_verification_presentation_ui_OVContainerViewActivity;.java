package vn.com.zalopay.owner_verification.presentation.ui;
public final class OVContainerViewActivity extends androidx.activity.ComponentActivity {
    public static final q0.N0 i;
    public static vn.com.zalopay.owner_verification.presentation.ui.OVContainerViewActivity j;
    public static kotlin.Pair k;

    static OVContainerViewActivity()
    {
        vn.com.zalopay.owner_verification.presentation.ui.OVContainerViewActivity.i = q0.d.G(BX.c.a, q0.D0.e);
        return;
    }

    public OVContainerViewActivity()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        if (android.os.Build$VERSION.SDK_INT < 34) {
            this.overridePendingTransition(0, 0);
        }
        super.onCreate(p3);
        vn.com.zalopay.owner_verification.presentation.ui.OVContainerViewActivity.j = this;
        k.e.a(this, yX.a.a);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.owner_verification.presentation.ui.OVContainerViewActivity.j, this)) {
            vn.com.zalopay.owner_verification.presentation.ui.OVContainerViewActivity.j = 0;
        }
        return;
    }
}

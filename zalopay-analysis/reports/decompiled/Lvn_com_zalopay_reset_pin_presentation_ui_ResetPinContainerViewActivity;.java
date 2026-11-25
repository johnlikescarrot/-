package vn.com.zalopay.reset_pin.presentation.ui;
public final class ResetPinContainerViewActivity extends androidx.activity.ComponentActivity {
    public static final q0.N0 i;
    public static vn.com.zalopay.reset_pin.presentation.ui.ResetPinContainerViewActivity j;
    public static kotlin.Pair k;

    static ResetPinContainerViewActivity()
    {
        vn.com.zalopay.reset_pin.presentation.ui.ResetPinContainerViewActivity.i = q0.d.G(eY.e.a, q0.D0.e);
        return;
    }

    public ResetPinContainerViewActivity()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        if (android.os.Build$VERSION.SDK_INT < 34) {
            this.overridePendingTransition(0, 0);
        }
        super.onCreate(p3);
        vn.com.zalopay.reset_pin.presentation.ui.ResetPinContainerViewActivity.j = this;
        k.e.a(this, bY.b.a);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.reset_pin.presentation.ui.ResetPinContainerViewActivity.j, this)) {
            vn.com.zalopay.reset_pin.presentation.ui.ResetPinContainerViewActivity.j = 0;
        }
        return;
    }
}

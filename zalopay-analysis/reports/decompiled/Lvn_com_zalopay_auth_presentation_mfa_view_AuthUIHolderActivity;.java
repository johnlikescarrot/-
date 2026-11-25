package vn.com.zalopay.auth.presentation.mfa.view;
public final class AuthUIHolderActivity extends androidx.activity.ComponentActivity {
    public static final q0.N0 i;
    public static vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity j;
    public static volatile boolean k;
    public static kotlin.Pair l;

    static AuthUIHolderActivity()
    {
        vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.i = q0.d.G(LS.c.a, q0.D0.e);
        return;
    }

    public AuthUIHolderActivity()
    {
        return;
    }

    public final boolean b1()
    {
        if (!this.isFinishing()) {
            if ((!vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.k) || (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.i.getValue(), LS.c.a))) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.j, this)) {
                    vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.j = 0;
                }
                this.finish();
                if (android.os.Build$VERSION.SDK_INT < 34) {
                    this.overridePendingTransition(0, 0);
                }
            } else {
                return 0;
            }
        }
        return 1;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        if (android.os.Build$VERSION.SDK_INT < 34) {
            this.overridePendingTransition(0, 0);
        }
        super.onCreate(p3);
        if (!this.b1()) {
            vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.j = this;
            k.e.a(this, LS.f.a);
            return;
        } else {
            return;
        }
    }

    public final void onDestroy()
    {
        super.onDestroy();
        if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.j, this)) {
            vn.com.zalopay.auth.presentation.mfa.view.AuthUIHolderActivity.j = 0;
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        this.b1();
        return;
    }
}

package vn.com.zalopay.ce_ticket.presentation.ui;
public final class CETicketContainerActivity extends androidx.activity.ComponentActivity {
    public static final q0.N0 i;
    public static vn.com.zalopay.ce_ticket.presentation.ui.CETicketContainerActivity j;
    public static boolean k;

    static CETicketContainerActivity()
    {
        vn.com.zalopay.ce_ticket.presentation.ui.CETicketContainerActivity.i = q0.d.G(VT.c.a, q0.D0.e);
        return;
    }

    public CETicketContainerActivity()
    {
        return;
    }

    public final void onBackPressed()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        if (android.os.Build$VERSION.SDK_INT < 34) {
            this.overridePendingTransition(0, 0);
        }
        super.onCreate(p3);
        vn.com.zalopay.ce_ticket.presentation.ui.CETicketContainerActivity.j = this;
        k.e.a(this, TT.b.a);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.ce_ticket.presentation.ui.CETicketContainerActivity.j, this)) {
            vn.com.zalopay.ce_ticket.presentation.ui.CETicketContainerActivity.j = 0;
        }
        return;
    }
}

package vn.com.vng.zalopay.bankbinding.presentation.ui.webapp;
public final class BankWebViewInterface {
    public final androidx.lifecycle.T a;

    public BankWebViewInterface(androidx.lifecycle.T p1)
    {
        this.a = p1;
        return;
    }

    public final void onJsPaymentResult(String p3)
    {
        no.k v0_0 = 0;
        if (p3 != null) {
            try {
            } catch (Exception) {
            }
            v0_0 = ((no.k) nY.h.b(p3, new vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebViewInterface$onJsPaymentResult$$inlined$parseJsonSafely$1().getType()));
        }
        this.a.j(new Xs.b(new uo.J(v0_0)));
        return;
    }

    public final void onReceiveLinkBankError(String p2, String p3)
    {
        this.a.j(new Xs.b(new uo.H(p3)));
        return;
    }

    public final void onUserCancelLinkBank()
    {
        this.a.j(new Xs.b(uo.I.a));
        return;
    }

    public final void trackEvent(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            UR.a.d(p2);
        }
        return;
    }
}

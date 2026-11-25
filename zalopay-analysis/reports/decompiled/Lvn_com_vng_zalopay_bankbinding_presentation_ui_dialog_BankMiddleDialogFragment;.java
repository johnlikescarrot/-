package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankMiddleDialogFragment extends Io.Y {
    public final a9.f H;
    public final ng.S I;

    public BankMiddleDialogFragment()
    {
        this.H = new a9.f(kotlin.jvm.internal.M.a.b(Io.E), new Io.i(this, 7));
        this.I = ng.x.b(new Io.f(4, this));
        return;
    }

    public final android.app.Dialog q1(android.os.Bundle p4)
    {
        DZ.x v4_1 = new DZ.x(this.requireActivity(), this.w1().getIconType(), 2132018254);
        v4_1.setCanceledOnTouchOutside(0);
        v4_1.G = 0;
        Io.D v0_1 = this.w1().getConfirmButton();
        if (v0_1 == null) {
            v0_1 = this.getString(-2079719390);
        } else {
            if (v0_1.length() <= 0) {
                v0_1 = this.getString(-2079719390);
            }
            if (v0_1 == null) {
            }
        }
        v4_1.d(v0_1);
        Io.D v0_4 = this.w1().getCancelButton();
        if (v0_4 != null) {
            v4_1.c(v0_4);
        }
        Io.D v0_6 = this.w1().getTitle();
        if (v0_6 != null) {
            v4_1.g(v0_6);
        }
        if (this.w1().getMessage().length() > 0) {
            v4_1.e(this.w1().getMessage());
        }
        v4_1.F = new Io.D(this, v4_1, 0);
        v4_1.E = new Io.D(this, v4_1, 1);
        return v4_1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument w1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument) this.I.getValue());
    }
}

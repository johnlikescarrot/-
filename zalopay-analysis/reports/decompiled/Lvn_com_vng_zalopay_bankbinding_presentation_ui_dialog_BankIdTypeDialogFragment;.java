package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankIdTypeDialogFragment extends androidx.fragment.app.v {
    public Sn.b H;
    public final a9.f I;
    public final ng.S J;

    public BankIdTypeDialogFragment()
    {
        this.I = new a9.f(kotlin.jvm.internal.M.a.b(Io.C), new Io.i(this, 6));
        this.J = ng.x.b(new Io.f(3, this));
        return;
    }

    public final void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        this.t1(1, 2132018254);
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.widget.LinearLayout v2_5 = p2.inflate(-2079850472, p3, 0);
        Sn.b v3_5 = -2079915946;
        String v4_3 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915946, v2_5));
        if (v4_3 != null) {
            v3_5 = -2079915872;
            android.widget.ListView v0_2 = ((android.widget.ListView) l5.s.a(-2079915872, v2_5));
            if (v0_2 != null) {
                android.widget.LinearLayout v2_4 = ((android.widget.LinearLayout) v2_5);
                this.H = new Sn.b(v2_4, v4_3, v0_2, v2_4);
                return v2_4;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v2_5.getResources().getResourceName(v3_5)));
    }

    public final void onDestroyView()
    {
        super.onDestroyView();
        this.H = 0;
        return;
    }

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        String v0_4 = this.J;
        vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity[] v2_1 = new vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity[0];
        IllegalStateException v4_2 = new Go.b(this.requireContext(), ((vn.com.vng.zalopay.bankbinding.domain.entity.IdentityOptionEntity[]) ((java.util.Collection) ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument) v0_4.getValue()).getOptions()).toArray(v2_1)), ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdTypeDialogArgument) v0_4.getValue()).getCurrentIdType());
        Io.B v5_1 = this.H;
        if (v5_1 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            ((android.widget.ListView) v5_1.d).setAdapter(v4_2);
            IllegalStateException v4_5 = this.H;
            if (v4_5 == null) {
                throw new IllegalStateException("Required value was null.");
            } else {
                ((androidx.appcompat.widget.AppCompatButton) v4_5.c).setOnClickListener(new Io.A(0, this));
                IllegalStateException v4_10 = this.H;
                if (v4_10 == null) {
                    throw new IllegalStateException("Required value was null.");
                } else {
                    ((android.widget.ListView) v4_10.d).setOnItemClickListener(new Io.B(this));
                    return;
                }
            }
        }
    }
}

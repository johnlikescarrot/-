package vn.com.vng.zalopay.cashier;
public abstract class CashierActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final java.util.Stack k;
    public final ng.S i;
    public final String j;

    static CashierActivity()
    {
        vn.com.vng.zalopay.cashier.CashierActivity.k = new java.util.Stack();
        return;
    }

    public CashierActivity()
    {
        this.i = ng.x.b(new TT.a(15, this));
        try {
            String v0_1 = java.util.UUID.randomUUID().toString();
        } catch (String v0_2) {
            v0_1 = kotlin.ResultKt.createFailure(v0_2);
        }
        if ((v0_1 instanceof ng.K)) {
            v0_1 = "";
        }
        this.j = ((String) v0_1);
        return;
    }

    public abstract String e1();

    public abstract Wo.b g1();

    public void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        vn.com.vng.zalopay.cashier.CashierActivity.k.push(this);
        return;
    }

    public final void onDestroy()
    {
        vn.com.vng.zalopay.cashier.CashierActivity.k.remove(this);
        super.onDestroy();
        return;
    }
}

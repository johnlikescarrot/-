package vn.com.vng.zalopay.core.ui.activity;
public abstract class BaseBindingActivity extends androidx.appcompat.app.AppCompatActivity {
    public com.google.firebase.perf.metrics.Trace i;
    public M3.a j;

    public BaseBindingActivity()
    {
        return;
    }

    public final M3.a e1()
    {
        String v0_0 = this.j;
        if (v0_0 == null) {
            if (v0_0 == null) {
                v0_0 = 0;
            }
            String v0_5 = v0_0.getClass().getClasses();
            IllegalStateException v1_1 = new StringBuilder("[BaseBindingActivity] Must inflate ");
            v1_1.append(v0_5);
            v1_1.append(" first");
            throw new IllegalStateException(v1_1.toString().toString());
        } else {
            if (v0_0 != null) {
                return v0_0;
            } else {
                return 0;
            }
        }
    }

    public abstract yN.a g1(android.view.LayoutInflater p0);

    public abstract void h1();

    public abstract void i1();

    public void onCreate(android.os.Bundle p3)
    {
        com.google.firebase.perf.metrics.Trace v0_0 = Pb.b.a();
        String v1_1 = this.getClass().getSimpleName();
        v0_0.getClass();
        com.google.firebase.perf.metrics.Trace v0_1 = Pb.b.b(v1_1);
        this.i = v0_1;
        v0_1.start();
        super.onCreate(p3);
        this.h1();
        this.j = this.g1(this.getLayoutInflater());
        this.setContentView(this.e1().getRoot());
        this.i1();
        return;
    }
}

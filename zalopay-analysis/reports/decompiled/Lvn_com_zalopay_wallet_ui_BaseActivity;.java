package vn.com.zalopay.wallet.ui;
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity implements YY.l {
    public static final java.util.Stack k;
    public final com.google.firebase.iid.i i;
    public boolean j;

    static BaseActivity()
    {
        vn.com.zalopay.wallet.ui.BaseActivity.k = new java.util.Stack();
        return;
    }

    public BaseActivity()
    {
        this.i = new com.google.firebase.iid.i(23, 0);
        return;
    }

    public final void O(androidx.fragment.app.Fragment p7)
    {
        if ((!this.isFinishing()) && ((!this.j) && (p7 != null))) {
            int v0_18 = this.getSupportFragmentManager();
            androidx.fragment.app.a v1_2 = this.i;
            v1_2.b = v0_18;
            v0_18.o.add(new YY.k(v1_2));
            v1_2.c = new ref.WeakReference(this);
            if (!((androidx.fragment.app.n0) v1_2.b).P()) {
                int v0_9 = ((androidx.fragment.app.n0) v1_2.b).H();
                int v3_0 = 0;
                while (v3_0 < v0_9) {
                    int v4_5;
                    int v4_2 = ((androidx.fragment.app.n0) v1_2.b);
                    if (v3_0 != v4_2.d.size()) {
                        v4_5 = ((androidx.fragment.app.e0) v4_2.d.get(v3_0));
                    } else {
                        v4_5 = v4_2.h;
                        if (v4_5 == 0) {
                            throw new IndexOutOfBoundsException();
                        }
                    }
                    ((androidx.fragment.app.n0) v1_2.b).T(v4_5.getId(), 0);
                    v3_0++;
                }
                int v0_12 = ((androidx.fragment.app.n0) v1_2.b);
                if ((v0_12 != 0) && (v0_12.E(p7.getTag()) == null)) {
                    int v0_15 = ((androidx.fragment.app.n0) v1_2.b);
                    v0_15.getClass();
                    androidx.fragment.app.a v1_1 = new androidx.fragment.app.a(v0_15);
                    v1_1.m(2131362806, p7, 0);
                    v1_1.n(2130772072, 2130772078, 2130772073, 2130772077);
                    v1_1.c(p7.getClass().getCanonicalName());
                    v1_1.f();
                }
            }
        }
        return;
    }

    public abstract androidx.fragment.app.Fragment e1();

    public abstract int g1();

    public final void onActivityResult(int p3, int p4, android.content.Intent p5)
    {
        super.onActivityResult(p3, p4, p5);
        androidx.fragment.app.Fragment v0_3 = ((androidx.fragment.app.n0) this.i.b).D(2131362806);
        if (v0_3 != null) {
            v0_3.onActivityResult(p3, p4, p5);
        }
        return;
    }

    public final void onBackPressed()
    {
        androidx.fragment.app.n0 v0_0 = this.i;
        ((androidx.fragment.app.n0) v0_0.b).D(2131362806);
        if (!this.j) {
            if (((androidx.fragment.app.n0) v0_0.b).H() != 1) {
                if (!((androidx.fragment.app.n0) v0_0.b).P()) {
                    ((androidx.fragment.app.n0) v0_0.b).V();
                }
            } else {
                this.finish();
            }
            super.onBackPressed();
            return;
        } else {
            return;
        }
    }

    public final void onBackStackChanged()
    {
        ((androidx.fragment.app.n0) this.i.b).H();
        return;
    }

    public void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        java.util.List v0_1 = ((fv.a) new fv.c(((org.greenrobot.eventbus.e) ((dagger.internal.o) wY.a.a().a.b).get())).get());
        if ((v0_1 != null) && (v0_1.a)) {
            q9.h5.a(this, n9.e0.b(this, v0_1.b, ".webp"), v0_1.d);
        }
        q9.h5.b(this);
        this.j = 0;
        vn.com.zalopay.wallet.ui.BaseActivity.k.push(this);
        this.setContentView(this.g1());
        String v1_6 = this.i;
        Object[] v2_1 = this.getSupportFragmentManager();
        v1_6.b = v2_1;
        v2_1.o.add(new YY.k(v1_6));
        String v1_8 = this.i;
        v1_8.getClass();
        v1_8.c = new ref.WeakReference(this);
        if (p5 == null) {
            try {
                this.getIntent().getExtras();
                this.O(this.e1());
            } catch (Exception v5_4) {
                Object[] v2_4 = new Object[0];
                ck.b.c(v5_4, "Navigate on error", v2_4);
            }
        }
        this.getWindow().setStatusBarColor(0);
        return;
    }

    public final void onDestroy()
    {
        vn.com.zalopay.wallet.ui.BaseActivity.k.remove(this);
        super.onDestroy();
        return;
    }

    public final boolean onOptionsItemSelected(android.view.MenuItem p2)
    {
        if (p2.getItemId() != 16908332) {
            return 0;
        } else {
            this.onBackPressed();
            return 1;
        }
    }

    public final void onResumeFragments()
    {
        super.onResumeFragments();
        this.j = 0;
        return;
    }

    public final void onSaveInstanceState(android.os.Bundle p1, android.os.PersistableBundle p2)
    {
        super.onSaveInstanceState(p1, p2);
        this.j = 1;
        return;
    }
}

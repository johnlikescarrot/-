package vn.com.vng.zalopay.feature.ui;
public final class FeatureModuleRoutingActivity extends androidx.appcompat.app.AppCompatActivity implements lx.a {
    public c2.b i;
    public nx.a j;
    public wa.b k;
    public Jv.a l;

    public FeatureModuleRoutingActivity()
    {
        return;
    }

    public final void S0()
    {
        boolean v0_0 = this.k;
        String v1_0 = 0;
        if (!v0_0) {
            v0_0 = 0;
        }
        boolean v0_2 = v0_0.e();
        nx.a v2 = this.j;
        if (v2 != null) {
            v1_0 = v2;
        }
        if (v0_2.contains(v1_0.d())) {
            this.finish();
        }
        return;
    }

    public final void onCreate(android.os.Bundle p7)
    {
        this.i = new c2.b(((android.content.Context) ((CC.f) q9.a5.a()).h.get()), 0);
        super.onCreate(p7);
        nx.a v1_0 = 0;
        boolean v7_9 = this.getLayoutInflater().inflate(2131558460, 0, 0);
        String v0_9 = 2131363684;
        if (((androidx.fragment.app.FragmentContainerView) l5.s.a(2131363684, v7_9)) != null) {
            vn.zalopay.ui.navigationbar.NavigationBar v4_1 = ((vn.zalopay.ui.navigationbar.NavigationBar) l5.s.a(2131363687, v7_9));
            if (v4_1 == null) {
                v0_9 = 2131363687;
            } else {
                boolean v7_13 = ((android.widget.LinearLayout) v7_9);
                this.l = new Jv.a(0, v4_1, v7_13);
                this.setContentView(v7_13);
                this.k = m9.D2.a(this);
                int vtmp12 = this.getIntent().getIntExtra("feature_type", 0);
                android.os.Bundle v2_4 = this.i;
                if (v2_4 == null) {
                    v2_4 = 0;
                }
                boolean v7_18;
                android.os.Bundle v2_5 = v2_4.a;
                switch (vtmp12) {
                    case 1:
                        v7_18 = new CW.a(v2_5);
                        break;
                    case 2:
                        v7_18 = new com.airbnb.lottie.d(v2_5);
                        break;
                    case 3:
                        v7_18 = new c2.b(v2_5, 0);
                        break;
                    case 4:
                    case 5:
                    default:
                        v7_18 = 0;
                        break;
                    case 6:
                        v7_18 = new H8.n(v2_5);
                        break;
                    case 7:
                        v7_18 = new C4.i(v2_5);
                        break;
                    case 8:
                        v7_18 = new AT.a(v2_5);
                        break;
                    case 9:
                        v7_18 = new g4.a(v2_5, 10);
                        break;
                    case 10:
                        v7_18 = new M4.r(v2_5);
                        break;
                    case 11:
                        v7_18 = new n8.i(2, v2_5);
                        break;
                }
                if (v7_18) {
                    this.j = v7_18;
                    this.setTitle(v7_18.getTitle());
                    boolean v7_28 = this.l;
                    if (!v7_28) {
                        v7_28 = 0;
                    }
                    boolean v7_30 = ((vn.zalopay.ui.navigationbar.NavigationBar) v7_28.c);
                    android.os.Bundle v2_7 = this.j;
                    if (v2_7 == null) {
                        v2_7 = 0;
                    }
                    boolean v7_3;
                    v7_30.setTitle(v2_7.getTitle());
                    v7_30.setListener(new IK.n(this, 4));
                    boolean v7_2 = this.getSupportFragmentManager().D(2131363684);
                    if (!(v7_2 instanceof androidx.navigation.fragment.NavHostFragment)) {
                        v7_3 = 0;
                    } else {
                        v7_3 = ((androidx.navigation.fragment.NavHostFragment) v7_2);
                    }
                    if (v7_3) {
                        boolean v7_4 = v7_3.m1();
                        if (v7_4) {
                            android.os.Bundle v2_0 = this.j;
                            if (v2_0 == null) {
                                v2_0 = 0;
                            }
                            v7_4.y(((Z2.M) v7_4.C.getValue()).b(v2_0.c()), this.getIntent().getExtras());
                            boolean v7_5 = this.k;
                            if (!v7_5) {
                                v7_5 = 0;
                            }
                            boolean v7_7 = v7_5.e();
                            String v0_6 = this.j;
                            if (v0_6 != null) {
                                v1_0 = v0_6;
                            }
                            if (v7_7.contains(v1_0.d())) {
                                this.finish();
                            }
                            return;
                        }
                    }
                    this.finish();
                    return;
                } else {
                    this.finish();
                    return;
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v7_9.getResources().getResourceName(v0_9)));
    }
}

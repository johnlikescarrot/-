package vn.com.vng.zalopay.ui.activity;
public abstract class BaseDeeplinkActivity extends vn.com.vng.zalopay.ui.activity.SkinActivity implements ZO.b {
    public WO.d n;

    public BaseDeeplinkActivity()
    {
        return;
    }

    public final android.app.Activity getActivity()
    {
        return this;
    }

    public final android.content.Context getContext()
    {
        return this;
    }

    public void onActivityResult(int p5, int p6, android.content.Intent p7)
    {
        super.onActivityResult(p5, p6, p7);
        WO.d v0 = this.n;
        if (v0.b != null) {
            if ((p6 != -1) || (p5 != 142)) {
                if (p5 != 161) {
                    if ((p6 != null) || (p5 != 100)) {
                        if ((p6 == -1) && ((p5 == 100) && (p7 != null))) {
                            v0.q = 1;
                            v0.G0(p7.getData());
                        }
                    } else {
                        ((ZO.c) v0.b).getActivity().finish();
                        return;
                    }
                } else {
                    ((ZO.c) v0.b).u(p7, Integer.valueOf(p6));
                    return;
                }
            } else {
                v0.q = 1;
                v0.H0(((ZO.c) v0.b).getIntent());
                return;
            }
        }
        return;
    }

    public void onCreate(android.os.Bundle p7)
    {
        super.onCreate(p7);
        android.content.Intent v0_0 = this.n;
        v0_0.getClass();
        org.greenrobot.eventbus.e v2 = v0_0.e;
        v0_0.b = ((ZO.c) this);
        String v1_7 = v0_0.c;
        long v3_0 = v0_0.d.p();
        try {
            if (v3_0 != 0) {
                long v4_0 = v0_0.b;
                if (v4_0 != 0) {
                    GO.e.a.o(v1_7, q9.z6.b(((ZO.c) v4_0).getIntent().getData()));
                }
                long v4_5 = eQ.e.c(v1_7);
                if (!android.text.TextUtils.isEmpty(v4_5)) {
                    UR.a.e = v4_5;
                }
                UR.a.g = v3_0.c;
                UR.a.f = v0_0.r.c(v1_7);
                UR.a.o = String.valueOf(v3_0.d);
                UR.a.k = String.valueOf(v3_0.b);
            }
        } catch (Exception) {
        }
        if (!v2.e(v0_0)) {
            v2.k(v0_0);
        }
        if ((this.getIntent().getFlags() & 1048576) != 1048576) {
            if (p7 == null) {
                this.n.H0(this.getIntent());
            }
            return;
        } else {
            this.n.b0(at.W.f);
            return;
        }
    }

    public final void onDestroy()
    {
        this.n.e0();
        this.n.e0();
        super.onDestroy();
        return;
    }

    public final void onNewIntent(android.content.Intent p2)
    {
        super.onNewIntent(p2);
        this.setIntent(p2);
        this.n.H0(this.getIntent());
        return;
    }
}

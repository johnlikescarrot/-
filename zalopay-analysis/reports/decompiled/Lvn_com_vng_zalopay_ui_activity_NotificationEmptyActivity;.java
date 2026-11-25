package vn.com.vng.zalopay.ui.activity;
public class NotificationEmptyActivity extends androidx.fragment.app.FragmentActivity implements DH.c {
    public static final synthetic int r;
    public int i;
    public Pt.n j;
    public vn.com.vng.zalopay.navigation.b k;
    public org.greenrobot.eventbus.e l;
    public xn.b m;
    public Ns.g n;
    public Yv.b o;
    public final kf.a p;
    public um.d q;

    public NotificationEmptyActivity()
    {
        this.i = 1;
        this.p = new kf.a(0);
        return;
    }

    public static java.util.HashMap c1(org.json.JSONObject p5)
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        java.util.Iterator v1 = p5.keys();
        while (v1.hasNext()) {
            String v2_0 = ((String) v1.next());
            java.util.HashMap v3_0 = p5.get(v2_0);
            if ((v3_0 instanceof org.json.JSONObject)) {
                v3_0 = vn.com.vng.zalopay.ui.activity.NotificationEmptyActivity.c1(((org.json.JSONObject) v3_0));
            }
            v0_1.put(v2_0, v3_0);
        }
        return v0_1;
    }

    public final void M(String p3)
    {
        this.k.j(this, 1, p3);
        return;
    }

    public final void X0(android.net.Uri p3)
    {
        this.k.getClass();
        android.content.Intent v0_2 = new android.content.Intent("android.intent.action.VIEW");
        v0_2.setData(p3);
        this.startActivity(v0_2);
        return;
    }

    public final void e1(android.content.Intent p6)
    {
        com.google.common.reflect.v v0_0 = vn.com.vng.zalopay.AndroidApplication.g;
        vn.com.vng.zalopay.data.notification.b v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        com.google.common.reflect.v v0_6 = v0_0.b;
        if (v0_6 != null) {
            this.p.c(v0_6.s1().a().subscribeOn(Ef.g.c).subscribe(new K0.a(15), new Ua.d(12)));
        }
        if (this.isTaskRoot()) {
            this.k.c(this, 0, 1);
        }
        org.greenrobot.eventbus.e v2_5 = this.l;
        Pt.n v3_3 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v3_3 == null) {
            v3_3 = 0;
        }
        Pt.n v3_4 = v3_3.b;
        if (v3_4 != null) {
            v1 = v3_4.A1();
        }
        this.j.p();
        com.google.common.reflect.v v0_7 = new com.google.common.reflect.v(v2_5, this, v1);
        v0_7.s(p6);
        v0_7.v();
        return;
    }

    public final void h(String p8)
    {
        if (com.google.android.gms.internal.mlkit_vision_barcode.l0.c(p8)) {
            kotlinx.coroutines.BuildersKt.runBlocking$default(0, new b0.x(this.o, this, android.net.Uri.parse(p8), 0, 10), 1, 0);
            return;
        } else {
            return;
        }
    }

    public final void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        if ((p2 == 1478) && (p3 == -1)) {
            this.e1(p4);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p13)
    {
        super.onCreate(p13);
        android.os.Bundle v13_1 = vn.com.vng.zalopay.AndroidApplication.g;
        um.d v0_3 = 0;
        if (v13_1 == null) {
            v13_1 = 0;
        }
        android.os.Bundle v13_12 = v13_1.a;
        if (v13_12 != null) {
            v0_3 = v13_12;
        }
        this.j = v0_3.D();
        this.l = v0_3.i();
        this.m = v0_3.g();
        this.n = v0_3.P();
        this.o = v0_3.K();
        this.q = v0_3.m();
        if (this.j != null) {
            this.k = v0_3.R();
            android.os.Bundle v13_11 = this.getIntent();
            if (v13_11.hasExtra("notification_source")) {
                this.i = v13_11.getIntExtra("notification_source", 1);
            }
            GO.e.a.o(this, q9.z6.b(v13_11.getData()));
            UR.a.g(3, this.j.getUserId(), "openFromNotification");
            if (this.i == 1) {
                um.d v0_11 = this.getIntent().getStringExtra("notification_clicked_tracking_argument");
                if (v0_11 != null) {
                    try {
                        UR.a.e("01.1801.000", vn.com.vng.zalopay.ui.activity.NotificationEmptyActivity.c1(new org.json.JSONObject(v0_11)));
                    } catch (um.d v0_13) {
                        v0_13.printStackTrace();
                    }
                }
            }
            if ((v13_11.hasExtra("notification_pushid")) && (v13_11.hasExtra("notification_push_source"))) {
                UR.a.h(1, v13_11.getIntExtra("notification_push_source", 0), this.j.getUserId(), v13_11.getStringExtra("notification_pushid"), this.n.c(this), System.currentTimeMillis());
            }
            if (this.j.i()) {
                this.e1(v13_11);
                return;
            } else {
                android.os.Bundle v13_3 = v13_11.getExtras();
                if (v13_3 == null) {
                    v13_3 = new android.os.Bundle();
                }
                v13_3.putInt("login_source", 1);
                v13_3.putInt("notification_source", this.i);
                this.q.c(this, v13_3);
                this.finish();
                return;
            }
        } else {
            return;
        }
    }

    public final void onDestroy()
    {
        this.p.e();
        super.onDestroy();
        return;
    }

    public final void t(android.os.Bundle p7, long p8)
    {
        eQ.a.g(new MO.i(this, p8, p7, 0), 3000);
        return;
    }
}

package vn.com.zalopay.nfc_sdk.nfc_in_house;
public final class BottomSheetNFC extends androidx.activity.ComponentActivity {
    public static AZ.h t;
    public static BT.e u;
    public static AA.d v;
    public static AZ.f w;
    public static BV.m x;
    public static BV.m y;
    public static Ae.r z;
    public android.os.Vibrator i;
    public String j;
    public String k;
    public String l;
    public int m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public boolean s;

    public BottomSheetNFC()
    {
        this.o = -1;
        return;
    }

    public static final void b1(q0.C0 p0, int p1)
    {
        p0.setValue(Integer.valueOf(p1));
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        if (android.os.Build$VERSION.SDK_INT >= 27) {
            this.setRequestedOrientation(1);
        }
        super.onCreate(p5);
        this.j = this.getIntent().getStringExtra("extra_id_card_key");
        this.k = this.getIntent().getStringExtra("extra_doe_key");
        this.l = this.getIntent().getStringExtra("extra_dob_key");
        this.m = this.getIntent().getIntExtra("extra_max_retry_key", 0);
        this.p = this.getIntent().getIntExtra("extra_current_count_key", 0);
        this.n = this.getIntent().getIntExtra("extra_current_retry_key", 0);
        this.o = this.getIntent().getIntExtra("extra_max_scan_key", -1);
        this.q = this.getIntent().getBooleanExtra("extra_auto_show_key", 0);
        this.r = this.getIntent().getBooleanExtra("extra_is_pace_key", 0);
        if (!YW.l.u) {
            YW.l.e = android.nfc.NfcAdapter.getDefaultAdapter(this);
            YW.l.u = 1;
        }
        k.e.a(this, new y0.b(new AA.e(29, this), 1, 1162639237));
        return;
    }

    public final void onPause()
    {
        if (this.s) {
            kotlinx.coroutines.Job v0_2 = YW.l.k;
            if (v0_2 != null) {
                kotlinx.coroutines.Job v0_4 = ((android.app.Activity) v0_2.get());
                if ((v0_4 != null) && (v0_4 == this)) {
                    kotlinx.coroutines.Job v0_1 = YW.l.j;
                    if (v0_1 != null) {
                        v0_1.cancel(0);
                    }
                    YW.l.d(this);
                }
            }
        }
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (this.s) {
            if (this.i == null) {
                this.i = ((android.os.Vibrator) this.getSystemService(android.os.Vibrator));
            }
            YW.l.f(this, 1, new YW.a(this, 0));
            this.s = 1;
        }
        return;
    }
}

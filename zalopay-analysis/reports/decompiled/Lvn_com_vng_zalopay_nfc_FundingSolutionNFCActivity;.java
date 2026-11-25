package vn.com.vng.zalopay.nfc;
public final class FundingSolutionNFCActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int s;
    public final ng.S i;
    public android.nfc.NfcAdapter j;
    public xS.b k;
    public KG.a l;
    public kG.b m;
    public final O5.b n;
    public sH.k o;
    public kotlinx.coroutines.Job p;
    public int q;
    public String r;

    public FundingSolutionNFCActivity()
    {
        this.i = ng.x.b(new sH.f(this, 0));
        this.n = new O5.b(kotlin.jvm.internal.M.a.b(sH.M), new sH.j(this, 0), new sH.f(this, 1), new sH.j(this, 1));
        this.q = 1;
        this.r = "";
        return;
    }

    public final void onCreate(android.os.Bundle p13)
    {
        super.onCreate(p13);
        androidx.lifecycle.A v13_1 = com.google.android.gms.internal.mlkit_vision_barcode.d7.a;
        if (v13_1 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            y0.b v0_19 = new xS.b();
            v0_19.a = ji.j0.c(yH.b.c);
            this.k = v0_19;
            y0.b v0_10 = ((KG.a) ((dagger.internal.o) v13_1.c).get());
            p9.n7.c(v0_10);
            this.l = v0_10;
            this.m = new kG.b(1);
            new sH.M();
            androidx.lifecycle.A v13_13 = ((Ns.d) ((dagger.internal.o) v13_1.e).get());
            p9.n7.c(v13_13);
            int v1_4 = new h3.j();
            v1_4.a = new java.text.SimpleDateFormat("HH:mm:ss dd/MM/yyyy", java.util.Locale.getDefault());
            v1_4.b = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Metadata.platform", "zpa"), kotlin.TuplesKt.to("Metadata.os", "Android"), kotlin.TuplesKt.to("Metadata.osType", "Metadata.androidOS"), kotlin.TuplesKt.to("Metadata.osVersion", v13_13.d), kotlin.TuplesKt.to("Metadata.deviceModel", v13_13.f)}));
            this.o = new sH.k(v1_4);
            if (!this.getIntent().hasExtra("source")) {
                throw new IllegalArgumentException("Failed requirement.");
            } else {
                this.q = this.getIntent().getIntExtra("source", 1);
                y0.b v0_21 = this.getIntent().getIntExtra("result_screen_visibility", 1);
                uH.f.b.getClass();
                uH.f.c.get(Integer.valueOf(v0_21));
                this.getIntent().getIntExtra("from_zmp", 0);
                androidx.lifecycle.A v13_3 = this.getIntent().getStringExtra("external_request_id");
                if (v13_3 == null) {
                    v13_3 = "";
                }
                this.r = v13_3;
                androidx.lifecycle.A v13_4 = this.o;
                if (v13_4 == null) {
                    v13_4 = 0;
                }
                int v4_0;
                v13_4.n0(new sH.r(String.valueOf(this.q), this.r));
                k.e.a(this, new y0.b(new sH.g(this, 0), 1, 1743381033));
                androidx.lifecycle.A v13_7 = this.o;
                if (v13_7 == null) {
                    v4_0 = 0;
                } else {
                    v4_0 = v13_7;
                }
                androidx.lifecycle.x v3_1 = this.getLifecycle();
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v3_1), 0, 0, new rx.b(v3_1, v4_0, 0, this, 3), 3, 0);
                return;
            }
        }
    }

    public final void onDestroy()
    {
        kotlinx.coroutines.Job v0 = this.p;
        if (v0 != null) {
            v0.cancel(0);
        }
        super.onDestroy();
        return;
    }

    public final void onNewIntent(android.content.Intent p5)
    {
        int v0_0 = this.i;
        super.onNewIntent(p5);
        if ((!kotlin.jvm.internal.Intrinsics.areEqual("android.nfc.action.TAG_DISCOVERED", p5.getAction())) && (!kotlin.jvm.internal.Intrinsics.areEqual("android.nfc.action.TECH_DISCOVERED", p5.getAction()))) {
            super.onNewIntent(p5);
            return;
        } else {
            sH.x v5_2;
            sH.x v5_1 = p5.getExtras();
            if (v5_1 == null) {
                v5_2 = 0;
            } else {
                v5_2 = ((android.nfc.Tag) p9.M.a(v5_1, "android.nfc.extra.TAG", android.nfc.Tag));
            }
            try {
                int v0_4 = p9.w5.a(((vn.com.vng.zalopay.nfc.argument.BankNFCArgument) v0_0.getValue()).getLastIdNumber(), ((vn.com.vng.zalopay.nfc.argument.BankNFCArgument) v0_0.getValue()).getDob(), ((vn.com.vng.zalopay.nfc.argument.BankNFCArgument) v0_0.getValue()).getExpiryDate());
                sH.k v1_4 = new io.reactivex.internal.operators.observable.f1(12, this);
                this.p = p9.w5.b(this, v5_2, v0_4, v1_4);
                return;
            } catch (Throwable) {
                sH.x v5_5 = this.o;
                if (v5_5 != null) {
                    v1_4 = v5_5;
                }
                v1_4.n0(new sH.x(-8));
                return;
            }
        }
    }

    public final void onPause()
    {
        super.onPause();
        android.nfc.NfcAdapter v0_0 = this.p;
        if (v0_0 != null) {
            v0_0.cancel(0);
        }
        android.nfc.NfcAdapter v0_1 = android.nfc.NfcAdapter.getDefaultAdapter(this);
        this.j = v0_1;
        if (v0_1 != null) {
            v0_1.disableForegroundDispatch(this);
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        sH.n v0_5 = android.nfc.NfcAdapter.getDefaultAdapter(this);
        this.j = v0_5;
        sH.k v1 = 0;
        if (v0_5 != null) {
            if ((v0_5 == null) || (v0_5.isEnabled())) {
                sH.n v0_0 = this.o;
                if (v0_0 == null) {
                    v0_0 = 0;
                }
                String v2_0 = this.o;
                if (v2_0 == null) {
                    v2_0 = 0;
                }
                String v3_0 = this.o;
                if (v3_0 == null) {
                    v3_0 = 0;
                }
                if ((!kotlin.text.StringsKt.isBlank(((sH.L) v0_0.h0()).a.b)) && ((!kotlin.text.StringsKt.isBlank(((sH.L) v2_0.h0()).a.c)) && (!kotlin.text.StringsKt.isBlank(((sH.L) v3_0.h0()).a.d)))) {
                    sH.n v0_9 = this.o;
                    if (v0_9 != null) {
                        v1 = v0_9;
                    }
                    v1.n0(sH.n.a);
                }
                return;
            } else {
                sH.n v0_11 = this.o;
                if (v0_11 != null) {
                    v1 = v0_11;
                }
                v1.n0(sH.m.a);
                return;
            }
        } else {
            sH.n v0_13 = this.o;
            if (v0_13 != null) {
                v1 = v0_13;
            }
            v1.n0(sH.l.a);
            return;
        }
    }
}

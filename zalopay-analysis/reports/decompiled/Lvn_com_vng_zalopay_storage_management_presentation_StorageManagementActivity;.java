package vn.com.vng.zalopay.storage_management.presentation;
public final class StorageManagementActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int l;
    public jO.a i;
    public dagger.e j;
    public final O5.b k;

    public StorageManagementActivity()
    {
        this.k = new O5.b(kotlin.jvm.internal.M.a.b(fO.g), new fO.b(this, 0), new fO.a(this, 0), new fO.b(this, 1));
        return;
    }

    public final void onCreate(android.os.Bundle p12)
    {
        YW.h v12_2;
        super.onCreate(p12);
        YW.h v12_1 = p9.h7.a;
        if (v12_1 != null) {
            int v1_10 = new an.b(v12_1);
            v12_2 = new androidx.media3.container.l(22, 0);
            int v1_0 = dagger.internal.f.b(new WH.e(13, v1_10));
            v12_2.b = v1_0;
            v12_2.c = new eH.e(v1_0, 6);
        } else {
            v12_2 = 0;
        }
        if (v12_2 == null) {
            this.finish();
            return;
        } else {
            int v1_13;
            this.i = ((jO.a) ((dagger.internal.o) v12_2.b).get());
            this.j = dagger.internal.f.a(((eH.e) v12_2.c));
            androidx.activity.q.a(this, 0, 3);
            YW.h v12_8 = kotlin.text.StringsKt.y(android.text.format.Formatter.formatFileSize(this, q9.a6.a(this)), new String[] {" "}), 0, 6);
            YW.h v12_10 = ((String) v12_8.get(1));
            int v1_12 = java.text.NumberFormat.getInstance(java.util.Locale.getDefault()).parse(((String) v12_8.get(0)));
            if (v1_12 == 0) {
                v1_13 = 0;
            } else {
                v1_13 = v1_12.floatValue();
            }
            fO.g v3_7 = ((fO.g) this.k.getValue());
            hO.b v4_1 = new hO.b(v1_13, v12_10);
            v3_7.getClass();
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v3_7), 0, 0, new androidx.lifecycle.m(v3_7, v4_1, 0, 16), 3, 0);
            k.e.a(this, new y0.b(new YW.h(16, this), 1, -1007604933));
            return;
        }
    }
}

package vn.zalopay.ui.um_custom_loading;
public final class CenterLoadingActivity extends androidx.activity.ComponentActivity {
    public kotlinx.coroutines.Job i;

    public CenterLoadingActivity()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p7)
    {
        super.onCreate(p7);
        k.e.a(this, s00.b.a);
        this.i = kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getMain()), 0, 0, new cV.Y(this, 0, 18), 3, 0);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        s00.a.a.a();
        kotlinx.coroutines.Job v0_1 = this.i;
        if (v0_1 != null) {
            v0_1.cancel(0);
        }
        return;
    }
}

package vn.com.vng.zalopay.core.ui.activity;
public abstract class BaseFeatureActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int i;

    public BaseFeatureActivity()
    {
        return;
    }

    public static void e1(vn.com.vng.zalopay.core.ui.activity.BaseFeatureActivity p2)
    {
        p9.U.a(p2.getWindow(), 0);
        p2.getWindow().setStatusBarColor(0);
        p2.getWindow().setNavigationBarColor(0);
        return;
    }

    public final void g1()
    {
        androidx.appcompat.app.o v0_1 = new androidx.appcompat.app.o(this);
        androidx.appcompat.app.l v1 = v0_1.a;
        v1.f = "H\u1ec7 th\u1ed1ng \u0111\u00e3 l\u00e0m m\u1edbi \u1ee9ng d\u1ee5ng \u0111\u1ec3 ti\u1ebft ki\u1ec7m t\u00e0i nguy\u00ean. Vui l\u00f2ng th\u1eed l\u1ea1i t\u00ednh n\u0103ng t\u1eeb \u0111\u1ea7u.";
        int v2_3 = new Ss.a(0, this);
        v1.g = "OK";
        v1.h = v2_3;
        v1.k = 0;
        v0_1.a();
        return;
    }
}

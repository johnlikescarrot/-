package vn.com.vng.zalopay.app_widget.presentation.ui.movie;
public final class OpenMovieWidgetAction implements C2.a {
    public static final A2.c a;
    public static final A2.c b;
    public static final A2.c c;

    static OpenMovieWidgetAction()
    {
        vn.com.vng.zalopay.app_widget.presentation.ui.movie.OpenMovieWidgetAction.a = new A2.c(wmoi.igob.ggcn(1809));
        vn.com.vng.zalopay.app_widget.presentation.ui.movie.OpenMovieWidgetAction.b = new A2.c(wmoi.igob.ggcn(1810));
        vn.com.vng.zalopay.app_widget.presentation.ui.movie.OpenMovieWidgetAction.c = new A2.c(wmoi.igob.ggcn(1811));
        return;
    }

    public OpenMovieWidgetAction()
    {
        return;
    }

    public final Object a(android.content.Context p5, A2.f p6, kotlin.coroutines.Continuation p7)
    {
        int v6_1 = p6.a;
        String v7_2 = ((String) v6_1.get(vn.com.vng.zalopay.app_widget.presentation.ui.movie.OpenMovieWidgetAction.b));
        int v0_0 = "";
        if (v7_2 == null) {
            v7_2 = "";
        }
        String v1_1 = ((Integer) v6_1.get(vn.com.vng.zalopay.app_widget.presentation.ui.movie.OpenMovieWidgetAction.a));
        if (v1_1 != null) {
            String v1_2 = v1_1.toString();
            if (v1_2 != null) {
                v0_0 = v1_2;
            }
        }
        int v6_4;
        int v6_3 = ((Boolean) v6_1.get(vn.com.vng.zalopay.app_widget.presentation.ui.movie.OpenMovieWidgetAction.c));
        if (v6_3 == 0) {
            v6_4 = 0;
        } else {
            v6_4 = v6_3.booleanValue();
        }
        int v0_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(wmoi.igob.ggcn(1812), qn.b.e), kotlin.TuplesKt.to(wmoi.igob.ggcn(1813), v0_0)}));
        kotlin.collections.a0.plus(v0_3, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(1814), wmoi.igob.ggcn(1815))));
        java.util.Objects.toString(v0_3);
        UR.a.e(wmoi.igob.ggcn(1816), v0_3);
        try {
            if (v7_2.length() > 0) {
                q9.r6.b(p5, v7_2);
            }
        } catch (Exception) {
        }
        if (v6_4 != 0) {
            q9.w5.c(p5);
        }
        return kotlin.Unit.INSTANCE;
    }
}

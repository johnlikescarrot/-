package vn.com.vng.zalopay.app_widget.presentation.ui.movie;
public final class MovieAppWidgetReceiver extends androidx.glance.appwidget.GlanceAppWidgetReceiver {
    public final ln.d b;

    public MovieAppWidgetReceiver()
    {
        this.b = new ln.d();
        return;
    }

    public final B2.K b()
    {
        return this.b;
    }

    public final void onDisabled(android.content.Context p2)
    {
        super.onDisabled(p2);
        if (p2 != null) {
            V3.l.e(p2).b(wmoi.igob.ggcn(6543));
        }
        return;
    }

    public final void onEnabled(android.content.Context p7)
    {
        if (p7 != null) {
            q9.w5.c(p7);
        }
        if (p7 != null) {
            androidx.work.A v0_7 = new androidx.work.f();
            V3.l v1_2 = new androidx.work.d();
            v1_2.a = 1;
            v1_2.f = -1;
            v1_2.g = -1;
            new java.util.HashSet();
            v1_2.b = 0;
            v1_2.c = 0;
            v1_2.a = 2;
            v1_2.d = 0;
            v1_2.e = 0;
            v1_2.h = v0_7;
            v1_2.f = -1;
            v1_2.g = -1;
            V3.l.e(p7).c(wmoi.igob.ggcn(6544), ((androidx.work.A) ((androidx.work.z) new androidx.work.z(vn.com.vng.zalopay.app_widget.worker_manager.AppWidgetFetchMoviesWorker, 12).e(v1_2)).b()));
        }
        super.onEnabled(p7);
        return;
    }

    public final void onUpdate(android.content.Context p1, android.appwidget.AppWidgetManager p2, int[] p3)
    {
        q9.w5.c(p1);
        super.onUpdate(p1, p2, p3);
        return;
    }
}

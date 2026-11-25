package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final synthetic class Y0 implements kotlin.jvm.functions.Function1 {
    public static final String amco = "DELETE FROM micro_app_version";
    public static final String nvis = "[Application] Load resource app 1 error";
    public static final String qcicisy = "footerLayout";
    public static final String qjilw = "headerGroup";
    public static final String qzsri = "[Application] Load resource error";
    public static final String rqtudsd = "DELETE FROM MiniAppInfoNavBar";
    public static final String vbutr = "DELETE FROM MiniAppInfoGroup";
    public static final String zpab;
    public final synthetic int a;

    static Y0()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0, 19117, 19117);
        return;
    }

    public synthetic Y0(int p1)
    {
        this.a = p1;
        return;
    }

    public synthetic Y0(xu.h p1)
    {
        this.a = 17;
        return;
    }

    public final Object invoke(Object p15)
    {
        java.util.List v3_14 = vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.qjilw;
        int v4_19 = vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.qcicisy;
        int v5_7 = 0;
        switch (this.a) {
            case 0:
                return ((BB.k) p15).b.a;
            case 1:
                return ((BB.U) p15).a;
            case 2:
                return ((FB.e) p15).getTitle();
            case 3:
                ft.o v15_37 = ((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p15).a);
                long v1_4 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v15_37, 10));
                ft.o v15_38 = v15_37.iterator();
                while (v15_38.hasNext()) {
                    BB.z v2_7 = ((BB.F) v15_38.next());
                    if ((v2_7 instanceof BB.z)) {
                        BB.z v2_8 = ((BB.z) v2_7);
                        java.util.List v3_12 = v2_8.c;
                        v2_7 = new BB.z(v2_8.a, v2_8.b, new GB.a((v3_12.b + 1), v3_12.a), v2_8.d, v2_8.e, v2_8.f);
                    }
                    v1_4.add(v2_7);
                }
                return vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p15), v1_4, 0, 0, 0, 0, 0, 0, 254);
            case 4:
                long v1_2 = new Object[0];
                ck.b.c(((Throwable) p15), vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.qzsri, v1_2);
                return kotlin.Unit.INSTANCE;
            case 5:
                return kotlin.Unit.INSTANCE;
            case 6:
                long v1_1 = new Object[0];
                ck.b.c(((Throwable) p15), vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.nvis, v1_1);
                return kotlin.Unit.INSTANCE;
            case 7:
                ((K0.G) p15).r(1);
                return kotlin.Unit.INSTANCE;
            case 8:
                ((G1.p) p15).getClass();
                Throwable v0_16 = new G1.d(v4_19);
                int v4_9 = new G1.d(v3_14);
                java.util.List v3_6 = new G1.c(v4_9.b, ((G1.p) p15).b(v4_9));
                int v4_11 = v3_6.c;
                G1.y.c(v3_6.e, v4_11.d, 0, 6);
                G1.J.g(v3_6.d, v4_11.c, 0, 6);
                G1.J.g(v3_6.f, v4_11.e, 0, 6);
                java.util.List v3_9 = new G1.c(v0_16.b, ((G1.p) p15).b(v0_16));
                ft.o v15_25 = v3_9.c;
                G1.J.g(v3_9.d, v15_25.c, 0, 6);
                G1.J.g(v3_9.f, v15_25.e, 0, 6);
                G1.y.c(v3_9.g, v15_25.f, 0, 6);
                return kotlin.Unit.INSTANCE;
            case 9:
                return kotlin.Unit.INSTANCE;
            case 10:
                ft.o v15_19 = ((B3.b) p15).N1(vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.amco);
                try {
                    v15_19.K1();
                    v15_19.close();
                    ft.o v15_20 = kotlin.Unit.INSTANCE;
                    return v15_20;
                } catch (Throwable v0_14) {
                    v15_20.close();
                    throw v0_14;
                }
            case 11:
                ft.o v15_15 = ((B3.b) p15).N1(vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.rqtudsd);
                try {
                    v15_15.K1();
                    v15_15.close();
                    ft.o v15_16 = kotlin.Unit.INSTANCE;
                    return v15_16;
                } catch (Throwable v0_12) {
                    v15_16.close();
                    throw v0_12;
                }
            case 12:
                ft.o v15_12 = ((B3.b) p15).N1(vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.vbutr);
                try {
                    v15_12.K1();
                    v15_12.close();
                    ft.o v15_13 = kotlin.Unit.INSTANCE;
                    return v15_13;
                } catch (Throwable v0_10) {
                    v15_13.close();
                    throw v0_10;
                }
            case 13:
                ft.o v15_9 = ((B3.b) p15).N1(vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0.zpab);
                try {
                    v15_9.K1();
                    v15_9.close();
                    ft.o v15_10 = kotlin.Unit.INSTANCE;
                    return v15_10;
                } catch (Throwable v0_8) {
                    v15_10.close();
                    throw v0_8;
                }
            case 14:
                ((G1.p) p15).getClass();
                Throwable v0_2 = new G1.d(v4_19);
                int v4_1 = new G1.d(v3_14);
                java.util.List v3_1 = new G1.c(v4_1.b, ((G1.p) p15).b(v4_1));
                int v4_3 = v3_1.c;
                G1.y.c(v3_1.e, v4_3.d, 0, 6);
                G1.J.g(v3_1.d, v4_3.c, 0, 6);
                G1.J.g(v3_1.f, v4_3.e, 0, 6);
                java.util.List v3_4 = new G1.c(v0_2.b, ((G1.p) p15).b(v0_2));
                ft.o v15_4 = v3_4.c;
                G1.J.g(v3_4.d, v15_4.c, 0, 6);
                G1.J.g(v3_4.f, v15_4.e, 0, 6);
                G1.y.c(v3_4.g, v15_4.f, 0, 6);
                return kotlin.Unit.INSTANCE;
            case 15:
                return nY.t.b(((Ew.k) p15));
            case 16:
                return nY.t.b(((Ew.k) p15));
            case 17:
                ft.o v15_74;
                if (((Rt.g) p15) != null) {
                    Throwable v0_26 = new Ew.k();
                    v0_26.d = "";
                    v0_26.a = ((Rt.g) p15).d;
                    v0_26.c = ((Rt.g) p15).b;
                    v0_26.b = ((Rt.g) p15).g.longValue();
                    v0_26.d = ((Rt.g) p15).c;
                    v15_74 = v0_26;
                } else {
                    v15_74 = 0;
                }
                return v15_74;
            case 18:
                if (!((Boolean) p15).booleanValue()) {
                    yX.a.a();
                }
                return kotlin.Unit.INSTANCE;
            case 19:
                return ((kotlin.text.MatchResult) p15).getValue();
            case 20:
                if (((String) p15).length() > 0) {
                    v5_7 = 1;
                }
                return Boolean.valueOf(v5_7);
            case 21:
                try {
                    ft.o v15_62 = ((ft.o) nY.h.b(((String) p15), ft.o));
                } catch (Exception) {
                    v15_62 = ft.o.o;
                }
                return v15_62;
            case 22:
                return Boolean.valueOf((android.text.TextUtils.isEmpty(((ft.o) p15).a) ^ 1));
            case 23:
                return Boolean.valueOf((((java.util.Collection) ((java.util.List) p15)).isEmpty() ^ 1));
            case 24:
                return Boolean.FALSE;
            case 25:
                ((Boolean) p15).booleanValue();
                return ((Boolean) p15);
            case 26:
                return kotlin.collections.E.emptyList();
            case 27:
                return ((gf.A) p15).delay(1000, java.util.concurrent.TimeUnit.MILLISECONDS);
            case 28:
                return ((Boolean) ((nY.t) p15).a());
            default:
                return ((Iterable) ((java.util.List) p15));
        }
    }
}

package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class b1 implements ji.j {
    public final synthetic int a;
    public final synthetic ji.j b;
    public final synthetic vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 c;

    public synthetic b1(ji.j p1, vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final Object emit(Object p39, kotlin.coroutines.Continuation p40)
    {
        switch (this.a) {
            case 0:
                String v2_2;
                if (!(p40 instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.a1)) {
                    v2_2 = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.a1(this, p40);
                } else {
                    v2_2 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.a1) p40);
                    BB.q v4_0 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.a1) p40).e;
                    if ((v4_0 & -2147483648) == 0) {
                    } else {
                        ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.a1) p40).e = (v4_0 - -2147483648);
                    }
                }
                kotlin.NoWhenBranchMatchedException v1_10 = v2_2.d;
                BB.q v4_12 = vg.i.getCOROUTINE_SUSPENDED();
                BB.E v5_5 = v2_2.e;
                if (v5_5 == null) {
                    kotlin.ResultKt.throwOnFailure(v1_10);
                    kotlin.NoWhenBranchMatchedException v1_13 = this.b;
                    BB.E v5_11 = new Ai.D();
                    this.c.N = v5_11;
                    this.c.z.b(vn.com.vng.zalopay.homev5.presenter.ui.home_page.d1.a);
                    FB.d v7_35 = new h3.s0(new h3.x0(new CA.X(new h3.x0(((h3.s0) p39).a, new ey.c(this.c, v5_11, 0, 19)), new CS.h(2, 13, 0), 4), new pI.c(this.c, 0, 19)), ((h3.s0) p39).b, ((h3.s0) p39).c);
                    v2_2.e = 1;
                    if (v1_13.emit(v7_35, v2_2) != v4_12) {
                        v4_12 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v5_5 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(v1_10);
                    }
                }
                return v4_12;
            default:
                h3.x0 v8_5;
                String v2_52 = this.c;
                if (!(p40 instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.e1)) {
                    v8_5 = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.e1(this, p40);
                } else {
                    v8_5 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.e1) p40);
                    FB.d v9_39 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.e1) p40).e;
                    if ((v9_39 & -2147483648) == 0) {
                    } else {
                        ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.e1) p40).e = (v9_39 - -2147483648);
                    }
                }
                BB.z v6_0;
                FB.d v9_0;
                String v2_1;
                int v3_0;
                ji.j v11;
                kotlin.NoWhenBranchMatchedException v1_2 = v8_5.d;
                BB.z v6_20 = vg.i.getCOROUTINE_SUSPENDED();
                FB.d v9_41 = v8_5.e;
                if (v9_41 == null) {
                    kotlin.ResultKt.throwOnFailure(v1_2);
                    v11 = this.b;
                    v9_0 = ((ng.L) p39).a;
                    kotlin.NoWhenBranchMatchedException v1_25 = v2_52.p;
                    v8_5.g = v11;
                    v8_5.h = v9_0;
                    v8_5.i = 0;
                    v8_5.e = 1;
                    v1_2 = v1_25.a.f();
                    if (v1_2 != v6_20) {
                        v3_0 = 0;
                        boolean v14 = ((Boolean) v1_2).booleanValue();
                        if (!(v9_0 instanceof ng.K)) {
                            FB.d v9_2 = ((Iterable) ((java.util.List) v9_0));
                            String v12_0 = 10;
                            kotlin.NoWhenBranchMatchedException v1_6 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v9_2, 10));
                            FB.d v9_3 = v9_2.iterator();
                            while (v9_3.hasNext()) {
                                BB.q v4_3;
                                BB.B v28;
                                BB.V v18_0;
                                BB.z v19_1;
                                int v13_3 = ((fA.T) v9_3.next());
                                String v15_1 = v2_52.k;
                                FB.d v7_0 = v15_1.e;
                                BB.Q v10_0 = v15_1.l;
                                if (!v13_3.b()) {
                                    if (!(v13_3 instanceof fA.z)) {
                                        if (!(v13_3 instanceof fA.v)) {
                                            BB.V v18_7;
                                            if (!(v13_3 instanceof fA.o)) {
                                                if (!(v13_3 instanceof fA.A)) {
                                                    if (!(v13_3 instanceof fA.P)) {
                                                        FB.d v7_46;
                                                        v28 = v2_52;
                                                        if (!(v13_3 instanceof fA.n)) {
                                                            v18_0 = v9_3;
                                                            if (!(v13_3 instanceof fA.q)) {
                                                                if (!(v13_3 instanceof fA.C)) {
                                                                    BB.z v19_4;
                                                                    if (!(v13_3 instanceof fA.L)) {
                                                                        if (!(v13_3 instanceof fA.E)) {
                                                                            if (!(v13_3 instanceof fA.G)) {
                                                                                if (!(v13_3 instanceof fA.I)) {
                                                                                    v19_1 = v6_20;
                                                                                    if (!(v13_3 instanceof fA.u)) {
                                                                                        if (!(v13_3 instanceof fA.S)) {
                                                                                            if (!(v13_3 instanceof fA.y)) {
                                                                                                throw new kotlin.NoWhenBranchMatchedException();
                                                                                            } else {
                                                                                                int v13_4 = ((fA.y) v13_3);
                                                                                                String v2_16 = v13_4.a;
                                                                                                BB.q v4_11 = v13_4.d;
                                                                                                BB.E v5_4 = v13_4.c;
                                                                                                BB.z v6_1 = v13_4.e;
                                                                                                FB.d v7_2 = ((Iterable) v13_4.f);
                                                                                                FB.d v9_5 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v7_2, 10));
                                                                                                FB.d v7_3 = v7_2.iterator();
                                                                                                while (v7_3.hasNext()) {
                                                                                                    String v12_4 = ((fA.x) v7_3.next());
                                                                                                    String v16_2 = v2_16;
                                                                                                    v9_5.add(new BB.N(v12_4.a, v12_4.b, v12_4.c, v12_4.d, v12_4.e, v12_4.f, v12_4.g));
                                                                                                    v2_16 = v16_2;
                                                                                                }
                                                                                                String v16_1 = v2_16;
                                                                                                String v2_17 = v13_4.g;
                                                                                                v15_1.f.getClass();
                                                                                                v4_3 = new BB.q(v4_11, v5_4, v6_1, v9_5, new EB.a(v2_17.a), v16_1);
                                                                                                v1_6.add(v4_3);
                                                                                                v9_3 = v18_0;
                                                                                                v6_20 = v19_1;
                                                                                                v2_52 = v28;
                                                                                                FB.d v7 = 2;
                                                                                                v12_0 = 10;
                                                                                            }
                                                                                        } else {
                                                                                            int v13_5 = ((fA.S) v13_3);
                                                                                            String v2_25 = v13_5.a;
                                                                                            BB.q v4_13 = v13_5.c;
                                                                                            BB.z v6_2 = v13_5.d;
                                                                                            FB.d v7_8 = ((Iterable) v13_5.e);
                                                                                            FB.d v9_7 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v7_8, 10));
                                                                                            FB.d v7_9 = v7_8.iterator();
                                                                                            while (v7_9.hasNext()) {
                                                                                                String v12_9 = ((fA.Q) v7_9.next());
                                                                                                v9_7.add(new BB.U(v12_9.a, v12_9.f, v12_9.d, v12_9.e, v12_9.b, v12_9.c, v12_9.g, v12_9.h));
                                                                                                BB.Q v10 = 10;
                                                                                            }
                                                                                            v4_3 = new BB.E(v4_13, v6_2, v2_25, v9_7);
                                                                                        }
                                                                                    } else {
                                                                                        int v13_7 = ((fA.u) v13_3);
                                                                                        String v2_26 = v13_7.a;
                                                                                        BB.z v6_5 = ((Iterable) v13_7.c);
                                                                                        FB.d v7_11 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v6_5, 10));
                                                                                        BB.z v6_6 = v6_5.iterator();
                                                                                        while (v6_6.hasNext()) {
                                                                                            int v13_8;
                                                                                            FB.d v9_11 = ((fA.t) v6_6.next());
                                                                                            String v12_13 = ((fA.s) kotlin.collections.CollectionsKt.firstOrNull(v9_11.b));
                                                                                            if (v12_13 == null) {
                                                                                                v13_8 = 0;
                                                                                            } else {
                                                                                                v13_8 = v12_13.a;
                                                                                            }
                                                                                            int v13_9;
                                                                                            String v21_2 = String.valueOf(v13_8);
                                                                                            if (v12_13 == null) {
                                                                                                v13_9 = 0;
                                                                                            } else {
                                                                                                v13_9 = v12_13.c;
                                                                                            }
                                                                                            String v12_14;
                                                                                            String v22_2 = String.valueOf(v13_9);
                                                                                            if (v12_13 == null) {
                                                                                                v12_14 = 0;
                                                                                            } else {
                                                                                                v12_14 = v12_13.d;
                                                                                            }
                                                                                            int v23_2;
                                                                                            if (v12_14 != null) {
                                                                                                v23_2 = v12_14;
                                                                                            } else {
                                                                                                v23_2 = "";
                                                                                            }
                                                                                            FB.d v9_12 = v9_11.a;
                                                                                            v7_11.add(new BB.T(v21_2, v22_2, v23_2, v9_12.a, v9_12.b));
                                                                                        }
                                                                                        v4_3 = new BB.D(v2_26, v7_11);
                                                                                    }
                                                                                } else {
                                                                                    int v13_10 = ((fA.I) v13_3);
                                                                                    String v2_28 = ((Iterable) v13_10.d);
                                                                                    BB.E v5_16 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v2_28, 10));
                                                                                    String v2_29 = v2_28.iterator();
                                                                                    while (v2_29.hasNext()) {
                                                                                        FB.d v7_21;
                                                                                        String v16_4;
                                                                                        BB.z v19_2;
                                                                                        FB.a v17_2;
                                                                                        FB.d v9_19 = ((fA.n0) v2_29.next());
                                                                                        if (!(v9_19 instanceof fA.l0)) {
                                                                                            v16_4 = v2_29;
                                                                                            v19_2 = v6_20;
                                                                                            v17_2 = v7_0;
                                                                                            if (!(v9_19 instanceof fA.m0)) {
                                                                                                throw new kotlin.NoWhenBranchMatchedException();
                                                                                            } else {
                                                                                                int v23_4;
                                                                                                FB.d v9_20 = ((fA.m0) v9_19);
                                                                                                String v2_32 = v9_20.a;
                                                                                                BB.z v6_14 = v9_20.b;
                                                                                                FB.d v7_16 = v9_20.c;
                                                                                                if (v7_16 == null) {
                                                                                                    v23_4 = 0;
                                                                                                } else {
                                                                                                    v15_1.i.getClass();
                                                                                                    v23_4 = kc.b.a(v7_16);
                                                                                                }
                                                                                                FB.d v9_21 = v9_20.e;
                                                                                                v7_21 = new FB.d(v2_32, v6_14, v23_4, v9_20.d, 0, new BB.Q(((fA.k0) v9_21.b), ((String) v9_21.c)));
                                                                                            }
                                                                                        } else {
                                                                                            String v2_34;
                                                                                            FB.d v9_24 = ((fA.l0) v9_19);
                                                                                            String v12_23 = v9_24.a;
                                                                                            BB.Q v10_10 = v9_24.b;
                                                                                            v16_4 = v2_29;
                                                                                            String v2_33 = v9_24.c;
                                                                                            v19_2 = v6_20;
                                                                                            v17_2 = v7_0;
                                                                                            if (v2_33 == null) {
                                                                                                v2_34 = 0;
                                                                                            } else {
                                                                                                String v2_36 = v15_1.h.b(v2_33.longValue());
                                                                                                if ((v2_36 instanceof ng.K)) {
                                                                                                    v2_36 = "";
                                                                                                }
                                                                                                v2_34 = ((String) v2_36);
                                                                                            }
                                                                                            FB.d v7_22 = v9_24.d;
                                                                                            v7_21 = new FB.b(v12_23, v10_10, new BB.Q(((fA.k0) v7_22.b), ((String) v7_22.c)), v2_34);
                                                                                        }
                                                                                        v5_16.add(v7_21);
                                                                                        v2_29 = v16_4;
                                                                                        v7_0 = v17_2;
                                                                                        v6_20 = v19_2;
                                                                                    }
                                                                                    String v15_3;
                                                                                    v19_1 = v6_20;
                                                                                    String v2_30 = v13_10.a;
                                                                                    BB.q v4_15 = v13_10.e;
                                                                                    if (v4_15 == null) {
                                                                                        v15_3 = 0;
                                                                                    } else {
                                                                                        BB.q v4_16 = v4_15.d;
                                                                                        v15_3 = new BB.w(v4_15.a, v4_15.b, v4_15.c, new BB.Q(((fA.k0) v4_16.b), ((String) v4_16.c)), v4_15.e);
                                                                                    }
                                                                                    BB.q v4_19 = v13_10.c;
                                                                                    v7_0.getClass();
                                                                                    v10 = 10;
                                                                                    v4_3 = new BB.x(v5_16, v14, v15_3, v2_30, new FB.a(v4_19.a, v4_19.b));
                                                                                }
                                                                            } else {
                                                                                int v13_12 = ((fA.G) v13_3);
                                                                                BB.E v5_20 = v13_12.e;
                                                                                FB.d v9_28 = v13_12.f;
                                                                                BB.Q v10_12 = v13_12.g;
                                                                                String v15_7 = v13_12.h;
                                                                                String v12_24 = v15_7.a;
                                                                                BB.Q v26_3 = v13_12.a;
                                                                                FB.d v20_10 = v13_12.d;
                                                                                String v2_39 = new BB.Q(v15_7.c, v15_7.b);
                                                                                BB.q v4_23 = v13_12.c;
                                                                                v7_0.getClass();
                                                                                v19_4 = new BB.v(v20_10, v5_20, v9_28, v10_12, v12_24, v2_39, v26_3, new FB.a(v4_23.a, v4_23.b));
                                                                            }
                                                                        } else {
                                                                            v10_0.getClass();
                                                                            v4_3 = iB.a.c();
                                                                            v19_1 = v6_20;
                                                                        }
                                                                    } else {
                                                                        String v2_40 = v15_1.g;
                                                                        int v13_14 = ((fA.L) v13_3);
                                                                        v2_40.getClass();
                                                                        BB.q v4_27 = ng.x.b(new AB.a(0));
                                                                        BB.E v5_22 = v13_14.a;
                                                                        FB.d v7_28 = v13_14.e;
                                                                        FB.d v9_30 = v13_14.c;
                                                                        BB.Q v10_14 = v13_14.f;
                                                                        String v12_26 = ((Iterable) v13_14.d);
                                                                        String v16_5 = ((d6.l) v2_40.b);
                                                                        int v13_16 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v12_26, 10));
                                                                        String v2_44 = v12_26.iterator();
                                                                        while (v2_44.hasNext()) {
                                                                            String v12_30 = ((fA.K) v2_44.next());
                                                                            v16_5.getClass();
                                                                            v13_16.add(d6.l.k(v12_30));
                                                                        }
                                                                        int v13_20;
                                                                        String v2_46 = new GB.a(0, v13_16);
                                                                        int v13_18 = ((fA.K) kotlin.collections.CollectionsKt.getOrNull(v7_28, 0));
                                                                        if (v13_18 == 0) {
                                                                            v13_20 = ((BB.S) v4_27.getValue());
                                                                        } else {
                                                                            v16_5.getClass();
                                                                            v13_20 = d6.l.k(v13_18);
                                                                        }
                                                                        BB.q v4_29;
                                                                        int v23_6 = v13_20;
                                                                        FB.d v7_31 = ((fA.K) kotlin.collections.CollectionsKt.getOrNull(v7_28, 1));
                                                                        if (v7_31 == null) {
                                                                            v4_29 = ((BB.S) v4_27.getValue());
                                                                        } else {
                                                                            v16_5.getClass();
                                                                            v4_29 = d6.l.k(v7_31);
                                                                        }
                                                                        v19_4 = new BB.z(v9_30, v10_14, v2_46, v23_6, v4_29, v5_22);
                                                                    }
                                                                    v4_3 = v19_4;
                                                                } else {
                                                                    int v13_22 = ((fA.C) v13_3);
                                                                    String v2_47 = v13_22.a;
                                                                    BB.q v4_31 = ((Iterable) v13_22.c);
                                                                    BB.E v5_24 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v4_31, 10));
                                                                    BB.q v4_32 = v4_31.iterator();
                                                                    while (v4_32.hasNext()) {
                                                                        FB.d v7_37 = ((fA.B) v4_32.next());
                                                                        v5_24.add(new BB.P(((int) v7_37.a), v7_37.b, v7_37.c, v7_37.d, 0));
                                                                    }
                                                                    v4_3 = new BB.u(v5_24, v2_47);
                                                                }
                                                            } else {
                                                                String v2_48 = v15_1.d;
                                                                int v13_24 = ((fA.q) v13_3);
                                                                v2_48.getClass();
                                                                BB.q v4_35 = ((Iterable) v13_24.d);
                                                                BB.E v5_26 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v4_35, 10));
                                                                BB.q v4_36 = v4_35.iterator();
                                                                while (v4_36.hasNext()) {
                                                                    String v24_8;
                                                                    FB.d v7_48 = ((fA.g) v4_36.next());
                                                                    FB.d v9_36 = ((int) v7_48.a);
                                                                    BB.Q v10_19 = v7_48.b;
                                                                    ((kc.b) v2_48.c).getClass();
                                                                    String v21_8 = kc.b.a(v10_19);
                                                                    BB.Q v10_20 = v7_48.c;
                                                                    String v12_35 = v7_48.e;
                                                                    String v15_10 = v7_48.d;
                                                                    String v16_6 = v4_36;
                                                                    if (v15_10 == null) {
                                                                        v24_8 = 0;
                                                                    } else {
                                                                        ((kX.d) v2_48.b).getClass();
                                                                        v24_8 = kX.d.c(v15_10);
                                                                    }
                                                                    v5_26.add(new BB.k(v9_36, v21_8, v10_20, v12_35, v24_8, 0, v7_48.g, 32));
                                                                    v4_36 = v16_6;
                                                                }
                                                                FB.d v7_43 = v13_24.c;
                                                                v7_46 = new BB.o(new BB.n(v7_43.a, v7_43.b, v7_43.c), v5_26, v13_24.a);
                                                            }
                                                        } else {
                                                            String v2_50 = v15_1.a;
                                                            int v13_26 = ((fA.n) v13_3);
                                                            v2_50.getClass();
                                                            BB.q v4_45 = ((Iterable) v13_26.d);
                                                            BB.E v5_28 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v4_45, 10));
                                                            BB.q v4_46 = v4_45.iterator();
                                                            while (v4_46.hasNext()) {
                                                                String v24_9;
                                                                FB.d v7_53 = ((fA.g) v4_46.next());
                                                                BB.V v18_1 = v9_3;
                                                                FB.d v9_38 = ((int) v7_53.a);
                                                                BB.Q v10_22 = v7_53.b;
                                                                ((kc.b) v2_50.c).getClass();
                                                                String v21_9 = kc.b.a(v10_22);
                                                                BB.Q v10_23 = v7_53.c;
                                                                String v12_38 = v7_53.e;
                                                                String v15_11 = v7_53.d;
                                                                String v16_7 = v4_46;
                                                                if (v15_11 == null) {
                                                                    v24_9 = 0;
                                                                } else {
                                                                    ((kX.d) v2_50.b).getClass();
                                                                    v24_9 = kX.d.c(v15_11);
                                                                }
                                                                v5_28.add(new BB.k(v9_38, v21_9, v10_23, v12_38, v24_9, 0, v7_53.g, 32));
                                                                v4_46 = v16_7;
                                                                v9_3 = v18_1;
                                                            }
                                                            v18_0 = v9_3;
                                                            v7_46 = new BB.l(v13_26.c, v5_28, v13_26.a);
                                                        }
                                                        v19_1 = v6_20;
                                                        v4_3 = v7_46;
                                                    } else {
                                                        int v13_27 = ((fA.P) v13_3);
                                                        v15_1.j.getClass();
                                                        BB.q v4_54 = v13_27.a;
                                                        BB.E v5_29 = v13_27.c;
                                                        v28 = v2_52;
                                                        String v2_55 = new BB.B(v5_29.b, v5_29.e, v5_29.f, v5_29.d, v5_29.c, v5_29.g, v5_29.a, 0, 264);
                                                        BB.E v5_32 = ((Iterable) v13_27.d);
                                                        FB.d v7_56 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v5_32, 10));
                                                        BB.E v5_33 = v5_32.iterator();
                                                        while (v5_33.hasNext()) {
                                                            int v13_30;
                                                            BB.Q v10_28 = ((fA.N) v5_33.next());
                                                            int v13_28 = v10_28.b;
                                                            String v15_13 = v10_28.c;
                                                            String v16_8 = v5_33;
                                                            BB.E v5_36 = v10_28.a;
                                                            String v12_42 = v10_28.e.ordinal();
                                                            if (v12_42 == null) {
                                                                v13_30 = new BB.X(v5_36, v15_13, v13_28, 0);
                                                            } else {
                                                                if (v12_42 != 1) {
                                                                    throw new kotlin.NoWhenBranchMatchedException();
                                                                } else {
                                                                    v13_30 = new BB.V(v5_36, v15_13, v13_28, v10_28.d, 0);
                                                                }
                                                            }
                                                            v7_56.add(v13_30);
                                                            v5_33 = v16_8;
                                                        }
                                                        v4_3 = new BB.C(v2_55, v7_56, v4_54);
                                                        v19_1 = v6_20;
                                                        v18_0 = v9_3;
                                                    }
                                                } else {
                                                    int v13_31 = ((fA.A) v13_3);
                                                    v18_7 = new BB.t(v13_31.c, v13_31.e, v13_31.d, 0, v13_31.a);
                                                }
                                            } else {
                                                int v13_32 = ((fA.o) v13_3);
                                                v15_1.k.getClass();
                                                v18_7 = new BB.m(v13_32.a, v13_32.c, v13_32.d, v13_32.e, v13_32.f, v13_32.g, v13_32.h);
                                            }
                                            v28 = v2_52;
                                            v19_1 = v6_20;
                                            v4_3 = v18_7;
                                        } else {
                                            BB.q v4_58 = v15_1.b;
                                            int v13_34 = ((fA.v) v13_3);
                                            v4_58.getClass();
                                            BB.E v5_42 = v13_34.a;
                                            FB.d v7_60 = ((Iterable) v13_34.c);
                                            BB.q v4_60 = ((h3.j) v4_58.a);
                                            BB.Q v10_34 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v7_60, v12_0));
                                            FB.d v7_61 = v7_60.iterator();
                                            while (v7_61.hasNext()) {
                                                v10_34.add(v4_60.j(((fA.g) v7_61.next())));
                                            }
                                            v4_3 = new BB.p(v10_34, v5_42);
                                            v28 = v2_52;
                                        }
                                    } else {
                                        BB.q v4_62 = v15_1.c;
                                        int v13_40 = ((fA.z) v13_3);
                                        v4_62.getClass();
                                        BB.E v5_44 = ((Iterable) v13_40.a);
                                        BB.q v4_64 = ((h3.j) v4_62.b);
                                        FB.d v7_63 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v5_44, v12_0));
                                        BB.E v5_45 = v5_44.iterator();
                                        while (v5_45.hasNext()) {
                                            v7_63.add(v4_64.j(((fA.g) v5_45.next())));
                                        }
                                        v4_3 = new BB.r(v7_63);
                                    }
                                } else {
                                    v4_3 = ((BB.F) ((java.util.Map) v10_0.b.getValue()).get(v13_3.a()));
                                    if (v4_3 == null) {
                                    }
                                }
                            }
                            v9_0 = v1_6;
                        }
                        BB.z v19_0 = v6_20;
                        kotlin.NoWhenBranchMatchedException v1_8 = new ng.L(v9_0);
                        v8_5.g = 0;
                        v8_5.h = 0;
                        v8_5.i = v3_0;
                        v8_5.e = 2;
                        v2_1 = v19_0;
                        if (v11.emit(v1_8, v8_5) != v2_1) {
                            v6_0 = kotlin.Unit.INSTANCE;
                        } else {
                            v6_0 = v2_1;
                        }
                    } else {
                        v2_1 = v6_20;
                    }
                } else {
                    if (v9_41 == 1) {
                        v3_0 = v8_5.i;
                        v9_0 = v8_5.h;
                        v11 = v8_5.g;
                        kotlin.ResultKt.throwOnFailure(v1_2);
                    } else {
                        if (v9_41 != 2) {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        } else {
                            kotlin.ResultKt.throwOnFailure(v1_2);
                        }
                    }
                }
                return v6_0;
        }
    }
}

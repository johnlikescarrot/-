package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class y0 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public final synthetic int d;
    public int e;
    public final synthetic vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 f;

    public synthetic y0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 p1, kotlin.coroutines.Continuation p2, int p3)
    {
        this.d = p3;
        this.f = p1;
        super(2, p2);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p3, kotlin.coroutines.Continuation p4)
    {
        switch (this.d) {
            case 0:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this.f, p4, 0);
            case 1:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this.f, p4, 1);
            case 2:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this.f, p4, 2);
            case 3:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this.f, p4, 3);
            case 4:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this.f, p4, 4);
            case 5:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this.f, p4, 5);
            default:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this.f, p4, 6);
        }
    }

    public final Object invoke(Object p2, Object p3)
    {
        switch (this.d) {
            case 0:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 1:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 2:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 3:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 4:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 5:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            default:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
        }
    }

    public final Object invokeSuspend(Object p19)
    {
        OB.d v5_0 = 0;
        int v6_1 = this.f;
        switch (this.d) {
            case 0:
                String v1_54 = vg.i.getCOROUTINE_SUSPENDED();
                String v3_35 = this.e;
                if (v3_35 == null) {
                    kotlin.ResultKt.throwOnFailure(p19);
                    CB.a v4_22 = new MD.l(10, ji.j0.r(q0.d.O(new mS.a(13, v6_1)), 1));
                    this.e = 1;
                    String v2_39 = ji.j0.w(v4_22, this);
                    if (v2_39 != v1_54) {
                        v1_54 = v2_39;
                    }
                } else {
                    if (v3_35 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p19);
                        v1_54 = p19;
                    }
                }
                return v1_54;
            case 1:
                String v1_40;
                String v1_39 = vg.i.getCOROUTINE_SUSPENDED();
                String v3_16 = this.e;
                if (v3_16 == null) {
                    kotlin.ResultKt.throwOnFailure(p19);
                    String v3_19 = ((nA.e) v6_1.w.a);
                    this.e = 1;
                    String v3_20 = v3_19.a(this);
                    if (v3_20 != v1_39) {
                        v1_40 = v3_20;
                        if (!(v1_40 instanceof ng.K)) {
                            String v1_46 = ((Iterable) ((java.util.List) v1_40));
                            String v3_24 = ((kO.t) v6_1.x.b);
                            CB.a v4_14 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v1_46, 10));
                            String v1_47 = v1_46.iterator();
                            while (v1_47.hasNext()) {
                                OB.h v7_17 = ((fA.f) v1_47.next());
                                v3_24.getClass();
                                v4_14.add(new CB.a(v7_17.a, 1));
                            }
                            v1_40 = v4_14;
                        }
                        String v3_25 = ng.L.a(v1_40);
                        if (v3_25 != null) {
                            ck.b.b(v3_25);
                        } else {
                            String v1_49 = ((Iterable) ((java.util.List) v1_40));
                            OB.d v11_7 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v1_49, 10));
                            String v1_50 = v1_49.iterator();
                            while (v1_50.hasNext()) {
                                String v2_36 = ((CB.a) v1_50.next());
                                String v3_32 = ((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v6_1.j0()).b).iterator();
                                while (v3_32.hasNext()) {
                                    CB.a v4_16 = v3_32.next();
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(((CB.a) v4_16).a, v2_36.a)) {
                                    }
                                    String v3_33;
                                    CB.a v4_17 = ((CB.a) v4_16);
                                    if (v4_17 == null) {
                                        v3_33 = 1;
                                    } else {
                                        v3_33 = (v4_17.b + 1);
                                    }
                                    v11_7.add(new CB.a(v2_36.a, v3_33));
                                }
                                v4_16 = 0;
                            }
                            v6_1.I.clear();
                            String v1_52 = v6_1.k0();
                            do {
                                String v2_33 = v1_52.getValue();
                            } while(!v1_52.c(v2_33, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v2_33), 0, v11_7, 0, 0, 0, 0, 0, 253)));
                        }
                        v1_39 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v3_16 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p19);
                        v1_40 = ((ng.L) p19).a;
                    }
                }
                return v1_39;
            case 2:
                String v1_26;
                String v1_25 = vg.i.getCOROUTINE_SUSPENDED();
                String v2_22 = this.e;
                if (v2_22 == null) {
                    kotlin.ResultKt.throwOnFailure(p19);
                    String v2_23 = v6_1.t;
                    this.e = 1;
                    String v2_24 = v2_23.a(this);
                    if (v2_24 != v1_25) {
                        v1_26 = v2_24;
                        String v2_26 = ng.L.a(v1_26);
                        if (v2_26 != null) {
                            ck.b.b(v2_26);
                        } else {
                            String v1_31 = ((fA.U) v1_26);
                            if (v1_31 != null) {
                                v6_1.l.getClass();
                                v5_0 = new BB.G(v1_31.a, v1_31.b, v1_31.c, v1_31.d);
                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v5_0, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v6_1.j0()).f)) {
                                    v6_1.w0(sA.a.W, v5_0);
                                }
                            }
                            OB.i v13_4 = v5_0;
                            String v1_38 = v6_1.k0();
                            do {
                                String v2_29 = v1_38.getValue();
                            } while(!v1_38.c(v2_29, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v2_29), 0, 0, 0, 0, 0, v13_4, 0, 223)));
                        }
                        v1_25 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v2_22 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p19);
                        v1_26 = ((ng.L) p19).a;
                    }
                }
                return v1_25;
            case 3:
                String v1_2;
                String v1_3 = vg.i.getCOROUTINE_SUSPENDED();
                String v2_49 = this.e;
                if (v2_49 == null) {
                    kotlin.ResultKt.throwOnFailure(p19);
                    if (!v6_1.F) {
                        String v2_51 = v6_1.v;
                        this.e = 1;
                        String v2_0 = v2_51.a(this);
                        if (v2_0 != v1_3) {
                            v1_2 = v2_0;
                            String v2_2 = ng.L.a(v1_2);
                            if (v2_2 != null) {
                                ck.b.b(v2_2);
                            } else {
                                String v1_8 = ((lA.j) v1_2);
                                if (v1_8 != null) {
                                    OB.h v7_1;
                                    String v2_3 = v6_1.m;
                                    if (!(v1_8 instanceof lA.b)) {
                                        if (!(v1_8 instanceof lA.g)) {
                                            if (!(v1_8 instanceof lA.h)) {
                                                v2_3.getClass();
                                                throw new kotlin.NoWhenBranchMatchedException();
                                            } else {
                                                OB.d v11_0;
                                                String v2_4 = v2_3.b;
                                                String v1_11 = ((lA.h) v1_8);
                                                String v3_4 = ((uc.i) v2_4.b);
                                                String v8_0 = v1_11.a;
                                                String v9_0 = v1_11.b;
                                                String v10_0 = v1_11.c;
                                                CB.a v4_0 = v1_11.d;
                                                if (v4_0 == null) {
                                                    v11_0 = 0;
                                                } else {
                                                    v11_0 = v3_4.M(v4_0);
                                                }
                                                CB.a v4_2 = v1_11.e;
                                                if (v4_2 != null) {
                                                    v5_0 = v3_4.M(v4_2);
                                                }
                                                OB.d v12_0 = v5_0;
                                                String v1_12 = v1_11.f;
                                                ((Ac.a) v2_4.c).getClass();
                                                v7_1 = new OB.h(v8_0, v9_0, v10_0, v11_0, v12_0, new OB.i(v1_12.a, v1_12.b, v1_12.c));
                                            }
                                        } else {
                                            String v10_1;
                                            String v2_8 = v2_3.c;
                                            String v1_14 = ((lA.g) v1_8);
                                            String v3_7 = ((uc.i) v2_8.b);
                                            String v8_1 = v1_14.a;
                                            String v9_1 = v1_14.b;
                                            CB.a v4_3 = v1_14.c;
                                            if (v4_3 == null) {
                                                v10_1 = 0;
                                            } else {
                                                v10_1 = v3_7.M(v4_3);
                                            }
                                            CB.a v4_5 = v1_14.d;
                                            if (v4_5 != null) {
                                                v5_0 = v3_7.M(v4_5);
                                            }
                                            OB.d v11_1 = v5_0;
                                            String v1_15 = v1_14.e;
                                            ((Ac.a) v2_8.c).getClass();
                                            v7_1 = new OB.g(v8_1, v9_1, v10_1, v11_1, new OB.i(v1_15.a, v1_15.b, v1_15.c));
                                        }
                                    } else {
                                        OB.d v11_2;
                                        String v2_13 = v2_3.a;
                                        String v1_17 = ((lA.b) v1_8);
                                        String v3_10 = ((uc.i) v2_13.b);
                                        CB.a v4_6 = v1_17.a;
                                        String v8_3 = new OB.e(v4_6.a, v4_6.b);
                                        String v9_2 = v1_17.b;
                                        String v10_2 = v1_17.c;
                                        CB.a v4_8 = v1_17.d;
                                        if (v4_8 == null) {
                                            v11_2 = 0;
                                        } else {
                                            v11_2 = v3_10.M(v4_8);
                                        }
                                        CB.a v4_10 = v1_17.e;
                                        if (v4_10 != null) {
                                            v5_0 = v3_10.M(v4_10);
                                        }
                                        OB.d v12_3 = v5_0;
                                        String v1_20 = v1_17.f;
                                        ((Ac.a) v2_13.c).getClass();
                                        v7_1 = new OB.f(v8_3, v9_2, v10_2, v11_2, v12_3, new OB.i(v1_20.a, v1_20.b, v1_20.c));
                                    }
                                    v5_0 = v7_1;
                                    v6_1.x0(v5_0, sA.a.Z);
                                }
                                String v9_3 = v5_0;
                                String v1_23 = v6_1.k0();
                                do {
                                    OB.d v11_3 = v1_23.getValue();
                                } while(!v1_23.c(v11_3, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v11_3), 0, 0, 0, 0, 0, 0, v9_3, 191)));
                            }
                            v1_3 = kotlin.Unit.INSTANCE;
                        }
                    } else {
                        v1_3 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v2_49 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p19);
                        v1_2 = ((ng.L) p19).a;
                    }
                }
                return v1_3;
            case 4:
                String v1_66 = vg.i.getCOROUTINE_SUSPENDED();
                String v2_45 = this.e;
                if (v2_45 == null) {
                    kotlin.ResultKt.throwOnFailure(p19);
                    this.e = 1;
                    if (gi.U.a(1000, this) != v1_66) {
                        String v1_69 = v6_1.k0();
                        do {
                            String v2_47 = v1_69.getValue();
                        } while(!v1_69.c(v2_47, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v2_47), 0, 0, 0, 0, 0, 0, 0, 127)));
                        ((mA.a) ((androidx.media3.extractor.mp4.g) v6_1.w.d).b).a();
                        v1_66 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v2_45 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p19);
                    }
                }
                return v1_66;
            case 5:
                String v1_1 = vg.i.getCOROUTINE_SUSPENDED();
                String v2_1 = this.e;
                if (v2_1 == null) {
                    kotlin.ResultKt.throwOnFailure(p19);
                    this.e = 1;
                    if (gi.U.a(1000, this) != v1_1) {
                        String v1_24 = v6_1.k0();
                        do {
                            String v2_25 = v1_24.getValue();
                        } while(!v1_24.c(v2_25, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v2_25), 0, 0, 0, 0, 0, 0, 0, 127)));
                        ((mA.a) ((androidx.media3.extractor.mp4.g) v6_1.w.d).b).a();
                        v1_1 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v2_1 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p19);
                    }
                }
                return v1_1;
            default:
                String v2_43;
                String v1_60 = vg.i.getCOROUTINE_SUSPENDED();
                String v2_40 = this.e;
                if (v2_40 == null) {
                    kotlin.ResultKt.throwOnFailure(p19);
                    String v2_41 = v6_1.p;
                    this.e = 1;
                    v2_43 = v2_41.a.f();
                    if (v2_43 != v1_60) {
                        v1_60 = Boolean.valueOf((((Boolean) v2_43).booleanValue() ^ 1));
                    }
                } else {
                    if (v2_40 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p19);
                        v2_43 = p19;
                    }
                }
                return v1_60;
        }
    }
}

package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class g1 extends oA.g {
    public final YB.e A;
    public final jB.a B;
    public final iB.a C;
    public final YB.c D;
    public final sA.f E;
    public final boolean F;
    public final com.google.android.gms.internal.firebase-perf.z G;
    public final ji.c0 H;
    public final java.util.HashMap I;
    public final ji.i0 J;
    public kotlinx.coroutines.Job K;
    public final java.util.HashMap L;
    public boolean M;
    public volatile Ai.D N;
    public final Is.a h;
    public final org.greenrobot.eventbus.e i;
    public final nA.i j;
    public final lB.b k;
    public final kc.a l;
    public final NB.c m;
    public final nA.A n;
    public final nA.H o;
    public final nA.q p;
    public final nA.B q;
    public final sA.c r;
    public final nA.s s;
    public final nA.k t;
    public final nA.a u;
    public final nA.m v;
    public final androidx.camera.camera2.internal.S0 w;
    public final androidx.media3.container.l x;
    public final kc.b y;
    public final IB.b z;

    public g1(Is.a p10, org.greenrobot.eventbus.e p11, nA.i p12, lB.b p13, kc.a p14, NB.c p15, nA.A p16, nA.H p17, nA.q p18, nA.B p19, sA.c p20, nA.s p21, nA.k p22, nA.a p23, nA.m p24, androidx.camera.camera2.internal.S0 p25, androidx.media3.container.l p26, kc.b p27, IB.b p28, YB.e p29, jB.a p30, iB.a p31, YB.c p32, sA.f p33, boolean p34)
    {
        this.h = p10;
        this.i = p11;
        this.j = p12;
        this.k = p13;
        this.l = p14;
        this.m = p15;
        this.n = p16;
        this.o = p17;
        this.p = p18;
        this.q = p19;
        this.r = p20;
        this.s = p21;
        this.t = p22;
        this.u = p23;
        this.v = p24;
        this.w = p25;
        this.x = p26;
        this.y = p27;
        this.z = p28;
        this.A = p29;
        this.B = p30;
        this.C = p31;
        this.D = p32;
        this.E = p33;
        this.F = p34;
        this.G = new com.google.android.gms.internal.firebase-perf.z(new O5.b(10), new Tb.c(26), 17);
        sA.b v12_13 = new vn.com.vng.zalopay.homev5.presenter.ui.home_page.c1(((nA.r) p25.b).a.e(), this, 0);
        java.util.Map v10_29 = this.i0();
        this.H = ji.j0.D(new ji.x(new h3.x0(new CQ.a(2, 13, 0), new PL.h(new ji.e0(new h3.r(h3.t.b(new h3.r(v12_13, new h3.g(0, v10_29, 0), 0, 1)), new B2.J(3, 0), 0, 0)), 2)), new B4.v(3, 5, 0)), v10_29, ji.m0.b, 1);
        this.I = new java.util.HashMap();
        java.util.Map v10_1 = Integer.valueOf(0);
        this.J = ji.j0.b(0, 7, 0);
        this.L = new java.util.HashMap();
        p11.k(this);
        this.s0();
        this.r0(1);
        if (((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).h) {
            sA.a v11_7 = ((mA.a) ((j9.q) p25.e).b);
            sA.b v12_5 = v11_7.d();
            if ((v12_5 instanceof ng.K)) {
                v12_5 = v10_1;
            }
            v11_7.c((((Number) v12_5).intValue() + 1));
            Object v1_5 = ((mA.a) ((io.reactivex.internal.operators.observable.f1) p25.f).b).d();
            if (!(v1_5 instanceof ng.K)) {
                v10_1 = v1_5;
            }
            p20.b(sA.a.e, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(sA.b.l, Integer.valueOf(((Number) v10_1).intValue()))));
        }
        return;
    }

    public final Object h0()
    {
        int v3 = (this.s.a() ^ 1);
        int v1_1 = ((mA.a) ((lO.o) this.w.c).b);
        int v4_0 = v1_1.b();
        if ((v4_0 instanceof ng.K)) {
            v4_0 = Boolean.FALSE;
        }
        java.util.List v8;
        if (!((Boolean) v4_0).booleanValue()) {
            int v1_2 = v1_1.d();
            int v4_3 = Integer.valueOf(0);
            if ((v1_2 instanceof ng.K)) {
                v1_2 = v4_3;
            }
            if (((Number) v1_2).intValue() < 3) {
                v8 = 1;
            } else {
                v8 = 0;
            }
        }
        return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0(kotlin.collections.E.emptyList(), kotlin.collections.E.emptyList(), v3, 0, 0, 0, 0, v8);
    }

    public final void n0(Object p20)
    {
        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_13 = 0;
        sA.c v3_51 = Integer.valueOf(0);
        sA.b v5_5 = 0;
        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.k0.a)) {
            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.K)) {
                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.D)) {
                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.P)) {
                        if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.V)) {
                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.L)) {
                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.O)) {
                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.T)) {
                                        if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.S)) {
                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.Q)) {
                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.n0)) {
                                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.e0.a)) {
                                                        if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.U)) {
                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.W)) {
                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.m0)) {
                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.F)) {
                                                                        if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.X)) {
                                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.o0)) {
                                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.M)) {
                                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.N)) {
                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.b0.a)) {
                                                                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.f0.a)) {
                                                                                                if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.g0.a)) {
                                                                                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.i0.a)) {
                                                                                                        if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.q0)) {
                                                                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.Z)) {
                                                                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.p0)) {
                                                                                                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.G.a)) {
                                                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.r.a)) {
                                                                                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.y)) {
                                                                                                                                if ((!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.H.a)) && (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.p.a))) {
                                                                                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.J)) {
                                                                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.I.a)) {
                                                                                                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.A)) {
                                                                                                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.B)) {
                                                                                                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.t)) {
                                                                                                                                                        if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.v)) {
                                                                                                                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.x)) {
                                                                                                                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.o)) {
                                                                                                                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.u)) {
                                                                                                                                                                        if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.z)) {
                                                                                                                                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.s)) {
                                                                                                                                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.E)) {
                                                                                                                                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.w)) {
                                                                                                                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.h0.a)) {
                                                                                                                                                                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.d0.a)) {
                                                                                                                                                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.a0)) {
                                                                                                                                                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.C)) {
                                                                                                                                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.j0.a)) {
                                                                                                                                                                                                            if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.q)) {
                                                                                                                                                                                                                if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.l0)) {
                                                                                                                                                                                                                    if (!(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20) instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.c0)) {
                                                                                                                                                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20), vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y.a)) {
                                                                                                                                                                                                                            throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                            if (!this.M) {
                                                                                                                                                                                                                                this.M = 1;
                                                                                                                                                                                                                                this.E.finish();
                                                                                                                                                                                                                                return;
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                        this.g0(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.g(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.c0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a));
                                                                                                                                                                                                                        return;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                    sA.f v0_118 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.l0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                                                                    this.r.b(sA.a.c, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.h, v0_118.g.get("reminder_tracking_id")), kotlin.TuplesKt.to(sA.b.g, v0_118.g.get("reminder_name"))})));
                                                                                                                                                                                                                    return;
                                                                                                                                                                                                                }
                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                sA.f v0_125 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.q) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                                                                this.p0(v0_125.b);
                                                                                                                                                                                                                this.z.j(v0_125);
                                                                                                                                                                                                                return;
                                                                                                                                                                                                            }
                                                                                                                                                                                                        } else {
                                                                                                                                                                                                            this.q0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.V0.a);
                                                                                                                                                                                                            return;
                                                                                                                                                                                                        }
                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        this.u0(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.C) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a);
                                                                                                                                                                                                        return;
                                                                                                                                                                                                    }
                                                                                                                                                                                                } else {
                                                                                                                                                                                                    this.g0(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.f(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.a0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a));
                                                                                                                                                                                                    return;
                                                                                                                                                                                                }
                                                                                                                                                                                            } else {
                                                                                                                                                                                                this.q0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.Q0.a);
                                                                                                                                                                                                return;
                                                                                                                                                                                            }
                                                                                                                                                                                        } else {
                                                                                                                                                                                            this.q0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.T0.a);
                                                                                                                                                                                            return;
                                                                                                                                                                                        }
                                                                                                                                                                                    } else {
                                                                                                                                                                                        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_77 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.w) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                                        sA.f v0_135 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.w) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                                                        sA.c v3_37 = this.z;
                                                                                                                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_135, XA.a.a)) {
                                                                                                                                                                                            if (!(v0_135 instanceof XA.b)) {
                                                                                                                                                                                                throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                                            } else {
                                                                                                                                                                                                sA.a v4_32 = v2_77.j.iterator();
                                                                                                                                                                                                while (v4_32.hasNext()) {
                                                                                                                                                                                                    Object v6_25 = v4_32.next();
                                                                                                                                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(((DB.K) v6_25).getId(), ((XA.b) v0_135).a)) {
                                                                                                                                                                                                        v5_5 = v6_25;
                                                                                                                                                                                                        break;
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                                sA.b v5_27 = ((DB.K) v5_5);
                                                                                                                                                                                                if (v5_27 != null) {
                                                                                                                                                                                                    this.p0(v5_27.a());
                                                                                                                                                                                                    v3_37.e(v2_77, v5_27.o());
                                                                                                                                                                                                    return;
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        } else {
                                                                                                                                                                                            this.p0(v2_77.e);
                                                                                                                                                                                            v3_37.d(v2_77);
                                                                                                                                                                                            return;
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                } else {
                                                                                                                                                                                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_78 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.E) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                                    sA.f v0_142 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.E) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                                                    sA.c v3_38 = v2_78.l;
                                                                                                                                                                                    sA.a v4_35 = this.z;
                                                                                                                                                                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_142, dB.g.a)) {
                                                                                                                                                                                        if (!(v0_142 instanceof dB.e)) {
                                                                                                                                                                                            if (!(v0_142 instanceof dB.f)) {
                                                                                                                                                                                                throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                                            } else {
                                                                                                                                                                                                sA.f v0_148 = ((DB.a0) kotlin.collections.CollectionsKt.getOrNull(v3_38, ((dB.f) v0_142).a));
                                                                                                                                                                                                if (v0_148 != null) {
                                                                                                                                                                                                    this.p0(v0_148.a());
                                                                                                                                                                                                    v4_35.e(v2_78, v0_148.q());
                                                                                                                                                                                                    return;
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        } else {
                                                                                                                                                                                            sA.f v0_153 = ((DB.a0) kotlin.collections.CollectionsKt.getOrNull(v3_38, ((dB.e) v0_142).a));
                                                                                                                                                                                            if (v0_153 != null) {
                                                                                                                                                                                                this.p0(v0_153.a());
                                                                                                                                                                                                v4_35.e(v2_78, v0_153.o());
                                                                                                                                                                                                return;
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    } else {
                                                                                                                                                                                        this.p0(v2_78.e);
                                                                                                                                                                                        v4_35.d(v2_78);
                                                                                                                                                                                        return;
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            } else {
                                                                                                                                                                                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_79 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.s) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                                sA.f v0_157 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.s) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                                                sA.c v3_41 = this.z;
                                                                                                                                                                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_157, TA.a.a)) {
                                                                                                                                                                                    if (!(v0_157 instanceof TA.b)) {
                                                                                                                                                                                        if (!(v0_157 instanceof TA.c)) {
                                                                                                                                                                                            throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                                        } else {
                                                                                                                                                                                            sA.a v4_44 = v2_79.h.iterator();
                                                                                                                                                                                            while (v4_44.hasNext()) {
                                                                                                                                                                                                Object v6_27 = v4_44.next();
                                                                                                                                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(((DB.b) v6_27).a, ((TA.c) v0_157).a)) {
                                                                                                                                                                                                    v5_5 = v6_27;
                                                                                                                                                                                                    break;
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                            sA.b v5_32 = ((DB.b) v5_5);
                                                                                                                                                                                            if (v5_32 != null) {
                                                                                                                                                                                                this.p0(v5_32.e);
                                                                                                                                                                                                v3_41.e(v2_79, v5_32.f);
                                                                                                                                                                                                return;
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    } else {
                                                                                                                                                                                        sA.a v4_47 = v2_79.i.iterator();
                                                                                                                                                                                        while (v4_47.hasNext()) {
                                                                                                                                                                                            Object v6_29 = v4_47.next();
                                                                                                                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(((DB.a) v6_29).a, ((TA.b) v0_157).a)) {
                                                                                                                                                                                                v5_5 = v6_29;
                                                                                                                                                                                                break;
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                        sA.b v5_33 = ((DB.a) v5_5);
                                                                                                                                                                                        if (v5_33 != null) {
                                                                                                                                                                                            this.p0(v5_33.e);
                                                                                                                                                                                            v3_41.e(v2_79, v5_33.f);
                                                                                                                                                                                            return;
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                } else {
                                                                                                                                                                                    this.p0(v2_79.g);
                                                                                                                                                                                    v3_41.d(v2_79);
                                                                                                                                                                                    return;
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        } else {
                                                                                                                                                                            vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_80 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.z) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                            sA.f v0_166 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.z) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                                            sA.c v3_42 = this.z;
                                                                                                                                                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_166, aB.a.a)) {
                                                                                                                                                                                if (!(v0_166 instanceof aB.b)) {
                                                                                                                                                                                    throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                                } else {
                                                                                                                                                                                    sA.a v4_54 = v2_80.h.iterator();
                                                                                                                                                                                    while (v4_54.hasNext()) {
                                                                                                                                                                                        Object v6_31 = v4_54.next();
                                                                                                                                                                                        if (((DB.P) v6_31).a == ((aB.b) v0_166).a) {
                                                                                                                                                                                            v5_5 = v6_31;
                                                                                                                                                                                            break;
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                    sA.b v5_34 = ((DB.P) v5_5);
                                                                                                                                                                                    if (v5_34 != null) {
                                                                                                                                                                                        this.p0(v5_34.e);
                                                                                                                                                                                        v3_42.e(v2_80, v5_34.g);
                                                                                                                                                                                        return;
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            } else {
                                                                                                                                                                                this.p0(v2_80.g);
                                                                                                                                                                                v3_42.d(v2_80);
                                                                                                                                                                                return;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    } else {
                                                                                                                                                                        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_81 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.u) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                        sA.f v0_173 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.u) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                                        sA.c v3_43 = v2_81.h;
                                                                                                                                                                        sA.a v4_56 = v2_81.e;
                                                                                                                                                                        Object v6_32 = this.z;
                                                                                                                                                                        if ((!kotlin.jvm.internal.Intrinsics.areEqual(v0_173, VA.f.a)) && (!kotlin.jvm.internal.Intrinsics.areEqual(v0_173, VA.b.a))) {
                                                                                                                                                                            if ((!kotlin.jvm.internal.Intrinsics.areEqual(v0_173, VA.c.a)) && (!kotlin.jvm.internal.Intrinsics.areEqual(v0_173, VA.d.a))) {
                                                                                                                                                                                if (!(v0_173 instanceof VA.e)) {
                                                                                                                                                                                    if ((!kotlin.jvm.internal.Intrinsics.areEqual(v0_173, VA.g.a)) && (!kotlin.jvm.internal.Intrinsics.areEqual(v0_173, VA.h.a))) {
                                                                                                                                                                                        throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                                    } else {
                                                                                                                                                                                        this.p0(v4_56.d);
                                                                                                                                                                                        v6_32.e(v2_81, v4_56.e);
                                                                                                                                                                                        return;
                                                                                                                                                                                    }
                                                                                                                                                                                } else {
                                                                                                                                                                                    sA.c v3_49 = v2_81.i.iterator();
                                                                                                                                                                                    while (v3_49.hasNext()) {
                                                                                                                                                                                        sA.a v4_58 = v3_49.next();
                                                                                                                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(((DB.e) v4_58).a, ((VA.e) v0_173).a)) {
                                                                                                                                                                                            v5_5 = v4_58;
                                                                                                                                                                                            break;
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                    sA.b v5_35 = ((DB.e) v5_5);
                                                                                                                                                                                    if (v5_35 != null) {
                                                                                                                                                                                        this.p0(v5_35.e);
                                                                                                                                                                                        v6_32.e(v2_81, v5_35.j);
                                                                                                                                                                                        return;
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            } else {
                                                                                                                                                                                this.p0(v3_43.e);
                                                                                                                                                                                v6_32.e(v2_81, v3_43.j);
                                                                                                                                                                                return;
                                                                                                                                                                            }
                                                                                                                                                                        } else {
                                                                                                                                                                            this.p0(v2_81.f);
                                                                                                                                                                            v6_32.d(v2_81);
                                                                                                                                                                            return;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                } else {
                                                                                                                                                                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_86 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.o) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                    sA.f v0_185 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.o) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                                    sA.c v3_50 = this.z;
                                                                                                                                                                    if (!(v0_185 instanceof RA.b)) {
                                                                                                                                                                        if (!(v0_185 instanceof RA.c)) {
                                                                                                                                                                            this.p0(v2_86.a());
                                                                                                                                                                            v3_50.c(v2_86);
                                                                                                                                                                            return;
                                                                                                                                                                        } else {
                                                                                                                                                                            vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_90 = ((Iterable) v2_86.d()).iterator();
                                                                                                                                                                            while (v2_90.hasNext()) {
                                                                                                                                                                                sA.a v4_62 = v2_90.next();
                                                                                                                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(((hA.a) v4_62).a, ((RA.c) v0_185).a)) {
                                                                                                                                                                                    v5_5 = v4_62;
                                                                                                                                                                                    break;
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                            sA.b v5_36 = ((hA.a) v5_5);
                                                                                                                                                                            if (v5_36 != null) {
                                                                                                                                                                                this.p0(v5_36.f);
                                                                                                                                                                                v3_50.g(v5_36);
                                                                                                                                                                                return;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    } else {
                                                                                                                                                                        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_94 = ((Iterable) v2_86.d()).iterator();
                                                                                                                                                                        while (v2_94.hasNext()) {
                                                                                                                                                                            sA.a v4_64 = v2_94.next();
                                                                                                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(((hA.a) v4_64).a, ((RA.b) v0_185).a)) {
                                                                                                                                                                                v5_5 = v4_64;
                                                                                                                                                                                break;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                        sA.b v5_37 = ((hA.a) v5_5);
                                                                                                                                                                        if (v5_37 != null) {
                                                                                                                                                                            this.p0(v5_37.f);
                                                                                                                                                                            v3_50.g(v5_37);
                                                                                                                                                                            return;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            } else {
                                                                                                                                                                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_96 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.x) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                                sA.f v0_190 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.x) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                                sA.c v3_52 = this.z;
                                                                                                                                                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_190, ZA.d.a)) {
                                                                                                                                                                    if ((!kotlin.jvm.internal.Intrinsics.areEqual(v0_190, ZA.b.a)) && (!kotlin.jvm.internal.Intrinsics.areEqual(v0_190, ZA.c.a))) {
                                                                                                                                                                        throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                    } else {
                                                                                                                                                                        sA.f v0_194 = v2_96.h;
                                                                                                                                                                        if (v0_194 != null) {
                                                                                                                                                                            sA.a v4_70 = v0_194.f();
                                                                                                                                                                            if (v4_70 != null) {
                                                                                                                                                                                this.p0(v4_70);
                                                                                                                                                                            }
                                                                                                                                                                            v3_52.e(v2_96, v0_194.o());
                                                                                                                                                                            return;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                } else {
                                                                                                                                                                    sA.f v0_196 = v2_96.g;
                                                                                                                                                                    if (v0_196 != null) {
                                                                                                                                                                        this.p0(v0_196);
                                                                                                                                                                    }
                                                                                                                                                                    v3_52.d(v2_96);
                                                                                                                                                                    return;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        } else {
                                                                                                                                                            vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_99 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.v) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                            sA.f v0_198 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.v) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                            sA.c v3_53 = this.z;
                                                                                                                                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_198, WA.a.a)) {
                                                                                                                                                                if ((!kotlin.jvm.internal.Intrinsics.areEqual(v0_198, WA.b.a)) && (!kotlin.jvm.internal.Intrinsics.areEqual(v0_198, WA.c.a))) {
                                                                                                                                                                    if (!(v0_198 instanceof WA.d)) {
                                                                                                                                                                        throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                    } else {
                                                                                                                                                                        sA.f v0_202 = ((WA.d) v0_198).a;
                                                                                                                                                                        sA.a v4_79 = v0_202.d;
                                                                                                                                                                        if (v4_79 != null) {
                                                                                                                                                                            this.p0(v4_79);
                                                                                                                                                                        }
                                                                                                                                                                        v3_53.e(v2_99, v0_202.e);
                                                                                                                                                                        return;
                                                                                                                                                                    }
                                                                                                                                                                } else {
                                                                                                                                                                    sA.f v0_204 = v2_99.h;
                                                                                                                                                                    if (v0_204 != null) {
                                                                                                                                                                        sA.a v4_80 = v0_204.h;
                                                                                                                                                                        if (v4_80 != null) {
                                                                                                                                                                            this.p0(v4_80);
                                                                                                                                                                        }
                                                                                                                                                                        v3_53.e(v2_99, v0_204.i);
                                                                                                                                                                        return;
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            } else {
                                                                                                                                                                sA.f v0_206 = v2_99.g;
                                                                                                                                                                if (v0_206 != null) {
                                                                                                                                                                    this.p0(v0_206);
                                                                                                                                                                    v3_53.d(v2_99);
                                                                                                                                                                    return;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    } else {
                                                                                                                                                        sA.f v0_212;
                                                                                                                                                        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_102 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.t) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                        sA.f v0_208 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.t) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                        this.p0(v2_102.f);
                                                                                                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_208, UA.b.a)) {
                                                                                                                                                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_208, UA.c.a)) {
                                                                                                                                                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_208, UA.a.a)) {
                                                                                                                                                                    throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                                } else {
                                                                                                                                                                    v0_212 = "CTA";
                                                                                                                                                                }
                                                                                                                                                            } else {
                                                                                                                                                                v0_212 = "blockUSP";
                                                                                                                                                            }
                                                                                                                                                        } else {
                                                                                                                                                            v0_212 = "Image";
                                                                                                                                                        }
                                                                                                                                                        this.z.f(v2_102, v0_212);
                                                                                                                                                        return;
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_104 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.B) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                    sA.f v0_214 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.B) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                    sA.c v3_56 = this.z;
                                                                                                                                                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_214, cB.a.a)) {
                                                                                                                                                        if (!(v0_214 instanceof cB.b)) {
                                                                                                                                                            throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                        } else {
                                                                                                                                                            sA.f v0_218 = ((cB.b) v0_214).a;
                                                                                                                                                            sA.a v4_89 = v0_218.a;
                                                                                                                                                            if (v4_89 != null) {
                                                                                                                                                                this.p0(v4_89);
                                                                                                                                                            }
                                                                                                                                                            v3_56.e(v2_104, v0_218.g);
                                                                                                                                                            return;
                                                                                                                                                        }
                                                                                                                                                    } else {
                                                                                                                                                        sA.f v0_220 = v2_104.e;
                                                                                                                                                        if (v0_220 != null) {
                                                                                                                                                            this.p0(v0_220);
                                                                                                                                                        }
                                                                                                                                                        v3_56.d(v2_104);
                                                                                                                                                        return;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_107 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.A) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                                sA.f v0_222 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.A) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                                                                                                                                sA.c v3_57 = this.z;
                                                                                                                                                if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_222, bB.a.a)) {
                                                                                                                                                    if (!(v0_222 instanceof bB.b)) {
                                                                                                                                                        throw new kotlin.NoWhenBranchMatchedException();
                                                                                                                                                    } else {
                                                                                                                                                        sA.f v0_228 = ((DB.T) kotlin.collections.CollectionsKt.getOrNull(v2_107.h, ((bB.b) v0_222).a));
                                                                                                                                                        if (v0_228 != null) {
                                                                                                                                                            this.p0(v0_228.a());
                                                                                                                                                            v3_57.e(v2_107, v0_228.o());
                                                                                                                                                            return;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                } else {
                                                                                                                                                    this.p0(v2_107.g);
                                                                                                                                                    v3_57.d(v2_107);
                                                                                                                                                    return;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            this.m0(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0(3));
                                                                                                                                            return;
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        sA.f v0_234 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.J) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_109 = this.I;
                                                                                                                                        sA.c v3_60 = ((Number) v2_109.getOrDefault(v0_234.a, v3_51)).intValue();
                                                                                                                                        if (v3_60 < 3) {
                                                                                                                                            v2_109.put(v0_234.a, Integer.valueOf((v3_60 + 1)));
                                                                                                                                            kotlinx.coroutines.BuildersKt.launch$default(this.i0(), 0, 0, new tS.v(this, v0_234, 0, 17), 3, 0);
                                                                                                                                            return;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    this.t0();
                                                                                                                                    return;
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_113 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                                                sA.c v3_65 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).g;
                                                                                                                                if (v3_65 != null) {
                                                                                                                                    sA.f v0_237;
                                                                                                                                    if (!((vn.com.vng.zalopay.homev5.presenter.ui.home_page.y) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b) {
                                                                                                                                        v0_237 = sA.a.b0;
                                                                                                                                    } else {
                                                                                                                                        v0_237 = sA.a.a0;
                                                                                                                                    }
                                                                                                                                    this.x0(v3_65, v0_237);
                                                                                                                                }
                                                                                                                                sA.a v4_97 = this.k0();
                                                                                                                                do {
                                                                                                                                    sA.f v0_238 = v4_97.getValue();
                                                                                                                                } while(!v4_97.c(v0_238, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v0_238), 0, 0, 0, 0, 0, 0, 0, 191)));
                                                                                                                                if ((v2_113 instanceof OB.b)) {
                                                                                                                                    this.p0(((OB.b) v2_113).a);
                                                                                                                                    return;
                                                                                                                                }
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            sA.f v0_244 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).f;
                                                                                                                            if (v0_244 != null) {
                                                                                                                                this.p0(v0_244.d);
                                                                                                                                this.w0(sA.a.X, v0_244);
                                                                                                                                return;
                                                                                                                            }
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_119 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).f;
                                                                                                                        if (v2_119 != null) {
                                                                                                                            sA.a v4_98 = this.k0();
                                                                                                                            do {
                                                                                                                                sA.f v0_247 = v4_98.getValue();
                                                                                                                            } while(!v4_98.c(v0_247, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v0_247), 0, 0, 0, 0, 0, 0, 0, 223)));
                                                                                                                            sA.f v0_249 = this.u;
                                                                                                                            sA.c v3_68 = v2_119.c;
                                                                                                                            v0_249.getClass();
                                                                                                                            try {
                                                                                                                                v0_249.a.a(v3_68);
                                                                                                                                sA.f v0_251 = Boolean.TRUE;
                                                                                                                            } catch (sA.f v0_252) {
                                                                                                                                v0_251 = kotlin.ResultKt.createFailure(v0_252);
                                                                                                                            }
                                                                                                                            if ((v0_251 instanceof ng.K)) {
                                                                                                                                v0_251 = Boolean.FALSE;
                                                                                                                            }
                                                                                                                            ((Boolean) v0_251).getClass();
                                                                                                                            this.w0(sA.a.Y, v2_119);
                                                                                                                        }
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_122 = this.k0();
                                                                                                                    do {
                                                                                                                        sA.c v3_70 = v2_122.getValue();
                                                                                                                    } while(!v2_122.c(v3_70, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v3_70), 0, 0, 0, 0, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.p0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a, 0, 0, 239)));
                                                                                                                }
                                                                                                            } else {
                                                                                                                this.g0(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.d(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Z) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a));
                                                                                                                return;
                                                                                                            }
                                                                                                        } else {
                                                                                                            int v13_4 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.q0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                                            vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_127 = this.k0();
                                                                                                            do {
                                                                                                                sA.f v0_258 = v2_127.getValue();
                                                                                                            } while(!v2_127.c(v0_258, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v0_258), 0, 0, 0, v13_4, 0, 0, 0, 247)));
                                                                                                            sA.f v0_262 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).d;
                                                                                                            if (v0_262 != null) {
                                                                                                                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_129 = this.N;
                                                                                                                try {
                                                                                                                    if ((v2_129 != null) && (((java.util.HashSet) v2_129.d).contains(v0_262) == 1)) {
                                                                                                                        this.g0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.e.a);
                                                                                                                        return;
                                                                                                                    }
                                                                                                                } catch (sA.f v0_264) {
                                                                                                                    throw v0_264;
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        this.q0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.U0.a);
                                                                                                        return;
                                                                                                    }
                                                                                                } else {
                                                                                                    this.q0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.S0.a);
                                                                                                    return;
                                                                                                }
                                                                                            } else {
                                                                                                this.q0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.R0.a);
                                                                                                return;
                                                                                            }
                                                                                        } else {
                                                                                            sA.f v0_269 = this.N;
                                                                                            if (v0_269 != null) {
                                                                                                this.z.b(new jV.k(27, v0_269));
                                                                                                try {
                                                                                                    sA.c v3_77 = kotlin.collections.CollectionsKt.drop(((java.util.ArrayList) v0_269.c), v0_269.b);
                                                                                                    v0_269.b = ((java.util.ArrayList) v0_269.c).size();
                                                                                                } catch (sA.f v0_271) {
                                                                                                    throw v0_271;
                                                                                                }
                                                                                                if (!v3_77.isEmpty()) {
                                                                                                    this.z.i(v3_77);
                                                                                                    return;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        this.p0(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.N) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.e);
                                                                                        vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_137 = this.r;
                                                                                        Object v6_45 = kotlin.TuplesKt.to(sA.b.I, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.N) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.f);
                                                                                        kotlin.TuplesKt.to(sA.b.O, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.N) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.d.name());
                                                                                        kotlin.TuplesKt.to(sA.b.P, Integer.valueOf(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.N) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.g));
                                                                                        kotlin.TuplesKt.to(sA.b.Q, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.N) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.c);
                                                                                        kotlin.TuplesKt.to(sA.b.R, Boolean.valueOf(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.N) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.a));
                                                                                        kotlin.TuplesKt.to(sA.b.U, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.N) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b.a);
                                                                                        v2_137.b(sA.a.U, kotlin.collections.a0.mapOf(new kotlin.Pair[] {v6_45, kotlin.TuplesKt.to(sA.b.h0, "detailedBlock")})));
                                                                                        return;
                                                                                    }
                                                                                } else {
                                                                                    sA.f v0_280 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.M) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                                    this.p0(v0_280.c);
                                                                                    this.r.b(sA.a.U, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.U, v0_280.e.a), kotlin.TuplesKt.to(sA.b.h0, "outerCard")})));
                                                                                    return;
                                                                                }
                                                                            } else {
                                                                                kotlinx.coroutines.BuildersKt.launch$default(this.i0(), this.h.c, 0, new tS.v(this, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.o0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a, 0, 19), 2, 0);
                                                                                return;
                                                                            }
                                                                        } else {
                                                                            this.p0(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.b);
                                                                            return;
                                                                        }
                                                                    } else {
                                                                        this.p0(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.F) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.h);
                                                                        this.r.b(sA.a.O, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.D, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.F) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.a), kotlin.TuplesKt.to(sA.b.p, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.F) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.d), kotlin.TuplesKt.to(sA.b.E, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.F) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.c), kotlin.TuplesKt.to(sA.b.F, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.F) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.e), kotlin.TuplesKt.to(sA.b.g0, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.F) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.g)})));
                                                                        return;
                                                                    }
                                                                } else {
                                                                    this.r.b(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.m0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.m0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b);
                                                                    return;
                                                                }
                                                            } else {
                                                                sA.f v0_17 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.W) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                                this.g0(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.j(v0_17));
                                                                sA.a v4_4 = this.r;
                                                                Object v6_6 = kotlin.TuplesKt.to(sA.b.r, v0_17.e);
                                                                String v8_8 = ((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).a).iterator();
                                                                while (v8_8.hasNext()) {
                                                                    tS.v v9_4 = ((BB.F) v8_8.next());
                                                                    if ((v9_4 instanceof BB.D)) {
                                                                        String v8_9 = ((BB.D) v9_4).a.size();
                                                                    }
                                                                    boolean v7_7 = kotlin.TuplesKt.to(sA.b.H, Integer.valueOf(v8_9));
                                                                    sA.c v3_2 = kotlin.TuplesKt.to(sA.b.B, v3_51);
                                                                    tS.v v9_10 = ((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).a).iterator();
                                                                    do {
                                                                        int v11_1 = -1;
                                                                        if (v9_10.hasNext()) {
                                                                            int v10_4 = ((BB.F) v9_10.next());
                                                                        }
                                                                        v4_4.b(sA.a.Q, kotlin.collections.a0.mapOf(new kotlin.Pair[] {v6_6, v7_7, v3_2, kotlin.TuplesKt.to(sA.b.C, Integer.valueOf(v11_1))})));
                                                                        return;
                                                                    } while(!(v10_4 instanceof BB.D));
                                                                    tS.v v9_12 = ((BB.D) v10_4).a.iterator();
                                                                    while (v9_12.hasNext()) {
                                                                        if (!kotlin.jvm.internal.Intrinsics.areEqual(((BB.T) v9_12.next()).a, v0_17.a)) {
                                                                            v2_13++;
                                                                        } else {
                                                                            v11_1 = v2_13;
                                                                            break;
                                                                        }
                                                                    }
                                                                    v4_4.b(sA.a.Q, kotlin.collections.a0.mapOf(new kotlin.Pair[] {v6_6, v7_7, v3_2, kotlin.TuplesKt.to(sA.b.C, Integer.valueOf(v11_1))})));
                                                                    return;
                                                                }
                                                                v8_9 = 0;
                                                            }
                                                        } else {
                                                            sA.f v0_23 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.U) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                            this.p0(v0_23.f);
                                                            this.z.h(v0_23);
                                                            return;
                                                        }
                                                    } else {
                                                        this.r0(1);
                                                        this.s0();
                                                        int v12_2 = (this.s.a() ^ 1);
                                                        if (((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).c != v12_2) {
                                                            sA.f v0_29 = this.k0();
                                                            do {
                                                                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_18 = v0_29.getValue();
                                                            } while(!v0_29.c(v2_18, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v2_18), 0, 0, v12_2, 0, 0, 0, 0, 251)));
                                                        }
                                                        if (this.s.a()) {
                                                            sA.f v0_32 = this.L;
                                                            vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_23 = ((Iterable) v0_32.values()).iterator();
                                                            while (v2_23.hasNext()) {
                                                                ((kotlin.jvm.functions.Function0) v2_23.next()).invoke();
                                                            }
                                                            v0_32.clear();
                                                            return;
                                                        }
                                                    }
                                                } else {
                                                    this.r.b(sA.a.H, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(sA.b.w, kotlin.collections.CollectionsKt.l(((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.n0) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a), ", ", 0, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0(2), 30))));
                                                    return;
                                                }
                                            } else {
                                                sA.f v0_39 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.Q) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_27 = v0_39.c;
                                                if (v2_27 != null) {
                                                    this.v0(v2_27.d);
                                                }
                                                this.r.b(sA.a.C, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(sA.b.v, v0_39.e.a)));
                                                return;
                                            }
                                        } else {
                                            vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_31 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.S) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                            sA.f v0_45 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.S) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                                            this.v0(v0_45.e());
                                            sA.c v3_10 = this.r;
                                            Object v6_8 = kotlin.collections.Z.createMapBuilder();
                                            v6_8.put(sA.b.P, Integer.valueOf(v2_31));
                                            v6_8.put(sA.b.w, v0_45.getTitle());
                                            if (!(v0_45 instanceof FB.b)) {
                                                if (!(v0_45 instanceof FB.d)) {
                                                    throw new kotlin.NoWhenBranchMatchedException();
                                                } else {
                                                    sA.f v0_49 = ((FB.d) v0_45).c;
                                                    if (v0_49 != null) {
                                                        v5_5 = v0_49.a;
                                                    }
                                                    v6_8.put(sA.b.y, v5_5);
                                                }
                                            } else {
                                                sA.f v0_51 = ((FB.b) v0_45).d;
                                                if (v0_51 != null) {
                                                    v5_5 = new zA.a(v0_51);
                                                }
                                                v6_8.put(sA.b.x, v5_5);
                                            }
                                            v3_10.b(sA.a.G, kotlin.collections.Z.build(v6_8));
                                            return;
                                        }
                                    } else {
                                        sA.f v0_54 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.T) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                        this.v0(v0_54.f);
                                        this.r.b(sA.a.S, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.u, v0_54.b), kotlin.TuplesKt.to(sA.b.y, v0_54.c), kotlin.TuplesKt.to(sA.b.I, v0_54.e)})));
                                        return;
                                    }
                                } else {
                                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_43 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.O) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                                    sA.f v0_62 = ((String) kotlin.collections.CollectionsKt.getOrNull(v2_43.a, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.O) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b));
                                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_44 = v2_43.b;
                                    this.B.getClass();
                                    sA.c v3_14 = jB.a.a(v2_44);
                                    if (v3_14 != null) {
                                        sA.a v4_11 = q9.y6.h(v2_44);
                                        if (v4_11 != null) {
                                            sA.a v4_12 = v4_11.getQueryParameters("user-type");
                                            if (v4_12 != null) {
                                                v5_5 = ((String) kotlin.collections.CollectionsKt.firstOrNull(v4_12));
                                            }
                                        }
                                    }
                                    sA.c v3_15;
                                    if (v3_14 == null) {
                                        v3_15 = "web";
                                    } else {
                                        v3_15 = "native";
                                    }
                                    this.r.b(sA.a.w, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.j, v3_15), kotlin.TuplesKt.to(sA.b.k, v5_5), kotlin.TuplesKt.to(sA.b.f0, v0_62)})));
                                    if ((jB.a.a(v2_44)) && ((v0_62 != null) && (jB.a.a(v2_44)))) {
                                        v2_44 = android.net.Uri.parse(v2_44).buildUpon().appendQueryParameter("shortcut-keyword", v0_62).build().toString();
                                    }
                                    this.p0(v2_44);
                                    return;
                                }
                            } else {
                                this.p0(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.L) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.a.c);
                                this.r.b(sA.a.F, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.q, kotlin.collections.CollectionsKt.l(((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.L) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.b), ", ", 0, 0, new qK.a(29), 30)), kotlin.TuplesKt.to(sA.b.r, kotlin.collections.CollectionsKt.l(((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.L) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.b), ", ", 0, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y0(0), 30))})));
                                return;
                            }
                        } else {
                            this.p0(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.V) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.g);
                            vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_56 = this.r;
                            sA.a v4_16 = kotlin.collections.Z.createMapBuilder();
                            v4_16.put(sA.b.q, Integer.valueOf(448));
                            v4_16.put(sA.b.r, "Zalopay Priority");
                            v4_16.put(sA.b.N, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.V) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.e);
                            sA.f v0_74 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.V) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.b;
                            if (v0_74 != null) {
                                v4_16.put(sA.b.M, v0_74);
                            }
                            v2_56.b(sA.a.B, kotlin.collections.Z.build(v4_16));
                            return;
                        }
                    } else {
                        this.p0(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.P) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.d);
                        this.r.b(sA.a.x, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.q, Integer.valueOf(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.P) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.a)), kotlin.TuplesKt.to(sA.b.r, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.P) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a.b)})));
                        return;
                    }
                } else {
                    vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_62 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.D) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                    if (!(v2_62 instanceof BB.X)) {
                        if (!(v2_62 instanceof BB.V)) {
                            throw new kotlin.NoWhenBranchMatchedException();
                        } else {
                            sA.f v0_91 = ((BB.V) v2_62).d;
                            if (v0_91 != null) {
                                try {
                                    sA.f v0_93 = Integer.valueOf(Integer.parseInt(v0_91));
                                } catch (sA.f v0_94) {
                                    v0_93 = kotlin.ResultKt.createFailure(v0_94);
                                }
                                if (!(v0_93 instanceof ng.K)) {
                                    v5_5 = v0_93;
                                }
                                v5_5 = ((Integer) v5_5);
                            }
                        }
                    } else {
                        if (((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).h) {
                            kotlinx.coroutines.BuildersKt.launch$default(this.i0(), 0, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this, 0, 5), 3, 0);
                        }
                    }
                    sA.f v0_101 = kotlin.collections.Z.createMapBuilder();
                    if (v5_5 != null) {
                        v0_101.put("total", v5_5);
                    }
                    this.r.a(v2_62.b(), kotlin.collections.Z.build(v0_101));
                    this.p0(v2_62.c());
                }
                return;
            } else {
                tS.v v9_28;
                vn.com.vng.zalopay.homev5.presenter.ui.home_page.Y v2_63 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.K) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).a;
                sA.c v3_31 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.K) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).b;
                sA.f v0_105 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.K) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.r0) p20)).c;
                this.p0(v2_63.d);
                sA.a v4_21 = BB.i.c;
                if (v3_31 != v4_21) {
                    v9_28 = 0;
                } else {
                    tS.v v9_22 = ((Iterable) ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).a);
                    int v10_19 = new java.util.ArrayList();
                    tS.v v9_23 = v9_22.iterator();
                    while (v9_23.hasNext()) {
                        int v11_10 = v9_23.next();
                        if ((v11_10 instanceof BB.p)) {
                            v10_19.add(v11_10);
                        }
                    }
                    tS.v v9_25 = ((BB.p) kotlin.collections.CollectionsKt.firstOrNull(v10_19));
                    if (v9_25 == null) {
                    } else {
                        tS.v v9_26 = v9_25.a;
                        if (v9_26 == null) {
                        } else {
                            tS.v v9_27 = v9_26.size();
                            if (v9_27 == 8) {
                                v9_28 = "7_slots";
                            } else {
                                if (v9_27 == 12) {
                                    v9_28 = "11_slots";
                                }
                            }
                        }
                    }
                }
                if (v3_31 == v4_21) {
                    v5_5 = v2_63.g;
                }
                sA.c v3_33;
                sA.a v4_22 = this.r;
                int v13_1 = kotlin.TuplesKt.to(sA.b.q, Integer.valueOf(v2_63.a));
                kotlin.TuplesKt.to(sA.b.r, v2_63.b.a);
                sA.c v3_32 = v3_31.ordinal();
                if (v3_32 == null) {
                    v3_33 = "favorite";
                } else {
                    if (v3_32 == 1) {
                        v3_33 = "popular";
                    } else {
                        if (v3_32 == 2) {
                            v3_33 = "main";
                        } else {
                            if (v3_32 != 3) {
                                throw new kotlin.NoWhenBranchMatchedException();
                            } else {
                                v3_33 = "recent";
                            }
                        }
                    }
                }
                kotlin.TuplesKt.to(sA.b.t, v3_33);
                kotlin.TuplesKt.to(sA.b.b, Integer.valueOf(v0_105));
                kotlin.TuplesKt.to(sA.b.c, v9_28);
                v4_22.b(sA.a.y, kotlin.collections.a0.mapOf(new kotlin.Pair[] {v13_1, kotlin.TuplesKt.to(sA.b.s, v5_5)})));
                return;
            }
        } else {
            kotlinx.coroutines.BuildersKt.launch$default(this.i0(), 0, 0, new tS.v(this, 0, 18), 3, 0);
            return;
        }
    }

    public final void onCleared()
    {
        this.i.p(this);
        super.onCleared();
        return;
    }

    public final void onNetworkChange(YG.a p13)
    {
        boolean v0_2 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).c;
        java.util.Iterator v1_3 = this.k0();
        do {
            kotlin.jvm.functions.Function0 v2_4 = v1_3.getValue();
        } while(!v1_3.c(v2_4, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v2_4), 0, 0, (p13.a ^ 1), 0, 0, 0, 0, 251)));
        if (p13.a) {
            int v13_2 = this.L;
            java.util.Iterator v1_2 = ((Iterable) v13_2.values()).iterator();
            while (v1_2.hasNext()) {
                ((kotlin.jvm.functions.Function0) v1_2.next()).invoke();
            }
            v13_2.clear();
            if (v0_2) {
                this.r0(0);
            }
        }
        return;
    }

    public final void p0(String p2)
    {
        this.g0(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.h(p2));
        return;
    }

    public final void q0(vn.com.vng.zalopay.homev5.presenter.ui.home_page.W0 p7)
    {
        kotlinx.coroutines.BuildersKt.launch$default(this.i0(), 0, 0, new tS.v(this, p7, 0, 16), 3, 0);
        return;
    }

    public final void r0(boolean p9)
    {
        int v0_0 = this.K;
        if (v0_0 != 0) {
            v0_0.cancel(0);
        }
        this.K = kotlinx.coroutines.BuildersKt.launch$default(this.i0(), this.h.c, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.f1(this, p9, 0, 0), 2, 0);
        return;
    }

    public final void s0()
    {
        long v2 = System.currentTimeMillis();
        sA.c.d(this.r, String.valueOf(v2), 0, 2);
        kotlinx.coroutines.BuildersKt.launch$default(this.i0(), this.h.c, 0, new D1.f(this, v2, 0, 9), 2, 0);
        return;
    }

    public final void t0()
    {
        ji.b0 v0_2 = ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) this.j0()).g;
        if (v0_2 != null) {
            this.x0(v0_2, sA.a.c0);
        }
        ji.b0 v0_3 = this.k0();
        do {
            boolean v1_0 = v0_3.getValue();
        } while(!v0_3.c(v1_0, vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0.a(((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) v1_0), 0, 0, 0, 0, 0, 0, 0, 191)));
        return;
    }

    public final void u0(BB.C p10)
    {
        boolean v2_0 = ((mA.a) ((io.reactivex.internal.operators.observable.f1) this.w.f).b).d();
        Integer vtmp2 = Integer.valueOf(0);
        if ((v2_0 instanceof ng.K)) {
            v2_0 = vtmp2;
        }
        this.r.b(sA.a.d, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(sA.b.l, Integer.valueOf(((Number) v2_0).intValue()))));
        String v1_3 = 0;
        kotlinx.coroutines.BuildersKt.launch$default(this.i0(), 0, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(this, 0, 4), 3, 0);
        java.util.Iterator v10_3 = ((Iterable) p10.b).iterator();
        while (v10_3.hasNext()) {
            BB.Y v0_3 = v10_3.next();
            if ((((BB.Y) v0_3) instanceof BB.X)) {
            }
            BB.Y v0_4 = ((BB.Y) v0_3);
            if (v0_4 != null) {
                v1_3 = v0_4.c();
            }
            if (v1_3 != null) {
                this.p0(v1_3);
            }
            return;
        }
        v0_3 = 0;
    }

    public final void v0(BB.Q p3)
    {
        kotlin.NoWhenBranchMatchedException v3_1 = p3.b;
        String v0_4 = p3.a.ordinal();
        if (v0_4 == null) {
            this.p0(v3_1);
            return;
        } else {
            if (v0_4 == 1) {
                sA.c.c(this.r, sA.a.E);
                this.g0(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.i(v3_1));
                return;
            } else {
                if (v0_4 != 2) {
                    throw new kotlin.NoWhenBranchMatchedException();
                } else {
                    kotlin.NoWhenBranchMatchedException v3_6 = new Object[0];
                    ck.b.a("Unknown SOF action type", v3_6);
                    return;
                }
            }
        }
    }

    public final void w0(sA.a p3, BB.G p4)
    {
        this.r.b(p3, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(sA.b.X, String.valueOf(p4.a)), kotlin.TuplesKt.to(sA.b.Y, p4.b)})));
        return;
    }

    public final void x0(OB.j p4, sA.a p5)
    {
        sA.c v0_0 = kotlin.collections.Z.createMapBuilder();
        java.util.Map v4_3 = p4.a();
        v0_0.put(sA.b.s, v4_3.a);
        v0_0.put(sA.b.i0, v4_3.b);
        if (p5 == sA.a.Z) {
            v0_0.put(sA.b.j0, v4_3.c);
        }
        this.r.b(p5, kotlin.collections.Z.build(v0_0));
        return;
    }
}

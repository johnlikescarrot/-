package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class q implements lQ.d {
    public final ji.c0 A;
    public final ji.i0 B;
    public final ji.c0 C;
    public final okhttp3.OkHttpClient a;
    public final kotlinx.coroutines.CoroutineScope b;
    public final okhttp3.OkHttpClient c;
    public final vA.b d;
    public final kotlin.collections.s e;
    public final java.util.concurrent.ConcurrentHashMap f;
    public final Object g;
    public final java.util.LinkedHashSet h;
    public final Object i;
    public final Object j;
    public kotlinx.coroutines.Job k;
    public vn.com.vng.zalopay.network_monitor.data.monitor.c l;
    public boolean m;
    public long n;
    public int o;
    public final ji.i0 p;
    public final ji.c0 q;
    public final ji.i0 r;
    public final ji.c0 s;
    public final ji.i0 t;
    public final ji.c0 u;
    public final ji.i0 v;
    public final ji.c0 w;
    public final ji.i0 x;
    public final ji.c0 y;
    public final ji.i0 z;

    public q(okhttp3.OkHttpClient p3, kotlinx.coroutines.CoroutineScope p4, okhttp3.OkHttpClient p5)
    {
        int v0_1 = new vA.b(13);
        this.a = p3;
        this.b = p4;
        this.c = p5;
        this.d = v0_1;
        this.e = new kotlin.collections.s();
        this.f = new java.util.concurrent.ConcurrentHashMap();
        this.g = new Object();
        this.h = new java.util.LinkedHashSet();
        this.i = new Object();
        this.j = new Object();
        this.n = -9223372036854775808;
        ji.c0 v3_13 = ji.j0.b(64, 5, 0);
        this.p = v3_13;
        this.q = ji.j0.h(v3_13);
        int v0_2 = ji.j0.b(1, 5, 0);
        this.r = v0_2;
        this.s = ji.j0.h(v0_2);
        int v0_4 = ji.j0.b(1, 5, 0);
        this.t = v0_4;
        this.u = ji.j0.h(v0_4);
        ji.c0 v1_1 = ji.j0.b(16, 5, 0);
        this.v = v1_1;
        this.w = ji.j0.h(v1_1);
        ji.c0 v1_3 = ji.j0.b(16, 5, 0);
        this.x = v1_3;
        this.y = ji.j0.h(v1_3);
        ji.c0 v3_16 = ji.j0.b(1, 5, 0);
        this.z = v3_16;
        this.A = ji.j0.h(v3_16);
        ji.c0 v3_18 = ji.j0.b(16, 5, 0);
        this.B = v3_18;
        this.C = ji.j0.h(v3_18);
        return;
    }

    public static final java.util.Set d(vn.com.vng.zalopay.network_monitor.data.monitor.q p6)
    {
        java.util.Iterator v1_2 = ((Iterable) p6.f.keySet());
        java.util.ArrayList v2_1 = new java.util.ArrayList();
        java.util.Iterator v1_3 = v1_2.iterator();
        while (v1_3.hasNext()) {
            Object v3_1 = v1_3.next();
            if (p6.h.add(((String) v3_1))) {
                v2_1.add(v3_1);
            }
        }
        return kotlin.collections.CollectionsKt.toSet(v2_1);
    }

    public static final Object e(vn.com.vng.zalopay.network_monitor.data.monitor.q p4, java.util.Set p5, wg.c p6)
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.k v0_2;
        if (!(p6 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.k)) {
            v0_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.k(p4, p6);
        } else {
            v0_2 = ((vn.com.vng.zalopay.network_monitor.data.monitor.k) p6);
            Object v1_2 = ((vn.com.vng.zalopay.network_monitor.data.monitor.k) p6).g;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((vn.com.vng.zalopay.network_monitor.data.monitor.k) p6).g = (v1_2 - -2147483648);
            }
        }
        boolean v6_1 = v0_2.e;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        String v2_0 = v0_2.g;
        try {
            if (v2_0 == null) {
                kotlin.ResultKt.throwOnFailure(v6_1);
                vn.com.vng.zalopay.network_monitor.data.monitor.f v4_2 = p4.c.newCall(new okhttp3.Request$Builder().url("https://api.zalopay.vn/v2/user/session/check").get().build());
                v0_2.d = ((java.util.Set) p5);
                v0_2.g = 1;
                v6_1 = vn.com.vng.zalopay.network_monitor.data.monitor.q.l(v4_2, v0_2);
                if (v6_1 == v1_1) {
                    return v1_1;
                }
            } else {
                if (v2_0 != 1) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    p5 = ((java.util.Set) v0_2.d);
                    kotlin.ResultKt.throwOnFailure(v6_1);
                }
            }
        } catch (vn.com.vng.zalopay.network_monitor.data.monitor.f v4_6) {
            if ((v4_6 instanceof java.util.concurrent.CancellationException)) {
                throw v4_6;
            } else {
                java.util.Objects.toString(p5);
                if ((v4_6 instanceof javax.net.ssl.SSLHandshakeException)) {
                    vn.com.vng.zalopay.network_monitor.data.monitor.f v4_8 = vn.com.vng.zalopay.network_monitor.data.monitor.h.a;
                } else {
                    if ((v4_6 instanceof javax.net.ssl.SSLPeerUnverifiedException)) {
                    } else {
                        if ((v4_6 instanceof javax.net.ssl.SSLException)) {
                        } else {
                            if (!(v4_6 instanceof java.security.cert.CertPathValidatorException)) {
                                v4_8 = vn.com.vng.zalopay.network_monitor.data.monitor.f.a;
                            }
                        }
                    }
                }
                return v4_8;
            }
        }
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.i) v6_1);
    }

    public static final Object f(vn.com.vng.zalopay.network_monitor.data.monitor.q p5, String p6, java.util.Set p7, wg.c p8)
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.m v0_2;
        if (!(p8 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.m)) {
            v0_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.m(p5, p8);
        } else {
            v0_2 = ((vn.com.vng.zalopay.network_monitor.data.monitor.m) p8);
            Object v1_2 = ((vn.com.vng.zalopay.network_monitor.data.monitor.m) p8).g;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((vn.com.vng.zalopay.network_monitor.data.monitor.m) p8).g = (v1_2 - -2147483648);
            }
        }
        Boolean v8_1 = v0_2.e;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        int v2_0 = v0_2.g;
        boolean v3_0 = 0;
        try {
            if (v2_0 == 0) {
                kotlin.ResultKt.throwOnFailure(v8_1);
                Boolean v5_2 = p5.a.newCall(new okhttp3.Request$Builder().url(p6).head().build());
                v0_2.d = ((java.util.Set) p7);
                v0_2.g = 1;
                v8_1 = vn.com.vng.zalopay.network_monitor.data.monitor.q.m(v5_2, v0_2);
                if (v8_1 != v1_1) {
                    v3_0 = ((Boolean) v8_1).booleanValue();
                } else {
                    return v1_1;
                }
            } else {
                if (v2_0 != 1) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    p7 = ((java.util.Set) v0_2.d);
                    kotlin.ResultKt.throwOnFailure(v8_1);
                }
            }
        } catch (java.io.IOException) {
            java.util.Objects.toString(p7);
        } catch (Boolean v5_6) {
            if ((v5_6 instanceof java.util.concurrent.CancellationException)) {
                throw v5_6;
            } else {
                java.util.Objects.toString(p7);
            }
        }
        return Boolean.valueOf(v3_0);
    }

    public static final Object g(vn.com.vng.zalopay.network_monitor.data.monitor.q p9, java.util.Set p10, boolean p11, wg.c p12)
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.n v0_2;
        if (!(p12 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.n)) {
            v0_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.n(p9, p12);
        } else {
            v0_2 = ((vn.com.vng.zalopay.network_monitor.data.monitor.n) p12);
            Object v1_2 = ((vn.com.vng.zalopay.network_monitor.data.monitor.n) p12).h;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((vn.com.vng.zalopay.network_monitor.data.monitor.n) p12).h = (v1_2 - -2147483648);
            }
        }
        vn.com.vng.zalopay.network_monitor.data.monitor.i v12_1 = v0_2.f;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        java.util.List v2_0 = v0_2.h;
        int v4_0 = 1;
        try {
            if (v2_0 == null) {
                kotlin.ResultKt.throwOnFailure(v12_1);
                vn.com.vng.zalopay.network_monitor.data.monitor.i v12_3 = new h0.w(p9, p10, 0, 19);
                v0_2.d = ((java.util.Set) p10);
                v0_2.e = p11;
                v0_2.h = 1;
                v12_1 = gi.L.c(v12_3, v0_2);
                if (v12_1 != v1_1) {
                    vn.com.vng.zalopay.network_monitor.data.monitor.i v12_4 = ((kotlin.Pair) v12_1);
                    java.util.List v2_5 = ((java.util.List) v12_4.component1());
                    vn.com.vng.zalopay.network_monitor.data.monitor.i v12_6 = ((vn.com.vng.zalopay.network_monitor.data.monitor.i) v12_4.component2());
                    if ((p11 == null) && (!p9.m)) {
                        if ((!(((Iterable) p10) instanceof java.util.Collection)) || (!((java.util.Collection) ((Iterable) p10)).isEmpty())) {
                            java.util.Iterator v6_3 = ((Iterable) p10).iterator();
                            while (v6_3.hasNext()) {
                                if (p9.f.containsKey(((String) v6_3.next()))) {
                                    if (v4_0 != 0) {
                                        v0_2.d = ((java.util.Set) p10);
                                        v0_2.e = p11;
                                        v0_2.h = 2;
                                        if (p9.k(v2_5, v12_6, v0_2) == v1_1) {
                                            return v1_1;
                                        }
                                    }
                                }
                            }
                        } else {
                        }
                        v4_0 = 0;
                    }
                }
            } else {
                if (v2_0 == 1) {
                    p11 = v0_2.e;
                    p10 = ((java.util.Set) v0_2.d);
                    kotlin.ResultKt.throwOnFailure(v12_1);
                } else {
                    if (v2_0 != 2) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        p10 = ((java.util.Set) v0_2.d);
                        kotlin.ResultKt.throwOnFailure(v12_1);
                    }
                }
            }
        } catch (Throwable v11_2) {
            p9.p(p10);
            throw v11_2;
        }
        p9.p(p10);
        return kotlin.Unit.INSTANCE;
    }

    public static final boolean h(vn.com.vng.zalopay.network_monitor.data.monitor.q p5)
    {
        java.util.Iterator v1_3 = ((Iterable) p5.f.keySet());
        int v3 = 0;
        if ((!(v1_3 instanceof java.util.Collection)) || (!((java.util.Collection) v1_3).isEmpty())) {
            java.util.Iterator v1_1 = v1_3.iterator();
            while (v1_1.hasNext()) {
                if (!p5.h.contains(((String) v1_1.next()))) {
                    v3 = 1;
                    break;
                }
            }
        } else {
        }
        return v3;
    }

    public static Object l(okhttp3.Call p2, vn.com.vng.zalopay.network_monitor.data.monitor.k p3)
    {
        gi.l v0_1 = new gi.l(1, vg.h.intercepted(p3));
        v0_1.p();
        v0_1.r(new vn.com.vng.zalopay.network_monitor.data.monitor.l(p2, 0));
        try {
            Object v2_1 = com.google.firebase.perf.network.FirebasePerfOkHttpClient.execute(p2);
        } catch (Object v2_2) {
            if (!v0_1.isActive()) {
                Object v2_4 = v0_1.o();
                vg.i.getCOROUTINE_SUSPENDED();
                return v2_4;
            } else {
                v0_1.resumeWith(kotlin.ResultKt.createFailure(v2_2));
                v2_4 = v0_1.o();
                vg.i.getCOROUTINE_SUSPENDED();
                return v2_4;
            }
        }
        int v3_2;
        int v3_1 = v2_1.code();
        if ((500 > v3_1) || (v3_1 >= 600)) {
            v3_2 = vn.com.vng.zalopay.network_monitor.data.monitor.e.a;
        } else {
            v3_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.g(v2_1.code());
        }
        if (v0_1.isActive()) {
            v0_1.resumeWith(v3_2);
        }
        kotlin.io.CloseableKt.closeFinally(v2_1, 0);
        v2_4 = v0_1.o();
        vg.i.getCOROUTINE_SUSPENDED();
        return v2_4;
    }

    public static Object m(okhttp3.Call p3, vn.com.vng.zalopay.network_monitor.data.monitor.m p4)
    {
        int v1 = 1;
        gi.l v0_1 = new gi.l(1, vg.h.intercepted(p4));
        v0_1.p();
        v0_1.r(new vn.com.vng.zalopay.network_monitor.data.monitor.l(p3, 1));
        Boolean v3_1 = com.google.firebase.perf.network.FirebasePerfOkHttpClient.execute(p3);
        if (!v3_1.isSuccessful()) {
            if (v3_1.code() != 204) {
                v1 = 0;
            } else {
            }
        }
        Boolean v3_2 = v3_1.body();
        if (v3_2 != null) {
            v3_2.close();
        }
        if (!v0_1.isActive()) {
            Boolean v3_7 = v0_1.o();
            vg.i.getCOROUTINE_SUSPENDED();
            return v3_7;
        } else {
            v0_1.resumeWith(Boolean.valueOf(v1));
            v3_7 = v0_1.o();
            vg.i.getCOROUTINE_SUSPENDED();
            return v3_7;
        }
    }

    public final void a(String p7, Throwable p8)
    {
        try {
            this.f.remove(p7);
            this.h.remove(p7);
        } catch (kotlinx.coroutines.CoroutineScope v0_5) {
            throw v0_5;
        }
        if ((!(p8 instanceof javax.net.ssl.SSLHandshakeException)) && ((!(p8 instanceof javax.net.ssl.SSLPeerUnverifiedException)) && ((!(p8 instanceof javax.net.ssl.SSLException)) && (!(p8 instanceof java.security.cert.CertPathValidatorException))))) {
            if ((!(p8 instanceof java.net.UnknownHostException)) && ((!(p8 instanceof java.net.ConnectException)) && ((!(p8 instanceof java.net.NoRouteToHostException)) && (!(p8 instanceof java.net.SocketException))))) {
                this.r();
                return;
            } else {
                try {
                    kotlinx.coroutines.CoroutineScope v0_1 = 1;
                    int v8_2 = (this.o + 1);
                    this.o = v8_2;
                } catch (kotlinx.coroutines.CoroutineScope v0_3) {
                    throw v0_3;
                }
                if (v8_2 < 2) {
                    v0_1 = 0;
                } else {
                }
                if (v0_1 != null) {
                    this.o = 0;
                }
                if (v0_1 != null) {
                    kotlinx.coroutines.BuildersKt.launch$default(this.b, kotlinx.coroutines.Dispatchers.getIO(), 0, new vn.com.vng.zalopay.network_monitor.data.monitor.p(this, 0, 0), 2, 0);
                    return;
                } else {
                    return;
                }
            }
        } else {
            this.r();
            this.s();
            return;
        }
    }

    public final void b(long p3, String p5)
    {
        try {
            this.f.put(p5, Long.valueOf(p3));
            this.t();
            return;
        } catch (Throwable v3_2) {
            throw v3_2;
        }
    }

    public final void c(String p12, boolean p13, double p14, Integer p16)
    {
        try {
            this.f.remove(p12);
            this.h.remove(p12);
        } catch (Throwable v0_4) {
            throw v0_4;
        }
        if (p16 != null) {
            this.r();
        }
        if ((p16 == null) || (!new kotlin.ranges.IntRange(500, 599, 1).z(p16.intValue()))) {
            int v10 = 0;
        } else {
            v10 = 1;
        }
        int v9;
        int v5_0 = ((Number) this.d.invoke()).longValue();
        if ((p13 == null) || (v10 != 0)) {
            v9 = 0;
        } else {
            v9 = 1;
        }
        ji.i0 v4_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.a(v5_0, p14, v9, v10);
        vn.com.vng.zalopay.network_monitor.data.monitor.b v3_6 = this.e;
        if (v3_6.c >= 20) {
            v3_6.removeFirst();
        }
        this.e.addLast(v4_2);
        this.o();
        this.p.f(this.j());
        if (p13 != null) {
            this.B.f(kotlin.Unit.INSTANCE);
        }
        if ((p16 != null) && (new kotlin.ranges.IntRange(500, 599, 1).z(p16.intValue()))) {
            this.s();
        }
        return;
    }

    public final boolean i()
    {
        int v0_5 = ((Number) this.d.invoke()).longValue();
        long v2_2 = this.n;
        if ((v2_2 == -9223372036854775808) || ((v0_5 - v2_2) >= 5000)) {
            this.n = v0_5;
            return 1;
        } else {
            return 0;
        }
    }

    public final vn.com.vng.zalopay.network_monitor.data.monitor.b j()
    {
        kotlin.collections.s v0 = this.e;
        if (!v0.isEmpty()) {
            int v5_0;
            int v2_0 = 0;
            if (!v0.isEmpty()) {
                double v1_9 = v0.iterator();
                Double v3_0 = 0;
                while (v1_9.hasNext()) {
                    vn.com.vng.zalopay.network_monitor.data.monitor.b v4_2 = ((vn.com.vng.zalopay.network_monitor.data.monitor.a) v1_9.next());
                    if ((!v4_2.c) || (v4_2.d)) {
                        v3_0++;
                        if (v3_0 < null) {
                            kotlin.collections.E.throwCountOverflow();
                        }
                    }
                }
                v5_0 = v3_0;
            } else {
                v5_0 = 0;
            }
            if (!v0.isEmpty()) {
                double v1_2 = v0.iterator();
                while (v1_2.hasNext()) {
                    if (((vn.com.vng.zalopay.network_monitor.data.monitor.a) v1_2.next()).d) {
                        v2_0++;
                        if (v2_0 < 0) {
                            kotlin.collections.E.throwCountOverflow();
                        }
                    }
                }
            }
            int v8 = v2_0;
            double v1_4 = new java.util.ArrayList();
            int v2_1 = v0.iterator();
            while (v2_1.hasNext()) {
                Double v3_13 = v2_1.next();
                vn.com.vng.zalopay.network_monitor.data.monitor.b v4_7 = ((vn.com.vng.zalopay.network_monitor.data.monitor.a) v3_13);
                if ((v4_7.c) && (!v4_7.d)) {
                    v1_4.add(v3_13);
                }
            }
            int v2_3 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v1_4, 10));
            double v1_6 = v1_4.iterator();
            while (v1_6.hasNext()) {
                v2_3.add(Double.valueOf(((vn.com.vng.zalopay.network_monitor.data.monitor.a) v1_6.next()).b));
            }
            double v1_8;
            if (!v2_3.isEmpty()) {
                v1_8 = kotlin.collections.CollectionsKt.e(v2_3);
            } else {
                v1_8 = 0;
            }
            return new vn.com.vng.zalopay.network_monitor.data.monitor.b(v5_0, v1_8, v8, v0.c);
        } else {
            return new vn.com.vng.zalopay.network_monitor.data.monitor.b(0, 0, 0, 0);
        }
    }

    public final Object k(java.util.List p4, vn.com.vng.zalopay.network_monitor.data.monitor.i p5, vn.com.vng.zalopay.network_monitor.data.monitor.n p6)
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.e v0 = vn.com.vng.zalopay.network_monitor.data.monitor.e.a;
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p5, v0)) {
            int v2 = 0;
            if ((!(((Iterable) p4) instanceof java.util.Collection)) || (!((java.util.Collection) ((Iterable) p4)).isEmpty())) {
                kotlin.NoWhenBranchMatchedException v4_1 = ((Iterable) p4).iterator();
                while (v4_1.hasNext()) {
                    if (((Boolean) v4_1.next()).booleanValue()) {
                        v2++;
                        if (v2 < 0) {
                            kotlin.collections.E.throwCountOverflow();
                        }
                    }
                }
            }
            if (v2 >= 2) {
                if (!kotlin.jvm.internal.Intrinsics.areEqual(p5, vn.com.vng.zalopay.network_monitor.data.monitor.f.a)) {
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(p5, vn.com.vng.zalopay.network_monitor.data.monitor.h.a)) {
                        if (!(p5 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.g)) {
                            if (!kotlin.jvm.internal.Intrinsics.areEqual(p5, v0)) {
                                throw new kotlin.NoWhenBranchMatchedException();
                            } else {
                                return kotlin.Unit.INSTANCE;
                            }
                        } else {
                            kotlin.NoWhenBranchMatchedException v4_15 = this.v.emit(new Integer(((vn.com.vng.zalopay.network_monitor.data.monitor.g) p5).a), p6);
                            if (v4_15 != vg.i.getCOROUTINE_SUSPENDED()) {
                                return kotlin.Unit.INSTANCE;
                            } else {
                                return v4_15;
                            }
                        }
                    } else {
                        kotlin.NoWhenBranchMatchedException v4_17 = kotlin.Unit.INSTANCE;
                        Object v5_6 = this.x.emit(v4_17, p6);
                        if (v5_6 != vg.i.getCOROUTINE_SUSPENDED()) {
                            return v4_17;
                        } else {
                            return v5_6;
                        }
                    }
                } else {
                    kotlin.NoWhenBranchMatchedException v4_18 = kotlin.Unit.INSTANCE;
                    Object v5_8 = this.r.emit(v4_18, p6);
                    if (v5_8 != vg.i.getCOROUTINE_SUSPENDED()) {
                        return v4_18;
                    } else {
                        return v5_8;
                    }
                }
            } else {
                kotlin.NoWhenBranchMatchedException v4_19 = kotlin.Unit.INSTANCE;
                Object v5_10 = this.t.emit(v4_19, p6);
                if (v5_10 != vg.i.getCOROUTINE_SUSPENDED()) {
                    return v4_19;
                } else {
                    return v5_10;
                }
            }
        } else {
            return kotlin.Unit.INSTANCE;
        }
    }

    public final void n(vn.com.vng.zalopay.network_monitor.data.monitor.d p8)
    {
        int v0_1;
        if (p8 != vn.com.vng.zalopay.network_monitor.data.monitor.d.a) {
            v0_1 = vn.com.vng.zalopay.network_monitor.data.monitor.c.b;
        } else {
            v0_1 = vn.com.vng.zalopay.network_monitor.data.monitor.c.a;
        }
        this.l = v0_1;
        this.m = 0;
        this.k = kotlinx.coroutines.BuildersKt.launch$default(this.b, 0, 0, new vn.com.vng.zalopay.network_monitor.data.monitor.o(p8, this, 0), 3, 0);
        return;
    }

    public final void o()
    {
        long v0_4 = (((Number) this.d.invoke()).longValue() - 60000);
        while(true) {
            kotlin.collections.s v2_1 = this.e;
            if ((v2_1.isEmpty()) || (((vn.com.vng.zalopay.network_monitor.data.monitor.a) v2_1.first()).a >= v0_4)) {
                break;
            }
            v2_1.removeFirst();
        }
        return;
    }

    public final void p(java.util.Set p4)
    {
        if (!p4.isEmpty()) {
            kotlin.Unit v4_3 = ((Iterable) p4).iterator();
            while (v4_3.hasNext()) {
                String v1_1 = ((String) v4_3.next());
                this.f.remove(v1_1);
                this.h.remove(v1_1);
            }
            return;
        } else {
            return;
        }
    }

    public final void q()
    {
        try {
            this.e.clear();
            Object v0 = this.g;
            try {
                this.f.clear();
                this.h.clear();
                this.r();
            } catch (long v1_1) {
                throw v1_1;
            }
            long v1_2 = this.k;
            if (v1_2 != 0) {
                v1_2.cancel(0);
            }
            this.k = 0;
            this.l = 0;
            this.m = 0;
            this.n = -9223372036854775808;
            return;
        } catch (long v1_6) {
            throw v1_6;
        }
    }

    public final void r()
    {
        try {
            this.o = 0;
            return;
        } catch (Throwable v1_1) {
            throw v1_1;
        }
    }

    public final void s()
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.d v1_10;
        vn.com.vng.zalopay.network_monitor.data.monitor.d v1_0 = this.l;
        if (v1_0 != null) {
            v1_10 = vn.com.vng.zalopay.network_monitor.data.monitor.j.a[v1_0.ordinal()];
        } else {
            v1_10 = -1;
        }
        if (v1_10 == -1) {
            if (this.i()) {
                this.n(vn.com.vng.zalopay.network_monitor.data.monitor.d.b);
            } else {
                return;
            }
        } else {
            if (v1_10 == 1) {
                if (this.i()) {
                    vn.com.vng.zalopay.network_monitor.data.monitor.d v1_4 = this.k;
                    if (v1_4 != null) {
                        v1_4.cancel(0);
                    }
                    this.n(vn.com.vng.zalopay.network_monitor.data.monitor.d.b);
                } else {
                    return;
                }
            } else {
                if (v1_10 != 2) {
                    throw new kotlin.NoWhenBranchMatchedException();
                } else {
                    this.m = 1;
                }
            }
        }
        return;
    }

    public final void t()
    {
        try {
            if (this.k == null) {
                this.n(vn.com.vng.zalopay.network_monitor.data.monitor.d.a);
                return;
            } else {
                return;
            }
        } catch (kotlin.Unit v1_2) {
            throw v1_2;
        }
    }
}

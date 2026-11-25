package vn.com.vng.zalopay.app_widget.presentation.ui.game;
public final class OpenGameWidgetAction implements C2.a {
    public static final A2.c a;
    public static final A2.c b;
    public static final A2.c c;

    static OpenGameWidgetAction()
    {
        vn.com.vng.zalopay.app_widget.presentation.ui.game.OpenGameWidgetAction.a = new A2.c("game_id_key");
        vn.com.vng.zalopay.app_widget.presentation.ui.game.OpenGameWidgetAction.b = new A2.c("game_deeplink_key");
        vn.com.vng.zalopay.app_widget.presentation.ui.game.OpenGameWidgetAction.c = new A2.c("game_need_reload_key");
        return;
    }

    public OpenGameWidgetAction()
    {
        return;
    }

    public final Object a(android.content.Context p18, A2.f p19, kotlin.coroutines.Continuation p20)
    {
        jn.f v3_1;
        IllegalStateException v0_0 = p18;
        if (!(p20 instanceof jn.f)) {
            v3_1 = new jn.f(this, ((wg.c) p20));
        } else {
            v3_1 = ((jn.f) p20);
            vn.com.vng.zalopay.app_widget.presentation.ui.game.OpenGameWidgetAction v4_2 = ((jn.f) p20).m;
            if ((v4_2 & -2147483648) == 0) {
            } else {
                ((jn.f) p20).m = (v4_2 - -2147483648);
            }
        }
        java.util.Iterator v2_7;
        Object v6_8;
        Object v6_7;
        z2.x v9_1;
        int v1_1;
        java.util.Iterator v2_0;
        int v1_2;
        Object v6_0;
        z2.x v9_0;
        IllegalStateException v0_3;
        int v1_4;
        IllegalStateException v7_5;
        android.content.Context v10_1;
        IllegalStateException v0_1;
        z2.x v11_1;
        java.util.Iterator v2_4 = v3_1.k;
        Object v5_0 = vg.i.getCOROUTINE_SUSPENDED();
        Object v6_10 = v3_1.m;
        if (v6_10 == null) {
            kotlin.ResultKt.throwOnFailure(v2_4);
            v2_0 = ((String) p19.a.get(vn.com.vng.zalopay.app_widget.presentation.ui.game.OpenGameWidgetAction.a));
            Object v6_12 = "";
            if (v2_0 == null) {
                v2_0 = "";
            }
            int v1_8 = p19.a;
            java.util.Map v14_3 = ((String) v1_8.get(vn.com.vng.zalopay.app_widget.presentation.ui.game.OpenGameWidgetAction.b));
            if (v14_3 != null) {
                v6_12 = v14_3;
            }
            int v1_10 = ((Boolean) v1_8.get(vn.com.vng.zalopay.app_widget.presentation.ui.game.OpenGameWidgetAction.c));
            if (v1_10 == 0) {
                v1_1 = 0;
            } else {
                v1_1 = v1_10.booleanValue();
            }
            java.util.Map v14_8 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("type", qn.b.d), kotlin.TuplesKt.to("gameId", v2_0)}));
            kotlin.collections.a0.plus(v14_8, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("os", "android")));
            java.util.Objects.toString(v14_8);
            UR.a.e("01.8999.031", v14_8);
            try {
                if (v6_12.length() > 0) {
                    q9.r6.b(p18, v6_12);
                }
            } catch (Exception) {
            }
            if (v1_1 != 0) {
                q9.w5.a(p18);
            }
            jn.d.k.getClass();
            Object v6_15 = jn.b.a(p18).getData();
            v3_1.d = p18;
            v3_1.e = v2_0;
            v3_1.h = v1_1;
            v3_1.m = 1;
            v6_0 = ji.j0.u(v6_15, v3_1);
            if (v6_0 != v5_0) {
                Object v6_1 = ((u2.b) v6_0);
                jn.d.k.getClass();
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v6_1.a.get(jn.d.n), v2_0)) {
                    Object v6_5 = jn.b.a(v0_0);
                    IllegalStateException v7_3 = new Zb.A(1, v2_0, 0);
                    v3_1.d = v0_0;
                    v3_1.e = v2_0;
                    v3_1.h = v1_1;
                    v3_1.m = 2;
                    if (com.google.android.gms.internal.mlkit_vision_barcode.N7.b(v6_5, v7_3, v3_1) != v5_0) {
                        v6_7 = v0_0;
                        v0_1 = v1_1;
                        v1_2 = v2_0;
                        java.util.Iterator v2_3 = new B2.U(v6_7);
                        v3_1.d = v6_7;
                        v3_1.e = v1_2;
                        v3_1.h = v0_1;
                        v3_1.m = 3;
                        v2_4 = v2_3.a(jn.d, v3_1);
                        if (v2_4 != v5_0) {
                            v2_7 = ((Iterable) ((java.util.List) v2_4)).iterator();
                            v7_5 = 0;
                            if (v2_7.hasNext()) {
                                v9_0 = ((z2.x) v2_7.next());
                                android.content.Context v10_3 = new Zb.A(2, v1_2, 0);
                                v3_1.d = v6_7;
                                v3_1.e = v1_2;
                                v3_1.f = v2_7;
                                v3_1.g = v9_0;
                                v3_1.h = v0_1;
                                v3_1.i = v7_5;
                                v3_1.j = 0;
                                v3_1.m = 4;
                                if (com.google.android.gms.internal.mlkit_vision_barcode.V5.c(v6_7, v9_0, v10_3, v3_1) != v5_0) {
                                    v11_1 = v1_2;
                                    v10_1 = v6_7;
                                    v1_4 = 0;
                                    v6_8 = v0_1;
                                    v0_3 = v7_5;
                                    IllegalStateException v7_7 = new jn.d();
                                    v3_1.d = v10_1;
                                    v3_1.e = v11_1;
                                    v3_1.f = v2_7;
                                    v3_1.g = 0;
                                    v3_1.h = v6_8;
                                    v3_1.i = v0_3;
                                    v3_1.j = v1_4;
                                    v3_1.m = 5;
                                    if (v7_7.d(v10_1, v9_0, v3_1) != v5_0) {
                                        v7_5 = v0_3;
                                        v0_1 = v6_8;
                                        v9_1 = v11_1;
                                        v6_7 = v10_1;
                                        v1_2 = v9_1;
                                    } else {
                                        return v5_0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (v6_10 == 1) {
                IllegalStateException v0_8 = v3_1.h;
                int v1_11 = v3_1.e;
                Object v6_17 = v3_1.d;
                kotlin.ResultKt.throwOnFailure(v2_4);
                v1_1 = v0_8;
                v0_0 = v6_17;
                v6_0 = v2_4;
                v2_0 = v1_11;
            } else {
                if (v6_10 == 2) {
                    v0_1 = v3_1.h;
                    v1_2 = v3_1.e;
                    v6_7 = v3_1.d;
                    kotlin.ResultKt.throwOnFailure(v2_4);
                } else {
                    if (v6_10 == 3) {
                        v0_1 = v3_1.h;
                        v1_2 = v3_1.e;
                        v6_7 = v3_1.d;
                        kotlin.ResultKt.throwOnFailure(v2_4);
                    } else {
                        if (v6_10 == 4) {
                            IllegalStateException v0_2 = v3_1.j;
                            int v1_3 = v3_1.i;
                            v6_8 = v3_1.h;
                            v9_0 = v3_1.g;
                            android.content.Context v10_0 = v3_1.f;
                            v11_1 = v3_1.e;
                            java.util.Map v14_0 = v3_1.d;
                            kotlin.ResultKt.throwOnFailure(v2_4);
                            v1_4 = v0_2;
                            v0_3 = v1_3;
                            v2_7 = v10_0;
                            v10_1 = v14_0;
                        } else {
                            if (v6_10 != 5) {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            } else {
                                IllegalStateException v0_6 = v3_1.i;
                                int v1_6 = v3_1.h;
                                Object v6_9 = v3_1.f;
                                v9_1 = v3_1.e;
                                v10_1 = v3_1.d;
                                kotlin.ResultKt.throwOnFailure(v2_4);
                                v7_5 = v0_6;
                                v0_1 = v1_6;
                                v2_7 = v6_9;
                            }
                        }
                    }
                }
            }
        }
        return kotlin.Unit.INSTANCE;
    }
}

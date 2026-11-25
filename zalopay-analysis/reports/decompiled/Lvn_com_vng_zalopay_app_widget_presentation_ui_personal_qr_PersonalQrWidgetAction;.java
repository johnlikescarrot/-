package vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr;
public final class PersonalQrWidgetAction implements C2.a {
    public static final A2.c a;
    public static final A2.c b;

    static PersonalQrWidgetAction()
    {
        vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQrWidgetAction.a = new A2.c(wmoi.igob.ggcn(7030));
        vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQrWidgetAction.b = new A2.c(wmoi.igob.ggcn(7031));
        return;
    }

    public PersonalQrWidgetAction()
    {
        return;
    }

    public final Object a(android.content.Context p12, A2.f p13, kotlin.coroutines.Continuation p14)
    {
        nn.j v0_1;
        if (!(p14 instanceof nn.j)) {
            v0_1 = new nn.j(this, ((wg.c) p14));
        } else {
            v0_1 = ((nn.j) p14);
            Object v1_2 = ((nn.j) p14).l;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((nn.j) p14).l = (v1_2 - -2147483648);
            }
        }
        kotlin.Unit v5_1;
        String v14_12;
        z2.x v2_0;
        java.util.Map v8_0;
        java.util.Map v13_9;
        z2.x v2_1;
        java.util.Map v13_7;
        IllegalStateException v12_3;
        String v14_8 = v0_1.j;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        z2.x v2_2 = v0_1.l;
        if (v2_2 == null) {
            kotlin.ResultKt.throwOnFailure(v14_8);
            String v14_16 = ((String) p13.a.get(vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQrWidgetAction.a));
            if (v14_16 != null) {
                java.util.Map v13_15 = ((String) p13.a.get(vn.com.vng.zalopay.app_widget.presentation.ui.personal_qr.PersonalQrWidgetAction.b));
                if (v13_15 == null) {
                    v13_15 = nn.i.b;
                }
                q9.r6.b(p12, v14_16);
                String v14_18 = kotlin.jvm.internal.Intrinsics.areEqual(v13_15, wmoi.igob.ggcn(7033));
                z2.x v2_6 = wmoi.igob.ggcn(7034);
                java.util.Map v8_5 = wmoi.igob.ggcn(7035);
                if (v14_18 == null) {
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v13_15, wmoi.igob.ggcn(7037))) {
                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v13_15, wmoi.igob.ggcn(7039))) {
                            if (!kotlin.jvm.internal.Intrinsics.areEqual(v13_15, wmoi.igob.ggcn(7041))) {
                                java.util.Map v13_17 = kotlin.collections.a0.emptyMap();
                                kotlin.collections.a0.plus(v13_17, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(v8_5, v2_6)));
                                java.util.Objects.toString(v13_17);
                                UR.a.e(wmoi.igob.ggcn(7043), v13_17);
                            } else {
                                java.util.Map v13_18 = kotlin.collections.a0.emptyMap();
                                kotlin.collections.a0.plus(v13_18, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(v8_5, v2_6)));
                                java.util.Objects.toString(v13_18);
                                UR.a.e(wmoi.igob.ggcn(7042), v13_18);
                            }
                        } else {
                            java.util.Map v13_19 = kotlin.collections.a0.emptyMap();
                            kotlin.collections.a0.plus(v13_19, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(v8_5, v2_6)));
                            java.util.Objects.toString(v13_19);
                            UR.a.e(wmoi.igob.ggcn(7040), v13_19);
                        }
                    } else {
                        java.util.Map v13_1 = kotlin.collections.a0.emptyMap();
                        kotlin.collections.a0.plus(v13_1, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(v8_5, v2_6)));
                        java.util.Objects.toString(v13_1);
                        UR.a.e(wmoi.igob.ggcn(7038), v13_1);
                    }
                } else {
                    java.util.Map v13_2 = kotlin.collections.a0.emptyMap();
                    kotlin.collections.a0.plus(v13_2, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(v8_5, v2_6)));
                    java.util.Objects.toString(v13_2);
                    UR.a.e(wmoi.igob.ggcn(7036), v13_2);
                }
                java.util.Map v13_4 = new B2.U(p12);
                v0_1.d = p12;
                v0_1.l = 1;
                v14_8 = v13_4.a(nn.h, v0_1);
                if (v14_8 != v1_1) {
                    String v14_10 = ((java.util.List) v14_8);
                    v13_7 = Wm.a.b.i(p12).a();
                    v14_12 = ((Iterable) v14_10).iterator();
                    v2_0 = 0;
                    if (!v14_12.hasNext()) {
                        return kotlin.Unit.INSTANCE;
                    } else {
                        kotlin.Unit v5_4 = ((z2.x) v14_12.next());
                        java.util.Map v8_2 = new cz.H(v13_7, 0, 24);
                        v0_1.d = p12;
                        v0_1.e = v13_7;
                        v0_1.f = v14_12;
                        v0_1.g = v5_4;
                        v0_1.h = v2_0;
                        v0_1.i = 0;
                        v0_1.l = 2;
                        if (com.google.android.gms.internal.mlkit_vision_barcode.V5.c(p12, v5_4, v8_2, v0_1) != v1_1) {
                            v5_1 = p12;
                            v12_3 = v2_0;
                            v2_1 = v5_4;
                            v8_0 = v13_7;
                            v13_9 = 0;
                            int v9_3 = new nn.h();
                            v0_1.d = v5_1;
                            v0_1.e = v8_0;
                            v0_1.f = v14_12;
                            v0_1.g = 0;
                            v0_1.h = v12_3;
                            v0_1.i = v13_9;
                            v0_1.l = 3;
                            if (v9_3.d(v5_1, v2_1, v0_1) != v1_1) {
                                v13_7 = v8_0;
                                v2_0 = v12_3;
                                p12 = v5_1;
                            }
                        }
                    }
                }
            } else {
                return kotlin.Unit.INSTANCE;
            }
        } else {
            if (v2_2 == 1) {
                p12 = v0_1.d;
                kotlin.ResultKt.throwOnFailure(v14_8);
            } else {
                if (v2_2 == 2) {
                    IllegalStateException v12_2 = v0_1.i;
                    java.util.Map v13_8 = v0_1.h;
                    v2_1 = v0_1.g;
                    kotlin.Unit v5_0 = v0_1.f;
                    v8_0 = v0_1.e;
                    int v9_0 = v0_1.d;
                    kotlin.ResultKt.throwOnFailure(v14_8);
                    v13_9 = v12_2;
                    v12_3 = v13_8;
                    v14_12 = v5_0;
                    v5_1 = v9_0;
                } else {
                    if (v2_2 != 3) {
                        throw new IllegalStateException(wmoi.igob.ggcn(7032));
                    } else {
                        v12_3 = v0_1.h;
                        java.util.Map v13_11 = v0_1.f;
                        z2.x v2_3 = v0_1.e;
                        v5_1 = v0_1.d;
                        kotlin.ResultKt.throwOnFailure(v14_8);
                        v14_12 = v13_11;
                        v13_7 = v2_3;
                    }
                }
            }
        }
        return v1_1;
    }
}

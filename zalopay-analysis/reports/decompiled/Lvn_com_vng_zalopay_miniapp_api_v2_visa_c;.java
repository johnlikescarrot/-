package vn.com.vng.zalopay.miniapp.api.v2.visa;
public final class c implements LR.a {
    public static final IF.d e = None;
    public static final int f = 8;
    private static final byte[] g;
    private static volatile boolean h;
    private final java.util.concurrent.ExecutorService a;
    private ref.WeakReference b;
    private MR.b c;
    private jQ.E d;

    static c()
    {
        vn.com.vng.zalopay.miniapp.api.v2.visa.c.e = new IF.d();
        byte[] v0_3 = new byte[2];
        v0_3 = {111, 0};
        vn.com.vng.zalopay.miniapp.api.v2.visa.c.g = v0_3;
        return;
    }

    public c()
    {
        this.a = java.util.concurrent.Executors.newSingleThreadExecutor(new IF.c(0));
        this.d = jQ.E.a;
        return;
    }

    private final void D(MR.b p4, String p5, jQ.E p6, gQ.b p7, Boolean p8)
    {
        JF.B v0_0 = this.d;
        if ((v0_0 != jQ.E.b) || (v0_0 != p6)) {
            this.d = p6;
            if (p4 != null) {
                int v7_1;
                String v6_1 = p6.name();
                if (p7 == 0) {
                    v7_1 = 0;
                } else {
                    v7_1 = androidx.activity.result.e.o(p7.a, " - ", p7.b);
                }
                p4.g(new JF.B(p5, v6_1, v7_1, p8).a());
            }
        }
        return;
    }

    public static synthetic void E(vn.com.vng.zalopay.miniapp.api.v2.visa.c p1, MR.b p2, String p3, jQ.E p4, gQ.b p5, Boolean p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p2 = p1.c;
        }
        if ((p7 & 2) != 0) {
            p3 = 0;
        }
        if ((p7 & 8) != 0) {
            p5 = 0;
        }
        int v7_2;
        MR.b v4_1;
        vn.com.vng.zalopay.miniapp.api.v2.visa.c v5_1;
        MR.b v6_1;
        jQ.E v8_1;
        void v3_1;
        if ((p7 & 16) == 0) {
            v8_1 = p6;
            v7_2 = p5;
            v5_1 = p3;
            v6_1 = p4;
            v3_1 = p1;
            v4_1 = p2;
        } else {
            v8_1 = 0;
            v6_1 = p4;
            v7_2 = p5;
            v4_1 = p2;
            v5_1 = p3;
            v3_1 = p1;
        }
        v3_1.D(v4_1, v5_1, v6_1, v7_2, v8_1);
        return;
    }

    public static synthetic Thread b(Runnable p0)
    {
        return vn.com.vng.zalopay.miniapp.api.v2.visa.c.h(p0);
    }

    public static final synthetic void c(vn.com.vng.zalopay.miniapp.api.v2.visa.c p0, String p1, QR.d p2, gQ.b p3, MR.b p4)
    {
        p0.j(p1, p2, p3, p4);
        return;
    }

    public static final synthetic java.util.concurrent.ExecutorService d(vn.com.vng.zalopay.miniapp.api.v2.visa.c p0)
    {
        return p0.a;
    }

    public static final synthetic byte[] e()
    {
        return vn.com.vng.zalopay.miniapp.api.v2.visa.c.g;
    }

    public static final synthetic boolean f()
    {
        return vn.com.vng.zalopay.miniapp.api.v2.visa.c.h;
    }

    public static final synthetic void g(vn.com.vng.zalopay.miniapp.api.v2.visa.c p0, MR.b p1, String p2, jQ.E p3, gQ.b p4, Boolean p5)
    {
        p0.D(p1, p2, p3, p4, p5);
        return;
    }

    private static final Thread h(Runnable p2)
    {
        return new Thread(p2, "VisaMiniAppApdu");
    }

    private final void j(String p2, QR.d p3, gQ.b p4, MR.b p5)
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p4, gQ.b.c)) {
            if (kotlin.text.StringsKt.isBlank(p4.a)) {
                if (p5 != null) {
                    p5.h(p3);
                }
            } else {
                p4.a(p2);
                if (p5 != null) {
                    p5.h(new H8.n(p4.a, p4.b, 1));
                    return;
                }
            }
        } else {
            if (p5 != null) {
                p5.h(JF.L.a);
                return;
            }
        }
        return;
    }

    private final void k()
    {
        fQ.g.e = 0;
        fQ.g.g.set(0);
        this.c = 0;
        return;
    }

    private final void l(org.json.JSONObject p7, MR.b p8)
    {
        JF.a v0_0 = 0;
        if (p7 != null) {
            com.visa.visapay.common.service.request.BaseParams v1_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p7);
            if (v1_3 != null) {
                JF.J v5_2;
                String v2_5 = p7.optJSONObject("baseParams");
                if (v2_5 != null) {
                    String v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v2_5);
                    if (v3_1 != null) {
                        iQ.f v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v2_5);
                        if (v4_1 != null) {
                            String v2_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v2_5);
                            if (v2_0 != null) {
                                v5_2 = new JF.J(v3_1, v4_1, v2_0);
                            } else {
                                v5_2 = 0;
                            }
                        }
                    }
                }
                if (v5_2 != null) {
                    JF.a v7_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("panEnrollmentId", p7);
                    if (v7_1 != null) {
                        v0_0 = new JF.a(v1_3, v5_2, v7_1);
                    }
                }
            }
        }
        if (v0_0 != null) {
            JF.a v7_2 = v0_0.a;
            com.visa.visapay.common.service.request.BaseParams v1_2 = v0_0.b.a();
            JF.a v0_2 = v0_0.c;
            String v2_3 = new IF.e(p8, this, 0);
            gQ.b v8_2 = fQ.g.b.a;
            if (v8_2 != null) {
                gQ.b v8_3 = v8_2.getCardService();
                if (v8_3 != null) {
                    try {
                        v8_3.fetchCardArt(v7_2, v1_2.a(), v0_2, new iQ.f(v2_3, 0));
                        return;
                    } catch (JF.a v7_3) {
                        JF.a v7_4 = v7_3.getMessage();
                        if (v7_4 == null) {
                            v7_4 = "";
                        }
                        v2_3.a(new gQ.b("", v7_4));
                        return;
                    }
                }
            }
            v2_3.a(gQ.b.c);
            return;
        } else {
            if (p8 != null) {
                p8.h(ZF.m.c);
            }
            return;
        }
    }

    private final void m(org.json.JSONObject p9, MR.b p10)
    {
        String v0_0 = 0;
        if (p9 != null) {
            com.visa.visapaysdk.service.EnrolmentService v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p9);
            if (v1_1 != null) {
                JF.J v5_2;
                String v2_5 = p9.optJSONObject("baseParams");
                if (v2_5 != null) {
                    String v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v2_5);
                    if (v3_1 != null) {
                        com.visa.visapay.common.service.request.BaseParams v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v2_5);
                        if (v4_1 != null) {
                            String v2_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v2_5);
                            if (v2_0 != null) {
                                v5_2 = new JF.J(v3_1, v4_1, v2_0);
                            } else {
                                v5_2 = 0;
                            }
                        }
                    }
                }
                if (v5_2 != null) {
                    String v9_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("accountCorrelationId", p9);
                    if (v9_1 != null) {
                        v0_0 = new JF.d(v1_1, v5_2, v9_1);
                    }
                }
            }
        }
        if (v0_0 != null) {
            String v9_2 = this.b;
            if (v9_2 != null) {
                String v2_3 = ((androidx.fragment.app.FragmentActivity) v9_2.get());
                if (v2_3 != null) {
                    String v3_2 = v0_0.a;
                    String v9_5 = v0_0.b.a();
                    JF.J v5_3 = v0_0.c;
                    IF.e v7_1 = new IF.e(p10, this, 1);
                    gQ.b v10_2 = fQ.g.b.a;
                    if (v10_2 != null) {
                        com.visa.visapaysdk.service.EnrolmentService v1_2 = v10_2.getEnrolmentService();
                        if (v1_2 != null) {
                            try {
                                v1_2.generateCard(v2_3, v3_2, v9_5.a(), v5_3, new iQ.f(v7_1, 1));
                                return;
                            } catch (String v0_3) {
                                String v9_7 = v0_3.getMessage();
                                if (v9_7 == null) {
                                    v9_7 = "";
                                }
                                v7_1.a(new gQ.b("", v9_7));
                                return;
                            }
                        }
                    }
                    v7_1.a(gQ.b.c);
                    return;
                }
            }
            if (p10 != null) {
                p10.h(ZF.m.c);
            }
        } else {
            if (p10 != null) {
                p10.h(ZF.m.c);
                return;
            }
        }
        return;
    }

    private final void n(org.json.JSONObject p9, MR.b p10)
    {
        JF.j v0_0 = 0;
        if (p9 != null) {
            jQ.a v3_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p9);
            if (v3_0 != null) {
                JF.J v4_2;
                String v1_8 = p9.optJSONObject("baseParams");
                if (v1_8 != null) {
                    JF.j v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v1_8);
                    if (v2_1 != null) {
                        JF.J v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v1_8);
                        if (v4_1 != null) {
                            String v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v1_8);
                            if (v1_1 != null) {
                                v4_2 = new JF.J(v2_1, v4_1, v1_1);
                            } else {
                                v4_2 = 0;
                            }
                        }
                    }
                }
                if (v4_2 != null) {
                    JF.J v5_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("cardReferenceId", p9);
                    if (v5_3 != null) {
                        String v6_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("cardCvv", p9);
                        String v7_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("presentationType", p9);
                        if (v7_0 != null) {
                            v0_0 = new JF.j(v3_0, v4_2, v5_3, v6_0, v7_0);
                        }
                    }
                }
            }
        }
        if (v0_0 != null) {
            ZF.m v9_1 = this.b;
            if (v9_1 != null) {
                String v1_6 = ((androidx.fragment.app.FragmentActivity) v9_1.get());
                if (v1_6 != null) {
                    JF.j v2_4 = v0_0.a;
                    jQ.a v3_1 = v0_0.b.a();
                    JF.J v4_3 = v0_0.c;
                    JF.J v5_4 = v0_0.d;
                    JF.j v0_1 = v0_0.e;
                    jQ.u.a.getClass();
                    fQ.g.d(v1_6, v2_4, v3_1, v4_3, v5_4, o9.z.q(v0_1), new IF.e(p10, this, 2));
                    return;
                }
            }
            if (p10 != null) {
                p10.h(ZF.m.c);
            }
        } else {
            if (p10 != null) {
                p10.h(ZF.m.c);
                return;
            }
        }
        return;
    }

    private final void o(org.json.JSONObject p7, MR.b p8)
    {
        JF.l v0_0 = 0;
        if (p7) {
            jQ.a v1_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p7);
            if (v1_3 != null) {
                JF.J v5_2;
                String v2_5 = p7.optJSONObject("baseParams");
                if (v2_5 != null) {
                    int v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v2_5);
                    if (v3_1 != 0) {
                        String v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v2_5);
                        if (v4_1 != null) {
                            String v2_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v2_5);
                            if (v2_0 != null) {
                                v5_2 = new JF.J(v3_1, v4_1, v2_0);
                            } else {
                                v5_2 = 0;
                            }
                        }
                    }
                }
                if (v5_2 != null) {
                    v0_0 = new JF.l(v1_3, v5_2, p7.optBoolean("syncRequired", 0));
                }
            }
        }
        if (v0_0 != null) {
            fQ.g.e(v0_0.a, v0_0.b.a(), new IF.e(p8, this, 3), v0_0.c);
            return;
        } else {
            if (p8 != null) {
                p8.h(ZF.m.c);
            }
            return;
        }
    }

    private final void p(org.json.JSONObject p6, MR.b p7)
    {
        String v6_3;
        jQ.a v0 = 0;
        if (p6 != null) {
            String v1_2 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p6);
            if (v1_2 != null) {
                JF.J v4_2;
                String v6_8 = p6.optJSONObject("baseParams");
                if (v6_8 != null) {
                    int v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v6_8);
                    if (v2_1 != 0) {
                        String v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v6_8);
                        if (v3_1 != null) {
                            String v6_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v6_8);
                            if (v6_1 != null) {
                                v4_2 = new JF.J(v2_1, v3_1, v6_1);
                            } else {
                                v4_2 = 0;
                            }
                        }
                    }
                }
                v6_3 = new JF.o(v1_2, v4_2);
            } else {
                v6_3 = 0;
            }
        }
        if (v6_3 != null) {
            String v1_1 = v6_3.a;
            String v6_4 = v6_3.b;
            if (v6_4 != null) {
                v0 = v6_4.a();
            }
            fQ.g.f(v1_1, v0, new IF.e(p7, this, 4));
            return;
        } else {
            if (p7 != null) {
                p7.h(ZF.m.c);
            }
            return;
        }
    }

    private final void q(org.json.JSONObject p5, MR.b p6)
    {
        String v0_0 = 0;
        if (p5 != null) {
            String v1_2 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerIdentifier", p5);
            if (v1_2 != null) {
                String v2_2 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", p5);
                if (v2_2 != null) {
                    JF.L v5_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("profileAppId", p5);
                    if (v5_1 != null) {
                        v0_0 = new JF.r(v1_2, v2_2, v5_1);
                    }
                }
            }
        }
        if (v0_0 != null) {
            JF.L v5_2 = this.b;
            if (v5_2 != null) {
                JF.L v5_4 = ((androidx.fragment.app.FragmentActivity) v5_2.get());
                if (v5_4 != null) {
                    JF.L v5_5 = v5_4.getBaseContext();
                    if (v5_5 != null) {
                        if (!fQ.g.g(v5_5, v0_0.a, v0_0.b, v0_0.c)) {
                            if (p6 == null) {
                                return;
                            } else {
                                p6.h(JF.L.a);
                                return;
                            }
                        } else {
                            if (p6 == null) {
                                return;
                            } else {
                                p6.success();
                                return;
                            }
                        }
                    }
                }
            }
            if (p6 != null) {
                p6.h(ZF.m.c);
            }
        } else {
            if (p6 != null) {
                p6.h(ZF.m.c);
                return;
            }
        }
        return;
    }

    private final void r(org.json.JSONObject p10, MR.b p11)
    {
        JF.s v0_0 = 0;
        if (p10 != null) {
            com.visa.visapay.common.service.request.BaseParams v3_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p10);
            if (v3_0 != null) {
                JF.J v4_2;
                String v1_7 = p10.optJSONObject("baseParams");
                if (v1_7 != null) {
                    JF.s v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v1_7);
                    if (v2_1 != null) {
                        JF.J v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v1_7);
                        if (v4_1 != null) {
                            String v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v1_7);
                            if (v1_1 != null) {
                                v4_2 = new JF.J(v2_1, v4_1, v1_1);
                            } else {
                                v4_2 = 0;
                            }
                        }
                    }
                }
                if (v4_2 != null) {
                    JF.J v5_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("tokenReferenceId", p10);
                    if (v5_3 != null) {
                        String v6_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("clientAppId", p10);
                        if (v6_0 != null) {
                            String v7_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("vNotificationId", p10);
                            if (v7_0 != null) {
                                v0_0 = new JF.s(v3_0, v4_2, v5_3, v6_0, v7_0);
                            }
                        }
                    }
                }
            }
        }
        if (v0_0 != null) {
            JF.s v2_4 = v0_0.a;
            String v10_2 = v0_0.b.a();
            JF.J v4_3 = v0_0.c;
            JF.J v5_4 = v0_0.d;
            String v6_1 = v0_0.e;
            IF.e v8_1 = new IF.e(p11, this, 5);
            gQ.b v11_2 = fQ.g.b.a;
            if (v11_2 != null) {
                String v1_5 = v11_2.getCardService();
                if (v1_5 != null) {
                    try {
                        v1_5.initiateReperso(v2_4, v10_2.a(), v4_3, v5_4, v6_1, new iQ.f(v8_1, 2));
                        return;
                    } catch (JF.s v0_2) {
                        String v10_5 = v0_2.getMessage();
                        if (v10_5 == null) {
                            v10_5 = "";
                        }
                        v8_1.a(new gQ.b("", v10_5));
                        return;
                    }
                }
            }
            v8_1.a(gQ.b.c);
            return;
        } else {
            if (p11 != null) {
                p11.h(ZF.m.c);
            }
            return;
        }
    }

    private final void s(org.json.JSONObject p6, MR.b p7)
    {
        JF.v v0_0 = 0;
        if (p6 != null) {
            IF.e v1_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p6);
            if (v1_3 != null) {
                JF.J v4_2;
                String v6_4 = p6.optJSONObject("baseParams");
                if (v6_4 != null) {
                    String v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v6_4);
                    if (v2_1 != null) {
                        String v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v6_4);
                        if (v3_1 != null) {
                            String v6_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v6_4);
                            if (v6_1 != null) {
                                v4_2 = new JF.J(v2_1, v3_1, v6_1);
                            } else {
                                v4_2 = 0;
                            }
                        }
                    }
                }
                if (v4_2 != null) {
                    v0_0 = new JF.v(v1_3, v4_2);
                }
            }
        }
        if (v0_0 != null) {
            fQ.g.d.n(v0_0.a, v0_0.b.a(), new IF.e(p7, this, 6));
            return;
        } else {
            if (p7 != null) {
                p7.h(ZF.m.c);
            }
            return;
        }
    }

    private final void t(org.json.JSONObject p10, MR.b p11)
    {
        JF.y v0_0 = 0;
        if (p10 != null) {
            jQ.w v3_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p10);
            if (v3_0 != null) {
                JF.J v4_2;
                String v1_9 = p10.optJSONObject("baseParams");
                if (v1_9 != null) {
                    JF.y v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v1_9);
                    if (v2_1 != null) {
                        JF.J v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v1_9);
                        if (v4_1 != null) {
                            String v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v1_9);
                            if (v1_1 != null) {
                                v4_2 = new JF.J(v2_1, v4_1, v1_1);
                            } else {
                                v4_2 = 0;
                            }
                        }
                    }
                }
                if (v4_2 != null) {
                    JF.y v2_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("lcmAction", p10);
                    jQ.w.a.getClass();
                    JF.J v5_3 = s5.f.f(v2_3);
                    if (v5_3 != null) {
                        JF.y v2_5 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("lcmReason", p10);
                        jQ.x.a.getClass();
                        String v1_5 = jQ.x.d.iterator();
                        while (v1_5.hasNext()) {
                            jQ.x v6_1 = v1_5.next();
                            if (kotlin.text.y.equals(v2_5, ((jQ.x) v6_1).name(), 1)) {
                            }
                            jQ.x v6_2 = ((jQ.x) v6_1);
                            if (v6_2 != null) {
                                String v7_4 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("cardReferenceId", p10);
                                if (v7_4 != null) {
                                    v0_0 = new JF.y(v3_0, v4_2, v5_3, v6_2, v7_4, com.google.android.gms.internal.mlkit_vision_barcode.U6.b("tokenReferenceId", p10));
                                }
                            }
                        }
                        v6_1 = 0;
                    }
                }
            }
        }
        if (v0_0 != null) {
            fQ.g.h(v0_0.a, v0_0.b.a(), v0_0.c, v0_0.d, v0_0.e, v0_0.f, new IF.e(p11, this, 7));
            return;
        } else {
            if (p11 != null) {
                p11.h(ZF.m.c);
            }
            return;
        }
    }

    private final void u(org.json.JSONObject p8, MR.b p9)
    {
        JF.A v0_0 = 0;
        if (p8 != null) {
            String v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p8);
            if (v1_1 != null) {
                JF.J v5_2;
                String v2_5 = p8.optJSONObject("baseParams");
                if (v2_5 != null) {
                    oc.b v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v2_5);
                    if (v3_1 != null) {
                        String v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v2_5);
                        if (v4_1 != null) {
                            String v2_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v2_5);
                            if (v2_0 != null) {
                                v5_2 = new JF.J(v3_1, v4_1, v2_0);
                            } else {
                                v5_2 = 0;
                            }
                        }
                    }
                }
                if (v5_2 != null) {
                    String v2_2 = p8.optBoolean("isCvmVerified");
                    jQ.v v8_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("cvmMode", p8);
                    jQ.v.a.getClass();
                    jQ.v v8_2 = oc.b.f(v8_1);
                    if (v8_2 != null) {
                        v0_0 = new JF.A(v1_1, v5_2, v2_2, v8_2);
                    }
                }
            }
        }
        if (v0_0 != null) {
            this.c = p9;
            fQ.g.j(v0_0.a, v0_0.b.a(), v0_0.c, v0_0.d, new IF.f(this, p9, v0_0, 0), 0);
            return;
        } else {
            if (p9 != null) {
                p9.h(ZF.m.c);
            }
            return;
        }
    }

    private final void v(org.json.JSONObject p10, MR.b p11)
    {
        String v0_0 = 0;
        if (p10 != null) {
            com.visa.visapaysdk.service.EnrolmentService v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p10);
            if (v1_1 != null) {
                JF.J v5_2;
                String v2_6 = p10.optJSONObject("baseParams");
                if (v2_6 != null) {
                    String v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v2_6);
                    if (v3_1 != null) {
                        com.visa.visapay.common.service.request.BaseParams v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v2_6);
                        if (v4_1 != null) {
                            String v2_0 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v2_6);
                            if (v2_0 != null) {
                                v5_2 = new JF.J(v3_1, v4_1, v2_0);
                            } else {
                                v5_2 = 0;
                            }
                        }
                    }
                }
                if (v5_2 != null) {
                    String v2_2 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("cardReferenceId", p10);
                    if (v2_2 != null) {
                        String v10_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("presentationType", p10);
                        if (v10_1 != null) {
                            v0_0 = new JF.E(v1_1, v5_2, v2_2, v10_1);
                        }
                    }
                }
            }
        }
        if (v0_0 != null) {
            String v10_2 = this.b;
            if (v10_2 != null) {
                String v2_4 = ((androidx.fragment.app.FragmentActivity) v10_2.get());
                if (v2_4 != null) {
                    String v3_3 = v0_0.a;
                    String v10_5 = v0_0.b.a();
                    JF.J v5_3 = v0_0.c;
                    String v0_2 = v0_0.d;
                    jQ.u.a.getClass();
                    String v0_3 = o9.z.q(v0_2);
                    IF.e v8_1 = new IF.e(p11, this, 8);
                    gQ.b v11_1 = fQ.g.b;
                    com.visa.visapaysdk.service.EnrolmentService v1_4 = v11_1.a;
                    if (v1_4 != null) {
                        com.visa.visapaysdk.service.EnrolmentService v1_5 = v1_4.getEnrolmentService();
                        if (v1_5 != null) {
                            try {
                                v1_5.registerCard(v2_4, v3_3, v10_5.a(), v5_3, v0_3.e(), new iQ.g(v8_1, v11_1, v10_5, 2));
                                return;
                            } catch (String v0_5) {
                                String v10_7 = v0_5.getMessage();
                                if (v10_7 == null) {
                                    v10_7 = "";
                                }
                                v8_1.a(new gQ.b("", v10_7));
                                return;
                            }
                        }
                    }
                    v8_1.a(gQ.b.c);
                    return;
                }
            }
            if (p11 != null) {
                p11.h(ZF.m.c);
            }
        } else {
            if (p11 != null) {
                p11.h(ZF.m.c);
                return;
            }
        }
        return;
    }

    private final void w(org.json.JSONObject p6, MR.b p7)
    {
        JF.F v0_0 = 0;
        if (p6 != null) {
            IF.e v1_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p6);
            if (v1_3 != null) {
                JF.J v4_2;
                String v6_4 = p6.optJSONObject("baseParams");
                if (v6_4 != null) {
                    String v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletProviderId", v6_4);
                    if (v2_1 != null) {
                        String v3_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("walletCustomerId", v6_4);
                        if (v3_1 != null) {
                            String v6_1 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("customerCorrelationId", v6_4);
                            if (v6_1 != null) {
                                v4_2 = new JF.J(v2_1, v3_1, v6_1);
                            } else {
                                v4_2 = 0;
                            }
                        }
                    }
                }
                if (v4_2 != null) {
                    v0_0 = new JF.F(v1_3, v4_2);
                }
            }
        }
        if (v0_0 != null) {
            fQ.g.m(v0_0.a, v0_0.b.a(), new IF.e(p7, this, 9));
            return;
        } else {
            if (p7 != null) {
                p7.h(ZF.m.c);
            }
            return;
        }
    }

    private final void x(org.json.JSONObject p4, MR.b p5)
    {
        JF.I v0_0 = 0;
        if (p4 != null) {
            IF.e v1_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("requestId", p4);
            if (v1_3 != null) {
                ZF.m v4_3 = com.google.android.gms.internal.mlkit_vision_barcode.U6.b("encryptedSessionDetails", p4);
                if (v4_3 != null) {
                    v0_0 = new JF.I(v1_3, v4_3);
                }
            }
        }
        if (v0_0 != null) {
            fQ.g.n(v0_0.a, v0_0.b, new IF.e(p5, this, 10));
            return;
        } else {
            if (p5 != null) {
                p5.h(ZF.m.c);
            }
            return;
        }
    }

    private final void y(MR.b p1)
    {
        this.k();
        if (p1 != null) {
            p1.success();
        }
        return;
    }

    public void A()
    {
        this.a.shutdownNow();
        return;
    }

    public final void B(Long p9)
    {
        if (this.z(p9)) {
            vn.com.vng.zalopay.miniapp.api.v2.visa.c.E(this, 0, 0, jQ.E.a, 0, 0, 27, 0);
            vn.com.vng.zalopay.miniapp.api.v2.visa.c.h = 0;
            this.k();
            return;
        } else {
            return;
        }
    }

    public final void C(Long p1, boolean p2)
    {
        if (this.z(p1)) {
            vn.com.vng.zalopay.miniapp.api.v2.visa.c.h = p2;
            if (p2 == null) {
                vn.com.vng.zalopay.miniapp.api.v2.visa.a.a("web");
                return;
            } else {
                vn.com.vng.zalopay.miniapp.api.v2.visa.a.b("web", new vn.com.vng.zalopay.miniapp.api.v2.visa.b(this));
                return;
            }
        } else {
            return;
        }
    }

    public void a(String p2, org.json.JSONObject p3, MR.b p4)
    {
        if (p2) {
            switch (p2.hashCode()) {
                case -1833982721:
                    if (p2.equals("registerProcessCommandApdu")) {
                        this.u(p3, p4);
                    }
                    break;
                case -1723890858:
                    if (p2.equals("manageVISACard")) {
                        this.t(p3, p4);
                        return;
                    }
                case -1451991795:
                    if (p2.equals("initiateReperso")) {
                        this.r(p3, p4);
                        return;
                    }
                case -1385982280:
                    if (p2.equals("fetchVISACardArt")) {
                        this.l(p3, p4);
                        return;
                    }
                case -1074383862:
                    if (p2.equals("resetVISASDK")) {
                        this.w(p3, p4);
                        return;
                    }
                case -252974234:
                    if (p2.equals("generateVISACard")) {
                        this.m(p3, p4);
                        return;
                    }
                case 108846451:
                    if (p2.equals("setVISASession")) {
                        this.x(p3, p4);
                        return;
                    }
                case 763843683:
                    if (p2.equals("generateVISAToken")) {
                        this.n(p3, p4);
                        return;
                    }
                case 839901481:
                    if (p2.equals("initVISASDK")) {
                        this.p(p3, p4);
                        return;
                    }
                case 841343012:
                    if (p2.equals("initVISAService")) {
                        this.q(p3, p4);
                        return;
                    }
                case 1049927045:
                    if (p2.equals("getVISACardList")) {
                        this.o(p3, p4);
                        return;
                    }
                case 1124939352:
                    if (p2.equals("unregisterProcessCommandApdu")) {
                        this.y(p4);
                        return;
                    }
                case 1268510224:
                    if (p2.equals("initiateVISAHCE")) {
                        this.s(p3, p4);
                        return;
                    }
                case 2063791764:
                    if (p2.equals("registerVISACard")) {
                        this.v(p3, p4);
                        return;
                    }
                default:
            }
        }
        return;
    }

    public final void i(ref.WeakReference p1)
    {
        this.b = p1;
        return;
    }

    public void onCreate()
    {
        return;
    }

    public final boolean z(Long p5)
    {
        if ((p5 == 0) || (p5.longValue() != 7419021155426396)) {
            return 0;
        } else {
            return 1;
        }
    }
}

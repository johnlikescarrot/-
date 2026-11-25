package vn.com.vng.zalopay.alipay.service;
public final class MpmServiceProvider implements com.iap.android.mppclient.mpm.service.MPMService {
    public static final int $stable = 0;
    public static final String ALIPAY_MPM_HANDLE_BY_SDK = "HANDLE_BY_SDK";
    public static final vn.com.vng.zalopay.alipay.service.MpmServiceProvider$Companion Companion;
    private static com.iap.android.mppclient.basic.callback.Callback callback;
    public Fm.a cashierGatewayDelegate;
    private final android.content.Context context;
    private final ng.w coroutineScope$delegate;
    private final Pm.a decodeAlipayMppUseCase;

    static MpmServiceProvider()
    {
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion = new vn.com.vng.zalopay.alipay.service.MpmServiceProvider$Companion(0);
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.$stable = 8;
        return;
    }

    public MpmServiceProvider(android.content.Context p1, Pm.a p2)
    {
        this.context = p1;
        this.decodeAlipayMppUseCase = p2;
        this.coroutineScope$delegate = ng.x.b(new vA.b(11));
        return;
    }

    public static synthetic kotlin.Unit a(fu.j p0)
    {
        return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.showErrorDialog$lambda$7(p0);
    }

    public static final synthetic com.iap.android.mppclient.basic.callback.Callback access$getCallback$cp()
    {
        return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.callback;
    }

    public static final synthetic Pm.a access$getDecodeAlipayMppUseCase$p(vn.com.vng.zalopay.alipay.service.MpmServiceProvider p0)
    {
        return p0.decodeAlipayMppUseCase;
    }

    public static final synthetic void access$handleInitiatePay(vn.com.vng.zalopay.alipay.service.MpmServiceProvider p0, Hm.b p1)
    {
        p0.handleInitiatePay(p1);
        return;
    }

    public static final synthetic void access$showErrorDialog(vn.com.vng.zalopay.alipay.service.MpmServiceProvider p0, fu.j p1)
    {
        p0.showErrorDialog(p1);
        return;
    }

    public static synthetic kotlin.Unit b()
    {
        return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.showErrorDialog$lambda$6();
    }

    public static synthetic kotlin.Unit c(fu.j p0)
    {
        return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.showErrorDialog$lambda$8(p0);
    }

    private static final kotlinx.coroutines.CoroutineScope coroutineScope_delegate$lambda$0()
    {
        return gi.L.a(kotlinx.coroutines.Dispatchers.getIO().plus(gi.N.d()));
    }

    public static synthetic kotlin.Unit d(Fm.h p0)
    {
        return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.handleInitiatePay$lambda$5$lambda$4(p0);
    }

    public static synthetic kotlinx.coroutines.CoroutineScope e()
    {
        return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.coroutineScope_delegate$lambda$0();
    }

    private final kotlinx.coroutines.CoroutineScope getCoroutineScope()
    {
        return ((kotlinx.coroutines.CoroutineScope) this.coroutineScope$delegate.getValue());
    }

    private final void handleInitiatePay(Hm.b p15)
    {
        boolean v1_2;
        String v0_0 = 0;
        if (p15 == null) {
            v1_2 = 0;
        } else {
            boolean v1_1 = p15.b();
            if (!v1_1) {
            } else {
                v1_2 = v1_1.a();
            }
        }
        if (!kotlin.text.y.equals(v1_2, "HANDLE_BY_SDK", 1)) {
            if (p15 != null) {
                Fm.c v15_1 = p15.a();
                if (v15_1 != null) {
                    Fm.c v15_2 = q9.y6.h(v15_1);
                    if (v15_2 != null) {
                        String v0_4;
                        String v0_3 = v15_2.getQueryParameter("appid");
                        if (v0_3 == null) {
                            v0_4 = 0;
                        } else {
                            v0_4 = Long.parseLong(v0_3);
                        }
                        long v4 = v0_4;
                        Fm.c v15_4 = v15_2.getQueryParameter("zptranstoken");
                        if (v15_4 == null) {
                            v15_4 = "";
                        }
                        String v6 = v15_4;
                        com.google.android.gms.internal.firebase-perf.z v3_1 = new com.google.android.gms.internal.firebase-perf.z(this.getCashierGatewayDelegate());
                        kotlinx.coroutines.BuildersKt.launch$default(((li.e) v3_1.c), 0, 0, new Fm.b(v3_1, v4, v6, new qK.a(26), 0, 0), 3, 0);
                        v3_1.w(v4, v6, Fm.c.a);
                        return;
                    }
                }
            }
            vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendFailedCallback("1001", "deeplink is null or empty");
            return;
        } else {
            if (p15 != null) {
                Fm.c v15_10 = p15.b();
                if (v15_10 != null) {
                    v0_0 = new com.iap.android.mppclient.basic.model.DecodeServiceResult();
                    v0_0.sdkActionPayload = v15_10.b();
                }
            }
            if (v0_0 != null) {
                Fm.c v15_12 = v0_0.sdkActionPayload;
                if ((v15_12 != null) && (v15_12.length() != 0)) {
                    vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendSuccessCallback(v0_0);
                    return;
                }
            }
            vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendFailedCallback("1001", "actionParams is null");
            return;
        }
    }

    private static final kotlin.Unit handleInitiatePay$lambda$5$lambda$4(Fm.h p2)
    {
        java.util.Objects.toString(p2);
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p2, Fm.g.a)) {
            if (!kotlin.jvm.internal.Intrinsics.areEqual(p2, Fm.d.a)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(p2, Fm.e.a)) {
                    vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendFailedCallback("2001", "Payment is failed");
                }
            } else {
                vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendFailedCallback("1004", "Payment is canceled");
            }
        } else {
            vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendSuccessCallback(new com.iap.android.mppclient.basic.model.DecodeServiceResult());
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void showErrorDialog(fu.j p8)
    {
        if (p8 != null) {
            if (p8.a != -3012) {
                vn.com.vng.zalopay.alipay.presentation.dialog.a v0_1 = this.context.getString(2131952454);
                vn.com.vng.zalopay.alipay.service.b v5_1 = new vn.com.vng.zalopay.alipay.service.b(p8, 1);
                p8 = Em.c.a;
                android.app.Activity v8_1 = Em.c.b();
                if (v8_1 != null) {
                    android.content.Intent v6_2 = new android.content.Intent(v8_1, vn.com.vng.zalopay.alipay.presentation.dialog.AlipayErrorDialogActivity);
                    v6_2.addFlags(65536);
                    v6_2.putExtra("dialog_title", v0_1);
                    v6_2.putExtra("dialog_callback", new vn.com.vng.zalopay.alipay.presentation.dialog.a(v5_1));
                    v8_1.startActivity(v6_2);
                }
            } else {
                vn.com.vng.zalopay.alipay.presentation.dialog.a v0_5 = this.context.getString(2131952451);
                vn.com.vng.zalopay.alipay.service.b v5_4 = new vn.com.vng.zalopay.alipay.service.b(p8, 0);
                p8 = Em.c.a;
                android.app.Activity v8_2 = Em.c.b();
                if (v8_2 != null) {
                    android.content.Intent v6_5 = new android.content.Intent(v8_2, vn.com.vng.zalopay.alipay.presentation.dialog.AlipayErrorDialogActivity);
                    v6_5.addFlags(65536);
                    v6_5.putExtra("dialog_title", v0_5);
                    v6_5.putExtra("dialog_callback", new vn.com.vng.zalopay.alipay.presentation.dialog.a(v5_4));
                    v8_2.startActivity(v6_5);
                    return;
                }
            }
        } else {
            android.app.Activity v8_4 = this.context.getString(2131952454);
            vn.com.vng.zalopay.alipay.presentation.dialog.a v0_9 = new vA.b(12);
            vn.com.vng.zalopay.alipay.service.b v5 = Em.c.a;
            vn.com.vng.zalopay.alipay.service.b v5_6 = Em.c.b();
            if (v5_6 != null) {
                android.content.Intent v6_7 = new android.content.Intent(v5_6, vn.com.vng.zalopay.alipay.presentation.dialog.AlipayErrorDialogActivity);
                v6_7.addFlags(65536);
                v6_7.putExtra("dialog_title", v8_4);
                v6_7.putExtra("dialog_callback", new vn.com.vng.zalopay.alipay.presentation.dialog.a(v0_9));
                v5_6.startActivity(v6_7);
                return;
            }
        }
        return;
    }

    private static final kotlin.Unit showErrorDialog$lambda$6()
    {
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendFailedCallback("1003", "");
        return kotlin.Unit.INSTANCE;
    }

    private static final kotlin.Unit showErrorDialog$lambda$7(fu.j p0)
    {
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.showErrorDialog$sendError(p0);
        return kotlin.Unit.INSTANCE;
    }

    private static final kotlin.Unit showErrorDialog$lambda$8(fu.j p0)
    {
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.showErrorDialog$sendError(p0);
        return kotlin.Unit.INSTANCE;
    }

    private static final void showErrorDialog$sendError(fu.j p2)
    {
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.Companion.sendFailedCallback(String.valueOf(p2.a), p2.c);
        return;
    }

    private final void userInitiatePay(String p7)
    {
        kotlinx.coroutines.BuildersKt.launch$default(this.getCoroutineScope(), 0, 0, new vn.com.vng.zalopay.alipay.service.MpmServiceProvider$userInitiatePay$1(this, p7, 0), 3, 0);
        return;
    }

    public void decode(com.iap.android.mppclient.basic.model.DecodeServiceParams p5, com.iap.android.mppclient.basic.callback.Callback p6)
    {
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.callback = p6;
        String v6_1 = 0;
        if (p5 != null) {
            java.util.Map v0_3 = p5.codeValue;
            if (v0_3 != null) {
                String v1_1 = kotlin.collections.Z.createMapBuilder();
                String v2_1 = com.google.android.gms.internal.mlkit_vision_barcode.o5.a;
                if (v2_1 == null) {
                    v2_1 = "";
                }
                v1_1.put("originalCodeValue", v2_1);
                v1_1.put("newCodeValue", v0_3);
                java.util.Map v0_0 = com.google.android.gms.internal.mlkit_vision_barcode.o5.a;
                if ((v0_0 != null) && (!kotlin.text.StringsKt.isBlank(v0_0))) {
                    com.google.android.gms.internal.mlkit_vision_barcode.o5.a = 0;
                }
                UR.a.e("01.4604.024", kotlin.collections.Z.build(v1_1));
            }
        }
        if (p5 != null) {
            v6_1 = p5.codeValue;
        }
        this.userInitiatePay(v6_1);
        return;
    }

    public final Fm.a getCashierGatewayDelegate()
    {
        int v0_0 = this.cashierGatewayDelegate;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final android.content.Context getContext()
    {
        return this.context;
    }

    public final void setCashierGatewayDelegate(Fm.a p1)
    {
        this.cashierGatewayDelegate = p1;
        return;
    }
}

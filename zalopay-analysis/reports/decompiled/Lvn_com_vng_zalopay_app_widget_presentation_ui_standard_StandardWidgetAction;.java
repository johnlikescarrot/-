package vn.com.vng.zalopay.app_widget.presentation.ui.standard;
public final class StandardWidgetAction implements C2.a {
    public static final A2.c a;
    public static final A2.c b;

    static StandardWidgetAction()
    {
        vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardWidgetAction.a = new A2.c("standard_deeplink_key");
        vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardWidgetAction.b = new A2.c("standard_item_name_key");
        return;
    }

    public StandardWidgetAction()
    {
        return;
    }

    public final Object a(android.content.Context p6, A2.f p7, kotlin.coroutines.Continuation p8)
    {
        java.util.Map v7_1 = p7.a;
        String v8_2 = ((String) v7_1.get(vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardWidgetAction.a));
        if (v8_2 != null) {
            java.util.Map v7_2 = ((String) v7_1.get(vn.com.vng.zalopay.app_widget.presentation.ui.standard.StandardWidgetAction.b));
            if (v7_2 == null) {
                v7_2 = "click_widget";
            }
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v7_2, "transfer")) {
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v7_2, "receive")) {
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v7_2, "qrcode")) {
                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v7_2, "fin")) {
                            java.util.Map v7_4 = kotlin.collections.a0.emptyMap();
                            kotlin.collections.a0.plus(v7_4, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("os", "android")));
                            java.util.Objects.toString(v7_4);
                            UR.a.e("01.8999.010", v7_4);
                        } else {
                            java.util.Map v7_6 = kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("name", "fin"));
                            kotlin.collections.a0.plus(v7_6, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("os", "android")));
                            java.util.Objects.toString(v7_6);
                            UR.a.e("01.8999.014", v7_6);
                        }
                    } else {
                        java.util.Map v7_8 = kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("name", "qrcode"));
                        kotlin.collections.a0.plus(v7_8, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("os", "android")));
                        java.util.Objects.toString(v7_8);
                        UR.a.e("01.8999.013", v7_8);
                    }
                } else {
                    java.util.Map v7_10 = kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("name", "receive"));
                    kotlin.collections.a0.plus(v7_10, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("os", "android")));
                    java.util.Objects.toString(v7_10);
                    UR.a.e("01.8999.012", v7_10);
                }
            } else {
                java.util.Map v7_13 = kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("name", "transfer"));
                kotlin.collections.a0.plus(v7_13, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("os", "android")));
                java.util.Objects.toString(v7_13);
                UR.a.e("01.8999.011", v7_13);
            }
            try {
                q9.r6.b(p6, v8_2);
            } catch (Exception) {
            }
            return kotlin.Unit.INSTANCE;
        } else {
            return kotlin.Unit.INSTANCE;
        }
    }
}

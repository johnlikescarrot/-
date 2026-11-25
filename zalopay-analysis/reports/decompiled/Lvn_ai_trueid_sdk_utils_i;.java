package vn.ai.trueid.sdk.utils;
public final class i {

    public static void a(kotlin.jvm.functions.Function1 p13)
    {
        java.util.HashMap v0_1 = new java.util.ArrayList();
        lS.b v1_20 = Ok.q.a;
        if (v1_20 != null) {
            v0_1.add(v1_20);
        }
        lS.b v1_0;
        lS.b v1_24 = Ok.q.b;
        if (v1_24 == null) {
            v1_0 = fk.b.h;
        } else {
            v0_1.add(v1_24);
            v1_0 = Ok.q.b.status;
        }
        int v6_0 = 1;
        v0_1.add(new vn.ai.trueid.sdk.models.CheckingRecord("idVerification", "", vn.ai.trueid.sdk.models.DetectionType.idVerification, 1));
        Ok.q.c.setRawImage(vn.ai.trueid.sdk.models.RawImage.Companion.fromBitmaps$trueid_release(fk.b.j, fk.b.m, fk.b.n));
        java.util.HashMap v0_29 = Ok.q.c.getIdInfo();
        if (v0_29 != null) {
            int v2_6 = fk.b.g.ordinal();
            vn.ai.trueid.sdk.utils.g v3_3 = new vn.ai.trueid.sdk.utils.g(p13);
            int v7_1 = kotlin.TuplesKt.to("id_number", v0_29.getIdNumber());
            kotlin.TuplesKt.to("full_name", v0_29.getFullname());
            kotlin.TuplesKt.to("liveness_status", Boolean.valueOf(v1_0));
            kotlin.TuplesKt.to("liveness_strategy", String.valueOf(v2_6));
            kotlin.TuplesKt.to("pull_ekyc_result", Integer.valueOf(1));
            java.util.Map v13_4 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {v7_1, kotlin.TuplesKt.to("dob", v0_29.getDob())}));
            int v2_7 = fk.b.l;
            if ((v2_7 != 0) && (v2_7.length() != 0)) {
                v13_4 = kotlin.collections.a0.plus(v13_4, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("eid_qr_text", String.valueOf(fk.b.l))));
            }
            int v2_13 = fk.b.d;
            if (v2_13 != 0) {
                v13_4 = kotlin.collections.a0.plus(kotlin.collections.a0.plus(kotlin.collections.a0.plus(kotlin.collections.a0.plus(kotlin.collections.a0.plus(v13_4, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("id_number", v2_13.getIdNumber()))), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("dob", vn.ai.trueid.sdk.utils.j.c(v2_13.getDob())))), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("given_date", vn.ai.trueid.sdk.utils.j.c(v2_13.getDoi())))), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("address", v2_13.getAddress()))), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("full_name", v2_13.getFullname())));
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_29.getGender().toUpperCase(), "UNKNOWN")) {
                    if (!kotlin.jvm.internal.Intrinsics.areEqual(v2_13.getGender().toLowerCase(), "male")) {
                        if (!kotlin.jvm.internal.Intrinsics.areEqual(v2_13.getGender().toLowerCase(), "female")) {
                            v6_0 = 0;
                        } else {
                            v6_0 = 2;
                        }
                    }
                    v13_4 = kotlin.collections.a0.plus(v13_4, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("gender", Integer.valueOf(v6_0))));
                }
            }
            java.util.HashMap v0_14 = fk.b.l;
            if ((v0_14 != null) && (v0_14.length() != 0)) {
                v13_4 = kotlin.collections.a0.plus(v13_4, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("eid_qr_text", String.valueOf(fk.b.l))));
            }
            java.util.HashMap v0_21 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            String v4_3 = v0_21.getEkycConfig$trueid_release().getConfigEndPoint().getComplete();
            if ((v4_3.length() != 0) && (kotlin.text.y.replace$default(v4_3, v0_21.getEkycConfig$trueid_release().getDomain(), "", 0, 4, 0).length() != 0)) {
                java.util.HashMap v0_26 = new java.util.HashMap();
                v0_26.putAll(v13_4);
                v0_26.putAll(vn.ai.trueid.sdk.networks.u.b);
                vn.ai.trueid.sdk.networks.q.h(v4_3, vn.ai.trueid.sdk.networks.u.c, v0_26, new lS.b(18, v3_3));
            } else {
                v3_3.b(new org.json.JSONObject("{\"result\":1,\"kyc_result\":{}}"));
                return;
            }
        }
        return;
    }

    public static void b()
    {
        String v1_0 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        Ok.q.b = new vn.ai.trueid.sdk.models.CheckingRecord(v1_0.getResources().getString(2131955480), v1_0.getResources().getString(2131955478), vn.ai.trueid.sdk.models.DetectionType.livenessDetection, fk.b.h);
        vn.ai.trueid.sdk.utils.i.a(Ok.a.l);
        return;
    }

    public static void c()
    {
        if (fk.b.c != null) {
            org.json.JSONObject v0_2 = new java.util.HashMap();
            s5.f v1_10 = fk.b.l;
            if ((v1_10 != null) && ((v1_10.length() != 0) && (fk.b.l.length() > 20))) {
                new org.json.JSONObject().put("raw_content", String.valueOf(fk.b.l));
                v0_2.put("qrcode_data", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("raw_content", String.valueOf(fk.b.l))));
            }
            s5.f v1_8;
            s5.f v1_7 = fk.b.d;
            if (v1_7 == null) {
                v1_8 = "";
            } else {
                v1_8 = v1_7.getIdNumber();
                if (v1_8 == null) {
                }
            }
            v0_2.put("id_number", v1_8);
            v0_2.put("nfc_data", kotlin.collections.a0.toMap(fk.b.c));
            v0_2.put("device_id", vn.ai.trueid.sdk.utils.Helpers.Companion.getAdvId());
            s5.f v1_15 = new s5.f();
            String v2_10 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release().getConfigEndPoint().getNfcrar();
            if (v2_10.length() <= 0) {
                fk.b.q = 0;
                v1_15.j(new org.json.JSONObject());
                return;
            } else {
                vn.ai.trueid.sdk.networks.q.d(new gX.j(v2_10, v0_2, v1_15));
                return;
            }
        } else {
            vn.ai.trueid.sdk.utils.i.b();
            return;
        }
    }
}

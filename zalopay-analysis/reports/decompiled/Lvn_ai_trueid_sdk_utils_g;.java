package vn.ai.trueid.sdk.utils;
public final class g implements vn.ai.trueid.sdk.networks.r {
    public final synthetic kotlin.jvm.functions.Function1 a;

    public g(kotlin.jvm.functions.Function1 p1)
    {
        this.a = p1;
        return;
    }

    public final void a(vn.ai.trueid.sdk.models.TrueError p5)
    {
        Ok.q.c.setEkycResult(new java.util.HashMap());
        this.a.invoke(Ok.q.c);
        Z.g v0_2 = Ok.q.d;
        if ((v0_2 != null) && (p5.getCode() >= 400)) {
            fk.b.o = p5;
            int v5_3 = ((vn.ai.trueid.sdk.activity.TrueIDActivity) v0_2.b).k;
            if (v5_3 == 0) {
                v5_3 = 0;
            }
            v5_3.k0(0);
        }
        int v5_4 = m9.F2.a;
        if (v5_4 != 0) {
            v5_4.dismiss();
        }
        m9.F2.a = 0;
        return;
    }

    public final void b(org.json.JSONObject p25)
    {
        kotlin.jvm.functions.Function1 v3 = this.a;
        Ok.q.c.setCode(p25.getInt("result"));
        if (Ok.q.c.getCode() == 1) {
            vn.ai.trueid.sdk.models.Decision v15_4 = vn.ai.trueid.sdk.models.TrueError.Companion;
            Ok.q.c.setErrorMessage(v15_4.getSuccess().getMessage());
            fk.b.o = v15_4.getSuccess();
        }
        if (!p25.getJSONObject("kyc_result").keys().hasNext()) {
            Ok.q.c.setEkycResult(0);
        } else {
            Ok.q.c.setEkycResult(q9.A4.a(p25));
        }
        if (p25.getJSONObject("kyc_result").has("front")) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.parseAndSaveNfcInfo(p25.getJSONObject("kyc_result").getJSONObject("front"));
        }
        vn.ai.trueid.sdk.models.VerificationResult v13_19 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        Ok.q.c.setClientID(v13_19.getEkycConfig$trueid_release().getAppId());
        Ok.q.c.setRequestId(vn.ai.trueid.sdk.networks.q.f());
        Ok.q.c.setDecision(0);
        if (v13_19.isReturnNFC$trueid_release()) {
            Ok.q.c.setNfcData(vn.ai.trueid.sdk.utils.j.g(fk.b.c));
        }
        Ok.q.c.setNfcInfo(fk.b.q);
        Ok.q.c.setQrData(fk.b.l);
        Ok.q.c.setFaceMatching(0);
        if (p25.getJSONObject("kyc_result").has("decision")) {
            android.app.Dialog v0_37 = p25.getJSONObject("kyc_result").getJSONObject("decision");
            Ok.q.c.setDecision(new vn.ai.trueid.sdk.models.Decision(v0_37.getInt("code"), v0_37.getString("decision")));
        }
        if (p25.getJSONObject("kyc_result").has("compare")) {
            try {
                android.app.Dialog v0_42 = p25.getJSONObject("kyc_result").getJSONObject("compare");
                Ok.q.c.setFaceMatching(new vn.ai.trueid.sdk.models.FaceMatching(v0_42.optBoolean("matched", 0), v0_42.optDouble("similarity", 0), v0_42.optDouble("confidence_level", 0)));
            } catch (Exception) {
                Ok.q.c.setFaceMatching(0);
            }
        }
        if (p25.getJSONObject("kyc_result").has("complete")) {
            try {
                Ok.q.c.setLivenessCheck(p25.getJSONObject("kyc_result").getJSONObject("complete").getBoolean("liveness_status"));
            } catch (Exception) {
                Ok.q.c.setLivenessCheck(fk.b.h);
            }
        }
        if (fk.b.d != null) {
            Ok.q.c.setIdInfo(fk.b.d);
        }
        Ok.q.c.setRecords(fk.b.p);
        android.app.Dialog v0_52 = Ok.q.d;
        if (v0_52 != null) {
            v0_52.n(Ok.q.c);
        }
        v3.invoke(Ok.q.c);
        android.app.Dialog v0_54 = m9.F2.a;
        if (v0_54 == null) {
            m9.F2.a = 0;
            return;
        } else {
            v0_54.dismiss();
        }
    }
}

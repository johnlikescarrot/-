package vn.ai.trueid.sdk.networks;
public final enum class F extends java.lang.Enum {
    public static java.util.Map b;
    public static Integer c;
    public static java.util.List d;
    public static final synthetic vn.ai.trueid.sdk.networks.F[] e;
    public final String a;

    static F()
    {
        java.util.List v0_1 = new vn.ai.trueid.sdk.networks.F("INPUT_VALIDATION", 0, "inputValidation");
        new vn.ai.trueid.sdk.networks.F("FACE_COUNT", 1, "faceCount");
        vn.ai.trueid.sdk.networks.F v2_1 = new vn.ai.trueid.sdk.networks.F("OCCLUDED", 2, "occluded");
        new vn.ai.trueid.sdk.networks.F("OPENED_EYES", 3, "openedEyes");
        vn.ai.trueid.sdk.networks.F v4_3 = new vn.ai.trueid.sdk.networks.F("BLURRED", 4, "blurred");
        new vn.ai.trueid.sdk.networks.F("LIVENESS", 5, "liveness");
        new vn.ai.trueid.sdk.networks.F("FACE_MATCH", 6, "faceMatch");
        new vn.ai.trueid.sdk.networks.F("DEVICE_RISK", 7, "deviceRisk");
        vn.ai.trueid.sdk.networks.F v8_4 = new vn.ai.trueid.sdk.networks.F("TRANSACTION_STATUS", 8, "transactionStatus");
        vn.ai.trueid.sdk.networks.F.e = new vn.ai.trueid.sdk.networks.F[] {v0_1, v8_4});
        vn.ai.trueid.sdk.networks.F.b = new java.util.LinkedHashMap();
        vn.ai.trueid.sdk.networks.F v1 = v4_3;
        vn.ai.trueid.sdk.networks.F v3 = v2_1;
        vn.ai.trueid.sdk.networks.F.d = kotlin.collections.E.listOf(new vn.ai.trueid.sdk.networks.F[] {v0_1, v8_4}));
        return;
    }

    public F(String p1, int p2, String p3)
    {
        super(p1, p2);
        super.a = p3;
        return;
    }

    public static vn.ai.trueid.sdk.networks.F valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.networks.F) Enum.valueOf(vn.ai.trueid.sdk.networks.F, p1));
    }

    public static vn.ai.trueid.sdk.networks.F[] values()
    {
        return ((vn.ai.trueid.sdk.networks.F[]) vn.ai.trueid.sdk.networks.F.e.clone());
    }

    public final String e()
    {
        switch (this.ordinal()) {
            case 0:
            case 5:
            case 7:
            case 8:
                return vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465);
            case 1:
                return vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465);
            case 2:
                return vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955500);
            case 3:
                return vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465);
            case 4:
                return vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955499);
            case 6:
                return vn.ai.trueid.sdk.utils.Helpers.Companion.getString(2131955465);
            default:
                throw new kotlin.NoWhenBranchMatchedException();
        }
    }
}

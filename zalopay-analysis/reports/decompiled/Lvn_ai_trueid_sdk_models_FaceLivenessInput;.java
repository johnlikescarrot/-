package vn.ai.trueid.sdk.models;
public final class FaceLivenessInput {
    private final org.json.JSONObject deviceInfo;
    private final String transID;

    public FaceLivenessInput()
    {
        this(0, 0, 3, 0);
        return;
    }

    public FaceLivenessInput(String p1, org.json.JSONObject p2)
    {
        this.transID = p1;
        this.deviceInfo = p2;
        return;
    }

    public synthetic FaceLivenessInput(String p1, org.json.JSONObject p2, int p3, kotlin.jvm.internal.DefaultConstructorMarker p4)
    {
        if ((p3 & 1) != 0) {
            p1 = "";
        }
        if ((p3 & 2) != 0) {
            p2 = 0;
        }
        this(p1, p2);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceLivenessInput copy$default(vn.ai.trueid.sdk.models.FaceLivenessInput p0, String p1, org.json.JSONObject p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.transID;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.deviceInfo;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.transID;
    }

    public final org.json.JSONObject component2()
    {
        return this.deviceInfo;
    }

    public final vn.ai.trueid.sdk.models.FaceLivenessInput copy(String p2, org.json.JSONObject p3)
    {
        return new vn.ai.trueid.sdk.models.FaceLivenessInput(p2, p3);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceLivenessInput)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.transID, ((vn.ai.trueid.sdk.models.FaceLivenessInput) p5).transID)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.deviceInfo, ((vn.ai.trueid.sdk.models.FaceLivenessInput) p5).deviceInfo)) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final org.json.JSONObject getDeviceInfo()
    {
        return this.deviceInfo;
    }

    public final String getTransID()
    {
        return this.transID;
    }

    public int hashCode()
    {
        int v1_1;
        int v0_2 = (this.transID.hashCode() * 31);
        int v1_0 = this.deviceInfo;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        return (v0_2 + v1_1);
    }

    public String toString()
    {
        String v0_0 = this.transID;
        org.json.JSONObject v1 = this.deviceInfo;
        StringBuilder v2_1 = new StringBuilder("FaceLivenessInput(transID=");
        v2_1.append(v0_0);
        v2_1.append(", deviceInfo=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }
}

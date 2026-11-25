package vn.ai.trueid.sdk.models;
public final class FaceAuthenInput {
    private final org.json.JSONObject deviceInfo;
    private final String personID;
    private final String transID;

    public FaceAuthenInput()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public FaceAuthenInput(String p1, String p2, org.json.JSONObject p3)
    {
        this.transID = p1;
        this.personID = p2;
        this.deviceInfo = p3;
        return;
    }

    public synthetic FaceAuthenInput(String p2, String p3, org.json.JSONObject p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 1) != 0) {
            p2 = "";
        }
        if ((p5 & 2) != 0) {
            p3 = "";
        }
        if ((p5 & 4) != 0) {
            p4 = 0;
        }
        this(p2, p3, p4);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceAuthenInput copy$default(vn.ai.trueid.sdk.models.FaceAuthenInput p0, String p1, String p2, org.json.JSONObject p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.transID;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.personID;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.deviceInfo;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.transID;
    }

    public final String component2()
    {
        return this.personID;
    }

    public final org.json.JSONObject component3()
    {
        return this.deviceInfo;
    }

    public final vn.ai.trueid.sdk.models.FaceAuthenInput copy(String p2, String p3, org.json.JSONObject p4)
    {
        return new vn.ai.trueid.sdk.models.FaceAuthenInput(p2, p3, p4);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceAuthenInput)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.transID, ((vn.ai.trueid.sdk.models.FaceAuthenInput) p5).transID)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.personID, ((vn.ai.trueid.sdk.models.FaceAuthenInput) p5).personID)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.deviceInfo, ((vn.ai.trueid.sdk.models.FaceAuthenInput) p5).deviceInfo)) {
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

    public final String getPersonID()
    {
        return this.personID;
    }

    public final String getTransID()
    {
        return this.transID;
    }

    public int hashCode()
    {
        int v1_0;
        int v0_4 = e5.i.c((this.transID.hashCode() * 31), 31, this.personID);
        int v1_2 = this.deviceInfo;
        if (v1_2 != 0) {
            v1_0 = v1_2.hashCode();
        } else {
            v1_0 = 0;
        }
        return (v0_4 + v1_0);
    }

    public String toString()
    {
        org.json.JSONObject v2 = this.deviceInfo;
        String v0_2 = androidx.camera.camera2.internal.G.i("FaceAuthenInput(transID=", this.transID, ", personID=", this.personID, ", deviceInfo=");
        v0_2.append(v2);
        v0_2.append(")");
        return v0_2.toString();
    }
}

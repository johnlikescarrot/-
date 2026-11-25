package vn.ai.trueid.sdk.models;
public final class FaceEnrollInput {
    private org.json.JSONObject deviceInfo;
    private String dob;
    private String doe;
    private String eKYCRequestId;
    private String idNumber;
    private String personID;
    private String transID;

    public FaceEnrollInput()
    {
        this(0, 0, 0, 0, 0, 0, 0, 127, 0);
        return;
    }

    public FaceEnrollInput(String p1, String p2, String p3, String p4, String p5, String p6, org.json.JSONObject p7)
    {
        this.personID = p1;
        this.transID = p2;
        this.idNumber = p3;
        this.eKYCRequestId = p4;
        this.dob = p5;
        this.doe = p6;
        this.deviceInfo = p7;
        return;
    }

    public synthetic FaceEnrollInput(String p2, String p3, String p4, String p5, String p6, String p7, org.json.JSONObject p8, int p9, kotlin.jvm.internal.DefaultConstructorMarker p10)
    {
        if ((p9 & 1) != 0) {
            p2 = "";
        }
        if ((p9 & 2) != 0) {
            p3 = "";
        }
        if ((p9 & 4) != 0) {
            p4 = "";
        }
        if ((p9 & 8) != 0) {
            p5 = "";
        }
        if ((p9 & 16) != 0) {
            p6 = "01012000";
        }
        if ((p9 & 32) != 0) {
            p7 = "31122000";
        }
        if ((p9 & 64) != 0) {
            p8 = 0;
        }
        this(p2, p3, p4, p5, p6, p7, p8);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceEnrollInput copy$default(vn.ai.trueid.sdk.models.FaceEnrollInput p0, String p1, String p2, String p3, String p4, String p5, String p6, org.json.JSONObject p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.personID;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.transID;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.idNumber;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.eKYCRequestId;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.dob;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.doe;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.deviceInfo;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.personID;
    }

    public final String component2()
    {
        return this.transID;
    }

    public final String component3()
    {
        return this.idNumber;
    }

    public final String component4()
    {
        return this.eKYCRequestId;
    }

    public final String component5()
    {
        return this.dob;
    }

    public final String component6()
    {
        return this.doe;
    }

    public final org.json.JSONObject component7()
    {
        return this.deviceInfo;
    }

    public final vn.ai.trueid.sdk.models.FaceEnrollInput copy(String p9, String p10, String p11, String p12, String p13, String p14, org.json.JSONObject p15)
    {
        return new vn.ai.trueid.sdk.models.FaceEnrollInput(p9, p10, p11, p12, p13, p14, p15);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceEnrollInput)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.personID, ((vn.ai.trueid.sdk.models.FaceEnrollInput) p5).personID)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.transID, ((vn.ai.trueid.sdk.models.FaceEnrollInput) p5).transID)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.idNumber, ((vn.ai.trueid.sdk.models.FaceEnrollInput) p5).idNumber)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.eKYCRequestId, ((vn.ai.trueid.sdk.models.FaceEnrollInput) p5).eKYCRequestId)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.dob, ((vn.ai.trueid.sdk.models.FaceEnrollInput) p5).dob)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.doe, ((vn.ai.trueid.sdk.models.FaceEnrollInput) p5).doe)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.deviceInfo, ((vn.ai.trueid.sdk.models.FaceEnrollInput) p5).deviceInfo)) {
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

    public final String getDob()
    {
        return this.dob;
    }

    public final String getDoe()
    {
        return this.doe;
    }

    public final String getEKYCRequestId()
    {
        return this.eKYCRequestId;
    }

    public final String getIdNumber()
    {
        return this.idNumber;
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
        int v1_1;
        int v0_4 = e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.personID.hashCode() * 31), 31, this.transID), 31, this.idNumber), 31, this.eKYCRequestId), 31, this.dob), 31, this.doe);
        int v1_0 = this.deviceInfo;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        return (v0_4 + v1_1);
    }

    public final void setDeviceInfo(org.json.JSONObject p1)
    {
        this.deviceInfo = p1;
        return;
    }

    public final void setDob(String p1)
    {
        this.dob = p1;
        return;
    }

    public final void setDoe(String p1)
    {
        this.doe = p1;
        return;
    }

    public final void setEKYCRequestId(String p1)
    {
        this.eKYCRequestId = p1;
        return;
    }

    public final void setIdNumber(String p1)
    {
        this.idNumber = p1;
        return;
    }

    public final void setPersonID(String p1)
    {
        this.personID = p1;
        return;
    }

    public final void setTransID(String p1)
    {
        this.transID = p1;
        return;
    }

    public String toString()
    {
        String v0_0 = this.personID;
        String v1 = this.idNumber;
        String v2 = this.transID;
        String v3 = this.eKYCRequestId;
        StringBuilder v4_1 = new StringBuilder();
        v4_1.append(v0_0);
        v4_1.append("|");
        v4_1.append(v1);
        v4_1.append("|");
        v4_1.append(v2);
        return A1.a.l(v4_1, "|", v3);
    }
}

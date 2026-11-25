package vn.ai.trueid.sdk.models;
public final class FaceEnrollResult {
    private final int code;
    private final String enrollResult;
    private final String errorMessage;
    private final String nfcData;
    private String qrData;
    private final java.util.List rawImage;
    private java.util.List records;

    public FaceEnrollResult()
    {
        this(0, 0, 0, 0, 0, 0, 0, 127, 0);
        return;
    }

    public FaceEnrollResult(int p1, String p2, String p3, java.util.List p4, String p5, String p6, java.util.List p7)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.enrollResult = p3;
        this.rawImage = p4;
        this.nfcData = p5;
        this.qrData = p6;
        this.records = p7;
        return;
    }

    public synthetic FaceEnrollResult(int p2, String p3, String p4, java.util.List p5, String p6, String p7, java.util.List p8, int p9, kotlin.jvm.internal.DefaultConstructorMarker p10)
    {
        if ((p9 & 1) != 0) {
            p2 = vn.ai.trueid.sdk.models.TrueError.Companion.getSuccess().getCode();
        }
        if ((p9 & 2) != 0) {
            p3 = "";
        }
        if ((p9 & 4) != 0) {
            p4 = "";
        }
        if ((p9 & 8) != 0) {
            p5 = kotlin.collections.E.emptyList();
        }
        if ((p9 & 16) != 0) {
            p6 = "";
        }
        if ((p9 & 32) != 0) {
            p7 = "";
        }
        if ((p9 & 64) != 0) {
            p8 = kotlin.collections.E.emptyList();
        }
        this(p2, p3, p4, p5, p6, p7, p8);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceEnrollResult copy$default(vn.ai.trueid.sdk.models.FaceEnrollResult p0, int p1, String p2, String p3, java.util.List p4, String p5, String p6, java.util.List p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.errorMessage;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.enrollResult;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.rawImage;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.nfcData;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.qrData;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.records;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final int component1()
    {
        return this.code;
    }

    public final String component2()
    {
        return this.errorMessage;
    }

    public final String component3()
    {
        return this.enrollResult;
    }

    public final java.util.List component4()
    {
        return this.rawImage;
    }

    public final String component5()
    {
        return this.nfcData;
    }

    public final String component6()
    {
        return this.qrData;
    }

    public final java.util.List component7()
    {
        return this.records;
    }

    public final vn.ai.trueid.sdk.models.FaceEnrollResult copy(int p9, String p10, String p11, java.util.List p12, String p13, String p14, java.util.List p15)
    {
        return new vn.ai.trueid.sdk.models.FaceEnrollResult(p9, p10, p11, p12, p13, p14, p15);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceEnrollResult)) {
                if (this.code == ((vn.ai.trueid.sdk.models.FaceEnrollResult) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorMessage, ((vn.ai.trueid.sdk.models.FaceEnrollResult) p5).errorMessage)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.enrollResult, ((vn.ai.trueid.sdk.models.FaceEnrollResult) p5).enrollResult)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.rawImage, ((vn.ai.trueid.sdk.models.FaceEnrollResult) p5).rawImage)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.nfcData, ((vn.ai.trueid.sdk.models.FaceEnrollResult) p5).nfcData)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.qrData, ((vn.ai.trueid.sdk.models.FaceEnrollResult) p5).qrData)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.records, ((vn.ai.trueid.sdk.models.FaceEnrollResult) p5).records)) {
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

    public final int getCode()
    {
        return this.code;
    }

    public final String getEnrollResult()
    {
        return this.enrollResult;
    }

    public final String getErrorMessage()
    {
        return this.errorMessage;
    }

    public final String getNfcData()
    {
        return this.nfcData;
    }

    public final String getQrData()
    {
        return this.qrData;
    }

    public final java.util.List getRawImage()
    {
        return this.rawImage;
    }

    public final java.util.List getRecords()
    {
        return this.records;
    }

    public int hashCode()
    {
        return (this.records.hashCode() + e5.i.c(e5.i.c(Kb.e.h(e5.i.c(e5.i.c((Integer.hashCode(this.code) * 31), 31, this.errorMessage), 31, this.enrollResult), 31, this.rawImage), 31, this.nfcData), 31, this.qrData));
    }

    public final void setQrData(String p1)
    {
        this.qrData = p1;
        return;
    }

    public final void setRecords(java.util.List p1)
    {
        this.records = p1;
        return;
    }

    public final org.json.JSONObject toJson()
    {
        return new org.json.JSONObject(this.toMap());
    }

    public final java.util.Map toMap()
    {
        kotlin.Pair v2 = kotlin.TuplesKt.to("code", Integer.valueOf(this.code));
        kotlin.TuplesKt.to("errorMessage", this.errorMessage);
        kotlin.TuplesKt.to("enrollResult", this.enrollResult);
        kotlin.TuplesKt.to("rawImage", this.rawImage);
        kotlin.TuplesKt.to("nfcData", this.nfcData);
        kotlin.TuplesKt.to("qrData", this.qrData);
        java.util.Map v0_7 = ((Iterable) this.records);
        java.util.ArrayList v1_6 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v0_7, 10));
        java.util.Map v0_8 = v0_7.iterator();
        while (v0_8.hasNext()) {
            v1_6.add(((vn.ai.trueid.sdk.models.Record) v0_8.next()).toMap());
        }
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v2, kotlin.TuplesKt.to("records", v1_6)}));
    }

    public String toString()
    {
        String v2_1 = this.enrollResult;
        java.util.List v3 = this.rawImage;
        String v4 = this.nfcData;
        String v5 = this.qrData;
        java.util.List v6 = this.records;
        String v0_1 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(this.code, "FaceEnrollResult(code=", ", errorMessage=", this.errorMessage, ", enrollResult=");
        A1.a.w(v0_1, v2_1, ", rawImage=", v3, ", nfcData=");
        q9.H.p(v0_1, v4, ", qrData=", v5, ", records=");
        return androidx.camera.camera2.internal.G.h(v0_1, v6, ")");
    }
}

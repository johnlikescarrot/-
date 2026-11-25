package vn.ai.trueid.sdk.models;
public final class FaceAuthenResult {
    private final String authenResult;
    private final int code;
    private final String errorMessage;
    private final java.util.List rawImage;
    private java.util.List records;

    public FaceAuthenResult()
    {
        this(0, 0, 0, 0, 0, 31, 0);
        return;
    }

    public FaceAuthenResult(int p1, String p2, String p3, java.util.List p4, java.util.List p5)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.authenResult = p3;
        this.rawImage = p4;
        this.records = p5;
        return;
    }

    public synthetic FaceAuthenResult(int p2, String p3, String p4, java.util.List p5, java.util.List p6, int p7, kotlin.jvm.internal.DefaultConstructorMarker p8)
    {
        if ((p7 & 1) != 0) {
            p2 = 0;
        }
        if ((p7 & 2) != 0) {
            p3 = "";
        }
        if ((p7 & 4) != 0) {
            p4 = "";
        }
        if ((p7 & 8) != 0) {
            p5 = kotlin.collections.E.emptyList();
        }
        if ((p7 & 16) != 0) {
            p6 = kotlin.collections.E.emptyList();
        }
        this(p2, p3, p4, p5, p6);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceAuthenResult copy$default(vn.ai.trueid.sdk.models.FaceAuthenResult p0, int p1, String p2, String p3, java.util.List p4, java.util.List p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.errorMessage;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.authenResult;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.rawImage;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.records;
        }
        return p0.copy(p1, p2, p3, p4, p5);
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
        return this.authenResult;
    }

    public final java.util.List component4()
    {
        return this.rawImage;
    }

    public final java.util.List component5()
    {
        return this.records;
    }

    public final vn.ai.trueid.sdk.models.FaceAuthenResult copy(int p7, String p8, String p9, java.util.List p10, java.util.List p11)
    {
        return new vn.ai.trueid.sdk.models.FaceAuthenResult(p7, p8, p9, p10, p11);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceAuthenResult)) {
                if (this.code == ((vn.ai.trueid.sdk.models.FaceAuthenResult) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorMessage, ((vn.ai.trueid.sdk.models.FaceAuthenResult) p5).errorMessage)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.authenResult, ((vn.ai.trueid.sdk.models.FaceAuthenResult) p5).authenResult)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.rawImage, ((vn.ai.trueid.sdk.models.FaceAuthenResult) p5).rawImage)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.records, ((vn.ai.trueid.sdk.models.FaceAuthenResult) p5).records)) {
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
            return 1;
        }
    }

    public final String getAuthenResult()
    {
        return this.authenResult;
    }

    public final int getCode()
    {
        return this.code;
    }

    public final String getErrorMessage()
    {
        return this.errorMessage;
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
        return (this.records.hashCode() + Kb.e.h(e5.i.c(e5.i.c((Integer.hashCode(this.code) * 31), 31, this.errorMessage), 31, this.authenResult), 31, this.rawImage));
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
        java.util.Map v0_4 = kotlin.TuplesKt.to("code", Integer.valueOf(this.code));
        kotlin.Pair v1_2 = kotlin.TuplesKt.to("errorMessage", this.errorMessage);
        kotlin.Pair v2_1 = kotlin.TuplesKt.to("authenResult", this.authenResult);
        kotlin.Pair v3_2 = kotlin.TuplesKt.to("rawImage", this.rawImage);
        kotlin.Pair v4_2 = ((Iterable) this.records);
        java.util.ArrayList v5_1 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v4_2, 10));
        kotlin.Pair v4_3 = v4_2.iterator();
        while (v4_3.hasNext()) {
            v5_1.add(((vn.ai.trueid.sdk.models.Record) v4_3.next()).toMap());
        }
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v0_4, v1_2, v2_1, v3_2, kotlin.TuplesKt.to("records", v5_1)}));
    }

    public String toString()
    {
        String v2 = this.authenResult;
        java.util.List v3 = this.rawImage;
        java.util.List v4 = this.records;
        String v0_2 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(this.code, "FaceAuthenResult(code=", ", errorMessage=", this.errorMessage, ", authenResult=");
        A1.a.w(v0_2, v2, ", rawImage=", v3, ", records=");
        return androidx.camera.camera2.internal.G.h(v0_2, v4, ")");
    }
}

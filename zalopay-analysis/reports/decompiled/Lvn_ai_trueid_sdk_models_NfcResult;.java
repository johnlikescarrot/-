package vn.ai.trueid.sdk.models;
public final class NfcResult {
    private final int code;
    private final String errorMessage;
    private final String nfcData;
    private final vn.ai.trueid.sdk.models.NfcInfo nfcInfo;
    private final String nfcResult;
    private final String qrData;
    private java.util.List records;

    public NfcResult()
    {
        this(0, 0, 0, 0, 0, 0, 0, 127, 0);
        return;
    }

    public NfcResult(int p1, String p2, vn.ai.trueid.sdk.models.NfcInfo p3, String p4, String p5, String p6, java.util.List p7)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.nfcInfo = p3;
        this.nfcResult = p4;
        this.nfcData = p5;
        this.qrData = p6;
        this.records = p7;
        return;
    }

    public NfcResult(int p3, String p4, vn.ai.trueid.sdk.models.NfcInfo p5, String p6, String p7, String p8, java.util.List p9, int p10, kotlin.jvm.internal.DefaultConstructorMarker p11)
    {
        if ((p10 & 1) != 0) {
            vn.ai.trueid.sdk.models.TrueError v3_1 = fk.b.o;
            if (v3_1 == null) {
                v3_1 = vn.ai.trueid.sdk.models.TrueError.Companion.getSuccess();
            }
            p3 = v3_1.getCode();
        }
        if ((p10 & 2) != 0) {
            vn.ai.trueid.sdk.models.NfcResult v4_1 = fk.b.o;
            if (v4_1 != null) {
                p4 = v4_1.getMessage();
                if (p4 != null) {
                    int v1_0 = 0;
                    if ((p10 & 4) != 0) {
                        p5 = 0;
                    }
                    if ((p10 & 8) != 0) {
                        p6 = "";
                    }
                    if ((p10 & 16) != 0) {
                        int v7_1 = fk.b.c;
                        if (v7_1 != 0) {
                            try {
                                v1_0 = new org.json.JSONObject(v7_1).toString();
                            } catch (Exception) {
                                v1_0 = "{}";
                            }
                        }
                        if (v1_0 != 0) {
                            p7 = v1_0;
                        } else {
                            p7 = "{}";
                        }
                    }
                    if ((p10 & 32) != 0) {
                        p8 = fk.b.l;
                        if (p8 == null) {
                            p8 = "";
                        }
                    }
                    if ((p10 & 64) != 0) {
                        p9 = kotlin.collections.E.emptyList();
                    }
                    this(p3, p4, p5, p6, p7, p8, p9);
                    return;
                }
            }
            p4 = "";
        }
    }

    public final int getCode()
    {
        return this.code;
    }

    public final String getErrorMessage()
    {
        return this.errorMessage;
    }

    public final String getNfcData()
    {
        return this.nfcData;
    }

    public final vn.ai.trueid.sdk.models.NfcInfo getNfcInfo()
    {
        return this.nfcInfo;
    }

    public final String getNfcResult()
    {
        return this.nfcResult;
    }

    public final String getQrData()
    {
        return this.qrData;
    }

    public final java.util.List getRecords()
    {
        return this.records;
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
        java.util.Map v0_2;
        kotlin.Pair v2 = kotlin.TuplesKt.to("code", Integer.valueOf(this.code));
        kotlin.TuplesKt.to("errorMessage", this.errorMessage);
        java.util.Map v0_1 = this.nfcInfo;
        if (v0_1 == null) {
            v0_2 = 0;
        } else {
            v0_2 = v0_1.toMap();
        }
        kotlin.TuplesKt.to("nfcInfo", v0_2);
        kotlin.TuplesKt.to("nfcData", this.nfcData);
        kotlin.TuplesKt.to("qrData", this.qrData);
        kotlin.TuplesKt.to("nfcResult", this.nfcResult);
        java.util.Map v0_8 = ((Iterable) this.records);
        java.util.ArrayList v1_6 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v0_8, 10));
        java.util.Map v0_9 = v0_8.iterator();
        while (v0_9.hasNext()) {
            v1_6.add(((vn.ai.trueid.sdk.models.Record) v0_9.next()).toMap());
        }
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v2, kotlin.TuplesKt.to("records", v1_6)}));
    }
}

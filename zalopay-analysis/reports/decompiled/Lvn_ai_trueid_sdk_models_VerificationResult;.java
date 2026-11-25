package vn.ai.trueid.sdk.models;
public final class VerificationResult {
    private String clientID;
    private int code;
    private vn.ai.trueid.sdk.models.Decision decision;
    private java.util.Map ekycResult;
    private String errorMessage;
    private vn.ai.trueid.sdk.models.FaceMatching faceMatching;
    private vn.ai.trueid.sdk.models.Person idInfo;
    private boolean livenessCheck;
    private String nfcData;
    private vn.ai.trueid.sdk.models.NfcInfo nfcInfo;
    private String qrData;
    private vn.ai.trueid.sdk.models.RawImage rawImage;
    private java.util.List records;
    private String requestId;

    public VerificationResult(int p19, String p20)
    {
        String v0_2;
        String v0_0 = fk.b.o;
        if (v0_0 == null) {
            v0_2 = 0;
        } else {
            if (v0_0.getCode() != 1) {
                v0_2 = "";
            } else {
                v0_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release().getAppId();
            }
        }
        this(p19, p20, v0_2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15360, 0);
        return;
    }

    public VerificationResult(int p1, String p2, String p3, String p4, vn.ai.trueid.sdk.models.Person p5, java.util.Map p6, vn.ai.trueid.sdk.models.Decision p7, vn.ai.trueid.sdk.models.FaceMatching p8, boolean p9, vn.ai.trueid.sdk.models.NfcInfo p10, String p11, vn.ai.trueid.sdk.models.RawImage p12, String p13, java.util.List p14)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.clientID = p3;
        this.requestId = p4;
        this.idInfo = p5;
        this.ekycResult = p6;
        this.decision = p7;
        this.faceMatching = p8;
        this.livenessCheck = p9;
        this.nfcInfo = p10;
        this.nfcData = p11;
        this.rawImage = p12;
        this.qrData = p13;
        this.records = p14;
        return;
    }

    public synthetic VerificationResult(int p18, String p19, String p20, String p21, vn.ai.trueid.sdk.models.Person p22, java.util.Map p23, vn.ai.trueid.sdk.models.Decision p24, vn.ai.trueid.sdk.models.FaceMatching p25, boolean p26, vn.ai.trueid.sdk.models.NfcInfo p27, String p28, vn.ai.trueid.sdk.models.RawImage p29, String p30, java.util.List p31, int p32, kotlin.jvm.internal.DefaultConstructorMarker p33)
    {
        int v3;
        if ((p32 & 1) == 0) {
            v3 = p18;
        } else {
            v3 = vn.ai.trueid.sdk.models.TrueError.Companion.getSuccess().getCode();
        }
        String v4;
        if ((p32 & 2) == 0) {
            v4 = p19;
        } else {
            v4 = "";
        }
        String v1_3;
        if ((p32 & 4) == 0) {
            v1_3 = p20;
        } else {
            v1_3 = 0;
        }
        String v6_1;
        if ((p32 & 8) == 0) {
            v6_1 = p21;
        } else {
            v6_1 = 0;
        }
        vn.ai.trueid.sdk.models.Person v7_1;
        if ((p32 & 16) == 0) {
            v7_1 = p22;
        } else {
            v7_1 = 0;
        }
        java.util.Map v8_1;
        if ((p32 & 32) == 0) {
            v8_1 = p23;
        } else {
            v8_1 = 0;
        }
        vn.ai.trueid.sdk.models.Decision v9_1;
        if ((p32 & 64) == 0) {
            v9_1 = p24;
        } else {
            v9_1 = 0;
        }
        vn.ai.trueid.sdk.models.FaceMatching v10_1;
        if ((p32 & 128) == 0) {
            v10_1 = p25;
        } else {
            v10_1 = 0;
        }
        boolean v11_1;
        if ((p32 & 256) == 0) {
            v11_1 = p26;
        } else {
            v11_1 = 0;
        }
        String v13;
        if ((p32 & 1024) == 0) {
            v13 = p28;
        } else {
            v13 = "";
        }
        vn.ai.trueid.sdk.models.RawImage v14;
        if ((p32 & 2048) == 0) {
            v14 = p29;
        } else {
            v14 = 0;
        }
        String v15;
        if ((p32 & 4096) == 0) {
            v15 = p30;
        } else {
            v15 = 0;
        }
        java.util.List v16;
        if ((p32 & 8192) == 0) {
            v16 = p31;
        } else {
            v16 = kotlin.collections.E.emptyList();
        }
        this(v3, v4, v1_3, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, p27, v13, v14, v15, v16);
        return;
    }

    public VerificationResult(int p19, String p20, java.util.List p21)
    {
        String v0_2;
        String v0_0 = fk.b.o;
        if (v0_0 == null) {
            v0_2 = 0;
        } else {
            if (v0_0.getCode() != 1) {
                v0_2 = "";
            } else {
                v0_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release().getAppId();
            }
        }
        this(p19, p20, v0_2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, p21, 7168, 0);
        return;
    }

    public VerificationResult(vn.ai.trueid.sdk.models.TrueError p18)
    {
        vn.ai.trueid.sdk.models.VerificationResult v0_0;
        int v1 = p18.getCode();
        String v2 = p18.getMessage();
        vn.ai.trueid.sdk.models.VerificationResult v0_4 = fk.b.o;
        if (v0_4 == null) {
            v0_0 = 0;
        } else {
            if (v0_4.getCode() != 1) {
                v0_0 = "";
            } else {
                v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release().getAppId();
            }
        }
        this(v1, v2, v0_0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15360, 0);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.VerificationResult copy$default(vn.ai.trueid.sdk.models.VerificationResult p14, int p15, String p16, String p17, String p18, vn.ai.trueid.sdk.models.Person p19, java.util.Map p20, vn.ai.trueid.sdk.models.Decision p21, vn.ai.trueid.sdk.models.FaceMatching p22, boolean p23, vn.ai.trueid.sdk.models.NfcInfo p24, String p25, vn.ai.trueid.sdk.models.RawImage p26, String p27, java.util.List p28, int p29, Object p30)
    {
        int v1_1;
        if ((p29 & 1) == 0) {
            v1_1 = p15;
        } else {
            v1_1 = p14.code;
        }
        String v2_1;
        if ((p29 & 2) == 0) {
            v2_1 = p16;
        } else {
            v2_1 = p14.errorMessage;
        }
        String v3_0;
        if ((p29 & 4) == 0) {
            v3_0 = p17;
        } else {
            v3_0 = p14.clientID;
        }
        String v4_1;
        if ((p29 & 8) == 0) {
            v4_1 = p18;
        } else {
            v4_1 = p14.requestId;
        }
        vn.ai.trueid.sdk.models.Person v5_1;
        if ((p29 & 16) == 0) {
            v5_1 = p19;
        } else {
            v5_1 = p14.idInfo;
        }
        java.util.Map v6_1;
        if ((p29 & 32) == 0) {
            v6_1 = p20;
        } else {
            v6_1 = p14.ekycResult;
        }
        vn.ai.trueid.sdk.models.Decision v7_1;
        if ((p29 & 64) == 0) {
            v7_1 = p21;
        } else {
            v7_1 = p14.decision;
        }
        vn.ai.trueid.sdk.models.FaceMatching v8_1;
        if ((p29 & 128) == 0) {
            v8_1 = p22;
        } else {
            v8_1 = p14.faceMatching;
        }
        boolean v9_1;
        if ((p29 & 256) == 0) {
            v9_1 = p23;
        } else {
            v9_1 = p14.livenessCheck;
        }
        vn.ai.trueid.sdk.models.NfcInfo v10_1;
        if ((p29 & 512) == 0) {
            v10_1 = p24;
        } else {
            v10_1 = p14.nfcInfo;
        }
        String v11_1;
        if ((p29 & 1024) == 0) {
            v11_1 = p25;
        } else {
            v11_1 = p14.nfcData;
        }
        vn.ai.trueid.sdk.models.RawImage v12_1;
        if ((p29 & 2048) == 0) {
            v12_1 = p26;
        } else {
            v12_1 = p14.rawImage;
        }
        String v13_1;
        if ((p29 & 4096) == 0) {
            v13_1 = p27;
        } else {
            v13_1 = p14.qrData;
        }
        java.util.List v29_1;
        if ((p29 & 8192) == 0) {
            v29_1 = p28;
        } else {
            v29_1 = p14.records;
        }
        return p14.copy(v1_1, v2_1, v3_0, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v29_1);
    }

    public final int component1()
    {
        return this.code;
    }

    public final vn.ai.trueid.sdk.models.NfcInfo component10()
    {
        return this.nfcInfo;
    }

    public final String component11()
    {
        return this.nfcData;
    }

    public final vn.ai.trueid.sdk.models.RawImage component12()
    {
        return this.rawImage;
    }

    public final String component13()
    {
        return this.qrData;
    }

    public final java.util.List component14()
    {
        return this.records;
    }

    public final String component2()
    {
        return this.errorMessage;
    }

    public final String component3()
    {
        return this.clientID;
    }

    public final String component4()
    {
        return this.requestId;
    }

    public final vn.ai.trueid.sdk.models.Person component5()
    {
        return this.idInfo;
    }

    public final java.util.Map component6()
    {
        return this.ekycResult;
    }

    public final vn.ai.trueid.sdk.models.Decision component7()
    {
        return this.decision;
    }

    public final vn.ai.trueid.sdk.models.FaceMatching component8()
    {
        return this.faceMatching;
    }

    public final boolean component9()
    {
        return this.livenessCheck;
    }

    public final vn.ai.trueid.sdk.models.VerificationResult copy(int p16, String p17, String p18, String p19, vn.ai.trueid.sdk.models.Person p20, java.util.Map p21, vn.ai.trueid.sdk.models.Decision p22, vn.ai.trueid.sdk.models.FaceMatching p23, boolean p24, vn.ai.trueid.sdk.models.NfcInfo p25, String p26, vn.ai.trueid.sdk.models.RawImage p27, String p28, java.util.List p29)
    {
        return new vn.ai.trueid.sdk.models.VerificationResult(p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.VerificationResult)) {
                if (this.code == ((vn.ai.trueid.sdk.models.VerificationResult) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorMessage, ((vn.ai.trueid.sdk.models.VerificationResult) p5).errorMessage)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientID, ((vn.ai.trueid.sdk.models.VerificationResult) p5).clientID)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestId, ((vn.ai.trueid.sdk.models.VerificationResult) p5).requestId)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.idInfo, ((vn.ai.trueid.sdk.models.VerificationResult) p5).idInfo)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.ekycResult, ((vn.ai.trueid.sdk.models.VerificationResult) p5).ekycResult)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.decision, ((vn.ai.trueid.sdk.models.VerificationResult) p5).decision)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.faceMatching, ((vn.ai.trueid.sdk.models.VerificationResult) p5).faceMatching)) {
                                                if (this.livenessCheck == ((vn.ai.trueid.sdk.models.VerificationResult) p5).livenessCheck) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.nfcInfo, ((vn.ai.trueid.sdk.models.VerificationResult) p5).nfcInfo)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.nfcData, ((vn.ai.trueid.sdk.models.VerificationResult) p5).nfcData)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.rawImage, ((vn.ai.trueid.sdk.models.VerificationResult) p5).rawImage)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.qrData, ((vn.ai.trueid.sdk.models.VerificationResult) p5).qrData)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.records, ((vn.ai.trueid.sdk.models.VerificationResult) p5).records)) {
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

    public final String getClientID()
    {
        return this.clientID;
    }

    public final int getCode()
    {
        return this.code;
    }

    public final vn.ai.trueid.sdk.models.Decision getDecision()
    {
        return this.decision;
    }

    public final java.util.Map getEkycResult()
    {
        return this.ekycResult;
    }

    public final String getErrorMessage()
    {
        return this.errorMessage;
    }

    public final vn.ai.trueid.sdk.models.FaceMatching getFaceMatching()
    {
        return this.faceMatching;
    }

    public final vn.ai.trueid.sdk.models.Person getIdInfo()
    {
        return this.idInfo;
    }

    public final boolean getLivenessCheck()
    {
        return this.livenessCheck;
    }

    public final String getNfcData()
    {
        return this.nfcData;
    }

    public final vn.ai.trueid.sdk.models.NfcInfo getNfcInfo()
    {
        return this.nfcInfo;
    }

    public final String getQrData()
    {
        return this.qrData;
    }

    public final vn.ai.trueid.sdk.models.RawImage getRawImage()
    {
        return this.rawImage;
    }

    public final java.util.List getRecords()
    {
        return this.records;
    }

    public final String getRequestId()
    {
        return this.requestId;
    }

    public int hashCode()
    {
        String v2_0;
        int v0_11 = (Integer.hashCode(this.code) * 31);
        String v2_12 = this.errorMessage;
        int v3 = 0;
        if (v2_12 != null) {
            v2_0 = v2_12.hashCode();
        } else {
            v2_0 = 0;
        }
        String v2_2;
        int v0_2 = ((v0_11 + v2_0) * 31);
        String v2_1 = this.clientID;
        if (v2_1 != null) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        String v2_4;
        int v0_4 = ((v0_2 + v2_2) * 31);
        String v2_3 = this.requestId;
        if (v2_3 != null) {
            v2_4 = v2_3.hashCode();
        } else {
            v2_4 = 0;
        }
        String v2_6;
        int v0_7 = ((v0_4 + v2_4) * 31);
        String v2_5 = this.idInfo;
        if (v2_5 != null) {
            v2_6 = v2_5.hashCode();
        } else {
            v2_6 = 0;
        }
        String v2_8;
        int v0_9 = ((v0_7 + v2_6) * 31);
        String v2_7 = this.ekycResult;
        if (v2_7 != null) {
            v2_8 = v2_7.hashCode();
        } else {
            v2_8 = 0;
        }
        String v2_10;
        int v0_12 = ((v0_9 + v2_8) * 31);
        String v2_9 = this.decision;
        if (v2_9 != null) {
            v2_10 = v2_9.hashCode();
        } else {
            v2_10 = 0;
        }
        String v2_13;
        int v0_14 = ((v0_12 + v2_10) * 31);
        String v2_11 = this.faceMatching;
        if (v2_11 != null) {
            v2_13 = v2_11.hashCode();
        } else {
            v2_13 = 0;
        }
        String v2_14 = this.livenessCheck;
        if (v2_14 != null) {
            v2_14 = 1;
        }
        String v2_16;
        int v0_18 = ((((v0_14 + v2_13) * 31) + v2_14) * 31);
        String v2_15 = this.nfcInfo;
        if (v2_15 != null) {
            v2_16 = v2_15.hashCode();
        } else {
            v2_16 = 0;
        }
        String v2_19;
        int v0_21 = e5.i.c(((v0_18 + v2_16) * 31), 31, this.nfcData);
        String v2_18 = this.rawImage;
        if (v2_18 != null) {
            v2_19 = v2_18.hashCode();
        } else {
            v2_19 = 0;
        }
        int v0_23 = ((v0_21 + v2_19) * 31);
        String v2_20 = this.qrData;
        if (v2_20 != null) {
            v3 = v2_20.hashCode();
        }
        return (this.records.hashCode() + ((v0_23 + v3) * 31));
    }

    public final void setClientID(String p1)
    {
        this.clientID = p1;
        return;
    }

    public final void setCode(int p1)
    {
        this.code = p1;
        return;
    }

    public final void setDecision(vn.ai.trueid.sdk.models.Decision p1)
    {
        this.decision = p1;
        return;
    }

    public final void setEkycResult(java.util.Map p1)
    {
        this.ekycResult = p1;
        return;
    }

    public final void setErrorMessage(String p1)
    {
        this.errorMessage = p1;
        return;
    }

    public final void setFaceMatching(vn.ai.trueid.sdk.models.FaceMatching p1)
    {
        this.faceMatching = p1;
        return;
    }

    public final void setIdInfo(vn.ai.trueid.sdk.models.Person p1)
    {
        this.idInfo = p1;
        return;
    }

    public final void setLivenessCheck(boolean p1)
    {
        this.livenessCheck = p1;
        return;
    }

    public final void setNfcData(String p1)
    {
        this.nfcData = p1;
        return;
    }

    public final void setNfcInfo(vn.ai.trueid.sdk.models.NfcInfo p1)
    {
        this.nfcInfo = p1;
        return;
    }

    public final void setQrData(String p1)
    {
        this.qrData = p1;
        return;
    }

    public final void setRawImage(vn.ai.trueid.sdk.models.RawImage p1)
    {
        this.rawImage = p1;
        return;
    }

    public final void setRecords(java.util.List p1)
    {
        this.records = p1;
        return;
    }

    public final void setRequestId(String p1)
    {
        this.requestId = p1;
        return;
    }

    public final org.json.JSONObject toJson()
    {
        return new org.json.JSONObject(this.toMap());
    }

    public final java.util.Map toMap()
    {
        kotlin.Pair v3 = kotlin.TuplesKt.to("code", Integer.valueOf(this.code));
        java.util.Map v1_19 = this.errorMessage;
        if (v1_19 == null) {
            v1_19 = "";
        }
        kotlin.TuplesKt.to("errorMessage", v1_19);
        java.util.Map v1_4 = ((Iterable) this.records);
        String v2_2 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v1_4, 10));
        java.util.Map v1_5 = v1_4.iterator();
        while (v1_5.hasNext()) {
            v2_2.add(((vn.ai.trueid.sdk.models.Record) v1_5.next()).toMap());
        }
        java.util.Map v1_9;
        kotlin.TuplesKt.to("records", v2_2);
        java.util.Map v1_7 = this.idInfo;
        String v2_3 = 0;
        if (v1_7 == null) {
            v1_9 = 0;
        } else {
            v1_9 = v1_7.toMap();
        }
        java.util.Map v1_13;
        kotlin.TuplesKt.to("idInfo", v1_9);
        kotlin.TuplesKt.to("ekycResult", this.ekycResult);
        java.util.Map v1_12 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release();
        if (v1_12 == null) {
            v1_13 = 0;
        } else {
            v1_13 = v1_12.getAppId();
        }
        java.util.Map v1_16;
        kotlin.TuplesKt.to("clientId", v1_13);
        java.util.Map v1 = vn.ai.trueid.sdk.networks.u.a;
        kotlin.TuplesKt.to("requestId", vn.ai.trueid.sdk.networks.q.f());
        java.util.Map v1_15 = this.rawImage;
        if (v1_15 == null) {
            v1_16 = 0;
        } else {
            v1_16 = v1_15.toMap();
        }
        java.util.Map v1_18;
        kotlin.TuplesKt.to("rawImage", v1_16);
        java.util.Map v1_17 = this.decision;
        if (v1_17 == null) {
            v1_18 = 0;
        } else {
            v1_18 = v1_17.toMap();
        }
        java.util.Map v1_21;
        kotlin.TuplesKt.to("decision", v1_18);
        java.util.Map v1_20 = this.faceMatching;
        if (v1_20 == null) {
            v1_21 = 0;
        } else {
            v1_21 = v1_20.toMap();
        }
        kotlin.TuplesKt.to("faceMatching", v1_21);
        java.util.Map v1_22 = this.nfcInfo;
        if (v1_22 != null) {
            v2_3 = v1_22.toMap();
        }
        kotlin.TuplesKt.to("nfcInfo", v2_3);
        kotlin.TuplesKt.to("nfcData", this.nfcData);
        kotlin.TuplesKt.to("qrData", this.qrData);
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v3, kotlin.TuplesKt.to("livenessCheck", Boolean.valueOf(this.livenessCheck))}));
    }

    public String toString()
    {
        String v3_0 = this.clientID;
        String v4 = this.requestId;
        vn.ai.trueid.sdk.models.Person v5 = this.idInfo;
        java.util.Map v6 = this.ekycResult;
        vn.ai.trueid.sdk.models.Decision v7 = this.decision;
        vn.ai.trueid.sdk.models.FaceMatching v8 = this.faceMatching;
        boolean v9 = this.livenessCheck;
        vn.ai.trueid.sdk.models.NfcInfo v10 = this.nfcInfo;
        String v11 = this.nfcData;
        vn.ai.trueid.sdk.models.RawImage v12 = this.rawImage;
        java.util.List v14 = this.records;
        String v16 = this.qrData;
        String v0_2 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(this.code, "VerificationResult(code=", ", errorMessage=", this.errorMessage, ", clientID=");
        q9.H.p(v0_2, v3_0, ", requestId=", v4, ", idInfo=");
        v0_2.append(v5);
        v0_2.append(", ekycResult=");
        v0_2.append(v6);
        v0_2.append(", decision=");
        v0_2.append(v7);
        v0_2.append(", faceMatching=");
        v0_2.append(v8);
        v0_2.append(", livenessCheck=");
        v0_2.append(v9);
        v0_2.append(", nfcInfo=");
        v0_2.append(v10);
        v0_2.append(", nfcData=");
        v0_2.append(v11);
        v0_2.append(", rawImage=");
        v0_2.append(v12);
        v0_2.append(", qrData=");
        return A1.a.m(v0_2, v16, ", records=", v14, ")");
    }
}

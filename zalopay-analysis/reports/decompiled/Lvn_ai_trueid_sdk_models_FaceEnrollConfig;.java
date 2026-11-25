package vn.ai.trueid.sdk.models;
public final class FaceEnrollConfig {
    public static final vn.ai.trueid.sdk.models.FaceEnrollConfig$Companion Companion;
    public static final int LIVENESS_TYPE_FLASH;
    public static final int LIVENESS_TYPE_ZOOM;
    private final String accessTokenEndPoint;
    private final String animationOvalColor;
    private final String appId;
    private final String appSecret;
    private final String backgroundColor;
    private final String closeColor;
    private final String configHeader;
    private final String createRequestEndPoint;
    private final String domain;
    private final String enrollEndPoint;
    private final String errorColor;
    private final String fontName;
    private final boolean isAutoProcess;
    private final boolean isHideQRScan;
    private final boolean isShowCloseButton;
    private final boolean isShowLivenessSuccess;
    private int livenessType;
    private final int maxRetry;
    private final String ovalColor;
    private final String primaryColor;
    private final boolean returnNFC;
    private final String sdkKey;
    private final String secondaryColor;
    private final String signVersion;
    private final String textButtonColor;
    private final String textColor;
    private final boolean trueidWatermark;

    static FaceEnrollConfig()
    {
        wmoi.igob.jtgo(vn.ai.trueid.sdk.models.FaceEnrollConfig, 20325, 20325);
        vn.ai.trueid.sdk.models.FaceEnrollConfig.Companion = new vn.ai.trueid.sdk.models.FaceEnrollConfig$Companion(0);
        return;
    }

    public FaceEnrollConfig(String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38)
    {
        this(p31, p32, p33, p34, p35, p36, p38, "1", p37, 3, 1, 0, "#26B840", "#42526E", "#FF6234", "#253858", "#FFFFFF", "#000000", 0, 0, 0, 0, 0, 0, 0, 0, 0, 133433344, 0);
        return;
    }

    public synthetic FaceEnrollConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, int p10, kotlin.jvm.internal.DefaultConstructorMarker p11)
    {
        if ((p10 & 1) != 0) {
            p2 = "https://";
        }
        if ((p10 & 2) != 0) {
            p3 = "";
        }
        if ((p10 & 4) != 0) {
            p4 = "";
        }
        if ((p10 & 8) != 0) {
            p5 = "";
        }
        if ((p10 & 16) != 0) {
            p6 = "";
        }
        if ((p10 & 32) != 0) {
            p7 = "";
        }
        if ((p10 & 64) != 0) {
            p8 = "";
        }
        String v8_1;
        String v10_2;
        String v11_5;
        vn.ai.trueid.sdk.models.FaceEnrollConfig v3_1;
        String v6_1;
        String v9_1;
        vn.ai.trueid.sdk.models.FaceEnrollConfig v5_1;
        String v7_1;
        String v4_1;
        if ((p10 & 128) == 0) {
            v11_5 = p9;
            v10_2 = p8;
            v8_1 = p6;
            v9_1 = p7;
            v6_1 = p4;
            v7_1 = p5;
            v4_1 = p2;
            v5_1 = p3;
            v3_1 = this;
        } else {
            v11_5 = "";
            v9_1 = p7;
            v10_2 = p8;
            v7_1 = p5;
            v8_1 = p6;
            v5_1 = p3;
            v6_1 = p4;
            v3_1 = this;
            v4_1 = p2;
        }
        v3_1(v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_2, v11_5);
        return;
    }

    public FaceEnrollConfig(String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39)
    {
        this(p31, p32, p33, p34, p35, p36, p38, p39, p37, 3, 1, 0, "#26B840", "#42526E", "#FF6234", "#253858", "#FFFFFF", "#000000", 0, 0, 0, 0, 0, 0, 0, 0, 0, 133433344, 0);
        return;
    }

    public synthetic FaceEnrollConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, int p11, kotlin.jvm.internal.DefaultConstructorMarker p12)
    {
        if ((p11 & 1) != 0) {
            p2 = "https://";
        }
        if ((p11 & 2) != 0) {
            p3 = "";
        }
        if ((p11 & 4) != 0) {
            p4 = "";
        }
        if ((p11 & 8) != 0) {
            p5 = "";
        }
        if ((p11 & 16) != 0) {
            p6 = "";
        }
        if ((p11 & 32) != 0) {
            p7 = "";
        }
        if ((p11 & 64) != 0) {
            p8 = "";
        }
        if ((p11 & 128) != 0) {
            p9 = "";
        }
        if ((p11 & 256) != 0) {
            p10 = "1";
        }
        this(p2, p3, p4, p5, p6, p7, p8, p9, p10);
        return;
    }

    public FaceEnrollConfig(String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39, int p40, boolean p41)
    {
        this(p31, p32, p33, p34, p35, p36, p38, p39, p37, 3, 1, 0, "#26B840", "#42526E", "#FF6234", "#253858", "#FFFFFF", "#000000", 0, p40, p41, 0, 0, 0, 0, 0, 0, 132384768, 0);
        return;
    }

    public synthetic FaceEnrollConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, int p11, boolean p12, int p13, kotlin.jvm.internal.DefaultConstructorMarker p14)
    {
        if ((p13 & 1) != 0) {
            p2 = "https://";
        }
        if ((p13 & 2) != 0) {
            p3 = "";
        }
        if ((p13 & 4) != 0) {
            p4 = "";
        }
        if ((p13 & 8) != 0) {
            p5 = "";
        }
        if ((p13 & 16) != 0) {
            p6 = "";
        }
        if ((p13 & 32) != 0) {
            p7 = "";
        }
        if ((p13 & 64) != 0) {
            p8 = "";
        }
        if ((p13 & 128) != 0) {
            p9 = "";
        }
        if ((p13 & 256) != 0) {
            p10 = "1";
        }
        if ((p13 & 512) != 0) {
            p11 = 0;
        }
        vn.ai.trueid.sdk.models.FaceEnrollConfig v3_1;
        vn.ai.trueid.sdk.models.FaceEnrollConfig v5_1;
        String v11_1;
        String v12_1;
        String v8_1;
        String v14_9;
        String v6_1;
        String v13_2;
        String v9_1;
        String v4_1;
        String v7_1;
        String v10_1;
        if ((p13 & 1024) == 0) {
            v14_9 = p12;
            v13_2 = p11;
            v11_1 = p9;
            v12_1 = p10;
            v9_1 = p7;
            v10_1 = p8;
            v7_1 = p5;
            v8_1 = p6;
            v5_1 = p3;
            v6_1 = p4;
            v3_1 = this;
            v4_1 = p2;
        } else {
            v14_9 = 0;
            v12_1 = p10;
            v13_2 = p11;
            v10_1 = p8;
            v11_1 = p9;
            v8_1 = p6;
            v9_1 = p7;
            v6_1 = p4;
            v7_1 = p5;
            v4_1 = p2;
            v5_1 = p3;
            v3_1 = this;
        }
        v3_1(v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_2, v14_9);
        return;
    }

    public FaceEnrollConfig(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, int p10, boolean p11, boolean p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, int p20, boolean p21, String p22, String p23, boolean p24, String p25, boolean p26, boolean p27)
    {
        this.domain = p1;
        this.enrollEndPoint = p2;
        this.createRequestEndPoint = p3;
        this.accessTokenEndPoint = p4;
        this.appId = p5;
        this.appSecret = p6;
        this.sdkKey = p7;
        this.signVersion = p8;
        this.configHeader = p9;
        this.maxRetry = p10;
        this.isShowCloseButton = p11;
        this.trueidWatermark = p12;
        this.primaryColor = p13;
        this.secondaryColor = p14;
        this.errorColor = p15;
        this.textColor = p16;
        this.backgroundColor = p17;
        this.closeColor = p18;
        this.textButtonColor = p19;
        this.livenessType = p20;
        this.isAutoProcess = p21;
        this.ovalColor = p22;
        this.animationOvalColor = p23;
        this.isHideQRScan = p24;
        this.fontName = p25;
        this.isShowLivenessSuccess = p26;
        this.returnNFC = p27;
        return;
    }

    public synthetic FaceEnrollConfig(String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39, int p40, boolean p41, boolean p42, String p43, String p44, String p45, String p46, String p47, String p48, String p49, int p50, boolean p51, String p52, String p53, boolean p54, String p55, boolean p56, boolean p57, int p58, kotlin.jvm.internal.DefaultConstructorMarker p59)
    {
        int v12;
        if ((p58 & 512) == 0) {
            v12 = p40;
        } else {
            v12 = 3;
        }
        boolean v13;
        if ((p58 & 1024) == 0) {
            v13 = p41;
        } else {
            v13 = 1;
        }
        boolean v14;
        if ((p58 & 2048) == 0) {
            v14 = p42;
        } else {
            v14 = 0;
        }
        String v15;
        if ((p58 & 4096) == 0) {
            v15 = p43;
        } else {
            v15 = 0;
        }
        String v16;
        if ((p58 & 8192) == 0) {
            v16 = p44;
        } else {
            v16 = 0;
        }
        String v17;
        if ((p58 & 16384) == 0) {
            v17 = p45;
        } else {
            v17 = 0;
        }
        String v18;
        if ((32768 & p58) == 0) {
            v18 = p46;
        } else {
            v18 = 0;
        }
        String v19;
        if ((65536 & p58) == 0) {
            v19 = p47;
        } else {
            v19 = 0;
        }
        String v20;
        if ((131072 & p58) == 0) {
            v20 = p48;
        } else {
            v20 = 0;
        }
        String v21;
        if ((262144 & p58) == 0) {
            v21 = p49;
        } else {
            v21 = 0;
        }
        int v22;
        if ((524288 & p58) == 0) {
            v22 = p50;
        } else {
            v22 = 0;
        }
        boolean v23;
        if ((1048576 & p58) == 0) {
            v23 = p51;
        } else {
            v23 = 0;
        }
        String v24;
        if ((2097152 & p58) == 0) {
            v24 = p52;
        } else {
            v24 = "";
        }
        String v25;
        if ((4194304 & p58) == 0) {
            v25 = p53;
        } else {
            v25 = "";
        }
        boolean v26;
        if ((8388608 & p58) == 0) {
            v26 = p54;
        } else {
            v26 = 0;
        }
        String v27;
        if ((16777216 & p58) == 0) {
            v27 = p55;
        } else {
            v27 = 0;
        }
        boolean v28;
        if ((33554432 & p58) == 0) {
            v28 = p56;
        } else {
            v28 = 1;
        }
        String v11;
        String v9;
        String v5_0;
        String v6;
        String v3_0;
        boolean v29;
        vn.ai.trueid.sdk.models.FaceEnrollConfig v2_0;
        String v8;
        String v4_0;
        String v10;
        String v7;
        if ((p58 & 67108864) == 0) {
            v29 = p57;
            v2_0 = this;
            v3_0 = p31;
            v4_0 = p32;
            v5_0 = p33;
            v6 = p34;
            v7 = p35;
            v8 = p36;
            v9 = p37;
            v10 = p38;
            v11 = p39;
        } else {
            v29 = 0;
            v2_0 = this;
            v4_0 = p32;
            v5_0 = p33;
            v6 = p34;
            v7 = p35;
            v8 = p36;
            v9 = p37;
            v10 = p38;
            v11 = p39;
            v3_0 = p31;
        }
        v2_0(v3_0, v4_0, v5_0, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28, v29);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceEnrollConfig copy$default(vn.ai.trueid.sdk.models.FaceEnrollConfig p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, int p27, boolean p28, boolean p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, int p37, boolean p38, String p39, String p40, boolean p41, String p42, boolean p43, boolean p44, int p45, Object p46)
    {
        String v2_1;
        if ((p45 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.domain;
        }
        String v3_1;
        if ((p45 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.enrollEndPoint;
        }
        String v4_1;
        if ((p45 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.createRequestEndPoint;
        }
        String v5_1;
        if ((p45 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.accessTokenEndPoint;
        }
        String v6_1;
        if ((p45 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.appId;
        }
        String v7_1;
        if ((p45 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.appSecret;
        }
        String v8_1;
        if ((p45 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.sdkKey;
        }
        String v9_1;
        if ((p45 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.signVersion;
        }
        String v10_1;
        if ((p45 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.configHeader;
        }
        int v11_1;
        if ((p45 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.maxRetry;
        }
        boolean v12_1;
        if ((p45 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.isShowCloseButton;
        }
        boolean v13_1;
        if ((p45 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.trueidWatermark;
        }
        String v14_1;
        if ((p45 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.primaryColor;
        }
        String v15_1;
        if ((p45 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.secondaryColor;
        }
        String v2_3;
        if ((p45 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.errorColor;
        }
        boolean v1_7;
        if ((p45 & 32768) == 0) {
            v1_7 = p33;
        } else {
            v1_7 = p17.textColor;
        }
        boolean v1_8;
        if ((p45 & 65536) == 0) {
            v1_8 = p34;
        } else {
            v1_8 = p17.backgroundColor;
        }
        boolean v1_9;
        if ((p45 & 131072) == 0) {
            v1_9 = p35;
        } else {
            v1_9 = p17.closeColor;
        }
        boolean v1_10;
        if ((p45 & 262144) == 0) {
            v1_10 = p36;
        } else {
            v1_10 = p17.textButtonColor;
        }
        boolean v1_11;
        if ((p45 & 524288) == 0) {
            v1_11 = p37;
        } else {
            v1_11 = p17.livenessType;
        }
        boolean v1_12;
        if ((p45 & 1048576) == 0) {
            v1_12 = p38;
        } else {
            v1_12 = p17.isAutoProcess;
        }
        boolean v1_1;
        if ((p45 & 2097152) == 0) {
            v1_1 = p39;
        } else {
            v1_1 = p17.ovalColor;
        }
        boolean v1_2;
        if ((p45 & 4194304) == 0) {
            v1_2 = p40;
        } else {
            v1_2 = p17.animationOvalColor;
        }
        boolean v1_3;
        if ((p45 & 8388608) == 0) {
            v1_3 = p41;
        } else {
            v1_3 = p17.isHideQRScan;
        }
        boolean v1_4;
        if ((p45 & 16777216) == 0) {
            v1_4 = p42;
        } else {
            v1_4 = p17.fontName;
        }
        boolean v1_5;
        if ((p45 & 33554432) == 0) {
            v1_5 = p43;
        } else {
            v1_5 = p17.isShowLivenessSuccess;
        }
        boolean v45_1;
        boolean v44_1;
        if ((p45 & 67108864) == 0) {
            v45_1 = p44;
            v44_1 = v1_5;
        } else {
            v44_1 = v1_5;
            v45_1 = p17.returnNFC;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_7, v1_8, v1_9, v1_10, v1_11, v1_12, v1_1, v1_2, v1_3, v1_4, v44_1, v45_1);
    }

    public final String component1()
    {
        return this.domain;
    }

    public final int component10()
    {
        return this.maxRetry;
    }

    public final boolean component11()
    {
        return this.isShowCloseButton;
    }

    public final boolean component12()
    {
        return this.trueidWatermark;
    }

    public final String component13()
    {
        return this.primaryColor;
    }

    public final String component14()
    {
        return this.secondaryColor;
    }

    public final String component15()
    {
        return this.errorColor;
    }

    public final String component16()
    {
        return this.textColor;
    }

    public final String component17()
    {
        return this.backgroundColor;
    }

    public final String component18()
    {
        return this.closeColor;
    }

    public final String component19()
    {
        return this.textButtonColor;
    }

    public final String component2()
    {
        return this.enrollEndPoint;
    }

    public final int component20()
    {
        return this.livenessType;
    }

    public final boolean component21()
    {
        return this.isAutoProcess;
    }

    public final String component22()
    {
        return this.ovalColor;
    }

    public final String component23()
    {
        return this.animationOvalColor;
    }

    public final boolean component24()
    {
        return this.isHideQRScan;
    }

    public final String component25()
    {
        return this.fontName;
    }

    public final boolean component26()
    {
        return this.isShowLivenessSuccess;
    }

    public final boolean component27()
    {
        return this.returnNFC;
    }

    public final String component3()
    {
        return this.createRequestEndPoint;
    }

    public final String component4()
    {
        return this.accessTokenEndPoint;
    }

    public final String component5()
    {
        return this.appId;
    }

    public final String component6()
    {
        return this.appSecret;
    }

    public final String component7()
    {
        return this.sdkKey;
    }

    public final String component8()
    {
        return this.signVersion;
    }

    public final String component9()
    {
        return this.configHeader;
    }

    public final org.json.JSONObject configHeaderJson()
    {
        try {
            org.json.JSONObject v0_1 = new org.json.JSONObject(this.configHeader);
        } catch (org.json.JSONObject v0_3) {
            v0_1 = kotlin.ResultKt.createFailure(v0_3);
        }
        org.json.JSONObject v1_2 = new org.json.JSONObject();
        if ((v0_1 instanceof ng.K)) {
            v0_1 = v1_2;
        }
        return ((org.json.JSONObject) v0_1);
    }

    public final vn.ai.trueid.sdk.models.FaceEnrollConfig copy(String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, int p38, boolean p39, boolean p40, String p41, String p42, String p43, String p44, String p45, String p46, String p47, int p48, boolean p49, String p50, String p51, boolean p52, String p53, boolean p54, boolean p55)
    {
        return new vn.ai.trueid.sdk.models.FaceEnrollConfig(p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50, p51, p52, p53, p54, p55);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceEnrollConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.domain, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).domain)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.enrollEndPoint, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).enrollEndPoint)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.createRequestEndPoint, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).createRequestEndPoint)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.accessTokenEndPoint, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).accessTokenEndPoint)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.appId, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).appId)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.appSecret, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).appSecret)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.sdkKey, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).sdkKey)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.signVersion, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).signVersion)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.configHeader, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).configHeader)) {
                                                    if (this.maxRetry == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).maxRetry) {
                                                        if (this.isShowCloseButton == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).isShowCloseButton) {
                                                            if (this.trueidWatermark == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).trueidWatermark) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.primaryColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).primaryColor)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.secondaryColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).secondaryColor)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).errorColor)) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.textColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).textColor)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.backgroundColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).backgroundColor)) {
                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.closeColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).closeColor)) {
                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.textButtonColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).textButtonColor)) {
                                                                                            if (this.livenessType == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).livenessType) {
                                                                                                if (this.isAutoProcess == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).isAutoProcess) {
                                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.ovalColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).ovalColor)) {
                                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.animationOvalColor, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).animationOvalColor)) {
                                                                                                            if (this.isHideQRScan == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).isHideQRScan) {
                                                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.fontName, ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).fontName)) {
                                                                                                                    if (this.isShowLivenessSuccess == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).isShowLivenessSuccess) {
                                                                                                                        if (this.returnNFC == ((vn.ai.trueid.sdk.models.FaceEnrollConfig) p5).returnNFC) {
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

    public final String getAccessTokenEndPoint()
    {
        return this.accessTokenEndPoint;
    }

    public final String getAnimationOvalColor()
    {
        return this.animationOvalColor;
    }

    public final String getAppId()
    {
        return this.appId;
    }

    public final String getAppSecret()
    {
        return this.appSecret;
    }

    public final String getBackgroundColor()
    {
        return this.backgroundColor;
    }

    public final String getCloseColor()
    {
        return this.closeColor;
    }

    public final String getConfigHeader()
    {
        return this.configHeader;
    }

    public final String getCreateRequestEndPoint()
    {
        return this.createRequestEndPoint;
    }

    public final String getDomain()
    {
        return this.domain;
    }

    public final String getEnrollEndPoint()
    {
        return this.enrollEndPoint;
    }

    public final String getErrorColor()
    {
        return this.errorColor;
    }

    public final String getFontName()
    {
        return this.fontName;
    }

    public final int getLivenessType()
    {
        return this.livenessType;
    }

    public final int getMaxRetry()
    {
        return this.maxRetry;
    }

    public final String getOvalColor()
    {
        return this.ovalColor;
    }

    public final String getPrimaryColor()
    {
        return this.primaryColor;
    }

    public final boolean getReturnNFC()
    {
        return this.returnNFC;
    }

    public final String getSdkKey()
    {
        return this.sdkKey;
    }

    public final String getSecondaryColor()
    {
        return this.secondaryColor;
    }

    public final String getSignVersion()
    {
        return this.signVersion;
    }

    public final String getTextButtonColor()
    {
        return this.textButtonColor;
    }

    public final String getTextColor()
    {
        return this.textColor;
    }

    public final boolean getTrueidWatermark()
    {
        return this.trueidWatermark;
    }

    public int hashCode()
    {
        int vtmp10 = Kb.e.v(this.maxRetry, e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.domain.hashCode() * 31), 31, this.enrollEndPoint), 31, this.createRequestEndPoint), 31, this.accessTokenEndPoint), 31, this.appId), 31, this.appSecret), 31, this.sdkKey), 31, this.signVersion), 31, this.configHeader), 31);
        int v2_12 = this.isShowCloseButton;
        boolean v3 = 1;
        if (v2_12 != 0) {
            v2_12 = 1;
        }
        int v2_13 = this.trueidWatermark;
        if (v2_13 != 0) {
            v2_13 = 1;
        }
        int v2_15;
        int v0_24 = ((((vtmp10 + v2_12) * 31) + v2_13) * 31);
        int v2_14 = this.primaryColor;
        int v4 = 0;
        if (v2_14 != 0) {
            v2_15 = v2_14.hashCode();
        } else {
            v2_15 = 0;
        }
        int v2_18;
        int v0_26 = ((v0_24 + v2_15) * 31);
        int v2_17 = this.secondaryColor;
        if (v2_17 != 0) {
            v2_18 = v2_17.hashCode();
        } else {
            v2_18 = 0;
        }
        int v2_20;
        int v0_28 = ((v0_26 + v2_18) * 31);
        int v2_19 = this.errorColor;
        if (v2_19 != 0) {
            v2_20 = v2_19.hashCode();
        } else {
            v2_20 = 0;
        }
        int v2_22;
        int v0_30 = ((v0_28 + v2_20) * 31);
        int v2_21 = this.textColor;
        if (v2_21 != 0) {
            v2_22 = v2_21.hashCode();
        } else {
            v2_22 = 0;
        }
        int v2_24;
        int v0_33 = ((v0_30 + v2_22) * 31);
        int v2_23 = this.backgroundColor;
        if (v2_23 != 0) {
            v2_24 = v2_23.hashCode();
        } else {
            v2_24 = 0;
        }
        int v2_27;
        int v0_35 = ((v0_33 + v2_24) * 31);
        int v2_25 = this.closeColor;
        if (v2_25 != 0) {
            v2_27 = v2_25.hashCode();
        } else {
            v2_27 = 0;
        }
        int v2_29;
        int v0_37 = ((v0_35 + v2_27) * 31);
        int v2_28 = this.textButtonColor;
        if (v2_28 != 0) {
            v2_29 = v2_28.hashCode();
        } else {
            v2_29 = 0;
        }
        int vtmp18 = Kb.e.v(this.livenessType, ((v0_37 + v2_29) * 31), 31);
        int v2_31 = this.isAutoProcess;
        if (v2_31 != 0) {
            v2_31 = 1;
        }
        int vtmp20 = e5.i.c(e5.i.c(((vtmp18 + v2_31) * 31), 31, this.ovalColor), 31, this.animationOvalColor);
        int v2_2 = this.isHideQRScan;
        if (v2_2 != 0) {
            v2_2 = 1;
        }
        int v0_6 = ((vtmp20 + v2_2) * 31);
        int v2_4 = this.fontName;
        if (v2_4 != 0) {
            v4 = v2_4.hashCode();
        }
        int v2_5 = this.isShowLivenessSuccess;
        if (v2_5 != 0) {
            v2_5 = 1;
        }
        boolean v1_0 = this.returnNFC;
        if (!v1_0) {
            v3 = v1_0;
        }
        return (((((v0_6 + v4) * 31) + v2_5) * 31) + v3);
    }

    public final boolean isAutoProcess()
    {
        return this.isAutoProcess;
    }

    public final boolean isHideQRScan()
    {
        return this.isHideQRScan;
    }

    public final boolean isShowCloseButton()
    {
        return this.isShowCloseButton;
    }

    public final boolean isShowLivenessSuccess()
    {
        return this.isShowLivenessSuccess;
    }

    public final boolean isValidConfigHeaderJson()
    {
        int v0_3;
        if (this.configHeader.length() <= 0) {
            v0_3 = 0;
        } else {
            try {
                int v0_1 = new org.json.JSONObject(this.configHeader);
            } catch (int v0_6) {
                v0_1 = kotlin.ResultKt.createFailure(v0_6);
            }
            if ((v0_1 instanceof ng.K)) {
            } else {
                v0_3 = 1;
            }
        }
        return v0_3;
    }

    public final void setLivenessType(int p1)
    {
        this.livenessType = p1;
        return;
    }

    public final java.util.Map toMap()
    {
        String v3_0 = kotlin.TuplesKt.to("domain", this.domain);
        kotlin.TuplesKt.to("enrollEndPoint", this.enrollEndPoint);
        kotlin.TuplesKt.to("createRequestEndPoint", this.createRequestEndPoint);
        kotlin.TuplesKt.to("accessTokenEndPoint", this.accessTokenEndPoint);
        kotlin.TuplesKt.to("appId", this.appId);
        kotlin.TuplesKt.to("appSecret", this.appSecret);
        kotlin.TuplesKt.to("signVersion", this.signVersion);
        kotlin.TuplesKt.to("maxRetry", Integer.valueOf(this.maxRetry));
        kotlin.TuplesKt.to("isShowCloseButton", Boolean.valueOf(this.isShowCloseButton));
        kotlin.TuplesKt.to("primaryColor", this.primaryColor);
        kotlin.TuplesKt.to("secondaryColor", this.secondaryColor);
        kotlin.TuplesKt.to("errorColor", this.errorColor);
        kotlin.TuplesKt.to("textColor", this.textColor);
        kotlin.TuplesKt.to("backgroundColor", this.backgroundColor);
        kotlin.TuplesKt.to("closeColor", this.closeColor);
        kotlin.TuplesKt.to("textButtonColor", this.textButtonColor);
        java.util.Map v1_20 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {v3_0, kotlin.TuplesKt.to("livenessType", Integer.valueOf(this.livenessType))}));
        if (this.isValidConfigHeaderJson()) {
            v1_20.put("configHeader", this.configHeader);
        }
        return v1_20;
    }

    public String toString()
    {
        String v3_0 = this.createRequestEndPoint;
        boolean v4_0 = this.accessTokenEndPoint;
        String v5 = this.appId;
        String v6 = this.appSecret;
        String v7 = this.sdkKey;
        String v8 = this.signVersion;
        String v9 = this.configHeader;
        int v10 = this.maxRetry;
        boolean v11 = this.isShowCloseButton;
        boolean v12 = this.trueidWatermark;
        String v14 = this.secondaryColor;
        String v16 = this.errorColor;
        String v17 = this.textColor;
        String v18 = this.backgroundColor;
        String v19 = this.closeColor;
        String v20 = this.textButtonColor;
        int v21 = this.livenessType;
        boolean v22 = this.isAutoProcess;
        String v23 = this.ovalColor;
        String v24 = this.animationOvalColor;
        boolean v25 = this.isHideQRScan;
        String v26 = this.fontName;
        boolean v27 = this.isShowLivenessSuccess;
        boolean v28 = this.returnNFC;
        String v29 = this.primaryColor;
        String v0_2 = androidx.camera.camera2.internal.G.i("FaceEnrollConfig(domain=", this.domain, ", enrollEndPoint=", this.enrollEndPoint, ", createRequestEndPoint=");
        q9.H.p(v0_2, v3_0, ", accessTokenEndPoint=", v4_0, ", appId=");
        q9.H.p(v0_2, v5, ", appSecret=", v6, ", sdkKey=");
        q9.H.p(v0_2, v7, ", signVersion=", v8, ", configHeader=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(v10, v9, ", maxRetry=", ", isShowCloseButton=", v0_2);
        q9.H.q(v0_2, v11, ", trueidWatermark=", v12, ", primaryColor=");
        q9.H.p(v0_2, v29, ", secondaryColor=", v14, ", errorColor=");
        q9.H.p(v0_2, v16, ", textColor=", v17, ", backgroundColor=");
        q9.H.p(v0_2, v18, ", closeColor=", v19, ", textButtonColor=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(v21, v20, ", livenessType=", ", isAutoProcess=", v0_2);
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.A(v0_2, v22, ", ovalColor=", v23, ", animationOvalColor=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.z(v0_2, v24, ", isHideQRScan=", v25, ", fontName=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.z(v0_2, v26, ", isShowLivenessSuccess=", v27, ", returnNFC=");
        return androidx.activity.result.e.q(")", v0_2, v28);
    }
}

package vn.ai.trueid.sdk.models;
public final class FaceAuthenConfig {
    private final String accessTokenEndPoint;
    private final String animationOvalColor;
    private final String appId;
    private final String appSecret;
    private final String authenEndPoint;
    private final String backgroundColor;
    private final String closeColor;
    private final String configHeader;
    private final String createRequestEndPoint;
    private final String domain;
    private final String errorColor;
    private java.util.List errorDetails;
    private final String fontName;
    private final boolean isAutoProcess;
    private final boolean isShowCloseButton;
    private final boolean isShowLivenessSuccess;
    private int livenessType;
    private final int maxRetry;
    private final String ovalColor;
    private final String primaryColor;
    private final String sdkKey;
    private final String secondaryColor;
    private final String signVersion;
    private final String textButtonColor;
    private final String textColor;
    private final boolean trueidWatermark;

    public FaceAuthenConfig(String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37)
    {
        this(p30, p31, p32, p33, p34, p35, p37, "1", p36, 3, 1, 0, "#26B840", "#42526E", "#FF6234", "#253858", "#FFFFFF", "#000000", 0, 0, 0, 0, 0, 0, 0, 0, 66324480, 0);
        return;
    }

    public synthetic FaceAuthenConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, int p10, kotlin.jvm.internal.DefaultConstructorMarker p11)
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
        String v11_5;
        vn.ai.trueid.sdk.models.FaceAuthenConfig v3_1;
        String v6_1;
        String v7_1;
        String v9_1;
        vn.ai.trueid.sdk.models.FaceAuthenConfig v5_1;
        String v10_2;
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

    public FaceAuthenConfig(String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38)
    {
        this(p30, p31, p32, p33, p34, p35, p37, p38, p36, 3, 1, 0, "#26B840", "#42526E", "#FF6234", "#253858", "#FFFFFF", "#000000", 0, 0, 0, 0, 0, 0, 0, 0, 66324480, 0);
        return;
    }

    public synthetic FaceAuthenConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, int p11, kotlin.jvm.internal.DefaultConstructorMarker p12)
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

    public FaceAuthenConfig(String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, int p39, boolean p40)
    {
        this(p30, p31, p32, p33, p34, p35, p37, p38, p36, 3, 1, 0, "#26B840", "#42526E", "#FF6234", "#253858", "#FFFFFF", "#000000", 0, p39, p40, 0, 0, 0, 0, 0, 65275904, 0);
        return;
    }

    public synthetic FaceAuthenConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, int p11, boolean p12, int p13, kotlin.jvm.internal.DefaultConstructorMarker p14)
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
        String v12_1;
        String v14_9;
        String v6_1;
        vn.ai.trueid.sdk.models.FaceAuthenConfig v5_1;
        String v9_1;
        String v4_1;
        String v10_1;
        String v11_1;
        String v7_1;
        String v13_2;
        vn.ai.trueid.sdk.models.FaceAuthenConfig v3_1;
        String v8_1;
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

    public FaceAuthenConfig(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, int p10, boolean p11, boolean p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, int p20, boolean p21, java.util.List p22, String p23, String p24, String p25, boolean p26)
    {
        this.domain = p1;
        this.authenEndPoint = p2;
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
        this.errorDetails = p22;
        this.ovalColor = p23;
        this.animationOvalColor = p24;
        this.fontName = p25;
        this.isShowLivenessSuccess = p26;
        return;
    }

    public synthetic FaceAuthenConfig(String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, int p39, boolean p40, boolean p41, String p42, String p43, String p44, String p45, String p46, String p47, String p48, int p49, boolean p50, java.util.List p51, String p52, String p53, String p54, boolean p55, int p56, kotlin.jvm.internal.DefaultConstructorMarker p57)
    {
        int v12;
        if ((p56 & 512) == 0) {
            v12 = p39;
        } else {
            v12 = 3;
        }
        boolean v13;
        if ((p56 & 1024) == 0) {
            v13 = p40;
        } else {
            v13 = 1;
        }
        boolean v14;
        if ((p56 & 2048) == 0) {
            v14 = p41;
        } else {
            v14 = 0;
        }
        String v15;
        if ((p56 & 4096) == 0) {
            v15 = p42;
        } else {
            v15 = 0;
        }
        String v16;
        if ((p56 & 8192) == 0) {
            v16 = p43;
        } else {
            v16 = 0;
        }
        String v17;
        if ((p56 & 16384) == 0) {
            v17 = p44;
        } else {
            v17 = 0;
        }
        String v18;
        if ((32768 & p56) == 0) {
            v18 = p45;
        } else {
            v18 = 0;
        }
        String v19;
        if ((65536 & p56) == 0) {
            v19 = p46;
        } else {
            v19 = 0;
        }
        String v20;
        if ((131072 & p56) == 0) {
            v20 = p47;
        } else {
            v20 = 0;
        }
        String v21;
        if ((262144 & p56) == 0) {
            v21 = p48;
        } else {
            v21 = 0;
        }
        int v22;
        if ((524288 & p56) == 0) {
            v22 = p49;
        } else {
            v22 = 0;
        }
        boolean v23;
        if ((1048576 & p56) == 0) {
            v23 = p50;
        } else {
            v23 = 0;
        }
        java.util.List v24;
        if ((2097152 & p56) == 0) {
            v24 = p51;
        } else {
            v24 = kotlin.collections.E.listOf(new String[] {"blurred", "occluded"}));
        }
        String v25;
        if ((4194304 & p56) == 0) {
            v25 = p52;
        } else {
            v25 = "";
        }
        String v26;
        if ((8388608 & p56) == 0) {
            v26 = p53;
        } else {
            v26 = "";
        }
        String v27;
        if ((16777216 & p56) == 0) {
            v27 = p54;
        } else {
            v27 = 0;
        }
        String v9;
        String v3_0;
        String v4_0;
        vn.ai.trueid.sdk.models.FaceAuthenConfig v2_0;
        String v7;
        String v5;
        String v10;
        boolean v28;
        String v8;
        String v6;
        String v11;
        if ((p56 & 33554432) == 0) {
            v28 = p55;
            v2_0 = this;
            v3_0 = p30;
            v4_0 = p31;
            v5 = p32;
            v6 = p33;
            v7 = p34;
            v8 = p35;
            v9 = p36;
            v10 = p37;
            v11 = p38;
        } else {
            v28 = 1;
            v3_0 = p30;
            v4_0 = p31;
            v5 = p32;
            v6 = p33;
            v7 = p34;
            v8 = p35;
            v9 = p36;
            v10 = p37;
            v11 = p38;
            v2_0 = this;
        }
        v2_0(v3_0, v4_0, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceAuthenConfig copy$default(vn.ai.trueid.sdk.models.FaceAuthenConfig p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, int p27, boolean p28, boolean p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, int p37, boolean p38, java.util.List p39, String p40, String p41, String p42, boolean p43, int p44, Object p45)
    {
        String v2_1;
        if ((p44 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.domain;
        }
        String v3_1;
        if ((p44 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.authenEndPoint;
        }
        String v4_1;
        if ((p44 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.createRequestEndPoint;
        }
        String v5_1;
        if ((p44 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.accessTokenEndPoint;
        }
        String v6_1;
        if ((p44 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.appId;
        }
        String v7_1;
        if ((p44 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.appSecret;
        }
        String v8_1;
        if ((p44 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.sdkKey;
        }
        String v9_1;
        if ((p44 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.signVersion;
        }
        String v10_1;
        if ((p44 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.configHeader;
        }
        int v11_1;
        if ((p44 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.maxRetry;
        }
        boolean v12_1;
        if ((p44 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.isShowCloseButton;
        }
        boolean v13_1;
        if ((p44 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.trueidWatermark;
        }
        String v14_1;
        if ((p44 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.primaryColor;
        }
        String v15_1;
        if ((p44 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.secondaryColor;
        }
        String v2_3;
        if ((p44 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.errorColor;
        }
        boolean v1_6;
        if ((p44 & 32768) == 0) {
            v1_6 = p33;
        } else {
            v1_6 = p17.textColor;
        }
        boolean v1_7;
        if ((p44 & 65536) == 0) {
            v1_7 = p34;
        } else {
            v1_7 = p17.backgroundColor;
        }
        boolean v1_8;
        if ((p44 & 131072) == 0) {
            v1_8 = p35;
        } else {
            v1_8 = p17.closeColor;
        }
        boolean v1_9;
        if ((p44 & 262144) == 0) {
            v1_9 = p36;
        } else {
            v1_9 = p17.textButtonColor;
        }
        boolean v1_10;
        if ((p44 & 524288) == 0) {
            v1_10 = p37;
        } else {
            v1_10 = p17.livenessType;
        }
        boolean v1_11;
        if ((p44 & 1048576) == 0) {
            v1_11 = p38;
        } else {
            v1_11 = p17.isAutoProcess;
        }
        boolean v1_1;
        if ((p44 & 2097152) == 0) {
            v1_1 = p39;
        } else {
            v1_1 = p17.errorDetails;
        }
        boolean v1_2;
        if ((p44 & 4194304) == 0) {
            v1_2 = p40;
        } else {
            v1_2 = p17.ovalColor;
        }
        boolean v1_3;
        if ((p44 & 8388608) == 0) {
            v1_3 = p41;
        } else {
            v1_3 = p17.animationOvalColor;
        }
        boolean v1_4;
        if ((p44 & 16777216) == 0) {
            v1_4 = p42;
        } else {
            v1_4 = p17.fontName;
        }
        String v44_1;
        boolean v43_1;
        if ((p44 & 33554432) == 0) {
            v44_1 = p43;
            v43_1 = v1_4;
        } else {
            v43_1 = v1_4;
            v44_1 = p17.isShowLivenessSuccess;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_6, v1_7, v1_8, v1_9, v1_10, v1_11, v1_1, v1_2, v1_3, v43_1, v44_1);
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
        return this.authenEndPoint;
    }

    public final int component20()
    {
        return this.livenessType;
    }

    public final boolean component21()
    {
        return this.isAutoProcess;
    }

    public final java.util.List component22()
    {
        return this.errorDetails;
    }

    public final String component23()
    {
        return this.ovalColor;
    }

    public final String component24()
    {
        return this.animationOvalColor;
    }

    public final String component25()
    {
        return this.fontName;
    }

    public final boolean component26()
    {
        return this.isShowLivenessSuccess;
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

    public final vn.ai.trueid.sdk.models.FaceAuthenConfig copy(String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, int p37, boolean p38, boolean p39, String p40, String p41, String p42, String p43, String p44, String p45, String p46, int p47, boolean p48, java.util.List p49, String p50, String p51, String p52, boolean p53)
    {
        return new vn.ai.trueid.sdk.models.FaceAuthenConfig(p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50, p51, p52, p53);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.FaceAuthenConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.domain, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).domain)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.authenEndPoint, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).authenEndPoint)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.createRequestEndPoint, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).createRequestEndPoint)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.accessTokenEndPoint, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).accessTokenEndPoint)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.appId, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).appId)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.appSecret, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).appSecret)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.sdkKey, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).sdkKey)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.signVersion, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).signVersion)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.configHeader, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).configHeader)) {
                                                    if (this.maxRetry == ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).maxRetry) {
                                                        if (this.isShowCloseButton == ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).isShowCloseButton) {
                                                            if (this.trueidWatermark == ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).trueidWatermark) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.primaryColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).primaryColor)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.secondaryColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).secondaryColor)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).errorColor)) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.textColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).textColor)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.backgroundColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).backgroundColor)) {
                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.closeColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).closeColor)) {
                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.textButtonColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).textButtonColor)) {
                                                                                            if (this.livenessType == ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).livenessType) {
                                                                                                if (this.isAutoProcess == ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).isAutoProcess) {
                                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorDetails, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).errorDetails)) {
                                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.ovalColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).ovalColor)) {
                                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.animationOvalColor, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).animationOvalColor)) {
                                                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.fontName, ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).fontName)) {
                                                                                                                    if (this.isShowLivenessSuccess == ((vn.ai.trueid.sdk.models.FaceAuthenConfig) p5).isShowLivenessSuccess) {
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

    public final String getAuthenEndPoint()
    {
        return this.authenEndPoint;
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

    public final String getErrorColor()
    {
        return this.errorColor;
    }

    public final java.util.List getErrorDetails()
    {
        return this.errorDetails;
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
        int vtmp10 = Kb.e.v(this.maxRetry, e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.domain.hashCode() * 31), 31, this.authenEndPoint), 31, this.createRequestEndPoint), 31, this.accessTokenEndPoint), 31, this.appId), 31, this.appSecret), 31, this.sdkKey), 31, this.signVersion), 31, this.configHeader), 31);
        String v2_11 = this.isShowCloseButton;
        boolean v3 = 1;
        if (v2_11 != null) {
            v2_11 = 1;
        }
        String v2_12 = this.trueidWatermark;
        if (v2_12 != null) {
            v2_12 = 1;
        }
        String v2_14;
        int v0_21 = ((((vtmp10 + v2_11) * 31) + v2_12) * 31);
        String v2_13 = this.primaryColor;
        int v4 = 0;
        if (v2_13 != null) {
            v2_14 = v2_13.hashCode();
        } else {
            v2_14 = 0;
        }
        String v2_17;
        int v0_23 = ((v0_21 + v2_14) * 31);
        String v2_16 = this.secondaryColor;
        if (v2_16 != null) {
            v2_17 = v2_16.hashCode();
        } else {
            v2_17 = 0;
        }
        String v2_19;
        int v0_25 = ((v0_23 + v2_17) * 31);
        String v2_18 = this.errorColor;
        if (v2_18 != null) {
            v2_19 = v2_18.hashCode();
        } else {
            v2_19 = 0;
        }
        String v2_21;
        int v0_27 = ((v0_25 + v2_19) * 31);
        String v2_20 = this.textColor;
        if (v2_20 != null) {
            v2_21 = v2_20.hashCode();
        } else {
            v2_21 = 0;
        }
        String v2_23;
        int v0_30 = ((v0_27 + v2_21) * 31);
        String v2_22 = this.backgroundColor;
        if (v2_22 != null) {
            v2_23 = v2_22.hashCode();
        } else {
            v2_23 = 0;
        }
        String v2_26;
        int v0_32 = ((v0_30 + v2_23) * 31);
        String v2_24 = this.closeColor;
        if (v2_24 != null) {
            v2_26 = v2_24.hashCode();
        } else {
            v2_26 = 0;
        }
        String v2_28;
        int v0_34 = ((v0_32 + v2_26) * 31);
        String v2_27 = this.textButtonColor;
        if (v2_27 != null) {
            v2_28 = v2_27.hashCode();
        } else {
            v2_28 = 0;
        }
        int vtmp18 = Kb.e.v(this.livenessType, ((v0_34 + v2_28) * 31), 31);
        String v2_30 = this.isAutoProcess;
        if (v2_30 != null) {
            v2_30 = 1;
        }
        int v0_5 = e5.i.c(e5.i.c(Kb.e.h(((vtmp18 + v2_30) * 31), 31, this.errorDetails), 31, this.ovalColor), 31, this.animationOvalColor);
        String v2_4 = this.fontName;
        if (v2_4 != null) {
            v4 = v2_4.hashCode();
        }
        boolean v1_0 = this.isShowLivenessSuccess;
        if (!v1_0) {
            v3 = v1_0;
        }
        return (((v0_5 + v4) * 31) + v3);
    }

    public final boolean isAutoProcess()
    {
        return this.isAutoProcess;
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

    public final void setErrorDetails(java.util.List p1)
    {
        this.errorDetails = p1;
        return;
    }

    public final void setLivenessType(int p1)
    {
        this.livenessType = p1;
        return;
    }

    public final java.util.Map toMap()
    {
        String v3_0 = kotlin.TuplesKt.to("domain", this.domain);
        kotlin.TuplesKt.to("authenEndPoint", this.authenEndPoint);
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
        String v4_0 = this.accessTokenEndPoint;
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
        java.util.List v23 = this.errorDetails;
        String v24 = this.ovalColor;
        String v25 = this.animationOvalColor;
        String v26 = this.fontName;
        boolean v27 = this.isShowLivenessSuccess;
        String v28 = this.primaryColor;
        String v0_3 = androidx.camera.camera2.internal.G.i("FaceAuthenConfig(domain=", this.domain, ", authenEndPoint=", this.authenEndPoint, ", createRequestEndPoint=");
        q9.H.p(v0_3, v3_0, ", accessTokenEndPoint=", v4_0, ", appId=");
        q9.H.p(v0_3, v5, ", appSecret=", v6, ", sdkKey=");
        q9.H.p(v0_3, v7, ", signVersion=", v8, ", configHeader=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(v10, v9, ", maxRetry=", ", isShowCloseButton=", v0_3);
        q9.H.q(v0_3, v11, ", trueidWatermark=", v12, ", primaryColor=");
        q9.H.p(v0_3, v28, ", secondaryColor=", v14, ", errorColor=");
        q9.H.p(v0_3, v16, ", textColor=", v17, ", backgroundColor=");
        q9.H.p(v0_3, v18, ", closeColor=", v19, ", textButtonColor=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(v21, v20, ", livenessType=", ", isAutoProcess=", v0_3);
        v0_3.append(v22);
        v0_3.append(", errorDetails=");
        v0_3.append(v23);
        v0_3.append(", ovalColor=");
        q9.H.p(v0_3, v24, ", animationOvalColor=", v25, ", fontName=");
        v0_3.append(v26);
        v0_3.append(", isShowLivenessSuccess=");
        v0_3.append(v27);
        v0_3.append(")");
        return v0_3.toString();
    }
}

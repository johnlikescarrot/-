package vn.ai.trueid.sdk.models;
public final class FaceLivenessConfig {
    private final String accessTokenEndPoint;
    private final String animationOvalColor;
    private final String appId;
    private final String appSecret;
    private final String backgroundColor;
    private final String closeColor;
    private final String configHeader;
    private final String createRequestEndPoint;
    private final String domain;
    private final String errorColor;
    private java.util.List errorDetails;
    private final String fontName;
    private final boolean isAutoProcess;
    private final boolean isRecordFace;
    private final boolean isShowCloseButton;
    private final boolean isShowLivenessSuccess;
    private final String livenessEndPoint;
    private int livenessType;
    private final int maxRetry;
    private final String ovalColor;
    private final String primaryColor;
    private final int recordBitRate;
    private final int recordFrameRate;
    private final int recordMaxDimension;
    private final int recordStartMode;
    private final String sdkKey;
    private final String secondaryColor;
    private final String signVersion;
    private final String textButtonColor;
    private final String textColor;
    private final boolean trueidWatermark;

    public FaceLivenessConfig()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 511, 0);
        return;
    }

    public FaceLivenessConfig(String p13)
    {
        this(p13, 0, 0, 0, 0, 0, 0, 0, 0, 510, 0);
        return;
    }

    public FaceLivenessConfig(String p13, String p14)
    {
        this(p13, p14, 0, 0, 0, 0, 0, 0, 0, 508, 0);
        return;
    }

    public FaceLivenessConfig(String p13, String p14, String p15)
    {
        this(p13, p14, p15, 0, 0, 0, 0, 0, 0, 504, 0);
        return;
    }

    public FaceLivenessConfig(String p13, String p14, String p15, String p16)
    {
        this(p13, p14, p15, p16, 0, 0, 0, 0, 0, 496, 0);
        return;
    }

    public FaceLivenessConfig(String p13, String p14, String p15, String p16, String p17)
    {
        this(p13, p14, p15, p16, p17, 0, 0, 0, 0, 480, 0);
        return;
    }

    public FaceLivenessConfig(String p13, String p14, String p15, String p16, String p17, String p18)
    {
        this(p13, p14, p15, p16, p17, p18, 0, 0, 0, 448, 0);
        return;
    }

    public FaceLivenessConfig(String p13, String p14, String p15, String p16, String p17, String p18, String p19)
    {
        this(p13, p14, p15, p16, p17, p18, p19, 0, 0, 384, 0);
        return;
    }

    public FaceLivenessConfig(String p13, String p14, String p15, String p16, String p17, String p18, String p19, String p20)
    {
        this(p13, p14, p15, p16, p17, p18, p19, p20, 0, 256, 0);
        return;
    }

    public FaceLivenessConfig(String p35, String p36, String p37, String p38, String p39, String p40, String p41, String p42, String p43)
    {
        this(p35, p36, p38, p37, p41, p39, p40, p42, p43, 3, 1, 0, "#26B840", "#42526E", "#FF6234", "#253858", "#FFFFFF", "#000000", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2146699264, 0);
        return;
    }

    public synthetic FaceLivenessConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, int p11, kotlin.jvm.internal.DefaultConstructorMarker p12)
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

    public FaceLivenessConfig(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, int p10, boolean p11, boolean p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, int p20, java.util.List p21, boolean p22, String p23, String p24, boolean p25, int p26, int p27, int p28, int p29, String p30, boolean p31)
    {
        this.domain = p1;
        this.livenessEndPoint = p2;
        this.accessTokenEndPoint = p3;
        this.createRequestEndPoint = p4;
        this.configHeader = p5;
        this.appId = p6;
        this.appSecret = p7;
        this.sdkKey = p8;
        this.signVersion = p9;
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
        this.errorDetails = p21;
        this.isAutoProcess = p22;
        this.ovalColor = p23;
        this.animationOvalColor = p24;
        this.isRecordFace = p25;
        this.recordMaxDimension = p26;
        this.recordBitRate = p27;
        this.recordFrameRate = p28;
        this.recordStartMode = p29;
        this.fontName = p30;
        this.isShowLivenessSuccess = p31;
        return;
    }

    public synthetic FaceLivenessConfig(String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, int p39, boolean p40, boolean p41, String p42, String p43, String p44, String p45, String p46, String p47, String p48, int p49, java.util.List p50, boolean p51, String p52, String p53, boolean p54, int p55, int p56, int p57, int p58, String p59, boolean p60, int p61, kotlin.jvm.internal.DefaultConstructorMarker p62)
    {
        boolean v1_4;
        String v2 = "";
        if ((p61 & 1) == 0) {
            v1_4 = p30;
        } else {
            v1_4 = "";
        }
        String v3_1;
        if ((p61 & 2) == 0) {
            v3_1 = p31;
        } else {
            v3_1 = "";
        }
        String v4_1;
        if ((p61 & 4) == 0) {
            v4_1 = p32;
        } else {
            v4_1 = 0;
        }
        String v6_1;
        if ((p61 & 8) == 0) {
            v6_1 = p33;
        } else {
            v6_1 = 0;
        }
        String v7_1;
        if ((p61 & 16) == 0) {
            v7_1 = p34;
        } else {
            v7_1 = "";
        }
        String v8_1;
        if ((p61 & 32) == 0) {
            v8_1 = p35;
        } else {
            v8_1 = "";
        }
        String v9_1;
        if ((p61 & 64) == 0) {
            v9_1 = p36;
        } else {
            v9_1 = "";
        }
        String v10_1;
        if ((p61 & 128) == 0) {
            v10_1 = p37;
        } else {
            v10_1 = "";
        }
        String v11_1;
        if ((p61 & 256) == 0) {
            v11_1 = p38;
        } else {
            v11_1 = "1";
        }
        int v12_1;
        if ((p61 & 512) == 0) {
            v12_1 = p39;
        } else {
            v12_1 = 3;
        }
        boolean v13_1;
        if ((p61 & 1024) == 0) {
            v13_1 = p40;
        } else {
            v13_1 = 1;
        }
        boolean v15_1;
        int v16 = 0;
        if ((p61 & 2048) == 0) {
            v15_1 = p41;
        } else {
            v15_1 = 0;
        }
        String v5_1;
        if ((p61 & 4096) == 0) {
            v5_1 = p42;
        } else {
            v5_1 = "#26B840";
        }
        String v14_1;
        if ((p61 & 8192) == 0) {
            v14_1 = p43;
        } else {
            v14_1 = "#42526E";
        }
        boolean v1_6;
        p62 = v1_4;
        if ((p61 & 16384) == 0) {
            v1_6 = p44;
        } else {
            v1_6 = "#FF6234";
        }
        String v17_2;
        if ((p61 & 32768) == 0) {
            v17_2 = p45;
        } else {
            v17_2 = "#253858";
        }
        String v18_2;
        String v19 = "#FFFFFF";
        if ((p61 & 65536) == 0) {
            v18_2 = p46;
        } else {
            v18_2 = "#FFFFFF";
        }
        String v20_2;
        if ((p61 & 131072) == 0) {
            v20_2 = p47;
        } else {
            v20_2 = "#000000";
        }
        if ((p61 & 262144) == 0) {
            v19 = p48;
        }
        int v21_4;
        if ((p61 & 524288) == 0) {
            v21_4 = p49;
        } else {
            v21_4 = 0;
        }
        java.util.List v0_1;
        String v32_2;
        if ((p61 & 1048576) == 0) {
            v32_2 = v1_6;
            v0_1 = p50;
        } else {
            v32_2 = v1_6;
            v0_1 = kotlin.collections.E.listOf(new String[] {"blurred", "occluded"}));
        }
        boolean v1_3;
        if ((p61 & 2097152) == 0) {
            v1_3 = p51;
        } else {
            v1_3 = 0;
        }
        String v22_2;
        if ((p61 & 4194304) == 0) {
            v22_2 = p52;
        } else {
            v22_2 = "";
        }
        if ((p61 & 8388608) == 0) {
            v2 = p53;
        }
        boolean v23_4;
        if ((p61 & 16777216) == 0) {
            v23_4 = p54;
        } else {
            v23_4 = 0;
        }
        int v24_2;
        if ((p61 & 33554432) == 0) {
            v24_2 = p55;
        } else {
            v24_2 = 720;
        }
        int v25_2;
        if ((p61 & 67108864) == 0) {
            v25_2 = p56;
        } else {
            v25_2 = 1200000;
        }
        int v26_2;
        if ((p61 & 134217728) == 0) {
            v26_2 = p57;
        } else {
            v26_2 = 15;
        }
        if ((p61 & 268435456) == 0) {
            v16 = p58;
        }
        String v27_4;
        if ((p61 & 536870912) == 0) {
            v27_4 = p59;
        } else {
            v27_4 = 0;
        }
        boolean v61_1;
        if ((p61 & 1073741824) == 0) {
            v61_1 = p60;
        } else {
            v61_1 = 1;
        }
        this(p62, v3_1, v4_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v15_1, v5_1, v14_1, v32_2, v17_2, v18_2, v20_2, v19, v21_4, v0_1, v1_3, v22_2, v2, v23_4, v24_2, v25_2, v26_2, v16, v27_4, v61_1);
        return;
    }

    public final org.json.JSONObject configHeaderJson()
    {
        org.json.JSONObject v0_2;
        if (!kotlin.text.StringsKt.isBlank(this.configHeader)) {
            v0_2 = new org.json.JSONObject(this.configHeader);
        } else {
            v0_2 = new org.json.JSONObject();
        }
        org.json.JSONObject v1_1 = new org.json.JSONObject();
        if ((v0_2 instanceof ng.K)) {
            v0_2 = v1_1;
        }
        return ((org.json.JSONObject) v0_2);
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

    public final String getLivenessEndPoint()
    {
        return this.livenessEndPoint;
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

    public final int getRecordBitRate()
    {
        return this.recordBitRate;
    }

    public final int getRecordFrameRate()
    {
        return this.recordFrameRate;
    }

    public final int getRecordMaxDimension()
    {
        return this.recordMaxDimension;
    }

    public final int getRecordStartMode()
    {
        return this.recordStartMode;
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

    public final boolean isAutoProcess()
    {
        return this.isAutoProcess;
    }

    public final boolean isConfigHeaderJson()
    {
        Boolean v1_0 = 0;
        if (!kotlin.text.StringsKt.isBlank(this.configHeader)) {
            try {
                boolean v0_3;
                if (new org.json.JSONObject(this.configHeader).length() < 0) {
                    v0_3 = Boolean.valueOf(v1_0);
                } else {
                    v1_0 = 1;
                }
            } catch (boolean v0_2) {
                v0_3 = kotlin.ResultKt.createFailure(v0_2);
            }
            if ((v0_3 instanceof ng.K)) {
                v0_3 = Boolean.FALSE;
            }
            return ((Boolean) v0_3).booleanValue();
        } else {
            return 0;
        }
    }

    public final boolean isRecordFace()
    {
        return this.isRecordFace;
    }

    public final boolean isShowCloseButton()
    {
        return this.isShowCloseButton;
    }

    public final boolean isShowLivenessSuccess()
    {
        return this.isShowLivenessSuccess;
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
        kotlin.Pair v3 = kotlin.TuplesKt.to("domain", this.domain);
        kotlin.TuplesKt.to("livenessEndPoint", this.livenessEndPoint);
        kotlin.TuplesKt.to("accessTokenEndPoint", this.accessTokenEndPoint);
        kotlin.TuplesKt.to("createRequestEndPoint", this.createRequestEndPoint);
        kotlin.TuplesKt.to("configHeader", this.configHeader);
        kotlin.TuplesKt.to("appId", this.appId);
        kotlin.TuplesKt.to("appSecret", this.appSecret);
        kotlin.TuplesKt.to("signVersion", this.signVersion);
        kotlin.TuplesKt.to("maxRetry", Integer.valueOf(this.maxRetry));
        kotlin.TuplesKt.to("isShowCloseButton", Boolean.valueOf(this.isShowCloseButton));
        kotlin.TuplesKt.to("livenessType", Integer.valueOf(this.livenessType));
        kotlin.TuplesKt.to("primaryColor", this.primaryColor);
        kotlin.TuplesKt.to("secondaryColor", this.secondaryColor);
        kotlin.TuplesKt.to("errorColor", this.errorColor);
        kotlin.TuplesKt.to("textColor", this.textColor);
        kotlin.TuplesKt.to("backgroundColor", this.backgroundColor);
        kotlin.TuplesKt.to("closeColor", this.closeColor);
        kotlin.TuplesKt.to("textButtonColor", this.textButtonColor);
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v3, kotlin.TuplesKt.to("errorDetails", this.errorDetails)}));
    }
}

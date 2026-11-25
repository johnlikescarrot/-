package vn.ai.trueid.sdk.navigation;
public final class TrueIDLivenessCaptureConfig {
    private final String animationOvalColor;
    private final String appId;
    private final String backgroundColor;
    private final String closeColor;
    private final String configHeader;
    private final String errorColor;
    private final java.util.List errorDetails;
    private final boolean isAutoProcess;
    private final boolean isRecordFace;
    private final boolean isShowCloseButton;
    private final boolean isShowLivenessSuccess;
    private final int livenessType;
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

    public TrueIDLivenessCaptureConfig(String p1, String p2, String p3, String p4, int p5, int p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14, String p15, boolean p16, boolean p17, boolean p18, boolean p19, java.util.List p20, boolean p21, int p22, int p23, int p24, int p25)
    {
        this.appId = p1;
        this.configHeader = p2;
        this.sdkKey = p3;
        this.signVersion = p4;
        this.livenessType = p5;
        this.maxRetry = p6;
        this.primaryColor = p7;
        this.secondaryColor = p8;
        this.errorColor = p9;
        this.textColor = p10;
        this.backgroundColor = p11;
        this.closeColor = p12;
        this.textButtonColor = p13;
        this.ovalColor = p14;
        this.animationOvalColor = p15;
        this.isShowCloseButton = p16;
        this.trueidWatermark = p17;
        this.isAutoProcess = p18;
        this.isShowLivenessSuccess = p19;
        this.errorDetails = p20;
        this.isRecordFace = p21;
        this.recordMaxDimension = p22;
        this.recordBitRate = p23;
        this.recordFrameRate = p24;
        this.recordStartMode = p25;
        return;
    }

    public synthetic TrueIDLivenessCaptureConfig(String p30, String p31, String p32, String p33, int p34, int p35, String p36, String p37, String p38, String p39, String p40, String p41, String p42, String p43, String p44, boolean p45, boolean p46, boolean p47, boolean p48, java.util.List p49, boolean p50, int p51, int p52, int p53, int p54, int p55, kotlin.jvm.internal.DefaultConstructorMarker p56)
    {
        String v6;
        if ((p55 & 4) == 0) {
            v6 = p32;
        } else {
            v6 = "";
        }
        String v7;
        if ((p55 & 8) == 0) {
            v7 = p33;
        } else {
            v7 = "2";
        }
        int v8;
        if ((p55 & 16) == 0) {
            v8 = p34;
        } else {
            v8 = 0;
        }
        int v9;
        if ((p55 & 32) == 0) {
            v9 = p35;
        } else {
            v9 = 3;
        }
        String v10;
        if ((p55 & 64) == 0) {
            v10 = p36;
        } else {
            v10 = "#26B840";
        }
        String v11;
        if ((p55 & 128) == 0) {
            v11 = p37;
        } else {
            v11 = "#FFFFFF";
        }
        String v12;
        if ((p55 & 256) == 0) {
            v12 = p38;
        } else {
            v12 = "#FF3B30";
        }
        String v13;
        if ((p55 & 512) == 0) {
            v13 = p39;
        } else {
            v13 = "#000000";
        }
        String v14;
        if ((p55 & 1024) == 0) {
            v14 = p40;
        } else {
            v14 = "#FFFFFF";
        }
        String v15;
        if ((p55 & 2048) == 0) {
            v15 = p41;
        } else {
            v15 = "#000000";
        }
        String v16;
        if ((p55 & 4096) == 0) {
            v16 = p42;
        } else {
            v16 = "#FFFFFF";
        }
        String v17;
        if ((p55 & 8192) == 0) {
            v17 = p43;
        } else {
            v17 = "";
        }
        String v18;
        if ((p55 & 16384) == 0) {
            v18 = p44;
        } else {
            v18 = "";
        }
        boolean v19;
        if ((32768 & p55) == 0) {
            v19 = p45;
        } else {
            v19 = 1;
        }
        boolean v20;
        if ((65536 & p55) == 0) {
            v20 = p46;
        } else {
            v20 = 0;
        }
        boolean v21;
        if ((131072 & p55) == 0) {
            v21 = p47;
        } else {
            v21 = 0;
        }
        boolean v22;
        if ((262144 & p55) == 0) {
            v22 = p48;
        } else {
            v22 = 1;
        }
        java.util.List v23;
        if ((524288 & p55) == 0) {
            v23 = p49;
        } else {
            v23 = kotlin.collections.E.listOf(new String[] {"blurred", "occluded"}));
        }
        boolean v24;
        if ((1048576 & p55) == 0) {
            v24 = p50;
        } else {
            v24 = 0;
        }
        int v25;
        if ((2097152 & p55) == 0) {
            v25 = p51;
        } else {
            v25 = 720;
        }
        int v26;
        if ((4194304 & p55) == 0) {
            v26 = p52;
        } else {
            v26 = 1200000;
        }
        int v27;
        if ((8388608 & p55) == 0) {
            v27 = p53;
        } else {
            v27 = 15;
        }
        int v28;
        String v4_0;
        vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig v3_1;
        String v5_0;
        if ((p55 & 16777216) == 0) {
            v28 = p54;
            v3_1 = this;
            v4_0 = p30;
            v5_0 = p31;
        } else {
            v28 = 0;
            v4_0 = p30;
            v5_0 = p31;
            v3_1 = this;
        }
        v3_1(v4_0, v5_0, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig copy$default(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig p17, String p18, String p19, String p20, String p21, int p22, int p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, boolean p33, boolean p34, boolean p35, boolean p36, java.util.List p37, boolean p38, int p39, int p40, int p41, int p42, int p43, Object p44)
    {
        String v2_1;
        if ((p43 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.appId;
        }
        String v3_1;
        if ((p43 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.configHeader;
        }
        String v4_1;
        if ((p43 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.sdkKey;
        }
        String v5_1;
        if ((p43 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.signVersion;
        }
        int v6_1;
        if ((p43 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.livenessType;
        }
        int v7_1;
        if ((p43 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.maxRetry;
        }
        String v8_1;
        if ((p43 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.primaryColor;
        }
        String v9_1;
        if ((p43 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.secondaryColor;
        }
        String v10_1;
        if ((p43 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.errorColor;
        }
        String v11_1;
        if ((p43 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.textColor;
        }
        String v12_1;
        if ((p43 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.backgroundColor;
        }
        String v13_1;
        if ((p43 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.closeColor;
        }
        String v14_1;
        if ((p43 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.textButtonColor;
        }
        String v15_1;
        if ((p43 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.ovalColor;
        }
        String v2_3;
        if ((p43 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.animationOvalColor;
        }
        int v1_5;
        if ((p43 & 32768) == 0) {
            v1_5 = p33;
        } else {
            v1_5 = p17.isShowCloseButton;
        }
        int v1_6;
        if ((p43 & 65536) == 0) {
            v1_6 = p34;
        } else {
            v1_6 = p17.trueidWatermark;
        }
        int v1_7;
        if ((p43 & 131072) == 0) {
            v1_7 = p35;
        } else {
            v1_7 = p17.isAutoProcess;
        }
        int v1_8;
        if ((p43 & 262144) == 0) {
            v1_8 = p36;
        } else {
            v1_8 = p17.isShowLivenessSuccess;
        }
        int v1_9;
        if ((p43 & 524288) == 0) {
            v1_9 = p37;
        } else {
            v1_9 = p17.errorDetails;
        }
        int v1_10;
        if ((p43 & 1048576) == 0) {
            v1_10 = p38;
        } else {
            v1_10 = p17.isRecordFace;
        }
        int v1_1;
        if ((p43 & 2097152) == 0) {
            v1_1 = p39;
        } else {
            v1_1 = p17.recordMaxDimension;
        }
        int v1_2;
        if ((p43 & 4194304) == 0) {
            v1_2 = p40;
        } else {
            v1_2 = p17.recordBitRate;
        }
        int v1_3;
        if ((p43 & 8388608) == 0) {
            v1_3 = p41;
        } else {
            v1_3 = p17.recordFrameRate;
        }
        int v42_1;
        int v43_1;
        if ((p43 & 16777216) == 0) {
            v43_1 = p42;
            v42_1 = v1_3;
        } else {
            v42_1 = v1_3;
            v43_1 = p17.recordStartMode;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_5, v1_6, v1_7, v1_8, v1_9, v1_10, v1_1, v1_2, v42_1, v43_1);
    }

    public final String component1()
    {
        return this.appId;
    }

    public final String component10()
    {
        return this.textColor;
    }

    public final String component11()
    {
        return this.backgroundColor;
    }

    public final String component12()
    {
        return this.closeColor;
    }

    public final String component13()
    {
        return this.textButtonColor;
    }

    public final String component14()
    {
        return this.ovalColor;
    }

    public final String component15()
    {
        return this.animationOvalColor;
    }

    public final boolean component16()
    {
        return this.isShowCloseButton;
    }

    public final boolean component17()
    {
        return this.trueidWatermark;
    }

    public final boolean component18()
    {
        return this.isAutoProcess;
    }

    public final boolean component19()
    {
        return this.isShowLivenessSuccess;
    }

    public final String component2()
    {
        return this.configHeader;
    }

    public final java.util.List component20()
    {
        return this.errorDetails;
    }

    public final boolean component21()
    {
        return this.isRecordFace;
    }

    public final int component22()
    {
        return this.recordMaxDimension;
    }

    public final int component23()
    {
        return this.recordBitRate;
    }

    public final int component24()
    {
        return this.recordFrameRate;
    }

    public final int component25()
    {
        return this.recordStartMode;
    }

    public final String component3()
    {
        return this.sdkKey;
    }

    public final String component4()
    {
        return this.signVersion;
    }

    public final int component5()
    {
        return this.livenessType;
    }

    public final int component6()
    {
        return this.maxRetry;
    }

    public final String component7()
    {
        return this.primaryColor;
    }

    public final String component8()
    {
        return this.secondaryColor;
    }

    public final String component9()
    {
        return this.errorColor;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig copy(String p27, String p28, String p29, String p30, int p31, int p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39, String p40, String p41, boolean p42, boolean p43, boolean p44, boolean p45, java.util.List p46, boolean p47, int p48, int p49, int p50, int p51)
    {
        return new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig(p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50, p51);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.appId, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).appId)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.configHeader, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).configHeader)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.sdkKey, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).sdkKey)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.signVersion, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).signVersion)) {
                                if (this.livenessType == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).livenessType) {
                                    if (this.maxRetry == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).maxRetry) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.primaryColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).primaryColor)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.secondaryColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).secondaryColor)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).errorColor)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.textColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).textColor)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.backgroundColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).backgroundColor)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.closeColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).closeColor)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.textButtonColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).textButtonColor)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.ovalColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).ovalColor)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.animationOvalColor, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).animationOvalColor)) {
                                                                            if (this.isShowCloseButton == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).isShowCloseButton) {
                                                                                if (this.trueidWatermark == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).trueidWatermark) {
                                                                                    if (this.isAutoProcess == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).isAutoProcess) {
                                                                                        if (this.isShowLivenessSuccess == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).isShowLivenessSuccess) {
                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorDetails, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).errorDetails)) {
                                                                                                if (this.isRecordFace == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).isRecordFace) {
                                                                                                    if (this.recordMaxDimension == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).recordMaxDimension) {
                                                                                                        if (this.recordBitRate == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).recordBitRate) {
                                                                                                            if (this.recordFrameRate == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).recordFrameRate) {
                                                                                                                if (this.recordStartMode == ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureConfig) p5).recordStartMode) {
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
            return 1;
        }
    }

    public final String getAnimationOvalColor()
    {
        return this.animationOvalColor;
    }

    public final String getAppId()
    {
        return this.appId;
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

    public final String getErrorColor()
    {
        return this.errorColor;
    }

    public final java.util.List getErrorDetails()
    {
        return this.errorDetails;
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

    public int hashCode()
    {
        int vtmp15 = e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(Kb.e.v(this.maxRetry, Kb.e.v(this.livenessType, e5.i.c(e5.i.c(e5.i.c((this.appId.hashCode() * 31), 31, this.configHeader), 31, this.sdkKey), 31, this.signVersion), 31), 31), 31, this.primaryColor), 31, this.secondaryColor), 31, this.errorColor), 31, this.textColor), 31, this.backgroundColor), 31, this.closeColor), 31, this.textButtonColor), 31, this.ovalColor), 31, this.animationOvalColor);
        int v2_12 = this.isShowCloseButton;
        boolean v3 = 1;
        if (v2_12 != 0) {
            v2_12 = 1;
        }
        int v2_14 = this.trueidWatermark;
        if (v2_14 != 0) {
            v2_14 = 1;
        }
        int v2_15 = this.isAutoProcess;
        if (v2_15 != 0) {
            v2_15 = 1;
        }
        int v2_16 = this.isShowLivenessSuccess;
        if (v2_16 != 0) {
            v2_16 = 1;
        }
        int vtmp16 = Kb.e.h(((((((((vtmp15 + v2_12) * 31) + v2_14) * 31) + v2_15) * 31) + v2_16) * 31), 31, this.errorDetails);
        int v2_18 = this.isRecordFace;
        if (v2_18 == 0) {
            v3 = v2_18;
        }
        return (Integer.hashCode(this.recordStartMode) + Kb.e.v(this.recordFrameRate, Kb.e.v(this.recordBitRate, Kb.e.v(this.recordMaxDimension, ((vtmp16 + v3) * 31), 31), 31), 31));
    }

    public final boolean isAutoProcess()
    {
        return this.isAutoProcess;
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

    public String toString()
    {
        int v3_0 = this.sdkKey;
        int v4_0 = this.signVersion;
        int v5 = this.livenessType;
        int v6 = this.maxRetry;
        String v7 = this.primaryColor;
        String v8 = this.secondaryColor;
        String v9 = this.errorColor;
        String v10 = this.textColor;
        String v11 = this.backgroundColor;
        String v12 = this.closeColor;
        String v14 = this.ovalColor;
        String v16 = this.animationOvalColor;
        boolean v17 = this.isShowCloseButton;
        boolean v18 = this.trueidWatermark;
        boolean v19 = this.isAutoProcess;
        boolean v20 = this.isShowLivenessSuccess;
        java.util.List v21 = this.errorDetails;
        boolean v22 = this.isRecordFace;
        int v23 = this.recordMaxDimension;
        int v24 = this.recordBitRate;
        int v25 = this.recordFrameRate;
        int v26 = this.recordStartMode;
        String v27 = this.textButtonColor;
        String v0_2 = androidx.camera.camera2.internal.G.i("TrueIDLivenessCaptureConfig(appId=", this.appId, ", configHeader=", this.configHeader, ", sdkKey=");
        q9.H.p(v0_2, v3_0, ", signVersion=", v4_0, ", livenessType=");
        androidx.compose.foundation.layout.G0.w(v0_2, v5, ", maxRetry=", v6, ", primaryColor=");
        q9.H.p(v0_2, v7, ", secondaryColor=", v8, ", errorColor=");
        q9.H.p(v0_2, v9, ", textColor=", v10, ", backgroundColor=");
        q9.H.p(v0_2, v11, ", closeColor=", v12, ", textButtonColor=");
        q9.H.p(v0_2, v27, ", ovalColor=", v14, ", animationOvalColor=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.z(v0_2, v16, ", isShowCloseButton=", v17, ", trueidWatermark=");
        q9.H.q(v0_2, v18, ", isAutoProcess=", v19, ", isShowLivenessSuccess=");
        v0_2.append(v20);
        v0_2.append(", errorDetails=");
        v0_2.append(v21);
        v0_2.append(", isRecordFace=");
        v0_2.append(v22);
        v0_2.append(", recordMaxDimension=");
        v0_2.append(v23);
        v0_2.append(", recordBitRate=");
        androidx.compose.foundation.layout.G0.w(v0_2, v24, ", recordFrameRate=", v25, ", recordStartMode=");
        return A1.a.h(v26, ")", v0_2);
    }
}

package vn.ai.trueid.sdk.face.present.domain.configs;
public final class SDKConfig {
    private final String backgroundColor;
    private final String closeColor;
    private final String errorColor;
    private final String fontName;
    private final boolean isAutoProcess;
    private final boolean isCancelable;
    private final boolean isLockSimulator;
    private final boolean isShowGuideScreen;
    private final boolean isShowLivenessSuccess;
    private final int livenessType;
    private final String ovalColor;
    private final String primaryColor;
    private final String secondaryColor;
    private final String textButtonColor;
    private final String textColor;
    private final int textSize;
    private final int textSizeButton;
    private final int textSizeTitle;

    public SDKConfig()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 262143, 0);
        return;
    }

    public SDKConfig(String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28)
    {
        this(p21, p22, p23, p24, p25, p26, p27, p28, 0, 1, 0, 1, 0, "", 22, 16, 18, 4096, 0);
        return;
    }

    public synthetic SDKConfig(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, int p10, kotlin.jvm.internal.DefaultConstructorMarker p11)
    {
        if ((p10 & 1) != 0) {
            p2 = "#1780DE";
        }
        if ((p10 & 2) != 0) {
            p3 = "#1780DE";
        }
        if ((p10 & 4) != 0) {
            p4 = "#1780DE";
        }
        if ((p10 & 8) != 0) {
            p5 = "#FF0000";
        }
        if ((p10 & 16) != 0) {
            p6 = "#000000";
        }
        if ((p10 & 32) != 0) {
            p7 = "#000000";
        }
        if ((p10 & 64) != 0) {
            p8 = "#000000";
        }
        if ((p10 & 128) != 0) {
            p9 = "#FFFFFF";
        }
        this(p2, p3, p4, p5, p6, p7, p8, p9);
        return;
    }

    public SDKConfig(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, boolean p9, boolean p10, boolean p11, boolean p12, String p13, int p14, int p15, int p16, int p17, boolean p18)
    {
        this.primaryColor = p1;
        this.secondaryColor = p2;
        this.ovalColor = p3;
        this.errorColor = p4;
        this.closeColor = p5;
        this.textColor = p6;
        this.textButtonColor = p7;
        this.backgroundColor = p8;
        this.isAutoProcess = p9;
        this.isCancelable = p10;
        this.isShowGuideScreen = p11;
        this.isLockSimulator = p12;
        this.fontName = p13;
        this.textSizeTitle = p14;
        this.textSize = p15;
        this.textSizeButton = p16;
        this.livenessType = p17;
        this.isShowLivenessSuccess = p18;
        return;
    }

    public synthetic SDKConfig(String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, boolean p28, boolean p29, boolean p30, boolean p31, String p32, int p33, int p34, int p35, int p36, boolean p37, int p38, kotlin.jvm.internal.DefaultConstructorMarker p39)
    {
        String v1_1;
        String v2 = "#1780DE";
        if ((p38 & 1) == 0) {
            v1_1 = p20;
        } else {
            v1_1 = "#1780DE";
        }
        String v3_1;
        if ((p38 & 2) == 0) {
            v3_1 = p21;
        } else {
            v3_1 = "#1780DE";
        }
        if ((p38 & 4) == 0) {
            v2 = p22;
        }
        String v4_2;
        if ((p38 & 8) == 0) {
            v4_2 = p23;
        } else {
            v4_2 = "#FF0000";
        }
        String v5_1;
        String v6 = "#000000";
        if ((p38 & 16) == 0) {
            v5_1 = p24;
        } else {
            v5_1 = "#000000";
        }
        String v7_1;
        if ((p38 & 32) == 0) {
            v7_1 = p25;
        } else {
            v7_1 = "#000000";
        }
        if ((p38 & 64) == 0) {
            v6 = p26;
        }
        String v8_2;
        if ((p38 & 128) == 0) {
            v8_2 = p27;
        } else {
            v8_2 = "#FFFFFF";
        }
        boolean v9_1;
        if ((p38 & 256) == 0) {
            v9_1 = p28;
        } else {
            v9_1 = 0;
        }
        boolean v11_1;
        if ((p38 & 512) == 0) {
            v11_1 = p29;
        } else {
            v11_1 = 1;
        }
        boolean v13_1;
        if ((p38 & 1024) == 0) {
            v13_1 = p30;
        } else {
            v13_1 = 0;
        }
        boolean v14_1;
        if ((p38 & 2048) == 0) {
            v14_1 = p31;
        } else {
            v14_1 = 1;
        }
        String v15_1;
        if ((p38 & 4096) == 0) {
            v15_1 = p32;
        } else {
            v15_1 = "";
        }
        int v10_1;
        if ((p38 & 8192) == 0) {
            v10_1 = p33;
        } else {
            v10_1 = 22;
        }
        int v12_1;
        if ((p38 & 16384) == 0) {
            v12_1 = p34;
        } else {
            v12_1 = 16;
        }
        int v16_2;
        if ((p38 & 32768) == 0) {
            v16_2 = p35;
        } else {
            v16_2 = 18;
        }
        int v17_2;
        if ((p38 & 65536) == 0) {
            v17_2 = p36;
        } else {
            v17_2 = 0;
        }
        boolean v38_1;
        if ((p38 & 131072) == 0) {
            v38_1 = p37;
        } else {
            v38_1 = 1;
        }
        this(v1_1, v3_1, v2, v4_2, v5_1, v7_1, v6, v8_2, v9_1, v11_1, v13_1, v14_1, v15_1, v10_1, v12_1, v16_2, v17_2, v38_1);
        return;
    }

    public SDKConfig(String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, boolean p29, boolean p30, boolean p31, boolean p32, boolean p33)
    {
        this(p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, 0, "", 22, 16, 18, 4096, 0);
        return;
    }

    public synthetic SDKConfig(String p14, String p15, String p16, String p17, String p18, String p19, String p20, String p21, boolean p22, boolean p23, boolean p24, boolean p25, boolean p26, int p27, kotlin.jvm.internal.DefaultConstructorMarker p28)
    {
        String v2 = "#1780DE";
        if ((p27 & 1) != 0) {
            p14 = "#1780DE";
        }
        String v1_2;
        if ((p27 & 2) == 0) {
            v1_2 = p15;
        } else {
            v1_2 = "#1780DE";
        }
        if ((p27 & 4) == 0) {
            v2 = p16;
        }
        String v3_1;
        if ((p27 & 8) == 0) {
            v3_1 = p17;
        } else {
            v3_1 = "#FF0000";
        }
        String v4_1;
        String v5 = "#000000";
        if ((p27 & 16) == 0) {
            v4_1 = p18;
        } else {
            v4_1 = "#000000";
        }
        String v6_1;
        if ((p27 & 32) == 0) {
            v6_1 = p19;
        } else {
            v6_1 = "#000000";
        }
        if ((p27 & 64) == 0) {
            v5 = p20;
        }
        String v7_2;
        if ((p27 & 128) == 0) {
            v7_2 = p21;
        } else {
            v7_2 = "#FFFFFF";
        }
        boolean v8_1;
        boolean v9 = 0;
        if ((p27 & 256) == 0) {
            v8_1 = p22;
        } else {
            v8_1 = 0;
        }
        boolean v10_1;
        if ((p27 & 512) == 0) {
            v10_1 = p23;
        } else {
            v10_1 = 1;
        }
        if ((p27 & 1024) == 0) {
            v9 = p24;
        }
        boolean v12_2;
        if ((p27 & 2048) == 0) {
            v12_2 = p25;
        } else {
            v12_2 = 1;
        }
        boolean v27_1;
        if ((p27 & 4096) == 0) {
            v27_1 = p26;
        } else {
            v27_1 = 1;
        }
        this(p14, v1_2, v2, v3_1, v4_1, v6_1, v5, v7_2, v8_1, v10_1, v9, v12_2, v27_1);
        return;
    }

    public SDKConfig(String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, boolean p28, boolean p29, boolean p30, boolean p31, boolean p32, String p33, int p34, int p35, int p36)
    {
        this(p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p33, p34, p35, p36, 0, p32);
        return;
    }

    public synthetic SDKConfig(String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, boolean p27, boolean p28, boolean p29, boolean p30, boolean p31, String p32, int p33, int p34, int p35, int p36, kotlin.jvm.internal.DefaultConstructorMarker p37)
    {
        String v1_1;
        String v2 = "#1780DE";
        if ((p36 & 1) == 0) {
            v1_1 = p19;
        } else {
            v1_1 = "#1780DE";
        }
        String v3_1;
        if ((p36 & 2) == 0) {
            v3_1 = p20;
        } else {
            v3_1 = "#1780DE";
        }
        if ((p36 & 4) == 0) {
            v2 = p21;
        }
        String v4_2;
        if ((p36 & 8) == 0) {
            v4_2 = p22;
        } else {
            v4_2 = "#FF0000";
        }
        String v5_1;
        String v6 = "#000000";
        if ((p36 & 16) == 0) {
            v5_1 = p23;
        } else {
            v5_1 = "#000000";
        }
        String v7_1;
        if ((p36 & 32) == 0) {
            v7_1 = p24;
        } else {
            v7_1 = "#000000";
        }
        if ((p36 & 64) == 0) {
            v6 = p25;
        }
        String v8_2;
        if ((p36 & 128) == 0) {
            v8_2 = p26;
        } else {
            v8_2 = "#FFFFFF";
        }
        boolean v9_1;
        boolean v10 = 0;
        if ((p36 & 256) == 0) {
            v9_1 = p27;
        } else {
            v9_1 = 0;
        }
        boolean v11_1;
        boolean v12 = 1;
        if ((p36 & 512) == 0) {
            v11_1 = p28;
        } else {
            v11_1 = 1;
        }
        if ((p36 & 1024) == 0) {
            v10 = p29;
        }
        boolean v13_2;
        if ((p36 & 2048) == 0) {
            v13_2 = p30;
        } else {
            v13_2 = 1;
        }
        if ((p36 & 4096) == 0) {
            v12 = p31;
        }
        String v14_2;
        if ((p36 & 8192) == 0) {
            v14_2 = p32;
        } else {
            v14_2 = "";
        }
        int v15_1;
        if ((p36 & 16384) == 0) {
            v15_1 = p33;
        } else {
            v15_1 = 22;
        }
        int v16_2;
        if ((p36 & 32768) == 0) {
            v16_2 = p34;
        } else {
            v16_2 = 16;
        }
        int v36_1;
        if ((p36 & 65536) == 0) {
            v36_1 = p35;
        } else {
            v36_1 = 18;
        }
        this(v1_1, v3_1, v2, v4_2, v5_1, v7_1, v6, v8_2, v9_1, v11_1, v10, v13_2, v12, v14_2, v15_1, v16_2, v36_1);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig copy$default(vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, boolean p26, boolean p27, boolean p28, boolean p29, String p30, int p31, int p32, int p33, int p34, boolean p35, int p36, Object p37)
    {
        int v2_1;
        if ((p36 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.primaryColor;
        }
        String v3_1;
        if ((p36 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.secondaryColor;
        }
        String v4_1;
        if ((p36 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.ovalColor;
        }
        String v5_1;
        if ((p36 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.errorColor;
        }
        String v6_1;
        if ((p36 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.closeColor;
        }
        String v7_1;
        if ((p36 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.textColor;
        }
        String v8_1;
        if ((p36 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.textButtonColor;
        }
        String v9_1;
        if ((p36 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.backgroundColor;
        }
        boolean v10_1;
        if ((p36 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.isAutoProcess;
        }
        boolean v11_1;
        if ((p36 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.isCancelable;
        }
        boolean v12_1;
        if ((p36 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.isShowGuideScreen;
        }
        boolean v13_1;
        if ((p36 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.isLockSimulator;
        }
        String v14_1;
        if ((p36 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.fontName;
        }
        int v15_1;
        if ((p36 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.textSizeTitle;
        }
        int v2_3;
        if ((p36 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.textSize;
        }
        boolean v1_1;
        if ((p36 & 32768) == 0) {
            v1_1 = p33;
        } else {
            v1_1 = p17.textSizeButton;
        }
        boolean v1_2;
        if ((p36 & 65536) == 0) {
            v1_2 = p34;
        } else {
            v1_2 = p17.livenessType;
        }
        boolean v35_1;
        int v36_1;
        if ((p36 & 131072) == 0) {
            v36_1 = p35;
            v35_1 = v1_2;
        } else {
            v35_1 = v1_2;
            v36_1 = p17.isShowLivenessSuccess;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_1, v35_1, v36_1);
    }

    public final String component1()
    {
        return this.primaryColor;
    }

    public final boolean component10()
    {
        return this.isCancelable;
    }

    public final boolean component11()
    {
        return this.isShowGuideScreen;
    }

    public final boolean component12()
    {
        return this.isLockSimulator;
    }

    public final String component13()
    {
        return this.fontName;
    }

    public final int component14()
    {
        return this.textSizeTitle;
    }

    public final int component15()
    {
        return this.textSize;
    }

    public final int component16()
    {
        return this.textSizeButton;
    }

    public final int component17()
    {
        return this.livenessType;
    }

    public final boolean component18()
    {
        return this.isShowLivenessSuccess;
    }

    public final String component2()
    {
        return this.secondaryColor;
    }

    public final String component3()
    {
        return this.ovalColor;
    }

    public final String component4()
    {
        return this.errorColor;
    }

    public final String component5()
    {
        return this.closeColor;
    }

    public final String component6()
    {
        return this.textColor;
    }

    public final String component7()
    {
        return this.textButtonColor;
    }

    public final String component8()
    {
        return this.backgroundColor;
    }

    public final boolean component9()
    {
        return this.isAutoProcess;
    }

    public final vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig copy(String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, boolean p28, boolean p29, boolean p30, boolean p31, String p32, int p33, int p34, int p35, int p36, boolean p37)
    {
        return new vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig(p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.primaryColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).primaryColor)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.secondaryColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).secondaryColor)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.ovalColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).ovalColor)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).errorColor)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.closeColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).closeColor)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.textColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).textColor)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.textButtonColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).textButtonColor)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.backgroundColor, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).backgroundColor)) {
                                                if (this.isAutoProcess == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).isAutoProcess) {
                                                    if (this.isCancelable == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).isCancelable) {
                                                        if (this.isShowGuideScreen == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).isShowGuideScreen) {
                                                            if (this.isLockSimulator == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).isLockSimulator) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.fontName, ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).fontName)) {
                                                                    if (this.textSizeTitle == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).textSizeTitle) {
                                                                        if (this.textSize == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).textSize) {
                                                                            if (this.textSizeButton == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).textSizeButton) {
                                                                                if (this.livenessType == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).livenessType) {
                                                                                    if (this.isShowLivenessSuccess == ((vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig) p5).isShowLivenessSuccess) {
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
            return 1;
        }
    }

    public final String getBackgroundColor()
    {
        return this.backgroundColor;
    }

    public final String getCloseColor()
    {
        return this.closeColor;
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

    public final String getOvalColor()
    {
        return this.ovalColor;
    }

    public final String getPrimaryColor()
    {
        return this.primaryColor;
    }

    public final String getSecondaryColor()
    {
        return this.secondaryColor;
    }

    public final String getTextButtonColor()
    {
        return this.textButtonColor;
    }

    public final String getTextColor()
    {
        return this.textColor;
    }

    public final int getTextSize()
    {
        return this.textSize;
    }

    public final int getTextSizeButton()
    {
        return this.textSizeButton;
    }

    public final int getTextSizeTitle()
    {
        return this.textSizeTitle;
    }

    public int hashCode()
    {
        int vtmp8 = e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.primaryColor.hashCode() * 31), 31, this.secondaryColor), 31, this.ovalColor), 31, this.errorColor), 31, this.closeColor), 31, this.textColor), 31, this.textButtonColor), 31, this.backgroundColor);
        int v2_5 = this.isAutoProcess;
        boolean v3 = 1;
        if (v2_5 != 0) {
            v2_5 = 1;
        }
        int v2_6 = this.isCancelable;
        if (v2_6 != 0) {
            v2_6 = 1;
        }
        int v2_7 = this.isShowGuideScreen;
        if (v2_7 != 0) {
            v2_7 = 1;
        }
        int v2_8 = this.isLockSimulator;
        if (v2_8 != 0) {
            v2_8 = 1;
        }
        int vtmp13 = Kb.e.v(this.livenessType, Kb.e.v(this.textSizeButton, Kb.e.v(this.textSize, Kb.e.v(this.textSizeTitle, e5.i.c(((((((((vtmp8 + v2_5) * 31) + v2_6) * 31) + v2_7) * 31) + v2_8) * 31), 31, this.fontName), 31), 31), 31), 31);
        boolean v1_1 = this.isShowLivenessSuccess;
        if (!v1_1) {
            v3 = v1_1;
        }
        return (vtmp13 + v3);
    }

    public final boolean isAutoProcess()
    {
        return this.isAutoProcess;
    }

    public final boolean isCancelable()
    {
        return this.isCancelable;
    }

    public final boolean isLockSimulator()
    {
        return this.isLockSimulator;
    }

    public final boolean isShowGuideScreen()
    {
        return this.isShowGuideScreen;
    }

    public final boolean isShowLivenessSuccess()
    {
        return this.isShowLivenessSuccess;
    }

    public final org.json.JSONObject toJson()
    {
        return new org.json.JSONObject(this.toMap());
    }

    public final java.util.Map toMap()
    {
        kotlin.Pair v2 = kotlin.TuplesKt.to("primaryColor", this.primaryColor);
        kotlin.TuplesKt.to("secondaryColor", this.secondaryColor);
        kotlin.TuplesKt.to("ovalColor", this.ovalColor);
        kotlin.TuplesKt.to("errorColor", this.errorColor);
        kotlin.TuplesKt.to("closeColor", this.closeColor);
        kotlin.TuplesKt.to("textColor", this.textColor);
        kotlin.TuplesKt.to("textButtonColor", this.textButtonColor);
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v2, kotlin.TuplesKt.to("backgroundColor", this.backgroundColor)}));
    }

    public String toString()
    {
        String v0_1 = new StringBuilder("SDKConfig(primaryColor=");
        v0_1.append(this.primaryColor);
        v0_1.append(", secondaryColor=");
        v0_1.append(this.secondaryColor);
        v0_1.append(", ovalColor=");
        v0_1.append(this.ovalColor);
        v0_1.append(", errorColor=");
        v0_1.append(this.errorColor);
        v0_1.append(", closeColor=");
        v0_1.append(this.closeColor);
        v0_1.append(", textColor=");
        v0_1.append(this.textColor);
        v0_1.append(", textButtonColor=");
        v0_1.append(this.textButtonColor);
        v0_1.append(", backgroundColor=");
        v0_1.append(this.backgroundColor);
        v0_1.append(", isAutoProcess=");
        v0_1.append(this.isAutoProcess);
        v0_1.append(", isCancelable=");
        v0_1.append(this.isCancelable);
        v0_1.append(", isShowGuideScreen=");
        v0_1.append(this.isShowGuideScreen);
        v0_1.append(", isLockSimulator=");
        v0_1.append(this.isLockSimulator);
        v0_1.append(", fontName=");
        v0_1.append(this.fontName);
        v0_1.append(", textSizeTitle=");
        v0_1.append(this.textSizeTitle);
        v0_1.append(", textSize=");
        v0_1.append(this.textSize);
        v0_1.append(", textSizeButton=");
        v0_1.append(this.textSizeButton);
        v0_1.append(", livenessType=");
        v0_1.append(this.livenessType);
        v0_1.append(", isShowLivenessSuccess=");
        return androidx.activity.result.e.r(v0_1, this.isShowLivenessSuccess, 41);
    }
}

package vn.ai.trueid.sdk.models.sotp;
public final class TrueIdSmartOTPUIConfig {
    private final String accentBarColor;
    private final String backgroundColor;
    private final String cancelButtonBorderColor;
    private final String cancelButtonTextColor;
    private final String cardBackgroundColor;
    private final String cardBorderColor;
    private final String confirmButtonColor;
    private final String confirmButtonTextColor;
    private final int cornerRadius;
    private final String fontName;
    private final boolean isShowCloseButton;
    private final String keypadButtonColor;
    private final String keypadButtonTextColor;
    private final String keypadDeleteIconColor;
    private final String labelColor;
    private final String otpCodeColor;
    private final String otpStatusColor;
    private final String pinDotBorderColor;
    private final String pinDotEmptyColor;
    private final String pinDotFilledColor;
    private final String pinErrorColor;
    private final String pinSubtitleColor;
    private final String pinTitleColor;
    private final String timerColor;
    private final String titleColor;
    private final String valueColor;

    public TrueIdSmartOTPUIConfig()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108863, 0);
        return;
    }

    public TrueIdSmartOTPUIConfig(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, String p20, String p21, String p22, String p23, int p24, String p25, boolean p26)
    {
        this.backgroundColor = p1;
        this.cardBackgroundColor = p2;
        this.labelColor = p3;
        this.valueColor = p4;
        this.titleColor = p5;
        this.otpCodeColor = p6;
        this.otpStatusColor = p7;
        this.accentBarColor = p8;
        this.cardBorderColor = p9;
        this.confirmButtonColor = p10;
        this.confirmButtonTextColor = p11;
        this.cancelButtonTextColor = p12;
        this.cancelButtonBorderColor = p13;
        this.timerColor = p14;
        this.pinTitleColor = p15;
        this.pinSubtitleColor = p16;
        this.pinErrorColor = p17;
        this.pinDotFilledColor = p18;
        this.pinDotEmptyColor = p19;
        this.pinDotBorderColor = p20;
        this.keypadButtonColor = p21;
        this.keypadButtonTextColor = p22;
        this.keypadDeleteIconColor = p23;
        this.cornerRadius = p24;
        this.fontName = p25;
        this.isShowCloseButton = p26;
        return;
    }

    public synthetic TrueIdSmartOTPUIConfig(String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39, String p40, String p41, String p42, String p43, String p44, String p45, int p46, String p47, boolean p48, int p49, kotlin.jvm.internal.DefaultConstructorMarker p50)
    {
        String v1_1;
        if ((p49 & 1) == 0) {
            v1_1 = p23;
        } else {
            v1_1 = "#07090E";
        }
        String v2_1;
        String v3 = "#1C2538";
        if ((p49 & 2) == 0) {
            v2_1 = p24;
        } else {
            v2_1 = "#1C2538";
        }
        String v4_1;
        String v5 = "#9EAFB8";
        if ((p49 & 4) == 0) {
            v4_1 = p25;
        } else {
            v4_1 = "#9EAFB8";
        }
        String v6_1;
        String v7 = "#FFFFFF";
        if ((p49 & 8) == 0) {
            v6_1 = p26;
        } else {
            v6_1 = "#FFFFFF";
        }
        String v8_1;
        if ((p49 & 16) == 0) {
            v8_1 = p27;
        } else {
            v8_1 = "#FFFFFF";
        }
        String v9_1;
        String v10 = "#00E676";
        if ((p49 & 32) == 0) {
            v9_1 = p28;
        } else {
            v9_1 = "#00E676";
        }
        String v11_1;
        if ((p49 & 64) == 0) {
            v11_1 = p29;
        } else {
            v11_1 = "#9EAFB8";
        }
        String v12_1;
        if ((p49 & 128) == 0) {
            v12_1 = p30;
        } else {
            v12_1 = "#00C853";
        }
        String v13_1;
        String v14 = "#2E3C56";
        if ((p49 & 256) == 0) {
            v13_1 = p31;
        } else {
            v13_1 = "#2E3C56";
        }
        String v15_1;
        if ((p49 & 512) == 0) {
            v15_1 = p32;
        } else {
            v15_1 = "#00E676";
        }
        String v1_3;
        if ((p49 & 1024) == 0) {
            v1_3 = p33;
        } else {
            v1_3 = "#FFFFFF";
        }
        String v1_5;
        if ((p49 & 2048) == 0) {
            v1_5 = p34;
        } else {
            v1_5 = "#8FA3AD";
        }
        String v1_7;
        if ((p49 & 4096) == 0) {
            v1_7 = p35;
        } else {
            v1_7 = "#384B57";
        }
        String v1_9;
        String v16 = "#FF5252";
        if ((p49 & 8192) == 0) {
            v1_9 = p36;
        } else {
            v1_9 = "#FF5252";
        }
        String v1_11;
        if ((p49 & 16384) == 0) {
            v1_11 = p37;
        } else {
            v1_11 = "#FFFFFF";
        }
        String v17_2;
        if ((p49 & 32768) == 0) {
            v17_2 = p38;
        } else {
            v17_2 = "#9EAFB8";
        }
        if ((p49 & 65536) == 0) {
            v16 = p39;
        }
        if ((p49 & 131072) == 0) {
            v10 = p40;
        }
        String v18_6;
        if ((p49 & 262144) == 0) {
            v18_6 = p41;
        } else {
            v18_6 = "#2E3C56";
        }
        if ((p49 & 524288) == 0) {
            v14 = p42;
        }
        if ((p49 & 1048576) == 0) {
            v3 = p43;
        }
        if ((p49 & 2097152) == 0) {
            v7 = p44;
        }
        if ((p49 & 4194304) == 0) {
            v5 = p45;
        }
        int v19_4;
        if ((p49 & 8388608) == 0) {
            v19_4 = p46;
        } else {
            v19_4 = 16;
        }
        String v20_2;
        if ((p49 & 16777216) == 0) {
            v20_2 = p47;
        } else {
            v20_2 = "";
        }
        boolean v49_1;
        if ((p49 & 33554432) == 0) {
            v49_1 = p48;
        } else {
            v49_1 = 1;
        }
        this(v1_1, v2_1, v4_1, v6_1, v8_1, v9_1, v11_1, v12_1, v13_1, v15_1, v1_3, v1_5, v1_7, v1_9, v1_11, v17_2, v16, v10, v18_6, v14, v3, v7, v5, v19_4, v20_2, v49_1);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig copy$default(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39, String p40, int p41, String p42, boolean p43, int p44, Object p45)
    {
        String v2_1;
        if ((p44 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.backgroundColor;
        }
        String v3_1;
        if ((p44 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.cardBackgroundColor;
        }
        String v4_1;
        if ((p44 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.labelColor;
        }
        String v5_1;
        if ((p44 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.valueColor;
        }
        String v6_1;
        if ((p44 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.titleColor;
        }
        String v7_1;
        if ((p44 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.otpCodeColor;
        }
        String v8_1;
        if ((p44 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.otpStatusColor;
        }
        String v9_1;
        if ((p44 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.accentBarColor;
        }
        String v10_1;
        if ((p44 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.cardBorderColor;
        }
        String v11_1;
        if ((p44 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.confirmButtonColor;
        }
        String v12_1;
        if ((p44 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.confirmButtonTextColor;
        }
        String v13_1;
        if ((p44 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.cancelButtonTextColor;
        }
        String v14_1;
        if ((p44 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.cancelButtonBorderColor;
        }
        String v15_1;
        if ((p44 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.timerColor;
        }
        String v2_3;
        if ((p44 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.pinTitleColor;
        }
        boolean v1_6;
        if ((p44 & 32768) == 0) {
            v1_6 = p33;
        } else {
            v1_6 = p17.pinSubtitleColor;
        }
        boolean v1_7;
        if ((p44 & 65536) == 0) {
            v1_7 = p34;
        } else {
            v1_7 = p17.pinErrorColor;
        }
        boolean v1_8;
        if ((p44 & 131072) == 0) {
            v1_8 = p35;
        } else {
            v1_8 = p17.pinDotFilledColor;
        }
        boolean v1_9;
        if ((p44 & 262144) == 0) {
            v1_9 = p36;
        } else {
            v1_9 = p17.pinDotEmptyColor;
        }
        boolean v1_10;
        if ((p44 & 524288) == 0) {
            v1_10 = p37;
        } else {
            v1_10 = p17.pinDotBorderColor;
        }
        boolean v1_11;
        if ((p44 & 1048576) == 0) {
            v1_11 = p38;
        } else {
            v1_11 = p17.keypadButtonColor;
        }
        boolean v1_1;
        if ((p44 & 2097152) == 0) {
            v1_1 = p39;
        } else {
            v1_1 = p17.keypadButtonTextColor;
        }
        boolean v1_2;
        if ((p44 & 4194304) == 0) {
            v1_2 = p40;
        } else {
            v1_2 = p17.keypadDeleteIconColor;
        }
        boolean v1_3;
        if ((p44 & 8388608) == 0) {
            v1_3 = p41;
        } else {
            v1_3 = p17.cornerRadius;
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
            v44_1 = p17.isShowCloseButton;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_6, v1_7, v1_8, v1_9, v1_10, v1_11, v1_1, v1_2, v1_3, v43_1, v44_1);
    }

    public final String component1()
    {
        return this.backgroundColor;
    }

    public final String component10()
    {
        return this.confirmButtonColor;
    }

    public final String component11()
    {
        return this.confirmButtonTextColor;
    }

    public final String component12()
    {
        return this.cancelButtonTextColor;
    }

    public final String component13()
    {
        return this.cancelButtonBorderColor;
    }

    public final String component14()
    {
        return this.timerColor;
    }

    public final String component15()
    {
        return this.pinTitleColor;
    }

    public final String component16()
    {
        return this.pinSubtitleColor;
    }

    public final String component17()
    {
        return this.pinErrorColor;
    }

    public final String component18()
    {
        return this.pinDotFilledColor;
    }

    public final String component19()
    {
        return this.pinDotEmptyColor;
    }

    public final String component2()
    {
        return this.cardBackgroundColor;
    }

    public final String component20()
    {
        return this.pinDotBorderColor;
    }

    public final String component21()
    {
        return this.keypadButtonColor;
    }

    public final String component22()
    {
        return this.keypadButtonTextColor;
    }

    public final String component23()
    {
        return this.keypadDeleteIconColor;
    }

    public final int component24()
    {
        return this.cornerRadius;
    }

    public final String component25()
    {
        return this.fontName;
    }

    public final boolean component26()
    {
        return this.isShowCloseButton;
    }

    public final String component3()
    {
        return this.labelColor;
    }

    public final String component4()
    {
        return this.valueColor;
    }

    public final String component5()
    {
        return this.titleColor;
    }

    public final String component6()
    {
        return this.otpCodeColor;
    }

    public final String component7()
    {
        return this.otpStatusColor;
    }

    public final String component8()
    {
        return this.accentBarColor;
    }

    public final String component9()
    {
        return this.cardBorderColor;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig copy(String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39, String p40, String p41, String p42, String p43, String p44, String p45, String p46, String p47, String p48, String p49, String p50, int p51, String p52, boolean p53)
    {
        return new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig(p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50, p51, p52, p53);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.backgroundColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).backgroundColor)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardBackgroundColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).cardBackgroundColor)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.labelColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).labelColor)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.valueColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).valueColor)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.titleColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).titleColor)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpCodeColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).otpCodeColor)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.otpStatusColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).otpStatusColor)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.accentBarColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).accentBarColor)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardBorderColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).cardBorderColor)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButtonColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).confirmButtonColor)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.confirmButtonTextColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).confirmButtonTextColor)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cancelButtonTextColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).cancelButtonTextColor)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.cancelButtonBorderColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).cancelButtonBorderColor)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.timerColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).timerColor)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinTitleColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).pinTitleColor)) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinSubtitleColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).pinSubtitleColor)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinErrorColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).pinErrorColor)) {
                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinDotFilledColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).pinDotFilledColor)) {
                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinDotEmptyColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).pinDotEmptyColor)) {
                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinDotBorderColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).pinDotBorderColor)) {
                                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.keypadButtonColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).keypadButtonColor)) {
                                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.keypadButtonTextColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).keypadButtonTextColor)) {
                                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.keypadDeleteIconColor, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).keypadDeleteIconColor)) {
                                                                                                            if (this.cornerRadius == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).cornerRadius) {
                                                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.fontName, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).fontName)) {
                                                                                                                    if (this.isShowCloseButton == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig) p5).isShowCloseButton) {
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

    public final String getAccentBarColor()
    {
        return this.accentBarColor;
    }

    public final String getBackgroundColor()
    {
        return this.backgroundColor;
    }

    public final String getCancelButtonBorderColor()
    {
        return this.cancelButtonBorderColor;
    }

    public final String getCancelButtonTextColor()
    {
        return this.cancelButtonTextColor;
    }

    public final String getCardBackgroundColor()
    {
        return this.cardBackgroundColor;
    }

    public final String getCardBorderColor()
    {
        return this.cardBorderColor;
    }

    public final String getConfirmButtonColor()
    {
        return this.confirmButtonColor;
    }

    public final String getConfirmButtonTextColor()
    {
        return this.confirmButtonTextColor;
    }

    public final int getCornerRadius()
    {
        return this.cornerRadius;
    }

    public final String getFontName()
    {
        return this.fontName;
    }

    public final String getKeypadButtonColor()
    {
        return this.keypadButtonColor;
    }

    public final String getKeypadButtonTextColor()
    {
        return this.keypadButtonTextColor;
    }

    public final String getKeypadDeleteIconColor()
    {
        return this.keypadDeleteIconColor;
    }

    public final String getLabelColor()
    {
        return this.labelColor;
    }

    public final String getOtpCodeColor()
    {
        return this.otpCodeColor;
    }

    public final String getOtpStatusColor()
    {
        return this.otpStatusColor;
    }

    public final String getPinDotBorderColor()
    {
        return this.pinDotBorderColor;
    }

    public final String getPinDotEmptyColor()
    {
        return this.pinDotEmptyColor;
    }

    public final String getPinDotFilledColor()
    {
        return this.pinDotFilledColor;
    }

    public final String getPinErrorColor()
    {
        return this.pinErrorColor;
    }

    public final String getPinSubtitleColor()
    {
        return this.pinSubtitleColor;
    }

    public final String getPinTitleColor()
    {
        return this.pinTitleColor;
    }

    public final String getTimerColor()
    {
        return this.timerColor;
    }

    public final String getTitleColor()
    {
        return this.titleColor;
    }

    public final String getValueColor()
    {
        return this.valueColor;
    }

    public int hashCode()
    {
        int vtmp25 = e5.i.c(Kb.e.v(this.cornerRadius, e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.backgroundColor.hashCode() * 31), 31, this.cardBackgroundColor), 31, this.labelColor), 31, this.valueColor), 31, this.titleColor), 31, this.otpCodeColor), 31, this.otpStatusColor), 31, this.accentBarColor), 31, this.cardBorderColor), 31, this.confirmButtonColor), 31, this.confirmButtonTextColor), 31, this.cancelButtonTextColor), 31, this.cancelButtonBorderColor), 31, this.timerColor), 31, this.pinTitleColor), 31, this.pinSubtitleColor), 31, this.pinErrorColor), 31, this.pinDotFilledColor), 31, this.pinDotEmptyColor), 31, this.pinDotBorderColor), 31, this.keypadButtonColor), 31, this.keypadButtonTextColor), 31, this.keypadDeleteIconColor), 31), 31, this.fontName);
        int v1_1 = this.isShowCloseButton;
        if (v1_1 != 0) {
            v1_1 = 1;
        }
        return (vtmp25 + v1_1);
    }

    public final boolean isShowCloseButton()
    {
        return this.isShowCloseButton;
    }

    public String toString()
    {
        String v3_0 = this.labelColor;
        int v4_0 = this.valueColor;
        String v5 = this.titleColor;
        String v6 = this.otpCodeColor;
        String v7 = this.otpStatusColor;
        String v8 = this.accentBarColor;
        String v9 = this.cardBorderColor;
        String v10 = this.confirmButtonColor;
        String v11 = this.confirmButtonTextColor;
        String v12 = this.cancelButtonTextColor;
        String v14 = this.timerColor;
        String v16 = this.pinTitleColor;
        String v17 = this.pinSubtitleColor;
        String v18 = this.pinErrorColor;
        String v19 = this.pinDotFilledColor;
        String v20 = this.pinDotEmptyColor;
        String v21 = this.pinDotBorderColor;
        String v22 = this.keypadButtonColor;
        String v23 = this.keypadButtonTextColor;
        String v24 = this.keypadDeleteIconColor;
        int v25 = this.cornerRadius;
        String v26 = this.fontName;
        boolean v27 = this.isShowCloseButton;
        String v28 = this.cancelButtonBorderColor;
        String v0_2 = androidx.camera.camera2.internal.G.i("TrueIdSmartOTPUIConfig(backgroundColor=", this.backgroundColor, ", cardBackgroundColor=", this.cardBackgroundColor, ", labelColor=");
        q9.H.p(v0_2, v3_0, ", valueColor=", v4_0, ", titleColor=");
        q9.H.p(v0_2, v5, ", otpCodeColor=", v6, ", otpStatusColor=");
        q9.H.p(v0_2, v7, ", accentBarColor=", v8, ", cardBorderColor=");
        q9.H.p(v0_2, v9, ", confirmButtonColor=", v10, ", confirmButtonTextColor=");
        q9.H.p(v0_2, v11, ", cancelButtonTextColor=", v12, ", cancelButtonBorderColor=");
        q9.H.p(v0_2, v28, ", timerColor=", v14, ", pinTitleColor=");
        q9.H.p(v0_2, v16, ", pinSubtitleColor=", v17, ", pinErrorColor=");
        q9.H.p(v0_2, v18, ", pinDotFilledColor=", v19, ", pinDotEmptyColor=");
        q9.H.p(v0_2, v20, ", pinDotBorderColor=", v21, ", keypadButtonColor=");
        q9.H.p(v0_2, v22, ", keypadButtonTextColor=", v23, ", keypadDeleteIconColor=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(v25, v24, ", cornerRadius=", ", fontName=", v0_2);
        v0_2.append(v26);
        v0_2.append(", isShowCloseButton=");
        v0_2.append(v27);
        v0_2.append(")");
        return v0_2.toString();
    }
}

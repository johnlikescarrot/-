package vn.ai.trueid.sdk.models.sotp;
public final class TrueIdSmartOTPConfig {
    public static final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig$Companion Companion;
    private static final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig default;
    private final String accessTokenEndPoint;
    private String appId;
    private String appSecret;
    private final String authInitiateEndpoint;
    private final String completeEndpoint;
    private final String createRequestEndPoint;
    private final String credentialStatusEndpoint;
    private final String domain;
    private final String gatewayStaticPublicKey;
    private final String hpkeAuthInfo;
    private final String hpkeRegistrationInfo;
    private final String initiateAuthEndpoint;
    private final String initiateEndpoint;
    private final String kid;
    private String lockEndpoint;
    private String personID;
    private vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy pinPolicy;
    private final String provisionEndpoint;
    private final String revokeDeviceEndpoint;
    private final String sealEndpoint;
    private int sotpType;
    private vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig sslPinningConfig;
    private final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig uiConfig;
    private String userId;
    private final String verifyEndpoint;

    static TrueIdSmartOTPConfig()
    {
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig.Companion = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig$Companion(0);
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig.default = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8388607, 0);
        return;
    }

    public TrueIdSmartOTPConfig()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8388607, 0);
        return;
    }

    public TrueIdSmartOTPConfig(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14, int p15, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p16, vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig p17, String p18, String p19, String p20, String p21, String p22, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p23)
    {
        this.domain = p1;
        this.appId = p2;
        this.appSecret = p3;
        this.accessTokenEndPoint = p4;
        this.createRequestEndPoint = p5;
        this.provisionEndpoint = p6;
        this.initiateEndpoint = p7;
        this.initiateAuthEndpoint = p8;
        this.completeEndpoint = p9;
        this.verifyEndpoint = p10;
        this.revokeDeviceEndpoint = p11;
        this.credentialStatusEndpoint = p12;
        this.authInitiateEndpoint = p13;
        this.personID = p14;
        this.sotpType = p15;
        this.pinPolicy = p16;
        this.sslPinningConfig = p17;
        this.sealEndpoint = p18;
        this.kid = p19;
        this.gatewayStaticPublicKey = p20;
        this.hpkeRegistrationInfo = p21;
        this.hpkeAuthInfo = p22;
        this.uiConfig = p23;
        this.userId = "";
        this.lockEndpoint = wmoi.igob.ggcn(3122);
        return;
    }

    public synthetic TrueIdSmartOTPConfig(String p52, String p53, String p54, String p55, String p56, String p57, String p58, String p59, String p60, String p61, String p62, String p63, String p64, String p65, int p66, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p67, vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig p68, String p69, String p70, String p71, String p72, String p73, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p74, int p75, kotlin.jvm.internal.DefaultConstructorMarker p76)
    {
        int v1_1;
        String v2 = "";
        if ((p75 & 1) == 0) {
            v1_1 = p52;
        } else {
            v1_1 = "";
        }
        String v3_1;
        if ((p75 & 2) == 0) {
            v3_1 = p53;
        } else {
            v3_1 = "";
        }
        String v4_1;
        if ((p75 & 4) == 0) {
            v4_1 = p54;
        } else {
            v4_1 = "";
        }
        String v5_1;
        if ((p75 & 8) == 0) {
            v5_1 = p55;
        } else {
            v5_1 = "";
        }
        String v6_1;
        if ((p75 & 16) == 0) {
            v6_1 = p56;
        } else {
            v6_1 = "";
        }
        String v7_1;
        String v7_0 = (p75 & 32);
        String v8_1 = wmoi.igob.ggcn(3123);
        if (v7_0 == null) {
            v7_1 = p57;
        } else {
            v7_1 = v8_1;
        }
        if ((p75 & 64) == 0) {
            v8_1 = p58;
        }
        String v9_2;
        if ((p75 & 128) == 0) {
            v9_2 = p59;
        } else {
            v9_2 = wmoi.igob.ggcn(3124);
        }
        String v10_1;
        if ((p75 & 256) == 0) {
            v10_1 = p60;
        } else {
            v10_1 = wmoi.igob.ggcn(3125);
        }
        String v11_1;
        if ((p75 & 512) == 0) {
            v11_1 = p61;
        } else {
            v11_1 = wmoi.igob.ggcn(3126);
        }
        String v12_1;
        if ((p75 & 1024) == 0) {
            v12_1 = p62;
        } else {
            v12_1 = wmoi.igob.ggcn(3127);
        }
        String v13_1;
        if ((p75 & 2048) == 0) {
            v13_1 = p63;
        } else {
            v13_1 = wmoi.igob.ggcn(3128);
        }
        String v14_1;
        if ((p75 & 4096) == 0) {
            v14_1 = p64;
        } else {
            v14_1 = "";
        }
        String v15_1;
        if ((p75 & 8192) == 0) {
            v15_1 = p65;
        } else {
            v15_1 = "";
        }
        int v1_3;
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig v52_2 = v1_1;
        if ((p75 & 16384) == 0) {
            v1_3 = p66;
        } else {
            v1_3 = 1;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy v16_2;
        if ((p75 & 32768) == 0) {
            v16_2 = p67;
        } else {
            v16_2 = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy;
            v16_2(0, 0, 0, 0, 0, 31, 0);
        }
        vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig v17_0;
        if ((p75 & 65536) == 0) {
            v17_0 = p68;
        } else {
            v17_0 = 0;
        }
        String v18_2;
        if ((p75 & 131072) == 0) {
            v18_2 = p69;
        } else {
            v18_2 = "";
        }
        String v19_2;
        if ((p75 & 262144) == 0) {
            v19_2 = p70;
        } else {
            v19_2 = "";
        }
        if ((p75 & 524288) == 0) {
            v2 = p71;
        }
        String v20_4;
        if ((p75 & 1048576) == 0) {
            v20_4 = p72;
        } else {
            v20_4 = wmoi.igob.ggcn(3129);
        }
        String v21_2;
        if ((p75 & 2097152) == 0) {
            v21_2 = p73;
        } else {
            v21_2 = wmoi.igob.ggcn(3130);
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v75_1;
        if ((p75 & 4194304) == 0) {
            v75_1 = p74;
        } else {
            v75_1 = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108863, 0);
        }
        this(v52_2, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_2, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v1_3, v16_2, v17_0, v18_2, v19_2, v2, v20_4, v21_2, v75_1);
        return;
    }

    public static final synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig access$getDefault$cp()
    {
        return vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig.default;
    }

    public static synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig copy$default(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, int p32, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p33, vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig p34, String p35, String p36, String p37, String p38, String p39, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p40, int p41, Object p42)
    {
        int v2_1;
        if ((p41 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.domain;
        }
        String v3_1;
        if ((p41 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.appId;
        }
        String v4_1;
        if ((p41 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.appSecret;
        }
        String v5_1;
        if ((p41 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.accessTokenEndPoint;
        }
        String v6_1;
        if ((p41 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.createRequestEndPoint;
        }
        String v7_1;
        if ((p41 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.provisionEndpoint;
        }
        String v8_1;
        if ((p41 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.initiateEndpoint;
        }
        String v9_1;
        if ((p41 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.initiateAuthEndpoint;
        }
        String v10_1;
        if ((p41 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.completeEndpoint;
        }
        String v11_1;
        if ((p41 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.verifyEndpoint;
        }
        String v12_1;
        if ((p41 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.revokeDeviceEndpoint;
        }
        String v13_1;
        if ((p41 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.credentialStatusEndpoint;
        }
        String v14_1;
        if ((p41 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.authInitiateEndpoint;
        }
        String v15_1;
        if ((p41 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.personID;
        }
        int v2_3;
        if ((p41 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.sotpType;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v1_3;
        if ((p41 & 32768) == 0) {
            v1_3 = p33;
        } else {
            v1_3 = p17.pinPolicy;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v1_4;
        if ((p41 & 65536) == 0) {
            v1_4 = p34;
        } else {
            v1_4 = p17.sslPinningConfig;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v1_5;
        if ((p41 & 131072) == 0) {
            v1_5 = p35;
        } else {
            v1_5 = p17.sealEndpoint;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v1_6;
        if ((p41 & 262144) == 0) {
            v1_6 = p36;
        } else {
            v1_6 = p17.kid;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v1_7;
        if ((p41 & 524288) == 0) {
            v1_7 = p37;
        } else {
            v1_7 = p17.gatewayStaticPublicKey;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v1_8;
        if ((p41 & 1048576) == 0) {
            v1_8 = p38;
        } else {
            v1_8 = p17.hpkeRegistrationInfo;
        }
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v1_1;
        if ((p41 & 2097152) == 0) {
            v1_1 = p39;
        } else {
            v1_1 = p17.hpkeAuthInfo;
        }
        String v41_1;
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v40_1;
        if ((p41 & 4194304) == 0) {
            v41_1 = p40;
            v40_1 = v1_1;
        } else {
            v40_1 = v1_1;
            v41_1 = p17.uiConfig;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_3, v1_4, v1_5, v1_6, v1_7, v1_8, v40_1, v41_1);
    }

    public final String component1()
    {
        return this.domain;
    }

    public final String component10()
    {
        return this.verifyEndpoint;
    }

    public final String component11()
    {
        return this.revokeDeviceEndpoint;
    }

    public final String component12()
    {
        return this.credentialStatusEndpoint;
    }

    public final String component13()
    {
        return this.authInitiateEndpoint;
    }

    public final String component14()
    {
        return this.personID;
    }

    public final int component15()
    {
        return this.sotpType;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy component16()
    {
        return this.pinPolicy;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig component17()
    {
        return this.sslPinningConfig;
    }

    public final String component18()
    {
        return this.sealEndpoint;
    }

    public final String component19()
    {
        return this.kid;
    }

    public final String component2()
    {
        return this.appId;
    }

    public final String component20()
    {
        return this.gatewayStaticPublicKey;
    }

    public final String component21()
    {
        return this.hpkeRegistrationInfo;
    }

    public final String component22()
    {
        return this.hpkeAuthInfo;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig component23()
    {
        return this.uiConfig;
    }

    public final String component3()
    {
        return this.appSecret;
    }

    public final String component4()
    {
        return this.accessTokenEndPoint;
    }

    public final String component5()
    {
        return this.createRequestEndPoint;
    }

    public final String component6()
    {
        return this.provisionEndpoint;
    }

    public final String component7()
    {
        return this.initiateEndpoint;
    }

    public final String component8()
    {
        return this.initiateAuthEndpoint;
    }

    public final String component9()
    {
        return this.completeEndpoint;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig copy(String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, int p39, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p40, vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig p41, String p42, String p43, String p44, String p45, String p46, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig p47)
    {
        return new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig(p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.domain, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).domain)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.appId, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).appId)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.appSecret, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).appSecret)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.accessTokenEndPoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).accessTokenEndPoint)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.createRequestEndPoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).createRequestEndPoint)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.provisionEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).provisionEndpoint)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.initiateEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).initiateEndpoint)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.initiateAuthEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).initiateAuthEndpoint)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.completeEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).completeEndpoint)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.verifyEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).verifyEndpoint)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.revokeDeviceEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).revokeDeviceEndpoint)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.credentialStatusEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).credentialStatusEndpoint)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.authInitiateEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).authInitiateEndpoint)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.personID, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).personID)) {
                                                                        if (this.sotpType == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).sotpType) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinPolicy, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).pinPolicy)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.sslPinningConfig, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).sslPinningConfig)) {
                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.sealEndpoint, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).sealEndpoint)) {
                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.kid, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).kid)) {
                                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.gatewayStaticPublicKey, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).gatewayStaticPublicKey)) {
                                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.hpkeRegistrationInfo, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).hpkeRegistrationInfo)) {
                                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.hpkeAuthInfo, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).hpkeAuthInfo)) {
                                                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.uiConfig, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig) p5).uiConfig)) {
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
            return 1;
        }
    }

    public final String getAccessTokenEndPoint()
    {
        return this.accessTokenEndPoint;
    }

    public final String getAppId()
    {
        return this.appId;
    }

    public final String getAppSecret()
    {
        return this.appSecret;
    }

    public final String getAuthInitiateEndpoint()
    {
        return this.authInitiateEndpoint;
    }

    public final String getCompleteEndpoint()
    {
        return this.completeEndpoint;
    }

    public final String getCreateRequestEndPoint()
    {
        return this.createRequestEndPoint;
    }

    public final String getCredentialStatusEndpoint()
    {
        return this.credentialStatusEndpoint;
    }

    public final String getDomain()
    {
        return this.domain;
    }

    public final String getGatewayStaticPublicKey()
    {
        return this.gatewayStaticPublicKey;
    }

    public final String getHpkeAuthInfo()
    {
        return this.hpkeAuthInfo;
    }

    public final String getHpkeRegistrationInfo()
    {
        return this.hpkeRegistrationInfo;
    }

    public final String getInitiateAuthEndpoint()
    {
        return this.initiateAuthEndpoint;
    }

    public final String getInitiateEndpoint()
    {
        return this.initiateEndpoint;
    }

    public final String getKid()
    {
        return this.kid;
    }

    public final String getLockEndpoint()
    {
        return this.lockEndpoint;
    }

    public final String getPersonID()
    {
        return this.personID;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy getPinPolicy()
    {
        return this.pinPolicy;
    }

    public final String getProvisionEndpoint()
    {
        return this.provisionEndpoint;
    }

    public final String getRevokeDeviceEndpoint()
    {
        return this.revokeDeviceEndpoint;
    }

    public final String getSealEndpoint()
    {
        return this.sealEndpoint;
    }

    public final int getSotpType()
    {
        return this.sotpType;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig getSslPinningConfig()
    {
        return this.sslPinningConfig;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig getUiConfig()
    {
        return this.uiConfig;
    }

    public final String getUserId()
    {
        return this.userId;
    }

    public final String getVerifyEndpoint()
    {
        return this.verifyEndpoint;
    }

    public int hashCode()
    {
        int v0_17;
        String v2_16 = ((this.pinPolicy.hashCode() + Kb.e.v(this.sotpType, e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.domain.hashCode() * 31), 31, this.appId), 31, this.appSecret), 31, this.accessTokenEndPoint), 31, this.createRequestEndPoint), 31, this.provisionEndpoint), 31, this.initiateEndpoint), 31, this.initiateAuthEndpoint), 31, this.completeEndpoint), 31, this.verifyEndpoint), 31, this.revokeDeviceEndpoint), 31, this.credentialStatusEndpoint), 31, this.authInitiateEndpoint), 31, this.personID), 31)) * 31);
        int v0_16 = this.sslPinningConfig;
        if (v0_16 != 0) {
            v0_17 = v0_16.hashCode();
        } else {
            v0_17 = 0;
        }
        return (this.uiConfig.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(((v2_16 + v0_17) * 31), 31, this.sealEndpoint), 31, this.kid), 31, this.gatewayStaticPublicKey), 31, this.hpkeRegistrationInfo), 31, this.hpkeAuthInfo));
    }

    public final void setAppId(String p1)
    {
        this.appId = p1;
        return;
    }

    public final void setAppSecret(String p1)
    {
        this.appSecret = p1;
        return;
    }

    public final void setLockEndpoint(String p1)
    {
        this.lockEndpoint = p1;
        return;
    }

    public final void setPersonID(String p1)
    {
        this.personID = p1;
        return;
    }

    public final void setPinPolicy(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p1)
    {
        this.pinPolicy = p1;
        return;
    }

    public final void setSotpType(int p1)
    {
        this.sotpType = p1;
        return;
    }

    public final void setSslPinningConfig(vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig p1)
    {
        this.sslPinningConfig = p1;
        return;
    }

    public final void setUserId(String p1)
    {
        this.userId = p1;
        return;
    }

    public String toString()
    {
        String v3_2 = this.appSecret;
        String v4_2 = this.accessTokenEndPoint;
        String v5 = this.createRequestEndPoint;
        String v6 = this.provisionEndpoint;
        String v7 = this.initiateEndpoint;
        String v8 = this.initiateAuthEndpoint;
        String v9 = this.completeEndpoint;
        String v10 = this.verifyEndpoint;
        String v11 = this.revokeDeviceEndpoint;
        String v12 = this.credentialStatusEndpoint;
        String v14 = this.personID;
        int v16 = this.sotpType;
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy v17 = this.pinPolicy;
        vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig v18 = this.sslPinningConfig;
        String v19 = this.sealEndpoint;
        String v20 = this.kid;
        String v21 = this.gatewayStaticPublicKey;
        String v22 = this.hpkeRegistrationInfo;
        String v23 = this.hpkeAuthInfo;
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPUIConfig v24 = this.uiConfig;
        String v25 = this.authInitiateEndpoint;
        String v0_4 = androidx.camera.camera2.internal.G.i(wmoi.igob.ggcn(3133), this.domain, wmoi.igob.ggcn(3131), this.appId, wmoi.igob.ggcn(3132));
        q9.H.p(v0_4, v3_2, wmoi.igob.ggcn(3134), v4_2, wmoi.igob.ggcn(3135));
        q9.H.p(v0_4, v5, wmoi.igob.ggcn(3136), v6, wmoi.igob.ggcn(3137));
        q9.H.p(v0_4, v7, wmoi.igob.ggcn(3138), v8, wmoi.igob.ggcn(3139));
        q9.H.p(v0_4, v9, wmoi.igob.ggcn(3140), v10, wmoi.igob.ggcn(3141));
        q9.H.p(v0_4, v11, wmoi.igob.ggcn(3142), v12, wmoi.igob.ggcn(3143));
        q9.H.p(v0_4, v25, wmoi.igob.ggcn(3144), v14, wmoi.igob.ggcn(3145));
        v0_4.append(v16);
        v0_4.append(wmoi.igob.ggcn(3146));
        v0_4.append(v17);
        v0_4.append(wmoi.igob.ggcn(3147));
        v0_4.append(v18);
        v0_4.append(wmoi.igob.ggcn(3148));
        v0_4.append(v19);
        v0_4.append(wmoi.igob.ggcn(3149));
        q9.H.p(v0_4, v20, wmoi.igob.ggcn(3150), v21, wmoi.igob.ggcn(3151));
        q9.H.p(v0_4, v22, wmoi.igob.ggcn(3152), v23, wmoi.igob.ggcn(3153));
        v0_4.append(v24);
        v0_4.append(wmoi.igob.ggcn(3154));
        return v0_4.toString();
    }
}

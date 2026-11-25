package vn.com.vng.zalopay.feature.argument;
public final class BankBindingArgs implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final boolean autoFillFromApi;
    private final String bankBindingSource;
    private String bankCode;
    private final String clientBankCode;
    private final int deeplinkIdentityTypeParam;
    private final boolean enableSubCtaResultPage;
    private final android.os.Parcelable extraArgument;
    private final java.util.Map extraInfo;
    private final String flowSessionId;
    private final boolean hideToro;
    private final String initBankNumber;
    private final String initHolderName;
    private final boolean isSkipResult;
    private final String routingEntrypoint;
    private final String service;
    private final boolean shouldDisplayEKycSuggestion;
    private final int source;
    private final Integer suggestionType;
    private final String textMainCtaResultPage;

    static BankBindingArgs()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.feature.argument.BankBindingArgs, 20373, 20373);
        vn.com.vng.zalopay.feature.argument.BankBindingArgs.CREATOR = new iG.g(17);
        return;
    }

    public BankBindingArgs()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524287, 0);
        return;
    }

    public BankBindingArgs(String p1, String p2, int p3, String p4, String p5, boolean p6, android.os.Parcelable p7, Integer p8, String p9, String p10, boolean p11, String p12, boolean p13, String p14, java.util.Map p15, boolean p16, String p17, boolean p18, int p19)
    {
        this.bankCode = p1;
        this.clientBankCode = p2;
        this.source = p3;
        this.service = p4;
        this.bankBindingSource = p5;
        this.shouldDisplayEKycSuggestion = p6;
        this.extraArgument = p7;
        this.suggestionType = p8;
        this.routingEntrypoint = p9;
        this.initBankNumber = p10;
        this.isSkipResult = p11;
        this.initHolderName = p12;
        this.hideToro = p13;
        this.flowSessionId = p14;
        this.extraInfo = p15;
        this.enableSubCtaResultPage = p16;
        this.textMainCtaResultPage = p17;
        this.autoFillFromApi = p18;
        this.deeplinkIdentityTypeParam = p19;
        return;
    }

    public synthetic BankBindingArgs(String p20, String p21, int p22, String p23, String p24, boolean p25, android.os.Parcelable p26, Integer p27, String p28, String p29, boolean p30, String p31, boolean p32, String p33, java.util.Map p34, boolean p35, String p36, boolean p37, int p38, int p39, kotlin.jvm.internal.DefaultConstructorMarker p40)
    {
        java.util.Map v1_1;
        String v2 = "";
        if ((p39 & 1) == 0) {
            v1_1 = p20;
        } else {
            v1_1 = "";
        }
        String v3_0;
        Integer v4 = 0;
        if ((p39 & 2) == 0) {
            v3_0 = p21;
        } else {
            v3_0 = 0;
        }
        int v5_1;
        if ((p39 & 4) == 0) {
            v5_1 = p22;
        } else {
            v5_1 = 0;
        }
        String v7_1;
        if ((p39 & 8) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = "BANK_SELECTION";
        }
        String v8_1;
        if ((p39 & 16) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = "unknown";
        }
        boolean v9_1;
        if ((p39 & 32) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = 0;
        }
        android.os.Parcelable v10_1;
        if ((p39 & 64) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = 0;
        }
        if ((p39 & 128) == 0) {
            v4 = p27;
        }
        String v11_2;
        if ((p39 & 256) == 0) {
            v11_2 = p28;
        } else {
            v11_2 = "";
        }
        String v12_1;
        if ((p39 & 512) == 0) {
            v12_1 = p29;
        } else {
            v12_1 = "";
        }
        boolean v13_1;
        if ((p39 & 1024) == 0) {
            v13_1 = p30;
        } else {
            v13_1 = 0;
        }
        String v14_1;
        if ((p39 & 2048) == 0) {
            v14_1 = p31;
        } else {
            v14_1 = "";
        }
        boolean v15_1;
        if ((p39 & 4096) == 0) {
            v15_1 = p32;
        } else {
            v15_1 = 0;
        }
        String v6_1;
        if ((p39 & 8192) == 0) {
            v6_1 = p33;
        } else {
            v6_1 = "";
        }
        java.util.Map v1_3;
        p40 = v1_1;
        if ((p39 & 16384) == 0) {
            v1_3 = p34;
        } else {
            v1_3 = kotlin.collections.a0.emptyMap();
        }
        boolean v16_2;
        if ((p39 & 32768) == 0) {
            v16_2 = p35;
        } else {
            v16_2 = 1;
        }
        if ((p39 & 65536) == 0) {
            v2 = p36;
        }
        boolean v17_4;
        if ((p39 & 131072) == 0) {
            v17_4 = p37;
        } else {
            v17_4 = 0;
        }
        int v39_1;
        if ((p39 & 262144) == 0) {
            v39_1 = p38;
        } else {
            v39_1 = 0;
        }
        this(p40, v3_0, v5_1, v7_1, v8_1, v9_1, v10_1, v4, v11_2, v12_1, v13_1, v14_1, v15_1, v6_1, v1_3, v16_2, v2, v17_4, v39_1);
        return;
    }

    public static synthetic vn.com.vng.zalopay.feature.argument.BankBindingArgs copy$default(vn.com.vng.zalopay.feature.argument.BankBindingArgs p17, String p18, String p19, int p20, String p21, String p22, boolean p23, android.os.Parcelable p24, Integer p25, String p26, String p27, boolean p28, String p29, boolean p30, String p31, java.util.Map p32, boolean p33, String p34, boolean p35, int p36, int p37, Object p38)
    {
        java.util.Map v2_1;
        if ((p37 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.bankCode;
        }
        String v3_1;
        if ((p37 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.clientBankCode;
        }
        int v4_1;
        if ((p37 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.source;
        }
        String v5_1;
        if ((p37 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.service;
        }
        String v6_1;
        if ((p37 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.bankBindingSource;
        }
        boolean v7_1;
        if ((p37 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.shouldDisplayEKycSuggestion;
        }
        android.os.Parcelable v8_1;
        if ((p37 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.extraArgument;
        }
        Integer v9_1;
        if ((p37 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.suggestionType;
        }
        String v10_1;
        if ((p37 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.routingEntrypoint;
        }
        String v11_1;
        if ((p37 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.initBankNumber;
        }
        boolean v12_1;
        if ((p37 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.isSkipResult;
        }
        String v13_1;
        if ((p37 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.initHolderName;
        }
        boolean v14_1;
        if ((p37 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.hideToro;
        }
        String v15_1;
        if ((p37 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.flowSessionId;
        }
        java.util.Map v2_3;
        if ((p37 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.extraInfo;
        }
        int v1_1;
        if ((p37 & 32768) == 0) {
            v1_1 = p33;
        } else {
            v1_1 = p17.enableSubCtaResultPage;
        }
        int v1_2;
        if ((p37 & 65536) == 0) {
            v1_2 = p34;
        } else {
            v1_2 = p17.textMainCtaResultPage;
        }
        int v1_3;
        if ((p37 & 131072) == 0) {
            v1_3 = p35;
        } else {
            v1_3 = p17.autoFillFromApi;
        }
        int v36_1;
        boolean v37_1;
        if ((p37 & 262144) == 0) {
            v37_1 = p36;
            v36_1 = v1_3;
        } else {
            v36_1 = v1_3;
            v37_1 = p17.deeplinkIdentityTypeParam;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_1, v1_2, v36_1, v37_1);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component10()
    {
        return this.initBankNumber;
    }

    public final boolean component11()
    {
        return this.isSkipResult;
    }

    public final String component12()
    {
        return this.initHolderName;
    }

    public final boolean component13()
    {
        return this.hideToro;
    }

    public final String component14()
    {
        return this.flowSessionId;
    }

    public final java.util.Map component15()
    {
        return this.extraInfo;
    }

    public final boolean component16()
    {
        return this.enableSubCtaResultPage;
    }

    public final String component17()
    {
        return this.textMainCtaResultPage;
    }

    public final boolean component18()
    {
        return this.autoFillFromApi;
    }

    public final int component19()
    {
        return this.deeplinkIdentityTypeParam;
    }

    public final String component2()
    {
        return this.clientBankCode;
    }

    public final int component3()
    {
        return this.source;
    }

    public final String component4()
    {
        return this.service;
    }

    public final String component5()
    {
        return this.bankBindingSource;
    }

    public final boolean component6()
    {
        return this.shouldDisplayEKycSuggestion;
    }

    public final android.os.Parcelable component7()
    {
        return this.extraArgument;
    }

    public final Integer component8()
    {
        return this.suggestionType;
    }

    public final String component9()
    {
        return this.routingEntrypoint;
    }

    public final vn.com.vng.zalopay.feature.argument.BankBindingArgs copy(String p21, String p22, int p23, String p24, String p25, boolean p26, android.os.Parcelable p27, Integer p28, String p29, String p30, boolean p31, String p32, boolean p33, String p34, java.util.Map p35, boolean p36, String p37, boolean p38, int p39)
    {
        return new vn.com.vng.zalopay.feature.argument.BankBindingArgs(p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.feature.argument.BankBindingArgs)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).clientBankCode)) {
                        if (this.source == ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).source) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.service, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).service)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankBindingSource, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).bankBindingSource)) {
                                    if (this.shouldDisplayEKycSuggestion == ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).shouldDisplayEKycSuggestion) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.extraArgument, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).extraArgument)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.suggestionType, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).suggestionType)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.routingEntrypoint, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).routingEntrypoint)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.initBankNumber, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).initBankNumber)) {
                                                        if (this.isSkipResult == ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).isSkipResult) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.initHolderName, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).initHolderName)) {
                                                                if (this.hideToro == ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).hideToro) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.flowSessionId, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).flowSessionId)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.extraInfo, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).extraInfo)) {
                                                                            if (this.enableSubCtaResultPage == ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).enableSubCtaResultPage) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.textMainCtaResultPage, ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).textMainCtaResultPage)) {
                                                                                    if (this.autoFillFromApi == ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).autoFillFromApi) {
                                                                                        if (this.deeplinkIdentityTypeParam == ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) p5).deeplinkIdentityTypeParam) {
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
            return 1;
        }
    }

    public final boolean getAutoFillFromApi()
    {
        return this.autoFillFromApi;
    }

    public final String getBankBindingSource()
    {
        return this.bankBindingSource;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final int getDeeplinkIdentityTypeParam()
    {
        return this.deeplinkIdentityTypeParam;
    }

    public final boolean getEnableSubCtaResultPage()
    {
        return this.enableSubCtaResultPage;
    }

    public final android.os.Parcelable getExtraArgument()
    {
        return this.extraArgument;
    }

    public final java.util.Map getExtraInfo()
    {
        return this.extraInfo;
    }

    public final String getFlowSessionId()
    {
        return this.flowSessionId;
    }

    public final boolean getHideToro()
    {
        return this.hideToro;
    }

    public final String getInitBankNumber()
    {
        return this.initBankNumber;
    }

    public final String getInitHolderName()
    {
        return this.initHolderName;
    }

    public final String getRoutingEntrypoint()
    {
        return this.routingEntrypoint;
    }

    public final String getService()
    {
        return this.service;
    }

    public final boolean getShouldDisplayEKycSuggestion()
    {
        return this.shouldDisplayEKycSuggestion;
    }

    public final int getSource()
    {
        return this.source;
    }

    public final Integer getSuggestionType()
    {
        return this.suggestionType;
    }

    public final String getTextMainCtaResultPage()
    {
        return this.textMainCtaResultPage;
    }

    public int hashCode()
    {
        boolean v2_0;
        int v0_12 = (this.bankCode.hashCode() * 31);
        boolean v2_12 = this.clientBankCode;
        int v3 = 0;
        if (v2_12) {
            v2_0 = v2_12.hashCode();
        } else {
            v2_0 = 0;
        }
        boolean v2_6;
        int v0_7 = Kb.e.i(e5.i.c(e5.i.c(Kb.e.v(this.source, ((v0_12 + v2_0) * 31), 31), 31, this.service), 31, this.bankBindingSource), 31, this.shouldDisplayEKycSuggestion);
        boolean v2_5 = this.extraArgument;
        if (v2_5) {
            v2_6 = v2_5.hashCode();
        } else {
            v2_6 = 0;
        }
        int v0_9 = ((v0_7 + v2_6) * 31);
        boolean v2_7 = this.suggestionType;
        if (v2_7) {
            v3 = v2_7.hashCode();
        }
        return (Integer.hashCode(this.deeplinkIdentityTypeParam) + Kb.e.i(e5.i.c(Kb.e.i(q9.H.c(this.extraInfo, e5.i.c(Kb.e.i(e5.i.c(Kb.e.i(e5.i.c(e5.i.c(((v0_9 + v3) * 31), 31, this.routingEntrypoint), 31, this.initBankNumber), 31, this.isSkipResult), 31, this.initHolderName), 31, this.hideToro), 31, this.flowSessionId), 31), 31, this.enableSubCtaResultPage), 31, this.textMainCtaResultPage), 31, this.autoFillFromApi));
    }

    public final boolean isSkipResult()
    {
        return this.isSkipResult;
    }

    public final void setBankCode(String p1)
    {
        this.bankCode = p1;
        return;
    }

    public String toString()
    {
        String v3_0 = this.source;
        boolean v4_0 = this.service;
        String v5 = this.bankBindingSource;
        boolean v6 = this.shouldDisplayEKycSuggestion;
        android.os.Parcelable v7 = this.extraArgument;
        Integer v8 = this.suggestionType;
        String v9 = this.routingEntrypoint;
        String v10 = this.initBankNumber;
        boolean v11 = this.isSkipResult;
        String v12 = this.initHolderName;
        String v14 = this.flowSessionId;
        java.util.Map v16 = this.extraInfo;
        boolean v17 = this.enableSubCtaResultPage;
        String v18 = this.textMainCtaResultPage;
        boolean v19 = this.autoFillFromApi;
        int v20 = this.deeplinkIdentityTypeParam;
        boolean v21 = this.hideToro;
        String v0_2 = androidx.camera.camera2.internal.G.i("BankBindingArgs(bankCode=", this.bankCode, ", clientBankCode=", this.clientBankCode, ", source=");
        e5.i.s(v3_0, ", service=", v4_0, ", bankBindingSource=", v0_2);
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.z(v0_2, v5, ", shouldDisplayEKycSuggestion=", v6, ", extraArgument=");
        v0_2.append(v7);
        v0_2.append(", suggestionType=");
        v0_2.append(v8);
        v0_2.append(", routingEntrypoint=");
        q9.H.p(v0_2, v9, ", initBankNumber=", v10, ", isSkipResult=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.A(v0_2, v11, ", initHolderName=", v12, ", hideToro=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.A(v0_2, v21, ", flowSessionId=", v14, ", extraInfo=");
        v0_2.append(v16);
        v0_2.append(", enableSubCtaResultPage=");
        v0_2.append(v17);
        v0_2.append(", textMainCtaResultPage=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.z(v0_2, v18, ", autoFillFromApi=", v19, ", deeplinkIdentityTypeParam=");
        return A1.a.h(v20, ")", v0_2);
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        int v4_2;
        p3.writeString(this.bankCode);
        p3.writeString(this.clientBankCode);
        p3.writeInt(this.source);
        p3.writeString(this.service);
        p3.writeString(this.bankBindingSource);
        p3.writeInt(this.shouldDisplayEKycSuggestion);
        p3.writeParcelable(this.extraArgument, p4);
        int v4_1 = this.suggestionType;
        if (v4_1 != 0) {
            p3.writeInt(1);
            v4_2 = v4_1.intValue();
        } else {
            v4_2 = 0;
        }
        p3.writeInt(v4_2);
        p3.writeString(this.routingEntrypoint);
        p3.writeString(this.initBankNumber);
        p3.writeInt(this.isSkipResult);
        p3.writeString(this.initHolderName);
        p3.writeInt(this.hideToro);
        p3.writeString(this.flowSessionId);
        int v4_9 = this.extraInfo;
        p3.writeInt(v4_9.size());
        int v4_11 = v4_9.entrySet().iterator();
        while (v4_11.hasNext()) {
            String v0_9 = ((java.util.Map$Entry) v4_11.next());
            p3.writeString(((String) v0_9.getKey()));
            p3.writeString(((String) v0_9.getValue()));
        }
        p3.writeInt(this.enableSubCtaResultPage);
        p3.writeString(this.textMainCtaResultPage);
        p3.writeInt(this.autoFillFromApi);
        p3.writeInt(this.deeplinkIdentityTypeParam);
        return;
    }
}

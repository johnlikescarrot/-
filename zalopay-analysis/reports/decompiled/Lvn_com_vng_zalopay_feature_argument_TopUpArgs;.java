package vn.com.vng.zalopay.feature.argument;
public final class TopUpArgs implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final boolean bypassAmountInput;
    private final long initAmount;
    private final boolean insidePaymentFlow;
    private final boolean isShowNewUiProposal;
    private final vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity newUiProposalEntity;
    private final int redirectType;

    static TopUpArgs()
    {
        vn.com.vng.zalopay.feature.argument.TopUpArgs.CREATOR = new iG.g(22);
        return;
    }

    public TopUpArgs()
    {
        this(0, 0, 0, 0, 0, 0, 63, 0);
        return;
    }

    public TopUpArgs(long p1, boolean p3, int p4, boolean p5, boolean p6, vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity p7)
    {
        this.initAmount = p1;
        this.bypassAmountInput = p3;
        this.redirectType = p4;
        this.insidePaymentFlow = p5;
        this.isShowNewUiProposal = p6;
        this.newUiProposalEntity = p7;
        return;
    }

    public TopUpArgs(long p8, boolean p10, int p11, boolean p12, boolean p13, vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity p14, int p15, kotlin.jvm.internal.DefaultConstructorMarker p16)
    {
        long v0_1;
        if ((p15 & 1) == 0) {
            v0_1 = p8;
        } else {
            v0_1 = 0;
        }
        boolean v2_1;
        boolean v3 = 0;
        if ((p15 & 2) == 0) {
            v2_1 = p10;
        } else {
            v2_1 = 0;
        }
        int v4_0;
        boolean v5 = 1;
        if ((p15 & 4) == 0) {
            v4_0 = p11;
        } else {
            v4_0 = 1;
        }
        if ((p15 & 8) == 0) {
            v5 = p12;
        }
        if ((p15 & 16) == 0) {
            v3 = p13;
        }
        vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity v15_1;
        if ((p15 & 32) == 0) {
            v15_1 = p14;
        } else {
            v15_1 = 0;
        }
        this(v0_1, v2_1, v4_0, v5, v3, v15_1);
        return;
    }

    public static synthetic vn.com.vng.zalopay.feature.argument.TopUpArgs copy$default(vn.com.vng.zalopay.feature.argument.TopUpArgs p8, long p9, boolean p11, int p12, boolean p13, boolean p14, vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity p15, int p16, Object p17)
    {
        if ((p16 & 1) != 0) {
            p9 = p8.initAmount;
        }
        if ((p16 & 2) != 0) {
            p11 = p8.bypassAmountInput;
        }
        if ((p16 & 4) != 0) {
            p12 = p8.redirectType;
        }
        if ((p16 & 8) != 0) {
            p13 = p8.insidePaymentFlow;
        }
        if ((p16 & 16) != 0) {
            p14 = p8.isShowNewUiProposal;
        }
        if ((p16 & 32) != 0) {
            p15 = p8.newUiProposalEntity;
        }
        return p8.copy(p9, p11, p12, p13, p14, p15);
    }

    public final long component1()
    {
        return this.initAmount;
    }

    public final boolean component2()
    {
        return this.bypassAmountInput;
    }

    public final int component3()
    {
        return this.redirectType;
    }

    public final boolean component4()
    {
        return this.insidePaymentFlow;
    }

    public final boolean component5()
    {
        return this.isShowNewUiProposal;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity component6()
    {
        return this.newUiProposalEntity;
    }

    public final vn.com.vng.zalopay.feature.argument.TopUpArgs copy(long p9, boolean p11, int p12, boolean p13, boolean p14, vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity p15)
    {
        return new vn.com.vng.zalopay.feature.argument.TopUpArgs(p9, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.vng.zalopay.feature.argument.TopUpArgs)) {
                if (this.initAmount == ((vn.com.vng.zalopay.feature.argument.TopUpArgs) p8).initAmount) {
                    if (this.bypassAmountInput == ((vn.com.vng.zalopay.feature.argument.TopUpArgs) p8).bypassAmountInput) {
                        if (this.redirectType == ((vn.com.vng.zalopay.feature.argument.TopUpArgs) p8).redirectType) {
                            if (this.insidePaymentFlow == ((vn.com.vng.zalopay.feature.argument.TopUpArgs) p8).insidePaymentFlow) {
                                if (this.isShowNewUiProposal == ((vn.com.vng.zalopay.feature.argument.TopUpArgs) p8).isShowNewUiProposal) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.newUiProposalEntity, ((vn.com.vng.zalopay.feature.argument.TopUpArgs) p8).newUiProposalEntity)) {
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
            return 1;
        }
    }

    public final boolean getBypassAmountInput()
    {
        return this.bypassAmountInput;
    }

    public final long getInitAmount()
    {
        return this.initAmount;
    }

    public final boolean getInsidePaymentFlow()
    {
        return this.insidePaymentFlow;
    }

    public final vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity getNewUiProposalEntity()
    {
        return this.newUiProposalEntity;
    }

    public final int getRedirectType()
    {
        return this.redirectType;
    }

    public int hashCode()
    {
        int v1_1;
        int v0_3 = Kb.e.i(Kb.e.i(Kb.e.v(this.redirectType, Kb.e.i((Long.hashCode(this.initAmount) * 31), 31, this.bypassAmountInput), 31), 31, this.insidePaymentFlow), 31, this.isShowNewUiProposal);
        int v1_0 = this.newUiProposalEntity;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        return (v0_3 + v1_1);
    }

    public final boolean isShowNewUiProposal()
    {
        return this.isShowNewUiProposal;
    }

    public String toString()
    {
        String v0_0 = this.initAmount;
        boolean v2 = this.bypassAmountInput;
        int v3 = this.redirectType;
        boolean v4 = this.insidePaymentFlow;
        boolean v5 = this.isShowNewUiProposal;
        vn.com.vng.zalopay.domain.topupwithdraw.entity.routingtopup.RoutingTopupEntity v6 = this.newUiProposalEntity;
        StringBuilder v7_1 = new StringBuilder("TopUpArgs(initAmount=");
        v7_1.append(v0_0);
        v7_1.append(", bypassAmountInput=");
        v7_1.append(v2);
        v7_1.append(", redirectType=");
        v7_1.append(v3);
        v7_1.append(", insidePaymentFlow=");
        v7_1.append(v4);
        v7_1.append(", isShowNewUiProposal=");
        v7_1.append(v5);
        v7_1.append(", newUiProposalEntity=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeLong(this.initAmount);
        p3.writeInt(this.bypassAmountInput);
        p3.writeInt(this.redirectType);
        p3.writeInt(this.insidePaymentFlow);
        p3.writeInt(this.isShowNewUiProposal);
        p3.writeParcelable(this.newUiProposalEntity, p4);
        return;
    }
}

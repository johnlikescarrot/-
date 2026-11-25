package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$ProcessingModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int bcErrorCode;
    private final int bmErrorCode;
    private final int linkingType;
    private final String zpTransId;

    static BankSubmitStateModel$ProcessingModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel.CREATOR = new uo.y(23);
        return;
    }

    public BankSubmitStateModel$ProcessingModel(String p1, int p2, String p3, int p4, int p5)
    {
        this.zpTransId = p1;
        this.linkingType = p2;
        this.bankCode = p3;
        this.bmErrorCode = p4;
        this.bcErrorCode = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel p0, String p1, int p2, String p3, int p4, int p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.zpTransId;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.linkingType;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.bankCode;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.bmErrorCode;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.bcErrorCode;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.zpTransId;
    }

    public final int component2()
    {
        return this.linkingType;
    }

    public final String component3()
    {
        return this.bankCode;
    }

    public final int component4()
    {
        return this.bmErrorCode;
    }

    public final int component5()
    {
        return this.bcErrorCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel copy(String p7, int p8, String p9, int p10, int p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel) p5).zpTransId)) {
                    if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel) p5).linkingType) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel) p5).bankCode)) {
                            if (this.bmErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel) p5).bmErrorCode) {
                                if (this.bcErrorCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$ProcessingModel) p5).bcErrorCode) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final int getBcErrorCode()
    {
        return this.bcErrorCode;
    }

    public final int getBmErrorCode()
    {
        return this.bmErrorCode;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((((this.zpTransId.hashCode() * 31) + Integer.hashCode(this.linkingType)) * 31) + this.bankCode.hashCode()) * 31) + Integer.hashCode(this.bmErrorCode)) * 31) + Integer.hashCode(this.bcErrorCode));
    }

    public String toString()
    {
        String v0_0 = this.zpTransId;
        int v1 = this.linkingType;
        String v2 = this.bankCode;
        int v3 = this.bmErrorCode;
        int v4 = this.bcErrorCode;
        StringBuilder v5_1 = new StringBuilder("ProcessingModel(zpTransId=");
        v5_1.append(v0_0);
        v5_1.append(", linkingType=");
        v5_1.append(v1);
        v5_1.append(", bankCode=");
        v5_1.append(v2);
        v5_1.append(", bmErrorCode=");
        v5_1.append(v3);
        v5_1.append(", bcErrorCode=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.zpTransId);
        p1.writeInt(this.linkingType);
        p1.writeString(this.bankCode);
        p1.writeInt(this.bmErrorCode);
        p1.writeInt(this.bcErrorCode);
        return;
    }
}

package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$GenericErrorModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int linkingType;
    private final String message;
    private final String zpTransId;

    static BankSubmitStateModel$GenericErrorModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel.CREATOR = new uo.y(21);
        return;
    }

    public BankSubmitStateModel$GenericErrorModel(String p1, String p2, int p3, String p4)
    {
        this.message = p1;
        this.zpTransId = p2;
        this.linkingType = p3;
        this.bankCode = p4;
        return;
    }

    public synthetic BankSubmitStateModel$GenericErrorModel(String p1, String p2, int p3, String p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 1) != 0) {
            p1 = 0;
        }
        this(p1, p2, p3, p4);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel p0, String p1, String p2, int p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.zpTransId;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.linkingType;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.message;
    }

    public final String component2()
    {
        return this.zpTransId;
    }

    public final int component3()
    {
        return this.linkingType;
    }

    public final String component4()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel copy(String p2, String p3, int p4, String p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel) p5).message)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel) p5).zpTransId)) {
                        if (this.linkingType == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel) p5).linkingType) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$GenericErrorModel) p5).bankCode)) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final int getLinkingType()
    {
        return this.linkingType;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v0_5;
        int v0_0 = this.message;
        if (v0_0 != 0) {
            v0_5 = v0_0.hashCode();
        } else {
            v0_5 = 0;
        }
        return ((((((v0_5 * 31) + this.zpTransId.hashCode()) * 31) + Integer.hashCode(this.linkingType)) * 31) + this.bankCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.message;
        String v1 = this.zpTransId;
        int v2 = this.linkingType;
        String v3 = this.bankCode;
        StringBuilder v4_1 = new StringBuilder("GenericErrorModel(message=");
        v4_1.append(v0_0);
        v4_1.append(", zpTransId=");
        v4_1.append(v1);
        v4_1.append(", linkingType=");
        v4_1.append(v2);
        v4_1.append(", bankCode=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeString(this.zpTransId);
        p1.writeInt(this.linkingType);
        p1.writeString(this.bankCode);
        return;
    }
}

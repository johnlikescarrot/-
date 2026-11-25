package vn.com.vng.zalopay.bankbinding.presentation.model.ekyc;
public final class BankEKycInputModel implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int idType;
    private final String idValue;
    private final boolean isVerify;

    static BankEKycInputModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel.CREATOR = new uo.L(15);
        return;
    }

    public BankEKycInputModel(int p1, String p2, boolean p3)
    {
        this.idType = p1;
        this.idValue = p2;
        this.isVerify = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel p0, int p1, String p2, boolean p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.idType;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.idValue;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.isVerify;
        }
        return p0.copy(p1, p2, p3);
    }

    public final int component1()
    {
        return this.idType;
    }

    public final String component2()
    {
        return this.idValue;
    }

    public final boolean component3()
    {
        return this.isVerify;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel copy(int p2, String p3, boolean p4)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel)) {
                if (this.idType == ((vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel) p5).idType) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.idValue, ((vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel) p5).idValue)) {
                        if (this.isVerify == ((vn.com.vng.zalopay.bankbinding.presentation.model.ekyc.BankEKycInputModel) p5).isVerify) {
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
            return 1;
        }
    }

    public final int getIdType()
    {
        return this.idType;
    }

    public final String getIdValue()
    {
        return this.idValue;
    }

    public int hashCode()
    {
        return ((((Integer.hashCode(this.idType) * 31) + this.idValue.hashCode()) * 31) + Boolean.hashCode(this.isVerify));
    }

    public final boolean isVerify()
    {
        return this.isVerify;
    }

    public String toString()
    {
        String v0_0 = this.idType;
        String v1 = this.idValue;
        boolean v2 = this.isVerify;
        StringBuilder v3_1 = new StringBuilder("BankEKycInputModel(idType=");
        v3_1.append(v0_0);
        v3_1.append(", idValue=");
        v3_1.append(v1);
        v3_1.append(", isVerify=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.idType);
        p1.writeString(this.idValue);
        p1.writeInt(this.isVerify);
        return;
    }
}

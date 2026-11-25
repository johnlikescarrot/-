package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankTabsArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String bankName;
    private final java.util.List models;

    static BankTabsArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument.CREATOR = new no.e(6);
        return;
    }

    public BankTabsArgument(String p1, java.util.List p2, String p3)
    {
        this.bankName = p1;
        this.models = p2;
        this.bankCode = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument p0, String p1, java.util.List p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.bankName;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.models;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.bankName;
    }

    public final java.util.List component2()
    {
        return this.models;
    }

    public final String component3()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument copy(String p2, java.util.List p3, String p4)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument) p5).bankName)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.models, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument) p5).models)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument) p5).bankCode)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getBankName()
    {
        return this.bankName;
    }

    public final java.util.List getModels()
    {
        return this.models;
    }

    public int hashCode()
    {
        return ((((this.bankName.hashCode() * 31) + this.models.hashCode()) * 31) + this.bankCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankName;
        java.util.List v1 = this.models;
        String v2 = this.bankCode;
        StringBuilder v3_1 = new StringBuilder("BankTabsArgument(bankName=");
        v3_1.append(v0_0);
        v3_1.append(", models=");
        v3_1.append(v1);
        v3_1.append(", bankCode=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.bankName);
        java.util.Iterator v0_1 = this.models;
        p3.writeInt(v0_1.size());
        java.util.Iterator v0_2 = v0_1.iterator();
        while (v0_2.hasNext()) {
            p3.writeParcelable(((android.os.Parcelable) v0_2.next()), p4);
        }
        p3.writeString(this.bankCode);
        return;
    }
}

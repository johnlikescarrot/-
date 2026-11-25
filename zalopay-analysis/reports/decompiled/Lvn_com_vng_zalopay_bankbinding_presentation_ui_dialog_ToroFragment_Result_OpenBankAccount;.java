package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class ToroFragment$Result$OpenBankAccount extends Io.e0 implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String source;

    static ToroFragment$Result$OpenBankAccount()
    {
        vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount.CREATOR = new Io.S(10);
        return;
    }

    public ToroFragment$Result$OpenBankAccount(String p1, String p2)
    {
        this.bankCode = p1;
        this.source = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount copy$default(vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.source;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.source;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.source, ((vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.ToroFragment$Result$OpenBankAccount) p5).source)) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getSource()
    {
        return this.source;
    }

    public int hashCode()
    {
        return ((this.bankCode.hashCode() * 31) + this.source.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.source;
        StringBuilder v2_1 = new StringBuilder("OpenBankAccount(bankCode=");
        v2_1.append(v0_0);
        v2_1.append(", source=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.source);
        return;
    }
}

package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class SubmitAccountModeCModel$WebInternetBanking extends uo.s0 implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument bankWebAppArgument;

    static SubmitAccountModeCModel$WebInternetBanking()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$WebInternetBanking.CREATOR = new uo.L(12);
        return;
    }

    public SubmitAccountModeCModel$WebInternetBanking(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument p1)
    {
        this.bankWebAppArgument = p1;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$WebInternetBanking copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$WebInternetBanking p0, vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.bankWebAppArgument;
        }
        return p0.copy(p1);
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument component1()
    {
        return this.bankWebAppArgument;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$WebInternetBanking copy(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument p2)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$WebInternetBanking(p2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$WebInternetBanking)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankWebAppArgument, ((vn.com.vng.zalopay.bankbinding.presentation.model.SubmitAccountModeCModel$WebInternetBanking) p4).bankWebAppArgument)) {
                    return 1;
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

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument getBankWebAppArgument()
    {
        return this.bankWebAppArgument;
    }

    public int hashCode()
    {
        return this.bankWebAppArgument.hashCode();
    }

    public String toString()
    {
        String v0_0 = this.bankWebAppArgument;
        StringBuilder v1_1 = new StringBuilder("WebInternetBanking(bankWebAppArgument=");
        v1_1.append(v0_0);
        v1_1.append(")");
        return v1_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        this.bankWebAppArgument.writeToParcel(p2, p3);
        return;
    }
}

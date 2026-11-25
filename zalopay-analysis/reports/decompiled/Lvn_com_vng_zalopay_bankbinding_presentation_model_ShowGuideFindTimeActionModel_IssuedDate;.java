package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class ShowGuideFindTimeActionModel$IssuedDate extends uo.r0 {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.data.bank.ui.BankStyle bankStyle;
    private final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel issuedData;

    static ShowGuideFindTimeActionModel$IssuedDate()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate.CREATOR = new uo.L(8);
        return;
    }

    public ShowGuideFindTimeActionModel$IssuedDate(vn.com.vng.zalopay.data.bank.ui.BankStyle p1, vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel p2)
    {
        this.bankStyle = p1;
        this.issuedData = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate p0, vn.com.vng.zalopay.data.bank.ui.BankStyle p1, vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.bankStyle;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.issuedData;
        }
        return p0.copy(p1, p2);
    }

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle component1()
    {
        return this.bankStyle;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel component2()
    {
        return this.issuedData;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate copy(vn.com.vng.zalopay.data.bank.ui.BankStyle p2, vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankStyle, ((vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate) p5).bankStyle)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuedData, ((vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$IssuedDate) p5).issuedData)) {
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

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle getBankStyle()
    {
        return this.bankStyle;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel getIssuedData()
    {
        return this.issuedData;
    }

    public int hashCode()
    {
        return ((this.bankStyle.hashCode() * 31) + this.issuedData.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankStyle;
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$IssueDateModel v1 = this.issuedData;
        StringBuilder v2_1 = new StringBuilder("IssuedDate(bankStyle=");
        v2_1.append(v0_0);
        v2_1.append(", issuedData=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeParcelable(this.bankStyle, p3);
        this.issuedData.writeToParcel(p2, p3);
        return;
    }
}

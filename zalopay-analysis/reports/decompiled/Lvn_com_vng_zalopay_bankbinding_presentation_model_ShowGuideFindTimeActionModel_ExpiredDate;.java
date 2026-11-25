package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class ShowGuideFindTimeActionModel$ExpiredDate extends uo.r0 {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.data.bank.ui.BankStyle bankStyle;
    private final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel expiredData;

    static ShowGuideFindTimeActionModel$ExpiredDate()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate.CREATOR = new uo.L(7);
        return;
    }

    public ShowGuideFindTimeActionModel$ExpiredDate(vn.com.vng.zalopay.data.bank.ui.BankStyle p1, vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel p2)
    {
        this.bankStyle = p1;
        this.expiredData = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate p0, vn.com.vng.zalopay.data.bank.ui.BankStyle p1, vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.bankStyle;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.expiredData;
        }
        return p0.copy(p1, p2);
    }

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle component1()
    {
        return this.bankStyle;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel component2()
    {
        return this.expiredData;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate copy(vn.com.vng.zalopay.data.bank.ui.BankStyle p2, vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankStyle, ((vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate) p5).bankStyle)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.expiredData, ((vn.com.vng.zalopay.bankbinding.presentation.model.ShowGuideFindTimeActionModel$ExpiredDate) p5).expiredData)) {
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

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel getExpiredData()
    {
        return this.expiredData;
    }

    public int hashCode()
    {
        return ((this.bankStyle.hashCode() * 31) + this.expiredData.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankStyle;
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$ExpiryDateModel v1 = this.expiredData;
        StringBuilder v2_1 = new StringBuilder("ExpiredDate(bankStyle=");
        v2_1.append(v0_0);
        v2_1.append(", expiredData=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeParcelable(this.bankStyle, p3);
        this.expiredData.writeToParcel(p2, p3);
        return;
    }
}

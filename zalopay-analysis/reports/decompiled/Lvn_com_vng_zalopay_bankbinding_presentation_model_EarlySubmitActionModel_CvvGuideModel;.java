package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class EarlySubmitActionModel$CvvGuideModel extends uo.o0 {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.data.bank.ui.BankStyle bankStyle;
    private final String message;

    static EarlySubmitActionModel$CvvGuideModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel.CREATOR = new uo.L(6);
        return;
    }

    public EarlySubmitActionModel$CvvGuideModel(vn.com.vng.zalopay.data.bank.ui.BankStyle p1, String p2)
    {
        this.bankStyle = p1;
        this.message = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel p0, vn.com.vng.zalopay.data.bank.ui.BankStyle p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.bankStyle;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.message;
        }
        return p0.copy(p1, p2);
    }

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle component1()
    {
        return this.bankStyle;
    }

    public final String component2()
    {
        return this.message;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel copy(vn.com.vng.zalopay.data.bank.ui.BankStyle p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankStyle, ((vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel) p5).bankStyle)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.EarlySubmitActionModel$CvvGuideModel) p5).message)) {
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

    public final String getMessage()
    {
        return this.message;
    }

    public int hashCode()
    {
        return ((this.bankStyle.hashCode() * 31) + this.message.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankStyle;
        String v1 = this.message;
        StringBuilder v2_1 = new StringBuilder("CvvGuideModel(bankStyle=");
        v2_1.append(v0_0);
        v2_1.append(", message=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeParcelable(this.bankStyle, p3);
        p2.writeString(this.message);
        return;
    }
}

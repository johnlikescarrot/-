package vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus;
public final class BankLinkingStatusModel$CenterPopupError extends vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String message;

    static BankLinkingStatusModel$CenterPopupError()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError.CREATOR = new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.b();
        return;
    }

    public BankLinkingStatusModel$CenterPopupError()
    {
        this(0, 1, 0);
        return;
    }

    public BankLinkingStatusModel$CenterPopupError(String p2)
    {
        super(0);
        super.message = p2;
        return;
    }

    public synthetic BankLinkingStatusModel$CenterPopupError(String p1, int p2, kotlin.jvm.internal.DefaultConstructorMarker p3)
    {
        if ((p2 & 1) != 0) {
            p1 = 0;
        }
        this(p1);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError p0, String p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.message;
        }
        return p0.copy(p1);
    }

    public final String component1()
    {
        return this.message;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError copy(String p2)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError(p2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$CenterPopupError) p4).message)) {
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

    public final String getMessage()
    {
        return this.message;
    }

    public int hashCode()
    {
        int v0_0 = this.message;
        if (v0_0 != 0) {
            return v0_0.hashCode();
        } else {
            return 0;
        }
    }

    public String toString()
    {
        String v0_0 = this.message;
        StringBuilder v1_1 = new StringBuilder("CenterPopupError(message=");
        v1_1.append(v0_0);
        v1_1.append(")");
        return v1_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        return;
    }
}

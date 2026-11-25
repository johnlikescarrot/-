package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankOtpFeedbackArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int parentId;
    private final String requestCode;

    static BankOtpFeedbackArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument.CREATOR = new no.e(0);
        return;
    }

    public BankOtpFeedbackArgument(int p1, String p2)
    {
        this.parentId = p1;
        this.requestCode = p2;
        return;
    }

    public synthetic BankOtpFeedbackArgument(int p1, String p2, int p3, kotlin.jvm.internal.DefaultConstructorMarker p4)
    {
        if ((p3 & 2) != 0) {
            p2 = "";
        }
        this(p1, p2);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument p0, int p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.parentId;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.requestCode;
        }
        return p0.copy(p1, p2);
    }

    public final int component1()
    {
        return this.parentId;
    }

    public final String component2()
    {
        return this.requestCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument copy(int p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument)) {
                if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument) p5).parentId) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackArgument) p5).requestCode)) {
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

    public final int getParentId()
    {
        return this.parentId;
    }

    public final String getRequestCode()
    {
        return this.requestCode;
    }

    public int hashCode()
    {
        return ((Integer.hashCode(this.parentId) * 31) + this.requestCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.parentId;
        String v1 = this.requestCode;
        StringBuilder v2_1 = new StringBuilder("BankOtpFeedbackArgument(parentId=");
        v2_1.append(v0_0);
        v2_1.append(", requestCode=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.parentId);
        p1.writeString(this.requestCode);
        return;
    }
}

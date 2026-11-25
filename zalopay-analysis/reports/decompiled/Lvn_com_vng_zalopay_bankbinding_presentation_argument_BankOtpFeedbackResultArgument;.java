package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankOtpFeedbackResultArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final no.f Companion;
    private static final vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument DEFAULT;
    private final String answer;
    private final int resultCode;

    static BankOtpFeedbackResultArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument.Companion = new no.f();
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument.CREATOR = new no.e(1);
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument.DEFAULT = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument(0, "");
        return;
    }

    public BankOtpFeedbackResultArgument(int p1, String p2)
    {
        this.resultCode = p1;
        this.answer = p2;
        return;
    }

    public static final synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument access$getDEFAULT$cp()
    {
        return vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument.DEFAULT;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument p0, int p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.resultCode;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.answer;
        }
        return p0.copy(p1, p2);
    }

    public final int component1()
    {
        return this.resultCode;
    }

    public final String component2()
    {
        return this.answer;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument copy(int p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument)) {
                if (this.resultCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument) p5).resultCode) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.answer, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpFeedbackResultArgument) p5).answer)) {
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

    public final String getAnswer()
    {
        return this.answer;
    }

    public final int getResultCode()
    {
        return this.resultCode;
    }

    public int hashCode()
    {
        int v1_1;
        int v0_2 = (Integer.hashCode(this.resultCode) * 31);
        int v1_0 = this.answer;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        return (v0_2 + v1_1);
    }

    public String toString()
    {
        String v0_0 = this.resultCode;
        String v1 = this.answer;
        StringBuilder v2_1 = new StringBuilder("BankOtpFeedbackResultArgument(resultCode=");
        v2_1.append(v0_0);
        v2_1.append(", answer=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.resultCode);
        p1.writeString(this.answer);
        return;
    }
}

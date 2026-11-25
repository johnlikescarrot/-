package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankRopWebArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String accountNumber;
    private final String bankCode;
    private final String cardHolderName;
    private final String cardNumber;
    private final String url;

    static BankRopWebArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument.CREATOR = new no.e(4);
        return;
    }

    public BankRopWebArgument(String p1, String p2, String p3, String p4, String p5)
    {
        this.bankCode = p1;
        this.cardNumber = p2;
        this.accountNumber = p3;
        this.cardHolderName = p4;
        this.url = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument p0, String p1, String p2, String p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.cardNumber;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.accountNumber;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.cardHolderName;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.url;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.cardNumber;
    }

    public final String component3()
    {
        return this.accountNumber;
    }

    public final String component4()
    {
        return this.cardHolderName;
    }

    public final String component5()
    {
        return this.url;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument copy(String p7, String p8, String p9, String p10, String p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardNumber, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument) p5).cardNumber)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.accountNumber, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument) p5).accountNumber)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardHolderName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument) p5).cardHolderName)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument) p5).url)) {
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
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final String getAccountNumber()
    {
        return this.accountNumber;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getCardHolderName()
    {
        return this.cardHolderName;
    }

    public final String getCardNumber()
    {
        return this.cardNumber;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return ((((((((this.bankCode.hashCode() * 31) + this.cardNumber.hashCode()) * 31) + this.accountNumber.hashCode()) * 31) + this.cardHolderName.hashCode()) * 31) + this.url.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.cardNumber;
        String v2 = this.accountNumber;
        String v3 = this.cardHolderName;
        String v4 = this.url;
        StringBuilder v5_1 = new StringBuilder("BankRopWebArgument(bankCode=");
        v5_1.append(v0_0);
        v5_1.append(", cardNumber=");
        v5_1.append(v1);
        v5_1.append(", accountNumber=");
        v5_1.append(v2);
        v5_1.append(", cardHolderName=");
        v5_1.append(v3);
        v5_1.append(", url=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.cardNumber);
        p1.writeString(this.accountNumber);
        p1.writeString(this.cardHolderName);
        p1.writeString(this.url);
        return;
    }
}

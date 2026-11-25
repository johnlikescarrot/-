package vn.com.vng.zalopay.nfc.argument;
public final class BankNFCArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String dob;
    private final String expiryDate;
    private final String lastIdNumber;
    private final String redirectUrl;

    static BankNFCArgument()
    {
        vn.com.vng.zalopay.nfc.argument.BankNFCArgument.CREATOR = new m7.e(16);
        return;
    }

    public BankNFCArgument(String p1, String p2, String p3, String p4)
    {
        this.lastIdNumber = p1;
        this.dob = p2;
        this.expiryDate = p3;
        this.redirectUrl = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.nfc.argument.BankNFCArgument copy$default(vn.com.vng.zalopay.nfc.argument.BankNFCArgument p0, String p1, String p2, String p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.lastIdNumber;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.dob;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.expiryDate;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.redirectUrl;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.lastIdNumber;
    }

    public final String component2()
    {
        return this.dob;
    }

    public final String component3()
    {
        return this.expiryDate;
    }

    public final String component4()
    {
        return this.redirectUrl;
    }

    public final vn.com.vng.zalopay.nfc.argument.BankNFCArgument copy(String p2, String p3, String p4, String p5)
    {
        return new vn.com.vng.zalopay.nfc.argument.BankNFCArgument(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.nfc.argument.BankNFCArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.lastIdNumber, ((vn.com.vng.zalopay.nfc.argument.BankNFCArgument) p5).lastIdNumber)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.dob, ((vn.com.vng.zalopay.nfc.argument.BankNFCArgument) p5).dob)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.expiryDate, ((vn.com.vng.zalopay.nfc.argument.BankNFCArgument) p5).expiryDate)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.redirectUrl, ((vn.com.vng.zalopay.nfc.argument.BankNFCArgument) p5).redirectUrl)) {
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
            return 1;
        }
    }

    public final String getDob()
    {
        return this.dob;
    }

    public final String getExpiryDate()
    {
        return this.expiryDate;
    }

    public final String getLastIdNumber()
    {
        return this.lastIdNumber;
    }

    public final String getRedirectUrl()
    {
        return this.redirectUrl;
    }

    public int hashCode()
    {
        return (this.redirectUrl.hashCode() + e5.i.c(e5.i.c((this.lastIdNumber.hashCode() * 31), 31, this.dob), 31, this.expiryDate));
    }

    public String toString()
    {
        return androidx.compose.foundation.layout.G0.n(androidx.camera.camera2.internal.G.i(wmoi.igob.ggcn(933), this.lastIdNumber, wmoi.igob.ggcn(931), this.dob, wmoi.igob.ggcn(932)), this.expiryDate, wmoi.igob.ggcn(934), this.redirectUrl, wmoi.igob.ggcn(935));
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.lastIdNumber);
        p1.writeString(this.dob);
        p1.writeString(this.expiryDate);
        p1.writeString(this.redirectUrl);
        return;
    }
}

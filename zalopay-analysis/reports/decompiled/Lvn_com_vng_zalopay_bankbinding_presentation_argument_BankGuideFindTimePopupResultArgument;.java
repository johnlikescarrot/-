package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankGuideFindTimePopupResultArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final no.c Companion;
    private static final vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument DEFAULT;
    private final int destId;
    private final String keyValue;
    private final int resultCode;

    static BankGuideFindTimePopupResultArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument.Companion = new no.c();
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument.CREATOR = new Zn.a(22);
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument.DEFAULT = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument(0, "", 0);
        return;
    }

    public BankGuideFindTimePopupResultArgument(int p1, String p2, int p3)
    {
        this.resultCode = p1;
        this.keyValue = p2;
        this.destId = p3;
        return;
    }

    public static final synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument access$getDEFAULT$cp()
    {
        return vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument.DEFAULT;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument p0, int p1, String p2, int p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.resultCode;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.keyValue;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.destId;
        }
        return p0.copy(p1, p2, p3);
    }

    public final int component1()
    {
        return this.resultCode;
    }

    public final String component2()
    {
        return this.keyValue;
    }

    public final int component3()
    {
        return this.destId;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument copy(int p2, String p3, int p4)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument)) {
                if (this.resultCode == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument) p5).resultCode) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.keyValue, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument) p5).keyValue)) {
                        if (this.destId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimePopupResultArgument) p5).destId) {
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
            return 1;
        }
    }

    public final int getDestId()
    {
        return this.destId;
    }

    public final String getKeyValue()
    {
        return this.keyValue;
    }

    public final int getResultCode()
    {
        return this.resultCode;
    }

    public int hashCode()
    {
        return ((((Integer.hashCode(this.resultCode) * 31) + this.keyValue.hashCode()) * 31) + Integer.hashCode(this.destId));
    }

    public String toString()
    {
        String v0_0 = this.resultCode;
        String v1 = this.keyValue;
        int v2 = this.destId;
        StringBuilder v3_1 = new StringBuilder("BankGuideFindTimePopupResultArgument(resultCode=");
        v3_1.append(v0_0);
        v3_1.append(", keyValue=");
        v3_1.append(v1);
        v3_1.append(", destId=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.resultCode);
        p1.writeString(this.keyValue);
        p1.writeInt(this.destId);
        return;
    }
}

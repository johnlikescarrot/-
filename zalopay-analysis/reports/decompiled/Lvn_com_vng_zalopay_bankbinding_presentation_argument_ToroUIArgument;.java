package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class ToroUIArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String entryPoint;
    private final int parentId;
    private final String requestResult;

    static ToroUIArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument.CREATOR = new no.e(15);
        return;
    }

    public ToroUIArgument(int p1, String p2, String p3, String p4)
    {
        this.parentId = p1;
        this.requestResult = p2;
        this.bankCode = p3;
        this.entryPoint = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument p0, int p1, String p2, String p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.parentId;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.requestResult;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.bankCode;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.entryPoint;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final int component1()
    {
        return this.parentId;
    }

    public final String component2()
    {
        return this.requestResult;
    }

    public final String component3()
    {
        return this.bankCode;
    }

    public final String component4()
    {
        return this.entryPoint;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument copy(int p2, String p3, String p4, String p5)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument)) {
                if (this.parentId == ((vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument) p5).parentId) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.requestResult, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument) p5).requestResult)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument) p5).bankCode)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.entryPoint, ((vn.com.vng.zalopay.bankbinding.presentation.argument.ToroUIArgument) p5).entryPoint)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getEntryPoint()
    {
        return this.entryPoint;
    }

    public final int getParentId()
    {
        return this.parentId;
    }

    public final String getRequestResult()
    {
        return this.requestResult;
    }

    public int hashCode()
    {
        return ((((((Integer.hashCode(this.parentId) * 31) + this.requestResult.hashCode()) * 31) + this.bankCode.hashCode()) * 31) + this.entryPoint.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.parentId;
        String v1 = this.requestResult;
        String v2 = this.bankCode;
        String v3 = this.entryPoint;
        StringBuilder v4_1 = new StringBuilder("ToroUIArgument(parentId=");
        v4_1.append(v0_0);
        v4_1.append(", requestResult=");
        v4_1.append(v1);
        v4_1.append(", bankCode=");
        v4_1.append(v2);
        v4_1.append(", entryPoint=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.parentId);
        p1.writeString(this.requestResult);
        p1.writeString(this.bankCode);
        p1.writeString(this.entryPoint);
        return;
    }
}

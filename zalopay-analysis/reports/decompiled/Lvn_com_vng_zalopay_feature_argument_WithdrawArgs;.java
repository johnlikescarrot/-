package vn.com.vng.zalopay.feature.argument;
public final class WithdrawArgs implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String source;

    static WithdrawArgs()
    {
        vn.com.vng.zalopay.feature.argument.WithdrawArgs.CREATOR = new iG.g(23);
        return;
    }

    public WithdrawArgs()
    {
        this(0, 1, 0);
        return;
    }

    public WithdrawArgs(String p1)
    {
        this.source = p1;
        return;
    }

    public synthetic WithdrawArgs(String p1, int p2, kotlin.jvm.internal.DefaultConstructorMarker p3)
    {
        if ((p2 & 1) != 0) {
            p1 = "";
        }
        this(p1);
        return;
    }

    public static synthetic vn.com.vng.zalopay.feature.argument.WithdrawArgs copy$default(vn.com.vng.zalopay.feature.argument.WithdrawArgs p0, String p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.source;
        }
        return p0.copy(p1);
    }

    public final String component1()
    {
        return this.source;
    }

    public final vn.com.vng.zalopay.feature.argument.WithdrawArgs copy(String p2)
    {
        return new vn.com.vng.zalopay.feature.argument.WithdrawArgs(p2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.feature.argument.WithdrawArgs)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.source, ((vn.com.vng.zalopay.feature.argument.WithdrawArgs) p4).source)) {
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

    public final String getSource()
    {
        return this.source;
    }

    public int hashCode()
    {
        return this.source.hashCode();
    }

    public String toString()
    {
        return A1.a.i("WithdrawArgs(source=", this.source, ")");
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.source);
        return;
    }
}

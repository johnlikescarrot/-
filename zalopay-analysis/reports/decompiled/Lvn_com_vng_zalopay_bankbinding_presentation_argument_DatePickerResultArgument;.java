package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class DatePickerResultArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    public static final no.i Companion;
    private static final vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument UNDEFINED;
    private final String month;
    private final String year;

    static DatePickerResultArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument.Companion = new no.i();
        vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument.CREATOR = new no.e(13);
        vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument.UNDEFINED = new vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument("-1", "-1");
        return;
    }

    public DatePickerResultArgument(String p1, String p2)
    {
        this.month = p1;
        this.year = p2;
        return;
    }

    public static final synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument access$getUNDEFINED$cp()
    {
        return vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument.UNDEFINED;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.month;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.year;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.month;
    }

    public final String component2()
    {
        return this.year;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.month, ((vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument) p5).month)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.year, ((vn.com.vng.zalopay.bankbinding.presentation.argument.DatePickerResultArgument) p5).year)) {
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

    public final String getMonth()
    {
        return this.month;
    }

    public final String getYear()
    {
        return this.year;
    }

    public int hashCode()
    {
        return ((this.month.hashCode() * 31) + this.year.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.month;
        String v1 = this.year;
        StringBuilder v2_1 = new StringBuilder("DatePickerResultArgument(month=");
        v2_1.append(v0_0);
        v2_1.append(", year=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.month);
        p1.writeString(this.year);
        return;
    }
}

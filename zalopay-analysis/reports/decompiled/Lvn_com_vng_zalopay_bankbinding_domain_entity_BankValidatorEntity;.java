package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankValidatorEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String message;
    private final String regex;

    static BankValidatorEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity.CREATOR = new Io.S(20);
        return;
    }

    public BankValidatorEntity(String p1, String p2)
    {
        this.regex = p1;
        this.message = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.regex;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.message;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.regex;
    }

    public final String component2()
    {
        return this.message;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity copy(String p2, String p3)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.regex, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) p5).regex)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) p5).message)) {
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

    public final String getMessage()
    {
        return this.message;
    }

    public final String getRegex()
    {
        return this.regex;
    }

    public int hashCode()
    {
        return ((this.regex.hashCode() * 31) + this.message.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.regex;
        String v1 = this.message;
        StringBuilder v2_1 = new StringBuilder("BankValidatorEntity(regex=");
        v2_1.append(v0_0);
        v2_1.append(", message=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.regex);
        p1.writeString(this.message);
        return;
    }
}

package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class CardNumberGuideEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String message;

    static CardNumberGuideEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity.CREATOR = new Io.S(21);
        return;
    }

    public CardNumberGuideEntity(String p1)
    {
        this.message = p1;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity p0, String p1, int p2, Object p3)
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity copy(String p2)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity(p2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.domain.entity.CardNumberGuideEntity) p4).message)) {
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
        return this.message.hashCode();
    }

    public String toString()
    {
        String v0_0 = this.message;
        StringBuilder v1_1 = new StringBuilder("CardNumberGuideEntity(message=");
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

package vn.com.vng.zalopay.bankbinding.domain.entity.oao;
public final class ExtraInfoEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String redirectUrl;

    static ExtraInfoEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity.CREATOR = new Zn.a(3);
        return;
    }

    public ExtraInfoEntity(String p1)
    {
        this.redirectUrl = p1;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity p0, String p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.redirectUrl;
        }
        return p0.copy(p1);
    }

    public final String component1()
    {
        return this.redirectUrl;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity copy(String p2)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity(p2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.redirectUrl, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoEntity) p4).redirectUrl)) {
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

    public final String getRedirectUrl()
    {
        return this.redirectUrl;
    }

    public int hashCode()
    {
        return this.redirectUrl.hashCode();
    }

    public String toString()
    {
        String v0_0 = this.redirectUrl;
        StringBuilder v1_1 = new StringBuilder("ExtraInfoEntity(redirectUrl=");
        v1_1.append(v0_0);
        v1_1.append(")");
        return v1_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.redirectUrl);
        return;
    }
}

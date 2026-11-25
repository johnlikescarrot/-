package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankCtaEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String action;
    private final String title;
    private final String url;

    static BankCtaEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity.CREATOR = new Io.S(11);
        return;
    }

    public BankCtaEntity(String p1, String p2, String p3)
    {
        this.title = p1;
        this.url = p2;
        this.action = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity p0, String p1, String p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.url;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.action;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.title;
    }

    public final String component2()
    {
        return this.url;
    }

    public final String component3()
    {
        return this.action;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity copy(String p2, String p3, String p4)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity) p5).url)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.action, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity) p5).action)) {
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

    public final String getAction()
    {
        return this.action;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public int hashCode()
    {
        return ((((this.title.hashCode() * 31) + this.url.hashCode()) * 31) + this.action.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.url;
        String v2 = this.action;
        StringBuilder v3_1 = new StringBuilder("BankCtaEntity(title=");
        v3_1.append(v0_0);
        v3_1.append(", url=");
        v3_1.append(v1);
        v3_1.append(", action=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.title);
        p1.writeString(this.url);
        p1.writeString(this.action);
        return;
    }
}

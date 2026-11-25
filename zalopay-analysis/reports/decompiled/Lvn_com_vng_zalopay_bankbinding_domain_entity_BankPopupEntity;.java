package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankPopupEntity implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String description;
    private final String iconUrl;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity mainCta;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity subCta;
    private final String title;

    static BankPopupEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity.CREATOR = new Io.S(14);
        return;
    }

    public BankPopupEntity(String p1, String p2, String p3, vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity p4, vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity p5)
    {
        this.title = p1;
        this.description = p2;
        this.iconUrl = p3;
        this.mainCta = p4;
        this.subCta = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity p0, String p1, String p2, String p3, vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity p4, vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.description;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.iconUrl;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.mainCta;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.subCta;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.title;
    }

    public final String component2()
    {
        return this.description;
    }

    public final String component3()
    {
        return this.iconUrl;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity component4()
    {
        return this.mainCta;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity component5()
    {
        return this.subCta;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity copy(String p7, String p8, String p9, vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity p10, vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity p11)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.description, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity) p5).description)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.iconUrl, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity) p5).iconUrl)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.mainCta, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity) p5).mainCta)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.subCta, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankPopupEntity) p5).subCta)) {
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

    public final String getDescription()
    {
        return this.description;
    }

    public final String getIconUrl()
    {
        return this.iconUrl;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity getMainCta()
    {
        return this.mainCta;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity getSubCta()
    {
        return this.subCta;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity v1_2;
        int v0_2 = (((((this.title.hashCode() * 31) + this.description.hashCode()) * 31) + this.iconUrl.hashCode()) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity v1_1 = this.mainCta;
        int v2 = 0;
        if (v1_1 != null) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        int v0_5 = ((v0_2 + v1_2) * 31);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity v1_3 = this.subCta;
        if (v1_3 != null) {
            v2 = v1_3.hashCode();
        }
        return (v0_5 + v2);
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.description;
        String v2 = this.iconUrl;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity v3 = this.mainCta;
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity v4 = this.subCta;
        StringBuilder v5_1 = new StringBuilder("BankPopupEntity(title=");
        v5_1.append(v0_0);
        v5_1.append(", description=");
        v5_1.append(v1);
        v5_1.append(", iconUrl=");
        v5_1.append(v2);
        v5_1.append(", mainCta=");
        v5_1.append(v3);
        v5_1.append(", subCta=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p4, int p5)
    {
        p4.writeString(this.title);
        p4.writeString(this.description);
        p4.writeString(this.iconUrl);
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity v0_4 = this.mainCta;
        if (v0_4 != null) {
            p4.writeInt(1);
            v0_4.writeToParcel(p4, p5);
        } else {
            p4.writeInt(0);
        }
        vn.com.vng.zalopay.bankbinding.domain.entity.BankCtaEntity v0_1 = this.subCta;
        if (v0_1 != null) {
            p4.writeInt(1);
            v0_1.writeToParcel(p4, p5);
            return;
        } else {
            p4.writeInt(0);
            return;
        }
    }
}

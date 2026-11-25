package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class PageOneBackActionEntity implements android.os.Parcelable {
    public static final int $stable;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity openBankAccountPopup;

    static PageOneBackActionEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity.CREATOR = new Io.S(25);
        return;
    }

    public PageOneBackActionEntity(vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity p1)
    {
        this.openBankAccountPopup = p1;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity p0, vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.openBankAccountPopup;
        }
        return p0.copy(p1);
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity component1()
    {
        return this.openBankAccountPopup;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity copy(vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity p2)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity(p2);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.openBankAccountPopup, ((vn.com.vng.zalopay.bankbinding.domain.entity.PageOneBackActionEntity) p4).openBankAccountPopup)) {
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity getOpenBankAccountPopup()
    {
        return this.openBankAccountPopup;
    }

    public int hashCode()
    {
        int v0_0 = this.openBankAccountPopup;
        if (v0_0 != 0) {
            return v0_0.hashCode();
        } else {
            return 0;
        }
    }

    public String toString()
    {
        String v0_0 = this.openBankAccountPopup;
        StringBuilder v1_1 = new StringBuilder("PageOneBackActionEntity(openBankAccountPopup=");
        v1_1.append(v0_0);
        v1_1.append(")");
        return v1_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity v0 = this.openBankAccountPopup;
        if (v0 != null) {
            p3.writeInt(1);
            v0.writeToParcel(p3, p4);
            return;
        } else {
            p3.writeInt(0);
            return;
        }
    }
}

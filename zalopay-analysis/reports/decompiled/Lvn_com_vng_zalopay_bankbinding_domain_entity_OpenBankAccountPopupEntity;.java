package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class OpenBankAccountPopupEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String message;
    private final String title;

    static OpenBankAccountPopupEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity.CREATOR = new Io.S(24);
        return;
    }

    public OpenBankAccountPopupEntity(String p1, String p2, String p3)
    {
        this.title = p1;
        this.message = p2;
        this.bankCode = p3;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity p0, String p1, String p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.title;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.message;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.title;
    }

    public final String component2()
    {
        return this.message;
    }

    public final String component3()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity copy(String p2, String p3, String p4)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity(p2, p3, p4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity) p5).title)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity) p5).message)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.domain.entity.OpenBankAccountPopupEntity) p5).bankCode)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public int hashCode()
    {
        return ((((this.title.hashCode() * 31) + this.message.hashCode()) * 31) + this.bankCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.title;
        String v1 = this.message;
        String v2 = this.bankCode;
        StringBuilder v3_1 = new StringBuilder("OpenBankAccountPopupEntity(title=");
        v3_1.append(v0_0);
        v3_1.append(", message=");
        v3_1.append(v1);
        v3_1.append(", bankCode=");
        v3_1.append(v2);
        v3_1.append(")");
        return v3_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.title);
        p1.writeString(this.message);
        p1.writeString(this.bankCode);
        return;
    }
}

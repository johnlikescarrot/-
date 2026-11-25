package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class SuggestBankEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String bankName;
    private final String clientBankCode;
    private final String issuerBankName;

    static SuggestBankEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity.CREATOR = new Io.S(26);
        return;
    }

    public SuggestBankEntity(String p1, String p2, String p3, String p4)
    {
        this.bankCode = p1;
        this.clientBankCode = p2;
        this.bankName = p3;
        this.issuerBankName = p4;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity p0, String p1, String p2, String p3, String p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.clientBankCode;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.bankName;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.issuerBankName;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.clientBankCode;
    }

    public final String component3()
    {
        return this.bankName;
    }

    public final String component4()
    {
        return this.issuerBankName;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity copy(String p2, String p3, String p4, String p5)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity(p2, p3, p4, p5);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.clientBankCode, ((vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity) p5).clientBankCode)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity) p5).bankName)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.issuerBankName, ((vn.com.vng.zalopay.bankbinding.domain.entity.SuggestBankEntity) p5).issuerBankName)) {
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

    public final String getBankName()
    {
        return this.bankName;
    }

    public final String getClientBankCode()
    {
        return this.clientBankCode;
    }

    public final String getIssuerBankName()
    {
        return this.issuerBankName;
    }

    public int hashCode()
    {
        return ((((((this.bankCode.hashCode() * 31) + this.clientBankCode.hashCode()) * 31) + this.bankName.hashCode()) * 31) + this.issuerBankName.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.clientBankCode;
        String v2 = this.bankName;
        String v3 = this.issuerBankName;
        StringBuilder v4_1 = new StringBuilder("SuggestBankEntity(bankCode=");
        v4_1.append(v0_0);
        v4_1.append(", clientBankCode=");
        v4_1.append(v1);
        v4_1.append(", bankName=");
        v4_1.append(v2);
        v4_1.append(", issuerBankName=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.bankCode);
        p1.writeString(this.clientBankCode);
        p1.writeString(this.bankName);
        p1.writeString(this.issuerBankName);
        return;
    }
}

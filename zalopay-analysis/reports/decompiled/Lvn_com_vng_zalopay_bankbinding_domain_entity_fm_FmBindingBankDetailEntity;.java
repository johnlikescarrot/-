package vn.com.vng.zalopay.bankbinding.domain.entity.fm;
public final class FmBindingBankDetailEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String bankConnectorCode;
    private final int bankType;
    private final String bimId;
    private final String bindingId;
    private final String firstNum;
    private final String integrationId;
    private final Boolean isNeedUpgrade;
    private final String lastNum;

    static FmBindingBankDetailEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity.CREATOR = new Io.S(28);
        return;
    }

    public FmBindingBankDetailEntity(String p1, String p2, int p3, String p4, String p5, String p6, String p7, String p8, Boolean p9)
    {
        this.bankCode = p1;
        this.bankConnectorCode = p2;
        this.bankType = p3;
        this.firstNum = p4;
        this.lastNum = p5;
        this.integrationId = p6;
        this.bimId = p7;
        this.bindingId = p8;
        this.isNeedUpgrade = p9;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity p0, String p1, String p2, int p3, String p4, String p5, String p6, String p7, String p8, Boolean p9, int p10, Object p11)
    {
        if ((p10 & 1) != 0) {
            p1 = p0.bankCode;
        }
        if ((p10 & 2) != 0) {
            p2 = p0.bankConnectorCode;
        }
        if ((p10 & 4) != 0) {
            p3 = p0.bankType;
        }
        if ((p10 & 8) != 0) {
            p4 = p0.firstNum;
        }
        if ((p10 & 16) != 0) {
            p5 = p0.lastNum;
        }
        if ((p10 & 32) != 0) {
            p6 = p0.integrationId;
        }
        if ((p10 & 64) != 0) {
            p7 = p0.bimId;
        }
        if ((p10 & 128) != 0) {
            p8 = p0.bindingId;
        }
        if ((p10 & 256) != 0) {
            p9 = p0.isNeedUpgrade;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public final String component1()
    {
        return this.bankCode;
    }

    public final String component2()
    {
        return this.bankConnectorCode;
    }

    public final int component3()
    {
        return this.bankType;
    }

    public final String component4()
    {
        return this.firstNum;
    }

    public final String component5()
    {
        return this.lastNum;
    }

    public final String component6()
    {
        return this.integrationId;
    }

    public final String component7()
    {
        return this.bimId;
    }

    public final String component8()
    {
        return this.bindingId;
    }

    public final Boolean component9()
    {
        return this.isNeedUpgrade;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity copy(String p11, String p12, int p13, String p14, String p15, String p16, String p17, String p18, Boolean p19)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity(p11, p12, p13, p14, p15, p16, p17, p18, p19);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).bankCode)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankConnectorCode, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).bankConnectorCode)) {
                        if (this.bankType == ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).bankType) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.firstNum, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).firstNum)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.lastNum, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).lastNum)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.integrationId, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).integrationId)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bimId, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).bimId)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.bindingId, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).bindingId)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.isNeedUpgrade, ((vn.com.vng.zalopay.bankbinding.domain.entity.fm.FmBindingBankDetailEntity) p5).isNeedUpgrade)) {
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

    public final String getBankConnectorCode()
    {
        return this.bankConnectorCode;
    }

    public final int getBankType()
    {
        return this.bankType;
    }

    public final String getBimId()
    {
        return this.bimId;
    }

    public final String getBindingId()
    {
        return this.bindingId;
    }

    public final String getFirstNum()
    {
        return this.firstNum;
    }

    public final String getIntegrationId()
    {
        return this.integrationId;
    }

    public final String getLastNum()
    {
        return this.lastNum;
    }

    public int hashCode()
    {
        Boolean v1_6;
        int v0_7 = (((((((((this.bankCode.hashCode() * 31) + this.bankConnectorCode.hashCode()) * 31) + Integer.hashCode(this.bankType)) * 31) + this.firstNum.hashCode()) * 31) + this.lastNum.hashCode()) * 31);
        Boolean v1_5 = this.integrationId;
        int v2 = 0;
        if (v1_5 != null) {
            v1_6 = v1_5.hashCode();
        } else {
            v1_6 = 0;
        }
        Boolean v1_8;
        int v0_10 = ((v0_7 + v1_6) * 31);
        Boolean v1_7 = this.bimId;
        if (v1_7 != null) {
            v1_8 = v1_7.hashCode();
        } else {
            v1_8 = 0;
        }
        Boolean v1_11;
        int v0_12 = ((v0_10 + v1_8) * 31);
        Boolean v1_9 = this.bindingId;
        if (v1_9 != null) {
            v1_11 = v1_9.hashCode();
        } else {
            v1_11 = 0;
        }
        int v0_14 = ((v0_12 + v1_11) * 31);
        Boolean v1_12 = this.isNeedUpgrade;
        if (v1_12 != null) {
            v2 = v1_12.hashCode();
        }
        return (v0_14 + v2);
    }

    public final Boolean isNeedUpgrade()
    {
        return this.isNeedUpgrade;
    }

    public String toString()
    {
        String v0_0 = this.bankCode;
        String v1 = this.bankConnectorCode;
        int v2 = this.bankType;
        String v3 = this.firstNum;
        String v4 = this.lastNum;
        String v5 = this.integrationId;
        String v6 = this.bimId;
        String v7 = this.bindingId;
        Boolean v8 = this.isNeedUpgrade;
        StringBuilder v9_0 = new StringBuilder("FmBindingBankDetailEntity(bankCode=");
        v9_0.append(v0_0);
        v9_0.append(", bankConnectorCode=");
        v9_0.append(v1);
        v9_0.append(", bankType=");
        v9_0.append(v2);
        v9_0.append(", firstNum=");
        v9_0.append(v3);
        v9_0.append(", lastNum=");
        v9_0.append(v4);
        v9_0.append(", integrationId=");
        v9_0.append(v5);
        v9_0.append(", bimId=");
        v9_0.append(v6);
        v9_0.append(", bindingId=");
        v9_0.append(v7);
        v9_0.append(", isNeedUpgrade=");
        v9_0.append(v8);
        v9_0.append(")");
        return v9_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeString(this.bankCode);
        p2.writeString(this.bankConnectorCode);
        p2.writeInt(this.bankType);
        p2.writeString(this.firstNum);
        p2.writeString(this.lastNum);
        p2.writeString(this.integrationId);
        p2.writeString(this.bimId);
        p2.writeString(this.bindingId);
        boolean v3_4 = this.isNeedUpgrade;
        if (v3_4) {
            p2.writeInt(1);
            p2.writeInt(v3_4.booleanValue());
            return;
        } else {
            p2.writeInt(0);
            return;
        }
    }
}

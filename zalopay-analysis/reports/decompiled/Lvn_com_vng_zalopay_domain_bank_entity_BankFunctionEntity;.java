package vn.com.vng.zalopay.domain.bank.entity;
public final class BankFunctionEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final int functionCode;
    private final String maintenanceMessage;
    private final long maintenanceTo;
    private final boolean requireVerifiedKyc;
    private int status;
    private final java.util.List verifications;

    static BankFunctionEntity()
    {
        vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity.CREATOR = new iG.g(25);
        return;
    }

    public BankFunctionEntity(int p1, long p2, String p4, int p5, String p6, java.util.List p7, boolean p8)
    {
        this.functionCode = p1;
        this.maintenanceTo = p2;
        this.maintenanceMessage = p4;
        this.status = p5;
        this.bankCode = p6;
        this.verifications = p7;
        this.requireVerifiedKyc = p8;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity copy$default(vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity p0, int p1, long p2, String p4, int p5, String p6, java.util.List p7, boolean p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.functionCode;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.maintenanceTo;
        }
        if ((p9 & 4) != 0) {
            p4 = p0.maintenanceMessage;
        }
        if ((p9 & 8) != 0) {
            p5 = p0.status;
        }
        if ((p9 & 16) != 0) {
            p6 = p0.bankCode;
        }
        if ((p9 & 32) != 0) {
            p7 = p0.verifications;
        }
        if ((p9 & 64) != 0) {
            p8 = p0.requireVerifiedKyc;
        }
        return p0.copy(p1, p2, p4, p5, p6, p7, p8);
    }

    public final int component1()
    {
        return this.functionCode;
    }

    public final long component2()
    {
        return this.maintenanceTo;
    }

    public final String component3()
    {
        return this.maintenanceMessage;
    }

    public final int component4()
    {
        return this.status;
    }

    public final String component5()
    {
        return this.bankCode;
    }

    public final java.util.List component6()
    {
        return this.verifications;
    }

    public final boolean component7()
    {
        return this.requireVerifiedKyc;
    }

    public final vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity copy(int p10, long p11, String p13, int p14, String p15, java.util.List p16, boolean p17)
    {
        return new vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity(p10, p11, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity)) {
                if (this.functionCode == ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) p8).functionCode) {
                    if (this.maintenanceTo == ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) p8).maintenanceTo) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.maintenanceMessage, ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) p8).maintenanceMessage)) {
                            if (this.status == ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) p8).status) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) p8).bankCode)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.verifications, ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) p8).verifications)) {
                                        if (this.requireVerifiedKyc == ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) p8).requireVerifiedKyc) {
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
            return 1;
        }
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final int getFunctionCode()
    {
        return this.functionCode;
    }

    public final String getMaintenanceMessage()
    {
        return this.maintenanceMessage;
    }

    public final long getMaintenanceTo()
    {
        return this.maintenanceTo;
    }

    public final boolean getRequireVerifiedKyc()
    {
        return this.requireVerifiedKyc;
    }

    public final int getStatus()
    {
        return this.status;
    }

    public final java.util.List getVerifications()
    {
        return this.verifications;
    }

    public int hashCode()
    {
        return (Boolean.hashCode(this.requireVerifiedKyc) + Kb.e.h(e5.i.c(Kb.e.v(this.status, e5.i.c(Kb.e.g((Integer.hashCode(this.functionCode) * 31), 31, this.maintenanceTo), 31, this.maintenanceMessage), 31), 31, this.bankCode), 31, this.verifications));
    }

    public final void setStatus(int p1)
    {
        this.status = p1;
        return;
    }

    public String toString()
    {
        String v0_0 = this.functionCode;
        long v1 = this.maintenanceTo;
        String v3 = this.maintenanceMessage;
        int v4 = this.status;
        String v5 = this.bankCode;
        java.util.List v6 = this.verifications;
        boolean v7 = this.requireVerifiedKyc;
        StringBuilder v8_1 = new StringBuilder("BankFunctionEntity(functionCode=");
        v8_1.append(v0_0);
        v8_1.append(", maintenanceTo=");
        v8_1.append(v1);
        v8_1.append(", maintenanceMessage=");
        v8_1.append(v3);
        v8_1.append(", status=");
        v8_1.append(v4);
        v8_1.append(", bankCode=");
        v8_1.append(v5);
        v8_1.append(", verifications=");
        v8_1.append(v6);
        v8_1.append(", requireVerifiedKyc=");
        v8_1.append(v7);
        v8_1.append(")");
        return v8_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.functionCode);
        p3.writeLong(this.maintenanceTo);
        p3.writeString(this.maintenanceMessage);
        p3.writeInt(this.status);
        p3.writeString(this.bankCode);
        p3.writeStringList(this.verifications);
        p3.writeInt(this.requireVerifiedKyc);
        return;
    }
}

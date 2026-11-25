package vn.com.vng.zalopay.domain.bank.entity;
public final class BankConfigEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final java.util.List bankPmcDescription;
    private final int bankType;
    private final String cardPrefix;
    private final int displayOrder;
    private final String fullName;
    private final java.util.List functionList;
    private final int interfaceType;
    private final String loginBankUrl;
    private final String maintenanceMessage;
    private final long maintenanceTo;
    private final String minAppVersion;
    private final String name;
    private int status;
    private final java.util.List verifications;

    static BankConfigEntity()
    {
        vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity.CREATOR = new iG.g(24);
        return;
    }

    public BankConfigEntity(String p1, int p2, String p3, java.util.List p4, String p5, String p6, long p7, String p9, String p10, int p11, String p12, java.util.List p13, int p14, java.util.List p15, int p16)
    {
        this.cardPrefix = p1;
        this.displayOrder = p2;
        this.fullName = p3;
        this.functionList = p4;
        this.loginBankUrl = p5;
        this.maintenanceMessage = p6;
        this.maintenanceTo = p7;
        this.minAppVersion = p9;
        this.name = p10;
        this.status = p11;
        this.bankCode = p12;
        this.verifications = p13;
        this.interfaceType = p14;
        this.bankPmcDescription = p15;
        this.bankType = p16;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity copy$default(vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity p16, String p17, int p18, String p19, java.util.List p20, String p21, String p22, long p23, String p25, String p26, int p27, String p28, java.util.List p29, int p30, java.util.List p31, int p32, int p33, Object p34)
    {
        java.util.List v2_1;
        if ((p33 & 1) == 0) {
            v2_1 = p17;
        } else {
            v2_1 = p16.cardPrefix;
        }
        int v3_1;
        if ((p33 & 2) == 0) {
            v3_1 = p18;
        } else {
            v3_1 = p16.displayOrder;
        }
        String v4_1;
        if ((p33 & 4) == 0) {
            v4_1 = p19;
        } else {
            v4_1 = p16.fullName;
        }
        java.util.List v5_1;
        if ((p33 & 8) == 0) {
            v5_1 = p20;
        } else {
            v5_1 = p16.functionList;
        }
        String v6_1;
        if ((p33 & 16) == 0) {
            v6_1 = p21;
        } else {
            v6_1 = p16.loginBankUrl;
        }
        String v7_1;
        if ((p33 & 32) == 0) {
            v7_1 = p22;
        } else {
            v7_1 = p16.maintenanceMessage;
        }
        long v8_1;
        if ((p33 & 64) == 0) {
            v8_1 = p23;
        } else {
            v8_1 = p16.maintenanceTo;
        }
        String v10_1;
        if ((p33 & 128) == 0) {
            v10_1 = p25;
        } else {
            v10_1 = p16.minAppVersion;
        }
        String v11_1;
        if ((p33 & 256) == 0) {
            v11_1 = p26;
        } else {
            v11_1 = p16.name;
        }
        int v12_1;
        if ((p33 & 512) == 0) {
            v12_1 = p27;
        } else {
            v12_1 = p16.status;
        }
        String v13_1;
        if ((p33 & 1024) == 0) {
            v13_1 = p28;
        } else {
            v13_1 = p16.bankCode;
        }
        java.util.List v14_1;
        if ((p33 & 2048) == 0) {
            v14_1 = p29;
        } else {
            v14_1 = p16.verifications;
        }
        int v15_1;
        if ((p33 & 4096) == 0) {
            v15_1 = p30;
        } else {
            v15_1 = p16.interfaceType;
        }
        java.util.List v2_3;
        if ((p33 & 8192) == 0) {
            v2_3 = p31;
        } else {
            v2_3 = p16.bankPmcDescription;
        }
        int v33_1;
        if ((p33 & 16384) == 0) {
            v33_1 = p32;
        } else {
            v33_1 = p16.bankType;
        }
        return p16.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v33_1);
    }

    public final String component1()
    {
        return this.cardPrefix;
    }

    public final int component10()
    {
        return this.status;
    }

    public final String component11()
    {
        return this.bankCode;
    }

    public final java.util.List component12()
    {
        return this.verifications;
    }

    public final int component13()
    {
        return this.interfaceType;
    }

    public final java.util.List component14()
    {
        return this.bankPmcDescription;
    }

    public final int component15()
    {
        return this.bankType;
    }

    public final int component2()
    {
        return this.displayOrder;
    }

    public final String component3()
    {
        return this.fullName;
    }

    public final java.util.List component4()
    {
        return this.functionList;
    }

    public final String component5()
    {
        return this.loginBankUrl;
    }

    public final String component6()
    {
        return this.maintenanceMessage;
    }

    public final long component7()
    {
        return this.maintenanceTo;
    }

    public final String component8()
    {
        return this.minAppVersion;
    }

    public final String component9()
    {
        return this.name;
    }

    public final vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity copy(String p18, int p19, String p20, java.util.List p21, String p22, String p23, long p24, String p26, String p27, int p28, String p29, java.util.List p30, int p31, java.util.List p32, int p33)
    {
        return new vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity(p18, p19, p20, p21, p22, p23, p24, p26, p27, p28, p29, p30, p31, p32, p33);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardPrefix, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).cardPrefix)) {
                    if (this.displayOrder == ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).displayOrder) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.fullName, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).fullName)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.functionList, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).functionList)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.loginBankUrl, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).loginBankUrl)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.maintenanceMessage, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).maintenanceMessage)) {
                                        if (this.maintenanceTo == ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).maintenanceTo) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.minAppVersion, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).minAppVersion)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).name)) {
                                                    if (this.status == ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).status) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).bankCode)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.verifications, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).verifications)) {
                                                                if (this.interfaceType == ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).interfaceType) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankPmcDescription, ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).bankPmcDescription)) {
                                                                        if (this.bankType == ((vn.com.vng.zalopay.domain.bank.entity.BankConfigEntity) p8).bankType) {
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

    public final java.util.List getBankPmcDescription()
    {
        return this.bankPmcDescription;
    }

    public final int getBankType()
    {
        return this.bankType;
    }

    public final String getCardPrefix()
    {
        return this.cardPrefix;
    }

    public final int getDisplayOrder()
    {
        return this.displayOrder;
    }

    public final String getFullName()
    {
        return this.fullName;
    }

    public final java.util.List getFunctionList()
    {
        return this.functionList;
    }

    public final int getInterfaceType()
    {
        return this.interfaceType;
    }

    public final String getLoginBankUrl()
    {
        return this.loginBankUrl;
    }

    public final String getMaintenanceMessage()
    {
        return this.maintenanceMessage;
    }

    public final long getMaintenanceTo()
    {
        return this.maintenanceTo;
    }

    public final String getMinAppVersion()
    {
        return this.minAppVersion;
    }

    public final String getName()
    {
        return this.name;
    }

    public final String getShortBankName()
    {
        return this.name;
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
        return (Integer.hashCode(this.bankType) + Kb.e.h(Kb.e.v(this.interfaceType, Kb.e.h(e5.i.c(Kb.e.v(this.status, e5.i.c(e5.i.c(Kb.e.g(e5.i.c(e5.i.c(Kb.e.h(e5.i.c(Kb.e.v(this.displayOrder, (this.cardPrefix.hashCode() * 31), 31), 31, this.fullName), 31, this.functionList), 31, this.loginBankUrl), 31, this.maintenanceMessage), 31, this.maintenanceTo), 31, this.minAppVersion), 31, this.name), 31), 31, this.bankCode), 31, this.verifications), 31), 31, this.bankPmcDescription));
    }

    public final boolean isActive()
    {
        if (this.status != 1) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean isMaintenance()
    {
        if (this.status != 2) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void setStatus(int p1)
    {
        this.status = p1;
        return;
    }

    public String toString()
    {
        String v3 = this.fullName;
        java.util.List v4 = this.functionList;
        String v5 = this.loginBankUrl;
        String v6 = this.maintenanceMessage;
        long v7 = this.maintenanceTo;
        String v9 = this.minAppVersion;
        String v10 = this.name;
        int v11 = this.status;
        String v12 = this.bankCode;
        java.util.List v13 = this.verifications;
        java.util.List v16 = this.bankPmcDescription;
        int v17 = this.bankType;
        int v18 = this.interfaceType;
        String v0_2 = androidx.compose.foundation.layout.G0.p(this.displayOrder, "BankConfigEntity(cardPrefix=", this.cardPrefix, ", displayOrder=", ", fullName=");
        A1.a.w(v0_2, v3, ", functionList=", v4, ", loginBankUrl=");
        q9.H.p(v0_2, v5, ", maintenanceMessage=", v6, ", maintenanceTo=");
        A1.a.u(v7, ", minAppVersion=", v9, v0_2);
        v0_2.append(", name=");
        v0_2.append(v10);
        v0_2.append(", status=");
        v0_2.append(v11);
        v0_2.append(", bankCode=");
        v0_2.append(v12);
        v0_2.append(", verifications=");
        v0_2.append(v13);
        v0_2.append(", interfaceType=");
        v0_2.append(v18);
        v0_2.append(", bankPmcDescription=");
        v0_2.append(v16);
        v0_2.append(", bankType=");
        v0_2.append(v17);
        v0_2.append(")");
        return v0_2.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.cardPrefix);
        p3.writeInt(this.displayOrder);
        p3.writeString(this.fullName);
        java.util.Iterator v0_15 = this.functionList;
        p3.writeInt(v0_15.size());
        java.util.Iterator v0_1 = v0_15.iterator();
        while (v0_1.hasNext()) {
            ((vn.com.vng.zalopay.domain.bank.entity.BankFunctionEntity) v0_1.next()).writeToParcel(p3, p4);
        }
        p3.writeString(this.loginBankUrl);
        p3.writeString(this.maintenanceMessage);
        p3.writeLong(this.maintenanceTo);
        p3.writeString(this.minAppVersion);
        p3.writeString(this.name);
        p3.writeInt(this.status);
        p3.writeString(this.bankCode);
        p3.writeStringList(this.verifications);
        p3.writeInt(this.interfaceType);
        java.util.Iterator v0_12 = this.bankPmcDescription;
        p3.writeInt(v0_12.size());
        java.util.Iterator v0_13 = v0_12.iterator();
        while (v0_13.hasNext()) {
            ((kw.i) v0_13.next()).writeToParcel(p3, p4);
        }
        p3.writeInt(this.bankType);
        return;
    }
}

package vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus;
public final class BankLinkingStatusModel$SuccessModel extends vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final java.util.List cta;
    private final int functionId;
    private final String message;
    private final int resultCode;
    private final String title;
    private final String zpTransId;

    static BankLinkingStatusModel$SuccessModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel.CREATOR = new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.c();
        return;
    }

    public BankLinkingStatusModel$SuccessModel(String p2, int p3, String p4, java.util.List p5, String p6, int p7, String p8)
    {
        super(0);
        super.zpTransId = p2;
        super.resultCode = p3;
        super.message = p4;
        super.cta = p5;
        super.title = p6;
        super.functionId = p7;
        super.bankCode = p8;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel p0, String p1, int p2, String p3, java.util.List p4, String p5, int p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.zpTransId;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.resultCode;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.message;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.cta;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.title;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.functionId;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.bankCode;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.zpTransId;
    }

    public final int component2()
    {
        return this.resultCode;
    }

    public final String component3()
    {
        return this.message;
    }

    public final java.util.List component4()
    {
        return this.cta;
    }

    public final String component5()
    {
        return this.title;
    }

    public final int component6()
    {
        return this.functionId;
    }

    public final String component7()
    {
        return this.bankCode;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel copy(String p9, int p10, String p11, java.util.List p12, String p13, int p14, String p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel) p5).zpTransId)) {
                    if (this.resultCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel) p5).resultCode) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel) p5).message)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cta, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel) p5).cta)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel) p5).title)) {
                                    if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel) p5).functionId) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.linkstatus.BankLinkingStatusModel$SuccessModel) p5).bankCode)) {
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

    public final java.util.List getCta()
    {
        return this.cta;
    }

    public final int getFunctionId()
    {
        return this.functionId;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final int getResultCode()
    {
        return this.resultCode;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((((((((this.zpTransId.hashCode() * 31) + Integer.hashCode(this.resultCode)) * 31) + this.message.hashCode()) * 31) + this.cta.hashCode()) * 31) + this.title.hashCode()) * 31) + Integer.hashCode(this.functionId)) * 31) + this.bankCode.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.zpTransId;
        int v1 = this.resultCode;
        String v2 = this.message;
        java.util.List v3 = this.cta;
        String v4 = this.title;
        int v5 = this.functionId;
        String v6 = this.bankCode;
        StringBuilder v7_1 = new StringBuilder("SuccessModel(zpTransId=");
        v7_1.append(v0_0);
        v7_1.append(", resultCode=");
        v7_1.append(v1);
        v7_1.append(", message=");
        v7_1.append(v2);
        v7_1.append(", cta=");
        v7_1.append(v3);
        v7_1.append(", title=");
        v7_1.append(v4);
        v7_1.append(", functionId=");
        v7_1.append(v5);
        v7_1.append(", bankCode=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.zpTransId);
        p3.writeInt(this.resultCode);
        p3.writeString(this.message);
        java.util.Iterator v0_4 = this.cta;
        p3.writeInt(v0_4.size());
        java.util.Iterator v0_1 = v0_4.iterator();
        while (v0_1.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.CtaEntity) v0_1.next()).writeToParcel(p3, p4);
        }
        p3.writeString(this.title);
        p3.writeInt(this.functionId);
        p3.writeString(this.bankCode);
        return;
    }
}

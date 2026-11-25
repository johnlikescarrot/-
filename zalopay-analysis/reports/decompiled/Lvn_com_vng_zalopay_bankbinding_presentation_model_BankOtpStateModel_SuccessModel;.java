package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankOtpStateModel$SuccessModel extends uo.z implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final java.util.List cta;
    private final String message;
    private final int resultCode;
    private final String title;
    private final String zpTransId;

    static BankOtpStateModel$SuccessModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel.CREATOR = new uo.y(7);
        return;
    }

    public BankOtpStateModel$SuccessModel(String p1, int p2, String p3, java.util.List p4, String p5)
    {
        this.zpTransId = p1;
        this.resultCode = p2;
        this.message = p3;
        this.cta = p4;
        this.title = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel p0, String p1, int p2, String p3, java.util.List p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.zpTransId;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.resultCode;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.message;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.cta;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.title;
        }
        return p0.copy(p1, p2, p3, p4, p5);
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

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel copy(String p7, int p8, String p9, java.util.List p10, String p11)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel) p5).zpTransId)) {
                    if (this.resultCode == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel) p5).resultCode) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel) p5).message)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cta, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel) p5).cta)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankOtpStateModel$SuccessModel) p5).title)) {
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

    public final java.util.List getCta()
    {
        return this.cta;
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
        return ((((((((this.zpTransId.hashCode() * 31) + Integer.hashCode(this.resultCode)) * 31) + this.message.hashCode()) * 31) + this.cta.hashCode()) * 31) + this.title.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.zpTransId;
        int v1 = this.resultCode;
        String v2 = this.message;
        java.util.List v3 = this.cta;
        String v4 = this.title;
        StringBuilder v5_1 = new StringBuilder("SuccessModel(zpTransId=");
        v5_1.append(v0_0);
        v5_1.append(", resultCode=");
        v5_1.append(v1);
        v5_1.append(", message=");
        v5_1.append(v2);
        v5_1.append(", cta=");
        v5_1.append(v3);
        v5_1.append(", title=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
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
        return;
    }
}

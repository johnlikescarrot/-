package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankSubmitStateModel$BankRopWebModel extends uo.F implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String accountNumber;
    private final String additionalAction;
    private final String bankCode;
    private final String cardHolderName;
    private final String cardNumber;
    private final String url;
    private final String zpTransId;

    static BankSubmitStateModel$BankRopWebModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel.CREATOR = new uo.y(17);
        return;
    }

    public BankSubmitStateModel$BankRopWebModel(String p1, String p2, String p3, String p4, String p5, String p6, String p7)
    {
        this.zpTransId = p1;
        this.additionalAction = p2;
        this.bankCode = p3;
        this.cardNumber = p4;
        this.accountNumber = p5;
        this.cardHolderName = p6;
        this.url = p7;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel p0, String p1, String p2, String p3, String p4, String p5, String p6, String p7, int p8, Object p9)
    {
        if ((p8 & 1) != 0) {
            p1 = p0.zpTransId;
        }
        if ((p8 & 2) != 0) {
            p2 = p0.additionalAction;
        }
        if ((p8 & 4) != 0) {
            p3 = p0.bankCode;
        }
        if ((p8 & 8) != 0) {
            p4 = p0.cardNumber;
        }
        if ((p8 & 16) != 0) {
            p5 = p0.accountNumber;
        }
        if ((p8 & 32) != 0) {
            p6 = p0.cardHolderName;
        }
        if ((p8 & 64) != 0) {
            p7 = p0.url;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7);
    }

    public final String component1()
    {
        return this.zpTransId;
    }

    public final String component2()
    {
        return this.additionalAction;
    }

    public final String component3()
    {
        return this.bankCode;
    }

    public final String component4()
    {
        return this.cardNumber;
    }

    public final String component5()
    {
        return this.accountNumber;
    }

    public final String component6()
    {
        return this.cardHolderName;
    }

    public final String component7()
    {
        return this.url;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel copy(String p9, String p10, String p11, String p12, String p13, String p14, String p15)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel(p9, p10, p11, p12, p13, p14, p15);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel) p5).zpTransId)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.additionalAction, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel) p5).additionalAction)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel) p5).bankCode)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardNumber, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel) p5).cardNumber)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.accountNumber, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel) p5).accountNumber)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardHolderName, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel) p5).cardHolderName)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.url, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankSubmitStateModel$BankRopWebModel) p5).url)) {
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

    public final String getAccountNumber()
    {
        return this.accountNumber;
    }

    public final String getAdditionalAction()
    {
        return this.additionalAction;
    }

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getCardHolderName()
    {
        return this.cardHolderName;
    }

    public final String getCardNumber()
    {
        return this.cardNumber;
    }

    public final String getUrl()
    {
        return this.url;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        return ((((((((((((this.zpTransId.hashCode() * 31) + this.additionalAction.hashCode()) * 31) + this.bankCode.hashCode()) * 31) + this.cardNumber.hashCode()) * 31) + this.accountNumber.hashCode()) * 31) + this.cardHolderName.hashCode()) * 31) + this.url.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.zpTransId;
        String v1 = this.additionalAction;
        String v2 = this.bankCode;
        String v3 = this.cardNumber;
        String v4 = this.accountNumber;
        String v5 = this.cardHolderName;
        String v6 = this.url;
        StringBuilder v7_1 = new StringBuilder("BankRopWebModel(zpTransId=");
        v7_1.append(v0_0);
        v7_1.append(", additionalAction=");
        v7_1.append(v1);
        v7_1.append(", bankCode=");
        v7_1.append(v2);
        v7_1.append(", cardNumber=");
        v7_1.append(v3);
        v7_1.append(", accountNumber=");
        v7_1.append(v4);
        v7_1.append(", cardHolderName=");
        v7_1.append(v5);
        v7_1.append(", url=");
        v7_1.append(v6);
        v7_1.append(")");
        return v7_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.zpTransId);
        p1.writeString(this.additionalAction);
        p1.writeString(this.bankCode);
        p1.writeString(this.cardNumber);
        p1.writeString(this.accountNumber);
        p1.writeString(this.cardHolderName);
        p1.writeString(this.url);
        return;
    }
}

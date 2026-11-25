package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingFieldModel$CardNumberModel extends uo.c {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String hint;
    private final int id;
    private final String label;
    private final int maxLength;
    private final int order;
    private final String placeHolder;

    static BankBindingFieldModel$CardNumberModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel.CREATOR = new no.e(20);
        return;
    }

    public BankBindingFieldModel$CardNumberModel(int p2, String p3, int p4, String p5, int p6, String p7)
    {
        super(p2, p6, "");
        super.id = p2;
        super.hint = p3;
        super.maxLength = p4;
        super.label = p5;
        super.order = p6;
        super.placeHolder = p7;
        return;
    }

    public synthetic BankBindingFieldModel$CardNumberModel(int p8, String p9, int p10, String p11, int p12, String p13, int p14, kotlin.jvm.internal.DefaultConstructorMarker p15)
    {
        if ((p14 & 16) != 0) {
            p12 = 0;
        }
        this(p8, p9, p10, p11, p12, p13);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel p0, int p1, String p2, int p3, String p4, int p5, String p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.hint;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.maxLength;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.label;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.order;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.placeHolder;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final int component1()
    {
        return this.id;
    }

    public final String component2()
    {
        return this.hint;
    }

    public final int component3()
    {
        return this.maxLength;
    }

    public final String component4()
    {
        return this.label;
    }

    public final int component5()
    {
        return this.order;
    }

    public final String component6()
    {
        return this.placeHolder;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel copy(int p8, String p9, int p10, String p11, int p12, String p13)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel)) {
                if (this.id == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel) p5).id) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.hint, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel) p5).hint)) {
                        if (this.maxLength == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel) p5).maxLength) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.label, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel) p5).label)) {
                                if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel) p5).order) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.placeHolder, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$CardNumberModel) p5).placeHolder)) {
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
            return 1;
        }
    }

    public final String getHint()
    {
        return this.hint;
    }

    public int getId()
    {
        return this.id;
    }

    public final String getLabel()
    {
        return this.label;
    }

    public final int getMaxLength()
    {
        return this.maxLength;
    }

    public int getOrder()
    {
        return this.order;
    }

    public final String getPlaceHolder()
    {
        return this.placeHolder;
    }

    public int hashCode()
    {
        return ((((((((((Integer.hashCode(this.id) * 31) + this.hint.hashCode()) * 31) + Integer.hashCode(this.maxLength)) * 31) + this.label.hashCode()) * 31) + Integer.hashCode(this.order)) * 31) + this.placeHolder.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        String v1 = this.hint;
        int v2 = this.maxLength;
        String v3 = this.label;
        int v4 = this.order;
        String v5 = this.placeHolder;
        StringBuilder v6_1 = new StringBuilder("CardNumberModel(id=");
        v6_1.append(v0_0);
        v6_1.append(", hint=");
        v6_1.append(v1);
        v6_1.append(", maxLength=");
        v6_1.append(v2);
        v6_1.append(", label=");
        v6_1.append(v3);
        v6_1.append(", order=");
        v6_1.append(v4);
        v6_1.append(", placeHolder=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeInt(this.id);
        p1.writeString(this.hint);
        p1.writeInt(this.maxLength);
        p1.writeString(this.label);
        p1.writeInt(this.order);
        p1.writeString(this.placeHolder);
        return;
    }
}

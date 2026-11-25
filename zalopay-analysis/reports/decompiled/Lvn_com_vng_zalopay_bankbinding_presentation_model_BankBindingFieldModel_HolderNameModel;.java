package vn.com.vng.zalopay.bankbinding.presentation.model;
public final class BankBindingFieldModel$HolderNameModel extends uo.c {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final int functionId;
    private final String hint;
    private final int id;
    private final String label;
    private final int maxLength;
    private final int order;
    private final String placeholder;
    private final java.util.List validators;

    static BankBindingFieldModel$HolderNameModel()
    {
        vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel.CREATOR = new no.e(24);
        return;
    }

    public BankBindingFieldModel$HolderNameModel(int p2, int p3, java.util.List p4, int p5, int p6, String p7, String p8, String p9)
    {
        super(p2, p5, "");
        super.id = p2;
        super.maxLength = p3;
        super.validators = p4;
        super.order = p5;
        super.functionId = p6;
        super.label = p7;
        super.hint = p8;
        super.placeholder = p9;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel copy$default(vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel p0, int p1, int p2, java.util.List p3, int p4, int p5, String p6, String p7, String p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.maxLength;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.validators;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.order;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.functionId;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.label;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.hint;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.placeholder;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final int component1()
    {
        return this.id;
    }

    public final int component2()
    {
        return this.maxLength;
    }

    public final java.util.List component3()
    {
        return this.validators;
    }

    public final int component4()
    {
        return this.order;
    }

    public final int component5()
    {
        return this.functionId;
    }

    public final String component6()
    {
        return this.label;
    }

    public final String component7()
    {
        return this.hint;
    }

    public final String component8()
    {
        return this.placeholder;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel copy(int p10, int p11, java.util.List p12, int p13, int p14, String p15, String p16, String p17)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel)) {
                if (this.id == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).id) {
                    if (this.maxLength == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).maxLength) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.validators, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).validators)) {
                            if (this.order == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).order) {
                                if (this.functionId == ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).functionId) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.label, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).label)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.hint, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).hint)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.placeholder, ((vn.com.vng.zalopay.bankbinding.presentation.model.BankBindingFieldModel$HolderNameModel) p5).placeholder)) {
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
            return 1;
        }
    }

    public final int getFunctionId()
    {
        return this.functionId;
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

    public final String getPlaceholder()
    {
        return this.placeholder;
    }

    public final java.util.List getValidators()
    {
        return this.validators;
    }

    public int hashCode()
    {
        return ((((((((((((((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.maxLength)) * 31) + this.validators.hashCode()) * 31) + Integer.hashCode(this.order)) * 31) + Integer.hashCode(this.functionId)) * 31) + this.label.hashCode()) * 31) + this.hint.hashCode()) * 31) + this.placeholder.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        int v1 = this.maxLength;
        java.util.List v2 = this.validators;
        int v3 = this.order;
        int v4 = this.functionId;
        String v5 = this.label;
        String v6 = this.hint;
        String v7 = this.placeholder;
        StringBuilder v8_0 = new StringBuilder("HolderNameModel(id=");
        v8_0.append(v0_0);
        v8_0.append(", maxLength=");
        v8_0.append(v1);
        v8_0.append(", validators=");
        v8_0.append(v2);
        v8_0.append(", order=");
        v8_0.append(v3);
        v8_0.append(", functionId=");
        v8_0.append(v4);
        v8_0.append(", label=");
        v8_0.append(v5);
        v8_0.append(", hint=");
        v8_0.append(v6);
        v8_0.append(", placeholder=");
        v8_0.append(v7);
        v8_0.append(")");
        return v8_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeInt(this.id);
        p3.writeInt(this.maxLength);
        java.util.Iterator v0_2 = this.validators;
        p3.writeInt(v0_2.size());
        java.util.Iterator v0_3 = v0_2.iterator();
        while (v0_3.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.BankValidatorEntity) v0_3.next()).writeToParcel(p3, p4);
        }
        p3.writeInt(this.order);
        p3.writeInt(this.functionId);
        p3.writeString(this.label);
        p3.writeString(this.hint);
        p3.writeString(this.placeholder);
        return;
    }
}

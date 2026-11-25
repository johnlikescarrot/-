package vn.com.vng.zalopay.bankbinding.domain.repository.params;
public final class BankBindingInfoParams implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankNumber;
    private final String cvv;
    private final String expiryDate;
    private final String holderName;
    private final String issueData;
    private final java.util.Map terms;

    static BankBindingInfoParams()
    {
        vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams.CREATOR = new Zn.a(13);
        return;
    }

    public BankBindingInfoParams(String p1, String p2, String p3, String p4, String p5, java.util.Map p6)
    {
        this.bankNumber = p1;
        this.issueData = p2;
        this.expiryDate = p3;
        this.cvv = p4;
        this.holderName = p5;
        this.terms = p6;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams copy$default(vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams p0, String p1, String p2, String p3, String p4, String p5, java.util.Map p6, int p7, Object p8)
    {
        if ((p7 & 1) != 0) {
            p1 = p0.bankNumber;
        }
        if ((p7 & 2) != 0) {
            p2 = p0.issueData;
        }
        if ((p7 & 4) != 0) {
            p3 = p0.expiryDate;
        }
        if ((p7 & 8) != 0) {
            p4 = p0.cvv;
        }
        if ((p7 & 16) != 0) {
            p5 = p0.holderName;
        }
        if ((p7 & 32) != 0) {
            p6 = p0.terms;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6);
    }

    public final String component1()
    {
        return this.bankNumber;
    }

    public final String component2()
    {
        return this.issueData;
    }

    public final String component3()
    {
        return this.expiryDate;
    }

    public final String component4()
    {
        return this.cvv;
    }

    public final String component5()
    {
        return this.holderName;
    }

    public final java.util.Map component6()
    {
        return this.terms;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams copy(String p8, String p9, String p10, String p11, String p12, java.util.Map p13)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams(p8, p9, p10, p11, p12, p13);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankNumber, ((vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams) p5).bankNumber)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.issueData, ((vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams) p5).issueData)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.expiryDate, ((vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams) p5).expiryDate)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.cvv, ((vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams) p5).cvv)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.holderName, ((vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams) p5).holderName)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.terms, ((vn.com.vng.zalopay.bankbinding.domain.repository.params.BankBindingInfoParams) p5).terms)) {
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

    public final String getBankNumber()
    {
        return this.bankNumber;
    }

    public final String getCvv()
    {
        return this.cvv;
    }

    public final String getExpiryDate()
    {
        return this.expiryDate;
    }

    public final String getHolderName()
    {
        return this.holderName;
    }

    public final String getIssueData()
    {
        return this.issueData;
    }

    public final java.util.Map getTerms()
    {
        return this.terms;
    }

    public int hashCode()
    {
        int v0_10;
        int v0_0 = this.bankNumber;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_10 = v0_0.hashCode();
        } else {
            v0_10 = 0;
        }
        java.util.Map v2_0;
        int v0_11 = (v0_10 * 31);
        java.util.Map v2_8 = this.issueData;
        if (v2_8 != null) {
            v2_0 = v2_8.hashCode();
        } else {
            v2_0 = 0;
        }
        java.util.Map v2_2;
        int v0_2 = ((v0_11 + v2_0) * 31);
        java.util.Map v2_1 = this.expiryDate;
        if (v2_1 != null) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        java.util.Map v2_4;
        int v0_4 = ((v0_2 + v2_2) * 31);
        java.util.Map v2_3 = this.cvv;
        if (v2_3 != null) {
            v2_4 = v2_3.hashCode();
        } else {
            v2_4 = 0;
        }
        java.util.Map v2_6;
        int v0_6 = ((v0_4 + v2_4) * 31);
        java.util.Map v2_5 = this.holderName;
        if (v2_5 != null) {
            v2_6 = v2_5.hashCode();
        } else {
            v2_6 = 0;
        }
        int v0_8 = ((v0_6 + v2_6) * 31);
        java.util.Map v2_7 = this.terms;
        if (v2_7 != null) {
            v1 = v2_7.hashCode();
        }
        return (v0_8 + v1);
    }

    public String toString()
    {
        String v0_0 = this.bankNumber;
        String v1 = this.issueData;
        String v2 = this.expiryDate;
        String v3 = this.cvv;
        String v4 = this.holderName;
        java.util.Map v5 = this.terms;
        StringBuilder v6_1 = new StringBuilder("BankBindingInfoParams(bankNumber=");
        v6_1.append(v0_0);
        v6_1.append(", issueData=");
        v6_1.append(v1);
        v6_1.append(", expiryDate=");
        v6_1.append(v2);
        v6_1.append(", cvv=");
        v6_1.append(v3);
        v6_1.append(", holderName=");
        v6_1.append(v4);
        v6_1.append(", terms=");
        v6_1.append(v5);
        v6_1.append(")");
        return v6_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.bankNumber);
        p3.writeString(this.issueData);
        p3.writeString(this.expiryDate);
        p3.writeString(this.cvv);
        p3.writeString(this.holderName);
        java.util.Iterator v4_1 = this.terms;
        if (v4_1 != null) {
            p3.writeInt(1);
            p3.writeInt(v4_1.size());
            java.util.Iterator v4_4 = v4_1.entrySet().iterator();
            while (v4_4.hasNext()) {
                boolean v0_4 = ((java.util.Map$Entry) v4_4.next());
                p3.writeString(((String) v0_4.getKey()));
                p3.writeInt(((Boolean) v0_4.getValue()).booleanValue());
            }
            return;
        } else {
            p3.writeInt(0);
            return;
        }
    }
}

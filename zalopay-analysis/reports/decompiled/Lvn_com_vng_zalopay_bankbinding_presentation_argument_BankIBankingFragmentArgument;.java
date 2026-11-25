package vn.com.vng.zalopay.bankbinding.presentation.argument;
public final class BankIBankingFragmentArgument implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final String bankCode;
    private final String bankName;
    private final String id;
    private final String information;
    private final Integer suggestionType;
    private final String tabLabel;
    private final String title;
    private final java.util.List toggleView;

    static BankIBankingFragmentArgument()
    {
        vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument.CREATOR = new Zn.a(23);
        return;
    }

    public BankIBankingFragmentArgument(String p1, String p2, String p3, String p4, String p5, String p6, Integer p7, java.util.List p8)
    {
        this.id = p1;
        this.information = p2;
        this.tabLabel = p3;
        this.title = p4;
        this.bankCode = p5;
        this.bankName = p6;
        this.suggestionType = p7;
        this.toggleView = p8;
        return;
    }

    public synthetic BankIBankingFragmentArgument(String p11, String p12, String p13, String p14, String p15, String p16, Integer p17, java.util.List p18, int p19, kotlin.jvm.internal.DefaultConstructorMarker p20)
    {
        Integer v8;
        if ((p19 & 64) == 0) {
            v8 = p17;
        } else {
            v8 = 0;
        }
        this(p11, p12, p13, p14, p15, p16, v8, p18);
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument copy$default(vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument p0, String p1, String p2, String p3, String p4, String p5, String p6, Integer p7, java.util.List p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.information;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.tabLabel;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.title;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.bankCode;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.bankName;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.suggestionType;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.toggleView;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.id;
    }

    public final String component2()
    {
        return this.information;
    }

    public final String component3()
    {
        return this.tabLabel;
    }

    public final String component4()
    {
        return this.title;
    }

    public final String component5()
    {
        return this.bankCode;
    }

    public final String component6()
    {
        return this.bankName;
    }

    public final Integer component7()
    {
        return this.suggestionType;
    }

    public final java.util.List component8()
    {
        return this.toggleView;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument copy(String p10, String p11, String p12, String p13, String p14, String p15, Integer p16, java.util.List p17)
    {
        return new vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.id, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).id)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.information, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).information)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.tabLabel, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).tabLabel)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).title)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankCode, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).bankCode)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.bankName, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).bankName)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.suggestionType, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).suggestionType)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.toggleView, ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIBankingFragmentArgument) p5).toggleView)) {
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

    public final String getBankCode()
    {
        return this.bankCode;
    }

    public final String getBankName()
    {
        return this.bankName;
    }

    public final String getId()
    {
        return this.id;
    }

    public final String getInformation()
    {
        return this.information;
    }

    public final Integer getSuggestionType()
    {
        return this.suggestionType;
    }

    public final String getTabLabel()
    {
        return this.tabLabel;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public final java.util.List getToggleView()
    {
        return this.toggleView;
    }

    public int hashCode()
    {
        int v1_8;
        int v0_9 = (((((((((((this.id.hashCode() * 31) + this.information.hashCode()) * 31) + this.tabLabel.hashCode()) * 31) + this.title.hashCode()) * 31) + this.bankCode.hashCode()) * 31) + this.bankName.hashCode()) * 31);
        int v1_7 = this.suggestionType;
        if (v1_7 != 0) {
            v1_8 = v1_7.hashCode();
        } else {
            v1_8 = 0;
        }
        return (((v0_9 + v1_8) * 31) + this.toggleView.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.id;
        String v1 = this.information;
        String v2 = this.tabLabel;
        String v3 = this.title;
        String v4 = this.bankCode;
        String v5 = this.bankName;
        Integer v6 = this.suggestionType;
        java.util.List v7 = this.toggleView;
        StringBuilder v8_0 = new StringBuilder("BankIBankingFragmentArgument(id=");
        v8_0.append(v0_0);
        v8_0.append(", information=");
        v8_0.append(v1);
        v8_0.append(", tabLabel=");
        v8_0.append(v2);
        v8_0.append(", title=");
        v8_0.append(v3);
        v8_0.append(", bankCode=");
        v8_0.append(v4);
        v8_0.append(", bankName=");
        v8_0.append(v5);
        v8_0.append(", suggestionType=");
        v8_0.append(v6);
        v8_0.append(", toggleView=");
        v8_0.append(v7);
        v8_0.append(")");
        return v8_0.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        java.util.Iterator v0_3;
        p3.writeString(this.id);
        p3.writeString(this.information);
        p3.writeString(this.tabLabel);
        p3.writeString(this.title);
        p3.writeString(this.bankCode);
        p3.writeString(this.bankName);
        java.util.Iterator v0_2 = this.suggestionType;
        if (v0_2 != null) {
            p3.writeInt(1);
            v0_3 = v0_2.intValue();
        } else {
            v0_3 = 0;
        }
        p3.writeInt(v0_3);
        java.util.Iterator v0_5 = this.toggleView;
        p3.writeInt(v0_5.size());
        java.util.Iterator v0_6 = v0_5.iterator();
        while (v0_6.hasNext()) {
            ((vn.com.vng.zalopay.bankbinding.domain.entity.select.ToggleViewEntity) v0_6.next()).writeToParcel(p3, p4);
        }
        return;
    }
}

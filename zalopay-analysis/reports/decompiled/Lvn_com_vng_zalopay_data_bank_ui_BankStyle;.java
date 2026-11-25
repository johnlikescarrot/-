package vn.com.vng.zalopay.data.bank.ui;
public final class BankStyle implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    public static final Kt.a Companion;
    private static final vn.com.vng.zalopay.data.bank.ui.BankStyle DEFAULT;
    private final String backgroundUrl;
    private final String endColor;
    private final String iconName;
    private final String iconUrl;
    private final String key;
    private final String name;
    private final String onCardIcon;
    private final String startColor;

    static BankStyle()
    {
        vn.com.vng.zalopay.data.bank.ui.BankStyle.Companion = new Kt.a();
        vn.com.vng.zalopay.data.bank.ui.BankStyle.CREATOR = new Ew.g(21);
        vn.com.vng.zalopay.data.bank.ui.BankStyle.DEFAULT = new vn.com.vng.zalopay.data.bank.ui.BankStyle("", "#afb5b5", "#d7d8d3", "", 0, 0, 0, 0, 240, 0);
        return;
    }

    public BankStyle(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8)
    {
        this.key = p1;
        this.startColor = p2;
        this.endColor = p3;
        this.iconName = p4;
        this.onCardIcon = p5;
        this.name = p6;
        this.iconUrl = p7;
        this.backgroundUrl = p8;
        return;
    }

    public synthetic BankStyle(String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, int p10, kotlin.jvm.internal.DefaultConstructorMarker p11)
    {
        if ((p10 & 16) != 0) {
            p6 = "";
        }
        if ((p10 & 32) != 0) {
            p7 = "";
        }
        if ((p10 & 64) != 0) {
            p8 = "";
        }
        String v10_2;
        if ((p10 & 128) == 0) {
            v10_2 = p9;
        } else {
            v10_2 = "";
        }
        this(p2, p3, p4, p5, p6, p7, p8, v10_2);
        return;
    }

    public static final synthetic vn.com.vng.zalopay.data.bank.ui.BankStyle access$getDEFAULT$cp()
    {
        return vn.com.vng.zalopay.data.bank.ui.BankStyle.DEFAULT;
    }

    public static synthetic vn.com.vng.zalopay.data.bank.ui.BankStyle copy$default(vn.com.vng.zalopay.data.bank.ui.BankStyle p0, String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, int p9, Object p10)
    {
        if ((p9 & 1) != 0) {
            p1 = p0.key;
        }
        if ((p9 & 2) != 0) {
            p2 = p0.startColor;
        }
        if ((p9 & 4) != 0) {
            p3 = p0.endColor;
        }
        if ((p9 & 8) != 0) {
            p4 = p0.iconName;
        }
        if ((p9 & 16) != 0) {
            p5 = p0.onCardIcon;
        }
        if ((p9 & 32) != 0) {
            p6 = p0.name;
        }
        if ((p9 & 64) != 0) {
            p7 = p0.iconUrl;
        }
        if ((p9 & 128) != 0) {
            p8 = p0.backgroundUrl;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public final String component1()
    {
        return this.key;
    }

    public final String component2()
    {
        return this.startColor;
    }

    public final String component3()
    {
        return this.endColor;
    }

    public final String component4()
    {
        return this.iconName;
    }

    public final String component5()
    {
        return this.onCardIcon;
    }

    public final String component6()
    {
        return this.name;
    }

    public final String component7()
    {
        return this.iconUrl;
    }

    public final String component8()
    {
        return this.backgroundUrl;
    }

    public final vn.com.vng.zalopay.data.bank.ui.BankStyle copy(String p10, String p11, String p12, String p13, String p14, String p15, String p16, String p17)
    {
        return new vn.com.vng.zalopay.data.bank.ui.BankStyle(p10, p11, p12, p13, p14, p15, p16, p17);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.data.bank.ui.BankStyle)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.key, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).key)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.startColor, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).startColor)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.endColor, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).endColor)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.iconName, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).iconName)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.onCardIcon, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).onCardIcon)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).name)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.iconUrl, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).iconUrl)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.backgroundUrl, ((vn.com.vng.zalopay.data.bank.ui.BankStyle) p5).backgroundUrl)) {
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

    public final String getBackgroundUrl()
    {
        return this.backgroundUrl;
    }

    public final String getEndColor()
    {
        return this.endColor;
    }

    public final String getIconName()
    {
        return this.iconName;
    }

    public final String getIconUrl()
    {
        return this.iconUrl;
    }

    public final String getKey()
    {
        return this.key;
    }

    public final String getName()
    {
        return this.name;
    }

    public final String getOnCardIcon()
    {
        return this.onCardIcon;
    }

    public final String getStartColor()
    {
        return this.startColor;
    }

    public int hashCode()
    {
        int v2_2;
        int v0_2 = e5.i.c(e5.i.c(e5.i.c((this.key.hashCode() * 31), 31, this.startColor), 31, this.endColor), 31, this.iconName);
        int v2_1 = this.onCardIcon;
        int v3 = 0;
        if (v2_1 != 0) {
            v2_2 = v2_1.hashCode();
        } else {
            v2_2 = 0;
        }
        int v2_4;
        int v0_5 = ((v0_2 + v2_2) * 31);
        int v2_3 = this.name;
        if (v2_3 != 0) {
            v2_4 = v2_3.hashCode();
        } else {
            v2_4 = 0;
        }
        int v2_6;
        int v0_7 = ((v0_5 + v2_4) * 31);
        int v2_5 = this.iconUrl;
        if (v2_5 != 0) {
            v2_6 = v2_5.hashCode();
        } else {
            v2_6 = 0;
        }
        int v0_9 = ((v0_7 + v2_6) * 31);
        String v1_1 = this.backgroundUrl;
        if (v1_1 != null) {
            v3 = v1_1.hashCode();
        }
        return (v0_9 + v3);
    }

    public String toString()
    {
        String v2_1 = this.endColor;
        String v3 = this.iconName;
        String v4 = this.onCardIcon;
        String v5 = this.name;
        String v6 = this.iconUrl;
        String v7 = this.backgroundUrl;
        String v0_1 = androidx.camera.camera2.internal.G.i("BankStyle(key=", this.key, ", startColor=", this.startColor, ", endColor=");
        q9.H.p(v0_1, v2_1, ", iconName=", v3, ", onCardIcon=");
        q9.H.p(v0_1, v4, ", name=", v5, ", iconUrl=");
        return androidx.compose.foundation.layout.G0.n(v0_1, v6, ", backgroundUrl=", v7, ")");
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.key);
        p1.writeString(this.startColor);
        p1.writeString(this.endColor);
        p1.writeString(this.iconName);
        p1.writeString(this.onCardIcon);
        p1.writeString(this.name);
        p1.writeString(this.iconUrl);
        p1.writeString(this.backgroundUrl);
        return;
    }
}

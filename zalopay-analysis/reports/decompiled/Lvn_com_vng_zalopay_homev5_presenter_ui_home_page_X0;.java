package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class X0 {
    public final java.util.List a;
    public final java.util.List b;
    public final boolean c;
    public final String d;
    public final int e;
    public final BB.G f;
    public final OB.j g;
    public final boolean h;

    public X0(java.util.List p1, java.util.List p2, boolean p3, String p4, int p5, BB.G p6, OB.j p7, boolean p8)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        this.g = p7;
        this.h = p8;
        return;
    }

    public static vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 a(vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0 p9, java.util.List p10, java.util.ArrayList p11, boolean p12, String p13, int p14, BB.G p15, OB.j p16, int p17)
    {
        if ((p17 & 1) != 0) {
            p10 = p9.a;
        }
        if ((p17 & 2) != 0) {
            p11 = p9.b;
        }
        if ((p17 & 4) != 0) {
            p12 = p9.c;
        }
        if ((p17 & 8) != 0) {
            p13 = p9.d;
        }
        if ((p17 & 16) != 0) {
            p14 = p9.e;
        }
        if ((p17 & 32) != 0) {
            p15 = p9.f;
        }
        OB.j v7;
        if ((p17 & 64) == 0) {
            v7 = p16;
        } else {
            v7 = p9.g;
        }
        int v10_7;
        if ((p17 & 128) == 0) {
            v10_7 = 0;
        } else {
            v10_7 = p9.h;
        }
        int v8 = v10_7;
        p9.getClass();
        return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0(p10, p11, p12, p13, p14, p15, v7, v8);
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).b)) {
                        if (this.c == ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).c) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).d)) {
                                if (this.e == ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).e) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).f)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).g)) {
                                            if (this.h == ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.X0) p5).h) {
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

    public final int hashCode()
    {
        OB.j v3_1;
        int v0_1 = Kb.e.i(Kb.e.h((this.a.hashCode() * 31), 31, this.b), 31, this.c);
        int v2_0 = 0;
        OB.j v3_0 = this.d;
        if (v3_0 != null) {
            v3_1 = v3_0.hashCode();
        } else {
            v3_1 = 0;
        }
        OB.j v3_4;
        int v0_5 = Kb.e.v(this.e, ((v0_1 + v3_1) * 31), 31);
        OB.j v3_3 = this.f;
        if (v3_3 != null) {
            v3_4 = v3_3.hashCode();
        } else {
            v3_4 = 0;
        }
        int v0_7 = ((v0_5 + v3_4) * 31);
        OB.j v3_5 = this.g;
        if (v3_5 != null) {
            v2_0 = v3_5.hashCode();
        }
        return (Boolean.hashCode(this.h) + ((v0_7 + v2_0) * 31));
    }

    public final String toString()
    {
        String v0_1 = new StringBuilder("HomePageUiState(components=");
        v0_1.append(this.a);
        v0_1.append(", ads=");
        v0_1.append(this.b);
        v0_1.append(", showNetworkWarning=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.A(v0_1, this.c, ", highlightedNewsfeedKey=", this.d, ", hiddenBottomHeight=");
        v0_1.append(this.e);
        v0_1.append(", floatingCampaign=");
        v0_1.append(this.f);
        v0_1.append(", popup=");
        v0_1.append(this.g);
        v0_1.append(", showTooltipTransactionHistory=");
        v0_1.append(this.h);
        v0_1.append(")");
        return v0_1.toString();
    }
}

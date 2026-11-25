package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class l {
    public final boolean a;
    public final String b;
    public final String c;
    public final long d;
    public final String e;
    public final boolean f;
    public final iV.x g;

    public l(boolean p1, String p2, String p3, long p4, String p6, boolean p7, iV.x p8)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p6;
        this.f = p7;
        this.g = p8;
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.l a(vn.com.zalopay.ekycpro.presentation.nfc.state.l p9, boolean p10, String p11, String p12, long p13, String p15, boolean p16, iV.x p17, int p18)
    {
        if ((p18 & 1) != 0) {
            p10 = p9.a;
        }
        if ((p18 & 2) != 0) {
            p11 = p9.b;
        }
        if ((p18 & 4) != 0) {
            p12 = p9.c;
        }
        if ((p18 & 8) != 0) {
            p13 = p9.d;
        }
        if ((p18 & 16) != 0) {
            p15 = p9.e;
        }
        boolean v7;
        if ((p18 & 32) == 0) {
            v7 = p16;
        } else {
            v7 = p9.f;
        }
        iV.x v8;
        if ((p18 & 64) == 0) {
            v8 = p17;
        } else {
            v8 = p9.g;
        }
        p9.getClass();
        return new vn.com.zalopay.ekycpro.presentation.nfc.state.l(p10, p11, p12, p13, p15, v7, v8);
    }

    public final boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.l)) {
                if (this.a == ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) p8).a) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) p8).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) p8).c)) {
                            if (this.d == ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) p8).d) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) p8).e)) {
                                    if (this.f == ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) p8).f) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) p8).g)) {
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

    public final int hashCode()
    {
        int v1_1;
        int v0_4 = Kb.e.i(e5.i.c(Kb.e.g(e5.i.c(e5.i.c((Boolean.hashCode(this.a) * 31), 31, this.b), 31, this.c), 31, this.d), 31, this.e), 31, this.f);
        int v1_0 = this.g;
        if (v1_0 != 0) {
            v1_1 = v1_0.hashCode();
        } else {
            v1_1 = 0;
        }
        return (v0_4 + v1_1);
    }

    public final String toString()
    {
        String v0_2 = com.google.android.gms.internal.play_billing.c1.n("NfcOnBehalfState(isLoading=", ", phoneErrorText=", this.b, ", deeplink=", this.a);
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.t(this.d, this.c, ", expireAtMs=", v0_2);
        v0_2.append(", expiredDisplay=");
        v0_2.append(this.e);
        v0_2.append(", showPendingTray=");
        v0_2.append(this.f);
        v0_2.append(", errorPopup=");
        v0_2.append(this.g);
        v0_2.append(")");
        return v0_2.toString();
    }
}

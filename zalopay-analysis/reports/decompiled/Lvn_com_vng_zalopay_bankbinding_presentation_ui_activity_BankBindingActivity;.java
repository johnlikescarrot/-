package vn.com.vng.zalopay.bankbinding.presentation.ui.activity;
public final class BankBindingActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity implements Eo.a {
    public static final synthetic int D;
    public final ji.u0 A;
    public final ji.u0 B;
    public final OD.a C;
    public final int k;
    public final ng.S l;
    public androidx.compose.ui.platform.ComposeView m;
    public android.widget.LinearLayout n;
    public android.widget.TextView o;
    public ro.a p;
    public ji.u0 q;
    public ji.u0 r;
    public dZ.f s;
    public To.b t;
    public final O5.b u;
    public aP.a v;
    public Jo.a w;
    public Z2.L x;
    public String y;
    public final ji.u0 z;

    public BankBindingActivity()
    {
        this.k = -2079850496;
        this.l = ng.x.b(new Fo.a(this, 0));
        this.u = new O5.b(kotlin.jvm.internal.M.a.b(Ro.i), new Fo.j(this, 0), new Fo.a(this, 1), new Fo.j(this, 1));
        this.z = ji.j0.c("");
        OD.a v0_7 = ji.j0.c(new Ew.e(Ao.b.b));
        this.A = v0_7;
        this.B = v0_7;
        this.C = new OD.a(0, -1);
        return;
    }

    public final int e1()
    {
        return this.k;
    }

    public final OD.a g1()
    {
        return this.C;
    }

    public final vn.com.vng.zalopay.feature.argument.BankBindingArgs j1()
    {
        return ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) this.l.getValue());
    }

    public final ro.a m()
    {
        int v0_0 = this.p;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0;
        }
    }

    public final void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        if ((p2 == 151) && (p3 == -1)) {
            String v3_2;
            if (p4 == null) {
                v3_2 = 0;
            } else {
                String v3_7 = p4.getStringArrayExtra("resultStr");
                if (v3_7 == null) {
                } else {
                    String v3_1 = kotlin.collections.y.toList(v3_7);
                    if (v3_1 == null) {
                    } else {
                        v3_2 = ((String) kotlin.collections.CollectionsKt.firstOrNull(v3_1));
                    }
                }
            }
            Ew.e v4_2 = new Ew.e(new Ao.a(v3_2));
            ji.u0 v0_3 = this.A;
            v0_3.getClass();
            v0_3.l(0, v4_2);
            if (v3_2 == null) {
                m9.Y3.a("Not receive scan result", new NullPointerException("Scan result is null"));
            }
        }
        return;
    }

    public final void onCreate(android.os.Bundle p14)
    {
        Class v2_4;
        if (p14 == null) {
            v2_4 = 0;
        } else {
            v2_4 = 1;
        }
        if (n9.O.a == null) {
            n9.O.a = new Iz.b(new jQ.B(7), q9.a5.b());
        }
        String v3_5 = Ao.c.a;
        if ((v2_4 == null) || (v3_5 == null)) {
            String v3_6 = vn.com.vng.zalopay.AndroidApplication.g;
            if (v3_6 == null) {
                v3_6 = 0;
            }
            String v7_3 = v3_6.b;
            if (v7_3 != null) {
                int v8_3 = ji.j0.c(uo.t.a);
                int v9_1 = this.j1();
                int v10_1 = ji.j0.c(uo.i.a);
                Jo.a v12_1 = new Jo.a();
                v9_1.getClass();
                ji.u0 v11 = this.B;
                v11.getClass();
                boolean v5_9 = new ro.d(new Bo.p(), v7_3, v8_3, v9_1, v10_1, v11, v12_1);
                v5_9.w(this);
                this.p = v5_9;
                Ao.c.a = v5_9;
            } else {
                this.finish();
            }
        } else {
            this.p = v3_5;
            v3_5.w(this);
        }
        super.onCreate(p14);
        this.m = ((androidx.compose.ui.platform.ComposeView) this.findViewById(-2079915927));
        this.y = this.getString(-2079719378);
        this.z.setValue(this.getString(-2079719378));
        UnsupportedOperationException v14_58 = this.m;
        if (v14_58 != null) {
            v14_58.setContent(new y0.b(new Fo.b(0, this), 1, -9698574));
        }
        this.n = ((android.widget.LinearLayout) this.findViewById(-2079915879));
        this.o = ((android.widget.TextView) this.findViewById(-2079915792));
        UnsupportedOperationException v14_65 = this.getWindow();
        String v0_48 = v14_65.getDecorView();
        androidx.core.view.O.a(v0_48, new h8.i(v0_48, v14_65, 4));
        androidx.lifecycle.l0.i(this.getLifecycle()).a(new Fo.e(this, 0, 2));
        this.x = ((androidx.navigation.fragment.NavHostFragment) this.getSupportFragmentManager().D(-2079915866)).m1();
        androidx.lifecycle.l0.i(this.getLifecycle()).a(new Fo.e(this, 0, 0));
        UnsupportedOperationException v14_6 = this.v;
        if (v14_6 == null) {
            v14_6 = 0;
        }
        v14_6.d.f(this, new Fo.i(0, new Fo.c(0, this)));
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new Fo.e(this, 0, 1), 3, 0);
        if (v2_4 == null) {
            UnsupportedOperationException v14_11 = this.j1().getService();
            String v7_1 = uo.d.a;
            switch (v14_11.hashCode()) {
                case -2059315061:
                    if (v14_11.equals("SUBMIT_FORM")) {
                        UnsupportedOperationException v14_48 = this.x;
                        if (v14_48 == null) {
                            v14_48 = 0;
                        }
                        String v0_42 = new android.os.Bundle();
                        v14_48.getClass();
                        v14_48.k(-2079916021, v0_42, 0);
                        return;
                    } else {
                    }
                case -1465589975:
                    if (v14_11.equals("BANK_SELECTION")) {
                        UnsupportedOperationException v14_46 = this.x;
                        if (v14_46 == null) {
                            v14_46 = 0;
                        }
                        String v0_39 = new android.os.Bundle();
                        v14_46.getClass();
                        v14_46.k(-2079916014, v0_39, 0);
                        return;
                    } else {
                    }
                case -1401353257:
                    if (v14_11.equals("LINK_STATUS")) {
                    } else {
                    }
                    break;
                case -962247819:
                    if (v14_11.equals("OPEN_ACC_AND_LINK")) {
                        UnsupportedOperationException v14_43 = this.x;
                        if (v14_43 == null) {
                            v14_43 = 0;
                        }
                        String v0_33 = this.j1().getBankCode();
                        v14_43.getClass();
                        android.os.Bundle v1_7 = new android.os.Bundle();
                        v1_7.putString("bank_code", v0_33);
                        v1_7.putString("source", 0);
                        v14_43.k(-2079915991, v1_7, 0);
                        return;
                    } else {
                    }
                case 78603:
                    if (v14_11.equals("OTP")) {
                        UnsupportedOperationException v14_33 = this.r;
                        if (v14_33 == null) {
                            v14_33 = 0;
                        }
                        UnsupportedOperationException v14_36;
                        v14_33.getClass();
                        v14_33.l(0, v7_1);
                        UnsupportedOperationException v14_35 = this.j1().getExtraArgument();
                        if (!(v14_35 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument)) {
                            v14_36 = 0;
                        } else {
                            v14_36 = ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument) v14_35);
                        }
                        if (v14_36 != null) {
                            String v0_26 = this.x;
                            if (v0_26 == null) {
                                v0_26 = 0;
                            }
                            v0_26.getClass();
                            android.os.Bundle v1_5 = new android.os.Bundle();
                            if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument)) {
                                if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument)) {
                                    throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankOtpArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                                } else {
                                    v1_5.putSerializable("bank_otp_args", ((java.io.Serializable) v14_36));
                                }
                            } else {
                                v1_5.putParcelable("bank_otp_args", v14_36);
                            }
                            v0_26.k(-2079915990, v1_5, 0);
                            return;
                        } else {
                            UnsupportedOperationException v14_41 = this.x;
                            if (v14_41 == null) {
                                v14_41 = 0;
                            }
                            String v0_30 = new android.os.Bundle();
                            v14_41.getClass();
                            v14_41.k(-2079916014, v0_30, 0);
                            return;
                        }
                    } else {
                    }
                case 2336762:
                    if (v14_11.equals("LINK")) {
                        if (this.j1().getBankCode().length() <= 0) {
                            UnsupportedOperationException v14_28 = this.j1();
                            String v0_18 = new StringBuilder("[Bank] Start link without bank code params: ");
                            v0_18.append(v14_28);
                            String v0_19 = new Object[0];
                            ck.b.e(v0_18.toString(), v0_19);
                            UnsupportedOperationException v14_30 = this.x;
                            if (v14_30 == null) {
                                v14_30 = 0;
                            }
                            String v0_21 = new android.os.Bundle();
                            v14_30.getClass();
                            v14_30.k(-2079916014, v0_21, 0);
                            return;
                        } else {
                            UnsupportedOperationException v14_31 = this.x;
                            if (v14_31 == null) {
                                v14_31 = 0;
                            }
                            String v0_23 = new android.os.Bundle();
                            v14_31.getClass();
                            v14_31.k(-2079916021, v0_23, 0);
                            return;
                        }
                    } else {
                    }
                case 1171628955:
                    if (v14_11.equals("WEB_THREE_DS")) {
                        UnsupportedOperationException v14_15 = this.r;
                        if (v14_15 == null) {
                            v14_15 = 0;
                        }
                        UnsupportedOperationException v14_18;
                        v14_15.getClass();
                        v14_15.l(0, v7_1);
                        UnsupportedOperationException v14_17 = this.j1().getExtraArgument();
                        if (!(v14_17 instanceof vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument)) {
                            v14_18 = 0;
                        } else {
                            v14_18 = ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument) v14_17);
                        }
                        if (v14_18 != null) {
                            String v0_11 = this.x;
                            if (v0_11 == null) {
                                v0_11 = 0;
                            }
                            v0_11.getClass();
                            android.os.Bundle v1_3 = new android.os.Bundle();
                            if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument)) {
                                if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument)) {
                                    throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebAppArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
                                } else {
                                    v1_3.putSerializable("bank_web_app_argument", ((java.io.Serializable) v14_18));
                                }
                            } else {
                                v1_3.putParcelable("bank_web_app_argument", v14_18);
                            }
                            v0_11.k(-2079915989, v1_3, 0);
                            return;
                        } else {
                            UnsupportedOperationException v14_23 = this.x;
                            if (v14_23 == null) {
                                v14_23 = 0;
                            }
                            String v0_15 = new android.os.Bundle();
                            v14_23.getClass();
                            v14_23.k(-2079916014, v0_15, 0);
                            return;
                        }
                    } else {
                    }
                case 1878252455:
                    if (v14_11.equals("BANK_OAO_SELECTION")) {
                        UnsupportedOperationException v14_13 = this.x;
                        if (v14_13 == null) {
                            v14_13 = 0;
                        }
                        String v0_8 = new android.os.Bundle();
                        v14_13.getClass();
                        v14_13.k(-2079915984, v0_8, 0);
                        return;
                    } else {
                    }
                default:
            }
            UnsupportedOperationException v14_50 = this.j1().getService();
            String v0_44 = new StringBuilder("[BankBinding] Invalid service type ");
            v0_44.append(v14_50);
            String v0_45 = new Object[0];
            ck.b.e(v0_44.toString(), v0_45);
            UnsupportedOperationException v14_52 = this.x;
            if (v14_52 == null) {
                v14_52 = 0;
            }
            String v0_47 = new android.os.Bundle();
            v14_52.getClass();
            v14_52.k(-2079916014, v0_47, 0);
            return;
        } else {
            return;
        }
    }

    public final void onRequestPermissionsResult(int p1, String[] p2, int[] p3)
    {
        super.onRequestPermissionsResult(p1, p2, p3);
        if ((p1 == 11) && (!com.google.android.gms.internal.mlkit_vision_barcode.u0.e(p3))) {
            Object[] v1_3 = new Object[0];
            ck.b.e("[Bank] Not grant camera permission", v1_3);
        }
        return;
    }

    public final void onRestoreInstanceState(android.os.Bundle p2)
    {
        super.onRestoreInstanceState(p2);
        int v0 = this.x;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.v(p2);
        return;
    }

    public final void onSaveInstanceState(android.os.Bundle p2)
    {
        android.os.Bundle v0_0 = this.x;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        p2.putAll(v0_0.x());
        super.onSaveInstanceState(p2);
        return;
    }
}

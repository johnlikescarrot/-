package vn.com.vng.zalopay.protect.ui;
public class ProtectAccountFragment extends Ts.d implements vn.com.vng.zalopay.protect.ui.a, DZ.o$a {
    android.view.View mProtectAccountView;
    android.widget.TextView mProtectDescView;
    androidx.appcompat.widget.SwitchCompat mSwcProtectAccountView;
    androidx.appcompat.widget.SwitchCompat mSwcRequireConfirmPassword;
    androidx.appcompat.widget.SwitchCompat mSwcTouchIdView;
    android.widget.TextView mTvLockTime;
    android.widget.TextView mTvLockTimeDescription;
    android.view.View mTvManageDeviceLogin;
    android.view.View mTvTouchIdView;
    android.view.View mVgManageDeviceLogin;
    android.view.View mVgSetupAutoLockingTime;
    android.view.View mVgTouchIdView;
    public Yv.i v;
    android.view.View viewTermConditions;
    public Xt.b w;
    public DZ.o x;
    public vn.com.vng.zalopay.protect.ui.g y;
    public boolean z;

    public ProtectAccountFragment()
    {
        this.z = 1;
        return;
    }

    public static void r1(String p1)
    {
        JP.a.a(p1, java.util.Collections.EMPTY_MAP);
        return;
    }

    public final void K0()
    {
        String v0_0 = this.y;
        DZ.o v1_5 = v0_0.e.v();
        java.util.ArrayList v3_1 = new java.util.ArrayList();
        String v0_2 = ((uu.a) v0_0.g.get()).a.iterator();
        while (v0_2.hasNext()) {
            int v4_2 = ((is.d) v0_2.next());
            if (v1_5 != v4_2.b()) {
                v3_1.add(new DZ.d(v4_2.a(), v4_2.b(), 0));
            } else {
                v3_1.add(new DZ.d(v4_2.a(), v4_2.b(), 1));
            }
        }
        String v0_4 = new android.os.Bundle();
        v0_4.putParcelableArrayList("pref_multiple_choice_dialog", v3_1);
        DZ.o v1_3 = new DZ.o();
        v1_3.setArguments(v0_4);
        this.x = v1_3;
        v1_3.setTargetFragment(this, 1);
        String v0_5 = this.getActivity();
        if ((this.getFragmentManager() != null) && ((v0_5 != null) && (!v0_5.isFinishing()))) {
            this.x.v1(this.getFragmentManager(), v0_5.getLocalClassName());
        }
        return;
    }

    public final void P0(boolean p3)
    {
        androidx.appcompat.widget.SwitchCompat v0 = this.mSwcTouchIdView;
        if (v0 != null) {
            v0.post(new vn.com.vng.zalopay.protect.ui.c(this, p3));
        }
        return;
    }

    public final void R()
    {
        this.mVgTouchIdView.setVisibility(8);
        this.mTvTouchIdView.setVisibility(8);
        this.mProtectDescView.setText(2131954237);
        this.mTvLockTimeDescription.setText(2131954707);
        return;
    }

    public final void U0()
    {
        UR.a.c(11866, 0, 0, 0);
        return;
    }

    public final void h0(long p1)
    {
        this.q1(p1);
        return;
    }

    public final void k0()
    {
        this.mVgManageDeviceLogin.setVisibility(8);
        this.mTvManageDeviceLogin.setVisibility(8);
        return;
    }

    public final void m0(DZ.d p6)
    {
        try {
            long v0 = p6.b;
            this.q1(v0);
            this.y.e.x(v0);
        } catch (Exception) {
            return;
        }
        if (v0 != 0) {
            if (v0 != 30000) {
                if (v0 != 60000) {
                    if (v0 != 300000) {
                        if (v0 != -1) {
                            return;
                        } else {
                            UR.a.c(11865, 0, 0, 0);
                            return;
                        }
                    } else {
                        UR.a.c(11864, 0, 0, 0);
                        return;
                    }
                } else {
                    UR.a.c(11863, 0, 0, 0);
                    return;
                }
            } else {
                UR.a.c(11862, 0, 0, 0);
                return;
            }
        } else {
            UR.a.c(11861, 0, 0, 0);
            return;
        }
    }

    public final int m1()
    {
        return 2131558956;
    }

    public final void n1()
    {
        q9.a5.b().R(this);
        return;
    }

    public void onClickChangePassword()
    {
        vn.com.vng.zalopay.protect.ui.ProtectAccountFragment.r1("01.1701.001");
        q9.p0.a(this).k(2131362721, 0, 0);
        return;
    }

    public void onClickChangePhone()
    {
        android.net.Uri v0_1 = new java.util.HashMap();
        v0_1.put("os", "Android");
        UR.a.e("01.1711.999", v0_1);
        android.net.Uri v0_3 = ((Xt.a) this.w.get()).c;
        if (v0_3 != null) {
            android.net.Uri v0_5 = ((String) v0_3.get("update_phone_number_deeplink"));
            if (v0_5 != null) {
                q9.X4.b(this.v, this.getContext(), com.google.android.gms.internal.mlkit_vision_barcode.E0.a(v0_5, java.util.Collections.singletonMap("source", "setting")));
            } else {
                Yv.i v1_5 = new Object[0];
                ck.b.e("Update Phone deeplink is null", v1_5);
            }
        } else {
            Yv.i v1_6 = new Object[0];
            ck.b.e("Cannot launch update phone feature by deeplink", v1_6);
        }
        vn.com.vng.zalopay.protect.ui.ProtectAccountFragment.r1("01.1701.003");
        return;
    }

    public void onClickLoginDevices()
    {
        android.content.Intent v0_0 = this.y;
        android.content.Context v1 = this.getContext();
        v0_0.d.getClass();
        v1.startActivity(new android.content.Intent(v1, vn.com.vng.zalopay.logindevice.LoginDevicesActivity));
        return;
    }

    public void onClickLogout()
    {
        vn.com.vng.zalopay.protect.ui.ProtectAccountFragment.r1("01.1701.007");
        String v0_1 = this.y;
        String v1_0 = v0_1.c;
        if ((v0_1.E0() != null) && (!v0_1.E0().isFinishing())) {
            java.util.HashMap v2_2 = new DZ.x(v0_1.E0(), 0, 2132018254);
            v2_2.f(v1_0.getString(2131955631));
            v2_2.c(v0_1.E0().getString(2131955647));
            v2_2.g(v0_1.E0().getString(2131952318));
            v2_2.d(v0_1.E0().getString(2131954739));
            v2_2.E = new vn.com.vng.zalopay.protect.ui.e(v0_1, 0);
            v2_2.F = new vn.com.vng.zalopay.protect.ui.e(v0_1, 2);
            v2_2.show();
            java.util.HashMap v2_5 = new java.util.HashMap();
            v2_5.put("device_id", v0_1.k.c(v1_0));
            v2_5.put("initialize_time ", Long.valueOf(System.currentTimeMillis()));
            UR.a.e("01.1729.000", v2_5);
        }
        return;
    }

    public void onClickSetupAutoLockingTime()
    {
        if (this.z) {
            this.z = 0;
            UR.a.c(11860, 0, 0, 0);
            vn.com.vng.zalopay.protect.ui.a v0_7 = this.y;
            if (v0_7.b != null) {
                if (v0_7.e.v() >= 0) {
                    if ((v0_7.E0() != null) && (!v0_7.E0().isFinishing())) {
                        n9.O.c(v0_7.E0(), new pS.f(HS.c.b, "from_protect_account_feature", 0, java.util.Collections.EMPTY_MAP, 1), new vn.com.vng.zalopay.protect.ui.f(v0_7));
                    }
                } else {
                    ((vn.com.vng.zalopay.protect.ui.a) v0_7.b).K0();
                }
            }
        }
        eQ.a.g(new vn.com.vng.zalopay.protect.ui.b(this, 1), 500);
        return;
    }

    public void onClickTermConditions()
    {
        vn.com.vng.zalopay.protect.ui.ProtectAccountFragment.r1("01.1701.004");
        return;
    }

    public void onClickTermsConditions()
    {
        this.y.d.j(this.getContext(), 1, "https://zalopay.vn/quy-dinh/thoa-thuan-su-dung-zalopay");
        return;
    }

    public void onClickTouchPointResetPassCode()
    {
        vn.com.vng.zalopay.protect.ui.ProtectAccountFragment.r1("01.1701.002");
        m9.p.f(this.requireActivity());
        return;
    }

    public final void onDestroyView()
    {
        this.y.e0();
        DZ.o v0_1 = this.x;
        if (v0_1 != null) {
            v0_1.J = 0;
        }
        com.google.android.gms.internal.mlkit_vision_barcode.h7.a();
        super.onDestroyView();
        return;
    }

    public final void onPause()
    {
        this.y.getClass();
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        this.y.getClass();
        return;
    }

    public boolean onTouchTouchId()
    {
        if (this.z) {
            this.z = 0;
            String v0_6 = this.mSwcTouchIdView.isChecked();
            android.app.Activity v2_5 = this.y;
            if ((v2_5.b != null) && (v2_5.E0() != null)) {
                v2_5.E0().isFinishing();
            }
            String v0_1;
            android.app.Activity v2_2 = new java.util.HashMap();
            if (v0_6 != null) {
                v0_1 = "off";
            } else {
                v0_1 = "on";
            }
            v2_2.put("bio", v0_1);
            JP.a.a("01.1701.005", v2_2);
        }
        eQ.a.g(new vn.com.vng.zalopay.protect.ui.b(this, 0), 200);
        return 0;
    }

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        this.y.b = this;
        n9.O.a = new Iz.b(new jQ.B(7), q9.a5.b());
        vn.com.vng.zalopay.protect.ui.a v4_2 = this.y;
        boolean v5_1 = v4_2.e;
        if ((v4_2.b != null) && ((v4_2.E0() != null) && (!v4_2.E0().isFinishing()))) {
            ((vn.com.vng.zalopay.protect.ui.a) v4_2.b).h0(v5_1.v());
            if (com.facebook.applinks.b.w(v4_2.E0()).m(15) != 0) {
                ((vn.com.vng.zalopay.protect.ui.a) v4_2.b).R();
            }
            ((vn.com.vng.zalopay.protect.ui.a) v4_2.b).P0(HS.d.a.a(v4_2.E0(), v5_1.getUserId()));
            boolean v5_4 = hs.d.a;
            if (v5_4) {
                boolean v5_6 = v5_4.x;
                if ((v5_6) && (v5_6.a)) {
                    vn.com.vng.zalopay.protect.ui.ProtectAccountFragment.r1("01.1701.000");
                    return;
                }
            }
            vn.com.vng.zalopay.protect.ui.a v4_4 = v4_2.b;
            if (v4_4 != null) {
                ((vn.com.vng.zalopay.protect.ui.a) v4_4).k0();
            }
        }
        vn.com.vng.zalopay.protect.ui.ProtectAccountFragment.r1("01.1701.000");
        return;
    }

    public final void q1(long p6)
    {
        String v0_7 = ((uu.a) this.y.g.get()).a;
        Object v2_0 = 0;
        if (!com.google.android.gms.internal.mlkit_vision_barcode.p0.b(v0_7)) {
            String v0_2 = v0_7.iterator();
            while (v0_2.hasNext()) {
                Object v1_1 = v0_2.next();
                if (((is.d) v1_1).b() == p6) {
                    v2_0 = v1_1;
                    break;
                }
            }
        }
        Object v2_1 = ((is.d) v2_0);
        if (v2_1 != null) {
            String v6_5;
            if (p6 > 0) {
                this.mTvLockTime.setText(String.format(this.getString(2131954705), new Object[] {v2_1.a()})));
                v6_5 = "ON";
            } else {
                this.mTvLockTime.setText(v2_1.a());
                v6_5 = "OFF";
            }
            java.util.HashMap v7_5 = new java.util.HashMap();
            v7_5.put("status", v6_5);
            JP.a.a("01.1701.006", v7_5);
            return;
        } else {
            return;
        }
    }
}

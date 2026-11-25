package vn.com.vng.zalopay.internal;
public final class n implements Pt.n {
    public static final Object h;
    public final St.a a;
    public Ew.D b;
    public final org.greenrobot.eventbus.e c;
    public final Yu.d d;
    public boolean e;
    public java.util.Map f;
    public io.reactivex.subjects.f g;

    static n()
    {
        vn.com.vng.zalopay.internal.n.h = new Object();
        return;
    }

    public n(St.a p2, org.greenrobot.eventbus.e p3, Yu.d p4)
    {
        this.f = java.util.Collections.EMPTY_MAP;
        this.a = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final void A(Gw.a p3)
    {
        this.a.b(p3.a, "pref_user_status_profile_level3");
        if (this.g == null) {
            this.g = new io.reactivex.subjects.b().f();
        }
        this.g.onNext(new nc.d(26));
        return;
    }

    public final String B()
    {
        return this.a.getString("pref_user_avatar", "");
    }

    public final void C()
    {
        St.a v1 = this.a;
        v1.remove("EncryptedPassword");
        v1.remove("EncryptedPasswordIV");
        v1.remove("pref_state_checkbox_fppassword");
        return;
    }

    public final void D(Ew.D p6)
    {
        if ((p6 != null) && (!android.text.TextUtils.isEmpty(p6.l))) {
            Yu.d v2_1 = this.a;
            v2_1.a("pref_user_email", p6.o);
            v2_1.a("pref_user_name", p6.f);
            v2_1.a("pref_user_avatar", p6.g);
            v2_1.b(p6.r, "profilelevel");
            v2_1.b(p6.v, "kyc_level");
            v2_1.c(p6.d, "pref_user_phone");
            v2_1.a("pref_user_permanent_address", p6.q);
            v2_1.b(p6.u.intValue(), "pref_user_login_method");
            Yu.d v2_0 = this.d;
            v2_0.a("pref_user_id", p6.c);
            v2_0.a("pref_user_session", p6.l);
            String v6_1 = p6.m;
            if (v6_1 == null) {
                v6_1 = "";
            }
            v2_0.a("pref_user_zpw_session", v6_1);
        }
        return;
    }

    public final Gw.a E()
    {
        Gw.a v0_1 = this.a.getInt("pref_user_status_profile_level3", 0);
        Gw.a.b.getClass();
        return la.f.h(v0_1);
    }

    public final String F()
    {
        String v0_0 = this.b;
        if (v0_0 == null) {
            return "";
        } else {
            return v0_0.c;
        }
    }

    public final void G(boolean p3)
    {
        this.a.d(androidx.camera.camera2.internal.G.e("pref_meta_consent_active_", this.getUserId()), p3);
        return;
    }

    public final void H()
    {
        this.f = java.util.Collections.EMPTY_MAP;
        this.a.remove("pref_custom_reset_pin_out_app_header");
        return;
    }

    public final void I(String p3)
    {
        Yu.d v0_0 = this.b;
        if (v0_0 != null) {
            v0_0.l = p3;
        }
        this.d.a("pref_user_session", p3);
        return;
    }

    public final void J(long p3)
    {
        this.a.c(p3, "pref_last_time_actived");
        return;
    }

    public final void K(Ew.D p2)
    {
        try {
            this.b = p2;
            return;
        } catch (Throwable v2_1) {
            throw v2_1;
        }
    }

    public final void L(boolean p3)
    {
        this.e = p3;
        this.a.d("pref_drsite", p3);
        return;
    }

    public final java.util.Map M()
    {
        if (this.f.isEmpty()) {
            java.util.Map v0_7 = this.a.getString("pref_custom_reset_pin_out_app_header", 0);
            if ((v0_7 != null) && (!v0_7.isEmpty())) {
                return ((java.util.Map) nY.h.b(v0_7, new vn.com.vng.zalopay.internal.UserConfigImpl$1().getType()));
            } else {
                return java.util.Collections.EMPTY_MAP;
            }
        } else {
            return this.f;
        }
    }

    public final String N()
    {
        return this.a.getString("pref_user_name", "");
    }

    public final String O()
    {
        return this.a.getString("pref_last_uid", "");
    }

    public final long a()
    {
        return this.a.getLong("pref_zalo_id", -1);
    }

    public final boolean b()
    {
        return this.e;
    }

    public final String c()
    {
        String v0_0 = this.b;
        if (v0_0 == null) {
            return "";
        } else {
            return v0_0.l;
        }
    }

    public final String d()
    {
        String v0_0 = this.b;
        if (v0_0 == null) {
            return this.d.getString("pref_user_zpw_session");
        } else {
            String v0_3 = v0_0.m;
            if (v0_3 == null) {
                v0_3 = "";
            }
            return v0_3;
        }
    }

    public final void e(String p3, String p4)
    {
        St.a v1 = this.a;
        v1.a("EncryptedPassword", p3);
        v1.a("EncryptedPasswordIV", p4);
        return;
    }

    public final String f()
    {
        return this.a.getString("EncryptedPassword", "");
    }

    public final long g()
    {
        return this.a.getLong("pref_user_phone", 0);
    }

    public final String getSession()
    {
        String v0_0 = this.b;
        if (v0_0 == null) {
            return this.d.getString("pref_user_session");
        } else {
            return v0_0.l;
        }
    }

    public final String getUserId()
    {
        String v0_0 = this.b;
        if (v0_0 == null) {
            return this.d.getString("pref_user_id");
        } else {
            return v0_0.c;
        }
    }

    public final void h(String p3)
    {
        this.a.a("pref_previous_uid", p3);
        return;
    }

    public final boolean i()
    {
        try {
            int v1_1;
            if (this.b == null) {
                v1_1 = 0;
            } else {
                v1_1 = 1;
            }
        } catch (int v1_2) {
            throw v1_2;
        }
        return v1_1;
    }

    public final boolean j()
    {
        return this.a.getBoolean(androidx.camera.camera2.internal.G.e("pref_meta_consent_active_", this.getUserId()), 0);
    }

    public final void k(int p3)
    {
        St.a v0_0 = this.b;
        if (v0_0 != null) {
            v0_0.v = p3;
        }
        this.a.b(p3, "kyc_level");
        return;
    }

    public final void l()
    {
        Yu.d v1_0 = this.a;
        v1_0.remove("pref_user_avatar");
        v1_0.remove("pref_user_email");
        v1_0.remove("pref_user_name");
        v1_0.remove("pref_zalo_id");
        v1_0.remove("pref_user_phone");
        v1_0.remove("profilelevel");
        v1_0.remove("kyc_level");
        v1_0.remove("pref_user_birth_date");
        v1_0.remove("pref_user_identity");
        v1_0.remove("pref_zalopay_name");
        v1_0.remove("pref_use_protect_profile");
        v1_0.remove("pref_link_bank_last_index");
        v1_0.remove("pref_user_permanent_address");
        v1_0.remove("pref_not_display_warning_next_time");
        v1_0.remove("pref_quickpay_list_paymentcode");
        v1_0.remove("pref_quickpay_intro_appearance");
        v1_0.remove("pref_require_confirm_password");
        v1_0.remove("pref_user_status_profile_level3");
        v1_0.remove("pref_quickpay_voucher_guide");
        Yu.d v1_1 = this.d;
        v1_1.remove("pref_user_id");
        v1_1.remove("pref_user_session");
        v1_1.remove("pref_user_zpw_session");
        return;
    }

    public final void m(boolean p3)
    {
        this.a.d("pref_state_checkbox_fppassword", p3);
        return;
    }

    public final boolean n()
    {
        return (android.text.TextUtils.isEmpty(this.getSession()) ^ 1);
    }

    public final void o(String p3)
    {
        org.greenrobot.eventbus.e v0_0 = this.b;
        if (v0_0 != null) {
            v0_0.e = p3;
            this.a.a("pref_zalopay_name", p3);
            this.c.f(new w5.c());
            return;
        } else {
            return;
        }
    }

    public final Ew.D p()
    {
        try {
            return this.b;
        } catch (Throwable v1_1) {
            throw v1_1;
        }
    }

    public final void q(long p3)
    {
        this.a.c(p3, "pref_zalo_id");
        return;
    }

    public final void r()
    {
        boolean v0_0 = this.d;
        Object[] v2_0 = this.a;
        try {
            int v4_16 = v0_0.b("pref_user_session");
        } catch (boolean v0_17) {
            Object[] v2_1 = new Object[0];
            ck.b.g(v0_17, "[UserConfig] Load config exception", v2_1);
            return;
        }
        if (!android.text.TextUtils.isEmpty(v4_16)) {
            long v5_1 = new Ew.D(v0_0.b("pref_user_id"));
            this.b = v5_1;
            v5_1.l = v4_16;
            v5_1.m = v0_0.getString("pref_user_zpw_session");
            this.b.n = v2_0.getLong("pref_user_expirein", -1);
            this.b.b = v2_0.getLong("pref_zalo_id", -1);
            this.b.o = v2_0.getString("pref_user_email", "");
            this.b.f = v2_0.getString("pref_user_name", "");
            this.b.g = v2_0.getString("pref_user_avatar", "");
            this.b.h = v2_0.getString("pref_user_birth_date", "");
            this.b.r = v2_0.getInt("profilelevel", 0);
            this.b.v = v2_0.getInt("kyc_level", 0);
            this.b.d = v2_0.getLong("pref_user_phone", 0);
            this.b.p = v2_0.getString("pref_user_identity", "");
            this.b.e = v2_0.getString("pref_zalopay_name", "");
            this.b.q = v2_0.getString("pref_user_permanent_address", "");
            this.b.u = Integer.valueOf(v2_0.getInt("pref_user_login_method", 4));
            this.e = v2_0.getBoolean("pref_drsite", 0);
            return;
        } else {
            return;
        }
    }

    public final void s(String p3)
    {
        this.a.a("pref_last_uid", p3);
        return;
    }

    public final String t()
    {
        return this.a.getString("EncryptedPasswordIV", "");
    }

    public final void u(java.util.Map p4)
    {
        this.f = p4;
        this.a.getString("pref_custom_reset_pin_out_app_header", nY.h.d(p4));
        nY.h.d(p4);
        return;
    }

    public final long v()
    {
        return this.a.getLong(androidx.camera.camera2.internal.G.e("pref_autolocking_time_", this.getUserId()), -1);
    }

    public final boolean w()
    {
        int v0_0 = this.v();
        if ((v0_0 < 0) || ((System.currentTimeMillis() - this.a.getLong("pref_last_time_actived", 0)) < v0_0)) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void x(long p3)
    {
        this.a.c(p3, androidx.camera.camera2.internal.G.e("pref_autolocking_time_", this.getUserId()));
        return;
    }

    public final boolean y()
    {
        return this.a.getBoolean("pref_state_checkbox_fppassword", 0);
    }

    public final String z()
    {
        return this.a.getString("pref_previous_uid", "");
    }
}

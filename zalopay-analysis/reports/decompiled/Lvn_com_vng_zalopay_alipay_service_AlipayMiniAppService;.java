package vn.com.vng.zalopay.alipay.service;
public final class AlipayMiniAppService {
    public static final int $stable = 0;
    private static final String ALL_MINI_APP_ID = "all_mini_app_id";
    public static final vn.com.vng.zalopay.alipay.service.AlipayMiniAppService$Companion Companion = None;
    private static final String PUBLIC_PROFILE = "public_profile";
    private final Pm.b getOAuthCodeMiniAppProgramUseCase;
    private final Pm.c getPublicProfileMiniProgramUseCase;
    private final St.a iDataPersistent;

    static AlipayMiniAppService()
    {
        vn.com.vng.zalopay.alipay.service.AlipayMiniAppService.Companion = new vn.com.vng.zalopay.alipay.service.AlipayMiniAppService$Companion(0);
        vn.com.vng.zalopay.alipay.service.AlipayMiniAppService.$stable = 8;
        return;
    }

    public AlipayMiniAppService(St.a p1, Pm.b p2, Pm.c p3)
    {
        this.iDataPersistent = p1;
        this.getOAuthCodeMiniAppProgramUseCase = p2;
        this.getPublicProfileMiniProgramUseCase = p3;
        return;
    }

    private final void addCachedAppId(String p3)
    {
        java.util.List v0_2 = kotlin.collections.CollectionsKt.toMutableList(((java.util.Collection) this.getAllCachedAppId()));
        if (!v0_2.contains(p3)) {
            v0_2.add(p3);
            this.setAllCachedAppId(v0_2);
        }
        return;
    }

    private final java.util.List getAllCachedAppId()
    {
        java.util.List v0_2 = this.iDataPersistent.getString("all_mini_app_id", "");
        if (v0_2.length() <= 0) {
            return kotlin.collections.E.emptyList();
        } else {
            return kotlin.text.StringsKt.y(v0_2, new String[] {","}), 0, 6);
        }
    }

    private final String getIdPublicProfile(String p2)
    {
        return androidx.camera.camera2.internal.G.e("public_profile_", p2);
    }

    private final void setAllCachedAppId(java.util.List p9)
    {
        if ((((java.util.Collection) p9) != null) && (!((java.util.Collection) p9).isEmpty())) {
            this.iDataPersistent.a("all_mini_app_id", kotlin.collections.CollectionsKt.l(((Iterable) p9), ",", 0, 0, 0, 62));
            return;
        } else {
            this.iDataPersistent.remove("all_mini_app_id");
            return;
        }
    }

    public final Pm.b getGetOAuthCodeMiniAppProgramUseCase()
    {
        return this.getOAuthCodeMiniAppProgramUseCase;
    }

    public final Pm.c getGetPublicProfileMiniProgramUseCase()
    {
        return this.getPublicProfileMiniProgramUseCase;
    }

    public final Nm.b getPublicProfileMiniProgram(String p3)
    {
        Nm.b v3_4 = this.iDataPersistent.getString(this.getIdPublicProfile(p3), 0);
        if ((v3_4 != null) && (v3_4.length() != 0)) {
            return ((Nm.b) nY.h.b(v3_4, new vn.com.vng.zalopay.alipay.service.AlipayMiniAppService$getPublicProfileMiniProgram$$inlined$parseJson$1().getType()));
        } else {
            return 0;
        }
    }

    public final void removeCached()
    {
        java.util.Iterator v0_2 = ((Iterable) this.getAllCachedAppId()).iterator();
        while (v0_2.hasNext()) {
            this.setPublicProfileMiniProgram(((String) v0_2.next()), 0);
        }
        return;
    }

    public final void setPublicProfileMiniProgram(String p2, Nm.b p3)
    {
        String v2_1 = this.getIdPublicProfile(p2);
        if (p3 != null) {
            this.addCachedAppId(v2_1);
            this.iDataPersistent.a(v2_1, nY.h.d(p3));
            return;
        } else {
            this.iDataPersistent.remove(v2_1);
            return;
        }
    }
}

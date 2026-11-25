package vn.com.vng.zalopay.alipay.service;
public final class OAuthServiceProvider implements com.alipay.iap.android.wallet.acl.oauth.OAuthService {
    public static final int $stable;

    public OAuthServiceProvider()
    {
        return;
    }

    public static synthetic kotlin.Unit a(com.alipay.iap.android.wallet.acl.base.Callback p0, boolean p1)
    {
        return vn.com.vng.zalopay.alipay.service.OAuthServiceProvider.showAuthPage$lambda$0(p0, p1);
    }

    private final String[] getScopesForId(String p1)
    {
        if (p1 != null) {
            return new String[] {wmoi.igob.ggcn(1187)});
        } else {
            String[] v1_5 = new String[0];
            return v1_5;
        }
    }

    private final void handleGetAuthCode(String p11, java.util.List p12, com.alipay.iap.android.wallet.acl.base.Callback p13)
    {
        com.alipay.iap.android.wallet.acl.oauth.OAuthResult v3_1 = new com.alipay.iap.android.wallet.acl.oauth.OAuthResult();
        vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1 v0_3 = Em.c.a();
        if (v0_3 != null) {
            if ((p11 != null) && (p12 != 0)) {
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v0_3.getLifecycle()), 0, 0, new vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1(p11, p12, v3_1, p13, 0), 3, 0);
                return;
            } else {
                v3_1.setResultError(new com.alipay.iap.android.wallet.acl.base.ResultError(1000, wmoi.igob.ggcn(1188)));
                if (p13 != null) {
                    p13.result(v3_1);
                }
            }
        } else {
            v3_1.setResultError(new com.alipay.iap.android.wallet.acl.base.ResultError(1005, ""));
            if (p13 != null) {
                p13.result(v3_1);
                return;
            }
        }
        return;
    }

    private static final kotlin.Unit showAuthPage$lambda$0(com.alipay.iap.android.wallet.acl.base.Callback p3, boolean p4)
    {
        if (p4 == null) {
            com.alipay.iap.android.wallet.acl.oauth.OAuthPageConfirmResult v4_4 = new com.alipay.iap.android.wallet.acl.oauth.OAuthPageConfirmResult(0);
            v4_4.setResultError(new com.alipay.iap.android.wallet.acl.base.ResultError(1001, ""));
            if (p3 != null) {
                p3.result(v4_4);
            }
        } else {
            if (p3 != null) {
                p3.result(new com.alipay.iap.android.wallet.acl.oauth.OAuthPageConfirmResult(0));
            }
        }
        return kotlin.Unit.INSTANCE;
    }

    public void consultAuthPage(String p1, java.util.Map p2, com.alipay.iap.android.wallet.acl.base.APIContext p3, com.alipay.iap.android.wallet.acl.base.Callback p4)
    {
        return;
    }

    public void consultAuthPage(String p1, java.util.Set p2, java.util.Map p3, com.alipay.iap.android.wallet.acl.base.APIContext p4, com.alipay.iap.android.wallet.acl.base.Callback p5)
    {
        return;
    }

    public void getAuthCode(String p1, com.alipay.iap.android.wallet.acl.oauth.OAuthCodeFlowType p2, java.util.Map p3, com.alipay.iap.android.wallet.acl.base.APIContext p4, com.alipay.iap.android.wallet.acl.base.Callback p5)
    {
        this.handleGetAuthCode(p1, kotlin.collections.E.emptyList(), p5);
        return;
    }

    public void getAuthCode(String p1, java.util.Set p2, com.alipay.iap.android.wallet.acl.oauth.OAuthCodeFlowType p3, java.util.Map p4, com.alipay.iap.android.wallet.acl.base.APIContext p5, com.alipay.iap.android.wallet.acl.base.Callback p6)
    {
        int v2_1;
        if (p2 == 0) {
            v2_1 = 0;
        } else {
            v2_1 = kotlin.collections.CollectionsKt.toList(((Iterable) p2));
        }
        this.handleGetAuthCode(p1, v2_1, p6);
        return;
    }

    public void getAuthorizedScopes(String p1, java.util.Map p2, com.alipay.iap.android.wallet.acl.base.APIContext p3, com.alipay.iap.android.wallet.acl.base.Callback p4)
    {
        String[] v1_1 = this.getScopesForId(p1);
        String[] v3_1 = new String[0];
        com.alipay.iap.android.wallet.acl.oauth.OAuthScopeQueryResult v2_1 = new com.alipay.iap.android.wallet.acl.oauth.OAuthScopeQueryResult(v3_1);
        v2_1.setAuthorizedScopes(v1_1);
        if (p4 != null) {
            p4.result(v2_1);
        }
        return;
    }

    public void revokeAuthorizedScopes(String p1, java.util.Set p2, java.util.Map p3, com.alipay.iap.android.wallet.acl.base.APIContext p4, com.alipay.iap.android.wallet.acl.base.Callback p5)
    {
        return;
    }

    public void showAuthPage(String p7, String p8, String p9, java.util.Set p10, java.util.Map p11, com.alipay.iap.android.wallet.acl.base.APIContext p12, com.alipay.iap.android.wallet.acl.base.Callback p13)
    {
        kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getMain()), 0, 0, new AA.y(new vn.com.vng.zalopay.alipay.service.a(p13, 1), 0, 15), 3, 0);
        return;
    }
}

package vn.com.vng.zalopay.alipay.service;
public final class MemberServiceProvider implements com.alipay.iap.android.wallet.acl.member.MemberService {
    public static final int $stable;

    public MemberServiceProvider()
    {
        return;
    }

    public static synthetic kotlin.Unit a(com.alipay.iap.android.wallet.acl.member.MemberInfo p0, com.alipay.iap.android.wallet.acl.base.Callback p1, Nm.b p2)
    {
        return vn.com.vng.zalopay.alipay.service.MemberServiceProvider.getMemberInfo$lambda$0(p0, p1, p2);
    }

    public static final synthetic Object access$getPublicProfileRemote(vn.com.vng.zalopay.alipay.service.MemberServiceProvider p0, String p1, java.util.List p2, kotlin.coroutines.Continuation p3)
    {
        return p0.getPublicProfileRemote(p1, p2, p3);
    }

    private static final kotlin.Unit getMemberInfo$lambda$0(com.alipay.iap.android.wallet.acl.member.MemberInfo p0, com.alipay.iap.android.wallet.acl.base.Callback p1, Nm.b p2)
    {
        if (p2 != null) {
            p0.userId = p2.b();
        }
        if (p1 != null) {
            p1.result(new com.alipay.iap.android.wallet.acl.member.MemberInfoResult(p0));
        }
        return kotlin.Unit.INSTANCE;
    }

    private final void getPublicProfile(String p12, java.util.List p13, kotlin.jvm.functions.Function1 p14)
    {
        vn.com.vng.zalopay.alipay.service.AlipayMiniAppService v4 = com.google.android.gms.internal.mlkit_vision_barcode.n5.a;
        if (v4 != null) {
            vn.com.vng.zalopay.alipay.service.MemberServiceProvider v1_2 = v4.getPublicProfileMiniProgram(p12);
            if ((v1_2 != null) && ((System.currentTimeMillis() - v1_2.a()) <= java.util.concurrent.TimeUnit.DAYS.toMillis(3))) {
                p14.invoke(v1_2);
                return;
            } else {
                vn.com.vng.zalopay.alipay.service.MemberServiceProvider v1_0 = Em.c.a();
                if (v1_0 != null) {
                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v1_0.getLifecycle()), 0, 0, new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfile$1(this, p12, p13, v4, p14, 0), 3, 0);
                    return;
                } else {
                    p14.invoke(0);
                    return;
                }
            }
        } else {
            p14.invoke(0);
            return;
        }
    }

    private final Object getPublicProfileRemote(String p3, java.util.List p4, kotlin.coroutines.Continuation p5)
    {
        return gi.L.c(new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2(p3, p4, 0), p5);
    }

    public void getMemberInfo(com.alipay.iap.android.wallet.acl.member.MemberService$MemberInfoFetchStrategy p3, com.alipay.iap.android.wallet.acl.member.MemberInfoScope p4, com.alipay.iap.android.wallet.acl.base.APIContext p5, com.alipay.iap.android.wallet.acl.base.Callback p6)
    {
        com.alipay.iap.android.wallet.acl.member.MemberInfo v5_1 = new com.alipay.iap.android.wallet.acl.member.MemberInfo();
        if ((p4 != null) && (p3 != null)) {
            kotlin.NoWhenBranchMatchedException v3_7 = vn.com.vng.zalopay.alipay.service.MemberServiceProvider$WhenMappings.$EnumSwitchMapping$0[p3.ordinal()];
            if ((v3_7 != 1) && ((v3_7 != 2) && (v3_7 != 3))) {
                throw new kotlin.NoWhenBranchMatchedException();
            } else {
                this.getPublicProfile(p4.getAppId(), kotlin.collections.CollectionsKt.toList(((Iterable) p4.getScopes())), new nY.x(v5_1, p6, 10));
                return;
            }
        } else {
            if (p6 != null) {
                p6.result(new com.alipay.iap.android.wallet.acl.member.MemberInfoResult(v5_1));
            }
            return;
        }
    }
}

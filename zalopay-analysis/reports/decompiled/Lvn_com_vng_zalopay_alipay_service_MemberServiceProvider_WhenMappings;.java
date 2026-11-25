package vn.com.vng.zalopay.alipay.service;
public final synthetic class MemberServiceProvider$WhenMappings {
    public static final synthetic int[] $EnumSwitchMapping$0;

    static MemberServiceProvider$WhenMappings()
    {
        int[] v0_2 = new int[com.alipay.iap.android.wallet.acl.member.MemberService$MemberInfoFetchStrategy.values().length];
        com.alipay.iap.android.wallet.acl.member.MemberService$MemberInfoFetchStrategy.LOCAL_USER_ID_ONLY.ordinal()[int v1_4] = 1;
        com.alipay.iap.android.wallet.acl.member.MemberService$MemberInfoFetchStrategy.REMOTE_FETCH.ordinal()[int v1_0] = 2;
        try {
            com.alipay.iap.android.wallet.acl.member.MemberService$MemberInfoFetchStrategy.LOCAL_CACHED.ordinal()[int v1_2] = 3;
        } catch (NoSuchFieldError) {
        }
        vn.com.vng.zalopay.alipay.service.MemberServiceProvider$WhenMappings.$EnumSwitchMapping$0 = v0_2;
        return;
    }
}

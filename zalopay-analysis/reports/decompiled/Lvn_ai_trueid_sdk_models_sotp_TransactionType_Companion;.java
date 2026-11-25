package vn.ai.trueid.sdk.models.sotp;
public final class TransactionType$Companion {

    private TransactionType$Companion()
    {
        return;
    }

    public synthetic TransactionType$Companion(kotlin.jvm.internal.DefaultConstructorMarker p1)
    {
        return;
    }

    public final vn.ai.trueid.sdk.models.sotp.TransactionType fromString(String p2)
    {
        if (p2 != null) {
            try {
                return vn.ai.trueid.sdk.models.sotp.TransactionType.valueOf(p2.toUpperCase(java.util.Locale.ROOT));
            } catch (Exception) {
                return vn.ai.trueid.sdk.models.sotp.TransactionType.GENERIC;
            }
        } else {
            return vn.ai.trueid.sdk.models.sotp.TransactionType.GENERIC;
        }
    }
}

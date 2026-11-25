package vn.ai.trueid.sdk.models.sotp;
public final class TransactionDisplayModel implements java.io.Serializable {
    public static final vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel$Companion Companion;
    private final java.util.List fields;
    private final String rawJson;
    private final vn.ai.trueid.sdk.models.sotp.TransactionType transactionType;

    static TransactionDisplayModel()
    {
        vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel.Companion = new vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel$Companion(0);
        return;
    }

    public TransactionDisplayModel(vn.ai.trueid.sdk.models.sotp.TransactionType p1, java.util.List p2, String p3)
    {
        this.transactionType = p1;
        this.fields = p2;
        this.rawJson = p3;
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel copy$default(vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel p0, vn.ai.trueid.sdk.models.sotp.TransactionType p1, java.util.List p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.transactionType;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.fields;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.rawJson;
        }
        return p0.copy(p1, p2, p3);
    }

    public final vn.ai.trueid.sdk.models.sotp.TransactionType component1()
    {
        return this.transactionType;
    }

    public final java.util.List component2()
    {
        return this.fields;
    }

    public final String component3()
    {
        return this.rawJson;
    }

    public final vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel copy(vn.ai.trueid.sdk.models.sotp.TransactionType p2, java.util.List p3, String p4)
    {
        return new vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel(p2, p3, p4);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel)) {
                if (this.transactionType == ((vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel) p5).transactionType) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.fields, ((vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel) p5).fields)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.rawJson, ((vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel) p5).rawJson)) {
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
            return 1;
        }
    }

    public final java.util.List getFields()
    {
        return this.fields;
    }

    public final String getRawJson()
    {
        return this.rawJson;
    }

    public final vn.ai.trueid.sdk.models.sotp.TransactionType getTransactionType()
    {
        return this.transactionType;
    }

    public int hashCode()
    {
        return (this.rawJson.hashCode() + Kb.e.h((this.transactionType.hashCode() * 31), 31, this.fields));
    }

    public String toString()
    {
        String v0_0 = this.transactionType;
        java.util.List v1 = this.fields;
        String v2 = this.rawJson;
        StringBuilder v3_1 = new StringBuilder("TransactionDisplayModel(transactionType=");
        v3_1.append(v0_0);
        v3_1.append(", fields=");
        v3_1.append(v1);
        v3_1.append(", rawJson=");
        return A1.a.l(v3_1, v2, ")");
    }
}

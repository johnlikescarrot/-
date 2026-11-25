package vn.ai.trueid.sdk.models.sotp;
public final class TrueIdSmartOTPResult {
    private int code;
    private String errorMessage;
    private String result;

    public TrueIdSmartOTPResult()
    {
        this(0, 0, 0, 7, 0);
        return;
    }

    public TrueIdSmartOTPResult(int p1, String p2, String p3)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.result = p3;
        return;
    }

    public synthetic TrueIdSmartOTPResult(int p1, String p2, String p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 1) != 0) {
            p1 = 1;
        }
        if ((p4 & 2) != 0) {
            p2 = "Success";
        }
        if ((p4 & 4) != 0) {
            p3 = "";
        }
        this(p1, p2, p3);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult copy$default(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult p0, int p1, String p2, String p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.errorMessage;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.result;
        }
        return p0.copy(p1, p2, p3);
    }

    public final int component1()
    {
        return this.code;
    }

    public final String component2()
    {
        return this.errorMessage;
    }

    public final String component3()
    {
        return this.result;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult copy(int p2, String p3, String p4)
    {
        return new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(p2, p3, p4);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult)) {
                if (this.code == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorMessage, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult) p5).errorMessage)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.result, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult) p5).result)) {
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

    public final int getCode()
    {
        return this.code;
    }

    public final String getErrorMessage()
    {
        return this.errorMessage;
    }

    public final String getResult()
    {
        return this.result;
    }

    public int hashCode()
    {
        return (this.result.hashCode() + e5.i.c((Integer.hashCode(this.code) * 31), 31, this.errorMessage));
    }

    public final void setCode(int p1)
    {
        this.code = p1;
        return;
    }

    public final void setErrorMessage(String p1)
    {
        this.errorMessage = p1;
        return;
    }

    public final void setResult(String p1)
    {
        this.result = p1;
        return;
    }

    public final java.util.Map toFullMap()
    {
        java.util.Map v0_2 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("code", Integer.valueOf(this.code)), kotlin.TuplesKt.to("errorMessage", this.errorMessage)}));
        if ((this.code == 1) && (this.result.length() > 0)) {
            if (!vn.ai.trueid.sdk.utils.j.f(this.result)) {
                v0_2.put("result", this.result);
            } else {
                try {
                    String v1_8 = new org.json.JSONObject(this.result);
                    java.util.LinkedHashMap v3_2 = new java.util.LinkedHashMap();
                    v3_2.putAll(q9.A4.a(v1_8));
                    v0_2.put("result", v3_2);
                    return v0_2;
                } catch (Exception) {
                    v0_2.put("result", this.result);
                    return v0_2;
                }
            }
        }
        return v0_2;
    }

    public final org.json.JSONObject toJson()
    {
        return new org.json.JSONObject(this.toFullMap());
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("code", Integer.valueOf(this.code)), kotlin.TuplesKt.to("errorMessage", this.errorMessage), kotlin.TuplesKt.to("result", this.result)}));
    }

    public String toString()
    {
        return A1.a.l(com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(this.code, "TrueIdSmartOTPResult(code=", ", errorMessage=", this.errorMessage, ", result="), this.result, ")");
    }
}

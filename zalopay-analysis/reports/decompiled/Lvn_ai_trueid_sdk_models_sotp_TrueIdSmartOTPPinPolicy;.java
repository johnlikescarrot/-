package vn.ai.trueid.sdk.models.sotp;
public final class TrueIdSmartOTPPinPolicy {
    private final boolean blockWeakPin;
    private final java.util.List lockoutBackoffMs;
    private final int maxAttempts;
    private final int maxLength;
    private final int minLength;

    public TrueIdSmartOTPPinPolicy()
    {
        this(0, 0, 0, 0, 0, 31, 0);
        return;
    }

    public TrueIdSmartOTPPinPolicy(int p1, int p2, int p3, boolean p4, java.util.List p5)
    {
        this.minLength = p1;
        this.maxLength = p2;
        this.maxAttempts = p3;
        this.blockWeakPin = p4;
        this.lockoutBackoffMs = p5;
        return;
    }

    public synthetic TrueIdSmartOTPPinPolicy(int p4, int p5, int p6, boolean p7, java.util.List p8, int p9, kotlin.jvm.internal.DefaultConstructorMarker p10)
    {
        if ((p9 & 1) != 0) {
            p4 = 6;
        }
        if ((p9 & 2) != 0) {
            p5 = 6;
        }
        if ((p9 & 4) != 0) {
            p6 = 3;
        }
        if ((p9 & 8) != 0) {
            p7 = 1;
        }
        if ((p9 & 16) != 0) {
            p8 = kotlin.collections.E.listOf(new Long[] {Long.valueOf(5000), Long.valueOf(15000), Long.valueOf(25000), Long.valueOf(35000), Long.valueOf(45000)}));
        }
        this(p4, p5, p6, p7, p8);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy copy$default(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy p0, int p1, int p2, int p3, boolean p4, java.util.List p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.minLength;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.maxLength;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.maxAttempts;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.blockWeakPin;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.lockoutBackoffMs;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final int component1()
    {
        return this.minLength;
    }

    public final int component2()
    {
        return this.maxLength;
    }

    public final int component3()
    {
        return this.maxAttempts;
    }

    public final boolean component4()
    {
        return this.blockWeakPin;
    }

    public final java.util.List component5()
    {
        return this.lockoutBackoffMs;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy copy(int p7, int p8, int p9, boolean p10, java.util.List p11)
    {
        return new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy(p7, p8, p9, p10, p11);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy)) {
                if (this.minLength == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy) p5).minLength) {
                    if (this.maxLength == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy) p5).maxLength) {
                        if (this.maxAttempts == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy) p5).maxAttempts) {
                            if (this.blockWeakPin == ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy) p5).blockWeakPin) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.lockoutBackoffMs, ((vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPPinPolicy) p5).lockoutBackoffMs)) {
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
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final boolean getBlockWeakPin()
    {
        return this.blockWeakPin;
    }

    public final int getEffectiveMax()
    {
        return Og.x.coerceIn(this.maxLength, this.getEffectiveMin(), 10);
    }

    public final int getEffectiveMaxAttempts()
    {
        return Og.x.coerceAtLeast(this.maxAttempts, 1);
    }

    public final int getEffectiveMin()
    {
        return Og.x.coerceIn(this.minLength, 4, 10);
    }

    public final java.util.List getLockoutBackoffMs()
    {
        return this.lockoutBackoffMs;
    }

    public final int getMaxAttempts()
    {
        return this.maxAttempts;
    }

    public final int getMaxLength()
    {
        return this.maxLength;
    }

    public final int getMinLength()
    {
        return this.minLength;
    }

    public int hashCode()
    {
        int vtmp3 = Kb.e.v(this.maxAttempts, Kb.e.v(this.maxLength, (Integer.hashCode(this.minLength) * 31), 31), 31);
        int v2_0 = this.blockWeakPin;
        if (v2_0 != 0) {
            v2_0 = 1;
        }
        return (this.lockoutBackoffMs.hashCode() + ((vtmp3 + v2_0) * 31));
    }

    public final boolean isValid(String p5)
    {
        int v0_0 = this.getEffectiveMin();
        boolean v1_3 = this.getEffectiveMax();
        int v2 = p5.length();
        if ((v0_0 <= v2) && (v2 <= v1_3)) {
            int v0_1 = 0;
            while (v0_1 < p5.length()) {
                if (Character.isDigit(p5.charAt(v0_1))) {
                    v0_1++;
                }
            }
            return 1;
        }
        return 0;
    }

    public final boolean isWeak(String p10)
    {
        if (p10.length() >= 2) {
            int v0_2 = 0;
            while (v0_2 < p10.length()) {
                if (Character.isDigit(p10.charAt(v0_2))) {
                    v0_2++;
                }
            }
            int v0_1 = new java.util.ArrayList(p10.length());
            int v1_1 = 0;
            while (v1_1 < p10.length()) {
                v0_1.add(Integer.valueOf((p10.charAt(v1_1) - 48)));
                v1_1++;
            }
            if (kotlin.collections.CollectionsKt.toSet(v0_1).size() != 1) {
                int v10_3 = v0_1.size();
                int v3_1 = 1;
                int v4 = 1;
                int v5 = 1;
                while (v3_1 < v10_3) {
                    int v7_0 = (v3_1 - 1);
                    if (((Number) v0_1.get(v3_1)).intValue() != (((Number) v0_1.get(v7_0)).intValue() + 1)) {
                        v4 = 0;
                    }
                    if (((Number) v0_1.get(v3_1)).intValue() != (((Number) v0_1.get(v7_0)).intValue() - 1)) {
                        v5 = 0;
                    }
                    v3_1++;
                }
                if ((v4 == 0) && (v5 == 0)) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
        return 0;
    }

    public String toString()
    {
        int v2 = this.maxAttempts;
        boolean v3 = this.blockWeakPin;
        java.util.List v4 = this.lockoutBackoffMs;
        String v0_2 = androidx.activity.result.e.s(this.minLength, this.maxLength, "TrueIdSmartOTPPinPolicy(minLength=", ", maxLength=", ", maxAttempts=");
        v0_2.append(v2);
        v0_2.append(", blockWeakPin=");
        v0_2.append(v3);
        v0_2.append(", lockoutBackoffMs=");
        return androidx.camera.camera2.internal.G.h(v0_2, v4, ")");
    }
}

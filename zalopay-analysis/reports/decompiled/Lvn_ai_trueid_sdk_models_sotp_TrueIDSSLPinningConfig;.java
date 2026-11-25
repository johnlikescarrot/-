package vn.ai.trueid.sdk.models.sotp;
public final class TrueIDSSLPinningConfig {
    private final java.util.Map pinnedDomains;

    public TrueIDSSLPinningConfig(java.util.Map p7)
    {
        this.pinnedDomains = p7;
        if (p7.isEmpty()) {
            throw new IllegalArgumentException(wmoi.igob.ggcn(15886));
        } else {
            String v7_2 = p7.entrySet().iterator();
            while (v7_2.hasNext()) {
                IllegalArgumentException v0_2 = ((java.util.Map$Entry) v7_2.next());
                IllegalArgumentException v0_4 = ((java.util.List) v0_2.getValue());
                if (v0_4.size() < 2) {
                    throw new IllegalArgumentException(A1.a.i(wmoi.igob.ggcn(15884), ((String) v0_2.getKey()), wmoi.igob.ggcn(15885)).toString());
                } else {
                    IllegalArgumentException v0_11 = ((Iterable) v0_4).iterator();
                    while (v0_11.hasNext()) {
                        String v1_4 = ((String) v0_11.next());
                        if (!kotlin.text.y.startsWith$default(v1_4, wmoi.igob.ggcn(15882), 0, 2, 0)) {
                            throw new IllegalArgumentException(androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(15883), v1_4).toString());
                        }
                    }
                }
            }
            return;
        }
    }

    public static synthetic vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig copy$default(vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig p0, java.util.Map p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.pinnedDomains;
        }
        return p0.copy(p1);
    }

    public final java.util.Map component1()
    {
        return this.pinnedDomains;
    }

    public final vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig copy(java.util.Map p2)
    {
        return new vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig(p2);
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.pinnedDomains, ((vn.ai.trueid.sdk.models.sotp.TrueIDSSLPinningConfig) p4).pinnedDomains)) {
                    return 1;
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

    public final java.util.Map getPinnedDomains()
    {
        return this.pinnedDomains;
    }

    public int hashCode()
    {
        return this.pinnedDomains.hashCode();
    }

    public String toString()
    {
        String v0_0 = this.pinnedDomains;
        StringBuilder v1_1 = new StringBuilder(wmoi.igob.ggcn(15887));
        v1_1.append(v0_0);
        v1_1.append(wmoi.igob.ggcn(15888));
        return v1_1.toString();
    }
}

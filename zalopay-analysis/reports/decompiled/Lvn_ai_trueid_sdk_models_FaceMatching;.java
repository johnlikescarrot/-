package vn.ai.trueid.sdk.models;
public final class FaceMatching {
    private double confidence_level;
    private boolean matched;
    private double similarity;

    public FaceMatching(boolean p1, double p2, double p4)
    {
        this.matched = p1;
        this.similarity = p2;
        this.confidence_level = p4;
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.FaceMatching copy$default(vn.ai.trueid.sdk.models.FaceMatching p0, boolean p1, double p2, double p4, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.matched;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.similarity;
        }
        if ((p6 & 4) != 0) {
            p4 = p0.confidence_level;
        }
        return p0.copy(p1, p2, p4);
    }

    public final boolean component1()
    {
        return this.matched;
    }

    public final double component2()
    {
        return this.similarity;
    }

    public final double component3()
    {
        return this.confidence_level;
    }

    public final vn.ai.trueid.sdk.models.FaceMatching copy(boolean p7, double p8, double p10)
    {
        return new vn.ai.trueid.sdk.models.FaceMatching(p7, p8, p10);
    }

    public boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.ai.trueid.sdk.models.FaceMatching)) {
                if (this.matched == ((vn.ai.trueid.sdk.models.FaceMatching) p8).matched) {
                    if (Double.compare(this.similarity, ((vn.ai.trueid.sdk.models.FaceMatching) p8).similarity) == 0) {
                        if (Double.compare(this.confidence_level, ((vn.ai.trueid.sdk.models.FaceMatching) p8).confidence_level) == 0) {
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

    public final double getConfidence_level()
    {
        return this.confidence_level;
    }

    public final boolean getMatched()
    {
        return this.matched;
    }

    public final double getSimilarity()
    {
        return this.similarity;
    }

    public int hashCode()
    {
        int v0_0 = this.matched;
        if (v0_0 != 0) {
            v0_0 = 1;
        }
        return (Double.hashCode(this.confidence_level) + ((Double.hashCode(this.similarity) + (v0_0 * 31)) * 31));
    }

    public final void setConfidence_level(double p1)
    {
        this.confidence_level = p1;
        return;
    }

    public final void setMatched(boolean p1)
    {
        this.matched = p1;
        return;
    }

    public final void setSimilarity(double p1)
    {
        this.similarity = p1;
        return;
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("matched", Boolean.valueOf(this.matched)), kotlin.TuplesKt.to("similarity", Double.valueOf(this.similarity)), kotlin.TuplesKt.to("confidenceLevel", Double.valueOf(this.confidence_level))}));
    }

    public String toString()
    {
        String v0_0 = this.matched;
        double v1 = this.similarity;
        double v3 = this.confidence_level;
        StringBuilder v5_1 = new StringBuilder("FaceMatching(matched=");
        v5_1.append(v0_0);
        v5_1.append(", similarity=");
        v5_1.append(v1);
        v5_1.append(", confidence_level=");
        v5_1.append(v3);
        v5_1.append(")");
        return v5_1.toString();
    }
}

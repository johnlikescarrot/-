package vn.ai.trueid.lottie.model.content;
public class GradientColor {
    private final int[] colors;
    private final float[] positions;

    public GradientColor(float[] p1, int[] p2)
    {
        this.positions = p1;
        this.colors = p2;
        return;
    }

    private void copyFrom(vn.ai.trueid.lottie.model.content.GradientColor p5)
    {
        int v0 = 0;
        while(true) {
            int v1_0 = p5.colors;
            if (v0 >= v1_0.length) {
                break;
            }
            this.positions[v0] = p5.positions[v0];
            this.colors[v0] = v1_0[v0];
            v0++;
        }
        return;
    }

    private int getColorForPosition(float p6)
    {
        int v0_2 = java.util.Arrays.binarySearch(this.positions, p6);
        if (v0_2 < 0) {
            int v0_5 = (- (v0_2 + 1));
            if (v0_5 != 0) {
                int[] v1 = this.colors;
                if (v0_5 != (v1.length - 1)) {
                    float v2_0 = this.positions;
                    int v3_0 = (v0_5 - 1);
                    float v4 = v2_0[v3_0];
                    return vn.ai.trueid.lottie.utils.GammaEvaluator.evaluate(((p6 - v4) / (v2_0[v0_5] - v4)), v1[v3_0], v1[v0_5]);
                } else {
                    return v1[(v1.length - 1)];
                }
            } else {
                return this.colors[0];
            }
        } else {
            return this.colors[v0_2];
        }
    }

    public vn.ai.trueid.lottie.model.content.GradientColor copyWithPositions(float[] p4)
    {
        int[] v0_1 = new int[p4.length];
        vn.ai.trueid.lottie.model.content.GradientColor v1_0 = 0;
        while (v1_0 < p4.length) {
            v0_1[v1_0] = this.getColorForPosition(p4[v1_0]);
            v1_0++;
        }
        return new vn.ai.trueid.lottie.model.content.GradientColor(p4, v0_1);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((!p5) || ((this.getClass() != p5.getClass()) || ((!java.util.Arrays.equals(this.positions, ((vn.ai.trueid.lottie.model.content.GradientColor) p5).positions)) || (!java.util.Arrays.equals(this.colors, ((vn.ai.trueid.lottie.model.content.GradientColor) p5).colors))))) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    public int[] getColors()
    {
        return this.colors;
    }

    public float[] getPositions()
    {
        return this.positions;
    }

    public int getSize()
    {
        return this.colors.length;
    }

    public int hashCode()
    {
        return (java.util.Arrays.hashCode(this.colors) + (java.util.Arrays.hashCode(this.positions) * 31));
    }

    public void lerp(vn.ai.trueid.lottie.model.content.GradientColor p5, vn.ai.trueid.lottie.model.content.GradientColor p6, float p7)
    {
        if (!p5.equals(p6)) {
            if (p7 > 0) {
                if (p7 < 1065353216) {
                    if (p5.colors.length != p6.colors.length) {
                        int v0_3 = new StringBuilder(wmoi.igob.ggcn(10269));
                        v0_3.append(p5.colors.length);
                        v0_3.append(wmoi.igob.ggcn(10270));
                        throw new IllegalArgumentException(A1.a.h(p6.colors.length, wmoi.igob.ggcn(10271), v0_3));
                    } else {
                        int v0_5 = 0;
                        while(true) {
                            float v1_4 = p5.colors;
                            if (v0_5 >= v1_4.length) {
                                break;
                            }
                            this.positions[v0_5] = vn.ai.trueid.lottie.utils.MiscUtils.lerp(p5.positions[v0_5], p6.positions[v0_5], p7);
                            this.colors[v0_5] = vn.ai.trueid.lottie.utils.GammaEvaluator.evaluate(p7, p5.colors[v0_5], p6.colors[v0_5]);
                            v0_5++;
                        }
                        int v6_3 = v1_4.length;
                        while(true) {
                            int[] v7_3 = this.positions;
                            if (v6_3 >= v7_3.length) {
                                break;
                            }
                            int v0_8 = p5.colors;
                            v7_3[v6_3] = v7_3[(v0_8.length - 1)];
                            int[] v7_4 = this.colors;
                            v7_4[v6_3] = v7_4[(v0_8.length - 1)];
                            v6_3++;
                        }
                        return;
                    }
                } else {
                    this.copyFrom(p6);
                    return;
                }
            } else {
                this.copyFrom(p5);
                return;
            }
        } else {
            this.copyFrom(p5);
            return;
        }
    }
}

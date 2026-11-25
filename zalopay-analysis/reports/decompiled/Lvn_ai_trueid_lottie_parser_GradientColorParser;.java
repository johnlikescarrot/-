package vn.ai.trueid.lottie.parser;
public class GradientColorParser implements vn.ai.trueid.lottie.parser.ValueParser {
    private int colorPoints;

    public GradientColorParser(int p1)
    {
        this.colorPoints = p1;
        return;
    }

    private vn.ai.trueid.lottie.model.content.GradientColor addOpacityStopsToGradientIfNeeded(vn.ai.trueid.lottie.model.content.GradientColor p10, java.util.List p11)
    {
        int v0_1 = (this.colorPoints * 4);
        if (p11.size() > v0_1) {
            float[] v1_1 = p10.getPositions();
            int[] v2 = p10.getColors();
            float[] v3_2 = ((p11.size() - v0_1) / 2);
            float[] v4 = new float[v3_2];
            float[] v3_3 = new float[v3_2];
            int v5 = 0;
            int v6_0 = 0;
            while (v0_1 < p11.size()) {
                if ((v0_1 % 2) != 0) {
                    v3_3[v6_0] = ((Float) p11.get(v0_1)).floatValue();
                    v6_0++;
                } else {
                    v4[v6_0] = ((Float) p11.get(v0_1)).floatValue();
                }
                v0_1++;
            }
            float[] v10_2 = vn.ai.trueid.lottie.parser.GradientColorParser.mergeUniqueElements(p10.getPositions(), v4);
            vn.ai.trueid.lottie.model.content.GradientColor v11_1 = v10_2.length;
            int v0_2 = new int[v11_1];
            while (v5 < v11_1) {
                int v6_1 = v10_2[v5];
                float v7_1 = java.util.Arrays.binarySearch(v1_1, v6_1);
                int v8_0 = java.util.Arrays.binarySearch(v4, v6_1);
                if ((v7_1 >= 0) && (v8_0 <= 0)) {
                    v0_2[v5] = this.getColorInBetweenOpacityStops(v6_1, v2[v7_1], v4, v3_3);
                } else {
                    if (v8_0 < 0) {
                        v8_0 = (- (v8_0 + 1));
                    }
                    v0_2[v5] = this.getColorInBetweenColorStops(v6_1, v3_3[v8_0], v1_1, v2);
                }
                v5++;
            }
            return new vn.ai.trueid.lottie.model.content.GradientColor(v10_2, v0_2);
        } else {
            return p10;
        }
    }

    private int getColorInBetweenOpacityStops(float p7, int p8, float[] p9, float[] p10)
    {
        if ((p10.length >= 2) && (p7 > p9[0])) {
            int v1_1 = 1;
            while (v1_1 < p9.length) {
                float v2_1 = p9[v1_1];
                float v4 = v2_1 cmp p7;
                if ((v4 >= 0) || (v1_1 == (p9.length - 1))) {
                    int v7_5;
                    if (v4 > 0) {
                        int v0_1 = (v1_1 - 1);
                        int v9_1 = p9[v0_1];
                        v7_5 = vn.ai.trueid.lottie.utils.MiscUtils.lerp(p10[v0_1], p10[v1_1], ((p7 - v9_1) / (v2_1 - v9_1)));
                    } else {
                        v7_5 = p10[v1_1];
                    }
                    return android.graphics.Color.argb(((int) (v7_5 * 1132396544)), android.graphics.Color.red(p8), android.graphics.Color.green(p8), android.graphics.Color.blue(p8));
                } else {
                    v1_1++;
                }
            }
            throw new IllegalArgumentException("Unreachable code.");
        } else {
            return android.graphics.Color.argb(((int) (p10[0] * 1132396544)), android.graphics.Color.red(p8), android.graphics.Color.green(p8), android.graphics.Color.blue(p8));
        }
    }

    public static float[] mergeUniqueElements(float[] p9, float[] p10)
    {
        if (p9.length != 0) {
            if (p10.length != 0) {
                int v0_4 = (p9.length + p10.length);
                float[] v1_1 = new float[v0_4];
                int v2 = 0;
                int v3 = 0;
                int v4 = 0;
                int v5 = 0;
                while (v2 < v0_4) {
                    int v6_1;
                    float v7 = 2143289344;
                    if (v4 >= p9.length) {
                        v6_1 = 2143289344;
                    } else {
                        v6_1 = p9[v4];
                    }
                    if (v5 < p10.length) {
                        v7 = p10[v5];
                    }
                    if ((!Float.isNaN(v7)) && (v6_1 >= v7)) {
                        if ((!Float.isNaN(v6_1)) && (v7 >= v6_1)) {
                            v1_1[v2] = v6_1;
                            v4++;
                            v5++;
                            v3++;
                        } else {
                            v1_1[v2] = v7;
                            v5++;
                        }
                    } else {
                        v1_1[v2] = v6_1;
                        v4++;
                    }
                    v2++;
                }
                if (v3 != 0) {
                    return java.util.Arrays.copyOf(v1_1, (v0_4 - v3));
                } else {
                    return v1_1;
                }
            } else {
                return p9;
            }
        } else {
            return p10;
        }
    }

    public int getColorInBetweenColorStops(float p5, float p6, float[] p7, int[] p8)
    {
        if ((p8.length >= 2) && (p5 != p7[0])) {
            int v1_1 = 1;
            while (v1_1 < p7.length) {
                float v2_0 = p7[v1_1];
                if ((v2_0 >= p5) || (v1_1 == (p7.length - 1))) {
                    if ((v1_1 != (p7.length - 1)) || (p5 < v2_0)) {
                        int v3_6 = (v1_1 - 1);
                        int v7_1 = p7[v3_6];
                        int v5_5 = vn.ai.trueid.lottie.utils.GammaEvaluator.evaluate(((p5 - v7_1) / (v2_0 - v7_1)), p8[v3_6], p8[v1_1]);
                        return android.graphics.Color.argb(((int) (p6 * 1132396544)), android.graphics.Color.red(v5_5), android.graphics.Color.green(v5_5), android.graphics.Color.blue(v5_5));
                    } else {
                        return android.graphics.Color.argb(((int) (p6 * 1132396544)), android.graphics.Color.red(p8[v1_1]), android.graphics.Color.green(p8[v1_1]), android.graphics.Color.blue(p8[v1_1]));
                    }
                } else {
                    v1_1++;
                }
            }
            throw new IllegalArgumentException("Unreachable code.");
        } else {
            return p8[0];
        }
    }

    public bridge synthetic Object parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p1, float p2)
    {
        return this.parse(p1, p2);
    }

    public vn.ai.trueid.lottie.model.content.GradientColor parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p18, float p19)
    {
        int[] v2_0;
        vn.ai.trueid.lottie.model.content.GradientColor v1_1 = new java.util.ArrayList();
        int v5 = 0;
        if (p18.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY) {
            v2_0 = 0;
        } else {
            v2_0 = 1;
        }
        if (v2_0 != null) {
            p18.beginArray();
        }
        while (p18.hasNext()) {
            v1_1.add(Float.valueOf(((float) p18.nextDouble())));
        }
        if ((v1_1.size() == 4) && (((Float) v1_1.get(0)).floatValue() == 1065353216)) {
            v1_1.set(0, Float.valueOf(0));
            v1_1.add(Float.valueOf(1065353216));
            v1_1.add(((Float) v1_1.get(1)));
            v1_1.add(((Float) v1_1.get(2)));
            v1_1.add(((Float) v1_1.get(3)));
            this.colorPoints = 2;
        }
        if (v2_0 != null) {
            p18.endArray();
        }
        if (this.colorPoints == -1) {
            this.colorPoints = (v1_1.size() / 4);
        }
        int[] v2_5 = this.colorPoints;
        float[] v3_19 = new float[v2_5];
        int[] v2_6 = new int[v2_5];
        int v9_1 = 0;
        int v10 = 0;
        while (v5 < (this.colorPoints * 4)) {
            int v11_2 = (v5 / 4);
            int v12_6 = ((double) ((Float) v1_1.get(v5)).floatValue());
            float v14_0 = (v5 % 4);
            if (v14_0 == 0) {
                if (v11_2 > 0) {
                    float v15_0 = ((float) v12_6);
                    if (v3_19[(v11_2 - 1)] >= v15_0) {
                        v3_19[v11_2] = (v15_0 + 1008981770);
                        v5++;
                    }
                }
                v3_19[v11_2] = ((float) v12_6);
            } else {
                if (v14_0 == 1) {
                    v9_1 = ((int) (v12_6 * 4643176031446892544));
                } else {
                    if (v14_0 == 2) {
                        v10 = ((int) (v12_6 * 4643176031446892544));
                    } else {
                        if (v14_0 == 3) {
                            v2_6[v11_2] = android.graphics.Color.argb(255, v9_1, v10, ((int) (v12_6 * 4643176031446892544)));
                        }
                    }
                }
            }
        }
        return this.addOpacityStopsToGradientIfNeeded(new vn.ai.trueid.lottie.model.content.GradientColor(v3_19, v2_6), v1_1);
    }
}

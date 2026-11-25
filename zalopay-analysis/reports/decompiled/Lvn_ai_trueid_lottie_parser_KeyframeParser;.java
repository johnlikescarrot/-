package vn.ai.trueid.lottie.parser;
 class KeyframeParser {
    static vn.ai.trueid.lottie.parser.moshi.JsonReader$Options INTERPOLATOR_NAMES = None;
    private static final android.view.animation.Interpolator LINEAR_INTERPOLATOR = None;
    private static final float MAX_CP_VALUE = 17096;
    static vn.ai.trueid.lottie.parser.moshi.JsonReader$Options NAMES;
    private static P.O pathInterpolatorCache;

    static KeyframeParser()
    {
        vn.ai.trueid.lottie.parser.KeyframeParser.LINEAR_INTERPOLATOR = new android.view.animation.LinearInterpolator();
        String v3 = "e";
        String v5 = "i";
        vn.ai.trueid.lottie.parser.KeyframeParser.NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"t", "ti"}));
        vn.ai.trueid.lottie.parser.KeyframeParser.INTERPOLATOR_NAMES = vn.ai.trueid.lottie.parser.moshi.JsonReader$Options.of(new String[] {"x", "y"}));
        return;
    }

    public KeyframeParser()
    {
        return;
    }

    private static ref.WeakReference getInterpolator(int p2)
    {
        try {
            return ((ref.WeakReference) vn.ai.trueid.lottie.parser.KeyframeParser.pathInterpolatorCache().f(p2));
        } catch (Throwable v2_3) {
            throw v2_3;
        }
    }

    private static android.view.animation.Interpolator interpolatorFor(android.graphics.PointF p7, android.graphics.PointF p8)
    {
        float v1_3;
        p7.x = vn.ai.trueid.lottie.utils.MiscUtils.clamp(p7.x, -1082130432, 1065353216);
        p7.y = vn.ai.trueid.lottie.utils.MiscUtils.clamp(p7.y, -1027080192, 1120403456);
        p8.x = vn.ai.trueid.lottie.utils.MiscUtils.clamp(p8.x, -1082130432, 1065353216);
        int v0_5 = vn.ai.trueid.lottie.utils.MiscUtils.clamp(p8.y, -1027080192, 1120403456);
        p8.y = v0_5;
        int v0_6 = vn.ai.trueid.lottie.utils.Utils.hashFor(p7.x, p7.y, p8.x, v0_5);
        int v3_1 = 0;
        if (!vn.ai.trueid.lottie.L.getDisablePathInterpolatorCache()) {
            v1_3 = vn.ai.trueid.lottie.parser.KeyframeParser.getInterpolator(v0_6);
        } else {
            v1_3 = 0;
        }
        if (v1_3 != 0) {
            v3_1 = ((android.view.animation.Interpolator) v1_3.get());
        }
        if ((v1_3 != 0) && (v3_1 != 0)) {
            return v3_1;
        } else {
            try {
                android.view.animation.LinearInterpolator v6_1 = new android.view.animation.PathInterpolator(p7.x, p7.y, p8.x, p8.y);
            } catch (float v1_5) {
                if (!"The Path cannot loop back on itself.".equals(v1_5.getMessage())) {
                    v6_1 = new android.view.animation.LinearInterpolator();
                } else {
                    v6_1 = new android.view.animation.PathInterpolator(Math.min(p7.x, 1065353216), p7.y, Math.max(p8.x, 0), p8.y);
                }
            }
            try {
                if (!vn.ai.trueid.lottie.L.getDisablePathInterpolatorCache()) {
                    vn.ai.trueid.lottie.parser.KeyframeParser.putInterpolator(v0_6, new ref.WeakReference(v6_1));
                }
            } catch (ArrayIndexOutOfBoundsException) {
            }
            return v6_1;
        }
    }

    public static vn.ai.trueid.lottie.value.Keyframe parse(vn.ai.trueid.lottie.parser.moshi.JsonReader p0, vn.ai.trueid.lottie.LottieComposition p1, float p2, vn.ai.trueid.lottie.parser.ValueParser p3, boolean p4, boolean p5)
    {
        if ((!p4) || (!p5)) {
            if (!p4) {
                return vn.ai.trueid.lottie.parser.KeyframeParser.parseStaticValue(p0, p2, p3);
            } else {
                return vn.ai.trueid.lottie.parser.KeyframeParser.parseKeyframe(p1, p0, p2, p3);
            }
        } else {
            return vn.ai.trueid.lottie.parser.KeyframeParser.parseMultiDimensionalKeyframe(p1, p0, p2, p3);
        }
    }

    private static vn.ai.trueid.lottie.value.Keyframe parseKeyframe(vn.ai.trueid.lottie.LottieComposition p12, vn.ai.trueid.lottie.parser.moshi.JsonReader p13, float p14, vn.ai.trueid.lottie.parser.ValueParser p15)
    {
        p13.beginObject();
        android.graphics.PointF v0 = 0;
        vn.ai.trueid.lottie.value.Keyframe v3_2 = 0;
        Object v5 = 0;
        android.graphics.PointF v10 = 0;
        android.graphics.PointF v11 = 0;
        float v8 = 0;
        vn.ai.trueid.lottie.LottieComposition v4_2 = 0;
        android.graphics.PointF v1_0 = 0;
        while (p13.hasNext()) {
            switch (p13.selectName(vn.ai.trueid.lottie.parser.KeyframeParser.NAMES)) {
                case 0:
                    v8 = ((float) p13.nextDouble());
                    break;
                case 1:
                    v5 = p15.parse(p13, p14);
                    break;
                case 2:
                    v3_2 = p15.parse(p13, p14);
                    break;
                case 3:
                    v0 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p13, 1065353216);
                    break;
                case 4:
                    v1_0 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p13, 1065353216);
                    break;
                case 5:
                    if (p13.nextInt() != 1) {
                        v4_2 = 0;
                    } else {
                        v4_2 = 1;
                    }
                    break;
                case 6:
                    v10 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p13, p14);
                    break;
                case 7:
                    v11 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p13, p14);
                    break;
                default:
                    p13.skipValue();
            }
        }
        android.view.animation.Interpolator v7_0;
        vn.ai.trueid.lottie.value.Keyframe v6_1;
        p13.endObject();
        if (v4_2 == null) {
            if ((v0 == null) || (v1_0 == null)) {
                android.view.animation.Interpolator v13_1 = vn.ai.trueid.lottie.parser.KeyframeParser.LINEAR_INTERPOLATOR;
            } else {
                v13_1 = vn.ai.trueid.lottie.parser.KeyframeParser.interpolatorFor(v0, v1_0);
            }
            v7_0 = v13_1;
            v6_1 = v3_2;
        } else {
            v7_0 = vn.ai.trueid.lottie.parser.KeyframeParser.LINEAR_INTERPOLATOR;
            v6_1 = v5;
        }
        vn.ai.trueid.lottie.value.Keyframe v3_1 = new vn.ai.trueid.lottie.value.Keyframe(p12, v5, v6_1, v7_0, v8, 0);
        v3_1.pathCp1 = v10;
        v3_1.pathCp2 = v11;
        return v3_1;
    }

    private static vn.ai.trueid.lottie.value.Keyframe parseMultiDimensionalKeyframe(vn.ai.trueid.lottie.LottieComposition p21, vn.ai.trueid.lottie.parser.moshi.JsonReader p22, float p23, vn.ai.trueid.lottie.parser.ValueParser p24)
    {
        p22.beginObject();
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v6_4 = 0;
        android.graphics.PointF v7 = 0;
        vn.ai.trueid.lottie.value.Keyframe v8_3 = 0;
        vn.ai.trueid.lottie.LottieComposition v9_2 = 0;
        Object v10 = 0;
        double v11_1 = 0;
        float v12_0 = 0;
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v13_0 = 0;
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v14_0 = 0;
        int v15_0 = 0;
        float v16 = 0;
        Object v17 = 0;
        while (p22.hasNext()) {
            switch (p22.selectName(vn.ai.trueid.lottie.parser.KeyframeParser.NAMES)) {
                case 0:
                    android.view.animation.Interpolator v19 = v12_0;
                    v16 = ((float) p22.nextDouble());
                    break;
                case 1:
                    v19 = v12_0;
                    v10 = p24.parse(p22, p23);
                    break;
                case 2:
                    v19 = v12_0;
                    v17 = p24.parse(p22, p23);
                    break;
                case 3:
                    int v20_1 = v6_4;
                    if (p22.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT) {
                        v7 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p22, p23);
                    } else {
                        p22.beginObject();
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v3_10 = 0;
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4_9 = 0;
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v5_3 = 0;
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v6_5 = 0;
                        while (p22.hasNext()) {
                            android.view.animation.Interpolator v19_0;
                            vn.ai.trueid.lottie.LottieComposition v9_4 = p22.selectName(vn.ai.trueid.lottie.parser.KeyframeParser.INTERPOLATOR_NAMES);
                            if (v9_4 == null) {
                                v19_0 = v12_0;
                                vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v5_4 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER;
                                if (p22.peek() != v5_4) {
                                    p22.beginArray();
                                    v3_10 = ((float) p22.nextDouble());
                                    if (p22.peek() != v5_4) {
                                        v5_3 = v3_10;
                                    } else {
                                        v5_3 = ((float) p22.nextDouble());
                                    }
                                    p22.endArray();
                                } else {
                                    v5_3 = ((float) p22.nextDouble());
                                    v3_10 = v5_3;
                                }
                            } else {
                                if (v9_4 == 1) {
                                    vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v6_6 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER;
                                    if (p22.peek() != v6_6) {
                                        v19_0 = v12_0;
                                        p22.beginArray();
                                        v4_9 = ((float) p22.nextDouble());
                                        if (p22.peek() != v6_6) {
                                            v6_5 = v4_9;
                                        } else {
                                            v6_5 = ((float) p22.nextDouble());
                                        }
                                        p22.endArray();
                                    } else {
                                        v19_0 = v12_0;
                                        v6_5 = ((float) p22.nextDouble());
                                        v4_9 = v6_5;
                                    }
                                } else {
                                    p22.skipValue();
                                }
                            }
                            v12_0 = v19_0;
                        }
                        v9_2 = new android.graphics.PointF(v3_10, v4_9);
                        v11_1 = new android.graphics.PointF(v5_3, v6_5);
                        p22.endObject();
                    }
                    v6_4 = v20_1;
                    break;
                case 4:
                    if (p22.peek() != vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT) {
                        v8_3 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p22, p23);
                    } else {
                        p22.beginObject();
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v3_7 = 0;
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v5_2 = 0;
                        float v12_1 = 0;
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v13_3 = 0;
                        while (p22.hasNext()) {
                            int v20_0;
                            vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4_2 = p22.selectName(vn.ai.trueid.lottie.parser.KeyframeParser.INTERPOLATOR_NAMES);
                            if (v4_2 == null) {
                                v20_0 = v6_4;
                                vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4_3 = v14_0;
                                vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v6_0 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER;
                                if (p22.peek() != v6_0) {
                                    vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v6_1;
                                    v14_0 = v4_3;
                                    p22.beginArray();
                                    vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v3_4 = ((float) p22.nextDouble());
                                    if (p22.peek() != v6_0) {
                                        v6_1 = v3_4;
                                        v12_1 = v6_1;
                                    } else {
                                        v6_1 = v3_4;
                                        v12_1 = ((float) p22.nextDouble());
                                    }
                                    p22.endArray();
                                    v3_7 = v6_1;
                                } else {
                                    v14_0 = v4_3;
                                    v12_1 = ((float) p22.nextDouble());
                                    v3_7 = v12_1;
                                }
                            } else {
                                v20_0 = v6_4;
                                if (v4_2 == 1) {
                                    vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v5_0 = vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER;
                                    if (p22.peek() != v5_0) {
                                        p22.beginArray();
                                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4_6 = v14_0;
                                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v6_3 = ((float) p22.nextDouble());
                                        if (p22.peek() != v5_0) {
                                            v13_3 = v6_3;
                                        } else {
                                            v13_3 = ((float) p22.nextDouble());
                                        }
                                        p22.endArray();
                                        v14_0 = v4_6;
                                        v5_2 = v6_3;
                                    } else {
                                        v13_3 = ((float) p22.nextDouble());
                                        v5_2 = v13_3;
                                    }
                                } else {
                                    p22.skipValue();
                                }
                            }
                            v6_4 = v20_0;
                        }
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4_0 = new android.graphics.PointF(v3_7, v5_2);
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v3_1 = new android.graphics.PointF(v12_1, v13_3);
                        p22.endObject();
                        v13_0 = v3_1;
                        v12_0 = v4_0;
                    }
                    break;
                case 5:
                    if (p22.nextInt() != 1) {
                        v6_4 = 0;
                    } else {
                        v6_4 = 1;
                    }
                    break;
                case 6:
                    v14_0 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p22, p23);
                    break;
                case 7:
                    v15_0 = vn.ai.trueid.lottie.parser.JsonUtils.jsonToPoint(p22, p23);
                    break;
                default:
                    p22.skipValue();
            }
        }
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v13_5;
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v3_14;
        float v12_3;
        double v11_11;
        int v20_2 = v6_4;
        android.view.animation.Interpolator v19_1 = v12_0;
        p22.endObject();
        if (v20_2 == 0) {
            if ((v7 == null) || (v8_3 == null)) {
                if ((v9_2 == null) || ((v11_1 == 0) || ((v19_1 == null) || (v13_0 == null)))) {
                    android.view.animation.Interpolator v0_1 = vn.ai.trueid.lottie.parser.KeyframeParser.LINEAR_INTERPOLATOR;
                } else {
                    v12_3 = vn.ai.trueid.lottie.parser.KeyframeParser.interpolatorFor(v9_2, v19_1);
                    v13_5 = vn.ai.trueid.lottie.parser.KeyframeParser.interpolatorFor(v11_1, v13_0);
                    v11_11 = v17;
                    v3_14 = 0;
                    if ((v12_3 == 0) || (v13_5 == null)) {
                        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v4_11 = v14_0;
                        android.view.animation.Interpolator v0_4 = v15_0;
                        vn.ai.trueid.lottie.value.Keyframe v8_1 = new vn.ai.trueid.lottie.value.Keyframe(p21, v10, v11_11, v3_14, v16, 0);
                    } else {
                        v0_4 = v15_0;
                        v4_11 = v14_0;
                        v8_1 = new vn.ai.trueid.lottie.value.Keyframe(p21, v10, v11_11, v12_3, v13_5, v16, 0);
                    }
                    v8_1.pathCp1 = v4_11;
                    v8_1.pathCp2 = v0_4;
                    return v8_1;
                }
            } else {
                v0_1 = vn.ai.trueid.lottie.parser.KeyframeParser.interpolatorFor(v7, v8_3);
            }
            v3_14 = v0_1;
            v11_11 = v17;
            v12_3 = 0;
            v13_5 = 0;
        } else {
            v3_14 = vn.ai.trueid.lottie.parser.KeyframeParser.LINEAR_INTERPOLATOR;
            v11_11 = v10;
        }
    }

    private static vn.ai.trueid.lottie.value.Keyframe parseStaticValue(vn.ai.trueid.lottie.parser.moshi.JsonReader p0, float p1, vn.ai.trueid.lottie.parser.ValueParser p2)
    {
        return new vn.ai.trueid.lottie.value.Keyframe(p2.parse(p0, p1));
    }

    private static P.O pathInterpolatorCache()
    {
        if (vn.ai.trueid.lottie.parser.KeyframeParser.pathInterpolatorCache == null) {
            vn.ai.trueid.lottie.parser.KeyframeParser.pathInterpolatorCache = new P.O(0);
        }
        return vn.ai.trueid.lottie.parser.KeyframeParser.pathInterpolatorCache;
    }

    private static void putInterpolator(int p2, ref.WeakReference p3)
    {
        try {
            vn.ai.trueid.lottie.parser.KeyframeParser.pathInterpolatorCache.j(p2, p3);
            return;
        } catch (Throwable v2_1) {
            throw v2_1;
        }
    }
}

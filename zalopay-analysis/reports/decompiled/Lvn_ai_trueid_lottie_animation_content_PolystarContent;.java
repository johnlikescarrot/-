package vn.ai.trueid.lottie.animation.content;
public class PolystarContent implements vn.ai.trueid.lottie.animation.content.PathContent, vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener, vn.ai.trueid.lottie.animation.content.KeyPathElementContent {
    private static final float POLYGON_MAGIC_NUMBER = 16000;
    private static final float POLYSTAR_MAGIC_NUMBER = 1056236141;
    private final boolean hidden;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation innerRadiusAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation innerRoundednessAnimation;
    private boolean isPathValid;
    private final boolean isReversed;
    private final android.graphics.Path lastSegmentPath;
    private final android.graphics.PathMeasure lastSegmentPathMeasure;
    private final float[] lastSegmentPosition;
    private final vn.ai.trueid.lottie.LottieDrawable lottieDrawable;
    private final String name;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation outerRadiusAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation outerRoundednessAnimation;
    private final android.graphics.Path path;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation pointsAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation positionAnimation;
    private final vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation rotationAnimation;
    private final vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent trimPaths;
    private final vn.ai.trueid.lottie.model.content.PolystarShape$Type type;

    public PolystarContent(vn.ai.trueid.lottie.LottieDrawable p8, vn.ai.trueid.lottie.model.layer.BaseLayer p9, vn.ai.trueid.lottie.model.content.PolystarShape p10)
    {
        this.path = new android.graphics.Path();
        this.lastSegmentPath = new android.graphics.Path();
        this.lastSegmentPathMeasure = new android.graphics.PathMeasure();
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_2 = new float[2];
        this.lastSegmentPosition = v0_2;
        this.trimPaths = new vn.ai.trueid.lottie.animation.content.CompoundTrimPathContent();
        this.lottieDrawable = p8;
        this.name = p10.getName();
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v8_2 = p10.getType();
        this.type = v8_2;
        this.hidden = p10.isHidden();
        this.isReversed = p10.isReversed();
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_9 = p10.getPoints().createAnimation();
        this.pointsAnimation = v0_9;
        vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation v1_1 = p10.getPosition().createAnimation();
        this.positionAnimation = v1_1;
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v2_1 = p10.getRotation().createAnimation();
        this.rotationAnimation = v2_1;
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v3_1 = p10.getOuterRadius().createAnimation();
        this.outerRadiusAnimation = v3_1;
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v4_1 = p10.getOuterRoundedness().createAnimation();
        this.outerRoundednessAnimation = v4_1;
        vn.ai.trueid.lottie.model.content.PolystarShape$Type v5 = vn.ai.trueid.lottie.model.content.PolystarShape$Type.STAR;
        if (v8_2 != v5) {
            this.innerRadiusAnimation = 0;
            this.innerRoundednessAnimation = 0;
        } else {
            this.innerRadiusAnimation = p10.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = p10.getInnerRoundedness().createAnimation();
        }
        p9.addAnimation(v0_9);
        p9.addAnimation(v1_1);
        p9.addAnimation(v2_1);
        p9.addAnimation(v3_1);
        p9.addAnimation(v4_1);
        if (v8_2 == v5) {
            p9.addAnimation(this.innerRadiusAnimation);
            p9.addAnimation(this.innerRoundednessAnimation);
        }
        v0_9.addUpdateListener(this);
        v1_1.addUpdateListener(this);
        v2_1.addUpdateListener(this);
        v3_1.addUpdateListener(this);
        v4_1.addUpdateListener(this);
        if (v8_2 == v5) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
        return;
    }

    private void createPolygonPath()
    {
        float[] v2_8;
        android.graphics.Path v1_1 = ((int) Math.floor(((double) ((Float) this.pointsAnimation.getValue()).floatValue())));
        float[] v2_0 = this.rotationAnimation;
        if (v2_0 != null) {
            v2_8 = ((double) ((Float) v2_0.getValue()).floatValue());
        } else {
            v2_8 = 0;
        }
        float[] v2_10 = Math.toRadians((v2_8 - 4636033603912859648));
        float v4_5 = ((double) v1_1);
        android.graphics.Path v1_6 = ((float) (4618760256179416344 / v4_5));
        float v6_4 = (((Float) this.outerRoundednessAnimation.getValue()).floatValue() / 1120403456);
        float v7_4 = ((Float) this.outerRadiusAnimation.getValue()).floatValue();
        double v8 = ((double) v7_4);
        android.graphics.Path v10_0 = ((float) (Math.cos(v2_10) * v8));
        float[] v11_0 = ((float) (Math.sin(v2_10) * v8));
        this.path.moveTo(v10_0, v11_0);
        double v12_1 = ((double) v1_6);
        float[] v2_11 = (v2_10 + v12_1);
        float v4_3 = Math.ceil(v4_5);
        double v16 = v2_11;
        int v14 = 0;
        while(true) {
            android.graphics.Path v1_9 = ((double) v14);
            if (v1_9 >= v4_3) {
                break;
            }
            float v29;
            float v31;
            double v20 = v1_9;
            android.graphics.Path v1_18 = ((float) (Math.cos(v16) * v8));
            float[] v2_15 = ((float) (Math.sin(v16) * v8));
            if (v6_4 == 0) {
                v10_0 = v1_18;
                v11_0 = v2_15;
                v29 = v4_3;
                v31 = v6_4;
                if (v20 != (v29 - 4607182418800017408)) {
                    this.path.lineTo(v10_0, v11_0);
                    v16 += v12_1;
                }
            } else {
                android.graphics.Path v27;
                float[] v28;
                v29 = v4_3;
                v31 = v6_4;
                int v3_3 = ((double) ((float) (Math.atan2(((double) v11_0), ((double) v10_0)) - 4609753056924675352)));
                int v3_10 = ((double) ((float) (Math.atan2(((double) v2_15), ((double) v1_18)) - 4609753056924675352)));
                float v6_2 = ((v7_4 * v31) * 1048576000);
                float v4_1 = (v6_2 * ((float) Math.cos(v3_3)));
                android.graphics.Path v22_1 = (v6_2 * ((float) Math.sin(v3_3)));
                float v5_6 = (((float) Math.cos(v3_10)) * v6_2);
                float v6_3 = (v6_2 * ((float) Math.sin(v3_10)));
                if (v20 != (v29 - 4607182418800017408)) {
                    v27 = v1_18;
                    v28 = v2_15;
                    this.path.cubicTo((v10_0 - v4_1), (v11_0 - v22_1), (v27 + v5_6), (v28 + v6_3), v27, v28);
                } else {
                    this.lastSegmentPath.reset();
                    this.lastSegmentPath.moveTo(v10_0, v11_0);
                    float v33 = (v10_0 - v4_1);
                    float v34 = (v11_0 - v22_1);
                    float v35 = (v1_18 + v5_6);
                    float v36 = (v2_15 + v6_3);
                    v27 = v1_18;
                    v28 = v2_15;
                    this.lastSegmentPath.cubicTo(v33, v34, v35, v36, v27, v28);
                    this.lastSegmentPathMeasure.setPath(this.lastSegmentPath, 0);
                    android.graphics.Path v1_4 = this.lastSegmentPathMeasure;
                    v1_4.getPosTan((v1_4.getLength() * 1065351538), this.lastSegmentPosition, 0);
                    float[] v2_7 = this.lastSegmentPosition;
                    this.path.cubicTo(v33, v34, v35, v36, v2_7[0], v2_7[1]);
                }
                v10_0 = v27;
                v11_0 = v28;
            }
            v14++;
            v4_3 = v29;
            v6_4 = v31;
        }
        android.graphics.Path v1_13 = ((android.graphics.PointF) this.positionAnimation.getValue());
        this.path.offset(v1_13.x, v1_13.y);
        this.path.close();
        return;
    }

    private void createStarPath()
    {
        double v2_6;
        int v1_12 = ((Float) this.pointsAnimation.getValue()).floatValue();
        double v2_20 = this.rotationAnimation;
        if (v2_20 != 0) {
            v2_6 = ((double) ((Float) v2_20.getValue()).floatValue());
        } else {
            v2_6 = 0;
        }
        double v2_8 = Math.toRadians((v2_6 - 4636033603912859648));
        float v4_8 = ((double) v1_12);
        int v6_12 = ((float) (4618760256179416344 / v4_8));
        if (this.isReversed) {
            v6_12 *= -1082130432;
        }
        float v8_8 = (v6_12 / 1073741824);
        int v1_10 = (v1_12 - ((float) ((int) v1_12)));
        float v10_1 = v1_10 cmp 0;
        if (v10_1 != 0) {
            v2_8 += ((double) ((1065353216 - v1_10) * v8_8));
        }
        int v13_1;
        float v11_7 = ((Float) this.outerRadiusAnimation.getValue()).floatValue();
        float v12_3 = ((Float) this.innerRadiusAnimation.getValue()).floatValue();
        int v13_0 = this.innerRoundednessAnimation;
        if (v13_0 == 0) {
            v13_1 = 0;
        } else {
            v13_1 = (((Float) v13_0.getValue()).floatValue() / 1120403456);
        }
        int v15_1;
        int v15_0 = this.outerRoundednessAnimation;
        if (v15_0 == 0) {
            v15_1 = 0;
        } else {
            v15_1 = (((Float) v15_0.getValue()).floatValue() / 1120403456);
        }
        float v14_1;
        android.graphics.Path v9_4;
        float v16;
        float v10_0;
        double v2_2;
        float v7_2;
        float v20;
        float v8_1;
        float v17;
        if (v10_1 == 0) {
            v20 = 1073741824;
            int v21_2 = v8_8;
            v16 = 0;
            v17 = v10_1;
            float v7_7 = ((double) v11_7);
            v9_4 = ((float) (Math.cos(v2_8) * v7_7));
            v8_1 = ((float) (v7_7 * Math.sin(v2_8)));
            this.path.moveTo(v9_4, v8_1);
            v10_0 = v1_10;
            v7_2 = v21_2;
            v2_2 = (v2_8 + ((double) v7_2));
            v14_1 = 0;
        } else {
            v14_1 = androidx.activity.result.e.a(v11_7, v12_3, v1_10, v12_3);
            v16 = 0;
            v17 = v10_1;
            android.graphics.Path v9_11 = ((double) v14_1);
            v20 = 1073741824;
            int v21_0 = v8_8;
            float v7_1 = ((float) (Math.cos(v2_8) * v9_11));
            v8_1 = ((float) (Math.sin(v2_8) * v9_11));
            this.path.moveTo(v7_1, v8_1);
            v2_2 = (v2_8 + ((double) ((v6_12 * v1_10) / 1073741824)));
            v10_0 = v1_10;
            v9_4 = v7_1;
            v7_2 = v21_0;
        }
        int v1_1 = 0;
        int v21_1 = 0;
        double v22 = (Math.ceil(v4_8) * 4611686018427387904);
        float v3_0 = v8_1;
        double v2_3 = v9_4;
        float v8_2 = v2_2;
        while(true) {
            float v4_2 = ((double) v1_1);
            if (v4_2 >= v22) {
                break;
            }
            float v24_1;
            if (v21_1 == 0) {
                v24_1 = v12_3;
            } else {
                v24_1 = v11_7;
            }
            float v26_2;
            int v1_7;
            float v25_0 = v14_1 cmp v16;
            if ((v25_0 == 0) || (v4_2 != (v22 - 4611686018427387904))) {
                v26_2 = v1_1;
                v1_7 = v7_2;
            } else {
                v26_2 = v1_1;
                v1_7 = ((v6_12 * v10_0) / v20);
            }
            if ((v25_0 == 0) || (v4_2 != (v22 - 4607182418800017408))) {
                float v29_1 = v4_2;
                float v4_4 = v24_1;
            } else {
                v29_1 = v4_2;
                v4_4 = v14_1;
            }
            float v39;
            float v31_1;
            float v24_4;
            float v38;
            float v31_0 = ((double) v4_4);
            float v4_7 = ((float) (Math.cos(v8_2) * v31_0));
            float v40 = v6_12;
            float v5_1 = ((float) (Math.sin(v8_2) * v31_0));
            if ((v13_1 != v16) || (v15_1 != v16)) {
                float v3_2;
                v24_4 = v7_2;
                v31_1 = v8_2;
                int v6_6 = ((double) ((float) (Math.atan2(((double) v3_0), ((double) v2_3)) - 4609753056924675352)));
                v39 = v5_1;
                double v2_13 = ((double) ((float) (Math.atan2(((double) v5_1), ((double) v4_7)) - 4609753056924675352)));
                if (v21_1 == 0) {
                    v3_2 = v15_1;
                } else {
                    v3_2 = v13_1;
                }
                int v6_11;
                if (v21_1 == 0) {
                    v6_11 = v13_1;
                } else {
                    v6_11 = v15_1;
                }
                float v33_1;
                if (v21_1 == 0) {
                    v33_1 = v11_7;
                } else {
                    v33_1 = v12_3;
                }
                float v34_0;
                if (v21_1 == 0) {
                    v34_0 = v12_3;
                } else {
                    v34_0 = v11_7;
                }
                float v33_3 = ((v33_1 * v3_2) * 1056236141);
                float v8_7 = (((float) Math.cos(v6_6)) * v33_3);
                float v33_4 = (v33_3 * ((float) Math.sin(v6_6)));
                float v34_2 = ((v34_0 * v6_11) * 1056236141);
                float v5_5 = (((float) Math.cos(v2_13)) * v34_2);
                float v34_3 = (v34_2 * ((float) Math.sin(v2_13)));
                if (v17 != 0) {
                    if (v26_2 != 0) {
                        if (v29_1 == (v22 - 4607182418800017408)) {
                            v5_5 *= v10_0;
                            v34_3 *= v10_0;
                        }
                    } else {
                        v8_7 *= v10_0;
                        v33_4 *= v10_0;
                    }
                }
                v38 = v4_7;
                this.path.cubicTo((v2_3 - v8_7), (v3_0 - v33_4), (v4_7 + v5_5), (v39 + v34_3), v38, v39);
            } else {
                this.path.lineTo(v4_7, v5_1);
                v38 = v4_7;
                v39 = v5_1;
                v24_4 = v7_2;
                v31_1 = v8_2;
            }
            v8_2 = (v31_1 + ((double) v1_7));
            v21_1 ^= 1;
            v1_1 = (v26_2 + 1);
            v7_2 = v24_4;
            v2_3 = v38;
            v3_0 = v39;
            v6_12 = v40;
        }
        int v1_4 = ((android.graphics.PointF) this.positionAnimation.getValue());
        this.path.offset(v1_4.x, v1_4.y);
        this.path.close();
        return;
    }

    private void invalidate()
    {
        this.isPathValid = 0;
        this.lottieDrawable.invalidateSelf();
        return;
    }

    public void addValueCallback(Object p2, vn.ai.trueid.lottie.value.LottieValueCallback p3)
    {
        if (p2 != vn.ai.trueid.lottie.LottieProperty.POLYSTAR_POINTS) {
            if (p2 != vn.ai.trueid.lottie.LottieProperty.POLYSTAR_ROTATION) {
                if (p2 != vn.ai.trueid.lottie.LottieProperty.POSITION) {
                    if (p2 == vn.ai.trueid.lottie.LottieProperty.POLYSTAR_INNER_RADIUS) {
                        Float v0_8 = this.innerRadiusAnimation;
                        if (v0_8 != null) {
                            v0_8.setValueCallback(p3);
                            return;
                        }
                    }
                    if (p2 != vn.ai.trueid.lottie.LottieProperty.POLYSTAR_OUTER_RADIUS) {
                        if (p2 == vn.ai.trueid.lottie.LottieProperty.POLYSTAR_INNER_ROUNDEDNESS) {
                            Float v0_3 = this.innerRoundednessAnimation;
                            if (v0_3 != null) {
                                v0_3.setValueCallback(p3);
                                return;
                            }
                        }
                        if (p2 == vn.ai.trueid.lottie.LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
                            this.outerRoundednessAnimation.setValueCallback(p3);
                        }
                        return;
                    } else {
                        this.outerRadiusAnimation.setValueCallback(p3);
                        return;
                    }
                } else {
                    this.positionAnimation.setValueCallback(p3);
                    return;
                }
            } else {
                this.rotationAnimation.setValueCallback(p3);
                return;
            }
        } else {
            this.pointsAnimation.setValueCallback(p3);
            return;
        }
    }

    public String getName()
    {
        return this.name;
    }

    public android.graphics.Path getPath()
    {
        if (!this.isPathValid) {
            this.path.reset();
            if (!this.hidden) {
                android.graphics.Path v0_1 = vn.ai.trueid.lottie.animation.content.PolystarContent$1.$SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[this.type.ordinal()];
                if (v0_1 == 1) {
                    this.createStarPath();
                } else {
                    if (v0_1 == 2) {
                        this.createPolygonPath();
                    }
                }
                this.path.close();
                this.trimPaths.apply(this.path);
                this.isPathValid = 1;
                return this.path;
            } else {
                this.isPathValid = 1;
                return this.path;
            }
        } else {
            return this.path;
        }
    }

    public void onValueChanged()
    {
        this.invalidate();
        return;
    }

    public void resolveKeyPath(vn.ai.trueid.lottie.model.KeyPath p1, int p2, java.util.List p3, vn.ai.trueid.lottie.model.KeyPath p4)
    {
        vn.ai.trueid.lottie.utils.MiscUtils.resolveKeyPath(p1, p2, p3, p4, this);
        return;
    }

    public void setContents(java.util.List p4, java.util.List p5)
    {
        p5 = 0;
        while (p5 < p4.size()) {
            vn.ai.trueid.lottie.animation.content.TrimPathContent v0_3 = ((vn.ai.trueid.lottie.animation.content.Content) p4.get(p5));
            if ((v0_3 instanceof vn.ai.trueid.lottie.animation.content.TrimPathContent)) {
                vn.ai.trueid.lottie.animation.content.TrimPathContent v0_0 = ((vn.ai.trueid.lottie.animation.content.TrimPathContent) v0_3);
                if (v0_0.getType() == vn.ai.trueid.lottie.model.content.ShapeTrimPath$Type.SIMULTANEOUSLY) {
                    this.trimPaths.addTrimPath(v0_0);
                    v0_0.addListener(this);
                }
            }
            p5++;
        }
        return;
    }
}

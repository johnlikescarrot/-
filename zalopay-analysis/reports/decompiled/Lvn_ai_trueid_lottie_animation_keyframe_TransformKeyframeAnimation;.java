package vn.ai.trueid.lottie.animation.keyframe;
public class TransformKeyframeAnimation {
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation anchorPoint;
    private final boolean autoOrient;
    private float cachedCosX;
    private float cachedCosY;
    private float cachedRotationX;
    private float cachedRotationY;
    private float cachedRotationZ;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation endOpacity;
    private final android.graphics.Matrix matrix;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation opacity;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation position;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation rotation;
    private boolean rotation3DCacheDirty;
    private vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation rotationX;
    private vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation rotationY;
    private vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation rotationZ;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation scale;
    private vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation skew;
    private vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation skewAngle;
    private final android.graphics.Matrix skewMatrix1;
    private final android.graphics.Matrix skewMatrix2;
    private final android.graphics.Matrix skewMatrix3;
    private final float[] skewValues;
    private vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation startOpacity;

    public TransformKeyframeAnimation(vn.ai.trueid.lottie.model.animatable.AnimatableTransform p3)
    {
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_19;
        this.matrix = new android.graphics.Matrix();
        this.cachedRotationX = 2143289344;
        this.cachedRotationY = 2143289344;
        this.cachedRotationZ = 2143289344;
        this.cachedCosX = 1065353216;
        this.cachedCosY = 1065353216;
        this.rotation3DCacheDirty = 1;
        if (p3.getAnchorPoint() != null) {
            v0_19 = p3.getAnchorPoint().createAnimation();
        } else {
            v0_19 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_22;
        this.anchorPoint = v0_19;
        if (p3.getPosition() != null) {
            v0_22 = p3.getPosition().createAnimation();
        } else {
            v0_22 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_25;
        this.position = v0_22;
        if (p3.getScale() != null) {
            v0_25 = p3.getScale().createAnimation();
        } else {
            v0_25 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_29;
        this.scale = v0_25;
        if (p3.getRotation() != null) {
            v0_29 = p3.getRotation().createAnimation();
        } else {
            v0_29 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_32;
        this.rotation = v0_29;
        if (p3.getSkew() != null) {
            v0_32 = p3.getSkew().createAnimation();
        } else {
            v0_32 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_36;
        this.skew = v0_32;
        this.autoOrient = p3.isAutoOrient();
        if (p3.getRotationX() != null) {
            v0_36 = p3.getRotationX().createAnimation();
        } else {
            v0_36 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_39;
        this.rotationX = v0_36;
        if (p3.getRotationY() != null) {
            v0_39 = p3.getRotationY().createAnimation();
        } else {
            v0_39 = 0;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_43;
        this.rotationY = v0_39;
        if (p3.getRotationZ() != null) {
            v0_43 = p3.getRotationZ().createAnimation();
        } else {
            v0_43 = 0;
        }
        this.rotationZ = v0_43;
        if (this.skew == null) {
            this.skewMatrix1 = 0;
            this.skewMatrix2 = 0;
            this.skewMatrix3 = 0;
            this.skewValues = 0;
        } else {
            this.skewMatrix1 = new android.graphics.Matrix();
            this.skewMatrix2 = new android.graphics.Matrix();
            this.skewMatrix3 = new android.graphics.Matrix();
            vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_4 = new float[9];
            this.skewValues = v0_4;
        }
        vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue v0_8;
        if (p3.getSkewAngle() != null) {
            v0_8 = p3.getSkewAngle().createAnimation();
        } else {
            v0_8 = 0;
        }
        this.skewAngle = v0_8;
        if (p3.getOpacity() != null) {
            this.opacity = p3.getOpacity().createAnimation();
        }
        if (p3.getStartOpacity() == null) {
            this.startOpacity = 0;
        } else {
            this.startOpacity = p3.getStartOpacity().createAnimation();
        }
        if (p3.getEndOpacity() == null) {
            this.endOpacity = 0;
            return;
        } else {
            this.endOpacity = p3.getEndOpacity().createAnimation();
            return;
        }
    }

    public static synthetic void a(vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation p0)
    {
        p0.lambda$addListener$0();
        return;
    }

    public static synthetic void b(vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation p0)
    {
        p0.lambda$addListener$1();
        return;
    }

    public static synthetic void c(vn.ai.trueid.lottie.animation.keyframe.TransformKeyframeAnimation p0)
    {
        p0.lambda$addListener$2();
        return;
    }

    private void clearSkewValues()
    {
        int v0 = 0;
        while (v0 < 9) {
            this.skewValues[v0] = 0;
            v0++;
        }
        return;
    }

    private synthetic void lambda$addListener$0()
    {
        this.rotation3DCacheDirty = 1;
        return;
    }

    private synthetic void lambda$addListener$1()
    {
        this.rotation3DCacheDirty = 1;
        return;
    }

    private synthetic void lambda$addListener$2()
    {
        this.rotation3DCacheDirty = 1;
        return;
    }

    public void addAnimationsToLayer(vn.ai.trueid.lottie.model.layer.BaseLayer p2)
    {
        p2.addAnimation(this.opacity);
        p2.addAnimation(this.startOpacity);
        p2.addAnimation(this.endOpacity);
        p2.addAnimation(this.anchorPoint);
        p2.addAnimation(this.position);
        p2.addAnimation(this.scale);
        p2.addAnimation(this.rotation);
        p2.addAnimation(this.skew);
        p2.addAnimation(this.skewAngle);
        p2.addAnimation(this.rotationX);
        p2.addAnimation(this.rotationY);
        p2.addAnimation(this.rotationZ);
        return;
    }

    public void addListener(vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation$AnimationListener p4)
    {
        vn.ai.trueid.lottie.animation.keyframe.a v0_0 = this.opacity;
        if (v0_0 != null) {
            v0_0.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_7 = this.startOpacity;
        if (v0_7 != null) {
            v0_7.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_14 = this.endOpacity;
        if (v0_14 != null) {
            v0_14.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_15 = this.anchorPoint;
        if (v0_15 != null) {
            v0_15.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_1 = this.position;
        if (v0_1 != null) {
            v0_1.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_2 = this.scale;
        if (v0_2 != null) {
            v0_2.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_3 = this.rotation;
        if (v0_3 != null) {
            v0_3.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_4 = this.skew;
        if (v0_4 != null) {
            v0_4.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_5 = this.skewAngle;
        if (v0_5 != null) {
            v0_5.addUpdateListener(p4);
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_6 = this.rotationX;
        if (v0_6 != null) {
            v0_6.addUpdateListener(p4);
            this.rotationX.addUpdateListener(new vn.ai.trueid.lottie.animation.keyframe.a(0, this));
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_9 = this.rotationY;
        if (v0_9 != null) {
            v0_9.addUpdateListener(p4);
            this.rotationY.addUpdateListener(new vn.ai.trueid.lottie.animation.keyframe.a(1, this));
        }
        vn.ai.trueid.lottie.animation.keyframe.a v0_11 = this.rotationZ;
        if (v0_11 != null) {
            v0_11.addUpdateListener(p4);
            this.rotationZ.addUpdateListener(new vn.ai.trueid.lottie.animation.keyframe.a(2, this));
        }
        return;
    }

    public boolean applyValueCallback(Object p5, vn.ai.trueid.lottie.value.LottieValueCallback p6)
    {
        java.util.List v0_9 = Float.valueOf(1120403456);
        Float v1_1 = Float.valueOf(0);
        if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_ANCHOR_POINT) {
            if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_POSITION) {
                if (p5 == vn.ai.trueid.lottie.LottieProperty.TRANSFORM_POSITION_X) {
                    Float v2_1 = this.position;
                    if ((v2_1 instanceof vn.ai.trueid.lottie.animation.keyframe.SplitDimensionPathKeyframeAnimation)) {
                        ((vn.ai.trueid.lottie.animation.keyframe.SplitDimensionPathKeyframeAnimation) v2_1).setXValueCallback(p6);
                        return 1;
                    }
                }
                if (p5 == vn.ai.trueid.lottie.LottieProperty.TRANSFORM_POSITION_Y) {
                    Float v2_5 = this.position;
                    if ((v2_5 instanceof vn.ai.trueid.lottie.animation.keyframe.SplitDimensionPathKeyframeAnimation)) {
                        ((vn.ai.trueid.lottie.animation.keyframe.SplitDimensionPathKeyframeAnimation) v2_5).setYValueCallback(p6);
                        return 1;
                    }
                }
                if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_SCALE) {
                    if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_ROTATION) {
                        if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_OPACITY) {
                            if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_START_OPACITY) {
                                if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_END_OPACITY) {
                                    if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_SKEW) {
                                        if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_SKEW_ANGLE) {
                                            if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_ROTATION_X) {
                                                if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_ROTATION_Y) {
                                                    if (p5 != vn.ai.trueid.lottie.LottieProperty.TRANSFORM_ROTATION_Z) {
                                                        return 0;
                                                    } else {
                                                        if (this.rotationZ == null) {
                                                            this.rotationZ = new vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(v1_1)));
                                                        }
                                                        this.rotationZ.setValueCallback(p6);
                                                    }
                                                } else {
                                                    if (this.rotationY == null) {
                                                        this.rotationY = new vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(v1_1)));
                                                    }
                                                    this.rotationY.setValueCallback(p6);
                                                }
                                            } else {
                                                if (this.rotationX == null) {
                                                    this.rotationX = new vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(v1_1)));
                                                }
                                                this.rotationX.setValueCallback(p6);
                                            }
                                        } else {
                                            if (this.skewAngle == null) {
                                                this.skewAngle = new vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(v1_1)));
                                            }
                                            this.skewAngle.setValueCallback(p6);
                                        }
                                    } else {
                                        if (this.skew == null) {
                                            this.skew = new vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation(java.util.Collections.singletonList(new vn.ai.trueid.lottie.value.Keyframe(v1_1)));
                                        }
                                        this.skew.setValueCallback(p6);
                                    }
                                } else {
                                    vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v5_43 = this.endOpacity;
                                    if (v5_43 != null) {
                                        v5_43.setValueCallback(p6);
                                    } else {
                                        this.endOpacity = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p6, v0_9);
                                    }
                                }
                            } else {
                                vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v5_3 = this.startOpacity;
                                if (v5_3 != null) {
                                    v5_3.setValueCallback(p6);
                                } else {
                                    this.startOpacity = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p6, v0_9);
                                }
                            }
                        } else {
                            vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v5_6 = this.opacity;
                            if (v5_6 != null) {
                                v5_6.setValueCallback(p6);
                            } else {
                                this.opacity = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p6, Integer.valueOf(100));
                            }
                        }
                    } else {
                        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v5_9 = this.rotation;
                        if (v5_9 != null) {
                            v5_9.setValueCallback(p6);
                        } else {
                            this.rotation = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p6, v1_1);
                        }
                    }
                } else {
                    vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v5_12 = this.scale;
                    if (v5_12 != null) {
                        v5_12.setValueCallback(p6);
                    } else {
                        this.scale = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p6, new vn.ai.trueid.lottie.value.ScaleXY());
                    }
                }
            } else {
                vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v5_15 = this.position;
                if (v5_15 != null) {
                    v5_15.setValueCallback(p6);
                } else {
                    this.position = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p6, new android.graphics.PointF());
                }
            }
        } else {
            vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v5_18 = this.anchorPoint;
            if (v5_18 != null) {
                v5_18.setValueCallback(p6);
            } else {
                this.anchorPoint = new vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation(p6, new android.graphics.PointF());
            }
        }
        return 1;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation getEndOpacity()
    {
        return this.endOpacity;
    }

    public android.graphics.Matrix getMatrix()
    {
        this.matrix.reset();
        android.graphics.Matrix v0_22 = this.rotationX;
        float v2_5 = 1065353216;
        if ((v0_22 == null) || (v0_22.getFloatValue() == 0)) {
            android.graphics.Matrix v0_1 = this.rotationY;
            if ((v0_1 == null) || (v0_1.getFloatValue() == 0)) {
                android.graphics.Matrix v0_14 = this.rotationZ;
                if ((v0_14 == null) || (v0_14.getFloatValue() == 0)) {
                    android.graphics.Matrix v0_38 = this.position;
                    if (v0_38 != null) {
                        android.graphics.Matrix v4_4 = ((android.graphics.PointF) v0_38.getValue());
                        if (v4_4 != null) {
                            android.graphics.PointF v5_5 = v4_4.x;
                            if ((v5_5 != 0) || (v4_4.y != 0)) {
                                this.matrix.preTranslate(v5_5, v4_4.y);
                            }
                        }
                    }
                    if (!this.autoOrient) {
                        android.graphics.Matrix v0_53 = this.rotation;
                        if (v0_53 != null) {
                            android.graphics.Matrix v0_55;
                            if (!(v0_53 instanceof vn.ai.trueid.lottie.animation.keyframe.ValueCallbackKeyframeAnimation)) {
                                v0_55 = ((vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation) v0_53).getFloatValue();
                            } else {
                                v0_55 = ((Float) v0_53.getValue()).floatValue();
                            }
                            if (v0_55 != 0) {
                                this.matrix.preRotate(v0_55);
                            }
                        }
                    } else {
                        if (v0_38 != null) {
                            android.graphics.Matrix v4_11 = v0_38.getProgress();
                            android.graphics.PointF v5_7 = ((android.graphics.PointF) v0_38.getValue());
                            android.graphics.PointF v6_14 = v5_7.x;
                            android.graphics.PointF v5_8 = v5_7.y;
                            v0_38.setProgress((953267991 + v4_11));
                            android.graphics.Matrix v7_5 = ((android.graphics.PointF) v0_38.getValue());
                            v0_38.setProgress(v4_11);
                            this.matrix.preRotate(((float) Math.toDegrees(Math.atan2(((double) (v7_5.y - v5_8)), ((double) (v7_5.x - v6_14))))));
                        }
                    }
                    android.graphics.Matrix v0_64 = this.skew;
                    if (v0_64 != null) {
                        android.graphics.Matrix v4_20;
                        android.graphics.Matrix v4_16 = this.skewAngle;
                        if (v4_16 != null) {
                            v4_20 = ((float) Math.cos(Math.toRadians(((double) ((- v4_16.getFloatValue()) + 1119092736)))));
                        } else {
                            v4_20 = 0;
                        }
                        android.graphics.PointF v5_3;
                        android.graphics.PointF v6_0 = this.skewAngle;
                        if (v6_0 != null) {
                            v5_3 = ((float) Math.sin(Math.toRadians(((double) ((- v6_0.getFloatValue()) + 1119092736)))));
                        } else {
                            v5_3 = 1065353216;
                        }
                        android.graphics.Matrix v0_3 = ((float) Math.tan(Math.toRadians(((double) v0_64.getFloatValue()))));
                        this.clearSkewValues();
                        android.graphics.PointF v6_7 = this.skewValues;
                        v6_7[0] = v4_20;
                        v6_7[1] = v5_3;
                        float v8_0 = (- v5_3);
                        v6_7[3] = v8_0;
                        v6_7[4] = v4_20;
                        v6_7[8] = 1065353216;
                        this.skewMatrix1.setValues(v6_7);
                        this.clearSkewValues();
                        android.graphics.PointF v6_8 = this.skewValues;
                        v6_8[0] = 1065353216;
                        v6_8[3] = v0_3;
                        v6_8[4] = 1065353216;
                        v6_8[8] = 1065353216;
                        this.skewMatrix2.setValues(v6_8);
                        this.clearSkewValues();
                        android.graphics.Matrix v0_6 = this.skewValues;
                        v0_6[0] = v4_20;
                        v0_6[1] = v8_0;
                        v0_6[3] = v5_3;
                        v0_6[4] = v4_20;
                        v0_6[8] = 1065353216;
                        this.skewMatrix3.setValues(v0_6);
                        this.skewMatrix2.preConcat(this.skewMatrix1);
                        this.skewMatrix3.preConcat(this.skewMatrix2);
                        this.matrix.preConcat(this.skewMatrix3);
                    }
                    android.graphics.Matrix v0_11 = this.scale;
                    if (v0_11 != null) {
                        android.graphics.Matrix v0_13 = ((vn.ai.trueid.lottie.value.ScaleXY) v0_11.getValue());
                        if ((v0_13 != null) && ((v0_13.getScaleX() != 1065353216) || (v0_13.getScaleY() != 1065353216))) {
                            this.matrix.preScale(v0_13.getScaleX(), v0_13.getScaleY());
                        }
                    }
                    android.graphics.Matrix v0_16 = this.anchorPoint;
                    if (v0_16 != null) {
                        android.graphics.Matrix v0_18 = ((android.graphics.PointF) v0_16.getValue());
                        if (v0_18 != null) {
                            vn.ai.trueid.lottie.value.ScaleXY v1_9 = v0_18.x;
                            if ((v1_9 != 0) || (v0_18.y != 0)) {
                                this.matrix.preTranslate((- v1_9), (- v0_18.y));
                            }
                        }
                    }
                    return this.matrix;
                }
            }
        }
        int v9_1;
        android.graphics.Matrix v0_24 = this.rotationX;
        if (v0_24 == null) {
            v9_1 = 0;
        } else {
            v9_1 = v0_24.getFloatValue();
        }
        int v10_1;
        android.graphics.Matrix v0_26 = this.rotationY;
        if (v0_26 == null) {
            v10_1 = 0;
        } else {
            v10_1 = v0_26.getFloatValue();
        }
        int v11_1;
        android.graphics.Matrix v0_29 = this.rotationZ;
        if (v0_29 == null) {
            v11_1 = 0;
        } else {
            v11_1 = v0_29.getFloatValue();
        }
        if ((this.rotation3DCacheDirty) || ((v9_1 != this.cachedRotationX) || ((v10_1 != this.cachedRotationY) || (v11_1 != this.cachedRotationZ)))) {
            this.cachedRotationX = v9_1;
            this.cachedRotationY = v10_1;
            this.cachedRotationZ = v11_1;
            if (v9_1 == 0) {
                this.cachedCosX = 1065353216;
            } else {
                this.cachedCosX = ((float) Math.cos(Math.toRadians(((double) v9_1))));
            }
            if (v10_1 == 0) {
                this.cachedCosY = 1065353216;
            } else {
                this.cachedCosY = ((float) Math.cos(Math.toRadians(((double) v10_1))));
            }
            this.rotation3DCacheDirty = 0;
        }
        android.graphics.PointF v5_4;
        android.graphics.Matrix v0_43 = this.anchorPoint;
        vn.ai.trueid.lottie.value.ScaleXY v1_11 = 0;
        if (v0_43 != null) {
            v5_4 = ((android.graphics.PointF) v0_43.getValue());
        } else {
            v5_4 = 0;
        }
        android.graphics.PointF v6_9;
        android.graphics.Matrix v0_46 = this.position;
        if (v0_46 != null) {
            v6_9 = ((android.graphics.PointF) v0_46.getValue());
        } else {
            v6_9 = 0;
        }
        android.graphics.Matrix v0_49 = this.scale;
        if (v0_49 != null) {
            v1_11 = ((vn.ai.trueid.lottie.value.ScaleXY) v0_49.getValue());
        }
        android.graphics.Matrix v7_1;
        if (v1_11 == null) {
            v7_1 = 1065353216;
        } else {
            v7_1 = v1_11.getScaleX();
        }
        if (v1_11 != null) {
            v2_5 = v1_11.getScaleY();
        }
        vn.ai.trueid.lottie.utils.Transform3D.applyTransform(this.matrix, v5_4, v6_9, v7_1, v2_5, v9_1, v10_1, v11_1, this.cachedCosX, this.cachedCosY);
        return this.matrix;
    }

    public android.graphics.Matrix getMatrixForRepeater(float p12)
    {
        android.graphics.Matrix v0_14;
        android.graphics.Matrix v0_0 = this.position;
        float v1_0 = 0;
        if (v0_0 != null) {
            v0_14 = ((android.graphics.PointF) v0_0.getValue());
        } else {
            v0_14 = 0;
        }
        vn.ai.trueid.lottie.value.ScaleXY v2_1;
        vn.ai.trueid.lottie.value.ScaleXY v2_2 = this.scale;
        if (v2_2 != null) {
            v2_1 = ((vn.ai.trueid.lottie.value.ScaleXY) v2_2.getValue());
        } else {
            v2_1 = 0;
        }
        float v3_2 = this.anchorPoint;
        if (v3_2 != 0) {
            v1_0 = ((android.graphics.PointF) v3_2.getValue());
        }
        this.matrix.reset();
        if (v0_14 != null) {
            this.matrix.preTranslate((v0_14.x * p12), (v0_14.y * p12));
        }
        double v5_1;
        android.graphics.Matrix v0_4 = this.rotationX;
        float v3_5 = 0;
        if (v0_4 == null) {
            v5_1 = 0;
        } else {
            v5_1 = (v0_4.getFloatValue() * p12);
        }
        int v6;
        android.graphics.Matrix v0_7 = this.rotationY;
        if (v0_7 == null) {
            v6 = 0;
        } else {
            v6 = (v0_7.getFloatValue() * p12);
        }
        android.graphics.Matrix v0_12;
        android.graphics.Matrix v0_11 = this.rotationZ;
        if (v0_11 == null) {
            v0_12 = 0;
        } else {
            v0_12 = (v0_11.getFloatValue() * p12);
        }
        android.graphics.Matrix v4_2 = v5_1 cmp 0;
        if ((v4_2 == null) && ((v6 == 0) && (v0_12 == 0))) {
            android.graphics.Matrix v0_15 = this.rotation;
            if (v0_15 != null) {
                double v5_2;
                float vtmp11 = ((Float) v0_15.getValue()).floatValue();
                if (v1_0 != 0) {
                    v5_2 = v1_0.x;
                } else {
                    v5_2 = 0;
                }
                if (v1_0 != 0) {
                    v3_5 = v1_0.y;
                }
                this.matrix.preRotate((vtmp11 * p12), v5_2, v3_5);
            }
        } else {
            float v8_0;
            float v7_2 = 1065353216;
            if (v4_2 == null) {
                v8_0 = 1065353216;
            } else {
                v8_0 = ((float) Math.cos(Math.toRadians(((double) v5_1))));
            }
            if (v6 != 0) {
                v7_2 = ((float) Math.cos(Math.toRadians(((double) v6))));
            }
            float v9_3 = v7_2;
            if (v0_12 != 0) {
                float v7_3;
                if (v1_0 != 0) {
                    v7_3 = v1_0.x;
                } else {
                    v7_3 = 0;
                }
                if (v1_0 != 0) {
                    v3_5 = v1_0.y;
                }
                this.matrix.preRotate(v0_12, v7_3, v3_5);
            }
            vn.ai.trueid.lottie.utils.Transform3D.apply3DRotations(this.matrix, v5_1, v6, 0, v8_0, v9_3);
        }
        if (v2_1 != null) {
            this.matrix.preScale(((float) Math.pow(((double) v2_1.getScaleX()), ((double) p12))), ((float) Math.pow(((double) v2_1.getScaleY()), ((double) p12))));
        }
        return this.matrix;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation getOpacity()
    {
        return this.opacity;
    }

    public vn.ai.trueid.lottie.animation.keyframe.BaseKeyframeAnimation getStartOpacity()
    {
        return this.startOpacity;
    }

    public void setProgress(float p2)
    {
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_0 = this.opacity;
        if (v0_0 != null) {
            v0_0.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_7 = this.startOpacity;
        if (v0_7 != null) {
            v0_7.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_10 = this.endOpacity;
        if (v0_10 != null) {
            v0_10.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_11 = this.anchorPoint;
        if (v0_11 != null) {
            v0_11.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_1 = this.position;
        if (v0_1 != null) {
            v0_1.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_2 = this.scale;
        if (v0_2 != null) {
            v0_2.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_3 = this.rotation;
        if (v0_3 != null) {
            v0_3.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_4 = this.skew;
        if (v0_4 != null) {
            v0_4.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_5 = this.skewAngle;
        if (v0_5 != null) {
            v0_5.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_6 = this.rotationX;
        if (v0_6 != null) {
            v0_6.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_8 = this.rotationY;
        if (v0_8 != null) {
            v0_8.setProgress(p2);
        }
        vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation v0_9 = this.rotationZ;
        if (v0_9 != null) {
            v0_9.setProgress(p2);
        }
        return;
    }
}

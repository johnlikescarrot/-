package vn.com.vng.zalopay.livenessdetection.presentation.domain.model;
public final class SelfieCheckConfig implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR = None;
    public static final wD.j Companion = None;
    public static final int DEFAULT_EAR_FRAMES = 3;
    public static final float DEFAULT_EAR_MAX_HAND_RATIO = 0;
    public static final float DEFAULT_EAR_MIN_SKIN_RATIO = 1036831949;
    public static final float DEFAULT_EYE_OPEN_THRESHOLD = 1053609165;
    public static final float DEFAULT_GLARE_CELL_RATIO = 1045220557;
    public static final float DEFAULT_GLARE_FACE_RATIO = 16000;
    public static final float DEFAULT_GLARE_HAIR_RATIO;
    public static final float DEFAULT_OTHER_FACE_MIN_RATIO;
    private final int earFrames;
    private final float earMaxHandRatio;
    private final float earMinSkinRatio;
    private final float eyeOpenThreshold;
    private final float glareCellRatio;
    private final float glareFaceRatio;
    private final float glareHairRatio;
    private final float otherFaceMinRatio;
    private final boolean portraitCrop;

    static SelfieCheckConfig()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig, 22853, 22855);
        vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig.Companion = new wD.j();
        vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig.CREATOR = new wD.k(0);
        return;
    }

    public SelfieCheckConfig()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 511, 0);
        return;
    }

    public SelfieCheckConfig(float p1, float p2, float p3, float p4, float p5, int p6, float p7, boolean p8, float p9)
    {
        this.glareFaceRatio = p1;
        this.glareCellRatio = p2;
        this.glareHairRatio = p3;
        this.earMinSkinRatio = p4;
        this.earMaxHandRatio = p5;
        this.earFrames = p6;
        this.eyeOpenThreshold = p7;
        this.portraitCrop = p8;
        this.otherFaceMinRatio = p9;
        return;
    }

    public synthetic SelfieCheckConfig(float p3, float p4, float p5, float p6, float p7, int p8, float p9, boolean p10, float p11, int p12, kotlin.jvm.internal.DefaultConstructorMarker p13)
    {
        if ((p12 & 1) != 0) {
            p3 = 1048576000;
        }
        if ((p12 & 2) != 0) {
            p4 = 1045220557;
        }
        if ((p12 & 4) != 0) {
            p5 = 1036831949;
        }
        if ((p12 & 8) != 0) {
            p6 = 1036831949;
        }
        if ((p12 & 16) != 0) {
            p7 = 1043878380;
        }
        if ((p12 & 32) != 0) {
            p8 = 3;
        }
        if ((p12 & 64) != 0) {
            p9 = 1053609165;
        }
        if ((p12 & 128) != 0) {
            p10 = 0;
        }
        float v12_2;
        if ((p12 & 256) == 0) {
            v12_2 = p11;
        } else {
            v12_2 = 1048576000;
        }
        this(p3, p4, p5, p6, p7, p8, p9, p10, v12_2);
        return;
    }

    public static synthetic vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig copy$default(vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig p0, float p1, float p2, float p3, float p4, float p5, int p6, float p7, boolean p8, float p9, int p10, Object p11)
    {
        if ((p10 & 1) != 0) {
            p1 = p0.glareFaceRatio;
        }
        if ((p10 & 2) != 0) {
            p2 = p0.glareCellRatio;
        }
        if ((p10 & 4) != 0) {
            p3 = p0.glareHairRatio;
        }
        if ((p10 & 8) != 0) {
            p4 = p0.earMinSkinRatio;
        }
        if ((p10 & 16) != 0) {
            p5 = p0.earMaxHandRatio;
        }
        if ((p10 & 32) != 0) {
            p6 = p0.earFrames;
        }
        if ((p10 & 64) != 0) {
            p7 = p0.eyeOpenThreshold;
        }
        if ((p10 & 128) != 0) {
            p8 = p0.portraitCrop;
        }
        if ((p10 & 256) != 0) {
            p9 = p0.otherFaceMinRatio;
        }
        return p0.copy(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public final float component1()
    {
        return this.glareFaceRatio;
    }

    public final float component2()
    {
        return this.glareCellRatio;
    }

    public final float component3()
    {
        return this.glareHairRatio;
    }

    public final float component4()
    {
        return this.earMinSkinRatio;
    }

    public final float component5()
    {
        return this.earMaxHandRatio;
    }

    public final int component6()
    {
        return this.earFrames;
    }

    public final float component7()
    {
        return this.eyeOpenThreshold;
    }

    public final boolean component8()
    {
        return this.portraitCrop;
    }

    public final float component9()
    {
        return this.otherFaceMinRatio;
    }

    public final vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig copy(float p11, float p12, float p13, float p14, float p15, int p16, float p17, boolean p18, float p19)
    {
        return new vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig(p11, p12, p13, p14, p15, p16, p17, p18, p19);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig)) {
                if (Float.compare(this.glareFaceRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).glareFaceRatio) == 0) {
                    if (Float.compare(this.glareCellRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).glareCellRatio) == 0) {
                        if (Float.compare(this.glareHairRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).glareHairRatio) == 0) {
                            if (Float.compare(this.earMinSkinRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).earMinSkinRatio) == 0) {
                                if (Float.compare(this.earMaxHandRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).earMaxHandRatio) == 0) {
                                    if (this.earFrames == ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).earFrames) {
                                        if (Float.compare(this.eyeOpenThreshold, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).eyeOpenThreshold) == 0) {
                                            if (this.portraitCrop == ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).portraitCrop) {
                                                if (Float.compare(this.otherFaceMinRatio, ((vn.com.vng.zalopay.livenessdetection.presentation.domain.model.SelfieCheckConfig) p5).otherFaceMinRatio) == 0) {
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

    public final float getCropSizePercentage()
    {
        int v0_1;
        if (!this.portraitCrop) {
            v0_1 = 1053609165;
        } else {
            v0_1 = 1061997773;
        }
        return v0_1;
    }

    public final int getEarFrames()
    {
        return this.earFrames;
    }

    public final float getEarMaxHandRatio()
    {
        return this.earMaxHandRatio;
    }

    public final float getEarMinSkinRatio()
    {
        return this.earMinSkinRatio;
    }

    public final float getEyeOpenThreshold()
    {
        return this.eyeOpenThreshold;
    }

    public final float getGlareCellRatio()
    {
        return this.glareCellRatio;
    }

    public final float getGlareFaceRatio()
    {
        return this.glareFaceRatio;
    }

    public final float getGlareHairRatio()
    {
        return this.glareHairRatio;
    }

    public final float getOtherFaceMinRatio()
    {
        return this.otherFaceMinRatio;
    }

    public final boolean getPortraitCrop()
    {
        return this.portraitCrop;
    }

    public int hashCode()
    {
        return (Float.hashCode(this.otherFaceMinRatio) + Kb.e.i(Kb.e.d(this.eyeOpenThreshold, Kb.e.v(this.earFrames, Kb.e.d(this.earMaxHandRatio, Kb.e.d(this.earMinSkinRatio, Kb.e.d(this.glareHairRatio, Kb.e.d(this.glareCellRatio, (Float.hashCode(this.glareFaceRatio) * 31), 31), 31), 31), 31), 31), 31), 31, this.portraitCrop));
    }

    public final boolean isEarCheckEnabled()
    {
        if ((this.earMinSkinRatio <= 0) && (this.earMaxHandRatio >= 1065353216)) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString()
    {
        String v0_0 = this.glareFaceRatio;
        float v1 = this.glareCellRatio;
        float v2 = this.glareHairRatio;
        float v3 = this.earMinSkinRatio;
        float v4 = this.earMaxHandRatio;
        int v5 = this.earFrames;
        float v6 = this.eyeOpenThreshold;
        boolean v7 = this.portraitCrop;
        float v8 = this.otherFaceMinRatio;
        StringBuilder v9_0 = new StringBuilder(wmoi.igob.ggcn(14878));
        v9_0.append(v0_0);
        v9_0.append(wmoi.igob.ggcn(14879));
        v9_0.append(v1);
        v9_0.append(wmoi.igob.ggcn(14880));
        v9_0.append(v2);
        v9_0.append(wmoi.igob.ggcn(14881));
        v9_0.append(v3);
        v9_0.append(wmoi.igob.ggcn(14882));
        v9_0.append(v4);
        v9_0.append(wmoi.igob.ggcn(14883));
        v9_0.append(v5);
        v9_0.append(wmoi.igob.ggcn(14884));
        v9_0.append(v6);
        v9_0.append(wmoi.igob.ggcn(14885));
        v9_0.append(v7);
        v9_0.append(wmoi.igob.ggcn(14886));
        return androidx.camera.camera2.internal.G.c(v8, wmoi.igob.ggcn(14887), v9_0);
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeFloat(this.glareFaceRatio);
        p1.writeFloat(this.glareCellRatio);
        p1.writeFloat(this.glareHairRatio);
        p1.writeFloat(this.earMinSkinRatio);
        p1.writeFloat(this.earMaxHandRatio);
        p1.writeInt(this.earFrames);
        p1.writeFloat(this.eyeOpenThreshold);
        p1.writeInt(this.portraitCrop);
        p1.writeFloat(this.otherFaceMinRatio);
        return;
    }
}

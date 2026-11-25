package vn.ai.trueid.sdk.utils;
public final class TrueIDExifData {
    private final Double apertureF;
    private final String copyright;
    private final String dateTime;
    private final Double exposureSeconds;
    private final Double focalLengthMm;
    private final String imageDescription;
    private final String imageUniqueId;
    private final Integer iso;
    private final String make;
    private final String model;
    private final Integer pixelHeight;
    private final Integer pixelWidth;
    private final String software;
    private final String subSecTime;
    private final String userComment;

    public TrueIDExifData(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, Double p9, Double p10, Integer p11, Double p12, Integer p13, Integer p14, String p15)
    {
        this.make = p1;
        this.model = p2;
        this.software = p3;
        this.dateTime = p4;
        this.imageDescription = p5;
        this.copyright = p6;
        this.imageUniqueId = p7;
        this.subSecTime = p8;
        this.apertureF = p9;
        this.focalLengthMm = p10;
        this.iso = p11;
        this.exposureSeconds = p12;
        this.pixelWidth = p13;
        this.pixelHeight = p14;
        this.userComment = p15;
        return;
    }

    public static synthetic vn.ai.trueid.sdk.utils.TrueIDExifData copy$default(vn.ai.trueid.sdk.utils.TrueIDExifData p16, String p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, Double p25, Double p26, Integer p27, Double p28, Integer p29, Integer p30, String p31, int p32, Object p33)
    {
        String v2_1;
        if ((p32 & 1) == 0) {
            v2_1 = p17;
        } else {
            v2_1 = p16.make;
        }
        String v3_1;
        if ((p32 & 2) == 0) {
            v3_1 = p18;
        } else {
            v3_1 = p16.model;
        }
        String v4_1;
        if ((p32 & 4) == 0) {
            v4_1 = p19;
        } else {
            v4_1 = p16.software;
        }
        String v5_1;
        if ((p32 & 8) == 0) {
            v5_1 = p20;
        } else {
            v5_1 = p16.dateTime;
        }
        String v6_1;
        if ((p32 & 16) == 0) {
            v6_1 = p21;
        } else {
            v6_1 = p16.imageDescription;
        }
        String v7_1;
        if ((p32 & 32) == 0) {
            v7_1 = p22;
        } else {
            v7_1 = p16.copyright;
        }
        String v8_1;
        if ((p32 & 64) == 0) {
            v8_1 = p23;
        } else {
            v8_1 = p16.imageUniqueId;
        }
        String v9_1;
        if ((p32 & 128) == 0) {
            v9_1 = p24;
        } else {
            v9_1 = p16.subSecTime;
        }
        Double v10_1;
        if ((p32 & 256) == 0) {
            v10_1 = p25;
        } else {
            v10_1 = p16.apertureF;
        }
        Double v11_1;
        if ((p32 & 512) == 0) {
            v11_1 = p26;
        } else {
            v11_1 = p16.focalLengthMm;
        }
        Integer v12_1;
        if ((p32 & 1024) == 0) {
            v12_1 = p27;
        } else {
            v12_1 = p16.iso;
        }
        Double v13_1;
        if ((p32 & 2048) == 0) {
            v13_1 = p28;
        } else {
            v13_1 = p16.exposureSeconds;
        }
        Integer v14_1;
        if ((p32 & 4096) == 0) {
            v14_1 = p29;
        } else {
            v14_1 = p16.pixelWidth;
        }
        Integer v15_1;
        if ((p32 & 8192) == 0) {
            v15_1 = p30;
        } else {
            v15_1 = p16.pixelHeight;
        }
        String v32_1;
        if ((p32 & 16384) == 0) {
            v32_1 = p31;
        } else {
            v32_1 = p16.userComment;
        }
        return p16.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v32_1);
    }

    public final String component1()
    {
        return this.make;
    }

    public final Double component10()
    {
        return this.focalLengthMm;
    }

    public final Integer component11()
    {
        return this.iso;
    }

    public final Double component12()
    {
        return this.exposureSeconds;
    }

    public final Integer component13()
    {
        return this.pixelWidth;
    }

    public final Integer component14()
    {
        return this.pixelHeight;
    }

    public final String component15()
    {
        return this.userComment;
    }

    public final String component2()
    {
        return this.model;
    }

    public final String component3()
    {
        return this.software;
    }

    public final String component4()
    {
        return this.dateTime;
    }

    public final String component5()
    {
        return this.imageDescription;
    }

    public final String component6()
    {
        return this.copyright;
    }

    public final String component7()
    {
        return this.imageUniqueId;
    }

    public final String component8()
    {
        return this.subSecTime;
    }

    public final Double component9()
    {
        return this.apertureF;
    }

    public final vn.ai.trueid.sdk.utils.TrueIDExifData copy(String p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, Double p25, Double p26, Integer p27, Double p28, Integer p29, Integer p30, String p31)
    {
        return new vn.ai.trueid.sdk.utils.TrueIDExifData(p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.utils.TrueIDExifData)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.make, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).make)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.model, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).model)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.software, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).software)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.dateTime, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).dateTime)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.imageDescription, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).imageDescription)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.copyright, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).copyright)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.imageUniqueId, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).imageUniqueId)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.subSecTime, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).subSecTime)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.apertureF, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).apertureF)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.focalLengthMm, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).focalLengthMm)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.iso, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).iso)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.exposureSeconds, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).exposureSeconds)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.pixelWidth, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).pixelWidth)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.pixelHeight, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).pixelHeight)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.userComment, ((vn.ai.trueid.sdk.utils.TrueIDExifData) p5).userComment)) {
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

    public final Double getApertureF()
    {
        return this.apertureF;
    }

    public final String getCopyright()
    {
        return this.copyright;
    }

    public final String getDateTime()
    {
        return this.dateTime;
    }

    public final Double getExposureSeconds()
    {
        return this.exposureSeconds;
    }

    public final Double getFocalLengthMm()
    {
        return this.focalLengthMm;
    }

    public final String getImageDescription()
    {
        return this.imageDescription;
    }

    public final String getImageUniqueId()
    {
        return this.imageUniqueId;
    }

    public final Integer getIso()
    {
        return this.iso;
    }

    public final String getMake()
    {
        return this.make;
    }

    public final String getModel()
    {
        return this.model;
    }

    public final Integer getPixelHeight()
    {
        return this.pixelHeight;
    }

    public final Integer getPixelWidth()
    {
        return this.pixelWidth;
    }

    public final String getSoftware()
    {
        return this.software;
    }

    public final String getSubSecTime()
    {
        return this.subSecTime;
    }

    public final String getUserComment()
    {
        return this.userComment;
    }

    public int hashCode()
    {
        Integer v2_6;
        int v0_7 = e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.make.hashCode() * 31), 31, this.model), 31, this.software), 31, this.dateTime), 31, this.imageDescription), 31, this.copyright), 31, this.imageUniqueId), 31, this.subSecTime);
        Integer v2_5 = this.apertureF;
        int v3 = 0;
        if (v2_5 != null) {
            v2_6 = v2_5.hashCode();
        } else {
            v2_6 = 0;
        }
        Integer v2_8;
        int v0_9 = ((v0_7 + v2_6) * 31);
        Integer v2_7 = this.focalLengthMm;
        if (v2_7 != null) {
            v2_8 = v2_7.hashCode();
        } else {
            v2_8 = 0;
        }
        Integer v2_10;
        int v0_12 = ((v0_9 + v2_8) * 31);
        Integer v2_9 = this.iso;
        if (v2_9 != null) {
            v2_10 = v2_9.hashCode();
        } else {
            v2_10 = 0;
        }
        Integer v2_13;
        int v0_14 = ((v0_12 + v2_10) * 31);
        Integer v2_11 = this.exposureSeconds;
        if (v2_11 != null) {
            v2_13 = v2_11.hashCode();
        } else {
            v2_13 = 0;
        }
        Integer v2_15;
        int v0_16 = ((v0_14 + v2_13) * 31);
        Integer v2_14 = this.pixelWidth;
        if (v2_14 != null) {
            v2_15 = v2_14.hashCode();
        } else {
            v2_15 = 0;
        }
        int v0_18 = ((v0_16 + v2_15) * 31);
        Integer v2_16 = this.pixelHeight;
        if (v2_16 != null) {
            v3 = v2_16.hashCode();
        }
        return (this.userComment.hashCode() + ((v0_18 + v3) * 31));
    }

    public String toString()
    {
        String v3 = this.software;
        String v4 = this.dateTime;
        String v5 = this.imageDescription;
        String v6 = this.copyright;
        String v7 = this.imageUniqueId;
        String v8 = this.subSecTime;
        Double v9 = this.apertureF;
        Double v10 = this.focalLengthMm;
        Integer v11 = this.iso;
        Double v12 = this.exposureSeconds;
        Integer v13 = this.pixelWidth;
        String v16 = this.userComment;
        Integer v17 = this.pixelHeight;
        String v0_2 = androidx.camera.camera2.internal.G.i("TrueIDExifData(make=", this.make, ", model=", this.model, ", software=");
        q9.H.p(v0_2, v3, ", dateTime=", v4, ", imageDescription=");
        q9.H.p(v0_2, v5, ", copyright=", v6, ", imageUniqueId=");
        q9.H.p(v0_2, v7, ", subSecTime=", v8, ", apertureF=");
        v0_2.append(v9);
        v0_2.append(", focalLengthMm=");
        v0_2.append(v10);
        v0_2.append(", iso=");
        v0_2.append(v11);
        v0_2.append(", exposureSeconds=");
        v0_2.append(v12);
        v0_2.append(", pixelWidth=");
        v0_2.append(v13);
        v0_2.append(", pixelHeight=");
        v0_2.append(v17);
        v0_2.append(", userComment=");
        return A1.a.l(v0_2, v16, ")");
    }
}

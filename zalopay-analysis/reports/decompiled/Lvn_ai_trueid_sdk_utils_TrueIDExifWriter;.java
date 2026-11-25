package vn.ai.trueid.sdk.utils;
public final class TrueIDExifWriter {
    public static final vn.ai.trueid.sdk.utils.TrueIDExifWriter INSTANCE;

    static TrueIDExifWriter()
    {
        vn.ai.trueid.sdk.utils.TrueIDExifWriter.INSTANCE = new vn.ai.trueid.sdk.utils.TrueIDExifWriter();
        return;
    }

    private TrueIDExifWriter()
    {
        return;
    }

    private final byte[] ensureApp0First(byte[] p8, byte[] p9)
    {
        java.io.ByteArrayOutputStream v0_1 = new java.io.ByteArrayOutputStream((p8.length + p9.length));
        v0_1.write(255);
        v0_1.write(216);
        v0_1.write(p9);
        int v9_1 = 2;
        while (v9_1 < (p8.length - 1)) {
            if ((p8[v9_1] & 255) == 255) {
                int v3_6 = (p8[(v9_1 + 1)] & 255);
                if ((v3_6 != 216) && ((v3_6 != 1) && ((208 > v3_6) || (v3_6 >= 216)))) {
                    if ((v3_6 == 217) || ((v9_1 + 4) > p8.length)) {
                        break;
                    }
                    int v4_4 = (v9_1 + 2);
                    int v5_4 = (((p8[v4_4] & 255) << 8) | (p8[(v9_1 + 3)] & 255));
                    if (v3_6 != 218) {
                        if (v3_6 != 224) {
                            if ((v4_4 + v5_4) > p8.length) {
                                break;
                            }
                            v0_1.write(p8, v9_1, (v5_4 + 2));
                        }
                        v9_1 += (v5_4 + 2);
                    } else {
                        v0_1.write(p8, v9_1, (p8.length - v9_1));
                        return v0_1.toByteArray();
                    }
                } else {
                    v9_1 += 2;
                }
            } else {
                v9_1++;
            }
        }
        return v0_1.toByteArray();
    }

    private final byte[] extractApp0(byte[] p7)
    {
        if (p7.length >= 6) {
            if (((p7[2] & 255) == 255) && ((p7[3] & 255) == 224)) {
                int v3_2 = (((255 & p7[5]) | ((p7[4] & 255) << 8)) + 4);
                if (v3_2 <= p7.length) {
                    return kotlin.collections.w.copyOfRange(p7, 2, v3_2);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public final boolean hasJfifApp0(byte[] p7)
    {
        if (p7.length > 20) {
            int v0_1 = 0;
            while (v0_1 < (p7.length - 20)) {
                int v4_0 = 255;
                if (((p7[v0_1] & 255) != 255) || (((p7[(v0_1 + 1)] & 255) != 216) || (((p7[(v0_1 + 2)] & v4_0) != v4_0) || (((p7[(v0_1 + 3)] & 255) != 224) || ((((p7[(v0_1 + 4)] & v4_0) << 8) | (v4_0 & p7[(v0_1 + 5)])) < 16))))) {
                    v0_1++;
                } else {
                    return 1;
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    public final byte[] inject(android.content.Context p12, byte[] p13, vn.ai.trueid.sdk.utils.TrueIDExifData p14)
    {
        if (p13.length != 0) {
            int v5;
            boolean v3_1 = this.hasJfifApp0(p13);
            StringBuilder v4_2 = 0;
            if (!v3_1) {
                v5 = 0;
            } else {
                v5 = this.extractApp0(p13);
            }
            try {
                vn.ai.trueid.sdk.utils.Helpers$Companion v12_1 = p12.getCacheDir();
                double v7_2 = System.nanoTime();
                double v9_1 = new StringBuilder("tid_exif_");
                v9_1.append(v7_2);
                v9_1.append(".jpg");
                java.io.File v6_1 = new java.io.File(v12_1, v9_1.toString());
            } catch (vn.ai.trueid.sdk.utils.Helpers$Companion v12_4) {
                vn.ai.trueid.sdk.utils.Helpers$Companion v12_6 = v12_4.getMessage();
                Object[] v0_16 = new StringBuilder("\u26a0\ufe0f [ExifWriter] ghi EXIF th\u1ea5t b\u1ea1i: ");
                v0_16.append(v12_6);
                vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {v0_16.toString()}));
                try {
                    if (v4_2 != null) {
                        v4_2.delete();
                    }
                } catch (Exception) {
                }
                return p13;
            } catch (vn.ai.trueid.sdk.utils.Helpers$Companion v12_5) {
                try {
                    if (v4_2 != null) {
                        v4_2.delete();
                    }
                } catch (Exception) {
                }
                throw v12_5;
            }
            vn.ai.trueid.sdk.utils.Helpers$Companion v12_10 = new java.io.FileOutputStream(v6_1);
            try {
                v12_10.write(p13);
            } catch (Object[] v14_9) {
                try {
                    throw v14_9;
                } catch (Object[] v0_17) {
                    kotlin.io.CloseableKt.closeFinally(v12_10, v14_9);
                    throw v0_17;
                }
            }
            kotlin.io.CloseableKt.closeFinally(v12_10, 0);
            vn.ai.trueid.sdk.utils.Helpers$Companion v12_12 = new androidx.exifinterface.media.ExifInterface(v6_1.getAbsolutePath());
            v12_12.setAttribute("Make", p14.getMake());
            v12_12.setAttribute("Model", p14.getModel());
            v12_12.setAttribute("Software", p14.getSoftware());
            v12_12.setAttribute("DateTime", p14.getDateTime());
            v12_12.setAttribute("DateTimeOriginal", p14.getDateTime());
            v12_12.setAttribute("SubSecTimeOriginal", p14.getSubSecTime());
            v12_12.setAttribute("ImageDescription", p14.getImageDescription());
            v12_12.setAttribute("Copyright", p14.getCopyright());
            v12_12.setAttribute("ImageUniqueID", p14.getImageUniqueId());
            v12_12.setAttribute("Flash", "0");
            v12_12.setAttribute("SceneCaptureType", "0");
            v12_12.setAttribute("Orientation", "1");
            Object[] v0_19 = p14.getApertureF();
            if (v0_19 != null) {
                v12_12.setAttribute("FNumber", String.valueOf(v0_19.doubleValue()));
            }
            Object[] v0_21 = p14.getFocalLengthMm();
            if (v0_21 != null) {
                String v2_0 = ((int) (v0_21.doubleValue() * ((double) 100)));
                StringBuilder v4_1 = new StringBuilder();
                v4_1.append(v2_0);
                v4_1.append("/100");
                v12_12.setAttribute("FocalLength", v4_1.toString());
            }
            Object[] v0_1 = p14.getIso();
            if (v0_1 != null) {
                v12_12.setAttribute("PhotographicSensitivity", String.valueOf(v0_1.intValue()));
            }
            Object[] v0_4 = p14.getExposureSeconds();
            if (v0_4 != null) {
                v12_12.setAttribute("ExposureTime", String.valueOf(v0_4.doubleValue()));
            }
            Object[] v0_6 = p14.getPixelWidth();
            if (v0_6 != null) {
                v12_12.setAttribute("PixelXDimension", String.valueOf(v0_6.intValue()));
            }
            Object[] v0_9 = p14.getPixelHeight();
            if (v0_9 != null) {
                v12_12.setAttribute("PixelYDimension", String.valueOf(v0_9.intValue()));
            }
            v12_12.setAttribute("UserComment", p14.getUserComment());
            v12_12.saveAttributes();
            vn.ai.trueid.sdk.utils.Helpers$Companion v12_2 = Cg.l.readBytes(v6_1);
            if (v12_2.length != 0) {
                if ((v5 != 0) && (!this.hasJfifApp0(v12_2))) {
                    v12_2 = this.ensureApp0First(v12_2, v5);
                    vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {"\u2139\ufe0f [ExifWriter] \u0111\u00e3 \u0111\u01b0a APP0 v\u1ec1 ngay sau SOI (ExifInterface \u0111\u1eb7t APP1 tr\u01b0\u1edbc)"}));
                }
                if ((!v3_1) || (this.hasJfifApp0(v12_2))) {
                    try {
                        v6_1.delete();
                    } catch (Exception) {
                    }
                    return v12_2;
                } else {
                    vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {"\u26a0\ufe0f [ExifWriter] APP0 m\u1ea5t sau khi ghi EXIF \u2014 b\u1ecf EXIF, gi\u1eef \u1ea3nh g\u1ed1c"}));
                    try {
                        v6_1.delete();
                    } catch (Exception) {
                    }
                    return p13;
                }
            } else {
                try {
                    v6_1.delete();
                } catch (Exception) {
                }
                return p13;
            }
        } else {
            return p13;
        }
    }
}

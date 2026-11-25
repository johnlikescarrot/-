package vn.ai.trueid.lottie.utils;
public final class Utils {
    public static final android.graphics.Matrix IDENTITY_MATRIX;
    private static final float INV_SQRT_2;
    public static final int SECOND_IN_NANOS;
    private static final ThreadLocal threadLocalPathMeasure;
    private static final ThreadLocal threadLocalPoints;
    private static final ThreadLocal threadLocalTempPath;
    private static final ThreadLocal threadLocalTempPath2;

    static Utils()
    {
        wmoi.igob.jtgo(vn.ai.trueid.lottie.utils.Utils, 18251, 18251);
        vn.ai.trueid.lottie.utils.Utils.IDENTITY_MATRIX = new android.graphics.Matrix();
        vn.ai.trueid.lottie.utils.Utils.threadLocalPathMeasure = new vn.ai.trueid.lottie.utils.Utils$1();
        vn.ai.trueid.lottie.utils.Utils.threadLocalTempPath = new vn.ai.trueid.lottie.utils.Utils$2();
        vn.ai.trueid.lottie.utils.Utils.threadLocalTempPath2 = new vn.ai.trueid.lottie.utils.Utils$3();
        vn.ai.trueid.lottie.utils.Utils.threadLocalPoints = new vn.ai.trueid.lottie.utils.Utils$4();
        vn.ai.trueid.lottie.utils.Utils.INV_SQRT_2 = ((float) (Math.sqrt(4611686018427387904) / 4611686018427387904));
        return;
    }

    private Utils()
    {
        return;
    }

    public static void applyTrimPathIfNeeded(android.graphics.Path p10, float p11, float p12, float p13)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("applyTrimPathIfNeeded");
        }
        android.graphics.PathMeasure v0_3 = ((android.graphics.PathMeasure) vn.ai.trueid.lottie.utils.Utils.threadLocalPathMeasure.get());
        android.graphics.Path v2_1 = ((android.graphics.Path) vn.ai.trueid.lottie.utils.Utils.threadLocalTempPath.get());
        android.graphics.Path v3_2 = ((android.graphics.Path) vn.ai.trueid.lottie.utils.Utils.threadLocalTempPath2.get());
        v0_3.setPath(p10, 0);
        float v4_1 = v0_3.getLength();
        if ((p11 != 1065353216) || (p12 != 0)) {
            if ((v4_1 >= 1065353216) && (((double) Math.abs(((p12 - p11) - 1065353216))) >= 4576918229304087675)) {
                float v11_1 = (p11 * v4_1);
                int v12_1 = (p12 * v4_1);
                float v13_1 = (p13 * v4_1);
                float v5_5 = (Math.min(v11_1, v12_1) + v13_1);
                float v11_3 = (Math.max(v11_1, v12_1) + v13_1);
                if ((v5_5 >= v4_1) && (v11_3 >= v4_1)) {
                    v5_5 = ((float) vn.ai.trueid.lottie.utils.MiscUtils.floorMod(v5_5, v4_1));
                    v11_3 = ((float) vn.ai.trueid.lottie.utils.MiscUtils.floorMod(v11_3, v4_1));
                }
                if (v5_5 < 0) {
                    v5_5 = ((float) vn.ai.trueid.lottie.utils.MiscUtils.floorMod(v5_5, v4_1));
                }
                if (v11_3 < 0) {
                    v11_3 = ((float) vn.ai.trueid.lottie.utils.MiscUtils.floorMod(v11_3, v4_1));
                }
                int v12_8 = v5_5 cmp v11_3;
                if (v12_8 != 0) {
                    if (v12_8 >= 0) {
                        v5_5 -= v4_1;
                    }
                    v2_1.reset();
                    v0_3.getSegment(v5_5, v11_3, v2_1, 1);
                    if (v11_3 <= v4_1) {
                        if (v5_5 < 0) {
                            v3_2.reset();
                            v0_3.getSegment((v5_5 + v4_1), v4_1, v3_2, 1);
                            v2_1.addPath(v3_2);
                        }
                    } else {
                        v3_2.reset();
                        v0_3.getSegment(0, (v11_3 % v4_1), v3_2, 1);
                        v2_1.addPath(v3_2);
                    }
                    p10.set(v2_1);
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.endSection("applyTrimPathIfNeeded");
                        return;
                    }
                } else {
                    p10.reset();
                    if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                        vn.ai.trueid.lottie.L.endSection("applyTrimPathIfNeeded");
                        return;
                    }
                }
            } else {
                if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                    vn.ai.trueid.lottie.L.endSection("applyTrimPathIfNeeded");
                }
            }
        } else {
            if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
                vn.ai.trueid.lottie.L.endSection("applyTrimPathIfNeeded");
                return;
            }
        }
        return;
    }

    public static void applyTrimPathIfNeeded(android.graphics.Path p3, vn.ai.trueid.lottie.animation.content.TrimPathContent p4)
    {
        if ((p4 != 0) && (!p4.isHidden())) {
            vn.ai.trueid.lottie.utils.Utils.applyTrimPathIfNeeded(p3, (((vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation) p4.getStart()).getFloatValue() / 1120403456), (((vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation) p4.getEnd()).getFloatValue() / 1120403456), (((vn.ai.trueid.lottie.animation.keyframe.FloatKeyframeAnimation) p4.getOffset()).getFloatValue() / 1135869952));
        }
        return;
    }

    public static void closeQuietly(java.io.Closeable p0)
    {
        if (p0 == null) {
            return;
        } else {
            try {
                p0.close();
            } catch (RuntimeException v0_1) {
                throw v0_1;
            } catch (Exception) {
            }
            return;
        }
    }

    public static android.graphics.Path createPath(android.graphics.PointF p7, android.graphics.PointF p8, android.graphics.PointF p9, android.graphics.PointF p10)
    {
        android.graphics.Path v0_1 = new android.graphics.Path();
        v0_1.moveTo(p7.x, p7.y);
        if ((p9 == 0) || ((p10 == null) || ((p9.length() == 0) && (p10.length() == 0)))) {
            v0_1.lineTo(p8.x, p8.y);
            return v0_1;
        } else {
            float v5 = p8.x;
            float v6 = p8.y;
            v0_1.cubicTo((p7.x + p9.x), (p7.y + p9.y), (v5 + p10.x), (v6 + p10.y), v5, v6);
            return v0_1;
        }
    }

    public static float dpScale()
    {
        return android.content.res.Resources.getSystem().getDisplayMetrics().density;
    }

    public static float getAnimationScale(android.content.Context p2)
    {
        return android.provider.Settings$Global.getFloat(p2.getContentResolver(), "animator_duration_scale", 1065353216);
    }

    public static float getScale(android.graphics.Matrix p6)
    {
        double v0_5 = ((float[]) vn.ai.trueid.lottie.utils.Utils.threadLocalPoints.get());
        v0_5[0] = 0;
        v0_5[1] = 0;
        double v2_2 = vn.ai.trueid.lottie.utils.Utils.INV_SQRT_2;
        v0_5[2] = v2_2;
        v0_5[3] = v2_2;
        p6.mapPoints(v0_5);
        return ((float) Math.hypot(((double) (v0_5[2] - v0_5[0])), ((double) (v0_5[3] - v0_5[1]))));
    }

    public static boolean hasZeroScaleAxis(android.graphics.Matrix p6)
    {
        float v0_3 = ((float[]) vn.ai.trueid.lottie.utils.Utils.threadLocalPoints.get());
        v0_3[0] = 0;
        v0_3[1] = 0;
        v0_3[2] = 1192366779;
        v0_3[3] = 1192924988;
        p6.mapPoints(v0_3);
        if ((v0_3[0] != v0_3[2]) && (v0_3[1] != v0_3[3])) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int hashFor(float p2, float p3, float p4, float p5)
    {
        int v2_4;
        if (p2 == 0) {
            v2_4 = 17;
        } else {
            v2_4 = ((int) (((float) 527) * p2));
        }
        if (p3 != 0) {
            v2_4 = ((int) (((float) (v2_4 * 31)) * p3));
        }
        if (p4 != 0) {
            v2_4 = ((int) (((float) (v2_4 * 31)) * p4));
        }
        if (p5 != 0) {
            v2_4 = ((int) (((float) (v2_4 * 31)) * p5));
        }
        return v2_4;
    }

    public static boolean isAtLeastVersion(int p2, int p3, int p4, int p5, int p6, int p7)
    {
        if (p2 >= p5) {
            if (p2 <= p5) {
                if (p3 >= p6) {
                    if (p3 <= p6) {
                        if (p4 < p7) {
                            return 0;
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public static boolean isNetworkException(Throwable p1)
    {
        if ((!(p1 instanceof java.net.SocketException)) && ((!(p1 instanceof java.nio.channels.ClosedChannelException)) && ((!(p1 instanceof java.io.InterruptedIOException)) && ((!(p1 instanceof java.net.ProtocolException)) && ((!(p1 instanceof javax.net.ssl.SSLException)) && ((!(p1 instanceof java.net.UnknownHostException)) && (!(p1 instanceof java.net.UnknownServiceException)))))))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int mixOpacities(int p1, int p2)
    {
        return ((int) ((((((float) p1) / 1132396544) * ((float) p2)) / 1132396544) * 1132396544));
    }

    public static android.graphics.Bitmap renderPath(android.graphics.Path p4)
    {
        android.graphics.Bitmap v0_1 = new android.graphics.RectF();
        p4.computeBounds(v0_1, 0);
        android.graphics.Bitmap v0_2 = android.graphics.Bitmap.createBitmap(((int) v0_1.right), ((int) v0_1.bottom), android.graphics.Bitmap$Config.ARGB_8888);
        android.graphics.Canvas v1_1 = new android.graphics.Canvas(v0_2);
        vn.ai.trueid.lottie.animation.LPaint v2_1 = new vn.ai.trueid.lottie.animation.LPaint();
        v2_1.setAntiAlias(1);
        v2_1.setColor(-16776961);
        v1_1.drawPath(p4, v2_1);
        return v0_2;
    }

    public static android.graphics.Bitmap resizeBitmapIfNeeded(android.graphics.Bitmap p1, int p2, int p3)
    {
        if ((p1.getWidth() != p2) || (p1.getHeight() != p3)) {
            android.graphics.Bitmap v2_1 = android.graphics.Bitmap.createScaledBitmap(p1, p2, p3, 1);
            p1.recycle();
            return v2_1;
        } else {
            return p1;
        }
    }

    public static void saveLayerCompat(android.graphics.Canvas p1, android.graphics.RectF p2, android.graphics.Paint p3)
    {
        vn.ai.trueid.lottie.utils.Utils.saveLayerCompat(p1, p2, p3, 31);
        return;
    }

    public static void saveLayerCompat(android.graphics.Canvas p1, android.graphics.RectF p2, android.graphics.Paint p3, int p4)
    {
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.beginSection("Utils#saveLayer");
        }
        p1.saveLayer(p2, p3);
        if (vn.ai.trueid.lottie.L.isTraceEnabled()) {
            vn.ai.trueid.lottie.L.endSection("Utils#saveLayer");
        }
        return;
    }
}

package vn.ai.trueid.sdk.face.present.presentation.widgets;
public final class OverlayView extends android.view.View {
    public android.graphics.Paint a;
    public android.graphics.Paint b;
    public android.graphics.Paint c;
    public float d;
    public android.graphics.RectF e;
    public android.graphics.RectF f;
    public float g;
    public android.animation.ValueAnimator h;
    public android.graphics.Paint i;
    public android.graphics.RectF j;
    public android.graphics.Paint k;
    public boolean l;
    public String m;
    public String n;
    public String o;
    public String p;
    public vn.ai.trueid.sdk.face.u0 q;
    public final android.graphics.Paint r;
    public final ng.w s;
    public android.graphics.Paint t;
    public float u;
    public boolean v;

    public OverlayView(android.content.Context p3)
    {
        this(p3, 0, 2, 0);
        return;
    }

    public OverlayView(android.content.Context p6, android.util.AttributeSet p7)
    {
        StringBuilder v2_3;
        super(p6, p7);
        super.g = 1062333317;
        super.m = "";
        int v7_7 = new android.graphics.Paint(1);
        if (super.getConfig().getTextSize() <= 0) {
            v2_3 = p6.getResources().getDimension(vn.ai.trueid.sdk.face.R$dimen.facesdk_text_size);
        } else {
            v2_3 = ((float) super.getConfig().getTextSize());
        }
        StringBuilder v2_6 = android.util.TypedValue.applyDimension(2, v2_3, p6.getResources().getDisplayMetrics());
        v7_7.setColor(-1);
        v7_7.setTextSize(v2_6);
        v7_7.setTextAlign(android.graphics.Paint$Align.CENTER);
        v7_7.setLinearText(1);
        if (super.getConfig().getFontName().length() > 0) {
            try {
                Exception v6_1 = p6.getAssets();
                StringBuilder v2_12 = new StringBuilder("fonts/");
                v2_12.append(super.getConfig().getFontName());
                v7_7.setTypeface(android.graphics.Typeface.createFromAsset(v6_1, v2_12.toString()));
            } catch (Exception v6_3) {
                v6_3.printStackTrace();
            }
        }
        super.r = v7_7;
        super.s = ng.x.b(new vn.ai.trueid.sdk.face.x0(super));
        Exception v6_8 = new android.graphics.Paint();
        v6_8.setAntiAlias(1);
        v6_8.setColor(-16776961);
        v6_8.setStyle(android.graphics.Paint$Style.STROKE);
        v6_8.setStrokeWidth(1092616192);
        super.t = v6_8;
        super.c();
        super.setLayerType(1, 0);
        super.d();
        return;
    }

    public synthetic OverlayView(android.content.Context p1, android.util.AttributeSet p2, int p3, kotlin.jvm.internal.DefaultConstructorMarker p4)
    {
        if ((p3 & 2) != 0) {
            p2 = 0;
        }
        this(p1, p2);
        return;
    }

    public static void a(vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView p7)
    {
        p7.getClass();
        int v4_4 = new android.util.TypedValue();
        p7.getResources().getValue(vn.ai.trueid.sdk.face.R$dimen.facesdk_overlay_top_margin, v4_4, 1);
        p7.d = v4_4.getFloat();
        android.graphics.PorterDuff$Mode v3_2 = new android.graphics.Paint();
        v3_2.setColor(p7.getContext().getColor(vn.ai.trueid.sdk.face.R$color.facesdk_overlay_background));
        android.graphics.Paint$Style v1_2 = android.graphics.Paint$Style.FILL;
        v3_2.setStyle(v1_2);
        p7.a = v3_2;
        android.graphics.PorterDuff$Mode v3_4 = new android.graphics.Paint();
        v3_4.setColor(p7.getContext().getColor(vn.ai.trueid.sdk.face.R$color.facesdk_overlay_border));
        v3_4.setStrokeWidth(p7.getResources().getDimension(vn.ai.trueid.sdk.face.R$dimen.facesdk_overlay_border_stroke));
        v3_4.setStyle(android.graphics.Paint$Style.STROKE);
        p7.c = v3_4;
        android.graphics.Paint v0_6 = new android.graphics.Paint(1);
        v0_6.setColor(p7.getResources().getColor(vn.ai.trueid.sdk.face.R$color.facesdk_white_70));
        v0_6.setStyle(v1_2);
        v0_6.setMaskFilter(new android.graphics.BlurMaskFilter(1092616192, android.graphics.BlurMaskFilter$Blur.NORMAL));
        p7.i = v0_6;
        android.graphics.Paint v0_8 = new android.graphics.Paint();
        v0_8.setAntiAlias(1);
        v0_8.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
        v0_8.setStyle(v1_2);
        p7.b = v0_8;
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView p0, android.animation.ValueAnimator p1)
    {
        p0.g = ((Float) p1.getAnimatedValue()).floatValue();
        p0.invalidate();
        return;
    }

    private final vn.ai.trueid.sdk.face.present.domain.configs.SDKConfig getConfig()
    {
        return vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getConfig$face_release();
    }

    private final float getSweepAngle()
    {
        return ((Number) this.s.getValue()).floatValue();
    }

    public final void a()
    {
        this.v = 0;
        this.invalidate();
        return;
    }

    public final void a(android.graphics.RectF p3)
    {
        if (this.getResources().getBoolean(vn.ai.trueid.sdk.face.R$bool.sdk_debug)) {
            this.j = p3;
        }
        this.invalidate();
        return;
    }

    public final void b()
    {
        android.graphics.Paint v0_1;
        if (this.getConfig().getOvalColor().length() <= 0) {
            v0_1 = this.getConfig().getPrimaryColor();
        } else {
            v0_1 = this.getConfig().getOvalColor();
        }
        android.graphics.Paint v0_7;
        this.n = v0_1;
        if (this.getConfig().getOvalColor().length() <= 0) {
            v0_7 = this.getConfig().getPrimaryColor();
        } else {
            v0_7 = this.getConfig().getOvalColor();
        }
        this.p = v0_7;
        this.o = this.getConfig().getErrorColor();
        android.graphics.Paint v0_13 = new android.graphics.Paint();
        v0_13.setAntiAlias(1);
        v0_13.setColor(android.graphics.Color.parseColor(this.getConfig().getSecondaryColor()));
        v0_13.setStyle(android.graphics.Paint$Style.STROKE);
        v0_13.setStrokeWidth(1092616192);
        this.t = v0_13;
        android.graphics.Paint v0_15 = this.c;
        if (v0_15 != null) {
            v0_15.setColor(android.graphics.Color.parseColor(this.n));
        }
        this.invalidate();
        return;
    }

    public final void c()
    {
        vn.ai.trueid.sdk.face.v0 v0_0 = this.g;
        android.animation.ValueAnimator v1_2 = (1068289229 * v0_0);
        ID.c v2_4 = new float[2];
        v2_4[0] = v0_0;
        v2_4[1] = v1_2;
        vn.ai.trueid.sdk.face.v0 v0_1 = android.animation.ValueAnimator.ofFloat(v2_4);
        this.h = v0_1;
        android.animation.ValueAnimator v1_1 = 0;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        v0_1.setDuration(400);
        vn.ai.trueid.sdk.face.v0 v0_2 = this.h;
        if (v0_2 == null) {
            v0_2 = 0;
        }
        v0_2.addUpdateListener(new ID.c(3, this));
        vn.ai.trueid.sdk.face.v0 v0_3 = this.h;
        if (v0_3 != null) {
            v1_1 = v0_3;
        }
        v1_1.addListener(new vn.ai.trueid.sdk.face.v0(this));
        return;
    }

    public final void d()
    {
        this.post(new vn.ai.trueid.sdk.face.w0(this));
        return;
    }

    public final void e()
    {
        int v0 = this.h;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.cancel();
        return;
    }

    public final void f()
    {
        int v0 = this.h;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.start();
        return;
    }

    public final android.graphics.RectF getRectOval()
    {
        float v0_3 = ((float) this.getWidth());
        float v2_8 = (1060320051 * v0_3);
        float v3_0 = ((1082130432 * v2_8) / 1077936128);
        float v0_2 = ((v0_3 - v2_8) / ((float) 2));
        android.graphics.RectF v1_2 = (((((float) this.getHeight()) - v3_0) / ((float) 2)) - this.d);
        android.graphics.RectF v5_2 = new android.graphics.RectF(v0_2, v1_2, (v2_8 + v0_2), (v3_0 + v1_2));
        float v0_5 = (v5_2.width() * this.g);
        android.graphics.RectF v1_5 = (v5_2.height() * this.g);
        float v2_4 = ((v5_2.width() - v0_5) / ((float) 2));
        float v0_8 = ((v5_2.height() - v1_5) / ((float) 2));
        return new android.graphics.RectF((v5_2.left + v2_4), (v5_2.top + v0_8), (v5_2.right - v2_4), (v5_2.bottom - v0_8));
    }

    public final android.graphics.RectF getRectZoomIn()
    {
        return this.e;
    }

    public final android.graphics.RectF getRectZoomOut()
    {
        return this.f;
    }

    public final void onDraw(android.graphics.Canvas p18)
    {
        super.onDraw(p18);
        android.graphics.RectF v3_0 = new android.graphics.Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        float v4_0 = this.a;
        if (v4_0 != 0) {
            p18.drawRect(v3_0, v4_0);
        }
        android.graphics.RectF v3_1 = this.getRectOval();
        this.e = v3_1;
        if (v3_1 != null) {
            float v7_11 = this.c;
            if (v7_11 != 0) {
                p18.drawArc(v3_1, 0, this.getSweepAngle(), 1, v7_11);
            }
            String v2_4;
            float v7_12 = this.b;
            if (v7_12 == 0) {
                v2_4 = p18;
            } else {
                v2_4 = p18;
                p18.drawArc(v3_1, 0, this.getSweepAngle(), 1, v7_12);
            }
            if (this.l) {
                float v4_13 = this.i;
                if (v4_13 != 0) {
                    v2_4.drawOval(v3_1, v4_13);
                }
            }
            float v4_14 = this.k;
            if (v4_14 != 0) {
                v2_4.drawOval(v3_1, v4_14);
            }
            if (this.m.length() > 0) {
                try {
                    android.graphics.RectF v0_1;
                    float v4_19 = this.getContext().getAssets().list("");
                } catch (android.graphics.RectF v0_10) {
                    android.util.Log.e("Assets", "Error listing files in assets folder", v0_10);
                    v0_1 = v3_1;
                    float v6_1 = this.r;
                    float v4_4 = kotlin.text.StringsKt.y(this.m, new String[] {"\n"}), 0, 6);
                    float v7_7 = (v6_1.getFontMetrics().descent - v6_1.getFontMetrics().ascent);
                    float v11_0 = ((float) 2);
                    float v9_9 = ((v7_7 / v11_0) + (v0_1.centerY() - ((((float) v4_4.size()) * v7_7) / v11_0)));
                    float v4_5 = v4_4.iterator();
                    float v10_2 = 0;
                    while (v4_5.hasNext()) {
                        float v11_13 = v4_5.next();
                        float v12_2 = (v10_2 + 1);
                        if (v10_2 < 0) {
                            kotlin.collections.E.throwIndexOverflow();
                        }
                        v2_4.drawText(((String) v11_13), v0_1.centerX(), ((((float) v10_2) * v7_7) + v9_9), v6_1);
                        v10_2 = v12_2;
                    }
                }
                if (v4_19 == 0) {
                } else {
                    float v6_8 = 0;
                    while(true) {
                        float v7_14;
                        if (v6_8 >= v4_19.length) {
                            v7_14 = 0;
                        } else {
                            v7_14 = 1;
                        }
                        if (v7_14 == 0) {
                        } else {
                            try {
                            } catch (android.graphics.RectF v0_8) {
                                throw new java.util.NoSuchElementException(v0_8.getMessage());
                            }
                            if (kotlin.jvm.internal.Intrinsics.areEqual(v4_19[v6_8], "your_app_logo_facesdk.png")) {
                                break;
                            }
                            v6_8++;
                        }
                    }
                    try {
                        android.graphics.RectF v0_12 = android.graphics.BitmapFactory.decodeStream(this.getContext().getAssets().open("your_app_logo_facesdk.png"));
                    } catch (java.io.IOException) {
                        v0_12 = 0;
                    }
                    if (v0_12 != null) {
                        v2_4.drawBitmap(android.graphics.Bitmap.createScaledBitmap(v0_12, 240, 240, 0), new android.graphics.Rect(0, 0, 240, 240), new android.graphics.RectF((v3_1.centerX() - ((float) 120)), (v3_1.centerY() - ((float) 120)), (v3_1.centerX() + ((float) 120)), (v3_1.centerY() + ((float) 120))), 0);
                    }
                    v0_1 = new android.graphics.RectF(v3_1.left, (v3_1.centerY() + ((float) 120)), v3_1.right, v3_1.bottom);
                }
            }
            if (this.v) {
                android.graphics.RectF v0_3 = v3_1.centerX();
                float v4_6 = v3_1.centerY();
                float v6_4 = ((v3_1.width() / ((float) 2)) - ((float) 20));
                float v9_12 = ((v3_1.height() / ((float) 2)) - ((float) 20));
                int v5_1 = this.u;
                float v7_10 = this.t;
                float v10_4 = new android.graphics.Path();
                float v11_3 = Math.toRadians(((double) v5_1));
                v10_4.moveTo(((((float) Math.cos(v11_3)) * v6_4) + v0_3), ((((float) Math.sin(v11_3)) * v9_12) + v4_6));
                float v13_6 = 0;
                while(true) {
                    float v14_6 = 121;
                    if (v13_6 >= 121) {
                        break;
                    }
                    float v14_11 = Math.toRadians(((double) (((float) v13_6) + v5_1)));
                    float v16_1 = v9_12;
                    v10_4.lineTo(((((float) Math.cos(v14_11)) * v6_4) + v0_3), ((((float) Math.sin(v14_11)) * v16_1) + v4_6));
                    v13_6++;
                    v9_12 = v16_1;
                }
                float v16_0 = v9_12;
                float v11_4 = (v11_3 + 4614256656552045848);
                v10_4.moveTo(((((float) Math.cos(v11_4)) * v6_4) + v0_3), ((((float) Math.sin(v11_4)) * v16_0) + v4_6));
                int v8_5 = 0;
                while (v8_5 < v14_6) {
                    float v11_8 = Math.toRadians(((double) ((((float) 180) + v5_1) + ((float) v8_5))));
                    v10_4.lineTo(((((float) Math.cos(v11_8)) * v6_4) + v0_3), ((((float) Math.sin(v11_8)) * v16_0) + v4_6));
                    v8_5++;
                    v14_6 = 121;
                }
                v2_4.drawPath(v10_4, v7_10);
            }
            android.graphics.RectF v0_5 = this.j;
            if (v0_5 != null) {
                float v4_8 = new android.graphics.Paint();
                v4_8.setStrokeWidth(1092616192);
                v4_8.setColor(0);
                v4_8.setStyle(android.graphics.Paint$Style.STROKE);
                v4_8.setColor(-16777216);
                v2_4.drawRect(v0_5, v4_8);
                String v2_2 = new StringBuilder("FaceInsideOval face=");
                v2_2.append(v0_5);
                v2_2.append("\noval=");
                v2_2.append(v3_1);
                android.util.Log.e("OverlayView", v2_2.toString());
            }
        }
        return;
    }

    public final void setOnCallbackOverlayListener(vn.ai.trueid.sdk.face.u0 p1)
    {
        this.q = p1;
        return;
    }

    public final void setRectZoomIn(android.graphics.RectF p1)
    {
        this.e = p1;
        return;
    }

    public final void setRectZoomOut(android.graphics.RectF p1)
    {
        this.f = p1;
        return;
    }

    public final void setScaleFlashCheck(float p1)
    {
        this.g = p1;
        this.c();
        return;
    }
}

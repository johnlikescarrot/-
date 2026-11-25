package vn.ai.trueid.sdk.facev3.present.presentation.widgets;
public final class OverlayView extends android.view.View {
    public static final synthetic int D;
    public boolean A;
    public float B;
    public Integer C;
    public android.graphics.Paint a;
    public android.graphics.Paint b;
    public android.graphics.Paint c;
    public android.graphics.Paint d;
    public int e;
    public float f;
    public android.graphics.RectF g;
    public android.graphics.RectF h;
    public float i;
    public android.animation.ValueAnimator j;
    public android.graphics.Paint k;
    public android.graphics.Paint l;
    public boolean m;
    public String n;
    public String o;
    public String p;
    public String q;
    public final ng.S r;
    public Dk.d s;
    public kotlin.jvm.functions.Function1 t;
    public final android.graphics.Paint u;
    public final ng.S v;
    public float w;
    public final android.graphics.Path x;
    public final android.graphics.RectF y;
    public final android.graphics.Paint z;

    public OverlayView(android.content.Context p6, android.util.AttributeSet p7)
    {
        int v2_3;
        super(p6, p7);
        super.e = -16777216;
        super.i = 1062333317;
        super.n = "";
        super.r = ng.x.b(new Ai.o(super, p6, 3));
        int v0_2 = new android.graphics.Paint(1);
        if (super.getConfig().p <= 0) {
            v2_3 = p6.getResources().getDimension(2131165545);
        } else {
            v2_3 = ((float) super.getConfig().p);
        }
        int v2_6 = android.util.TypedValue.applyDimension(2, v2_3, p6.getResources().getDisplayMetrics());
        v0_2.setColor(-1);
        v0_2.setTextSize(v2_6);
        v0_2.setTextAlign(android.graphics.Paint$Align.CENTER);
        v0_2.setLinearText(1);
        if (super.getConfig().n.length() > 0) {
            Ae.B v6_1 = Ck.a.a(p6, super.getConfig().n);
            if (v6_1 != null) {
                v0_2.setTypeface(v6_1);
            }
        }
        super.u = v0_2;
        super.v = ng.x.b(new A0.c(10, super));
        Ae.B v6_6 = new android.graphics.Paint();
        v6_6.setAntiAlias(1);
        v6_6.setColor(-16776961);
        int v0_6 = android.graphics.Paint$Style.STROKE;
        v6_6.setStyle(v0_6);
        v6_6.setStrokeWidth(1092616192);
        super.x = new android.graphics.Path();
        super.y = new android.graphics.RectF();
        Ae.B v6_12 = new android.graphics.Paint(1);
        v6_12.setStyle(v0_6);
        super.z = v6_12;
        super.h();
        super.setLayerType(1, 0);
        super.post(new Ae.B(1, super));
        super.B = 1062333317;
        return;
    }

    private final yk.b getConfig()
    {
        yk.b v0 = wk.a.c;
        if (v0 != null) {
            return v0;
        } else {
            return new yk.b("#26B840", "#42526E", "#26B840", 0, "#FF6234", "#000000", "#253858", "#253858", "#FFFFFF", 0, 1, 1, "", 0, 0, 0, 0, 0, 0, 8126472);
        }
    }

    private final boolean getHasCustomLogo()
    {
        return ((Boolean) this.r.getValue()).booleanValue();
    }

    private final float getSweepAngle()
    {
        return ((Number) this.v.getValue()).floatValue();
    }

    public final void a()
    {
        int v3_1 = androidx.core.graphics.d.e(this.e, 255);
        androidx.core.graphics.d.e(this.e, 255);
        androidx.core.graphics.d.e(this.e, 255);
        androidx.core.graphics.d.e(this.e, 204);
        androidx.core.graphics.d.e(this.e, 255);
        float[] v15 = new float[6];
        v15 = {0, 1036831949, 1045220557, 1056964608, 1059481190, 1065353216};
        android.graphics.Paint v1_5 = this.d;
        if (v1_5 != null) {
            v1_5.setShader(new android.graphics.LinearGradient((((float) this.getWidth()) / 1073741824), 0, (((float) this.getWidth()) / 1073741824), ((float) this.getHeight()), new int[] {v3_1, androidx.core.graphics.d.e(this.e, 255)}), v15, android.graphics.Shader$TileMode.CLAMP));
            return;
        } else {
            return;
        }
    }

    public final android.graphics.RectF b()
    {
        float v0_3 = ((float) this.getWidth());
        float v2_2 = (1060320051 * v0_3);
        float v3_0 = ((1082130432 * v2_2) / 1077936128);
        float v0_2 = ((v0_3 - v2_2) / ((float) 2));
        float v1_2 = (((((float) this.getHeight()) - v3_0) / ((float) 2)) - this.f);
        return new android.graphics.RectF(v0_2, v1_2, (v2_2 + v0_2), (v3_0 + v1_2));
    }

    public final void c()
    {
        android.graphics.Paint v1_0 = wk.a.c;
        if (v1_0 == null) {
            v1_0 = new yk.b("#26B840", "#42526E", "#26B840", 0, "#FF6234", "#000000", "#253858", "#253858", "#FFFFFF", 0, 1, 1, "", 0, 0, 0, 0, 0, 0, 8126472);
        }
        android.graphics.Paint v1_2 = android.graphics.Color.parseColor(v1_0.i);
        int v2_1 = this.a;
        if (v2_1 == 0) {
            android.graphics.Paint v1_5;
            int v2_4 = new android.graphics.Paint();
            v2_4.setColor(v1_2);
            v2_4.setStyle(android.graphics.Paint$Style.FILL);
            if (this.d != null) {
                v1_5 = 0;
            } else {
                v1_5 = 255;
            }
            v2_4.setAlpha(v1_5);
            this.a = v2_4;
        } else {
            if (this.d != null) {
                v1_2 = 0;
            }
            v2_1.setColor(v1_2);
        }
        android.graphics.Paint v1_7 = new android.graphics.Paint();
        v1_7.setAlpha(0);
        this.l = v1_7;
        android.graphics.Paint v1_8 = this.c;
        if (v1_8 != null) {
            v1_8.setColor(android.graphics.Color.parseColor(this.o));
        }
        this.invalidate();
        return;
    }

    public final void d()
    {
        this.A = 0;
        if (this.C != null) {
            android.graphics.Paint v0_2 = this.c;
            if (v0_2 != null) {
                v0_2.setColor(android.graphics.Color.parseColor(this.o));
            }
            this.invalidate();
        }
        this.invalidate();
        return;
    }

    public final String e()
    {
        String v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        if (!v0_0.isZlpSupported$trueid_release()) {
            if (!v0_0.isHlbSupported$trueid_release()) {
                if (this.getConfig().c.length() <= 0) {
                    return this.getConfig().a;
                } else {
                    return this.getConfig().c;
                }
            } else {
                return String.format("#%06X", java.util.Arrays.copyOf(new Object[] {Integer.valueOf((16777215 & Sk.c.k()))}), 1));
            }
        } else {
            return "#FFFFFF";
        }
    }

    public final void f(boolean p2)
    {
        android.graphics.Paint v0 = this.c;
        if (v0 != null) {
            int v2_1;
            if (p2 == 0) {
                v2_1 = this.o;
            } else {
                v2_1 = this.q;
            }
            v0.setColor(android.graphics.Color.parseColor(v2_1));
        }
        this.invalidate();
        return;
    }

    public final void g()
    {
        this.o = this.e();
        this.q = this.e();
        this.p = this.getConfig().e;
        android.graphics.Paint v0_2 = this.getConfig().d;
        if (v0_2.length() <= 0) {
            v0_2 = 0;
        }
        if (v0_2 == null) {
            v0_2 = this.getConfig().c;
        }
        int v1_2 = new android.graphics.Paint();
        v1_2.setAntiAlias(1);
        v1_2.setColor(android.graphics.Color.parseColor(v0_2));
        v1_2.setStyle(android.graphics.Paint$Style.STROKE);
        v1_2.setStrokeWidth(1092616192);
        android.graphics.Paint v0_7 = this.c;
        if (v0_7 != null) {
            v0_7.setColor(android.graphics.Color.parseColor(this.o));
        }
        this.invalidate();
        return;
    }

    public final kotlin.jvm.functions.Function1 getOnOvalRectChanged()
    {
        return this.t;
    }

    public final android.graphics.RectF getRectOval()
    {
        android.graphics.Rect v0_0 = this.b();
        this.g = v0_0;
        android.graphics.RectF v1_9 = (v0_0.width() * this.i);
        float v2_0 = (v0_0.height() * this.i);
        float v3_2 = ((v0_0.width() - v1_9) / ((float) 2));
        int v4_2 = ((v0_0.height() - v2_0) / ((float) 2));
        v0_0.set((v0_0.left + v3_2), (v0_0.top + v4_2), (v0_0.right - v3_2), (v0_0.bottom - v4_2));
        android.graphics.RectF v1_6 = new android.graphics.RectF((v0_0.left - (v0_0.width() * 1028443341)), (v0_0.top - (v0_0.height() * 1028443341)), ((v0_0.width() * 1028443341) + v0_0.right), ((v0_0.height() * 1028443341) + v0_0.bottom));
        android.graphics.Rect v0_5 = new android.graphics.Rect();
        v1_6.roundOut(v0_5);
        return new android.graphics.RectF(v0_5);
    }

    public final android.graphics.RectF getRectZoomIn()
    {
        return this.g;
    }

    public final android.graphics.RectF getRectZoomOut()
    {
        return this.h;
    }

    public final void h()
    {
        int v0_0 = this.i;
        Bk.h v1_3 = (1068289229 * v0_0);
        android.animation.ValueAnimator v2_2 = new float[2];
        v2_2[0] = v0_0;
        v2_2[1] = v1_3;
        Bk.h v1_1 = android.animation.ValueAnimator.ofFloat(v2_2);
        this.j = v1_1;
        android.animation.ValueAnimator v2_0 = 0;
        if (v1_1 == null) {
            v1_1 = 0;
        }
        v1_1.setDuration(400);
        Bk.h v1_2 = this.j;
        if (v1_2 == null) {
            v1_2 = 0;
        }
        v1_2.addUpdateListener(new Dk.e(this, 0));
        Bk.h v1_4 = this.j;
        if (v1_4 != null) {
            v2_0 = v1_4;
        }
        v2_0.addListener(new Bk.h(1, this));
        return;
    }

    public final void i(String p3)
    {
        int v0_0 = this.a;
        if (v0_0 != 0) {
            int v1_0;
            v0_0.setColor(android.graphics.Color.parseColor(p3));
            v0_0.setStyle(android.graphics.Paint$Style.FILL);
            if (this.d != null) {
                v1_0 = 0;
            } else {
                v1_0 = 255;
            }
            v0_0.setAlpha(v1_0);
        }
        int v0_2 = new android.graphics.Paint();
        v0_2.setAntiAlias(1);
        v0_2.setColor(android.graphics.Color.parseColor(p3));
        v0_2.setStyle(android.graphics.Paint$Style.FILL);
        v0_2.setAlpha(200);
        this.l = v0_2;
        android.graphics.Paint v3_4 = this.c;
        if (v3_4 != null) {
            v3_4.setColor(-1);
            v3_4.setAlpha(150);
        }
        this.invalidate();
        return;
    }

    public final void j()
    {
        this.A = 1;
        int v0_1 = this.C;
        if (v0_1 != 0) {
            this.setBorderColor(v0_1.intValue());
        }
        this.invalidate();
        return;
    }

    public final void onDraw(android.graphics.Canvas p14)
    {
        super.onDraw(p14);
        int v0_9 = new android.graphics.Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        float v1_7 = this.a;
        if (v1_7 != 0) {
            p14.drawRect(v0_9, v1_7);
        }
        float v5_0 = this.b();
        this.g = v5_0;
        float v1_1 = (v5_0.width() * this.i);
        android.graphics.RectF v2_13 = (v5_0.height() * this.i);
        android.graphics.Canvas v4_3 = ((v5_0.width() - v1_1) / ((float) 2));
        float v6_8 = ((v5_0.height() - v2_13) / ((float) 2));
        v5_0.set((v5_0.left + v4_3), (v5_0.top + v6_8), (v5_0.right - v4_3), (v5_0.bottom - v6_8));
        android.graphics.RectF v2_17 = this.t;
        if (v2_17 != null) {
            v2_17.invoke(new android.graphics.RectF(v5_0));
        }
        android.graphics.Canvas v4_8;
        int v9_7 = this.c;
        if (v9_7 == 0) {
            v4_8 = p14;
        } else {
            v4_8 = p14;
            p14.drawArc(v5_0, 0, this.getSweepAngle(), 1, v9_7);
        }
        int v9_8 = this.b;
        if (v9_8 != 0) {
            v4_8.drawArc(v5_0, 0, this.getSweepAngle(), 1, v9_8);
        }
        if (this.m) {
            android.graphics.Path v14_14 = this.k;
            if (v14_14 != null) {
                v4_8.drawOval(v5_0, v14_14);
            }
        }
        android.graphics.Path v14_15 = this.l;
        if (v14_15 != null) {
            if (this.d != null) {
                v4_8.drawRect(v0_9, v14_15);
            } else {
                v4_8.drawOval(v5_0, v14_15);
            }
        }
        if (this.n.length() > 0) {
            android.graphics.Path v14_2;
            if (!this.getHasCustomLogo()) {
                v14_2 = v5_0;
            } else {
                try {
                    android.graphics.Path v14_22 = android.graphics.BitmapFactory.decodeStream(this.getContext().getAssets().open("your_app_logo_facesdk.png"));
                } catch (java.io.IOException) {
                    v14_22 = 0;
                }
                if (v14_22 != null) {
                    v4_8.drawBitmap(android.graphics.Bitmap.createScaledBitmap(v14_22, 240, 240, 0), new android.graphics.Rect(0, 0, 240, 240), new android.graphics.RectF((v5_0.centerX() - ((float) 120)), (v5_0.centerY() - ((float) 120)), (v5_0.centerX() + ((float) 120)), (v5_0.centerY() + ((float) 120))), 0);
                }
                v14_2 = new android.graphics.RectF(v5_0.left, (v5_0.centerY() + ((float) 120)), v5_0.right, v5_0.bottom);
            }
            android.graphics.RectF v2_3 = kotlin.text.StringsKt.y(this.n, new String[] {"\n"}), 0, 6);
            float v6_4 = this.u;
            float v7_5 = (v6_4.getFontMetrics().descent - v6_4.getFontMetrics().ascent);
            float v8_10 = ((v7_5 / ((float) 2)) + (v14_2.centerY() - ((((float) v2_3.size()) * v7_5) / ((float) 2))));
            android.graphics.RectF v2_5 = ((Iterable) v2_3).iterator();
            int v9_3 = 0;
            while (v2_5.hasNext()) {
                String v10_3 = v2_5.next();
                int v11_2 = (v9_3 + 1);
                if (v9_3 < 0) {
                    kotlin.collections.E.throwIndexOverflow();
                }
                v4_8.drawText(((String) v10_3), v14_2.centerX(), ((((float) v9_3) * v7_5) + v8_10), v6_4);
                v9_3 = v11_2;
            }
        }
        if (this.A) {
            android.graphics.Path v14_6 = this.getContext().getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
            if (v14_6 == null) {
                v14_6 = "TRUEID";
            }
            if (kotlin.jvm.internal.Intrinsics.areEqual(v14_6, "hlb")) {
                android.graphics.Path v14_9 = new android.graphics.Paint();
                v14_9.setAntiAlias(1);
                v14_9.setColor(Sk.c.k());
                v14_9.setStyle(android.graphics.Paint$Style.STROKE);
                v14_9.setStrokeWidth(1092616192);
            }
            android.graphics.Path v14_10 = v5_0.centerX();
            int v0_6 = v5_0.centerY();
            android.graphics.RectF v2_12 = ((v5_0.width() / ((float) 2)) - ((float) 20));
            float v5_3 = ((v5_0.height() / ((float) 2)) - ((float) 20));
            float v1_2 = this.w;
            int v3_2 = this.c;
            float v6_5 = this.z;
            if (v3_2 != 0) {
                v6_5.setColor(v3_2.getColor());
                v6_5.setStrokeWidth((v3_2.getStrokeWidth() * 1055286886));
            }
            android.graphics.RectF v2_14 = this.y;
            v2_14.set((v14_10 - v2_12), (v0_6 - v5_3), (v14_10 + v2_12), (v0_6 + v5_3));
            android.graphics.Path v14_12 = this.x;
            v14_12.rewind();
            v14_12.addArc(v2_14, v1_2, 1117782016);
            v14_12.addArc(v2_14, (v1_2 + 1127481344), 1117782016);
            v4_8.drawPath(v14_12, v6_5);
        }
        return;
    }

    public final void onSizeChanged(int p1, int p2, int p3, int p4)
    {
        super.onSizeChanged(p1, p2, p3, p4);
        if ((this.d != null) && ((p1 > null) && (p2 > null))) {
            this.a();
            android.graphics.Paint v1_2 = new android.graphics.Paint();
            v1_2.setColor(0);
            v1_2.setStyle(android.graphics.Paint$Style.FILL);
            this.a = v1_2;
        }
        return;
    }

    public final void setBorderColor(int p2)
    {
        android.graphics.Paint v0 = this.c;
        if (v0 != null) {
            v0.setColor(p2);
        }
        this.invalidate();
        return;
    }

    public final void setOnCallbackOverlayListener(Dk.d p1)
    {
        this.s = p1;
        return;
    }

    public final void setOnOvalRectChanged(kotlin.jvm.functions.Function1 p1)
    {
        this.t = p1;
        return;
    }

    public final void setProcessBorderColor(Integer p1)
    {
        this.C = p1;
        return;
    }

    public final void setRectZoomIn(android.graphics.RectF p1)
    {
        this.g = p1;
        return;
    }

    public final void setRectZoomOut(android.graphics.RectF p1)
    {
        this.h = p1;
        return;
    }

    public final void setScaleFlashCheck(float p1)
    {
        this.B = p1;
        this.i = p1;
        this.h();
        return;
    }
}

package vn.com.vng.zalopay.reformkyc.presentation.view;
public final class CircularProgressView extends android.view.View {
    public float A;
    public KK.b B;
    public float C;
    public final Ae.s D;
    public android.animation.ValueAnimator a;
    public android.os.Handler b;
    public final android.graphics.RectF c;
    public final android.graphics.Paint d;
    public final android.graphics.Paint e;
    public float f;
    public final android.graphics.Paint g;
    public float h;
    public float i;
    public float j;
    public float k;
    public int l;
    public Integer m;
    public Integer n;
    public KK.a o;
    public int p;
    public Integer q;
    public Integer r;
    public KK.a s;
    public boolean t;
    public float u;
    public KK.b v;
    public float w;
    public boolean x;
    public kotlin.jvm.functions.Function1 y;
    public kotlin.jvm.functions.Function1 z;

    public CircularProgressView(android.content.Context p5, android.util.AttributeSet p6)
    {
        super(p5, p6);
        super.c = new android.graphics.RectF();
        boolean v0_27 = new android.graphics.Paint();
        v0_27.setAntiAlias(1);
        KK.b v2_3 = android.graphics.Paint$Style.STROKE;
        v0_27.setStyle(v2_3);
        super.d = v0_27;
        boolean v0_3 = new android.graphics.Paint();
        v0_3.setAntiAlias(1);
        v0_3.setStyle(v2_3);
        super.e = v0_3;
        super.f = 1101004800;
        boolean v0_18 = new android.graphics.Paint();
        v0_18.setColor(-16777216);
        v0_18.setTextSize(super.f);
        v0_18.setAntiAlias(1);
        v0_18.setStrokeWidth(1069547520);
        v0_18.setStyle(android.graphics.Paint$Style.FILL_AND_STROKE);
        super.g = v0_18;
        super.i = 1120403456;
        super.j = super.getResources().getDimension(2131165390);
        super.k = super.getResources().getDimension(2131165376);
        super.l = -16777216;
        boolean v0_26 = KK.a.b;
        super.o = v0_26;
        super.p = -7829368;
        super.s = v0_26;
        super.u = 1132920832;
        KK.b v2_0 = KK.b.b;
        super.v = v2_0;
        super.B = v2_0;
        super.C = 1132920832;
        super.D = new Ae.s(17, super);
        IllegalArgumentException v5_4 = p5.getTheme().obtainStyledAttributes(p6, sm.c.a, 0, 0);
        super.setProgress(v5_4.getFloat(6, super.h));
        super.setProgressMax(v5_4.getFloat(8, super.i));
        super.setProgressBarWidth((v5_4.getDimension(13, super.j) / android.content.res.Resources.getSystem().getDisplayMetrics().density));
        super.setBackgroundProgressBarWidth((v5_4.getDimension(4, super.k) / android.content.res.Resources.getSystem().getDisplayMetrics().density));
        super.setProgressBarColor(v5_4.getInt(9, super.l));
        String v6_3 = v5_4.getColor(12, 0);
        if (v6_3 != null) {
            super.setProgressBarColorStart(Integer.valueOf(v6_3));
        }
        String v6_6 = v5_4.getColor(11, 0);
        if (v6_6 != null) {
            super.setProgressBarColorEnd(Integer.valueOf(v6_6));
        }
        super.setProgressBarColorDirection(vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView.g(v5_4.getInteger(10, super.o.a)));
        super.setBackgroundProgressBarColor(v5_4.getInt(0, super.p));
        String v6_15 = v5_4.getColor(3, 0);
        if (v6_15 != null) {
            super.setBackgroundProgressBarColorStart(Integer.valueOf(v6_15));
        }
        boolean v0_4 = v5_4.getColor(2, 0);
        if (v0_4) {
            super.setBackgroundProgressBarColorEnd(Integer.valueOf(v0_4));
        }
        super.setBackgroundProgressBarColorDirection(vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView.g(v5_4.getInteger(1, super.s.a)));
        boolean v0_12 = v5_4.getInteger(7, super.v.a);
        if (v0_12 != 1) {
            if (v0_12 != 2) {
                throw new IllegalArgumentException(androidx.activity.result.e.g(v0_12, "This value is not supported for ProgressDirection: "));
            } else {
                v2_0 = KK.b.c;
            }
        }
        super.setProgressDirection(v2_0);
        super.setRoundBorder(v5_4.getBoolean(14, super.t));
        super.setStartAngle(v5_4.getFloat(15, 0));
        super.setIndeterminateMode(v5_4.getBoolean(5, super.x));
        v5_4.recycle();
        return;
    }

    public static void a(vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView p2, android.animation.ValueAnimator p3)
    {
        float v3_5;
        float v3_1 = p3.getAnimatedValue();
        if (!(v3_1 instanceof Float)) {
            v3_5 = 0;
        } else {
            v3_5 = ((Float) v3_1);
        }
        if (v3_5 != 0) {
            float v3_6 = v3_5.floatValue();
            if (!p2.x) {
                p2.setProgress(v3_6);
            } else {
                p2 = p2.setProgressIndeterminateMode(v3_6);
            }
            if (p2.x) {
                float v3_3 = ((v3_6 * ((float) 360)) / ((float) 100));
                if (p2.B != KK.b.b) {
                    v3_3 = (- v3_3);
                }
                p2.setStartAngleIndeterminateMode((v3_3 + 1132920832));
            }
        }
        return;
    }

    public static void b(vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView p5)
    {
        Long v2 = Long.valueOf(1500);
        if (p5.x) {
            int v3_2 = p5.b;
            if (v3_2 != 0) {
                v3_2.postDelayed(p5.D, 1500);
            }
            float v0_1;
            KK.b v1 = KK.b.b;
            if (p5.B != v1) {
                v0_1 = v1;
            } else {
                v0_1 = KK.b.c;
            }
            void v5_1 = p5.setProgressDirectionIndeterminateMode(v0_1);
            if (v5_1.B != v1) {
                vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView.f(v5_1, v5_1.i, v2, 12);
            } else {
                vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView.f(v5_1, 0, v2, 12);
                return;
            }
        }
        return;
    }

    public static void f(vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView p2, float p3, Long p4, int p5)
    {
        if ((p5 & 2) != 0) {
            p4 = 0;
        }
        if (p2.h != p3) {
            int v5_7 = p2.a;
            if (v5_7 != 0) {
                v5_7.cancel();
            }
            int v5_3;
            if (!p2.x) {
                v5_3 = p2.h;
            } else {
                v5_3 = p2.A;
            }
            android.animation.ValueAnimator v0_1 = new float[2];
            v0_1[0] = v5_3;
            v0_1[1] = p3;
            p2.a = android.animation.ValueAnimator.ofFloat(v0_1);
            if (p4 != null) {
                android.animation.ValueAnimator v3_2 = p4.longValue();
                android.animation.ValueAnimator v0_2 = p2.a;
                if (v0_2 != null) {
                    v0_2.setDuration(v3_2);
                }
            }
            android.animation.ValueAnimator v3_3 = p2.a;
            if (v3_3 != null) {
                v3_3.addUpdateListener(new ID.c(1, p2));
            }
            android.animation.ValueAnimator v2_1 = p2.a;
            if (v2_1 != null) {
                v2_1.start();
            }
        }
        return;
    }

    public static KK.a g(int p2)
    {
        if (p2 == 1) {
            return KK.a.b;
        } else {
            if (p2 == 2) {
                return KK.a.c;
            } else {
                if (p2 == 3) {
                    return KK.a.d;
                } else {
                    if (p2 != 4) {
                        throw new IllegalArgumentException(androidx.activity.result.e.g(p2, "This value is not supported for GradientDirection: "));
                    } else {
                        return KK.a.e;
                    }
                }
            }
        }
    }

    private final void setProgressDirectionIndeterminateMode(KK.b p1)
    {
        this.B = p1;
        this.invalidate();
        return;
    }

    private final void setProgressIndeterminateMode(float p1)
    {
        this.A = p1;
        this.invalidate();
        return;
    }

    private final void setStartAngleIndeterminateMode(float p1)
    {
        this.C = p1;
        this.invalidate();
        return;
    }

    public final android.graphics.LinearGradient c(int p10, int p11, KK.a p12)
    {
        int v2;
        int v4;
        int v5;
        float v3;
        float v12_1 = p12.ordinal();
        if (v12_1 == 0) {
            v4 = ((float) this.getWidth());
            v2 = 0;
            v3 = 0;
            v5 = 0;
        } else {
            if (v12_1 == 1) {
                v2 = ((float) this.getWidth());
                v3 = 0;
                v4 = 0;
            } else {
                if (v12_1 == 2) {
                    v5 = ((float) this.getHeight());
                    v2 = 0;
                    v3 = 0;
                    v4 = 0;
                    return new android.graphics.LinearGradient(v2, v3, v4, v5, p10, p11, android.graphics.Shader$TileMode.CLAMP);
                } else {
                    if (v12_1 != 3) {
                        throw new kotlin.NoWhenBranchMatchedException();
                    } else {
                        v3 = ((float) this.getHeight());
                        v2 = 0;
                        v4 = 0;
                    }
                }
            }
            v5 = v4;
        }
        return new android.graphics.LinearGradient(v2, v3, v4, v5, p10, p11, android.graphics.Shader$TileMode.CLAMP);
    }

    public final void d()
    {
        android.graphics.LinearGradient v0_2;
        android.graphics.LinearGradient v0_0 = this.q;
        if (v0_0 == null) {
            v0_2 = this.p;
        } else {
            v0_2 = v0_0.intValue();
        }
        android.graphics.Paint v1_2;
        android.graphics.Paint v1_1 = this.r;
        if (v1_1 == null) {
            v1_2 = this.p;
        } else {
            v1_2 = v1_1.intValue();
        }
        this.d.setShader(this.c(v0_2, v1_2, this.s));
        return;
    }

    public final void e()
    {
        android.graphics.LinearGradient v0_2;
        android.graphics.LinearGradient v0_0 = this.m;
        if (v0_0 == null) {
            v0_2 = this.l;
        } else {
            v0_2 = v0_0.intValue();
        }
        android.graphics.Paint v1_2;
        android.graphics.Paint v1_1 = this.n;
        if (v1_1 == null) {
            v1_2 = this.l;
        } else {
            v1_2 = v1_1.intValue();
        }
        this.e.setShader(this.c(v0_2, v1_2, this.o));
        return;
    }

    public final int getBackgroundProgressBarColor()
    {
        return this.p;
    }

    public final KK.a getBackgroundProgressBarColorDirection()
    {
        return this.s;
    }

    public final Integer getBackgroundProgressBarColorEnd()
    {
        return this.r;
    }

    public final Integer getBackgroundProgressBarColorStart()
    {
        return this.q;
    }

    public final float getBackgroundProgressBarWidth()
    {
        return this.k;
    }

    public final boolean getIndeterminateMode()
    {
        return this.x;
    }

    public final kotlin.jvm.functions.Function1 getOnIndeterminateModeChangeListener()
    {
        return this.z;
    }

    public final kotlin.jvm.functions.Function1 getOnProgressChangeListener()
    {
        return this.y;
    }

    public final float getProgress()
    {
        return this.h;
    }

    public final int getProgressBarColor()
    {
        return this.l;
    }

    public final KK.a getProgressBarColorDirection()
    {
        return this.o;
    }

    public final Integer getProgressBarColorEnd()
    {
        return this.n;
    }

    public final Integer getProgressBarColorStart()
    {
        return this.m;
    }

    public final float getProgressBarWidth()
    {
        return this.j;
    }

    public final KK.b getProgressDirection()
    {
        return this.v;
    }

    public final float getProgressMax()
    {
        return this.i;
    }

    public final boolean getRoundBorder()
    {
        return this.t;
    }

    public final float getStartAngle()
    {
        return this.u;
    }

    public final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        android.os.Handler v0_0 = this.a;
        if (v0_0 != null) {
            v0_0.cancel();
        }
        android.os.Handler v0_1 = this.b;
        if (v0_1 != null) {
            v0_1.removeCallbacks(this.D);
        }
        return;
    }

    public final void onDraw(android.graphics.Canvas p9)
    {
        android.graphics.Canvas v1_4;
        super.onDraw(p9);
        float v2_0 = this.c;
        p9.drawOval(v2_0, this.d);
        android.graphics.Paint v0_2 = this.x;
        if (v0_2 == null) {
            v1_4 = this.h;
        } else {
            v1_4 = this.A;
        }
        int v5_1;
        float v3_1 = 0;
        if ((v0_2 == null) || (this.B != KK.b.b)) {
            v5_1 = 0;
        } else {
            v5_1 = 1;
        }
        if ((v0_2 == null) && (this.v == KK.b.b)) {
            v3_1 = 1;
        }
        if ((v5_1 == 0) && (v3_1 == 0)) {
            float v3_2 = -360;
        } else {
            v3_2 = 360;
        }
        android.graphics.Paint v0_1;
        if (v0_2 == null) {
            v0_1 = this.u;
        } else {
            v0_1 = this.C;
        }
        p9.drawArc(v2_0, v0_1, ((((float) v3_2) * ((v1_4 * 1120403456) / this.i)) / ((float) 100)), 0, this.e);
        android.graphics.Paint v0_3 = this.g;
        v0_3.setTextSize(this.f);
        float v2_1 = this.i;
        if (this.h > v2_1) {
            this.setProgress(v2_1);
        }
        String v9_5 = ((int) this.h);
        float v2_3 = ((int) this.i);
        float v3_7 = new StringBuilder();
        v3_7.append(v9_5);
        v3_7.append("/");
        v3_7.append(v2_3);
        String v9_7 = v3_7.toString();
        float v2_7 = ((((float) v9_7.length()) * this.f) / ((float) 4));
        float v3_14 = ((((float) v9_7.length()) * this.f) / ((float) 8));
        if (((int) this.h) == ((int) this.i)) {
            v0_3.setColor(this.l);
        }
        float v4_8 = this.w;
        p9.drawText(v9_7, (v4_8 - v2_7), (v4_8 + v3_14), v0_3);
        return;
    }

    public final void onMeasure(int p3, int p4)
    {
        float v3_4 = Math.min(android.view.View.getDefaultSize(this.getSuggestedMinimumWidth(), p3), android.view.View.getDefaultSize(this.getSuggestedMinimumHeight(), p4));
        this.setMeasuredDimension(v3_4, v3_4);
        float v3_1 = ((float) v3_4);
        this.f = (v3_1 / 1084227584);
        this.w = (v3_1 / 1073741824);
        android.graphics.RectF v4_5 = this.j;
        float v0_1 = this.k;
        if (v4_5 <= v0_1) {
            v4_5 = v0_1;
        }
        android.graphics.RectF v4_6 = (v4_5 / ((float) 2));
        this.c.set((((float) 0) + v4_6), (((float) 0) + v4_6), (v3_1 - v4_6), (v3_1 - v4_6));
        return;
    }

    public final void onSizeChanged(int p1, int p2, int p3, int p4)
    {
        super.onSizeChanged(p1, p2, p3, p4);
        this.e();
        this.d();
        this.invalidate();
        return;
    }

    public void setBackgroundColor(int p1)
    {
        this.setBackgroundProgressBarColor(p1);
        return;
    }

    public final void setBackgroundProgressBarColor(int p1)
    {
        this.p = p1;
        this.d();
        this.invalidate();
        return;
    }

    public final void setBackgroundProgressBarColorDirection(KK.a p1)
    {
        this.s = p1;
        this.d();
        this.invalidate();
        return;
    }

    public final void setBackgroundProgressBarColorEnd(Integer p1)
    {
        this.r = p1;
        this.d();
        this.invalidate();
        return;
    }

    public final void setBackgroundProgressBarColorStart(Integer p1)
    {
        this.q = p1;
        this.d();
        this.invalidate();
        return;
    }

    public final void setBackgroundProgressBarWidth(float p2)
    {
        float v2_1 = (p2 * android.content.res.Resources.getSystem().getDisplayMetrics().density);
        this.k = v2_1;
        this.d.setStrokeWidth(v2_1);
        this.requestLayout();
        this.invalidate();
        return;
    }

    public final void setIndeterminateMode(boolean p3)
    {
        this.x = p3;
        Ae.s v0_0 = this.z;
        if (v0_0 != null) {
            v0_0.invoke(Boolean.valueOf(p3));
        }
        this.setProgressIndeterminateMode(0);
        this.setProgressDirectionIndeterminateMode(KK.b.b);
        this.setStartAngleIndeterminateMode(1132920832);
        android.os.Handler v3_2 = this.b;
        Ae.s v0_1 = this.D;
        if (v3_2 != null) {
            v3_2.removeCallbacks(v0_1);
        }
        android.os.Handler v3_3 = this.a;
        if (v3_3 != null) {
            v3_3.cancel();
        }
        android.os.Handler v3_5 = new android.os.Handler();
        this.b = v3_5;
        if (this.x) {
            v3_5.post(v0_1);
        }
        return;
    }

    public final void setOnIndeterminateModeChangeListener(kotlin.jvm.functions.Function1 p1)
    {
        this.z = p1;
        return;
    }

    public final void setOnProgressChangeListener(kotlin.jvm.functions.Function1 p1)
    {
        this.y = p1;
        return;
    }

    public final void setProgress(float p3)
    {
        float v1 = this.i;
        if (this.h > v1) {
            p3 = v1;
        }
        this.h = p3;
        kotlin.jvm.functions.Function1 v0_2 = this.y;
        if (v0_2 != null) {
            v0_2.invoke(Float.valueOf(p3));
        }
        this.invalidate();
        return;
    }

    public final void setProgressBarColor(int p1)
    {
        this.l = p1;
        this.e();
        this.invalidate();
        return;
    }

    public final void setProgressBarColorDirection(KK.a p1)
    {
        this.o = p1;
        this.e();
        this.invalidate();
        return;
    }

    public final void setProgressBarColorEnd(Integer p1)
    {
        this.n = p1;
        this.e();
        this.invalidate();
        return;
    }

    public final void setProgressBarColorStart(Integer p1)
    {
        this.m = p1;
        this.e();
        this.invalidate();
        return;
    }

    public final void setProgressBarWidth(float p2)
    {
        float v2_1 = (p2 * android.content.res.Resources.getSystem().getDisplayMetrics().density);
        this.j = v2_1;
        this.e.setStrokeWidth(v2_1);
        this.requestLayout();
        this.invalidate();
        return;
    }

    public final void setProgressDirection(KK.b p1)
    {
        this.v = p1;
        this.invalidate();
        return;
    }

    public final void setProgressMax(float p3)
    {
        if (this.i < 0) {
            p3 = 1120403456;
        }
        this.i = p3;
        this.invalidate();
        return;
    }

    public final void setProgressWithAnimation(float p3)
    {
        vn.com.vng.zalopay.reformkyc.presentation.view.CircularProgressView.f(this, p3, 0, 14);
        return;
    }

    public final void setRoundBorder(boolean p2)
    {
        android.graphics.Paint$Cap v2_1;
        this.t = p2;
        if (p2 == null) {
            v2_1 = android.graphics.Paint$Cap.BUTT;
        } else {
            v2_1 = android.graphics.Paint$Cap.ROUND;
        }
        this.e.setStrokeCap(v2_1);
        this.invalidate();
        return;
    }

    public final void setStartAngle(float p5)
    {
        float v5_1 = (p5 + 1132920832);
        while(true) {
            float v1 = v5_1 cmp 1135869952;
            if (v1 <= 0) {
                break;
            }
            v5_1 -= ((float) 360);
        }
        if (v5_1 >= 0) {
            if (v1 > 0) {
                v5_1 = 1135869952;
            }
        } else {
            v5_1 = 0;
        }
        this.u = v5_1;
        this.invalidate();
        return;
    }
}

package vn.zalopay.ui.edittext;
public class ZPEditText extends androidx.appcompat.widget.AppCompatEditText {
    public boolean A;
    public GZ.k A0;
    public final int B;
    public boolean B0;
    public final int C;
    public int C0;
    public float D;
    public int D0;
    public String E;
    public float F;
    public boolean G;
    public float H;
    public CharSequence I;
    public boolean J;
    public final boolean K;
    public int L;
    public final int M;
    public boolean N;
    public boolean O;
    public boolean P;
    public final boolean Q;
    public final android.graphics.Bitmap R;
    public final android.graphics.Bitmap S;
    public boolean T;
    public boolean U;
    public boolean V;
    public final int W;
    public final boolean a0;
    public boolean b0;
    public int c0;
    public final int d0;
    public boolean e0;
    public boolean f0;
    public int g;
    public android.content.res.ColorStateList g0;
    public int h;
    public android.content.res.ColorStateList h0;
    public int i;
    public final android.animation.ArgbEvaluator i0;
    public int j;
    public final android.graphics.Paint j0;
    public int k;
    public final android.text.TextPaint k0;
    public int l;
    public final android.text.TextPaint l0;
    public final int m;
    public android.animation.ObjectAnimator m0;
    public boolean n;
    public android.animation.ObjectAnimator n0;
    public boolean o;
    public final GZ.c o0;
    public int p;
    public android.view.View$OnFocusChangeListener p0;
    public final int q;
    public GZ.s q0;
    public final int r;
    public vn.zalopay.ui.edittext.ZPEditText$b r0;
    public final int s;
    public vn.zalopay.ui.edittext.ZPEditText$c s0;
    public final int t;
    public int t0;
    public int u;
    public final int u0;
    public int v;
    public boolean v0;
    public final boolean w;
    public boolean w0;
    public int x;
    public int x0;
    public int y;
    public GZ.a y0;
    public boolean z;
    public GZ.m z0;

    public ZPEditText(android.content.Context p12, android.util.AttributeSet p13)
    {
        super(p12, p13);
        super.w = 0;
        super.i0 = new android.animation.ArgbEvaluator();
        super.j0 = new android.graphics.Paint(1);
        super.k0 = new android.text.TextPaint(1);
        super.l0 = new android.text.TextPaint(1);
        super.x0 = -1;
        super.B0 = 1;
        super.C0 = -1;
        super.D0 = -1;
        if (!super.isInEditMode()) {
            super.c0 = super.h(40);
            super.d0 = super.h(35);
            super.m = super.h(2);
            super.B = super.getResources().getDimensionPixelSize(2131165308);
            int[] v6_6 = p12.obtainStyledAttributes(p13, sZ.h.s);
            super.g0 = v6_6.getColorStateList(34);
            super.h0 = v6_6.getColorStateList(35);
            super.p = v6_6.getColor(2, -16777216);
            int v4_18 = new android.util.TypedValue();
            p12.getTheme().resolveAttribute(16843827, v4_18, 1);
            int v4_19 = v4_18.data;
        } else {
            return;
        }
    }

    private int getBottomEllipsisWidth()
    {
        if (!this.z) {
            return 0;
        } else {
            return (this.h(4) + (this.B * 5));
        }
    }

    private int getBottomTextLeftOffset()
    {
        return this.getBottomEllipsisWidth();
    }

    private int getBottomTextRightOffset()
    {
        return this.getCharactersCounterWidth();
    }

    private String getCharactersCounterText()
    {
        if (this.x > 0) {
            if (this.y > 0) {
                String v2_0;
                String v0_10 = new StringBuilder();
                String v2_6 = this.getText();
                GZ.s v3_2 = this.q0;
                if (v3_2 != null) {
                    v2_0 = v3_2.getLength();
                } else {
                    v2_0 = v2_6.length();
                }
                v0_10.append(v2_0);
                v0_10.append(" / ");
                v0_10.append(this.x);
                v0_10.append("-");
                v0_10.append(this.y);
                return v0_10.toString();
            } else {
                String v2_2;
                String v0_3 = new StringBuilder();
                String v2_1 = this.getText();
                GZ.s v3_0 = this.q0;
                if (v3_0 != null) {
                    v2_2 = v3_0.getLength();
                } else {
                    v2_2 = v2_1.length();
                }
                v0_3.append(v2_2);
                v0_3.append(" / ");
                return A1.a.h(this.x, "+", v0_3);
            }
        } else {
            String v2_5;
            String v0_7 = new StringBuilder();
            String v2_4 = this.getText();
            GZ.s v3_1 = this.q0;
            if (v3_1 != null) {
                v2_5 = v3_1.getLength();
            } else {
                v2_5 = v2_4.length();
            }
            v0_7.append(v2_5);
            v0_7.append(" / ");
            v0_7.append(this.y);
            return v0_7.toString();
        }
    }

    private int getCharactersCounterWidth()
    {
        if ((this.x <= 0) && (this.y <= 0)) {
            return 0;
        } else {
            return ((int) this.k0.measureText(this.getCharactersCounterText()));
        }
    }

    private void setFloatingLabelInternal(int p4)
    {
        if (p4 == 1) {
            this.n = 1;
            this.o = 0;
            return;
        } else {
            if (p4 == 2) {
                this.n = 1;
                this.o = 1;
                return;
            } else {
                this.n = 0;
                this.o = 0;
                return;
            }
        }
    }

    public final void d()
    {
        int v1 = 1;
        if (((!this.b0) && (!this.Q)) || ((this.x <= 0) && (this.y <= 0))) {
            this.O = 1;
            return;
        } else {
            int v0_2;
            int v0_1 = this.getText();
            if (v0_1 != 0) {
                int v3_0 = this.q0;
                if (v3_0 != 0) {
                    v0_2 = v3_0.getLength();
                } else {
                    v0_2 = v0_1.length();
                }
            } else {
                v0_2 = 0;
            }
            if (v0_2 < this.x) {
                v1 = 0;
            } else {
                int v3_2 = this.y;
                if ((v3_2 > 0) && (v0_2 > v3_2)) {
                }
            }
            this.O = v1;
            return;
        }
    }

    public final void e()
    {
        if (this.V) {
            int v0_4 = this.S;
            if (v0_4 != 0) {
                this.c0 = ((int) (this.getResources().getDimension(2131166212) + ((float) v0_4.getWidth())));
                return;
            }
        }
        this.c0 = this.h(40);
        return;
    }

    public final void f()
    {
        if (this.v0) {
            if (this.x0 == -1) {
                this.x0 = this.getPaddingBottom();
            }
            int v0_1 = (this.q + this.g);
            if (!android.text.TextUtils.isEmpty(this.getText())) {
                super.setPadding(this.s, v0_1, ((this.getButtonsCount() * this.c0) + this.t), this.x0);
                return;
            } else {
                super.setPadding(this.s, ((v0_1 + this.x0) / 2), ((this.getButtonsCount() * this.c0) + this.t), ((v0_1 + this.x0) / 2));
                return;
            }
        } else {
            super.setPadding(this.s, (this.q + this.g), (this.t + (this.getButtonsCount() * this.c0)), this.getPaddingBottom());
            return;
        }
    }

    public final void g(android.text.Editable p2)
    {
        if (this.A0 != null) {
            int v2_5 = p2.toString();
            if (v2_5 == 0) {
                v2_5 = "";
            }
            String v0_2 = this.A0.b(v2_5);
            if (!v2_5.equals(v0_2)) {
                int v2_3;
                this.setText(v0_2);
                if (this.getText() == null) {
                    v2_3 = 0;
                } else {
                    v2_3 = this.getText().length();
                }
                this.setSelection(v2_3);
                return;
            }
        }
        return;
    }

    public int getButtonsCount()
    {
        return this.U;
    }

    public CharSequence getError()
    {
        return this.E;
    }

    public android.animation.ObjectAnimator getLabelAnimator()
    {
        if (this.m0 == null) {
            android.animation.ObjectAnimator v0_3 = new float[2];
            v0_3 = {0, 1065353216};
            this.m0 = android.animation.ObjectAnimator.ofFloat(this, "floatingLabelFraction", v0_3);
        }
        long v1_1;
        if (!this.P) {
            v1_1 = 0;
        } else {
            v1_1 = 300;
        }
        this.m0.setDuration(v1_1);
        return this.m0;
    }

    public android.animation.ObjectAnimator getLabelFocusAnimator()
    {
        if (this.n0 == null) {
            android.animation.ObjectAnimator v0_2 = new float[2];
            v0_2 = {0, 1065353216};
            this.n0 = android.animation.ObjectAnimator.ofFloat(this, "focusFraction", v0_2);
        }
        return this.n0;
    }

    public String getSafeText()
    {
        if (this.getText() != null) {
            return this.getText().toString();
        } else {
            return "";
        }
    }

    public String getTextWithoutSpace()
    {
        if (!android.text.TextUtils.isEmpty(this.getText())) {
            return this.getText().toString().replaceAll(" ", "");
        } else {
            return "";
        }
    }

    public java.util.List getValidators()
    {
        return 0;
    }

    public final int h(int p3)
    {
        return Math.round(android.util.TypedValue.applyDimension(1, ((float) p3), this.getContext().getResources().getDisplayMetrics()));
    }

    public final void i()
    {
        float v0_1;
        int v1 = 1;
        if ((this.x <= 0) && ((this.y <= 0) && ((!this.z) && (this.E == null)))) {
            v0_1 = 0;
        } else {
            v0_1 = 1;
        }
        int v3 = this.C;
        if (v3 <= 0) {
            if (v0_1 == 0) {
                v1 = 0;
            }
        } else {
            v1 = v3;
        }
        this.D = ((float) v1);
        return;
    }

    public final void j()
    {
        int v0_5;
        if (!this.n) {
            v0_5 = this.l;
        } else {
            v0_5 = (this.i + this.l);
        }
        int v1_5;
        this.g = v0_5;
        int v1_7 = this.k0;
        v1_7.setTextSize(((float) this.k));
        int v0_1 = v1_7.getFontMetrics();
        if (!this.J) {
            v1_5 = (this.m * 2);
        } else {
            v1_5 = this.m;
        }
        this.h = (((int) ((v0_1.descent - v0_1.ascent) * this.D)) + v1_5);
        this.f();
        return;
    }

    public final void k()
    {
        if (android.text.TextUtils.isEmpty(this.getText())) {
            this.n();
        } else {
            int v0_5 = this.getText();
            this.setText(0);
            this.n();
            this.setText(v0_5);
            this.setSelection(v0_5.length());
            this.F = 1065353216;
            this.G = 1;
        }
        this.o();
        return;
    }

    public final boolean l(android.view.MotionEvent p7)
    {
        float v0_0 = p7.getX();
        float v7_1 = p7.getY();
        float v1_0 = ((this.getWidth() - this.getPaddingRight()) - this.W);
        int v3_4 = ((((this.getHeight() + this.getScrollY()) - this.getPaddingBottom()) + this.m) - this.d0);
        if (this.V) {
            v1_0 -= (this.h(1) + ((((int) this.getResources().getDimension(2131165266)) * 2) + this.S.getWidth()));
        }
        if ((v0_0 < ((float) v1_0)) || ((v0_0 >= ((float) (v1_0 + this.c0))) || ((v7_1 < ((float) v3_4)) || (v7_1 >= ((float) (v3_4 + this.d0)))))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean m()
    {
        if (((this.B0) && ((this.E != null) || (!this.O))) && (!android.text.TextUtils.isEmpty(this.getText().toString()))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void n()
    {
        int v0_0 = this.h0;
        if (v0_0 != 0) {
            this.setHintTextColor(v0_0);
            return;
        } else {
            this.setHintTextColor(((this.p & 16777215) | 1140850688));
            return;
        }
    }

    public final void o()
    {
        if (this.g0 == null) {
            int[] v2_4 = this.p;
            this.g0 = new android.content.res.ColorStateList(new int[][] {new int[] {16842910}), android.widget.EditText.EMPTY_STATE_SET}), new int[] {((v2_4 & 16777215) | -553648128), ((v2_4 & 16777215) | 1140850688)}));
        }
        this.setTextColor(this.g0);
        return;
    }

    public final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (!this.b0) {
            this.b0 = 1;
        }
        return;
    }

    public void onDraw(android.graphics.Canvas p19)
    {
        vn.zalopay.ui.edittext.ZPEditText v0 = this;
        int v7_0 = (this.getPaddingLeft() + this.getScrollX());
        float v8_0 = ((this.getWidth() + this.getScrollX()) - this.getPaddingRight());
        float v9_0 = ((this.getHeight() + this.getScrollY()) - this.getPaddingBottom());
        android.graphics.Paint v10 = this.j0;
        v10.setAlpha(255);
        if ((this.hasFocus()) && ((this.U) && ((!android.text.TextUtils.isEmpty(this.getText())) && (this.isEnabled())))) {
            float v3_30;
            float v2_53 = ((int) this.getResources().getDimension(2131165266));
            String v4_58 = this.R;
            if (this.W == -1) {
                v3_30 = ((this.c0 - v4_58.getWidth()) / 2);
            } else {
                v3_30 = ((this.c0 - v4_58.getWidth()) - this.W);
            }
            float v3_33 = (v3_30 + v8_0);
            if (this.V) {
                v3_33 -= (this.h(1) + ((v2_53 * 2) + this.S.getWidth()));
            }
            float v5_63;
            android.text.TextPaint v6_11;
            if (!this.a0) {
                android.text.TextPaint v6_9 = this.d0;
                v5_63 = ((this.m + v9_0) - v6_9);
                v6_11 = ((v6_9 - v4_58.getHeight()) / 2);
            } else {
                v5_63 = this.getScrollY();
                v6_11 = ((this.getMeasuredHeight() - v4_58.getHeight()) / 2);
            }
            p19.drawBitmap(v4_58, ((float) v3_33), ((float) (v6_11 + v5_63)), v10);
            if (this.V) {
                p19.drawBitmap(this.S, ((float) (this.h(1) + ((v2_53 * 2) + (this.R.getWidth() + v3_33)))), ((float) (((this.getMeasuredHeight() - this.S.getHeight()) / 2) + this.getScrollY())), v10);
                android.text.TextPaint v6_5 = this.l0;
                v6_5.setStrokeWidth(((float) this.h(1)));
                v6_5.setStyle(android.graphics.Paint$Style.FILL);
                String v4_11 = ((int) this.getResources().getDimension(2131166219));
                float v5_8 = ((this.getHeight() - v4_11) / 2);
                p19.drawLine(((float) ((this.R.getWidth() + v3_33) + v2_53)), ((float) v5_8), ((float) ((this.R.getWidth() + v3_33) + v2_53)), ((float) (v5_8 + v4_11)), v6_5);
            }
        }
        android.text.TextPaint v6_6;
        android.graphics.Canvas v1_2;
        if (this.J) {
            v1_2 = p19;
            v6_6 = v10;
        } else {
            android.graphics.Canvas v1_5 = ((this.getPaddingBottom() - this.h(1)) + v9_0);
            float v2_6 = (v0.getScrollX() + v0.t0);
            String v4_15 = ((this.getWidth() + this.getScrollX()) - this.u0);
            if (this.m()) {
                v6_6 = v10;
                if (this.isEnabled()) {
                    if (!this.hasFocus()) {
                        float v3_10 = this.L;
                        if (v3_10 == -1) {
                            v3_10 = this.p;
                        }
                        v6_6.setColor(v3_10);
                        v1_2 = p19;
                        p19.drawRect(((float) v2_6), ((float) v1_5), ((float) v4_15), ((float) (this.h(1) + v1_5)), v6_6);
                    } else {
                        v6_6.setColor(this.u);
                        v1_2 = p19;
                        p19.drawRect(((float) v2_6), ((float) v1_5), ((float) v4_15), ((float) (this.h(2) + v1_5)), v6_6);
                    }
                } else {
                    float v3_15 = this.L;
                    if (v3_15 == -1) {
                        v3_15 = this.p;
                    }
                    v6_6.setColor(v3_15);
                    v1_2 = p19;
                    p19.drawRect(((float) v2_6), ((float) v1_5), (((float) this.h(1)) + ((float) v7_0)), ((float) (this.h(1) + v1_5)), v6_6);
                }
            } else {
                v10.setColor(this.v);
                v1_2 = p19;
                v6_6 = v10;
                p19.drawRect(((float) v2_6), ((float) v1_5), ((float) v4_15), ((float) (this.h(2) + v1_5)), v6_6);
            }
        }
        float v3_21 = this.k0;
        v3_21.setTextSize(((float) this.k));
        if ((this.n) && (!android.text.TextUtils.isEmpty(this.I))) {
            v3_21.setTextSize(((float) this.i));
            if (this.m()) {
                String v4_23 = this.j;
                if (v4_23 == -1) {
                    v4_23 = ((this.p & 16777215) | 1140850688);
                }
                v3_21.setColor(((Integer) this.i0.evaluate((this.H * ((float) this.isEnabled())), Integer.valueOf(v4_23), Integer.valueOf(this.u))).intValue());
            } else {
                v3_21.setColor(this.v);
            }
            float v2_29 = v3_21.measureText(this.I.toString());
            if ((this.getGravity() & 5) != 5) {
                if ((this.getGravity() & 3) != 3) {
                    v7_0 += ((int) (((((float) ((v0.getWidth() - v0.s) - this.t)) - v2_29) / 1073741824) + ((float) this.s)));
                }
            } else {
                v7_0 = ((int) (((float) v8_0) - v2_29));
            }
            float v5_39;
            float v2_33 = this.l;
            float v8_3 = 1065353216;
            if (!this.A) {
                v5_39 = this.F;
            } else {
                v5_39 = 1065353216;
            }
            float v2_39 = ((int) ((((float) ((this.q + this.i) + v2_33)) - (((float) v2_33) * v5_39)) + ((float) this.getScrollY())));
            if ((this.m()) || (this.E == null)) {
                String v4_44;
                if (!this.A) {
                    v4_44 = this.F;
                } else {
                    v4_44 = 1065353216;
                }
                String v4_46 = this.j;
                if (v4_46 == -1) {
                    v8_3 = (((float) android.graphics.Color.alpha(v4_46)) / 1132462080);
                }
                v3_21.setAlpha(((int) (((((this.H * 1060991140) * ((float) this.isEnabled())) + 1048911544) * (v4_44 * 1132396544)) * v8_3)));
                v1_2.drawText(this.I.toString(), ((float) v7_0), ((float) v2_39), v3_21);
            } else {
                v3_21.setAlpha(255);
                v1_2.drawText(this.E, ((float) v7_0), ((float) v2_39), v3_21);
            }
        }
        super.onDraw(p19);
        if ((!this.w0) && (this.v0)) {
            this.f();
            this.w0 = 1;
        }
        if (this.K) {
            float v2_47 = this.getScrollY();
            float v3_22 = this.getScrollX();
            float v5_49 = (this.getWidth() + this.getScrollX());
            String v4_55 = this.M;
            if (v4_55 == -1) {
                v4_55 = this.p;
            }
            v6_6.setColor(v4_55);
            v1_2.drawRect(((float) v3_22), ((float) v2_47), ((float) v5_49), ((float) (this.h(1) + v2_47)), v6_6);
        }
        return;
    }

    public final void onLayout(boolean p1, int p2, int p3, int p4, int p5)
    {
        super.onLayout(p1, p2, p3, p4, p5);
        return;
    }

    public final boolean onTextContextMenuItem(int p2)
    {
        boolean v2_1 = super.onTextContextMenuItem(p2);
        GZ.m v0 = this.z0;
        if (v0 != null) {
            v0.a();
        }
        return v2_1;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p7)
    {
        if ((!this.z) || ((this.getScrollX() <= 0) || ((p7.getAction() != 0) || ((p7.getX() >= ((float) this.h(20))) || ((p7.getY() <= ((float) ((this.getHeight() - this.h) - this.r))) || (p7.getY() >= ((float) (this.getHeight() - this.r)))))))) {
            if ((this.hasFocus()) && ((this.U) && (this.isEnabled()))) {
                vn.zalopay.ui.edittext.ZPEditText$c v0_20 = p7.getAction();
                if (v0_20 == null) {
                    if (this.l(p7)) {
                        int v7_2 = this.C0;
                        if (v7_2 != -1) {
                            UR.a.c(v7_2, 0, 0, 0);
                        }
                        this.e0 = 1;
                        this.f0 = 1;
                        return 1;
                    }
                } else {
                    if (v0_20 == 1) {
                        if (this.f0) {
                            if (!android.text.TextUtils.isEmpty(this.getText())) {
                                this.setText(0);
                            }
                            this.f0 = 0;
                            vn.zalopay.ui.edittext.ZPEditText$c v0_27 = this.r0;
                            if (v0_27 != null) {
                                v0_27.a();
                            }
                        }
                        if (!this.e0) {
                            this.e0 = 0;
                            if (this.V) {
                                vn.zalopay.ui.edittext.ZPEditText$c v0_30 = p7.getX();
                                float v1_1 = p7.getY();
                                float v2_16 = (this.getWidth() - this.getPaddingRight());
                                int v3_8 = this.d0;
                                int v4_5 = ((((this.getHeight() + this.getScrollY()) - this.getPaddingBottom()) + this.m) - v3_8);
                                if ((v0_30 < ((float) v2_16)) || ((v0_30 >= ((float) (v2_16 + this.c0))) || ((v1_1 < ((float) v4_5)) || (v1_1 >= ((float) (v4_5 + v3_8)))))) {
                                    return super.onTouchEvent(p7);
                                } else {
                                    vn.zalopay.ui.edittext.ZPEditText$c v0_7 = this.s0;
                                    if (v0_7 == null) {
                                        return super.onTouchEvent(p7);
                                    } else {
                                        this.getSafeText();
                                        v0_7.a();
                                        return super.onTouchEvent(p7);
                                    }
                                }
                            }
                        } else {
                            this.e0 = 0;
                            return 1;
                        }
                    } else {
                        if (v0_20 != 2) {
                            if (v0_20 == 3) {
                                this.e0 = 0;
                                this.f0 = 0;
                            }
                            return super.onTouchEvent(p7);
                        }
                    }
                }
                if ((this.f0) && (!this.l(p7))) {
                    this.f0 = 0;
                }
                if (this.e0) {
                    return 1;
                }
            }
            return super.onTouchEvent(p7);
        } else {
            this.setSelection(0);
            return 0;
        }
    }

    public boolean p()
    {
        return 1;
    }

    public void setAccentTypeface(android.graphics.Typeface p2)
    {
        this.k0.setTypeface(p2);
        this.postInvalidate();
        return;
    }

    public void setAfterValidateListener(GZ.a p1)
    {
        this.y0 = p1;
        return;
    }

    public void setAutoValidate(boolean p1)
    {
        this.N = p1;
        if (p1) {
            this.p();
        }
        return;
    }

    public void setBaseColor(int p2)
    {
        if (this.p != p2) {
            this.p = p2;
        }
        this.k();
        this.postInvalidate();
        return;
    }

    public void setBottomTextSize(int p1)
    {
        this.k = p1;
        this.j();
        return;
    }

    public void setClearTextListener(vn.zalopay.ui.edittext.ZPEditText$b p1)
    {
        this.r0 = p1;
        return;
    }

    public void setCurrentBottomLines(float p1)
    {
        this.D = p1;
        this.j();
        return;
    }

    public void setError(CharSequence p1)
    {
        String v1_1;
        if (p1 != null) {
            v1_1 = p1.toString();
        } else {
            v1_1 = 0;
        }
        this.E = v1_1;
        return;
    }

    public void setErrorColor(int p1)
    {
        this.v = p1;
        this.postInvalidate();
        return;
    }

    public void setEventIdInnerFocus(int p1)
    {
        this.D0 = p1;
        return;
    }

    public void setEventIdTouchClear(int p1)
    {
        this.C0 = p1;
        return;
    }

    public void setFloatingLabel(int p1)
    {
        this.setFloatingLabelInternal(p1);
        this.j();
        return;
    }

    public void setFloatingLabelAlwaysShown(boolean p1)
    {
        this.A = p1;
        this.invalidate();
        return;
    }

    public void setFloatingLabelAnimating(boolean p1)
    {
        this.P = p1;
        return;
    }

    public void setFloatingLabelFraction(float p1)
    {
        this.F = p1;
        this.invalidate();
        return;
    }

    public void setFloatingLabelPadding(int p1)
    {
        this.l = p1;
        this.postInvalidate();
        return;
    }

    public void setFloatingLabelText(int p2)
    {
        this.setFloatingLabelText(this.getContext().getString(p2));
        return;
    }

    public void setFloatingLabelText(CharSequence p1)
    {
        if (p1 == null) {
            p1 = this.getHint();
        }
        this.I = p1;
        this.postInvalidate();
        return;
    }

    public void setFloatingLabelTextColor(int p1)
    {
        this.j = p1;
        this.postInvalidate();
        return;
    }

    public void setFloatingLabelTextSize(int p1)
    {
        this.i = p1;
        this.j();
        return;
    }

    public void setFocusFraction(float p1)
    {
        this.H = p1;
        this.invalidate();
        return;
    }

    public void setFormatText(GZ.k p1)
    {
        this.A0 = p1;
        this.g(this.getEditableText());
        return;
    }

    public void setHideUnderline(boolean p1)
    {
        this.J = p1;
        this.j();
        this.postInvalidate();
        return;
    }

    public void setHintText(CharSequence p1)
    {
        this.setHint(p1);
        this.setFloatingLabelText(p1);
        return;
    }

    public void setLengthChecker(GZ.s p1)
    {
        this.q0 = p1;
        return;
    }

    public void setMaxCharacters(int p1)
    {
        this.y = p1;
        this.i();
        this.j();
        this.postInvalidate();
        return;
    }

    public void setMinCharacters(int p1)
    {
        this.x = p1;
        this.i();
        this.j();
        this.postInvalidate();
        return;
    }

    public void setOnFocusChangeListener(android.view.View$OnFocusChangeListener p2)
    {
        if (this.o0 != null) {
            this.p0 = p2;
            return;
        } else {
            super.setOnFocusChangeListener(p2);
            return;
        }
    }

    public void setOnSearchListener(vn.zalopay.ui.edittext.ZPEditText$c p1)
    {
        this.s0 = p1;
        return;
    }

    public void setOnTextActionListener(GZ.m p1)
    {
        this.z0 = p1;
        return;
    }

    public void setPaddingLeftLine(int p1)
    {
        this.t0 = p1;
        this.invalidate();
        return;
    }

    public void setPrimaryColor(int p1)
    {
        this.u = p1;
        this.postInvalidate();
        return;
    }

    public void setShowClearButton(boolean p1)
    {
        this.U = p1;
        this.f();
        return;
    }

    public void setShowSearchButton(boolean p2)
    {
        if (this.V != p2) {
            this.V = p2;
            this.e();
            this.postInvalidate();
            return;
        } else {
            return;
        }
    }

    public void setSingleLineEllipsis(boolean p1)
    {
        this.z = p1;
        this.i();
        this.j();
        this.postInvalidate();
        return;
    }

    public void setUnderlineColor(int p1)
    {
        this.L = p1;
        this.postInvalidate();
        return;
    }

    public void setValidateOnFocusLost(boolean p1)
    {
        this.T = p1;
        return;
    }

    public void setZPHintTextColor(int p1)
    {
        this.h0 = android.content.res.ColorStateList.valueOf(p1);
        this.n();
        return;
    }

    public void setZPHintTextColor(android.content.res.ColorStateList p1)
    {
        this.h0 = p1;
        this.n();
        return;
    }

    public void setZPTextColor(int p1)
    {
        this.g0 = android.content.res.ColorStateList.valueOf(p1);
        this.o();
        return;
    }

    public void setZpTextColor(android.content.res.ColorStateList p1)
    {
        this.g0 = p1;
        this.o();
        return;
    }
}

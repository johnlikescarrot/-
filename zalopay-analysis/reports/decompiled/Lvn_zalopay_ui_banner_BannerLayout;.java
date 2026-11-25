package vn.zalopay.ui.banner;
public final class BannerLayout extends android.widget.FrameLayout {
    public uZ.a a;
    public final androidx.viewpager2.widget.ViewPager2 b;
    public wZ.a c;
    public vZ.a d;
    public int e;
    public final android.os.Handler f;
    public long g;
    public final float h;

    public BannerLayout(android.content.Context p5, android.util.AttributeSet p6)
    {
        super(p5, p6);
        int[] v0_2 = new androidx.viewpager2.widget.ViewPager2(super.getContext());
        super.b = v0_2;
        super.e = 1;
        super.f = new android.os.Handler(android.os.Looper.getMainLooper());
        super.h = 1081014926;
        v0_2.setLayoutParams(new android.widget.FrameLayout$LayoutParams(-1, -1));
        v0_2.setOffscreenPageLimit(3);
        v0_2.b(new P3.d(2, super));
        super.addView(v0_2);
        android.content.res.TypedArray v5_1 = p5.obtainStyledAttributes(p6, sZ.h.b, 0, 0);
        try {
            float v1_3 = v5_1.getFloat(0, 1081014926);
        } catch (RuntimeException) {
        } catch (Throwable v6_1) {
            v5_1.recycle();
            throw v6_1;
        }
        v5_1.recycle();
        super.h = v1_3;
        return;
    }

    public final void a()
    {
        long v0 = this.g;
        if (v0 > 0) {
            this.f.postDelayed(new tZ.a(0, this), v0);
            return;
        } else {
            return;
        }
    }

    public final void b()
    {
        this.f.removeCallbacksAndMessages(0);
        return;
    }

    public final boolean dispatchTouchEvent(android.view.MotionEvent p3)
    {
        int v0 = p3.getActionMasked();
        if (v0 == 0) {
            this.b();
        } else {
            if ((v0 == 1) || (v0 == 3)) {
                this.a();
            }
        }
        return super.dispatchTouchEvent(p3);
    }

    public final uZ.a getAdapter$widget_release()
    {
        return this.a;
    }

    public final int getCurrentPosition$widget_release()
    {
        return this.e;
    }

    public final vZ.a getIndicator$widget_release()
    {
        return this.d;
    }

    public final int getItemCount()
    {
        int v0_0 = this.a;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0.e.size();
        }
    }

    public final wZ.a getOnPageChangedListener$widget_release()
    {
        return this.c;
    }

    public final androidx.viewpager2.widget.ViewPager2 getPager$widget_release()
    {
        return this.b;
    }

    public final int getRealCount()
    {
        int v0_0 = this.a;
        if (v0_0 == 0) {
            return 0;
        } else {
            int v0_3 = v0_0.e.size();
            if (v0_3 > 1) {
                return (v0_3 - 2);
            } else {
                return v0_3;
            }
        }
    }

    public final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        this.b();
        this.a();
        return;
    }

    public final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        this.b();
        return;
    }

    public final void onMeasure(int p4, int p5)
    {
        float v0_0 = android.view.View$MeasureSpec.getMode(p5);
        int v4_2 = android.view.View$MeasureSpec.getSize(p4);
        int v5_1 = android.view.View$MeasureSpec.getSize(p5);
        if (v0_0 == -2147483648) {
            v5_1 = Math.min(v5_1, ((int) (((float) v4_2) / this.h)));
        } else {
            if (v0_0 != 1073741824) {
                v5_1 = ((int) (((float) v4_2) / this.h));
            }
        }
        this.setMeasuredDimension(v4_2, v5_1);
        this.measureChildren(android.view.View$MeasureSpec.makeMeasureSpec(v4_2, 1073741824), android.view.View$MeasureSpec.makeMeasureSpec(v5_1, 1073741824));
        return;
    }

    public final void setAdapter(uZ.a p4)
    {
        this.b();
        this.a = p4;
        int v0_2 = this.b;
        v0_2.setAdapter(p4);
        int v1 = 0;
        v0_2.d(this.e, 0);
        vZ.a v4_2 = this.d;
        if (v4_2 != null) {
            int v0_0 = v0_2.getCurrentItem();
            int v2_0 = this.getRealCount();
            if (v0_0 != 0) {
                if (v0_0 != (v2_0 + 1)) {
                    v1 = (v0_0 - 1);
                }
            } else {
                v1 = (v2_0 - 1);
            }
            v4_2.a = this.getRealCount();
            v4_2.b = v1;
            v4_2.requestLayout();
        }
        this.a();
        return;
    }

    public final void setAdapter$widget_release(uZ.a p1)
    {
        this.a = p1;
        return;
    }

    public final void setAutoScroll(long p1)
    {
        this.b();
        this.g = p1;
        this.a();
        return;
    }

    public final void setCurrentPosition$widget_release(int p1)
    {
        this.e = p1;
        return;
    }

    public final void setData(java.util.List p25)
    {
        int v2_5 = this.a;
        if (v2_5 != 0) {
            this.b();
            Ly.b v3_8 = v2_5.e;
            androidx.recyclerview.widget.s v4_23 = new java.util.ArrayList();
            v4_23.addAll(((java.util.Collection) p25));
            int v6_2 = 1;
            int v7_4 = 0;
            if (p25.size() > 1) {
                v4_23.add(0, p25.get((p25.size() - 1)));
                v4_23.add(p25.get(0));
            }
            v2_5.e = v4_23;
            vZ.a v1_10 = new Ly.b(v3_8, v4_23);
            Ly.b v3_2 = v3_8.size();
            androidx.recyclerview.widget.s v4_4 = v4_23.size();
            int v5_4 = new java.util.ArrayList();
            int v8_2 = new java.util.ArrayList();
            int[] v9_1 = new androidx.recyclerview.widget.r();
            v9_1.a = 0;
            v9_1.b = v3_2;
            v9_1.c = 0;
            v9_1.d = v4_4;
            v8_2.add(v9_1);
            androidx.recyclerview.widget.s v4_13 = 2;
            Ly.b v3_7 = (((((v3_2 + v4_4) + 1) / 2) * 2) + 1);
            int[] v9_2 = new int[v3_7];
            int v10_14 = (v3_7 / 2);
            Ly.b v3_9 = new int[v3_7];
            int v11_11 = new java.util.ArrayList();
            while (!v8_2.isEmpty()) {
                int v17_3;
                androidx.recyclerview.widget.q v19_1;
                int v14_12;
                int v12_12 = ((androidx.recyclerview.widget.r) v8_2.remove((v8_2.size() - v6_2)));
                if ((v12_12.b() >= v6_2) && (v12_12.a() >= v6_2)) {
                    int v15_17 = (((v12_12.a() + v12_12.b()) + v6_2) / v4_13);
                    androidx.recyclerview.widget.q v16_4 = (v6_2 + v10_14);
                    v9_2[v16_4] = v12_12.a;
                    v3_9[v16_4] = v12_12.b;
                    int v13_18 = v7_4;
                    while (v13_18 < v15_17) {
                        int v14_6;
                        if ((Math.abs((v12_12.b() - v12_12.a())) % 2) != v6_2) {
                            v14_6 = v7_4;
                        } else {
                            v14_6 = v6_2;
                        }
                        androidx.recyclerview.widget.q v16_9 = (v12_12.b() - v12_12.a());
                        v17_3 = v4_13;
                        androidx.recyclerview.widget.s v4_12 = (- v13_18);
                        int v6_4 = v4_12;
                        while (v6_4 <= v13_18) {
                            int v6_8;
                            int v7_17;
                            int v20_0;
                            if (v6_4 == v4_12) {
                                v20_0 = v6_4;
                                v6_8 = v9_2[((v20_0 + 1) + v10_14)];
                                v7_17 = v6_8;
                            } else {
                                if (v6_4 == v13_18) {
                                    v20_0 = v6_4;
                                } else {
                                    v20_0 = v6_4;
                                    if (v9_2[((v6_4 + 1) + v10_14)] > v9_2[((v6_4 - 1) + v10_14)]) {
                                    }
                                }
                                v6_8 = v9_2[((v20_0 - 1) + v10_14)];
                                v7_17 = (v6_8 + 1);
                            }
                            int v7_18;
                            int v21_2;
                            v19_1 = v10_14;
                            int v10_12 = (((v7_17 - v12_12.a) + v12_12.c) - v20_0);
                            if ((v13_18 != 0) && (v7_17 == v6_8)) {
                                v21_2 = v7_17;
                                v7_18 = (v10_12 - 1);
                            } else {
                                v21_2 = v7_17;
                                v7_18 = v10_12;
                            }
                            int v14_7 = v10_12;
                            int v10_13 = v21_2;
                            int v21_3 = v14_6;
                            int v22_0 = v15_17;
                            while ((v10_13 < v12_12.b) && ((v14_7 < v12_12.d) && (v1_10.d(v10_13, v14_7)))) {
                                v10_13++;
                                v14_7++;
                            }
                            v9_2[(v20_0 + v19_1)] = v10_13;
                            if (v21_3 != 0) {
                                int v15_22 = (v16_9 - v20_0);
                                if ((v15_22 >= (v4_12 + 1)) && ((v15_22 <= (v13_18 - 1)) && (v3_9[(v15_22 + v19_1)] <= v10_13))) {
                                    int v0_15 = new androidx.recyclerview.widget.s();
                                    v0_15.a = v6_8;
                                    v0_15.b = v7_18;
                                    v0_15.c = v10_13;
                                    v0_15.e = v14_7;
                                    v0_15.d = 0;
                                    if (v0_15 == 0) {
                                        int v0_23;
                                        if (((v12_12.b() - v12_12.a()) % 2) != 0) {
                                            v0_23 = 0;
                                        } else {
                                            v0_23 = 1;
                                        }
                                        int v6_14 = (v12_12.b() - v12_12.a());
                                        int v7_20 = v4_12;
                                        while (v7_20 <= v13_18) {
                                            if ((v7_20 != v4_12) && ((v7_20 == v13_18) || (v3_9[((v7_20 + 1) + v19_1)] >= v3_9[((v7_20 - 1) + v19_1)]))) {
                                                int v10_20 = v3_9[((v7_20 - 1) + v19_1)];
                                                int v14_11 = (v10_20 - 1);
                                            } else {
                                                v10_20 = v3_9[((v7_20 + 1) + v19_1)];
                                                v14_11 = v10_20;
                                            }
                                            int v0_27;
                                            androidx.recyclerview.widget.q v16_10 = v0_23;
                                            int v15_25 = (v12_12.d - ((v12_12.b - v14_11) - v7_20));
                                            if ((v13_18 != 0) && (v14_11 == v10_20)) {
                                                v0_27 = (v15_25 + 1);
                                            } else {
                                                v0_27 = v15_25;
                                            }
                                            int v20_1 = v6_14;
                                            while ((v14_11 > v12_12.a) && (v15_25 > v12_12.c)) {
                                                int v21_4 = v7_20;
                                                if (v1_10.d((v14_11 - 1), (v15_25 - 1))) {
                                                    v14_11--;
                                                    v15_25--;
                                                    v7_20 = v21_4;
                                                }
                                                v3_9[(v21_4 + v19_1)] = v14_11;
                                                if (v16_10 != null) {
                                                    int v6_19 = (v20_1 - v21_4);
                                                    if ((v6_19 >= v4_12) && ((v6_19 <= v13_18) && (v9_2[(v6_19 + v19_1)] >= v14_11))) {
                                                        androidx.recyclerview.widget.s v4_14 = new androidx.recyclerview.widget.s();
                                                        v4_14.a = v14_11;
                                                        v4_14.b = v15_25;
                                                        v4_14.c = v10_20;
                                                        v4_14.e = v0_27;
                                                        v4_14.d = 1;
                                                        if (v4_14 == null) {
                                                            v13_18++;
                                                            v4_13 = v17_3;
                                                            v10_14 = v19_1;
                                                            v15_17 = v22_0;
                                                            v6_2 = 1;
                                                            v7_4 = 0;
                                                        } else {
                                                            v14_12 = v4_14;
                                                            if (v14_12 == 0) {
                                                                v11_11.add(v12_12);
                                                            } else {
                                                                if (v14_12.a() > 0) {
                                                                    int v0_35;
                                                                    androidx.recyclerview.widget.s v4_16 = v14_12.b;
                                                                    int v0_32 = (v14_12.e - v4_16);
                                                                    int v7_23 = v14_12.a;
                                                                    int v6_23 = (v14_12.c - v7_23);
                                                                    if (v0_32 == v6_23) {
                                                                        v0_35 = new androidx.recyclerview.widget.p(v7_23, v4_16, v6_23);
                                                                    } else {
                                                                        if (!v14_12.d) {
                                                                            if (v0_32 <= v6_23) {
                                                                                v0_35 = new androidx.recyclerview.widget.p((v7_23 + 1), v4_16, v14_12.a());
                                                                            } else {
                                                                                v0_35 = new androidx.recyclerview.widget.p(v7_23, (v4_16 + 1), v14_12.a());
                                                                            }
                                                                        } else {
                                                                            v0_35 = new androidx.recyclerview.widget.p(v7_23, v4_16, v14_12.a());
                                                                        }
                                                                    }
                                                                    v5_4.add(v0_35);
                                                                }
                                                                int v0_43;
                                                                if (!v11_11.isEmpty()) {
                                                                    v0_43 = ((androidx.recyclerview.widget.r) v11_11.remove((v11_11.size() - 1)));
                                                                } else {
                                                                    v0_43 = new androidx.recyclerview.widget.r();
                                                                }
                                                                v0_43.a = v12_12.a;
                                                                v0_43.c = v12_12.c;
                                                                v0_43.b = v14_12.a;
                                                                v0_43.d = v14_12.b;
                                                                v8_2.add(v0_43);
                                                                v12_12.b = v12_12.b;
                                                                v12_12.d = v12_12.d;
                                                                v12_12.a = v14_12.c;
                                                                v12_12.c = v14_12.e;
                                                                v8_2.add(v12_12);
                                                            }
                                                            v4_13 = v17_3;
                                                            v10_14 = v19_1;
                                                            v6_2 = 1;
                                                            v7_4 = 0;
                                                        }
                                                        v6_4 = (v20_0 + 2);
                                                        v10_14 = v19_1;
                                                        v14_6 = v21_3;
                                                        v15_17 = v22_0;
                                                    }
                                                }
                                                v7_20 = (v21_4 + 2);
                                                v0_23 = v16_10;
                                                v6_14 = v20_1;
                                            }
                                            v21_4 = v7_20;
                                        }
                                        v4_14 = 0;
                                    } else {
                                        v14_12 = v0_15;
                                    }
                                }
                            }
                        }
                        v19_1 = v10_14;
                        v22_0 = v15_17;
                        v0_15 = 0;
                    }
                    v17_3 = v4_13;
                    v19_1 = v10_14;
                    v14_12 = 0;
                }
            }
            int v12_16;
            int v17_0 = v4_13;
            java.util.Collections.sort(v5_4, androidx.recyclerview.widget.t.a);
            int v0_3 = new androidx.media3.extractor.ogg.f();
            v0_3.d = v3_9;
            androidx.recyclerview.widget.s v4_11 = 0;
            java.util.Arrays.fill(v9_2, 0);
            java.util.Arrays.fill(v3_9, 0);
            v0_3.e = v1_10;
            int v6_27 = v1_10.b;
            int v7_25 = v6_27.size();
            v0_3.a = v7_25;
            int v8_3 = v1_10.c;
            int v10_24 = v8_3.size();
            v0_3.b = v10_24;
            v0_3.c = 1;
            if (!v5_4.isEmpty()) {
                v12_16 = ((androidx.recyclerview.widget.p) v5_4.get(0));
            } else {
                v12_16 = 0;
            }
            if ((v12_16 == 0) || ((v12_16.a != 0) || (v12_16.b != 0))) {
                v5_4.add(0, new androidx.recyclerview.widget.p(0, 0, 0));
            }
            v5_4.add(new androidx.recyclerview.widget.p(v7_25, v10_24, 0));
            int v7_26 = v5_4.iterator();
            while (v7_26.hasNext()) {
                int v10_7 = ((androidx.recyclerview.widget.p) v7_26.next());
                int v12_8 = v4_11;
                while (v12_8 < v10_7.c) {
                    androidx.recyclerview.widget.s v4_8;
                    int v13_11 = (v10_7.a + v12_8);
                    int v14_4 = (v10_7.b + v12_8);
                    int v15_10 = ((Ny.b) v6_27.get(v13_11));
                    androidx.recyclerview.widget.s v4_6 = ((Ny.b) v8_3.get(v14_4));
                    if (v4_6 != null) {
                        v4_8 = kotlin.jvm.internal.Intrinsics.areEqual(v15_10.a, v4_6.a);
                    } else {
                        v15_10.getClass();
                        v4_8 = 0;
                    }
                    androidx.recyclerview.widget.s v4_9;
                    if (v4_8 == null) {
                        v4_9 = 2;
                    } else {
                        v4_9 = 1;
                    }
                    v9_2[v13_11] = ((v14_4 << 4) | v4_9);
                    v3_9[v14_4] = (v4_9 | (v13_11 << 4));
                    v12_8++;
                    v4_11 = 0;
                }
            }
            if (v0_3.c) {
                androidx.recyclerview.widget.s v4_1 = v5_4.iterator();
                int v7_0 = 0;
                while (v4_1.hasNext()) {
                    int v10_5 = ((androidx.recyclerview.widget.p) v4_1.next());
                    while(true) {
                        int v11_1 = v10_5.a;
                        if (v7_0 >= v11_1) {
                            break;
                        }
                        if (v9_2[v7_0] == 0) {
                            int v11_3 = v5_4.size();
                            int v12_1 = 0;
                            int v13_7 = 0;
                            while (v12_1 < v11_3) {
                                int v14_2 = ((androidx.recyclerview.widget.p) v5_4.get(v12_1));
                                while(true) {
                                    int v15_6 = v14_2.b;
                                    if (v13_7 >= v15_6) {
                                        v13_7 = (v14_2.c + v15_6);
                                        v12_1++;
                                    } else {
                                        if ((v3_9[v13_7] == null) && (v1_10.d(v7_0, v13_7))) {
                                            break;
                                        }
                                        v13_7++;
                                    }
                                }
                                int v11_7;
                                int v11_5 = ((Ny.b) v6_27.get(v7_0));
                                int v12_3 = ((Ny.b) v8_3.get(v13_7));
                                if (v12_3 != 0) {
                                    v11_7 = kotlin.jvm.internal.Intrinsics.areEqual(v11_5.a, v12_3.a);
                                } else {
                                    v11_5.getClass();
                                    v11_7 = 0;
                                }
                                int v11_8;
                                if (v11_7 == 0) {
                                    v11_8 = 4;
                                } else {
                                    v11_8 = 8;
                                }
                                v9_2[v7_0] = ((v13_7 << 4) | v11_8);
                                v3_9[v13_7] = (v11_8 | (v7_0 << 4));
                                break;
                            }
                        }
                        v7_0++;
                    }
                    v7_0 = (v10_5.c + v11_1);
                }
            }
            int v2_0 = v0_3.a;
            Ly.b v3_1 = ((Ly.b) v0_3.e);
            androidx.recyclerview.widget.s v4_3 = new androidx.recyclerview.widget.f(new androidx.recyclerview.widget.d(v2_5));
            vZ.a v1_4 = new java.util.ArrayDeque();
            int v8_0 = (v5_4.size() - 1);
            int v7_3 = v0_3.b;
            int v6_1 = v2_0;
            while (v8_0 >= 0) {
                int v10_2 = ((androidx.recyclerview.widget.p) v5_4.get(v8_0));
                int v11_0 = v10_2.a;
                int v12_0 = v10_2.c;
                int v13_0 = (v11_0 + v12_0);
                int v10_3 = v10_2.b;
                int v14_0 = (v10_3 + v12_0);
                while (v6_1 > v13_0) {
                    androidx.recyclerview.widget.q v16_2;
                    androidx.recyclerview.widget.q v19_0;
                    v6_1--;
                    int v15_4 = v9_2[v6_1];
                    if ((v15_4 & 12) == 0) {
                        androidx.recyclerview.widget.q v25_3 = v2_0;
                        v16_2 = v5_4;
                        v19_0 = v7_3;
                        if (v4_3.b != 2) {
                            v4_3.a();
                            v4_3.c = v6_1;
                            v4_3.d = 1;
                            v4_3.b = 2;
                        } else {
                            int v2_7 = v4_3.c;
                            if ((v2_7 < v6_1) || (v2_7 > (v6_1 + 1))) {
                            } else {
                                v4_3.d = (v4_3.d + 1);
                                v4_3.c = v6_1;
                            }
                        }
                        v2_0 = (v25_3 - 1);
                    } else {
                        androidx.recyclerview.widget.q v25_4 = v2_0;
                        int v2_10 = (v15_4 >> 4);
                        v16_2 = v5_4;
                        v19_0 = v7_3;
                        int v7_12 = androidx.media3.extractor.ogg.f.d(v1_4, v2_10, 0);
                        if (v7_12 == 0) {
                            v1_4.add(new androidx.recyclerview.widget.q(v6_1, ((v25_4 - v6_1) - 1), 1));
                        } else {
                            int v5_18 = ((v25_4 - v7_12.b) - 1);
                            v4_3.c(v6_1, v5_18);
                            if ((v15_4 & 4) != 0) {
                                v4_3.b(v5_18, 1, v3_1.e(v6_1, v2_10));
                            }
                        }
                        v2_0 = v25_4;
                    }
                    v5_4 = v16_2;
                    v7_3 = v19_0;
                }
                androidx.recyclerview.widget.q v16_0 = v5_4;
                while (v7_3 > v14_0) {
                    androidx.recyclerview.widget.q v25_2;
                    v7_3--;
                    int v5_8 = ((int[]) v0_3.d)[v7_3];
                    if ((v5_8 & 12) == 0) {
                        v25_2 = v0_3;
                        if (v4_3.b != 1) {
                            v4_3.a();
                            v4_3.c = v6_1;
                            v4_3.d = 1;
                            v4_3.b = 1;
                        } else {
                            int v0_5 = v4_3.c;
                            if (v6_1 < v0_5) {
                            } else {
                                int v13_3 = v4_3.d;
                                if (v6_1 > (v0_5 + v13_3)) {
                                } else {
                                    v4_3.d = (v13_3 + 1);
                                    v4_3.c = Math.min(v6_1, v0_5);
                                }
                            }
                        }
                        v2_0++;
                    } else {
                        int v13_5 = (v5_8 >> 4);
                        v25_2 = v0_3;
                        int v0_7 = androidx.media3.extractor.ogg.f.d(v1_4, v13_5, 1);
                        if (v0_7 != 0) {
                            v4_3.c(((v2_0 - v0_7.b) - 1), v6_1);
                            if ((v5_8 & 4) != 0) {
                                v4_3.b(v6_1, 1, v3_1.e(v13_5, v7_3));
                            }
                        } else {
                            v1_4.add(new androidx.recyclerview.widget.q(v7_3, (v2_0 - v6_1), 0));
                        }
                    }
                    v0_3 = v25_2;
                }
                androidx.recyclerview.widget.q v25_1 = v0_3;
                int v5_2 = v10_3;
                int v0_2 = v11_0;
                int v6_3 = 0;
                while (v6_3 < v12_0) {
                    int v13_1 = v17_0;
                    if ((v9_2[v0_2] & 15) == v13_1) {
                        v4_3.b(v0_2, 1, v3_1.e(v0_2, v5_2));
                    }
                    v0_2++;
                    v5_2++;
                    v6_3++;
                    v17_0 = v13_1;
                }
                v8_0--;
                v0_3 = v25_1;
                v7_3 = v10_3;
                v6_1 = v11_0;
                v5_4 = v16_0;
            }
            int v7_5;
            v4_3.a();
            int v2_1 = this.b;
            v2_1.d(this.e, 0);
            vZ.a v1_6 = v2_1.getCurrentItem();
            int v2_2 = this.getRealCount();
            if (v1_6 != null) {
                if (v1_6 != (v2_2 + 1)) {
                    v7_5 = (v1_6 - 1);
                } else {
                    v7_5 = 0;
                }
            } else {
                v7_5 = (v2_2 - 1);
            }
            vZ.a v1_7 = this.d;
            if (v1_7 != null) {
                v1_7.a = this.getRealCount();
                v1_7.b = v7_5;
                v1_7.requestLayout();
            }
            this.a();
        }
        return;
    }

    public final void setIndicator(vZ.a p3)
    {
        this.d = p3;
        if ((this.a != null) && (p3 != null)) {
            int v1_1;
            int v0_3 = this.b.getCurrentItem();
            int v1_2 = this.getRealCount();
            if (v0_3 != 0) {
                if (v0_3 != (v1_2 + 1)) {
                    v1_1 = (v0_3 - 1);
                } else {
                    v1_1 = 0;
                }
            } else {
                v1_1 = (v1_2 - 1);
            }
            p3.a = this.getRealCount();
            p3.b = v1_1;
            p3.requestLayout();
        }
        return;
    }

    public final void setIndicator$widget_release(vZ.a p1)
    {
        this.d = p1;
        return;
    }

    public final void setOnPageChangedListener(wZ.a p1)
    {
        this.c = p1;
        return;
    }

    public final void setOnPageChangedListener$widget_release(wZ.a p1)
    {
        this.c = p1;
        return;
    }
}

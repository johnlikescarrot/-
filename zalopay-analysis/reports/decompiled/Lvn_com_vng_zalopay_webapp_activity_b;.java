package vn.com.vng.zalopay.webapp.activity;
public final synthetic class b implements java.lang.Runnable {
    public final synthetic vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity a;
    public final synthetic java.util.List b;
    public final synthetic kF.b c;

    public synthetic b(vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity p1, java.util.List p2, kF.b p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final void run()
    {
        android.widget.LinearLayout v0_0 = this.a;
        java.util.Iterator v1_0 = v0_0.viewShare;
        if (v1_0 != null) {
            v1_0.setVisibility(8);
        }
        java.util.Iterator v1_2 = v0_0.llActionViews;
        if (v1_2 != null) {
            v1_2.removeAllViews();
            java.util.Iterator v1_3 = this.b;
            if (!com.google.android.gms.internal.mlkit_vision_barcode.p0.b(v1_3)) {
                v0_0.llActionViews.setVisibility(0);
                java.util.Iterator v1_1 = v1_3.iterator();
                while (v1_1.hasNext()) {
                    android.widget.LinearLayout v2_4 = ((vn.com.vng.zalopay.domain.webapp.model.WebInternalActionModel) v1_1.next());
                    if (v2_4 != null) {
                        kF.b v5 = this.c;
                        if (android.text.TextUtils.isEmpty(v2_4.iconLink)) {
                            if (!android.text.TextUtils.isEmpty(v2_4.iconName)) {
                                UQ.c v4_5 = new vn.zalopay.ui.IconFont(v0_0, 0);
                                v4_5.setIcon(v2_4.iconName);
                                v4_5.setIconColor(v2_4.iconColor);
                                v4_5.setTextSize(1103626240);
                                android.widget.LinearLayout$LayoutParams v7_5 = new android.widget.LinearLayout$LayoutParams(-2, -2);
                                v7_5.rightMargin = ((int) eQ.a.b(v0_0, 10));
                                v4_5.setLayoutParams(v7_5);
                                v4_5.setOnClickListener(new UQ.c(v5, v2_4, 1));
                                v0_0.llActionViews.addView(v4_5);
                            }
                        } else {
                            UQ.c v4_9;
                            UQ.c v4_7 = new android.util.DisplayMetrics();
                            v0_0.getWindowManager().getDefaultDisplay().getMetrics(v4_7);
                            switch (v4_7.densityDpi) {
                                case 120:
                                    v4_9 = 15;
                                    break;
                                case 160:
                                    v4_9 = 20;
                                    break;
                                case 260:
                                case 280:
                                case 300:
                                case 320:
                                    v4_9 = 55;
                                    break;
                                case 340:
                                case 360:
                                case 400:
                                case 420:
                                case 480:
                                    v4_9 = 75;
                                    break;
                                case 560:
                                case 640:
                                    v4_9 = 90;
                                    break;
                                default:
                                    v4_9 = 35;
                            }
                            com.facebook.drawee.view.SimpleDraweeView v8_4 = new com.facebook.drawee.view.SimpleDraweeView(v0_0);
                            v8_4.c(v0_0, 0);
                            v8_4.d(v0_0, 0);
                            v8_4.setImageURI(android.net.Uri.parse(v2_4.iconLink));
                            v8_4.clearAnimation();
                            android.widget.LinearLayout$LayoutParams v7_10 = new android.widget.LinearLayout$LayoutParams(v4_9, v4_9);
                            v7_10.rightMargin = ((int) eQ.a.b(v0_0, 10));
                            v8_4.setLayoutParams(v7_10);
                            v8_4.setOnClickListener(new UQ.c(v5, v2_4, 0));
                            v0_0.llActionViews.addView(v8_4);
                        }
                    }
                }
            } else {
                v0_0.llActionViews.setVisibility(8);
                return;
            }
        }
        return;
    }
}

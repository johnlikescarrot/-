package vn.zalopay.ui.topsnackbar;
public final synthetic class g implements android.os.Handler$Callback {

    public final boolean handleMessage(android.os.Message p8)
    {
        vn.zalopay.ui.topsnackbar.p v0_0 = p8.what;
        if (v0_0 == null) {
            int v8_2 = ((vn.zalopay.ui.topsnackbar.p) p8.obj);
            vn.zalopay.ui.topsnackbar.p v0_1 = v8_2.b;
            if (v0_1.getParent() == null) {
                int v2_0 = v0_1.getLayoutParams();
                if ((v2_0 instanceof V1.c)) {
                    vn.zalopay.ui.topsnackbar.l v3_2 = new vn.zalopay.ui.topsnackbar.m(v8_2);
                    v3_2.e = Math.min(Math.max(0, 1036831949), 1065353216);
                    v3_2.f = Math.min(Math.max(0, 1058642330), 1065353216);
                    v3_2.d = 2;
                    v3_2.b = new vn.zalopay.ui.topsnackbar.k(v8_2);
                    ((V1.c) v2_0).b(v3_2);
                }
                v8_2.a.addView(v0_1);
            }
            v0_1.setOnAttachStateChangeListener(new vn.zalopay.ui.topsnackbar.h(v8_2));
            if (!v0_1.isLaidOut()) {
                v0_1.setOnLayoutChangeListener(new vn.zalopay.ui.topsnackbar.h(v8_2));
                return 1;
            } else {
                v8_2.a();
                return 1;
            }
        } else {
            int v2_8 = 0;
            if (v0_0 == 1) {
                vn.zalopay.ui.topsnackbar.p v0_3 = ((vn.zalopay.ui.topsnackbar.p) p8.obj);
                int v8_3 = p8.arg1;
                vn.zalopay.ui.topsnackbar.l v3_3 = v0_3.b;
                if (v3_3.getVisibility() == 0) {
                    android.view.ViewPropertyAnimator v4_10 = v3_3.getLayoutParams();
                    if ((v4_10 instanceof V1.c)) {
                        android.view.ViewPropertyAnimator v4_12 = ((V1.c) v4_10).a;
                        if ((v4_12 instanceof com.google.android.material.behavior.SwipeDismissBehavior)) {
                            android.view.ViewPropertyAnimator v4_15;
                            android.view.ViewPropertyAnimator v4_14 = ((com.google.android.material.behavior.SwipeDismissBehavior) v4_12).a;
                            if (v4_14 == null) {
                                v4_15 = 0;
                            } else {
                                v4_15 = v4_14.a;
                            }
                            if (v4_15 != null) {
                                v2_8 = 1;
                            }
                        }
                    }
                    if (v2_8 == 0) {
                        int v2_10 = androidx.core.view.x0.a(v3_3);
                        v2_10.f(((float) (- v3_3.getHeight())));
                        vn.zalopay.ui.topsnackbar.l v3_8 = new P2.a(1);
                        android.view.ViewPropertyAnimator v4_18 = ((android.view.View) v2_10.a.get());
                        if (v4_18 != null) {
                            v4_18.animate().setInterpolator(v3_8);
                        }
                        v2_10.c(250);
                        v2_10.d(new vn.zalopay.ui.topsnackbar.l(v0_3, v8_3));
                        v2_10.e();
                        return 1;
                    }
                }
                v0_3.c();
                return 1;
            } else {
                return 0;
            }
        }
    }
}

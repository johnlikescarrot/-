package vn.com.vng.zalopay.livenessdetection.presentation.fragment;
public final class CameraXFragment extends androidx.fragment.app.Fragment implements ID.a {
    public Jv.b r;
    public final ng.S s;
    public final ng.S t;
    public final ng.w u;
    public final ng.S v;
    public final ng.S w;
    public final String x;
    public final androidx.activity.result.d y;
    public final AZ.i z;

    public CameraXFragment()
    {
        super(2131558901);
        super.s = ng.x.b(new DD.a(super, 0));
        super.t = ng.x.b(new AB.a(12));
        AZ.i v0_4 = new AB.a(13);
        DD.a v1_3 = ng.x.a(ng.y.c, new A0.c(9, new A0.c(8, super)));
        super.u = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(super, kotlin.jvm.internal.M.a.b(HD.h), new CS.x(v1_3, 4), new CS.x(v1_3, 5), v0_4);
        super.v = ng.x.b(new DD.a(super, 1));
        super.w = ng.x.b(new DD.a(super, 2));
        super.x = "android.permission.CAMERA";
        super.y = super.registerForActivityResult(new androidx.fragment.app.f0(5), new BV.k1(1, super));
        super.z = new AZ.i(new DD.a(super, 3), new DD.a(super, 4));
        return;
    }

    public final TC.i m1()
    {
        return ((TC.i) this.w.getValue());
    }

    public final void n1()
    {
        androidx.lifecycle.T v0_0 = this.z;
        if (v0_0.isVisible()) {
            v0_0.m1();
        }
        this.m1().i(((androidx.camera.view.PreviewView) this.r.h));
        this.m1().c(fD.a.b);
        ((vn.com.vng.zalopay.livenessdetection.presentation.widgets.OverlayView) this.r.g).setCountdownListener(this);
        ((android.widget.Button) this.r.e).setOnClickListener(new Ae.p(6, this));
        androidx.lifecycle.T v0_23 = ((vn.com.vng.zalopay.livenessdetection.presentation.widgets.OverlayView) this.r.g);
        androidx.lifecycle.F v1_18 = new android.util.TypedValue();
        v0_23.getResources().getValue(2131165819, v1_18, 1);
        v1_18.getFloat();
        androidx.lifecycle.F v1_20 = new android.util.TypedValue();
        v0_23.getResources().getValue(2131165823, v1_20, 1);
        v1_20.getFloat();
        androidx.lifecycle.F v1_22 = new android.graphics.Paint();
        v1_22.setColor(v0_23.getContext().getColor(2131101034));
        AA.d v2_13 = android.graphics.Paint$Style.FILL;
        v1_22.setStyle(v2_13);
        v0_23.b = v1_22;
        androidx.lifecycle.F v1_24 = new android.graphics.Paint();
        v1_24.setColor(-256);
        v1_24.setStrokeWidth(v0_23.getResources().getDimension(2131165818));
        CS.v v3_13 = android.graphics.Paint$Style.STROKE;
        v1_24.setStyle(v3_13);
        v0_23.d = v1_24;
        androidx.lifecycle.F v1_26 = new android.graphics.Paint();
        v1_26.setColor(v0_23.getContext().getColor(2131100437));
        v1_26.setStrokeWidth(v0_23.getResources().getDimension(2131165818));
        v1_26.setStyle(v3_13);
        v0_23.e = v1_26;
        androidx.lifecycle.F v1_30 = new android.graphics.Paint();
        v1_30.setAntiAlias(1);
        v1_30.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
        v1_30.setStyle(v2_13);
        v0_23.c = v1_30;
        v0_23.invalidate();
        v0_23.setVisibility(0);
        this.r.b.setVisibility(0);
        this.getLifecycle().a(this.m1().h());
        androidx.lifecycle.T v0_1 = this.u;
        ((HD.h) v0_1.getValue()).d.f(this.getViewLifecycleOwner(), new CS.v(1, new DD.b(this, 1)));
        CS.v v3_3 = ((HD.h) v0_1.getValue());
        AA.d v2_1 = this.m1().g();
        androidx.lifecycle.T v0_5 = this.r;
        int v4_4 = ((vn.com.vng.zalopay.livenessdetection.presentation.widgets.OverlayView) v0_5.g);
        int v5_2 = ((androidx.camera.view.PreviewView) v0_5.h);
        androidx.lifecycle.T v0_7 = v3_3.B;
        if (v0_7 != null) {
            v0_7.cancel(0);
        }
        v3_3.B = kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v3_3), 0, 0, new B2.j(v2_1, v3_3, v4_4, v5_2, 0, 20), 3, 0);
        androidx.lifecycle.T v0_11 = this.m1().d();
        androidx.lifecycle.F v1_9 = v3_3.C;
        if (v1_9 != null) {
            v1_9.cancel(0);
        }
        v3_3.C = kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v3_3), 0, 0, new HD.g(v0_11, 0, 0), 3, 0);
        androidx.lifecycle.T v0_15 = v3_3.A;
        v0_15.f(this.getViewLifecycleOwner(), new e3.a(new DD.c(0, this), v0_15));
        v3_3.p.f(this.getViewLifecycleOwner(), new CS.v(1, new DD.b(this, 0)));
        v3_3.r.f(this.getViewLifecycleOwner(), new CS.v(1, new AA.d(13)));
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p9, android.view.ViewGroup p10, android.os.Bundle p11)
    {
        String v9_1 = p9.inflate(2131558901, p10, 0);
        int v10_1 = 2131362226;
        android.widget.Button v2_1 = ((android.widget.Button) l5.s.a(2131362226, v9_1));
        if (v2_1 != null) {
            androidx.constraintlayout.widget.ConstraintLayout v1_1 = ((androidx.constraintlayout.widget.ConstraintLayout) v9_1);
            v10_1 = 2131362503;
            if (((com.google.android.material.card.MaterialCardView) l5.s.a(2131362503, v9_1)) != null) {
                v10_1 = 2131363632;
                androidx.constraintlayout.widget.ConstraintLayout v4_1 = ((androidx.constraintlayout.widget.ConstraintLayout) l5.s.a(2131363632, v9_1));
                if (v4_1 != null) {
                    v10_1 = 2131363778;
                    vn.com.vng.zalopay.livenessdetection.presentation.widgets.OverlayView v5_1 = ((vn.com.vng.zalopay.livenessdetection.presentation.widgets.OverlayView) l5.s.a(2131363778, v9_1));
                    if (v5_1 != null) {
                        v10_1 = 2131364772;
                        android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(2131364772, v9_1));
                        if (v6_1 != null) {
                            v10_1 = 2131365093;
                            androidx.camera.view.PreviewView v7_1 = ((androidx.camera.view.PreviewView) l5.s.a(2131365093, v9_1));
                            if (v7_1 != null) {
                                this.r = new Jv.b(v1_1, v2_1, v1_1, v4_1, v5_1, v6_1, v7_1);
                                return v1_1;
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v9_1.getResources().getResourceName(v10_1)));
    }

    public final void onDestroy()
    {
        super.onDestroy();
        JD.g v0 = com.google.android.gms.internal.mlkit_vision_barcode.L7.c();
        v0.a.clear();
        v0.b = 0;
        return;
    }

    public final void onDestroyView()
    {
        super.onDestroyView();
        this.r = 0;
        return;
    }

    public final void onStop()
    {
        super.onStop();
        if (!this.requireActivity().isFinishing()) {
            ((sD.c) this.t.getValue()).b(new vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException$ContextSwitchException(0));
            this.requireActivity().finish();
        }
        return;
    }

    public final void onViewCreated(android.view.View p6, android.os.Bundle p7)
    {
        super.onViewCreated(p6, p7);
        java.util.Map v6_4 = ((HD.h) this.u.getValue());
        String v7_1 = this.s;
        String v0_6 = ((wD.c) v7_1.getValue()).i;
        v6_4.j = 0;
        v6_4.k = 0;
        v6_4.l.c = 0;
        ((kotlin.collections.s) v6_4.m.b).clear();
        v6_4.n = v0_6.size();
        String v3_6 = new java.util.LinkedList();
        v3_6.addAll(v0_6);
        v6_4.i = v3_6;
        new java.util.LinkedList().addAll(v0_6);
        String v0_0 = v6_4.h;
        String v3_9 = v6_4.i0();
        v0_0.getClass();
        v6_4.m0(HD.j.a(v0_0, v3_9, 0, 14));
        this.y.a(this.x, 0);
        java.util.Map v6_9 = ((wD.m) kotlin.collections.CollectionsKt.firstOrNull(((wD.c) v7_1.getValue()).i));
        if (v6_9 == null) {
            v6_9 = wD.m.d;
        }
        String v7_2 = "straight";
        switch (v6_9.ordinal()) {
            case 2:
                break;
            case 3:
                v7_2 = "turn right";
                break;
            case 4:
                v7_2 = "turn left";
                break;
            case 5:
                v7_2 = "smile";
                break;
            case 6:
                v7_2 = "blink 2 eyes";
                break;
            case 7:
                v7_2 = "zoom in";
                break;
            case 8:
                v7_2 = "zoom out";
                break;
            default:
        }
        com.google.android.gms.internal.mlkit_vision_barcode.L7.c().g(v7_2);
        JD.c.a("01.1480.000", kotlin.collections.a0.plus(com.google.android.gms.internal.mlkit_vision_barcode.L7.c().b(), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("action", v7_2))));
        return;
    }
}

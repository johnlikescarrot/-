package vn.ai.trueid.sdk.face;
public final class k1 implements M3.a {
    public final android.widget.RelativeLayout a;
    public final android.widget.Button b;
    public final android.widget.ImageView c;
    public final android.widget.Button d;
    public final android.widget.RelativeLayout e;
    public final vn.ai.trueid.sdk.face.present.presentation.widgets.GraphicOverlay f;
    public final android.widget.LinearLayout g;
    public final android.widget.RelativeLayout h;
    public final vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView i;
    public final android.widget.TextView j;
    public final android.widget.TextView k;
    public final android.widget.TextView l;
    public final android.widget.TextView m;
    public final androidx.camera.view.PreviewView n;

    public k1(android.widget.RelativeLayout p1, android.widget.Button p2, android.widget.ImageView p3, android.widget.Button p4, android.widget.RelativeLayout p5, vn.ai.trueid.sdk.face.present.presentation.widgets.GraphicOverlay p6, android.widget.LinearLayout p7, android.widget.RelativeLayout p8, vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView p9, android.widget.TextView p10, android.widget.TextView p11, android.widget.TextView p12, android.widget.TextView p13, androidx.camera.view.PreviewView p14)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        this.g = p7;
        this.h = p8;
        this.i = p9;
        this.j = p10;
        this.k = p11;
        this.l = p12;
        this.m = p13;
        this.n = p14;
        return;
    }

    public static vn.ai.trueid.sdk.face.k1 a(android.view.LayoutInflater p18, android.view.ViewGroup p19)
    {
        String v0_1 = p18.inflate(vn.ai.trueid.sdk.face.R$layout.trueid_facesdk_fragment_camerax, p19, 0);
        int v1_1 = vn.ai.trueid.sdk.face.R$id.btnAccept;
        android.widget.Button v5_0 = ((android.widget.Button) l5.s.a(v1_1, v0_1));
        if (v5_0 != null) {
            v1_1 = vn.ai.trueid.sdk.face.R$id.btnBack;
            android.widget.ImageView v6_1 = ((android.widget.ImageView) l5.s.a(v1_1, v0_1));
            if (v6_1 != null) {
                v1_1 = vn.ai.trueid.sdk.face.R$id.btnTryAgain;
                android.widget.Button v7_1 = ((android.widget.Button) l5.s.a(v1_1, v0_1));
                if (v7_1 != null) {
                    android.widget.RelativeLayout v4_1 = ((android.widget.RelativeLayout) v0_1);
                    v1_1 = vn.ai.trueid.sdk.face.R$id.graphicOverlay;
                    vn.ai.trueid.sdk.face.present.presentation.widgets.GraphicOverlay v9_1 = ((vn.ai.trueid.sdk.face.present.presentation.widgets.GraphicOverlay) l5.s.a(v1_1, v0_1));
                    if (v9_1 != null) {
                        v1_1 = vn.ai.trueid.sdk.face.R$id.imgSuccess;
                        if (((android.widget.ImageView) l5.s.a(v1_1, v0_1)) != null) {
                            v1_1 = vn.ai.trueid.sdk.face.R$id.layoutStatus;
                            android.widget.LinearLayout v10_1 = ((android.widget.LinearLayout) l5.s.a(v1_1, v0_1));
                            if (v10_1 != null) {
                                v1_1 = vn.ai.trueid.sdk.face.R$id.layoutSuccess;
                                android.widget.RelativeLayout v11_1 = ((android.widget.RelativeLayout) l5.s.a(v1_1, v0_1));
                                if (v11_1 != null) {
                                    v1_1 = vn.ai.trueid.sdk.face.R$id.overlayView;
                                    vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView v12_1 = ((vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView) l5.s.a(v1_1, v0_1));
                                    if (v12_1 != null) {
                                        v1_1 = vn.ai.trueid.sdk.face.R$id.toolbarText;
                                        android.widget.TextView v13_1 = ((android.widget.TextView) l5.s.a(v1_1, v0_1));
                                        if (v13_1 != null) {
                                            v1_1 = vn.ai.trueid.sdk.face.R$id.tvFailedText;
                                            android.widget.TextView v14_1 = ((android.widget.TextView) l5.s.a(v1_1, v0_1));
                                            if (v14_1 != null) {
                                                v1_1 = vn.ai.trueid.sdk.face.R$id.tvStatusText;
                                                android.widget.TextView v15_1 = ((android.widget.TextView) l5.s.a(v1_1, v0_1));
                                                if (v15_1 != null) {
                                                    v1_1 = vn.ai.trueid.sdk.face.R$id.tvSuccessText;
                                                    android.widget.TextView v16_1 = ((android.widget.TextView) l5.s.a(v1_1, v0_1));
                                                    if (v16_1 != null) {
                                                        v1_1 = vn.ai.trueid.sdk.face.R$id.viewFinder;
                                                        androidx.camera.view.PreviewView v17_1 = ((androidx.camera.view.PreviewView) l5.s.a(v1_1, v0_1));
                                                        if (v17_1 != null) {
                                                            return new vn.ai.trueid.sdk.face.k1(v4_1, v5_0, v6_1, v7_1, v4_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v16_1, v17_1);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v0_1.getResources().getResourceName(v1_1)));
    }

    public final android.view.View getRoot()
    {
        return this.a;
    }
}

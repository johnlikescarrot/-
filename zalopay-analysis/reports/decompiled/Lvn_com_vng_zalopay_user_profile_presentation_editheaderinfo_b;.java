package vn.com.vng.zalopay.user_profile.presentation.editheaderinfo;
public final class b extends sZ.b {
    public static final vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.a L;
    public static final synthetic kotlin.reflect.n[] M;
    public vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b$a H;
    public final Ai.t I;
    public final Ai.t J;
    public final Ai.t K;

    static b()
    {
        kotlin.reflect.p v1_2 = kotlin.jvm.internal.M.a;
        vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.a v0_6 = v1_2.g(new kotlin.jvm.internal.D("viewTakeAPhoto", 0, "getViewTakeAPhoto()Landroid/widget/TextView;", vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b));
        kotlin.reflect.p v3_0 = Kb.e.r(vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b, "viewPickImageFromGallery", "getViewPickImageFromGallery()Landroid/widget/TextView;", 0, v1_2);
        kotlin.reflect.p v1_1 = Kb.e.r(vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b, "imgClose", "getImgClose()Landroid/widget/ImageView;", 0, v1_2);
        kotlin.reflect.n[] v4_1 = new kotlin.reflect.n[3];
        v4_1[0] = v0_6;
        v4_1[1] = v3_0;
        v4_1[2] = v1_1;
        vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b.M = v4_1;
        vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b.L = new vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.a();
        return;
    }

    public b()
    {
        this.I = new Ai.t(2131365133, 22);
        this.J = new Ai.t(2131365117, 22);
        this.K = new Ai.t(2131363097, 22);
        return;
    }

    public final void onActivityResult(int p5, int p6, android.content.Intent p7)
    {
        super.onActivityResult(p5, p6, p7);
        String v0_0 = "";
        if ((p6 != -1) || (p5 != 12345)) {
            if ((p6 != -1) || (p5 != 39799)) {
                this.o1(0, 0);
                return;
            } else {
                vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b$a v5_1 = this.H;
                if (v5_1 != null) {
                    if ((p7 != null) && (p7.getData() != null)) {
                        android.net.Uri v6_2 = p7.getDataString();
                        if (v6_2 != null) {
                            v0_0 = v6_2;
                        }
                    }
                    String v7_1;
                    android.net.Uri v6_3 = android.net.Uri.parse(v0_0);
                    if (p7 == null) {
                        v7_1 = 0;
                    } else {
                        v7_1 = p7.getStringExtra("absolute_image_capture");
                    }
                    v5_1.B0(v6_3, String.valueOf(v7_1));
                    this.o1(0, 0);
                }
            }
        } else {
            vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b$a v5_2 = this.H;
            if (v5_2 != null) {
                if ((p7 != null) && (p7.getData() != null)) {
                    android.net.Uri v6_5 = p7.getDataString();
                    if (v6_5 != null) {
                        v0_0 = v6_5;
                    }
                }
                v5_2.H0(android.net.Uri.parse(v0_0));
                this.o1(0, 0);
                return;
            }
        }
        return;
    }

    public final void onStart()
    {
        android.view.Window v0_0 = this.C;
        if (v0_0 != null) {
            android.view.Window v0_1 = v0_0.getWindow();
            if (v0_1 != null) {
                v0_1.setGravity(81);
                v0_1.setLayout(-1, -2);
            }
        }
        super.onStart();
        return;
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        p9.n7.c(com.google.android.gms.internal.mlkit_vision_barcode.b0.a.r());
        super.onViewCreated(p3, p4);
        KP.k v4_4 = vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b.M;
        ((android.widget.TextView) this.I.j(this, v4_4[0])).setOnClickListener(new KP.k(this, 0));
        ((android.widget.TextView) this.J.j(this, v4_4[1])).setOnClickListener(new KP.k(this, 1));
        ((android.widget.ImageView) this.K.j(this, v4_4[2])).setOnClickListener(new KP.k(this, 2));
        return;
    }

    public final boolean w1()
    {
        return 0;
    }

    public final int x1()
    {
        return 2131559513;
    }

    public final void z1()
    {
        return;
    }
}

package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class MediaContentBottomDialogFragment extends Io.d {
    public final a9.f K;
    public final ng.S L;
    public final ng.S M;
    public Z2.L N;
    public Eo.a O;
    public Bo.n P;
    public final ng.w Q;

    public MediaContentBottomDialogFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.K = new a9.f(v1_0.b(Io.O), new Io.i(this, 10));
        this.L = ng.x.b(new Io.K(this, 0));
        this.M = ng.x.b(new Io.K(this, 1));
        ng.w v0_8 = new Io.K(this, 2);
        ng.w v2_6 = ng.x.a(ng.y.c, new Io.M(0, new Io.i(this, 11)));
        this.Q = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.W), new Io.N(v2_6, 0), new Io.N(v2_6, 1), v0_8);
        return;
    }

    public final M3.a C1(android.view.LayoutInflater p11, android.view.ViewGroup p12)
    {
        String v11_1 = p11.inflate(-2079850467, p12, 0);
        int v12_1 = -2079915881;
        android.widget.LinearLayout v3_1 = ((android.widget.LinearLayout) l5.s.a(-2079915881, v11_1));
        if (v3_1 != null) {
            v12_1 = -2079915880;
            android.widget.LinearLayout v4_1 = ((android.widget.LinearLayout) l5.s.a(-2079915880, v11_1));
            if (v4_1 != null) {
                v12_1 = -2079915849;
                if (((android.widget.ScrollView) l5.s.a(-2079915849, v11_1)) != null) {
                    v12_1 = -2079915840;
                    vn.zalopay.ui.svgutil.ZPSVGImageView v5_1 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915840, v11_1));
                    if (v5_1 != null) {
                        v12_1 = -2079915819;
                        android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(-2079915819, v11_1));
                        if (v6_1 != null) {
                            v12_1 = -2079915813;
                            android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(-2079915813, v11_1));
                            if (v7_1 != null) {
                                v12_1 = -2079915823;
                                android.widget.TextView v8_1 = ((android.widget.TextView) l5.s.a(-2079915823, v11_1));
                                if (v8_1 != null) {
                                    v12_1 = -2079915778;
                                    android.view.View v9 = l5.s.a(-2079915778, v11_1);
                                    if (v9 != null) {
                                        return new Sn.n(((android.widget.RelativeLayout) v11_1), v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v11_1.getResources().getResourceName(v12_1)));
    }

    public final void D1(android.view.View p6)
    {
        android.view.ViewGroup$LayoutParams v0 = p6.getLayoutParams();
        int v1_3 = ((((((Sn.n) this.y1()).b.getHeight() + ((Sn.n) this.y1()).c.getHeight()) + ((Sn.n) this.y1()).g.getHeight()) + ((Sn.n) this.y1()).h.getHeight()) + 30);
        int v2_12 = HF.b.e(this.requireActivity());
        float v3_0 = ((float) v2_12);
        int v4_2 = ((int) (1053609165 * v3_0));
        if (v1_3 < v2_12) {
            if (v1_3 < v4_2) {
                v1_3 = v4_2;
            }
        } else {
            v1_3 = ((int) (v3_0 * 1063675494));
        }
        v0.height = v1_3;
        p6.setLayoutParams(v0);
        return;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument E1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomMediaDialogArgument) this.L.getValue());
    }

    public final void onCreate(android.os.Bundle p2)
    {
        IllegalAccessException v2_4;
        super.onCreate(p2);
        IllegalAccessException v2_3 = this.getActivity();
        if (!(v2_3 instanceof Eo.a)) {
            v2_4 = 0;
        } else {
            v2_4 = ((Eo.a) v2_3);
        }
        if (v2_4 == null) {
            throw new IllegalAccessException("Parent activity must implement BankComponentListener");
        } else {
            this.O = v2_4;
            v2_4.m().p(this);
            return;
        }
    }

    public final void onViewCreated(android.view.View p9, android.os.Bundle p10)
    {
        super.onViewCreated(p9, p10);
        this.N = q9.p0.a(this);
        ((Sn.n) this.y1()).g.setText(this.E1().getTitle());
        ((Sn.n) this.y1()).e.setText(this.E1().getCancelButton());
        ((Sn.n) this.y1()).f.setText(this.E1().getConfirmButton());
        ((Sn.n) this.y1()).e.setOnClickListener(new Io.J(this, 0));
        ((Sn.n) this.y1()).d.setOnClickListener(new Io.J(this, 1));
        ((Sn.n) this.y1()).f.setOnClickListener(new Io.J(this, 2));
        String v9_34 = ((Iterable) this.E1().getBankMediaEntities()).iterator();
        while (v9_34.hasNext()) {
            NullPointerException v10_48 = ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaEntity) v9_34.next());
            int v0_57 = v10_48.getType();
            int v2_11 = -2079915784;
            switch (v0_57.hashCode()) {
                case -2113395594:
                    if (v0_57.equals("text_error")) {
                        NullPointerException v10_27 = kotlin.collections.CollectionsKt.firstOrNull(v10_48.getValues());
                        if (v10_27 != null) {
                            NullPointerException v10_28 = ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) v10_27);
                            if (v10_28.getContent().length() != 0) {
                                int v0_41 = android.view.LayoutInflater.from(this.getContext()).inflate(-2079850456, 0, 0);
                                int v1_17 = ((android.widget.RelativeLayout) v0_41);
                                if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915839, v0_41)) == null) {
                                    v2_11 = -2079915839;
                                } else {
                                    android.widget.TextView v4_8 = ((android.widget.TextView) l5.s.a(-2079915784, v0_41));
                                    if (v4_8 != null) {
                                        com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v4_8, v10_28.getContent(), new BZ.n(2));
                                        ((Sn.n) this.y1()).b.addView(v1_17, ((Sn.n) this.y1()).b.getChildCount());
                                    }
                                }
                                throw new NullPointerException("Missing required view with ID: ".concat(v0_41.getResources().getResourceName(v2_11)));
                            }
                        }
                    } else {
                    }
                    break;
                case -2099809082:
                    if (v0_57.equals("text_title")) {
                        NullPointerException v10_17 = kotlin.collections.CollectionsKt.firstOrNull(v10_48.getValues());
                        if (v10_17 != null) {
                            NullPointerException v10_18 = ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) v10_17);
                            if (v10_18.getContent().length() != 0) {
                                int v0_29 = android.view.LayoutInflater.from(this.getContext()).inflate(-2079850453, 0, 0);
                                int v1_13 = ((android.widget.FrameLayout) v0_29);
                                android.widget.TextView v4_5 = ((android.widget.TextView) l5.s.a(-2079915784, v0_29));
                                if (v4_5 == null) {
                                    throw new NullPointerException("Missing required view with ID: ".concat(v0_29.getResources().getResourceName(-2079915784)));
                                } else {
                                    com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v4_5, v10_18.getContent(), new BZ.n(2));
                                    ((Sn.n) this.y1()).b.addView(v1_13, ((Sn.n) this.y1()).b.getChildCount());
                                }
                            }
                        }
                    } else {
                    }
                    break;
                case 3556653:
                    if (v0_57.equals("text")) {
                        NullPointerException v10_6 = kotlin.collections.CollectionsKt.firstOrNull(v10_48.getValues());
                        if (v10_6 != null) {
                            NullPointerException v10_8 = ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) v10_6);
                            if (v10_8.getContent().length() != 0) {
                                int v0_17 = android.view.LayoutInflater.from(this.getContext()).inflate(-2079850454, 0, 0);
                                int v1_9 = ((android.widget.FrameLayout) v0_17);
                                android.widget.TextView v4_3 = ((android.widget.TextView) l5.s.a(-2079915784, v0_17));
                                if (v4_3 == null) {
                                    throw new NullPointerException("Missing required view with ID: ".concat(v0_17.getResources().getResourceName(-2079915784)));
                                } else {
                                    com.google.android.gms.internal.mlkit_vision_barcode.V7.b(v4_3, v10_8.getContent(), new BZ.n(2));
                                    ((Sn.n) this.y1()).b.addView(v1_9, ((Sn.n) this.y1()).b.getChildCount());
                                }
                            }
                        }
                    } else {
                    }
                    break;
                case 112202875:
                    if (v0_57.equals("video")) {
                        NullPointerException v10_51 = kotlin.collections.CollectionsKt.firstOrNull(v10_48.getValues());
                        if (v10_51 != null) {
                            NullPointerException v10_1 = ((vn.com.vng.zalopay.bankbinding.domain.entity.BankMediaValueEntity) v10_51);
                            int v0_1 = kotlin.text.StringsKt.y(v10_1.getExternalResource(), new String[] {"."}), 0, 6);
                            if (v0_1.size() == 2) {
                                int v2_2 = this.getContext();
                                String v6_3 = ((String) v0_1.get(0));
                                int v0_4 = ((String) v0_1.get(1)).toLowerCase(java.util.Locale.ROOT);
                                StringBuilder v7_3 = new StringBuilder(".");
                                v7_3.append(v0_4);
                                int v0_6 = n9.e0.b(v2_2, v6_3, v7_3.toString());
                                int v1_3 = android.view.LayoutInflater.from(this.getContext()).inflate(-2079850455, 0, 0);
                                android.widget.TextView v4_1 = ((android.widget.ImageView) l5.s.a(-2079915889, v1_3));
                                if (v4_1 == null) {
                                    throw new NullPointerException("Missing required view with ID: ".concat(v1_3.getResources().getResourceName(-2079915889)));
                                } else {
                                    int v1_4 = ((android.widget.FrameLayout) v1_3);
                                    v4_1.setImageDrawable(v0_6);
                                    ((Sn.n) this.y1()).b.addView(v1_4, ((Sn.n) this.y1()).b.getChildCount());
                                    v4_1.setOnClickListener(new Io.L(this, v10_1, 0));
                                }
                            }
                        }
                    } else {
                    }
                    break;
                default:
            }
            NullPointerException v10_36 = v10_48.getType();
            int v0_49 = new StringBuilder("[BankMediaPopup] Not support media type ");
            v0_49.append(v10_36);
            int v0_50 = new Object[0];
            ck.b.e(v0_49.toString(), v0_50);
        }
        ((Ro.W) this.Q.getValue()).g.f(this.getViewLifecycleOwner(), new cV.y(2, new Fo.c(5, this)));
        return;
    }

    public final boolean z1()
    {
        return 0;
    }
}

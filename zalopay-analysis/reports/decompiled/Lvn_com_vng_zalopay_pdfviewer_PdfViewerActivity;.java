package vn.com.vng.zalopay.pdfviewer;
public final class PdfViewerActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity {
    public static final pI.a n;
    public final boolean k;
    public oI.a l;
    public android.widget.TextView m;

    static PdfViewerActivity()
    {
        vn.com.vng.zalopay.pdfviewer.PdfViewerActivity.n = pI.a.a;
        return;
    }

    public PdfViewerActivity()
    {
        this.k = 1;
        return;
    }

    public final int e1()
    {
        return 2131558469;
    }

    public final OD.a g1()
    {
        return new OD.a("", -1);
    }

    public final void h1()
    {
        String v0_7 = this.getLayoutInflater().inflate(2131558469, 0, 0);
        NullPointerException v1_0 = 2131363580;
        android.widget.FrameLayout v5_1 = ((android.widget.FrameLayout) l5.s.a(2131363580, v0_7));
        if (v5_1 != null) {
            v1_0 = 2131363819;
            vn.com.vng.zalopay.pdfviewer.PdfRendererView v6_1 = ((vn.com.vng.zalopay.pdfviewer.PdfRendererView) l5.s.a(2131363819, v0_7));
            if (v6_1 != null) {
                v1_0 = 2131363919;
                android.widget.ProgressBar v7_1 = ((android.widget.ProgressBar) l5.s.a(2131363919, v0_7));
                if (v7_1 != null) {
                    v1_0 = 2131364478;
                    oI.a v3_2 = l5.s.a(2131364478, v0_7);
                    if (v3_2 != null) {
                        CZ.b v8_1 = ((android.widget.TextView) l5.s.a(2131364576, v3_2));
                        if (v8_1 == null) {
                            throw new NullPointerException("Missing required view with ID: ".concat(v3_2.getResources().getResourceName(2131364576)));
                        } else {
                            android.widget.LinearLayout v4_2 = ((android.widget.LinearLayout) v0_7);
                            this.l = new oI.a(v4_2, v5_1, v6_1, v7_1, new CZ.b(((androidx.appcompat.widget.Toolbar) v3_2), ((androidx.appcompat.widget.Toolbar) v3_2), v8_1, 4));
                            this.setContentView(v4_2);
                            return;
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v0_7.getResources().getResourceName(v1_0)));
    }

    public final boolean i1()
    {
        return this.k;
    }

    public final void j1()
    {
        android.widget.Toast.makeText(this, "Pdf has been corrupted", 0).show();
        this.k1(1);
        this.finish();
        return;
    }

    public final void k1(boolean p2)
    {
        android.widget.ProgressBar v0_0 = this.l;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        int v2_1;
        if (p2 == 0) {
            v2_1 = 8;
        } else {
            v2_1 = 0;
        }
        v0_0.d.setVisibility(v2_1);
        return;
    }

    public final void onCreate(android.os.Bundle p11)
    {
        super.onCreate(p11);
        this.m = ((android.widget.TextView) this.findViewById(2131364576));
        vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_13 = this.l;
        oI.a v1_0 = 0;
        if (v11_13 == null) {
            v11_13 = 0;
        }
        ((androidx.appcompat.widget.Toolbar) v11_13.e.c).setOnClickListener(new androidx.media3.ui.h(16, this));
        vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_5 = this.getIntent();
        if (v11_5 != null) {
            vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_6 = v11_5.getExtras();
            if (v11_6 != null) {
                vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_7 = v11_6.getString("pdf_file_title");
                if (v11_7 != null) {
                    android.webkit.WebView v0_12 = this.m;
                    if (v0_12 != null) {
                        v0_12.setText(v11_7);
                    }
                }
            }
        }
        vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_9 = this.getIntent();
        if (v11_9 != null) {
            vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_10 = v11_9.getExtras();
            if (v11_10 != null) {
                vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_11 = v11_10.getString("pdf_file_url");
                if (v11_11 != null) {
                    android.webkit.WebView v0_15 = vn.com.vng.zalopay.pdfviewer.PdfViewerActivity.n.ordinal();
                    if (v0_15 == null) {
                        if (android.text.TextUtils.isEmpty(v11_11)) {
                            this.j1();
                        }
                        android.webkit.WebView v0_17 = this.l;
                        if (v0_17 == null) {
                            v0_17 = 0;
                        }
                        android.webkit.WebView v0_18 = v0_17.c;
                        new lS.b(v11_11, new vn.com.vng.zalopay.pdfviewer.b(v0_18, pI.d.b, androidx.lifecycle.l0.j(((androidx.appcompat.app.AppCompatActivity) v0_18.getContext()))));
                        vn.com.vng.zalopay.pdfviewer.PdfRendererView v11_14 = this.l;
                        if (v11_14 != null) {
                            v1_0 = v11_14;
                        }
                        v1_0.c.setStatusListener(new vn.com.vng.zalopay.pdfviewer.f(this));
                    } else {
                        if (v0_15 == 1) {
                            if (android.text.TextUtils.isEmpty(v11_11)) {
                                this.j1();
                            }
                            android.webkit.WebView v0_23 = new java.io.File(v11_11);
                            oI.a v3_5 = this.l;
                            if (v3_5 != null) {
                                v1_0 = v3_5;
                            }
                            v1_0.c.a(v0_23, pI.d.b);
                            this.getApplicationContext();
                            new java.io.File(v11_11).delete();
                            this.k1(0);
                            return;
                        } else {
                            if (v0_15 != 2) {
                                throw new kotlin.NoWhenBranchMatchedException();
                            } else {
                                if (android.text.TextUtils.isEmpty(v11_11)) {
                                    this.j1();
                                }
                                this.getApplicationContext();
                                android.webkit.WebView v0_1 = new java.io.FileInputStream(new java.io.File(v11_11));
                                android.webkit.WebView v4_3 = new java.io.InputStreamReader(v0_1);
                                int v5_1 = new java.io.BufferedReader(v4_3);
                                String v6_1 = new StringBuilder();
                                while(true) {
                                    String v7_0 = v5_1.readLine();
                                    if (v7_0 == null) {
                                        break;
                                    }
                                    v6_1.append(v7_0);
                                }
                                v5_1.close();
                                v4_3.close();
                                v0_1.close();
                                String v6_2 = v6_1.toString();
                                if (v6_2 != null) {
                                    android.webkit.WebView v0_4 = this.l;
                                    if (v0_4 == null) {
                                        v0_4 = 0;
                                    }
                                    android.webkit.WebView v0_5 = v0_4.c;
                                    v0_5.addView(android.view.LayoutInflater.from(v0_5.getContext()).inflate(2131559663, v0_5, 0));
                                    android.webkit.WebView v4_9 = ((androidx.recyclerview.widget.RecyclerView) v0_5.findViewById(2131364013));
                                    v0_5.a = v4_9;
                                    if (v4_9 == null) {
                                        v4_9 = 0;
                                    }
                                    v4_9.setVisibility(8);
                                    android.webkit.WebView v4_12 = ((android.webkit.WebView) v0_5.findViewById(2131365192));
                                    v0_5.b = v4_12;
                                    if (v4_12 == null) {
                                        v4_12 = 0;
                                    }
                                    v4_12.setVisibility(0);
                                    android.webkit.WebView v4_13 = v0_5.b;
                                    if (v4_13 == null) {
                                        v4_13 = 0;
                                    }
                                    v4_13.getSettings().setJavaScriptEnabled(1);
                                    oI.a v3_0 = v0_5.b;
                                    if (v3_0 == null) {
                                        v3_0 = 0;
                                    }
                                    android.webkit.WebView v4_17;
                                    v3_0.setWebViewClient(new vn.com.vng.zalopay.pdfviewer.a(v0_5.j));
                                    android.webkit.WebView v0_8 = v0_5.b;
                                    if (v0_8 != null) {
                                        v4_17 = v0_8;
                                    } else {
                                        v4_17 = 0;
                                    }
                                    v4_17.loadDataWithBaseURL(0, v6_2, "text/html", "utf-8", 0);
                                }
                                this.getApplicationContext();
                                new java.io.File(v11_11).delete();
                                this.k1(0);
                                return;
                            }
                        }
                    }
                }
            }
        }
        return;
    }
}

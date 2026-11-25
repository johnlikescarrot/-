package vn.com.vng.zalopay.webview.ui;
public class WebViewFragment extends UO.a implements vn.com.vng.zalopay.webview.widget.d, vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment$a {
    protected android.widget.ImageView mImgError;
    protected android.view.View mLayoutRetry;
    protected android.widget.ProgressBar mProgressBar;
    protected android.widget.TextView mTvError;
    public vn.com.vng.zalopay.webview.widget.e v;
    public vn.com.vng.zalopay.webview.widget.ZPWebView w;
    public vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment x;
    public nY.p y;

    public WebViewFragment()
    {
        return;
    }

    public final void Z0()
    {
        vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment v0_0 = this.mLayoutRetry;
        if (v0_0 != null) {
            v0_0.setVisibility(8);
        }
        vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment v0_2 = this.v;
        String v2 = v0_2.a;
        if ((this.getActivity() != null) && (!android.text.TextUtils.isEmpty(v2))) {
            vn.com.vng.zalopay.webview.widget.ZPWebView v1_1 = v0_2.c;
            if (v1_1 != null) {
                v0_2.b = 0;
                v0_2.a = v2;
                v1_1.loadUrl(v2);
            }
        }
        vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment v0_1 = this.x;
        if (v0_1 != null) {
            v0_1.n1();
        }
        return;
    }

    public final void l0()
    {
        if (this.getActivity() != null) {
            this.getActivity().finish();
            return;
        } else {
            return;
        }
    }

    public final int m1()
    {
        return 2131560393;
    }

    public final void n1()
    {
        IllegalStateException v0_0 = q9.a5.c();
        if (v0_0 == null) {
            if (!(this.getContext() instanceof vn.com.vng.zalopay.user.UserBaseActivity)) {
                throw new IllegalStateException(wmoi.igob.ggcn(9851));
            } else {
                v0_0 = ((vn.com.vng.zalopay.user.UserBaseActivity) this.getContext()).m1();
                if (v0_0 == null) {
                    throw new IllegalStateException(wmoi.igob.ggcn(9850));
                }
            }
        }
        v0_0.F0(this);
        return;
    }

    public final void onActivityCreated(android.os.Bundle p4)
    {
        super.onActivityCreated(p4);
        String v4_1 = this.getArguments();
        if (v4_1 != null) {
            String v4_2 = v4_1.getString(wmoi.igob.ggcn(9852));
            vn.com.vng.zalopay.webview.widget.e v0_2 = this.v;
            if ((v0_2 != null) && ((this.getActivity() != null) && (!android.text.TextUtils.isEmpty(v4_2)))) {
                vn.com.vng.zalopay.webview.widget.ZPWebView v1_2 = v0_2.c;
                if (v1_2 != null) {
                    v0_2.b = 0;
                    v0_2.a = v4_2;
                    v1_2.loadUrl(v4_2);
                }
            }
        }
        return;
    }

    public final boolean onBackPressed()
    {
        int v0_0 = this.v;
        if (v0_0 != 0) {
            int v0_2 = v0_0.c;
            if ((v0_2 != 0) && (v0_2.canGoBack())) {
                v0_2.goBack();
                return 1;
            }
        }
        return 0;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        super.onCreate(p3);
        android.os.Bundle v3_1 = this.getArguments();
        boolean v0 = 1;
        if (v3_1 != null) {
            v0 = v3_1.getBoolean(wmoi.igob.ggcn(9853), 1);
        }
        this.setHasOptionsMenu(v0);
        return;
    }

    public final void onCreateOptionsMenu(android.view.Menu p3, android.view.MenuInflater p4)
    {
        p4.inflate(2131623945, p3);
        super.onCreateOptionsMenu(p3, p4);
        jR.k v4_9 = p3.findItem(2131361925).getActionView();
        ((vn.zalopay.ui.IconFont) v4_9.findViewById(2131363114)).setIcon(2131955793);
        v4_9.setOnClickListener(new jR.k(this, 0));
        android.view.View v3_2 = p3.findItem(2131361910).getActionView();
        ((vn.zalopay.ui.IconFont) v3_2.findViewById(2131363097)).setIcon(2131955797);
        v3_2.setOnClickListener(new jR.k(this, 1));
        return;
    }

    public final void onDestroyView()
    {
        vn.com.vng.zalopay.webview.widget.e v0 = this.v;
        if (v0 != null) {
            int v1_1 = v0.c;
            if (v1_1 != 0) {
                ((android.view.ViewGroup) v1_1.getParent()).removeView(v1_1);
                try {
                    v1_1.removeAllViews();
                } catch (Exception) {
                }
                v1_1.destroy();
            }
            v0.d = 0;
        }
        super.onDestroyView();
        return;
    }

    public final void onPause()
    {
        vn.com.vng.zalopay.webview.widget.ZPWebView v0_0 = this.v;
        if (v0_0 != null) {
            vn.com.vng.zalopay.webview.widget.ZPWebView v0_1 = v0_0.c;
            if (v0_1 != null) {
                v0_1.pauseTimers();
                v0_1.onPause();
            }
        }
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        vn.com.vng.zalopay.webview.widget.ZPWebView v0_0 = this.v;
        if (v0_0 != null) {
            vn.com.vng.zalopay.webview.widget.ZPWebView v0_1 = v0_0.c;
            if (v0_1 != null) {
                v0_1.onResume();
                v0_1.resumeTimers();
            }
        }
        return;
    }

    public void onRetryClicked()
    {
        vn.com.vng.zalopay.webview.widget.e v0_0 = this.mLayoutRetry;
        if (v0_0 != null) {
            v0_0.setVisibility(8);
        }
        vn.com.vng.zalopay.webview.widget.e v0_1 = this.mLayoutRetry;
        if (v0_1 != null) {
            v0_1.setVisibility(8);
        }
        vn.com.vng.zalopay.webview.widget.e v0_2 = this.v;
        String v2 = v0_2.a;
        if ((this.getActivity() != null) && (!android.text.TextUtils.isEmpty(v2))) {
            vn.com.vng.zalopay.webview.widget.ZPWebView v1_2 = v0_2.c;
            if (v1_2 != null) {
                v0_2.b = 0;
                v0_2.a = v2;
                v1_2.loadUrl(v2);
            }
        }
        return;
    }

    public final void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        this.w = ((vn.com.vng.zalopay.webview.widget.ZPWebView) p1.findViewById(2131365205));
        vn.com.vng.zalopay.webview.widget.ZPWebView v1_8 = this.mLayoutRetry;
        if (v1_8 != null) {
            v1_8.setVisibility(8);
        }
        this.w.getSettings().setUseWideViewPort(1);
        this.v = new vn.com.vng.zalopay.webview.widget.e(this.w, this);
        this.w.setWebChromeClient(new vn.com.vng.zalopay.webview.ui.a(this));
        return;
    }

    public final void s1()
    {
        if (this.getContext() != null) {
            if (!this.y.a()) {
                if (this.mLayoutRetry != null) {
                    android.view.View v0_1 = this.mImgError;
                    if ((v0_1 != null) && (this.mTvError != null)) {
                        v0_1.setImageResource(2131234413);
                        this.mTvError.setText(2131952642);
                        this.mLayoutRetry.setVisibility(0);
                    }
                }
            } else {
                if (this.mLayoutRetry != null) {
                    android.view.View v0_5 = this.mImgError;
                    if ((v0_5 != null) && (this.mTvError != null)) {
                        v0_5.setImageResource(2131234414);
                        this.mTvError.setText(2131953683);
                        this.mLayoutRetry.setVisibility(0);
                    }
                }
            }
            this.o1();
            return;
        } else {
            return;
        }
    }
}

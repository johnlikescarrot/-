package vn.com.vng.zalopay.webapp.fragment;
public class WebBottomSheetDialogFragment extends V9.h {
    public vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment$a H;
    public String I;
    public final DZ.J J;

    public WebBottomSheetDialogFragment()
    {
        this.J = new DZ.J(2, this);
        return;
    }

    public void handleClickCopyURL()
    {
        ((android.content.ClipboardManager) this.requireContext().getSystemService("clipboard")).setPrimaryClip(android.content.ClipData.newPlainText("Copied URL", this.I));
        android.widget.Toast.makeText(this.getContext(), this.getContext().getResources().getText(2131952339), 0).show();
        return;
    }

    public void handleClickOpenInBrowser()
    {
        Exception v0_0 = this.getContext();
        if (v0_0 != null) {
            try {
                String v2_2 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(this.I));
            } catch (Exception v0_1) {
                Object[] v1_3 = new Object[0];
                ck.b.c(v0_1, "Cannot open web in default browser", v1_3);
                return;
            }
            if (v2_2.resolveActivity(v0_0.getPackageManager()) != null) {
                v0_0.startActivity(v2_2);
                return;
            }
        }
        return;
    }

    public void handleClickRefreshWeb()
    {
        vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment$a v0 = this.H;
        if (v0 != null) {
            v0.Z0();
            return;
        } else {
            return;
        }
    }

    public void handleClickShareOnZalo()
    {
        int v0_0 = this.getContext();
        android.content.Intent v1_0 = this.I;
        java.util.ArrayList v5_1 = new java.util.ArrayList();
        java.util.Iterator v6_3 = new android.content.Intent();
        v6_3.setAction("android.intent.action.SEND");
        v6_3.setType("text/plain");
        java.util.Iterator v6_0 = v0_0.getPackageManager().queryIntentActivities(v6_3, 0);
        if (!v6_0.isEmpty()) {
            java.util.Iterator v6_1 = v6_0.iterator();
            while (v6_1.hasNext()) {
                String v7_4 = ((android.content.pm.ResolveInfo) v6_1.next());
                String v8_1 = v7_4.activityInfo.packageName;
                if (v8_1.contains("zalo")) {
                    android.content.Intent v9_3 = new android.content.Intent();
                    v9_3.setComponent(new android.content.ComponentName(v8_1, v7_4.activityInfo.name));
                    v9_3.setAction("android.intent.action.SEND");
                    v9_3.setType("text/plain");
                    v9_3.putExtra("android.intent.extra.TEXT", v1_0);
                    v9_3.setPackage(v8_1);
                    v5_1.add(v9_3);
                }
            }
            if (!v5_1.isEmpty()) {
                v0_0.startActivity(android.content.Intent.createChooser(((android.content.Intent) v5_1.get(0)), "Choose app to share"));
                UR.a.c(15320, 0, 0, 0);
                return;
            } else {
                m9.B.e(((android.app.Activity) v0_0), v0_0.getString(2131951798), 0);
                return;
            }
        } else {
            return;
        }
    }

    public final void onCancel(android.content.DialogInterface p1)
    {
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p8, android.view.ViewGroup p9, android.os.Bundle p10)
    {
        android.view.View v8_1 = p8.inflate(2131558522, p9, 1);
        butterknife.ButterKnife.a(v8_1, this);
        if ((this.getTargetFragment() instanceof vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment$a)) {
            this.H = ((vn.com.vng.zalopay.webapp.fragment.WebBottomSheetDialogFragment$a) this.getTargetFragment());
        }
        if (this.getArguments() != null) {
            this.I = this.getArguments().getString("current_url");
        }
        com.google.android.material.bottomsheet.BottomSheetBehavior v9_8 = ((android.widget.TextView) v8_1.findViewById(2131364896));
        if (v9_8 != null) {
            DZ.J v10_2 = this.I;
            int v1_1 = android.net.Uri.parse(v10_2).getHost();
            if (!android.text.TextUtils.isEmpty(v1_1)) {
                String[] v2_2 = v1_1.split("\\.");
                String v5_0 = v2_2.length;
                if (v5_0 >= 1) {
                    if (v5_0 >= 3) {
                        int v1_2 = 0;
                        while (v1_2 < 7) {
                            if (!v2_2[(v2_2.length - 2)].equals(nY.y.a[v1_2])) {
                                v1_2++;
                            } else {
                                v1_1 = nY.y.d(3, v10_2);
                            }
                        }
                        v1_1 = nY.y.d(2, v10_2);
                    }
                } else {
                    v1_1 = "";
                }
            }
            if (!android.text.TextUtils.isEmpty(v1_1)) {
                v9_8.setVisibility(0);
                v9_8.setText(String.format(this.getString(2131955800), new Object[] {v1_1})));
            } else {
                v9_8.setVisibility(8);
            }
        }
        com.google.android.material.bottomsheet.BottomSheetBehavior v9_12 = ((V1.c) v8_1.getLayoutParams());
        if (v9_12 != null) {
            com.google.android.material.bottomsheet.BottomSheetBehavior v9_13 = v9_12.a;
            if ((v9_13 != null) && ((v9_13 instanceof com.google.android.material.bottomsheet.BottomSheetBehavior))) {
                ((com.google.android.material.bottomsheet.BottomSheetBehavior) v9_13).z(this.J);
            }
        }
        return v8_1;
    }
}

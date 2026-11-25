package vn.com.zalopay.wallet.ui.channellist;
public final class TransactionInfoView extends android.widget.LinearLayout {
    public final android.view.View a;
    public final android.widget.TextView b;
    public final android.widget.LinearLayout c;
    public final android.view.View d;
    public final android.widget.TextView e;
    public final android.view.View f;
    public final android.widget.TextView g;
    public final android.widget.TextView h;
    public final android.view.View i;
    public final android.widget.TextView j;
    public final android.view.View k;
    public final android.widget.TextView l;
    public final android.view.View m;
    public final android.widget.TextView n;

    public TransactionInfoView(android.content.Context p3, android.util.AttributeSet p4)
    {
        super(p3, p4, 0);
        android.widget.TextView v3_16 = p3.obtainStyledAttributes(p4, sY.a.a);
        v3_16.getResourceId(0, 2131558729);
        int v4_1 = v3_16.getResourceId(1, 2131559584);
        v3_16.recycle();
        android.view.View.inflate(super.getContext(), v4_1, super);
        super.a = super.findViewById(2131361964);
        super.b = ((android.widget.TextView) super.findViewById(2131361965));
        super.c = ((android.widget.LinearLayout) super.findViewById(2131364516));
        super.d = super.findViewById(2131364510);
        super.e = ((android.widget.TextView) super.findViewById(2131364511));
        super.f = super.findViewById(2131362543);
        super.g = ((android.widget.TextView) super.findViewById(2131362544));
        super.h = ((android.widget.TextView) super.findViewById(2131362549));
        super.i = super.findViewById(2131364512);
        super.j = ((android.widget.TextView) super.findViewById(2131364513));
        super.k = super.findViewById(2131364514);
        super.l = ((android.widget.TextView) super.findViewById(2131364515));
        super.m = super.findViewById(2131364517);
        super.n = ((android.widget.TextView) super.findViewById(2131364518));
        return;
    }

    public final void setAppNameText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            int v0_1 = this.b;
            if (v0_1 != 0) {
                v0_1.setText(p2);
            }
            android.view.View v2_1 = this.a;
            if (v2_1 != null) {
                v2_1.setVisibility(0);
            }
        }
        return;
    }

    public final void setItemDetail(java.util.List p5)
    {
        java.util.Iterator v5_3;
        this.getContext();
        int v0 = 0;
        if (p5 != null) {
            int v1_3 = new java.util.ArrayList();
            android.view.View v2_0 = 0;
            while (v2_0 < p5.size()) {
                if (p5.get(v2_0) != null) {
                    throw new ClassCastException();
                } else {
                    v2_0++;
                }
            }
            v5_3 = v1_3;
        } else {
            v5_3 = 0;
        }
        if ((v5_3 == null) || (v5_3.size() <= 0)) {
            int v1_1 = 0;
        } else {
            v1_1 = 1;
        }
        if (v1_1 == 0) {
            java.util.Iterator v5_5 = this.c;
            if (v5_5 != null) {
                if (v1_1 == 0) {
                    v0 = 8;
                }
                v5_5.setVisibility(v0);
            }
        } else {
            android.view.View v2_1 = this.c;
            if (v2_1 != null) {
                v2_1.removeAllViews();
            }
            if (v5_3 != null) {
                java.util.Iterator v5_4 = v5_3.iterator();
                while (v5_4.hasNext()) {
                    android.view.View v2_3 = v5_4.next();
                    android.widget.LinearLayout v3_1 = this.c;
                    if (v3_1 != null) {
                        v3_1.addView(((android.view.View) v2_3));
                    }
                }
            }
        }
        return;
    }

    public final void setOriginAmountText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            int v0_1 = this.e;
            if (v0_1 != 0) {
                v0_1.setText(p2);
            }
            android.view.View v2_1 = this.d;
            if (v2_1 != null) {
                v2_1.setVisibility(0);
            }
        }
        return;
    }

    public final void setTransFeeText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            int v0_1 = this.j;
            if (v0_1 != 0) {
                v0_1.setText(p2);
            }
            android.view.View v2_1 = this.i;
            if (v2_1 != null) {
                v2_1.setVisibility(0);
            }
        }
        return;
    }

    public final void setTransIdText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            int v0_1 = this.l;
            if (v0_1 != 0) {
                v0_1.setText(p2);
            }
            android.view.View v2_1 = this.k;
            if (v2_1 != null) {
                v2_1.setVisibility(0);
            }
        }
        return;
    }

    public final void setTransTimeText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            int v0_1 = this.n;
            if (v0_1 != 0) {
                v0_1.setText(p2);
            }
            android.view.View v2_1 = this.m;
            if (v2_1 != null) {
                v2_1.setVisibility(0);
            }
        }
        return;
    }

    public final void setVoucherDiscountText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            int v0_1 = this.g;
            if (v0_1 != 0) {
                v0_1.setText(p2);
            }
            android.view.View v2_1 = this.f;
            if (v2_1 != null) {
                v2_1.setVisibility(0);
            }
        }
        return;
    }

    public final void setVoucherTitleText(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            android.widget.TextView v0_1 = this.h;
            if (v0_1 != null) {
                v0_1.setText(p2);
            }
        }
        return;
    }
}

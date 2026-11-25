package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class InputFieldContainerView extends android.widget.LinearLayout {

    public InputFieldContainerView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        return;
    }

    public final void dispatchRestoreInstanceState(android.util.SparseArray p1)
    {
        this.dispatchThawSelfOnly(p1);
        return;
    }

    public final void dispatchSaveInstanceState(android.util.SparseArray p1)
    {
        this.dispatchFreezeSelfOnly(p1);
        return;
    }

    public final void onRestoreInstanceState(android.os.Parcelable p5)
    {
        if (!(p5 instanceof Po.r)) {
            super.onRestoreInstanceState(p5);
            return;
        } else {
            super.onRestoreInstanceState(((Po.r) p5).getSuperState());
            java.util.Map v5_3 = ((Po.r) p5).a;
            if (v5_3 != null) {
                java.util.Map v5_1 = v5_3.a;
                java.util.Iterator v0_3 = new androidx.core.view.B0(this).iterator();
                int v1_0 = 0;
                while(true) {
                    android.view.View v2_1 = ((androidx.core.view.D0) v0_3);
                    if (!v2_1.hasNext()) {
                        break;
                    }
                    android.view.View v2_2 = v2_1.next();
                    int v3_1 = (v1_0 + 1);
                    if (v1_0 < 0) {
                        kotlin.collections.E.throwIndexOverflow();
                    }
                    ((android.view.View) v2_2).restoreHierarchyState(((android.util.SparseArray) v5_1.get(Integer.valueOf(v1_0))));
                    v1_0 = v3_1;
                }
            }
            return;
        }
    }

    public final android.os.Parcelable onSaveInstanceState()
    {
        Po.r v0_1 = new Po.r(super.onSaveInstanceState());
        java.util.Map v1_3 = new java.util.LinkedHashMap();
        Po.p v2_4 = new androidx.core.view.B0(this).iterator();
        int v3_0 = 0;
        while(true) {
            android.view.View v4_1 = ((androidx.core.view.D0) v2_4);
            if (!v4_1.hasNext()) {
                break;
            }
            android.view.View v4_2 = v4_1.next();
            int v5_1 = (v3_0 + 1);
            if (v3_0 < 0) {
                kotlin.collections.E.throwIndexOverflow();
            }
            android.view.View v4_3 = ((android.view.View) v4_2);
            android.util.SparseArray v6_1 = new android.util.SparseArray();
            v4_3.saveHierarchyState(v6_1);
            v1_3.put(Integer.valueOf(v3_0), v6_1);
            v3_0 = v5_1;
        }
        v0_1.a = new Po.p(okhttp3.internal.Util.toImmutableMap(v1_3));
        return v0_1;
    }
}

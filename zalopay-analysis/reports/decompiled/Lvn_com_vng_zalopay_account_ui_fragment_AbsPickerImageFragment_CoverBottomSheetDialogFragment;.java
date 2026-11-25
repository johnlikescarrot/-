package vn.com.vng.zalopay.account.ui.fragment;
public class AbsPickerImageFragment$CoverBottomSheetDialogFragment extends V9.h {
    public final vn.com.vng.zalopay.account.ui.fragment.a H;

    public AbsPickerImageFragment$CoverBottomSheetDialogFragment()
    {
        this.H = new vn.com.vng.zalopay.account.ui.fragment.a(this);
        return;
    }

    public void onClickCancel()
    {
        this.m1();
        return;
    }

    public void onClickCapture()
    {
        this.m1();
        return;
    }

    public void onClickGallery()
    {
        this.m1();
        return;
    }

    public final void u1(android.app.Dialog p3, int p4)
    {
        super.u1(p3, p4);
        vn.com.vng.zalopay.account.ui.fragment.a v4_4 = android.view.View.inflate(this.getContext(), 2131558517, 0);
        p3.setContentView(v4_4);
        butterknife.ButterKnife.a(v4_4, this);
        com.google.android.material.bottomsheet.BottomSheetBehavior v3_5 = ((V1.c) ((android.view.View) v4_4.getParent()).getLayoutParams()).a;
        if ((v3_5 instanceof com.google.android.material.bottomsheet.BottomSheetBehavior)) {
            ((com.google.android.material.bottomsheet.BottomSheetBehavior) v3_5).z(this.H);
        }
        return;
    }
}

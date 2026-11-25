package vn.com.vng.zalopay.account.ui.fragment;
public class AbsPickerImageFragment$CoverBottomSheetDialogFragment_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.account.ui.fragment.AbsPickerImageFragment$CoverBottomSheetDialogFragment b;
    public android.view.View c;
    public android.view.View d;
    public android.view.View e;

    public AbsPickerImageFragment$CoverBottomSheetDialogFragment_ViewBinding(vn.com.vng.zalopay.account.ui.fragment.AbsPickerImageFragment$CoverBottomSheetDialogFragment p3, android.view.View p4)
    {
        this.b = p3;
        vn.com.vng.zalopay.account.ui.fragment.d v0_6 = b4.e.b(p4, 2131363364, wmoi.igob.ggcn(11670));
        this.c = v0_6;
        v0_6.setOnClickListener(new vn.com.vng.zalopay.account.ui.fragment.b(p3));
        vn.com.vng.zalopay.account.ui.fragment.d v0_1 = b4.e.b(p4, 2131363371, wmoi.igob.ggcn(11671));
        this.d = v0_1;
        v0_1.setOnClickListener(new vn.com.vng.zalopay.account.ui.fragment.c(p3));
        android.view.View v4_1 = b4.e.b(p4, 2131363363, wmoi.igob.ggcn(11672));
        this.e = v4_1;
        v4_1.setOnClickListener(new vn.com.vng.zalopay.account.ui.fragment.d(p3));
        return;
    }

    public final void a()
    {
        if (this.b == null) {
            throw new IllegalStateException(wmoi.igob.ggcn(11673));
        } else {
            this.b = 0;
            this.c.setOnClickListener(0);
            this.c = 0;
            this.d.setOnClickListener(0);
            this.d = 0;
            this.e.setOnClickListener(0);
            this.e = 0;
            return;
        }
    }
}

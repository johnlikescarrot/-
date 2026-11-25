package vn.com.vng.zalopay.account.ui.fragment;
public abstract class AbsPickerImageFragment extends Ts.f {
    public int v;

    public AbsPickerImageFragment()
    {
        return;
    }

    public void r1(int p3, boolean p4)
    {
        if (p4 != null) {
            if (p3 == 14) {
                int v3_1 = this.v;
                if (v3_1 > 0) {
                    if (v3_1 != 153) {
                        this.v = v3_1;
                        if (this.q1(new String[] {wmoi.igob.ggcn(2236)}), 14)) {
                            android.content.Intent v4_4 = new android.content.Intent(wmoi.igob.ggcn(2237), android.provider.MediaStore$Images$Media.INTERNAL_CONTENT_URI);
                            v4_4.setType(wmoi.igob.ggcn(2238));
                            android.content.ComponentName v0_6 = this.getActivity();
                            if ((v0_6 == null) || ((v0_6.isFinishing()) || (v4_4.resolveActivity(v0_6.getPackageManager()) == null))) {
                                this.p1(2131953770);
                                return;
                            } else {
                                this.startActivityForResult(v4_4, v3_1);
                                return;
                            }
                        }
                    } else {
                        this.s1(v3_1);
                        return;
                    }
                }
            }
            return;
        } else {
            this.v = 0;
            return;
        }
    }

    public final void s1(int p4)
    {
        this.v = p4;
        if (this.q1(new String[] {wmoi.igob.ggcn(2239)}), 14)) {
            android.content.Intent v0_2 = new android.content.Intent();
            v0_2.setType(wmoi.igob.ggcn(2240));
            v0_2.setAction(wmoi.igob.ggcn(2241));
            v0_2.putExtra(wmoi.igob.ggcn(2242), 1);
            android.content.ComponentName v1_6 = this.getActivity();
            if ((v1_6 != null) && (!v1_6.isFinishing())) {
                if (v0_2.resolveActivity(v1_6.getPackageManager()) == null) {
                    this.p1(2131953770);
                } else {
                    this.startActivityForResult(v0_2, p4);
                    return;
                }
            }
        }
        return;
    }
}

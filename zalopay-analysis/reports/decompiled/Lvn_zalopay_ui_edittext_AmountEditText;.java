package vn.zalopay.ui.edittext;
public class AmountEditText extends yZ.b {
    public vn.zalopay.ui.edittext.AmountEditText$a F0;

    public AmountEditText(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        super.E0 = 0;
        if (!super.w) {
            super.setShowSoftInputOnFocus(0);
        }
        android.text.InputFilter[] v0_1 = new android.text.InputFilter[1];
        v0_1[0] = new yZ.a();
        super.setFilters(v0_1);
        super.addTextChangedListener(new yZ.d(super));
        return;
    }

    public final boolean p()
    {
        vn.zalopay.ui.edittext.AmountEditText$a v0 = this.F0;
        if (v0 != null) {
            v0.a();
        }
        return 1;
    }

    public void setChangeTextListener(vn.zalopay.ui.edittext.AmountEditText$a p1)
    {
        this.F0 = p1;
        return;
    }
}

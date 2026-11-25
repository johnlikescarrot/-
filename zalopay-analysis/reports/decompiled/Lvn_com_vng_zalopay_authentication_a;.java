package vn.com.vng.zalopay.authentication;
public final class a implements java.lang.Runnable {
    public final synthetic vn.com.vng.zalopay.authentication.AuthenticationDialog a;

    public a(vn.com.vng.zalopay.authentication.AuthenticationDialog p1)
    {
        this.a = p1;
        return;
    }

    public final void run()
    {
        CharSequence v0_0 = this.a;
        android.widget.TextView v1_0 = v0_0.mTvDecryptView;
        if (v1_0 != null) {
            v1_0.setTextColor(v0_0.getActivity().getColor(2131099792));
            if (!android.text.TextUtils.isEmpty(v0_0.a)) {
                v0_0.mTvDecryptView.setText(v0_0.a);
                return;
            } else {
                v0_0.mTvDecryptView.setText(v0_0.getString(2131952858));
                return;
            }
        } else {
            return;
        }
    }
}

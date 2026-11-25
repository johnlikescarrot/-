package vn.com.vng.zalopay.authentication;
public class AuthenticationDialog_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.authentication.AuthenticationDialog b;
    public android.view.View c;
    public android.view.View d;

    public AuthenticationDialog_ViewBinding(vn.com.vng.zalopay.authentication.AuthenticationDialog p5, android.view.View p6)
    {
        this.b = p5;
        Class v0_6 = b4.e.b(p6, 2131362242, "field \'mCancelButton\' and method \'onClickCancel\'");
        p5.mCancelButton = ((android.widget.Button) b4.e.a(v0_6, 2131362242, "field \'mCancelButton\'", android.widget.Button));
        this.c = v0_6;
        v0_6.setOnClickListener(new vn.com.vng.zalopay.authentication.b(p5, 0));
        Class v0_2 = b4.e.b(p6, 2131364191, "field \'mSecondDialogButton\' and method \'onOnClickButtonSecond\'");
        p5.mSecondDialogButton = ((android.widget.Button) b4.e.a(v0_2, 2131364191, "field \'mSecondDialogButton\'", android.widget.Button));
        this.d = v0_2;
        v0_2.setOnClickListener(new vn.com.vng.zalopay.authentication.b(p5, 1));
        p5.mFingerprintDecrypt = b4.e.b(p6, 2131362754, "field \'mFingerprintDecrypt\'");
        p5.mTvDecryptView = ((android.widget.TextView) b4.e.a(b4.e.b(p6, 2131362759, "field \'mTvDecryptView\'"), 2131362759, "field \'mTvDecryptView\'", android.widget.TextView));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.mCancelButton = 0;
            v0_0.mSecondDialogButton = 0;
            v0_0.mFingerprintDecrypt = 0;
            v0_0.mTvDecryptView = 0;
            this.c.setOnClickListener(0);
            this.c = 0;
            this.d.setOnClickListener(0);
            this.d = 0;
            return;
        }
    }
}

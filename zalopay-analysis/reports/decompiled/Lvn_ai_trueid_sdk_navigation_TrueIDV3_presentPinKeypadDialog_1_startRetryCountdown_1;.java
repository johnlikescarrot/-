package vn.ai.trueid.sdk.navigation;
public final class TrueIDV3$presentPinKeypadDialog$1$startRetryCountdown$1 extends android.os.CountDownTimer {
    public final synthetic String a;
    public final synthetic android.widget.TextView b;
    public final synthetic kotlin.jvm.internal.H c;
    public final synthetic android.widget.LinearLayout d;

    public TrueIDV3$presentPinKeypadDialog$1$startRetryCountdown$1(long p1, android.widget.LinearLayout p3, android.widget.TextView p4, String p5, kotlin.jvm.internal.H p6)
    {
        this.a = p5;
        this.b = p4;
        this.c = p6;
        this.d = p3;
        super(p1, 1000);
        return;
    }

    public void onFinish()
    {
        this.c.a = 0;
        this.d.setAlpha(1065353216);
        this.b.setText(this.a);
        return;
    }

    public void onTick(long p3)
    {
        String v0_1;
        int v3_7 = ((int) Math.ceil((((double) p3) / 4652007308841189376)));
        android.widget.TextView v4_2 = this.a;
        if (v4_2.length() != 0) {
            v0_1 = " ";
        } else {
            v0_1 = "";
        }
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append(v4_2);
        v1_1.append(v0_1);
        v1_1.append("Th\u1eed l\u1ea1i sau ");
        v1_1.append(v3_7);
        v1_1.append("s.");
        android.widget.TextView v4_1 = this.b;
        v4_1.setText(v1_1.toString());
        v4_1.setVisibility(0);
        return;
    }
}

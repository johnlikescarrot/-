package vn.ai.trueid.sdk.face;
public abstract class e0 {

    public static final void a(int p3, android.widget.Button p4)
    {
        android.graphics.PorterDuffColorFilter v3_1;
        android.graphics.drawable.Drawable v4_1 = p4.getBackground();
        android.graphics.PorterDuff$Mode v0_1 = androidx.core.graphics.b.j;
        if (android.os.Build$VERSION.SDK_INT < 29) {
            android.graphics.PorterDuff$Mode v0_2 = androidx.core.graphics.m.k(v0_1);
            if (v0_2 == null) {
                v3_1 = 0;
            } else {
                v3_1 = new android.graphics.PorterDuffColorFilter(p3, v0_2);
            }
        } else {
            android.graphics.PorterDuff$Mode v0_0 = androidx.core.graphics.a.b(v0_1);
            if (v0_0 == null) {
            } else {
                v3_1 = androidx.core.graphics.a.a(p3, v0_0);
            }
        }
        v4_1.setColorFilter(v3_1);
        return;
    }
}

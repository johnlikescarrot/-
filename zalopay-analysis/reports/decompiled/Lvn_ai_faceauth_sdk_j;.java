package vn.ai.faceauth.sdk;
public abstract class j implements vn.ai.faceauth.sdk.i {
    public final android.app.ActivityManager a;
    public final java.util.Timer b;
    public final vn.ai.faceauth.sdk.g c;
    public int d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    public long j;
    public int k;

    static j()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(25);
        return;
    }

    public j(android.content.Context p7)
    {
        this.a = ((android.app.ActivityManager) p7.getSystemService(jipm.njcx.zbjt(18)));
        java.util.Timer v0_3 = new java.util.Timer();
        this.b = v0_3;
        this.c = new vn.ai.faceauth.sdk.g(com.google.android.gms.tasks.f.a);
        this.g = 9223372036854775807;
        this.j = 9223372036854775807;
        v0_3.scheduleAtFixedRate(new vn.ai.faceauth.sdk.j$a(this), 0, 1000);
        return;
    }

    public static final native void a(long p0, long p1, vn.ai.faceauth.sdk.j p2, Object p3);

    public static final native void a(vn.ai.faceauth.sdk.j p0, Exception p1);

    public final native com.google.android.gms.tasks.Task a(com.google.android.gms.tasks.Task p0, long p1);

    public final native void a(android.graphics.Bitmap p0);
}

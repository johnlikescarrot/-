package vn.zalopay.ui.androidsvg;
public final class D0 extends android.os.AsyncTask {
    public final android.content.Context a;
    public final int b;
    public final synthetic vn.zalopay.ui.androidsvg.E0 c;

    public D0(vn.zalopay.ui.androidsvg.E0 p1, android.content.Context p2, int p3)
    {
        this.c = p1;
        this.a = p2;
        this.b = p3;
        return;
    }

    public final Object doInBackground(Object[] p3)
    {
        Object[] v3_1 = this.b;
        try {
            Thread.currentThread().setPriority(10);
            String v0_7 = this.a.getResources();
            Throwable v1_0 = new vn.zalopay.ui.androidsvg.M0();
            String v0_0 = v0_7.openRawResource(v3_1);
            try {
                Throwable v1_1 = v1_0.f(v0_0);
            } catch (Throwable v1_2) {
                v0_0.close();
                throw v1_2;
            }
            v0_0.close();
            return v1_1;
        } catch (String v0_1) {
            ck.b.e("SVGImageView", new Object[] {String.format("Error loading resource 0x%x: %s", new Object[] {Integer.valueOf(v3_1), v0_1.getMessage()}))}));
            v1_1 = 0;
            return v1_1;
        } catch (java.io.IOException) {
            return v1_1;
        }
    }

    public final void onPostExecute(Object p2)
    {
        vn.zalopay.ui.androidsvg.E0 v0 = this.c;
        v0.a = ((vn.zalopay.ui.androidsvg.t0) p2);
        v0.d();
        return;
    }
}

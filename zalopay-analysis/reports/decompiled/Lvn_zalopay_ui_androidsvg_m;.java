package vn.zalopay.ui.androidsvg;
public final class m {
    public java.util.ArrayList a;

    public m()
    {
        this.a = 0;
        return;
    }

    public final void a(vn.zalopay.ui.androidsvg.l p4)
    {
        if (this.a == null) {
            this.a = new java.util.ArrayList();
        }
        int v0_3 = 0;
        while (v0_3 < this.a.size()) {
            if (((vn.zalopay.ui.androidsvg.l) this.a.get(v0_3)).a.b <= p4.a.b) {
                v0_3++;
            } else {
                this.a.add(v0_3, p4);
                return;
            }
        }
        this.a.add(p4);
        return;
    }

    public final void b(vn.zalopay.ui.androidsvg.m p3)
    {
        if (p3.a != null) {
            if (this.a == null) {
                this.a = new java.util.ArrayList(p3.a.size());
            }
            java.util.Iterator v3_2 = p3.a.iterator();
            while (v3_2.hasNext()) {
                this.a(((vn.zalopay.ui.androidsvg.l) v3_2.next()));
            }
        }
        return;
    }

    public final String toString()
    {
        if (this.a != null) {
            String v0_4 = new StringBuilder();
            java.util.Iterator v1_1 = this.a.iterator();
            while (v1_1.hasNext()) {
                v0_4.append(((vn.zalopay.ui.androidsvg.l) v1_1.next()).toString());
                v0_4.append(10);
            }
            return v0_4.toString();
        } else {
            return "";
        }
    }
}

package vn.zalopay.ui.androidsvg;
public abstract class X extends vn.zalopay.ui.androidsvg.Z implements vn.zalopay.ui.androidsvg.Y, vn.zalopay.ui.androidsvg.W {
    public java.util.List i;
    public java.util.HashSet j;
    public String k;
    public java.util.HashSet l;
    public java.util.HashSet m;

    public X()
    {
        this.i = new java.util.ArrayList();
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        return;
    }

    public final String a()
    {
        return this.k;
    }

    public final void c(java.util.HashSet p1)
    {
        this.l = p1;
        return;
    }

    public final java.util.Set d()
    {
        return this.l;
    }

    public final java.util.Set e()
    {
        return 0;
    }

    public final void f(java.util.HashSet p1)
    {
        this.j = p1;
        return;
    }

    public final java.util.Set g()
    {
        return this.j;
    }

    public final java.util.List getChildren()
    {
        return this.i;
    }

    public final void h(java.util.HashSet p1)
    {
        return;
    }

    public final void i(java.util.HashSet p1)
    {
        this.m = p1;
        return;
    }

    public final void j(String p1)
    {
        this.k = p1;
        return;
    }

    public final java.util.Set l()
    {
        return this.m;
    }

    public void m(vn.zalopay.ui.androidsvg.c0 p2)
    {
        this.i.add(p2);
        return;
    }
}

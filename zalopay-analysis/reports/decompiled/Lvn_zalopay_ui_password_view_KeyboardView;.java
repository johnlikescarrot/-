package vn.zalopay.ui.password.view;
public class KeyboardView extends android.widget.LinearLayout implements android.view.View$OnClickListener {
    public WZ.f a;
    public boolean b;
    public final java.util.ArrayList c;

    public KeyboardView(android.content.Context p2, android.util.AttributeSet p3)
    {
        this(p2, p3, 0);
        return;
    }

    public KeyboardView(android.content.Context p3, android.util.AttributeSet p4, int p5)
    {
        super(p3, p4, 0);
        super.b = 0;
        if ((p4 != null) && (!super.isInEditMode())) {
            p3.getTheme().obtainStyledAttributes(p4, sZ.h.l, 0, 0);
            java.util.Iterator v3_4 = ((vn.zalopay.ui.password.view.KeyboardView) ((android.view.LayoutInflater) p3.getSystemService("layout_inflater")).inflate(2131560380, super));
            android.view.View v4_6 = new java.util.ArrayList();
            super.c = v4_6;
            v4_6.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363841)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363842)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363843)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363844)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363845)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363846)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363847)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363848)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363849)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363850)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363851)));
            super.c.add(((vn.zalopay.ui.password.view.KeyboardButtonView) v3_4.findViewById(2131363852)));
            java.util.Iterator v3_8 = super.c.iterator();
            while (v3_8.hasNext()) {
                ((android.view.View) v3_8.next()).setOnClickListener(super);
            }
        }
        return;
    }

    public final void a(boolean p3)
    {
        java.util.Iterator v0_1 = this.c.iterator();
        while (v0_1.hasNext()) {
            ((vn.zalopay.ui.password.view.KeyboardButtonView) v0_1.next()).setClickable(p3);
        }
        return;
    }

    public final void onClick(android.view.View p2)
    {
        if (this.a != null) {
            WZ.f v2_2 = p2.getId();
            if (v2_2 != 2131363841) {
                if (v2_2 != 2131363842) {
                    if (v2_2 != 2131363843) {
                        if (v2_2 != 2131363844) {
                            if (v2_2 != 2131363845) {
                                if (v2_2 != 2131363846) {
                                    if (v2_2 != 2131363847) {
                                        if (v2_2 != 2131363848) {
                                            if (v2_2 != 2131363849) {
                                                if (v2_2 != 2131363850) {
                                                    if (v2_2 != 2131363851) {
                                                        if ((v2_2 == 2131363852) && (this.b)) {
                                                            this.a.v(11);
                                                        }
                                                    } else {
                                                        this.a.v(12);
                                                        return;
                                                    }
                                                } else {
                                                    this.a.v(10);
                                                    return;
                                                }
                                            } else {
                                                this.a.v(9);
                                                return;
                                            }
                                        } else {
                                            this.a.v(8);
                                            return;
                                        }
                                    } else {
                                        this.a.v(7);
                                        return;
                                    }
                                } else {
                                    this.a.v(6);
                                    return;
                                }
                            } else {
                                this.a.v(5);
                                return;
                            }
                        } else {
                            this.a.v(4);
                            return;
                        }
                    } else {
                        this.a.v(3);
                        return;
                    }
                } else {
                    this.a.v(2);
                    return;
                }
            } else {
                this.a.v(1);
                return;
            }
        }
        return;
    }

    public void setEnableButtonFingerPrint(boolean p3)
    {
        this.b = 1;
        ((vn.zalopay.ui.password.view.KeyboardButtonView) this.c.get(11)).setEnableRipple(p3);
        return;
    }

    public void setImageDrawableButtonClear(android.graphics.drawable.Drawable p3)
    {
        ((vn.zalopay.ui.password.view.KeyboardButtonView) this.c.get(10)).setImageDrawableButton(p3);
        return;
    }

    public void setImageDrawableButtonFingerPrint(android.graphics.drawable.Drawable p3)
    {
        ((vn.zalopay.ui.password.view.KeyboardButtonView) this.c.get(11)).setImageDrawableButton(p3);
        return;
    }

    public void setKeyboardButtonClickedListener(WZ.f p3)
    {
        this.a = p3;
        java.util.Iterator v3_2 = this.c.iterator();
        while (v3_2.hasNext()) {
            ((vn.zalopay.ui.password.view.KeyboardButtonView) v3_2.next()).setOnRippleAnimationEndListener(this.a);
        }
        return;
    }

    public void setTextNumberColor(int p3)
    {
        java.util.Iterator v0_1 = this.c.iterator();
        while (v0_1.hasNext()) {
            ((vn.zalopay.ui.password.view.KeyboardButtonView) v0_1.next()).setTextNumberColor(p3);
        }
        return;
    }
}

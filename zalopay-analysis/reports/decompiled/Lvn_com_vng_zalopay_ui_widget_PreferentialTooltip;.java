package vn.com.vng.zalopay.ui.widget;
public final class PreferentialTooltip extends android.view.View {
    public final float a;
    public android.graphics.RectF b;
    public final android.graphics.RectF c;
    public final android.graphics.Paint d;
    public final android.graphics.Paint e;
    public final android.graphics.Paint f;
    public final android.graphics.Paint g;
    public final android.graphics.Paint h;

    public PreferentialTooltip(android.content.Context p6, android.util.AttributeSet p7)
    {
        super(p6, p7);
        m9.A.b(1092616192, super.getContext());
        super.a = ((float) m9.A.b(1077936128, super.getContext()));
        int v6_5 = ((float) m9.A.b(1065353216, super.getContext()));
        float v7_7 = ((float) ((int) android.util.TypedValue.applyDimension(2, 1096810496, super.getContext().getResources().getDisplayMetrics())));
        super.b = new android.graphics.RectF();
        super.c = new android.graphics.RectF();
        android.graphics.Paint v1_6 = new android.graphics.Paint(1);
        v1_6.setColor(super.getContext().getColor(2131099915));
        android.graphics.Paint$Style v3_2 = android.graphics.Paint$Style.FILL;
        v1_6.setStyle(v3_2);
        super.d = v1_6;
        android.graphics.Paint v1_8 = new android.graphics.Paint(1);
        v1_8.setStyle(v3_2);
        v1_8.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
        super.e = v1_8;
        android.graphics.Paint v1_10 = new android.graphics.Paint(1);
        android.graphics.Paint$Style v3_5 = android.graphics.Paint$Style.STROKE;
        v1_10.setStyle(v3_5);
        v1_10.setStrokeWidth(v6_5);
        v1_10.setColor(-1);
        super.f = v1_10;
        android.graphics.Paint v1_12 = new android.graphics.Paint(1);
        v1_12.setStyle(v3_5);
        v1_12.setStrokeWidth((((float) 2) * v6_5));
        v1_12.setColor(-1);
        super.g = v1_12;
        int v6_8 = new android.graphics.Paint(1);
        v6_8.setColor(-1);
        v6_8.setTextAlign(android.graphics.Paint$Align.CENTER);
        v6_8.setTextSize(v7_7);
        super.h = v6_8;
        super.setLayerType(1, 0);
        return;
    }

    public final void onDraw(android.graphics.Canvas p20)
    {
        p20.drawPaint(this.d);
        float v3_8 = this.c;
        float v4_12 = this.a;
        p20.drawRoundRect(v3_8, v4_12, v4_12, this.e);
        float v2_12 = (((float) (this.getWidth() / 2)) - ((float) m9.A.b(1113325568, this.getContext())));
        android.graphics.Paint v6_25 = (((float) (this.getWidth() / 2)) + ((float) m9.A.b(1113325568, this.getContext())));
        android.graphics.PointF v7_7 = (((float) this.getHeight()) - ((float) m9.A.b(1114112000, this.getContext())));
        android.graphics.PointF v8_17 = (((float) this.getHeight()) - ((float) m9.A.b(1103101952, this.getContext())));
        android.graphics.PointF v9_15 = new android.graphics.RectF(v2_12, v7_7, v6_25, v8_17);
        this.b = v9_15;
        p20.drawRoundRect(v9_15, v4_12, v4_12, this.f);
        float v4_11 = new android.graphics.Rect();
        android.graphics.PointF v9_17 = this.getContext().getString(2131954076);
        float v11_5 = this.h;
        v11_5.getTextBounds(v9_17, 0, v9_17.length(), v4_11);
        p20.drawText(v9_17, ((v2_12 + v6_25) / ((float) 2)), (((v7_7 + v8_17) / ((float) 2)) + ((float) (v4_11.height() / 2))), v11_5);
        float v2_23 = new android.graphics.Rect();
        float v4_17 = this.getContext().getString(2131954075);
        android.graphics.Paint v6_29 = this.getContext().getString(2131954077);
        v11_5.getTextBounds(v4_17, 0, v4_17.length(), v2_23);
        android.graphics.PointF v7_15 = ((float) (this.getWidth() / 2));
        android.graphics.PointF v8_0 = (v3_8.bottom + ((float) m9.A.b(1124204544, this.getContext())));
        p20.drawText(v4_17, v7_15, v8_0, v11_5);
        p20.drawText(v6_29, v7_15, ((((float) v2_23.height()) * 1069547520) + v8_0), v11_5);
        float v2_6 = m9.A.b(1103626240, this.getContext());
        float v4_3 = m9.A.b(1108738048, this.getContext());
        android.graphics.PointF v7_1 = new android.graphics.PointF(((float) (this.getWidth() / 2)), (v3_8.bottom + ((float) m9.A.b(1101004800, this.getContext()))));
        float v2_8 = ((float) v2_6);
        float v3_3 = new android.graphics.PointF(((((float) Math.cos(4612999014285018570)) * v2_8) + v7_1.x), ((((float) Math.sin(4612999014285018570)) * v2_8) + v7_1.y));
        android.graphics.PointF v8_10 = new android.graphics.PointF(((((float) Math.cos(4607237772390620796)) * v2_8) + v7_1.x), ((((float) Math.sin(4607237772390620796)) * v2_8) + v7_1.y));
        android.graphics.PointF v9_9 = new android.graphics.PointF(((((float) Math.cos(4611167904475081040)) * v2_8) + v7_1.x), ((v2_8 * ((float) Math.sin(4611167904475081040))) + v7_1.y));
        float v2_13 = new android.graphics.PointF(((float) (this.getWidth() / 2)), (v9_9.y + ((float) m9.A.b(1114636288, this.getContext()))));
        float v14_0 = ((double) v4_3);
        float v5_3 = new android.graphics.PointF(((float) ((Math.cos(4611167904475081040) * v14_0) + ((double) v9_9.x))), ((float) ((Math.sin(4611167904475081040) * v14_0) + ((double) v9_9.y))));
        android.graphics.Path v10_10 = new android.graphics.Path();
        v10_10.moveTo(v9_9.x, v9_9.y);
        v10_10.cubicTo(v9_9.x, v9_9.y, v5_3.x, v5_3.y, v2_13.x, v2_13.y);
        android.graphics.Paint v6_24 = this.g;
        p20.drawLine(v7_1.x, v7_1.y, v3_3.x, v3_3.y, v6_24);
        p20.drawLine(v7_1.x, v7_1.y, v8_10.x, v8_10.y, v6_24);
        p20.drawLine(v7_1.x, v7_1.y, v9_9.x, v9_9.y, v6_24);
        p20.drawPath(v10_10, v6_24);
        return;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p5)
    {
        if ((p5.getX() < this.b.left) || ((p5.getX() > this.b.right) || ((p5.getY() < this.b.top) || (p5.getY() > this.b.bottom)))) {
            float v2_5 = this.c;
            if ((p5.getX() < v2_5.left) || ((p5.getX() > v2_5.right) || ((p5.getY() < v2_5.top) || (p5.getY() > v2_5.bottom)))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            this.setVisibility(8);
            return 1;
        }
    }
}

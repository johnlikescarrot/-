package vn.ai.trueid.lottie.value;
public class ScaleXY {
    private float scaleX;
    private float scaleY;

    public ScaleXY()
    {
        this(1065353216, 1065353216);
        return;
    }

    public ScaleXY(float p1, float p2)
    {
        this.scaleX = p1;
        this.scaleY = p2;
        return;
    }

    public boolean equals(float p2, float p3)
    {
        if ((this.scaleX != p2) || (this.scaleY != p3)) {
            return 0;
        } else {
            return 1;
        }
    }

    public float getScaleX()
    {
        return this.scaleX;
    }

    public float getScaleY()
    {
        return this.scaleY;
    }

    public void set(float p1, float p2)
    {
        this.scaleX = p1;
        this.scaleY = p2;
        return;
    }

    public String toString()
    {
        String v0_1 = new StringBuilder();
        v0_1.append(this.getScaleX());
        v0_1.append("x");
        v0_1.append(this.getScaleY());
        return v0_1.toString();
    }
}

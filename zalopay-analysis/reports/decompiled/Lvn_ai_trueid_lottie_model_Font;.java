package vn.ai.trueid.lottie.model;
public class Font {
    private final float ascent;
    private final String family;
    private final String name;
    private final String style;
    private android.graphics.Typeface typeface;

    public Font(String p1, String p2, String p3, float p4)
    {
        this.family = p1;
        this.name = p2;
        this.style = p3;
        this.ascent = p4;
        return;
    }

    public float getAscent()
    {
        return this.ascent;
    }

    public String getFamily()
    {
        return this.family;
    }

    public String getName()
    {
        return this.name;
    }

    public String getStyle()
    {
        return this.style;
    }

    public android.graphics.Typeface getTypeface()
    {
        return this.typeface;
    }

    public void setTypeface(android.graphics.Typeface p1)
    {
        this.typeface = p1;
        return;
    }
}

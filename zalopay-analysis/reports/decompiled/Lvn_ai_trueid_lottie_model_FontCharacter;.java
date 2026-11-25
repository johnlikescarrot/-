package vn.ai.trueid.lottie.model;
public class FontCharacter {
    private final char character;
    private final String fontFamily;
    private final java.util.List shapes;
    private final double size;
    private final String style;
    private final double width;

    public FontCharacter(java.util.List p1, char p2, double p3, double p5, String p7, String p8)
    {
        this.shapes = p1;
        this.character = p2;
        this.size = p3;
        this.width = p5;
        this.style = p7;
        this.fontFamily = p8;
        return;
    }

    public static int hashFor(char p1, String p2, String p3)
    {
        return (p3.hashCode() + e5.i.c((p1 * 31), 31, p2));
    }

    public java.util.List getShapes()
    {
        return this.shapes;
    }

    public double getWidth()
    {
        return this.width;
    }

    public int hashCode()
    {
        return vn.ai.trueid.lottie.model.FontCharacter.hashFor(this.character, this.fontFamily, this.style);
    }
}

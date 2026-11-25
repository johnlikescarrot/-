package vn.ai.trueid.lottie.model.layer;
 class TextLayer$TextSubLine {
    private String text;
    private float width;

    private TextLayer$TextSubLine()
    {
        this.text = "";
        this.width = 0;
        return;
    }

    public synthetic TextLayer$TextSubLine(vn.ai.trueid.lottie.model.layer.TextLayer$1 p1)
    {
        return;
    }

    public static synthetic float access$000(vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine p0)
    {
        return p0.width;
    }

    public static synthetic String access$100(vn.ai.trueid.lottie.model.layer.TextLayer$TextSubLine p0)
    {
        return p0.text;
    }

    public void set(String p1, float p2)
    {
        this.text = p1;
        this.width = p2;
        return;
    }
}

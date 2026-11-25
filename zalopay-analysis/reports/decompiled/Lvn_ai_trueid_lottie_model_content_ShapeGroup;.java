package vn.ai.trueid.lottie.model.content;
public class ShapeGroup implements vn.ai.trueid.lottie.model.content.ContentModel {
    private final boolean hidden;
    private final java.util.List items;
    private final String name;

    public ShapeGroup(String p1, java.util.List p2, boolean p3)
    {
        this.name = p1;
        this.items = p2;
        this.hidden = p3;
        return;
    }

    public java.util.List getItems()
    {
        return this.items;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public vn.ai.trueid.lottie.animation.content.Content toContent(vn.ai.trueid.lottie.LottieDrawable p2, vn.ai.trueid.lottie.LottieComposition p3, vn.ai.trueid.lottie.model.layer.BaseLayer p4)
    {
        return new vn.ai.trueid.lottie.animation.content.ContentGroup(p2, p4, this, p3);
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("ShapeGroup{name=\'");
        v0_2.append(this.name);
        v0_2.append("\' Shapes: ");
        v0_2.append(java.util.Arrays.toString(this.items.toArray()));
        v0_2.append(125);
        return v0_2.toString();
    }
}

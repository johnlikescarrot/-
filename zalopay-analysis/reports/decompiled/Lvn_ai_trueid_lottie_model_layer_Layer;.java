package vn.ai.trueid.lottie.model.layer;
public class Layer {
    private final vn.ai.trueid.lottie.model.content.LBlendMode blendMode;
    private final vn.ai.trueid.lottie.model.content.BlurEffect blurEffect;
    private final vn.ai.trueid.lottie.LottieComposition composition;
    private final vn.ai.trueid.lottie.parser.DropShadowEffect dropShadowEffect;
    private final boolean hidden;
    private final java.util.List inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final vn.ai.trueid.lottie.model.layer.Layer$LayerType layerType;
    private final java.util.List masks;
    private final vn.ai.trueid.lottie.model.layer.Layer$MatteType matteType;
    private final long parentId;
    private final float preCompHeight;
    private final float preCompWidth;
    private final String refId;
    private final java.util.List shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startFrame;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableTextFrame text;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableTextProperties textProperties;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue timeRemapping;
    private final float timeStretch;
    private final vn.ai.trueid.lottie.model.animatable.AnimatableTransform transform;

    public Layer(java.util.List p1, vn.ai.trueid.lottie.LottieComposition p2, String p3, long p4, vn.ai.trueid.lottie.model.layer.Layer$LayerType p6, long p7, String p9, java.util.List p10, vn.ai.trueid.lottie.model.animatable.AnimatableTransform p11, int p12, int p13, int p14, float p15, float p16, float p17, float p18, vn.ai.trueid.lottie.model.animatable.AnimatableTextFrame p19, vn.ai.trueid.lottie.model.animatable.AnimatableTextProperties p20, java.util.List p21, vn.ai.trueid.lottie.model.layer.Layer$MatteType p22, vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue p23, boolean p24, vn.ai.trueid.lottie.model.content.BlurEffect p25, vn.ai.trueid.lottie.parser.DropShadowEffect p26, vn.ai.trueid.lottie.model.content.LBlendMode p27)
    {
        this.shapes = p1;
        this.composition = p2;
        this.layerName = p3;
        this.layerId = p4;
        this.layerType = p6;
        this.parentId = p7;
        this.refId = p9;
        this.masks = p10;
        this.transform = p11;
        this.solidWidth = p12;
        this.solidHeight = p13;
        this.solidColor = p14;
        this.timeStretch = p15;
        this.startFrame = p16;
        this.preCompWidth = p17;
        this.preCompHeight = p18;
        this.text = p19;
        this.textProperties = p20;
        this.inOutKeyframes = p21;
        this.matteType = p22;
        this.timeRemapping = p23;
        this.hidden = p24;
        this.blurEffect = p25;
        this.dropShadowEffect = p26;
        this.blendMode = p27;
        return;
    }

    public vn.ai.trueid.lottie.model.content.LBlendMode getBlendMode()
    {
        return this.blendMode;
    }

    public vn.ai.trueid.lottie.model.content.BlurEffect getBlurEffect()
    {
        return this.blurEffect;
    }

    public vn.ai.trueid.lottie.LottieComposition getComposition()
    {
        return this.composition;
    }

    public vn.ai.trueid.lottie.parser.DropShadowEffect getDropShadowEffect()
    {
        return this.dropShadowEffect;
    }

    public long getId()
    {
        return this.layerId;
    }

    public java.util.List getInOutKeyframes()
    {
        return this.inOutKeyframes;
    }

    public vn.ai.trueid.lottie.model.layer.Layer$LayerType getLayerType()
    {
        return this.layerType;
    }

    public java.util.List getMasks()
    {
        return this.masks;
    }

    public vn.ai.trueid.lottie.model.layer.Layer$MatteType getMatteType()
    {
        return this.matteType;
    }

    public String getName()
    {
        return this.layerName;
    }

    public long getParentId()
    {
        return this.parentId;
    }

    public float getPreCompHeight()
    {
        return this.preCompHeight;
    }

    public float getPreCompWidth()
    {
        return this.preCompWidth;
    }

    public String getRefId()
    {
        return this.refId;
    }

    public java.util.List getShapes()
    {
        return this.shapes;
    }

    public int getSolidColor()
    {
        return this.solidColor;
    }

    public int getSolidHeight()
    {
        return this.solidHeight;
    }

    public int getSolidWidth()
    {
        return this.solidWidth;
    }

    public float getStartProgress()
    {
        return (this.startFrame / this.composition.getDurationFrames());
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableTextFrame getText()
    {
        return this.text;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableTextProperties getTextProperties()
    {
        return this.textProperties;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableFloatValue getTimeRemapping()
    {
        return this.timeRemapping;
    }

    public float getTimeStretch()
    {
        return this.timeStretch;
    }

    public vn.ai.trueid.lottie.model.animatable.AnimatableTransform getTransform()
    {
        return this.transform;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    public String toString()
    {
        return this.toString("");
    }

    public String toString(String p7)
    {
        StringBuilder v0 = m4.d.o(p7);
        v0.append(this.getName());
        v0.append("\n");
        java.util.Iterator v2_1 = this.composition.layerModelForId(this.getParentId());
        if (v2_1 != null) {
            v0.append("\t\tParents: ");
            v0.append(v2_1.getName());
            java.util.Iterator v2_0 = this.composition.layerModelForId(v2_1.getParentId());
            while (v2_0 != null) {
                v0.append("->");
                v0.append(v2_0.getName());
                v2_0 = this.composition.layerModelForId(v2_0.getParentId());
            }
            v0.append(p7);
            v0.append("\n");
        }
        if (!this.getMasks().isEmpty()) {
            v0.append(p7);
            v0.append("\tMasks: ");
            v0.append(this.getMasks().size());
            v0.append("\n");
        }
        if ((this.getSolidWidth() != 0) && (this.getSolidHeight() != 0)) {
            v0.append(p7);
            v0.append("\tBackground: ");
            v0.append(String.format(java.util.Locale.US, "%dx%d %X\n", new Object[] {Integer.valueOf(this.getSolidWidth()), Integer.valueOf(this.getSolidHeight()), Integer.valueOf(this.getSolidColor())})));
        }
        if (!this.shapes.isEmpty()) {
            v0.append(p7);
            v0.append("\tShapes:\n");
            java.util.Iterator v2_17 = this.shapes.iterator();
            while (v2_17.hasNext()) {
                Object v3_7 = v2_17.next();
                v0.append(p7);
                v0.append("\t\t");
                v0.append(v3_7);
                v0.append("\n");
            }
        }
        return v0.toString();
    }
}

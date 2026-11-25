package vn.ai.trueid.lottie.model.animatable;
public class AnimatableTextRangeSelector {
    public final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue end;
    public final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue offset;
    public final vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue start;
    public final vn.ai.trueid.lottie.model.content.TextRangeUnits units;

    public AnimatableTextRangeSelector(vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p1, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p2, vn.ai.trueid.lottie.model.animatable.AnimatableIntegerValue p3, vn.ai.trueid.lottie.model.content.TextRangeUnits p4)
    {
        this.start = p1;
        this.end = p2;
        this.offset = p3;
        this.units = p4;
        return;
    }
}

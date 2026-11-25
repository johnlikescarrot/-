package vn.ai.trueid.lottie.value;
public class LottieFrameInfo {
    private float endFrame;
    private Object endValue;
    private float interpolatedKeyframeProgress;
    private float linearKeyframeProgress;
    private float overallProgress;
    private float startFrame;
    private Object startValue;

    public LottieFrameInfo()
    {
        return;
    }

    public float getEndFrame()
    {
        return this.endFrame;
    }

    public Object getEndValue()
    {
        return this.endValue;
    }

    public float getInterpolatedKeyframeProgress()
    {
        return this.interpolatedKeyframeProgress;
    }

    public float getLinearKeyframeProgress()
    {
        return this.linearKeyframeProgress;
    }

    public float getOverallProgress()
    {
        return this.overallProgress;
    }

    public float getStartFrame()
    {
        return this.startFrame;
    }

    public Object getStartValue()
    {
        return this.startValue;
    }

    public vn.ai.trueid.lottie.value.LottieFrameInfo set(float p1, float p2, Object p3, Object p4, float p5, float p6, float p7)
    {
        this.startFrame = p1;
        this.endFrame = p2;
        this.startValue = p3;
        this.endValue = p4;
        this.linearKeyframeProgress = p5;
        this.interpolatedKeyframeProgress = p6;
        this.overallProgress = p7;
        return this;
    }
}

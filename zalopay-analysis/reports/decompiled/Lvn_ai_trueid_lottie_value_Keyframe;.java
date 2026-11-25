package vn.ai.trueid.lottie.value;
public class Keyframe {
    private static final float UNSET_FLOAT = 0;
    private static final int UNSET_INT = 784923401;
    private final vn.ai.trueid.lottie.LottieComposition composition;
    public Float endFrame;
    private float endProgress;
    public Object endValue;
    private float endValueFloat;
    private int endValueInt;
    public final android.view.animation.Interpolator interpolator;
    public android.graphics.PointF pathCp1;
    public android.graphics.PointF pathCp2;
    public final float startFrame;
    private float startProgress;
    public final Object startValue;
    private float startValueFloat;
    private int startValueInt;
    public final android.view.animation.Interpolator xInterpolator;
    public final android.view.animation.Interpolator yInterpolator;

    static Keyframe()
    {
        wmoi.igob.jtgo(vn.ai.trueid.lottie.value.Keyframe, 17076, 17076);
        return;
    }

    public Keyframe(Object p3)
    {
        this.startValueFloat = -898407689;
        this.endValueFloat = -898407689;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = 1;
        this.endProgress = 1;
        this.pathCp1 = 0;
        this.pathCp2 = 0;
        this.composition = 0;
        this.startValue = p3;
        this.endValue = p3;
        this.interpolator = 0;
        this.xInterpolator = 0;
        this.yInterpolator = 0;
        this.startFrame = 1;
        this.endFrame = Float.valueOf(2139095039);
        return;
    }

    private Keyframe(Object p3, Object p4)
    {
        this.startValueFloat = -898407689;
        this.endValueFloat = -898407689;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = 1;
        this.endProgress = 1;
        this.pathCp1 = 0;
        this.pathCp2 = 0;
        this.composition = 0;
        this.startValue = p3;
        this.endValue = p4;
        this.interpolator = 0;
        this.xInterpolator = 0;
        this.yInterpolator = 0;
        this.startFrame = 1;
        this.endFrame = Float.valueOf(2139095039);
        return;
    }

    public Keyframe(vn.ai.trueid.lottie.LottieComposition p2, Object p3, Object p4, android.view.animation.Interpolator p5, float p6, Float p7)
    {
        this.startValueFloat = -898407689;
        this.endValueFloat = -898407689;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = 1;
        this.endProgress = 1;
        this.pathCp1 = 0;
        this.pathCp2 = 0;
        this.composition = p2;
        this.startValue = p3;
        this.endValue = p4;
        this.interpolator = p5;
        this.xInterpolator = 0;
        this.yInterpolator = 0;
        this.startFrame = p6;
        this.endFrame = p7;
        return;
    }

    public Keyframe(vn.ai.trueid.lottie.LottieComposition p2, Object p3, Object p4, android.view.animation.Interpolator p5, android.view.animation.Interpolator p6, float p7, Float p8)
    {
        this.startValueFloat = -898407689;
        this.endValueFloat = -898407689;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = 1;
        this.endProgress = 1;
        this.pathCp1 = 0;
        this.pathCp2 = 0;
        this.composition = p2;
        this.startValue = p3;
        this.endValue = p4;
        this.interpolator = 0;
        this.xInterpolator = p5;
        this.yInterpolator = p6;
        this.startFrame = p7;
        this.endFrame = p8;
        return;
    }

    public Keyframe(vn.ai.trueid.lottie.LottieComposition p2, Object p3, Object p4, android.view.animation.Interpolator p5, android.view.animation.Interpolator p6, android.view.animation.Interpolator p7, float p8, Float p9)
    {
        this.startValueFloat = -898407689;
        this.endValueFloat = -898407689;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = 1;
        this.endProgress = 1;
        this.pathCp1 = 0;
        this.pathCp2 = 0;
        this.composition = p2;
        this.startValue = p3;
        this.endValue = p4;
        this.interpolator = p5;
        this.xInterpolator = p6;
        this.yInterpolator = p7;
        this.startFrame = p8;
        this.endFrame = p9;
        return;
    }

    public boolean containsProgress(float p2)
    {
        if ((p2 < this.getStartProgress()) || (p2 >= this.getEndProgress())) {
            return 0;
        } else {
            return 1;
        }
    }

    public vn.ai.trueid.lottie.value.Keyframe copyWith(Object p2, Object p3)
    {
        return new vn.ai.trueid.lottie.value.Keyframe(p2, p3);
    }

    public float getEndProgress()
    {
        if (this.composition != null) {
            if (this.endProgress == 1) {
                if (this.endFrame != null) {
                    this.endProgress = ((float) (((double) this.getStartProgress()) + (((double) (this.endFrame.floatValue() - this.startFrame)) / ((double) this.composition.getDurationFrames()))));
                } else {
                    this.endProgress = 1065353216;
                }
            }
            return this.endProgress;
        } else {
            return 1065353216;
        }
    }

    public float getEndValueFloat()
    {
        if (this.endValueFloat == -898407689) {
            this.endValueFloat = ((Float) this.endValue).floatValue();
        }
        return this.endValueFloat;
    }

    public int getEndValueInt()
    {
        if (this.endValueInt == 784923401) {
            this.endValueInt = ((Integer) this.endValue).intValue();
        }
        return this.endValueInt;
    }

    public float getStartProgress()
    {
        float v0_0 = this.composition;
        if (v0_0 != 0) {
            if (this.startProgress == 1) {
                this.startProgress = ((this.startFrame - v0_0.getStartFrame()) / this.composition.getDurationFrames());
            }
            return this.startProgress;
        } else {
            return 0;
        }
    }

    public float getStartValueFloat()
    {
        if (this.startValueFloat == -898407689) {
            this.startValueFloat = ((Float) this.startValue).floatValue();
        }
        return this.startValueFloat;
    }

    public int getStartValueInt()
    {
        if (this.startValueInt == 784923401) {
            this.startValueInt = ((Integer) this.startValue).intValue();
        }
        return this.startValueInt;
    }

    public boolean isStatic()
    {
        if ((this.interpolator != null) || ((this.xInterpolator != null) || (this.yInterpolator != null))) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString()
    {
        String v0_1 = new StringBuilder("Keyframe{startValue=");
        v0_1.append(this.startValue);
        v0_1.append(", endValue=");
        v0_1.append(this.endValue);
        v0_1.append(", startFrame=");
        v0_1.append(this.startFrame);
        v0_1.append(", endFrame=");
        v0_1.append(this.endFrame);
        v0_1.append(", interpolator=");
        v0_1.append(this.interpolator);
        v0_1.append(125);
        return v0_1.toString();
    }
}

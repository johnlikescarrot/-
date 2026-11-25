package vn.ai.trueid.lottie.configurations.reducemotion;
public class IgnoreDisabledSystemAnimationsOption implements vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption {

    public IgnoreDisabledSystemAnimationsOption()
    {
        return;
    }

    public vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode getCurrentReducedMotionMode(android.content.Context p1)
    {
        return vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.STANDARD_MOTION;
    }
}

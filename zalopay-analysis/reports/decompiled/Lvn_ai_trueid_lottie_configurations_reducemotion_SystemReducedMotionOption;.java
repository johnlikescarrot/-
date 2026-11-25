package vn.ai.trueid.lottie.configurations.reducemotion;
public class SystemReducedMotionOption implements vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionOption {

    public SystemReducedMotionOption()
    {
        return;
    }

    public vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode getCurrentReducedMotionMode(android.content.Context p2)
    {
        if ((p2 != null) && (vn.ai.trueid.lottie.utils.Utils.getAnimationScale(p2) == 0)) {
            return vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.REDUCED_MOTION;
        } else {
            return vn.ai.trueid.lottie.configurations.reducemotion.ReducedMotionMode.STANDARD_MOTION;
        }
    }
}

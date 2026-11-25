package vn.ai.faceauth.sdk.presentation.domain.model;
public final class Constants {
    private static float BLUR_PROBABILITY;
    public static final vn.ai.faceauth.sdk.presentation.domain.model.Constants$Companion Companion;
    private static float EYE_OPENED_PROBABILITY;
    private static float LUMINOSITY_PROBABILITY;
    private static float SMILE_PROBABILITY;

    public static Constants()
    {
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.Companion = new vn.ai.faceauth.sdk.presentation.domain.model.Constants$Companion(0);
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.EYE_OPENED_PROBABILITY = 1053609165;
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.SMILE_PROBABILITY = 1050253722;
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.BLUR_PROBABILITY = 1061997773;
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.LUMINOSITY_PROBABILITY = 1053609165;
        return;
    }

    public Constants()
    {
        return;
    }

    public static final synthetic float access$getBLUR_PROBABILITY$cp()
    {
        return vn.ai.faceauth.sdk.presentation.domain.model.Constants.BLUR_PROBABILITY;
    }

    public static final synthetic float access$getEYE_OPENED_PROBABILITY$cp()
    {
        return vn.ai.faceauth.sdk.presentation.domain.model.Constants.EYE_OPENED_PROBABILITY;
    }

    public static final synthetic float access$getLUMINOSITY_PROBABILITY$cp()
    {
        return vn.ai.faceauth.sdk.presentation.domain.model.Constants.LUMINOSITY_PROBABILITY;
    }

    public static final synthetic float access$getSMILE_PROBABILITY$cp()
    {
        return vn.ai.faceauth.sdk.presentation.domain.model.Constants.SMILE_PROBABILITY;
    }

    public static final synthetic void access$setBLUR_PROBABILITY$cp(float p0)
    {
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.BLUR_PROBABILITY = p0;
        return;
    }

    public static final synthetic void access$setEYE_OPENED_PROBABILITY$cp(float p0)
    {
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.EYE_OPENED_PROBABILITY = p0;
        return;
    }

    public static final synthetic void access$setLUMINOSITY_PROBABILITY$cp(float p0)
    {
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.LUMINOSITY_PROBABILITY = p0;
        return;
    }

    public static final synthetic void access$setSMILE_PROBABILITY$cp(float p0)
    {
        vn.ai.faceauth.sdk.presentation.domain.model.Constants.SMILE_PROBABILITY = p0;
        return;
    }
}

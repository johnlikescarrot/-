package vn.ai.faceauth.sdk.presentation.presentation.utils;
public final class Logger {
    public static final vn.ai.faceauth.sdk.presentation.presentation.utils.Logger INSTANCE;
    private static java.util.List dataStep;

    static Logger()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(9);
        vn.ai.faceauth.sdk.presentation.presentation.utils.Logger.INSTANCE = new vn.ai.faceauth.sdk.presentation.presentation.utils.Logger();
        vn.ai.faceauth.sdk.presentation.presentation.utils.Logger.dataStep = new java.util.ArrayList();
        return;
    }

    private Logger()
    {
        return;
    }

    public final native void addStep(vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness p0);

    public final native java.util.List getDataStep();

    public final native void setDataStep(java.util.List p0);

    public final native String toJson();
}

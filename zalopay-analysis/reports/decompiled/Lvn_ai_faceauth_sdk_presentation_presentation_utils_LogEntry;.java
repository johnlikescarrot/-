package vn.ai.faceauth.sdk.presentation.presentation.utils;
public final class LogEntry {
    private final vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness stepLiveness;
    private final String timestamp;

    static LogEntry()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(54);
        return;
    }

    public LogEntry(vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness p1, String p2)
    {
        this.stepLiveness = p1;
        this.timestamp = p2;
        return;
    }

    public static synthetic vn.ai.faceauth.sdk.presentation.presentation.utils.LogEntry copy$default(vn.ai.faceauth.sdk.presentation.presentation.utils.LogEntry p0, vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.stepLiveness;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.timestamp;
        }
        return p0.copy(p1, p2);
    }

    public final native vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness component1();

    public final native String component2();

    public final native vn.ai.faceauth.sdk.presentation.presentation.utils.LogEntry copy(vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness p0, String p1);

    public native boolean equals(Object p0);

    public final native vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness getStepLiveness();

    public final native String getTimestamp();

    public native int hashCode();

    public native String toString();
}

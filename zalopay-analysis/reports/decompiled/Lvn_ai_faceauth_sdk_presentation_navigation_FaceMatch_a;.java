package vn.ai.faceauth.sdk.presentation.navigation;
public final class FaceMatch$a extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic java.util.List $listBase64;
    int label;

    static FaceMatch$a()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(59);
        return;
    }

    public FaceMatch$a(java.util.List p1, kotlin.coroutines.Continuation p2)
    {
        this.$listBase64 = p1;
        super(2, p2);
        return;
    }

    public final native kotlin.coroutines.Continuation create(Object p0, kotlin.coroutines.Continuation p1);

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final native Object invoke(kotlinx.coroutines.CoroutineScope p0, kotlin.coroutines.Continuation p1);

    public final native Object invokeSuspend(Object p0);
}

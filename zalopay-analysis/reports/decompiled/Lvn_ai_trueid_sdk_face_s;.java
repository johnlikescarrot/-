package vn.ai.trueid.sdk.face;
public final class s extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public int a;
    public final synthetic vn.ai.trueid.sdk.face.a0 b;
    public final synthetic vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView c;

    public s(vn.ai.trueid.sdk.face.a0 p1, vn.ai.trueid.sdk.face.present.presentation.widgets.OverlayView p2, kotlin.coroutines.Continuation p3)
    {
        this.b = p1;
        this.c = p2;
        super(2, p3);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p3, kotlin.coroutines.Continuation p4)
    {
        return new vn.ai.trueid.sdk.face.s(this.b, this.c, p4);
    }

    public final Object invoke(Object p1, Object p2)
    {
        return ((vn.ai.trueid.sdk.face.s) this.create(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2))).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p4)
    {
        vn.ai.trueid.sdk.face.b0 v0_0 = vg.i.getCOROUTINE_SUSPENDED();
        long v1_0 = this.a;
        if (v1_0 == 0) {
            kotlin.ResultKt.throwOnFailure(p4);
            this.a = 1;
            if (gi.U.a(500, this) == v0_0) {
                return v0_0;
            }
        } else {
            if (v1_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(p4);
            }
        }
        this.b.n = vn.ai.trueid.sdk.face.b0.FLASH_FIRST;
        this.c.a();
        return kotlin.Unit.INSTANCE;
    }
}

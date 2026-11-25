package vn.ai.trueid.sdk.face;
public final class o extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public int a;
    public final synthetic vn.ai.trueid.sdk.face.a0 b;

    public o(vn.ai.trueid.sdk.face.a0 p1, kotlin.coroutines.Continuation p2)
    {
        this.b = p1;
        super(2, p2);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p2, kotlin.coroutines.Continuation p3)
    {
        return new vn.ai.trueid.sdk.face.o(this.b, p3);
    }

    public final Object invoke(Object p1, Object p2)
    {
        return ((vn.ai.trueid.sdk.face.o) this.create(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2))).invokeSuspend(kotlin.Unit.INSTANCE);
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
        this.b.n = vn.ai.trueid.sdk.face.b0.FLASH_SECOND;
        return kotlin.Unit.INSTANCE;
    }
}

package vn.com.vng.zalopay.workers;
public final class LoadGatewaySdkWorker extends androidx.work.RxWorker {
    public static final synthetic kotlin.reflect.n[] d;
    public final h3.J c;

    static LoadGatewaySdkWorker()
    {
        kotlin.reflect.n[] v1_1 = new kotlin.reflect.n[1];
        v1_1[0] = kotlin.jvm.internal.M.a.g(new kotlin.jvm.internal.D("paymentWrapper", 0, "getPaymentWrapper()Lvn/com/vng/zalopay/pw/PaymentWrapper;", vn.com.vng.zalopay.workers.LoadGatewaySdkWorker));
        vn.com.vng.zalopay.workers.LoadGatewaySdkWorker.d = v1_1;
        return;
    }

    public LoadGatewaySdkWorker(android.content.Context p2, androidx.work.WorkerParameters p3)
    {
        super(p2, p3);
        super.c = new h3.J(new HR.b(), new s5.g());
        return;
    }

    public final gf.N b()
    {
        tf.c v0_0 = q9.a5.c();
        if (v0_0 != null) {
            v0_0.e1(this);
            AK.a v1_9 = new tf.b(1, new Gv.n(1, this));
            tf.c v0_1 = Ef.g.c;
            of.d.b(v0_1, "scheduler is null");
            return new tf.c(new tf.c(new io.reactivex.internal.operators.observable.p1(v1_9, v0_1, 2), new Gv.l(1, new FW.C(7)), 0), new AK.a(20), 1);
        } else {
            return new tf.b(2, androidx.work.r.a());
        }
    }
}

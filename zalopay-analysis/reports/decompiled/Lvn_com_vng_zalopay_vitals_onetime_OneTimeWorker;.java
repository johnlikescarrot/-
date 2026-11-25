package vn.com.vng.zalopay.vitals.onetime;
public final class OneTimeWorker extends androidx.work.CoroutineWorker {

    public OneTimeWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final Object b(wg.c p9)
    {
        String v0_2;
        if (!(p9 instanceof CQ.d)) {
            v0_2 = new CQ.d(this, p9);
        } else {
            v0_2 = ((CQ.d) p9);
            Object v1_2 = ((CQ.d) p9).i;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((CQ.d) p9).i = (v1_2 - -2147483648);
            }
        }
        int v9_6;
        String v2_6;
        java.util.Iterator v4;
        AQ.a v5;
        int v9_1 = v0_2.g;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        String v2_0 = v0_2.i;
        try {
            if (v2_0 == null) {
                kotlin.ResultKt.throwOnFailure(v9_1);
                int v9_2 = kQ.f.g;
                if (v9_2 == 0) {
                    v9_2 = new AQ.a(this.getApplicationContext());
                }
                v5 = v9_2;
                v4 = v9_2.a(4).entrySet().iterator();
                while (v4.hasNext()) {
                    int v9_13 = ((java.util.Map$Entry) v4.next());
                    v2_6 = ((String) v9_13.getKey());
                    try {
                        int v9_17 = ((CQ.c) new com.google.gson.Gson().fromJson(((String) v9_13.getValue()), CQ.c));
                    } catch (Exception) {
                        v5.a.edit().remove(v2_6).apply();
                    }
                    if (v9_17 != 0) {
                        xQ.a v6_2 = kQ.f.f;
                        if (v6_2 == null) {
                            v9_6 = 0;
                            if ((v9_6 == 0) || (v9_6.a() != null)) {
                                break;
                            }
                            v5.a.edit().remove(v2_6).apply();
                        } else {
                            v0_2.d = v5;
                            v0_2.e = v4;
                            v0_2.f = v2_6;
                            v0_2.i = 1;
                            v9_1 = v6_2.b(v9_17, v0_2);
                            if (v9_1 == v1_1) {
                                return v1_1;
                            } else {
                                v9_6 = ((yQ.f) v9_1);
                            }
                        }
                    } else {
                        v5.a.edit().remove(v2_6).apply();
                    }
                }
            } else {
                if (v2_0 != 1) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    v2_6 = v0_2.f;
                    v4 = v0_2.e;
                    v5 = v0_2.d;
                    kotlin.ResultKt.throwOnFailure(v9_1);
                }
            }
        } catch (int v9_22) {
            throw v9_22;
        } catch (java.util.concurrent.CancellationException) {
        }
        return androidx.work.r.b();
    }
}

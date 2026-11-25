package vn.com.vng.zalopay.miniapp.worker_manager;
public final class ExtractZipWorker extends androidx.work.CoroutineWorker {

    public ExtractZipWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final Object b(wg.c p14)
    {
        String v0_2;
        if (!(p14 instanceof mG.g)) {
            v0_2 = new mG.g(this, p14);
        } else {
            v0_2 = ((mG.g) p14);
            Object v1_2 = ((mG.g) p14).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((mG.g) p14).f = (v1_2 - -2147483648);
            }
        }
        Object v14_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        int v2_0 = v0_2.f;
        if (v2_0 == 0) {
            kotlin.ResultKt.throwOnFailure(v14_1);
            q9.a5.a().getClass();
            Object v14_4 = this.getInputData().b("zip_file_path");
            if (v14_4 != null) {
                int v7;
                if (v14_4.length() <= 0) {
                    v7 = 0;
                } else {
                    v7 = v14_4;
                }
                if (v7 != 0) {
                    Object v14_7 = this.getInputData().b("micro_app_name");
                    if (v14_7 != null) {
                        int v8;
                        if (v14_7.length() <= 0) {
                            v8 = 0;
                        } else {
                            v8 = v14_7;
                        }
                        if (v8 != 0) {
                            Object v14_10 = this.getInputData().b("micro_app_version");
                            if (v14_10 != null) {
                                int v9;
                                if (v14_10.length() <= 0) {
                                    v9 = 0;
                                } else {
                                    v9 = v14_10;
                                }
                                if (v9 != 0) {
                                    Object v14_13 = this.getInputData().b("web_resource_url");
                                    if (v14_13 != null) {
                                        int v10;
                                        if (v14_13.length() <= 0) {
                                            v10 = 0;
                                        } else {
                                            v10 = v14_13;
                                        }
                                        if (v10 != 0) {
                                            Object v14_15 = kotlinx.coroutines.Dispatchers.getIO();
                                            h0.w v5_1 = new h0.w(this, v7, v8, v9, v10, 0, 6);
                                            v0_2.f = 1;
                                            Object v14_16 = kotlinx.coroutines.BuildersKt.withContext(v14_15, v5_1, v0_2);
                                            if (v14_16 != v1_1) {
                                                return v14_16;
                                            } else {
                                                return v1_1;
                                            }
                                        }
                                    }
                                    return androidx.work.r.a();
                                }
                            }
                            return androidx.work.r.a();
                        }
                    }
                    return androidx.work.r.a();
                }
            }
            return androidx.work.r.a();
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(v14_1);
                return v14_1;
            }
        }
    }
}

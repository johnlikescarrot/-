package vn.com.vng.zalopay.miniapp.worker_manager;
public final class DownloadWorker extends androidx.work.CoroutineWorker {
    public final okhttp3.OkHttpClient d;
    public final int e;

    public DownloadWorker(android.content.Context p4, androidx.work.WorkerParameters p5)
    {
        super(p4, p5);
        super.d = new okhttp3.OkHttpClient$Builder().build();
        super.e = p5.a.hashCode();
        int v4_1 = ng.x.b(new DC.m(p4, 16));
        if (!q9.r7.a) {
            android.app.NotificationChannel v5_5 = new android.app.NotificationChannel("download_channel_foreground", "Downloading", 2);
            v5_5.setDescription("Notifications for downloading");
            ((android.app.NotificationManager) v4_1.getValue()).createNotificationChannel(v5_5);
            q9.r7.a = 1;
        }
        return;
    }

    public final Object b(wg.c p7)
    {
        androidx.work.h v0_2;
        if (!(p7 instanceof mG.b)) {
            v0_2 = new mG.b(this, p7);
        } else {
            v0_2 = ((mG.b) p7);
            Object v1_2 = ((mG.b) p7).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((mG.b) p7).f = (v1_2 - -2147483648);
            }
        }
        androidx.work.o v7_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        String v2_0 = v0_2.f;
        try {
            if (v2_0 == null) {
                kotlin.ResultKt.throwOnFailure(v7_1);
                androidx.work.o v7_3 = this.getInputData().b("url");
                if (v7_3 != null) {
                    String v2_3 = this.getInputData().b("filename");
                    if (v2_3 != null) {
                        v0_2.f = 1;
                        v7_1 = this.g(v7_3, v2_3, v0_2);
                        if (v7_1 == v1_1) {
                            return v1_1;
                        }
                    } else {
                        androidx.work.h v0_4 = new androidx.work.h(androidx.media3.exoplayer.source.r.g("error", "Filename is required"));
                        androidx.work.h.c(v0_4);
                        return new androidx.work.o(v0_4);
                    }
                } else {
                    androidx.work.h v0_6 = new androidx.work.h(androidx.media3.exoplayer.source.r.g("error", "URL is required"));
                    androidx.work.h.c(v0_6);
                    return new androidx.work.o(v0_6);
                }
            } else {
                if (v2_0 != 1) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    kotlin.ResultKt.throwOnFailure(v7_1);
                }
            }
        } catch (androidx.work.o v7_14) {
            androidx.work.o v7_15 = v7_14.getMessage();
            if (v7_15 == null) {
                v7_15 = "Unknown error occurred";
            }
            androidx.work.h v0_9 = new androidx.work.h(androidx.media3.exoplayer.source.r.g("error", v7_15));
            androidx.work.h.c(v0_9);
            return new androidx.work.o(v0_9);
        }
        return ((androidx.work.r) v7_1);
    }

    public final Object d()
    {
        android.app.Notification v0_2 = new androidx.core.app.A(this.getApplicationContext(), "download_channel_foreground");
        v0_2.e = androidx.core.app.A.b("\u0110ang t\u1ea3i");
        v0_2.f = androidx.core.app.A.b("");
        v0_2.w.icon = 17301633;
        v0_2.c(2, 1);
        v0_2.m = 100;
        android.app.Notification v0_1 = v0_2.a();
        int v4 = this.e;
        if (android.os.Build$VERSION.SDK_INT < 29) {
            return new androidx.work.j(v4, 0, v0_1);
        } else {
            return new androidx.work.j(v4, 0, v0_1);
        }
    }

    public final Object g(String p6, String p7, wg.c p8)
    {
        mG.c v0_2;
        if (!(p8 instanceof mG.c)) {
            v0_2 = new mG.c(this, p8);
        } else {
            v0_2 = ((mG.c) p8);
            Object v1_2 = ((mG.c) p8).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((mG.c) p8).f = (v1_2 - -2147483648);
            }
        }
        gi.F v8_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        mG.d v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v8_1);
            gi.F v8_2 = kotlinx.coroutines.Dispatchers.getIO();
            mG.d v2_2 = new mG.d(p6, this, p7, 0);
            v0_2.f = 1;
            IllegalStateException v6_1 = kotlinx.coroutines.BuildersKt.withContext(v8_2, v2_2, v0_2);
            if (v6_1 != v1_1) {
                return v6_1;
            } else {
                return v1_1;
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(v8_1);
                return v8_1;
            }
        }
    }
}

package vn.com.vng.zalopay.miniapp.worker_manager;
public final class DownloadZipWorker extends androidx.work.CoroutineWorker {
    public As.j d;
    public Ae.r e;
    public androidx.webkit.internal.o f;
    public Tb.c g;

    public DownloadZipWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public static final boolean g(vn.com.vng.zalopay.miniapp.worker_manager.DownloadZipWorker p6, String p7, java.io.File p8)
    {
        int v0_0 = p8.getParentFile();
        if ((v0_0 != 0) && (!v0_0.exists())) {
            v0_0.mkdirs();
        }
        boolean v1_1 = 0;
        try {
            int v7_4 = ((java.net.HttpURLConnection) ((java.net.URLConnection) com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(new java.net.URL(p7).openConnection())));
            try {
                v7_4.connect();
            } catch (Exception) {
                java.io.FileOutputStream v2_2 = 0;
                try {
                    if (p8.exists()) {
                        if (p8.isFile()) {
                            p8.delete();
                        }
                    }
                } catch (Throwable v8) {
                    int v6_1 = v1_1;
                    v1_1 = v2_2;
                    if (v1_1) {
                        v1_1.close();
                    }
                    if (v6_1 != 0) {
                        v6_1.close();
                    }
                    if (v7_4 != 0) {
                        v7_4.disconnect();
                    }
                    throw p8;
                }
                if (v2_2 != null) {
                    v2_2.close();
                }
                if (v1_1) {
                    v1_1.close();
                }
                if (v7_4 != 0) {
                    v7_4.disconnect();
                }
                return 0;
            } catch (Throwable v8) {
                v6_1 = 0;
            }
            if (v7_4.getResponseCode() != 200) {
                int v3_1 = v7_4.getResponseCode();
                int v4_0 = v7_4.getResponseMessage();
                StringBuilder v5_1 = new StringBuilder("Server returned HTTP ");
                v5_1.append(v3_1);
                v5_1.append(" ");
                v5_1.append(v4_0);
                throw new Exception(v5_1.toString());
            } else {
                v6_1 = new java.io.BufferedInputStream(v7_4.getInputStream());
                try {
                    v2_2 = new java.io.FileOutputStream(p8);
                    v1_1 = 8192;
                } catch (Exception) {
                    v2_2 = 8192;
                } catch (Throwable v8) {
                }
                boolean v1_2 = new byte[8192];
                while(true) {
                    int v3_2 = v6_1.read(v1_2);
                    if (v3_2 == -1) {
                        break;
                    }
                    v2_2.write(v1_2, 0, v3_2);
                }
                v2_2.flush();
                v2_2.close();
                v6_1.close();
                v7_4.disconnect();
                return 1;
            }
        } catch (Exception) {
            v7_4 = 0;
            v2_2 = 0;
        } catch (Throwable v8) {
            v6_1 = 0;
            v7_4 = 0;
        }
    }

    public final Object b(wg.c p19)
    {
        java.io.File v1_1;
        if (!(p19 instanceof mG.e)) {
            v1_1 = new mG.e(this, p19);
        } else {
            v1_1 = ((mG.e) p19);
            String v3_6 = ((mG.e) p19).g;
            if ((v3_6 & -2147483648) == 0) {
            } else {
                ((mG.e) p19).g = (v3_6 - -2147483648);
            }
        }
        String v6_0;
        mG.e v10 = v1_1;
        Object v0_2 = v10.e;
        Object v11 = vg.i.getCOROUTINE_SUSPENDED();
        java.io.File v1_7 = v10.g;
        String v5_0 = 0;
        try {
            if (v1_7 == null) {
                Object v0_31;
                kotlin.ResultKt.throwOnFailure(v0_2);
                Object v0_24 = ((CC.f) q9.a5.a());
                this.d = new As.j(((zs.b) v0_24.C0.get()));
                this.e = new Ae.r(1, ((zs.c) v0_24.K1.get()));
                this.f = new androidx.webkit.internal.o(3, ((zs.c) v0_24.K1.get()));
                this.g = new Tb.c(3, ((zs.c) v0_24.K1.get()));
                Object v0_30 = this.getInputData().a.get("mini_app_id");
                if (!(v0_30 instanceof Long)) {
                    v0_31 = -1;
                } else {
                    v0_31 = ((Long) v0_30).longValue();
                }
                androidx.work.o v8_2 = new Long(v0_31);
                if (v8_2.longValue() == -1) {
                    v8_2 = 0;
                }
                if (v8_2 == null) {
                    return androidx.work.r.a();
                } else {
                    Object v0_36 = v8_2.longValue();
                    String v6_14 = this.d;
                    if (v6_14 == null) {
                        v6_14 = 0;
                    } else {
                    }
                    java.io.File v7_4 = String.valueOf(v0_36);
                    v10.d = v0_36;
                    v10.g = 1;
                    String v6_15 = v6_14.a(v7_4, v10);
                    if (v6_15 != v11) {
                        v0_2 = v6_15;
                        v6_0 = v0_36;
                        Object v0_1 = ((ji.i) v0_2);
                        v10.d = v6_0;
                        v10.g = 2;
                        v0_2 = ji.j0.w(v0_1, v10);
                        if (v0_2 != v11) {
                            Object v0_3 = ((ns.c$a) v0_2);
                            if (v0_3 != null) {
                                Object v0_6 = v0_3.b();
                                if (v0_6 != null) {
                                    if ((v0_6.length() <= 0) || (!com.google.android.gms.internal.mlkit_vision_barcode.D0.j(v0_6))) {
                                        androidx.work.o v8_0 = 0;
                                    } else {
                                        v8_0 = v0_6;
                                    }
                                    if (v8_0 != null) {
                                        Object v0_9 = kotlin.text.StringsKt.substringAfter(v8_0, "micro-apps/", "");
                                        if (v0_9.length() <= 0) {
                                            v0_9 = 0;
                                        }
                                        if (v0_9 != null) {
                                            int v9_1 = kotlin.text.StringsKt.y(v0_9, new String[] {"/"}), 0, 6);
                                            if (v9_1.size() >= 2) {
                                                v5_0 = v9_1;
                                            }
                                            if (v5_0 != null) {
                                                String v3_2 = ((CharSequence) v5_0.get(0));
                                                if (v3_2.length() != 0) {
                                                    String v3_3 = ((String) v3_2);
                                                    String v4_1 = ((CharSequence) v5_0.get(1));
                                                    if (v4_1.length() != 0) {
                                                        String v4_2 = ((String) v4_1);
                                                        String v5_3 = new java.io.File(this.getApplicationContext().getFilesDir(), "micro-apps//zip/".concat(v0_9));
                                                        String v13_3 = v6_0;
                                                        java.io.File v7_1 = new java.io.File(this.getApplicationContext().getFilesDir(), androidx.activity.result.e.p("micro-apps//", v3_3, "/", v4_2));
                                                        gi.F v15 = kotlinx.coroutines.Dispatchers.getIO();
                                                        String v3_4 = v13_3;
                                                        Object v0_14 = new mG.f(v5_3, this, v3_4, v3_3, v4_2, v7_1, v8_0, 0);
                                                        v10.d = v3_4;
                                                        v10.g = 3;
                                                        Object v0_15 = kotlinx.coroutines.BuildersKt.withContext(v15, v0_14, v10);
                                                        if (v0_15 != v11) {
                                                            return v0_15;
                                                        } else {
                                                        }
                                                    } else {
                                                        return androidx.work.r.a();
                                                    }
                                                } else {
                                                    return androidx.work.r.a();
                                                }
                                            } else {
                                                return androidx.work.r.a();
                                            }
                                        } else {
                                            return androidx.work.r.a();
                                        }
                                    }
                                }
                                return androidx.work.r.a();
                            } else {
                                return androidx.work.r.a();
                            }
                        } else {
                        }
                    } else {
                    }
                }
            } else {
                if (v1_7 == 1) {
                    v6_0 = v10.d;
                    kotlin.ResultKt.throwOnFailure(v0_2);
                } else {
                    if (v1_7 == 2) {
                        v6_0 = v10.d;
                        kotlin.ResultKt.throwOnFailure(v0_2);
                    } else {
                        if (v1_7 != 3) {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        } else {
                            kotlin.ResultKt.throwOnFailure(v0_2);
                            return v0_2;
                        }
                    }
                }
            }
        } catch (Object v0_4) {
            ck.b.f(v0_4);
            return androidx.work.r.a();
        }
        return v11;
    }
}

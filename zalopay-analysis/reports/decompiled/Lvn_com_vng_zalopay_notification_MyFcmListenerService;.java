package vn.com.vng.zalopay.notification;
public class MyFcmListenerService extends com.google.firebase.messaging.FirebaseMessagingService {
    public com.google.android.gms.internal.firebase-perf.z a;
    public final org.greenrobot.eventbus.e b;
    public final android.content.SharedPreferences c;

    public MyFcmListenerService()
    {
        android.content.SharedPreferences v0_0 = vn.com.vng.zalopay.AndroidApplication.g;
        CC.f v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        android.content.SharedPreferences v0_4 = v0_0.a;
        if (v0_4 == null) {
            v0_4 = 0;
        }
        this.b = v0_4.i();
        android.content.SharedPreferences v0_1 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        android.content.SharedPreferences v0_2 = v0_1.a;
        if (v0_2 != null) {
            v1 = v0_2;
        }
        this.c = v1.H();
        return;
    }

    public final void onMessageReceived(com.google.firebase.messaging.m p27)
    {
        android.app.NotificationManager v3_7 = p27.a;
        if (p27.b == null) {
            android.media.AudioAttributes v2_30 = new P.f(0);
            String v6_1 = v3_7.keySet().iterator();
            while (v6_1.hasNext()) {
                String v7_11 = ((String) v6_1.next());
                long v8_11 = v3_7.get(v7_11);
                if (((v8_11 instanceof String)) && ((!v7_11.startsWith("google.")) && ((!v7_11.startsWith("gcm.")) && ((!v7_11.equals("from")) && ((!v7_11.equals("message_type")) && (!v7_11.equals("collapse_key"))))))) {
                    v2_30.put(v7_11, ((String) v8_11));
                }
            }
            p27.b = v2_30;
        }
        android.net.Uri v0_7 = p27.b;
        int v9_2 = ((String) v0_7.get("msg"));
        v0_7.get("badgenumber");
        android.media.AudioAttributes v2_11 = ((String) v0_7.get("embeddata"));
        java.util.ArrayList v10_2 = ((String) v0_7.get("boldtitle"));
        android.net.Uri v0_25 = ((String) v0_7.get("sound"));
        v3_7.getString("from");
        android.app.NotificationManager v3_8 = vn.com.vng.zalopay.AndroidApplication.g;
        // Both branches of the condition point to the same code.
        // if (v3_8 != null) {
            String v7_7;
            if (v3_8 != null) {
                v7_7 = v3_8;
            } else {
                v7_7 = 0;
            }
            if (v7_7.b == null) {
                if (v3_8 == null) {
                    v3_8 = 0;
                }
                android.app.NotificationManager v3_13 = v3_8.a;
                if (v3_13 == null) {
                    v3_13 = 0;
                }
                android.app.NotificationManager v3_14 = v3_13.D();
                v3_14.n();
                if (v3_14.n()) {
                    v3_14.r();
                    String v7_12 = vn.com.vng.zalopay.AndroidApplication.g;
                    if (v7_12 == null) {
                        v7_12 = 0;
                    }
                    v7_12.f(v3_14.p());
                }
            }
            try {
                android.media.AudioAttributes v2_29;
                if (android.text.TextUtils.isEmpty(v2_11)) {
                    v2_29 = 0;
                } else {
                    v2_29 = ((BH.a) nY.h.b(v2_11, BH.a));
                    if ((v0_25 != null) && (!v0_25.isEmpty())) {
                        v2_29.f = v0_25;
                    }
                }
            } catch (Exception) {
            }
            String v7_5 = "";
            try {
                if ((v2_29 != null) && (!android.text.TextUtils.isEmpty(v2_29.c))) {
                    androidx.core.app.T[] v11_25;
                    long v8_15 = vn.com.vng.zalopay.AndroidApplication.g;
                    if (v8_15 != 0) {
                        v11_25 = v8_15;
                    } else {
                        v11_25 = 0;
                    }
                    androidx.core.app.T[] v11_26 = v11_25.b;
                    if (v11_26 != null) {
                        if (v8_15 == 0) {
                            v8_15 = 0;
                        }
                        androidx.core.app.t v17_0;
                        long v8_0 = v8_15.getApplicationContext();
                        BH.g v12_2 = com.google.gson.JsonParser.parseString(v2_29.c).getAsJsonObject();
                        Integer v15_0 = v11_26.h().getUserId();
                        if (!android.text.TextUtils.isEmpty(0)) {
                            v17_0 = 0;
                        } else {
                            long v8_2 = android.provider.Settings$Secure.getString(v8_0.getContentResolver(), "android_id");
                            if ((android.text.TextUtils.isEmpty(v8_2)) || ("9774d56d682e549c".equals(v8_2))) {
                                v8_2 = java.util.UUID.randomUUID().toString().replace("-", "");
                            }
                            v17_0 = v8_2;
                        }
                        try {
                            int v16_0;
                            if (!v12_2.has("pushId")) {
                                v16_0 = "";
                            } else {
                                v16_0 = v12_2.get("pushId").getAsString();
                            }
                            try {
                                int v14_0;
                                if (!v12_2.has("source")) {
                                    v14_0 = 0;
                                } else {
                                    v14_0 = v12_2.get("source").getAsInt();
                                }
                            } catch (Exception) {
                            }
                            if (!android.text.TextUtils.isEmpty(v15_0)) {
                                UR.a.h(4, v14_0, v15_0, v16_0, v17_0, System.currentTimeMillis());
                                com.google.android.gms.internal.mlkit_vision_barcode.B5.a(v12_2, v2_29);
                            } else {
                            }
                        } catch (Exception) {
                        }
                    } else {
                    }
                }
            } catch (Exception) {
            }
            if (!android.text.TextUtils.isEmpty(v9_2)) {
                android.net.Uri v0_5 = vn.com.vng.zalopay.AndroidApplication.g;
                if (v0_5 == null) {
                    v0_5 = 0;
                }
                android.net.Uri v0_6 = v0_5.b;
                if (v0_6 != null) {
                    BH.g v12_6;
                    BH.g v12_4 = v0_6.h().p();
                    if (v12_4 != null) {
                        if (v2_29 != null) {
                            v12_6 = v12_4.c.equals(v2_29.d);
                        } else {
                            v12_6 = 1;
                        }
                    } else {
                        v12_6 = 0;
                    }
                    if (v12_6 != null) {
                        android.net.Uri v0_17;
                        BH.g v12_7 = v0_6.k1();
                        v12_7.getClass();
                        com.google.gson.JsonObject v13_3 = new hH.a();
                        v13_3.r = v10_2;
                        v13_3.j = v9_2;
                        v13_3.c = v2_29.a;
                        v13_3.b = v2_29.b;
                        v13_3.n = v2_29.d;
                        v13_3.h = ((long) v2_29.e);
                        v13_3.z = v2_29.f;
                        v13_3.m = 0;
                        android.net.Uri v0_16 = v2_29.c;
                        if (v0_16 == null) {
                            v0_17 = "";
                        } else {
                            v0_17 = v0_16.replaceAll("\\\"", "\"");
                        }
                        try {
                            android.net.Uri v0_19 = new com.google.gson.JsonParser().parse(v0_17).getAsJsonObject();
                        } catch (android.net.Uri v0_20) {
                            v0_20.printStackTrace();
                            v0_19 = 0;
                        }
                        if (v0_19 == null) {
                            v0_19 = new com.google.gson.JsonObject();
                        }
                        if (v0_19.has("notificationtype")) {
                            v13_3.m = ((long) v0_19.get("notificationtype").getAsInt());
                        }
                        Integer v15_6;
                        androidx.core.app.t v5_0;
                        v0_19.addProperty("sound", v13_3.z);
                        v13_3.k = new hH.b(v0_19);
                        v12_7.d.b(v13_3);
                        android.net.Uri v0_23 = v12_7.a;
                        int v14_15 = BH.g.b(v0_23);
                        if (android.text.TextUtils.isEmpty(v2_29.c)) {
                            v15_6 = v9_2;
                            v5_0 = 0;
                        } else {
                            try {
                                if (v2_29.c.isEmpty()) {
                                    v15_6 = v9_2;
                                    v5_0 = 0;
                                } else {
                                    v5_0 = new com.google.gson.JsonParser().parse(v2_29.c).getAsJsonObject();
                                    Integer v15_11 = v2_29.f;
                                    if ((v15_11 != null) && (!v15_11.isEmpty())) {
                                        v5_0.addProperty("sound", v2_29.f);
                                    }
                                    v15_6 = v9_2;
                                }
                            } catch (Exception) {
                            }
                            v14_15 = v12_7.a(v5_0, v13_3.C);
                        }
                        int v4_11;
                        android.media.AudioAttributes v2_13 = v0_23.getString(2131951830);
                        long v8_10 = new androidx.core.app.A(v0_23, v2_13);
                        com.google.gson.JsonObject v13_4 = v5_0;
                        v8_10.g = android.app.PendingIntent.getActivity(v0_23, ((int) System.currentTimeMillis()), v14_15, 1275068416);
                        if (!android.text.TextUtils.isEmpty(v10_2)) {
                            v4_11 = new android.text.SpannableString(v10_2);
                            v4_11.setSpan(new android.text.style.StyleSpan(1), 0, v10_2.length(), 33);
                        } else {
                            v4_11 = v2_13;
                        }
                        v8_10.e = androidx.core.app.A.b(v4_11);
                        v8_10.f = androidx.core.app.A.b(v15_6);
                        v8_10.w.icon = 2131233933;
                        v8_10.s = v0_23.getColor(2131100629);
                        v8_10.o = 1;
                        v8_10.p = 1;
                        if ((v13_4 == null) || (!v13_4.has("image"))) {
                            int v4_19 = new androidx.core.app.y();
                            v4_19.b = androidx.core.app.A.b(v15_6);
                            v8_10.f(v4_19);
                            v8_10.e(-16711936, 2000, 200);
                        } else {
                            try {
                                int v4_26 = ((java.net.HttpURLConnection) ((java.net.URLConnection) com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(new java.net.URL(v13_4.get("image").getAsString()).openConnection())));
                                v4_26.setDoInput(1);
                                v4_26.connect();
                                int v4_28 = android.graphics.BitmapFactory.decodeStream(v4_26.getInputStream());
                            } catch (java.io.IOException) {
                                v4_28 = 0;
                            }
                            java.util.ArrayList v10_5;
                            v8_10.d(v4_28);
                            androidx.core.app.t v5_13 = new androidx.core.app.x();
                            if (v4_28 != 0) {
                                v10_5 = new androidx.core.graphics.drawable.IconCompat(1);
                                v10_5.b = v4_28;
                            } else {
                                v10_5 = 0;
                            }
                            v5_13.b = v10_5;
                            v8_10.f(v5_13);
                        }
                        if ((v13_4 != null) && (v13_4.has("buttons"))) {
                            int v4_32 = v13_4.get("buttons").getAsJsonArray();
                            if (v4_32.isJsonArray()) {
                                int v4_33 = v4_32.iterator();
                                while (v4_33.hasNext()) {
                                    androidx.core.app.t v5_24 = ((com.google.gson.JsonElement) v4_33.next());
                                    if (v5_24.isJsonObject()) {
                                        androidx.core.app.t v5_25 = v5_24.getAsJsonObject();
                                        if ((v5_25.get("action_data").isJsonObject()) && (v5_25.get("action_text_button").getAsString() != null)) {
                                            androidx.core.app.T[] v11_21 = v5_25.get("action_text_button").getAsString();
                                            androidx.core.app.t v5_27 = v5_25.get("action_data").getAsJsonObject();
                                            androidx.core.app.t v5_28;
                                            java.util.ArrayList v10_12 = ((CH.a) v12_7.n.get(Integer.valueOf(99)));
                                            int v14_21 = new java.util.HashMap();
                                            v14_21.put("notification_source", Integer.valueOf(1));
                                            if (v10_12 == null) {
                                                v5_28 = BH.g.b(v0_23);
                                            } else {
                                                v5_28 = v10_12.a(v0_23, v5_27, v14_21);
                                            }
                                            androidx.core.app.T[] v23;
                                            android.app.PendingIntent v20 = android.app.PendingIntent.getActivity(v0_23, ((int) System.currentTimeMillis()), v5_28, 201326592);
                                            android.os.Bundle v21_1 = new android.os.Bundle();
                                            CharSequence v19 = androidx.core.app.A.b(v11_21);
                                            androidx.core.app.t v5_30 = new java.util.ArrayList();
                                            java.util.ArrayList v10_15 = new java.util.ArrayList();
                                            if (!v5_30.isEmpty()) {
                                                androidx.core.app.T[] v11_24 = new androidx.core.app.T[v5_30.size()];
                                                v23 = ((androidx.core.app.T[]) v5_30.toArray(v11_24));
                                            } else {
                                                v23 = 0;
                                            }
                                            androidx.core.app.T[] v22;
                                            if (!v10_15.isEmpty()) {
                                                androidx.core.app.t v5_35 = new androidx.core.app.T[v10_15.size()];
                                                v22 = ((androidx.core.app.T[]) v10_15.toArray(v5_35));
                                            } else {
                                                v22 = 0;
                                            }
                                            v8_10.b.add(new androidx.core.app.t(0, v19, v20, v21_1, v22, v23, 1, 1));
                                        }
                                    }
                                }
                            }
                        }
                        v8_10.c(16, 1);
                        android.app.NotificationManager v3_12 = ((android.app.NotificationManager) v0_23.getSystemService("notification"));
                        if (v3_12 != null) {
                            if ((v13_4 != null) && (v13_4.has("sound"))) {
                                v7_5 = v13_4.get("sound").getAsString();
                            }
                            if ((v7_5 != null) && (!v7_5.isEmpty())) {
                                if (!v7_5.equals("default")) {
                                    androidx.core.app.t v5_20 = 2131952829;
                                } else {
                                    v5_20 = 2131952388;
                                }
                            } else {
                                v5_20 = 2131952828;
                            }
                            androidx.core.app.t v5_21 = v0_23.getString(v5_20);
                            if (v3_12.getNotificationChannel(v5_21) == null) {
                                java.util.ArrayList v10_7 = new android.app.NotificationChannel(v5_21, v2_13, 4);
                                try {
                                    if ((v7_5 != null) && (!v7_5.isEmpty())) {
                                        if (!v7_5.equals("default")) {
                                            int v4_37;
                                            android.media.AudioAttributes v2_17 = new StringBuilder("android.resource://");
                                            v2_17.append(v0_23.getPackageName());
                                            v2_17.append("/");
                                            if (v7_5.equals("zalopay_sound.mp3")) {
                                                v4_37 = 2131886164;
                                            } else {
                                                if (v7_5.equals("default")) {
                                                    v4_37 = 999;
                                                } else {
                                                    v4_37 = 0;
                                                }
                                            }
                                            v2_17.append(v4_37);
                                            v10_7.setSound(android.net.Uri.parse(v2_17.toString()), new android.media.AudioAttributes$Builder().setContentType(2).setUsage(10).setLegacyStreamType(5).build());
                                        }
                                    } else {
                                        v10_7.setSound(0, 0);
                                    }
                                } catch (Exception) {
                                }
                                v3_12.createNotificationChannel(v10_7);
                            }
                            v8_10.u = v5_21;
                            v3_12.notify(((int) System.currentTimeMillis()), v8_10.a());
                            return;
                        } else {
                            android.media.AudioAttributes v2_26 = new Object[0];
                            ck.b.e("[Notification] Notification manager is null", v2_26);
                            return;
                        }
                    }
                }
                Integer v15_1 = v9_2;
                if (this.a == null) {
                    this.a = new com.google.android.gms.internal.firebase-perf.z(this.getApplicationContext());
                }
                String v6_2 = this.a;
                android.net.Uri v0_12 = ((android.content.Context) v6_2.b);
                if (v2_29 != null) {
                    int v9_0 = v15_1;
                    android.app.NotificationManager v3_5 = new hH.a();
                    v3_5.c = v2_29.a;
                    v3_5.B = ((long) 1);
                    v6_2.k(v6_2.p(v2_29, v3_5.C), v0_12.getString(2131951830), v9_0, v10_2, com.google.android.gms.internal.firebase-perf.z.x(v2_29));
                } else {
                    String v7_4 = new android.content.Intent(((android.content.Context) v6_2.b), vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2);
                    v7_4.setAction(String.valueOf(System.currentTimeMillis()));
                    v7_4.putExtra("notification_source", 1);
                    v6_2.k(v7_4, v0_12.getString(2131951830), v15_1, v10_2, 0);
                }
            }
            return;
        // }
    }

    public final void onNewToken(String p6)
    {
        ex.m v1_0 = this.c;
        String v0_1 = v1_0.getString("pref_current_device_token", "");
        if ((!android.text.TextUtils.isEmpty(v0_1)) && (!android.text.TextUtils.isEmpty(p6))) {
            UR.a.c(22010, p6, 0, 0);
        }
        if (!android.text.TextUtils.isEmpty(p6)) {
            v1_0.edit().putString("pref_current_device_token", p6).apply();
        }
        this.b.i(new ex.m(v0_1, p6));
        return;
    }
}

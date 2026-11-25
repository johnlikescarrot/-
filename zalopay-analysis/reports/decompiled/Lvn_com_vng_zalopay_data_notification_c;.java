package vn.com.vng.zalopay.data.notification;
public final class c extends Pt.m implements vn.com.vng.zalopay.data.notification.k$a {
    public final com.google.gson.JsonParser b;
    public final android.content.SharedPreferences c;

    public c(Rt.f p1, android.content.SharedPreferences p2)
    {
        super(p1);
        super.b = new com.google.gson.JsonParser();
        super.c = p2;
        return;
    }

    public final void B(long p4, long p6, long p8)
    {
        vn.com.vng.zalopay.data.cache.model.NotificationGDDao v0_2 = this.a.a().g;
        v0_2.getClass();
        ub.c v1_1 = new ub.c(v0_2);
        Aj.h v6_1 = vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Appid.a(Long.valueOf(p6));
        org.greenrobot.greendao.DaoException v4_2 = vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Notificationtype.a(Long.valueOf(p4));
        String v5_1 = vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Transid.a(Long.valueOf(p8));
        Aj.i[] v7_3 = new Aj.i[2];
        v7_3[0] = v4_2;
        v7_3[1] = v5_1;
        v1_1.K(v6_1, v7_3);
        v1_1.h = Integer.valueOf(1);
        org.greenrobot.greendao.DaoException v4_5 = v1_1.u();
        if (!com.google.android.gms.internal.mlkit_vision_barcode.p0.b(v4_5)) {
            String v5_3;
            org.greenrobot.greendao.DaoException v4_7 = ((Rt.i) v4_5.get(0));
            v0_2.a();
            v0_2.getClass();
            if (v4_7 == null) {
                v5_3 = 0;
            } else {
                v5_3 = v4_7.a;
            }
            if (v5_3 != null) {
                v0_2.e(v5_3);
                return;
            } else {
                if (v4_7 != null) {
                    throw new org.greenrobot.greendao.DaoException("Entity has no key");
                } else {
                    throw new NullPointerException("Entity may not be null");
                }
            }
        } else {
            return;
        }
    }

    public final boolean F(long p6, long p8)
    {
        Rt.f v3 = this.a;
        if (p6 <= 0) {
            if (p8 > 0) {
                int v6_12 = v3.a().g;
                v6_12.getClass();
                ub.c v7_1 = new ub.c(v6_12);
                Aj.i[] v8_2 = new Aj.i[0];
                v7_1.K(vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Mtuid.a(Long.valueOf(p8)), v8_2);
                if (v7_1.j() > 0) {
                    return 1;
                }
            }
        } else {
            Aj.i[] v8_4 = v3.a().g;
            v8_4.getClass();
            ub.c v9_1 = new ub.c(v8_4);
            ub.c v7_2 = new Aj.i[0];
            v9_1.K(vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Mtaid.a(Long.valueOf(p6)), v7_2);
            if (v9_1.j() > 0) {
                return 1;
            }
        }
        return 0;
    }

    public final Long b(Rt.k p3)
    {
        try {
            long v0_3 = this.a.a().i.j(p3);
        } catch (Long v3_2) {
            v3_2.getMessage();
            v0_3 = -1;
        }
        return Long.valueOf(v0_3);
    }

    public final int c()
    {
        int v0_5 = this.a.a().i;
        v0_5.getClass();
        ub.c v1_1 = new ub.c(v0_5);
        Aj.i[] v2_1 = new Aj.i[0];
        v1_1.K(vn.com.vng.zalopay.data.cache.model.RedPacketGreetingsGDDao$Properties.Isread.a(Boolean.FALSE), v2_1);
        return ((int) v1_1.j());
    }

    public final java.util.ArrayList e()
    {
        Throwable v0_4 = this.a.a().i;
        android.database.Cursor v1_3 = v0_4.b;
        String v2_1 = v0_4.f;
        if (v2_1.i == null) {
            v2_1.i = zj.c.d(v2_1.b, v2_1.c);
        }
        android.database.Cursor v1_2 = ((net.zetetic.database.sqlcipher.SQLiteDatabase) v1_3.b).q(v2_1.i, 0);
        try {
            Throwable v0_2 = v0_4.l(v1_2);
            v1_2.close();
            return v0_2;
        } catch (Throwable v0_3) {
            v1_2.close();
            throw v0_3;
        }
    }

    public final void h(hH.a p5)
    {
        android.content.SharedPreferences$Editor v0_2;
        android.content.SharedPreferences v1 = this.c;
        android.content.SharedPreferences$Editor v0_5 = v1.getString("cache_promotion_list", "");
        if (!android.text.TextUtils.isEmpty(v0_5)) {
            v0_2 = ((java.util.List) nY.h.b(v0_5, new vn.com.vng.zalopay.data.notification.NotificationLocalStorage$1().getType()));
        } else {
            v0_2 = new java.util.ArrayList();
        }
        v0_2.add(p5);
        v1.edit().putString("cache_promotion_list", nY.h.d(v0_2)).apply();
        return;
    }

    public final int j()
    {
        int v0_5 = this.a.a().g;
        v0_5.getClass();
        ub.c v1_1 = new ub.c(v0_5);
        Aj.i[] v2_2 = new Aj.i[0];
        v1_1.K(vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Notificationstate.a(Integer.valueOf(1)), v2_2);
        return ((int) v1_1.j());
    }

    public final hH.a k(long p6)
    {
        Long v0_0 = this.a;
        long v1_1 = v0_0.a();
        Long v6_7 = Long.valueOf(p6);
        hH.a v7_0 = ((sj.a) ((java.util.HashMap) v1_1.b).get(Rt.i));
        if (v7_0 == null) {
            throw new org.greenrobot.greendao.DaoException(androidx.compose.foundation.layout.G0.k(Rt.i, "No DAO registered for "));
        } else {
            Long v6_6 = ((Rt.i) v7_0.k(v6_7));
            if (v6_6 != null) {
                v6_6.i = Long.valueOf(((long) 3));
                hH.a v7_6 = v0_0.a().g;
                Long v0_3 = new Rt.i[] {v6_6});
                v7_6.getClass();
                v7_6.h(v7_6.f.a(), java.util.Arrays.asList(v0_3), 1);
            }
            if (v6_6 != null) {
                Long v0_17;
                hH.a v7_8 = new hH.a();
                v7_8.C = v6_6.a.longValue();
                v7_8.h = v6_6.c.longValue();
                v7_8.o = v6_6.g;
                v7_8.j = v6_6.e;
                v7_8.i = v6_6.d.longValue();
                v7_8.m = v6_6.j.longValue();
                Long v0_15 = v6_6.m;
                if (!android.text.TextUtils.isEmpty(v0_15)) {
                    try {
                        v0_17 = this.b.parse(v0_15).getAsJsonObject();
                    } catch (Long v0_18) {
                        long v1_9 = new Object[0];
                        ck.b.c(v0_18, " parse exception Notification Entity", v1_9);
                        v0_17 = new com.google.gson.JsonObject();
                    }
                } else {
                    v0_17 = new com.google.gson.JsonObject();
                }
                long v3_2;
                v7_8.k = new hH.b(v0_17);
                v7_8.n = v6_6.f;
                v7_8.g = v6_6.b.longValue();
                Long v0_24 = v6_6.h;
                long v1_0 = 0;
                if (v0_24 != null) {
                    v3_2 = v0_24.longValue();
                } else {
                    v3_2 = 0;
                }
                v7_8.q = v3_2;
                v7_8.B = v6_6.i.longValue();
                v7_8.c = v6_6.l.longValue();
                v7_8.b = v6_6.k.longValue();
                Long v6_1 = v6_6.n;
                if (v6_1 != null) {
                    v1_0 = v6_1.longValue();
                }
                v7_8.t = v1_0;
                return v7_8;
            } else {
                return 0;
            }
        }
    }

    public final boolean m(long p4)
    {
        Rt.f v0 = this.a;
        Aj.i[] v1_2 = v0.a().i;
        v1_2.getClass();
        ub.c v2_1 = new ub.c(v1_2);
        Aj.i[] v1_0 = new Aj.i[0];
        v2_1.K(vn.com.vng.zalopay.data.cache.model.RedPacketGreetingsGDDao$Properties.Id.a(Long.valueOf(p4)), v1_0);
        org.greenrobot.greendao.DaoException v4_3 = ((Rt.k) v2_1.J());
        if (v4_3 != null) {
            org.greenrobot.greendao.DaoException v4_4;
            String v5_2 = v0.a().i;
            v5_2.a();
            v5_2.getClass();
            if (v4_3 == null) {
                v4_4 = 0;
            } else {
                v4_4 = v4_3.a;
            }
            if (v4_4 == null) {
                throw new org.greenrobot.greendao.DaoException("Entity has no key");
            } else {
                v5_2.e(v4_4);
                return 1;
            }
        } else {
            return 0;
        }
    }

    public final long p(hH.a p14)
    {
        String v4_1;
        Rt.i v3_1 = 0;
        if (p14 != null) {
            if ((p14.b > 0) || (p14.c > 0)) {
                String v8_4;
                v4_1 = new Rt.i();
                v4_1.c = Long.valueOf(p14.h);
                v4_1.g = p14.o;
                v4_1.e = p14.j;
                v4_1.d = Long.valueOf(p14.i);
                long v5_20 = p14.m;
                v4_1.j = Long.valueOf(v5_20);
                if (v5_20 != 109) {
                    v8_4 = p14.a();
                } else {
                    long v5_22 = p14.k;
                    if (v5_22 != 0) {
                        v8_4 = v5_22.a;
                        if (v8_4 != null) {
                            if (!v5_22.d) {
                                try {
                                    String v8_5 = v8_4.get("type");
                                } catch (Exception) {
                                    v8_4 = v5_22.a;
                                    long v5_23;
                                    if (v8_4 == null) {
                                        v5_23 = "";
                                    } else {
                                        v5_23 = v8_4.toString();
                                    }
                                    v4_1.m = v5_23;
                                    v4_1.h = Long.valueOf(p14.q);
                                    v4_1.f = p14.n;
                                    v4_1.b = Long.valueOf(p14.g);
                                    v4_1.i = Long.valueOf(p14.B);
                                    long v5_5 = p14.C;
                                    if (v5_5 <= 0) {
                                        v4_1.a = 0;
                                    } else {
                                        v4_1.a = Long.valueOf(v5_5);
                                    }
                                    v4_1.k = Long.valueOf(p14.b);
                                    v4_1.l = Long.valueOf(p14.c);
                                    v4_1.n = Long.valueOf(p14.t);
                                    vn.com.vng.zalopay.data.cache.model.NotificationGDDao v14_4 = this.a;
                                    long v5_15 = -1;
                                    if (v4_1 != null) {
                                        try {
                                            com.google.gson.JsonObject v7_2 = v14_4.a().g;
                                            v5_15 = v7_2.g(v4_1, v7_2.f.b());
                                        } catch (com.google.gson.JsonObject v7_3) {
                                            v7_3.toString();
                                            if ((v7_3 instanceof android.database.sqlite.SQLiteConstraintException)) {
                                                com.google.gson.JsonObject v7_5 = v4_1.k;
                                                String v8_2 = v7_5.longValue();
                                                java.nio.charset.Charset v10_0 = v4_1.l;
                                                long v11 = v10_0.longValue();
                                                if (v8_2 <= 0) {
                                                    if (v11 <= 0) {
                                                        int v1_2 = 0;
                                                    } else {
                                                        int v1_4 = v14_4.a().g;
                                                        v1_4.getClass();
                                                        int v2_1 = new ub.c(v1_4);
                                                        com.google.gson.JsonObject v7_6 = new Aj.i[0];
                                                        v2_1.K(vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Mtuid.a(v10_0), v7_6);
                                                        v1_2 = v2_1.u();
                                                    }
                                                } else {
                                                    int v1_8 = v14_4.a().g;
                                                    v1_8.getClass();
                                                    int v2_3 = new ub.c(v1_8);
                                                    com.google.gson.JsonObject v7_7 = new Aj.i[0];
                                                    v2_3.K(vn.com.vng.zalopay.data.cache.model.NotificationGDDao$Properties.Mtaid.a(v7_5), v7_7);
                                                    v1_2 = v2_3.u();
                                                }
                                                if (v1_2 != 0) {
                                                    if (v1_2.size() > 0) {
                                                        v3_1 = ((Rt.i) v1_2.get(0));
                                                    }
                                                }
                                                if (!android.text.TextUtils.isEmpty(v4_1.e)) {
                                                    if (v3_1.j != null) {
                                                        if (v3_1.e != null) {
                                                        }
                                                    }
                                                    v4_1.i = v3_1.i;
                                                    v5_15 = v14_4.a().g.j(v4_1);
                                                }
                                            }
                                        }
                                    }
                                    return v5_15;
                                }
                                if (v8_5 != null) {
                                    if (v8_5.getAsInt() == 2) {
                                        String v8_8 = v5_22.a.get("message");
                                        if (v8_8 != null) {
                                            java.nio.charset.Charset v9_4 = new String(android.util.Base64.decode(v8_8.getAsString(), 0), java.nio.charset.StandardCharsets.UTF_16);
                                            v5_22.b = v9_4;
                                            if (!android.text.TextUtils.isEmpty(v9_4)) {
                                                v5_22.a.addProperty("message", v5_22.b);
                                            }
                                        }
                                        com.google.gson.JsonObject v7_12 = v5_22.a.get("displayname");
                                        if (v7_12 != null) {
                                            String v8_14 = new String(android.util.Base64.decode(v7_12.getAsString(), 0), java.nio.charset.StandardCharsets.UTF_16);
                                            v5_22.c = v8_14;
                                            if (!android.text.TextUtils.isEmpty(v8_14)) {
                                                v5_22.a.addProperty("displayname", v5_22.c);
                                            }
                                        }
                                        v5_22.d = 1;
                                        v8_4 = v5_22.a;
                                    } else {
                                        v8_4 = v5_22.a;
                                    }
                                } else {
                                    v8_4 = v5_22.a;
                                }
                            }
                        }
                    }
                    v8_4 = 0;
                }
            } else {
                ck.b.e("Notification is invalid transactionId [%s] userid [%s] destuserid [%s]", new Object[] {Long.valueOf(p14.g), p14.n, p14.o}));
                v4_1 = 0;
            }
        }
    }
}

package vn.ai.trueid.sdk.utils;
public final class TrueIDCaptureMetadata {
    public static final vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata INSTANCE = None;
    private static final int JPEG_QUALITY = 95;
    private static final int SCHEMA_VER = 1;
    private static Ok.y camBack;
    private static Ok.y camFront;
    private static boolean cameraCacheLoaded;
    private static String flowName;
    private static final Object lock;
    private static long prevStepAt;
    private static long sessionStartedAt;
    private static final java.util.HashMap stepCounters;

    static TrueIDCaptureMetadata()
    {
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE = new vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata();
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.lock = new Object();
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.stepCounters = new java.util.HashMap();
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.flowName = "UNKNOWN";
        return;
    }

    private TrueIDCaptureMetadata()
    {
        return;
    }

    private final Double brightness0to1(android.content.Context p5)
    {
        try {
            return Double.valueOf((((double) Og.x.coerceIn(android.provider.Settings$System.getInt(p5.getContentResolver(), "screen_brightness"), 0, 255)) / 4643176031446892544));
        } catch (Exception) {
            return 0;
        }
    }

    private final String buildJson(android.content.Context p14, vn.ai.trueid.sdk.TrueShield.ImageType p15, String p16, String p17, String p18, String p19, int p20, long p21, int p23, int p24, int p25, Long p26, Long p27)
    {
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata v5_0 = "";
        org.json.JSONObject v6_1 = new org.json.JSONObject();
        try {
            v6_1.put("schema_ver", 1);
            int v8_4 = new org.json.JSONObject();
            v8_4.put("request_id", p17);
            v8_4.put("image_uid", p16);
            v8_4.put("flow", p19);
            v8_4.put("step", p18);
            v8_4.put("retry_index", p20);
            v8_4.put("capture_mode", vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.captureModeName());
            org.json.JSONObject vtmp9 = v6_1.put("session", v8_4);
            int v8_14 = new org.json.JSONObject();
            v8_14.put("version", "3.3.18");
            v8_14.put("build_date", "126110614");
            v8_14.put("build_type", "release");
            Object v10_2 = 0;
            try {
                int v11_12 = p14.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
            } catch (Exception) {
                v11_12 = "";
                v8_14.put("vendor_id", v11_12);
                try {
                    int v11_13 = p14.getPackageName();
                } catch (Exception) {
                    v11_13 = "";
                    v8_14.put("host_bundle_id", v11_13);
                    try {
                        int v11_15 = vn.ai.trueid.sdk.utils.Helpers.Companion.getAppVersion();
                    } catch (Exception) {
                        v11_15 = "";
                        v8_14.put("host_app_version", v11_15);
                        org.json.JSONObject vtmp20 = v6_1.put("sdk", v8_14);
                        String v7_13 = new org.json.JSONObject();
                        v7_13.put("platform", "Android");
                        int v11_17 = android.os.Build$VERSION.RELEASE;
                        if (v11_17 == 0) {
                            v11_17 = "";
                        }
                        v7_13.put("os_version", v11_17);
                        int v11_18 = android.os.Build$VERSION.SDK_INT;
                        v7_13.put("api_level", v11_18);
                        int v12_4 = android.os.Build.MODEL;
                        if (v12_4 == 0) {
                            v12_4 = "";
                        }
                        v7_13.put("model_id", v12_4);
                        int v12_5 = android.os.Build.MANUFACTURER;
                        if (v12_5 == 0) {
                            v12_5 = "";
                        }
                        v7_13.put("manufacturer", v12_5);
                        int v12_0 = android.os.Build.BRAND;
                        if (v12_0 == 0) {
                            v12_0 = "";
                        }
                        v7_13.put("brand", v12_0);
                        int v12_1 = android.os.Build.HARDWARE;
                        if (v12_1 == 0) {
                            v12_1 = "";
                        }
                        v7_13.put("hardware", v12_1);
                        if (v11_18 < 31) {
                            v5_0 = org.json.JSONObject.NULL;
                        } else {
                            int v11_0 = androidx.appcompat.widget.m0.j();
                            if (v11_0 != 0) {
                                v5_0 = v11_0;
                            } else {
                            }
                        }
                        v7_13.put("soc_model", v5_0);
                        v7_13.put("cpu_cores", Runtime.getRuntime().availableProcessors());
                        int v8_6 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE;
                        v7_13.put("total_memory_mb", v8_6.totalMemoryMb(p14));
                        v7_13.put("is_tablet", vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.isTablet$trueid_release());
                        v6_1.put("device", v7_13);
                        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata v5_5 = new org.json.JSONObject();
                        v5_5.put("locale", java.util.Locale.getDefault().toString());
                        v5_5.put("timezone_id", java.util.TimeZone.getDefault().getID());
                        v5_5.put("timezone_offset_min", (java.util.TimeZone.getDefault().getOffset(p21) / 60000));
                        int v8_8;
                        int v8_7 = v8_6.telephony(p14);
                        if (v8_7 == 0) {
                            v8_8 = 0;
                        } else {
                            v8_8 = v8_7.getNetworkCountryIso();
                        }
                        if (v8_8 != 0) {
                            v5_5.put("network_country_iso", v8_8);
                            int v8_10 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.telephony(p14);
                            if (v8_10 != 0) {
                                v10_2 = v8_10.getSimCountryIso();
                            }
                            if (v10_2 != null) {
                                v5_5.put("sim_country_iso", v10_2);
                                org.json.JSONObject vtmp53 = v6_1.put("region", v5_5);
                                v6_1.put("screen", this.screenJson(p14));
                                v6_1.put("camera", this.cameraJson(p15));
                                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata v5_9 = new org.json.JSONObject();
                                v5_9.put("bytes", p23);
                                v5_9.put("jpeg_quality", 95);
                                if (p24 > 0) {
                                    v5_9.put("w_px", p24);
                                }
                                if (p25 > 0) {
                                    v5_9.put("h_px", p25);
                                }
                                v6_1.put("image", v5_9);
                                org.json.JSONObject v3_2 = new org.json.JSONObject();
                                v3_2.put("captured_at_utc", vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.iso8601Utc(p21));
                                v3_2.put("device_uptime_ms", android.os.SystemClock.elapsedRealtime());
                                if (p26 != null) {
                                    v3_2.put("since_session_start_ms", p26.longValue());
                                }
                                if (p27 != null) {
                                    v3_2.put("since_prev_step_ms", p27.longValue());
                                }
                                v6_1.put("timing", v3_2);
                                v6_1.put("state", this.stateJson(p14));
                                org.json.JSONObject v0_7 = new org.json.JSONObject();
                                v0_7.put("is_debug_build", 0);
                                Object v2_4 = Integer.valueOf(vn.ai.trueid.sdk.navigation.TrueIDV3.checkSecurityDeviceNative());
                                v0_7.put("security_code", v2_4);
                                org.json.JSONObject vtmp78 = v6_1.put("env", v0_7);
                                return v6_1.toString();
                            } else {
                                v10_2 = org.json.JSONObject.NULL;
                            }
                        } else {
                            v8_8 = org.json.JSONObject.NULL;
                        }
                    }
                    if (v11_15 != 0) {
                    } else {
                    }
                }
                if (v11_13 != 0) {
                } else {
                }
            }
            if (v11_12 != 0) {
            } else {
                v11_12 = "TRUEID";
            }
        } catch (Exception) {
            return "{\"schema_ver\":1,\"error\":\"serialize_failed\"}";
        }
    }

    private final kotlin.Pair camSpecFor(vn.ai.trueid.sdk.TrueShield.ImageType p3)
    {
        kotlin.NoWhenBranchMatchedException v3_9;
        if (p3 != null) {
            v3_9 = Ok.z.a[p3.ordinal()];
        } else {
            v3_9 = -1;
        }
        if (v3_9 == -1) {
            return kotlin.TuplesKt.to(0, 0);
        } else {
            if ((v3_9 == 1) || ((v3_9 == 2) || (v3_9 == 3))) {
                return kotlin.TuplesKt.to(vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.camFront, "front");
            } else {
                if ((v3_9 != 4) && (v3_9 != 5)) {
                    throw new kotlin.NoWhenBranchMatchedException();
                } else {
                    return kotlin.TuplesKt.to(vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.camBack, "back");
                }
            }
        }
    }

    private final org.json.JSONObject cameraJson(vn.ai.trueid.sdk.TrueShield.ImageType p4)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        Object v4_6 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.camSpecFor(p4);
        String v1_6 = ((Ok.y) v4_6.component1());
        Object v4_1 = ((String) v4_6.component2());
        if (v4_1 == null) {
            v4_1 = org.json.JSONObject.NULL;
        }
        Object v4_2;
        v0_1.put("position", v4_1);
        if (v1_6 == null) {
            v4_2 = org.json.JSONObject.NULL;
        } else {
            v4_2 = v1_6.a;
            if (v4_2 == null) {
            }
        }
        Object v4_3;
        v0_1.put("id", v4_2);
        if (v1_6 == null) {
            v4_3 = org.json.JSONObject.NULL;
        } else {
            v4_3 = v1_6.b;
            if (v4_3 == null) {
            }
        }
        Object v4_4;
        v0_1.put("aperture_f", v4_3);
        if (v1_6 == null) {
            v4_4 = org.json.JSONObject.NULL;
        } else {
            v4_4 = v1_6.c;
            if (v4_4 == null) {
            }
        }
        Object v4_5;
        v0_1.put("focal_length_mm", v4_4);
        if (v1_6 == null) {
            v4_5 = org.json.JSONObject.NULL;
        } else {
            v4_5 = v1_6.d;
            if (v4_5 == null) {
            }
        }
        Object v4_7;
        v0_1.put("sensor_w_mm", v4_5);
        if (v1_6 == null) {
            v4_7 = org.json.JSONObject.NULL;
        } else {
            v4_7 = v1_6.e;
            if (v4_7 == null) {
            }
        }
        Object v4_8;
        v0_1.put("sensor_h_mm", v4_7);
        if (v1_6 == null) {
            v4_8 = org.json.JSONObject.NULL;
        } else {
            v4_8 = v1_6.f;
            if (v4_8 == null) {
            }
        }
        Object v4_9;
        v0_1.put("iso_range_min", v4_8);
        if (v1_6 == null) {
            v4_9 = org.json.JSONObject.NULL;
        } else {
            v4_9 = v1_6.g;
            if (v4_9 == null) {
            }
        }
        Object v4_10;
        v0_1.put("iso_range_max", v4_9);
        if (v1_6 == null) {
            v4_10 = org.json.JSONObject.NULL;
        } else {
            v4_10 = v1_6.h;
            if (v4_10 == null) {
            }
        }
        v0_1.put("hw_level", v4_10);
        Object v4_11 = org.json.JSONObject.NULL;
        v0_1.put("iso", v4_11);
        v0_1.put("exposure_time_us", v4_11);
        v0_1.put("lens_position", v4_11);
        v0_1.put("wb_mode", v4_11);
        return v0_1;
    }

    private final String captureModeName()
    {
        String v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        if (!v0_0.isEkycCaptureMode$trueid_release()) {
            if (!v0_0.isEnrollCaptureMode$trueid_release()) {
                return "normal";
            } else {
                return "enroll_capture";
            }
        } else {
            return "ekyc_capture";
        }
    }

    private final String exifDateString(long p4)
    {
        return new java.text.SimpleDateFormat("yyyy:MM:dd HH:mm:ss", java.util.Locale.US).format(new java.util.Date(p4));
    }

    private final Object freeMemoryMb(android.content.Context p5)
    {
        try {
            Object v5_3 = ((android.app.ActivityManager) p5.getSystemService("activity"));
            long v0_2 = new android.app.ActivityManager$MemoryInfo();
            v5_3.getMemoryInfo(v0_2);
            Object v5_1 = Long.valueOf((v0_2.availMem / 1048576));
        } catch (Exception) {
            v5_1 = org.json.JSONObject.NULL;
        }
        return v5_1;
    }

    private final String iso8601Utc(long p4)
    {
        java.text.SimpleDateFormat v0_1 = new java.text.SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", java.util.Locale.US);
        v0_1.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return v0_1.format(new java.util.Date(p4));
    }

    private final void loadCameraCharacteristicsOnce(android.content.Context p13)
    {
        try {
            if (!vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.cameraCacheLoaded) {
                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.cameraCacheLoaded = 1;
                try {
                    int v13_2;
                    int v13_1 = p13.getSystemService("camera");
                } catch (Exception) {
                    return;
                }
                if (!(v13_1 instanceof android.hardware.camera2.CameraManager)) {
                    v13_2 = 0;
                } else {
                    v13_2 = ((android.hardware.camera2.CameraManager) v13_1);
                }
                if (v13_2 != 0) {
                    String[] v0_1 = v13_2.getCameraIdList();
                    int v3 = v0_1.length;
                    int v5 = 0;
                    while (v5 < v3) {
                        int v9_1;
                        String v6_12 = v0_1[v5];
                        int v7_4 = v13_2.getCameraCharacteristics(v6_12);
                        Ok.y v8_2 = ((Integer) v7_4.get(android.hardware.camera2.CameraCharacteristics.LENS_FACING));
                        if (v8_2 != null) {
                            if (v8_2.intValue() != 0) {
                                v9_1 = 0;
                            } else {
                                v9_1 = 1;
                            }
                        } else {
                        }
                        Ok.y v8_4;
                        if (v8_2 != null) {
                            if (v8_2.intValue() != 1) {
                                v8_4 = 0;
                            } else {
                                v8_4 = 1;
                            }
                        } else {
                        }
                        if (((v9_1 != 0) || (v8_4 != null)) && (((v9_1 == 0) || (vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.camFront == null)) && ((v8_4 == null) || (vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.camBack == null)))) {
                            Ok.y v8_7 = new Ok.y();
                            v8_7.a = v6_12;
                            v8_7.b = 0;
                            v8_7.c = 0;
                            v8_7.d = 0;
                            v8_7.e = 0;
                            v8_7.f = 0;
                            v8_7.g = 0;
                            v8_7.h = 0;
                            String v6_15 = ((float[]) v7_4.get(android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES));
                            if (v6_15 != null) {
                                String v6_16 = kotlin.collections.y.firstOrNull(v6_15);
                                if (v6_16 != null) {
                                    v8_7.b = Double.valueOf(((double) v6_16.floatValue()));
                                }
                            }
                            String v6_21 = ((float[]) v7_4.get(android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS));
                            if (v6_21 != null) {
                                String v6_22 = kotlin.collections.y.firstOrNull(v6_21);
                                if (v6_22 != null) {
                                    v8_7.c = Double.valueOf(((double) v6_22.floatValue()));
                                }
                            }
                            String v6_27 = ((android.util.SizeF) v7_4.get(android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE));
                            if (v6_27 != null) {
                                v8_7.d = Double.valueOf(((double) v6_27.getWidth()));
                                v8_7.e = Double.valueOf(((double) v6_27.getHeight()));
                            }
                            String v6_4 = ((android.util.Range) v7_4.get(android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE));
                            if (v6_4 != null) {
                                v8_7.f = ((Integer) v6_4.getLower());
                                v8_7.g = ((Integer) v6_4.getUpper());
                            }
                            String v6_10;
                            String v6_9 = ((Integer) v7_4.get(android.hardware.camera2.CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL));
                            if (v6_9 != null) {
                                if (v6_9.intValue() != 2) {
                                    if (v6_9 != null) {
                                        if (v6_9.intValue() == 0) {
                                            v6_10 = "limited";
                                            v8_7.h = v6_10;
                                            if (v9_1 == 0) {
                                                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.camBack = v8_7;
                                            } else {
                                                vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.camFront = v8_7;
                                            }
                                            v5++;
                                        }
                                    } else {
                                    }
                                    if (v6_9 != null) {
                                        if (v6_9.intValue() == 1) {
                                            v6_10 = "full";
                                        }
                                    } else {
                                    }
                                    if (v6_9 != null) {
                                        if (v6_9.intValue() == 3) {
                                            v6_10 = "level3";
                                        }
                                    } else {
                                    }
                                    v6_10 = 0;
                                } else {
                                    v6_10 = "legacy";
                                }
                            } else {
                            }
                        }
                    }
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        } catch (int v13_3) {
            throw v13_3;
        }
    }

    private final Object safeInt(kotlin.jvm.functions.Function0 p1)
    {
        try {
            Object v1_1 = p1.invoke();
        } catch (Exception) {
            v1_1 = org.json.JSONObject.NULL;
        }
        return v1_1;
    }

    private final Object safeOrNull(kotlin.jvm.functions.Function0 p1)
    {
        try {
            Object v1_2 = ((String) p1.invoke());
        } catch (Exception) {
            v1_2 = org.json.JSONObject.NULL;
            return v1_2;
        }
        if (v1_2 != null) {
            return v1_2;
        } else {
            v1_2 = org.json.JSONObject.NULL;
            return v1_2;
        }
    }

    private final String safeStr(kotlin.jvm.functions.Function0 p2)
    {
        try {
            String v2_2 = ((String) p2.invoke());
        } catch (Exception) {
            return "";
        }
        if (v2_2 != null) {
            return v2_2;
        } else {
            return "";
        }
    }

    private final org.json.JSONObject screenJson(android.content.Context p6)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        try {
            String v1_3 = p6.getResources().getDisplayMetrics();
            v0_1.put("w_px", v1_3.widthPixels);
            v0_1.put("h_px", v1_3.heightPixels);
            v0_1.put("scale", ((double) v1_3.density));
            v0_1.put("dpi", v1_3.densityDpi);
            Object v6_1 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE.brightness0to1(p6);
        } catch (Exception) {
            return v0_1;
        }
        if (v6_1 == null) {
            v6_1 = org.json.JSONObject.NULL;
        }
        v0_1.put("brightness", v6_1);
        return v0_1;
    }

    private final String softwareTag()
    {
        return androidx.activity.result.e.h(android.os.Build$VERSION.SDK_INT, "TrueIDSDK/3.3.18 (Android ", android.os.Build$VERSION.RELEASE, "; API ", ")");
    }

    private final org.json.JSONObject stateJson(android.content.Context p9)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        try {
            String v1_15;
            String v1_7 = p9.getSystemService("batterymanager");
            String v3_0 = 0;
        } catch (Exception) {
            return v0_1;
        }
        if (!(v1_7 instanceof android.os.BatteryManager)) {
            v1_15 = 0;
        } else {
            v1_15 = ((android.os.BatteryManager) v1_7);
        }
        String v1_0;
        if (v1_15 == null) {
            v1_0 = 0;
        } else {
            v1_0 = Integer.valueOf(v1_15.getIntProperty(4));
        }
        if ((v1_0 == null) || (v1_0.intValue() < 0)) {
            v1_0 = org.json.JSONObject.NULL;
        }
        v0_1.put("battery_pct", v1_0);
        String v1_5 = p9.registerReceiver(0, new android.content.IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (v1_5 == null) {
            String v1_6 = org.json.JSONObject.NULL;
            v0_1.put("is_charging", v1_6);
            v0_1.put("battery_temp_c", v1_6);
        } else {
            int v5_2;
            int v5_1 = v1_5.getIntExtra("status", -1);
            if (v5_1 == 2) {
                v5_2 = 1;
            } else {
                if (v5_1 != 5) {
                    v5_2 = 0;
                } else {
                }
            }
            String v1_9;
            v0_1.put("is_charging", v5_2);
            String v1_8 = v1_5.getIntExtra("temperature", -2147483648);
            if (v1_8 != -2147483648) {
                v1_9 = Double.valueOf((((double) v1_8) / 4621819117588971520));
            } else {
                v1_9 = org.json.JSONObject.NULL;
            }
            v0_1.put("battery_temp_c", v1_9);
        }
        String v1_11 = p9.getSystemService("power");
        if ((v1_11 instanceof android.os.PowerManager)) {
            v3_0 = ((android.os.PowerManager) v1_11);
        }
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata v2_6;
        if (v3_0 == null) {
            v2_6 = org.json.JSONObject.NULL;
        } else {
            v2_6 = Boolean.valueOf(v3_0.isPowerSaveMode());
        }
        v0_1.put("is_low_power_mode", v2_6);
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata v2_8 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.INSTANCE;
        v0_1.put("thermal_state", v2_8.thermalStateName(v3_0));
        String v1_16 = new android.os.StatFs(p9.getFilesDir().getAbsolutePath());
        v0_1.put("free_disk_mb", ((v1_16.getAvailableBlocksLong() * v1_16.getBlockSizeLong()) / 1048576));
        v0_1.put("free_memory_mb", v2_8.freeMemoryMb(p9));
        return v0_1;
    }

    private final String stepName(vn.ai.trueid.sdk.TrueShield.ImageType p3)
    {
        kotlin.NoWhenBranchMatchedException v3_9;
        if (p3 != null) {
            v3_9 = Ok.z.a[p3.ordinal()];
        } else {
            v3_9 = -1;
        }
        if (v3_9 == -1) {
            return "UNKNOWN";
        } else {
            if (v3_9 == 1) {
                return "SELFIE1";
            } else {
                if (v3_9 == 2) {
                    return "SELFIE2";
                } else {
                    if (v3_9 == 3) {
                        return "SELFIE3";
                    } else {
                        if (v3_9 == 4) {
                            return "ID_FRONT";
                        } else {
                            if (v3_9 != 5) {
                                throw new kotlin.NoWhenBranchMatchedException();
                            } else {
                                return "ID_BACK";
                            }
                        }
                    }
                }
            }
        }
    }

    private final android.telephony.TelephonyManager telephony(android.content.Context p3)
    {
        try {
            android.telephony.TelephonyManager v3_1 = p3.getSystemService("phone");
        } catch (Exception) {
            return 0;
        }
        if (!(v3_1 instanceof android.telephony.TelephonyManager)) {
            return 0;
        } else {
            return ((android.telephony.TelephonyManager) v3_1);
        }
    }

    private final Object thermalStateName(android.os.PowerManager p3)
    {
        if ((p3 != null) && (android.os.Build$VERSION.SDK_INT >= 29)) {
            switch (p3.getCurrentThermalStatus()) {
                case 0:
                    return "nominal";
                case 1:
                case 2:
                    return "fair";
                case 3:
                    return "serious";
                case 4:
                case 5:
                case 6:
                    return "critical";
                default:
                    return "unknown";
            }
        } else {
            return org.json.JSONObject.NULL;
        }
    }

    private final Object totalMemoryMb(android.content.Context p5)
    {
        try {
            Object v5_3 = ((android.app.ActivityManager) p5.getSystemService("activity"));
            long v0_2 = new android.app.ActivityManager$MemoryInfo();
            v5_3.getMemoryInfo(v0_2);
            Object v5_1 = Long.valueOf((v0_2.totalMem / 1048576));
        } catch (Exception) {
            v5_1 = org.json.JSONObject.NULL;
        }
        return v5_1;
    }

    public final void beginSession()
    {
        try {
            vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.sessionStartedAt = System.currentTimeMillis();
            vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.prevStepAt = 0;
            vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.stepCounters.clear();
            return;
        } catch (Throwable v1_0) {
            throw v1_0;
        }
    }

    public final vn.ai.trueid.sdk.utils.TrueIDExifData buildExifData(android.content.Context p28, vn.ai.trueid.sdk.TrueShield.ImageType p29, String p30, int p31, int p32, int p33)
    {
        long v9_0 = System.currentTimeMillis();
        String v6 = this.stepName(p29);
        String v18 = java.util.UUID.randomUUID().toString();
        int v4_2;
        String v0_0 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.stepCounters;
        int v4_1 = ((Integer) v0_0.get(v6));
        if (v4_1 != 0) {
            v4_2 = v4_1.intValue();
        } else {
            v4_2 = 0;
        }
        int v8 = v4_2;
        String v14_0;
        v0_0.put(v6, Integer.valueOf((v8 + 1)));
        int v4_5 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.sessionStartedAt;
        String v16_0 = 0;
        if (v4_5 <= 0) {
            v14_0 = 0;
        } else {
            v14_0 = Long.valueOf((v9_0 - v4_5));
        }
        String v15_0;
        int v4_7 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.prevStepAt;
        if (v4_7 <= 0) {
            v15_0 = 0;
        } else {
            v15_0 = Long.valueOf((v9_0 - v4_7));
        }
        vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.prevStepAt = v9_0;
        String v7 = vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.flowName;
        this = this.loadCameraCharacteristicsOnce(p28);
        String v26 = this.buildJson(p28, p29, v18, p30, v6, v7, v8, v9_0, p31, p32, p33, v14_0, v15_0);
        String v0_6 = android.os.Build.MANUFACTURER;
        if (v0_6 == null) {
            v0_6 = "";
        }
        String v12_1 = v0_6;
        String v0_7 = android.os.Build.MODEL;
        if (v0_7 == null) {
            v0_7 = "";
        }
        int v20;
        String v13_1 = v0_7;
        String v14_1 = this.softwareTag();
        String v15_1 = this.exifDateString(v9_0);
        String v0_10 = new StringBuilder("TrueID ");
        v0_10.append(v7);
        v0_10.append(" ");
        v0_10.append(v6);
        v0_10.append(" try");
        v0_10.append(v8);
        String v0_11 = v0_10.toString();
        String v19 = kotlin.text.StringsKt.q(3, String.valueOf((v9_0 % ((long) 1000))));
        Integer v2_13 = ((Ok.y) this.camSpecFor(p29).getFirst());
        if (v2_13 == null) {
            v20 = 0;
        } else {
            v20 = v2_13.b;
        }
        int v21;
        Integer v2_2 = ((Ok.y) this.camSpecFor(p29).getFirst());
        if (v2_2 == null) {
            v21 = 0;
        } else {
            v21 = v2_2.c;
        }
        int v24;
        if (p32 <= 0) {
            v24 = 0;
        } else {
            v24 = Integer.valueOf(p32);
        }
        if (p33 > 0) {
            v16_0 = Integer.valueOf(p33);
        }
        return new vn.ai.trueid.sdk.utils.TrueIDExifData(v12_1, v13_1, v14_1, v15_1, v0_11, "TrueID", v18, v19, v20, v21, 0, 0, v24, v16_0, v26);
    }

    public final void setFlow(String p2)
    {
        try {
            vn.ai.trueid.sdk.utils.TrueIDCaptureMetadata.flowName = p2;
            return;
        } catch (Throwable v2_1) {
            throw v2_1;
        }
    }
}

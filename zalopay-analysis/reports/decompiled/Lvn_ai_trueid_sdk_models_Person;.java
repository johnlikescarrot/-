package vn.ai.trueid.sdk.models;
public final class Person {
    private final String address;
    private final String dob;
    private final String doi;
    private final String dueDate;
    private final String fullname;
    private final String gender;
    private final String givenPlace;
    private final vn.ai.trueid.sdk.models.ResultValue idAddressDistrict;
    private final vn.ai.trueid.sdk.models.ResultValue idAddressProvince;
    private final vn.ai.trueid.sdk.models.ResultValue idAddressWard;
    private final String idNumber;
    private final vn.ai.trueid.sdk.models.ResultValue idOriginDistrict;
    private final vn.ai.trueid.sdk.models.ResultValue idOriginProvince;
    private final vn.ai.trueid.sdk.models.ResultValue idOriginWard;
    private final int idType;
    private final String origin;
    private final String qrText;

    public Person()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131071, 0);
        return;
    }

    public Person(String p1, int p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, vn.ai.trueid.sdk.models.ResultValue p11, vn.ai.trueid.sdk.models.ResultValue p12, vn.ai.trueid.sdk.models.ResultValue p13, vn.ai.trueid.sdk.models.ResultValue p14, vn.ai.trueid.sdk.models.ResultValue p15, vn.ai.trueid.sdk.models.ResultValue p16, String p17)
    {
        this.idNumber = p1;
        this.idType = p2;
        this.gender = p3;
        this.dob = p4;
        this.fullname = p5;
        this.address = p6;
        this.doi = p7;
        this.givenPlace = p8;
        this.origin = p9;
        this.dueDate = p10;
        this.idOriginWard = p11;
        this.idOriginDistrict = p12;
        this.idOriginProvince = p13;
        this.idAddressProvince = p14;
        this.idAddressDistrict = p15;
        this.idAddressWard = p16;
        this.qrText = p17;
        return;
    }

    public synthetic Person(String p19, int p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, vn.ai.trueid.sdk.models.ResultValue p29, vn.ai.trueid.sdk.models.ResultValue p30, vn.ai.trueid.sdk.models.ResultValue p31, vn.ai.trueid.sdk.models.ResultValue p32, vn.ai.trueid.sdk.models.ResultValue p33, vn.ai.trueid.sdk.models.ResultValue p34, String p35, int p36, kotlin.jvm.internal.DefaultConstructorMarker p37)
    {
        vn.ai.trueid.sdk.models.ResultValue v1_1;
        if ((p36 & 1) == 0) {
            v1_1 = p19;
        } else {
            v1_1 = "";
        }
        int v3_1;
        if ((p36 & 2) == 0) {
            v3_1 = p20;
        } else {
            v3_1 = 0;
        }
        String v4_1;
        if ((p36 & 4) == 0) {
            v4_1 = p21;
        } else {
            v4_1 = "";
        }
        String v5_1;
        if ((p36 & 8) == 0) {
            v5_1 = p22;
        } else {
            v5_1 = "";
        }
        String v6_1;
        if ((p36 & 16) == 0) {
            v6_1 = p23;
        } else {
            v6_1 = "";
        }
        String v7_1;
        if ((p36 & 32) == 0) {
            v7_1 = p24;
        } else {
            v7_1 = "";
        }
        String v8_1;
        if ((p36 & 64) == 0) {
            v8_1 = p25;
        } else {
            v8_1 = "";
        }
        String v9_1;
        if ((p36 & 128) == 0) {
            v9_1 = p26;
        } else {
            v9_1 = "";
        }
        String v10_1;
        if ((p36 & 256) == 0) {
            v10_1 = p27;
        } else {
            v10_1 = "";
        }
        String v11_1;
        if ((p36 & 512) == 0) {
            v11_1 = p28;
        } else {
            v11_1 = "";
        }
        vn.ai.trueid.sdk.models.ResultValue v12_1;
        if ((p36 & 1024) == 0) {
            v12_1 = p29;
        } else {
            v12_1 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v14_1;
        if ((p36 & 2048) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v15_1;
        if ((p36 & 4096) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v13_1;
        if ((p36 & 8192) == 0) {
            v13_1 = p32;
        } else {
            v13_1 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v1_3;
        if ((p36 & 16384) == 0) {
            v1_3 = p33;
        } else {
            v1_3 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v16_2;
        if ((p36 & 32768) == 0) {
            v16_2 = p34;
        } else {
            v16_2 = 0;
        }
        String v36_1;
        if ((p36 & 65536) == 0) {
            v36_1 = p35;
        } else {
            v36_1 = "";
        }
        this(v1_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v14_1, v15_1, v13_1, v1_3, v16_2, v36_1);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.Person copy$default(vn.ai.trueid.sdk.models.Person p17, String p18, int p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, vn.ai.trueid.sdk.models.ResultValue p28, vn.ai.trueid.sdk.models.ResultValue p29, vn.ai.trueid.sdk.models.ResultValue p30, vn.ai.trueid.sdk.models.ResultValue p31, vn.ai.trueid.sdk.models.ResultValue p32, vn.ai.trueid.sdk.models.ResultValue p33, String p34, int p35, Object p36)
    {
        vn.ai.trueid.sdk.models.ResultValue v2_1;
        if ((p35 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.idNumber;
        }
        int v3_1;
        if ((p35 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.idType;
        }
        String v4_1;
        if ((p35 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.gender;
        }
        String v5_1;
        if ((p35 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.dob;
        }
        String v6_1;
        if ((p35 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.fullname;
        }
        String v7_1;
        if ((p35 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.address;
        }
        String v8_1;
        if ((p35 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.doi;
        }
        String v9_1;
        if ((p35 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.givenPlace;
        }
        String v10_1;
        if ((p35 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.origin;
        }
        String v11_1;
        if ((p35 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.dueDate;
        }
        vn.ai.trueid.sdk.models.ResultValue v12_1;
        if ((p35 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.idOriginWard;
        }
        vn.ai.trueid.sdk.models.ResultValue v13_1;
        if ((p35 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.idOriginDistrict;
        }
        vn.ai.trueid.sdk.models.ResultValue v14_1;
        if ((p35 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.idOriginProvince;
        }
        vn.ai.trueid.sdk.models.ResultValue v15_1;
        if ((p35 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.idAddressProvince;
        }
        vn.ai.trueid.sdk.models.ResultValue v2_3;
        vn.ai.trueid.sdk.models.Person v18_2 = v2_1;
        if ((p35 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.idAddressDistrict;
        }
        String v1_1;
        if ((p35 & 32768) == 0) {
            v1_1 = p33;
        } else {
            v1_1 = p17.idAddressWard;
        }
        vn.ai.trueid.sdk.models.ResultValue v31_1;
        String v22_1;
        String v34_1;
        vn.ai.trueid.sdk.models.Person v18_1;
        String v25_1;
        int v20_1;
        String v28_1;
        vn.ai.trueid.sdk.models.ResultValue v32_1;
        String v23_1;
        vn.ai.trueid.sdk.models.ResultValue v35_1;
        String v26_1;
        vn.ai.trueid.sdk.models.ResultValue v30_1;
        String v21_1;
        vn.ai.trueid.sdk.models.ResultValue v29_1;
        vn.ai.trueid.sdk.models.ResultValue v33_1;
        vn.ai.trueid.sdk.models.Person v19_1;
        String v24_1;
        String v27_1;
        if ((p35 & 65536) == 0) {
            v35_1 = p34;
            v34_1 = v1_1;
            v19_1 = v18_2;
            v18_1 = p17;
            v33_1 = v2_3;
            v20_1 = v3_1;
            v21_1 = v4_1;
            v22_1 = v5_1;
            v23_1 = v6_1;
            v24_1 = v7_1;
            v25_1 = v8_1;
            v26_1 = v9_1;
            v27_1 = v10_1;
            v28_1 = v11_1;
            v29_1 = v12_1;
            v30_1 = v13_1;
            v31_1 = v14_1;
            v32_1 = v15_1;
        } else {
            v34_1 = v1_1;
            v35_1 = p17.qrText;
            v33_1 = v2_3;
            v20_1 = v3_1;
            v21_1 = v4_1;
            v22_1 = v5_1;
            v23_1 = v6_1;
            v24_1 = v7_1;
            v25_1 = v8_1;
            v26_1 = v9_1;
            v27_1 = v10_1;
            v28_1 = v11_1;
            v29_1 = v12_1;
            v30_1 = v13_1;
            v31_1 = v14_1;
            v32_1 = v15_1;
            v19_1 = v18_2;
            v18_1 = p17;
        }
        return v18_1.copy(v19_1, v20_1, v21_1, v22_1, v23_1, v24_1, v25_1, v26_1, v27_1, v28_1, v29_1, v30_1, v31_1, v32_1, v33_1, v34_1, v35_1);
    }

    public final String component1()
    {
        return this.idNumber;
    }

    public final String component10()
    {
        return this.dueDate;
    }

    public final vn.ai.trueid.sdk.models.ResultValue component11()
    {
        return this.idOriginWard;
    }

    public final vn.ai.trueid.sdk.models.ResultValue component12()
    {
        return this.idOriginDistrict;
    }

    public final vn.ai.trueid.sdk.models.ResultValue component13()
    {
        return this.idOriginProvince;
    }

    public final vn.ai.trueid.sdk.models.ResultValue component14()
    {
        return this.idAddressProvince;
    }

    public final vn.ai.trueid.sdk.models.ResultValue component15()
    {
        return this.idAddressDistrict;
    }

    public final vn.ai.trueid.sdk.models.ResultValue component16()
    {
        return this.idAddressWard;
    }

    public final String component17()
    {
        return this.qrText;
    }

    public final int component2()
    {
        return this.idType;
    }

    public final String component3()
    {
        return this.gender;
    }

    public final String component4()
    {
        return this.dob;
    }

    public final String component5()
    {
        return this.fullname;
    }

    public final String component6()
    {
        return this.address;
    }

    public final String component7()
    {
        return this.doi;
    }

    public final String component8()
    {
        return this.givenPlace;
    }

    public final String component9()
    {
        return this.origin;
    }

    public final vn.ai.trueid.sdk.models.Person copy(String p19, int p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, vn.ai.trueid.sdk.models.ResultValue p29, vn.ai.trueid.sdk.models.ResultValue p30, vn.ai.trueid.sdk.models.ResultValue p31, vn.ai.trueid.sdk.models.ResultValue p32, vn.ai.trueid.sdk.models.ResultValue p33, vn.ai.trueid.sdk.models.ResultValue p34, String p35)
    {
        return new vn.ai.trueid.sdk.models.Person(p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.Person)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.idNumber, ((vn.ai.trueid.sdk.models.Person) p5).idNumber)) {
                    if (this.idType == ((vn.ai.trueid.sdk.models.Person) p5).idType) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.gender, ((vn.ai.trueid.sdk.models.Person) p5).gender)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.dob, ((vn.ai.trueid.sdk.models.Person) p5).dob)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.fullname, ((vn.ai.trueid.sdk.models.Person) p5).fullname)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.address, ((vn.ai.trueid.sdk.models.Person) p5).address)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.doi, ((vn.ai.trueid.sdk.models.Person) p5).doi)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.givenPlace, ((vn.ai.trueid.sdk.models.Person) p5).givenPlace)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.origin, ((vn.ai.trueid.sdk.models.Person) p5).origin)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.dueDate, ((vn.ai.trueid.sdk.models.Person) p5).dueDate)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.idOriginWard, ((vn.ai.trueid.sdk.models.Person) p5).idOriginWard)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.idOriginDistrict, ((vn.ai.trueid.sdk.models.Person) p5).idOriginDistrict)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.idOriginProvince, ((vn.ai.trueid.sdk.models.Person) p5).idOriginProvince)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.idAddressProvince, ((vn.ai.trueid.sdk.models.Person) p5).idAddressProvince)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.idAddressDistrict, ((vn.ai.trueid.sdk.models.Person) p5).idAddressDistrict)) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.idAddressWard, ((vn.ai.trueid.sdk.models.Person) p5).idAddressWard)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.qrText, ((vn.ai.trueid.sdk.models.Person) p5).qrText)) {
                                                                                    return 1;
                                                                                } else {
                                                                                    return 0;
                                                                                }
                                                                            } else {
                                                                                return 0;
                                                                            }
                                                                        } else {
                                                                            return 0;
                                                                        }
                                                                    } else {
                                                                        return 0;
                                                                    }
                                                                } else {
                                                                    return 0;
                                                                }
                                                            } else {
                                                                return 0;
                                                            }
                                                        } else {
                                                            return 0;
                                                        }
                                                    } else {
                                                        return 0;
                                                    }
                                                } else {
                                                    return 0;
                                                }
                                            } else {
                                                return 0;
                                            }
                                        } else {
                                            return 0;
                                        }
                                    } else {
                                        return 0;
                                    }
                                } else {
                                    return 0;
                                }
                            } else {
                                return 0;
                            }
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final String getAddress()
    {
        return this.address;
    }

    public final String getDob()
    {
        return this.dob;
    }

    public final String getDoi()
    {
        return this.doi;
    }

    public final String getDueDate()
    {
        return this.dueDate;
    }

    public final String getFullname()
    {
        return this.fullname;
    }

    public final String getGender()
    {
        return this.gender;
    }

    public final String getGivenPlace()
    {
        return this.givenPlace;
    }

    public final vn.ai.trueid.sdk.models.ResultValue getIdAddressDistrict()
    {
        return this.idAddressDistrict;
    }

    public final vn.ai.trueid.sdk.models.ResultValue getIdAddressProvince()
    {
        return this.idAddressProvince;
    }

    public final vn.ai.trueid.sdk.models.ResultValue getIdAddressWard()
    {
        return this.idAddressWard;
    }

    public final String getIdNumber()
    {
        return this.idNumber;
    }

    public final vn.ai.trueid.sdk.models.ResultValue getIdOriginDistrict()
    {
        return this.idOriginDistrict;
    }

    public final vn.ai.trueid.sdk.models.ResultValue getIdOriginProvince()
    {
        return this.idOriginProvince;
    }

    public final vn.ai.trueid.sdk.models.ResultValue getIdOriginWard()
    {
        return this.idOriginWard;
    }

    public final int getIdType()
    {
        return this.idType;
    }

    public final String getOrigin()
    {
        return this.origin;
    }

    public final String getQrText()
    {
        return this.qrText;
    }

    public int hashCode()
    {
        vn.ai.trueid.sdk.models.ResultValue v2_8;
        int v0_9 = e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(Kb.e.v(this.idType, (this.idNumber.hashCode() * 31), 31), 31, this.gender), 31, this.dob), 31, this.fullname), 31, this.address), 31, this.doi), 31, this.givenPlace), 31, this.origin), 31, this.dueDate);
        vn.ai.trueid.sdk.models.ResultValue v2_7 = this.idOriginWard;
        int v3 = 0;
        if (v2_7 != null) {
            v2_8 = v2_7.hashCode();
        } else {
            v2_8 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v2_10;
        int v0_11 = ((v0_9 + v2_8) * 31);
        vn.ai.trueid.sdk.models.ResultValue v2_9 = this.idOriginDistrict;
        if (v2_9 != null) {
            v2_10 = v2_9.hashCode();
        } else {
            v2_10 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v2_13;
        int v0_14 = ((v0_11 + v2_10) * 31);
        vn.ai.trueid.sdk.models.ResultValue v2_11 = this.idOriginProvince;
        if (v2_11 != null) {
            v2_13 = v2_11.hashCode();
        } else {
            v2_13 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v2_15;
        int v0_16 = ((v0_14 + v2_13) * 31);
        vn.ai.trueid.sdk.models.ResultValue v2_14 = this.idAddressProvince;
        if (v2_14 != null) {
            v2_15 = v2_14.hashCode();
        } else {
            v2_15 = 0;
        }
        vn.ai.trueid.sdk.models.ResultValue v2_17;
        int v0_18 = ((v0_16 + v2_15) * 31);
        vn.ai.trueid.sdk.models.ResultValue v2_16 = this.idAddressDistrict;
        if (v2_16 != null) {
            v2_17 = v2_16.hashCode();
        } else {
            v2_17 = 0;
        }
        int v0_20 = ((v0_18 + v2_17) * 31);
        vn.ai.trueid.sdk.models.ResultValue v2_18 = this.idAddressWard;
        if (v2_18 != null) {
            v3 = v2_18.hashCode();
        }
        return (this.qrText.hashCode() + ((v0_20 + v3) * 31));
    }

    public final java.util.Map toMap()
    {
        java.util.Map v1_12;
        kotlin.Pair v3 = kotlin.TuplesKt.to("idNumber", this.idNumber);
        kotlin.TuplesKt.to("idType", Integer.valueOf(this.idType));
        kotlin.TuplesKt.to("gender", this.gender);
        kotlin.TuplesKt.to("dob", this.dob);
        kotlin.TuplesKt.to("fullname", this.fullname);
        kotlin.TuplesKt.to("address", this.address);
        kotlin.TuplesKt.to("doi", this.doi);
        kotlin.TuplesKt.to("givenPlace", this.givenPlace);
        kotlin.TuplesKt.to("origin", this.origin);
        kotlin.TuplesKt.to("dueDate", this.dueDate);
        java.util.Map v1_11 = this.idOriginWard;
        if (v1_11 == null) {
            v1_12 = 0;
        } else {
            v1_12 = v1_11.toMap();
        }
        java.util.Map v1_15;
        kotlin.TuplesKt.to("idOriginWard", v1_12);
        java.util.Map v1_14 = this.idOriginDistrict;
        if (v1_14 == null) {
            v1_15 = 0;
        } else {
            v1_15 = v1_14.toMap();
        }
        java.util.Map v1_17;
        kotlin.TuplesKt.to("idOriginDistrict", v1_15);
        java.util.Map v1_16 = this.idOriginProvince;
        if (v1_16 == null) {
            v1_17 = 0;
        } else {
            v1_17 = v1_16.toMap();
        }
        java.util.Map v1_19;
        kotlin.TuplesKt.to("idOriginProvince", v1_17);
        java.util.Map v1_18 = this.idAddressProvince;
        if (v1_18 == null) {
            v1_19 = 0;
        } else {
            v1_19 = v1_18.toMap();
        }
        String v2_11;
        java.util.Map v1_21 = kotlin.TuplesKt.to("idAddressProvince", v1_19);
        String v2_10 = this.idAddressDistrict;
        if (v2_10 == null) {
            v2_11 = 0;
        } else {
            v2_11 = v2_10.toMap();
        }
        String v2_14;
        kotlin.Pair v17 = v1_21;
        kotlin.TuplesKt.to("idAddressDistrict", v2_11);
        String v2_13 = this.idAddressWard;
        if (v2_13 == null) {
            v2_14 = 0;
        } else {
            v2_14 = v2_13.toMap();
        }
        kotlin.TuplesKt.to("idAddressWard", v2_14);
        kotlin.Pair v16 = v17;
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v3, kotlin.TuplesKt.to("qrText", this.qrText)}));
    }

    public String toString()
    {
        String v3 = this.gender;
        String v4 = this.dob;
        String v5 = this.fullname;
        String v6 = this.address;
        String v7 = this.doi;
        String v8 = this.givenPlace;
        String v9 = this.origin;
        String v10 = this.dueDate;
        vn.ai.trueid.sdk.models.ResultValue v11 = this.idOriginWard;
        vn.ai.trueid.sdk.models.ResultValue v12 = this.idOriginDistrict;
        vn.ai.trueid.sdk.models.ResultValue v13 = this.idOriginProvince;
        vn.ai.trueid.sdk.models.ResultValue v16 = this.idAddressDistrict;
        vn.ai.trueid.sdk.models.ResultValue v17 = this.idAddressWard;
        String v18 = this.qrText;
        vn.ai.trueid.sdk.models.ResultValue v19 = this.idAddressProvince;
        String v0_2 = androidx.compose.foundation.layout.G0.p(this.idType, "Person(idNumber=", this.idNumber, ", idType=", ", gender=");
        q9.H.p(v0_2, v3, ", dob=", v4, ", fullname=");
        q9.H.p(v0_2, v5, ", address=", v6, ", doi=");
        q9.H.p(v0_2, v7, ", givenPlace=", v8, ", origin=");
        q9.H.p(v0_2, v9, ", dueDate=", v10, ", idOriginWard=");
        v0_2.append(v11);
        v0_2.append(", idOriginDistrict=");
        v0_2.append(v12);
        v0_2.append(", idOriginProvince=");
        v0_2.append(v13);
        v0_2.append(", idAddressProvince=");
        v0_2.append(v19);
        v0_2.append(", idAddressDistrict=");
        v0_2.append(v16);
        v0_2.append(", idAddressWard=");
        v0_2.append(v17);
        v0_2.append(", qrText=");
        return A1.a.l(v0_2, v18, ")");
    }
}

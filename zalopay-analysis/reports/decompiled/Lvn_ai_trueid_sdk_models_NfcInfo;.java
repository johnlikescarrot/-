package vn.ai.trueid.sdk.models;
public final class NfcInfo {
    private final String address;
    private final String cardNumber;
    private final String character;
    private final String dateOfBirth;
    private final String expiredDate;
    private final String faceImage;
    private final String fatherName;
    private final String hometown;
    private final String issueDate;
    private final String motherName;
    private final String mrz;
    private final String name;
    private final String nation;
    private final String nationality;
    private final String partnerName;
    private final String previousNumber;
    private final String religion;
    private final String sex;

    public NfcInfo()
    {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 262143, 0);
        return;
    }

    public NfcInfo(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14, String p15, String p16, String p17, String p18)
    {
        this.mrz = p1;
        this.cardNumber = p2;
        this.dateOfBirth = p3;
        this.issueDate = p4;
        this.previousNumber = p5;
        this.name = p6;
        this.sex = p7;
        this.nationality = p8;
        this.nation = p9;
        this.religion = p10;
        this.hometown = p11;
        this.address = p12;
        this.character = p13;
        this.expiredDate = p14;
        this.fatherName = p15;
        this.motherName = p16;
        this.partnerName = p17;
        this.faceImage = p18;
        return;
    }

    public synthetic NfcInfo(String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, int p38, kotlin.jvm.internal.DefaultConstructorMarker p39)
    {
        String v1_1;
        if ((p38 & 1) == 0) {
            v1_1 = p20;
        } else {
            v1_1 = "";
        }
        String v3_1;
        if ((p38 & 2) == 0) {
            v3_1 = p21;
        } else {
            v3_1 = "";
        }
        String v4_1;
        if ((p38 & 4) == 0) {
            v4_1 = p22;
        } else {
            v4_1 = "";
        }
        String v5_1;
        if ((p38 & 8) == 0) {
            v5_1 = p23;
        } else {
            v5_1 = "";
        }
        String v6_1;
        if ((p38 & 16) == 0) {
            v6_1 = p24;
        } else {
            v6_1 = "";
        }
        String v7_1;
        if ((p38 & 32) == 0) {
            v7_1 = p25;
        } else {
            v7_1 = "";
        }
        String v8_1;
        if ((p38 & 64) == 0) {
            v8_1 = p26;
        } else {
            v8_1 = "";
        }
        String v9_1;
        if ((p38 & 128) == 0) {
            v9_1 = p27;
        } else {
            v9_1 = "";
        }
        String v10_1;
        if ((p38 & 256) == 0) {
            v10_1 = p28;
        } else {
            v10_1 = "";
        }
        String v11_1;
        if ((p38 & 512) == 0) {
            v11_1 = p29;
        } else {
            v11_1 = "";
        }
        String v12_1;
        if ((p38 & 1024) == 0) {
            v12_1 = p30;
        } else {
            v12_1 = "";
        }
        String v13_1;
        if ((p38 & 2048) == 0) {
            v13_1 = p31;
        } else {
            v13_1 = "";
        }
        String v14_1;
        if ((p38 & 4096) == 0) {
            v14_1 = p32;
        } else {
            v14_1 = "";
        }
        String v15_1;
        if ((p38 & 8192) == 0) {
            v15_1 = p33;
        } else {
            v15_1 = "";
        }
        String v1_3;
        if ((p38 & 16384) == 0) {
            v1_3 = p34;
        } else {
            v1_3 = "";
        }
        String v16_2;
        if ((p38 & 32768) == 0) {
            v16_2 = p35;
        } else {
            v16_2 = "";
        }
        String v17_2;
        if ((p38 & 65536) == 0) {
            v17_2 = p36;
        } else {
            v17_2 = "";
        }
        String v38_1;
        if ((p38 & 131072) == 0) {
            v38_1 = p37;
        } else {
            v38_1 = "";
        }
        this(v1_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v1_3, v16_2, v17_2, v38_1);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.NfcInfo copy$default(vn.ai.trueid.sdk.models.NfcInfo p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, int p36, Object p37)
    {
        String v2_1;
        if ((p36 & 1) == 0) {
            v2_1 = p18;
        } else {
            v2_1 = p17.mrz;
        }
        String v3_1;
        if ((p36 & 2) == 0) {
            v3_1 = p19;
        } else {
            v3_1 = p17.cardNumber;
        }
        String v4_1;
        if ((p36 & 4) == 0) {
            v4_1 = p20;
        } else {
            v4_1 = p17.dateOfBirth;
        }
        String v5_1;
        if ((p36 & 8) == 0) {
            v5_1 = p21;
        } else {
            v5_1 = p17.issueDate;
        }
        String v6_1;
        if ((p36 & 16) == 0) {
            v6_1 = p22;
        } else {
            v6_1 = p17.previousNumber;
        }
        String v7_1;
        if ((p36 & 32) == 0) {
            v7_1 = p23;
        } else {
            v7_1 = p17.name;
        }
        String v8_1;
        if ((p36 & 64) == 0) {
            v8_1 = p24;
        } else {
            v8_1 = p17.sex;
        }
        String v9_1;
        if ((p36 & 128) == 0) {
            v9_1 = p25;
        } else {
            v9_1 = p17.nationality;
        }
        String v10_1;
        if ((p36 & 256) == 0) {
            v10_1 = p26;
        } else {
            v10_1 = p17.nation;
        }
        String v11_1;
        if ((p36 & 512) == 0) {
            v11_1 = p27;
        } else {
            v11_1 = p17.religion;
        }
        String v12_1;
        if ((p36 & 1024) == 0) {
            v12_1 = p28;
        } else {
            v12_1 = p17.hometown;
        }
        String v13_1;
        if ((p36 & 2048) == 0) {
            v13_1 = p29;
        } else {
            v13_1 = p17.address;
        }
        String v14_1;
        if ((p36 & 4096) == 0) {
            v14_1 = p30;
        } else {
            v14_1 = p17.character;
        }
        String v15_1;
        if ((p36 & 8192) == 0) {
            v15_1 = p31;
        } else {
            v15_1 = p17.expiredDate;
        }
        String v2_3;
        if ((p36 & 16384) == 0) {
            v2_3 = p32;
        } else {
            v2_3 = p17.fatherName;
        }
        String v1_1;
        if ((p36 & 32768) == 0) {
            v1_1 = p33;
        } else {
            v1_1 = p17.motherName;
        }
        String v1_2;
        if ((p36 & 65536) == 0) {
            v1_2 = p34;
        } else {
            v1_2 = p17.partnerName;
        }
        String v36_1;
        String v35_1;
        if ((p36 & 131072) == 0) {
            v36_1 = p35;
            v35_1 = v1_2;
        } else {
            v35_1 = v1_2;
            v36_1 = p17.faceImage;
        }
        return p17.copy(v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8_1, v9_1, v10_1, v11_1, v12_1, v13_1, v14_1, v15_1, v2_3, v1_1, v35_1, v36_1);
    }

    public final String component1()
    {
        return this.mrz;
    }

    public final String component10()
    {
        return this.religion;
    }

    public final String component11()
    {
        return this.hometown;
    }

    public final String component12()
    {
        return this.address;
    }

    public final String component13()
    {
        return this.character;
    }

    public final String component14()
    {
        return this.expiredDate;
    }

    public final String component15()
    {
        return this.fatherName;
    }

    public final String component16()
    {
        return this.motherName;
    }

    public final String component17()
    {
        return this.partnerName;
    }

    public final String component18()
    {
        return this.faceImage;
    }

    public final String component2()
    {
        return this.cardNumber;
    }

    public final String component3()
    {
        return this.dateOfBirth;
    }

    public final String component4()
    {
        return this.issueDate;
    }

    public final String component5()
    {
        return this.previousNumber;
    }

    public final String component6()
    {
        return this.name;
    }

    public final String component7()
    {
        return this.sex;
    }

    public final String component8()
    {
        return this.nationality;
    }

    public final String component9()
    {
        return this.nation;
    }

    public final vn.ai.trueid.sdk.models.NfcInfo copy(String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37)
    {
        return new vn.ai.trueid.sdk.models.NfcInfo(p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.NfcInfo)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.mrz, ((vn.ai.trueid.sdk.models.NfcInfo) p5).mrz)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.cardNumber, ((vn.ai.trueid.sdk.models.NfcInfo) p5).cardNumber)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.dateOfBirth, ((vn.ai.trueid.sdk.models.NfcInfo) p5).dateOfBirth)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.issueDate, ((vn.ai.trueid.sdk.models.NfcInfo) p5).issueDate)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.previousNumber, ((vn.ai.trueid.sdk.models.NfcInfo) p5).previousNumber)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.ai.trueid.sdk.models.NfcInfo) p5).name)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.sex, ((vn.ai.trueid.sdk.models.NfcInfo) p5).sex)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.nationality, ((vn.ai.trueid.sdk.models.NfcInfo) p5).nationality)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.nation, ((vn.ai.trueid.sdk.models.NfcInfo) p5).nation)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.religion, ((vn.ai.trueid.sdk.models.NfcInfo) p5).religion)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.hometown, ((vn.ai.trueid.sdk.models.NfcInfo) p5).hometown)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.address, ((vn.ai.trueid.sdk.models.NfcInfo) p5).address)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.character, ((vn.ai.trueid.sdk.models.NfcInfo) p5).character)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.expiredDate, ((vn.ai.trueid.sdk.models.NfcInfo) p5).expiredDate)) {
                                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.fatherName, ((vn.ai.trueid.sdk.models.NfcInfo) p5).fatherName)) {
                                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.motherName, ((vn.ai.trueid.sdk.models.NfcInfo) p5).motherName)) {
                                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.partnerName, ((vn.ai.trueid.sdk.models.NfcInfo) p5).partnerName)) {
                                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.faceImage, ((vn.ai.trueid.sdk.models.NfcInfo) p5).faceImage)) {
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

    public final String getCardNumber()
    {
        return this.cardNumber;
    }

    public final String getCharacter()
    {
        return this.character;
    }

    public final String getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public final String getExpiredDate()
    {
        return this.expiredDate;
    }

    public final String getFaceImage()
    {
        return this.faceImage;
    }

    public final String getFatherName()
    {
        return this.fatherName;
    }

    public final String getHometown()
    {
        return this.hometown;
    }

    public final String getIssueDate()
    {
        return this.issueDate;
    }

    public final String getMotherName()
    {
        return this.motherName;
    }

    public final String getMrz()
    {
        return this.mrz;
    }

    public final String getName()
    {
        return this.name;
    }

    public final String getNation()
    {
        return this.nation;
    }

    public final String getNationality()
    {
        return this.nationality;
    }

    public final String getPartnerName()
    {
        return this.partnerName;
    }

    public final String getPreviousNumber()
    {
        return this.previousNumber;
    }

    public final String getReligion()
    {
        return this.religion;
    }

    public final String getSex()
    {
        return this.sex;
    }

    public int hashCode()
    {
        int v1_2;
        int v0_17 = e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.mrz.hashCode() * 31), 31, this.cardNumber), 31, this.dateOfBirth), 31, this.issueDate), 31, this.previousNumber), 31, this.name), 31, this.sex), 31, this.nationality), 31, this.nation), 31, this.religion), 31, this.hometown), 31, this.address), 31, this.character), 31, this.expiredDate), 31, this.fatherName), 31, this.motherName), 31, this.partnerName);
        int v1_1 = this.faceImage;
        if (v1_1 != 0) {
            v1_2 = v1_1.hashCode();
        } else {
            v1_2 = 0;
        }
        return (v0_17 + v1_2);
    }

    public final java.util.Map toMap()
    {
        kotlin.Pair v3 = kotlin.TuplesKt.to("cardNumber", this.cardNumber);
        kotlin.TuplesKt.to("dateOfBirth", this.dateOfBirth);
        kotlin.TuplesKt.to("issueDate", this.issueDate);
        kotlin.TuplesKt.to("previousNumber", this.previousNumber);
        kotlin.TuplesKt.to("name", this.name);
        kotlin.TuplesKt.to("sex", this.sex);
        kotlin.TuplesKt.to("nationality", this.nationality);
        kotlin.TuplesKt.to("nation", this.nation);
        kotlin.TuplesKt.to("religion", this.religion);
        kotlin.TuplesKt.to("hometown", this.hometown);
        kotlin.TuplesKt.to("address", this.address);
        kotlin.TuplesKt.to("character", this.character);
        kotlin.TuplesKt.to("expiredDate", this.expiredDate);
        kotlin.TuplesKt.to("fatherName", this.fatherName);
        kotlin.TuplesKt.to("motherName", this.motherName);
        kotlin.TuplesKt.to("partnerName", this.partnerName);
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v3, kotlin.TuplesKt.to("faceImage", this.faceImage)}));
    }

    public String toString()
    {
        String v3_0 = this.dateOfBirth;
        String v4_0 = this.issueDate;
        String v5 = this.previousNumber;
        String v6 = this.name;
        String v7 = this.sex;
        String v8 = this.nationality;
        String v9 = this.nation;
        String v10 = this.religion;
        String v11 = this.hometown;
        String v12 = this.address;
        String v14 = this.expiredDate;
        String v16 = this.fatherName;
        String v17 = this.motherName;
        String v18 = this.partnerName;
        String v19 = this.faceImage;
        String v20 = this.character;
        String v0_2 = androidx.camera.camera2.internal.G.i("NfcInfo(mrz=", this.mrz, ", cardNumber=", this.cardNumber, ", dateOfBirth=");
        q9.H.p(v0_2, v3_0, ", issueDate=", v4_0, ", previousNumber=");
        q9.H.p(v0_2, v5, ", name=", v6, ", sex=");
        q9.H.p(v0_2, v7, ", nationality=", v8, ", nation=");
        q9.H.p(v0_2, v9, ", religion=", v10, ", hometown=");
        q9.H.p(v0_2, v11, ", address=", v12, ", character=");
        q9.H.p(v0_2, v20, ", expiredDate=", v14, ", fatherName=");
        q9.H.p(v0_2, v16, ", motherName=", v17, ", partnerName=");
        return androidx.compose.foundation.layout.G0.n(v0_2, v18, ", faceImage=", v19, ")");
    }
}

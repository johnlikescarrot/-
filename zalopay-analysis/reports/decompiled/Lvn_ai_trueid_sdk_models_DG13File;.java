package vn.ai.trueid.sdk.models;
public final class DG13File {
    private static final String CCCD_NUMBER_TAG = "0113";
    private static final String CMND_NUMBER_TAG = "0F13";
    public static final vn.ai.trueid.sdk.models.DG13File$Companion Companion = None;
    private static final String DATE_EXPRIRATION_TAG = "02010C0C";
    private static final String DATE_OF_BIRTH_TAG = "300F02010313";
    private static final String DATE_RANGE_TAG = "0B13";
    private static final String DISTRICT_TAG = "080C";
    private static final int FATHER_NAME_TAG_LEN = 16;
    private static final int HUSBAND_WIFE_NAME_TAG_LEN = 16;
    private static final String IDENTIFY_CHARACTER_TAG = "0A0C";
    private static final int MOTHER_NAME_TAG_LEN = 6;
    private static final String NAME_PERSONAL_TAG = "020C";
    private static final String NATIONALITY_TAG = "050C";
    private static final String NATION_TAG = "060C";
    private static final String PERMANENT_ADDRESS_TAG = "";
    private static final String RELATIVE_END_TAG = "30323934";
    private static final String RELATIVE_START_TAG = "02010C0C";
    private static final String RELIGION_TAG = "070C";
    private static final String SEX_TAG = "040C";
    private String allHex;
    private int countFatherName;
    private int countMotherName;
    private String fatherNameHex;
    private String motherNameHex;

    static DG13File()
    {
        wmoi.igob.jtgo(vn.ai.trueid.sdk.models.DG13File, 19496, 19496);
        vn.ai.trueid.sdk.models.DG13File.Companion = new vn.ai.trueid.sdk.models.DG13File$Companion(0);
        return;
    }

    public DG13File()
    {
        this.allHex = "";
        this.fatherNameHex = "";
        this.motherNameHex = "";
        return;
    }

    public final String getAllHex()
    {
        return this.allHex;
    }

    public final vn.ai.trueid.sdk.models.CitizenInfo readContent()
    {
        vn.ai.trueid.sdk.models.DG13File v0 = this;
        vn.ai.trueid.sdk.models.CitizenInfo v3_0 = new vn.ai.trueid.sdk.models.CitizenInfo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524287, 0);
        v3_0.setCitizenPid(this.readObject(this.allHex, "0113"));
        v3_0.setFullName(this.readObject(this.allHex, "020C"));
        v3_0.setBirthDate(this.readObject(this.allHex, "300F02010313"));
        v3_0.setGender(this.readObject(this.allHex, "040C"));
        v3_0.setNationality(this.readObject(this.allHex, "050C"));
        v3_0.setEthnic(this.readObject(this.allHex, "060C"));
        v3_0.setReligion(this.readObject(this.allHex, "070C"));
        v3_0.setHomeTown(this.readObject(this.allHex, "080C"));
        v3_0.setRegPlaceAddress(this.readObject(this.allHex, "090C"));
        v3_0.setIdentifyCharacteristics(this.readObject(this.allHex, "0A0C"));
        v3_0.setDateProvide(this.readObject(this.allHex, "0B13"));
        v3_0.setOutOfDate(this.readObject(this.allHex, "02010C0C"));
        String v1_8 = v0.readRelative(v0.allHex, "02010C0C", "30323934");
    }

    public final String readFatherName(String p7, int p8, String p9)
    {
        if ((p7.length() != 0) && ((p9.length() != 0) && ((p8 > 0) && (kotlin.text.StringsKt.g(p7, p9))))) {
            int v0_1 = m9.H2.a(p7, p9);
            if (v0_1 != 0) {
                int v0_3 = (Integer.parseInt(v0_1, kotlin.text.CharsKt.checkRadix(16)) * 2);
                int v4 = kotlin.text.StringsKt.l(p7, p9, 0, 0, 6);
                int v5 = -1;
                if (v4 != -1) {
                    v5 = ((p9.length() + v4) + 2);
                }
                int v9_7 = ((kotlin.text.StringsKt.l(p7, p7.substring(v5, (v5 + v0_3)), 0, 0, 6) + v0_3) + p8);
                int v8_1 = (v9_7 + 2);
                int v9_9 = Integer.parseInt(p7.substring(v9_7, v8_1), kotlin.text.CharsKt.checkRadix(16));
                this.countFatherName = v9_9;
                String v7_1 = p7.substring(v8_1, ((v9_9 * 2) + v8_1));
                this.fatherNameHex = v7_1;
                return m9.H2.c(v7_1);
            }
        }
        return 0;
    }

    public final String readHusbandAndWifeName(String p4, int p5, String p6)
    {
        if (p6.length() <= 2) {
            p6 = this.fatherNameHex;
            p5 = 54;
        }
        int v5_1 = A1.a.b(this.countMotherName, 2, kotlin.text.StringsKt.l(p4, p6, 0, 0, 6), p5);
        int v6_1 = (v5_1 + 2);
        return m9.H2.c(p4.substring(v6_1, ((Integer.parseInt(p4.substring(v5_1, v6_1), kotlin.text.CharsKt.checkRadix(16)) * 2) + v6_1)));
    }

    public final String readMotherName(String p3, int p4, String p5)
    {
        char[] v4_6 = A1.a.b(this.countFatherName, 2, kotlin.text.StringsKt.l(p3, p5, 0, 0, 6), p4);
        int v5_2 = (v4_6 + 2);
        char[] v4_2 = Integer.parseInt(p3.substring(v4_6, v5_2), kotlin.text.CharsKt.checkRadix(16));
        this.countMotherName = v4_2;
        String v3_1 = p3.substring(v5_2, ((v4_2 * 2) + v5_2));
        this.motherNameHex = v3_1;
        if (v3_1.length() > 2) {
            return m9.H2.c(v3_1);
        } else {
            return 0;
        }
    }

    public final String readObject(String p3, String p4)
    {
        if ((p3.length() != 0) && ((p4.length() != 0) && (kotlin.text.StringsKt.g(p3, p4)))) {
            int v0_2 = kotlin.text.StringsKt.l(p3, p4, 0, 0, 6);
            int v1_1 = -1;
            if (v0_2 != -1) {
                v1_1 = ((p4.length() + v0_2) + 2);
            }
            int v4_1 = m9.H2.a(p3, p4);
            if (v4_1 != 0) {
                return m9.H2.c(p3.substring(v1_1, ((Integer.parseInt(v4_1, kotlin.text.CharsKt.checkRadix(16)) * 2) + v1_1)));
            }
        }
        return 0;
    }

    public final String readRelative(String p3, String p4, String p5)
    {
        if ((p3.length() != 0) && ((p4.length() != 0) && ((p5.length() != 0) && ((kotlin.text.StringsKt.g(p3, p4)) && (kotlin.text.StringsKt.g(p3, p5)))))) {
            String v3_2 = kotlin.text.StringsKt.y(p3.substring(kotlin.text.StringsKt.l(p3, p4, 0, 0, 6), kotlin.text.StringsKt.l(p3, p5, 0, 0, 6)), new String[] {"0201"}), 0, 6);
            Object v5_4 = v3_2.get(2);
            String v3_3 = v3_2.get(3);
            StringBuilder v0_6 = new StringBuilder();
            v0_6.append(v5_4);
            v0_6.append("0201");
            v0_6.append(v3_3);
            return v0_6.toString();
        } else {
            return 0;
        }
    }

    public final String replaceSpecialUTF8(String p7)
    {
        StringBuilder v0_1 = new StringBuilder();
        int v1 = p7.length();
        int v2 = 0;
        while (v2 < v1) {
            char v3 = p7.charAt(v2);
            if (kotlin.text.StringsKt.g("0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM\u0103\u00e2\u0111\u00ea\u00f4\u01a1\u01b0\u00e0\u1ea3\u00e3\u1ea1\u00e1\u1eb1\u1eb3\u1eb5\u1eb7\u1eaf\u1ea7\u1ea9\u1eab\u1ead\u1ea5\u00e8\u1ebb\u1ebd\u1eb9\u00e9\u1ec1\u1ec3\u1ec5\u1ec7\u1ebf\u00ec\u1ec9\u0129\u1ecb\u00ed\u00f2\u1ecf\u00f5\u1ecd\u00f3\u1ed3\u1ed5\u1ed7\u1ed9\u1ed1\u1edd\u1edf\u1ee1\u1ee3\u1edb\u00f9\u1ee7\u0169\u1ee5\u00fa\u1eeb\u1eed\u1eef\u1ef1\u1ee9\u1ef3\u1ef7\u1ef9\u1ef5\u00fd\u0102\u00c2\u0110\u00ca\u00d4\u01a0\u01af\u00c0\u1ea2\u00c3\u1ea0\u00c1\u1eb0\u1eb2\u1eb4\u1eb6\u1eae\u1ea6\u1ea8\u1eaa\u1eac\u1ea4\u00c8\u1eba\u1ebc\u1eb8\u00c9\u1ec0\u1ec2\u1ec4\u1ec6\u1ebe\u00cc\u1ec8\u0128\u1eca\u00cd\u00d2\u1ece\u00d5\u1ecc\u00d3\u1ed2\u1ed4\u1ed6\u1ed8\u1ed0\u1edc\u1ede\u1ee0\u1ee2\u1eda\u00d9\u1ee6\u0168\u1ee4\u00da\u1eea\u1eec\u1eee\u1ef0\u1ee8\u1ef2\u1ef6\u1ef8\u1ef4\u00dd\'/,.\":;() ", String.valueOf(v3))) {
                v0_1.append(v3);
            }
            v2++;
        }
        return v0_1.toString();
    }

    public final void setAllHex(String p1)
    {
        this.allHex = p1;
        return;
    }
}

package vn.ai.trueid.sdk.models.sotp;
public final class TransactionDisplayModel$Companion {

    private TransactionDisplayModel$Companion()
    {
        return;
    }

    public synthetic TransactionDisplayModel$Companion(kotlin.jvm.internal.DefaultConstructorMarker p1)
    {
        return;
    }

    private final String getLocalizedString(String p3, String p4)
    {
        try {
            String v0_0 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        } catch (Exception) {
            return p4;
        }
        if (!v0_0.isActivityInitialised()) {
            return p4;
        } else {
            String v0_1 = v0_0.getString(p3);
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_1, p3)) {
                return v0_1;
            } else {
                return p4;
            }
        }
    }

    public final vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel fromJson(String p29)
    {
        vn.ai.trueid.sdk.models.sotp.FieldStyle v4_1;
        vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel v2_11 = new org.json.JSONObject(p29);
        if (!v2_11.has("transactionType")) {
            if (!v2_11.has("transaction_type")) {
                v4_1 = 0;
            } else {
                v4_1 = v2_11.optString("transaction_type");
            }
        } else {
            v4_1 = v2_11.optString("transactionType");
        }
        vn.ai.trueid.sdk.models.sotp.FieldStyle v4_2 = vn.ai.trueid.sdk.models.sotp.TransactionType.Companion.fromString(v4_1);
        int v7_1 = new java.util.ArrayList();
        if ((v2_11.has("fields")) && (!v2_11.isNull("fields"))) {
            vn.ai.trueid.sdk.models.sotp.FieldStyle v9_3 = v2_11.optJSONArray("fields");
            if (v9_3 != null) {
                String v12_0 = v9_3.length();
                int v13_0 = 0;
                while (v13_0 < v12_0) {
                    String v18_1;
                    org.json.JSONObject v14_0 = v9_3.optJSONObject(v13_0);
                    if (v14_0 != null) {
                        String v15_1 = v14_0.optString("label", "");
                        int v5_10 = v14_0.optString("value", "");
                        v18_1 = v9_3;
                        try {
                            vn.ai.trueid.sdk.models.sotp.FieldStyle v9_7 = vn.ai.trueid.sdk.models.sotp.FieldStyle.valueOf(v14_0.optString("style", "NORMAL").toUpperCase(java.util.Locale.ROOT));
                        } catch (Exception) {
                            v9_7 = vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL;
                        }
                        v7_1.add(new vn.ai.trueid.sdk.models.sotp.DisplayField(v15_1, v5_10, v9_7));
                    } else {
                        v18_1 = v9_3;
                    }
                    v13_0++;
                    v9_3 = v18_1;
                }
            }
        }
        int v5_13;
        if (!v2_11.has("beneficiary_account")) {
            if (!v2_11.has("beneficiaryAccount")) {
                v5_13 = "";
            } else {
                v5_13 = v2_11.optString("beneficiaryAccount", "");
            }
        } else {
            v5_13 = v2_11.optString("beneficiary_account", "");
        }
        long v19_1 = v4_2;
        String v20_1 = "fields";
        if ((!v7_1.isEmpty()) || (v5_13.length() <= 0)) {
            if (v7_1.isEmpty()) {
                int v5_0 = v2_11.keys();
                while (v5_0.hasNext()) {
                    int v16_2;
                    String v1_3;
                    String v18_4 = v5_0;
                    int v5_16 = ((String) v5_0.next());
                    if (v5_16 == 0) {
                        v16_2 = v7_1;
                        v1_3 = v20_1;
                        int v7_2 = v2_11.optString(v5_16, "");
                        if (v7_2.length() > 0) {
                            String v1_7;
                            v20_1 = v1_3;
                            String v1_5 = kotlin.text.y.replace$default(v5_16.toLowerCase(java.util.Locale.ROOT), "_", "", 0, 4, 0);
                            String v27 = v2_11;
                            switch (v1_5.hashCode()) {
                                case -1724546052:
                                    if (v1_5.equals("description")) {
                                        v1_7 = this.getLocalizedString("trueidsdk_sotp_content", "N\u1ed9i dung");
                                    } else {
                                        v1_7 = kotlin.text.y.replace$default(v5_16, "_", " ", 0, 4, 0);
                                        if (v1_7.length() <= 0) {
                                        } else {
                                            String v17_1;
                                            vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel v2_7 = new StringBuilder();
                                            String v17_0 = v1_7.charAt(0);
                                            if (!Character.isLowerCase(v17_0)) {
                                                v17_1 = String.valueOf(v17_0);
                                            } else {
                                                v17_1 = kotlin.text.a.b(v17_0);
                                            }
                                            v2_7.append(v17_1);
                                            v2_7.append(v1_7.substring(1));
                                            v1_7 = v2_7.toString();
                                        }
                                    }
                                    break;
                                case -1413853096:
                                    if (v1_5.equals("amount")) {
                                        v1_7 = this.getLocalizedString("trueidsdk_sotp_amount", "S\u1ed1 ti\u1ec1n");
                                    }
                                    break;
                                case -1169770584:
                                    if (v1_5.equals("beneficiaryaccount")) {
                                        v1_7 = this.getLocalizedString("trueidsdk_sotp_account", "S\u1ed1 t\u00e0i kho\u1ea3n");
                                    }
                                    break;
                                case -329504058:
                                    if (v1_5.equals("beneficiarybankbin")) {
                                        v1_7 = this.getLocalizedString("trueidsdk_sotp_bank", "Ng\u00e2n h\u00e0ng");
                                    }
                                    break;
                                case 1402255088:
                                    if (v1_5.equals("beneficiaryname")) {
                                        v1_7 = this.getLocalizedString("trueidsdk_sotp_beneficiary", "Ng\u01b0\u1eddi nh\u1eadn");
                                    }
                                    break;
                                default:
                            }
                            String v1_15 = v16_2;
                            v1_15.add(new vn.ai.trueid.sdk.models.sotp.DisplayField(v1_7, v7_2, vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL));
                            v7_1 = v1_15;
                            v5_0 = v18_4;
                            v2_11 = v27;
                        }
                    } else {
                        String v1_17 = v5_16.hashCode();
                        v16_2 = v7_1;
                        if (v1_17 == -1274708295) {
                            v1_3 = v20_1;
                            if (!v5_16.equals(v1_3)) {
                            }
                        } else {
                            if (v1_17 == 509054971) {
                                if (!v5_16.equals("transaction_type")) {
                                }
                            } else {
                                if ((v1_17 != 1263023928) || (!v5_16.equals("transactionType"))) {
                                }
                            }
                            v7_1 = v16_2;
                            v5_0 = v18_4;
                        }
                    }
                    v20_1 = v1_3;
                    v7_1 = v16_2;
                    v5_0 = v18_4;
                }
            }
            return new vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel(v19_1, v7_1, p29);
        } else {
            vn.ai.trueid.sdk.models.sotp.TransactionType v3_2;
            if (!v2_11.has("beneficiary_name")) {
                if (!v2_11.has("beneficiaryName")) {
                    v3_2 = "";
                } else {
                    v3_2 = v2_11.optString("beneficiaryName", "");
                }
            } else {
                v3_2 = v2_11.optString("beneficiary_name", "");
            }
            String v6_4;
            if (!v2_11.has("beneficiary_bank_bin")) {
                if (!v2_11.has("beneficiaryBankBin")) {
                    v6_4 = "";
                } else {
                    v6_4 = v2_11.optString("beneficiaryBankBin", "");
                }
            } else {
                v6_4 = v2_11.optString("beneficiary_bank_bin", "");
            }
            String v18_0;
            int v5_5;
            String v17_6;
            vn.ai.trueid.sdk.models.sotp.FieldStyle v4_3 = v2_11.optString("description", "");
            if ((!v2_11.has("amount")) || (v2_11.isNull("amount"))) {
                v17_6 = v4_3;
                v18_0 = v5_13;
                v5_5 = 0;
            } else {
                v17_6 = v4_3;
                v18_0 = v5_13;
                long v19_0 = v2_11.optLong("amount", -1);
                if (v19_0 == -1) {
                    v5_5 = kotlin.text.StringsKt.toLongOrNull(v2_11.optString("amount", ""));
                } else {
                    v5_5 = Long.valueOf(v19_0);
                }
            }
            vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel v2_12 = v2_11.optString("currency", "VND");
            if (v3_2.length() > 0) {
                v7_1.add(new vn.ai.trueid.sdk.models.sotp.DisplayField(this.getLocalizedString("trueidsdk_sotp_beneficiary", "Ng\u01b0\u1eddi nh\u1eadn"), v3_2, vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL));
            }
            if (v18_0.length() > 0) {
                v7_1.add(new vn.ai.trueid.sdk.models.sotp.DisplayField(this.getLocalizedString("trueidsdk_sotp_account", "S\u1ed1 t\u00e0i kho\u1ea3n"), v18_0, vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL));
            }
            if (v6_4.length() > 0) {
                v7_1.add(new vn.ai.trueid.sdk.models.sotp.DisplayField(this.getLocalizedString("trueidsdk_sotp_bank", "Ng\u00e2n h\u00e0ng"), v6_4, vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL));
            }
            if (v5_5 != 0) {
                try {
                    vn.ai.trueid.sdk.models.sotp.TransactionType v3_11 = new java.text.DecimalFormat("#,###", new java.text.DecimalFormatSymbols(java.util.Locale.US)).format(v5_5.longValue());
                    vn.ai.trueid.sdk.models.sotp.FieldStyle v4_17 = new StringBuilder();
                    v4_17.append(v3_11);
                    v4_17.append(" ");
                    v4_17.append(v2_12);
                    vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel v2_13 = v4_17.toString();
                } catch (Exception) {
                    vn.ai.trueid.sdk.models.sotp.TransactionType v3_14 = new StringBuilder();
                    v3_14.append(v5_5);
                    v3_14.append(" ");
                    v3_14.append(v2_13);
                    v2_13 = v3_14.toString();
                }
                v7_1.add(new vn.ai.trueid.sdk.models.sotp.DisplayField(this.getLocalizedString("trueidsdk_sotp_amount", "S\u1ed1 ti\u1ec1n"), v2_13, vn.ai.trueid.sdk.models.sotp.FieldStyle.HIGHLIGHT));
            }
            if (v17_6.length() > 0) {
                v7_1.add(new vn.ai.trueid.sdk.models.sotp.DisplayField(this.getLocalizedString("trueidsdk_sotp_content", "N\u1ed9i dung"), v17_6, vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL));
            }
            return new vn.ai.trueid.sdk.models.sotp.TransactionDisplayModel(vn.ai.trueid.sdk.models.sotp.TransactionType.TRANSFER, v7_1, p29);
        }
    }
}

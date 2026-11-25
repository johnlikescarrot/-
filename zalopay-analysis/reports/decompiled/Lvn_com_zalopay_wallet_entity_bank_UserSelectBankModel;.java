package vn.com.zalopay.wallet.entity.bank;
public class UserSelectBankModel implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    public static final vn.com.zalopay.wallet.entity.bank.UserSelectBankModel EMPTY;
    private boolean isBankMigrationFlow;
    private boolean isMapAtCounter;
    public String mBankCode;
    private yt.g mBankMigrationData;
    private String mBankName;
    public int mBankType;
    public String mClientBankCode;
    private boolean mEnableBankRevamp;
    public boolean mEnableLinkDebitDirect;
    public boolean mEnableNapasMerchantHosted;
    private boolean mEnableTabLayout;
    public boolean mIsBankNapas;
    public boolean mIsLinkViaNapas;
    public String mKyc;
    public int mPaymentBankFlow;

    static UserSelectBankModel()
    {
        vn.com.zalopay.wallet.entity.bank.UserSelectBankModel.EMPTY = new vn.com.zalopay.wallet.entity.bank.UserSelectBankModel();
        vn.com.zalopay.wallet.entity.bank.UserSelectBankModel.CREATOR = new y9.z(10);
        return;
    }

    public UserSelectBankModel()
    {
        this.isBankMigrationFlow = 0;
        this.mBankName = "";
        this.mEnableNapasMerchantHosted = 0;
        this.mEnableLinkDebitDirect = 0;
        this.mEnableTabLayout = 0;
        this.mEnableBankRevamp = 0;
        this.mBankCode = "";
        return;
    }

    public UserSelectBankModel(android.os.Parcel p4)
    {
        int v1_5;
        int v0 = 0;
        this.isBankMigrationFlow = 0;
        this.mBankName = "";
        this.mEnableNapasMerchantHosted = 0;
        this.mEnableLinkDebitDirect = 0;
        this.mEnableTabLayout = 0;
        this.mEnableBankRevamp = 0;
        this.mBankCode = p4.readString();
        this.mPaymentBankFlow = p4.readInt();
        this.mBankType = p4.readInt();
        this.mKyc = p4.readString();
        if (p4.readByte() == 0) {
            v1_5 = 0;
        } else {
            v1_5 = 1;
        }
        int v1_8;
        this.mIsBankNapas = v1_5;
        if (p4.readByte() == 0) {
            v1_8 = 0;
        } else {
            v1_8 = 1;
        }
        int v1_11;
        this.mIsLinkViaNapas = v1_8;
        this.mClientBankCode = p4.readString();
        if (p4.readByte() == 0) {
            v1_11 = 0;
        } else {
            v1_11 = 1;
        }
        int v1_17;
        this.isMapAtCounter = v1_11;
        this.mBankMigrationData = ((yt.g) p4.readParcelable(yt.g.getClassLoader()));
        if (p4.readByte() == 0) {
            v1_17 = 0;
        } else {
            v1_17 = 1;
        }
        int v1_20;
        this.isBankMigrationFlow = v1_17;
        this.mBankName = p4.readString();
        if (p4.readByte() == 0) {
            v1_20 = 0;
        } else {
            v1_20 = 1;
        }
        int v1_22;
        this.mEnableNapasMerchantHosted = v1_20;
        if (p4.readByte() == 0) {
            v1_22 = 0;
        } else {
            v1_22 = 1;
        }
        int v1_24;
        this.mEnableLinkDebitDirect = v1_22;
        if (p4.readByte() == 0) {
            v1_24 = 0;
        } else {
            v1_24 = 1;
        }
        this.mEnableTabLayout = v1_24;
        if (p4.readByte() != 0) {
            v0 = 1;
        }
        this.mEnableBankRevamp = v0;
        return;
    }

    public UserSelectBankModel(String p3, int p4)
    {
        this.isBankMigrationFlow = 0;
        this.mBankName = "";
        this.mEnableNapasMerchantHosted = 0;
        this.mEnableLinkDebitDirect = 0;
        this.mEnableTabLayout = 0;
        this.mEnableBankRevamp = 0;
        this.mBankCode = p3;
        this.mBankType = p4;
        this.mIsBankNapas = 0;
        return;
    }

    public UserSelectBankModel(String p3, int p4, int p5, boolean p6, boolean p7)
    {
        this.isBankMigrationFlow = 0;
        this.mBankName = "";
        this.mEnableNapasMerchantHosted = 0;
        this.mEnableLinkDebitDirect = 0;
        this.mEnableTabLayout = 0;
        this.mEnableBankRevamp = 0;
        this.mBankCode = p3;
        this.mBankType = p4;
        this.mPaymentBankFlow = p5;
        this.mIsLinkViaNapas = p7;
        this.mIsBankNapas = p6;
        return;
    }

    public UserSelectBankModel(String p3, int p4, boolean p5)
    {
        this.isBankMigrationFlow = 0;
        this.mBankName = "";
        this.mEnableNapasMerchantHosted = 0;
        this.mEnableLinkDebitDirect = 0;
        this.mEnableTabLayout = 0;
        this.mEnableBankRevamp = 0;
        this.mBankCode = p3;
        this.mBankType = p4;
        this.mIsBankNapas = p5;
        return;
    }

    public UserSelectBankModel(String p3, String p4, int p5, boolean p6, boolean p7, String p8)
    {
        this.isBankMigrationFlow = 0;
        this.mBankName = "";
        this.mEnableNapasMerchantHosted = 0;
        this.mEnableLinkDebitDirect = 0;
        this.mEnableTabLayout = 0;
        this.mEnableBankRevamp = 0;
        this.mBankCode = p3;
        this.mBankType = p5;
        this.mIsBankNapas = p6;
        if (p4 == null) {
            p4 = "";
        }
        this.mClientBankCode = p4;
        this.isMapAtCounter = p7;
        this.mBankName = p8;
        return;
    }

    public UserSelectBankModel(vn.com.zalopay.wallet.entity.bank.UserSelectBankModel p3)
    {
        this.isBankMigrationFlow = 0;
        this.mBankName = "";
        this.mEnableNapasMerchantHosted = 0;
        this.mEnableLinkDebitDirect = 0;
        this.mEnableTabLayout = 0;
        this.mEnableBankRevamp = 0;
        this.mBankCode = p3.mBankCode;
        this.mPaymentBankFlow = p3.mPaymentBankFlow;
        this.mBankType = p3.mBankType;
        this.mKyc = p3.mKyc;
        this.mIsBankNapas = p3.mIsBankNapas;
        this.mIsLinkViaNapas = p3.mIsLinkViaNapas;
        this.mClientBankCode = p3.mClientBankCode;
        this.isMapAtCounter = p3.isMapAtCounter;
        this.isBankMigrationFlow = p3.isBankMigrationFlow;
        this.mBankName = p3.mBankName;
        this.mBankMigrationData = p3.mBankMigrationData;
        this.mEnableNapasMerchantHosted = p3.mEnableNapasMerchantHosted;
        this.mEnableLinkDebitDirect = p3.mEnableLinkDebitDirect;
        this.mEnableTabLayout = p3.mEnableTabLayout;
        this.mEnableBankRevamp = p3.mEnableBankRevamp;
        return;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean enableBankRevamp()
    {
        return this.mEnableBankRevamp;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5) && (this.getClass() == p5.getClass())) {
                if (this.mPaymentBankFlow == ((vn.com.zalopay.wallet.entity.bank.UserSelectBankModel) p5).mPaymentBankFlow) {
                    if (this.mBankType == ((vn.com.zalopay.wallet.entity.bank.UserSelectBankModel) p5).mBankType) {
                        String v2_2 = this.mBankCode;
                        if (v2_2 == null) {
                            if (((vn.com.zalopay.wallet.entity.bank.UserSelectBankModel) p5).mBankCode != null) {
                                return 0;
                            }
                        } else {
                            if (!v2_2.equals(((vn.com.zalopay.wallet.entity.bank.UserSelectBankModel) p5).mBankCode)) {
                                return 0;
                            }
                        }
                        String v2_5 = this.mKyc;
                        boolean v5_1 = ((vn.com.zalopay.wallet.entity.bank.UserSelectBankModel) p5).mKyc;
                        if (v2_5 == null) {
                            if (!v5_1) {
                                return 1;
                            }
                        } else {
                            return v2_5.equals(v5_1);
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
            return 0;
        } else {
            return 1;
        }
    }

    public yt.g getBankMigrationData()
    {
        return this.mBankMigrationData;
    }

    public String getBankName()
    {
        if (!android.text.TextUtils.isEmpty(this.mBankName)) {
            return this.mBankName;
        } else {
            return "";
        }
    }

    public boolean getEnableTabLayout()
    {
        return this.mEnableTabLayout;
    }

    public int hashCode()
    {
        int v0_4;
        int v0_0 = this.mBankCode;
        int v1 = 0;
        if (v0_0 == 0) {
            v0_4 = 0;
        } else {
            v0_4 = v0_0.hashCode();
        }
        int v0_2 = (((((v0_4 * 31) + this.mPaymentBankFlow) * 31) + this.mBankType) * 31);
        String v2_1 = this.mKyc;
        if (v2_1 != null) {
            v1 = v2_1.hashCode();
        }
        return (v0_2 + v1);
    }

    public boolean isEmpty()
    {
        return android.text.TextUtils.isEmpty(this.mBankCode);
    }

    public boolean isEnableBankMigration()
    {
        return this.isBankMigrationFlow;
    }

    public boolean isKycDataReady()
    {
        return (android.text.TextUtils.isEmpty(this.mKyc) ^ 1);
    }

    public boolean isMapAtCounter()
    {
        return this.isMapAtCounter;
    }

    public void setBankMigrationData(yt.g p1)
    {
        this.mBankMigrationData = p1;
        return;
    }

    public void setEnableBankMigration(boolean p1)
    {
        this.isBankMigrationFlow = p1;
        return;
    }

    public void setEnableBankRevamp(boolean p1)
    {
        this.mEnableBankRevamp = p1;
        return;
    }

    public void setEnableTabLayout(boolean p1)
    {
        this.mEnableTabLayout = p1;
        return;
    }

    public void setMapAtCounter(boolean p1)
    {
        this.isMapAtCounter = p1;
        return;
    }

    public void writeToParcel(android.os.Parcel p2, int p3)
    {
        p2.writeString(this.mBankCode);
        p2.writeInt(this.mPaymentBankFlow);
        p2.writeInt(this.mBankType);
        p2.writeString(this.mKyc);
        p2.writeByte(((byte) this.mIsBankNapas));
        p2.writeByte(((byte) this.mIsLinkViaNapas));
        p2.writeString(this.mClientBankCode);
        p2.writeByte(((byte) this.isMapAtCounter));
        p2.writeParcelable(this.mBankMigrationData, p3);
        p2.writeByte(((byte) this.isBankMigrationFlow));
        p2.writeString(this.mBankName);
        p2.writeByte(((byte) this.mEnableNapasMerchantHosted));
        p2.writeByte(((byte) this.mEnableLinkDebitDirect));
        p2.writeByte(((byte) this.mEnableTabLayout));
        p2.writeByte(((byte) this.mEnableBankRevamp));
        return;
    }
}

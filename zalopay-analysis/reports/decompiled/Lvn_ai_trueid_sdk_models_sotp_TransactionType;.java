package vn.ai.trueid.sdk.models.sotp;
public final enum class TransactionType extends java.lang.Enum implements java.io.Serializable {
    private static final synthetic vn.ai.trueid.sdk.models.sotp.TransactionType[] $VALUES;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType ACCOUNT_SETTINGS;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType BILL_PAYMENT;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType CARD_MANAGEMENT;
    public static final vn.ai.trueid.sdk.models.sotp.TransactionType$Companion Companion;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType DEVICE_MANAGEMENT;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType GENERIC;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType INVESTMENT;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType LOAN;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType LOGIN_APPROVAL;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType QR_PAYMENT;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType SAVINGS;
    public static final enum vn.ai.trueid.sdk.models.sotp.TransactionType TRANSFER;
    private final String displayTitle;
    private final String headerColorHex;

    private static final synthetic vn.ai.trueid.sdk.models.sotp.TransactionType[] $values()
    {
        vn.ai.trueid.sdk.models.sotp.TransactionType v2 = vn.ai.trueid.sdk.models.sotp.TransactionType.QR_PAYMENT;
        vn.ai.trueid.sdk.models.sotp.TransactionType v4 = vn.ai.trueid.sdk.models.sotp.TransactionType.SAVINGS;
        vn.ai.trueid.sdk.models.sotp.TransactionType v6 = vn.ai.trueid.sdk.models.sotp.TransactionType.LOAN;
        vn.ai.trueid.sdk.models.sotp.TransactionType v8 = vn.ai.trueid.sdk.models.sotp.TransactionType.DEVICE_MANAGEMENT;
        return new vn.ai.trueid.sdk.models.sotp.TransactionType[] {vn.ai.trueid.sdk.models.sotp.TransactionType.TRANSFER, vn.ai.trueid.sdk.models.sotp.TransactionType.GENERIC});
    }

    static TransactionType()
    {
        vn.ai.trueid.sdk.models.sotp.TransactionType.TRANSFER = new vn.ai.trueid.sdk.models.sotp.TransactionType("TRANSFER", 0, "Chuy\u1ec3n ti\u1ec1n", "#00C853");
        vn.ai.trueid.sdk.models.sotp.TransactionType.BILL_PAYMENT = new vn.ai.trueid.sdk.models.sotp.TransactionType("BILL_PAYMENT", 1, "Thanh to\u00e1n h\u00f3a \u0111\u01a1n", "#00C853");
        vn.ai.trueid.sdk.models.sotp.TransactionType.QR_PAYMENT = new vn.ai.trueid.sdk.models.sotp.TransactionType("QR_PAYMENT", 2, "Thanh to\u00e1n QR", "#00C853");
        vn.ai.trueid.sdk.models.sotp.TransactionType.CARD_MANAGEMENT = new vn.ai.trueid.sdk.models.sotp.TransactionType("CARD_MANAGEMENT", 3, "Qu\u1ea3n l\u00fd th\u1ebb", "#FF6D00");
        vn.ai.trueid.sdk.models.sotp.TransactionType.SAVINGS = new vn.ai.trueid.sdk.models.sotp.TransactionType("SAVINGS", 4, "Ti\u1ebft ki\u1ec7m", "#00C853");
        vn.ai.trueid.sdk.models.sotp.TransactionType.INVESTMENT = new vn.ai.trueid.sdk.models.sotp.TransactionType("INVESTMENT", 5, "\u0110\u1ea7u t\u01b0", "#00C853");
        vn.ai.trueid.sdk.models.sotp.TransactionType.LOAN = new vn.ai.trueid.sdk.models.sotp.TransactionType("LOAN", 6, "Vay v\u1ed1n", "#00C853");
        vn.ai.trueid.sdk.models.sotp.TransactionType.ACCOUNT_SETTINGS = new vn.ai.trueid.sdk.models.sotp.TransactionType("ACCOUNT_SETTINGS", 7, "C\u00e0i \u0111\u1eb7t t\u00e0i kho\u1ea3n", "#FFD600");
        vn.ai.trueid.sdk.models.sotp.TransactionType.DEVICE_MANAGEMENT = new vn.ai.trueid.sdk.models.sotp.TransactionType("DEVICE_MANAGEMENT", 8, "Qu\u1ea3n l\u00fd thi\u1ebft b\u1ecb", "#D50000");
        vn.ai.trueid.sdk.models.sotp.TransactionType.LOGIN_APPROVAL = new vn.ai.trueid.sdk.models.sotp.TransactionType("LOGIN_APPROVAL", 9, "X\u00e1c th\u1ef1c \u0111\u0103ng nh\u1eadp", "#D50000");
        vn.ai.trueid.sdk.models.sotp.TransactionType.GENERIC = new vn.ai.trueid.sdk.models.sotp.TransactionType("GENERIC", 10, "X\u00e1c nh\u1eadn giao d\u1ecbch", "#00C853");
        vn.ai.trueid.sdk.models.sotp.TransactionType.$VALUES = vn.ai.trueid.sdk.models.sotp.TransactionType.$values();
        vn.ai.trueid.sdk.models.sotp.TransactionType.Companion = new vn.ai.trueid.sdk.models.sotp.TransactionType$Companion(0);
        return;
    }

    private TransactionType(String p1, int p2, String p3, String p4)
    {
        super(p1, p2);
        super.displayTitle = p3;
        super.headerColorHex = p4;
        return;
    }

    public static vn.ai.trueid.sdk.models.sotp.TransactionType valueOf(String p1)
    {
        return ((vn.ai.trueid.sdk.models.sotp.TransactionType) Enum.valueOf(vn.ai.trueid.sdk.models.sotp.TransactionType, p1));
    }

    public static vn.ai.trueid.sdk.models.sotp.TransactionType[] values()
    {
        return ((vn.ai.trueid.sdk.models.sotp.TransactionType[]) vn.ai.trueid.sdk.models.sotp.TransactionType.$VALUES.clone());
    }

    public final String getDisplayTitle()
    {
        return this.displayTitle;
    }

    public final String getHeaderColorHex()
    {
        return this.headerColorHex;
    }
}

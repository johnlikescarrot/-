package vn.com.vng.zalopay.data.cache.model;
public class ZPCDao$Properties {
    public static final sj.b Avatar;
    public static final sj.b DisplayName;
    public static final sj.b Id;
    public static final sj.b NormalizeDisplayName;
    public static final sj.b PhoneNumber;
    public static final sj.b Source;
    public static final sj.b SpecialCharEliminatedName;
    public static final sj.b Status;
    public static final sj.b ZaloId;
    public static final sj.b ZalopayId;
    public static final sj.b ZalopayName;

    static ZPCDao$Properties()
    {
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.Id = new sj.b(0, Long, "id", 1, "_id");
        Class v9 = Long.TYPE;
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.ZaloId = new sj.b(1, v9, "zaloId", 0, "ZALO_ID");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.ZalopayId = new sj.b(2, v9, "zalopayId", 0, "ZALOPAY_ID");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.PhoneNumber = new sj.b(3, String, "phoneNumber", 0, "PHONE_NUMBER");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.ZalopayName = new sj.b(4, String, "zalopayName", 0, "ZALOPAY_NAME");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.Avatar = new sj.b(5, String, "avatar", 0, "AVATAR");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.DisplayName = new sj.b(6, String, "displayName", 0, "DISPLAY_NAME");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.NormalizeDisplayName = new sj.b(7, String, "normalizeDisplayName", 0, "NORMALIZE_DISPLAY_NAME");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.SpecialCharEliminatedName = new sj.b(8, String, "specialCharEliminatedName", 0, "SPECIAL_CHAR_ELIMINATED_NAME");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.Status = new sj.b(9, v9, "status", 0, "STATUS");
        vn.com.vng.zalopay.data.cache.model.ZPCDao$Properties.Source = new sj.b(10, Integer, "source", 0, "SOURCE");
        return;
    }

    public ZPCDao$Properties()
    {
        return;
    }
}

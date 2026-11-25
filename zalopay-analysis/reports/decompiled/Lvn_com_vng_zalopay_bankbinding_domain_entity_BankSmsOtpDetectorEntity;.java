package vn.com.vng.zalopay.bankbinding.domain.entity;
public final class BankSmsOtpDetectorEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final java.util.List patterns;
    private final String sender;

    static BankSmsOtpDetectorEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity.CREATOR = new Io.S(16);
        return;
    }

    public BankSmsOtpDetectorEntity(String p1, java.util.List p2)
    {
        this.sender = p1;
        this.patterns = p2;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity p0, String p1, java.util.List p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.sender;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.patterns;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.sender;
    }

    public final java.util.List component2()
    {
        return this.patterns;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity copy(String p2, java.util.List p3)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity(p2, p3);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.sender, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity) p5).sender)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.patterns, ((vn.com.vng.zalopay.bankbinding.domain.entity.BankSmsOtpDetectorEntity) p5).patterns)) {
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
            return 1;
        }
    }

    public final java.util.List getPatterns()
    {
        return this.patterns;
    }

    public final String getSender()
    {
        return this.sender;
    }

    public int hashCode()
    {
        return ((this.sender.hashCode() * 31) + this.patterns.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.sender;
        java.util.List v1 = this.patterns;
        StringBuilder v2_1 = new StringBuilder("BankSmsOtpDetectorEntity(sender=");
        v2_1.append(v0_0);
        v2_1.append(", patterns=");
        v2_1.append(v1);
        v2_1.append(")");
        return v2_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.sender);
        p1.writeStringList(this.patterns);
        return;
    }
}

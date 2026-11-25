package vn.com.vng.zalopay.domain.bank.entity.qr;
public final class BankDialogEntity implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    private final String button;
    private final int errorCode;
    private final String message;
    private final String title;
    private final String type;

    static BankDialogEntity()
    {
        vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity.CREATOR = new m7.e(3);
        return;
    }

    public BankDialogEntity(String p1, String p2, String p3, int p4, String p5)
    {
        this.message = p1;
        this.title = p2;
        this.button = p3;
        this.errorCode = p4;
        this.type = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity copy$default(vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity p0, String p1, String p2, String p3, int p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.message;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.title;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.button;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.errorCode;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.type;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.message;
    }

    public final String component2()
    {
        return this.title;
    }

    public final String component3()
    {
        return this.button;
    }

    public final int component4()
    {
        return this.errorCode;
    }

    public final String component5()
    {
        return this.type;
    }

    public final vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity copy(String p7, String p8, String p9, int p10, String p11)
    {
        return new vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity) p5).message)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.title, ((vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity) p5).title)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.button, ((vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity) p5).button)) {
                            if (this.errorCode == ((vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity) p5).errorCode) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.type, ((vn.com.vng.zalopay.domain.bank.entity.qr.BankDialogEntity) p5).type)) {
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
            return 1;
        }
    }

    public final String getButton()
    {
        return this.button;
    }

    public final int getErrorCode()
    {
        return this.errorCode;
    }

    public final String getMessage()
    {
        return this.message;
    }

    public final String getTitle()
    {
        return this.title;
    }

    public final String getType()
    {
        return this.type;
    }

    public int hashCode()
    {
        return (this.type.hashCode() + Kb.e.v(this.errorCode, e5.i.c(e5.i.c((this.message.hashCode() * 31), 31, this.title), 31, this.button), 31));
    }

    public String toString()
    {
        String v2 = this.button;
        int v3 = this.errorCode;
        String v4 = this.type;
        String v0_2 = androidx.camera.camera2.internal.G.i("BankDialogEntity(message=", this.message, ", title=", this.title, ", button=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(v3, v2, ", errorCode=", ", type=", v0_2);
        return A1.a.l(v0_2, v4, ")");
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.message);
        p1.writeString(this.title);
        p1.writeString(this.button);
        p1.writeInt(this.errorCode);
        p1.writeString(this.type);
        return;
    }
}

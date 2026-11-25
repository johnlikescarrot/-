package vn.com.vng.zalopay.bankbinding.domain.entity.oao;
public final class ExtraInfoStepEntity implements android.os.Parcelable {
    public static final int $stable = 8;
    public static final android.os.Parcelable$Creator CREATOR;
    private final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity detail;
    private final String extraInfoRawData;
    private final String name;
    private final String traceId;
    private final String zpTransId;

    static ExtraInfoStepEntity()
    {
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity.CREATOR = new Zn.a(6);
        return;
    }

    public ExtraInfoStepEntity(String p1, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity p2, String p3, String p4, String p5)
    {
        this.name = p1;
        this.detail = p2;
        this.zpTransId = p3;
        this.traceId = p4;
        this.extraInfoRawData = p5;
        return;
    }

    public static synthetic vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity copy$default(vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity p0, String p1, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity p2, String p3, String p4, String p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.name;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.detail;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.zpTransId;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.traceId;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.extraInfoRawData;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.name;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity component2()
    {
        return this.detail;
    }

    public final String component3()
    {
        return this.zpTransId;
    }

    public final String component4()
    {
        return this.traceId;
    }

    public final String component5()
    {
        return this.extraInfoRawData;
    }

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity copy(String p7, vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity p8, String p9, String p10, String p11)
    {
        return new vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity(p7, p8, p9, p10, p11);
    }

    public final int describeContents()
    {
        return 0;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity) p5).name)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.detail, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity) p5).detail)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.zpTransId, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity) p5).zpTransId)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.traceId, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity) p5).traceId)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.extraInfoRawData, ((vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepEntity) p5).extraInfoRawData)) {
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

    public final vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity getDetail()
    {
        return this.detail;
    }

    public final String getExtraInfoRawData()
    {
        return this.extraInfoRawData;
    }

    public final String getName()
    {
        return this.name;
    }

    public final String getTraceId()
    {
        return this.traceId;
    }

    public final String getZpTransId()
    {
        return this.zpTransId;
    }

    public int hashCode()
    {
        int v1_7;
        int v0_8 = (this.name.hashCode() * 31);
        int v1_6 = this.detail;
        if (v1_6 != 0) {
            v1_7 = v1_6.hashCode();
        } else {
            v1_7 = 0;
        }
        return (((((((v0_8 + v1_7) * 31) + this.zpTransId.hashCode()) * 31) + this.traceId.hashCode()) * 31) + this.extraInfoRawData.hashCode());
    }

    public String toString()
    {
        String v0_0 = this.name;
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity v1 = this.detail;
        String v2 = this.zpTransId;
        String v3 = this.traceId;
        String v4 = this.extraInfoRawData;
        StringBuilder v5_1 = new StringBuilder("ExtraInfoStepEntity(name=");
        v5_1.append(v0_0);
        v5_1.append(", detail=");
        v5_1.append(v1);
        v5_1.append(", zpTransId=");
        v5_1.append(v2);
        v5_1.append(", traceId=");
        v5_1.append(v3);
        v5_1.append(", extraInfoRawData=");
        v5_1.append(v4);
        v5_1.append(")");
        return v5_1.toString();
    }

    public final void writeToParcel(android.os.Parcel p3, int p4)
    {
        p3.writeString(this.name);
        vn.com.vng.zalopay.bankbinding.domain.entity.oao.ExtraInfoStepDetailEntity v0_1 = this.detail;
        if (v0_1 != null) {
            p3.writeInt(1);
            v0_1.writeToParcel(p3, p4);
        } else {
            p3.writeInt(0);
        }
        p3.writeString(this.zpTransId);
        p3.writeString(this.traceId);
        p3.writeString(this.extraInfoRawData);
        return;
    }
}

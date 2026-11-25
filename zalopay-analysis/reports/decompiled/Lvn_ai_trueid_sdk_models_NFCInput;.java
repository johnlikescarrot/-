package vn.ai.trueid.sdk.models;
public final class NFCInput {
    private String idNumber;

    public NFCInput()
    {
        this(0, 1, 0);
        return;
    }

    public NFCInput(String p1)
    {
        this.idNumber = p1;
        return;
    }

    public synthetic NFCInput(String p1, int p2, kotlin.jvm.internal.DefaultConstructorMarker p3)
    {
        if ((p2 & 1) != 0) {
            p1 = "";
        }
        this(p1);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.NFCInput copy$default(vn.ai.trueid.sdk.models.NFCInput p0, String p1, int p2, Object p3)
    {
        if ((p2 & 1) != 0) {
            p1 = p0.idNumber;
        }
        return p0.copy(p1);
    }

    public final String component1()
    {
        return this.idNumber;
    }

    public final vn.ai.trueid.sdk.models.NFCInput copy(String p2)
    {
        return new vn.ai.trueid.sdk.models.NFCInput(p2);
    }

    public boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.ai.trueid.sdk.models.NFCInput)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.idNumber, ((vn.ai.trueid.sdk.models.NFCInput) p4).idNumber)) {
                    return 1;
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

    public final String getIdNumber()
    {
        return this.idNumber;
    }

    public int hashCode()
    {
        return this.idNumber.hashCode();
    }

    public final void setIdNumber(String p1)
    {
        this.idNumber = p1;
        return;
    }

    public String toString()
    {
        return String.valueOf(this.idNumber);
    }
}

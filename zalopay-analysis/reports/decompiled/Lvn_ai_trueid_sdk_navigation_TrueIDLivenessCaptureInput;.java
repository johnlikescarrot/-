package vn.ai.trueid.sdk.navigation;
public final class TrueIDLivenessCaptureInput {
    private final String personId;
    private final String transID;

    public TrueIDLivenessCaptureInput()
    {
        this(0, 0, 3, 0);
        return;
    }

    public TrueIDLivenessCaptureInput(String p1, String p2)
    {
        this.transID = p1;
        this.personId = p2;
        return;
    }

    public synthetic TrueIDLivenessCaptureInput(String p2, String p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 1) != 0) {
            p2 = "";
        }
        if ((p4 & 2) != 0) {
            p3 = "";
        }
        this(p2, p3);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput copy$default(vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.transID;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.personId;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.transID;
    }

    public final String component2()
    {
        return this.personId;
    }

    public final vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput copy(String p2, String p3)
    {
        return new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput(p2, p3);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.transID, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput) p5).transID)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.personId, ((vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureInput) p5).personId)) {
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

    public final String getPersonId()
    {
        return this.personId;
    }

    public final String getTransID()
    {
        return this.transID;
    }

    public int hashCode()
    {
        return (this.personId.hashCode() + (this.transID.hashCode() * 31));
    }

    public String toString()
    {
        return androidx.camera.camera2.internal.G.f("TrueIDLivenessCaptureInput(transID=", this.transID, ", personId=", this.personId, ")");
    }
}

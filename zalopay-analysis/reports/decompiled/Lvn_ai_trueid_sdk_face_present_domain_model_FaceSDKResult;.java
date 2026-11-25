package vn.ai.trueid.sdk.face.present.domain.model;
public final class FaceSDKResult {
    private final int code;
    private final String errorMessage;
    private final java.util.List faceDuration;
    private final java.util.List faceImages;

    public FaceSDKResult()
    {
        this(0, 0, 0, 0, 15, 0);
        return;
    }

    public FaceSDKResult(int p1, String p2, java.util.List p3, java.util.List p4)
    {
        this.code = p1;
        this.errorMessage = p2;
        this.faceImages = p3;
        this.faceDuration = p4;
        return;
    }

    public synthetic FaceSDKResult(int p1, String p2, java.util.List p3, java.util.List p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 1) != 0) {
            p1 = 0;
        }
        if ((p5 & 2) != 0) {
            p2 = "";
        }
        if ((p5 & 4) != 0) {
            p3 = kotlin.collections.E.emptyList();
        }
        if ((p5 & 8) != 0) {
            p4 = kotlin.collections.E.emptyList();
        }
        this(p1, p2, p3, p4);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult copy$default(vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult p0, int p1, String p2, java.util.List p3, java.util.List p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.errorMessage;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.faceImages;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.faceDuration;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final int component1()
    {
        return this.code;
    }

    public final String component2()
    {
        return this.errorMessage;
    }

    public final java.util.List component3()
    {
        return this.faceImages;
    }

    public final java.util.List component4()
    {
        return this.faceDuration;
    }

    public final vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult copy(int p2, String p3, java.util.List p4, java.util.List p5)
    {
        return new vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult(p2, p3, p4, p5);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult)) {
                if (this.code == ((vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.errorMessage, ((vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult) p5).errorMessage)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.faceImages, ((vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult) p5).faceImages)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.faceDuration, ((vn.ai.trueid.sdk.face.present.domain.model.FaceSDKResult) p5).faceDuration)) {
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
            return 1;
        }
    }

    public final int getCode()
    {
        return this.code;
    }

    public final String getErrorMessage()
    {
        return this.errorMessage;
    }

    public final java.util.List getFaceDuration()
    {
        return this.faceDuration;
    }

    public final java.util.List getFaceImages()
    {
        return this.faceImages;
    }

    public int hashCode()
    {
        return (this.faceDuration.hashCode() + Kb.e.h(e5.i.c((Integer.hashCode(this.code) * 31), 31, this.errorMessage), 31, this.faceImages));
    }

    public final org.json.JSONObject toJson()
    {
        return new org.json.JSONObject(this.toMap());
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("code", Integer.valueOf(this.code)), kotlin.TuplesKt.to("errorMessage", this.errorMessage), kotlin.TuplesKt.to("faceImages", this.faceImages), kotlin.TuplesKt.to("faceDuration", this.faceDuration)}));
    }

    public String toString()
    {
        String v0_2 = new StringBuilder("FaceSDKResult(code=");
        v0_2.append(this.code);
        v0_2.append(", errorMessage=");
        v0_2.append(this.errorMessage);
        v0_2.append(", faceImages=");
        v0_2.append(this.faceImages);
        v0_2.append(", faceDuration=");
        return androidx.compose.foundation.layout.G0.o(v0_2, this.faceDuration, 41);
    }
}

package vn.ai.faceauth.sdk.presentation.domain.model;
public final class LivenessResponse {
    private java.util.Map customerData;
    private java.util.Map data;
    private String errorDetail;
    private String personId;
    private Integer statusCode;
    private String transId;

    static LivenessResponse()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(31);
        return;
    }

    public LivenessResponse()
    {
        this.statusCode = Integer.valueOf(0);
        return;
    }

    public final native java.util.Map getCustomerData();

    public final native java.util.Map getData();

    public final native String getErrorDetail();

    public final native String getPersonId();

    public final native Integer getStatusCode();

    public final native String getTransId();

    public final native void setCustomerData(java.util.Map p0);

    public final native void setData(java.util.Map p0);

    public final native void setErrorDetail(String p0);

    public final native void setPersonId(String p0);

    public final native void setStatusCode(Integer p0);

    public final native void setTransId(String p0);

    public final native org.json.JSONObject toJson();

    public final native java.util.Map toMap();
}

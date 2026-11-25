package vn.com.vng.zalopay.domain.webapp.model;
public class WebInternalActionModel {
    public String callback;
    public String function;
    public String iconColor;
    public String iconId;
    public String iconLink;
    public String iconName;

    public WebInternalActionModel()
    {
        return;
    }

    public RZ.a toActionBarData()
    {
        String v1 = this.iconId;
        String v2 = this.iconLink;
        String v3 = this.iconName;
        String v4 = this.iconColor;
        String v5 = this.function;
        String v6 = this.callback;
        RZ.a v0_1 = new RZ.a();
        v0_1.a = v1;
        v0_1.b = v2;
        v0_1.c = v3;
        v0_1.d = v4;
        v0_1.e = v5;
        v0_1.f = v6;
        return v0_1;
    }
}

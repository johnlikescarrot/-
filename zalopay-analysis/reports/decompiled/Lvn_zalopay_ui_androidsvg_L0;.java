package vn.zalopay.ui.androidsvg;
public final class L0 implements org.xml.sax.Attributes {
    public final org.xmlpull.v1.XmlPullParser a;

    public L0(org.xmlpull.v1.XmlPullParser p1)
    {
        this.a = p1;
        return;
    }

    public final int getIndex(String p1)
    {
        return -1;
    }

    public final int getIndex(String p1, String p2)
    {
        return -1;
    }

    public final int getLength()
    {
        return this.a.getAttributeCount();
    }

    public final String getLocalName(int p2)
    {
        return this.a.getAttributeName(p2);
    }

    public final String getQName(int p4)
    {
        org.xmlpull.v1.XmlPullParser v0 = this.a;
        String v1 = v0.getAttributeName(p4);
        if (v0.getAttributePrefix(p4) == null) {
            return v1;
        } else {
            StringBuilder v2_2 = new StringBuilder();
            v2_2.append(v0.getAttributePrefix(p4));
            v2_2.append(58);
            v2_2.append(v1);
            return v2_2.toString();
        }
    }

    public final String getType(int p1)
    {
        return 0;
    }

    public final String getType(String p1)
    {
        return 0;
    }

    public final String getType(String p1, String p2)
    {
        return 0;
    }

    public final String getURI(int p2)
    {
        return this.a.getAttributeNamespace(p2);
    }

    public final String getValue(int p2)
    {
        return this.a.getAttributeValue(p2);
    }

    public final String getValue(String p1)
    {
        return 0;
    }

    public final String getValue(String p1, String p2)
    {
        return 0;
    }
}

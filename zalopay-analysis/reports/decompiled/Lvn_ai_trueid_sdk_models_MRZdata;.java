package vn.ai.trueid.sdk.models;
public final class MRZdata {
    private final String dateOfBirth;
    private final String dateOfExpiry;
    private final String documentNumber;

    public MRZdata(String p1, String p2, String p3)
    {
        this.documentNumber = p1;
        this.dateOfBirth = p2;
        this.dateOfExpiry = p3;
        return;
    }

    public final String getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public final String getDateOfExpiry()
    {
        return this.dateOfExpiry;
    }

    public final String getDocumentNumber()
    {
        return this.documentNumber;
    }
}

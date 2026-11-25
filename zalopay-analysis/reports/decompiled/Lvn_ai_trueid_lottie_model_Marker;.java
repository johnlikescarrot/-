package vn.ai.trueid.lottie.model;
public class Marker {
    private static final String CARRIAGE_RETURN = "\r";
    public final float durationFrames;
    private final String name;
    public final float startFrame;

    public Marker(String p1, float p2, float p3)
    {
        this.name = p1;
        this.durationFrames = p3;
        this.startFrame = p2;
        return;
    }

    public float getDurationFrames()
    {
        return this.durationFrames;
    }

    public String getName()
    {
        return this.name;
    }

    public float getStartFrame()
    {
        return this.startFrame;
    }

    public boolean matchesName(String p5)
    {
        if (!this.name.equalsIgnoreCase(p5)) {
            if (this.name.endsWith("\r")) {
                String v0_1 = this.name;
                if (v0_1.substring(0, (v0_1.length() - 1)).equalsIgnoreCase(p5)) {
                    return 1;
                }
            }
            return 0;
        } else {
            return 1;
        }
    }
}

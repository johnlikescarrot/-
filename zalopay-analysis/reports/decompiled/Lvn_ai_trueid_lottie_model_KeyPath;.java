package vn.ai.trueid.lottie.model;
public class KeyPath {
    public static final vn.ai.trueid.lottie.model.KeyPath COMPOSITION;
    private final java.util.List keys;
    private vn.ai.trueid.lottie.model.KeyPathElement resolvedElement;

    static KeyPath()
    {
        vn.ai.trueid.lottie.model.KeyPath.COMPOSITION = new vn.ai.trueid.lottie.model.KeyPath(new String[] {wmoi.igob.ggcn(6371)}));
        return;
    }

    private KeyPath(vn.ai.trueid.lottie.model.KeyPath p3)
    {
        this.keys = new java.util.ArrayList(p3.keys);
        this.resolvedElement = p3.resolvedElement;
        return;
    }

    public varargs KeyPath(String[] p1)
    {
        this.keys = java.util.Arrays.asList(p1);
        return;
    }

    private boolean endsWithGlobstar()
    {
        return ((String) androidx.media3.exoplayer.source.r.b(1, this.keys)).equals(wmoi.igob.ggcn(6372));
    }

    private boolean isContainer(String p2)
    {
        return wmoi.igob.ggcn(6373).equals(p2);
    }

    public vn.ai.trueid.lottie.model.KeyPath addKey(String p3)
    {
        vn.ai.trueid.lottie.model.KeyPath v0_1 = new vn.ai.trueid.lottie.model.KeyPath(this);
        v0_1.keys.add(p3);
        return v0_1;
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5) && (this.getClass() == p5.getClass())) {
                if (this.keys.equals(((vn.ai.trueid.lottie.model.KeyPath) p5).keys)) {
                    vn.ai.trueid.lottie.model.KeyPathElement v2_2 = this.resolvedElement;
                    boolean v5_1 = ((vn.ai.trueid.lottie.model.KeyPath) p5).resolvedElement;
                    if (v2_2 == null) {
                        if (!v5_1) {
                            return 1;
                        }
                    } else {
                        return v2_2.equals(v5_1);
                    }
                } else {
                    return 0;
                }
            }
            return 0;
        } else {
            return 1;
        }
    }

    public boolean fullyResolvesTo(String p6, int p7)
    {
        if (p7 < this.keys.size()) {
            java.util.List v0_1;
            if (p7 != (this.keys.size() - 1)) {
                v0_1 = 0;
            } else {
                v0_1 = 1;
            }
            boolean v3_2 = ((String) this.keys.get(p7));
            if (v3_2.equals(wmoi.igob.ggcn(6374))) {
                if ((v0_1 != null) || (!((String) this.keys.get((p7 + 1))).equals(p6))) {
                    if (v0_1 == null) {
                        boolean v7_1 = (p7 + 1);
                        if (v7_1 >= (this.keys.size() - 1)) {
                            return ((String) this.keys.get(v7_1)).equals(p6);
                        } else {
                            return 0;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    if ((p7 != (this.keys.size() - 2)) && ((p7 != (this.keys.size() - 3)) || (!this.endsWithGlobstar()))) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            } else {
                if ((!v3_2.equals(p6)) && (!v3_2.equals(wmoi.igob.ggcn(6375)))) {
                    boolean v6_13 = 0;
                } else {
                    v6_13 = 1;
                }
                if (((v0_1 == null) && ((p7 != (this.keys.size() - 2)) || (!this.endsWithGlobstar()))) || (!v6_13)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        } else {
            return 0;
        }
    }

    public vn.ai.trueid.lottie.model.KeyPathElement getResolvedElement()
    {
        return this.resolvedElement;
    }

    public int hashCode()
    {
        int v1_1;
        int v0_2 = (this.keys.hashCode() * 31);
        int v1_0 = this.resolvedElement;
        if (v1_0 == 0) {
            v1_1 = 0;
        } else {
            v1_1 = v1_0.hashCode();
        }
        return (v0_2 + v1_1);
    }

    public int incrementDepthBy(String p4, int p5)
    {
        if (!this.isContainer(p4)) {
            if (((String) this.keys.get(p5)).equals(wmoi.igob.ggcn(6376))) {
                if (p5 != (this.keys.size() - 1)) {
                    if (!((String) this.keys.get((p5 + 1))).equals(p4)) {
                        return 0;
                    } else {
                        return 2;
                    }
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public String keysToString()
    {
        return this.keys.toString();
    }

    public boolean matches(String p4, int p5)
    {
        if (!this.isContainer(p4)) {
            if (p5 < this.keys.size()) {
                if ((!((String) this.keys.get(p5)).equals(p4)) && ((!((String) this.keys.get(p5)).equals(wmoi.igob.ggcn(6377))) && (!((String) this.keys.get(p5)).equals(wmoi.igob.ggcn(6378))))) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public boolean propagateToChildren(String p2, int p3)
    {
        if (!wmoi.igob.ggcn(6379).equals(p2)) {
            if ((p3 >= (this.keys.size() - 1)) && (!((String) this.keys.get(p3)).equals(wmoi.igob.ggcn(6380)))) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    public vn.ai.trueid.lottie.model.KeyPath resolve(vn.ai.trueid.lottie.model.KeyPathElement p2)
    {
        vn.ai.trueid.lottie.model.KeyPath v0_1 = new vn.ai.trueid.lottie.model.KeyPath(this);
        v0_1.resolvedElement = p2;
        return v0_1;
    }

    public String toString()
    {
        int v1_2;
        String v0_2 = new StringBuilder(wmoi.igob.ggcn(6381));
        v0_2.append(this.keys);
        v0_2.append(wmoi.igob.ggcn(6382));
        if (this.resolvedElement == null) {
            v1_2 = 0;
        } else {
            v1_2 = 1;
        }
        return androidx.activity.result.e.r(v0_2, v1_2, 125);
    }
}

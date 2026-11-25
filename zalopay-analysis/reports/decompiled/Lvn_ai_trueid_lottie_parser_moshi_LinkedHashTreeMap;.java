package vn.ai.trueid.lottie.parser.moshi;
final class LinkedHashTreeMap extends java.util.AbstractMap implements java.io.Serializable {
    static final synthetic boolean $assertionsDisabled;
    private static final java.util.Comparator NATURAL_ORDER;
    java.util.Comparator comparator;
    private vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$EntrySet entrySet;
    final vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node header;
    private vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$KeySet keySet;
    int modCount;
    int size;
    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[] table;
    int threshold;

    static LinkedHashTreeMap()
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap.NATURAL_ORDER = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$1();
        return;
    }

    public LinkedHashTreeMap()
    {
        this(0);
        return;
    }

    public LinkedHashTreeMap(java.util.Comparator p2)
    {
        this.size = 0;
        this.modCount = 0;
        if (p2 == 0) {
            p2 = vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap.NATURAL_ORDER;
        }
        this.comparator = p2;
        this.header = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node();
        int v2_2 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[16];
        this.table = v2_2;
        this.threshold = ((v2_2.length / 4) + (v2_2.length / 2));
        return;
    }

    private void doubleCapacity()
    {
        int v0_1 = vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap.doubleCapacity(this.table);
        this.table = v0_1;
        this.threshold = ((v0_1.length / 4) + (v0_1.length / 2));
        return;
    }

    public static vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[] doubleCapacity(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[] p11)
    {
        int v0 = p11.length;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[] v1_1 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[(v0 * 2)];
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$AvlIterator v2_1 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$AvlIterator();
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$AvlBuilder v3_1 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$AvlBuilder();
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$AvlBuilder v4_1 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$AvlBuilder();
        int v6 = 0;
        while (v6 < v0) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_0 = p11[v6];
            if (v7_0 != null) {
                v2_1.reset(v7_0);
                int v8_0 = 0;
                int v9 = 0;
                while(true) {
                    int v10_0 = v2_1.next();
                    if (v10_0 == 0) {
                        break;
                    }
                    if ((v10_0.hash & v0) != 0) {
                        v9++;
                    } else {
                        v8_0++;
                    }
                }
                v3_1.reset(v8_0);
                v4_1.reset(v9);
                v2_1.reset(v7_0);
                while(true) {
                    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_1 = v2_1.next();
                    if (v7_1 == null) {
                        break;
                    }
                    if ((v7_1.hash & v0) != 0) {
                        v4_1.add(v7_1);
                    } else {
                        v3_1.add(v7_1);
                    }
                }
                int v8_1;
                vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_2 = 0;
                if (v8_0 <= 0) {
                    v8_1 = 0;
                } else {
                    v8_1 = v3_1.root();
                }
                v1_1[v6] = v8_1;
                int v8_2 = (v6 + v0);
                if (v9 > 0) {
                    v7_2 = v4_1.root();
                }
                v1_1[v8_2] = v7_2;
            }
            v6++;
        }
        return v1_1;
    }

    private boolean equal(Object p1, Object p2)
    {
        if ((p1 != p2) && ((p1 == 0) || (!p1.equals(p2)))) {
            return 0;
        } else {
            return 1;
        }
    }

    private void rebalance(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p8, boolean p9)
    {
        while (p8 != null) {
            int v3_5;
            int v0_0 = p8.left;
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_1 = p8.right;
            int v2_0 = 0;
            if (v0_0 == 0) {
                v3_5 = 0;
            } else {
                v3_5 = v0_0.height;
            }
            int v4;
            if (v1_1 == null) {
                v4 = 0;
            } else {
                v4 = v1_1.height;
            }
            int v5 = (v3_5 - v4);
            if (v5 != -2) {
                if (v5 != 2) {
                    if (v5 != 0) {
                        p8.height = (Math.max(v3_5, v4) + 1);
                        if (!p9) {
                            break;
                        }
                    } else {
                        p8.height = (v3_5 + 1);
                        if (p9) {
                            break;
                        }
                    }
                } else {
                    int v3_2;
                    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_2 = v0_0.left;
                    int v3_1 = v0_0.right;
                    if (v3_1 == 0) {
                        v3_2 = 0;
                    } else {
                        v3_2 = v3_1.height;
                    }
                    if (v1_2 != null) {
                        v2_0 = v1_2.height;
                    }
                    int v2_1 = (v2_0 - v3_2);
                    if ((v2_1 != 1) && ((v2_1 != 0) || (p9))) {
                        this.rotateLeft(v0_0);
                        this.rotateRight(p8);
                    } else {
                        this.rotateRight(p8);
                    }
                    if (p9) {
                        break;
                    }
                }
            } else {
                int v3_4;
                int v0_3 = v1_1.left;
                int v3_3 = v1_1.right;
                if (v3_3 == 0) {
                    v3_4 = 0;
                } else {
                    v3_4 = v3_3.height;
                }
                if (v0_3 != 0) {
                    v2_0 = v0_3.height;
                }
                int v2_2 = (v2_0 - v3_4);
                if ((v2_2 != -1) && ((v2_2 != 0) || (p9))) {
                    this.rotateRight(v1_1);
                    this.rotateLeft(p8);
                } else {
                    this.rotateLeft(p8);
                }
                if (p9) {
                    break;
                }
            }
            p8 = p8.parent;
        }
        return;
    }

    private void replaceInParent(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p3, vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p4)
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[] v0_0 = p3.parent;
        p3.parent = 0;
        if (p4 != null) {
            p4.parent = v0_0;
        }
        if (v0_0 == null) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[] v0_1 = this.table;
            v0_1[(p3.hash & (v0_1.length - 1))] = p4;
            return;
        } else {
            if (v0_0.left != p3) {
                v0_0.right = p4;
                return;
            } else {
                v0_0.left = p4;
                return;
            }
        }
    }

    private void rotateLeft(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p6)
    {
        int v0_0 = p6.left;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1 = p6.right;
        int v2_1 = v1.left;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v3 = v1.right;
        p6.right = v2_1;
        if (v2_1 != 0) {
            v2_1.parent = p6;
        }
        int v0_1;
        this.replaceInParent(p6, v1);
        v1.left = p6;
        p6.parent = v1;
        int v4 = 0;
        if (v0_0 == 0) {
            v0_1 = 0;
        } else {
            v0_1 = v0_0.height;
        }
        int v2_0;
        if (v2_1 == 0) {
            v2_0 = 0;
        } else {
            v2_0 = v2_1.height;
        }
        int v0_3 = (Math.max(v0_1, v2_0) + 1);
        p6.height = v0_3;
        if (v3 != null) {
            v4 = v3.height;
        }
        v1.height = (Math.max(v0_3, v4) + 1);
        return;
    }

    private void rotateRight(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p6)
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v0 = p6.left;
        int v1_0 = p6.right;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v2 = v0.left;
        int v3_1 = v0.right;
        p6.left = v3_1;
        if (v3_1 != 0) {
            v3_1.parent = p6;
        }
        int v1_1;
        this.replaceInParent(p6, v0);
        v0.right = p6;
        p6.parent = v0;
        int v4 = 0;
        if (v1_0 == 0) {
            v1_1 = 0;
        } else {
            v1_1 = v1_0.height;
        }
        int v3_0;
        if (v3_1 == 0) {
            v3_0 = 0;
        } else {
            v3_0 = v3_1.height;
        }
        int v1_3 = (Math.max(v1_1, v3_0) + 1);
        p6.height = v1_3;
        if (v2 != null) {
            v4 = v2.height;
        }
        v0.height = (Math.max(v1_3, v4) + 1);
        return;
    }

    private static int secondaryHash(int p2)
    {
        int v2_1 = (p2 ^ ((p2 >> 20) ^ (p2 >> 12)));
        return ((v2_1 >> 4) ^ ((v2_1 >> 7) ^ v2_1));
    }

    private Object writeReplace()
    {
        return new java.util.LinkedHashMap(this);
    }

    public void clear()
    {
        java.util.Arrays.fill(this.table, 0);
        this.size = 0;
        this.modCount = (this.modCount + 1);
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v0_4 = this.header;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v2 = v0_4.next;
        while (v2 != v0_4) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v3 = v2.next;
            v2.prev = 0;
            v2.next = 0;
            v2 = v3;
        }
        v0_4.prev = v0_4;
        v0_4.next = v0_4;
        return;
    }

    public boolean containsKey(Object p1)
    {
        if (this.findByObject(p1) == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public java.util.Set entrySet()
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$EntrySet v0_0 = this.entrySet;
        if (v0_0 == null) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$EntrySet v0_2 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$EntrySet(this);
            this.entrySet = v0_2;
            return v0_2;
        } else {
            return v0_0;
        }
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node find(Object p12, boolean p13)
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_0;
        String v0_0 = this.comparator;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node[] v1 = this.table;
        int v6 = vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap.secondaryHash(p12.hashCode());
        int v2_3 = ((v1.length - 1) & v6);
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v3_0 = v1[v2_3];
        if (v3_0 == null) {
            v7_0 = 0;
        } else {
            String v5_1;
            if (v0_0 != vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap.NATURAL_ORDER) {
                v5_1 = 0;
            } else {
                v5_1 = ((Comparable) p12);
            }
            while(true) {
                if (v5_1 == null) {
                    v7_0 = v0_0.compare(p12, v3_0.key);
                } else {
                    v7_0 = v5_1.compareTo(v3_0.key);
                }
                if (v7_0 != null) {
                    vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v8_0;
                    if (v7_0 >= null) {
                        v8_0 = v3_0.right;
                    } else {
                        v8_0 = v3_0.left;
                    }
                    if (v8_0 == null) {
                        break;
                    }
                    v3_0 = v8_0;
                } else {
                    return v3_0;
                }
            }
        }
        int v10 = v7_0;
        if (p13 != 0) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v3_2;
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_3 = this.header;
            if (v3_0 != null) {
                vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v4_1 = v3_0;
                v3_2 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node(v4_1, p12, v6, v7_3, v7_3.prev);
                if (v10 >= 0) {
                    v4_1.right = v3_2;
                } else {
                    v4_1.left = v3_2;
                }
                this.rebalance(v4_1, 1);
            } else {
                if ((v0_0 == vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap.NATURAL_ORDER) && (!(p12 instanceof Comparable))) {
                    throw new ClassCastException(p12.getClass().getName().concat(" is not Comparable"));
                } else {
                    v3_2 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node(v3_0, p12, v6, v7_3, v7_3.prev);
                    v1[v2_3] = v3_2;
                }
            }
            int v12_4 = this.size;
            this.size = (v12_4 + 1);
            if (v12_4 > this.threshold) {
                this.doubleCapacity();
            }
            this.modCount = (this.modCount + 1);
            return v3_2;
        } else {
            return 0;
        }
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node findByEntry(java.util.Map$Entry p3)
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v0_1 = this.findByObject(p3.getKey());
        if ((v0_1 == null) || (!this.equal(v0_1.value, p3.getValue()))) {
            return 0;
        } else {
            return v0_1;
        }
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node findByObject(Object p3)
    {
        try {
            if (p3 == null) {
                return 0;
            } else {
                return this.find(p3, 0);
            }
        } catch (ClassCastException) {
            return 0;
        }
    }

    public Object get(Object p1)
    {
        int v1_1 = this.findByObject(p1);
        if (v1_1 == 0) {
            return 0;
        } else {
            return v1_1.value;
        }
    }

    public java.util.Set keySet()
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$KeySet v0_0 = this.keySet;
        if (v0_0 == null) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$KeySet v0_2 = new vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$KeySet(this);
            this.keySet = v0_2;
            return v0_2;
        } else {
            return v0_0;
        }
    }

    public Object put(Object p2, Object p3)
    {
        if (p2 == null) {
            throw new NullPointerException("key == null");
        } else {
            NullPointerException v2_3 = this.find(p2, 1);
            Object v0_1 = v2_3.value;
            v2_3.value = p3;
            return v0_1;
        }
    }

    public Object remove(Object p1)
    {
        int v1_1 = this.removeInternalByKey(p1);
        if (v1_1 == 0) {
            return 0;
        } else {
            return v1_1.value;
        }
    }

    public void removeInternal(vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node p6, boolean p7)
    {
        if (p7 != null) {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_1 = p6.prev;
            v7_1.next = p6.next;
            p6.next.prev = v7_1;
            p6.prev = 0;
            p6.next = 0;
        }
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_3 = p6.left;
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_0 = p6.right;
        int v2_0 = p6.parent;
        int v3 = 0;
        if ((v7_3 == null) || (v1_0 == null)) {
            if (v7_3 == null) {
                if (v1_0 == null) {
                    this.replaceInParent(p6, 0);
                } else {
                    this.replaceInParent(p6, v1_0);
                    p6.right = 0;
                }
            } else {
                this.replaceInParent(p6, v7_3);
                p6.left = 0;
            }
            this.rebalance(v2_0, 0);
            this.size = (this.size - 1);
            this.modCount = (this.modCount + 1);
            return;
        } else {
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v7_2;
            if (v7_3.height <= v1_0.height) {
                v7_2 = v1_0.first();
            } else {
                v7_2 = v7_3.last();
            }
            int v2_2;
            this.removeInternal(v7_2, 0);
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_2 = p6.left;
            if (v1_2 == null) {
                v2_2 = 0;
            } else {
                v2_2 = v1_2.height;
                v7_2.left = v1_2;
                v1_2.parent = v7_2;
                p6.left = 0;
            }
            vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v1_3 = p6.right;
            if (v1_3 != null) {
                v3 = v1_3.height;
                v7_2.right = v1_3;
                v1_3.parent = v7_2;
                p6.right = 0;
            }
            v7_2.height = (Math.max(v2_2, v3) + 1);
            this.replaceInParent(p6, v7_2);
            return;
        }
    }

    public vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node removeInternalByKey(Object p2)
    {
        vn.ai.trueid.lottie.parser.moshi.LinkedHashTreeMap$Node v2_1 = this.findByObject(p2);
        if (v2_1 != null) {
            this.removeInternal(v2_1, 1);
        }
        return v2_1;
    }

    public int size()
    {
        return this.size;
    }
}

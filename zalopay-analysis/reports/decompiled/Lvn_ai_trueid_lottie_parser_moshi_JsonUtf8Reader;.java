package vn.ai.trueid.lottie.parser.moshi;
final class JsonUtf8Reader extends vn.ai.trueid.lottie.parser.moshi.JsonReader {
    private static final okio.ByteString CLOSING_BLOCK_COMMENT = None;
    private static final okio.ByteString DOUBLE_QUOTE_OR_SLASH = None;
    private static final okio.ByteString LINEFEED_OR_CARRIAGE_RETURN = None;
    private static final long MIN_INCOMPLETE_INTEGER = 17524406870024074036;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 0;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 0;
    private static final int PEEKED_BUFFERED_NAME = 15;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 0;
    private static final int PEEKED_END_OBJECT = 0;
    private static final int PEEKED_EOF = 18;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 16;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 17;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private static final okio.ByteString SINGLE_QUOTE_OR_SLASH;
    private static final okio.ByteString UNQUOTED_STRING_TERMINALS;
    private final okio.Buffer buffer;
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private final okio.BufferedSource source;

    static JsonUtf8Reader()
    {
        wmoi.igob.jtgo(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader, 20417, 20421);
        vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH = okio.ByteString.encodeUtf8("\'\\");
        vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH = okio.ByteString.encodeUtf8("\"\\");
        vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.UNQUOTED_STRING_TERMINALS = okio.ByteString.encodeUtf8("{}[]:, \n\t\r\u000c/\\;#=");
        vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.LINEFEED_OR_CARRIAGE_RETURN = okio.ByteString.encodeUtf8("\n\r");
        vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.CLOSING_BLOCK_COMMENT = okio.ByteString.encodeUtf8("*/");
        return;
    }

    public JsonUtf8Reader(okio.BufferedSource p2)
    {
        this.peeked = 0;
        if (p2 == null) {
            throw new NullPointerException("source == null");
        } else {
            this.source = p2;
            this.buffer = p2.buffer();
            this.pushScope(6);
            return;
        }
    }

    private void checkLenient()
    {
        if (!this.lenient) {
            throw this.syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        } else {
            return;
        }
    }

    private int doPeek()
    {
        void v0_0 = this;
        vn.ai.trueid.lottie.parser.moshi.JsonEncodingException v1_0 = this.scopes;
        String v2_5 = this.stackSize;
        int v3_1 = v1_0[(v2_5 - 1)];
        if (v3_1 != 1) {
            if (v3_1 != 2) {
                if ((v3_1 != 3) && (v3_1 != 5)) {
                    if (v3_1 != 4) {
                        if (v3_1 != 6) {
                            if (v3_1 != 7) {
                                if (v3_1 == 8) {
                                    throw new IllegalStateException("JsonReader is closed");
                                }
                            } else {
                                if (this.nextNonWhitespace(0) != -1) {
                                    v0_0 = this.checkLenient();
                                } else {
                                    this.peeked = 18;
                                    return 18;
                                }
                            }
                        } else {
                            v1_0[(v2_5 - 1)] = 7;
                        }
                    } else {
                        v1_0[(v2_5 - 1)] = 5;
                        vn.ai.trueid.lottie.parser.moshi.JsonEncodingException v1_38 = this.nextNonWhitespace(1);
                        this.buffer.readByte();
                        if (v1_38 != 58) {
                            if (v1_38 != 61) {
                                throw this.syntaxError("Expected \':\'");
                            } else {
                                v0_0 = this.checkLenient();
                                if ((v0_0.source.request(1)) && (v0_0.buffer.getByte(0) == 62)) {
                                    v0_0.buffer.readByte();
                                }
                            }
                        }
                    }
                } else {
                    v1_0[(v2_5 - 1)] = 4;
                    if (v3_1 == 5) {
                        String v2_15 = this.nextNonWhitespace(1);
                        this.buffer.readByte();
                        if (v2_15 != 44) {
                            if (v2_15 == 59) {
                                v0_0 = this.checkLenient();
                            } else {
                                if (v2_15 != 125) {
                                    throw this.syntaxError("Unterminated object");
                                } else {
                                    this.peeked = 2;
                                    return 2;
                                }
                                vn.ai.trueid.lottie.parser.moshi.JsonEncodingException v1_14 = v0_0.nextNonWhitespace(1);
                                if (v1_14 == 34) {
                                    v0_0.buffer.readByte();
                                    v0_0.peeked = 9;
                                    return 9;
                                } else {
                                    if (v1_14 == 39) {
                                        void v0_2 = v0_0.checkLenient();
                                        v0_2.buffer.readByte();
                                        v0_2.peeked = 8;
                                        return 8;
                                    } else {
                                        if ((v1_14 != 44) && (v1_14 != 59)) {
                                            if (v1_14 == 91) {
                                                v0_0.buffer.readByte();
                                                v0_0.peeked = 3;
                                                return 3;
                                            } else {
                                                if (v1_14 == 93) {
                                                    if (v3_1 == 1) {
                                                        v0_0.buffer.readByte();
                                                        v0_0.peeked = 4;
                                                        return 4;
                                                    }
                                                } else {
                                                    if (v1_14 == 123) {
                                                        v0_0.buffer.readByte();
                                                        v0_0.peeked = 1;
                                                        return 1;
                                                    } else {
                                                        vn.ai.trueid.lottie.parser.moshi.JsonEncodingException v1_25 = v0_0.peekKeyword();
                                                        if (v1_25 == null) {
                                                            vn.ai.trueid.lottie.parser.moshi.JsonEncodingException v1_26 = v0_0.peekNumber();
                                                            if (v1_26 == null) {
                                                                if (!v0_0.isLiteral(v0_0.buffer.getByte(0))) {
                                                                    throw v0_0.syntaxError("Expected value");
                                                                } else {
                                                                    v0_0.checkLenient().peeked = 10;
                                                                    return 10;
                                                                }
                                                            } else {
                                                                return v1_26;
                                                            }
                                                        } else {
                                                            return v1_25;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if ((v3_1 != 1) && (v3_1 != 2)) {
                                            throw v0_0.syntaxError("Unexpected value");
                                        } else {
                                            v0_0.checkLenient().peeked = 7;
                                            return 7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    String v2_16 = v0_0.nextNonWhitespace(1);
                    if (v2_16 == 34) {
                        v0_0.buffer.readByte();
                        v0_0.peeked = 13;
                        return 13;
                    } else {
                        if (v2_16 == 39) {
                            v0_0.buffer.readByte();
                            v0_0.checkLenient().peeked = 12;
                            return 12;
                        } else {
                            if (v2_16 == 125) {
                                if (v3_1 == 5) {
                                    throw v0_0.syntaxError("Expected name");
                                } else {
                                    v0_0.buffer.readByte();
                                    v0_0.peeked = 2;
                                    return 2;
                                }
                            } else {
                                void v0_1 = v0_0.checkLenient();
                                if (!v0_1.isLiteral(((char) v2_16))) {
                                    throw v0_1.syntaxError("Expected name");
                                } else {
                                    v0_1.peeked = 14;
                                    return 14;
                                }
                            }
                        }
                    }
                }
            } else {
                vn.ai.trueid.lottie.parser.moshi.JsonEncodingException v1_11 = this.nextNonWhitespace(1);
                this.buffer.readByte();
                if (v1_11 != 44) {
                    if (v1_11 == 59) {
                        v0_0 = this.checkLenient();
                    } else {
                        if (v1_11 != 93) {
                            throw this.syntaxError("Unterminated array");
                        } else {
                            this.peeked = 4;
                            return 4;
                        }
                    }
                }
            }
        } else {
            v1_0[(v2_5 - 1)] = 2;
        }
    }

    private int findName(String p5, vn.ai.trueid.lottie.parser.moshi.JsonReader$Options p6)
    {
        int v2 = 0;
        while (v2 < p6.strings.length) {
            if (!p5.equals(p6.strings[v2])) {
                v2++;
            } else {
                this.peeked = 0;
                this.pathNames[(this.stackSize - 1)] = p5;
                return v2;
            }
        }
        return -1;
    }

    private boolean isLiteral(int p2)
    {
        if ((p2 != 9) && ((p2 != 10) && ((p2 != 12) && ((p2 != 13) && (p2 != 32))))) {
            if (p2 != 35) {
                if (p2 == 44) {
                    return 0;
                } else {
                    if ((p2 != 47) && (p2 != 61)) {
                        if ((p2 == 123) || ((p2 == 125) || (p2 == 58))) {
                            return 0;
                        } else {
                            if (p2 != 59) {
                                switch (p2) {
                                    case 92:
                                    default:
                                        return 1;
                                }
                            }
                        }
                    }
                }
            }
            this.checkLenient();
        }
        return 0;
    }

    private int nextNonWhitespace(boolean p7)
    {
        do {
            okio.Buffer v1_0 = 0;
            byte v3_1 = (v1_0 + 1);
            if (!this.source.request(((long) v3_1))) {
                if (p7 != null) {
                    throw new java.io.EOFException("End of input");
                } else {
                    return -1;
                }
            } else {
                byte v2_1 = this.buffer.getByte(((long) v1_0));
                if ((v2_1 == 10) || ((v2_1 == 32) || ((v2_1 == 13) || (v2_1 == 9)))) {
                    v1_0 = v3_1;
                    while(true) {
                    }
                }
            }
            this.buffer.skip(((long) v1_0));
            if (v2_1 != 47) {
                if (v2_1 == 35) {
                    this.checkLenient();
                    this.skipToEndOfLine();
                }
            } else {
                if (this.source.request(2)) {
                    this.checkLenient();
                    byte v3_5 = this.buffer.getByte(1);
                    if (v3_5 == 42) {
                        this.buffer.readByte();
                        this.buffer.readByte();
                    } else {
                        if (v3_5 == 47) {
                            this.buffer.readByte();
                            this.buffer.readByte();
                            this.skipToEndOfLine();
                        }
                    }
                }
            }
            return v2_1;
        } while(!this.skipToEndOfBlockComment());
        throw this.syntaxError("Unterminated comment");
    }

    private String nextQuotedValue(okio.ByteString p6)
    {
        okio.Buffer v0_0 = 0;
        while(true) {
            char v1_1 = this.source.indexOfElement(p6);
            if (v1_1 == -1) {
                break;
            }
            if (this.buffer.getByte(v1_1) != 92) {
                if (v0_0 != null) {
                    v0_0.append(this.buffer.readUtf8(v1_1));
                    this.buffer.readByte();
                    return v0_0.toString();
                } else {
                    String v6_6 = this.buffer.readUtf8(v1_1);
                    this.buffer.readByte();
                    return v6_6;
                }
            } else {
                if (v0_0 == null) {
                    v0_0 = new StringBuilder();
                }
                v0_0.append(this.buffer.readUtf8(v1_1));
                this.buffer.readByte();
                v0_0.append(this.readEscapeCharacter());
            }
        }
        throw this.syntaxError("Unterminated string");
    }

    private String nextUnquotedValue()
    {
        String v0_2 = this.source.indexOfElement(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.UNQUOTED_STRING_TERMINALS);
        if (v0_2 == -1) {
            return this.buffer.readUtf8();
        } else {
            return this.buffer.readUtf8(v0_2);
        }
    }

    private int peekKeyword()
    {
        long v1_4;
        int v3;
        okio.Buffer v0_1;
        okio.Buffer v0_2 = this.buffer.getByte(0);
        if ((v0_2 != 116) && (v0_2 != 84)) {
            if ((v0_2 != 102) && (v0_2 != 70)) {
                if ((v0_2 != 110) && (v0_2 != 78)) {
                    return 0;
                } else {
                    v0_1 = "null";
                    v1_4 = "NULL";
                    v3 = 7;
                }
            } else {
                v0_1 = "false";
                v1_4 = "FALSE";
                v3 = 6;
            }
        } else {
            v0_1 = "true";
            v1_4 = "TRUE";
            v3 = 5;
        }
        int v4 = v0_1.length();
        int v5_0 = 1;
        while (v5_0 < v4) {
            int v7 = (v5_0 + 1);
            if (this.source.request(((long) v7))) {
                byte v6_3 = this.buffer.getByte(((long) v5_0));
                if ((v6_3 == v0_1.charAt(v5_0)) || (v6_3 == v1_4.charAt(v5_0))) {
                    v5_0 = v7;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        if ((!this.source.request(((long) (v4 + 1)))) || (!this.isLiteral(this.buffer.getByte(((long) v4))))) {
            this.buffer.skip(((long) v4));
            this.peeked = v3;
            return v3;
        } else {
            return 0;
        }
    }

    private int peekNumber()
    {
        int v7 = 1;
        int v5 = 0;
        int v6 = 0;
        long v8 = 0;
        int v10 = 0;
        while(true) {
            int v18;
            long v16;
            int v12 = (v5 + 1);
            if (this.source.request(((long) v12))) {
                v16 = 0;
                int v1_5 = this.buffer.getByte(((long) v5));
                v18 = 0;
                if (v1_5 == 43) {
                    if (v6 != 5) {
                        return 0;
                    } else {
                        v6 = 6;
                    }
                } else {
                    if ((v1_5 == 69) || (v1_5 == 101)) {
                        if ((v6 != 2) && (v6 != 4)) {
                            break;
                        }
                        v6 = 5;
                    } else {
                        if (v1_5 == 45) {
                            if (v6 != 0) {
                                if (v6 != 5) {
                                    return 0;
                                }
                            } else {
                                v6 = 1;
                                v10 = 1;
                            }
                        } else {
                            if (v1_5 == 46) {
                                if (v6 != 2) {
                                    return 0;
                                } else {
                                    v6 = 3;
                                }
                            } else {
                                if ((v1_5 >= 48) && (v1_5 <= 57)) {
                                    if ((v6 != 1) && (v6 != 0)) {
                                        if (v6 != 2) {
                                            if (v6 != 3) {
                                                if ((v6 == 5) || (v6 == 6)) {
                                                    v6 = 7;
                                                }
                                            } else {
                                                v6 = 4;
                                            }
                                        } else {
                                            if (v8 != 0) {
                                                int v1_9;
                                                long v2_11 = ((10 * v8) - ((long) (v1_5 - 48)));
                                                int v1_7 = v8 cmp -922337203685477580;
                                                if ((v1_7 <= 0) && ((v1_7 != 0) || (v2_11 >= v8))) {
                                                    v1_9 = 0;
                                                } else {
                                                    v1_9 = 1;
                                                }
                                                v7 &= v1_9;
                                                v8 = v2_11;
                                            } else {
                                                return 0;
                                            }
                                        }
                                    } else {
                                        v8 = ((long) (- (v1_5 - 48)));
                                        v6 = 2;
                                    }
                                } else {
                                    if (this.isLiteral(v1_5)) {
                                        return 0;
                                    } else {
                                        if (((v6 != 2) || ((v7 == 0) || ((v8 == -9223372036854775808) && (v10 == 0)))) || ((v8 == v16) && (v10 != 0))) {
                                            if ((v6 != 2) && ((v6 != 4) && (v6 != 7))) {
                                                return v18;
                                            } else {
                                                this.peekedNumberLength = v5;
                                                this.peeked = 17;
                                                return 17;
                                            }
                                        } else {
                                            if (v10 == 0) {
                                                v8 = (- v8);
                                            }
                                            this.peekedLong = v8;
                                            this.buffer.skip(((long) v5));
                                            this.peeked = 16;
                                            return 16;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                v5 = v12;
            } else {
                v16 = 0;
                v18 = 0;
            }
        }
        return 0;
    }

    private char readEscapeCharacter()
    {
        if (!this.source.request(1)) {
            throw this.syntaxError("Unterminated escape sequence");
        } else {
            int v0_3 = this.buffer.readByte();
            if ((v0_3 == 10) || ((v0_3 == 34) || ((v0_3 == 39) || ((v0_3 == 47) || (v0_3 == 92))))) {
                return ((char) v0_3);
            } else {
                if (v0_3 == 98) {
                    return 8;
                } else {
                    if (v0_3 == 102) {
                        return 12;
                    } else {
                        if (v0_3 == 110) {
                            return 10;
                        } else {
                            if (v0_3 == 114) {
                                return 13;
                            } else {
                                if (v0_3 == 116) {
                                    return 9;
                                } else {
                                    if (v0_3 == 117) {
                                        if (!this.source.request(4)) {
                                            String v1_10 = new StringBuilder("Unterminated escape sequence at path ");
                                            v1_10.append(this.getPath());
                                            throw new java.io.EOFException(v1_10.toString());
                                        } else {
                                            int v0_1 = 0;
                                            String v1_1 = 0;
                                            while (v0_1 < 4) {
                                                int v5_4;
                                                int v5_3 = this.buffer.getByte(((long) v0_1));
                                                if ((v5_3 < 48) || (v5_3 > 57)) {
                                                    if ((v5_3 < 97) || (v5_3 > 102)) {
                                                        if ((v5_3 < 65) || (v5_3 > 70)) {
                                                            int v0_21 = new StringBuilder("\\u");
                                                            v0_21.append(this.buffer.readUtf8(4));
                                                            throw this.syntaxError(v0_21.toString());
                                                        } else {
                                                            v5_4 = (v5_3 - 55);
                                                        }
                                                    } else {
                                                        v5_4 = (v5_3 - 87);
                                                    }
                                                } else {
                                                    v5_4 = (v5_3 - 48);
                                                }
                                                v1_1 = ((char) (v5_4 + ((char) (v1_1 << 4))));
                                                v0_1++;
                                            }
                                            this.buffer.skip(4);
                                            return v1_1;
                                        }
                                    } else {
                                        if (!this.lenient) {
                                            String v1_4 = new StringBuilder("Invalid escape sequence: \\");
                                            v1_4.append(((char) v0_3));
                                            throw this.syntaxError(v1_4.toString());
                                        } else {
                                            return ((char) v0_3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void skipQuotedValue(okio.ByteString p7)
    {
        while(true) {
            long v0_2 = this.source.indexOfElement(p7);
            if (v0_2 == -1) {
                break;
            }
            if (this.buffer.getByte(v0_2) != 92) {
                this.buffer.skip((v0_2 + 1));
                return;
            } else {
                this.buffer.skip((v0_2 + 1));
                this.readEscapeCharacter();
            }
        }
        throw this.syntaxError("Unterminated string");
    }

    private boolean skipToEndOfBlockComment()
    {
        int v0_2;
        int v1_0 = vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.CLOSING_BLOCK_COMMENT;
        long v2_1 = this.source.indexOf(v1_0);
        if (v2_1 == -1) {
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        long v2_0;
        okio.Buffer v4_1 = this.buffer;
        if (v0_2 == 0) {
            v2_0 = v4_1.size();
        } else {
            v2_0 = (v2_1 + ((long) v1_0.size()));
        }
        v4_1.skip(v2_0);
        return v0_2;
    }

    private void skipToEndOfLine()
    {
        long v0_1;
        long v0_2 = this.source.indexOfElement(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.LINEFEED_OR_CARRIAGE_RETURN);
        okio.Buffer v2 = this.buffer;
        if (v0_2 == -1) {
            v0_1 = v2.size();
        } else {
            v0_1 = (v0_2 + 1);
        }
        v2.skip(v0_1);
        return;
    }

    private void skipUnquotedValue()
    {
        long v0_1 = this.source.indexOfElement(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.UNQUOTED_STRING_TERMINALS);
        okio.Buffer v2 = this.buffer;
        if (v0_1 == -1) {
            v0_1 = v2.size();
        }
        v2.skip(v0_1);
        return;
    }

    public void beginArray()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        if (v0_0 != 3) {
            String v1_4 = new StringBuilder("Expected BEGIN_ARRAY but was ");
            v1_4.append(this.peek());
            v1_4.append(" at path ");
            v1_4.append(this.getPath());
            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_4.toString());
        } else {
            this.pushScope(1);
            this.pathIndices[(this.stackSize - 1)] = 0;
            this.peeked = 0;
            return;
        }
    }

    public void beginObject()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        if (v0_0 != 1) {
            String v1_3 = new StringBuilder("Expected BEGIN_OBJECT but was ");
            v1_3.append(this.peek());
            v1_3.append(" at path ");
            v1_3.append(this.getPath());
            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_3.toString());
        } else {
            this.pushScope(3);
            this.peeked = 0;
            return;
        }
    }

    public void close()
    {
        this.peeked = 0;
        this.scopes[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
        return;
    }

    public void endArray()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        if (v0_0 != 4) {
            String v1_5 = new StringBuilder("Expected END_ARRAY but was ");
            v1_5.append(this.peek());
            v1_5.append(" at path ");
            v1_5.append(this.getPath());
            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_5.toString());
        } else {
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_2 = this.stackSize;
            this.stackSize = (v0_2 - 1);
            String v1_2 = this.pathIndices;
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_3 = (v0_2 - 2);
            v1_2[v0_3] = (v1_2[v0_3] + 1);
            this.peeked = 0;
            return;
        }
    }

    public void endObject()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        if (v0_0 != 2) {
            String v1_5 = new StringBuilder("Expected END_OBJECT but was ");
            v1_5.append(this.peek());
            v1_5.append(" at path ");
            v1_5.append(this.getPath());
            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_5.toString());
        } else {
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_2 = this.stackSize;
            String v2_3 = (v0_2 - 1);
            this.stackSize = v2_3;
            this.pathNames[v2_3] = 0;
            String v2_4 = this.pathIndices;
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_3 = (v0_2 - 2);
            v2_4[v0_3] = (v2_4[v0_3] + 1);
            this.peeked = 0;
            return;
        }
    }

    public boolean hasNext()
    {
        int v0_0 = this.peeked;
        if (v0_0 == 0) {
            v0_0 = this.doPeek();
        }
        if ((v0_0 == 2) || ((v0_0 == 4) || (v0_0 == 18))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean nextBoolean()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        if (v0_0 != 5) {
            if (v0_0 != 6) {
                String v1_1 = new StringBuilder("Expected a boolean but was ");
                v1_1.append(this.peek());
                v1_1.append(" at path ");
                v1_1.append(this.getPath());
                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_1.toString());
            } else {
                this.peeked = 0;
                vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_3 = this.pathIndices;
                String v1_4 = (this.stackSize - 1);
                v0_3[v1_4] = (v0_3[v1_4] + 1);
                return 0;
            }
        } else {
            this.peeked = 0;
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_4 = this.pathIndices;
            String v1_6 = (this.stackSize - 1);
            v0_4[v1_6] = (v0_4[v1_6] + 1);
            return 1;
        }
    }

    public double nextDouble()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        if (v0_0 != 16) {
            if (v0_0 != 17) {
                if (v0_0 != 9) {
                    if (v0_0 != 8) {
                        if (v0_0 != 10) {
                            if (v0_0 != 11) {
                                String v1_6 = new StringBuilder("Expected a double but was ");
                                v1_6.append(this.peek());
                                v1_6.append(" at path ");
                                v1_6.append(this.getPath());
                                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_6.toString());
                            }
                        } else {
                            this.peekedString = this.nextUnquotedValue();
                        }
                    } else {
                        this.peekedString = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                    }
                } else {
                    this.peekedString = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                }
            } else {
                this.peekedString = this.buffer.readUtf8(((long) this.peekedNumberLength));
            }
            this.peeked = 11;
            try {
                vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_13 = Double.parseDouble(this.peekedString);
                StringBuilder v3_0 = this.lenient;
            } catch (NumberFormatException) {
                String v1_11 = new StringBuilder(v3_0);
                v1_11.append(this.peekedString);
                v1_11.append(" at path ");
                v1_11.append(this.getPath());
                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_11.toString());
            }
            if ((v3_0 == null) && ((Double.isNaN(v0_13)) || (Double.isInfinite(v0_13)))) {
                StringBuilder v3_4 = new StringBuilder("JSON forbids NaN and infinities: ");
                v3_4.append(v0_13);
                v3_4.append(" at path ");
                v3_4.append(this.getPath());
                throw new vn.ai.trueid.lottie.parser.moshi.JsonEncodingException(v3_4.toString());
            } else {
                this.peekedString = 0;
                this.peeked = 0;
                String v2_9 = this.pathIndices;
                StringBuilder v3_8 = (this.stackSize - 1);
                v2_9[v3_8] = (v2_9[v3_8] + 1);
                return v0_13;
            }
        } else {
            this.peeked = 0;
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_18 = this.pathIndices;
            String v1_1 = (this.stackSize - 1);
            v0_18[v1_1] = (v0_18[v1_1] + 1);
            return ((double) this.peekedLong);
        }
    }

    public int nextInt()
    {
        int v0_0 = this.peeked;
        if (v0_0 == 0) {
            v0_0 = this.doPeek();
        }
        if (v0_0 != 16) {
            try {
                if (v0_0 != 17) {
                    if ((v0_0 != 9) && (v0_0 != 8)) {
                        if (v0_0 != 11) {
                            int[] v1_7 = new StringBuilder("Expected an int but was ");
                            v1_7.append(this.peek());
                            v1_7.append(" at path ");
                            v1_7.append(this.getPath());
                            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_7.toString());
                        }
                    } else {
                        int v0_8;
                        if (v0_0 != 9) {
                            v0_8 = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                        } else {
                            v0_8 = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                        }
                        this.peekedString = v0_8;
                        int v0_10 = Integer.parseInt(v0_8);
                        this.peeked = 0;
                        int[] v1_10 = this.pathIndices;
                        int v6_3 = (this.stackSize - 1);
                        v1_10[v6_3] = (v1_10[v6_3] + 1);
                        return v0_10;
                    }
                } else {
                    this.peekedString = this.buffer.readUtf8(((long) this.peekedNumberLength));
                }
            } catch (NumberFormatException) {
            }
            this.peeked = 11;
            try {
                int v0_14 = Double.parseDouble(this.peekedString);
                int v5_2 = ((int) v0_14);
            } catch (NumberFormatException) {
                int[] v1_13 = new StringBuilder("Expected an int but was ");
                v1_13.append(this.peekedString);
                v1_13.append(" at path ");
                v1_13.append(this.getPath());
                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_13.toString());
            }
            if (((double) v5_2) != v0_14) {
                int[] v1_16 = new StringBuilder("Expected an int but was ");
                v1_16.append(this.peekedString);
                v1_16.append(" at path ");
                v1_16.append(this.getPath());
                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_16.toString());
            } else {
                this.peekedString = 0;
                this.peeked = 0;
                int v0_21 = this.pathIndices;
                int[] v1_20 = (this.stackSize - 1);
                v0_21[v1_20] = (v0_21[v1_20] + 1);
                return v5_2;
            }
        } else {
            int v0_22 = this.peekedLong;
            int v5_1 = ((int) v0_22);
            if (v0_22 != ((long) v5_1)) {
                int[] v1_1 = new StringBuilder("Expected an int but was ");
                v1_1.append(this.peekedLong);
                v1_1.append(" at path ");
                v1_1.append(this.getPath());
                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_1.toString());
            } else {
                this.peeked = 0;
                int v0_4 = this.pathIndices;
                int[] v1_5 = (this.stackSize - 1);
                v0_4[v1_5] = (v0_4[v1_5] + 1);
                return v5_1;
            }
        }
    }

    public String nextName()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_3;
        if (v0_0 != 14) {
            if (v0_0 != 13) {
                if (v0_0 != 12) {
                    if (v0_0 != 15) {
                        String v1_2 = new StringBuilder("Expected a name but was ");
                        v1_2.append(this.peek());
                        v1_2.append(" at path ");
                        v1_2.append(this.getPath());
                        throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_2.toString());
                    } else {
                        v0_3 = this.peekedString;
                    }
                } else {
                    v0_3 = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                }
            } else {
                v0_3 = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
            }
        } else {
            v0_3 = this.nextUnquotedValue();
        }
        this.peeked = 0;
        this.pathNames[(this.stackSize - 1)] = v0_3;
        return v0_3;
    }

    public String nextString()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_4;
        if (v0_0 != 10) {
            if (v0_0 != 9) {
                if (v0_0 != 8) {
                    if (v0_0 != 11) {
                        if (v0_0 != 16) {
                            if (v0_0 != 17) {
                                String v1_4 = new StringBuilder("Expected a string but was ");
                                v1_4.append(this.peek());
                                v1_4.append(" at path ");
                                v1_4.append(this.getPath());
                                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_4.toString());
                            } else {
                                v0_4 = this.buffer.readUtf8(((long) this.peekedNumberLength));
                            }
                        } else {
                            v0_4 = Long.toString(this.peekedLong);
                        }
                    } else {
                        v0_4 = this.peekedString;
                        this.peekedString = 0;
                    }
                } else {
                    v0_4 = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                }
            } else {
                v0_4 = this.nextQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
            }
        } else {
            v0_4 = this.nextUnquotedValue();
        }
        this.peeked = 0;
        String v1_11 = this.pathIndices;
        String v2_5 = (this.stackSize - 1);
        v1_11[v2_5] = (v1_11[v2_5] + 1);
        return v0_4;
    }

    public vn.ai.trueid.lottie.parser.moshi.JsonReader$Token peek()
    {
        vn.ai.trueid.lottie.parser.moshi.JsonReader$Token v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        switch (v0_0) {
            case 1:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_OBJECT;
            case 2:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_OBJECT;
            case 3:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BEGIN_ARRAY;
            case 4:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_ARRAY;
            case 5:
            case 6:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.BOOLEAN;
            case 7:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NAME;
            case 16:
            case 17:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.NUMBER;
            case 18:
                return vn.ai.trueid.lottie.parser.moshi.JsonReader$Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public int selectName(vn.ai.trueid.lottie.parser.moshi.JsonReader$Options p5)
    {
        String v0_0 = this.peeked;
        if (v0_0 == null) {
            v0_0 = this.doPeek();
        }
        if ((v0_0 >= 12) && (v0_0 <= 15)) {
            if (v0_0 != 15) {
                String v0_2 = this.source.select(p5.doubleQuoteSuffix);
                if (v0_2 == -1) {
                    String v0_4 = this.pathNames[(this.stackSize - 1)];
                    String v3_3 = this.nextName();
                    int v5_1 = this.findName(v3_3, p5);
                    if (v5_1 == -1) {
                        this.peeked = 15;
                        this.peekedString = v3_3;
                        this.pathNames[(this.stackSize - 1)] = v0_4;
                    }
                    return v5_1;
                } else {
                    this.peeked = 0;
                    this.pathNames[(this.stackSize - 1)] = p5.strings[v0_2];
                    return v0_2;
                }
            } else {
                return this.findName(this.peekedString, p5);
            }
        } else {
            return -1;
        }
    }

    public void skipName()
    {
        if (this.failOnUnknown) {
            String v1_9 = new StringBuilder("Cannot skip unexpected ");
            v1_9.append(this.peek());
            v1_9.append(" at ");
            v1_9.append(this.getPath());
            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_9.toString());
        } else {
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_2 = this.peeked;
            if (v0_2 == null) {
                v0_2 = this.doPeek();
            }
            if (v0_2 != 14) {
                if (v0_2 != 13) {
                    if (v0_2 != 12) {
                        if (v0_2 != 15) {
                            String v1_7 = new StringBuilder("Expected a name but was ");
                            v1_7.append(this.peek());
                            v1_7.append(" at path ");
                            v1_7.append(this.getPath());
                            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_7.toString());
                        }
                    } else {
                        this.skipQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                    }
                } else {
                    this.skipQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                }
            } else {
                this.skipUnquotedValue();
            }
            this.peeked = 0;
            this.pathNames[(this.stackSize - 1)] = "null";
            return;
        }
    }

    public void skipValue()
    {
        if (this.failOnUnknown) {
            String v1_6 = new StringBuilder("Cannot skip unexpected ");
            v1_6.append(this.peek());
            v1_6.append(" at ");
            v1_6.append(this.getPath());
            throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_6.toString());
        } else {
            String v1_1 = 0;
            do {
                String v2_6 = this.peeked;
                if (v2_6 == null) {
                    v2_6 = this.doPeek();
                }
                if (v2_6 != 3) {
                    if (v2_6 != 1) {
                        if (v2_6 != 4) {
                            if (v2_6 != 2) {
                                if ((v2_6 != 14) && (v2_6 != 10)) {
                                    if ((v2_6 != 9) && (v2_6 != 13)) {
                                        if ((v2_6 != 8) && (v2_6 != 12)) {
                                            if (v2_6 != 17) {
                                                if (v2_6 == 18) {
                                                    String v1_8 = new StringBuilder("Expected a value but was ");
                                                    v1_8.append(this.peek());
                                                    v1_8.append(" at path ");
                                                    v1_8.append(this.getPath());
                                                    throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_8.toString());
                                                }
                                            } else {
                                                this.buffer.skip(((long) this.peekedNumberLength));
                                            }
                                        } else {
                                            this.skipQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                                        }
                                    } else {
                                        this.skipQuotedValue(vn.ai.trueid.lottie.parser.moshi.JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                                    }
                                } else {
                                    this.skipUnquotedValue();
                                }
                            } else {
                                v1_1--;
                                if (v1_1 < null) {
                                    String v1_11 = new StringBuilder("Expected a value but was ");
                                    v1_11.append(this.peek());
                                    v1_11.append(" at path ");
                                    v1_11.append(this.getPath());
                                    throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_11.toString());
                                } else {
                                    this.stackSize = (this.stackSize - 1);
                                }
                            }
                        } else {
                            v1_1--;
                            if (v1_1 < null) {
                                String v1_14 = new StringBuilder("Expected a value but was ");
                                v1_14.append(this.peek());
                                v1_14.append(" at path ");
                                v1_14.append(this.getPath());
                                throw new vn.ai.trueid.lottie.parser.moshi.JsonDataException(v1_14.toString());
                            } else {
                                this.stackSize = (this.stackSize - 1);
                            }
                        }
                    } else {
                        this.pushScope(3);
                        v1_1++;
                    }
                } else {
                    this.pushScope(1);
                }
                this.peeked = 0;
            } while(v1_1 != null);
            vn.ai.trueid.lottie.parser.moshi.JsonDataException v0_2 = this.pathIndices;
            String v1_2 = this.stackSize;
            String v2_3 = (v1_2 - 1);
            v0_2[v2_3] = (v0_2[v2_3] + 1);
            this.pathNames[(v1_2 - 1)] = "null";
            return;
        }
    }

    public String toString()
    {
        String v0_1 = new StringBuilder("JsonReader(");
        v0_1.append(this.source);
        v0_1.append(")");
        return v0_1.toString();
    }
}

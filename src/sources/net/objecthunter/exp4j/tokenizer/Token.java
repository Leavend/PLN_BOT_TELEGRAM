package net.objecthunter.exp4j.tokenizer;

/* loaded from: classes3.dex */
public abstract class Token {
    public static final short TOKEN_FUNCTION = 3;
    public static final short TOKEN_NUMBER = 1;
    public static final short TOKEN_OPERATOR = 2;
    public static final short TOKEN_PARENTHESES_CLOSE = 5;
    public static final short TOKEN_PARENTHESES_OPEN = 4;
    public static final short TOKEN_SEPARATOR = 7;
    public static final short TOKEN_VARIABLE = 6;
    protected final int type;

    Token(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}

package net.objecthunter.exp4j.tokenizer;

/* loaded from: classes3.dex */
public class UnknownFunctionOrVariableException extends IllegalArgumentException {
    private static final long serialVersionUID = 1;
    private final String expression;
    private final String message;
    private final int position;
    private final String token;

    public UnknownFunctionOrVariableException(String str, int i, int i2) {
        this.expression = str;
        String str2 = token(str, i, i2);
        this.token = str2;
        this.position = i;
        this.message = "Unknown function or variable '" + str2 + "' at pos " + i + " in expression '" + str + "'";
    }

    private static String token(String str, int i, int i2) {
        int length = str.length();
        int i3 = (i2 + i) - 1;
        if (length >= i3) {
            length = i3;
        }
        return str.substring(i, length);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public String getExpression() {
        return this.expression;
    }

    public String getToken() {
        return this.token;
    }

    public int getPosition() {
        return this.position;
    }
}

package net.objecthunter.exp4j.tokenizer;

/* loaded from: classes3.dex */
public final class NumberToken extends Token {
    private final double value;

    public NumberToken(double d) {
        super(1);
        this.value = d;
    }

    NumberToken(char[] cArr, int i, int i2) {
        this(Double.parseDouble(String.valueOf(cArr, i, i2)));
    }

    public double getValue() {
        return this.value;
    }
}

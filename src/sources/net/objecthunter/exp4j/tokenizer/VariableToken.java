package net.objecthunter.exp4j.tokenizer;

/* loaded from: classes3.dex */
public class VariableToken extends Token {
    private final String name;

    public String getName() {
        return this.name;
    }

    public VariableToken(String str) {
        super(6);
        this.name = str;
    }
}

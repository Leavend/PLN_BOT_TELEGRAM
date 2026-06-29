package net.objecthunter.exp4j.operator;

import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;

/* loaded from: classes3.dex */
public abstract class Operator {
    public static final char[] ALLOWED_OPERATOR_CHARS = {'+', Soundex.SILENT_MARKER, '*', '/', '%', '^', '!', '#', Typography.section, Typography.dollar, Typography.amp, ';', ':', '~', Typography.less, Typography.greater, '|', '='};
    public static final int PRECEDENCE_ADDITION = 500;
    public static final int PRECEDENCE_DIVISION = 1000;
    public static final int PRECEDENCE_MODULO = 1000;
    public static final int PRECEDENCE_MULTIPLICATION = 1000;
    public static final int PRECEDENCE_POWER = 10000;
    public static final int PRECEDENCE_SUBTRACTION = 500;
    public static final int PRECEDENCE_UNARY_MINUS = 5000;
    public static final int PRECEDENCE_UNARY_PLUS = 5000;
    protected final boolean leftAssociative;
    protected final int numOperands;
    protected final int precedence;
    protected final String symbol;

    public abstract double apply(double... dArr);

    public Operator(String str, int i, boolean z, int i2) {
        this.numOperands = i;
        this.leftAssociative = z;
        this.symbol = str;
        this.precedence = i2;
    }

    public static boolean isAllowedOperatorChar(char c) {
        for (char c2 : ALLOWED_OPERATOR_CHARS) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    public boolean isLeftAssociative() {
        return this.leftAssociative;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public int getNumOperands() {
        return this.numOperands;
    }
}

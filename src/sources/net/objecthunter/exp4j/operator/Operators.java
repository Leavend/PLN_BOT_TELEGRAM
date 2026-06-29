package net.objecthunter.exp4j.operator;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes3.dex */
public abstract class Operators {
    private static final int INDEX_ADDITION = 0;
    private static final int INDEX_DIVISION = 3;
    private static final int INDEX_MODULO = 5;
    private static final int INDEX_MUTLIPLICATION = 2;
    private static final int INDEX_POWER = 4;
    private static final int INDEX_SUBTRACTION = 1;
    private static final int INDEX_UNARYMINUS = 6;
    private static final int INDEX_UNARYPLUS = 7;
    private static final Operator[] builtinOperators;

    static {
        builtinOperators = new Operator[]{new Operator(str, i, true, i) { // from class: net.objecthunter.exp4j.operator.Operators.1
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                return dArr[0] + dArr[1];
            }
        }, new Operator(str, i, 1 == true ? 1 : 0, i) { // from class: net.objecthunter.exp4j.operator.Operators.2
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                return dArr[0] - dArr[1];
            }
        }, new Operator("*", i, 1 == true ? 1 : 0, i) { // from class: net.objecthunter.exp4j.operator.Operators.5
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                return dArr[0] * dArr[1];
            }
        }, new Operator(InternalZipConstants.ZIP_FILE_SEPARATOR, i, 1 == true ? 1 : 0, i) { // from class: net.objecthunter.exp4j.operator.Operators.6
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                double d = dArr[1];
                if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    throw new ArithmeticException("Division by zero!");
                }
                return dArr[0] / d;
            }
        }, new Operator("^", i, z, Operator.PRECEDENCE_POWER) { // from class: net.objecthunter.exp4j.operator.Operators.7
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                return Math.pow(dArr[0], dArr[1]);
            }
        }, new Operator("%", i, 1 == true ? 1 : 0, i) { // from class: net.objecthunter.exp4j.operator.Operators.8
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                double d = dArr[1];
                if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    throw new ArithmeticException("Division by zero!");
                }
                return dArr[0] % d;
            }
        }, new Operator(str, 1 == true ? 1 : 0, z, i) { // from class: net.objecthunter.exp4j.operator.Operators.3
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                return -dArr[0];
            }
        }, new Operator(str, 1 == true ? 1 : 0, z, i) { // from class: net.objecthunter.exp4j.operator.Operators.4
            @Override // net.objecthunter.exp4j.operator.Operator
            public double apply(double... dArr) {
                return dArr[0];
            }
        }};
        String str = "+";
        int i = 2;
        int i2 = 500;
        boolean z = false;
        String str2 = "-";
        int i3 = 5000;
        int i4 = 1000;
    }

    public static Operator getBuiltinOperator(char c, int i) {
        if (c == '%') {
            return builtinOperators[5];
        }
        if (c == '-') {
            if (i != 1) {
                return builtinOperators[1];
            }
            return builtinOperators[6];
        }
        if (c == '/') {
            return builtinOperators[3];
        }
        if (c == '^') {
            return builtinOperators[4];
        }
        if (c == '*') {
            return builtinOperators[2];
        }
        if (c != '+') {
            return null;
        }
        if (i != 1) {
            return builtinOperators[0];
        }
        return builtinOperators[7];
    }
}

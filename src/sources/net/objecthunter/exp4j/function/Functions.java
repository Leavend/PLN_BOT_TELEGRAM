package net.objecthunter.exp4j.function;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes3.dex */
public class Functions {
    private static final int INDEX_ABS = 6;
    private static final int INDEX_ACOS = 7;
    private static final int INDEX_ASIN = 8;
    private static final int INDEX_ATAN = 9;
    private static final int INDEX_CBRT = 10;
    private static final int INDEX_CEIL = 11;
    private static final int INDEX_COS = 1;
    private static final int INDEX_COSH = 16;
    private static final int INDEX_COT = 3;
    private static final int INDEX_EXP = 18;
    private static final int INDEX_EXPM1 = 19;
    private static final int INDEX_FLOOR = 12;
    private static final int INDEX_LOG = 4;
    private static final int INDEX_LOG10 = 20;
    private static final int INDEX_LOG1P = 5;
    private static final int INDEX_LOG2 = 21;
    private static final int INDEX_POW = 17;
    private static final int INDEX_SGN = 22;
    private static final int INDEX_SIN = 0;
    private static final int INDEX_SINH = 13;
    private static final int INDEX_SQRT = 14;
    private static final int INDEX_TAN = 2;
    private static final int INDEX_TANH = 15;
    private static final Function[] builtinFunctions;

    static {
        builtinFunctions = new Function[]{new Function("sin") { // from class: net.objecthunter.exp4j.function.Functions.1
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.sin(dArr[0]);
            }
        }, function, function, new Function("cot") { // from class: net.objecthunter.exp4j.function.Functions.4
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                if (Math.tan(dArr[0]) == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    throw new ArithmeticException("Division by zero in cotangent!");
                }
                return 1.0d / Math.tan(dArr[0]);
            }
        }, new Function("log") { // from class: net.objecthunter.exp4j.function.Functions.5
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.log(dArr[0]);
            }
        }, new Function("log1p") { // from class: net.objecthunter.exp4j.function.Functions.8
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.log1p(dArr[0]);
            }
        }, new Function("abs") { // from class: net.objecthunter.exp4j.function.Functions.9
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.abs(dArr[0]);
            }
        }, new Function("acos") { // from class: net.objecthunter.exp4j.function.Functions.10
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.acos(dArr[0]);
            }
        }, new Function("asin") { // from class: net.objecthunter.exp4j.function.Functions.11
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.asin(dArr[0]);
            }
        }, new Function("atan") { // from class: net.objecthunter.exp4j.function.Functions.12
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.atan(dArr[0]);
            }
        }, new Function("cbrt") { // from class: net.objecthunter.exp4j.function.Functions.13
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.cbrt(dArr[0]);
            }
        }, new Function("ceil") { // from class: net.objecthunter.exp4j.function.Functions.19
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.ceil(dArr[0]);
            }
        }, new Function("floor") { // from class: net.objecthunter.exp4j.function.Functions.14
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.floor(dArr[0]);
            }
        }, new Function("sinh") { // from class: net.objecthunter.exp4j.function.Functions.15
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.sinh(dArr[0]);
            }
        }, new Function("sqrt") { // from class: net.objecthunter.exp4j.function.Functions.16
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.sqrt(dArr[0]);
            }
        }, new Function("tanh") { // from class: net.objecthunter.exp4j.function.Functions.17
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.tanh(dArr[0]);
            }
        }, new Function("cosh") { // from class: net.objecthunter.exp4j.function.Functions.18
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.cosh(dArr[0]);
            }
        }, new Function(str, 2) { // from class: net.objecthunter.exp4j.function.Functions.20
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.pow(dArr[0], dArr[1]);
            }
        }, new Function("exp", i) { // from class: net.objecthunter.exp4j.function.Functions.21
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.exp(dArr[0]);
            }
        }, new Function("expm1", i) { // from class: net.objecthunter.exp4j.function.Functions.22
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.expm1(dArr[0]);
            }
        }, new Function("log10") { // from class: net.objecthunter.exp4j.function.Functions.7
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.log10(dArr[0]);
            }
        }, new Function("log2") { // from class: net.objecthunter.exp4j.function.Functions.6
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.log(dArr[0]) / Math.log(2.0d);
            }
        }, new Function("signum", i) { // from class: net.objecthunter.exp4j.function.Functions.23
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                double d = dArr[0];
                if (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return 1.0d;
                }
                if (d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return -1.0d;
                }
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }
        }};
        Function function = new Function("cos") { // from class: net.objecthunter.exp4j.function.Functions.2
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.cos(dArr[0]);
            }
        };
        int i = 1;
        Function function2 = new Function("tan") { // from class: net.objecthunter.exp4j.function.Functions.3
            @Override // net.objecthunter.exp4j.function.Function
            public double apply(double... dArr) {
                return Math.tan(dArr[0]);
            }
        };
        String str = "pow";
    }

    public static Function getBuiltinFunction(String str) {
        if (str.equals("sin")) {
            return builtinFunctions[0];
        }
        if (str.equals("cos")) {
            return builtinFunctions[1];
        }
        if (str.equals("tan")) {
            return builtinFunctions[2];
        }
        if (str.equals("cot")) {
            return builtinFunctions[3];
        }
        if (str.equals("asin")) {
            return builtinFunctions[8];
        }
        if (str.equals("acos")) {
            return builtinFunctions[7];
        }
        if (str.equals("atan")) {
            return builtinFunctions[9];
        }
        if (str.equals("sinh")) {
            return builtinFunctions[13];
        }
        if (str.equals("cosh")) {
            return builtinFunctions[16];
        }
        if (str.equals("tanh")) {
            return builtinFunctions[15];
        }
        if (str.equals("abs")) {
            return builtinFunctions[6];
        }
        if (str.equals("log")) {
            return builtinFunctions[4];
        }
        if (str.equals("log10")) {
            return builtinFunctions[20];
        }
        if (str.equals("log2")) {
            return builtinFunctions[21];
        }
        if (str.equals("log1p")) {
            return builtinFunctions[5];
        }
        if (str.equals("ceil")) {
            return builtinFunctions[11];
        }
        if (str.equals("floor")) {
            return builtinFunctions[12];
        }
        if (str.equals("sqrt")) {
            return builtinFunctions[14];
        }
        if (str.equals("cbrt")) {
            return builtinFunctions[10];
        }
        if (str.equals("pow")) {
            return builtinFunctions[17];
        }
        if (str.equals("exp")) {
            return builtinFunctions[18];
        }
        if (str.equals("expm1")) {
            return builtinFunctions[19];
        }
        if (str.equals("signum")) {
            return builtinFunctions[22];
        }
        return null;
    }
}

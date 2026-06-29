package net.objecthunter.exp4j.function;

/* loaded from: classes3.dex */
public abstract class Function {
    protected final String name;
    protected final int numArguments;

    public abstract double apply(double... dArr);

    public Function(String str, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The number of function arguments can not be less than 0 for '" + str + "'");
        }
        if (!isValidFunctionName(str)) {
            throw new IllegalArgumentException("The function name '" + str + "' is invalid");
        }
        this.name = str;
        this.numArguments = i;
    }

    public Function(String str) {
        this(str, 1);
    }

    public String getName() {
        return this.name;
    }

    public int getNumArguments() {
        return this.numArguments;
    }

    public static char[] getAllowedFunctionCharacters() {
        char[] cArr = new char[53];
        int i = 0;
        int i2 = 65;
        while (i2 < 91) {
            cArr[i] = (char) i2;
            i2++;
            i++;
        }
        int i3 = 97;
        while (i3 < 123) {
            cArr[i] = (char) i3;
            i3++;
            i++;
        }
        cArr[i] = '_';
        return cArr;
    }

    public static boolean isValidFunctionName(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (!Character.isLetter(cCharAt) && cCharAt != '_' && (!Character.isDigit(cCharAt) || i <= 0)) {
                return false;
            }
        }
        return true;
    }
}

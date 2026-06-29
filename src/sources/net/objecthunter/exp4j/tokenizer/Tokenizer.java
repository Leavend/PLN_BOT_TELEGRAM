package net.objecthunter.exp4j.tokenizer;

import java.util.Map;
import java.util.Set;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.operator.Operators;

/* loaded from: classes3.dex */
public class Tokenizer {
    private final char[] expression;
    private final int expressionLength;
    private final boolean implicitMultiplication;
    private Token lastToken;
    private int pos = 0;
    private final Map<String, Function> userFunctions;
    private final Map<String, Operator> userOperators;
    private final Set<String> variableNames;

    private boolean isArgumentSeparator(char c) {
        return c == ',';
    }

    private boolean isCloseParentheses(char c) {
        return c == ')' || c == '}' || c == ']';
    }

    private boolean isOpenParentheses(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    public Tokenizer(String str, Map<String, Function> map, Map<String, Operator> map2, Set<String> set, boolean z) {
        char[] charArray = str.trim().toCharArray();
        this.expression = charArray;
        this.expressionLength = charArray.length;
        this.userFunctions = map;
        this.userOperators = map2;
        this.variableNames = set;
        this.implicitMultiplication = z;
    }

    public Tokenizer(String str, Map<String, Function> map, Map<String, Operator> map2, Set<String> set) {
        char[] charArray = str.trim().toCharArray();
        this.expression = charArray;
        this.expressionLength = charArray.length;
        this.userFunctions = map;
        this.userOperators = map2;
        this.variableNames = set;
        this.implicitMultiplication = true;
    }

    public boolean hasNext() {
        return this.expression.length > this.pos;
    }

    public Token nextToken() {
        char c = this.expression[this.pos];
        while (Character.isWhitespace(c)) {
            char[] cArr = this.expression;
            int i = this.pos + 1;
            this.pos = i;
            c = cArr[i];
        }
        if (Character.isDigit(c) || c == '.') {
            Token token = this.lastToken;
            if (token != null) {
                if (token.getType() == 1) {
                    throw new IllegalArgumentException("Unable to parse char '" + c + "' (Code:" + ((int) c) + ") at [" + this.pos + "]");
                }
                if (this.implicitMultiplication && this.lastToken.getType() != 2 && this.lastToken.getType() != 4 && this.lastToken.getType() != 3 && this.lastToken.getType() != 7) {
                    OperatorToken operatorToken = new OperatorToken(Operators.getBuiltinOperator('*', 2));
                    this.lastToken = operatorToken;
                    return operatorToken;
                }
            }
            return parseNumberToken(c);
        }
        if (isArgumentSeparator(c)) {
            return parseArgumentSeparatorToken(c);
        }
        if (isOpenParentheses(c)) {
            Token token2 = this.lastToken;
            if (token2 != null && this.implicitMultiplication && token2.getType() != 2 && this.lastToken.getType() != 4 && this.lastToken.getType() != 3 && this.lastToken.getType() != 7) {
                OperatorToken operatorToken2 = new OperatorToken(Operators.getBuiltinOperator('*', 2));
                this.lastToken = operatorToken2;
                return operatorToken2;
            }
            return parseParentheses(true);
        }
        if (isCloseParentheses(c)) {
            return parseParentheses(false);
        }
        if (Operator.isAllowedOperatorChar(c)) {
            return parseOperatorToken(c);
        }
        if (isAlphabetic(c) || c == '_') {
            Token token3 = this.lastToken;
            if (token3 != null && this.implicitMultiplication && token3.getType() != 2 && this.lastToken.getType() != 4 && this.lastToken.getType() != 3 && this.lastToken.getType() != 7) {
                OperatorToken operatorToken3 = new OperatorToken(Operators.getBuiltinOperator('*', 2));
                this.lastToken = operatorToken3;
                return operatorToken3;
            }
            return parseFunctionOrVariable();
        }
        throw new IllegalArgumentException("Unable to parse char '" + c + "' (Code:" + ((int) c) + ") at [" + this.pos + "]");
    }

    private Token parseArgumentSeparatorToken(char c) {
        this.pos++;
        ArgumentSeparatorToken argumentSeparatorToken = new ArgumentSeparatorToken();
        this.lastToken = argumentSeparatorToken;
        return argumentSeparatorToken;
    }

    private Token parseParentheses(boolean z) {
        if (z) {
            this.lastToken = new OpenParenthesesToken();
        } else {
            this.lastToken = new CloseParenthesesToken();
        }
        this.pos++;
        return this.lastToken;
    }

    private Token parseFunctionOrVariable() {
        int i = this.pos;
        if (isEndOfExpression(i)) {
            this.pos++;
        }
        int i2 = (i + 1) - 1;
        Token functionToken = null;
        int i3 = 1;
        int i4 = 1;
        while (!isEndOfExpression(i2) && isVariableOrFunctionCharacter(this.expression[i2])) {
            String str = new String(this.expression, i, i4);
            Set<String> set = this.variableNames;
            if (set != null && set.contains(str)) {
                functionToken = new VariableToken(str);
            } else {
                Function function = getFunction(str);
                if (function != null) {
                    functionToken = new FunctionToken(function);
                } else {
                    i4++;
                    i2 = (i + i4) - 1;
                }
            }
            i3 = i4;
            i4++;
            i2 = (i + i4) - 1;
        }
        if (functionToken == null) {
            throw new UnknownFunctionOrVariableException(new String(this.expression), this.pos, i4);
        }
        this.pos += i3;
        this.lastToken = functionToken;
        return functionToken;
    }

    private Function getFunction(String str) {
        Map<String, Function> map = this.userFunctions;
        Function function = map != null ? map.get(str) : null;
        return function == null ? Functions.getBuiltinFunction(str) : function;
    }

    private Token parseOperatorToken(char c) {
        Operator operator;
        int i = this.pos;
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        int i2 = 1;
        while (true) {
            int i3 = i + i2;
            if (isEndOfExpression(i3) || !Operator.isAllowedOperatorChar(this.expression[i3])) {
                break;
            }
            i2++;
            sb.append(this.expression[i3]);
        }
        while (true) {
            if (sb.length() <= 0) {
                operator = null;
                break;
            }
            operator = getOperator(sb.toString());
            if (operator != null) {
                break;
            }
            sb.setLength(sb.length() - 1);
        }
        this.pos += sb.length();
        OperatorToken operatorToken = new OperatorToken(operator);
        this.lastToken = operatorToken;
        return operatorToken;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private net.objecthunter.exp4j.operator.Operator getOperator(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, net.objecthunter.exp4j.operator.Operator> r0 = r4.userOperators
            if (r0 == 0) goto Lb
            java.lang.Object r0 = r0.get(r5)
            net.objecthunter.exp4j.operator.Operator r0 = (net.objecthunter.exp4j.operator.Operator) r0
            goto Lc
        Lb:
            r0 = 0
        Lc:
            if (r0 != 0) goto L4d
            int r1 = r5.length()
            r2 = 1
            if (r1 != r2) goto L4d
            net.objecthunter.exp4j.tokenizer.Token r0 = r4.lastToken
            if (r0 != 0) goto L1a
            goto L44
        L1a:
            int r0 = r0.getType()
            r1 = 4
            if (r0 == r1) goto L44
            r1 = 7
            if (r0 != r1) goto L25
            goto L44
        L25:
            r1 = 2
            if (r0 != r1) goto L43
            net.objecthunter.exp4j.tokenizer.Token r0 = r4.lastToken
            net.objecthunter.exp4j.tokenizer.OperatorToken r0 = (net.objecthunter.exp4j.tokenizer.OperatorToken) r0
            net.objecthunter.exp4j.operator.Operator r0 = r0.getOperator()
            int r3 = r0.getNumOperands()
            if (r3 == r1) goto L44
            int r3 = r0.getNumOperands()
            if (r3 != r2) goto L43
            boolean r0 = r0.isLeftAssociative()
            if (r0 != 0) goto L43
            goto L44
        L43:
            r2 = r1
        L44:
            r0 = 0
            char r5 = r5.charAt(r0)
            net.objecthunter.exp4j.operator.Operator r0 = net.objecthunter.exp4j.operator.Operators.getBuiltinOperator(r5, r2)
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.objecthunter.exp4j.tokenizer.Tokenizer.getOperator(java.lang.String):net.objecthunter.exp4j.operator.Operator");
    }

    private Token parseNumberToken(char c) {
        int i;
        int i2 = this.pos;
        this.pos = i2 + 1;
        if (isEndOfExpression(i2 + 1)) {
            NumberToken numberToken = new NumberToken(Double.parseDouble(String.valueOf(c)));
            this.lastToken = numberToken;
            return numberToken;
        }
        int i3 = 1;
        while (true) {
            i = i2 + i3;
            if (!isEndOfExpression(i)) {
                char[] cArr = this.expression;
                char c2 = cArr[i];
                char c3 = cArr[i - 1];
                if (!isNumeric(c2, c3 == 'e' || c3 == 'E')) {
                    break;
                }
                i3++;
                this.pos++;
            } else {
                break;
            }
        }
        char c4 = this.expression[i - 1];
        if (c4 == 'e' || c4 == 'E') {
            i3--;
            this.pos--;
        }
        NumberToken numberToken2 = new NumberToken(this.expression, i2, i3);
        this.lastToken = numberToken2;
        return numberToken2;
    }

    private static boolean isNumeric(char c, boolean z) {
        return Character.isDigit(c) || c == '.' || c == 'e' || c == 'E' || (z && (c == '-' || c == '+'));
    }

    public static boolean isAlphabetic(int i) {
        return Character.isLetter(i);
    }

    public static boolean isVariableOrFunctionCharacter(int i) {
        return isAlphabetic(i) || Character.isDigit(i) || i == 95 || i == 46;
    }

    private boolean isEndOfExpression(int i) {
        return this.expressionLength <= i;
    }
}

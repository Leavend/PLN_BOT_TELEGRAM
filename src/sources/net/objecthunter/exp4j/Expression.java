package net.objecthunter.exp4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.tokenizer.FunctionToken;
import net.objecthunter.exp4j.tokenizer.NumberToken;
import net.objecthunter.exp4j.tokenizer.OperatorToken;
import net.objecthunter.exp4j.tokenizer.Token;
import net.objecthunter.exp4j.tokenizer.VariableToken;

/* loaded from: classes3.dex */
public class Expression {
    private final Token[] tokens;
    private final Set<String> userFunctionNames;
    private final Map<String, Double> variables;

    private static Map<String, Double> createDefaultVariables() {
        HashMap map = new HashMap(4);
        Double dValueOf = Double.valueOf(3.141592653589793d);
        map.put("pi", dValueOf);
        map.put("π", dValueOf);
        map.put("φ", Double.valueOf(1.61803398874d));
        map.put("e", Double.valueOf(2.718281828459045d));
        return map;
    }

    public Expression(Expression expression) {
        Token[] tokenArr = expression.tokens;
        this.tokens = (Token[]) Arrays.copyOf(tokenArr, tokenArr.length);
        HashMap map = new HashMap();
        this.variables = map;
        map.putAll(expression.variables);
        this.userFunctionNames = new HashSet(expression.userFunctionNames);
    }

    Expression(Token[] tokenArr) {
        this.tokens = tokenArr;
        this.variables = createDefaultVariables();
        this.userFunctionNames = Collections.emptySet();
    }

    Expression(Token[] tokenArr, Set<String> set) {
        this.tokens = tokenArr;
        this.variables = createDefaultVariables();
        this.userFunctionNames = set;
    }

    public Expression setVariable(String str, double d) {
        checkVariableName(str);
        this.variables.put(str, Double.valueOf(d));
        return this;
    }

    private void checkVariableName(String str) {
        if (this.userFunctionNames.contains(str) || Functions.getBuiltinFunction(str) != null) {
            throw new IllegalArgumentException("The variable name '" + str + "' is invalid. Since there exists a function with the same name");
        }
    }

    public Expression setVariables(Map<String, Double> map) {
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            setVariable(entry.getKey(), entry.getValue().doubleValue());
        }
        return this;
    }

    public Set<String> getVariableNames() {
        HashSet hashSet = new HashSet();
        for (Token token : this.tokens) {
            if (token.getType() == 6) {
                hashSet.add(((VariableToken) token).getName());
            }
        }
        return hashSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public net.objecthunter.exp4j.ValidationResult validate(boolean r12) {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 0
            r0.<init>(r1)
            r2 = 6
            if (r12 == 0) goto L40
            net.objecthunter.exp4j.tokenizer.Token[] r12 = r11.tokens
            int r3 = r12.length
            r4 = r1
        Ld:
            if (r4 >= r3) goto L40
            r5 = r12[r4]
            int r6 = r5.getType()
            if (r6 != r2) goto L3d
            net.objecthunter.exp4j.tokenizer.VariableToken r5 = (net.objecthunter.exp4j.tokenizer.VariableToken) r5
            java.lang.String r5 = r5.getName()
            java.util.Map<java.lang.String, java.lang.Double> r6 = r11.variables
            boolean r6 = r6.containsKey(r5)
            if (r6 != 0) goto L3d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "The setVariable '"
            r6.<init>(r7)
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r6 = "' has not been set"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r0.add(r5)
        L3d:
            int r4 = r4 + 1
            goto Ld
        L40:
            net.objecthunter.exp4j.tokenizer.Token[] r12 = r11.tokens
            int r3 = r12.length
            r4 = r1
            r5 = r4
        L45:
            r6 = 1
            if (r4 >= r3) goto Lab
            r7 = r12[r4]
            int r8 = r7.getType()
            if (r8 == r6) goto L99
            r9 = 2
            if (r8 == r9) goto L8a
            r9 = 3
            if (r8 == r9) goto L59
            if (r8 == r2) goto L99
            goto L9b
        L59:
            net.objecthunter.exp4j.tokenizer.FunctionToken r7 = (net.objecthunter.exp4j.tokenizer.FunctionToken) r7
            net.objecthunter.exp4j.function.Function r7 = r7.getFunction()
            int r8 = r7.getNumArguments()
            if (r8 <= r5) goto L81
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Not enough arguments for '"
            r9.<init>(r10)
            java.lang.String r7 = r7.getName()
            java.lang.StringBuilder r7 = r9.append(r7)
            java.lang.String r9 = "'"
            java.lang.StringBuilder r7 = r7.append(r9)
            java.lang.String r7 = r7.toString()
            r0.add(r7)
        L81:
            if (r8 <= r6) goto L87
            int r8 = r8 + (-1)
            int r5 = r5 - r8
            goto L9b
        L87:
            if (r8 != 0) goto L9b
            goto L99
        L8a:
            net.objecthunter.exp4j.tokenizer.OperatorToken r7 = (net.objecthunter.exp4j.tokenizer.OperatorToken) r7
            net.objecthunter.exp4j.operator.Operator r7 = r7.getOperator()
            int r7 = r7.getNumOperands()
            if (r7 != r9) goto L9b
            int r5 = r5 + (-1)
            goto L9b
        L99:
            int r5 = r5 + 1
        L9b:
            if (r5 >= r6) goto La8
            java.lang.String r12 = "Too many operators"
            r0.add(r12)
            net.objecthunter.exp4j.ValidationResult r12 = new net.objecthunter.exp4j.ValidationResult
            r12.<init>(r1, r0)
            return r12
        La8:
            int r4 = r4 + 1
            goto L45
        Lab:
            if (r5 <= r6) goto Lb2
            java.lang.String r12 = "Too many operands"
            r0.add(r12)
        Lb2:
            int r12 = r0.size()
            if (r12 != 0) goto Lbb
            net.objecthunter.exp4j.ValidationResult r12 = net.objecthunter.exp4j.ValidationResult.SUCCESS
            goto Lc0
        Lbb:
            net.objecthunter.exp4j.ValidationResult r12 = new net.objecthunter.exp4j.ValidationResult
            r12.<init>(r1, r0)
        Lc0:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: net.objecthunter.exp4j.Expression.validate(boolean):net.objecthunter.exp4j.ValidationResult");
    }

    public ValidationResult validate() {
        return validate(true);
    }

    public Future<Double> evaluateAsync(ExecutorService executorService) {
        return executorService.submit(new Callable<Double>() { // from class: net.objecthunter.exp4j.Expression.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Double call() throws Exception {
                return Double.valueOf(Expression.this.evaluate());
            }
        });
    }

    public double evaluate() {
        ArrayStack arrayStack = new ArrayStack();
        int i = 0;
        while (true) {
            Token[] tokenArr = this.tokens;
            if (i < tokenArr.length) {
                Token token = tokenArr[i];
                if (token.getType() == 1) {
                    arrayStack.push(((NumberToken) token).getValue());
                } else if (token.getType() == 6) {
                    String name = ((VariableToken) token).getName();
                    Double d = this.variables.get(name);
                    if (d == null) {
                        throw new IllegalArgumentException("No value has been set for the setVariable '" + name + "'.");
                    }
                    arrayStack.push(d.doubleValue());
                } else if (token.getType() == 2) {
                    OperatorToken operatorToken = (OperatorToken) token;
                    if (arrayStack.size() < operatorToken.getOperator().getNumOperands()) {
                        throw new IllegalArgumentException("Invalid number of operands available for '" + operatorToken.getOperator().getSymbol() + "' operator");
                    }
                    if (operatorToken.getOperator().getNumOperands() == 2) {
                        arrayStack.push(operatorToken.getOperator().apply(arrayStack.pop(), arrayStack.pop()));
                    } else if (operatorToken.getOperator().getNumOperands() == 1) {
                        arrayStack.push(operatorToken.getOperator().apply(arrayStack.pop()));
                    }
                } else if (token.getType() == 3) {
                    FunctionToken functionToken = (FunctionToken) token;
                    int numArguments = functionToken.getFunction().getNumArguments();
                    if (arrayStack.size() < numArguments) {
                        throw new IllegalArgumentException("Invalid number of arguments available for '" + functionToken.getFunction().getName() + "' function");
                    }
                    double[] dArr = new double[numArguments];
                    for (int i2 = numArguments - 1; i2 >= 0; i2--) {
                        dArr[i2] = arrayStack.pop();
                    }
                    arrayStack.push(functionToken.getFunction().apply(dArr));
                } else {
                    continue;
                }
                i++;
            } else {
                if (arrayStack.size() > 1) {
                    throw new IllegalArgumentException("Invalid number of items on the output queue. Might be caused by an invalid number of arguments for a function.");
                }
                return arrayStack.pop();
            }
        }
    }
}

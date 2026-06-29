package net.objecthunter.exp4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.shuntingyard.ShuntingYard;

/* loaded from: classes3.dex */
public class ExpressionBuilder {
    private final String expression;
    private boolean implicitMultiplication = true;
    private final Map<String, Function> userFunctions;
    private final Map<String, Operator> userOperators;
    private final Set<String> variableNames;

    public ExpressionBuilder(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Expression can not be empty");
        }
        this.expression = str;
        this.userOperators = new HashMap(4);
        this.userFunctions = new HashMap(4);
        this.variableNames = new HashSet(4);
    }

    public ExpressionBuilder function(Function function) {
        this.userFunctions.put(function.getName(), function);
        return this;
    }

    public ExpressionBuilder functions(Function... functionArr) {
        for (Function function : functionArr) {
            this.userFunctions.put(function.getName(), function);
        }
        return this;
    }

    public ExpressionBuilder functions(List<Function> list) {
        for (Function function : list) {
            this.userFunctions.put(function.getName(), function);
        }
        return this;
    }

    public ExpressionBuilder variables(Set<String> set) {
        this.variableNames.addAll(set);
        return this;
    }

    public ExpressionBuilder variables(String... strArr) {
        Collections.addAll(this.variableNames, strArr);
        return this;
    }

    public ExpressionBuilder variable(String str) {
        this.variableNames.add(str);
        return this;
    }

    public ExpressionBuilder implicitMultiplication(boolean z) {
        this.implicitMultiplication = z;
        return this;
    }

    public ExpressionBuilder operator(Operator operator) {
        checkOperatorSymbol(operator);
        this.userOperators.put(operator.getSymbol(), operator);
        return this;
    }

    private void checkOperatorSymbol(Operator operator) {
        String symbol = operator.getSymbol();
        for (char c : symbol.toCharArray()) {
            if (!Operator.isAllowedOperatorChar(c)) {
                throw new IllegalArgumentException("The operator symbol '" + symbol + "' is invalid");
            }
        }
    }

    public ExpressionBuilder operator(Operator... operatorArr) {
        for (Operator operator : operatorArr) {
            operator(operator);
        }
        return this;
    }

    public ExpressionBuilder operator(List<Operator> list) {
        Iterator<Operator> it = list.iterator();
        while (it.hasNext()) {
            operator(it.next());
        }
        return this;
    }

    public Expression build() {
        if (this.expression.length() == 0) {
            throw new IllegalArgumentException("The expression can not be empty");
        }
        this.variableNames.add("pi");
        this.variableNames.add("π");
        this.variableNames.add("e");
        this.variableNames.add("φ");
        for (String str : this.variableNames) {
            if (Functions.getBuiltinFunction(str) != null || this.userFunctions.containsKey(str)) {
                throw new IllegalArgumentException("A variable can not have the same name as a function [" + str + "]");
            }
        }
        return new Expression(ShuntingYard.convertToRPN(this.expression, this.userFunctions, this.userOperators, this.variableNames, this.implicitMultiplication), this.userFunctions.keySet());
    }
}

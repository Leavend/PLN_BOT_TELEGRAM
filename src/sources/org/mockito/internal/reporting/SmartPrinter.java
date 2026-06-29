package org.mockito.internal.reporting;

import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public class SmartPrinter {
    private final String actual;
    private final String wanted;

    public SmartPrinter(MatchableInvocation matchableInvocation, Invocation invocation, Integer... numArr) {
        PrintSettings printSettings = new PrintSettings();
        printSettings.setMultiline(matchableInvocation.toString().contains("\n") || invocation.toString().contains("\n"));
        printSettings.setMatchersToBeDescribedWithExtraTypeInfo(numArr);
        this.wanted = printSettings.print(matchableInvocation);
        this.actual = printSettings.print(invocation);
    }

    public String getWanted() {
        return this.wanted;
    }

    public String getActual() {
        return this.actual;
    }
}

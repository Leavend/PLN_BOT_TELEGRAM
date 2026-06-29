package org.mockito.internal.debugging;

import java.io.PrintStream;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;

/* loaded from: classes3.dex */
public class VerboseMockInvocationLogger implements InvocationListener {
    private int mockInvocationsCounter;
    final PrintStream printStream;

    public VerboseMockInvocationLogger() {
        this(System.out);
    }

    public VerboseMockInvocationLogger(PrintStream printStream) {
        this.mockInvocationsCounter = 0;
        this.printStream = printStream;
    }

    @Override // org.mockito.listeners.InvocationListener
    public void reportInvocation(MethodInvocationReport methodInvocationReport) {
        printHeader();
        printStubInfo(methodInvocationReport);
        printInvocation(methodInvocationReport.getInvocation());
        printReturnedValueOrThrowable(methodInvocationReport);
        printFooter();
    }

    private void printReturnedValueOrThrowable(MethodInvocationReport methodInvocationReport) {
        if (methodInvocationReport.threwException()) {
            printlnIndented("has thrown: " + methodInvocationReport.getThrowable().getClass() + (methodInvocationReport.getThrowable().getMessage() != null ? " with message " + methodInvocationReport.getThrowable().getMessage() : ""));
        } else {
            printlnIndented("has returned: \"" + methodInvocationReport.getReturnedValue() + "\"" + (methodInvocationReport.getReturnedValue() != null ? " (" + methodInvocationReport.getReturnedValue().getClass().getName() + ")" : ""));
        }
    }

    private void printStubInfo(MethodInvocationReport methodInvocationReport) {
        if (methodInvocationReport.getLocationOfStubbing() != null) {
            printlnIndented("stubbed: " + methodInvocationReport.getLocationOfStubbing());
        }
    }

    private void printHeader() {
        this.mockInvocationsCounter++;
        this.printStream.println("############ Logging method invocation #" + this.mockInvocationsCounter + " on mock/spy ########");
    }

    private void printInvocation(DescribedInvocation describedInvocation) {
        this.printStream.println(describedInvocation.toString());
        printlnIndented("invoked: " + describedInvocation.getLocation().toString());
    }

    private void printFooter() {
        this.printStream.println("");
    }

    private void printlnIndented(String str) {
        this.printStream.println("   " + str);
    }
}

package org.mockito.exceptions.verification.junit;

import junit.framework.ComparisonFailure;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.mockito.internal.util.StringUtil;

/* loaded from: classes3.dex */
public class ArgumentsAreDifferent extends ComparisonFailure {
    private static final long serialVersionUID = 1;
    private final String message;
    private final StackTraceElement[] unfilteredStackTrace;

    public ArgumentsAreDifferent(String str, String str2, String str3) {
        super(str, str2, str3);
        this.message = str;
        this.unfilteredStackTrace = getStackTrace();
        new ConditionalStackTraceFilter().filter(this);
    }

    @Override // junit.framework.ComparisonFailure, java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public StackTraceElement[] getUnfilteredStackTrace() {
        return this.unfilteredStackTrace;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return StringUtil.removeFirstLine(super.toString());
    }
}

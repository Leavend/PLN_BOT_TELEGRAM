package org.mockito.internal.exceptions.stacktrace;

import org.mockito.exceptions.stacktrace.StackTraceCleaner;
import org.mockito.plugins.StackTraceCleanerProvider;

/* loaded from: classes3.dex */
public class DefaultStackTraceCleanerProvider implements StackTraceCleanerProvider {
    @Override // org.mockito.plugins.StackTraceCleanerProvider
    public StackTraceCleaner getStackTraceCleaner(StackTraceCleaner stackTraceCleaner) {
        return stackTraceCleaner;
    }
}

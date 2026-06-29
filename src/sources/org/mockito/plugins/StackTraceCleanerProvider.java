package org.mockito.plugins;

import org.mockito.exceptions.stacktrace.StackTraceCleaner;

/* loaded from: classes3.dex */
public interface StackTraceCleanerProvider {
    StackTraceCleaner getStackTraceCleaner(StackTraceCleaner stackTraceCleaner);
}

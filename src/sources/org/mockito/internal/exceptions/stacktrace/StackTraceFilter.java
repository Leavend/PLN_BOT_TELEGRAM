package org.mockito.internal.exceptions.stacktrace;

import java.io.Serializable;
import java.util.ArrayList;
import org.mockito.exceptions.stacktrace.StackTraceCleaner;
import org.mockito.internal.configuration.plugins.Plugins;

/* loaded from: classes3.dex */
public class StackTraceFilter implements Serializable {
    private static final StackTraceCleaner CLEANER = Plugins.getStackTraceCleanerProvider().getStackTraceCleaner(new DefaultStackTraceCleaner());
    static final long serialVersionUID = -5499819791513105700L;

    public StackTraceElement[] filter(StackTraceElement[] stackTraceElementArr, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            if (CLEANER.isIn(stackTraceElement)) {
                arrayList.add(stackTraceElement);
            }
        }
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]);
    }
}

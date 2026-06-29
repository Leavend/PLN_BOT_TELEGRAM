package org.mockito.internal.debugging;

import java.io.Serializable;
import org.mockito.internal.exceptions.stacktrace.StackTraceFilter;
import org.mockito.invocation.Location;

/* loaded from: classes3.dex */
public class LocationImpl implements Location, Serializable {
    private static final StackTraceFilter defaultStackTraceFilter = new StackTraceFilter();
    private static final long serialVersionUID = -9054861157390980624L;
    private final StackTraceFilter stackTraceFilter;
    private final Throwable stackTraceHolder;

    public LocationImpl() {
        this(defaultStackTraceFilter);
    }

    public LocationImpl(StackTraceFilter stackTraceFilter) {
        this(stackTraceFilter, new Throwable());
    }

    public LocationImpl(Throwable th) {
        this(defaultStackTraceFilter, th);
    }

    private LocationImpl(StackTraceFilter stackTraceFilter, Throwable th) {
        this.stackTraceFilter = stackTraceFilter;
        this.stackTraceHolder = th;
    }

    @Override // org.mockito.invocation.Location
    public String toString() {
        StackTraceElement[] stackTraceElementArrFilter = this.stackTraceFilter.filter(this.stackTraceHolder.getStackTrace(), false);
        return stackTraceElementArrFilter.length == 0 ? "-> at <<unknown line>>" : "-> at " + stackTraceElementArrFilter[0].toString();
    }
}

package org.mockito.internal.junit;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
class StubbingArgMismatches {
    final Map<Invocation, Set<Invocation>> mismatches = new LinkedHashMap();

    StubbingArgMismatches() {
    }

    public void add(Invocation invocation, Invocation invocation2) {
        Set<Invocation> linkedHashSet = this.mismatches.get(invocation2);
        if (linkedHashSet == null) {
            linkedHashSet = new LinkedHashSet<>();
            this.mismatches.put(invocation2, linkedHashSet);
        }
        linkedHashSet.add(invocation);
    }

    public void format(String str, MockitoLogger mockitoLogger) {
        if (this.mismatches.isEmpty()) {
            return;
        }
        StubbingHint stubbingHint = new StubbingHint(str);
        int i = 1;
        for (Map.Entry<Invocation, Set<Invocation>> entry : this.mismatches.entrySet()) {
            int i2 = i + 1;
            stubbingHint.appendLine(Integer.valueOf(i), ". Unused... ", entry.getKey().getLocation());
            Iterator<Invocation> it = entry.getValue().iterator();
            while (it.hasNext()) {
                stubbingHint.appendLine(" ...args ok? ", it.next().getLocation());
            }
            i = i2;
        }
        mockitoLogger.log(stubbingHint.toString());
    }

    public int size() {
        return this.mismatches.size();
    }

    public String toString() {
        return "" + this.mismatches;
    }
}

package org.mockito.internal.junit;

import java.util.Collection;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class UnusedStubbings {
    private final Collection<? extends Stubbing> unused;

    UnusedStubbings(Collection<? extends Stubbing> collection) {
        this.unused = collection;
    }

    void format(String str, MockitoLogger mockitoLogger) {
        if (this.unused.isEmpty()) {
            return;
        }
        StubbingHint stubbingHint = new StubbingHint(str);
        int i = 1;
        for (Stubbing stubbing : this.unused) {
            if (!stubbing.wasUsed()) {
                stubbingHint.appendLine(Integer.valueOf(i), ". Unused ", stubbing.getInvocation().getLocation());
                i++;
            }
        }
        mockitoLogger.log(stubbingHint.toString());
    }

    public int size() {
        return this.unused.size();
    }

    public String toString() {
        return this.unused.toString();
    }

    public void reportUnused() {
        if (this.unused.isEmpty()) {
            return;
        }
        Reporter.unncessaryStubbingException(ListUtil.convert(this.unused, new ListUtil.Converter<Stubbing, Invocation>() { // from class: org.mockito.internal.junit.UnusedStubbings.1
            @Override // org.mockito.internal.util.collections.ListUtil.Converter
            public Invocation convert(Stubbing stubbing) {
                return stubbing.getInvocation();
            }
        }));
    }
}

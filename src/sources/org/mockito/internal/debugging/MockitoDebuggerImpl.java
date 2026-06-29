package org.mockito.internal.debugging;

import java.util.Arrays;
import java.util.List;
import org.mockito.MockitoDebugger;
import org.mockito.internal.invocation.UnusedStubsFinder;
import org.mockito.internal.invocation.finder.AllInvocationsFinder;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public class MockitoDebuggerImpl implements MockitoDebugger {
    private final UnusedStubsFinder unusedStubsFinder = new UnusedStubsFinder();

    @Override // org.mockito.MockitoDebugger
    @Deprecated
    public String printInvocations(Object... objArr) {
        List<Invocation> listFind = AllInvocationsFinder.find(Arrays.asList(objArr));
        String str = (("" + line("********************************")) + line("*** Mockito interactions log ***")) + line("********************************");
        for (Invocation invocation : listFind) {
            str = (str + line(invocation.toString())) + line(" invoked: " + invocation.getLocation());
            if (invocation.stubInfo() != null) {
                str = str + line(" stubbed: " + invocation.stubInfo().stubbedAt().toString());
            }
        }
        List<Invocation> listFind2 = this.unusedStubsFinder.find(Arrays.asList(objArr));
        if (listFind2.isEmpty()) {
            return print(str);
        }
        String str2 = ((str + line("********************************")) + line("***       Unused stubs       ***")) + line("********************************");
        for (Invocation invocation2 : listFind2) {
            str2 = (str2 + line(invocation2.toString())) + line(" stubbed: " + invocation2.getLocation());
        }
        return print(str2);
    }

    private String line(String str) {
        return str + "\n";
    }

    private String print(String str) {
        System.out.println(str);
        return str;
    }
}

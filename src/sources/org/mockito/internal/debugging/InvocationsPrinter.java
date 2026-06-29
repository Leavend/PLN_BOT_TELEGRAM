package org.mockito.internal.debugging;

import java.util.Collection;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.ListUtil;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class InvocationsPrinter {
    public String printInvocations(Object obj) {
        Collection<Invocation> invocations = Mockito.mockingDetails(obj).getInvocations();
        Collection<Stubbing> stubbings = Mockito.mockingDetails(obj).getStubbings();
        if (invocations.isEmpty() && stubbings.isEmpty()) {
            return "No interactions and stubbings found for mock: " + obj;
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        int i2 = 1;
        for (Invocation invocation : invocations) {
            if (i2 == 1) {
                sb.append("[Mockito] Interactions of: ").append(obj).append("\n");
            }
            int i3 = i2 + 1;
            sb.append(" ").append(i2).append(". ").append(invocation.toString()).append("\n  ");
            sb.append(invocation.getLocation()).append("\n");
            if (invocation.stubInfo() != null) {
                sb.append("   - stubbed ").append(invocation.stubInfo().stubbedAt()).append("\n");
            }
            i2 = i3;
        }
        if (ListUtil.filter(stubbings, new ListUtil.Filter<Stubbing>() { // from class: org.mockito.internal.debugging.InvocationsPrinter.1
            @Override // org.mockito.internal.util.collections.ListUtil.Filter
            public boolean isOut(Stubbing stubbing) {
                return stubbing.wasUsed();
            }
        }).isEmpty()) {
            return sb.toString();
        }
        sb.append("[Mockito] Unused stubbings of: ").append(obj).append("\n");
        for (Stubbing stubbing : stubbings) {
            sb.append(" ").append(i).append(". ").append(stubbing.getInvocation()).append("\n  - stubbed ");
            sb.append(stubbing.getInvocation().getLocation()).append("\n");
            i++;
        }
        return sb.toString();
    }
}

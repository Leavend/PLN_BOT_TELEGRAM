package org.mockito.internal.invocation;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.matchers.LocalizedMatcher;
import org.mockito.internal.progress.ArgumentMatcherStorage;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public class MatchersBinder implements Serializable {
    public InvocationMatcher bindMatchers(ArgumentMatcherStorage argumentMatcherStorage, Invocation invocation) {
        List<LocalizedMatcher> listPullLocalizedMatchers = argumentMatcherStorage.pullLocalizedMatchers();
        validateMatchers(invocation, listPullLocalizedMatchers);
        LinkedList linkedList = new LinkedList();
        Iterator<LocalizedMatcher> it = listPullLocalizedMatchers.iterator();
        while (it.hasNext()) {
            linkedList.add(it.next().getMatcher());
        }
        return new InvocationMatcher(invocation, linkedList);
    }

    private void validateMatchers(Invocation invocation, List<LocalizedMatcher> list) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        int length = invocation.getArguments().length;
        if (length != size) {
            throw Reporter.invalidUseOfMatchers(length, list);
        }
    }
}

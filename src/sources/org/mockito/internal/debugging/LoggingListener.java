package org.mockito.internal.debugging;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.util.StringUtil;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public class LoggingListener implements FindingsListener {
    private final boolean warnAboutUnstubbed;
    private final List<String> argMismatchStubs = new LinkedList();
    private final List<String> unusedStubs = new LinkedList();
    private final List<String> unstubbedCalls = new LinkedList();

    public LoggingListener(boolean z) {
        this.warnAboutUnstubbed = z;
    }

    @Override // org.mockito.internal.debugging.FindingsListener
    public void foundStubCalledWithDifferentArgs(Invocation invocation, InvocationMatcher invocationMatcher) {
        String string = Integer.toString(indexOfNextPair(this.argMismatchStubs.size()));
        String strReplaceAll = string.replaceAll("\\d", " ");
        this.argMismatchStubs.add(string + ". Stubbed " + invocation.getLocation());
        this.argMismatchStubs.add(strReplaceAll + "  Invoked " + invocationMatcher.getInvocation().getLocation());
    }

    static int indexOfNextPair(int i) {
        return (i / 2) + 1;
    }

    @Override // org.mockito.internal.debugging.FindingsListener
    public void foundUnusedStub(Invocation invocation) {
        this.unusedStubs.add((this.unusedStubs.size() + 1) + ". " + invocation.getLocation());
    }

    @Override // org.mockito.internal.debugging.FindingsListener
    public void foundUnstubbed(InvocationMatcher invocationMatcher) {
        if (this.warnAboutUnstubbed) {
            this.unstubbedCalls.add((this.unstubbedCalls.size() + 1) + ". " + invocationMatcher.getInvocation().getLocation());
        }
    }

    public String getStubbingInfo() {
        if (this.argMismatchStubs.isEmpty() && this.unusedStubs.isEmpty() && this.unstubbedCalls.isEmpty()) {
            return "";
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add("[Mockito] Additional stubbing information (see javadoc for StubbingInfo class):");
        if (!this.argMismatchStubs.isEmpty()) {
            linkedList.add("[Mockito]");
            linkedList.add("[Mockito] Argument mismatch between stubbing and actual invocation (is stubbing correct in the test?):");
            linkedList.add("[Mockito]");
            addOrderedList(linkedList, this.argMismatchStubs);
        }
        if (!this.unusedStubs.isEmpty()) {
            linkedList.add("[Mockito]");
            linkedList.add("[Mockito] Unused stubbing (perhaps can be removed from the test?):");
            linkedList.add("[Mockito]");
            addOrderedList(linkedList, this.unusedStubs);
        }
        if (!this.unstubbedCalls.isEmpty()) {
            linkedList.add("[Mockito]");
            linkedList.add("[Mockito] Unstubbed method invocations (perhaps missing stubbing in the test?):");
            linkedList.add("[Mockito]");
            addOrderedList(linkedList, this.unstubbedCalls);
        }
        return StringUtil.join("", linkedList);
    }

    private void addOrderedList(List<String> list, List<String> list2) {
        Iterator<String> it = list2.iterator();
        while (it.hasNext()) {
            list.add("[Mockito] " + it.next());
        }
    }
}

package org.mockito.internal.stubbing;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class StubbedInvocationMatcher extends InvocationMatcher implements Serializable, Stubbing {
    private static final long serialVersionUID = 4919105134123672727L;
    private final Queue<Answer> answers;
    private DescribedInvocation usedAt;

    public StubbedInvocationMatcher(MatchableInvocation matchableInvocation, Answer answer) {
        super(matchableInvocation.getInvocation(), matchableInvocation.getMatchers());
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        this.answers = concurrentLinkedQueue;
        concurrentLinkedQueue.add(answer);
    }

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Answer answerPeek;
        synchronized (this.answers) {
            answerPeek = this.answers.size() == 1 ? this.answers.peek() : this.answers.poll();
        }
        return answerPeek.answer(invocationOnMock);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void markStubUsed(DescribedInvocation describedInvocation) {
        this.usedAt = describedInvocation;
    }

    @Override // org.mockito.stubbing.Stubbing
    public boolean wasUsed() {
        return this.usedAt != null;
    }

    @Override // org.mockito.internal.invocation.InvocationMatcher, org.mockito.invocation.DescribedInvocation
    public String toString() {
        return super.toString() + " stubbed with: " + this.answers;
    }
}

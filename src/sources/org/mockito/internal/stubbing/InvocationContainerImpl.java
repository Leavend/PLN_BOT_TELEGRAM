package org.mockito.internal.stubbing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.invocation.StubInfoImpl;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.verification.DefaultRegisteredInvocations;
import org.mockito.internal.verification.RegisteredInvocations;
import org.mockito.internal.verification.SingleRegisteredInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;
import org.mockito.stubbing.ValidableAnswer;

/* loaded from: classes3.dex */
public class InvocationContainerImpl implements InvocationContainer, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = -5334301962749537177L;
    private MatchableInvocation invocationForStubbing;
    private final RegisteredInvocations registeredInvocations;
    private final LinkedList<StubbedInvocationMatcher> stubbed = new LinkedList<>();
    private final List<Answer<?>> answersForStubbing = new ArrayList();

    public InvocationContainerImpl(MockCreationSettings mockCreationSettings) {
        this.registeredInvocations = createRegisteredInvocations(mockCreationSettings);
    }

    public void setInvocationForPotentialStubbing(MatchableInvocation matchableInvocation) {
        this.registeredInvocations.add(matchableInvocation.getInvocation());
        this.invocationForStubbing = matchableInvocation;
    }

    public void resetInvocationForPotentialStubbing(MatchableInvocation matchableInvocation) {
        this.invocationForStubbing = matchableInvocation;
    }

    public void addAnswer(Answer answer) {
        this.registeredInvocations.removeLast();
        addAnswer(answer, false);
    }

    public void addConsecutiveAnswer(Answer answer) {
        addAnswer(answer, true);
    }

    public StubbedInvocationMatcher addAnswer(Answer answer, boolean z) {
        StubbedInvocationMatcher first;
        Invocation invocation = this.invocationForStubbing.getInvocation();
        ThreadSafeMockingProgress.mockingProgress().stubbingCompleted();
        if (answer instanceof ValidableAnswer) {
            ((ValidableAnswer) answer).validateFor(invocation);
        }
        synchronized (this.stubbed) {
            if (z) {
                this.stubbed.getFirst().addAnswer(answer);
            } else {
                this.stubbed.addFirst(new StubbedInvocationMatcher(this.invocationForStubbing, answer));
            }
            first = this.stubbed.getFirst();
        }
        return first;
    }

    Object answerTo(Invocation invocation) throws Throwable {
        return findAnswerFor(invocation).answer(invocation);
    }

    public StubbedInvocationMatcher findAnswerFor(Invocation invocation) {
        synchronized (this.stubbed) {
            Iterator<StubbedInvocationMatcher> it = this.stubbed.iterator();
            while (it.hasNext()) {
                StubbedInvocationMatcher next = it.next();
                if (next.matches(invocation)) {
                    next.markStubUsed(invocation);
                    invocation.markStubbed(new StubInfoImpl(next));
                    return next;
                }
            }
            return null;
        }
    }

    public void setAnswersForStubbing(List<Answer<?>> list) {
        this.answersForStubbing.addAll(list);
    }

    public boolean hasAnswersForStubbing() {
        return !this.answersForStubbing.isEmpty();
    }

    public boolean hasInvocationForPotentialStubbing() {
        return !this.registeredInvocations.isEmpty();
    }

    public void setMethodForStubbing(MatchableInvocation matchableInvocation) {
        this.invocationForStubbing = matchableInvocation;
        int i = 0;
        while (i < this.answersForStubbing.size()) {
            addAnswer(this.answersForStubbing.get(i), i != 0);
            i++;
        }
        this.answersForStubbing.clear();
    }

    public String toString() {
        return "invocationForStubbing: " + this.invocationForStubbing;
    }

    public List<Invocation> getInvocations() {
        return this.registeredInvocations.getAll();
    }

    public void clearInvocations() {
        this.registeredInvocations.clear();
    }

    public List<Stubbing> getStubbedInvocations() {
        return this.stubbed;
    }

    public Object invokedMock() {
        return this.invocationForStubbing.getInvocation().getMock();
    }

    public MatchableInvocation getInvocationForStubbing() {
        return this.invocationForStubbing;
    }

    private RegisteredInvocations createRegisteredInvocations(MockCreationSettings mockCreationSettings) {
        RegisteredInvocations defaultRegisteredInvocations;
        if (mockCreationSettings.isStubOnly()) {
            defaultRegisteredInvocations = new SingleRegisteredInvocation();
        } else {
            defaultRegisteredInvocations = new DefaultRegisteredInvocations();
        }
        return defaultRegisteredInvocations;
    }
}

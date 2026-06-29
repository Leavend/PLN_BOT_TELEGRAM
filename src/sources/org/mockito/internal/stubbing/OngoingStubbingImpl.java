package org.mockito.internal.stubbing;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

/* loaded from: classes3.dex */
public class OngoingStubbingImpl<T> extends BaseStubbing<T> {
    private final InvocationContainerImpl invocationContainer;

    public OngoingStubbingImpl(InvocationContainerImpl invocationContainerImpl) {
        this.invocationContainer = invocationContainerImpl;
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenAnswer(Answer<?> answer) {
        if (!this.invocationContainer.hasInvocationForPotentialStubbing()) {
            throw Reporter.incorrectUseOfApi();
        }
        this.invocationContainer.addAnswer(answer);
        return new ConsecutiveStubbing(this.invocationContainer);
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> then(Answer<?> answer) {
        return thenAnswer(answer);
    }

    public List<Invocation> getRegisteredInvocations() {
        return this.invocationContainer.getInvocations();
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public <M> M getMock() {
        return (M) this.invocationContainer.invokedMock();
    }
}

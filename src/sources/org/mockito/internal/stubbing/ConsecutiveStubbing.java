package org.mockito.internal.stubbing;

import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

/* loaded from: classes3.dex */
public class ConsecutiveStubbing<T> extends BaseStubbing<T> {
    private final InvocationContainerImpl invocationContainerImpl;

    public ConsecutiveStubbing(InvocationContainerImpl invocationContainerImpl) {
        this.invocationContainerImpl = invocationContainerImpl;
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenAnswer(Answer<?> answer) {
        this.invocationContainerImpl.addConsecutiveAnswer(answer);
        return this;
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> then(Answer<?> answer) {
        return thenAnswer(answer);
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public <M> M getMock() {
        return (M) this.invocationContainerImpl.invokedMock();
    }
}

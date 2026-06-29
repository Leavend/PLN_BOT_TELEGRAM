package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import org.mockito.internal.MockitoCore;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/* loaded from: classes3.dex */
public class ReturnsMocks implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -6755257986994634579L;
    private final MockitoCore mockitoCore = new MockitoCore();
    private final Answer<Object> delegate = new ReturnsMoreEmptyValues();

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object objAnswer = this.delegate.answer(invocationOnMock);
        return objAnswer != null ? objAnswer : returnValueFor(invocationOnMock.getMethod().getReturnType());
    }

    Object returnValueFor(Class<?> cls) {
        if (this.mockitoCore.isTypeMockable(cls)) {
            return this.mockitoCore.mock(cls, new MockSettingsImpl().defaultAnswer(this));
        }
        return null;
    }
}

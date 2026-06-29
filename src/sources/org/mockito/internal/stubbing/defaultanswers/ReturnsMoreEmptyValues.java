package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/* loaded from: classes3.dex */
public class ReturnsMoreEmptyValues implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -2816745041482698471L;
    private final Answer<Object> delegate = new ReturnsEmptyValues();

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object objAnswer = this.delegate.answer(invocationOnMock);
        return objAnswer != null ? objAnswer : returnValueFor(invocationOnMock.getMethod().getReturnType());
    }

    Object returnValueFor(Class<?> cls) {
        if (cls == String.class) {
            return "";
        }
        if (cls.isArray()) {
            return Array.newInstance(cls.getComponentType(), 0);
        }
        return null;
    }
}

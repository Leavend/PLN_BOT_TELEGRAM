package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import org.mockito.Mockito;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.invocation.Location;
import org.mockito.stubbing.Answer;

/* loaded from: classes3.dex */
public class ReturnsSmartNulls implements Answer<Object>, Serializable {
    private static final long serialVersionUID = 7618312406617949441L;
    private final Answer<Object> delegate = new ReturnsMoreEmptyValues();

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object objAnswer = this.delegate.answer(invocationOnMock);
        if (objAnswer != null) {
            return objAnswer;
        }
        Class<?> returnType = invocationOnMock.getMethod().getReturnType();
        if (returnType.isPrimitive() || Modifier.isFinal(returnType.getModifiers())) {
            return null;
        }
        return Mockito.mock(returnType, new ThrowsSmartNullPointer(invocationOnMock, new LocationImpl()));
    }

    private static class ThrowsSmartNullPointer implements Answer {
        private final Location location;
        private final InvocationOnMock unstubbedInvocation;

        public ThrowsSmartNullPointer(InvocationOnMock invocationOnMock, Location location) {
            this.unstubbedInvocation = invocationOnMock;
            this.location = location;
        }

        @Override // org.mockito.stubbing.Answer
        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            if (ObjectMethodsGuru.isToStringMethod(invocationOnMock.getMethod())) {
                return "SmartNull returned by this unstubbed method call on a mock:\n" + this.unstubbedInvocation.toString();
            }
            throw Reporter.smartNullPointerException(this.unstubbedInvocation.toString(), this.location);
        }
    }
}

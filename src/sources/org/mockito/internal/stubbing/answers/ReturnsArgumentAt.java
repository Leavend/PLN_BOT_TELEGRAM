package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

/* loaded from: classes3.dex */
public class ReturnsArgumentAt implements Answer<Object>, ValidableAnswer, Serializable {
    public static final int LAST_ARGUMENT = -1;
    private static final long serialVersionUID = -589315085166295101L;
    private final int wantedArgumentPosition;

    public ReturnsArgumentAt(int i) {
        if (i != -1 && i < 0) {
            throw Reporter.invalidArgumentRangeAtIdentityAnswerCreationTime();
        }
        this.wantedArgumentPosition = i;
    }

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        int iInferWantedArgumentPosition = inferWantedArgumentPosition(invocationOnMock);
        validateIndexWithinInvocationRange(invocationOnMock, iInferWantedArgumentPosition);
        if (wantedArgIndexIsVarargAndSameTypeAsReturnType(invocationOnMock.getMethod(), iInferWantedArgumentPosition)) {
            return ((Invocation) invocationOnMock).getRawArguments()[iInferWantedArgumentPosition];
        }
        return invocationOnMock.getArgument(iInferWantedArgumentPosition);
    }

    @Override // org.mockito.stubbing.ValidableAnswer
    public void validateFor(InvocationOnMock invocationOnMock) {
        int iInferWantedArgumentPosition = inferWantedArgumentPosition(invocationOnMock);
        validateIndexWithinInvocationRange(invocationOnMock, iInferWantedArgumentPosition);
        validateArgumentTypeCompatibility((Invocation) invocationOnMock, iInferWantedArgumentPosition);
    }

    private int inferWantedArgumentPosition(InvocationOnMock invocationOnMock) {
        int i = this.wantedArgumentPosition;
        return i == -1 ? invocationOnMock.getArguments().length - 1 : i;
    }

    private void validateIndexWithinInvocationRange(InvocationOnMock invocationOnMock, int i) {
        if (wantedArgumentPositionIsValidForInvocation(invocationOnMock, i)) {
            return;
        }
        int i2 = this.wantedArgumentPosition;
        throw Reporter.invalidArgumentPositionRangeAtInvocationTime(invocationOnMock, i2 == -1, i2);
    }

    private void validateArgumentTypeCompatibility(Invocation invocation, int i) {
        InvocationInfo invocationInfo = new InvocationInfo(invocation);
        Class<?> clsInferArgumentType = inferArgumentType(invocation, i);
        if (!invocationInfo.isValidReturnType(clsInferArgumentType)) {
            throw Reporter.wrongTypeOfArgumentToReturn(invocation, invocationInfo.printMethodReturnType(), clsInferArgumentType, this.wantedArgumentPosition);
        }
    }

    private boolean wantedArgIndexIsVarargAndSameTypeAsReturnType(Method method, int i) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return method.isVarArgs() && i == parameterTypes.length - 1 && method.getReturnType().isAssignableFrom(parameterTypes[i]);
    }

    private boolean wantedArgumentPositionIsValidForInvocation(InvocationOnMock invocationOnMock, int i) {
        if (i < 0) {
            return false;
        }
        return invocationOnMock.getMethod().isVarArgs() || invocationOnMock.getArguments().length > i;
    }

    private Class<?> inferArgumentType(Invocation invocation, int i) {
        Class<?>[] parameterTypes = invocation.getMethod().getParameterTypes();
        if (!invocation.getMethod().isVarArgs()) {
            Class<?> cls = parameterTypes[i];
            Object argument = invocation.getArgument(i);
            return (cls.isPrimitive() || argument == null) ? cls : argument.getClass();
        }
        int length = parameterTypes.length - 1;
        if (i < length) {
            return parameterTypes[i];
        }
        if (wantedArgIndexIsVarargAndSameTypeAsReturnType(invocation.getMethod(), i)) {
            return parameterTypes[i];
        }
        return parameterTypes[length].getComponentType();
    }
}

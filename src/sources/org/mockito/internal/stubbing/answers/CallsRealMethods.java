package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import org.mockito.Answers;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

/* loaded from: classes3.dex */
public class CallsRealMethods implements Answer<Object>, ValidableAnswer, Serializable {
    private static final long serialVersionUID = 9057165148930624087L;

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        if (Modifier.isAbstract(invocationOnMock.getMethod().getModifiers())) {
            return Answers.RETURNS_DEFAULTS.answer(invocationOnMock);
        }
        return invocationOnMock.callRealMethod();
    }

    @Override // org.mockito.stubbing.ValidableAnswer
    public void validateFor(InvocationOnMock invocationOnMock) {
        if (new InvocationInfo(invocationOnMock).isAbstract()) {
            throw Reporter.cannotCallAbstractRealMethod();
        }
    }
}

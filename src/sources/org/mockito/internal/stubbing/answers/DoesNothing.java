package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

/* loaded from: classes3.dex */
public class DoesNothing implements Answer<Object>, ValidableAnswer, Serializable {
    private static final DoesNothing SINGLETON = new DoesNothing();
    private static final long serialVersionUID = 4840880517740698416L;

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) {
        return null;
    }

    private DoesNothing() {
    }

    public static DoesNothing doesNothing() {
        return SINGLETON;
    }

    @Override // org.mockito.stubbing.ValidableAnswer
    public void validateFor(InvocationOnMock invocationOnMock) {
        if (!new InvocationInfo(invocationOnMock).isVoid()) {
            throw Reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }
}

package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

/* loaded from: classes3.dex */
public class AnswersWithDelay implements Answer<Object>, ValidableAnswer, Serializable {
    private static final long serialVersionUID = 2177950597971260246L;
    private final Answer<Object> answer;
    private final long sleepyTime;

    public AnswersWithDelay(long j, Answer<Object> answer) {
        this.sleepyTime = j;
        this.answer = answer;
    }

    @Override // org.mockito.stubbing.Answer
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        TimeUnit.MILLISECONDS.sleep(this.sleepyTime);
        return this.answer.answer(invocationOnMock);
    }

    @Override // org.mockito.stubbing.ValidableAnswer
    public void validateFor(InvocationOnMock invocationOnMock) {
        Answer<Object> answer = this.answer;
        if (answer instanceof ValidableAnswer) {
            ((ValidableAnswer) answer).validateFor(invocationOnMock);
        }
    }
}

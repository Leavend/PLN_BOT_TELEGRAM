package org.mockito.internal.stubbing;

import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.mockito.internal.util.MockUtil;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;
import org.objenesis.ObjenesisHelper;

/* loaded from: classes3.dex */
public class StubberImpl implements Stubber {
    private final List<Answer<?>> answers = new LinkedList();

    @Override // org.mockito.stubbing.Stubber
    public <T> T when(T t) {
        if (t == null) {
            throw Reporter.nullPassedToWhenMethod();
        }
        if (!MockUtil.isMock(t)) {
            throw Reporter.notAMockPassedToWhenMethod();
        }
        MockUtil.getInvocationContainer(t).setAnswersForStubbing(this.answers);
        return t;
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doReturn(Object obj) {
        return doReturnValues(obj);
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doReturn(Object obj, Object... objArr) {
        return doReturnValues(obj).doReturnValues(objArr);
    }

    private StubberImpl doReturnValues(Object... objArr) {
        if (objArr == null) {
            this.answers.add(new Returns(null));
            return this;
        }
        for (Object obj : objArr) {
            this.answers.add(new Returns(obj));
        }
        return this;
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doThrow(Throwable... thArr) {
        if (thArr == null) {
            this.answers.add(new ThrowsException(null));
            return this;
        }
        for (Throwable th : thArr) {
            this.answers.add(new ThrowsException(th));
        }
        return this;
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doThrow(Class<? extends Throwable> cls) {
        if (cls == null) {
            ThreadSafeMockingProgress.mockingProgress().reset();
            throw Reporter.notAnException();
        }
        try {
            return doThrow((Throwable) ObjenesisHelper.newInstance(cls));
        } catch (RuntimeException e) {
            ThreadSafeMockingProgress.mockingProgress().reset();
            throw e;
        }
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
        Stubber stubberDoThrow = doThrow(cls);
        if (clsArr == null) {
            ThreadSafeMockingProgress.mockingProgress().reset();
            throw Reporter.notAnException();
        }
        for (Class<? extends Throwable> cls2 : clsArr) {
            stubberDoThrow = stubberDoThrow.doThrow(cls2);
        }
        return stubberDoThrow;
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doNothing() {
        this.answers.add(DoesNothing.doesNothing());
        return this;
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doAnswer(Answer answer) {
        this.answers.add(answer);
        return this;
    }

    @Override // org.mockito.stubbing.Stubber
    public Stubber doCallRealMethod() {
        this.answers.add(new CallsRealMethods());
        return this;
    }
}

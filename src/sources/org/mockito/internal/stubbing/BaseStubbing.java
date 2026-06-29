package org.mockito.internal.stubbing;

import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.mockito.stubbing.OngoingStubbing;
import org.objenesis.ObjenesisHelper;

/* loaded from: classes3.dex */
public abstract class BaseStubbing<T> implements OngoingStubbing<T> {
    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenReturn(T t) {
        return thenAnswer(new Returns(t));
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenReturn(T t, T... tArr) {
        OngoingStubbing<T> ongoingStubbingThenReturn = thenReturn(t);
        if (tArr == null) {
            return ongoingStubbingThenReturn.thenReturn(null);
        }
        for (T t2 : tArr) {
            ongoingStubbingThenReturn = ongoingStubbingThenReturn.thenReturn(t2);
        }
        return ongoingStubbingThenReturn;
    }

    private OngoingStubbing<T> thenThrow(Throwable th) {
        return thenAnswer(new ThrowsException(th));
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenThrow(Throwable... thArr) {
        OngoingStubbing<T> ongoingStubbingThenThrow = null;
        if (thArr == null) {
            return thenThrow((Throwable) null);
        }
        for (Throwable th : thArr) {
            if (ongoingStubbingThenThrow == null) {
                ongoingStubbingThenThrow = thenThrow(th);
            } else {
                ongoingStubbingThenThrow = ongoingStubbingThenThrow.thenThrow(th);
            }
        }
        return ongoingStubbingThenThrow;
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenThrow(Class<? extends Throwable> cls) {
        if (cls == null) {
            ThreadSafeMockingProgress.mockingProgress().reset();
            throw Reporter.notAnException();
        }
        return thenThrow((Throwable) ObjenesisHelper.newInstance(cls));
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
        if (clsArr == null) {
            return thenThrow((Class<? extends Throwable>) null);
        }
        OngoingStubbing<T> ongoingStubbingThenThrow = thenThrow(cls);
        for (Class<? extends Throwable> cls2 : clsArr) {
            ongoingStubbingThenThrow = ongoingStubbingThenThrow.thenThrow(cls2);
        }
        return ongoingStubbingThenThrow;
    }

    @Override // org.mockito.stubbing.OngoingStubbing
    public OngoingStubbing<T> thenCallRealMethod() {
        return thenAnswer(new CallsRealMethods());
    }
}

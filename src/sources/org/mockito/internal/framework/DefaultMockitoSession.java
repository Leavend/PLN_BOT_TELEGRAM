package org.mockito.internal.framework;

import java.util.Iterator;
import java.util.List;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoSession;
import org.mockito.exceptions.misusing.RedundantListenerException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.junit.TestFinishedEvent;
import org.mockito.internal.junit.UniversalTestListener;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.quality.Strictness;

/* loaded from: classes3.dex */
public class DefaultMockitoSession implements MockitoSession {
    private final UniversalTestListener listener;
    private final String name;
    private final List<Object> testClassInstances;

    public DefaultMockitoSession(List<Object> list, String str, Strictness strictness, MockitoLogger mockitoLogger) {
        this.testClassInstances = list;
        this.name = str;
        UniversalTestListener universalTestListener = new UniversalTestListener(strictness, mockitoLogger);
        this.listener = universalTestListener;
        try {
            Mockito.framework().addListener(universalTestListener);
        } catch (RedundantListenerException unused) {
            Reporter.unfinishedMockingSession();
        }
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            MockitoAnnotations.initMocks(it.next());
        }
    }

    @Override // org.mockito.MockitoSession
    public void setStrictness(Strictness strictness) {
        this.listener.setStrictness(strictness);
    }

    @Override // org.mockito.MockitoSession
    public void finishMocking() {
        finishMocking(null);
    }

    @Override // org.mockito.MockitoSession
    public void finishMocking(final Throwable th) {
        Mockito.framework().removeListener(this.listener);
        this.listener.testFinished(new TestFinishedEvent() { // from class: org.mockito.internal.framework.DefaultMockitoSession.1
            @Override // org.mockito.internal.junit.TestFinishedEvent
            public Throwable getFailure() {
                return th;
            }

            @Override // org.mockito.internal.junit.TestFinishedEvent
            public String getTestName() {
                return DefaultMockitoSession.this.name;
            }
        });
        if (th == null) {
            Mockito.validateMockitoUsage();
        }
    }
}

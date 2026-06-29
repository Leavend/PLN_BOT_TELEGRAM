package org.mockito.junit;

import java.lang.reflect.InvocationTargetException;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.mockito.internal.runners.InternalRunner;
import org.mockito.internal.runners.RunnerFactory;
import org.mockito.internal.runners.StrictRunner;

/* loaded from: classes3.dex */
public class MockitoJUnitRunner extends Runner implements Filterable {
    private final InternalRunner runner;

    public static class Silent extends MockitoJUnitRunner {
        public Silent(Class<?> cls) throws InvocationTargetException {
            super(new RunnerFactory().create(cls));
        }
    }

    public static class Strict extends MockitoJUnitRunner {
        public Strict(Class<?> cls) throws InvocationTargetException {
            super(new StrictRunner(new RunnerFactory().createStrict(cls), cls));
        }
    }

    public static class StrictStubs extends MockitoJUnitRunner {
        public StrictStubs(Class<?> cls) throws InvocationTargetException {
            super(new StrictRunner(new RunnerFactory().createStrictStubs(cls), cls));
        }
    }

    public MockitoJUnitRunner(Class<?> cls) throws InvocationTargetException {
        this(new StrictRunner(new RunnerFactory().createStrict(cls), cls));
    }

    MockitoJUnitRunner(InternalRunner internalRunner) throws InvocationTargetException {
        this.runner = internalRunner;
    }

    @Override // org.junit.runner.Runner
    public void run(RunNotifier runNotifier) {
        this.runner.run(runNotifier);
    }

    @Override // org.junit.runner.Runner, org.junit.runner.Describable
    public Description getDescription() {
        return this.runner.getDescription();
    }

    @Override // org.junit.runner.manipulation.Filterable
    public void filter(Filter filter) throws NoTestsRemainException {
        this.runner.filter(filter);
    }
}

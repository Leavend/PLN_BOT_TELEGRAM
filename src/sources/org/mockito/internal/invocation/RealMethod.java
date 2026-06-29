package org.mockito.internal.invocation;

import java.io.Serializable;
import java.util.concurrent.Callable;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.mockito.invocation.InvocationFactory;

/* loaded from: classes3.dex */
public interface RealMethod extends Serializable {
    Object invoke() throws Throwable;

    boolean isInvokable();

    public enum IsIllegal implements RealMethod {
        INSTANCE;

        @Override // org.mockito.internal.invocation.RealMethod
        public boolean isInvokable() {
            return false;
        }

        @Override // org.mockito.internal.invocation.RealMethod
        public Object invoke() {
            throw new IllegalStateException();
        }
    }

    public static class FromCallable extends FromBehavior implements RealMethod {
        public FromCallable(final Callable<?> callable) {
            super(new InvocationFactory.RealMethodBehavior() { // from class: org.mockito.internal.invocation.RealMethod.FromCallable.1
                @Override // org.mockito.invocation.InvocationFactory.RealMethodBehavior
                public Object call() throws Throwable {
                    return callable.call();
                }
            });
        }
    }

    public static class FromBehavior implements RealMethod {
        private final InvocationFactory.RealMethodBehavior<?> behavior;

        @Override // org.mockito.internal.invocation.RealMethod
        public boolean isInvokable() {
            return true;
        }

        FromBehavior(InvocationFactory.RealMethodBehavior<?> realMethodBehavior) {
            this.behavior = realMethodBehavior;
        }

        @Override // org.mockito.internal.invocation.RealMethod
        public Object invoke() throws Throwable {
            try {
                return this.behavior.call();
            } catch (Throwable th) {
                new ConditionalStackTraceFilter().filter(th);
                throw th;
            }
        }
    }
}

package androidx.test.espresso.core.internal.deps.guava.util.concurrent;

import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.FluentFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* loaded from: classes5.dex */
class TrustedListenableFutureTask extends FluentFuture.TrustedFuture implements RunnableFuture {
    private volatile InterruptibleTask task;

    private final class TrustedFutureInterruptibleTask extends InterruptibleTask {
        private final Callable callable;

        TrustedFutureInterruptibleTask(Callable callable) {
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.InterruptibleTask
        void afterRanInterruptiblyFailure(Throwable th) {
            TrustedListenableFutureTask.this.setException(th);
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.InterruptibleTask
        void afterRanInterruptiblySuccess(Object obj) {
            TrustedListenableFutureTask.this.set(obj);
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.InterruptibleTask
        final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.InterruptibleTask
        Object runInterruptibly() throws Exception {
            return this.callable.call();
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.InterruptibleTask
        String toPendingString() {
            return this.callable.toString();
        }
    }

    TrustedListenableFutureTask(Callable callable) {
        this.task = new TrustedFutureInterruptibleTask(callable);
    }

    static TrustedListenableFutureTask create(Runnable runnable, Object obj) {
        return new TrustedListenableFutureTask(Executors.callable(runnable, obj));
    }

    @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture
    protected void afterDone() {
        InterruptibleTask interruptibleTask;
        super.afterDone();
        if (wasInterrupted() && (interruptibleTask = this.task) != null) {
            interruptibleTask.interruptTask();
        }
        this.task = null;
    }

    @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture
    protected String pendingToString() {
        InterruptibleTask interruptibleTask = this.task;
        return interruptibleTask != null ? "task=[" + interruptibleTask + "]" : super.pendingToString();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            interruptibleTask.run();
        }
        this.task = null;
    }

    static TrustedListenableFutureTask create(Callable callable) {
        return new TrustedListenableFutureTask(callable);
    }
}

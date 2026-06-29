package androidx.test.espresso.core.internal.deps.guava.util.concurrent;

import androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
public abstract class FluentFuture extends GwtFluentFutureCatchingSpecialization {

    static abstract class TrustedFuture extends FluentFuture implements AbstractFuture.Trusted {
        TrustedFuture() {
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
        public final void addListener(Runnable runnable, Executor executor) {
            super.addListener(runnable, executor);
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean cancel(boolean z) {
            return super.cancel(z);
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final Object get() throws ExecutionException, InterruptedException {
            return super.get();
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isCancelled() {
            return super.isCancelled();
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isDone() {
            return super.isDone();
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final Object get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
            return super.get(j, timeUnit);
        }
    }

    FluentFuture() {
    }
}

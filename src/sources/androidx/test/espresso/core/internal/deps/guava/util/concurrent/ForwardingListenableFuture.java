package androidx.test.espresso.core.internal.deps.guava.util.concurrent;

import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* loaded from: classes5.dex */
public abstract class ForwardingListenableFuture extends ForwardingFuture implements ListenableFuture {

    public static abstract class SimpleForwardingListenableFuture extends ForwardingListenableFuture {
        private final ListenableFuture delegate;

        protected SimpleForwardingListenableFuture(ListenableFuture listenableFuture) {
            this.delegate = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.ForwardingListenableFuture, androidx.test.espresso.core.internal.deps.guava.util.concurrent.ForwardingFuture, androidx.test.espresso.core.internal.deps.guava.collect.ForwardingObject
        public final ListenableFuture delegate() {
            return this.delegate;
        }
    }

    protected ForwardingListenableFuture() {
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        delegate().addListener(runnable, executor);
    }

    @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.ForwardingFuture, androidx.test.espresso.core.internal.deps.guava.collect.ForwardingObject
    protected abstract ListenableFuture delegate();

    @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.ForwardingFuture, androidx.test.espresso.core.internal.deps.guava.collect.ForwardingObject
    protected /* bridge */ /* synthetic */ Future delegate() {
        throw null;
    }
}

package androidx.test.espresso.core.internal.deps.guava.util.concurrent;

import androidx.test.espresso.core.internal.deps.guava.base.Function;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
abstract class AbstractTransformFuture extends FluentFuture.TrustedFuture implements Runnable {
    Object function;
    ListenableFuture inputFuture;

    private static final class TransformFuture extends AbstractTransformFuture {
        TransformFuture(ListenableFuture listenableFuture, Function function) {
            super(listenableFuture, function);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractTransformFuture
        public Object doTransform(Function function, Object obj) {
            return function.apply(obj);
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractTransformFuture
        void setResult(Object obj) {
            set(obj);
        }
    }

    AbstractTransformFuture(ListenableFuture listenableFuture, Object obj) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.function = Preconditions.checkNotNull(obj);
    }

    static ListenableFuture create(ListenableFuture listenableFuture, Function function, Executor executor) {
        Preconditions.checkNotNull(function);
        TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
        listenableFuture.addListener(transformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, transformFuture));
        return transformFuture;
    }

    @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture
    protected final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    abstract Object doTransform(Object obj, Object obj2) throws Exception;

    @Override // androidx.test.espresso.core.internal.deps.guava.util.concurrent.AbstractFuture
    protected String pendingToString() {
        ListenableFuture listenableFuture = this.inputFuture;
        Object obj = this.function;
        String strPendingToString = super.pendingToString();
        String str = listenableFuture != null ? "inputFuture=[" + listenableFuture + "], " : "";
        if (obj != null) {
            return str + "function=[" + obj + "]";
        }
        if (strPendingToString != null) {
            return str + strPendingToString;
        }
        return null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture listenableFuture = this.inputFuture;
        Object obj = this.function;
        if ((isCancelled() | (listenableFuture == null)) || (obj == null)) {
            return;
        }
        this.inputFuture = null;
        if (listenableFuture.isCancelled()) {
            setFuture(listenableFuture);
            return;
        }
        try {
            try {
                Object objDoTransform = doTransform(obj, Futures.getDone(listenableFuture));
                this.function = null;
                setResult(objDoTransform);
            } catch (Throwable th) {
                try {
                    Platform.restoreInterruptIfIsInterruptedException(th);
                    setException(th);
                } finally {
                    this.function = null;
                }
            }
        } catch (Error e) {
            setException(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e2) {
            setException(e2);
        } catch (ExecutionException e3) {
            setException(e3.getCause());
        }
    }

    abstract void setResult(Object obj);
}

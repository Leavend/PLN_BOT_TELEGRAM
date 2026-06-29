package androidx.test.espresso;

import androidx.test.espresso.core.internal.deps.guava.base.MoreObjects;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.util.concurrent.MoreExecutors;
import androidx.test.espresso.remote.NoRemoteEspressoInstanceException;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.logging.LogFactory;

/* loaded from: classes5.dex */
final class InteractionResultsHandler {
    private static final int LOCAL_OR_REMOTE_ERROR_PRIORITY = Integer.MAX_VALUE;
    private static final String TAG = "InteractionResultsHandl";

    private static class ExecutionResult<T> {
        private final Throwable failure;
        private final boolean priority;
        private final T result;
        private final boolean success;

        private ExecutionResult(T t, boolean z, Throwable th, boolean z2) {
            this.result = t;
            this.success = z;
            this.failure = th;
            this.priority = z2;
        }

        public static <T> ExecutionResult<T> error(Throwable th) {
            return error(th, false);
        }

        public static <T> ExecutionResult<T> success(T t) {
            return new ExecutionResult<>(t, true, null, true);
        }

        public Throwable getFailure() {
            Preconditions.checkState(!this.success);
            return this.failure;
        }

        public T getResult() {
            Preconditions.checkState(this.success);
            return this.result;
        }

        public boolean isPriority() {
            return this.priority;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues().add(LogFactory.PRIORITY_KEY, this.priority).add(FirebaseAnalytics.Param.SUCCESS, this.success).add("result", this.result).add("failure", this.failure).toString();
        }

        public static <T> ExecutionResult<T> error(Throwable th, boolean z) {
            return new ExecutionResult<>(null, false, th, z);
        }
    }

    private InteractionResultsHandler() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> ExecutionResult<T> adaptResult(Future<T> future) {
        try {
            Preconditions.checkState(future.isDone());
            return ExecutionResult.success(future.get());
        } catch (Error e) {
            return ExecutionResult.error(e);
        } catch (InterruptedException e2) {
            return ExecutionResult.error(e2);
        } catch (RuntimeException e3) {
            return ExecutionResult.error(e3);
        } catch (ExecutionException e4) {
            return ExecutionResult.error(e4, getPriority(e4) == Integer.MAX_VALUE);
        }
    }

    private static <T> T finalResult(ExecutionResult<T> executionResult) {
        if (executionResult.isSuccess()) {
            return executionResult.getResult();
        }
        Throwable failure = executionResult.getFailure();
        if (!(failure instanceof ExecutionException)) {
            if (failure instanceof InterruptedException) {
                throw new IllegalStateException("Interrupted while interacting remotely", failure);
            }
            throw new RuntimeException("Error interacting remotely", failure);
        }
        Throwable cause = failure.getCause();
        if (cause instanceof RuntimeException) {
            throw ((RuntimeException) cause);
        }
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        throw new RuntimeException("Unknown error during interactions", executionResult.getFailure());
    }

    static <T> T gatherAnyResult(List<ListenableFuture<T>> list) {
        return (T) gatherAnyResult(list, MoreExecutors.directExecutor());
    }

    static <T> T gatherAnyResult(List<ListenableFuture<T>> list, Executor executor) {
        Preconditions.checkNotNull(list);
        Preconditions.checkState(!list.isEmpty());
        int size = list.size();
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(size);
        for (final ListenableFuture<T> listenableFuture : list) {
            listenableFuture.addListener(new Runnable() { // from class: androidx.test.espresso.InteractionResultsHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    if (listenableFuture.isCancelled()) {
                        return;
                    }
                    linkedBlockingQueue.offer(InteractionResultsHandler.adaptResult(listenableFuture));
                }
            }, executor);
        }
        ExecutionResult executionResultPickResult = null;
        while (size != 0) {
            if (executionResultPickResult != null) {
                try {
                    try {
                        if (executionResultPickResult.isPriority()) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Interrupted while interacting", e);
                    }
                } finally {
                    Iterator<ListenableFuture<T>> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().cancel(true);
                    }
                }
            }
            size--;
            executionResultPickResult = pickResult(executionResultPickResult, (ExecutionResult) linkedBlockingQueue.take());
        }
        return (T) finalResult(executionResultPickResult);
    }

    private static int getPriority(Throwable th) {
        if (th == null) {
            return Integer.MIN_VALUE;
        }
        if (!(th instanceof ExecutionException)) {
            return -2147483647;
        }
        if (th.getCause() instanceof NoRemoteEspressoInstanceException) {
            return 0;
        }
        return th.getCause() instanceof NoActivityResumedException ? 1 : Integer.MAX_VALUE;
    }

    private static <T> ExecutionResult<T> pickResult(ExecutionResult<T> executionResult, ExecutionResult<T> executionResult2) {
        return executionResult2 == null ? executionResult : executionResult == null ? executionResult2 : executionResult.isSuccess() ? executionResult : (!executionResult2.isSuccess() && getPriority(executionResult.getFailure()) > getPriority(executionResult2.getFailure())) ? executionResult : executionResult2;
    }
}

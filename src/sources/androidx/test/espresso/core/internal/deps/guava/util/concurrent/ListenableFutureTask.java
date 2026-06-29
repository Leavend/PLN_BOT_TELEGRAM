package androidx.test.espresso.core.internal.deps.guava.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
public class ListenableFutureTask extends FutureTask implements ListenableFuture {
    private final ExecutionList executionList;

    ListenableFutureTask(Runnable runnable, Object obj) {
        super(runnable, obj);
        this.executionList = new ExecutionList();
    }

    public static ListenableFutureTask create(Runnable runnable, Object obj) {
        return new ListenableFutureTask(runnable, obj);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.executionList.add(runnable, executor);
    }

    @Override // java.util.concurrent.FutureTask
    protected void done() {
        this.executionList.execute();
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public Object get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        return nanos <= 2147483647999999999L ? super.get(j, timeUnit) : super.get(Math.min(nanos, 2147483647999999999L), TimeUnit.NANOSECONDS);
    }

    public static ListenableFutureTask create(Callable callable) {
        return new ListenableFutureTask(callable);
    }

    ListenableFutureTask(Callable callable) {
        super(callable);
        this.executionList = new ExecutionList();
    }
}

package androidx.test.espresso.core.internal.deps.guava.util.concurrent;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* loaded from: classes5.dex */
public interface ListeningExecutorService extends ExecutorService {
    ListenableFuture submit(Callable callable);
}

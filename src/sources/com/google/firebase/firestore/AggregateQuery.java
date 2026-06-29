package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;

/* loaded from: classes4.dex */
public class AggregateQuery {
    private final Query query;

    AggregateQuery(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return this.query;
    }

    public Task<AggregateQuerySnapshot> get(AggregateSource aggregateSource) {
        Preconditions.checkNotNull(aggregateSource, "AggregateSource must not be null");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.query.firestore.getClient().runCountQuery(this.query.query).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.AggregateQuery$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m6391lambda$get$0$comgooglefirebasefirestoreAggregateQuery(taskCompletionSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$get$0$com-google-firebase-firestore-AggregateQuery, reason: not valid java name */
    /* synthetic */ Object m6391lambda$get$0$comgooglefirebasefirestoreAggregateQuery(TaskCompletionSource taskCompletionSource, Task task) throws Exception {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(new AggregateQuerySnapshot(this, ((Long) task.getResult()).longValue()));
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AggregateQuery) {
            return this.query.equals(((AggregateQuery) obj).query);
        }
        return false;
    }

    public int hashCode() {
        return this.query.hashCode();
    }
}

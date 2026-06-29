package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
public class AggregateQuerySnapshot {
    private final long count;
    private final AggregateQuery query;

    AggregateQuerySnapshot(AggregateQuery aggregateQuery, long j) {
        Preconditions.checkNotNull(aggregateQuery);
        this.query = aggregateQuery;
        this.count = j;
    }

    public AggregateQuery getQuery() {
        return this.query;
    }

    public long getCount() {
        return this.count;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateQuerySnapshot)) {
            return false;
        }
        AggregateQuerySnapshot aggregateQuerySnapshot = (AggregateQuerySnapshot) obj;
        return this.count == aggregateQuerySnapshot.count && this.query.equals(aggregateQuerySnapshot.query);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.count), this.query);
    }
}

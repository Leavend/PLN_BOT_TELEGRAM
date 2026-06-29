package com.google.firebase.database.core.persistence;

import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.Context;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.UserWriteRecord;
import com.google.firebase.database.core.utilities.Clock;
import com.google.firebase.database.core.utilities.DefaultClock;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.CacheNode;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public class DefaultPersistenceManager implements PersistenceManager {
    private final CachePolicy cachePolicy;
    private final LogWrapper logger;
    private long serverCacheUpdatesSinceLastPruneCheck;
    private final PersistenceStorageEngine storageLayer;
    private final TrackedQueryManager trackedQueryManager;

    public DefaultPersistenceManager(Context context, PersistenceStorageEngine persistenceStorageEngine, CachePolicy cachePolicy) {
        this(context, persistenceStorageEngine, cachePolicy, new DefaultClock());
    }

    public DefaultPersistenceManager(Context context, PersistenceStorageEngine persistenceStorageEngine, CachePolicy cachePolicy, Clock clock) {
        this.serverCacheUpdatesSinceLastPruneCheck = 0L;
        this.storageLayer = persistenceStorageEngine;
        LogWrapper logger = context.getLogger("Persistence");
        this.logger = logger;
        this.trackedQueryManager = new TrackedQueryManager(persistenceStorageEngine, logger, clock);
        this.cachePolicy = cachePolicy;
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void saveUserOverwrite(Path path, Node node, long j) {
        this.storageLayer.saveUserOverwrite(path, node, j);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void saveUserMerge(Path path, CompoundWrite compoundWrite, long j) {
        this.storageLayer.saveUserMerge(path, compoundWrite, j);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void removeUserWrite(long j) {
        this.storageLayer.removeUserWrite(j);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void removeAllUserWrites() {
        this.storageLayer.removeAllUserWrites();
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void applyUserWriteToServerCache(Path path, Node node) {
        if (this.trackedQueryManager.hasActiveDefaultQuery(path)) {
            return;
        }
        this.storageLayer.overwriteServerCache(path, node);
        this.trackedQueryManager.ensureCompleteTrackedQuery(path);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void applyUserWriteToServerCache(Path path, CompoundWrite compoundWrite) {
        Iterator<Map.Entry<Path, Node>> it = compoundWrite.iterator();
        while (it.hasNext()) {
            Map.Entry<Path, Node> next = it.next();
            applyUserWriteToServerCache(path.child(next.getKey()), next.getValue());
        }
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public List<UserWriteRecord> loadUserWrites() {
        return this.storageLayer.loadUserWrites();
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public CacheNode serverCache(QuerySpec querySpec) {
        Set<ChildKey> knownCompleteChildren;
        boolean z;
        if (this.trackedQueryManager.isQueryComplete(querySpec)) {
            TrackedQuery trackedQueryFindTrackedQuery = this.trackedQueryManager.findTrackedQuery(querySpec);
            knownCompleteChildren = (querySpec.loadsAllData() || trackedQueryFindTrackedQuery == null || !trackedQueryFindTrackedQuery.complete) ? null : this.storageLayer.loadTrackedQueryKeys(trackedQueryFindTrackedQuery.f89id);
            z = true;
        } else {
            knownCompleteChildren = this.trackedQueryManager.getKnownCompleteChildren(querySpec.getPath());
            z = false;
        }
        Node nodeServerCache = this.storageLayer.serverCache(querySpec.getPath());
        if (knownCompleteChildren != null) {
            Node nodeEmpty = EmptyNode.Empty();
            for (ChildKey childKey : knownCompleteChildren) {
                nodeEmpty = nodeEmpty.updateImmediateChild(childKey, nodeServerCache.getImmediateChild(childKey));
            }
            return new CacheNode(IndexedNode.from(nodeEmpty, querySpec.getIndex()), z, true);
        }
        return new CacheNode(IndexedNode.from(nodeServerCache, querySpec.getIndex()), z, false);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void updateServerCache(QuerySpec querySpec, Node node) {
        if (querySpec.loadsAllData()) {
            this.storageLayer.overwriteServerCache(querySpec.getPath(), node);
        } else {
            this.storageLayer.mergeIntoServerCache(querySpec.getPath(), node);
        }
        setQueryComplete(querySpec);
        doPruneCheckAfterServerUpdate();
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void updateServerCache(Path path, CompoundWrite compoundWrite) {
        this.storageLayer.mergeIntoServerCache(path, compoundWrite);
        doPruneCheckAfterServerUpdate();
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void setQueryActive(QuerySpec querySpec) {
        this.trackedQueryManager.setQueryActive(querySpec);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void setQueryInactive(QuerySpec querySpec) {
        this.trackedQueryManager.setQueryInactive(querySpec);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void setQueryComplete(QuerySpec querySpec) {
        if (querySpec.loadsAllData()) {
            this.trackedQueryManager.setQueriesComplete(querySpec.getPath());
        } else {
            this.trackedQueryManager.setQueryCompleteIfExists(querySpec);
        }
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void setTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set) {
        Utilities.hardAssert(!querySpec.loadsAllData(), "We should only track keys for filtered queries.");
        TrackedQuery trackedQueryFindTrackedQuery = this.trackedQueryManager.findTrackedQuery(querySpec);
        Utilities.hardAssert(trackedQueryFindTrackedQuery != null && trackedQueryFindTrackedQuery.active, "We only expect tracked keys for currently-active queries.");
        this.storageLayer.saveTrackedQueryKeys(trackedQueryFindTrackedQuery.f89id, set);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public void updateTrackedQueryKeys(QuerySpec querySpec, Set<ChildKey> set, Set<ChildKey> set2) {
        Utilities.hardAssert(!querySpec.loadsAllData(), "We should only track keys for filtered queries.");
        TrackedQuery trackedQueryFindTrackedQuery = this.trackedQueryManager.findTrackedQuery(querySpec);
        Utilities.hardAssert(trackedQueryFindTrackedQuery != null && trackedQueryFindTrackedQuery.active, "We only expect tracked keys for currently-active queries.");
        this.storageLayer.updateTrackedQueryKeys(trackedQueryFindTrackedQuery.f89id, set, set2);
    }

    @Override // com.google.firebase.database.core.persistence.PersistenceManager
    public <T> T runInTransaction(Callable<T> callable) {
        this.storageLayer.beginTransaction();
        try {
            T tCall = callable.call();
            this.storageLayer.setTransactionSuccessful();
            return tCall;
        } finally {
        }
    }

    private void doPruneCheckAfterServerUpdate() {
        long j = this.serverCacheUpdatesSinceLastPruneCheck + 1;
        this.serverCacheUpdatesSinceLastPruneCheck = j;
        if (this.cachePolicy.shouldCheckCacheSize(j)) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Reached prune check threshold.", new Object[0]);
            }
            this.serverCacheUpdatesSinceLastPruneCheck = 0L;
            long jServerCacheEstimatedSizeInBytes = this.storageLayer.serverCacheEstimatedSizeInBytes();
            if (this.logger.logsDebug()) {
                this.logger.debug("Cache size: " + jServerCacheEstimatedSizeInBytes, new Object[0]);
            }
            boolean z = true;
            while (z && this.cachePolicy.shouldPrune(jServerCacheEstimatedSizeInBytes, this.trackedQueryManager.countOfPrunableQueries())) {
                PruneForest pruneForestPruneOldQueries = this.trackedQueryManager.pruneOldQueries(this.cachePolicy);
                if (pruneForestPruneOldQueries.prunesAnything()) {
                    this.storageLayer.pruneCache(Path.getEmptyPath(), pruneForestPruneOldQueries);
                } else {
                    z = false;
                }
                jServerCacheEstimatedSizeInBytes = this.storageLayer.serverCacheEstimatedSizeInBytes();
                if (this.logger.logsDebug()) {
                    this.logger.debug("Cache size after prune: " + jServerCacheEstimatedSizeInBytes, new Object[0]);
                }
            }
        }
    }
}

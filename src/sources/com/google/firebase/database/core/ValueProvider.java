package com.google.firebase.database.core;

import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;

/* loaded from: classes4.dex */
abstract class ValueProvider {
    public abstract ValueProvider getImmediateChild(ChildKey childKey);

    public abstract Node node();

    ValueProvider() {
    }

    public static class ExistingValueProvider extends ValueProvider {
        private final Node node;

        ExistingValueProvider(Node node) {
            this.node = node;
        }

        @Override // com.google.firebase.database.core.ValueProvider
        public ValueProvider getImmediateChild(ChildKey childKey) {
            return new ExistingValueProvider(this.node.getImmediateChild(childKey));
        }

        @Override // com.google.firebase.database.core.ValueProvider
        public Node node() {
            return this.node;
        }
    }

    public static class DeferredValueProvider extends ValueProvider {
        private final Path path;
        private final SyncTree syncTree;

        DeferredValueProvider(SyncTree syncTree, Path path) {
            this.syncTree = syncTree;
            this.path = path;
        }

        @Override // com.google.firebase.database.core.ValueProvider
        public ValueProvider getImmediateChild(ChildKey childKey) {
            return new DeferredValueProvider(this.syncTree, this.path.child(childKey));
        }

        @Override // com.google.firebase.database.core.ValueProvider
        public Node node() {
            return this.syncTree.calcCompleteEventCache(this.path, new ArrayList());
        }
    }
}

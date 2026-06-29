package androidx.test.espresso.util;

import android.view.View;
import android.view.ViewGroup;
import androidx.test.espresso.core.internal.deps.guava.base.Function;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.collect.AbstractIterator;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;
import androidx.test.espresso.core.internal.deps.guava.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes5.dex */
public final class TreeIterables {
    private static final TreeViewer<View> VIEW_TREE_VIEWER = new ViewTreeViewer();

    static class DistanceRecordingTreeViewer<T> implements TreeViewer<T> {
        private final TreeViewer<T> delegateViewer;
        private final Map<T, Integer> nodeToDistance = Maps.newHashMap();
        private final T root;

        DistanceRecordingTreeViewer(T t, TreeViewer<T> treeViewer) {
            this.root = (T) Preconditions.checkNotNull(t);
            this.delegateViewer = (TreeViewer) Preconditions.checkNotNull(treeViewer);
        }

        @Override // androidx.test.espresso.util.TreeIterables.TreeViewer
        public Collection<T> children(T t) {
            if (t == this.root) {
                this.nodeToDistance.put(t, 0);
            }
            int distance = getDistance(t) + 1;
            Collection<T> collectionChildren = this.delegateViewer.children(t);
            Iterator<T> it = collectionChildren.iterator();
            while (it.hasNext()) {
                this.nodeToDistance.put(it.next(), Integer.valueOf(distance));
            }
            return collectionChildren;
        }

        int getDistance(T t) {
            return ((Integer) Preconditions.checkNotNull(this.nodeToDistance.get(t), "Never seen %s before", t)).intValue();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    private static abstract class TraversalStrategy {
        public static final TraversalStrategy BREADTH_FIRST = new AnonymousClass1("BREADTH_FIRST", 0);
        public static final TraversalStrategy DEPTH_FIRST = new AnonymousClass2("DEPTH_FIRST", 1);
        private static final /* synthetic */ TraversalStrategy[] $VALUES = $values();

        /* renamed from: androidx.test.espresso.util.TreeIterables$TraversalStrategy$1, reason: invalid class name */
        enum AnonymousClass1 extends TraversalStrategy {
            private AnonymousClass1(String str, int i) {
                super(str, i);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.test.espresso.util.TreeIterables.TraversalStrategy
            <T> void combineNewChildren(LinkedList<T> linkedList, Collection<T> collection) {
                linkedList.addAll(collection);
            }
        }

        /* renamed from: androidx.test.espresso.util.TreeIterables$TraversalStrategy$2, reason: invalid class name */
        enum AnonymousClass2 extends TraversalStrategy {
            private AnonymousClass2(String str, int i) {
                super(str, i);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.test.espresso.util.TreeIterables.TraversalStrategy
            <T> void combineNewChildren(LinkedList<T> linkedList, Collection<T> collection) {
                linkedList.addAll(0, collection);
            }
        }

        private static /* synthetic */ TraversalStrategy[] $values() {
            return new TraversalStrategy[]{BREADTH_FIRST, DEPTH_FIRST};
        }

        private TraversalStrategy(String str, int i) {
        }

        public static TraversalStrategy valueOf(String str) {
            return (TraversalStrategy) Enum.valueOf(TraversalStrategy.class, str);
        }

        public static TraversalStrategy[] values() {
            return (TraversalStrategy[]) $VALUES.clone();
        }

        abstract <T> void combineNewChildren(LinkedList<T> linkedList, Collection<T> collection);

        <T> T next(LinkedList<T> linkedList) {
            return linkedList.removeFirst();
        }
    }

    private static class TreeTraversalIterable<T> implements Iterable<T> {
        private final T root;
        private final TraversalStrategy traversalStrategy;
        private final TreeViewer<T> treeViewer;

        private TreeTraversalIterable(T t, TraversalStrategy traversalStrategy, TreeViewer<T> treeViewer) {
            this.root = (T) Preconditions.checkNotNull(t);
            this.traversalStrategy = (TraversalStrategy) Preconditions.checkNotNull(traversalStrategy);
            this.treeViewer = (TreeViewer) Preconditions.checkNotNull(treeViewer);
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            final LinkedList linkedListNewLinkedList = Lists.newLinkedList();
            linkedListNewLinkedList.add(this.root);
            return new AbstractIterator<T>() { // from class: androidx.test.espresso.util.TreeIterables.TreeTraversalIterable.1
                @Override // androidx.test.espresso.core.internal.deps.guava.collect.AbstractIterator
                public T computeNext() {
                    if (linkedListNewLinkedList.isEmpty()) {
                        return endOfData();
                    }
                    T t = (T) Preconditions.checkNotNull(TreeTraversalIterable.this.traversalStrategy.next(linkedListNewLinkedList), "Null items not allowed!");
                    TreeTraversalIterable.this.traversalStrategy.combineNewChildren(linkedListNewLinkedList, TreeTraversalIterable.this.treeViewer.children(t));
                    return t;
                }
            };
        }
    }

    interface TreeViewer<T> {
        Collection<T> children(T t);
    }

    public static class ViewAndDistance {
        private final int distanceFromRoot;
        private final View view;

        private ViewAndDistance(View view, int i) {
            this.view = view;
            this.distanceFromRoot = i;
        }

        public int getDistanceFromRoot() {
            return this.distanceFromRoot;
        }

        public View getView() {
            return this.view;
        }
    }

    static class ViewTreeViewer implements TreeViewer<View> {
        ViewTreeViewer() {
        }

        @Override // androidx.test.espresso.util.TreeIterables.TreeViewer
        public Collection<View> children(View view) {
            Preconditions.checkNotNull(view);
            if (!(view instanceof ViewGroup)) {
                return Collections.emptyList();
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            for (int i = 0; i < childCount; i++) {
                arrayListNewArrayList.add(viewGroup.getChildAt(i));
            }
            return arrayListNewArrayList;
        }
    }

    private TreeIterables() {
    }

    static <T> Iterable<T> breadthFirstTraversal(T t, TreeViewer<T> treeViewer) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(treeViewer);
        return new TreeTraversalIterable(t, TraversalStrategy.BREADTH_FIRST, treeViewer);
    }

    public static Iterable<View> breadthFirstViewTraversal(View view) {
        return breadthFirstTraversal(view, VIEW_TREE_VIEWER);
    }

    static <T> Iterable<T> depthFirstTraversal(T t, TreeViewer<T> treeViewer) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(treeViewer);
        return new TreeTraversalIterable(t, TraversalStrategy.DEPTH_FIRST, treeViewer);
    }

    public static Iterable<View> depthFirstViewTraversal(View view) {
        return depthFirstTraversal(view, VIEW_TREE_VIEWER);
    }

    public static Iterable<ViewAndDistance> depthFirstViewTraversalWithDistance(View view) {
        final DistanceRecordingTreeViewer distanceRecordingTreeViewer = new DistanceRecordingTreeViewer(view, VIEW_TREE_VIEWER);
        return Iterables.transform(depthFirstTraversal(view, distanceRecordingTreeViewer), new Function<View, ViewAndDistance>() { // from class: androidx.test.espresso.util.TreeIterables.1
            @Override // androidx.test.espresso.core.internal.deps.guava.base.Function
            public ViewAndDistance apply(View view2) {
                return new ViewAndDistance(view2, distanceRecordingTreeViewer.getDistance(view2));
            }
        });
    }
}

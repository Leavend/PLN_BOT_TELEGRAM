package com.google.firebase.database.core;

import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class CompoundWrite implements Iterable<Map.Entry<Path, Node>> {
    private static final CompoundWrite EMPTY = new CompoundWrite(new ImmutableTree(null));
    private final ImmutableTree<Node> writeTree;

    private CompoundWrite(ImmutableTree<Node> immutableTree) {
        this.writeTree = immutableTree;
    }

    public static CompoundWrite emptyWrite() {
        return EMPTY;
    }

    public static CompoundWrite fromValue(Map<String, Object> map) {
        ImmutableTree immutableTreeEmptyInstance = ImmutableTree.emptyInstance();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            immutableTreeEmptyInstance = immutableTreeEmptyInstance.setTree(new Path(entry.getKey()), new ImmutableTree(NodeUtilities.NodeFromJSON(entry.getValue())));
        }
        return new CompoundWrite(immutableTreeEmptyInstance);
    }

    public static CompoundWrite fromChildMerge(Map<ChildKey, Node> map) {
        ImmutableTree immutableTreeEmptyInstance = ImmutableTree.emptyInstance();
        for (Map.Entry<ChildKey, Node> entry : map.entrySet()) {
            immutableTreeEmptyInstance = immutableTreeEmptyInstance.setTree(new Path(entry.getKey()), new ImmutableTree(entry.getValue()));
        }
        return new CompoundWrite(immutableTreeEmptyInstance);
    }

    public static CompoundWrite fromPathMerge(Map<Path, Node> map) {
        ImmutableTree immutableTreeEmptyInstance = ImmutableTree.emptyInstance();
        for (Map.Entry<Path, Node> entry : map.entrySet()) {
            immutableTreeEmptyInstance = immutableTreeEmptyInstance.setTree(entry.getKey(), new ImmutableTree(entry.getValue()));
        }
        return new CompoundWrite(immutableTreeEmptyInstance);
    }

    public CompoundWrite addWrite(Path path, Node node) {
        if (path.isEmpty()) {
            return new CompoundWrite(new ImmutableTree(node));
        }
        Path pathFindRootMostPathWithValue = this.writeTree.findRootMostPathWithValue(path);
        if (pathFindRootMostPathWithValue != null) {
            Path relative = Path.getRelative(pathFindRootMostPathWithValue, path);
            Node node2 = this.writeTree.get(pathFindRootMostPathWithValue);
            ChildKey back = relative.getBack();
            if (back != null && back.isPriorityChildName() && node2.getChild(relative.getParent()).isEmpty()) {
                return this;
            }
            return new CompoundWrite(this.writeTree.set(pathFindRootMostPathWithValue, node2.updateChild(relative, node)));
        }
        return new CompoundWrite(this.writeTree.setTree(path, new ImmutableTree<>(node)));
    }

    public CompoundWrite addWrite(ChildKey childKey, Node node) {
        return addWrite(new Path(childKey), node);
    }

    public CompoundWrite addWrites(final Path path, CompoundWrite compoundWrite) {
        return (CompoundWrite) compoundWrite.writeTree.fold(this, new ImmutableTree.TreeVisitor<Node, CompoundWrite>() { // from class: com.google.firebase.database.core.CompoundWrite.1
            @Override // com.google.firebase.database.core.utilities.ImmutableTree.TreeVisitor
            public CompoundWrite onNodeValue(Path path2, Node node, CompoundWrite compoundWrite2) {
                return compoundWrite2.addWrite(path.child(path2), node);
            }
        });
    }

    public CompoundWrite removeWrite(Path path) {
        if (path.isEmpty()) {
            return EMPTY;
        }
        return new CompoundWrite(this.writeTree.setTree(path, ImmutableTree.emptyInstance()));
    }

    public boolean hasCompleteWrite(Path path) {
        return getCompleteNode(path) != null;
    }

    public Node rootWrite() {
        return this.writeTree.getValue();
    }

    public Node getCompleteNode(Path path) {
        Path pathFindRootMostPathWithValue = this.writeTree.findRootMostPathWithValue(path);
        if (pathFindRootMostPathWithValue != null) {
            return this.writeTree.get(pathFindRootMostPathWithValue).getChild(Path.getRelative(pathFindRootMostPathWithValue, path));
        }
        return null;
    }

    public List<NamedNode> getCompleteChildren() {
        ArrayList arrayList = new ArrayList();
        if (this.writeTree.getValue() != null) {
            for (NamedNode namedNode : this.writeTree.getValue()) {
                arrayList.add(new NamedNode(namedNode.getName(), namedNode.getNode()));
            }
        } else {
            Iterator<Map.Entry<ChildKey, ImmutableTree<Node>>> it = this.writeTree.getChildren().iterator();
            while (it.hasNext()) {
                Map.Entry<ChildKey, ImmutableTree<Node>> next = it.next();
                ImmutableTree<Node> value = next.getValue();
                if (value.getValue() != null) {
                    arrayList.add(new NamedNode(next.getKey(), value.getValue()));
                }
            }
        }
        return arrayList;
    }

    public CompoundWrite childCompoundWrite(Path path) {
        if (path.isEmpty()) {
            return this;
        }
        Node completeNode = getCompleteNode(path);
        if (completeNode != null) {
            return new CompoundWrite(new ImmutableTree(completeNode));
        }
        return new CompoundWrite(this.writeTree.subtree(path));
    }

    public Map<ChildKey, CompoundWrite> childCompoundWrites() {
        HashMap map = new HashMap();
        Iterator<Map.Entry<ChildKey, ImmutableTree<Node>>> it = this.writeTree.getChildren().iterator();
        while (it.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Node>> next = it.next();
            map.put(next.getKey(), new CompoundWrite(next.getValue()));
        }
        return map;
    }

    public boolean isEmpty() {
        return this.writeTree.isEmpty();
    }

    private Node applySubtreeWrite(Path path, ImmutableTree<Node> immutableTree, Node node) {
        if (immutableTree.getValue() != null) {
            return node.updateChild(path, immutableTree.getValue());
        }
        Iterator<Map.Entry<ChildKey, ImmutableTree<Node>>> it = immutableTree.getChildren().iterator();
        Node value = null;
        while (it.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Node>> next = it.next();
            ImmutableTree<Node> value2 = next.getValue();
            ChildKey key = next.getKey();
            if (key.isPriorityChildName()) {
                Utilities.hardAssert(value2.getValue() != null, "Priority writes must always be leaf nodes");
                value = value2.getValue();
            } else {
                node = applySubtreeWrite(path.child(key), value2, node);
            }
        }
        return (node.getChild(path).isEmpty() || value == null) ? node : node.updateChild(path.child(ChildKey.getPriorityKey()), value);
    }

    public Node apply(Node node) {
        return applySubtreeWrite(Path.getEmptyPath(), this.writeTree, node);
    }

    public Map<String, Object> getValue(final boolean z) {
        final HashMap map = new HashMap();
        this.writeTree.foreach(new ImmutableTree.TreeVisitor<Node, Void>() { // from class: com.google.firebase.database.core.CompoundWrite.2
            @Override // com.google.firebase.database.core.utilities.ImmutableTree.TreeVisitor
            public Void onNodeValue(Path path, Node node, Void r4) {
                map.put(path.wireFormat(), node.getValue(z));
                return null;
            }
        });
        return map;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<Path, Node>> iterator() {
        return this.writeTree.iterator();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((CompoundWrite) obj).getValue(true).equals(getValue(true));
    }

    public int hashCode() {
        return getValue(true).hashCode();
    }

    public String toString() {
        return "CompoundWrite{" + getValue(true).toString() + "}";
    }
}

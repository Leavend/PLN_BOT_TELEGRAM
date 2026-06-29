package com.google.firebase.database.core;

import com.google.firebase.database.core.ValueProvider;
import com.google.firebase.database.core.utilities.Clock;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.ChildrenNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.snapshot.PriorityUtilities;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class ServerValues {
    public static final String NAME_OP_INCREMENT = "increment";
    public static final String NAME_OP_TIMESTAMP = "timestamp";
    public static final String NAME_SUBKEY_SERVERVALUE = ".sv";

    public static Map<String, Object> generateServerValues(Clock clock) {
        HashMap map = new HashMap();
        map.put(NAME_OP_TIMESTAMP, Long.valueOf(clock.millis()));
        return map;
    }

    public static Object resolveDeferredLeafValue(Object obj, ValueProvider valueProvider, Map<String, Object> map) {
        Object objResolveComplexDeferredValue;
        if (!(obj instanceof Map)) {
            return obj;
        }
        Map map2 = (Map) obj;
        if (!map2.containsKey(NAME_SUBKEY_SERVERVALUE)) {
            return obj;
        }
        Object obj2 = map2.get(NAME_SUBKEY_SERVERVALUE);
        if (obj2 instanceof String) {
            objResolveComplexDeferredValue = resolveScalarDeferredValue((String) obj2, map);
        } else {
            objResolveComplexDeferredValue = obj2 instanceof Map ? resolveComplexDeferredValue((Map) obj2, valueProvider, map) : null;
        }
        return objResolveComplexDeferredValue == null ? obj : objResolveComplexDeferredValue;
    }

    static Object resolveScalarDeferredValue(String str, Map<String, Object> map) {
        if (NAME_OP_TIMESTAMP.equals(str) && map.containsKey(str)) {
            return map.get(str);
        }
        return null;
    }

    static Object resolveComplexDeferredValue(Map<String, Object> map, ValueProvider valueProvider, Map<String, Object> map2) {
        if (!map.containsKey(NAME_OP_INCREMENT)) {
            return null;
        }
        Object obj = map.get(NAME_OP_INCREMENT);
        if (!(obj instanceof Number)) {
            return null;
        }
        Number number = (Number) obj;
        Node node = valueProvider.node();
        if (!node.isLeafNode() || !(node.getValue() instanceof Number)) {
            return number;
        }
        Number number2 = (Number) node.getValue();
        if (canBeRepresentedAsLong(number) && canBeRepresentedAsLong(number2)) {
            long jLongValue = number.longValue();
            long jLongValue2 = number2.longValue();
            long j = jLongValue + jLongValue2;
            if (((jLongValue ^ j) & (jLongValue2 ^ j)) >= 0) {
                return Long.valueOf(j);
            }
        }
        return Double.valueOf(number.doubleValue() + number2.doubleValue());
    }

    public static Node resolveDeferredValueSnapshot(Node node, Node node2, Map<String, Object> map) {
        return resolveDeferredValueSnapshot(node, new ValueProvider.ExistingValueProvider(node2), map);
    }

    public static Node resolveDeferredValueSnapshot(Node node, SyncTree syncTree, Path path, Map<String, Object> map) {
        return resolveDeferredValueSnapshot(node, new ValueProvider.DeferredValueProvider(syncTree, path), map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Node resolveDeferredValueSnapshot(Node node, final ValueProvider valueProvider, final Map<String, Object> map) {
        Object value = node.getPriority().getValue();
        Object objResolveDeferredLeafValue = resolveDeferredLeafValue(value, valueProvider.getImmediateChild(ChildKey.fromString(".priority")), map);
        if (node.isLeafNode()) {
            Object objResolveDeferredLeafValue2 = resolveDeferredLeafValue(node.getValue(), valueProvider, map);
            return (objResolveDeferredLeafValue2.equals(node.getValue()) && Utilities.equals(objResolveDeferredLeafValue, value)) ? node : NodeUtilities.NodeFromJSON(objResolveDeferredLeafValue2, PriorityUtilities.parsePriority(objResolveDeferredLeafValue));
        }
        if (node.isEmpty()) {
            return node;
        }
        ChildrenNode childrenNode = (ChildrenNode) node;
        final SnapshotHolder snapshotHolder = new SnapshotHolder(childrenNode);
        childrenNode.forEachChild(new ChildrenNode.ChildVisitor() { // from class: com.google.firebase.database.core.ServerValues.1
            @Override // com.google.firebase.database.snapshot.ChildrenNode.ChildVisitor
            public void visitChild(ChildKey childKey, Node node2) {
                Node nodeResolveDeferredValueSnapshot = ServerValues.resolveDeferredValueSnapshot(node2, valueProvider.getImmediateChild(childKey), (Map<String, Object>) map);
                if (nodeResolveDeferredValueSnapshot != node2) {
                    snapshotHolder.update(new Path(childKey.asString()), nodeResolveDeferredValueSnapshot);
                }
            }
        });
        if (!snapshotHolder.getRootNode().getPriority().equals(objResolveDeferredLeafValue)) {
            return snapshotHolder.getRootNode().updatePriority(PriorityUtilities.parsePriority(objResolveDeferredLeafValue));
        }
        return snapshotHolder.getRootNode();
    }

    public static CompoundWrite resolveDeferredValueMerge(CompoundWrite compoundWrite, SyncTree syncTree, Path path, Map<String, Object> map) {
        CompoundWrite compoundWriteEmptyWrite = CompoundWrite.emptyWrite();
        Iterator<Map.Entry<Path, Node>> it = compoundWrite.iterator();
        while (it.hasNext()) {
            Map.Entry<Path, Node> next = it.next();
            compoundWriteEmptyWrite = compoundWriteEmptyWrite.addWrite(next.getKey(), resolveDeferredValueSnapshot(next.getValue(), new ValueProvider.DeferredValueProvider(syncTree, path.child(next.getKey())), map));
        }
        return compoundWriteEmptyWrite;
    }

    private static boolean canBeRepresentedAsLong(Number number) {
        return ((number instanceof Double) || (number instanceof Float)) ? false : true;
    }
}

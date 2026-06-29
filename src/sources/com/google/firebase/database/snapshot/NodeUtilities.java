package com.google.firebase.database.snapshot;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.core.ServerValues;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class NodeUtilities {
    public static Node NodeFromJSON(Object obj) throws DatabaseException {
        return NodeFromJSON(obj, PriorityUtilities.NullPriority());
    }

    public static Node NodeFromJSON(Object obj, Node node) throws DatabaseException {
        HashMap map;
        try {
            if (obj instanceof Map) {
                Map map2 = (Map) obj;
                if (map2.containsKey(".priority")) {
                    node = PriorityUtilities.parsePriority(map2.get(".priority"));
                }
                if (map2.containsKey(".value")) {
                    obj = map2.get(".value");
                }
            }
            if (obj == null) {
                return EmptyNode.Empty();
            }
            if (obj instanceof String) {
                return new StringNode((String) obj, node);
            }
            if (obj instanceof Long) {
                return new LongNode((Long) obj, node);
            }
            if (obj instanceof Integer) {
                return new LongNode(Long.valueOf(((Integer) obj).intValue()), node);
            }
            if (obj instanceof Double) {
                return new DoubleNode((Double) obj, node);
            }
            if (obj instanceof Boolean) {
                return new BooleanNode((Boolean) obj, node);
            }
            if (!(obj instanceof Map) && !(obj instanceof List)) {
                throw new DatabaseException("Failed to parse node with class " + obj.getClass().toString());
            }
            if (obj instanceof Map) {
                Map map3 = (Map) obj;
                if (map3.containsKey(ServerValues.NAME_SUBKEY_SERVERVALUE)) {
                    return new DeferredValueNode(map3, node);
                }
                map = new HashMap(map3.size());
                for (String str : map3.keySet()) {
                    if (!str.startsWith(".")) {
                        Node nodeNodeFromJSON = NodeFromJSON(map3.get(str));
                        if (!nodeNodeFromJSON.isEmpty()) {
                            map.put(ChildKey.fromString(str), nodeNodeFromJSON);
                        }
                    }
                }
            } else {
                List list = (List) obj;
                map = new HashMap(list.size());
                for (int i = 0; i < list.size(); i++) {
                    String str2 = "" + i;
                    Node nodeNodeFromJSON2 = NodeFromJSON(list.get(i));
                    if (!nodeNodeFromJSON2.isEmpty()) {
                        map.put(ChildKey.fromString(str2), nodeNodeFromJSON2);
                    }
                }
            }
            if (map.isEmpty()) {
                return EmptyNode.Empty();
            }
            return new ChildrenNode(ImmutableSortedMap.Builder.fromMap(map, ChildrenNode.NAME_ONLY_COMPARATOR), node);
        } catch (ClassCastException e) {
            throw new DatabaseException("Failed to parse node", e);
        }
    }

    public static int nameAndPriorityCompare(ChildKey childKey, Node node, ChildKey childKey2, Node node2) {
        int iCompareTo = node.compareTo(node2);
        return iCompareTo != 0 ? iCompareTo : childKey.compareTo(childKey2);
    }
}

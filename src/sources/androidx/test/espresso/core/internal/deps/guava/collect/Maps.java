package androidx.test.espresso.core.internal.deps.guava.collect;

import androidx.test.espresso.core.internal.deps.guava.base.Function;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class Maps {

    /* renamed from: androidx.test.espresso.core.internal.deps.guava.collect.Maps$1, reason: invalid class name */
    class AnonymousClass1 extends TransformedIterator {
        @Override // androidx.test.espresso.core.internal.deps.guava.collect.TransformedIterator
        /* bridge */ /* synthetic */ Object transform(Object obj) {
            throw null;
        }
    }

    private enum EntryFunction implements Function {
        KEY { // from class: androidx.test.espresso.core.internal.deps.guava.collect.Maps.EntryFunction.1
            @Override // androidx.test.espresso.core.internal.deps.guava.base.Function
            public Object apply(Map.Entry entry) {
                return entry.getKey();
            }
        },
        VALUE { // from class: androidx.test.espresso.core.internal.deps.guava.collect.Maps.EntryFunction.2
            @Override // androidx.test.espresso.core.internal.deps.guava.base.Function
            public Object apply(Map.Entry entry) {
                return entry.getValue();
            }
        };

        /* synthetic */ EntryFunction(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static HashMap newHashMap() {
        return new HashMap();
    }

    static String toStringImpl(Map map) {
        StringBuilder sbAppend = Collections2.newStringBuilderForCollection(map.size()).append('{');
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            if (!z) {
                sbAppend.append(", ");
            }
            sbAppend.append(entry.getKey()).append('=').append(entry.getValue());
            z = false;
        }
        return sbAppend.append('}').toString();
    }

    static Function valueFunction() {
        return EntryFunction.VALUE;
    }

    static boolean equalsImpl(Map map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }
}

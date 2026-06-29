package androidx.compose.runtime.collection;

import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: IdentityArrayMap.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0019J\u0012\u0010\u001a\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0002J\"\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001d\u001a\u00020\u0005H\u0002JA\u0010\u001e\u001a\u00020\u001526\u0010\u001f\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00150 H\u0086\bJ\u0018\u0010$\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0018\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010%J\u0006\u0010&\u001a\u00020\u0017J\u0006\u0010'\u001a\u00020\u0017J\u0015\u0010(\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0018\u001a\u00028\u0000¢\u0006\u0002\u0010%JA\u0010)\u001a\u00020\u001526\u0010\u001f\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00170 H\u0086\bJ,\u0010*\u001a\u00020\u00152!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00170+H\u0086\bJ\u001e\u0010,\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0002\u0010-R0\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR0\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0011\u0010\u000b¨\u0006."}, d2 = {"Landroidx/compose/runtime/collection/IdentityArrayMap;", "Key", "", "Value", "capacity", "", "(I)V", "<set-?>", "", UserMetadata.KEYDATA_FILENAME, "getKeys", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "size", "getSize", "()I", "values", "getValues", "asMap", "", "clear", "", "contains", "", DatabaseFileArchive.COLUMN_KEY, "(Ljava/lang/Object;)Z", "find", "findExactIndex", "midIndex", "keyHash", "forEach", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "remove", "removeIf", "removeValueIf", "Lkotlin/Function1;", "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class IdentityArrayMap<Key, Value> {
    public static final int $stable = 8;
    private Object[] keys;
    private int size;
    private Object[] values;

    public IdentityArrayMap() {
        this(0, 1, null);
    }

    public IdentityArrayMap(int i) {
        this.keys = new Object[i];
        this.values = new Object[i];
    }

    public /* synthetic */ IdentityArrayMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 16 : i);
    }

    public final Object[] getKeys() {
        return this.keys;
    }

    public final Object[] getValues() {
        return this.values;
    }

    public final int getSize() {
        return this.size;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isNotEmpty() {
        return this.size > 0;
    }

    public final boolean contains(Key key) {
        return find(key) >= 0;
    }

    public final Value get(Key key) {
        int iFind = find(key);
        if (iFind >= 0) {
            return (Value) this.values[iFind];
        }
        return null;
    }

    public final void set(Key key, Value value) {
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
        int i = this.size;
        int iFind = find(key);
        if (iFind >= 0) {
            objArr2[iFind] = value;
            return;
        }
        int i2 = -(iFind + 1);
        boolean z = i == objArr.length;
        Object[] objArr3 = z ? new Object[i * 2] : objArr;
        int i3 = i2 + 1;
        ArraysKt.copyInto(objArr, objArr3, i3, i2, i);
        if (z) {
            ArraysKt.copyInto$default(objArr, objArr3, 0, 0, i2, 6, (Object) null);
        }
        objArr3[i2] = key;
        this.keys = objArr3;
        Object[] objArr4 = z ? new Object[i * 2] : objArr2;
        ArraysKt.copyInto(objArr2, objArr4, i3, i2, i);
        if (z) {
            ArraysKt.copyInto$default(objArr2, objArr4, 0, 0, i2, 6, (Object) null);
        }
        objArr4[i2] = value;
        this.values = objArr4;
        this.size++;
    }

    public final Value remove(Key key) {
        int iFind = find(key);
        if (iFind < 0) {
            return null;
        }
        Object[] objArr = this.values;
        Value value = (Value) objArr[iFind];
        int i = this.size;
        Object[] objArr2 = this.keys;
        int i2 = iFind + 1;
        ArraysKt.copyInto(objArr2, objArr2, iFind, i2, i);
        ArraysKt.copyInto(objArr, objArr, iFind, i2, i);
        int i3 = i - 1;
        objArr2[i3] = null;
        objArr[i3] = null;
        this.size = i3;
        return value;
    }

    public final void clear() {
        this.size = 0;
        ArraysKt.fill$default(this.keys, (Object) null, 0, 0, 6, (Object) null);
        ArraysKt.fill$default(this.values, (Object) null, 0, 0, 6, (Object) null);
    }

    public final void removeIf(Function2<? super Key, ? super Value, Boolean> block) {
        int size = getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = getKeys()[i2];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
            if (!block.invoke(obj, getValues()[i2]).booleanValue()) {
                if (i != i2) {
                    getKeys()[i] = obj;
                    getValues()[i] = getValues()[i2];
                }
                i++;
            }
        }
        if (getSize() > i) {
            int size2 = getSize();
            for (int i3 = i; i3 < size2; i3++) {
                getKeys()[i3] = null;
                getValues()[i3] = null;
            }
            this.size = i;
        }
    }

    public final void forEach(Function2<? super Key, ? super Value, Unit> block) {
        int size = getSize();
        for (int i = 0; i < size; i++) {
            Object obj = getKeys()[i];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
            block.invoke(obj, getValues()[i]);
        }
    }

    private final int find(Object key) {
        int iIdentityHashCode = ActualJvm_jvmKt.identityHashCode(key);
        int i = this.size - 1;
        Object[] objArr = this.keys;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj = objArr[i3];
            int iIdentityHashCode2 = ActualJvm_jvmKt.identityHashCode(obj);
            if (iIdentityHashCode2 < iIdentityHashCode) {
                i2 = i3 + 1;
            } else {
                if (iIdentityHashCode2 <= iIdentityHashCode) {
                    return key == obj ? i3 : findExactIndex(i3, key, iIdentityHashCode);
                }
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    private final int findExactIndex(int midIndex, Object key, int keyHash) {
        Object obj;
        Object[] objArr = this.keys;
        int i = this.size;
        for (int i2 = midIndex - 1; -1 < i2; i2--) {
            Object obj2 = objArr[i2];
            if (obj2 == key) {
                return i2;
            }
            if (ActualJvm_jvmKt.identityHashCode(obj2) != keyHash) {
                break;
            }
        }
        do {
            midIndex++;
            if (midIndex >= i) {
                return -(i + 1);
            }
            obj = objArr[midIndex];
            if (obj == key) {
                return midIndex;
            }
        } while (ActualJvm_jvmKt.identityHashCode(obj) == keyHash);
        return -(midIndex + 1);
    }

    /* compiled from: IdentityArrayMap.kt */
    @Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0017\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0012H\u0016R&\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"androidx/compose/runtime/collection/IdentityArrayMap$asMap$1", "", RemoteConfigConstants.ResponseFieldKey.ENTRIES, "", "", "getEntries", "()Ljava/util/Set;", UserMetadata.KEYDATA_FILENAME, "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", DatabaseFileArchive.COLUMN_KEY, "(Ljava/lang/Object;)Z", "containsValue", "value", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "isEmpty", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: androidx.compose.runtime.collection.IdentityArrayMap$asMap$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Map<Key, Value>, KMappedMarker {
        final /* synthetic */ IdentityArrayMap<Key, Value> this$0;

        @Override // java.util.Map
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value compute(Key key, BiFunction<? super Key, ? super Value, ? extends Value> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value computeIfAbsent(Key key, Function<? super Key, ? extends Value> function) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value computeIfPresent(Key key, BiFunction<? super Key, ? super Value, ? extends Value> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value merge(Key key, Value value, BiFunction<? super Value, ? super Value, ? extends Value> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value put(Key key, Value value) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public void putAll(Map<? extends Key, ? extends Value> map) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value putIfAbsent(Key key, Value value) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value remove(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Value replace(Key key, Value value) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public boolean replace(Key key, Value value, Value value2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super Key, ? super Value, ? extends Value> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(IdentityArrayMap<Key, Value> identityArrayMap) {
            this.this$0 = identityArrayMap;
        }

        @Override // java.util.Map
        public final /* bridge */ Set<Map.Entry<Key, Value>> entrySet() {
            return getEntries();
        }

        @Override // java.util.Map
        public final /* bridge */ Set<Key> keySet() {
            return getKeys();
        }

        @Override // java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.Map
        public final /* bridge */ Collection<Value> values() {
            return getValues();
        }

        public Set<Map.Entry<Key, Value>> getEntries() {
            return new IdentityArrayMap$asMap$1$entries$1(this.this$0);
        }

        public Set<Key> getKeys() {
            return new IdentityArrayMap$asMap$1$keys$1(this.this$0);
        }

        public int getSize() {
            return this.this$0.getSize();
        }

        public Collection<Value> getValues() {
            return new IdentityArrayMap$asMap$1$values$1(this.this$0);
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.this$0.isEmpty();
        }

        @Override // java.util.Map
        public Value get(Object key) {
            if (key == null) {
                return null;
            }
            return this.this$0.get(key);
        }

        @Override // java.util.Map
        public boolean containsValue(Object value) {
            return ArraysKt.contains(this.this$0.getValues(), value);
        }

        @Override // java.util.Map
        public boolean containsKey(Object key) {
            return (key == null || this.this$0.get(key) == null) ? false : true;
        }
    }

    public final Map<Key, Value> asMap() {
        return new AnonymousClass1(this);
    }

    public final void removeValueIf(Function1<? super Value, Boolean> block) {
        int size = getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = getKeys()[i2];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
            if (!block.invoke(getValues()[i2]).booleanValue()) {
                if (i != i2) {
                    getKeys()[i] = obj;
                    getValues()[i] = getValues()[i2];
                }
                i++;
            }
        }
        if (getSize() > i) {
            int size2 = getSize();
            for (int i3 = i; i3 < size2; i3++) {
                getKeys()[i3] = null;
                getValues()[i3] = null;
            }
            this.size = i;
        }
    }
}

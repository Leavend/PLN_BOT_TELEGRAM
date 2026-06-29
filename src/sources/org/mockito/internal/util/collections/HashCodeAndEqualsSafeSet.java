package org.mockito.internal.util.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.mockito.internal.util.Checks;

/* loaded from: classes3.dex */
public class HashCodeAndEqualsSafeSet implements Set<Object> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final HashSet<HashCodeAndEqualsMockWrapper> backingHashSet = new HashSet<>();

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<Object> iterator() {
        return new Iterator<Object>() { // from class: org.mockito.internal.util.collections.HashCodeAndEqualsSafeSet.1
            private final Iterator<HashCodeAndEqualsMockWrapper> iterator;

            {
                this.iterator = HashCodeAndEqualsSafeSet.this.backingHashSet.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.iterator.hasNext();
            }

            @Override // java.util.Iterator
            public Object next() {
                return this.iterator.next().get();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.iterator.remove();
            }
        };
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.backingHashSet.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.backingHashSet.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.backingHashSet.contains(HashCodeAndEqualsMockWrapper.of(obj));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Object obj) {
        return this.backingHashSet.add(HashCodeAndEqualsMockWrapper.of(obj));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        return this.backingHashSet.remove(HashCodeAndEqualsMockWrapper.of(obj));
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.backingHashSet.clear();
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        if (obj instanceof HashCodeAndEqualsSafeSet) {
            return this.backingHashSet.equals(((HashCodeAndEqualsSafeSet) obj).backingHashSet);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        return this.backingHashSet.hashCode();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return unwrapTo(new Object[size()]);
    }

    private <T> T[] unwrapTo(T[] tArr) {
        Iterator<Object> it = iterator();
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (it.hasNext()) {
                tArr[i] = it.next();
            }
        }
        return tArr;
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < size()) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size()));
        }
        return (T[]) unwrapTo(tArr);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.backingHashSet.removeAll(asWrappedMocks(collection));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.backingHashSet.containsAll(asWrappedMocks(collection));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<?> collection) {
        return this.backingHashSet.addAll(asWrappedMocks(collection));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.backingHashSet.retainAll(asWrappedMocks(collection));
    }

    private HashSet<HashCodeAndEqualsMockWrapper> asWrappedMocks(Collection<?> collection) {
        Checks.checkNotNull(collection, "Passed collection should notify() be null");
        HashSet<HashCodeAndEqualsMockWrapper> hashSet = new HashSet<>();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            hashSet.add(HashCodeAndEqualsMockWrapper.of(it.next()));
        }
        return hashSet;
    }

    public String toString() {
        return this.backingHashSet.toString();
    }

    public static HashCodeAndEqualsSafeSet of(Object... objArr) {
        return of((Iterable<Object>) Arrays.asList(objArr));
    }

    public static HashCodeAndEqualsSafeSet of(Iterable<Object> iterable) {
        HashCodeAndEqualsSafeSet hashCodeAndEqualsSafeSet = new HashCodeAndEqualsSafeSet();
        if (iterable != null) {
            Iterator<Object> it = iterable.iterator();
            while (it.hasNext()) {
                hashCodeAndEqualsSafeSet.add(it.next());
            }
        }
        return hashCodeAndEqualsSafeSet;
    }
}

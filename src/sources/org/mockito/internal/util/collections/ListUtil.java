package org.mockito.internal.util.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class ListUtil {

    public interface Converter<From, To> {
        To convert(From from);
    }

    public interface Filter<T> {
        boolean isOut(T t);
    }

    public static <T> LinkedList<T> filter(Collection<T> collection, Filter<T> filter) {
        LinkedList<T> linkedList = new LinkedList<>();
        for (T t : collection) {
            if (!filter.isOut(t)) {
                linkedList.add(t);
            }
        }
        return linkedList;
    }

    public static <From, To> LinkedList<To> convert(Collection<From> collection, Converter<From, To> converter) {
        LinkedList<To> linkedList = new LinkedList<>();
        Iterator<From> it = collection.iterator();
        while (it.hasNext()) {
            linkedList.add(converter.convert(it.next()));
        }
        return linkedList;
    }
}

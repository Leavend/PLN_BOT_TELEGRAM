package com.google.firebase.firestore;

import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class Filter {

    static class UnaryFilter extends Filter {
        private final FieldPath field;
        private final FieldFilter.Operator operator;
        private final Object value;

        public UnaryFilter(FieldPath fieldPath, FieldFilter.Operator operator, Object obj) {
            this.field = fieldPath;
            this.operator = operator;
            this.value = obj;
        }

        public FieldPath getField() {
            return this.field;
        }

        public FieldFilter.Operator getOperator() {
            return this.operator;
        }

        public Object getValue() {
            return this.value;
        }
    }

    static class CompositeFilter extends Filter {
        private final List<Filter> filters;
        private final CompositeFilter.Operator operator;

        public CompositeFilter(List<Filter> list, CompositeFilter.Operator operator) {
            this.filters = list;
            this.operator = operator;
        }

        public List<Filter> getFilters() {
            return this.filters;
        }

        public CompositeFilter.Operator getOperator() {
            return this.operator;
        }
    }

    public static Filter equalTo(String str, Object obj) {
        return equalTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter equalTo(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.EQUAL, obj);
    }

    public static Filter notEqualTo(String str, Object obj) {
        return notEqualTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter notEqualTo(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.NOT_EQUAL, obj);
    }

    public static Filter greaterThan(String str, Object obj) {
        return greaterThan(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter greaterThan(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.GREATER_THAN, obj);
    }

    public static Filter greaterThanOrEqualTo(String str, Object obj) {
        return greaterThanOrEqualTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter greaterThanOrEqualTo(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.GREATER_THAN_OR_EQUAL, obj);
    }

    public static Filter lessThan(String str, Object obj) {
        return lessThan(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter lessThan(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.LESS_THAN, obj);
    }

    public static Filter lessThanOrEqualTo(String str, Object obj) {
        return lessThanOrEqualTo(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter lessThanOrEqualTo(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.LESS_THAN_OR_EQUAL, obj);
    }

    public static Filter arrayContains(String str, Object obj) {
        return arrayContains(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter arrayContains(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.ARRAY_CONTAINS, obj);
    }

    public static Filter arrayContainsAny(String str, Object obj) {
        return arrayContainsAny(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter arrayContainsAny(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.ARRAY_CONTAINS_ANY, obj);
    }

    public static Filter inArray(String str, Object obj) {
        return inArray(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter inArray(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.IN, obj);
    }

    public static Filter notInArray(String str, Object obj) {
        return notInArray(FieldPath.fromDotSeparatedPath(str), obj);
    }

    public static Filter notInArray(FieldPath fieldPath, Object obj) {
        return new UnaryFilter(fieldPath, FieldFilter.Operator.NOT_IN, obj);
    }

    public static Filter or(Filter... filterArr) {
        return new CompositeFilter(Arrays.asList(filterArr), CompositeFilter.Operator.OR);
    }

    public static Filter and(Filter... filterArr) {
        return new CompositeFilter(Arrays.asList(filterArr), CompositeFilter.Operator.AND);
    }
}

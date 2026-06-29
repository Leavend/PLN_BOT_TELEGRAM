package org.mockito.internal.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mockito.internal.util.Checks;
import org.mockito.internal.util.collections.ListUtil;

/* loaded from: classes3.dex */
public abstract class Fields {
    public static InstanceFields allDeclaredFieldsOf(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            arrayList.addAll(instanceFieldsIn(obj, superclass.getDeclaredFields()));
        }
        return new InstanceFields(obj, arrayList);
    }

    public static InstanceFields declaredFieldsOf(Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(instanceFieldsIn(obj, obj.getClass().getDeclaredFields()));
        return new InstanceFields(obj, arrayList);
    }

    private static List<InstanceField> instanceFieldsIn(Object obj, Field[] fieldArr) {
        ArrayList arrayList = new ArrayList();
        for (Field field : fieldArr) {
            arrayList.add(new InstanceField(field, obj));
        }
        return arrayList;
    }

    public static ListUtil.Filter<InstanceField> annotatedBy(final Class<? extends Annotation>... clsArr) {
        return new ListUtil.Filter<InstanceField>() { // from class: org.mockito.internal.util.reflection.Fields.1
            @Override // org.mockito.internal.util.collections.ListUtil.Filter
            public boolean isOut(InstanceField instanceField) {
                Checks.checkNotNull(clsArr, "Provide at least one annotation class");
                for (Class<? extends Annotation> cls : clsArr) {
                    if (instanceField.isAnnotatedBy(cls)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListUtil.Filter<InstanceField> nullField() {
        return new ListUtil.Filter<InstanceField>() { // from class: org.mockito.internal.util.reflection.Fields.2
            @Override // org.mockito.internal.util.collections.ListUtil.Filter
            public boolean isOut(InstanceField instanceField) {
                return instanceField.isNull();
            }
        };
    }

    public static ListUtil.Filter<InstanceField> syntheticField() {
        return new ListUtil.Filter<InstanceField>() { // from class: org.mockito.internal.util.reflection.Fields.3
            @Override // org.mockito.internal.util.collections.ListUtil.Filter
            public boolean isOut(InstanceField instanceField) {
                return instanceField.isSynthetic();
            }
        };
    }

    public static class InstanceFields {
        private final Object instance;
        private final List<InstanceField> instanceFields;

        public InstanceFields(Object obj, List<InstanceField> list) {
            this.instance = obj;
            this.instanceFields = list;
        }

        public InstanceFields filter(ListUtil.Filter<InstanceField> filter) {
            return new InstanceFields(this.instance, ListUtil.filter(this.instanceFields, filter));
        }

        public InstanceFields notNull() {
            return filter(Fields.nullField());
        }

        public List<InstanceField> instanceFields() {
            return new ArrayList(this.instanceFields);
        }

        public List<Object> assignedValues() {
            ArrayList arrayList = new ArrayList(this.instanceFields.size());
            Iterator<InstanceField> it = this.instanceFields.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().read());
            }
            return arrayList;
        }

        public List<String> names() {
            ArrayList arrayList = new ArrayList(this.instanceFields.size());
            Iterator<InstanceField> it = this.instanceFields.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().name());
            }
            return arrayList;
        }
    }
}

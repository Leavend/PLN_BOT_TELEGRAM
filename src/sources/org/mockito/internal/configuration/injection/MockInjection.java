package org.mockito.internal.configuration.injection;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.mockito.internal.util.Checks;
import org.mockito.internal.util.collections.Sets;

/* loaded from: classes3.dex */
public class MockInjection {
    public static OngoingMockInjection onField(Field field, Object obj) {
        return new OngoingMockInjection(field, obj);
    }

    public static OngoingMockInjection onFields(Set<Field> set, Object obj) {
        return new OngoingMockInjection(set, obj);
    }

    public static class OngoingMockInjection {
        private final Object fieldOwner;
        private final Set<Field> fields;
        private final MockInjectionStrategy injectionStrategies;
        private final Set<Object> mocks;
        private final MockInjectionStrategy postInjectionStrategies;

        private OngoingMockInjection(Field field, Object obj) {
            this((Set<Field>) Collections.singleton(field), obj);
        }

        private OngoingMockInjection(Set<Field> set, Object obj) {
            HashSet hashSet = new HashSet();
            this.fields = hashSet;
            this.mocks = Sets.newMockSafeHashSet(new Object[0]);
            this.injectionStrategies = MockInjectionStrategy.nop();
            this.postInjectionStrategies = MockInjectionStrategy.nop();
            this.fieldOwner = Checks.checkNotNull(obj, "fieldOwner");
            hashSet.addAll((Collection) Checks.checkItemsNotNull(set, "fields"));
        }

        public OngoingMockInjection withMocks(Set<Object> set) {
            this.mocks.addAll((Collection) Checks.checkNotNull(set, "mocks"));
            return this;
        }

        public OngoingMockInjection tryConstructorInjection() {
            this.injectionStrategies.thenTry(new ConstructorInjection());
            return this;
        }

        public OngoingMockInjection tryPropertyOrFieldInjection() {
            this.injectionStrategies.thenTry(new PropertyAndSetterInjection());
            return this;
        }

        public OngoingMockInjection handleSpyAnnotation() {
            this.postInjectionStrategies.thenTry(new SpyOnInjectedFieldsHandler());
            return this;
        }

        public void apply() {
            for (Field field : this.fields) {
                this.injectionStrategies.process(field, this.fieldOwner, this.mocks);
                this.postInjectionStrategies.process(field, this.fieldOwner, this.mocks);
            }
        }
    }
}

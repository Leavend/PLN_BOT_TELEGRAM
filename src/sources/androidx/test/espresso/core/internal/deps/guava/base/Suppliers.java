package androidx.test.espresso.core.internal.deps.guava.base;

import java.io.Serializable;

/* loaded from: classes5.dex */
public final class Suppliers {

    private static class SupplierOfInstance implements Supplier, Serializable {
        private static final long serialVersionUID = 0;
        final Object instance;

        SupplierOfInstance(Object obj) {
            this.instance = obj;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        @Override // androidx.test.espresso.core.internal.deps.guava.base.Supplier
        public Object get() {
            return this.instance;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            return "Suppliers.ofInstance(" + this.instance + ")";
        }
    }

    public static Supplier ofInstance(Object obj) {
        return new SupplierOfInstance(obj);
    }
}

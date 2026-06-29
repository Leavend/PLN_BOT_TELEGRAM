package org.mockito.internal.configuration.injection.filter;

/* loaded from: classes3.dex */
public interface OngoingInjector {
    public static final OngoingInjector nop = new OngoingInjector() { // from class: org.mockito.internal.configuration.injection.filter.OngoingInjector.1
        @Override // org.mockito.internal.configuration.injection.filter.OngoingInjector
        public Object thenInject() {
            return null;
        }
    };

    Object thenInject();
}

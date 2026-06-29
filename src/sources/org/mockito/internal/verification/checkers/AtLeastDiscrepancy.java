package org.mockito.internal.verification.checkers;

import org.mockito.internal.reporting.Discrepancy;

/* loaded from: classes3.dex */
public class AtLeastDiscrepancy extends Discrepancy {
    public AtLeastDiscrepancy(int i, int i2) {
        super(i, i2);
    }

    @Override // org.mockito.internal.reporting.Discrepancy
    public String getPluralizedWantedCount() {
        return "*at least* " + super.getPluralizedWantedCount();
    }
}

package org.mockito.internal.progress;

/* loaded from: classes3.dex */
public class SequenceNumber {
    private static int sequenceNumber = 1;

    public static synchronized int next() {
        int i;
        i = sequenceNumber;
        sequenceNumber = i + 1;
        return i;
    }
}

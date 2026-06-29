package org.mockito.internal.reporting;

/* loaded from: classes3.dex */
public class Pluralizer {
    public static String pluralize(int i) {
        return i == 1 ? "1 time" : i + " times";
    }

    public static String were_exactly_x_interactions(int i) {
        return i == 1 ? "was exactly 1 interaction" : "were exactly " + i + " interactions";
    }
}

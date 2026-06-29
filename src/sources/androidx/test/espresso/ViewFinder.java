package androidx.test.espresso;

import android.view.View;

/* loaded from: classes5.dex */
public interface ViewFinder {
    View getView() throws NoMatchingViewException, AmbiguousViewMatcherException;
}

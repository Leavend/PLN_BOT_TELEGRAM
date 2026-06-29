package androidx.test.espresso.assertion;

import android.view.View;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.base.Predicate;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.remote.annotation.RemoteMsgConstructor;
import androidx.test.espresso.remote.annotation.RemoteMsgField;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import java.util.ArrayList;
import java.util.Locale;
import junit.framework.AssertionFailedError;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;

/* loaded from: classes5.dex */
public final class ViewAssertions {
    private static final String TAG = "ViewAssertions";

    static class DoesNotExistViewAssertion implements ViewAssertion {
        @RemoteMsgConstructor
        private DoesNotExistViewAssertion() {
        }

        @Override // androidx.test.espresso.ViewAssertion
        public void check(View view, NoMatchingViewException noMatchingViewException) {
            if (view != null) {
                ViewMatchers.assertThat("View is present in the hierarchy: " + HumanReadables.describe(view), true, Matchers.is(false));
            }
        }
    }

    static class SelectedDescendantsMatchViewAssertion implements ViewAssertion {

        @RemoteMsgField(order = 1)
        private final Matcher<View> matcher;

        @RemoteMsgField(order = 0)
        private final Matcher<View> selector;

        @RemoteMsgConstructor
        private SelectedDescendantsMatchViewAssertion(Matcher<View> matcher, Matcher<View> matcher2) {
            this.selector = matcher;
            this.matcher = matcher2;
        }

        @Override // androidx.test.espresso.ViewAssertion
        public void check(View view, NoMatchingViewException noMatchingViewException) {
            Preconditions.checkNotNull(view);
            ArrayList arrayList = new ArrayList();
            for (View view2 : Iterables.filter(TreeIterables.breadthFirstViewTraversal(view), new Predicate<View>() { // from class: androidx.test.espresso.assertion.ViewAssertions.SelectedDescendantsMatchViewAssertion.1
                @Override // androidx.test.espresso.core.internal.deps.guava.base.Predicate
                public boolean apply(View view3) {
                    return SelectedDescendantsMatchViewAssertion.this.selector.matches(view3);
                }
            })) {
                if (!this.matcher.matches(view2)) {
                    arrayList.add(view2);
                }
            }
            if (arrayList.size() > 0) {
                throw new AssertionFailedError(HumanReadables.getViewHierarchyErrorMessage(view, arrayList, String.format(Locale.ROOT, "At least one view did not match the required matcher: %s", this.matcher), "****DOES NOT MATCH****"));
            }
        }

        public String toString() {
            return String.format(Locale.ROOT, "SelectedDescendantsMatchViewAssertion{selector=%s, matcher=%s}", this.selector, this.matcher);
        }
    }

    private ViewAssertions() {
    }

    public static ViewAssertion doesNotExist() {
        return new DoesNotExistViewAssertion();
    }

    public static ViewAssertion matches(Matcher<? super View> matcher) {
        return new MatchesViewAssertion((Matcher) Preconditions.checkNotNull(matcher));
    }

    public static ViewAssertion selectedDescendantsMatch(Matcher<View> matcher, Matcher<View> matcher2) {
        return new SelectedDescendantsMatchViewAssertion(matcher, matcher2);
    }

    static class MatchesViewAssertion implements ViewAssertion {

        @RemoteMsgField(order = 0)
        final Matcher<? super View> viewMatcher;

        @RemoteMsgConstructor
        private MatchesViewAssertion(Matcher<? super View> matcher) {
            this.viewMatcher = matcher;
        }

        public String toString() {
            return String.format(Locale.ROOT, "MatchesViewAssertion{viewMatcher=%s}", this.viewMatcher);
        }

        @Override // androidx.test.espresso.ViewAssertion
        public void check(View view, NoMatchingViewException noMatchingViewException) {
            if (noMatchingViewException != null) {
                throw noMatchingViewException;
            }
            StringDescription stringDescription = new StringDescription();
            stringDescription.appendText("'");
            this.viewMatcher.describeTo(stringDescription);
            stringDescription.appendText("' doesn't match the selected view.");
            ViewMatchers.assertThat(stringDescription.toString(), view, this.viewMatcher);
        }
    }
}

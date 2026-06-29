package androidx.test.espresso;

import android.view.View;
import androidx.core.os.EnvironmentCompat;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;
import androidx.test.espresso.util.EspressoOptional;
import androidx.test.espresso.util.HumanReadables;
import java.util.List;
import java.util.Locale;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public final class NoMatchingViewException extends RuntimeException implements RootViewException {
    private EspressoOptional<String> adapterViewWarning;
    private List<View> adapterViews;
    private boolean includeViewHierarchy;
    private View rootView;
    private Matcher<? super View> viewMatcher;

    public static class Builder {
        private Throwable cause;
        private View rootView;
        private Matcher<? super View> viewMatcher;
        private List<View> adapterViews = Lists.newArrayList();
        private boolean includeViewHierarchy = true;
        private EspressoOptional<String> adapterViewWarning = EspressoOptional.absent();
        private int maxMsgLen = Integer.MAX_VALUE;
        private String viewHierarchyFile = null;

        public NoMatchingViewException build() {
            Preconditions.checkNotNull(this.viewMatcher);
            Preconditions.checkNotNull(this.rootView);
            Preconditions.checkNotNull(this.adapterViews);
            Preconditions.checkNotNull(this.adapterViewWarning);
            return new NoMatchingViewException(this);
        }

        public Builder from(NoMatchingViewException noMatchingViewException) {
            this.viewMatcher = noMatchingViewException.viewMatcher;
            this.rootView = noMatchingViewException.rootView;
            this.adapterViews = noMatchingViewException.adapterViews;
            this.adapterViewWarning = noMatchingViewException.adapterViewWarning;
            this.includeViewHierarchy = noMatchingViewException.includeViewHierarchy;
            return this;
        }

        public Builder includeViewHierarchy(boolean z) {
            this.includeViewHierarchy = z;
            return this;
        }

        public Builder withAdapterViewWarning(EspressoOptional<String> espressoOptional) {
            this.adapterViewWarning = espressoOptional;
            return this;
        }

        public Builder withAdapterViews(List<View> list) {
            this.adapterViews = list;
            return this;
        }

        public Builder withCause(Throwable th) {
            this.cause = th;
            return this;
        }

        public Builder withMaxMsgLen(int i) {
            this.maxMsgLen = i;
            return this;
        }

        public Builder withRootView(View view) {
            this.rootView = view;
            return this;
        }

        public Builder withViewHierarchyFile(String str) {
            this.viewHierarchyFile = str;
            return this;
        }

        public Builder withViewMatcher(Matcher<? super View> matcher) {
            this.viewMatcher = matcher;
            return this;
        }
    }

    private NoMatchingViewException(Builder builder) {
        super(getErrorMessage(builder), builder.cause);
        this.adapterViews = Lists.newArrayList();
        this.includeViewHierarchy = true;
        this.adapterViewWarning = EspressoOptional.absent();
        this.viewMatcher = builder.viewMatcher;
        this.rootView = builder.rootView;
        this.adapterViews = builder.adapterViews;
        this.adapterViewWarning = builder.adapterViewWarning;
        this.includeViewHierarchy = builder.includeViewHierarchy;
    }

    @Override // androidx.test.espresso.RootViewException
    public View getRootView() {
        return this.rootView;
    }

    private static String getErrorMessage(Builder builder) {
        if (!builder.includeViewHierarchy) {
            return String.format(Locale.ROOT, "Could not find a view that matches %s", builder.viewMatcher);
        }
        String str = String.format(Locale.ROOT, "No views in hierarchy found matching: %s", builder.viewMatcher);
        if (builder.adapterViewWarning.isPresent()) {
            str = str + ((String) builder.adapterViewWarning.get());
        }
        String viewHierarchyErrorMessage = HumanReadables.getViewHierarchyErrorMessage(builder.rootView, null, str, null, builder.maxMsgLen);
        if (builder.viewHierarchyFile == null) {
            return viewHierarchyErrorMessage;
        }
        return viewHierarchyErrorMessage + String.format("\nThe complete view hierarchy is available in artifact file '%s'.", builder.viewHierarchyFile);
    }

    public String getViewMatcherDescription() {
        Matcher<? super View> matcher = this.viewMatcher;
        return matcher != null ? matcher.toString() : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    private NoMatchingViewException(String str) {
        super(str);
        this.adapterViews = Lists.newArrayList();
        this.includeViewHierarchy = true;
        this.adapterViewWarning = EspressoOptional.absent();
    }
}

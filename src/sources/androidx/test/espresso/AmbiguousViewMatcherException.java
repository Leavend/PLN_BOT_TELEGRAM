package androidx.test.espresso;

import android.view.View;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.collect.ImmutableSet;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.internal.platform.util.TestOutputEmitter;
import java.util.ArrayList;
import java.util.Locale;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public final class AmbiguousViewMatcherException extends RuntimeException implements RootViewException {
    private View[] others;
    private View rootView;
    private View view1;
    private View view2;
    private Matcher<? super View> viewMatcher;

    public static class Builder {
        private View[] others;
        private View rootView;
        private View view1;
        private View view2;
        private Matcher<? super View> viewMatcher;
        private boolean includeViewHierarchy = true;
        private int maxMsgLen = Integer.MAX_VALUE;
        private String viewHierarchyFile = null;

        public AmbiguousViewMatcherException build() {
            Preconditions.checkNotNull(this.viewMatcher);
            Preconditions.checkNotNull(this.rootView);
            Preconditions.checkNotNull(this.view1);
            Preconditions.checkNotNull(this.view2);
            Preconditions.checkNotNull(this.others);
            return new AmbiguousViewMatcherException(this);
        }

        public Builder from(AmbiguousViewMatcherException ambiguousViewMatcherException) {
            this.viewMatcher = ambiguousViewMatcherException.viewMatcher;
            this.rootView = ambiguousViewMatcherException.rootView;
            this.view1 = ambiguousViewMatcherException.view1;
            this.view2 = ambiguousViewMatcherException.view2;
            this.others = ambiguousViewMatcherException.others;
            return this;
        }

        public Builder includeViewHierarchy(boolean z) {
            this.includeViewHierarchy = z;
            return this;
        }

        public Builder withMaxMsgLen(int i) {
            this.maxMsgLen = i;
            return this;
        }

        public Builder withOtherAmbiguousViews(View... viewArr) {
            this.others = viewArr;
            return this;
        }

        public Builder withRootView(View view) {
            this.rootView = view;
            return this;
        }

        public Builder withView1(View view) {
            this.view1 = view;
            return this;
        }

        public Builder withView2(View view) {
            this.view2 = view;
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

    private AmbiguousViewMatcherException(Builder builder) {
        this(getErrorMessage(builder));
        this.viewMatcher = builder.viewMatcher;
        this.rootView = builder.rootView;
        this.view1 = builder.view1;
        this.view2 = builder.view2;
        this.others = builder.others;
    }

    @Override // androidx.test.espresso.RootViewException
    public View getRootView() {
        return this.rootView;
    }

    private static String getErrorMessage(Builder builder) {
        if (!builder.includeViewHierarchy) {
            return String.format(Locale.ROOT, "Multiple ambiguous views found for matcher %s", builder.viewMatcher);
        }
        int i = 0;
        ArrayList arrayListNewArrayList = Lists.newArrayList(ImmutableSet.builder().add((Object[]) new View[]{builder.view1, builder.view2}).add((Object[]) builder.others).build());
        StringBuilder sb = new StringBuilder();
        int size = arrayListNewArrayList.size();
        while (true) {
            if (i < size) {
                if (i >= 5) {
                    sb.append(String.format(Locale.ROOT, "\n- [truncated, listing 5 out of %d views].", Integer.valueOf(size)));
                    break;
                }
                int i2 = i + 1;
                sb.append(String.format(Locale.ROOT, "\n- [%d] %s", Integer.valueOf(i2), HumanReadables.describe((View) arrayListNewArrayList.get(i))));
                i = i2;
            } else {
                break;
            }
        }
        String viewHierarchyErrorMessage = HumanReadables.getViewHierarchyErrorMessage(builder.rootView, arrayListNewArrayList, String.format(Locale.ROOT, "'%s' matches %d views in the hierarchy:%s", builder.viewMatcher, Integer.valueOf(size), sb), "****MATCHES****", builder.maxMsgLen);
        return builder.viewHierarchyFile != null ? viewHierarchyErrorMessage + String.format("\nThe complete view hierarchy is available in artifact file '%s'.", builder.viewHierarchyFile) : viewHierarchyErrorMessage;
    }

    private AmbiguousViewMatcherException(String str) {
        super(str);
        TestOutputEmitter.dumpThreadStates("ThreadState-AmbiguousViewMatcherException.txt");
    }
}

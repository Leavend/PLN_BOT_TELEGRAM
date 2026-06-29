package org.mockito.internal.invocation;

import java.util.ArrayList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.internal.matchers.VarargMatcher;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public class MatcherApplicationStrategy {
    private final Invocation invocation;
    private final List<ArgumentMatcher<?>> matchers;
    private final MatcherApplicationType matchingType;

    enum MatcherApplicationType {
        ONE_MATCHER_PER_ARGUMENT,
        MATCH_EACH_VARARGS_WITH_LAST_MATCHER,
        ERROR_UNSUPPORTED_NUMBER_OF_MATCHERS
    }

    private MatcherApplicationStrategy(Invocation invocation, List<ArgumentMatcher<?>> list, MatcherApplicationType matcherApplicationType) {
        this.invocation = invocation;
        if (matcherApplicationType == MatcherApplicationType.MATCH_EACH_VARARGS_WITH_LAST_MATCHER) {
            this.matchers = appendLastMatcherNTimes(list, varargLength(invocation));
        } else {
            this.matchers = list;
        }
        this.matchingType = matcherApplicationType;
    }

    public static MatcherApplicationStrategy getMatcherApplicationStrategyFor(Invocation invocation, List<ArgumentMatcher<?>> list) {
        return new MatcherApplicationStrategy(invocation, list, getMatcherApplicationType(invocation, list));
    }

    public boolean forEachMatcherAndArgument(ArgumentMatcherAction argumentMatcherAction) {
        if (this.matchingType == MatcherApplicationType.ERROR_UNSUPPORTED_NUMBER_OF_MATCHERS) {
            return false;
        }
        Object[] arguments = this.invocation.getArguments();
        for (int i = 0; i < arguments.length; i++) {
            if (!argumentMatcherAction.apply(this.matchers.get(i), arguments[i])) {
                return false;
            }
        }
        return true;
    }

    private static MatcherApplicationType getMatcherApplicationType(Invocation invocation, List<ArgumentMatcher<?>> list) {
        int length = invocation.getRawArguments().length;
        int length2 = invocation.getArguments().length;
        int size = list.size();
        if (length2 == size) {
            return MatcherApplicationType.ONE_MATCHER_PER_ARGUMENT;
        }
        if (length == size && isLastMatcherVarargMatcher(list)) {
            return MatcherApplicationType.MATCH_EACH_VARARGS_WITH_LAST_MATCHER;
        }
        return MatcherApplicationType.ERROR_UNSUPPORTED_NUMBER_OF_MATCHERS;
    }

    private static boolean isLastMatcherVarargMatcher(List<ArgumentMatcher<?>> list) {
        ArgumentMatcher<?> argumentMatcherLastMatcher = lastMatcher(list);
        if (argumentMatcherLastMatcher instanceof HamcrestArgumentMatcher) {
            return ((HamcrestArgumentMatcher) argumentMatcherLastMatcher).isVarargMatcher();
        }
        return argumentMatcherLastMatcher instanceof VarargMatcher;
    }

    private static List<ArgumentMatcher<?>> appendLastMatcherNTimes(List<ArgumentMatcher<?>> list, int i) {
        ArgumentMatcher<?> argumentMatcherLastMatcher = lastMatcher(list);
        ArrayList arrayList = new ArrayList(list);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(argumentMatcherLastMatcher);
        }
        return arrayList;
    }

    private static int varargLength(Invocation invocation) {
        return invocation.getArguments().length - invocation.getRawArguments().length;
    }

    private static ArgumentMatcher<?> lastMatcher(List<ArgumentMatcher<?>> list) {
        return list.get(list.size() - 1);
    }
}

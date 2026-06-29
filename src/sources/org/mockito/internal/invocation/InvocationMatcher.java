package org.mockito.internal.invocation;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.CapturesArguments;
import org.mockito.internal.reporting.PrintSettings;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public class InvocationMatcher implements MatchableInvocation, DescribedInvocation, Serializable {
    private final Invocation invocation;
    private final List<ArgumentMatcher<?>> matchers;

    public InvocationMatcher(Invocation invocation, List<ArgumentMatcher> list) {
        this.invocation = invocation;
        if (list.isEmpty()) {
            this.matchers = ArgumentsProcessor.argumentsToMatchers(invocation.getArguments());
        } else {
            this.matchers = list;
        }
    }

    public InvocationMatcher(Invocation invocation) {
        this(invocation, Collections.emptyList());
    }

    public static List<InvocationMatcher> createFrom(List<Invocation> list) {
        LinkedList linkedList = new LinkedList();
        Iterator<Invocation> it = list.iterator();
        while (it.hasNext()) {
            linkedList.add(new InvocationMatcher(it.next()));
        }
        return linkedList;
    }

    public Method getMethod() {
        return this.invocation.getMethod();
    }

    @Override // org.mockito.invocation.MatchableInvocation
    public Invocation getInvocation() {
        return this.invocation;
    }

    @Override // org.mockito.invocation.MatchableInvocation
    public List<ArgumentMatcher> getMatchers() {
        return this.matchers;
    }

    @Override // org.mockito.invocation.DescribedInvocation
    public String toString() {
        return new PrintSettings().print(this.matchers, this.invocation);
    }

    @Override // org.mockito.invocation.MatchableInvocation
    public boolean matches(Invocation invocation) {
        return this.invocation.getMock().equals(invocation.getMock()) && hasSameMethod(invocation) && argumentsMatch(invocation);
    }

    @Override // org.mockito.invocation.MatchableInvocation
    public boolean hasSimilarMethod(Invocation invocation) {
        if (!getMethod().getName().equals(invocation.getMethod().getName()) || invocation.isVerified() || getInvocation().getMock() != invocation.getMock()) {
            return false;
        }
        if (hasSameMethod(invocation)) {
            return true;
        }
        return !argumentsMatch(invocation);
    }

    @Override // org.mockito.invocation.MatchableInvocation
    public boolean hasSameMethod(Invocation invocation) {
        Method method = this.invocation.getMethod();
        Method method2 = invocation.getMethod();
        if (method.getName() == null || !method.getName().equals(method2.getName())) {
            return false;
        }
        return Arrays.equals(method.getParameterTypes(), method2.getParameterTypes());
    }

    @Override // org.mockito.invocation.DescribedInvocation
    public Location getLocation() {
        return this.invocation.getLocation();
    }

    @Override // org.mockito.invocation.MatchableInvocation
    public void captureArgumentsFrom(Invocation invocation) {
        MatcherApplicationStrategy.getMatcherApplicationStrategyFor(invocation, this.matchers).forEachMatcherAndArgument(captureArgument());
    }

    private ArgumentMatcherAction captureArgument() {
        return new ArgumentMatcherAction() { // from class: org.mockito.internal.invocation.InvocationMatcher.1
            @Override // org.mockito.internal.invocation.ArgumentMatcherAction
            public boolean apply(ArgumentMatcher<?> argumentMatcher, Object obj) {
                if (!(argumentMatcher instanceof CapturesArguments)) {
                    return true;
                }
                ((CapturesArguments) argumentMatcher).captureFrom(obj);
                return true;
            }
        };
    }

    private boolean argumentsMatch(Invocation invocation) {
        return MatcherApplicationStrategy.getMatcherApplicationStrategyFor(invocation, getMatchers()).forEachMatcherAndArgument(TypeSafeMatching.matchesTypeSafe());
    }
}

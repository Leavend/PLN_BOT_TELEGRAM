package org.mockito.internal.junit;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.internal.session.MockitoSessionLoggerAdapter;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

/* loaded from: classes3.dex */
public class JUnitRule implements MockitoRule {
    private final MockitoLogger logger;
    private MockitoSession session;
    private Strictness strictness;

    public JUnitRule(MockitoLogger mockitoLogger, Strictness strictness) {
        this.logger = mockitoLogger;
        this.strictness = strictness;
    }

    @Override // org.junit.rules.MethodRule
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object obj) {
        return new Statement() { // from class: org.mockito.internal.junit.JUnitRule.1
            @Override // org.junit.runners.model.Statement
            public void evaluate() throws Throwable {
                JUnitRule.this.session = Mockito.mockitoSession().name(obj.getClass().getSimpleName() + "." + frameworkMethod.getName()).strictness(JUnitRule.this.strictness).logger(new MockitoSessionLoggerAdapter(JUnitRule.this.logger)).initMocks(obj).startMocking();
                Throwable thEvaluateSafely = evaluateSafely(statement);
                JUnitRule.this.session.finishMocking(thEvaluateSafely);
                if (thEvaluateSafely != null) {
                    throw thEvaluateSafely;
                }
            }

            private Throwable evaluateSafely(Statement statement2) {
                try {
                    statement2.evaluate();
                    return null;
                } catch (Throwable th) {
                    return th;
                }
            }
        };
    }

    @Override // org.mockito.junit.MockitoRule
    public MockitoRule silent() {
        return strictness(Strictness.LENIENT);
    }

    @Override // org.mockito.junit.MockitoRule
    public MockitoRule strictness(Strictness strictness) {
        this.strictness = strictness;
        MockitoSession mockitoSession = this.session;
        if (mockitoSession != null) {
            mockitoSession.setStrictness(strictness);
        }
        return this;
    }
}

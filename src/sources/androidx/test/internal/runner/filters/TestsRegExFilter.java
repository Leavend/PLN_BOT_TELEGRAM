package androidx.test.internal.runner.filters;

import androidx.test.filters.AbstractFilter;
import java.util.regex.Pattern;
import org.junit.runner.Description;

/* loaded from: classes5.dex */
public final class TestsRegExFilter extends AbstractFilter {
    private Pattern pattern = null;

    @Override // org.junit.runner.manipulation.Filter
    public String describe() {
        return "tests filter";
    }

    public void setPattern(String patternString) {
        this.pattern = Pattern.compile(patternString);
    }

    @Override // androidx.test.filters.AbstractFilter
    protected boolean evaluateTest(Description description) {
        if (this.pattern == null) {
            return true;
        }
        return this.pattern.matcher(String.format("%s#%s", description.getClassName(), description.getMethodName())).find();
    }
}

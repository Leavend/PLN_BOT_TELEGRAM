package androidx.test.internal.runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes5.dex */
public abstract class TestLoader {
    protected abstract Runner doCreateRunner(String className);

    public static class Factory {
        private Factory() {
        }

        public static TestLoader create(ClassLoader classLoader, RunnerBuilder runnerBuilder, boolean scanningPath) {
            if (classLoader == null) {
                classLoader = TestLoader.class.getClassLoader();
            }
            if (scanningPath) {
                return new ScanningTestLoader(classLoader, runnerBuilder);
            }
            return new DirectTestLoader(classLoader, runnerBuilder);
        }
    }

    public List<Runner> getRunnersFor(Collection<String> classNames) {
        Runner runnerDoCreateRunner;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : classNames) {
            if (!linkedHashMap.containsKey(str) && (runnerDoCreateRunner = doCreateRunner(str)) != null) {
                linkedHashMap.put(str, runnerDoCreateRunner);
            }
        }
        return new ArrayList(linkedHashMap.values());
    }
}

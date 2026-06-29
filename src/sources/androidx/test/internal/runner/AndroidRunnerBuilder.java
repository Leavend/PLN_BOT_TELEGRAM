package androidx.test.internal.runner;

import androidx.test.internal.runner.junit3.AndroidJUnit3Builder;
import androidx.test.internal.runner.junit3.AndroidSuiteBuilder;
import androidx.test.internal.runner.junit4.AndroidAnnotatedBuilder;
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder;
import androidx.test.internal.util.AndroidRunnerParams;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.internal.builders.AnnotatedBuilder;
import org.junit.internal.builders.IgnoredBuilder;
import org.junit.internal.builders.JUnit3Builder;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes5.dex */
class AndroidRunnerBuilder extends AllDefaultPossibilitiesBuilder {
    private final AndroidAnnotatedBuilder androidAnnotatedBuilder;
    private final AndroidJUnit3Builder androidJUnit3Builder;
    private final AndroidJUnit4Builder androidJUnit4Builder;
    private final AndroidSuiteBuilder androidSuiteBuilder;
    private final List<RunnerBuilder> customRunnerBuilders;
    private final IgnoredBuilder ignoredBuilder;

    public AndroidRunnerBuilder(AndroidRunnerParams runnerParams) {
        this(null, runnerParams, Collections.emptyList());
    }

    AndroidRunnerBuilder(AndroidRunnerParams runnerParams, List<Class<? extends RunnerBuilder>> customRunnerBuilderClasses) {
        this(null, runnerParams, customRunnerBuilderClasses);
    }

    AndroidRunnerBuilder(RunnerBuilder suiteBuilder, AndroidRunnerParams runnerParams, List<Class<? extends RunnerBuilder>> customRunnerBuilderClasses) {
        super(true);
        this.androidJUnit3Builder = new AndroidJUnit3Builder(runnerParams);
        this.androidJUnit4Builder = new AndroidJUnit4Builder(runnerParams);
        this.androidSuiteBuilder = new AndroidSuiteBuilder(runnerParams);
        this.androidAnnotatedBuilder = new AndroidAnnotatedBuilder(suiteBuilder == null ? this : suiteBuilder, runnerParams);
        this.ignoredBuilder = new IgnoredBuilder();
        this.customRunnerBuilders = instantiateRunnerBuilders(customRunnerBuilderClasses);
    }

    private List<RunnerBuilder> instantiateRunnerBuilders(List<Class<? extends RunnerBuilder>> customRunnerBuilderClasses) {
        ArrayList arrayList = new ArrayList();
        for (Class<? extends RunnerBuilder> cls : customRunnerBuilderClasses) {
            try {
                arrayList.add(cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not create instance of " + String.valueOf(cls) + ", make sure that it is a public concrete class with a public no-argument constructor", e);
            } catch (InstantiationException e2) {
                throw new IllegalStateException("Could not create instance of " + String.valueOf(cls) + ", make sure that it is a public concrete class with a public no-argument constructor", e2);
            } catch (NoSuchMethodException e3) {
                throw new IllegalStateException("Could not create instance of " + String.valueOf(cls) + ", make sure that it is a public concrete class with a public no-argument constructor", e3);
            } catch (InvocationTargetException e4) {
                throw new IllegalStateException("Could not create instance of " + String.valueOf(cls) + ", the constructor must not throw an exception", e4);
            }
        }
        return arrayList;
    }

    @Override // org.junit.internal.builders.AllDefaultPossibilitiesBuilder, org.junit.runners.model.RunnerBuilder
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        Iterator<RunnerBuilder> it = this.customRunnerBuilders.iterator();
        while (it.hasNext()) {
            Runner runnerSafeRunnerForClass = it.next().safeRunnerForClass(testClass);
            if (runnerSafeRunnerForClass != null) {
                return runnerSafeRunnerForClass;
            }
        }
        return super.runnerForClass(testClass);
    }

    @Override // org.junit.internal.builders.AllDefaultPossibilitiesBuilder
    protected JUnit4Builder junit4Builder() {
        return this.androidJUnit4Builder;
    }

    @Override // org.junit.internal.builders.AllDefaultPossibilitiesBuilder
    protected JUnit3Builder junit3Builder() {
        return this.androidJUnit3Builder;
    }

    @Override // org.junit.internal.builders.AllDefaultPossibilitiesBuilder
    protected AnnotatedBuilder annotatedBuilder() {
        return this.androidAnnotatedBuilder;
    }

    @Override // org.junit.internal.builders.AllDefaultPossibilitiesBuilder
    protected IgnoredBuilder ignoredBuilder() {
        return this.ignoredBuilder;
    }

    @Override // org.junit.internal.builders.AllDefaultPossibilitiesBuilder
    protected RunnerBuilder suiteMethodBuilder() {
        return this.androidSuiteBuilder;
    }
}

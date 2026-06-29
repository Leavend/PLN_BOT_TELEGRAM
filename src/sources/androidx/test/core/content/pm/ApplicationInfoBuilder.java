package androidx.test.core.content.pm;

import android.content.pm.ApplicationInfo;
import androidx.test.internal.util.Checks;

/* loaded from: classes5.dex */
public final class ApplicationInfoBuilder {
    private String name;
    private String packageName;

    private ApplicationInfoBuilder() {
    }

    public static ApplicationInfoBuilder newBuilder() {
        return new ApplicationInfoBuilder();
    }

    public ApplicationInfoBuilder setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public ApplicationInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ApplicationInfo build() {
        Checks.checkNotNull(this.packageName, "Mandatory field 'packageName' missing.");
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.name = this.name;
        applicationInfo.packageName = this.packageName;
        return applicationInfo;
    }
}

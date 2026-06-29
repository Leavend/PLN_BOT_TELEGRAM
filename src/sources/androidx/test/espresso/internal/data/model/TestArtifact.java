package androidx.test.espresso.internal.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TestArtifact.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Landroidx/test/espresso/internal/data/model/TestArtifact;", "", "filepath", "", "contentType", "(Ljava/lang/String;Ljava/lang/String;)V", "getContentType", "()Ljava/lang/String;", "getFilepath", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "third_party.android.androidx_test.espresso.core.java.androidx.test.espresso.internal.data.model_model"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final /* data */ class TestArtifact {
    private final String contentType;
    private final String filepath;

    public TestArtifact(String filepath, String contentType) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.filepath = filepath;
        this.contentType = contentType;
    }

    public static /* synthetic */ TestArtifact copy$default(TestArtifact testArtifact, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = testArtifact.filepath;
        }
        if ((i & 2) != 0) {
            str2 = testArtifact.contentType;
        }
        return testArtifact.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFilepath() {
        return this.filepath;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContentType() {
        return this.contentType;
    }

    public final TestArtifact copy(String filepath, String contentType) {
        Intrinsics.checkNotNullParameter(filepath, "filepath");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        return new TestArtifact(filepath, contentType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TestArtifact)) {
            return false;
        }
        TestArtifact testArtifact = (TestArtifact) other;
        return Intrinsics.areEqual(this.filepath, testArtifact.filepath) && Intrinsics.areEqual(this.contentType, testArtifact.contentType);
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final String getFilepath() {
        return this.filepath;
    }

    public int hashCode() {
        return (this.filepath.hashCode() * 31) + this.contentType.hashCode();
    }

    public String toString() {
        return "TestArtifact(filepath=" + this.filepath + ", contentType=" + this.contentType + ")";
    }
}

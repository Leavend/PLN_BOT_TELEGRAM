package androidx.compose.animation.core;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidPathMeasure_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.apache.http.cookie.ClientCookie;

/* compiled from: PathEasing.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/compose/animation/core/PathEasing;", "Landroidx/compose/animation/core/Easing;", ClientCookie.PATH_ATTR, "Landroidx/compose/ui/graphics/Path;", "(Landroidx/compose/ui/graphics/Path;)V", "offsetX", "", "offsetY", "transform", "", "fraction", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class PathEasing implements Easing {
    public static final int $stable = 0;
    private final float[] offsetX;
    private final float[] offsetY;

    public PathEasing(Path path) {
        PathMeasure PathMeasure = AndroidPathMeasure_androidKt.PathMeasure();
        PathMeasure.setPath(path, false);
        float length = PathMeasure.getLength();
        if (!(length > 0.0f)) {
            throw new IllegalArgumentException("Path cannot be zero in length. Ensure that supplied Path starts at [0,0] and ends at [1,1]".toString());
        }
        int i = ((int) (length / 0.002f)) + 1;
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = 0.0f;
        }
        this.offsetX = fArr;
        float[] fArr2 = new float[i];
        for (int i3 = 0; i3 < i; i3++) {
            fArr2[i3] = 0.0f;
        }
        this.offsetY = fArr2;
        for (int i4 = 0; i4 < i; i4++) {
            long jMo3312getPositiontuRUvjQ = PathMeasure.mo3312getPositiontuRUvjQ((i4 * length) / (i - 1));
            this.offsetX[i4] = Offset.m3173getXimpl(jMo3312getPositiontuRUvjQ);
            this.offsetY[i4] = Offset.m3174getYimpl(jMo3312getPositiontuRUvjQ);
            if (i4 > 0) {
                float[] fArr3 = this.offsetX;
                if (fArr3[i4] < fArr3[i4 - 1]) {
                    throw new IllegalArgumentException("Path needs to be continuously increasing");
                }
            }
        }
    }

    @Override // androidx.compose.animation.core.Easing
    public float transform(float fraction) {
        if (fraction <= 0.0f) {
            return 0.0f;
        }
        if (fraction >= 1.0f) {
            return 1.0f;
        }
        int iBinarySearch$default = ArraysKt.binarySearch$default(this.offsetX, fraction, 0, 0, 6, (Object) null);
        if (iBinarySearch$default > 0) {
            return this.offsetY[iBinarySearch$default];
        }
        int iAbs = Math.abs(iBinarySearch$default);
        float[] fArr = this.offsetX;
        if (iAbs >= fArr.length - 1) {
            return ArraysKt.last(this.offsetY);
        }
        int i = iAbs + 1;
        float f = fArr[i];
        float f2 = fArr[iAbs];
        float f3 = (fraction - f2) / (f - f2);
        float[] fArr2 = this.offsetY;
        float f4 = fArr2[iAbs];
        return f4 + (f3 * (fArr2[i] - f4));
    }
}

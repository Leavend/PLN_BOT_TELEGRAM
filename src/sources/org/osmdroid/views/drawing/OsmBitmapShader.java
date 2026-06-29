package org.osmdroid.views.drawing;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Shader;
import org.osmdroid.util.PointL;
import org.osmdroid.views.Projection;

@Deprecated
/* loaded from: classes3.dex */
public class OsmBitmapShader extends BitmapShader {
    private static final PointL sPoint = new PointL();
    private int mBitmapHeight;
    private int mBitmapWidth;
    private final Matrix mMatrix;

    public OsmBitmapShader(Bitmap bitmap, Shader.TileMode tileMode, Shader.TileMode tileMode2) {
        super(bitmap, tileMode, tileMode2);
        this.mMatrix = new Matrix();
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
    }

    public void onDrawCycle(Projection projection) {
        projection.toMercatorPixels(0, 0, sPoint);
        this.mMatrix.setTranslate((-r0.x) % this.mBitmapWidth, (-r0.y) % this.mBitmapHeight);
        setLocalMatrix(this.mMatrix);
    }
}

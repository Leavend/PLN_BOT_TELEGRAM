package id.go.bpsfasih.utils.helper;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/ImageHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ImageHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: ImageHelper.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0016\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0011J\u0016\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001b¨\u0006\u001c"}, d2 = {"Lid/go/bpsfasih/utils/helper/ImageHelper$Companion;", "", "()V", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "flip", "Landroid/graphics/Bitmap;", "bitmap", "horizontal", "", "vertical", "getCompressedBitmap", "imagePath", "", "getRealPathFromURI", "context", "Landroid/app/Activity;", "contentUri", "Landroid/net/Uri;", "modifyOrientation", "image_absolute_path", "rotate", "degrees", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            Intrinsics.checkNotNullParameter(image_absolute_path, "image_absolute_path");
            int attributeInt = new ExifInterface(image_absolute_path).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 2) {
                return flip(bitmap, true, false);
            }
            if (attributeInt == 3) {
                return rotate(bitmap, 180.0f);
            }
            if (attributeInt == 4) {
                return flip(bitmap, false, true);
            }
            if (attributeInt != 6) {
                return attributeInt != 8 ? bitmap : rotate(bitmap, 270.0f);
            }
            return rotate(bitmap, 90.0f);
        }

        public final Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            Matrix matrix = new Matrix();
            matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(bitmap, 0, …map.height, matrix, true)");
            return bitmapCreateBitmap;
        }

        public final Bitmap rotate(Bitmap bitmap, float degrees) {
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            Matrix matrix = new Matrix();
            matrix.postRotate(degrees);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(bitmap, 0, …map.height, matrix, true)");
            return bitmapCreateBitmap;
        }

        public final String getRealPathFromURI(Activity context, Uri contentUri) throws IllegalArgumentException {
            Intrinsics.checkNotNullParameter(context, "context");
            Cursor cursorManagedQuery = context.managedQuery(contentUri, new String[]{"_data"}, null, null, null);
            int columnIndexOrThrow = cursorManagedQuery.getColumnIndexOrThrow("_data");
            cursorManagedQuery.moveToFirst();
            String string = cursorManagedQuery.getString(columnIndexOrThrow);
            Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(columnIndex)");
            return string;
        }

        public final Bitmap getCompressedBitmap(String imagePath) {
            Bitmap bitmapCreateBitmap;
            Intrinsics.checkNotNullParameter(imagePath, "imagePath");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(imagePath, options);
            int i = options.outHeight;
            int i2 = options.outWidth;
            float f = i2;
            float f2 = i;
            float f3 = f / f2;
            if (f2 > 1920.0f || f > 1080.0f) {
                if (f3 < 0.5625f) {
                    i2 = (int) ((1920.0f / f2) * f);
                    i = (int) 1920.0f;
                } else {
                    i = f3 > 0.5625f ? (int) ((1080.0f / f) * f2) : (int) 1920.0f;
                    i2 = (int) 1080.0f;
                }
            }
            options.inSampleSize = calculateInSampleSize(options, i2, i);
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[16384];
            try {
                bitmapDecodeFile = BitmapFactory.decodeFile(imagePath, options);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
            try {
                bitmapCreateBitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
                bitmapCreateBitmap = null;
            }
            float f4 = i2;
            float f5 = f4 / options.outWidth;
            float f6 = i;
            float f7 = f6 / options.outHeight;
            float f8 = f4 / 2.0f;
            float f9 = f6 / 2.0f;
            Matrix matrix = new Matrix();
            matrix.setScale(f5, f7, f8, f9);
            if (bitmapCreateBitmap == null) {
                return null;
            }
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.setMatrix(matrix);
            canvas.drawBitmap(bitmapDecodeFile, f8 - (bitmapDecodeFile.getWidth() / 2), f9 - (bitmapDecodeFile.getHeight() / 2), new Paint(2));
            try {
                int attributeInt = new ExifInterface(imagePath).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0);
                Matrix matrix2 = new Matrix();
                if (attributeInt == 3) {
                    matrix2.postRotate(180.0f);
                } else if (attributeInt == 6) {
                    matrix2.postRotate(90.0f);
                } else if (attributeInt == 8) {
                    matrix2.postRotate(270.0f);
                }
                bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), matrix2, true);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Intrinsics.checkNotNull(bitmapCreateBitmap);
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }

        private final int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
            int iRound;
            int i = options.outHeight;
            int i2 = options.outWidth;
            if (i > reqHeight || i2 > reqWidth) {
                iRound = Math.round(i / reqHeight);
                int iRound2 = Math.round(i2 / reqWidth);
                if (iRound >= iRound2) {
                    iRound = iRound2;
                }
            } else {
                iRound = 1;
            }
            while ((i2 * i) / (iRound * iRound) > reqWidth * reqHeight * 2) {
                iRound++;
            }
            return iRound;
        }
    }
}

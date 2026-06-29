package id.go.bpsfasih.utils.helper;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.utils.helper.ImageHelper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: CameraHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/utils/helper/CameraHelper;", "", "()V", "Companion", "OnBarcodeListener", "OnCameraOrGalleryListener", "OnSignatureListener", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CameraHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: CameraHelper.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/utils/helper/CameraHelper$OnBarcodeListener;", "", "onBarcodeResult", "", "barcode", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface OnBarcodeListener {
        void onBarcodeResult(String barcode);
    }

    /* compiled from: CameraHelper.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H&J\u001a\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH&¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/utils/helper/CameraHelper$OnCameraOrGalleryListener;", "", "onCameraResultNew", "", "bitmap", "Landroid/graphics/Bitmap;", "imageFile", "Ljava/io/File;", "fileNameFormGear", "", "onCancel", "onGalleryResult", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface OnCameraOrGalleryListener {
        void onCameraResultNew(Bitmap bitmap, File imageFile, String fileNameFormGear);

        void onCancel();

        void onGalleryResult(Bitmap bitmap, String fileNameFormGear);
    }

    /* compiled from: CameraHelper.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/utils/helper/CameraHelper$OnSignatureListener;", "", "onSignatureResult", "", ClientCookie.PATH_ATTR, "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface OnSignatureListener {
        void onSignatureResult(String path);
    }

    /* compiled from: CameraHelper.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\f\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\bJ\u0018\u0010\u000f\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000b\u001a\u00020\bJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/utils/helper/CameraHelper$Companion;", "", "()V", "checkPermission", "", "activity", "Landroid/app/Activity;", "permission", "", "getPhotoFile", "Ljava/io/File;", "fileName", "openCamera", "", "Lid/go/bpsfasih/BaseClassActivityNew;", "openGallery", "processingCamera", "Landroid/graphics/Bitmap;", "imageUri", "Landroid/net/Uri;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void openCamera(BaseClassActivityNew activity, String fileName) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            if (activity != null) {
                BaseClassActivityNew baseClassActivityNew = activity;
                if (checkPermission(baseClassActivityNew, "android.permission.CAMERA")) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    File photoFile = getPhotoFile(fileName, baseClassActivityNew);
                    if (photoFile != null) {
                        activity.setPhotoFile(photoFile);
                        activity.setFileNameFormGear(fileName);
                        BaseClassActivityNew baseClassActivityNew2 = activity;
                        Uri uriForFile = FileProvider.getUriForFile(baseClassActivityNew2, "id.go.bpsfasih.fileprovider", photoFile);
                        intent.putExtra("output", uriForFile);
                        intent.addFlags(3);
                        intent.setClipData(ClipData.newRawUri("captured_photo", uriForFile));
                        List<ResolveInfo> listQueryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 65536);
                        Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "activity.packageManager.…                        )");
                        Iterator<T> it = listQueryIntentActivities.iterator();
                        while (it.hasNext()) {
                            activity.grantUriPermission(((ResolveInfo) it.next()).activityInfo.packageName, uriForFile, 3);
                        }
                        if (intent.resolveActivity(activity.getPackageManager()) != null) {
                            activity.startActivityForResult(intent, 1);
                            return;
                        } else {
                            Toast.makeText(baseClassActivityNew2, "#1 Gagal mengambil gambar", 0).show();
                            return;
                        }
                    }
                    Toast.makeText(activity, "#2 Gagal mengambil gambar", 0).show();
                }
            }
        }

        public final void openGallery(BaseClassActivityNew activity, String fileName) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            if (activity != null) {
                activity.startActivityForResult(intent, 2);
            }
            if (activity == null) {
                return;
            }
            activity.setFileNameFormGear(fileName);
        }

        private final File getPhotoFile(String fileName, Activity activity) {
            try {
                return File.createTempFile(fileName, ".jpg", activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            } catch (IOException | Exception unused) {
                return null;
            }
        }

        private final boolean checkPermission(Activity activity, String permission) {
            Intrinsics.checkNotNull(activity);
            if (ContextCompat.checkSelfPermission(activity, permission) == 0) {
                return true;
            }
            PermissionHelper.INSTANCE.requestPermission(activity, permission);
            return false;
        }

        public final Bitmap processingCamera(Activity activity, Uri imageUri) throws IOException {
            Bitmap bitmapDecodeBitmap;
            Intrinsics.checkNotNullParameter(activity, "activity");
            Bitmap bitmap = null;
            if (Build.VERSION.SDK_INT >= 29) {
                try {
                    ContentResolver contentResolver = activity.getContentResolver();
                    Intrinsics.checkNotNull(imageUri);
                    ImageDecoder.Source sourceCreateSource = ImageDecoder.createSource(contentResolver, imageUri);
                    Intrinsics.checkNotNullExpressionValue(sourceCreateSource, "createSource(\n          …                        )");
                    bitmapDecodeBitmap = ImageDecoder.decodeBitmap(sourceCreateSource);
                    Intrinsics.checkNotNullExpressionValue(bitmapDecodeBitmap, "decodeBitmap(source)");
                } catch (IOException e) {
                    e.printStackTrace();
                    bitmapDecodeBitmap = null;
                }
            } else {
                bitmapDecodeBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), imageUri);
                Intrinsics.checkNotNullExpressionValue(bitmapDecodeBitmap, "getBitmap(activity.contentResolver, imageUri)");
            }
            ImageHelper.Companion companion = ImageHelper.INSTANCE;
            if (bitmapDecodeBitmap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bitmap");
            } else {
                bitmap = bitmapDecodeBitmap;
            }
            companion.modifyOrientation(bitmap, ImageHelper.INSTANCE.getRealPathFromURI(activity, imageUri));
            return ImageHelper.INSTANCE.getCompressedBitmap(ImageHelper.INSTANCE.getRealPathFromURI(activity, imageUri));
        }
    }
}

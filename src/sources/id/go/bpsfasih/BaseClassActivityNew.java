package id.go.bpsfasih;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.WindowCompat;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.messaging.Constants;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.ui.bantuan.BantuanActivity;
import id.go.bpsfasih.ui.onboarding.OnBoardingActivity;
import id.go.bpsfasih.utils.FileUtil;
import id.go.bpsfasih.utils.helper.CameraHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.FormEngineHelper;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.bouncycastle.i18n.MessageBundle;

/* compiled from: BaseClassActivityNew.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001:\u0001jB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020\u001cH\u0002J\u0006\u0010#\u001a\u00020$J\"\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u0012\u0010+\u001a\u00020$2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014JA\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020'2\u0006\u00100\u001a\u00020\b2\n\b\u0002\u00101\u001a\u0004\u0018\u00010'2\n\b\u0002\u00102\u001a\u0004\u0018\u0001032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\bH\u0004¢\u0006\u0002\u00105J\u0010\u00106\u001a\u00020$2\u0006\u00107\u001a\u00020\u0014H\u0004J\u0010\u00108\u001a\u00020$2\u0006\u00109\u001a\u00020\u0016H\u0004J\u0018\u00108\u001a\u00020$2\u0006\u00109\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0004J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u00020\u0018H\u0004J\u0010\u0010<\u001a\u00020$2\u0006\u0010=\u001a\u00020\u001aH\u0004J\b\u0010>\u001a\u00020$H\u0004Je\u0010?\u001a\u00020$2\b\u00100\u001a\u0004\u0018\u00010\b2\b\u0010@\u001a\u0004\u0018\u00010\b2\b\u0010A\u001a\u0004\u0018\u00010'2\b\u0010B\u001a\u0004\u0018\u00010\b2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010\b2\b\u0010F\u001a\u0004\u0018\u00010D2\b\b\u0002\u0010G\u001a\u00020\u001c2\b\b\u0002\u0010H\u001a\u00020\u001c¢\u0006\u0002\u0010IJ\u008d\u0001\u0010J\u001a\u00020$2\b\u00100\u001a\u0004\u0018\u00010\b2\b\u0010K\u001a\u0004\u0018\u00010'2\b\u0010@\u001a\u0004\u0018\u00010\b2\b\u0010L\u001a\u0004\u0018\u00010'2\b\u0010A\u001a\u0004\u0018\u00010'2\b\u0010B\u001a\u0004\u0018\u00010\b2\b\u0010M\u001a\u0004\u0018\u00010'2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010\b2\b\u0010N\u001a\u0004\u0018\u00010'2\b\u0010F\u001a\u0004\u0018\u00010D2\b\u0010O\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010H\u001a\u00020\u001c¢\u0006\u0002\u0010PJh\u0010Q\u001a\u00020$2\u0006\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010\b2\b\u0010U\u001a\u0004\u0018\u00010\b2\b\u0010V\u001a\u0004\u0018\u00010\b2\b\u0010B\u001a\u0004\u0018\u00010\b2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010\b2\b\u0010F\u001a\u0004\u0018\u00010D2\b\u0010W\u001a\u0004\u0018\u00010D2\b\b\u0002\u0010X\u001a\u00020\u001cJ>\u0010Y\u001a\u00020$26\u0010Z\u001a2\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(^\u0012\u0013\u0012\u00110\b¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(_\u0012\u0004\u0012\u00020$0[J\u0006\u0010`\u001a\u00020$J>\u0010a\u001a\u00020$26\u0010Z\u001a2\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(^\u0012\u0013\u0012\u00110\b¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(_\u0012\u0004\u0012\u00020$0[J\u0010\u0010b\u001a\u00020$2\u0006\u0010c\u001a\u00020\bH\u0002J\u0006\u0010d\u001a\u00020$J\u000e\u0010d\u001a\u00020$2\u0006\u0010e\u001a\u00020\bJ\u001a\u0010f\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020\u00042\b\b\u0002\u0010h\u001a\u00020iH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006k"}, d2 = {"Lid/go/bpsfasih/BaseClassActivityNew;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "compressedImage", "Ljava/io/File;", "customProgressBar", "Lid/go/bpsfasih/CustomProgressBar;", "fileNameFormGear", "", "getFileNameFormGear", "()Ljava/lang/String;", "setFileNameFormGear", "(Ljava/lang/String;)V", "imageUri", "Landroid/net/Uri;", "getImageUri", "()Landroid/net/Uri;", "setImageUri", "(Landroid/net/Uri;)V", "mBarcodeListener", "Lid/go/bpsfasih/utils/helper/CameraHelper$OnBarcodeListener;", "mCameraOrGalleryListener", "Lid/go/bpsfasih/utils/helper/CameraHelper$OnCameraOrGalleryListener;", "mOnPermissionResult", "Lid/go/bpsfasih/BaseClassActivityNew$OnPermissionResult;", "mSignatureListener", "Lid/go/bpsfasih/utils/helper/CameraHelper$OnSignatureListener;", "needBitmap", "", "photoFile", "getPhotoFile", "()Ljava/io/File;", "setPhotoFile", "(Ljava/io/File;)V", "canShowWindowSafely", "hideProgressBar", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setAppBar", "visibilityBackButton", MessageBundle.TITLE_ENTRY, "menu", "menuListener", "Landroid/view/MenuItem$OnMenuItemClickListener;", "periodeId", "(ILjava/lang/String;Ljava/lang/Integer;Landroid/view/MenuItem$OnMenuItemClickListener;Ljava/lang/String;)V", "setBarcodeListener", "barcodeListener", "setCameraOrGalleryListener", "cameraOrGalleryListener", "setOnPermissionResult", "onPermissionResult", "setSignatureListener", "signatureListener", "setStatusBarColor", "showAlertDialog", "message", "icon", "rButtonText", "rButtonListener", "Landroid/view/View$OnClickListener;", "lButtonText", "lButtonListener", "showClose", "cancelAble", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Landroid/view/View$OnClickListener;Ljava/lang/String;Landroid/view/View$OnClickListener;ZZ)V", "showAlertDialogColor", "titleColor", "messageColor", "rButtonTextColor", "lButtonTextColor", "tutupButtonColor", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Landroid/view/View$OnClickListener;Ljava/lang/String;Ljava/lang/Integer;Landroid/view/View$OnClickListener;Ljava/lang/Integer;Z)V", "showAlertDialogKodeVerifikasi", "dialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "judul", "deskripsi_1", "deskripsi_2", "tButtonListener", "isRight", "showAlertDialogKodeVerifikasiAdmin", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "accessToken", "showAlertDialogKodeVerifikasiLogout", "showAlertDialogToken", "showError", "errorMessage", "showProgressBar", Constants.ScionAnalytics.PARAM_LABEL, "waitForCameraFileReady", StorageChooser.FILE_PICKER, "timeoutMs", "", "OnPermissionResult", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BaseClassActivityNew extends AppCompatActivity {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private File compressedImage;
    private CustomProgressBar customProgressBar;
    private String fileNameFormGear;
    private Uri imageUri;
    private CameraHelper.OnBarcodeListener mBarcodeListener;
    private CameraHelper.OnCameraOrGalleryListener mCameraOrGalleryListener;
    private OnPermissionResult mOnPermissionResult;
    private CameraHelper.OnSignatureListener mSignatureListener;
    private boolean needBitmap;
    private File photoFile;

    /* compiled from: BaseClassActivityNew.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/BaseClassActivityNew$OnPermissionResult;", "", "permissionResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface OnPermissionResult {
        void permissionResult(int requestCode, int resultCode, Intent data);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public final Uri getImageUri() {
        return this.imageUri;
    }

    public final void setImageUri(Uri uri) {
        this.imageUri = uri;
    }

    public final File getPhotoFile() {
        return this.photoFile;
    }

    public final void setPhotoFile(File file) {
        this.photoFile = file;
    }

    public final String getFileNameFormGear() {
        return this.fileNameFormGear;
    }

    public final void setFileNameFormGear(String str) {
        this.fileNameFormGear = str;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 35) {
            WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
        }
    }

    private final boolean canShowWindowSafely() {
        return (isFinishing() || isDestroyed()) ? false : true;
    }

    protected final void setCameraOrGalleryListener(CameraHelper.OnCameraOrGalleryListener cameraOrGalleryListener) {
        Intrinsics.checkNotNullParameter(cameraOrGalleryListener, "cameraOrGalleryListener");
        this.mCameraOrGalleryListener = cameraOrGalleryListener;
    }

    protected final void setCameraOrGalleryListener(CameraHelper.OnCameraOrGalleryListener cameraOrGalleryListener, boolean needBitmap) {
        Intrinsics.checkNotNullParameter(cameraOrGalleryListener, "cameraOrGalleryListener");
        this.mCameraOrGalleryListener = cameraOrGalleryListener;
        this.needBitmap = needBitmap;
    }

    protected final void setBarcodeListener(CameraHelper.OnBarcodeListener barcodeListener) {
        Intrinsics.checkNotNullParameter(barcodeListener, "barcodeListener");
        this.mBarcodeListener = barcodeListener;
    }

    protected final void setSignatureListener(CameraHelper.OnSignatureListener signatureListener) {
        Intrinsics.checkNotNullParameter(signatureListener, "signatureListener");
        this.mSignatureListener = signatureListener;
    }

    protected final void setOnPermissionResult(OnPermissionResult onPermissionResult) {
        Intrinsics.checkNotNullParameter(onPermissionResult, "onPermissionResult");
        this.mOnPermissionResult = onPermissionResult;
    }

    static /* synthetic */ boolean waitForCameraFileReady$default(BaseClassActivityNew baseClassActivityNew, File file, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: waitForCameraFileReady");
        }
        if ((i & 2) != 0) {
            j = 3000;
        }
        return baseClassActivityNew.waitForCameraFileReady(file, j);
    }

    private final boolean waitForCameraFileReady(File file, long timeoutMs) throws InterruptedException {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = -1;
        int i = 0;
        while (SystemClock.elapsedRealtime() - jElapsedRealtime < timeoutMs) {
            long length = file.exists() ? file.length() : -1L;
            if (length <= 0 || length != j) {
                i = 0;
            } else {
                i++;
                if (i >= 2) {
                    return true;
                }
            }
            Thread.sleep(150L);
            j = length;
        }
        return file.exists() && file.length() > 0;
    }

    protected final void setStatusBarColor() {
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        Window window2 = getWindow();
        View decorView = window2 != null ? window2.getDecorView() : null;
        if (decorView == null) {
            return;
        }
        decorView.setSystemUiVisibility(0);
    }

    public static /* synthetic */ void setAppBar$default(BaseClassActivityNew baseClassActivityNew, int i, String str, Integer num, MenuItem.OnMenuItemClickListener onMenuItemClickListener, String str2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setAppBar");
        }
        baseClassActivityNew.setAppBar(i, str, (i2 & 4) != 0 ? null : num, (i2 & 8) != 0 ? null : onMenuItemClickListener, (i2 & 16) != 0 ? null : str2);
    }

    protected final void setAppBar(int visibilityBackButton, String title, final Integer menu, final MenuItem.OnMenuItemClickListener menuListener, String periodeId) {
        Intrinsics.checkNotNullParameter(title, "title");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_activity);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        TextView textView = (TextView) _$_findCachedViewById(R.id.title_toolbar);
        if (textView != null) {
            textView.setText(title);
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.back_button);
        if (imageView != null) {
            imageView.setVisibility(visibilityBackButton);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.back_button);
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda16
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivityNew.setAppBar$lambda$0(this.f$0, view);
                }
            });
        }
        ((TextView) bottomSheetDialog.findViewById(R.id.halaman_tv)).setText(title);
        if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_DEV, false, 2, null)) {
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda17
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivityNew.setAppBar$lambda$1(bottomSheetDialog, view);
                }
            });
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setVisibility(8);
            ((TextView) bottomSheetDialog.findViewById(R.id.modeBottomSheer_tv)).setText("Development");
        } else if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD, false, 2, null)) {
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda18
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivityNew.setAppBar$lambda$2(bottomSheetDialog, view);
                }
            });
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setVisibility(8);
            ((TextView) bottomSheetDialog.findViewById(R.id.modeBottomSheer_tv)).setText("Production");
        }
        if (menu != null && menuListener != null) {
            ((ImageView) _$_findCachedViewById(R.id.menu_toolbar_iv)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.menu_toolbar_iv)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivityNew.setAppBar$lambda$4(this.f$0, menu, menuListener, view);
                }
            });
        }
        ((TextView) bottomSheetDialog.findViewById(R.id.versi_formgear_tv)).setText(FormEngineHelper.INSTANCE.getFormEngineVersion(CommonCons.FORMGEAR_ID_DUMMY));
        ((TextView) bottomSheetDialog.findViewById(R.id.versi_aplikasi_tv)).setText(BuildConfig.VERSION_NAME);
        ((Button) bottomSheetDialog.findViewById(R.id.laporkan_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.setAppBar$lambda$5(this.f$0, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.setAppBar$lambda$6(bottomSheetDialog, view);
            }
        });
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAppBar$lambda$0(BaseClassActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAppBar$lambda$1(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAppBar$lambda$2(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAppBar$lambda$4(BaseClassActivityNew this$0, Integer num, final MenuItem.OnMenuItemClickListener onMenuItemClickListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PopupMenu popupMenu = new PopupMenu(this$0, (ImageView) this$0._$_findCachedViewById(R.id.menu_toolbar_iv));
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "popup.menuInflater");
        menuInflater.inflate(num.intValue(), popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda1
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return BaseClassActivityNew.setAppBar$lambda$4$lambda$3(onMenuItemClickListener, menuItem);
            }
        });
        popupMenu.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setAppBar$lambda$4$lambda$3(MenuItem.OnMenuItemClickListener onMenuItemClickListener, MenuItem menuItem) {
        onMenuItemClickListener.onMenuItemClick(menuItem);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAppBar$lambda$5(BaseClassActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BantuanActivity.Companion companion = BantuanActivity.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        companion.startActivity(applicationContext, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setAppBar$lambda$6(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public final void showProgressBar() {
        if (canShowWindowSafely()) {
            if (this.customProgressBar == null) {
                this.customProgressBar = new CustomProgressBar(this, null, 2, null);
            }
            if (this.customProgressBar != null) {
                new CustomProgressBar(this, null, 2, null);
            }
            runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda29
                @Override // java.lang.Runnable
                public final void run() {
                    BaseClassActivityNew.showProgressBar$lambda$8(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProgressBar$lambda$8(BaseClassActivityNew this$0) {
        CustomProgressBar customProgressBar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.canShowWindowSafely() || (customProgressBar = this$0.customProgressBar) == null) {
            return;
        }
        customProgressBar.showLoading();
    }

    public final void showProgressBar(String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        if (canShowWindowSafely()) {
            if (this.customProgressBar == null) {
                this.customProgressBar = new CustomProgressBar(this, null, 2, null);
            }
            if (this.customProgressBar != null) {
                new CustomProgressBar(this, label);
            }
            runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    BaseClassActivityNew.showProgressBar$lambda$10(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProgressBar$lambda$10(BaseClassActivityNew this$0) {
        CustomProgressBar customProgressBar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.canShowWindowSafely() || (customProgressBar = this$0.customProgressBar) == null) {
            return;
        }
        customProgressBar.showLoading();
    }

    public final void hideProgressBar() {
        runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                BaseClassActivityNew.hideProgressBar$lambda$11(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void hideProgressBar$lambda$11(BaseClassActivityNew this$0) {
        CustomProgressBar customProgressBar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.customProgressBar == null) {
            this$0.customProgressBar = new CustomProgressBar(this$0, null, 2, null);
        }
        if (this$0.isDestroyed()) {
            return;
        }
        CustomProgressBar customProgressBar2 = this$0.customProgressBar;
        boolean z = false;
        if (customProgressBar2 != null && customProgressBar2.isShowing()) {
            z = true;
        }
        if (!z || (customProgressBar = this$0.customProgressBar) == null) {
            return;
        }
        customProgressBar.dismiss();
    }

    public static /* synthetic */ void showAlertDialog$default(BaseClassActivityNew baseClassActivityNew, String str, String str2, Integer num, String str3, View.OnClickListener onClickListener, String str4, View.OnClickListener onClickListener2, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showAlertDialog");
        }
        baseClassActivityNew.showAlertDialog(str, str2, num, str3, onClickListener, str4, onClickListener2, (i & 128) != 0 ? true : z, (i & 256) != 0 ? false : z2);
    }

    public final void showAlertDialog(String title, String message, Integer icon, String rButtonText, final View.OnClickListener rButtonListener, String lButtonText, final View.OnClickListener lButtonListener, boolean showClose, boolean cancelAble) {
        if (canShowWindowSafely()) {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_dialog);
            Window window = bottomSheetDialog.getWindow();
            Intrinsics.checkNotNull(window);
            window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            bottomSheetDialog.setCanceledOnTouchOutside(false);
            if (title != null) {
                ((TextView) bottomSheetDialog.findViewById(R.id.judul_bottomDialog)).setText(title);
            } else {
                ((TextView) bottomSheetDialog.findViewById(R.id.judul_bottomDialog)).setVisibility(8);
            }
            if (message != null) {
                ((TextView) bottomSheetDialog.findViewById(R.id.deskripsi_bottomDialog)).setText(message);
            } else {
                ((TextView) bottomSheetDialog.findViewById(R.id.deskripsi_bottomDialog)).setVisibility(8);
            }
            if (icon != null) {
                ((ImageView) bottomSheetDialog.findViewById(R.id.icon_bottomDialog)).setImageResource(icon.intValue());
            } else {
                ((ImageView) bottomSheetDialog.findViewById(R.id.icon_bottomDialog)).setVisibility(8);
            }
            bottomSheetDialog.setCancelable(cancelAble);
            if (rButtonListener != null) {
                ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setText(rButtonText);
                ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialog$lambda$12(bottomSheetDialog, rButtonListener, view);
                    }
                });
            } else {
                ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setVisibility(8);
            }
            if (lButtonListener != null) {
                ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setText(lButtonText);
                ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialog$lambda$13(bottomSheetDialog, lButtonListener, view);
                    }
                });
            } else {
                ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setVisibility(8);
            }
            if (showClose) {
                ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialog$lambda$14(bottomSheetDialog, view);
                    }
                });
            } else {
                ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).setVisibility(8);
            }
            runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    BaseClassActivityNew.showAlertDialog$lambda$15(this.f$0, bottomSheetDialog);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$12(BottomSheetDialog dialog, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$13(BottomSheetDialog dialog, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
        onClickListener.onClick(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$14(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$15(BaseClassActivityNew this$0, BottomSheetDialog dialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (!this$0.canShowWindowSafely() || dialog.isShowing()) {
            return;
        }
        dialog.show();
    }

    public static /* synthetic */ void showAlertDialogColor$default(BaseClassActivityNew baseClassActivityNew, String str, Integer num, String str2, Integer num2, Integer num3, String str3, Integer num4, View.OnClickListener onClickListener, String str4, Integer num5, View.OnClickListener onClickListener2, Integer num6, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showAlertDialogColor");
        }
        baseClassActivityNew.showAlertDialogColor(str, num, str2, num2, num3, str3, num4, onClickListener, str4, num5, onClickListener2, num6, (i & 4096) != 0 ? false : z);
    }

    public final void showAlertDialogColor(String title, Integer titleColor, String message, Integer messageColor, Integer icon, String rButtonText, Integer rButtonTextColor, final View.OnClickListener rButtonListener, String lButtonText, Integer lButtonTextColor, final View.OnClickListener lButtonListener, Integer tutupButtonColor, boolean cancelAble) {
        if (canShowWindowSafely()) {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_dialog);
            Window window = bottomSheetDialog.getWindow();
            Intrinsics.checkNotNull(window);
            window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            bottomSheetDialog.setCanceledOnTouchOutside(false);
            if (title != null) {
                ((TextView) bottomSheetDialog.findViewById(R.id.judul_bottomDialog)).setText(title);
                TextView textView = (TextView) bottomSheetDialog.findViewById(R.id.judul_bottomDialog);
                Resources resources = getResources();
                Intrinsics.checkNotNull(titleColor);
                textView.setTextColor(resources.getColor(titleColor.intValue(), getTheme()));
            } else {
                ((TextView) bottomSheetDialog.findViewById(R.id.judul_bottomDialog)).setVisibility(8);
            }
            if (message != null) {
                ((TextView) bottomSheetDialog.findViewById(R.id.deskripsi_bottomDialog)).setText(message);
                TextView textView2 = (TextView) bottomSheetDialog.findViewById(R.id.deskripsi_bottomDialog);
                Resources resources2 = getResources();
                Intrinsics.checkNotNull(messageColor);
                textView2.setTextColor(resources2.getColor(messageColor.intValue(), getTheme()));
            } else {
                ((TextView) bottomSheetDialog.findViewById(R.id.deskripsi_bottomDialog)).setVisibility(8);
            }
            if (icon != null) {
                ((ImageView) bottomSheetDialog.findViewById(R.id.icon_bottomDialog)).setImageResource(icon.intValue());
            } else {
                ((ImageView) bottomSheetDialog.findViewById(R.id.icon_bottomDialog)).setVisibility(8);
            }
            bottomSheetDialog.setCancelable(cancelAble);
            if (rButtonListener != null) {
                ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setText(rButtonText);
                Button button = (Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog);
                Resources resources3 = getResources();
                Intrinsics.checkNotNull(rButtonTextColor);
                button.setBackground(resources3.getDrawable(rButtonTextColor.intValue(), getTheme()));
                ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda25
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialogColor$lambda$16(bottomSheetDialog, rButtonListener, view);
                    }
                });
            } else {
                ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setVisibility(8);
            }
            if (lButtonListener != null) {
                ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setText(lButtonText);
                Button button2 = (Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog);
                Resources resources4 = getResources();
                Intrinsics.checkNotNull(lButtonTextColor);
                button2.setBackground(resources4.getDrawable(lButtonTextColor.intValue(), getTheme()));
                ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda26
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialogColor$lambda$17(bottomSheetDialog, lButtonListener, view);
                    }
                });
            } else {
                ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setVisibility(8);
            }
            if (tutupButtonColor != null) {
                DrawableCompat.setTint(((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).getDrawable(), getResources().getColor(tutupButtonColor.intValue(), getTheme()));
                ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda27
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialogColor$lambda$18(bottomSheetDialog, view);
                    }
                });
            } else {
                ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).setVisibility(8);
            }
            runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda28
                @Override // java.lang.Runnable
                public final void run() {
                    BaseClassActivityNew.showAlertDialogColor$lambda$19(this.f$0, bottomSheetDialog);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogColor$lambda$16(BottomSheetDialog dialog, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogColor$lambda$17(BottomSheetDialog dialog, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
        onClickListener.onClick(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogColor$lambda$18(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogColor$lambda$19(BaseClassActivityNew this$0, BottomSheetDialog dialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (!this$0.canShowWindowSafely() || dialog.isShowing()) {
            return;
        }
        dialog.show();
    }

    public final void showAlertDialogKodeVerifikasiLogout() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        final String strValueOf = String.valueOf(RangesKt.random(new IntRange(100, 999), Random.INSTANCE));
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_dialog_kode_verifikasi);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        ((TextView) bottomSheetDialog.findViewById(R.id.code_tv)).setText(strValueOf);
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogKodeVerifikasiLogout$lambda$20(bottomSheetDialog, strValueOf, this, view);
            }
        });
        ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogKodeVerifikasiLogout$lambda$21(bottomSheetDialog, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogKodeVerifikasiLogout$lambda$22(bottomSheetDialog, view);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiLogout$lambda$20(BottomSheetDialog dialog, String code, final BaseClassActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(code, "$code");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((EditText) dialog.findViewById(R.id.code_et)).getText().toString().equals(code)) {
            FasihApp.INSTANCE.getSession().logout(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.BaseClassActivityNew$showAlertDialogKodeVerifikasiLogout$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Intent intent = new Intent(this.this$0, (Class<?>) OnBoardingActivity.class);
                    intent.setFlags(268468224);
                    this.this$0.startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this$0, "Kode verifikasi yang anda masukkan salah", 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiLogout$lambda$21(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiLogout$lambda$22(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static /* synthetic */ void showAlertDialogKodeVerifikasi$default(BaseClassActivityNew baseClassActivityNew, BottomSheetDialog bottomSheetDialog, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, String str5, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showAlertDialogKodeVerifikasi");
        }
        baseClassActivityNew.showAlertDialogKodeVerifikasi(bottomSheetDialog, str, str2, str3, str4, onClickListener, str5, onClickListener2, onClickListener3, (i & 512) != 0 ? true : z);
    }

    public final void showAlertDialogKodeVerifikasi(final BottomSheetDialog dialog, String judul, String deskripsi_1, String deskripsi_2, String rButtonText, final View.OnClickListener rButtonListener, String lButtonText, final View.OnClickListener lButtonListener, final View.OnClickListener tButtonListener, boolean isRight) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final String strValueOf = String.valueOf(RangesKt.random(new IntRange(100, 999), Random.INSTANCE));
        dialog.setContentView(R.layout.bottom_sheet_base_dialog_kode_verifikasi);
        Window window = dialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        if (judul != null) {
            ((TextView) dialog.findViewById(R.id.judul_bottomDialogKodeVerifikasi)).setText(judul);
        }
        if (deskripsi_1 != null) {
            ((TextView) dialog.findViewById(R.id.deskripsi_bottomDialogKodeVerifikasi_1)).setText(deskripsi_1);
        }
        if (deskripsi_2 != null) {
            ((TextView) dialog.findViewById(R.id.deskripsi_bottomDialogKodeVerifikasi_2)).setText(deskripsi_2);
        }
        ((TextView) dialog.findViewById(R.id.code_tv)).setText(strValueOf);
        if (isRight) {
            if (rButtonListener != null) {
                ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setText(rButtonText);
                ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda31
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialogKodeVerifikasi$lambda$23(dialog, strValueOf, rButtonListener, this, view);
                    }
                });
            }
            if (lButtonListener != null) {
                ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setText(lButtonText);
                ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda32
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialogKodeVerifikasi$lambda$24(lButtonListener, view);
                    }
                });
            }
        } else {
            if (lButtonListener != null) {
                ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setText(lButtonText);
                ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda33
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialogKodeVerifikasi$lambda$25(dialog, strValueOf, lButtonListener, this, view);
                    }
                });
            }
            if (rButtonListener != null) {
                ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setText(rButtonText);
                ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda34
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseClassActivityNew.showAlertDialogKodeVerifikasi$lambda$26(rButtonListener, view);
                    }
                });
            }
        }
        if (tButtonListener != null) {
            ((ImageView) dialog.findViewById(R.id.tutup_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda35
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivityNew.showAlertDialogKodeVerifikasi$lambda$27(tButtonListener, view);
                }
            });
        }
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$23(BottomSheetDialog dialog, String code, View.OnClickListener onClickListener, BaseClassActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(code, "$code");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!((EditText) dialog.findViewById(R.id.code_et)).getText().toString().equals(code)) {
            Toast.makeText(this$0, "Kode verifikasi yang anda masukkan salah", 1).show();
        } else if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$24(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$25(BottomSheetDialog dialog, String code, View.OnClickListener onClickListener, BaseClassActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(code, "$code");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!((EditText) dialog.findViewById(R.id.code_et)).getText().toString().equals(code)) {
            Toast.makeText(this$0, "Kode verifikasi yang anda masukkan salah", 1).show();
        } else if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$26(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$27(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public final void showAlertDialogKodeVerifikasiAdmin(final Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_dialog_kode_verifikasi_admin);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        final String str = "FASIH515";
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasiAdmin)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogKodeVerifikasiAdmin$lambda$28(bottomSheetDialog, str, this, callback, view);
            }
        });
        ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasiAdmin)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogKodeVerifikasiAdmin$lambda$29(bottomSheetDialog, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_bottomDialogKodeVerifikasiAdmin)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogKodeVerifikasiAdmin$lambda$30(bottomSheetDialog, view);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiAdmin$lambda$28(BottomSheetDialog dialog, String code, BaseClassActivityNew this$0, final Function2 callback, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(code, "$code");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        if (((EditText) dialog.findViewById(R.id.code_et_bottomDialogKodeVerifikasiAdmin)).getText().toString().equals(code)) {
            this$0.showAlertDialogToken(new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.BaseClassActivityNew$showAlertDialogKodeVerifikasiAdmin$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, String accessToken) {
                    Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                    callback.invoke(Boolean.valueOf(z), accessToken);
                }
            });
        } else {
            Toast.makeText(this$0, "Password yang anda masukkan salah", 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiAdmin$lambda$29(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiAdmin$lambda$30(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final void showAlertDialogToken(final Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_dialog_token);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasiAdmin)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogToken$lambda$31(bottomSheetDialog, callback, view);
            }
        });
        ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasiAdmin)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogToken$lambda$32(bottomSheetDialog, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_bottomDialogKodeVerifikasiAdmin)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivityNew.showAlertDialogToken$lambda$33(bottomSheetDialog, view);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogToken$lambda$31(BottomSheetDialog dialog, Function2 callback, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        callback.invoke(false, StringsKt.removePrefix(((EditText) dialog.findViewById(R.id.access_token_bottomDialogToken)).getText().toString(), (CharSequence) "Bearer "));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogToken$lambda$32(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogToken$lambda$33(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        String stringExtra;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 || resultCode != -1) {
            return;
        }
        if (requestCode == 1) {
            new Thread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BaseClassActivityNew.onActivityResult$lambda$35(this.f$0);
                }
            }).start();
            return;
        }
        if (requestCode == 2) {
            new Thread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    BaseClassActivityNew.onActivityResult$lambda$36(data, this);
                }
            }).start();
            return;
        }
        if (requestCode == 100) {
            if (this.mSignatureListener != null) {
                stringExtra = data != null ? data.getStringExtra(CommonCons.INSTANCE.getSIGNATUREPATH()) : null;
                CameraHelper.OnSignatureListener onSignatureListener = this.mSignatureListener;
                if (onSignatureListener != null) {
                    onSignatureListener.onSignatureResult(stringExtra);
                    return;
                }
                return;
            }
            return;
        }
        if (requestCode == 49374) {
            if (this.mBarcodeListener != null) {
                IntentResult activityResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                stringExtra = activityResult != null ? activityResult.getContents() : null;
                CameraHelper.OnBarcodeListener onBarcodeListener = this.mBarcodeListener;
                if (onBarcodeListener != null) {
                    onBarcodeListener.onBarcodeResult(stringExtra);
                    return;
                }
                return;
            }
            return;
        }
        OnPermissionResult onPermissionResult = this.mOnPermissionResult;
        if (onPermissionResult != null) {
            onPermissionResult.permissionResult(resultCode, requestCode, data);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v9, types: [T, android.graphics.Bitmap] */
    public static final void onActivityResult$lambda$35(final BaseClassActivityNew this$0) {
        Runnable runnable;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            try {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (this$0.needBitmap) {
                    objectRef.element = CameraHelper.INSTANCE.processingCamera(this$0, this$0.imageUri);
                }
                File file = this$0.photoFile;
                if (file != null) {
                    if ((file != null ? file.getPath() : null) != null) {
                        File file2 = this$0.photoFile;
                        Intrinsics.checkNotNull(file2);
                        if (!waitForCameraFileReady$default(this$0, file2, 0L, 2, null)) {
                            this$0.showError("#3 File gambar belum siap, silakan coba lagi");
                            return;
                        }
                        FileHelper.Companion companion = FileHelper.INSTANCE;
                        Context applicationContext = this$0.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                        companion.compressImage(applicationContext, file2.getPath(), new Function2<Boolean, File, Unit>() { // from class: id.go.bpsfasih.BaseClassActivityNew$onActivityResult$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, File file3) {
                                invoke(bool.booleanValue(), file3);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z, File file3) {
                                if (z) {
                                    CameraHelper.OnCameraOrGalleryListener onCameraOrGalleryListener = this.this$0.mCameraOrGalleryListener;
                                    if (onCameraOrGalleryListener != null) {
                                        Bitmap bitmap = objectRef.element;
                                        String fileNameFormGear = this.this$0.getFileNameFormGear();
                                        Intrinsics.checkNotNull(fileNameFormGear);
                                        onCameraOrGalleryListener.onCameraResultNew(bitmap, file3, fileNameFormGear);
                                        return;
                                    }
                                    return;
                                }
                                this.this$0.showError("#3 Gagal mengambil gambar");
                            }
                        });
                    } else {
                        this$0.showError("#4 Gagal mengambil gambar");
                    }
                } else {
                    this$0.showError("#5 Gagal mengambil gambar");
                }
                runnable = new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda23
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseClassActivityNew.onActivityResult$lambda$35$lambda$34(this.f$0);
                    }
                };
            } catch (IOException e) {
                e.printStackTrace();
                CameraHelper.OnCameraOrGalleryListener onCameraOrGalleryListener = this$0.mCameraOrGalleryListener;
                if (onCameraOrGalleryListener != null) {
                    onCameraOrGalleryListener.onCancel();
                }
                runnable = new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda23
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseClassActivityNew.onActivityResult$lambda$35$lambda$34(this.f$0);
                    }
                };
            }
            this$0.runOnUiThread(runnable);
        } finally {
            this$0.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda23
                @Override // java.lang.Runnable
                public final void run() {
                    BaseClassActivityNew.onActivityResult$lambda$35$lambda$34(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActivityResult$lambda$35$lambda$34(BaseClassActivityNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideProgressBar();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActivityResult$lambda$36(Intent intent, BaseClassActivityNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Uri data = intent != null ? intent.getData() : null;
        if (data == null) {
            this$0.showError("Failed to open picture!");
            return;
        }
        try {
            File fileFrom = FileUtil.INSTANCE.from(this$0, data);
            if (fileFrom != null) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new BaseClassActivityNew$onActivityResult$2$1(this$0, fileFrom, null), 3, null);
            } else {
                this$0.showError("Failed to read picture data!");
            }
        } catch (IOException e) {
            this$0.showError("Failed to read picture data!");
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showError(final String errorMessage) {
        runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivityNew$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                BaseClassActivityNew.showError$lambda$37(this.f$0, errorMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showError$lambda$37(BaseClassActivityNew this$0, String errorMessage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(errorMessage, "$errorMessage");
        Toast.makeText(this$0, errorMessage, 0).show();
    }
}

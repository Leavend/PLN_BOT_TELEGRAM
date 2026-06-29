package id.go.bpsfasih;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.ui.onboarding.OnBoardingActivity;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.bouncycastle.i18n.MessageBundle;

/* compiled from: BaseClassActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0017\u0018\u00002\u00020\u0001:\u0001<B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\"\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u000eH\u0004J\b\u0010 \u001a\u00020\u0017H\u0004J\b\u0010!\u001a\u00020\u0017H\u0004J\b\u0010\"\u001a\u00020\u0017H\u0004Je\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010%2\b\u0010'\u001a\u0004\u0018\u00010\u001a2\b\u0010(\u001a\u0004\u0018\u00010%2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010%2\b\u0010,\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010-\u001a\u00020\u00102\b\b\u0002\u0010.\u001a\u00020\u0010¢\u0006\u0002\u0010/JT\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010%2\b\u00104\u001a\u0004\u0018\u00010%2\b\u00105\u001a\u0004\u0018\u00010%2\b\u0010(\u001a\u0004\u0018\u00010%2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010%2\b\u0010,\u001a\u0004\u0018\u00010*J^\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010%2\b\u00104\u001a\u0004\u0018\u00010%2\b\u00105\u001a\u0004\u0018\u00010%2\b\u0010(\u001a\u0004\u0018\u00010%2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010%2\b\u0010,\u001a\u0004\u0018\u00010*2\b\u00106\u001a\u0004\u0018\u00010*J\u0006\u00107\u001a\u00020\u0017J\u0010\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020%H\u0002J\u0006\u0010:\u001a\u00020\u0017J\u000e\u0010:\u001a\u00020\u00172\u0006\u0010;\u001a\u00020%R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006="}, d2 = {"Lid/go/bpsfasih/BaseClassActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "compressedImage", "Ljava/io/File;", "customProgressBar", "Lid/go/bpsfasih/CustomProgressBar;", "imageUri", "Landroid/net/Uri;", "getImageUri", "()Landroid/net/Uri;", "setImageUri", "(Landroid/net/Uri;)V", "mOnPermissionResult", "Lid/go/bpsfasih/BaseClassActivity$OnPermissionResult;", "needBitmap", "", "photoFile", "getPhotoFile", "()Ljava/io/File;", "setPhotoFile", "(Ljava/io/File;)V", "hideProgressBar", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "setOnPermissionResult", "onPermissionResult", "setStatusBarColors", "setTheme", "setThemeNoActionBar", "showAlertDialog", MessageBundle.TITLE_ENTRY, "", "message", "icon", "rButtonText", "rButtonListener", "Landroid/view/View$OnClickListener;", "lButtonText", "lButtonListener", "showClose", "cancelAble", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Landroid/view/View$OnClickListener;Ljava/lang/String;Landroid/view/View$OnClickListener;ZZ)V", "showAlertDialogKodeVerifikasi", "dialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "judul", "deskripsi_1", "deskripsi_2", "tButtonListener", "showAlertDialogKodeVerifikasiLogout", "showError", "errorMessage", "showProgressBar", Constants.ScionAnalytics.PARAM_LABEL, "OnPermissionResult", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BaseClassActivity extends AppCompatActivity {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private File compressedImage;
    private CustomProgressBar customProgressBar;
    private Uri imageUri;
    private OnPermissionResult mOnPermissionResult;
    private boolean needBitmap;
    private File photoFile;

    /* compiled from: BaseClassActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/BaseClassActivity$OnPermissionResult;", "", "permissionResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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

    protected final void setOnPermissionResult(OnPermissionResult onPermissionResult) {
        Intrinsics.checkNotNullParameter(onPermissionResult, "onPermissionResult");
        this.mOnPermissionResult = onPermissionResult;
    }

    protected final void setTheme() {
        if (Intrinsics.areEqual(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_DEV)) {
            setTheme(R.style.dev);
        } else if (Intrinsics.areEqual(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD)) {
            setTheme(R.style.production);
        }
    }

    protected final void setThemeNoActionBar() {
        if (Intrinsics.areEqual(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_DEV)) {
            setTheme(R.style.dev_NoActionBar);
        } else if (Intrinsics.areEqual(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD)) {
            setTheme(R.style.production_NoActionBar);
        }
    }

    public final void showProgressBar() {
        if (this.customProgressBar == null) {
            this.customProgressBar = new CustomProgressBar(this, null, 2, null);
        }
        if (this.customProgressBar != null) {
            new CustomProgressBar(this, null, 2, null);
        }
        CustomProgressBar customProgressBar = this.customProgressBar;
        if (customProgressBar != null) {
            customProgressBar.showLoading();
        }
    }

    public final void showProgressBar(String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        if (this.customProgressBar == null) {
            this.customProgressBar = new CustomProgressBar(this, null, 2, null);
        }
        if (this.customProgressBar != null) {
            new CustomProgressBar(this, label);
        }
        CustomProgressBar customProgressBar = this.customProgressBar;
        if (customProgressBar != null) {
            customProgressBar.showLoading();
        }
    }

    public final void hideProgressBar() {
        runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                BaseClassActivity.hideProgressBar$lambda$2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void hideProgressBar$lambda$2(BaseClassActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.customProgressBar == null) {
            this$0.customProgressBar = new CustomProgressBar(this$0, null, 2, null);
        }
        CustomProgressBar customProgressBar = this$0.customProgressBar;
        if (customProgressBar == null || customProgressBar == null) {
            return;
        }
        customProgressBar.dismiss();
    }

    public static /* synthetic */ void showAlertDialog$default(BaseClassActivity baseClassActivity, String str, String str2, Integer num, String str3, View.OnClickListener onClickListener, String str4, View.OnClickListener onClickListener2, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showAlertDialog");
        }
        baseClassActivity.showAlertDialog(str, str2, num, str3, onClickListener, str4, onClickListener2, (i & 128) != 0 ? true : z, (i & 256) != 0 ? false : z2);
    }

    public final void showAlertDialog(String title, String message, Integer icon, String rButtonText, final View.OnClickListener rButtonListener, String lButtonText, final View.OnClickListener lButtonListener, boolean showClose, boolean cancelAble) {
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
            ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialog$lambda$3(bottomSheetDialog, rButtonListener, view);
                }
            });
        } else {
            ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialog)).setVisibility(8);
        }
        if (lButtonListener != null) {
            ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setText(lButtonText);
            ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialog$lambda$4(bottomSheetDialog, lButtonListener, view);
                }
            });
        } else {
            ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialog)).setVisibility(8);
        }
        if (showClose) {
            ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialog$lambda$5(bottomSheetDialog, view);
                }
            });
        } else {
            ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).setVisibility(8);
        }
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$3(BottomSheetDialog dialog, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$4(BottomSheetDialog dialog, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
        onClickListener.onClick(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialog$lambda$5(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    protected final void setStatusBarColors() {
        View decorView;
        Window window = getWindow();
        if (window == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        decorView.setBackgroundResource(R.color.blue_completed);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        OnPermissionResult onPermissionResult;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1 || (onPermissionResult = this.mOnPermissionResult) == null) {
            return;
        }
        onPermissionResult.permissionResult(resultCode, requestCode, data);
    }

    private final void showError(final String errorMessage) {
        runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                BaseClassActivity.showError$lambda$7(this.f$0, errorMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showError$lambda$7(BaseClassActivity this$0, String errorMessage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(errorMessage, "$errorMessage");
        Toast.makeText(this$0, errorMessage, 0).show();
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
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivity.showAlertDialogKodeVerifikasiLogout$lambda$8(bottomSheetDialog, strValueOf, this, view);
            }
        });
        ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivity.showAlertDialogKodeVerifikasiLogout$lambda$9(bottomSheetDialog, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivity.showAlertDialogKodeVerifikasiLogout$lambda$10(bottomSheetDialog, view);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiLogout$lambda$8(BottomSheetDialog dialog, String code, final BaseClassActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(code, "$code");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((EditText) dialog.findViewById(R.id.code_et)).getText().toString().equals(code)) {
            FasihApp.INSTANCE.getSession().logout(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.BaseClassActivity$showAlertDialogKodeVerifikasiLogout$1$1
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
    public static final void showAlertDialogKodeVerifikasiLogout$lambda$9(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasiLogout$lambda$10(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final void showAlertDialogKodeVerifikasi(final BottomSheetDialog dialog, String judul, String deskripsi_1, String deskripsi_2, String rButtonText, final View.OnClickListener rButtonListener, String lButtonText, final View.OnClickListener lButtonListener) {
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
        if (rButtonListener != null) {
            ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setText(rButtonText);
            ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialogKodeVerifikasi$lambda$11(dialog, strValueOf, rButtonListener, this, view);
                }
            });
        }
        if (lButtonListener != null) {
            ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setText(lButtonText);
            ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda13
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialogKodeVerifikasi$lambda$12(lButtonListener, view);
                }
            });
        }
        ((ImageView) dialog.findViewById(R.id.tutup_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseClassActivity.showAlertDialogKodeVerifikasi$lambda$13(dialog, view);
            }
        });
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$11(BottomSheetDialog dialog, String code, View.OnClickListener onClickListener, BaseClassActivity this$0, View view) {
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
    public static final void showAlertDialogKodeVerifikasi$lambda$12(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$13(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final void showAlertDialogKodeVerifikasi(final BottomSheetDialog dialog, String judul, String deskripsi_1, String deskripsi_2, String rButtonText, final View.OnClickListener rButtonListener, String lButtonText, final View.OnClickListener lButtonListener, final View.OnClickListener tButtonListener) {
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
        if (rButtonListener != null) {
            ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setText(rButtonText);
            ((Button) dialog.findViewById(R.id.rButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialogKodeVerifikasi$lambda$14(dialog, strValueOf, rButtonListener, this, view);
                }
            });
        }
        if (lButtonListener != null) {
            ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setText(lButtonText);
            ((Button) dialog.findViewById(R.id.lButton_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialogKodeVerifikasi$lambda$15(lButtonListener, view);
                }
            });
        }
        if (tButtonListener != null) {
            ((ImageView) dialog.findViewById(R.id.tutup_bottomDialogKodeVerifikasi)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.BaseClassActivity$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseClassActivity.showAlertDialogKodeVerifikasi$lambda$16(tButtonListener, view);
                }
            });
        }
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$14(BottomSheetDialog dialog, String code, View.OnClickListener onClickListener, BaseClassActivity this$0, View view) {
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
    public static final void showAlertDialogKodeVerifikasi$lambda$15(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogKodeVerifikasi$lambda$16(View.OnClickListener onClickListener, View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}

package id.go.bpsfasih.ui.backupRestore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.databinding.ActivityBackupRestoreBinding;
import id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.helper.ZipHelper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: BackupRestoreActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0010\u0010\u0017\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\"\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0012\u0010\u001f\u001a\u00020\u00142\b\u0010 \u001a\u0004\u0018\u00010!H\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\""}, d2 = {"Lid/go/bpsfasih/ui/backupRestore/BackupRestoreActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "PATH_DATA_LISTING", "", "getPATH_DATA_LISTING", "()Ljava/lang/String;", "binding", "Lid/go/bpsfasih/databinding/ActivityBackupRestoreBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivityBackupRestoreBinding;", "setBinding", "(Lid/go/bpsfasih/databinding/ActivityBackupRestoreBinding;)V", "viewModel", "Lid/go/bpsfasih/ui/backupRestore/BackupRestoreViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/backupRestore/BackupRestoreViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/backupRestore/BackupRestoreViewModel;)V", "backupNew", "", "data", "Landroid/content/Intent;", "backupOld", "executionUnZip", "importAssignmentListingJson", "initObservables", "onActivityResult", "requestCode", "", "resultCode", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BackupRestoreActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public ActivityBackupRestoreBinding binding;
    public BackupRestoreViewModel viewModel;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final String PATH_DATA_LISTING = Directory.INSTANCE.getABSOLUTEPATHENV() + "assignment_listing" + CommonCons.INSTANCE.getEXTENSION_JSON();

    @Override // id.go.bpsfasih.BaseClassActivityNew
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
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

    public final ActivityBackupRestoreBinding getBinding() {
        ActivityBackupRestoreBinding activityBackupRestoreBinding = this.binding;
        if (activityBackupRestoreBinding != null) {
            return activityBackupRestoreBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivityBackupRestoreBinding activityBackupRestoreBinding) {
        Intrinsics.checkNotNullParameter(activityBackupRestoreBinding, "<set-?>");
        this.binding = activityBackupRestoreBinding;
    }

    public final BackupRestoreViewModel getViewModel() {
        BackupRestoreViewModel backupRestoreViewModel = this.viewModel;
        if (backupRestoreViewModel != null) {
            return backupRestoreViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(BackupRestoreViewModel backupRestoreViewModel) {
        Intrinsics.checkNotNullParameter(backupRestoreViewModel, "<set-?>");
        this.viewModel = backupRestoreViewModel;
    }

    public final String getPATH_DATA_LISTING() {
        return this.PATH_DATA_LISTING;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_restore);
        setViewModel((BackupRestoreViewModel) ViewModelProviders.of(this).get(BackupRestoreViewModel.class));
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_backup_restore);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.l….activity_backup_restore)");
        setBinding((ActivityBackupRestoreBinding) contentView);
        getBinding().setViewModel(getViewModel());
        setStatusBarColor();
        BaseClassActivityNew.setAppBar$default(this, 0, "Backup Restore", null, null, null, 24, null);
        initObservables();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) throws Throwable {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4) {
            if (resultCode == -1) {
                ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
                final Handler handler = new Handler(Looper.getMainLooper());
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                executorServiceNewSingleThreadExecutor.execute(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BackupRestoreActivity.onActivityResult$lambda$2(objectRef, data, this, handler);
                    }
                });
                return;
            }
            return;
        }
        if (requestCode == 8) {
            if (resultCode == -1) {
                if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("backup_database")) {
                    backupNew(data);
                    return;
                } else {
                    backupOld(data);
                    return;
                }
            }
            return;
        }
        if (requestCode == 10 && resultCode == -1) {
            ZipHelper.Companion companion = ZipHelper.INSTANCE;
            String absolute_path_error = Directory.INSTANCE.getABSOLUTE_PATH_ERROR();
            Uri data2 = data != null ? data.getData() : null;
            Intrinsics.checkNotNull(data2);
            companion.zipBPS(absolute_path_error, data2, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity.onActivityResult.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    if (z) {
                        Intent intent = new Intent();
                        Intent intent2 = data;
                        intent.setAction("android.intent.action.SEND");
                        intent.setType("application/zip");
                        Uri data3 = intent2 != null ? intent2.getData() : null;
                        Intrinsics.checkNotNull(data3);
                        intent.putExtra("android.intent.extra.STREAM", data3);
                        intent.putExtra("android.intent.extra.SUBJECT", "Error Log ICS Mobile");
                        intent.putExtra("android.intent.extra.TEXT", "Error Log ICS Mobile");
                        BackupRestoreActivity.this.startActivity(intent);
                        return;
                    }
                    Toast.makeText(BackupRestoreActivity.this, "Gagal ZIP : " + message, 1).show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, java.lang.String[]] */
    public static final void onActivityResult$lambda$2(final Ref.ObjectRef respon, Intent intent, final BackupRestoreActivity this$0, Handler handler) {
        Intrinsics.checkNotNullParameter(respon, "$respon");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(handler, "$handler");
        for (final int i = 0; i < 3; i++) {
            if (i == 1) {
                try {
                    ZipHelper.Companion companion = ZipHelper.INSTANCE;
                    Uri data = intent != null ? intent.getData() : null;
                    Intrinsics.checkNotNull(data);
                    respon.element = companion.unZipBPS(data);
                    this$0.importAssignmentListingJson();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
            handler.post(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BackupRestoreActivity.onActivityResult$lambda$2$lambda$1(i, this$0, respon);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onActivityResult$lambda$2$lambda$1(int i, final BackupRestoreActivity this$0, Ref.ObjectRef respon) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(respon, "$respon");
        if (i == 0) {
            this$0.showProgressBar();
        }
        if (i == 2) {
            this$0.hideProgressBar();
            T t = respon.element;
            Intrinsics.checkNotNull(t);
            String str = ((String[]) t)[0];
            T t2 = respon.element;
            Intrinsics.checkNotNull(t2);
            BaseClassActivityNew.showAlertDialog$default(this$0, str, ((String[]) t2)[1], null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BackupRestoreActivity.onActivityResult$lambda$2$lambda$1$lambda$0(this.f$0, view);
                }
            }, null, null, false, false, 384, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActivityResult$lambda$2$lambda$1$lambda$0(BackupRestoreActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideProgressBar();
    }

    /* compiled from: BackupRestoreActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$initObservables$1, reason: invalid class name and case insensitive filesystem */
    static final class C08501 extends Lambda implements Function1<Boolean, Unit> {
        C08501() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            if (z) {
                final BackupRestoreActivity backupRestoreActivity = BackupRestoreActivity.this;
                backupRestoreActivity.showAlertDialog("Peringatan", "Proses ini membutuhkan waktu. Mohon berkenan menunggu hingga proses loading selesai.", null, "Backup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$initObservables$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BackupRestoreActivity.C08501.invoke$lambda$0(backupRestoreActivity, view);
                    }
                }, "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$initObservables$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BackupRestoreActivity.C08501.invoke$lambda$1(view);
                    }
                }, true, true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BackupRestoreActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            String str = new SimpleDateFormat("dd_MM_yy__HH_mm").format((Date) new java.sql.Date(System.currentTimeMillis()));
            String str2 = "FASIH_" + CommonCons.INSTANCE.getAPPLICATION_STORAGE() + "_" + FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME()) + "_" + str + ".zip";
            Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.TITLE", str2);
            this$0.startActivityForResult(intent, 8);
        }
    }

    private final void initObservables() {
        SingleLiveEvent<Boolean> backupDataIsClicked = getViewModel().getBackupDataIsClicked();
        if (backupDataIsClicked != null) {
            backupDataIsClicked.observe(this, new BackupRestoreActivity$sam$androidx_lifecycle_Observer$0(new C08501()));
        }
        SingleLiveEvent<Boolean> restoreDataIsClicked = getViewModel().getRestoreDataIsClicked();
        if (restoreDataIsClicked != null) {
            restoreDataIsClicked.observe(this, new BackupRestoreActivity$sam$androidx_lifecycle_Observer$0(new AnonymousClass2()));
        }
        SingleLiveEvent<Boolean> shareErrorIsClicked = getViewModel().getShareErrorIsClicked();
        if (shareErrorIsClicked != null) {
            shareErrorIsClicked.observe(this, new BackupRestoreActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity.initObservables.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        String str = new SimpleDateFormat("dd_MM_yy__HH_mm").format((Date) new java.sql.Date(System.currentTimeMillis()));
                        String str2 = "FASIH_Error_" + CommonCons.INSTANCE.getAPPLICATION_STORAGE() + "_" + FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME()) + "_" + str + ".zip";
                        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
                        intent.addCategory("android.intent.category.OPENABLE");
                        intent.setType("*/*");
                        intent.putExtra("android.intent.extra.TITLE", str2);
                        BackupRestoreActivity.this.startActivityForResult(intent, 10);
                    }
                }
            }));
        }
    }

    /* compiled from: BackupRestoreActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$initObservables$2, reason: invalid class name */
    static final class AnonymousClass2 extends Lambda implements Function1<Boolean, Unit> {
        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            if (z) {
                File file = new File(Directory.INSTANCE.getABSOLUTEPATH());
                if (!file.exists() || file.listFiles().length <= 0) {
                    BackupRestoreActivity.this.executionUnZip();
                    return;
                }
                final BackupRestoreActivity backupRestoreActivity = BackupRestoreActivity.this;
                BackupRestoreActivity backupRestoreActivity2 = backupRestoreActivity;
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$initObservables$2$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BackupRestoreActivity.AnonymousClass2.invoke$lambda$0(backupRestoreActivity, view);
                    }
                };
                final BackupRestoreActivity backupRestoreActivity3 = BackupRestoreActivity.this;
                BaseClassActivityNew.showAlertDialog$default(backupRestoreActivity2, "Perhatian", "Pada folder BPS anda terdapat file, apakah anda ingin menimpanya?", null, "Iya", onClickListener, "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$initObservables$2$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BackupRestoreActivity.AnonymousClass2.invoke$lambda$1(backupRestoreActivity3, view);
                    }
                }, false, false, 384, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(BackupRestoreActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.executionUnZip();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(BackupRestoreActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.hideProgressBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void executionUnZip() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        startActivityForResult(intent, 4);
    }

    private final void importAssignmentListingJson() {
        try {
            File file = new File(this.PATH_DATA_LISTING);
            if (file.exists()) {
                Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file, null, 1, null), (Class<Object>) AssignmentEntity[].class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…nmentEntity>::class.java)");
                for (AssignmentEntity assignmentEntity : ArraysKt.toMutableList((Object[]) objFromJson)) {
                    String lowerCase = assignmentEntity.getId().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    assignmentEntity.setId(lowerCase);
                    String lowerCase2 = assignmentEntity.getIdPrimary().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    assignmentEntity.setIdPrimary(lowerCase2);
                    DataSurvey.Assignment.INSTANCE.getAssignmentRepository().insertDataWithoutReplace(assignmentEntity);
                }
                Log.d("FOUR", "importAssignmentJson: Sukses import data File assignment_listing");
                return;
            }
            Log.d("FOUR", "importAssignmentJson: File assignment_listing.json belum ada");
        } catch (Exception e) {
            Log.d("FOUR", "importAssignmentJson: " + e.getMessage());
        }
    }

    /* compiled from: BackupRestoreActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1", f = "BackupRestoreActivity.kt", i = {}, l = {245}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Intent $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Intent intent, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$data = intent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BackupRestoreActivity.this.new AnonymousClass1(this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: BackupRestoreActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1", f = "BackupRestoreActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Intent $data;
            int label;
            final /* synthetic */ BackupRestoreActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01921(BackupRestoreActivity backupRestoreActivity, Intent intent, Continuation<? super C01921> continuation) {
                super(2, continuation);
                this.this$0 = backupRestoreActivity;
                this.$data = intent;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01921(this.this$0, this.$data, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                String absolutepath = Directory.INSTANCE.getABSOLUTEPATH();
                String parent = this.this$0.getDatabasePath("Survey_database").getParent();
                if (parent == null) {
                    parent = "";
                }
                String str = parent;
                Intent intent = this.$data;
                Uri data = intent != null ? intent.getData() : null;
                Intrinsics.checkNotNull(data);
                FileHelper.INSTANCE.copyFolder(absolutepath, str, new C01931(absolutepath, str, this.this$0, data, this.$data));
                return Unit.INSTANCE;
            }

            /* compiled from: BackupRestoreActivity.kt */
            @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01931 extends Lambda implements Function2<Boolean, String, Unit> {
                final /* synthetic */ String $absolutePath;
                final /* synthetic */ Intent $data;
                final /* synthetic */ Uri $destinationBackup;
                final /* synthetic */ String $originDatabase;
                final /* synthetic */ BackupRestoreActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01931(String str, String str2, BackupRestoreActivity backupRestoreActivity, Uri uri, Intent intent) {
                    super(2);
                    this.$absolutePath = str;
                    this.$originDatabase = str2;
                    this.this$0 = backupRestoreActivity;
                    this.$destinationBackup = uri;
                    this.$data = intent;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$1$lambda$0(View view) {
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                /* compiled from: BackupRestoreActivity.kt */
                @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1, reason: invalid class name and collision with other inner class name */
                static final class C01941 extends Lambda implements Function2<Boolean, String, Unit> {
                    final /* synthetic */ String $absolutePath;
                    final /* synthetic */ Intent $data;
                    final /* synthetic */ Uri $destinationBackup;
                    final /* synthetic */ BackupRestoreActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C01941(String str, Uri uri, BackupRestoreActivity backupRestoreActivity, Intent intent) {
                        super(2);
                        this.$absolutePath = str;
                        this.$destinationBackup = uri;
                        this.this$0 = backupRestoreActivity;
                        this.$data = intent;
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void invoke$lambda$1$lambda$0(View view) {
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void invoke$lambda$3$lambda$2(View view) {
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) throws Throwable {
                        invoke(bool.booleanValue(), str);
                        return Unit.INSTANCE;
                    }

                    /* compiled from: BackupRestoreActivity.kt */
                    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C01951 extends Lambda implements Function2<Boolean, String, Unit> {
                        final /* synthetic */ Intent $data;
                        final /* synthetic */ BackupRestoreActivity this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C01951(BackupRestoreActivity backupRestoreActivity, Intent intent) {
                            super(2);
                            this.this$0 = backupRestoreActivity;
                            this.$data = intent;
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invoke$lambda$3$lambda$0(View view) {
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invoke$lambda$5$lambda$4(View view) {
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                            invoke(bool.booleanValue(), str);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z, final String message) {
                            Intrinsics.checkNotNullParameter(message, "message");
                            if (z) {
                                this.this$0.hideProgressBar();
                                final BackupRestoreActivity backupRestoreActivity = this.this$0;
                                final Intent intent = this.$data;
                                backupRestoreActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$1$$ExternalSyntheticLambda2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.C01951.invoke$lambda$3(backupRestoreActivity, intent);
                                    }
                                });
                                return;
                            }
                            this.this$0.hideProgressBar();
                            final BackupRestoreActivity backupRestoreActivity2 = this.this$0;
                            backupRestoreActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$1$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.C01951.invoke$lambda$5(backupRestoreActivity2, message);
                                }
                            });
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invoke$lambda$3(final BackupRestoreActivity this$0, final Intent intent) {
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            BackupRestoreActivity backupRestoreActivity = this$0;
                            BaseClassActivityNew.showAlertDialog$default(backupRestoreActivity, "Sukses", "Sukses melakukan backup, apakah anda ingin mengirimkan backup anda?", null, "Kirim", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$1$$ExternalSyntheticLambda1
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.C01951.invoke$lambda$3$lambda$2(this$0, intent, view);
                                }
                            }, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$1$$ExternalSyntheticLambda0
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.C01951.invoke$lambda$3$lambda$0(view);
                                }
                            }, false, true, 128, null);
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invoke$lambda$3$lambda$2(BackupRestoreActivity this$0, Intent intent, View view) {
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            Intent intent2 = new Intent();
                            intent2.setAction("android.intent.action.SEND");
                            intent2.setType("application/zip");
                            Uri data = intent != null ? intent.getData() : null;
                            Intrinsics.checkNotNull(data);
                            intent2.putExtra("android.intent.extra.STREAM", data);
                            intent2.putExtra("android.intent.extra.SUBJECT", "Backup Fasih Mobile");
                            intent2.putExtra("android.intent.extra.TEXT", "Backup Fasih Mobile");
                            this$0.startActivity(intent2);
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invoke$lambda$5(BackupRestoreActivity this$0, String message) {
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            Intrinsics.checkNotNullParameter(message, "$message");
                            int i = R.color.error30;
                            int i2 = R.color.error30;
                            this$0.showAlertDialogColor("Gagal", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$1$$ExternalSyntheticLambda4
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.C01951.invoke$lambda$5$lambda$4(view);
                                }
                            }, null, null, null, Integer.valueOf(R.color.error30), true);
                        }
                    }

                    public final void invoke(boolean z, final String message) throws Throwable {
                        Intrinsics.checkNotNullParameter(message, "message");
                        if (!z) {
                            this.this$0.hideProgressBar();
                            Log.d("FOUR", "onActivityResult: Gagal protected zip folder");
                            final BackupRestoreActivity backupRestoreActivity = this.this$0;
                            backupRestoreActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.invoke$lambda$3(backupRestoreActivity, message);
                                }
                            });
                            return;
                        }
                        if (z) {
                            ZipHelper.INSTANCE.zipBPS(this.$absolutePath, this.$destinationBackup, new C01951(this.this$0, this.$data));
                            return;
                        }
                        this.this$0.hideProgressBar();
                        Log.d("FOUR", "onActivityResult: Gagal backup");
                        final BackupRestoreActivity backupRestoreActivity2 = this.this$0;
                        backupRestoreActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.invoke$lambda$1(backupRestoreActivity2, message);
                            }
                        });
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void invoke$lambda$1(BackupRestoreActivity this$0, String message) {
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        Intrinsics.checkNotNullParameter(message, "$message");
                        int i = R.color.error30;
                        int i2 = R.color.error30;
                        this$0.showAlertDialogColor("Gagal", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$$ExternalSyntheticLambda2
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.invoke$lambda$1$lambda$0(view);
                            }
                        }, null, null, null, Integer.valueOf(R.color.error30), true);
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void invoke$lambda$3(BackupRestoreActivity this$0, String message) {
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        Intrinsics.checkNotNullParameter(message, "$message");
                        int i = R.color.error30;
                        int i2 = R.color.error30;
                        this$0.showAlertDialogColor("Gagal", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$1$$ExternalSyntheticLambda3
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                BackupRestoreActivity.AnonymousClass1.C01921.C01931.C01941.invoke$lambda$3$lambda$2(view);
                            }
                        }, null, null, null, Integer.valueOf(R.color.error30), true);
                    }
                }

                public final void invoke(boolean z, final String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    if (z) {
                        String inputPath = new File(this.$absolutePath, new File(this.$originDatabase).getName()).getPath();
                        ZipHelper.Companion companion = ZipHelper.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(inputPath, "inputPath");
                        String str = this.$absolutePath;
                        char[] charArray = "FMGood".toCharArray();
                        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
                        companion.protectedZip(inputPath, str, charArray, new C01941(this.$absolutePath, this.$destinationBackup, this.this$0, this.$data));
                        return;
                    }
                    this.this$0.hideProgressBar();
                    Log.d("FOUR", "onActivityResult: Gagal copy folder");
                    final BackupRestoreActivity backupRestoreActivity = this.this$0;
                    backupRestoreActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            BackupRestoreActivity.AnonymousClass1.C01921.C01931.invoke$lambda$1(backupRestoreActivity, message);
                        }
                    });
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$1(BackupRestoreActivity this$0, String message) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(message, "$message");
                    int i = R.color.error30;
                    int i2 = R.color.error30;
                    this$0.showAlertDialogColor("Gagal", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupNew$1$1$1$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            BackupRestoreActivity.AnonymousClass1.C01921.C01931.invoke$lambda$1$lambda$0(view);
                        }
                    }, null, null, null, Integer.valueOf(R.color.error30), true);
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C01921(BackupRestoreActivity.this, this.$data, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void backupNew(Intent data) {
        showProgressBar();
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new AnonymousClass1(data, null), 3, null);
    }

    /* compiled from: BackupRestoreActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1", f = "BackupRestoreActivity.kt", i = {}, l = {398}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1, reason: invalid class name and case insensitive filesystem */
    static final class C08491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Intent $data;
        int label;
        final /* synthetic */ BackupRestoreActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08491(Intent intent, BackupRestoreActivity backupRestoreActivity, Continuation<? super C08491> continuation) {
            super(2, continuation);
            this.$data = intent;
            this.this$0 = backupRestoreActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08491(this.$data, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: BackupRestoreActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1", f = "BackupRestoreActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Intent $data;
            int label;
            final /* synthetic */ BackupRestoreActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01961(Intent intent, BackupRestoreActivity backupRestoreActivity, Continuation<? super C01961> continuation) {
                super(2, continuation);
                this.$data = intent;
                this.this$0 = backupRestoreActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01961(this.$data, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: BackupRestoreActivity.kt */
            @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            /* renamed from: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01971 extends Lambda implements Function2<Boolean, String, Unit> {
                final /* synthetic */ Intent $data;
                final /* synthetic */ BackupRestoreActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01971(BackupRestoreActivity backupRestoreActivity, Intent intent) {
                    super(2);
                    this.this$0 = backupRestoreActivity;
                    this.$data = intent;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$3$lambda$0(View view) {
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$5$lambda$4(View view) {
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, final String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    this.this$0.hideProgressBar();
                    if (z) {
                        final BackupRestoreActivity backupRestoreActivity = this.this$0;
                        final Intent intent = this.$data;
                        backupRestoreActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1$1$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                BackupRestoreActivity.C08491.C01961.C01971.invoke$lambda$3(backupRestoreActivity, intent);
                            }
                        });
                    } else {
                        Log.d("FOUR", "onActivityResult: Gagal backup");
                        final BackupRestoreActivity backupRestoreActivity2 = this.this$0;
                        backupRestoreActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1$1$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                BackupRestoreActivity.C08491.C01961.C01971.invoke$lambda$5(backupRestoreActivity2, message);
                            }
                        });
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$3(final BackupRestoreActivity this$0, final Intent intent) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    BackupRestoreActivity backupRestoreActivity = this$0;
                    BaseClassActivityNew.showAlertDialog$default(backupRestoreActivity, "Sukses", "Sukses melakukan backup, apakah anda ingin mengirimkan backup anda?", null, "Kirim", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1$1$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            BackupRestoreActivity.C08491.C01961.C01971.invoke$lambda$3$lambda$2(this$0, intent, view);
                        }
                    }, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            BackupRestoreActivity.C08491.C01961.C01971.invoke$lambda$3$lambda$0(view);
                        }
                    }, false, true, 128, null);
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$3$lambda$2(BackupRestoreActivity this$0, Intent intent, View view) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intent intent2 = new Intent();
                    intent2.setAction("android.intent.action.SEND");
                    intent2.setType("application/zip");
                    Uri data = intent != null ? intent.getData() : null;
                    Intrinsics.checkNotNull(data);
                    intent2.putExtra("android.intent.extra.STREAM", data);
                    intent2.putExtra("android.intent.extra.SUBJECT", "Backup Fasih Mobile");
                    intent2.putExtra("android.intent.extra.TEXT", "Backup Fasih Mobile");
                    this$0.startActivity(intent2);
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$5(BackupRestoreActivity this$0, String message) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(message, "$message");
                    int i = R.color.success30;
                    int i2 = R.color.success30;
                    this$0.showAlertDialogColor("Sukses", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Selesai", Integer.valueOf(R.drawable.layout_button_success), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity$backupOld$1$1$1$$ExternalSyntheticLambda4
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            BackupRestoreActivity.C08491.C01961.C01971.invoke$lambda$5$lambda$4(view);
                        }
                    }, null, null, null, Integer.valueOf(R.color.success30), true);
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ZipHelper.Companion companion = ZipHelper.INSTANCE;
                String absolutepath = Directory.INSTANCE.getABSOLUTEPATH();
                Intent intent = this.$data;
                Uri data = intent != null ? intent.getData() : null;
                Intrinsics.checkNotNull(data);
                companion.zipBPS(absolutepath, data, new C01971(this.this$0, this.$data));
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getIO(), new C01961(this.$data, this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void backupOld(Intent data) {
        showProgressBar();
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new C08491(data, this, null), 3, null);
    }
}

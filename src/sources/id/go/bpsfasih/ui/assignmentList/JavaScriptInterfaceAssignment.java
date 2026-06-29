package id.go.bpsfasih.ui.assignmentList;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.AssignmentSubmitVersionEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.Region;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.models.AssignmentReturnData;
import id.go.bpsfasih.data.local.models.AssignmentSubmitS3Response;
import id.go.bpsfasih.data.local.models.BaseResponseDataUpload;
import id.go.bpsfasih.data.local.models.PresignedUrlsItem;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.local.pojo.Sync;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository;
import id.go.bpsfasih.data.local.repository.PeriodeRepository;
import id.go.bpsfasih.data.remote.dto.AssignmentAnswerResponse;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment;
import id.go.bpsfasih.ui.formGear.FormGearActivity;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import id.go.bpsfasih.ui.uploadactivity.UploadActivity;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.AssignmentEncryptionHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.helper.ZipHelper;
import id.go.bpsfasih.utils.sync.reqDownload.RDAssignmentNotif;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JavaScriptInterfaceAssignment.kt */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0007\u0018\u0000 k2\u00020\u0001:\u0001kBg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0010¢\u0006\u0002\u0010\u0014J'\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010+\u001a\u00020)¢\u0006\u0002\u0010,J'\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0(2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0(2\u0006\u0010+\u001a\u00020\t¢\u0006\u0002\u0010.J\u001c\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\t2\b\u00102\u001a\u0004\u0018\u00010\tH\u0007J\u0010\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020)H\u0002J\u0012\u00105\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\tH\u0007J\u001c\u00106\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\t072\u0006\u00101\u001a\u00020\tH\u0002JH\u00108\u001a\u0002002\u0006\u00101\u001a\u00020\t26\u00109\u001a2\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(=\u0012\u0013\u0012\u00110\t¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002000:H\u0002J\u0012\u0010?\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\tH\u0007J\u0010\u0010@\u001a\u0002002\u0006\u00101\u001a\u00020\tH\u0007J\u0010\u0010A\u001a\u0002002\u0006\u0010B\u001a\u00020CH\u0002J\"\u0010D\u001a\u0002002\u001a\u0010E\u001a\u0016\u0012\u0004\u0012\u00020\t\u0018\u00010Fj\n\u0012\u0004\u0012\u00020\t\u0018\u0001`GJ\u0010\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020JH\u0002J\n\u0010K\u001a\u0004\u0018\u00010\tH\u0007J\u0010\u0010L\u001a\u0002002\u0006\u0010M\u001a\u00020NH\u0002J\u0010\u0010O\u001a\u00020\u00102\u0006\u0010B\u001a\u00020CH\u0002J\u0018\u0010P\u001a\u0002002\u0006\u00101\u001a\u00020\t2\u0006\u0010Q\u001a\u00020\tH\u0002J\u0018\u0010R\u001a\u0002002\u0006\u00101\u001a\u00020\t2\u0006\u0010S\u001a\u00020\tH\u0002J\u001c\u0010T\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\t2\b\u00102\u001a\u0004\u0018\u00010\tH\u0007J\b\u0010U\u001a\u000200H\u0007J\u0010\u0010V\u001a\u0002002\u0006\u0010B\u001a\u00020CH\u0002J\"\u0010W\u001a\u0004\u0018\u00010\t2\u0006\u0010X\u001a\u00020)2\u0006\u0010Y\u001a\u00020)2\u0006\u00101\u001a\u00020\tH\u0002J\u0012\u0010Z\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\tH\u0007J\u0010\u0010[\u001a\u0002002\u0006\u0010\\\u001a\u00020\tH\u0007J\b\u0010]\u001a\u000200H\u0007J\u000e\u0010^\u001a\u0002002\u0006\u0010_\u001a\u00020\tJ\u0018\u0010`\u001a\u0002002\u0006\u0010a\u001a\u00020\t2\u0006\u0010B\u001a\u00020CH\u0002J\u0010\u0010b\u001a\u0002002\u0006\u0010B\u001a\u00020CH\u0002J!\u0010c\u001a\u0002002\u0006\u0010B\u001a\u00020C2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020)0(¢\u0006\u0002\u0010eJ\u0018\u0010f\u001a\u0002002\u0006\u0010B\u001a\u00020C2\u0006\u0010g\u001a\u00020!H\u0002JJ\u0010h\u001a\u0002002\u0006\u0010i\u001a\u00020\t28\u00109\u001a4\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(j\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(i\u0012\u0004\u0012\u0002000:H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006l"}, d2 = {"Lid/go/bpsfasih/ui/assignmentList/JavaScriptInterfaceAssignment;", "", "_webView", "Landroid/webkit/WebView;", "activity", "Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity;", "model", "Lid/go/bpsfasih/ui/assignmentList/AssignmentUpdateListingViewModel;", "mRegionId", "", "mPeriodeId", "mSurveyId", "mRegionFullCode", "mRegionName", "mTemplateId", "mIsPencacah", "", "mUserLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "isListingDone", "(Landroid/webkit/WebView;Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity;Lid/go/bpsfasih/ui/assignmentList/AssignmentUpdateListingViewModel;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/google/android/gms/maps/model/LatLng;Z)V", "getActivity", "()Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity;", "assignmentRepo", "Lid/go/bpsfasih/data/local/repository/AssignmentRepository;", "getAssignmentRepo", "()Lid/go/bpsfasih/data/local/repository/AssignmentRepository;", "setAssignmentRepo", "(Lid/go/bpsfasih/data/local/repository/AssignmentRepository;)V", "assignmentSubmitVersionRepo", "Lid/go/bpsfasih/data/local/repository/AssignmentSubmitVersionRepository;", "hasWrappedDataKey", "indexPathFileBackup", "", "getIndexPathFileBackup", "()I", "setIndexPathFileBackup", "(I)V", "mWebView", "appendFile", "", "Ljava/io/File;", "arr", "element", "([Ljava/io/File;Ljava/io/File;)[Ljava/io/File;", "appendString", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "bukaDialogAksi", "", "assignmentId", "surveyId", "calculateMD5", StorageChooser.FILE_PICKER, "changeModeAssignment", "create7zFile", "Lkotlin/Pair;", "createUploadModel", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", FirebaseAnalytics.Param.SUCCESS, "message", "deleteAssignment", "downloadAssignmentData", "downloadSingleAssignment", "assignment", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "execute", "filter", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "formatFileSize", "size", "", "getStateDataTable", "handleChangeModeDone", "response", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "isDataExist", "onlineChangeModeWithData", "param", "onlineChangeModeWithoutData", "bodyParam", "openAssignment", "openDaftarUpload", "openFormAssignment", "requestFileAssignment", "folderIn", "folderTemp", "restoreAssignment", "saveStateDataTable", "data", "selesai", "setSorting", "str", "showAlertDialogChangeMode", "newMode", "showDownloadBeforeOpenDialog", "showRestore", "listBackupAssignmentOrigin", "(Lid/go/bpsfasih/data/local/entities/AssignmentEntity;[Ljava/io/File;)V", "showSubmitVersionMismatchDialog", "localSubmitVersionCode", "validasiEmail", "email", NotificationCompat.CATEGORY_STATUS, "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class JavaScriptInterfaceAssignment {
    private static final int ASSIGNMENT_STATUS_REJECTED_FOR_OPEN_GUARD = 3;
    public static final String TAG_HANDLER = "Android";
    private final AssignmentListActivity activity;
    private AssignmentRepository assignmentRepo;
    private final AssignmentSubmitVersionRepository assignmentSubmitVersionRepo;
    private final boolean hasWrappedDataKey;
    private int indexPathFileBackup;
    private final boolean isListingDone;
    private final boolean mIsPencacah;
    private String mPeriodeId;
    private String mRegionFullCode;
    private String mRegionId;
    private String mRegionName;
    private String mSurveyId;
    private String mTemplateId;
    private final LatLng mUserLatLng;
    private WebView mWebView;
    private final AssignmentUpdateListingViewModel model;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public JavaScriptInterfaceAssignment(WebView _webView, AssignmentListActivity activity, AssignmentUpdateListingViewModel model, String mRegionId, String mPeriodeId, String mSurveyId, String mRegionFullCode, String mRegionName, String mTemplateId, boolean z, LatLng latLng, boolean z2) {
        Intrinsics.checkNotNullParameter(_webView, "_webView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(mRegionId, "mRegionId");
        Intrinsics.checkNotNullParameter(mPeriodeId, "mPeriodeId");
        Intrinsics.checkNotNullParameter(mSurveyId, "mSurveyId");
        Intrinsics.checkNotNullParameter(mRegionFullCode, "mRegionFullCode");
        Intrinsics.checkNotNullParameter(mRegionName, "mRegionName");
        Intrinsics.checkNotNullParameter(mTemplateId, "mTemplateId");
        this.mWebView = _webView;
        this.assignmentRepo = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
        this.assignmentSubmitVersionRepo = DataSurvey.AssignmentSubmitVersion.INSTANCE.getAssignmentSubmitVersionRepository();
        this.mPeriodeId = mPeriodeId;
        this.mRegionId = mRegionId;
        this.mSurveyId = mSurveyId;
        this.mRegionFullCode = mRegionFullCode;
        this.mRegionName = mRegionName;
        this.mTemplateId = mTemplateId;
        this.activity = activity;
        this.mIsPencacah = z;
        this.mUserLatLng = latLng;
        this.model = model;
        this.isListingDone = z2;
        String wrappedDataKey = AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(mRegionId, mPeriodeId);
        this.hasWrappedDataKey = !(wrappedDataKey == null || StringsKt.isBlank(wrappedDataKey));
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/ui/assignmentList/JavaScriptInterfaceAssignment$Companion;", "", "()V", "ASSIGNMENT_STATUS_REJECTED_FOR_OPEN_GUARD", "", "TAG_HANDLER", "", "shouldRequireDownloadBeforeOpen", "", "assignmentStatusId", "dataDownloadedAt", "shouldRequireDownloadBeforeOpen$app_release", "(Ljava/lang/Integer;Ljava/lang/String;)Z", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean shouldRequireDownloadBeforeOpen$app_release(Integer assignmentStatusId, String dataDownloadedAt) {
            if (assignmentStatusId == null || assignmentStatusId.intValue() != 3) {
                return false;
            }
            String str = dataDownloadedAt;
            return str == null || str.length() == 0;
        }
    }

    public final AssignmentRepository getAssignmentRepo() {
        return this.assignmentRepo;
    }

    public final void setAssignmentRepo(AssignmentRepository assignmentRepository) {
        Intrinsics.checkNotNullParameter(assignmentRepository, "<set-?>");
        this.assignmentRepo = assignmentRepository;
    }

    public final AssignmentListActivity getActivity() {
        return this.activity;
    }

    public final int getIndexPathFileBackup() {
        return this.indexPathFileBackup;
    }

    public final void setIndexPathFileBackup(int i) {
        this.indexPathFileBackup = i;
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$execute$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$execute$1, reason: invalid class name and case insensitive filesystem */
    static final class C08401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ArrayList<String> $filter;
        int label;
        final /* synthetic */ JavaScriptInterfaceAssignment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08401(ArrayList<String> arrayList, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, Continuation<? super C08401> continuation) {
            super(2, continuation);
            this.$filter = arrayList;
            this.this$0 = javaScriptInterfaceAssignment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08401(this.$filter, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String string;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                ArrayList<String> arrayList = this.$filter;
                if (arrayList == null || (string = arrayList.toString()) == null) {
                    string = "null";
                }
                String str = "javascript:setParam(" + JSONObject.quote(this.this$0.mSurveyId) + "," + JSONObject.quote(this.this$0.mPeriodeId) + "," + JSONObject.quote(this.this$0.mRegionId) + "," + JSONObject.quote(this.this$0.mRegionFullCode) + "," + JSONObject.quote(this.this$0.mRegionName) + "," + JSONObject.quote(this.this$0.mTemplateId) + "," + this.this$0.isListingDone + "," + this.this$0.mIsPencacah + "," + this.this$0.hasWrappedDataKey + "," + JSONObject.quote(string) + ")";
                WebView webView = this.this$0.mWebView;
                if (webView != null) {
                    webView.evaluateJavascript(str, null);
                }
            } catch (Exception unused) {
            }
            return Unit.INSTANCE;
        }
    }

    public final void execute(ArrayList<String> filter) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08401(filter, this, null), 2, null);
    }

    @JavascriptInterface
    public final void bukaDialogAksi(final String assignmentId, final String surveyId) {
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                JavaScriptInterfaceAssignment.bukaDialogAksi$lambda$0(this.f$0, assignmentId, surveyId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bukaDialogAksi$lambda$0(JavaScriptInterfaceAssignment this$0, String str, String str2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AssignmentListActivity assignmentListActivity = this$0.activity;
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNull(str2);
        assignmentListActivity.showDialogAksi(str, str2);
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openAssignment$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08441(String str, Continuation<? super C08441> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JavaScriptInterfaceAssignment.this.new C08441(this.$assignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (ActivityCompat.checkSelfPermission(JavaScriptInterfaceAssignment.this.getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(JavaScriptInterfaceAssignment.this.getActivity(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01901(JavaScriptInterfaceAssignment.this, this.$assignmentId, null), 2, null);
            } else {
                Toast.makeText(JavaScriptInterfaceAssignment.this.getActivity(), "Location Permission tidak aktif, aktifkan Location Permission", 0).show();
            }
            return Unit.INSTANCE;
        }

        /* compiled from: JavaScriptInterfaceAssignment.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openAssignment$1$1", f = "JavaScriptInterfaceAssignment.kt", i = {0}, l = {174}, m = "invokeSuspend", n = {"assignment"}, s = {"L$0"})
        /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openAssignment$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $assignmentId;
            Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ JavaScriptInterfaceAssignment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01901(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, String str, Continuation<? super C01901> continuation) {
                super(2, continuation);
                this.this$0 = javaScriptInterfaceAssignment;
                this.$assignmentId = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$2$lambda$1(View view) {
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01901(this.this$0, this.$assignmentId, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                final AssignmentEntity assignmentEntity;
                String str;
                UserRole userRole;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                Boolean boolIsPencacah = null;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    assignmentEntity = (AssignmentEntity) BuildersKt__BuildersKt.runBlocking$default(null, new JavaScriptInterfaceAssignment$openAssignment$1$1$assignment$1(this.this$0, this.$assignmentId, null), 1, null);
                    if (assignmentEntity == null) {
                        return Unit.INSTANCE;
                    }
                    PeriodeRepository periodeRepository = DataSurvey.Periode.INSTANCE.getPeriodeRepository();
                    String str2 = this.this$0.mPeriodeId;
                    String userId = FasihApp.INSTANCE.getSession().getUserId();
                    this.L$0 = assignmentEntity;
                    this.L$1 = "IS_PENCACAH";
                    this.label = 1;
                    obj = periodeRepository.getPeriodeByPrimaryId(str2 + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    str = "IS_PENCACAH";
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    str = (String) this.L$1;
                    assignmentEntity = (AssignmentEntity) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                List<UserRole> role = ((PeriodeEntityNew) obj).getRole();
                if (role != null && (userRole = role.get(0)) != null) {
                    boolIsPencacah = userRole.isPencacah();
                }
                Intrinsics.checkNotNull(boolIsPencacah);
                Log.d(str, String.valueOf(boolIsPencacah.booleanValue()));
                final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
                BaseClassActivityNew.showAlertDialog$default(javaScriptInterfaceAssignment.getActivity(), "Konfirmasi", "Anda akan membuka dokumen ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openAssignment$1$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        JavaScriptInterfaceAssignment.C08441.C01901.invokeSuspend$lambda$2$lambda$0(javaScriptInterfaceAssignment, assignmentEntity, view);
                    }
                }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openAssignment$1$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        JavaScriptInterfaceAssignment.C08441.C01901.invokeSuspend$lambda$2$lambda$1(view);
                    }
                }, false, false, 384, null);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$2$lambda$0(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, AssignmentEntity assignmentEntity, View view) {
                if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("success_download_data")) {
                    if (!javaScriptInterfaceAssignment.isDataExist(assignmentEntity)) {
                        AssignmentSubmitVersionEntity assignmentSubmitVersionEntity = (AssignmentSubmitVersionEntity) BuildersKt__BuildersKt.runBlocking$default(null, new JavaScriptInterfaceAssignment$openAssignment$1$1$1$1$localSubmitVersionCode$1(javaScriptInterfaceAssignment, assignmentEntity, null), 1, null);
                        int submitVersionCode = assignmentSubmitVersionEntity != null ? assignmentSubmitVersionEntity.getSubmitVersionCode() : 0;
                        if (submitVersionCode != assignmentEntity.getSubmitVersionCode()) {
                            javaScriptInterfaceAssignment.showSubmitVersionMismatchDialog(assignmentEntity, submitVersionCode);
                            return;
                        }
                    } else {
                        javaScriptInterfaceAssignment.showDownloadBeforeOpenDialog(assignmentEntity);
                        return;
                    }
                }
                javaScriptInterfaceAssignment.openFormAssignment(assignmentEntity);
            }
        }
    }

    @JavascriptInterface
    public final void openAssignment(String assignmentId, String surveyId) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08441(assignmentId, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDataExist(AssignmentEntity assignment) {
        return INSTANCE.shouldRequireDownloadBeforeOpen$app_release(assignment.getAssignmentStatusId(), assignment.getDataDownloadedAt());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDownloadBeforeOpenDialog(final AssignmentEntity assignment) {
        BaseClassActivityNew.showAlertDialog$default(this.activity, "Perhatian", "Data isian assignment ini belum ter-download. Download terlebih dahulu, untuk melihat isian.", null, "Download Sekarang", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.showDownloadBeforeOpenDialog$lambda$1(this.f$0, assignment, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDownloadBeforeOpenDialog$lambda$1(JavaScriptInterfaceAssignment this$0, AssignmentEntity assignment, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignment, "$assignment");
        if (!Network.INSTANCE.isOnline(this$0.activity)) {
            Toast.makeText(this$0.activity, "Pastikan perangkat Anda terhubung dengan internet.", 1).show();
        } else {
            this$0.downloadSingleAssignment(assignment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSubmitVersionMismatchDialog(final AssignmentEntity assignment, int localSubmitVersionCode) {
        BaseClassActivityNew.showAlertDialog$default(this.activity, "Perhatian", "Versi data assignment ini di lokal berbeda dengan versi server. Versi lokal: " + localSubmitVersionCode + ", versi server: " + assignment.getSubmitVersionCode() + ". Download ulang data untuk mendapatkan isian terbaru.", null, "Download Sekarang", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.showSubmitVersionMismatchDialog$lambda$2(this.f$0, assignment, view);
            }
        }, "Buka Saja", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.showSubmitVersionMismatchDialog$lambda$3(this.f$0, assignment, view);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubmitVersionMismatchDialog$lambda$2(JavaScriptInterfaceAssignment this$0, AssignmentEntity assignment, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignment, "$assignment");
        this$0.downloadSingleAssignment(assignment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSubmitVersionMismatchDialog$lambda$3(JavaScriptInterfaceAssignment this$0, AssignmentEntity assignment, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignment, "$assignment");
        this$0.openFormAssignment(assignment);
    }

    private final void downloadSingleAssignment(final AssignmentEntity assignment) {
        this.activity.showProgressBar();
        new RDAssignmentNotif(this.mSurveyId, this.mPeriodeId, CollectionsKt.listOf(assignment.getId()), new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.downloadSingleAssignment.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            /* compiled from: JavaScriptInterfaceAssignment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadSingleAssignment$1$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadSingleAssignment$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $message;
                int label;
                final /* synthetic */ JavaScriptInterfaceAssignment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01801(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, String str, Continuation<? super C01801> continuation) {
                    super(2, continuation);
                    this.this$0 = javaScriptInterfaceAssignment;
                    this.$message = str;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01801(this.this$0, this.$message, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.getActivity().hideProgressBar();
                    AssignmentListActivity activity = this.this$0.getActivity();
                    String str = this.$message;
                    if (str == null) {
                        str = "Gagal mengunduh assignment";
                    }
                    Toast.makeText(activity, str, 1).show();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x004d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke(java.lang.String r11, boolean r12) {
                /*
                    r10 = this;
                    r0 = 0
                    if (r12 == 0) goto L21
                    kotlinx.coroutines.GlobalScope r12 = kotlinx.coroutines.GlobalScope.INSTANCE
                    r1 = r12
                    kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                    kotlinx.coroutines.MainCoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getMain()
                    r2 = r12
                    kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
                    r3 = 0
                    id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadSingleAssignment$1$1 r12 = new id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadSingleAssignment$1$1
                    id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment r4 = r2
                    r12.<init>(r4, r11, r0)
                    r4 = r12
                    kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                    r5 = 2
                    r6 = 0
                    kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
                    goto L94
                L21:
                    id.go.bpsfasih.data.local.entities.AssignmentEntity r11 = r1
                    java.lang.String r11 = r11.getBasePath()
                    r12 = 0
                    if (r11 == 0) goto L4e
                    id.go.bpsfasih.data.local.entities.AssignmentEntity r11 = r1
                    java.lang.String r11 = r11.getBasePath()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
                    java.lang.String r1 = ".7z"
                    r2 = 2
                    boolean r11 = kotlin.text.StringsKt.endsWith$default(r11, r1, r12, r2, r0)
                    if (r11 != 0) goto L4d
                    id.go.bpsfasih.data.local.entities.AssignmentEntity r11 = r1
                    java.lang.String r11 = r11.getBasePath()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
                    java.lang.String r1 = ".zip"
                    boolean r11 = kotlin.text.StringsKt.endsWith$default(r11, r1, r12, r2, r0)
                    if (r11 == 0) goto L4e
                L4d:
                    r12 = 1
                L4e:
                    id.go.bpsfasih.domain.models.SyncAnswerPartial r11 = new id.go.bpsfasih.domain.models.SyncAnswerPartial
                    id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment r1 = r2
                    java.lang.String r2 = id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.access$getMSurveyId$p(r1)
                    id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment r1 = r2
                    java.lang.String r3 = id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.access$getMPeriodeId$p(r1)
                    id.go.bpsfasih.data.local.entities.AssignmentEntity r1 = r1
                    java.lang.String r4 = r1.getId()
                    if (r12 == 0) goto L66
                    r5 = r0
                    goto L71
                L66:
                    id.go.bpsfasih.data.local.entities.AssignmentEntity r1 = r1
                    java.lang.String r1 = r1.getId()
                    java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
                    r5 = r1
                L71:
                    if (r12 == 0) goto L7d
                    id.go.bpsfasih.data.local.entities.AssignmentEntity r12 = r1
                    java.lang.String r12 = r12.getId()
                    java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r12)
                L7d:
                    r6 = r0
                    r7 = 0
                    r8 = 32
                    r9 = 0
                    r1 = r11
                    r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
                    id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft r12 = new id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft
                    id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadSingleAssignment$1$2 r0 = new id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadSingleAssignment$1$2
                    id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment r1 = r2
                    r0.<init>()
                    kotlin.jvm.functions.Function3 r0 = (kotlin.jvm.functions.Function3) r0
                    r12.<init>(r11, r0)
                L94:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.C08391.invoke(java.lang.String, boolean):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openFormAssignment(final AssignmentEntity assignment) {
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.model;
        if (assignmentUpdateListingViewModel != null) {
            assignmentUpdateListingViewModel.ensureRegionSupportDataForAssignment(assignment, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.openFormAssignment.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* compiled from: JavaScriptInterfaceAssignment.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openFormAssignment$1$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$openFormAssignment$1$1, reason: invalid class name and collision with other inner class name */
                static final class C01911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ AssignmentEntity $assignment;
                    int label;
                    final /* synthetic */ JavaScriptInterfaceAssignment this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C01911(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, AssignmentEntity assignmentEntity, Continuation<? super C01911> continuation) {
                        super(2, continuation);
                        this.this$0 = javaScriptInterfaceAssignment;
                        this.$assignment = assignmentEntity;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C01911(this.this$0, this.$assignment, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C01911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        FormGearActivity.INSTANCE.startActivity(this.this$0.getActivity(), this.this$0.mTemplateId, Boxing.boxBoolean(this.this$0.mIsPencacah), this.this$0.mPeriodeId, this.$assignment, false, false, true, this.this$0.mUserLatLng, true);
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C01911(JavaScriptInterfaceAssignment.this, assignment, null), 2, null);
                }
            });
        }
    }

    @JavascriptInterface
    public final void selesai() {
        this.activity.hideProgressBar();
    }

    @JavascriptInterface
    public final void deleteAssignment(final String assignmentId) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.activity);
        this.activity.showAlertDialogKodeVerifikasi(bottomSheetDialog, "Hapus Assignment", "Apakah yakin menghapus assignment tersebut?", "Untuk melakukan aksi berikut, tolong ketik kode verifikasi dengan benar", "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.deleteAssignment$lambda$4(bottomSheetDialog, view);
            }
        }, "Hapus", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.deleteAssignment$lambda$5(bottomSheetDialog, this, assignmentId, view);
            }
        }, new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.deleteAssignment$lambda$6(bottomSheetDialog, view);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAssignment$lambda$4(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAssignment$lambda$5(BottomSheetDialog dialog, JavaScriptInterfaceAssignment this$0, String str, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new JavaScriptInterfaceAssignment$deleteAssignment$2$1(this$0, str, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAssignment$lambda$6(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$restoreAssignment$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {385}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$restoreAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08461(String str, Continuation<? super C08461> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JavaScriptInterfaceAssignment.this.new C08461(this.$assignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0070  */
        /* JADX WARN: Type inference failed for: r3v2, types: [T, java.io.File[]] */
        /* JADX WARN: Type inference failed for: r5v6, types: [T, java.io.File[]] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instructions count: 281
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.C08461.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @JavascriptInterface
    public final void restoreAssignment(String assignmentId) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08461(assignmentId, null), 2, null);
    }

    @JavascriptInterface
    public final void changeModeAssignment(String assignmentId) {
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("change_mode")) {
            AssignmentEntity assignmentEntity = (AssignmentEntity) BuildersKt__BuildersKt.runBlocking$default(null, new JavaScriptInterfaceAssignment$changeModeAssignment$assignment$1(assignmentId, null), 1, null);
            SurveyEntity surveyEntity = (SurveyEntity) BuildersKt__BuildersKt.runBlocking$default(null, new JavaScriptInterfaceAssignment$changeModeAssignment$survey$1(this, null), 1, null);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(assignmentEntity, this, (PeriodeEntityNew) BuildersKt__BuildersKt.runBlocking$default(null, new JavaScriptInterfaceAssignment$changeModeAssignment$periode$1(this, null), 1, null), surveyEntity, null), 2, null);
            return;
        }
        Toast.makeText(this.activity, "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$changeModeAssignment$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$changeModeAssignment$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AssignmentEntity $assignment;
        final /* synthetic */ PeriodeEntityNew $periode;
        final /* synthetic */ SurveyEntity $survey;
        int label;
        final /* synthetic */ JavaScriptInterfaceAssignment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AssignmentEntity assignmentEntity, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, PeriodeEntityNew periodeEntityNew, SurveyEntity surveyEntity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$assignment = assignmentEntity;
            this.this$0 = javaScriptInterfaceAssignment;
            this.$periode = periodeEntityNew;
            this.$survey = surveyEntity;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(View view) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$assignment, this.this$0, this.$periode, this.$survey, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0027  */
        /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r6v6, types: [T, java.lang.String[]] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instructions count: 388
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invokeSuspend$lambda$4(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, Ref.ObjectRef objectRef, AssignmentEntity assignmentEntity, DialogInterface dialogInterface, int i) {
            javaScriptInterfaceAssignment.showAlertDialogChangeMode(((String[]) objectRef.element)[javaScriptInterfaceAssignment.getIndexPathFileBackup()], assignmentEntity);
        }
    }

    private final void validasiEmail(String email, Function2<? super Boolean, ? super String, Unit> callback) {
        String str = email;
        if (str.length() == 0) {
            callback.invoke(true, null);
        } else if (Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
            callback.invoke(true, email);
        } else {
            callback.invoke(false, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAlertDialogChangeMode(final String newMode, final AssignmentEntity assignment) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.activity);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_dialog_change_mode);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        ((TextView) bottomSheetDialog.findViewById(R.id.judul_bottomDialogChangeMode)).setText("Konfirmasi Pindah Mode");
        ((TextView) bottomSheetDialog.findViewById(R.id.deskripsi_bottomDialogChangeMode)).setText("Anda akan mengubah mode menjadi " + newMode + " ?");
        if (StringsKt.contains$default((CharSequence) newMode, (CharSequence) "CAPI", false, 2, (Object) null)) {
            ((CheckBox) bottomSheetDialog.findViewById(R.id.checkbox_bottomDialogChangeMode)).setVisibility(8);
            ((LinearLayout) bottomSheetDialog.findViewById(R.id.email_l_bottomDialogChangeMode)).setVisibility(8);
        } else {
            FileHelper.Companion companion = FileHelper.INSTANCE;
            String str = this.mSurveyId;
            if (str == null) {
                str = "";
            }
            String str2 = this.mPeriodeId;
            if (str2 == null) {
                str2 = "";
            }
            String id2 = assignment.getId();
            if (new File(companion.pathAnswerAssignment(str, str2, id2 != null ? id2 : "")).exists()) {
                ((CheckBox) bottomSheetDialog.findViewById(R.id.checkbox_bottomDialogChangeMode)).setVisibility(0);
                ((CheckBox) bottomSheetDialog.findViewById(R.id.checkbox_bottomDialogChangeMode)).setChecked(true);
            }
        }
        ((ImageView) bottomSheetDialog.findViewById(R.id.icon_bottomDialogChangeMode)).setVisibility(8);
        bottomSheetDialog.setCancelable(true);
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogChangeMode)).setText("Pindah");
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogChangeMode)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.showAlertDialogChangeMode$lambda$7(bottomSheetDialog, this, newMode, assignment, view);
            }
        });
        ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogChangeMode)).setText("Batal");
        ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogChangeMode)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.showAlertDialogChangeMode$lambda$8(bottomSheetDialog, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_bottomDialogChangeMode)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment.showAlertDialogChangeMode$lambda$9(bottomSheetDialog, view);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v9, types: [T, java.lang.String] */
    public static final void showAlertDialogChangeMode$lambda$7(final BottomSheetDialog dialog, final JavaScriptInterfaceAssignment this$0, final String newMode, final AssignmentEntity assignment, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(newMode, "$newMode");
        Intrinsics.checkNotNullParameter(assignment, "$assignment");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = ((EditText) dialog.findViewById(R.id.email_et_bottomDialogChangeMode)).getText().toString();
        this$0.validasiEmail((String) objectRef.element, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$showAlertDialogChangeMode$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) throws JSONException {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String str) throws JSONException {
                if (z) {
                    dialog.dismiss();
                    this$0.getActivity().showProgressBar();
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(newMode);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("modes", jSONArray);
                    String str2 = objectRef.element;
                    if (str2 == null) {
                        str2 = JSONObject.NULL;
                    }
                    jSONObject.put("email", str2);
                    jSONObject.put(HintConstants.AUTOFILL_HINT_PHONE, (Object) null);
                    String string = jSONObject.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "jsonTemp.toString()");
                    if (((CheckBox) dialog.findViewById(R.id.checkbox_bottomDialogChangeMode)).isChecked()) {
                        this$0.onlineChangeModeWithData(assignment.getId(), string);
                        return;
                    } else {
                        this$0.onlineChangeModeWithoutData(assignment.getId(), string);
                        return;
                    }
                }
                Toast.makeText(this$0.getActivity(), "Email yang anda tuliskan tidak sesuai", 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogChangeMode$lambda$8(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAlertDialogChangeMode$lambda$9(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1, reason: invalid class name and case insensitive filesystem */
    static final class C08421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        final /* synthetic */ String $param;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08421(String str, String str2, Continuation<? super C08421> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
            this.$param = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08421 c08421 = JavaScriptInterfaceAssignment.this.new C08421(this.$assignmentId, this.$param, continuation);
            c08421.L$0 = obj;
            return c08421;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: JavaScriptInterfaceAssignment.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01841 extends Lambda implements Function2<Boolean, String, Unit> {
            final /* synthetic */ CoroutineScope $$this$launch;
            final /* synthetic */ String $assignmentId;
            final /* synthetic */ String $param;
            final /* synthetic */ JavaScriptInterfaceAssignment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01841(String str, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, String str2, CoroutineScope coroutineScope) {
                super(2);
                this.$assignmentId = str;
                this.this$0 = javaScriptInterfaceAssignment;
                this.$param = str2;
                this.$$this$launch = coroutineScope;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) throws JSONException {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, final String message) throws JSONException {
                Intrinsics.checkNotNullParameter(message, "message");
                if (z) {
                    final AssignmentEntity assignment = FasihApp.INSTANCE.getAssignmentDao().getAssignmentById(this.$assignmentId).getAssignment();
                    final List<String> listListOf = CollectionsKt.listOf(this.$assignmentId + "_" + System.currentTimeMillis() + ".7z");
                    AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
                    String str = this.$assignmentId;
                    String copyFromId = assignment != null ? assignment.getCopyFromId() : null;
                    String str2 = this.this$0.mPeriodeId;
                    final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
                    final String str3 = this.$assignmentId;
                    final String str4 = this.$param;
                    final CoroutineScope coroutineScope = this.$$this$launch;
                    assignmentRepositoryImpl.assignmentSubmitS3Presign(str, copyFromId, str2, listListOf, new Function1<AssignmentSubmitS3Response, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.onlineChangeModeWithData.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AssignmentSubmitS3Response assignmentSubmitS3Response) throws NoSuchAlgorithmException, IOException {
                            invoke2(assignmentSubmitS3Response);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AssignmentSubmitS3Response assignmentSubmitS3Response) throws NoSuchAlgorithmException, IOException {
                            PresignedUrlsItem presignedUrlsItem;
                            Log.d(">>> uload assignment", new Gson().toJson(assignmentSubmitS3Response));
                            if (assignmentSubmitS3Response != null ? Intrinsics.areEqual((Object) assignmentSubmitS3Response.getSuccess(), (Object) true) : false) {
                                Pair pairCreate7zFile = javaScriptInterfaceAssignment.create7zFile(str3);
                                File file = (File) pairCreate7zFile.component1();
                                String str5 = (String) pairCreate7zFile.component2();
                                AssignmentRepositoryImpl assignmentRepositoryImpl2 = new AssignmentRepositoryImpl();
                                List<PresignedUrlsItem> presignedUrls = assignmentSubmitS3Response.getData().getPresignedUrls();
                                String presignedUrl = (presignedUrls == null || (presignedUrlsItem = presignedUrls.get(0)) == null) ? null : presignedUrlsItem.getPresignedUrl();
                                Intrinsics.checkNotNull(presignedUrl);
                                assignmentRepositoryImpl2.assignmentSubmitS3Upload(presignedUrl, file, new C01861(listListOf, str5, assignment, javaScriptInterfaceAssignment, str3, str4, coroutineScope));
                            }
                        }

                        /* compiled from: JavaScriptInterfaceAssignment.kt */
                        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1$1$1, reason: invalid class name and collision with other inner class name */
                        static final class C01861 extends Lambda implements Function1<Boolean, Unit> {
                            final /* synthetic */ CoroutineScope $$this$launch;
                            final /* synthetic */ AssignmentEntity $assignmentEntity;
                            final /* synthetic */ String $assignmentId;
                            final /* synthetic */ List<String> $fileNames;
                            final /* synthetic */ String $md5;
                            final /* synthetic */ String $param;
                            final /* synthetic */ JavaScriptInterfaceAssignment this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C01861(List<String> list, String str, AssignmentEntity assignmentEntity, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, String str2, String str3, CoroutineScope coroutineScope) {
                                super(1);
                                this.$fileNames = list;
                                this.$md5 = str;
                                this.$assignmentEntity = assignmentEntity;
                                this.this$0 = javaScriptInterfaceAssignment;
                                this.$assignmentId = str2;
                                this.$param = str3;
                                this.$$this$launch = coroutineScope;
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            /* compiled from: JavaScriptInterfaceAssignment.kt */
                            @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "response", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                            /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                            static final class C01871 extends Lambda implements Function1<BaseResponseDataUpload, Unit> {
                                final /* synthetic */ CoroutineScope $$this$launch;
                                final /* synthetic */ String $assignmentId;
                                final /* synthetic */ String $param;
                                final /* synthetic */ JavaScriptInterfaceAssignment this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C01871(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, String str, String str2, CoroutineScope coroutineScope) {
                                    super(1);
                                    this.this$0 = javaScriptInterfaceAssignment;
                                    this.$assignmentId = str;
                                    this.$param = str2;
                                    this.$$this$launch = coroutineScope;
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                                    invoke2(baseResponseDataUpload);
                                    return Unit.INSTANCE;
                                }

                                /* compiled from: JavaScriptInterfaceAssignment.kt */
                                @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                static final class C01881 extends Lambda implements Function1<BaseResponseDataUpload, Unit> {
                                    final /* synthetic */ CoroutineScope $$this$launch;
                                    final /* synthetic */ JavaScriptInterfaceAssignment this$0;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C01881(CoroutineScope coroutineScope, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment) {
                                        super(1);
                                        this.$$this$launch = coroutineScope;
                                        this.this$0 = javaScriptInterfaceAssignment;
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                                        invoke2(baseResponseDataUpload);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                                        Unit unit;
                                        if (baseResponseDataUpload != null) {
                                            this.this$0.handleChangeModeDone(baseResponseDataUpload);
                                            unit = Unit.INSTANCE;
                                        } else {
                                            unit = null;
                                        }
                                        if (unit == null) {
                                            final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
                                            javaScriptInterfaceAssignment.getActivity().runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1$1$1$1$1$$ExternalSyntheticLambda0
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    JavaScriptInterfaceAssignment.C08421.C01841.C01851.C01861.C01871.C01881.invoke$lambda$2$lambda$1(javaScriptInterfaceAssignment);
                                                }
                                            });
                                        }
                                    }

                                    /* JADX INFO: Access modifiers changed from: private */
                                    public static final void invoke$lambda$2$lambda$1(JavaScriptInterfaceAssignment this$0) {
                                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                                        this$0.getActivity().hideProgressBar();
                                        Toast.makeText(this$0.getActivity(), "Error request change mode", 1).show();
                                    }
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(final BaseResponseDataUpload baseResponseDataUpload) {
                                    if (baseResponseDataUpload != null ? Intrinsics.areEqual((Object) baseResponseDataUpload.getSuccess(), (Object) true) : false) {
                                        new AssignmentRepositoryImpl().changeModeAssignment(this.this$0.getActivity(), this.$assignmentId, this.$param, new C01881(this.$$this$launch, this.this$0));
                                        return;
                                    }
                                    AssignmentListActivity activity = this.this$0.getActivity();
                                    if (activity != null) {
                                        final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
                                        activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1$1$1$1$$ExternalSyntheticLambda0
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                JavaScriptInterfaceAssignment.C08421.C01841.C01851.C01861.C01871.invoke$lambda$0(javaScriptInterfaceAssignment, baseResponseDataUpload);
                                            }
                                        });
                                    }
                                }

                                /* JADX INFO: Access modifiers changed from: private */
                                public static final void invoke$lambda$0(JavaScriptInterfaceAssignment this$0, BaseResponseDataUpload baseResponseDataUpload) {
                                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                                    AssignmentListActivity activity = this$0.getActivity();
                                    if (activity != null) {
                                        activity.hideProgressBar();
                                    }
                                    Toast.makeText(this$0.getActivity(), baseResponseDataUpload != null ? baseResponseDataUpload.getMessage() : null, 0).show();
                                }
                            }

                            public final void invoke(boolean z) {
                                if (z) {
                                    AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
                                    String str = this.$fileNames.get(0);
                                    String str2 = this.$md5;
                                    AssignmentEntity assignmentEntity = this.$assignmentEntity;
                                    String periodeNotPrimary = assignmentEntity != null ? assignmentEntity.getPeriodeNotPrimary() : null;
                                    Intrinsics.checkNotNull(periodeNotPrimary);
                                    AssignmentEntity assignmentEntity2 = this.$assignmentEntity;
                                    String id2 = assignmentEntity2 != null ? assignmentEntity2.getId() : null;
                                    Intrinsics.checkNotNull(id2);
                                    AssignmentEntity assignmentEntity3 = this.$assignmentEntity;
                                    Boolean boolValueOf = assignmentEntity3 != null ? Boolean.valueOf(assignmentEntity3.isNew()) : null;
                                    Intrinsics.checkNotNull(boolValueOf);
                                    boolean zBooleanValue = boolValueOf.booleanValue();
                                    Region region = this.$assignmentEntity.getRegion();
                                    String id3 = region != null ? region.getId() : null;
                                    Intrinsics.checkNotNull(id3);
                                    AssignmentEntity assignmentEntity4 = this.$assignmentEntity;
                                    String data1 = assignmentEntity4 != null ? assignmentEntity4.getData1() : null;
                                    AssignmentEntity assignmentEntity5 = this.$assignmentEntity;
                                    String data2 = assignmentEntity5 != null ? assignmentEntity5.getData2() : null;
                                    AssignmentEntity assignmentEntity6 = this.$assignmentEntity;
                                    String data3 = assignmentEntity6 != null ? assignmentEntity6.getData3() : null;
                                    AssignmentEntity assignmentEntity7 = this.$assignmentEntity;
                                    String data4 = assignmentEntity7 != null ? assignmentEntity7.getData4() : null;
                                    AssignmentEntity assignmentEntity8 = this.$assignmentEntity;
                                    String data5 = assignmentEntity8 != null ? assignmentEntity8.getData5() : null;
                                    AssignmentEntity assignmentEntity9 = this.$assignmentEntity;
                                    String data6 = assignmentEntity9 != null ? assignmentEntity9.getData6() : null;
                                    AssignmentEntity assignmentEntity10 = this.$assignmentEntity;
                                    String data7 = assignmentEntity10 != null ? assignmentEntity10.getData7() : null;
                                    AssignmentEntity assignmentEntity11 = this.$assignmentEntity;
                                    String data8 = assignmentEntity11 != null ? assignmentEntity11.getData8() : null;
                                    AssignmentEntity assignmentEntity12 = this.$assignmentEntity;
                                    String data9 = assignmentEntity12 != null ? assignmentEntity12.getData9() : null;
                                    AssignmentEntity assignmentEntity13 = this.$assignmentEntity;
                                    String data10 = assignmentEntity13 != null ? assignmentEntity13.getData10() : null;
                                    AssignmentEntity assignmentEntity14 = this.$assignmentEntity;
                                    Float latitude = assignmentEntity14 != null ? assignmentEntity14.getLatitude() : null;
                                    AssignmentEntity assignmentEntity15 = this.$assignmentEntity;
                                    Float longitude = assignmentEntity15 != null ? assignmentEntity15.getLongitude() : null;
                                    AssignmentEntity assignmentEntity16 = this.$assignmentEntity;
                                    String copyFromId = assignmentEntity16 != null ? assignmentEntity16.getCopyFromId() : null;
                                    AssignmentEntity assignmentEntity17 = this.$assignmentEntity;
                                    String paradata = assignmentEntity17 != null ? assignmentEntity17.getParadata() : null;
                                    AssignmentEntity assignmentEntity18 = this.$assignmentEntity;
                                    String comment = assignmentEntity18 != null ? assignmentEntity18.getComment() : null;
                                    AssignmentEntity assignmentEntity19 = this.$assignmentEntity;
                                    String str3 = data4;
                                    assignmentRepositoryImpl.assignmentSubmitS3Post(str, str2, periodeNotPrimary, id2, zBooleanValue, true, id3, data1, data2, data3, str3, data5, data6, data7, data8, data9, data10, latitude, longitude, copyFromId, paradata, comment, assignmentEntity19 != null ? assignmentEntity19.getNote() : null, new C01871(this.this$0, this.$assignmentId, this.$param, this.$$this$launch));
                                    return;
                                }
                                AssignmentListActivity activity = this.this$0.getActivity();
                                if (activity != null) {
                                    final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
                                    activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1$1$1$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            JavaScriptInterfaceAssignment.C08421.C01841.C01851.C01861.invoke$lambda$0(javaScriptInterfaceAssignment);
                                        }
                                    });
                                }
                            }

                            /* JADX INFO: Access modifiers changed from: private */
                            public static final void invoke$lambda$0(JavaScriptInterfaceAssignment this$0) {
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                AssignmentListActivity activity = this$0.getActivity();
                                if (activity != null) {
                                    activity.hideProgressBar();
                                }
                                Toast.makeText(this$0.getActivity(), "Gagal upload data", 0).show();
                            }
                        }
                    });
                    return;
                }
                AssignmentListActivity activity = this.this$0.getActivity();
                final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment2 = this.this$0;
                activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithData$1$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        JavaScriptInterfaceAssignment.C08421.C01841.invoke$lambda$0(javaScriptInterfaceAssignment2, message);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$0(JavaScriptInterfaceAssignment this$0, String message) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(message, "$message");
                this$0.getActivity().hideProgressBar();
                Toast.makeText(this$0.getActivity(), message, 1).show();
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                JavaScriptInterfaceAssignment.this.createUploadModel(this.$assignmentId, new C01841(this.$assignmentId, JavaScriptInterfaceAssignment.this, this.$param, (CoroutineScope) this.L$0));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onlineChangeModeWithData(String assignmentId, String param) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08421(assignmentId, param, null), 2, null);
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithoutData$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithoutData$1, reason: invalid class name and case insensitive filesystem */
    static final class C08431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        final /* synthetic */ String $bodyParam;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08431(String str, String str2, Continuation<? super C08431> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
            this.$bodyParam = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08431 c08431 = JavaScriptInterfaceAssignment.this.new C08431(this.$assignmentId, this.$bodyParam, continuation);
            c08431.L$0 = obj;
            return c08431;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: JavaScriptInterfaceAssignment.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithoutData$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01891 extends Lambda implements Function1<BaseResponseDataUpload, Unit> {
            final /* synthetic */ CoroutineScope $$this$launch;
            final /* synthetic */ JavaScriptInterfaceAssignment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01891(CoroutineScope coroutineScope, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment) {
                super(1);
                this.$$this$launch = coroutineScope;
                this.this$0 = javaScriptInterfaceAssignment;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                invoke2(baseResponseDataUpload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                Unit unit;
                if (baseResponseDataUpload != null) {
                    this.this$0.handleChangeModeDone(baseResponseDataUpload);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
                    javaScriptInterfaceAssignment.getActivity().runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$onlineChangeModeWithoutData$1$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            JavaScriptInterfaceAssignment.C08431.C01891.invoke$lambda$2$lambda$1(javaScriptInterfaceAssignment);
                        }
                    });
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$2$lambda$1(JavaScriptInterfaceAssignment this$0) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                this$0.getActivity().hideProgressBar();
                Toast.makeText(this$0.getActivity(), "Error request change mode", 1).show();
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            new AssignmentRepositoryImpl().changeModeAssignment(JavaScriptInterfaceAssignment.this.getActivity(), this.$assignmentId, this.$bodyParam, new C01891((CoroutineScope) this.L$0, JavaScriptInterfaceAssignment.this));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onlineChangeModeWithoutData(String assignmentId, String bodyParam) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08431(assignmentId, bodyParam, null), 2, null);
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$handleChangeModeDone$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$handleChangeModeDone$1, reason: invalid class name and case insensitive filesystem */
    static final class C08411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BaseResponseDataUpload $response;
        int label;
        final /* synthetic */ JavaScriptInterfaceAssignment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08411(BaseResponseDataUpload baseResponseDataUpload, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, Continuation<? super C08411> continuation) {
            super(2, continuation);
            this.$response = baseResponseDataUpload;
            this.this$0 = javaScriptInterfaceAssignment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08411(this.$response, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateAssignmentAfterUpload(this.$response.getData());
            AssignmentReturnData data = this.$response.getData();
            Intrinsics.checkNotNull(data);
            List<String> mode = data.getMode();
            boolean z = false;
            if (mode != null && mode.contains("CAPI")) {
                z = true;
            }
            if (!z) {
                FileHelper.Companion companion = FileHelper.INSTANCE;
                AssignmentReturnData data2 = this.$response.getData();
                Intrinsics.checkNotNull(data2);
                companion.deleteListDownloaded(data2.getId() + InternalZipConstants.ZIP_FILE_SEPARATOR + FasihApp.INSTANCE.getSession().getUserId(), new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.handleChangeModeDone.1.1
                    public final void invoke(boolean z2, String message) {
                        Intrinsics.checkNotNullParameter(message, "message");
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                        invoke(bool.booleanValue(), str);
                        return Unit.INSTANCE;
                    }
                });
            }
            AssignmentListActivity activity = this.this$0.getActivity();
            final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
            activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$handleChangeModeDone$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    JavaScriptInterfaceAssignment.C08411.invokeSuspend$lambda$0(javaScriptInterfaceAssignment);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment) {
            javaScriptInterfaceAssignment.getActivity().hideProgressBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleChangeModeDone(BaseResponseDataUpload response) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08411(response, this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createUploadModel(String assignmentId, Function2<? super Boolean, ? super String, Unit> callback) throws IOException {
        File file = new File(FileHelper.INSTANCE.pathTempAnswer(this.mSurveyId, this.mPeriodeId));
        FileHelper.Companion companion = FileHelper.INSTANCE;
        String str = this.mSurveyId;
        if (str == null) {
            str = "";
        }
        String str2 = this.mPeriodeId;
        if (str2 == null) {
            str2 = "";
        }
        File file2 = new File(companion.pathAnswerAssignment(str, str2, assignmentId != null ? assignmentId : ""));
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(file.getPath() + File.separator + assignmentId + ".7z");
        if (file3.exists()) {
            FilesKt.deleteRecursively(file3);
        }
        String strRequestFileAssignment = requestFileAssignment(file2, file, assignmentId);
        if (strRequestFileAssignment != null) {
            file3 = new File(strRequestFileAssignment);
        }
        if (!file3.exists()) {
            callback.invoke(false, "Gagal file tidak ditemukan");
        } else {
            callback.invoke(true, "Sukses");
        }
    }

    private final String requestFileAssignment(File folderIn, File folderTemp, String assignmentId) throws IOException {
        try {
            AssignmentListActivity assignmentListActivity = this.activity;
            if (assignmentListActivity != null) {
                assignmentListActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        JavaScriptInterfaceAssignment.requestFileAssignment$lambda$11(this.f$0);
                    }
                });
            }
            File file = new File(folderTemp.getPath() + File.separator + assignmentId);
            if (!file.exists()) {
                file.mkdirs();
            }
            FilesKt.copyRecursively$default(folderIn, file, true, null, 4, null);
            AssignmentEntity assignment = ((Sync) BuildersKt__BuildersKt.runBlocking$default(null, new JavaScriptInterfaceAssignment$requestFileAssignment$assignment$1(assignmentId, null), 1, null)).getAssignment();
            Intrinsics.checkNotNull(assignment);
            if (assignment.isEncrypt()) {
                File file2 = new File(file.getPath() + File.separator + "data" + CommonCons.INSTANCE.getEXTENSION_JSON());
                AssignmentEncryptionHelper assignmentEncryptionHelper = AssignmentEncryptionHelper.INSTANCE;
                FileHelper.Companion companion = FileHelper.INSTANCE;
                FileHelper.Companion companion2 = FileHelper.INSTANCE;
                String str = this.mSurveyId;
                String str2 = "";
                if (str == null) {
                    str = "";
                }
                String str3 = this.mPeriodeId;
                if (str3 == null) {
                    str3 = "";
                }
                if (assignmentId != null) {
                    str2 = assignmentId;
                }
                String string = companion.readFile(companion2.pathAnswerAssignment(str, str3, str2) + "/data.json").toString();
                Intrinsics.checkNotNullExpressionValue(string, "FileHelper.readFile(\n   …             ).toString()");
                String strDecrypt = assignmentEncryptionHelper.decrypt(string, AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(this.mRegionId, this.mPeriodeId));
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(strDecrypt);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            File file3 = new File(file.getPath() + File.separator + FormGearJavascriptInterface.REFERENCE_FILE + CommonCons.INSTANCE.getEXTENSION_JSON());
            ZipHelper.Companion companion3 = ZipHelper.INSTANCE;
            String path = file3.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "fileReference.path");
            companion3.zipFile(path, file.getPath() + File.separator + FormGearJavascriptInterface.REFERENCE_FILE);
            file3.delete();
            FilesKt.deleteRecursively(new File(file.getPath() + File.separator + FormGearJavascriptInterface.MEDIA_FILE));
            new File(file.getPath() + File.separator + FormGearJavascriptInterface.NOTE_FILE + CommonCons.INSTANCE.getEXTENSION_TXT()).delete();
            ZipHelper.Companion companion4 = ZipHelper.INSTANCE;
            String path2 = file.getPath();
            Intrinsics.checkNotNullExpressionValue(path2, "folderOut.path");
            String path3 = file.getPath();
            Intrinsics.checkNotNullExpressionValue(path3, "folderOut.path");
            companion4.zip7File(path2, path3);
            FilesKt.deleteRecursively(file);
            return folderTemp.getPath() + File.separator + assignmentId + ".7z";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestFileAssignment$lambda$11(JavaScriptInterfaceAssignment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AssignmentListActivity assignmentListActivity = this$0.activity;
        if (assignmentListActivity != null) {
            assignmentListActivity.showProgressBar("Compressing Assignment");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<File, String> create7zFile(String assignmentId) throws NoSuchAlgorithmException, IOException {
        Log.d(">>> create 7z", "start");
        File file = new File(FileHelper.INSTANCE.pathTempAnswer(this.mSurveyId, this.mPeriodeId));
        FileHelper.Companion companion = FileHelper.INSTANCE;
        String str = this.mSurveyId;
        if (str == null) {
            str = "";
        }
        String str2 = this.mPeriodeId;
        if (str2 == null) {
            str2 = "";
        }
        File file2 = new File(companion.pathAnswerAssignment(str, str2, assignmentId != null ? assignmentId : ""));
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(file2, "checksum.md5");
        FilesKt.writeText$default(file3, "This file will contain MD5", null, 2, null);
        File file4 = new File(file.getPath() + File.separator + assignmentId + ".7z");
        if (file4.exists()) {
            FilesKt.deleteRecursively(file4);
        }
        String strRequestFileAssignment = requestFileAssignment(file2, file, assignmentId);
        if (strRequestFileAssignment != null) {
            file4 = new File(strRequestFileAssignment);
        }
        String strCalculateMD5 = calculateMD5(file4);
        FilesKt.writeText$default(file3, strCalculateMD5, null, 2, null);
        return new Pair<>(file4, strCalculateMD5);
    }

    private final String calculateMD5(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[8192];
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i != -1) {
                messageDigest.update(bArr, 0, i);
            } else {
                fileInputStream.close();
                byte[] md5Bytes = messageDigest.digest();
                Intrinsics.checkNotNullExpressionValue(md5Bytes, "md5Bytes");
                return ArraysKt.joinToString$default(md5Bytes, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.calculateMD5.2
                    public final CharSequence invoke(byte b) {
                        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
                        return str;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                        return invoke(b.byteValue());
                    }
                }, 30, (Object) null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r4v8, types: [T, java.lang.String[]] */
    public final void showRestore(final AssignmentEntity assignment, final File[] listBackupAssignmentOrigin) {
        Intrinsics.checkNotNullParameter(assignment, "assignment");
        Intrinsics.checkNotNullParameter(listBackupAssignmentOrigin, "listBackupAssignmentOrigin");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new String[0];
        for (File file : listBackupAssignmentOrigin) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String name = file.getName();
            Intrinsics.checkNotNullExpressionValue(name, "i.name");
            objectRef.element = appendString((String[]) objectRef.element, simpleDateFormat.format((Date) new java.sql.Date(Long.parseLong(name))) + " (" + formatFileSize(new File(file, "data" + CommonCons.INSTANCE.getEXTENSION_JSON()).length()) + ")");
        }
        if (listBackupAssignmentOrigin.length == 0) {
            Toast.makeText(this.activity, "Tidak ada file yang dapat direstore, assignment belum pernah dibuka", 0).show();
            return;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.activity).setTitle("Ada " + listBackupAssignmentOrigin.length + " file. Pilih versi file yang akan direstore").setSingleChoiceItems((CharSequence[]) objectRef.element, 0, new DialogInterface.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                JavaScriptInterfaceAssignment.showRestore$lambda$14(this.f$0, dialogInterface, i);
            }
        }).setPositiveButton("Pilih", new DialogInterface.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                JavaScriptInterfaceAssignment.showRestore$lambda$15(this.f$0, objectRef, listBackupAssignmentOrigin, assignment, dialogInterface, i);
            }
        }).create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "Builder(activity)\n      …              }).create()");
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRestore$lambda$14(JavaScriptInterfaceAssignment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.indexPathFileBackup = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRestore$lambda$15(JavaScriptInterfaceAssignment this$0, Ref.ObjectRef listBackupAssignmentLabel, File[] listBackupAssignmentOrigin, AssignmentEntity assignment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listBackupAssignmentLabel, "$listBackupAssignmentLabel");
        Intrinsics.checkNotNullParameter(listBackupAssignmentOrigin, "$listBackupAssignmentOrigin");
        Intrinsics.checkNotNullParameter(assignment, "$assignment");
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this$0.model;
        Intrinsics.checkNotNull(assignmentUpdateListingViewModel);
        assignmentUpdateListingViewModel.getLastBackupTime(new JavaScriptInterfaceAssignment$showRestore$dialog$2$1(listBackupAssignmentLabel, this$0, listBackupAssignmentOrigin, assignment, dialogInterface));
    }

    private final String formatFileSize(long size) {
        if (size >= 1024) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(Locale.US, "%.1f KB", Arrays.copyOf(new Object[]{Double.valueOf(size / 1024.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
            return str;
        }
        return size + " B";
    }

    public final File[] appendFile(File[] arr, File element) {
        Intrinsics.checkNotNullParameter(arr, "arr");
        Intrinsics.checkNotNullParameter(element, "element");
        List mutableList = ArraysKt.toMutableList(arr);
        mutableList.add(element);
        return (File[]) mutableList.toArray(new File[0]);
    }

    public final String[] appendString(String[] arr, String element) {
        Intrinsics.checkNotNullParameter(arr, "arr");
        Intrinsics.checkNotNullParameter(element, "element");
        List mutableList = ArraysKt.toMutableList(arr);
        mutableList.add(element);
        return (String[]) mutableList.toArray(new String[0]);
    }

    @JavascriptInterface
    public final void openDaftarUpload() {
        this.activity.startActivity(new Intent(this.activity, (Class<?>) UploadActivity.class));
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$setSorting$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$setSorting$1, reason: invalid class name and case insensitive filesystem */
    static final class C08481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $str;
        int label;
        final /* synthetic */ JavaScriptInterfaceAssignment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08481(String str, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, Continuation<? super C08481> continuation) {
            super(2, continuation);
            this.$str = str;
            this.this$0 = javaScriptInterfaceAssignment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08481(this.$str, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                String str = "javascript:setSorting('" + this.$str + "')";
                WebView webView = this.this$0.mWebView;
                if (webView != null) {
                    webView.evaluateJavascript(str, null);
                }
            } catch (Error unused) {
            }
            return Unit.INSTANCE;
        }
    }

    public final void setSorting(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08481(str, this, null), 2, null);
    }

    /* compiled from: JavaScriptInterfaceAssignment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$saveStateDataTable$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {1058}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$saveStateDataTable$1, reason: invalid class name and case insensitive filesystem */
    static final class C08471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08471(String str, Continuation<? super C08471> continuation) {
            super(2, continuation);
            this.$data = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JavaScriptInterfaceAssignment.this.new C08471(this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().updateStateDataTable(JavaScriptInterfaceAssignment.this.mRegionId, JavaScriptInterfaceAssignment.this.mPeriodeId, this.$data, this) == coroutine_suspended) {
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

    @JavascriptInterface
    public final void saveStateDataTable(String data) throws InterruptedException {
        Intrinsics.checkNotNullParameter(data, "data");
        BuildersKt__BuildersKt.runBlocking$default(null, new C08471(data, null), 1, null);
    }

    @JavascriptInterface
    public final String getStateDataTable() {
        String stateDataTable;
        AssignmentRegionEntity assignmentRegionEntity = (AssignmentRegionEntity) BuildersKt__BuildersKt.runBlocking$default(null, new JavaScriptInterfaceAssignment$getStateDataTable$assignmentRegion$1(this, null), 1, null);
        return (assignmentRegionEntity == null || (stateDataTable = assignmentRegionEntity.getStateDataTable()) == null) ? "" : stateDataTable;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void downloadAssignmentData(java.lang.String r15) {
        /*
            r14 = this;
            java.lang.String r0 = "assignmentId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadAssignmentData$assignment$1 r0 = new id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadAssignmentData$assignment$1
            r1 = 0
            r0.<init>(r15, r1)
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r15 = 1
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.runBlocking$default(r1, r0, r15, r1)
            id.go.bpsfasih.data.local.entities.AssignmentEntity r0 = (id.go.bpsfasih.data.local.entities.AssignmentEntity) r0
            r1 = 0
            if (r0 == 0) goto L2b
            java.lang.Integer r2 = r0.getAssignmentStatusId()
            id.go.bpsfasih.data.CommonCons r3 = id.go.bpsfasih.data.CommonCons.INSTANCE
            int r3 = r3.getASSIGNMENT_STATUS_OPEN()
            if (r2 != 0) goto L24
            goto L2b
        L24:
            int r2 = r2.intValue()
            if (r2 != r3) goto L2b
            goto L2c
        L2b:
            r15 = r1
        L2c:
            if (r15 != 0) goto L8a
            id.go.bpsfasih.utils.helper.FileHelper$Companion r15 = id.go.bpsfasih.utils.helper.FileHelper.INSTANCE
            java.lang.String r1 = ""
            if (r0 == 0) goto L3a
            java.lang.String r2 = r0.getSurveyIdNotPrimary()
            if (r2 != 0) goto L3b
        L3a:
            r2 = r1
        L3b:
            java.lang.String r3 = r14.mPeriodeId
            if (r3 != 0) goto L40
            r3 = r1
        L40:
            if (r0 == 0) goto L4a
            java.lang.String r4 = r0.getId()
            if (r4 != 0) goto L49
            goto L4a
        L49:
            r1 = r4
        L4a:
            java.lang.String r15 = r15.pathAnswerAssignment(r2, r3, r1)
            com.google.android.material.bottomsheet.BottomSheetDialog r2 = new com.google.android.material.bottomsheet.BottomSheetDialog
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity r1 = r14.activity
            android.content.Context r1 = (android.content.Context) r1
            r2.<init>(r1)
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity r1 = r14.activity
            r3 = r1
            id.go.bpsfasih.BaseClassActivityNew r3 = (id.go.bpsfasih.BaseClassActivityNew) r3
            int r4 = id.go.bpsfasih.R.string.alert_title_redownload_data
            java.lang.String r4 = r1.getString(r4)
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity r1 = r14.activity
            int r5 = id.go.bpsfasih.R.string.alert_redownload_data
            java.lang.String r5 = r1.getString(r5)
            java.lang.String r6 = "Untuk melakukan aksi berikut, tolong ketik kode verifikasi dengan benar"
            java.lang.String r7 = "YA"
            id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda8 r8 = new id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda8
            r8.<init>()
            java.lang.String r15 = "TIDAK"
            id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda9 r9 = new id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$$ExternalSyntheticLambda9
            r9.<init>()
            r10 = 0
            r11 = 0
            r12 = 512(0x200, float:7.175E-43)
            r13 = 0
            r1 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r15
            id.go.bpsfasih.BaseClassActivityNew.showAlertDialogKodeVerifikasi$default(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            goto L9e
        L8a:
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity r15 = r14.activity
            r0 = r15
            android.content.Context r0 = (android.content.Context) r0
            int r2 = id.go.bpsfasih.R.string.alert_status_assignment
            java.lang.String r15 = r15.getString(r2)
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15
            android.widget.Toast r15 = android.widget.Toast.makeText(r0, r15, r1)
            r15.show()
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment.downloadAssignmentData(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadAssignmentData$lambda$16(final AssignmentEntity assignmentEntity, final String answerPath, final JavaScriptInterfaceAssignment this$0, final BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(answerPath, "$answerPath");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
        Intrinsics.checkNotNull(assignmentEntity);
        assignmentRepositoryImpl.getAssignmentAnswer(assignmentEntity.getId(), new Function1<AssignmentAnswerResponse, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadAssignmentData$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentAnswerResponse assignmentAnswerResponse) throws IOException {
                invoke2(assignmentAnswerResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentAnswerResponse assignmentAnswerResponse) throws IOException {
                Log.d(">>> data", (assignmentAnswerResponse != null ? assignmentAnswerResponse.getData() : null));
                String data = assignmentAnswerResponse != null ? assignmentAnswerResponse.getData() : null;
                if (data == null || data.length() == 0) {
                    return;
                }
                FileHelper.Companion companion = FileHelper.INSTANCE;
                String str = answerPath;
                String str2 = "data" + CommonCons.INSTANCE.getEXTENSION_JSON();
                Intrinsics.checkNotNull(assignmentAnswerResponse);
                String string = assignmentAnswerResponse.getData().toString();
                Long lValueOf = Long.valueOf(System.currentTimeMillis());
                final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this$0;
                final AssignmentEntity assignmentEntity2 = assignmentEntity;
                final BottomSheetDialog bottomSheetDialog = dialog;
                companion.saveAssignmentFile(str, str2, string, lValueOf, false, (32 & 32) != 0 ? null : null, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadAssignmentData$1$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    /* compiled from: JavaScriptInterfaceAssignment.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadAssignmentData$1$1$1$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {1112}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$downloadAssignmentData$1$1$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C01791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ AssignmentEntity $assignment;
                        int label;
                        final /* synthetic */ JavaScriptInterfaceAssignment this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C01791(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, AssignmentEntity assignmentEntity, Continuation<? super C01791> continuation) {
                            super(2, continuation);
                            this.this$0 = javaScriptInterfaceAssignment;
                            this.$assignment = assignmentEntity;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C01791(this.this$0, this.$assignment, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C01791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (this.this$0.assignmentSubmitVersionRepo.upsert(this.$assignment.getId(), this.$assignment.getSubmitVersionCode(), this) == coroutine_suspended) {
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

                    public final void invoke(boolean z) {
                        javaScriptInterfaceAssignment.getAssignmentRepo().updateDataDownloadedAt(assignmentEntity2.getId());
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C01791(javaScriptInterfaceAssignment, assignmentEntity2, null), 2, null);
                        Toast.makeText(javaScriptInterfaceAssignment.getActivity(), javaScriptInterfaceAssignment.getActivity().getString(R.string.success_replace_data), 0).show();
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadAssignmentData$lambda$17(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }
}

package id.go.bpsfasih.ui.assignmentList;

import android.app.Activity;
import android.app.Application;
import android.content.res.ColorStateList;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.CustomColumnEntity;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.entities.Data;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.SurveyRolesEntity;
import id.go.bpsfasih.data.local.entities.UserRolesEntity;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.local.repository.AssignmentRegionRepository;
import id.go.bpsfasih.data.local.repository.PeriodeRepository;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.data.remote.dto.RegionMetadataResponse;
import id.go.bpsfasih.data.remote.dto.SurveyRolesResponse;
import id.go.bpsfasih.data.remote.dto.UserRolesResponse;
import id.go.bpsfasih.data.repository.RegionRepositoryImpl;
import id.go.bpsfasih.data.repository.SurveyRepositoryImpl;
import id.go.bpsfasih.domain.models.BackupModel;
import id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel;
import id.go.bpsfasih.utils.CrashHandler;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.AssignmentEncryptionHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import net.lingala.zip4j.util.InternalZipConstants;
import okhttp3.HttpUrl;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AssignmentUpdateListingViewModel.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010L\u001a\u00020MH\u0007J'\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00050O2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00050O2\u0006\u0010Q\u001a\u00020\u0005¢\u0006\u0002\u0010RJ\u000e\u0010S\u001a\u00020M2\u0006\u0010T\u001a\u00020\u0005J\u0006\u0010U\u001a\u00020MJ\u0016\u0010V\u001a\u00020M2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020M0XH\u0002J\u001c\u0010Y\u001a\u00020M2\u0006\u0010Z\u001a\u00020\u00192\f\u0010W\u001a\b\u0012\u0004\u0012\u00020M0XJ\u0006\u0010[\u001a\u00020MJ?\u0010\\\u001a\u00020M2\b\u0010]\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052#\u0010^\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010`¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020M0_J@\u0010d\u001a\u00020M28\u0010^\u001a4\u0012\u0013\u0012\u00110\f¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(f\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(g\u0012\u0004\u0012\u00020M0eJ\u0010\u0010h\u001a\u00020M2\u0006\u0010i\u001a\u00020\u0019H\u0002J\u000e\u0010j\u001a\u00020M2\u0006\u0010Z\u001a\u00020\u0019J\b\u0010k\u001a\u00020MH\u0002J\b\u0010l\u001a\u00020MH\u0002J8\u0010m\u001a\u00020M2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u00052\u0018\u0010^\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020r0\"\u0012\u0004\u0012\u00020M0_Jh\u0010s\u001a\u00020M2\b\u0010t\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010T\u001a\u0004\u0018\u00010\u000528\u0010^\u001a4\u0012\u0013\u0012\u00110\f¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(u\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(v\u0012\u0004\u0012\u00020M0eJ\u0006\u0010w\u001a\u00020MJL\u0010x\u001a\u00020M2\f\u0010y\u001a\b\u0012\u0004\u0012\u00020o0z2\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u00052\f\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00050z2\u0018\u0010^\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\"\u0012\u0004\u0012\u00020M0_J\u0006\u0010|\u001a\u00020MJ\u0006\u0010}\u001a\u00020MJ\u0010\u0010~\u001a\u00020M2\u0006\u0010v\u001a\u00020\u0005H\u0002J\u0006\u0010\u007f\u001a\u00020MJ+\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00052\n\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\u0019H\u0002J\u001f\u0010\u0085\u0001\u001a\u00020M2\b\u0010Z\u001a\u0004\u0018\u00010\u00192\n\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u0001H\u0002R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\"0!¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R \u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R \u00101\u001a\b\u0012\u0004\u0012\u00020\f0\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001b\"\u0004\b2\u0010\u001dR \u00103\u001a\b\u0012\u0004\u0012\u00020\f0\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001b\"\u0004\b4\u0010\u001dR\u000e\u00105\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\f0\u0018¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001bR\u0015\u00108\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010:\u001a\u0004\b8\u00109R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0010R\u001a\u0010=\u001a\u00020>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010@\"\u0004\bE\u0010BR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0010R \u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u001b\"\u0004\bK\u0010\u001d¨\u0006\u0088\u0001"}, d2 = {"Lid/go/bpsfasih/ui/assignmentList/AssignmentUpdateListingViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "surveyId", "", "periodeId", "regionId", "templateId", "activity", "Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity;", "canAddSample", "", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity;Z)V", "PATH_DATA", "getPATH_DATA", "()Ljava/lang/String;", "getActivity", "()Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity;", "addIsVisible", "Landroidx/databinding/ObservableField;", "getAddIsVisible", "()Landroidx/databinding/ObservableField;", "assignmentAdded", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "getAssignmentAdded", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setAssignmentAdded", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "getCanAddSample", "()Z", "data", "Landroidx/lifecycle/MediatorLiveData;", "", "getData", "()Landroidx/lifecycle/MediatorLiveData;", "filter", "Ljava/util/ArrayList;", "getFilter", "()Ljava/util/ArrayList;", "setFilter", "(Ljava/util/ArrayList;)V", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "getFusedLocationClient", "()Lcom/google/android/gms/location/FusedLocationProviderClient;", "setFusedLocationClient", "(Lcom/google/android/gms/location/FusedLocationProviderClient;)V", "isAssignmentDeleted", "setAssignmentDeleted", "isCanOpen", "setCanOpen", "isDownloadingRegionSupportData", "isInit", "isListingDone", "isPencacah", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPeriodeId", "getRegionId", "selectIndex", "", "getSelectIndex", "()I", "setSelectIndex", "(I)V", "selectIndexStringOrInt", "getSelectIndexStringOrInt", "setSelectIndexStringOrInt", "getSurveyId", "getTemplateId", "userLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "getUserLatLng", "setUserLatLng", "addAssignment", "", "appendString", "", "arr", "element", "([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "assignAssignment", "assignmentId", "customColumn", "downloadRegionSupportDataForAssignment", "onReady", "Lkotlin/Function0;", "ensureRegionSupportDataForAssignment", "assignment", "filterByStatus", "getAssignmentRegion", "surveyPeriodeId", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "Lkotlin/ParameterName;", "name", "assignmentBlokSensus", "getLastBackupTime", "Lkotlin/Function2;", "isExist", "time", "insertAssignmentJson", "assignmentEntity", "openAssignment", "regionDone", "regionUndone", "requestUserPetugas", "role", "Lid/go/bpsfasih/data/local/entities/SurveyRolesEntity;", "parentUser", "smallRegionFullCode", "Lid/go/bpsfasih/data/local/entities/UserRolesEntity;", "restore", "pathFileOrigin", "isDone", "message", "setDeleteAssignment", "setUserPetugas", "listRole", "", "surveyPeriodRoleUserIds", "showDialogDoneOrUndone", "showInfoWarna", "showRegionSupportDownloadError", "sorting", "updatePreDefineData", "strPreDefData", "customData", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "originAss", "validateLocation", FirebaseAnalytics.Param.LOCATION, "Landroid/location/Location;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentUpdateListingViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final String PATH_DATA;
    private final AssignmentListActivity activity;
    private final ObservableField<Boolean> addIsVisible;
    private SingleLiveEvent<AssignmentEntity> assignmentAdded;
    private final boolean canAddSample;
    private final MediatorLiveData<List<AssignmentEntity>> data;
    private ArrayList<String> filter;
    public FusedLocationProviderClient fusedLocationClient;
    private SingleLiveEvent<Boolean> isAssignmentDeleted;
    private SingleLiveEvent<Boolean> isCanOpen;
    private boolean isDownloadingRegionSupportData;
    private boolean isInit;
    private final SingleLiveEvent<Boolean> isListingDone;
    private final Boolean isPencacah;
    private final String periodeId;
    private final String regionId;
    private int selectIndex;
    private int selectIndexStringOrInt;
    private final String surveyId;
    private final String templateId;
    private SingleLiveEvent<LatLng> userLatLng;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadRegionSupportDataForAssignment$lambda$10(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRegionSupportDownloadError$lambda$12$lambda$11(View view) {
    }

    public static final /* synthetic */ void access$regionDone(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
        assignmentUpdateListingViewModel.regionDone();
    }

    public static final /* synthetic */ void access$regionUndone(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
        assignmentUpdateListingViewModel.regionUndone();
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final String getRegionId() {
        return this.regionId;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final AssignmentListActivity getActivity() {
        return this.activity;
    }

    public final boolean getCanAddSample() {
        return this.canAddSample;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssignmentUpdateListingViewModel(Application application, String surveyId, String periodeId, String regionId, String templateId, AssignmentListActivity activity, boolean z) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.surveyId = surveyId;
        this.periodeId = periodeId;
        this.regionId = regionId;
        this.templateId = templateId;
        this.activity = activity;
        this.canAddSample = z;
        this.data = new MediatorLiveData<>();
        ObservableField<Boolean> observableField = new ObservableField<>(false);
        this.addIsVisible = observableField;
        this.assignmentAdded = new SingleLiveEvent<>();
        this.isCanOpen = new SingleLiveEvent<>();
        this.userLatLng = new SingleLiveEvent<>();
        this.isAssignmentDeleted = new SingleLiveEvent<>();
        this.isListingDone = new SingleLiveEvent<>();
        this.filter = new ArrayList<>();
        this.PATH_DATA = Directory.INSTANCE.getABSOLUTEPATHENV() + File.separator + "assignment_listing" + CommonCons.INSTANCE.getEXTENSION_JSON();
        this.isInit = true;
        Boolean bool = (Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(null), 1, null);
        this.isPencacah = bool;
        if (Intrinsics.areEqual((Object) bool, (Object) true) && z) {
            observableField.set(true);
        } else {
            observableField.set(false);
        }
        this.isAssignmentDeleted.postValue(false);
    }

    public final MediatorLiveData<List<AssignmentEntity>> getData() {
        return this.data;
    }

    public final ObservableField<Boolean> getAddIsVisible() {
        return this.addIsVisible;
    }

    public final FusedLocationProviderClient getFusedLocationClient() {
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        if (fusedLocationProviderClient != null) {
            return fusedLocationProviderClient;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
        return null;
    }

    public final void setFusedLocationClient(FusedLocationProviderClient fusedLocationProviderClient) {
        Intrinsics.checkNotNullParameter(fusedLocationProviderClient, "<set-?>");
        this.fusedLocationClient = fusedLocationProviderClient;
    }

    public final int getSelectIndex() {
        return this.selectIndex;
    }

    public final void setSelectIndex(int i) {
        this.selectIndex = i;
    }

    public final int getSelectIndexStringOrInt() {
        return this.selectIndexStringOrInt;
    }

    public final void setSelectIndexStringOrInt(int i) {
        this.selectIndexStringOrInt = i;
    }

    public final SingleLiveEvent<AssignmentEntity> getAssignmentAdded() {
        return this.assignmentAdded;
    }

    public final void setAssignmentAdded(SingleLiveEvent<AssignmentEntity> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.assignmentAdded = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> isCanOpen() {
        return this.isCanOpen;
    }

    public final void setCanOpen(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.isCanOpen = singleLiveEvent;
    }

    public final SingleLiveEvent<LatLng> getUserLatLng() {
        return this.userLatLng;
    }

    public final void setUserLatLng(SingleLiveEvent<LatLng> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.userLatLng = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> isAssignmentDeleted() {
        return this.isAssignmentDeleted;
    }

    public final void setAssignmentDeleted(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.isAssignmentDeleted = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> isListingDone() {
        return this.isListingDone;
    }

    /* renamed from: isPencacah, reason: from getter */
    public final Boolean getIsPencacah() {
        return this.isPencacah;
    }

    public final ArrayList<String> getFilter() {
        return this.filter;
    }

    public final void setFilter(ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.filter = arrayList;
    }

    public final String getPATH_DATA() {
        return this.PATH_DATA;
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {HttpStatusCodesKt.HTTP_EARLY_HINTS}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentUpdateListingViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            UserRole userRole;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PeriodeRepository periodeRepository = DataSurvey.Periode.INSTANCE.getPeriodeRepository();
                String periodeId = AssignmentUpdateListingViewModel.this.getPeriodeId();
                String userId = FasihApp.INSTANCE.getSession().getUserId();
                this.label = 1;
                obj = periodeRepository.getPeriodeByPrimaryId(periodeId + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List<UserRole> role = ((PeriodeEntityNew) obj).getRole();
            if (role == null || (userRole = role.get(0)) == null) {
                return null;
            }
            return userRole.isPencacah();
        }
    }

    public final void addAssignment() {
        if (ActivityCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08281(null), 2, null);
        } else {
            Toast.makeText(this.activity, "Location Permission tidak aktif, aktifkan Location Permission", 0).show();
        }
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08281(Continuation<? super C08281> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$2$lambda$1(View view) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentUpdateListingViewModel.this.new C08281(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getRandomAssignmentPrelist(AssignmentUpdateListingViewModel.this.getRegionId(), AssignmentUpdateListingViewModel.this.getPeriodeId(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final AssignmentEntity assignmentEntity = (AssignmentEntity) obj;
            if (assignmentEntity != null) {
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
                BaseClassActivityNew.showAlertDialog$default(assignmentUpdateListingViewModel.getActivity(), "Tambah Assignment", "Apakah Anda yakin menambahkan assignment ?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AssignmentUpdateListingViewModel.C08281.invokeSuspend$lambda$2$lambda$0(assignmentUpdateListingViewModel, assignmentEntity, view);
                    }
                }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$addAssignment$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AssignmentUpdateListingViewModel.C08281.invokeSuspend$lambda$2$lambda$1(view);
                    }
                }, false, false, 256, null);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$2$lambda$0(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, AssignmentEntity assignmentEntity, View view) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AssignmentUpdateListingViewModel$addAssignment$1$1$1$1(assignmentUpdateListingViewModel, assignmentEntity, null), 2, null);
        }
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1", f = "AssignmentUpdateListingViewModel.kt", i = {0, 0}, l = {ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, m = "invokeSuspend", n = {"isSubmitted", "isListingDone"}, s = {"L$0", "L$1"})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1, reason: invalid class name and case insensitive filesystem */
    static final class C08371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<List<AssignmentEntity>> $assignments;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ AssignmentUpdateListingViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08371(Ref.ObjectRef<List<AssignmentEntity>> objectRef, AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, Continuation<? super C08371> continuation) {
            super(2, continuation);
            this.$assignments = objectRef;
            this.this$0 = assignmentUpdateListingViewModel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$3$lambda$1(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$6$lambda$5(View view) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08371(this.$assignments, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [T, java.util.List] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.BooleanRef booleanRef;
            Ref.BooleanRef booleanRef2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.$assignments.element = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentsByPeriodeRegionId(this.this$0.getRegionId(), this.this$0.getPeriodeId());
                List<AssignmentEntity> list = this.$assignments.element;
                if (!(list == null || list.isEmpty())) {
                    booleanRef = new Ref.BooleanRef();
                    booleanRef.element = true;
                    Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
                    this.L$0 = booleanRef;
                    this.L$1 = booleanRef3;
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new C01781(booleanRef3, this.this$0, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef2 = booleanRef3;
                } else {
                    AssignmentListActivity activity = this.this$0.getActivity();
                    final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.this$0;
                    activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            AssignmentUpdateListingViewModel.C08371.invokeSuspend$lambda$8(assignmentUpdateListingViewModel);
                        }
                    });
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                booleanRef2 = (Ref.BooleanRef) this.L$1;
                booleanRef = (Ref.BooleanRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            for (AssignmentEntity assignmentEntity : this.$assignments.element) {
                Integer assignmentStatusId = assignmentEntity.getAssignmentStatusId();
                int assignment_status_submited = CommonCons.INSTANCE.getASSIGNMENT_STATUS_SUBMITED();
                if (assignmentStatusId == null || assignmentStatusId.intValue() != assignment_status_submited) {
                    Integer assignmentStatusId2 = assignmentEntity.getAssignmentStatusId();
                    int assignment_status_approved = CommonCons.INSTANCE.getASSIGNMENT_STATUS_APPROVED();
                    if (assignmentStatusId2 == null || assignmentStatusId2.intValue() != assignment_status_approved) {
                        booleanRef.element = false;
                    }
                }
            }
            if (booleanRef2.element) {
                AssignmentListActivity activity2 = this.this$0.getActivity();
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = this.this$0;
                activity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel.C08371.invokeSuspend$lambda$3(assignmentUpdateListingViewModel2);
                    }
                });
            } else if (booleanRef.element) {
                AssignmentListActivity activity3 = this.this$0.getActivity();
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel3 = this.this$0;
                activity3.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel.C08371.invokeSuspend$lambda$6(assignmentUpdateListingViewModel3);
                    }
                });
            } else {
                AssignmentListActivity activity4 = this.this$0.getActivity();
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel4 = this.this$0;
                activity4.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel.C08371.invokeSuspend$lambda$7(assignmentUpdateListingViewModel4);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        /* compiled from: AssignmentUpdateListingViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {252}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.BooleanRef $isListingDone;
            Object L$0;
            int label;
            final /* synthetic */ AssignmentUpdateListingViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01781(Ref.BooleanRef booleanRef, AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, Continuation<? super C01781> continuation) {
                super(2, continuation);
                this.$isListingDone = booleanRef;
                this.this$0 = assignmentUpdateListingViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01781(this.$isListingDone, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Ref.BooleanRef booleanRef;
                Boolean done_listing;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.BooleanRef booleanRef2 = this.$isListingDone;
                    this.L$0 = booleanRef2;
                    this.label = 1;
                    Object statusAssignmentRegion = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().getStatusAssignmentRegion(this.this$0.getRegionId(), this.this$0.getPeriodeId(), this);
                    if (statusAssignmentRegion == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef = booleanRef2;
                    obj = statusAssignmentRegion;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    booleanRef = (Ref.BooleanRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                AssignmentRegionEntity assignmentRegionEntity = (AssignmentRegionEntity) obj;
                booleanRef.element = (assignmentRegionEntity == null || (done_listing = assignmentRegionEntity.getDone_listing()) == null) ? false : done_listing.booleanValue();
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$3(final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            BaseClassActivityNew.showAlertDialog$default(assignmentUpdateListingViewModel.getActivity(), "Konfirmasi", "Anda yakin membatalkan region ini sudah selesai didata?", null, "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.C08371.invokeSuspend$lambda$3$lambda$1(view);
                }
            }, "Yakin", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.access$regionUndone(assignmentUpdateListingViewModel);
                }
            }, true, false, 256, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$6(final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            BaseClassActivityNew.showAlertDialog$default(assignmentUpdateListingViewModel.getActivity(), "Konfirmasi", "Anda yakin region ini sudah selesai didata?", null, "Sudah", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.access$regionDone(assignmentUpdateListingViewModel);
                }
            }, "Belum", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$showDialogDoneOrUndone$1$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.C08371.invokeSuspend$lambda$6$lambda$5(view);
                }
            }, true, false, 256, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$7(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            BaseClassActivityNew.showAlertDialog$default(assignmentUpdateListingViewModel.getActivity(), "Info", "Assignment belum semua tersubmit, mohon submit assignment yang masih dalam status open atau reject.", null, "Ok", null, null, null, true, false, 256, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$8(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            BaseClassActivityNew.showAlertDialog$default(assignmentUpdateListingViewModel.getActivity(), "Info", "Data tidak ditemukan", null, null, null, null, null, true, false, 256, null);
        }
    }

    public final void showDialogDoneOrUndone() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08371(new Ref.ObjectRef(), this, null), 2, null);
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1, reason: invalid class name and case insensitive filesystem */
    static final class C08331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<AssignmentRegionRepository> $repoAssignmentRegion;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08331(Ref.ObjectRef<AssignmentRegionRepository> objectRef, Continuation<? super C08331> continuation) {
            super(2, continuation);
            this.$repoAssignmentRegion = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentUpdateListingViewModel.this.new C08331(this.$repoAssignmentRegion, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException {
            AssignmentRegionEntity assignmentRegionEntity;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if ((AssignmentUpdateListingViewModel.this.getPeriodeId().length() > 0) && AssignmentUpdateListingViewModel.this.getRegionId() != null && (assignmentRegionEntity = (AssignmentRegionEntity) BuildersKt__BuildersKt.runBlocking$default(null, new AssignmentUpdateListingViewModel$regionDone$1$assignmentRegion$1(this.$repoAssignmentRegion, AssignmentUpdateListingViewModel.this, null), 1, null)) != null) {
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
                Ref.ObjectRef<AssignmentRegionRepository> objectRef = this.$repoAssignmentRegion;
                assignmentUpdateListingViewModel.getActivity().runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionDone$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel.C08331.invokeSuspend$lambda$1$lambda$0(assignmentUpdateListingViewModel);
                    }
                });
                new RegionRepositoryImpl().regionDone(assignmentRegionEntity, new AssignmentUpdateListingViewModel$regionDone$1$1$2(assignmentUpdateListingViewModel, objectRef));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1$lambda$0(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            assignmentUpdateListingViewModel.getActivity().showProgressBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, id.go.bpsfasih.data.local.repository.AssignmentRegionRepository] */
    public final void regionDone() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08331(objectRef, null), 2, null);
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionUndone$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionUndone$1, reason: invalid class name and case insensitive filesystem */
    static final class C08341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<AssignmentRegionRepository> $repoAssignmentRegion;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08341(Ref.ObjectRef<AssignmentRegionRepository> objectRef, Continuation<? super C08341> continuation) {
            super(2, continuation);
            this.$repoAssignmentRegion = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentUpdateListingViewModel.this.new C08341(this.$repoAssignmentRegion, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException {
            AssignmentRegionEntity assignmentRegionEntity;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if ((AssignmentUpdateListingViewModel.this.getPeriodeId().length() > 0) && AssignmentUpdateListingViewModel.this.getRegionId() != null && (assignmentRegionEntity = (AssignmentRegionEntity) BuildersKt__BuildersKt.runBlocking$default(null, new AssignmentUpdateListingViewModel$regionUndone$1$assignmentRegion$1(this.$repoAssignmentRegion, AssignmentUpdateListingViewModel.this, null), 1, null)) != null) {
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
                Ref.ObjectRef<AssignmentRegionRepository> objectRef = this.$repoAssignmentRegion;
                assignmentUpdateListingViewModel.getActivity().runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$regionUndone$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel.C08341.invokeSuspend$lambda$1$lambda$0(assignmentUpdateListingViewModel);
                    }
                });
                new RegionRepositoryImpl().regionUndone(assignmentRegionEntity, new AssignmentUpdateListingViewModel$regionUndone$1$1$2(assignmentUpdateListingViewModel, objectRef));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1$lambda$0(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            assignmentUpdateListingViewModel.getActivity().showProgressBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, id.go.bpsfasih.data.local.repository.AssignmentRegionRepository] */
    public final void regionUndone() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08341(objectRef, null), 2, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public final void filterByStatus() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.activity);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_filter_assignment);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setCancelable(true);
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogFilterAssignment)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssignmentUpdateListingViewModel.filterByStatus$lambda$0(bottomSheetDialog, this, view);
            }
        });
        ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogFilterAssignment)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssignmentUpdateListingViewModel.filterByStatus$lambda$1(bottomSheetDialog, this, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialogFilterAssignment)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssignmentUpdateListingViewModel.filterByStatus$lambda$2(bottomSheetDialog, view);
            }
        });
        for (String str : this.filter) {
            int iHashCode = str.hashCode();
            if (iHashCode != 1823) {
                if (iHashCode != 1824) {
                    switch (iHashCode) {
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                            if (str.equals("0")) {
                                ((CheckBox) bottomSheetDialog.findViewById(R.id.open_cb_bottomSheetFilterAssignment)).setChecked(true);
                                break;
                            } else {
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                            if (str.equals("1")) {
                                ((CheckBox) bottomSheetDialog.findViewById(R.id.submit_cb_bottomSheetFilterAssignment)).setChecked(true);
                                break;
                            } else {
                                break;
                            }
                        case 50:
                            if (str.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                ((CheckBox) bottomSheetDialog.findViewById(R.id.approve_cb_bottomSheetFilterAssignment)).setChecked(true);
                                break;
                            } else {
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                            if (str.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                ((CheckBox) bottomSheetDialog.findViewById(R.id.reject_cb_bottomSheetFilterAssignment)).setChecked(true);
                                break;
                            } else {
                                break;
                            }
                    }
                } else if (str.equals("99")) {
                    ((CheckBox) bottomSheetDialog.findViewById(R.id.pending_cb_bottomSheetFilterAssignment)).setChecked(true);
                }
            } else if (str.equals("98")) {
                ((CheckBox) bottomSheetDialog.findViewById(R.id.pernahDibuka_cb_bottomSheetFilterAssignment)).setChecked(true);
            }
        }
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void filterByStatus$lambda$0(BottomSheetDialog dialog, AssignmentUpdateListingViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.filter.clear();
        if (((CheckBox) dialog.findViewById(R.id.open_cb_bottomSheetFilterAssignment)).isChecked()) {
            this$0.filter.add("0");
        } else {
            this$0.filter.remove("0");
        }
        if (((CheckBox) dialog.findViewById(R.id.submit_cb_bottomSheetFilterAssignment)).isChecked()) {
            this$0.filter.add("1");
        } else {
            this$0.filter.remove("1");
        }
        if (((CheckBox) dialog.findViewById(R.id.approve_cb_bottomSheetFilterAssignment)).isChecked()) {
            this$0.filter.add(ExifInterface.GPS_MEASUREMENT_2D);
        } else {
            this$0.filter.remove(ExifInterface.GPS_MEASUREMENT_2D);
        }
        if (((CheckBox) dialog.findViewById(R.id.reject_cb_bottomSheetFilterAssignment)).isChecked()) {
            this$0.filter.add(ExifInterface.GPS_MEASUREMENT_3D);
        } else {
            this$0.filter.remove(ExifInterface.GPS_MEASUREMENT_3D);
        }
        if (((CheckBox) dialog.findViewById(R.id.pending_cb_bottomSheetFilterAssignment)).isChecked()) {
            this$0.filter.add("99");
        } else {
            this$0.filter.remove("99");
        }
        if (((CheckBox) dialog.findViewById(R.id.pernahDibuka_cb_bottomSheetFilterAssignment)).isChecked()) {
            this$0.filter.add("98");
        } else {
            this$0.filter.remove("98");
        }
        JavaScriptInterfaceAssignment jsi$app_release = this$0.activity.getJsi();
        if (jsi$app_release != null) {
            jsi$app_release.execute(this$0.filter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void filterByStatus$lambda$1(BottomSheetDialog dialog, AssignmentUpdateListingViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.filter.clear();
        JavaScriptInterfaceAssignment jsi$app_release = this$0.activity.getJsi();
        if (jsi$app_release != null) {
            jsi$app_release.execute(this$0.filter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void filterByStatus$lambda$2(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final void showInfoWarna() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.activity);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_info_colors);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setCancelable(true);
        ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogInfoColors)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssignmentUpdateListingViewModel.showInfoWarna$lambda$4(bottomSheetDialog, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialogInfoColors)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssignmentUpdateListingViewModel.showInfoWarna$lambda$5(bottomSheetDialog, view);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showInfoWarna$lambda$4(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showInfoWarna$lambda$5(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final void customColumn() {
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("custom_column_assignment")) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C08301(null), 3, null);
        } else {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentUpdateListingViewModel.customColumn$lambda$6(this.f$0);
                }
            });
        }
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$customColumn$1", f = "AssignmentUpdateListingViewModel.kt", i = {0, 1, 1}, l = {554, 557}, m = "invokeSuspend", n = {"listColumn", "listColumn", "dataCustomTemplate"}, s = {"L$0", "L$0", "L$1"})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$customColumn$1, reason: invalid class name and case insensitive filesystem */
    static final class C08301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        C08301(Continuation<? super C08301> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentUpdateListingViewModel.this.new C08301(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00c9  */
        /* JADX WARN: Type inference failed for: r5v1, types: [T, java.util.List] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instructions count: 420
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel.C08301.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$3(final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, Ref.ObjectRef objectRef, CustomColumnEntity customColumnEntity) {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(assignmentUpdateListingViewModel.getActivity());
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_edit_tampilan_kolom);
            Window window = bottomSheetDialog.getWindow();
            Intrinsics.checkNotNull(window);
            window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            bottomSheetDialog.setCancelable(true);
            final EditTampilanKolomBottomSheetAdapter editTampilanKolomBottomSheetAdapter = new EditTampilanKolomBottomSheetAdapter();
            ((RecyclerView) bottomSheetDialog.findViewById(R.id.kolom_rv)).setLayoutManager(new LinearLayoutManager(bottomSheetDialog.getContext()));
            ((RecyclerView) bottomSheetDialog.findViewById(R.id.kolom_rv)).setAdapter(editTampilanKolomBottomSheetAdapter);
            editTampilanKolomBottomSheetAdapter.setData((List) objectRef.element, customColumnEntity);
            ((Button) bottomSheetDialog.findViewById(R.id.rButton_bottomDialogEditTampilanKolom)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$customColumn$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.C08301.invokeSuspend$lambda$3$lambda$0(bottomSheetDialog, editTampilanKolomBottomSheetAdapter, assignmentUpdateListingViewModel, view);
                }
            });
            ((Button) bottomSheetDialog.findViewById(R.id.lButton_bottomDialogEditTampilanKolom)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$customColumn$1$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });
            ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialogFilterAssignment)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$customColumn$1$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$3$lambda$0(BottomSheetDialog bottomSheetDialog, EditTampilanKolomBottomSheetAdapter editTampilanKolomBottomSheetAdapter, final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, View view) {
            bottomSheetDialog.dismiss();
            DataSurvey.CustomColumn.INSTANCE.getCustomColumnRepo().insert(editTampilanKolomBottomSheetAdapter.getListSelected(), new Function0<Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$customColumn$1$1$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    assignmentUpdateListingViewModel.getActivity().finish();
                    assignmentUpdateListingViewModel.getActivity().startActivity(assignmentUpdateListingViewModel.getActivity().getIntent());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void customColumn$lambda$6(AssignmentUpdateListingViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
    }

    public final void sorting() {
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("sorting_assignment")) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C08381(null), 3, null);
        } else {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentUpdateListingViewModel.sorting$lambda$7(this.f$0);
                }
            });
        }
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$sorting$1", f = "AssignmentUpdateListingViewModel.kt", i = {0}, l = {668}, m = "invokeSuspend", n = {"listColumn"}, s = {"L$0"})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$sorting$1, reason: invalid class name and case insensitive filesystem */
    static final class C08381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C08381(Continuation<? super C08381> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentUpdateListingViewModel.this.new C08381(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08381) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v25, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v27, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v29, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v31, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v33, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v35, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v37, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v39, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r0v41, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r8v16, types: [T, java.lang.String[]] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final Ref.ObjectRef objectRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = new String[0];
                this.L$0 = objectRef2;
                this.label = 1;
                Object itemById = DataSurvey.CustomTemplate.INSTANCE.getCustomTemplateRepo().getItemById(AssignmentUpdateListingViewModel.this.getTemplateId(), this);
                if (itemById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                obj = itemById;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Ref.ObjectRef objectRef3 = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                objectRef = objectRef3;
            }
            CustomDataTemplateEntity customDataTemplateEntity = (CustomDataTemplateEntity) obj;
            if (customDataTemplateEntity != null) {
                if (customDataTemplateEntity.getData1() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
                    String[] strArr = (String[]) objectRef.element;
                    Data data1 = customDataTemplateEntity.getData1();
                    Intrinsics.checkNotNull(data1);
                    String columnName = data1.getColumnName();
                    Intrinsics.checkNotNull(columnName);
                    objectRef.element = assignmentUpdateListingViewModel.appendString(strArr, columnName);
                }
                if (customDataTemplateEntity.getData2() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = AssignmentUpdateListingViewModel.this;
                    String[] strArr2 = (String[]) objectRef.element;
                    Data data2 = customDataTemplateEntity.getData2();
                    Intrinsics.checkNotNull(data2);
                    String columnName2 = data2.getColumnName();
                    Intrinsics.checkNotNull(columnName2);
                    objectRef.element = assignmentUpdateListingViewModel2.appendString(strArr2, columnName2);
                }
                if (customDataTemplateEntity.getData3() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel3 = AssignmentUpdateListingViewModel.this;
                    String[] strArr3 = (String[]) objectRef.element;
                    Data data3 = customDataTemplateEntity.getData3();
                    Intrinsics.checkNotNull(data3);
                    String columnName3 = data3.getColumnName();
                    Intrinsics.checkNotNull(columnName3);
                    objectRef.element = assignmentUpdateListingViewModel3.appendString(strArr3, columnName3);
                }
                if (customDataTemplateEntity.getData4() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel4 = AssignmentUpdateListingViewModel.this;
                    String[] strArr4 = (String[]) objectRef.element;
                    Data data4 = customDataTemplateEntity.getData4();
                    Intrinsics.checkNotNull(data4);
                    String columnName4 = data4.getColumnName();
                    Intrinsics.checkNotNull(columnName4);
                    objectRef.element = assignmentUpdateListingViewModel4.appendString(strArr4, columnName4);
                }
                if (customDataTemplateEntity.getData5() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel5 = AssignmentUpdateListingViewModel.this;
                    String[] strArr5 = (String[]) objectRef.element;
                    Data data5 = customDataTemplateEntity.getData5();
                    Intrinsics.checkNotNull(data5);
                    String columnName5 = data5.getColumnName();
                    Intrinsics.checkNotNull(columnName5);
                    objectRef.element = assignmentUpdateListingViewModel5.appendString(strArr5, columnName5);
                }
                if (customDataTemplateEntity.getData6() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel6 = AssignmentUpdateListingViewModel.this;
                    String[] strArr6 = (String[]) objectRef.element;
                    Data data6 = customDataTemplateEntity.getData6();
                    Intrinsics.checkNotNull(data6);
                    String columnName6 = data6.getColumnName();
                    Intrinsics.checkNotNull(columnName6);
                    objectRef.element = assignmentUpdateListingViewModel6.appendString(strArr6, columnName6);
                }
                if (customDataTemplateEntity.getData7() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel7 = AssignmentUpdateListingViewModel.this;
                    String[] strArr7 = (String[]) objectRef.element;
                    Data data7 = customDataTemplateEntity.getData7();
                    Intrinsics.checkNotNull(data7);
                    String columnName7 = data7.getColumnName();
                    Intrinsics.checkNotNull(columnName7);
                    objectRef.element = assignmentUpdateListingViewModel7.appendString(strArr7, columnName7);
                }
                if (customDataTemplateEntity.getData8() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel8 = AssignmentUpdateListingViewModel.this;
                    String[] strArr8 = (String[]) objectRef.element;
                    Data data8 = customDataTemplateEntity.getData8();
                    Intrinsics.checkNotNull(data8);
                    String columnName8 = data8.getColumnName();
                    Intrinsics.checkNotNull(columnName8);
                    objectRef.element = assignmentUpdateListingViewModel8.appendString(strArr8, columnName8);
                }
                if (customDataTemplateEntity.getData9() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel9 = AssignmentUpdateListingViewModel.this;
                    String[] strArr9 = (String[]) objectRef.element;
                    Data data9 = customDataTemplateEntity.getData9();
                    Intrinsics.checkNotNull(data9);
                    String columnName9 = data9.getColumnName();
                    Intrinsics.checkNotNull(columnName9);
                    objectRef.element = assignmentUpdateListingViewModel9.appendString(strArr9, columnName9);
                }
                if (customDataTemplateEntity.getData10() != null) {
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel10 = AssignmentUpdateListingViewModel.this;
                    String[] strArr10 = (String[]) objectRef.element;
                    Data data10 = customDataTemplateEntity.getData10();
                    Intrinsics.checkNotNull(data10);
                    String columnName10 = data10.getColumnName();
                    Intrinsics.checkNotNull(columnName10);
                    objectRef.element = assignmentUpdateListingViewModel10.appendString(strArr10, columnName10);
                }
                String dataSort = FileHelper.INSTANCE.readDataSort(AssignmentUpdateListingViewModel.this.getPeriodeId());
                final Ref.IntRef intRef = new Ref.IntRef();
                intRef.element = -1;
                final Ref.IntRef intRef2 = new Ref.IntRef();
                final Ref.IntRef intRef3 = new Ref.IntRef();
                if (dataSort != null) {
                    JsonObject asJsonObject = new JsonParser().parse(dataSort).getAsJsonObject();
                    intRef.element = asJsonObject.get("column").getAsInt();
                    intRef2.element = asJsonObject.get("secara").getAsInt();
                    intRef3.element = asJsonObject.get("sebagai").getAsInt();
                }
                AssignmentListActivity activity = AssignmentUpdateListingViewModel.this.getActivity();
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel11 = AssignmentUpdateListingViewModel.this;
                activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$sorting$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel.C08381.invokeSuspend$lambda$5(assignmentUpdateListingViewModel11, objectRef, intRef2, intRef3, intRef);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invokeSuspend$lambda$5(final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, Ref.ObjectRef objectRef, Ref.IntRef intRef, Ref.IntRef intRef2, final Ref.IntRef intRef3) {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(assignmentUpdateListingViewModel.getActivity());
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_sort_assignment);
            Window window = bottomSheetDialog.getWindow();
            Intrinsics.checkNotNull(window);
            window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            bottomSheetDialog.setCancelable(true);
            Object[] objArr = (Object[]) objectRef.element;
            int length = objArr.length;
            int i = 0;
            final int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                String str = (String) objArr[i];
                RadioButton radioButton = new RadioButton(assignmentUpdateListingViewModel.getActivity());
                radioButton.setText(str);
                radioButton.setId(View.generateViewId());
                radioButton.setTextColor(ContextCompat.getColor(assignmentUpdateListingViewModel.getActivity(), R.color.primary30));
                radioButton.setButtonTintList(new ColorStateList(new int[][]{new int[]{android.R.attr.state_checked}, new int[]{-16842912}}, new int[]{ContextCompat.getColor(assignmentUpdateListingViewModel.getActivity(), R.color.primary30), ContextCompat.getColor(assignmentUpdateListingViewModel.getActivity(), R.color.primary30)}));
                if (i2 == intRef3.element) {
                    radioButton.setChecked(true);
                }
                radioButton.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$sorting$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        intRef3.element = i2;
                    }
                });
                ((RadioGroup) bottomSheetDialog.findViewById(R.id.listColumn_rg)).addView(radioButton);
                i++;
                i2 = i3;
            }
            if (intRef.element == 0) {
                ((RadioButton) bottomSheetDialog.findViewById(R.id.asc_rb)).setChecked(true);
            } else {
                ((RadioButton) bottomSheetDialog.findViewById(R.id.desc_rb)).setChecked(true);
            }
            if (intRef2.element == 0) {
                ((RadioButton) bottomSheetDialog.findViewById(R.id.huruf_rb)).setChecked(true);
            } else {
                ((RadioButton) bottomSheetDialog.findViewById(R.id.angka_rb)).setChecked(true);
            }
            ((Button) bottomSheetDialog.findViewById(R.id.rButton)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$sorting$1$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws IOException {
                    AssignmentUpdateListingViewModel.C08381.invokeSuspend$lambda$5$lambda$2(intRef3, assignmentUpdateListingViewModel, bottomSheetDialog, view);
                }
            });
            ((Button) bottomSheetDialog.findViewById(R.id.lButton)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$sorting$1$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });
            ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialogFilterAssignment)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$sorting$1$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$5$lambda$2(Ref.IntRef intRef, AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, BottomSheetDialog bottomSheetDialog, View view) throws IOException {
            if (intRef.element == -1) {
                Toast.makeText(assignmentUpdateListingViewModel.getActivity(), "Anda belum memilih kolom", 1).show();
                return;
            }
            bottomSheetDialog.dismiss();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("column", Integer.valueOf(intRef.element));
            jsonObject.addProperty("secara", Integer.valueOf(!((RadioButton) bottomSheetDialog.findViewById(R.id.asc_rb)).isChecked() ? 1 : 0));
            jsonObject.addProperty("sebagai", Integer.valueOf(!((RadioButton) bottomSheetDialog.findViewById(R.id.huruf_rb)).isChecked() ? 1 : 0));
            FileHelper.Companion companion = FileHelper.INSTANCE;
            String periodeId = assignmentUpdateListingViewModel.getPeriodeId();
            String string = jsonObject.toString();
            Intrinsics.checkNotNullExpressionValue(string, "jsonObject.toString()");
            companion.writeDataSort(periodeId, string);
            assignmentUpdateListingViewModel.getActivity().finish();
            assignmentUpdateListingViewModel.getActivity().startActivity(assignmentUpdateListingViewModel.getActivity().getIntent());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sorting$lambda$7(AssignmentUpdateListingViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
    }

    public final String[] appendString(String[] arr, String element) {
        Intrinsics.checkNotNullParameter(arr, "arr");
        Intrinsics.checkNotNullParameter(element, "element");
        List mutableList = ArraysKt.toMutableList(arr);
        mutableList.add(element);
        return (String[]) mutableList.toArray(new String[0]);
    }

    public final void ensureRegionSupportDataForAssignment(AssignmentEntity assignment, final Function0<Unit> onReady) {
        Intrinsics.checkNotNullParameter(assignment, "assignment");
        Intrinsics.checkNotNullParameter(onReady, "onReady");
        if (!assignment.isEncrypt()) {
            onReady.invoke();
            return;
        }
        String wrappedDataKey = AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(assignment);
        if (!(wrappedDataKey == null || StringsKt.isBlank(wrappedDataKey))) {
            onReady.invoke();
        } else {
            BaseClassActivityNew.showAlertDialog$default(this.activity, "Metadata Wilayah Belum Diunduh", "Anda perlu mengunduh metadata wilayah terlebih dahulu.", null, "Unduh Sekarang", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.ensureRegionSupportDataForAssignment$lambda$8(this.f$0, onReady, view);
                }
            }, "Kembali", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.ensureRegionSupportDataForAssignment$lambda$9(this.f$0, view);
                }
            }, false, false, 256, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ensureRegionSupportDataForAssignment$lambda$8(AssignmentUpdateListingViewModel this$0, Function0 onReady, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onReady, "$onReady");
        this$0.downloadRegionSupportDataForAssignment(onReady);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ensureRegionSupportDataForAssignment$lambda$9(AssignmentUpdateListingViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    private final void downloadRegionSupportDataForAssignment(final Function0<Unit> onReady) {
        if (this.isDownloadingRegionSupportData) {
            return;
        }
        if (!Network.INSTANCE.isOnline(this.activity)) {
            AssignmentListActivity assignmentListActivity = this.activity;
            int i = R.color.error30;
            int i2 = R.color.error30;
            assignmentListActivity.showAlertDialogColor("Gagal", Integer.valueOf(i), "Tidak dapat mengunduh data wilayah. Periksa koneksi internet lalu coba lagi.", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.downloadRegionSupportDataForAssignment$lambda$10(view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
            return;
        }
        this.isDownloadingRegionSupportData = true;
        this.activity.showProgressBar();
        new RegionRepositoryImpl().getAssignmentRegion(this.periodeId, new Function1<AssignmentRegionResponse, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel.downloadRegionSupportDataForAssignment.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
                invoke2(assignmentRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
                String message;
                boolean z = true;
                if (assignmentRegionResponse != null ? Intrinsics.areEqual((Object) assignmentRegionResponse.getSuccess(), (Object) true) : false) {
                    List<AssignmentRegionEntity> data = assignmentRegionResponse.getData();
                    if (data != null && !data.isEmpty()) {
                        z = false;
                    }
                    if (!z) {
                        final List<AssignmentRegionEntity> listMapIdToAssignmentRegion = AssignmentRegionEntity.INSTANCE.mapIdToAssignmentRegion(assignmentRegionResponse.getData());
                        AssignmentRegionRepository assignmentRegionRepository = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository();
                        String periodeId = AssignmentUpdateListingViewModel.this.getPeriodeId();
                        final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
                        final Function0<Unit> function0 = onReady;
                        assignmentRegionRepository.deleteByPeriode(periodeId, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel.downloadRegionSupportDataForAssignment.2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                AssignmentRegionRepository assignmentRegionRepository2 = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository();
                                List<AssignmentRegionEntity> list = listMapIdToAssignmentRegion;
                                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = assignmentUpdateListingViewModel;
                                final Function0<Unit> function02 = function0;
                                assignmentRegionRepository2.insertAll(list, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel.downloadRegionSupportDataForAssignment.2.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        RegionRepositoryImpl regionRepositoryImpl = new RegionRepositoryImpl();
                                        String periodeId2 = assignmentUpdateListingViewModel2.getPeriodeId();
                                        final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel3 = assignmentUpdateListingViewModel2;
                                        final Function0<Unit> function03 = function02;
                                        regionRepositoryImpl.getRegionMetadata(periodeId2, new Function1<RegionMetadataResponse, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel.downloadRegionSupportDataForAssignment.2.1.1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(RegionMetadataResponse regionMetadataResponse) {
                                                invoke2(regionMetadataResponse);
                                                return Unit.INSTANCE;
                                            }

                                            /* compiled from: AssignmentUpdateListingViewModel.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                            @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$downloadRegionSupportDataForAssignment$2$1$1$1$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                            /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$downloadRegionSupportDataForAssignment$2$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                            static final class C01761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ RegionMetadataResponse $metadataResult;
                                                final /* synthetic */ Function0<Unit> $onReady;
                                                int label;
                                                final /* synthetic */ AssignmentUpdateListingViewModel this$0;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                C01761(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, Function0<Unit> function0, RegionMetadataResponse regionMetadataResponse, Continuation<? super C01761> continuation) {
                                                    super(2, continuation);
                                                    this.this$0 = assignmentUpdateListingViewModel;
                                                    this.$onReady = function0;
                                                    this.$metadataResult = regionMetadataResponse;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new C01761(this.this$0, this.$onReady, this.$metadataResult, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((C01761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                /* compiled from: AssignmentUpdateListingViewModel.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                                @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$downloadRegionSupportDataForAssignment$2$1$1$1$1$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {892}, m = "invokeSuspend", n = {}, s = {})
                                                /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$downloadRegionSupportDataForAssignment$2$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                                static final class C01771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    final /* synthetic */ RegionMetadataResponse $metadataResult;
                                                    int label;
                                                    final /* synthetic */ AssignmentUpdateListingViewModel this$0;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    C01771(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel, RegionMetadataResponse regionMetadataResponse, Continuation<? super C01771> continuation) {
                                                        super(2, continuation);
                                                        this.this$0 = assignmentUpdateListingViewModel;
                                                        this.$metadataResult = regionMetadataResponse;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new C01771(this.this$0, this.$metadataResult, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((C01771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object obj) {
                                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        int i = this.label;
                                                        if (i == 0) {
                                                            ResultKt.throwOnFailure(obj);
                                                            this.label = 1;
                                                            if (DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().updateRegionMetadataByPeriode(this.this$0.getPeriodeId(), this.$metadataResult.getData(), this) == coroutine_suspended) {
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

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object obj) throws InterruptedException {
                                                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    if (this.label == 0) {
                                                        ResultKt.throwOnFailure(obj);
                                                        BuildersKt__BuildersKt.runBlocking$default(null, new C01771(this.this$0, this.$metadataResult, null), 1, null);
                                                        this.this$0.getActivity().hideProgressBar();
                                                        this.this$0.isDownloadingRegionSupportData = false;
                                                        this.$onReady.invoke();
                                                        return Unit.INSTANCE;
                                                    }
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(RegionMetadataResponse regionMetadataResponse) {
                                                String message2;
                                                if ((regionMetadataResponse != null ? Intrinsics.areEqual((Object) regionMetadataResponse.getSuccess(), (Object) true) : false) && regionMetadataResponse.getData() != null) {
                                                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01761(assignmentUpdateListingViewModel3, function03, regionMetadataResponse, null), 2, null);
                                                    return;
                                                }
                                                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel4 = assignmentUpdateListingViewModel3;
                                                if (regionMetadataResponse == null || (message2 = regionMetadataResponse.getMessage()) == null) {
                                                    message2 = "Metadata wilayah gagal diunduh.";
                                                }
                                                assignmentUpdateListingViewModel4.showRegionSupportDownloadError(message2);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        return;
                    }
                }
                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = AssignmentUpdateListingViewModel.this;
                if (assignmentRegionResponse == null || (message = assignmentRegionResponse.getMessage()) == null) {
                    message = "Data wilayah gagal diunduh.";
                }
                assignmentUpdateListingViewModel2.showRegionSupportDownloadError(message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showRegionSupportDownloadError(final String message) {
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentUpdateListingViewModel.showRegionSupportDownloadError$lambda$12(this.f$0, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRegionSupportDownloadError$lambda$12(AssignmentUpdateListingViewModel this$0, String message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(message, "$message");
        this$0.activity.hideProgressBar();
        this$0.isDownloadingRegionSupportData = false;
        AssignmentListActivity assignmentListActivity = this$0.activity;
        int i = R.color.error30;
        int i2 = R.color.error30;
        assignmentListActivity.showAlertDialogColor("Gagal", Integer.valueOf(i), message, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssignmentUpdateListingViewModel.showRegionSupportDownloadError$lambda$12$lambda$11(view);
            }
        }, null, null, null, Integer.valueOf(R.color.error30), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertAssignmentJson(AssignmentEntity assignmentEntity) throws IOException {
        try {
            File file = new File(Directory.INSTANCE.getABSOLUTEPATHENV(), "");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(this.PATH_DATA);
            if (!file2.exists()) {
                try {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        outputStreamWriter.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    CrashHandler.INSTANCE.sendExeption(e2);
                    e2.printStackTrace();
                }
                file2.createNewFile();
            }
            Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file2, null, 1, null), (Class<Object>) AssignmentEntity[].class);
            Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…nmentEntity>::class.java)");
            List mutableList = ArraysKt.toMutableList((Object[]) objFromJson);
            mutableList.add(assignmentEntity);
            String jsonString = new Gson().toJson(mutableList);
            FileHelper.Companion companion = FileHelper.INSTANCE;
            String absolutepathenv = Directory.INSTANCE.getABSOLUTEPATHENV();
            Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
            companion.writeFile(absolutepathenv, "assignment_listing.json", jsonString);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String updatePreDefineData(String strPreDefData, CustomDataTemplateEntity customData, AssignmentEntity originAss) throws JSONException {
        if (strPreDefData == null || customData == null || !StringsKt.startsWith$default(strPreDefData, "{", false, 2, (Object) null)) {
            return strPreDefData;
        }
        JSONObject jSONObject = new JSONObject(strPreDefData);
        Iterator<String> itKeys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "joPreDef.keys()");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Data data1 = customData.getData1();
            if (Intrinsics.areEqual(next, data1 != null ? data1.getDataKey() : null)) {
                jSONObject.put(next, "");
            } else {
                Data data2 = customData.getData2();
                if (Intrinsics.areEqual(next, data2 != null ? data2.getDataKey() : null)) {
                    jSONObject.put(next, "");
                } else {
                    Data data3 = customData.getData3();
                    if (Intrinsics.areEqual(next, data3 != null ? data3.getDataKey() : null)) {
                        jSONObject.put(next, "");
                    } else {
                        Data data4 = customData.getData4();
                        if (Intrinsics.areEqual(next, data4 != null ? data4.getDataKey() : null)) {
                            jSONObject.put(next, "");
                        } else {
                            Data data5 = customData.getData5();
                            if (Intrinsics.areEqual(next, data5 != null ? data5.getDataKey() : null)) {
                                jSONObject.put(next, "");
                            } else {
                                Data data6 = customData.getData6();
                                if (Intrinsics.areEqual(next, data6 != null ? data6.getDataKey() : null)) {
                                    jSONObject.put(next, "");
                                } else {
                                    Data data7 = customData.getData7();
                                    if (Intrinsics.areEqual(next, data7 != null ? data7.getDataKey() : null)) {
                                        jSONObject.put(next, "");
                                    } else {
                                        Data data8 = customData.getData8();
                                        if (Intrinsics.areEqual(next, data8 != null ? data8.getDataKey() : null)) {
                                            jSONObject.put(next, "");
                                        } else {
                                            Data data9 = customData.getData9();
                                            if (Intrinsics.areEqual(next, data9 != null ? data9.getDataKey() : null)) {
                                                jSONObject.put(next, "");
                                            } else {
                                                Data data10 = customData.getData10();
                                                if (Intrinsics.areEqual(next, data10 != null ? data10.getDataKey() : null)) {
                                                    jSONObject.put(next, "");
                                                } else {
                                                    jSONObject.put(next, "");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String blokSensusFullCode = originAss.getBlokSensusFullCode();
        if (blokSensusFullCode != null) {
            if (blokSensusFullCode.length() >= 2) {
                String strSubstring = blokSensusFullCode.substring(0, 2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                jSONObject.put("kode_prov_listing", strSubstring);
            }
            if (blokSensusFullCode.length() >= 4) {
                String strSubstring2 = blokSensusFullCode.substring(2, 4);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                jSONObject.put("kode_kab_listing", strSubstring2);
            }
            if (blokSensusFullCode.length() >= 7) {
                String strSubstring3 = blokSensusFullCode.substring(4, 7);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                jSONObject.put("kode_kec_listing", strSubstring3);
            }
            if (blokSensusFullCode.length() >= 10) {
                String strSubstring4 = blokSensusFullCode.substring(7, 10);
                Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String…ing(startIndex, endIndex)");
                jSONObject.put("kode_desa_listing", strSubstring4);
            }
            if (blokSensusFullCode.length() > 10) {
                String strSubstring5 = blokSensusFullCode.substring(10);
                Intrinsics.checkNotNullExpressionValue(strSubstring5, "this as java.lang.String).substring(startIndex)");
                jSONObject.put("kode_blok_listing", strSubstring5);
            }
        }
        jSONObject.put("nm_prov_listing", originAss.getProvinceName());
        jSONObject.put("nm_kab_listing", originAss.getRegencyName());
        jSONObject.put("nm_kec_listing", originAss.getDistrictName());
        jSONObject.put("nm_desa_listing", originAss.getVillageName());
        jSONObject.put("is_assignment_tambahan", "1");
        return jSONObject.toString();
    }

    public final void getLastBackupTime(Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            File file = new File(Directory.INSTANCE.getABSOLUTEPATHBACKUP() + File.separator + "backup" + CommonCons.INSTANCE.getEXTENSION_JSON());
            if (file.exists()) {
                Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file, null, 1, null), (Class<Object>) BackupModel[].class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…BackupModel>::class.java)");
                callback.invoke(true, ((BackupModel) CollectionsKt.last(ArraysKt.toMutableList((Object[]) objFromJson))).getTime());
            } else {
                callback.invoke(false, null);
            }
        } catch (Exception e) {
            callback.invoke(false, e.getMessage());
            String message = e.getMessage();
            Intrinsics.checkNotNull(message);
            Log.d("hanaapt", message);
        }
    }

    public final void restore(String pathFileOrigin, String surveyId, String periodeId, String assignmentId, Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        FileHelper.Companion companion = FileHelper.INSTANCE;
        if (surveyId == null) {
            surveyId = "";
        }
        if (periodeId == null) {
            periodeId = "";
        }
        if (assignmentId == null) {
            assignmentId = "";
        }
        String strPathAnswerAssignment = companion.pathAnswerAssignment(surveyId, periodeId, assignmentId);
        try {
            File[] fileArrListFiles = new File(pathFileOrigin).listFiles();
            Intrinsics.checkNotNullExpressionValue(fileArrListFiles, "File(pathFileOrigin).listFiles()");
            for (File it : fileArrListFiles) {
                if (new File(strPathAnswerAssignment + InternalZipConstants.ZIP_FILE_SEPARATOR + it.getName()).exists()) {
                    new File(strPathAnswerAssignment + InternalZipConstants.ZIP_FILE_SEPARATOR + it.getName()).delete();
                }
                Intrinsics.checkNotNullExpressionValue(it, "it");
                FilesKt.copyTo$default(it, new File(strPathAnswerAssignment + InternalZipConstants.ZIP_FILE_SEPARATOR + it.getName()), false, 0, 6, null);
            }
            callback.invoke(true, null);
        } catch (IOException e) {
            callback.invoke(false, e.getMessage());
        }
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$getAssignmentRegion$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$getAssignmentRegion$1, reason: invalid class name and case insensitive filesystem */
    static final class C08311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<AssignmentRegionEntity, Unit> $callback;
        final /* synthetic */ String $regionId;
        final /* synthetic */ String $surveyPeriodeId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08311(Function1<? super AssignmentRegionEntity, Unit> function1, String str, String str2, Continuation<? super C08311> continuation) {
            super(2, continuation);
            this.$callback = function1;
            this.$regionId = str;
            this.$surveyPeriodeId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08311(this.$callback, this.$regionId, this.$surveyPeriodeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AssignmentRegionEntity assignmentRegionEntity = (AssignmentRegionEntity) BuildersKt__BuildersKt.runBlocking$default(null, new AssignmentUpdateListingViewModel$getAssignmentRegion$1$assignmentRegion$1(this.$regionId, this.$surveyPeriodeId, null), 1, null);
                if (assignmentRegionEntity == null) {
                    this.$callback.invoke(null);
                } else {
                    this.$callback.invoke(assignmentRegionEntity);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void getAssignmentRegion(String surveyPeriodeId, String regionId, Function1<? super AssignmentRegionEntity, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08311(callback, regionId, surveyPeriodeId, null), 2, null);
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$openAssignment$1", f = "AssignmentUpdateListingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$openAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AssignmentEntity $assignment;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08321(AssignmentEntity assignmentEntity, Continuation<? super C08321> continuation) {
            super(2, continuation);
            this.$assignment = assignmentEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentUpdateListingViewModel.this.new C08321(this.$assignment, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (ActivityCompat.checkSelfPermission(AssignmentUpdateListingViewModel.this.getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(AssignmentUpdateListingViewModel.this.getActivity(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                AssignmentListActivity activity = AssignmentUpdateListingViewModel.this.getActivity();
                final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
                activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$openAssignment$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AssignmentUpdateListingViewModel.C08321.invokeSuspend$lambda$0(assignmentUpdateListingViewModel);
                    }
                });
                return Unit.INSTANCE;
            }
            Task<Location> lastLocation = AssignmentUpdateListingViewModel.this.getFusedLocationClient().getLastLocation();
            final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = AssignmentUpdateListingViewModel.this;
            final AssignmentEntity assignmentEntity = this.$assignment;
            final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel.openAssignment.1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                    invoke2(location);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Location location) {
                    assignmentUpdateListingViewModel2.validateLocation(assignmentEntity, location);
                }
            };
            lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$openAssignment$1$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj2) {
                    function1.invoke(obj2);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(AssignmentUpdateListingViewModel assignmentUpdateListingViewModel) {
            Toast.makeText(assignmentUpdateListingViewModel.getActivity(), "Lokasi GPS belum ditemukan, silakan klik kembali", 0);
        }
    }

    public final void openAssignment(AssignmentEntity assignment) {
        Intrinsics.checkNotNullParameter(assignment, "assignment");
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this.activity);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(activity)");
        setFusedLocationClient(fusedLocationProviderClient);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C08321(assignment, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void validateLocation(AssignmentEntity assignment, Location location) {
        if (assignment != null) {
            assignment.getBlokSensusFullCode();
        }
        if (assignment == null) {
            Toast.makeText(getApplication(), "Assignment tidak ditemukan..", 1).show();
        } else if (location == null) {
            Toast.makeText(getApplication(), "Lokasi GPS belum ditemukan, silakan klik kembali", 1).show();
        } else {
            this.userLatLng.postValue(new LatLng(location.getLatitude(), location.getLongitude()));
            this.isCanOpen.postValue(true);
        }
    }

    public final void setDeleteAssignment() {
        Boolean value = this.isAssignmentDeleted.getValue();
        Intrinsics.checkNotNull(value);
        if (value.booleanValue()) {
            this.isAssignmentDeleted.postValue(false);
        } else {
            this.isAssignmentDeleted.postValue(true);
        }
    }

    public final void assignAssignment(String assignmentId) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("assign_petugas")) {
            this.activity.showProgressBar();
            new SurveyRepositoryImpl().getSurveyRole(this.surveyId, new C08291((PeriodeEntityNew) BuildersKt__BuildersKt.runBlocking$default(null, new AssignmentUpdateListingViewModel$assignAssignment$periode$1(this, null), 1, null), assignmentId));
            return;
        }
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentUpdateListingViewModel.assignAssignment$lambda$19(this.f$0);
            }
        });
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/data/remote/dto/SurveyRolesResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$assignAssignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C08291 extends Lambda implements Function1<SurveyRolesResponse, Unit> {
        final /* synthetic */ String $assignmentId;
        final /* synthetic */ PeriodeEntityNew $periode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08291(PeriodeEntityNew periodeEntityNew, String str) {
            super(1);
            this.$periode = periodeEntityNew;
            this.$assignmentId = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$4$lambda$2(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$4$lambda$3(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$6$lambda$5(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SurveyRolesResponse surveyRolesResponse) {
            invoke2(surveyRolesResponse);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00ba  */
        /* JADX WARN: Type inference failed for: r2v14, types: [T, java.util.List] */
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke2(id.go.bpsfasih.data.remote.dto.SurveyRolesResponse r21) {
            /*
                Method dump skipped, instructions count: 481
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel.C08291.invoke2(id.go.bpsfasih.data.remote.dto.SurveyRolesResponse):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignAssignment$lambda$19(AssignmentUpdateListingViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "listUser", "", "Lid/go/bpsfasih/data/local/entities/UserRolesEntity;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$setUserPetugas$1, reason: invalid class name and case insensitive filesystem */
    static final class C08361 extends Lambda implements Function1<List<? extends UserRolesEntity>, Unit> {
        final /* synthetic */ Function1<List<String>, Unit> $callback;
        final /* synthetic */ List<SurveyRolesEntity> $listRole;
        final /* synthetic */ String $smallRegionFullCode;
        final /* synthetic */ List<String> $surveyPeriodRoleUserIds;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08361(List<SurveyRolesEntity> list, List<String> list2, String str, Function1<? super List<String>, Unit> function1) {
            super(1);
            this.$listRole = list;
            this.$surveyPeriodRoleUserIds = list2;
            this.$smallRegionFullCode = str;
            this.$callback = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends UserRolesEntity> list) {
            invoke2((List<UserRolesEntity>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final List<UserRolesEntity> listUser) {
            Intrinsics.checkNotNullParameter(listUser, "listUser");
            AssignmentListActivity activity = AssignmentUpdateListingViewModel.this.getActivity();
            final AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
            final List<SurveyRolesEntity> list = this.$listRole;
            final List<String> list2 = this.$surveyPeriodRoleUserIds;
            final String str = this.$smallRegionFullCode;
            final Function1<List<String>, Unit> function1 = this.$callback;
            activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$setUserPetugas$1$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentUpdateListingViewModel.C08361.invoke$lambda$6(assignmentUpdateListingViewModel, list, listUser, list2, str, function1);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$6(final AssignmentUpdateListingViewModel this$0, final List listRole, final List listUser, final List surveyPeriodRoleUserIds, final String smallRegionFullCode, final Function1 callback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(listRole, "$listRole");
            Intrinsics.checkNotNullParameter(listUser, "$listUser");
            Intrinsics.checkNotNullParameter(surveyPeriodRoleUserIds, "$surveyPeriodRoleUserIds");
            Intrinsics.checkNotNullParameter(smallRegionFullCode, "$smallRegionFullCode");
            Intrinsics.checkNotNullParameter(callback, "$callback");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this$0.getActivity());
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_assign_petugas);
            Window window = bottomSheetDialog.getWindow();
            Intrinsics.checkNotNull(window);
            window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            bottomSheetDialog.setCancelable(true);
            ((TextView) bottomSheetDialog.findViewById(R.id.title_bd)).setText("Pilih petugas sebagai " + ((SurveyRolesEntity) CollectionsKt.first(listRole)).getDescription());
            Iterator it = listUser.iterator();
            while (it.hasNext()) {
                UserRolesEntity userRolesEntity = (UserRolesEntity) it.next();
                RadioButton radioButton = new RadioButton(this$0.getActivity());
                radioButton.setText(userRolesEntity.getUsername());
                RadioButton radioButton2 = radioButton;
                radioButton2.setPadding(10, 10, 10, 10);
                radioButton.setButtonTintList(ContextCompat.getColorStateList(this$0.getActivity(), R.color.button_primary));
                radioButton.setTextColor(ContextCompat.getColorStateList(this$0.getActivity(), R.color.primary30));
                radioButton.setTextSize(12.0f);
                ((RadioGroup) bottomSheetDialog.findViewById(R.id.petugas_rg_bd)).addView(radioButton2);
            }
            ((RadioGroup) bottomSheetDialog.findViewById(R.id.petugas_rg_bd)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$setUserPetugas$1$$ExternalSyntheticLambda0
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                    AssignmentUpdateListingViewModel.C08361.invoke$lambda$6$lambda$1(objectRef, bottomSheetDialog, radioGroup, i);
                }
            });
            ((Button) bottomSheetDialog.findViewById(R.id.rButton_bd)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$setUserPetugas$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.C08361.invoke$lambda$6$lambda$3(objectRef, this$0, bottomSheetDialog, listUser, surveyPeriodRoleUserIds, listRole, smallRegionFullCode, callback, view);
                }
            });
            ((Button) bottomSheetDialog.findViewById(R.id.lButton_bd)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$setUserPetugas$1$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.C08361.invoke$lambda$6$lambda$4(bottomSheetDialog, this$0, view);
                }
            });
            ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_bd)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$setUserPetugas$1$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AssignmentUpdateListingViewModel.C08361.invoke$lambda$6$lambda$5(bottomSheetDialog, this$0, view);
                }
            });
            bottomSheetDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r2v1, types: [T, android.view.View] */
        public static final void invoke$lambda$6$lambda$1(Ref.ObjectRef selectedPetugas, BottomSheetDialog dialog, RadioGroup radioGroup, int i) {
            Intrinsics.checkNotNullParameter(selectedPetugas, "$selectedPetugas");
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            Intrinsics.checkNotNullParameter(radioGroup, "radioGroup");
            selectedPetugas.element = dialog.findViewById(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$6$lambda$3(Ref.ObjectRef selectedPetugas, AssignmentUpdateListingViewModel this$0, BottomSheetDialog dialog, List listUser, List surveyPeriodRoleUserIds, List listRole, String smallRegionFullCode, Function1 callback, View view) {
            Intrinsics.checkNotNullParameter(selectedPetugas, "$selectedPetugas");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            Intrinsics.checkNotNullParameter(listUser, "$listUser");
            Intrinsics.checkNotNullParameter(surveyPeriodRoleUserIds, "$surveyPeriodRoleUserIds");
            Intrinsics.checkNotNullParameter(listRole, "$listRole");
            Intrinsics.checkNotNullParameter(smallRegionFullCode, "$smallRegionFullCode");
            Intrinsics.checkNotNullParameter(callback, "$callback");
            if (selectedPetugas.element == 0) {
                Toast.makeText(this$0.getActivity(), "Anda harus memilih salah satu petugas", 1).show();
                return;
            }
            dialog.dismiss();
            ArrayList arrayList = new ArrayList();
            for (Object obj : listUser) {
                String username = ((UserRolesEntity) obj).getUsername();
                Intrinsics.checkNotNull(username);
                T t = selectedPetugas.element;
                Intrinsics.checkNotNull(t);
                if (username.equals(((RadioButton) t).getText())) {
                    arrayList.add(obj);
                }
            }
            UserRolesEntity userRolesEntity = (UserRolesEntity) CollectionsKt.first((List) arrayList);
            String id2 = userRolesEntity.getId();
            Intrinsics.checkNotNull(id2);
            surveyPeriodRoleUserIds.add(id2);
            listRole.remove(0);
            if (listRole.size() > 0) {
                String userId = userRolesEntity.getUserId();
                Intrinsics.checkNotNull(userId);
                this$0.setUserPetugas(listRole, userId, smallRegionFullCode, surveyPeriodRoleUserIds, callback);
                return;
            }
            callback.invoke(surveyPeriodRoleUserIds);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$6$lambda$4(BottomSheetDialog dialog, AssignmentUpdateListingViewModel this$0, View view) {
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            dialog.dismiss();
            this$0.getActivity().hideProgressBar();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$6$lambda$5(BottomSheetDialog dialog, AssignmentUpdateListingViewModel this$0, View view) {
            Intrinsics.checkNotNullParameter(dialog, "$dialog");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            dialog.dismiss();
            this$0.getActivity().hideProgressBar();
        }
    }

    public final void setUserPetugas(List<SurveyRolesEntity> listRole, String parentUser, String smallRegionFullCode, List<String> surveyPeriodRoleUserIds, Function1<? super List<String>, Unit> callback) {
        Intrinsics.checkNotNullParameter(listRole, "listRole");
        Intrinsics.checkNotNullParameter(parentUser, "parentUser");
        Intrinsics.checkNotNullParameter(smallRegionFullCode, "smallRegionFullCode");
        Intrinsics.checkNotNullParameter(surveyPeriodRoleUserIds, "surveyPeriodRoleUserIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        requestUserPetugas((SurveyRolesEntity) CollectionsKt.first((List) listRole), parentUser, smallRegionFullCode, new C08361(listRole, surveyPeriodRoleUserIds, smallRegionFullCode, callback));
    }

    /* compiled from: AssignmentUpdateListingViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lid/go/bpsfasih/data/remote/dto/UserRolesResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$requestUserPetugas$1, reason: invalid class name and case insensitive filesystem */
    static final class C08351 extends Lambda implements Function1<UserRolesResponse, Unit> {
        final /* synthetic */ Function1<List<UserRolesEntity>, Unit> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08351(Function1<? super List<UserRolesEntity>, Unit> function1) {
            super(1);
            this.$callback = function1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1$lambda$0(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$3$lambda$2(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UserRolesResponse userRolesResponse) {
            invoke2(userRolesResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(UserRolesResponse userRolesResponse) {
            Unit unit;
            if (userRolesResponse != null) {
                Function1<List<UserRolesEntity>, Unit> function1 = this.$callback;
                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = AssignmentUpdateListingViewModel.this;
                if (Intrinsics.areEqual((Object) userRolesResponse.getSuccess(), (Object) true)) {
                    function1.invoke(userRolesResponse.getData());
                } else {
                    assignmentUpdateListingViewModel.getActivity().hideProgressBar();
                    AssignmentListActivity activity = assignmentUpdateListingViewModel.getActivity();
                    int i = R.color.error30;
                    activity.showAlertDialogColor("Gagal", Integer.valueOf(i), "Terdapat kesalahan : " + userRolesResponse.getMessage(), Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$requestUserPetugas$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AssignmentUpdateListingViewModel.C08351.invoke$lambda$1$lambda$0(view);
                        }
                    }, null, null, null, Integer.valueOf(R.color.error30), true);
                }
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = AssignmentUpdateListingViewModel.this;
                assignmentUpdateListingViewModel2.getActivity().hideProgressBar();
                assignmentUpdateListingViewModel2.getActivity().showAlertDialogColor("Gagal", Integer.valueOf(R.color.error30), "Terdapat kesalahan ketika mengambil daftar petugas", Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentUpdateListingViewModel$requestUserPetugas$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AssignmentUpdateListingViewModel.C08351.invoke$lambda$3$lambda$2(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
            }
        }
    }

    public final void requestUserPetugas(SurveyRolesEntity role, String parentUser, String smallRegionFullCode, Function1<? super List<UserRolesEntity>, Unit> callback) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(parentUser, "parentUser");
        Intrinsics.checkNotNullParameter(smallRegionFullCode, "smallRegionFullCode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        SurveyRepositoryImpl surveyRepositoryImpl = new SurveyRepositoryImpl();
        String str = this.periodeId;
        String id2 = role.getId();
        Intrinsics.checkNotNull(id2);
        surveyRepositoryImpl.getListUserByPeriodRoleRegion(str, id2, parentUser, smallRegionFullCode, new C08351(callback));
    }
}

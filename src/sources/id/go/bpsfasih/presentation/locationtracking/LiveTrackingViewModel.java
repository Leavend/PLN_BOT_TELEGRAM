package id.go.bpsfasih.presentation.locationtracking;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase;
import id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl;
import id.go.bpsfasih.data.repository.TrackingRepositoryImpl;
import id.go.bpsfasih.domain.model.LocationTracking;
import id.go.bpsfasih.domain.repository.LocationTrackingRepository;
import id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase;
import id.go.bpsfasih.presentation.locationtracking.LocationTrackingUiState;
import id.go.bpsfasih.utils.GpsStatusHelper;
import id.go.bpsfasih.utils.LiveTrackingHelper;
import id.go.bpsfasih.utils.LocationHelper;
import id.go.bpsfasih.utils.LocationTrackingManager;
import id.go.bpsfasih.utils.TrackingPreferences;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import io.grpc.internal.GrpcUtil;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: LiveTrackingViewModel.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u000fH\u0002J\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u000200J\u0010\u00102\u001a\u00020,2\b\b\u0002\u00103\u001a\u00020\u000fJ\u0006\u00104\u001a\u00020,J\b\u00105\u001a\u00020,H\u0002J\u0006\u00106\u001a\u00020,J\u000e\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020\u0007J\u0006\u00109\u001a\u00020,R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u001d\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0016¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018¨\u0006:"}, d2 = {"Lid/go/bpsfasih/presentation/locationtracking/LiveTrackingViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_hasLocationPermission", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isGpsEnabled", "_isSendingAllData", "_isTracking", "_locations", "", "Lid/go/bpsfasih/domain/model/LocationTracking;", "_sendAllDataMessage", "", "_uiState", "Lid/go/bpsfasih/presentation/locationtracking/LocationTrackingUiState;", "_unsentLocations", "_unsentTrackingCount", "", "hasLocationPermission", "Lkotlinx/coroutines/flow/StateFlow;", "getHasLocationPermission", "()Lkotlinx/coroutines/flow/StateFlow;", "isGpsEnabled", "isLoading", "isSendingAllData", "isTracking", "locations", "getLocations", "repository", "Lid/go/bpsfasih/domain/repository/LocationTrackingRepository;", "sendAllDataMessage", "getSendAllDataMessage", "syncLocationTrackingUseCase", "Lid/go/bpsfasih/domain/usecase/SyncLocationTrackingUseCase;", "uiState", "getUiState", "unsentLocations", "getUnsentLocations", "unsentTrackingCount", "getUnsentTrackingCount", "disableTracking", "", "message", "getCurrentDate", "getSaveIntervalSeconds", "", "getSendIntervalSeconds", "loadLocations", "date", "refreshTrackingStatusFromDevice", "refreshUnsentTrackingCount", "sendAllTrackingDataInBatches", "setTrackingEnabled", "enabled", "stopTracking", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LiveTrackingViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<Boolean> _hasLocationPermission;
    private final MutableStateFlow<Boolean> _isGpsEnabled;
    private final MutableStateFlow<Boolean> _isSendingAllData;
    private final MutableStateFlow<Boolean> _isTracking;
    private final MutableStateFlow<List<LocationTracking>> _locations;
    private final MutableStateFlow<String> _sendAllDataMessage;
    private final MutableStateFlow<LocationTrackingUiState> _uiState;
    private final MutableStateFlow<List<LocationTracking>> _unsentLocations;
    private final MutableStateFlow<Integer> _unsentTrackingCount;
    private final StateFlow<Boolean> hasLocationPermission;
    private final StateFlow<Boolean> isGpsEnabled;
    private boolean isLoading;
    private final StateFlow<Boolean> isSendingAllData;
    private final StateFlow<Boolean> isTracking;
    private final StateFlow<List<LocationTracking>> locations;
    private final LocationTrackingRepository repository;
    private final StateFlow<String> sendAllDataMessage;
    private final SyncLocationTrackingUseCase syncLocationTrackingUseCase;
    private final StateFlow<LocationTrackingUiState> uiState;
    private final StateFlow<List<LocationTracking>> unsentLocations;
    private final StateFlow<Integer> unsentTrackingCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTrackingViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        MutableStateFlow<LocationTrackingUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(LocationTrackingUiState.Idle.INSTANCE);
        this._uiState = MutableStateFlow;
        this.uiState = MutableStateFlow;
        MutableStateFlow<List<LocationTracking>> MutableStateFlow2 = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._locations = MutableStateFlow2;
        this.locations = MutableStateFlow2;
        MutableStateFlow<Boolean> MutableStateFlow3 = StateFlowKt.MutableStateFlow(false);
        this._isTracking = MutableStateFlow3;
        this.isTracking = MutableStateFlow3;
        MutableStateFlow<Boolean> MutableStateFlow4 = StateFlowKt.MutableStateFlow(false);
        this._isGpsEnabled = MutableStateFlow4;
        this.isGpsEnabled = MutableStateFlow4;
        MutableStateFlow<Boolean> MutableStateFlow5 = StateFlowKt.MutableStateFlow(false);
        this._hasLocationPermission = MutableStateFlow5;
        this.hasLocationPermission = MutableStateFlow5;
        MutableStateFlow<Integer> MutableStateFlow6 = StateFlowKt.MutableStateFlow(0);
        this._unsentTrackingCount = MutableStateFlow6;
        this.unsentTrackingCount = MutableStateFlow6;
        MutableStateFlow<List<LocationTracking>> MutableStateFlow7 = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._unsentLocations = MutableStateFlow7;
        this.unsentLocations = MutableStateFlow7;
        MutableStateFlow<Boolean> MutableStateFlow8 = StateFlowKt.MutableStateFlow(false);
        this._isSendingAllData = MutableStateFlow8;
        this.isSendingAllData = MutableStateFlow8;
        MutableStateFlow<String> MutableStateFlow9 = StateFlowKt.MutableStateFlow(null);
        this._sendAllDataMessage = MutableStateFlow9;
        this.sendAllDataMessage = MutableStateFlow9;
        LocationTrackingRepositoryImpl locationTrackingRepositoryImpl = new LocationTrackingRepositoryImpl(new LocationTrackingLocalDataSource(SurveyRoomDatabase.INSTANCE.getDatabase(application).locationTrackingDAO()));
        this.repository = locationTrackingRepositoryImpl;
        this.syncLocationTrackingUseCase = new SyncLocationTrackingUseCase(locationTrackingRepositoryImpl, new TrackingRepositoryImpl(null, 1, null), new AnonymousClass1(null));
        refreshTrackingStatusFromDevice();
    }

    public final StateFlow<LocationTrackingUiState> getUiState() {
        return this.uiState;
    }

    public final StateFlow<List<LocationTracking>> getLocations() {
        return this.locations;
    }

    public final StateFlow<Boolean> isTracking() {
        return this.isTracking;
    }

    public final StateFlow<Boolean> isGpsEnabled() {
        return this.isGpsEnabled;
    }

    public final StateFlow<Boolean> getHasLocationPermission() {
        return this.hasLocationPermission;
    }

    public final StateFlow<Integer> getUnsentTrackingCount() {
        return this.unsentTrackingCount;
    }

    public final StateFlow<List<LocationTracking>> getUnsentLocations() {
        return this.unsentLocations;
    }

    public final StateFlow<Boolean> isSendingAllData() {
        return this.isSendingAllData;
    }

    public final StateFlow<String> getSendAllDataMessage() {
        return this.sendAllDataMessage;
    }

    /* compiled from: LiveTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "", "assignmentId"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$1", f = "LiveTrackingViewModel.kt", i = {}, l = {GrpcUtil.DEFAULT_PORT_PLAINTEXT}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<String, Continuation<? super String>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(String str, Continuation<? super String> continuation) {
            return ((AnonymousClass1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String str = (String) this.L$0;
                String str2 = str;
                if (str2 == null || str2.length() == 0) {
                    return null;
                }
                this.label = 1;
                obj = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentOnly(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AssignmentEntity assignmentEntity = (AssignmentEntity) obj;
            if (assignmentEntity != null) {
                return assignmentEntity.getPeriodeNotPrimary();
            }
            return null;
        }
    }

    public final void refreshTrackingStatusFromDevice() {
        TrackingPreferences trackingPreferences = new TrackingPreferences();
        Application application = getApplication();
        boolean zIsGpsEnabled = GpsStatusHelper.INSTANCE.isGpsEnabled(application);
        boolean zHasLocationPermission = LocationHelper.INSTANCE.hasLocationPermission(application);
        boolean zIsTrackingActive = trackingPreferences.isTrackingActive(application);
        this._isGpsEnabled.setValue(Boolean.valueOf(zIsGpsEnabled));
        this._hasLocationPermission.setValue(Boolean.valueOf(zHasLocationPermission));
        if (!zHasLocationPermission && zIsTrackingActive) {
            disableTracking("Live tracking dinonaktifkan karena izin lokasi tidak tersedia.");
        } else if (!zIsGpsEnabled && zIsTrackingActive) {
            disableTracking("Live tracking dinonaktifkan karena GPS perangkat sedang nonaktif.");
        } else {
            this._isTracking.setValue(Boolean.valueOf(zIsTrackingActive && zIsGpsEnabled && zHasLocationPermission));
        }
        refreshUnsentTrackingCount();
    }

    /* compiled from: LiveTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$stopTracking$1", f = "LiveTrackingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$stopTracking$1, reason: invalid class name and case insensitive filesystem */
    static final class C08211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08211(Continuation<? super C08211> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LiveTrackingViewModel.this.new C08211(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                LocationTrackingManager.INSTANCE.stopTracking();
                new TrackingPreferences().setTrackingActive(LiveTrackingViewModel.this.getApplication(), false);
                LiveTrackingViewModel.this._isTracking.setValue(Boxing.boxBoolean(false));
                LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Success("Tracking stopped successfully"));
            } catch (Exception e) {
                LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Failed to stop tracking: " + e.getMessage()));
            }
            return Unit.INSTANCE;
        }
    }

    public final void stopTracking() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08211(null), 3, null);
    }

    /* compiled from: LiveTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$setTrackingEnabled$1", f = "LiveTrackingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$setTrackingEnabled$1, reason: invalid class name and case insensitive filesystem */
    static final class C08201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08201(boolean z, Continuation<? super C08201> continuation) {
            super(2, continuation);
            this.$enabled = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LiveTrackingViewModel.this.new C08201(this.$enabled, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Application application = LiveTrackingViewModel.this.getApplication();
                String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ID());
                if (sessionString == null) {
                    sessionString = "";
                }
                String str = sessionString;
                if (this.$enabled) {
                    if (!LocationHelper.INSTANCE.hasLocationPermission(application)) {
                        LiveTrackingViewModel.this._hasLocationPermission.setValue(Boxing.boxBoolean(false));
                        LiveTrackingViewModel.this._isTracking.setValue(Boxing.boxBoolean(false));
                        LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Izin lokasi diperlukan untuk menyalakan live tracking."));
                        return Unit.INSTANCE;
                    }
                    if (!GpsStatusHelper.INSTANCE.isGpsEnabled(application)) {
                        LiveTrackingViewModel.this._isGpsEnabled.setValue(Boxing.boxBoolean(false));
                        LiveTrackingViewModel.this._isTracking.setValue(Boxing.boxBoolean(false));
                        LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Aktifkan GPS terlebih dahulu untuk menyalakan live tracking."));
                        return Unit.INSTANCE;
                    }
                    if (!(str.length() > 0)) {
                        LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("User ID tidak ditemukan"));
                    } else {
                        LiveTrackingHelper.startTracking$default(LiveTrackingHelper.INSTANCE, application, str, null, 4, null);
                        LiveTrackingViewModel.this._isTracking.setValue(Boxing.boxBoolean(true));
                        LiveTrackingViewModel.this._isGpsEnabled.setValue(Boxing.boxBoolean(true));
                        LiveTrackingViewModel.this._uiState.setValue(LocationTrackingUiState.Idle.INSTANCE);
                        LiveTrackingViewModel.this.refreshUnsentTrackingCount();
                    }
                } else {
                    LiveTrackingHelper.INSTANCE.stopTrackingPermanent(application);
                    LiveTrackingViewModel.this._isTracking.setValue(Boxing.boxBoolean(false));
                    LiveTrackingViewModel.this._uiState.setValue(LocationTrackingUiState.Idle.INSTANCE);
                    LiveTrackingViewModel.this.refreshUnsentTrackingCount();
                }
            } catch (Exception e) {
                LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Gagal mengubah status tracking: " + e.getMessage()));
            }
            return Unit.INSTANCE;
        }
    }

    public final void setTrackingEnabled(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08201(enabled, null), 3, null);
    }

    private final void disableTracking(String message) {
        LiveTrackingHelper.INSTANCE.stopTrackingPermanent(getApplication());
        LocationTrackingManager.INSTANCE.stopTracking();
        this._isTracking.setValue(false);
        this._uiState.setValue(new LocationTrackingUiState.Error(message));
    }

    public static /* synthetic */ void loadLocations$default(LiveTrackingViewModel liveTrackingViewModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = liveTrackingViewModel.getCurrentDate();
        }
        liveTrackingViewModel.loadLocations(str);
    }

    public final void loadLocations(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        if (this.isLoading) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08171(date, null), 3, null);
    }

    /* compiled from: LiveTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$loadLocations$1", f = "LiveTrackingViewModel.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$loadLocations$1, reason: invalid class name and case insensitive filesystem */
    static final class C08171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $date;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08171(String str, Continuation<? super C08171> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LiveTrackingViewModel.this.new C08171(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        LiveTrackingViewModel.this.isLoading = true;
                        LiveTrackingViewModel.this._uiState.setValue(LocationTrackingUiState.Loading.INSTANCE);
                        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ID());
                        if (sessionString == null) {
                            sessionString = "";
                        }
                        this.label = 1;
                        obj = LiveTrackingViewModel.this.repository.getLocationsByUserAndDate(sessionString, this.$date, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    List list = (List) obj;
                    LiveTrackingViewModel.this._locations.setValue(list);
                    LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Success("Loaded " + list.size() + " locations"));
                } catch (Exception e) {
                    LiveTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Failed to load locations: " + e.getMessage()));
                }
                LiveTrackingViewModel.this.isLoading = false;
                return Unit.INSTANCE;
            } catch (Throwable th) {
                LiveTrackingViewModel.this.isLoading = false;
                throw th;
            }
        }
    }

    private final String getCurrentDate() {
        String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "dateFormat.format(Date())");
        return str;
    }

    public final long getSaveIntervalSeconds() {
        return LocationTrackingManager.INSTANCE.getSaveIntervalSeconds();
    }

    public final long getSendIntervalSeconds() {
        return LocationTrackingManager.INSTANCE.getSyncIntervalSeconds();
    }

    /* compiled from: LiveTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$refreshUnsentTrackingCount$1", f = "LiveTrackingViewModel.kt", i = {}, l = {228}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$refreshUnsentTrackingCount$1, reason: invalid class name and case insensitive filesystem */
    static final class C08181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08181(Continuation<? super C08181> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LiveTrackingViewModel.this.new C08181(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = LiveTrackingViewModel.this.repository.getUnsyncedLocations(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                List list = (List) obj;
                LiveTrackingViewModel.this._unsentTrackingCount.setValue(Boxing.boxInt(list.size()));
                LiveTrackingViewModel.this._unsentLocations.setValue(CollectionsKt.sortedWith(list, new Comparator() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$refreshUnsentTrackingCount$1$invokeSuspend$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Long.valueOf(((LocationTracking) t).getTimestamp()), Long.valueOf(((LocationTracking) t2).getTimestamp()));
                    }
                }));
            } catch (Exception unused) {
                LiveTrackingViewModel.this._unsentTrackingCount.setValue(Boxing.boxInt(0));
                LiveTrackingViewModel.this._unsentLocations.setValue(CollectionsKt.emptyList());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshUnsentTrackingCount() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08181(null), 3, null);
    }

    public final void sendAllTrackingDataInBatches() {
        if (this._isSendingAllData.getValue().booleanValue()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08191(null), 3, null);
    }

    /* compiled from: LiveTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$sendAllTrackingDataInBatches$1", f = "LiveTrackingViewModel.kt", i = {0}, l = {248}, m = "invokeSuspend", n = {"totalSynced"}, s = {"I$0"})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel$sendAllTrackingDataInBatches$1, reason: invalid class name and case insensitive filesystem */
    static final class C08191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        int label;

        C08191(Continuation<? super C08191> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LiveTrackingViewModel.this.new C08191(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:17:0x005d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0069 A[Catch: Exception -> 0x00b5, all -> 0x00f5, TryCatch #2 {all -> 0x00f5, blocks: (B:19:0x0063, B:21:0x0069, B:23:0x006f, B:24:0x0078, B:25:0x0079, B:28:0x0084, B:30:0x008c, B:39:0x00c0, B:31:0x0092), top: B:45:0x0008 }] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0079 A[Catch: Exception -> 0x00b5, all -> 0x00f5, TryCatch #2 {all -> 0x00f5, blocks: (B:19:0x0063, B:21:0x0069, B:23:0x006f, B:24:0x0078, B:25:0x0079, B:28:0x0084, B:30:0x008c, B:39:0x00c0, B:31:0x0092), top: B:45:0x0008 }] */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v14 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x005e -> B:46:0x0063). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 265
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.presentation.locationtracking.LiveTrackingViewModel.C08191.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}

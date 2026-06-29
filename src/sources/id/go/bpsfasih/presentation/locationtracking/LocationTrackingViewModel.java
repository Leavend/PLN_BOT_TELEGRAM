package id.go.bpsfasih.presentation.locationtracking;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import id.go.bpsfasih.domain.model.LocationTracking;
import id.go.bpsfasih.domain.usecase.CheckTrackingStatusUseCase;
import id.go.bpsfasih.domain.usecase.GetLocationsByAssignmentUseCase;
import id.go.bpsfasih.domain.usecase.GetTodayLocationsUseCase;
import id.go.bpsfasih.domain.usecase.SaveLocationUseCase;
import id.go.bpsfasih.domain.usecase.StartTrackingUseCase;
import id.go.bpsfasih.domain.usecase.StopTrackingUseCase;
import id.go.bpsfasih.presentation.locationtracking.LocationTrackingUiState;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: LocationTrackingViewModel.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u001fJ\u0006\u0010\"\u001a\u00020\u001bJ\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001fJA\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010$\u001a\u00020\u001f¢\u0006\u0002\u0010,J\u001e\u0010-\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010.\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017¨\u0006/"}, d2 = {"Lid/go/bpsfasih/presentation/locationtracking/LocationTrackingViewModel;", "Landroidx/lifecycle/ViewModel;", "startTrackingUseCase", "Lid/go/bpsfasih/domain/usecase/StartTrackingUseCase;", "stopTrackingUseCase", "Lid/go/bpsfasih/domain/usecase/StopTrackingUseCase;", "saveLocationUseCase", "Lid/go/bpsfasih/domain/usecase/SaveLocationUseCase;", "checkTrackingStatusUseCase", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase;", "getTodayLocationsUseCase", "Lid/go/bpsfasih/domain/usecase/GetTodayLocationsUseCase;", "getLocationsByAssignmentUseCase", "Lid/go/bpsfasih/domain/usecase/GetLocationsByAssignmentUseCase;", "(Lid/go/bpsfasih/domain/usecase/StartTrackingUseCase;Lid/go/bpsfasih/domain/usecase/StopTrackingUseCase;Lid/go/bpsfasih/domain/usecase/SaveLocationUseCase;Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase;Lid/go/bpsfasih/domain/usecase/GetTodayLocationsUseCase;Lid/go/bpsfasih/domain/usecase/GetLocationsByAssignmentUseCase;)V", "_dialogState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lid/go/bpsfasih/presentation/locationtracking/TrackingDialogState;", "_uiState", "Lid/go/bpsfasih/presentation/locationtracking/LocationTrackingUiState;", "dialogState", "Lkotlinx/coroutines/flow/StateFlow;", "getDialogState", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "checkShouldShowDialog", "", "context", "Landroid/content/Context;", "currentDate", "", "getLocationsByAssignment", "assignmentId", "hideDialog", "observeTodayLocations", "date", "saveCurrentLocation", "userId", "latitude", "", "longitude", "accuracy", "", "(Ljava/lang/String;Ljava/lang/String;DDLjava/lang/Float;Ljava/lang/String;)V", "startTracking", "stopTracking", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationTrackingViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<TrackingDialogState> _dialogState;
    private final MutableStateFlow<LocationTrackingUiState> _uiState;
    private final CheckTrackingStatusUseCase checkTrackingStatusUseCase;
    private final StateFlow<TrackingDialogState> dialogState;
    private final GetLocationsByAssignmentUseCase getLocationsByAssignmentUseCase;
    private final GetTodayLocationsUseCase getTodayLocationsUseCase;
    private final SaveLocationUseCase saveLocationUseCase;
    private final StartTrackingUseCase startTrackingUseCase;
    private final StopTrackingUseCase stopTrackingUseCase;
    private final StateFlow<LocationTrackingUiState> uiState;

    public LocationTrackingViewModel(StartTrackingUseCase startTrackingUseCase, StopTrackingUseCase stopTrackingUseCase, SaveLocationUseCase saveLocationUseCase, CheckTrackingStatusUseCase checkTrackingStatusUseCase, GetTodayLocationsUseCase getTodayLocationsUseCase, GetLocationsByAssignmentUseCase getLocationsByAssignmentUseCase) {
        Intrinsics.checkNotNullParameter(startTrackingUseCase, "startTrackingUseCase");
        Intrinsics.checkNotNullParameter(stopTrackingUseCase, "stopTrackingUseCase");
        Intrinsics.checkNotNullParameter(saveLocationUseCase, "saveLocationUseCase");
        Intrinsics.checkNotNullParameter(checkTrackingStatusUseCase, "checkTrackingStatusUseCase");
        Intrinsics.checkNotNullParameter(getTodayLocationsUseCase, "getTodayLocationsUseCase");
        Intrinsics.checkNotNullParameter(getLocationsByAssignmentUseCase, "getLocationsByAssignmentUseCase");
        this.startTrackingUseCase = startTrackingUseCase;
        this.stopTrackingUseCase = stopTrackingUseCase;
        this.saveLocationUseCase = saveLocationUseCase;
        this.checkTrackingStatusUseCase = checkTrackingStatusUseCase;
        this.getTodayLocationsUseCase = getTodayLocationsUseCase;
        this.getLocationsByAssignmentUseCase = getLocationsByAssignmentUseCase;
        MutableStateFlow<LocationTrackingUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(LocationTrackingUiState.Idle.INSTANCE);
        this._uiState = MutableStateFlow;
        this.uiState = MutableStateFlow;
        MutableStateFlow<TrackingDialogState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new TrackingDialogState(false, null, 3, null));
        this._dialogState = MutableStateFlow2;
        this.dialogState = MutableStateFlow2;
    }

    public final StateFlow<LocationTrackingUiState> getUiState() {
        return this.uiState;
    }

    public final StateFlow<TrackingDialogState> getDialogState() {
        return this.dialogState;
    }

    public final void checkShouldShowDialog(Context context, String currentDate) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        this._dialogState.setValue(new TrackingDialogState(!(this.checkTrackingStatusUseCase.invoke(context, currentDate) instanceof CheckTrackingStatusUseCase.TrackingStatus.Active), currentDate));
    }

    /* compiled from: LocationTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$startTracking$1", f = "LocationTrackingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$startTracking$1, reason: invalid class name and case insensitive filesystem */
    static final class C08241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ String $currentDate;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08241(Context context, String str, String str2, Continuation<? super C08241> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$userId = str;
            this.$currentDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LocationTrackingViewModel.this.new C08241(this.$context, this.$userId, this.$currentDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LocationTrackingViewModel.this._uiState.setValue(LocationTrackingUiState.Loading.INSTANCE);
                if (Result.m6859isSuccessimpl(LocationTrackingViewModel.this.startTrackingUseCase.m6678invoke0E7RQCE(this.$context, this.$userId, this.$currentDate))) {
                    LocationTrackingViewModel.this._dialogState.setValue(TrackingDialogState.copy$default((TrackingDialogState) LocationTrackingViewModel.this._dialogState.getValue(), false, null, 2, null));
                    LocationTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Success("Tracking dimulai"));
                } else {
                    LocationTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Gagal memulai tracking"));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void startTracking(Context context, String userId, String currentDate) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08241(context, userId, currentDate, null), 3, null);
    }

    /* compiled from: LocationTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$stopTracking$1", f = "LocationTrackingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$stopTracking$1, reason: invalid class name and case insensitive filesystem */
    static final class C08251 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08251(Context context, Continuation<? super C08251> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LocationTrackingViewModel.this.new C08251(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LocationTrackingViewModel.this.stopTrackingUseCase.m6679invokeIoAF18A(this.$context);
                LocationTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Success("Tracking dihentikan"));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void stopTracking(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08251(context, null), 3, null);
    }

    /* compiled from: LocationTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$saveCurrentLocation$1", f = "LocationTrackingViewModel.kt", i = {}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$saveCurrentLocation$1, reason: invalid class name and case insensitive filesystem */
    static final class C08231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Float $accuracy;
        final /* synthetic */ String $assignmentId;
        final /* synthetic */ String $date;
        final /* synthetic */ double $latitude;
        final /* synthetic */ double $longitude;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08231(String str, String str2, double d, double d2, Float f, String str3, Continuation<? super C08231> continuation) {
            super(2, continuation);
            this.$userId = str;
            this.$assignmentId = str2;
            this.$latitude = d;
            this.$longitude = d2;
            this.$accuracy = f;
            this.$date = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LocationTrackingViewModel.this.new C08231(this.$userId, this.$assignmentId, this.$latitude, this.$longitude, this.$accuracy, this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object value;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SaveLocationUseCase saveLocationUseCase = LocationTrackingViewModel.this.saveLocationUseCase;
                String str = this.$userId;
                String str2 = this.$assignmentId;
                this.label = 1;
                Object objM6677invoketZkwj4A = saveLocationUseCase.m6677invoketZkwj4A(str, (192 & 2) != 0 ? null : str2, this.$latitude, this.$longitude, this.$accuracy, this.$date, (192 & 64) != 0 ? "ENTRY" : null, (192 & 128) != 0 ? "" : null, this);
                if (objM6677invoketZkwj4A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                value = objM6677invoketZkwj4A;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                value = ((Result) obj).getValue();
            }
            if (Result.m6858isFailureimpl(value)) {
                LocationTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Gagal menyimpan lokasi"));
            }
            return Unit.INSTANCE;
        }
    }

    public final void saveCurrentLocation(String userId, String assignmentId, double latitude, double longitude, Float accuracy, String date) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(date, "date");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08231(userId, assignmentId, latitude, longitude, accuracy, date, null), 3, null);
    }

    /* compiled from: LocationTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$observeTodayLocations$1", f = "LocationTrackingViewModel.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$observeTodayLocations$1, reason: invalid class name and case insensitive filesystem */
    static final class C08221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $date;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08221(String str, Continuation<? super C08221> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LocationTrackingViewModel.this.new C08221(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<LocationTracking>> flowInvoke = LocationTrackingViewModel.this.getTodayLocationsUseCase.invoke(this.$date);
                final LocationTrackingViewModel locationTrackingViewModel = LocationTrackingViewModel.this;
                this.label = 1;
                if (flowInvoke.collect(new FlowCollector<List<? extends LocationTracking>>() { // from class: id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel.observeTodayLocations.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(List<? extends LocationTracking> list, Continuation continuation) {
                        return emit2((List<LocationTracking>) list, (Continuation<? super Unit>) continuation);
                    }

                    /* renamed from: emit, reason: avoid collision after fix types in other method */
                    public final Object emit2(List<LocationTracking> list, Continuation<? super Unit> continuation) {
                        locationTrackingViewModel._uiState.setValue(new LocationTrackingUiState.TrackingActive(list.size()));
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
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

    public final void observeTodayLocations(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C08221(date, null), 3, null);
    }

    /* compiled from: LocationTrackingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$getLocationsByAssignment$1", f = "LocationTrackingViewModel.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.presentation.locationtracking.LocationTrackingViewModel$getLocationsByAssignment$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LocationTrackingViewModel.this.new AnonymousClass1(this.$assignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = LocationTrackingViewModel.this.getLocationsByAssignmentUseCase.invoke(this.$assignmentId, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception unused) {
                LocationTrackingViewModel.this._uiState.setValue(new LocationTrackingUiState.Error("Gagal mengambil data lokasi"));
            }
            return Unit.INSTANCE;
        }
    }

    public final void getLocationsByAssignment(String assignmentId) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(assignmentId, null), 3, null);
    }

    public final void hideDialog() {
        MutableStateFlow<TrackingDialogState> mutableStateFlow = this._dialogState;
        mutableStateFlow.setValue(TrackingDialogState.copy$default(mutableStateFlow.getValue(), false, null, 2, null));
    }
}

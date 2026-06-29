package id.go.bpsfasih.ui.login;

import android.app.Application;
import android.net.Uri;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.messaging.Constants;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase;
import id.go.bpsfasih.data.local.entities.FieldOfficerEntity;
import id.go.bpsfasih.data.local.repository.FieldOfficerRepository;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.remote.dto.LoginSsoTokenResponse;
import id.go.bpsfasih.data.repository.AuthRepositoryImpl;
import id.go.bpsfasih.data.repository.DeviceRepositoryImpl;
import id.go.bpsfasih.utils.JWTUtils;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.FcmHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LoginViewModel.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u0007J\u0006\u00103\u001a\u000200J\b\u0010\u000b\u001a\u000200H\u0002J\u0010\u00104\u001a\u0002002\u0006\u00105\u001a\u00020\rH\u0007J\u0010\u00106\u001a\u0002002\u0006\u00105\u001a\u00020\rH\u0007J\u0019\u00107\u001a\u00020\u00142\u0006\u00108\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u00109J\u0011\u0010:\u001a\u00020;H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010<J\b\u0010=\u001a\u000200H\u0002J\u001d\u0010>\u001a\u0002002\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020BH\u0000¢\u0006\u0002\bCJW\u0010D\u001a\u0002002\u0006\u0010E\u001a\u00020\r2\u0006\u0010F\u001a\u00020\r28\u0010G\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\u0014¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002000HH\u0000¢\u0006\u0002\bMJ\b\u0010N\u001a\u0004\u0018\u000102J\b\u0010O\u001a\u0004\u0018\u000102R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010&\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0015\"\u0004\b(\u0010\u0017R\"\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u000f\"\u0004\b+\u0010\u0011R\"\u0010,\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0015\"\u0004\b.\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006P"}, d2 = {"Lid/go/bpsfasih/ui/login/LoginViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "btnSelected", "Landroidx/databinding/ObservableBoolean;", "getBtnSelected", "()Landroidx/databinding/ObservableBoolean;", "setBtnSelected", "(Landroidx/databinding/ObservableBoolean;)V", "errorLogin", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getErrorLogin", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setErrorLogin", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "isSecure", "Landroidx/databinding/ObservableField;", "", "()Landroidx/databinding/ObservableField;", "setSecure", "(Landroidx/databinding/ObservableField;)V", "offlineLogin", "getOfflineLogin", "setOfflineLogin", HintConstants.AUTOFILL_HINT_PASSWORD, "getPassword", "setPassword", "progressDialog", "Landroidx/lifecycle/MutableLiveData;", "getProgressDialog", "()Landroidx/lifecycle/MutableLiveData;", "setProgressDialog", "(Landroidx/lifecycle/MutableLiveData;)V", "repository", "Lid/go/bpsfasih/data/local/repository/FieldOfficerRepository;", "url", "getUrl", "setUrl", "userLogin", "getUserLogin", "setUserLogin", HintConstants.AUTOFILL_HINT_USERNAME, "getUsername", "setUsername", "authenticate", "", "uri", "Landroid/net/Uri;", "checkToken", "exchangeCodeForTokenEksternal", "code", "exchangeCodeForTokenInternal", "getIsSynced", DownloadModel.ID, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJumlahSurvey", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleError", "handleResponseSso", "response", "Lid/go/bpsfasih/data/remote/dto/LoginSsoTokenResponse;", "timeNow", "", "handleResponseSso$app_release", "registerDevice", "userId", "fcmToken", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "message", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "registerDevice$app_release", "urlLoginSsoEksternal", "urlLoginSsoInternal", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LoginViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private ObservableBoolean btnSelected;
    private SingleLiveEvent<String> errorLogin;
    private ObservableField<Boolean> isSecure;
    private SingleLiveEvent<Boolean> offlineLogin;
    private ObservableField<String> password;
    private MutableLiveData<Boolean> progressDialog;
    private FieldOfficerRepository repository;
    private ObservableField<String> url;
    private SingleLiveEvent<Boolean> userLogin;
    private ObservableField<String> username;

    /* compiled from: LoginViewModel.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.login.LoginViewModel", f = "LoginViewModel.kt", i = {0}, l = {182}, m = "getIsSynced", n = {"result"}, s = {"L$0"})
    /* renamed from: id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$1, reason: invalid class name and case insensitive filesystem */
    static final class C09001 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09001(Continuation<? super C09001> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LoginViewModel.this.getIsSynced(null, this);
        }
    }

    /* compiled from: LoginViewModel.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.login.LoginViewModel", f = "LoginViewModel.kt", i = {0}, l = {174}, m = "getJumlahSurvey", n = {"result"}, s = {"L$0"})
    /* renamed from: id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$1, reason: invalid class name and case insensitive filesystem */
    static final class C09011 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09011(Continuation<? super C09011> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LoginViewModel.this.getJumlahSurvey(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.userLogin = new SingleLiveEvent<>();
        this.errorLogin = new SingleLiveEvent<>();
        this.url = new ObservableField<>(CommonCons.INSTANCE.getDEFAULT_URL());
        this.username = new ObservableField<>("");
        this.password = new ObservableField<>("");
        this.isSecure = new ObservableField<>(true);
        this.btnSelected = new ObservableBoolean(false);
        this.progressDialog = new SingleLiveEvent();
        this.offlineLogin = new SingleLiveEvent<>();
        this.repository = new FieldOfficerRepository(SurveyRoomDatabase.INSTANCE.getDatabase(application).fieldOfficerDAO());
    }

    public final ObservableField<String> getUrl() {
        return this.url;
    }

    public final void setUrl(ObservableField<String> observableField) {
        this.url = observableField;
    }

    public final ObservableField<String> getUsername() {
        return this.username;
    }

    public final void setUsername(ObservableField<String> observableField) {
        this.username = observableField;
    }

    public final ObservableField<String> getPassword() {
        return this.password;
    }

    public final void setPassword(ObservableField<String> observableField) {
        this.password = observableField;
    }

    public final ObservableField<Boolean> isSecure() {
        return this.isSecure;
    }

    public final void setSecure(ObservableField<Boolean> observableField) {
        this.isSecure = observableField;
    }

    public final ObservableBoolean getBtnSelected() {
        return this.btnSelected;
    }

    public final void setBtnSelected(ObservableBoolean observableBoolean) {
        this.btnSelected = observableBoolean;
    }

    public final MutableLiveData<Boolean> getProgressDialog() {
        return this.progressDialog;
    }

    public final void setProgressDialog(MutableLiveData<Boolean> mutableLiveData) {
        this.progressDialog = mutableLiveData;
    }

    public final SingleLiveEvent<Boolean> getUserLogin() {
        return this.userLogin;
    }

    public final void setUserLogin(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.userLogin = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getOfflineLogin() {
        return this.offlineLogin;
    }

    public final void setOfflineLogin(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.offlineLogin = singleLiveEvent;
    }

    public final SingleLiveEvent<String> getErrorLogin() {
        return this.errorLogin;
    }

    public final void setErrorLogin(SingleLiveEvent<String> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.errorLogin = singleLiveEvent;
    }

    public final void checkToken() {
        if (FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ACCESS_TOKEN()) != null) {
            SingleLiveEvent<Boolean> singleLiveEvent = this.userLogin;
            if (singleLiveEvent == null) {
                return;
            }
            singleLiveEvent.setValue(true);
            return;
        }
        SingleLiveEvent<Boolean> singleLiveEvent2 = this.userLogin;
        if (singleLiveEvent2 == null) {
            return;
        }
        singleLiveEvent2.setValue(false);
    }

    public final void handleResponseSso$app_release(final LoginSsoTokenResponse response, final long timeNow) throws Exception {
        Intrinsics.checkNotNullParameter(response, "response");
        final JSONObject data = new JWTUtils().getData(response.getAccess_token());
        String string = data.getString("sub");
        Intrinsics.checkNotNullExpressionValue(string, "profil.getString(\"sub\")");
        final FieldOfficerEntity fieldOfficerEntity = new FieldOfficerEntity(string, null, data.getString("name"), data.getString("email"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "null", false);
        ObservableField<String> observableField = this.url;
        final String strValueOf = String.valueOf(observableField != null ? observableField.get() : null);
        FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_AUTH(), response.getToken_type() + " " + response.getAccess_token());
        FcmHelper.INSTANCE.getToken(new Function1<String, Unit>() { // from class: id.go.bpsfasih.ui.login.LoginViewModel$handleResponseSso$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) throws JSONException {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) throws JSONException {
                if (str != null) {
                    LoginViewModel loginViewModel = this.this$0;
                    String id2 = fieldOfficerEntity.getId();
                    final LoginViewModel loginViewModel2 = this.this$0;
                    final LoginSsoTokenResponse loginSsoTokenResponse = response;
                    final JSONObject jSONObject = data;
                    final long j = timeNow;
                    final FieldOfficerEntity fieldOfficerEntity2 = fieldOfficerEntity;
                    final String str2 = strValueOf;
                    loginViewModel.registerDevice$app_release(id2, str, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.ui.login.LoginViewModel$handleResponseSso$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str3, Boolean bool) {
                            invoke(str3, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String str3, boolean z) {
                            if (!z) {
                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02381(loginSsoTokenResponse, jSONObject, j, fieldOfficerEntity2, str2, loginViewModel2, null), 2, null);
                            } else {
                                loginViewModel2.getErrorLogin().postValue(str3);
                            }
                        }

                        /* compiled from: LoginViewModel.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "id.go.bpsfasih.ui.login.LoginViewModel$handleResponseSso$1$1$1", f = "LoginViewModel.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: id.go.bpsfasih.ui.login.LoginViewModel$handleResponseSso$1$1$1, reason: invalid class name and collision with other inner class name */
                        static final class C02381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ FieldOfficerEntity $data;
                            final /* synthetic */ String $dataUrl;
                            final /* synthetic */ JSONObject $profil;
                            final /* synthetic */ LoginSsoTokenResponse $response;
                            final /* synthetic */ long $timeNow;
                            int label;
                            final /* synthetic */ LoginViewModel this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C02381(LoginSsoTokenResponse loginSsoTokenResponse, JSONObject jSONObject, long j, FieldOfficerEntity fieldOfficerEntity, String str, LoginViewModel loginViewModel, Continuation<? super C02381> continuation) {
                                super(2, continuation);
                                this.$response = loginSsoTokenResponse;
                                this.$profil = jSONObject;
                                this.$timeNow = j;
                                this.$data = fieldOfficerEntity;
                                this.$dataUrl = str;
                                this.this$0 = loginViewModel;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new C02381(this.$response, this.$profil, this.$timeNow, this.$data, this.$dataUrl, this.this$0, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((C02381) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            /* JADX WARN: Removed duplicated region for block: B:16:0x01ca  */
                            /* JADX WARN: Removed duplicated region for block: B:20:0x01f0  */
                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                                /*
                                    Method dump skipped, instructions count: 506
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.login.LoginViewModel$handleResponseSso$1.AnonymousClass1.C02381.invokeSuspend(java.lang.Object):java.lang.Object");
                            }
                        }
                    });
                    return;
                }
                MutableLiveData<Boolean> progressDialog = this.this$0.getProgressDialog();
                if (progressDialog != null) {
                    progressDialog.postValue(false);
                }
                this.this$0.errorLogin();
            }
        });
    }

    public final void registerDevice$app_release(String userId, String fcmToken, final Function2<? super String, ? super Boolean, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(fcmToken, "fcmToken");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        if (!(sessionString == null || sessionString.length() == 0)) {
            new DeviceRepositoryImpl().registerDevice(userId, fcmToken, new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.ui.login.LoginViewModel$registerDevice$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(BaseResponse baseResponse) {
                    invoke2(baseResponse);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BaseResponse baseResponse) {
                    Unit unit;
                    if (baseResponse != null) {
                        Function2<String, Boolean, Unit> function2 = callback;
                        LoginViewModel loginViewModel = this.this$0;
                        if (Intrinsics.areEqual((Object) baseResponse.getSuccess(), (Object) true)) {
                            function2.invoke(baseResponse.getMessage(), false);
                        } else {
                            loginViewModel.getErrorLogin().postValue(baseResponse.getMessage());
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        this.this$0.handleError();
                    }
                }
            });
        } else {
            callback.invoke(null, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleError() {
        errorLogin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void errorLogin() {
        MutableLiveData<Boolean> mutableLiveData = this.progressDialog;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(false);
        }
        SingleLiveEvent<Boolean> singleLiveEvent = this.offlineLogin;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getJumlahSurvey(kotlin.coroutines.Continuation<? super java.lang.Integer> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof id.go.bpsfasih.ui.login.LoginViewModel.C09011
            if (r0 == 0) goto L14
            r0 = r11
            id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$1 r0 = (id.go.bpsfasih.ui.login.LoginViewModel.C09011) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$1 r0 = new id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$IntRef r0 = (kotlin.jvm.internal.Ref.IntRef) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L60
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L36:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.jvm.internal.Ref$IntRef r11 = new kotlin.jvm.internal.Ref$IntRef
            r11.<init>()
            kotlinx.coroutines.GlobalScope r2 = kotlinx.coroutines.GlobalScope.INSTANCE
            r4 = r2
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            r5 = 0
            r6 = 0
            id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$2 r2 = new id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$2
            r7 = 0
            r2.<init>(r11, r7)
            r7 = r2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            kotlinx.coroutines.Deferred r2 = kotlinx.coroutines.BuildersKt.async$default(r4, r5, r6, r7, r8, r9)
            r0.L$0 = r11
            r0.label = r3
            java.lang.Object r0 = r2.await(r0)
            if (r0 != r1) goto L5f
            return r1
        L5f:
            r0 = r11
        L60:
            int r11 = r0.element
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.login.LoginViewModel.getJumlahSurvey(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: LoginViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$2", f = "LoginViewModel.kt", i = {}, l = {173}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.login.LoginViewModel$getJumlahSurvey$2, reason: invalid class name and case insensitive filesystem */
    static final class C09022 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $result;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09022(Ref.IntRef intRef, Continuation<? super C09022> continuation) {
            super(2, continuation);
            this.$result = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09022(this.$result, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09022) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.IntRef intRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.IntRef intRef2 = this.$result;
                this.L$0 = intRef2;
                this.label = 1;
                Object jumlahSurveys = DataSurvey.Survey.INSTANCE.getSurveyRepo().getJumlahSurveys(this);
                if (jumlahSurveys == coroutine_suspended) {
                    return coroutine_suspended;
                }
                intRef = intRef2;
                obj = jumlahSurveys;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                intRef = (Ref.IntRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            intRef.element = ((Number) obj).intValue();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getIsSynced(java.lang.String r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof id.go.bpsfasih.ui.login.LoginViewModel.C09001
            if (r0 == 0) goto L14
            r0 = r12
            id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$1 r0 = (id.go.bpsfasih.ui.login.LoginViewModel.C09001) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$1 r0 = new id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r11 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L60
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L36:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            kotlinx.coroutines.GlobalScope r2 = kotlinx.coroutines.GlobalScope.INSTANCE
            r4 = r2
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            r5 = 0
            r6 = 0
            id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$2 r2 = new id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$2
            r7 = 0
            r2.<init>(r12, r10, r11, r7)
            r7 = r2
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            kotlinx.coroutines.Deferred r11 = kotlinx.coroutines.BuildersKt.async$default(r4, r5, r6, r7, r8, r9)
            r0.L$0 = r12
            r0.label = r3
            java.lang.Object r11 = r11.await(r0)
            if (r11 != r1) goto L5f
            return r1
        L5f:
            r11 = r12
        L60:
            T r11 = r11.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.login.LoginViewModel.getIsSynced(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: LoginViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$2", f = "LoginViewModel.kt", i = {}, l = {181}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.login.LoginViewModel$getIsSynced$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $id;
        final /* synthetic */ Ref.ObjectRef<Boolean> $result;
        Object L$0;
        int label;
        final /* synthetic */ LoginViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef<Boolean> objectRef, LoginViewModel loginViewModel, String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$result = objectRef;
            this.this$0 = loginViewModel;
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$result, this.this$0, this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef<Boolean> objectRef;
            T t;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<Boolean> objectRef2 = this.$result;
                this.L$0 = objectRef2;
                this.label = 1;
                Object syncedUser = this.this$0.repository.getSyncedUser(this.$id, this);
                if (syncedUser == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                t = syncedUser;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                t = obj;
            }
            objectRef.element = t;
            return Unit.INSTANCE;
        }
    }

    public final Uri urlLoginSsoEksternal() {
        return Uri.parse("https://sso.bps.go.id/auth/realms/eksternal/protocol/openid-connect/auth").buildUpon().appendQueryParameter("client_id", Config.CLIENT_ID_SSO_EKSTERNAL).appendQueryParameter("redirect_uri", Config.DEEPLINK_SSO_EKSTERNAL).appendQueryParameter("response_type", "code").build();
    }

    public final Uri urlLoginSsoInternal() {
        return Uri.parse("https://sso.bps.go.id/auth/realms/pegawai-bps/protocol/openid-connect/auth").buildUpon().appendQueryParameter("client_id", Config.CLIENT_ID_SSO_INTERNAL).appendQueryParameter("redirect_uri", Config.DEEPLINK_SSO_INTERNAL).appendQueryParameter("response_type", "code").build();
    }

    public final void authenticate(Uri uri) {
        boolean z = true;
        if (uri != null) {
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
            if (StringsKt.startsWith$default(string, Config.DEEPLINK_SSO_INTERNAL, false, 2, (Object) null)) {
                String queryParameter = uri.getQueryParameter("code");
                String str = queryParameter;
                if (str != null && !StringsKt.isBlank(str)) {
                    z = false;
                }
                if (z) {
                    return;
                }
                MutableLiveData<Boolean> mutableLiveData = this.progressDialog;
                if (mutableLiveData != null) {
                    mutableLiveData.postValue(true);
                }
                exchangeCodeForTokenInternal(queryParameter);
                return;
            }
        }
        if (uri != null) {
            String string2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "uri.toString()");
            if (StringsKt.startsWith$default(string2, Config.DEEPLINK_SSO_EKSTERNAL, false, 2, (Object) null)) {
                String queryParameter2 = uri.getQueryParameter("code");
                String str2 = queryParameter2;
                if (str2 != null && !StringsKt.isBlank(str2)) {
                    z = false;
                }
                if (z) {
                    return;
                }
                MutableLiveData<Boolean> mutableLiveData2 = this.progressDialog;
                if (mutableLiveData2 != null) {
                    mutableLiveData2.postValue(true);
                }
                exchangeCodeForTokenEksternal(queryParameter2);
            }
        }
    }

    public final void exchangeCodeForTokenInternal(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        new AuthRepositoryImpl().loginSsoInternal(code, new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.ui.login.LoginViewModel.exchangeCodeForTokenInternal.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginSsoTokenResponse loginSsoTokenResponse) throws Exception {
                invoke2(loginSsoTokenResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginSsoTokenResponse loginSsoTokenResponse) throws Exception {
                Unit unit;
                if (loginSsoTokenResponse != null) {
                    LoginViewModel loginViewModel = LoginViewModel.this;
                    String errorMessage = loginSsoTokenResponse.getErrorMessage();
                    if (errorMessage == null || errorMessage.length() == 0) {
                        loginViewModel.handleResponseSso$app_release(loginSsoTokenResponse, System.currentTimeMillis());
                    } else {
                        loginViewModel.getErrorLogin().postValue(loginSsoTokenResponse.getErrorMessage());
                    }
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    LoginViewModel.this.handleError();
                }
            }
        });
    }

    public final void exchangeCodeForTokenEksternal(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        new AuthRepositoryImpl().loginSsoEksternal(code, new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.ui.login.LoginViewModel.exchangeCodeForTokenEksternal.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginSsoTokenResponse loginSsoTokenResponse) throws Exception {
                invoke2(loginSsoTokenResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginSsoTokenResponse loginSsoTokenResponse) throws Exception {
                Unit unit;
                if (loginSsoTokenResponse != null) {
                    LoginViewModel loginViewModel = LoginViewModel.this;
                    String errorMessage = loginSsoTokenResponse.getErrorMessage();
                    if (errorMessage == null || errorMessage.length() == 0) {
                        loginViewModel.handleResponseSso$app_release(loginSsoTokenResponse, System.currentTimeMillis());
                    } else {
                        loginViewModel.getErrorLogin().postValue(loginSsoTokenResponse.getErrorMessage());
                    }
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    LoginViewModel.this.handleError();
                }
            }
        });
    }
}

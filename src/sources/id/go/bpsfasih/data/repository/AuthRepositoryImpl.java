package id.go.bpsfasih.data.repository;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.api.AuthApiService;
import id.go.bpsfasih.data.remote.dto.LoginSsoTokenResponse;
import id.go.bpsfasih.domain.repository.AuthRepository;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AuthRepository.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J5\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\bH\u0016J5\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\bH\u0016J-\u0010\u000e\u001a\u00020\u00042#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\bH\u0016J-\u0010\u000f\u001a\u00020\u00042#\u0010\u0007\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\bH\u0016¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/data/repository/AuthRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/AuthRepository;", "()V", "loginSsoEksternal", "", "code", "", "loginCallback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/LoginSsoTokenResponse;", "Lkotlin/ParameterName;", "name", "result", "loginSsoInternal", "loginSsoRefreshTokenEksternal", "loginSsoRefreshTokenInternal", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AuthRepositoryImpl implements AuthRepository {
    public static final int $stable = 0;

    @Override // id.go.bpsfasih.domain.repository.AuthRepository
    public void loginSsoInternal(String code, final Function1<? super LoginSsoTokenResponse, Unit> loginCallback) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(loginCallback, "loginCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable observableSubscribeOn = AuthApiService.DefaultImpls.getTokenSsoInternal$default(RetrofitClient.INSTANCE.getAuthApiService(), null, null, null, code, 7, null).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<LoginSsoTokenResponse, Unit> function1 = new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoInternal.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginSsoTokenResponse loginSsoTokenResponse) {
                invoke2(loginSsoTokenResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginSsoTokenResponse loginSsoTokenResponse) {
                Log.d("melia-success", loginSsoTokenResponse.toString());
                loginCallback.invoke(loginSsoTokenResponse);
            }
        };
        Consumer consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoInternal$lambda$0(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoInternal.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Unit unit;
                LoginSsoTokenResponse loginSsoTokenResponse;
                Log.d("melia-error", String.valueOf(th.getMessage()));
                String message = th.getMessage();
                if (message != null) {
                    Function1<LoginSsoTokenResponse, Unit> function13 = loginCallback;
                    String str = message;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "401", false, 2, (Object) null)) {
                        loginSsoTokenResponse = new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "User tidak ditemukan");
                    } else {
                        loginSsoTokenResponse = (StringsKt.contains$default((CharSequence) str, (CharSequence) "java.net.UnknownHostException", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "Unable to resolve host", false, 2, (Object) null)) ? new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "Tidak dapat terhubung ke server") : null;
                    }
                    function13.invoke(loginSsoTokenResponse);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    loginCallback.invoke(null);
                }
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoInternal$lambda$1(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoInternal$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoInternal$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AuthRepository
    public void loginSsoEksternal(String code, final Function1<? super LoginSsoTokenResponse, Unit> loginCallback) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(loginCallback, "loginCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable observableSubscribeOn = AuthApiService.DefaultImpls.getTokenSsoEksternal$default(RetrofitClient.INSTANCE.getAuthApiService(), null, null, null, code, 7, null).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<LoginSsoTokenResponse, Unit> function1 = new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoEksternal.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginSsoTokenResponse loginSsoTokenResponse) {
                invoke2(loginSsoTokenResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginSsoTokenResponse loginSsoTokenResponse) {
                Log.d("melia-success", loginSsoTokenResponse.toString());
                loginCallback.invoke(loginSsoTokenResponse);
            }
        };
        Consumer consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoEksternal$lambda$2(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoEksternal.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Unit unit;
                LoginSsoTokenResponse loginSsoTokenResponse;
                Log.d("melia-error", String.valueOf(th.getMessage()));
                String message = th.getMessage();
                if (message != null) {
                    Function1<LoginSsoTokenResponse, Unit> function13 = loginCallback;
                    String str = message;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "401", false, 2, (Object) null)) {
                        loginSsoTokenResponse = new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "User tidak ditemukan");
                    } else {
                        loginSsoTokenResponse = (StringsKt.contains$default((CharSequence) str, (CharSequence) "java.net.UnknownHostException", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "Unable to resolve host", false, 2, (Object) null)) ? new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "Tidak dapat terhubung ke server") : null;
                    }
                    function13.invoke(loginSsoTokenResponse);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    loginCallback.invoke(null);
                }
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoEksternal$lambda$3(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoEksternal$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoEksternal$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AuthRepository
    public void loginSsoRefreshTokenInternal(final Function1<? super LoginSsoTokenResponse, Unit> loginCallback) {
        Intrinsics.checkNotNullParameter(loginCallback, "loginCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable observableSubscribeOn = AuthApiService.DefaultImpls.getRefreshTokenSsoInternal$default(RetrofitClient.INSTANCE.getAuthApiService(), null, null, null, 7, null).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<LoginSsoTokenResponse, Unit> function1 = new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoRefreshTokenInternal.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginSsoTokenResponse loginSsoTokenResponse) {
                invoke2(loginSsoTokenResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginSsoTokenResponse loginSsoTokenResponse) {
                Log.d("melia-success", loginSsoTokenResponse.toString());
                loginCallback.invoke(loginSsoTokenResponse);
            }
        };
        Consumer consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoRefreshTokenInternal$lambda$4(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoRefreshTokenInternal.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Unit unit;
                LoginSsoTokenResponse loginSsoTokenResponse;
                Log.d("melia-error", String.valueOf(th.getMessage()));
                String message = th.getMessage();
                if (message != null) {
                    Function1<LoginSsoTokenResponse, Unit> function13 = loginCallback;
                    String str = message;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "401", false, 2, (Object) null)) {
                        loginSsoTokenResponse = new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "User tidak ditemukan");
                    } else {
                        loginSsoTokenResponse = (StringsKt.contains$default((CharSequence) str, (CharSequence) "java.net.UnknownHostException", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "Unable to resolve host", false, 2, (Object) null)) ? new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "Tidak dapat terhubung ke server") : null;
                    }
                    function13.invoke(loginSsoTokenResponse);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    loginCallback.invoke(null);
                }
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoRefreshTokenInternal$lambda$5(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoRefreshTokenInternal$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoRefreshTokenInternal$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AuthRepository
    public void loginSsoRefreshTokenEksternal(final Function1<? super LoginSsoTokenResponse, Unit> loginCallback) {
        Intrinsics.checkNotNullParameter(loginCallback, "loginCallback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable observableSubscribeOn = AuthApiService.DefaultImpls.getRefreshTokenSsoEksternal$default(RetrofitClient.INSTANCE.getAuthApiService(), null, null, null, 7, null).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<LoginSsoTokenResponse, Unit> function1 = new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoRefreshTokenEksternal.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginSsoTokenResponse loginSsoTokenResponse) {
                invoke2(loginSsoTokenResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginSsoTokenResponse loginSsoTokenResponse) {
                Log.d("melia-success", loginSsoTokenResponse.toString());
                loginCallback.invoke(loginSsoTokenResponse);
            }
        };
        Consumer consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoRefreshTokenEksternal$lambda$6(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl.loginSsoRefreshTokenEksternal.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Unit unit;
                LoginSsoTokenResponse loginSsoTokenResponse;
                Log.d("melia-error", String.valueOf(th.getMessage()));
                String message = th.getMessage();
                if (message != null) {
                    Function1<LoginSsoTokenResponse, Unit> function13 = loginCallback;
                    String str = message;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "401", false, 2, (Object) null)) {
                        loginSsoTokenResponse = new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "User tidak ditemukan");
                    } else {
                        loginSsoTokenResponse = (StringsKt.contains$default((CharSequence) str, (CharSequence) "java.net.UnknownHostException", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "Unable to resolve host", false, 2, (Object) null)) ? new LoginSsoTokenResponse("", 0L, 0L, "", "", "", "", "Tidak dapat terhubung ke server") : null;
                    }
                    function13.invoke(loginSsoTokenResponse);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    loginCallback.invoke(null);
                }
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AuthRepositoryImpl$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AuthRepositoryImpl.loginSsoRefreshTokenEksternal$lambda$7(function12, obj);
            }
        }));
        Log.d("disposable_size", String.valueOf(FasihApp.INSTANCE.getMyCompositeDisposable().size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoRefreshTokenEksternal$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSsoRefreshTokenEksternal$lambda$7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}

package id.go.bpsfasih.utils.helper;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.remote.dto.LoginSsoTokenResponse;
import id.go.bpsfasih.data.repository.AuthRepositoryImpl;
import id.go.bpsfasih.utils.helper.SsoHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: SsoHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/SsoHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SsoHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: SsoHelper.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004JC\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eH\u0002J1\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000e¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/utils/helper/SsoHelper$Companion;", "", "()V", "checkExpSession", "", "handleResponseSso", "", "activity", "Landroid/app/Activity;", "timeNow", "", "response", "Lid/go/bpsfasih/data/remote/dto/LoginSsoTokenResponse;", "callBack", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "result", "requestRefreshToken", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean checkExpSession() {
            return FasihApp.INSTANCE.getSession().getSessionLong(CommonCons.INSTANCE.getSESSION_EXPIRED(), 0L) >= System.currentTimeMillis();
        }

        public final void requestRefreshToken(final Activity activity, final Function1<? super Boolean, Unit> callBack) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(callBack, "callBack");
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL(), "");
            Intrinsics.checkNotNull(sessionString);
            if (StringsKt.contains$default((CharSequence) sessionString, (CharSequence) "@bps.go.id", false, 2, (Object) null)) {
                Toast.makeText(activity, "Loading request token...", 0).show();
                Log.d("FOUR", "checkLogin: Refresh token berjalan");
                new AuthRepositoryImpl().loginSsoRefreshTokenInternal(new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.utils.helper.SsoHelper$Companion$requestRefreshToken$1
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
                        Unit unit;
                        Log.d("gran-melia", String.valueOf(loginSsoTokenResponse));
                        if (loginSsoTokenResponse != null) {
                            Activity activity2 = activity;
                            final Function1<Boolean, Unit> function1 = callBack;
                            String errorMessage = loginSsoTokenResponse.getErrorMessage();
                            if (errorMessage == null || errorMessage.length() == 0) {
                                SsoHelper.INSTANCE.handleResponseSso(activity2, System.currentTimeMillis(), loginSsoTokenResponse, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.helper.SsoHelper$Companion$requestRefreshToken$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                        invoke(bool.booleanValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(boolean z) {
                                        function1.invoke(Boolean.valueOf(z));
                                    }
                                });
                                Toast.makeText(activity2, "Sukses melakukan refresh token.", 0).show();
                            } else {
                                Toast.makeText(activity2, "Gagal melakukan refresh token, silakan login.", 1).show();
                                function1.invoke(false);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        if (unit == null) {
                            SsoHelper.Companion companion = SsoHelper.INSTANCE;
                            Activity activity3 = activity;
                            Function1<Boolean, Unit> function12 = callBack;
                            Toast.makeText(activity3, "Gagal melakukan refresh token, silakan login.", 1).show();
                            function12.invoke(false);
                        }
                    }
                });
            } else {
                Toast.makeText(activity, "Loading request token...", 0).show();
                Log.d("FOUR", "checkLogin: Refresh token berjalan");
                new AuthRepositoryImpl().loginSsoRefreshTokenEksternal(new Function1<LoginSsoTokenResponse, Unit>() { // from class: id.go.bpsfasih.utils.helper.SsoHelper$Companion$requestRefreshToken$2
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
                        Unit unit;
                        Log.d("gran-melia", String.valueOf(loginSsoTokenResponse));
                        if (loginSsoTokenResponse != null) {
                            Activity activity2 = activity;
                            final Function1<Boolean, Unit> function1 = callBack;
                            String errorMessage = loginSsoTokenResponse.getErrorMessage();
                            if (errorMessage == null || errorMessage.length() == 0) {
                                SsoHelper.INSTANCE.handleResponseSso(activity2, System.currentTimeMillis(), loginSsoTokenResponse, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.helper.SsoHelper$Companion$requestRefreshToken$2$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                        invoke(bool.booleanValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(boolean z) {
                                        function1.invoke(Boolean.valueOf(z));
                                    }
                                });
                                Toast.makeText(activity2, "Sukses melakukan refresh token.", 0).show();
                            } else {
                                Toast.makeText(activity2, "Gagal melakukan refresh token, silakan login.", 1).show();
                                function1.invoke(false);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        if (unit == null) {
                            SsoHelper.Companion companion = SsoHelper.INSTANCE;
                            Activity activity3 = activity;
                            Function1<Boolean, Unit> function12 = callBack;
                            Toast.makeText(activity3, "Gagal melakukan refresh token, silakan login.", 1).show();
                            function12.invoke(false);
                        }
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleResponseSso(Activity activity, long timeNow, LoginSsoTokenResponse response, Function1<? super Boolean, Unit> callBack) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SsoHelper$Companion$handleResponseSso$1(activity, response, timeNow, callBack, null), 2, null);
        }
    }
}

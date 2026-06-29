package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.remote.dto.LoginSsoTokenResponse;
import io.reactivex.Observable;
import kotlin.Metadata;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* compiled from: AuthApiService.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u0006H'J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u0006H'J6\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u0006H'J6\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u0006H'¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/data/remote/api/AuthApiService;", "", "getRefreshTokenSsoEksternal", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/LoginSsoTokenResponse;", "client_id", "", "grant_type", "code", "getRefreshTokenSsoInternal", "getTokenSsoEksternal", "redirect_uri", "getTokenSsoInternal", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AuthApiService {
    @FormUrlEncoded
    @POST("/auth/realms/eksternal/protocol/openid-connect/token")
    Observable<LoginSsoTokenResponse> getRefreshTokenSsoEksternal(@Field("client_id") String client_id, @Field("grant_type") String grant_type, @Field("refresh_token") String code);

    @FormUrlEncoded
    @POST("/auth/realms/pegawai-bps/protocol/openid-connect/token")
    Observable<LoginSsoTokenResponse> getRefreshTokenSsoInternal(@Field("client_id") String client_id, @Field("grant_type") String grant_type, @Field("refresh_token") String code);

    @FormUrlEncoded
    @POST("/auth/realms/eksternal/protocol/openid-connect/token")
    Observable<LoginSsoTokenResponse> getTokenSsoEksternal(@Field("client_id") String client_id, @Field("grant_type") String grant_type, @Field("redirect_uri") String redirect_uri, @Field("code") String code);

    @FormUrlEncoded
    @POST("/auth/realms/pegawai-bps/protocol/openid-connect/token")
    Observable<LoginSsoTokenResponse> getTokenSsoInternal(@Field("client_id") String client_id, @Field("grant_type") String grant_type, @Field("redirect_uri") String redirect_uri, @Field("code") String code);

    /* compiled from: AuthApiService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static /* synthetic */ Observable getTokenSsoInternal$default(AuthApiService authApiService, String str, String str2, String str3, String str4, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTokenSsoInternal");
            }
            if ((i & 1) != 0) {
                str = Config.CLIENT_ID_SSO_INTERNAL;
            }
            if ((i & 2) != 0) {
                str2 = "authorization_code";
            }
            if ((i & 4) != 0) {
                str3 = Config.DEEPLINK_SSO_INTERNAL;
            }
            return authApiService.getTokenSsoInternal(str, str2, str3, str4);
        }

        public static /* synthetic */ Observable getRefreshTokenSsoInternal$default(AuthApiService authApiService, String str, String str2, String str3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRefreshTokenSsoInternal");
            }
            if ((i & 1) != 0) {
                str = Config.CLIENT_ID_SSO_INTERNAL;
            }
            if ((i & 2) != 0) {
                str2 = "refresh_token";
            }
            if ((i & 4) != 0) {
                str3 = String.valueOf(FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_REFRESH_TOKEN()));
            }
            return authApiService.getRefreshTokenSsoInternal(str, str2, str3);
        }

        public static /* synthetic */ Observable getTokenSsoEksternal$default(AuthApiService authApiService, String str, String str2, String str3, String str4, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTokenSsoEksternal");
            }
            if ((i & 1) != 0) {
                str = Config.CLIENT_ID_SSO_EKSTERNAL;
            }
            if ((i & 2) != 0) {
                str2 = "authorization_code";
            }
            if ((i & 4) != 0) {
                str3 = Config.DEEPLINK_SSO_EKSTERNAL;
            }
            return authApiService.getTokenSsoEksternal(str, str2, str3, str4);
        }

        public static /* synthetic */ Observable getRefreshTokenSsoEksternal$default(AuthApiService authApiService, String str, String str2, String str3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRefreshTokenSsoEksternal");
            }
            if ((i & 1) != 0) {
                str = Config.CLIENT_ID_SSO_EKSTERNAL;
            }
            if ((i & 2) != 0) {
                str2 = "refresh_token";
            }
            if ((i & 4) != 0) {
                str3 = String.valueOf(FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_REFRESH_TOKEN()));
            }
            return authApiService.getRefreshTokenSsoEksternal(str, str2, str3);
        }
    }
}

package id.go.bpsfasih.data.localserver;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.remote.api.AnalyticApiService;
import id.go.bpsfasih.data.remote.api.AssignmentApiService;
import id.go.bpsfasih.data.remote.api.AuthApiService;
import id.go.bpsfasih.data.remote.api.ConnectorApiService;
import id.go.bpsfasih.data.remote.api.DeviceApiService;
import id.go.bpsfasih.data.remote.api.NotificationApiService;
import id.go.bpsfasih.data.remote.api.PeriodeApiService;
import id.go.bpsfasih.data.remote.api.RegionApiService;
import id.go.bpsfasih.data.remote.api.SurveyApiService;
import id.go.bpsfasih.data.remote.api.TarikSampelApiService;
import id.go.bpsfasih.data.remote.api.TemplateApiService;
import id.go.bpsfasih.data.remote.api.TicketApiService;
import id.go.bpsfasih.data.remote.api.TrackingApiService;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: RetrofitClient.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/data/localserver/RetrofitClient;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RetrofitClient {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Retrofit retrofit;

    /* compiled from: RetrofitClient.kt */
    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lid/go/bpsfasih/data/localserver/RetrofitClient$Companion;", "", "()V", "retrofit", "Lretrofit2/Retrofit;", "getAnalyticApiService", "Lid/go/bpsfasih/data/remote/api/AnalyticApiService;", "getAssignmentApiService", "Lid/go/bpsfasih/data/remote/api/AssignmentApiService;", "getAuthApiService", "Lid/go/bpsfasih/data/remote/api/AuthApiService;", "getConnectorApiService", "Lid/go/bpsfasih/data/remote/api/ConnectorApiService;", "getDeviceApiService", "Lid/go/bpsfasih/data/remote/api/DeviceApiService;", "getNotificationApiService", "Lid/go/bpsfasih/data/remote/api/NotificationApiService;", "getPeriodeApiService", "Lid/go/bpsfasih/data/remote/api/PeriodeApiService;", "getRegionApiService", "Lid/go/bpsfasih/data/remote/api/RegionApiService;", "getRetrofitInstance", "getSurveyApiService", "Lid/go/bpsfasih/data/remote/api/SurveyApiService;", "getTarikSampelApiService", "Lid/go/bpsfasih/data/remote/api/TarikSampelApiService;", "getTemplateApiService", "Lid/go/bpsfasih/data/remote/api/TemplateApiService;", "getTicketApiService", "Lid/go/bpsfasih/data/remote/api/TicketApiService;", "getTrackingApiService", "Lid/go/bpsfasih/data/remote/api/TrackingApiService;", "setMode", "", "ResponseInterceptor", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setMode() {
            RetrofitClient.retrofit = null;
        }

        public final Retrofit getRetrofitInstance() {
            if (RetrofitClient.retrofit == null) {
                Log.d("retrofit_init", "oke");
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.addInterceptor(new ResponseInterceptor());
                builder.readTimeout(30L, TimeUnit.SECONDS);
                builder.connectTimeout(5L, TimeUnit.SECONDS);
                RetrofitClient.retrofit = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build();
            }
            return RetrofitClient.retrofit;
        }

        public final SurveyApiService getSurveyApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(SurveyApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(SurveyApiService::class.java)");
            return (SurveyApiService) objCreate;
        }

        public final PeriodeApiService getPeriodeApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(PeriodeApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(PeriodeApiService::class.java)");
            return (PeriodeApiService) objCreate;
        }

        public final TemplateApiService getTemplateApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(TemplateApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(TemplateApiService::class.java)");
            return (TemplateApiService) objCreate;
        }

        public final AssignmentApiService getAssignmentApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(AssignmentApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(AssignmentApiService::class.java)");
            return (AssignmentApiService) objCreate;
        }

        public final RegionApiService getRegionApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(RegionApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(RegionApiService::class.java)");
            return (RegionApiService) objCreate;
        }

        public final AuthApiService getAuthApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(Config.BASE_URL_SSO).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(AuthApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(AuthApiService::class.java)");
            return (AuthApiService) objCreate;
        }

        public final NotificationApiService getNotificationApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(NotificationApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(Notifica…onApiService::class.java)");
            return (NotificationApiService) objCreate;
        }

        public final TarikSampelApiService getTarikSampelApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(TarikSampelApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(TarikSampelApiService::class.java)");
            return (TarikSampelApiService) objCreate;
        }

        public final DeviceApiService getDeviceApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(DeviceApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(DeviceApiService::class.java)");
            return (DeviceApiService) objCreate;
        }

        public final TicketApiService getTicketApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(TicketApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(TicketApiService::class.java)");
            return (TicketApiService) objCreate;
        }

        public final TrackingApiService getTrackingApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(TrackingApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(TrackingApiService::class.java)");
            return (TrackingApiService) objCreate;
        }

        public final ConnectorApiService getConnectorApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(ConnectorApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(ConnectorApiService::class.java)");
            return (ConnectorApiService) objCreate;
        }

        public final AnalyticApiService getAnalyticApiService() throws SecurityException {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ResponseInterceptor());
            builder.readTimeout(30L, TimeUnit.SECONDS);
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            Object objCreate = new Retrofit.Builder().baseUrl(new Config().BASE_URL()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(builder.build()).build().create(AnalyticApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(AnalyticApiService::class.java)");
            return (AnalyticApiService) objCreate;
        }

        /* compiled from: RetrofitClient.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/data/localserver/RetrofitClient$Companion$ResponseInterceptor;", "Lokhttp3/Interceptor;", "()V", "getRequestBuilder", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "intercept", "isNeedAuth", "", ClientCookie.PATH_ATTR, "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        private static final class ResponseInterceptor implements Interceptor {
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                String strString;
                Intrinsics.checkNotNullParameter(chain, "chain");
                Response requestBuilder = getRequestBuilder(chain);
                try {
                    ResponseBody responseBodyBody = requestBuilder.body();
                    if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                        strString = "";
                    }
                    ResponseBody responseBodyBody2 = requestBuilder.body();
                    return requestBuilder.newBuilder().body(ResponseBody.INSTANCE.create(responseBodyBody2 != null ? responseBodyBody2.getMediaType() : null, strString)).build();
                } catch (JSONException e) {
                    e.printStackTrace();
                    return requestBuilder;
                }
            }

            private final Response getRequestBuilder(Interceptor.Chain chain) {
                Request request = chain.request();
                String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_AUTH());
                if (sessionString == null) {
                    sessionString = "";
                }
                String sessionString2 = FasihApp.INSTANCE.getSession().getSessionString("deviceId");
                String str = sessionString2 != null ? sessionString2 : "";
                String strHeader = request.header("No-Authentication");
                boolean z = strHeader != null && StringsKt.equals(strHeader, "true", true);
                String strHost = request.url().host();
                Request.Builder builderNewBuilder = request.newBuilder();
                if (z) {
                    builderNewBuilder.removeHeader("Authorization");
                    builderNewBuilder.removeHeader("No-Authentication");
                }
                String str2 = strHost;
                if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "wilkerstat", false, 2, (Object) null)) {
                    builderNewBuilder.header("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 8.1.0; Android SDK built for x86 Build/OSM1.180201.021)").header("Content-Type", "application/json");
                    if (!z) {
                        if ((sessionString.length() > 0) && isNeedAuth(request.url().pathSegments().get(request.url().pathSegments().size() - 1))) {
                            builderNewBuilder.addHeader("Authorization", sessionString);
                        }
                    }
                    if (str.length() > 0) {
                        builderNewBuilder.addHeader("X-Device-Id", str);
                    }
                } else if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "/gsm/api/", false, 2, (Object) null)) {
                    builderNewBuilder.header("x-bps-key", "402890816d306129016d340e2ac50001");
                } else {
                    builderNewBuilder.header("Authorization", "Bearer ZtRmH0TheNmEqHlyhtVI3Ce5gGa3nOUtxnX40BpIlHOSYIrhshpC9OZzpB7i").header("Content-Type", "application/json");
                }
                return chain.proceed(builderNewBuilder.build());
            }

            private final boolean isNeedAuth(String path) {
                return (path == null || StringsKt.equals(StringsKt.trim((CharSequence) path.toString()).toString(), "dologin", true)) ? false : true;
            }
        }
    }
}

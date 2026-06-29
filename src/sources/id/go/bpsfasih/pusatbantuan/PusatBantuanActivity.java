package id.go.bpsfasih.pusatbantuan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.pusatbantuan.PusatBantuanActivity;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: PusatBantuanActivity.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 >2\u00020\u0001:\u0002>?B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00100\u001a\u000201J\b\u00102\u001a\u000201H\u0003J\"\u00103\u001a\u0002012\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\b\u00106\u001a\u0004\u0018\u000107H\u0014J\u0012\u00108\u001a\u0002012\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\u0018\u0010;\u001a\u0002012\u0006\u0010<\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0006\u0010=\u001a\u000201R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010!\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0010\u0010(\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020+X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u0006@"}, d2 = {"Lid/go/bpsfasih/pusatbantuan/PusatBantuanActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "FILECHOOSER_RESULTCODE", "", "REQUEST_SELECT_FILE", "getREQUEST_SELECT_FILE", "()I", "assignmentId", "", "deviceInfo", "firebaseToken", "isProduction", "", "jsi", "Lid/go/bpsfasih/pusatbantuan/JavascriptInterfaceBantuan;", "link", "getLink", "()Ljava/lang/String;", "setLink", "(Ljava/lang/String;)V", "loadingPb", "Landroid/widget/ProgressBar;", "getLoadingPb", "()Landroid/widget/ProgressBar;", "setLoadingPb", "(Landroid/widget/ProgressBar;)V", "mUploadMessage", "Landroid/webkit/ValueCallback;", "pusatBantuan", "Lid/go/bpsfasih/pusatbantuan/PusatBantuan;", "surveys", "token", "uploadMessageArray", "", "Landroid/net/Uri;", "getUploadMessageArray", "()Landroid/webkit/ValueCallback;", "setUploadMessageArray", "(Landroid/webkit/ValueCallback;)V", "userId", HintConstants.AUTOFILL_HINT_USERNAME, "wvVWebContent", "Landroid/webkit/WebView;", "getWvVWebContent", "()Landroid/webkit/WebView;", "setWvVWebContent", "(Landroid/webkit/WebView;)V", "hideProgressBar", "", "loadContent", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "sendFile", "ticketId", "showProgressBar", "Companion", "MyWebChromeClient", "pusatbantuan_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PusatBantuanActivity extends AppCompatActivity {
    private String deviceInfo;
    private String firebaseToken;
    private JavascriptInterfaceBantuan jsi;
    private String link;
    public ProgressBar loadingPb;
    private ValueCallback<?> mUploadMessage;
    private PusatBantuan pusatBantuan;
    private String surveys;
    private String token;
    private ValueCallback<Uri[]> uploadMessageArray;
    private String userId;
    private String username;
    public WebView wvVWebContent;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String USERNAME = HintConstants.AUTOFILL_HINT_USERNAME;
    private static final String USERID = "userId";
    private static final String TOKEN = "token";
    private static final String DEVICEINFO = "device_info";
    private static final String FIREBASE_TOKEN = "firebase_token";
    private static final String SURVEYS = "survey";
    private static final String IS_PRODUCTION = "is_production";
    private static final String ASSIGNMENT_ID = "assignment_id";
    private static final String PUSAT_BANTUAN = "pusat_bantuan";
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean isProduction = true;
    private String assignmentId = "";
    private final int REQUEST_SELECT_FILE = 100;
    private final int FILECHOOSER_RESULTCODE = 1;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final WebView getWvVWebContent() {
        WebView webView = this.wvVWebContent;
        if (webView != null) {
            return webView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("wvVWebContent");
        return null;
    }

    public final void setWvVWebContent(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.wvVWebContent = webView;
    }

    public final ProgressBar getLoadingPb() {
        ProgressBar progressBar = this.loadingPb;
        if (progressBar != null) {
            return progressBar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("loadingPb");
        return null;
    }

    public final void setLoadingPb(ProgressBar progressBar) {
        Intrinsics.checkNotNullParameter(progressBar, "<set-?>");
        this.loadingPb = progressBar;
    }

    public final int getREQUEST_SELECT_FILE() {
        return this.REQUEST_SELECT_FILE;
    }

    public final ValueCallback<Uri[]> getUploadMessageArray() {
        return this.uploadMessageArray;
    }

    public final void setUploadMessageArray(ValueCallback<Uri[]> valueCallback) {
        this.uploadMessageArray = valueCallback;
    }

    public final String getLink() {
        return this.link;
    }

    public final void setLink(String str) {
        this.link = str;
    }

    /* compiled from: PusatBantuanActivity.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jk\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\b\u0010 \u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010$\u001a\u00020%¢\u0006\u0002\u0010&R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006¨\u0006'"}, d2 = {"Lid/go/bpsfasih/pusatbantuan/PusatBantuanActivity$Companion;", "", "()V", "ASSIGNMENT_ID", "", "getASSIGNMENT_ID", "()Ljava/lang/String;", "DEVICEINFO", "getDEVICEINFO", "FIREBASE_TOKEN", "getFIREBASE_TOKEN", "IS_PRODUCTION", "getIS_PRODUCTION", "PUSAT_BANTUAN", "getPUSAT_BANTUAN", "SURVEYS", "getSURVEYS", "TOKEN", "getTOKEN", "USERID", "getUSERID", "USERNAME", "getUSERNAME", "startActivity", "", "context", "Landroid/content/Context;", HintConstants.AUTOFILL_HINT_USERNAME, "userId", "token", "deviceInfo", "firebaseToken", "assignmentId", "isProduction", "", "surveys", "pusatBantuan", "Lid/go/bpsfasih/pusatbantuan/PusatBantuan;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lid/go/bpsfasih/pusatbantuan/PusatBantuan;)V", "pusatbantuan_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getUSERNAME() {
            return PusatBantuanActivity.USERNAME;
        }

        public final String getUSERID() {
            return PusatBantuanActivity.USERID;
        }

        public final String getTOKEN() {
            return PusatBantuanActivity.TOKEN;
        }

        public final String getDEVICEINFO() {
            return PusatBantuanActivity.DEVICEINFO;
        }

        public final String getFIREBASE_TOKEN() {
            return PusatBantuanActivity.FIREBASE_TOKEN;
        }

        public final String getSURVEYS() {
            return PusatBantuanActivity.SURVEYS;
        }

        public final String getIS_PRODUCTION() {
            return PusatBantuanActivity.IS_PRODUCTION;
        }

        public final String getASSIGNMENT_ID() {
            return PusatBantuanActivity.ASSIGNMENT_ID;
        }

        public final String getPUSAT_BANTUAN() {
            return PusatBantuanActivity.PUSAT_BANTUAN;
        }

        public final void startActivity(Context context, String username, String userId, String token, String deviceInfo, String firebaseToken, String assignmentId, Boolean isProduction, String surveys, PusatBantuan pusatBantuan) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(pusatBantuan, "pusatBantuan");
            Intent intent = new Intent(context, (Class<?>) PusatBantuanActivity.class);
            intent.putExtra(getUSERNAME(), username);
            intent.putExtra(getUSERID(), userId);
            intent.putExtra(getTOKEN(), token);
            intent.putExtra(getDEVICEINFO(), deviceInfo);
            intent.putExtra(getFIREBASE_TOKEN(), firebaseToken);
            intent.putExtra(getIS_PRODUCTION(), isProduction);
            intent.putExtra(getASSIGNMENT_ID(), assignmentId);
            intent.putExtra(getPUSAT_BANTUAN(), pusatBantuan);
            intent.putExtra(getSURVEYS(), surveys);
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusat_bantuan);
        View viewFindViewById = findViewById(R.id.web_view_component);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.web_view_component)");
        setWvVWebContent((WebView) viewFindViewById);
        View viewFindViewById2 = findViewById(R.id.loading_pb);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.loading_pb)");
        setLoadingPb((ProgressBar) viewFindViewById2);
        if (Build.VERSION.SDK_INT >= 33) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.READ_MEDIA_AUDIO"}, 1);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
        loadContent();
    }

    private final void loadContent() {
        PusatBantuan pusatBantuan;
        this.username = getIntent().getStringExtra(USERNAME);
        this.userId = getIntent().getStringExtra(USERID);
        this.token = getIntent().getStringExtra(TOKEN);
        this.deviceInfo = getIntent().getStringExtra(DEVICEINFO);
        this.firebaseToken = getIntent().getStringExtra(FIREBASE_TOKEN);
        this.isProduction = getIntent().getBooleanExtra(IS_PRODUCTION, true);
        this.assignmentId = getIntent().getStringExtra(ASSIGNMENT_ID);
        if (Build.VERSION.SDK_INT >= 33) {
            Serializable serializableExtra = getIntent().getSerializableExtra(PUSAT_BANTUAN, PusatBantuan.class);
            Intrinsics.checkNotNull(serializableExtra);
            pusatBantuan = (PusatBantuan) serializableExtra;
        } else {
            Serializable serializableExtra2 = getIntent().getSerializableExtra(PUSAT_BANTUAN);
            Intrinsics.checkNotNull(serializableExtra2, "null cannot be cast to non-null type id.go.bpsfasih.pusatbantuan.PusatBantuan");
            pusatBantuan = (PusatBantuan) serializableExtra2;
        }
        this.pusatBantuan = pusatBantuan;
        this.surveys = getIntent().getStringExtra(SURVEYS);
        getWvVWebContent().getSettings().setJavaScriptEnabled(true);
        getWvVWebContent().getSettings().setAllowFileAccess(true);
        getWvVWebContent().getSettings().setDomStorageEnabled(true);
        getWvVWebContent().getSettings().setAllowContentAccess(true);
        getWvVWebContent().getSettings().setAllowFileAccess(true);
        getWvVWebContent().clearCache(true);
        getWvVWebContent().setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.pusatbantuan.PusatBantuanActivity.loadContent.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        this.jsi = new JavascriptInterfaceBantuan(getWvVWebContent(), this, this.username, this.userId, this.token, this.assignmentId, this.deviceInfo, this.firebaseToken, this.surveys);
        WebView wvVWebContent = getWvVWebContent();
        JavascriptInterfaceBantuan javascriptInterfaceBantuan = this.jsi;
        Intrinsics.checkNotNull(javascriptInterfaceBantuan);
        wvVWebContent.addJavascriptInterface(javascriptInterfaceBantuan, "Android");
        getWvVWebContent().setWebChromeClient(new MyWebChromeClient());
        getWvVWebContent().setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.pusatbantuan.PusatBantuanActivity.loadContent.2
            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                view.loadUrl(url);
                return true;
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(request, "request");
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(PusatBantuanActivity.this.getApplicationContext(), description, 0);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                super.onPageFinished(view, url);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                Toast.makeText(PusatBantuanActivity.this.getApplicationContext(), String.valueOf(errorResponse), 0);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(request, "request");
                Intrinsics.checkNotNullParameter(error, "error");
                super.onReceivedError(view, request, error);
                Toast.makeText(PusatBantuanActivity.this.getApplicationContext(), "onReceivedError", 0);
                Toast.makeText(PusatBantuanActivity.this.getApplicationContext(), "error code: " + error.getErrorCode() + ' ' + request.getUrl() + " , " + ((Object) error.getDescription()), 0).show();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(handler, "handler");
                Intrinsics.checkNotNullParameter(error, "error");
                super.onReceivedSslError(view, handler, error);
                Toast.makeText(PusatBantuanActivity.this.getApplicationContext(), "onReceivedSslError", 0).show();
            }
        });
        if (this.isProduction) {
            getWvVWebContent().loadUrl("file:///android_asset/bantuan/ticket-prod.html");
        } else {
            getWvVWebContent().loadUrl("file:///android_asset/bantuan/ticket-dev.html");
        }
    }

    /* compiled from: PusatBantuanActivity.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0017J\u001c\u0010\u0011\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0004J\u0016\u0010\u0011\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\fH\u0004J&\u0010\u0011\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0004¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/pusatbantuan/PusatBantuanActivity$MyWebChromeClient;", "Landroid/webkit/WebChromeClient;", "(Lid/go/bpsfasih/pusatbantuan/PusatBantuanActivity;)V", "onPermissionRequest", "", "request", "Landroid/webkit/PermissionRequest;", "onShowFileChooser", "", "mWebView", "Landroid/webkit/WebView;", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "fileChooserParams", "Landroid/webkit/WebChromeClient$FileChooserParams;", "openFileChooser", "uploadMsg", "acceptType", "", "capture", "pusatbantuan_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MyWebChromeClient extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public void onPermissionRequest(PermissionRequest request) {
        }

        public MyWebChromeClient() {
        }

        protected final void openFileChooser(ValueCallback<?> uploadMsg, String acceptType) {
            Intrinsics.checkNotNullParameter(uploadMsg, "uploadMsg");
            Intrinsics.checkNotNullParameter(acceptType, "acceptType");
            PusatBantuanActivity.this.mUploadMessage = uploadMsg;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("image/*");
            PusatBantuanActivity.this.startActivityForResult(Intent.createChooser(intent, "File Chooser"), PusatBantuanActivity.this.FILECHOOSER_RESULTCODE);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            Intrinsics.checkNotNullParameter(mWebView, "mWebView");
            Intrinsics.checkNotNullParameter(filePathCallback, "filePathCallback");
            Intrinsics.checkNotNullParameter(fileChooserParams, "fileChooserParams");
            if (PusatBantuanActivity.this.getUploadMessageArray() != null) {
                ValueCallback<Uri[]> uploadMessageArray = PusatBantuanActivity.this.getUploadMessageArray();
                Intrinsics.checkNotNull(uploadMessageArray);
                uploadMessageArray.onReceiveValue(null);
                PusatBantuanActivity.this.setUploadMessageArray(null);
            }
            PusatBantuanActivity.this.setUploadMessageArray(filePathCallback);
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("image/*");
            try {
                PusatBantuanActivity pusatBantuanActivity = PusatBantuanActivity.this;
                pusatBantuanActivity.startActivityForResult(intent, pusatBantuanActivity.getREQUEST_SELECT_FILE());
                return true;
            } catch (Exception unused) {
                PusatBantuanActivity.this.setUploadMessageArray(null);
                Toast.makeText(PusatBantuanActivity.this.getApplicationContext(), "Cannot Open File Chooser", 0).show();
                return false;
            }
        }

        protected final void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            Intrinsics.checkNotNullParameter(uploadMsg, "uploadMsg");
            Intrinsics.checkNotNullParameter(acceptType, "acceptType");
            Intrinsics.checkNotNullParameter(capture, "capture");
            PusatBantuanActivity.this.mUploadMessage = uploadMsg;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("image/*");
            PusatBantuanActivity.this.startActivityForResult(Intent.createChooser(intent, "File Chooser"), PusatBantuanActivity.this.FILECHOOSER_RESULTCODE);
        }

        protected final void openFileChooser(ValueCallback<Uri> uploadMsg) {
            Intrinsics.checkNotNullParameter(uploadMsg, "uploadMsg");
            PusatBantuanActivity.this.mUploadMessage = uploadMsg;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("image/*");
            PusatBantuanActivity.this.startActivityForResult(Intent.createChooser(intent, "File Chooser"), PusatBantuanActivity.this.FILECHOOSER_RESULTCODE);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != this.REQUEST_SELECT_FILE || this.uploadMessageArray == null) {
            return;
        }
        System.out.print((Object) ("result code = " + resultCode));
        Uri[] result = WebChromeClient.FileChooserParams.parseResult(resultCode, data);
        ValueCallback<Uri[]> valueCallback = this.uploadMessageArray;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(result);
        }
        this.uploadMessageArray = null;
    }

    public final void sendFile(String ticketId, String assignmentId) {
        Intrinsics.checkNotNullParameter(ticketId, "ticketId");
        if (assignmentId != null) {
            runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.pusatbantuan.PusatBantuanActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PusatBantuanActivity.sendFile$lambda$0(this.f$0);
                }
            });
            PusatBantuan pusatBantuan = this.pusatBantuan;
            Intrinsics.checkNotNull(pusatBantuan);
            pusatBantuan.sendFile(assignmentId, ticketId, new C08262());
            return;
        }
        Toast.makeText(this, "Gagal mendapatkan data", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendFile$lambda$0(PusatBantuanActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showProgressBar();
    }

    /* compiled from: PusatBantuanActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.pusatbantuan.PusatBantuanActivity$sendFile$2, reason: invalid class name and case insensitive filesystem */
    static final class C08262 extends Lambda implements Function2<Boolean, String, Unit> {
        C08262() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
            invoke(bool.booleanValue(), str);
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z, final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            final PusatBantuanActivity pusatBantuanActivity = PusatBantuanActivity.this;
            pusatBantuanActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.pusatbantuan.PusatBantuanActivity$sendFile$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PusatBantuanActivity.C08262.invoke$lambda$0(pusatBantuanActivity, message);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(PusatBantuanActivity this$0, String message) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(message, "$message");
            this$0.hideProgressBar();
            Toast.makeText(this$0, message, 1).show();
        }
    }

    public final void showProgressBar() {
        getLoadingPb().setVisibility(0);
        getWindow().setFlags(16, 16);
    }

    public final void hideProgressBar() {
        getLoadingPb().setVisibility(8);
        getWindow().clearFlags(16);
    }
}

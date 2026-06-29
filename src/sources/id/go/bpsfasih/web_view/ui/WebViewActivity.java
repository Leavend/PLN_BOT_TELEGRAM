package id.go.bpsfasih.web_view.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.web_view.databinding.ActivityWebViewBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WebViewActivity.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0006\u0010\u0013\u001a\u00020\u0010J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/web_view/ui/WebViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lid/go/bpsfasih/web_view/databinding/ActivityWebViewBinding;", "getBinding", "()Lid/go/bpsfasih/web_view/databinding/ActivityWebViewBinding;", "setBinding", "(Lid/go/bpsfasih/web_view/databinding/ActivityWebViewBinding;)V", "url", "", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "clearCookies", "", "context", "Landroid/content/Context;", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "web-view_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class WebViewActivity extends AppCompatActivity {
    public ActivityWebViewBinding binding;
    private String url;

    public final ActivityWebViewBinding getBinding() {
        ActivityWebViewBinding activityWebViewBinding = this.binding;
        if (activityWebViewBinding != null) {
            return activityWebViewBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivityWebViewBinding activityWebViewBinding) {
        Intrinsics.checkNotNullParameter(activityWebViewBinding, "<set-?>");
        this.binding = activityWebViewBinding;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWebViewBinding activityWebViewBindingInflate = ActivityWebViewBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWebViewBindingInflate, "inflate(layoutInflater)");
        setBinding(activityWebViewBindingInflate);
        setContentView(getBinding().getRoot());
        this.url = getIntent().getStringExtra("URL");
        initView();
    }

    public final void initView() {
        getBinding().contentL.getSettings().setJavaScriptEnabled(true);
        getBinding().contentL.getSettings().setAllowFileAccess(true);
        getBinding().contentL.clearCache(true);
        getBinding().contentL.clearHistory();
        clearCookies(this);
        getBinding().contentL.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.web_view.ui.WebViewActivity.initView.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri url = request != null ? request.getUrl() : null;
                Intrinsics.checkNotNull(url);
                return handleUri(url);
            }

            private final boolean handleUri(Uri uri) {
                String string = uri.toString();
                Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
                if (!StringsKt.contains$default((CharSequence) string, (CharSequence) "id.go.bps", false, 2, (Object) null)) {
                    String string2 = uri.toString();
                    Intrinsics.checkNotNullExpressionValue(string2, "uri.toString()");
                    if (!StringsKt.contains$default((CharSequence) string2, (CharSequence) "https://sso.bps.go.id/auth/realms/eksternal/broker/google/login", false, 2, (Object) null)) {
                        return false;
                    }
                }
                WebViewActivity.this.setResult(-1, new Intent().putExtra("uri", uri.toString()));
                WebViewActivity.this.finish();
                return true;
            }
        });
        getBinding().contentL.loadUrl(String.valueOf(this.url));
    }

    public final void clearCookies(Context context) {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
    }
}

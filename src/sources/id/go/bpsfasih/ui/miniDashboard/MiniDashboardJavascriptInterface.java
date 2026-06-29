package id.go.bpsfasih.ui.miniDashboard;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MiniDashboardJavascriptInterface.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\n\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0007J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/ui/miniDashboard/MiniDashboardJavascriptInterface;", "", "webView", "Landroid/webkit/WebView;", "activity", "Lid/go/bpsfasih/ui/miniDashboard/MiniDashboardActivity;", "assignmentData", "", "dashboardScript", "(Landroid/webkit/WebView;Lid/go/bpsfasih/ui/miniDashboard/MiniDashboardActivity;Ljava/lang/String;Ljava/lang/String;)V", "getAssignmentData", "getDashboardDesign", "loadJSONFromAsset", "context", "Landroid/content/Context;", "fileName", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MiniDashboardJavascriptInterface {
    public static final String TAG_HANDLER = "Android";
    private MiniDashboardActivity activity;
    private String assignmentData;
    public static final int $stable = 8;

    public MiniDashboardJavascriptInterface(WebView webView, MiniDashboardActivity activity, String assignmentData, String str) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(assignmentData, "assignmentData");
        this.activity = activity;
        this.assignmentData = assignmentData;
    }

    @JavascriptInterface
    public final String getAssignmentData() {
        return this.assignmentData;
    }

    @JavascriptInterface
    public final String getDashboardDesign() {
        try {
            return loadJSONFromAsset(this.activity, "dashboard_design.json");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final String loadJSONFromAsset(Context context, String fileName) throws IOException {
        try {
            InputStream inputStreamOpen = context.getAssets().open(fileName);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(fileName)");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            return new String(bArr, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package id.go.bpsfasih.utils.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* compiled from: Network.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/utils/helper/Network;", "", "()V", "NETWORK_STATUS_MOBILE", "", "NETWORK_STATUS_NOT_CONNECTED", "NETWORK_STATUS_WIFI", "TYPE_MOBILE", "TYPE_NOT_CONNECTED", "TYPE_WIFI", "connectivityStatus", "context", "Landroid/content/Context;", "connectivityStatusString", "createPartFromString", "Lokhttp3/RequestBody;", "descriptionString", "", "isOnline", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Network {
    public static final int $stable = 0;
    public static final Network INSTANCE = new Network();
    private static final int NETWORK_STATUS_MOBILE = 2;
    private static final int NETWORK_STATUS_NOT_CONNECTED = 0;
    private static final int NETWORK_STATUS_WIFI = 1;
    private static final int TYPE_MOBILE = 2;
    private static final int TYPE_NOT_CONNECTED = 0;
    private static final int TYPE_WIFI = 1;

    private Network() {
    }

    private final int connectivityStatus(Context context) {
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        return activeNetworkInfo.getType() == 0 ? 2 : 0;
    }

    private final int connectivityStatusString(Context context) {
        int iConnectivityStatus = connectivityStatus(context);
        if (iConnectivityStatus == 0) {
            return 0;
        }
        if (iConnectivityStatus != 1) {
            return iConnectivityStatus != 2 ? -1 : 2;
        }
        return 1;
    }

    public final boolean isOnline(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int iConnectivityStatusString = connectivityStatusString(context);
        return iConnectivityStatusString == 1 || iConnectivityStatusString == 2;
    }

    public final RequestBody createPartFromString(String descriptionString) {
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MultipartBody.FORM;
        if (descriptionString == null) {
            descriptionString = "";
        }
        return companion.create(mediaType, descriptionString);
    }
}

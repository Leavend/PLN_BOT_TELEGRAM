package id.go.bpsfasih.utils.helper;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.models.SignalInfoModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SignalInfoHelper.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/utils/helper/SignalInfoHelper;", "", "()V", "getSignalHelper", "Lid/go/bpsfasih/domain/models/SignalInfoModel;", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SignalInfoHelper {
    public static final int $stable = 0;
    public static final SignalInfoHelper INSTANCE = new SignalInfoHelper();

    private SignalInfoHelper() {
    }

    public final SignalInfoModel getSignalHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            SignalInfoModel signalInfoModel = new SignalInfoModel();
            Object systemService = context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            signalInfoModel.setType(String.valueOf(telephonyManager.getPhoneType()));
            signalInfoModel.setProvider(telephonyManager.getNetworkOperatorName().toString());
            if (Build.VERSION.SDK_INT >= 28) {
                signalInfoModel.setDetailSignalStrength(String.valueOf(telephonyManager.getSignalStrength()));
            } else {
                signalInfoModel.setDetailSignalStrength("");
            }
            return signalInfoModel;
        } catch (Exception unused) {
            return null;
        }
    }
}

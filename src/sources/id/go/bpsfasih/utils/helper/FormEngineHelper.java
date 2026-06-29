package id.go.bpsfasih.utils.helper;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.utils.Directory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FormEngineHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/FormEngineHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FormEngineHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: FormEngineHelper.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/utils/helper/FormEngineHelper$Companion;", "", "()V", "getFormEngineVersion", "", "formgearId", "getFormGearId", "", "surveyId", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getFormEngineVersion(String formgearId) throws JSONException {
            Intrinsics.checkNotNullParameter(formgearId, "formgearId");
            try {
                String hasil = new JSONObject(FileHelper.INSTANCE.readFile(Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + formgearId + "/version.json").toString()).getString(ClientCookie.VERSION_ATTR);
                Intrinsics.checkNotNullExpressionValue(hasil, "hasil");
                return hasil;
            } catch (JSONException unused) {
                return "";
            }
        }

        public final int getFormGearId(String surveyId) {
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Integer num = (Integer) BuildersKt__BuildersKt.runBlocking$default(null, new FormEngineHelper$Companion$getFormGearId$formEngineId$1(surveyId, null), 1, null);
            if (num == null) {
                return 1;
            }
            return num.intValue();
        }
    }
}

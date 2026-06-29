package id.go.bpsfasih.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Directory.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/Directory;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Directory {
    public static final int $stable = 0;
    private static final String ABSOLUTEPATH;
    private static final String ABSOLUTEPATHBACKUP;
    private static final String ABSOLUTEPATHENV;
    private static final String ENGINE_VERSION;
    private static final String FORMENGINE_FILE_TEMP;
    private static final String FORMENGINE_PATH;
    private static final String TARIK_SAMPEL_OFFLINE_PATH;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ABSOLUTE_PATH_ERROR = CommonCons.INSTANCE.getABSOLUTE_PATH() + "errorLog";
    private static final String ABSOLUTEPATHANSWERPREFIX = CommonCons.INSTANCE.getABSOLUTE_PATH() + FasihApp.INSTANCE.getSession().getUserId() + "/answers";
    private static final String ABSOLUTEPATHTEMPLATEDSIGNER = CommonCons.INSTANCE.getABSOLUTE_PATH() + "Template/";
    private static final String ABSOLUTEPATHLOOKUP = CommonCons.INSTANCE.getABSOLUTE_PATH() + "lookup/";

    /* compiled from: Directory.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/utils/Directory$Companion;", "", "()V", "ABSOLUTEPATH", "", "getABSOLUTEPATH", "()Ljava/lang/String;", "ABSOLUTEPATHANSWERPREFIX", "getABSOLUTEPATHANSWERPREFIX", "ABSOLUTEPATHBACKUP", "getABSOLUTEPATHBACKUP", "ABSOLUTEPATHENV", "getABSOLUTEPATHENV", "ABSOLUTEPATHLOOKUP", "getABSOLUTEPATHLOOKUP", "ABSOLUTEPATHTEMPLATEDSIGNER", "getABSOLUTEPATHTEMPLATEDSIGNER", "ABSOLUTE_PATH_ERROR", "getABSOLUTE_PATH_ERROR", "ENGINE_VERSION", "getENGINE_VERSION", "FORMENGINE_FILE_TEMP", "getFORMENGINE_FILE_TEMP", "FORMENGINE_PATH", "getFORMENGINE_PATH", "TARIK_SAMPEL_OFFLINE_PATH", "getTARIK_SAMPEL_OFFLINE_PATH", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getABSOLUTE_PATH_ERROR() {
            return Directory.ABSOLUTE_PATH_ERROR;
        }

        public final String getABSOLUTEPATHANSWERPREFIX() {
            return Directory.ABSOLUTEPATHANSWERPREFIX;
        }

        public final String getABSOLUTEPATHTEMPLATEDSIGNER() {
            return Directory.ABSOLUTEPATHTEMPLATEDSIGNER;
        }

        public final String getABSOLUTEPATHLOOKUP() {
            return Directory.ABSOLUTEPATHLOOKUP;
        }

        public final String getABSOLUTEPATH() {
            return Directory.ABSOLUTEPATH;
        }

        public final String getABSOLUTEPATHBACKUP() {
            return Directory.ABSOLUTEPATHBACKUP;
        }

        public final String getABSOLUTEPATHENV() {
            return Directory.ABSOLUTEPATHENV;
        }

        public final String getENGINE_VERSION() {
            return Directory.ENGINE_VERSION;
        }

        public final String getFORMENGINE_FILE_TEMP() {
            return Directory.FORMENGINE_FILE_TEMP;
        }

        public final String getFORMENGINE_PATH() {
            return Directory.FORMENGINE_PATH;
        }

        public final String getTARIK_SAMPEL_OFFLINE_PATH() {
            return Directory.TARIK_SAMPEL_OFFLINE_PATH;
        }
    }

    static {
        String str = ContextCompat.getExternalFilesDirs(FasihApp.INSTANCE.getContext(), null)[0].getAbsolutePath() + "/BPS/";
        ABSOLUTEPATH = str;
        ABSOLUTEPATHBACKUP = CommonCons.INSTANCE.getABSOLUTE_PATH() + "backup/";
        ABSOLUTEPATHENV = CommonCons.INSTANCE.getABSOLUTE_PATH() + "env/";
        ENGINE_VERSION = CommonCons.INSTANCE.getABSOLUTE_PATH() + "new-capi/engine.json";
        FORMENGINE_FILE_TEMP = "form-gear";
        FORMENGINE_PATH = str + "formengine";
        TARIK_SAMPEL_OFFLINE_PATH = str + "tariksampeloffline";
    }
}

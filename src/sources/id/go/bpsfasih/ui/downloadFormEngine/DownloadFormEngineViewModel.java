package id.go.bpsfasih.ui.downloadFormEngine;

import android.content.Context;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import id.go.bpsfasih.data.local.entities.FormEngineEntity;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import id.go.bpsfasih.data.local.models.FormEngineResponse;
import id.go.bpsfasih.data.repository.NotificationRepositoryImpl;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.helper.FormEngineHelper;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.sync.reqDownload.RDFormgear;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: DownloadFormEngineViewModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010!\u001a\u00020\"H\u0007J\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020\"J\u0006\u0010%\u001a\u00020\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001a\u0010\u001d\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0011¨\u0006&"}, d2 = {"Lid/go/bpsfasih/ui/downloadFormEngine/DownloadFormEngineViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "surveyId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "getContext", "()Landroid/content/Context;", "description", "Landroidx/lifecycle/MutableLiveData;", "getDescription", "()Landroidx/lifecycle/MutableLiveData;", "setDescription", "(Landroidx/lifecycle/MutableLiveData;)V", "formEngineId", "getFormEngineId", "()Ljava/lang/String;", "setFormEngineId", "(Ljava/lang/String;)V", "formEngineLocalVersion", "getFormEngineLocalVersion", "setFormEngineLocalVersion", "formEngineName", "getFormEngineName", "setFormEngineName", "formEngineServerVersion", "getFormEngineServerVersion", "setFormEngineServerVersion", "formEngineUrl", "getFormEngineUrl", "setFormEngineUrl", "getSurveyId", "downloadFormEngine", "", "getFormEngineLocal", "getFormEngineServer", "getFormEngineType", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DownloadFormEngineViewModel extends ViewModel {
    public static final int $stable = 8;
    private final Context context;
    private MutableLiveData<String> description;
    private String formEngineId;
    private String formEngineLocalVersion;
    private String formEngineName;
    private String formEngineServerVersion;
    private String formEngineUrl;
    private final String surveyId;

    public DownloadFormEngineViewModel(Context context, String surveyId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        this.context = context;
        this.surveyId = surveyId;
        this.formEngineUrl = "";
        this.formEngineId = "";
        this.formEngineName = "";
        this.formEngineLocalVersion = "";
        this.formEngineServerVersion = "";
        this.description = new MutableLiveData<>();
        getFormEngineType();
        getFormEngineLocal();
        getFormEngineServer();
        if (this.formEngineLocalVersion.length() == 0) {
            this.description.postValue(this.formEngineName + " belum terdapat pada perangkat");
        } else if (!this.formEngineLocalVersion.equals(this.formEngineServerVersion)) {
            this.description.postValue(this.formEngineName + " yang terdapat pada perangkat anda bukan versi terbaru");
        } else {
            this.description.postValue(this.formEngineName + " yang terdapat pada perangkat anda adalah versi terbaru. Ingin tetap mengunduh?");
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getFormEngineUrl() {
        return this.formEngineUrl;
    }

    public final void setFormEngineUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.formEngineUrl = str;
    }

    public final String getFormEngineId() {
        return this.formEngineId;
    }

    public final void setFormEngineId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.formEngineId = str;
    }

    public final String getFormEngineName() {
        return this.formEngineName;
    }

    public final void setFormEngineName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.formEngineName = str;
    }

    public final String getFormEngineLocalVersion() {
        return this.formEngineLocalVersion;
    }

    public final void setFormEngineLocalVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.formEngineLocalVersion = str;
    }

    public final String getFormEngineServerVersion() {
        return this.formEngineServerVersion;
    }

    public final void setFormEngineServerVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.formEngineServerVersion = str;
    }

    public final MutableLiveData<String> getDescription() {
        return this.description;
    }

    public final void setDescription(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.description = mutableLiveData;
    }

    public final void getFormEngineType() {
        Unit unit = null;
        TemplateValidationEntity templateValidationEntity = (TemplateValidationEntity) BuildersKt__BuildersKt.runBlocking$default(null, new DownloadFormEngineViewModel$getFormEngineType$templateValidasi$1(this, null), 1, null);
        if (templateValidationEntity != null) {
            this.formEngineId = String.valueOf(templateValidationEntity.getFormEngineId());
            String formEngineBrandName = templateValidationEntity.getFormEngineBrandName();
            Intrinsics.checkNotNull(formEngineBrandName);
            this.formEngineName = formEngineBrandName;
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Toast.makeText(this.context, "Belum terdapat data template dan validasi. Harap melakukan sinkronisasi template terlebih dahulu", 1).show();
            Context context = this.context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity");
            ((DownloadFormEngineActivity) context).finish();
        }
    }

    public final void getFormEngineLocal() {
        if (new File(Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + this.formEngineId).exists()) {
            this.formEngineLocalVersion = FormEngineHelper.INSTANCE.getFormEngineVersion(this.formEngineId);
        }
    }

    public final void getFormEngineServer() {
        if (Network.INSTANCE.isOnline(this.context)) {
            Context context = this.context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity");
            ((DownloadFormEngineActivity) context).showProgressBar();
            new NotificationRepositoryImpl().getFormEngine(this.formEngineId, new Function1<FormEngineResponse, Unit>() { // from class: id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineViewModel.getFormEngineServer.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FormEngineResponse formEngineResponse) {
                    invoke2(formEngineResponse);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FormEngineResponse formEngineResponse) {
                    ((DownloadFormEngineActivity) DownloadFormEngineViewModel.this.getContext()).hideProgressBar();
                    if (formEngineResponse != null ? Intrinsics.areEqual((Object) formEngineResponse.getSuccess(), (Object) true) : false) {
                        FormEngineEntity data = formEngineResponse.getData();
                        DownloadFormEngineViewModel downloadFormEngineViewModel = DownloadFormEngineViewModel.this;
                        String linkDownload = data.getLinkDownload();
                        Intrinsics.checkNotNull(linkDownload);
                        downloadFormEngineViewModel.setFormEngineUrl(linkDownload);
                        DownloadFormEngineViewModel downloadFormEngineViewModel2 = DownloadFormEngineViewModel.this;
                        String version = data.getVersion();
                        Intrinsics.checkNotNull(version);
                        downloadFormEngineViewModel2.setFormEngineServerVersion(version);
                        return;
                    }
                    Toast.makeText(DownloadFormEngineViewModel.this.getContext(), "Terjadi kesalahan ketika cek versi form engine", 0).show();
                }
            });
            return;
        }
        Context context2 = this.context;
        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity");
        ((DownloadFormEngineActivity) context2).finish();
        Toast.makeText(this.context, "Anda perlu koneksi internet untuk download formengine", 0).show();
    }

    public final void downloadFormEngine() {
        Context context = this.context;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity");
        ((DownloadFormEngineActivity) context).showProgressBar();
        new RDFormgear(this.formEngineUrl, this.formEngineId, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineViewModel.downloadFormEngine.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                if (z) {
                    ((DownloadFormEngineActivity) DownloadFormEngineViewModel.this.getContext()).hideProgressBar();
                    Toast.makeText(DownloadFormEngineViewModel.this.getContext(), "Sukses download", 0).show();
                    ((DownloadFormEngineActivity) DownloadFormEngineViewModel.this.getContext()).finish();
                } else {
                    ((DownloadFormEngineActivity) DownloadFormEngineViewModel.this.getContext()).hideProgressBar();
                    Toast.makeText(DownloadFormEngineViewModel.this.getContext(), message, 0).show();
                }
            }
        });
    }
}

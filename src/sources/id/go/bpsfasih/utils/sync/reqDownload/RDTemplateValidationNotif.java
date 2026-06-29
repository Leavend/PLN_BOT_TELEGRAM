package id.go.bpsfasih.utils.sync.reqDownload;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity;
import id.go.bpsfasih.data.local.entities.LookupsList;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository;
import id.go.bpsfasih.data.local.repository.TemplateValidationRepository;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.repository.TemplateRepositoryImpl;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.ZipHelper;
import id.go.bpsfasih.utils.tools.Downloader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RDTemplateValidationNotif.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00128\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0002\u0010\rJ\b\u0010\u001f\u001a\u00020\fH\u0002JB\u0010 \u001a\u00020\f28\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u0002JB\u0010!\u001a\u00020\f28\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u0002JB\u0010\"\u001a\u00020\f28\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u0002JB\u0010#\u001a\u00020\f28\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u0002J@\u0010$\u001a\u00020\f26\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(%\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u0002RC\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006&"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDTemplateValidationNotif;", "", "templateValidation", "Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "message", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "(Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;Lkotlin/jvm/functions/Function2;)V", "getCallback", "()Lkotlin/jvm/functions/Function2;", "customDataNew", "Lid/go/bpsfasih/data/local/entities/CustomDataTemplateEntity;", "customTemplateRepo", "Lid/go/bpsfasih/data/local/repository/CustomDataTemplateRepository;", "listLookup", "", "Lid/go/bpsfasih/data/local/entities/LookupsList;", "paramsTemplateVersion", "paramsValidationVersion", "templateValidationPathDownload", "templateValidationRepo", "Lid/go/bpsfasih/data/local/repository/TemplateValidationRepository;", "userId", "getUserId", "()Ljava/lang/String;", "downloadTemplateValidationNotif", "requestCustomDataNotif", "requestListLookupNotif", "saveCustomDataNotif", "saveListLookupNotif", "saveTemplateValidationVersion", "meesage", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDTemplateValidationNotif {
    public static final int $stable = 8;
    private final Function2<String, Boolean, Unit> callback;
    private CustomDataTemplateEntity customDataNew;
    private final CustomDataTemplateRepository customTemplateRepo;
    private List<LookupsList> listLookup;
    private String paramsTemplateVersion;
    private String paramsValidationVersion;
    private final TemplateValidationEntity templateValidation;
    private String templateValidationPathDownload;
    private final TemplateValidationRepository templateValidationRepo;
    private final String userId;

    /* JADX WARN: Multi-variable type inference failed */
    public RDTemplateValidationNotif(TemplateValidationEntity templateValidation, Function2<? super String, ? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(templateValidation, "templateValidation");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.templateValidation = templateValidation;
        this.callback = callback;
        this.paramsTemplateVersion = "templateVersion=";
        this.paramsValidationVersion = "validationVersion=";
        this.templateValidationPathDownload = new Config().BASE_URL() + "/mobile/assignment-sync/api/mobile/template/zip/" + templateValidation.getTemplate_id() + "?" + this.paramsTemplateVersion + templateValidation.getTemplate_version() + "&" + this.paramsValidationVersion + templateValidation.getValidasi_version();
        this.listLookup = new ArrayList();
        this.customTemplateRepo = DataSurvey.CustomTemplate.INSTANCE.getCustomTemplateRepo();
        this.templateValidationRepo = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository();
        this.userId = FasihApp.INSTANCE.getSession().getUserId();
        downloadTemplateValidationNotif();
    }

    public final Function2<String, Boolean, Unit> getCallback() {
        return this.callback;
    }

    public final String getUserId() {
        return this.userId;
    }

    private final void downloadTemplateValidationNotif() {
        Downloader.INSTANCE.downloadTemplateValidationNotif(this.templateValidationPathDownload, this.templateValidation.getTemplate_id(), new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif.downloadTemplateValidationNotif.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String resultTemplateId, boolean z) {
                Intrinsics.checkNotNullParameter(resultTemplateId, "resultTemplateId");
                if (!z) {
                    ZipHelper.INSTANCE.unZip(Directory.INSTANCE.getABSOLUTEPATHTEMPLATEDSIGNER() + RDTemplateValidationNotif.this.templateValidation.getTemplate_id() + File.separator, RDTemplateValidationNotif.this.templateValidation.getTemplate_id() + CommonCons.INSTANCE.getEXTENSION_ZIP(), false);
                    RDTemplateValidationNotif rDTemplateValidationNotif = RDTemplateValidationNotif.this;
                    final RDTemplateValidationNotif rDTemplateValidationNotif2 = RDTemplateValidationNotif.this;
                    rDTemplateValidationNotif.requestCustomDataNotif(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif.downloadTemplateValidationNotif.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                            invoke(str, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String str, boolean z2) {
                            rDTemplateValidationNotif2.getCallback().invoke(str, Boolean.valueOf(z2));
                        }
                    });
                    return;
                }
                RDTemplateValidationNotif.this.getCallback().invoke("Gagal mendownload template terbaru", Boolean.valueOf(z));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestCustomDataNotif(final Function2<? super String, ? super Boolean, Unit> callback) {
        TemplateRepositoryImpl templateRepositoryImpl = new TemplateRepositoryImpl();
        String template_id = this.templateValidation.getTemplate_id();
        String template_version = this.templateValidation.getTemplate_version();
        Intrinsics.checkNotNull(template_version);
        templateRepositoryImpl.getCustomData(template_id, template_version, new Function2<CustomDataTemplateEntity, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif.requestCustomDataNotif.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CustomDataTemplateEntity customDataTemplateEntity, Boolean bool) {
                invoke(customDataTemplateEntity, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CustomDataTemplateEntity customDataTemplateEntity, boolean z) {
                if (!z) {
                    if (customDataTemplateEntity != null) {
                        RDTemplateValidationNotif rDTemplateValidationNotif = RDTemplateValidationNotif.this;
                        customDataTemplateEntity.getIdPrimary();
                        customDataTemplateEntity.setId(rDTemplateValidationNotif.templateValidation.getTemplate_id());
                        customDataTemplateEntity.setIdPrimary(customDataTemplateEntity.getId() + File.separator + rDTemplateValidationNotif.getUserId());
                        customDataTemplateEntity.setUserId(rDTemplateValidationNotif.getUserId());
                        rDTemplateValidationNotif.customDataNew = customDataTemplateEntity.copy((8191 & 1) != 0 ? customDataTemplateEntity.idPrimary : null, (8191 & 2) != 0 ? customDataTemplateEntity.id : null, (8191 & 4) != 0 ? customDataTemplateEntity.userId : null, (8191 & 8) != 0 ? customDataTemplateEntity.data1 : null, (8191 & 16) != 0 ? customDataTemplateEntity.data2 : null, (8191 & 32) != 0 ? customDataTemplateEntity.data3 : null, (8191 & 64) != 0 ? customDataTemplateEntity.data4 : null, (8191 & 128) != 0 ? customDataTemplateEntity.data5 : null, (8191 & 256) != 0 ? customDataTemplateEntity.data6 : null, (8191 & 512) != 0 ? customDataTemplateEntity.data7 : null, (8191 & 1024) != 0 ? customDataTemplateEntity.data8 : null, (8191 & 2048) != 0 ? customDataTemplateEntity.data9 : null, (8191 & 4096) != 0 ? customDataTemplateEntity.data10 : null);
                    }
                    RDTemplateValidationNotif rDTemplateValidationNotif2 = RDTemplateValidationNotif.this;
                    final Function2<String, Boolean, Unit> function2 = callback;
                    rDTemplateValidationNotif2.requestListLookupNotif(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif.requestCustomDataNotif.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                            invoke(str, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String str, boolean z2) {
                            function2.invoke(str, Boolean.valueOf(z2));
                        }
                    });
                    return;
                }
                callback.invoke("Gagal mendownload custom data", true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestListLookupNotif(final Function2<? super String, ? super Boolean, Unit> callback) {
        new TemplateRepositoryImpl().getListLookup(this.templateValidation.getSurvey_id(), new Function2<List<? extends LookupsList>, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif.requestListLookupNotif.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends LookupsList> list, Boolean bool) {
                invoke((List<LookupsList>) list, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(List<LookupsList> list, boolean z) {
                List list2;
                if (!z) {
                    if (list != null) {
                        RDTemplateValidationNotif rDTemplateValidationNotif = RDTemplateValidationNotif.this;
                        for (LookupsList lookupsList : list) {
                            if (lookupsList != null && (list2 = rDTemplateValidationNotif.listLookup) != null) {
                                list2.add(lookupsList);
                            }
                        }
                    }
                    RDTemplateValidationNotif rDTemplateValidationNotif2 = RDTemplateValidationNotif.this;
                    final Function2<String, Boolean, Unit> function2 = callback;
                    rDTemplateValidationNotif2.saveListLookupNotif(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif.requestListLookupNotif.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                            invoke(str, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String str, boolean z2) {
                            function2.invoke(str, Boolean.valueOf(z2));
                        }
                    });
                    return;
                }
                callback.invoke("Gagal mendownload custom data", true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveListLookupNotif(final Function2<? super String, ? super Boolean, Unit> callback) {
        List<LookupsList> list = this.listLookup;
        if (list != null) {
            new RDLookUpNotif(list, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif$saveListLookupNotif$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                    invoke(str, bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(String message, boolean z) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    if (!z) {
                        RDTemplateValidationNotif rDTemplateValidationNotif = this.this$0;
                        final Function2<String, Boolean, Unit> function2 = callback;
                        rDTemplateValidationNotif.saveCustomDataNotif(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif$saveListLookupNotif$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                                invoke(str, bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(String str, boolean z2) {
                                function2.invoke(str, Boolean.valueOf(z2));
                            }
                        });
                        return;
                    }
                    callback.invoke(message, true);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveCustomDataNotif(final Function2<? super String, ? super Boolean, Unit> callback) {
        Unit unit;
        CustomDataTemplateEntity customDataTemplateEntity = this.customDataNew;
        if (customDataTemplateEntity != null) {
            this.customTemplateRepo.insert(customDataTemplateEntity, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif$saveCustomDataNotif$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RDTemplateValidationNotif rDTemplateValidationNotif = this.this$0;
                    final Function2<String, Boolean, Unit> function2 = callback;
                    rDTemplateValidationNotif.saveTemplateValidationVersion(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif$saveCustomDataNotif$1$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                            invoke(str, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String message, boolean z) {
                            Intrinsics.checkNotNullParameter(message, "message");
                            function2.invoke(message, Boolean.valueOf(z));
                        }
                    });
                }
            });
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            callback.invoke("Data1 sampai data10 tidak ditemukan", true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveTemplateValidationVersion(final Function2<? super String, ? super Boolean, Unit> callback) {
        this.templateValidationRepo.insertData(this.templateValidation, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif.saveTemplateValidationVersion.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                callback.invoke("Sukses", false);
            }
        });
    }
}

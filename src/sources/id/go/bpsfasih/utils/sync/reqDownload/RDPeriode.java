package id.go.bpsfasih.utils.sync.reqDownload;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.models.AllSurveyModel;
import id.go.bpsfasih.data.local.models.PeriodeList;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.remote.dto.AllSurveyResponse;
import id.go.bpsfasih.data.remote.dto.PeriodeResponse;
import id.go.bpsfasih.data.repository.PeriodeRepositoryImpl;
import id.go.bpsfasih.data.repository.SurveyRepositoryImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RDPeriode.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012)\u0010\u0004\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\u0010\fJ\b\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0002R4\u0010\u0004\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDPeriode;", "", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "callback", "Lkotlin/Function1;", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlin/ParameterName;", "name", "result", "", "(Lid/go/bpsfasih/data/local/entities/SurveyEntity;Lkotlin/jvm/functions/Function1;)V", "getCallback", "()Lkotlin/jvm/functions/Function1;", "listPeriodeFromApiSurvey", "Lid/go/bpsfasih/data/local/models/PeriodeList;", "getSurvey", "()Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "requestPeriode", "requestSurvey", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDPeriode {
    public static final int $stable = 8;
    private final Function1<List<PeriodeEntityNew>, Unit> callback;
    private List<PeriodeList> listPeriodeFromApiSurvey;
    private final SurveyEntity survey;

    /* JADX WARN: Multi-variable type inference failed */
    public RDPeriode(SurveyEntity survey, Function1<? super List<PeriodeEntityNew>, Unit> callback) {
        Intrinsics.checkNotNullParameter(survey, "survey");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.survey = survey;
        this.callback = callback;
        this.listPeriodeFromApiSurvey = new ArrayList();
        requestSurvey();
    }

    public final SurveyEntity getSurvey() {
        return this.survey;
    }

    public final Function1<List<PeriodeEntityNew>, Unit> getCallback() {
        return this.callback;
    }

    private final void requestSurvey() {
        new SurveyRepositoryImpl().checkSurveys(new Function3<AllSurveyResponse, Boolean, String, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDPeriode.requestSurvey.1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(AllSurveyResponse allSurveyResponse, Boolean bool, String str) {
                invoke(allSurveyResponse, bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(AllSurveyResponse allSurveyResponse, boolean z, String message) {
                Unit unit;
                Unit unit2;
                Intrinsics.checkNotNullParameter(message, "message");
                if (z) {
                    RDPeriode.this.getCallback().invoke(null);
                    return;
                }
                if (allSurveyResponse != null) {
                    RDPeriode rDPeriode = RDPeriode.this;
                    for (AllSurveyModel allSurveyModel : allSurveyResponse.getData()) {
                        if (Intrinsics.areEqual(allSurveyModel.getId(), rDPeriode.getSurvey().getId())) {
                            List<PeriodeList> listPeriode = allSurveyModel.getListPeriode();
                            if (listPeriode != null) {
                                rDPeriode.listPeriodeFromApiSurvey.addAll(listPeriode);
                                rDPeriode.requestPeriode();
                                unit2 = Unit.INSTANCE;
                            } else {
                                unit2 = null;
                            }
                            if (unit2 == null) {
                                rDPeriode.getCallback().invoke(null);
                            }
                            unit = Unit.INSTANCE;
                        }
                    }
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
                unit = null;
                if (unit == null) {
                    RDPeriode.this.getCallback().invoke(null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestPeriode() {
        new PeriodeRepositoryImpl().getPeriode(this.survey.getId(), new Function1<PeriodeResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDPeriode.requestPeriode.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PeriodeResponse periodeResponse) {
                invoke2(periodeResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PeriodeResponse periodeResponse) {
                if (periodeResponse != null) {
                    List<PeriodeEntityNew> data = periodeResponse.getData();
                    RDPeriode rDPeriode = RDPeriode.this;
                    for (PeriodeEntityNew periodeEntityNew : data) {
                        for (PeriodeList periodeList : rDPeriode.listPeriodeFromApiSurvey) {
                            if (Intrinsics.areEqual(periodeList != null ? periodeList.getId() : null, periodeEntityNew.getId())) {
                                List<UserRole> listUserRole = periodeList != null ? periodeList.getListUserRole() : null;
                                for (PeriodeList periodeList2 : rDPeriode.listPeriodeFromApiSurvey) {
                                    if (Intrinsics.areEqual(periodeList2 != null ? periodeList2.getId() : null, periodeEntityNew.getId())) {
                                        String surveyPeriodeRoleUserId = periodeList2 != null ? periodeList2.getSurveyPeriodeRoleUserId() : null;
                                        for (PeriodeList periodeList3 : rDPeriode.listPeriodeFromApiSurvey) {
                                            if (Intrinsics.areEqual(periodeList3 != null ? periodeList3.getId() : null, periodeEntityNew.getId())) {
                                                List<String> listSmallestRegionFullCode = periodeList3 != null ? periodeList3.getListSmallestRegionFullCode() : null;
                                                periodeEntityNew.setSurvey(rDPeriode.getSurvey());
                                                periodeEntityNew.setRole(listUserRole);
                                                periodeEntityNew.setUserIdPeriode(FasihApp.INSTANCE.getSession().getUserId());
                                                periodeEntityNew.setSurveyPeriodeRoleUserId(surveyPeriodeRoleUserId);
                                                periodeEntityNew.setListSmallestRegionFullCode(listSmallestRegionFullCode);
                                                periodeEntityNew.setPrimaryId(periodeEntityNew.getId() + File.separator + FasihApp.INSTANCE.getSession().getUserId());
                                            }
                                        }
                                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                    }
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                    RDPeriode.this.getCallback().invoke(CollectionsKt.toMutableList((Collection) periodeResponse.getData()));
                    return;
                }
                RDPeriode.this.getCallback().invoke(null);
            }
        });
    }
}

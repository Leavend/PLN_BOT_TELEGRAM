package id.go.bpsfasih.utils.sync.reqDownload;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.mapper.SurveyMapperKt;
import id.go.bpsfasih.data.remote.dto.SurveyDto;
import id.go.bpsfasih.data.remote.dto.SurveyResponse;
import id.go.bpsfasih.data.repository.SurveyRepositoryImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RDSurvey.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B0\u0012)\u0010\u0002\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\u0002\u0010\nJ\b\u0010\u0012\u001a\u00020\tH\u0002J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\tH\u0002R1\u0010\u0002\u001a%\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDSurvey;", "", "callback", "Lkotlin/Function1;", "", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "Lkotlin/ParameterName;", "name", "data", "", "(Lkotlin/jvm/functions/Function1;)V", "surveyList", "", "Lid/go/bpsfasih/data/remote/dto/SurveyDto;", "getSurveyList", "()Ljava/util/List;", "setSurveyList", "(Ljava/util/List;)V", "handleError", "handleResponseSurvey", "response", "Lid/go/bpsfasih/data/remote/dto/SurveyResponse;", "requestSurvey", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDSurvey {
    public static final int $stable = 8;
    private final Function1<List<SurveyEntity>, Unit> callback;
    private List<SurveyDto> surveyList;

    /* JADX WARN: Multi-variable type inference failed */
    public RDSurvey(Function1<? super List<SurveyEntity>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
        this.surveyList = new ArrayList();
        requestSurvey();
    }

    public final List<SurveyDto> getSurveyList() {
        return this.surveyList;
    }

    public final void setSurveyList(List<SurveyDto> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.surveyList = list;
    }

    private final void requestSurvey() {
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDSurvey$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RDSurvey.requestSurvey$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestSurvey$lambda$0(final RDSurvey this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new SurveyRepositoryImpl().getNewSurvey(new Function1<SurveyResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDSurvey$requestSurvey$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SurveyResponse surveyResponse) {
                invoke2(surveyResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SurveyResponse surveyResponse) {
                if (surveyResponse != null) {
                    this.this$0.handleResponseSurvey(surveyResponse);
                } else {
                    this.this$0.handleError();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleResponseSurvey(SurveyResponse response) {
        this.surveyList.addAll(response.getData());
        List<SurveyDto> list = this.surveyList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(SurveyMapperKt.toEntity(SurveyMapperKt.toDomain((SurveyDto) it.next())));
        }
        this.callback.invoke(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleError() {
        this.callback.invoke(null);
    }
}

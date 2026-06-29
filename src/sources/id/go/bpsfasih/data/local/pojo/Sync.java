package id.go.bpsfasih.data.local.pojo;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import kotlin.Metadata;

/* compiled from: Sync.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/data/local/pojo/Sync;", "", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "periode", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "assignment", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "(Lid/go/bpsfasih/data/local/entities/SurveyEntity;Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;Lid/go/bpsfasih/data/local/entities/AssignmentEntity;)V", "getAssignment", "()Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "getPeriode", "()Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "getSurvey", "()Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Sync {
    public static final int $stable = 8;
    private final AssignmentEntity assignment;
    private final PeriodeEntityNew periode;
    private final SurveyEntity survey;

    public Sync(SurveyEntity surveyEntity, PeriodeEntityNew periodeEntityNew, AssignmentEntity assignmentEntity) {
        this.survey = surveyEntity;
        this.periode = periodeEntityNew;
        this.assignment = assignmentEntity;
    }

    public final SurveyEntity getSurvey() {
        return this.survey;
    }

    public final PeriodeEntityNew getPeriode() {
        return this.periode;
    }

    public final AssignmentEntity getAssignment() {
        return this.assignment;
    }
}

package id.go.bpsfasih.utils.helper;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import id.go.bpsfasih.domain.models.EmptyPrincipalLogErrorModel;
import id.go.bpsfasih.domain.models.FormEngineLogErrorModel;
import id.go.bpsfasih.utils.helper.FirestoreHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FirestoreHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/FirestoreHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FirestoreHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: FirestoreHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/utils/helper/FirestoreHelper$Companion;", "", "()V", "send", "", "collection", "", "data", "sendEmptyPrincipalLogError", "Lid/go/bpsfasih/domain/models/EmptyPrincipalLogErrorModel;", "sendFasihEngineLogError", "Lid/go/bpsfasih/domain/models/FormEngineLogErrorModel;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void send(String collection, Object data) {
            Intrinsics.checkNotNullParameter(collection, "collection");
            Intrinsics.checkNotNullParameter(data, "data");
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance()");
            Task<DocumentReference> taskAdd = firebaseFirestore.collection(collection).add(data);
            final FirestoreHelper$Companion$send$1 firestoreHelper$Companion$send$1 = new Function1<DocumentReference, Unit>() { // from class: id.go.bpsfasih.utils.helper.FirestoreHelper$Companion$send$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DocumentReference documentReference) {
                    invoke2(documentReference);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DocumentReference documentReference) {
                    Log.d("FOUR", "DocumentSnapshot added with ID: " + documentReference.getId());
                }
            };
            taskAdd.addOnSuccessListener(new OnSuccessListener() { // from class: id.go.bpsfasih.utils.helper.FirestoreHelper$Companion$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    FirestoreHelper.Companion.send$lambda$0(firestoreHelper$Companion$send$1, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: id.go.bpsfasih.utils.helper.FirestoreHelper$Companion$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    FirestoreHelper.Companion.send$lambda$1(exc);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void send$lambda$0(Function1 tmp0, Object obj) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            tmp0.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void send$lambda$1(Exception e) {
            Intrinsics.checkNotNullParameter(e, "e");
            Log.w("FOUR", "Error adding document", e);
        }

        public final void sendFasihEngineLogError(FormEngineLogErrorModel data) {
            Intrinsics.checkNotNullParameter(data, "data");
            send("form-engine-log-error", data);
        }

        public final void sendEmptyPrincipalLogError(EmptyPrincipalLogErrorModel data) {
            Intrinsics.checkNotNullParameter(data, "data");
            send("empty-principal-log-error", data);
        }
    }
}

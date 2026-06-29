package id.go.bpsfasih.ui.unduhChangeMode;

import android.app.Activity;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.R;
import id.go.bpsfasih.domain.models.UnduhChangeModeModel;
import id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1;
import id.go.bpsfasih.utils.helper.FileHelper;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UnduhChangeModeAdapter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1", f = "UnduhChangeModeAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UnduhChangeModeAdapter$onBindViewHolder$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ UnduhChangeModeModel $current;
    int label;
    final /* synthetic */ UnduhChangeModeAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UnduhChangeModeAdapter$onBindViewHolder$1$1(UnduhChangeModeModel unduhChangeModeModel, UnduhChangeModeAdapter unduhChangeModeAdapter, Continuation<? super UnduhChangeModeAdapter$onBindViewHolder$1$1> continuation) {
        super(2, continuation);
        this.$current = unduhChangeModeModel;
        this.this$0 = unduhChangeModeAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UnduhChangeModeAdapter$onBindViewHolder$1$1(this.$current, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UnduhChangeModeAdapter$onBindViewHolder$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0084  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r10.label
            if (r0 != 0) goto La1
            kotlin.ResultKt.throwOnFailure(r11)
            id.go.bpsfasih.domain.models.SyncAnswerPartial r11 = new id.go.bpsfasih.domain.models.SyncAnswerPartial
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r0 = r10.$current
            java.lang.String r2 = r0.getSurveyId()
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r0 = r10.$current
            java.lang.String r3 = r0.getPeriodeId()
            r4 = 0
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r0 = r10.$current
            java.lang.String r0 = r0.getBasePath()
            java.lang.String r1 = ".zip"
            java.lang.String r5 = ".7z"
            r6 = 2
            r7 = 0
            r8 = 0
            if (r0 == 0) goto L51
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r0 = r10.$current
            java.lang.String r0 = r0.getBasePath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r0, r5, r7, r6, r8)
            if (r0 != 0) goto L51
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r0 = r10.$current
            java.lang.String r0 = r0.getBasePath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r0, r1, r7, r6, r8)
            if (r0 != 0) goto L51
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r0 = r10.$current
            java.lang.String r0 = r0.getId()
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            goto L52
        L51:
            r0 = r8
        L52:
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r9 = r10.$current
            java.lang.String r9 = r9.getBasePath()
            if (r9 == 0) goto L84
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r9 = r10.$current
            java.lang.String r9 = r9.getBasePath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            boolean r5 = kotlin.text.StringsKt.endsWith$default(r9, r5, r7, r6, r8)
            if (r5 != 0) goto L78
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r5 = r10.$current
            java.lang.String r5 = r5.getBasePath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            boolean r1 = kotlin.text.StringsKt.endsWith$default(r5, r1, r7, r6, r8)
            if (r1 == 0) goto L84
        L78:
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r1 = r10.$current
            java.lang.String r1 = r1.getId()
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            r6 = r1
            goto L85
        L84:
            r6 = r8
        L85:
            r7 = 0
            r8 = 36
            r9 = 0
            r1 = r11
            r5 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode r0 = new id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode
            id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1$1 r1 = new id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1$1
            id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter r2 = r10.this$0
            id.go.bpsfasih.domain.models.UnduhChangeModeModel r3 = r10.$current
            r1.<init>(r2, r3)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r0.<init>(r11, r1)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        La1:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: UnduhChangeModeAdapter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "message", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function2<String, Boolean, Unit> {
        final /* synthetic */ UnduhChangeModeModel $current;
        final /* synthetic */ UnduhChangeModeAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(UnduhChangeModeAdapter unduhChangeModeAdapter, UnduhChangeModeModel unduhChangeModeModel) {
            super(2);
            this.this$0 = unduhChangeModeAdapter;
            this.$current = unduhChangeModeModel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(View view) {
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) throws IOException {
            invoke(str, bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(String str, boolean z) throws IOException {
            Activity activity = this.this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity");
            ((UnduhChangeModeActivity) activity).hideProgressBar();
            if (!z) {
                FileHelper.INSTANCE.insertListDownloaded(this.$current.getIdPrimary(), new C02551(this.this$0));
                return;
            }
            Activity activity2 = this.this$0.getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity");
            UnduhChangeModeActivity unduhChangeModeActivity = (UnduhChangeModeActivity) activity2;
            int i = R.color.error30;
            int i2 = R.color.error30;
            unduhChangeModeActivity.showAlertDialogColor("Gagal", Integer.valueOf(i), str, Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UnduhChangeModeAdapter$onBindViewHolder$1$1.AnonymousClass1.invoke$lambda$0(view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
        }

        /* compiled from: UnduhChangeModeAdapter.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "message", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        /* renamed from: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02551 extends Lambda implements Function2<Boolean, String, Unit> {
            final /* synthetic */ UnduhChangeModeAdapter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02551(UnduhChangeModeAdapter unduhChangeModeAdapter) {
                super(2);
                this.this$0 = unduhChangeModeAdapter;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$1(View view) {
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                if (!z) {
                    Activity activity = this.this$0.getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity");
                    UnduhChangeModeActivity unduhChangeModeActivity = (UnduhChangeModeActivity) activity;
                    int i = R.color.success30;
                    int i2 = R.color.success30;
                    final UnduhChangeModeAdapter unduhChangeModeAdapter = this.this$0;
                    unduhChangeModeActivity.showAlertDialogColor("Sukses", Integer.valueOf(i), "Sukses mengunduh data", Integer.valueOf(i2), null, "Selesai", Integer.valueOf(R.drawable.layout_button_success), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1$1$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UnduhChangeModeAdapter$onBindViewHolder$1$1.AnonymousClass1.C02551.invoke$lambda$0(unduhChangeModeAdapter, view);
                        }
                    }, null, null, null, Integer.valueOf(R.color.success30), true);
                    return;
                }
                Activity activity2 = this.this$0.getActivity();
                Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity");
                int i3 = R.color.error30;
                int i4 = R.color.error30;
                ((UnduhChangeModeActivity) activity2).showAlertDialogColor("Gagal", Integer.valueOf(i3), message, Integer.valueOf(i4), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeAdapter$onBindViewHolder$1$1$1$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UnduhChangeModeAdapter$onBindViewHolder$1$1.AnonymousClass1.C02551.invoke$lambda$1(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$0(UnduhChangeModeAdapter this$0, View view) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                this$0.getActivity().finish();
                this$0.getActivity().startActivity(this$0.getActivity().getIntent());
            }
        }
    }
}

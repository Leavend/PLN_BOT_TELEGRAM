package id.go.bpsfasih.ui.assignmentList;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.nambimobile.widgets.efab.FabOption;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.TarikSampleEntity;
import id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssignmentListActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "assignmentRegion", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class AssignmentListActivity$loadContent$1 extends Lambda implements Function1<AssignmentRegionEntity, Unit> {
    final /* synthetic */ Ref.ObjectRef<Boolean> $isListingDone;
    final /* synthetic */ AssignmentListActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssignmentListActivity$loadContent$1(AssignmentListActivity assignmentListActivity, Ref.ObjectRef<Boolean> objectRef) {
        super(1);
        this.this$0 = assignmentListActivity;
        this.$isListingDone = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionEntity assignmentRegionEntity) {
        invoke2(assignmentRegionEntity);
        return Unit.INSTANCE;
    }

    /* compiled from: AssignmentListActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1$1", f = "AssignmentListActivity.kt", i = {}, l = {389}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<Boolean> $isListingDone;
        int label;
        final /* synthetic */ AssignmentListActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<Boolean> objectRef, AssignmentListActivity assignmentListActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$isListingDone = objectRef;
            this.this$0 = assignmentListActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$isListingDone, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Boolean] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objWithContext;
            AssignmentUpdateListingViewModel assignmentUpdateListingViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AssignmentListActivity$loadContent$1$1$offlineSampling$1(this.this$0, null), this);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objWithContext = obj;
            }
            if (((TarikSampleEntity) objWithContext) != null) {
                this.$isListingDone.element = Boxing.boxBoolean(true);
                this.this$0.setTarikSampleDone(Boxing.boxBoolean(true));
                ((FabOption) this.this$0._$_findCachedViewById(R.id.fab_regionDone)).setFabOptionEnabled(true);
                Boolean bool = this.$isListingDone.element;
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue()) {
                    final AssignmentListActivity assignmentListActivity = this.this$0;
                    assignmentListActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            AssignmentListActivity$loadContent$1.AnonymousClass1.invokeSuspend$lambda$0(assignmentListActivity);
                        }
                    });
                } else {
                    final AssignmentListActivity assignmentListActivity2 = this.this$0;
                    assignmentListActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            AssignmentListActivity$loadContent$1.AnonymousClass1.invokeSuspend$lambda$1(assignmentListActivity2);
                        }
                    });
                    Boolean bool2 = this.this$0.mIsPencacah;
                    Intrinsics.checkNotNull(bool2);
                    if (bool2.booleanValue() && this.this$0.mCanAddSample) {
                        ((FabOption) this.this$0._$_findCachedViewById(R.id.fab_addAssignment)).setFabOptionEnabled(true);
                    } else {
                        ((FabOption) this.this$0._$_findCachedViewById(R.id.fab_addAssignment)).setFabOptionEnabled(false);
                    }
                }
                ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).getSettings().setJavaScriptEnabled(true);
                ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccess(true);
                ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccessFromFileURLs(true);
                ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).clearCache(true);
                ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity.loadContent.1.1.3
                    @Override // android.webkit.WebViewClient
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
                AssignmentListActivity assignmentListActivity3 = this.this$0;
                WebView wv_main = (WebView) this.this$0._$_findCachedViewById(R.id.wv_main);
                Intrinsics.checkNotNullExpressionValue(wv_main, "wv_main");
                AssignmentListActivity assignmentListActivity4 = this.this$0;
                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = assignmentListActivity4.assignmentUpdateListingViewModel;
                if (assignmentUpdateListingViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
                    assignmentUpdateListingViewModel = null;
                } else {
                    assignmentUpdateListingViewModel = assignmentUpdateListingViewModel2;
                }
                String str = this.this$0.mRegionId;
                Intrinsics.checkNotNull(str);
                String str2 = this.this$0.mPeriodId;
                Intrinsics.checkNotNull(str2);
                String str3 = this.this$0.mSurveyId;
                Intrinsics.checkNotNull(str3);
                String str4 = this.this$0.mRegionFullCode;
                if (str4 == null) {
                    str4 = "";
                }
                String str5 = str4;
                String safeRegionName = this.this$0.getSafeRegionName();
                String str6 = this.this$0.mTemplateId;
                Intrinsics.checkNotNull(str6);
                Boolean bool3 = this.this$0.mIsPencacah;
                Intrinsics.checkNotNull(bool3);
                boolean zBooleanValue = bool3.booleanValue();
                Boolean bool4 = this.$isListingDone.element;
                Intrinsics.checkNotNull(bool4);
                assignmentListActivity3.setJsi$app_release(new JavaScriptInterfaceAssignment(wv_main, assignmentListActivity4, assignmentUpdateListingViewModel, str, str2, str3, str5, safeRegionName, str6, zBooleanValue, null, bool4.booleanValue()));
                WebView webView = (WebView) this.this$0._$_findCachedViewById(R.id.wv_main);
                JavaScriptInterfaceAssignment jsi = this.this$0.getJsi();
                Intrinsics.checkNotNull(jsi);
                webView.addJavascriptInterface(jsi, "Android");
                WebView webView2 = (WebView) this.this$0._$_findCachedViewById(R.id.wv_main);
                final AssignmentListActivity assignmentListActivity5 = this.this$0;
                webView2.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity.loadContent.1.1.4
                    @Override // android.webkit.WebViewClient
                    public void onPageFinished(WebView view, String url) {
                        Intrinsics.checkNotNullParameter(view, "view");
                        Intrinsics.checkNotNullParameter(url, "url");
                        super.onPageFinished(view, url);
                        JavaScriptInterfaceAssignment jsi2 = assignmentListActivity5.getJsi();
                        if (jsi2 != null) {
                            jsi2.execute(null);
                        }
                    }
                });
                ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).loadUrl("file:///android_asset/assignment_list.html");
            } else {
                int i2 = R.color.error30;
                int i3 = R.color.error30;
                final AssignmentListActivity assignmentListActivity6 = this.this$0;
                this.this$0.showAlertDialogColor("Gagal", Boxing.boxInt(i2), "Terdapat data yang gagal di unduh", Boxing.boxInt(i3), null, "Tutup", Boxing.boxInt(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1$1$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        assignmentListActivity6.finish();
                    }
                }, null, null, null, Boxing.boxInt(R.color.error30), true);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(AssignmentListActivity assignmentListActivity) {
            ((FabOption) assignmentListActivity._$_findCachedViewById(R.id.fab_addAssignment)).setFabOptionEnabled(false);
            ((FabOption) assignmentListActivity._$_findCachedViewById(R.id.fab_regionDone)).setFabOptionIcon(assignmentListActivity.getDrawable(R.drawable.baseline_undo_24));
            ((FabOption) assignmentListActivity._$_findCachedViewById(R.id.fab_regionDone)).getLabel().setLabelText("Batal tandai wilayah selesai");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(AssignmentListActivity assignmentListActivity) {
            ((FabOption) assignmentListActivity._$_findCachedViewById(R.id.fab_regionDone)).setFabOptionIcon(assignmentListActivity.getDrawable(R.drawable.baseline_check_24));
            ((FabOption) assignmentListActivity._$_findCachedViewById(R.id.fab_regionDone)).getLabel().setLabelText("Tandai wilayah telah selesai");
        }
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.lang.Boolean] */
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(AssignmentRegionEntity assignmentRegionEntity) {
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel;
        if (assignmentRegionEntity == null) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), null, null, new AnonymousClass1(this.$isListingDone, this.this$0, null), 3, null);
            return;
        }
        this.$isListingDone.element = assignmentRegionEntity.getDone_listing();
        this.this$0.setTarikSampleDone(assignmentRegionEntity.getDone_tarik_sample());
        ((FabOption) this.this$0._$_findCachedViewById(R.id.fab_regionDone)).setFabOptionEnabled(true);
        Boolean bool = this.$isListingDone.element;
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            final AssignmentListActivity assignmentListActivity = this.this$0;
            assignmentListActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentListActivity$loadContent$1.invoke$lambda$0(assignmentListActivity);
                }
            });
        } else {
            final AssignmentListActivity assignmentListActivity2 = this.this$0;
            assignmentListActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentListActivity$loadContent$1.invoke$lambda$1(assignmentListActivity2);
                }
            });
            Boolean bool2 = this.this$0.mIsPencacah;
            Intrinsics.checkNotNull(bool2);
            if (bool2.booleanValue() && this.this$0.mCanAddSample) {
                ((FabOption) this.this$0._$_findCachedViewById(R.id.fab_addAssignment)).setFabOptionEnabled(true);
            } else {
                ((FabOption) this.this$0._$_findCachedViewById(R.id.fab_addAssignment)).setFabOptionEnabled(false);
            }
        }
        ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).getSettings().setJavaScriptEnabled(true);
        ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccess(true);
        ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccessFromFileURLs(true);
        ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).clearCache(true);
        ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1.4
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        AssignmentListActivity assignmentListActivity3 = this.this$0;
        WebView wv_main = (WebView) this.this$0._$_findCachedViewById(R.id.wv_main);
        Intrinsics.checkNotNullExpressionValue(wv_main, "wv_main");
        AssignmentListActivity assignmentListActivity4 = this.this$0;
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = assignmentListActivity4.assignmentUpdateListingViewModel;
        if (assignmentUpdateListingViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
            assignmentUpdateListingViewModel = null;
        } else {
            assignmentUpdateListingViewModel = assignmentUpdateListingViewModel2;
        }
        String str = this.this$0.mRegionId;
        Intrinsics.checkNotNull(str);
        String str2 = this.this$0.mPeriodId;
        Intrinsics.checkNotNull(str2);
        String str3 = this.this$0.mSurveyId;
        Intrinsics.checkNotNull(str3);
        String str4 = this.this$0.mRegionFullCode;
        if (str4 == null) {
            str4 = "";
        }
        String str5 = str4;
        String safeRegionName = this.this$0.getSafeRegionName();
        String str6 = this.this$0.mTemplateId;
        Intrinsics.checkNotNull(str6);
        Boolean bool3 = this.this$0.mIsPencacah;
        Intrinsics.checkNotNull(bool3);
        boolean zBooleanValue = bool3.booleanValue();
        Boolean bool4 = this.$isListingDone.element;
        Intrinsics.checkNotNull(bool4);
        assignmentListActivity3.setJsi$app_release(new JavaScriptInterfaceAssignment(wv_main, assignmentListActivity4, assignmentUpdateListingViewModel, str, str2, str3, str5, safeRegionName, str6, zBooleanValue, null, bool4.booleanValue()));
        WebView webView = (WebView) this.this$0._$_findCachedViewById(R.id.wv_main);
        JavaScriptInterfaceAssignment jsi = this.this$0.getJsi();
        Intrinsics.checkNotNull(jsi);
        webView.addJavascriptInterface(jsi, "Android");
        WebView webView2 = (WebView) this.this$0._$_findCachedViewById(R.id.wv_main);
        final AssignmentListActivity assignmentListActivity5 = this.this$0;
        webView2.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$loadContent$1.5
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                super.onPageFinished(view, url);
                JavaScriptInterfaceAssignment jsi2 = assignmentListActivity5.getJsi();
                if (jsi2 != null) {
                    jsi2.execute(null);
                }
            }
        });
        ((WebView) this.this$0._$_findCachedViewById(R.id.wv_main)).loadUrl("file:///android_asset/assignment_list.html");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(AssignmentListActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((FabOption) this$0._$_findCachedViewById(R.id.fab_addAssignment)).setFabOptionEnabled(false);
        ((FabOption) this$0._$_findCachedViewById(R.id.fab_regionDone)).setFabOptionIcon(this$0.getDrawable(R.drawable.baseline_undo_24));
        ((FabOption) this$0._$_findCachedViewById(R.id.fab_regionDone)).getLabel().setLabelText("Batal tandai wilayah selesai");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(AssignmentListActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((FabOption) this$0._$_findCachedViewById(R.id.fab_regionDone)).setFabOptionIcon(this$0.getDrawable(R.drawable.baseline_check_24));
        ((FabOption) this$0._$_findCachedViewById(R.id.fab_regionDone)).getLabel().setLabelText("Tandai wilayah telah selesai");
    }
}

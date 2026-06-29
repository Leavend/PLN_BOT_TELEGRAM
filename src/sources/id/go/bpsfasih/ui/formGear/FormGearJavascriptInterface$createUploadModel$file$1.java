package id.go.bpsfasih.ui.formGear;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: FormGearJavascriptInterface.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class FormGearJavascriptInterface$createUploadModel$file$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ FormGearJavascriptInterface this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FormGearJavascriptInterface$createUploadModel$file$1(FormGearJavascriptInterface formGearJavascriptInterface) {
        super(0);
        this.this$0 = formGearJavascriptInterface;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        FormGearActivity formGearActivity = this.this$0.activity;
        final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
        formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$createUploadModel$file$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface$createUploadModel$file$1.invoke$lambda$0(formGearJavascriptInterface);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.showProgressBar("Compressing Assignment");
    }
}

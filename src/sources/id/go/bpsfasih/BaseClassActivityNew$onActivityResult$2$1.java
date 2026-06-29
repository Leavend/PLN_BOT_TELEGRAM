package id.go.bpsfasih;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.utils.helper.CameraHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BaseClassActivityNew.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.BaseClassActivityNew$onActivityResult$2$1", f = "BaseClassActivityNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class BaseClassActivityNew$onActivityResult$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $selectedFile;
    int label;
    final /* synthetic */ BaseClassActivityNew this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BaseClassActivityNew$onActivityResult$2$1(BaseClassActivityNew baseClassActivityNew, File file, Continuation<? super BaseClassActivityNew$onActivityResult$2$1> continuation) {
        super(2, continuation);
        this.this$0 = baseClassActivityNew;
        this.$selectedFile = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseClassActivityNew$onActivityResult$2$1(this.this$0, this.$selectedFile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseClassActivityNew$onActivityResult$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FileHelper.Companion companion = FileHelper.INSTANCE;
        Context applicationContext = this.this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        String path = this.$selectedFile.getPath();
        final BaseClassActivityNew baseClassActivityNew = this.this$0;
        companion.compressImage(applicationContext, path, new Function2<Boolean, File, Unit>() { // from class: id.go.bpsfasih.BaseClassActivityNew$onActivityResult$2$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, File file) {
                invoke(bool.booleanValue(), file);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, File file) {
                if (!z || file == null) {
                    baseClassActivityNew.showError("Failed to compress picture!");
                    return;
                }
                baseClassActivityNew.compressedImage = file;
                CameraHelper.OnCameraOrGalleryListener onCameraOrGalleryListener = baseClassActivityNew.mCameraOrGalleryListener;
                if (onCameraOrGalleryListener != null) {
                    File file2 = baseClassActivityNew.compressedImage;
                    String fileNameFormGear = baseClassActivityNew.getFileNameFormGear();
                    Intrinsics.checkNotNull(fileNameFormGear);
                    onCameraOrGalleryListener.onCameraResultNew(null, file2, fileNameFormGear);
                }
            }
        });
        return Unit.INSTANCE;
    }
}

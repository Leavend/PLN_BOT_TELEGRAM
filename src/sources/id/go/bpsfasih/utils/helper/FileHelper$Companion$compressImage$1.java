package id.go.bpsfasih.utils.helper;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.zelory.compressor.Compressor;
import id.zelory.compressor.constraint.Compression;
import id.zelory.compressor.constraint.FormatConstraintKt;
import id.zelory.compressor.constraint.QualityConstraintKt;
import id.zelory.compressor.constraint.ResolutionConstraintKt;
import java.io.File;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FileHelper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.helper.FileHelper$Companion$compressImage$1", f = "FileHelper.kt", i = {}, l = {375}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FileHelper$Companion$compressImage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<Boolean, File, Unit> $callBack;
    final /* synthetic */ Context $context;
    final /* synthetic */ Ref.ObjectRef<Integer> $height;
    final /* synthetic */ Ref.ObjectRef<Integer> $quality;
    final /* synthetic */ File $sourceFile;
    final /* synthetic */ Ref.ObjectRef<Integer> $width;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FileHelper$Companion$compressImage$1(Context context, File file, Function2<? super Boolean, ? super File, Unit> function2, Ref.ObjectRef<Integer> objectRef, Ref.ObjectRef<Integer> objectRef2, Ref.ObjectRef<Integer> objectRef3, Continuation<? super FileHelper$Companion$compressImage$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$sourceFile = file;
        this.$callBack = function2;
        this.$width = objectRef;
        this.$height = objectRef2;
        this.$quality = objectRef3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileHelper$Companion$compressImage$1(this.$context, this.$sourceFile, this.$callBack, this.$width, this.$height, this.$quality, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileHelper$Companion$compressImage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Compressor compressor = Compressor.INSTANCE;
            Context context = this.$context;
            File file = this.$sourceFile;
            final Ref.ObjectRef<Integer> objectRef = this.$width;
            final Ref.ObjectRef<Integer> objectRef2 = this.$height;
            final Ref.ObjectRef<Integer> objectRef3 = this.$quality;
            this.label = 1;
            obj = Compressor.compress$default(compressor, context, file, null, new Function1<Compression, Unit>() { // from class: id.go.bpsfasih.utils.helper.FileHelper$Companion$compressImage$1$compressedImageFile$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Compression compression) {
                    invoke2(compression);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Compression compress) {
                    Intrinsics.checkNotNullParameter(compress, "$this$compress");
                    Integer num = objectRef.element;
                    Intrinsics.checkNotNull(num);
                    int iIntValue = num.intValue();
                    Integer num2 = objectRef2.element;
                    Intrinsics.checkNotNull(num2);
                    ResolutionConstraintKt.resolution(compress, iIntValue, num2.intValue());
                    Integer num3 = objectRef3.element;
                    Intrinsics.checkNotNull(num3);
                    QualityConstraintKt.quality(compress, num3.intValue());
                    FormatConstraintKt.format(compress, Bitmap.CompressFormat.JPEG);
                }
            }, this, 4, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$callBack.invoke(Boxing.boxBoolean(true), (File) obj);
        return Unit.INSTANCE;
    }
}

package id.go.bpsfasih.calculator;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.lingala.zip4j.util.InternalZipConstants;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: CalculatorDialog.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010#\u001a\u00020$H\u0002J\u0006\u0010%\u001a\u00020$J\u0012\u0010&\u001a\u00020$2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\u0006\u0010)\u001a\u00020$J\u0006\u0010*\u001a\u00020$J\u000e\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\u0005J\u0006\u0010-\u001a\u00020$J\u000e\u0010.\u001a\u00020$2\u0006\u0010,\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012¨\u0006/"}, d2 = {"Lid/go/bpsfasih/calculator/CalculatorDialog;", "Landroid/app/Dialog;", "activity", "Landroid/app/Activity;", "initResult", "", "callback", "Lid/go/bpsfasih/calculator/CalculatorCallback;", "(Landroid/app/Activity;Ljava/lang/String;Lid/go/bpsfasih/calculator/CalculatorCallback;)V", "getActivity", "()Landroid/app/Activity;", "getInitResult", "()Ljava/lang/String;", "lastDot", "", "getLastDot", "()Z", "setLastDot", "(Z)V", "lastNumaric", "getLastNumaric", "setLastNumaric", "lastResult", "getLastResult", "setLastResult", "(Ljava/lang/String;)V", "outputTextView", "Landroid/widget/TextView;", "getOutputTextView", "()Landroid/widget/TextView;", "setOutputTextView", "(Landroid/widget/TextView;)V", "stateError", "getStateError", "setStateError", "copyTextToClipboard", "", "onClear", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDecimalPoint", "onDelete", "onDigit", "str", "onEqual", "onOperator", "calculator-simple_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CalculatorDialog extends Dialog {
    private final Activity activity;
    private CalculatorCallback callback;
    private final String initResult;
    private boolean lastDot;
    private boolean lastNumaric;
    private String lastResult;
    public TextView outputTextView;
    private boolean stateError;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CalculatorDialog(Activity activity, String str, CalculatorCallback callback) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.activity = activity;
        this.initResult = str;
        this.lastResult = str;
        this.callback = callback;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final String getInitResult() {
        return this.initResult;
    }

    public final TextView getOutputTextView() {
        TextView textView = this.outputTextView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("outputTextView");
        return null;
    }

    public final void setOutputTextView(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.outputTextView = textView;
    }

    public final boolean getLastNumaric() {
        return this.lastNumaric;
    }

    public final void setLastNumaric(boolean z) {
        this.lastNumaric = z;
    }

    public final boolean getStateError() {
        return this.stateError;
    }

    public final void setStateError(boolean z) {
        this.stateError = z;
    }

    public final boolean getLastDot() {
        return this.lastDot;
    }

    public final void setLastDot(boolean z) {
        this.lastDot = z;
    }

    public final String getLastResult() {
        return this.lastResult;
    }

    public final void setLastResult(String str) {
        this.lastResult = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_main);
        View viewFindViewById = findViewById(R.id.txtInput);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.txtInput)");
        setOutputTextView((TextView) viewFindViewById);
        getOutputTextView().setText(this.lastResult);
        ((Button) findViewById(R.id.btnOne)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$1(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnTwo)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$2(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnThree)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$3(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnFour)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$4(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnFive)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$5(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnSix)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$6(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnSeven)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$7(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnEight)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$8(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnNine)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$9(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnZero)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$10(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnDecimal)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$11(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$12(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$13(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$14(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnDivide)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$15(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnMultiply)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$16(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnSubtract)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$17(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnClear)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$18(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnEqual)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$19(this.f$0, view);
            }
        });
        ((ImageView) findViewById(R.id.btnDeleteOne)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$20(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnCopy)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.calculator.CalculatorDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalculatorDialog.onCreate$lambda$21(this.f$0, view);
            }
        });
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit(ExifInterface.GPS_MEASUREMENT_2D);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit(ExifInterface.GPS_MEASUREMENT_3D);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("4");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("5");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("6");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("7");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("9");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDigit("0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$11(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDecimalPoint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$12(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onOperator("+");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$13(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onOperator("+");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onOperator("+");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$15(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onOperator(InternalZipConstants.ZIP_FILE_SEPARATOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$16(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onOperator("*");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$17(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onOperator("-");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$18(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onClear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$19(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onEqual();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$20(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDelete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$21(CalculatorDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.copyTextToClipboard();
    }

    public final void onDigit(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        if (this.stateError) {
            getOutputTextView().setText(str);
            this.stateError = false;
        } else {
            getOutputTextView().append(str);
        }
        this.lastNumaric = true;
    }

    public final void onDecimalPoint() {
        if (!this.lastNumaric || this.stateError || this.lastDot) {
            return;
        }
        getOutputTextView().append(".");
        this.lastNumaric = false;
        this.lastDot = true;
    }

    public final void onOperator(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        if (!this.lastNumaric || this.stateError) {
            return;
        }
        getOutputTextView().append(str);
        this.lastNumaric = false;
        this.lastDot = false;
    }

    public final void onDelete() {
        String str = getOutputTextView().getText();
        if (str.length() > 0) {
            if (!str.equals("")) {
                Intrinsics.checkNotNullExpressionValue(str, "str");
                str = str.subSequence(0, str.length() - 1).toString();
            }
            getOutputTextView().setText(str);
            return;
        }
        Toast.makeText(this.activity, "Sudah dihapus", 0).show();
    }

    public final void onClear() {
        getOutputTextView().setText("");
        this.lastNumaric = false;
        this.stateError = false;
        this.lastDot = false;
    }

    public final void onEqual() {
        if (!this.lastNumaric || this.stateError) {
            return;
        }
        try {
            double dEvaluate = new ExpressionBuilder(getOutputTextView().getText().toString()).build().evaluate();
            getOutputTextView().setText(String.valueOf(dEvaluate));
            this.lastDot = true;
            this.lastResult = String.valueOf(dEvaluate);
            this.callback.setInitResult(String.valueOf(dEvaluate));
        } catch (Exception unused) {
            getOutputTextView().setText("Error");
            this.stateError = true;
            this.lastNumaric = false;
        }
    }

    private final void copyTextToClipboard() {
        Object systemService = this.activity.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(TextBundle.TEXT_ENTRY, this.lastResult));
        Toast.makeText(this.activity, "Sukses copy", 1).show();
    }
}

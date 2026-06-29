package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: TextFieldBuffer.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a{\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032`\u0010\u0005\u001a\\\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\u0006H\u0080\b\u001a\u001c\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0007\u001aE\u0010\u0012\u001a\u00020\u0001*\u00020\u001326\u0010\u0014\u001a2\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00010\u0015H\u0087\b\u001aE\u0010\u0019\u001a\u00020\u0001*\u00020\u001326\u0010\u0014\u001a2\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00010\u0015H\u0087\b\u001a\u001c\u0010\u001a\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0007\u001a\f\u0010\u001e\u001a\u00020\u0001*\u00020\u000fH\u0007\u001a\f\u0010\u001f\u001a\u00020\u0001*\u00020\u000fH\u0007¨\u0006 "}, d2 = {"findCommonPrefixAndSuffix", "", "a", "", "b", "onFound", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "aPrefixStart", "aSuffixStart", "bPrefixStart", "bSuffixStart", "delete", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "start", "end", "forEachChange", "Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/text/TextRange;", "range", "originalRange", "forEachChangeReversed", "insert", FirebaseAnalytics.Param.INDEX, TextBundle.TEXT_ENTRY, "", "placeCursorAtEnd", "selectAll", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextFieldBufferKt {
    public static final void insert(TextFieldBuffer textFieldBuffer, int i, String str) {
        textFieldBuffer.replace(i, i, str);
    }

    public static final void delete(TextFieldBuffer textFieldBuffer, int i, int i2) {
        textFieldBuffer.replace(i, i2, "");
    }

    public static final void placeCursorAtEnd(TextFieldBuffer textFieldBuffer) {
        textFieldBuffer.placeCursorBeforeCharAt(textFieldBuffer.getLength());
    }

    public static final void selectAll(TextFieldBuffer textFieldBuffer) {
        textFieldBuffer.m1087selectCharsIn5zctL8(TextRangeKt.TextRange(0, textFieldBuffer.getLength()));
    }

    public static final void forEachChange(TextFieldBuffer.ChangeList changeList, Function2<? super TextRange, ? super TextRange, Unit> function2) {
        for (int i = 0; i < changeList.getChangeCount(); i++) {
            function2.invoke(TextRange.m5223boximpl(changeList.mo1080getRangejx7JFs(i)), TextRange.m5223boximpl(changeList.mo1079getOriginalRangejx7JFs(i)));
        }
    }

    public static final void forEachChangeReversed(TextFieldBuffer.ChangeList changeList, Function2<? super TextRange, ? super TextRange, Unit> function2) {
        for (int changeCount = changeList.getChangeCount() - 1; changeCount >= 0; changeCount--) {
            function2.invoke(TextRange.m5223boximpl(changeList.mo1080getRangejx7JFs(changeCount)), TextRange.m5223boximpl(changeList.mo1079getOriginalRangejx7JFs(changeCount)));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void findCommonPrefixAndSuffix(java.lang.CharSequence r9, java.lang.CharSequence r10, kotlin.jvm.functions.Function4<? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> r11) {
        /*
            int r0 = r9.length()
            int r1 = r10.length()
            int r2 = r9.length()
            r3 = 1
            r4 = 0
            if (r2 <= 0) goto L12
            r2 = r3
            goto L13
        L12:
            r2 = r4
        L13:
            if (r2 == 0) goto L55
            int r2 = r10.length()
            if (r2 <= 0) goto L1d
            r2 = r3
            goto L1e
        L1d:
            r2 = r4
        L1e:
            if (r2 == 0) goto L55
            r2 = r4
            r5 = r2
            r6 = r5
        L23:
            if (r4 != 0) goto L35
            char r7 = r9.charAt(r2)
            char r8 = r10.charAt(r5)
            if (r7 != r8) goto L34
            int r2 = r2 + 1
            int r5 = r5 + 1
            goto L35
        L34:
            r4 = r3
        L35:
            if (r6 != 0) goto L4b
            int r7 = r0 + (-1)
            char r7 = r9.charAt(r7)
            int r8 = r1 + (-1)
            char r8 = r10.charAt(r8)
            if (r7 != r8) goto L4a
            int r0 = r0 + (-1)
            int r1 = r1 + (-1)
            goto L4b
        L4a:
            r6 = r3
        L4b:
            if (r2 >= r0) goto L53
            if (r5 >= r1) goto L53
            if (r4 == 0) goto L23
            if (r6 == 0) goto L23
        L53:
            r4 = r2
            goto L56
        L55:
            r5 = r4
        L56:
            if (r4 < r0) goto L5b
            if (r5 < r1) goto L5b
            return
        L5b:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r11.invoke(r9, r10, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.TextFieldBufferKt.findCommonPrefixAndSuffix(java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function4):void");
    }
}

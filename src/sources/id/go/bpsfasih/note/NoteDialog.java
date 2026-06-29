package id.go.bpsfasih.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import com.codekidlabs.storagechooser.utils.MemoryUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NoteDialog.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/note/NoteDialog;", "Landroidx/fragment/app/DialogFragment;", "callbackListener", "Lid/go/bpsfasih/note/CallBackListener;", FormGearJavascriptInterface.NOTE_FILE, "", "canEdit", "", "(Lid/go/bpsfasih/note/CallBackListener;Ljava/lang/String;Z)V", "getTheme", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", MemoryUtil.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "notes_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NoteDialog extends DialogFragment {
    public Map<Integer, View> _$_findViewCache;
    private CallBackListener callbackListener;
    private boolean canEdit;
    private String note;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public NoteDialog(CallBackListener callbackListener, String note, boolean z) {
        Intrinsics.checkNotNullParameter(callbackListener, "callbackListener");
        Intrinsics.checkNotNullParameter(note, "note");
        this._$_findViewCache = new LinkedHashMap();
        this.callbackListener = callbackListener;
        this.note = note;
        this.canEdit = z;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        setCancelable(false);
        return inflater.inflate(R.layout.note_dialog, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.DialogTheme;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (!this.canEdit) {
            ((EditText) _$_findCachedViewById(R.id.et_note)).setEnabled(false);
        }
        if (this.note.equals("null")) {
            this.note = "";
        }
        ((EditText) _$_findCachedViewById(R.id.et_note)).setText(this.note);
        ((FloatingActionButton) _$_findCachedViewById(R.id.saveFAB)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.note.NoteDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NoteDialog.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(NoteDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = ((EditText) this$0._$_findCachedViewById(R.id.et_note)).getText().toString();
        if (string == null || string.length() == 0) {
            this$0.callbackListener.onDataReceived("");
        } else {
            this$0.callbackListener.onDataReceived(((EditText) this$0._$_findCachedViewById(R.id.et_note)).getText().toString());
        }
        this$0.dismiss();
    }
}

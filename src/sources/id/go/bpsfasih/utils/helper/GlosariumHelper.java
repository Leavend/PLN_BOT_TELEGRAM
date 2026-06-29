package id.go.bpsfasih.utils.helper;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: GlosariumHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/GlosariumHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class GlosariumHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static MutableLiveData<List<String>> tempData = new MutableLiveData<>();

    /* compiled from: GlosariumHelper.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\f2\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0006R&\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/utils/helper/GlosariumHelper$Companion;", "", "()V", "tempData", "Landroidx/lifecycle/MutableLiveData;", "", "", "getTempData", "()Landroidx/lifecycle/MutableLiveData;", "setTempData", "(Landroidx/lifecycle/MutableLiveData;)V", "getData", "Landroidx/lifecycle/LiveData;", DatabaseFileArchive.COLUMN_KEY, "perbaruiData", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String perbaruiData() {
            return "Berhasil memperbarui data";
        }

        private Companion() {
        }

        public final MutableLiveData<List<String>> getTempData() {
            return GlosariumHelper.tempData;
        }

        public final void setTempData(MutableLiveData<List<String>> mutableLiveData) {
            Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
            GlosariumHelper.tempData = mutableLiveData;
        }

        public final LiveData<List<String>> getData(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            if (key.equals("")) {
                getTempData().postValue(CollectionsKt.listOf((Object[]) new String[]{"Satu", "Tiga", "Dua", "Empat", "Lima"}));
            } else {
                getTempData().postValue(CollectionsKt.listOf(key));
            }
            return getTempData();
        }
    }
}

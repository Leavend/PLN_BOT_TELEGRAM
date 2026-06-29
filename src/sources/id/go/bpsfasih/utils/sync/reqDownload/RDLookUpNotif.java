package id.go.bpsfasih.utils.sync.reqDownload;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.LookupsList;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.helper.ZipHelper;
import id.go.bpsfasih.utils.tools.Downloader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: RDLookUpNotif.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001BK\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00126\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\u0010\u000eJ\b\u0010\u0015\u001a\u00020\rH\u0002J@\u0010\u0016\u001a\u00020\r26\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0006H\u0002J\b\u0010\u0018\u001a\u00020\rH\u0002J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0004H\u0002R>\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDLookUpNotif;", "", "allLookup", "", "Lid/go/bpsfasih/data/local/entities/LookupsList;", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "result", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "(Ljava/util/List;Lkotlin/jvm/functions/Function2;)V", "listLookup", "listLookupsPath", "getListLookupsPath", "()Ljava/util/List;", "setListLookupsPath", "(Ljava/util/List;)V", "nextQueue", "processDB", "message", "removeQueue", "requestLookup", "lookup", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDLookUpNotif {
    public static final int $stable = 8;
    private final Function2<String, Boolean, Unit> callback;
    private List<LookupsList> listLookup;
    private List<String> listLookupsPath;

    /* JADX WARN: Multi-variable type inference failed */
    public RDLookUpNotif(List<LookupsList> allLookup, Function2<? super String, ? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(allLookup, "allLookup");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
        this.listLookup = CollectionsKt.toMutableList((Collection) allLookup);
        this.listLookupsPath = new ArrayList();
        nextQueue();
    }

    public final List<String> getListLookupsPath() {
        return this.listLookupsPath;
    }

    public final void setListLookupsPath(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.listLookupsPath = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void nextQueue() {
        List<LookupsList> list = this.listLookup;
        boolean z = false;
        if (list != null && list.size() == 0) {
            z = true;
        }
        if (z) {
            processDB(new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDLookUpNotif.nextQueue.1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                    invoke(str, bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(String result, boolean z2) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    RDLookUpNotif.this.callback.invoke(result, Boolean.valueOf(z2));
                }
            });
            return;
        }
        List<LookupsList> list2 = this.listLookup;
        LookupsList lookupsList = list2 != null ? (LookupsList) CollectionsKt.first((List) list2) : null;
        removeQueue();
        Intrinsics.checkNotNull(lookupsList);
        requestLookup(lookupsList);
    }

    private final void removeQueue() {
        List<LookupsList> list = this.listLookup;
        Integer numValueOf = list != null ? Integer.valueOf(list.size()) : null;
        Intrinsics.checkNotNull(numValueOf);
        if (numValueOf.intValue() > 0) {
            List<LookupsList> list2 = this.listLookup;
            Intrinsics.checkNotNull(list2);
            if (list2.size() > 0) {
                List<LookupsList> list3 = this.listLookup;
                Intrinsics.checkNotNull(list3);
                list3.remove(0);
            } else {
                List<LookupsList> list4 = this.listLookup;
                Intrinsics.checkNotNull(list4);
                list4.remove(0);
            }
        }
    }

    private final void requestLookup(final LookupsList lookup) {
        if (lookup == null) {
            nextQueue();
        } else {
            Log.d("start_lookup", lookup.toString());
            Downloader.INSTANCE.requestLookup(lookup, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDLookUpNotif.requestLookup.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, String str) {
                    if (!z) {
                        ZipHelper.Companion companion = ZipHelper.INSTANCE;
                        String str2 = Directory.INSTANCE.getABSOLUTEPATHLOOKUP() + InternalZipConstants.ZIP_FILE_SEPARATOR + lookup.getId();
                        String str3 = lookup.getVersion() + CommonCons.INSTANCE.getEXTENSION_ZIP();
                        final RDLookUpNotif rDLookUpNotif = this;
                        final LookupsList lookupsList = lookup;
                        companion.unZipLookup(str2, str3, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDLookUpNotif.requestLookup.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str4) {
                                invoke(bool.booleanValue(), str4);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z2, String message) {
                                Intrinsics.checkNotNullParameter(message, "message");
                                if (!z2) {
                                    rDLookUpNotif.callback.invoke(message, true);
                                    return;
                                }
                                rDLookUpNotif.getListLookupsPath().add(Directory.INSTANCE.getABSOLUTEPATHLOOKUP() + InternalZipConstants.ZIP_FILE_SEPARATOR + lookupsList.getId() + InternalZipConstants.ZIP_FILE_SEPARATOR + lookupsList.getVersion() + CommonCons.INSTANCE.getEXTENSION_JSON());
                                rDLookUpNotif.nextQueue();
                            }
                        });
                        return;
                    }
                    Log.d("FOUR", "requestLookupData: Error request lookup data");
                    this.callback.invoke("Gagal mendownload lookup", true);
                }
            });
        }
    }

    private final void processDB(final Function2<? super String, ? super Boolean, Unit> callback) {
        new SyncLookUp(this.listLookupsPath, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDLookUpNotif$processDB$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String messga) {
                Intrinsics.checkNotNullParameter(messga, "messga");
                callback.invoke(messga, Boolean.valueOf(z));
            }
        });
    }
}

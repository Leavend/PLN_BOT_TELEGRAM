package id.go.bpsfasih.ui.periode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.internal.runner.listener.InstrumentationResultPrinter;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.models.FormEngineResponse;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.local.pojo.PeriodePojo;
import id.go.bpsfasih.data.repository.NotificationRepositoryImpl;
import id.go.bpsfasih.databinding.ItemPeriodeBinding;
import id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity;
import id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.helper.FormEngineHelper;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.ThrottledOnClicklistener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: PeriodeAdapter.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001)B7\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u001e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0018H\u0017J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0018H\u0016J\u001b\u0010%\u001a\u00020\u001e2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0000¢\u0006\u0002\b(R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006*"}, d2 = {"Lid/go/bpsfasih/ui/periode/PeriodeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/periode/PeriodeAdapter$PeriodeViewHolder;", "context", "Landroid/content/Context;", "isUpdateListing", "", "templateId", "", "panelType", "callback", "Lid/go/bpsfasih/ui/periode/PeriodeAdapterCallback;", "(Landroid/content/Context;ZLjava/lang/String;ZLid/go/bpsfasih/ui/periode/PeriodeAdapterCallback;)V", "getContext", "()Landroid/content/Context;", "getPanelType", "()Z", "periode", "", "Lid/go/bpsfasih/data/local/pojo/PeriodePojo;", "getTemplateId", "()Ljava/lang/String;", "checkFormEngine", "formEngineId", "", InstrumentationResultPrinter.REPORT_KEY_NUM_CURRENT, "view", "Landroid/view/View;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setPeriode", "periodes", "", "setPeriode$app_release", "PeriodeViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PeriodeAdapter extends RecyclerView.Adapter<PeriodeViewHolder> {
    public static final int $stable = 8;
    private final PeriodeAdapterCallback callback;
    private final Context context;
    private final boolean isUpdateListing;
    private final boolean panelType;
    private List<PeriodePojo> periode;
    private final String templateId;

    public /* synthetic */ PeriodeAdapter(Context context, boolean z, String str, boolean z2, PeriodeAdapterCallback periodeAdapterCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? false : z, (i & 4) != 0 ? "" : str, z2, periodeAdapterCallback);
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final boolean getPanelType() {
        return this.panelType;
    }

    public PeriodeAdapter(Context context, boolean z, String str, boolean z2, PeriodeAdapterCallback periodeAdapterCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.isUpdateListing = z;
        this.templateId = str;
        this.panelType = z2;
        this.callback = periodeAdapterCallback;
        this.periode = CollectionsKt.toMutableList((Collection) CollectionsKt.emptyList());
    }

    /* compiled from: PeriodeAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/periode/PeriodeAdapter$PeriodeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemPeriodeBinding;", "(Lid/go/bpsfasih/databinding/ItemPeriodeBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemPeriodeBinding;", "bind", "", "item", "Lid/go/bpsfasih/data/local/pojo/PeriodePojo;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class PeriodeViewHolder extends RecyclerView.ViewHolder {
        public static final int $stable = 8;
        private final ItemPeriodeBinding binding;

        public final ItemPeriodeBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PeriodeViewHolder(ItemPeriodeBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(PeriodePojo item) {
            String str;
            UserRole userRole;
            UserRole userRole2;
            String str2;
            UserRole userRole3;
            UserRole userRole4;
            Intrinsics.checkNotNullParameter(item, "item");
            this.binding.periodeTitle.setText(item.getPeriode().getName());
            this.binding.tvOpen.setText(String.valueOf(item.getOpenCount()));
            this.binding.tvSubmit.setText(String.valueOf(item.getSubmitCount()));
            this.binding.tvReject.setText(String.valueOf(item.getRejectCount()));
            this.binding.tvApprove.setText(String.valueOf(item.getApprovedCount()));
            TextView textView = this.binding.tvPending;
            Intrinsics.checkNotNull(textView);
            textView.setText(String.valueOf(item.getPendingCount()));
            TextView textView2 = this.binding.periodeTime;
            TimeUnit timeUnit = TimeUnit.DAYS;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = item.getPeriode().getEndDate();
            Intrinsics.checkNotNull(endDate);
            String description = null;
            if (String.valueOf(timeUnit.convert(timeInMillis - simpleDateFormat.parse(endDate).getTime(), TimeUnit.MILLISECONDS)).equals("0")) {
                long timeInMillis2 = Calendar.getInstance().getTimeInMillis();
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                String endDate2 = item.getPeriode().getEndDate();
                Intrinsics.checkNotNull(endDate2);
                if (timeInMillis2 > simpleDateFormat2.parse(endDate2).getTime()) {
                    List<UserRole> role = item.getPeriode().getRole();
                    if (role != null && (userRole4 = role.get(0)) != null) {
                        description = userRole4.getDescription();
                    }
                    str2 = "Selesai Hari ini - " + description;
                } else {
                    List<UserRole> role2 = item.getPeriode().getRole();
                    if (role2 != null && (userRole3 = role2.get(0)) != null) {
                        description = userRole3.getDescription();
                    }
                    str2 = "Selesai Besok - " + description;
                }
                str = str2;
            } else {
                long timeInMillis3 = Calendar.getInstance().getTimeInMillis();
                SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
                String endDate3 = item.getPeriode().getEndDate();
                Intrinsics.checkNotNull(endDate3);
                if (timeInMillis3 > simpleDateFormat3.parse(endDate3).getTime()) {
                    TimeUnit timeUnit2 = TimeUnit.DAYS;
                    long timeInMillis4 = Calendar.getInstance().getTimeInMillis();
                    SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
                    String endDate4 = item.getPeriode().getEndDate();
                    Intrinsics.checkNotNull(endDate4);
                    long jConvert = timeUnit2.convert(timeInMillis4 - simpleDateFormat4.parse(endDate4).getTime(), TimeUnit.MILLISECONDS);
                    List<UserRole> role3 = item.getPeriode().getRole();
                    if (role3 != null && (userRole2 = role3.get(0)) != null) {
                        description = userRole2.getDescription();
                    }
                    str = "Selesai " + jConvert + " Hari Yang Lalu - " + description;
                } else {
                    TimeUnit timeUnit3 = TimeUnit.DAYS;
                    SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("yyyy-MM-dd");
                    String endDate5 = item.getPeriode().getEndDate();
                    Intrinsics.checkNotNull(endDate5);
                    long jConvert2 = timeUnit3.convert(simpleDateFormat5.parse(endDate5).getTime() - Calendar.getInstance().getTimeInMillis(), TimeUnit.MILLISECONDS) + 1;
                    List<UserRole> role4 = item.getPeriode().getRole();
                    if (role4 != null && (userRole = role4.get(0)) != null) {
                        description = userRole.getDescription();
                    }
                    str = "Selesai " + jConvert2 + " Hari Lagi - " + description;
                }
            }
            textView2.setText(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PeriodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemPeriodeBinding binding = (ItemPeriodeBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_periode, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new PeriodeViewHolder(binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(PeriodeViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final PeriodePojo periodePojo = this.periode.get(position);
        holder.bind(periodePojo);
        if (position < 9) {
            String str = "0" + (position + 1);
        } else {
            String.valueOf(position + 1);
        }
        holder.getBinding().executePendingBindings();
        holder.getBinding().periodeConstrain.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeriodeAdapter.onBindViewHolder$lambda$0(periodePojo, this, view);
            }
        });
        holder.getBinding().btnUpdate.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeriodeAdapter.onBindViewHolder$lambda$1(this.f$0, periodePojo, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(PeriodePojo current, PeriodeAdapter this$0, View it) {
        Intrinsics.checkNotNullParameter(current, "$current");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = current.getPeriode().getEndDate();
        Intrinsics.checkNotNull(endDate);
        if (timeInMillis - simpleDateFormat.parse(endDate).getTime() <= 86400000) {
            Integer num = (Integer) BuildersKt__BuildersKt.runBlocking$default(null, new PeriodeAdapter$onBindViewHolder$1$formEngineId$1(current, null), 1, null);
            Intrinsics.checkNotNull(num);
            int iIntValue = num.intValue();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            this$0.checkFormEngine(iIntValue, current, it);
            return;
        }
        Toast.makeText(this$0.context, "Periode sudah berlalu. Anda sudah tidak bisa melakukan pendataan pada periode ini", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(PeriodeAdapter this$0, PeriodePojo current, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(current, "$current");
        PeriodeAdapterCallback periodeAdapterCallback = this$0.callback;
        if (periodeAdapterCallback != null) {
            periodeAdapterCallback.onItemClicked(current.getPeriode().getId());
        }
    }

    public final boolean checkFormEngine(final int formEngineId, final PeriodePojo current, final View view) {
        Intrinsics.checkNotNullParameter(current, "current");
        Intrinsics.checkNotNullParameter(view, "view");
        File file = new File(Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + formEngineId);
        if (file.exists()) {
            if (Network.INSTANCE.isOnline(this.context)) {
                new NotificationRepositoryImpl().getFormEngine(String.valueOf(formEngineId), new Function1<FormEngineResponse, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter.checkFormEngine.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FormEngineResponse formEngineResponse) {
                        invoke2(formEngineResponse);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FormEngineResponse formEngineResponse) {
                        if (formEngineResponse != null ? Intrinsics.areEqual((Object) formEngineResponse.getSuccess(), (Object) true) : false) {
                            if (!FormEngineHelper.INSTANCE.getFormEngineVersion(String.valueOf(formEngineId)).equals(formEngineResponse.getData().getVersion())) {
                                Intent intent = new Intent(this.getContext(), (Class<?>) DownloadFormEngineActivity.class);
                                intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), current.getPeriode().getSurvey().getId());
                                this.getContext().startActivity(intent);
                                return;
                            } else {
                                final PeriodeAdapter periodeAdapter = this;
                                final PeriodePojo periodePojo = current;
                                new ThrottledOnClicklistener(new Function1<View, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter.checkFormEngine.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(View it) {
                                        UserRole userRole;
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        DaftarWilayahActivity.Companion companion = DaftarWilayahActivity.INSTANCE;
                                        Context context = periodeAdapter.getContext();
                                        String primaryId = periodePojo.getPeriode().getPrimaryId();
                                        String id2 = periodePojo.getPeriode().getId();
                                        String templateId = periodeAdapter.getTemplateId();
                                        List<UserRole> role = periodePojo.getPeriode().getRole();
                                        companion.startActivity(context, primaryId, id2, templateId, (role == null || (userRole = (UserRole) CollectionsKt.first((List) role)) == null) ? null : userRole.isPencacah(), periodeAdapter.getPanelType(), periodePojo.getPeriode().getSurvey().getCanAddSample(), periodePojo.getPeriode().getSurvey().getId());
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                                        invoke2(view2);
                                        return Unit.INSTANCE;
                                    }
                                }).onClick(view);
                                return;
                            }
                        }
                        Toast.makeText(this.getContext(), "Terjadi kesalahan ketika cek versi form engine", 0).show();
                        final PeriodeAdapter periodeAdapter2 = this;
                        final PeriodePojo periodePojo2 = current;
                        new ThrottledOnClicklistener(new Function1<View, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter.checkFormEngine.1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(View it) {
                                UserRole userRole;
                                Intrinsics.checkNotNullParameter(it, "it");
                                DaftarWilayahActivity.Companion companion = DaftarWilayahActivity.INSTANCE;
                                Context context = periodeAdapter2.getContext();
                                String primaryId = periodePojo2.getPeriode().getPrimaryId();
                                String id2 = periodePojo2.getPeriode().getId();
                                String templateId = periodeAdapter2.getTemplateId();
                                List<UserRole> role = periodePojo2.getPeriode().getRole();
                                companion.startActivity(context, primaryId, id2, templateId, (role == null || (userRole = (UserRole) CollectionsKt.first((List) role)) == null) ? null : userRole.isPencacah(), periodeAdapter2.getPanelType(), periodePojo2.getPeriode().getSurvey().getCanAddSample(), periodePojo2.getPeriode().getSurvey().getId());
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                                invoke2(view2);
                                return Unit.INSTANCE;
                            }
                        }).onClick(view);
                    }
                });
            } else {
                new ThrottledOnClicklistener(new Function1<View, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter.checkFormEngine.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View it) {
                        UserRole userRole;
                        Intrinsics.checkNotNullParameter(it, "it");
                        DaftarWilayahActivity.Companion companion = DaftarWilayahActivity.INSTANCE;
                        Context context = PeriodeAdapter.this.getContext();
                        String primaryId = current.getPeriode().getPrimaryId();
                        String id2 = current.getPeriode().getId();
                        String templateId = PeriodeAdapter.this.getTemplateId();
                        List<UserRole> role = current.getPeriode().getRole();
                        companion.startActivity(context, primaryId, id2, templateId, (role == null || (userRole = (UserRole) CollectionsKt.first((List) role)) == null) ? null : userRole.isPencacah(), PeriodeAdapter.this.getPanelType(), current.getPeriode().getSurvey().getCanAddSample(), current.getPeriode().getSurvey().getId());
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                        invoke2(view2);
                        return Unit.INSTANCE;
                    }
                }).onClick(view);
            }
        } else {
            file.mkdirs();
            if (Network.INSTANCE.isOnline(this.context)) {
                new NotificationRepositoryImpl().getFormEngine(String.valueOf(formEngineId), new Function1<FormEngineResponse, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter.checkFormEngine.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FormEngineResponse formEngineResponse) {
                        invoke2(formEngineResponse);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FormEngineResponse formEngineResponse) {
                        if (formEngineResponse != null ? Intrinsics.areEqual((Object) formEngineResponse.getSuccess(), (Object) true) : false) {
                            if (!FormEngineHelper.INSTANCE.getFormEngineVersion(String.valueOf(formEngineId)).equals(formEngineResponse.getData().getVersion())) {
                                Intent intent = new Intent(this.getContext(), (Class<?>) DownloadFormEngineActivity.class);
                                intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), current.getPeriode().getSurvey().getId());
                                this.getContext().startActivity(intent);
                                return;
                            } else {
                                final PeriodeAdapter periodeAdapter = this;
                                final PeriodePojo periodePojo = current;
                                new ThrottledOnClicklistener(new Function1<View, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeAdapter.checkFormEngine.3.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(View it) {
                                        UserRole userRole;
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        DaftarWilayahActivity.Companion companion = DaftarWilayahActivity.INSTANCE;
                                        Context context = periodeAdapter.getContext();
                                        String primaryId = periodePojo.getPeriode().getPrimaryId();
                                        String id2 = periodePojo.getPeriode().getId();
                                        String templateId = periodeAdapter.getTemplateId();
                                        List<UserRole> role = periodePojo.getPeriode().getRole();
                                        companion.startActivity(context, primaryId, id2, templateId, (role == null || (userRole = (UserRole) CollectionsKt.first((List) role)) == null) ? null : userRole.isPencacah(), periodeAdapter.getPanelType(), periodePojo.getPeriode().getSurvey().getCanAddSample(), periodePojo.getPeriode().getSurvey().getId());
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                                        invoke2(view2);
                                        return Unit.INSTANCE;
                                    }
                                }).onClick(view);
                                return;
                            }
                        }
                        Toast.makeText(this.getContext(), "Terjadi kesalahan ketika cek versi form engine", 0).show();
                    }
                });
            } else {
                Toast.makeText(this.context, "Anda perlu koneksi internet untuk download formengine", 0).show();
            }
        }
        return false;
    }

    public final void setPeriode$app_release(List<PeriodePojo> periodes) {
        Intrinsics.checkNotNullParameter(periodes, "periodes");
        this.periode = CollectionsKt.toMutableList((Collection) periodes);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.periode.size();
    }
}

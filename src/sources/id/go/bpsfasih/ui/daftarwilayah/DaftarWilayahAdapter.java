package id.go.bpsfasih.ui.daftarwilayah;

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
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.Level1;
import id.go.bpsfasih.data.local.entities.Level10;
import id.go.bpsfasih.data.local.entities.Level2;
import id.go.bpsfasih.data.local.entities.Level3;
import id.go.bpsfasih.data.local.entities.Level4;
import id.go.bpsfasih.data.local.entities.Level5;
import id.go.bpsfasih.data.local.entities.Level6;
import id.go.bpsfasih.data.local.entities.Level7;
import id.go.bpsfasih.data.local.entities.Level8;
import id.go.bpsfasih.data.local.entities.Level9;
import id.go.bpsfasih.data.local.entities.LevelRegionMetadata;
import id.go.bpsfasih.data.local.entities.Region;
import id.go.bpsfasih.data.local.entities.RegionMetadata;
import id.go.bpsfasih.data.local.entities.SamplingRegionEntity;
import id.go.bpsfasih.data.local.pojo.AssignmentRegionWilayahPojo;
import id.go.bpsfasih.databinding.ItemDaftarWilayahBinding;
import id.go.bpsfasih.ui.assignmentList.AssignmentListActivity;
import id.go.bpsfasih.ui.rekapWilayah.RekapWilayahActivity;
import id.go.bpsfasih.utils.helper.AssignmentEncryptionHelper;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.helper.ThrottledOnClicklistener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DaftarWilayahAdapter.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 92\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00029:B=\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u00020,H\u0016J\u0018\u00101\u001a\u00020\u00022\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020,H\u0016J+\u00105\u001a\u00020.2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\u000e\u00107\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001fH\u0000¢\u0006\u0002\b8R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0007\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001d\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010$R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006;"}, d2 = {"Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahAdapter$UpdateListingViewHolder;", "context", "Landroid/content/Context;", "templateId", "", "isPencacah", "", "canAddSample", "surveyId", "viewModel", "Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModel;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Boolean;ZLjava/lang/String;Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModel;)V", "assignments", "", "Lid/go/bpsfasih/data/local/pojo/AssignmentRegionWilayahPojo;", "getCanAddSample", "()Z", "setCanAddSample", "(Z)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "()Ljava/lang/Boolean;", "setPencacah", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "isRekapWilayahEnabled", "mSamplingRegionOfflines", "", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "getSurveyId", "()Ljava/lang/String;", "setSurveyId", "(Ljava/lang/String;)V", "getTemplateId", "setTemplateId", "getViewModel", "()Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModel;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "samplingRegionOffline", "setData$app_release", "Companion", "UpdateListingViewHolder", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DaftarWilayahAdapter extends RecyclerView.Adapter<UpdateListingViewHolder> {
    private List<AssignmentRegionWilayahPojo> assignments;
    private boolean canAddSample;
    private Context context;
    private Boolean isPencacah;
    private final boolean isRekapWilayahEnabled;
    private List<SamplingRegionEntity> mSamplingRegionOfflines;
    private String surveyId;
    private String templateId;
    private UpdateListingViewModel viewModel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final void setTemplateId(String str) {
        this.templateId = str;
    }

    /* renamed from: isPencacah, reason: from getter */
    public final Boolean getIsPencacah() {
        return this.isPencacah;
    }

    public final void setPencacah(Boolean bool) {
        this.isPencacah = bool;
    }

    public final boolean getCanAddSample() {
        return this.canAddSample;
    }

    public final void setCanAddSample(boolean z) {
        this.canAddSample = z;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        this.surveyId = str;
    }

    public final UpdateListingViewModel getViewModel() {
        return this.viewModel;
    }

    public final void setViewModel(UpdateListingViewModel updateListingViewModel) {
        Intrinsics.checkNotNullParameter(updateListingViewModel, "<set-?>");
        this.viewModel = updateListingViewModel;
    }

    public DaftarWilayahAdapter(Context context, String str, Boolean bool, boolean z, String str2, UpdateListingViewModel viewModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.context = context;
        this.templateId = str;
        this.isPencacah = bool;
        this.canAddSample = z;
        this.surveyId = str2;
        this.viewModel = viewModel;
        this.isRekapWilayahEnabled = RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("rekap_wilayah");
        this.assignments = CollectionsKt.toMutableList((Collection) CollectionsKt.emptyList());
    }

    /* compiled from: DaftarWilayahAdapter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahAdapter$Companion;", "", "()V", "getRegionLabel", "", "regionMetadata", "Lid/go/bpsfasih/data/local/entities/RegionMetadata;", FirebaseAnalytics.Param.INDEX, "", "getRegionLevelCount", "region", "Lid/go/bpsfasih/data/local/entities/Region;", "getSmallestRegionName", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getSmallestRegionName(Region region) {
            Level1 level1;
            Level1 level12;
            Level2 level2;
            Level1 level13;
            Level2 level22;
            Level3 level3;
            Level1 level14;
            Level2 level23;
            Level3 level32;
            Level4 level4;
            Level1 level15;
            Level2 level24;
            Level3 level33;
            Level4 level42;
            Level5 level5;
            Level1 level16;
            Level2 level25;
            Level3 level34;
            Level4 level43;
            Level5 level52;
            Level6 level6;
            Level1 level17;
            Level2 level26;
            Level3 level35;
            Level4 level44;
            Level5 level53;
            Level6 level62;
            Level7 level7;
            Level1 level18;
            Level2 level27;
            Level3 level36;
            Level4 level45;
            Level5 level54;
            Level6 level63;
            Level7 level72;
            Level8 level8;
            Level1 level19;
            Level2 level28;
            Level3 level37;
            Level4 level46;
            Level5 level55;
            Level6 level64;
            Level7 level73;
            Level8 level82;
            Level9 level9;
            Level1 level110;
            Level2 level29;
            Level3 level38;
            Level4 level47;
            Level5 level56;
            Level6 level65;
            Level7 level74;
            Level8 level83;
            Level9 level92;
            Level10 level10;
            String name;
            if (region != null && (level110 = region.getLevel1()) != null && (level29 = level110.getLevel2()) != null && (level38 = level29.getLevel3()) != null && (level47 = level38.getLevel4()) != null && (level56 = level47.getLevel5()) != null && (level65 = level56.getLevel6()) != null && (level74 = level65.getLevel7()) != null && (level83 = level74.getLevel8()) != null && (level92 = level83.getLevel9()) != null && (level10 = level92.getLevel10()) != null && (name = level10.getName()) != null) {
                return name;
            }
            String name2 = (region == null || (level19 = region.getLevel1()) == null || (level28 = level19.getLevel2()) == null || (level37 = level28.getLevel3()) == null || (level46 = level37.getLevel4()) == null || (level55 = level46.getLevel5()) == null || (level64 = level55.getLevel6()) == null || (level73 = level64.getLevel7()) == null || (level82 = level73.getLevel8()) == null || (level9 = level82.getLevel9()) == null) ? null : level9.getName();
            if (name2 == null) {
                name2 = (region == null || (level18 = region.getLevel1()) == null || (level27 = level18.getLevel2()) == null || (level36 = level27.getLevel3()) == null || (level45 = level36.getLevel4()) == null || (level54 = level45.getLevel5()) == null || (level63 = level54.getLevel6()) == null || (level72 = level63.getLevel7()) == null || (level8 = level72.getLevel8()) == null) ? null : level8.getName();
                if (name2 == null) {
                    name2 = (region == null || (level17 = region.getLevel1()) == null || (level26 = level17.getLevel2()) == null || (level35 = level26.getLevel3()) == null || (level44 = level35.getLevel4()) == null || (level53 = level44.getLevel5()) == null || (level62 = level53.getLevel6()) == null || (level7 = level62.getLevel7()) == null) ? null : level7.getName();
                    if (name2 == null) {
                        name2 = (region == null || (level16 = region.getLevel1()) == null || (level25 = level16.getLevel2()) == null || (level34 = level25.getLevel3()) == null || (level43 = level34.getLevel4()) == null || (level52 = level43.getLevel5()) == null || (level6 = level52.getLevel6()) == null) ? null : level6.getName();
                        if (name2 == null) {
                            name2 = (region == null || (level15 = region.getLevel1()) == null || (level24 = level15.getLevel2()) == null || (level33 = level24.getLevel3()) == null || (level42 = level33.getLevel4()) == null || (level5 = level42.getLevel5()) == null) ? null : level5.getName();
                            if (name2 == null) {
                                name2 = (region == null || (level14 = region.getLevel1()) == null || (level23 = level14.getLevel2()) == null || (level32 = level23.getLevel3()) == null || (level4 = level32.getLevel4()) == null) ? null : level4.getName();
                                if (name2 == null) {
                                    name2 = (region == null || (level13 = region.getLevel1()) == null || (level22 = level13.getLevel2()) == null || (level3 = level22.getLevel3()) == null) ? null : level3.getName();
                                    if (name2 == null) {
                                        name2 = (region == null || (level12 = region.getLevel1()) == null || (level2 = level12.getLevel2()) == null) ? null : level2.getName();
                                        if (name2 == null) {
                                            if (region == null || (level1 = region.getLevel1()) == null) {
                                                return null;
                                            }
                                            return level1.getName();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return name2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getRegionLevelCount(Region region) {
            Level2 level2;
            Level2 level22;
            Level3 level3;
            Level2 level23;
            Level3 level32;
            Level4 level4;
            Level2 level24;
            Level3 level33;
            Level4 level42;
            Level5 level5;
            Level2 level25;
            Level3 level34;
            Level4 level43;
            Level5 level52;
            Level6 level6;
            Level2 level26;
            Level3 level35;
            Level4 level44;
            Level5 level53;
            Level6 level62;
            Level7 level7;
            Level2 level27;
            Level3 level36;
            Level4 level45;
            Level5 level54;
            Level6 level63;
            Level7 level72;
            Level8 level8;
            Level2 level28;
            Level3 level37;
            Level4 level46;
            Level5 level55;
            Level6 level64;
            Level7 level73;
            Level8 level82;
            Level9 level9;
            if ((region != null ? region.getLevel1() : null) == null) {
                return 0;
            }
            Level1 level1 = region.getLevel1();
            if (((level1 == null || (level28 = level1.getLevel2()) == null || (level37 = level28.getLevel3()) == null || (level46 = level37.getLevel4()) == null || (level55 = level46.getLevel5()) == null || (level64 = level55.getLevel6()) == null || (level73 = level64.getLevel7()) == null || (level82 = level73.getLevel8()) == null || (level9 = level82.getLevel9()) == null) ? null : level9.getLevel10()) != null) {
                return 10;
            }
            Level1 level12 = region.getLevel1();
            if (((level12 == null || (level27 = level12.getLevel2()) == null || (level36 = level27.getLevel3()) == null || (level45 = level36.getLevel4()) == null || (level54 = level45.getLevel5()) == null || (level63 = level54.getLevel6()) == null || (level72 = level63.getLevel7()) == null || (level8 = level72.getLevel8()) == null) ? null : level8.getLevel9()) != null) {
                return 9;
            }
            Level1 level13 = region.getLevel1();
            if (((level13 == null || (level26 = level13.getLevel2()) == null || (level35 = level26.getLevel3()) == null || (level44 = level35.getLevel4()) == null || (level53 = level44.getLevel5()) == null || (level62 = level53.getLevel6()) == null || (level7 = level62.getLevel7()) == null) ? null : level7.getLevel8()) != null) {
                return 8;
            }
            Level1 level14 = region.getLevel1();
            if (((level14 == null || (level25 = level14.getLevel2()) == null || (level34 = level25.getLevel3()) == null || (level43 = level34.getLevel4()) == null || (level52 = level43.getLevel5()) == null || (level6 = level52.getLevel6()) == null) ? null : level6.getLevel7()) != null) {
                return 7;
            }
            Level1 level15 = region.getLevel1();
            if (((level15 == null || (level24 = level15.getLevel2()) == null || (level33 = level24.getLevel3()) == null || (level42 = level33.getLevel4()) == null || (level5 = level42.getLevel5()) == null) ? null : level5.getLevel6()) != null) {
                return 6;
            }
            Level1 level16 = region.getLevel1();
            if (((level16 == null || (level23 = level16.getLevel2()) == null || (level32 = level23.getLevel3()) == null || (level4 = level32.getLevel4()) == null) ? null : level4.getLevel5()) != null) {
                return 5;
            }
            Level1 level17 = region.getLevel1();
            if (((level17 == null || (level22 = level17.getLevel2()) == null || (level3 = level22.getLevel3()) == null) ? null : level3.getLevel4()) != null) {
                return 4;
            }
            Level1 level18 = region.getLevel1();
            if (((level18 == null || (level2 = level18.getLevel2()) == null) ? null : level2.getLevel3()) != null) {
                return 3;
            }
            Level1 level19 = region.getLevel1();
            return (level19 != null ? level19.getLevel2() : null) != null ? 2 : 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getRegionLabel(RegionMetadata regionMetadata, int index) {
            List<LevelRegionMetadata> level;
            LevelRegionMetadata levelRegionMetadata;
            String name;
            return (regionMetadata == null || (level = regionMetadata.getLevel()) == null || (levelRegionMetadata = (LevelRegionMetadata) CollectionsKt.getOrNull(level, index)) == null || (name = levelRegionMetadata.getName()) == null) ? "Level " + (index + 1) : name;
        }
    }

    /* compiled from: DaftarWilayahAdapter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahAdapter$UpdateListingViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lid/go/bpsfasih/databinding/ItemDaftarWilayahBinding;", "(Lid/go/bpsfasih/databinding/ItemDaftarWilayahBinding;)V", "getBinding", "()Lid/go/bpsfasih/databinding/ItemDaftarWilayahBinding;", "bind", "", "item", "Lid/go/bpsfasih/data/local/pojo/AssignmentRegionWilayahPojo;", "viewModel", "Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModel;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class UpdateListingViewHolder extends RecyclerView.ViewHolder {
        public static final int $stable = 8;
        private final ItemDaftarWilayahBinding binding;

        public final ItemDaftarWilayahBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UpdateListingViewHolder(ItemDaftarWilayahBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(AssignmentRegionWilayahPojo item, UpdateListingViewModel viewModel) {
            Integer levelCount;
            Level1 level1;
            Level2 level2;
            Level3 level3;
            Level4 level4;
            Level5 level5;
            Level6 level6;
            Level7 level7;
            Level8 level8;
            Level9 level9;
            Level10 level10;
            Level1 level12;
            Level2 level22;
            Level3 level32;
            Level4 level42;
            Level5 level52;
            Level6 level62;
            Level7 level72;
            Level8 level82;
            Level9 level92;
            Level1 level13;
            Level2 level23;
            Level3 level33;
            Level4 level43;
            Level5 level53;
            Level6 level63;
            Level7 level73;
            Level8 level83;
            Level1 level14;
            Level2 level24;
            Level3 level34;
            Level4 level44;
            Level5 level54;
            Level6 level64;
            Level7 level74;
            Level1 level15;
            Level2 level25;
            Level3 level35;
            Level4 level45;
            Level5 level55;
            Level6 level65;
            Level1 level16;
            Level2 level26;
            Level3 level36;
            Level4 level46;
            Level5 level56;
            Level1 level17;
            Level2 level27;
            Level3 level37;
            Level4 level47;
            Level1 level18;
            Level2 level28;
            Level3 level38;
            Level1 level19;
            Level2 level29;
            Level1 level110;
            Level1 level111;
            Level2 level210;
            Level3 level39;
            Level4 level48;
            Level5 level57;
            Level6 level66;
            Level7 level75;
            Level8 level84;
            Level9 level93;
            Level10 level102;
            Level1 level112;
            Level2 level211;
            Level3 level310;
            Level4 level49;
            Level5 level58;
            Level6 level67;
            Level7 level76;
            Level8 level85;
            Level9 level94;
            Level1 level113;
            Level2 level212;
            Level3 level311;
            Level4 level410;
            Level5 level59;
            Level6 level68;
            Level7 level77;
            Level8 level86;
            Level1 level114;
            Level2 level213;
            Level3 level312;
            Level4 level411;
            Level5 level510;
            Level6 level69;
            Level7 level78;
            Level1 level115;
            Level2 level214;
            Level3 level313;
            Level4 level412;
            Level5 level511;
            Level6 level610;
            Level1 level116;
            Level2 level215;
            Level3 level314;
            Level4 level413;
            Level5 level512;
            Level1 level117;
            Level2 level216;
            Level3 level315;
            Level4 level414;
            Level1 level118;
            Level2 level217;
            Level3 level316;
            Level1 level119;
            Level2 level218;
            Level1 level120;
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(viewModel, "viewModel");
            Region region = item.getAssignmentRegion().getRegion();
            RegionMetadata regionMetadata = item.getAssignmentRegion().getRegionMetadata();
            String smallest_region_full_code = item.getAssignmentRegion().getSmallest_region_full_code();
            this.binding.tvOpen.setText(String.valueOf(item.getOpenCount()));
            this.binding.tvSubmit.setText(String.valueOf(item.getSubmitCount()));
            this.binding.tvReject.setText(String.valueOf(item.getRejectCount()));
            this.binding.tvApprove.setText(String.valueOf(item.getApprovedCount()));
            TextView textView = this.binding.tvPending;
            Intrinsics.checkNotNull(textView);
            textView.setText(String.valueOf(item.getPendingCount()));
            this.binding.tvValueRegionCode.setText(smallest_region_full_code == null ? "" : smallest_region_full_code);
            String str = smallest_region_full_code;
            this.binding.llRegionCode.setVisibility(str == null || StringsKt.isBlank(str) ? 8 : 0);
            String name = null;
            String name2 = (region == null || (level120 = region.getLevel1()) == null) ? null : level120.getName();
            if (!(name2 == null || name2.length() == 0)) {
                this.binding.llLevel1.setVisibility(0);
            }
            String name3 = (region == null || (level119 = region.getLevel1()) == null || (level218 = level119.getLevel2()) == null) ? null : level218.getName();
            if (!(name3 == null || name3.length() == 0)) {
                this.binding.llLevel2.setVisibility(0);
            }
            String name4 = (region == null || (level118 = region.getLevel1()) == null || (level217 = level118.getLevel2()) == null || (level316 = level217.getLevel3()) == null) ? null : level316.getName();
            if (!(name4 == null || name4.length() == 0)) {
                this.binding.llLevel3.setVisibility(0);
            }
            String name5 = (region == null || (level117 = region.getLevel1()) == null || (level216 = level117.getLevel2()) == null || (level315 = level216.getLevel3()) == null || (level414 = level315.getLevel4()) == null) ? null : level414.getName();
            if (!(name5 == null || name5.length() == 0)) {
                this.binding.llLevel4.setVisibility(0);
            }
            String name6 = (region == null || (level116 = region.getLevel1()) == null || (level215 = level116.getLevel2()) == null || (level314 = level215.getLevel3()) == null || (level413 = level314.getLevel4()) == null || (level512 = level413.getLevel5()) == null) ? null : level512.getName();
            if (!(name6 == null || name6.length() == 0)) {
                this.binding.llLevel5.setVisibility(0);
            }
            String name7 = (region == null || (level115 = region.getLevel1()) == null || (level214 = level115.getLevel2()) == null || (level313 = level214.getLevel3()) == null || (level412 = level313.getLevel4()) == null || (level511 = level412.getLevel5()) == null || (level610 = level511.getLevel6()) == null) ? null : level610.getName();
            if (!(name7 == null || name7.length() == 0)) {
                this.binding.llLevel6.setVisibility(0);
            }
            String name8 = (region == null || (level114 = region.getLevel1()) == null || (level213 = level114.getLevel2()) == null || (level312 = level213.getLevel3()) == null || (level411 = level312.getLevel4()) == null || (level510 = level411.getLevel5()) == null || (level69 = level510.getLevel6()) == null || (level78 = level69.getLevel7()) == null) ? null : level78.getName();
            if (!(name8 == null || name8.length() == 0)) {
                this.binding.llLevel7.setVisibility(0);
            }
            String name9 = (region == null || (level113 = region.getLevel1()) == null || (level212 = level113.getLevel2()) == null || (level311 = level212.getLevel3()) == null || (level410 = level311.getLevel4()) == null || (level59 = level410.getLevel5()) == null || (level68 = level59.getLevel6()) == null || (level77 = level68.getLevel7()) == null || (level86 = level77.getLevel8()) == null) ? null : level86.getName();
            if (!(name9 == null || name9.length() == 0)) {
                this.binding.llLevel8.setVisibility(0);
            }
            String name10 = (region == null || (level112 = region.getLevel1()) == null || (level211 = level112.getLevel2()) == null || (level310 = level211.getLevel3()) == null || (level49 = level310.getLevel4()) == null || (level58 = level49.getLevel5()) == null || (level67 = level58.getLevel6()) == null || (level76 = level67.getLevel7()) == null || (level85 = level76.getLevel8()) == null || (level94 = level85.getLevel9()) == null) ? null : level94.getName();
            if (!(name10 == null || name10.length() == 0)) {
                this.binding.llLevel9.setVisibility(0);
            }
            String name11 = (region == null || (level111 = region.getLevel1()) == null || (level210 = level111.getLevel2()) == null || (level39 = level210.getLevel3()) == null || (level48 = level39.getLevel4()) == null || (level57 = level48.getLevel5()) == null || (level66 = level57.getLevel6()) == null || (level75 = level66.getLevel7()) == null || (level84 = level75.getLevel8()) == null || (level93 = level84.getLevel9()) == null || (level102 = level93.getLevel10()) == null) ? null : level102.getName();
            if (!(name11 == null || name11.length() == 0)) {
                this.binding.llLevel10.setVisibility(0);
            }
            this.binding.tvValueLevel1.setText((region == null || (level110 = region.getLevel1()) == null) ? null : level110.getName());
            this.binding.tvValueLevel2.setText((region == null || (level19 = region.getLevel1()) == null || (level29 = level19.getLevel2()) == null) ? null : level29.getName());
            this.binding.tvValueLevel3.setText((region == null || (level18 = region.getLevel1()) == null || (level28 = level18.getLevel2()) == null || (level38 = level28.getLevel3()) == null) ? null : level38.getName());
            this.binding.tvValueLevel4.setText((region == null || (level17 = region.getLevel1()) == null || (level27 = level17.getLevel2()) == null || (level37 = level27.getLevel3()) == null || (level47 = level37.getLevel4()) == null) ? null : level47.getName());
            this.binding.tvValueLevel5.setText((region == null || (level16 = region.getLevel1()) == null || (level26 = level16.getLevel2()) == null || (level36 = level26.getLevel3()) == null || (level46 = level36.getLevel4()) == null || (level56 = level46.getLevel5()) == null) ? null : level56.getName());
            this.binding.tvValueLevel6.setText((region == null || (level15 = region.getLevel1()) == null || (level25 = level15.getLevel2()) == null || (level35 = level25.getLevel3()) == null || (level45 = level35.getLevel4()) == null || (level55 = level45.getLevel5()) == null || (level65 = level55.getLevel6()) == null) ? null : level65.getName());
            this.binding.tvValueLevel7.setText((region == null || (level14 = region.getLevel1()) == null || (level24 = level14.getLevel2()) == null || (level34 = level24.getLevel3()) == null || (level44 = level34.getLevel4()) == null || (level54 = level44.getLevel5()) == null || (level64 = level54.getLevel6()) == null || (level74 = level64.getLevel7()) == null) ? null : level74.getName());
            this.binding.tvValueLevel8.setText((region == null || (level13 = region.getLevel1()) == null || (level23 = level13.getLevel2()) == null || (level33 = level23.getLevel3()) == null || (level43 = level33.getLevel4()) == null || (level53 = level43.getLevel5()) == null || (level63 = level53.getLevel6()) == null || (level73 = level63.getLevel7()) == null || (level83 = level73.getLevel8()) == null) ? null : level83.getName());
            this.binding.tvValueLevel9.setText((region == null || (level12 = region.getLevel1()) == null || (level22 = level12.getLevel2()) == null || (level32 = level22.getLevel3()) == null || (level42 = level32.getLevel4()) == null || (level52 = level42.getLevel5()) == null || (level62 = level52.getLevel6()) == null || (level72 = level62.getLevel7()) == null || (level82 = level72.getLevel8()) == null || (level92 = level82.getLevel9()) == null) ? null : level92.getName());
            TextView textView2 = this.binding.tvValueLevel10;
            if (region != null && (level1 = region.getLevel1()) != null && (level2 = level1.getLevel2()) != null && (level3 = level2.getLevel3()) != null && (level4 = level3.getLevel4()) != null && (level5 = level4.getLevel5()) != null && (level6 = level5.getLevel6()) != null && (level7 = level6.getLevel7()) != null && (level8 = level7.getLevel8()) != null && (level9 = level8.getLevel9()) != null && (level10 = level9.getLevel10()) != null) {
                name = level10.getName();
            }
            textView2.setText(name);
            switch ((regionMetadata == null || (levelCount = regionMetadata.getLevelCount()) == null) ? DaftarWilayahAdapter.INSTANCE.getRegionLevelCount(region) : levelCount.intValue()) {
                case 1:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    break;
                case 2:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    break;
                case 3:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    break;
                case 4:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    this.binding.tvLabelLevel4.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 3));
                    break;
                case 5:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    this.binding.tvLabelLevel4.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 3));
                    this.binding.tvLabelLevel5.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 4));
                    break;
                case 6:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    this.binding.tvLabelLevel4.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 3));
                    this.binding.tvLabelLevel5.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 4));
                    this.binding.tvLabelLevel6.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 5));
                    break;
                case 7:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    this.binding.tvLabelLevel4.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 3));
                    this.binding.tvLabelLevel5.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 4));
                    this.binding.tvLabelLevel6.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 5));
                    this.binding.tvLabelLevel7.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 6));
                    break;
                case 8:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    this.binding.tvLabelLevel4.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 3));
                    this.binding.tvLabelLevel5.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 4));
                    this.binding.tvLabelLevel6.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 5));
                    this.binding.tvLabelLevel7.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 6));
                    this.binding.tvLabelLevel8.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 7));
                    break;
                case 9:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    this.binding.tvLabelLevel4.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 3));
                    this.binding.tvLabelLevel5.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 4));
                    this.binding.tvLabelLevel6.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 5));
                    this.binding.tvLabelLevel7.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 6));
                    this.binding.tvLabelLevel8.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 7));
                    this.binding.tvLabelLevel9.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 8));
                    break;
                case 10:
                    this.binding.tvLabelLevel1.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 0));
                    this.binding.tvLabelLevel2.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 1));
                    this.binding.tvLabelLevel3.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 2));
                    this.binding.tvLabelLevel4.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 3));
                    this.binding.tvLabelLevel5.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 4));
                    this.binding.tvLabelLevel6.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 5));
                    this.binding.tvLabelLevel7.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 6));
                    this.binding.tvLabelLevel8.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 7));
                    this.binding.tvLabelLevel9.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 8));
                    this.binding.tvLabelLevel10.setText(DaftarWilayahAdapter.INSTANCE.getRegionLabel(regionMetadata, 9));
                    break;
                default:
                    System.out.print((Object) "x is neither 1 nor 2");
                    break;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public UpdateListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemDaftarWilayahBinding binding = (ItemDaftarWilayahBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_daftar_wilayah, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new UpdateListingViewHolder(binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(UpdateListingViewHolder holder, int position) {
        SamplingRegionEntity samplingRegionEntity;
        Object next;
        Intrinsics.checkNotNullParameter(holder, "holder");
        final AssignmentRegionWilayahPojo assignmentRegionWilayahPojo = this.assignments.get(position);
        holder.bind(assignmentRegionWilayahPojo, this.viewModel);
        holder.getBinding().executePendingBindings();
        String wrappedDataKey = AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(assignmentRegionWilayahPojo.getAssignmentRegion().getRegion_id(), assignmentRegionWilayahPojo.getAssignmentRegion().getSurvey_period_id());
        holder.getBinding().imgWrappedDataKey.setVisibility((wrappedDataKey == null || StringsKt.isBlank(wrappedDataKey)) ^ true ? 0 : 8);
        holder.getBinding().btnRekapWilayah.setVisibility(this.isRekapWilayahEnabled ? 0 : 8);
        final String smallest_region_full_code = assignmentRegionWilayahPojo.getAssignmentRegion().getSmallest_region_full_code();
        final String smallestRegionName = INSTANCE.getSmallestRegionName(assignmentRegionWilayahPojo.getAssignmentRegion().getRegion());
        List<SamplingRegionEntity> list = this.mSamplingRegionOfflines;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (Intrinsics.areEqual(((SamplingRegionEntity) next).getFullcode(), smallest_region_full_code)) {
                        break;
                    }
                }
            }
            samplingRegionEntity = (SamplingRegionEntity) next;
        } else {
            samplingRegionEntity = null;
        }
        holder.getBinding().tvOfflineSampling.setVisibility(StringsKt.equals$default(samplingRegionEntity != null ? samplingRegionEntity.getMode() : null, "OFFLINE", false, 2, null) ? 0 : 4);
        holder.getBinding().updateListingLayout.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DaftarWilayahAdapter.onBindViewHolder$lambda$1(this.f$0, assignmentRegionWilayahPojo, smallest_region_full_code, smallestRegionName, view);
            }
        });
        holder.getBinding().btnRekapWilayah.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DaftarWilayahAdapter.onBindViewHolder$lambda$4(assignmentRegionWilayahPojo, this, smallest_region_full_code, smallestRegionName, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(final DaftarWilayahAdapter this$0, final AssignmentRegionWilayahPojo current, final String str, final String str2, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(current, "$current");
        ThrottledOnClicklistener throttledOnClicklistener = new ThrottledOnClicklistener(new Function1<View, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahAdapter$onBindViewHolder$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                AssignmentListActivity.Companion companion = AssignmentListActivity.INSTANCE;
                Context context = this.this$0.getContext();
                String surveyId = this.this$0.getSurveyId();
                String survey_period_id = current.getAssignmentRegion().getSurvey_period_id();
                String region_id = current.getAssignmentRegion().getRegion_id();
                String str3 = str;
                if (str3 == null) {
                    str3 = "";
                }
                companion.startActivity(context, surveyId, survey_period_id, region_id, str3, str2, this.this$0.getTemplateId(), this.this$0.getIsPencacah(), this.this$0.getCanAddSample());
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }
        });
        Intrinsics.checkNotNullExpressionValue(it, "it");
        throttledOnClicklistener.onClick(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$4(AssignmentRegionWilayahPojo current, DaftarWilayahAdapter this$0, String str, String str2, View view) {
        Intrinsics.checkNotNullParameter(current, "$current");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String region_id = current.getAssignmentRegion().getRegion_id();
        String survey_period_id = current.getAssignmentRegion().getSurvey_period_id();
        if (region_id != null) {
            String str3 = survey_period_id;
            if (!(str3 == null || str3.length() == 0)) {
                String str4 = this$0.templateId;
                if (!(str4 == null || str4.length() == 0)) {
                    StringBuilder sb = new StringBuilder();
                    if (str == null) {
                        str = "";
                    }
                    sb.append(str);
                    String str5 = str2;
                    if (!(str5 == null || str5.length() == 0)) {
                        if (sb.length() > 0) {
                            sb.append(" - ");
                        }
                        sb.append(str2);
                    }
                    String string = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
                    Intent intent = new Intent(this$0.context, (Class<?>) RekapWilayahActivity.class);
                    intent.putExtra("REGION_ID", region_id);
                    intent.putExtra("PERIODE_ID", survey_period_id);
                    intent.putExtra("TEMPLATE_ID", this$0.templateId);
                    intent.putExtra("WILAYAH", string);
                    this$0.context.startActivity(intent);
                    return;
                }
            }
        }
        Toast.makeText(this$0.context, "Missing required data for recap view", 0).show();
    }

    public final void setData$app_release(List<AssignmentRegionWilayahPojo> data, List<SamplingRegionEntity> samplingRegionOffline) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.assignments = CollectionsKt.toMutableList((Collection) data);
        this.mSamplingRegionOfflines = samplingRegionOffline;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.assignments.size();
    }
}

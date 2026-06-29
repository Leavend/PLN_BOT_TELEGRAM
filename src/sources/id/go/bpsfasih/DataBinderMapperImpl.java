package id.go.bpsfasih;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.databinding.ActivityAssignmentlistBindingImpl;
import id.go.bpsfasih.databinding.ActivityBackupRestoreBindingImpl;
import id.go.bpsfasih.databinding.ActivityBantuanBindingImpl;
import id.go.bpsfasih.databinding.ActivityDaftarWilayahBindingImpl;
import id.go.bpsfasih.databinding.ActivityFormgearBindingImpl;
import id.go.bpsfasih.databinding.ActivityFormgearDownloadBindingImpl;
import id.go.bpsfasih.databinding.ActivityLoginBindingImpl;
import id.go.bpsfasih.databinding.ActivityMainmenuBindingImpl;
import id.go.bpsfasih.databinding.ActivityMemoryInfoBindingImpl;
import id.go.bpsfasih.databinding.ActivityMiniDashboardBindingImpl;
import id.go.bpsfasih.databinding.ActivityPeriodeBindingImpl;
import id.go.bpsfasih.databinding.ActivityRekapWilayahBindingImpl;
import id.go.bpsfasih.databinding.ActivitySistemBindingImpl;
import id.go.bpsfasih.databinding.ActivitySurveyBindingImpl;
import id.go.bpsfasih.databinding.ActivitySyncAnswerPartialBindingImpl;
import id.go.bpsfasih.databinding.ActivityTarikSampelBindingImpl;
import id.go.bpsfasih.databinding.ActivityTarikSampelOfflineBindingImpl;
import id.go.bpsfasih.databinding.ActivityUnduhChangeModeBindingImpl;
import id.go.bpsfasih.databinding.ActivityUploadBindingImpl;
import id.go.bpsfasih.databinding.AssignmentStructureBindingImpl;
import id.go.bpsfasih.databinding.BottomSheetBaseActivityBindingImpl;
import id.go.bpsfasih.databinding.FragmentHomeBindingImpl;
import id.go.bpsfasih.databinding.FragmentHomeDaftarSurveyBindingImpl;
import id.go.bpsfasih.databinding.FragmentPengaturanBindingImpl;
import id.go.bpsfasih.databinding.FragmentUploadingBindingImpl;
import id.go.bpsfasih.databinding.ItemCustomColumnBindingImpl;
import id.go.bpsfasih.databinding.ItemDaftarUnduhChangeModeBindingImpl;
import id.go.bpsfasih.databinding.ItemDaftarWilayahBindingImpl;
import id.go.bpsfasih.databinding.ItemFragmentUploadingBindingImpl;
import id.go.bpsfasih.databinding.ItemPeriodeBindingImpl;
import id.go.bpsfasih.databinding.ItemSortAssignmentBindingImpl;
import id.go.bpsfasih.databinding.ItemSurveyBindingImpl;
import id.go.bpsfasih.databinding.ItemSyncAnswerPartialBindingImpl;
import id.go.bpsfasih.databinding.ItemSyncAnswerPartialPeriodeBindingImpl;
import id.go.bpsfasih.databinding.ItemSyncBindingImpl;
import id.go.bpsfasih.databinding.ViewToolbarBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYASSIGNMENTLIST = 1;
    private static final int LAYOUT_ACTIVITYBACKUPRESTORE = 2;
    private static final int LAYOUT_ACTIVITYBANTUAN = 3;
    private static final int LAYOUT_ACTIVITYDAFTARWILAYAH = 4;
    private static final int LAYOUT_ACTIVITYFORMGEAR = 5;
    private static final int LAYOUT_ACTIVITYFORMGEARDOWNLOAD = 6;
    private static final int LAYOUT_ACTIVITYLOGIN = 7;
    private static final int LAYOUT_ACTIVITYMAINMENU = 8;
    private static final int LAYOUT_ACTIVITYMEMORYINFO = 9;
    private static final int LAYOUT_ACTIVITYMINIDASHBOARD = 10;
    private static final int LAYOUT_ACTIVITYPERIODE = 11;
    private static final int LAYOUT_ACTIVITYREKAPWILAYAH = 12;
    private static final int LAYOUT_ACTIVITYSISTEM = 13;
    private static final int LAYOUT_ACTIVITYSURVEY = 14;
    private static final int LAYOUT_ACTIVITYSYNCANSWERPARTIAL = 15;
    private static final int LAYOUT_ACTIVITYTARIKSAMPEL = 16;
    private static final int LAYOUT_ACTIVITYTARIKSAMPELOFFLINE = 17;
    private static final int LAYOUT_ACTIVITYUNDUHCHANGEMODE = 18;
    private static final int LAYOUT_ACTIVITYUPLOAD = 19;
    private static final int LAYOUT_ASSIGNMENTSTRUCTURE = 20;
    private static final int LAYOUT_BOTTOMSHEETBASEACTIVITY = 21;
    private static final int LAYOUT_FRAGMENTHOME = 22;
    private static final int LAYOUT_FRAGMENTHOMEDAFTARSURVEY = 23;
    private static final int LAYOUT_FRAGMENTPENGATURAN = 24;
    private static final int LAYOUT_FRAGMENTUPLOADING = 25;
    private static final int LAYOUT_ITEMCUSTOMCOLUMN = 26;
    private static final int LAYOUT_ITEMDAFTARUNDUHCHANGEMODE = 27;
    private static final int LAYOUT_ITEMDAFTARWILAYAH = 28;
    private static final int LAYOUT_ITEMFRAGMENTUPLOADING = 29;
    private static final int LAYOUT_ITEMPERIODE = 30;
    private static final int LAYOUT_ITEMSORTASSIGNMENT = 31;
    private static final int LAYOUT_ITEMSURVEY = 32;
    private static final int LAYOUT_ITEMSYNC = 33;
    private static final int LAYOUT_ITEMSYNCANSWERPARTIAL = 34;
    private static final int LAYOUT_ITEMSYNCANSWERPARTIALPERIODE = 35;
    private static final int LAYOUT_VIEWTOOLBAR = 36;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(36);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_assignmentlist, 1);
        sparseIntArray.put(R.layout.activity_backup_restore, 2);
        sparseIntArray.put(R.layout.activity_bantuan, 3);
        sparseIntArray.put(R.layout.activity_daftar_wilayah, 4);
        sparseIntArray.put(R.layout.activity_formgear, 5);
        sparseIntArray.put(R.layout.activity_formgear_download, 6);
        sparseIntArray.put(R.layout.activity_login, 7);
        sparseIntArray.put(R.layout.activity_mainmenu, 8);
        sparseIntArray.put(R.layout.activity_memory_info, 9);
        sparseIntArray.put(R.layout.activity_mini_dashboard, 10);
        sparseIntArray.put(R.layout.activity_periode, 11);
        sparseIntArray.put(R.layout.activity_rekap_wilayah, 12);
        sparseIntArray.put(R.layout.activity_sistem, 13);
        sparseIntArray.put(R.layout.activity_survey, 14);
        sparseIntArray.put(R.layout.activity_sync_answer_partial, 15);
        sparseIntArray.put(R.layout.activity_tarik_sampel, 16);
        sparseIntArray.put(R.layout.activity_tarik_sampel_offline, 17);
        sparseIntArray.put(R.layout.activity_unduh_change_mode, 18);
        sparseIntArray.put(R.layout.activity_upload, 19);
        sparseIntArray.put(R.layout.assignment_structure, 20);
        sparseIntArray.put(R.layout.bottom_sheet_base_activity, 21);
        sparseIntArray.put(R.layout.fragment_home, 22);
        sparseIntArray.put(R.layout.fragment_home_daftar_survey, 23);
        sparseIntArray.put(R.layout.fragment_pengaturan, 24);
        sparseIntArray.put(R.layout.fragment_uploading, 25);
        sparseIntArray.put(R.layout.item_custom_column, 26);
        sparseIntArray.put(R.layout.item_daftar_unduh_change_mode, 27);
        sparseIntArray.put(R.layout.item_daftar_wilayah, 28);
        sparseIntArray.put(R.layout.item_fragment_uploading, 29);
        sparseIntArray.put(R.layout.item_periode, 30);
        sparseIntArray.put(R.layout.item_sort_assignment, 31);
        sparseIntArray.put(R.layout.item_survey, 32);
        sparseIntArray.put(R.layout.item_sync, 33);
        sparseIntArray.put(R.layout.item_sync_answer_partial, 34);
        sparseIntArray.put(R.layout.item_sync_answer_partial_periode, 35);
        sparseIntArray.put(R.layout.view_toolbar, 36);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
        int i = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (i <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i) {
            case 1:
                if ("layout/activity_assignmentlist_0".equals(tag)) {
                    return new ActivityAssignmentlistBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_assignmentlist is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_backup_restore_0".equals(tag)) {
                    return new ActivityBackupRestoreBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_backup_restore is invalid. Received: " + tag);
            case 3:
                if ("layout/activity_bantuan_0".equals(tag)) {
                    return new ActivityBantuanBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bantuan is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_daftar_wilayah_0".equals(tag)) {
                    return new ActivityDaftarWilayahBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_daftar_wilayah is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_formgear_0".equals(tag)) {
                    return new ActivityFormgearBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_formgear is invalid. Received: " + tag);
            case 6:
                if ("layout/activity_formgear_download_0".equals(tag)) {
                    return new ActivityFormgearDownloadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_formgear_download is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_login_0".equals(tag)) {
                    return new ActivityLoginBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
            case 8:
                if ("layout/activity_mainmenu_0".equals(tag)) {
                    return new ActivityMainmenuBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_mainmenu is invalid. Received: " + tag);
            case 9:
                if ("layout/activity_memory_info_0".equals(tag)) {
                    return new ActivityMemoryInfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_memory_info is invalid. Received: " + tag);
            case 10:
                if ("layout/activity_mini_dashboard_0".equals(tag)) {
                    return new ActivityMiniDashboardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_mini_dashboard is invalid. Received: " + tag);
            case 11:
                if ("layout/activity_periode_0".equals(tag)) {
                    return new ActivityPeriodeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_periode is invalid. Received: " + tag);
            case 12:
                if ("layout/activity_rekap_wilayah_0".equals(tag)) {
                    return new ActivityRekapWilayahBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_rekap_wilayah is invalid. Received: " + tag);
            case 13:
                if ("layout/activity_sistem_0".equals(tag)) {
                    return new ActivitySistemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_sistem is invalid. Received: " + tag);
            case 14:
                if ("layout/activity_survey_0".equals(tag)) {
                    return new ActivitySurveyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_survey is invalid. Received: " + tag);
            case 15:
                if ("layout/activity_sync_answer_partial_0".equals(tag)) {
                    return new ActivitySyncAnswerPartialBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_sync_answer_partial is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_tarik_sampel_0".equals(tag)) {
                    return new ActivityTarikSampelBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_tarik_sampel is invalid. Received: " + tag);
            case 17:
                if ("layout/activity_tarik_sampel_offline_0".equals(tag)) {
                    return new ActivityTarikSampelOfflineBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_tarik_sampel_offline is invalid. Received: " + tag);
            case 18:
                if ("layout/activity_unduh_change_mode_0".equals(tag)) {
                    return new ActivityUnduhChangeModeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_unduh_change_mode is invalid. Received: " + tag);
            case 19:
                if ("layout/activity_upload_0".equals(tag)) {
                    return new ActivityUploadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_upload is invalid. Received: " + tag);
            case 20:
                if ("layout/assignment_structure_0".equals(tag)) {
                    return new AssignmentStructureBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for assignment_structure is invalid. Received: " + tag);
            case 21:
                if ("layout/bottom_sheet_base_activity_0".equals(tag)) {
                    return new BottomSheetBaseActivityBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bottom_sheet_base_activity is invalid. Received: " + tag);
            case 22:
                if ("layout/fragment_home_0".equals(tag)) {
                    return new FragmentHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
            case 23:
                if ("layout/fragment_home_daftar_survey_0".equals(tag)) {
                    return new FragmentHomeDaftarSurveyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home_daftar_survey is invalid. Received: " + tag);
            case 24:
                if ("layout/fragment_pengaturan_0".equals(tag)) {
                    return new FragmentPengaturanBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pengaturan is invalid. Received: " + tag);
            case 25:
                if ("layout/fragment_uploading_0".equals(tag)) {
                    return new FragmentUploadingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_uploading is invalid. Received: " + tag);
            case 26:
                if ("layout/item_custom_column_0".equals(tag)) {
                    return new ItemCustomColumnBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_custom_column is invalid. Received: " + tag);
            case 27:
                if ("layout/item_daftar_unduh_change_mode_0".equals(tag)) {
                    return new ItemDaftarUnduhChangeModeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_daftar_unduh_change_mode is invalid. Received: " + tag);
            case 28:
                if ("layout/item_daftar_wilayah_0".equals(tag)) {
                    return new ItemDaftarWilayahBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_daftar_wilayah is invalid. Received: " + tag);
            case 29:
                if ("layout/item_fragment_uploading_0".equals(tag)) {
                    return new ItemFragmentUploadingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_fragment_uploading is invalid. Received: " + tag);
            case 30:
                if ("layout/item_periode_0".equals(tag)) {
                    return new ItemPeriodeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_periode is invalid. Received: " + tag);
            case 31:
                if ("layout/item_sort_assignment_0".equals(tag)) {
                    return new ItemSortAssignmentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sort_assignment is invalid. Received: " + tag);
            case 32:
                if ("layout/item_survey_0".equals(tag)) {
                    return new ItemSurveyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_survey is invalid. Received: " + tag);
            case 33:
                if ("layout/item_sync_0".equals(tag)) {
                    return new ItemSyncBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sync is invalid. Received: " + tag);
            case 34:
                if ("layout/item_sync_answer_partial_0".equals(tag)) {
                    return new ItemSyncAnswerPartialBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sync_answer_partial is invalid. Received: " + tag);
            case 35:
                if ("layout/item_sync_answer_partial_periode_0".equals(tag)) {
                    return new ItemSyncAnswerPartialPeriodeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sync_answer_partial_periode is invalid. Received: " + tag);
            case 36:
                if ("layout/view_toolbar_0".equals(tag)) {
                    return new ViewToolbarBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for view_toolbar is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
        if (views == null || views.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId) <= 0 || views[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String tag) {
        Integer num;
        if (tag == null || (num = InnerLayoutIdLookup.sKeys.get(tag)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int localId) {
        return InnerBrLookup.sKeys.get(localId);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(4);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "secondToViewModel");
            sparseArray.put(2, "secondViewModel");
            sparseArray.put(3, "viewModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> map = new HashMap<>(36);
            sKeys = map;
            map.put("layout/activity_assignmentlist_0", Integer.valueOf(R.layout.activity_assignmentlist));
            map.put("layout/activity_backup_restore_0", Integer.valueOf(R.layout.activity_backup_restore));
            map.put("layout/activity_bantuan_0", Integer.valueOf(R.layout.activity_bantuan));
            map.put("layout/activity_daftar_wilayah_0", Integer.valueOf(R.layout.activity_daftar_wilayah));
            map.put("layout/activity_formgear_0", Integer.valueOf(R.layout.activity_formgear));
            map.put("layout/activity_formgear_download_0", Integer.valueOf(R.layout.activity_formgear_download));
            map.put("layout/activity_login_0", Integer.valueOf(R.layout.activity_login));
            map.put("layout/activity_mainmenu_0", Integer.valueOf(R.layout.activity_mainmenu));
            map.put("layout/activity_memory_info_0", Integer.valueOf(R.layout.activity_memory_info));
            map.put("layout/activity_mini_dashboard_0", Integer.valueOf(R.layout.activity_mini_dashboard));
            map.put("layout/activity_periode_0", Integer.valueOf(R.layout.activity_periode));
            map.put("layout/activity_rekap_wilayah_0", Integer.valueOf(R.layout.activity_rekap_wilayah));
            map.put("layout/activity_sistem_0", Integer.valueOf(R.layout.activity_sistem));
            map.put("layout/activity_survey_0", Integer.valueOf(R.layout.activity_survey));
            map.put("layout/activity_sync_answer_partial_0", Integer.valueOf(R.layout.activity_sync_answer_partial));
            map.put("layout/activity_tarik_sampel_0", Integer.valueOf(R.layout.activity_tarik_sampel));
            map.put("layout/activity_tarik_sampel_offline_0", Integer.valueOf(R.layout.activity_tarik_sampel_offline));
            map.put("layout/activity_unduh_change_mode_0", Integer.valueOf(R.layout.activity_unduh_change_mode));
            map.put("layout/activity_upload_0", Integer.valueOf(R.layout.activity_upload));
            map.put("layout/assignment_structure_0", Integer.valueOf(R.layout.assignment_structure));
            map.put("layout/bottom_sheet_base_activity_0", Integer.valueOf(R.layout.bottom_sheet_base_activity));
            map.put("layout/fragment_home_0", Integer.valueOf(R.layout.fragment_home));
            map.put("layout/fragment_home_daftar_survey_0", Integer.valueOf(R.layout.fragment_home_daftar_survey));
            map.put("layout/fragment_pengaturan_0", Integer.valueOf(R.layout.fragment_pengaturan));
            map.put("layout/fragment_uploading_0", Integer.valueOf(R.layout.fragment_uploading));
            map.put("layout/item_custom_column_0", Integer.valueOf(R.layout.item_custom_column));
            map.put("layout/item_daftar_unduh_change_mode_0", Integer.valueOf(R.layout.item_daftar_unduh_change_mode));
            map.put("layout/item_daftar_wilayah_0", Integer.valueOf(R.layout.item_daftar_wilayah));
            map.put("layout/item_fragment_uploading_0", Integer.valueOf(R.layout.item_fragment_uploading));
            map.put("layout/item_periode_0", Integer.valueOf(R.layout.item_periode));
            map.put("layout/item_sort_assignment_0", Integer.valueOf(R.layout.item_sort_assignment));
            map.put("layout/item_survey_0", Integer.valueOf(R.layout.item_survey));
            map.put("layout/item_sync_0", Integer.valueOf(R.layout.item_sync));
            map.put("layout/item_sync_answer_partial_0", Integer.valueOf(R.layout.item_sync_answer_partial));
            map.put("layout/item_sync_answer_partial_periode_0", Integer.valueOf(R.layout.item_sync_answer_partial_periode));
            map.put("layout/view_toolbar_0", Integer.valueOf(R.layout.view_toolbar));
        }
    }
}

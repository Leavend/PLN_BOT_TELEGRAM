package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ParadataModel.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b2\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bw\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0015J\u0011\u00109\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u0011\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010#J\u0092\u0001\u0010C\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÆ\u0001¢\u0006\u0002\u0010DJ\u0013\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020\u0013HÖ\u0001J\t\u0010I\u001a\u00020\u0006HÖ\u0001R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001b\"\u0004\b(\u0010\u001dR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b7\u0010#\"\u0004\b8\u0010%¨\u0006J"}, d2 = {"Lid/go/bpsfasih/domain/models/ParadataModel;", "", "timestampEntities", "", "Lid/go/bpsfasih/domain/models/TimestampModel;", "formgear_version", "", "deviceInfo", "Lid/go/bpsfasih/domain/models/DeviceInfo;", "data", "signalInfo", "Lid/go/bpsfasih/domain/models/SignalInfoModel;", "memoryInfo", "Lid/go/bpsfasih/domain/models/MemoryInfo;", "storageInfo", "Lid/go/bpsfasih/domain/models/StorageInfo;", "actionLogEntities", "Lid/go/bpsfasih/domain/models/ActionLog;", "totalDuration", "", "encryptionType", "(Ljava/util/List;Ljava/lang/String;Lid/go/bpsfasih/domain/models/DeviceInfo;Ljava/lang/String;Lid/go/bpsfasih/domain/models/SignalInfoModel;Lid/go/bpsfasih/domain/models/MemoryInfo;Lid/go/bpsfasih/domain/models/StorageInfo;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getActionLogEntities", "()Ljava/util/List;", "setActionLogEntities", "(Ljava/util/List;)V", "getData", "()Ljava/lang/String;", "setData", "(Ljava/lang/String;)V", "getDeviceInfo", "()Lid/go/bpsfasih/domain/models/DeviceInfo;", "setDeviceInfo", "(Lid/go/bpsfasih/domain/models/DeviceInfo;)V", "getEncryptionType", "()Ljava/lang/Integer;", "setEncryptionType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getFormgear_version", "setFormgear_version", "getMemoryInfo", "()Lid/go/bpsfasih/domain/models/MemoryInfo;", "setMemoryInfo", "(Lid/go/bpsfasih/domain/models/MemoryInfo;)V", "getSignalInfo", "()Lid/go/bpsfasih/domain/models/SignalInfoModel;", "setSignalInfo", "(Lid/go/bpsfasih/domain/models/SignalInfoModel;)V", "getStorageInfo", "()Lid/go/bpsfasih/domain/models/StorageInfo;", "setStorageInfo", "(Lid/go/bpsfasih/domain/models/StorageInfo;)V", "getTimestampEntities", "setTimestampEntities", "getTotalDuration", "setTotalDuration", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/util/List;Ljava/lang/String;Lid/go/bpsfasih/domain/models/DeviceInfo;Ljava/lang/String;Lid/go/bpsfasih/domain/models/SignalInfoModel;Lid/go/bpsfasih/domain/models/MemoryInfo;Lid/go/bpsfasih/domain/models/StorageInfo;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)Lid/go/bpsfasih/domain/models/ParadataModel;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ParadataModel {
    public static final int $stable = 8;
    private List<ActionLog> actionLogEntities;
    private String data;
    private DeviceInfo deviceInfo;
    private Integer encryptionType;
    private String formgear_version;
    private MemoryInfo memoryInfo;
    private SignalInfoModel signalInfo;
    private StorageInfo storageInfo;
    private List<TimestampModel> timestampEntities;
    private Integer totalDuration;

    public final List<TimestampModel> component1() {
        return this.timestampEntities;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getEncryptionType() {
        return this.encryptionType;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFormgear_version() {
        return this.formgear_version;
    }

    /* renamed from: component3, reason: from getter */
    public final DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    /* renamed from: component4, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* renamed from: component5, reason: from getter */
    public final SignalInfoModel getSignalInfo() {
        return this.signalInfo;
    }

    /* renamed from: component6, reason: from getter */
    public final MemoryInfo getMemoryInfo() {
        return this.memoryInfo;
    }

    /* renamed from: component7, reason: from getter */
    public final StorageInfo getStorageInfo() {
        return this.storageInfo;
    }

    public final List<ActionLog> component8() {
        return this.actionLogEntities;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getTotalDuration() {
        return this.totalDuration;
    }

    public final ParadataModel copy(List<TimestampModel> timestampEntities, String formgear_version, DeviceInfo deviceInfo, String data, SignalInfoModel signalInfo, MemoryInfo memoryInfo, StorageInfo storageInfo, List<ActionLog> actionLogEntities, Integer totalDuration, Integer encryptionType) {
        return new ParadataModel(timestampEntities, formgear_version, deviceInfo, data, signalInfo, memoryInfo, storageInfo, actionLogEntities, totalDuration, encryptionType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParadataModel)) {
            return false;
        }
        ParadataModel paradataModel = (ParadataModel) other;
        return Intrinsics.areEqual(this.timestampEntities, paradataModel.timestampEntities) && Intrinsics.areEqual(this.formgear_version, paradataModel.formgear_version) && Intrinsics.areEqual(this.deviceInfo, paradataModel.deviceInfo) && Intrinsics.areEqual(this.data, paradataModel.data) && Intrinsics.areEqual(this.signalInfo, paradataModel.signalInfo) && Intrinsics.areEqual(this.memoryInfo, paradataModel.memoryInfo) && Intrinsics.areEqual(this.storageInfo, paradataModel.storageInfo) && Intrinsics.areEqual(this.actionLogEntities, paradataModel.actionLogEntities) && Intrinsics.areEqual(this.totalDuration, paradataModel.totalDuration) && Intrinsics.areEqual(this.encryptionType, paradataModel.encryptionType);
    }

    public int hashCode() {
        List<TimestampModel> list = this.timestampEntities;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        String str = this.formgear_version;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        DeviceInfo deviceInfo = this.deviceInfo;
        int iHashCode3 = (iHashCode2 + (deviceInfo == null ? 0 : deviceInfo.hashCode())) * 31;
        String str2 = this.data;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        SignalInfoModel signalInfoModel = this.signalInfo;
        int iHashCode5 = (iHashCode4 + (signalInfoModel == null ? 0 : signalInfoModel.hashCode())) * 31;
        MemoryInfo memoryInfo = this.memoryInfo;
        int iHashCode6 = (iHashCode5 + (memoryInfo == null ? 0 : memoryInfo.hashCode())) * 31;
        StorageInfo storageInfo = this.storageInfo;
        int iHashCode7 = (iHashCode6 + (storageInfo == null ? 0 : storageInfo.hashCode())) * 31;
        List<ActionLog> list2 = this.actionLogEntities;
        int iHashCode8 = (iHashCode7 + (list2 == null ? 0 : list2.hashCode())) * 31;
        Integer num = this.totalDuration;
        int iHashCode9 = (iHashCode8 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.encryptionType;
        return iHashCode9 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "ParadataModel(timestampEntities=" + this.timestampEntities + ", formgear_version=" + this.formgear_version + ", deviceInfo=" + this.deviceInfo + ", data=" + this.data + ", signalInfo=" + this.signalInfo + ", memoryInfo=" + this.memoryInfo + ", storageInfo=" + this.storageInfo + ", actionLogEntities=" + this.actionLogEntities + ", totalDuration=" + this.totalDuration + ", encryptionType=" + this.encryptionType + ")";
    }

    public ParadataModel(List<TimestampModel> list, String str, DeviceInfo deviceInfo, String str2, SignalInfoModel signalInfoModel, MemoryInfo memoryInfo, StorageInfo storageInfo, List<ActionLog> list2, Integer num, Integer num2) {
        this.timestampEntities = list;
        this.formgear_version = str;
        this.deviceInfo = deviceInfo;
        this.data = str2;
        this.signalInfo = signalInfoModel;
        this.memoryInfo = memoryInfo;
        this.storageInfo = storageInfo;
        this.actionLogEntities = list2;
        this.totalDuration = num;
        this.encryptionType = num2;
    }

    public /* synthetic */ ParadataModel(List list, String str, DeviceInfo deviceInfo, String str2, SignalInfoModel signalInfoModel, MemoryInfo memoryInfo, StorageInfo storageInfo, List list2, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, str, deviceInfo, str2, signalInfoModel, memoryInfo, storageInfo, list2, num, (i & 512) != 0 ? null : num2);
    }

    public final List<TimestampModel> getTimestampEntities() {
        return this.timestampEntities;
    }

    public final void setTimestampEntities(List<TimestampModel> list) {
        this.timestampEntities = list;
    }

    public final String getFormgear_version() {
        return this.formgear_version;
    }

    public final void setFormgear_version(String str) {
        this.formgear_version = str;
    }

    public final DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public final void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        this.data = str;
    }

    public final SignalInfoModel getSignalInfo() {
        return this.signalInfo;
    }

    public final void setSignalInfo(SignalInfoModel signalInfoModel) {
        this.signalInfo = signalInfoModel;
    }

    public final MemoryInfo getMemoryInfo() {
        return this.memoryInfo;
    }

    public final void setMemoryInfo(MemoryInfo memoryInfo) {
        this.memoryInfo = memoryInfo;
    }

    public final StorageInfo getStorageInfo() {
        return this.storageInfo;
    }

    public final void setStorageInfo(StorageInfo storageInfo) {
        this.storageInfo = storageInfo;
    }

    public final List<ActionLog> getActionLogEntities() {
        return this.actionLogEntities;
    }

    public final void setActionLogEntities(List<ActionLog> list) {
        this.actionLogEntities = list;
    }

    public final Integer getTotalDuration() {
        return this.totalDuration;
    }

    public final void setTotalDuration(Integer num) {
        this.totalDuration = num;
    }

    public final Integer getEncryptionType() {
        return this.encryptionType;
    }

    public final void setEncryptionType(Integer num) {
        this.encryptionType = num;
    }
}

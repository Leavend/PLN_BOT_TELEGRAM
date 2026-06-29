package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import id.go.bpsfasih.ui.formGear.FormGearActivity;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\bu\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B§\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0004\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\"J\u000b\u0010a\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010{\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010|\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010=J\u000b\u0010}\u001a\u0004\u0018\u00010\u0004HÆ\u0003Jê\u0002\u0010~\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u007fJ\u0016\u0010\u0080\u0001\u001a\u00020\f2\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u0001HÖ\u0003J\u000b\u0010\u0083\u0001\u001a\u00030\u0084\u0001HÖ\u0001J\n\u0010\u0085\u0001\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010$\"\u0004\b(\u0010&R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010$\"\u0004\b.\u0010&R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010$\"\u0004\b0\u0010&R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010$\"\u0004\b2\u0010&R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010$\"\u0004\b4\u0010&R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010$\"\u0004\b6\u0010&R \u0010\u001a\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010$\"\u0004\b:\u0010&R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010$\"\u0004\b<\u0010&R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010@\u001a\u0004\b\u000b\u0010=\"\u0004\b>\u0010?R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010$\"\u0004\bB\u0010&R \u0010\u0017\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010$\"\u0004\bD\u0010&R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010$\"\u0004\bF\u0010&R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010$\"\u0004\bH\u0010&R \u0010 \u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010$\"\u0004\bJ\u0010&R \u0010!\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010$\"\u0004\bL\u0010&R \u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010$\"\u0004\bN\u0010&R \u0010\u001f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010$\"\u0004\bP\u0010&R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010$\"\u0004\bR\u0010&R \u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010$\"\u0004\bT\u0010&R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010$\"\u0004\bV\u0010&R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010$\"\u0004\bX\u0010&R \u0010\u001d\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010$\"\u0004\bZ\u0010&R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010$\"\u0004\b\\\u0010&R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010$\"\u0004\b^\u0010&R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010$\"\u0004\b`\u0010&¨\u0006\u0086\u0001"}, d2 = {"Lid/go/bpsfasih/data/local/entities/DataAssignment;", "Ljava/io/Serializable;", "()V", "bangunanSensus", "", "sls_number", "address", "householdCode", "rutaId", "name", "bangunanFisik", "isLiveIn", "", "address_number", "strata", "prop", "kab_reg", "kec_dist", "desa_village", "blok", "cawi_identifier", "cawi_pin", "provinsi", "kabupatenKota", "kecamatan", "data", "desa", "alamat", "namaDusun", "rtRw", "nomorKk", "namaKepalaKeluarga", "latitude", "longitude", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getAddress_number", "setAddress_number", "getAlamat", "setAlamat", "getBangunanFisik", "setBangunanFisik", "getBangunanSensus", "setBangunanSensus", "getBlok", "setBlok", "getCawi_identifier", "setCawi_identifier", "getCawi_pin", "setCawi_pin", "getData", "setData", "getDesa", "setDesa", "getDesa_village", "setDesa_village", "getHouseholdCode", "setHouseholdCode", "()Ljava/lang/Boolean;", "setLiveIn", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getKab_reg", "setKab_reg", "getKabupatenKota", "setKabupatenKota", "getKec_dist", "setKec_dist", "getKecamatan", "setKecamatan", "getLatitude", "setLatitude", "getLongitude", "setLongitude", "getNamaDusun", "setNamaDusun", "getNamaKepalaKeluarga", "setNamaKepalaKeluarga", "getName", "setName", "getNomorKk", "setNomorKk", "getProp", "setProp", "getProvinsi", "setProvinsi", "getRtRw", "setRtRw", "getRutaId", "setRutaId", "getSls_number", "setSls_number", "getStrata", "setStrata", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lid/go/bpsfasih/data/local/entities/DataAssignment;", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DataAssignment implements Serializable {
    public static final int $stable = 8;
    private String address;
    private String address_number;
    private String alamat;
    private String bangunanFisik;
    private String bangunanSensus;
    private String blok;
    private String cawi_identifier;
    private String cawi_pin;
    private String data;

    @SerializedName("desa/kelurahan/nagari")
    private String desa;
    private String desa_village;
    private String householdCode;
    private Boolean isLiveIn;
    private String kab_reg;

    @SerializedName("kabupaten/kota")
    private String kabupatenKota;
    private String kec_dist;
    private String kecamatan;

    @SerializedName(FormGearActivity.LAT)
    private String latitude;

    @SerializedName(FormGearActivity.LON)
    private String longitude;

    @SerializedName("nama_dusun/_dukuh/_kampung/_lingkungan/_banjar/_jorong")
    private String namaDusun;

    @SerializedName("nama_kepala_keluarga")
    private String namaKepalaKeluarga;
    private String name;

    @SerializedName("nomor_kartu_keluarga_(kk)")
    private String nomorKk;
    private String prop;
    private String provinsi;

    @SerializedName("rt/rw")
    private String rtRw;
    private String rutaId;
    private String sls_number;
    private String strata;

    /* renamed from: component1, reason: from getter */
    public final String getBangunanSensus() {
        return this.bangunanSensus;
    }

    /* renamed from: component10, reason: from getter */
    public final String getStrata() {
        return this.strata;
    }

    /* renamed from: component11, reason: from getter */
    public final String getProp() {
        return this.prop;
    }

    /* renamed from: component12, reason: from getter */
    public final String getKab_reg() {
        return this.kab_reg;
    }

    /* renamed from: component13, reason: from getter */
    public final String getKec_dist() {
        return this.kec_dist;
    }

    /* renamed from: component14, reason: from getter */
    public final String getDesa_village() {
        return this.desa_village;
    }

    /* renamed from: component15, reason: from getter */
    public final String getBlok() {
        return this.blok;
    }

    /* renamed from: component16, reason: from getter */
    public final String getCawi_identifier() {
        return this.cawi_identifier;
    }

    /* renamed from: component17, reason: from getter */
    public final String getCawi_pin() {
        return this.cawi_pin;
    }

    /* renamed from: component18, reason: from getter */
    public final String getProvinsi() {
        return this.provinsi;
    }

    /* renamed from: component19, reason: from getter */
    public final String getKabupatenKota() {
        return this.kabupatenKota;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSls_number() {
        return this.sls_number;
    }

    /* renamed from: component20, reason: from getter */
    public final String getKecamatan() {
        return this.kecamatan;
    }

    /* renamed from: component21, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* renamed from: component22, reason: from getter */
    public final String getDesa() {
        return this.desa;
    }

    /* renamed from: component23, reason: from getter */
    public final String getAlamat() {
        return this.alamat;
    }

    /* renamed from: component24, reason: from getter */
    public final String getNamaDusun() {
        return this.namaDusun;
    }

    /* renamed from: component25, reason: from getter */
    public final String getRtRw() {
        return this.rtRw;
    }

    /* renamed from: component26, reason: from getter */
    public final String getNomorKk() {
        return this.nomorKk;
    }

    /* renamed from: component27, reason: from getter */
    public final String getNamaKepalaKeluarga() {
        return this.namaKepalaKeluarga;
    }

    /* renamed from: component28, reason: from getter */
    public final String getLatitude() {
        return this.latitude;
    }

    /* renamed from: component29, reason: from getter */
    public final String getLongitude() {
        return this.longitude;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component4, reason: from getter */
    public final String getHouseholdCode() {
        return this.householdCode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getRutaId() {
        return this.rutaId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBangunanFisik() {
        return this.bangunanFisik;
    }

    /* renamed from: component8, reason: from getter */
    public final Boolean getIsLiveIn() {
        return this.isLiveIn;
    }

    /* renamed from: component9, reason: from getter */
    public final String getAddress_number() {
        return this.address_number;
    }

    public final DataAssignment copy(String bangunanSensus, String sls_number, String address, String householdCode, String rutaId, String name, String bangunanFisik, Boolean isLiveIn, String address_number, String strata, String prop, String kab_reg, String kec_dist, String desa_village, String blok, String cawi_identifier, String cawi_pin, String provinsi, String kabupatenKota, String kecamatan, String data, String desa, String alamat, String namaDusun, String rtRw, String nomorKk, String namaKepalaKeluarga, String latitude, String longitude) {
        return new DataAssignment(bangunanSensus, sls_number, address, householdCode, rutaId, name, bangunanFisik, isLiveIn, address_number, strata, prop, kab_reg, kec_dist, desa_village, blok, cawi_identifier, cawi_pin, provinsi, kabupatenKota, kecamatan, data, desa, alamat, namaDusun, rtRw, nomorKk, namaKepalaKeluarga, latitude, longitude);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataAssignment)) {
            return false;
        }
        DataAssignment dataAssignment = (DataAssignment) other;
        return Intrinsics.areEqual(this.bangunanSensus, dataAssignment.bangunanSensus) && Intrinsics.areEqual(this.sls_number, dataAssignment.sls_number) && Intrinsics.areEqual(this.address, dataAssignment.address) && Intrinsics.areEqual(this.householdCode, dataAssignment.householdCode) && Intrinsics.areEqual(this.rutaId, dataAssignment.rutaId) && Intrinsics.areEqual(this.name, dataAssignment.name) && Intrinsics.areEqual(this.bangunanFisik, dataAssignment.bangunanFisik) && Intrinsics.areEqual(this.isLiveIn, dataAssignment.isLiveIn) && Intrinsics.areEqual(this.address_number, dataAssignment.address_number) && Intrinsics.areEqual(this.strata, dataAssignment.strata) && Intrinsics.areEqual(this.prop, dataAssignment.prop) && Intrinsics.areEqual(this.kab_reg, dataAssignment.kab_reg) && Intrinsics.areEqual(this.kec_dist, dataAssignment.kec_dist) && Intrinsics.areEqual(this.desa_village, dataAssignment.desa_village) && Intrinsics.areEqual(this.blok, dataAssignment.blok) && Intrinsics.areEqual(this.cawi_identifier, dataAssignment.cawi_identifier) && Intrinsics.areEqual(this.cawi_pin, dataAssignment.cawi_pin) && Intrinsics.areEqual(this.provinsi, dataAssignment.provinsi) && Intrinsics.areEqual(this.kabupatenKota, dataAssignment.kabupatenKota) && Intrinsics.areEqual(this.kecamatan, dataAssignment.kecamatan) && Intrinsics.areEqual(this.data, dataAssignment.data) && Intrinsics.areEqual(this.desa, dataAssignment.desa) && Intrinsics.areEqual(this.alamat, dataAssignment.alamat) && Intrinsics.areEqual(this.namaDusun, dataAssignment.namaDusun) && Intrinsics.areEqual(this.rtRw, dataAssignment.rtRw) && Intrinsics.areEqual(this.nomorKk, dataAssignment.nomorKk) && Intrinsics.areEqual(this.namaKepalaKeluarga, dataAssignment.namaKepalaKeluarga) && Intrinsics.areEqual(this.latitude, dataAssignment.latitude) && Intrinsics.areEqual(this.longitude, dataAssignment.longitude);
    }

    public int hashCode() {
        String str = this.bangunanSensus;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.sls_number;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.address;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.householdCode;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.rutaId;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.name;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.bangunanFisik;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Boolean bool = this.isLiveIn;
        int iHashCode8 = (iHashCode7 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str8 = this.address_number;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.strata;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.prop;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.kab_reg;
        int iHashCode12 = (iHashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.kec_dist;
        int iHashCode13 = (iHashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.desa_village;
        int iHashCode14 = (iHashCode13 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.blok;
        int iHashCode15 = (iHashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.cawi_identifier;
        int iHashCode16 = (iHashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.cawi_pin;
        int iHashCode17 = (iHashCode16 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.provinsi;
        int iHashCode18 = (iHashCode17 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.kabupatenKota;
        int iHashCode19 = (iHashCode18 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.kecamatan;
        int iHashCode20 = (iHashCode19 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.data;
        int iHashCode21 = (iHashCode20 + (str20 == null ? 0 : str20.hashCode())) * 31;
        String str21 = this.desa;
        int iHashCode22 = (iHashCode21 + (str21 == null ? 0 : str21.hashCode())) * 31;
        String str22 = this.alamat;
        int iHashCode23 = (iHashCode22 + (str22 == null ? 0 : str22.hashCode())) * 31;
        String str23 = this.namaDusun;
        int iHashCode24 = (iHashCode23 + (str23 == null ? 0 : str23.hashCode())) * 31;
        String str24 = this.rtRw;
        int iHashCode25 = (iHashCode24 + (str24 == null ? 0 : str24.hashCode())) * 31;
        String str25 = this.nomorKk;
        int iHashCode26 = (iHashCode25 + (str25 == null ? 0 : str25.hashCode())) * 31;
        String str26 = this.namaKepalaKeluarga;
        int iHashCode27 = (iHashCode26 + (str26 == null ? 0 : str26.hashCode())) * 31;
        String str27 = this.latitude;
        int iHashCode28 = (iHashCode27 + (str27 == null ? 0 : str27.hashCode())) * 31;
        String str28 = this.longitude;
        return iHashCode28 + (str28 != null ? str28.hashCode() : 0);
    }

    public String toString() {
        return "DataAssignment(bangunanSensus=" + this.bangunanSensus + ", sls_number=" + this.sls_number + ", address=" + this.address + ", householdCode=" + this.householdCode + ", rutaId=" + this.rutaId + ", name=" + this.name + ", bangunanFisik=" + this.bangunanFisik + ", isLiveIn=" + this.isLiveIn + ", address_number=" + this.address_number + ", strata=" + this.strata + ", prop=" + this.prop + ", kab_reg=" + this.kab_reg + ", kec_dist=" + this.kec_dist + ", desa_village=" + this.desa_village + ", blok=" + this.blok + ", cawi_identifier=" + this.cawi_identifier + ", cawi_pin=" + this.cawi_pin + ", provinsi=" + this.provinsi + ", kabupatenKota=" + this.kabupatenKota + ", kecamatan=" + this.kecamatan + ", data=" + this.data + ", desa=" + this.desa + ", alamat=" + this.alamat + ", namaDusun=" + this.namaDusun + ", rtRw=" + this.rtRw + ", nomorKk=" + this.nomorKk + ", namaKepalaKeluarga=" + this.namaKepalaKeluarga + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ")";
    }

    public DataAssignment(String str, String str2, String str3, String str4, String str5, String str6, String str7, Boolean bool, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28) {
        this.bangunanSensus = str;
        this.sls_number = str2;
        this.address = str3;
        this.householdCode = str4;
        this.rutaId = str5;
        this.name = str6;
        this.bangunanFisik = str7;
        this.isLiveIn = bool;
        this.address_number = str8;
        this.strata = str9;
        this.prop = str10;
        this.kab_reg = str11;
        this.kec_dist = str12;
        this.desa_village = str13;
        this.blok = str14;
        this.cawi_identifier = str15;
        this.cawi_pin = str16;
        this.provinsi = str17;
        this.kabupatenKota = str18;
        this.kecamatan = str19;
        this.data = str20;
        this.desa = str21;
        this.alamat = str22;
        this.namaDusun = str23;
        this.rtRw = str24;
        this.nomorKk = str25;
        this.namaKepalaKeluarga = str26;
        this.latitude = str27;
        this.longitude = str28;
    }

    public final String getBangunanSensus() {
        return this.bangunanSensus;
    }

    public final void setBangunanSensus(String str) {
        this.bangunanSensus = str;
    }

    public final String getSls_number() {
        return this.sls_number;
    }

    public final void setSls_number(String str) {
        this.sls_number = str;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        this.address = str;
    }

    public final String getHouseholdCode() {
        return this.householdCode;
    }

    public final void setHouseholdCode(String str) {
        this.householdCode = str;
    }

    public final String getRutaId() {
        return this.rutaId;
    }

    public final void setRutaId(String str) {
        this.rutaId = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getBangunanFisik() {
        return this.bangunanFisik;
    }

    public final void setBangunanFisik(String str) {
        this.bangunanFisik = str;
    }

    public final Boolean isLiveIn() {
        return this.isLiveIn;
    }

    public final void setLiveIn(Boolean bool) {
        this.isLiveIn = bool;
    }

    public final String getAddress_number() {
        return this.address_number;
    }

    public final void setAddress_number(String str) {
        this.address_number = str;
    }

    public final String getStrata() {
        return this.strata;
    }

    public final void setStrata(String str) {
        this.strata = str;
    }

    public final String getProp() {
        return this.prop;
    }

    public final void setProp(String str) {
        this.prop = str;
    }

    public final String getKab_reg() {
        return this.kab_reg;
    }

    public final void setKab_reg(String str) {
        this.kab_reg = str;
    }

    public final String getKec_dist() {
        return this.kec_dist;
    }

    public final void setKec_dist(String str) {
        this.kec_dist = str;
    }

    public final String getDesa_village() {
        return this.desa_village;
    }

    public final void setDesa_village(String str) {
        this.desa_village = str;
    }

    public final String getBlok() {
        return this.blok;
    }

    public final void setBlok(String str) {
        this.blok = str;
    }

    public final String getCawi_identifier() {
        return this.cawi_identifier;
    }

    public final void setCawi_identifier(String str) {
        this.cawi_identifier = str;
    }

    public final String getCawi_pin() {
        return this.cawi_pin;
    }

    public final void setCawi_pin(String str) {
        this.cawi_pin = str;
    }

    public final String getProvinsi() {
        return this.provinsi;
    }

    public final void setProvinsi(String str) {
        this.provinsi = str;
    }

    public final String getKabupatenKota() {
        return this.kabupatenKota;
    }

    public final void setKabupatenKota(String str) {
        this.kabupatenKota = str;
    }

    public final String getKecamatan() {
        return this.kecamatan;
    }

    public final void setKecamatan(String str) {
        this.kecamatan = str;
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        this.data = str;
    }

    public final String getDesa() {
        return this.desa;
    }

    public final void setDesa(String str) {
        this.desa = str;
    }

    public final String getAlamat() {
        return this.alamat;
    }

    public final void setAlamat(String str) {
        this.alamat = str;
    }

    public final String getNamaDusun() {
        return this.namaDusun;
    }

    public final void setNamaDusun(String str) {
        this.namaDusun = str;
    }

    public final String getRtRw() {
        return this.rtRw;
    }

    public final void setRtRw(String str) {
        this.rtRw = str;
    }

    public final String getNomorKk() {
        return this.nomorKk;
    }

    public final void setNomorKk(String str) {
        this.nomorKk = str;
    }

    public final String getNamaKepalaKeluarga() {
        return this.namaKepalaKeluarga;
    }

    public final void setNamaKepalaKeluarga(String str) {
        this.namaKepalaKeluarga = str;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(String str) {
        this.latitude = str;
    }

    public final String getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(String str) {
        this.longitude = str;
    }

    public DataAssignment() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}

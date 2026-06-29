package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.domain.models.AssignmentHistory;
import id.go.bpsfasih.domain.models.AssignmentResponsibilityForResponseData;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u0000q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0003\b\u008d\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u0000 è\u00022\u00020\u0001:\u0002è\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BÅ\u0006\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0011\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0017\u001a\u00020\u0004\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0004\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010&\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010,\u001a\u0004\u0018\u00010\r\u0012\b\u0010-\u001a\u0004\u0018\u00010\r\u0012\b\u0010.\u001a\u0004\u0018\u00010\r\u0012\b\u0010/\u001a\u0004\u0018\u000100\u0012\b\u00101\u001a\u0004\u0018\u000100\u0012\b\u00102\u001a\u0004\u0018\u00010\u0004\u0012\b\u00103\u001a\u0004\u0018\u00010\u0004\u0012\b\u00104\u001a\u0004\u0018\u00010\u0004\u0012\b\u00105\u001a\u0004\u0018\u00010\u0004\u0012\b\u00106\u001a\u0004\u0018\u00010\u0004\u0012\b\u00107\u001a\u0004\u0018\u00010\u0004\u0012\b\u00108\u001a\u0004\u0018\u00010\u0011\u0012\b\u00109\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010:\u001a\u00020\u0011\u0012\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010<\u0012\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010<\u0012\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010@\u0012\b\b\u0002\u0010B\u001a\u00020\u0011\u0012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010D\u0012\n\b\u0002\u0010E\u001a\u0004\u0018\u00010D\u0012\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010H\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010I\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010J\u001a\u00020\u0011\u0012\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u0004\u0012\u0010\u0010Q\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010<\u0012\b\u0010R\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010S\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010T\u001a\u0004\u0018\u00010U\u0012\b\u0010V\u001a\u0004\u0018\u00010W\u0012\b\u0010X\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010Y\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010Z\u001a\u00020\u0011\u0012\b\u0010[\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010]\u001a\u00020\r¢\u0006\u0002\u0010^J\n\u0010\u0093\u0002\u001a\u00020\u0004HÆ\u0003J\f\u0010\u0094\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0095\u0002\u001a\u00020\u0011HÆ\u0003J\n\u0010\u0096\u0002\u001a\u00020\u0011HÆ\u0003J\n\u0010\u0097\u0002\u001a\u00020\u0014HÆ\u0003J\n\u0010\u0098\u0002\u001a\u00020\u0011HÆ\u0003J\f\u0010\u0099\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u009a\u0002\u001a\u00020\u0004HÆ\u0003J\n\u0010\u009b\u0002\u001a\u00020\u0004HÆ\u0003J\f\u0010\u009c\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u009d\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u009e\u0002\u001a\u00020\u0004HÆ\u0003J\f\u0010\u009f\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010 \u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¡\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¢\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010£\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¤\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¥\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¦\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010§\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¨\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010©\u0002\u001a\u00020\u0004HÆ\u0003J\f\u0010ª\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010«\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¬\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u00ad\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010®\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¯\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010°\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010±\u0002\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010jJ\u0011\u0010²\u0002\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010jJ\u0011\u0010³\u0002\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010jJ\n\u0010´\u0002\u001a\u00020\u0004HÆ\u0003J\u0012\u0010µ\u0002\u001a\u0004\u0018\u000100HÆ\u0003¢\u0006\u0003\u0010¼\u0001J\u0012\u0010¶\u0002\u001a\u0004\u0018\u000100HÆ\u0003¢\u0006\u0003\u0010¼\u0001J\f\u0010·\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¸\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¹\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010º\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010»\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010¼\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0012\u0010½\u0002\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0003\u0010\u0083\u0001J\u0012\u0010¾\u0002\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0003\u0010\u0083\u0001J\f\u0010¿\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010À\u0002\u001a\u00020\u0011HÆ\u0003J\u0012\u0010Á\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010<HÆ\u0003J\u0012\u0010Â\u0002\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010<HÆ\u0003J\u0012\u0010Ã\u0002\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010@HÆ\u0003J\n\u0010Ä\u0002\u001a\u00020\u0011HÆ\u0003J\u0012\u0010Å\u0002\u001a\u0004\u0018\u00010DHÆ\u0003¢\u0006\u0003\u0010Á\u0001J\u0012\u0010Æ\u0002\u001a\u0004\u0018\u00010DHÆ\u0003¢\u0006\u0003\u0010Á\u0001J\f\u0010Ç\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0012\u0010È\u0002\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0003\u0010\u0083\u0001J\u0012\u0010É\u0002\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0003\u0010\u0083\u0001J\f\u0010Ê\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Ë\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010Ì\u0002\u001a\u00020\u0011HÆ\u0003J\f\u0010Í\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Î\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Ï\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Ð\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Ñ\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Ò\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0014\u0010Ó\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010<HÆ\u0003J\f\u0010Ô\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Õ\u0002\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\f\u0010Ö\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010×\u0002\u001a\u0004\u0018\u00010UHÆ\u0003J\f\u0010Ø\u0002\u001a\u0004\u0018\u00010WHÆ\u0003J\f\u0010Ù\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Ú\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010Û\u0002\u001a\u00020\u0011HÆ\u0003J\f\u0010Ü\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010Ý\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010Þ\u0002\u001a\u00020\rHÆ\u0003J\u0011\u0010ß\u0002\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010jJ\f\u0010à\u0002\u001a\u0004\u0018\u00010\u0004HÆ\u0003J´\u0007\u0010á\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00112\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\n\b\u0002\u00101\u001a\u0004\u0018\u0001002\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010:\u001a\u00020\u00112\u0010\b\u0002\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010<2\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010<2\u0010\b\u0002\u0010?\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010@2\b\b\u0002\u0010B\u001a\u00020\u00112\n\b\u0002\u0010C\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010E\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010J\u001a\u00020\u00112\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00042\u0012\b\u0002\u0010Q\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010<2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010T\u001a\u0004\u0018\u00010U2\n\b\u0002\u0010V\u001a\u0004\u0018\u00010W2\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010Z\u001a\u00020\u00112\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010]\u001a\u00020\rHÆ\u0001¢\u0006\u0003\u0010â\u0002J\u0016\u0010ã\u0002\u001a\u00020\u00112\n\u0010ä\u0002\u001a\u0005\u0018\u00010å\u0002HÖ\u0003J\n\u0010æ\u0002\u001a\u00020\rHÖ\u0001J\n\u0010ç\u0002\u001a\u00020\u0004HÖ\u0001R\"\u0010?\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\"\u0010=\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010`\"\u0004\bd\u0010bR\u001c\u0010X\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010m\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001c\u0010[\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010f\"\u0004\bo\u0010hR\u001c\u0010S\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010f\"\u0004\bq\u0010hR\u001c\u0010&\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010f\"\u0004\bs\u0010hR\u001c\u0010%\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010f\"\u0004\bu\u0010hR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010f\"\u0004\bw\u0010hR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010f\"\u0004\by\u0010hR\u001c\u0010R\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010f\"\u0004\b{\u0010hR\u001c\u0010I\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010f\"\u0004\b}\u0010hR\u001c\u00105\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010f\"\u0004\b\u007f\u0010hR\u001e\u00103\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010f\"\u0005\b\u0081\u0001\u0010hR#\u00109\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0015\n\u0003\u0010\u0086\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001e\u00106\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u0010f\"\u0005\b\u0088\u0001\u0010hR#\u00108\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0015\n\u0003\u0010\u0086\u0001\u001a\u0006\b\u0089\u0001\u0010\u0083\u0001\"\u0006\b\u008a\u0001\u0010\u0085\u0001R\u001e\u00107\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010f\"\u0005\b\u008c\u0001\u0010hR\u001e\u00104\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u0010f\"\u0005\b\u008e\u0001\u0010hR$\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\"\u0006\b\u0091\u0001\u0010\u0092\u0001R\u001e\u0010'\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u0010f\"\u0005\b\u0094\u0001\u0010hR\u001e\u0010P\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0095\u0001\u0010f\"\u0005\b\u0096\u0001\u0010hR\u001e\u0010(\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010f\"\u0005\b\u0098\u0001\u0010hR\u001e\u0010)\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u0010f\"\u0005\b\u009a\u0001\u0010hR\u001e\u0010*\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u0010f\"\u0005\b\u009c\u0001\u0010hR\u001e\u0010+\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010f\"\u0005\b\u009e\u0001\u0010hR\u001e\u0010L\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010f\"\u0005\b \u0001\u0010hR\u001e\u0010M\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¡\u0001\u0010f\"\u0005\b¢\u0001\u0010hR\u001e\u0010N\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010f\"\u0005\b¤\u0001\u0010hR\u001e\u0010O\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¥\u0001\u0010f\"\u0005\b¦\u0001\u0010hR\"\u0010\\\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b§\u0001\u0010f\"\u0005\b¨\u0001\u0010hR\u001e\u0010!\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b©\u0001\u0010f\"\u0005\bª\u0001\u0010hR\u001e\u0010\u001f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b«\u0001\u0010f\"\u0005\b¬\u0001\u0010hR\u001e\u0010 \u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u00ad\u0001\u0010f\"\u0005\b®\u0001\u0010hR \u0010\u0007\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010f\"\u0005\b°\u0001\u0010hR \u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b±\u0001\u0010f\"\u0005\b²\u0001\u0010hR\"\u0010G\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0014\n\u0003\u0010\u0086\u0001\u001a\u0005\bG\u0010\u0083\u0001\"\u0006\b³\u0001\u0010\u0085\u0001R!\u0010Z\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0011\n\u0000\u001a\u0005\bZ\u0010´\u0001\"\u0006\bµ\u0001\u0010¶\u0001R\u001d\u0010\u0012\u001a\u00020\u0011X\u0086\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u0012\u0010´\u0001\"\u0006\b·\u0001\u0010¶\u0001R\u001d\u0010B\u001a\u00020\u0011X\u0086\u000e¢\u0006\u0011\n\u0000\u001a\u0005\bB\u0010´\u0001\"\u0006\b¸\u0001\u0010¶\u0001R\u001e\u0010F\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¹\u0001\u0010f\"\u0005\bº\u0001\u0010hR#\u0010/\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u0015\n\u0003\u0010¿\u0001\u001a\u0006\b»\u0001\u0010¼\u0001\"\u0006\b½\u0001\u0010¾\u0001R#\u0010C\u001a\u0004\u0018\u00010DX\u0086\u000e¢\u0006\u0015\n\u0003\u0010Ä\u0001\u001a\u0006\bÀ\u0001\u0010Á\u0001\"\u0006\bÂ\u0001\u0010Ã\u0001R#\u00101\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u0015\n\u0003\u0010¿\u0001\u001a\u0006\bÅ\u0001\u0010¼\u0001\"\u0006\bÆ\u0001\u0010¾\u0001R#\u0010E\u001a\u0004\u0018\u00010DX\u0086\u000e¢\u0006\u0015\n\u0003\u0010Ä\u0001\u001a\u0006\bÇ\u0001\u0010Á\u0001\"\u0006\bÈ\u0001\u0010Ã\u0001R&\u0010Q\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010<X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÉ\u0001\u0010`\"\u0005\bÊ\u0001\u0010bR\u001e\u0010Y\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bË\u0001\u0010f\"\u0005\bÌ\u0001\u0010hR\u001e\u0010:\u001a\u00020\u0011X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÍ\u0001\u0010´\u0001\"\u0006\bÎ\u0001\u0010¶\u0001R\u001e\u0010J\u001a\u00020\u0011X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÏ\u0001\u0010´\u0001\"\u0006\bÐ\u0001\u0010¶\u0001R\u001e\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÑ\u0001\u0010Ò\u0001\"\u0006\bÓ\u0001\u0010Ô\u0001R\u001e\u0010K\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÕ\u0001\u0010f\"\u0005\bÖ\u0001\u0010hR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b×\u0001\u0010f\"\u0005\bØ\u0001\u0010hR\u001e\u0010\u0015\u001a\u00020\u0011X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÙ\u0001\u0010´\u0001\"\u0006\bÚ\u0001\u0010¶\u0001R\u001c\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÛ\u0001\u0010f\"\u0005\bÜ\u0001\u0010hR\u001c\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÝ\u0001\u0010f\"\u0005\bÞ\u0001\u0010hR\u001e\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bß\u0001\u0010f\"\u0005\bà\u0001\u0010hR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bá\u0001\u0010f\"\u0005\bâ\u0001\u0010hR\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bã\u0001\u0010f\"\u0005\bä\u0001\u0010hR\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bå\u0001\u0010f\"\u0005\bæ\u0001\u0010hR\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bç\u0001\u0010f\"\u0005\bè\u0001\u0010hR\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bé\u0001\u0010f\"\u0005\bê\u0001\u0010hR\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bë\u0001\u0010f\"\u0005\bì\u0001\u0010hR$\u0010V\u001a\u0004\u0018\u00010W8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bí\u0001\u0010î\u0001\"\u0006\bï\u0001\u0010ð\u0001R$\u0010T\u001a\u0004\u0018\u00010U8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bñ\u0001\u0010ò\u0001\"\u0006\bó\u0001\u0010ô\u0001R$\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010<X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bõ\u0001\u0010`\"\u0005\bö\u0001\u0010bR#\u0010H\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0015\n\u0003\u0010\u0086\u0001\u001a\u0006\b÷\u0001\u0010\u0083\u0001\"\u0006\bø\u0001\u0010\u0085\u0001R\u001e\u00102\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bù\u0001\u0010f\"\u0005\bú\u0001\u0010hR\"\u0010]\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bû\u0001\u0010ü\u0001\"\u0006\bý\u0001\u0010þ\u0001R$\u0010.\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010m\u001a\u0005\bÿ\u0001\u0010j\"\u0005\b\u0080\u0002\u0010lR$\u0010,\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010m\u001a\u0005\b\u0081\u0002\u0010j\"\u0005\b\u0082\u0002\u0010lR$\u0010-\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010m\u001a\u0005\b\u0083\u0002\u0010j\"\u0005\b\u0084\u0002\u0010lR\u001c\u0010\u0017\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0002\u0010f\"\u0005\b\u0086\u0002\u0010hR\u001c\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0002\u0010f\"\u0005\b\u0088\u0002\u0010hR\u001e\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0089\u0002\u0010´\u0001\"\u0006\b\u008a\u0002\u0010¶\u0001R\"\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0002\u0010f\"\u0005\b\u008c\u0002\u0010hR\u001e\u0010$\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0002\u0010f\"\u0005\b\u008e\u0002\u0010hR\u001e\u0010\"\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0002\u0010f\"\u0005\b\u0090\u0002\u0010hR\u001e\u0010#\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0002\u0010f\"\u0005\b\u0092\u0002\u0010h¨\u0006é\u0002"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Ljava/io/Serializable;", "()V", "idPrimary", "", "periodeId", "periodeNotPrimary", DownloadModel.ID, "userIdAssignment", "preDefinedData", "data", "Lid/go/bpsfasih/data/local/entities/DataAssignment;", "assignmentStatusId", "", "cawiToken", "cawiPin", "tarikSample", "", "isNew", "otherStatus", "", "pendingStatus", "parentUser", "surveyId", "surveyIdNotPrimary", "provinceId", "provinceName", "provinceCode", "regencyId", "regencyName", "regencyCode", "districtId", "districtName", "districtFullcode", "villageId", "villageName", "villageFullcode", "blokSensusId", "blokSensusFullCode", "data1", "data2", "data3", "data4", "data5", "sum_error", "sum_remark", "sum_clean", "latitude", "", "longitude", "strata", "currentUserId", "currentUserUsername", "currentUserFullname", "currentUserSurveyRoleId", "currentUserSurveyRoleName", "currentUserSurveyRoleIsPencacah", "currentUserSurveyRoleCanPullSample", "offlineSend", "responsibility", "", "assignmentResponsibility", "Lid/go/bpsfasih/domain/models/AssignmentResponsibilityForResponseData;", "assignmentHistories", "", "Lid/go/bpsfasih/domain/models/AssignmentHistory;", "is_inside_blok_sensus", "latitude_if_outside", "", "longitude_if_outside", "keterangan_validasi", "isDone", "secondary", "copyFromId", "original", "paradata", "data6", "data7", "data8", "data9", "data10", "mode", ClientCookie.COMMENT_ATTR, "basePathComment", "regionMetadata", "Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "region", "Lid/go/bpsfasih/data/local/entities/Region;", "assignmentStatusAlias", FormGearJavascriptInterface.NOTE_FILE, "isEncrypt", "basePath", "dataDownloadedAt", "submitVersionCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/DataAssignment;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;ZZJZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;ZLjava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/RegionMetadata;Lid/go/bpsfasih/data/local/entities/Region;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;I)V", "getAssignmentHistories", "()Ljava/util/List;", "setAssignmentHistories", "(Ljava/util/List;)V", "getAssignmentResponsibility", "setAssignmentResponsibility", "getAssignmentStatusAlias", "()Ljava/lang/String;", "setAssignmentStatusAlias", "(Ljava/lang/String;)V", "getAssignmentStatusId", "()Ljava/lang/Integer;", "setAssignmentStatusId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBasePath", "setBasePath", "getBasePathComment", "setBasePathComment", "getBlokSensusFullCode", "setBlokSensusFullCode", "getBlokSensusId", "setBlokSensusId", "getCawiPin", "setCawiPin", "getCawiToken", "setCawiToken", "getComment", "setComment", "getCopyFromId", "setCopyFromId", "getCurrentUserFullname", "setCurrentUserFullname", "getCurrentUserId", "setCurrentUserId", "getCurrentUserSurveyRoleCanPullSample", "()Ljava/lang/Boolean;", "setCurrentUserSurveyRoleCanPullSample", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCurrentUserSurveyRoleId", "setCurrentUserSurveyRoleId", "getCurrentUserSurveyRoleIsPencacah", "setCurrentUserSurveyRoleIsPencacah", "getCurrentUserSurveyRoleName", "setCurrentUserSurveyRoleName", "getCurrentUserUsername", "setCurrentUserUsername", "getData", "()Lid/go/bpsfasih/data/local/entities/DataAssignment;", "setData", "(Lid/go/bpsfasih/data/local/entities/DataAssignment;)V", "getData1", "setData1", "getData10", "setData10", "getData2", "setData2", "getData3", "setData3", "getData4", "setData4", "getData5", "setData5", "getData6", "setData6", "getData7", "setData7", "getData8", "setData8", "getData9", "setData9", "getDataDownloadedAt", "setDataDownloadedAt", "getDistrictFullcode", "setDistrictFullcode", "getDistrictId", "setDistrictId", "getDistrictName", "setDistrictName", "getId", "setId", "getIdPrimary", "setIdPrimary", "setDone", "()Z", "setEncrypt", "(Z)V", "setNew", "set_inside_blok_sensus", "getKeterangan_validasi", "setKeterangan_validasi", "getLatitude", "()Ljava/lang/Float;", "setLatitude", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "getLatitude_if_outside", "()Ljava/lang/Double;", "setLatitude_if_outside", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getLongitude", "setLongitude", "getLongitude_if_outside", "setLongitude_if_outside", "getMode", "setMode", "getNote", "setNote", "getOfflineSend", "setOfflineSend", "getOriginal", "setOriginal", "getOtherStatus", "()J", "setOtherStatus", "(J)V", "getParadata", "setParadata", "getParentUser", "setParentUser", "getPendingStatus", "setPendingStatus", "getPeriodeId", "setPeriodeId", "getPeriodeNotPrimary", "setPeriodeNotPrimary", "getPreDefinedData", "setPreDefinedData", "getProvinceCode", "setProvinceCode", "getProvinceId", "setProvinceId", "getProvinceName", "setProvinceName", "getRegencyCode", "setRegencyCode", "getRegencyId", "setRegencyId", "getRegencyName", "setRegencyName", "getRegion", "()Lid/go/bpsfasih/data/local/entities/Region;", "setRegion", "(Lid/go/bpsfasih/data/local/entities/Region;)V", "getRegionMetadata", "()Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "setRegionMetadata", "(Lid/go/bpsfasih/data/local/entities/RegionMetadata;)V", "getResponsibility", "setResponsibility", "getSecondary", "setSecondary", "getStrata", "setStrata", "getSubmitVersionCode", "()I", "setSubmitVersionCode", "(I)V", "getSum_clean", "setSum_clean", "getSum_error", "setSum_error", "getSum_remark", "setSum_remark", "getSurveyId", "setSurveyId", "getSurveyIdNotPrimary", "setSurveyIdNotPrimary", "getTarikSample", "setTarikSample", "getUserIdAssignment", "setUserIdAssignment", "getVillageFullcode", "setVillageFullcode", "getVillageId", "setVillageId", "getVillageName", "setVillageName", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component6", "component60", "component61", "component62", "component63", "component64", "component65", "component66", "component67", "component68", "component69", "component7", "component70", "component71", "component72", "component73", "component74", "component75", "component76", "component77", "component78", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/DataAssignment;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;ZZJZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;ZLjava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/RegionMetadata;Lid/go/bpsfasih/data/local/entities/Region;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;I)Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "equals", "other", "", "hashCode", "toString", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentEntity implements Serializable {
    private List<AssignmentHistory> assignmentHistories;
    private List<AssignmentResponsibilityForResponseData> assignmentResponsibility;
    private String assignmentStatusAlias;
    private Integer assignmentStatusId;
    private String basePath;
    private String basePathComment;
    private String blokSensusFullCode;
    private String blokSensusId;
    private String cawiPin;
    private String cawiToken;
    private String comment;
    private String copyFromId;
    private String currentUserFullname;
    private String currentUserId;
    private Boolean currentUserSurveyRoleCanPullSample;
    private String currentUserSurveyRoleId;
    private Boolean currentUserSurveyRoleIsPencacah;
    private String currentUserSurveyRoleName;
    private String currentUserUsername;
    private DataAssignment data;
    private String data1;
    private String data10;
    private String data2;
    private String data3;
    private String data4;
    private String data5;
    private String data6;
    private String data7;
    private String data8;
    private String data9;
    private String dataDownloadedAt;
    private String districtFullcode;
    private String districtId;
    private String districtName;

    @SerializedName(DownloadModel.ID)
    private String id;
    private String idPrimary;
    private Boolean isDone;
    private boolean isEncrypt;
    private boolean isNew;
    private boolean is_inside_blok_sensus;
    private String keterangan_validasi;
    private Float latitude;
    private Double latitude_if_outside;
    private Float longitude;
    private Double longitude_if_outside;
    private List<String> mode;
    private String note;
    private boolean offlineSend;
    private boolean original;
    private long otherStatus;
    private String paradata;
    private String parentUser;
    private boolean pendingStatus;
    private String periodeId;
    private String periodeNotPrimary;
    private String preDefinedData;
    private String provinceCode;
    private String provinceId;
    private String provinceName;
    private String regencyCode;
    private String regencyId;
    private String regencyName;
    private Region region;
    private RegionMetadata regionMetadata;
    private List<String> responsibility;
    private Boolean secondary;
    private String strata;
    private int submitVersionCode;

    @SerializedName("sumClean")
    private Integer sum_clean;

    @SerializedName("sumError")
    private Integer sum_error;

    @SerializedName("sumRemark")
    private Integer sum_remark;
    private String surveyId;
    private String surveyIdNotPrimary;
    private boolean tarikSample;

    @SerializedName("userId")
    private String userIdAssignment;
    private String villageFullcode;
    private String villageId;
    private String villageName;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* renamed from: component1, reason: from getter */
    public final String getIdPrimary() {
        return this.idPrimary;
    }

    /* renamed from: component10, reason: from getter */
    public final String getCawiPin() {
        return this.cawiPin;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getTarikSample() {
        return this.tarikSample;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getIsNew() {
        return this.isNew;
    }

    /* renamed from: component13, reason: from getter */
    public final long getOtherStatus() {
        return this.otherStatus;
    }

    /* renamed from: component14, reason: from getter */
    public final boolean getPendingStatus() {
        return this.pendingStatus;
    }

    /* renamed from: component15, reason: from getter */
    public final String getParentUser() {
        return this.parentUser;
    }

    /* renamed from: component16, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component17, reason: from getter */
    public final String getSurveyIdNotPrimary() {
        return this.surveyIdNotPrimary;
    }

    /* renamed from: component18, reason: from getter */
    public final String getProvinceId() {
        return this.provinceId;
    }

    /* renamed from: component19, reason: from getter */
    public final String getProvinceName() {
        return this.provinceName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPeriodeId() {
        return this.periodeId;
    }

    /* renamed from: component20, reason: from getter */
    public final String getProvinceCode() {
        return this.provinceCode;
    }

    /* renamed from: component21, reason: from getter */
    public final String getRegencyId() {
        return this.regencyId;
    }

    /* renamed from: component22, reason: from getter */
    public final String getRegencyName() {
        return this.regencyName;
    }

    /* renamed from: component23, reason: from getter */
    public final String getRegencyCode() {
        return this.regencyCode;
    }

    /* renamed from: component24, reason: from getter */
    public final String getDistrictId() {
        return this.districtId;
    }

    /* renamed from: component25, reason: from getter */
    public final String getDistrictName() {
        return this.districtName;
    }

    /* renamed from: component26, reason: from getter */
    public final String getDistrictFullcode() {
        return this.districtFullcode;
    }

    /* renamed from: component27, reason: from getter */
    public final String getVillageId() {
        return this.villageId;
    }

    /* renamed from: component28, reason: from getter */
    public final String getVillageName() {
        return this.villageName;
    }

    /* renamed from: component29, reason: from getter */
    public final String getVillageFullcode() {
        return this.villageFullcode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPeriodeNotPrimary() {
        return this.periodeNotPrimary;
    }

    /* renamed from: component30, reason: from getter */
    public final String getBlokSensusId() {
        return this.blokSensusId;
    }

    /* renamed from: component31, reason: from getter */
    public final String getBlokSensusFullCode() {
        return this.blokSensusFullCode;
    }

    /* renamed from: component32, reason: from getter */
    public final String getData1() {
        return this.data1;
    }

    /* renamed from: component33, reason: from getter */
    public final String getData2() {
        return this.data2;
    }

    /* renamed from: component34, reason: from getter */
    public final String getData3() {
        return this.data3;
    }

    /* renamed from: component35, reason: from getter */
    public final String getData4() {
        return this.data4;
    }

    /* renamed from: component36, reason: from getter */
    public final String getData5() {
        return this.data5;
    }

    /* renamed from: component37, reason: from getter */
    public final Integer getSum_error() {
        return this.sum_error;
    }

    /* renamed from: component38, reason: from getter */
    public final Integer getSum_remark() {
        return this.sum_remark;
    }

    /* renamed from: component39, reason: from getter */
    public final Integer getSum_clean() {
        return this.sum_clean;
    }

    /* renamed from: component4, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component40, reason: from getter */
    public final Float getLatitude() {
        return this.latitude;
    }

    /* renamed from: component41, reason: from getter */
    public final Float getLongitude() {
        return this.longitude;
    }

    /* renamed from: component42, reason: from getter */
    public final String getStrata() {
        return this.strata;
    }

    /* renamed from: component43, reason: from getter */
    public final String getCurrentUserId() {
        return this.currentUserId;
    }

    /* renamed from: component44, reason: from getter */
    public final String getCurrentUserUsername() {
        return this.currentUserUsername;
    }

    /* renamed from: component45, reason: from getter */
    public final String getCurrentUserFullname() {
        return this.currentUserFullname;
    }

    /* renamed from: component46, reason: from getter */
    public final String getCurrentUserSurveyRoleId() {
        return this.currentUserSurveyRoleId;
    }

    /* renamed from: component47, reason: from getter */
    public final String getCurrentUserSurveyRoleName() {
        return this.currentUserSurveyRoleName;
    }

    /* renamed from: component48, reason: from getter */
    public final Boolean getCurrentUserSurveyRoleIsPencacah() {
        return this.currentUserSurveyRoleIsPencacah;
    }

    /* renamed from: component49, reason: from getter */
    public final Boolean getCurrentUserSurveyRoleCanPullSample() {
        return this.currentUserSurveyRoleCanPullSample;
    }

    /* renamed from: component5, reason: from getter */
    public final String getUserIdAssignment() {
        return this.userIdAssignment;
    }

    /* renamed from: component50, reason: from getter */
    public final boolean getOfflineSend() {
        return this.offlineSend;
    }

    public final List<String> component51() {
        return this.responsibility;
    }

    public final List<AssignmentResponsibilityForResponseData> component52() {
        return this.assignmentResponsibility;
    }

    public final List<AssignmentHistory> component53() {
        return this.assignmentHistories;
    }

    /* renamed from: component54, reason: from getter */
    public final boolean getIs_inside_blok_sensus() {
        return this.is_inside_blok_sensus;
    }

    /* renamed from: component55, reason: from getter */
    public final Double getLatitude_if_outside() {
        return this.latitude_if_outside;
    }

    /* renamed from: component56, reason: from getter */
    public final Double getLongitude_if_outside() {
        return this.longitude_if_outside;
    }

    /* renamed from: component57, reason: from getter */
    public final String getKeterangan_validasi() {
        return this.keterangan_validasi;
    }

    /* renamed from: component58, reason: from getter */
    public final Boolean getIsDone() {
        return this.isDone;
    }

    /* renamed from: component59, reason: from getter */
    public final Boolean getSecondary() {
        return this.secondary;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPreDefinedData() {
        return this.preDefinedData;
    }

    /* renamed from: component60, reason: from getter */
    public final String getCopyFromId() {
        return this.copyFromId;
    }

    /* renamed from: component61, reason: from getter */
    public final boolean getOriginal() {
        return this.original;
    }

    /* renamed from: component62, reason: from getter */
    public final String getParadata() {
        return this.paradata;
    }

    /* renamed from: component63, reason: from getter */
    public final String getData6() {
        return this.data6;
    }

    /* renamed from: component64, reason: from getter */
    public final String getData7() {
        return this.data7;
    }

    /* renamed from: component65, reason: from getter */
    public final String getData8() {
        return this.data8;
    }

    /* renamed from: component66, reason: from getter */
    public final String getData9() {
        return this.data9;
    }

    /* renamed from: component67, reason: from getter */
    public final String getData10() {
        return this.data10;
    }

    public final List<String> component68() {
        return this.mode;
    }

    /* renamed from: component69, reason: from getter */
    public final String getComment() {
        return this.comment;
    }

    /* renamed from: component7, reason: from getter */
    public final DataAssignment getData() {
        return this.data;
    }

    /* renamed from: component70, reason: from getter */
    public final String getBasePathComment() {
        return this.basePathComment;
    }

    /* renamed from: component71, reason: from getter */
    public final RegionMetadata getRegionMetadata() {
        return this.regionMetadata;
    }

    /* renamed from: component72, reason: from getter */
    public final Region getRegion() {
        return this.region;
    }

    /* renamed from: component73, reason: from getter */
    public final String getAssignmentStatusAlias() {
        return this.assignmentStatusAlias;
    }

    /* renamed from: component74, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    /* renamed from: component75, reason: from getter */
    public final boolean getIsEncrypt() {
        return this.isEncrypt;
    }

    /* renamed from: component76, reason: from getter */
    public final String getBasePath() {
        return this.basePath;
    }

    /* renamed from: component77, reason: from getter */
    public final String getDataDownloadedAt() {
        return this.dataDownloadedAt;
    }

    /* renamed from: component78, reason: from getter */
    public final int getSubmitVersionCode() {
        return this.submitVersionCode;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getAssignmentStatusId() {
        return this.assignmentStatusId;
    }

    /* renamed from: component9, reason: from getter */
    public final String getCawiToken() {
        return this.cawiToken;
    }

    public final AssignmentEntity copy(String idPrimary, String periodeId, String periodeNotPrimary, String id2, String userIdAssignment, String preDefinedData, DataAssignment data, Integer assignmentStatusId, String cawiToken, String cawiPin, boolean tarikSample, boolean isNew, long otherStatus, boolean pendingStatus, String parentUser, String surveyId, String surveyIdNotPrimary, String provinceId, String provinceName, String provinceCode, String regencyId, String regencyName, String regencyCode, String districtId, String districtName, String districtFullcode, String villageId, String villageName, String villageFullcode, String blokSensusId, String blokSensusFullCode, String data1, String data2, String data3, String data4, String data5, Integer sum_error, Integer sum_remark, Integer sum_clean, Float latitude, Float longitude, String strata, String currentUserId, String currentUserUsername, String currentUserFullname, String currentUserSurveyRoleId, String currentUserSurveyRoleName, Boolean currentUserSurveyRoleIsPencacah, Boolean currentUserSurveyRoleCanPullSample, boolean offlineSend, List<String> responsibility, List<AssignmentResponsibilityForResponseData> assignmentResponsibility, List<AssignmentHistory> assignmentHistories, boolean is_inside_blok_sensus, Double latitude_if_outside, Double longitude_if_outside, String keterangan_validasi, Boolean isDone, Boolean secondary, String copyFromId, boolean original, String paradata, String data6, String data7, String data8, String data9, String data10, List<String> mode, String comment, String basePathComment, RegionMetadata regionMetadata, Region region, String assignmentStatusAlias, String note, boolean isEncrypt, String basePath, String dataDownloadedAt, int submitVersionCode) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(periodeNotPrimary, "periodeNotPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(surveyIdNotPrimary, "surveyIdNotPrimary");
        return new AssignmentEntity(idPrimary, periodeId, periodeNotPrimary, id2, userIdAssignment, preDefinedData, data, assignmentStatusId, cawiToken, cawiPin, tarikSample, isNew, otherStatus, pendingStatus, parentUser, surveyId, surveyIdNotPrimary, provinceId, provinceName, provinceCode, regencyId, regencyName, regencyCode, districtId, districtName, districtFullcode, villageId, villageName, villageFullcode, blokSensusId, blokSensusFullCode, data1, data2, data3, data4, data5, sum_error, sum_remark, sum_clean, latitude, longitude, strata, currentUserId, currentUserUsername, currentUserFullname, currentUserSurveyRoleId, currentUserSurveyRoleName, currentUserSurveyRoleIsPencacah, currentUserSurveyRoleCanPullSample, offlineSend, responsibility, assignmentResponsibility, assignmentHistories, is_inside_blok_sensus, latitude_if_outside, longitude_if_outside, keterangan_validasi, isDone, secondary, copyFromId, original, paradata, data6, data7, data8, data9, data10, mode, comment, basePathComment, regionMetadata, region, assignmentStatusAlias, note, isEncrypt, basePath, dataDownloadedAt, submitVersionCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentEntity)) {
            return false;
        }
        AssignmentEntity assignmentEntity = (AssignmentEntity) other;
        return Intrinsics.areEqual(this.idPrimary, assignmentEntity.idPrimary) && Intrinsics.areEqual(this.periodeId, assignmentEntity.periodeId) && Intrinsics.areEqual(this.periodeNotPrimary, assignmentEntity.periodeNotPrimary) && Intrinsics.areEqual(this.id, assignmentEntity.id) && Intrinsics.areEqual(this.userIdAssignment, assignmentEntity.userIdAssignment) && Intrinsics.areEqual(this.preDefinedData, assignmentEntity.preDefinedData) && Intrinsics.areEqual(this.data, assignmentEntity.data) && Intrinsics.areEqual(this.assignmentStatusId, assignmentEntity.assignmentStatusId) && Intrinsics.areEqual(this.cawiToken, assignmentEntity.cawiToken) && Intrinsics.areEqual(this.cawiPin, assignmentEntity.cawiPin) && this.tarikSample == assignmentEntity.tarikSample && this.isNew == assignmentEntity.isNew && this.otherStatus == assignmentEntity.otherStatus && this.pendingStatus == assignmentEntity.pendingStatus && Intrinsics.areEqual(this.parentUser, assignmentEntity.parentUser) && Intrinsics.areEqual(this.surveyId, assignmentEntity.surveyId) && Intrinsics.areEqual(this.surveyIdNotPrimary, assignmentEntity.surveyIdNotPrimary) && Intrinsics.areEqual(this.provinceId, assignmentEntity.provinceId) && Intrinsics.areEqual(this.provinceName, assignmentEntity.provinceName) && Intrinsics.areEqual(this.provinceCode, assignmentEntity.provinceCode) && Intrinsics.areEqual(this.regencyId, assignmentEntity.regencyId) && Intrinsics.areEqual(this.regencyName, assignmentEntity.regencyName) && Intrinsics.areEqual(this.regencyCode, assignmentEntity.regencyCode) && Intrinsics.areEqual(this.districtId, assignmentEntity.districtId) && Intrinsics.areEqual(this.districtName, assignmentEntity.districtName) && Intrinsics.areEqual(this.districtFullcode, assignmentEntity.districtFullcode) && Intrinsics.areEqual(this.villageId, assignmentEntity.villageId) && Intrinsics.areEqual(this.villageName, assignmentEntity.villageName) && Intrinsics.areEqual(this.villageFullcode, assignmentEntity.villageFullcode) && Intrinsics.areEqual(this.blokSensusId, assignmentEntity.blokSensusId) && Intrinsics.areEqual(this.blokSensusFullCode, assignmentEntity.blokSensusFullCode) && Intrinsics.areEqual(this.data1, assignmentEntity.data1) && Intrinsics.areEqual(this.data2, assignmentEntity.data2) && Intrinsics.areEqual(this.data3, assignmentEntity.data3) && Intrinsics.areEqual(this.data4, assignmentEntity.data4) && Intrinsics.areEqual(this.data5, assignmentEntity.data5) && Intrinsics.areEqual(this.sum_error, assignmentEntity.sum_error) && Intrinsics.areEqual(this.sum_remark, assignmentEntity.sum_remark) && Intrinsics.areEqual(this.sum_clean, assignmentEntity.sum_clean) && Intrinsics.areEqual((Object) this.latitude, (Object) assignmentEntity.latitude) && Intrinsics.areEqual((Object) this.longitude, (Object) assignmentEntity.longitude) && Intrinsics.areEqual(this.strata, assignmentEntity.strata) && Intrinsics.areEqual(this.currentUserId, assignmentEntity.currentUserId) && Intrinsics.areEqual(this.currentUserUsername, assignmentEntity.currentUserUsername) && Intrinsics.areEqual(this.currentUserFullname, assignmentEntity.currentUserFullname) && Intrinsics.areEqual(this.currentUserSurveyRoleId, assignmentEntity.currentUserSurveyRoleId) && Intrinsics.areEqual(this.currentUserSurveyRoleName, assignmentEntity.currentUserSurveyRoleName) && Intrinsics.areEqual(this.currentUserSurveyRoleIsPencacah, assignmentEntity.currentUserSurveyRoleIsPencacah) && Intrinsics.areEqual(this.currentUserSurveyRoleCanPullSample, assignmentEntity.currentUserSurveyRoleCanPullSample) && this.offlineSend == assignmentEntity.offlineSend && Intrinsics.areEqual(this.responsibility, assignmentEntity.responsibility) && Intrinsics.areEqual(this.assignmentResponsibility, assignmentEntity.assignmentResponsibility) && Intrinsics.areEqual(this.assignmentHistories, assignmentEntity.assignmentHistories) && this.is_inside_blok_sensus == assignmentEntity.is_inside_blok_sensus && Intrinsics.areEqual((Object) this.latitude_if_outside, (Object) assignmentEntity.latitude_if_outside) && Intrinsics.areEqual((Object) this.longitude_if_outside, (Object) assignmentEntity.longitude_if_outside) && Intrinsics.areEqual(this.keterangan_validasi, assignmentEntity.keterangan_validasi) && Intrinsics.areEqual(this.isDone, assignmentEntity.isDone) && Intrinsics.areEqual(this.secondary, assignmentEntity.secondary) && Intrinsics.areEqual(this.copyFromId, assignmentEntity.copyFromId) && this.original == assignmentEntity.original && Intrinsics.areEqual(this.paradata, assignmentEntity.paradata) && Intrinsics.areEqual(this.data6, assignmentEntity.data6) && Intrinsics.areEqual(this.data7, assignmentEntity.data7) && Intrinsics.areEqual(this.data8, assignmentEntity.data8) && Intrinsics.areEqual(this.data9, assignmentEntity.data9) && Intrinsics.areEqual(this.data10, assignmentEntity.data10) && Intrinsics.areEqual(this.mode, assignmentEntity.mode) && Intrinsics.areEqual(this.comment, assignmentEntity.comment) && Intrinsics.areEqual(this.basePathComment, assignmentEntity.basePathComment) && Intrinsics.areEqual(this.regionMetadata, assignmentEntity.regionMetadata) && Intrinsics.areEqual(this.region, assignmentEntity.region) && Intrinsics.areEqual(this.assignmentStatusAlias, assignmentEntity.assignmentStatusAlias) && Intrinsics.areEqual(this.note, assignmentEntity.note) && this.isEncrypt == assignmentEntity.isEncrypt && Intrinsics.areEqual(this.basePath, assignmentEntity.basePath) && Intrinsics.areEqual(this.dataDownloadedAt, assignmentEntity.dataDownloadedAt) && this.submitVersionCode == assignmentEntity.submitVersionCode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((this.idPrimary.hashCode() * 31) + this.periodeId.hashCode()) * 31) + this.periodeNotPrimary.hashCode()) * 31) + this.id.hashCode()) * 31;
        String str = this.userIdAssignment;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.preDefinedData;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        DataAssignment dataAssignment = this.data;
        int iHashCode4 = (iHashCode3 + (dataAssignment == null ? 0 : dataAssignment.hashCode())) * 31;
        Integer num = this.assignmentStatusId;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.cawiToken;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.cawiPin;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        boolean z = this.tarikSample;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode7 + i) * 31;
        boolean z2 = this.isNew;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int iHashCode8 = (((i2 + i3) * 31) + Long.hashCode(this.otherStatus)) * 31;
        boolean z3 = this.pendingStatus;
        int i4 = z3;
        if (z3 != 0) {
            i4 = 1;
        }
        int i5 = (iHashCode8 + i4) * 31;
        String str5 = this.parentUser;
        int iHashCode9 = (((((i5 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.surveyId.hashCode()) * 31) + this.surveyIdNotPrimary.hashCode()) * 31;
        String str6 = this.provinceId;
        int iHashCode10 = (iHashCode9 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.provinceName;
        int iHashCode11 = (iHashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.provinceCode;
        int iHashCode12 = (iHashCode11 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.regencyId;
        int iHashCode13 = (iHashCode12 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.regencyName;
        int iHashCode14 = (iHashCode13 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.regencyCode;
        int iHashCode15 = (iHashCode14 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.districtId;
        int iHashCode16 = (iHashCode15 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.districtName;
        int iHashCode17 = (iHashCode16 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.districtFullcode;
        int iHashCode18 = (iHashCode17 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.villageId;
        int iHashCode19 = (iHashCode18 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.villageName;
        int iHashCode20 = (iHashCode19 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.villageFullcode;
        int iHashCode21 = (iHashCode20 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.blokSensusId;
        int iHashCode22 = (iHashCode21 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.blokSensusFullCode;
        int iHashCode23 = (iHashCode22 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.data1;
        int iHashCode24 = (iHashCode23 + (str20 == null ? 0 : str20.hashCode())) * 31;
        String str21 = this.data2;
        int iHashCode25 = (iHashCode24 + (str21 == null ? 0 : str21.hashCode())) * 31;
        String str22 = this.data3;
        int iHashCode26 = (iHashCode25 + (str22 == null ? 0 : str22.hashCode())) * 31;
        String str23 = this.data4;
        int iHashCode27 = (iHashCode26 + (str23 == null ? 0 : str23.hashCode())) * 31;
        String str24 = this.data5;
        int iHashCode28 = (iHashCode27 + (str24 == null ? 0 : str24.hashCode())) * 31;
        Integer num2 = this.sum_error;
        int iHashCode29 = (iHashCode28 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.sum_remark;
        int iHashCode30 = (iHashCode29 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.sum_clean;
        int iHashCode31 = (iHashCode30 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Float f = this.latitude;
        int iHashCode32 = (iHashCode31 + (f == null ? 0 : f.hashCode())) * 31;
        Float f2 = this.longitude;
        int iHashCode33 = (iHashCode32 + (f2 == null ? 0 : f2.hashCode())) * 31;
        String str25 = this.strata;
        int iHashCode34 = (iHashCode33 + (str25 == null ? 0 : str25.hashCode())) * 31;
        String str26 = this.currentUserId;
        int iHashCode35 = (iHashCode34 + (str26 == null ? 0 : str26.hashCode())) * 31;
        String str27 = this.currentUserUsername;
        int iHashCode36 = (iHashCode35 + (str27 == null ? 0 : str27.hashCode())) * 31;
        String str28 = this.currentUserFullname;
        int iHashCode37 = (iHashCode36 + (str28 == null ? 0 : str28.hashCode())) * 31;
        String str29 = this.currentUserSurveyRoleId;
        int iHashCode38 = (iHashCode37 + (str29 == null ? 0 : str29.hashCode())) * 31;
        String str30 = this.currentUserSurveyRoleName;
        int iHashCode39 = (iHashCode38 + (str30 == null ? 0 : str30.hashCode())) * 31;
        Boolean bool = this.currentUserSurveyRoleIsPencacah;
        int iHashCode40 = (iHashCode39 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.currentUserSurveyRoleCanPullSample;
        int iHashCode41 = (iHashCode40 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        boolean z4 = this.offlineSend;
        int i6 = z4;
        if (z4 != 0) {
            i6 = 1;
        }
        int i7 = (iHashCode41 + i6) * 31;
        List<String> list = this.responsibility;
        int iHashCode42 = (i7 + (list == null ? 0 : list.hashCode())) * 31;
        List<AssignmentResponsibilityForResponseData> list2 = this.assignmentResponsibility;
        int iHashCode43 = (iHashCode42 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<AssignmentHistory> list3 = this.assignmentHistories;
        int iHashCode44 = (iHashCode43 + (list3 == null ? 0 : list3.hashCode())) * 31;
        boolean z5 = this.is_inside_blok_sensus;
        int i8 = z5;
        if (z5 != 0) {
            i8 = 1;
        }
        int i9 = (iHashCode44 + i8) * 31;
        Double d = this.latitude_if_outside;
        int iHashCode45 = (i9 + (d == null ? 0 : d.hashCode())) * 31;
        Double d2 = this.longitude_if_outside;
        int iHashCode46 = (iHashCode45 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str31 = this.keterangan_validasi;
        int iHashCode47 = (iHashCode46 + (str31 == null ? 0 : str31.hashCode())) * 31;
        Boolean bool3 = this.isDone;
        int iHashCode48 = (iHashCode47 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.secondary;
        int iHashCode49 = (iHashCode48 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        String str32 = this.copyFromId;
        int iHashCode50 = (iHashCode49 + (str32 == null ? 0 : str32.hashCode())) * 31;
        boolean z6 = this.original;
        int i10 = z6;
        if (z6 != 0) {
            i10 = 1;
        }
        int i11 = (iHashCode50 + i10) * 31;
        String str33 = this.paradata;
        int iHashCode51 = (i11 + (str33 == null ? 0 : str33.hashCode())) * 31;
        String str34 = this.data6;
        int iHashCode52 = (iHashCode51 + (str34 == null ? 0 : str34.hashCode())) * 31;
        String str35 = this.data7;
        int iHashCode53 = (iHashCode52 + (str35 == null ? 0 : str35.hashCode())) * 31;
        String str36 = this.data8;
        int iHashCode54 = (iHashCode53 + (str36 == null ? 0 : str36.hashCode())) * 31;
        String str37 = this.data9;
        int iHashCode55 = (iHashCode54 + (str37 == null ? 0 : str37.hashCode())) * 31;
        String str38 = this.data10;
        int iHashCode56 = (iHashCode55 + (str38 == null ? 0 : str38.hashCode())) * 31;
        List<String> list4 = this.mode;
        int iHashCode57 = (iHashCode56 + (list4 == null ? 0 : list4.hashCode())) * 31;
        String str39 = this.comment;
        int iHashCode58 = (iHashCode57 + (str39 == null ? 0 : str39.hashCode())) * 31;
        String str40 = this.basePathComment;
        int iHashCode59 = (iHashCode58 + (str40 == null ? 0 : str40.hashCode())) * 31;
        RegionMetadata regionMetadata = this.regionMetadata;
        int iHashCode60 = (iHashCode59 + (regionMetadata == null ? 0 : regionMetadata.hashCode())) * 31;
        Region region = this.region;
        int iHashCode61 = (iHashCode60 + (region == null ? 0 : region.hashCode())) * 31;
        String str41 = this.assignmentStatusAlias;
        int iHashCode62 = (iHashCode61 + (str41 == null ? 0 : str41.hashCode())) * 31;
        String str42 = this.note;
        int iHashCode63 = (iHashCode62 + (str42 == null ? 0 : str42.hashCode())) * 31;
        boolean z7 = this.isEncrypt;
        int i12 = (iHashCode63 + (z7 ? 1 : z7 ? 1 : 0)) * 31;
        String str43 = this.basePath;
        int iHashCode64 = (i12 + (str43 == null ? 0 : str43.hashCode())) * 31;
        String str44 = this.dataDownloadedAt;
        return ((iHashCode64 + (str44 != null ? str44.hashCode() : 0)) * 31) + Integer.hashCode(this.submitVersionCode);
    }

    public String toString() {
        return "AssignmentEntity(idPrimary=" + this.idPrimary + ", periodeId=" + this.periodeId + ", periodeNotPrimary=" + this.periodeNotPrimary + ", id=" + this.id + ", userIdAssignment=" + this.userIdAssignment + ", preDefinedData=" + this.preDefinedData + ", data=" + this.data + ", assignmentStatusId=" + this.assignmentStatusId + ", cawiToken=" + this.cawiToken + ", cawiPin=" + this.cawiPin + ", tarikSample=" + this.tarikSample + ", isNew=" + this.isNew + ", otherStatus=" + this.otherStatus + ", pendingStatus=" + this.pendingStatus + ", parentUser=" + this.parentUser + ", surveyId=" + this.surveyId + ", surveyIdNotPrimary=" + this.surveyIdNotPrimary + ", provinceId=" + this.provinceId + ", provinceName=" + this.provinceName + ", provinceCode=" + this.provinceCode + ", regencyId=" + this.regencyId + ", regencyName=" + this.regencyName + ", regencyCode=" + this.regencyCode + ", districtId=" + this.districtId + ", districtName=" + this.districtName + ", districtFullcode=" + this.districtFullcode + ", villageId=" + this.villageId + ", villageName=" + this.villageName + ", villageFullcode=" + this.villageFullcode + ", blokSensusId=" + this.blokSensusId + ", blokSensusFullCode=" + this.blokSensusFullCode + ", data1=" + this.data1 + ", data2=" + this.data2 + ", data3=" + this.data3 + ", data4=" + this.data4 + ", data5=" + this.data5 + ", sum_error=" + this.sum_error + ", sum_remark=" + this.sum_remark + ", sum_clean=" + this.sum_clean + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", strata=" + this.strata + ", currentUserId=" + this.currentUserId + ", currentUserUsername=" + this.currentUserUsername + ", currentUserFullname=" + this.currentUserFullname + ", currentUserSurveyRoleId=" + this.currentUserSurveyRoleId + ", currentUserSurveyRoleName=" + this.currentUserSurveyRoleName + ", currentUserSurveyRoleIsPencacah=" + this.currentUserSurveyRoleIsPencacah + ", currentUserSurveyRoleCanPullSample=" + this.currentUserSurveyRoleCanPullSample + ", offlineSend=" + this.offlineSend + ", responsibility=" + this.responsibility + ", assignmentResponsibility=" + this.assignmentResponsibility + ", assignmentHistories=" + this.assignmentHistories + ", is_inside_blok_sensus=" + this.is_inside_blok_sensus + ", latitude_if_outside=" + this.latitude_if_outside + ", longitude_if_outside=" + this.longitude_if_outside + ", keterangan_validasi=" + this.keterangan_validasi + ", isDone=" + this.isDone + ", secondary=" + this.secondary + ", copyFromId=" + this.copyFromId + ", original=" + this.original + ", paradata=" + this.paradata + ", data6=" + this.data6 + ", data7=" + this.data7 + ", data8=" + this.data8 + ", data9=" + this.data9 + ", data10=" + this.data10 + ", mode=" + this.mode + ", comment=" + this.comment + ", basePathComment=" + this.basePathComment + ", regionMetadata=" + this.regionMetadata + ", region=" + this.region + ", assignmentStatusAlias=" + this.assignmentStatusAlias + ", note=" + this.note + ", isEncrypt=" + this.isEncrypt + ", basePath=" + this.basePath + ", dataDownloadedAt=" + this.dataDownloadedAt + ", submitVersionCode=" + this.submitVersionCode + ")";
    }

    public AssignmentEntity(String idPrimary, String periodeId, String periodeNotPrimary, String id2, String str, String str2, DataAssignment dataAssignment, Integer num, String str3, String str4, boolean z, boolean z2, long j, boolean z3, String str5, String surveyId, String surveyIdNotPrimary, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, Integer num2, Integer num3, Integer num4, Float f, Float f2, String str25, String str26, String str27, String str28, String str29, String str30, Boolean bool, Boolean bool2, boolean z4, List<String> list, List<AssignmentResponsibilityForResponseData> list2, List<AssignmentHistory> list3, boolean z5, Double d, Double d2, String str31, Boolean bool3, Boolean bool4, String str32, boolean z6, String str33, String str34, String str35, String str36, String str37, String str38, List<String> list4, String str39, String str40, RegionMetadata regionMetadata, Region region, String str41, String str42, boolean z7, String str43, String str44, int i) {
        Intrinsics.checkNotNullParameter(idPrimary, "idPrimary");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(periodeNotPrimary, "periodeNotPrimary");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(surveyIdNotPrimary, "surveyIdNotPrimary");
        this.idPrimary = idPrimary;
        this.periodeId = periodeId;
        this.periodeNotPrimary = periodeNotPrimary;
        this.id = id2;
        this.userIdAssignment = str;
        this.preDefinedData = str2;
        this.data = dataAssignment;
        this.assignmentStatusId = num;
        this.cawiToken = str3;
        this.cawiPin = str4;
        this.tarikSample = z;
        this.isNew = z2;
        this.otherStatus = j;
        this.pendingStatus = z3;
        this.parentUser = str5;
        this.surveyId = surveyId;
        this.surveyIdNotPrimary = surveyIdNotPrimary;
        this.provinceId = str6;
        this.provinceName = str7;
        this.provinceCode = str8;
        this.regencyId = str9;
        this.regencyName = str10;
        this.regencyCode = str11;
        this.districtId = str12;
        this.districtName = str13;
        this.districtFullcode = str14;
        this.villageId = str15;
        this.villageName = str16;
        this.villageFullcode = str17;
        this.blokSensusId = str18;
        this.blokSensusFullCode = str19;
        this.data1 = str20;
        this.data2 = str21;
        this.data3 = str22;
        this.data4 = str23;
        this.data5 = str24;
        this.sum_error = num2;
        this.sum_remark = num3;
        this.sum_clean = num4;
        this.latitude = f;
        this.longitude = f2;
        this.strata = str25;
        this.currentUserId = str26;
        this.currentUserUsername = str27;
        this.currentUserFullname = str28;
        this.currentUserSurveyRoleId = str29;
        this.currentUserSurveyRoleName = str30;
        this.currentUserSurveyRoleIsPencacah = bool;
        this.currentUserSurveyRoleCanPullSample = bool2;
        this.offlineSend = z4;
        this.responsibility = list;
        this.assignmentResponsibility = list2;
        this.assignmentHistories = list3;
        this.is_inside_blok_sensus = z5;
        this.latitude_if_outside = d;
        this.longitude_if_outside = d2;
        this.keterangan_validasi = str31;
        this.isDone = bool3;
        this.secondary = bool4;
        this.copyFromId = str32;
        this.original = z6;
        this.paradata = str33;
        this.data6 = str34;
        this.data7 = str35;
        this.data8 = str36;
        this.data9 = str37;
        this.data10 = str38;
        this.mode = list4;
        this.comment = str39;
        this.basePathComment = str40;
        this.regionMetadata = regionMetadata;
        this.region = region;
        this.assignmentStatusAlias = str41;
        this.note = str42;
        this.isEncrypt = z7;
        this.basePath = str43;
        this.dataDownloadedAt = str44;
        this.submitVersionCode = i;
    }

    public final String getIdPrimary() {
        return this.idPrimary;
    }

    public final void setIdPrimary(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.idPrimary = str;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final void setPeriodeId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.periodeId = str;
    }

    public final String getPeriodeNotPrimary() {
        return this.periodeNotPrimary;
    }

    public final void setPeriodeNotPrimary(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.periodeNotPrimary = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getUserIdAssignment() {
        return this.userIdAssignment;
    }

    public final void setUserIdAssignment(String str) {
        this.userIdAssignment = str;
    }

    public final String getPreDefinedData() {
        return this.preDefinedData;
    }

    public final void setPreDefinedData(String str) {
        this.preDefinedData = str;
    }

    public final DataAssignment getData() {
        return this.data;
    }

    public final void setData(DataAssignment dataAssignment) {
        this.data = dataAssignment;
    }

    public /* synthetic */ AssignmentEntity(String str, String str2, String str3, String str4, String str5, String str6, DataAssignment dataAssignment, Integer num, String str7, String str8, boolean z, boolean z2, long j, boolean z3, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, Integer num2, Integer num3, Integer num4, Float f, Float f2, String str31, String str32, String str33, String str34, String str35, String str36, Boolean bool, Boolean bool2, boolean z4, List list, List list2, List list3, boolean z5, Double d, Double d2, String str37, Boolean bool3, Boolean bool4, String str38, boolean z6, String str39, String str40, String str41, String str42, String str43, String str44, List list4, String str45, String str46, RegionMetadata regionMetadata, Region region, String str47, String str48, boolean z7, String str49, String str50, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? "" : str6, dataAssignment, (i2 & 128) != 0 ? 0 : num, str7, str8, z, (i2 & 2048) != 0 ? false : z2, j, (i2 & 8192) != 0 ? false : z3, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25, (i2 & Integer.MIN_VALUE) != 0 ? "" : str26, (i3 & 1) != 0 ? "" : str27, (i3 & 2) != 0 ? "" : str28, (i3 & 4) != 0 ? "" : str29, (i3 & 8) != 0 ? "" : str30, num2, num3, num4, f, f2, str31, str32, str33, str34, str35, str36, bool, bool2, (i3 & 131072) != 0 ? false : z4, list, list2, list3, (i3 & 2097152) != 0 ? true : z5, (i3 & 4194304) != 0 ? Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : d, (i3 & 8388608) != 0 ? Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : d2, (i3 & 16777216) != 0 ? "" : str37, (i3 & 33554432) != 0 ? false : bool3, bool4, str38, (i3 & 268435456) != 0 ? true : z6, (i3 & 536870912) != 0 ? "" : str39, (i3 & 1073741824) != 0 ? "" : str40, (i3 & Integer.MIN_VALUE) != 0 ? "" : str41, (i4 & 1) != 0 ? "" : str42, (i4 & 2) != 0 ? "" : str43, (i4 & 4) != 0 ? "" : str44, list4, str45, str46, regionMetadata, region, str47, str48, (i4 & 1024) != 0 ? false : z7, str49, (i4 & 4096) != 0 ? null : str50, (i4 & 8192) != 0 ? 0 : i);
    }

    public final Integer getAssignmentStatusId() {
        return this.assignmentStatusId;
    }

    public final void setAssignmentStatusId(Integer num) {
        this.assignmentStatusId = num;
    }

    public final String getCawiToken() {
        return this.cawiToken;
    }

    public final void setCawiToken(String str) {
        this.cawiToken = str;
    }

    public final String getCawiPin() {
        return this.cawiPin;
    }

    public final void setCawiPin(String str) {
        this.cawiPin = str;
    }

    public final boolean getTarikSample() {
        return this.tarikSample;
    }

    public final void setTarikSample(boolean z) {
        this.tarikSample = z;
    }

    public final boolean isNew() {
        return this.isNew;
    }

    public final void setNew(boolean z) {
        this.isNew = z;
    }

    public final long getOtherStatus() {
        return this.otherStatus;
    }

    public final void setOtherStatus(long j) {
        this.otherStatus = j;
    }

    public final boolean getPendingStatus() {
        return this.pendingStatus;
    }

    public final void setPendingStatus(boolean z) {
        this.pendingStatus = z;
    }

    public final String getParentUser() {
        return this.parentUser;
    }

    public final void setParentUser(String str) {
        this.parentUser = str;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.surveyId = str;
    }

    public final String getSurveyIdNotPrimary() {
        return this.surveyIdNotPrimary;
    }

    public final void setSurveyIdNotPrimary(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.surveyIdNotPrimary = str;
    }

    public final String getProvinceId() {
        return this.provinceId;
    }

    public final void setProvinceId(String str) {
        this.provinceId = str;
    }

    public final String getProvinceName() {
        return this.provinceName;
    }

    public final void setProvinceName(String str) {
        this.provinceName = str;
    }

    public final String getProvinceCode() {
        return this.provinceCode;
    }

    public final void setProvinceCode(String str) {
        this.provinceCode = str;
    }

    public final String getRegencyId() {
        return this.regencyId;
    }

    public final void setRegencyId(String str) {
        this.regencyId = str;
    }

    public final String getRegencyName() {
        return this.regencyName;
    }

    public final void setRegencyName(String str) {
        this.regencyName = str;
    }

    public final String getRegencyCode() {
        return this.regencyCode;
    }

    public final void setRegencyCode(String str) {
        this.regencyCode = str;
    }

    public final String getDistrictId() {
        return this.districtId;
    }

    public final void setDistrictId(String str) {
        this.districtId = str;
    }

    public final String getDistrictName() {
        return this.districtName;
    }

    public final void setDistrictName(String str) {
        this.districtName = str;
    }

    public final String getDistrictFullcode() {
        return this.districtFullcode;
    }

    public final void setDistrictFullcode(String str) {
        this.districtFullcode = str;
    }

    public final String getVillageId() {
        return this.villageId;
    }

    public final void setVillageId(String str) {
        this.villageId = str;
    }

    public final String getVillageName() {
        return this.villageName;
    }

    public final void setVillageName(String str) {
        this.villageName = str;
    }

    public final String getVillageFullcode() {
        return this.villageFullcode;
    }

    public final void setVillageFullcode(String str) {
        this.villageFullcode = str;
    }

    public final String getBlokSensusId() {
        return this.blokSensusId;
    }

    public final void setBlokSensusId(String str) {
        this.blokSensusId = str;
    }

    public final String getBlokSensusFullCode() {
        return this.blokSensusFullCode;
    }

    public final void setBlokSensusFullCode(String str) {
        this.blokSensusFullCode = str;
    }

    public final String getData1() {
        return this.data1;
    }

    public final void setData1(String str) {
        this.data1 = str;
    }

    public final String getData2() {
        return this.data2;
    }

    public final void setData2(String str) {
        this.data2 = str;
    }

    public final String getData3() {
        return this.data3;
    }

    public final void setData3(String str) {
        this.data3 = str;
    }

    public final String getData4() {
        return this.data4;
    }

    public final void setData4(String str) {
        this.data4 = str;
    }

    public final String getData5() {
        return this.data5;
    }

    public final void setData5(String str) {
        this.data5 = str;
    }

    public final Integer getSum_error() {
        return this.sum_error;
    }

    public final void setSum_error(Integer num) {
        this.sum_error = num;
    }

    public final Integer getSum_remark() {
        return this.sum_remark;
    }

    public final void setSum_remark(Integer num) {
        this.sum_remark = num;
    }

    public final Integer getSum_clean() {
        return this.sum_clean;
    }

    public final void setSum_clean(Integer num) {
        this.sum_clean = num;
    }

    public final Float getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(Float f) {
        this.latitude = f;
    }

    public final Float getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(Float f) {
        this.longitude = f;
    }

    public final String getStrata() {
        return this.strata;
    }

    public final void setStrata(String str) {
        this.strata = str;
    }

    public final String getCurrentUserId() {
        return this.currentUserId;
    }

    public final void setCurrentUserId(String str) {
        this.currentUserId = str;
    }

    public final String getCurrentUserUsername() {
        return this.currentUserUsername;
    }

    public final void setCurrentUserUsername(String str) {
        this.currentUserUsername = str;
    }

    public final String getCurrentUserFullname() {
        return this.currentUserFullname;
    }

    public final void setCurrentUserFullname(String str) {
        this.currentUserFullname = str;
    }

    public final String getCurrentUserSurveyRoleId() {
        return this.currentUserSurveyRoleId;
    }

    public final void setCurrentUserSurveyRoleId(String str) {
        this.currentUserSurveyRoleId = str;
    }

    public final String getCurrentUserSurveyRoleName() {
        return this.currentUserSurveyRoleName;
    }

    public final void setCurrentUserSurveyRoleName(String str) {
        this.currentUserSurveyRoleName = str;
    }

    public final Boolean getCurrentUserSurveyRoleIsPencacah() {
        return this.currentUserSurveyRoleIsPencacah;
    }

    public final void setCurrentUserSurveyRoleIsPencacah(Boolean bool) {
        this.currentUserSurveyRoleIsPencacah = bool;
    }

    public final Boolean getCurrentUserSurveyRoleCanPullSample() {
        return this.currentUserSurveyRoleCanPullSample;
    }

    public final void setCurrentUserSurveyRoleCanPullSample(Boolean bool) {
        this.currentUserSurveyRoleCanPullSample = bool;
    }

    public final boolean getOfflineSend() {
        return this.offlineSend;
    }

    public final void setOfflineSend(boolean z) {
        this.offlineSend = z;
    }

    public final List<String> getResponsibility() {
        return this.responsibility;
    }

    public final void setResponsibility(List<String> list) {
        this.responsibility = list;
    }

    public final List<AssignmentResponsibilityForResponseData> getAssignmentResponsibility() {
        return this.assignmentResponsibility;
    }

    public final void setAssignmentResponsibility(List<AssignmentResponsibilityForResponseData> list) {
        this.assignmentResponsibility = list;
    }

    public final List<AssignmentHistory> getAssignmentHistories() {
        return this.assignmentHistories;
    }

    public final void setAssignmentHistories(List<AssignmentHistory> list) {
        this.assignmentHistories = list;
    }

    public final boolean is_inside_blok_sensus() {
        return this.is_inside_blok_sensus;
    }

    public final void set_inside_blok_sensus(boolean z) {
        this.is_inside_blok_sensus = z;
    }

    public final Double getLatitude_if_outside() {
        return this.latitude_if_outside;
    }

    public final void setLatitude_if_outside(Double d) {
        this.latitude_if_outside = d;
    }

    public final Double getLongitude_if_outside() {
        return this.longitude_if_outside;
    }

    public final void setLongitude_if_outside(Double d) {
        this.longitude_if_outside = d;
    }

    public final String getKeterangan_validasi() {
        return this.keterangan_validasi;
    }

    public final void setKeterangan_validasi(String str) {
        this.keterangan_validasi = str;
    }

    public final Boolean isDone() {
        return this.isDone;
    }

    public final void setDone(Boolean bool) {
        this.isDone = bool;
    }

    public final Boolean getSecondary() {
        return this.secondary;
    }

    public final void setSecondary(Boolean bool) {
        this.secondary = bool;
    }

    public final String getCopyFromId() {
        return this.copyFromId;
    }

    public final void setCopyFromId(String str) {
        this.copyFromId = str;
    }

    public final boolean getOriginal() {
        return this.original;
    }

    public final void setOriginal(boolean z) {
        this.original = z;
    }

    public final String getParadata() {
        return this.paradata;
    }

    public final void setParadata(String str) {
        this.paradata = str;
    }

    public final String getData6() {
        return this.data6;
    }

    public final void setData6(String str) {
        this.data6 = str;
    }

    public final String getData7() {
        return this.data7;
    }

    public final void setData7(String str) {
        this.data7 = str;
    }

    public final String getData8() {
        return this.data8;
    }

    public final void setData8(String str) {
        this.data8 = str;
    }

    public final String getData9() {
        return this.data9;
    }

    public final void setData9(String str) {
        this.data9 = str;
    }

    public final String getData10() {
        return this.data10;
    }

    public final void setData10(String str) {
        this.data10 = str;
    }

    public final List<String> getMode() {
        return this.mode;
    }

    public final void setMode(List<String> list) {
        this.mode = list;
    }

    public final String getComment() {
        return this.comment;
    }

    public final void setComment(String str) {
        this.comment = str;
    }

    public final String getBasePathComment() {
        return this.basePathComment;
    }

    public final void setBasePathComment(String str) {
        this.basePathComment = str;
    }

    public final RegionMetadata getRegionMetadata() {
        return this.regionMetadata;
    }

    public final void setRegionMetadata(RegionMetadata regionMetadata) {
        this.regionMetadata = regionMetadata;
    }

    public final Region getRegion() {
        return this.region;
    }

    public final void setRegion(Region region) {
        this.region = region;
    }

    public final String getAssignmentStatusAlias() {
        return this.assignmentStatusAlias;
    }

    public final void setAssignmentStatusAlias(String str) {
        this.assignmentStatusAlias = str;
    }

    public final String getNote() {
        return this.note;
    }

    public final void setNote(String str) {
        this.note = str;
    }

    public final boolean isEncrypt() {
        return this.isEncrypt;
    }

    public final void setEncrypt(boolean z) {
        this.isEncrypt = z;
    }

    public final String getBasePath() {
        return this.basePath;
    }

    public final void setBasePath(String str) {
        this.basePath = str;
    }

    public final String getDataDownloadedAt() {
        return this.dataDownloadedAt;
    }

    public final void setDataDownloadedAt(String str) {
        this.dataDownloadedAt = str;
    }

    public final int getSubmitVersionCode() {
        return this.submitVersionCode;
    }

    public final void setSubmitVersionCode(int i) {
        this.submitVersionCode = i;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AssignmentEntity() {
        Double dValueOf = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        this("", "", "", "", "", "", null, 0, null, null, false, false, 0L, false, null, "", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, "", "", "", "", "", null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, true, dValueOf, dValueOf, "", false, null, null, true, "", "", "", "", "", "", null, null, null, null, null, null, null, false, null, null, 0);
    }

    /* compiled from: AssigmentEntity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\n"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentEntity$Companion;", "", "()V", "mapIdToAssignment", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "surveyId", "", "periodeId", "assignments", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<AssignmentEntity> mapIdToAssignment(String surveyId, String periodeId, List<AssignmentEntity> assignments) {
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            Intrinsics.checkNotNullParameter(assignments, "assignments");
            for (AssignmentEntity assignmentEntity : assignments) {
                assignmentEntity.setUserIdAssignment(FasihApp.INSTANCE.getSession().getUserId());
                assignmentEntity.setIdPrimary(assignmentEntity.getId() + File.separator + assignmentEntity.getUserIdAssignment());
                assignmentEntity.setPeriodeNotPrimary(periodeId);
                assignmentEntity.setPeriodeId(periodeId + File.separator + assignmentEntity.getUserIdAssignment());
                assignmentEntity.setSurveyIdNotPrimary(surveyId);
                assignmentEntity.setSurveyId(surveyId + File.separator + assignmentEntity.getUserIdAssignment());
                assignmentEntity.setOriginal(true);
            }
            return assignments;
        }
    }
}

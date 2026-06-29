package id.go.bpsfasih.data;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.ui.formGear.StaticValue;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CommonCons.kt */
@Metadata(d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0007\n\u0003\b«\u0001\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0014\u0010#\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0014\u0010%\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0014\u0010'\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u000e\u0010)\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0014\u0010.\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b/\u0010 R\u0014\u00100\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b1\u0010 R\u0014\u00102\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0006R\u0014\u00104\u001a\u000205X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0006R\u0014\u0010:\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b;\u0010 R\u0014\u0010<\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0006R\u0014\u0010>\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0006R\u001a\u0010@\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0006\"\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0006R\u0014\u0010F\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0006R\u0014\u0010H\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0006R\u000e\u0010J\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010K\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0006R\u0014\u0010M\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0006R\u0014\u0010O\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0006R\u0014\u0010Q\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0006R\u0014\u0010S\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0006R\u0014\u0010U\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0006R\u0014\u0010W\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0006R\u0014\u0010Y\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0006R\u0014\u0010[\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0006R\u0014\u0010]\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0006R\u0014\u0010_\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0006R\u0014\u0010a\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0006R\u0014\u0010c\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0006R\u0014\u0010e\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0006R\u0014\u0010g\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0006R\u0014\u0010i\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0006R\u0014\u0010k\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0006R\u0014\u0010m\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0006R\u0014\u0010o\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0006R\u0014\u0010q\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0006R\u0014\u0010s\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bt\u0010\u0006R\u0014\u0010u\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\u0006R\u0014\u0010w\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bx\u0010\u0006R\u0014\u0010y\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\u0006R\u0014\u0010{\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b|\u0010\u0006R\u0014\u0010}\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b~\u0010\u0006R\u0015\u0010\u007f\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010\u0006R\u0016\u0010\u0081\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0006R\u0016\u0010\u0083\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0006R\u0016\u0010\u0085\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010\u0006R\u0016\u0010\u0087\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u0006R\u0016\u0010\u0089\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010\u0006R\u0016\u0010\u008b\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010\u0006R\u0016\u0010\u008d\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0006R\u0016\u0010\u008f\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0090\u0001\u0010\u0006R\u0016\u0010\u0091\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010\u0006R\u000f\u0010\u0093\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0094\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u0095\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0096\u0001\u001a\u00020\u001eX\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010 R\u0016\u0010\u0098\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0099\u0001\u0010\u0006R\u0016\u0010\u009a\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010\u0006R\u000f\u0010\u009c\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009d\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010\u009e\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u009f\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b \u0001\u0010\u0006R\u0016\u0010¡\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\u0006R\u0016\u0010£\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¤\u0001\u0010\u0006R\u0016\u0010¥\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¦\u0001\u0010\u0006R\u0016\u0010§\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¨\u0001\u0010\u0006R\u0016\u0010©\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bª\u0001\u0010\u0006R\u0016\u0010«\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¬\u0001\u0010\u0006R\u0016\u0010\u00ad\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b®\u0001\u0010\u0006R\u0016\u0010¯\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b°\u0001\u0010\u0006R\u0016\u0010±\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b²\u0001\u0010\u0006R\u0016\u0010³\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b´\u0001\u0010\u0006R\u0016\u0010µ\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¶\u0001\u0010\u0006R\u0016\u0010·\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¸\u0001\u0010\u0006R\u0016\u0010¹\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bº\u0001\u0010\u0006R\u0016\u0010»\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¼\u0001\u0010\u0006R\u0016\u0010½\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¾\u0001\u0010\u0006R\u0016\u0010¿\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÀ\u0001\u0010\u0006R\u0016\u0010Á\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÂ\u0001\u0010\u0006R\u0016\u0010Ã\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÄ\u0001\u0010\u0006R\u0016\u0010Å\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÆ\u0001\u0010\u0006R\u000f\u0010Ç\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010È\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÉ\u0001\u0010\u0006R\u0016\u0010Ê\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bË\u0001\u0010\u0006R\u000f\u0010Ì\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010Í\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010Î\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010Ï\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010Ð\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÑ\u0001\u0010\u0006R\u0016\u0010Ò\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÓ\u0001\u0010\u0006R\u0016\u0010Ô\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÕ\u0001\u0010\u0006R\u0016\u0010Ö\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b×\u0001\u0010\u0006R\u0016\u0010Ø\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÙ\u0001\u0010\u0006R\u000f\u0010Ú\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010Û\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010Ü\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010Ý\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÞ\u0001\u0010\u0006R\u000f\u0010ß\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0018\u0010à\u0001\u001a\u00030á\u0001X\u0086D¢\u0006\n\n\u0000\u001a\u0006\bâ\u0001\u0010ã\u0001R\u000f\u0010ä\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010å\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010æ\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010ç\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010è\u0001\u001a\u00020\u001eX\u0086D¢\u0006\t\n\u0000\u001a\u0005\bé\u0001\u0010 R\u000f\u0010ê\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010ë\u0001\u001a\u00020\u001eX\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010ì\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010í\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000f\u0010î\u0001\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010ï\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bð\u0001\u0010\u0006R\u0016\u0010ñ\u0001\u001a\u00020\u001eX\u0086D¢\u0006\t\n\u0000\u001a\u0005\bò\u0001\u0010 R\u0016\u0010ó\u0001\u001a\u00020\u001eX\u0086D¢\u0006\t\n\u0000\u001a\u0005\bô\u0001\u0010 R\u0016\u0010õ\u0001\u001a\u00020\u001eX\u0086D¢\u0006\t\n\u0000\u001a\u0005\bö\u0001\u0010 R\u0016\u0010÷\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bø\u0001\u0010\u0006R\u0016\u0010ù\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bú\u0001\u0010\u0006R\"\u0010û\u0001\u001a\u0005\u0018\u00010ü\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bý\u0001\u0010þ\u0001\"\u0006\bÿ\u0001\u0010\u0080\u0002¨\u0006\u0081\u0002"}, d2 = {"Lid/go/bpsfasih/data/CommonCons;", "", "()V", "ABSOLUTE_PATH", "", "getABSOLUTE_PATH", "()Ljava/lang/String;", "ACTION_LOG_APPROVE", "getACTION_LOG_APPROVE", "ACTION_LOG_CHANGE_MODE", "getACTION_LOG_CHANGE_MODE", "ACTION_LOG_CLOSED", "getACTION_LOG_CLOSED", "ACTION_LOG_EDIT", "getACTION_LOG_EDIT", "ACTION_LOG_GET_LOCATION", "getACTION_LOG_GET_LOCATION", "ACTION_LOG_OPEN", "getACTION_LOG_OPEN", "ACTION_LOG_REJECT", "getACTION_LOG_REJECT", "ACTION_LOG_REVOKE", "getACTION_LOG_REVOKE", "ACTION_LOG_SUBMIT", "getACTION_LOG_SUBMIT", "APPLICATION_STORAGE", "getAPPLICATION_STORAGE", "ASSIGNMENT_FOLDER_TEMP", "getASSIGNMENT_FOLDER_TEMP", "ASSIGNMENT_STATUS_APPROVED", "", "getASSIGNMENT_STATUS_APPROVED", "()I", "ASSIGNMENT_STATUS_OPEN", "getASSIGNMENT_STATUS_OPEN", "ASSIGNMENT_STATUS_PENDING", "getASSIGNMENT_STATUS_PENDING", "ASSIGNMENT_STATUS_REJECTED", "getASSIGNMENT_STATUS_REJECTED", "ASSIGNMENT_STATUS_SUBMITED", "getASSIGNMENT_STATUS_SUBMITED", "BTN_APPROVAL_APPROVE_OR_REJECT", "BTN_APPROVAL_N0_ACTION", "BTN_APPROVAL_REVOKE", "CODE_LOCATION_HISTORY", "getCODE_LOCATION_HISTORY", "COUNT_PARTIAL_REQUEST_ANSWER", "getCOUNT_PARTIAL_REQUEST_ANSWER", "COUNT_PARTIAL_REQUEST_ANSWER_S3", "getCOUNT_PARTIAL_REQUEST_ANSWER_S3", "DEFAULT_URL", "getDEFAULT_URL", "DISTANCE_LIVE_TRACKING", "", "getDISTANCE_LIVE_TRACKING", "()F", "ENCRYPTION_SECRET_KEY", "getENCRYPTION_SECRET_KEY", "ERROR_CODE_GENERAL", "getERROR_CODE_GENERAL", "EXTENSION_7ZIP", "getEXTENSION_7ZIP", "EXTENSION_JSON", "getEXTENSION_JSON", "EXTENSION_LOG", "getEXTENSION_LOG", "setEXTENSION_LOG", "(Ljava/lang/String;)V", "EXTENSION_TXT", "getEXTENSION_TXT", "EXTENSION_ZIP", "getEXTENSION_ZIP", "FLAG", "getFLAG", "FORMGEAR_ID_DUMMY", "FORM_ENGINE_ID", "getFORM_ENGINE_ID", "FORM_ENGINE_NAME", "getFORM_ENGINE_NAME", "FORM_ENGINE_URL", "getFORM_ENGINE_URL", "FRAME_CATEGORY_ID", "getFRAME_CATEGORY_ID", "KEY_ACCURACY", "getKEY_ACCURACY", "KEY_ANSWER_PATH", "getKEY_ANSWER_PATH", "KEY_ASSIGNMENT_ID", "getKEY_ASSIGNMENT_ID", "KEY_ASSIGNMENT_STATUS", "getKEY_ASSIGNMENT_STATUS", "KEY_CAN_ADD_SAMPLE", "getKEY_CAN_ADD_SAMPLE", "KEY_FORM_DATA_KEY", "getKEY_FORM_DATA_KEY", "KEY_FORM_ENGINE_ID", "getKEY_FORM_ENGINE_ID", "KEY_ID_LOCATION_HISTORY", "getKEY_ID_LOCATION_HISTORY", "KEY_IS_KOFAX", "getKEY_IS_KOFAX", "KEY_IS_PENCACAH", "getKEY_IS_PENCACAH", "KEY_IS_UPDATE_LISTING", "getKEY_IS_UPDATE_LISTING", "KEY_LATITUDE", "getKEY_LATITUDE", "KEY_LIST_ID_ASSIGNMENT", "getKEY_LIST_ID_ASSIGNMENT", "KEY_LONGITUDE", "getKEY_LONGITUDE", "KEY_MODE", "getKEY_MODE", "KEY_OFFLINE_MODE", "getKEY_OFFLINE_MODE", "KEY_ONLINE_MODE", "getKEY_ONLINE_MODE", "KEY_PANEL_TYPE", "getKEY_PANEL_TYPE", "KEY_PATH_LOCATION_HISTORY", "getKEY_PATH_LOCATION_HISTORY", "KEY_PERIODE_ID", "getKEY_PERIODE_ID", "KEY_PERIODE_ID_ONLY", "getKEY_PERIODE_ID_ONLY", "KEY_REGION_FULL_CODE", "getKEY_REGION_FULL_CODE", "KEY_REGION_ID", "getKEY_REGION_ID", "KEY_REGION_NAME", "getKEY_REGION_NAME", "KEY_SAMPLING_ID", "getKEY_SAMPLING_ID", "KEY_SAMPLING_REGION_ID", "getKEY_SAMPLING_REGION_ID", "KEY_SAVE_DATA", "getKEY_SAVE_DATA", "KEY_SURVEY_ID", "getKEY_SURVEY_ID", "KEY_SURVEY_PARENT_ID", "getKEY_SURVEY_PARENT_ID", "KEY_TOKEN_ENABLE", "getKEY_TOKEN_ENABLE", "KEY_VIEW_LEVEL_ID", "getKEY_VIEW_LEVEL_ID", "LIST_UNDUH_CHANGE_MODE_DOWNLOAD", "getLIST_UNDUH_CHANGE_MODE_DOWNLOAD", CommonCons.MENU_UNDUH_CHANGE_MODE, "MODE_DEV", "MODE_PROD", "NOTIF_ID", "getNOTIF_ID", "PATH_ANSWER", "getPATH_ANSWER", "PREDEFINED_DATA_ASSIGNMENT", "getPREDEFINED_DATA_ASSIGNMENT", "REMOTE_CONFIG_FEATURE_TRACKING_LOCATION", "REMOTE_CONFIG_LIVE_TRACKING", "REMOTE_CONFIG_WAITING_TIME_SYNC_ALL_ASSIGNMENT", "SESSION_ACCESS_TOKEN", "getSESSION_ACCESS_TOKEN", "SESSION_AUTH", "getSESSION_AUTH", "SESSION_DEVICE_ID", "getSESSION_DEVICE_ID", "SESSION_EMAIL", "getSESSION_EMAIL", "SESSION_EXPIRED", "getSESSION_EXPIRED", "SESSION_FIRST_NAME", "getSESSION_FIRST_NAME", "SESSION_ID", "getSESSION_ID", "SESSION_LAST_NAME", "getSESSION_LAST_NAME", "SESSION_LIST_GROUP", "getSESSION_LIST_GROUP", "SESSION_NIK", "getSESSION_NIK", "SESSION_PHONE", "getSESSION_PHONE", "SESSION_REFRESH_EXPIRED", "getSESSION_REFRESH_EXPIRED", "SESSION_REFRESH_TOKEN", "getSESSION_REFRESH_TOKEN", "SESSION_REGENCY_ID", "getSESSION_REGENCY_ID", "SESSION_ROLE_ID", "getSESSION_ROLE_ID", "SESSION_SERVER_URL", "getSESSION_SERVER_URL", "SESSION_STATE", "getSESSION_STATE", "SESSION_TOKEN_TYPE", "getSESSION_TOKEN_TYPE", "SESSION_USERNAME", "getSESSION_USERNAME", "SIGNATUREPATH", "getSIGNATUREPATH", "SIGNATURE_REQUEST_CODE", "SURVEY_NAME", "getSURVEY_NAME", "SURVEY_TITLE", "getSURVEY_TITLE", "SURVEY_TYPE_IS_AREA", "SURVEY_TYPE_IS_PENCACAH", "SURVEY_TYPE_IS_UPDATE", "SYNC_INTENT", "TAG", "getTAG", "TAG_UPLOAD_ERROR_BLOCK_SENSUS", "getTAG_UPLOAD_ERROR_BLOCK_SENSUS", "TAG_UPLOAD_ERROR_HOUSE_HOLD_NAME", "getTAG_UPLOAD_ERROR_HOUSE_HOLD_NAME", "TAG_UPLOAD_ERROR_PERIOD", "getTAG_UPLOAD_ERROR_PERIOD", "TAG_UPLOAD_ERROR_SURVEY", "getTAG_UPLOAD_ERROR_SURVEY", "TAKE_BARCODE", "TAKE_GALLERY", "TAKE_PICTURE", "TEMPLATE_ID", "getTEMPLATE_ID", "TIMESTAMP_AVAIABLE_SYNC_ALL_ASS", "TIME_LIVE_TRACKING", "", "getTIME_LIVE_TRACKING", "()J", "UPLOAD_APPROVE_OR_REJECT", "UPLOAD_CHANGE_MODE_WITHOUT_DATA", "UPLOAD_CHANGE_MODE_WITH_DATA", "UPLOAD_EDIT", "UPLOAD_MULTIPART_BUFFER_SIZE", "getUPLOAD_MULTIPART_BUFFER_SIZE", "UPLOAD_REVOKE", "UPLOAD_SUBMIT", "URL_REMOTE_CONFIG_CHANGELOG_APPLICATION", "URL_REMOTE_CONFIG_CHANGELOG_FORM_GEAR", "URL_REMOTE_CONFIG_KEBIJAKAN_PRIVASI", "WARNING_KATEGORI", "getWARNING_KATEGORI", "WARNING_PERTAMA_AKSES", "getWARNING_PERTAMA_AKSES", "WARNING_TIDAK_ADA_DATA", "getWARNING_TIDAK_ADA_DATA", "WARNING_UNDUH_ENGINE", "getWARNING_UNDUH_ENGINE", "WEB_FOLDER", "getWEB_FOLDER", "WEB_INDEX", "getWEB_INDEX", "json", "Lorg/json/JSONObject;", "getJson", "()Lorg/json/JSONObject;", "setJson", "(Lorg/json/JSONObject;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CommonCons {
    private static final int ASSIGNMENT_STATUS_OPEN = 0;
    public static final int BTN_APPROVAL_APPROVE_OR_REJECT = 1;
    public static final int BTN_APPROVAL_N0_ACTION = 0;
    public static final int BTN_APPROVAL_REVOKE = 2;
    public static final String FORMGEAR_ID_DUMMY = "5241ea40-e34d-479b-91c1-d0769d9fa1da";
    public static final String MENU_UNDUH_CHANGE_MODE = "MENU_UNDUH_CHANGE_MODE";
    public static final String MODE_DEV = "dev";
    public static final String MODE_PROD = "prod";
    public static final String REMOTE_CONFIG_FEATURE_TRACKING_LOCATION = "tracking_location";
    public static final String REMOTE_CONFIG_LIVE_TRACKING = "live_tracking";
    public static final String REMOTE_CONFIG_WAITING_TIME_SYNC_ALL_ASSIGNMENT = "sync_all_assignment";
    public static final int SIGNATURE_REQUEST_CODE = 100;
    public static final String SURVEY_TYPE_IS_AREA = "survey_type_area";
    public static final String SURVEY_TYPE_IS_PENCACAH = "survey_type_pencacah";
    public static final String SURVEY_TYPE_IS_UPDATE = "survey_type_update";
    public static final int SYNC_INTENT = 4;
    public static final int TAKE_BARCODE = 49374;
    public static final int TAKE_GALLERY = 2;
    public static final int TAKE_PICTURE = 1;
    public static final String TIMESTAMP_AVAIABLE_SYNC_ALL_ASS = "timestamp_avaiable_sync_all_ass";
    private static final long TIME_LIVE_TRACKING = 0;
    public static final int UPLOAD_APPROVE_OR_REJECT = 2;
    public static final int UPLOAD_CHANGE_MODE_WITHOUT_DATA = 5;
    public static final int UPLOAD_CHANGE_MODE_WITH_DATA = 4;
    public static final int UPLOAD_EDIT = 6;
    public static final int UPLOAD_REVOKE = 3;
    public static final int UPLOAD_SUBMIT = 1;
    public static final String URL_REMOTE_CONFIG_CHANGELOG_APPLICATION = "url_changelog_app";
    public static final String URL_REMOTE_CONFIG_CHANGELOG_FORM_GEAR = "url_changelog_formgear";
    public static final String URL_REMOTE_CONFIG_KEBIJAKAN_PRIVASI = "url_kebijakan_privasi";
    private static JSONObject json;
    public static final CommonCons INSTANCE = new CommonCons();
    private static final String APPLICATION_STORAGE = "BPS";
    private static final String DEFAULT_URL = "";
    private static final String ABSOLUTE_PATH = ContextCompat.getExternalFilesDirs(FasihApp.INSTANCE.getContext(), null)[0].getAbsolutePath() + File.separator + "BPS" + File.separator;
    private static final String WEB_FOLDER = "new-capi/pages";
    private static final String WEB_INDEX = "index.html";
    private static final String ASSIGNMENT_FOLDER_TEMP = "temp";
    private static final String PATH_ANSWER = "answers";
    private static final int NOTIF_ID = 234932;
    private static final int UPLOAD_MULTIPART_BUFFER_SIZE = 10240;
    private static final String SURVEY_TITLE = "Survey";
    private static String EXTENSION_LOG = ".txt";
    private static final String EXTENSION_ZIP = ".zip";
    private static final String EXTENSION_7ZIP = ".7z";
    private static final String EXTENSION_JSON = ".json";
    private static final String EXTENSION_TXT = ".txt";
    private static final String SESSION_ID = DownloadModel.ID;
    private static final String SESSION_DEVICE_ID = "deviceId";
    private static final String SESSION_USERNAME = HintConstants.AUTOFILL_HINT_USERNAME;
    private static final String SESSION_ROLE_ID = "role_id";
    private static final String SESSION_NIK = "nik";
    private static final String SESSION_REGENCY_ID = "regencyId";
    private static final String SESSION_AUTH = "auth";
    private static final String SESSION_EXPIRED = "authExpired";
    private static final String SESSION_REFRESH_EXPIRED = "authExpiredRefresh";
    private static final String SESSION_FIRST_NAME = "firstName";
    private static final String SESSION_LAST_NAME = "lastName";
    private static final String SESSION_PHONE = HintConstants.AUTOFILL_HINT_PHONE;
    private static final String SESSION_EMAIL = "email";
    private static final String SESSION_ACCESS_TOKEN = "access_token";
    private static final String SESSION_REFRESH_TOKEN = "refresh_token";
    private static final String SESSION_TOKEN_TYPE = "token_type";
    private static final String SESSION_SERVER_URL = "server_url";
    private static final String SESSION_LIST_GROUP = "list_group";
    private static final String SESSION_STATE = "session_state";
    private static final float DISTANCE_LIVE_TRACKING = 20.0f;
    private static final int ASSIGNMENT_STATUS_SUBMITED = 1;
    private static final int ASSIGNMENT_STATUS_APPROVED = 2;
    private static final int ASSIGNMENT_STATUS_REJECTED = 3;
    private static final int ASSIGNMENT_STATUS_PENDING = 99;
    private static final int ERROR_CODE_GENERAL = 999;
    private static final String TAG = "tag";
    private static final String SIGNATUREPATH = "signaturePath";
    private static final String KEY_ASSIGNMENT_ID = "assignment_id";
    private static final String PREDEFINED_DATA_ASSIGNMENT = "predefinedData";
    private static final String KEY_SURVEY_ID = "survey_id";
    private static final String KEY_SURVEY_PARENT_ID = "survey_parent_id";
    private static final String KEY_PERIODE_ID = "periode_id";
    private static final String KEY_TOKEN_ENABLE = "token_enable";
    private static final String KEY_VIEW_LEVEL_ID = "view_level_id";
    private static final String FRAME_CATEGORY_ID = "frame_category_id";
    private static final String TEMPLATE_ID = "template__id";
    private static final String SURVEY_NAME = "survey_name";
    private static final String KEY_ASSIGNMENT_STATUS = "mAssignmentStatus";
    private static final String KEY_IS_PENCACAH = "is_pencacah";
    private static final String KEY_PANEL_TYPE = "panel_type";
    private static final String KEY_IS_KOFAX = "is_kofaxs";
    private static final String KEY_SAVE_DATA = "save_data";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_ACCURACY = "accuracy";
    private static final String KEY_CAN_ADD_SAMPLE = "can_add_sample";
    private static final String KEY_IS_UPDATE_LISTING = "is_update_listing";
    private static final String KEY_FORM_ENGINE_ID = "form_engine_id";
    private static final String KEY_FORM_DATA_KEY = "data_key";
    private static final String KEY_PERIODE_ID_ONLY = "periode_id_only";
    private static final String KEY_MODE = "mode";
    private static final String KEY_OFFLINE_MODE = "OFFLINE";
    private static final String KEY_ONLINE_MODE = "ONLINE";
    private static final String KEY_ANSWER_PATH = "answer_path";
    private static final String KEY_SAMPLING_ID = "sampling_id";
    private static final String KEY_SAMPLING_REGION_ID = "sampling_region_id";
    private static final String KEY_REGION_ID = "region_id";
    private static final String KEY_REGION_FULL_CODE = "region_full_code";
    private static final String KEY_REGION_NAME = "region_name";
    private static final String KEY_LIST_ID_ASSIGNMENT = "list_id_assignment";
    private static final String KEY_ID_LOCATION_HISTORY = "id_location_history";
    private static final String KEY_PATH_LOCATION_HISTORY = "path_location_history";
    private static final int CODE_LOCATION_HISTORY = 112;
    private static final String TAG_UPLOAD_ERROR_SURVEY = "surveyName";
    private static final String TAG_UPLOAD_ERROR_PERIOD = "periodName";
    private static final String TAG_UPLOAD_ERROR_BLOCK_SENSUS = "blockSensus";
    private static final String TAG_UPLOAD_ERROR_HOUSE_HOLD_NAME = "houseHoldName";
    private static final String FLAG = "flag";
    private static final String FORM_ENGINE_NAME = "form_gear_name";
    private static final String FORM_ENGINE_ID = "form_gear_id";
    private static final String FORM_ENGINE_URL = "form_engine_url";
    private static final String ACTION_LOG_OPEN = "OPEN";
    private static final String ACTION_LOG_CLOSED = "CLOSE";
    private static final String ACTION_LOG_SUBMIT = StaticValue.Actions.FLAG_SUBMIT;
    private static final String ACTION_LOG_APPROVE = "APPROVE";
    private static final String ACTION_LOG_REJECT = "REJECT";
    private static final String ACTION_LOG_REVOKE = "REVOKE";
    private static final String ACTION_LOG_CHANGE_MODE = "CHANGE_MODE";
    private static final String ACTION_LOG_EDIT = "EDIT";
    private static final String ACTION_LOG_GET_LOCATION = "GET_LOCATION";
    private static final String LIST_UNDUH_CHANGE_MODE_DOWNLOAD = "unduh_change_mode_downloaded.json";
    private static final int COUNT_PARTIAL_REQUEST_ANSWER = 25;
    private static final int COUNT_PARTIAL_REQUEST_ANSWER_S3 = 100;
    private static final String WARNING_KATEGORI = "kategori_warning";
    private static final int WARNING_PERTAMA_AKSES = 1;
    private static final int WARNING_TIDAK_ADA_DATA = 2;
    private static final int WARNING_UNDUH_ENGINE = 3;
    private static final String ENCRYPTION_SECRET_KEY = "Z!,vDKUPv;.Jy0Q4Eq1wVCY-a_!GnT";
    public static final int $stable = 8;

    private CommonCons() {
    }

    public final String getAPPLICATION_STORAGE() {
        return APPLICATION_STORAGE;
    }

    public final String getDEFAULT_URL() {
        return DEFAULT_URL;
    }

    public final String getABSOLUTE_PATH() {
        return ABSOLUTE_PATH;
    }

    public final String getWEB_FOLDER() {
        return WEB_FOLDER;
    }

    public final String getWEB_INDEX() {
        return WEB_INDEX;
    }

    public final String getASSIGNMENT_FOLDER_TEMP() {
        return ASSIGNMENT_FOLDER_TEMP;
    }

    public final String getPATH_ANSWER() {
        return PATH_ANSWER;
    }

    public final int getNOTIF_ID() {
        return NOTIF_ID;
    }

    public final int getUPLOAD_MULTIPART_BUFFER_SIZE() {
        return UPLOAD_MULTIPART_BUFFER_SIZE;
    }

    public final String getSURVEY_TITLE() {
        return SURVEY_TITLE;
    }

    public final String getEXTENSION_LOG() {
        return EXTENSION_LOG;
    }

    public final void setEXTENSION_LOG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        EXTENSION_LOG = str;
    }

    public final String getEXTENSION_ZIP() {
        return EXTENSION_ZIP;
    }

    public final String getEXTENSION_7ZIP() {
        return EXTENSION_7ZIP;
    }

    public final String getEXTENSION_JSON() {
        return EXTENSION_JSON;
    }

    public final String getEXTENSION_TXT() {
        return EXTENSION_TXT;
    }

    public final String getSESSION_ID() {
        return SESSION_ID;
    }

    public final String getSESSION_DEVICE_ID() {
        return SESSION_DEVICE_ID;
    }

    public final String getSESSION_USERNAME() {
        return SESSION_USERNAME;
    }

    public final String getSESSION_ROLE_ID() {
        return SESSION_ROLE_ID;
    }

    public final String getSESSION_NIK() {
        return SESSION_NIK;
    }

    public final String getSESSION_REGENCY_ID() {
        return SESSION_REGENCY_ID;
    }

    public final String getSESSION_AUTH() {
        return SESSION_AUTH;
    }

    public final String getSESSION_EXPIRED() {
        return SESSION_EXPIRED;
    }

    public final String getSESSION_REFRESH_EXPIRED() {
        return SESSION_REFRESH_EXPIRED;
    }

    public final String getSESSION_FIRST_NAME() {
        return SESSION_FIRST_NAME;
    }

    public final String getSESSION_LAST_NAME() {
        return SESSION_LAST_NAME;
    }

    public final String getSESSION_PHONE() {
        return SESSION_PHONE;
    }

    public final String getSESSION_EMAIL() {
        return SESSION_EMAIL;
    }

    public final String getSESSION_ACCESS_TOKEN() {
        return SESSION_ACCESS_TOKEN;
    }

    public final String getSESSION_REFRESH_TOKEN() {
        return SESSION_REFRESH_TOKEN;
    }

    public final String getSESSION_TOKEN_TYPE() {
        return SESSION_TOKEN_TYPE;
    }

    public final String getSESSION_SERVER_URL() {
        return SESSION_SERVER_URL;
    }

    public final String getSESSION_LIST_GROUP() {
        return SESSION_LIST_GROUP;
    }

    public final String getSESSION_STATE() {
        return SESSION_STATE;
    }

    public final float getDISTANCE_LIVE_TRACKING() {
        return DISTANCE_LIVE_TRACKING;
    }

    public final long getTIME_LIVE_TRACKING() {
        return TIME_LIVE_TRACKING;
    }

    public final int getASSIGNMENT_STATUS_OPEN() {
        return ASSIGNMENT_STATUS_OPEN;
    }

    public final int getASSIGNMENT_STATUS_SUBMITED() {
        return ASSIGNMENT_STATUS_SUBMITED;
    }

    public final int getASSIGNMENT_STATUS_APPROVED() {
        return ASSIGNMENT_STATUS_APPROVED;
    }

    public final int getASSIGNMENT_STATUS_REJECTED() {
        return ASSIGNMENT_STATUS_REJECTED;
    }

    public final int getASSIGNMENT_STATUS_PENDING() {
        return ASSIGNMENT_STATUS_PENDING;
    }

    public final int getERROR_CODE_GENERAL() {
        return ERROR_CODE_GENERAL;
    }

    public final String getTAG() {
        return TAG;
    }

    public final String getSIGNATUREPATH() {
        return SIGNATUREPATH;
    }

    public final JSONObject getJson() {
        return json;
    }

    public final void setJson(JSONObject jSONObject) {
        json = jSONObject;
    }

    public final String getKEY_ASSIGNMENT_ID() {
        return KEY_ASSIGNMENT_ID;
    }

    public final String getPREDEFINED_DATA_ASSIGNMENT() {
        return PREDEFINED_DATA_ASSIGNMENT;
    }

    public final String getKEY_SURVEY_ID() {
        return KEY_SURVEY_ID;
    }

    public final String getKEY_SURVEY_PARENT_ID() {
        return KEY_SURVEY_PARENT_ID;
    }

    public final String getKEY_PERIODE_ID() {
        return KEY_PERIODE_ID;
    }

    public final String getKEY_TOKEN_ENABLE() {
        return KEY_TOKEN_ENABLE;
    }

    public final String getKEY_VIEW_LEVEL_ID() {
        return KEY_VIEW_LEVEL_ID;
    }

    public final String getFRAME_CATEGORY_ID() {
        return FRAME_CATEGORY_ID;
    }

    public final String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public final String getSURVEY_NAME() {
        return SURVEY_NAME;
    }

    public final String getKEY_ASSIGNMENT_STATUS() {
        return KEY_ASSIGNMENT_STATUS;
    }

    public final String getKEY_IS_PENCACAH() {
        return KEY_IS_PENCACAH;
    }

    public final String getKEY_PANEL_TYPE() {
        return KEY_PANEL_TYPE;
    }

    public final String getKEY_IS_KOFAX() {
        return KEY_IS_KOFAX;
    }

    public final String getKEY_SAVE_DATA() {
        return KEY_SAVE_DATA;
    }

    public final String getKEY_LATITUDE() {
        return KEY_LATITUDE;
    }

    public final String getKEY_LONGITUDE() {
        return KEY_LONGITUDE;
    }

    public final String getKEY_ACCURACY() {
        return KEY_ACCURACY;
    }

    public final String getKEY_CAN_ADD_SAMPLE() {
        return KEY_CAN_ADD_SAMPLE;
    }

    public final String getKEY_IS_UPDATE_LISTING() {
        return KEY_IS_UPDATE_LISTING;
    }

    public final String getKEY_FORM_ENGINE_ID() {
        return KEY_FORM_ENGINE_ID;
    }

    public final String getKEY_FORM_DATA_KEY() {
        return KEY_FORM_DATA_KEY;
    }

    public final String getKEY_PERIODE_ID_ONLY() {
        return KEY_PERIODE_ID_ONLY;
    }

    public final String getKEY_MODE() {
        return KEY_MODE;
    }

    public final String getKEY_OFFLINE_MODE() {
        return KEY_OFFLINE_MODE;
    }

    public final String getKEY_ONLINE_MODE() {
        return KEY_ONLINE_MODE;
    }

    public final String getKEY_ANSWER_PATH() {
        return KEY_ANSWER_PATH;
    }

    public final String getKEY_SAMPLING_ID() {
        return KEY_SAMPLING_ID;
    }

    public final String getKEY_SAMPLING_REGION_ID() {
        return KEY_SAMPLING_REGION_ID;
    }

    public final String getKEY_REGION_ID() {
        return KEY_REGION_ID;
    }

    public final String getKEY_REGION_FULL_CODE() {
        return KEY_REGION_FULL_CODE;
    }

    public final String getKEY_REGION_NAME() {
        return KEY_REGION_NAME;
    }

    public final String getKEY_LIST_ID_ASSIGNMENT() {
        return KEY_LIST_ID_ASSIGNMENT;
    }

    public final String getKEY_ID_LOCATION_HISTORY() {
        return KEY_ID_LOCATION_HISTORY;
    }

    public final String getKEY_PATH_LOCATION_HISTORY() {
        return KEY_PATH_LOCATION_HISTORY;
    }

    public final int getCODE_LOCATION_HISTORY() {
        return CODE_LOCATION_HISTORY;
    }

    public final String getTAG_UPLOAD_ERROR_SURVEY() {
        return TAG_UPLOAD_ERROR_SURVEY;
    }

    public final String getTAG_UPLOAD_ERROR_PERIOD() {
        return TAG_UPLOAD_ERROR_PERIOD;
    }

    public final String getTAG_UPLOAD_ERROR_BLOCK_SENSUS() {
        return TAG_UPLOAD_ERROR_BLOCK_SENSUS;
    }

    public final String getTAG_UPLOAD_ERROR_HOUSE_HOLD_NAME() {
        return TAG_UPLOAD_ERROR_HOUSE_HOLD_NAME;
    }

    public final String getFLAG() {
        return FLAG;
    }

    public final String getFORM_ENGINE_NAME() {
        return FORM_ENGINE_NAME;
    }

    public final String getFORM_ENGINE_ID() {
        return FORM_ENGINE_ID;
    }

    public final String getFORM_ENGINE_URL() {
        return FORM_ENGINE_URL;
    }

    public final String getACTION_LOG_OPEN() {
        return ACTION_LOG_OPEN;
    }

    public final String getACTION_LOG_CLOSED() {
        return ACTION_LOG_CLOSED;
    }

    public final String getACTION_LOG_SUBMIT() {
        return ACTION_LOG_SUBMIT;
    }

    public final String getACTION_LOG_APPROVE() {
        return ACTION_LOG_APPROVE;
    }

    public final String getACTION_LOG_REJECT() {
        return ACTION_LOG_REJECT;
    }

    public final String getACTION_LOG_REVOKE() {
        return ACTION_LOG_REVOKE;
    }

    public final String getACTION_LOG_CHANGE_MODE() {
        return ACTION_LOG_CHANGE_MODE;
    }

    public final String getACTION_LOG_EDIT() {
        return ACTION_LOG_EDIT;
    }

    public final String getACTION_LOG_GET_LOCATION() {
        return ACTION_LOG_GET_LOCATION;
    }

    public final String getLIST_UNDUH_CHANGE_MODE_DOWNLOAD() {
        return LIST_UNDUH_CHANGE_MODE_DOWNLOAD;
    }

    public final int getCOUNT_PARTIAL_REQUEST_ANSWER() {
        return COUNT_PARTIAL_REQUEST_ANSWER;
    }

    public final int getCOUNT_PARTIAL_REQUEST_ANSWER_S3() {
        return COUNT_PARTIAL_REQUEST_ANSWER_S3;
    }

    public final String getWARNING_KATEGORI() {
        return WARNING_KATEGORI;
    }

    public final int getWARNING_PERTAMA_AKSES() {
        return WARNING_PERTAMA_AKSES;
    }

    public final int getWARNING_TIDAK_ADA_DATA() {
        return WARNING_TIDAK_ADA_DATA;
    }

    public final int getWARNING_UNDUH_ENGINE() {
        return WARNING_UNDUH_ENGINE;
    }

    public final String getENCRYPTION_SECRET_KEY() {
        return ENCRYPTION_SECRET_KEY;
    }
}

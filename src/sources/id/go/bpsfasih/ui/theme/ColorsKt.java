package id.go.bpsfasih.ui.theme;

import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: Colors.kt */
@Metadata(d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0003\bÎ\u0001\"\u0016\u0010\u0000\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0005\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0006\u0010\u0003\"\u0016\u0010\u0007\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\b\u0010\u0003\"\u0016\u0010\t\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\f\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u000e\u0010\u0003\"\u0016\u0010\u000f\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\u0003\"\u0016\u0010\u0011\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0012\u0010\u0003\"\u0016\u0010\u0013\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0014\u0010\u0003\"\u0016\u0010\u0015\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0016\u0010\u0003\"\u0016\u0010\u0017\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0018\u0010\u0003\"\u0016\u0010\u0019\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u001a\u0010\u0003\"\u0016\u0010\u001b\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u001c\u0010\u0003\"\u0016\u0010\u001d\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u001e\u0010\u0003\"\u0016\u0010\u001f\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b \u0010\u0003\"\u0016\u0010!\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\"\u0010\u0003\"\u0016\u0010#\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b$\u0010\u0003\"\u0016\u0010%\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b&\u0010\u0003\"\u0016\u0010'\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b(\u0010\u0003\"\u0016\u0010)\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b*\u0010\u0003\"\u0016\u0010+\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b,\u0010\u0003\"\u0016\u0010-\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b.\u0010\u0003\"\u0016\u0010/\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b0\u0010\u0003\"\u0016\u00101\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b2\u0010\u0003\"\u0016\u00103\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b4\u0010\u0003\"\u0016\u00105\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b6\u0010\u0003\"\u0016\u00107\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b8\u0010\u0003\"\u0016\u00109\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b:\u0010\u0003\"\u0016\u0010;\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b<\u0010\u0003\"\u0016\u0010=\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b>\u0010\u0003\"\u0016\u0010?\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b@\u0010\u0003\"\u0016\u0010A\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bB\u0010\u0003\"\u0016\u0010C\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bD\u0010\u0003\"\u0016\u0010E\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bF\u0010\u0003\"\u0016\u0010G\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bH\u0010\u0003\"\u0016\u0010I\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bJ\u0010\u0003\"\u0016\u0010K\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bL\u0010\u0003\"\u0016\u0010M\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bN\u0010\u0003\"\u0016\u0010O\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bP\u0010\u0003\"\u0016\u0010Q\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bR\u0010\u0003\"\u0016\u0010S\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bT\u0010\u0003\"\u0016\u0010U\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bV\u0010\u0003\"\u0016\u0010W\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bX\u0010\u0003\"\u0016\u0010Y\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bZ\u0010\u0003\"\u0016\u0010[\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\\\u0010\u0003\"\u0016\u0010]\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b^\u0010\u0003\"\u0016\u0010_\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b`\u0010\u0003\"\u0016\u0010a\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bb\u0010\u0003\"\u0016\u0010c\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bd\u0010\u0003\"\u0016\u0010e\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bf\u0010\u0003\"\u0016\u0010g\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bh\u0010\u0003\"\u0016\u0010i\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bj\u0010\u0003\"\u0016\u0010k\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bl\u0010\u0003\"\u0016\u0010m\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bn\u0010\u0003\"\u0016\u0010o\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bp\u0010\u0003\"\u0016\u0010q\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\br\u0010\u0003\"\u0016\u0010s\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bt\u0010\u0003\"\u0016\u0010u\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bv\u0010\u0003\"\u0016\u0010w\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bx\u0010\u0003\"\u0016\u0010y\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\bz\u0010\u0003\"\u0016\u0010{\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b|\u0010\u0003\"\u0016\u0010}\u001a\u00020\u0001ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b~\u0010\u0003\"\u0017\u0010\u007f\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0080\u0001\u0010\u0003\"\u0018\u0010\u0081\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0082\u0001\u0010\u0003\"\u0018\u0010\u0083\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0084\u0001\u0010\u0003\"\u0018\u0010\u0085\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0086\u0001\u0010\u0003\"\u0018\u0010\u0087\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0088\u0001\u0010\u0003\"\u0018\u0010\u0089\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u008a\u0001\u0010\u0003\"\u0018\u0010\u008b\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u008c\u0001\u0010\u0003\"\u0018\u0010\u008d\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u008e\u0001\u0010\u0003\"\u0018\u0010\u008f\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0090\u0001\u0010\u0003\"\u0018\u0010\u0091\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0092\u0001\u0010\u0003\"\u0018\u0010\u0093\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0094\u0001\u0010\u0003\"\u0018\u0010\u0095\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0096\u0001\u0010\u0003\"\u0018\u0010\u0097\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u0098\u0001\u0010\u0003\"\u0018\u0010\u0099\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u009a\u0001\u0010\u0003\"\u0018\u0010\u009b\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u009c\u0001\u0010\u0003\"\u0018\u0010\u009d\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b\u009e\u0001\u0010\u0003\"\u0018\u0010\u009f\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b \u0001\u0010\u0003\"\u0018\u0010¡\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¢\u0001\u0010\u0003\"\u0018\u0010£\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¤\u0001\u0010\u0003\"\u0018\u0010¥\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¦\u0001\u0010\u0003\"\u0018\u0010§\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¨\u0001\u0010\u0003\"\u0018\u0010©\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bª\u0001\u0010\u0003\"\u0018\u0010«\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¬\u0001\u0010\u0003\"\u0018\u0010\u00ad\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b®\u0001\u0010\u0003\"\u0018\u0010¯\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b°\u0001\u0010\u0003\"\u0018\u0010±\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b²\u0001\u0010\u0003\"\u0018\u0010³\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b´\u0001\u0010\u0003\"\u0018\u0010µ\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¶\u0001\u0010\u0003\"\u0018\u0010·\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¸\u0001\u0010\u0003\"\u0018\u0010¹\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bº\u0001\u0010\u0003\"\u0018\u0010»\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¼\u0001\u0010\u0003\"\u0018\u0010½\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\b¾\u0001\u0010\u0003\"\u0018\u0010¿\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÀ\u0001\u0010\u0003\"\u0018\u0010Á\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÂ\u0001\u0010\u0003\"\u0018\u0010Ã\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÄ\u0001\u0010\u0003\"\u0018\u0010Å\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÆ\u0001\u0010\u0003\"\u0018\u0010Ç\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÈ\u0001\u0010\u0003\"\u0018\u0010É\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÊ\u0001\u0010\u0003\"\u0018\u0010Ë\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÌ\u0001\u0010\u0003\"\u0018\u0010Í\u0001\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u000b\n\u0002\u0010\u0004\u001a\u0005\bÎ\u0001\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Ï\u0001"}, d2 = {"Abu", "Landroidx/compose/ui/graphics/Color;", "getAbu", "()J", "J", "BackgroundAssignment", "getBackgroundAssignment", "BackgroundColor", "getBackgroundColor", "Black", "getBlack", "BlackOverlay", "getBlackOverlay", "Blue300", "getBlue300", "Blue800", "getBlue800", "BlueCompleted", "getBlueCompleted", "BlueCompleted100", "getBlueCompleted100", "BlueCompleted200", "getBlueCompleted200", "BlueCompleted300", "getBlueCompleted300", "BlueCompleted400", "getBlueCompleted400", "BlueCompleted500", "getBlueCompleted500", "BlueGrey", "getBlueGrey", "ColorAccent", "getColorAccent", "ColorCyan", "getColorCyan", "ColorDivider", "getColorDivider", "ColorPrimary", "getColorPrimary", "ColorPrimaryDark", "getColorPrimaryDark", "ColorWhite", "getColorWhite", "ColorWhiteBackground", "getColorWhiteBackground", ExifInterface.TAG_DATETIME, "getDateTime", "Description", "getDescription", "Emphasis", "getEmphasis", "Error30", "getError30", "Error40", "getError40", "Error50", "getError50", "Error60", "getError60", "Error70", "getError70", "GreenApproved", "getGreenApproved", "GreenDark", "getGreenDark", "GreenLight", "getGreenLight", "Grey10", "getGrey10", "Grey100", "getGrey100", "Grey1000", "getGrey1000", "Grey20", "getGrey20", "Grey200", "getGrey200", "Grey3", "getGrey3", "Grey300", "getGrey300", "Grey40", "getGrey40", "Grey400", "getGrey400", "Grey5", "getGrey5", "Grey50", "getGrey50", "Grey500", "getGrey500", "Grey60", "getGrey60", "Grey600", "getGrey600", "Grey700", "getGrey700", "Grey80", "getGrey80", "Grey800", "getGrey800", "Grey90", "getGrey90", "Grey900", "getGrey900", "ItemName", "getItemName", "MainColor", "getMainColor", "Neural30", "getNeural30", "Neural40", "getNeural40", "Neural50", "getNeural50", "Neural60", "getNeural60", "Neural70", "getNeural70", "Orange", "getOrange", "OrangeEdit", "getOrangeEdit", "OrangeSubmitted", "getOrangeSubmitted", "OverlayDark10", "getOverlayDark10", "OverlayDark20", "getOverlayDark20", "OverlayDark30", "getOverlayDark30", "OverlayDark40", "getOverlayDark40", "OverlayDark50", "getOverlayDark50", "OverlayDark60", "getOverlayDark60", "OverlayDark70", "getOverlayDark70", "OverlayDark80", "getOverlayDark80", "OverlayDark90", "getOverlayDark90", "OverlayLight10", "getOverlayLight10", "OverlayLight20", "getOverlayLight20", "OverlayLight30", "getOverlayLight30", "OverlayLight40", "getOverlayLight40", "OverlayLight50", "getOverlayLight50", "OverlayLight60", "getOverlayLight60", "OverlayLight70", "getOverlayLight70", "OverlayLight80", "getOverlayLight80", "OverlayLight90", "getOverlayLight90", "PinkCompleted500", "getPinkCompleted500", "PlaceholderBg", "getPlaceholderBg", "Primary30", "getPrimary30", "Primary40", "getPrimary40", "Primary50", "getPrimary50", "Primary60", "getPrimary60", "Primary70", "getPrimary70", "PrimaryTextColor", "getPrimaryTextColor", "RedReject", "getRedReject", "SecondColor", "getSecondColor", "SecondaryTextColor", "getSecondaryTextColor", "Success30", "getSuccess30", "Success40", "getSuccess40", "Success50", "getSuccess50", "Success60", "getSuccess60", "Success70", "getSuccess70", "Transparent", "getTransparent", "Warning30", "getWarning30", "Warning40", "getWarning40", "Warning50", "getWarning50", "Warning60", "getWarning60", "Warning70", "getWarning70", "White", "getWhite", "app_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ColorsKt {
    private static final long BackgroundColor;
    private static final long Blue300;
    private static final long Blue800;
    private static final long GreenLight;
    private static final long Grey1000;
    private static final long Grey600;
    private static final long Grey700;
    private static final long Grey800;
    private static final long Grey900;
    private static final long MainColor;
    private static final long PrimaryTextColor;
    private static final long SecondColor;
    private static final long SecondaryTextColor;
    private static final long ColorPrimary = ColorKt.Color(4278216345L);
    private static final long ColorPrimaryDark = ColorKt.Color(4280119479L);
    private static final long ColorAccent = ColorKt.Color(4292345945L);
    private static final long Primary30 = ColorKt.Color(4278216345L);
    private static final long Primary40 = ColorKt.Color(4278225100L);
    private static final long Primary50 = ColorKt.Color(4278233855L);
    private static final long Primary60 = ColorKt.Color(4281580543L);
    private static final long Primary70 = ColorKt.Color(4284927231L);
    private static final long Neural30 = ColorKt.Color(4287732641L);
    private static final long Neural40 = ColorKt.Color(4289508536L);
    private static final long Neural50 = ColorKt.Color(4291349968L);
    private static final long Neural60 = ColorKt.Color(4293125863L);
    private static final long Neural70 = ColorKt.Color(4294111987L);
    private static final long Success30 = ColorKt.Color(4283204119L);
    private static final long Success40 = ColorKt.Color(4284919326L);
    private static final long Success50 = ColorKt.Color(4286568742L);
    private static final long Success60 = ColorKt.Color(4288274769L);
    private static final long Success70 = ColorKt.Color(4289915005L);
    private static final long Warning30 = ColorKt.Color(4288241920L);
    private static final long Warning40 = ColorKt.Color(4291592704L);
    private static final long Warning50 = ColorKt.Color(4294943232L);
    private static final long Warning60 = ColorKt.Color(4294948147L);
    private static final long Warning70 = ColorKt.Color(4294952806L);
    private static final long Error30 = ColorKt.Color(4287499019L);
    private static final long Error40 = ColorKt.Color(4290580239L);
    private static final long Error50 = ColorKt.Color(4293661459L);
    private static final long Error60 = ColorKt.Color(4293935682L);
    private static final long Error70 = ColorKt.Color(4293935682L);
    private static final long ColorWhite = ColorKt.Color(InternalZipConstants.ZIP_64_SIZE_LIMIT);
    private static final long White = ColorKt.Color(InternalZipConstants.ZIP_64_SIZE_LIMIT);
    private static final long Black = ColorKt.Color(4278190080L);
    private static final long BlackOverlay = ColorKt.Color(1711276032);
    private static final long ColorWhiteBackground = ColorKt.Color(4294638330L);
    private static final long ColorCyan = ColorKt.Color(4281578981L);
    private static final long ColorDivider = ColorKt.Color(872415231);
    private static final long Transparent = ColorKt.Color(0);
    private static final long Emphasis = ColorKt.Color(4281448167L);
    private static final long BlueGrey = ColorKt.Color(4284513675L);
    private static final long GreenDark = ColorKt.Color(4280717069L);
    private static final long Orange = ColorKt.Color(4294961360L);
    private static final long OrangeEdit = ColorKt.Color(4294936576L);
    private static final long OrangeSubmitted = ColorKt.Color(4293561602L);
    private static final long GreenApproved = ColorKt.Color(4281635911L);
    private static final long BlueCompleted500 = ColorKt.Color(4280233880L);
    private static final long PinkCompleted500 = ColorKt.Color(4292345945L);
    private static final long BlueCompleted400 = ColorKt.Color(4281680802L);
    private static final long BlueCompleted300 = ColorKt.Color(4283127980L);
    private static final long BlueCompleted200 = ColorKt.Color(4284640694L);
    private static final long BlueCompleted = ColorKt.Color(4280233880L);
    private static final long BlueCompleted100 = ColorKt.Color(4287600587L);
    private static final long ItemName = ColorKt.Color(4278979596L);
    private static final long Description = ColorKt.Color(4279900698L);
    private static final long Abu = ColorKt.Color(4286019447L);
    private static final long DateTime = ColorKt.Color(4286019447L);
    private static final long PlaceholderBg = ColorKt.Color(4291217094L);
    private static final long BackgroundAssignment = ColorKt.Color(4293585642L);
    private static final long RedReject = ColorKt.Color(4293871471L);
    private static final long OverlayDark10 = ColorKt.Color(436207616);
    private static final long OverlayDark20 = ColorKt.Color(855638016);
    private static final long OverlayDark30 = ColorKt.Color(1291845632);
    private static final long OverlayDark40 = ColorKt.Color(1711276032);
    private static final long OverlayDark50 = ColorKt.Color(2147483648L);
    private static final long OverlayDark60 = ColorKt.Color(2566914048L);
    private static final long OverlayDark70 = ColorKt.Color(3003121664L);
    private static final long OverlayDark80 = ColorKt.Color(3422552064L);
    private static final long OverlayDark90 = ColorKt.Color(3858759680L);
    private static final long OverlayLight10 = ColorKt.Color(452984831);
    private static final long OverlayLight20 = ColorKt.Color(872415231);
    private static final long OverlayLight30 = ColorKt.Color(1308622847);
    private static final long OverlayLight40 = ColorKt.Color(1728053247);
    private static final long OverlayLight50 = ColorKt.Color(2164260863L);
    private static final long OverlayLight60 = ColorKt.Color(2583691263L);
    private static final long OverlayLight70 = ColorKt.Color(3019898879L);
    private static final long OverlayLight80 = ColorKt.Color(3439329279L);
    private static final long OverlayLight90 = ColorKt.Color(3875536895L);
    private static final long Grey3 = ColorKt.Color(4294440951L);
    private static final long Grey5 = ColorKt.Color(4294111986L);
    private static final long Grey10 = ColorKt.Color(4293322470L);
    private static final long Grey20 = ColorKt.Color(4291611852L);
    private static final long Grey40 = ColorKt.Color(4288256409L);
    private static final long Grey60 = ColorKt.Color(4284900966L);
    private static final long Grey80 = ColorKt.Color(4281812815L);
    private static final long Grey90 = ColorKt.Color(4280693304L);
    private static final long Grey50 = ColorKt.Color(4294638330L);
    private static final long Grey100 = ColorKt.Color(4294309365L);
    private static final long Grey200 = ColorKt.Color(4293848814L);
    private static final long Grey300 = ColorKt.Color(4292927712L);
    private static final long Grey400 = ColorKt.Color(4290624957L);
    private static final long Grey500 = ColorKt.Color(4288585374L);

    static {
        long jColor = ColorKt.Color(4285887861L);
        Grey600 = jColor;
        Grey700 = ColorKt.Color(4284572001L);
        long jColor2 = ColorKt.Color(4282532418L);
        Grey800 = jColor2;
        Grey900 = ColorKt.Color(4280361249L);
        Grey1000 = ColorKt.Color(4279900698L);
        MainColor = ColorKt.Color(4281571839L);
        SecondColor = ColorKt.Color(4287468195L);
        BackgroundColor = ColorKt.Color(4294573311L);
        PrimaryTextColor = jColor2;
        SecondaryTextColor = jColor;
        Blue300 = ColorKt.Color(4284790262L);
        Blue800 = ColorKt.Color(4279592384L);
        GreenLight = ColorKt.Color(4286695300L);
    }

    public static final long getColorPrimary() {
        return ColorPrimary;
    }

    public static final long getColorPrimaryDark() {
        return ColorPrimaryDark;
    }

    public static final long getColorAccent() {
        return ColorAccent;
    }

    public static final long getPrimary30() {
        return Primary30;
    }

    public static final long getPrimary40() {
        return Primary40;
    }

    public static final long getPrimary50() {
        return Primary50;
    }

    public static final long getPrimary60() {
        return Primary60;
    }

    public static final long getPrimary70() {
        return Primary70;
    }

    public static final long getNeural30() {
        return Neural30;
    }

    public static final long getNeural40() {
        return Neural40;
    }

    public static final long getNeural50() {
        return Neural50;
    }

    public static final long getNeural60() {
        return Neural60;
    }

    public static final long getNeural70() {
        return Neural70;
    }

    public static final long getSuccess30() {
        return Success30;
    }

    public static final long getSuccess40() {
        return Success40;
    }

    public static final long getSuccess50() {
        return Success50;
    }

    public static final long getSuccess60() {
        return Success60;
    }

    public static final long getSuccess70() {
        return Success70;
    }

    public static final long getWarning30() {
        return Warning30;
    }

    public static final long getWarning40() {
        return Warning40;
    }

    public static final long getWarning50() {
        return Warning50;
    }

    public static final long getWarning60() {
        return Warning60;
    }

    public static final long getWarning70() {
        return Warning70;
    }

    public static final long getError30() {
        return Error30;
    }

    public static final long getError40() {
        return Error40;
    }

    public static final long getError50() {
        return Error50;
    }

    public static final long getError60() {
        return Error60;
    }

    public static final long getError70() {
        return Error70;
    }

    public static final long getColorWhite() {
        return ColorWhite;
    }

    public static final long getWhite() {
        return White;
    }

    public static final long getBlack() {
        return Black;
    }

    public static final long getBlackOverlay() {
        return BlackOverlay;
    }

    public static final long getColorWhiteBackground() {
        return ColorWhiteBackground;
    }

    public static final long getColorCyan() {
        return ColorCyan;
    }

    public static final long getColorDivider() {
        return ColorDivider;
    }

    public static final long getTransparent() {
        return Transparent;
    }

    public static final long getEmphasis() {
        return Emphasis;
    }

    public static final long getBlueGrey() {
        return BlueGrey;
    }

    public static final long getGreenDark() {
        return GreenDark;
    }

    public static final long getOrange() {
        return Orange;
    }

    public static final long getOrangeEdit() {
        return OrangeEdit;
    }

    public static final long getOrangeSubmitted() {
        return OrangeSubmitted;
    }

    public static final long getGreenApproved() {
        return GreenApproved;
    }

    public static final long getBlueCompleted500() {
        return BlueCompleted500;
    }

    public static final long getPinkCompleted500() {
        return PinkCompleted500;
    }

    public static final long getBlueCompleted400() {
        return BlueCompleted400;
    }

    public static final long getBlueCompleted300() {
        return BlueCompleted300;
    }

    public static final long getBlueCompleted200() {
        return BlueCompleted200;
    }

    public static final long getBlueCompleted() {
        return BlueCompleted;
    }

    public static final long getBlueCompleted100() {
        return BlueCompleted100;
    }

    public static final long getItemName() {
        return ItemName;
    }

    public static final long getDescription() {
        return Description;
    }

    public static final long getAbu() {
        return Abu;
    }

    public static final long getDateTime() {
        return DateTime;
    }

    public static final long getPlaceholderBg() {
        return PlaceholderBg;
    }

    public static final long getBackgroundAssignment() {
        return BackgroundAssignment;
    }

    public static final long getRedReject() {
        return RedReject;
    }

    public static final long getOverlayDark10() {
        return OverlayDark10;
    }

    public static final long getOverlayDark20() {
        return OverlayDark20;
    }

    public static final long getOverlayDark30() {
        return OverlayDark30;
    }

    public static final long getOverlayDark40() {
        return OverlayDark40;
    }

    public static final long getOverlayDark50() {
        return OverlayDark50;
    }

    public static final long getOverlayDark60() {
        return OverlayDark60;
    }

    public static final long getOverlayDark70() {
        return OverlayDark70;
    }

    public static final long getOverlayDark80() {
        return OverlayDark80;
    }

    public static final long getOverlayDark90() {
        return OverlayDark90;
    }

    public static final long getOverlayLight10() {
        return OverlayLight10;
    }

    public static final long getOverlayLight20() {
        return OverlayLight20;
    }

    public static final long getOverlayLight30() {
        return OverlayLight30;
    }

    public static final long getOverlayLight40() {
        return OverlayLight40;
    }

    public static final long getOverlayLight50() {
        return OverlayLight50;
    }

    public static final long getOverlayLight60() {
        return OverlayLight60;
    }

    public static final long getOverlayLight70() {
        return OverlayLight70;
    }

    public static final long getOverlayLight80() {
        return OverlayLight80;
    }

    public static final long getOverlayLight90() {
        return OverlayLight90;
    }

    public static final long getGrey3() {
        return Grey3;
    }

    public static final long getGrey5() {
        return Grey5;
    }

    public static final long getGrey10() {
        return Grey10;
    }

    public static final long getGrey20() {
        return Grey20;
    }

    public static final long getGrey40() {
        return Grey40;
    }

    public static final long getGrey60() {
        return Grey60;
    }

    public static final long getGrey80() {
        return Grey80;
    }

    public static final long getGrey90() {
        return Grey90;
    }

    public static final long getGrey50() {
        return Grey50;
    }

    public static final long getGrey100() {
        return Grey100;
    }

    public static final long getGrey200() {
        return Grey200;
    }

    public static final long getGrey300() {
        return Grey300;
    }

    public static final long getGrey400() {
        return Grey400;
    }

    public static final long getGrey500() {
        return Grey500;
    }

    public static final long getGrey600() {
        return Grey600;
    }

    public static final long getGrey700() {
        return Grey700;
    }

    public static final long getGrey800() {
        return Grey800;
    }

    public static final long getGrey900() {
        return Grey900;
    }

    public static final long getGrey1000() {
        return Grey1000;
    }

    public static final long getMainColor() {
        return MainColor;
    }

    public static final long getSecondColor() {
        return SecondColor;
    }

    public static final long getBackgroundColor() {
        return BackgroundColor;
    }

    public static final long getPrimaryTextColor() {
        return PrimaryTextColor;
    }

    public static final long getSecondaryTextColor() {
        return SecondaryTextColor;
    }

    public static final long getBlue300() {
        return Blue300;
    }

    public static final long getBlue800() {
        return Blue800;
    }

    public static final long getGreenLight() {
        return GreenLight;
    }
}

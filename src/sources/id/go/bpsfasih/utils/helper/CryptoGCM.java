package id.go.bpsfasih.utils.helper;

import android.util.Base64;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: CryptoGCM.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/CryptoGCM;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CryptoGCM {
    public static final int $stable = 0;
    private static final String AES_MODE = "AES/GCM/NoPadding";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    /* compiled from: CryptoGCM.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/utils/helper/CryptoGCM$Companion;", "", "()V", "AES_MODE", "", "IV_LENGTH", "", "TAG_LENGTH", "decrypt", "encrypted", DatabaseFileArchive.COLUMN_KEY, "", "encrypt", "plainText", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String encrypt(String plainText, byte[] key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
            Intrinsics.checkNotNullParameter(plainText, "plainText");
            Intrinsics.checkNotNullParameter(key, "key");
            Cipher cipher = Cipher.getInstance(CryptoGCM.AES_MODE);
            byte[] bArr = new byte[12];
            new SecureRandom().nextBytes(bArr);
            cipher.init(1, new SecretKeySpec(key, "AES"), new GCMParameterSpec(128, bArr));
            byte[] bytes = plainText.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] cipherText = cipher.doFinal(bytes);
            Intrinsics.checkNotNullExpressionValue(cipherText, "cipherText");
            String strEncodeToString = Base64.encodeToString(ArraysKt.plus(bArr, cipherText), 2);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(combined, Base64.NO_WRAP)");
            return strEncodeToString;
        }

        public final String decrypt(String encrypted, byte[] key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
            Intrinsics.checkNotNullParameter(encrypted, "encrypted");
            Intrinsics.checkNotNullParameter(key, "key");
            byte[] decoded = Base64.decode(encrypted, 2);
            Intrinsics.checkNotNullExpressionValue(decoded, "decoded");
            byte[] bArrCopyOfRange = ArraysKt.copyOfRange(decoded, 0, 12);
            byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(decoded, 12, decoded.length);
            Cipher cipher = Cipher.getInstance(CryptoGCM.AES_MODE);
            cipher.init(2, new SecretKeySpec(key, "AES"), new GCMParameterSpec(128, bArrCopyOfRange));
            byte[] plain = cipher.doFinal(bArrCopyOfRange2);
            Intrinsics.checkNotNullExpressionValue(plain, "plain");
            return new String(plain, Charsets.UTF_8);
        }
    }
}

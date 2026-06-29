package id.go.bpsfasih.utils.helper;

import android.util.Base64;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.Region;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AssignmentEncryptionHelper.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/utils/helper/AssignmentEncryptionHelper;", "", "()V", "TAG", "", "VALID_KEY_LENGTHS", "", "", "decodeWrappedDataKey", "", "wrappedDataKey", "decrypt", "cipherText", "encrypt", "plainText", "getWrappedDataKey", "assignment", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "regionId", "surveyPeriodeId", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentEncryptionHelper {
    private static final String TAG = "AssignmentEncryption";
    public static final AssignmentEncryptionHelper INSTANCE = new AssignmentEncryptionHelper();
    private static final Set<Integer> VALID_KEY_LENGTHS = SetsKt.setOf((Object[]) new Integer[]{16, 24, 32});
    public static final int $stable = 8;

    private AssignmentEncryptionHelper() {
    }

    public final byte[] decodeWrappedDataKey(String wrappedDataKey) {
        Intrinsics.checkNotNullParameter(wrappedDataKey, "wrappedDataKey");
        byte[] keyBytes = Base64.decode(wrappedDataKey, 0);
        if (!VALID_KEY_LENGTHS.contains(Integer.valueOf(keyBytes.length))) {
            throw new IllegalArgumentException(("Invalid AES key length from wrappedDatakey: " + keyBytes.length + " bytes").toString());
        }
        Intrinsics.checkNotNullExpressionValue(keyBytes, "keyBytes");
        return keyBytes;
    }

    public final String encrypt(String plainText, String wrappedDataKey) {
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        String str = wrappedDataKey;
        if (str == null || StringsKt.isBlank(str)) {
            String strEncrypt = new Kripto().encrypt(plainText, CommonCons.INSTANCE.getENCRYPTION_SECRET_KEY());
            if (strEncrypt != null) {
                return strEncrypt;
            }
            throw new IllegalStateException("Legacy encryption failed");
        }
        return CryptoGCM.INSTANCE.encrypt(plainText, decodeWrappedDataKey(wrappedDataKey));
    }

    public final String decrypt(String cipherText, String wrappedDataKey) {
        Intrinsics.checkNotNullParameter(cipherText, "cipherText");
        String str = wrappedDataKey;
        if (!(str == null || StringsKt.isBlank(str))) {
            try {
                return CryptoGCM.INSTANCE.decrypt(cipherText, decodeWrappedDataKey(wrappedDataKey));
            } catch (Exception e) {
                Log.w(TAG, "GCM decrypt failed, fallback to legacy decrypt", e);
            }
        }
        String strDecrypt = new Kripto().decrypt(CommonCons.INSTANCE.getENCRYPTION_SECRET_KEY(), cipherText);
        if (strDecrypt != null) {
            return strDecrypt;
        }
        throw new IllegalStateException("Legacy decryption failed");
    }

    public final String getWrappedDataKey(String regionId, String surveyPeriodeId) {
        return WrappedDataKeyStore.INSTANCE.get(surveyPeriodeId, regionId);
    }

    public final String getWrappedDataKey(AssignmentEntity assignment) {
        if (assignment == null) {
            return null;
        }
        Region region = assignment.getRegion();
        String id2 = region != null ? region.getId() : null;
        String periodeNotPrimary = assignment.getPeriodeNotPrimary();
        if (StringsKt.isBlank(periodeNotPrimary)) {
            periodeNotPrimary = StringsKt.substringBefore$default(assignment.getPeriodeId(), '/', (String) null, 2, (Object) null);
        }
        return getWrappedDataKey(id2, periodeNotPrimary);
    }
}

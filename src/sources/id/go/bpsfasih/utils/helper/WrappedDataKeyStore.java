package id.go.bpsfasih.utils.helper;

import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.stats.CodePackage;
import id.go.bpsfasih.FasihApp;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: WrappedDataKeyStore.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019H\u0002J$\u0010\u001b\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lid/go/bpsfasih/utils/helper/WrappedDataKeyStore;", "", "()V", "ANDROID_KEYSTORE", "", "IV_LENGTH", "", "KEY_ALIAS", "PREFIX", "PREF_NAME", "TAG_LENGTH", "TRANSFORMATION", "decrypt", "value", "deleteAll", "", "deleteByPeriode", "surveyPeriodeId", "encrypt", "entryKey", "regionId", "get", "getOrCreateSecretKey", "Ljavax/crypto/SecretKey;", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "save", "wrappedDataKey", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class WrappedDataKeyStore {
    public static final int $stable = 0;
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    public static final WrappedDataKeyStore INSTANCE = new WrappedDataKeyStore();
    private static final int IV_LENGTH = 12;
    private static final String KEY_ALIAS = "assignment_region_wrapped_data_key_store";
    private static final String PREFIX = "wrapped_data_key";
    private static final String PREF_NAME = "assignment_region_wrapped_data_keys";
    private static final int TAG_LENGTH = 128;
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";

    private WrappedDataKeyStore() {
    }

    private final SharedPreferences preferences() {
        return FasihApp.INSTANCE.getContext().getSharedPreferences(PREF_NAME, 0);
    }

    private final String entryKey(String surveyPeriodeId, String regionId) {
        return "wrapped_data_key::" + surveyPeriodeId + "::" + regionId;
    }

    private final SecretKey getOrCreateSecretKey() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, UnrecoverableEntryException, InvalidAlgorithmParameterException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        KeyStore.Entry entry = keyStore.getEntry(KEY_ALIAS, null);
        KeyStore.SecretKeyEntry secretKeyEntry = entry instanceof KeyStore.SecretKeyEntry ? (KeyStore.SecretKeyEntry) entry : null;
        SecretKey secretKey = secretKeyEntry != null ? secretKeyEntry.getSecretKey() : null;
        if (secretKey != null) {
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE);
        KeyGenParameterSpec keyGenParameterSpecBuild = new KeyGenParameterSpec.Builder(KEY_ALIAS, 3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(true).setKeySize(256).build();
        Intrinsics.checkNotNullExpressionValue(keyGenParameterSpecBuild, "Builder(\n            KEY…256)\n            .build()");
        keyGenerator.init(keyGenParameterSpecBuild);
        SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "keyGenerator.generateKey()");
        return secretKeyGenerateKey;
    }

    private final String encrypt(String value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(1, getOrCreateSecretKey());
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] encrypted = cipher.doFinal(bytes);
        byte[] iv = cipher.getIV();
        if (iv == null) {
            iv = new byte[0];
        }
        Intrinsics.checkNotNullExpressionValue(encrypted, "encrypted");
        String strEncodeToString = Base64.encodeToString(ArraysKt.plus(iv, encrypted), 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(iv + encrypted, Base64.NO_WRAP)");
        return strEncodeToString;
    }

    private final String decrypt(String value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] decoded = Base64.decode(value, 2);
        Intrinsics.checkNotNullExpressionValue(decoded, "decoded");
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(decoded, 0, 12);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(decoded, 12, decoded.length);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(2, getOrCreateSecretKey(), new GCMParameterSpec(128, bArrCopyOfRange));
        byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "cipher.doFinal(cipherText)");
        return new String(bArrDoFinal, Charsets.UTF_8);
    }

    public final void save(String surveyPeriodeId, String regionId, String wrappedDataKey) {
        String str = surveyPeriodeId;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        String str2 = regionId;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return;
        }
        String str3 = wrappedDataKey;
        if (str3 == null || StringsKt.isBlank(str3)) {
            return;
        }
        preferences().edit().putString(entryKey(surveyPeriodeId, regionId), encrypt(wrappedDataKey)).apply();
    }

    public final String get(String surveyPeriodeId, String regionId) {
        String string;
        String str = surveyPeriodeId;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String str2 = regionId;
        if ((str2 == null || StringsKt.isBlank(str2)) || (string = preferences().getString(entryKey(surveyPeriodeId, regionId), null)) == null) {
            return null;
        }
        try {
            return decrypt(string);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void deleteByPeriode(String surveyPeriodeId) {
        String str = surveyPeriodeId;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        String str2 = "wrapped_data_key::" + surveyPeriodeId + "::";
        SharedPreferences.Editor editorEdit = preferences().edit();
        Set<String> setKeySet = preferences().getAll().keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            String it = (String) obj;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (StringsKt.startsWith$default(it, str2, false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            editorEdit.remove((String) it2.next());
        }
        editorEdit.apply();
    }

    public final void deleteAll() {
        SharedPreferences.Editor editorEdit = preferences().edit();
        Set<String> setKeySet = preferences().getAll().keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            String it = (String) obj;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (StringsKt.startsWith$default(it, "wrapped_data_key::", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            editorEdit.remove((String) it2.next());
        }
        editorEdit.apply();
    }
}

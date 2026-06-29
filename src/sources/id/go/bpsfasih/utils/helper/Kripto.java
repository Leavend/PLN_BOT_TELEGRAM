package id.go.bpsfasih.utils.helper;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: Kripto.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tJ\u0018\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tJ\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/utils/helper/Kripto;", "", "()V", "iterations", "", "keyLength", "random", "Ljava/security/SecureRandom;", "decrypt", "", DatabaseFileArchive.COLUMN_KEY, "strToDecrypt", "encrypt", "strToEncrypt", "secret_key", "generateKey", "Ljavax/crypto/SecretKey;", "passphrase", "salt", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Kripto {
    public static final int $stable = 8;
    private final int iterations = 11000;
    private final int keyLength = 256;
    private final SecureRandom random = new SecureRandom();

    public final String encrypt(String strToEncrypt, String secret_key) {
        String str;
        Intrinsics.checkNotNullParameter(strToEncrypt, "strToEncrypt");
        Intrinsics.checkNotNullParameter(secret_key, "secret_key");
        Security.addProvider(new BouncyCastleProvider());
        try {
            byte[] bArr = new byte[32];
            this.random.nextBytes(bArr);
            SecretKey secretKeyGenerateKey = generateKey(secret_key, new String(bArr, Charsets.UTF_8));
            Charset charsetForName = Charset.forName("UTF8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
            byte[] bytes = strToEncrypt.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] bArr2 = new byte[16];
            this.random.nextBytes(bArr2);
            synchronized (Cipher.class) {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                cipher.init(1, secretKeyGenerateKey, new IvParameterSpec(bArr2));
                byte[] bArr3 = new byte[cipher.getOutputSize(bytes.length)];
                cipher.doFinal(bArr3, cipher.update(bytes, 0, bytes.length, bArr3, 0));
                byte[] bArrDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest(bArr3);
                byte[] bArrEncode = Base64.encode(bArr3);
                Intrinsics.checkNotNullExpressionValue(bArrEncode, "encode(cipherText)");
                String str2 = new String(bArrEncode, Charsets.UTF_8);
                byte[] bArrEncode2 = Base64.encode(bArrDigest);
                Intrinsics.checkNotNullExpressionValue(bArrEncode2, "encode(digest)");
                String str3 = new String(bArrEncode2, Charsets.UTF_8);
                byte[] bArrEncode3 = Base64.encode(bArr);
                Intrinsics.checkNotNullExpressionValue(bArrEncode3, "encode(salt)");
                String str4 = new String(bArrEncode3, Charsets.UTF_8);
                byte[] bArrEncode4 = Base64.encode(bArr2);
                Intrinsics.checkNotNullExpressionValue(bArrEncode4, "encode(iv)");
                str = str2 + "#" + str3 + "#" + str4 + "#" + new String(bArrEncode4, Charsets.UTF_8);
            }
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        } catch (BadPaddingException e4) {
            e4.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
            return null;
        } catch (ShortBufferException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    public final String decrypt(String key, String strToDecrypt) {
        List listSplit$default;
        byte[] bArrDecode;
        byte[] bytes;
        String string;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(strToDecrypt, "strToDecrypt");
        Security.addProvider(new BouncyCastleProvider());
        try {
            listSplit$default = StringsKt.split$default((CharSequence) strToDecrypt, new String[]{"#"}, false, 0, 6, (Object) null);
            bArrDecode = Base64.decode((String) listSplit$default.get(2));
            Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(concatedata[2])");
        } catch (UnsupportedEncodingException e) {
            e = e;
        } catch (InvalidKeyException e2) {
            e = e2;
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
        } catch (BadPaddingException e4) {
            e = e4;
        } catch (IllegalBlockSizeException e5) {
            e = e5;
        } catch (NoSuchPaddingException e6) {
            e = e6;
        } catch (ShortBufferException e7) {
            e = e7;
        }
        try {
            SecretKey secretKeyGenerateKey = generateKey(key, new String(bArrDecode, Charsets.UTF_8));
            byte[] bArrDecode2 = Base64.decode((String) listSplit$default.get(3));
            String str = (String) listSplit$default.get(0);
            int length = str.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            String string2 = str.subSequence(i, length + 1).toString();
            if (string2 != null) {
                Charset charsetForName = Charset.forName("UTF8");
                Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
                bytes = string2.getBytes(charsetForName);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            } else {
                bytes = null;
            }
            byte[] bArrDecode3 = Base64.decode(bytes);
            byte[] bArrEncode = Base64.encode(MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest(bArrDecode3));
            Intrinsics.checkNotNullExpressionValue(bArrEncode, "encode(digest)");
            if (!Intrinsics.areEqual(new String(bArrEncode, Charsets.UTF_8), listSplit$default.get(1))) {
                return "datakorup";
            }
            synchronized (Cipher.class) {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                cipher.init(2, secretKeyGenerateKey, new IvParameterSpec(bArrDecode2));
                byte[] bArr = new byte[cipher.getOutputSize(bArrDecode3.length)];
                cipher.doFinal(bArr, cipher.update(bArrDecode3, 0, bArrDecode3.length, bArr, 0));
                String str2 = new String(bArr, Charsets.UTF_8);
                int length2 = str2.length() - 1;
                int i2 = 0;
                boolean z3 = false;
                while (i2 <= length2) {
                    boolean z4 = Intrinsics.compare((int) str2.charAt(!z3 ? i2 : length2), 32) <= 0;
                    if (z3) {
                        if (!z4) {
                            break;
                        }
                        length2--;
                    } else if (z4) {
                        i2++;
                    } else {
                        z3 = true;
                    }
                }
                string = str2.subSequence(i2, length2 + 1).toString();
            }
            return string;
        } catch (UnsupportedEncodingException e8) {
            e = e8;
            Log.d(">>> 1", e.toString());
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e9) {
            e = e9;
            Log.d(">>> 4", e.toString());
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e10) {
            e = e10;
            Log.d(">>> 6", e.toString());
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e11) {
            e = e11;
            Log.d(">>> 3", e.toString());
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e12) {
            e = e12;
            Log.d(">>> 2", e.toString());
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e13) {
            e = e13;
            Log.d(">>> 5", e.toString());
            e.printStackTrace();
            return null;
        } catch (ShortBufferException e14) {
            e = e14;
            Log.d(">>> 7", e.toString());
            e.printStackTrace();
            return null;
        }
    }

    private final SecretKey generateKey(String passphrase, String salt) {
        char[] charArray = passphrase.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        byte[] bytes = salt.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return SecretKeyFactory.getInstance("PBEWITHSHA256AND256BITAES-CBC-BC").generateSecret(new PBEKeySpec(charArray, bytes, this.iterations, this.keyLength));
    }
}

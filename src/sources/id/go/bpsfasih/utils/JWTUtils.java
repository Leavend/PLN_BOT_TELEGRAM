package id.go.bpsfasih.utils;

import android.util.Base64;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.json.JSONObject;

/* compiled from: JWTUtils.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0005J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0002¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/utils/JWTUtils;", "", "()V", "decoded", "Ljava/util/HashMap;", "", "Lorg/json/JSONObject;", "Lkotlin/collections/HashMap;", "JWTEncoded", "getData", "s", "getJson", "strEncoded", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class JWTUtils {
    public static final int $stable = 0;

    public final HashMap<String, JSONObject> decoded(String JWTEncoded) throws Exception {
        Intrinsics.checkNotNullParameter(JWTEncoded, "JWTEncoded");
        HashMap<String, JSONObject> map = new HashMap<>();
        try {
            String[] strArr = (String[]) new Regex("\\.").split(JWTEncoded, 0).toArray(new String[0]);
            Log.d("JWT_DECODED", "Header: " + getJson(strArr[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(strArr[1]));
            map.put("header", new JSONObject(getJson(strArr[0])));
            map.put("payload", new JSONObject(getJson(strArr[1])));
        } catch (UnsupportedEncodingException unused) {
        }
        return map;
    }

    public final JSONObject getData(String s) throws Exception {
        Intrinsics.checkNotNullParameter(s, "s");
        return new JSONObject(String.valueOf(decoded(s).get("payload")));
    }

    private final String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] bArrDecode = Base64.decode(strEncoded, 8);
        Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(strEncoded, Base64.URL_SAFE)");
        Charset charsetForName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
        return new String(bArrDecode, charsetForName);
    }
}

package id.go.bpsfasih.ui.formGear;

import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.dao.AssigmentDAO;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import id.go.bpsfasih.data.local.entities.Region;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import id.go.bpsfasih.data.local.models.AssignmentSubmitS3Response;
import id.go.bpsfasih.data.local.models.BaseResponseDataUpload;
import id.go.bpsfasih.data.local.models.PresignedUrlsItem;
import id.go.bpsfasih.data.local.models.S3Entity;
import id.go.bpsfasih.data.local.models.S3Response;
import id.go.bpsfasih.data.local.models.TemplateValidasiVersionModel;
import id.go.bpsfasih.data.local.models.TemplateValidationResponse;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.local.pojo.Sync;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.data.local.repository.TemplateValidationRepository;
import id.go.bpsfasih.data.remote.dto.AssignmentAnswerResponse;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.data.repository.ConnectorRepositoryImpl;
import id.go.bpsfasih.data.repository.NotificationRepositoryImpl;
import id.go.bpsfasih.domain.models.ActionLog;
import id.go.bpsfasih.domain.models.Encryption;
import id.go.bpsfasih.domain.models.LocationHistory;
import id.go.bpsfasih.domain.models.ParadataModel;
import id.go.bpsfasih.domain.models.PhotoFasihForm;
import id.go.bpsfasih.domain.models.PhotoFormGear;
import id.go.bpsfasih.domain.models.UploadModel;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import id.go.bpsfasih.ui.formGear.StaticValue;
import id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity;
import id.go.bpsfasih.utils.CrashHandler;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.AssignmentEncryptionHelper;
import id.go.bpsfasih.utils.helper.BatteryInfoHelper;
import id.go.bpsfasih.utils.helper.CameraHelper;
import id.go.bpsfasih.utils.helper.DeviceInfoHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.FirebaseAnalitycHelper;
import id.go.bpsfasih.utils.helper.FormEngineHelper;
import id.go.bpsfasih.utils.helper.MemoryInfoHelper;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.helper.SignalInfoHelper;
import id.go.bpsfasih.utils.helper.SsoHelper;
import id.go.bpsfasih.utils.helper.StorageInfoHelper;
import id.go.bpsfasih.utils.helper.ZipHelper;
import id.go.bpsfasih.utils.helper.location.GetLocationHelper;
import id.go.bpsfasih.utils.helper.location.LocCallback;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import net.lingala.zip4j.util.InternalZipConstants;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.http.cookie.ClientCookie;
import org.bouncycastle.i18n.ErrorBundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: FormGearJavascriptInterface.kt */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u0000 \u00ad\u00012\u00020\u0001:\u0002\u00ad\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ4\u0010&\u001a\u00020'2\u0006\u0010&\u001a\u00020\u00052\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0005H\u0007J \u0010+\u001a\u00020'2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u000e\u0010/\u001a\u00020'2\u0006\u00100\u001a\u00020\u000fJ\u000e\u00101\u001a\u00020'2\u0006\u00100\u001a\u00020\u000fJ\u0018\u00102\u001a\u00020'2\b\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00020\u0005J\u0015\u00106\u001a\u00020'2\b\u00107\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u00108J\u0018\u00109\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u0005H\u0007J\b\u0010<\u001a\u00020\u000fH\u0002J\u0014\u0010=\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u00050>H\u0002J\b\u0010?\u001a\u00020@H\u0002J\u0013\u0010A\u001a\u0004\u0018\u00010BH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010CJ\b\u0010D\u001a\u00020\u0005H\u0002J2\u0010E\u001a\u00020'2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020G2\u0006\u0010J\u001a\u00020\u00052\b\b\u0002\u0010K\u001a\u00020\u0005H\u0002J\n\u0010L\u001a\u0004\u0018\u00010MH\u0002J(\u0010N\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u0005H\u0007J&\u0010R\u001a\u00020'2\u0006\u00100\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0005J \u0010S\u001a\u00020'2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010J\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u000e\u0010T\u001a\u00020'2\u0006\u0010U\u001a\u00020\u0005J\u0006\u0010V\u001a\u00020'J\n\u0010W\u001a\u0004\u0018\u00010XH\u0002J\u0006\u0010Y\u001a\u00020\u0005J\b\u0010Z\u001a\u0004\u0018\u00010[J\u0014\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00050]2\u0006\u0010U\u001a\u00020\u0005J\u0014\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00050]2\u0006\u0010U\u001a\u00020\u0005J\b\u0010_\u001a\u0004\u0018\u00010\u0005J\n\u0010`\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010a\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010b\u001a\u0004\u0018\u00010\u0005H\u0007J-\u0010\u0019\u001a\u00020'2#\u0010c\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\be\u0012\b\b:\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020'0dH\u0002J\n\u0010f\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010g\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010h\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010i\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010j\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010k\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010l\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010m\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010n\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010o\u001a\u0004\u0018\u00010\u0005H\u0007J\u0010\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020rH\u0002J!\u0010s\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00052\u0006\u0010t\u001a\u00020uH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010vJ\u0010\u0010w\u001a\u00020'2\u0006\u0010x\u001a\u00020yH\u0002J\u0010\u0010z\u001a\u00020\u000f2\b\u0010{\u001a\u0004\u0018\u00010\u0005J\u000e\u0010|\u001a\u00020\u00052\u0006\u00103\u001a\u000204J\u0010\u0010}\u001a\u00020'2\b\b\u0002\u0010~\u001a\u00020\u000fJ\u001a\u0010\u007f\u001a\u00020'2\u0007\u0010\u0080\u0001\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0002J\u0011\u0010\u0082\u0001\u001a\u00020'2\u0006\u00100\u001a\u00020\u000fH\u0002J\t\u0010\u0083\u0001\u001a\u00020'H\u0002J\u000f\u0010\u0084\u0001\u001a\u00020'2\u0006\u0010J\u001a\u00020\u0005J\u0018\u0010\u0085\u0001\u001a\u00020'2\u0007\u0010\u0086\u0001\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u0005J\t\u0010\u0087\u0001\u001a\u00020'H\u0002J&\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u0007\u0010\u008a\u0001\u001a\u00020\u000f2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0003\u0010\u008c\u0001J\u0007\u0010\u008d\u0001\u001a\u00020'J\u0007\u0010\u008e\u0001\u001a\u00020'J\u0011\u0010\u008f\u0001\u001a\u00020'2\u0006\u0010U\u001a\u00020\u0005H\u0007J`\u0010\u0090\u0001\u001a\u00020'2\u0006\u0010&\u001a\u00020\u00052\u000b\b\u0002\u0010\u0091\u0001\u001a\u0004\u0018\u00010G2\u000b\b\u0002\u0010\u0092\u0001\u001a\u0004\u0018\u00010G2\u000b\b\u0002\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u000f2 \b\u0002\u0010\u0094\u0001\u001a\u0019\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020'\u0018\u00010\u0095\u0001H\u0002¢\u0006\u0003\u0010\u0096\u0001J<\u0010\u0097\u0001\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00052\u0007\u0010\u0098\u0001\u001a\u00020\u00052\u0007\u0010\u0099\u0001\u001a\u00020\u00052\u0007\u0010\u009a\u0001\u001a\u00020\u0005H\u0007J*\u0010\u009b\u0001\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00052\u0007\u0010\u009a\u0001\u001a\u00020\u0005H\u0007J\u0011\u0010\u009c\u0001\u001a\u00020'2\u0006\u0010;\u001a\u00020\u0005H\u0007J&\u0010\u009d\u0001\u001a\u00020'2\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00052\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020'0dJ8\u0010\u009f\u0001\u001a\u00020'2\t\u0010 \u0001\u001a\u0004\u0018\u00010G2\t\u0010¡\u0001\u001a\u0004\u0018\u00010G2\t\u0010¢\u0001\u001a\u0004\u0018\u00010G2\b\u0010J\u001a\u0004\u0018\u00010\u0005¢\u0006\u0003\u0010£\u0001J\u0012\u0010¤\u0001\u001a\u00020'2\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0002J&\u0010¥\u0001\u001a\u00020'2\t\u0010¦\u0001\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0003\u0010§\u0001J3\u0010¨\u0001\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020\u00182 \b\u0002\u0010\u0094\u0001\u001a\u0019\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020'\u0018\u00010\u0095\u0001H\u0002J=\u0010©\u0001\u001a\u00020'2\n\u0010ª\u0001\u001a\u0005\u0018\u00010\u0089\u00012 \b\u0002\u0010\u0094\u0001\u001a\u0019\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020'\u0018\u00010\u0095\u0001H\u0002¢\u0006\u0003\u0010«\u0001J\u0011\u0010¬\u0001\u001a\u00020'2\u0006\u0010U\u001a\u00020\u0005H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010$\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006®\u0001"}, d2 = {"Lid/go/bpsfasih/ui/formGear/FormGearJavascriptInterface;", "", "_webView", "Landroid/webkit/WebView;", "_formEngineId", "", "activity", "Lid/go/bpsfasih/ui/formGear/FormGearActivity;", "(Landroid/webkit/WebView;Ljava/lang/String;Lid/go/bpsfasih/ui/formGear/FormGearActivity;)V", "coordinat", "Lorg/json/JSONObject;", "formEngineId", "formSessionStartTime", "", "isCameraGPS", "", "Ljava/lang/Boolean;", "keyOpenKamera", "mDataKey", "getMDataKey", "()Ljava/lang/String;", "setMDataKey", "(Ljava/lang/String;)V", "paradata", "Lid/go/bpsfasih/domain/models/ParadataModel;", "getParadata", "()Lid/go/bpsfasih/domain/models/ParadataModel;", "setParadata", "(Lid/go/bpsfasih/domain/models/ParadataModel;)V", "pendingShowTemporarySaveDialog", "timestampNow", "getTimestampNow", "()Ljava/lang/Long;", "setTimestampNow", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "userId", "webView", "action", "", DatabaseFileArchive.COLUMN_KEY, "data", "otherData", "appendExistingActionLogs", "actionLogEntities", "", "Lid/go/bpsfasih/domain/models/ActionLog;", "approval", "isApproval", "approveReject", "cameraResult", StorageChooser.FILE_PICKER, "Ljava/io/File;", "fileNameFormGear", "checkMock", "isMock", "(Ljava/lang/Boolean;)V", "connectorApi", "name", "param", "consumeTemporarySaveDialogFlag", "create7zFile", "Lkotlin/Pair;", "createArchiveRequest", "Lid/go/bpsfasih/ui/formGear/DataFormArchiveRequest;", "createAssignmentUploadEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentUploadEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createDefaultResponse", "createLocationHistory", FormGearActivity.LAT, "", "lng", "acc", "dataKey", NotificationCompat.CATEGORY_STATUS, "createUploadModel", "Lid/go/bpsfasih/domain/models/UploadModel;", "dispatchFinalResult", "remark", FormGearJavascriptInterface.PRINCIPAL_FILE, ErrorBundle.SUMMARY_ENTRY, "edit", "execute", "fileReload", "json", "finalize", "getCurrentAssignmentEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "getDataKey", "getDataTemplateValidationVersion", "Lid/go/bpsfasih/data/local/models/TemplateValidasiVersionModel;", "getFilenamesFromJsonFasihForm", "", "getFilenamesFromJsonFormGear", "getFormGearVersion", "getFormMode", "getIsNew", "getMedia", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "getPreset", "getPrincipalCollection", "getReference", "getRemark", "getResponse", "getRolePetugas", "getTemplate", "getUserName", "getUserRole", "getValidation", "getValuePrincipal", "jsonObject", "Lcom/google/gson/JsonObject;", "hitConnectorApiSuspend", "body", "Lokhttp3/RequestBody;", "(Ljava/lang/String;Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAssignmentEncrypt", "encryption", "Lid/go/bpsfasih/domain/models/Encryption;", "isJsonFormat", "str", "md5Base64OfFile", "mobileExit", "showTemporarySaveDialog", "notifyUploadMediaError", "isShow", "message", "onlineEdit", "onlineSubmit", "requestLocationFasihForm", "requestLocationWithDialog", "needCamera", "resetTemporarySaveDialogFlag", "resolveParadataEncryptionType", "", "isEncrypt", "wrappedDataKey", "(ZLjava/lang/String;)Ljava/lang/Integer;", "revoke", "revokeProcess", "s3refreshUrl", "saveLogParadata", "latitudeParam", "longitudeParam", "isMockParam", "onComplete", "Lkotlin/Function2;", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function2;)V", "saveOrSubmit", FormGearJavascriptInterface.REFERENCE_FILE, FormGearJavascriptInterface.MEDIA_FILE, "flag", "saveOrSubmitFasihForm", "sendEvent", "setPrincipalData", "dataPrincipal", "setResultLocation", "latitude", "longitude", "accuracy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;)V", "showShortToast", "submit", "resume", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "updateParadata", "updateParadataEncryptionType", "encryptionType", "(Ljava/lang/Integer;Lkotlin/jvm/functions/Function2;)V", "uploadMedia", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FormGearJavascriptInterface {
    public static final String DATA_FILE = "data";
    public static final String LOCATION_HISTORY = "location_histories";
    public static final String MEDIA_FILE = "media";
    public static final String NOTE_FILE = "note";
    private static final int PARADATA_ENCRYPTION_TYPE_WRAPPED_KEY = 2;
    public static final String PRINCIPAL_FILE = "principal";
    public static final String REFERENCE_FILE = "reference";
    public static final String STATUS_LOCATION_DIRECT = "DIRECT";
    public static final String STATUS_LOCATION_MAP = "MAPS";
    public static final String TAG_HANDLER = "Android";
    private static final String TAG_LOCATION = "FormGearLocation";
    private FormGearActivity activity;
    private JSONObject coordinat;
    private String formEngineId;
    private long formSessionStartTime;
    private Boolean isCameraGPS;
    private String keyOpenKamera;
    private String mDataKey;
    private ParadataModel paradata;
    private boolean pendingShowTemporarySaveDialog;
    private Long timestampNow;
    private String userId;
    private WebView webView;
    public static final int $stable = 8;

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface", f = "FormGearJavascriptInterface.kt", i = {0, 1, 1}, l = {2530, 2533}, m = "createAssignmentUploadEntity", n = {"this", "this", "data"}, s = {"L$0", "L$0", "L$1"})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$createAssignmentUploadEntity$1, reason: invalid class name and case insensitive filesystem */
    static final class C08751 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C08751(Continuation<? super C08751> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FormGearJavascriptInterface.this.createAssignmentUploadEntity(this);
        }
    }

    public FormGearJavascriptInterface(WebView _webView, String _formEngineId, FormGearActivity activity) {
        Intrinsics.checkNotNullParameter(_webView, "_webView");
        Intrinsics.checkNotNullParameter(_formEngineId, "_formEngineId");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.webView = _webView;
        this.activity = activity;
        this.formEngineId = _formEngineId;
        this.formSessionStartTime = System.currentTimeMillis();
        this.userId = FasihApp.INSTANCE.getSession().getUserId();
        this.keyOpenKamera = "";
        this.mDataKey = "";
    }

    public final ParadataModel getParadata() {
        return this.paradata;
    }

    public final void setParadata(ParadataModel paradataModel) {
        this.paradata = paradataModel;
    }

    public final Long getTimestampNow() {
        return this.timestampNow;
    }

    public final void setTimestampNow(Long l) {
        this.timestampNow = l;
    }

    public final String getMDataKey() {
        return this.mDataKey;
    }

    public final void setMDataKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mDataKey = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetTemporarySaveDialogFlag() {
        this.pendingShowTemporarySaveDialog = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean consumeTemporarySaveDialogFlag() {
        boolean z = this.pendingShowTemporarySaveDialog;
        this.pendingShowTemporarySaveDialog = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showShortToast(final String message) {
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface.showShortToast$lambda$0(this.f$0, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showShortToast$lambda$0(FormGearJavascriptInterface this$0, String message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(message, "$message");
        Toast.makeText(this$0.activity, message, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyUploadMediaError(final boolean isShow, final String message) {
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface.notifyUploadMediaError$lambda$1(isShow, this, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyUploadMediaError$lambda$1(boolean z, FormGearJavascriptInterface this$0, String message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(message, "$message");
        if (z) {
            this$0.webView.evaluateJavascript("javascript:result('FILE_UPLOADED', '', 'false')", null);
        }
        Toast.makeText(this$0.activity, message, 0).show();
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getCurrentAssignmentEntity$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getCurrentAssignmentEntity$1, reason: invalid class name and case insensitive filesystem */
    static final class C08791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
        int label;

        C08791(Continuation<? super C08791> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08791(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
            return ((C08791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                String userId = FasihApp.INSTANCE.getSession().getUserId();
                this.label = 1;
                obj = assignmentRepository.getAssignmentByIdPrimary(mAssignmentId + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return ((Sync) obj).getAssignment();
        }
    }

    private final AssignmentEntity getCurrentAssignmentEntity() {
        return (AssignmentEntity) BuildersKt__BuildersKt.runBlocking$default(null, new C08791(null), 1, null);
    }

    private final Integer resolveParadataEncryptionType(boolean isEncrypt, String wrappedDataKey) {
        if (isEncrypt) {
            String str = wrappedDataKey;
            if (!(str == null || StringsKt.isBlank(str))) {
                return 2;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void updateParadataEncryptionType$default(FormGearJavascriptInterface formGearJavascriptInterface, Integer num, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        formGearJavascriptInterface.updateParadataEncryptionType(num, function2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateParadataEncryptionType(final Integer encryptionType, final Function2<? super Boolean, ? super String, Unit> onComplete) {
        getParadata(new Function1<ParadataModel, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.updateParadataEncryptionType.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ParadataModel paradataModel) {
                invoke2(paradataModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ParadataModel paradataModel) {
                ParadataModel paradataModel2;
                if (paradataModel == null || (paradataModel2 = paradataModel.copy((511 & 1) != 0 ? paradataModel.timestampEntities : null, (511 & 2) != 0 ? paradataModel.formgear_version : null, (511 & 4) != 0 ? paradataModel.deviceInfo : null, (511 & 8) != 0 ? paradataModel.data : null, (511 & 16) != 0 ? paradataModel.signalInfo : null, (511 & 32) != 0 ? paradataModel.memoryInfo : null, (511 & 64) != 0 ? paradataModel.storageInfo : null, (511 & 128) != 0 ? paradataModel.actionLogEntities : null, (511 & 256) != 0 ? paradataModel.totalDuration : null, (511 & 512) != 0 ? paradataModel.encryptionType : encryptionType)) == null) {
                    paradataModel2 = new ParadataModel(null, null, null, null, null, null, null, null, null, encryptionType);
                }
                this.updateParadata(paradataModel2, onComplete);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void appendExistingActionLogs(List<ActionLog> actionLogEntities, ParadataModel paradata) {
        List<ActionLog> actionLogEntities2 = paradata != null ? paradata.getActionLogEntities() : null;
        if (actionLogEntities2 == null) {
            actionLogEntities2 = CollectionsKt.emptyList();
        }
        actionLogEntities.addAll(actionLogEntities2);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getUserName$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getUserName$1, reason: invalid class name and case insensitive filesystem */
    static final class C08821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08821(Continuation<? super C08821> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08821(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FormGearJavascriptInterface.saveLogParadata$default(FormGearJavascriptInterface.this, CommonCons.INSTANCE.getACTION_LOG_OPEN(), null, null, null, null, 30, null);
            return Unit.INSTANCE;
        }
    }

    @JavascriptInterface
    public final String getUserName() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08821(null), 2, null);
        return FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME());
    }

    @JavascriptInterface
    public final String getIsNew() {
        return ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearJavascriptInterface$getIsNew$isNew$1(this, null), 1, null)).booleanValue() ? "1" : ExifInterface.GPS_MEASUREMENT_2D;
    }

    @JavascriptInterface
    public final String getFormMode() {
        Integer num = (Integer) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearJavascriptInterface$getFormMode$status$1(this, null), 1, null);
        if (this.activity.getIsEdit()) {
            return "4";
        }
        if ((num != null && num.intValue() == 0) || (num != null && num.intValue() == 3)) {
            UserRole role = this.activity.getRole();
            if (role != null ? Intrinsics.areEqual((Object) role.isPencacah(), (Object) true) : false) {
                return "1";
            }
        } else if (num != null && num.intValue() == 1) {
            UserRole role2 = this.activity.getRole();
            if (!(role2 != null ? Intrinsics.areEqual((Object) role2.isPencacah(), (Object) true) : false)) {
                return ExifInterface.GPS_MEASUREMENT_2D;
            }
        } else if (num != null) {
            num.intValue();
        }
        return ExifInterface.GPS_MEASUREMENT_3D;
    }

    @JavascriptInterface
    public final String getTemplate() {
        String text;
        try {
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(new File(FileHelper.INSTANCE.pathTemplate(this.activity.getTemplateId()))), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
            } finally {
            }
        } catch (IOException unused) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.getTemplate$lambda$4(this.f$0);
                }
            });
            text = "";
        }
        if (text != null) {
            return text;
        }
        Intrinsics.throwUninitializedPropertyAccessException("jsonString");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getTemplate$lambda$4(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Anda belum memiliki file template, silahkan melakukan sinkronisasi lagi untuk mendapatkan file template terbaru", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda45
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getTemplate$lambda$4$lambda$3(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getTemplate$lambda$4$lambda$3(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    @JavascriptInterface
    public final String getValidation() {
        try {
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(new File(FileHelper.INSTANCE.pathValidation(this.activity.getTemplateId()))), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                return text;
            } finally {
            }
        } catch (IOException unused) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda33
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.getValidation$lambda$7(this.f$0);
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getValidation$lambda$7(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Anda belum memiliki file validasi, silahkan melakukan sinkronisasi lagi untuk mendapatkan file validasi terbaru", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getValidation$lambda$7$lambda$6(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getValidation$lambda$7$lambda$6(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    @JavascriptInterface
    public final String getPreset() {
        try {
            AssignmentEntity assignment = ((Sync) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearJavascriptInterface$getPreset$assignment$1(this, null), 1, null)).getAssignment();
            Intrinsics.checkNotNull(assignment);
            return String.valueOf(assignment.getPreDefinedData());
        } catch (IOException unused) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.getPreset$lambda$9(this.f$0);
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getPreset$lambda$9(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Terdapat kesalahan pada preset", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda50
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getPreset$lambda$9$lambda$8(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getPreset$lambda$9$lambda$8(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    @JavascriptInterface
    public final String getResponse() {
        try {
            if (new File(this.activity.getAnswerPath() + "/data.json").exists()) {
                AssignmentEntity currentAssignmentEntity = getCurrentAssignmentEntity();
                String string = FileHelper.INSTANCE.readFile(this.activity.getAnswerPath() + "/data.json").toString();
                Intrinsics.checkNotNullExpressionValue(string, "FileHelper.readFile(acti…A_FILE}.json\").toString()");
                if (isJsonFormat(string)) {
                    return string;
                }
                String strDecrypt = AssignmentEncryptionHelper.INSTANCE.decrypt(string, AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(currentAssignmentEntity));
                String str = strDecrypt;
                if (!(str == null || str.length() == 0) && isJsonFormat(strDecrypt)) {
                    return strDecrypt;
                }
                if (this.activity.getIsEdit()) {
                    this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda44
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface.getResponse$lambda$10(this.f$0);
                        }
                    });
                    return null;
                }
                this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda46
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.getResponse$lambda$12(this.f$0);
                    }
                });
                return null;
            }
            return createDefaultResponse();
        } catch (IOException unused) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda47
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.getResponse$lambda$14(this.f$0);
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getResponse$lambda$10(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.showProgressBar();
        AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
        String mAssignmentId = this$0.activity.getMAssignmentId();
        if (mAssignmentId == null) {
            mAssignmentId = "";
        }
        assignmentRepositoryImpl.getAssignmentAnswer(mAssignmentId, new Function1<AssignmentAnswerResponse, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getResponse$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentAnswerResponse assignmentAnswerResponse) throws IOException {
                invoke2(assignmentAnswerResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentAnswerResponse assignmentAnswerResponse) throws IOException {
                this.this$0.activity.hideProgressBar();
                FileHelper.Companion companion = FileHelper.INSTANCE;
                String answerPath = this.this$0.activity.getAnswerPath();
                String str = "data" + CommonCons.INSTANCE.getEXTENSION_JSON();
                Intrinsics.checkNotNull(assignmentAnswerResponse);
                String string = assignmentAnswerResponse.getData().toString();
                Long lValueOf = Long.valueOf(System.currentTimeMillis());
                final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                companion.saveAssignmentFile(answerPath, str, string, lValueOf, false, (32 & 32) != 0 ? null : null, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getResponse$1$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        formGearJavascriptInterface.activity.loadContent();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getResponse$lambda$12(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Terdapat kesalahan ketika proses decrypt data. Laporkan melalui fitur Bantuan", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getResponse$lambda$12$lambda$11(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getResponse$lambda$12$lambda$11(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getResponse$lambda$14(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Terdapat kesalahan pada file data", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getResponse$lambda$14$lambda$13(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getResponse$lambda$14$lambda$13(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    private final String createDefaultResponse() throws JSONException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date());
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("description", "");
        jSONObject.put("dataKey", "");
        jSONObject.put("answers", new JSONArray());
        jSONObject.put("createdAt", str);
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "JSONObject().apply {\n   …dAt)\n        }.toString()");
        return string;
    }

    @JavascriptInterface
    public final String getMedia() throws IOException {
        try {
            if (new File(this.activity.getAnswerPath() + "/media.json").exists()) {
                return FileHelper.INSTANCE.readFile(this.activity.getAnswerPath() + "/media.json").toString();
            }
            InputStream inputStreamOpen = this.activity.getAssets().open("client/formgear/media.json");
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "activity.assets.open(\"client/formgear/media.json\")");
            Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                return text;
            } finally {
            }
        } catch (IOException unused) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.getMedia$lambda$19(this.f$0);
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getMedia$lambda$19(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Terdapat kesalahan pada file data", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda36
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getMedia$lambda$19$lambda$18(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getMedia$lambda$19$lambda$18(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    @JavascriptInterface
    public final String getRemark() {
        try {
            Sync sync = (Sync) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearJavascriptInterface$getRemark$assignment$1(this, null), 1, null);
            AssignmentEntity assignment = sync.getAssignment();
            Intrinsics.checkNotNull(assignment);
            String.valueOf(assignment.getComment());
            AssignmentEntity assignment2 = sync.getAssignment();
            Intrinsics.checkNotNull(assignment2);
            return String.valueOf(assignment2.getComment());
        } catch (IOException unused) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda37
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.getRemark$lambda$21(this.f$0);
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getRemark$lambda$21(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Terdapat kesalahan pada data comment", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getRemark$lambda$21$lambda$20(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getRemark$lambda$21$lambda$20(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    @JavascriptInterface
    public final String getReference() throws IOException {
        try {
            File file = new File(this.activity.getAnswerPath() + "/reference.json");
            File file2 = new File(this.activity.getAnswerPath() + "/reference.7z");
            File file3 = new File(this.activity.getAnswerPath() + "/reference.zip");
            if (file2.exists()) {
                ZipHelper.Companion companion = ZipHelper.INSTANCE;
                String path = file2.getPath();
                Intrinsics.checkNotNullExpressionValue(path, "file7z.path");
                companion.extractZip(path, this.activity.getAnswerPath());
                FilesKt.deleteRecursively(file2);
            }
            if (file3.exists()) {
                ZipHelper.Companion.unZip$default(ZipHelper.INSTANCE, this.activity.getAnswerPath(), "reference.zip", null, 4, null);
                FilesKt.deleteRecursively(file3);
            }
            if (file.exists()) {
                return FileHelper.INSTANCE.readFile(this.activity.getAnswerPath() + "/reference.json").toString();
            }
            InputStream inputStreamOpen = this.activity.getAssets().open("client/formgear/reference.json");
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "activity.assets.open(\"cl…formgear/reference.json\")");
            Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                return text;
            } finally {
            }
        } catch (IOException unused) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.getReference$lambda$24(this.f$0);
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getReference$lambda$24(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0.activity, "Kesalahan", "Terdapat kesalahan pada file reference", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda49
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.getReference$lambda$24$lambda$23(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getReference$lambda$24$lambda$23(FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JavascriptInterface
    public final String getPrincipalCollection() throws InterruptedException {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "{}";
        BuildersKt__BuildersKt.runBlocking$default(null, new C08811(objectRef, new JSONArray(), null), 1, null);
        return (String) objectRef.element;
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getPrincipalCollection$1", f = "FormGearJavascriptInterface.kt", i = {0}, l = {498}, m = "invokeSuspend", n = {"repo"}, s = {"L$0"})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getPrincipalCollection$1, reason: invalid class name and case insensitive filesystem */
    static final class C08811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ JSONArray $jsonArrayData;
        final /* synthetic */ Ref.ObjectRef<String> $jsonString;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08811(Ref.ObjectRef<String> objectRef, JSONArray jSONArray, Continuation<? super C08811> continuation) {
            super(2, continuation);
            this.$jsonString = objectRef;
            this.$jsonArrayData = jSONArray;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08811(this.$jsonString, this.$jsonArrayData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [T, id.go.bpsfasih.data.local.repository.AssignmentRepository] */
        /* JADX WARN: Type inference failed for: r7v9, types: [T, java.lang.String] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException {
            Ref.ObjectRef objectRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
                String mAssignmentId = formGearActivity != null ? formGearActivity.getMAssignmentId() : null;
                Intrinsics.checkNotNull(mAssignmentId);
                this.L$0 = objectRef2;
                this.label = 1;
                Object assignmentOnly = assignmentRepository.getAssignmentOnly(mAssignmentId, this);
                if (assignmentOnly == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                obj = assignmentOnly;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            AssignmentEntity assignmentEntity = (AssignmentEntity) obj;
            if (assignmentEntity == null) {
                return null;
            }
            Ref.ObjectRef<String> objectRef3 = this.$jsonString;
            JSONArray jSONArray = this.$jsonArrayData;
            AssignmentRepository assignmentRepository2 = (AssignmentRepository) objectRef.element;
            Region region = assignmentEntity.getRegion();
            Intrinsics.checkNotNull(region);
            String id2 = region.getId();
            Intrinsics.checkNotNull(id2);
            for (AssignmentEntity assignmentEntity2 : assignmentRepository2.getAssignmentsByPeriodeRegionId(id2, assignmentEntity.getPeriodeNotPrimary())) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("data1", assignmentEntity2.getData1());
                jSONObject.put("data2", assignmentEntity2.getData2());
                jSONObject.put("data3", assignmentEntity2.getData3());
                jSONObject.put("data4", assignmentEntity2.getData4());
                jSONObject.put("data5", assignmentEntity2.getData5());
                jSONObject.put("data6", assignmentEntity2.getData6());
                jSONObject.put("data7", assignmentEntity2.getData7());
                jSONObject.put("data8", assignmentEntity2.getData8());
                jSONObject.put("data9", assignmentEntity2.getData9());
                jSONObject.put("data10", assignmentEntity2.getData10());
                jSONArray.put(jSONObject);
            }
            objectRef3.element = jSONArray.toString();
            return Unit.INSTANCE;
        }
    }

    @JavascriptInterface
    public final void saveOrSubmit(String data, final String remark, final String principal, final String reference, final String media, final String flag) throws IOException {
        String wrappedDataKey;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(principal, "principal");
        Intrinsics.checkNotNullParameter(reference, "reference");
        Intrinsics.checkNotNullParameter(media, "media");
        Intrinsics.checkNotNullParameter(flag, "flag");
        this.timestampNow = Long.valueOf(System.currentTimeMillis());
        AssignmentEntity currentAssignmentEntity = getCurrentAssignmentEntity();
        boolean z = currentAssignmentEntity != null && currentAssignmentEntity.isEncrypt();
        if (z) {
            wrappedDataKey = AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(currentAssignmentEntity);
        } else {
            wrappedDataKey = null;
        }
        String str = wrappedDataKey;
        final Integer numResolveParadataEncryptionType = resolveParadataEncryptionType(z, str);
        FileHelper.INSTANCE.saveAssignmentFile(this.activity.getAnswerPath(), "data" + CommonCons.INSTANCE.getEXTENSION_JSON(), data, this.timestampNow, z, str, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmit.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws IOException {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z2) throws IOException {
                if (!z2) {
                    FormGearJavascriptInterface.this.resetTemporarySaveDialogFlag();
                    return;
                }
                FileHelper.Companion companion = FileHelper.INSTANCE;
                String answerPath = FormGearJavascriptInterface.this.activity.getAnswerPath();
                String str2 = FormGearJavascriptInterface.REFERENCE_FILE + CommonCons.INSTANCE.getEXTENSION_JSON();
                String str3 = reference;
                Long timestampNow = FormGearJavascriptInterface.this.getTimestampNow();
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                final String str4 = media;
                final String str5 = principal;
                final String str6 = remark;
                final Integer num = numResolveParadataEncryptionType;
                final String str7 = flag;
                companion.saveAssignmentFile(answerPath, str2, str3, timestampNow, false, (32 & 32) != 0 ? null : null, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmit.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws IOException {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z3) throws IOException {
                        if (!z3) {
                            formGearJavascriptInterface.resetTemporarySaveDialogFlag();
                            return;
                        }
                        FileHelper.Companion companion2 = FileHelper.INSTANCE;
                        String answerPath2 = formGearJavascriptInterface.activity.getAnswerPath();
                        String str8 = FormGearJavascriptInterface.MEDIA_FILE + CommonCons.INSTANCE.getEXTENSION_JSON();
                        String str9 = str4;
                        Long timestampNow2 = formGearJavascriptInterface.getTimestampNow();
                        final FormGearJavascriptInterface formGearJavascriptInterface2 = formGearJavascriptInterface;
                        final String str10 = str5;
                        final String str11 = str6;
                        final Integer num2 = num;
                        final String str12 = str7;
                        companion2.saveAssignmentFile(answerPath2, str8, str9, timestampNow2, false, (32 & 32) != 0 ? null : null, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmit.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws IOException {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z4) throws IOException {
                                if (!z4) {
                                    formGearJavascriptInterface2.resetTemporarySaveDialogFlag();
                                    return;
                                }
                                FileHelper.Companion companion3 = FileHelper.INSTANCE;
                                String answerPath3 = formGearJavascriptInterface2.activity.getAnswerPath();
                                String str13 = FormGearJavascriptInterface.PRINCIPAL_FILE + CommonCons.INSTANCE.getEXTENSION_JSON();
                                String str14 = str10;
                                Long timestampNow3 = formGearJavascriptInterface2.getTimestampNow();
                                final FormGearJavascriptInterface formGearJavascriptInterface3 = formGearJavascriptInterface2;
                                final String str15 = str10;
                                final String str16 = str11;
                                final Integer num3 = num2;
                                final String str17 = str12;
                                companion3.saveAssignmentFile(answerPath3, str13, str14, timestampNow3, false, (32 & 32) != 0 ? null : null, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmit.1.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                        invoke(bool.booleanValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(boolean z5) {
                                        if (!z5) {
                                            formGearJavascriptInterface3.resetTemporarySaveDialogFlag();
                                            return;
                                        }
                                        FormGearJavascriptInterface formGearJavascriptInterface4 = formGearJavascriptInterface3;
                                        String str18 = str15;
                                        final FormGearJavascriptInterface formGearJavascriptInterface5 = formGearJavascriptInterface3;
                                        final String str19 = str16;
                                        final Integer num4 = num3;
                                        final String str20 = str17;
                                        formGearJavascriptInterface4.setPrincipalData(str18, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmit.1.1.1.1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                                invoke(bool.booleanValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(boolean z6) {
                                                if (!z6) {
                                                    formGearJavascriptInterface5.resetTemporarySaveDialogFlag();
                                                    return;
                                                }
                                                DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateComment(formGearJavascriptInterface5.activity.getMAssignmentId() + InternalZipConstants.ZIP_FILE_SEPARATOR + FasihApp.INSTANCE.getSession().getUserId(), str19);
                                                formGearJavascriptInterface5.updateParadataEncryptionType(num4, new C02281(formGearJavascriptInterface5, str20));
                                            }

                                            /* compiled from: FormGearJavascriptInterface.kt */
                                            @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "isSuccess", "", "errorMessage", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                            /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                            static final class C02281 extends Lambda implements Function2<Boolean, String, Unit> {
                                                final /* synthetic */ String $flag;
                                                final /* synthetic */ FormGearJavascriptInterface this$0;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                C02281(FormGearJavascriptInterface formGearJavascriptInterface, String str) {
                                                    super(2);
                                                    this.this$0 = formGearJavascriptInterface;
                                                    this.$flag = str;
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                                                    invoke(bool.booleanValue(), str);
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(boolean z, final String str) {
                                                    if (!z) {
                                                        FormGearActivity formGearActivity = this.this$0.activity;
                                                        final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                                        formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$$ExternalSyntheticLambda0
                                                            @Override // java.lang.Runnable
                                                            public final void run() {
                                                                FormGearJavascriptInterface.C08911.C02241.C02251.C02261.C02271.C02281.invoke$lambda$0(formGearJavascriptInterface, str);
                                                            }
                                                        });
                                                        this.this$0.resetTemporarySaveDialogFlag();
                                                        return;
                                                    }
                                                    if (!this.$flag.equals(StaticValue.Actions.FLAG_SUBMIT)) {
                                                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass3(this.this$0, null), 2, null);
                                                        FormGearActivity formGearActivity2 = this.this$0.activity;
                                                        final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
                                                        formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$$ExternalSyntheticLambda1
                                                            @Override // java.lang.Runnable
                                                            public final void run() {
                                                                FormGearJavascriptInterface.C08911.C02241.C02251.C02261.C02271.C02281.invoke$lambda$1(formGearJavascriptInterface2);
                                                            }
                                                        });
                                                        return;
                                                    }
                                                    FormGearJavascriptInterface.saveLogParadata$default(this.this$0, CommonCons.INSTANCE.getACTION_LOG_SUBMIT(), null, null, null, new AnonymousClass2(this.this$0), 14, null);
                                                }

                                                /* JADX INFO: Access modifiers changed from: private */
                                                public static final void invoke$lambda$0(FormGearJavascriptInterface this$0, String str) {
                                                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                                                    FormGearActivity formGearActivity = this$0.activity;
                                                    if (str == null) {
                                                        str = "Gagal menyimpan paradata";
                                                    }
                                                    Toast.makeText(formGearActivity, str, 0).show();
                                                }

                                                /* compiled from: FormGearJavascriptInterface.kt */
                                                @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "isSuccessLog", "", "errorMessageLog", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                                /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$2, reason: invalid class name */
                                                static final class AnonymousClass2 extends Lambda implements Function2<Boolean, String, Unit> {
                                                    final /* synthetic */ FormGearJavascriptInterface this$0;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass2(FormGearJavascriptInterface formGearJavascriptInterface) {
                                                        super(2);
                                                        this.this$0 = formGearJavascriptInterface;
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                                                        invoke(bool.booleanValue(), str);
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(boolean z, final String str) {
                                                        if (!z) {
                                                            FormGearActivity formGearActivity = this.this$0.activity;
                                                            final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                                            formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$2$$ExternalSyntheticLambda0
                                                                @Override // java.lang.Runnable
                                                                public final void run() {
                                                                    FormGearJavascriptInterface.C08911.C02241.C02251.C02261.C02271.C02281.AnonymousClass2.invoke$lambda$0(formGearJavascriptInterface, str);
                                                                }
                                                            });
                                                            this.this$0.resetTemporarySaveDialogFlag();
                                                            return;
                                                        }
                                                        if (!SsoHelper.INSTANCE.checkExpSession()) {
                                                            FormGearActivity formGearActivity2 = this.this$0.activity;
                                                            final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
                                                            formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$2$$ExternalSyntheticLambda1
                                                                @Override // java.lang.Runnable
                                                                public final void run() {
                                                                    FormGearJavascriptInterface.C08911.C02241.C02251.C02261.C02271.C02281.AnonymousClass2.invoke$lambda$1(formGearJavascriptInterface2);
                                                                }
                                                            });
                                                            return;
                                                        }
                                                        FormGearJavascriptInterface.submit$default(this.this$0, null, null, 2, null);
                                                    }

                                                    /* JADX INFO: Access modifiers changed from: private */
                                                    public static final void invoke$lambda$0(FormGearJavascriptInterface this$0, String str) {
                                                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                                                        FormGearActivity formGearActivity = this$0.activity;
                                                        if (str == null) {
                                                            str = "Gagal menyimpan paradata";
                                                        }
                                                        Toast.makeText(formGearActivity, str, 0).show();
                                                    }

                                                    /* JADX INFO: Access modifiers changed from: private */
                                                    public static final void invoke$lambda$1(final FormGearJavascriptInterface this$0) {
                                                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                                                        this$0.activity.showProgressBar();
                                                        SsoHelper.INSTANCE.requestRefreshToken(this$0.activity, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$2$2$1
                                                            {
                                                                super(1);
                                                            }

                                                            @Override // kotlin.jvm.functions.Function1
                                                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                                                invoke(bool.booleanValue());
                                                                return Unit.INSTANCE;
                                                            }

                                                            public final void invoke(boolean z) {
                                                                if (!z) {
                                                                    this$0.activity.hideProgressBar();
                                                                    this$0.activity.showAlertDialogKodeVerifikasiLogout();
                                                                } else {
                                                                    FormGearJavascriptInterface.submit$default(this$0, null, null, 2, null);
                                                                }
                                                            }
                                                        });
                                                    }
                                                }

                                                /* compiled from: FormGearJavascriptInterface.kt */
                                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                                @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$3", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                                /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmit$1$1$1$1$1$1$3, reason: invalid class name */
                                                static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                    int label;
                                                    final /* synthetic */ FormGearJavascriptInterface this$0;

                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    AnonymousClass3(FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super AnonymousClass3> continuation) {
                                                        super(2, continuation);
                                                        this.this$0 = formGearJavascriptInterface;
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                        return new AnonymousClass3(this.this$0, continuation);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                        return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                    }

                                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                    public final Object invokeSuspend(Object obj) {
                                                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                        if (this.label != 0) {
                                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                        }
                                                        ResultKt.throwOnFailure(obj);
                                                        AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                                                        String mAssignmentId = this.this$0.activity.getMAssignmentId();
                                                        Intrinsics.checkNotNull(mAssignmentId);
                                                        assignmentRepository.updateIsEncrypt(mAssignmentId);
                                                        return Unit.INSTANCE;
                                                    }
                                                }

                                                /* JADX INFO: Access modifiers changed from: private */
                                                public static final void invoke$lambda$1(FormGearJavascriptInterface this$0) {
                                                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                                                    if (this$0.consumeTemporarySaveDialogFlag()) {
                                                        this$0.activity.showTemporarySaveSuccessDialog();
                                                    } else {
                                                        Toast.makeText(this$0.activity, "Sukses proses simpan", 0).show();
                                                    }
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    @JavascriptInterface
    public final void saveOrSubmitFasihForm(String data, final String remark, final String principal, final String flag) {
        String wrappedDataKey;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(principal, "principal");
        Intrinsics.checkNotNullParameter(flag, "flag");
        if (!this.activity.getIsEdit()) {
            try {
                AssignmentEntity currentAssignmentEntity = getCurrentAssignmentEntity();
                boolean z = currentAssignmentEntity != null && currentAssignmentEntity.isEncrypt();
                if (z) {
                    wrappedDataKey = AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(currentAssignmentEntity);
                } else {
                    wrappedDataKey = null;
                }
                String str = wrappedDataKey;
                final Integer numResolveParadataEncryptionType = resolveParadataEncryptionType(z, str);
                this.timestampNow = Long.valueOf(System.currentTimeMillis());
                FileHelper.INSTANCE.saveAssignmentFile(this.activity.getAnswerPath(), "data" + CommonCons.INSTANCE.getEXTENSION_JSON(), data, this.timestampNow, z, str, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmitFasihForm.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws IOException {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2) throws IOException {
                        if (!z2) {
                            FormGearJavascriptInterface.this.resetTemporarySaveDialogFlag();
                            FormGearJavascriptInterface.this.showShortToast("Gagal menyimpan file jawaban");
                            return;
                        }
                        FileHelper.Companion companion = FileHelper.INSTANCE;
                        String answerPath = FormGearJavascriptInterface.this.activity.getAnswerPath();
                        String str2 = FormGearJavascriptInterface.PRINCIPAL_FILE + CommonCons.INSTANCE.getEXTENSION_JSON();
                        String str3 = principal;
                        Long timestampNow = FormGearJavascriptInterface.this.getTimestampNow();
                        final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                        final String str4 = principal;
                        final String str5 = remark;
                        final Integer num = numResolveParadataEncryptionType;
                        final String str6 = flag;
                        companion.saveAssignmentFile(answerPath, str2, str3, timestampNow, false, (32 & 32) != 0 ? null : null, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmitFasihForm.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z3) {
                                if (!z3) {
                                    formGearJavascriptInterface.resetTemporarySaveDialogFlag();
                                    formGearJavascriptInterface.showShortToast("Gagal menyimpan file principal");
                                    return;
                                }
                                FormGearJavascriptInterface formGearJavascriptInterface2 = formGearJavascriptInterface;
                                String str7 = str4;
                                final FormGearJavascriptInterface formGearJavascriptInterface3 = formGearJavascriptInterface;
                                final String str8 = str5;
                                final Integer num2 = num;
                                final String str9 = str6;
                                formGearJavascriptInterface2.setPrincipalData(str7, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveOrSubmitFasihForm.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                        invoke(bool.booleanValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(boolean z4) {
                                        if (!z4) {
                                            formGearJavascriptInterface3.resetTemporarySaveDialogFlag();
                                            formGearJavascriptInterface3.showShortToast("Gagal menyimpan data principal");
                                            return;
                                        }
                                        try {
                                            DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateComment(formGearJavascriptInterface3.activity.getMAssignmentId() + InternalZipConstants.ZIP_FILE_SEPARATOR + FasihApp.INSTANCE.getSession().getUserId(), str8);
                                            formGearJavascriptInterface3.updateParadataEncryptionType(num2, new C02311(formGearJavascriptInterface3, str9));
                                        } catch (Exception e) {
                                            formGearJavascriptInterface3.resetTemporarySaveDialogFlag();
                                            FormGearJavascriptInterface formGearJavascriptInterface4 = formGearJavascriptInterface3;
                                            String message = e.getMessage();
                                            if (message == null) {
                                                message = "Gagal menyimpan catatan assignment";
                                            }
                                            formGearJavascriptInterface4.showShortToast(message);
                                        }
                                    }

                                    /* compiled from: FormGearJavascriptInterface.kt */
                                    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "isSuccess", "", "errorMessage", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmitFasihForm$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                    static final class C02311 extends Lambda implements Function2<Boolean, String, Unit> {
                                        final /* synthetic */ String $flag;
                                        final /* synthetic */ FormGearJavascriptInterface this$0;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        C02311(FormGearJavascriptInterface formGearJavascriptInterface, String str) {
                                            super(2);
                                            this.this$0 = formGearJavascriptInterface;
                                            this.$flag = str;
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                                            invoke(bool.booleanValue(), str);
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(boolean z, String str) {
                                            if (!z) {
                                                this.this$0.resetTemporarySaveDialogFlag();
                                                FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                                if (str == null) {
                                                    str = "Gagal menyimpan paradata";
                                                }
                                                formGearJavascriptInterface.showShortToast(str);
                                                return;
                                            }
                                            if (!this.$flag.equals(StaticValue.Actions.FLAG_SUBMIT)) {
                                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass2(this.this$0, null), 2, null);
                                                FormGearActivity formGearActivity = this.this$0.activity;
                                                final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
                                                formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmitFasihForm$1$1$1$1$$ExternalSyntheticLambda0
                                                    @Override // java.lang.Runnable
                                                    public final void run() {
                                                        FormGearJavascriptInterface.C08921.C02291.C02301.C02311.invoke$lambda$0(formGearJavascriptInterface2);
                                                    }
                                                });
                                                return;
                                            }
                                            FormGearJavascriptInterface.saveLogParadata$default(this.this$0, CommonCons.INSTANCE.getACTION_LOG_SUBMIT(), null, null, null, new C02321(this.this$0), 14, null);
                                        }

                                        /* compiled from: FormGearJavascriptInterface.kt */
                                        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "isSuccessLog", "", "errorMessageLog", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmitFasihForm$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                        static final class C02321 extends Lambda implements Function2<Boolean, String, Unit> {
                                            final /* synthetic */ FormGearJavascriptInterface this$0;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            C02321(FormGearJavascriptInterface formGearJavascriptInterface) {
                                                super(2);
                                                this.this$0 = formGearJavascriptInterface;
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                                                invoke(bool.booleanValue(), str);
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(boolean z, String str) {
                                                if (!z) {
                                                    this.this$0.resetTemporarySaveDialogFlag();
                                                    FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                                    if (str == null) {
                                                        str = "Gagal menyimpan paradata";
                                                    }
                                                    formGearJavascriptInterface.showShortToast(str);
                                                    return;
                                                }
                                                if (!SsoHelper.INSTANCE.checkExpSession()) {
                                                    FormGearActivity formGearActivity = this.this$0.activity;
                                                    final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
                                                    formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmitFasihForm$1$1$1$1$1$$ExternalSyntheticLambda0
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            FormGearJavascriptInterface.C08921.C02291.C02301.C02311.C02321.invoke$lambda$0(formGearJavascriptInterface2);
                                                        }
                                                    });
                                                    return;
                                                }
                                                FormGearJavascriptInterface.submit$default(this.this$0, null, null, 2, null);
                                            }

                                            /* JADX INFO: Access modifiers changed from: private */
                                            public static final void invoke$lambda$0(final FormGearJavascriptInterface this$0) {
                                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                                this$0.activity.showProgressBar();
                                                SsoHelper.INSTANCE.requestRefreshToken(this$0.activity, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmitFasihForm$1$1$1$1$1$1$1
                                                    {
                                                        super(1);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function1
                                                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                                        invoke(bool.booleanValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(boolean z) {
                                                        if (!z) {
                                                            this$0.activity.hideProgressBar();
                                                            this$0.activity.showAlertDialogKodeVerifikasiLogout();
                                                        } else {
                                                            FormGearJavascriptInterface.submit$default(this$0, null, null, 2, null);
                                                        }
                                                    }
                                                });
                                            }
                                        }

                                        /* compiled from: FormGearJavascriptInterface.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmitFasihForm$1$1$1$1$2", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveOrSubmitFasihForm$1$1$1$1$2, reason: invalid class name */
                                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            int label;
                                            final /* synthetic */ FormGearJavascriptInterface this$0;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass2(FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super AnonymousClass2> continuation) {
                                                super(2, continuation);
                                                this.this$0 = formGearJavascriptInterface;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass2(this.this$0, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object obj) {
                                                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                if (this.label != 0) {
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                ResultKt.throwOnFailure(obj);
                                                AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                                                String mAssignmentId = this.this$0.activity.getMAssignmentId();
                                                Intrinsics.checkNotNull(mAssignmentId);
                                                assignmentRepository.updateIsEncrypt(mAssignmentId);
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* JADX INFO: Access modifiers changed from: private */
                                        public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
                                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                                            if (this$0.consumeTemporarySaveDialogFlag()) {
                                                this$0.activity.showTemporarySaveSuccessDialog();
                                            } else {
                                                Toast.makeText(this$0.activity, "Sukses proses simpan", 0).show();
                                            }
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
                return;
            } catch (Exception e) {
                resetTemporarySaveDialogFlag();
                String message = e.getMessage();
                if (message == null) {
                    message = "Terjadi kesalahan saat menyimpan jawaban";
                }
                showShortToast(message);
                return;
            }
        }
        resetTemporarySaveDialogFlag();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JavascriptInterface
    public final void execute(String action, final String dataKey, String data) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(dataKey, "dataKey");
        Intrinsics.checkNotNullParameter(data, "data");
        switch (action.hashCode()) {
            case -1798725188:
                if (action.equals(StaticValue.Actions.FILE_RELOAD)) {
                    this.mDataKey = dataKey;
                    fileReload(data);
                    break;
                }
                break;
            case -1702679004:
                if (action.equals(StaticValue.Actions.FILE_UPLOAD)) {
                    this.mDataKey = dataKey;
                    uploadMedia(data);
                    break;
                }
                break;
            case -1611296843:
                if (action.equals("LOCATION")) {
                    FormGearActivity formGearActivity = this.activity;
                    Intrinsics.checkNotNull(formGearActivity);
                    if (ActivityCompat.checkSelfPermission(formGearActivity.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
                        FormGearActivity formGearActivity2 = this.activity;
                        Intrinsics.checkNotNull(formGearActivity2);
                        if (ActivityCompat.checkSelfPermission(formGearActivity2.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    FormGearJavascriptInterface.execute$lambda$25(this.f$0);
                                }
                            });
                            break;
                        }
                    }
                    this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface.execute$lambda$29(this.f$0, dataKey);
                        }
                    });
                    break;
                }
                break;
            case 2157948:
                if (action.equals(StaticValue.Actions.FILE)) {
                    this.keyOpenKamera = dataKey;
                    this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface.execute$lambda$33(dataKey, this);
                        }
                    });
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$25(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Location Permission tidak aktif, aktifkan Location Permission", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$29(final FormGearJavascriptInterface this$0, final String dataKey) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dataKey, "$dataKey");
        FormGearActivity formGearActivity = this$0.activity;
        if (formGearActivity != null) {
            BaseClassActivityNew.showAlertDialog$default(formGearActivity, "Location", "Bagaimana Anda ingin mengambil lokasi?", null, "Buka Peta", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda34
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.execute$lambda$29$lambda$27(this.f$0, dataKey, view);
                }
            }, "Ambil Langsung", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda35
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.execute$lambda$29$lambda$28(this.f$0, dataKey, view);
                }
            }, true, false, 256, null);
        }
        this$0.isCameraGPS = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$29$lambda$27(FormGearJavascriptInterface this$0, String dataKey, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dataKey, "$dataKey");
        this$0.mDataKey = dataKey;
        ActivityResultLauncher<Intent> resultLauncher = this$0.activity.getResultLauncher();
        Intent intent = new Intent(this$0.activity, (Class<?>) PetaGetLocationActivity.class);
        intent.putExtra(PetaGetLocationActivity.EXTRA_ASSIGNMENT_ID, this$0.activity.getMAssignmentId());
        intent.putExtra(PetaGetLocationActivity.EXTRA_DATA_KEY, dataKey);
        intent.putExtra(PetaGetLocationActivity.EXTRA_SHOW_CURRENT_LOCATION, true);
        resultLauncher.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$29$lambda$28(FormGearJavascriptInterface this$0, String dataKey, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dataKey, "$dataKey");
        this$0.requestLocationFasihForm(dataKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$33(String dataKey, final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(dataKey, "$dataKey");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (dataKey.length() == 0) {
            this$0.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda29
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.execute$lambda$33$lambda$30(this.f$0);
                }
            });
            return;
        }
        this$0.mDataKey = dataKey;
        final String str = this$0.activity.getMAssignmentId() + "__" + dataKey;
        FormGearActivity formGearActivity = this$0.activity;
        if (formGearActivity != null) {
            BaseClassActivityNew.showAlertDialog$default(formGearActivity, "Foto", "Anda akan mengambil foto dari ...", null, "Kamera", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda30
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.execute$lambda$33$lambda$31(this.f$0, str, view);
                }
            }, "Galeri", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda31
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.execute$lambda$33$lambda$32(this.f$0, str, view);
                }
            }, true, false, 256, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$33$lambda$30(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "File name tidak tersedia, silakan coba lagi", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$33$lambda$31(FormGearJavascriptInterface this$0, String fileName, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        CameraHelper.INSTANCE.openCamera(this$0.activity, fileName + "__c");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$33$lambda$32(FormGearJavascriptInterface this$0, String fileName, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        CameraHelper.INSTANCE.openGallery(this$0.activity, fileName + "__g");
    }

    public static /* synthetic */ void action$default(FormGearJavascriptInterface formGearJavascriptInterface, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        if ((i & 8) != 0) {
            str4 = null;
        }
        formGearJavascriptInterface.action(str, str2, str3, str4);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JavascriptInterface
    public final void action(String action, final String key, final String data, String otherData) {
        Intrinsics.checkNotNullParameter(action, "action");
        switch (action.hashCode()) {
            case -1798725188:
                if (action.equals(StaticValue.Actions.FILE_RELOAD)) {
                    Intrinsics.checkNotNull(data);
                    fileReload(data);
                    break;
                }
                break;
            case -1702679004:
                if (action.equals(StaticValue.Actions.FILE_UPLOAD)) {
                    Intrinsics.checkNotNull(data);
                    uploadMedia(data);
                    break;
                }
                break;
            case -1611296843:
                if (action.equals("LOCATION")) {
                    FormGearActivity formGearActivity = this.activity;
                    Intrinsics.checkNotNull(formGearActivity);
                    if (ActivityCompat.checkSelfPermission(formGearActivity.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
                        FormGearActivity formGearActivity2 = this.activity;
                        Intrinsics.checkNotNull(formGearActivity2);
                        if (ActivityCompat.checkSelfPermission(formGearActivity2.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda12
                                @Override // java.lang.Runnable
                                public final void run() {
                                    FormGearJavascriptInterface.action$lambda$34(this.f$0);
                                }
                            });
                            break;
                        }
                    }
                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    booleanRef.element = true;
                    String str = data;
                    if (!(str == null || str.length() == 0)) {
                        booleanRef.element = Boolean.parseBoolean(data);
                    }
                    this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda13
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface.action$lambda$39(this.f$0, data, booleanRef, key);
                        }
                    });
                    break;
                }
                break;
            case -1525751696:
                if (action.equals(StaticValue.Actions.CAMERA_GPS)) {
                    FormGearActivity formGearActivity3 = this.activity;
                    Intrinsics.checkNotNull(formGearActivity3);
                    if (ActivityCompat.checkSelfPermission(formGearActivity3.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
                        FormGearActivity formGearActivity4 = this.activity;
                        Intrinsics.checkNotNull(formGearActivity4);
                        if (ActivityCompat.checkSelfPermission(formGearActivity4.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda16
                                @Override // java.lang.Runnable
                                public final void run() {
                                    FormGearJavascriptInterface.action$lambda$45(this.f$0);
                                }
                            });
                            break;
                        }
                    }
                    final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    booleanRef2.element = true;
                    String str2 = data;
                    if (!(str2 == null || str2.length() == 0)) {
                        booleanRef2.element = Boolean.parseBoolean(data);
                    }
                    this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda17
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface.action$lambda$49(this.f$0, data, booleanRef2);
                        }
                    });
                    break;
                }
                break;
            case 67303276:
                if (action.equals(StaticValue.Actions.OPEN_MAPS)) {
                    try {
                        JSONObject jSONObject = new JSONObject(data);
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://maps.google.com/maps?q=loc:" + jSONObject.getString("latitude") + "," + jSONObject.getString("longitude") + " (Lokasi Responden)"));
                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                        this.activity.startActivity(intent);
                        break;
                    } catch (JSONException unused) {
                        return;
                    }
                }
                break;
            case 644144791:
                if (action.equals(StaticValue.Actions.GET_LOC)) {
                    FormGearActivity formGearActivity5 = this.activity;
                    Intrinsics.checkNotNull(formGearActivity5);
                    if (ActivityCompat.checkSelfPermission(formGearActivity5.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
                        FormGearActivity formGearActivity6 = this.activity;
                        Intrinsics.checkNotNull(formGearActivity6);
                        if (ActivityCompat.checkSelfPermission(formGearActivity6.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda14
                                @Override // java.lang.Runnable
                                public final void run() {
                                    FormGearJavascriptInterface.action$lambda$40(this.f$0);
                                }
                            });
                            break;
                        }
                    }
                    GetLocationHelper getLocationHelper = new GetLocationHelper();
                    FormGearActivity formGearActivity7 = this.activity;
                    Intrinsics.checkNotNull(formGearActivity7);
                    getLocationHelper.GetLocation(formGearActivity7, new AnonymousClass4(action));
                    break;
                }
                break;
            case 1980544805:
                if (action.equals(StaticValue.Actions.OPEN_CAMERA)) {
                    this.keyOpenKamera = String.valueOf(key);
                    this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda15
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface.action$lambda$44(data, this);
                        }
                    });
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$34(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Location Permission tidak aktif, aktifkan Location Permission", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$39(final FormGearJavascriptInterface this$0, final String str, final Ref.BooleanRef needCamera, final String str2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(needCamera, "$needCamera");
        FormGearActivity formGearActivity = this$0.activity;
        if (formGearActivity != null) {
            BaseClassActivityNew.showAlertDialog$default(formGearActivity, "Location", "Bagaimana Anda ingin mengambil lokasi?", null, "Buka Peta", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda39
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.action$lambda$39$lambda$37(this.f$0, str, view);
                }
            }, "Ambil Langsung", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda40
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.action$lambda$39$lambda$38(this.f$0, needCamera, str2, view);
                }
            }, true, false, 256, null);
        }
        this$0.isCameraGPS = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$39$lambda$37(FormGearJavascriptInterface this$0, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityResultLauncher<Intent> resultLauncher = this$0.activity.getResultLauncher();
        Intent intent = new Intent(this$0.activity, (Class<?>) PetaGetLocationActivity.class);
        Intent intent2 = new Intent(this$0.activity, (Class<?>) PetaGetLocationActivity.class);
        intent2.putExtra(PetaGetLocationActivity.EXTRA_ASSIGNMENT_ID, this$0.activity.getMAssignmentId());
        intent2.putExtra(PetaGetLocationActivity.EXTRA_DATA_KEY, str);
        intent2.putExtra(PetaGetLocationActivity.EXTRA_SHOW_CURRENT_LOCATION, true);
        resultLauncher.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$39$lambda$38(FormGearJavascriptInterface this$0, Ref.BooleanRef needCamera, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(needCamera, "$needCamera");
        boolean z = needCamera.element;
        Intrinsics.checkNotNull(str);
        this$0.requestLocationWithDialog(z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$40(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Location Permission tidak aktif, aktifkan Location Permission", 0).show();
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"id/go/bpsfasih/ui/formGear/FormGearJavascriptInterface$action$4", "Lid/go/bpsfasih/utils/helper/location/LocCallback;", "result", "", FormGearActivity.LAT, "", "lng", "accuracy", "", "isMock", "", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Boolean;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$action$4, reason: invalid class name */
    public static final class AnonymousClass4 implements LocCallback {
        final /* synthetic */ String $action;

        AnonymousClass4(String str) {
            this.$action = str;
        }

        @Override // id.go.bpsfasih.utils.helper.location.LocCallback
        public void result(final Double lat, final Double lng, Float accuracy, Boolean isMock) {
            FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
            if (formGearActivity != null) {
                final String str = this.$action;
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$action$4$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.AnonymousClass4.result$lambda$0(str, lat, lng, formGearJavascriptInterface);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$0(String action, Double d, Double d2, FormGearJavascriptInterface this$0) {
            Intrinsics.checkNotNullParameter(action, "$action");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            String str = "javascript:result('" + action + "', '" + d + ", " + d2 + "')";
            WebView webView = this$0.webView;
            if (webView != null) {
                webView.evaluateJavascript(str, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$44(String str, final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            this$0.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.action$lambda$44$lambda$41(this.f$0);
                }
            });
            return;
        }
        final String str3 = this$0.activity.getMAssignmentId() + "__" + str;
        FormGearActivity formGearActivity = this$0.activity;
        if (formGearActivity != null) {
            BaseClassActivityNew.showAlertDialog$default(formGearActivity, "Foto", "Anda akan mengambil foto dari ...", null, "Kamera", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.action$lambda$44$lambda$42(this.f$0, str3, view);
                }
            }, "Galeri", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda20
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.action$lambda$44$lambda$43(this.f$0, str3, view);
                }
            }, true, false, 256, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$44$lambda$41(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "File name tidak tersedia, silakan coba lagi", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$44$lambda$42(FormGearJavascriptInterface this$0, String fileName, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        CameraHelper.INSTANCE.openCamera(this$0.activity, fileName + "__c");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$44$lambda$43(FormGearJavascriptInterface this$0, String fileName, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        CameraHelper.INSTANCE.openGallery(this$0.activity, fileName + "__g");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$45(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Location Permission tidak aktif, aktifkan Location Permission", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$49(final FormGearJavascriptInterface this$0, final String str, final Ref.BooleanRef needCamera) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(needCamera, "$needCamera");
        FormGearActivity formGearActivity = this$0.activity;
        if (formGearActivity != null) {
            BaseClassActivityNew.showAlertDialog$default(formGearActivity, "Location", "Bagaimana anda ingin mengambil lokasi sekarang?", null, "Buka Peta", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.action$lambda$49$lambda$47(this.f$0, str, view);
                }
            }, "Ambil Langsung", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearJavascriptInterface.action$lambda$49$lambda$48(this.f$0, needCamera, view);
                }
            }, true, false, 256, null);
        }
        this$0.isCameraGPS = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$49$lambda$47(FormGearJavascriptInterface this$0, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityResultLauncher<Intent> resultLauncher = this$0.activity.getResultLauncher();
        Intent intent = new Intent(this$0.activity, (Class<?>) PetaGetLocationActivity.class);
        intent.putExtra(PetaGetLocationActivity.EXTRA_ASSIGNMENT_ID, this$0.activity.getMAssignmentId());
        intent.putExtra(PetaGetLocationActivity.EXTRA_DATA_KEY, str);
        intent.putExtra(PetaGetLocationActivity.EXTRA_SHOW_CURRENT_LOCATION, true);
        resultLauncher.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void action$lambda$49$lambda$48(FormGearJavascriptInterface this$0, Ref.BooleanRef needCamera, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(needCamera, "$needCamera");
        this$0.requestLocationWithDialog(needCamera.element, "");
    }

    private final void uploadMedia(String json) {
        boolean featuresRemoteConfigIsShow = RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("has_upload_image_error_callback");
        List<String> filenamesFromJsonFormGear = this.formEngineId.equals("1") ? getFilenamesFromJsonFormGear(json) : getFilenamesFromJsonFasihForm(json);
        if (!filenamesFromJsonFormGear.isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08961(filenamesFromJsonFormGear, this, featuresRemoteConfigIsShow, json, null), 2, null);
        } else {
            notifyUploadMediaError(featuresRemoteConfigIsShow, "File media tidak ada");
        }
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$uploadMedia$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$uploadMedia$1, reason: invalid class name and case insensitive filesystem */
    static final class C08961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<String> $fileNames;
        final /* synthetic */ boolean $isShow;
        final /* synthetic */ String $json;
        int label;
        final /* synthetic */ FormGearJavascriptInterface this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08961(List<String> list, FormGearJavascriptInterface formGearJavascriptInterface, boolean z, String str, Continuation<? super C08961> continuation) {
            super(2, continuation);
            this.$fileNames = list;
            this.this$0 = formGearJavascriptInterface;
            this.$isShow = z;
            this.$json = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08961(this.$fileNames, this.this$0, this.$isShow, this.$json, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String str = (String) CollectionsKt.first((List) this.$fileNames);
            final File file = new File(this.this$0.activity.getAnswerPath() + File.separator + FormGearJavascriptInterface.MEDIA_FILE + File.separator + str);
            try {
            } catch (Exception e) {
                FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                boolean z = this.$isShow;
                String message = e.getMessage();
                if (message == null) {
                    message = "Terjadi error upload media";
                }
                formGearJavascriptInterface.notifyUploadMediaError(z, message);
            }
            if (!file.exists()) {
                this.this$0.notifyUploadMediaError(this.$isShow, "File media tidak ditemukan");
                return Unit.INSTANCE;
            }
            final long length = file.length();
            if (length <= 0) {
                this.this$0.notifyUploadMediaError(this.$isShow, "File media kosong");
                return Unit.INSTANCE;
            }
            final String strMd5Base64OfFile = this.this$0.md5Base64OfFile(file);
            AssigmentDAO assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
            String mAssignmentId = this.this$0.activity.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId);
            final AssignmentEntity assignment = assignmentDao.getAssignmentById(mAssignmentId).getAssignment();
            if (assignment == null) {
                this.this$0.notifyUploadMediaError(this.$isShow, "Data assignment tidak ditemukan");
                return Unit.INSTANCE;
            }
            AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
            String mAssignmentId2 = this.this$0.activity.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId2);
            String copyFromId = assignment.getCopyFromId();
            String mPeriodeId = this.this$0.activity.getMPeriodeId();
            Intrinsics.checkNotNull(mPeriodeId);
            final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
            final boolean z2 = this.$isShow;
            final List<String> list = this.$fileNames;
            final String str2 = this.$json;
            assignmentRepositoryImpl.submitPresignS3Put(mAssignmentId2, copyFromId, mPeriodeId, str, length, strMd5Base64OfFile, new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.uploadMedia.1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(S3Response s3Response) {
                    invoke2(s3Response);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(S3Response s3Response) {
                    String message2;
                    S3Entity s3Entity;
                    List<PresignedUrlsItem> presignedUrls;
                    PresignedUrlsItem presignedUrlsItem;
                    boolean z3 = true;
                    if (s3Response != null ? Intrinsics.areEqual((Object) s3Response.getSuccess(), (Object) true) : false) {
                        List<S3Entity> data = s3Response.getData();
                        String presignedUrl = (data == null || (s3Entity = (S3Entity) CollectionsKt.firstOrNull((List) data)) == null || (presignedUrls = s3Entity.getPresignedUrls()) == null || (presignedUrlsItem = (PresignedUrlsItem) CollectionsKt.firstOrNull((List) presignedUrls)) == null) ? null : presignedUrlsItem.getPresignedUrl();
                        String str3 = presignedUrl;
                        if (str3 != null && str3.length() != 0) {
                            z3 = false;
                        }
                        if (z3) {
                            FormGearJavascriptInterface formGearJavascriptInterface3 = formGearJavascriptInterface2;
                            boolean z4 = z2;
                            String message3 = s3Response.getMessage();
                            if (message3 == null) {
                                message3 = "URL upload media tidak tersedia";
                            }
                            formGearJavascriptInterface3.notifyUploadMediaError(z4, message3);
                            return;
                        }
                        AssignmentRepositoryImpl assignmentRepositoryImpl2 = new AssignmentRepositoryImpl();
                        long j = length;
                        String str4 = strMd5Base64OfFile;
                        File file2 = file;
                        final FormGearJavascriptInterface formGearJavascriptInterface4 = formGearJavascriptInterface2;
                        final AssignmentEntity assignmentEntity = assignment;
                        final List<String> list2 = list;
                        final boolean z5 = z2;
                        final String str5 = str2;
                        assignmentRepositoryImpl2.uploadImageS3(presignedUrl, j, str4, file2, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.uploadMedia.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws JSONException {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z6) throws JSONException {
                                if (!z6) {
                                    formGearJavascriptInterface4.notifyUploadMediaError(z5, "Terjadi error upload media");
                                    return;
                                }
                                AssignmentRepositoryImpl assignmentRepositoryImpl3 = new AssignmentRepositoryImpl();
                                String mAssignmentId3 = formGearJavascriptInterface4.activity.getMAssignmentId();
                                Intrinsics.checkNotNull(mAssignmentId3);
                                String copyFromId2 = assignmentEntity.getCopyFromId();
                                String mPeriodeId2 = formGearJavascriptInterface4.activity.getMPeriodeId();
                                Intrinsics.checkNotNull(mPeriodeId2);
                                List<String> list3 = list2;
                                final List<String> list4 = list2;
                                final FormGearJavascriptInterface formGearJavascriptInterface5 = formGearJavascriptInterface4;
                                final boolean z7 = z5;
                                final String str6 = str5;
                                assignmentRepositoryImpl3.submitPresignS3Get(mAssignmentId3, copyFromId2, mPeriodeId2, list3, new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.uploadMedia.1.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(S3Response s3Response2) {
                                        invoke2(s3Response2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(S3Response s3Response2) {
                                        String str7;
                                        String message4;
                                        List<S3Entity> data2;
                                        S3Entity s3Entity2;
                                        List<PresignedUrlsItem> presignedUrls2;
                                        PresignedUrlsItem presignedUrlsItem2;
                                        String presignedUrl2 = (s3Response2 == null || (data2 = s3Response2.getData()) == null || (s3Entity2 = (S3Entity) CollectionsKt.firstOrNull((List) data2)) == null || (presignedUrls2 = s3Entity2.getPresignedUrls()) == null || (presignedUrlsItem2 = (PresignedUrlsItem) CollectionsKt.firstOrNull((List) presignedUrls2)) == null) ? null : presignedUrlsItem2.getPresignedUrl();
                                        String str8 = presignedUrl2;
                                        if (!(str8 == null || str8.length() == 0)) {
                                            if (formGearJavascriptInterface5.formEngineId.equals("1")) {
                                                str7 = "javascript:result('FILE_UPLOADED', '" + presignedUrl2 + "', 'false')";
                                            } else {
                                                PhotoFasihForm photoFasihForm = (PhotoFasihForm) new Gson().fromJson(str6, PhotoFasihForm.class);
                                                str7 = "javascript:fasihForm.event.emit(\n  \"upload-finished\",\n  \"" + formGearJavascriptInterface5.getMDataKey() + "\",\n  '{ \"filename\": \"" + photoFasihForm.getFilename() + "\", \"uri\": \"" + photoFasihForm.getUri() + "\", \"url\": \"" + presignedUrl2 + "\" }'\n)\n";
                                            }
                                            formGearJavascriptInterface5.webView.evaluateJavascript(str7, null);
                                            return;
                                        }
                                        Log.e("Android", "submitPresignS3Get returned empty presigned url for " + list4);
                                        FormGearJavascriptInterface formGearJavascriptInterface6 = formGearJavascriptInterface5;
                                        boolean z8 = z7;
                                        if (s3Response2 == null || (message4 = s3Response2.getMessage()) == null) {
                                            message4 = "Terjadi error upload media";
                                        }
                                        formGearJavascriptInterface6.notifyUploadMediaError(z8, message4);
                                    }
                                });
                            }
                        });
                        return;
                    }
                    FormGearJavascriptInterface formGearJavascriptInterface5 = formGearJavascriptInterface2;
                    boolean z6 = z2;
                    if (s3Response == null || (message2 = s3Response.getMessage()) == null) {
                        message2 = "Error upload media";
                    }
                    formGearJavascriptInterface5.notifyUploadMediaError(z6, message2);
                }
            });
            return Unit.INSTANCE;
        }
    }

    public final void fileReload(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        List<String> filenamesFromJsonFormGear = this.formEngineId.equals("1") ? getFilenamesFromJsonFormGear(json) : getFilenamesFromJsonFasihForm(json);
        if (!filenamesFromJsonFormGear.isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08771(filenamesFromJsonFormGear, json, null), 2, null);
        }
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$fileReload$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$fileReload$1, reason: invalid class name and case insensitive filesystem */
    static final class C08771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<String> $fileNames;
        final /* synthetic */ String $json;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08771(List<String> list, String str, Continuation<? super C08771> continuation) {
            super(2, continuation);
            this.$fileNames = list;
            this.$json = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08771(this.$fileNames, this.$json, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                AssigmentDAO assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
                String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                Intrinsics.checkNotNull(mAssignmentId);
                AssignmentEntity assignment = assignmentDao.getAssignmentById(mAssignmentId).getAssignment();
                AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
                String mAssignmentId2 = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                Intrinsics.checkNotNull(mAssignmentId2);
                Intrinsics.checkNotNull(assignment);
                String copyFromId = assignment.getCopyFromId();
                String mPeriodeId = FormGearJavascriptInterface.this.activity.getMPeriodeId();
                Intrinsics.checkNotNull(mPeriodeId);
                List<String> list = this.$fileNames;
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                final String str = this.$json;
                assignmentRepositoryImpl.refreshImageS3(mAssignmentId2, copyFromId, mPeriodeId, list, new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.fileReload.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(S3Response s3Response) {
                        invoke2(s3Response);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(S3Response s3Response) {
                        String str2;
                        S3Entity s3Entity;
                        List<PresignedUrlsItem> presignedUrls;
                        PresignedUrlsItem presignedUrlsItem;
                        S3Entity s3Entity2;
                        List<PresignedUrlsItem> presignedUrls2;
                        PresignedUrlsItem presignedUrlsItem2;
                        Intrinsics.checkNotNull(s3Response);
                        Boolean success = s3Response.getSuccess();
                        Intrinsics.checkNotNull(success);
                        if (success.booleanValue()) {
                            s3Response.getData();
                            FormGearJavascriptInterface formGearJavascriptInterface2 = formGearJavascriptInterface;
                            String str3 = str;
                            if (formGearJavascriptInterface2.formEngineId.equals("1")) {
                                List<S3Entity> data = s3Response.getData();
                                str2 = "javascript:result('FILE_UPLOADED', '" + ((data == null || (s3Entity2 = data.get(0)) == null || (presignedUrls2 = s3Entity2.getPresignedUrls()) == null || (presignedUrlsItem2 = presignedUrls2.get(0)) == null) ? null : presignedUrlsItem2.getPresignedUrl()) + "', 'false')";
                            } else {
                                PhotoFasihForm photoFasihForm = (PhotoFasihForm) new Gson().fromJson(str3, PhotoFasihForm.class);
                                String mDataKey = formGearJavascriptInterface2.getMDataKey();
                                String filename = photoFasihForm.getFilename();
                                String uri = photoFasihForm.getUri();
                                List<S3Entity> data2 = s3Response.getData();
                                str2 = "javascript:fasihForm.event.emit(\n  \"upload-finished\",\n  \"" + mDataKey + "\",\n  '{ \"filename\": \"" + filename + "\", \"uri\": \"" + uri + "\", \"url\": \"" + ((data2 == null || (s3Entity = data2.get(0)) == null || (presignedUrls = s3Entity.getPresignedUrls()) == null || (presignedUrlsItem = presignedUrls.get(0)) == null) ? null : presignedUrlsItem.getPresignedUrl()) + "\" }'\n)\n";
                            }
                            formGearJavascriptInterface2.webView.evaluateJavascript(str2, null);
                        }
                    }
                });
            } catch (Exception unused) {
                FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
                final FormGearJavascriptInterface formGearJavascriptInterface2 = FormGearJavascriptInterface.this;
                formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$fileReload$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.C08771.invokeSuspend$lambda$0(formGearJavascriptInterface2);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FormGearJavascriptInterface formGearJavascriptInterface) {
            Toast.makeText(formGearJavascriptInterface.activity, "Terjadi error reload media", 0).show();
        }
    }

    public final List<String> getFilenamesFromJsonFormGear(String json) throws JsonSyntaxException {
        Intrinsics.checkNotNullParameter(json, "json");
        Object objFromJson = new Gson().fromJson(json, (Class<Object>) PhotoFormGear[].class);
        Intrinsics.checkNotNullExpressionValue(objFromJson, "gson.fromJson(json, Arra…otoFormGear>::class.java)");
        List list = ArraysKt.toList((Object[]) objFromJson);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((PhotoFormGear) it.next()).getFilename());
        }
        return arrayList;
    }

    public final List<String> getFilenamesFromJsonFasihForm(String json) throws JsonSyntaxException {
        Intrinsics.checkNotNullParameter(json, "json");
        Object objFromJson = new Gson().fromJson(json, (Class<Object>) PhotoFasihForm.class);
        Intrinsics.checkNotNullExpressionValue(objFromJson, "gson.fromJson(json, PhotoFasihForm::class.java)");
        return CollectionsKt.listOf(((PhotoFasihForm) objFromJson).getFilename());
    }

    public final void cameraResult(final File file, final String fileNameFormGear) {
        Intrinsics.checkNotNullParameter(fileNameFormGear, "fileNameFormGear");
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface.cameraResult$lambda$51(file, this, fileNameFormGear);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cameraResult$lambda$51(File file, FormGearJavascriptInterface this$0, String fileNameFormGear) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileNameFormGear, "$fileNameFormGear");
        if (file != null && this$0.activity.getAnswerPath() != null) {
            String str2 = fileNameFormGear + ".jpg";
            Toast.makeText(this$0.activity, "Nama file foto : " + str2, 0).show();
            FileHelper.Companion companion = FileHelper.INSTANCE;
            String path = file.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "file.path");
            companion.moveToAnswer(path, this$0.activity.getAnswerPath(), str2);
            File file2 = new File(this$0.activity.getAnswerPath() + File.separator + MEDIA_FILE + File.separator + str2);
            if (!Intrinsics.areEqual((Object) this$0.isCameraGPS, (Object) true)) {
                if (this$0.formEngineId.equals("1")) {
                    str = "javascript:result('CAMERA', 'file://" + file2 + "', '" + str2 + "')";
                } else {
                    str = "javascript:fasihForm.event.emit(\n  \"file-selected\",\n  \"" + this$0.getMDataKey() + "\",\n  '[{ \"filename\": \"" + str2 + "\", \"uri\": \"file://" + file2 + "\" }]'\n)\n";
                }
                try {
                    mobileExit$default(this$0, false, 1, null);
                } catch (Exception unused) {
                }
                this$0.webView.evaluateJavascript(str, null);
            }
        }
        this$0.isCameraGPS = false;
    }

    public final void requestLocationWithDialog(boolean needCamera, String dataKey) {
        Intrinsics.checkNotNullParameter(dataKey, "dataKey");
        Log.d(TAG_LOCATION, "requestLocationWithDialog needCamera=" + needCamera + " dataKey=" + dataKey + " assignmentId=" + this.activity.getMAssignmentId());
        this.activity.showProgressBar();
        GetLocationHelper getLocationHelper = new GetLocationHelper();
        FormGearActivity formGearActivity = this.activity;
        Intrinsics.checkNotNull(formGearActivity);
        getLocationHelper.GetLocation(formGearActivity, new C08871(needCamera, dataKey));
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"id/go/bpsfasih/ui/formGear/FormGearJavascriptInterface$requestLocationWithDialog$1", "Lid/go/bpsfasih/utils/helper/location/LocCallback;", "result", "", FormGearActivity.LAT, "", "lng", "accuracy", "", "isMock", "", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Boolean;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationWithDialog$1, reason: invalid class name and case insensitive filesystem */
    public static final class C08871 implements LocCallback {
        final /* synthetic */ String $dataKey;
        final /* synthetic */ boolean $needCamera;

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$4$lambda$3(View view) {
        }

        C08871(boolean z, String str) {
            this.$needCamera = z;
            this.$dataKey = str;
        }

        @Override // id.go.bpsfasih.utils.helper.location.LocCallback
        public void result(final Double lat, final Double lng, final Float accuracy, final Boolean isMock) {
            Log.d(FormGearJavascriptInterface.TAG_LOCATION, "requestLocationWithDialog result lat=" + lat + " lng=" + lng + " accuracy=" + accuracy + " isMock=" + isMock + " assignmentId=" + FormGearJavascriptInterface.this.activity.getMAssignmentId());
            FormGearJavascriptInterface.this.checkMock(isMock);
            FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
            if (formGearActivity != null) {
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                final boolean z = this.$needCamera;
                final String str = this.$dataKey;
                formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationWithDialog$1$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() throws JSONException, IOException {
                        FormGearJavascriptInterface.C08871.result$lambda$4(formGearJavascriptInterface, lat, lng, z, isMock, accuracy, str);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$4(final FormGearJavascriptInterface this$0, Double d, Double d2, boolean z, Boolean bool, Float f, String dataKey) throws JSONException, IOException {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(dataKey, "$dataKey");
            this$0.activity.hideProgressBar();
            if (d == null || d2 == null) {
                this$0.activity.showAlertDialogColor("Gagal", Integer.valueOf(R.color.error30), "Gagal menambil lokasi", Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationWithDialog$1$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearJavascriptInterface.C08871.result$lambda$4$lambda$3(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
                return;
            }
            this$0.coordinat = new JSONObject();
            if (z) {
                this$0.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationWithDialog$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.C08871.result$lambda$4$lambda$2(this$0);
                    }
                });
            } else {
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    return;
                }
                this$0.createLocationHistory(d.doubleValue(), d2.doubleValue(), f != null ? f.floatValue() : 0.0f, dataKey, FormGearJavascriptInterface.STATUS_LOCATION_DIRECT);
                this$0.setResultLocation(d, d2, Double.valueOf(f != null ? f.floatValue() : 0.0f), "");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$4$lambda$2(final FormGearJavascriptInterface this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            FormGearActivity formGearActivity = this$0.activity;
            if (formGearActivity != null) {
                BaseClassActivityNew.showAlertDialog$default(formGearActivity, "Foto", "Anda akan mengambil foto dari ...", null, "Kamera", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationWithDialog$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearJavascriptInterface.C08871.result$lambda$4$lambda$2$lambda$0(this$0, view);
                    }
                }, "Galeri", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationWithDialog$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearJavascriptInterface.C08871.result$lambda$4$lambda$2$lambda$1(this$0, view);
                    }
                }, true, false, 256, null);
            }
            this$0.isCameraGPS = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$4$lambda$2$lambda$0(FormGearJavascriptInterface this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            CameraHelper.INSTANCE.openCamera(this$0.activity, "photo_");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$4$lambda$2$lambda$1(FormGearJavascriptInterface this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            CameraHelper.INSTANCE.openGallery(this$0.activity, "photo");
        }
    }

    public final void requestLocationFasihForm(String dataKey) {
        Intrinsics.checkNotNullParameter(dataKey, "dataKey");
        Log.d(TAG_LOCATION, "requestLocationFasihForm dataKey=" + dataKey + " assignmentId=" + this.activity.getMAssignmentId());
        this.activity.showProgressBar();
        GetLocationHelper getLocationHelper = new GetLocationHelper();
        FormGearActivity formGearActivity = this.activity;
        Intrinsics.checkNotNull(formGearActivity);
        getLocationHelper.GetLocation(formGearActivity, new C08861(dataKey));
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"id/go/bpsfasih/ui/formGear/FormGearJavascriptInterface$requestLocationFasihForm$1", "Lid/go/bpsfasih/utils/helper/location/LocCallback;", "result", "", FormGearActivity.LAT, "", "lng", "accuracy", "", "isMock", "", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Boolean;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationFasihForm$1, reason: invalid class name and case insensitive filesystem */
    public static final class C08861 implements LocCallback {
        final /* synthetic */ String $dataKey;

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$1$lambda$0(View view) {
        }

        C08861(String str) {
            this.$dataKey = str;
        }

        @Override // id.go.bpsfasih.utils.helper.location.LocCallback
        public void result(final Double lat, final Double lng, final Float accuracy, final Boolean isMock) {
            Log.d(FormGearJavascriptInterface.TAG_LOCATION, "requestLocationFasihForm result lat=" + lat + " lng=" + lng + " accuracy=" + accuracy + " isMock=" + isMock + " assignmentId=" + FormGearJavascriptInterface.this.activity.getMAssignmentId());
            FormGearJavascriptInterface.this.checkMock(isMock);
            FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
            if (formGearActivity != null) {
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                final String str = this.$dataKey;
                formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationFasihForm$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() throws JSONException, IOException {
                        FormGearJavascriptInterface.C08861.result$lambda$1(formGearJavascriptInterface, lat, lng, isMock, accuracy, str);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void result$lambda$1(FormGearJavascriptInterface this$0, Double d, Double d2, Boolean bool, Float f, String dataKey) throws JSONException, IOException {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(dataKey, "$dataKey");
            this$0.activity.hideProgressBar();
            if (d == null || d2 == null) {
                this$0.activity.showAlertDialogColor("Gagal", Integer.valueOf(R.color.error30), "Gagal menambil lokasi", Integer.valueOf(R.color.error30), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$requestLocationFasihForm$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearJavascriptInterface.C08861.result$lambda$1$lambda$0(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
                return;
            }
            this$0.coordinat = new JSONObject();
            if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                return;
            }
            this$0.createLocationHistory(d.doubleValue(), d2.doubleValue(), f != null ? f.floatValue() : 0.0f, dataKey, FormGearJavascriptInterface.STATUS_LOCATION_DIRECT);
            this$0.setResultLocation(d, d2, Double.valueOf(f != null ? f.floatValue() : 0.0f), dataKey);
        }
    }

    /* renamed from: getDataKey, reason: from getter */
    public final String getMDataKey() {
        return this.mDataKey;
    }

    public final void setResultLocation(Double latitude, Double longitude, Double accuracy, String dataKey) throws JSONException, IOException {
        String str;
        String str2;
        ValueCallback<String> valueCallback;
        Log.d(TAG_LOCATION, "setResultLocation lat=" + latitude + " lng=" + longitude + " accuracy=" + accuracy + " dataKey=" + dataKey + " assignmentId=" + this.activity.getMAssignmentId());
        AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
        FormGearActivity formGearActivity = this.activity;
        String mAssignmentId = formGearActivity != null ? formGearActivity.getMAssignmentId() : null;
        Intrinsics.checkNotNull(mAssignmentId);
        assignmentRepository.updateLatLong(mAssignmentId + InternalZipConstants.ZIP_FILE_SEPARATOR + FasihApp.INSTANCE.getSession().getUserId(), latitude, longitude);
        Intrinsics.checkNotNull(latitude);
        double dDoubleValue = latitude.doubleValue();
        Intrinsics.checkNotNull(longitude);
        double dDoubleValue2 = longitude.doubleValue();
        double dDoubleValue3 = accuracy != null ? accuracy.doubleValue() : 0.0d;
        Intrinsics.checkNotNull(dataKey);
        createLocationHistory(dDoubleValue, dDoubleValue2, dDoubleValue3, dataKey, STATUS_LOCATION_MAP);
        if (Intrinsics.areEqual(latitude, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) || Intrinsics.areEqual(longitude, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda48
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.setResultLocation$lambda$52(this.f$0);
                }
            });
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("latitude", latitude);
        jSONObject.put("longitude", longitude);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("coordinat", jSONObject);
        jSONObject2.put("remark", "");
        jSONObject2.put("accuracy", accuracy != null ? accuracy : "");
        if (this.formEngineId.equals("1")) {
            str2 = "javascript:result('CAMERA_GPS', '" + jSONObject2 + "')";
            str = dataKey;
        } else {
            str = dataKey;
            str2 = "javascript:fasihForm.event.emit('geolocation-acquired','" + str + "','{\"latitude\": " + latitude + ", \"longitude\": " + longitude + ", \"accuracy\": " + accuracy + "}')";
        }
        String str3 = str2;
        saveLogParadata$default(this, CommonCons.INSTANCE.getACTION_LOG_GET_LOCATION() + "_" + str, latitude, longitude, false, null, 16, null);
        try {
            Log.d(">>>", "SAVE LOCATION");
            valueCallback = null;
            try {
                mobileExit$default(this, false, 1, null);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            valueCallback = null;
        }
        WebView webView = this.webView;
        if (webView != null) {
            webView.evaluateJavascript(str3, valueCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setResultLocation$lambda$52(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Koordinat tidak valid, mohon ulangi lagi", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createLocationHistory(double lat, double lng, double acc, String dataKey, String status) throws IOException {
        ArrayList arrayList;
        LocationHistory[] locationHistoryAsObject = FileHelper.INSTANCE.readLocationHistoryAsObject(Directory.INSTANCE.getABSOLUTEPATHENV() + "location_histories.json");
        if (locationHistoryAsObject == null || (arrayList = ArraysKt.toMutableList(locationHistoryAsObject)) == null) {
            arrayList = new ArrayList();
        }
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        arrayList.add(new LocationHistory(lowerCase, this.activity.getMAssignmentId(), dataKey, lat, lng, acc, String.valueOf(System.currentTimeMillis()), status));
        String jsonString = new Gson().toJson(arrayList);
        Log.d(">>> status", status);
        Log.d(">>> history", jsonString);
        Log.d(">>> filePath", Directory.INSTANCE.getABSOLUTEPATHENV() + "location_histories.json)");
        FileHelper.Companion companion = FileHelper.INSTANCE;
        String absolutepathenv = Directory.INSTANCE.getABSOLUTEPATHENV();
        Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
        companion.writeFile(absolutepathenv, "location_histories.json", jsonString);
    }

    public final void checkMock(Boolean isMock) {
        if (Intrinsics.areEqual((Object) isMock, (Object) true)) {
            final boolean featuresRemoteConfigIsShow = RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("is_force_dialog_mock");
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda32
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.checkMock$lambda$54(this.f$0, featuresRemoteConfigIsShow);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkMock$lambda$54(final FormGearJavascriptInterface this$0, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearActivity formGearActivity = this$0.activity;
        int i = R.color.error30;
        int i2 = R.color.error30;
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda38
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearJavascriptInterface.checkMock$lambda$54$lambda$53(z, this$0, view);
            }
        };
        int i3 = R.drawable.layout_button_error;
        formGearActivity.showAlertDialogColor("Peringatan", Integer.valueOf(i), "Anda terdeteksi menggunakan manipulasi GPS", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(i3), onClickListener, null, null, null, z ? null : Integer.valueOf(R.color.error30), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkMock$lambda$54$lambda$53(boolean z, FormGearJavascriptInterface this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            mobileExit$default(this$0, false, 1, null);
        }
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$setPrincipalData$1", f = "FormGearJavascriptInterface.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1615, 1710}, m = "invokeSuspend", n = {FormGearJavascriptInterface.PRINCIPAL_FILE, "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9"})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$setPrincipalData$1, reason: invalid class name and case insensitive filesystem */
    static final class C08931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Boolean, Unit> $callback;
        final /* synthetic */ String $dataPrincipal;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08931(String str, Function1<? super Boolean, Unit> function1, Continuation<? super C08931> continuation) {
            super(2, continuation);
            this.$dataPrincipal = str;
            this.$callback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08931(this.$dataPrincipal, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(19:0|2|(1:206)|(1:(1:(8:6|7|8|197|(1:199)|200|204|205)(2:9|10))(3:11|12|13))(3:15|16|(1:18))|14|19|208|20|(2:22|23)(1:24)|27|(2:30|28)|210|31|(6:34|(1:41)(1:40)|42|(2:44|213)(3:(1:51)(1:50)|52|(2:54|214)(3:(1:61)(1:60)|62|(2:64|215)(3:(1:71)(1:70)|72|(2:74|216)(3:(1:81)(1:80)|82|(2:84|217)(3:(1:91)(1:90)|92|(2:94|218)(3:(1:101)(1:100)|102|(2:104|219)(3:(1:111)(1:110)|112|(2:114|220)(3:(1:121)(1:120)|122|(2:124|221)(3:(1:131)(1:130)|132|(2:134|212)(1:222))))))))))|135|32)|211|136|(18:156|(1:158)|159|(1:161)(1:162)|163|(1:165)|166|(1:168)(1:169)|170|(1:172)(1:173)|174|(1:176)(1:177)|178|(1:180)(1:181)|(1:187)(1:186)|188|(1:190)(1:191)|192)|193|(1:195)(6:196|197|(0)|200|204|205)) */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x00a7, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x00a8, code lost:
        
            r0.printStackTrace();
            id.go.bpsfasih.utils.helper.CrashlyticHelper.INSTANCE.sendException(r0);
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:199:0x04a3 A[Catch: Exception -> 0x04fd, TryCatch #0 {Exception -> 0x04fd, blocks: (B:7:0x003a, B:197:0x049f, B:199:0x04a3, B:200:0x04f2, B:12:0x004e, B:19:0x0085, B:20:0x008e, B:22:0x009d, B:27:0x00b2, B:28:0x00be, B:30:0x00c4, B:31:0x00ed, B:32:0x0141, B:34:0x0147, B:36:0x014f, B:38:0x0155, B:40:0x015b, B:42:0x016d, B:44:0x0182, B:46:0x018c, B:48:0x0192, B:50:0x0198, B:52:0x01a2, B:54:0x01b3, B:56:0x01bd, B:58:0x01c3, B:60:0x01c9, B:62:0x01d3, B:64:0x01e4, B:66:0x01ee, B:68:0x01f4, B:70:0x01fa, B:72:0x0204, B:74:0x0215, B:76:0x021f, B:78:0x0225, B:80:0x022b, B:82:0x0235, B:84:0x0246, B:86:0x0250, B:88:0x0256, B:90:0x025c, B:92:0x0266, B:94:0x0277, B:96:0x0281, B:98:0x0287, B:100:0x028d, B:102:0x0297, B:104:0x02a8, B:106:0x02b2, B:108:0x02b8, B:110:0x02be, B:112:0x02c8, B:114:0x02d9, B:116:0x02e2, B:118:0x02e8, B:120:0x02ee, B:122:0x02f8, B:124:0x0309, B:126:0x0312, B:128:0x0318, B:130:0x031e, B:132:0x0328, B:134:0x0339, B:136:0x0347, B:138:0x0353, B:140:0x035b, B:142:0x0363, B:144:0x036b, B:146:0x0373, B:148:0x037b, B:150:0x0383, B:152:0x038b, B:154:0x0393, B:156:0x039b, B:159:0x03b6, B:163:0x03c1, B:166:0x03d0, B:170:0x03db, B:174:0x03fe, B:178:0x040f, B:183:0x0422, B:188:0x042e, B:192:0x0437, B:193:0x0462, B:26:0x00a8, B:16:0x0058), top: B:206:0x000c, inners: #1 }] */
        /* JADX WARN: Type inference failed for: r3v104, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v67, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v71, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v75, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v79, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v83, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v87, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v91, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v95, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v99, types: [T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v0, types: [T, java.util.HashMap] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r42) {
            /*
                Method dump skipped, instructions count: 1296
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.C08931.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void setPrincipalData(String dataPrincipal, Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Log.d(">>> principal", String.valueOf(dataPrincipal));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C08931(dataPrincipal, callback, null), 3, null);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$mobileExit$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$mobileExit$1, reason: invalid class name and case insensitive filesystem */
    static final class C08831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showTemporarySaveDialog;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08831(boolean z, Continuation<? super C08831> continuation) {
            super(2, continuation);
            this.$showTemporarySaveDialog = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08831(this.$showTemporarySaveDialog, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    FormGearJavascriptInterface.this.pendingShowTemporarySaveDialog = this.$showTemporarySaveDialog;
                    String str = FormGearJavascriptInterface.this.formEngineId.equals("1") ? "javascript:mobileExitAndroid()" : "javascript:fasihForm.event.emit('trigger-save')";
                    WebView webView = FormGearJavascriptInterface.this.webView;
                    if (webView != null) {
                        webView.evaluateJavascript(str, null);
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02181(FormGearJavascriptInterface.this, null), 2, null);
                } catch (Error unused) {
                    FormGearJavascriptInterface.this.pendingShowTemporarySaveDialog = false;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* compiled from: FormGearJavascriptInterface.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$mobileExit$1$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {1750}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$mobileExit$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ FormGearJavascriptInterface this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02181(FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super C02181> continuation) {
                super(2, continuation);
                this.this$0 = formGearJavascriptInterface;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C02181(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C02181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Integer assignmentStatusId;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                    String mAssignmentId = this.this$0.activity.getMAssignmentId();
                    String userId = FasihApp.INSTANCE.getSession().getUserId();
                    this.label = 1;
                    obj = assignmentRepository.getAssignmentByIdPrimary(mAssignmentId + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                AssignmentEntity assignment = ((Sync) obj).getAssignment();
                if ((assignment == null || (assignmentStatusId = assignment.getAssignmentStatusId()) == null || assignmentStatusId.intValue() != 0) ? false : true) {
                    FormGearJavascriptInterface.saveLogParadata$default(this.this$0, CommonCons.INSTANCE.getACTION_LOG_CLOSED(), null, null, null, null, 30, null);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public static /* synthetic */ void mobileExit$default(FormGearJavascriptInterface formGearJavascriptInterface, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        formGearJavascriptInterface.mobileExit(z);
    }

    public final void mobileExit(boolean showTemporarySaveDialog) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08831(showTemporarySaveDialog, null), 2, null);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$finalize$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$finalize$1, reason: invalid class name and case insensitive filesystem */
    static final class C08781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08781(Continuation<? super C08781> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08781(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    if (FormGearJavascriptInterface.this.formEngineId.equals("1")) {
                        FormGearJavascriptInterface.this.activity.requestCheckButtonApproval();
                    } else {
                        WebView webView = FormGearJavascriptInterface.this.webView;
                        if (webView != null) {
                            webView.evaluateJavascript("javascript:fasihForm.event.emit('trigger-finalize')", null);
                        }
                    }
                } catch (Error unused) {
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void finalize() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08781(null), 2, null);
    }

    @JavascriptInterface
    public final void dispatchFinalResult(String data, String remark, String principal, String summary) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(principal, "principal");
        Intrinsics.checkNotNullParameter(summary, "summary");
        this.activity.requestCheckButtonApprovalFasihForm(new JsonParser().parse(summary).getAsJsonObject().get(Constants.IPC_BUNDLE_KEY_SEND_ERROR).getAsJsonArray().size() > 0, data, remark, principal);
    }

    public final void approval(final boolean isApproval) {
        if (Network.INSTANCE.isOnline(this.activity)) {
            if (SsoHelper.INSTANCE.checkExpSession()) {
                approveReject(isApproval);
                return;
            } else {
                this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda23
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.approval$lambda$55(this.f$0, isApproval);
                    }
                });
                return;
            }
        }
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface.approval$lambda$56(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void approval$lambda$55(final FormGearJavascriptInterface this$0, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.showProgressBar();
        SsoHelper.INSTANCE.requestRefreshToken(this$0.activity, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$approval$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z2) {
                if (!z2) {
                    this.this$0.activity.hideProgressBar();
                    this.this$0.activity.showAlertDialogKodeVerifikasiLogout();
                } else {
                    this.this$0.approveReject(z);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void approval$lambda$56(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Tidak ada koneksi internet", 0).show();
    }

    public final void edit(final boolean isApproval, String data, final String remark, final String principal) throws IOException {
        String wrappedDataKey;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(principal, "principal");
        if (Network.INSTANCE.isOnline(this.activity)) {
            AssignmentEntity currentAssignmentEntity = getCurrentAssignmentEntity();
            boolean z = currentAssignmentEntity != null && currentAssignmentEntity.isEncrypt();
            if (z) {
                wrappedDataKey = AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(currentAssignmentEntity);
            } else {
                wrappedDataKey = null;
            }
            this.timestampNow = Long.valueOf(System.currentTimeMillis());
            FileHelper.INSTANCE.saveAssignmentFile(this.activity.getAnswerPath(), "data" + CommonCons.INSTANCE.getEXTENSION_JSON(), data, this.timestampNow, z, wrappedDataKey, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.edit.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws IOException {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z2) throws IOException {
                    if (z2) {
                        Log.d(">> DATA SAVE", "SUCCESS");
                        Log.d(">> MEDIA SAVE", "SUCCESS");
                        FileHelper.Companion companion = FileHelper.INSTANCE;
                        String answerPath = FormGearJavascriptInterface.this.activity.getAnswerPath();
                        String str = FormGearJavascriptInterface.PRINCIPAL_FILE + CommonCons.INSTANCE.getEXTENSION_JSON();
                        String str2 = principal;
                        Long timestampNow = FormGearJavascriptInterface.this.getTimestampNow();
                        final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                        final String str3 = principal;
                        final String str4 = remark;
                        final boolean z3 = isApproval;
                        companion.saveAssignmentFile(answerPath, str, str2, timestampNow, false, (32 & 32) != 0 ? null : null, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.edit.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z4) {
                                if (z4) {
                                    FormGearJavascriptInterface formGearJavascriptInterface2 = formGearJavascriptInterface;
                                    String str5 = str3;
                                    final FormGearJavascriptInterface formGearJavascriptInterface3 = formGearJavascriptInterface;
                                    final String str6 = str4;
                                    final boolean z5 = z3;
                                    formGearJavascriptInterface2.setPrincipalData(str5, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.edit.1.1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                            invoke(bool.booleanValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(boolean z6) {
                                            if (z6) {
                                                Log.d(">> PRINCIPAL SAVE", "SUCCESS");
                                                DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateComment(formGearJavascriptInterface3.activity.getMAssignmentId() + InternalZipConstants.ZIP_FILE_SEPARATOR + FasihApp.INSTANCE.getSession().getUserId(), str6);
                                                Log.d(">> REMARK : ", "SUCCESS");
                                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02161(formGearJavascriptInterface3, null), 2, null);
                                                formGearJavascriptInterface3.onlineEdit(z5);
                                                return;
                                            }
                                            Log.d(">> PRINCIPAL SAVE 2", "FAILED");
                                        }

                                        /* compiled from: FormGearJavascriptInterface.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$edit$1$1$1$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$edit$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                        static final class C02161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            int label;
                                            final /* synthetic */ FormGearJavascriptInterface this$0;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            C02161(FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super C02161> continuation) {
                                                super(2, continuation);
                                                this.this$0 = formGearJavascriptInterface;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new C02161(this.this$0, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((C02161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object obj) {
                                                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                if (this.label != 0) {
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                ResultKt.throwOnFailure(obj);
                                                AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                                                String mAssignmentId = this.this$0.activity.getMAssignmentId();
                                                Intrinsics.checkNotNull(mAssignmentId);
                                                assignmentRepository.updateIsEncrypt(mAssignmentId);
                                                return Unit.INSTANCE;
                                            }
                                        }
                                    });
                                    return;
                                }
                                Log.d(">> PRINCIPAL SAVE 2", "FAILED");
                            }
                        });
                    }
                }
            });
            return;
        }
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda41
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface.edit$lambda$57(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void edit$lambda$57(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Tidak ada koneksi internet", 0).show();
    }

    public final void revoke() {
        if (Network.INSTANCE.isOnline(this.activity)) {
            if (SsoHelper.INSTANCE.checkExpSession()) {
                revokeProcess();
                return;
            } else {
                this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda42
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.revoke$lambda$58(this.f$0);
                    }
                });
                return;
            }
        }
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda43
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface.revoke$lambda$59(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revoke$lambda$58(final FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.showProgressBar();
        SsoHelper.INSTANCE.requestRefreshToken(this$0.activity, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$revoke$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (!z) {
                    this.this$0.activity.hideProgressBar();
                    this.this$0.activity.showAlertDialogKodeVerifikasiLogout();
                } else {
                    this.this$0.revokeProcess();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revoke$lambda$59(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Tidak ada koneksi internet", 0).show();
    }

    static /* synthetic */ void submit$default(FormGearJavascriptInterface formGearJavascriptInterface, String str, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        formGearJavascriptInterface.submit(str, bool);
    }

    private final void submit(String resume, Boolean isApproval) {
        if (Network.INSTANCE.isOnline(this.activity)) {
            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda27
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.submit$lambda$60(this.f$0);
                }
            });
            NotificationRepositoryImpl notificationRepositoryImpl = new NotificationRepositoryImpl();
            String surveyId = this.activity.getSurveyId();
            if (surveyId == null) {
                surveyId = "";
            }
            notificationRepositoryImpl.checkVersionTemplateValidation(surveyId, new AnonymousClass2(isApproval));
            return;
        }
        this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                FormGearJavascriptInterface.submit$lambda$61(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submit$lambda$60(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.showProgressBar();
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lid/go/bpsfasih/data/local/models/TemplateValidationResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2, reason: invalid class name */
    static final class AnonymousClass2 extends Lambda implements Function1<TemplateValidationResponse, Unit> {
        final /* synthetic */ Boolean $isApproval;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Boolean bool) {
            super(1);
            this.$isApproval = bool;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$4$lambda$3(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(TemplateValidationResponse templateValidationResponse) {
            invoke2(templateValidationResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(TemplateValidationResponse templateValidationResponse) {
            String template_version;
            String validasi_version;
            final String str;
            FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
            final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
            formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    FormGearJavascriptInterface.AnonymousClass2.invoke$lambda$0(formGearJavascriptInterface);
                }
            });
            Log.d("FOUR", "checkNotificationTemplateValidationVersion - " + new Gson().toJson(templateValidationResponse));
            if (templateValidationResponse != null ? Intrinsics.areEqual((Object) templateValidationResponse.getSuccess(), (Object) true) : false) {
                TemplateValidasiVersionModel dataTemplateValidationVersion = FormGearJavascriptInterface.this.getDataTemplateValidationVersion();
                TemplateValidationEntity templateValidationEntity = (TemplateValidationEntity) CollectionsKt.firstOrNull((List) templateValidationResponse.getData());
                if (templateValidationEntity != null) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C02332(templateValidationEntity, null), 3, null);
                    if (dataTemplateValidationVersion == null || !StringsKt.equals$default(dataTemplateValidationVersion.getTemplate_id(), templateValidationEntity.getTemplate_id(), false, 2, null) || !StringsKt.equals$default(dataTemplateValidationVersion.getTemplate_version(), templateValidationEntity.getTemplate_version(), false, 2, null) || !StringsKt.equals$default(dataTemplateValidationVersion.getValidasi_version(), templateValidationEntity.getValidasi_version(), false, 2, null)) {
                        FormGearActivity formGearActivity2 = FormGearJavascriptInterface.this.activity;
                        String str2 = "-";
                        if (dataTemplateValidationVersion == null || (template_version = dataTemplateValidationVersion.getTemplate_version()) == null) {
                            template_version = "-";
                        }
                        if (dataTemplateValidationVersion != null && (validasi_version = dataTemplateValidationVersion.getValidasi_version()) != null) {
                            str2 = validasi_version;
                        }
                        BaseClassActivityNew.showAlertDialog$default(formGearActivity2, "Kesalahan", "Versi template atau validasi yang sudah diunduh belum terbaru. Versi terunduh :" + template_version + InternalZipConstants.ZIP_FILE_SEPARATOR + str2 + " | Versi terbaru : " + templateValidationEntity.getTemplate_version() + InternalZipConstants.ZIP_FILE_SEPARATOR + templateValidationEntity.getValidasi_version() + ". Silahkan sync template dan validasi terlebih dahulu", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2$$ExternalSyntheticLambda3
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                FormGearJavascriptInterface.AnonymousClass2.invoke$lambda$1(view);
                            }
                        }, null, null, false, false, 128, null);
                        return;
                    }
                    Boolean bool = this.$isApproval;
                    if (bool == null) {
                        str = "Submit";
                    } else if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                        str = "Approve";
                    } else {
                        if (!Intrinsics.areEqual((Object) bool, (Object) false)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        str = "Reject";
                    }
                    FormGearActivity formGearActivity3 = FormGearJavascriptInterface.this.activity;
                    final FormGearJavascriptInterface formGearJavascriptInterface2 = FormGearJavascriptInterface.this;
                    formGearActivity3.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearJavascriptInterface.AnonymousClass2.invoke$lambda$4(formGearJavascriptInterface2, str);
                        }
                    });
                    return;
                }
                FormGearJavascriptInterface.this.showShortToast("Data versi template dan validasi tidak tersedia");
                return;
            }
            Toast.makeText(FormGearJavascriptInterface.this.activity, "Gagal request versi template dan validasi", 0).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.activity.hideProgressBar();
        }

        /* compiled from: FormGearJavascriptInterface.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2$2", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2$2, reason: invalid class name and collision with other inner class name */
        static final class C02332 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ TemplateValidationEntity $dataTemplateValidationServer;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02332(TemplateValidationEntity templateValidationEntity, Continuation<? super C02332> continuation) {
                super(2, continuation);
                this.$dataTemplateValidationServer = templateValidationEntity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C02332(this.$dataTemplateValidationServer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C02332) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                TemplateValidationRepository templateValidationRepository = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository();
                int formEngineId = this.$dataTemplateValidationServer.getFormEngineId();
                String formEngineBrandName = this.$dataTemplateValidationServer.getFormEngineBrandName();
                Intrinsics.checkNotNull(formEngineBrandName);
                templateValidationRepository.updateData(formEngineId, formEngineBrandName, this.$dataTemplateValidationServer.getSurvey_id(), new Function0<Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.submit.2.2.1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                });
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$4(final FormGearJavascriptInterface this$0, String status) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(status, "$status");
            FormGearActivity formGearActivity = this$0.activity;
            if (formGearActivity != null) {
                BaseClassActivityNew.showAlertDialog$default(formGearActivity, status, "Apakah anda yakin untuk " + status + " assignment ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearJavascriptInterface.AnonymousClass2.invoke$lambda$4$lambda$2(this$0, view);
                    }
                }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$submit$2$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearJavascriptInterface.AnonymousClass2.invoke$lambda$4$lambda$3(view);
                    }
                }, false, false, 384, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$4$lambda$2(FormGearJavascriptInterface this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.onlineSubmit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submit$lambda$61(FormGearJavascriptInterface this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.activity, "Tidak ada koneksi internet", 0).show();
    }

    static /* synthetic */ void saveLogParadata$default(FormGearJavascriptInterface formGearJavascriptInterface, String str, Double d, Double d2, Boolean bool, Function2 function2, int i, Object obj) {
        formGearJavascriptInterface.saveLogParadata(str, (i & 2) != 0 ? null : d, (i & 4) != 0 ? null : d2, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : function2);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveLogParadata$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveLogParadata$1, reason: invalid class name and case insensitive filesystem */
    static final class C08901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $action;
        final /* synthetic */ Boolean $isMockParam;
        final /* synthetic */ Double $latitudeParam;
        final /* synthetic */ Double $longitudeParam;
        final /* synthetic */ Function2<Boolean, String, Unit> $onComplete;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08901(Double d, Double d2, Boolean bool, Function2<? super Boolean, ? super String, Unit> function2, String str, Continuation<? super C08901> continuation) {
            super(2, continuation);
            this.$latitudeParam = d;
            this.$longitudeParam = d2;
            this.$isMockParam = bool;
            this.$onComplete = function2;
            this.$action = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08901(this.$latitudeParam, this.$longitudeParam, this.$isMockParam, this.$onComplete, this.$action, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [T, java.util.ArrayList] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new ArrayList();
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                final int i = (int) ((jCurrentTimeMillis - FormGearJavascriptInterface.this.formSessionStartTime) / 1000);
                FormGearJavascriptInterface.this.formSessionStartTime = jCurrentTimeMillis;
                if (this.$latitudeParam == null && this.$longitudeParam == null && this.$isMockParam == null) {
                    if (ActivityCompat.checkSelfPermission(FormGearJavascriptInterface.this.activity, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(FormGearJavascriptInterface.this.activity, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                        FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
                        final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                        formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveLogParadata$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                FormGearJavascriptInterface.C08901.invokeSuspend$lambda$0(formGearJavascriptInterface);
                            }
                        });
                        Function2<Boolean, String, Unit> function2 = this.$onComplete;
                        if (function2 != null) {
                            function2.invoke(Boxing.boxBoolean(true), null);
                        }
                    } else {
                        GetLocationHelper getLocationHelper = new GetLocationHelper();
                        FormGearActivity formGearActivity2 = FormGearJavascriptInterface.this.activity;
                        Intrinsics.checkNotNull(formGearActivity2);
                        final FormGearJavascriptInterface formGearJavascriptInterface2 = FormGearJavascriptInterface.this;
                        final String str = this.$action;
                        final Function2<Boolean, String, Unit> function22 = this.$onComplete;
                        getLocationHelper.GetLocation(formGearActivity2, new LocCallback() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveLogParadata.1.2
                            @Override // id.go.bpsfasih.utils.helper.location.LocCallback
                            public void result(final Double lat, final Double lng, Float accuracy, final Boolean isMock) {
                                FormGearJavascriptInterface formGearJavascriptInterface3 = formGearJavascriptInterface2;
                                final int i2 = i;
                                final Ref.ObjectRef<List<ActionLog>> objectRef2 = objectRef;
                                final String str2 = str;
                                final FormGearJavascriptInterface formGearJavascriptInterface4 = formGearJavascriptInterface2;
                                final Function2<Boolean, String, Unit> function23 = function22;
                                formGearJavascriptInterface3.getParadata(new Function1<ParadataModel, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveLogParadata$1$2$result$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(ParadataModel paradataModel) {
                                        invoke2(paradataModel);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(ParadataModel paradataModel) {
                                        int iIntValue;
                                        if (paradataModel != null && paradataModel.getTotalDuration() != null) {
                                            Integer totalDuration = paradataModel.getTotalDuration();
                                            Intrinsics.checkNotNull(totalDuration);
                                            iIntValue = totalDuration.intValue() + i2;
                                        } else {
                                            iIntValue = i2;
                                        }
                                        if (paradataModel != null) {
                                            formGearJavascriptInterface4.appendExistingActionLogs(objectRef2.element, paradataModel);
                                            objectRef2.element.add(new ActionLog(String.valueOf(System.currentTimeMillis()), str2, lat, lng, isMock, FasihApp.INSTANCE.getSession().getUserId(), FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME()), BatteryInfoHelper.INSTANCE.getPerformanceMetrics(formGearJavascriptInterface4.activity)));
                                        } else {
                                            objectRef2.element.add(new ActionLog(String.valueOf(System.currentTimeMillis()), str2, lat, lng, isMock, FasihApp.INSTANCE.getSession().getUserId(), FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME()), BatteryInfoHelper.INSTANCE.getPerformanceMetrics(formGearJavascriptInterface4.activity)));
                                        }
                                        ParadataModel paradataModel2 = new ParadataModel(null, formGearJavascriptInterface4.getFormGearVersion(), DeviceInfoHelper.INSTANCE.getDeviceInfo(), "", SignalInfoHelper.INSTANCE.getSignalHelper(formGearJavascriptInterface4.activity), MemoryInfoHelper.INSTANCE.getMemoryInfo(formGearJavascriptInterface4.activity), StorageInfoHelper.INSTANCE.getStorageInfo(), objectRef2.element, Integer.valueOf(iIntValue), paradataModel != null ? paradataModel.getEncryptionType() : null);
                                        Log.d(">>> Duration : ", new StringBuilder().append(iIntValue).toString());
                                        FormGearJavascriptInterface formGearJavascriptInterface5 = formGearJavascriptInterface4;
                                        final Function2<Boolean, String, Unit> function24 = function23;
                                        formGearJavascriptInterface5.updateParadata(paradataModel2, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$saveLogParadata$1$2$result$1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str3) {
                                                invoke(bool.booleanValue(), str3);
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(boolean z, String str3) {
                                                Function2<Boolean, String, Unit> function25 = function24;
                                                if (function25 != null) {
                                                    function25.invoke(Boolean.valueOf(z), str3);
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                } else {
                    FormGearJavascriptInterface formGearJavascriptInterface3 = FormGearJavascriptInterface.this;
                    final String str2 = this.$action;
                    final Double d = this.$latitudeParam;
                    final Double d2 = this.$longitudeParam;
                    final Boolean bool = this.$isMockParam;
                    final FormGearJavascriptInterface formGearJavascriptInterface4 = FormGearJavascriptInterface.this;
                    final Function2<Boolean, String, Unit> function23 = this.$onComplete;
                    formGearJavascriptInterface3.getParadata(new Function1<ParadataModel, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveLogParadata.1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ParadataModel paradataModel) {
                            invoke2(paradataModel);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ParadataModel paradataModel) {
                            int iIntValue;
                            if (paradataModel != null && paradataModel.getTotalDuration() != null) {
                                Integer totalDuration = paradataModel.getTotalDuration();
                                Intrinsics.checkNotNull(totalDuration);
                                iIntValue = totalDuration.intValue() + i;
                            } else {
                                iIntValue = i;
                            }
                            if (paradataModel != null) {
                                formGearJavascriptInterface4.appendExistingActionLogs(objectRef.element, paradataModel);
                                objectRef.element.add(new ActionLog(String.valueOf(System.currentTimeMillis()), str2, d, d2, bool, FasihApp.INSTANCE.getSession().getUserId(), FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME()), BatteryInfoHelper.INSTANCE.getPerformanceMetrics(formGearJavascriptInterface4.activity)));
                            } else {
                                objectRef.element.add(new ActionLog(String.valueOf(System.currentTimeMillis()), str2, d, d2, bool, FasihApp.INSTANCE.getSession().getUserId(), FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME()), BatteryInfoHelper.INSTANCE.getPerformanceMetrics(formGearJavascriptInterface4.activity)));
                            }
                            ParadataModel paradataModel2 = new ParadataModel(null, formGearJavascriptInterface4.getFormGearVersion(), DeviceInfoHelper.INSTANCE.getDeviceInfo(), "", SignalInfoHelper.INSTANCE.getSignalHelper(formGearJavascriptInterface4.activity), MemoryInfoHelper.INSTANCE.getMemoryInfo(formGearJavascriptInterface4.activity), StorageInfoHelper.INSTANCE.getStorageInfo(), objectRef.element, Integer.valueOf(iIntValue), paradataModel != null ? paradataModel.getEncryptionType() : null);
                            Log.d(">>> Duration : ", new StringBuilder().append(iIntValue).toString());
                            FormGearJavascriptInterface formGearJavascriptInterface5 = formGearJavascriptInterface4;
                            final Function2<Boolean, String, Unit> function24 = function23;
                            formGearJavascriptInterface5.updateParadata(paradataModel2, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.saveLogParadata.1.3.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool2, String str3) {
                                    invoke(bool2.booleanValue(), str3);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(boolean z, String str3) {
                                    Function2<Boolean, String, Unit> function25 = function24;
                                    if (function25 != null) {
                                        function25.invoke(Boolean.valueOf(z), str3);
                                    }
                                }
                            });
                        }
                    });
                }
            } catch (Exception e) {
                CrashHandler.INSTANCE.sendExeption(e);
                Function2<Boolean, String, Unit> function24 = this.$onComplete;
                if (function24 != null) {
                    Boolean boolBoxBoolean = Boxing.boxBoolean(false);
                    String message = e.getMessage();
                    if (message == null) {
                        message = "Gagal menyimpan paradata";
                    }
                    function24.invoke(boolBoxBoolean, message);
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FormGearJavascriptInterface formGearJavascriptInterface) {
            Toast.makeText(formGearJavascriptInterface.activity, "Location Permission tidak aktif, aktifkan Location Permission", 0).show();
        }
    }

    private final void saveLogParadata(String action, Double latitudeParam, Double longitudeParam, Boolean isMockParam, Function2<? super Boolean, ? super String, Unit> onComplete) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C08901(latitudeParam, longitudeParam, isMockParam, onComplete, action, null), 2, null);
    }

    public final String getFormGearVersion() {
        try {
            return FormEngineHelper.INSTANCE.getFormEngineVersion(CommonCons.FORMGEAR_ID_DUMMY);
        } catch (JSONException unused) {
            return "";
        }
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getParadata$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {2168}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$getParadata$1, reason: invalid class name and case insensitive filesystem */
    static final class C08801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<ParadataModel, Unit> $callback;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08801(Function1<? super ParadataModel, Unit> function1, Continuation<? super C08801> continuation) {
            super(2, continuation);
            this.$callback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08801(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FormGearJavascriptInterface formGearJavascriptInterface;
            Gson gson;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    formGearJavascriptInterface = FormGearJavascriptInterface.this;
                    Gson gson2 = new Gson();
                    AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                    String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                    String userId = FasihApp.INSTANCE.getSession().getUserId();
                    this.L$0 = formGearJavascriptInterface;
                    this.L$1 = gson2;
                    this.label = 1;
                    Object assignmentByIdPrimary = assignmentRepository.getAssignmentByIdPrimary(mAssignmentId + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, this);
                    if (assignmentByIdPrimary == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    gson = gson2;
                    obj = assignmentByIdPrimary;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    gson = (Gson) this.L$1;
                    formGearJavascriptInterface = (FormGearJavascriptInterface) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                AssignmentEntity assignment = ((Sync) obj).getAssignment();
                formGearJavascriptInterface.setParadata((ParadataModel) gson.fromJson(assignment != null ? assignment.getParadata() : null, ParadataModel.class));
                if (FormGearJavascriptInterface.this.getParadata() != null) {
                    this.$callback.invoke(FormGearJavascriptInterface.this.getParadata());
                } else {
                    this.$callback.invoke(null);
                }
            } catch (Exception e) {
                CrashHandler.INSTANCE.sendExeption(e);
                this.$callback.invoke(null);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getParadata(Function1<? super ParadataModel, Unit> callback) {
        try {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C08801(callback, null), 3, null);
        } catch (Exception e) {
            CrashHandler.INSTANCE.sendExeption(e);
            callback.invoke(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void updateParadata$default(FormGearJavascriptInterface formGearJavascriptInterface, ParadataModel paradataModel, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        formGearJavascriptInterface.updateParadata(paradataModel, function2);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$updateParadata$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {2198, 2203}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$updateParadata$1, reason: invalid class name and case insensitive filesystem */
    static final class C08941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<Boolean, String, Unit> $onComplete;
        final /* synthetic */ ParadataModel $paradata;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08941(ParadataModel paradataModel, Function2<? super Boolean, ? super String, Unit> function2, Continuation<? super C08941> continuation) {
            super(2, continuation);
            this.$paradata = paradataModel;
            this.$onComplete = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08941(this.$paradata, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                    FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
                    String mAssignmentId = formGearActivity != null ? formGearActivity.getMAssignmentId() : null;
                    Intrinsics.checkNotNull(mAssignmentId);
                    assignmentRepository.updateParadata(mAssignmentId + InternalZipConstants.ZIP_FILE_SEPARATOR + FasihApp.INSTANCE.getSession().getUserId(), new Gson().toJson(this.$paradata).toString());
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new C02341(this.$onComplete, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1 && i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                CrashHandler.INSTANCE.sendExeption(e);
                this.label = 2;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.$onComplete, e, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }

        /* compiled from: FormGearJavascriptInterface.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$updateParadata$1$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$updateParadata$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function2<Boolean, String, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C02341(Function2<? super Boolean, ? super String, Unit> function2, Continuation<? super C02341> continuation) {
                super(2, continuation);
                this.$onComplete = function2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C02341(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C02341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Function2<Boolean, String, Unit> function2 = this.$onComplete;
                if (function2 == null) {
                    return null;
                }
                function2.invoke(Boxing.boxBoolean(true), null);
                return Unit.INSTANCE;
            }
        }

        /* compiled from: FormGearJavascriptInterface.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$updateParadata$1$2", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$updateParadata$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Exception $e;
            final /* synthetic */ Function2<Boolean, String, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function2<? super Boolean, ? super String, Unit> function2, Exception exc, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$onComplete = function2;
                this.$e = exc;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$onComplete, this.$e, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Function2<Boolean, String, Unit> function2 = this.$onComplete;
                if (function2 == null) {
                    return null;
                }
                Boolean boolBoxBoolean = Boxing.boxBoolean(false);
                String message = this.$e.getMessage();
                if (message == null) {
                    message = "Gagal menyimpan paradata";
                }
                function2.invoke(boolBoxBoolean, message);
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateParadata(ParadataModel paradata, Function2<? super Boolean, ? super String, Unit> onComplete) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C08941(paradata, onComplete, null), 3, null);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {2213}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1, reason: invalid class name and case insensitive filesystem */
    static final class C08851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08851(Continuation<? super C08851> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08851(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AssignmentUploadEntity assignmentUploadEntity;
            AssignmentEntity assignment;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = FormGearJavascriptInterface.this.createAssignmentUploadEntity(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                assignmentUploadEntity = (AssignmentUploadEntity) obj;
                AssigmentDAO assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
                String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                Intrinsics.checkNotNull(mAssignmentId);
                assignment = assignmentDao.getAssignmentById(mAssignmentId).getAssignment();
            } catch (Exception e) {
                FormGearActivity formGearActivity = FormGearJavascriptInterface.this.activity;
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.C08851.invokeSuspend$lambda$2(formGearJavascriptInterface, e);
                    }
                });
            }
            if (assignment == null) {
                FormGearActivity formGearActivity2 = FormGearJavascriptInterface.this.activity;
                final FormGearJavascriptInterface formGearJavascriptInterface2 = FormGearJavascriptInterface.this;
                formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineSubmit$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FormGearJavascriptInterface.C08851.invokeSuspend$lambda$0(formGearJavascriptInterface2);
                    }
                });
                return Unit.INSTANCE;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            String mAssignmentId2 = FormGearJavascriptInterface.this.activity.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId2);
            List<String> listListOf = CollectionsKt.listOf(mAssignmentId2 + "_" + jCurrentTimeMillis + ".7z");
            DataSurvey.Upload.INSTANCE.getUploadRepository().insert(assignmentUploadEntity);
            FormGearJavascriptInterface.this.createUploadModel();
            FormGearJavascriptInterface formGearJavascriptInterface3 = FormGearJavascriptInterface.this;
            AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
            String mAssignmentId3 = formGearJavascriptInterface3.activity.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId3);
            String copyFromId = assignment.getCopyFromId();
            String mPeriodeId = formGearJavascriptInterface3.activity.getMPeriodeId();
            Intrinsics.checkNotNull(mPeriodeId);
            assignmentRepositoryImpl.assignmentSubmitS3Presign(mAssignmentId3, copyFromId, mPeriodeId, listListOf, new FormGearJavascriptInterface$onlineSubmit$1$2$1(formGearJavascriptInterface3, listListOf, assignment));
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FormGearJavascriptInterface formGearJavascriptInterface) {
            formGearJavascriptInterface.activity.hideProgressBar();
            Toast.makeText(formGearJavascriptInterface.activity, "Data assignment tidak ditemukan", 0).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$2(FormGearJavascriptInterface formGearJavascriptInterface, Exception exc) {
            formGearJavascriptInterface.activity.hideProgressBar();
            FormGearActivity formGearActivity = formGearJavascriptInterface.activity;
            String message = exc.getMessage();
            if (message == null) {
                message = "Terjadi kesalahan saat submit data";
            }
            Toast.makeText(formGearActivity, message, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onlineSubmit() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08851(null), 2, null);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$approveReject$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$approveReject$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $isApproval;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$isApproval = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new AnonymousClass1(this.$isApproval, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                AssigmentDAO assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
                String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                Intrinsics.checkNotNull(mAssignmentId);
                AssignmentEntity assignment = assignmentDao.getAssignmentById(mAssignmentId).getAssignment();
                AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
                Intrinsics.checkNotNull(assignment);
                boolean z = this.$isApproval;
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                assignmentRepositoryImpl.uploadAssignmentApproval(assignment, z, new Function1<BaseResponseDataUpload, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.approveReject.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                        invoke2(baseResponseDataUpload);
                        return Unit.INSTANCE;
                    }

                    /* compiled from: FormGearJavascriptInterface.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$approveReject$1$1$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$approveReject$1$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C02131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ BaseResponseDataUpload $it;
                        int label;
                        final /* synthetic */ FormGearJavascriptInterface this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C02131(BaseResponseDataUpload baseResponseDataUpload, FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super C02131> continuation) {
                            super(2, continuation);
                            this.$it = baseResponseDataUpload;
                            this.this$0 = formGearJavascriptInterface;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C02131(this.$it, this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C02131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            try {
                                DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateAssignmentAfterUpload(this.$it.getData());
                                FormGearActivity formGearActivity = this.this$0.activity;
                                if (formGearActivity != null) {
                                    final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                    formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$approveReject$1$1$1$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            FormGearJavascriptInterface.AnonymousClass1.C02121.C02131.invokeSuspend$lambda$0(formGearJavascriptInterface);
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                Log.d("FOUR", "onlineSubmit: " + e);
                                FormGearActivity formGearActivity2 = this.this$0.activity;
                                final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
                                formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$approveReject$1$1$1$$ExternalSyntheticLambda1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        FormGearJavascriptInterface.AnonymousClass1.C02121.C02131.invokeSuspend$lambda$1(formGearJavascriptInterface2);
                                    }
                                });
                            }
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invokeSuspend$lambda$0(FormGearJavascriptInterface formGearJavascriptInterface) {
                            FormGearActivity formGearActivity = formGearJavascriptInterface.activity;
                            if (formGearActivity != null) {
                                formGearActivity.hideProgressBar();
                            }
                            FormGearActivity formGearActivity2 = formGearJavascriptInterface.activity;
                            if (formGearActivity2 != null) {
                                formGearActivity2.finish();
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invokeSuspend$lambda$1(FormGearJavascriptInterface formGearJavascriptInterface) {
                            Toast.makeText(formGearJavascriptInterface.activity, "Kesalahan saat update data di perangkat", 0).show();
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                        if (baseResponseDataUpload != null ? Intrinsics.areEqual((Object) baseResponseDataUpload.getSuccess(), (Object) true) : false) {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02131(baseResponseDataUpload, formGearJavascriptInterface, null), 2, null);
                        }
                    }
                });
            } catch (Exception e) {
                Log.d(">> ERROR 2", new Gson().toJson(e));
            }
            return Unit.INSTANCE;
        }
    }

    public final void approveReject(boolean isApproval) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(isApproval, null), 2, null);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$revokeProcess$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$revokeProcess$1, reason: invalid class name and case insensitive filesystem */
    static final class C08881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08881(Continuation<? super C08881> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08881(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                AssigmentDAO assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
                String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                Intrinsics.checkNotNull(mAssignmentId);
                AssignmentEntity assignment = assignmentDao.getAssignmentById(mAssignmentId).getAssignment();
                AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
                Intrinsics.checkNotNull(assignment);
                final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                assignmentRepositoryImpl.revoke(assignment, new Function1<BaseResponseDataUpload, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.revokeProcess.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                        invoke2(baseResponseDataUpload);
                        return Unit.INSTANCE;
                    }

                    /* compiled from: FormGearJavascriptInterface.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$revokeProcess$1$1$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$revokeProcess$1$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C02221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ BaseResponseDataUpload $it;
                        int label;
                        final /* synthetic */ FormGearJavascriptInterface this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C02221(BaseResponseDataUpload baseResponseDataUpload, FormGearJavascriptInterface formGearJavascriptInterface, Continuation<? super C02221> continuation) {
                            super(2, continuation);
                            this.$it = baseResponseDataUpload;
                            this.this$0 = formGearJavascriptInterface;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C02221(this.$it, this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C02221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            try {
                                DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateAssignmentAfterUpload(this.$it.getData());
                                FormGearActivity formGearActivity = this.this$0.activity;
                                if (formGearActivity != null) {
                                    final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                    formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$revokeProcess$1$1$1$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            FormGearJavascriptInterface.C08881.C02211.C02221.invokeSuspend$lambda$0(formGearJavascriptInterface);
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                Log.d("FOUR", "onlineSubmit: " + e);
                                FormGearActivity formGearActivity2 = this.this$0.activity;
                                final FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0;
                                formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$revokeProcess$1$1$1$$ExternalSyntheticLambda1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        FormGearJavascriptInterface.C08881.C02211.C02221.invokeSuspend$lambda$1(formGearJavascriptInterface2);
                                    }
                                });
                            }
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invokeSuspend$lambda$0(FormGearJavascriptInterface formGearJavascriptInterface) {
                            FormGearActivity formGearActivity = formGearJavascriptInterface.activity;
                            if (formGearActivity != null) {
                                formGearActivity.hideProgressBar();
                            }
                            FormGearActivity formGearActivity2 = formGearJavascriptInterface.activity;
                            if (formGearActivity2 != null) {
                                formGearActivity2.finish();
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invokeSuspend$lambda$1(FormGearJavascriptInterface formGearJavascriptInterface) {
                            Toast.makeText(formGearJavascriptInterface.activity, "Kesalahan saat update data di perangkat", 0).show();
                        }
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                        if (baseResponseDataUpload != null ? Intrinsics.areEqual((Object) baseResponseDataUpload.getSuccess(), (Object) true) : false) {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02221(baseResponseDataUpload, formGearJavascriptInterface, null), 2, null);
                        }
                    }
                });
            } catch (Exception e) {
                Log.d(">> ERROR 2", new Gson().toJson(e));
            }
            return Unit.INSTANCE;
        }
    }

    public final void revokeProcess() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08881(null), 2, null);
    }

    /* compiled from: FormGearJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1, reason: invalid class name and case insensitive filesystem */
    static final class C08841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $isApproval;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08841(boolean z, Continuation<? super C08841> continuation) {
            super(2, continuation);
            this.$isApproval = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FormGearJavascriptInterface.this.new C08841(this.$isApproval, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AssigmentDAO assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
            String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId);
            final AssignmentEntity assignment = assignmentDao.getAssignmentById(mAssignmentId).getAssignment();
            long jCurrentTimeMillis = System.currentTimeMillis();
            String mAssignmentId2 = FormGearJavascriptInterface.this.activity.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId2);
            final List<String> listListOf = CollectionsKt.listOf(mAssignmentId2 + "_" + jCurrentTimeMillis + ".7z");
            FormGearJavascriptInterface.this.createUploadModel();
            final FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
            final boolean z = this.$isApproval;
            AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
            String mAssignmentId3 = formGearJavascriptInterface.activity.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId3);
            String copyFromId = assignment != null ? assignment.getCopyFromId() : null;
            String mPeriodeId = formGearJavascriptInterface.activity.getMPeriodeId();
            Intrinsics.checkNotNull(mPeriodeId);
            assignmentRepositoryImpl.assignmentEditS3Presign(mAssignmentId3, copyFromId, mPeriodeId, listListOf, new Function1<AssignmentSubmitS3Response, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AssignmentSubmitS3Response assignmentSubmitS3Response) throws Throwable {
                    invoke2(assignmentSubmitS3Response);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AssignmentSubmitS3Response assignmentSubmitS3Response) throws Throwable {
                    PresignedUrlsItem presignedUrlsItem;
                    Log.d(">>> uload assignment", new Gson().toJson(assignmentSubmitS3Response));
                    if (assignmentSubmitS3Response != null ? Intrinsics.areEqual((Object) assignmentSubmitS3Response.getSuccess(), (Object) true) : false) {
                        Pair pairCreate7zFile = formGearJavascriptInterface.create7zFile();
                        File file = (File) pairCreate7zFile.component1();
                        String str = (String) pairCreate7zFile.component2();
                        AssignmentRepositoryImpl assignmentRepositoryImpl2 = new AssignmentRepositoryImpl();
                        List<PresignedUrlsItem> presignedUrls = assignmentSubmitS3Response.getData().getPresignedUrls();
                        String presignedUrl = (presignedUrls == null || (presignedUrlsItem = presignedUrls.get(0)) == null) ? null : presignedUrlsItem.getPresignedUrl();
                        Intrinsics.checkNotNull(presignedUrl);
                        assignmentRepositoryImpl2.assignmentEditS3Upload(presignedUrl, file, new AnonymousClass1(listListOf, str, assignment, z, formGearJavascriptInterface));
                    }
                }

                /* compiled from: FormGearJavascriptInterface.kt */
                @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1, reason: invalid class name */
                static final class AnonymousClass1 extends Lambda implements Function1<Boolean, Unit> {
                    final /* synthetic */ AssignmentEntity $assignmentEntity;
                    final /* synthetic */ List<String> $fileNames;
                    final /* synthetic */ boolean $isApproval;
                    final /* synthetic */ String $md5;
                    final /* synthetic */ FormGearJavascriptInterface this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(List<String> list, String str, AssignmentEntity assignmentEntity, boolean z, FormGearJavascriptInterface formGearJavascriptInterface) {
                        super(1);
                        this.$fileNames = list;
                        this.$md5 = str;
                        this.$assignmentEntity = assignmentEntity;
                        this.$isApproval = z;
                        this.this$0 = formGearJavascriptInterface;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    /* compiled from: FormGearJavascriptInterface.kt */
                    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "response", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C02191 extends Lambda implements Function1<BaseResponseDataUpload, Unit> {
                        final /* synthetic */ FormGearJavascriptInterface this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C02191(FormGearJavascriptInterface formGearJavascriptInterface) {
                            super(1);
                            this.this$0 = formGearJavascriptInterface;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                            invoke2(baseResponseDataUpload);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(final BaseResponseDataUpload baseResponseDataUpload) {
                            if (baseResponseDataUpload != null ? Intrinsics.areEqual((Object) baseResponseDataUpload.getSuccess(), (Object) true) : false) {
                                FormGearActivity formGearActivity = this.this$0.activity;
                                if (formGearActivity != null) {
                                    final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                    formGearActivity.runOnUiThread(
                                    /*  JADX ERROR: Method code generation error
                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0022: INVOKE 
                                          (r0v5 'formGearActivity' id.go.bpsfasih.ui.formGear.FormGearActivity)
                                          (wrap:java.lang.Runnable:0x001f: CONSTRUCTOR (r1v10 'formGearJavascriptInterface' id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface A[DONT_INLINE]) A[MD:(id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface):void (m), WRAPPED] call: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda0.<init>(id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface):void type: CONSTRUCTOR)
                                         VIRTUAL call: id.go.bpsfasih.ui.formGear.FormGearActivity.runOnUiThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (s)] in method: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.onlineEdit.1.1.1.1.1.invoke(id.go.bpsfasih.data.local.models.BaseResponseDataUpload):void, file: classes2.dex
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:298)
                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:277)
                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                        	... 29 more
                                        */
                                    /*
                                        this = this;
                                        if (r9 == 0) goto L10
                                        java.lang.Boolean r0 = r9.getSuccess()
                                        r1 = 1
                                        java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                                        boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                                        goto L11
                                    L10:
                                        r0 = 0
                                    L11:
                                        if (r0 == 0) goto L78
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r0 = r8.this$0
                                        id.go.bpsfasih.ui.formGear.FormGearActivity r0 = id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.access$getActivity$p(r0)
                                        if (r0 == 0) goto L25
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r1 = r8.this$0
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda0 r2 = new id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda0
                                        r2.<init>(r1)
                                        r0.runOnUiThread(r2)
                                    L25:
                                        r0 = 0
                                        if (r9 == 0) goto L2d
                                        java.lang.String r1 = r9.getMessage()
                                        goto L2e
                                    L2d:
                                        r1 = r0
                                    L2e:
                                        java.lang.String r1 = java.lang.String.valueOf(r1)
                                        java.lang.String r2 = ">>> verification"
                                        android.util.Log.d(r2, r1)
                                        kotlinx.coroutines.GlobalScope r1 = kotlinx.coroutines.GlobalScope.INSTANCE     // Catch: java.lang.Exception -> L52
                                        r2 = r1
                                        kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2     // Catch: java.lang.Exception -> L52
                                        kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Exception -> L52
                                        r3 = r1
                                        kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3     // Catch: java.lang.Exception -> L52
                                        r4 = 0
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$2 r1 = new id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$2     // Catch: java.lang.Exception -> L52
                                        r1.<init>(r9, r0)     // Catch: java.lang.Exception -> L52
                                        r5 = r1
                                        kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch: java.lang.Exception -> L52
                                        r6 = 2
                                        r7 = 0
                                        kotlinx.coroutines.BuildersKt.launch$default(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Exception -> L52
                                        goto L8a
                                    L52:
                                        r9 = move-exception
                                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                                        java.lang.String r1 = "onlineSubmit: "
                                        r0.<init>(r1)
                                        java.lang.StringBuilder r9 = r0.append(r9)
                                        java.lang.String r9 = r9.toString()
                                        java.lang.String r0 = "FOUR"
                                        android.util.Log.d(r0, r9)
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r9 = r8.this$0
                                        id.go.bpsfasih.ui.formGear.FormGearActivity r9 = id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.access$getActivity$p(r9)
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r0 = r8.this$0
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda1 r1 = new id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda1
                                        r1.<init>(r0)
                                        r9.runOnUiThread(r1)
                                        goto L8a
                                    L78:
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r0 = r8.this$0
                                        id.go.bpsfasih.ui.formGear.FormGearActivity r0 = id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.access$getActivity$p(r0)
                                        if (r0 == 0) goto L8a
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r1 = r8.this$0
                                        id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda2 r2 = new id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$$ExternalSyntheticLambda2
                                        r2.<init>(r1, r9)
                                        r0.runOnUiThread(r2)
                                    L8a:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1.AnonymousClass1.C02191.invoke2(id.go.bpsfasih.data.local.models.BaseResponseDataUpload):void");
                                }

                                /* JADX INFO: Access modifiers changed from: private */
                                public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
                                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                                    FormGearActivity formGearActivity = this$0.activity;
                                    if (formGearActivity != null) {
                                        formGearActivity.hideProgressBar();
                                    }
                                    FormGearActivity formGearActivity2 = this$0.activity;
                                    if (formGearActivity2 != null) {
                                        formGearActivity2.finish();
                                    }
                                }

                                /* compiled from: FormGearJavascriptInterface.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$2", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1$2, reason: invalid class name */
                                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ BaseResponseDataUpload $response;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    AnonymousClass2(BaseResponseDataUpload baseResponseDataUpload, Continuation<? super AnonymousClass2> continuation) {
                                        super(2, continuation);
                                        this.$response = baseResponseDataUpload;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new AnonymousClass2(this.$response, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        if (this.label != 0) {
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        ResultKt.throwOnFailure(obj);
                                        DataSurvey.Assignment.INSTANCE.getAssignmentRepository().updateAssignmentAfterUpload(this.$response.getData());
                                        return Unit.INSTANCE;
                                    }
                                }

                                /* JADX INFO: Access modifiers changed from: private */
                                public static final void invoke$lambda$1(FormGearJavascriptInterface this$0) {
                                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                                    Toast.makeText(this$0.activity, "Kesalahan saat update data di perangkat", 0).show();
                                }

                                /* JADX INFO: Access modifiers changed from: private */
                                public static final void invoke$lambda$2(FormGearJavascriptInterface this$0, BaseResponseDataUpload baseResponseDataUpload) {
                                    String message;
                                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                                    FormGearActivity formGearActivity = this$0.activity;
                                    if (formGearActivity != null) {
                                        formGearActivity.hideProgressBar();
                                    }
                                    FormGearActivity formGearActivity2 = this$0.activity;
                                    if (baseResponseDataUpload == null || (message = baseResponseDataUpload.getMessage()) == null) {
                                        message = "Gagal mengirimkan edit";
                                    }
                                    Toast.makeText(formGearActivity2, message, 0).show();
                                }
                            }

                            public final void invoke(boolean z) {
                                if (!z) {
                                    FormGearActivity formGearActivity = this.this$0.activity;
                                    if (formGearActivity != null) {
                                        final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                        formGearActivity.runOnUiThread(
                                        /*  JADX ERROR: Method code generation error
                                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00e0: INVOKE 
                                              (r1v1 'formGearActivity' id.go.bpsfasih.ui.formGear.FormGearActivity)
                                              (wrap:java.lang.Runnable:0x00dd: CONSTRUCTOR (r2v0 'formGearJavascriptInterface' id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface A[DONT_INLINE]) A[MD:(id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface):void (m), WRAPPED] call: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$$ExternalSyntheticLambda0.<init>(id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface):void type: CONSTRUCTOR)
                                             VIRTUAL call: id.go.bpsfasih.ui.formGear.FormGearActivity.runOnUiThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (s)] in method: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1.1.invoke(boolean):void, file: classes2.dex
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:298)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:277)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                            	... 31 more
                                            */
                                        /*
                                            this = this;
                                            r0 = r27
                                            if (r28 == 0) goto Ld1
                                            id.go.bpsfasih.data.repository.AssignmentRepositoryImpl r1 = new id.go.bpsfasih.data.repository.AssignmentRepositoryImpl
                                            r1.<init>()
                                            java.util.List<java.lang.String> r2 = r0.$fileNames
                                            r3 = 0
                                            java.lang.Object r2 = r2.get(r3)
                                            java.lang.String r2 = (java.lang.String) r2
                                            java.lang.String r3 = r0.$md5
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r4 = r0.$assignmentEntity
                                            if (r4 == 0) goto L1d
                                            java.lang.String r4 = r4.getPeriodeNotPrimary()
                                            goto L1e
                                        L1d:
                                            r4 = 0
                                        L1e:
                                            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r6 = r0.$assignmentEntity
                                            java.lang.String r6 = r6.getId()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r7 = r0.$assignmentEntity
                                            boolean r7 = r7.isNew()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r8 = r0.$assignmentEntity
                                            id.go.bpsfasih.data.local.entities.Region r8 = r8.getRegion()
                                            if (r8 == 0) goto L3a
                                            java.lang.String r8 = r8.getId()
                                            goto L3b
                                        L3a:
                                            r8 = 0
                                        L3b:
                                            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r9 = r0.$assignmentEntity
                                            java.lang.String r9 = r9.getData1()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r10 = r0.$assignmentEntity
                                            java.lang.String r10 = r10.getData2()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r11 = r0.$assignmentEntity
                                            java.lang.String r11 = r11.getData3()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r12 = r0.$assignmentEntity
                                            java.lang.String r12 = r12.getData4()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r13 = r0.$assignmentEntity
                                            java.lang.String r13 = r13.getData5()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r14 = r0.$assignmentEntity
                                            java.lang.String r14 = r14.getData6()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r15 = r0.$assignmentEntity
                                            java.lang.String r15 = r15.getData7()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.String r16 = r5.getData8()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.String r17 = r5.getData9()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.String r18 = r5.getData10()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.Float r19 = r5.getLatitude()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.Float r20 = r5.getLongitude()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            if (r5 == 0) goto L91
                                            java.lang.String r5 = r5.getCopyFromId()
                                            r26 = r5
                                            goto L93
                                        L91:
                                            r26 = 0
                                        L93:
                                            boolean r5 = r0.$isApproval
                                            r21 = r5
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.String r22 = r5.getParadata()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.String r23 = r5.getComment()
                                            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = r0.$assignmentEntity
                                            java.lang.String r24 = r5.getNote()
                                            id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1 r5 = new id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$1
                                            r28 = r15
                                            id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r15 = r0.this$0
                                            r5.<init>(r15)
                                            r25 = r5
                                            kotlin.jvm.functions.Function1 r25 = (kotlin.jvm.functions.Function1) r25
                                            r5 = r6
                                            r6 = r7
                                            r7 = r8
                                            r8 = r9
                                            r9 = r10
                                            r10 = r11
                                            r11 = r12
                                            r12 = r13
                                            r13 = r14
                                            r14 = r28
                                            r15 = r16
                                            r16 = r17
                                            r17 = r18
                                            r18 = r19
                                            r19 = r20
                                            r20 = r26
                                            r1.assignmentEditS3Post(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
                                            goto Le3
                                        Ld1:
                                            id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r1 = r0.this$0
                                            id.go.bpsfasih.ui.formGear.FormGearActivity r1 = id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.access$getActivity$p(r1)
                                            if (r1 == 0) goto Le3
                                            id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface r2 = r0.this$0
                                            id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$$ExternalSyntheticLambda0 r3 = new id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1$1$$ExternalSyntheticLambda0
                                            r3.<init>(r2)
                                            r1.runOnUiThread(r3)
                                        Le3:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$onlineEdit$1$1$1.AnonymousClass1.invoke(boolean):void");
                                    }

                                    /* JADX INFO: Access modifiers changed from: private */
                                    public static final void invoke$lambda$0(FormGearJavascriptInterface this$0) {
                                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                                        FormGearActivity formGearActivity = this$0.activity;
                                        if (formGearActivity != null) {
                                            formGearActivity.hideProgressBar();
                                        }
                                        Toast.makeText(this$0.activity, "Gagal upload data", 0).show();
                                    }
                                }
                            });
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public final void onlineEdit(boolean isApproval) {
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08841(isApproval, null), 2, null);
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    /* JADX WARN: Removed duplicated region for block: B:38:0x00aa  */
                    /* JADX WARN: Removed duplicated region for block: B:39:0x00b0  */
                    /* JADX WARN: Removed duplicated region for block: B:42:0x00db  */
                    /* JADX WARN: Removed duplicated region for block: B:45:0x00e8  */
                    /* JADX WARN: Removed duplicated region for block: B:47:0x00ec  */
                    /* JADX WARN: Removed duplicated region for block: B:50:0x00f9  */
                    /* JADX WARN: Removed duplicated region for block: B:52:0x00fd  */
                    /* JADX WARN: Removed duplicated region for block: B:55:0x010a  */
                    /* JADX WARN: Removed duplicated region for block: B:57:0x010e  */
                    /* JADX WARN: Removed duplicated region for block: B:60:0x011b  */
                    /* JADX WARN: Removed duplicated region for block: B:62:0x011f  */
                    /* JADX WARN: Removed duplicated region for block: B:65:0x012c  */
                    /* JADX WARN: Removed duplicated region for block: B:67:0x0130  */
                    /* JADX WARN: Removed duplicated region for block: B:70:0x013d  */
                    /* JADX WARN: Removed duplicated region for block: B:72:0x0141  */
                    /* JADX WARN: Removed duplicated region for block: B:75:0x014e  */
                    /* JADX WARN: Removed duplicated region for block: B:77:0x0152  */
                    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
                    /* JADX WARN: Removed duplicated region for block: B:80:0x015f  */
                    /* JADX WARN: Removed duplicated region for block: B:82:0x0163  */
                    /* JADX WARN: Removed duplicated region for block: B:85:0x0170  */
                    /* JADX WARN: Removed duplicated region for block: B:87:0x0174  */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object createAssignmentUploadEntity(kotlin.coroutines.Continuation<? super id.go.bpsfasih.data.local.entities.AssignmentUploadEntity> r36) {
                        /*
                            Method dump skipped, instructions count: 398
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface.createAssignmentUploadEntity(kotlin.coroutines.Continuation):java.lang.Object");
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public final UploadModel createUploadModel() {
                        File fileCreateArchive = DataFormArchiveHelper.INSTANCE.createArchive(createArchiveRequest(), new FormGearJavascriptInterface$createUploadModel$file$1(this));
                        if (fileCreateArchive == null) {
                            return null;
                        }
                        if (!fileCreateArchive.exists()) {
                            return null;
                        }
                        String path = fileCreateArchive.getPath();
                        String name = fileCreateArchive.getName();
                        long length = fileCreateArchive.length() + 1;
                        FormGearActivity formGearActivity = this.activity;
                        String mAssignmentId = formGearActivity != null ? formGearActivity.getMAssignmentId() : null;
                        FormGearActivity formGearActivity2 = this.activity;
                        String mAssignmentId2 = formGearActivity2 != null ? formGearActivity2.getMAssignmentId() : null;
                        String str = this.userId;
                        FormGearActivity formGearActivity3 = this.activity;
                        return new UploadModel(null, path, name, 0L, length, mAssignmentId, formGearActivity3 != null ? formGearActivity3.getMPeriodeId() : null, mAssignmentId2, null, 0, str, 0, null, null, 15105, null);
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public final String getValuePrincipal(JsonObject jsonObject) {
                        JsonElement jsonElement = jsonObject.get("answer");
                        if (jsonElement.isJsonArray()) {
                            String asString = jsonElement.getAsJsonArray().get(0).getAsJsonObject().get(Constants.ScionAnalytics.PARAM_LABEL).getAsString();
                            Intrinsics.checkNotNullExpressionValue(asString, "{\n            json.asJso…abel\").asString\n        }");
                            return asString;
                        }
                        String asString2 = jsonElement.getAsString();
                        Intrinsics.checkNotNullExpressionValue(asString2, "{\n            json.asString\n        }");
                        return asString2;
                    }

                    @JavascriptInterface
                    public final void s3refreshUrl(String json) {
                        Intrinsics.checkNotNullParameter(json, "json");
                        if (getFilenamesFromJsonFormGear(json).size() != 0) {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C08891(null), 2, null);
                        }
                    }

                    /* compiled from: FormGearJavascriptInterface.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$s3refreshUrl$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$s3refreshUrl$1, reason: invalid class name and case insensitive filesystem */
                    static final class C08891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        int label;

                        C08891(Continuation<? super C08891> continuation) {
                            super(2, continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return FormGearJavascriptInterface.this.new C08891(continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C08891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            try {
                                AssigmentDAO assignmentDao = FasihApp.INSTANCE.getAssignmentDao();
                                String mAssignmentId = FormGearJavascriptInterface.this.activity.getMAssignmentId();
                                Intrinsics.checkNotNull(mAssignmentId);
                                assignmentDao.getAssignmentById(mAssignmentId).getAssignment();
                            } catch (Exception unused) {
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @JavascriptInterface
                    public final String getRolePetugas() {
                        return (String) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearJavascriptInterface$getRolePetugas$rolePetugas$1(this, null), 1, null);
                    }

                    @JavascriptInterface
                    public final String getUserRole() {
                        return (String) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearJavascriptInterface$getUserRole$rolePetugas$1(this, null), 1, null);
                    }

                    /* compiled from: FormGearJavascriptInterface.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$connectorApi$1", f = "FormGearJavascriptInterface.kt", i = {}, l = {2644}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$connectorApi$1, reason: invalid class name and case insensitive filesystem */
                    static final class C08741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
                        final /* synthetic */ String $name;
                        final /* synthetic */ String $param;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C08741(String str, String str2, Continuation<? super C08741> continuation) {
                            super(2, continuation);
                            this.$param = str;
                            this.$name = str2;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return FormGearJavascriptInterface.this.new C08741(this.$param, this.$name, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
                            return ((C08741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            try {
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    String newParam = new Gson().toJson(MapsKt.mapOf(TuplesKt.to("assignmentId", FormGearJavascriptInterface.this.activity.getMAssignmentId()), TuplesKt.to("body", new Gson().fromJson(this.$param, new TypeToken<Map<String, ? extends Object>>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$connectorApi$1$requestMap$1
                                    }.getType()))));
                                    FormGearJavascriptInterface formGearJavascriptInterface = FormGearJavascriptInterface.this;
                                    String str = this.$name;
                                    RequestBody.Companion companion = RequestBody.INSTANCE;
                                    Intrinsics.checkNotNullExpressionValue(newParam, "newParam");
                                    this.label = 1;
                                    obj = formGearJavascriptInterface.hitConnectorApiSuspend(str, RequestBody.Companion.create$default(companion, newParam, (MediaType) null, 1, (Object) null), this);
                                    if (obj == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                return (String) obj;
                            } catch (Exception e) {
                                Log.e(">>>>", "Error: ", e);
                                return "{\"success\":false,\"message\":\"" + e.getMessage() + "\"}";
                            }
                        }
                    }

                    @JavascriptInterface
                    public final String connectorApi(String name, String param) {
                        Intrinsics.checkNotNullParameter(name, "name");
                        Intrinsics.checkNotNullParameter(param, "param");
                        return (String) BuildersKt__BuildersKt.runBlocking$default(null, new C08741(param, name, null), 1, null);
                    }

                    @JavascriptInterface
                    public final void sendEvent(String param) throws JSONException {
                        Intrinsics.checkNotNullParameter(param, "param");
                        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("form_engine_send_event")) {
                            FirebaseAnalitycHelper.Companion companion = FirebaseAnalitycHelper.INSTANCE;
                            FormGearActivity formGearActivity = this.activity;
                            FormGearActivity formGearActivity2 = formGearActivity;
                            String mAssignmentId = formGearActivity.getMAssignmentId();
                            if (mAssignmentId == null) {
                                mAssignmentId = "0";
                            }
                            companion.sendFormEngineEvent(formGearActivity2, mAssignmentId, param);
                        }
                    }

                    public final boolean isJsonFormat(String str) {
                        try {
                            try {
                                new JSONObject(str);
                                return true;
                            } catch (JSONException unused) {
                                new JSONArray(str);
                                return true;
                            }
                        } catch (JSONException unused2) {
                            return false;
                        }
                    }

                    private final void insertAssignmentEncrypt(Encryption encryption) throws IOException {
                        try {
                            String str = Directory.INSTANCE.getABSOLUTEPATHENV() + File.separator + "encryption" + CommonCons.INSTANCE.getEXTENSION_JSON();
                            File file = new File(Directory.INSTANCE.getABSOLUTEPATHENV(), "");
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            File file2 = new File(str);
                            if (!file2.exists()) {
                                try {
                                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                                    outputStreamWriter.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                                    outputStreamWriter.flush();
                                    outputStreamWriter.close();
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e2) {
                                    CrashHandler.INSTANCE.sendExeption(e2);
                                    e2.printStackTrace();
                                }
                                file2.createNewFile();
                            }
                            Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file2, null, 1, null), (Class<Object>) Encryption[].class);
                            Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…<Encryption>::class.java)");
                            List mutableList = ArraysKt.toMutableList((Object[]) objFromJson);
                            mutableList.add(encryption);
                            String jsonString = new Gson().toJson(mutableList);
                            FileHelper.Companion companion = FileHelper.INSTANCE;
                            String absolutepathenv = Directory.INSTANCE.getABSOLUTEPATHENV();
                            String str2 = "encryption" + CommonCons.INSTANCE.getEXTENSION_JSON();
                            Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
                            companion.writeFile(absolutepathenv, str2, jsonString);
                        } catch (Exception unused) {
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public final Pair<File, String> create7zFile() throws Throwable {
                        DataFormArchiveResult dataFormArchiveResultCreateArchiveWithChecksum = DataFormArchiveHelper.INSTANCE.createArchiveWithChecksum(createArchiveRequest(), new FormGearJavascriptInterface$create7zFile$result$1(this));
                        if (dataFormArchiveResultCreateArchiveWithChecksum == null) {
                            throw new IOException("Failed to create 7z file for assignment " + this.activity.getMAssignmentId());
                        }
                        return TuplesKt.to(dataFormArchiveResultCreateArchiveWithChecksum.getArchiveFile(), dataFormArchiveResultCreateArchiveWithChecksum.getMd5Checksum());
                    }

                    private final DataFormArchiveRequest createArchiveRequest() {
                        String mAssignmentId = this.activity.getMAssignmentId();
                        String str = mAssignmentId == null ? "" : mAssignmentId;
                        File file = new File(this.activity.getAnswerPath());
                        FileHelper.Companion companion = FileHelper.INSTANCE;
                        String surveyId = this.activity.getSurveyId();
                        if (surveyId == null) {
                            surveyId = "";
                        }
                        String mPeriodeId = this.activity.getMPeriodeId();
                        return new DataFormArchiveRequest(str, file, new File(companion.pathTempAnswer(surveyId, mPeriodeId != null ? mPeriodeId : "")), null, 8, null);
                    }

                    public final TemplateValidasiVersionModel getDataTemplateValidationVersion() {
                        String asString;
                        try {
                            Reader inputStreamReader = new InputStreamReader(new FileInputStream(new File(FileHelper.INSTANCE.pathTemplate(this.activity.getTemplateId()))), Charsets.UTF_8);
                            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                            try {
                                String text = TextStreamsKt.readText(bufferedReader);
                                CloseableKt.closeFinally(bufferedReader, null);
                                asString = new JsonParser().parse(text).getAsJsonObject().get(ClientCookie.VERSION_ATTR).getAsString();
                            } finally {
                            }
                            try {
                                Reader inputStreamReader2 = new InputStreamReader(new FileInputStream(new File(FileHelper.INSTANCE.pathValidation(this.activity.getTemplateId()))), Charsets.UTF_8);
                                bufferedReader = inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, 8192);
                                try {
                                    String text2 = TextStreamsKt.readText(bufferedReader);
                                    CloseableKt.closeFinally(bufferedReader, null);
                                    return new TemplateValidasiVersionModel(this.activity.getTemplateId(), asString, new JsonParser().parse(text2).getAsJsonObject().get(ClientCookie.VERSION_ATTR).getAsString());
                                } finally {
                                }
                            } catch (JsonParseException e) {
                                Log.d("FOUR", "getDataTemplateValidationVersion: " + e);
                                return null;
                            } catch (IOException unused) {
                                return null;
                            }
                        } catch (JsonParseException e2) {
                            Log.d("FOUR", "getDataTemplateValidationVersion: " + e2);
                            return null;
                        } catch (IOException unused2) {
                            return null;
                        }
                    }

                    public final String md5Base64OfFile(File file) throws NoSuchAlgorithmException {
                        Intrinsics.checkNotNullParameter(file, "file");
                        byte[] bArr = new byte[4096];
                        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            FileInputStream fileInputStream2 = fileInputStream;
                            while (true) {
                                int i = fileInputStream2.read(bArr);
                                if (i != -1) {
                                    messageDigest.update(bArr, 0, i);
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(fileInputStream, null);
                                    String strEncodeToString = Base64.encodeToString(messageDigest.digest(), 2);
                                    Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(md5Bytes,…roid.util.Base64.NO_WRAP)");
                                    return strEncodeToString;
                                }
                            }
                        } finally {
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public final Object hitConnectorApiSuspend(String str, RequestBody requestBody, Continuation<? super String> continuation) {
                        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
                        cancellableContinuationImpl.initCancellability();
                        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
                        if (Network.INSTANCE.isOnline(this.activity)) {
                            this.activity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$hitConnectorApiSuspend$2$1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.this$0.activity.showProgressBar();
                                }
                            });
                            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                            new ConnectorRepositoryImpl().send(str, requestBody, new Function1<String, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$hitConnectorApiSuspend$2$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                                    invoke2(str2);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(String str2) {
                                    FormGearActivity formGearActivity = this.this$0.activity;
                                    final FormGearJavascriptInterface formGearJavascriptInterface = this.this$0;
                                    formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$hitConnectorApiSuspend$2$2.1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            formGearJavascriptInterface.activity.hideProgressBar();
                                        }
                                    });
                                    if (atomicBoolean.get()) {
                                        return;
                                    }
                                    Log.d(">>>>", "Raw response: " + str2);
                                    if (str2 != null) {
                                        CancellableContinuation<String> cancellableContinuation = cancellableContinuationImpl2;
                                        Result.Companion companion = Result.INSTANCE;
                                        cancellableContinuation.resumeWith(Result.m6852constructorimpl(str2));
                                    } else {
                                        CancellableContinuation<String> cancellableContinuation2 = cancellableContinuationImpl2;
                                        Result.Companion companion2 = Result.INSTANCE;
                                        cancellableContinuation2.resumeWith(Result.m6852constructorimpl("{\"success\":false,\"message\":\"API call failed\"}"));
                                    }
                                }
                            });
                            cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface$hitConnectorApiSuspend$2$3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                    invoke2(th);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Throwable th) {
                                    atomicBoolean.set(true);
                                }
                            });
                        } else {
                            Result.Companion companion = Result.INSTANCE;
                            cancellableContinuationImpl2.resumeWith(Result.m6852constructorimpl("{\"error\":\"No internet connection\",\"status\":0}"));
                        }
                        Object result = cancellableContinuationImpl.getResult();
                        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            DebugProbesKt.probeCoroutineSuspended(continuation);
                        }
                        return result;
                    }
                }

package id.go.bpsfasih.ui.formGear;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.internal.runner.listener.InstrumentationResultPrinter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.nambimobile.widgets.efab.ExpandableFab;
import com.nambimobile.widgets.efab.FabOption;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.calculator.CalculatorCallback;
import id.go.bpsfasih.calculator.CalculatorDialog;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.Region;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import id.go.bpsfasih.data.local.models.AssignmentSubmitS3Response;
import id.go.bpsfasih.data.local.models.BaseResponseDataStringApproval;
import id.go.bpsfasih.data.local.models.BaseResponseDataUpload;
import id.go.bpsfasih.data.local.models.PresignedUrlsItem;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.local.repository.PeriodeRepository;
import id.go.bpsfasih.data.local.repository.TemplateValidationRepository;
import id.go.bpsfasih.data.localserver.Server;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.domain.models.FormEngineLogErrorModel;
import id.go.bpsfasih.domain.models.LocationHistory;
import id.go.bpsfasih.ui.bantuan.BantuanActivity;
import id.go.bpsfasih.ui.formGear.FormGearActivity;
import id.go.bpsfasih.ui.map.MapUbinanActivity;
import id.go.bpsfasih.ui.sistem.SistemActivity;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.AssignmentEncryptionHelper;
import id.go.bpsfasih.utils.helper.CameraHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.FirestoreHelper;
import id.go.bpsfasih.utils.helper.FormEngineHelper;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.helper.TemplateHelper;
import id.go.bpsfasih.utils.helper.ValidationHelper;
import io.github.hyuwah.draggableviewlib.DraggableUtils;
import io.github.hyuwah.draggableviewlib.DraggableView;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: FormGearActivity.kt */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 ¸\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002¸\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u001e\u0010s\u001a\u000e\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020\u00070t2\b\u0010v\u001a\u0004\u0018\u00010wH\u0002J\u0006\u0010x\u001a\u00020yJ\b\u0010z\u001a\u00020\u0007H\u0002J\b\u0010{\u001a\u0004\u0018\u00010\u0007J\b\u0010|\u001a\u0004\u0018\u00010\u0007J\b\u0010}\u001a\u0004\u0018\u00010\u0007J2\u0010~\u001a\u00020y2(\u0010\u007f\u001a$\u0012\u0019\u0012\u0017\u0018\u00010\u0081\u0001¢\u0006\u000f\b\u0082\u0001\u0012\n\b\u0083\u0001\u0012\u0005\b\b(\u0084\u0001\u0012\u0004\u0012\u00020y0\u0080\u0001H\u0002J\t\u0010\u0085\u0001\u001a\u00020yH\u0002J\t\u0010\u0086\u0001\u001a\u00020\u0010H\u0002J0\u0010\u0087\u0001\u001a\u00020y2%\u0010\u007f\u001a!\u0012\u0016\u0012\u00140\u0010¢\u0006\u000f\b\u0082\u0001\u0012\n\b\u0083\u0001\u0012\u0005\b\b(\u0087\u0001\u0012\u0004\u0012\u00020y0\u0080\u0001H\u0002J\u0012\u0010\u0088\u0001\u001a\u00020\u00102\u0007\u0010\u0089\u0001\u001a\u00020\u0007H\u0002J\t\u0010\u008a\u0001\u001a\u00020yH\u0007J\t\u0010\u008b\u0001\u001a\u00020yH\u0002J&\u0010\u008c\u0001\u001a\u00020y2\u0007\u0010\u008d\u0001\u001a\u00020\u001d2\u0007\u0010\u008e\u0001\u001a\u00020\u001d2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010ZH\u0014J\t\u0010\u0090\u0001\u001a\u00020yH\u0016J\u0014\u0010\u0091\u0001\u001a\u00020y2\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0007H\u0016J)\u0010\u0093\u0001\u001a\u00020y2\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010u2\u0007\u0010\u0097\u0001\u001a\u00020\u0007H\u0016J\t\u0010\u0098\u0001\u001a\u00020yH\u0016J\u0015\u0010\u0099\u0001\u001a\u00020y2\n\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0015J\t\u0010\u009c\u0001\u001a\u00020yH\u0014J\u001e\u0010\u009d\u0001\u001a\u00020y2\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u0007H\u0016J\u0013\u0010\u009e\u0001\u001a\u00020\u00102\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0016J\u0014\u0010¡\u0001\u001a\u00020y2\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010£\u0001\u001a\u00020y2\u0007\u0010\u008f\u0001\u001a\u00020\u001dH\u0003J5\u0010¤\u0001\u001a\u00020y2\u0007\u0010¥\u0001\u001a\u00020\u001d2\u0007\u0010¦\u0001\u001a\u00020\u00102\u0007\u0010\u008f\u0001\u001a\u00020\u00072\u0006\u0010R\u001a\u00020\u00072\u0007\u0010§\u0001\u001a\u00020\u0007H\u0003J\u001e\u0010¨\u0001\u001a\u0004\u0018\u00010\u00072\b\u0010©\u0001\u001a\u00030ª\u00012\u0007\u0010«\u0001\u001a\u00020\u0007H\u0002J$\u0010¬\u0001\u001a\u0004\u0018\u0001002\b\u0010©\u0001\u001a\u00030ª\u00012\u0007\u0010«\u0001\u001a\u00020\u0007H\u0002¢\u0006\u0003\u0010\u00ad\u0001J\u0007\u0010®\u0001\u001a\u00020yJ*\u0010¯\u0001\u001a\u00020y2\u0007\u0010¦\u0001\u001a\u00020\u00102\u0007\u0010\u008f\u0001\u001a\u00020\u00072\u0006\u0010R\u001a\u00020\u00072\u0007\u0010§\u0001\u001a\u00020\u0007J\t\u0010°\u0001\u001a\u00020yH\u0002J\t\u0010±\u0001\u001a\u00020yH\u0002J\t\u0010²\u0001\u001a\u00020yH\u0002J\u0007\u0010³\u0001\u001a\u00020yJ\u0012\u0010´\u0001\u001a\u00020y2\u0007\u0010µ\u0001\u001a\u00020CH\u0002J\t\u0010¶\u0001\u001a\u00020yH\u0002J\u0013\u0010·\u0001\u001a\u00020y2\b\u0010v\u001a\u0004\u0018\u00010wH\u0002R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u000bR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u000bR\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010#\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0011\"\u0004\b%\u0010\u0013R\u001a\u0010&\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0011\"\u0004\b(\u0010\u0013R\u001e\u0010)\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010.\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00102\"\u0004\b7\u00104R\u001c\u00108\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\t\"\u0004\b:\u0010\u000bR\u001c\u0010;\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\t\"\u0004\b=\u0010\u000bR\u000e\u0010>\u001a\u00020?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010F\u001a\u00020GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001c\u0010L\u001a\u0004\u0018\u00010MX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001a\u0010R\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\t\"\u0004\bT\u0010\u000bR\u001a\u0010U\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\t\"\u0004\bW\u0010\u000bR(\u0010X\u001a\u0010\u0012\f\u0012\n [*\u0004\u0018\u00010Z0Z0YX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u001c\u0010`\u001a\u0004\u0018\u00010aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u0014\u0010f\u001a\b\u0012\u0004\u0012\u00020h0gX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010i\u001a\b\u0012\u0004\u0012\u00020h0gX\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010j\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\t\"\u0004\bl\u0010\u000bR\u001a\u0010m\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\t\"\u0004\bo\u0010\u000bR\u001c\u0010p\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\t\"\u0004\br\u0010\u000b¨\u0006¹\u0001"}, d2 = {"Lid/go/bpsfasih/ui/formGear/FormGearActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "Lid/go/bpsfasih/utils/helper/CameraHelper$OnCameraOrGalleryListener;", "Lid/go/bpsfasih/utils/helper/CameraHelper$OnBarcodeListener;", "Lid/go/bpsfasih/utils/helper/CameraHelper$OnSignatureListener;", "()V", "answerPath", "", "getAnswerPath", "()Ljava/lang/String;", "setAnswerPath", "(Ljava/lang/String;)V", "formEngineId", "getFormEngineId", "setFormEngineId", "isEdit", "", "()Z", "setEdit", "(Z)V", "jsi", "Lid/go/bpsfasih/ui/formGear/FormGearJavascriptInterface;", "lastResult", "getLastResult", "setLastResult", "mAssignmentId", "getMAssignmentId", "setMAssignmentId", "mAssignmentStatus", "", "getMAssignmentStatus", "()Ljava/lang/Integer;", "setMAssignmentStatus", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "mIsKofax", "getMIsKofax", "setMIsKofax", "mIsPanelType", "getMIsPanelType", "setMIsPanelType", "mIsTarikSample", "getMIsTarikSample", "()Ljava/lang/Boolean;", "setMIsTarikSample", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "mLatitude", "", "getMLatitude", "()D", "setMLatitude", "(D)V", "mLongitude", "getMLongitude", "setMLongitude", "mPeriodeId", "getMPeriodeId", "setMPeriodeId", "mPreDefinedData", "getMPreDefinedData", "setMPreDefinedData", "nextOnlineBackupAvailableAtMillis", "", "onlineBackupCountdownRunnable", "Ljava/lang/Runnable;", "onlineBackupDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "onlineBackupHandler", "Landroid/os/Handler;", "periodeRepo", "Lid/go/bpsfasih/data/local/repository/PeriodeRepository;", "getPeriodeRepo", "()Lid/go/bpsfasih/data/local/repository/PeriodeRepository;", "setPeriodeRepo", "(Lid/go/bpsfasih/data/local/repository/PeriodeRepository;)V", "periodeResultQuery", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "getPeriodeResultQuery", "()Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "setPeriodeResultQuery", "(Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;)V", "remark", "getRemark", "setRemark", "response", "getResponse", "setResponse", "resultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getResultLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setResultLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "role", "Lid/go/bpsfasih/data/local/models/UserRole;", "getRole", "()Lid/go/bpsfasih/data/local/models/UserRole;", "setRole", "(Lid/go/bpsfasih/data/local/models/UserRole;)V", "someDraggableViewBukanPencacah", "Lio/github/hyuwah/draggableviewlib/DraggableView;", "Lcom/nambimobile/widgets/efab/ExpandableFab;", "someDraggableViewPencacah", "surveyId", "getSurveyId", "setSurveyId", "template", "getTemplate", "setTemplate", "templateId", "getTemplateId", "setTemplateId", "create7zFile", "Lkotlin/Pair;", "Ljava/io/File;", "assignmentEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "deleteReference", "", "formatOnlineBackupRemaining", "getCss", "getEngine", "getHTML", "getLatLon", "callBack", "Lkotlin/Function1;", "Lcom/google/android/gms/maps/model/LatLng;", "Lkotlin/ParameterName;", "name", "loc", "initServer", "isOnlineBackupReady", "isSubSegmen", "isValidResponseJson", "value", "loadContent", "loadContentSafely", "onActivityResult", "requestCode", "resultCode", "data", "onBackPressed", "onBarcodeResult", "barcode", "onCameraResultNew", "bitmap", "Landroid/graphics/Bitmap;", "imageFile", "fileNameFormGear", "onCancel", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onGalleryResult", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onSignatureResult", ClientCookie.PATH_ATTR, "openDialog", "openDialogFasihForm", "param", "isError", FormGearJavascriptInterface.PRINCIPAL_FILE, "parsePredataByKey", "predataJsonArray", "Lorg/json/JSONArray;", DatabaseFileArchive.COLUMN_KEY, "parsePredataLatLonByKey", "(Lorg/json/JSONArray;Ljava/lang/String;)Ljava/lang/Double;", "requestCheckButtonApproval", "requestCheckButtonApprovalFasihForm", "showDecryptErrorAndExit", "showExitConfirmation", "showFileDataErrorAndExit", "showTemporarySaveSuccessDialog", "startOnlineBackupCountdown", "dialog", "stopOnlineBackupCountdown", "submitDraft", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FormGearActivity extends BaseClassActivityNew implements CameraHelper.OnCameraOrGalleryListener, CameraHelper.OnBarcodeListener, CameraHelper.OnSignatureListener {
    public static final String IS_SUB_SEGMEN = "is_sub_segmen";
    public static final String LAT = "lat";
    public static final String LON = "long";
    private static final long ONLINE_BACKUP_INTERVAL_MILLIS = 300000;
    public static final String PREDATA = "predata";
    private boolean isEdit;
    private FormGearJavascriptInterface jsi;
    private String lastResult;
    private Integer mAssignmentStatus;
    private boolean mIsKofax;
    private boolean mIsPanelType;
    private Boolean mIsTarikSample;
    private double mLatitude;
    private double mLongitude;
    private long nextOnlineBackupAvailableAtMillis;
    private Runnable onlineBackupCountdownRunnable;
    private BottomSheetDialog onlineBackupDialog;
    private PeriodeEntityNew periodeResultQuery;
    private ActivityResultLauncher<Intent> resultLauncher;
    private UserRole role;
    private DraggableView<ExpandableFab> someDraggableViewBukanPencacah;
    private DraggableView<ExpandableFab> someDraggableViewPencacah;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String answerPath = "";
    private String surveyId = "";
    private String mPeriodeId = "";
    private String mAssignmentId = "";
    private String mPreDefinedData = "";
    private String template = "";
    private String formEngineId = "";
    private String remark = "";
    private String response = "";
    private String templateId = "sensus.json";
    private PeriodeRepository periodeRepo = DataSurvey.Periode.INSTANCE.getPeriodeRepository();
    private final Handler onlineBackupHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$17$lambda$16(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$20$lambda$19(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$23$lambda$22(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$26$lambda$25(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$29$lambda$28(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$32$lambda$31(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showExitConfirmation$lambda$34(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showExitConfirmation$lambda$36(View view) {
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // id.go.bpsfasih.utils.helper.CameraHelper.OnCameraOrGalleryListener
    public void onCancel() {
    }

    @Override // id.go.bpsfasih.utils.helper.CameraHelper.OnCameraOrGalleryListener
    public void onGalleryResult(Bitmap bitmap, String fileNameFormGear) {
        Intrinsics.checkNotNullParameter(fileNameFormGear, "fileNameFormGear");
    }

    public FormGearActivity() {
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$resultLauncher$1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(ActivityResult activityResult) throws JSONException, IOException {
                if (activityResult.getResultCode() == -1) {
                    Intent data = activityResult.getData();
                    Double dValueOf = data != null ? Double.valueOf(data.getDoubleExtra(CommonCons.INSTANCE.getKEY_LATITUDE(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) : null;
                    Double dValueOf2 = data != null ? Double.valueOf(data.getDoubleExtra(CommonCons.INSTANCE.getKEY_LONGITUDE(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) : null;
                    Float fValueOf = data != null ? Float.valueOf(data.getFloatExtra(CommonCons.INSTANCE.getKEY_ACCURACY(), 0.0f)) : null;
                    Log.d("FormGearLocation", "resultLauncher lat=" + dValueOf + " lng=" + dValueOf2 + " accuracy=" + fValueOf + " assignmentId=" + this.this$0.getMAssignmentId());
                    if (this.this$0.getFormEngineId().equals("1")) {
                        FormGearJavascriptInterface formGearJavascriptInterface = this.this$0.jsi;
                        if (formGearJavascriptInterface != null) {
                            formGearJavascriptInterface.setResultLocation(dValueOf, dValueOf2, fValueOf != null ? Double.valueOf(fValueOf.floatValue()) : null, "");
                            return;
                        }
                        return;
                    }
                    FormGearJavascriptInterface formGearJavascriptInterface2 = this.this$0.jsi;
                    if (formGearJavascriptInterface2 != null) {
                        Double dValueOf3 = fValueOf != null ? Double.valueOf(fValueOf.floatValue()) : null;
                        FormGearJavascriptInterface formGearJavascriptInterface3 = this.this$0.jsi;
                        formGearJavascriptInterface2.setResultLocation(dValueOf, dValueOf2, dValueOf3, formGearJavascriptInterface3 != null ? formGearJavascriptInterface3.getMDataKey() : null);
                    }
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…\n\n            }\n        }");
        this.resultLauncher = activityResultLauncherRegisterForActivityResult;
    }

    public final String getAnswerPath() {
        return this.answerPath;
    }

    public final void setAnswerPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.answerPath = str;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        this.surveyId = str;
    }

    public final String getMPeriodeId() {
        return this.mPeriodeId;
    }

    public final void setMPeriodeId(String str) {
        this.mPeriodeId = str;
    }

    public final String getMAssignmentId() {
        return this.mAssignmentId;
    }

    public final void setMAssignmentId(String str) {
        this.mAssignmentId = str;
    }

    public final String getMPreDefinedData() {
        return this.mPreDefinedData;
    }

    public final void setMPreDefinedData(String str) {
        this.mPreDefinedData = str;
    }

    public final String getTemplate() {
        return this.template;
    }

    public final void setTemplate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.template = str;
    }

    public final String getFormEngineId() {
        return this.formEngineId;
    }

    public final void setFormEngineId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.formEngineId = str;
    }

    public final String getRemark() {
        return this.remark;
    }

    public final void setRemark(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.remark = str;
    }

    public final String getResponse() {
        return this.response;
    }

    public final void setResponse(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.response = str;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final void setTemplateId(String str) {
        this.templateId = str;
    }

    public final Integer getMAssignmentStatus() {
        return this.mAssignmentStatus;
    }

    public final void setMAssignmentStatus(Integer num) {
        this.mAssignmentStatus = num;
    }

    public final Boolean getMIsTarikSample() {
        return this.mIsTarikSample;
    }

    public final void setMIsTarikSample(Boolean bool) {
        this.mIsTarikSample = bool;
    }

    public final double getMLatitude() {
        return this.mLatitude;
    }

    public final void setMLatitude(double d) {
        this.mLatitude = d;
    }

    public final double getMLongitude() {
        return this.mLongitude;
    }

    public final void setMLongitude(double d) {
        this.mLongitude = d;
    }

    public final boolean getMIsPanelType() {
        return this.mIsPanelType;
    }

    public final void setMIsPanelType(boolean z) {
        this.mIsPanelType = z;
    }

    public final boolean getMIsKofax() {
        return this.mIsKofax;
    }

    public final void setMIsKofax(boolean z) {
        this.mIsKofax = z;
    }

    public final PeriodeEntityNew getPeriodeResultQuery() {
        return this.periodeResultQuery;
    }

    public final void setPeriodeResultQuery(PeriodeEntityNew periodeEntityNew) {
        this.periodeResultQuery = periodeEntityNew;
    }

    public final PeriodeRepository getPeriodeRepo() {
        return this.periodeRepo;
    }

    public final void setPeriodeRepo(PeriodeRepository periodeRepository) {
        Intrinsics.checkNotNullParameter(periodeRepository, "<set-?>");
        this.periodeRepo = periodeRepository;
    }

    /* renamed from: isEdit, reason: from getter */
    public final boolean getIsEdit() {
        return this.isEdit;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
    }

    public final UserRole getRole() {
        return this.role;
    }

    public final void setRole(UserRole userRole) {
        this.role = userRole;
    }

    public final String getLastResult() {
        return this.lastResult;
    }

    public final void setLastResult(String str) {
        this.lastResult = str;
    }

    public final ActivityResultLauncher<Intent> getResultLauncher() {
        return this.resultLauncher;
    }

    public final void setResultLauncher(ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
        this.resultLauncher = activityResultLauncher;
    }

    /* compiled from: FormGearActivity.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jk\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00102\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0010¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/ui/formGear/FormGearActivity$Companion;", "", "()V", "IS_SUB_SEGMEN", "", "LAT", "LON", "ONLINE_BACKUP_INTERVAL_MILLIS", "", "PREDATA", "startActivity", "", "context", "Landroid/content/Context;", "templateId", "isPencacah", "", "periodeId", "assignmentEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "panelType", "isKofax", "saveData", "latlng", "Lcom/google/android/gms/maps/model/LatLng;", "isListing", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/AssignmentEntity;ZZZLcom/google/android/gms/maps/model/LatLng;Z)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context, String templateId, Boolean isPencacah, String periodeId, AssignmentEntity assignmentEntity, boolean panelType, boolean isKofax, boolean saveData, LatLng latlng, boolean isListing) {
            Integer assignmentStatusId;
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) FormGearActivity.class);
            boolean z = false;
            if (assignmentEntity != null && assignmentEntity.getPendingStatus()) {
                z = true;
            }
            if (z) {
                assignmentStatusId = 99;
            } else {
                assignmentStatusId = assignmentEntity != null ? assignmentEntity.getAssignmentStatusId() : null;
            }
            intent.putExtra(CommonCons.INSTANCE.getTEMPLATE_ID(), templateId);
            intent.putExtra(CommonCons.INSTANCE.getPREDEFINED_DATA_ASSIGNMENT(), assignmentEntity != null ? assignmentEntity.getPreDefinedData() : null);
            intent.putExtra(CommonCons.INSTANCE.getKEY_ASSIGNMENT_ID(), assignmentEntity != null ? assignmentEntity.getId() : null);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), periodeId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_ASSIGNMENT_STATUS(), assignmentStatusId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_IS_PENCACAH(), isPencacah);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PANEL_TYPE(), panelType);
            intent.putExtra(CommonCons.INSTANCE.getKEY_IS_KOFAX(), isKofax);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SAVE_DATA(), saveData);
            intent.putExtra(CommonCons.INSTANCE.getKEY_LATITUDE(), latlng != null ? Double.valueOf(latlng.latitude) : null);
            intent.putExtra(CommonCons.INSTANCE.getKEY_LONGITUDE(), latlng != null ? Double.valueOf(latlng.longitude) : null);
            intent.putExtra(CommonCons.INSTANCE.getKEY_IS_UPDATE_LISTING(), isListing);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.onCreate.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                FormGearActivity.this.showExitConfirmation();
            }
        });
        setContentView(R.layout.activity_formgear);
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(false);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root_layout);
            if (relativeLayout != null) {
                relativeLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda30
                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        return FormGearActivity.onCreate$lambda$1$lambda$0(view, windowInsets);
                    }
                });
            }
        } else {
            getWindow().getDecorView().setSystemUiVisibility(1280);
            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.root_layout);
            if (relativeLayout2 != null) {
                relativeLayout2.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda35
                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        return FormGearActivity.onCreate$lambda$3$lambda$2(view, windowInsets);
                    }
                });
            }
        }
        getWindow().setStatusBarColor(getResources().getColor(R.color.grey_700, null));
        getWindow().getDecorView().setSystemUiVisibility(256);
        getWindow().setStatusBarColor(Color.parseColor("#757575"));
        initServer();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass4(null), 2, null);
        ((FabOption) _$_findCachedViewById(R.id.fab_approval)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda36
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$4(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_simpan_sementara_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda37
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$5(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_rebuild_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda38
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$6(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_rebuild_bukan_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda39
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$7(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_calculator_bukan_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda40
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$8(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_calculator_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda41
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$9(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_notes_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda42
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$10(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_notes_bukan_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda43
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$11(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_bantuan_bukan_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda31
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$12(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_bantuan_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda32
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.onCreate$lambda$13(this.f$0, view);
            }
        });
        ((FabOption) _$_findCachedViewById(R.id.fab_location_pencacah)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda34
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws JSONException {
                FormGearActivity.onCreate$lambda$14(this.f$0, view);
            }
        });
        this.someDraggableViewBukanPencacah = new DraggableView.Builder((ExpandableFab) _$_findCachedViewById(R.id.expendable_fab_bukan_pencacah)).build();
        this.someDraggableViewBukanPencacah = DraggableUtils.setupDraggable((ExpandableFab) _$_findCachedViewById(R.id.expendable_fab_bukan_pencacah)).build();
        this.someDraggableViewPencacah = new DraggableView.Builder((ExpandableFab) _$_findCachedViewById(R.id.expendable_fab_pencacah)).build();
        this.someDraggableViewPencacah = DraggableUtils.setupDraggable((ExpandableFab) _$_findCachedViewById(R.id.expendable_fab_pencacah)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsets onCreate$lambda$1$lambda$0(View view, WindowInsets insets) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(insets, "insets");
        Insets insets2 = insets.getInsets(WindowInsets.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets2, "insets.getInsets(\n      …s()\n                    )");
        view.setPadding(insets2.left, insets2.top, insets2.right, insets2.bottom);
        return insets;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsets onCreate$lambda$3$lambda$2(View view, WindowInsets insets) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(insets, "insets");
        view.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
        return insets;
    }

    /* compiled from: FormGearActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$4", f = "FormGearActivity.kt", i = {0, 1, 2}, l = {261, 277, 292}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0"})
    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = FormGearActivity.this.new AnonymousClass4(continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x01be  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x01de  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x01eb  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x01ee  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x01fd  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0241  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0256  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x02ab  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x02f4  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r16) {
            /*
                Method dump skipped, instructions count: 764
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearActivity.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: FormGearActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$4$1", f = "FormGearActivity.kt", i = {}, l = {263}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$4$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
            int label;
            final /* synthetic */ FormGearActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(FormGearActivity formGearActivity, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = formGearActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TemplateValidationRepository templateValidationRepository = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository();
                    String surveyId = this.this$0.getSurveyId();
                    Intrinsics.checkNotNull(surveyId);
                    this.label = 1;
                    obj = templateValidationRepository.getDataBySurveyId(surveyId, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                TemplateValidationEntity templateValidationEntity = (TemplateValidationEntity) obj;
                if (templateValidationEntity != null) {
                    return templateValidationEntity.getTemplate_id();
                }
                return null;
            }
        }

        /* compiled from: FormGearActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$4$2", f = "FormGearActivity.kt", i = {}, l = {284, 283}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$4$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
            Object L$0;
            int label;
            final /* synthetic */ FormGearActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(FormGearActivity formGearActivity, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = formGearActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r7.label
                    r2 = 0
                    r3 = 2
                    r4 = 1
                    if (r1 == 0) goto L23
                    if (r1 == r4) goto L1b
                    if (r1 != r3) goto L13
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L5a
                L13:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L1b:
                    java.lang.Object r1 = r7.L$0
                    id.go.bpsfasih.data.local.repository.TemplateValidationRepository r1 = (id.go.bpsfasih.data.local.repository.TemplateValidationRepository) r1
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L4a
                L23:
                    kotlin.ResultKt.throwOnFailure(r8)
                    id.go.bpsfasih.utils.dbHelper.DataSurvey$TemplateValidation$Companion r8 = id.go.bpsfasih.utils.dbHelper.DataSurvey.TemplateValidation.INSTANCE
                    id.go.bpsfasih.data.local.repository.TemplateValidationRepository r1 = r8.getTemplateValidationRepository()
                    id.go.bpsfasih.utils.dbHelper.DataSurvey$Survey$Companion r8 = id.go.bpsfasih.utils.dbHelper.DataSurvey.Survey.INSTANCE
                    id.go.bpsfasih.data.local.repository.SurveyRepositoryNew r8 = r8.getSurveyRepo()
                    id.go.bpsfasih.ui.formGear.FormGearActivity r5 = r7.this$0
                    java.lang.String r5 = r5.getMPeriodeId()
                    if (r5 != 0) goto L3c
                    java.lang.String r5 = ""
                L3c:
                    r6 = r7
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                    r7.L$0 = r1
                    r7.label = r4
                    java.lang.Object r8 = r8.getSurveyId(r5, r6)
                    if (r8 != r0) goto L4a
                    return r0
                L4a:
                    java.lang.String r8 = (java.lang.String) r8
                    r4 = r7
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r7.L$0 = r2
                    r7.label = r3
                    java.lang.Object r8 = r1.getDataBySurveyId(r8, r4)
                    if (r8 != r0) goto L5a
                    return r0
                L5a:
                    id.go.bpsfasih.data.local.entities.TemplateValidationEntity r8 = (id.go.bpsfasih.data.local.entities.TemplateValidationEntity) r8
                    if (r8 == 0) goto L66
                    int r8 = r8.getFormEngineId()
                    java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
                L66:
                    java.lang.String r8 = java.lang.String.valueOf(r2)
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearActivity.AnonymousClass4.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        Intrinsics.checkNotNull(formGearJavascriptInterface);
        formGearJavascriptInterface.finalize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.mobileExit(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.deleteReference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.deleteReference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(final FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("calculator")) {
            new CalculatorDialog(this$0, this$0.lastResult, new CalculatorCallback() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$9$calculatorDialog$1
                @Override // id.go.bpsfasih.calculator.CalculatorCallback
                public void setInitResult(String str) {
                    Intrinsics.checkNotNullParameter(str, "str");
                    this.this$0.setLastResult(str);
                }
            }).show();
        } else {
            Toast.makeText(this$0.getApplicationContext(), "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(final FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("calculator")) {
            new CalculatorDialog(this$0, this$0.lastResult, new CalculatorCallback() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$10$calculatorDialog$1
                @Override // id.go.bpsfasih.calculator.CalculatorCallback
                public void setInitResult(String str) {
                    Intrinsics.checkNotNullParameter(str, "str");
                    this.this$0.setLastResult(str);
                }
            }).show();
        } else {
            Toast.makeText(this$0.getApplicationContext(), "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("lembar_kerja")) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new FormGearActivity$onCreate$11$1(this$0, null), 2, null);
        } else {
            Toast.makeText(this$0.getApplicationContext(), "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$11(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("lembar_kerja")) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new FormGearActivity$onCreate$12$1(this$0, null), 2, null);
        } else {
            Toast.makeText(this$0.getApplicationContext(), "Fitur ini dinonaktifkan untuk sementara waktu", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$12(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BantuanActivity.Companion companion = BantuanActivity.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        companion.startActivity(applicationContext, this$0.mAssignmentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$13(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BantuanActivity.Companion companion = BantuanActivity.INSTANCE;
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        companion.startActivity(applicationContext, this$0.mAssignmentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14(final FormGearActivity this$0, View view) throws JSONException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isSubSegmen(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$15$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws JSONException {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) throws JSONException {
                if (z) {
                    FormGearActivity formGearActivity = this.this$0;
                    final FormGearActivity formGearActivity2 = this.this$0;
                    formGearActivity.getLatLon(new Function1<LatLng, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$15$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(LatLng latLng) {
                            invoke2(latLng);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(LatLng latLng) {
                            if (latLng != null) {
                                Intent intent = new Intent(formGearActivity2, (Class<?>) MapUbinanActivity.class);
                                intent.putExtra(CommonCons.INSTANCE.getKEY_LATITUDE(), latLng.latitude);
                                intent.putExtra(CommonCons.INSTANCE.getKEY_LONGITUDE(), latLng.longitude);
                                formGearActivity2.startActivity(intent);
                                return;
                            }
                            formGearActivity2.startActivity(new Intent(formGearActivity2, (Class<?>) MapUbinanActivity.class));
                        }
                    });
                } else {
                    this.this$0.startActivity(new Intent(this.this$0, (Class<?>) MapUbinanActivity.class));
                }
            }
        });
    }

    public final void requestCheckButtonApproval() {
        AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
        String str = this.mAssignmentId;
        Intrinsics.checkNotNull(str);
        assignmentRepositoryImpl.checkButtonApproval(str, new Function1<BaseResponseDataStringApproval, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.requestCheckButtonApproval.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                invoke2(baseResponseDataStringApproval);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                Unit unit;
                if (baseResponseDataStringApproval != null) {
                    FormGearActivity formGearActivity = FormGearActivity.this;
                    Integer data = baseResponseDataStringApproval.getData();
                    Intrinsics.checkNotNull(data);
                    formGearActivity.openDialog(data.intValue());
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    Toast.makeText(FormGearActivity.this, "Gagal request check button approval", 1).show();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openDialog(int data) {
        final ApproveRejectDialog approveRejectDialog = new ApproveRejectDialog(data, false, this);
        approveRejectDialog.setReject(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda44
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.openDialog$lambda$17(this.f$0, approveRejectDialog, view);
            }
        });
        approveRejectDialog.setApprove(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda45
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.openDialog$lambda$20(this.f$0, approveRejectDialog, view);
            }
        });
        approveRejectDialog.setUnApprove(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda46
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.openDialog$lambda$23(this.f$0, approveRejectDialog, view);
            }
        });
        approveRejectDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$17(final FormGearActivity this$0, ApproveRejectDialog approveRejectDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(approveRejectDialog, "$approveRejectDialog");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            FormGearJavascriptInterface.mobileExit$default(formGearJavascriptInterface, false, 1, null);
        }
        approveRejectDialog.dismiss();
        BaseClassActivityNew.showAlertDialog$default(this$0, "Konfirmasi", "Anda akan REJECT assignment ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialog$lambda$17$lambda$15(this.f$0, view2);
            }
        }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialog$lambda$17$lambda$16(view2);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$17$lambda$15(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.approval(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$20(final FormGearActivity this$0, ApproveRejectDialog approveRejectDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(approveRejectDialog, "$approveRejectDialog");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            FormGearJavascriptInterface.mobileExit$default(formGearJavascriptInterface, false, 1, null);
        }
        approveRejectDialog.dismiss();
        BaseClassActivityNew.showAlertDialog$default(this$0, "Konfirmasi", "Anda akan APPROVE assignment ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialog$lambda$20$lambda$18(this.f$0, view2);
            }
        }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialog$lambda$20$lambda$19(view2);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$20$lambda$18(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.approval(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$23(final FormGearActivity this$0, ApproveRejectDialog approveRejectDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(approveRejectDialog, "$approveRejectDialog");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            FormGearJavascriptInterface.mobileExit$default(formGearJavascriptInterface, false, 1, null);
        }
        approveRejectDialog.dismiss();
        BaseClassActivityNew.showAlertDialog$default(this$0, "Konfirmasi", "Anda akan UNAPPROVE assignment ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialog$lambda$23$lambda$21(this.f$0, view2);
            }
        }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialog$lambda$23$lambda$22(view2);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialog$lambda$23$lambda$21(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.revoke();
        }
    }

    public final void requestCheckButtonApprovalFasihForm(final boolean isError, final String data, final String remark, final String principal) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(principal, "principal");
        AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
        String str = this.mAssignmentId;
        Intrinsics.checkNotNull(str);
        assignmentRepositoryImpl.checkButtonApproval(str, new Function1<BaseResponseDataStringApproval, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.requestCheckButtonApprovalFasihForm.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                invoke2(baseResponseDataStringApproval);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                Unit unit;
                if (baseResponseDataStringApproval != null) {
                    FormGearActivity formGearActivity = FormGearActivity.this;
                    boolean z = isError;
                    String str2 = data;
                    String str3 = remark;
                    String str4 = principal;
                    Integer data2 = baseResponseDataStringApproval.getData();
                    Intrinsics.checkNotNull(data2);
                    formGearActivity.openDialogFasihForm(data2.intValue(), z, str2, str3, str4);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    Toast.makeText(FormGearActivity.this, "Gagal request check button approval", 1).show();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openDialogFasihForm(int param, boolean isError, final String data, final String remark, final String principal) {
        final ApproveRejectDialog approveRejectDialog = new ApproveRejectDialog(param, isError, this);
        approveRejectDialog.setReject(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.openDialogFasihForm$lambda$26(this.f$0, approveRejectDialog, data, remark, principal, view);
            }
        });
        approveRejectDialog.setApprove(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.openDialogFasihForm$lambda$29(this.f$0, approveRejectDialog, data, remark, principal, view);
            }
        });
        approveRejectDialog.setUnApprove(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.openDialogFasihForm$lambda$32(this.f$0, approveRejectDialog, view);
            }
        });
        approveRejectDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$26(final FormGearActivity this$0, ApproveRejectDialog approveRejectDialog, final String data, final String remark, final String principal, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(approveRejectDialog, "$approveRejectDialog");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(remark, "$remark");
        Intrinsics.checkNotNullParameter(principal, "$principal");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            FormGearJavascriptInterface.mobileExit$default(formGearJavascriptInterface, false, 1, null);
        }
        approveRejectDialog.dismiss();
        BaseClassActivityNew.showAlertDialog$default(this$0, "Konfirmasi", "Anda akan REJECT assignment ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws IOException {
                FormGearActivity.openDialogFasihForm$lambda$26$lambda$24(this.f$0, data, remark, principal, view2);
            }
        }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialogFasihForm$lambda$26$lambda$25(view2);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$26$lambda$24(FormGearActivity this$0, String data, String remark, String principal, View view) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(remark, "$remark");
        Intrinsics.checkNotNullParameter(principal, "$principal");
        boolean z = this$0.isEdit;
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (z) {
            if (formGearJavascriptInterface != null) {
                formGearJavascriptInterface.edit(false, data, remark, principal);
            }
        } else if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.approval(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$29(final FormGearActivity this$0, ApproveRejectDialog approveRejectDialog, final String data, final String remark, final String principal, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(approveRejectDialog, "$approveRejectDialog");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(remark, "$remark");
        Intrinsics.checkNotNullParameter(principal, "$principal");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            FormGearJavascriptInterface.mobileExit$default(formGearJavascriptInterface, false, 1, null);
        }
        approveRejectDialog.dismiss();
        BaseClassActivityNew.showAlertDialog$default(this$0, "Konfirmasi", "Anda akan APPROVE assignment ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws IOException {
                FormGearActivity.openDialogFasihForm$lambda$29$lambda$27(this.f$0, data, remark, principal, view2);
            }
        }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda33
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialogFasihForm$lambda$29$lambda$28(view2);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$29$lambda$27(FormGearActivity this$0, String data, String remark, String principal, View view) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(remark, "$remark");
        Intrinsics.checkNotNullParameter(principal, "$principal");
        boolean z = this$0.isEdit;
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (z) {
            if (formGearJavascriptInterface != null) {
                formGearJavascriptInterface.edit(true, data, remark, principal);
            }
        } else if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.approval(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$32(final FormGearActivity this$0, ApproveRejectDialog approveRejectDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(approveRejectDialog, "$approveRejectDialog");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            FormGearJavascriptInterface.mobileExit$default(formGearJavascriptInterface, false, 1, null);
        }
        approveRejectDialog.dismiss();
        BaseClassActivityNew.showAlertDialog$default(this$0, "Konfirmasi", "Anda akan UNAPPROVE assignment ini?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda47
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialogFasihForm$lambda$32$lambda$30(this.f$0, view2);
            }
        }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda48
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FormGearActivity.openDialogFasihForm$lambda$32$lambda$31(view2);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openDialogFasihForm$lambda$32$lambda$30(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.revoke();
        }
    }

    private final void initServer() {
        Server.HttpServerThread httpServerThread = new Server.HttpServerThread();
        if (httpServerThread.isAlive()) {
            return;
        }
        Log.d(InstrumentationResultPrinter.REPORT_KEY_NAME_TEST, "serverThread ");
        httpServerThread.start();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        showExitConfirmation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showExitConfirmation() {
        if (this.isEdit) {
            BaseClassActivityNew.showAlertDialog$default(this, "Perhatian", "Anda sedang dalam mode edit. Jika anda keluar halaman ini, hasil edit anda tidak akan tersimpan pada aplikasi. Apakah Anda yakin akan keluar dari halaman ini?", null, "Iya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda15
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearActivity.showExitConfirmation$lambda$33(this.f$0, view);
                }
            }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda16
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearActivity.showExitConfirmation$lambda$34(view);
                }
            }, true, false, 256, null);
        } else {
            BaseClassActivityNew.showAlertDialog$default(this, "Perhatian", "Apakah Anda yakin akan keluar dari halaman ini ?", null, "Iya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda17
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearActivity.showExitConfirmation$lambda$35(this.f$0, view);
                }
            }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda18
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FormGearActivity.showExitConfirmation$lambda$36(view);
                }
            }, true, false, 256, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showExitConfirmation$lambda$33(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showExitConfirmation$lambda$35(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FormGearJavascriptInterface formGearJavascriptInterface = this$0.jsi;
        if (formGearJavascriptInterface != null) {
            FormGearJavascriptInterface.mobileExit$default(formGearJavascriptInterface, false, 1, null);
        }
        this$0.finish();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public final void loadContent() {
        ((WebView) _$_findCachedViewById(R.id.wv_main)).getSettings().setJavaScriptEnabled(true);
        ((WebView) _$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccess(true);
        ((WebView) _$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccessFromFileURLs(true);
        ((WebView) _$_findCachedViewById(R.id.wv_main)).clearCache(true);
        String html = getHTML();
        String strReplace$default = html != null ? StringsKt.replace$default(html, "//formgear_js", String.valueOf(getEngine()), false, 4, (Object) null) : null;
        ((WebView) _$_findCachedViewById(R.id.wv_main)).loadDataWithBaseURL(null, String.valueOf(strReplace$default != null ? StringsKt.replace$default(strReplace$default, "/*style*/", String.valueOf(getCss()), false, 4, (Object) null) : null), "text/html", "utf-8", null);
        ((WebView) _$_findCachedViewById(R.id.wv_main)).setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.loadContent.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebView wv_main = (WebView) _$_findCachedViewById(R.id.wv_main);
        Intrinsics.checkNotNullExpressionValue(wv_main, "wv_main");
        this.jsi = new FormGearJavascriptInterface(wv_main, this.formEngineId.toString(), this);
        WebView webView = (WebView) _$_findCachedViewById(R.id.wv_main);
        FormGearJavascriptInterface formGearJavascriptInterface = this.jsi;
        Intrinsics.checkNotNull(formGearJavascriptInterface);
        webView.addJavascriptInterface(formGearJavascriptInterface, "Android");
        ((WebView) _$_findCachedViewById(R.id.wv_main)).setWebChromeClient(new WebChromeClient() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.loadContent.2
            @Override // android.webkit.WebChromeClient
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                Intrinsics.checkNotNullParameter(origin, "origin");
                Intrinsics.checkNotNullParameter(callback, "callback");
                callback.invoke(origin, true, false);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) throws JSONException {
                if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("send_error_console_form_engine")) {
                    if ((consoleMessage != null ? consoleMessage.messageLevel() : null) == ConsoleMessage.MessageLevel.ERROR) {
                        String formEngineVersion = FormEngineHelper.INSTANCE.getFormEngineVersion(FormGearActivity.this.getFormEngineId());
                        TemplateHelper.Companion companion = TemplateHelper.INSTANCE;
                        String templateId = FormGearActivity.this.getTemplateId();
                        if (templateId == null) {
                            templateId = "";
                        }
                        String version = companion.getVersion(templateId);
                        String str = version == null ? "" : version;
                        ValidationHelper.Companion companion2 = ValidationHelper.INSTANCE;
                        String templateId2 = FormGearActivity.this.getTemplateId();
                        if (templateId2 == null) {
                            templateId2 = "";
                        }
                        String version2 = companion2.getVersion(templateId2);
                        String str2 = version2 == null ? "" : version2;
                        FirestoreHelper.Companion companion3 = FirestoreHelper.INSTANCE;
                        String formEngineId = FormGearActivity.this.getFormEngineId();
                        String templateId3 = FormGearActivity.this.getTemplateId();
                        if (templateId3 == null) {
                            templateId3 = "";
                        }
                        String templateId4 = FormGearActivity.this.getTemplateId();
                        String str3 = templateId4 == null ? "" : templateId4;
                        String mAssignmentId = FormGearActivity.this.getMAssignmentId();
                        String str4 = mAssignmentId == null ? "" : mAssignmentId;
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str5 = String.format("{message : %s, sourceId : %s, lineNumber : %d}", Arrays.copyOf(new Object[]{consoleMessage.message(), consoleMessage.sourceId(), Integer.valueOf(consoleMessage.lineNumber())}, 3));
                        Intrinsics.checkNotNullExpressionValue(str5, "format(format, *args)");
                        companion3.sendFasihEngineLogError(new FormEngineLogErrorModel(null, null, null, 0, formEngineId, formEngineVersion, templateId3, str, str3, str2, str4, str5, null, 4111, null));
                    }
                }
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr = new Object[3];
                objArr[0] = consoleMessage != null ? consoleMessage.message() : null;
                objArr[1] = consoleMessage != null ? consoleMessage.sourceId() : null;
                objArr[2] = consoleMessage != null ? Integer.valueOf(consoleMessage.lineNumber()) : null;
                String str6 = String.format("{message : %s, sourceId : %s, lineNumber : %d}", Arrays.copyOf(objArr, 3));
                Intrinsics.checkNotNullExpressionValue(str6, "format(format, *args)");
                Log.d("WebView", str6);
                return true;
            }
        });
        ((WebView) _$_findCachedViewById(R.id.wv_main)).setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.loadContent.3
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                super.onPageFinished(view, url);
            }
        });
    }

    private final boolean isValidResponseJson(String value) {
        try {
            new JSONObject(value);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    private final void showDecryptErrorAndExit() {
        runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                FormGearActivity.showDecryptErrorAndExit$lambda$38(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDecryptErrorAndExit$lambda$38(final FormGearActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0, "Kesalahan", "Terdapat kesalahan ketika proses decrypt data. Laporkan melalui fitur Bantuan", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.showDecryptErrorAndExit$lambda$38$lambda$37(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDecryptErrorAndExit$lambda$38$lambda$37(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    private final void showFileDataErrorAndExit() {
        runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FormGearActivity.showFileDataErrorAndExit$lambda$40(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showFileDataErrorAndExit$lambda$40(final FormGearActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseClassActivityNew.showAlertDialog$default(this$0, "Kesalahan", "Terdapat kesalahan pada file data", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.showFileDataErrorAndExit$lambda$40$lambda$39(this.f$0, view);
            }
        }, null, null, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showFileDataErrorAndExit$lambda$40$lambda$39(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadContentSafely() {
        try {
            File file = new File(this.answerPath, "data.json");
            if (file.exists()) {
                boolean z = true;
                AssignmentEntity assignmentEntity = (AssignmentEntity) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearActivity$loadContentSafely$assignment$1(this, null), 1, null);
                FileHelper.Companion companion = FileHelper.INSTANCE;
                String path = file.getPath();
                Intrinsics.checkNotNullExpressionValue(path, "responseFile.path");
                String string = companion.readFile(path).toString();
                Intrinsics.checkNotNullExpressionValue(string, "FileHelper.readFile(responseFile.path).toString()");
                if (!isValidResponseJson(string)) {
                    String strDecrypt = AssignmentEncryptionHelper.INSTANCE.decrypt(string, AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(assignmentEntity));
                    String str = strDecrypt;
                    if (str != null && str.length() != 0) {
                        z = false;
                    }
                    if (z || !isValidResponseJson(strDecrypt)) {
                        showDecryptErrorAndExit();
                        return;
                    }
                }
            }
            loadContent();
        } catch (IOException unused) {
            showFileDataErrorAndExit();
        } catch (Exception unused2) {
            showDecryptErrorAndExit();
        }
    }

    public final String getHTML() {
        String string;
        try {
            String str = Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + this.formEngineId + "/index.html";
            if (new File(str).exists()) {
                string = FileHelper.INSTANCE.readFile(str).toString();
            } else {
                BaseClassActivityNew.showAlertDialog$default(this, "Kesalahan", "Anda belum memiliki file FormGear, silahkan mengunduh FormGear terlebih dahulu", null, "Unduh", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda11
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearActivity.getHTML$lambda$41(this.f$0, view);
                    }
                }, null, null, false, false, 384, null);
                string = "";
            }
            return string;
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getHTML$lambda$41(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, (Class<?>) SistemActivity.class));
        this$0.finish();
    }

    public final String getEngine() {
        String string;
        try {
            String str = Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + this.formEngineId + InternalZipConstants.ZIP_FILE_SEPARATOR + (this.formEngineId.equals("1") ? "form-gear" : "fasih-form") + ".es.js";
            if (new File(str).exists()) {
                string = FileHelper.INSTANCE.readFile(str).toString();
            } else {
                BaseClassActivityNew.showAlertDialog$default(this, "Kesalahan", "Anda belum memiliki file FormGear, silahkan mengunduh FormGear terlebih dahulu", null, "Unduh", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearActivity.getEngine$lambda$42(this.f$0, view);
                    }
                }, null, null, false, false, 384, null);
                string = "";
            }
            return string;
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getEngine$lambda$42(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, (Class<?>) SistemActivity.class));
        this$0.finish();
    }

    public final void deleteReference() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        showAlertDialogKodeVerifikasi(bottomSheetDialog, "Muat Ulang", "Anda yakin muat ulang kuesioner ?", "Proses ini akan memuat ulang kuesioner kembali, sehingga membutuhkan waktu membuka kuesioner yang lebih lama dari proses sebelumnya.", "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.deleteReference$lambda$43(bottomSheetDialog, view);
            }
        }, "Yakin", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.deleteReference$lambda$44(this.f$0, bottomSheetDialog, view);
            }
        }, new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.deleteReference$lambda$45(bottomSheetDialog, view);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteReference$lambda$43(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteReference$lambda$44(FormGearActivity this$0, BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        File file = new File(this$0.answerPath + "/reference" + CommonCons.INSTANCE.getEXTENSION_JSON());
        if (file.exists()) {
            file.delete();
            Toast.makeText(this$0.getApplicationContext(), "Reference dimuat ulang", 0).show();
            this$0.loadContent();
        } else {
            Toast.makeText(this$0.getApplicationContext(), "Reference tidak ditemukan", 0).show();
        }
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteReference$lambda$45(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public final String getCss() {
        String string;
        try {
            String str = Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + this.formEngineId + "/style.css";
            if (new File(str).exists()) {
                string = FileHelper.INSTANCE.readFile(str).toString();
            } else {
                BaseClassActivityNew.showAlertDialog$default(this, "Kesalahan", "Anda belum memiliki file FormGear, silahkan mengunduh FormGear terlebih dahulu", null, "Unduh", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda24
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FormGearActivity.getCss$lambda$46(this.f$0, view);
                    }
                }, null, null, false, false, 384, null);
                string = "";
            }
            return string;
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getCss$lambda$46(FormGearActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, (Class<?>) SistemActivity.class));
        this$0.finish();
    }

    @Override // id.go.bpsfasih.utils.helper.CameraHelper.OnCameraOrGalleryListener
    public void onCameraResultNew(Bitmap bitmap, File imageFile, String fileNameFormGear) {
        Intrinsics.checkNotNullParameter(fileNameFormGear, "fileNameFormGear");
        FormGearJavascriptInterface formGearJavascriptInterface = this.jsi;
        if (formGearJavascriptInterface != null) {
            formGearJavascriptInterface.cameraResult(imageFile, fileNameFormGear);
        }
    }

    @Override // id.go.bpsfasih.utils.helper.CameraHelper.OnBarcodeListener
    public void onBarcodeResult(String barcode) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // id.go.bpsfasih.utils.helper.CameraHelper.OnSignatureListener
    public void onSignatureResult(String path) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws JSONException, IOException {
        String str;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == CommonCons.INSTANCE.getCODE_LOCATION_HISTORY()) {
            LocationHistory[] locationHistoryAsObject = FileHelper.INSTANCE.readLocationHistoryAsObject(Directory.INSTANCE.getABSOLUTEPATHENV() + "/location_histories.json");
            String stringExtra = data != null ? data.getStringExtra(CommonCons.INSTANCE.getKEY_ID_LOCATION_HISTORY()) : null;
            Log.d("FormGearLocation", "onActivityResult CODE_LOCATION_HISTORY idHistory=" + stringExtra + " assignmentId=" + this.mAssignmentId);
            if (locationHistoryAsObject != null) {
                int length = locationHistoryAsObject.length;
                int i = 0;
                while (i < length) {
                    LocationHistory locationHistory = locationHistoryAsObject[i];
                    if (locationHistory.getId().equals(stringExtra)) {
                        str = stringExtra;
                        Log.d("FormGearLocation", "historySelected lat=" + locationHistory.getLatitude() + " lng=" + locationHistory.getLongitude() + " accuracy=" + locationHistory.getAccuracy() + " assignmentId=" + this.mAssignmentId);
                        if (this.formEngineId.equals("1")) {
                            FormGearJavascriptInterface formGearJavascriptInterface = this.jsi;
                            if (formGearJavascriptInterface != null) {
                                formGearJavascriptInterface.setResultLocation(Double.valueOf(locationHistory.getLatitude()), Double.valueOf(locationHistory.getLongitude()), Double.valueOf(locationHistory.getAccuracy()), "");
                            }
                        } else {
                            FormGearJavascriptInterface formGearJavascriptInterface2 = this.jsi;
                            if (formGearJavascriptInterface2 != null) {
                                Double dValueOf = Double.valueOf(locationHistory.getLatitude());
                                Double dValueOf2 = Double.valueOf(locationHistory.getLongitude());
                                Double dValueOf3 = Double.valueOf(locationHistory.getAccuracy());
                                FormGearJavascriptInterface formGearJavascriptInterface3 = this.jsi;
                                formGearJavascriptInterface2.setResultLocation(dValueOf, dValueOf2, dValueOf3, formGearJavascriptInterface3 != null ? formGearJavascriptInterface3.getMDataKey() : null);
                            }
                        }
                    } else {
                        str = stringExtra;
                    }
                    i++;
                    stringExtra = str;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getLatLon(Function1<? super LatLng, Unit> callBack) throws JSONException {
        try {
            JSONArray preDataJson = new JSONObject(this.mPreDefinedData).getJSONArray(PREDATA);
            Intrinsics.checkNotNullExpressionValue(preDataJson, "preDataJson");
            Double predataLatLonByKey = parsePredataLatLonByKey(preDataJson, LAT);
            Double predataLatLonByKey2 = parsePredataLatLonByKey(preDataJson, LON);
            if (predataLatLonByKey != null && predataLatLonByKey2 != null && !Double.isNaN(predataLatLonByKey.doubleValue()) && !Double.isNaN(predataLatLonByKey2.doubleValue())) {
                callBack.invoke(new LatLng(predataLatLonByKey.doubleValue(), predataLatLonByKey2.doubleValue()));
            } else {
                callBack.invoke(null);
            }
        } catch (JSONException unused) {
            callBack.invoke(null);
        }
    }

    private final void isSubSegmen(Function1<? super Boolean, Unit> callBack) throws JSONException {
        try {
            JSONArray preDataJson = new JSONObject(this.mPreDefinedData).getJSONArray(PREDATA);
            Intrinsics.checkNotNullExpressionValue(preDataJson, "preDataJson");
            if (Intrinsics.areEqual(parsePredataByKey(preDataJson, IS_SUB_SEGMEN), "1")) {
                callBack.invoke(true);
            } else {
                callBack.invoke(false);
            }
        } catch (JSONException unused) {
            callBack.invoke(false);
        }
    }

    private final String parsePredataByKey(JSONArray predataJsonArray, String key) throws JSONException {
        int length = predataJsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = predataJsonArray.getJSONObject(i);
            if (Intrinsics.areEqual(jSONObject.optString("dataKey"), key)) {
                return jSONObject.optString("answer");
            }
        }
        return null;
    }

    private final Double parsePredataLatLonByKey(JSONArray predataJsonArray, String key) throws JSONException {
        int length = predataJsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = predataJsonArray.getJSONObject(i);
            if (Intrinsics.areEqual(jSONObject.optString("dataKey"), key)) {
                return Double.valueOf(jSONObject.optDouble("answer"));
            }
        }
        return null;
    }

    public final void showTemporarySaveSuccessDialog() {
        runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                FormGearActivity.showTemporarySaveSuccessDialog$lambda$52(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTemporarySaveSuccessDialog$lambda$52(final FormGearActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialog bottomSheetDialog = this$0.onlineBackupDialog;
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
        }
        final BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(this$0);
        bottomSheetDialog2.setContentView(R.layout.bottom_sheet_base_dialog);
        Window window = bottomSheetDialog2.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog2.setCanceledOnTouchOutside(false);
        bottomSheetDialog2.setCancelable(true);
        ((ImageView) bottomSheetDialog2.findViewById(R.id.icon_bottomDialog)).setVisibility(8);
        ((TextView) bottomSheetDialog2.findViewById(R.id.judul_bottomDialog)).setText("Simpan Sementara Berhasil");
        ((TextView) bottomSheetDialog2.findViewById(R.id.deskripsi_bottomDialog)).setText("Sukses menyimpan data sementara di HP Anda");
        ((Button) bottomSheetDialog2.findViewById(R.id.lButton_bottomDialog)).setText("Tutup");
        ((Button) bottomSheetDialog2.findViewById(R.id.lButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda49
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.showTemporarySaveSuccessDialog$lambda$52$lambda$48(bottomSheetDialog2, view);
            }
        });
        ((ImageView) bottomSheetDialog2.findViewById(R.id.tutup_buttomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FormGearActivity.showTemporarySaveSuccessDialog$lambda$52$lambda$49(bottomSheetDialog2, view);
            }
        });
        ((Button) bottomSheetDialog2.findViewById(R.id.rButton_bottomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws JSONException {
                FormGearActivity.showTemporarySaveSuccessDialog$lambda$52$lambda$50(this.f$0, bottomSheetDialog2, view);
            }
        });
        bottomSheetDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                FormGearActivity.showTemporarySaveSuccessDialog$lambda$52$lambda$51(this.f$0, bottomSheetDialog2, dialogInterface);
            }
        });
        this$0.onlineBackupDialog = bottomSheetDialog2;
        bottomSheetDialog2.show();
        this$0.startOnlineBackupCountdown(bottomSheetDialog2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTemporarySaveSuccessDialog$lambda$52$lambda$48(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTemporarySaveSuccessDialog$lambda$52$lambda$49(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTemporarySaveSuccessDialog$lambda$52$lambda$50(FormGearActivity this$0, BottomSheetDialog dialog, View view) throws JSONException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (this$0.isOnlineBackupReady()) {
            this$0.nextOnlineBackupAvailableAtMillis = System.currentTimeMillis() + ONLINE_BACKUP_INTERVAL_MILLIS;
            this$0.startOnlineBackupCountdown(dialog);
            this$0.submitDraft((AssignmentEntity) BuildersKt__BuildersKt.runBlocking$default(null, new FormGearActivity$showTemporarySaveSuccessDialog$1$3$assignment$1(this$0, null), 1, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTemporarySaveSuccessDialog$lambda$52$lambda$51(FormGearActivity this$0, BottomSheetDialog dialog, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.stopOnlineBackupCountdown();
        if (Intrinsics.areEqual(this$0.onlineBackupDialog, dialog)) {
            this$0.onlineBackupDialog = null;
        }
    }

    private final void startOnlineBackupCountdown(final BottomSheetDialog dialog) {
        stopOnlineBackupCountdown();
        Runnable runnable = new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.startOnlineBackupCountdown.1
            @Override // java.lang.Runnable
            public void run() {
                String str;
                if (!dialog.isShowing()) {
                    this.stopOnlineBackupCountdown();
                    return;
                }
                boolean zIsOnlineBackupReady = this.isOnlineBackupReady();
                ((Button) dialog.findViewById(R.id.rButton_bottomDialog)).setEnabled(zIsOnlineBackupReady);
                Button button = (Button) dialog.findViewById(R.id.rButton_bottomDialog);
                if (!zIsOnlineBackupReady) {
                    str = "Kirim ke Server (" + this.formatOnlineBackupRemaining() + ")";
                }
                button.setText(str);
                if (zIsOnlineBackupReady) {
                    return;
                }
                this.onlineBackupHandler.postDelayed(this, 1000L);
            }
        };
        this.onlineBackupCountdownRunnable = runnable;
        Handler handler = this.onlineBackupHandler;
        Intrinsics.checkNotNull(runnable);
        handler.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopOnlineBackupCountdown() {
        Runnable runnable = this.onlineBackupCountdownRunnable;
        if (runnable != null) {
            this.onlineBackupHandler.removeCallbacks(runnable);
        }
        this.onlineBackupCountdownRunnable = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isOnlineBackupReady() {
        return System.currentTimeMillis() >= this.nextOnlineBackupAvailableAtMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatOnlineBackupRemaining() {
        long jCoerceAtLeast = RangesKt.coerceAtLeast(this.nextOnlineBackupAvailableAtMillis - System.currentTimeMillis(), 0L) / 1000;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(jCoerceAtLeast / 60), Long.valueOf(jCoerceAtLeast % 60)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        stopOnlineBackupCountdown();
        BottomSheetDialog bottomSheetDialog = this.onlineBackupDialog;
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
        }
        this.onlineBackupDialog = null;
        super.onDestroy();
    }

    private final void submitDraft(final AssignmentEntity assignmentEntity) throws JSONException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String id2 = assignmentEntity != null ? assignmentEntity.getId() : null;
        Intrinsics.checkNotNull(id2);
        final List<String> listListOf = CollectionsKt.listOf(id2 + "_" + jCurrentTimeMillis + ".7z");
        AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
        String id3 = assignmentEntity != null ? assignmentEntity.getId() : null;
        Intrinsics.checkNotNull(id3);
        String copyFromId = assignmentEntity != null ? assignmentEntity.getCopyFromId() : null;
        String str = this.mPeriodeId;
        Intrinsics.checkNotNull(str);
        assignmentRepositoryImpl.assignmentSubmitS3Presign(id3, copyFromId, str, listListOf, new Function1<AssignmentSubmitS3Response, Unit>() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity.submitDraft.1
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
                if (assignmentSubmitS3Response != null ? Intrinsics.areEqual((Object) assignmentSubmitS3Response.getSuccess(), (Object) true) : false) {
                    Pair pairCreate7zFile = FormGearActivity.this.create7zFile(assignmentEntity);
                    File file = (File) pairCreate7zFile.component1();
                    String str2 = (String) pairCreate7zFile.component2();
                    AssignmentRepositoryImpl assignmentRepositoryImpl2 = new AssignmentRepositoryImpl();
                    List<PresignedUrlsItem> presignedUrls = assignmentSubmitS3Response.getData().getPresignedUrls();
                    String presignedUrl = (presignedUrls == null || (presignedUrlsItem = presignedUrls.get(0)) == null) ? null : presignedUrlsItem.getPresignedUrl();
                    Intrinsics.checkNotNull(presignedUrl);
                    assignmentRepositoryImpl2.assignmentSubmitS3Upload(presignedUrl, file, new C02091(listListOf, str2, assignmentEntity, FormGearActivity.this));
                }
            }

            /* compiled from: FormGearActivity.kt */
            @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            /* renamed from: id.go.bpsfasih.ui.formGear.FormGearActivity$submitDraft$1$1, reason: invalid class name and collision with other inner class name */
            static final class C02091 extends Lambda implements Function1<Boolean, Unit> {
                final /* synthetic */ AssignmentEntity $assignmentEntity;
                final /* synthetic */ List<String> $fileNames;
                final /* synthetic */ String $md5;
                final /* synthetic */ FormGearActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02091(List<String> list, String str, AssignmentEntity assignmentEntity, FormGearActivity formGearActivity) {
                    super(1);
                    this.$fileNames = list;
                    this.$md5 = str;
                    this.$assignmentEntity = assignmentEntity;
                    this.this$0 = formGearActivity;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                /* compiled from: FormGearActivity.kt */
                @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "response", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                /* renamed from: id.go.bpsfasih.ui.formGear.FormGearActivity$submitDraft$1$1$1, reason: invalid class name and collision with other inner class name */
                static final class C02101 extends Lambda implements Function1<BaseResponseDataUpload, Unit> {
                    final /* synthetic */ FormGearActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C02101(FormGearActivity formGearActivity) {
                        super(1);
                        this.this$0 = formGearActivity;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                        invoke2(baseResponseDataUpload);
                        return Unit.INSTANCE;
                    }

                    /* compiled from: FormGearActivity.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$submitDraft$1$1$1$1", f = "FormGearActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: id.go.bpsfasih.ui.formGear.FormGearActivity$submitDraft$1$1$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C02111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ BaseResponseDataUpload $response;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C02111(BaseResponseDataUpload baseResponseDataUpload, Continuation<? super C02111> continuation) {
                            super(2, continuation);
                            this.$response = baseResponseDataUpload;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C02111(this.$response, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C02111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final BaseResponseDataUpload baseResponseDataUpload) {
                        if (baseResponseDataUpload != null ? Intrinsics.areEqual((Object) baseResponseDataUpload.getSuccess(), (Object) true) : false) {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02111(baseResponseDataUpload, null), 2, null);
                            final FormGearActivity formGearActivity = this.this$0;
                            formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$submitDraft$1$1$1$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    FormGearActivity.C08731.C02091.C02101.invoke$lambda$0(formGearActivity, baseResponseDataUpload);
                                }
                            });
                        } else {
                            final FormGearActivity formGearActivity2 = this.this$0;
                            formGearActivity2.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$submitDraft$1$1$1$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    FormGearActivity.C08731.C02091.C02101.invoke$lambda$1(formGearActivity2, baseResponseDataUpload);
                                }
                            });
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void invoke$lambda$0(FormGearActivity this$0, BaseResponseDataUpload baseResponseDataUpload) {
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        this$0.hideProgressBar();
                        Toast.makeText(this$0, baseResponseDataUpload != null ? baseResponseDataUpload.getMessage() : null, 0).show();
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void invoke$lambda$1(FormGearActivity this$0, BaseResponseDataUpload baseResponseDataUpload) {
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        this$0.hideProgressBar();
                        Toast.makeText(this$0, baseResponseDataUpload != null ? baseResponseDataUpload.getMessage() : null, 0).show();
                    }
                }

                public final void invoke(boolean z) {
                    if (z) {
                        AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
                        String str = this.$fileNames.get(0);
                        String str2 = this.$md5;
                        AssignmentEntity assignmentEntity = this.$assignmentEntity;
                        String periodeNotPrimary = assignmentEntity != null ? assignmentEntity.getPeriodeNotPrimary() : null;
                        Intrinsics.checkNotNull(periodeNotPrimary);
                        AssignmentEntity assignmentEntity2 = this.$assignmentEntity;
                        String id2 = assignmentEntity2 != null ? assignmentEntity2.getId() : null;
                        Intrinsics.checkNotNull(id2);
                        AssignmentEntity assignmentEntity3 = this.$assignmentEntity;
                        Boolean boolValueOf = assignmentEntity3 != null ? Boolean.valueOf(assignmentEntity3.isNew()) : null;
                        Intrinsics.checkNotNull(boolValueOf);
                        boolean zBooleanValue = boolValueOf.booleanValue();
                        Region region = this.$assignmentEntity.getRegion();
                        String id3 = region != null ? region.getId() : null;
                        Intrinsics.checkNotNull(id3);
                        AssignmentEntity assignmentEntity4 = this.$assignmentEntity;
                        String data1 = assignmentEntity4 != null ? assignmentEntity4.getData1() : null;
                        AssignmentEntity assignmentEntity5 = this.$assignmentEntity;
                        String data2 = assignmentEntity5 != null ? assignmentEntity5.getData2() : null;
                        AssignmentEntity assignmentEntity6 = this.$assignmentEntity;
                        String data3 = assignmentEntity6 != null ? assignmentEntity6.getData3() : null;
                        AssignmentEntity assignmentEntity7 = this.$assignmentEntity;
                        String data4 = assignmentEntity7 != null ? assignmentEntity7.getData4() : null;
                        AssignmentEntity assignmentEntity8 = this.$assignmentEntity;
                        String data5 = assignmentEntity8 != null ? assignmentEntity8.getData5() : null;
                        AssignmentEntity assignmentEntity9 = this.$assignmentEntity;
                        String data6 = assignmentEntity9 != null ? assignmentEntity9.getData6() : null;
                        AssignmentEntity assignmentEntity10 = this.$assignmentEntity;
                        String data7 = assignmentEntity10 != null ? assignmentEntity10.getData7() : null;
                        AssignmentEntity assignmentEntity11 = this.$assignmentEntity;
                        String data8 = assignmentEntity11 != null ? assignmentEntity11.getData8() : null;
                        AssignmentEntity assignmentEntity12 = this.$assignmentEntity;
                        String data9 = assignmentEntity12 != null ? assignmentEntity12.getData9() : null;
                        AssignmentEntity assignmentEntity13 = this.$assignmentEntity;
                        String data10 = assignmentEntity13 != null ? assignmentEntity13.getData10() : null;
                        AssignmentEntity assignmentEntity14 = this.$assignmentEntity;
                        Float latitude = assignmentEntity14 != null ? assignmentEntity14.getLatitude() : null;
                        AssignmentEntity assignmentEntity15 = this.$assignmentEntity;
                        Float longitude = assignmentEntity15 != null ? assignmentEntity15.getLongitude() : null;
                        AssignmentEntity assignmentEntity16 = this.$assignmentEntity;
                        String copyFromId = assignmentEntity16 != null ? assignmentEntity16.getCopyFromId() : null;
                        AssignmentEntity assignmentEntity17 = this.$assignmentEntity;
                        String paradata = assignmentEntity17 != null ? assignmentEntity17.getParadata() : null;
                        AssignmentEntity assignmentEntity18 = this.$assignmentEntity;
                        String comment = assignmentEntity18 != null ? assignmentEntity18.getComment() : null;
                        AssignmentEntity assignmentEntity19 = this.$assignmentEntity;
                        assignmentRepositoryImpl.assignmentSubmitS3Post(str, str2, periodeNotPrimary, id2, zBooleanValue, true, id3, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, latitude, longitude, copyFromId, paradata, comment, assignmentEntity19 != null ? assignmentEntity19.getNote() : null, new C02101(this.this$0));
                        return;
                    }
                    final FormGearActivity formGearActivity = this.this$0;
                    formGearActivity.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$submitDraft$1$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            FormGearActivity.C08731.C02091.invoke$lambda$0(formGearActivity);
                        }
                    });
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$0(FormGearActivity this$0) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    this$0.hideProgressBar();
                    Toast.makeText(this$0, "Gagal upload data", 0).show();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.Pair<java.io.File, java.lang.String> create7zFile(id.go.bpsfasih.data.local.entities.AssignmentEntity r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearActivity.create7zFile(id.go.bpsfasih.data.local.entities.AssignmentEntity):kotlin.Pair");
    }
}

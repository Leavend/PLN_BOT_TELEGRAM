package id.go.bpsfasih.utils;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationHelper.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Ju\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062M\u0010\u0007\u001aI\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00040\b2\u0016\u0010\u0010\u001a\u0012\u0012\b\u0012\u00060\u0012j\u0002`\u0013\u0012\u0004\u0012\u00020\u00040\u0011J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006Jw\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182M\u0010\u0007\u001aI\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00040\b2\u0016\u0010\u0010\u001a\u0012\u0012\b\u0012\u00060\u0012j\u0002`\u0013\u0012\u0004\u0012\u00020\u00040\u0011H\u0002¨\u0006\u0019"}, d2 = {"Lid/go/bpsfasih/utils/LocationHelper;", "", "()V", "getCurrentLocation", "", "context", "Landroid/content/Context;", "onSuccess", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "latitude", "longitude", "", "accuracy", "onError", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "hasLocationPermission", "", "requestCurrentLocation", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationHelper {
    public static final int $stable = 0;
    public static final LocationHelper INSTANCE = new LocationHelper();

    private LocationHelper() {
    }

    public final boolean hasLocationPermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0) || (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0);
    }

    public final void getCurrentLocation(Context context, final Function3<? super Double, ? super Double, ? super Float, Unit> onSuccess, final Function1<? super Exception, Unit> onError) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (!hasLocationPermission(context)) {
            onError.invoke(new SecurityException("Location permission not granted"));
            return;
        }
        final FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(context)");
        try {
            Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
            final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: id.go.bpsfasih.utils.LocationHelper.getCurrentLocation.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                    invoke2(location);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Location location) {
                    if (location == null) {
                        LocationHelper.INSTANCE.requestCurrentLocation(fusedLocationProviderClient, onSuccess, onError);
                    } else {
                        onSuccess.invoke(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Float.valueOf(location.getAccuracy()));
                    }
                }
            };
            lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: id.go.bpsfasih.utils.LocationHelper$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    LocationHelper.getCurrentLocation$lambda$0(function1, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: id.go.bpsfasih.utils.LocationHelper$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    LocationHelper.getCurrentLocation$lambda$1(fusedLocationProviderClient, onSuccess, onError, exc);
                }
            });
        } catch (SecurityException e) {
            onError.invoke(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getCurrentLocation$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getCurrentLocation$lambda$1(FusedLocationProviderClient fusedLocationClient, Function3 onSuccess, Function1 onError, Exception exception) {
        Intrinsics.checkNotNullParameter(fusedLocationClient, "$fusedLocationClient");
        Intrinsics.checkNotNullParameter(onSuccess, "$onSuccess");
        Intrinsics.checkNotNullParameter(onError, "$onError");
        Intrinsics.checkNotNullParameter(exception, "exception");
        INSTANCE.requestCurrentLocation(fusedLocationClient, onSuccess, onError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestCurrentLocation(final FusedLocationProviderClient fusedLocationClient, final Function3<? super Double, ? super Double, ? super Float, Unit> onSuccess, final Function1<? super Exception, Unit> onError) {
        try {
            LocationRequest locationRequestBuild = new LocationRequest.Builder(100, 10000L).build();
            Intrinsics.checkNotNullExpressionValue(locationRequestBuild, "Builder(\n               …nds\n            ).build()");
            fusedLocationClient.requestLocationUpdates(locationRequestBuild, new LocationCallback() { // from class: id.go.bpsfasih.utils.LocationHelper$requestCurrentLocation$locationCallback$1
                @Override // com.google.android.gms.location.LocationCallback
                public void onLocationResult(LocationResult locationResult) {
                    Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                    Location lastLocation = locationResult.getLastLocation();
                    if (lastLocation != null) {
                        onSuccess.invoke(Double.valueOf(lastLocation.getLatitude()), Double.valueOf(lastLocation.getLongitude()), Float.valueOf(lastLocation.getAccuracy()));
                        fusedLocationClient.removeLocationUpdates(this);
                    } else {
                        onError.invoke(new Exception("Location is null"));
                    }
                }
            }, Looper.getMainLooper());
        } catch (SecurityException e) {
            onError.invoke(e);
        } catch (Exception e2) {
            onError.invoke(e2);
        }
    }
}

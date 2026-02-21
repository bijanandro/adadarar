package ir.hiup.pushsdk;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.CoroutineWorker;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PermissionCheckWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lir/hiup/pushsdk/PermissionCheckWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "log", "", NotificationCompat.CATEGORY_MESSAGE, "", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PermissionCheckWorker extends CoroutineWorker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PermissionCheckWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    private final void log(String msg) {
        if (PushSdk.INSTANCE.isLoggingEnabled()) {
            Log.d(PushSdk.LOG_TAG, "[PermCheck] " + msg);
        }
    }

    @Override // androidx.work.CoroutineWorker
    public Object doWork(Continuation<? super ListenableWorker.Result> continuation) throws Throwable {
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z = ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.POST_NOTIFICATIONS") == 0;
            Object systemService = getApplicationContext().getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            boolean zAreNotificationsEnabled = ((NotificationManager) systemService).areNotificationsEnabled();
            log("permissionGranted=" + z + ", notificationsEnabled=" + zAreNotificationsEnabled);
            Data inputData = getInputData();
            Intrinsics.checkNotNullExpressionValue(inputData, "getInputData(...)");
            String string = inputData.getString("BASE_URL");
            if (string == null) {
                ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
                Intrinsics.checkNotNullExpressionValue(resultFailure, "failure(...)");
                return resultFailure;
            }
            String string2 = inputData.getString("API_KEY");
            if (string2 == null) {
                ListenableWorker.Result resultFailure2 = ListenableWorker.Result.failure();
                Intrinsics.checkNotNullExpressionValue(resultFailure2, "failure(...)");
                return resultFailure2;
            }
            String string3 = inputData.getString("INSTALL_MARKET");
            if (string3 == null) {
                string3 = "";
            }
            String str = string3;
            long j = inputData.getLong("INTERVAL", 15L);
            String string4 = inputData.getString("UNIT");
            if (string4 == null) {
                string4 = TimeUnit.MINUTES.name();
            }
            Intrinsics.checkNotNull(string4);
            TimeUnit timeUnitValueOf = TimeUnit.valueOf(string4);
            if (z && zAreNotificationsEnabled) {
                PushSdk pushSdk = PushSdk.INSTANCE;
                Context applicationContext = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                pushSdk.enqueueWorkManagerPolling(applicationContext, string, string2, str, j, timeUnitValueOf);
                WorkManager.getInstance(getApplicationContext()).cancelUniqueWork(PushSdk.WORK_PERMISSION_CHECK);
                ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
                Intrinsics.checkNotNullExpressionValue(resultSuccess, "success(...)");
                return resultSuccess;
            }
        }
        ListenableWorker.Result resultRetry = ListenableWorker.Result.retry();
        Intrinsics.checkNotNullExpressionValue(resultRetry, "retry(...)");
        return resultRetry;
    }
}

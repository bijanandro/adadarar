package ir.hiup.pushsdk;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import ir.hiup.pushsdk.NotificationHelper;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: pushSdk.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJB\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\u000fH\u0007J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0004H\u0002J(\u0010\"\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J \u0010#\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J8\u0010$\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010%\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J2\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006'"}, d2 = {"Lir/hiup/pushsdk/PushSdk;", "", "()V", "KEY_API_KEY", "", "KEY_DEVICE_ID", "KEY_HAS_REGISTERED", "KEY_LAST_ID", "KEY_MARKET", "KEY_PREV_BASE_URL", "LOG_TAG", "PREFS", "WORK_NAME", "WORK_PERMISSION_CHECK", "isLoggingEnabled", "", "()Z", "setLoggingEnabled", "(Z)V", "enqueueWorkManagerPolling", "", "context", "Landroid/content/Context;", "baseUrl", "apiKey", PushSdk.KEY_MARKET, "interval", "", "unit", "Ljava/util/concurrent/TimeUnit;", "initialize", "useAlarmManager", "log", NotificationCompat.CATEGORY_MESSAGE, "registerDevice", "scheduleNextAlarm", "schedulePermissionCheck", "shutdown", "testPollNow", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PushSdk {
    public static final PushSdk INSTANCE = new PushSdk();
    public static final String KEY_API_KEY = "api_key";
    public static final String KEY_DEVICE_ID = "device_id";
    private static final String KEY_HAS_REGISTERED = "has_registered";
    public static final String KEY_LAST_ID = "lastId";
    public static final String KEY_MARKET = "installMarket";
    public static final String KEY_PREV_BASE_URL = "prev_base_url";
    public static final String LOG_TAG = "PushSdk";
    public static final String PREFS = "PushSdk_Prefs";
    private static final String WORK_NAME = "PushSdkPolling";
    public static final String WORK_PERMISSION_CHECK = "PushSdkPermissionCheck";
    private static boolean isLoggingEnabled;

    private PushSdk() {
    }

    public final boolean isLoggingEnabled() {
        return isLoggingEnabled;
    }

    public final void setLoggingEnabled(boolean z) {
        isLoggingEnabled = z;
    }

    @JvmStatic
    public static final void initialize(Context context, String baseUrl, String apiKey, long interval, TimeUnit unit, String installMarket, boolean useAlarmManager) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(installMarket, "installMarket");
        PushSdk pushSdk = INSTANCE;
        isLoggingEnabled = true;
        pushSdk.log("initialize – baseUrl=" + baseUrl + ", interval=" + interval + ' ' + unit + ", useAlarmManager=" + useAlarmManager);
        NotificationHelper.configureChannel(new NotificationHelper.ChannelConfig("PushSdkChannel", "Push Service", 4, "App push notifications"));
        NotificationHelper.initChannel(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        boolean z = sharedPreferences.getBoolean(KEY_HAS_REGISTERED, false);
        String string = sharedPreferences.getString(KEY_PREV_BASE_URL, null);
        if (!z || !Intrinsics.areEqual(string, baseUrl)) {
            pushSdk.log("Registering device – first time or URL changed");
            pushSdk.registerDevice(context, baseUrl, apiKey, installMarket);
            sharedPreferences.edit().putBoolean(KEY_HAS_REGISTERED, true).putString(KEY_PREV_BASE_URL, baseUrl).putString(KEY_API_KEY, apiKey).putString(KEY_MARKET, installMarket).putLong(KEY_LAST_ID, 0L).apply();
        } else {
            pushSdk.log("Skipping register; already registered (URL=" + string + ')');
        }
        if (Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") != 0) {
            pushSdk.schedulePermissionCheck(context, baseUrl, apiKey, installMarket, interval, unit);
            return;
        }
        if (useAlarmManager) {
            pushSdk.scheduleNextAlarm(context, interval, unit);
            pushSdk.log("AlarmManager polling scheduled every " + interval + ' ' + unit + " (inexact)");
            return;
        }
        pushSdk.enqueueWorkManagerPolling(context, baseUrl, apiKey, installMarket, interval, unit);
        pushSdk.log("WorkManager polling enqueued");
    }

    @JvmStatic
    public static final void shutdown(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        INSTANCE.log("shutdown – cancelling polling and permission checks");
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME);
        WorkManager.getInstance(context).cancelUniqueWork(WORK_PERMISSION_CHECK);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, (Class<?>) PollReceiver.class), 201326592);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        ((AlarmManager) systemService).cancel(broadcast);
    }

    private final void schedulePermissionCheck(Context context, String baseUrl, String apiKey, String installMarket, long interval, TimeUnit unit) throws Throwable {
        Pair[] pairArr = {TuplesKt.to("BASE_URL", baseUrl), TuplesKt.to("API_KEY", apiKey), TuplesKt.to("INSTALL_MARKET", installMarket), TuplesKt.to("INTERVAL", Long.valueOf(interval)), TuplesKt.to("UNIT", unit.name())};
        Data.Builder builder = new Data.Builder();
        for (int i = 0; i < 5; i++) {
            Pair pair = pairArr[i];
            builder.put((String) pair.getFirst(), pair.getSecond());
        }
        Data dataBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(dataBuild, "dataBuilder.build()");
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(WORK_PERMISSION_CHECK, ExistingPeriodicWorkPolicy.KEEP, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) PermissionCheckWorker.class, 15L, TimeUnit.MINUTES).setInputData(dataBuild).setInitialDelay(0L, TimeUnit.SECONDS).build());
        log("PermissionCheckWorker scheduled every 15 minutes");
    }

    public final void enqueueWorkManagerPolling(Context context, String baseUrl, String apiKey, String installMarket, long interval, TimeUnit unit) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(installMarket, "installMarket");
        Intrinsics.checkNotNullParameter(unit, "unit");
        long jNextLong = Random.INSTANCE.nextLong(0L, Math.min(unit.toMillis(interval) / ((long) 20), TimeUnit.MINUTES.toMillis(15L)));
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        Pair[] pairArr = {TuplesKt.to("BASE_URL", baseUrl), TuplesKt.to("DEVICE_ID", sharedPreferences.getString(KEY_DEVICE_ID, "")), TuplesKt.to("API_KEY", apiKey), TuplesKt.to("INSTALL_MARKET", sharedPreferences.getString(KEY_MARKET, "")), TuplesKt.to("LAST_ID", Long.valueOf(sharedPreferences.getLong(KEY_LAST_ID, 0L)))};
        Data.Builder builder = new Data.Builder();
        for (int i = 0; i < 5; i++) {
            Pair pair = pairArr[i];
            builder.put((String) pair.getFirst(), pair.getSecond());
        }
        Data dataBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(dataBuild, "dataBuilder.build()");
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) NotificationWorker.class, interval, unit).setInitialDelay(jNextLong, TimeUnit.MILLISECONDS).setInputData(dataBuild).setBackoffCriteria(BackoffPolicy.LINEAR, 1L, unit).build());
        log("enqueueWorkManagerPolling – every " + interval + ' ' + unit + " with " + jNextLong + " ms jitter");
    }

    private final void scheduleNextAlarm(Context context, long interval, TimeUnit unit) {
        long millis = unit.toMillis(interval);
        long jElapsedRealtime = SystemClock.elapsedRealtime() + millis;
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, (Class<?>) PollReceiver.class), 201326592);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        ((AlarmManager) systemService).setInexactRepeating(2, jElapsedRealtime, millis, broadcast);
    }

    private final void registerDevice(Context context, String baseUrl, String apiKey, String installMarket) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        if (!sharedPreferences.contains(KEY_DEVICE_ID)) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            sharedPreferences.edit().putString(KEY_DEVICE_ID, string).apply();
            log("registerDevice – generated deviceId=" + string);
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(baseUrl, sharedPreferences, apiKey, installMarket, context, null), 3, null);
    }

    /* JADX INFO: renamed from: ir.hiup.pushsdk.PushSdk$registerDevice$1, reason: invalid class name */
    /* JADX INFO: compiled from: pushSdk.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "ir.hiup.pushsdk.PushSdk$registerDevice$1", f = "pushSdk.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $apiKey;
        final /* synthetic */ String $baseUrl;
        final /* synthetic */ Context $context;
        final /* synthetic */ String $installMarket;
        final /* synthetic */ SharedPreferences $prefs;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, SharedPreferences sharedPreferences, String str2, String str3, Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$baseUrl = str;
            this.$prefs = sharedPreferences;
            this.$apiKey = str2;
            this.$installMarket = str3;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$baseUrl, this.$prefs, this.$apiKey, this.$installMarket, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            String str = "";
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                List listSplit$default = StringsKt.split$default((CharSequence) DeviceInfoProvider.getSupportedAbis(), new String[]{","}, false, 0, 6, (Object) null);
                JSONArray jSONArray = new JSONArray();
                Iterator it = listSplit$default.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
                JSONObject jSONObject = new JSONObject();
                SharedPreferences sharedPreferences = this.$prefs;
                String str2 = this.$apiKey;
                String str3 = this.$installMarket;
                Context context = this.$context;
                String string = sharedPreferences.getString(PushSdk.KEY_DEVICE_ID, "");
                if (string == null) {
                    string = "";
                }
                jSONObject.put("deviceId", string);
                jSONObject.put("apiKey", str2);
                jSONObject.put(PushSdk.KEY_MARKET, str3);
                jSONObject.put("appVersion", DeviceInfoProvider.getAppVersion(context));
                jSONObject.put("androidVersion", DeviceInfoProvider.getAndroidVersion());
                jSONObject.put("sdkInt", DeviceInfoProvider.getSdkInt());
                jSONObject.put("manufacturer", DeviceInfoProvider.getManufacturer());
                jSONObject.put("model", DeviceInfoProvider.getModel());
                jSONObject.put("androidId", DeviceInfoProvider.getAndroidId(context));
                jSONObject.put("locale", DeviceInfoProvider.getLocale());
                jSONObject.put("timeZone", DeviceInfoProvider.getTimeZone());
                jSONObject.put("abis", jSONArray);
                jSONObject.put("screenWidth", DeviceInfoProvider.getScreenWidth(context));
                jSONObject.put("screenHeight", DeviceInfoProvider.getScreenHeight(context));
                jSONObject.put("densityDpi", DeviceInfoProvider.getScreenDensity(context));
                PushSdk.INSTANCE.log("registerDevice – payload=" + jSONObject);
                RequestBody.Companion companion = RequestBody.INSTANCE;
                MediaType mediaType = MediaType.INSTANCE.parse("application/json");
                String string2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                Response responseExecute = new OkHttpClient().newCall(new Request.Builder().url(this.$baseUrl + "/api/register").post(companion.create(mediaType, string2)).addHeader("Accept", "application/json").build()).execute();
                try {
                    Response response = responseExecute;
                    PushSdk.INSTANCE.log("registerDevice – response code=" + response.code());
                    PushSdk pushSdk = PushSdk.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("registerDevice – response body=");
                    ResponseBody responseBodyBody = response.body();
                    String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                    if (strString != null) {
                        str = strString;
                    }
                    sb.append(str);
                    pushSdk.log(sb.toString());
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(responseExecute, null);
                } finally {
                }
            } catch (Exception e) {
                PushSdk.INSTANCE.log("registerDevice exception: " + e.getLocalizedMessage());
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void log(String msg) {
        if (isLoggingEnabled) {
            Log.d(LOG_TAG, msg);
        }
    }

    public static /* synthetic */ void testPollNow$default(Context context, String str, String str2, String str3, boolean z, int i, Object obj) throws Throwable {
        if ((i & 16) != 0) {
            z = false;
        }
        testPollNow(context, str, str2, str3, z);
    }

    @JvmStatic
    public static final void testPollNow(Context context, String baseUrl, String apiKey, String installMarket, boolean useAlarmManager) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(installMarket, "installMarket");
        INSTANCE.log("testPollNow – enqueueing one-off poll");
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        Pair[] pairArr = {TuplesKt.to("BASE_URL", baseUrl), TuplesKt.to("DEVICE_ID", sharedPreferences.getString(KEY_DEVICE_ID, "")), TuplesKt.to("API_KEY", apiKey), TuplesKt.to("INSTALL_MARKET", sharedPreferences.getString(KEY_MARKET, "")), TuplesKt.to("LAST_ID", Long.valueOf(sharedPreferences.getLong(KEY_LAST_ID, 0L)))};
        Data.Builder builder = new Data.Builder();
        for (int i = 0; i < 5; i++) {
            Pair pair = pairArr[i];
            builder.put((String) pair.getFirst(), pair.getSecond());
        }
        Data dataBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(dataBuild, "dataBuilder.build()");
        WorkManager.getInstance(context).enqueue(new OneTimeWorkRequest.Builder(NotificationWorker.class).setInputData(dataBuild).build());
        if (useAlarmManager) {
            long jElapsedRealtime = SystemClock.elapsedRealtime() + TimeUnit.MINUTES.toMillis(1L);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, (Class<?>) PollReceiver.class), 201326592);
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
            ((AlarmManager) systemService).set(2, jElapsedRealtime, broadcast);
        }
    }
}

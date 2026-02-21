package ir.hiup.pushsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.unity.androidnotifications.UnityNotificationManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lir/hiup/pushsdk/NotificationWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleNotification", "", "json", "Lorg/json/JSONObject;", "log", NotificationCompat.CATEGORY_MESSAGE, "", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class NotificationWorker extends CoroutineWorker {

    /* JADX INFO: renamed from: ir.hiup.pushsdk.NotificationWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationWorker.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "ir.hiup.pushsdk.NotificationWorker", f = "NotificationWorker.kt", i = {}, l = {28}, m = "doWork", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationWorker.this.doWork(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void log(String msg) {
        if (PushSdk.INSTANCE.isLoggingEnabled()) {
            Log.d(PushSdk.LOG_TAG, "[Worker] " + msg);
        }
    }

    /* JADX INFO: renamed from: ir.hiup.pushsdk.NotificationWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: NotificationWorker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "ir.hiup.pushsdk.NotificationWorker$doWork$2", f = "NotificationWorker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NotificationWorker.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            ListenableWorker.Result resultRetry;
            HttpUrl.Builder builderNewBuilder;
            HttpUrl.Builder builderAddQueryParameter;
            HttpUrl.Builder builderAddQueryParameter2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                NotificationWorker.this.log("doWork started");
                String string = NotificationWorker.this.getInputData().getString("BASE_URL");
                if (string == null) {
                    ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
                    NotificationWorker.this.log("Missing BASE_URL");
                    return resultFailure;
                }
                String string2 = NotificationWorker.this.getInputData().getString("DEVICE_ID");
                if (string2 == null) {
                    ListenableWorker.Result resultFailure2 = ListenableWorker.Result.failure();
                    NotificationWorker.this.log("Missing DEVICE_ID");
                    return resultFailure2;
                }
                String string3 = NotificationWorker.this.getInputData().getString("API_KEY");
                if (string3 == null) {
                    ListenableWorker.Result resultFailure3 = ListenableWorker.Result.failure();
                    NotificationWorker.this.log("Missing API_KEY");
                    return resultFailure3;
                }
                String string4 = NotificationWorker.this.getInputData().getString("INSTALL_MARKET");
                String str = "";
                if (string4 == null) {
                    string4 = "";
                }
                long j = NotificationWorker.this.getInputData().getLong("LAST_ID", 0L);
                NotificationWorker.this.log("Params: lastId=" + j + ", installMarket=" + string4);
                OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().callTimeout(5L, TimeUnit.SECONDS).build();
                HttpUrl httpUrl = HttpUrl.INSTANCE.parse(string + "/api/notifications");
                HttpUrl httpUrlBuild = (httpUrl == null || (builderNewBuilder = httpUrl.newBuilder()) == null || (builderAddQueryParameter = builderNewBuilder.addQueryParameter(PushSdk.KEY_LAST_ID, String.valueOf(j))) == null || (builderAddQueryParameter2 = builderAddQueryParameter.addQueryParameter(PushSdk.KEY_MARKET, string4)) == null) ? null : builderAddQueryParameter2.build();
                if (httpUrlBuild == null) {
                    NotificationWorker.this.log("Invalid URL: " + string + "/api/notifications");
                    return ListenableWorker.Result.failure();
                }
                NotificationWorker.this.log("Request URL: " + httpUrlBuild);
                try {
                    Response responseExecute = okHttpClientBuild.newCall(new Request.Builder().url(httpUrlBuild).addHeader("Accept", "application/json").addHeader("X-Api-Key", string3).addHeader("X-Device-Id", string2).get().build()).execute();
                    NotificationWorker notificationWorker = NotificationWorker.this;
                    try {
                        Response response = responseExecute;
                        int iCode = response.code();
                        notificationWorker.log("HTTP response code=" + iCode);
                        if (iCode == 204) {
                            notificationWorker.log("No Content (204), nothing to do");
                            ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
                            CloseableKt.closeFinally(responseExecute, null);
                            return resultSuccess;
                        }
                        ResponseBody responseBodyBody = response.body();
                        String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                        if (strString != null) {
                            str = strString;
                        }
                        notificationWorker.log("Response body: " + str);
                        if (200 <= iCode && iCode < 300) {
                            notificationWorker.handleNotification(new JSONObject(str));
                            resultRetry = ListenableWorker.Result.success();
                        } else if (iCode == 304) {
                            notificationWorker.log("No updates (304)");
                            resultRetry = ListenableWorker.Result.success();
                        } else {
                            notificationWorker.log("Unexpected HTTP " + iCode + ", retrying");
                            resultRetry = ListenableWorker.Result.retry();
                        }
                        CloseableKt.closeFinally(responseExecute, null);
                        return resultRetry;
                    } finally {
                    }
                } catch (Exception e) {
                    NotificationWorker.this.log("Exception: " + e.getLocalizedMessage());
                    return ListenableWorker.Result.retry();
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof ir.hiup.pushsdk.NotificationWorker.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            ir.hiup.pushsdk.NotificationWorker$doWork$1 r0 = (ir.hiup.pushsdk.NotificationWorker.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            ir.hiup.pushsdk.NotificationWorker$doWork$1 r0 = new ir.hiup.pushsdk.NotificationWorker$doWork$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            ir.hiup.pushsdk.NotificationWorker$doWork$2 r2 = new ir.hiup.pushsdk.NotificationWorker$doWork$2
            r4 = 0
            r2.<init>(r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L4c
            return r1
        L4c:
            java.lang.String r0 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ir.hiup.pushsdk.NotificationWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleNotification(JSONObject json) {
        log("handleNotification JSON=" + json);
        long time = 0;
        long jOptLong = json.optLong(PushSdk.KEY_LAST_ID, 0L);
        log("Parsed newLastId=" + jOptLong);
        if (jOptLong <= 0) {
            log("No valid lastId, skipping");
            return;
        }
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PushSdk.PREFS, 0);
        long j = sharedPreferences.getLong(PushSdk.KEY_LAST_ID, 0L);
        log("Saved lastId=" + j);
        if (jOptLong > j) {
            sharedPreferences.edit().putLong(PushSdk.KEY_LAST_ID, jOptLong).apply();
            log("Updated saved lastId to " + jOptLong);
            JSONObject jSONObjectOptJSONObject = json.optJSONObject("notification");
            if (jSONObjectOptJSONObject == null) {
                log("No 'notification' object in JSON, skipping");
                return;
            }
            int iOptInt = jSONObjectOptJSONObject.optInt(UnityNotificationManager.KEY_ID);
            String strOptString = jSONObjectOptJSONObject.optString("title");
            String strOptString2 = jSONObjectOptJSONObject.optString("body");
            String strOptString3 = jSONObjectOptJSONObject.optString("click_action");
            String strOptString4 = jSONObjectOptJSONObject.optString("icon_url", null);
            String strOptString5 = jSONObjectOptJSONObject.optString("image_url", null);
            String strOptString6 = jSONObjectOptJSONObject.optString("start_at");
            log("Notification parsed: id=" + iOptInt + ", title='" + strOptString + "', startAtIso=" + strOptString6);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                Date date = simpleDateFormat.parse(strOptString6);
                if (date != null) {
                    time = date.getTime();
                }
            } catch (Exception e) {
                log("Date parse error: " + e.getLocalizedMessage());
            }
            long j2 = time;
            log("Computed showAtMillis=" + j2 + ", now=" + System.currentTimeMillis());
            if (j2 > System.currentTimeMillis()) {
                log("Scheduling notification id=" + iOptInt + " for future display");
                Context applicationContext = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                Intrinsics.checkNotNull(strOptString);
                Intrinsics.checkNotNull(strOptString2);
                Intrinsics.checkNotNull(strOptString3);
                NotificationHelper.schedule(applicationContext, iOptInt, strOptString, strOptString2, strOptString3, strOptString4, strOptString5, j2);
                return;
            }
            log("Showing notification id=" + iOptInt + " immediately");
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
            Intrinsics.checkNotNull(strOptString);
            Intrinsics.checkNotNull(strOptString2);
            Intrinsics.checkNotNull(strOptString3);
            NotificationHelper.show(applicationContext2, iOptInt, strOptString, strOptString2, strOptString3, strOptString4, strOptString5);
            return;
        }
        log("Already handled latest notification, skipping");
    }
}

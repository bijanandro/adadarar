package ir.hiup.pushsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.unity.androidnotifications.UnityNotificationManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: compiled from: PollReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lir/hiup/pushsdk/PollReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "ctx", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PollReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context ctx, Intent intent) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Log.d(PushSdk.LOG_TAG, "PollReceiver fired");
        new Thread(new Runnable() { // from class: ir.hiup.pushsdk.PollReceiver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PollReceiver.onReceive$lambda$2(ctx);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onReceive$lambda$2(Context ctx) {
        String str = "";
        Intrinsics.checkNotNullParameter(ctx, "$ctx");
        try {
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(PushSdk.PREFS, 0);
            String string = sharedPreferences.getString(PushSdk.KEY_PREV_BASE_URL, "");
            if (string == null) {
                string = "";
            }
            String string2 = sharedPreferences.getString(PushSdk.KEY_DEVICE_ID, "");
            if (string2 == null) {
                string2 = "";
            }
            String string3 = sharedPreferences.getString(PushSdk.KEY_API_KEY, "");
            if (string3 == null) {
                string3 = "";
            }
            long j = sharedPreferences.getLong(PushSdk.KEY_LAST_ID, 0L);
            String string4 = sharedPreferences.getString(PushSdk.KEY_MARKET, "");
            if (string4 == null) {
                string4 = "";
            }
            Response responseExecute = new OkHttpClient().newCall(new Request.Builder().url(string + "/api/notifications?lastId=" + j + "&installMarket=" + string4).addHeader("Accept", "application/json").addHeader("X-Api-Key", string3).addHeader("X-Device-Id", string2).get().build()).execute();
            try {
                Response response = responseExecute;
                int iCode = response.code();
                if (iCode == 200) {
                    ResponseBody responseBodyBody = response.body();
                    String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                    if (strString != null) {
                        str = strString;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    sharedPreferences.edit().putLong(PushSdk.KEY_LAST_ID, jSONObject.optLong(PushSdk.KEY_LAST_ID, j)).apply();
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("notification");
                    if (jSONObjectOptJSONObject != null) {
                        Intrinsics.checkNotNull(jSONObjectOptJSONObject);
                        int iOptInt = jSONObjectOptJSONObject.optInt(UnityNotificationManager.KEY_ID);
                        String strOptString = jSONObjectOptJSONObject.optString("title");
                        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                        String strOptString2 = jSONObjectOptJSONObject.optString("body");
                        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                        String strOptString3 = jSONObjectOptJSONObject.optString("click_action");
                        Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                        NotificationHelper.show(ctx, iOptInt, strOptString, strOptString2, strOptString3, jSONObjectOptJSONObject.optString("icon_url", null), jSONObjectOptJSONObject.optString("image_url", null));
                        Unit unit = Unit.INSTANCE;
                    }
                } else if (iCode == 304) {
                    Integer.valueOf(Log.d(PushSdk.LOG_TAG, "No updates (304)"));
                } else {
                    Integer.valueOf(Log.d(PushSdk.LOG_TAG, "Unexpected HTTP " + response.code()));
                }
                CloseableKt.closeFinally(responseExecute, null);
            } finally {
            }
        } catch (Exception e) {
            Log.e(PushSdk.LOG_TAG, "Poll failed", e);
        }
    }
}

package ir.hiup.pushsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlarmReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lir/hiup/pushsdk/AlarmReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class AlarmReceiver extends BroadcastReceiver {
    public static final String EXTRA_BODY = "ir.hiup.pushsdk.EXTRA_BODY";
    public static final String EXTRA_CLICK = "ir.hiup.pushsdk.EXTRA_CLICK";
    public static final String EXTRA_ICON_URL = "ir.hiup.pushsdk.EXTRA_ICON_URL";
    public static final String EXTRA_ID = "ir.hiup.pushsdk.EXTRA_ID";
    public static final String EXTRA_IMAGE_URL = "ir.hiup.pushsdk.EXTRA_IMAGE_URL";
    public static final String EXTRA_TITLE = "ir.hiup.pushsdk.EXTRA_TITLE";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        int intExtra = intent.getIntExtra(EXTRA_ID, 0);
        String stringExtra = intent.getStringExtra(EXTRA_TITLE);
        String str = stringExtra == null ? "" : stringExtra;
        String stringExtra2 = intent.getStringExtra(EXTRA_BODY);
        String str2 = stringExtra2 == null ? "" : stringExtra2;
        String stringExtra3 = intent.getStringExtra(EXTRA_CLICK);
        String str3 = stringExtra3 == null ? "" : stringExtra3;
        String stringExtra4 = intent.getStringExtra(EXTRA_ICON_URL);
        String stringExtra5 = intent.getStringExtra(EXTRA_IMAGE_URL);
        if (PushSdk.INSTANCE.isLoggingEnabled()) {
            Log.d(PushSdk.LOG_TAG, "[AlarmReceiver] id=" + intExtra + " title=" + str);
        }
        NotificationHelper.show(context, intExtra, str, str2, str3, stringExtra4, stringExtra5);
    }
}

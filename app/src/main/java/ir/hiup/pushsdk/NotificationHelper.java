package ir.hiup.pushsdk;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.Glide;
import com.unity.androidnotifications.UnityNotificationManager;
import com.unity3d.ads.metadata.MediationMetaData;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NotificationHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002JL\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0007JD\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lir/hiup/pushsdk/NotificationHelper;", "", "()V", "LOG_TAG", "", "channelConfig", "Lir/hiup/pushsdk/NotificationHelper$ChannelConfig;", "configureChannel", "", "config", "initChannel", "ctx", "Landroid/content/Context;", "log", NotificationCompat.CATEGORY_MESSAGE, "schedule", UnityNotificationManager.KEY_ID, "", "title", "body", "clickAction", "iconUrl", "imageUrl", "showAt", "", "show", "ChannelConfig", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class NotificationHelper {
    private static final String LOG_TAG = "PushSdk";
    public static final NotificationHelper INSTANCE = new NotificationHelper();
    private static ChannelConfig channelConfig = new ChannelConfig(null, null, 0, null, 15, null);

    private NotificationHelper() {
    }

    /* JADX INFO: compiled from: NotificationHelper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lir/hiup/pushsdk/NotificationHelper$ChannelConfig;", "", UnityNotificationManager.KEY_ID, "", MediationMetaData.KEY_NAME, "importance", "", "description", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getId", "getImportance", "()I", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class ChannelConfig {
        private final String description;
        private final String id;
        private final int importance;
        private final String name;

        public ChannelConfig() {
            this(null, null, 0, null, 15, null);
        }

        public static /* synthetic */ ChannelConfig copy$default(ChannelConfig channelConfig, String str, String str2, int i, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = channelConfig.id;
            }
            if ((i2 & 2) != 0) {
                str2 = channelConfig.name;
            }
            if ((i2 & 4) != 0) {
                i = channelConfig.importance;
            }
            if ((i2 & 8) != 0) {
                str3 = channelConfig.description;
            }
            return channelConfig.copy(str, str2, i, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getImportance() {
            return this.importance;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final ChannelConfig copy(String id, String name, int importance, String description) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            return new ChannelConfig(id, name, importance, description);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChannelConfig)) {
                return false;
            }
            ChannelConfig channelConfig = (ChannelConfig) other;
            return Intrinsics.areEqual(this.id, channelConfig.id) && Intrinsics.areEqual(this.name, channelConfig.name) && this.importance == channelConfig.importance && Intrinsics.areEqual(this.description, channelConfig.description);
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.importance) * 31) + this.description.hashCode();
        }

        public String toString() {
            return "ChannelConfig(id=" + this.id + ", name=" + this.name + ", importance=" + this.importance + ", description=" + this.description + ')';
        }

        public ChannelConfig(String id, String name, int i, String description) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            this.id = id;
            this.name = name;
            this.importance = i;
            this.description = description;
        }

        public /* synthetic */ ChannelConfig(String str, String str2, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? "PushSdkChannel" : str, (i2 & 2) != 0 ? "Push Service" : str2, (i2 & 4) != 0 ? 4 : i, (i2 & 8) != 0 ? "App push notifications" : str3);
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final int getImportance() {
            return this.importance;
        }

        public final String getDescription() {
            return this.description;
        }
    }

    private final void log(String msg) {
        if (PushSdk.INSTANCE.isLoggingEnabled()) {
            Log.d("PushSdk", "[Helper] " + msg);
        }
    }

    @JvmStatic
    public static final void configureChannel(ChannelConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        NotificationHelper notificationHelper = INSTANCE;
        channelConfig = config;
        notificationHelper.log("configureChannel – " + config);
    }

    @JvmStatic
    public static final void initChannel(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        Object systemService = ctx.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (notificationManager.getNotificationChannel(channelConfig.getId()) != null) {
            INSTANCE.log("initChannel – channel already exists");
            return;
        }
        ComponentDialog$$ExternalSyntheticApiModelOutline0.m18m();
        NotificationChannel notificationChannelM = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(channelConfig.getId(), channelConfig.getName(), channelConfig.getImportance());
        notificationChannelM.setDescription(channelConfig.getDescription());
        notificationChannelM.enableLights(true);
        notificationChannelM.setLightColor(-16776961);
        notificationChannelM.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannelM);
        INSTANCE.log("initChannel – created channel " + notificationChannelM.getId());
    }

    @JvmStatic
    public static final void show(Context ctx, int id, String title, String body, String clickAction, String iconUrl, String imageUrl) {
        Intent intent;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(clickAction, "clickAction");
        initChannel(ctx);
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(ctx, channelConfig.getId()).setSmallIcon(ctx.getApplicationInfo().icon).setContentTitle(title).setContentText(body).setAutoCancel(true);
        Intrinsics.checkNotNullExpressionValue(autoCancel, "setAutoCancel(...)");
        String str = iconUrl;
        if (str != null && !StringsKt.isBlank(str) && !Intrinsics.areEqual(iconUrl, "null")) {
            try {
                autoCancel.setLargeIcon(Glide.with(ctx).asBitmap().load(iconUrl).submit().get());
            } catch (Exception e) {
                INSTANCE.log("Failed to load largeIcon: " + e.getLocalizedMessage());
            }
        }
        String str2 = imageUrl;
        if (str2 != null && !StringsKt.isBlank(str2) && !Intrinsics.areEqual(imageUrl, "null")) {
            try {
                autoCancel.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(Glide.with(ctx).asBitmap().load(imageUrl).submit().get()).bigLargeIcon(null));
            } catch (Exception e2) {
                INSTANCE.log("Failed to load bigPicture: " + e2.getLocalizedMessage());
            }
        }
        if (StringsKt.equals(clickAction, "OPEN_APP", true)) {
            intent = ctx.getPackageManager().getLaunchIntentForPackage(ctx.getPackageName());
        } else if (StringsKt.startsWith(clickAction, "http://", true) || StringsKt.startsWith(clickAction, "https://", true)) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(clickAction));
        } else {
            intent = ctx.getPackageManager().getLaunchIntentForPackage(ctx.getPackageName());
        }
        if (intent != null) {
            intent.addFlags(335544320);
        }
        autoCancel.setContentIntent(PendingIntent.getActivity(ctx, id, intent, 201326592));
        Object systemService = ctx.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).notify(id, autoCancel.build());
    }

    @JvmStatic
    public static final void schedule(Context ctx, int id, String title, String body, String clickAction, String iconUrl, String imageUrl, long showAt) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(clickAction, "clickAction");
        NotificationHelper notificationHelper = INSTANCE;
        notificationHelper.log("schedule – id=" + id + " at " + showAt);
        initChannel(ctx);
        Intent intent = new Intent(ctx, (Class<?>) AlarmReceiver.class);
        intent.putExtra(AlarmReceiver.EXTRA_ID, id);
        intent.putExtra(AlarmReceiver.EXTRA_TITLE, title);
        intent.putExtra(AlarmReceiver.EXTRA_BODY, body);
        intent.putExtra(AlarmReceiver.EXTRA_CLICK, clickAction);
        intent.putExtra(AlarmReceiver.EXTRA_ICON_URL, iconUrl);
        intent.putExtra(AlarmReceiver.EXTRA_IMAGE_URL, imageUrl);
        PendingIntent broadcast = PendingIntent.getBroadcast(ctx, id, intent, 201326592);
        Object systemService = ctx.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        ((AlarmManager) systemService).setExactAndAllowWhileIdle(0, showAt, broadcast);
        notificationHelper.log("schedule – exact alarm set (id=" + id + ')');
    }
}

package com.unity.androidnotifications;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Base64;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.unity3d.player.UnityPlayer;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class UnityNotificationManager extends BroadcastReceiver {
    public static final String KEY_BIG_CONTENT_DESCRIPTION = "com.unity.BigContentDescription";
    public static final String KEY_BIG_CONTENT_TITLE = "com.unity.BigContentTytle";
    public static final String KEY_BIG_LARGE_ICON = "com.unity.BigLargeIcon";
    public static final String KEY_BIG_PICTURE = "com.unity.BigPicture";
    public static final String KEY_BIG_SHOW_WHEN_COLLAPSED = "com.unity.BigShowWhenCollapsed";
    public static final String KEY_BIG_SUMMARY_TEXT = "com.unity.BigSummaryText";
    public static final String KEY_CHANNEL_ID = "channelID";
    public static final String KEY_FIRE_TIME = "fireTime";
    public static final String KEY_ID = "id";
    public static final String KEY_INTENT_DATA = "data";
    public static final String KEY_LARGE_ICON = "largeIcon";
    public static final String KEY_NOTIFICATION = "unityNotification";
    public static final String KEY_NOTIFICATION_DISMISSED = "com.unity.NotificationDismissed";
    public static final String KEY_NOTIFICATION_ID = "com.unity.NotificationID";
    public static final String KEY_REPEAT_INTERVAL = "repeatInterval";
    public static final String KEY_SHOW_IN_FOREGROUND = "com.unity.showInForeground";
    public static final String KEY_SMALL_ICON = "smallIcon";
    public static UnityNotificationManager j;
    public g d;
    public Random e;
    public HashSet f;
    public ConcurrentHashMap g;
    public NotificationCallback h;
    public Context a = null;
    public Activity b = null;
    public Class c = null;
    public int i = -1;

    public static synchronized UnityNotificationManager a(Context context) {
        if (j == null) {
            UnityNotificationManager unityNotificationManager = new UnityNotificationManager();
            j = unityNotificationManager;
            unityNotificationManager.f = new HashSet();
            j.g = new ConcurrentHashMap();
        }
        j.a = context.getApplicationContext();
        return j;
    }

    public static String getNotificationChannelId(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getChannelId();
        }
        return null;
    }

    public static Integer getNotificationColor(Notification notification) {
        if (Build.VERSION.SDK_INT < 26 || notification.extras.containsKey(NotificationCompat.EXTRA_COLORIZED)) {
            return Integer.valueOf(notification.color);
        }
        return null;
    }

    public static int getNotificationGroupAlertBehavior(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getGroupAlertBehavior();
        }
        return 0;
    }

    public static synchronized UnityNotificationManager getNotificationManagerImpl(Activity activity, NotificationCallback notificationCallback) {
        if (j == null) {
            j = new UnityNotificationManager();
        }
        UnityNotificationManager unityNotificationManager = j;
        unityNotificationManager.getClass();
        unityNotificationManager.a = activity.getApplicationContext();
        unityNotificationManager.b = activity;
        unityNotificationManager.h = notificationCallback;
        if (unityNotificationManager.g == null) {
            unityNotificationManager.g = new ConcurrentHashMap();
        }
        g gVar = unityNotificationManager.d;
        if (gVar == null || !gVar.isAlive()) {
            unityNotificationManager.d = new g(unityNotificationManager, unityNotificationManager.g);
        }
        if (unityNotificationManager.e == null) {
            unityNotificationManager.e = new Random();
        }
        if (unityNotificationManager.f == null) {
            unityNotificationManager.f = new HashSet();
        }
        unityNotificationManager.a();
        Class clsA = h.a(unityNotificationManager.a);
        unityNotificationManager.c = clsA;
        if (clsA == null) {
            throw new RuntimeException("Failed to determine Activity to be opened when tapping notification");
        }
        if (!unityNotificationManager.d.isAlive()) {
            unityNotificationManager.d.start();
        }
        return j;
    }

    public static void setNotificationColor(Notification.Builder builder, int i) {
        if (i != 0) {
            builder.setColor(i);
            if (Build.VERSION.SDK_INT >= 26) {
                builder.setColorized(true);
            }
        }
    }

    public static void setNotificationGroupAlertBehavior(Notification.Builder builder, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setGroupAlertBehavior(i);
        }
    }

    public static void setNotificationIcon(Notification.Builder builder, String str, String str2) {
        if (str2 == null || (str2.length() == 0 && builder.getExtras().getString(str) != null)) {
            builder.getExtras().remove(str);
        } else {
            builder.getExtras().putString(str, str2);
        }
    }

    public static void setNotificationUsesChronometer(Notification.Builder builder, boolean z) {
        builder.setUsesChronometer(z);
    }

    public int areNotificationsEnabled() {
        boolean z = Build.VERSION.SDK_INT < 33 || this.a.checkCallingOrSelfPermission("android.permission.POST_NOTIFICATIONS") == 0;
        boolean zAreNotificationsEnabled = getNotificationManager().areNotificationsEnabled();
        if (z) {
            return zAreNotificationsEnabled ? 1 : 5;
        }
        return 2;
    }

    public final synchronized Set b() {
        return this.a.getSharedPreferences("UNITY_STORED_NOTIFICATION_IDS", 0).getStringSet("UNITY_NOTIFICATION_IDS", new HashSet());
    }

    public boolean canScheduleExactAlarms() {
        Bundle bundleA;
        AlarmManager alarmManager = (AlarmManager) this.a.getSystemService(NotificationCompat.CATEGORY_ALARM);
        int i = Build.VERSION.SDK_INT;
        if (this.i < 0 && (bundleA = a()) != null) {
            this.i = bundleA.getInt("com.unity.androidnotifications.exact_scheduling", 1);
        }
        if (this.i == 0) {
            return false;
        }
        if (i < 31) {
            return true;
        }
        return alarmManager.canScheduleExactAlarms();
    }

    public void cancelAllNotifications() {
        getNotificationManager().cancelAll();
    }

    public void cancelAllPendingNotificationIntents() {
        this.d.a();
    }

    public void cancelDisplayedNotification(int i) {
        getNotificationManager().cancel(i);
    }

    public void cancelPendingNotification(int i) {
        this.d.a(i);
    }

    public boolean checkIfPendingNotificationIsRegistered(int i) {
        return a(i, new Intent(this.b, (Class<?>) UnityNotificationManager.class), 536870912) != null;
    }

    public Notification.Builder createNotificationBuilder(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            h$$ExternalSyntheticApiModelOutline0.m();
            return h$$ExternalSyntheticApiModelOutline0.m(this.a, str);
        }
        Notification.Builder builder = new Notification.Builder(this.a);
        a notificationChannel = getNotificationChannel(str);
        long[] jArr = notificationChannel.c;
        int i = -1;
        if (jArr == null || jArr.length <= 0) {
            builder.setDefaults(-1);
        } else {
            builder.setDefaults(5);
            builder.setVibrate(notificationChannel.c);
        }
        builder.setVisibility(notificationChannel.d);
        int i2 = notificationChannel.b;
        if (i2 == 0) {
            i = -2;
        } else if (i2 != 2) {
            i = (i2 == 3 || i2 != 4) ? 0 : 2;
        }
        builder.setPriority(i);
        builder.getExtras().putString(KEY_CHANNEL_ID, str);
        return builder;
    }

    public void deleteNotificationChannel(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            getNotificationManager().deleteNotificationChannel(str);
            return;
        }
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("UNITY_NOTIFICATIONS", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("ChannelIDs", new HashSet());
        if (stringSet.contains(str)) {
            HashSet hashSet = new HashSet(stringSet);
            hashSet.remove(str);
            SharedPreferences.Editor editorClear = sharedPreferences.edit().clear();
            editorClear.putStringSet("ChannelIDs", hashSet);
            editorClear.apply();
            this.a.getSharedPreferences("unity_notification_channel_" + str, 0).edit().clear().apply();
        }
    }

    public void deleteNotificationChannelGroup(String str) {
        if (str == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            getNotificationManager().deleteNotificationChannelGroup(str);
            return;
        }
        for (a aVar : getNotificationChannels()) {
            if (str.equals(aVar.e)) {
                deleteNotificationChannel(aVar.a);
            }
        }
    }

    public a getNotificationChannel(String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = a(this.a).getNotificationManager().getNotificationChannel(str);
            if (notificationChannel == null) {
                return null;
            }
            a aVar = new a();
            aVar.a = notificationChannel.getId();
            notificationChannel.getName().toString();
            aVar.b = notificationChannel.getImportance();
            notificationChannel.getDescription();
            notificationChannel.shouldShowLights();
            notificationChannel.shouldVibrate();
            notificationChannel.canBypassDnd();
            notificationChannel.canShowBadge();
            aVar.c = notificationChannel.getVibrationPattern();
            aVar.d = notificationChannel.getLockscreenVisibility();
            aVar.e = notificationChannel.getGroup();
            return aVar;
        }
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("unity_notification_channel_" + str, 0);
        a aVar2 = new a();
        aVar2.a = str;
        sharedPreferences.getString("title", "undefined");
        aVar2.b = sharedPreferences.getInt("importance", 3);
        sharedPreferences.getString("description", "undefined");
        sharedPreferences.getBoolean("enableLights", false);
        sharedPreferences.getBoolean("enableVibration", false);
        sharedPreferences.getBoolean("canBypassDnd", false);
        sharedPreferences.getBoolean("canShowBadge", false);
        aVar2.d = sharedPreferences.getInt("lockscreenVisibility", 1);
        aVar2.e = sharedPreferences.getString("group", null);
        String[] strArrSplit = sharedPreferences.getString("vibrationPattern", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI).split(",");
        int length = strArrSplit.length;
        long[] jArr = new long[length];
        if (length > 1) {
            for (int i = 0; i < strArrSplit.length; i++) {
                try {
                    jArr[i] = Long.parseLong(strArrSplit[i]);
                } catch (NumberFormatException unused) {
                    jArr[i] = 1;
                }
            }
        }
        aVar2.c = length > 1 ? jArr : null;
        return aVar2;
    }

    public a[] getNotificationChannels() {
        int i = 0;
        if (Build.VERSION.SDK_INT < 26) {
            Set<String> stringSet = this.a.getSharedPreferences("UNITY_NOTIFICATIONS", 0).getStringSet("ChannelIDs", new HashSet());
            if (stringSet.size() == 0) {
                return null;
            }
            a[] aVarArr = new a[stringSet.size()];
            Iterator<String> it = stringSet.iterator();
            while (it.hasNext()) {
                aVarArr[i] = getNotificationChannel(it.next());
                i++;
            }
            return aVarArr;
        }
        List notificationChannels = getNotificationManager().getNotificationChannels();
        if (notificationChannels.size() == 0) {
            return null;
        }
        a[] aVarArr2 = new a[notificationChannels.size()];
        Iterator it2 = notificationChannels.iterator();
        while (it2.hasNext()) {
            NotificationChannel notificationChannelM = h$$ExternalSyntheticApiModelOutline0.m(it2.next());
            a aVar = new a();
            aVar.a = notificationChannelM.getId();
            notificationChannelM.getName().toString();
            aVar.b = notificationChannelM.getImportance();
            notificationChannelM.getDescription();
            notificationChannelM.shouldShowLights();
            notificationChannelM.shouldVibrate();
            notificationChannelM.canBypassDnd();
            notificationChannelM.canShowBadge();
            aVar.c = notificationChannelM.getVibrationPattern();
            aVar.d = notificationChannelM.getLockscreenVisibility();
            aVar.e = notificationChannelM.getGroup();
            aVarArr2[i] = aVar;
            i++;
        }
        return aVarArr2;
    }

    public NotificationManager getNotificationManager() {
        return (NotificationManager) this.a.getSystemService("notification");
    }

    public int getTargetSdk() {
        return this.a.getApplicationInfo().targetSdkVersion;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a(context).onReceive(intent);
    }

    public void registerNotificationChannel(String str, String str2, int i, String str3, boolean z, boolean z2, boolean z3, boolean z4, long[] jArr, int i2, String str4) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannelM = h$$ExternalSyntheticApiModelOutline0.m(str, str2, i);
            notificationChannelM.setDescription(str3);
            notificationChannelM.enableLights(z);
            notificationChannelM.enableVibration(z2);
            notificationChannelM.setBypassDnd(z3);
            notificationChannelM.setShowBadge(z4);
            notificationChannelM.setVibrationPattern(jArr);
            notificationChannelM.setLockscreenVisibility(i2);
            notificationChannelM.setGroup(str4);
            getNotificationManager().createNotificationChannel(notificationChannelM);
            return;
        }
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("UNITY_NOTIFICATIONS", 0);
        HashSet hashSet = new HashSet(sharedPreferences.getStringSet("ChannelIDs", new HashSet()));
        hashSet.add(str);
        SharedPreferences.Editor editorClear = sharedPreferences.edit().clear();
        editorClear.putStringSet("ChannelIDs", hashSet);
        editorClear.apply();
        SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("unity_notification_channel_" + str, 0).edit();
        editorEdit.putString("title", str2);
        editorEdit.putInt("importance", i);
        editorEdit.putString("description", str3);
        editorEdit.putBoolean("enableLights", z);
        editorEdit.putBoolean("enableVibration", z2);
        editorEdit.putBoolean("canBypassDnd", z3);
        editorEdit.putBoolean("canShowBadge", z4);
        editorEdit.putString("vibrationPattern", Arrays.toString(jArr));
        editorEdit.putInt("lockscreenVisibility", i2);
        editorEdit.putString("group", str4);
        editorEdit.apply();
    }

    public void registerNotificationChannelGroup(String str, String str2, String str3) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            NotificationChannelGroup notificationChannelGroupM = h$$ExternalSyntheticApiModelOutline0.m(str, str2);
            if (i >= 28) {
                notificationChannelGroupM.setDescription(str3);
            }
            getNotificationManager().createNotificationChannelGroup(notificationChannelGroupM);
        }
    }

    public int scheduleNotification(Notification.Builder builder, boolean z) {
        int i;
        Bundle extras = builder.getExtras();
        if (extras.containsKey(KEY_ID)) {
            i = builder.getExtras().getInt(KEY_ID, -1);
        } else {
            int iNextInt = 0;
            do {
                iNextInt += this.e.nextInt(1000);
            } while (this.g.containsKey(Integer.valueOf(iNextInt)));
            extras.putInt(KEY_ID, iNextInt);
            i = iNextInt;
        }
        this.d.a(i, builder, z, this.g.putIfAbsent(Integer.valueOf(i), builder) == null);
        return i;
    }

    public void setupBigPictureStyle(Notification.Builder builder, String str, String str2, String str3, String str4, String str5, boolean z) {
        Bundle extras = builder.getExtras();
        if (str2 == null || str2.length() == 0) {
            return;
        }
        extras.putString(KEY_BIG_LARGE_ICON, str);
        extras.putString(KEY_BIG_PICTURE, str2);
        extras.putString(KEY_BIG_CONTENT_TITLE, str3);
        extras.putString(KEY_BIG_SUMMARY_TEXT, str5);
        extras.putString(KEY_BIG_CONTENT_DESCRIPTION, str4);
        extras.putBoolean(KEY_BIG_SHOW_WHEN_COLLAPSED, z);
    }

    public void showNotificationSettings(String str) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 26) {
            intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this.a.getPackageName(), null));
        } else {
            if (str == null || str.length() <= 0) {
                intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
            } else {
                Intent intent2 = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
                intent2.putExtra("android.provider.extra.CHANNEL_ID", str);
                intent = intent2;
            }
            intent.putExtra("android.provider.extra.APP_PACKAGE", this.a.getPackageName());
        }
        intent.addFlags(268435456);
        this.b.startActivity(intent);
    }

    public int checkNotificationStatus(int i) {
        for (StatusBarNotification statusBarNotification : getNotificationManager().getActiveNotifications()) {
            if (i == statusBarNotification.getId()) {
                return 2;
            }
        }
        return (this.g.containsKey(Integer.valueOf(i)) || checkIfPendingNotificationIsRegistered(i)) ? 1 : 0;
    }

    public Notification getNotificationFromIntent(Intent intent) throws IOException {
        if (intent.hasExtra(KEY_NOTIFICATION_ID)) {
            int i = intent.getExtras().getInt(KEY_NOTIFICATION_ID);
            for (StatusBarNotification statusBarNotification : getNotificationManager().getActiveNotifications()) {
                if (statusBarNotification.getId() == i) {
                    return statusBarNotification.getNotification();
                }
            }
        }
        Object objValueOf = intent.hasExtra(KEY_NOTIFICATION_ID) ? Integer.valueOf(intent.getExtras().getInt(KEY_NOTIFICATION_ID)) : intent.hasExtra(KEY_NOTIFICATION) ? intent.getParcelableExtra(KEY_NOTIFICATION) : null;
        if (objValueOf instanceof Integer) {
            Integer num = (Integer) objValueOf;
            Object obj = this.g.get(num);
            if (obj == null) {
                Object objA = h.a(this.a, this.a.getSharedPreferences("u_notification_data_" + num.toString(), 0));
                objValueOf = objA == null ? null : objA instanceof Notification ? h.a(this.a, (Notification) objA) : (Notification.Builder) objA;
            } else {
                objValueOf = obj;
            }
        }
        if (objValueOf == null) {
            return null;
        }
        return objValueOf instanceof Notification ? (Notification) objValueOf : ((Notification.Builder) objValueOf).build();
    }

    public final synchronized void b(HashSet hashSet) {
        SharedPreferences.Editor editorClear = this.a.getSharedPreferences("UNITY_STORED_NOTIFICATION_IDS", 0).edit().clear();
        editorClear.putStringSet("UNITY_NOTIFICATION_IDS", hashSet);
        editorClear.apply();
    }

    public final Bundle a() {
        try {
            return this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public final void a(int i, Notification.Builder builder, boolean z) {
        boolean z2;
        boolean zCanScheduleExactAlarms;
        Bundle bundleA;
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        String strEncodeToString;
        Bundle extras = builder.getExtras();
        long j2 = extras.getLong(KEY_REPEAT_INTERVAL, -1L);
        long j3 = extras.getLong(KEY_FIRE_TIME, -1L);
        boolean z3 = j3 - Calendar.getInstance().getTime().getTime() < 1000;
        if (!z3 || j2 > 0) {
            if (z3) {
                j3 += j2;
            }
            Intent intent = new Intent(this.a, (Class<?>) UnityNotificationManager.class);
            intent.setFlags(268468224);
            Notification notificationBuild = builder.build();
            synchronized (this) {
                String string = Integer.toString(notificationBuild.extras.getInt(KEY_ID, -1));
                SharedPreferences sharedPreferences = this.a.getSharedPreferences("u_notification_data_" + string, 0);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                } catch (Exception e) {
                    Log.e("UnityNotifications", "Failed to serialize notification", e);
                }
                if (z) {
                    Intent intent2 = new Intent();
                    intent2.putExtra(KEY_NOTIFICATION, notificationBuild);
                    try {
                        byte[] bArrA = h.a(intent2);
                        if (bArrA != null && bArrA.length != 0) {
                            dataOutputStream.write(h.b);
                            dataOutputStream.writeInt(0);
                            dataOutputStream.writeInt(bArrA.length);
                            dataOutputStream.write(bArrA);
                            dataOutputStream.close();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            strEncodeToString = Base64.encodeToString(byteArray, 0, byteArray.length, 0);
                            SharedPreferences.Editor editorClear = sharedPreferences.edit().clear();
                            editorClear.putString("data", strEncodeToString);
                            editorClear.apply();
                        }
                    } catch (Exception e2) {
                        Log.e("UnityNotifications", "Failed to serialize notification as Parcel", e2);
                    } catch (OutOfMemoryError e3) {
                        Log.e("UnityNotifications", "Failed to serialize notification as Parcel", e3);
                    }
                } else if (h.a(notificationBuild, dataOutputStream)) {
                    dataOutputStream.flush();
                    byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                    strEncodeToString = Base64.encodeToString(byteArray2, 0, byteArray2.length, 0);
                    SharedPreferences.Editor editorClear2 = sharedPreferences.edit().clear();
                    editorClear2.putString("data", strEncodeToString);
                    editorClear2.apply();
                }
            }
            Bundle extras2 = builder.getExtras();
            int i2 = extras2.getInt(KEY_ID, -1);
            long j4 = extras2.getLong(KEY_REPEAT_INTERVAL, -1L);
            this.g.put(Integer.valueOf(i2), builder);
            intent.putExtra(KEY_NOTIFICATION_ID, i2);
            PendingIntent pendingIntentA = a(i2, intent, 134217728);
            AlarmManager alarmManager = (AlarmManager) this.a.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (j4 <= 0) {
                int i3 = Build.VERSION.SDK_INT;
                if (this.i >= 0 || (bundleA = a()) == null) {
                    z2 = true;
                } else {
                    z2 = true;
                    this.i = bundleA.getInt("com.unity.androidnotifications.exact_scheduling", 1);
                }
                if (this.i == 0) {
                    zCanScheduleExactAlarms = false;
                } else {
                    zCanScheduleExactAlarms = i3 < 31 ? z2 : alarmManager.canScheduleExactAlarms();
                }
                if (zCanScheduleExactAlarms) {
                    alarmManager.setExactAndAllowWhileIdle(0, j3, pendingIntentA);
                } else {
                    alarmManager.set(0, j3, pendingIntentA);
                }
            } else {
                alarmManager.setInexactRepeating(0, j3, j4, pendingIntentA);
            }
        }
        if (z3) {
            a(i, a(this.c, builder));
        }
    }

    public void onReceive(Intent intent) {
        a(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0133  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.app.Notification a(java.lang.Class r10, android.app.Notification.Builder r11) {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity.androidnotifications.UnityNotificationManager.a(java.lang.Class, android.app.Notification$Builder):android.app.Notification");
    }

    public final void a(HashSet hashSet) {
        Log.d("UnityNotifications", "Checking for invalid notification IDs still hanging around");
        Intent intent = new Intent(this.a, (Class<?>) UnityNotificationManager.class);
        intent.setFlags(268468224);
        HashSet<String> hashSet2 = new HashSet();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (a(Integer.valueOf(str).intValue(), intent, 536870912) == null) {
                hashSet2.add(str);
            }
        }
        for (StatusBarNotification statusBarNotification : getNotificationManager().getActiveNotifications()) {
            hashSet2.remove(String.valueOf(statusBarNotification.getId()));
        }
        Activity activity = UnityPlayer.currentActivity;
        if (activity != null) {
            Intent intent2 = activity.getIntent();
            if (intent2.hasExtra(KEY_NOTIFICATION_ID)) {
                hashSet2.remove(String.valueOf(intent2.getExtras().getInt(KEY_NOTIFICATION_ID)));
            }
        }
        HashSet hashSet3 = new HashSet(hashSet);
        for (String str2 : hashSet2) {
            hashSet3.remove(str2);
            this.g.remove(str2);
        }
        Iterator it2 = hashSet2.iterator();
        while (it2.hasNext()) {
            a((String) it2.next());
        }
    }

    public final PendingIntent a(int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(this.a, i, intent, i2 | 67108864);
    }

    public final void a(int i) {
        PendingIntent pendingIntentA = a(i, new Intent(this.a, (Class<?>) UnityNotificationManager.class), 536870912);
        if (pendingIntentA != null) {
            ((AlarmManager) this.a.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(pendingIntentA);
            pendingIntentA.cancel();
        }
    }

    public final synchronized void a(String str) {
        this.a.getSharedPreferences("u_notification_data_" + str, 0).edit().clear().apply();
    }

    public final void a(int i, Notification notification) {
        boolean z = notification.extras.getBoolean(KEY_SHOW_IN_FOREGROUND, true);
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        int i2 = runningAppProcessInfo.importance;
        if ((i2 != 100 && i2 != 200) || z) {
            getNotificationManager().notify(i, notification);
        }
        if (notification.extras.getLong(KEY_REPEAT_INTERVAL, -1L) <= 0) {
            this.g.remove(Integer.valueOf(i));
            a(i);
        }
        try {
            NotificationCallback notificationCallback = this.h;
            if (notificationCallback != null) {
                notificationCallback.onSentNotification(notification);
            }
        } catch (RuntimeException unused) {
            Log.w("UnityNotifications", "Can not invoke OnNotificationReceived event when the app is not running!");
        }
    }

    public final void a(Intent intent) {
        Object parcelableExtra;
        if (intent.hasExtra(KEY_NOTIFICATION_ID)) {
            parcelableExtra = Integer.valueOf(intent.getExtras().getInt(KEY_NOTIFICATION_ID));
        } else {
            parcelableExtra = intent.hasExtra(KEY_NOTIFICATION) ? intent.getParcelableExtra(KEY_NOTIFICATION) : null;
        }
        if (parcelableExtra == null) {
            return;
        }
        if (parcelableExtra instanceof Notification) {
            Notification notification = (Notification) parcelableExtra;
            a(notification.extras.getInt(KEY_ID, -1), notification);
            return;
        }
        final Integer num = (Integer) parcelableExtra;
        Notification.Builder builder = (Notification.Builder) this.g.get(num);
        if (builder != null) {
            int iIntValue = num.intValue();
            Class clsA = this.c;
            if (clsA == null && (clsA = h.a(this.a)) == null) {
                Log.e("UnityNotifications", "Activity not found, cannot show notification");
                return;
            }
            Notification notificationA = a(clsA, builder);
            if (notificationA != null) {
                a(iIntValue, notificationA);
                return;
            }
            return;
        }
        AsyncTask.execute(new Runnable() { // from class: com.unity.androidnotifications.UnityNotificationManager$$ExternalSyntheticLambda40
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                this.f$0.a(num);
            }
        });
    }

    public final void a(Integer num) throws IOException {
        Notification.Builder builderA;
        Object objA = h.a(this.a, this.a.getSharedPreferences("u_notification_data_" + num.toString(), 0));
        if (objA == null) {
            builderA = null;
        } else if (objA instanceof Notification) {
            builderA = h.a(this.a, (Notification) objA);
        } else {
            builderA = (Notification.Builder) objA;
        }
        if (builderA == null) {
            Log.e("UnityNotifications", "Failed to recover builder, can't send notification");
            return;
        }
        int iIntValue = num.intValue();
        Class clsA = this.c;
        if (clsA == null && (clsA = h.a(this.a)) == null) {
            Log.e("UnityNotifications", "Activity not found, cannot show notification");
            return;
        }
        Notification notificationA = a(clsA, builderA);
        if (notificationA != null) {
            a(iIntValue, notificationA);
        }
    }
}

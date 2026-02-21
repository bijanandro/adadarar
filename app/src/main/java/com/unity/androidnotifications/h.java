package com.unity.androidnotifications;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    public static final byte[] a = {85, 77, 78, 78};
    public static final byte[] b = {85, 77, 78, 80};

    public static int a(Context context, String str) {
        if (str == null) {
            return 0;
        }
        try {
            Resources resources = context.getResources();
            if (resources != null) {
                int identifier = resources.getIdentifier(str, "mipmap", context.getPackageName());
                return identifier == 0 ? resources.getIdentifier(str, "drawable", context.getPackageName()) : identifier;
            }
        } catch (Resources.NotFoundException unused) {
        }
        return 0;
    }

    public static String b(DataInputStream dataInputStream) throws IOException {
        int i = dataInputStream.readInt();
        if (i <= 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        if (dataInputStream.read(bArr) == i) {
            return new String(bArr, StandardCharsets.UTF_8);
        }
        throw new IOException("Insufficient amount of bytes read");
    }

    public static boolean a(Notification notification, DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.write(a);
            dataOutputStream.writeInt(3);
            boolean z = notification.extras.getBoolean(NotificationCompat.EXTRA_SHOW_WHEN, false);
            dataOutputStream.writeInt(notification.extras.getInt(UnityNotificationManager.KEY_ID));
            a(dataOutputStream, notification.extras.getString(NotificationCompat.EXTRA_TITLE));
            a(dataOutputStream, notification.extras.getString(NotificationCompat.EXTRA_TEXT));
            a(dataOutputStream, notification.extras.getString(UnityNotificationManager.KEY_SMALL_ICON));
            a(dataOutputStream, notification.extras.getString(UnityNotificationManager.KEY_LARGE_ICON));
            dataOutputStream.writeLong(notification.extras.getLong(UnityNotificationManager.KEY_FIRE_TIME, -1L));
            dataOutputStream.writeLong(notification.extras.getLong(UnityNotificationManager.KEY_REPEAT_INTERVAL, -1L));
            a(dataOutputStream, notification.extras.getString(NotificationCompat.EXTRA_BIG_TEXT));
            dataOutputStream.writeBoolean(notification.extras.getBoolean(NotificationCompat.EXTRA_SHOW_CHRONOMETER, false));
            dataOutputStream.writeBoolean(z);
            a(dataOutputStream, notification.extras.getString("data"));
            dataOutputStream.writeBoolean(notification.extras.getBoolean(UnityNotificationManager.KEY_SHOW_IN_FOREGROUND, true));
            String string = notification.extras.getString(UnityNotificationManager.KEY_BIG_PICTURE);
            a(dataOutputStream, string);
            if (string != null && string.length() > 0) {
                a(dataOutputStream, notification.extras.getString(UnityNotificationManager.KEY_BIG_LARGE_ICON));
                a(dataOutputStream, notification.extras.getString(UnityNotificationManager.KEY_BIG_CONTENT_TITLE));
                a(dataOutputStream, notification.extras.getString(UnityNotificationManager.KEY_BIG_CONTENT_DESCRIPTION));
                a(dataOutputStream, notification.extras.getString(UnityNotificationManager.KEY_BIG_SUMMARY_TEXT));
                dataOutputStream.writeBoolean(notification.extras.getBoolean(UnityNotificationManager.KEY_BIG_SHOW_WHEN_COLLAPSED, false));
            }
            a(dataOutputStream, Build.VERSION.SDK_INT < 26 ? null : notification.getChannelId());
            Integer notificationColor = UnityNotificationManager.getNotificationColor(notification);
            dataOutputStream.writeBoolean(notificationColor != null);
            if (notificationColor != null) {
                dataOutputStream.writeInt(notificationColor.intValue());
            }
            dataOutputStream.writeInt(notification.number);
            dataOutputStream.writeBoolean((notification.flags & 16) != 0);
            a(dataOutputStream, notification.getGroup());
            dataOutputStream.writeBoolean((notification.flags & 512) != 0);
            dataOutputStream.writeInt(UnityNotificationManager.getNotificationGroupAlertBehavior(notification));
            a(dataOutputStream, notification.getSortKey());
            if (z) {
                dataOutputStream.writeLong(notification.when);
            }
            return true;
        } catch (Exception e) {
            Log.e("UnityNotifications", "Failed to serialize notification", e);
            return false;
        }
    }

    public static void a(DataOutputStream dataOutputStream, String str) throws IOException {
        if (str != null && str.length() != 0) {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            dataOutputStream.writeInt(bytes.length);
            dataOutputStream.write(bytes);
            return;
        }
        dataOutputStream.writeInt(0);
    }

    public static byte[] a(Intent intent) {
        try {
            Parcel parcelObtain = Parcel.obtain();
            Bundle bundle = new Bundle();
            bundle.putParcelable("obj", intent);
            parcelObtain.writeParcelable(bundle, 0);
            byte[] bArrMarshall = parcelObtain.marshall();
            parcelObtain.recycle();
            return bArrMarshall;
        } catch (Exception e) {
            Log.e("UnityNotifications", "Failed to serialize Parcelable", e);
            return null;
        } catch (OutOfMemoryError e2) {
            Log.e("UnityNotifications", "Failed to serialize Parcelable", e2);
            return null;
        }
    }

    public static Object a(Context context, SharedPreferences sharedPreferences) throws IOException {
        String string = sharedPreferences.getString("data", "");
        if (string != null && string.length() > 0) {
            Object objA = a(context, Base64.decode(string, 0));
            if (objA != null) {
                return objA;
            }
            String string2 = sharedPreferences.getString("fallback.data", "");
            if (string2 != null && string2.length() > 0) {
                return a(context, Base64.decode(string2, 0));
            }
        }
        return null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(25:(2:196|30)(1:33)|(3:35|(1:37)(1:38)|(2:40|(23:46|49|50|208|53|(3:203|55|56)(1:61)|62|(1:64)(1:66)|67|68|(2:70|71)(7:72|73|(1:75)|76|(5:206|78|79|201|80)(1:92)|(5:204|94|95|197|96)(1:101)|102)|(1:104)|(1:110)|(1:112)(1:(1:114))|(1:116)|(1:118)|119|(1:123)|124|(1:128)|(1:130)|131|(18:194|150|151|192|152|153|199|154|(1:156)|(1:158)|(1:160)|(1:162)|(1:166)|167|(1:171)|172|173|213)(1:212))(23:44|45|50|208|53|(0)(0)|62|(0)(0)|67|68|(0)(0)|(0)|(0)|(0)(0)|(0)|(0)|119|(2:121|123)|124|(2:126|128)|(0)|131|(0)(0)))(1:47))(2:51|52)|48|49|50|208|53|(0)(0)|62|(0)(0)|67|68|(0)(0)|(0)|(0)|(0)(0)|(0)|(0)|119|(0)|124|(0)|(0)|131|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x029a, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x029b, code lost:
    
        r4 = "data";
        r2 = com.unity.androidnotifications.UnityNotificationManager.KEY_REPEAT_INTERVAL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02a4, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02a5, code lost:
    
        r4 = "data";
        r2 = com.unity.androidnotifications.UnityNotificationManager.KEY_REPEAT_INTERVAL;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0225 A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0233 A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x023a A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0254 A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x025b A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x026c A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0283 A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x028e A[Catch: OutOfMemoryError -> 0x022b, Exception -> 0x022e, TRY_LEAVE, TryCatch #23 {Exception -> 0x022e, OutOfMemoryError -> 0x022b, blocks: (B:104:0x0225, B:110:0x0233, B:112:0x023a, B:116:0x0254, B:118:0x025b, B:119:0x0260, B:121:0x026c, B:123:0x0272, B:124:0x0277, B:126:0x0283, B:128:0x0289, B:130:0x028e, B:114:0x024b, B:96:0x0208, B:102:0x0218), top: B:197:0x0208 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x015f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:212:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a2 A[Catch: OutOfMemoryError -> 0x0168, Exception -> 0x0171, TRY_ENTER, TRY_LEAVE, TryCatch #20 {Exception -> 0x0171, OutOfMemoryError -> 0x0168, blocks: (B:55:0x015f, B:64:0x01a2, B:70:0x01b5, B:75:0x01d1), top: B:203:0x015f }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b5 A[Catch: OutOfMemoryError -> 0x0168, Exception -> 0x0171, TRY_ENTER, TRY_LEAVE, TryCatch #20 {Exception -> 0x0171, OutOfMemoryError -> 0x0168, blocks: (B:55:0x015f, B:64:0x01a2, B:70:0x01b5, B:75:0x01d1), top: B:203:0x015f }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01be A[Catch: OutOfMemoryError -> 0x029a, Exception -> 0x02a4, TRY_ENTER, TRY_LEAVE, TryCatch #17 {Exception -> 0x02a4, OutOfMemoryError -> 0x029a, blocks: (B:53:0x0155, B:62:0x017e, B:68:0x01ab, B:72:0x01be), top: B:208:0x0155 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object a(android.content.Context r49, byte[] r50) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1063
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity.androidnotifications.h.a(android.content.Context, byte[]):java.lang.Object");
    }

    public static boolean a(DataInputStream dataInputStream, byte[] bArr) {
        for (byte b2 : bArr) {
            try {
                if (dataInputStream.readByte() != b2) {
                    return false;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    public static Parcelable a(DataInputStream dataInputStream) throws IOException {
        int i = dataInputStream.readInt();
        if (i <= 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        if (dataInputStream.read(bArr) == i) {
            try {
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.unmarshall(bArr, 0, i);
                parcelObtain.setDataPosition(0);
                Bundle bundle = (Bundle) parcelObtain.readParcelable(h.class.getClassLoader());
                parcelObtain.recycle();
                if (bundle != null) {
                    return bundle.getParcelable("obj");
                }
            } catch (Exception e) {
                Log.e("UnityNotifications", "Failed to deserialize parcelable", e);
            } catch (OutOfMemoryError e2) {
                Log.e("UnityNotifications", "Failed to deserialize parcelable", e2);
            }
            return null;
        }
        throw new IOException("Insufficient amount of bytes read");
    }

    public static Class a(Context context) {
        try {
            try {
                PackageManager packageManager = context.getPackageManager();
                Bundle bundle = packageManager.getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle.containsKey("custom_notification_android_activity")) {
                    try {
                        return Class.forName(bundle.getString("custom_notification_android_activity"));
                    } catch (ClassNotFoundException e) {
                        Log.e("UnityNotifications", "Specified activity class for notifications not found: " + e.getMessage());
                    }
                }
                Log.w("UnityNotifications", "No custom_notification_android_activity found, attempting to find app activity class");
                boolean z = true;
                ActivityInfo[] activityInfoArr = packageManager.getPackageInfo(context.getPackageName(), 1).activities;
                if (activityInfoArr == null) {
                    Log.e("UnityNotifications", "Could not get package activities");
                    return null;
                }
                int length = activityInfoArr.length;
                String str = null;
                int i = 0;
                boolean z2 = false;
                boolean z3 = false;
                while (true) {
                    if (i >= length) {
                        z = z3;
                        break;
                    }
                    ActivityInfo activityInfo = activityInfoArr[i];
                    if (activityInfo.enabled && activityInfo.targetActivity == null) {
                        String str2 = activityInfo.name;
                        boolean z4 = str2.endsWith(".UnityPlayerActivity") || str2.endsWith(".UnityPlayerGameActivity");
                        if (str == null) {
                            str = activityInfo.name;
                        } else if (z2 == z4) {
                            if (z2 && z4) {
                                break;
                            }
                            z3 = true;
                        } else if (z4) {
                            str = activityInfo.name;
                            z3 = false;
                        }
                        z2 = z4;
                    }
                    i++;
                }
                if (z) {
                    Log.e("UnityNotifications", "Multiple choices for activity for notifications, set activity explicitly in Notification Settings");
                    return null;
                }
                if (str == null) {
                    Log.e("UnityNotifications", "Activity class for notifications not found");
                    return null;
                }
                return Class.forName(str);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                return null;
            }
        } catch (ClassNotFoundException e3) {
            Log.e("UnityNotifications", "Failed to find activity class: " + e3.getMessage());
            return null;
        }
    }

    public static Notification.Builder a(Context context, Notification notification) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(context, notification);
                builderRecoverBuilder.setExtras(notification.extras);
                return builderRecoverBuilder;
            }
        } catch (Exception e) {
            Log.e("UnityNotifications", "Failed to recover builder for notification!", e);
        } catch (OutOfMemoryError e2) {
            Log.e("UnityNotifications", "Failed to recover builder for notification!", e2);
        }
        Notification.Builder builderCreateNotificationBuilder = UnityNotificationManager.a(context).createNotificationBuilder(notification.extras.getString(UnityNotificationManager.KEY_CHANNEL_ID));
        UnityNotificationManager.setNotificationIcon(builderCreateNotificationBuilder, UnityNotificationManager.KEY_SMALL_ICON, notification.extras.getString(UnityNotificationManager.KEY_SMALL_ICON));
        String string = notification.extras.getString(UnityNotificationManager.KEY_LARGE_ICON);
        if (string != null && !string.isEmpty()) {
            UnityNotificationManager.setNotificationIcon(builderCreateNotificationBuilder, UnityNotificationManager.KEY_LARGE_ICON, string);
        }
        builderCreateNotificationBuilder.setContentTitle(notification.extras.getString(NotificationCompat.EXTRA_TITLE));
        builderCreateNotificationBuilder.setContentText(notification.extras.getString(NotificationCompat.EXTRA_TEXT));
        builderCreateNotificationBuilder.setAutoCancel((notification.flags & 16) != 0);
        int i = notification.number;
        if (i >= 0) {
            builderCreateNotificationBuilder.setNumber(i);
        }
        String string2 = notification.extras.getString(NotificationCompat.EXTRA_BIG_TEXT);
        if (string2 != null) {
            builderCreateNotificationBuilder.setStyle(new Notification.BigTextStyle().bigText(string2));
        }
        builderCreateNotificationBuilder.setWhen(notification.when);
        String group = notification.getGroup();
        if (group != null && !group.isEmpty()) {
            builderCreateNotificationBuilder.setGroup(group);
        }
        builderCreateNotificationBuilder.setGroupSummary((notification.flags & 512) != 0);
        String sortKey = notification.getSortKey();
        if (sortKey != null && !sortKey.isEmpty()) {
            builderCreateNotificationBuilder.setSortKey(sortKey);
        }
        builderCreateNotificationBuilder.setShowWhen(notification.extras.getBoolean(NotificationCompat.EXTRA_SHOW_WHEN, false));
        Integer notificationColor = UnityNotificationManager.getNotificationColor(notification);
        if (notificationColor != null) {
            UnityNotificationManager.setNotificationColor(builderCreateNotificationBuilder, notificationColor.intValue());
        }
        UnityNotificationManager.setNotificationUsesChronometer(builderCreateNotificationBuilder, notification.extras.getBoolean(NotificationCompat.EXTRA_SHOW_CHRONOMETER, false));
        UnityNotificationManager.setNotificationGroupAlertBehavior(builderCreateNotificationBuilder, UnityNotificationManager.getNotificationGroupAlertBehavior(notification));
        builderCreateNotificationBuilder.getExtras().putInt(UnityNotificationManager.KEY_ID, notification.extras.getInt(UnityNotificationManager.KEY_ID, 0));
        builderCreateNotificationBuilder.getExtras().putLong(UnityNotificationManager.KEY_REPEAT_INTERVAL, notification.extras.getLong(UnityNotificationManager.KEY_REPEAT_INTERVAL, 0L));
        builderCreateNotificationBuilder.getExtras().putLong(UnityNotificationManager.KEY_FIRE_TIME, notification.extras.getLong(UnityNotificationManager.KEY_FIRE_TIME, 0L));
        String string3 = notification.extras.getString("data");
        if (string3 != null && !string3.isEmpty()) {
            builderCreateNotificationBuilder.getExtras().putString("data", string3);
        }
        return builderCreateNotificationBuilder;
    }
}

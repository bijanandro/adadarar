package com.unity.androidnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ Notification.Builder m(Context context, String str) {
        return new Notification.Builder(context, str);
    }

    public static /* bridge */ /* synthetic */ NotificationChannel m(Object obj) {
        return (NotificationChannel) obj;
    }

    public static /* synthetic */ NotificationChannel m(String str, CharSequence charSequence, int i) {
        return new NotificationChannel(str, charSequence, i);
    }

    public static /* synthetic */ NotificationChannelGroup m(String str, CharSequence charSequence) {
        return new NotificationChannelGroup(str, charSequence);
    }

    public static /* synthetic */ void m() {
    }
}

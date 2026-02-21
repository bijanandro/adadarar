package com.unity.androidnotifications;

import android.app.Notification;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e extends f {
    public final int a;
    public final Notification.Builder b;
    public final boolean c;
    public final boolean d;

    public e(int i, Notification.Builder builder, boolean z, boolean z2) {
        this.a = i;
        this.b = builder;
        this.c = z;
        this.d = z2;
    }

    @Override // com.unity.androidnotifications.f
    public final boolean a(UnityNotificationManager unityNotificationManager, ConcurrentHashMap concurrentHashMap) {
        String strValueOf = String.valueOf(this.a);
        try {
            UnityNotificationManager.j.a(this.a, this.b, this.c);
            return this.d;
        } catch (Throwable th) {
            concurrentHashMap.remove(Integer.valueOf(this.a));
            unityNotificationManager.a(this.a);
            unityNotificationManager.a(strValueOf);
            throw th;
        }
    }
}

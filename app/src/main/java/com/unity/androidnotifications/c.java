package com.unity.androidnotifications;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c extends f {
    public final int a;

    public c(int i) {
        this.a = i;
    }

    @Override // com.unity.androidnotifications.f
    public final boolean a(UnityNotificationManager unityNotificationManager, ConcurrentHashMap concurrentHashMap) {
        unityNotificationManager.a(this.a);
        if (concurrentHashMap.remove(Integer.valueOf(this.a)) == null) {
            return false;
        }
        unityNotificationManager.a(String.valueOf(this.a));
        return true;
    }
}

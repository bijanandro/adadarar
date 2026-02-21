package com.unity.androidnotifications;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b extends f {
    @Override // com.unity.androidnotifications.f
    public final boolean a(UnityNotificationManager unityNotificationManager, ConcurrentHashMap concurrentHashMap) {
        if (concurrentHashMap.isEmpty()) {
            return false;
        }
        Enumeration enumerationKeys = concurrentHashMap.keys();
        while (enumerationKeys.hasMoreElements()) {
            Integer num = (Integer) enumerationKeys.nextElement();
            unityNotificationManager.a(num.intValue());
            unityNotificationManager.a(String.valueOf(num));
        }
        concurrentHashMap.clear();
        return true;
    }
}

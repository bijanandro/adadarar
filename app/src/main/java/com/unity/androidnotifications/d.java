package com.unity.androidnotifications;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class d extends f {
    public final g a;

    public d(g gVar) {
        this.a = gVar;
    }

    @Override // com.unity.androidnotifications.f
    public final boolean a(UnityNotificationManager unityNotificationManager, ConcurrentHashMap concurrentHashMap) {
        HashSet hashSet = new HashSet();
        Enumeration enumerationKeys = concurrentHashMap.keys();
        while (enumerationKeys.hasMoreElements()) {
            hashSet.add(String.valueOf(enumerationKeys.nextElement()));
        }
        g gVar = this.a;
        boolean z = gVar.d >= 50;
        gVar.d = 0;
        if (z) {
            gVar.c.a(hashSet);
        }
        gVar.c.b(hashSet);
        return false;
    }
}

package com.unity.androidnotifications;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Thread {
    public final ConcurrentHashMap b;
    public final UnityNotificationManager c;
    public final LinkedTransferQueue a = new LinkedTransferQueue();
    public int d = 50;

    public g(UnityNotificationManager unityNotificationManager, ConcurrentHashMap concurrentHashMap) {
        ArrayList<Notification.Builder> arrayList;
        boolean z;
        this.c = unityNotificationManager;
        this.b = concurrentHashMap;
        if (concurrentHashMap.size() == 0) {
            synchronized (unityNotificationManager) {
                Set setB = unityNotificationManager.b();
                arrayList = new ArrayList();
                HashSet<String> hashSet = new HashSet();
                Iterator it = setB.iterator();
                while (true) {
                    z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    String str = (String) it.next();
                    Object objA = h.a(unityNotificationManager.a, unityNotificationManager.a.getSharedPreferences("u_notification_data_" + str, 0));
                    Notification.Builder builderA = objA != null ? objA instanceof Notification.Builder ? (Notification.Builder) objA : h.a(unityNotificationManager.a, (Notification) objA) : null;
                    if (builderA != null) {
                        arrayList.add(builderA);
                    } else {
                        hashSet.add(str);
                    }
                }
                if (hashSet.size() > 0) {
                    HashSet hashSet2 = new HashSet(setB);
                    for (String str2 : hashSet) {
                        hashSet2.remove(str2);
                        unityNotificationManager.a(str2);
                    }
                    unityNotificationManager.b(hashSet2);
                }
            }
            if (arrayList.size() == 0) {
                return;
            }
            long time = Calendar.getInstance().getTime().getTime();
            for (Notification.Builder builder : arrayList) {
                Bundle extras = builder.getExtras();
                int i = extras.getInt(UnityNotificationManager.KEY_ID, -1);
                if (extras.getLong(UnityNotificationManager.KEY_FIRE_TIME, -1L) - time > 0) {
                    this.b.put(Integer.valueOf(i), builder);
                } else {
                    z = true;
                }
            }
            if (z) {
                this.a.add(new d(this));
            }
        }
    }

    public final void a(int i, Notification.Builder builder, boolean z, boolean z2) {
        this.a.add(new e(i, builder, z, z2));
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean zA;
        while (true) {
            boolean z = false;
            while (true) {
                try {
                    f fVar = (f) this.a.take();
                    try {
                        zA = fVar.a(this.c, this.b);
                    } catch (Exception e) {
                        Log.e("UnityNotifications", "Exception executing notification task", e);
                        zA = false;
                    }
                    z |= zA;
                    if (!(fVar instanceof d)) {
                        this.d++;
                    }
                } catch (InterruptedException unused) {
                }
                if (this.a.size() == 0 && z) {
                    try {
                        this.a.add(new d(this));
                        break;
                    } catch (InterruptedException unused2) {
                        z = false;
                        if (this.a.isEmpty()) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public final void a(int i) {
        this.a.add(new c(i));
    }

    public final void a() {
        this.a.add(new b());
    }
}

package com.unity3d.player;

import android.content.res.Configuration;

/* JADX INFO: renamed from: com.unity3d.player.q1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0077q1 implements Runnable {
    final /* synthetic */ Configuration a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    RunnableC0077q1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Configuration configuration) {
        this.b = unityPlayerForActivityOrService;
        this.a = configuration;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.nativeConfigurationChanged(this.a);
    }
}

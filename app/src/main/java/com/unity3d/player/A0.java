package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class A0 {
    private UnityPlayer a;

    public A0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    final void a() {
        if (PlatformSupport.OREO_SUPPORT) {
            this.a.runOnUiThread(new RunnableC0096z0(this));
        }
    }

    final void b() {
        if (PlatformSupport.OREO_SUPPORT) {
            this.a.runOnUiThread(new RunnableC0094y0(this));
        }
    }
}

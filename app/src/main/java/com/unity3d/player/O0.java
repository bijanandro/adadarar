package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class O0 implements Runnable {
    final /* synthetic */ UnityPlayer a;

    O0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.getFrameLayout().removeView(this.a.m_SplashScreen);
        this.a.m_SplashScreen = null;
    }
}

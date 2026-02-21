package com.unity3d.player;

import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class D1 implements Runnable {
    final /* synthetic */ UnityPlayerForGameActivity a;

    D1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        this.a = unityPlayerForGameActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        O o = unityPlayerForGameActivity.m_PersistentUnitySurface;
        FrameLayout frameLayout = unityPlayerForGameActivity.getFrameLayout();
        N n = o.b;
        if (n != null && n.getParent() != null) {
            frameLayout.removeView(o.b);
        }
        this.a.m_PersistentUnitySurface.b = null;
    }
}

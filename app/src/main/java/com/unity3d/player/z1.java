package com.unity3d.player;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class z1 implements SurfaceHolder.Callback {
    final /* synthetic */ UnityPlayerForGameActivity a;

    z1(UnityPlayerForGameActivity unityPlayerForGameActivity) {
        this.a = unityPlayerForGameActivity;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        O o = unityPlayerForGameActivity.m_PersistentUnitySurface;
        FrameLayout frameLayout = unityPlayerForGameActivity.getFrameLayout();
        N n = o.b;
        if (n == null || n.getParent() != null) {
            return;
        }
        frameLayout.addView(o.b);
        frameLayout.bringChildToFront(o.b);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        UnityPlayerForGameActivity unityPlayerForGameActivity = this.a;
        O o = unityPlayerForGameActivity.m_PersistentUnitySurface;
        SurfaceView surfaceView = unityPlayerForGameActivity.m_SurfaceView;
        o.getClass();
        if (!PlatformSupport.NOUGAT_SUPPORT || o.a == null) {
            return;
        }
        if (o.b == null) {
            o.b = new N(o, o.a);
        }
        o.b.a(surfaceView);
    }
}

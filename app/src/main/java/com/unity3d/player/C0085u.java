package com.unity3d.player;

import android.graphics.SurfaceTexture;

/* JADX INFO: renamed from: com.unity3d.player.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0085u implements SurfaceTexture.OnFrameAvailableListener {
    final /* synthetic */ C0089w a;

    C0085u(C0089w c0089w) {
        this.a = c0089w;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        ((Camera2Wrapper) this.a.a).a(surfaceTexture);
    }
}

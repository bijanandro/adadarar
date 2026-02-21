package com.unity3d.player;

import android.view.SurfaceHolder;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class C0 implements SurfaceHolder.Callback {
    final /* synthetic */ D0 a;

    C0(D0 d0) {
        this.a = d0;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.a.b.updateGLDisplay(0, surfaceHolder.getSurface());
        this.a.b.sendSurfaceChangedEvent();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.a.b.updateGLDisplay(0, surfaceHolder.getSurface());
        D0 d0 = this.a;
        O o = d0.c;
        FrameLayout frameLayout = d0.b.getFrameLayout();
        N n = o.b;
        if (n == null || n.getParent() != null) {
            return;
        }
        frameLayout.addView(o.b);
        frameLayout.bringChildToFront(o.b);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        D0 d0 = this.a;
        O o = d0.c;
        C0042f c0042f = d0.a;
        o.getClass();
        if (PlatformSupport.NOUGAT_SUPPORT && o.a != null) {
            if (o.b == null) {
                o.b = new N(o, o.a);
            }
            o.b.a(c0042f);
        }
        this.a.b.updateGLDisplay(0, null);
    }
}

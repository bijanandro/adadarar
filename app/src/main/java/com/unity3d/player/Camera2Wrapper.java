package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.view.Surface;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class Camera2Wrapper implements com.unity3d.player.a.b {
    private Context a;
    private C0089w b = null;

    public Camera2Wrapper(Context context) {
        this.a = context;
        initCamera2Jni();
    }

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    public final void a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    public final void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i, int i2, int i3) {
        nativeFrameReady(byteBuffer, byteBuffer2, byteBuffer3, i, i2, i3);
    }

    protected void closeCamera2() {
        C0089w c0089w = this.b;
        if (c0089w != null) {
            c0089w.a();
        }
        this.b = null;
    }

    protected int getCamera2Count() {
        return C0089w.getCameraIds(this.a).length;
    }

    protected int getCamera2FocalLengthEquivalent(int i) {
        return C0089w.a(this.a, i);
    }

    protected int[] getCamera2Resolutions(int i) {
        return C0089w.b(this.a, i);
    }

    protected int getCamera2SensorOrientation(int i) {
        return C0089w.c(this.a, i);
    }

    protected Rect getFrameSizeCamera2() {
        C0089w c0089w = this.b;
        return c0089w != null ? c0089w.c() : new Rect();
    }

    protected boolean initializeCamera2(int i, int i2, int i3, int i4, int i5, Surface surface) {
        if (this.b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        int i6 = C0089w.E;
        C0089w c0095z = PlatformSupport.QUINCE_TART_SUPPORT ? new C0095z(this) : new C0089w(this);
        this.b = c0095z;
        return c0095z.a(this.a, i, i2, i3, i4, i5, surface);
    }

    protected boolean isCamera2AutoFocusPointSupported(int i) {
        return C0089w.d(this.a, i);
    }

    protected boolean isCamera2FrontFacing(int i) {
        return C0089w.e(this.a, i);
    }

    protected void pauseCamera2() {
        C0089w c0089w = this.b;
        if (c0089w != null) {
            c0089w.d();
        }
    }

    protected boolean setAutoFocusPoint(float f, float f2) {
        C0089w c0089w = this.b;
        if (c0089w != null) {
            return c0089w.a(f, f2);
        }
        return false;
    }

    protected void startCamera2() {
        C0089w c0089w = this.b;
        if (c0089w != null) {
            c0089w.h();
        }
    }

    protected void stopCamera2() {
        C0089w c0089w = this.b;
        if (c0089w != null) {
            c0089w.i();
        }
    }
}

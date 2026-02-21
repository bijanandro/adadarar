package com.unity3d.player;

import android.hardware.camera2.CameraDevice;

/* JADX INFO: renamed from: com.unity3d.player.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0081s extends CameraDevice.StateCallback {
    final /* synthetic */ C0089w a;

    C0081s(C0089w c0089w) {
        this.a = c0089w;
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onClosed(CameraDevice cameraDevice) {
        C0089w.D.release();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onDisconnected(CameraDevice cameraDevice) {
        D.Log(5, "Camera2: CameraDevice disconnected.");
        this.a.a(cameraDevice);
        C0089w.D.release();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onError(CameraDevice cameraDevice, int i) {
        D.Log(6, "Camera2: Error opening CameraDevice " + i);
        this.a.a(cameraDevice);
        C0089w.D.release();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onOpened(CameraDevice cameraDevice) {
        this.a.b = cameraDevice;
        C0089w.D.release();
    }
}

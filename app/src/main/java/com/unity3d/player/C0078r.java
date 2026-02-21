package com.unity3d.player;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;

/* JADX INFO: renamed from: com.unity3d.player.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0078r extends CameraCaptureSession.StateCallback {
    final /* synthetic */ C0089w a;

    C0078r(C0089w c0089w) {
        this.a = c0089w;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        this.a.A = 3;
        D.Log(6, "Camera2: CaptureSession configuration failed.");
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        String str;
        C0089w c0089w = this.a;
        if (c0089w.b == null) {
            return;
        }
        synchronized (c0089w.t) {
            C0089w c0089w2 = this.a;
            c0089w2.r = cameraCaptureSession;
            try {
                try {
                    c0089w2.q = c0089w2.b.createCaptureRequest(1);
                    C0089w c0089w3 = this.a;
                    c0089w3.q.addTarget(c0089w3.w);
                    C0089w c0089w4 = this.a;
                    c0089w4.q.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, c0089w4.n);
                    this.a.g();
                } catch (CameraAccessException e) {
                    str = "Camera2: CameraAccessException " + e;
                    D.Log(6, str);
                }
            } catch (IllegalStateException e2) {
                str = "Camera2: IllegalStateException " + e2;
                D.Log(6, str);
            }
        }
    }
}

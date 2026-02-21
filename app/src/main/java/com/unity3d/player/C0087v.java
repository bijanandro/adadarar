package com.unity3d.player;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;

/* JADX INFO: renamed from: com.unity3d.player.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0087v extends CameraCaptureSession.CaptureCallback {
    final /* synthetic */ C0089w a;

    C0087v(C0089w c0089w) {
        this.a = c0089w;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        this.a.a(captureRequest.getTag());
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
        D.Log(5, "Camera2: Capture session failed " + captureRequest.getTag() + " reason " + captureFailure.getReason());
        this.a.a(captureRequest.getTag());
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
    }
}

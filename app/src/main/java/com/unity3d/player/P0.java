package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class P0 implements Runnable {
    final /* synthetic */ PermissionRequest a;
    final /* synthetic */ String[] b;
    final /* synthetic */ int[] c;

    P0(PermissionRequest permissionRequest, String[] strArr, int[] iArr) {
        this.a = permissionRequest;
        this.b = strArr;
        this.c = iArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.permissionResponse(this.b, this.c);
    }
}

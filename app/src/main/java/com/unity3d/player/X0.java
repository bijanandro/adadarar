package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class X0 implements IPermissionRequestCallbacks {
    private long a;
    final /* synthetic */ UnityPlayer b;

    public X0(UnityPlayer unityPlayer, long j) {
        this.b = unityPlayer;
        this.a = j;
    }

    @Override // com.unity3d.player.IPermissionRequestCallbacks
    public final void onPermissionResult(String[] strArr, int[] iArr) {
        int length = iArr.length;
        boolean z = false;
        if (length != 0) {
            if (length != 1) {
                D.Log(6, "Only a single permission request is supported");
                return;
            } else if (iArr[0] == 1) {
                z = true;
            }
        }
        if (this.a == 0) {
            return;
        }
        this.b.invokeOnMainThread((Z0) new W0(this, z));
    }
}

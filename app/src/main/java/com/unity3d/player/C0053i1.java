package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.i1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0053i1 extends Z0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ UnityPlayerForActivityOrService e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0053i1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z, String str, int i) {
        super(unityPlayerForActivityOrService);
        this.e = unityPlayerForActivityOrService;
        this.b = z;
        this.c = str;
        this.d = i;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        if (this.b) {
            this.e.nativeSoftInputCanceled();
        } else {
            String str = this.c;
            if (str != null) {
                this.e.nativeSetInputString(str);
            }
        }
        if (this.d == 1) {
            this.e.nativeSoftInputClosed();
        }
    }
}

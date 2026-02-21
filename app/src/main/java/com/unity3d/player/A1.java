package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class A1 extends Z0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ UnityPlayerForGameActivity d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    A1(UnityPlayerForGameActivity unityPlayerForGameActivity, int i, int i2) {
        super(unityPlayerForGameActivity);
        this.d = unityPlayerForGameActivity;
        this.b = i;
        this.c = i2;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        this.d.nativeOrientationChanged(this.b, this.c);
    }
}

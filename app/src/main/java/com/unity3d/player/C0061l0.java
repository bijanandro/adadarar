package com.unity3d.player;

import android.content.res.Configuration;

/* JADX INFO: renamed from: com.unity3d.player.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0061l0 extends Z0 {
    final /* synthetic */ Configuration b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0061l0(UnityPlayer unityPlayer, Configuration configuration) {
        super(unityPlayer);
        this.b = configuration;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        UnityAccessibilityDelegate.sendFontScaleChangedNotification(this.b.fontScale);
    }
}

package com.unity3d.player;

import android.view.accessibility.CaptioningManager;
import java.util.Objects;

/* JADX INFO: renamed from: com.unity3d.player.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0090w0 extends CaptioningManager.CaptioningChangeListener {
    final /* synthetic */ UnityAccessibilityDelegate a;

    C0090w0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
        unityAccessibilityDelegate.e.addCaptioningChangeListener(this);
        onEnabledChanged(unityAccessibilityDelegate.e.isEnabled());
    }

    protected void cleanup() {
        this.a.e.removeCaptioningChangeListener(this);
    }

    @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
    public final void onEnabledChanged(boolean z) {
        UnityPlayer unityPlayer = this.a.a;
        Objects.requireNonNull(unityPlayer);
        this.a.a.invokeOnMainThread((Runnable) new C0088v0(unityPlayer, z));
    }
}

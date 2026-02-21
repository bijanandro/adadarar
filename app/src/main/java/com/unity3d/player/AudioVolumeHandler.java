package com.unity3d.player;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class AudioVolumeHandler implements InterfaceC0072p {
    private C0075q a;

    AudioVolumeHandler(Context context) {
        C0075q c0075q = new C0075q(context);
        this.a = c0075q;
        c0075q.a(this);
    }

    public final void a() {
        this.a.a();
        this.a = null;
    }

    @Override // com.unity3d.player.InterfaceC0072p
    public final native void onAudioVolumeChanged(int i);
}

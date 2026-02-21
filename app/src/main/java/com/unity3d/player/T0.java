package com.unity3d.player;

import android.content.Context;
import android.view.OrientationEventListener;

/* JADX INFO: loaded from: classes.dex */
final class T0 extends OrientationEventListener {
    final /* synthetic */ UnityPlayer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    T0(UnityPlayer unityPlayer, Context context, int i) {
        super(context, i);
        this.a = unityPlayer;
    }

    @Override // android.view.OrientationEventListener
    public final void onOrientationChanged(int i) {
        UnityPlayer unityPlayer = this.a;
        unityPlayer.onOrientationChanged(unityPlayer.mNaturalOrientation, i);
    }
}

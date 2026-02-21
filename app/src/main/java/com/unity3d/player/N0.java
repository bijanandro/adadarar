package com.unity3d.player;

import android.os.SystemClock;
import android.view.KeyEvent;
import androidx.core.view.InputDeviceCompat;

/* JADX INFO: loaded from: classes.dex */
final class N0 implements Runnable {
    final /* synthetic */ UnityPlayer a;

    N0(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(jUptimeMillis, jUptimeMillis, 0, 4, 1, 0, -1, 0, 0, InputDeviceCompat.SOURCE_KEYBOARD);
        KeyEvent keyEvent2 = new KeyEvent(jUptimeMillis, jUptimeMillis + 1, 1, 4, 1, 0, -1, 0, 0, InputDeviceCompat.SOURCE_KEYBOARD);
        this.a.getActivity().dispatchKeyEvent(keyEvent);
        this.a.getActivity().dispatchKeyEvent(keyEvent2);
    }
}

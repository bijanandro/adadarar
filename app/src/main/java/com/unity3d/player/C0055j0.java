package com.unity3d.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

/* JADX INFO: renamed from: com.unity3d.player.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0055j0 {
    private Context a;
    private C0052i0 b;

    public C0055j0(Context context) {
        this.a = context;
    }

    public final void a() {
        if (this.b != null) {
            this.a.getContentResolver().unregisterContentObserver(this.b);
            this.b = null;
        }
    }

    public final void a(InterfaceC0049h0 interfaceC0049h0) {
        this.b = new C0052i0(new Handler(Looper.getMainLooper()), interfaceC0049h0);
        this.a.getContentResolver().registerContentObserver(Settings.System.getUriFor("accelerometer_rotation"), true, this.b);
    }
}

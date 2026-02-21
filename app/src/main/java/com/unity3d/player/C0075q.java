package com.unity3d.player;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

/* JADX INFO: renamed from: com.unity3d.player.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0075q {
    private final Context a;
    private final AudioManager b;
    private C0069o c;

    public C0075q(Context context) {
        this.a = context;
        this.b = (AudioManager) context.getSystemService("audio");
    }

    public final void a() {
        if (this.c != null) {
            this.a.getContentResolver().unregisterContentObserver(this.c);
            this.c = null;
        }
    }

    public final void a(InterfaceC0072p interfaceC0072p) {
        this.c = new C0069o(new Handler(Looper.getMainLooper()), this.b, interfaceC0072p);
        this.a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.c);
    }
}

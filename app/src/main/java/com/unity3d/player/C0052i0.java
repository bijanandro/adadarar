package com.unity3d.player;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: renamed from: com.unity3d.player.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0052i0 extends ContentObserver {
    private InterfaceC0049h0 a;

    public C0052i0(Handler handler, InterfaceC0049h0 interfaceC0049h0) {
        super(handler);
        this.a = interfaceC0049h0;
    }

    @Override // android.database.ContentObserver
    public final boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        InterfaceC0049h0 interfaceC0049h0 = this.a;
        if (interfaceC0049h0 != null) {
            ((OrientationLockListener) interfaceC0049h0).b();
        }
    }
}

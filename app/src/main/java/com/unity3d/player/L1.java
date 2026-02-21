package com.unity3d.player;

import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class L1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ boolean e;
    final /* synthetic */ long f;
    final /* synthetic */ long g;
    final /* synthetic */ Q1 h;

    L1(Q1 q1, String str, int i, int i2, int i3, boolean z, long j, long j2) {
        this.h = q1;
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = z;
        this.f = j;
        this.g = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Q1 q1 = this.h;
        if (q1.f != null) {
            D.Log(5, "Video already playing");
            Q1 q12 = this.h;
            q12.g = 2;
            q12.d.release();
            return;
        }
        Q1 q13 = this.h;
        q1.f = new I1(q13.b, q13.a, this.a, this.b, this.c, this.d, this.e, this.f, this.g, new K1(this));
        Q1 q14 = this.h;
        if (q14.f != null) {
            FrameLayout frameLayout = q14.a.getFrameLayout();
            frameLayout.bringToFront();
            frameLayout.addView(this.h.f);
        }
    }
}

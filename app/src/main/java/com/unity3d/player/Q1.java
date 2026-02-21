package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
final class Q1 {
    private UnityPlayer a;
    private P1 c;
    private Context b = null;
    private final Semaphore d = new Semaphore(0);
    private final ReentrantLock e = new ReentrantLock();
    private I1 f = null;
    private int g = 2;
    private boolean h = false;
    private boolean i = false;

    Q1(UnityPlayer unityPlayer) {
        this.a = null;
        this.a = unityPlayer;
    }

    public final void a() {
        this.e.lock();
        I1 i1 = this.f;
        if (i1 != null) {
            i1.updateVideoLayout();
        }
        this.e.unlock();
    }

    public final boolean a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, P1 p1) {
        this.e.lock();
        this.c = p1;
        this.b = context;
        this.d.drainPermits();
        this.g = 2;
        runOnUiThread(new L1(this, str, i, i2, i3, z, j, j2));
        boolean z2 = false;
        try {
            this.e.unlock();
            this.d.acquire();
            this.e.lock();
            if (this.g != 2) {
                z2 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new M1(this));
        runOnUiThread((!z2 || this.g == 3) ? new O1(this) : new N1(this));
        this.e.unlock();
        return z2;
    }

    public final void b() {
        this.e.lock();
        I1 i1 = this.f;
        if (i1 != null) {
            if (this.g == 0) {
                i1.cancelOnPrepare();
            } else if (this.i) {
                boolean zA = i1.a();
                this.h = zA;
                if (!zA) {
                    this.f.pause();
                }
            }
        }
        this.e.unlock();
    }

    public final void c() {
        this.e.lock();
        I1 i1 = this.f;
        if (i1 != null && this.i && !this.h) {
            i1.start();
        }
        this.e.unlock();
    }

    protected void runOnUiThread(Runnable runnable) {
        Context context = this.b;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            D.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}

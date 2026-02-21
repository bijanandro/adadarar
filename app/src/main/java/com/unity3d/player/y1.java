package com.unity3d.player;

import android.content.Context;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
final class y1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ boolean d;
    final /* synthetic */ boolean e;
    final /* synthetic */ boolean f;
    final /* synthetic */ boolean g;
    final /* synthetic */ String h;
    final /* synthetic */ int i;
    final /* synthetic */ boolean j;
    final /* synthetic */ boolean k;
    final /* synthetic */ Semaphore l;
    final /* synthetic */ UnityPlayerForActivityOrService m;

    y1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, UnityPlayerForActivityOrService unityPlayerForActivityOrService2, String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i2, boolean z5, boolean z6, Semaphore semaphore) {
        this.m = unityPlayerForActivityOrService;
        this.a = unityPlayerForActivityOrService2;
        this.b = str;
        this.c = i;
        this.d = z;
        this.e = z2;
        this.f = z3;
        this.g = z4;
        this.h = str2;
        this.i = i2;
        this.j = z5;
        this.k = z6;
        this.l = semaphore;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            try {
                UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.m;
                if (unityPlayerForActivityOrService.mSoftInput != null) {
                    unityPlayerForActivityOrService.dismissSoftInput();
                }
                UnityPlayerForActivityOrService unityPlayerForActivityOrService2 = this.m;
                int iA = SoftInputProvider.a();
                Context context = this.m.mContext;
                UnityPlayerForActivityOrService unityPlayerForActivityOrService3 = this.a;
                unityPlayerForActivityOrService2.mSoftInput = com.unity3d.player.a.a.a(iA) != 2 ? new C0046g0(context, unityPlayerForActivityOrService3) : new C0031b0(context, unityPlayerForActivityOrService3);
                this.m.mSoftInput.a(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
                W w = this.m.mSoftInput;
                w.f = new x1(this);
                w.d();
                this.m.nativeReportKeyboardConfigChanged();
            } catch (Exception e) {
                D.Log(6, "Exception when opening Softinput " + e);
            }
        } finally {
            this.l.release();
        }
    }
}

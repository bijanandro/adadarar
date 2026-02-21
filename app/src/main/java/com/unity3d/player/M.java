package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.view.PixelCopy$OnPixelCopyFinishedListener;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
final class M implements PixelCopy$OnPixelCopyFinishedListener {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ N b;

    M(N n, Semaphore semaphore) {
        this.b = n;
        this.a = semaphore;
    }

    public final void onPixelCopyFinished(int i) {
        this.a.release();
        if (i == 0) {
            Context context = this.b.b.a;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new L(this));
            }
        }
    }
}

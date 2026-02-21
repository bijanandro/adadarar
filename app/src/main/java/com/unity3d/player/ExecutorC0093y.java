package com.unity3d.player;

import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: renamed from: com.unity3d.player.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class ExecutorC0093y implements Executor {
    private final Handler a;

    public ExecutorC0093y(Handler handler) {
        this.a = handler;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (this.a.post(runnable)) {
            return;
        }
        throw new RejectedExecutionException(this.a + " is shutting down");
    }
}

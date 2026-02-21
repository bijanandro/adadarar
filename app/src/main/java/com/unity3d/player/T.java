package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class T implements Runnable {
    final long a;
    final long b;

    public T(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (ReflectionHelper.beginProxyCall(this.a)) {
            try {
                ReflectionHelper.nativeProxyFinalize(this.b);
            } finally {
                ReflectionHelper.endProxyCall();
            }
        }
    }
}

package com.google.android.gms.internal.play_billing;

import java.io.IOException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzci extends IOException {
    zzci() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzci(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    zzci(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}

package com.google.android.gms.internal.play_billing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzay extends zzba {
    private final ByteBuffer zza = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    zzay() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzba
    public final zzbe zza(char c) {
        this.zza.putChar(c);
        try {
            zzb(this.zza.array(), 0, 2);
            return this;
        } finally {
            this.zza.clear();
        }
    }

    protected void zzb(byte[] bArr, int i, int i2) {
        throw null;
    }
}
